/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BehaviorRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int WAKEUP_ACTIVATE_INDEX = 0;
/*     */   static final int WAKEUP_DEACTIVATE_INDEX = 1;
/*     */   static final int WAKEUP_VP_ENTRY_INDEX = 2;
/*     */   static final int WAKEUP_VP_EXIT_INDEX = 3;
/*     */   static final int WAKEUP_TIME_INDEX = 4;
/*     */   static final int NUM_WAKEUPS = 5;
/*     */   static final int WAKEUP_ACTIVATE = 1;
/*     */   static final int WAKEUP_DEACTIVATE = 2;
/*     */   static final int WAKEUP_VP_ENTRY = 4;
/*     */   static final int WAKEUP_VP_EXIT = 8;
/*     */   static final int WAKEUP_TIME = 16;
/*     */   static final int NUM_SCHEDULING_INTERVALS = 10;
/*     */   static final int BEHAIVORS_IN_BS_LIST = 0;
/*     */   static final int SCHEDULE_IN_BS_LIST = 1;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*     */   Bounds schedulingRegion;
/*     */   BoundingLeafRetained boundingLeaf;
/*     */   WakeupCondition wakeupCondition;
/*     */   WakeupCondition newWakeupCondition;
/*     */   ViewPlatformRetained vp;
/*     */   boolean active;
/*     */   boolean enable;
/*     */   int schedulingInterval;
/*     */   boolean conditionSet;
/*     */   boolean inCallback;
/*     */   boolean inInitCallback;
/*     */   Bounds transformedRegion;
/*     */   int isDirty;
/*     */   int wakeupMask;
/*     */   int[] wakeupArray;
/*     */   Object[] targets;
/*     */   
/*     */   BehaviorRetained() {
/*  55 */     this.schedulingRegion = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     this.boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     this.wakeupCondition = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     this.newWakeupCondition = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.vp = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     this.active = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.enable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     this.schedulingInterval = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.conditionSet = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     this.inCallback = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     this.inInitCallback = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     this.transformedRegion = null;
/*     */ 
/*     */     
/* 125 */     this.isDirty = 65535;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.wakeupMask = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.wakeupArray = new int[5];
/*     */ 
/*     */     
/* 138 */     this.targets = new Object[1];
/*     */ 
/*     */     
/* 141 */     this.nodeType = 17;
/* 142 */     this.localBounds = new BoundingBox();
/* 143 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/* 144 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/* 145 */     this.targets[0] = this;
/* 146 */     IndexedUnorderSet.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Bounds getSchedulingBounds() {
/* 154 */     Bounds bounds = null;
/*     */     
/* 156 */     if (this.schedulingRegion != null) {
/* 157 */       bounds = (Bounds)this.schedulingRegion.clone();
/* 158 */       if (this.staticTransform != null) {
/* 159 */         Transform3D transform3D = this.staticTransform.getInvTransform();
/* 160 */         bounds.transform(transform3D);
/*     */       } 
/*     */     } 
/* 163 */     return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSchedulingBounds(Bounds paramBounds) {
/* 173 */     if (paramBounds != null) {
/* 174 */       this.schedulingRegion = (Bounds)paramBounds.clone();
/* 175 */       if (this.staticTransform != null) {
/* 176 */         this.schedulingRegion.transform(this.staticTransform.transform);
/*     */       }
/*     */     } else {
/* 179 */       this.schedulingRegion = null;
/*     */     } 
/*     */     
/* 182 */     if (this.source != null && this.source.isLive()) {
/* 183 */       sendMessage(35);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSchedulingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 192 */     if (this.source != null && this.source.isLive() && 
/* 193 */       this.boundingLeaf != null) {
/* 194 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this);
/*     */     }
/*     */     
/* 197 */     if (paramBoundingLeaf != null) {
/* 198 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*     */     } else {
/* 200 */       this.boundingLeaf = null;
/*     */     } 
/*     */     
/* 203 */     if (this.source != null && this.source.isLive()) {
/* 204 */       if (this.boundingLeaf != null)
/* 205 */         this.boundingLeaf.mirrorBoundingLeaf.addUser(this); 
/* 206 */       sendMessage(35);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setEnable(boolean paramBoolean) {
/* 215 */     if (this.enable != paramBoolean) {
/* 216 */       this.enable = paramBoolean;
/* 217 */       if (this.source != null && this.source.isLive()) {
/* 218 */         sendMessage(paramBoolean ? 29 : 30);
/*     */       }
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
/* 230 */   boolean getEnable() { return this.enable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSchedulingInterval(int paramInt) {
/* 241 */     if (this.source != null && this.source.isLive() && !this.inCallback) {
/*     */ 
/*     */ 
/*     */       
/* 245 */       sendMessage(55, new Integer(paramInt));
/*     */     }
/*     */     else {
/*     */       
/* 249 */       this.schedulingInterval = paramInt;
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
/* 261 */   int getSchedulingInterval() { return this.schedulingInterval; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   BoundingLeaf getSchedulingBoundingLeaf() { return (this.boundingLeaf != null) ? (BoundingLeaf)this.boundingLeaf.source : null; }
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
/* 280 */     doSetLive(paramSetLiveState);
/* 281 */     if (this.inBackgroundGroup) {
/* 282 */       throw new IllegalSceneGraphException(J3dI18N.getString("BehaviorRetained0"));
/*     */     }
/*     */     
/* 285 */     if (this.inSharedGroup) {
/* 286 */       throw new IllegalSharingException(J3dI18N.getString("BehaviorRetained1"));
/*     */     }
/*     */ 
/*     */     
/* 290 */     paramSetLiveState.nodeList.add(this);
/* 291 */     paramSetLiveState.behaviorNodes.add(this);
/* 292 */     paramSetLiveState.notifyThreads |= 0x100;
/*     */     
/* 294 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 296 */       paramSetLiveState.switchTargets[0].addNode(this, 2);
/*     */     }
/* 298 */     this.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*     */     
/* 300 */     if (this.boundingLeaf != null) {
/* 301 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this);
/*     */     }
/* 303 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 304 */       paramSetLiveState.transformTargets[0].addNode(this, 2);
/* 305 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/* 307 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 316 */     super.clearLive(paramSetLiveState);
/* 317 */     paramSetLiveState.nodeList.add(this);
/* 318 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 319 */       paramSetLiveState.transformTargets[0].addNode(this, 2);
/* 320 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/* 322 */     paramSetLiveState.notifyThreads |= 0x100;
/* 323 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 325 */       paramSetLiveState.switchTargets[0].addNode(this, 2);
/*     */     }
/* 327 */     if (this.boundingLeaf != null) {
/* 328 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void executeInitialize() {
/* 339 */     synchronized (this) {
/* 340 */       boolean bool1 = this.inCallback;
/* 341 */       boolean bool2 = this.inInitCallback;
/*     */       
/* 343 */       this.inCallback = true;
/* 344 */       this.inInitCallback = true;
/*     */       try {
/* 346 */         ((Behavior)this.source).initialize();
/*     */       }
/* 348 */       catch (RuntimeException runtimeException) {
/* 349 */         System.err.println("Exception occurred during Behavior initialization:");
/* 350 */         runtimeException.printStackTrace();
/*     */       }
/* 352 */       catch (Error error) {
/*     */         
/* 354 */         System.err.println("Error occurred during Behavior initialization:");
/* 355 */         error.printStackTrace();
/*     */       } 
/* 357 */       this.inCallback = bool1;
/* 358 */       this.inInitCallback = bool2;
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
/*     */   void wakeupOn(WakeupCondition paramWakeupCondition) {
/* 371 */     if (paramWakeupCondition == null) {
/* 372 */       throw new NullPointerException(J3dI18N.getString("BehaviorRetained2"));
/*     */     }
/*     */     
/* 375 */     if (!this.inInitCallback) {
/* 376 */       this.conditionSet = true;
/* 377 */       this.wakeupCondition = paramWakeupCondition;
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 385 */       this.newWakeupCondition = paramWakeupCondition;
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
/*     */   void handleLastWakeupOn(WakeupCondition paramWakeupCondition, BehaviorStructure paramBehaviorStructure) {
/* 398 */     if (paramBehaviorStructure == this.universe.behaviorStructure) {
/* 399 */       if (this.wakeupCondition == paramWakeupCondition) {
/*     */         
/* 401 */         this.wakeupCondition.resetTree();
/*     */       } else {
/* 403 */         if (paramWakeupCondition != null) {
/* 404 */           paramWakeupCondition.cleanTree(paramBehaviorStructure);
/*     */         }
/* 406 */         this.wakeupCondition.buildTree(null, 0, this);
/*     */       } 
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
/* 420 */   WakeupCondition getWakeupCondition() { return this.wakeupCondition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void postId(int paramInt) {
/* 430 */     if (this.source != null && this.source.isLive()) {
/* 431 */       this.universe.behaviorStructure.handleBehaviorPost((Behavior)this.source, paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 436 */   protected View getView() { return (this.universe != null) ? this.universe.getCurrentView() : null; }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformRegion(Bounds paramBounds) {
/* 441 */     if (this.boundingLeaf == null) {
/* 442 */       updateTransformRegion();
/*     */     }
/* 444 */     else if (paramBounds == null) {
/* 445 */       this.transformedRegion = null;
/*     */     } else {
/* 447 */       this.transformedRegion = (Bounds)paramBounds.clone();
/* 448 */       this.transformedRegion.transform(this.boundingLeaf.mirrorBoundingLeaf.getCurrentLocalToVworld());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformRegion() {
/* 455 */     if (this.boundingLeaf == null || !this.boundingLeaf.mirrorBoundingLeaf.switchState.currentSwitchOn) {
/*     */       
/* 457 */       if (this.schedulingRegion == null) {
/* 458 */         this.transformedRegion = null;
/*     */       } else {
/*     */         
/* 461 */         if (this.transformedRegion != null) {
/* 462 */           this.transformedRegion.set(this.schedulingRegion);
/*     */         } else {
/* 464 */           this.transformedRegion = (Bounds)this.schedulingRegion.clone();
/*     */         } 
/* 466 */         this.transformedRegion.transform(getCurrentLocalToVworld());
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 471 */       this.transformedRegion = this.boundingLeaf.mirrorBoundingLeaf.transformedRegion;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   void updateBoundingLeaf(long paramLong) { this.transformedRegion = this.boundingLeaf.mirrorBoundingLeaf.transformedRegion; }
/*     */ 
/*     */   
/*     */   void addWakeupCondition() {}
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 488 */     J3dMessage j3dMessage = new J3dMessage();
/* 489 */     j3dMessage.threads = 256;
/* 490 */     j3dMessage.type = paramInt;
/* 491 */     j3dMessage.universe = this.universe;
/* 492 */     j3dMessage.args[0] = this.targets;
/* 493 */     j3dMessage.args[1] = this;
/* 494 */     j3dMessage.args[2] = paramObject;
/* 495 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */   
/* 499 */   final void sendMessage(int paramInt) { sendMessage(paramInt, null); }
/*     */ 
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 503 */     super.mergeTransform(paramTransformGroupRetained);
/* 504 */     if (this.schedulingRegion != null) {
/* 505 */       this.schedulingRegion.transform(paramTransformGroupRetained.transform);
/*     */     }
/* 507 */     if (this.source instanceof DistanceLOD)
/* 508 */       ((DistanceLOD)this.source).mergeTransform(paramTransformGroupRetained); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\BehaviorRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */