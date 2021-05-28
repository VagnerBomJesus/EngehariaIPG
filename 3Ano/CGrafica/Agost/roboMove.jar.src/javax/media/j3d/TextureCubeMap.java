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
/*     */ public class TextureCubeMap
/*     */   extends Texture
/*     */ {
/*     */   public static final int POSITIVE_X = 0;
/*     */   public static final int NEGATIVE_X = 1;
/*     */   public static final int POSITIVE_Y = 2;
/*     */   public static final int NEGATIVE_Y = 3;
/*     */   public static final int POSITIVE_Z = 4;
/*     */   public static final int NEGATIVE_Z = 5;
/*     */   
/*     */   public TextureCubeMap() {}
/*     */   
/* 123 */   public TextureCubeMap(int paramInt1, int paramInt2, int paramInt3) { super(paramInt1, paramInt2, paramInt3, paramInt3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public TextureCubeMap(int paramInt1, int paramInt2, int paramInt3, int paramInt4) { super(paramInt1, paramInt2, paramInt3, paramInt3, paramInt4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImage(int paramInt1, int paramInt2, ImageComponent2D paramImageComponent2D) {
/* 187 */     if (isLiveOrCompiled() && 
/* 188 */       !getCapability(7)) {
/* 189 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureCubeMap1"));
/*     */     }
/*     */ 
/*     */     
/* 193 */     validateImageIllegalSharing(paramImageComponent2D);
/*     */     
/* 195 */     if (isLive()) {
/* 196 */       ((TextureCubeMapRetained)this.retained).setImage(paramInt1, paramInt2, paramImageComponent2D);
/*     */     } else {
/* 198 */       ((TextureCubeMapRetained)this.retained).initImage(paramInt1, paramInt2, paramImageComponent2D);
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
/*     */   public void setImages(int paramInt, ImageComponent2D[] paramArrayOfImageComponent2D) {
/* 230 */     if (isLiveOrCompiled() && 
/* 231 */       !getCapability(7)) {
/* 232 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureCubeMap1"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 237 */     for (byte b = 0; b < paramArrayOfImageComponent2D.length; b++) {
/* 238 */       validateImageIllegalSharing(paramArrayOfImageComponent2D[b]);
/*     */     }
/*     */     
/* 241 */     if (isLive()) {
/* 242 */       ((TextureCubeMapRetained)this.retained).setImages(paramInt, paramArrayOfImageComponent2D);
/*     */     } else {
/* 244 */       ((TextureCubeMapRetained)this.retained).initImages(paramInt, paramArrayOfImageComponent2D);
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
/*     */   public ImageComponent getImage(int paramInt1, int paramInt2) {
/* 270 */     if (isLiveOrCompiled() && 
/* 271 */       !getCapability(4)) {
/* 272 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureCubeMap2"));
/*     */     }
/*     */ 
/*     */     
/* 276 */     return ((TextureCubeMapRetained)this.retained).getImage(paramInt1, paramInt2);
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
/*     */   public ImageComponent[] getImages(int paramInt) {
/* 299 */     if (isLiveOrCompiled() && 
/* 300 */       !getCapability(4)) {
/* 301 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureCubeMap2"));
/*     */     }
/*     */ 
/*     */     
/* 305 */     return ((TextureCubeMapRetained)this.retained).getImages(paramInt);
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
/* 319 */   public void setImage(int paramInt, ImageComponent paramImageComponent) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 333 */   public void setImages(ImageComponent[] paramArrayOfImageComponent) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public ImageComponent getImage(int paramInt) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public ImageComponent[] getImages() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 369 */     this.retained = new TextureCubeMapRetained();
/* 370 */     this.retained.setSource(this);
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
/* 381 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { checkDuplicateNodeComponent(paramNodeComponent); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TextureCubeMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */