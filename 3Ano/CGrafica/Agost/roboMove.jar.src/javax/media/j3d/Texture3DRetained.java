/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Texture3DRetained
/*     */   extends TextureRetained
/*     */ {
/*  25 */   int boundaryModeR = 3;
/*  26 */   int depth = 1;
/*     */ 
/*     */   
/*  29 */   final void setDepth(int paramInt) { this.depth = paramInt; }
/*     */ 
/*     */ 
/*     */   
/*  33 */   final int getDepth() { return this.depth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   final void initBoundaryModeR(int paramInt) { this.boundaryModeR = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   final int getBoundaryModeR() { return this.boundaryModeR; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   void bindTexture(Context paramContext, int paramInt, boolean paramBoolean) { Pipeline.getPipeline().bindTexture3D(paramContext, paramInt, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   void updateTextureBoundary(Context paramContext, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { Pipeline.getPipeline().updateTexture3DBoundary(paramContext, paramInt1, paramInt2, paramInt3, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   void updateTextureFilterModes(Context paramContext, int paramInt1, int paramInt2) { Pipeline.getPipeline().updateTexture3DFilterModes(paramContext, paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   void updateTextureSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { Pipeline.getPipeline().updateTexture3DSharpenFunc(paramContext, paramInt, paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   void updateTextureFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { Pipeline.getPipeline().updateTexture3DFilter4Func(paramContext, paramInt, paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   void updateTextureAnisotropicFilter(Context paramContext, float paramFloat) { Pipeline.getPipeline().updateTexture3DAnisotropicFilter(paramContext, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   void updateTextureImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, Object paramObject) { Pipeline.getPipeline().updateTexture3DImage(paramCanvas3D.ctx, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramObject, useAutoMipMapGeneration(paramCanvas3D)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   void updateTextureSubImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, int paramInt16, Object paramObject) { Pipeline.getPipeline().updateTexture3DSubImage(paramCanvas3D.ctx, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramInt12, paramInt13, paramInt14, paramInt15, paramInt16, paramObject, useAutoMipMapGeneration(paramCanvas3D)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   int getTextureId() { return VirtualUniverse.mc.getTexture3DId(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void freeTextureId(int paramInt) {
/* 148 */     synchronized (this.resourceLock) {
/* 149 */       if (this.objectId == paramInt) {
/* 150 */         this.objectId = -1;
/* 151 */         VirtualUniverse.mc.freeTexture3DId(paramInt);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTextureDimensions(Canvas3D paramCanvas3D) {
/* 161 */     if (this.images[false][false] != null) {
/* 162 */       updateTextureImage(paramCanvas3D, this.maxLevels, 0, 0, this.format, this.images[0][0].getImageFormatTypeIntValue(false), this.width, this.height, this.depth, this.boundaryWidth, this.images[0][0].getImageDataTypeIntValue(), null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   void updateTextureBoundary(Canvas3D paramCanvas3D) { updateTextureBoundary(paramCanvas3D.ctx, this.boundaryModeS, this.boundaryModeT, this.boundaryModeR, this.boundaryColor.x, this.boundaryColor.y, this.boundaryColor.z, this.boundaryColor.w); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   void updateTextureLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { Pipeline.getPipeline().updateTexture3DLodRange(paramContext, paramInt1, paramInt2, paramFloat1, paramFloat2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   void updateTextureLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { Pipeline.getPipeline().updateTexture3DLodOffset(paramContext, paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void reloadTextureImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, ImageComponentRetained paramImageComponentRetained, int paramInt3) {
/* 204 */     ImageComponentRetained.ImageData imageData = paramImageComponentRetained.getImageData(false);
/*     */     
/* 206 */     updateTextureImage(paramCanvas3D, 0, paramInt3, paramInt2, this.format, paramImageComponentRetained.getImageFormatTypeIntValue(false), paramImageComponentRetained.width, paramImageComponentRetained.height, this.depth, this.boundaryWidth, paramImageComponentRetained.getImageDataTypeIntValue(), imageData.get());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void reloadTextureSubImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, ImageComponentUpdateInfo paramImageComponentUpdateInfo, ImageComponentRetained paramImageComponentRetained) {
/* 217 */     int i = paramImageComponentUpdateInfo.x;
/* 218 */     int j = paramImageComponentUpdateInfo.y;
/* 219 */     int k = paramImageComponentUpdateInfo.z;
/* 220 */     int m = paramImageComponentUpdateInfo.width;
/* 221 */     int n = paramImageComponentUpdateInfo.height;
/*     */     
/* 223 */     int i1 = i;
/* 224 */     int i2 = j;
/*     */     
/* 226 */     ImageComponentRetained.ImageData imageData = paramImageComponentRetained.getImageData(false);
/*     */     
/* 228 */     updateTextureSubImage(paramCanvas3D, 0, paramInt1, i1, i2, k, this.format, paramImageComponentRetained.getImageFormatTypeIntValue(false), i1, i2, k, paramImageComponentRetained.width, paramImageComponentRetained.height, m, n, 1, paramImageComponentRetained.getImageDataTypeIntValue(), imageData.get());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Texture3DRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */