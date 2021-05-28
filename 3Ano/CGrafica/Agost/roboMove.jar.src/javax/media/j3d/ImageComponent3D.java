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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageComponent3D
/*     */   extends ImageComponent
/*     */ {
/*     */   ImageComponent3D() {}
/*     */   
/*  75 */   public ImageComponent3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4) { ((ImageComponent3DRetained)this.retained).processParams(paramInt1, paramInt2, paramInt3, paramInt4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent3D(int paramInt, BufferedImage[] paramArrayOfBufferedImage) {
/*  94 */     ((ImageComponent3DRetained)this.retained).processParams(paramInt, paramArrayOfBufferedImage[0].getWidth(null), paramArrayOfBufferedImage[0].getHeight(null), paramArrayOfBufferedImage.length);
/*     */     
/*  96 */     for (byte b = 0; b < paramArrayOfBufferedImage.length; b++) {
/*  97 */       ((ImageComponent3DRetained)this.retained).set(b, paramArrayOfBufferedImage[b]);
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
/*     */   public ImageComponent3D(int paramInt, RenderedImage[] paramArrayOfRenderedImage) {
/* 120 */     ((ImageComponent3DRetained)this.retained).processParams(paramInt, paramArrayOfRenderedImage[0].getWidth(), paramArrayOfRenderedImage[0].getHeight(), paramArrayOfRenderedImage.length);
/*     */     
/* 122 */     for (byte b = 0; b < paramArrayOfRenderedImage.length; b++) {
/* 123 */       ((ImageComponent3DRetained)this.retained).set(b, paramArrayOfRenderedImage[b]);
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
/*     */   public ImageComponent3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2) {
/* 158 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 159 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 160 */     ((ImageComponent3DRetained)this.retained).processParams(paramInt1, paramInt2, paramInt3, paramInt4);
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
/*     */   public ImageComponent3D(int paramInt, BufferedImage[] paramArrayOfBufferedImage, boolean paramBoolean1, boolean paramBoolean2) {
/* 191 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 192 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 193 */     ((ImageComponent3DRetained)this.retained).processParams(paramInt, paramArrayOfBufferedImage[0].getWidth(null), paramArrayOfBufferedImage[0].getHeight(null), paramArrayOfBufferedImage.length);
/*     */     
/* 195 */     for (byte b = 0; b < paramArrayOfBufferedImage.length; b++) {
/* 196 */       ((ImageComponent3DRetained)this.retained).set(b, paramArrayOfBufferedImage[b]);
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
/*     */   public ImageComponent3D(int paramInt, RenderedImage[] paramArrayOfRenderedImage, boolean paramBoolean1, boolean paramBoolean2) {
/* 229 */     ((ImageComponentRetained)this.retained).setByReference(paramBoolean1);
/* 230 */     ((ImageComponentRetained)this.retained).setYUp(paramBoolean2);
/* 231 */     ((ImageComponent3DRetained)this.retained).processParams(paramInt, paramArrayOfRenderedImage[0].getWidth(), paramArrayOfRenderedImage[0].getHeight(), paramArrayOfRenderedImage.length);
/*     */     
/* 233 */     for (byte b = 0; b < paramArrayOfRenderedImage.length; b++) {
/* 234 */       ((ImageComponent3DRetained)this.retained).set(b, paramArrayOfRenderedImage[b]);
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
/* 273 */   public ImageComponent3D(int paramInt, NioImageBuffer[] paramArrayOfNioImageBuffer, boolean paramBoolean1, boolean paramBoolean2) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDepth() {
/* 294 */     if (isLiveOrCompiled() && 
/* 295 */       !getCapability(0))
/* 296 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent3D0")); 
/* 297 */     return ((ImageComponent3DRetained)this.retained).getDepth();
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
/*     */   public void set(BufferedImage[] paramArrayOfBufferedImage) {
/* 327 */     checkForLiveOrCompiled();
/* 328 */     int i = ((ImageComponent3DRetained)this.retained).getDepth();
/*     */     
/* 330 */     if (i != paramArrayOfBufferedImage.length)
/* 331 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D1")); 
/* 332 */     for (byte b = 0; b < i; b++) {
/* 333 */       ((ImageComponent3DRetained)this.retained).set(b, paramArrayOfBufferedImage[b]);
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
/*     */   public void set(RenderedImage[] paramArrayOfRenderedImage) {
/* 370 */     checkForLiveOrCompiled();
/* 371 */     int i = ((ImageComponent3DRetained)this.retained).getDepth();
/*     */     
/* 373 */     if (i != paramArrayOfRenderedImage.length)
/* 374 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D1")); 
/* 375 */     for (byte b = 0; b < i; b++) {
/* 376 */       ((ImageComponent3DRetained)this.retained).set(b, paramArrayOfRenderedImage[b]);
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
/* 416 */   public void set(NioImageBuffer[] paramArrayOfNioImageBuffer) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int paramInt, BufferedImage paramBufferedImage) {
/* 454 */     checkForLiveOrCompiled();
/* 455 */     if (paramBufferedImage.getWidth(null) != getWidth()) {
/* 456 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D2"));
/*     */     }
/* 458 */     if (paramBufferedImage.getHeight(null) != getHeight()) {
/* 459 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D4"));
/*     */     }
/* 461 */     ((ImageComponent3DRetained)this.retained).set(paramInt, paramBufferedImage);
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
/*     */   public void set(int paramInt, RenderedImage paramRenderedImage) {
/* 492 */     checkForLiveOrCompiled();
/*     */     
/* 494 */     ((ImageComponent3DRetained)this.retained).set(paramInt, paramRenderedImage);
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
/* 528 */   public void set(int paramInt, NioImageBuffer paramNioImageBuffer) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage[] getImage() {
/* 554 */     if (isLiveOrCompiled() && 
/* 555 */       !getCapability(2))
/* 556 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent3D3")); 
/* 557 */     return ((ImageComponent3DRetained)this.retained).getImage();
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
/*     */   public RenderedImage[] getRenderedImage() {
/* 580 */     if (isLiveOrCompiled() && 
/* 581 */       !getCapability(2))
/* 582 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent3D3")); 
/* 583 */     return ((ImageComponent3DRetained)this.retained).getRenderedImage();
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
/* 610 */   public NioImageBuffer[] getNioImage() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage(int paramInt) {
/* 633 */     if (isLiveOrCompiled() && 
/* 634 */       !getCapability(2)) {
/* 635 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent3D3"));
/*     */     }
/* 637 */     RenderedImage renderedImage = ((ImageComponent3DRetained)this.retained).getImage(paramInt);
/* 638 */     if (renderedImage != null && !(renderedImage instanceof BufferedImage)) {
/* 639 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent3D9"));
/*     */     }
/* 641 */     return (BufferedImage)renderedImage;
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
/*     */   public RenderedImage getRenderedImage(int paramInt) {
/* 667 */     if (isLiveOrCompiled() && 
/* 668 */       !getCapability(2))
/* 669 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent3D3")); 
/* 670 */     return ((ImageComponent3DRetained)this.retained).getImage(paramInt);
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
/* 699 */   public NioImageBuffer getNioImage(int paramInt) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubImage(int paramInt1, RenderedImage paramRenderedImage, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
/* 760 */     if (isLiveOrCompiled() && !getCapability(3))
/*     */     {
/* 762 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent3D5"));
/*     */     }
/*     */ 
/*     */     
/* 766 */     if (((ImageComponent3DRetained)this.retained).isByReference()) {
/* 767 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent3D8"));
/*     */     }
/*     */ 
/*     */     
/* 771 */     int i = ((ImageComponent3DRetained)this.retained).getWidth();
/* 772 */     int j = ((ImageComponent3DRetained)this.retained).getHeight();
/*     */     
/* 774 */     if (paramInt4 < 0 || paramInt5 < 0 || paramInt4 + paramInt2 > i || paramInt5 + paramInt3 > j || paramInt6 < 0 || paramInt7 < 0 || paramInt6 + paramInt2 > i || paramInt7 + paramInt3 > j)
/*     */     {
/*     */ 
/*     */       
/* 778 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D7"));
/*     */     }
/*     */ 
/*     */     
/* 782 */     ((ImageComponent3DRetained)this.retained).setSubImage(paramInt1, paramRenderedImage, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
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
/*     */   public void updateData(Updater paramUpdater, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 830 */     if (isLiveOrCompiled() && !getCapability(3))
/*     */     {
/* 832 */       throw new CapabilityNotSetException(J3dI18N.getString("ImageComponent3D5"));
/*     */     }
/*     */ 
/*     */     
/* 836 */     if (!((ImageComponent3DRetained)this.retained).isByReference()) {
/* 837 */       throw new IllegalStateException(J3dI18N.getString("ImageComponent3D6"));
/*     */     }
/*     */ 
/*     */     
/* 841 */     int i = ((ImageComponent3DRetained)this.retained).getWidth();
/* 842 */     int j = ((ImageComponent3DRetained)this.retained).getHeight();
/*     */     
/* 844 */     if (paramInt2 < 0 || paramInt3 < 0 || paramInt2 + paramInt4 > i || paramInt3 + paramInt5 > j) {
/* 845 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponent3D7"));
/*     */     }
/*     */ 
/*     */     
/* 849 */     ((ImageComponent3DRetained)this.retained).updateData(paramUpdater, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 859 */     this.retained = new ImageComponent3DRetained();
/* 860 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 869 */     ImageComponent3DRetained imageComponent3DRetained = (ImageComponent3DRetained)this.retained;
/*     */     
/* 871 */     ImageComponent3D imageComponent3D = new ImageComponent3D(imageComponent3DRetained.getFormat(), imageComponent3DRetained.width, imageComponent3DRetained.height, imageComponent3DRetained.depth);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 885 */     imageComponent3D.duplicateNodeComponent(this);
/* 886 */     return imageComponent3D;
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
/* 909 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 911 */     BufferedImage[] arrayOfBufferedImage = ((ImageComponent3DRetained)paramNodeComponent.retained).getImage();
/*     */ 
/*     */     
/* 914 */     if (arrayOfBufferedImage != null) {
/* 915 */       ImageComponent3DRetained imageComponent3DRetained = (ImageComponent3DRetained)this.retained;
/*     */       
/* 917 */       for (int i = imageComponent3DRetained.depth - 1; i >= 0; i--) {
/* 918 */         if (arrayOfBufferedImage[i] != null)
/* 919 */           imageComponent3DRetained.set(i, arrayOfBufferedImage[i]); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface Updater {
/*     */     void updateData(ImageComponent3D param1ImageComponent3D, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ImageComponent3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */