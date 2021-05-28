/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Interpolator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class FloatValueInterpolator
/*     */   extends Interpolator
/*     */ {
/*     */   private float[] knots;
/*     */   private int knotsLength;
/*     */   protected int currentKnotIndex;
/*     */   protected float currentInterpolationRatio;
/*     */   protected float[] values;
/*     */   protected float currentValue;
/*     */   
/*     */   FloatValueInterpolator(Alpha paramAlpha, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/*  79 */     super(paramAlpha);
/*     */ 
/*     */     
/*  82 */     this.knotsLength = paramArrayOfFloat1.length;
/*  83 */     if (paramArrayOfFloat1[0] < -1.0E-4D || paramArrayOfFloat1[0] > 1.0E-4D) {
/*  84 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("FloatValueInterpolator0"));
/*     */     }
/*     */ 
/*     */     
/*  88 */     if ((paramArrayOfFloat1[this.knotsLength - 1] - 1.0F) < -1.0E-4D || (paramArrayOfFloat1[this.knotsLength - 1] - 1.0F) > 1.0E-4D)
/*     */     {
/*     */       
/*  91 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("FloatValueInterpolator1"));
/*     */     }
/*     */ 
/*     */     
/*  95 */     this.knots = new float[this.knotsLength]; byte b;
/*  96 */     for (b = 0; b < this.knotsLength; b++) {
/*  97 */       if (b && paramArrayOfFloat1[b] < paramArrayOfFloat1[b - true]) {
/*  98 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("FloatValueInterpolator2"));
/*     */       }
/* 100 */       this.knots[b] = paramArrayOfFloat1[b];
/*     */     } 
/*     */ 
/*     */     
/* 104 */     if (this.knotsLength != paramArrayOfFloat2.length) {
/* 105 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("FloatValueInterpolator3"));
/*     */     }
/*     */ 
/*     */     
/* 109 */     this.values = new float[this.knotsLength];
/* 110 */     for (b = 0; b < this.knotsLength; b++) {
/* 111 */       this.values[b] = paramArrayOfFloat2[b];
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
/* 123 */   void setValue(int paramInt, float paramFloat) { this.values[paramInt] = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   float getValue(int paramInt) { return this.values[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computePathInterpolation() {
/* 147 */     float f = getAlpha().value();
/*     */     
/* 149 */     for (byte b = 0; b < this.knotsLength; b++) {
/* 150 */       if ((!b && f <= this.knots[b]) || (b && f >= this.knots[b - true] && f <= this.knots[b])) {
/*     */ 
/*     */         
/* 153 */         if (!b) {
/* 154 */           this.currentInterpolationRatio = 0.0F;
/* 155 */           this.currentKnotIndex = 0;
/* 156 */           this.currentValue = this.values[0];
/*     */           break;
/*     */         } 
/* 159 */         this.currentInterpolationRatio = (f - this.knots[b - true]) / (this.knots[b] - this.knots[b - true]);
/*     */         
/* 161 */         this.currentKnotIndex = b - true;
/* 162 */         this.currentValue = this.values[b - true] + this.currentInterpolationRatio * (this.values[b] - this.values[b - true]);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\lw3d\FloatValueInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */