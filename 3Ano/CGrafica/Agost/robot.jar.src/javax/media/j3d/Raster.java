/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Raster
/*     */   extends Geometry
/*     */ {
/*     */   public static final int RASTER_COLOR = 1;
/*     */   public static final int RASTER_DEPTH = 2;
/*     */   public static final int RASTER_COLOR_DEPTH = 3;
/*     */   public static final int CLIP_POSITION = 0;
/*     */   public static final int CLIP_IMAGE = 1;
/*     */   public static final int ALLOW_POSITION_READ = 0;
/*     */   public static final int ALLOW_POSITION_WRITE = 1;
/*     */   public static final int ALLOW_OFFSET_READ = 2;
/*     */   public static final int ALLOW_OFFSET_WRITE = 3;
/*     */   public static final int ALLOW_IMAGE_READ = 4;
/*     */   public static final int ALLOW_IMAGE_WRITE = 5;
/*     */   public static final int ALLOW_DEPTH_COMPONENT_READ = 6;
/*     */   public static final int ALLOW_DEPTH_COMPONENT_WRITE = 7;
/*     */   public static final int ALLOW_SIZE_READ = 8;
/*     */   public static final int ALLOW_SIZE_WRITE = 9;
/*     */   public static final int ALLOW_TYPE_READ = 10;
/*     */   public static final int ALLOW_CLIP_MODE_READ = 11;
/*     */   public static final int ALLOW_CLIP_MODE_WRITE = 12;
/* 184 */   private static final int[] readCapabilities = { 0, 2, 4, 6, 8, 10, 11 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public Raster() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster(Point3f paramPoint3f, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, ImageComponent2D paramImageComponent2D, DepthComponent paramDepthComponent) {
/* 243 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 245 */     ((RasterRetained)this.retained).setPosition(paramPoint3f);
/* 246 */     ((RasterRetained)this.retained).setType(paramInt1);
/* 247 */     ((RasterRetained)this.retained).setSrcOffset(paramInt2, paramInt3);
/* 248 */     ((RasterRetained)this.retained).setSize(paramInt4, paramInt5);
/* 249 */     ((RasterRetained)this.retained).setImage(paramImageComponent2D);
/* 250 */     ((RasterRetained)this.retained).setDepthComponent(paramDepthComponent);
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
/*     */   public Raster(Point3f paramPoint3f, int paramInt, Point paramPoint, Dimension paramDimension, ImageComponent2D paramImageComponent2D, DepthComponent paramDepthComponent) {
/* 278 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 280 */     ((RasterRetained)this.retained).setPosition(paramPoint3f);
/* 281 */     ((RasterRetained)this.retained).setType(paramInt);
/* 282 */     ((RasterRetained)this.retained).setSrcOffset(paramPoint.x, paramPoint.y);
/* 283 */     ((RasterRetained)this.retained).setSize(paramDimension.width, paramDimension.height);
/* 284 */     ((RasterRetained)this.retained).setImage(paramImageComponent2D);
/* 285 */     ((RasterRetained)this.retained).setDepthComponent(paramDepthComponent);
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
/*     */   public Raster(Point3f paramPoint3f, int paramInt1, int paramInt2, Point paramPoint1, Dimension paramDimension, Point paramPoint2, ImageComponent2D paramImageComponent2D, DepthComponent paramDepthComponent) {
/* 322 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 324 */     ((RasterRetained)this.retained).setPosition(paramPoint3f);
/* 325 */     ((RasterRetained)this.retained).setType(paramInt1);
/* 326 */     ((RasterRetained)this.retained).setClipMode(paramInt2);
/* 327 */     ((RasterRetained)this.retained).setSrcOffset(paramPoint1.x, paramPoint1.y);
/* 328 */     ((RasterRetained)this.retained).setSize(paramDimension.width, paramDimension.height);
/* 329 */     ((RasterRetained)this.retained).setDstOffset(paramPoint2.x, paramPoint2.y);
/* 330 */     ((RasterRetained)this.retained).setImage(paramImageComponent2D);
/* 331 */     ((RasterRetained)this.retained).setDepthComponent(paramDepthComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 339 */     this.retained = new RasterRetained();
/* 340 */     this.retained.setSource(this);
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
/*     */   public void setPosition(Point3f paramPoint3f) {
/* 352 */     if (isLiveOrCompiled() && 
/* 353 */       !getCapability(1))
/* 354 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster0")); 
/* 355 */     ((RasterRetained)this.retained).setPosition(paramPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPosition(Point3f paramPoint3f) {
/* 365 */     if (isLiveOrCompiled() && 
/* 366 */       !getCapability(0)) {
/* 367 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster1"));
/*     */     }
/* 369 */     ((RasterRetained)this.retained).getPosition(paramPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int paramInt) {
/* 380 */     checkForLiveOrCompiled();
/* 381 */     ((RasterRetained)this.retained).setType(paramInt);
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
/*     */   public int getType() {
/* 393 */     if (isLiveOrCompiled() && 
/* 394 */       !getCapability(10))
/* 395 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster2")); 
/* 396 */     return ((RasterRetained)this.retained).getType();
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
/*     */   public void setClipMode(int paramInt) {
/* 411 */     if (isLiveOrCompiled() && 
/* 412 */       !getCapability(12)) {
/* 413 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster10"));
/*     */     }
/* 415 */     ((RasterRetained)this.retained).setClipMode(paramInt);
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
/*     */   public int getClipMode() {
/* 429 */     if (isLiveOrCompiled() && 
/* 430 */       !getCapability(11)) {
/* 431 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster11"));
/*     */     }
/* 433 */     return ((RasterRetained)this.retained).getClipMode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public void setOffset(int paramInt1, int paramInt2) { setSrcOffset(paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public void setOffset(Point paramPoint) { setSrcOffset(paramPoint); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public void getOffset(Point paramPoint) { getSrcOffset(paramPoint); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSrcOffset(int paramInt1, int paramInt2) {
/* 478 */     if (isLiveOrCompiled() && 
/* 479 */       !getCapability(3)) {
/* 480 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster7"));
/*     */     }
/* 482 */     ((RasterRetained)this.retained).setSrcOffset(paramInt1, paramInt2);
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
/*     */   public void setSrcOffset(Point paramPoint) {
/* 496 */     if (isLiveOrCompiled() && 
/* 497 */       !getCapability(3)) {
/* 498 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster7"));
/*     */     }
/* 500 */     ((RasterRetained)this.retained).setSrcOffset(paramPoint.x, paramPoint.y);
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
/*     */   public void getSrcOffset(Point paramPoint) {
/* 513 */     if (isLiveOrCompiled() && 
/* 514 */       !getCapability(2)) {
/* 515 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster8"));
/*     */     }
/* 517 */     ((RasterRetained)this.retained).getSrcOffset(paramPoint);
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
/*     */   public void setSize(int paramInt1, int paramInt2) {
/* 529 */     if (isLiveOrCompiled() && 
/* 530 */       !getCapability(9)) {
/* 531 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster9"));
/*     */     }
/* 533 */     ((RasterRetained)this.retained).setSize(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(Dimension paramDimension) {
/* 543 */     if (isLiveOrCompiled() && 
/* 544 */       !getCapability(9)) {
/* 545 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster9"));
/*     */     }
/* 547 */     ((RasterRetained)this.retained).setSize(paramDimension.width, paramDimension.height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSize(Dimension paramDimension) {
/* 558 */     if (isLiveOrCompiled() && 
/* 559 */       !getCapability(8)) {
/* 560 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster1"));
/*     */     }
/* 562 */     ((RasterRetained)this.retained).getSize(paramDimension);
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
/*     */   public void setDstOffset(int paramInt1, int paramInt2) {
/* 581 */     if (isLiveOrCompiled() && 
/* 582 */       !getCapability(3)) {
/* 583 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster7"));
/*     */     }
/* 585 */     ((RasterRetained)this.retained).setDstOffset(paramInt1, paramInt2);
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
/*     */   public void setDstOffset(Point paramPoint) {
/* 602 */     if (isLiveOrCompiled() && 
/* 603 */       !getCapability(3)) {
/* 604 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster7"));
/*     */     }
/* 606 */     ((RasterRetained)this.retained).setDstOffset(paramPoint.x, paramPoint.y);
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
/*     */   public void getDstOffset(Point paramPoint) {
/* 619 */     if (isLiveOrCompiled() && 
/* 620 */       !getCapability(2)) {
/* 621 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster8"));
/*     */     }
/* 623 */     ((RasterRetained)this.retained).getDstOffset(paramPoint);
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
/*     */   public void setImage(ImageComponent2D paramImageComponent2D) {
/* 646 */     if (isLiveOrCompiled() && 
/* 647 */       !getCapability(5)) {
/* 648 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster3"));
/*     */     }
/*     */     
/* 651 */     if (paramImageComponent2D != null) {
/* 652 */       ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)paramImageComponent2D.retained;
/* 653 */       if (imageComponent2DRetained.getUsedByOffScreen() && 
/* 654 */         isLive()) {
/* 655 */         throw new IllegalSharingException(J3dI18N.getString("Raster12"));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 660 */     ((RasterRetained)this.retained).setImage(paramImageComponent2D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent2D getImage() {
/* 671 */     if (isLiveOrCompiled() && 
/* 672 */       !getCapability(4))
/* 673 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster4")); 
/* 674 */     return ((RasterRetained)this.retained).getImage();
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
/*     */   public void setDepthComponent(DepthComponent paramDepthComponent) {
/* 686 */     if (isLiveOrCompiled() && 
/* 687 */       !getCapability(7))
/* 688 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster5")); 
/* 689 */     ((RasterRetained)this.retained).setDepthComponent(paramDepthComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DepthComponent getDepthComponent() {
/* 700 */     if (isLiveOrCompiled() && 
/* 701 */       !getCapability(6))
/* 702 */       throw new CapabilityNotSetException(J3dI18N.getString("Raster6")); 
/* 703 */     return ((RasterRetained)this.retained).getDepthComponent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 712 */     Raster raster = new Raster();
/* 713 */     raster.duplicateNodeComponent(this);
/* 714 */     return raster;
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
/* 726 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { checkDuplicateNodeComponent(paramNodeComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 750 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 752 */     RasterRetained rasterRetained1 = (RasterRetained)paramNodeComponent.retained;
/* 753 */     RasterRetained rasterRetained2 = (RasterRetained)this.retained;
/*     */     
/* 755 */     Point3f point3f = new Point3f();
/* 756 */     rasterRetained1.getPosition(point3f);
/* 757 */     rasterRetained2.setPosition(point3f);
/* 758 */     rasterRetained2.setType(rasterRetained1.getType());
/* 759 */     rasterRetained2.setClipMode(rasterRetained1.getClipMode());
/* 760 */     Point point = new Point();
/* 761 */     rasterRetained1.getSrcOffset(point);
/* 762 */     rasterRetained2.setSrcOffset(point.x, point.y);
/* 763 */     rasterRetained1.getDstOffset(point);
/* 764 */     rasterRetained2.setDstOffset(point.x, point.y);
/* 765 */     Dimension dimension = new Dimension();
/* 766 */     rasterRetained1.getSize(dimension);
/* 767 */     rasterRetained2.setSize(dimension.width, dimension.height);
/* 768 */     rasterRetained2.setImage((ImageComponent2D)getNodeComponent(rasterRetained1.getImage(), paramBoolean, paramNodeComponent.nodeHashtable));
/*     */ 
/*     */ 
/*     */     
/* 772 */     rasterRetained2.setDepthComponent((DepthComponent)getNodeComponent(rasterRetained1.getDepthComponent(), paramBoolean, paramNodeComponent.nodeHashtable));
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
/*     */   boolean duplicateChild() {
/* 787 */     if (getDuplicateOnCloneTree())
/* 788 */       return true; 
/* 789 */     RasterRetained rasterRetained = (RasterRetained)this.retained;
/*     */     
/* 791 */     ImageComponent2D imageComponent2D = rasterRetained.getImage();
/* 792 */     if (imageComponent2D != null && imageComponent2D.getDuplicateOnCloneTree()) {
/* 793 */       return true;
/*     */     }
/* 795 */     DepthComponent depthComponent = rasterRetained.getDepthComponent();
/* 796 */     if (depthComponent != null && depthComponent.getDuplicateOnCloneTree()) {
/* 797 */       return true;
/*     */     }
/* 799 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Raster.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */