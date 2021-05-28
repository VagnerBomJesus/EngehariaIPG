/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ImageComponent3DRetained
/*     */   extends ImageComponentRetained
/*     */ {
/*  25 */   void setDepth(int paramInt) { this.depth = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   int getDepth() { return this.depth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void set(int paramInt, BufferedImage paramBufferedImage) {
/*  47 */     this.geomLock.getLock();
/*     */     
/*  49 */     if (this.byReference)
/*     */     {
/*  51 */       setRefImage(paramBufferedImage, paramInt);
/*     */     }
/*     */     
/*  54 */     if (this.imageData == null) {
/*     */ 
/*     */ 
/*     */       
/*  58 */       this.abgrSupported = true;
/*  59 */       this.imageTypeIsSupported = isImageTypeSupported(paramBufferedImage);
/*  60 */       this.imageData = createRenderedImageDataObject(null);
/*     */     
/*     */     }
/*  63 */     else if (getImageType() != evaluateImageType(paramBufferedImage)) {
/*     */     
/*     */     } 
/*     */ 
/*     */     
/*  68 */     if (this.imageTypeIsSupported) {
/*  69 */       copySupportedImageToImageData(paramBufferedImage, paramInt, this.imageData);
/*     */     }
/*     */     else {
/*     */       
/*  73 */       copyUnsupportedImageToImageData(paramBufferedImage, paramInt, this.imageData);
/*     */     } 
/*     */ 
/*     */     
/*  77 */     this.geomLock.unLock();
/*     */     
/*  79 */     if (this.source.isLive())
/*     */     {
/*     */       
/*  82 */       sendMessage(1, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void set(int paramInt, RenderedImage paramRenderedImage) {
/* 161 */     int i = paramRenderedImage.getWidth();
/* 162 */     int j = paramRenderedImage.getHeight();
/*     */     
/* 164 */     if (i != this.width) {
/* 165 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D2"));
/*     */     }
/* 167 */     if (j != this.height) {
/* 168 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D4"));
/*     */     }
/*     */     
/* 171 */     if (paramRenderedImage instanceof BufferedImage) {
/* 172 */       set(paramInt, (BufferedImage)paramRenderedImage);
/*     */     }
/*     */     else {
/*     */       
/* 176 */       ColorModel colorModel = paramRenderedImage.getColorModel();
/* 177 */       WritableRaster writableRaster = paramRenderedImage.copyData(null);
/* 178 */       BufferedImage bufferedImage = new BufferedImage(colorModel, writableRaster, colorModel.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */       
/* 182 */       set(paramInt, bufferedImage);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RenderedImage[] getRenderedImage() {
/* 193 */     RenderedImage[] arrayOfRenderedImage = new RenderedImage[this.depth];
/*     */     
/* 195 */     if (!this.byReference) {
/* 196 */       for (byte b = 0; b < this.depth; b++) {
/* 197 */         arrayOfRenderedImage[b] = this.imageData.createBufferedImage(b);
/*     */       }
/*     */     } else {
/*     */       
/* 201 */       for (byte b = 0; b < this.depth; b++) {
/* 202 */         arrayOfRenderedImage[b] = this.imageData.createBufferedImage(b);
/*     */       }
/*     */     } 
/*     */     
/* 206 */     return arrayOfRenderedImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BufferedImage[] getImage() {
/* 217 */     BufferedImage[] arrayOfBufferedImage = new BufferedImage[this.depth];
/*     */     
/* 219 */     if (!this.byReference) {
/* 220 */       for (byte b = 0; b < this.depth; b++) {
/* 221 */         arrayOfBufferedImage[b] = this.imageData.createBufferedImage(b);
/*     */       }
/*     */     } else {
/*     */       
/* 225 */       for (byte b = 0; b < this.depth; b++) {
/* 226 */         arrayOfBufferedImage[b] = this.imageData.createBufferedImage(b);
/* 227 */         if (!(arrayOfBufferedImage[b] instanceof BufferedImage)) {
/* 228 */           throw new IllegalStateException(J3dI18N.getString("ImageComponent3DRetained0"));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 233 */     return arrayOfBufferedImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RenderedImage getImage(int paramInt) {
/* 243 */     if (!this.byReference) {
/* 244 */       return this.imageData.createBufferedImage(paramInt);
/*     */     }
/*     */     
/* 247 */     return (RenderedImage)getRefImage(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateData(ImageComponent3D.Updater paramUpdater, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 258 */     this.geomLock.getLock();
/*     */ 
/*     */     
/* 261 */     paramUpdater.updateData((ImageComponent3D)this.source, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*     */     
/* 263 */     RenderedImage renderedImage = (RenderedImage)getRefImage(paramInt1);
/* 264 */     assert renderedImage != null;
/* 265 */     assert this.imageData != null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 270 */     int i = paramInt2 + renderedImage.getMinX();
/* 271 */     int j = paramInt3 + renderedImage.getMinY();
/*     */     
/* 273 */     if (this.imageTypeIsSupported) {
/* 274 */       if (renderedImage instanceof BufferedImage) {
/* 275 */         copyImageLineByLine((BufferedImage)renderedImage, i, j, paramInt2, paramInt3, paramInt1, paramInt4, paramInt5, this.imageData);
/*     */       } else {
/* 277 */         copySupportedImageToImageData(renderedImage, i, j, paramInt2, paramInt3, paramInt1, paramInt4, paramInt5, this.imageData);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 282 */     else if (renderedImage instanceof BufferedImage) {
/* 283 */       copyUnsupportedImageToImageData((BufferedImage)renderedImage, i, j, paramInt2, paramInt3, paramInt1, paramInt4, paramInt5, this.imageData);
/*     */     } else {
/* 285 */       copyUnsupportedImageToImageData(renderedImage, i, j, paramInt2, paramInt3, paramInt1, paramInt4, paramInt5, this.imageData);
/*     */     } 
/*     */ 
/*     */     
/* 289 */     this.geomLock.unLock();
/*     */ 
/*     */     
/* 292 */     if (this.source.isLive()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 299 */       ImageComponentUpdateInfo imageComponentUpdateInfo = new ImageComponentUpdateInfo();
/* 300 */       imageComponentUpdateInfo.x = paramInt2;
/* 301 */       imageComponentUpdateInfo.y = paramInt3;
/* 302 */       imageComponentUpdateInfo.z = paramInt1;
/* 303 */       imageComponentUpdateInfo.width = paramInt4;
/* 304 */       imageComponentUpdateInfo.height = paramInt5;
/*     */       
/* 306 */       sendMessage(2, imageComponentUpdateInfo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setSubImage(int paramInt1, RenderedImage paramRenderedImage, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
/* 313 */     if (!isSubImageTypeEqual(paramRenderedImage)) {
/* 314 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent2D6"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 319 */     assert !this.byReference;
/* 320 */     assert this.imageData != null;
/*     */     
/* 322 */     this.geomLock.getLock();
/*     */     
/* 324 */     if (this.imageTypeIsSupported) {
/*     */ 
/*     */       
/* 327 */       if (paramRenderedImage instanceof BufferedImage) {
/* 328 */         copyImageLineByLine((BufferedImage)paramRenderedImage, paramInt4, paramInt5, paramInt6, paramInt7, paramInt1, paramInt2, paramInt3, this.imageData);
/*     */       } else {
/*     */         
/* 331 */         copySupportedImageToImageData(paramRenderedImage, paramInt4, paramInt5, paramInt6, paramInt7, paramInt1, paramInt2, paramInt3, this.imageData);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 337 */     else if (paramRenderedImage instanceof BufferedImage) {
/* 338 */       copyUnsupportedImageToImageData((BufferedImage)paramRenderedImage, paramInt4, paramInt5, paramInt6, paramInt7, paramInt1, paramInt2, paramInt3, this.imageData);
/*     */     } else {
/*     */       
/* 341 */       copyUnsupportedImageToImageData(paramRenderedImage, paramInt4, paramInt5, paramInt6, paramInt7, paramInt1, paramInt2, paramInt3, this.imageData);
/*     */     } 
/*     */ 
/*     */     
/* 345 */     this.geomLock.unLock();
/*     */ 
/*     */     
/* 348 */     if (this.source.isLive()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 355 */       ImageComponentUpdateInfo imageComponentUpdateInfo = new ImageComponentUpdateInfo();
/* 356 */       imageComponentUpdateInfo.x = paramInt6;
/* 357 */       imageComponentUpdateInfo.y = paramInt7;
/* 358 */       imageComponentUpdateInfo.z = paramInt1;
/* 359 */       imageComponentUpdateInfo.width = paramInt2;
/* 360 */       imageComponentUpdateInfo.height = paramInt3;
/*     */       
/* 362 */       sendMessage(2, imageComponentUpdateInfo);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ImageComponent3DRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */