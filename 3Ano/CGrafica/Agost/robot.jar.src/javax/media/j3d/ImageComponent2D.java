/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.logging.Level;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageComponent2D
/*     */   extends ImageComponent
/*     */ {
/*     */   ImageComponent2D() {}
/*     */   
/*     */   public ImageComponent2D(int paramInt1, int paramInt2, int paramInt3) {
/*  78 */     if (MasterControl.isDevLoggable(Level.FINER)) {
/*  79 */       MasterControl.getDevLogger().finer("ImageComponent - using default of byCopy");
/*     */     }
/*  81 */     ((ImageComponent2DRetained)this.retained).processParams(paramInt1, paramInt2, paramInt3, 1);
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
/*     */   public ImageComponent2D(int paramInt, BufferedImage paramBufferedImage) {
/*  98 */     if (MasterControl.isDevLoggable(Level.FINER)) {
/*  99 */       MasterControl.getDevLogger().finer("ImageComponent - using default of byCopy");
/*     */     }
/* 101 */     ((ImageComponent2DRetained)this.retained).processParams(paramInt, paramBufferedImage.getWidth(), paramBufferedImage.getHeight(), 1);
/* 102 */     ((ImageComponent2DRetained)this.retained).set(paramBufferedImage);
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
/*     */   public ImageComponent2D(int paramInt, RenderedImage paramRenderedImage) {
/* 122 */     if (MasterControl.isDevLoggable(Level.FINER)) {
/* 123 */       MasterControl.getDevLogger().finer("ImageComponent - using default of byCopy");
/*     */     }
/* 125 */     ((ImageComponent2DRetained)this.retained).processParams(paramInt, paramRenderedImage.getWidth(), paramRenderedImage.getHeight(), 1);
/* 126 */     ((ImageComponent2DRetained)this.retained).set(paramRenderedImage);
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
/*     */   public ImageComponent2D(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2) {
/* 157 */     if (MasterControl.isDevLoggable(Level.INFO) && 
/* 158 */       paramBoolean1 && !paramBoolean2) {
/* 159 */       MasterControl.getDevLogger().info("ImageComponent - yUp should be set when using byReference, otherwise an extra copy of the image will be created");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 166 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 167 */     ((ImageComponent2DRetained)this.retained).processParams(paramInt1, paramInt2, paramInt3, 1);
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
/*     */   public ImageComponent2D(int paramInt, BufferedImage paramBufferedImage, boolean paramBoolean1, boolean paramBoolean2) {
/* 194 */     if (MasterControl.isDevLoggable(Level.INFO) && 
/* 195 */       paramBoolean1 && !paramBoolean2) {
/* 196 */       MasterControl.getDevLogger().info("ImageComponent - yUp should be set when using byReference, otherwise an extra copy of the image will be created");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 203 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 204 */     ((ImageComponent2DRetained)this.retained).processParams(paramInt, paramBufferedImage.getWidth(), paramBufferedImage.getHeight(), 1);
/* 205 */     ((ImageComponent2DRetained)this.retained).set(paramBufferedImage);
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
/*     */   public ImageComponent2D(int paramInt, RenderedImage paramRenderedImage, boolean paramBoolean1, boolean paramBoolean2) {
/* 235 */     if (MasterControl.isDevLoggable(Level.INFO) && 
/* 236 */       paramBoolean1 && !paramBoolean2) {
/* 237 */       MasterControl.getDevLogger().info("ImageComponent - yUp should be set when using byReference, otherwise an extra copy of the image will be created");
/*     */     }
/*     */ 
/*     */     
/* 241 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 242 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 243 */     ((ImageComponent2DRetained)this.retained).processParams(paramInt, paramRenderedImage.getWidth(), paramRenderedImage.getHeight(), 1);
/* 244 */     ((ImageComponent2DRetained)this.retained).set(paramRenderedImage);
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
/*     */   public ImageComponent2D(int paramInt, NioImageBuffer paramNioImageBuffer, boolean paramBoolean1, boolean paramBoolean2) {
/* 279 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 280 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 281 */     ((ImageComponent2DRetained)this.retained).processParams(paramInt, paramNioImageBuffer.getWidth(), paramNioImageBuffer.getHeight(), 1);
/* 282 */     ((ImageComponent2DRetained)this.retained).set(paramNioImageBuffer);
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
/*     */   public void set(BufferedImage paramBufferedImage) {
/* 308 */     if (isLiveOrCompiled() && 
/* 309 */       !getCapability(3)) {
/* 310 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D1"));
/*     */     }
/*     */ 
/*     */     
/* 314 */     ((ImageComponent2DRetained)this.retained).set(paramBufferedImage);
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
/*     */   public void set(RenderedImage paramRenderedImage) {
/* 344 */     if (isLiveOrCompiled() && 
/* 345 */       !getCapability(3)) {
/* 346 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D1"));
/*     */     }
/*     */ 
/*     */     
/* 350 */     ((ImageComponent2DRetained)this.retained).set(paramRenderedImage);
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
/*     */   public void set(NioImageBuffer paramNioImageBuffer) {
/* 383 */     if (isLiveOrCompiled() && 
/* 384 */       !getCapability(3)) {
/* 385 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D1"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 390 */     ((ImageComponent2DRetained)this.retained).set(paramNioImageBuffer);
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
/*     */   public BufferedImage getImage() {
/* 410 */     if (isLiveOrCompiled() && 
/* 411 */       !getCapability(2)) {
/* 412 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D0"));
/*     */     }
/*     */     
/* 415 */     RenderedImage renderedImage = ((ImageComponent2DRetained)this.retained).getImage();
/*     */     
/* 417 */     if (renderedImage != null && !(renderedImage instanceof BufferedImage)) {
/* 418 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent2D5"));
/*     */     }
/* 420 */     return (BufferedImage)renderedImage;
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
/*     */   public RenderedImage getRenderedImage() {
/* 444 */     if (isLiveOrCompiled() && 
/* 445 */       !getCapability(2))
/* 446 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D0")); 
/* 447 */     return ((ImageComponent2DRetained)this.retained).getImage();
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
/*     */   public NioImageBuffer getNioImage() {
/* 470 */     if (isLiveOrCompiled() && 
/* 471 */       !getCapability(2)) {
/* 472 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D0"));
/*     */     }
/*     */     
/* 475 */     return ((ImageComponent2DRetained)this.retained).getNioImage();
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
/*     */   public void setSubImage(RenderedImage paramRenderedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 535 */     if (isLiveOrCompiled() && !getCapability(3))
/*     */     {
/* 537 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D1"));
/*     */     }
/*     */ 
/*     */     
/* 541 */     if (((ImageComponent2DRetained)this.retained).isByReference()) {
/* 542 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent2D4"));
/*     */     }
/*     */ 
/*     */     
/* 546 */     int i = ((ImageComponent2DRetained)this.retained).getWidth();
/* 547 */     int j = ((ImageComponent2DRetained)this.retained).getHeight();
/*     */ 
/*     */     
/* 550 */     if (paramInt3 < 0 || paramInt4 < 0 || paramInt3 + paramInt1 > paramRenderedImage.getWidth() || paramInt4 + paramInt2 > paramRenderedImage.getHeight() || paramInt5 < 0 || paramInt6 < 0 || paramInt5 + paramInt1 > i || paramInt6 + paramInt2 > j)
/*     */     {
/*     */ 
/*     */       
/* 554 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2D3"));
/*     */     }
/*     */ 
/*     */     
/* 558 */     ((ImageComponent2DRetained)this.retained).setSubImage(paramRenderedImage, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
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
/*     */   public void updateData(Updater paramUpdater, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 603 */     if (isLiveOrCompiled() && !getCapability(3))
/*     */     {
/* 605 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent2D1"));
/*     */     }
/*     */ 
/*     */     
/* 609 */     if (!((ImageComponent2DRetained)this.retained).isByReference()) {
/* 610 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent2D2"));
/*     */     }
/*     */ 
/*     */     
/* 614 */     int i = ((ImageComponent2DRetained)this.retained).getWidth();
/* 615 */     int j = ((ImageComponent2DRetained)this.retained).getHeight();
/*     */     
/* 617 */     if (paramInt1 < 0 || paramInt2 < 0 || paramInt1 + paramInt3 > i || paramInt2 + paramInt4 > j) {
/* 618 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent2D3"));
/*     */     }
/*     */ 
/*     */     
/* 622 */     ((ImageComponent2DRetained)this.retained).updateData(paramUpdater, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 631 */     this.retained = new ImageComponent2DRetained();
/* 632 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 639 */     ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)this.retained;
/*     */     
/* 641 */     ImageComponent2D imageComponent2D = new ImageComponent2D(imageComponent2DRetained.getFormat(), imageComponent2DRetained.width, imageComponent2DRetained.height, imageComponent2DRetained.byReference, imageComponent2DRetained.yUp);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 646 */     imageComponent2D.duplicateNodeComponent(this);
/* 647 */     return imageComponent2D;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 670 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 672 */     ImageComponent.ImageClass imageClass = ((ImageComponentRetained)paramNodeComponent.retained).getImageClass();
/*     */     
/* 674 */     if (imageClass == ImageComponent.ImageClass.NIO_IMAGE_BUFFER) {
/* 675 */       NioImageBuffer nioImageBuffer = ((ImageComponent2DRetained)paramNodeComponent.retained).getNioImage();
/*     */ 
/*     */       
/* 678 */       if (nioImageBuffer != null) {
/* 679 */         ((ImageComponent2DRetained)this.retained).set(nioImageBuffer);
/*     */       }
/*     */     } else {
/* 682 */       RenderedImage renderedImage = ((ImageComponent2DRetained)paramNodeComponent.retained).getImage();
/*     */ 
/*     */       
/* 685 */       if (renderedImage != null)
/* 686 */         ((ImageComponent2DRetained)this.retained).set(renderedImage); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface Updater {
/*     */     void updateData(ImageComponent2D param1ImageComponent2D, int param1Int1, int param1Int2, int param1Int3, int param1Int4);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ImageComponent2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */