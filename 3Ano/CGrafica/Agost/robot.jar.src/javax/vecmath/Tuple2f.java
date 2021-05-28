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
/*     */ public abstract class Tuple2f
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = 9011180388985266884L;
/*     */   public float x;
/*     */   public float y;
/*     */   
/*     */   public Tuple2f(float paramFloat1, float paramFloat2) {
/*  44 */     this.x = paramFloat1;
/*  45 */     this.y = paramFloat2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2f(float[] paramArrayOfFloat) {
/*  55 */     this.x = paramArrayOfFloat[0];
/*  56 */     this.y = paramArrayOfFloat[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2f(Tuple2f paramTuple2f) {
/*  66 */     this.x = paramTuple2f.x;
/*  67 */     this.y = paramTuple2f.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2f(Tuple2d paramTuple2d) {
/*  77 */     this.x = (float)paramTuple2d.x;
/*  78 */     this.y = (float)paramTuple2d.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2f() {
/*  87 */     this.x = 0.0F;
/*  88 */     this.y = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(float paramFloat1, float paramFloat2) {
/*  99 */     this.x = paramFloat1;
/* 100 */     this.y = paramFloat2;
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
/* 111 */     this.x = paramArrayOfFloat[0];
/* 112 */     this.y = paramArrayOfFloat[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple2f paramTuple2f) {
/* 122 */     this.x = paramTuple2f.x;
/* 123 */     this.y = paramTuple2f.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple2d paramTuple2d) {
/* 133 */     this.x = (float)paramTuple2d.x;
/* 134 */     this.y = (float)paramTuple2d.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(float[] paramArrayOfFloat) {
/* 144 */     paramArrayOfFloat[0] = this.x;
/* 145 */     paramArrayOfFloat[1] = this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2) {
/* 156 */     paramTuple2f1.x += paramTuple2f2.x;
/* 157 */     paramTuple2f1.y += paramTuple2f2.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple2f paramTuple2f) {
/* 167 */     this.x += paramTuple2f.x;
/* 168 */     this.y += paramTuple2f.y;
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
/*     */   public final void sub(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2) {
/* 180 */     paramTuple2f1.x -= paramTuple2f2.x;
/* 181 */     paramTuple2f1.y -= paramTuple2f2.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple2f paramTuple2f) {
/* 192 */     this.x -= paramTuple2f.x;
/* 193 */     this.y -= paramTuple2f.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple2f paramTuple2f) {
/* 203 */     this.x = -paramTuple2f.x;
/* 204 */     this.y = -paramTuple2f.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 213 */     this.x = -this.x;
/* 214 */     this.y = -this.y;
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
/*     */   public final void scale(float paramFloat, Tuple2f paramTuple2f) {
/* 226 */     this.x = paramFloat * paramTuple2f.x;
/* 227 */     this.y = paramFloat * paramTuple2f.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(float paramFloat) {
/* 238 */     this.x *= paramFloat;
/* 239 */     this.y *= paramFloat;
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
/*     */   public final void scaleAdd(float paramFloat, Tuple2f paramTuple2f1, Tuple2f paramTuple2f2) {
/* 252 */     this.x = paramFloat * paramTuple2f1.x + paramTuple2f2.x;
/* 253 */     this.y = paramFloat * paramTuple2f1.y + paramTuple2f2.y;
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
/*     */   public final void scaleAdd(float paramFloat, Tuple2f paramTuple2f) {
/* 265 */     this.x = paramFloat * this.x + paramTuple2f.x;
/* 266 */     this.y = paramFloat * this.y + paramTuple2f.y;
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
/* 280 */     long l = 1L;
/* 281 */     l = 31L * l + VecMathUtil.floatToIntBits(this.x);
/* 282 */     l = 31L * l + VecMathUtil.floatToIntBits(this.y);
/* 283 */     return (int)(l ^ l >> 32);
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
/*     */   public boolean equals(Tuple2f paramTuple2f) {
/*     */     try {
/* 296 */       return (this.x == paramTuple2f.x && this.y == paramTuple2f.y);
/*     */     } catch (NullPointerException nullPointerException) {
/* 298 */       return false;
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
/* 312 */     try { Tuple2f tuple2f = (Tuple2f)paramObject;
/* 313 */       return (this.x == tuple2f.x && this.y == tuple2f.y); }
/*     */     catch (NullPointerException nullPointerException)
/* 315 */     { return false; }
/* 316 */     catch (ClassCastException classCastException) { return false; }
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
/*     */   public boolean epsilonEquals(Tuple2f paramTuple2f, float paramFloat) {
/* 333 */     float f = this.x - paramTuple2f.x;
/* 334 */     if (Float.isNaN(f)) return false; 
/* 335 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 337 */     f = this.y - paramTuple2f.y;
/* 338 */     if (Float.isNaN(f)) return false; 
/* 339 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 341 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public String toString() { return "(" + this.x + ", " + this.y + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(float paramFloat1, float paramFloat2, Tuple2f paramTuple2f) {
/* 364 */     if (paramTuple2f.x > paramFloat2) {
/* 365 */       this.x = paramFloat2;
/* 366 */     } else if (paramTuple2f.x < paramFloat1) {
/* 367 */       this.x = paramFloat1;
/*     */     } else {
/* 369 */       this.x = paramTuple2f.x;
/*     */     } 
/*     */     
/* 372 */     if (paramTuple2f.y > paramFloat2) {
/* 373 */       this.y = paramFloat2;
/* 374 */     } else if (paramTuple2f.y < paramFloat1) {
/* 375 */       this.y = paramFloat1;
/*     */     } else {
/* 377 */       this.y = paramTuple2f.y;
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
/*     */   public final void clampMin(float paramFloat, Tuple2f paramTuple2f) {
/* 391 */     if (paramTuple2f.x < paramFloat) {
/* 392 */       this.x = paramFloat;
/*     */     } else {
/* 394 */       this.x = paramTuple2f.x;
/*     */     } 
/*     */     
/* 397 */     if (paramTuple2f.y < paramFloat) {
/* 398 */       this.y = paramFloat;
/*     */     } else {
/* 400 */       this.y = paramTuple2f.y;
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
/*     */   public final void clampMax(float paramFloat, Tuple2f paramTuple2f) {
/* 414 */     if (paramTuple2f.x > paramFloat) {
/* 415 */       this.x = paramFloat;
/*     */     } else {
/* 417 */       this.x = paramTuple2f.x;
/*     */     } 
/*     */     
/* 420 */     if (paramTuple2f.y > paramFloat) {
/* 421 */       this.y = paramFloat;
/*     */     } else {
/* 423 */       this.y = paramTuple2f.y;
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
/*     */   public final void absolute(Tuple2f paramTuple2f) {
/* 436 */     this.x = Math.abs(paramTuple2f.x);
/* 437 */     this.y = Math.abs(paramTuple2f.y);
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
/*     */   public final void clamp(float paramFloat1, float paramFloat2) {
/* 449 */     if (this.x > paramFloat2) {
/* 450 */       this.x = paramFloat2;
/* 451 */     } else if (this.x < paramFloat1) {
/* 452 */       this.x = paramFloat1;
/*     */     } 
/*     */     
/* 455 */     if (this.y > paramFloat2) {
/* 456 */       this.y = paramFloat2;
/* 457 */     } else if (this.y < paramFloat1) {
/* 458 */       this.y = paramFloat1;
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
/*     */   public final void clampMin(float paramFloat) {
/* 470 */     if (this.x < paramFloat) this.x = paramFloat; 
/* 471 */     if (this.y < paramFloat) this.y = paramFloat;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(float paramFloat) {
/* 481 */     if (this.x > paramFloat) this.x = paramFloat; 
/* 482 */     if (this.y > paramFloat) this.y = paramFloat;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 491 */     this.x = Math.abs(this.x);
/* 492 */     this.y = Math.abs(this.y);
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
/*     */   public final void interpolate(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2, float paramFloat) {
/* 505 */     this.x = (1.0F - paramFloat) * paramTuple2f1.x + paramFloat * paramTuple2f2.x;
/* 506 */     this.y = (1.0F - paramFloat) * paramTuple2f1.y + paramFloat * paramTuple2f2.y;
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
/*     */   public final void interpolate(Tuple2f paramTuple2f, float paramFloat) {
/* 520 */     this.x = (1.0F - paramFloat) * this.x + paramFloat * paramTuple2f.x;
/* 521 */     this.y = (1.0F - paramFloat) * this.y + paramFloat * paramTuple2f.y;
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
/* 536 */       return super.clone();
/* 537 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 539 */       throw new InternalError();
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
/* 552 */   public final float getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 564 */   public final void setX(float paramFloat) { this.x = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 576 */   public final float getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 588 */   public final void setY(float paramFloat) { this.y = paramFloat; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\vecmath\Tuple2f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */