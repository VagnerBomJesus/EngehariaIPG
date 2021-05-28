/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Background
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_APPLICATION_BOUNDS_READ = 12;
/*     */   public static final int ALLOW_APPLICATION_BOUNDS_WRITE = 13;
/*     */   public static final int ALLOW_IMAGE_READ = 14;
/*     */   public static final int ALLOW_IMAGE_WRITE = 15;
/*     */   public static final int ALLOW_COLOR_READ = 16;
/*     */   public static final int ALLOW_COLOR_WRITE = 17;
/*     */   public static final int ALLOW_GEOMETRY_READ = 18;
/*     */   public static final int ALLOW_GEOMETRY_WRITE = 19;
/*     */   public static final int ALLOW_IMAGE_SCALE_MODE_READ = 20;
/*     */   public static final int ALLOW_IMAGE_SCALE_MODE_WRITE = 21;
/*     */   public static final int SCALE_NONE = 0;
/*     */   public static final int SCALE_FIT_MIN = 1;
/*     */   public static final int SCALE_FIT_MAX = 2;
/*     */   public static final int SCALE_FIT_ALL = 3;
/*     */   public static final int SCALE_REPEAT = 4;
/*     */   public static final int SCALE_NONE_CENTER = 5;
/* 232 */   private static final int[] readCapabilities = { 12, 16, 18, 14, 20 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public Background() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Background(Color3f paramColor3f) {
/* 266 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 268 */     ((BackgroundRetained)this.retained).setColor(paramColor3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Background(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 278 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 280 */     ((BackgroundRetained)this.retained).setColor(paramFloat1, paramFloat2, paramFloat3);
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
/*     */   public Background(ImageComponent2D paramImageComponent2D) {
/* 298 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 300 */     if (paramImageComponent2D != null && paramImageComponent2D.getImageClass() == ImageComponent.ImageClass.NIO_IMAGE_BUFFER)
/*     */     {
/* 302 */       throw new IllegalArgumentException(J3dI18N.getString("Background14"));
/*     */     }
/*     */     
/* 305 */     ((BackgroundRetained)this.retained).setImage(paramImageComponent2D);
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
/*     */   public Background(BranchGroup paramBranchGroup) {
/* 323 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 325 */     ((BackgroundRetained)this.retained).setGeometry(paramBranchGroup);
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
/*     */   public void setColor(Color3f paramColor3f) {
/* 337 */     if (isLiveOrCompiled() && 
/* 338 */       !getCapability(17)) {
/* 339 */       throw new CapabilityNotSetException(J3dI18N.getString("Background0"));
/*     */     }
/* 341 */     if (isLive()) {
/* 342 */       ((BackgroundRetained)this.retained).setColor(paramColor3f);
/*     */     } else {
/* 344 */       ((BackgroundRetained)this.retained).initColor(paramColor3f);
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
/*     */   public void setColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 359 */     if (isLiveOrCompiled() && 
/* 360 */       !getCapability(17)) {
/* 361 */       throw new CapabilityNotSetException(J3dI18N.getString("Background0"));
/*     */     }
/* 363 */     if (isLive()) {
/* 364 */       ((BackgroundRetained)this.retained).setColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 366 */       ((BackgroundRetained)this.retained).initColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getColor(Color3f paramColor3f) {
/* 376 */     if (isLiveOrCompiled() && 
/* 377 */       !getCapability(16)) {
/* 378 */       throw new CapabilityNotSetException(J3dI18N.getString("Background2"));
/*     */     }
/* 380 */     ((BackgroundRetained)this.retained).getColor(paramColor3f);
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
/*     */   public void setImage(ImageComponent2D paramImageComponent2D) {
/* 407 */     if (isLiveOrCompiled() && 
/* 408 */       !getCapability(15)) {
/* 409 */       throw new CapabilityNotSetException(J3dI18N.getString("Background3"));
/*     */     }
/* 411 */     BackgroundRetained backgroundRetained = (BackgroundRetained)this.retained;
/*     */     
/* 413 */     if (paramImageComponent2D != null && paramImageComponent2D.getImageClass() == ImageComponent.ImageClass.NIO_IMAGE_BUFFER)
/*     */     {
/* 415 */       throw new IllegalArgumentException(J3dI18N.getString("Background14"));
/*     */     }
/*     */ 
/*     */     
/* 419 */     if (paramImageComponent2D != null) {
/* 420 */       ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)paramImageComponent2D.retained;
/* 421 */       if (imageComponent2DRetained.getUsedByOffScreen()) {
/* 422 */         if (isLive()) {
/* 423 */           throw new IllegalSharingException(J3dI18N.getString("Background12"));
/*     */         }
/* 425 */         if (backgroundRetained.getInImmCtx()) {
/* 426 */           throw new IllegalSharingException(J3dI18N.getString("Background13"));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 431 */     if (isLive()) {
/* 432 */       backgroundRetained.setImage(paramImageComponent2D);
/*     */     } else {
/* 434 */       backgroundRetained.initImage(paramImageComponent2D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent2D getImage() {
/* 444 */     if (isLiveOrCompiled() && 
/* 445 */       !getCapability(14)) {
/* 446 */       throw new CapabilityNotSetException(J3dI18N.getString("Background4"));
/*     */     }
/* 448 */     return ((BackgroundRetained)this.retained).getImage();
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
/*     */   public void setImageScaleMode(int paramInt) {
/* 467 */     if (isLiveOrCompiled() && 
/* 468 */       !getCapability(21)) {
/* 469 */       throw new CapabilityNotSetException(J3dI18N.getString("Background9"));
/*     */     }
/* 471 */     switch (paramInt) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */         break;
/*     */       default:
/* 480 */         throw new IllegalArgumentException(J3dI18N.getString("Background11"));
/*     */     } 
/*     */     
/* 483 */     if (isLive()) {
/* 484 */       ((BackgroundRetained)this.retained).setImageScaleMode(paramInt);
/*     */     } else {
/* 486 */       ((BackgroundRetained)this.retained).initImageScaleMode(paramInt);
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
/*     */   public int getImageScaleMode() {
/* 502 */     if (isLiveOrCompiled() && 
/* 503 */       !getCapability(20))
/* 504 */       throw new CapabilityNotSetException(J3dI18N.getString("Background10")); 
/* 505 */     return ((BackgroundRetained)this.retained).getImageScaleMode();
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
/*     */   public void setGeometry(BranchGroup paramBranchGroup) {
/* 524 */     if (isLiveOrCompiled() && 
/* 525 */       !getCapability(19)) {
/* 526 */       throw new CapabilityNotSetException(J3dI18N.getString("Background5"));
/*     */     }
/* 528 */     if (isLive()) {
/* 529 */       ((BackgroundRetained)this.retained).setGeometry(paramBranchGroup);
/*     */     } else {
/* 531 */       ((BackgroundRetained)this.retained).initGeometry(paramBranchGroup);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BranchGroup getGeometry() {
/* 542 */     if (isLiveOrCompiled() && 
/* 543 */       !getCapability(18)) {
/* 544 */       throw new CapabilityNotSetException(J3dI18N.getString("Background6"));
/*     */     }
/* 546 */     return ((BackgroundRetained)this.retained).getGeometry();
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
/*     */   public void setApplicationBounds(Bounds paramBounds) {
/* 558 */     if (isLiveOrCompiled() && 
/* 559 */       !getCapability(13)) {
/* 560 */       throw new CapabilityNotSetException(J3dI18N.getString("Background7"));
/*     */     }
/* 562 */     if (isLive()) {
/* 563 */       ((BackgroundRetained)this.retained).setApplicationBounds(paramBounds);
/*     */     } else {
/* 565 */       ((BackgroundRetained)this.retained).initApplicationBounds(paramBounds);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getApplicationBounds() {
/* 575 */     if (isLiveOrCompiled() && 
/* 576 */       !getCapability(12)) {
/* 577 */       throw new CapabilityNotSetException(J3dI18N.getString("Background8"));
/*     */     }
/* 579 */     return ((BackgroundRetained)this.retained).getApplicationBounds();
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
/*     */   public void setApplicationBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 592 */     if (isLiveOrCompiled() && 
/* 593 */       !getCapability(13)) {
/* 594 */       throw new CapabilityNotSetException(J3dI18N.getString("Background7"));
/*     */     }
/* 596 */     if (isLive()) {
/* 597 */       ((BackgroundRetained)this.retained).setApplicationBoundingLeaf(paramBoundingLeaf);
/*     */     } else {
/* 599 */       ((BackgroundRetained)this.retained).initApplicationBoundingLeaf(paramBoundingLeaf);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf getApplicationBoundingLeaf() {
/* 609 */     if (isLiveOrCompiled() && 
/* 610 */       !getCapability(12)) {
/* 611 */       throw new CapabilityNotSetException(J3dI18N.getString("Background8"));
/*     */     }
/* 613 */     return ((BackgroundRetained)this.retained).getApplicationBoundingLeaf();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 621 */     this.retained = new BackgroundRetained();
/* 622 */     this.retained.setSource(this);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 644 */     Background background = new Background();
/* 645 */     background.duplicateNode(this, paramBoolean);
/* 646 */     return background;
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
/* 684 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { checkDuplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 710 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 712 */     BackgroundRetained backgroundRetained1 = (BackgroundRetained)paramNode.retained;
/* 713 */     BackgroundRetained backgroundRetained2 = (BackgroundRetained)this.retained;
/*     */     
/* 715 */     Color3f color3f = new Color3f();
/* 716 */     backgroundRetained1.getColor(color3f);
/* 717 */     backgroundRetained2.initColor(color3f);
/* 718 */     backgroundRetained2.initApplicationBounds(backgroundRetained1.getApplicationBounds());
/* 719 */     backgroundRetained2.initGeometry(backgroundRetained1.getGeometry());
/* 720 */     backgroundRetained2.initImage((ImageComponent2D)getNodeComponent(backgroundRetained1.getImage(), paramBoolean, paramNode.nodeHashtable));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 726 */     backgroundRetained2.initApplicationBoundingLeaf(backgroundRetained1.getApplicationBoundingLeaf());
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
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 758 */     super.updateNodeReferences(paramNodeReferenceTable);
/*     */     
/* 760 */     BackgroundRetained backgroundRetained = (BackgroundRetained)this.retained;
/* 761 */     BoundingLeaf boundingLeaf = backgroundRetained.getApplicationBoundingLeaf();
/*     */     
/* 763 */     if (boundingLeaf != null) {
/* 764 */       SceneGraphObject sceneGraphObject = paramNodeReferenceTable.getNewObjectReference(boundingLeaf);
/* 765 */       backgroundRetained.initApplicationBoundingLeaf((BoundingLeaf)sceneGraphObject);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Background.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */