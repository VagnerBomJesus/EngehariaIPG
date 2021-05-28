/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.vecmath.Point2f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Desperate
/*     */ {
/*     */   static boolean desperate(Triangulator paramTriangulator, int paramInt1, int paramInt2, boolean[] paramArrayOfBoolean) {
/*  76 */     int[] arrayOfInt1 = new int[1];
/*  77 */     int[] arrayOfInt2 = new int[1];
/*  78 */     int[] arrayOfInt3 = new int[1];
/*  79 */     int[] arrayOfInt4 = new int[1];
/*  80 */     int[] arrayOfInt5 = new int[1];
/*  81 */     int[] arrayOfInt6 = new int[1];
/*  82 */     int[] arrayOfInt7 = new int[1];
/*  83 */     int[] arrayOfInt8 = new int[1];
/*     */     
/*  85 */     paramArrayOfBoolean[0] = false;
/*     */ 
/*     */ 
/*     */     
/*  89 */     if (existsCrossOver(paramTriangulator, paramInt1, arrayOfInt5, arrayOfInt1, arrayOfInt6, arrayOfInt2, arrayOfInt7, arrayOfInt3, arrayOfInt8, arrayOfInt4)) {
/*     */ 
/*     */       
/*  92 */       handleCrossOver(paramTriangulator, arrayOfInt5[0], arrayOfInt1[0], arrayOfInt6[0], arrayOfInt2[0], arrayOfInt7[0], arrayOfInt3[0], arrayOfInt8[0], arrayOfInt4[0]);
/*     */       
/*  94 */       return false;
/*     */     } 
/*     */     
/*  97 */     NoHash.prepareNoHashEdges(paramTriangulator, paramInt2, paramInt2 + 1);
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (existsSplit(paramTriangulator, paramInt1, arrayOfInt5, arrayOfInt1, arrayOfInt6, arrayOfInt2)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       handleSplit(paramTriangulator, arrayOfInt5[0], arrayOfInt1[0], arrayOfInt6[0], arrayOfInt2[0]);
/* 108 */       paramArrayOfBoolean[0] = true;
/* 109 */       return false;
/*     */     } 
/*     */     
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean existsCrossOver(Triangulator paramTriangulator, int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int[] paramArrayOfInt5, int[] paramArrayOfInt6, int[] paramArrayOfInt7, int[] paramArrayOfInt8) {
/* 121 */     paramArrayOfInt1[0] = paramInt;
/* 122 */     paramArrayOfInt2[0] = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/* 123 */     paramArrayOfInt3[0] = paramTriangulator.fetchNextData(paramArrayOfInt1[0]);
/* 124 */     paramArrayOfInt4[0] = paramTriangulator.fetchData(paramArrayOfInt3[0]);
/* 125 */     paramArrayOfInt5[0] = paramTriangulator.fetchNextData(paramArrayOfInt3[0]);
/* 126 */     paramArrayOfInt6[0] = paramTriangulator.fetchData(paramArrayOfInt5[0]);
/* 127 */     paramArrayOfInt7[0] = paramTriangulator.fetchNextData(paramArrayOfInt5[0]);
/* 128 */     paramArrayOfInt8[0] = paramTriangulator.fetchData(paramArrayOfInt7[0]);
/*     */     
/*     */     do {
/* 131 */       BBox bBox1 = new BBox(paramTriangulator, paramArrayOfInt2[0], paramArrayOfInt4[0]);
/* 132 */       BBox bBox2 = new BBox(paramTriangulator, paramArrayOfInt6[0], paramArrayOfInt8[0]);
/* 133 */       if (bBox1.BBoxOverlap(bBox2) && 
/* 134 */         Numerics.segIntersect(paramTriangulator, bBox1.imin, bBox1.imax, bBox2.imin, bBox2.imax, -1)) {
/* 135 */         return true;
/*     */       }
/* 137 */       paramArrayOfInt1[0] = paramArrayOfInt3[0];
/* 138 */       paramArrayOfInt2[0] = paramArrayOfInt4[0];
/* 139 */       paramArrayOfInt3[0] = paramArrayOfInt5[0];
/* 140 */       paramArrayOfInt4[0] = paramArrayOfInt6[0];
/* 141 */       paramArrayOfInt5[0] = paramArrayOfInt7[0];
/* 142 */       paramArrayOfInt6[0] = paramArrayOfInt8[0];
/* 143 */       paramArrayOfInt7[0] = paramTriangulator.fetchNextData(paramArrayOfInt5[0]);
/* 144 */       paramArrayOfInt8[0] = paramTriangulator.fetchData(paramArrayOfInt7[0]);
/*     */     }
/* 146 */     while (paramArrayOfInt1[0] != paramInt);
/*     */     
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void handleCrossOver(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
/*     */     boolean bool;
/* 160 */     int i = paramTriangulator.getAngle(paramInt1);
/* 161 */     int j = paramTriangulator.getAngle(paramInt7);
/* 162 */     if (i < j) { bool = true; }
/* 163 */     else if (i > j) { bool = false; }
/* 164 */     else if (paramTriangulator.earsSorted)
/* 165 */     { double d1 = Numerics.getRatio(paramTriangulator, paramInt6, paramInt8, paramInt2);
/* 166 */       double d2 = Numerics.getRatio(paramTriangulator, paramInt2, paramInt4, paramInt8);
/* 167 */       if (d2 < d1) { bool = false; }
/* 168 */       else { bool = true; }
/*     */        }
/*     */     else
/* 171 */     { bool = true; }
/*     */ 
/*     */     
/* 174 */     if (bool) {
/*     */       
/* 176 */       paramTriangulator.deleteLinks(paramInt3);
/*     */       
/* 178 */       paramTriangulator.storeTriangle(paramInt1, paramInt3, paramInt5);
/* 179 */       paramTriangulator.setAngle(paramInt5, 1);
/* 180 */       Heap.insertIntoHeap(paramTriangulator, 0.0D, paramInt5, paramInt1, paramInt7);
/*     */     }
/*     */     else {
/*     */       
/* 184 */       paramTriangulator.deleteLinks(paramInt5);
/*     */       
/* 186 */       paramTriangulator.storeTriangle(paramInt3, paramInt5, paramInt7);
/* 187 */       paramTriangulator.setAngle(paramInt3, 1);
/* 188 */       Heap.insertIntoHeap(paramTriangulator, 0.0D, paramInt3, paramInt1, paramInt7);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean letsHope(Triangulator paramTriangulator, int paramInt) {
/* 199 */     int j = paramInt;
/* 200 */     int n = paramTriangulator.fetchData(j);
/*     */     
/*     */     do {
/* 203 */       if (paramTriangulator.getAngle(j) > 0) {
/* 204 */         int i2 = paramTriangulator.fetchPrevData(j);
/* 205 */         int i4 = paramTriangulator.fetchData(i2);
/* 206 */         int i3 = paramTriangulator.fetchNextData(j);
/* 207 */         int i5 = paramTriangulator.fetchData(i3);
/* 208 */         Heap.insertIntoHeap(paramTriangulator, 0.0D, j, i2, i3);
/* 209 */         return true;
/*     */       } 
/* 211 */       j = paramTriangulator.fetchNextData(j);
/* 212 */       n = paramTriangulator.fetchData(j);
/* 213 */     } while (j != paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     paramTriangulator.setAngle(paramInt, 1);
/* 219 */     int i = paramTriangulator.fetchPrevData(paramInt);
/* 220 */     int m = paramTriangulator.fetchData(i);
/* 221 */     int k = paramTriangulator.fetchNextData(paramInt);
/* 222 */     int i1 = paramTriangulator.fetchData(k);
/* 223 */     Heap.insertIntoHeap(paramTriangulator, 0.0D, paramInt, i, k);
/* 224 */     n = paramTriangulator.fetchData(paramInt);
/*     */     
/* 226 */     return true;
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
/*     */   static boolean existsSplit(Triangulator paramTriangulator, int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4) {
/* 240 */     if (paramTriangulator.numPoints > paramTriangulator.maxNumDist) {
/*     */       
/* 242 */       paramTriangulator.maxNumDist = paramTriangulator.numPoints;
/* 243 */       paramTriangulator.distances = new Distance[paramTriangulator.maxNumDist];
/* 244 */       for (byte b = 0; b < paramTriangulator.maxNumDist; b++)
/* 245 */         paramTriangulator.distances[b] = new Distance(); 
/*     */     } 
/* 247 */     paramArrayOfInt1[0] = paramInt;
/* 248 */     paramArrayOfInt2[0] = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/* 249 */     int j = paramTriangulator.fetchNextData(paramArrayOfInt1[0]);
/* 250 */     int n = paramTriangulator.fetchData(j);
/*     */     
/* 252 */     int k = paramTriangulator.fetchNextData(j);
/* 253 */     int i1 = paramTriangulator.fetchData(k);
/*     */     
/* 255 */     int i = paramTriangulator.fetchPrevData(paramArrayOfInt1[0]);
/* 256 */     int m = paramTriangulator.fetchData(i);
/*     */     
/* 258 */     if (foundSplit(paramTriangulator, k, i1, i, paramArrayOfInt1[0], paramArrayOfInt2[0], m, n, paramArrayOfInt3, paramArrayOfInt4))
/* 259 */       return true; 
/* 260 */     m = paramArrayOfInt2[0];
/* 261 */     paramArrayOfInt1[0] = j;
/* 262 */     paramArrayOfInt2[0] = n;
/* 263 */     j = k;
/* 264 */     n = i1;
/* 265 */     k = paramTriangulator.fetchNextData(j);
/* 266 */     i1 = paramTriangulator.fetchData(k);
/*     */     
/* 268 */     while (k != paramInt) {
/* 269 */       if (foundSplit(paramTriangulator, k, i1, paramInt, paramArrayOfInt1[0], paramArrayOfInt2[0], m, n, paramArrayOfInt3, paramArrayOfInt4))
/* 270 */         return true; 
/* 271 */       m = paramArrayOfInt2[0];
/* 272 */       paramArrayOfInt1[0] = j;
/* 273 */       paramArrayOfInt2[0] = n;
/* 274 */       j = k;
/* 275 */       n = i1;
/* 276 */       k = paramTriangulator.fetchNextData(j);
/* 277 */       i1 = paramTriangulator.fetchData(k);
/*     */     } 
/*     */     
/* 280 */     return false;
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
/*     */   static int windingNumber(Triangulator paramTriangulator, int paramInt, Point2f paramPoint2f) {
/* 296 */     int j = paramTriangulator.fetchData(paramInt);
/* 297 */     int i = paramTriangulator.fetchNextData(paramInt);
/* 298 */     int k = paramTriangulator.fetchData(i);
/* 299 */     double d = Numerics.angle(paramTriangulator, paramPoint2f, paramTriangulator.points[j], paramTriangulator.points[k]);
/* 300 */     while (i != paramInt) {
/* 301 */       j = k;
/* 302 */       i = paramTriangulator.fetchNextData(i);
/* 303 */       k = paramTriangulator.fetchData(i);
/* 304 */       d += Numerics.angle(paramTriangulator, paramPoint2f, paramTriangulator.points[j], paramTriangulator.points[k]);
/*     */     } 
/*     */     
/* 307 */     d += Math.PI;
/* 308 */     return (int)(d / 6.283185307179586D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean foundSplit(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 319 */     byte b1 = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 328 */       (paramTriangulator.distances[b1]).dist = Numerics.baseLength(paramTriangulator.points[paramInt5], paramTriangulator.points[paramInt2]);
/*     */       
/* 330 */       (paramTriangulator.distances[b1]).ind = paramInt1;
/* 331 */       b1++;
/* 332 */       paramInt1 = paramTriangulator.fetchNextData(paramInt1);
/* 333 */       paramInt2 = paramTriangulator.fetchData(paramInt1);
/* 334 */     } while (paramInt1 != paramInt3);
/*     */     
/* 336 */     Bridge.sortDistance(paramTriangulator.distances, b1);
/*     */ 
/*     */     
/* 339 */     for (byte b2 = 0; b2 < b1; b2++) {
/* 340 */       paramArrayOfInt1[0] = (paramTriangulator.distances[b2]).ind;
/* 341 */       paramArrayOfInt2[0] = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/* 342 */       if (paramInt5 != paramArrayOfInt2[0]) {
/* 343 */         int k = paramTriangulator.fetchPrevData(paramArrayOfInt1[0]);
/* 344 */         int i = paramTriangulator.fetchData(k);
/* 345 */         int m = paramTriangulator.fetchNextData(paramArrayOfInt1[0]);
/* 346 */         int j = paramTriangulator.fetchData(m);
/*     */         
/* 348 */         boolean bool1 = (paramTriangulator.getAngle(paramArrayOfInt1[0]) > 0);
/* 349 */         boolean bool2 = Numerics.isInCone(paramTriangulator, i, paramArrayOfInt2[0], j, paramInt5, bool1);
/* 350 */         if (bool2) {
/* 351 */           bool1 = (paramTriangulator.getAngle(paramInt4) > 0);
/* 352 */           bool2 = Numerics.isInCone(paramTriangulator, paramInt6, paramInt5, paramInt7, paramArrayOfInt2[0], bool1);
/* 353 */           if (bool2) {
/* 354 */             BBox bBox = new BBox(paramTriangulator, paramInt5, paramArrayOfInt2[0]);
/* 355 */             if (!NoHash.noHashEdgeIntersectionExists(paramTriangulator, bBox, -1, -1, paramInt4, -1)) {
/*     */ 
/*     */               
/* 358 */               Point2f point2f = new Point2f();
/* 359 */               Basic.vectorAdd2D(paramTriangulator.points[paramInt5], paramTriangulator.points[paramArrayOfInt2[0]], point2f);
/* 360 */               Basic.multScalar2D(0.5D, point2f);
/* 361 */               if (windingNumber(paramTriangulator, paramInt3, point2f) == 1) return true;
/*     */             
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 368 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void handleSplit(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 375 */     int i3 = -1;
/*     */ 
/*     */     
/* 378 */     int i = paramTriangulator.makeNode(paramInt2);
/* 379 */     paramTriangulator.insertAfter(paramInt1, i);
/*     */ 
/*     */ 
/*     */     
/* 383 */     i3 = paramTriangulator.list[paramInt1].getCommonIndex();
/*     */     
/* 385 */     paramTriangulator.list[i].setCommonIndex(i3);
/*     */     
/* 387 */     int j = paramTriangulator.makeNode(paramInt4);
/* 388 */     paramTriangulator.insertAfter(paramInt3, j);
/*     */     
/* 390 */     i3 = paramTriangulator.list[paramInt3].getCommonIndex();
/* 391 */     paramTriangulator.list[j].setCommonIndex(i3);
/*     */ 
/*     */ 
/*     */     
/* 395 */     paramTriangulator.splitSplice(paramInt1, i, paramInt3, j);
/*     */ 
/*     */     
/* 398 */     paramTriangulator.storeChain(paramInt1);
/* 399 */     paramTriangulator.storeChain(paramInt3);
/*     */ 
/*     */     
/* 402 */     int m = paramTriangulator.fetchNextData(paramInt1);
/* 403 */     int i1 = paramTriangulator.fetchData(m);
/* 404 */     int k = paramTriangulator.fetchPrevData(paramInt1);
/* 405 */     int n = paramTriangulator.fetchData(k);
/* 406 */     int i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt2, i1, paramInt1);
/* 407 */     paramTriangulator.setAngle(paramInt1, i2);
/*     */     
/* 409 */     m = paramTriangulator.fetchNextData(i);
/* 410 */     i1 = paramTriangulator.fetchData(m);
/* 411 */     k = paramTriangulator.fetchPrevData(i);
/* 412 */     n = paramTriangulator.fetchData(k);
/* 413 */     i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt2, i1, i);
/* 414 */     paramTriangulator.setAngle(i, i2);
/*     */     
/* 416 */     m = paramTriangulator.fetchNextData(paramInt3);
/* 417 */     i1 = paramTriangulator.fetchData(m);
/* 418 */     k = paramTriangulator.fetchPrevData(paramInt3);
/* 419 */     n = paramTriangulator.fetchData(k);
/* 420 */     i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt4, i1, paramInt3);
/* 421 */     paramTriangulator.setAngle(paramInt3, i2);
/*     */     
/* 423 */     m = paramTriangulator.fetchNextData(j);
/* 424 */     i1 = paramTriangulator.fetchData(m);
/* 425 */     k = paramTriangulator.fetchPrevData(j);
/* 426 */     n = paramTriangulator.fetchData(k);
/* 427 */     i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt4, i1, j);
/* 428 */     paramTriangulator.setAngle(j, i2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\Desperate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */