/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CubicSplineCurve
/*     */ {
/*     */   private float totalCurveLength;
/*     */   private CubicSplineSegment[] cubicSplineSegment;
/*     */   public int numSegments;
/*     */   
/*     */   CubicSplineCurve() {
/*  70 */     this.numSegments = 0;
/*  71 */     this.totalCurveLength = 0.0F;
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
/*     */   CubicSplineCurve(TCBKeyFrame[] paramArrayOfTCBKeyFrame) {
/*  83 */     int i = paramArrayOfTCBKeyFrame.length;
/*     */     
/*  85 */     if (i < 4) {
/*  86 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("CubicSplineCurve0"));
/*     */     }
/*  88 */     this.numSegments = i - 3;
/*  89 */     this.cubicSplineSegment = new CubicSplineSegment[this.numSegments];
/*     */ 
/*     */     
/*  92 */     byte b1 = 0, b2 = 1, b3 = 2, b4 = 3;
/*  93 */     for (; b1 < this.numSegments; b1++, b2++, b3++, b4++) {
/*  94 */       this.cubicSplineSegment[b1] = new CubicSplineSegment(paramArrayOfTCBKeyFrame[b1], paramArrayOfTCBKeyFrame[b2], paramArrayOfTCBKeyFrame[b3], paramArrayOfTCBKeyFrame[b4]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  99 */     computeTotalCurveLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CubicSplineCurve(CubicSplineSegment[] paramArrayOfCubicSplineSegment) {
/* 110 */     this.cubicSplineSegment = new CubicSplineSegment[paramArrayOfCubicSplineSegment.length];
/* 111 */     this.numSegments = this.cubicSplineSegment.length;
/* 112 */     for (byte b = 0; b < this.numSegments; b++) {
/* 113 */       this.cubicSplineSegment[b] = paramArrayOfCubicSplineSegment[b];
/*     */     }
/*     */ 
/*     */     
/* 117 */     computeTotalCurveLength();
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
/*     */   public void setSegments(CubicSplineSegment[] paramArrayOfCubicSplineSegment) {
/* 129 */     this.cubicSplineSegment = new CubicSplineSegment[paramArrayOfCubicSplineSegment.length];
/* 130 */     this.numSegments = this.cubicSplineSegment.length;
/* 131 */     for (byte b = 0; b < this.numSegments; b++) {
/* 132 */       this.cubicSplineSegment[b] = paramArrayOfCubicSplineSegment[b];
/*     */     }
/*     */ 
/*     */     
/* 136 */     computeTotalCurveLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public CubicSplineSegment getSegment(int paramInt) { return this.cubicSplineSegment[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeTotalCurveLength() {
/* 154 */     this.totalCurveLength = 0.0F;
/* 155 */     for (byte b = 0; b < this.numSegments; b++) {
/* 156 */       this.totalCurveLength += (this.cubicSplineSegment[b]).length;
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
/*     */ 
/*     */ 
/*     */   
/* 170 */   public float getTotalCurveLength() { return this.totalCurveLength; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\interpolators\CubicSplineCurve.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */