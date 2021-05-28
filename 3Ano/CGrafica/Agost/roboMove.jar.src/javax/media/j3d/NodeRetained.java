/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Vector;
/*     */ 
/*     */ abstract class NodeRetained
/*     */   extends SceneGraphObjectRetained implements NnuId {
/*     */   static final int BACKGROUND = 1;
/*     */   static final int CLIP = 2;
/*     */   static final int LINEARFOG = 3;
/*     */   static final int EXPONENTIALFOG = 4;
/*     */   static final int AMBIENTLIGHT = 5;
/*     */   static final int DIRECTIONALLIGHT = 6;
/*     */   static final int POINTLIGHT = 7;
/*     */   static final int SPOTLIGHT = 8;
/*     */   static final int LINK = 9;
/*     */   static final int MORPH = 10;
/*     */   static final int SHAPE = 11;
/*     */   static final int BACKGROUNDSOUND = 12;
/*     */   static final int POINTSOUND = 13;
/*     */   static final int CONESOUND = 14;
/*     */   static final int SOUNDSCAPE = 15;
/*     */   static final int VIEWPLATFORM = 16;
/*     */   static final int BEHAVIOR = 17;
/*     */   static final int SWITCH = 18;
/*     */   static final int BRANCHGROUP = 19;
/*     */   static final int ORDEREDGROUP = 20;
/*     */   static final int DECALGROUP = 21;
/*     */   static final int SHAREDGROUP = 22;
/*     */   static final int GROUP = 23;
/*     */   static final int TRANSFORMGROUP = 24;
/*     */   static final int BOUNDINGLEAF = 25;
/*     */   static final int MODELCLIP = 26;
/*     */   static final int ALTERNATEAPPEARANCE = 27;
/*     */   static final int ORIENTEDSHAPE3D = 28;
/*     */   static final int VIEWSPECIFICGROUP = 29;
/*     */   static final int NUMNODES = 29;
/*     */   static final int CONTAINS_VIEWPLATFORM = 1;
/*     */   VirtualUniverse universe;
/*     */   Locale locale;
/*     */   NodeRetained parent;
/*     */   String nodeId;
/*     */   int nodeType;
/*     */   int refCount;
/*     */   int childIndex;
/*     */   boolean inSharedGroup;
/*     */   boolean pickable;
/*     */   boolean collidable;
/*     */   Transform3D[][] localToVworld;
/*     */   int[][] localToVworldIndex;
/*     */   static final int LAST_LOCAL_TO_VWORLD = 0;
/*     */   static final int CURRENT_LOCAL_TO_VWORLD = 1;
/*     */   HashKey[] localToVworldKeys;
/*     */   boolean boundsAutoCompute;
/*     */   Bounds localBounds;
/*     */   Bounds apiBounds;
/*     */   protected Bounds cachedBounds;
/*     */   ArrayList branchGroupPaths;
/*     */   BackgroundRetained geometryBackground;
/*     */   GroupRetained parentTransformLink;
/*     */   GroupRetained parentSwitchLink;
/*     */   TransformGroupRetained staticTransform;
/*     */   Integer orderedId;
/*     */   int nnuId;
/*     */   
/*     */   NodeRetained() {
/*  67 */     this.universe = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.locale = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.parent = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     this.nodeId = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     this.refCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.childIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     this.inSharedGroup = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     this.pickable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.collidable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     this.localToVworld = (Transform3D[][])null;
/* 121 */     this.localToVworldIndex = (int[][])null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     this.localToVworldKeys = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     this.boundsAutoCompute = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.cachedBounds = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     this.branchGroupPaths = new ArrayList(1);
/*     */ 
/*     */     
/* 158 */     this.geometryBackground = null;
/*     */ 
/*     */     
/* 161 */     this.parentTransformLink = null;
/*     */ 
/*     */     
/* 164 */     this.parentSwitchLink = null;
/*     */ 
/*     */     
/* 167 */     this.staticTransform = null;
/*     */ 
/*     */     
/* 170 */     this.orderedId = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     this.nnuId = NnuIdManager.getId();
/*     */     
/* 179 */     this.localBounds = new BoundingBox();
/* 180 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/* 181 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 186 */   public int getId() { return this.nnuId; }
/*     */ 
/*     */   
/*     */   public int equal(NnuId paramNnuId) {
/* 190 */     int i = paramNnuId.getId();
/* 191 */     if (this.nnuId < i) {
/* 192 */       return -1;
/*     */     }
/* 194 */     if (this.nnuId > i) {
/* 195 */       return 1;
/*     */     }
/*     */     
/* 198 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 203 */   Bounds getLocalBounds(Bounds paramBounds) { return (Bounds)paramBounds.clone(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setBounds(Bounds paramBounds) {
/* 211 */     this.apiBounds = paramBounds;
/* 212 */     if (this.source.isLive()) {
/* 213 */       if (!this.boundsAutoCompute) {
/* 214 */         if (paramBounds != null) {
/* 215 */           this.localBounds = getLocalBounds(paramBounds);
/* 216 */           if (this.staticTransform != null) {
/* 217 */             this.localBounds.transform(this.staticTransform.transform);
/*     */           }
/*     */         }
/* 220 */         else if (this.localBounds != null) {
/* 221 */           this.localBounds.set((Bounds)null);
/*     */         } else {
/*     */           
/* 224 */           this.localBounds = new BoundingBox((Bounds)null);
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 229 */     else if (paramBounds != null) {
/* 230 */       this.localBounds = getLocalBounds(paramBounds);
/* 231 */       if (this.staticTransform != null) {
/* 232 */         this.localBounds.transform(this.staticTransform.transform);
/*     */       }
/*     */     }
/* 235 */     else if (this.localBounds != null) {
/* 236 */       this.localBounds.set((Bounds)null);
/*     */     } else {
/*     */       
/* 239 */       this.localBounds = new BoundingBox((Bounds)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Bounds getEffectiveBounds() {
/* 250 */     Bounds bounds = null;
/* 251 */     if (this.localBounds != null && !this.localBounds.isEmpty()) {
/* 252 */       bounds = (Bounds)this.localBounds.clone();
/* 253 */       if (this.staticTransform != null) {
/* 254 */         Transform3D transform3D = this.staticTransform.getInvTransform();
/* 255 */         bounds.transform(transform3D);
/*     */       } 
/*     */     } 
/* 258 */     return bounds;
/*     */   }
/*     */ 
/*     */   
/* 262 */   Bounds getBounds() { return this.apiBounds; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void computeCombineBounds(Bounds paramBounds) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setBoundsAutoCompute(boolean paramBoolean) {
/* 280 */     if (this.boundsAutoCompute == paramBoolean) {
/*     */       return;
/*     */     }
/*     */     
/* 284 */     this.boundsAutoCompute = paramBoolean;
/* 285 */     dirtyBoundsCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   boolean getBoundsAutoCompute() { return this.boundsAutoCompute; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 301 */   void setParent(NodeRetained paramNodeRetained) { this.parent = paramNodeRetained; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   NodeRetained getParent() { return this.parent; }
/*     */ 
/*     */ 
/*     */   
/*     */   void transformBounds(SceneGraphPath paramSceneGraphPath, Bounds paramBounds) {
/* 314 */     if (!((NodeRetained)paramSceneGraphPath.item.retained).inSharedGroup) {
/* 315 */       paramBounds.transform(getCurrentLocalToVworld());
/*     */     } else {
/* 317 */       HashKey hashKey = new HashKey("");
/* 318 */       paramSceneGraphPath.getHashKey(hashKey);
/* 319 */       paramBounds.transform(getCurrentLocalToVworld(hashKey));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeLocalToVworld(NodeRetained paramNodeRetained1, NodeRetained paramNodeRetained2, HashKey paramHashKey, Transform3D paramTransform3D) {
/* 330 */     if (paramNodeRetained2 instanceof SharedGroupRetained) {
/*     */       
/* 332 */       String str = paramHashKey.getLastNodeId();
/*     */       
/* 334 */       SharedGroupRetained sharedGroupRetained = (SharedGroupRetained)paramNodeRetained2;
/*     */ 
/*     */       
/* 337 */       for (byte b = 0; b < sharedGroupRetained.parents.size(); b++) {
/*     */         
/* 339 */         if (str.equals(((NodeRetained)sharedGroupRetained.parents.elementAt(b)).nodeId)) {
/*     */ 
/*     */ 
/*     */           
/* 343 */           computeLocalToVworld(paramNodeRetained1, (NodeRetained)sharedGroupRetained.parents.elementAt(b), paramHashKey, paramTransform3D);
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 350 */       throw new RuntimeException(J3dI18N.getString("NodeRetained4"));
/*     */     } 
/*     */ 
/*     */     
/* 354 */     NodeRetained nodeRetained = paramNodeRetained2.getParent();
/*     */     
/* 356 */     if (nodeRetained == null) {
/*     */       
/* 358 */       if (((BranchGroupRetained)paramNodeRetained2).locale != null) {
/* 359 */         paramTransform3D.setIdentity();
/*     */       } else {
/*     */         
/* 362 */         throw new RuntimeException(J3dI18N.getString("NodeRetained5"));
/*     */       } 
/*     */     } else {
/*     */       
/* 366 */       computeLocalToVworld(paramNodeRetained1, nodeRetained, paramHashKey, paramTransform3D);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (paramNodeRetained2 instanceof TransformGroupRetained && paramNodeRetained2 != paramNodeRetained1) {
/* 373 */       Transform3D transform3D = new Transform3D();
/* 374 */       ((TransformGroupRetained)paramNodeRetained2).transform.getWithLock(transform3D);
/* 375 */       paramTransform3D.mul(transform3D);
/* 376 */     } else if (paramNodeRetained2 == paramNodeRetained1 && this.staticTransform != null) {
/* 377 */       paramTransform3D.mul(this.staticTransform.transform);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void computeNonLiveLocalToVworld(Transform3D paramTransform3D, Node paramNode) {
/* 388 */     NodeRetained nodeRetained = getParent();
/*     */     
/* 390 */     if (nodeRetained == null) {
/* 391 */       paramTransform3D.setIdentity();
/*     */     } else {
/* 393 */       nodeRetained.computeNonLiveLocalToVworld(paramTransform3D, paramNode);
/*     */     } 
/* 395 */     if (this instanceof TransformGroupRetained && this.source != paramNode) {
/* 396 */       Transform3D transform3D = new Transform3D();
/* 397 */       ((TransformGroupRetained)this).getTransform(transform3D);
/* 398 */       paramTransform3D.mul(transform3D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getLocalToVworld(Transform3D paramTransform3D) {
/* 407 */     if (this.inSharedGroup) {
/* 408 */       throw new IllegalSharingException(J3dI18N.getString("NodeRetained0"));
/*     */     }
/*     */ 
/*     */     
/* 412 */     if (this.localToVworld == null) {
/* 413 */       paramTransform3D.setIdentity();
/*     */     } else {
/* 415 */       computeLocalToVworld(this, this, null, paramTransform3D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getLocalToVworld(SceneGraphPath paramSceneGraphPath, Transform3D paramTransform3D) {
/* 424 */     HashKey hashKey = new HashKey("");
/*     */     
/* 426 */     if (!this.inSharedGroup) {
/* 427 */       throw new IllegalSharingException(J3dI18N.getString("NodeRetained1"));
/*     */     }
/* 429 */     paramSceneGraphPath.validate(hashKey);
/* 430 */     computeLocalToVworld(this, this, hashKey, paramTransform3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getLocalToVworld(Transform3D paramTransform3D, HashKey paramHashKey) {
/* 438 */     HashKey hashKey = new HashKey(paramHashKey);
/* 439 */     computeLocalToVworld(this, this, hashKey, paramTransform3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Locale getLocale() {
/* 447 */     if (this.inSharedGroup) {
/* 448 */       throw new IllegalSharingException(J3dI18N.getString("NodeRetained0"));
/*     */     }
/*     */     
/* 451 */     return this.locale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Transform3D getCurrentLocalToVworld() {
/* 460 */     if (this.localToVworld != null) {
/* 461 */       return this.localToVworld[0][this.localToVworldIndex[0][1]];
/*     */     }
/* 463 */     return new Transform3D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 470 */   Transform3D getCurrentLocalToVworld(int paramInt) { return this.localToVworld[paramInt][this.localToVworldIndex[paramInt][1]]; }
/*     */ 
/*     */ 
/*     */   
/*     */   Transform3D getCurrentLocalToVworld(HashKey paramHashKey) {
/* 475 */     if (this.localToVworld != null) {
/* 476 */       if (!this.inSharedGroup) {
/* 477 */         return this.localToVworld[0][this.localToVworldIndex[0][1]];
/*     */       }
/* 479 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 480 */       if (i >= 0) {
/* 481 */         return this.localToVworld[i][this.localToVworldIndex[i][1]];
/*     */       }
/*     */     } 
/*     */     
/* 485 */     return new Transform3D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Transform3D getLastLocalToVworld() {
/* 493 */     if (this.localToVworld != null) {
/* 494 */       return this.localToVworld[0][this.localToVworldIndex[0][0]];
/*     */     }
/* 496 */     return new Transform3D();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 501 */   Transform3D getLastLocalToVworld(int paramInt) { return this.localToVworld[paramInt][this.localToVworldIndex[paramInt][0]]; }
/*     */ 
/*     */ 
/*     */   
/*     */   Transform3D getLastLocalToVworld(HashKey paramHashKey) {
/* 506 */     if (this.localToVworld != null) {
/* 507 */       if (!this.inSharedGroup) {
/* 508 */         return this.localToVworld[0][this.localToVworldIndex[0][0]];
/*     */       }
/* 510 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 511 */       if (i >= 0) {
/* 512 */         return this.localToVworld[i][this.localToVworldIndex[i][0]];
/*     */       }
/*     */     } 
/*     */     
/* 516 */     return new Transform3D();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setAuxData(SetLiveState paramSetLiveState, int paramInt1, int paramInt2) {}
/*     */ 
/*     */   
/*     */   void setNodeData(SetLiveState paramSetLiveState) {
/* 525 */     this.localToVworld = paramSetLiveState.localToVworld;
/* 526 */     this.localToVworldIndex = paramSetLiveState.localToVworldIndex;
/* 527 */     this.localToVworldKeys = paramSetLiveState.localToVworldKeys;
/*     */ 
/*     */     
/* 530 */     this.branchGroupPaths = paramSetLiveState.parentBranchGroupPaths;
/*     */     
/* 532 */     this.parentTransformLink = paramSetLiveState.parentTransformLink;
/* 533 */     this.parentSwitchLink = paramSetLiveState.parentSwitchLink;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setPickable(boolean paramBoolean) {
/* 539 */     if (this.pickable == paramBoolean) {
/*     */       return;
/*     */     }
/* 542 */     this.pickable = paramBoolean;
/*     */     
/* 544 */     if (this.source.isLive()) {
/* 545 */       synchronized (this.universe.sceneGraphLock) {
/*     */         boolean[] arrayOfBoolean;
/* 547 */         if (!this.inSharedGroup) {
/* 548 */           arrayOfBoolean = new boolean[1];
/*     */         } else {
/* 550 */           arrayOfBoolean = new boolean[this.localToVworldKeys.length];
/*     */         } 
/*     */         
/* 553 */         findPickableFlags(arrayOfBoolean);
/* 554 */         updatePickable(this.localToVworldKeys, arrayOfBoolean);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   void updatePickable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 560 */     for (byte b = 0; b < paramArrayOfBoolean.length; b++) {
/* 561 */       if (!this.pickable) {
/* 562 */         paramArrayOfBoolean[b] = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 569 */   boolean getPickable() { return this.pickable; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setCollidable(boolean paramBoolean) {
/* 575 */     if (this.collidable == paramBoolean) {
/*     */       return;
/*     */     }
/* 578 */     this.collidable = paramBoolean;
/*     */     
/* 580 */     if (this.source.isLive()) {
/* 581 */       synchronized (this.universe.sceneGraphLock) {
/*     */         boolean[] arrayOfBoolean;
/* 583 */         if (!this.inSharedGroup) {
/* 584 */           arrayOfBoolean = new boolean[1];
/*     */         } else {
/* 586 */           arrayOfBoolean = new boolean[this.localToVworldKeys.length];
/*     */         } 
/*     */         
/* 589 */         findCollidableFlags(arrayOfBoolean);
/* 590 */         updateCollidable(this.localToVworldKeys, arrayOfBoolean);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 598 */   boolean getCollidable() { return this.collidable; }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateCollidable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 603 */     for (byte b = 0; b < paramArrayOfBoolean.length; b++) {
/* 604 */       if (!this.collidable) {
/* 605 */         paramArrayOfBoolean[b] = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void notifySceneGraphChanged(boolean paramBoolean) {}
/*     */ 
/*     */   
/*     */   void recombineAbove() {}
/*     */ 
/*     */   
/*     */   void updateLocalToVworld() {}
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 621 */     int i = this.refCount;
/*     */     
/* 623 */     doSetLive(paramSetLiveState);
/* 624 */     if (i <= 0) {
/* 625 */       markAsLive();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void doSetLive(SetLiveState paramSetLiveState) {
/* 631 */     int i = this.refCount;
/*     */     
/* 633 */     this.refCount += paramSetLiveState.refCount;
/* 634 */     if (this.locale != null && this.universe != paramSetLiveState.universe)
/* 635 */       throw new IllegalSharingException(J3dI18N.getString("NodeRetained3")); 
/* 636 */     if (paramSetLiveState.locale == null) {
/* 637 */       System.err.println("NodeRetained.setLive() locale is null");
/*     */     }
/*     */     
/* 640 */     this.locale = paramSetLiveState.locale;
/* 641 */     this.inSharedGroup = paramSetLiveState.inSharedGroup;
/*     */     
/* 643 */     if (i <= 0) {
/* 644 */       if (this.listIdx == null) {
/* 645 */         this.universe = paramSetLiveState.universe;
/*     */       
/*     */       }
/* 648 */       else if (paramSetLiveState.universe != this.universe) {
/* 649 */         synchronized (this) {
/* 650 */           this.universe = paramSetLiveState.universe;
/* 651 */           incIdxUsed();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 656 */     paramSetLiveState.universe.numNodes++;
/*     */ 
/*     */     
/* 659 */     for (byte b = 0; b < paramSetLiveState.pickable.length; b++) {
/* 660 */       if (!this.pickable) {
/* 661 */         paramSetLiveState.pickable[b] = false;
/*     */       }
/* 663 */       if (!this.collidable) {
/* 664 */         paramSetLiveState.collidable[b] = false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 669 */     if (i <= 0) {
/* 670 */       super.doSetLive(paramSetLiveState);
/*     */     }
/* 672 */     if (this.inBackgroundGroup) {
/* 673 */       this.geometryBackground = paramSetLiveState.geometryBackground;
/*     */     }
/*     */     
/* 676 */     setNodeData(paramSetLiveState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 685 */     if (this.refCount <= 0) {
/* 686 */       this.localToVworld = (Transform3D[][])null;
/* 687 */       this.localToVworldIndex = (int[][])null;
/* 688 */       this.localToVworldKeys = null;
/*     */ 
/*     */       
/* 691 */       this.branchGroupPaths = new ArrayList(1);
/* 692 */       this.parentTransformLink = null;
/* 693 */       this.parentSwitchLink = null;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 698 */       this.localToVworld = paramSetLiveState.localToVworld;
/* 699 */       this.localToVworldIndex = paramSetLiveState.localToVworldIndex;
/* 700 */       this.localToVworldKeys = paramSetLiveState.localToVworldKeys;
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
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 713 */     this.refCount -= paramSetLiveState.refCount;
/*     */     
/* 715 */     if (this.refCount <= 0) {
/* 716 */       clearLive();
/*     */ 
/*     */       
/* 719 */       if (this.nodeId != null) {
/* 720 */         this.universe.nodeIdFreeList.addElement(this.nodeId);
/* 721 */         this.nodeId = null;
/*     */       } 
/*     */     } 
/*     */     
/* 725 */     this.universe.numNodes--;
/*     */ 
/*     */     
/* 728 */     removeNodeData(paramSetLiveState);
/*     */     
/* 730 */     if (this.refCount <= 0) {
/* 731 */       this.locale = null;
/* 732 */       this.geometryBackground = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void findPickableFlags(boolean[] paramArrayOfBoolean) {
/* 738 */     NodeRetained nodeRetained = this;
/*     */ 
/*     */     
/* 741 */     if (!this.inSharedGroup) {
/* 742 */       paramArrayOfBoolean[0] = true;
/* 743 */       nodeRetained = nodeRetained.parent;
/* 744 */       while (nodeRetained != null) {
/* 745 */         if (!nodeRetained.pickable) {
/* 746 */           paramArrayOfBoolean[0] = false;
/*     */           break;
/*     */         } 
/* 749 */         nodeRetained = nodeRetained.parent;
/*     */       } 
/*     */     } else {
/*     */       
/* 753 */       for (byte b = 0; b < paramArrayOfBoolean.length; b++) {
/* 754 */         nodeRetained = this;
/* 755 */         paramArrayOfBoolean[b] = true;
/* 756 */         HashKey hashKey = new HashKey(this.localToVworldKeys[b]);
/*     */         
/*     */         while (true) {
/* 759 */           if (nodeRetained instanceof SharedGroupRetained) {
/* 760 */             String str = hashKey.getLastNodeId();
/* 761 */             Vector vector = ((SharedGroupRetained)nodeRetained).parents;
/* 762 */             int i = vector.size();
/* 763 */             NodeRetained nodeRetained1 = nodeRetained;
/* 764 */             for (byte b1 = 0; b1 < i; b1++) {
/* 765 */               NodeRetained nodeRetained2 = (NodeRetained)vector.elementAt(b1);
/* 766 */               if (nodeRetained2.nodeId.equals(str)) {
/* 767 */                 nodeRetained = nodeRetained2;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 771 */             if (nodeRetained1 == nodeRetained) {
/*     */               return;
/*     */             }
/*     */           } else {
/*     */             
/* 776 */             nodeRetained = nodeRetained.parent;
/*     */           } 
/* 778 */           if (nodeRetained == null)
/*     */             break; 
/* 780 */           if (!nodeRetained.pickable) {
/* 781 */             paramArrayOfBoolean[b] = false;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void findCollidableFlags(boolean[] paramArrayOfBoolean) {
/* 792 */     NodeRetained nodeRetained = this;
/*     */     
/* 794 */     if (!this.inSharedGroup) {
/* 795 */       paramArrayOfBoolean[0] = true;
/* 796 */       nodeRetained = nodeRetained.parent;
/* 797 */       while (nodeRetained != null) {
/* 798 */         if (!nodeRetained.collidable) {
/* 799 */           paramArrayOfBoolean[0] = false;
/*     */           break;
/*     */         } 
/* 802 */         nodeRetained = nodeRetained.parent;
/*     */       } 
/*     */     } else {
/*     */       
/* 806 */       for (byte b = 0; b < paramArrayOfBoolean.length; b++) {
/* 807 */         nodeRetained = this;
/* 808 */         paramArrayOfBoolean[b] = true;
/* 809 */         HashKey hashKey = new HashKey(this.localToVworldKeys[b]);
/*     */         
/*     */         while (true) {
/* 812 */           if (nodeRetained instanceof SharedGroupRetained) {
/* 813 */             String str = hashKey.getLastNodeId();
/* 814 */             Vector vector = ((SharedGroupRetained)nodeRetained).parents;
/* 815 */             int i = vector.size();
/* 816 */             NodeRetained nodeRetained1 = nodeRetained;
/* 817 */             for (byte b1 = 0; b1 < i; b1++) {
/* 818 */               NodeRetained nodeRetained2 = (NodeRetained)vector.elementAt(b1);
/* 819 */               if (nodeRetained2.nodeId.equals(str)) {
/* 820 */                 nodeRetained = nodeRetained2;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 824 */             if (nodeRetained == nodeRetained1) {
/*     */               return;
/*     */             }
/*     */           } else {
/* 828 */             nodeRetained = nodeRetained.parent;
/*     */           } 
/* 830 */           if (nodeRetained == null)
/*     */             break; 
/* 832 */           if (!nodeRetained.collidable) {
/* 833 */             paramArrayOfBoolean[b] = false;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void findTransformLevels(int[] paramArrayOfInt) {
/* 842 */     NodeRetained nodeRetained = this;
/*     */ 
/*     */     
/* 845 */     if (!this.inSharedGroup) {
/* 846 */       paramArrayOfInt[0] = -1;
/* 847 */       while (nodeRetained != null) {
/* 848 */         if (nodeRetained.nodeType == 24) {
/* 849 */           TransformGroupRetained transformGroupRetained = (TransformGroupRetained)nodeRetained;
/* 850 */           paramArrayOfInt[0] = transformGroupRetained.transformLevels[0];
/*     */           break;
/*     */         } 
/* 853 */         nodeRetained = nodeRetained.parent;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 858 */       for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 859 */         nodeRetained = this;
/* 860 */         paramArrayOfInt[b] = -1;
/* 861 */         HashKey hashKey = new HashKey(this.localToVworldKeys[b]);
/*     */ 
/*     */         
/* 864 */         while (nodeRetained != null) {
/*     */           
/* 866 */           if (nodeRetained instanceof SharedGroupRetained) {
/*     */             
/* 868 */             String str = hashKey.getLastNodeId();
/* 869 */             Vector vector = ((SharedGroupRetained)nodeRetained).parents;
/* 870 */             int i = vector.size();
/* 871 */             NodeRetained nodeRetained1 = nodeRetained;
/* 872 */             for (byte b1 = 0; b1 < i; b1++) {
/* 873 */               NodeRetained nodeRetained2 = (NodeRetained)vector.elementAt(b1);
/*     */               
/* 875 */               if (nodeRetained2.nodeId.equals(str)) {
/* 876 */                 nodeRetained = nodeRetained2;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 880 */             if (nodeRetained1 == nodeRetained)
/*     */             {
/*     */               return;
/*     */             }
/*     */           }
/* 885 */           else if (nodeRetained.nodeType == 24) {
/* 886 */             TransformGroupRetained transformGroupRetained = (TransformGroupRetained)nodeRetained;
/* 887 */             if (transformGroupRetained.inSharedGroup) {
/*     */               
/* 889 */               int i = hashKey.equals(transformGroupRetained.localToVworldKeys, 0, transformGroupRetained.localToVworldKeys.length);
/*     */ 
/*     */               
/* 892 */               paramArrayOfInt[b] = transformGroupRetained.transformLevels[i]; break;
/*     */             } 
/* 894 */             paramArrayOfInt[b] = transformGroupRetained.transformLevels[0];
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 899 */           nodeRetained = nodeRetained.parent;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isStatic() {
/* 907 */     if (this.source.getCapability(11) || this.source.getCapability(46) || this.source.getCapability(1) || this.source.getCapability(0) || this.source.getCapability(3) || this.source.getCapability(4) || this.source.getCapability(5) || this.source.getCapability(6) || this.source.getCapability(7) || this.source.getCapability(8) || this.source.getCapability(9) || this.source.getCapability(10))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 919 */       return false;
/*     */     }
/* 921 */     return true;
/*     */   }
/*     */   
/*     */   void merge(CompileState paramCompileState) {
/* 925 */     this.staticTransform = paramCompileState.staticTransform;
/* 926 */     if (paramCompileState.parentGroup != null) {
/* 927 */       paramCompileState.parentGroup.compiledChildrenList.add(this);
/*     */     }
/* 929 */     this.parent = paramCompileState.parentGroup;
/* 930 */     if (this.staticTransform != null) {
/* 931 */       mergeTransform(this.staticTransform);
/*     */     }
/*     */   }
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 936 */     if (this.localBounds != null) {
/* 937 */       this.localBounds.transform(paramTransformGroupRetained.transform);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 942 */   int[] processViewSpecificInfo(int paramInt, HashKey paramHashKey, View paramView, ArrayList paramArrayList1, int[] paramArrayOfInt, ArrayList paramArrayList2) { return paramArrayOfInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 947 */   VirtualUniverse getVirtualUniverse() { return this.universe; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void searchGeometryAtoms(UnorderList paramUnorderList) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dirtyBoundsCache() {
/* 959 */     if (VirtualUniverse.mc.cacheAutoComputedBounds) {
/* 960 */       this.cachedBounds = null;
/* 961 */       if (this.parent != null)
/* 962 */         this.parent.dirtyBoundsCache(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\NodeRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */