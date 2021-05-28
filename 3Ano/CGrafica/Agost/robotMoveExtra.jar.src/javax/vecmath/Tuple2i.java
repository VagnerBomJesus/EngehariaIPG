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
/*     */ public abstract class Tuple2i
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -3555701650170169638L;
/*     */   public int x;
/*     */   public int y;
/*     */   
/*     */   public Tuple2i(int paramInt1, int paramInt2) {
/*  45 */     this.x = paramInt1;
/*  46 */     this.y = paramInt2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2i(int[] paramArrayOfInt) {
/*  55 */     this.x = paramArrayOfInt[0];
/*  56 */     this.y = paramArrayOfInt[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2i(Tuple2i paramTuple2i) {
/*  66 */     this.x = paramTuple2i.x;
/*  67 */     this.y = paramTuple2i.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple2i() {
/*  75 */     this.x = 0;
/*  76 */     this.y = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(int paramInt1, int paramInt2) {
/*  87 */     this.x = paramInt1;
/*  88 */     this.y = paramInt2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(int[] paramArrayOfInt) {
/*  98 */     this.x = paramArrayOfInt[0];
/*  99 */     this.y = paramArrayOfInt[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple2i paramTuple2i) {
/* 108 */     this.x = paramTuple2i.x;
/* 109 */     this.y = paramTuple2i.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(int[] paramArrayOfInt) {
/* 118 */     paramArrayOfInt[0] = this.x;
/* 119 */     paramArrayOfInt[1] = this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple2i paramTuple2i) {
/* 128 */     paramTuple2i.x = this.x;
/* 129 */     paramTuple2i.y = this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple2i paramTuple2i1, Tuple2i paramTuple2i2) {
/* 139 */     paramTuple2i1.x += paramTuple2i2.x;
/* 140 */     paramTuple2i1.y += paramTuple2i2.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(Tuple2i paramTuple2i) {
/* 149 */     this.x += paramTuple2i.x;
/* 150 */     this.y += paramTuple2i.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple2i paramTuple2i1, Tuple2i paramTuple2i2) {
/* 161 */     paramTuple2i1.x -= paramTuple2i2.x;
/* 162 */     paramTuple2i1.y -= paramTuple2i2.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void sub(Tuple2i paramTuple2i) {
/* 172 */     this.x -= paramTuple2i.x;
/* 173 */     this.y -= paramTuple2i.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate(Tuple2i paramTuple2i) {
/* 182 */     this.x = -paramTuple2i.x;
/* 183 */     this.y = -paramTuple2i.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 191 */     this.x = -this.x;
/* 192 */     this.y = -this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(int paramInt, Tuple2i paramTuple2i) {
/* 203 */     this.x = paramInt * paramTuple2i.x;
/* 204 */     this.y = paramInt * paramTuple2i.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scale(int paramInt) {
/* 214 */     this.x *= paramInt;
/* 215 */     this.y *= paramInt;
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
/*     */   public final void scaleAdd(int paramInt, Tuple2i paramTuple2i1, Tuple2i paramTuple2i2) {
/* 227 */     this.x = paramInt * paramTuple2i1.x + paramTuple2i2.x;
/* 228 */     this.y = paramInt * paramTuple2i1.y + paramTuple2i2.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void scaleAdd(int paramInt, Tuple2i paramTuple2i) {
/* 239 */     this.x = paramInt * this.x + paramTuple2i.x;
/* 240 */     this.y = paramInt * this.y + paramTuple2i.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public String toString() { return "(" + this.x + ", " + this.y + ")"; }
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
/* 262 */       Tuple2i tuple2i = (Tuple2i)paramObject;
/* 263 */       return (this.x == tuple2i.x && this.y == tuple2i.y);
/*     */     }
/* 265 */     catch (NullPointerException nullPointerException) {
/* 266 */       return false;
/*     */     }
/* 268 */     catch (ClassCastException classCastException) {
/* 269 */       return false;
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
/* 283 */     long l = 1L;
/* 284 */     l = 31L * l + this.x;
/* 285 */     l = 31L * l + this.y;
/* 286 */     return (int)(l ^ l >> 32);
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
/*     */   public final void clamp(int paramInt1, int paramInt2, Tuple2i paramTuple2i) {
/* 298 */     if (paramTuple2i.x > paramInt2) {
/* 299 */       this.x = paramInt2;
/* 300 */     } else if (paramTuple2i.x < paramInt1) {
/* 301 */       this.x = paramInt1;
/*     */     } else {
/* 303 */       this.x = paramTuple2i.x;
/*     */     } 
/*     */     
/* 306 */     if (paramTuple2i.y > paramInt2) {
/* 307 */       this.y = paramInt2;
/* 308 */     } else if (paramTuple2i.y < paramInt1) {
/* 309 */       this.y = paramInt1;
/*     */     } else {
/* 311 */       this.y = paramTuple2i.y;
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
/*     */   public final void clampMin(int paramInt, Tuple2i paramTuple2i) {
/* 323 */     if (paramTuple2i.x < paramInt) {
/* 324 */       this.x = paramInt;
/*     */     } else {
/* 326 */       this.x = paramTuple2i.x;
/*     */     } 
/*     */     
/* 329 */     if (paramTuple2i.y < paramInt) {
/* 330 */       this.y = paramInt;
/*     */     } else {
/* 332 */       this.y = paramTuple2i.y;
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
/*     */   public final void clampMax(int paramInt, Tuple2i paramTuple2i) {
/* 344 */     if (paramTuple2i.x > paramInt) {
/* 345 */       this.x = paramInt;
/*     */     } else {
/* 347 */       this.x = paramTuple2i.x;
/*     */     } 
/*     */     
/* 350 */     if (paramTuple2i.y > paramInt) {
/* 351 */       this.y = paramInt;
/*     */     } else {
/* 353 */       this.y = paramTuple2i.y;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute(Tuple2i paramTuple2i) {
/* 364 */     this.x = Math.abs(paramTuple2i.x);
/* 365 */     this.y = Math.abs(paramTuple2i.y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clamp(int paramInt1, int paramInt2) {
/* 375 */     if (this.x > paramInt2) {
/* 376 */       this.x = paramInt2;
/* 377 */     } else if (this.x < paramInt1) {
/* 378 */       this.x = paramInt1;
/*     */     } 
/*     */     
/* 381 */     if (this.y > paramInt2) {
/* 382 */       this.y = paramInt2;
/* 383 */     } else if (this.y < paramInt1) {
/* 384 */       this.y = paramInt1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMin(int paramInt) {
/* 394 */     if (this.x < paramInt) {
/* 395 */       this.x = paramInt;
/*     */     }
/* 397 */     if (this.y < paramInt) {
/* 398 */       this.y = paramInt;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clampMax(int paramInt) {
/* 407 */     if (this.x > paramInt) {
/* 408 */       this.x = paramInt;
/*     */     }
/* 410 */     if (this.y > paramInt) {
/* 411 */       this.y = paramInt;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void absolute() {
/* 419 */     this.x = Math.abs(this.x);
/* 420 */     this.y = Math.abs(this.y);
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
/*     */   public Object clone() {
/*     */     try {
/* 433 */       return super.clone();
/* 434 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 436 */       throw new InternalError();
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
/* 449 */   public final int getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 461 */   public final void setX(int paramInt) { this.x = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 473 */   public final int getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 485 */   public final void setY(int paramInt) { this.y = paramInt; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Tuple2i.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */