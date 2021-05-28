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
/*     */ public abstract class Tuple4f
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = 7068460319248845763L;
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */   public float w;
/*     */   
/*     */   public Tuple4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  56 */     this.x = paramFloat1;
/*  57 */     this.y = paramFloat2;
/*  58 */     this.z = paramFloat3;
/*  59 */     this.w = paramFloat4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4f(float[] paramArrayOfFloat) {
/*  69 */     this.x = paramArrayOfFloat[0];
/*  70 */     this.y = paramArrayOfFloat[1];
/*  71 */     this.z = paramArrayOfFloat[2];
/*  72 */     this.w = paramArrayOfFloat[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4f(Tuple4f paramTuple4f) {
/*  82 */     this.x = paramTuple4f.x;
/*  83 */     this.y = paramTuple4f.y;
/*  84 */     this.z = paramTuple4f.z;
/*  85 */     this.w = paramTuple4f.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4f(Tuple4d paramTuple4d) {
/*  95 */     this.x = (float)paramTuple4d.x;
/*  96 */     this.y = (float)paramTuple4d.y;
/*  97 */     this.z = (float)paramTuple4d.z;
/*  98 */     this.w = (float)paramTuple4d.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4f() {
/* 107 */     this.x = 0.0F;
/* 108 */     this.y = 0.0F;
/* 109 */     this.z = 0.0F;
/* 110 */     this.w = 0.0F;
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
/* 123 */     this.x = paramFloat1;
/* 124 */     this.y = paramFloat2;
/* 125 */     this.z = paramFloat3;
/* 126 */     this.w = paramFloat4;
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
/* 137 */     this.x = paramArrayOfFloat[0];
/* 138 */     this.y = paramArrayOfFloat[1];
/* 139 */     this.z = paramArrayOfFloat[2];
/* 140 */     this.w = paramArrayOfFloat[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4f paramTuple4f) {
/* 150 */     this.x = paramTuple4f.x;
/* 151 */     this.y = paramTuple4f.y;
/* 152 */     this.z = paramTuple4f.z;
/* 153 */     this.w = paramTuple4f.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4d paramTuple4d) {
/* 163 */     this.x = (float)paramTuple4d.x;
/* 164 */     this.y = (float)paramTuple4d.y;
/* 165 */     this.z = (float)paramTuple4d.z;
/* 166 */     this.w = (float)paramTuple4d.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(float[] paramArrayOfFloat) {
/* 176 */     paramArrayOfFloat[0] = this.x;
/* 177 */     paramArrayOfFloat[1] = this.y;
/* 178 */     paramArrayOfFloat[2] = this.z;
/* 179 */     paramArrayOfFloat[3] = this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple4f paramTuple4f) {
/* 189 */     paramTuple4f.x = this.x;
/* 190 */     paramTuple4f.y = this.y;
/* 191 */     paramTuple4f.z = this.z;
/* 192 */     paramTuple4f.w = this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
/* 203 */     paramTuple4f1.x += paramTuple4f2.x;
/* 204 */     paramTuple4f1.y += paramTuple4f2.y;
/* 205 */     paramTuple4f1.z += paramTuple4f2.z;
/* 206 */     paramTuple4f1.w += paramTuple4f2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple4f paramTuple4f) {
/* 216 */     this.x += paramTuple4f.x;
/* 217 */     this.y += paramTuple4f.y;
/* 218 */     this.z += paramTuple4f.z;
/* 219 */     this.w += paramTuple4f.w;
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
/*     */   public final void sub(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
/* 231 */     paramTuple4f1.x -= paramTuple4f2.x;
/* 232 */     paramTuple4f1.y -= paramTuple4f2.y;
/* 233 */     paramTuple4f1.z -= paramTuple4f2.z;
/* 234 */     paramTuple4f1.w -= paramTuple4f2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple4f paramTuple4f) {
/* 245 */     this.x -= paramTuple4f.x;
/* 246 */     this.y -= paramTuple4f.y;
/* 247 */     this.z -= paramTuple4f.z;
/* 248 */     this.w -= paramTuple4f.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple4f paramTuple4f) {
/* 258 */     this.x = -paramTuple4f.x;
/* 259 */     this.y = -paramTuple4f.y;
/* 260 */     this.z = -paramTuple4f.z;
/* 261 */     this.w = -paramTuple4f.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 270 */     this.x = -this.x;
/* 271 */     this.y = -this.y;
/* 272 */     this.z = -this.z;
/* 273 */     this.w = -this.w;
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
/*     */   public final void scale(float paramFloat, Tuple4f paramTuple4f) {
/* 285 */     this.x = paramFloat * paramTuple4f.x;
/* 286 */     this.y = paramFloat * paramTuple4f.y;
/* 287 */     this.z = paramFloat * paramTuple4f.z;
/* 288 */     this.w = paramFloat * paramTuple4f.w;
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
/* 299 */     this.x *= paramFloat;
/* 300 */     this.y *= paramFloat;
/* 301 */     this.z *= paramFloat;
/* 302 */     this.w *= paramFloat;
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
/*     */   public final void scaleAdd(float paramFloat, Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
/* 315 */     this.x = paramFloat * paramTuple4f1.x + paramTuple4f2.x;
/* 316 */     this.y = paramFloat * paramTuple4f1.y + paramTuple4f2.y;
/* 317 */     this.z = paramFloat * paramTuple4f1.z + paramTuple4f2.z;
/* 318 */     this.w = paramFloat * paramTuple4f1.w + paramTuple4f2.w;
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
/*     */   public final void scaleAdd(float paramFloat, Tuple4f paramTuple4f) {
/* 330 */     this.x = paramFloat * this.x + paramTuple4f.x;
/* 331 */     this.y = paramFloat * this.y + paramTuple4f.y;
/* 332 */     this.z = paramFloat * this.z + paramTuple4f.z;
/* 333 */     this.w = paramFloat * this.w + paramTuple4f.w;
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
/* 344 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Tuple4f paramTuple4f) {
/*     */     try {
/* 356 */       return (this.x == paramTuple4f.x && this.y == paramTuple4f.y && this.z == paramTuple4f.z && this.w == paramTuple4f.w);
/*     */     } catch (NullPointerException nullPointerException) {
/*     */       
/* 359 */       return false;
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
/* 372 */     try { Tuple4f tuple4f = (Tuple4f)paramObject;
/* 373 */       return (this.x == tuple4f.x && this.y == tuple4f.y && this.z == tuple4f.z && this.w == tuple4f.w); }
/*     */     catch (NullPointerException nullPointerException)
/*     */     
/* 376 */     { return false; }
/* 377 */     catch (ClassCastException classCastException) { return false; }
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
/*     */   public boolean epsilonEquals(Tuple4f paramTuple4f, float paramFloat) {
/* 395 */     float f = this.x - paramTuple4f.x;
/* 396 */     if (Float.isNaN(f)) return false; 
/* 397 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 399 */     f = this.y - paramTuple4f.y;
/* 400 */     if (Float.isNaN(f)) return false; 
/* 401 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 403 */     f = this.z - paramTuple4f.z;
/* 404 */     if (Float.isNaN(f)) return false; 
/* 405 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 407 */     f = this.w - paramTuple4f.w;
/* 408 */     if (Float.isNaN(f)) return false; 
/* 409 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 411 */     return true;
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
/* 424 */     long l = 1L;
/* 425 */     l = 31L * l + VecMathUtil.floatToIntBits(this.x);
/* 426 */     l = 31L * l + VecMathUtil.floatToIntBits(this.y);
/* 427 */     l = 31L * l + VecMathUtil.floatToIntBits(this.z);
/* 428 */     l = 31L * l + VecMathUtil.floatToIntBits(this.w);
/* 429 */     return (int)(l ^ l >> 32);
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
/*     */   public final void clamp(float paramFloat1, float paramFloat2, Tuple4f paramTuple4f) {
/* 442 */     if (paramTuple4f.x > paramFloat2) {
/* 443 */       this.x = paramFloat2;
/* 444 */     } else if (paramTuple4f.x < paramFloat1) {
/* 445 */       this.x = paramFloat1;
/*     */     } else {
/* 447 */       this.x = paramTuple4f.x;
/*     */     } 
/*     */     
/* 450 */     if (paramTuple4f.y > paramFloat2) {
/* 451 */       this.y = paramFloat2;
/* 452 */     } else if (paramTuple4f.y < paramFloat1) {
/* 453 */       this.y = paramFloat1;
/*     */     } else {
/* 455 */       this.y = paramTuple4f.y;
/*     */     } 
/*     */     
/* 458 */     if (paramTuple4f.z > paramFloat2) {
/* 459 */       this.z = paramFloat2;
/* 460 */     } else if (paramTuple4f.z < paramFloat1) {
/* 461 */       this.z = paramFloat1;
/*     */     } else {
/* 463 */       this.z = paramTuple4f.z;
/*     */     } 
/*     */     
/* 466 */     if (paramTuple4f.w > paramFloat2) {
/* 467 */       this.w = paramFloat2;
/* 468 */     } else if (paramTuple4f.w < paramFloat1) {
/* 469 */       this.w = paramFloat1;
/*     */     } else {
/* 471 */       this.w = paramTuple4f.w;
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
/*     */   public final void clampMin(float paramFloat, Tuple4f paramTuple4f) {
/* 485 */     if (paramTuple4f.x < paramFloat) {
/* 486 */       this.x = paramFloat;
/*     */     } else {
/* 488 */       this.x = paramTuple4f.x;
/*     */     } 
/*     */     
/* 491 */     if (paramTuple4f.y < paramFloat) {
/* 492 */       this.y = paramFloat;
/*     */     } else {
/* 494 */       this.y = paramTuple4f.y;
/*     */     } 
/*     */     
/* 497 */     if (paramTuple4f.z < paramFloat) {
/* 498 */       this.z = paramFloat;
/*     */     } else {
/* 500 */       this.z = paramTuple4f.z;
/*     */     } 
/*     */     
/* 503 */     if (paramTuple4f.w < paramFloat) {
/* 504 */       this.w = paramFloat;
/*     */     } else {
/* 506 */       this.w = paramTuple4f.w;
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
/*     */   public final void clampMax(float paramFloat, Tuple4f paramTuple4f) {
/* 521 */     if (paramTuple4f.x > paramFloat) {
/* 522 */       this.x = paramFloat;
/*     */     } else {
/* 524 */       this.x = paramTuple4f.x;
/*     */     } 
/*     */     
/* 527 */     if (paramTuple4f.y > paramFloat) {
/* 528 */       this.y = paramFloat;
/*     */     } else {
/* 530 */       this.y = paramTuple4f.y;
/*     */     } 
/*     */     
/* 533 */     if (paramTuple4f.z > paramFloat) {
/* 534 */       this.z = paramFloat;
/*     */     } else {
/* 536 */       this.z = paramTuple4f.z;
/*     */     } 
/*     */     
/* 539 */     if (paramTuple4f.w > paramFloat) {
/* 540 */       this.w = paramFloat;
/*     */     } else {
/* 542 */       this.w = paramTuple4f.z;
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
/*     */   public final void absolute(Tuple4f paramTuple4f) {
/* 555 */     this.x = Math.abs(paramTuple4f.x);
/* 556 */     this.y = Math.abs(paramTuple4f.y);
/* 557 */     this.z = Math.abs(paramTuple4f.z);
/* 558 */     this.w = Math.abs(paramTuple4f.w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(float paramFloat1, float paramFloat2) {
/* 569 */     if (this.x > paramFloat2) {
/* 570 */       this.x = paramFloat2;
/* 571 */     } else if (this.x < paramFloat1) {
/* 572 */       this.x = paramFloat1;
/*     */     } 
/*     */     
/* 575 */     if (this.y > paramFloat2) {
/* 576 */       this.y = paramFloat2;
/* 577 */     } else if (this.y < paramFloat1) {
/* 578 */       this.y = paramFloat1;
/*     */     } 
/*     */     
/* 581 */     if (this.z > paramFloat2) {
/* 582 */       this.z = paramFloat2;
/* 583 */     } else if (this.z < paramFloat1) {
/* 584 */       this.z = paramFloat1;
/*     */     } 
/*     */     
/* 587 */     if (this.w > paramFloat2) {
/* 588 */       this.w = paramFloat2;
/* 589 */     } else if (this.w < paramFloat1) {
/* 590 */       this.w = paramFloat1;
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
/* 602 */     if (this.x < paramFloat) this.x = paramFloat; 
/* 603 */     if (this.y < paramFloat) this.y = paramFloat; 
/* 604 */     if (this.z < paramFloat) this.z = paramFloat; 
/* 605 */     if (this.w < paramFloat) this.w = paramFloat;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(float paramFloat) {
/* 616 */     if (this.x > paramFloat) this.x = paramFloat; 
/* 617 */     if (this.y > paramFloat) this.y = paramFloat; 
/* 618 */     if (this.z > paramFloat) this.z = paramFloat; 
/* 619 */     if (this.w > paramFloat) this.w = paramFloat;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 629 */     this.x = Math.abs(this.x);
/* 630 */     this.y = Math.abs(this.y);
/* 631 */     this.z = Math.abs(this.z);
/* 632 */     this.w = Math.abs(this.w);
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
/*     */   public void interpolate(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2, float paramFloat) {
/* 645 */     this.x = (1.0F - paramFloat) * paramTuple4f1.x + paramFloat * paramTuple4f2.x;
/* 646 */     this.y = (1.0F - paramFloat) * paramTuple4f1.y + paramFloat * paramTuple4f2.y;
/* 647 */     this.z = (1.0F - paramFloat) * paramTuple4f1.z + paramFloat * paramTuple4f2.z;
/* 648 */     this.w = (1.0F - paramFloat) * paramTuple4f1.w + paramFloat * paramTuple4f2.w;
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
/*     */   public void interpolate(Tuple4f paramTuple4f, float paramFloat) {
/* 661 */     this.x = (1.0F - paramFloat) * this.x + paramFloat * paramTuple4f.x;
/* 662 */     this.y = (1.0F - paramFloat) * this.y + paramFloat * paramTuple4f.y;
/* 663 */     this.z = (1.0F - paramFloat) * this.z + paramFloat * paramTuple4f.z;
/* 664 */     this.w = (1.0F - paramFloat) * this.w + paramFloat * paramTuple4f.w;
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
/* 679 */       return super.clone();
/* 680 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 682 */       throw new InternalError();
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
/* 694 */   public final float getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 706 */   public final void setX(float paramFloat) { this.x = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 718 */   public final float getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 730 */   public final void setY(float paramFloat) { this.y = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 741 */   public final float getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 753 */   public final void setZ(float paramFloat) { this.z = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 765 */   public final float getW() { return this.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 777 */   public final void setW(float paramFloat) { this.w = paramFloat; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\vecmath\Tuple4f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */