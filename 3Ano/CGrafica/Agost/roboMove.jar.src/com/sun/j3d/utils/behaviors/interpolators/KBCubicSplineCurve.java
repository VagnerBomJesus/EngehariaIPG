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
/*     */ public class KBCubicSplineCurve
/*     */ {
/*     */   private float totalCurveLength;
/*     */   private KBCubicSplineSegment[] cubicSplineSegment;
/*     */   public int numSegments;
/*     */   
/*     */   KBCubicSplineCurve() {
/*  68 */     this.numSegments = 0;
/*  69 */     this.totalCurveLength = 0.0F;
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
/*     */   KBCubicSplineCurve(KBKeyFrame[] paramArrayOfKBKeyFrame) {
/*  81 */     int i = paramArrayOfKBKeyFrame.length;
/*     */     
/*  83 */     if (i < 4) {
/*  84 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("KBCubicSplineCurve0"));
/*     */     }
/*     */     
/*  87 */     this.numSegments = i - 3;
/*  88 */     this.cubicSplineSegment = new KBCubicSplineSegment[this.numSegments];
/*     */ 
/*     */     
/*  91 */     byte b1 = 0, b2 = 1, b3 = 2, b4 = 3;
/*  92 */     for (; b1 < this.numSegments; b1++, b2++, b3++, b4++) {
/*  93 */       this.cubicSplineSegment[b1] = new KBCubicSplineSegment(paramArrayOfKBKeyFrame[b1], paramArrayOfKBKeyFrame[b2], paramArrayOfKBKeyFrame[b3], paramArrayOfKBKeyFrame[b4]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  98 */     computeTotalCurveLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   KBCubicSplineCurve(KBCubicSplineSegment[] paramArrayOfKBCubicSplineSegment) {
/* 109 */     this.cubicSplineSegment = new KBCubicSplineSegment[paramArrayOfKBCubicSplineSegment.length];
/* 110 */     this.numSegments = this.cubicSplineSegment.length;
/* 111 */     for (byte b = 0; b < this.numSegments; b++) {
/* 112 */       this.cubicSplineSegment[b] = paramArrayOfKBCubicSplineSegment[b];
/*     */     }
/*     */ 
/*     */     
/* 116 */     computeTotalCurveLength();
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
/*     */   public void setSegments(KBCubicSplineSegment[] paramArrayOfKBCubicSplineSegment) {
/* 128 */     this.cubicSplineSegment = new KBCubicSplineSegment[paramArrayOfKBCubicSplineSegment.length];
/* 129 */     this.numSegments = this.cubicSplineSegment.length;
/* 130 */     for (byte b = 0; b < this.numSegments; b++) {
/* 131 */       this.cubicSplineSegment[b] = paramArrayOfKBCubicSplineSegment[b];
/*     */     }
/*     */ 
/*     */     
/* 135 */     computeTotalCurveLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public KBCubicSplineSegment getSegment(int paramInt) { return this.cubicSplineSegment[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeTotalCurveLength() {
/* 153 */     this.totalCurveLength = 0.0F;
/* 154 */     for (byte b = 0; b < this.numSegments; b++) {
/* 155 */       this.totalCurveLength += (this.cubicSplineSegment[b]).length;
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
/* 169 */   public float getTotalCurveLength() { return this.totalCurveLength; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\behaviors\interpolators\KBCubicSplineCurve.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */