/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
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
/*     */ public abstract class Light
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_STATE_READ = 12;
/*     */   public static final int ALLOW_STATE_WRITE = 13;
/*     */   public static final int ALLOW_COLOR_READ = 14;
/*     */   public static final int ALLOW_COLOR_WRITE = 15;
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_READ = 16;
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_WRITE = 17;
/*     */   public static final int ALLOW_SCOPE_READ = 28;
/*     */   public static final int ALLOW_SCOPE_WRITE = 29;
/* 224 */   private static final int[] readCapabilities = { 12, 14, 16, 28 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public Light() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Light(Color3f paramColor3f) {
/* 253 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 255 */     ((LightRetained)this.retained).initColor(paramColor3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Light(boolean paramBoolean, Color3f paramColor3f) {
/* 266 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 268 */     ((LightRetained)this.retained).initEnable(paramBoolean);
/* 269 */     ((LightRetained)this.retained).initColor(paramColor3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnable(boolean paramBoolean) {
/* 279 */     if (isLiveOrCompiled() && 
/* 280 */       !getCapability(13)) {
/* 281 */       throw new CapabilityNotSetException(J3dI18N.getString("Light0"));
/*     */     }
/* 283 */     if (isLive()) {
/* 284 */       ((LightRetained)this.retained).setEnable(paramBoolean);
/*     */     } else {
/* 286 */       ((LightRetained)this.retained).initEnable(paramBoolean);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getEnable() {
/* 296 */     if (isLiveOrCompiled() && 
/* 297 */       !getCapability(12)) {
/* 298 */       throw new CapabilityNotSetException(J3dI18N.getString("Light1"));
/*     */     }
/* 300 */     return ((LightRetained)this.retained).getEnable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(Color3f paramColor3f) {
/* 310 */     if (isLiveOrCompiled() && 
/* 311 */       !getCapability(15)) {
/* 312 */       throw new CapabilityNotSetException(J3dI18N.getString("Light2"));
/*     */     }
/* 314 */     if (isLive()) {
/* 315 */       ((LightRetained)this.retained).setColor(paramColor3f);
/*     */     } else {
/* 317 */       ((LightRetained)this.retained).initColor(paramColor3f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getColor(Color3f paramColor3f) {
/* 327 */     if (isLiveOrCompiled() && 
/* 328 */       !getCapability(14)) {
/* 329 */       throw new CapabilityNotSetException(J3dI18N.getString("Light3"));
/*     */     }
/* 331 */     ((LightRetained)this.retained).getColor(paramColor3f);
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
/*     */   public void setScope(Group paramGroup, int paramInt) {
/* 348 */     if (isLiveOrCompiled() && 
/* 349 */       !getCapability(29)) {
/* 350 */       throw new CapabilityNotSetException(J3dI18N.getString("Light4"));
/*     */     }
/* 352 */     if (isLive()) {
/* 353 */       ((LightRetained)this.retained).setScope(paramGroup, paramInt);
/*     */     } else {
/* 355 */       ((LightRetained)this.retained).initScope(paramGroup, paramInt);
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
/*     */   public Group getScope(int paramInt) {
/* 368 */     if (isLiveOrCompiled() && 
/* 369 */       !getCapability(28)) {
/* 370 */       throw new CapabilityNotSetException(J3dI18N.getString("Light5"));
/*     */     }
/* 372 */     return ((LightRetained)this.retained).getScope(paramInt);
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
/*     */   public void insertScope(Group paramGroup, int paramInt) {
/* 390 */     if (isLiveOrCompiled() && 
/* 391 */       !getCapability(29)) {
/* 392 */       throw new CapabilityNotSetException(J3dI18N.getString("Light6"));
/*     */     }
/* 394 */     if (isLive()) {
/* 395 */       ((LightRetained)this.retained).insertScope(paramGroup, paramInt);
/*     */     } else {
/* 397 */       ((LightRetained)this.retained).initInsertScope(paramGroup, paramInt);
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
/*     */   public void removeScope(int paramInt) {
/* 413 */     if (isLiveOrCompiled() && 
/* 414 */       !getCapability(29)) {
/* 415 */       throw new CapabilityNotSetException(J3dI18N.getString("Light7"));
/*     */     }
/* 417 */     if (isLive()) {
/* 418 */       ((LightRetained)this.retained).removeScope(paramInt);
/*     */     } else {
/* 420 */       ((LightRetained)this.retained).initRemoveScope(paramInt);
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
/*     */   public Enumeration getAllScopes() {
/* 432 */     if (isLiveOrCompiled() && 
/* 433 */       !getCapability(28)) {
/* 434 */       throw new CapabilityNotSetException(J3dI18N.getString("Light8"));
/*     */     }
/* 436 */     return ((LightRetained)this.retained).getAllScopes();
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
/*     */   public void addScope(Group paramGroup) {
/* 452 */     if (isLiveOrCompiled() && 
/* 453 */       !getCapability(29)) {
/* 454 */       throw new CapabilityNotSetException(J3dI18N.getString("Light9"));
/*     */     }
/* 456 */     if (isLive()) {
/* 457 */       ((LightRetained)this.retained).addScope(paramGroup);
/*     */     } else {
/* 459 */       ((LightRetained)this.retained).initAddScope(paramGroup);
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
/*     */   public int numScopes() {
/* 473 */     if (isLiveOrCompiled() && 
/* 474 */       !getCapability(28)) {
/* 475 */       throw new CapabilityNotSetException(J3dI18N.getString("Light8"));
/*     */     }
/* 477 */     return ((LightRetained)this.retained).numScopes();
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
/*     */   public int indexOfScope(Group paramGroup) {
/* 494 */     if (isLiveOrCompiled() && 
/* 495 */       !getCapability(28)) {
/* 496 */       throw new CapabilityNotSetException(J3dI18N.getString("Light8"));
/*     */     }
/* 498 */     return ((LightRetained)this.retained).indexOfScope(paramGroup);
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
/*     */   public void removeScope(Group paramGroup) {
/* 519 */     if (isLiveOrCompiled() && 
/* 520 */       !getCapability(29)) {
/* 521 */       throw new CapabilityNotSetException(J3dI18N.getString("Light7"));
/*     */     }
/* 523 */     if (isLive()) {
/* 524 */       ((LightRetained)this.retained).removeScope(paramGroup);
/*     */     } else {
/* 526 */       ((LightRetained)this.retained).initRemoveScope(paramGroup);
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
/*     */   public void removeAllScopes() {
/* 544 */     if (isLiveOrCompiled() && 
/* 545 */       !getCapability(29)) {
/* 546 */       throw new CapabilityNotSetException(J3dI18N.getString("Light7"));
/*     */     }
/* 548 */     if (isLive()) {
/* 549 */       ((LightRetained)this.retained).removeAllScopes();
/*     */     } else {
/* 551 */       ((LightRetained)this.retained).initRemoveAllScopes();
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
/*     */   public void setInfluencingBounds(Bounds paramBounds) {
/* 564 */     if (isLiveOrCompiled() && 
/* 565 */       !getCapability(17)) {
/* 566 */       throw new CapabilityNotSetException(J3dI18N.getString("Light11"));
/*     */     }
/* 568 */     if (isLive()) {
/* 569 */       ((LightRetained)this.retained).setInfluencingBounds(paramBounds);
/*     */     } else {
/* 571 */       ((LightRetained)this.retained).initInfluencingBounds(paramBounds);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getInfluencingBounds() {
/* 581 */     if (isLiveOrCompiled() && 
/* 582 */       !getCapability(16)) {
/* 583 */       throw new CapabilityNotSetException(J3dI18N.getString("Light12"));
/*     */     }
/* 585 */     return ((LightRetained)this.retained).getInfluencingBounds();
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
/*     */   public void setInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 598 */     if (isLiveOrCompiled() && 
/* 599 */       !getCapability(17)) {
/* 600 */       throw new CapabilityNotSetException(J3dI18N.getString("Light11"));
/*     */     }
/* 602 */     if (isLive()) {
/* 603 */       ((LightRetained)this.retained).setInfluencingBoundingLeaf(paramBoundingLeaf);
/*     */     } else {
/* 605 */       ((LightRetained)this.retained).initInfluencingBoundingLeaf(paramBoundingLeaf);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf getInfluencingBoundingLeaf() {
/* 615 */     if (isLiveOrCompiled() && 
/* 616 */       !getCapability(16)) {
/* 617 */       throw new CapabilityNotSetException(J3dI18N.getString("Light12"));
/*     */     }
/* 619 */     return ((LightRetained)this.retained).getInfluencingBoundingLeaf();
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 646 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 648 */     LightRetained lightRetained1 = (LightRetained)paramNode.retained;
/* 649 */     LightRetained lightRetained2 = (LightRetained)this.retained;
/*     */     
/* 651 */     Color3f color3f = new Color3f();
/* 652 */     lightRetained1.getColor(color3f);
/* 653 */     lightRetained2.initColor(color3f);
/* 654 */     lightRetained2.initInfluencingBounds(lightRetained1.getInfluencingBounds());
/*     */     
/* 656 */     Enumeration enumeration = lightRetained1.getAllScopes();
/* 657 */     while (enumeration.hasMoreElements())
/*     */     {
/* 659 */       lightRetained2.initAddScope((Group)enumeration.nextElement());
/*     */     }
/*     */ 
/*     */     
/* 663 */     lightRetained2.initInfluencingBoundingLeaf(lightRetained1.getInfluencingBoundingLeaf());
/*     */     
/* 665 */     lightRetained2.initEnable(lightRetained1.getEnable());
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
/* 697 */     LightRetained lightRetained = (LightRetained)this.retained;
/* 698 */     BoundingLeaf boundingLeaf = lightRetained.getInfluencingBoundingLeaf();
/*     */     
/* 700 */     if (boundingLeaf != null) {
/* 701 */       SceneGraphObject sceneGraphObject = paramNodeReferenceTable.getNewObjectReference(boundingLeaf);
/* 702 */       lightRetained.initInfluencingBoundingLeaf((BoundingLeaf)sceneGraphObject);
/*     */     } 
/*     */     
/* 705 */     int i = lightRetained.numScopes();
/* 706 */     for (byte b = 0; b < i; b++)
/* 707 */       lightRetained.initScope((Group)paramNodeReferenceTable.getNewObjectReference(lightRetained.getScope(b)), b); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Light.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */