/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Morph
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_GEOMETRY_ARRAY_READ = 12;
/*     */   public static final int ALLOW_GEOMETRY_ARRAY_WRITE = 13;
/*     */   public static final int ALLOW_APPEARANCE_READ = 14;
/*     */   public static final int ALLOW_APPEARANCE_WRITE = 15;
/*     */   public static final int ALLOW_WEIGHTS_READ = 16;
/*     */   public static final int ALLOW_WEIGHTS_WRITE = 17;
/*     */   public static final int ALLOW_COLLISION_BOUNDS_READ = 18;
/*     */   public static final int ALLOW_COLLISION_BOUNDS_WRITE = 19;
/*     */   public static final int ALLOW_APPEARANCE_OVERRIDE_READ = 20;
/*     */   public static final int ALLOW_APPEARANCE_OVERRIDE_WRITE = 21;
/* 155 */   private static final int[] readCapabilities = { 12, 14, 16, 18, 20 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   Morph() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Morph(GeometryArray[] paramArrayOfGeometryArray) {
/* 210 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 212 */     ((MorphRetained)this.retained).setGeometryArrays(paramArrayOfGeometryArray);
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
/*     */   public Morph(GeometryArray[] paramArrayOfGeometryArray, Appearance paramAppearance) {
/* 248 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 250 */     ((MorphRetained)this.retained).setGeometryArrays(paramArrayOfGeometryArray);
/* 251 */     ((MorphRetained)this.retained).setAppearance(paramAppearance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 259 */     this.retained = new MorphRetained();
/* 260 */     this.retained.setSource(this);
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
/* 271 */     if (isLiveOrCompiled() && 
/* 272 */       !getCapability(19)) {
/* 273 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph0"));
/*     */     }
/* 275 */     ((MorphRetained)this.retained).setCollisionBounds(paramBounds);
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
/* 286 */     if (isLiveOrCompiled() && 
/* 287 */       !getCapability(18)) {
/* 288 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph1"));
/*     */     }
/* 290 */     return ((MorphRetained)this.retained).getCollisionBounds();
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
/*     */   public void setGeometryArrays(GeometryArray[] paramArrayOfGeometryArray) {
/* 342 */     if (isLiveOrCompiled() && 
/* 343 */       !getCapability(13)) {
/* 344 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph2"));
/*     */     }
/* 346 */     ((MorphRetained)this.retained).setGeometryArrays(paramArrayOfGeometryArray);
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
/*     */   public GeometryArray getGeometryArray(int paramInt) {
/* 358 */     if (isLiveOrCompiled() && 
/* 359 */       !getCapability(12)) {
/* 360 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph3"));
/*     */     }
/* 362 */     return ((MorphRetained)this.retained).getGeometryArray(paramInt);
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
/* 375 */     if (isLiveOrCompiled() && 
/* 376 */       !getCapability(15)) {
/* 377 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph4"));
/*     */     }
/* 379 */     ((MorphRetained)this.retained).setAppearance(paramAppearance);
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
/* 390 */     if (isLiveOrCompiled() && 
/* 391 */       !getCapability(14)) {
/* 392 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph5"));
/*     */     }
/* 394 */     return ((MorphRetained)this.retained).getAppearance();
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
/* 417 */   public boolean intersect(SceneGraphPath paramSceneGraphPath, PickShape paramPickShape) { return intersect(paramSceneGraphPath, paramPickShape, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 441 */     if (isLiveOrCompiled()) {
/* 442 */       checkForAllowIntersect();
/*     */     }
/*     */     
/* 445 */     return ((MorphRetained)this.retained).intersect(paramSceneGraphPath, paramPickRay, paramArrayOfDouble);
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
/* 475 */     if (isLiveOrCompiled()) {
/* 476 */       checkForAllowIntersect();
/*     */     }
/*     */     
/* 479 */     if (paramPickShape instanceof PickPoint) {
/* 480 */       throw new IllegalArgumentException(J3dI18N.getString("Morph10"));
/*     */     }
/*     */     
/* 483 */     return ((MorphRetained)this.retained).intersect(paramSceneGraphPath, paramPickShape, paramArrayOfDouble);
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
/*     */   public void setWeights(double[] paramArrayOfDouble) {
/* 507 */     if (isLiveOrCompiled() && 
/* 508 */       !getCapability(17)) {
/* 509 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph8"));
/*     */     }
/* 511 */     ((MorphRetained)this.retained).setWeights(paramArrayOfDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getWeights() {
/* 521 */     if (isLiveOrCompiled() && 
/* 522 */       !getCapability(16)) {
/* 523 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph9"));
/*     */     }
/* 525 */     return ((MorphRetained)this.retained).getWeights();
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
/*     */   public void setAppearanceOverrideEnable(boolean paramBoolean) {
/* 545 */     if (isLiveOrCompiled() && 
/* 546 */       !getCapability(21)) {
/* 547 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph11"));
/*     */     }
/* 549 */     ((MorphRetained)this.retained).setAppearanceOverrideEnable(paramBoolean);
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
/*     */   public boolean getAppearanceOverrideEnable() {
/* 562 */     if (isLiveOrCompiled() && 
/* 563 */       !getCapability(20)) {
/* 564 */       throw new CapabilityNotSetException(J3dI18N.getString("Morph12"));
/*     */     }
/* 566 */     return ((MorphRetained)this.retained).getAppearanceOverrideEnable();
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 584 */     Morph morph = new Morph();
/* 585 */     morph.duplicateNode(this, paramBoolean);
/* 586 */     return morph;
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
/* 621 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { checkDuplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 646 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 648 */     MorphRetained morphRetained1 = (MorphRetained)paramNode.retained;
/* 649 */     MorphRetained morphRetained2 = (MorphRetained)this.retained;
/*     */     
/* 651 */     Hashtable hashtable = paramNode.nodeHashtable;
/*     */     
/* 653 */     double[] arrayOfDouble = morphRetained1.getWeights();
/*     */     
/* 655 */     morphRetained2.setCollisionBounds(morphRetained1.getCollisionBounds());
/* 656 */     morphRetained2.setAppearance((Appearance)getNodeComponent(morphRetained1.getAppearance(), paramBoolean, hashtable));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 661 */     GeometryArray[] arrayOfGeometryArray = new GeometryArray[arrayOfDouble.length];
/*     */     
/* 663 */     for (int i = arrayOfDouble.length - 1; i >= 0; i--) {
/* 664 */       arrayOfGeometryArray[i] = (GeometryArray)getNodeComponent(morphRetained1.getGeometryArray(i), paramBoolean, hashtable);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 669 */     morphRetained2.setGeometryArrays(arrayOfGeometryArray);
/* 670 */     morphRetained2.setWeights(arrayOfDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkForAllowIntersect() {
/* 677 */     MorphRetained morphRetained = (MorphRetained)this.retained;
/* 678 */     int i = morphRetained.getNumGeometryArrays();
/* 679 */     for (byte b = 0; b < i; b++) {
/* 680 */       if (!(morphRetained.geometryArrays[b]).source.getCapability(18))
/*     */       {
/*     */         
/* 683 */         throw new CapabilityNotSetException(J3dI18N.getString("Morph6"));
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Morph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */