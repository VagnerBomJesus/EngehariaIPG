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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TextureCubeMapRetained
/*     */   extends TextureRetained
/*     */ {
/*     */   static final int NUMFACES = 6;
/*     */   
/*     */   void initialize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
/*  33 */     this.numFaces = 6;
/*     */     
/*  35 */     super.initialize(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initImage(int paramInt1, int paramInt2, ImageComponent paramImageComponent) {
/*  46 */     checkImageSize(paramInt1, paramImageComponent);
/*     */     
/*  48 */     if (this.images == null) {
/*  49 */       throw new IllegalArgumentException(J3dI18N.getString("TextureRetained0"));
/*     */     }
/*     */ 
/*     */     
/*  53 */     if (paramImageComponent instanceof ImageComponent3D) {
/*  54 */       throw new IllegalArgumentException(J3dI18N.getString("TextureCubeMap3"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  59 */     if (paramInt2 < 0 || paramInt2 > 5)
/*     */     {
/*  61 */       throw new IllegalArgumentException(J3dI18N.getString("TextureCubeMap4"));
/*     */     }
/*     */ 
/*     */     
/*  65 */     if (this.source.isLive()) {
/*  66 */       if (this.images[paramInt2][paramInt1] != null) {
/*  67 */         this.images[paramInt2][paramInt1].clearLive(this.refCount);
/*     */       }
/*     */ 
/*     */       
/*  71 */       if (paramImageComponent != null) {
/*  72 */         ((ImageComponentRetained)paramImageComponent.retained).setLive(this.inBackgroundGroup, this.refCount);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (paramImageComponent != null) {
/*  82 */       this.images[paramInt2][paramInt1] = (ImageComponentRetained)paramImageComponent.retained;
/*     */     } else {
/*  84 */       this.images[paramInt2][paramInt1] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   final void setImage(int paramInt1, int paramInt2, ImageComponent paramImageComponent) {
/*  90 */     initImage(paramInt1, paramInt2, paramImageComponent);
/*     */     
/*  92 */     Object[] arrayOfObject = new Object[3];
/*  93 */     arrayOfObject[0] = new Integer(paramInt1);
/*  94 */     arrayOfObject[1] = paramImageComponent;
/*  95 */     arrayOfObject[2] = new Integer(paramInt2);
/*  96 */     sendMessage(4, arrayOfObject);
/*     */ 
/*     */ 
/*     */     
/* 100 */     if (this.userSpecifiedEnable) {
/* 101 */       this.enable = this.userSpecifiedEnable;
/* 102 */       if (paramImageComponent != null && paramInt1 < this.maxLevels) {
/* 103 */         ImageComponentRetained imageComponentRetained = (ImageComponentRetained)paramImageComponent.retained;
/* 104 */         if (imageComponentRetained.isByReference()) {
/* 105 */           if (imageComponentRetained.getRefImage(false) == null) {
/* 106 */             this.enable = false;
/*     */           
/*     */           }
/*     */         }
/* 110 */         else if (imageComponentRetained.getImageData(isUseAsRaster()).get() == null) {
/* 111 */           this.enable = false;
/*     */         } 
/*     */         
/* 114 */         if (!this.enable) {
/* 115 */           sendMessage(1, Boolean.FALSE);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void initImages(int paramInt, ImageComponent[] paramArrayOfImageComponent) {
/* 122 */     if (paramArrayOfImageComponent.length != this.maxLevels) {
/* 123 */       throw new IllegalArgumentException(J3dI18N.getString("Texture20"));
/*     */     }
/* 125 */     for (byte b = 0; b < paramArrayOfImageComponent.length; b++) {
/* 126 */       initImage(b, paramInt, paramArrayOfImageComponent[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setImages(int paramInt, ImageComponent[] paramArrayOfImageComponent) {
/* 134 */     initImages(paramInt, paramArrayOfImageComponent);
/*     */     
/* 136 */     ImageComponent[] arrayOfImageComponent = new ImageComponent[paramArrayOfImageComponent.length]; byte b;
/* 137 */     for (b = 0; b < paramArrayOfImageComponent.length; b++) {
/* 138 */       arrayOfImageComponent[b] = paramArrayOfImageComponent[b];
/*     */     }
/*     */     
/* 141 */     Object[] arrayOfObject = new Object[2];
/* 142 */     arrayOfObject[0] = arrayOfImageComponent;
/* 143 */     arrayOfObject[1] = new Integer(paramInt);
/*     */     
/* 145 */     sendMessage(32, arrayOfObject);
/*     */ 
/*     */     
/* 148 */     if (this.userSpecifiedEnable) {
/* 149 */       this.enable = this.userSpecifiedEnable;
/* 150 */       b = 0;
/* 151 */       while (this.enable && b < this.maxLevels) {
/* 152 */         if (paramArrayOfImageComponent[b] != null) {
/* 153 */           ImageComponentRetained imageComponentRetained = (ImageComponentRetained)(paramArrayOfImageComponent[b]).retained;
/* 154 */           if (imageComponentRetained.isByReference()) {
/* 155 */             if (imageComponentRetained.getRefImage(false) == null) {
/* 156 */               this.enable = false;
/*     */             
/*     */             }
/*     */           }
/* 160 */           else if (imageComponentRetained.getImageData(isUseAsRaster()).get() == null) {
/* 161 */             this.enable = false;
/*     */           } 
/*     */         } 
/*     */         
/* 165 */         b++;
/*     */       } 
/* 167 */       if (!this.enable) {
/* 168 */         sendMessage(1, Boolean.FALSE);
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
/*     */ 
/*     */ 
/*     */   
/*     */   final ImageComponent getImage(int paramInt1, int paramInt2) {
/* 184 */     if (paramInt2 < 0 || paramInt2 > 5)
/*     */     {
/* 186 */       throw new IllegalArgumentException(J3dI18N.getString("TextureCubeMap4"));
/*     */     }
/*     */ 
/*     */     
/* 190 */     return (this.images != null && this.images[paramInt2][paramInt1] != null) ? (ImageComponent)(this.images[paramInt2][paramInt1]).source : null;
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
/*     */   final ImageComponent[] getImages(int paramInt) {
/* 202 */     if (this.images == null) {
/* 203 */       return null;
/*     */     }
/* 205 */     if (paramInt < 0 || paramInt > 5)
/*     */     {
/* 207 */       throw new IllegalArgumentException(J3dI18N.getString("TextureCubeMap4"));
/*     */     }
/*     */ 
/*     */     
/* 211 */     ImageComponent[] arrayOfImageComponent = new ImageComponent[this.images[paramInt].length];
/* 212 */     for (byte b = 0; b < this.images[paramInt].length; b++) {
/* 213 */       if (this.images[paramInt][b] != null) {
/* 214 */         arrayOfImageComponent[b] = (ImageComponent)(this.images[paramInt][b]).source;
/*     */       } else {
/* 216 */         arrayOfImageComponent[b] = null;
/*     */       } 
/* 218 */     }  return arrayOfImageComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 223 */   void bindTexture(Context paramContext, int paramInt, boolean paramBoolean) { Pipeline.getPipeline().bindTextureCubeMap(paramContext, paramInt, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   void updateTextureBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { Pipeline.getPipeline().updateTextureCubeMapBoundary(paramContext, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   void updateTextureFilterModes(Context paramContext, int paramInt1, int paramInt2) { Pipeline.getPipeline().updateTextureCubeMapFilterModes(paramContext, paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   void updateTextureSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { Pipeline.getPipeline().updateTextureCubeMapSharpenFunc(paramContext, paramInt, paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   void updateTextureFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { Pipeline.getPipeline().updateTextureCubeMapFilter4Func(paramContext, paramInt, paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   void updateTextureAnisotropicFilter(Context paramContext, float paramFloat) { Pipeline.getPipeline().updateTextureCubeMapAnisotropicFilter(paramContext, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   void updateTextureLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { Pipeline.getPipeline().updateTextureCubeMapLodRange(paramContext, paramInt1, paramInt2, paramFloat1, paramFloat2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   void updateTextureLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { Pipeline.getPipeline().updateTextureCubeMapLodOffset(paramContext, paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTextureDimensions(Canvas3D paramCanvas3D) {
/* 287 */     if (this.images[false][false] != null) {
/*     */       
/* 289 */       int i = this.images[0][0].getImageFormatTypeIntValue(false);
/* 290 */       int j = this.images[0][0].getImageDataTypeIntValue();
/*     */       
/* 292 */       for (byte b = 0; b < 6; b++) {
/* 293 */         updateTextureImage(paramCanvas3D, b, this.maxLevels, 0, this.format, i, this.width, this.height, this.boundaryWidth, j, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   void updateTextureImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject) { Pipeline.getPipeline().updateTextureCubeMapImage(paramCanvas3D.ctx, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramObject, useAutoMipMapGeneration(paramCanvas3D)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 325 */   void updateTextureSubImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject) { Pipeline.getPipeline().updateTextureCubeMapSubImage(paramCanvas3D.ctx, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramInt12, paramObject, useAutoMipMapGeneration(paramCanvas3D)); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TextureCubeMapRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */