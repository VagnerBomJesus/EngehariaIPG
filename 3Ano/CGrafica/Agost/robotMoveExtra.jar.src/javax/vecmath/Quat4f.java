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
/*     */ public class Quat4f
/*     */   extends Tuple4f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 2675933778405442383L;
/*     */   static final double EPS = 1.0E-6D;
/*     */   static final double EPS2 = 1.0E-30D;
/*     */   static final double PIO2 = 1.57079632679D;
/*     */   
/*     */   public Quat4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  41 */     float f = (float)(1.0D / Math.sqrt((paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 + paramFloat3 * paramFloat3 + paramFloat4 * paramFloat4)));
/*  42 */     this.x = paramFloat1 * f;
/*  43 */     this.y = paramFloat2 * f;
/*  44 */     this.z = paramFloat3 * f;
/*  45 */     this.w = paramFloat4 * f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Quat4f(float[] paramArrayOfFloat) {
/*  56 */     float f = (float)(1.0D / Math.sqrt((paramArrayOfFloat[0] * paramArrayOfFloat[0] + paramArrayOfFloat[1] * paramArrayOfFloat[1] + paramArrayOfFloat[2] * paramArrayOfFloat[2] + paramArrayOfFloat[3] * paramArrayOfFloat[3])));
/*  57 */     this.x = paramArrayOfFloat[0] * f;
/*  58 */     this.y = paramArrayOfFloat[1] * f;
/*  59 */     this.z = paramArrayOfFloat[2] * f;
/*  60 */     this.w = paramArrayOfFloat[3] * f;
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
/*  71 */   public Quat4f(Quat4f paramQuat4f) { super(paramQuat4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public Quat4f(Quat4d paramQuat4d) { super(paramQuat4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Quat4f(Tuple4f paramTuple4f) {
/*  91 */     float f = (float)(1.0D / Math.sqrt((paramTuple4f.x * paramTuple4f.x + paramTuple4f.y * paramTuple4f.y + paramTuple4f.z * paramTuple4f.z + paramTuple4f.w * paramTuple4f.w)));
/*  92 */     this.x = paramTuple4f.x * f;
/*  93 */     this.y = paramTuple4f.y * f;
/*  94 */     this.z = paramTuple4f.z * f;
/*  95 */     this.w = paramTuple4f.w * f;
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
/*     */   public Quat4f(Tuple4d paramTuple4d) {
/* 107 */     double d = 1.0D / Math.sqrt(paramTuple4d.x * paramTuple4d.x + paramTuple4d.y * paramTuple4d.y + paramTuple4d.z * paramTuple4d.z + paramTuple4d.w * paramTuple4d.w);
/* 108 */     this.x = (float)(paramTuple4d.x * d);
/* 109 */     this.y = (float)(paramTuple4d.y * d);
/* 110 */     this.z = (float)(paramTuple4d.z * d);
/* 111 */     this.w = (float)(paramTuple4d.w * d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Quat4f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void conjugate(Quat4f paramQuat4f) {
/* 130 */     this.x = -paramQuat4f.x;
/* 131 */     this.y = -paramQuat4f.y;
/* 132 */     this.z = -paramQuat4f.z;
/* 133 */     this.w = paramQuat4f.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void conjugate() {
/* 141 */     this.x = -this.x;
/* 142 */     this.y = -this.y;
/* 143 */     this.z = -this.z;
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
/*     */   public final void mul(Quat4f paramQuat4f1, Quat4f paramQuat4f2) {
/* 156 */     if (this != paramQuat4f1 && this != paramQuat4f2) {
/* 157 */       this.w = paramQuat4f1.w * paramQuat4f2.w - paramQuat4f1.x * paramQuat4f2.x - paramQuat4f1.y * paramQuat4f2.y - paramQuat4f1.z * paramQuat4f2.z;
/* 158 */       this.x = paramQuat4f1.w * paramQuat4f2.x + paramQuat4f2.w * paramQuat4f1.x + paramQuat4f1.y * paramQuat4f2.z - paramQuat4f1.z * paramQuat4f2.y;
/* 159 */       this.y = paramQuat4f1.w * paramQuat4f2.y + paramQuat4f2.w * paramQuat4f1.y - paramQuat4f1.x * paramQuat4f2.z + paramQuat4f1.z * paramQuat4f2.x;
/* 160 */       this.z = paramQuat4f1.w * paramQuat4f2.z + paramQuat4f2.w * paramQuat4f1.z + paramQuat4f1.x * paramQuat4f2.y - paramQuat4f1.y * paramQuat4f2.x;
/*     */     }
/*     */     else {
/*     */       
/* 164 */       float f3 = paramQuat4f1.w * paramQuat4f2.w - paramQuat4f1.x * paramQuat4f2.x - paramQuat4f1.y * paramQuat4f2.y - paramQuat4f1.z * paramQuat4f2.z;
/* 165 */       float f1 = paramQuat4f1.w * paramQuat4f2.x + paramQuat4f2.w * paramQuat4f1.x + paramQuat4f1.y * paramQuat4f2.z - paramQuat4f1.z * paramQuat4f2.y;
/* 166 */       float f2 = paramQuat4f1.w * paramQuat4f2.y + paramQuat4f2.w * paramQuat4f1.y - paramQuat4f1.x * paramQuat4f2.z + paramQuat4f1.z * paramQuat4f2.x;
/* 167 */       this.z = paramQuat4f1.w * paramQuat4f2.z + paramQuat4f2.w * paramQuat4f1.z + paramQuat4f1.x * paramQuat4f2.y - paramQuat4f1.y * paramQuat4f2.x;
/* 168 */       this.w = f3;
/* 169 */       this.x = f1;
/* 170 */       this.y = f2;
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
/*     */   public final void mul(Quat4f paramQuat4f) {
/* 184 */     float f3 = this.w * paramQuat4f.w - this.x * paramQuat4f.x - this.y * paramQuat4f.y - this.z * paramQuat4f.z;
/* 185 */     float f1 = this.w * paramQuat4f.x + paramQuat4f.w * this.x + this.y * paramQuat4f.z - this.z * paramQuat4f.y;
/* 186 */     float f2 = this.w * paramQuat4f.y + paramQuat4f.w * this.y - this.x * paramQuat4f.z + this.z * paramQuat4f.x;
/* 187 */     this.z = this.w * paramQuat4f.z + paramQuat4f.w * this.z + this.x * paramQuat4f.y - this.y * paramQuat4f.x;
/* 188 */     this.w = f3;
/* 189 */     this.x = f1;
/* 190 */     this.y = f2;
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
/*     */   public final void mulInverse(Quat4f paramQuat4f1, Quat4f paramQuat4f2) {
/* 203 */     Quat4f quat4f = new Quat4f(paramQuat4f2);
/*     */     
/* 205 */     quat4f.inverse();
/* 206 */     mul(paramQuat4f1, quat4f);
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
/*     */   public final void mulInverse(Quat4f paramQuat4f) {
/* 219 */     Quat4f quat4f = new Quat4f(paramQuat4f);
/*     */     
/* 221 */     quat4f.inverse();
/* 222 */     mul(quat4f);
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
/*     */   public final void inverse(Quat4f paramQuat4f) {
/* 235 */     float f = 1.0F / (paramQuat4f.w * paramQuat4f.w + paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z);
/* 236 */     this.w = f * paramQuat4f.w;
/* 237 */     this.x = -f * paramQuat4f.x;
/* 238 */     this.y = -f * paramQuat4f.y;
/* 239 */     this.z = -f * paramQuat4f.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void inverse() {
/* 250 */     float f = 1.0F / (this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
/* 251 */     this.w *= f;
/* 252 */     this.x *= -f;
/* 253 */     this.y *= -f;
/* 254 */     this.z *= -f;
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
/*     */   public final void normalize(Quat4f paramQuat4f) {
/* 267 */     float f = paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z + paramQuat4f.w * paramQuat4f.w;
/*     */     
/* 269 */     if (f > 0.0F) {
/* 270 */       f = 1.0F / (float)Math.sqrt(f);
/* 271 */       this.x = f * paramQuat4f.x;
/* 272 */       this.y = f * paramQuat4f.y;
/* 273 */       this.z = f * paramQuat4f.z;
/* 274 */       this.w = f * paramQuat4f.w;
/*     */     } else {
/* 276 */       this.x = 0.0F;
/* 277 */       this.y = 0.0F;
/* 278 */       this.z = 0.0F;
/* 279 */       this.w = 0.0F;
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
/*     */   public final void normalize() {
/* 291 */     float f = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
/*     */     
/* 293 */     if (f > 0.0F) {
/* 294 */       f = 1.0F / (float)Math.sqrt(f);
/* 295 */       this.x *= f;
/* 296 */       this.y *= f;
/* 297 */       this.z *= f;
/* 298 */       this.w *= f;
/*     */     } else {
/* 300 */       this.x = 0.0F;
/* 301 */       this.y = 0.0F;
/* 302 */       this.z = 0.0F;
/* 303 */       this.w = 0.0F;
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
/*     */   public final void set(Matrix4f paramMatrix4f) {
/* 315 */     float f = 0.25F * (paramMatrix4f.m00 + paramMatrix4f.m11 + paramMatrix4f.m22 + paramMatrix4f.m33);
/*     */     
/* 317 */     if (f >= 0.0F) {
/* 318 */       if (f >= 1.0E-30D) {
/* 319 */         this.w = (float)Math.sqrt(f);
/* 320 */         f = 0.25F / this.w;
/* 321 */         this.x = (paramMatrix4f.m21 - paramMatrix4f.m12) * f;
/* 322 */         this.y = (paramMatrix4f.m02 - paramMatrix4f.m20) * f;
/* 323 */         this.z = (paramMatrix4f.m10 - paramMatrix4f.m01) * f;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 327 */       this.w = 0.0F;
/* 328 */       this.x = 0.0F;
/* 329 */       this.y = 0.0F;
/* 330 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 334 */     this.w = 0.0F;
/* 335 */     f = -0.5F * (paramMatrix4f.m11 + paramMatrix4f.m22);
/*     */     
/* 337 */     if (f >= 0.0F) {
/* 338 */       if (f >= 1.0E-30D) {
/* 339 */         this.x = (float)Math.sqrt(f);
/* 340 */         f = 1.0F / 2.0F * this.x;
/* 341 */         this.y = paramMatrix4f.m10 * f;
/* 342 */         this.z = paramMatrix4f.m20 * f;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 346 */       this.x = 0.0F;
/* 347 */       this.y = 0.0F;
/* 348 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 352 */     this.x = 0.0F;
/* 353 */     f = 0.5F * (1.0F - paramMatrix4f.m22);
/*     */     
/* 355 */     if (f >= 1.0E-30D) {
/* 356 */       this.y = (float)Math.sqrt(f);
/* 357 */       this.z = paramMatrix4f.m21 / 2.0F * this.y;
/*     */       
/*     */       return;
/*     */     } 
/* 361 */     this.y = 0.0F;
/* 362 */     this.z = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Matrix4d paramMatrix4d) {
/* 373 */     double d = 0.25D * (paramMatrix4d.m00 + paramMatrix4d.m11 + paramMatrix4d.m22 + paramMatrix4d.m33);
/*     */     
/* 375 */     if (d >= 0.0D) {
/* 376 */       if (d >= 1.0E-30D) {
/* 377 */         this.w = (float)Math.sqrt(d);
/* 378 */         d = 0.25D / this.w;
/* 379 */         this.x = (float)((paramMatrix4d.m21 - paramMatrix4d.m12) * d);
/* 380 */         this.y = (float)((paramMatrix4d.m02 - paramMatrix4d.m20) * d);
/* 381 */         this.z = (float)((paramMatrix4d.m10 - paramMatrix4d.m01) * d);
/*     */         return;
/*     */       } 
/*     */     } else {
/* 385 */       this.w = 0.0F;
/* 386 */       this.x = 0.0F;
/* 387 */       this.y = 0.0F;
/* 388 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 392 */     this.w = 0.0F;
/* 393 */     d = -0.5D * (paramMatrix4d.m11 + paramMatrix4d.m22);
/* 394 */     if (d >= 0.0D) {
/* 395 */       if (d >= 1.0E-30D) {
/* 396 */         this.x = (float)Math.sqrt(d);
/* 397 */         d = 0.5D / this.x;
/* 398 */         this.y = (float)(paramMatrix4d.m10 * d);
/* 399 */         this.z = (float)(paramMatrix4d.m20 * d);
/*     */         return;
/*     */       } 
/*     */     } else {
/* 403 */       this.x = 0.0F;
/* 404 */       this.y = 0.0F;
/* 405 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 409 */     this.x = 0.0F;
/* 410 */     d = 0.5D * (1.0D - paramMatrix4d.m22);
/* 411 */     if (d >= 1.0E-30D) {
/* 412 */       this.y = (float)Math.sqrt(d);
/* 413 */       this.z = (float)(paramMatrix4d.m21 / 2.0D * this.y);
/*     */       
/*     */       return;
/*     */     } 
/* 417 */     this.y = 0.0F;
/* 418 */     this.z = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Matrix3f paramMatrix3f) {
/* 429 */     float f = 0.25F * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 + 1.0F);
/*     */     
/* 431 */     if (f >= 0.0F) {
/* 432 */       if (f >= 1.0E-30D) {
/* 433 */         this.w = (float)Math.sqrt(f);
/* 434 */         f = 0.25F / this.w;
/* 435 */         this.x = (paramMatrix3f.m21 - paramMatrix3f.m12) * f;
/* 436 */         this.y = (paramMatrix3f.m02 - paramMatrix3f.m20) * f;
/* 437 */         this.z = (paramMatrix3f.m10 - paramMatrix3f.m01) * f;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 441 */       this.w = 0.0F;
/* 442 */       this.x = 0.0F;
/* 443 */       this.y = 0.0F;
/* 444 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 448 */     this.w = 0.0F;
/* 449 */     f = -0.5F * (paramMatrix3f.m11 + paramMatrix3f.m22);
/* 450 */     if (f >= 0.0F) {
/* 451 */       if (f >= 1.0E-30D) {
/* 452 */         this.x = (float)Math.sqrt(f);
/* 453 */         f = 0.5F / this.x;
/* 454 */         this.y = paramMatrix3f.m10 * f;
/* 455 */         this.z = paramMatrix3f.m20 * f;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 459 */       this.x = 0.0F;
/* 460 */       this.y = 0.0F;
/* 461 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 465 */     this.x = 0.0F;
/* 466 */     f = 0.5F * (1.0F - paramMatrix3f.m22);
/* 467 */     if (f >= 1.0E-30D) {
/* 468 */       this.y = (float)Math.sqrt(f);
/* 469 */       this.z = paramMatrix3f.m21 / 2.0F * this.y;
/*     */       
/*     */       return;
/*     */     } 
/* 473 */     this.y = 0.0F;
/* 474 */     this.z = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Matrix3d paramMatrix3d) {
/* 485 */     double d = 0.25D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 + 1.0D);
/*     */     
/* 487 */     if (d >= 0.0D) {
/* 488 */       if (d >= 1.0E-30D) {
/* 489 */         this.w = (float)Math.sqrt(d);
/* 490 */         d = 0.25D / this.w;
/* 491 */         this.x = (float)((paramMatrix3d.m21 - paramMatrix3d.m12) * d);
/* 492 */         this.y = (float)((paramMatrix3d.m02 - paramMatrix3d.m20) * d);
/* 493 */         this.z = (float)((paramMatrix3d.m10 - paramMatrix3d.m01) * d);
/*     */         return;
/*     */       } 
/*     */     } else {
/* 497 */       this.w = 0.0F;
/* 498 */       this.x = 0.0F;
/* 499 */       this.y = 0.0F;
/* 500 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 504 */     this.w = 0.0F;
/* 505 */     d = -0.5D * (paramMatrix3d.m11 + paramMatrix3d.m22);
/* 506 */     if (d >= 0.0D) {
/* 507 */       if (d >= 1.0E-30D) {
/* 508 */         this.x = (float)Math.sqrt(d);
/* 509 */         d = 0.5D / this.x;
/* 510 */         this.y = (float)(paramMatrix3d.m10 * d);
/* 511 */         this.z = (float)(paramMatrix3d.m20 * d);
/*     */         return;
/*     */       } 
/*     */     } else {
/* 515 */       this.x = 0.0F;
/* 516 */       this.y = 0.0F;
/* 517 */       this.z = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 521 */     this.x = 0.0F;
/* 522 */     d = 0.5D * (1.0D - paramMatrix3d.m22);
/* 523 */     if (d >= 1.0E-30D) {
/* 524 */       this.y = (float)Math.sqrt(d);
/* 525 */       this.z = (float)(paramMatrix3d.m21 / 2.0D * this.y);
/*     */       
/*     */       return;
/*     */     } 
/* 529 */     this.y = 0.0F;
/* 530 */     this.z = 1.0F;
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
/*     */   public final void set(AxisAngle4f paramAxisAngle4f) {
/* 543 */     float f = (float)Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/* 544 */     if (f < 1.0E-6D) {
/* 545 */       this.w = 0.0F;
/* 546 */       this.x = 0.0F;
/* 547 */       this.y = 0.0F;
/* 548 */       this.z = 0.0F;
/*     */     } else {
/* 550 */       f = 1.0F / f;
/* 551 */       float f1 = (float)Math.sin(paramAxisAngle4f.angle / 2.0D);
/* 552 */       this.w = (float)Math.cos(paramAxisAngle4f.angle / 2.0D);
/* 553 */       this.x = paramAxisAngle4f.x * f * f1;
/* 554 */       this.y = paramAxisAngle4f.y * f * f1;
/* 555 */       this.z = paramAxisAngle4f.z * f * f1;
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
/*     */   public final void set(AxisAngle4d paramAxisAngle4d) {
/* 570 */     float f = (float)(1.0D / Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z));
/*     */     
/* 572 */     if (f < 1.0E-6D) {
/* 573 */       this.w = 0.0F;
/* 574 */       this.x = 0.0F;
/* 575 */       this.y = 0.0F;
/* 576 */       this.z = 0.0F;
/*     */     } else {
/* 578 */       f = 1.0F / f;
/* 579 */       float f1 = (float)Math.sin(paramAxisAngle4d.angle / 2.0D);
/* 580 */       this.w = (float)Math.cos(paramAxisAngle4d.angle / 2.0D);
/* 581 */       this.x = (float)paramAxisAngle4d.x * f * f1;
/* 582 */       this.y = (float)paramAxisAngle4d.y * f * f1;
/* 583 */       this.z = (float)paramAxisAngle4d.z * f * f1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void interpolate(Quat4f paramQuat4f, float paramFloat) {
/* 606 */     double d3, d2, d1 = (this.x * paramQuat4f.x + this.y * paramQuat4f.y + this.z * paramQuat4f.z + this.w * paramQuat4f.w);
/*     */     
/* 608 */     if (d1 < 0.0D) {
/*     */       
/* 610 */       paramQuat4f.x = -paramQuat4f.x; paramQuat4f.y = -paramQuat4f.y; paramQuat4f.z = -paramQuat4f.z; paramQuat4f.w = -paramQuat4f.w;
/* 611 */       d1 = -d1;
/*     */     } 
/*     */     
/* 614 */     if (1.0D - d1 > 1.0E-6D) {
/* 615 */       double d4 = Math.acos(d1);
/* 616 */       double d5 = Math.sin(d4);
/* 617 */       d2 = Math.sin((1.0D - paramFloat) * d4) / d5;
/* 618 */       d3 = Math.sin(paramFloat * d4) / d5;
/*     */     } else {
/* 620 */       d2 = 1.0D - paramFloat;
/* 621 */       d3 = paramFloat;
/*     */     } 
/*     */     
/* 624 */     this.w = (float)(d2 * this.w + d3 * paramQuat4f.w);
/* 625 */     this.x = (float)(d2 * this.x + d3 * paramQuat4f.x);
/* 626 */     this.y = (float)(d2 * this.y + d3 * paramQuat4f.y);
/* 627 */     this.z = (float)(d2 * this.z + d3 * paramQuat4f.z);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void interpolate(Quat4f paramQuat4f1, Quat4f paramQuat4f2, float paramFloat) {
/* 649 */     double d3, d2, d1 = (paramQuat4f2.x * paramQuat4f1.x + paramQuat4f2.y * paramQuat4f1.y + paramQuat4f2.z * paramQuat4f1.z + paramQuat4f2.w * paramQuat4f1.w);
/*     */     
/* 651 */     if (d1 < 0.0D) {
/*     */       
/* 653 */       paramQuat4f1.x = -paramQuat4f1.x; paramQuat4f1.y = -paramQuat4f1.y; paramQuat4f1.z = -paramQuat4f1.z; paramQuat4f1.w = -paramQuat4f1.w;
/* 654 */       d1 = -d1;
/*     */     } 
/*     */     
/* 657 */     if (1.0D - d1 > 1.0E-6D) {
/* 658 */       double d4 = Math.acos(d1);
/* 659 */       double d5 = Math.sin(d4);
/* 660 */       d2 = Math.sin((1.0D - paramFloat) * d4) / d5;
/* 661 */       d3 = Math.sin(paramFloat * d4) / d5;
/*     */     } else {
/* 663 */       d2 = 1.0D - paramFloat;
/* 664 */       d3 = paramFloat;
/*     */     } 
/* 666 */     this.w = (float)(d2 * paramQuat4f1.w + d3 * paramQuat4f2.w);
/* 667 */     this.x = (float)(d2 * paramQuat4f1.x + d3 * paramQuat4f2.x);
/* 668 */     this.y = (float)(d2 * paramQuat4f1.y + d3 * paramQuat4f2.y);
/* 669 */     this.z = (float)(d2 * paramQuat4f1.z + d3 * paramQuat4f2.z);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Quat4f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */