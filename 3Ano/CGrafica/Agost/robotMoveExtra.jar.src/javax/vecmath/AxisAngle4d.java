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
/*     */ 
/*     */ 
/*     */ public class AxisAngle4d
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = 3644296204459140589L;
/*     */   public double x;
/*     */   public double y;
/*     */   public double z;
/*     */   public double angle;
/*     */   static final double EPS = 1.0E-6D;
/*     */   
/*     */   public AxisAngle4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/*  61 */     this.x = paramDouble1;
/*  62 */     this.y = paramDouble2;
/*  63 */     this.z = paramDouble3;
/*  64 */     this.angle = paramDouble4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4d(double[] paramArrayOfDouble) {
/*  75 */     this.x = paramArrayOfDouble[0];
/*  76 */     this.y = paramArrayOfDouble[1];
/*  77 */     this.z = paramArrayOfDouble[2];
/*  78 */     this.angle = paramArrayOfDouble[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4d(AxisAngle4d paramAxisAngle4d) {
/*  86 */     this.x = paramAxisAngle4d.x;
/*  87 */     this.y = paramAxisAngle4d.y;
/*  88 */     this.z = paramAxisAngle4d.z;
/*  89 */     this.angle = paramAxisAngle4d.angle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4d(AxisAngle4f paramAxisAngle4f) {
/* 100 */     this.x = paramAxisAngle4f.x;
/* 101 */     this.y = paramAxisAngle4f.y;
/* 102 */     this.z = paramAxisAngle4f.z;
/* 103 */     this.angle = paramAxisAngle4f.angle;
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
/*     */   public AxisAngle4d(Vector3d paramVector3d, double paramDouble) {
/* 116 */     this.x = paramVector3d.x;
/* 117 */     this.y = paramVector3d.y;
/* 118 */     this.z = paramVector3d.z;
/* 119 */     this.angle = paramDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAngle4d() {
/* 128 */     this.x = 0.0D;
/* 129 */     this.y = 0.0D;
/* 130 */     this.z = 1.0D;
/* 131 */     this.angle = 0.0D;
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
/*     */   public final void set(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 144 */     this.x = paramDouble1;
/* 145 */     this.y = paramDouble2;
/* 146 */     this.z = paramDouble3;
/* 147 */     this.angle = paramDouble4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(double[] paramArrayOfDouble) {
/* 157 */     this.x = paramArrayOfDouble[0];
/* 158 */     this.y = paramArrayOfDouble[1];
/* 159 */     this.z = paramArrayOfDouble[2];
/* 160 */     this.angle = paramArrayOfDouble[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(AxisAngle4d paramAxisAngle4d) {
/* 170 */     this.x = paramAxisAngle4d.x;
/* 171 */     this.y = paramAxisAngle4d.y;
/* 172 */     this.z = paramAxisAngle4d.z;
/* 173 */     this.angle = paramAxisAngle4d.angle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(AxisAngle4f paramAxisAngle4f) {
/* 183 */     this.x = paramAxisAngle4f.x;
/* 184 */     this.y = paramAxisAngle4f.y;
/* 185 */     this.z = paramAxisAngle4f.z;
/* 186 */     this.angle = paramAxisAngle4f.angle;
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
/*     */   public final void set(Vector3d paramVector3d, double paramDouble) {
/* 199 */     this.x = paramVector3d.x;
/* 200 */     this.y = paramVector3d.y;
/* 201 */     this.z = paramVector3d.z;
/* 202 */     this.angle = paramDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(double[] paramArrayOfDouble) {
/* 213 */     paramArrayOfDouble[0] = this.x;
/* 214 */     paramArrayOfDouble[1] = this.y;
/* 215 */     paramArrayOfDouble[2] = this.z;
/* 216 */     paramArrayOfDouble[3] = this.angle;
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
/*     */   public final void set(Matrix4f paramMatrix4f) {
/* 230 */     Matrix3d matrix3d = new Matrix3d();
/*     */     
/* 232 */     paramMatrix4f.get(matrix3d);
/*     */     
/* 234 */     this.x = (float)(matrix3d.m21 - matrix3d.m12);
/* 235 */     this.y = (float)(matrix3d.m02 - matrix3d.m20);
/* 236 */     this.z = (float)(matrix3d.m10 - matrix3d.m01);
/* 237 */     double d = this.x * this.x + this.y * this.y + this.z * this.z;
/*     */     
/* 239 */     if (d > 1.0E-6D) {
/* 240 */       d = Math.sqrt(d);
/* 241 */       double d1 = 0.5D * d;
/* 242 */       double d2 = 0.5D * (matrix3d.m00 + matrix3d.m11 + matrix3d.m22 - 1.0D);
/*     */       
/* 244 */       this.angle = (float)Math.atan2(d1, d2);
/*     */       
/* 246 */       double d3 = 1.0D / d;
/* 247 */       this.x *= d3;
/* 248 */       this.y *= d3;
/* 249 */       this.z *= d3;
/*     */     } else {
/* 251 */       this.x = 0.0D;
/* 252 */       this.y = 1.0D;
/* 253 */       this.z = 0.0D;
/* 254 */       this.angle = 0.0D;
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
/*     */   public final void set(Matrix4d paramMatrix4d) {
/* 269 */     Matrix3d matrix3d = new Matrix3d();
/*     */     
/* 271 */     paramMatrix4d.get(matrix3d);
/*     */     
/* 273 */     this.x = (float)(matrix3d.m21 - matrix3d.m12);
/* 274 */     this.y = (float)(matrix3d.m02 - matrix3d.m20);
/* 275 */     this.z = (float)(matrix3d.m10 - matrix3d.m01);
/*     */     
/* 277 */     double d = this.x * this.x + this.y * this.y + this.z * this.z;
/*     */     
/* 279 */     if (d > 1.0E-6D) {
/* 280 */       d = Math.sqrt(d);
/*     */       
/* 282 */       double d1 = 0.5D * d;
/* 283 */       double d2 = 0.5D * (matrix3d.m00 + matrix3d.m11 + matrix3d.m22 - 1.0D);
/* 284 */       this.angle = (float)Math.atan2(d1, d2);
/*     */       
/* 286 */       double d3 = 1.0D / d;
/* 287 */       this.x *= d3;
/* 288 */       this.y *= d3;
/* 289 */       this.z *= d3;
/*     */     } else {
/* 291 */       this.x = 0.0D;
/* 292 */       this.y = 1.0D;
/* 293 */       this.z = 0.0D;
/* 294 */       this.angle = 0.0D;
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
/*     */   public final void set(Matrix3f paramMatrix3f) {
/* 308 */     this.x = (paramMatrix3f.m21 - paramMatrix3f.m12);
/* 309 */     this.y = (paramMatrix3f.m02 - paramMatrix3f.m20);
/* 310 */     this.z = (paramMatrix3f.m10 - paramMatrix3f.m01);
/* 311 */     double d = this.x * this.x + this.y * this.y + this.z * this.z;
/*     */     
/* 313 */     if (d > 1.0E-6D) {
/* 314 */       d = Math.sqrt(d);
/*     */       
/* 316 */       double d1 = 0.5D * d;
/* 317 */       double d2 = 0.5D * ((paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22) - 1.0D);
/* 318 */       this.angle = (float)Math.atan2(d1, d2);
/*     */       
/* 320 */       double d3 = 1.0D / d;
/* 321 */       this.x *= d3;
/* 322 */       this.y *= d3;
/* 323 */       this.z *= d3;
/*     */     } else {
/* 325 */       this.x = 0.0D;
/* 326 */       this.y = 1.0D;
/* 327 */       this.z = 0.0D;
/* 328 */       this.angle = 0.0D;
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
/*     */   public final void set(Matrix3d paramMatrix3d) {
/* 342 */     this.x = (float)(paramMatrix3d.m21 - paramMatrix3d.m12);
/* 343 */     this.y = (float)(paramMatrix3d.m02 - paramMatrix3d.m20);
/* 344 */     this.z = (float)(paramMatrix3d.m10 - paramMatrix3d.m01);
/*     */     
/* 346 */     double d = this.x * this.x + this.y * this.y + this.z * this.z;
/*     */     
/* 348 */     if (d > 1.0E-6D) {
/* 349 */       d = Math.sqrt(d);
/*     */       
/* 351 */       double d1 = 0.5D * d;
/* 352 */       double d2 = 0.5D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 - 1.0D);
/*     */       
/* 354 */       this.angle = (float)Math.atan2(d1, d2);
/*     */       
/* 356 */       double d3 = 1.0D / d;
/* 357 */       this.x *= d3;
/* 358 */       this.y *= d3;
/* 359 */       this.z *= d3;
/*     */     } else {
/* 361 */       this.x = 0.0D;
/* 362 */       this.y = 1.0D;
/* 363 */       this.z = 0.0D;
/* 364 */       this.angle = 0.0D;
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
/*     */   public final void set(Quat4f paramQuat4f) {
/* 380 */     double d = (paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z);
/*     */     
/* 382 */     if (d > 1.0E-6D) {
/* 383 */       d = Math.sqrt(d);
/* 384 */       double d1 = 1.0D / d;
/*     */       
/* 386 */       this.x = paramQuat4f.x * d1;
/* 387 */       this.y = paramQuat4f.y * d1;
/* 388 */       this.z = paramQuat4f.z * d1;
/* 389 */       this.angle = 2.0D * Math.atan2(d, paramQuat4f.w);
/*     */     } else {
/* 391 */       this.x = 0.0D;
/* 392 */       this.y = 1.0D;
/* 393 */       this.z = 0.0D;
/* 394 */       this.angle = 0.0D;
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
/* 408 */     double d = paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z;
/*     */     
/* 410 */     if (d > 1.0E-6D) {
/* 411 */       d = Math.sqrt(d);
/* 412 */       double d1 = 1.0D / d;
/*     */       
/* 414 */       this.x = paramQuat4d.x * d1;
/* 415 */       this.y = paramQuat4d.y * d1;
/* 416 */       this.z = paramQuat4d.z * d1;
/* 417 */       this.angle = 2.0D * Math.atan2(d, paramQuat4d.w);
/*     */     } else {
/* 419 */       this.x = 0.0D;
/* 420 */       this.y = 1.0D;
/* 421 */       this.z = 0.0D;
/* 422 */       this.angle = 0.0D;
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
/* 433 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(AxisAngle4d paramAxisAngle4d) {
/*     */     try {
/* 446 */       return (this.x == paramAxisAngle4d.x && this.y == paramAxisAngle4d.y && this.z == paramAxisAngle4d.z && this.angle == paramAxisAngle4d.angle);
/*     */     } catch (NullPointerException nullPointerException) {
/*     */       
/* 449 */       return false;
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
/*     */   public boolean equals(Object paramObject) {
/*     */     
/* 462 */     try { AxisAngle4d axisAngle4d = (AxisAngle4d)paramObject;
/* 463 */       return (this.x == axisAngle4d.x && this.y == axisAngle4d.y && this.z == axisAngle4d.z && this.angle == axisAngle4d.angle); }
/*     */     catch (NullPointerException nullPointerException)
/*     */     
/* 466 */     { return false; }
/* 467 */     catch (ClassCastException classCastException) { return false; }
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
/*     */   
/*     */   public boolean epsilonEquals(AxisAngle4d paramAxisAngle4d, double paramDouble) {
/* 485 */     double d = this.x - paramAxisAngle4d.x;
/* 486 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 488 */     d = this.y - paramAxisAngle4d.y;
/* 489 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 491 */     d = this.z - paramAxisAngle4d.z;
/* 492 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 494 */     d = this.angle - paramAxisAngle4d.angle;
/* 495 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 497 */     return true;
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
/*     */   public int hashCode() {
/* 510 */     long l = 1L;
/* 511 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
/* 512 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
/* 513 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.z);
/* 514 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.angle);
/* 515 */     return (int)(l ^ l >> 32);
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
/* 529 */       return super.clone();
/* 530 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 532 */       throw new InternalError();
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
/* 546 */   public final double getAngle() { return this.angle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 559 */   public final void setAngle(double paramDouble) { this.angle = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 571 */   public double getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 583 */   public final void setX(double paramDouble) { this.x = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 595 */   public final double getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 607 */   public final void setY(double paramDouble) { this.y = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 619 */   public double getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 631 */   public final void setZ(double paramDouble) { this.z = paramDouble; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\AxisAngle4d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */