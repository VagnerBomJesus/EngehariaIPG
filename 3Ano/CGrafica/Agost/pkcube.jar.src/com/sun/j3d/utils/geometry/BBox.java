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
/*     */ class BBox
/*     */ {
/*     */   int imin;
/*     */   int imax;
/*     */   double ymin;
/*     */   double ymax;
/*     */   
/*     */   BBox(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/*  83 */     this.imin = Math.min(paramInt1, paramInt2);
/*  84 */     this.imax = Math.max(paramInt1, paramInt2);
/*  85 */     this.ymin = Math.min((paramTriangulator.points[this.imin]).y, (paramTriangulator.points[this.imax]).y);
/*  86 */     this.ymax = Math.max((paramTriangulator.points[this.imin]).y, (paramTriangulator.points[this.imax]).y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  91 */   boolean pntInBBox(Triangulator paramTriangulator, int paramInt) { return (this.imax < paramInt) ? false : ((this.imin > paramInt) ? false : ((this.ymax < (paramTriangulator.points[paramInt]).y) ? false : (!(this.ymin > (paramTriangulator.points[paramInt]).y)))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   boolean BBoxOverlap(BBox paramBBox) { return (this.imax < paramBBox.imin) ? false : ((this.imin > paramBBox.imax) ? false : ((this.ymax < paramBBox.ymin) ? false : (!(this.ymin > paramBBox.ymax)))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   boolean BBoxContained(BBox paramBBox) { return (this.imin <= paramBBox.imin && this.imax >= paramBBox.imax && this.ymin <= paramBBox.ymin && this.ymax >= paramBBox.ymax); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   boolean BBoxIdenticalLeaf(BBox paramBBox) { return (this.imin == paramBBox.imin && this.imax == paramBBox.imax); }
/*     */ 
/*     */ 
/*     */   
/*     */   void BBoxUnion(BBox paramBBox1, BBox paramBBox2) {
/* 118 */     paramBBox2.imin = Math.min(this.imin, paramBBox1.imin);
/* 119 */     paramBBox2.imax = Math.max(this.imax, paramBBox1.imax);
/* 120 */     paramBBox2.ymin = Math.min(this.ymin, paramBBox1.ymin);
/* 121 */     paramBBox2.ymax = Math.max(this.ymax, paramBBox1.ymax);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 126 */   double BBoxArea(Triangulator paramTriangulator) { return ((paramTriangulator.points[this.imax]).x - (paramTriangulator.points[this.imin]).x) * (this.ymax - this.ymin); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\BBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */