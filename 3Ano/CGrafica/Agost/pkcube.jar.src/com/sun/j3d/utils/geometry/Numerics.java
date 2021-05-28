/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.vecmath.Point2f;
/*     */ import javax.vecmath.Tuple2f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Numerics
/*     */ {
/*  70 */   static double max3(double paramDouble1, double paramDouble2, double paramDouble3) { return (paramDouble1 > paramDouble2) ? ((paramDouble1 > paramDouble3) ? paramDouble1 : paramDouble3) : ((paramDouble2 > paramDouble3) ? paramDouble2 : paramDouble3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   static double min3(double paramDouble1, double paramDouble2, double paramDouble3) { return (paramDouble1 < paramDouble2) ? ((paramDouble1 < paramDouble3) ? paramDouble1 : paramDouble3) : ((paramDouble2 < paramDouble3) ? paramDouble2 : paramDouble3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   static boolean lt(double paramDouble1, double paramDouble2) { return (paramDouble1 < -paramDouble2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   static boolean le(double paramDouble1, double paramDouble2) { return (paramDouble1 <= paramDouble2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   static boolean ge(double paramDouble1, double paramDouble2) { return (paramDouble1 > -paramDouble2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   static boolean eq(double paramDouble1, double paramDouble2) { return (paramDouble1 <= paramDouble2 && paramDouble1 >= -paramDouble2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   static boolean gt(double paramDouble1, double paramDouble2) { return (paramDouble1 > paramDouble2); }
/*     */ 
/*     */ 
/*     */   
/*     */   static double baseLength(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2) {
/* 107 */     double d1 = (paramTuple2f2.x - paramTuple2f1.x);
/* 108 */     double d2 = (paramTuple2f2.y - paramTuple2f1.y);
/* 109 */     return Math.abs(d1) + Math.abs(d2);
/*     */   }
/*     */ 
/*     */   
/*     */   static double sideLength(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2) {
/* 114 */     double d1 = (paramTuple2f2.x - paramTuple2f1.x);
/* 115 */     double d2 = (paramTuple2f2.y - paramTuple2f1.y);
/* 116 */     return d1 * d1 + d2 * d2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   static boolean inBetween(int paramInt1, int paramInt2, int paramInt3) { return (paramInt1 <= paramInt3 && paramInt3 <= paramInt2); }
/*     */ 
/*     */ 
/*     */   
/* 129 */   static boolean strictlyInBetween(int paramInt1, int paramInt2, int paramInt3) { return (paramInt1 < paramInt3 && paramInt3 < paramInt2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static double stableDet2D(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3) {
/*     */     double d;
/* 145 */     if (paramInt1 == paramInt2 || paramInt1 == paramInt3 || paramInt2 == paramInt3) {
/* 146 */       d = 0.0D;
/*     */     } else {
/*     */       
/* 149 */       Point2f point2f1 = paramTriangulator.points[paramInt1];
/* 150 */       Point2f point2f2 = paramTriangulator.points[paramInt2];
/* 151 */       Point2f point2f3 = paramTriangulator.points[paramInt3];
/*     */       
/* 153 */       if (paramInt1 < paramInt2) {
/* 154 */         if (paramInt2 < paramInt3) {
/* 155 */           d = Basic.det2D(point2f1, point2f2, point2f3);
/* 156 */         } else if (paramInt1 < paramInt3) {
/* 157 */           d = -Basic.det2D(point2f1, point2f3, point2f2);
/*     */         } else {
/* 159 */           d = Basic.det2D(point2f3, point2f1, point2f2);
/*     */         }
/*     */       
/* 162 */       } else if (paramInt1 < paramInt3) {
/* 163 */         d = -Basic.det2D(point2f2, point2f1, point2f3);
/* 164 */       } else if (paramInt2 < paramInt3) {
/* 165 */         d = Basic.det2D(point2f2, point2f3, point2f1);
/*     */       } else {
/* 167 */         d = -Basic.det2D(point2f3, point2f2, point2f1);
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int orientation(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3) {
/*     */     byte b;
/* 183 */     double d = stableDet2D(paramTriangulator, paramInt1, paramInt2, paramInt3);
/*     */     
/* 185 */     if (lt(d, paramTriangulator.epsilon)) { b = -1; }
/* 186 */     else if (gt(d, paramTriangulator.epsilon)) { b = 1; }
/* 187 */     else { b = 0; }
/* 188 */      return b;
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
/*     */   static boolean isInCone(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
/* 204 */     boolean bool = true;
/* 205 */     if (paramBoolean) {
/* 206 */       if (paramInt1 != paramInt2) {
/* 207 */         int i = orientation(paramTriangulator, paramInt1, paramInt2, paramInt4);
/*     */         
/* 209 */         if (i < 0) { bool = false; }
/* 210 */         else if (i == 0)
/* 211 */         { if (paramInt1 < paramInt2)
/* 212 */           { if (!inBetween(paramInt1, paramInt2, paramInt4)) bool = false;
/*     */              }
/*     */           
/* 215 */           else if (!inBetween(paramInt2, paramInt1, paramInt4)) { bool = false; }
/*     */            }
/*     */       
/*     */       } 
/* 219 */       if (paramInt2 != paramInt3 && bool == true) {
/* 220 */         int i = orientation(paramTriangulator, paramInt2, paramInt3, paramInt4);
/*     */ 
/*     */         
/* 223 */         if (i < 0) { bool = false; }
/* 224 */         else if (i == 0)
/* 225 */         { if (paramInt2 < paramInt3)
/* 226 */           { if (!inBetween(paramInt2, paramInt3, paramInt4)) bool = false;
/*     */              }
/*     */           
/* 229 */           else if (!inBetween(paramInt3, paramInt2, paramInt4)) { bool = false; }
/*     */            }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 235 */       int i = orientation(paramTriangulator, paramInt1, paramInt2, paramInt4);
/* 236 */       if (i <= 0) {
/* 237 */         int j = orientation(paramTriangulator, paramInt2, paramInt3, paramInt4);
/* 238 */         if (j < 0) bool = false; 
/*     */       } 
/*     */     } 
/* 241 */     return bool;
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
/*     */   static int isConvexAngle(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*     */     int i;
/* 264 */     if (paramInt1 == paramInt2) {
/* 265 */       if (paramInt2 == paramInt3)
/*     */       {
/*     */         
/* 268 */         return 1;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 273 */       return 1;
/*     */     } 
/*     */     
/* 276 */     if (paramInt2 == paramInt3)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 284 */       return -1;
/*     */     }
/*     */     
/* 287 */     int j = orientation(paramTriangulator, paramInt1, paramInt2, paramInt3);
/*     */ 
/*     */ 
/*     */     
/* 291 */     if (j > 0) {
/* 292 */       i = 1;
/*     */     }
/* 294 */     else if (j < 0) {
/* 295 */       i = -1;
/*     */     }
/*     */     else {
/*     */       
/* 299 */       Point2f point2f1 = new Point2f();
/* 300 */       Point2f point2f2 = new Point2f();
/* 301 */       Basic.vectorSub2D(paramTriangulator.points[paramInt1], paramTriangulator.points[paramInt2], point2f1);
/* 302 */       Basic.vectorSub2D(paramTriangulator.points[paramInt3], paramTriangulator.points[paramInt2], point2f2);
/* 303 */       double d = Basic.dotProduct2D(point2f1, point2f2);
/* 304 */       if (d < 0.0D) {
/*     */         
/* 306 */         i = 0;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 312 */         i = spikeAngle(paramTriangulator, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 317 */     return i;
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
/*     */   static boolean pntInTriangle(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 329 */     boolean bool = false;
/* 330 */     int i = orientation(paramTriangulator, paramInt2, paramInt3, paramInt4);
/* 331 */     if (i >= 0) {
/* 332 */       i = orientation(paramTriangulator, paramInt1, paramInt2, paramInt4);
/* 333 */       if (i >= 0) {
/* 334 */         i = orientation(paramTriangulator, paramInt3, paramInt1, paramInt4);
/* 335 */         if (i >= 0) bool = true; 
/*     */       } 
/*     */     } 
/* 338 */     return bool;
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
/*     */   static boolean vtxInTriangle(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt) {
/* 352 */     boolean bool = false;
/* 353 */     int i = orientation(paramTriangulator, paramInt2, paramInt3, paramInt4);
/* 354 */     if (i >= 0) {
/* 355 */       i = orientation(paramTriangulator, paramInt1, paramInt2, paramInt4);
/* 356 */       if (i > 0) {
/* 357 */         i = orientation(paramTriangulator, paramInt3, paramInt1, paramInt4);
/* 358 */         if (i > 0) {
/* 359 */           bool = true;
/* 360 */           paramArrayOfInt[0] = 0;
/*     */         }
/* 362 */         else if (i == 0) {
/* 363 */           bool = true;
/* 364 */           paramArrayOfInt[0] = 1;
/*     */         }
/*     */       
/* 367 */       } else if (i == 0) {
/* 368 */         i = orientation(paramTriangulator, paramInt3, paramInt1, paramInt4);
/* 369 */         if (i > 0) {
/* 370 */           bool = true;
/* 371 */           paramArrayOfInt[0] = 2;
/*     */         }
/* 373 */         else if (i == 0) {
/* 374 */           bool = true;
/* 375 */           paramArrayOfInt[0] = 3;
/*     */         } 
/*     */       } 
/*     */     } 
/* 379 */     return bool;
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
/*     */ 
/*     */   
/*     */   static boolean segIntersect(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 403 */     if (paramInt1 == paramInt2 || paramInt3 == paramInt4) return false; 
/* 404 */     if (paramInt1 == paramInt3 && paramInt2 == paramInt4) return true;
/*     */     
/* 406 */     if (paramInt3 == paramInt5 || paramInt4 == paramInt5) paramTriangulator.identCntr++;
/*     */     
/* 408 */     int k = orientation(paramTriangulator, paramInt1, paramInt2, paramInt3);
/* 409 */     int m = orientation(paramTriangulator, paramInt1, paramInt2, paramInt4);
/* 410 */     if ((k == 1 && m == 1) || (k == -1 && m == -1)) {
/* 411 */       return false;
/*     */     }
/* 413 */     if (k == 0) {
/* 414 */       if (strictlyInBetween(paramInt1, paramInt2, paramInt3)) return true; 
/* 415 */       if (m == 0) {
/* 416 */         if (strictlyInBetween(paramInt1, paramInt2, paramInt4)) return true; 
/*     */       } else {
/* 418 */         return false;
/*     */       } 
/* 420 */     } else if (m == 0) {
/* 421 */       if (strictlyInBetween(paramInt1, paramInt2, paramInt4)) return true; 
/* 422 */       return false;
/*     */     } 
/*     */     
/* 425 */     int i = orientation(paramTriangulator, paramInt3, paramInt4, paramInt1);
/* 426 */     int j = orientation(paramTriangulator, paramInt3, paramInt4, paramInt2);
/* 427 */     if ((i <= 0 && j <= 0) || (i >= 0 && j >= 0)) {
/* 428 */       return false;
/*     */     }
/* 430 */     return true;
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
/*     */   static double getRatio(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3) {
/* 450 */     Point2f point2f1 = paramTriangulator.points[paramInt1];
/* 451 */     Point2f point2f2 = paramTriangulator.points[paramInt2];
/* 452 */     Point2f point2f3 = paramTriangulator.points[paramInt3];
/*     */ 
/*     */     
/* 455 */     double d2 = baseLength(point2f1, point2f2);
/* 456 */     double d3 = baseLength(point2f1, point2f3);
/* 457 */     double d4 = baseLength(point2f3, point2f2);
/* 458 */     double d5 = max3(d2, d3, d4);
/*     */     
/* 460 */     if (10.0D * d2 < Math.min(d3, d4)) return 0.1D;
/*     */     
/* 462 */     double d1 = stableDet2D(paramTriangulator, paramInt1, paramInt2, paramInt3);
/* 463 */     if (lt(d1, paramTriangulator.epsilon)) {
/* 464 */       d1 = -d1;
/*     */     }
/* 466 */     else if (!gt(d1, paramTriangulator.epsilon)) {
/* 467 */       if (d5 > d2) return 0.1D; 
/* 468 */       return Double.MAX_VALUE;
/*     */     } 
/*     */     
/* 471 */     double d6 = d5 * d5 / d1;
/*     */     
/* 473 */     if (d6 < 10.0D) return d6;
/*     */     
/* 475 */     if (d2 < d5) return 0.1D; 
/* 476 */     return d6;
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
/*     */   static int spikeAngle(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 490 */     int j = paramInt4;
/* 491 */     int n = paramTriangulator.fetchData(j);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 496 */     int i = paramTriangulator.fetchPrevData(j);
/* 497 */     int m = paramTriangulator.fetchData(i);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 502 */     int k = paramTriangulator.fetchNextData(j);
/* 503 */     int i1 = paramTriangulator.fetchData(k);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 508 */     return recSpikeAngle(paramTriangulator, paramInt1, paramInt2, paramInt3, i, k);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int recSpikeAngle(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 519 */     if (paramInt4 == paramInt5)
/*     */     {
/*     */ 
/*     */       
/* 523 */       return -2;
/*     */     }
/*     */     
/* 526 */     if (paramInt1 != paramInt3) {
/* 527 */       int n, m; if (paramInt1 < paramInt2) {
/* 528 */         m = paramInt1;
/* 529 */         n = paramInt2;
/*     */       } else {
/*     */         
/* 532 */         m = paramInt2;
/* 533 */         n = paramInt1;
/*     */       } 
/* 535 */       if (inBetween(m, n, paramInt3)) {
/* 536 */         paramInt2 = paramInt3;
/* 537 */         paramInt5 = paramTriangulator.fetchNextData(paramInt5);
/* 538 */         paramInt3 = paramTriangulator.fetchData(paramInt5);
/*     */         
/* 540 */         if (paramInt4 == paramInt5) return 2; 
/* 541 */         int i1 = orientation(paramTriangulator, paramInt1, paramInt2, paramInt3);
/* 542 */         if (i1 > 0) return 2; 
/* 543 */         if (i1 < 0) return -2; 
/* 544 */         return recSpikeAngle(paramTriangulator, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*     */       } 
/*     */       
/* 547 */       paramInt2 = paramInt1;
/* 548 */       paramInt4 = paramTriangulator.fetchPrevData(paramInt4);
/* 549 */       paramInt1 = paramTriangulator.fetchData(paramInt4);
/* 550 */       if (paramInt4 == paramInt5) return 2; 
/* 551 */       int k = orientation(paramTriangulator, paramInt1, paramInt2, paramInt3);
/* 552 */       if (k > 0) return 2; 
/* 553 */       if (k < 0) return -2; 
/* 554 */       return recSpikeAngle(paramTriangulator, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*     */     } 
/*     */ 
/*     */     
/* 558 */     int j = paramInt2;
/* 559 */     paramInt2 = paramInt1;
/* 560 */     paramInt4 = paramTriangulator.fetchPrevData(paramInt4);
/* 561 */     paramInt1 = paramTriangulator.fetchData(paramInt4);
/*     */     
/* 563 */     if (paramInt4 == paramInt5) return 2; 
/* 564 */     paramInt5 = paramTriangulator.fetchNextData(paramInt5);
/* 565 */     paramInt3 = paramTriangulator.fetchData(paramInt5);
/* 566 */     if (paramInt4 == paramInt5) return 2; 
/* 567 */     int i = orientation(paramTriangulator, paramInt1, paramInt2, paramInt3);
/* 568 */     if (i > 0) {
/* 569 */       int k = orientation(paramTriangulator, paramInt1, paramInt2, j);
/* 570 */       if (k > 0) {
/* 571 */         int m = orientation(paramTriangulator, paramInt2, paramInt3, j);
/* 572 */         if (m > 0) return -2; 
/*     */       } 
/* 574 */       return 2;
/*     */     } 
/* 576 */     if (i < 0) {
/* 577 */       int k = orientation(paramTriangulator, paramInt2, paramInt1, j);
/* 578 */       if (k > 0) {
/* 579 */         int m = orientation(paramTriangulator, paramInt3, paramInt2, j);
/* 580 */         if (m > 0) return 2; 
/*     */       } 
/* 582 */       return -2;
/*     */     } 
/*     */     
/* 585 */     Point2f point2f1 = new Point2f();
/* 586 */     Basic.vectorSub2D(paramTriangulator.points[paramInt1], paramTriangulator.points[paramInt2], point2f1);
/* 587 */     Point2f point2f2 = new Point2f();
/* 588 */     Basic.vectorSub2D(paramTriangulator.points[paramInt3], paramTriangulator.points[paramInt2], point2f2);
/* 589 */     double d = Basic.dotProduct2D(point2f1, point2f2);
/* 590 */     if (d < 0.0D) {
/* 591 */       i = orientation(paramTriangulator, paramInt2, paramInt1, j);
/* 592 */       if (i > 0) return 2; 
/* 593 */       return -2;
/*     */     } 
/*     */     
/* 596 */     return recSpikeAngle(paramTriangulator, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
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
/*     */   static double angle(Triangulator paramTriangulator, Point2f paramPoint2f1, Point2f paramPoint2f2, Point2f paramPoint2f3) {
/* 615 */     int i = Basic.signEps(Basic.det2D(paramPoint2f3, paramPoint2f1, paramPoint2f2), paramTriangulator.epsilon);
/*     */     
/* 617 */     if (i == 0) return 0.0D;
/*     */     
/* 619 */     Point2f point2f1 = new Point2f();
/* 620 */     Point2f point2f2 = new Point2f();
/* 621 */     Basic.vectorSub2D(paramPoint2f2, paramPoint2f1, point2f1);
/* 622 */     Basic.vectorSub2D(paramPoint2f3, paramPoint2f1, point2f2);
/*     */     
/* 624 */     double d1 = Math.atan2(point2f1.y, point2f1.x);
/* 625 */     double d2 = Math.atan2(point2f2.y, point2f2.x);
/*     */     
/* 627 */     if (d1 < 0.0D) d1 += 6.283185307179586D; 
/* 628 */     if (d2 < 0.0D) d2 += 6.283185307179586D;
/*     */     
/* 630 */     double d3 = d1 - d2;
/* 631 */     if (d3 > Math.PI) { d3 = 6.283185307179586D - d3; }
/* 632 */     else if (d3 < -3.141592653589793D) { d3 = 6.283185307179586D + d3; }
/*     */     
/* 634 */     if (i == 1) {
/* 635 */       if (d3 < 0.0D) return -d3; 
/* 636 */       return d3;
/*     */     } 
/*     */     
/* 639 */     if (d3 > 0.0D) return -d3; 
/* 640 */     return d3;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\Numerics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */