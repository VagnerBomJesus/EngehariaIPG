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
/*     */ 
/*     */ class Degenerate
/*     */ {
/*     */   static boolean handleDegeneracies(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  83 */     int[] arrayOfInt = new int[1];
/*     */ 
/*     */     
/*  86 */     double d1 = 0.0D, d2 = 0.0D, d3 = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     int m = paramTriangulator.fetchPrevData(paramInt6);
/*  98 */     int j = paramTriangulator.fetchData(m);
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (j != paramInt3 && j != paramInt4) {
/* 103 */       boolean bool = Numerics.vtxInTriangle(paramTriangulator, paramInt1, paramInt3, paramInt4, j, arrayOfInt);
/* 104 */       if (bool && arrayOfInt[0] == 0) return true; 
/* 105 */       if (paramInt3 <= paramInt4) {
/* 106 */         if (paramInt5 <= j) {
/* 107 */           bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt4, paramInt5, j, -1);
/*     */         } else {
/* 109 */           bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt4, j, paramInt5, -1);
/*     */         }
/*     */       
/* 112 */       } else if (paramInt5 <= j) {
/* 113 */         bool = Numerics.segIntersect(paramTriangulator, paramInt4, paramInt3, paramInt5, j, -1);
/*     */       } else {
/* 115 */         bool = Numerics.segIntersect(paramTriangulator, paramInt4, paramInt3, j, paramInt5, -1);
/*     */       } 
/* 117 */       if (bool) {
/* 118 */         return true;
/*     */       }
/*     */     } 
/* 121 */     m = paramTriangulator.fetchNextData(paramInt6);
/* 122 */     j = paramTriangulator.fetchData(m);
/*     */ 
/*     */     
/* 125 */     if (j != paramInt3 && j != paramInt4) {
/* 126 */       boolean bool = Numerics.vtxInTriangle(paramTriangulator, paramInt1, paramInt3, paramInt4, j, arrayOfInt);
/* 127 */       if (bool && arrayOfInt[0] == 0) return true; 
/* 128 */       if (paramInt3 <= paramInt4)
/* 129 */       { if (paramInt5 <= j) { bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt4, paramInt5, j, -1); }
/* 130 */         else { bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt4, j, paramInt5, -1); }
/*     */         
/*     */          }
/* 133 */       else if (paramInt5 <= j) { bool = Numerics.segIntersect(paramTriangulator, paramInt4, paramInt3, paramInt5, j, -1); }
/* 134 */       else { bool = Numerics.segIntersect(paramTriangulator, paramInt4, paramInt3, j, paramInt5, -1); }
/*     */       
/* 136 */       if (bool) return true;
/*     */     
/*     */     } 
/* 139 */     int i = paramInt1;
/* 140 */     int k = paramInt2;
/* 141 */     paramInt2 = paramTriangulator.fetchNextData(paramInt2);
/* 142 */     paramInt1 = paramTriangulator.fetchData(paramInt2);
/* 143 */     while (paramInt2 != paramInt6) {
/* 144 */       int n = paramTriangulator.fetchNextData(paramInt2);
/* 145 */       paramInt3 = paramTriangulator.fetchData(n);
/* 146 */       d1 = Numerics.stableDet2D(paramTriangulator, i, paramInt1, paramInt3);
/* 147 */       d2 += d1;
/* 148 */       paramInt2 = n;
/* 149 */       paramInt1 = paramInt3;
/*     */     } 
/*     */     
/* 152 */     paramInt2 = paramTriangulator.fetchPrevData(k);
/* 153 */     paramInt1 = paramTriangulator.fetchData(paramInt2);
/* 154 */     while (paramInt2 != paramInt6) {
/* 155 */       int n = paramTriangulator.fetchPrevData(paramInt2);
/* 156 */       paramInt3 = paramTriangulator.fetchData(n);
/* 157 */       d1 = Numerics.stableDet2D(paramTriangulator, i, paramInt1, paramInt3);
/* 158 */       d3 += d1;
/* 159 */       paramInt2 = n;
/* 160 */       paramInt1 = paramInt3;
/*     */     } 
/*     */     
/* 163 */     paramTriangulator; paramTriangulator; if (Numerics.le(d2, 1.0E-8D) && Numerics.le(d3, 1.0E-8D))
/* 164 */       return false; 
/* 165 */     paramTriangulator; paramTriangulator; if (Numerics.ge(d2, 1.0E-8D) && Numerics.ge(d3, 1.0E-8D)) {
/* 166 */       return false;
/*     */     }
/* 168 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\Degenerate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */