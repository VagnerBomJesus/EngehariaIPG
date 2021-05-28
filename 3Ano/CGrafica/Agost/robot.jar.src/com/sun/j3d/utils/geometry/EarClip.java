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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class EarClip
/*     */ {
/*     */   static void classifyAngles(Triangulator paramTriangulator, int paramInt) {
/*  82 */     int j = paramInt;
/*  83 */     int m = paramTriangulator.fetchData(j);
/*  84 */     int i = paramTriangulator.fetchPrevData(j);
/*  85 */     int k = paramTriangulator.fetchData(i);
/*     */     
/*     */     do {
/*  88 */       int n = paramTriangulator.fetchNextData(j);
/*  89 */       int i1 = paramTriangulator.fetchData(n);
/*  90 */       int i2 = Numerics.isConvexAngle(paramTriangulator, k, m, i1, j);
/*  91 */       paramTriangulator.setAngle(j, i2);
/*  92 */       k = m;
/*  93 */       m = i1;
/*  94 */       j = n;
/*  95 */     } while (j != paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void classifyEars(Triangulator paramTriangulator, int paramInt) {
/* 106 */     int[] arrayOfInt1 = new int[1];
/* 107 */     int[] arrayOfInt2 = new int[1];
/* 108 */     double[] arrayOfDouble = new double[1];
/*     */     
/* 110 */     Heap.initHeap(paramTriangulator);
/*     */     
/* 112 */     int i = paramInt;
/* 113 */     int j = paramTriangulator.fetchData(i);
/*     */     
/*     */     do {
/* 116 */       if (paramTriangulator.getAngle(i) > 0 && isEar(paramTriangulator, i, arrayOfInt1, arrayOfInt2, arrayOfDouble))
/*     */       {
/*     */         
/* 119 */         Heap.dumpOnHeap(paramTriangulator, arrayOfDouble[0], i, arrayOfInt1[0], arrayOfInt2[0]);
/*     */       }
/* 121 */       i = paramTriangulator.fetchNextData(i);
/* 122 */       j = paramTriangulator.fetchData(i);
/* 123 */     } while (i != paramInt);
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
/*     */   static boolean isEar(Triangulator paramTriangulator, int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, double[] paramArrayOfDouble) {
/* 146 */     int k = paramTriangulator.fetchData(paramInt);
/* 147 */     paramArrayOfInt2[0] = paramTriangulator.fetchNextData(paramInt);
/* 148 */     int m = paramTriangulator.fetchData(paramArrayOfInt2[0]);
/* 149 */     int i2 = paramTriangulator.fetchNextData(paramArrayOfInt2[0]);
/* 150 */     int n = paramTriangulator.fetchData(i2);
/* 151 */     paramArrayOfInt1[0] = paramTriangulator.fetchPrevData(paramInt);
/* 152 */     int j = paramTriangulator.fetchData(paramArrayOfInt1[0]);
/* 153 */     int i1 = paramTriangulator.fetchPrevData(paramArrayOfInt1[0]);
/* 154 */     int i = paramTriangulator.fetchData(i1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (j == m || j == k || k == m || paramTriangulator.getAngle(paramInt) == 2) {
/*     */       
/* 163 */       paramArrayOfDouble[0] = 0.0D;
/* 164 */       return true;
/*     */     } 
/*     */     
/* 167 */     if (i == m) {
/*     */       
/* 169 */       if (paramTriangulator.getAngle(i1) < 0 || paramTriangulator.getAngle(paramArrayOfInt2[0]) < 0) {
/* 170 */         paramArrayOfDouble[0] = 0.0D;
/* 171 */         return true;
/*     */       } 
/*     */       
/* 174 */       return false;
/*     */     } 
/*     */     
/* 177 */     if (j == n) {
/*     */       
/* 179 */       if (paramTriangulator.getAngle(paramArrayOfInt1[0]) < 0 || paramTriangulator.getAngle(i2) < 0) {
/* 180 */         paramArrayOfDouble[0] = 0.0D;
/* 181 */         return true;
/*     */       } 
/*     */       
/* 184 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 188 */     boolean bool1 = (paramTriangulator.getAngle(paramArrayOfInt1[0]) > 0);
/* 189 */     boolean bool2 = Numerics.isInCone(paramTriangulator, i, j, k, m, bool1);
/*     */ 
/*     */     
/* 192 */     if (!bool2) return false; 
/* 193 */     bool1 = (paramTriangulator.getAngle(paramArrayOfInt2[0]) > 0);
/* 194 */     bool2 = Numerics.isInCone(paramTriangulator, k, m, n, j, bool1);
/*     */ 
/*     */     
/* 197 */     if (bool2) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       BBox bBox = new BBox(paramTriangulator, j, m);
/*     */       
/* 204 */       if (!NoHash.noHashIntersectionExists(paramTriangulator, k, paramInt, m, j, bBox)) {
/* 205 */         if (paramTriangulator.earsSorted) {
/*     */           
/* 207 */           paramArrayOfDouble[0] = Numerics.getRatio(paramTriangulator, j, m, k);
/*     */         } else {
/*     */           
/* 210 */           paramArrayOfDouble[0] = 1.0D;
/*     */         } 
/* 212 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 217 */     return false;
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
/*     */   static boolean clipEar(Triangulator paramTriangulator, boolean[] paramArrayOfBoolean) {
/*     */     int i3, i1, k, j;
/* 236 */     double[] arrayOfDouble = new double[1];
/* 237 */     int[] arrayOfInt1 = new int[1];
/* 238 */     int[] arrayOfInt2 = new int[1];
/* 239 */     int[] arrayOfInt3 = new int[1];
/* 240 */     int[] arrayOfInt4 = new int[1];
/* 241 */     int[] arrayOfInt5 = new int[1];
/* 242 */     int[] arrayOfInt6 = new int[1];
/*     */     
/* 244 */     boolean bool = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 252 */       if (!Heap.deleteFromHeap(paramTriangulator, arrayOfInt6, arrayOfInt2, arrayOfInt4))
/*     */       {
/* 254 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 258 */       j = paramTriangulator.fetchPrevData(arrayOfInt6[0]);
/* 259 */       i1 = paramTriangulator.fetchData(j);
/* 260 */       k = paramTriangulator.fetchNextData(arrayOfInt6[0]);
/* 261 */       i3 = paramTriangulator.fetchData(k);
/*     */     }
/* 263 */     while (arrayOfInt2[0] != j || arrayOfInt4[0] != k);
/*     */ 
/*     */ 
/*     */     
/* 267 */     int i2 = paramTriangulator.fetchData(arrayOfInt6[0]);
/*     */ 
/*     */     
/* 270 */     paramTriangulator.deleteLinks(arrayOfInt6[0]);
/*     */ 
/*     */ 
/*     */     
/* 274 */     paramTriangulator.storeTriangle(j, arrayOfInt6[0], k);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     int i = paramTriangulator.fetchPrevData(j);
/* 280 */     int n = paramTriangulator.fetchData(i);
/* 281 */     if (i == k) {
/*     */       
/* 283 */       paramArrayOfBoolean[0] = true;
/* 284 */       return true;
/*     */     } 
/* 286 */     int i5 = Numerics.isConvexAngle(paramTriangulator, n, i1, i3, j);
/*     */     
/* 288 */     int m = paramTriangulator.fetchNextData(k);
/* 289 */     int i4 = paramTriangulator.fetchData(m);
/*     */     
/* 291 */     int i6 = Numerics.isConvexAngle(paramTriangulator, i1, i3, i4, k);
/*     */     
/* 293 */     if (i1 != i3) {
/* 294 */       if (i5 >= 0 && paramTriangulator.getAngle(j) < 0)
/* 295 */         NoHash.deleteReflexVertex(paramTriangulator, j); 
/* 296 */       if (i6 >= 0 && paramTriangulator.getAngle(k) < 0) {
/* 297 */         NoHash.deleteReflexVertex(paramTriangulator, k);
/*     */       }
/*     */     }
/* 300 */     else if (i5 >= 0 && paramTriangulator.getAngle(j) < 0) {
/* 301 */       NoHash.deleteReflexVertex(paramTriangulator, j);
/* 302 */     } else if (i6 >= 0 && paramTriangulator.getAngle(k) < 0) {
/* 303 */       NoHash.deleteReflexVertex(paramTriangulator, k);
/*     */     } 
/*     */ 
/*     */     
/* 307 */     paramTriangulator.setAngle(j, i5);
/* 308 */     paramTriangulator.setAngle(k, i6);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 313 */     if (i5 > 0 && 
/* 314 */       isEar(paramTriangulator, j, arrayOfInt1, arrayOfInt3, arrayOfDouble))
/*     */     {
/* 316 */       Heap.insertIntoHeap(paramTriangulator, arrayOfDouble[0], j, arrayOfInt1[0], arrayOfInt3[0]);
/*     */     }
/*     */ 
/*     */     
/* 320 */     if (i6 > 0 && 
/* 321 */       isEar(paramTriangulator, k, arrayOfInt3, arrayOfInt5, arrayOfDouble)) {
/* 322 */       Heap.insertIntoHeap(paramTriangulator, arrayOfDouble[0], k, arrayOfInt3[0], arrayOfInt5[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 327 */     i = paramTriangulator.fetchPrevData(j);
/* 328 */     n = paramTriangulator.fetchData(i);
/* 329 */     m = paramTriangulator.fetchNextData(k);
/* 330 */     i4 = paramTriangulator.fetchData(m);
/* 331 */     if (i == m) {
/*     */       
/* 333 */       paramTriangulator.storeTriangle(j, k, m);
/* 334 */       paramArrayOfBoolean[0] = true;
/*     */     } else {
/*     */       
/* 337 */       paramArrayOfBoolean[0] = false;
/*     */     } 
/*     */     
/* 340 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\EarClip.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */