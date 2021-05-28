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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Texture2D
/*     */   extends Texture
/*     */ {
/*     */   public static final int ALLOW_DETAIL_TEXTURE_READ = 15;
/*     */   public static final int LINEAR_DETAIL = 6;
/*     */   public static final int LINEAR_DETAIL_RGB = 7;
/*     */   public static final int LINEAR_DETAIL_ALPHA = 8;
/*     */   public static final int DETAIL_ADD = 0;
/*     */   public static final int DETAIL_MODULATE = 1;
/* 105 */   private static final int[] readCapabilities = { 15 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public Texture2D() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Texture2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 155 */     super(paramInt1, paramInt2, paramInt3, paramInt4);
/*     */ 
/*     */     
/* 158 */     setDefaultReadCapabilities(readCapabilities);
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
/*     */   public Texture2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 197 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*     */ 
/*     */     
/* 200 */     setDefaultReadCapabilities(readCapabilities);
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
/*     */   public void setMagFilter(int paramInt) {
/* 228 */     checkForLiveOrCompiled();
/*     */     
/* 230 */     switch (paramInt) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */         break;
/*     */       default:
/* 244 */         throw new IllegalArgumentException(J3dI18N.getString("Texture29"));
/*     */     } 
/*     */     
/* 247 */     ((Texture2DRetained)this.retained).initMagFilter(paramInt);
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
/*     */   public void setDetailImage(ImageComponent2D paramImageComponent2D) {
/* 263 */     checkForLiveOrCompiled();
/* 264 */     ((Texture2DRetained)this.retained).initDetailImage(paramImageComponent2D);
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
/*     */   public ImageComponent2D getDetailImage() {
/* 279 */     if (isLiveOrCompiled() && 
/* 280 */       !getCapability(15)) {
/* 281 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture2D0"));
/*     */     }
/*     */ 
/*     */     
/* 285 */     return ((Texture2DRetained)this.retained).getDetailImage();
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
/*     */   public void setDetailTextureMode(int paramInt) {
/* 304 */     checkForLiveOrCompiled();
/* 305 */     if (paramInt != 0 && paramInt != 1) {
/* 306 */       throw new IllegalArgumentException(J3dI18N.getString("Texture2D1"));
/*     */     }
/*     */     
/* 309 */     ((Texture2DRetained)this.retained).initDetailTextureMode(paramInt);
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
/*     */   public int getDetailTextureMode() {
/* 324 */     if (isLiveOrCompiled() && 
/* 325 */       !getCapability(15)) {
/* 326 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture2D0"));
/*     */     }
/*     */ 
/*     */     
/* 330 */     return ((Texture2DRetained)this.retained).getDetailTextureMode();
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
/*     */   public void setDetailTextureLevel(int paramInt) {
/* 347 */     checkForLiveOrCompiled();
/* 348 */     if (paramInt < 0) {
/* 349 */       throw new IllegalArgumentException(J3dI18N.getString("Texture2D2"));
/*     */     }
/*     */     
/* 352 */     ((Texture2DRetained)this.retained).initDetailTextureLevel(paramInt);
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
/*     */   public int getDetailTextureLevel() {
/* 367 */     if (isLiveOrCompiled() && 
/* 368 */       !getCapability(15)) {
/* 369 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture2D0"));
/*     */     }
/*     */ 
/*     */     
/* 373 */     return ((Texture2DRetained)this.retained).getDetailTextureLevel();
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
/*     */   public void setDetailTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 393 */     checkForLiveOrCompiled();
/* 394 */     if ((paramArrayOfFloat1 != null && paramArrayOfFloat2 != null && paramArrayOfFloat1.length == paramArrayOfFloat2.length) || (paramArrayOfFloat1 == null && paramArrayOfFloat2 == null)) {
/*     */       
/* 396 */       ((Texture2DRetained)this.retained).initDetailTextureFunc(paramArrayOfFloat1, paramArrayOfFloat2);
/*     */     } else {
/* 398 */       throw new IllegalStateException(J3dI18N.getString("Texture2D3"));
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
/*     */   public void setDetailTextureFunc(Point2f[] paramArrayOfPoint2f) {
/* 416 */     checkForLiveOrCompiled();
/* 417 */     ((Texture2DRetained)this.retained).initDetailTextureFunc(paramArrayOfPoint2f);
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
/*     */   public int getDetailTextureFuncPointsCount() {
/* 432 */     if (isLiveOrCompiled() && 
/* 433 */       !getCapability(15)) {
/* 434 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture2D0"));
/*     */     }
/*     */ 
/*     */     
/* 438 */     return ((Texture2DRetained)this.retained).getDetailTextureFuncPointsCount();
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
/*     */   public void getDetailTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 455 */     if (isLiveOrCompiled() && 
/* 456 */       !getCapability(15)) {
/* 457 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture2D0"));
/*     */     }
/*     */ 
/*     */     
/* 461 */     ((Texture2DRetained)this.retained).getDetailTextureFunc(paramArrayOfFloat1, paramArrayOfFloat2);
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
/*     */   public void getDetailTextureFunc(Point2f[] paramArrayOfPoint2f) {
/* 476 */     if (isLiveOrCompiled() && 
/* 477 */       !getCapability(15)) {
/* 478 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture2D0"));
/*     */     }
/*     */ 
/*     */     
/* 482 */     ((Texture2DRetained)this.retained).getDetailTextureFunc(paramArrayOfPoint2f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 491 */     this.retained = new Texture2DRetained();
/* 492 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 501 */     Texture2DRetained texture2DRetained = (Texture2DRetained)this.retained;
/*     */     
/* 503 */     Texture2D texture2D = new Texture2D(texture2DRetained.getMipMapMode(), texture2DRetained.format, texture2DRetained.width, texture2DRetained.height);
/*     */     
/* 505 */     texture2D.duplicateNodeComponent(this);
/* 506 */     return texture2D;
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
/* 517 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { checkDuplicateNodeComponent(paramNodeComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 539 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 541 */     Texture2DRetained texture2DRetained1 = (Texture2DRetained)paramNodeComponent.retained;
/*     */     
/* 543 */     Texture2DRetained texture2DRetained2 = (Texture2DRetained)this.retained;
/*     */ 
/*     */     
/* 546 */     texture2DRetained2.initDetailImage(texture2DRetained1.getDetailImage());
/* 547 */     texture2DRetained2.initDetailTextureMode(texture2DRetained1.getDetailTextureMode());
/* 548 */     texture2DRetained2.initDetailTextureLevel(texture2DRetained1.getDetailTextureLevel());
/* 549 */     texture2DRetained2.initDetailTextureFunc(texture2DRetained1.getDetailTextureFunc());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Texture2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */