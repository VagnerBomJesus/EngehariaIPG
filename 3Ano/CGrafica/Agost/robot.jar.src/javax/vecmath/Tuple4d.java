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
/*     */ public abstract class Tuple4d
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -4748953690425311052L;
/*     */   public double x;
/*     */   public double y;
/*     */   public double z;
/*     */   public double w;
/*     */   
/*     */   public Tuple4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/*  56 */     this.x = paramDouble1;
/*  57 */     this.y = paramDouble2;
/*  58 */     this.z = paramDouble3;
/*  59 */     this.w = paramDouble4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4d(double[] paramArrayOfDouble) {
/*  70 */     this.x = paramArrayOfDouble[0];
/*  71 */     this.y = paramArrayOfDouble[1];
/*  72 */     this.z = paramArrayOfDouble[2];
/*  73 */     this.w = paramArrayOfDouble[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4d(Tuple4d paramTuple4d) {
/*  83 */     this.x = paramTuple4d.x;
/*  84 */     this.y = paramTuple4d.y;
/*  85 */     this.z = paramTuple4d.z;
/*  86 */     this.w = paramTuple4d.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4d(Tuple4f paramTuple4f) {
/*  96 */     this.x = paramTuple4f.x;
/*  97 */     this.y = paramTuple4f.y;
/*  98 */     this.z = paramTuple4f.z;
/*  99 */     this.w = paramTuple4f.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4d() {
/* 108 */     this.x = 0.0D;
/* 109 */     this.y = 0.0D;
/* 110 */     this.z = 0.0D;
/* 111 */     this.w = 0.0D;
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
/* 124 */     this.x = paramDouble1;
/* 125 */     this.y = paramDouble2;
/* 126 */     this.z = paramDouble3;
/* 127 */     this.w = paramDouble4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(double[] paramArrayOfDouble) {
/* 137 */     this.x = paramArrayOfDouble[0];
/* 138 */     this.y = paramArrayOfDouble[1];
/* 139 */     this.z = paramArrayOfDouble[2];
/* 140 */     this.w = paramArrayOfDouble[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4d paramTuple4d) {
/* 150 */     this.x = paramTuple4d.x;
/* 151 */     this.y = paramTuple4d.y;
/* 152 */     this.z = paramTuple4d.z;
/* 153 */     this.w = paramTuple4d.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4f paramTuple4f) {
/* 163 */     this.x = paramTuple4f.x;
/* 164 */     this.y = paramTuple4f.y;
/* 165 */     this.z = paramTuple4f.z;
/* 166 */     this.w = paramTuple4f.w;
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
/* 177 */     paramArrayOfDouble[0] = this.x;
/* 178 */     paramArrayOfDouble[1] = this.y;
/* 179 */     paramArrayOfDouble[2] = this.z;
/* 180 */     paramArrayOfDouble[3] = this.w;
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
/*     */   public final void get(Tuple4d paramTuple4d) {
/* 192 */     paramTuple4d.x = this.x;
/* 193 */     paramTuple4d.y = this.y;
/* 194 */     paramTuple4d.z = this.z;
/* 195 */     paramTuple4d.w = this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2) {
/* 206 */     paramTuple4d1.x += paramTuple4d2.x;
/* 207 */     paramTuple4d1.y += paramTuple4d2.y;
/* 208 */     paramTuple4d1.z += paramTuple4d2.z;
/* 209 */     paramTuple4d1.w += paramTuple4d2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple4d paramTuple4d) {
/* 219 */     this.x += paramTuple4d.x;
/* 220 */     this.y += paramTuple4d.y;
/* 221 */     this.z += paramTuple4d.z;
/* 222 */     this.w += paramTuple4d.w;
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
/*     */   public final void sub(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2) {
/* 234 */     paramTuple4d1.x -= paramTuple4d2.x;
/* 235 */     paramTuple4d1.y -= paramTuple4d2.y;
/* 236 */     paramTuple4d1.z -= paramTuple4d2.z;
/* 237 */     paramTuple4d1.w -= paramTuple4d2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple4d paramTuple4d) {
/* 248 */     this.x -= paramTuple4d.x;
/* 249 */     this.y -= paramTuple4d.y;
/* 250 */     this.z -= paramTuple4d.z;
/* 251 */     this.w -= paramTuple4d.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple4d paramTuple4d) {
/* 261 */     this.x = -paramTuple4d.x;
/* 262 */     this.y = -paramTuple4d.y;
/* 263 */     this.z = -paramTuple4d.z;
/* 264 */     this.w = -paramTuple4d.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 273 */     this.x = -this.x;
/* 274 */     this.y = -this.y;
/* 275 */     this.z = -this.z;
/* 276 */     this.w = -this.w;
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
/*     */   public final void scale(double paramDouble, Tuple4d paramTuple4d) {
/* 288 */     this.x = paramDouble * paramTuple4d.x;
/* 289 */     this.y = paramDouble * paramTuple4d.y;
/* 290 */     this.z = paramDouble * paramTuple4d.z;
/* 291 */     this.w = paramDouble * paramTuple4d.w;
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
/* 302 */     this.x *= paramDouble;
/* 303 */     this.y *= paramDouble;
/* 304 */     this.z *= paramDouble;
/* 305 */     this.w *= paramDouble;
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
/*     */   public final void scaleAdd(double paramDouble, Tuple4d paramTuple4d1, Tuple4d paramTuple4d2) {
/* 318 */     this.x = paramDouble * paramTuple4d1.x + paramTuple4d2.x;
/* 319 */     this.y = paramDouble * paramTuple4d1.y + paramTuple4d2.y;
/* 320 */     this.z = paramDouble * paramTuple4d1.z + paramTuple4d2.z;
/* 321 */     this.w = paramDouble * paramTuple4d1.w + paramTuple4d2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public final void scaleAdd(float paramFloat, Tuple4d paramTuple4d) { scaleAdd(paramFloat, paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scaleAdd(double paramDouble, Tuple4d paramTuple4d) {
/* 341 */     this.x = paramDouble * this.x + paramTuple4d.x;
/* 342 */     this.y = paramDouble * this.y + paramTuple4d.y;
/* 343 */     this.z = paramDouble * this.z + paramTuple4d.z;
/* 344 */     this.w = paramDouble * this.w + paramTuple4d.w;
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
/* 355 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Tuple4d paramTuple4d) {
/*     */     try {
/* 368 */       return (this.x == paramTuple4d.x && this.y == paramTuple4d.y && this.z == paramTuple4d.z && this.w == paramTuple4d.w);
/*     */     } catch (NullPointerException nullPointerException) {
/*     */       
/* 371 */       return false;
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
/* 385 */     try { Tuple4d tuple4d = (Tuple4d)paramObject;
/* 386 */       return (this.x == tuple4d.x && this.y == tuple4d.y && this.z == tuple4d.z && this.w == tuple4d.w); }
/*     */     catch (NullPointerException nullPointerException)
/*     */     
/* 389 */     { return false; }
/* 390 */     catch (ClassCastException classCastException) { return false; }
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
/*     */   public boolean epsilonEquals(Tuple4d paramTuple4d, double paramDouble) {
/* 408 */     double d = this.x - paramTuple4d.x;
/* 409 */     if (Double.isNaN(d)) return false; 
/* 410 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 412 */     d = this.y - paramTuple4d.y;
/* 413 */     if (Double.isNaN(d)) return false; 
/* 414 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 416 */     d = this.z - paramTuple4d.z;
/* 417 */     if (Double.isNaN(d)) return false; 
/* 418 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 420 */     d = this.w - paramTuple4d.w;
/* 421 */     if (Double.isNaN(d)) return false; 
/* 422 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 424 */     return true;
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
/* 438 */     long l = 1L;
/* 439 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
/* 440 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
/* 441 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.z);
/* 442 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.w);
/* 443 */     return (int)(l ^ l >> 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public final void clamp(float paramFloat1, float paramFloat2, Tuple4d paramTuple4d) { clamp(paramFloat1, paramFloat2, paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(double paramDouble1, double paramDouble2, Tuple4d paramTuple4d) {
/* 463 */     if (paramTuple4d.x > paramDouble2) {
/* 464 */       this.x = paramDouble2;
/* 465 */     } else if (paramTuple4d.x < paramDouble1) {
/* 466 */       this.x = paramDouble1;
/*     */     } else {
/* 468 */       this.x = paramTuple4d.x;
/*     */     } 
/*     */     
/* 471 */     if (paramTuple4d.y > paramDouble2) {
/* 472 */       this.y = paramDouble2;
/* 473 */     } else if (paramTuple4d.y < paramDouble1) {
/* 474 */       this.y = paramDouble1;
/*     */     } else {
/* 476 */       this.y = paramTuple4d.y;
/*     */     } 
/*     */     
/* 479 */     if (paramTuple4d.z > paramDouble2) {
/* 480 */       this.z = paramDouble2;
/* 481 */     } else if (paramTuple4d.z < paramDouble1) {
/* 482 */       this.z = paramDouble1;
/*     */     } else {
/* 484 */       this.z = paramTuple4d.z;
/*     */     } 
/*     */     
/* 487 */     if (paramTuple4d.w > paramDouble2) {
/* 488 */       this.w = paramDouble2;
/* 489 */     } else if (paramTuple4d.w < paramDouble1) {
/* 490 */       this.w = paramDouble1;
/*     */     } else {
/* 492 */       this.w = paramTuple4d.w;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 502 */   public final void clampMin(float paramFloat, Tuple4d paramTuple4d) { clampMin(paramFloat, paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMin(double paramDouble, Tuple4d paramTuple4d) {
/* 513 */     if (paramTuple4d.x < paramDouble) {
/* 514 */       this.x = paramDouble;
/*     */     } else {
/* 516 */       this.x = paramTuple4d.x;
/*     */     } 
/*     */     
/* 519 */     if (paramTuple4d.y < paramDouble) {
/* 520 */       this.y = paramDouble;
/*     */     } else {
/* 522 */       this.y = paramTuple4d.y;
/*     */     } 
/*     */     
/* 525 */     if (paramTuple4d.z < paramDouble) {
/* 526 */       this.z = paramDouble;
/*     */     } else {
/* 528 */       this.z = paramTuple4d.z;
/*     */     } 
/*     */     
/* 531 */     if (paramTuple4d.w < paramDouble) {
/* 532 */       this.w = paramDouble;
/*     */     } else {
/* 534 */       this.w = paramTuple4d.w;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 544 */   public final void clampMax(float paramFloat, Tuple4d paramTuple4d) { clampMax(paramFloat, paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(double paramDouble, Tuple4d paramTuple4d) {
/* 555 */     if (paramTuple4d.x > paramDouble) {
/* 556 */       this.x = paramDouble;
/*     */     } else {
/* 558 */       this.x = paramTuple4d.x;
/*     */     } 
/*     */     
/* 561 */     if (paramTuple4d.y > paramDouble) {
/* 562 */       this.y = paramDouble;
/*     */     } else {
/* 564 */       this.y = paramTuple4d.y;
/*     */     } 
/*     */     
/* 567 */     if (paramTuple4d.z > paramDouble) {
/* 568 */       this.z = paramDouble;
/*     */     } else {
/* 570 */       this.z = paramTuple4d.z;
/*     */     } 
/*     */     
/* 573 */     if (paramTuple4d.w > paramDouble) {
/* 574 */       this.w = paramDouble;
/*     */     } else {
/* 576 */       this.w = paramTuple4d.z;
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
/*     */   public final void absolute(Tuple4d paramTuple4d) {
/* 589 */     this.x = Math.abs(paramTuple4d.x);
/* 590 */     this.y = Math.abs(paramTuple4d.y);
/* 591 */     this.z = Math.abs(paramTuple4d.z);
/* 592 */     this.w = Math.abs(paramTuple4d.w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 602 */   public final void clamp(float paramFloat1, float paramFloat2) { clamp(paramFloat1, paramFloat2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(double paramDouble1, double paramDouble2) {
/* 612 */     if (this.x > paramDouble2) {
/* 613 */       this.x = paramDouble2;
/* 614 */     } else if (this.x < paramDouble1) {
/* 615 */       this.x = paramDouble1;
/*     */     } 
/*     */     
/* 618 */     if (this.y > paramDouble2) {
/* 619 */       this.y = paramDouble2;
/* 620 */     } else if (this.y < paramDouble1) {
/* 621 */       this.y = paramDouble1;
/*     */     } 
/*     */     
/* 624 */     if (this.z > paramDouble2) {
/* 625 */       this.z = paramDouble2;
/* 626 */     } else if (this.z < paramDouble1) {
/* 627 */       this.z = paramDouble1;
/*     */     } 
/*     */     
/* 630 */     if (this.w > paramDouble2) {
/* 631 */       this.w = paramDouble2;
/* 632 */     } else if (this.w < paramDouble1) {
/* 633 */       this.w = paramDouble1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 643 */   public final void clampMin(float paramFloat) { clampMin(paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMin(double paramDouble) {
/* 652 */     if (this.x < paramDouble) this.x = paramDouble; 
/* 653 */     if (this.y < paramDouble) this.y = paramDouble; 
/* 654 */     if (this.z < paramDouble) this.z = paramDouble; 
/* 655 */     if (this.w < paramDouble) this.w = paramDouble;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 663 */   public final void clampMax(float paramFloat) { clampMax(paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(double paramDouble) {
/* 672 */     if (this.x > paramDouble) this.x = paramDouble; 
/* 673 */     if (this.y > paramDouble) this.y = paramDouble; 
/* 674 */     if (this.z > paramDouble) this.z = paramDouble; 
/* 675 */     if (this.w > paramDouble) this.w = paramDouble;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 685 */     this.x = Math.abs(this.x);
/* 686 */     this.y = Math.abs(this.y);
/* 687 */     this.z = Math.abs(this.z);
/* 688 */     this.w = Math.abs(this.w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 697 */   public void interpolate(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2, float paramFloat) { interpolate(paramTuple4d1, paramTuple4d2, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void interpolate(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2, double paramDouble) {
/* 709 */     this.x = (1.0D - paramDouble) * paramTuple4d1.x + paramDouble * paramTuple4d2.x;
/* 710 */     this.y = (1.0D - paramDouble) * paramTuple4d1.y + paramDouble * paramTuple4d2.y;
/* 711 */     this.z = (1.0D - paramDouble) * paramTuple4d1.z + paramDouble * paramTuple4d2.z;
/* 712 */     this.w = (1.0D - paramDouble) * paramTuple4d1.w + paramDouble * paramTuple4d2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 720 */   public void interpolate(Tuple4d paramTuple4d, float paramFloat) { interpolate(paramTuple4d, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void interpolate(Tuple4d paramTuple4d, double paramDouble) {
/* 731 */     this.x = (1.0D - paramDouble) * this.x + paramDouble * paramTuple4d.x;
/* 732 */     this.y = (1.0D - paramDouble) * this.y + paramDouble * paramTuple4d.y;
/* 733 */     this.z = (1.0D - paramDouble) * this.z + paramDouble * paramTuple4d.z;
/* 734 */     this.w = (1.0D - paramDouble) * this.w + paramDouble * paramTuple4d.w;
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
/* 748 */       return super.clone();
/* 749 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 751 */       throw new InternalError();
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
/* 763 */   public final double getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 775 */   public final void setX(double paramDouble) { this.x = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 787 */   public final double getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 799 */   public final void setY(double paramDouble) { this.y = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 810 */   public final double getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 822 */   public final void setZ(double paramDouble) { this.z = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 834 */   public final double getW() { return this.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 846 */   public final void setW(double paramDouble) { this.w = paramDouble; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\vecmath\Tuple4d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */