/*     */ package javax.media.j3d;
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
/*     */ class Texture2DRetained
/*     */   extends TextureRetained
/*     */ {
/*  32 */   private ImageComponent2DRetained detailImage = null;
/*  33 */   private int detailTextureMode = 1;
/*  34 */   private int detailTextureLevel = 2;
/*  35 */   private int numDetailTextureFuncPts = 0;
/*  36 */   private float[] detailTextureFuncPts = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initDetailImage(ImageComponent2D paramImageComponent2D) {
/*  44 */     if (paramImageComponent2D == null) {
/*  45 */       this.detailImage = null;
/*     */     } else {
/*  47 */       this.detailImage = (ImageComponent2DRetained)paramImageComponent2D.retained;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final ImageComponent2D getDetailImage() {
/*  56 */     if (this.detailImage != null) {
/*  57 */       return (ImageComponent2D)this.detailImage.source;
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   final void initDetailTextureMode(int paramInt) { this.detailTextureMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   final int getDetailTextureMode() { return this.detailTextureMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   final void initDetailTextureLevel(int paramInt) { this.detailTextureLevel = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   final int getDetailTextureLevel() { return this.detailTextureLevel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initDetailTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 100 */     if (paramArrayOfFloat1 == null) {
/* 101 */       this.detailTextureFuncPts = null;
/* 102 */       this.numDetailTextureFuncPts = 0;
/*     */     } else {
/* 104 */       this.numDetailTextureFuncPts = paramArrayOfFloat1.length;
/* 105 */       if (this.detailTextureFuncPts == null || this.detailTextureFuncPts.length != paramArrayOfFloat1.length * 2)
/*     */       {
/* 107 */         this.detailTextureFuncPts = new float[paramArrayOfFloat1.length * 2];
/*     */       }
/* 109 */       for (byte b1 = 0, b2 = 0; b1 < paramArrayOfFloat1.length; b1++) {
/* 110 */         this.detailTextureFuncPts[b2++] = paramArrayOfFloat1[b1];
/* 111 */         this.detailTextureFuncPts[b2++] = paramArrayOfFloat2[b1];
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   final void initDetailTextureFunc(Point2f[] paramArrayOfPoint2f) {
/* 117 */     if (paramArrayOfPoint2f == null) {
/* 118 */       this.detailTextureFuncPts = null;
/* 119 */       this.numDetailTextureFuncPts = 0;
/*     */     } else {
/* 121 */       this.numDetailTextureFuncPts = paramArrayOfPoint2f.length;
/* 122 */       if (this.detailTextureFuncPts == null || this.detailTextureFuncPts.length != paramArrayOfPoint2f.length * 2)
/*     */       {
/* 124 */         this.detailTextureFuncPts = new float[paramArrayOfPoint2f.length * 2];
/*     */       }
/* 126 */       for (byte b1 = 0, b2 = 0; b1 < paramArrayOfPoint2f.length; b1++) {
/* 127 */         this.detailTextureFuncPts[b2++] = (paramArrayOfPoint2f[b1]).x;
/* 128 */         this.detailTextureFuncPts[b2++] = (paramArrayOfPoint2f[b1]).y;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   final void initDetailTextureFunc(float[] paramArrayOfFloat) {
/* 134 */     if (paramArrayOfFloat == null) {
/* 135 */       this.detailTextureFuncPts = null;
/* 136 */       this.numDetailTextureFuncPts = 0;
/*     */     } else {
/* 138 */       this.numDetailTextureFuncPts = paramArrayOfFloat.length / 2;
/* 139 */       if (this.detailTextureFuncPts == null || this.detailTextureFuncPts.length != paramArrayOfFloat.length)
/*     */       {
/* 141 */         this.detailTextureFuncPts = new float[paramArrayOfFloat.length];
/*     */       }
/* 143 */       for (byte b = 0; b < paramArrayOfFloat.length; b++) {
/* 144 */         this.detailTextureFuncPts[b] = paramArrayOfFloat[b];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   final int getDetailTextureFuncPointsCount() { return this.numDetailTextureFuncPts; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void getDetailTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 162 */     if (this.detailTextureFuncPts != null) {
/* 163 */       for (byte b1 = 0, b2 = 0; b1 < this.numDetailTextureFuncPts; b1++) {
/* 164 */         paramArrayOfFloat1[b1] = this.detailTextureFuncPts[b2++];
/* 165 */         paramArrayOfFloat2[b1] = this.detailTextureFuncPts[b2++];
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   final void getDetailTextureFunc(Point2f[] paramArrayOfPoint2f) {
/* 171 */     if (this.detailTextureFuncPts != null) {
/* 172 */       for (byte b1 = 0, b2 = 0; b1 < this.numDetailTextureFuncPts; b1++) {
/* 173 */         (paramArrayOfPoint2f[b1]).x = this.detailTextureFuncPts[b2++];
/* 174 */         (paramArrayOfPoint2f[b1]).y = this.detailTextureFuncPts[b2++];
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   final float[] getDetailTextureFunc() { return this.detailTextureFuncPts; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Texture2DRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */