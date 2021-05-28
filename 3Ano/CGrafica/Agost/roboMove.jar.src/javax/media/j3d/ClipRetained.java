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
/*     */ class ClipRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int BOUNDS_CHANGED = 1;
/*     */   static final int BOUNDINGLEAF_CHANGED = 2;
/*     */   static final int BACKDISTANCE_CHANGED = 4;
/*     */   double backDistance;
/*     */   double backDistanceInVworld;
/*     */   Bounds applicationRegion;
/*     */   BoundingLeafRetained boundingLeaf;
/*     */   Bounds transformedRegion;
/*     */   boolean inImmCtx;
/*     */   static final int targetThreads = 4224;
/*     */   boolean isViewScoped;
/*     */   
/*     */   ClipRetained() {
/*  35 */     this.backDistance = 100.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     this.applicationRegion = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     this.boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     this.transformedRegion = null;
/*     */ 
/*     */     
/*  58 */     this.inImmCtx = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.isViewScoped = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     this.nodeType = 2;
/*  76 */     this.localBounds = new BoundingBox();
/*  77 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  78 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   final void initBackDistance(double paramDouble) { this.backDistance = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setBackDistance(double paramDouble) {
/*  95 */     this.backDistance = paramDouble;
/*  96 */     sendMessage(4, new Double(paramDouble), null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   final double getBackDistance() { return this.backDistance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initApplicationBounds(Bounds paramBounds) {
/* 113 */     if (paramBounds != null) {
/* 114 */       this.applicationRegion = (Bounds)paramBounds.clone();
/*     */     } else {
/* 116 */       this.applicationRegion = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setApplicationBounds(Bounds paramBounds) {
/* 125 */     initApplicationBounds(paramBounds);
/*     */     
/* 127 */     if (this.boundingLeaf == null) {
/* 128 */       sendMessage(1, (paramBounds != null) ? paramBounds.clone() : null, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   final Bounds getApplicationBounds() { return (this.applicationRegion != null) ? (Bounds)this.applicationRegion.clone() : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initApplicationBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 147 */     if (paramBoundingLeaf != null) {
/* 148 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*     */     } else {
/* 150 */       this.boundingLeaf = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setApplicationBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 158 */     if (this.boundingLeaf != null) {
/* 159 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this);
/*     */     }
/* 161 */     if (paramBoundingLeaf != null) {
/* 162 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/* 163 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this);
/*     */     } else {
/* 165 */       this.boundingLeaf = null;
/*     */     } 
/* 167 */     sendMessage(2, (this.boundingLeaf != null) ? this.boundingLeaf.mirrorBoundingLeaf : null, (this.applicationRegion != null) ? this.applicationRegion.clone() : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   BoundingLeaf getApplicationBoundingLeaf() { return (this.boundingLeaf != null) ? (BoundingLeaf)this.boundingLeaf.source : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   boolean getInImmCtx() { return this.inImmCtx; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 200 */     if (this.inImmCtx) {
/* 201 */       throw new IllegalSharingException(J3dI18N.getString("ClipRetained0"));
/*     */     }
/*     */     
/* 204 */     doSetLive(paramSetLiveState);
/*     */     
/* 206 */     if (this.inBackgroundGroup) {
/* 207 */       throw new IllegalSceneGraphException(J3dI18N.getString("ClipRetained1"));
/*     */     }
/*     */ 
/*     */     
/* 211 */     if (this.inSharedGroup) {
/* 212 */       throw new IllegalSharingException(J3dI18N.getString("ClipRetained2"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 217 */     initMirrorObject();
/* 218 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 219 */       paramSetLiveState.viewScopedNodeList.add(this);
/* 220 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 222 */       paramSetLiveState.nodeList.add(this);
/*     */     } 
/*     */     
/* 225 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 227 */       paramSetLiveState.switchTargets[0].addNode(this, 1);
/*     */     }
/* 229 */     this.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*     */ 
/*     */     
/* 232 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 233 */       paramSetLiveState.transformTargets[0].addNode(this, 1);
/* 234 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */     
/* 237 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */ 
/*     */     
/* 240 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 248 */     super.clearLive(paramSetLiveState);
/* 249 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 250 */       paramSetLiveState.viewScopedNodeList.add(this);
/* 251 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 253 */       paramSetLiveState.nodeList.add(this);
/*     */     } 
/* 255 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 256 */       paramSetLiveState.transformTargets[0].addNode(this, 1);
/* 257 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */ 
/*     */     
/* 261 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */     
/* 263 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 265 */       paramSetLiveState.switchTargets[0].addNode(this, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   void initMirrorObject() {
/* 270 */     Transform3D transform3D = getLastLocalToVworld();
/*     */     
/* 272 */     if (this.boundingLeaf != null) {
/* 273 */       this.transformedRegion = this.boundingLeaf.mirrorBoundingLeaf.transformedRegion;
/*     */     
/*     */     }
/* 276 */     else if (this.applicationRegion != null) {
/* 277 */       this.transformedRegion = (Bounds)this.applicationRegion.clone();
/* 278 */       this.transformedRegion.transform(this.applicationRegion, transform3D);
/*     */     } else {
/*     */       
/* 281 */       this.transformedRegion = null;
/*     */     } 
/*     */ 
/*     */     
/* 285 */     this.backDistanceInVworld = this.backDistance * transform3D.getDistanceScale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/* 292 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*     */     
/* 294 */     Transform3D transform3D = getCurrentLocalToVworld();
/*     */ 
/*     */     
/* 297 */     if ((i & true) != 0) {
/* 298 */       if (paramArrayOfObject[2] != null) {
/* 299 */         this.transformedRegion = ((Bounds)paramArrayOfObject[2]).copy(this.transformedRegion);
/* 300 */         this.transformedRegion.transform(this.transformedRegion, transform3D);
/*     */       }
/*     */       else {
/*     */         
/* 304 */         this.transformedRegion = null;
/*     */       }
/*     */     
/* 307 */     } else if ((i & 0x2) != 0) {
/* 308 */       if (paramArrayOfObject[2] != null) {
/* 309 */         this.transformedRegion = ((BoundingLeafRetained)paramArrayOfObject[2]).transformedRegion;
/*     */       } else {
/*     */         
/* 312 */         Bounds bounds = (Bounds)paramArrayOfObject[3];
/* 313 */         if (bounds != null) {
/* 314 */           this.transformedRegion = bounds.copy(this.transformedRegion);
/* 315 */           this.transformedRegion.transform(bounds, transform3D);
/*     */         }
/*     */         else {
/*     */           
/* 319 */           this.transformedRegion = null;
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 325 */     else if ((i & 0x4) != 0) {
/* 326 */       this.backDistanceInVworld = ((Double)paramArrayOfObject[2]).doubleValue() * transform3D.getDistanceScale();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateBoundingLeaf() {
/* 337 */     if (this.boundingLeaf != null && this.boundingLeaf.mirrorBoundingLeaf.switchState.currentSwitchOn) {
/*     */       
/* 339 */       this.transformedRegion = this.boundingLeaf.mirrorBoundingLeaf.transformedRegion;
/*     */     
/*     */     }
/* 342 */     else if (this.applicationRegion != null) {
/* 343 */       this.transformedRegion = this.applicationRegion.copy(this.transformedRegion);
/* 344 */       this.transformedRegion.transform(this.applicationRegion, getCurrentLocalToVworld());
/*     */     } else {
/*     */       
/* 347 */       this.transformedRegion = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateTransformChange() {
/* 354 */     if (this.boundingLeaf == null && 
/* 355 */       this.applicationRegion != null) {
/* 356 */       this.transformedRegion = (Bounds)this.applicationRegion.clone();
/* 357 */       this.transformedRegion.transform(this.applicationRegion, getCurrentLocalToVworld());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject1, Object paramObject2) {
/* 364 */     J3dMessage j3dMessage = new J3dMessage();
/* 365 */     j3dMessage.threads = 4224;
/* 366 */     j3dMessage.type = 21;
/* 367 */     j3dMessage.universe = this.universe;
/* 368 */     j3dMessage.args[0] = this;
/* 369 */     j3dMessage.args[1] = new Integer(paramInt);
/* 370 */     j3dMessage.args[2] = paramObject1;
/* 371 */     j3dMessage.args[3] = paramObject2;
/* 372 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 376 */     super.mergeTransform(paramTransformGroupRetained);
/* 377 */     if (this.applicationRegion != null) {
/* 378 */       this.applicationRegion.transform(paramTransformGroupRetained.transform);
/*     */     }
/*     */   }
/*     */   
/* 382 */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) { paramArrayList.add(this); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ClipRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */