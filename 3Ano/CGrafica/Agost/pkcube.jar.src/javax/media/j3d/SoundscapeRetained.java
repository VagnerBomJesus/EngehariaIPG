/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SoundscapeRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int ATTRIBUTES_CHANGED = 1;
/*     */   static final int BOUNDING_LEAF_CHANGED = 2;
/*     */   static final int APPLICATION_BOUNDS_CHANGED = 4;
/*     */   Bounds applicationRegion;
/*     */   BoundingLeafRetained boundingLeaf;
/*     */   Bounds transformedRegion;
/*     */   AuralAttributesRetained attributes;
/*     */   int isDirty;
/*     */   int targetThreads;
/*     */   boolean isViewScoped;
/*     */   
/*     */   void dispatchMessage(int paramInt, Object paramObject) {
/*  59 */     J3dMessage j3dMessage = new J3dMessage();
/*  60 */     j3dMessage.threads = this.targetThreads;
/*  61 */     j3dMessage.type = 40;
/*  62 */     j3dMessage.universe = this.universe;
/*  63 */     j3dMessage.args[0] = this;
/*  64 */     j3dMessage.args[1] = new Integer(paramInt);
/*  65 */     j3dMessage.args[2] = new Integer(0);
/*  66 */     j3dMessage.args[3] = null;
/*  67 */     j3dMessage.args[4] = paramObject;
/*  68 */     VirtualUniverse.mc.processMessage(j3dMessage); } SoundscapeRetained() { this.applicationRegion = null; this.boundingLeaf = null;
/*     */     this.transformedRegion = null;
/*     */     this.attributes = null;
/*     */     this.isDirty = 65535;
/*     */     this.targetThreads = 514;
/*     */     this.isViewScoped = false;
/*  74 */     this.nodeType = 15;
/*  75 */     this.localBounds = new BoundingBox();
/*  76 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  77 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setApplicationBounds(Bounds paramBounds) {
/*  87 */     if (paramBounds != null) {
/*  88 */       this.applicationRegion = (Bounds)paramBounds.clone();
/*  89 */       if (this.staticTransform != null) {
/*  90 */         this.applicationRegion.transform(this.staticTransform.transform);
/*     */       }
/*     */     } else {
/*     */       
/*  94 */       this.applicationRegion = null;
/*     */     } 
/*  96 */     updateTransformChange();
/*  97 */     this.isDirty |= 0x4;
/*  98 */     dispatchMessage(4, paramBounds);
/*     */     
/* 100 */     if (this.source != null && this.source.isLive()) {
/* 101 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Bounds getApplicationBounds() {
/* 111 */     Bounds bounds = null;
/*     */     
/* 113 */     if (this.applicationRegion == null) {
/* 114 */       return (Bounds)null;
/*     */     }
/* 116 */     bounds = (Bounds)this.applicationRegion.clone();
/* 117 */     if (this.staticTransform != null) {
/* 118 */       Transform3D transform3D = this.staticTransform.getInvTransform();
/* 119 */       bounds.transform(transform3D);
/*     */     } 
/* 121 */     return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setApplicationBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 129 */     if (this.boundingLeaf != null)
/*     */     {
/* 131 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this);
/*     */     }
/* 133 */     if (paramBoundingLeaf != null) {
/* 134 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/* 135 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this);
/*     */     } else {
/* 137 */       this.boundingLeaf = null;
/*     */     } 
/* 139 */     updateTransformChange();
/* 140 */     this.isDirty |= 0x2;
/* 141 */     dispatchMessage(2, paramBoundingLeaf);
/*     */     
/* 143 */     if (this.source != null && this.source.isLive()) {
/* 144 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BoundingLeaf getApplicationBoundingLeaf() {
/* 152 */     if (this.boundingLeaf != null) {
/* 153 */       return (BoundingLeaf)this.boundingLeaf.source;
/*     */     }
/* 155 */     return (BoundingLeaf)null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAuralAttributes(AuralAttributes paramAuralAttributes) {
/* 165 */     if (this.source.isLive()) {
/* 166 */       if (this.attributes != null) {
/* 167 */         this.attributes.clearLive(this.refCount);
/*     */       }
/*     */       
/* 170 */       if (paramAuralAttributes != null) {
/* 171 */         ((AuralAttributesRetained)paramAuralAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*     */       }
/*     */     } 
/*     */     
/* 175 */     if (this.attributes != null) {
/* 176 */       this.attributes.removeUser(this);
/*     */     }
/*     */     
/* 179 */     if (paramAuralAttributes != null) {
/* 180 */       this.attributes = (AuralAttributesRetained)paramAuralAttributes.retained;
/* 181 */       this.attributes.addUser(this);
/*     */     } else {
/* 183 */       this.attributes = null;
/*     */     } 
/*     */ 
/*     */     
/* 187 */     this.isDirty |= 0x1;
/* 188 */     dispatchMessage(1, paramAuralAttributes);
/* 189 */     if (this.source != null && this.source.isLive()) {
/* 190 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AuralAttributes getAuralAttributes() {
/* 200 */     if (this.attributes != null) {
/* 201 */       return (AuralAttributes)this.attributes.source;
/*     */     }
/*     */     
/* 204 */     return (AuralAttributes)null;
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
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 226 */     Object object = null;
/* 227 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 228 */     if ((i & 0x2) != 0) {
/* 229 */       if (this.boundingLeaf != null) {
/* 230 */         this.transformedRegion = this.boundingLeaf.transformedRegion;
/*     */       
/*     */       }
/* 233 */       else if (this.applicationRegion != null) {
/* 234 */         this.transformedRegion = (Bounds)this.applicationRegion.clone();
/* 235 */         this.transformedRegion.transform(this.applicationRegion, getLastLocalToVworld());
/*     */       }
/*     */       else {
/*     */         
/* 239 */         this.transformedRegion = null;
/*     */       }
/*     */     
/*     */     }
/* 243 */     else if ((i & 0x4) != 0) {
/*     */       
/* 245 */       if (this.boundingLeaf == null) {
/* 246 */         this.transformedRegion = (Bounds)this.applicationRegion.clone();
/* 247 */         this.transformedRegion.transform(this.applicationRegion, getLastLocalToVworld());
/*     */       }
/*     */       else {
/*     */         
/* 251 */         this.transformedRegion = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void updateTransformChange() {
/* 258 */     if (this.boundingLeaf != null) {
/* 259 */       this.transformedRegion = this.boundingLeaf.transformedRegion;
/*     */     
/*     */     }
/* 262 */     else if (this.applicationRegion != null) {
/* 263 */       this.transformedRegion = this.applicationRegion.copy(this.transformedRegion);
/* 264 */       this.transformedRegion.transform(this.applicationRegion, getLastLocalToVworld());
/*     */     }
/*     */     else {
/*     */       
/* 268 */       this.transformedRegion = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateBoundingLeaf(long paramLong) {
/* 276 */     if (this.boundingLeaf != null && this.boundingLeaf.switchState.currentSwitchOn) {
/* 277 */       this.transformedRegion = this.boundingLeaf.transformedRegion;
/*     */     }
/* 279 */     else if (this.applicationRegion != null) {
/* 280 */       this.transformedRegion = this.applicationRegion.copy(this.transformedRegion);
/* 281 */       this.transformedRegion.transform(this.applicationRegion, getLastLocalToVworld());
/*     */     } else {
/*     */       
/* 284 */       this.transformedRegion = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 344 */     doSetLive(paramSetLiveState);
/*     */     
/* 346 */     if (this.attributes != null) {
/* 347 */       this.attributes.setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/*     */     }
/* 349 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 350 */       paramSetLiveState.transformTargets[0].addNode(this, 3);
/* 351 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */ 
/*     */     
/* 355 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 356 */       paramSetLiveState.viewScopedNodeList.add(this);
/* 357 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 359 */       paramSetLiveState.nodeList.add(this);
/*     */     } 
/*     */     
/* 362 */     if (this.inBackgroundGroup) {
/* 363 */       throw new IllegalSceneGraphException(J3dI18N.getString("SoundscapeRetained1"));
/*     */     }
/*     */ 
/*     */     
/* 367 */     if (this.inSharedGroup) {
/* 368 */       throw new IllegalSharingException(J3dI18N.getString("SoundscapeRetained0"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 373 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 375 */       paramSetLiveState.switchTargets[0].addNode(this, 3);
/*     */     }
/* 377 */     this.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/* 378 */     paramSetLiveState.notifyThreads |= 0x202;
/*     */ 
/*     */     
/* 381 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 389 */     super.clearLive(paramSetLiveState);
/* 390 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 392 */       paramSetLiveState.switchTargets[0].addNode(this, 3);
/*     */     }
/*     */     
/* 395 */     if (this.attributes != null) {
/* 396 */       this.attributes.clearLive(paramSetLiveState.refCount);
/*     */     }
/* 398 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 399 */       paramSetLiveState.transformTargets[0].addNode(this, 3);
/* 400 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */ 
/*     */     
/* 404 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 405 */       paramSetLiveState.viewScopedNodeList.add(this);
/* 406 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 408 */       paramSetLiveState.nodeList.add(this);
/*     */     } 
/* 410 */     paramSetLiveState.notifyThreads |= 0x202;
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
/*     */   void update(SoundscapeRetained paramSoundscapeRetained) {
/* 426 */     this.applicationRegion = (Bounds)paramSoundscapeRetained.applicationRegion.clone();
/* 427 */     this.attributes = paramSoundscapeRetained.attributes;
/*     */   }
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 431 */     super.mergeTransform(paramTransformGroupRetained);
/* 432 */     if (this.applicationRegion != null) {
/* 433 */       this.applicationRegion.transform(paramTransformGroupRetained.transform);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 438 */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) { paramArrayList.add(this); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\SoundscapeRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */