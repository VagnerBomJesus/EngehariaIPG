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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Bridge
/*     */ {
/*     */   static void constructBridges(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/*  71 */     int[] arrayOfInt1 = new int[1];
/*  72 */     int[] arrayOfInt2 = new int[1];
/*  73 */     int[] arrayOfInt3 = new int[1];
/*  74 */     int[] arrayOfInt4 = new int[1];
/*     */     
/*  76 */     int[] arrayOfInt5 = new int[1];
/*  77 */     int[] arrayOfInt6 = new int[1];
/*     */     
/*  79 */     if (paramTriangulator.noHashingEdges != true)
/*  80 */       System.out.println("Bridge:constructBridges noHashingEdges is false"); 
/*  81 */     if (paramInt2 <= paramInt1)
/*  82 */       System.out.println("Bridge:constructBridges loopMax<=loopMin"); 
/*  83 */     if (paramInt1 < 0)
/*  84 */       System.out.println("Bridge:constructBridges loopMin<0"); 
/*  85 */     if (paramInt2 > paramTriangulator.numLoops) {
/*  86 */       System.out.println("Bridge:constructBridges loopMax>triRef.numLoops");
/*     */     }
/*  88 */     int k = paramInt2 - paramInt1 - 1;
/*     */     
/*  90 */     if (k > paramTriangulator.maxNumLeftMost) {
/*  91 */       paramTriangulator.maxNumLeftMost = k;
/*  92 */       paramTriangulator.leftMost = new Left[k];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  97 */     findLeftMostVertex(paramTriangulator, paramTriangulator.loops[paramInt1], arrayOfInt2, arrayOfInt1);
/*  98 */     byte b1 = 0;
/*  99 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 100 */       findLeftMostVertex(paramTriangulator, paramTriangulator.loops[i], arrayOfInt6, arrayOfInt5);
/* 101 */       paramTriangulator.leftMost[b1] = new Left();
/* 102 */       (paramTriangulator.leftMost[b1]).ind = arrayOfInt6[0];
/* 103 */       (paramTriangulator.leftMost[b1]).index = arrayOfInt5[0];
/*     */       
/* 105 */       b1++;
/*     */     } 
/*     */ 
/*     */     
/* 109 */     sortLeft(paramTriangulator.leftMost, k);
/*     */ 
/*     */ 
/*     */     
/* 113 */     int j = paramTriangulator.numPoints + 2 * paramTriangulator.numLoops;
/* 114 */     paramTriangulator.maxNumDist = j;
/* 115 */     paramTriangulator.distances = new Distance[j];
/* 116 */     for (byte b2 = 0; b2 < paramTriangulator.maxNumDist; b2++) {
/* 117 */       paramTriangulator.distances[b2] = new Distance();
/*     */     }
/*     */     
/* 120 */     for (b1 = 0; b1 < k; b1++) {
/* 121 */       if (!findBridge(paramTriangulator, arrayOfInt2[0], arrayOfInt1[0], (paramTriangulator.leftMost[b1]).index, arrayOfInt4, arrayOfInt3));
/*     */ 
/*     */ 
/*     */       
/* 125 */       if (arrayOfInt3[0] == (paramTriangulator.leftMost[b1]).index) {
/*     */ 
/*     */         
/* 128 */         simpleBridge(paramTriangulator, arrayOfInt4[0], (paramTriangulator.leftMost[b1]).ind);
/*     */       } else {
/*     */         
/* 131 */         insertBridge(paramTriangulator, arrayOfInt4[0], arrayOfInt3[0], (paramTriangulator.leftMost[b1]).ind, (paramTriangulator.leftMost[b1]).index);
/*     */       } 
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
/*     */   static boolean findBridge(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 144 */     byte b2 = 0;
/*     */ 
/*     */     
/* 147 */     Distance[] arrayOfDistance = null;
/*     */ 
/*     */ 
/*     */     
/* 151 */     paramArrayOfInt1[0] = paramInt1;
/* 152 */     paramArrayOfInt2[0] = paramInt2;
/* 153 */     if (paramArrayOfInt2[0] == paramInt3) return true; 
/* 154 */     if (b2 >= paramTriangulator.maxNumDist) {
/*     */       
/* 156 */       paramTriangulator; paramTriangulator.maxNumDist += 50;
/* 157 */       arrayOfDistance = paramTriangulator.distances;
/* 158 */       paramTriangulator.distances = new Distance[paramTriangulator.maxNumDist];
/* 159 */       System.arraycopy(arrayOfDistance, 0, paramTriangulator.distances, 0, arrayOfDistance.length);
/* 160 */       for (int i = arrayOfDistance.length; i < paramTriangulator.maxNumDist; i++) {
/* 161 */         paramTriangulator.distances[i] = new Distance();
/*     */       }
/*     */     } 
/* 164 */     (paramTriangulator.distances[b2]).dist = Numerics.baseLength(paramTriangulator.points[paramInt3], paramTriangulator.points[paramArrayOfInt2[0]]);
/*     */     
/* 166 */     (paramTriangulator.distances[b2]).ind = paramArrayOfInt1[0];
/* 167 */     b2++;
/*     */ 
/*     */     
/* 170 */     paramArrayOfInt1[0] = paramTriangulator.fetchNextData(paramArrayOfInt1[0]);
/* 171 */     paramArrayOfInt2[0] = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/* 172 */     while (paramArrayOfInt1[0] != paramInt1) {
/* 173 */       if (paramArrayOfInt2[0] == paramInt3) return true; 
/* 174 */       if (b2 >= paramTriangulator.maxNumDist) {
/*     */         
/* 176 */         paramTriangulator; paramTriangulator.maxNumDist += 50;
/* 177 */         arrayOfDistance = paramTriangulator.distances;
/* 178 */         paramTriangulator.distances = new Distance[paramTriangulator.maxNumDist];
/* 179 */         System.arraycopy(arrayOfDistance, 0, paramTriangulator.distances, 0, arrayOfDistance.length);
/* 180 */         for (int i = arrayOfDistance.length; i < paramTriangulator.maxNumDist; i++) {
/* 181 */           paramTriangulator.distances[i] = new Distance();
/*     */         }
/*     */       } 
/* 184 */       (paramTriangulator.distances[b2]).dist = Numerics.baseLength(paramTriangulator.points[paramInt3], paramTriangulator.points[paramArrayOfInt2[0]]);
/*     */       
/* 186 */       (paramTriangulator.distances[b2]).ind = paramArrayOfInt1[0];
/* 187 */       b2++;
/* 188 */       paramArrayOfInt1[0] = paramTriangulator.fetchNextData(paramArrayOfInt1[0]);
/* 189 */       paramArrayOfInt2[0] = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/*     */     } 
/*     */ 
/*     */     
/* 193 */     sortDistance(paramTriangulator.distances, b2);
/*     */     
/*     */     byte b1;
/*     */     
/* 197 */     for (b1 = 0; b1 < b2; b1++) {
/* 198 */       paramArrayOfInt1[0] = (paramTriangulator.distances[b1]).ind;
/* 199 */       paramArrayOfInt2[0] = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/* 200 */       if (paramArrayOfInt2[0] <= paramInt3) {
/* 201 */         int k = paramTriangulator.fetchPrevData(paramArrayOfInt1[0]);
/* 202 */         int i = paramTriangulator.fetchData(k);
/* 203 */         int m = paramTriangulator.fetchNextData(paramArrayOfInt1[0]);
/* 204 */         int j = paramTriangulator.fetchData(m);
/* 205 */         boolean bool1 = (paramTriangulator.getAngle(paramArrayOfInt1[0]) > 0);
/*     */         
/* 207 */         boolean bool2 = Numerics.isInCone(paramTriangulator, i, paramArrayOfInt2[0], j, paramInt3, bool1);
/* 208 */         if (bool2) {
/* 209 */           BBox bBox = new BBox(paramTriangulator, paramArrayOfInt2[0], paramInt3);
/* 210 */           if (!NoHash.noHashEdgeIntersectionExists(paramTriangulator, bBox, -1, -1, paramArrayOfInt1[0], -1)) {
/* 211 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     for (b1 = 0; b1 < b2; b1++) {
/* 221 */       paramArrayOfInt1[0] = (paramTriangulator.distances[b1]).ind;
/* 222 */       paramArrayOfInt2[0] = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/* 223 */       int k = paramTriangulator.fetchPrevData(paramArrayOfInt1[0]);
/* 224 */       int i = paramTriangulator.fetchData(k);
/* 225 */       int m = paramTriangulator.fetchNextData(paramArrayOfInt1[0]);
/* 226 */       int j = paramTriangulator.fetchData(m);
/* 227 */       BBox bBox = new BBox(paramTriangulator, paramArrayOfInt2[0], paramInt3);
/* 228 */       if (!NoHash.noHashEdgeIntersectionExists(paramTriangulator, bBox, -1, -1, paramArrayOfInt1[0], -1)) {
/* 229 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 233 */     paramArrayOfInt1[0] = paramInt1;
/* 234 */     paramArrayOfInt2[0] = paramInt2;
/*     */     
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void findLeftMostVertex(Triangulator paramTriangulator, int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 244 */     int i = paramInt;
/* 245 */     int j = paramTriangulator.fetchData(i);
/* 246 */     paramArrayOfInt1[0] = i;
/* 247 */     paramArrayOfInt2[0] = j;
/* 248 */     i = paramTriangulator.fetchNextData(i);
/* 249 */     j = paramTriangulator.fetchData(i);
/* 250 */     while (i != paramInt) {
/* 251 */       if (j < paramArrayOfInt2[0]) {
/* 252 */         paramArrayOfInt1[0] = i;
/* 253 */         paramArrayOfInt2[0] = j;
/*     */       }
/* 255 */       else if (j == paramArrayOfInt2[0] && 
/* 256 */         paramTriangulator.getAngle(i) < 0) {
/* 257 */         paramArrayOfInt1[0] = i;
/* 258 */         paramArrayOfInt2[0] = j;
/*     */       } 
/*     */       
/* 261 */       i = paramTriangulator.fetchNextData(i);
/* 262 */       j = paramTriangulator.fetchData(i);
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
/*     */   static void simpleBridge(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/* 274 */     paramTriangulator.rotateLinks(paramInt1, paramInt2);
/*     */ 
/*     */     
/* 277 */     int k = paramTriangulator.fetchData(paramInt1);
/* 278 */     int j = paramTriangulator.fetchNextData(paramInt1);
/* 279 */     int i1 = paramTriangulator.fetchData(j);
/* 280 */     int i = paramTriangulator.fetchPrevData(paramInt1);
/* 281 */     int n = paramTriangulator.fetchData(i);
/* 282 */     int i2 = Numerics.isConvexAngle(paramTriangulator, n, k, i1, paramInt1);
/* 283 */     paramTriangulator.setAngle(paramInt1, i2);
/*     */     
/* 285 */     int m = paramTriangulator.fetchData(paramInt2);
/* 286 */     j = paramTriangulator.fetchNextData(paramInt2);
/* 287 */     i1 = paramTriangulator.fetchData(j);
/* 288 */     i = paramTriangulator.fetchPrevData(paramInt2);
/* 289 */     n = paramTriangulator.fetchData(i);
/* 290 */     i2 = Numerics.isConvexAngle(paramTriangulator, n, m, i1, paramInt2);
/* 291 */     paramTriangulator.setAngle(paramInt2, i2);
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
/*     */   static void insertBridge(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 303 */     int i = paramTriangulator.makeNode(paramInt2);
/* 304 */     paramTriangulator.insertAfter(paramInt1, i);
/*     */ 
/*     */ 
/*     */     
/* 308 */     int i3 = paramTriangulator.list[paramInt1].getCommonIndex();
/*     */     
/* 310 */     paramTriangulator.list[i].setCommonIndex(i3);
/*     */ 
/*     */     
/* 313 */     int j = paramTriangulator.makeNode(paramInt4);
/* 314 */     paramTriangulator.insertAfter(paramInt3, j);
/*     */     
/* 316 */     i3 = paramTriangulator.list[paramInt3].getCommonIndex();
/* 317 */     paramTriangulator.list[j].setCommonIndex(i3);
/*     */ 
/*     */     
/* 320 */     paramTriangulator.splitSplice(paramInt1, i, paramInt3, j);
/*     */ 
/*     */     
/* 323 */     int m = paramTriangulator.fetchNextData(paramInt1);
/* 324 */     int i1 = paramTriangulator.fetchData(m);
/* 325 */     int k = paramTriangulator.fetchPrevData(paramInt1);
/* 326 */     int n = paramTriangulator.fetchData(k);
/* 327 */     int i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt2, i1, paramInt1);
/* 328 */     paramTriangulator.setAngle(paramInt1, i2);
/*     */     
/* 330 */     m = paramTriangulator.fetchNextData(i);
/* 331 */     i1 = paramTriangulator.fetchData(m);
/* 332 */     k = paramTriangulator.fetchPrevData(i);
/* 333 */     n = paramTriangulator.fetchData(k);
/* 334 */     i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt2, i1, i);
/* 335 */     paramTriangulator.setAngle(i, i2);
/*     */     
/* 337 */     m = paramTriangulator.fetchNextData(paramInt3);
/* 338 */     i1 = paramTriangulator.fetchData(m);
/* 339 */     k = paramTriangulator.fetchPrevData(paramInt3);
/* 340 */     n = paramTriangulator.fetchData(k);
/* 341 */     i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt4, i1, paramInt3);
/* 342 */     paramTriangulator.setAngle(paramInt3, i2);
/*     */     
/* 344 */     m = paramTriangulator.fetchNextData(j);
/* 345 */     i1 = paramTriangulator.fetchData(m);
/* 346 */     k = paramTriangulator.fetchPrevData(j);
/* 347 */     n = paramTriangulator.fetchData(k);
/* 348 */     i2 = Numerics.isConvexAngle(paramTriangulator, n, paramInt4, i1, j);
/* 349 */     paramTriangulator.setAngle(j, i2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static int l_comp(Left paramLeft1, Left paramLeft2) {
/* 355 */     if (paramLeft1.index < paramLeft2.index) return -1; 
/* 356 */     if (paramLeft1.index > paramLeft2.index) return 1; 
/* 357 */     return 0;
/*     */   }
/*     */   
/*     */   static int d_comp(Distance paramDistance1, Distance paramDistance2) {
/* 361 */     if (paramDistance1.dist < paramDistance2.dist) return -1; 
/* 362 */     if (paramDistance1.dist > paramDistance2.dist) return 1; 
/* 363 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void sortLeft(Left[] paramArrayOfLeft, int paramInt) {
/* 369 */     Left left = new Left();
/*     */     
/* 371 */     for (byte b = 0; b < paramInt; b++) {
/* 372 */       for (byte b1 = b + true; b1 < paramInt; b1++) {
/* 373 */         if (l_comp(paramArrayOfLeft[b], paramArrayOfLeft[b1]) > 0) {
/* 374 */           left.copy(paramArrayOfLeft[b]);
/* 375 */           paramArrayOfLeft[b].copy(paramArrayOfLeft[b1]);
/* 376 */           paramArrayOfLeft[b1].copy(left);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void sortDistance(Distance[] paramArrayOfDistance, int paramInt) {
/* 385 */     Distance distance = new Distance();
/*     */     
/* 387 */     for (byte b = 0; b < paramInt; b++) {
/* 388 */       for (byte b1 = b + true; b1 < paramInt; b1++) {
/* 389 */         if (d_comp(paramArrayOfDistance[b], paramArrayOfDistance[b1]) > 0) {
/* 390 */           distance.copy(paramArrayOfDistance[b]);
/* 391 */           paramArrayOfDistance[b].copy(paramArrayOfDistance[b1]);
/* 392 */           paramArrayOfDistance[b1].copy(distance);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\Bridge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */