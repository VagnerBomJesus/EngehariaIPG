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
/*     */ public abstract class Tuple3i
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -732740491767276200L;
/*     */   public int x;
/*     */   public int y;
/*     */   public int z;
/*     */   
/*     */   public Tuple3i(int paramInt1, int paramInt2, int paramInt3) {
/*  51 */     this.x = paramInt1;
/*  52 */     this.y = paramInt2;
/*  53 */     this.z = paramInt3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3i(int[] paramArrayOfInt) {
/*  62 */     this.x = paramArrayOfInt[0];
/*  63 */     this.y = paramArrayOfInt[1];
/*  64 */     this.z = paramArrayOfInt[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3i(Tuple3i paramTuple3i) {
/*  74 */     this.x = paramTuple3i.x;
/*  75 */     this.y = paramTuple3i.y;
/*  76 */     this.z = paramTuple3i.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3i() {
/*  84 */     this.x = 0;
/*  85 */     this.y = 0;
/*  86 */     this.z = 0;
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
/*     */   public final void set(int paramInt1, int paramInt2, int paramInt3) {
/*  98 */     this.x = paramInt1;
/*  99 */     this.y = paramInt2;
/* 100 */     this.z = paramInt3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(int[] paramArrayOfInt) {
/* 110 */     this.x = paramArrayOfInt[0];
/* 111 */     this.y = paramArrayOfInt[1];
/* 112 */     this.z = paramArrayOfInt[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3i paramTuple3i) {
/* 121 */     this.x = paramTuple3i.x;
/* 122 */     this.y = paramTuple3i.y;
/* 123 */     this.z = paramTuple3i.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(int[] paramArrayOfInt) {
/* 132 */     paramArrayOfInt[0] = this.x;
/* 133 */     paramArrayOfInt[1] = this.y;
/* 134 */     paramArrayOfInt[2] = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple3i paramTuple3i) {
/* 143 */     paramTuple3i.x = this.x;
/* 144 */     paramTuple3i.y = this.y;
/* 145 */     paramTuple3i.z = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple3i paramTuple3i1, Tuple3i paramTuple3i2) {
/* 155 */     paramTuple3i1.x += paramTuple3i2.x;
/* 156 */     paramTuple3i1.y += paramTuple3i2.y;
/* 157 */     paramTuple3i1.z += paramTuple3i2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple3i paramTuple3i) {
/* 166 */     this.x += paramTuple3i.x;
/* 167 */     this.y += paramTuple3i.y;
/* 168 */     this.z += paramTuple3i.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple3i paramTuple3i1, Tuple3i paramTuple3i2) {
/* 179 */     paramTuple3i1.x -= paramTuple3i2.x;
/* 180 */     paramTuple3i1.y -= paramTuple3i2.y;
/* 181 */     paramTuple3i1.z -= paramTuple3i2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple3i paramTuple3i) {
/* 191 */     this.x -= paramTuple3i.x;
/* 192 */     this.y -= paramTuple3i.y;
/* 193 */     this.z -= paramTuple3i.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple3i paramTuple3i) {
/* 202 */     this.x = -paramTuple3i.x;
/* 203 */     this.y = -paramTuple3i.y;
/* 204 */     this.z = -paramTuple3i.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 212 */     this.x = -this.x;
/* 213 */     this.y = -this.y;
/* 214 */     this.z = -this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(int paramInt, Tuple3i paramTuple3i) {
/* 225 */     this.x = paramInt * paramTuple3i.x;
/* 226 */     this.y = paramInt * paramTuple3i.y;
/* 227 */     this.z = paramInt * paramTuple3i.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(int paramInt) {
/* 237 */     this.x *= paramInt;
/* 238 */     this.y *= paramInt;
/* 239 */     this.z *= paramInt;
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
/*     */   public final void scaleAdd(int paramInt, Tuple3i paramTuple3i1, Tuple3i paramTuple3i2) {
/* 251 */     this.x = paramInt * paramTuple3i1.x + paramTuple3i2.x;
/* 252 */     this.y = paramInt * paramTuple3i1.y + paramTuple3i2.y;
/* 253 */     this.z = paramInt * paramTuple3i1.z + paramTuple3i2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scaleAdd(int paramInt, Tuple3i paramTuple3i) {
/* 264 */     this.x = paramInt * this.x + paramTuple3i.x;
/* 265 */     this.y = paramInt * this.y + paramTuple3i.y;
/* 266 */     this.z = paramInt * this.z + paramTuple3i.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ")"; }
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
/* 288 */       Tuple3i tuple3i = (Tuple3i)paramObject;
/* 289 */       return (this.x == tuple3i.x && this.y == tuple3i.y && this.z == tuple3i.z);
/*     */     }
/* 291 */     catch (NullPointerException nullPointerException) {
/* 292 */       return false;
/*     */     }
/* 294 */     catch (ClassCastException classCastException) {
/* 295 */       return false;
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
/* 309 */     long l = 1L;
/* 310 */     l = 31L * l + this.x;
/* 311 */     l = 31L * l + this.y;
/* 312 */     l = 31L * l + this.z;
/* 313 */     return (int)(l ^ l >> 32);
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
/*     */   public final void clamp(int paramInt1, int paramInt2, Tuple3i paramTuple3i) {
/* 325 */     if (paramTuple3i.x > paramInt2) {
/* 326 */       this.x = paramInt2;
/* 327 */     } else if (paramTuple3i.x < paramInt1) {
/* 328 */       this.x = paramInt1;
/*     */     } else {
/* 330 */       this.x = paramTuple3i.x;
/*     */     } 
/*     */     
/* 333 */     if (paramTuple3i.y > paramInt2) {
/* 334 */       this.y = paramInt2;
/* 335 */     } else if (paramTuple3i.y < paramInt1) {
/* 336 */       this.y = paramInt1;
/*     */     } else {
/* 338 */       this.y = paramTuple3i.y;
/*     */     } 
/*     */     
/* 341 */     if (paramTuple3i.z > paramInt2) {
/* 342 */       this.z = paramInt2;
/* 343 */     } else if (paramTuple3i.z < paramInt1) {
/* 344 */       this.z = paramInt1;
/*     */     } else {
/* 346 */       this.z = paramTuple3i.z;
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
/*     */   public final void clampMin(int paramInt, Tuple3i paramTuple3i) {
/* 358 */     if (paramTuple3i.x < paramInt) {
/* 359 */       this.x = paramInt;
/*     */     } else {
/* 361 */       this.x = paramTuple3i.x;
/*     */     } 
/*     */     
/* 364 */     if (paramTuple3i.y < paramInt) {
/* 365 */       this.y = paramInt;
/*     */     } else {
/* 367 */       this.y = paramTuple3i.y;
/*     */     } 
/*     */     
/* 370 */     if (paramTuple3i.z < paramInt) {
/* 371 */       this.z = paramInt;
/*     */     } else {
/* 373 */       this.z = paramTuple3i.z;
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
/*     */   public final void clampMax(int paramInt, Tuple3i paramTuple3i) {
/* 385 */     if (paramTuple3i.x > paramInt) {
/* 386 */       this.x = paramInt;
/*     */     } else {
/* 388 */       this.x = paramTuple3i.x;
/*     */     } 
/*     */     
/* 391 */     if (paramTuple3i.y > paramInt) {
/* 392 */       this.y = paramInt;
/*     */     } else {
/* 394 */       this.y = paramTuple3i.y;
/*     */     } 
/*     */     
/* 397 */     if (paramTuple3i.z > paramInt) {
/* 398 */       this.z = paramInt;
/*     */     } else {
/* 400 */       this.z = paramTuple3i.z;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute(Tuple3i paramTuple3i) {
/* 411 */     this.x = Math.abs(paramTuple3i.x);
/* 412 */     this.y = Math.abs(paramTuple3i.y);
/* 413 */     this.z = Math.abs(paramTuple3i.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(int paramInt1, int paramInt2) {
/* 423 */     if (this.x > paramInt2) {
/* 424 */       this.x = paramInt2;
/* 425 */     } else if (this.x < paramInt1) {
/* 426 */       this.x = paramInt1;
/*     */     } 
/*     */     
/* 429 */     if (this.y > paramInt2) {
/* 430 */       this.y = paramInt2;
/* 431 */     } else if (this.y < paramInt1) {
/* 432 */       this.y = paramInt1;
/*     */     } 
/*     */     
/* 435 */     if (this.z > paramInt2) {
/* 436 */       this.z = paramInt2;
/* 437 */     } else if (this.z < paramInt1) {
/* 438 */       this.z = paramInt1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMin(int paramInt) {
/* 448 */     if (this.x < paramInt) {
/* 449 */       this.x = paramInt;
/*     */     }
/* 451 */     if (this.y < paramInt) {
/* 452 */       this.y = paramInt;
/*     */     }
/* 454 */     if (this.z < paramInt) {
/* 455 */       this.z = paramInt;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(int paramInt) {
/* 464 */     if (this.x > paramInt) {
/* 465 */       this.x = paramInt;
/*     */     }
/* 467 */     if (this.y > paramInt) {
/* 468 */       this.y = paramInt;
/*     */     }
/* 470 */     if (this.z > paramInt) {
/* 471 */       this.z = paramInt;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 479 */     this.x = Math.abs(this.x);
/* 480 */     this.y = Math.abs(this.y);
/* 481 */     this.z = Math.abs(this.z);
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
/* 495 */       return super.clone();
/* 496 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 498 */       throw new InternalError();
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
/* 511 */   public final int getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 523 */   public final void setX(int paramInt) { this.x = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 535 */   public final int getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 547 */   public final void setY(int paramInt) { this.y = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 557 */   public final int getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 569 */   public final void setZ(int paramInt) { this.z = paramInt; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Tuple3i.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */