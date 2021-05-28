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
/*     */ public abstract class Tuple3d
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = 5542096614926168415L;
/*     */   public double x;
/*     */   public double y;
/*     */   public double z;
/*     */   
/*     */   public Tuple3d(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  50 */     this.x = paramDouble1;
/*  51 */     this.y = paramDouble2;
/*  52 */     this.z = paramDouble3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3d(double[] paramArrayOfDouble) {
/*  61 */     this.x = paramArrayOfDouble[0];
/*  62 */     this.y = paramArrayOfDouble[1];
/*  63 */     this.z = paramArrayOfDouble[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3d(Tuple3d paramTuple3d) {
/*  72 */     this.x = paramTuple3d.x;
/*  73 */     this.y = paramTuple3d.y;
/*  74 */     this.z = paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3d(Tuple3f paramTuple3f) {
/*  83 */     this.x = paramTuple3f.x;
/*  84 */     this.y = paramTuple3f.y;
/*  85 */     this.z = paramTuple3f.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3d() {
/*  93 */     this.x = 0.0D;
/*  94 */     this.y = 0.0D;
/*  95 */     this.z = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 106 */     this.x = paramDouble1;
/* 107 */     this.y = paramDouble2;
/* 108 */     this.z = paramDouble3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(double[] paramArrayOfDouble) {
/* 118 */     this.x = paramArrayOfDouble[0];
/* 119 */     this.y = paramArrayOfDouble[1];
/* 120 */     this.z = paramArrayOfDouble[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3d paramTuple3d) {
/* 129 */     this.x = paramTuple3d.x;
/* 130 */     this.y = paramTuple3d.y;
/* 131 */     this.z = paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3f paramTuple3f) {
/* 140 */     this.x = paramTuple3f.x;
/* 141 */     this.y = paramTuple3f.y;
/* 142 */     this.z = paramTuple3f.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(double[] paramArrayOfDouble) {
/* 152 */     paramArrayOfDouble[0] = this.x;
/* 153 */     paramArrayOfDouble[1] = this.y;
/* 154 */     paramArrayOfDouble[2] = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple3d paramTuple3d) {
/* 164 */     paramTuple3d.x = this.x;
/* 165 */     paramTuple3d.y = this.y;
/* 166 */     paramTuple3d.z = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2) {
/* 177 */     paramTuple3d1.x += paramTuple3d2.x;
/* 178 */     paramTuple3d1.y += paramTuple3d2.y;
/* 179 */     paramTuple3d1.z += paramTuple3d2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple3d paramTuple3d) {
/* 189 */     this.x += paramTuple3d.x;
/* 190 */     this.y += paramTuple3d.y;
/* 191 */     this.z += paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2) {
/* 202 */     paramTuple3d1.x -= paramTuple3d2.x;
/* 203 */     paramTuple3d1.y -= paramTuple3d2.y;
/* 204 */     paramTuple3d1.z -= paramTuple3d2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple3d paramTuple3d) {
/* 214 */     this.x -= paramTuple3d.x;
/* 215 */     this.y -= paramTuple3d.y;
/* 216 */     this.z -= paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple3d paramTuple3d) {
/* 226 */     this.x = -paramTuple3d.x;
/* 227 */     this.y = -paramTuple3d.y;
/* 228 */     this.z = -paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 237 */     this.x = -this.x;
/* 238 */     this.y = -this.y;
/* 239 */     this.z = -this.z;
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
/*     */   public final void scale(double paramDouble, Tuple3d paramTuple3d) {
/* 251 */     this.x = paramDouble * paramTuple3d.x;
/* 252 */     this.y = paramDouble * paramTuple3d.y;
/* 253 */     this.z = paramDouble * paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(double paramDouble) {
/* 264 */     this.x *= paramDouble;
/* 265 */     this.y *= paramDouble;
/* 266 */     this.z *= paramDouble;
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
/*     */   public final void scaleAdd(double paramDouble, Tuple3d paramTuple3d1, Tuple3d paramTuple3d2) {
/* 279 */     this.x = paramDouble * paramTuple3d1.x + paramTuple3d2.x;
/* 280 */     this.y = paramDouble * paramTuple3d1.y + paramTuple3d2.y;
/* 281 */     this.z = paramDouble * paramTuple3d1.z + paramTuple3d2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public final void scaleAdd(double paramDouble, Tuple3f paramTuple3f) { scaleAdd(paramDouble, new Point3d(paramTuple3f)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scaleAdd(double paramDouble, Tuple3d paramTuple3d) {
/* 300 */     this.x = paramDouble * this.x + paramTuple3d.x;
/* 301 */     this.y = paramDouble * this.y + paramTuple3d.y;
/* 302 */     this.z = paramDouble * this.z + paramTuple3d.z;
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
/* 313 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ")"; }
/*     */ 
/*     */ 
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
/* 326 */     long l = 1L;
/* 327 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
/* 328 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
/* 329 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.z);
/* 330 */     return (int)(l ^ l >> 32);
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
/*     */   public boolean equals(Tuple3d paramTuple3d) {
/*     */     try {
/* 343 */       return (this.x == paramTuple3d.x && this.y == paramTuple3d.y && this.z == paramTuple3d.z);
/*     */     } catch (NullPointerException nullPointerException) {
/* 345 */       return false;
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
/* 358 */     try { Tuple3d tuple3d = (Tuple3d)paramObject;
/* 359 */       return (this.x == tuple3d.x && this.y == tuple3d.y && this.z == tuple3d.z); }
/*     */     catch (ClassCastException classCastException)
/* 361 */     { return false; }
/* 362 */     catch (NullPointerException nullPointerException) { return false; }
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
/*     */   public boolean epsilonEquals(Tuple3d paramTuple3d, double paramDouble) {
/* 379 */     double d = this.x - paramTuple3d.x;
/* 380 */     if (Double.isNaN(d)) return false; 
/* 381 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 383 */     d = this.y - paramTuple3d.y;
/* 384 */     if (Double.isNaN(d)) return false; 
/* 385 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 387 */     d = this.z - paramTuple3d.z;
/* 388 */     if (Double.isNaN(d)) return false; 
/* 389 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 391 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 400 */   public final void clamp(float paramFloat1, float paramFloat2, Tuple3d paramTuple3d) { clamp(paramFloat1, paramFloat2, paramTuple3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(double paramDouble1, double paramDouble2, Tuple3d paramTuple3d) {
/* 412 */     if (paramTuple3d.x > paramDouble2) {
/* 413 */       this.x = paramDouble2;
/* 414 */     } else if (paramTuple3d.x < paramDouble1) {
/* 415 */       this.x = paramDouble1;
/*     */     } else {
/* 417 */       this.x = paramTuple3d.x;
/*     */     } 
/*     */     
/* 420 */     if (paramTuple3d.y > paramDouble2) {
/* 421 */       this.y = paramDouble2;
/* 422 */     } else if (paramTuple3d.y < paramDouble1) {
/* 423 */       this.y = paramDouble1;
/*     */     } else {
/* 425 */       this.y = paramTuple3d.y;
/*     */     } 
/*     */     
/* 428 */     if (paramTuple3d.z > paramDouble2) {
/* 429 */       this.z = paramDouble2;
/* 430 */     } else if (paramTuple3d.z < paramDouble1) {
/* 431 */       this.z = paramDouble1;
/*     */     } else {
/* 433 */       this.z = paramTuple3d.z;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 443 */   public final void clampMin(float paramFloat, Tuple3d paramTuple3d) { clampMin(paramFloat, paramTuple3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMin(double paramDouble, Tuple3d paramTuple3d) {
/* 454 */     if (paramTuple3d.x < paramDouble) {
/* 455 */       this.x = paramDouble;
/*     */     } else {
/* 457 */       this.x = paramTuple3d.x;
/*     */     } 
/*     */     
/* 460 */     if (paramTuple3d.y < paramDouble) {
/* 461 */       this.y = paramDouble;
/*     */     } else {
/* 463 */       this.y = paramTuple3d.y;
/*     */     } 
/*     */     
/* 466 */     if (paramTuple3d.z < paramDouble) {
/* 467 */       this.z = paramDouble;
/*     */     } else {
/* 469 */       this.z = paramTuple3d.z;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 479 */   public final void clampMax(float paramFloat, Tuple3d paramTuple3d) { clampMax(paramFloat, paramTuple3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(double paramDouble, Tuple3d paramTuple3d) {
/* 490 */     if (paramTuple3d.x > paramDouble) {
/* 491 */       this.x = paramDouble;
/*     */     } else {
/* 493 */       this.x = paramTuple3d.x;
/*     */     } 
/*     */     
/* 496 */     if (paramTuple3d.y > paramDouble) {
/* 497 */       this.y = paramDouble;
/*     */     } else {
/* 499 */       this.y = paramTuple3d.y;
/*     */     } 
/*     */     
/* 502 */     if (paramTuple3d.z > paramDouble) {
/* 503 */       this.z = paramDouble;
/*     */     } else {
/* 505 */       this.z = paramTuple3d.z;
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
/*     */   public final void absolute(Tuple3d paramTuple3d) {
/* 518 */     this.x = Math.abs(paramTuple3d.x);
/* 519 */     this.y = Math.abs(paramTuple3d.y);
/* 520 */     this.z = Math.abs(paramTuple3d.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 529 */   public final void clamp(float paramFloat1, float paramFloat2) { clamp(paramFloat1, paramFloat2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(double paramDouble1, double paramDouble2) {
/* 539 */     if (this.x > paramDouble2) {
/* 540 */       this.x = paramDouble2;
/* 541 */     } else if (this.x < paramDouble1) {
/* 542 */       this.x = paramDouble1;
/*     */     } 
/*     */     
/* 545 */     if (this.y > paramDouble2) {
/* 546 */       this.y = paramDouble2;
/* 547 */     } else if (this.y < paramDouble1) {
/* 548 */       this.y = paramDouble1;
/*     */     } 
/*     */     
/* 551 */     if (this.z > paramDouble2) {
/* 552 */       this.z = paramDouble2;
/* 553 */     } else if (this.z < paramDouble1) {
/* 554 */       this.z = paramDouble1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 564 */   public final void clampMin(float paramFloat) { clampMin(paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMin(double paramDouble) {
/* 573 */     if (this.x < paramDouble) this.x = paramDouble; 
/* 574 */     if (this.y < paramDouble) this.y = paramDouble; 
/* 575 */     if (this.z < paramDouble) this.z = paramDouble;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 584 */   public final void clampMax(float paramFloat) { clampMax(paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(double paramDouble) {
/* 593 */     if (this.x > paramDouble) this.x = paramDouble; 
/* 594 */     if (this.y > paramDouble) this.y = paramDouble; 
/* 595 */     if (this.z > paramDouble) this.z = paramDouble;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 604 */     this.x = Math.abs(this.x);
/* 605 */     this.y = Math.abs(this.y);
/* 606 */     this.z = Math.abs(this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 614 */   public final void interpolate(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2, float paramFloat) { interpolate(paramTuple3d1, paramTuple3d2, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void interpolate(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2, double paramDouble) {
/* 626 */     this.x = (1.0D - paramDouble) * paramTuple3d1.x + paramDouble * paramTuple3d2.x;
/* 627 */     this.y = (1.0D - paramDouble) * paramTuple3d1.y + paramDouble * paramTuple3d2.y;
/* 628 */     this.z = (1.0D - paramDouble) * paramTuple3d1.z + paramDouble * paramTuple3d2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 636 */   public final void interpolate(Tuple3d paramTuple3d, float paramFloat) { interpolate(paramTuple3d, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void interpolate(Tuple3d paramTuple3d, double paramDouble) {
/* 647 */     this.x = (1.0D - paramDouble) * this.x + paramDouble * paramTuple3d.x;
/* 648 */     this.y = (1.0D - paramDouble) * this.y + paramDouble * paramTuple3d.y;
/* 649 */     this.z = (1.0D - paramDouble) * this.z + paramDouble * paramTuple3d.z;
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
/* 663 */       return super.clone();
/* 664 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 666 */       throw new InternalError();
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
/* 678 */   public final double getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 690 */   public final void setX(double paramDouble) { this.x = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 702 */   public final double getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 714 */   public final void setY(double paramDouble) { this.y = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 725 */   public final double getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 737 */   public final void setZ(double paramDouble) { this.z = paramDouble; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Tuple3d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */