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
/*     */ class Orientation
/*     */ {
/*     */   static void adjustOrientation(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/*  79 */     if (paramInt1 >= paramInt2) {
/*  80 */       System.out.println("Orientation:adjustOrientation Problem i1>=i2 !!!");
/*     */     }
/*  82 */     if (paramTriangulator.numLoops >= paramTriangulator.maxNumPolyArea) {
/*     */       
/*  84 */       paramTriangulator.maxNumPolyArea = paramTriangulator.numLoops;
/*  85 */       double[] arrayOfDouble = paramTriangulator.polyArea;
/*  86 */       paramTriangulator.polyArea = new double[paramTriangulator.maxNumPolyArea];
/*  87 */       if (arrayOfDouble != null) {
/*  88 */         System.arraycopy(arrayOfDouble, 0, paramTriangulator.polyArea, 0, arrayOfDouble.length);
/*     */       }
/*     */     } 
/*     */     
/*     */     int i;
/*  93 */     for (i = paramInt1; i < paramInt2; i++) {
/*  94 */       int k = paramTriangulator.loops[i];
/*  95 */       paramTriangulator.polyArea[i] = polygonArea(paramTriangulator, k);
/*     */     } 
/*     */ 
/*     */     
/*  99 */     double d = Math.abs(paramTriangulator.polyArea[paramInt1]);
/* 100 */     int j = paramInt1;
/* 101 */     for (i = paramInt1 + 1; i < paramInt2; i++) {
/* 102 */       if (d < Math.abs(paramTriangulator.polyArea[i])) {
/* 103 */         d = Math.abs(paramTriangulator.polyArea[i]);
/* 104 */         j = i;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 109 */     if (j != paramInt1) {
/* 110 */       int k = paramTriangulator.loops[paramInt1];
/* 111 */       paramTriangulator.loops[paramInt1] = paramTriangulator.loops[j];
/* 112 */       paramTriangulator.loops[j] = k;
/*     */       
/* 114 */       d = paramTriangulator.polyArea[paramInt1];
/* 115 */       paramTriangulator.polyArea[paramInt1] = paramTriangulator.polyArea[j];
/* 116 */       paramTriangulator.polyArea[j] = d;
/*     */     } 
/*     */ 
/*     */     
/* 120 */     if (paramTriangulator.polyArea[paramInt1] < 0.0D) paramTriangulator.swapLinks(paramTriangulator.loops[paramInt1]); 
/* 121 */     for (i = paramInt1 + 1; i < paramInt2; i++) {
/* 122 */       if (paramTriangulator.polyArea[i] > 0.0D) paramTriangulator.swapLinks(paramTriangulator.loops[i]);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static double polygonArea(Triangulator paramTriangulator, int paramInt) {
/* 130 */     byte b = 0;
/*     */ 
/*     */     
/* 133 */     double d1 = 0.0D, d2 = 0.0D;
/*     */     
/* 135 */     int i = paramInt;
/* 136 */     int k = paramTriangulator.fetchData(i);
/* 137 */     int j = paramTriangulator.fetchNextData(i);
/* 138 */     int m = paramTriangulator.fetchData(j);
/* 139 */     d1 = Numerics.stableDet2D(paramTriangulator, b, k, m);
/*     */     
/* 141 */     i = j;
/* 142 */     k = m;
/* 143 */     while (i != paramInt) {
/* 144 */       j = paramTriangulator.fetchNextData(i);
/* 145 */       m = paramTriangulator.fetchData(j);
/* 146 */       d2 = Numerics.stableDet2D(paramTriangulator, b, k, m);
/* 147 */       d1 += d2;
/* 148 */       i = j;
/* 149 */       k = m;
/*     */     } 
/*     */     
/* 152 */     return d1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void determineOrientation(Triangulator paramTriangulator, int paramInt) {
/* 163 */     double d = polygonArea(paramTriangulator, paramInt);
/*     */ 
/*     */     
/* 166 */     if (d < 0.0D) {
/* 167 */       paramTriangulator.swapLinks(paramInt);
/* 168 */       paramTriangulator.ccwLoop = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\Orientation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */