/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Quadrics
/*     */ {
/*     */   GeomBuffer disk(double paramDouble1, int paramInt, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2) {
/*     */     double d2;
/*  63 */     if (paramBoolean1) { d2 = 1.0D; }
/*  64 */     else { d2 = -1.0D; }
/*     */     
/*  66 */     double d1 = 6.283185307179586D / paramInt;
/*     */     
/*  68 */     GeomBuffer geomBuffer = new GeomBuffer(paramInt + 2);
/*     */     
/*  70 */     geomBuffer.begin(16);
/*  71 */     geomBuffer.normal3d(0.0D, 1.0D * d2, 0.0D);
/*  72 */     geomBuffer.texCoord2d(0.5D, 0.5D);
/*  73 */     geomBuffer.vertex3d(0.0D, paramDouble2, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (!paramBoolean1) {
/*  83 */       for (byte b = 0; b <= paramInt; b++) {
/*  84 */         double d3 = b * d1;
/*     */         
/*  86 */         double d4 = Math.sin(d3 - 1.5707963267948966D);
/*  87 */         double d5 = Math.cos(d3 - 1.5707963267948966D);
/*  88 */         geomBuffer.normal3d(0.0D, 1.0D * d2, 0.0D);
/*  89 */         if (paramBoolean2) {
/*  90 */           geomBuffer.texCoord2d(0.5D + d5 * 0.5D, 1.0D - 0.5D + d4 * 0.5D);
/*     */         } else {
/*     */           
/*  93 */           geomBuffer.texCoord2d(0.5D + d5 * 0.5D, 0.5D + d4 * 0.5D);
/*     */         } 
/*  95 */         geomBuffer.vertex3d(paramDouble1 * d5, paramDouble2, paramDouble1 * d4);
/*     */       } 
/*     */     } else {
/*  98 */       for (int i = paramInt; i >= 0; i--) {
/*  99 */         double d3 = i * d1;
/*     */         
/* 101 */         double d4 = Math.sin(d3 - 1.5707963267948966D);
/* 102 */         double d5 = Math.cos(d3 - 1.5707963267948966D);
/* 103 */         geomBuffer.normal3d(0.0D, 1.0D * d2, 0.0D);
/* 104 */         if (paramBoolean2) {
/* 105 */           geomBuffer.texCoord2d(0.5D + d5 * 0.5D, 1.0D - 0.5D - d4 * 0.5D);
/*     */         } else {
/*     */           
/* 108 */           geomBuffer.texCoord2d(0.5D + d5 * 0.5D, 0.5D - d4 * 0.5D);
/*     */         } 
/* 110 */         geomBuffer.vertex3d(d5 * paramDouble1, paramDouble2, d4 * paramDouble1);
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     geomBuffer.end();
/* 115 */     return geomBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeomBuffer cylinder(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
/*     */     double d1;
/* 126 */     if (paramBoolean1) { d1 = 1.0D; }
/* 127 */     else { d1 = -1.0D; }
/*     */ 
/*     */     
/* 130 */     double d2 = 6.283185307179586D / paramInt1;
/* 131 */     double d3 = paramDouble1 / paramInt2;
/* 132 */     double d4 = 1.0D / paramInt1;
/* 133 */     double d5 = 1.0D / paramInt2;
/*     */     
/* 135 */     GeomBuffer geomBuffer = new GeomBuffer(paramInt2 * 2 * (paramInt1 + 1));
/*     */     
/* 137 */     double d6 = 0.0D, d7 = 0.0D;
/*     */     
/* 139 */     double d8 = -paramDouble1 / 2.0D;
/*     */ 
/*     */     
/* 142 */     geomBuffer.begin(1);
/*     */     
/* 144 */     for (byte b = 0; b < paramInt2; b++) {
/* 145 */       double d = d8 + d3;
/* 146 */       if (paramBoolean1) {
/* 147 */         double d9 = Math.cos(paramInt1 * d2 - 1.5707963267948966D);
/* 148 */         double d10 = Math.sin(paramInt1 * d2 - 1.5707963267948966D);
/* 149 */         double d11 = Math.cos((paramInt1 - 1) * d2 - 1.5707963267948966D);
/* 150 */         double d12 = Math.sin((paramInt1 - 1) * d2 - 1.5707963267948966D);
/*     */ 
/*     */         
/* 153 */         geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 154 */         if (paramBoolean2) {
/* 155 */           geomBuffer.texCoord2d(d6, 1.0D - d7 + d5);
/*     */         } else {
/*     */           
/* 158 */           geomBuffer.texCoord2d(d6, d7 + d5);
/*     */         } 
/* 160 */         geomBuffer.vertex3d(d9 * paramDouble2, d, d10 * paramDouble2);
/*     */ 
/*     */         
/* 163 */         geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 164 */         if (paramBoolean2) {
/* 165 */           geomBuffer.texCoord2d(d6, 1.0D - d7);
/*     */         } else {
/* 167 */           geomBuffer.texCoord2d(d6, d7);
/*     */         } 
/* 169 */         geomBuffer.vertex3d(d9 * paramDouble2, d8, d10 * paramDouble2);
/*     */ 
/*     */         
/* 172 */         geomBuffer.normal3d(d11 * d1, 0.0D, d12 * d1);
/* 173 */         if (paramBoolean2) {
/* 174 */           geomBuffer.texCoord2d(d6 + d4, 1.0D - d7 + d5);
/*     */         } else {
/*     */           
/* 177 */           geomBuffer.texCoord2d(d6 + d4, d7 + d5);
/*     */         } 
/* 179 */         geomBuffer.vertex3d(d11 * paramDouble2, d, d12 * paramDouble2);
/*     */ 
/*     */         
/* 182 */         geomBuffer.normal3d(d11 * d1, 0.0D, d12 * d1);
/* 183 */         if (paramBoolean2) {
/* 184 */           geomBuffer.texCoord2d(d6 + d4, 1.0D - d7);
/*     */         } else {
/*     */           
/* 187 */           geomBuffer.texCoord2d(d6 + d4, d7);
/*     */         } 
/* 189 */         geomBuffer.vertex3d(d11 * paramDouble2, d8, d12 * paramDouble2);
/*     */         
/* 191 */         d6 += d4 * 2.0D;
/*     */         
/* 193 */         for (int i = paramInt1 - 2; i >= 0; i--) {
/* 194 */           d9 = Math.cos(i * d2 - 1.5707963267948966D);
/* 195 */           d10 = Math.sin(i * d2 - 1.5707963267948966D);
/*     */ 
/*     */           
/* 198 */           geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 199 */           if (paramBoolean2) {
/* 200 */             geomBuffer.texCoord2d(d6, 1.0D - d7 + d5);
/*     */           } else {
/*     */             
/* 203 */             geomBuffer.texCoord2d(d6, d7 + d5);
/*     */           } 
/* 205 */           geomBuffer.vertex3d(d9 * paramDouble2, d, d10 * paramDouble2);
/*     */ 
/*     */           
/* 208 */           geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 209 */           if (paramBoolean2) {
/* 210 */             geomBuffer.texCoord2d(d6, 1.0D - d7);
/*     */           } else {
/*     */             
/* 213 */             geomBuffer.texCoord2d(d6, d7);
/*     */           } 
/* 215 */           geomBuffer.vertex3d(d9 * paramDouble2, d8, d10 * paramDouble2);
/*     */           
/* 217 */           d6 += d4;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 222 */         double d9 = Math.cos(-1.5707963267948966D);
/* 223 */         double d10 = Math.sin(-1.5707963267948966D);
/* 224 */         double d11 = Math.cos(d2 - 1.5707963267948966D);
/* 225 */         double d12 = Math.sin(d2 - 1.5707963267948966D);
/*     */         
/* 227 */         geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 228 */         if (paramBoolean2) {
/* 229 */           geomBuffer.texCoord2d(d6, 1.0D - d7 + d5);
/*     */         } else {
/*     */           
/* 232 */           geomBuffer.texCoord2d(d6, d7 + d5);
/*     */         } 
/* 234 */         geomBuffer.vertex3d(d9 * paramDouble2, d, d10 * paramDouble2);
/*     */ 
/*     */         
/* 237 */         geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 238 */         if (paramBoolean2) {
/* 239 */           geomBuffer.texCoord2d(d6, 1.0D - d7);
/*     */         } else {
/*     */           
/* 242 */           geomBuffer.texCoord2d(d6, d7);
/*     */         } 
/* 244 */         geomBuffer.vertex3d(d9 * paramDouble2, d8, d10 * paramDouble2);
/*     */         
/* 246 */         geomBuffer.normal3d(d11 * d1, 0.0D, d12 * d1);
/* 247 */         if (paramBoolean2) {
/* 248 */           geomBuffer.texCoord2d(d6 + d4, 1.0D - d7 + d5);
/*     */         } else {
/*     */           
/* 251 */           geomBuffer.texCoord2d(d6 + d4, d7 + d5);
/*     */         } 
/* 253 */         geomBuffer.vertex3d(d11 * paramDouble2, d, d12 * paramDouble2);
/*     */         
/* 255 */         geomBuffer.normal3d(d11 * d1, 0.0D, d12 * d1);
/* 256 */         if (paramBoolean2) {
/* 257 */           geomBuffer.texCoord2d(d6 + d4, 1.0D - d7);
/*     */         } else {
/*     */           
/* 260 */           geomBuffer.texCoord2d(d6 + d4, d7);
/*     */         } 
/* 262 */         geomBuffer.vertex3d(d11 * paramDouble2, d8, d12 * paramDouble2);
/*     */         
/* 264 */         d6 += d4 * 2.0D;
/*     */         
/* 266 */         for (byte b1 = 2; b1 <= paramInt1; b1++) {
/* 267 */           d9 = Math.cos(b1 * d2 - 1.5707963267948966D);
/* 268 */           d10 = Math.sin(b1 * d2 - 1.5707963267948966D);
/*     */           
/* 270 */           geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 271 */           if (paramBoolean2) {
/* 272 */             geomBuffer.texCoord2d(d6, 1.0D - d7 + d5);
/*     */           } else {
/*     */             
/* 275 */             geomBuffer.texCoord2d(d6, d7 + d5);
/*     */           } 
/* 277 */           geomBuffer.vertex3d(d9 * paramDouble2, d, d10 * paramDouble2);
/*     */           
/* 279 */           geomBuffer.normal3d(d9 * d1, 0.0D, d10 * d1);
/* 280 */           if (paramBoolean2) {
/* 281 */             geomBuffer.texCoord2d(d6, 1.0D - d7);
/*     */           } else {
/*     */             
/* 284 */             geomBuffer.texCoord2d(d6, d7);
/*     */           } 
/* 286 */           geomBuffer.vertex3d(d9 * paramDouble2, d8, d10 * paramDouble2);
/*     */           
/* 288 */           d6 += d4;
/*     */         } 
/*     */       } 
/*     */       
/* 292 */       d6 = 0.0D;
/* 293 */       d7 += d5;
/* 294 */       d8 += d3;
/*     */     } 
/*     */     
/* 297 */     geomBuffer.end();
/*     */     
/* 299 */     return geomBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeomBuffer coneBody(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt1, int paramInt2, double paramDouble5, boolean paramBoolean1, boolean paramBoolean2) {
/*     */     double d2;
/* 309 */     if (paramBoolean1) { d2 = 1.0D; }
/* 310 */     else { d2 = -1.0D; }
/*     */ 
/*     */     
/* 313 */     double d3 = 6.283185307179586D / paramInt1;
/* 314 */     double d4 = (paramDouble4 - paramDouble3) / paramInt2;
/* 315 */     double d5 = paramDouble2 - paramDouble1;
/* 316 */     double d6 = d5 / paramInt2;
/* 317 */     double d7 = (paramDouble3 - paramDouble4) / d5;
/* 318 */     double d8 = 1.0D / paramInt1;
/*     */ 
/*     */     
/* 321 */     GeomBuffer geomBuffer = new GeomBuffer(paramInt2 * 2 * (paramInt1 + 1));
/*     */     
/* 323 */     double d9 = 0.0D, d10 = 0.0D;
/*     */     
/* 325 */     double d11 = paramDouble1;
/*     */     
/* 327 */     double d1 = paramDouble3;
/*     */     
/* 329 */     geomBuffer.begin(1);
/*     */     
/* 331 */     for (byte b = 0; b < paramInt2; b++) {
/* 332 */       double d = d11 + d6;
/* 333 */       if (paramBoolean1) {
/* 334 */         double d12 = Math.cos(paramInt1 * d3 - 1.5707963267948966D);
/* 335 */         double d13 = Math.sin(paramInt1 * d3 - 1.5707963267948966D);
/* 336 */         double d14 = Math.cos((paramInt1 - 1) * d3 - 1.5707963267948966D);
/* 337 */         double d15 = Math.sin((paramInt1 - 1) * d3 - 1.5707963267948966D);
/*     */ 
/*     */         
/* 340 */         geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 341 */         if (paramBoolean2) {
/* 342 */           geomBuffer.texCoord2d(d9, 1.0D - d10 + paramDouble5);
/*     */         } else {
/*     */           
/* 345 */           geomBuffer.texCoord2d(d9, d10 + paramDouble5);
/*     */         } 
/* 347 */         geomBuffer.vertex3d(d12 * (d1 + d4), d, d13 * (d1 + d4));
/*     */ 
/*     */         
/* 350 */         geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 351 */         if (paramBoolean2) {
/* 352 */           geomBuffer.texCoord2d(d9, 1.0D - d10);
/*     */         } else {
/*     */           
/* 355 */           geomBuffer.texCoord2d(d9, d10);
/*     */         } 
/* 357 */         geomBuffer.vertex3d(d12 * d1, d11, d13 * d1);
/*     */ 
/*     */         
/* 360 */         geomBuffer.normal3d(d14 * d2, d7 * d2, d15 * d2);
/* 361 */         if (paramBoolean2) {
/* 362 */           geomBuffer.texCoord2d(d9 + d8, 1.0D - d10 + paramDouble5);
/*     */         } else {
/*     */           
/* 365 */           geomBuffer.texCoord2d(d9 + d8, d10 + paramDouble5);
/*     */         } 
/* 367 */         geomBuffer.vertex3d(d14 * (d1 + d4), d, d15 * (d1 + d4));
/*     */ 
/*     */         
/* 370 */         geomBuffer.normal3d(d14 * d2, d7 * d2, d15 * d2);
/* 371 */         if (paramBoolean2) {
/* 372 */           geomBuffer.texCoord2d(d9 + d8, 1.0D - d10);
/*     */         } else {
/*     */           
/* 375 */           geomBuffer.texCoord2d(d9 + d8, d10);
/*     */         } 
/* 377 */         geomBuffer.vertex3d(d14 * d1, d11, d15 * d1);
/*     */         
/* 379 */         d9 += d8 * 2.0D;
/*     */         
/* 381 */         for (int i = paramInt1 - 2; i >= 0; i--) {
/* 382 */           d12 = Math.cos(i * d3 - 1.5707963267948966D);
/* 383 */           d13 = Math.sin(i * d3 - 1.5707963267948966D);
/*     */ 
/*     */           
/* 386 */           geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 387 */           if (paramBoolean2) {
/* 388 */             geomBuffer.texCoord2d(d9, 1.0D - d10 + paramDouble5);
/*     */           } else {
/* 390 */             geomBuffer.texCoord2d(d9, d10 + paramDouble5);
/*     */           } 
/* 392 */           geomBuffer.vertex3d(d12 * (d1 + d4), d, d13 * (d1 + d4));
/*     */ 
/*     */           
/* 395 */           geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 396 */           if (paramBoolean2) {
/* 397 */             geomBuffer.texCoord2d(d9, 1.0D - d10);
/*     */           } else {
/*     */             
/* 400 */             geomBuffer.texCoord2d(d9, d10);
/*     */           } 
/* 402 */           geomBuffer.vertex3d(d12 * d1, d11, d13 * d1);
/*     */           
/* 404 */           d9 += d8;
/*     */         } 
/*     */       } else {
/* 407 */         double d12 = Math.cos(-1.5707963267948966D);
/* 408 */         double d13 = Math.sin(-1.5707963267948966D);
/* 409 */         double d14 = Math.cos(d3 - 1.5707963267948966D);
/* 410 */         double d15 = Math.sin(d3 - 1.5707963267948966D);
/*     */ 
/*     */         
/* 413 */         geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 414 */         if (paramBoolean2) {
/* 415 */           geomBuffer.texCoord2d(d9, 1.0D - d10 + paramDouble5);
/*     */         } else {
/*     */           
/* 418 */           geomBuffer.texCoord2d(d9, d10 + paramDouble5);
/*     */         } 
/* 420 */         geomBuffer.vertex3d(d12 * (d1 + d4), d, d13 * (d1 + d4));
/*     */         
/* 422 */         geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 423 */         if (paramBoolean2) {
/* 424 */           geomBuffer.texCoord2d(d9, 1.0D - d10);
/*     */         } else {
/*     */           
/* 427 */           geomBuffer.texCoord2d(d9, d10);
/*     */         } 
/* 429 */         geomBuffer.vertex3d(d12 * d1, d11, d13 * d1);
/*     */         
/* 431 */         geomBuffer.normal3d(d14 * d2, d7 * d2, d15 * d2);
/* 432 */         if (paramBoolean2) {
/* 433 */           geomBuffer.texCoord2d(d9 + d8, 1.0D - d10 + paramDouble5);
/*     */         } else {
/*     */           
/* 436 */           geomBuffer.texCoord2d(d9 + d8, d10 + paramDouble5);
/*     */         } 
/* 438 */         geomBuffer.vertex3d(d14 * (d1 + d4), d, d15 * (d1 + d4));
/*     */         
/* 440 */         geomBuffer.normal3d(d14 * d2, d7 * d2, d15 * d2);
/* 441 */         if (paramBoolean2) {
/* 442 */           geomBuffer.texCoord2d(d9 + d8, 1.0D - d10);
/*     */         } else {
/*     */           
/* 445 */           geomBuffer.texCoord2d(d9 + d8, d10);
/*     */         } 
/* 447 */         geomBuffer.vertex3d(d14 * d1, d11, d15 * d1);
/*     */         
/* 449 */         d9 += d8 * 2.0D;
/*     */         
/* 451 */         for (byte b1 = 2; b1 <= paramInt1; b1++) {
/* 452 */           d12 = Math.cos(b1 * d3 - 1.5707963267948966D);
/* 453 */           d13 = Math.sin(b1 * d3 - 1.5707963267948966D);
/*     */           
/* 455 */           geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 456 */           if (paramBoolean2) {
/* 457 */             geomBuffer.texCoord2d(d9, 1.0D - d10 + paramDouble5);
/*     */           } else {
/*     */             
/* 460 */             geomBuffer.texCoord2d(d9, d10 + paramDouble5);
/*     */           } 
/* 462 */           geomBuffer.vertex3d(d12 * (d1 + d4), d, d13 * (d1 + d4));
/*     */           
/* 464 */           geomBuffer.normal3d(d12 * d2, d7 * d2, d13 * d2);
/* 465 */           if (paramBoolean2) {
/* 466 */             geomBuffer.texCoord2d(d9, 1.0D - d10);
/*     */           } else {
/*     */             
/* 469 */             geomBuffer.texCoord2d(d9, d10);
/*     */           } 
/* 471 */           geomBuffer.vertex3d(d12 * d1, d11, d13 * d1);
/*     */           
/* 473 */           d9 += d8;
/*     */         } 
/*     */       } 
/* 476 */       d9 = 0.0D;
/* 477 */       d10 += paramDouble5;
/* 478 */       d11 += d6;
/* 479 */       d1 += d4;
/*     */     } 
/* 481 */     geomBuffer.end();
/*     */     
/* 483 */     return geomBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeomBuffer coneTop(double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, boolean paramBoolean1, boolean paramBoolean2) {
/*     */     double d1;
/* 492 */     if (paramBoolean1) { d1 = 1.0D; }
/* 493 */     else { d1 = -1.0D; }
/*     */ 
/*     */     
/* 496 */     double d2 = 6.283185307179586D / paramInt;
/* 497 */     double d3 = paramDouble2 / paramDouble3;
/* 498 */     double d4 = 1.0D / paramInt;
/* 499 */     double d5 = paramDouble1 + paramDouble3;
/*     */ 
/*     */     
/* 502 */     GeomBuffer geomBuffer = new GeomBuffer(paramInt + 2);
/* 503 */     geomBuffer.begin(16);
/*     */ 
/*     */     
/* 506 */     geomBuffer.normal3d(0.0D, d3 * d1, 0.0D);
/* 507 */     if (paramBoolean2) {
/* 508 */       geomBuffer.texCoord2d(0.5D, 0.0D);
/*     */     } else {
/*     */       
/* 511 */       geomBuffer.texCoord2d(0.5D, 1.0D);
/*     */     } 
/* 513 */     geomBuffer.vertex3d(0.0D, d5, 0.0D);
/*     */ 
/*     */     
/* 516 */     double d6 = 0.0D;
/*     */     
/* 518 */     if (paramBoolean1) {
/* 519 */       for (int i = paramInt; i >= 0; i--) {
/* 520 */         double d7 = Math.cos(i * d2 - 1.5707963267948966D);
/* 521 */         double d8 = Math.sin(i * d2 - 1.5707963267948966D);
/* 522 */         geomBuffer.normal3d(d7 * d1, d3 * d1, d8 * d1);
/* 523 */         if (paramBoolean2) {
/* 524 */           geomBuffer.texCoord2d(d6, 1.0D - paramDouble4);
/*     */         } else {
/* 526 */           geomBuffer.texCoord2d(d6, paramDouble4);
/*     */         } 
/* 528 */         geomBuffer.vertex3d(d7 * paramDouble2, paramDouble1, d8 * paramDouble2);
/*     */         
/* 530 */         d6 += d4;
/*     */       } 
/*     */     } else {
/* 533 */       for (byte b = 0; b <= paramInt; b++) {
/* 534 */         double d7 = Math.cos(b * d2 - 1.5707963267948966D);
/* 535 */         double d8 = Math.sin(b * d2 - 1.5707963267948966D);
/* 536 */         geomBuffer.normal3d(d7 * d1, d3 * d1, d8 * d1);
/* 537 */         if (paramBoolean2) {
/* 538 */           geomBuffer.texCoord2d(d6, 1.0D - paramDouble4);
/*     */         } else {
/* 540 */           geomBuffer.texCoord2d(d6, paramDouble4);
/*     */         } 
/* 542 */         geomBuffer.vertex3d(d7 * paramDouble2, paramDouble1, d8 * paramDouble2);
/* 543 */         d6 += d4;
/*     */       } 
/*     */     } 
/* 546 */     geomBuffer.end();
/* 547 */     return geomBuffer;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\Quadrics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */