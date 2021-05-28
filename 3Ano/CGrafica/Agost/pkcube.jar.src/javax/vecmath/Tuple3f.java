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
/*     */ public abstract class Tuple3f
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = 5019834619484343712L;
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */   
/*     */   public Tuple3f(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  50 */     this.x = paramFloat1;
/*  51 */     this.y = paramFloat2;
/*  52 */     this.z = paramFloat3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3f(float[] paramArrayOfFloat) {
/*  62 */     this.x = paramArrayOfFloat[0];
/*  63 */     this.y = paramArrayOfFloat[1];
/*  64 */     this.z = paramArrayOfFloat[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3f(Tuple3f paramTuple3f) {
/*  74 */     this.x = paramTuple3f.x;
/*  75 */     this.y = paramTuple3f.y;
/*  76 */     this.z = paramTuple3f.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3f(Tuple3d paramTuple3d) {
/*  86 */     this.x = (float)paramTuple3d.x;
/*  87 */     this.y = (float)paramTuple3d.y;
/*  88 */     this.z = (float)paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3f() {
/*  97 */     this.x = 0.0F;
/*  98 */     this.y = 0.0F;
/*  99 */     this.z = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 121 */     this.x = paramFloat1;
/* 122 */     this.y = paramFloat2;
/* 123 */     this.z = paramFloat3;
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
/* 134 */     this.x = paramArrayOfFloat[0];
/* 135 */     this.y = paramArrayOfFloat[1];
/* 136 */     this.z = paramArrayOfFloat[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3f paramTuple3f) {
/* 146 */     this.x = paramTuple3f.x;
/* 147 */     this.y = paramTuple3f.y;
/* 148 */     this.z = paramTuple3f.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3d paramTuple3d) {
/* 158 */     this.x = (float)paramTuple3d.x;
/* 159 */     this.y = (float)paramTuple3d.y;
/* 160 */     this.z = (float)paramTuple3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(float[] paramArrayOfFloat) {
/* 170 */     paramArrayOfFloat[0] = this.x;
/* 171 */     paramArrayOfFloat[1] = this.y;
/* 172 */     paramArrayOfFloat[2] = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple3f paramTuple3f) {
/* 182 */     paramTuple3f.x = this.x;
/* 183 */     paramTuple3f.y = this.y;
/* 184 */     paramTuple3f.z = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2) {
/* 195 */     paramTuple3f1.x += paramTuple3f2.x;
/* 196 */     paramTuple3f1.y += paramTuple3f2.y;
/* 197 */     paramTuple3f1.z += paramTuple3f2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple3f paramTuple3f) {
/* 207 */     this.x += paramTuple3f.x;
/* 208 */     this.y += paramTuple3f.y;
/* 209 */     this.z += paramTuple3f.z;
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
/*     */   public final void sub(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2) {
/* 221 */     paramTuple3f1.x -= paramTuple3f2.x;
/* 222 */     paramTuple3f1.y -= paramTuple3f2.y;
/* 223 */     paramTuple3f1.z -= paramTuple3f2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple3f paramTuple3f) {
/* 234 */     this.x -= paramTuple3f.x;
/* 235 */     this.y -= paramTuple3f.y;
/* 236 */     this.z -= paramTuple3f.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple3f paramTuple3f) {
/* 246 */     this.x = -paramTuple3f.x;
/* 247 */     this.y = -paramTuple3f.y;
/* 248 */     this.z = -paramTuple3f.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 257 */     this.x = -this.x;
/* 258 */     this.y = -this.y;
/* 259 */     this.z = -this.z;
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
/*     */   public final void scale(float paramFloat, Tuple3f paramTuple3f) {
/* 271 */     this.x = paramFloat * paramTuple3f.x;
/* 272 */     this.y = paramFloat * paramTuple3f.y;
/* 273 */     this.z = paramFloat * paramTuple3f.z;
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
/* 284 */     this.x *= paramFloat;
/* 285 */     this.y *= paramFloat;
/* 286 */     this.z *= paramFloat;
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
/*     */   public final void scaleAdd(float paramFloat, Tuple3f paramTuple3f1, Tuple3f paramTuple3f2) {
/* 299 */     this.x = paramFloat * paramTuple3f1.x + paramTuple3f2.x;
/* 300 */     this.y = paramFloat * paramTuple3f1.y + paramTuple3f2.y;
/* 301 */     this.z = paramFloat * paramTuple3f1.z + paramTuple3f2.z;
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
/*     */   public final void scaleAdd(float paramFloat, Tuple3f paramTuple3f) {
/* 314 */     this.x = paramFloat * this.x + paramTuple3f.x;
/* 315 */     this.y = paramFloat * this.y + paramTuple3f.y;
/* 316 */     this.z = paramFloat * this.z + paramTuple3f.z;
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
/*     */   public boolean equals(Tuple3f paramTuple3f) {
/*     */     try {
/* 330 */       return (this.x == paramTuple3f.x && this.y == paramTuple3f.y && this.z == paramTuple3f.z);
/*     */     } catch (NullPointerException nullPointerException) {
/* 332 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*     */     
/* 344 */     try { Tuple3f tuple3f = (Tuple3f)paramObject;
/* 345 */       return (this.x == tuple3f.x && this.y == tuple3f.y && this.z == tuple3f.z); }
/*     */     catch (NullPointerException nullPointerException)
/* 347 */     { return false; }
/* 348 */     catch (ClassCastException classCastException) { return false; }
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
/*     */   public boolean epsilonEquals(Tuple3f paramTuple3f, float paramFloat) {
/* 365 */     float f = this.x - paramTuple3f.x;
/* 366 */     if (Float.isNaN(f)) return false; 
/* 367 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 369 */     f = this.y - paramTuple3f.y;
/* 370 */     if (Float.isNaN(f)) return false; 
/* 371 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 373 */     f = this.z - paramTuple3f.z;
/* 374 */     if (Float.isNaN(f)) return false; 
/* 375 */     if (((f < 0.0F) ? -f : f) > paramFloat) return false;
/*     */     
/* 377 */     return true;
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
/* 391 */     long l = 1L;
/* 392 */     l = 31L * l + VecMathUtil.floatToIntBits(this.x);
/* 393 */     l = 31L * l + VecMathUtil.floatToIntBits(this.y);
/* 394 */     l = 31L * l + VecMathUtil.floatToIntBits(this.z);
/* 395 */     return (int)(l ^ l >> 32);
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
/*     */   public final void clamp(float paramFloat1, float paramFloat2, Tuple3f paramTuple3f) {
/* 409 */     if (paramTuple3f.x > paramFloat2) {
/* 410 */       this.x = paramFloat2;
/* 411 */     } else if (paramTuple3f.x < paramFloat1) {
/* 412 */       this.x = paramFloat1;
/*     */     } else {
/* 414 */       this.x = paramTuple3f.x;
/*     */     } 
/*     */     
/* 417 */     if (paramTuple3f.y > paramFloat2) {
/* 418 */       this.y = paramFloat2;
/* 419 */     } else if (paramTuple3f.y < paramFloat1) {
/* 420 */       this.y = paramFloat1;
/*     */     } else {
/* 422 */       this.y = paramTuple3f.y;
/*     */     } 
/*     */     
/* 425 */     if (paramTuple3f.z > paramFloat2) {
/* 426 */       this.z = paramFloat2;
/* 427 */     } else if (paramTuple3f.z < paramFloat1) {
/* 428 */       this.z = paramFloat1;
/*     */     } else {
/* 430 */       this.z = paramTuple3f.z;
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
/*     */   public final void clampMin(float paramFloat, Tuple3f paramTuple3f) {
/* 444 */     if (paramTuple3f.x < paramFloat) {
/* 445 */       this.x = paramFloat;
/*     */     } else {
/* 447 */       this.x = paramTuple3f.x;
/*     */     } 
/*     */     
/* 450 */     if (paramTuple3f.y < paramFloat) {
/* 451 */       this.y = paramFloat;
/*     */     } else {
/* 453 */       this.y = paramTuple3f.y;
/*     */     } 
/*     */     
/* 456 */     if (paramTuple3f.z < paramFloat) {
/* 457 */       this.z = paramFloat;
/*     */     } else {
/* 459 */       this.z = paramTuple3f.z;
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
/*     */   public final void clampMax(float paramFloat, Tuple3f paramTuple3f) {
/* 473 */     if (paramTuple3f.x > paramFloat) {
/* 474 */       this.x = paramFloat;
/*     */     } else {
/* 476 */       this.x = paramTuple3f.x;
/*     */     } 
/*     */     
/* 479 */     if (paramTuple3f.y > paramFloat) {
/* 480 */       this.y = paramFloat;
/*     */     } else {
/* 482 */       this.y = paramTuple3f.y;
/*     */     } 
/*     */     
/* 485 */     if (paramTuple3f.z > paramFloat) {
/* 486 */       this.z = paramFloat;
/*     */     } else {
/* 488 */       this.z = paramTuple3f.z;
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
/*     */   public final void absolute(Tuple3f paramTuple3f) {
/* 501 */     this.x = Math.abs(paramTuple3f.x);
/* 502 */     this.y = Math.abs(paramTuple3f.y);
/* 503 */     this.z = Math.abs(paramTuple3f.z);
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
/* 515 */     if (this.x > paramFloat2) {
/* 516 */       this.x = paramFloat2;
/* 517 */     } else if (this.x < paramFloat1) {
/* 518 */       this.x = paramFloat1;
/*     */     } 
/*     */     
/* 521 */     if (this.y > paramFloat2) {
/* 522 */       this.y = paramFloat2;
/* 523 */     } else if (this.y < paramFloat1) {
/* 524 */       this.y = paramFloat1;
/*     */     } 
/*     */     
/* 527 */     if (this.z > paramFloat2) {
/* 528 */       this.z = paramFloat2;
/* 529 */     } else if (this.z < paramFloat1) {
/* 530 */       this.z = paramFloat1;
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
/* 542 */     if (this.x < paramFloat) this.x = paramFloat; 
/* 543 */     if (this.y < paramFloat) this.y = paramFloat; 
/* 544 */     if (this.z < paramFloat) this.z = paramFloat;
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
/* 555 */     if (this.x > paramFloat) this.x = paramFloat; 
/* 556 */     if (this.y > paramFloat) this.y = paramFloat; 
/* 557 */     if (this.z > paramFloat) this.z = paramFloat;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 567 */     this.x = Math.abs(this.x);
/* 568 */     this.y = Math.abs(this.y);
/* 569 */     this.z = Math.abs(this.z);
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
/*     */   public final void interpolate(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2, float paramFloat) {
/* 583 */     this.x = (1.0F - paramFloat) * paramTuple3f1.x + paramFloat * paramTuple3f2.x;
/* 584 */     this.y = (1.0F - paramFloat) * paramTuple3f1.y + paramFloat * paramTuple3f2.y;
/* 585 */     this.z = (1.0F - paramFloat) * paramTuple3f1.z + paramFloat * paramTuple3f2.z;
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
/*     */   public final void interpolate(Tuple3f paramTuple3f, float paramFloat) {
/* 599 */     this.x = (1.0F - paramFloat) * this.x + paramFloat * paramTuple3f.x;
/* 600 */     this.y = (1.0F - paramFloat) * this.y + paramFloat * paramTuple3f.y;
/* 601 */     this.z = (1.0F - paramFloat) * this.z + paramFloat * paramTuple3f.z;
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
/*     */   public Object clone() {
/*     */     try {
/* 617 */       return super.clone();
/* 618 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 620 */       throw new InternalError();
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
/* 633 */   public final float getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 645 */   public final void setX(float paramFloat) { this.x = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 657 */   public final float getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 669 */   public final void setY(float paramFloat) { this.y = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 680 */   public final float getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 692 */   public final void setZ(float paramFloat) { this.z = paramFloat; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Tuple3f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */