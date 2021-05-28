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
/*     */ public abstract class Fog
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_READ = 12;
/*     */   public static final int ALLOW_INFLUENCING_BOUNDS_WRITE = 13;
/*     */   public static final int ALLOW_COLOR_READ = 14;
/*     */   public static final int ALLOW_COLOR_WRITE = 15;
/*     */   public static final int ALLOW_SCOPE_READ = 18;
/*     */   public static final int ALLOW_SCOPE_WRITE = 19;
/*  81 */   private static final int[] readCapabilities = { 12, 14, 18 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Fog() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Fog(Color3f paramColor3f) {
/* 109 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 111 */     ((FogRetained)this.retained).initColor(paramColor3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Fog(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 122 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 124 */     ((FogRetained)this.retained).initColor(paramFloat1, paramFloat2, paramFloat3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(Color3f paramColor3f) {
/* 134 */     if (isLiveOrCompiled() && 
/* 135 */       !getCapability(15)) {
/* 136 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog0"));
/*     */     }
/* 138 */     if (isLive()) {
/* 139 */       ((FogRetained)this.retained).setColor(paramColor3f);
/*     */     } else {
/* 141 */       ((FogRetained)this.retained).initColor(paramColor3f);
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
/*     */   public void setColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 153 */     if (isLiveOrCompiled() && 
/* 154 */       !getCapability(15)) {
/* 155 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog0"));
/*     */     }
/* 157 */     if (isLive()) {
/* 158 */       ((FogRetained)this.retained).setColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 160 */       ((FogRetained)this.retained).initColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getColor(Color3f paramColor3f) {
/* 170 */     if (isLiveOrCompiled() && 
/* 171 */       !getCapability(14)) {
/* 172 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog2"));
/*     */     }
/* 174 */     ((FogRetained)this.retained).getColor(paramColor3f);
/*     */   }
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
/* 187 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog3"));
/*     */     }
/* 189 */     if (isLive()) {
/* 190 */       ((FogRetained)this.retained).setInfluencingBounds(paramBounds);
/*     */     } else {
/* 192 */       ((FogRetained)this.retained).initInfluencingBounds(paramBounds);
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
/* 203 */     if (isLiveOrCompiled() && 
/* 204 */       !getCapability(12)) {
/* 205 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog4"));
/*     */     }
/* 207 */     return ((FogRetained)this.retained).getInfluencingBounds();
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
/* 220 */     if (isLiveOrCompiled() && 
/* 221 */       !getCapability(13)) {
/* 222 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog3"));
/*     */     }
/* 224 */     if (isLive()) {
/* 225 */       ((FogRetained)this.retained).setInfluencingBoundingLeaf(paramBoundingLeaf);
/*     */     } else {
/* 227 */       ((FogRetained)this.retained).initInfluencingBoundingLeaf(paramBoundingLeaf);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf getInfluencingBoundingLeaf() {
/* 237 */     if (isLiveOrCompiled() && 
/* 238 */       !getCapability(12)) {
/* 239 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog4"));
/*     */     }
/* 241 */     return ((FogRetained)this.retained).getInfluencingBoundingLeaf();
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
/* 261 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog7"));
/*     */     }
/*     */     
/* 264 */     if (isLive()) {
/* 265 */       ((FogRetained)this.retained).setScope(paramGroup, paramInt);
/*     */     } else {
/* 267 */       ((FogRetained)this.retained).initScope(paramGroup, paramInt);
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
/* 280 */     if (isLiveOrCompiled() && 
/* 281 */       !getCapability(18)) {
/* 282 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog8"));
/*     */     }
/* 284 */     return ((FogRetained)this.retained).getScope(paramInt);
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
/* 302 */     if (isLiveOrCompiled() && 
/* 303 */       !getCapability(19)) {
/* 304 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog9"));
/*     */     }
/* 306 */     if (isLive()) {
/* 307 */       ((FogRetained)this.retained).insertScope(paramGroup, paramInt);
/*     */     } else {
/* 309 */       ((FogRetained)this.retained).initInsertScope(paramGroup, paramInt);
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
/* 325 */     if (isLiveOrCompiled() && 
/* 326 */       !getCapability(19)) {
/* 327 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog10"));
/*     */     }
/* 329 */     if (isLive()) {
/* 330 */       ((FogRetained)this.retained).removeScope(paramInt);
/*     */     } else {
/* 332 */       ((FogRetained)this.retained).initRemoveScope(paramInt);
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
/* 344 */     if (isLiveOrCompiled() && 
/* 345 */       !getCapability(18)) {
/* 346 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog11"));
/*     */     }
/* 348 */     return ((FogRetained)this.retained).getAllScopes();
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
/* 364 */     if (isLiveOrCompiled() && 
/* 365 */       !getCapability(19)) {
/* 366 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog12"));
/*     */     }
/* 368 */     if (isLive()) {
/* 369 */       ((FogRetained)this.retained).addScope(paramGroup);
/*     */     } else {
/* 371 */       ((FogRetained)this.retained).initAddScope(paramGroup);
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
/* 385 */     if (isLiveOrCompiled() && 
/* 386 */       !getCapability(18)) {
/* 387 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog11"));
/*     */     }
/* 389 */     return ((FogRetained)this.retained).numScopes();
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
/* 406 */     if (isLiveOrCompiled() && 
/* 407 */       !getCapability(18))
/* 408 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog8")); 
/* 409 */     return ((FogRetained)this.retained).indexOfScope(paramGroup);
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
/* 430 */     if (isLiveOrCompiled() && 
/* 431 */       !getCapability(19)) {
/* 432 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog10"));
/*     */     }
/* 434 */     if (isLive()) {
/* 435 */       ((FogRetained)this.retained).removeScope(paramGroup);
/*     */     } else {
/* 437 */       ((FogRetained)this.retained).initRemoveScope(paramGroup);
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
/* 455 */     if (isLiveOrCompiled() && 
/* 456 */       !getCapability(19)) {
/* 457 */       throw new CapabilityNotSetException(J3dI18N.getString("Fog10"));
/*     */     }
/* 459 */     if (isLive()) {
/* 460 */       ((FogRetained)this.retained).removeAllScopes();
/*     */     } else {
/* 462 */       ((FogRetained)this.retained).initRemoveAllScopes();
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
/* 488 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 490 */     FogRetained fogRetained1 = (FogRetained)paramNode.retained;
/* 491 */     FogRetained fogRetained2 = (FogRetained)this.retained;
/*     */     
/* 493 */     Color3f color3f = new Color3f();
/* 494 */     fogRetained1.getColor(color3f);
/* 495 */     fogRetained2.initColor(color3f);
/* 496 */     fogRetained2.initInfluencingBounds(fogRetained1.getInfluencingBounds());
/*     */     
/* 498 */     Enumeration enumeration = fogRetained1.getAllScopes();
/* 499 */     while (enumeration.hasMoreElements())
/*     */     {
/* 501 */       fogRetained2.initAddScope((Group)enumeration.nextElement());
/*     */     }
/*     */ 
/*     */     
/* 505 */     fogRetained2.initInfluencingBoundingLeaf(fogRetained1.getInfluencingBoundingLeaf());
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
/* 535 */     FogRetained fogRetained = (FogRetained)this.retained;
/* 536 */     BoundingLeaf boundingLeaf = fogRetained.getInfluencingBoundingLeaf();
/*     */     
/* 538 */     if (boundingLeaf != null) {
/* 539 */       SceneGraphObject sceneGraphObject = paramNodeReferenceTable.getNewObjectReference(boundingLeaf);
/* 540 */       fogRetained.initInfluencingBoundingLeaf((BoundingLeaf)sceneGraphObject);
/*     */     } 
/*     */ 
/*     */     
/* 544 */     int i = fogRetained.numScopes();
/* 545 */     for (byte b = 0; b < i; b++)
/* 546 */       fogRetained.initScope((Group)paramNodeReferenceTable.getNewObjectReference(fogRetained.getScope(b)), b); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Fog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */