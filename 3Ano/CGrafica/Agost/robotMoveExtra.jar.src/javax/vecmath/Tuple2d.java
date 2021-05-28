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
/*     */ public abstract class Tuple2d
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = 6205762482756093838L;
/*     */   public double x;
/*     */   public double y;
/*     */   
/*     */   public Tuple2d(double paramDouble1, double paramDouble2) {
/*  44 */     this.x = paramDouble1;
/*  45 */     this.y = paramDouble2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2d(double[] paramArrayOfDouble) {
/*  55 */     this.x = paramArrayOfDouble[0];
/*  56 */     this.y = paramArrayOfDouble[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2d(Tuple2d paramTuple2d) {
/*  66 */     this.x = paramTuple2d.x;
/*  67 */     this.y = paramTuple2d.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2d(Tuple2f paramTuple2f) {
/*  77 */     this.x = paramTuple2f.x;
/*  78 */     this.y = paramTuple2f.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2d() {
/*  86 */     this.x = 0.0D;
/*  87 */     this.y = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(double paramDouble1, double paramDouble2) {
/*  98 */     this.x = paramDouble1;
/*  99 */     this.y = paramDouble2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(double[] paramArrayOfDouble) {
/* 110 */     this.x = paramArrayOfDouble[0];
/* 111 */     this.y = paramArrayOfDouble[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple2d paramTuple2d) {
/* 121 */     this.x = paramTuple2d.x;
/* 122 */     this.y = paramTuple2d.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple2f paramTuple2f) {
/* 132 */     this.x = paramTuple2f.x;
/* 133 */     this.y = paramTuple2f.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(double[] paramArrayOfDouble) {
/* 142 */     paramArrayOfDouble[0] = this.x;
/* 143 */     paramArrayOfDouble[1] = this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2) {
/* 154 */     paramTuple2d1.x += paramTuple2d2.x;
/* 155 */     paramTuple2d1.y += paramTuple2d2.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple2d paramTuple2d) {
/* 165 */     this.x += paramTuple2d.x;
/* 166 */     this.y += paramTuple2d.y;
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
/*     */   public final void sub(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2) {
/* 178 */     paramTuple2d1.x -= paramTuple2d2.x;
/* 179 */     paramTuple2d1.y -= paramTuple2d2.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple2d paramTuple2d) {
/* 190 */     this.x -= paramTuple2d.x;
/* 191 */     this.y -= paramTuple2d.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple2d paramTuple2d) {
/* 201 */     this.x = -paramTuple2d.x;
/* 202 */     this.y = -paramTuple2d.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 211 */     this.x = -this.x;
/* 212 */     this.y = -this.y;
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
/*     */   public final void scale(double paramDouble, Tuple2d paramTuple2d) {
/* 224 */     this.x = paramDouble * paramTuple2d.x;
/* 225 */     this.y = paramDouble * paramTuple2d.y;
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
/* 236 */     this.x *= paramDouble;
/* 237 */     this.y *= paramDouble;
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
/*     */   public final void scaleAdd(double paramDouble, Tuple2d paramTuple2d1, Tuple2d paramTuple2d2) {
/* 250 */     this.x = paramDouble * paramTuple2d1.x + paramTuple2d2.x;
/* 251 */     this.y = paramDouble * paramTuple2d1.y + paramTuple2d2.y;
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
/*     */   public final void scaleAdd(double paramDouble, Tuple2d paramTuple2d) {
/* 263 */     this.x = paramDouble * this.x + paramTuple2d.x;
/* 264 */     this.y = paramDouble * this.y + paramTuple2d.y;
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
/* 278 */     long l = 1L;
/* 279 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
/* 280 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
/* 281 */     return (int)(l ^ l >> 32);
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
/*     */   public boolean equals(Tuple2d paramTuple2d) {
/*     */     try {
/* 294 */       return (this.x == paramTuple2d.x && this.y == paramTuple2d.y);
/*     */     } catch (NullPointerException nullPointerException) {
/* 296 */       return false;
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
/* 310 */     try { Tuple2d tuple2d = (Tuple2d)paramObject;
/* 311 */       return (this.x == tuple2d.x && this.y == tuple2d.y); }
/*     */     catch (NullPointerException nullPointerException)
/* 313 */     { return false; }
/* 314 */     catch (ClassCastException classCastException) { return false; }
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
/*     */   public boolean epsilonEquals(Tuple2d paramTuple2d, double paramDouble) {
/* 331 */     double d = this.x - paramTuple2d.x;
/* 332 */     if (Double.isNaN(d)) return false; 
/* 333 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 335 */     d = this.y - paramTuple2d.y;
/* 336 */     if (Double.isNaN(d)) return false; 
/* 337 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*     */     
/* 339 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public String toString() { return "(" + this.x + ", " + this.y + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(double paramDouble1, double paramDouble2, Tuple2d paramTuple2d) {
/* 362 */     if (paramTuple2d.x > paramDouble2) {
/* 363 */       this.x = paramDouble2;
/* 364 */     } else if (paramTuple2d.x < paramDouble1) {
/* 365 */       this.x = paramDouble1;
/*     */     } else {
/* 367 */       this.x = paramTuple2d.x;
/*     */     } 
/*     */     
/* 370 */     if (paramTuple2d.y > paramDouble2) {
/* 371 */       this.y = paramDouble2;
/* 372 */     } else if (paramTuple2d.y < paramDouble1) {
/* 373 */       this.y = paramDouble1;
/*     */     } else {
/* 375 */       this.y = paramTuple2d.y;
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
/*     */   public final void clampMin(double paramDouble, Tuple2d paramTuple2d) {
/* 389 */     if (paramTuple2d.x < paramDouble) {
/* 390 */       this.x = paramDouble;
/*     */     } else {
/* 392 */       this.x = paramTuple2d.x;
/*     */     } 
/*     */     
/* 395 */     if (paramTuple2d.y < paramDouble) {
/* 396 */       this.y = paramDouble;
/*     */     } else {
/* 398 */       this.y = paramTuple2d.y;
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
/*     */   public final void clampMax(double paramDouble, Tuple2d paramTuple2d) {
/* 412 */     if (paramTuple2d.x > paramDouble) {
/* 413 */       this.x = paramDouble;
/*     */     } else {
/* 415 */       this.x = paramTuple2d.x;
/*     */     } 
/*     */     
/* 418 */     if (paramTuple2d.y > paramDouble) {
/* 419 */       this.y = paramDouble;
/*     */     } else {
/* 421 */       this.y = paramTuple2d.y;
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
/*     */   public final void absolute(Tuple2d paramTuple2d) {
/* 434 */     this.x = Math.abs(paramTuple2d.x);
/* 435 */     this.y = Math.abs(paramTuple2d.y);
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
/*     */   public final void clamp(double paramDouble1, double paramDouble2) {
/* 447 */     if (this.x > paramDouble2) {
/* 448 */       this.x = paramDouble2;
/* 449 */     } else if (this.x < paramDouble1) {
/* 450 */       this.x = paramDouble1;
/*     */     } 
/*     */     
/* 453 */     if (this.y > paramDouble2) {
/* 454 */       this.y = paramDouble2;
/* 455 */     } else if (this.y < paramDouble1) {
/* 456 */       this.y = paramDouble1;
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
/*     */   public final void clampMin(double paramDouble) {
/* 468 */     if (this.x < paramDouble) this.x = paramDouble; 
/* 469 */     if (this.y < paramDouble) this.y = paramDouble;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(double paramDouble) {
/* 479 */     if (this.x > paramDouble) this.x = paramDouble; 
/* 480 */     if (this.y > paramDouble) this.y = paramDouble;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 489 */     this.x = Math.abs(this.x);
/* 490 */     this.y = Math.abs(this.y);
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
/*     */   public final void interpolate(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2, double paramDouble) {
/* 503 */     this.x = (1.0D - paramDouble) * paramTuple2d1.x + paramDouble * paramTuple2d2.x;
/* 504 */     this.y = (1.0D - paramDouble) * paramTuple2d1.y + paramDouble * paramTuple2d2.y;
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
/*     */   public final void interpolate(Tuple2d paramTuple2d, double paramDouble) {
/* 516 */     this.x = (1.0D - paramDouble) * this.x + paramDouble * paramTuple2d.x;
/* 517 */     this.y = (1.0D - paramDouble) * this.y + paramDouble * paramTuple2d.y;
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
/*     */   public Object clone() {
/*     */     try {
/* 532 */       return super.clone();
/* 533 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 535 */       throw new InternalError();
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
/* 548 */   public final double getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 560 */   public final void setX(double paramDouble) { this.x = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 572 */   public final double getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 584 */   public final void setY(double paramDouble) { this.y = paramDouble; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Tuple2d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */