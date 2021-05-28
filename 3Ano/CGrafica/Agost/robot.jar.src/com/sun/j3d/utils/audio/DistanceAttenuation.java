/*     */ package com.sun.j3d.utils.audio;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import javax.media.j3d.SoundException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DistanceAttenuation
/*     */ {
/*     */   static final int DOUBLE_DISTANCE_HALF_GAIN = 1;
/*     */   
/*     */   public void fillDistanceAttenuation(float paramFloat1, float paramFloat2, Point2f[] paramArrayOfPoint2f) {
/*  87 */     if (paramArrayOfPoint2f == null) {
/*  88 */       throw new SoundException(J3dUtilsI18N.getString("DistanceAttenuation0"));
/*     */     }
/*  90 */     int i = paramArrayOfPoint2f.length;
/*  91 */     (paramArrayOfPoint2f[0]).x = 0.0F;
/*  92 */     (paramArrayOfPoint2f[0]).y = paramFloat2;
/*  93 */     float f1 = paramFloat1;
/*  94 */     float f2 = paramFloat2;
/*     */     
/*  96 */     for (byte b = 1; b < i; b++) {
/*  97 */       (paramArrayOfPoint2f[b]).x = f1;
/*  98 */       (paramArrayOfPoint2f[b]).y = f2;
/*  99 */       f1 *= 2.0F;
/* 100 */       f2 *= 0.5F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillDistanceAttenuation(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Point2f[] paramArrayOfPoint2f) {
/* 108 */     if (paramArrayOfPoint2f == null) {
/* 109 */       throw new SoundException(J3dUtilsI18N.getString("DistanceAttenuation0"));
/*     */     }
/* 111 */     int i = paramArrayOfPoint2f.length;
/* 112 */     (paramArrayOfPoint2f[0]).x = paramFloat1;
/* 113 */     (paramArrayOfPoint2f[0]).y = paramFloat2;
/*     */ 
/*     */     
/* 116 */     float f1 = paramFloat3;
/* 117 */     float f2 = paramFloat4;
/*     */     
/* 119 */     for (byte b = 1; b < i; b++) {
/* 120 */       (paramArrayOfPoint2f[b]).x = paramFloat1 + f1;
/* 121 */       (paramArrayOfPoint2f[b]).y = f2;
/* 122 */       f1 *= 2.0F;
/* 123 */       f2 *= 0.5F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fillDistanceAttenuation(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, int paramInt, Point2f[] paramArrayOfPoint2f) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\audio\DistanceAttenuation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */