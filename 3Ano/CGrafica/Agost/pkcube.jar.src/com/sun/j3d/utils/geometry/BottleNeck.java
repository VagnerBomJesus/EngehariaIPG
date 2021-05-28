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
/*     */ class BottleNeck
/*     */ {
/*     */   static boolean checkArea(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/*  71 */     double d1 = 0.0D, d2 = 0.0D, d3 = 0.0D;
/*     */     
/*  73 */     int j = paramTriangulator.fetchData(paramInt1);
/*  74 */     int i = paramTriangulator.fetchNextData(paramInt1);
/*  75 */     int k = paramTriangulator.fetchData(i);
/*     */     
/*  77 */     while (i != paramInt2) {
/*  78 */       int m = paramTriangulator.fetchNextData(i);
/*  79 */       int n = paramTriangulator.fetchData(m);
/*  80 */       d1 = Numerics.stableDet2D(paramTriangulator, j, k, n);
/*  81 */       d2 += d1;
/*  82 */       i = m;
/*  83 */       k = n;
/*     */     } 
/*     */     
/*  86 */     paramTriangulator; if (Numerics.le(d2, 1.0E-8D)) return false;
/*     */     
/*  88 */     i = paramTriangulator.fetchNextData(paramInt2);
/*  89 */     k = paramTriangulator.fetchData(i);
/*  90 */     while (i != paramInt1) {
/*  91 */       int m = paramTriangulator.fetchNextData(i);
/*  92 */       int n = paramTriangulator.fetchData(m);
/*  93 */       d1 = Numerics.stableDet2D(paramTriangulator, j, k, n);
/*  94 */       d3 += d1;
/*  95 */       i = m;
/*  96 */       k = n;
/*     */     } 
/*     */     
/*  99 */     paramTriangulator; if (Numerics.le(d3, 1.0E-8D)) return false; 
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean checkBottleNeck(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*     */     boolean bool;
/* 111 */     int j = paramInt1;
/*     */     
/* 113 */     int i = paramTriangulator.fetchPrevData(paramInt4);
/* 114 */     int k = paramTriangulator.fetchData(i);
/* 115 */     if (k != paramInt2 && k != paramInt3) {
/* 116 */       bool = Numerics.pntInTriangle(paramTriangulator, paramInt1, paramInt2, paramInt3, k);
/* 117 */       if (bool) return true;
/*     */     
/*     */     } 
/* 120 */     if (paramInt2 <= paramInt3)
/* 121 */     { if (j <= k) { bool = Numerics.segIntersect(paramTriangulator, paramInt2, paramInt3, j, k, -1); }
/* 122 */       else { bool = Numerics.segIntersect(paramTriangulator, paramInt2, paramInt3, k, j, -1); }
/*     */       
/*     */        }
/* 125 */     else if (j <= k) { bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt2, j, k, -1); }
/* 126 */     else { bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt2, k, j, -1); }
/*     */     
/* 128 */     if (bool) return true;
/*     */     
/* 130 */     i = paramTriangulator.fetchNextData(paramInt4);
/* 131 */     k = paramTriangulator.fetchData(i);
/*     */     
/* 133 */     if (k != paramInt2 && k != paramInt3) {
/* 134 */       bool = Numerics.pntInTriangle(paramTriangulator, paramInt1, paramInt2, paramInt3, k);
/* 135 */       if (bool) return true;
/*     */     
/*     */     } 
/* 138 */     if (paramInt2 <= paramInt3)
/* 139 */     { if (j <= k) { bool = Numerics.segIntersect(paramTriangulator, paramInt2, paramInt3, j, k, -1); }
/* 140 */       else { bool = Numerics.segIntersect(paramTriangulator, paramInt2, paramInt3, k, j, -1); }
/*     */       
/*     */        }
/* 143 */     else if (j <= k) { bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt2, j, k, -1); }
/* 144 */     else { bool = Numerics.segIntersect(paramTriangulator, paramInt3, paramInt2, k, j, -1); }
/*     */ 
/*     */     
/* 147 */     if (bool) return true;
/*     */     
/* 149 */     i = paramTriangulator.fetchNextData(paramInt4);
/* 150 */     k = paramTriangulator.fetchData(i);
/* 151 */     while (i != paramInt4) {
/* 152 */       if (j == k && 
/* 153 */         checkArea(paramTriangulator, paramInt4, i)) return true;
/*     */       
/* 155 */       i = paramTriangulator.fetchNextData(i);
/* 156 */       k = paramTriangulator.fetchData(i);
/*     */     } 
/*     */     
/* 159 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\BottleNeck.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */