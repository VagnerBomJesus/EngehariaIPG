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
/*     */ public abstract class Tuple4i
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = 8064614250942616720L;
/*     */   public int x;
/*     */   public int y;
/*     */   public int z;
/*     */   public int w;
/*     */   
/*     */   public Tuple4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  57 */     this.x = paramInt1;
/*  58 */     this.y = paramInt2;
/*  59 */     this.z = paramInt3;
/*  60 */     this.w = paramInt4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4i(int[] paramArrayOfInt) {
/*  69 */     this.x = paramArrayOfInt[0];
/*  70 */     this.y = paramArrayOfInt[1];
/*  71 */     this.z = paramArrayOfInt[2];
/*  72 */     this.w = paramArrayOfInt[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4i(Tuple4i paramTuple4i) {
/*  82 */     this.x = paramTuple4i.x;
/*  83 */     this.y = paramTuple4i.y;
/*  84 */     this.z = paramTuple4i.z;
/*  85 */     this.w = paramTuple4i.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4i() {
/*  93 */     this.x = 0;
/*  94 */     this.y = 0;
/*  95 */     this.z = 0;
/*  96 */     this.w = 0;
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
/*     */   public final void set(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 109 */     this.x = paramInt1;
/* 110 */     this.y = paramInt2;
/* 111 */     this.z = paramInt3;
/* 112 */     this.w = paramInt4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(int[] paramArrayOfInt) {
/* 122 */     this.x = paramArrayOfInt[0];
/* 123 */     this.y = paramArrayOfInt[1];
/* 124 */     this.z = paramArrayOfInt[2];
/* 125 */     this.w = paramArrayOfInt[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4i paramTuple4i) {
/* 134 */     this.x = paramTuple4i.x;
/* 135 */     this.y = paramTuple4i.y;
/* 136 */     this.z = paramTuple4i.z;
/* 137 */     this.w = paramTuple4i.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(int[] paramArrayOfInt) {
/* 146 */     paramArrayOfInt[0] = this.x;
/* 147 */     paramArrayOfInt[1] = this.y;
/* 148 */     paramArrayOfInt[2] = this.z;
/* 149 */     paramArrayOfInt[3] = this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple4i paramTuple4i) {
/* 158 */     paramTuple4i.x = this.x;
/* 159 */     paramTuple4i.y = this.y;
/* 160 */     paramTuple4i.z = this.z;
/* 161 */     paramTuple4i.w = this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple4i paramTuple4i1, Tuple4i paramTuple4i2) {
/* 171 */     paramTuple4i1.x += paramTuple4i2.x;
/* 172 */     paramTuple4i1.y += paramTuple4i2.y;
/* 173 */     paramTuple4i1.z += paramTuple4i2.z;
/* 174 */     paramTuple4i1.w += paramTuple4i2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple4i paramTuple4i) {
/* 183 */     this.x += paramTuple4i.x;
/* 184 */     this.y += paramTuple4i.y;
/* 185 */     this.z += paramTuple4i.z;
/* 186 */     this.w += paramTuple4i.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple4i paramTuple4i1, Tuple4i paramTuple4i2) {
/* 197 */     paramTuple4i1.x -= paramTuple4i2.x;
/* 198 */     paramTuple4i1.y -= paramTuple4i2.y;
/* 199 */     paramTuple4i1.z -= paramTuple4i2.z;
/* 200 */     paramTuple4i1.w -= paramTuple4i2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple4i paramTuple4i) {
/* 210 */     this.x -= paramTuple4i.x;
/* 211 */     this.y -= paramTuple4i.y;
/* 212 */     this.z -= paramTuple4i.z;
/* 213 */     this.w -= paramTuple4i.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple4i paramTuple4i) {
/* 222 */     this.x = -paramTuple4i.x;
/* 223 */     this.y = -paramTuple4i.y;
/* 224 */     this.z = -paramTuple4i.z;
/* 225 */     this.w = -paramTuple4i.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 233 */     this.x = -this.x;
/* 234 */     this.y = -this.y;
/* 235 */     this.z = -this.z;
/* 236 */     this.w = -this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(int paramInt, Tuple4i paramTuple4i) {
/* 247 */     this.x = paramInt * paramTuple4i.x;
/* 248 */     this.y = paramInt * paramTuple4i.y;
/* 249 */     this.z = paramInt * paramTuple4i.z;
/* 250 */     this.w = paramInt * paramTuple4i.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(int paramInt) {
/* 260 */     this.x *= paramInt;
/* 261 */     this.y *= paramInt;
/* 262 */     this.z *= paramInt;
/* 263 */     this.w *= paramInt;
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
/*     */   public final void scaleAdd(int paramInt, Tuple4i paramTuple4i1, Tuple4i paramTuple4i2) {
/* 275 */     this.x = paramInt * paramTuple4i1.x + paramTuple4i2.x;
/* 276 */     this.y = paramInt * paramTuple4i1.y + paramTuple4i2.y;
/* 277 */     this.z = paramInt * paramTuple4i1.z + paramTuple4i2.z;
/* 278 */     this.w = paramInt * paramTuple4i1.w + paramTuple4i2.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scaleAdd(int paramInt, Tuple4i paramTuple4i) {
/* 289 */     this.x = paramInt * this.x + paramTuple4i.x;
/* 290 */     this.y = paramInt * this.y + paramTuple4i.y;
/* 291 */     this.z = paramInt * this.z + paramTuple4i.z;
/* 292 */     this.w = paramInt * this.w + paramTuple4i.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")"; }
/*     */ 
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
/*     */     try {
/* 315 */       Tuple4i tuple4i = (Tuple4i)paramObject;
/* 316 */       return (this.x == tuple4i.x && this.y == tuple4i.y && this.z == tuple4i.z && this.w == tuple4i.w);
/*     */     
/*     */     }
/* 319 */     catch (NullPointerException nullPointerException) {
/* 320 */       return false;
/*     */     }
/* 322 */     catch (ClassCastException classCastException) {
/* 323 */       return false;
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
/*     */   public int hashCode() {
/* 337 */     long l = 1L;
/* 338 */     l = 31L * l + this.x;
/* 339 */     l = 31L * l + this.y;
/* 340 */     l = 31L * l + this.z;
/* 341 */     l = 31L * l + this.w;
/* 342 */     return (int)(l ^ l >> 32);
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
/*     */   public final void clamp(int paramInt1, int paramInt2, Tuple4i paramTuple4i) {
/* 354 */     if (paramTuple4i.x > paramInt2) {
/* 355 */       this.x = paramInt2;
/* 356 */     } else if (paramTuple4i.x < paramInt1) {
/* 357 */       this.x = paramInt1;
/*     */     } else {
/* 359 */       this.x = paramTuple4i.x;
/*     */     } 
/*     */     
/* 362 */     if (paramTuple4i.y > paramInt2) {
/* 363 */       this.y = paramInt2;
/* 364 */     } else if (paramTuple4i.y < paramInt1) {
/* 365 */       this.y = paramInt1;
/*     */     } else {
/* 367 */       this.y = paramTuple4i.y;
/*     */     } 
/*     */     
/* 370 */     if (paramTuple4i.z > paramInt2) {
/* 371 */       this.z = paramInt2;
/* 372 */     } else if (paramTuple4i.z < paramInt1) {
/* 373 */       this.z = paramInt1;
/*     */     } else {
/* 375 */       this.z = paramTuple4i.z;
/*     */     } 
/*     */     
/* 378 */     if (paramTuple4i.w > paramInt2) {
/* 379 */       this.w = paramInt2;
/* 380 */     } else if (paramTuple4i.w < paramInt1) {
/* 381 */       this.w = paramInt1;
/*     */     } else {
/* 383 */       this.w = paramTuple4i.w;
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
/*     */   public final void clampMin(int paramInt, Tuple4i paramTuple4i) {
/* 395 */     if (paramTuple4i.x < paramInt) {
/* 396 */       this.x = paramInt;
/*     */     } else {
/* 398 */       this.x = paramTuple4i.x;
/*     */     } 
/*     */     
/* 401 */     if (paramTuple4i.y < paramInt) {
/* 402 */       this.y = paramInt;
/*     */     } else {
/* 404 */       this.y = paramTuple4i.y;
/*     */     } 
/*     */     
/* 407 */     if (paramTuple4i.z < paramInt) {
/* 408 */       this.z = paramInt;
/*     */     } else {
/* 410 */       this.z = paramTuple4i.z;
/*     */     } 
/*     */     
/* 413 */     if (paramTuple4i.w < paramInt) {
/* 414 */       this.w = paramInt;
/*     */     } else {
/* 416 */       this.w = paramTuple4i.w;
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
/*     */   public final void clampMax(int paramInt, Tuple4i paramTuple4i) {
/* 430 */     if (paramTuple4i.x > paramInt) {
/* 431 */       this.x = paramInt;
/*     */     } else {
/* 433 */       this.x = paramTuple4i.x;
/*     */     } 
/*     */     
/* 436 */     if (paramTuple4i.y > paramInt) {
/* 437 */       this.y = paramInt;
/*     */     } else {
/* 439 */       this.y = paramTuple4i.y;
/*     */     } 
/*     */     
/* 442 */     if (paramTuple4i.z > paramInt) {
/* 443 */       this.z = paramInt;
/*     */     } else {
/* 445 */       this.z = paramTuple4i.z;
/*     */     } 
/*     */     
/* 448 */     if (paramTuple4i.w > paramInt) {
/* 449 */       this.w = paramInt;
/*     */     } else {
/* 451 */       this.w = paramTuple4i.z;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute(Tuple4i paramTuple4i) {
/* 462 */     this.x = Math.abs(paramTuple4i.x);
/* 463 */     this.y = Math.abs(paramTuple4i.y);
/* 464 */     this.z = Math.abs(paramTuple4i.z);
/* 465 */     this.w = Math.abs(paramTuple4i.w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(int paramInt1, int paramInt2) {
/* 475 */     if (this.x > paramInt2) {
/* 476 */       this.x = paramInt2;
/* 477 */     } else if (this.x < paramInt1) {
/* 478 */       this.x = paramInt1;
/*     */     } 
/*     */     
/* 481 */     if (this.y > paramInt2) {
/* 482 */       this.y = paramInt2;
/* 483 */     } else if (this.y < paramInt1) {
/* 484 */       this.y = paramInt1;
/*     */     } 
/*     */     
/* 487 */     if (this.z > paramInt2) {
/* 488 */       this.z = paramInt2;
/* 489 */     } else if (this.z < paramInt1) {
/* 490 */       this.z = paramInt1;
/*     */     } 
/*     */     
/* 493 */     if (this.w > paramInt2) {
/* 494 */       this.w = paramInt2;
/* 495 */     } else if (this.w < paramInt1) {
/* 496 */       this.w = paramInt1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMin(int paramInt) {
/* 506 */     if (this.x < paramInt) {
/* 507 */       this.x = paramInt;
/*     */     }
/* 509 */     if (this.y < paramInt) {
/* 510 */       this.y = paramInt;
/*     */     }
/* 512 */     if (this.z < paramInt) {
/* 513 */       this.z = paramInt;
/*     */     }
/* 515 */     if (this.w < paramInt) {
/* 516 */       this.w = paramInt;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(int paramInt) {
/* 525 */     if (this.x > paramInt) {
/* 526 */       this.x = paramInt;
/*     */     }
/* 528 */     if (this.y > paramInt) {
/* 529 */       this.y = paramInt;
/*     */     }
/* 531 */     if (this.z > paramInt) {
/* 532 */       this.z = paramInt;
/*     */     }
/* 534 */     if (this.w > paramInt) {
/* 535 */       this.w = paramInt;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 543 */     this.x = Math.abs(this.x);
/* 544 */     this.y = Math.abs(this.y);
/* 545 */     this.z = Math.abs(this.z);
/* 546 */     this.w = Math.abs(this.w);
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
/* 560 */       return super.clone();
/* 561 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 563 */       throw new InternalError();
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
/* 577 */   public final int getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 589 */   public final void setX(int paramInt) { this.x = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 601 */   public final int getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 613 */   public final void setY(int paramInt) { this.y = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 624 */   public final int getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 636 */   public final void setZ(int paramInt) { this.z = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 646 */   public final int getW() { return this.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 658 */   public final void setW(int paramInt) { this.w = paramInt; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Tuple4i.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */