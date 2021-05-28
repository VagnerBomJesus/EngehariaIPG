/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CubicSplineSegment
/*     */ {
/*  76 */   static final double[] modRoot = { 0.046910077D, 0.230765345D, 0.5D, 0.769234655D, 0.953089922D };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   static final double[] modCoeff = { 0.118463442D, 0.239314335D, 0.284444444D, 0.239314335D, 0.118463442D };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   TCBKeyFrame[] keyFrame = new TCBKeyFrame[4];
/*     */   
/*     */   Point3f c0;
/*     */   
/*     */   Point3f c1;
/*     */   
/*     */   Point3f c2;
/*     */   
/*     */   Point3f c3;
/*     */   
/*     */   Point3f e0;
/*     */   
/*     */   Point3f e1;
/*     */   
/*     */   Point3f e2;
/*     */   
/*     */   Point3f e3;
/*     */   
/*     */   float one_minus_t_in;
/*     */   float one_minus_c_in;
/*     */   float one_minus_b_in;
/*     */   float one_plus_c_in;
/*     */   float one_plus_b_in;
/*     */   float ddb;
/*     */   float dda;
/*     */   float one_minus_t_out;
/*     */   float one_minus_c_out;
/*     */   float one_minus_b_out;
/*     */   float one_plus_c_out;
/*     */   float one_plus_b_out;
/*     */   float dsb;
/*     */   float dsa;
/*     */   float length;
/*     */   int linear;
/*     */   
/* 131 */   CubicSplineSegment() { this.length = 0.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CubicSplineSegment(TCBKeyFrame paramTCBKeyFrame1, TCBKeyFrame paramTCBKeyFrame2, TCBKeyFrame paramTCBKeyFrame3, TCBKeyFrame paramTCBKeyFrame4) {
/* 152 */     this.keyFrame[0] = new TCBKeyFrame(paramTCBKeyFrame1);
/* 153 */     this.keyFrame[1] = new TCBKeyFrame(paramTCBKeyFrame2);
/* 154 */     this.keyFrame[2] = new TCBKeyFrame(paramTCBKeyFrame3);
/* 155 */     this.keyFrame[3] = new TCBKeyFrame(paramTCBKeyFrame4);
/*     */ 
/*     */ 
/*     */     
/* 159 */     if (paramTCBKeyFrame3.linear == 1) {
/* 160 */       this.linear = 1;
/*     */     } else {
/* 162 */       this.linear = 0;
/* 163 */       computeCommonCoefficients(paramTCBKeyFrame1, paramTCBKeyFrame2, paramTCBKeyFrame3, paramTCBKeyFrame4);
/* 164 */       computeHermiteCoefficients(paramTCBKeyFrame1, paramTCBKeyFrame2, paramTCBKeyFrame3, paramTCBKeyFrame4);
/*     */     } 
/*     */     
/* 167 */     this.length = computeLength(1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeCommonCoefficients(TCBKeyFrame paramTCBKeyFrame1, TCBKeyFrame paramTCBKeyFrame2, TCBKeyFrame paramTCBKeyFrame3, TCBKeyFrame paramTCBKeyFrame4) {
/* 179 */     float f1 = 1.0F - paramTCBKeyFrame2.tension;
/* 180 */     float f2 = 1.0F - paramTCBKeyFrame2.continuity;
/* 181 */     float f3 = 1.0F - paramTCBKeyFrame2.bias;
/* 182 */     float f4 = 1.0F + paramTCBKeyFrame2.continuity;
/* 183 */     float f5 = 1.0F + paramTCBKeyFrame2.bias;
/*     */ 
/*     */     
/* 186 */     this.ddb = f1 * f2 * f3;
/* 187 */     this.dda = f1 * f4 * f5;
/*     */ 
/*     */     
/* 190 */     float f6 = 1.0F - paramTCBKeyFrame3.tension;
/* 191 */     float f7 = 1.0F - paramTCBKeyFrame3.continuity;
/* 192 */     float f8 = 1.0F - paramTCBKeyFrame3.bias;
/* 193 */     float f9 = 1.0F + paramTCBKeyFrame3.continuity;
/* 194 */     float f10 = 1.0F + paramTCBKeyFrame3.bias;
/*     */ 
/*     */     
/* 197 */     this.dsb = f1 * f4 * f3;
/* 198 */     this.dsa = f1 * f2 * f5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeHermiteCoefficients(TCBKeyFrame paramTCBKeyFrame1, TCBKeyFrame paramTCBKeyFrame2, TCBKeyFrame paramTCBKeyFrame3, TCBKeyFrame paramTCBKeyFrame4) {
/* 209 */     Point3f point3f1 = new Point3f();
/* 210 */     Point3f point3f2 = new Point3f();
/*     */ 
/*     */     
/* 213 */     paramTCBKeyFrame3.position.x -= paramTCBKeyFrame2.position.x;
/* 214 */     paramTCBKeyFrame3.position.y -= paramTCBKeyFrame2.position.y;
/* 215 */     paramTCBKeyFrame3.position.z -= paramTCBKeyFrame2.position.z;
/*     */     
/* 217 */     paramTCBKeyFrame3.scale.x -= paramTCBKeyFrame2.scale.x;
/* 218 */     paramTCBKeyFrame3.scale.y -= paramTCBKeyFrame2.scale.y;
/* 219 */     paramTCBKeyFrame3.scale.z -= paramTCBKeyFrame2.scale.z;
/*     */ 
/*     */     
/* 222 */     Point3f point3f3 = new Point3f();
/* 223 */     Point3f point3f4 = new Point3f();
/*     */ 
/*     */     
/* 226 */     if (paramTCBKeyFrame1.knot == paramTCBKeyFrame2.knot) {
/*     */       
/* 228 */       float f = 0.5F * (this.dda + this.ddb);
/*     */ 
/*     */       
/* 231 */       point3f3.x = f * point3f1.x;
/* 232 */       point3f3.y = f * point3f1.y;
/* 233 */       point3f3.z = f * point3f1.z;
/*     */ 
/*     */       
/* 236 */       point3f4.x = f * point3f2.x;
/* 237 */       point3f4.y = f * point3f2.y;
/* 238 */       point3f4.z = f * point3f2.z;
/*     */     }
/*     */     else {
/*     */       
/* 242 */       float f = (paramTCBKeyFrame2.knot - paramTCBKeyFrame1.knot) / (paramTCBKeyFrame3.knot - paramTCBKeyFrame1.knot);
/*     */ 
/*     */       
/* 245 */       point3f3.x = f * (this.ddb * point3f1.x + this.dda * (paramTCBKeyFrame2.position.x - paramTCBKeyFrame1.position.x));
/*     */       
/* 247 */       point3f3.y = f * (this.ddb * point3f1.y + this.dda * (paramTCBKeyFrame2.position.y - paramTCBKeyFrame1.position.y));
/*     */       
/* 249 */       point3f3.z = f * (this.ddb * point3f1.z + this.dda * (paramTCBKeyFrame2.position.z - paramTCBKeyFrame1.position.z));
/*     */ 
/*     */ 
/*     */       
/* 253 */       point3f4.x = f * (this.ddb * point3f2.x + this.dda * (paramTCBKeyFrame2.scale.x - paramTCBKeyFrame1.scale.x));
/*     */       
/* 255 */       point3f4.y = f * (this.ddb * point3f2.y + this.dda * (paramTCBKeyFrame2.scale.y - paramTCBKeyFrame1.scale.y));
/*     */       
/* 257 */       point3f4.z = f * (this.ddb * point3f2.z + this.dda * (paramTCBKeyFrame2.scale.z - paramTCBKeyFrame1.scale.z));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 262 */     Point3f point3f5 = new Point3f();
/* 263 */     Point3f point3f6 = new Point3f();
/*     */ 
/*     */     
/* 266 */     if (paramTCBKeyFrame3.knot == paramTCBKeyFrame4.knot) {
/*     */       
/* 268 */       float f = 0.5F * (this.dsa + this.dsb);
/*     */ 
/*     */       
/* 271 */       point3f5.x = f * point3f1.x;
/* 272 */       point3f5.y = f * point3f1.y;
/* 273 */       point3f5.z = f * point3f1.z;
/*     */ 
/*     */       
/* 276 */       point3f6.x = f * point3f2.x;
/* 277 */       point3f6.y = f * point3f2.y;
/* 278 */       point3f6.z = f * point3f2.z;
/*     */     }
/*     */     else {
/*     */       
/* 282 */       float f = (paramTCBKeyFrame3.knot - paramTCBKeyFrame2.knot) / (paramTCBKeyFrame4.knot - paramTCBKeyFrame2.knot);
/*     */ 
/*     */       
/* 285 */       point3f5.x = f * (this.dsb * (paramTCBKeyFrame4.position.x - paramTCBKeyFrame3.position.x) + this.dsa * point3f1.x);
/*     */       
/* 287 */       point3f5.y = f * (this.dsb * (paramTCBKeyFrame4.position.y - paramTCBKeyFrame3.position.y) + this.dsa * point3f1.y);
/*     */       
/* 289 */       point3f5.z = f * (this.dsb * (paramTCBKeyFrame4.position.z - paramTCBKeyFrame3.position.z) + this.dsa * point3f1.z);
/*     */ 
/*     */ 
/*     */       
/* 293 */       point3f6.x = f * (this.dsb * (paramTCBKeyFrame4.scale.x - paramTCBKeyFrame3.scale.x) + this.dsa * point3f2.x);
/*     */       
/* 295 */       point3f6.y = f * (this.dsb * (paramTCBKeyFrame4.scale.y - paramTCBKeyFrame3.scale.y) + this.dsa * point3f2.y);
/*     */       
/* 297 */       point3f6.z = f * (this.dsb * (paramTCBKeyFrame4.scale.z - paramTCBKeyFrame3.scale.z) + this.dsa * point3f2.z);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 302 */     this.c0 = new Point3f();
/* 303 */     this.c0.x = paramTCBKeyFrame2.position.x;
/* 304 */     this.c0.y = paramTCBKeyFrame2.position.y;
/* 305 */     this.c0.z = paramTCBKeyFrame2.position.z;
/*     */     
/* 307 */     this.c1 = new Point3f();
/* 308 */     this.c1.x = point3f3.x;
/* 309 */     this.c1.y = point3f3.y;
/* 310 */     this.c1.z = point3f3.z;
/*     */     
/* 312 */     this.c2 = new Point3f();
/* 313 */     this.c2.x = 3.0F * point3f1.x - 2.0F * point3f3.x - point3f5.x;
/* 314 */     this.c2.y = 3.0F * point3f1.y - 2.0F * point3f3.y - point3f5.y;
/* 315 */     this.c2.z = 3.0F * point3f1.z - 2.0F * point3f3.z - point3f5.z;
/*     */     
/* 317 */     this.c3 = new Point3f();
/* 318 */     this.c3.x = -2.0F * point3f1.x + point3f3.x + point3f5.x;
/* 319 */     this.c3.y = -2.0F * point3f1.y + point3f3.y + point3f5.y;
/* 320 */     this.c3.z = -2.0F * point3f1.z + point3f3.z + point3f5.z;
/*     */ 
/*     */     
/* 323 */     this.e0 = new Point3f();
/* 324 */     this.e0.x = paramTCBKeyFrame2.scale.x;
/* 325 */     this.e0.y = paramTCBKeyFrame2.scale.y;
/* 326 */     this.e0.z = paramTCBKeyFrame2.scale.z;
/*     */     
/* 328 */     this.e1 = new Point3f();
/* 329 */     this.e1.x = point3f4.x;
/* 330 */     this.e1.y = point3f4.y;
/* 331 */     this.e1.z = point3f4.z;
/*     */     
/* 333 */     this.e2 = new Point3f();
/* 334 */     this.e2.x = 3.0F * point3f2.x - 2.0F * point3f4.x - point3f6.x;
/* 335 */     this.e2.y = 3.0F * point3f2.y - 2.0F * point3f4.y - point3f6.y;
/* 336 */     this.e2.z = 3.0F * point3f2.z - 2.0F * point3f4.z - point3f6.z;
/*     */     
/* 338 */     this.e3 = new Point3f();
/* 339 */     this.e3.x = -2.0F * point3f2.x + point3f4.x + point3f6.x;
/* 340 */     this.e3.y = -2.0F * point3f2.y + point3f4.y + point3f6.y;
/* 341 */     this.e3.z = -2.0F * point3f2.z + point3f4.z + point3f6.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float computeLength(float paramFloat) {
/* 353 */     float f = 0.0F;
/*     */ 
/*     */     
/* 356 */     if (this.linear == 1) {
/* 357 */       f = paramFloat * (this.keyFrame[2]).position.distance((this.keyFrame[1]).position);
/*     */     }
/*     */     else {
/*     */       
/* 361 */       byte b1 = 5;
/* 362 */       for (byte b2 = 0; b2 < b1; b2++)
/* 363 */         f += (float)modCoeff[b2] * computeSpeed(paramFloat * (float)modRoot[b2]); 
/* 364 */       f *= paramFloat;
/*     */     } 
/*     */     
/* 367 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   private float computeSpeed(float paramFloat) {
/* 372 */     Point3f point3f = new Point3f();
/*     */     
/* 374 */     this.c1.x += paramFloat * (2.0F * this.c2.x + 3.0F * paramFloat * this.c3.x);
/* 375 */     this.c1.y += paramFloat * (2.0F * this.c2.y + 3.0F * paramFloat * this.c3.y);
/* 376 */     this.c1.z += paramFloat * (2.0F * this.c2.z + 3.0F * paramFloat * this.c3.z);
/*     */     
/* 378 */     return (float)Math.sqrt((point3f.x * point3f.x + point3f.y * point3f.y + point3f.z * point3f.z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getInterpolatedQuaternion(float paramFloat, Quat4f paramQuat4f) {
/* 395 */     if (this.linear == 1) {
/*     */ 
/*     */       
/* 398 */       double d = ((this.keyFrame[1]).quat.x * (this.keyFrame[2]).quat.x + (this.keyFrame[1]).quat.y * (this.keyFrame[2]).quat.y + (this.keyFrame[1]).quat.z * (this.keyFrame[2]).quat.z + (this.keyFrame[1]).quat.w * (this.keyFrame[2]).quat.w);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 403 */       if (d < 0.0D) {
/* 404 */         (this.keyFrame[1]).quat.x += (-(this.keyFrame[2]).quat.x - (this.keyFrame[1]).quat.x) * paramFloat;
/*     */         
/* 406 */         (this.keyFrame[1]).quat.y += (-(this.keyFrame[2]).quat.y - (this.keyFrame[1]).quat.y) * paramFloat;
/*     */         
/* 408 */         (this.keyFrame[1]).quat.z += (-(this.keyFrame[2]).quat.z - (this.keyFrame[1]).quat.z) * paramFloat;
/*     */         
/* 410 */         (this.keyFrame[1]).quat.w += (-(this.keyFrame[2]).quat.w - (this.keyFrame[1]).quat.w) * paramFloat;
/*     */       } else {
/*     */         
/* 413 */         (this.keyFrame[1]).quat.x += ((this.keyFrame[2]).quat.x - (this.keyFrame[1]).quat.x) * paramFloat;
/*     */         
/* 415 */         (this.keyFrame[1]).quat.y += ((this.keyFrame[2]).quat.y - (this.keyFrame[1]).quat.y) * paramFloat;
/*     */         
/* 417 */         (this.keyFrame[1]).quat.z += ((this.keyFrame[2]).quat.z - (this.keyFrame[1]).quat.z) * paramFloat;
/*     */         
/* 419 */         (this.keyFrame[1]).quat.w += ((this.keyFrame[2]).quat.w - (this.keyFrame[1]).quat.w) * paramFloat;
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 429 */       paramQuat4f.interpolate((this.keyFrame[1]).quat, (this.keyFrame[2]).quat, paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getInterpolatedScale(float paramFloat, Point3f paramPoint3f) {
/* 450 */     if (this.linear == 1) {
/*     */       
/* 452 */       (this.keyFrame[1]).scale.x += ((this.keyFrame[2]).scale.x - (this.keyFrame[1]).scale.x) * paramFloat;
/*     */       
/* 454 */       (this.keyFrame[1]).scale.y += ((this.keyFrame[2]).scale.y - (this.keyFrame[1]).scale.y) * paramFloat;
/*     */       
/* 456 */       (this.keyFrame[1]).scale.z += ((this.keyFrame[2]).scale.z - (this.keyFrame[1]).scale.z) * paramFloat;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 461 */       this.e0.x += paramFloat * (this.e1.x + paramFloat * (this.e2.x + paramFloat * this.e3.x));
/* 462 */       this.e0.y += paramFloat * (this.e1.y + paramFloat * (this.e2.y + paramFloat * this.e3.y));
/* 463 */       this.e0.z += paramFloat * (this.e1.z + paramFloat * (this.e2.z + paramFloat * this.e3.z));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getInterpolatedPosition(float paramFloat, Point3f paramPoint3f) {
/* 483 */     if (this.linear == 1) {
/* 484 */       (this.keyFrame[1]).position.x += ((this.keyFrame[2]).position.x - (this.keyFrame[1]).position.x) * paramFloat;
/*     */       
/* 486 */       (this.keyFrame[1]).position.y += ((this.keyFrame[2]).position.y - (this.keyFrame[1]).position.y) * paramFloat;
/*     */       
/* 488 */       (this.keyFrame[1]).position.z += ((this.keyFrame[2]).position.z - (this.keyFrame[1]).position.z) * paramFloat;
/*     */     }
/*     */     else {
/*     */       
/* 492 */       this.c0.x += paramFloat * (this.c1.x + paramFloat * (this.c2.x + paramFloat * this.c3.x));
/* 493 */       this.c0.y += paramFloat * (this.c1.y + paramFloat * (this.c2.y + paramFloat * this.c3.y));
/* 494 */       this.c0.z += paramFloat * (this.c1.z + paramFloat * (this.c2.z + paramFloat * this.c3.z));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getInterpolatedPositionVector(float paramFloat, Vector3f paramVector3f) {
/* 513 */     if (this.linear == 1) {
/* 514 */       paramVector3f.x = (this.keyFrame[1]).position.x + ((this.keyFrame[2]).position.x - (this.keyFrame[1]).position.x) * paramFloat;
/*     */       
/* 516 */       paramVector3f.y = (this.keyFrame[1]).position.y + ((this.keyFrame[2]).position.y - (this.keyFrame[1]).position.y) * paramFloat;
/*     */       
/* 518 */       paramVector3f.z = (this.keyFrame[1]).position.z + ((this.keyFrame[2]).position.z - (this.keyFrame[1]).position.z) * paramFloat;
/*     */     }
/*     */     else {
/*     */       
/* 522 */       paramVector3f.x = this.c0.x + paramFloat * (this.c1.x + paramFloat * (this.c2.x + paramFloat * this.c3.x));
/* 523 */       paramVector3f.y = this.c0.y + paramFloat * (this.c1.y + paramFloat * (this.c2.y + paramFloat * this.c3.y));
/* 524 */       paramVector3f.z = this.c0.z + paramFloat * (this.c1.z + paramFloat * (this.c2.z + paramFloat * this.c3.z));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 541 */   public float getInterpolatedValue(float paramFloat) { return computeLength(paramFloat) / this.length; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\interpolators\CubicSplineSegment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */