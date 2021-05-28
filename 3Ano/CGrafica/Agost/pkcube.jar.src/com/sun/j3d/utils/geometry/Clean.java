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
/*     */ class Clean
/*     */ {
/*     */   static void initPUnsorted(Triangulator paramTriangulator, int paramInt) {
/*  69 */     if (paramInt > paramTriangulator.maxNumPUnsorted) {
/*  70 */       paramTriangulator.maxNumPUnsorted = paramInt;
/*  71 */       paramTriangulator.pUnsorted = new Point2f[paramTriangulator.maxNumPUnsorted];
/*  72 */       for (byte b = 0; b < paramTriangulator.maxNumPUnsorted; b++) {
/*  73 */         paramTriangulator.pUnsorted[b] = new Point2f();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int cleanPolyhedralFace(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/*  83 */     initPUnsorted(paramTriangulator, paramTriangulator.numPoints);
/*     */     int j;
/*  85 */     for (j = 0; j < paramTriangulator.numPoints; j++) {
/*  86 */       paramTriangulator.pUnsorted[j].set(paramTriangulator.points[j]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     sort(paramTriangulator.points, paramTriangulator.numPoints);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     j = 0; int k;
/* 109 */     for (k = 1; k < paramTriangulator.numPoints; k++) {
/* 110 */       if (pComp(paramTriangulator.points[j], paramTriangulator.points[k]) != 0) {
/* 111 */         j++;
/* 112 */         paramTriangulator.points[j] = paramTriangulator.points[k];
/*     */       } 
/*     */     } 
/* 115 */     int m = j + 1;
/* 116 */     int i = paramTriangulator.numPoints - m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     for (j = paramInt1; j < paramInt2; j++) {
/* 127 */       int i1 = paramTriangulator.loops[j];
/* 128 */       int i2 = paramTriangulator.fetchNextData(i1);
/* 129 */       int n = paramTriangulator.fetchData(i2);
/* 130 */       while (i2 != i1) {
/* 131 */         k = findPInd(paramTriangulator.points, m, paramTriangulator.pUnsorted[n]);
/* 132 */         paramTriangulator.updateIndex(i2, k);
/* 133 */         i2 = paramTriangulator.fetchNextData(i2);
/* 134 */         n = paramTriangulator.fetchData(i2);
/*     */       } 
/* 136 */       k = findPInd(paramTriangulator.points, m, paramTriangulator.pUnsorted[n]);
/* 137 */       paramTriangulator.updateIndex(i2, k);
/*     */     } 
/*     */     
/* 140 */     paramTriangulator.numPoints = m;
/*     */     
/* 142 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void sort(Point2f[] paramArrayOfPoint2f, int paramInt) {
/* 148 */     Point2f point2f = new Point2f();
/*     */     
/* 150 */     for (byte b = 0; b < paramInt; b++) {
/* 151 */       for (byte b1 = b + true; b1 < paramInt; b1++) {
/* 152 */         if (pComp(paramArrayOfPoint2f[b], paramArrayOfPoint2f[b1]) > 0) {
/* 153 */           point2f.set(paramArrayOfPoint2f[b]);
/* 154 */           paramArrayOfPoint2f[b].set(paramArrayOfPoint2f[b1]);
/* 155 */           paramArrayOfPoint2f[b1].set(point2f);
/*     */         } 
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
/*     */   static int findPInd(Point2f[] paramArrayOfPoint2f, int paramInt, Point2f paramPoint2f) {
/* 169 */     for (byte b = 0; b < paramInt; b++) {
/* 170 */       if (paramPoint2f.x == (paramArrayOfPoint2f[b]).x && paramPoint2f.y == (paramArrayOfPoint2f[b]).y)
/*     */       {
/* 172 */         return b;
/*     */       }
/*     */     } 
/* 175 */     return -1;
/*     */   }
/*     */   
/*     */   static int pComp(Point2f paramPoint2f1, Point2f paramPoint2f2) {
/* 179 */     if (paramPoint2f1.x < paramPoint2f2.x)
/* 180 */       return -1; 
/* 181 */     if (paramPoint2f1.x > paramPoint2f2.x) {
/* 182 */       return 1;
/*     */     }
/* 184 */     if (paramPoint2f1.y < paramPoint2f2.y)
/* 185 */       return -1; 
/* 186 */     if (paramPoint2f1.y > paramPoint2f2.y) {
/* 187 */       return 1;
/*     */     }
/* 189 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\Clean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */