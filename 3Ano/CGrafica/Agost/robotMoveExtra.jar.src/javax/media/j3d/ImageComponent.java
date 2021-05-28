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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ImageComponent
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int FORMAT_RGB = 1;
/*     */   public static final int FORMAT_RGBA = 2;
/*     */   public static final int FORMAT_RGB8 = 1;
/*     */   public static final int FORMAT_RGBA8 = 2;
/*     */   public static final int FORMAT_RGB5 = 3;
/*     */   public static final int FORMAT_RGB5_A1 = 4;
/*     */   public static final int FORMAT_RGB4 = 5;
/*     */   public static final int FORMAT_RGBA4 = 6;
/*     */   public static final int FORMAT_LUM4_ALPHA4 = 7;
/*     */   public static final int FORMAT_LUM8_ALPHA8 = 8;
/*     */   public static final int FORMAT_R3_G3_B2 = 9;
/*     */   public static final int FORMAT_CHANNEL8 = 10;
/*     */   static final int FORMAT_TOTAL = 10;
/*     */   public static final int ALLOW_SIZE_READ = 0;
/*     */   public static final int ALLOW_FORMAT_READ = 1;
/*     */   public static final int ALLOW_IMAGE_READ = 2;
/*     */   public static final int ALLOW_IMAGE_WRITE = 3;
/*     */   
/*     */   public enum ImageClass
/*     */   {
/* 182 */     BUFFERED_IMAGE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     RENDERED_IMAGE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     NIO_IMAGE_BUFFER;
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
/* 234 */   private static final int[] readCapabilities = { 0, 0, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   ImageComponent() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent(int paramInt1, int paramInt2, int paramInt3) {
/* 268 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 270 */     ((ImageComponentRetained)this.retained).processParams(paramInt1, paramInt2, paramInt3, 1);
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
/*     */   public ImageComponent(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2) {
/* 300 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 302 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 303 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 304 */     ((ImageComponentRetained)this.retained).processParams(paramInt1, paramInt2, paramInt3, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 314 */     if (isLiveOrCompiled() && 
/* 315 */       !getCapability(0))
/* 316 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent0")); 
/* 317 */     return ((ImageComponentRetained)this.retained).getWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 327 */     if (isLiveOrCompiled() && 
/* 328 */       !getCapability(0))
/* 329 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent1")); 
/* 330 */     return ((ImageComponentRetained)this.retained).getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFormat() {
/* 340 */     if (isLiveOrCompiled() && 
/* 341 */       !getCapability(1))
/* 342 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2")); 
/* 343 */     return ((ImageComponentRetained)this.retained).getFormat();
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
/* 357 */   public boolean isByReference() { return ((ImageComponentRetained)this.retained).isByReference(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYUp(boolean paramBoolean) {
/* 382 */     checkForLiveOrCompiled();
/*     */ 
/*     */     
/* 385 */     if (((ImageComponentRetained)this.retained).getImageClass() == ImageClass.NIO_IMAGE_BUFFER) {
/* 386 */       throw new IllegalStateException("ImageComponent4");
/*     */     }
/*     */     
/* 389 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean);
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
/* 403 */   public boolean isYUp() { return ((ImageComponentRetained)this.retained).isYUp(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   public ImageClass getImageClass() { return ((ImageComponentRetained)this.retained).getImageClass(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ImageComponent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */