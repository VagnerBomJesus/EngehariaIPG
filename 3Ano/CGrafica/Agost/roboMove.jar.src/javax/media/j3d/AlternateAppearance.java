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
/*     */ public class AlternateAppearance
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_READ = 12;
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_WRITE = 13;
/*     */   public static final int ALLOW_APPEARANCE_READ = 14;
/*     */   public static final int ALLOW_APPEARANCE_WRITE = 15;
/*     */   public static final int ALLOW_SCOPE_READ = 16;
/*     */   public static final int ALLOW_SCOPE_WRITE = 17;
/*  96 */   private static final int[] readCapabilities = { 12, 14, 16 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public AlternateAppearance() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AlternateAppearance(Appearance paramAppearance) {
/* 127 */     setDefaultReadCapabilities(readCapabilities);
/* 128 */     ((AlternateAppearanceRetained)this.retained).initAppearance(paramAppearance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 136 */     this.retained = new AlternateAppearanceRetained();
/* 137 */     this.retained.setSource(this);
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
/*     */   public void setAppearance(Appearance paramAppearance) {
/* 149 */     if (isLiveOrCompiled() && 
/* 150 */       !getCapability(15)) {
/* 151 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance0"));
/*     */     }
/* 153 */     if (isLive()) {
/* 154 */       ((AlternateAppearanceRetained)this.retained).setAppearance(paramAppearance);
/*     */     } else {
/* 156 */       ((AlternateAppearanceRetained)this.retained).initAppearance(paramAppearance);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Appearance getAppearance() {
/* 167 */     if (isLiveOrCompiled() && 
/* 168 */       !getCapability(14)) {
/* 169 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance2"));
/*     */     }
/* 171 */     return ((AlternateAppearanceRetained)this.retained).getAppearance();
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
/*     */   public void setInfluencingBounds(Bounds paramBounds) {
/* 185 */     if (isLiveOrCompiled() && 
/* 186 */       !getCapability(13)) {
/* 187 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance3"));
/*     */     }
/*     */     
/* 190 */     if (isLive()) {
/* 191 */       ((AlternateAppearanceRetained)this.retained).setInfluencingBounds(paramBounds);
/*     */     } else {
/* 193 */       ((AlternateAppearanceRetained)this.retained).initInfluencingBounds(paramBounds);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getInfluencingBounds() {
/* 203 */     if (isLiveOrCompiled() && 
/* 204 */       !getCapability(12)) {
/* 205 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance4"));
/*     */     }
/*     */     
/* 208 */     return ((AlternateAppearanceRetained)this.retained).getInfluencingBounds();
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
/* 223 */     if (isLiveOrCompiled() && 
/* 224 */       !getCapability(13)) {
/* 225 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance3"));
/*     */     }
/*     */     
/* 228 */     if (isLive()) {
/* 229 */       ((AlternateAppearanceRetained)this.retained).setInfluencingBoundingLeaf(paramBoundingLeaf);
/*     */     } else {
/* 231 */       ((AlternateAppearanceRetained)this.retained).initInfluencingBoundingLeaf(paramBoundingLeaf);
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
/* 242 */     if (isLiveOrCompiled() && 
/* 243 */       !getCapability(12)) {
/* 244 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance4"));
/*     */     }
/*     */     
/* 247 */     return ((AlternateAppearanceRetained)this.retained).getInfluencingBoundingLeaf();
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
/*     */   public void setScope(Group paramGroup, int paramInt) {
/* 267 */     if (isLiveOrCompiled() && 
/* 268 */       !getCapability(17)) {
/* 269 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance7"));
/*     */     }
/*     */ 
/*     */     
/* 273 */     if (isLive()) {
/* 274 */       ((AlternateAppearanceRetained)this.retained).setScope(paramGroup, paramInt);
/*     */     } else {
/* 276 */       ((AlternateAppearanceRetained)this.retained).initScope(paramGroup, paramInt);
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
/* 289 */     if (isLiveOrCompiled() && 
/* 290 */       !getCapability(16)) {
/* 291 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance8"));
/*     */     }
/*     */     
/* 294 */     return ((AlternateAppearanceRetained)this.retained).getScope(paramInt);
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
/*     */   public void insertScope(Group paramGroup, int paramInt) {
/* 313 */     if (isLiveOrCompiled() && 
/* 314 */       !getCapability(17)) {
/* 315 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance9"));
/*     */     }
/*     */     
/* 318 */     if (isLive()) {
/* 319 */       ((AlternateAppearanceRetained)this.retained).insertScope(paramGroup, paramInt);
/*     */     } else {
/* 321 */       ((AlternateAppearanceRetained)this.retained).initInsertScope(paramGroup, paramInt);
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
/*     */   public void removeScope(int paramInt) {
/* 340 */     if (isLiveOrCompiled() && 
/* 341 */       !getCapability(17)) {
/* 342 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance10"));
/*     */     }
/*     */     
/* 345 */     if (isLive()) {
/* 346 */       ((AlternateAppearanceRetained)this.retained).removeScope(paramInt);
/*     */     } else {
/* 348 */       ((AlternateAppearanceRetained)this.retained).initRemoveScope(paramInt);
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
/*     */   public Enumeration getAllScopes() {
/* 361 */     if (isLiveOrCompiled() && 
/* 362 */       !getCapability(16)) {
/* 363 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance11"));
/*     */     }
/*     */     
/* 366 */     return ((AlternateAppearanceRetained)this.retained).getAllScopes();
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
/*     */   public void addScope(Group paramGroup) {
/* 384 */     if (isLiveOrCompiled() && 
/* 385 */       !getCapability(17)) {
/* 386 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance12"));
/*     */     }
/*     */     
/* 389 */     if (isLive()) {
/* 390 */       ((AlternateAppearanceRetained)this.retained).addScope(paramGroup);
/*     */     } else {
/* 392 */       ((AlternateAppearanceRetained)this.retained).initAddScope(paramGroup);
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
/*     */   public int numScopes() {
/* 409 */     if (isLiveOrCompiled() && 
/* 410 */       !getCapability(16)) {
/* 411 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance11"));
/*     */     }
/*     */     
/* 414 */     return ((AlternateAppearanceRetained)this.retained).numScopes();
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
/* 431 */     if (isLiveOrCompiled() && 
/* 432 */       !getCapability(16)) {
/* 433 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance8"));
/*     */     }
/* 435 */     return ((AlternateAppearanceRetained)this.retained).indexOfScope(paramGroup);
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
/* 456 */     if (isLiveOrCompiled() && 
/* 457 */       !getCapability(17)) {
/* 458 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance10"));
/*     */     }
/* 460 */     if (isLive()) {
/* 461 */       ((AlternateAppearanceRetained)this.retained).removeScope(paramGroup);
/*     */     } else {
/* 463 */       ((AlternateAppearanceRetained)this.retained).initRemoveScope(paramGroup);
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
/* 481 */     if (isLiveOrCompiled() && 
/* 482 */       !getCapability(17))
/* 483 */       throw new CapabilityNotSetException(J3dI18N.getString("AlternateAppearance10")); 
/* 484 */     if (isLive()) {
/* 485 */       ((AlternateAppearanceRetained)this.retained).removeAllScopes();
/*     */     } else {
/* 487 */       ((AlternateAppearanceRetained)this.retained).initRemoveAllScopes();
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 513 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 515 */     AlternateAppearanceRetained alternateAppearanceRetained1 = (AlternateAppearanceRetained)paramNode.retained;
/*     */     
/* 517 */     AlternateAppearanceRetained alternateAppearanceRetained2 = (AlternateAppearanceRetained)this.retained;
/*     */     
/* 519 */     alternateAppearanceRetained2.initAppearance((Appearance)getNodeComponent(alternateAppearanceRetained1.getAppearance(), paramBoolean, paramNode.nodeHashtable));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 524 */     alternateAppearanceRetained2.initInfluencingBounds(alternateAppearanceRetained1.getInfluencingBounds());
/*     */     
/* 526 */     Enumeration enumeration = alternateAppearanceRetained1.getAllScopes();
/* 527 */     while (enumeration.hasMoreElements())
/*     */     {
/* 529 */       alternateAppearanceRetained2.initAddScope((Group)enumeration.nextElement());
/*     */     }
/*     */ 
/*     */     
/* 533 */     alternateAppearanceRetained2.initInfluencingBoundingLeaf(alternateAppearanceRetained1.getInfluencingBoundingLeaf());
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
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 564 */     AlternateAppearanceRetained alternateAppearanceRetained = (AlternateAppearanceRetained)this.retained;
/*     */ 
/*     */     
/* 567 */     BoundingLeaf boundingLeaf = alternateAppearanceRetained.getInfluencingBoundingLeaf();
/*     */     
/* 569 */     if (boundingLeaf != null) {
/* 570 */       SceneGraphObject sceneGraphObject = paramNodeReferenceTable.getNewObjectReference(boundingLeaf);
/* 571 */       alternateAppearanceRetained.initInfluencingBoundingLeaf((BoundingLeaf)sceneGraphObject);
/*     */     } 
/*     */     
/* 574 */     int i = alternateAppearanceRetained.numScopes();
/* 575 */     for (byte b = 0; b < i; b++) {
/* 576 */       alternateAppearanceRetained.initScope((Group)paramNodeReferenceTable.getNewObjectReference(alternateAppearanceRetained.getScope(b)), b);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 597 */     AlternateAppearance alternateAppearance = new AlternateAppearance();
/* 598 */     alternateAppearance.duplicateNode(this, paramBoolean);
/* 599 */     return alternateAppearance;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\AlternateAppearance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */