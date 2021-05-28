/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import javax.vecmath.Vector4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelClip
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_READ = 12;
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_WRITE = 13;
/*     */   public static final int ALLOW_PLANE_READ = 14;
/*     */   public static final int ALLOW_PLANE_WRITE = 15;
/*     */   public static final int ALLOW_ENABLE_READ = 16;
/*     */   public static final int ALLOW_ENABLE_WRITE = 17;
/*     */   public static final int ALLOW_SCOPE_READ = 18;
/*     */   public static final int ALLOW_SCOPE_WRITE = 19;
/* 113 */   private static final int[] readCapabilities = { 18, 16, 12, 14 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public ModelClip() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelClip(Vector4d[] paramArrayOfVector4d) {
/* 151 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 153 */     ((ModelClipRetained)this.retained).initPlanes(paramArrayOfVector4d);
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
/*     */   public ModelClip(Vector4d[] paramArrayOfVector4d, boolean[] paramArrayOfBoolean) {
/* 166 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 168 */     ((ModelClipRetained)this.retained).initPlanes(paramArrayOfVector4d);
/* 169 */     ((ModelClipRetained)this.retained).initEnables(paramArrayOfBoolean);
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
/*     */   public void setInfluencingBounds(Bounds paramBounds) {
/* 182 */     if (isLiveOrCompiled() && 
/* 183 */       !getCapability(13)) {
/* 184 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip0"));
/*     */     }
/* 186 */     if (isLive()) {
/* 187 */       ((ModelClipRetained)this.retained).setInfluencingBounds(paramBounds);
/*     */     } else {
/* 189 */       ((ModelClipRetained)this.retained).initInfluencingBounds(paramBounds);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getInfluencingBounds() {
/* 200 */     if (isLiveOrCompiled() && 
/* 201 */       !getCapability(12)) {
/* 202 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip1"));
/*     */     }
/* 204 */     return ((ModelClipRetained)this.retained).getInfluencingBounds();
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
/*     */   public void setInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 219 */     if (isLiveOrCompiled() && 
/* 220 */       !getCapability(13)) {
/* 221 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip13"));
/*     */     }
/* 223 */     if (isLive()) {
/* 224 */       ((ModelClipRetained)this.retained).setInfluencingBoundingLeaf(paramBoundingLeaf);
/*     */     } else {
/* 226 */       ((ModelClipRetained)this.retained).initInfluencingBoundingLeaf(paramBoundingLeaf);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf getInfluencingBoundingLeaf() {
/* 237 */     if (isLiveOrCompiled() && 
/* 238 */       !getCapability(12)) {
/* 239 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip14"));
/*     */     }
/* 241 */     return ((ModelClipRetained)this.retained).getInfluencingBoundingLeaf();
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
/*     */   public void setScope(Group paramGroup, int paramInt) {
/* 259 */     if (isLiveOrCompiled() && 
/* 260 */       !getCapability(19)) {
/* 261 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip7"));
/*     */     }
/* 263 */     if (isLive()) {
/* 264 */       ((ModelClipRetained)this.retained).setScope(paramGroup, paramInt);
/*     */     } else {
/* 266 */       ((ModelClipRetained)this.retained).initScope(paramGroup, paramInt);
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
/* 279 */     if (isLiveOrCompiled() && 
/* 280 */       !getCapability(18)) {
/* 281 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip8"));
/*     */     }
/* 283 */     return ((ModelClipRetained)this.retained).getScope(paramInt);
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
/* 301 */     if (isLiveOrCompiled() && 
/* 302 */       !getCapability(19)) {
/* 303 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip9"));
/*     */     }
/* 305 */     if (isLive()) {
/* 306 */       ((ModelClipRetained)this.retained).insertScope(paramGroup, paramInt);
/*     */     } else {
/* 308 */       ((ModelClipRetained)this.retained).initInsertScope(paramGroup, paramInt);
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
/* 324 */     if (isLiveOrCompiled() && 
/* 325 */       !getCapability(19)) {
/* 326 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip10"));
/*     */     }
/* 328 */     if (isLive()) {
/* 329 */       ((ModelClipRetained)this.retained).removeScope(paramInt);
/*     */     } else {
/* 331 */       ((ModelClipRetained)this.retained).initRemoveScope(paramInt);
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
/* 343 */     if (isLiveOrCompiled() && 
/* 344 */       !getCapability(18)) {
/* 345 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip11"));
/*     */     }
/* 347 */     return ((ModelClipRetained)this.retained).getAllScopes();
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
/* 363 */     if (isLiveOrCompiled() && 
/* 364 */       !getCapability(19)) {
/* 365 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip12"));
/*     */     }
/* 367 */     if (isLive()) {
/* 368 */       ((ModelClipRetained)this.retained).addScope(paramGroup);
/*     */     } else {
/* 370 */       ((ModelClipRetained)this.retained).initAddScope(paramGroup);
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
/* 384 */     if (isLiveOrCompiled() && 
/* 385 */       !getCapability(18)) {
/* 386 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip11"));
/*     */     }
/* 388 */     return ((ModelClipRetained)this.retained).numScopes();
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
/* 405 */     if (isLiveOrCompiled() && 
/* 406 */       !getCapability(18))
/* 407 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip8")); 
/* 408 */     return ((ModelClipRetained)this.retained).indexOfScope(paramGroup);
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
/* 429 */     if (isLiveOrCompiled() && 
/* 430 */       !getCapability(19))
/* 431 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip10")); 
/* 432 */     if (isLive()) {
/* 433 */       ((ModelClipRetained)this.retained).removeScope(paramGroup);
/*     */     } else {
/* 435 */       ((ModelClipRetained)this.retained).initRemoveScope(paramGroup);
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
/* 453 */     if (isLiveOrCompiled() && 
/* 454 */       !getCapability(19))
/* 455 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip10")); 
/* 456 */     if (isLive()) {
/* 457 */       ((ModelClipRetained)this.retained).removeAllScopes();
/*     */     } else {
/* 459 */       ((ModelClipRetained)this.retained).initRemoveAllScopes();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlanes(Vector4d[] paramArrayOfVector4d) {
/* 470 */     if (isLiveOrCompiled() && 
/* 471 */       !getCapability(15)) {
/* 472 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip2"));
/*     */     }
/* 474 */     if (isLive()) {
/* 475 */       ((ModelClipRetained)this.retained).setPlanes(paramArrayOfVector4d);
/*     */     } else {
/* 477 */       ((ModelClipRetained)this.retained).initPlanes(paramArrayOfVector4d);
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
/*     */   public void getPlanes(Vector4d[] paramArrayOfVector4d) {
/* 490 */     if (isLiveOrCompiled() && 
/* 491 */       !getCapability(14)) {
/* 492 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip3"));
/*     */     }
/* 494 */     ((ModelClipRetained)this.retained).getPlanes(paramArrayOfVector4d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlane(int paramInt, Vector4d paramVector4d) {
/* 505 */     if (isLiveOrCompiled() && 
/* 506 */       !getCapability(15)) {
/* 507 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip2"));
/*     */     }
/* 509 */     if (isLive()) {
/* 510 */       ((ModelClipRetained)this.retained).setPlane(paramInt, paramVector4d);
/*     */     } else {
/* 512 */       ((ModelClipRetained)this.retained).initPlane(paramInt, paramVector4d);
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
/*     */   public void getPlane(int paramInt, Vector4d paramVector4d) {
/* 526 */     if (isLiveOrCompiled() && 
/* 527 */       !getCapability(14)) {
/* 528 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip3"));
/*     */     }
/* 530 */     ((ModelClipRetained)this.retained).getPlane(paramInt, paramVector4d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnables(boolean[] paramArrayOfBoolean) {
/* 540 */     if (isLiveOrCompiled() && 
/* 541 */       !getCapability(17)) {
/* 542 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip4"));
/*     */     }
/* 544 */     if (isLive()) {
/* 545 */       ((ModelClipRetained)this.retained).setEnables(paramArrayOfBoolean);
/*     */     } else {
/* 547 */       ((ModelClipRetained)this.retained).initEnables(paramArrayOfBoolean);
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
/*     */   public void getEnables(boolean[] paramArrayOfBoolean) {
/* 559 */     if (isLiveOrCompiled() && 
/* 560 */       !getCapability(16)) {
/* 561 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip5"));
/*     */     }
/* 563 */     ((ModelClipRetained)this.retained).getEnables(paramArrayOfBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnable(int paramInt, boolean paramBoolean) {
/* 573 */     if (isLiveOrCompiled() && 
/* 574 */       !getCapability(17)) {
/* 575 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip4"));
/*     */     }
/* 577 */     if (isLive()) {
/* 578 */       ((ModelClipRetained)this.retained).setEnable(paramInt, paramBoolean);
/*     */     } else {
/* 580 */       ((ModelClipRetained)this.retained).initEnable(paramInt, paramBoolean);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getEnable(int paramInt) {
/* 590 */     if (isLiveOrCompiled() && 
/* 591 */       !getCapability(16)) {
/* 592 */       throw new CapabilityNotSetException(J3dI18N.getString("ModelClip5"));
/*     */     }
/* 594 */     return ((ModelClipRetained)this.retained).getEnable(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 602 */     this.retained = new ModelClipRetained();
/* 603 */     this.retained.setSource(this);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 623 */     ModelClip modelClip = new ModelClip();
/* 624 */     modelClip.duplicateNode(this, paramBoolean);
/* 625 */     return modelClip;
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
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 655 */     ModelClipRetained modelClipRetained = (ModelClipRetained)this.retained;
/* 656 */     BoundingLeaf boundingLeaf = modelClipRetained.getInfluencingBoundingLeaf();
/*     */ 
/*     */     
/* 659 */     if (boundingLeaf != null) {
/* 660 */       SceneGraphObject sceneGraphObject = paramNodeReferenceTable.getNewObjectReference(boundingLeaf);
/* 661 */       modelClipRetained.initInfluencingBoundingLeaf((BoundingLeaf)sceneGraphObject);
/*     */     } 
/*     */     
/* 664 */     int i = modelClipRetained.numScopes();
/* 665 */     for (byte b = 0; b < i; b++) {
/* 666 */       modelClipRetained.initScope((Group)paramNodeReferenceTable.getNewObjectReference(modelClipRetained.getScope(b)), b);
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 694 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 696 */     ModelClipRetained modelClipRetained1 = (ModelClipRetained)paramNode.retained;
/*     */     
/* 698 */     ModelClipRetained modelClipRetained2 = (ModelClipRetained)this.retained;
/*     */     
/* 700 */     Vector4d vector4d = new Vector4d();
/*     */     
/* 702 */     for (byte b = 5; b >= 0; b--) {
/* 703 */       modelClipRetained1.getPlane(b, vector4d);
/* 704 */       modelClipRetained2.initPlane(b, vector4d);
/* 705 */       modelClipRetained2.initEnable(b, modelClipRetained1.getEnable(b));
/*     */     } 
/* 707 */     modelClipRetained2.initInfluencingBounds(modelClipRetained1.getInfluencingBounds());
/*     */     
/* 709 */     Enumeration enumeration = modelClipRetained1.getAllScopes();
/* 710 */     while (enumeration.hasMoreElements())
/*     */     {
/* 712 */       modelClipRetained2.initAddScope((Group)enumeration.nextElement());
/*     */     }
/*     */ 
/*     */     
/* 716 */     modelClipRetained2.initInfluencingBoundingLeaf(modelClipRetained1.getInfluencingBoundingLeaf());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ModelClip.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */