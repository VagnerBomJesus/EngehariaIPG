/*     */ package javax.vecmath;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class AxisAngle4f
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -163246355858070601L;
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */   public float angle;
/*     */   static final double EPS = 1.0E-6D;
/*     */   
/*     */   public AxisAngle4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  59 */     this.x = paramFloat1;
/*  60 */     this.y = paramFloat2;
/*  61 */     this.z = paramFloat3;
/*  62 */     this.angle = paramFloat4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4f(float[] paramArrayOfFloat) {
/*  72 */     this.x = paramArrayOfFloat[0];
/*  73 */     this.y = paramArrayOfFloat[1];
/*  74 */     this.z = paramArrayOfFloat[2];
/*  75 */     this.angle = paramArrayOfFloat[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4f(AxisAngle4f paramAxisAngle4f) {
/*  86 */     this.x = paramAxisAngle4f.x;
/*  87 */     this.y = paramAxisAngle4f.y;
/*  88 */     this.z = paramAxisAngle4f.z;
/*  89 */     this.angle = paramAxisAngle4f.angle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4f(AxisAngle4d paramAxisAngle4d) {
/*  99 */     this.x = (float)paramAxisAngle4d.x;
/* 100 */     this.y = (float)paramAxisAngle4d.y;
/* 101 */     this.z = (float)paramAxisAngle4d.z;
/* 102 */     this.angle = (float)paramAxisAngle4d.angle;
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
/*     */   public AxisAngle4f(Vector3f paramVector3f, float paramFloat) {
/* 115 */     this.x = paramVector3f.x;
/* 116 */     this.y = paramVector3f.y;
/* 117 */     this.z = paramVector3f.z;
/* 118 */     this.angle = paramFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4f() {
/* 127 */     this.x = 0.0F;
/* 128 */     this.y = 0.0F;
/* 129 */     this.z = 1.0F;
/* 130 */     this.angle = 0.0F;
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
/*     */   public final void set(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 143 */     this.x = paramFloat1;
/* 144 */     this.y = paramFloat2;
/* 145 */     this.z = paramFloat3;
/* 146 */     this.angle = paramFloat4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(float[] paramArrayOfFloat) {
/* 157 */     this.x = paramArrayOfFloat[0];
/* 158 */     this.y = paramArrayOfFloat[1];
/* 159 */     this.z = paramArrayOfFloat[2];
/* 160 */     this.angle = paramArrayOfFloat[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(AxisAngle4f paramAxisAngle4f) {
/* 170 */     this.x = paramAxisAngle4f.x;
/* 171 */     this.y = paramAxisAngle4f.y;
/* 172 */     this.z = paramAxisAngle4f.z;
/* 173 */     this.angle = paramAxisAngle4f.angle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(AxisAngle4d paramAxisAngle4d) {
/* 183 */     this.x = (float)paramAxisAngle4d.x;
/* 184 */     this.y = (float)paramAxisAngle4d.y;
/* 185 */     this.z = (float)paramAxisAngle4d.z;
/* 186 */     this.angle = (float)paramAxisAngle4d.angle;
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
/*     */   public final void set(Vector3f paramVector3f, float paramFloat) {
/* 199 */     this.x = paramVector3f.x;
/* 200 */     this.y = paramVector3f.y;
/* 201 */     this.z = paramVector3f.z;
/* 202 */     this.angle = paramFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(float[] paramArrayOfFloat) {
/* 212 */     paramArrayOfFloat[0] = this.x;
/* 213 */     paramArrayOfFloat[1] = this.y;
/* 214 */     paramArrayOfFloat[2] = this.z;
/* 215 */     paramArrayOfFloat[3] = this.angle;
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
/*     */   public final void set(Quat4f paramQuat4f) {
/* 228 */     double d = (paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z);
/*     */     
/* 230 */     if (d > 1.0E-6D) {
/* 231 */       d = Math.sqrt(d);
/* 232 */       double d1 = 1.0D / d;
/*     */       
/* 234 */       this.x = (float)(paramQuat4f.x * d1);
/* 235 */       this.y = (float)(paramQuat4f.y * d1);
/* 236 */       this.z = (float)(paramQuat4f.z * d1);
/* 237 */       this.angle = (float)(2.0D * Math.atan2(d, paramQuat4f.w));
/*     */     } else {
/* 239 */       this.x = 0.0F;
/* 240 */       this.y = 1.0F;
/* 241 */       this.z = 0.0F;
/* 242 */       this.angle = 0.0F;
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
/*     */   public final void set(Quat4d paramQuat4d) {
/* 256 */     double d = paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z;
/*     */     
/* 258 */     if (d > 1.0E-6D) {
/* 259 */       d = Math.sqrt(d);
/* 260 */       double d1 = 1.0D / d;
/*     */       
/* 262 */       this.x = (float)(paramQuat4d.x * d1);
/* 263 */       this.y = (float)(paramQuat4d.y * d1);
/* 264 */       this.z = (float)(paramQuat4d.z * d1);
/* 265 */       this.angle = (float)(2.0D * Math.atan2(d, paramQuat4d.w));
/*     */     } else {
/* 267 */       this.x = 0.0F;
/* 268 */       this.y = 1.0F;
/* 269 */       this.z = 0.0F;
/* 270 */       this.angle = 0.0F;
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
/*     */   public final void set(Matrix4f paramMatrix4f) {
/* 284 */     Matrix3f matrix3f = new Matrix3f();
/*     */     
/* 286 */     paramMatrix4f.get(matrix3f);
/*     */     
/* 288 */     this.x = matrix3f.m21 - matrix3f.m12;
/* 289 */     this.y = matrix3f.m02 - matrix3f.m20;
/* 290 */     this.z = matrix3f.m10 - matrix3f.m01;
/* 291 */     double d = (this.x * this.x + this.y * this.y + this.z * this.z);
/*     */     
/* 293 */     if (d > 1.0E-6D) {
/* 294 */       d = Math.sqrt(d);
/* 295 */       double d1 = 0.5D * d;
/* 296 */       double d2 = 0.5D * ((matrix3f.m00 + matrix3f.m11 + matrix3f.m22) - 1.0D);
/*     */       
/* 298 */       this.angle = (float)Math.atan2(d1, d2);
/* 299 */       double d3 = 1.0D / d;
/* 300 */       this.x = (float)(this.x * d3);
/* 301 */       this.y = (float)(this.y * d3);
/* 302 */       this.z = (float)(this.z * d3);
/*     */     } else {
/* 304 */       this.x = 0.0F;
/* 305 */       this.y = 1.0F;
/* 306 */       this.z = 0.0F;
/* 307 */       this.angle = 0.0F;
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
/*     */   public final void set(Matrix4d paramMatrix4d) {
/* 323 */     Matrix3d matrix3d = new Matrix3d();
/*     */     
/* 325 */     paramMatrix4d.get(matrix3d);
/*     */ 
/*     */     
/* 328 */     this.x = (float)(matrix3d.m21 - matrix3d.m12);
/* 329 */     this.y = (float)(matrix3d.m02 - matrix3d.m20);
/* 330 */     this.z = (float)(matrix3d.m10 - matrix3d.m01);
/* 331 */     double d = (this.x * this.x + this.y * this.y + this.z * this.z);
/*     */     
/* 333 */     if (d > 1.0E-6D) {
/* 334 */       d = Math.sqrt(d);
/* 335 */       double d1 = 0.5D * d;
/* 336 */       double d2 = 0.5D * (matrix3d.m00 + matrix3d.m11 + matrix3d.m22 - 1.0D);
/* 337 */       this.angle = (float)Math.atan2(d1, d2);
/*     */       
/* 339 */       double d3 = 1.0D / d;
/* 340 */       this.x = (float)(this.x * d3);
/* 341 */       this.y = (float)(this.y * d3);
/* 342 */       this.z = (float)(this.z * d3);
/*     */     } else {
/* 344 */       this.x = 0.0F;
/* 345 */       this.y = 1.0F;
/* 346 */       this.z = 0.0F;
/* 347 */       this.angle = 0.0F;
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
/*     */   public final void set(Matrix3f paramMatrix3f) {
/* 362 */     this.x = paramMatrix3f.m21 - paramMatrix3f.m12;
/* 363 */     this.y = paramMatrix3f.m02 - paramMatrix3f.m20;
/* 364 */     this.z = paramMatrix3f.m10 - paramMatrix3f.m01;
/* 365 */     double d = (this.x * this.x + this.y * this.y + this.z * this.z);
/* 366 */     if (d > 1.0E-6D) {
/* 367 */       d = Math.sqrt(d);
/* 368 */       double d1 = 0.5D * d;
/* 369 */       double d2 = 0.5D * ((paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22) - 1.0D);
/*     */       
/* 371 */       this.angle = (float)Math.atan2(d1, d2);
/*     */       
/* 373 */       double d3 = 1.0D / d;
/* 374 */       this.x = (float)(this.x * d3);
/* 375 */       this.y = (float)(this.y * d3);
/* 376 */       this.z = (float)(this.z * d3);
/*     */     } else {
/* 378 */       this.x = 0.0F;
/* 379 */       this.y = 1.0F;
/* 380 */       this.z = 0.0F;
/* 381 */       this.angle = 0.0F;
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
/*     */   public final void set(Matrix3d paramMatrix3d) {
/* 397 */     this.x = (float)(paramMatrix3d.m21 - paramMatrix3d.m12);
/* 398 */     this.y = (float)(paramMatrix3d.m02 - paramMatrix3d.m20);
/* 399 */     this.z = (float)(paramMatrix3d.m10 - paramMatrix3d.m01);
/* 400 */     double d = (this.x * this.x + this.y * this.y + this.z * this.z);
/*     */     
/* 402 */     if (d > 1.0E-6D) {
/* 403 */       d = Math.sqrt(d);
/* 404 */       double d1 = 0.5D * d;
/* 405 */       double d2 = 0.5D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 - 1.0D);
/*     */       
/* 407 */       this.angle = (float)Math.atan2(d1, d2);
/*     */       
/* 409 */       double d3 = 1.0D / d;
/* 410 */       this.x = (float)(this.x * d3);
/* 411 */       this.y = (float)(this.y * d3);
/* 412 */       this.z = (float)(this.z * d3);
/*     */     } else {
/* 414 */       this.x = 0.0F;
/* 415 */       this.y = 1.0F;
/* 416 */       this.z = 0.0F;
/* 417 */       this.angle = 0.0F;
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
/* 428 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle + ")"; }
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
/*     */   public boolean equals(AxisAngle4f paramAxisAngle4f) {
/*     */     try {
/* 441 */       return (this.x == paramAxisAngle4f.x && this.y == paramAxisAngle4f.y && this.z == paramAxisAngle4f.z && this.angle == paramAxisAngle4f.angle);
/*     */     } catch (NullPointerException nullPointerException) {
/*     */       
/* 444 */       return false;
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
/*     */   public boolean equals(Object paramObject) {
/*     */     
/* 458 */     try { AxisAngle4f axisAngle4f = (AxisAngle4f)paramObject;
/* 459 */       return (this.x == axisAngle4f.x && this.y == axisAngle4f.y && this.z == axisAngle4f.z && this.angle == axisAngle4f.angle); }
/*     */     catch (NullPointerException nullPointerException)
/*     */     
/* 462 */     { return false; }
/* 463 */     catch (ClassCastException classCastException) { return false; }
/*     */   
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
/*     */   public boolean epsilonEquals(AxisAngle4f paramAxisAngle4f, float paramFloat) {
/* 480 */     float f = this.x - paramAxisAngle4f.x;
/* 481 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 483 */     f = this.y - paramAxisAngle4f.y;
/* 484 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 486 */     f = this.z - paramAxisAngle4f.z;
/* 487 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 489 */     f = this.angle - paramAxisAngle4f.angle;
/* 490 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 492 */     return true;
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
/*     */   public int hashCode() {
/* 506 */     long l = 1L;
/* 507 */     l = 31L * l + VecMathUtil.floatToIntBits(this.x);
/* 508 */     l = 31L * l + VecMathUtil.floatToIntBits(this.y);
/* 509 */     l = 31L * l + VecMathUtil.floatToIntBits(this.z);
/* 510 */     l = 31L * l + VecMathUtil.floatToIntBits(this.angle);
/* 511 */     return (int)(l ^ l >> 32);
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
/*     */   public Object clone() {
/*     */     try {
/* 525 */       return super.clone();
/* 526 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 528 */       throw new InternalError();
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
/* 542 */   public final float getAngle() { return this.angle; }
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
/* 555 */   public final void setAngle(float paramFloat) { this.angle = paramFloat; }
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
/* 567 */   public final float getX() { return this.x; }
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
/* 579 */   public final void setX(float paramFloat) { this.x = paramFloat; }
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
/* 591 */   public final float getY() { return this.y; }
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
/* 603 */   public final void setY(float paramFloat) { this.y = paramFloat; }
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
/* 615 */   public final float getZ() { return this.z; }
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
/* 627 */   public final void setZ(float paramFloat) { this.z = paramFloat; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\AxisAngle4f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */