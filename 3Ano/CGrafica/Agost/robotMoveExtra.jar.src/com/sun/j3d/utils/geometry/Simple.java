/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Simple
/*     */ {
/*     */   static boolean simpleFace(Triangulator paramTriangulator, int paramInt) {
/*  88 */     int i = paramTriangulator.fetchPrevData(paramInt);
/*  89 */     int i2 = paramTriangulator.fetchData(i);
/*     */     
/*  91 */     if (i == paramInt) {
/*     */       
/*  93 */       System.out.println("***** polygon with only one vertex?! *****\n");
/*  94 */       return true;
/*     */     } 
/*     */     
/*  97 */     int j = paramTriangulator.fetchNextData(paramInt);
/*  98 */     int n = paramTriangulator.fetchData(j);
/*  99 */     if (i == j) {
/*     */       
/* 101 */       System.out.println("***** polygon with only two vertices?! *****\n");
/* 102 */       return true;
/*     */     } 
/*     */     
/* 105 */     int k = paramTriangulator.fetchNextData(j);
/* 106 */     int i1 = paramTriangulator.fetchData(k);
/* 107 */     if (i == k) {
/*     */       
/* 109 */       int i4 = paramTriangulator.fetchData(paramInt);
/*     */       
/* 111 */       paramTriangulator.storeTriangle(paramInt, j, k);
/* 112 */       return true;
/*     */     } 
/*     */     
/* 115 */     int m = paramTriangulator.fetchNextData(k);
/* 116 */     int i3 = paramTriangulator.fetchData(m);
/* 117 */     if (i == m) {
/*     */ 
/*     */ 
/*     */       
/* 121 */       paramTriangulator.initPnts(5);
/* 122 */       int i4 = paramTriangulator.fetchData(paramInt);
/*     */       
/* 124 */       Point3f point3f1 = new Point3f();
/* 125 */       Point3f point3f2 = new Point3f();
/* 126 */       Point3f point3f3 = new Point3f();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 138 */       Basic.vectorSub(paramTriangulator.vertices[i4], paramTriangulator.vertices[n], point3f1);
/* 139 */       Basic.vectorSub(paramTriangulator.vertices[i1], paramTriangulator.vertices[n], point3f2);
/* 140 */       Basic.vectorProduct(point3f1, point3f2, point3f3);
/*     */ 
/*     */       
/* 143 */       double d1 = Math.abs(point3f3.x);
/* 144 */       double d2 = Math.abs(point3f3.y);
/* 145 */       double d3 = Math.abs(point3f3.z);
/* 146 */       if (d3 >= d1 && d3 >= d2) {
/*     */         
/* 148 */         (paramTriangulator.points[1]).x = (paramTriangulator.vertices[i4]).x;
/* 149 */         (paramTriangulator.points[1]).y = (paramTriangulator.vertices[i4]).y;
/* 150 */         (paramTriangulator.points[2]).x = (paramTriangulator.vertices[n]).x;
/* 151 */         (paramTriangulator.points[2]).y = (paramTriangulator.vertices[n]).y;
/* 152 */         (paramTriangulator.points[3]).x = (paramTriangulator.vertices[i1]).x;
/* 153 */         (paramTriangulator.points[3]).y = (paramTriangulator.vertices[i1]).y;
/* 154 */         (paramTriangulator.points[4]).x = (paramTriangulator.vertices[i3]).x;
/* 155 */         (paramTriangulator.points[4]).y = (paramTriangulator.vertices[i3]).y;
/*     */       }
/* 157 */       else if (d1 >= d2 && d1 >= d3) {
/*     */         
/* 159 */         (paramTriangulator.points[1]).x = (paramTriangulator.vertices[i4]).z;
/* 160 */         (paramTriangulator.points[1]).y = (paramTriangulator.vertices[i4]).y;
/* 161 */         (paramTriangulator.points[2]).x = (paramTriangulator.vertices[n]).z;
/* 162 */         (paramTriangulator.points[2]).y = (paramTriangulator.vertices[n]).y;
/* 163 */         (paramTriangulator.points[3]).x = (paramTriangulator.vertices[i1]).z;
/* 164 */         (paramTriangulator.points[3]).y = (paramTriangulator.vertices[i1]).y;
/* 165 */         (paramTriangulator.points[4]).x = (paramTriangulator.vertices[i3]).z;
/* 166 */         (paramTriangulator.points[4]).y = (paramTriangulator.vertices[i3]).y;
/*     */       } else {
/*     */         
/* 169 */         (paramTriangulator.points[1]).x = (paramTriangulator.vertices[i4]).x;
/* 170 */         (paramTriangulator.points[1]).y = (paramTriangulator.vertices[i4]).z;
/* 171 */         (paramTriangulator.points[2]).x = (paramTriangulator.vertices[n]).x;
/* 172 */         (paramTriangulator.points[2]).y = (paramTriangulator.vertices[n]).z;
/* 173 */         (paramTriangulator.points[3]).x = (paramTriangulator.vertices[i1]).x;
/* 174 */         (paramTriangulator.points[3]).y = (paramTriangulator.vertices[i1]).z;
/* 175 */         (paramTriangulator.points[4]).x = (paramTriangulator.vertices[i3]).x;
/* 176 */         (paramTriangulator.points[4]).y = (paramTriangulator.vertices[i3]).z;
/*     */       } 
/* 178 */       paramTriangulator.numPoints = 5;
/*     */ 
/*     */       
/* 181 */       int i5 = Numerics.orientation(paramTriangulator, 1, 2, 3);
/* 182 */       int i6 = Numerics.orientation(paramTriangulator, 1, 3, 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 190 */       if ((i5 > 0 && i6 > 0) || (i5 < 0 && i6 < 0)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 200 */         paramTriangulator.storeTriangle(paramInt, j, k);
/* 201 */         paramTriangulator.storeTriangle(paramInt, k, m);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 216 */         paramTriangulator.storeTriangle(j, k, m);
/* 217 */         paramTriangulator.storeTriangle(j, m, paramInt);
/*     */       } 
/* 219 */       return true;
/*     */     } 
/*     */     
/* 222 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\Simple.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */