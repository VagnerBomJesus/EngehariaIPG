/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Vector3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HiResCoord
/*     */ {
/*     */   int[] x;
/*     */   int[] y;
/*     */   int[] z;
/*  65 */   private double[] scales = { 7.922816251426434E28D, 1.8446744073709552E19D, 4.294967296E9D, 1.0D, 2.3283064365386963E-10D, 5.421010862427522E-20D, 1.2621774483536189E-29D, 2.9387358770557188E-39D };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HiResCoord(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/*  88 */     this.x = new int[8];
/*  89 */     this.y = new int[8];
/*  90 */     this.z = new int[8];
/*     */     
/*  92 */     for (byte b = 0; b < 8; b++) {
/*  93 */       this.x[b] = paramArrayOfInt1[b];
/*  94 */       this.y[b] = paramArrayOfInt2[b];
/*  95 */       this.z[b] = paramArrayOfInt3[b];
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
/*     */   public HiResCoord(HiResCoord paramHiResCoord) {
/* 107 */     this.x = new int[8];
/* 108 */     this.y = new int[8];
/* 109 */     this.z = new int[8];
/*     */     
/* 111 */     this.x[0] = paramHiResCoord.x[0];
/* 112 */     this.y[0] = paramHiResCoord.y[0];
/* 113 */     this.z[0] = paramHiResCoord.z[0];
/*     */     
/* 115 */     this.x[1] = paramHiResCoord.x[1];
/* 116 */     this.y[1] = paramHiResCoord.y[1];
/* 117 */     this.z[1] = paramHiResCoord.z[1];
/*     */     
/* 119 */     this.x[2] = paramHiResCoord.x[2];
/* 120 */     this.y[2] = paramHiResCoord.y[2];
/* 121 */     this.z[2] = paramHiResCoord.z[2];
/*     */     
/* 123 */     this.x[3] = paramHiResCoord.x[3];
/* 124 */     this.y[3] = paramHiResCoord.y[3];
/* 125 */     this.z[3] = paramHiResCoord.z[3];
/*     */     
/* 127 */     this.x[4] = paramHiResCoord.x[4];
/* 128 */     this.y[4] = paramHiResCoord.y[4];
/* 129 */     this.z[4] = paramHiResCoord.z[4];
/*     */     
/* 131 */     this.x[5] = paramHiResCoord.x[5];
/* 132 */     this.y[5] = paramHiResCoord.y[5];
/* 133 */     this.z[5] = paramHiResCoord.z[5];
/*     */     
/* 135 */     this.x[6] = paramHiResCoord.x[6];
/* 136 */     this.y[6] = paramHiResCoord.y[6];
/* 137 */     this.z[6] = paramHiResCoord.z[6];
/*     */     
/* 139 */     this.x[7] = paramHiResCoord.x[7];
/* 140 */     this.y[7] = paramHiResCoord.y[7];
/* 141 */     this.z[7] = paramHiResCoord.z[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HiResCoord() {
/* 149 */     this.x = new int[8];
/* 150 */     this.y = new int[8];
/* 151 */     this.z = new int[8];
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
/*     */   public void setHiResCoord(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 164 */     for (byte b = 0; b < 8; b++) {
/* 165 */       this.x[b] = paramArrayOfInt1[b];
/* 166 */       this.y[b] = paramArrayOfInt2[b];
/* 167 */       this.z[b] = paramArrayOfInt3[b];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHiResCoord(HiResCoord paramHiResCoord) {
/* 178 */     this.x[0] = paramHiResCoord.x[0];
/* 179 */     this.y[0] = paramHiResCoord.y[0];
/* 180 */     this.z[0] = paramHiResCoord.z[0];
/*     */     
/* 182 */     this.x[1] = paramHiResCoord.x[1];
/* 183 */     this.y[1] = paramHiResCoord.y[1];
/* 184 */     this.z[1] = paramHiResCoord.z[1];
/*     */     
/* 186 */     this.x[2] = paramHiResCoord.x[2];
/* 187 */     this.y[2] = paramHiResCoord.y[2];
/* 188 */     this.z[2] = paramHiResCoord.z[2];
/*     */     
/* 190 */     this.x[3] = paramHiResCoord.x[3];
/* 191 */     this.y[3] = paramHiResCoord.y[3];
/* 192 */     this.z[3] = paramHiResCoord.z[3];
/*     */     
/* 194 */     this.x[4] = paramHiResCoord.x[4];
/* 195 */     this.y[4] = paramHiResCoord.y[4];
/* 196 */     this.z[4] = paramHiResCoord.z[4];
/*     */     
/* 198 */     this.x[5] = paramHiResCoord.x[5];
/* 199 */     this.y[5] = paramHiResCoord.y[5];
/* 200 */     this.z[5] = paramHiResCoord.z[5];
/*     */     
/* 202 */     this.x[6] = paramHiResCoord.x[6];
/* 203 */     this.y[6] = paramHiResCoord.y[6];
/* 204 */     this.z[6] = paramHiResCoord.z[6];
/*     */     
/* 206 */     this.x[7] = paramHiResCoord.x[7];
/* 207 */     this.y[7] = paramHiResCoord.y[7];
/* 208 */     this.z[7] = paramHiResCoord.z[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHiResCoordX(int[] paramArrayOfInt) {
/* 217 */     this.x[0] = paramArrayOfInt[0];
/* 218 */     this.x[1] = paramArrayOfInt[1];
/* 219 */     this.x[2] = paramArrayOfInt[2];
/* 220 */     this.x[3] = paramArrayOfInt[3];
/* 221 */     this.x[4] = paramArrayOfInt[4];
/* 222 */     this.x[5] = paramArrayOfInt[5];
/* 223 */     this.x[6] = paramArrayOfInt[6];
/* 224 */     this.x[7] = paramArrayOfInt[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHiResCoordY(int[] paramArrayOfInt) {
/* 232 */     this.y[0] = paramArrayOfInt[0];
/* 233 */     this.y[1] = paramArrayOfInt[1];
/* 234 */     this.y[2] = paramArrayOfInt[2];
/* 235 */     this.y[3] = paramArrayOfInt[3];
/* 236 */     this.y[4] = paramArrayOfInt[4];
/* 237 */     this.y[5] = paramArrayOfInt[5];
/* 238 */     this.y[6] = paramArrayOfInt[6];
/* 239 */     this.y[7] = paramArrayOfInt[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHiResCoordZ(int[] paramArrayOfInt) {
/* 247 */     this.z[0] = paramArrayOfInt[0];
/* 248 */     this.z[1] = paramArrayOfInt[1];
/* 249 */     this.z[2] = paramArrayOfInt[2];
/* 250 */     this.z[3] = paramArrayOfInt[3];
/* 251 */     this.z[4] = paramArrayOfInt[4];
/* 252 */     this.z[5] = paramArrayOfInt[5];
/* 253 */     this.z[6] = paramArrayOfInt[6];
/* 254 */     this.z[7] = paramArrayOfInt[7];
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
/*     */   public void getHiResCoord(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 266 */     paramArrayOfInt1[0] = this.x[0];
/* 267 */     paramArrayOfInt1[1] = this.x[1];
/* 268 */     paramArrayOfInt1[2] = this.x[2];
/* 269 */     paramArrayOfInt1[3] = this.x[3];
/* 270 */     paramArrayOfInt1[4] = this.x[4];
/* 271 */     paramArrayOfInt1[5] = this.x[5];
/* 272 */     paramArrayOfInt1[6] = this.x[6];
/* 273 */     paramArrayOfInt1[7] = this.x[7];
/*     */     
/* 275 */     paramArrayOfInt2[0] = this.y[0];
/* 276 */     paramArrayOfInt2[1] = this.y[1];
/* 277 */     paramArrayOfInt2[2] = this.y[2];
/* 278 */     paramArrayOfInt2[3] = this.y[3];
/* 279 */     paramArrayOfInt2[4] = this.y[4];
/* 280 */     paramArrayOfInt2[5] = this.y[5];
/* 281 */     paramArrayOfInt2[6] = this.y[6];
/* 282 */     paramArrayOfInt2[7] = this.y[7];
/*     */     
/* 284 */     paramArrayOfInt3[0] = this.z[0];
/* 285 */     paramArrayOfInt3[1] = this.z[1];
/* 286 */     paramArrayOfInt3[2] = this.z[2];
/* 287 */     paramArrayOfInt3[3] = this.z[3];
/* 288 */     paramArrayOfInt3[4] = this.z[4];
/* 289 */     paramArrayOfInt3[5] = this.z[5];
/* 290 */     paramArrayOfInt3[6] = this.z[6];
/* 291 */     paramArrayOfInt3[7] = this.z[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getHiResCoord(HiResCoord paramHiResCoord) {
/* 300 */     paramHiResCoord.x[0] = this.x[0];
/* 301 */     paramHiResCoord.x[1] = this.x[1];
/* 302 */     paramHiResCoord.x[2] = this.x[2];
/* 303 */     paramHiResCoord.x[3] = this.x[3];
/* 304 */     paramHiResCoord.x[4] = this.x[4];
/* 305 */     paramHiResCoord.x[5] = this.x[5];
/* 306 */     paramHiResCoord.x[6] = this.x[6];
/* 307 */     paramHiResCoord.x[7] = this.x[7];
/*     */     
/* 309 */     paramHiResCoord.y[0] = this.y[0];
/* 310 */     paramHiResCoord.y[1] = this.y[1];
/* 311 */     paramHiResCoord.y[2] = this.y[2];
/* 312 */     paramHiResCoord.y[3] = this.y[3];
/* 313 */     paramHiResCoord.y[4] = this.y[4];
/* 314 */     paramHiResCoord.y[5] = this.y[5];
/* 315 */     paramHiResCoord.y[6] = this.y[6];
/* 316 */     paramHiResCoord.y[7] = this.y[7];
/*     */     
/* 318 */     paramHiResCoord.z[0] = this.z[0];
/* 319 */     paramHiResCoord.z[1] = this.z[1];
/* 320 */     paramHiResCoord.z[2] = this.z[2];
/* 321 */     paramHiResCoord.z[3] = this.z[3];
/* 322 */     paramHiResCoord.z[4] = this.z[4];
/* 323 */     paramHiResCoord.z[5] = this.z[5];
/* 324 */     paramHiResCoord.z[6] = this.z[6];
/* 325 */     paramHiResCoord.z[7] = this.z[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getHiResCoordX(int[] paramArrayOfInt) {
/* 334 */     paramArrayOfInt[0] = this.x[0];
/* 335 */     paramArrayOfInt[1] = this.x[1];
/* 336 */     paramArrayOfInt[2] = this.x[2];
/* 337 */     paramArrayOfInt[3] = this.x[3];
/* 338 */     paramArrayOfInt[4] = this.x[4];
/* 339 */     paramArrayOfInt[5] = this.x[5];
/* 340 */     paramArrayOfInt[6] = this.x[6];
/* 341 */     paramArrayOfInt[7] = this.x[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getHiResCoordY(int[] paramArrayOfInt) {
/* 350 */     paramArrayOfInt[0] = this.y[0];
/* 351 */     paramArrayOfInt[1] = this.y[1];
/* 352 */     paramArrayOfInt[2] = this.y[2];
/* 353 */     paramArrayOfInt[3] = this.y[3];
/* 354 */     paramArrayOfInt[4] = this.y[4];
/* 355 */     paramArrayOfInt[5] = this.y[5];
/* 356 */     paramArrayOfInt[6] = this.y[6];
/* 357 */     paramArrayOfInt[7] = this.y[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getHiResCoordZ(int[] paramArrayOfInt) {
/* 366 */     paramArrayOfInt[0] = this.z[0];
/* 367 */     paramArrayOfInt[1] = this.z[1];
/* 368 */     paramArrayOfInt[2] = this.z[2];
/* 369 */     paramArrayOfInt[3] = this.z[3];
/* 370 */     paramArrayOfInt[4] = this.z[4];
/* 371 */     paramArrayOfInt[5] = this.z[5];
/* 372 */     paramArrayOfInt[6] = this.z[6];
/* 373 */     paramArrayOfInt[7] = this.z[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(HiResCoord paramHiResCoord) {
/*     */     try {
/* 383 */       return (this.x[0] == paramHiResCoord.x[0] && this.x[1] == paramHiResCoord.x[1] && this.x[2] == paramHiResCoord.x[2] && this.x[3] == paramHiResCoord.x[3] && this.x[4] == paramHiResCoord.x[4] && this.x[5] == paramHiResCoord.x[5] && this.x[6] == paramHiResCoord.x[6] && this.x[7] == paramHiResCoord.x[7] && this.y[0] == paramHiResCoord.y[0] && this.y[1] == paramHiResCoord.y[1] && this.y[2] == paramHiResCoord.y[2] && this.y[3] == paramHiResCoord.y[3] && this.y[4] == paramHiResCoord.y[4] && this.y[5] == paramHiResCoord.y[5] && this.y[6] == paramHiResCoord.y[6] && this.y[7] == paramHiResCoord.y[7] && this.z[0] == paramHiResCoord.z[0] && this.z[1] == paramHiResCoord.z[1] && this.z[2] == paramHiResCoord.z[2] && this.z[3] == paramHiResCoord.z[3] && this.z[4] == paramHiResCoord.z[4] && this.z[5] == paramHiResCoord.z[5] && this.z[6] == paramHiResCoord.z[6] && this.z[7] == paramHiResCoord.z[7]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     catch (NullPointerException nullPointerException) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 408 */       return false;
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
/* 421 */     try { HiResCoord hiResCoord = (HiResCoord)paramObject;
/* 422 */       return (this.x[0] == hiResCoord.x[0] && this.x[1] == hiResCoord.x[1] && this.x[2] == hiResCoord.x[2] && this.x[3] == hiResCoord.x[3] && this.x[4] == hiResCoord.x[4] && this.x[5] == hiResCoord.x[5] && this.x[6] == hiResCoord.x[6] && this.x[7] == hiResCoord.x[7] && this.y[0] == hiResCoord.y[0] && this.y[1] == hiResCoord.y[1] && this.y[2] == hiResCoord.y[2] && this.y[3] == hiResCoord.y[3] && this.y[4] == hiResCoord.y[4] && this.y[5] == hiResCoord.y[5] && this.y[6] == hiResCoord.y[6] && this.y[7] == hiResCoord.y[7] && this.z[0] == hiResCoord.z[0] && this.z[1] == hiResCoord.z[1] && this.z[2] == hiResCoord.z[2] && this.z[3] == hiResCoord.z[3] && this.z[4] == hiResCoord.z[4] && this.z[5] == hiResCoord.z[5] && this.z[6] == hiResCoord.z[6] && this.z[7] == hiResCoord.z[7]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */        }
/*     */     
/*     */     catch (NullPointerException nullPointerException)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 447 */       return false; }
/* 448 */     catch (ClassCastException classCastException) { return false; }
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
/* 461 */   public void add(HiResCoord paramHiResCoord1, HiResCoord paramHiResCoord2) { hiResAdd(this, paramHiResCoord1, paramHiResCoord2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sub(HiResCoord paramHiResCoord1, HiResCoord paramHiResCoord2) {
/* 471 */     HiResCoord hiResCoord = new HiResCoord();
/*     */ 
/*     */ 
/*     */     
/* 475 */     hiResNegate(hiResCoord, paramHiResCoord2);
/* 476 */     hiResAdd(this, paramHiResCoord1, hiResCoord);
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
/* 487 */   public void negate(HiResCoord paramHiResCoord) { hiResNegate(this, paramHiResCoord); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public void negate() { hiResNegate(this, this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(int paramInt, HiResCoord paramHiResCoord) {
/* 507 */     hiResScale(paramHiResCoord.x, this.x, paramInt);
/* 508 */     hiResScale(paramHiResCoord.y, this.y, paramInt);
/* 509 */     hiResScale(paramHiResCoord.z, this.z, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(int paramInt) {
/* 517 */     hiResScale(this.x, this.x, paramInt);
/* 518 */     hiResScale(this.y, this.y, paramInt);
/* 519 */     hiResScale(this.z, this.z, paramInt);
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
/* 534 */   public void difference(HiResCoord paramHiResCoord, Vector3d paramVector3d) { hiResDiff(this, paramHiResCoord, paramVector3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distance(HiResCoord paramHiResCoord) {
/* 544 */     Vector3d vector3d = new Vector3d();
/*     */     
/* 546 */     hiResDiff(this, paramHiResCoord, vector3d);
/*     */     
/* 548 */     return Math.sqrt(vector3d.x * vector3d.x + vector3d.y * vector3d.y + vector3d.z * vector3d.z);
/*     */   }
/*     */ 
/*     */   
/*     */   private void hiResNegate(HiResCoord paramHiResCoord1, HiResCoord paramHiResCoord2) {
/* 553 */     negateCoord(paramHiResCoord1.x, paramHiResCoord2.x);
/* 554 */     negateCoord(paramHiResCoord1.y, paramHiResCoord2.y);
/* 555 */     negateCoord(paramHiResCoord1.z, paramHiResCoord2.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void negateCoord(int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/*     */     byte b;
/* 563 */     for (b = 0; b < 8; b++) {
/* 564 */       paramArrayOfInt1[b] = paramArrayOfInt2[b] ^ 0xFFFFFFFF;
/*     */     }
/*     */     
/* 567 */     for (b = 7; b >= 0; b--) {
/* 568 */       if (paramArrayOfInt1[b] == -1) {
/* 569 */         paramArrayOfInt1[b] = 0;
/*     */       } else {
/* 571 */         paramArrayOfInt1[b] = paramArrayOfInt1[b] + 1;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void hiResAdd(HiResCoord paramHiResCoord1, HiResCoord paramHiResCoord2, HiResCoord paramHiResCoord3) {
/* 581 */     long l2 = 2147483647L;
/* 582 */     long l3 = 1L;
/* 583 */     l3 <<= 31;
/* 584 */     long l4 = 2147483647L;
/* 585 */     l4 <<= true;
/* 586 */     l4++;
/*     */ 
/*     */     
/* 589 */     long l1 = 0L; byte b;
/* 590 */     for (b = 7; b > 0; b--) {
/* 591 */       long l5 = 0L;
/* 592 */       l5 = l2 & paramHiResCoord2.x[b];
/* 593 */       if (paramHiResCoord2.x[b] < 0) l5 |= l3;
/*     */       
/* 595 */       long l6 = 0L;
/* 596 */       l6 = l2 & paramHiResCoord3.x[b];
/* 597 */       if (paramHiResCoord3.x[b] < 0) l6 |= l3;
/*     */       
/* 599 */       l6 = l6 + l5 + l1;
/* 600 */       l1 = l6 >> 32;
/* 601 */       paramHiResCoord1.x[b] = (int)(l6 & l4);
/*     */     } 
/* 603 */     paramHiResCoord1.x[0] = paramHiResCoord2.x[0] + paramHiResCoord3.x[0] + (int)l1;
/*     */ 
/*     */     
/* 606 */     l1 = 0L;
/* 607 */     for (b = 7; b > 0; b--) {
/* 608 */       long l5 = 0L;
/* 609 */       l5 = l2 & paramHiResCoord2.y[b];
/* 610 */       if (paramHiResCoord2.y[b] < 0) l5 |= l3;
/*     */       
/* 612 */       long l6 = 0L;
/* 613 */       l6 = l2 & paramHiResCoord3.y[b];
/* 614 */       if (paramHiResCoord3.y[b] < 0) l6 |= l3;
/*     */       
/* 616 */       l6 = l6 + l5 + l1;
/* 617 */       l1 = l6 >> 32;
/* 618 */       paramHiResCoord1.y[b] = (int)(l6 & l4);
/*     */     } 
/* 620 */     paramHiResCoord1.y[0] = paramHiResCoord2.y[0] + paramHiResCoord3.y[0] + (int)l1;
/*     */     
/* 622 */     l1 = 0L;
/* 623 */     for (b = 7; b > 0; b--) {
/* 624 */       long l5 = 0L;
/* 625 */       l5 = l2 & paramHiResCoord2.z[b];
/* 626 */       if (paramHiResCoord2.z[b] < 0) l5 |= l3;
/*     */       
/* 628 */       long l6 = 0L;
/* 629 */       l6 = l2 & paramHiResCoord3.z[b];
/* 630 */       if (paramHiResCoord3.z[b] < 0) l6 |= l3;
/*     */       
/* 632 */       l6 = l6 + l5 + l1;
/* 633 */       l1 = l6 >> 32;
/* 634 */       paramHiResCoord1.z[b] = (int)(l6 & l4);
/*     */     } 
/* 636 */     paramHiResCoord1.z[0] = paramHiResCoord2.z[0] + paramHiResCoord3.z[0] + (int)l1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void hiResScale(int[] paramArrayOfInt1, int[] paramArrayOfInt2, double paramDouble) {
/* 643 */     int i = Integer.MAX_VALUE;
/* 644 */     long l2 = 2147483647L;
/* 645 */     l2 <<= true;
/* 646 */     l2++;
/* 647 */     long l3 = 1L;
/* 648 */     l3 <<= 31;
/*     */     
/* 650 */     long l1 = 0L;
/* 651 */     for (byte b = 7; b > 0; b--) {
/* 652 */       long l = 0L;
/* 653 */       l = (i & paramArrayOfInt1[b]);
/* 654 */       if (paramArrayOfInt1[b] < 0) l |= l3; 
/* 655 */       l = (long)(l * paramDouble + l1);
/* 656 */       l1 = l >> 32;
/* 657 */       paramArrayOfInt2[b] = (int)(l & l2);
/*     */     } 
/* 659 */     paramArrayOfInt2[0] = (int)(paramArrayOfInt1[0] * paramDouble + l1);
/*     */   }
/*     */   
/*     */   private void hiResDiff(HiResCoord paramHiResCoord1, HiResCoord paramHiResCoord2, Vector3d paramVector3d) {
/*     */     int[] arrayOfInt2;
/* 664 */     HiResCoord hiResCoord = new HiResCoord();
/*     */     
/* 666 */     int[] arrayOfInt1 = new int[8];
/*     */     
/* 668 */     int i = Integer.MAX_VALUE;
/* 669 */     long l = 1L;
/* 670 */     l <<= 31;
/*     */ 
/*     */ 
/*     */     
/* 674 */     hiResNegate(hiResCoord, paramHiResCoord2);
/* 675 */     hiResAdd(hiResCoord, paramHiResCoord1, hiResCoord);
/*     */ 
/*     */     
/* 678 */     if (hiResCoord.x[0] < 0) {
/* 679 */       arrayOfInt2 = arrayOfInt1;
/* 680 */       negateCoord(arrayOfInt2, hiResCoord.x);
/*     */     } else {
/* 682 */       arrayOfInt2 = hiResCoord.x;
/*     */     } 
/* 684 */     paramVector3d.x = 0.0D; byte b;
/* 685 */     for (b = 7; b > 0; b--) {
/* 686 */       long l1 = (arrayOfInt2[b] & i);
/* 687 */       if (arrayOfInt2[b] < 0) l1 |= l; 
/* 688 */       paramVector3d.x += this.scales[b] * l1;
/*     */     } 
/* 690 */     paramVector3d.x += this.scales[0] * arrayOfInt2[0];
/* 691 */     if (hiResCoord.x[0] < 0) paramVector3d.x = -paramVector3d.x;
/*     */     
/* 693 */     if (hiResCoord.y[0] < 0) {
/* 694 */       arrayOfInt2 = arrayOfInt1;
/* 695 */       negateCoord(arrayOfInt2, hiResCoord.y);
/*     */     } else {
/* 697 */       arrayOfInt2 = hiResCoord.y;
/*     */     } 
/* 699 */     paramVector3d.y = 0.0D;
/* 700 */     for (b = 7; b > 0; b--) {
/* 701 */       long l1 = (arrayOfInt2[b] & i);
/* 702 */       if (arrayOfInt2[b] < 0) l1 |= l; 
/* 703 */       paramVector3d.y += this.scales[b] * l1;
/*     */     } 
/* 705 */     paramVector3d.y += this.scales[0] * arrayOfInt2[0];
/* 706 */     if (hiResCoord.y[0] < 0) paramVector3d.y = -paramVector3d.y;
/*     */     
/* 708 */     if (hiResCoord.z[0] < 0) {
/* 709 */       arrayOfInt2 = arrayOfInt1;
/* 710 */       negateCoord(arrayOfInt2, hiResCoord.z);
/*     */     } else {
/* 712 */       arrayOfInt2 = hiResCoord.z;
/*     */     } 
/* 714 */     paramVector3d.z = 0.0D;
/* 715 */     for (b = 7; b > 0; b--) {
/* 716 */       long l1 = (arrayOfInt2[b] & i);
/* 717 */       if (arrayOfInt2[b] < 0) l1 |= l; 
/* 718 */       paramVector3d.z += this.scales[b] * l1;
/*     */     } 
/* 720 */     paramVector3d.z += this.scales[0] * arrayOfInt2[0];
/* 721 */     if (hiResCoord.z[0] < 0) paramVector3d.z = -paramVector3d.z; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\HiResCoord.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */