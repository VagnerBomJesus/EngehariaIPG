/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import javax.vecmath.Point3f;
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
/*     */ 
/*     */ public class KBCubicSplineSegment
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
/*  96 */   KBKeyFrame[] keyFrame = new KBKeyFrame[4];
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
/*     */   float h0;
/*     */   
/*     */   float h1;
/*     */   
/*     */   float h2;
/*     */   
/*     */   float h3;
/*     */   
/*     */   float p0;
/*     */   
/*     */   float p1;
/*     */   
/*     */   float p2;
/*     */   
/*     */   float p3;
/*     */   float b0;
/*     */   float b1;
/*     */   
/* 132 */   KBCubicSplineSegment() { this.length = 0.0F; }
/*     */ 
/*     */   
/*     */   float b2;
/*     */   
/*     */   float b3;
/*     */   
/*     */   float one_minus_t_in;
/*     */   
/*     */   float one_minus_c_in;
/*     */   
/*     */   float one_minus_b_in;
/*     */   
/*     */   float one_plus_c_in;
/*     */   
/*     */   float one_plus_b_in;
/*     */   
/*     */   float ddb;
/*     */   float dda;
/*     */   
/*     */   KBCubicSplineSegment(KBKeyFrame paramKBKeyFrame1, KBKeyFrame paramKBKeyFrame2, KBKeyFrame paramKBKeyFrame3, KBKeyFrame paramKBKeyFrame4) {
/* 153 */     this.keyFrame[0] = new KBKeyFrame(paramKBKeyFrame1);
/* 154 */     this.keyFrame[1] = new KBKeyFrame(paramKBKeyFrame2);
/* 155 */     this.keyFrame[2] = new KBKeyFrame(paramKBKeyFrame3);
/* 156 */     this.keyFrame[3] = new KBKeyFrame(paramKBKeyFrame4);
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (paramKBKeyFrame3.linear == 1) {
/* 161 */       this.linear = 1;
/*     */     } else {
/* 163 */       this.linear = 0;
/* 164 */       computeCommonCoefficients(paramKBKeyFrame1, paramKBKeyFrame2, paramKBKeyFrame3, paramKBKeyFrame4);
/* 165 */       computeHermiteCoefficients(paramKBKeyFrame1, paramKBKeyFrame2, paramKBKeyFrame3, paramKBKeyFrame4);
/*     */     } 
/*     */     
/* 168 */     this.length = computeLength(1.0F);
/*     */   }
/*     */   float one_minus_t_out; float one_minus_c_out;
/*     */   float one_minus_b_out;
/*     */   float one_plus_c_out;
/*     */   float one_plus_b_out;
/*     */   float dsb;
/*     */   float dsa;
/*     */   float length;
/*     */   int linear;
/*     */   
/*     */   private void computeCommonCoefficients(KBKeyFrame paramKBKeyFrame1, KBKeyFrame paramKBKeyFrame2, KBKeyFrame paramKBKeyFrame3, KBKeyFrame paramKBKeyFrame4) {
/* 180 */     float f1 = 1.0F - paramKBKeyFrame2.tension;
/* 181 */     float f2 = 1.0F - paramKBKeyFrame2.continuity;
/* 182 */     float f3 = 1.0F - paramKBKeyFrame2.bias;
/* 183 */     float f4 = 1.0F + paramKBKeyFrame2.continuity;
/* 184 */     float f5 = 1.0F + paramKBKeyFrame2.bias;
/*     */ 
/*     */     
/* 187 */     this.ddb = f1 * f2 * f3;
/* 188 */     this.dda = f1 * f4 * f5;
/*     */ 
/*     */     
/* 191 */     float f6 = 1.0F - paramKBKeyFrame3.tension;
/* 192 */     float f7 = 1.0F - paramKBKeyFrame3.continuity;
/* 193 */     float f8 = 1.0F - paramKBKeyFrame3.bias;
/* 194 */     float f9 = 1.0F + paramKBKeyFrame3.continuity;
/* 195 */     float f10 = 1.0F + paramKBKeyFrame3.bias;
/*     */ 
/*     */     
/* 198 */     this.dsb = f1 * f4 * f3;
/* 199 */     this.dsa = f1 * f2 * f5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeHermiteCoefficients(KBKeyFrame paramKBKeyFrame1, KBKeyFrame paramKBKeyFrame2, KBKeyFrame paramKBKeyFrame3, KBKeyFrame paramKBKeyFrame4) {
/*     */     float f9, f8, f7, f6, f5, f4;
/* 210 */     Point3f point3f1 = new Point3f();
/* 211 */     Point3f point3f2 = new Point3f();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     paramKBKeyFrame3.position.x -= paramKBKeyFrame2.position.x;
/* 218 */     paramKBKeyFrame3.position.y -= paramKBKeyFrame2.position.y;
/* 219 */     paramKBKeyFrame3.position.z -= paramKBKeyFrame2.position.z;
/*     */     
/* 221 */     paramKBKeyFrame3.scale.x -= paramKBKeyFrame2.scale.x;
/* 222 */     paramKBKeyFrame3.scale.y -= paramKBKeyFrame2.scale.y;
/* 223 */     paramKBKeyFrame3.scale.z -= paramKBKeyFrame2.scale.z;
/*     */ 
/*     */     
/* 226 */     float f1 = paramKBKeyFrame3.heading - paramKBKeyFrame2.heading;
/* 227 */     float f2 = paramKBKeyFrame3.pitch - paramKBKeyFrame2.pitch;
/* 228 */     float f3 = paramKBKeyFrame3.bank - paramKBKeyFrame2.bank;
/*     */ 
/*     */     
/* 231 */     Point3f point3f3 = new Point3f();
/* 232 */     Point3f point3f4 = new Point3f();
/*     */ 
/*     */ 
/*     */     
/* 236 */     if (paramKBKeyFrame1.knot == paramKBKeyFrame2.knot) {
/*     */       
/* 238 */       float f = 0.5F * (this.dda + this.ddb);
/*     */ 
/*     */       
/* 241 */       point3f3.x = f * point3f1.x;
/* 242 */       point3f3.y = f * point3f1.y;
/* 243 */       point3f3.z = f * point3f1.z;
/*     */ 
/*     */       
/* 246 */       point3f4.x = f * point3f2.x;
/* 247 */       point3f4.y = f * point3f2.y;
/* 248 */       point3f4.z = f * point3f2.z;
/*     */ 
/*     */       
/* 251 */       f4 = f * f1;
/* 252 */       f5 = f * f2;
/* 253 */       f6 = f * f3;
/*     */     }
/*     */     else {
/*     */       
/* 257 */       float f = (paramKBKeyFrame2.knot - paramKBKeyFrame1.knot) / (paramKBKeyFrame3.knot - paramKBKeyFrame1.knot);
/*     */ 
/*     */       
/* 260 */       point3f3.x = f * (this.ddb * point3f1.x + this.dda * (paramKBKeyFrame2.position.x - paramKBKeyFrame1.position.x));
/*     */       
/* 262 */       point3f3.y = f * (this.ddb * point3f1.y + this.dda * (paramKBKeyFrame2.position.y - paramKBKeyFrame1.position.y));
/*     */       
/* 264 */       point3f3.z = f * (this.ddb * point3f1.z + this.dda * (paramKBKeyFrame2.position.z - paramKBKeyFrame1.position.z));
/*     */ 
/*     */ 
/*     */       
/* 268 */       point3f4.x = f * (this.ddb * point3f2.x + this.dda * (paramKBKeyFrame2.scale.x - paramKBKeyFrame1.scale.x));
/*     */       
/* 270 */       point3f4.y = f * (this.ddb * point3f2.y + this.dda * (paramKBKeyFrame2.scale.y - paramKBKeyFrame1.scale.y));
/*     */       
/* 272 */       point3f4.z = f * (this.ddb * point3f2.z + this.dda * (paramKBKeyFrame2.scale.z - paramKBKeyFrame1.scale.z));
/*     */ 
/*     */ 
/*     */       
/* 276 */       f4 = f * (this.ddb * f1 + this.dda * (paramKBKeyFrame2.heading - paramKBKeyFrame1.heading));
/*     */       
/* 278 */       f5 = f * (this.ddb * f2 + this.dda * (paramKBKeyFrame2.pitch - paramKBKeyFrame1.pitch));
/*     */       
/* 280 */       f6 = f * (this.ddb * f3 + this.dda * (paramKBKeyFrame2.bank - paramKBKeyFrame1.bank));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 285 */     Point3f point3f5 = new Point3f();
/* 286 */     Point3f point3f6 = new Point3f();
/*     */ 
/*     */ 
/*     */     
/* 290 */     if (paramKBKeyFrame3.knot == paramKBKeyFrame4.knot) {
/*     */       
/* 292 */       float f = 0.5F * (this.dsa + this.dsb);
/*     */ 
/*     */       
/* 295 */       point3f5.x = f * point3f1.x;
/* 296 */       point3f5.y = f * point3f1.y;
/* 297 */       point3f5.z = f * point3f1.z;
/*     */ 
/*     */       
/* 300 */       point3f6.x = f * point3f2.x;
/* 301 */       point3f6.y = f * point3f2.y;
/* 302 */       point3f6.z = f * point3f2.z;
/*     */ 
/*     */       
/* 305 */       f7 = f * f1;
/* 306 */       f8 = f * f2;
/* 307 */       f9 = f * f3;
/*     */     }
/*     */     else {
/*     */       
/* 311 */       float f = (paramKBKeyFrame3.knot - paramKBKeyFrame2.knot) / (paramKBKeyFrame4.knot - paramKBKeyFrame2.knot);
/*     */ 
/*     */       
/* 314 */       point3f5.x = f * (this.dsb * (paramKBKeyFrame4.position.x - paramKBKeyFrame3.position.x) + this.dsa * point3f1.x);
/*     */       
/* 316 */       point3f5.y = f * (this.dsb * (paramKBKeyFrame4.position.y - paramKBKeyFrame3.position.y) + this.dsa * point3f1.y);
/*     */       
/* 318 */       point3f5.z = f * (this.dsb * (paramKBKeyFrame4.position.z - paramKBKeyFrame3.position.z) + this.dsa * point3f1.z);
/*     */ 
/*     */ 
/*     */       
/* 322 */       point3f6.x = f * (this.dsb * (paramKBKeyFrame4.scale.x - paramKBKeyFrame3.scale.x) + this.dsa * point3f2.x);
/*     */       
/* 324 */       point3f6.y = f * (this.dsb * (paramKBKeyFrame4.scale.y - paramKBKeyFrame3.scale.y) + this.dsa * point3f2.y);
/*     */       
/* 326 */       point3f6.z = f * (this.dsb * (paramKBKeyFrame4.scale.z - paramKBKeyFrame3.scale.z) + this.dsa * point3f2.z);
/*     */ 
/*     */ 
/*     */       
/* 330 */       f7 = f * (this.dsb * (paramKBKeyFrame4.heading - paramKBKeyFrame3.heading) + this.dsa * f1);
/*     */       
/* 332 */       f8 = f * (this.dsb * (paramKBKeyFrame4.pitch - paramKBKeyFrame3.pitch) + this.dsa * f2);
/*     */       
/* 334 */       f9 = f * (this.dsb * (paramKBKeyFrame4.bank - paramKBKeyFrame3.bank) + this.dsa * f3);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 339 */     this.c0 = new Point3f();
/* 340 */     this.c0.x = paramKBKeyFrame2.position.x;
/* 341 */     this.c0.y = paramKBKeyFrame2.position.y;
/* 342 */     this.c0.z = paramKBKeyFrame2.position.z;
/*     */     
/* 344 */     this.c1 = new Point3f();
/* 345 */     this.c1.x = point3f3.x;
/* 346 */     this.c1.y = point3f3.y;
/* 347 */     this.c1.z = point3f3.z;
/*     */     
/* 349 */     this.c2 = new Point3f();
/* 350 */     this.c2.x = 3.0F * point3f1.x - 2.0F * point3f3.x - point3f5.x;
/* 351 */     this.c2.y = 3.0F * point3f1.y - 2.0F * point3f3.y - point3f5.y;
/* 352 */     this.c2.z = 3.0F * point3f1.z - 2.0F * point3f3.z - point3f5.z;
/*     */     
/* 354 */     this.c3 = new Point3f();
/* 355 */     this.c3.x = -2.0F * point3f1.x + point3f3.x + point3f5.x;
/* 356 */     this.c3.y = -2.0F * point3f1.y + point3f3.y + point3f5.y;
/* 357 */     this.c3.z = -2.0F * point3f1.z + point3f3.z + point3f5.z;
/*     */ 
/*     */     
/* 360 */     this.e0 = new Point3f();
/* 361 */     this.e0.x = paramKBKeyFrame2.scale.x;
/* 362 */     this.e0.y = paramKBKeyFrame2.scale.y;
/* 363 */     this.e0.z = paramKBKeyFrame2.scale.z;
/*     */     
/* 365 */     this.e1 = new Point3f();
/* 366 */     this.e1.x = point3f4.x;
/* 367 */     this.e1.y = point3f4.y;
/* 368 */     this.e1.z = point3f4.z;
/*     */     
/* 370 */     this.e2 = new Point3f();
/* 371 */     this.e2.x = 3.0F * point3f2.x - 2.0F * point3f4.x - point3f6.x;
/* 372 */     this.e2.y = 3.0F * point3f2.y - 2.0F * point3f4.y - point3f6.y;
/* 373 */     this.e2.z = 3.0F * point3f2.z - 2.0F * point3f4.z - point3f6.z;
/*     */     
/* 375 */     this.e3 = new Point3f();
/* 376 */     this.e3.x = -2.0F * point3f2.x + point3f4.x + point3f6.x;
/* 377 */     this.e3.y = -2.0F * point3f2.y + point3f4.y + point3f6.y;
/* 378 */     this.e3.z = -2.0F * point3f2.z + point3f4.z + point3f6.z;
/*     */ 
/*     */ 
/*     */     
/* 382 */     this.h0 = paramKBKeyFrame2.heading;
/* 383 */     this.p0 = paramKBKeyFrame2.pitch;
/* 384 */     this.b0 = paramKBKeyFrame2.bank;
/*     */     
/* 386 */     this.h1 = f4;
/* 387 */     this.p1 = f5;
/* 388 */     this.b1 = f6;
/*     */     
/* 390 */     this.h2 = 3.0F * f1 - 2.0F * f4 - f7;
/* 391 */     this.p2 = 3.0F * f2 - 2.0F * f5 - f8;
/* 392 */     this.b2 = 3.0F * f3 - 2.0F * f6 - f9;
/*     */     
/* 394 */     this.h3 = -2.0F * f1 + f4 + f7;
/* 395 */     this.p3 = -2.0F * f2 + f5 + f8;
/* 396 */     this.b3 = -2.0F * f3 + f6 + f9;
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
/* 408 */     float f = 0.0F;
/*     */ 
/*     */     
/* 411 */     if (this.linear == 1) {
/* 412 */       f = paramFloat * (this.keyFrame[2]).position.distance((this.keyFrame[1]).position);
/*     */     }
/*     */     else {
/*     */       
/* 416 */       byte b4 = 5;
/* 417 */       for (byte b5 = 0; b5 < b4; b5++)
/* 418 */         f += (float)modCoeff[b5] * computeSpeed(paramFloat * (float)modRoot[b5]); 
/* 419 */       f *= paramFloat;
/*     */     } 
/*     */     
/* 422 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   private float computeSpeed(float paramFloat) {
/* 427 */     Point3f point3f = new Point3f();
/*     */     
/* 429 */     this.c1.x += paramFloat * (2.0F * this.c2.x + 3.0F * paramFloat * this.c3.x);
/* 430 */     this.c1.y += paramFloat * (2.0F * this.c2.y + 3.0F * paramFloat * this.c3.y);
/* 431 */     this.c1.z += paramFloat * (2.0F * this.c2.z + 3.0F * paramFloat * this.c3.z);
/*     */     
/* 433 */     return (float)Math.sqrt((point3f.x * point3f.x + point3f.y * point3f.y + point3f.z * point3f.z));
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
/*     */   public void getInterpolatedScale(float paramFloat, Point3f paramPoint3f) {
/* 451 */     if (this.linear == 1) {
/*     */       
/* 453 */       (this.keyFrame[1]).scale.x += ((this.keyFrame[2]).scale.x - (this.keyFrame[1]).scale.x) * paramFloat;
/*     */       
/* 455 */       (this.keyFrame[1]).scale.y += ((this.keyFrame[2]).scale.y - (this.keyFrame[1]).scale.y) * paramFloat;
/*     */       
/* 457 */       (this.keyFrame[1]).scale.z += ((this.keyFrame[2]).scale.z - (this.keyFrame[1]).scale.z) * paramFloat;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 462 */       this.e0.x += paramFloat * (this.e1.x + paramFloat * (this.e2.x + paramFloat * this.e3.x));
/* 463 */       this.e0.y += paramFloat * (this.e1.y + paramFloat * (this.e2.y + paramFloat * this.e3.y));
/* 464 */       this.e0.z += paramFloat * (this.e1.z + paramFloat * (this.e2.z + paramFloat * this.e3.z));
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
/* 484 */     if (this.linear == 1) {
/* 485 */       (this.keyFrame[1]).position.x += ((this.keyFrame[2]).position.x - (this.keyFrame[1]).position.x) * paramFloat;
/*     */       
/* 487 */       (this.keyFrame[1]).position.y += ((this.keyFrame[2]).position.y - (this.keyFrame[1]).position.y) * paramFloat;
/*     */       
/* 489 */       (this.keyFrame[1]).position.z += ((this.keyFrame[2]).position.z - (this.keyFrame[1]).position.z) * paramFloat;
/*     */     }
/*     */     else {
/*     */       
/* 493 */       this.c0.x += paramFloat * (this.c1.x + paramFloat * (this.c2.x + paramFloat * this.c3.x));
/* 494 */       this.c0.y += paramFloat * (this.c1.y + paramFloat * (this.c2.y + paramFloat * this.c3.y));
/* 495 */       this.c0.z += paramFloat * (this.c1.z + paramFloat * (this.c2.z + paramFloat * this.c3.z));
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
/* 514 */     if (this.linear == 1) {
/* 515 */       paramVector3f.x = (this.keyFrame[1]).position.x + ((this.keyFrame[2]).position.x - (this.keyFrame[1]).position.x) * paramFloat;
/*     */       
/* 517 */       paramVector3f.y = (this.keyFrame[1]).position.y + ((this.keyFrame[2]).position.y - (this.keyFrame[1]).position.y) * paramFloat;
/*     */       
/* 519 */       paramVector3f.z = (this.keyFrame[1]).position.z + ((this.keyFrame[2]).position.z - (this.keyFrame[1]).position.z) * paramFloat;
/*     */     }
/*     */     else {
/*     */       
/* 523 */       paramVector3f.x = this.c0.x + paramFloat * (this.c1.x + paramFloat * (this.c2.x + paramFloat * this.c3.x));
/* 524 */       paramVector3f.y = this.c0.y + paramFloat * (this.c1.y + paramFloat * (this.c2.y + paramFloat * this.c3.y));
/* 525 */       paramVector3f.z = this.c0.z + paramFloat * (this.c1.z + paramFloat * (this.c2.z + paramFloat * this.c3.z));
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
/*     */   public float getInterpolatedHeading(float paramFloat) {
/*     */     float f;
/* 545 */     if (this.linear == 1) {
/*     */       
/* 547 */       f = (this.keyFrame[1]).heading + ((this.keyFrame[2]).heading - (this.keyFrame[1]).heading) * paramFloat;
/*     */     }
/*     */     else {
/*     */       
/* 551 */       f = this.h0 + paramFloat * (this.h1 + paramFloat * (this.h2 + paramFloat * this.h3));
/*     */     } 
/*     */ 
/*     */     
/* 555 */     return f;
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
/*     */   public float getInterpolatedPitch(float paramFloat) {
/*     */     float f;
/* 574 */     if (this.linear == 1) {
/*     */       
/* 576 */       f = (this.keyFrame[1]).pitch + ((this.keyFrame[2]).pitch - (this.keyFrame[1]).pitch) * paramFloat;
/*     */     }
/*     */     else {
/*     */       
/* 580 */       f = this.p0 + paramFloat * (this.p1 + paramFloat * (this.p2 + paramFloat * this.p3));
/*     */     } 
/*     */ 
/*     */     
/* 584 */     return f;
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
/*     */   public float getInterpolatedBank(float paramFloat) {
/*     */     float f;
/* 602 */     if (this.linear == 1) {
/*     */       
/* 604 */       f = (this.keyFrame[1]).bank + ((this.keyFrame[2]).bank - (this.keyFrame[1]).bank) * paramFloat;
/*     */     }
/*     */     else {
/*     */       
/* 608 */       f = this.b0 + paramFloat * (this.b1 + paramFloat * (this.b2 + paramFloat * this.b3));
/*     */     } 
/*     */ 
/*     */     
/* 612 */     return f;
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
/* 628 */   public float getInterpolatedValue(float paramFloat) { return computeLength(paramFloat) / this.length; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\interpolators\KBCubicSplineSegment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */