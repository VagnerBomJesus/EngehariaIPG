/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Shape3D
/*     */   extends Leaf
/*     */ {
/*     */   int id;
/*     */   public static final int ALLOW_GEOMETRY_READ = 12;
/*     */   public static final int ALLOW_GEOMETRY_WRITE = 13;
/*     */   public static final int ALLOW_APPEARANCE_READ = 14;
/*     */   public static final int ALLOW_APPEARANCE_WRITE = 15;
/*     */   public static final int ALLOW_COLLISION_BOUNDS_READ = 16;
/*     */   public static final int ALLOW_COLLISION_BOUNDS_WRITE = 17;
/*     */   public static final int ALLOW_APPEARANCE_OVERRIDE_READ = 18;
/*     */   public static final int ALLOW_APPEARANCE_OVERRIDE_WRITE = 19;
/* 115 */   private static final int[] readCapabilities = { 12, 14, 16, 18 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public Shape3D() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape3D(Geometry paramGeometry) {
/* 155 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 157 */     ((Shape3DRetained)this.retained).setGeometry(paramGeometry, 0);
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
/*     */   public Shape3D(Geometry paramGeometry, Appearance paramAppearance) {
/* 172 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 174 */     ((Shape3DRetained)this.retained).setGeometry(paramGeometry, 0);
/* 175 */     ((Shape3DRetained)this.retained).setAppearance(paramAppearance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 183 */     this.retained = new Shape3DRetained();
/* 184 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCollisionBounds(Bounds paramBounds) {
/* 195 */     if (isLiveOrCompiled() && 
/* 196 */       !getCapability(17)) {
/* 197 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D0"));
/*     */     }
/* 199 */     ((Shape3DRetained)this.retained).setCollisionBounds(paramBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getCollisionBounds() {
/* 210 */     if (isLiveOrCompiled() && 
/* 211 */       !getCapability(16)) {
/* 212 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D1"));
/*     */     }
/* 214 */     return ((Shape3DRetained)this.retained).getCollisionBounds(this.id);
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
/*     */   public void setGeometry(Geometry paramGeometry) {
/* 234 */     if (isLiveOrCompiled() && 
/* 235 */       !getCapability(13)) {
/* 236 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D2"));
/*     */     }
/* 238 */     ((Shape3DRetained)this.retained).setGeometry(paramGeometry, 0);
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
/*     */   public Geometry getGeometry() {
/* 250 */     if (isLiveOrCompiled() && 
/* 251 */       !getCapability(12)) {
/* 252 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D3"));
/*     */     }
/* 254 */     return ((Shape3DRetained)this.retained).getGeometry(0, this.id);
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
/*     */   public void setGeometry(Geometry paramGeometry, int paramInt) {
/* 279 */     if (isLiveOrCompiled() && 
/* 280 */       !getCapability(13)) {
/* 281 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D2"));
/*     */     }
/* 283 */     ((Shape3DRetained)this.retained).setGeometry(paramGeometry, paramInt);
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
/*     */   public Geometry getGeometry(int paramInt) {
/* 299 */     if (isLiveOrCompiled() && 
/* 300 */       !getCapability(12)) {
/* 301 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D3"));
/*     */     }
/* 303 */     return ((Shape3DRetained)this.retained).getGeometry(paramInt, this.id);
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
/*     */   public void insertGeometry(Geometry paramGeometry, int paramInt) {
/* 327 */     if (isLiveOrCompiled() && 
/* 328 */       !getCapability(13)) {
/* 329 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D2"));
/*     */     }
/* 331 */     ((Shape3DRetained)this.retained).insertGeometry(paramGeometry, paramInt);
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
/*     */   public void removeGeometry(int paramInt) {
/* 346 */     if (isLiveOrCompiled() && 
/* 347 */       !getCapability(13)) {
/* 348 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D2"));
/*     */     }
/* 350 */     ((Shape3DRetained)this.retained).removeGeometry(paramInt);
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
/*     */   public Enumeration getAllGeometries() {
/* 366 */     if (isLiveOrCompiled() && 
/* 367 */       !getCapability(12)) {
/* 368 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D3"));
/*     */     }
/* 370 */     return ((Shape3DRetained)this.retained).getAllGeometries(this.id);
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
/*     */   public void addGeometry(Geometry paramGeometry) {
/* 392 */     if (isLiveOrCompiled() && 
/* 393 */       !getCapability(13)) {
/* 394 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D2"));
/*     */     }
/* 396 */     ((Shape3DRetained)this.retained).addGeometry(paramGeometry);
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
/*     */   public int numGeometries() {
/* 412 */     if (isLiveOrCompiled() && 
/* 413 */       !getCapability(12))
/* 414 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D3")); 
/* 415 */     return ((Shape3DRetained)this.retained).numGeometries(this.id);
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
/*     */   public int indexOfGeometry(Geometry paramGeometry) {
/* 433 */     if (isLiveOrCompiled() && 
/* 434 */       !getCapability(12))
/* 435 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D3")); 
/* 436 */     return ((Shape3DRetained)this.retained).indexOfGeometry(paramGeometry);
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
/*     */   public void removeGeometry(Geometry paramGeometry) {
/* 453 */     if (isLiveOrCompiled() && 
/* 454 */       !getCapability(13))
/* 455 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D2")); 
/* 456 */     ((Shape3DRetained)this.retained).removeGeometry(paramGeometry);
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
/*     */   public void removeAllGeometries() {
/* 470 */     if (isLiveOrCompiled() && 
/* 471 */       !getCapability(13))
/* 472 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D2")); 
/* 473 */     ((Shape3DRetained)this.retained).removeAllGeometries();
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
/*     */   public void setAppearance(Appearance paramAppearance) {
/* 486 */     if (isLiveOrCompiled() && 
/* 487 */       !getCapability(15)) {
/* 488 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D4"));
/*     */     }
/* 490 */     ((Shape3DRetained)this.retained).setAppearance(paramAppearance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Appearance getAppearance() {
/* 501 */     if (isLiveOrCompiled() && 
/* 502 */       !getCapability(14)) {
/* 503 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D5"));
/*     */     }
/* 505 */     return ((Shape3DRetained)this.retained).getAppearance();
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
/* 528 */   public boolean intersect(SceneGraphPath paramSceneGraphPath, PickShape paramPickShape) { return intersect(paramSceneGraphPath, paramPickShape, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersect(SceneGraphPath paramSceneGraphPath, PickRay paramPickRay, double[] paramArrayOfDouble) {
/* 552 */     if (isLiveOrCompiled() && 
/* 553 */       !((Shape3DRetained)this.retained).allowIntersect()) {
/* 554 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D6"));
/*     */     }
/* 556 */     return ((Shape3DRetained)this.retained).intersect(paramSceneGraphPath, paramPickRay, paramArrayOfDouble);
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
/*     */   public boolean intersect(SceneGraphPath paramSceneGraphPath, PickShape paramPickShape, double[] paramArrayOfDouble) {
/* 586 */     if (isLiveOrCompiled() && 
/* 587 */       !((Shape3DRetained)this.retained).allowIntersect()) {
/* 588 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D6"));
/*     */     }
/*     */     
/* 591 */     if (paramPickShape instanceof PickPoint) {
/* 592 */       throw new IllegalArgumentException(J3dI18N.getString("Shape3D7"));
/*     */     }
/*     */     
/* 595 */     return ((Shape3DRetained)this.retained).intersect(paramSceneGraphPath, paramPickShape, paramArrayOfDouble);
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
/*     */   public void setAppearanceOverrideEnable(boolean paramBoolean) {
/* 617 */     if (isLiveOrCompiled() && 
/* 618 */       !getCapability(19)) {
/* 619 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D8"));
/*     */     }
/* 621 */     ((Shape3DRetained)this.retained).setAppearanceOverrideEnable(paramBoolean);
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
/*     */   public boolean getAppearanceOverrideEnable() {
/* 635 */     if (isLiveOrCompiled() && 
/* 636 */       !getCapability(18)) {
/* 637 */       throw new CapabilityNotSetException(J3dI18N.getString("Shape3D9"));
/*     */     }
/* 639 */     return ((Shape3DRetained)this.retained).getAppearanceOverrideEnable();
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 666 */     Shape3D shape3D = new Shape3D();
/* 667 */     shape3D.duplicateNode(this, paramBoolean);
/* 668 */     return shape3D;
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
/* 703 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { checkDuplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 731 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 733 */     Shape3DRetained shape3DRetained1 = (Shape3DRetained)paramNode.retained;
/* 734 */     Shape3DRetained shape3DRetained2 = (Shape3DRetained)this.retained;
/*     */     
/* 736 */     shape3DRetained2.setAppearance((Appearance)getNodeComponent(shape3DRetained1.getAppearance(), paramBoolean, paramNode.nodeHashtable));
/*     */ 
/*     */ 
/*     */     
/* 740 */     int i = shape3DRetained1.numGeometries(this.id);
/* 741 */     if (i > 0) {
/* 742 */       shape3DRetained2.setGeometry((Geometry)getNodeComponent(shape3DRetained1.getGeometry(0, this.id), paramBoolean, paramNode.nodeHashtable), 0);
/*     */ 
/*     */ 
/*     */       
/* 746 */       for (byte b = 1; b < i; b++) {
/* 747 */         shape3DRetained2.addGeometry((Geometry)getNodeComponent(shape3DRetained1.getGeometry(b, this.id), paramBoolean, paramNode.nodeHashtable));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 754 */     shape3DRetained2.setCollisionBounds(shape3DRetained1.getCollisionBounds(this.id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getBounds() {
/* 761 */     if (isLiveOrCompiled()) {
/* 762 */       if (!getCapability(3)) {
/* 763 */         throw new CapabilityNotSetException(J3dI18N.getString("Node2"));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 768 */       checkForCycle();
/*     */     } 
/*     */     
/* 771 */     return ((Shape3DRetained)this.retained).getBounds();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Shape3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */