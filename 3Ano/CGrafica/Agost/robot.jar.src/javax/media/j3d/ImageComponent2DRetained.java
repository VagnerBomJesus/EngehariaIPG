/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.RenderedImage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ImageComponent2DRetained
/*     */   extends ImageComponentRetained
/*     */ {
/*     */   void set(NioImageBuffer paramNioImageBuffer) {
/*  38 */     int i = paramNioImageBuffer.getWidth();
/*  39 */     int j = paramNioImageBuffer.getHeight();
/*     */     
/*  41 */     if (!this.byReference) {
/*  42 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2D7"));
/*     */     }
/*  44 */     if (!this.yUp) {
/*  45 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2D8"));
/*     */     }
/*     */     
/*  48 */     if (i != this.width) {
/*  49 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2DRetained0"));
/*     */     }
/*  51 */     if (j != this.height) {
/*  52 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2DRetained1"));
/*     */     }
/*     */     
/*  55 */     this.geomLock.getLock();
/*     */     
/*  57 */     setImageClass(paramNioImageBuffer);
/*     */ 
/*     */     
/*  60 */     setRefImage(paramNioImageBuffer, 0);
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.abgrSupported = true;
/*     */     
/*  66 */     this.imageTypeIsSupported = isImageTypeSupported(paramNioImageBuffer);
/*     */     
/*  68 */     if (this.imageTypeIsSupported) {
/*     */ 
/*     */ 
/*     */       
/*  72 */       this.imageData = createNioImageBufferDataObject(paramNioImageBuffer);
/*     */     }
/*     */     else {
/*     */       
/*  76 */       this.imageData = createNioImageBufferDataObject(null);
/*  77 */       copyUnsupportedNioImageToImageData(paramNioImageBuffer, 0, 0, 0, 0, i, j, this.imageData);
/*     */     } 
/*     */ 
/*     */     
/*  81 */     this.geomLock.unLock();
/*     */     
/*  83 */     if (this.source.isLive())
/*     */     {
/*     */       
/*  86 */       sendMessage(1, null);
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
/*     */   void set(RenderedImage paramRenderedImage) {
/*  99 */     int i = paramRenderedImage.getWidth();
/* 100 */     int j = paramRenderedImage.getHeight();
/*     */     
/* 102 */     if (i != this.width) {
/* 103 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2DRetained0"));
/*     */     }
/* 105 */     if (j != this.height) {
/* 106 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2DRetained1"));
/*     */     }
/*     */     
/* 109 */     setImageClass(paramRenderedImage);
/*     */     
/* 111 */     this.geomLock.getLock();
/*     */     
/* 113 */     if (this.byReference) {
/* 114 */       setRefImage(paramRenderedImage, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 119 */     this.abgrSupported = true;
/*     */     
/* 121 */     this.imageTypeIsSupported = isImageTypeSupported(paramRenderedImage);
/*     */     
/* 123 */     if (this.imageTypeIsSupported) {
/*     */       
/* 125 */       if (this.byReference && this.yUp)
/*     */       {
/*     */         
/* 128 */         if (paramRenderedImage instanceof BufferedImage)
/*     */         {
/* 130 */           this.imageData = createRenderedImageDataObject(paramRenderedImage);
/*     */         }
/*     */         else
/*     */         {
/* 134 */           this.imageData = null;
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 142 */         this.imageData = createRenderedImageDataObject(null);
/* 143 */         copySupportedImageToImageData(paramRenderedImage, 0, this.imageData);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 153 */       this.imageData = createRenderedImageDataObject(null);
/* 154 */       copyUnsupportedImageToImageData(paramRenderedImage, 0, this.imageData);
/*     */     } 
/*     */ 
/*     */     
/* 158 */     this.geomLock.unLock();
/*     */     
/* 160 */     if (this.source.isLive())
/*     */     {
/*     */       
/* 163 */       sendMessage(1, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setSubImage(RenderedImage paramRenderedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 170 */     if (!isSubImageTypeEqual(paramRenderedImage)) {
/* 171 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent2D6"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 176 */     assert !this.byReference;
/* 177 */     assert this.imageData != null;
/*     */     
/* 179 */     this.geomLock.getLock();
/*     */     
/* 181 */     if (this.imageTypeIsSupported) {
/*     */ 
/*     */       
/* 184 */       if (paramRenderedImage instanceof BufferedImage) {
/* 185 */         copyImageLineByLine((BufferedImage)paramRenderedImage, paramInt3, paramInt4, paramInt5, paramInt6, 0, paramInt1, paramInt2, this.imageData);
/*     */       } else {
/*     */         
/* 188 */         copySupportedImageToImageData(paramRenderedImage, paramInt3, paramInt4, paramInt5, paramInt6, 0, paramInt1, paramInt2, this.imageData);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 194 */     else if (paramRenderedImage instanceof BufferedImage) {
/* 195 */       copyUnsupportedImageToImageData((BufferedImage)paramRenderedImage, paramInt3, paramInt4, paramInt5, paramInt6, 0, paramInt1, paramInt2, this.imageData);
/*     */     } else {
/*     */       
/* 198 */       copyUnsupportedImageToImageData(paramRenderedImage, paramInt3, paramInt4, paramInt5, paramInt6, 0, paramInt1, paramInt2, this.imageData);
/*     */     } 
/*     */     
/* 201 */     this.geomLock.unLock();
/*     */     
/* 203 */     if (this.source.isLive()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 210 */       ImageComponentUpdateInfo imageComponentUpdateInfo = new ImageComponentUpdateInfo();
/* 211 */       imageComponentUpdateInfo.x = paramInt5;
/* 212 */       imageComponentUpdateInfo.y = paramInt6;
/* 213 */       imageComponentUpdateInfo.z = 0;
/* 214 */       imageComponentUpdateInfo.width = paramInt1;
/* 215 */       imageComponentUpdateInfo.height = paramInt2;
/*     */       
/* 217 */       sendMessage(2, imageComponentUpdateInfo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RenderedImage getImage() {
/* 228 */     if (isByReference()) {
/* 229 */       return (RenderedImage)getRefImage(0);
/*     */     }
/*     */     
/* 232 */     if (this.imageData != null) {
/* 233 */       return this.imageData.createBufferedImage(0);
/*     */     }
/*     */     
/* 236 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   NioImageBuffer getNioImage() {
/* 244 */     if (getImageClass() != ImageComponent.ImageClass.NIO_IMAGE_BUFFER) {
/* 245 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent2D9"));
/*     */     }
/*     */     
/* 248 */     assert this.byReference == true;
/*     */     
/* 250 */     return (NioImageBuffer)getRefImage(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateData(ImageComponent2D.Updater paramUpdater, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 261 */     this.geomLock.getLock();
/*     */     
/* 263 */     paramUpdater.updateData((ImageComponent2D)this.source, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */     
/* 265 */     Object object = getRefImage(0);
/* 266 */     assert object != null;
/* 267 */     assert this.imageData != null;
/*     */ 
/*     */     
/* 270 */     if (!this.imageData.isDataByRef())
/*     */     {
/*     */       
/* 273 */       if (this.imageTypeIsSupported) {
/* 274 */         assert !(object instanceof NioImageBuffer);
/*     */         
/* 276 */         if (object instanceof BufferedImage) {
/* 277 */           copyImageLineByLine((BufferedImage)object, paramInt1, paramInt2, paramInt1, paramInt2, 0, paramInt3, paramInt4, this.imageData);
/*     */         } else {
/* 279 */           RenderedImage renderedImage = (RenderedImage)object;
/* 280 */           copySupportedImageToImageData(renderedImage, paramInt1 + renderedImage.getMinX(), paramInt2 + renderedImage.getMinY(), paramInt1, paramInt2, 0, paramInt3, paramInt4, this.imageData);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 285 */       else if (object instanceof BufferedImage) {
/* 286 */         copyUnsupportedImageToImageData((BufferedImage)object, paramInt1, paramInt2, paramInt1, paramInt2, 0, paramInt3, paramInt4, this.imageData);
/* 287 */       } else if (object instanceof RenderedImage) {
/* 288 */         RenderedImage renderedImage = (RenderedImage)object;
/* 289 */         copyUnsupportedImageToImageData(renderedImage, paramInt1 + renderedImage.getMinX(), paramInt2 + renderedImage.getMinY(), paramInt1, paramInt2, 0, paramInt3, paramInt4, this.imageData);
/* 290 */       } else if (object instanceof NioImageBuffer) {
/* 291 */         copyUnsupportedNioImageToImageData((NioImageBuffer)object, paramInt1, paramInt2, paramInt1, paramInt2, paramInt3, paramInt4, this.imageData);
/*     */       } else {
/*     */         assert false;
/*     */       } 
/*     */     }
/*     */     
/* 297 */     this.geomLock.unLock();
/*     */ 
/*     */     
/* 300 */     if (this.source.isLive()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 307 */       ImageComponentUpdateInfo imageComponentUpdateInfo = new ImageComponentUpdateInfo();
/* 308 */       imageComponentUpdateInfo.x = paramInt1;
/* 309 */       imageComponentUpdateInfo.y = paramInt2;
/* 310 */       imageComponentUpdateInfo.z = 0;
/* 311 */       imageComponentUpdateInfo.width = paramInt3;
/* 312 */       imageComponentUpdateInfo.height = paramInt4;
/*     */       
/* 314 */       sendMessage(2, imageComponentUpdateInfo);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 319 */   void clearLive(int paramInt) { super.clearLive(paramInt); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ImageComponent2DRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */