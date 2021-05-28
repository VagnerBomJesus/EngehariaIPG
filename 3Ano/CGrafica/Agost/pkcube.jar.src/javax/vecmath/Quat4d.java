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
/*     */ public class Quat4d
/*     */   extends Tuple4d
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 7577479888820201099L;
/*     */   static final double EPS = 1.0E-6D;
/*     */   static final double EPS2 = 1.0E-30D;
/*     */   static final double PIO2 = 1.57079632679D;
/*     */   
/*     */   public Quat4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/*  41 */     double d = 1.0D / Math.sqrt(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2 + paramDouble3 * paramDouble3 + paramDouble4 * paramDouble4);
/*  42 */     this.x = paramDouble1 * d;
/*  43 */     this.y = paramDouble2 * d;
/*  44 */     this.z = paramDouble3 * d;
/*  45 */     this.w = paramDouble4 * d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Quat4d(double[] paramArrayOfDouble) {
/*  56 */     double d = 1.0D / Math.sqrt(paramArrayOfDouble[0] * paramArrayOfDouble[0] + paramArrayOfDouble[1] * paramArrayOfDouble[1] + paramArrayOfDouble[2] * paramArrayOfDouble[2] + paramArrayOfDouble[3] * paramArrayOfDouble[3]);
/*  57 */     this.x = paramArrayOfDouble[0] * d;
/*  58 */     this.y = paramArrayOfDouble[1] * d;
/*  59 */     this.z = paramArrayOfDouble[2] * d;
/*  60 */     this.w = paramArrayOfDouble[3] * d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public Quat4d(Quat4d paramQuat4d) { super(paramQuat4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public Quat4d(Quat4f paramQuat4f) { super(paramQuat4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Quat4d(Tuple4f paramTuple4f) {
/*  90 */     double d = 1.0D / Math.sqrt((paramTuple4f.x * paramTuple4f.x + paramTuple4f.y * paramTuple4f.y + paramTuple4f.z * paramTuple4f.z + paramTuple4f.w * paramTuple4f.w));
/*  91 */     this.x = paramTuple4f.x * d;
/*  92 */     this.y = paramTuple4f.y * d;
/*  93 */     this.z = paramTuple4f.z * d;
/*  94 */     this.w = paramTuple4f.w * d;
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
/*     */   public Quat4d(Tuple4d paramTuple4d) {
/* 106 */     double d = 1.0D / Math.sqrt(paramTuple4d.x * paramTuple4d.x + paramTuple4d.y * paramTuple4d.y + paramTuple4d.z * paramTuple4d.z + paramTuple4d.w * paramTuple4d.w);
/* 107 */     this.x = paramTuple4d.x * d;
/* 108 */     this.y = paramTuple4d.y * d;
/* 109 */     this.z = paramTuple4d.z * d;
/* 110 */     this.w = paramTuple4d.w * d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Quat4d() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void conjugate(Quat4d paramQuat4d) {
/* 129 */     this.x = -paramQuat4d.x;
/* 130 */     this.y = -paramQuat4d.y;
/* 131 */     this.z = -paramQuat4d.z;
/* 132 */     this.w = paramQuat4d.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void conjugate() {
/* 142 */     this.x = -this.x;
/* 143 */     this.y = -this.y;
/* 144 */     this.z = -this.z;
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
/*     */   public final void mul(Quat4d paramQuat4d1, Quat4d paramQuat4d2) {
/* 157 */     if (this != paramQuat4d1 && this != paramQuat4d2) {
/* 158 */       this.w = paramQuat4d1.w * paramQuat4d2.w - paramQuat4d1.x * paramQuat4d2.x - paramQuat4d1.y * paramQuat4d2.y - paramQuat4d1.z * paramQuat4d2.z;
/* 159 */       this.x = paramQuat4d1.w * paramQuat4d2.x + paramQuat4d2.w * paramQuat4d1.x + paramQuat4d1.y * paramQuat4d2.z - paramQuat4d1.z * paramQuat4d2.y;
/* 160 */       this.y = paramQuat4d1.w * paramQuat4d2.y + paramQuat4d2.w * paramQuat4d1.y - paramQuat4d1.x * paramQuat4d2.z + paramQuat4d1.z * paramQuat4d2.x;
/* 161 */       this.z = paramQuat4d1.w * paramQuat4d2.z + paramQuat4d2.w * paramQuat4d1.z + paramQuat4d1.x * paramQuat4d2.y - paramQuat4d1.y * paramQuat4d2.x;
/*     */     }
/*     */     else {
/*     */       
/* 165 */       double d3 = paramQuat4d1.w * paramQuat4d2.w - paramQuat4d1.x * paramQuat4d2.x - paramQuat4d1.y * paramQuat4d2.y - paramQuat4d1.z * paramQuat4d2.z;
/* 166 */       double d1 = paramQuat4d1.w * paramQuat4d2.x + paramQuat4d2.w * paramQuat4d1.x + paramQuat4d1.y * paramQuat4d2.z - paramQuat4d1.z * paramQuat4d2.y;
/* 167 */       double d2 = paramQuat4d1.w * paramQuat4d2.y + paramQuat4d2.w * paramQuat4d1.y - paramQuat4d1.x * paramQuat4d2.z + paramQuat4d1.z * paramQuat4d2.x;
/* 168 */       this.z = paramQuat4d1.w * paramQuat4d2.z + paramQuat4d2.w * paramQuat4d1.z + paramQuat4d1.x * paramQuat4d2.y - paramQuat4d1.y * paramQuat4d2.x;
/* 169 */       this.w = d3;
/* 170 */       this.x = d1;
/* 171 */       this.y = d2;
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
/*     */   public final void mul(Quat4d paramQuat4d) {
/* 185 */     double d3 = this.w * paramQuat4d.w - this.x * paramQuat4d.x - this.y * paramQuat4d.y - this.z * paramQuat4d.z;
/* 186 */     double d1 = this.w * paramQuat4d.x + paramQuat4d.w * this.x + this.y * paramQuat4d.z - this.z * paramQuat4d.y;
/* 187 */     double d2 = this.w * paramQuat4d.y + paramQuat4d.w * this.y - this.x * paramQuat4d.z + this.z * paramQuat4d.x;
/* 188 */     this.z = this.w * paramQuat4d.z + paramQuat4d.w * this.z + this.x * paramQuat4d.y - this.y * paramQuat4d.x;
/* 189 */     this.w = d3;
/* 190 */     this.x = d1;
/* 191 */     this.y = d2;
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
/*     */   public final void mulInverse(Quat4d paramQuat4d1, Quat4d paramQuat4d2) {
/* 204 */     Quat4d quat4d = new Quat4d(paramQuat4d2);
/*     */     
/* 206 */     quat4d.inverse();
/* 207 */     mul(paramQuat4d1, quat4d);
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
/*     */   public final void mulInverse(Quat4d paramQuat4d) {
/* 220 */     Quat4d quat4d = new Quat4d(paramQuat4d);
/*     */     
/* 222 */     quat4d.inverse();
/* 223 */     mul(quat4d);
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
/*     */   public final void inverse(Quat4d paramQuat4d) {
/* 235 */     double d = 1.0D / (paramQuat4d.w * paramQuat4d.w + paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z);
/* 236 */     this.w = d * paramQuat4d.w;
/* 237 */     this.x = -d * paramQuat4d.x;
/* 238 */     this.y = -d * paramQuat4d.y;
/* 239 */     this.z = -d * paramQuat4d.z;
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
/* 250 */     double d = 1.0D / (this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
/* 251 */     this.w *= d;
/* 252 */     this.x *= -d;
/* 253 */     this.y *= -d;
/* 254 */     this.z *= -d;
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
/*     */   public final void normalize(Quat4d paramQuat4d) {
/* 267 */     double d = paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z + paramQuat4d.w * paramQuat4d.w;
/*     */     
/* 269 */     if (d > 0.0D) {
/* 270 */       d = 1.0D / Math.sqrt(d);
/* 271 */       this.x = d * paramQuat4d.x;
/* 272 */       this.y = d * paramQuat4d.y;
/* 273 */       this.z = d * paramQuat4d.z;
/* 274 */       this.w = d * paramQuat4d.w;
/*     */     } else {
/* 276 */       this.x = 0.0D;
/* 277 */       this.y = 0.0D;
/* 278 */       this.z = 0.0D;
/* 279 */       this.w = 0.0D;
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
/* 291 */     double d = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
/*     */     
/* 293 */     if (d > 0.0D) {
/* 294 */       d = 1.0D / Math.sqrt(d);
/* 295 */       this.x *= d;
/* 296 */       this.y *= d;
/* 297 */       this.z *= d;
/* 298 */       this.w *= d;
/*     */     } else {
/* 300 */       this.x = 0.0D;
/* 301 */       this.y = 0.0D;
/* 302 */       this.z = 0.0D;
/* 303 */       this.w = 0.0D;
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
/* 315 */     double d = 0.25D * (paramMatrix4f.m00 + paramMatrix4f.m11 + paramMatrix4f.m22 + paramMatrix4f.m33);
/*     */     
/* 317 */     if (d >= 0.0D) {
/* 318 */       if (d >= 1.0E-30D) {
/* 319 */         this.w = Math.sqrt(d);
/* 320 */         d = 0.25D / this.w;
/* 321 */         this.x = (paramMatrix4f.m21 - paramMatrix4f.m12) * d;
/* 322 */         this.y = (paramMatrix4f.m02 - paramMatrix4f.m20) * d;
/* 323 */         this.z = (paramMatrix4f.m10 - paramMatrix4f.m01) * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 327 */       this.w = 0.0D;
/* 328 */       this.x = 0.0D;
/* 329 */       this.y = 0.0D;
/* 330 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 334 */     this.w = 0.0D;
/* 335 */     d = -0.5D * (paramMatrix4f.m11 + paramMatrix4f.m22);
/* 336 */     if (d >= 0.0D) {
/* 337 */       if (d >= 1.0E-30D) {
/* 338 */         this.x = Math.sqrt(d);
/* 339 */         d = 1.0D / 2.0D * this.x;
/* 340 */         this.y = paramMatrix4f.m10 * d;
/* 341 */         this.z = paramMatrix4f.m20 * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 345 */       this.x = 0.0D;
/* 346 */       this.y = 0.0D;
/* 347 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 351 */     this.x = 0.0D;
/* 352 */     d = 0.5D * (1.0D - paramMatrix4f.m22);
/* 353 */     if (d >= 1.0E-30D) {
/* 354 */       this.y = Math.sqrt(d);
/* 355 */       this.z = paramMatrix4f.m21 / 2.0D * this.y;
/*     */       
/*     */       return;
/*     */     } 
/* 359 */     this.y = 0.0D;
/* 360 */     this.z = 1.0D;
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
/* 371 */     double d = 0.25D * (paramMatrix4d.m00 + paramMatrix4d.m11 + paramMatrix4d.m22 + paramMatrix4d.m33);
/*     */     
/* 373 */     if (d >= 0.0D) {
/* 374 */       if (d >= 1.0E-30D) {
/* 375 */         this.w = Math.sqrt(d);
/* 376 */         d = 0.25D / this.w;
/* 377 */         this.x = (paramMatrix4d.m21 - paramMatrix4d.m12) * d;
/* 378 */         this.y = (paramMatrix4d.m02 - paramMatrix4d.m20) * d;
/* 379 */         this.z = (paramMatrix4d.m10 - paramMatrix4d.m01) * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 383 */       this.w = 0.0D;
/* 384 */       this.x = 0.0D;
/* 385 */       this.y = 0.0D;
/* 386 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 390 */     this.w = 0.0D;
/* 391 */     d = -0.5D * (paramMatrix4d.m11 + paramMatrix4d.m22);
/* 392 */     if (d >= 0.0D) {
/* 393 */       if (d >= 1.0E-30D) {
/* 394 */         this.x = Math.sqrt(d);
/* 395 */         d = 0.5D / this.x;
/* 396 */         this.y = paramMatrix4d.m10 * d;
/* 397 */         this.z = paramMatrix4d.m20 * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 401 */       this.x = 0.0D;
/* 402 */       this.y = 0.0D;
/* 403 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 407 */     this.x = 0.0D;
/* 408 */     d = 0.5D * (1.0D - paramMatrix4d.m22);
/* 409 */     if (d >= 1.0E-30D) {
/* 410 */       this.y = Math.sqrt(d);
/* 411 */       this.z = paramMatrix4d.m21 / 2.0D * this.y;
/*     */       
/*     */       return;
/*     */     } 
/* 415 */     this.y = 0.0D;
/* 416 */     this.z = 1.0D;
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
/* 427 */     double d = 0.25D * ((paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22) + 1.0D);
/*     */     
/* 429 */     if (d >= 0.0D) {
/* 430 */       if (d >= 1.0E-30D) {
/* 431 */         this.w = Math.sqrt(d);
/* 432 */         d = 0.25D / this.w;
/* 433 */         this.x = (paramMatrix3f.m21 - paramMatrix3f.m12) * d;
/* 434 */         this.y = (paramMatrix3f.m02 - paramMatrix3f.m20) * d;
/* 435 */         this.z = (paramMatrix3f.m10 - paramMatrix3f.m01) * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 439 */       this.w = 0.0D;
/* 440 */       this.x = 0.0D;
/* 441 */       this.y = 0.0D;
/* 442 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 446 */     this.w = 0.0D;
/* 447 */     d = -0.5D * (paramMatrix3f.m11 + paramMatrix3f.m22);
/* 448 */     if (d >= 0.0D) {
/* 449 */       if (d >= 1.0E-30D) {
/* 450 */         this.x = Math.sqrt(d);
/* 451 */         d = 0.5D / this.x;
/* 452 */         this.y = paramMatrix3f.m10 * d;
/* 453 */         this.z = paramMatrix3f.m20 * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 457 */       this.x = 0.0D;
/* 458 */       this.y = 0.0D;
/* 459 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 463 */     this.x = 0.0D;
/* 464 */     d = 0.5D * (1.0D - paramMatrix3f.m22);
/* 465 */     if (d >= 1.0E-30D) {
/* 466 */       this.y = Math.sqrt(d);
/* 467 */       this.z = paramMatrix3f.m21 / 2.0D * this.y;
/*     */     } 
/*     */     
/* 470 */     this.y = 0.0D;
/* 471 */     this.z = 1.0D;
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
/* 482 */     double d = 0.25D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 + 1.0D);
/*     */     
/* 484 */     if (d >= 0.0D) {
/* 485 */       if (d >= 1.0E-30D) {
/* 486 */         this.w = Math.sqrt(d);
/* 487 */         d = 0.25D / this.w;
/* 488 */         this.x = (paramMatrix3d.m21 - paramMatrix3d.m12) * d;
/* 489 */         this.y = (paramMatrix3d.m02 - paramMatrix3d.m20) * d;
/* 490 */         this.z = (paramMatrix3d.m10 - paramMatrix3d.m01) * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 494 */       this.w = 0.0D;
/* 495 */       this.x = 0.0D;
/* 496 */       this.y = 0.0D;
/* 497 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 501 */     this.w = 0.0D;
/* 502 */     d = -0.5D * (paramMatrix3d.m11 + paramMatrix3d.m22);
/* 503 */     if (d >= 0.0D) {
/* 504 */       if (d >= 1.0E-30D) {
/* 505 */         this.x = Math.sqrt(d);
/* 506 */         d = 0.5D / this.x;
/* 507 */         this.y = paramMatrix3d.m10 * d;
/* 508 */         this.z = paramMatrix3d.m20 * d;
/*     */         return;
/*     */       } 
/*     */     } else {
/* 512 */       this.x = 0.0D;
/* 513 */       this.y = 0.0D;
/* 514 */       this.z = 1.0D;
/*     */       
/*     */       return;
/*     */     } 
/* 518 */     this.x = 0.0D;
/* 519 */     d = 0.5D * (1.0D - paramMatrix3d.m22);
/* 520 */     if (d >= 1.0E-30D) {
/* 521 */       this.y = Math.sqrt(d);
/* 522 */       this.z = paramMatrix3d.m21 / 2.0D * this.y;
/*     */       
/*     */       return;
/*     */     } 
/* 526 */     this.y = 0.0D;
/* 527 */     this.z = 1.0D;
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
/*     */   public final void set(AxisAngle4f paramAxisAngle4f) {
/* 541 */     double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/* 542 */     if (d < 1.0E-6D) {
/* 543 */       this.w = 0.0D;
/* 544 */       this.x = 0.0D;
/* 545 */       this.y = 0.0D;
/* 546 */       this.z = 0.0D;
/*     */     } else {
/* 548 */       double d1 = Math.sin(paramAxisAngle4f.angle / 2.0D);
/* 549 */       d = 1.0D / d;
/* 550 */       this.w = Math.cos(paramAxisAngle4f.angle / 2.0D);
/* 551 */       this.x = paramAxisAngle4f.x * d * d1;
/* 552 */       this.y = paramAxisAngle4f.y * d * d1;
/* 553 */       this.z = paramAxisAngle4f.z * d * d1;
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
/* 568 */     double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/* 569 */     if (d < 1.0E-6D) {
/* 570 */       this.w = 0.0D;
/* 571 */       this.x = 0.0D;
/* 572 */       this.y = 0.0D;
/* 573 */       this.z = 0.0D;
/*     */     } else {
/* 575 */       d = 1.0D / d;
/* 576 */       double d1 = Math.sin(paramAxisAngle4d.angle / 2.0D);
/* 577 */       this.w = Math.cos(paramAxisAngle4d.angle / 2.0D);
/* 578 */       this.x = paramAxisAngle4d.x * d * d1;
/* 579 */       this.y = paramAxisAngle4d.y * d * d1;
/* 580 */       this.z = paramAxisAngle4d.z * d * d1;
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
/*     */   public final void interpolate(Quat4d paramQuat4d, double paramDouble) {
/* 601 */     double d3, d2, d1 = this.x * paramQuat4d.x + this.y * paramQuat4d.y + this.z * paramQuat4d.z + this.w * paramQuat4d.w;
/*     */     
/* 603 */     if (d1 < 0.0D) {
/*     */       
/* 605 */       paramQuat4d.x = -paramQuat4d.x; paramQuat4d.y = -paramQuat4d.y; paramQuat4d.z = -paramQuat4d.z; paramQuat4d.w = -paramQuat4d.w;
/* 606 */       d1 = -d1;
/*     */     } 
/*     */     
/* 609 */     if (1.0D - d1 > 1.0E-6D) {
/* 610 */       double d4 = Math.acos(d1);
/* 611 */       double d5 = Math.sin(d4);
/* 612 */       d2 = Math.sin((1.0D - paramDouble) * d4) / d5;
/* 613 */       d3 = Math.sin(paramDouble * d4) / d5;
/*     */     } else {
/* 615 */       d2 = 1.0D - paramDouble;
/* 616 */       d3 = paramDouble;
/*     */     } 
/*     */     
/* 619 */     this.w = d2 * this.w + d3 * paramQuat4d.w;
/* 620 */     this.x = d2 * this.x + d3 * paramQuat4d.x;
/* 621 */     this.y = d2 * this.y + d3 * paramQuat4d.y;
/* 622 */     this.z = d2 * this.z + d3 * paramQuat4d.z;
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
/*     */   public final void interpolate(Quat4d paramQuat4d1, Quat4d paramQuat4d2, double paramDouble) {
/* 641 */     double d3, d2, d1 = paramQuat4d2.x * paramQuat4d1.x + paramQuat4d2.y * paramQuat4d1.y + paramQuat4d2.z * paramQuat4d1.z + paramQuat4d2.w * paramQuat4d1.w;
/*     */     
/* 643 */     if (d1 < 0.0D) {
/*     */       
/* 645 */       paramQuat4d1.x = -paramQuat4d1.x; paramQuat4d1.y = -paramQuat4d1.y; paramQuat4d1.z = -paramQuat4d1.z; paramQuat4d1.w = -paramQuat4d1.w;
/* 646 */       d1 = -d1;
/*     */     } 
/*     */     
/* 649 */     if (1.0D - d1 > 1.0E-6D) {
/* 650 */       double d4 = Math.acos(d1);
/* 651 */       double d5 = Math.sin(d4);
/* 652 */       d2 = Math.sin((1.0D - paramDouble) * d4) / d5;
/* 653 */       d3 = Math.sin(paramDouble * d4) / d5;
/*     */     } else {
/* 655 */       d2 = 1.0D - paramDouble;
/* 656 */       d3 = paramDouble;
/*     */     } 
/* 658 */     this.w = d2 * paramQuat4d1.w + d3 * paramQuat4d2.w;
/* 659 */     this.x = d2 * paramQuat4d1.x + d3 * paramQuat4d2.x;
/* 660 */     this.y = d2 * paramQuat4d1.y + d3 * paramQuat4d2.y;
/* 661 */     this.z = d2 * paramQuat4d1.z + d3 * paramQuat4d2.z;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Quat4d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */