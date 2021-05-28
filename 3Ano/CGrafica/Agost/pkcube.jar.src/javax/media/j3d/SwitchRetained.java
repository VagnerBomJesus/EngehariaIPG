/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SwitchRetained
/*     */   extends GroupRetained
/*     */   implements TargetsInterface
/*     */ {
/*     */   static final int GEO_NODES = 1;
/*     */   static final int ENV_NODES = 2;
/*     */   static final int BEHAVIOR_NODES = 4;
/*     */   static final int SOUND_NODES = 8;
/*     */   static final int BOUNDINGLEAF_NODES = 16;
/*  33 */   int whichChild = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   BitSet childMask = new BitSet();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   BitSet renderChildMask = new BitSet();
/*     */ 
/*     */   
/*     */   boolean isDirty = true;
/*     */ 
/*     */   
/*  50 */   ArrayList switchLevels = new ArrayList(1);
/*     */ 
/*     */   
/*  53 */   HashKey switchKey = new HashKey();
/*     */ 
/*     */   
/*  56 */   int switchIndexCount = 0;
/*     */ 
/*     */   
/*  59 */   UpdateTargets updateTargets = null;
/*     */   
/*  61 */   ArrayList childrenSwitchStates = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setWhichChild(int paramInt, boolean paramBoolean) {
/*  76 */     this.whichChild = paramInt;
/*  77 */     this.isDirty = true;
/*     */     
/*  79 */     if (this.source != null && this.source.isLive()) {
/*  80 */       byte b; this.updateTargets = new UpdateTargets();
/*  81 */       ArrayList arrayList = new ArrayList(1);
/*  82 */       int i = this.children.size();
/*  83 */       switch (paramInt) {
/*     */         case -2:
/*  85 */           for (b = 0; b < i; b++) {
/*  86 */             if (!this.renderChildMask.get(b) || paramBoolean) {
/*  87 */               this.renderChildMask.set(b);
/*  88 */               updateSwitchChild(b, true, arrayList);
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case -1:
/*  93 */           for (b = 0; b < i; b++) {
/*  94 */             if (this.renderChildMask.get(b) == true || paramBoolean) {
/*  95 */               this.renderChildMask.clear(b);
/*  96 */               updateSwitchChild(b, false, arrayList);
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case -3:
/* 101 */           for (b = 0; b < i; b++) {
/* 102 */             if (this.childMask.get(b) == true) {
/* 103 */               if (!this.renderChildMask.get(b) || paramBoolean) {
/* 104 */                 this.renderChildMask.set(b);
/* 105 */                 updateSwitchChild(b, true, arrayList);
/*     */               }
/*     */             
/* 108 */             } else if (this.renderChildMask.get(b) == true || paramBoolean) {
/* 109 */               this.renderChildMask.clear(b);
/* 110 */               updateSwitchChild(b, false, arrayList);
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         
/*     */         default:
/* 116 */           for (b = 0; b < i; b++) {
/* 117 */             if (b == paramInt) {
/* 118 */               if (!this.renderChildMask.get(b) || paramBoolean) {
/* 119 */                 this.renderChildMask.set(b);
/* 120 */                 updateSwitchChild(b, true, arrayList);
/*     */               }
/*     */             
/* 123 */             } else if (this.renderChildMask.get(b) == true || paramBoolean) {
/* 124 */               this.renderChildMask.clear(b);
/* 125 */               updateSwitchChild(b, false, arrayList);
/*     */             } 
/*     */           } 
/*     */           break;
/*     */       } 
/*     */       
/* 131 */       sendMessage(arrayList);
/*     */     } 
/* 133 */     dirtyBoundsCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   int getWhichChild() { return this.whichChild; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setChildMask(BitSet paramBitSet) {
/*     */     int i;
/* 152 */     if (paramBitSet.size() > this.childMask.size()) {
/* 153 */       i = paramBitSet.size();
/*     */     } else {
/* 155 */       i = this.childMask.size();
/*     */     } 
/*     */     byte b;
/* 158 */     for (b = 0; b < i; b++) {
/* 159 */       if (paramBitSet.get(b)) {
/* 160 */         this.childMask.set(b);
/*     */       } else {
/* 162 */         this.childMask.clear(b);
/*     */       } 
/*     */     } 
/* 165 */     this.isDirty = true;
/* 166 */     if (this.source != null && this.source.isLive() && this.whichChild == -3) {
/*     */       
/* 168 */       this.updateTargets = new UpdateTargets();
/* 169 */       ArrayList arrayList = new ArrayList(1);
/* 170 */       int j = this.children.size();
/* 171 */       for (b = 0; b < j; b++) {
/* 172 */         if (paramBitSet.get(b) == true) {
/* 173 */           if (!this.renderChildMask.get(b)) {
/* 174 */             this.renderChildMask.set(b);
/* 175 */             updateSwitchChild(b, true, arrayList);
/*     */           }
/*     */         
/* 178 */         } else if (this.renderChildMask.get(b) == true) {
/* 179 */           this.renderChildMask.clear(b);
/* 180 */           updateSwitchChild(b, false, arrayList);
/*     */         } 
/*     */       } 
/*     */       
/* 184 */       sendMessage(arrayList);
/*     */     } 
/* 186 */     dirtyBoundsCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sendMessage(ArrayList paramArrayList) {
/* 195 */     int i = this.updateTargets.computeSwitchThreads();
/*     */     
/* 197 */     if (i > 0) {
/*     */       
/* 199 */       J3dMessage j3dMessage = new J3dMessage();
/* 200 */       j3dMessage.type = 27;
/* 201 */       j3dMessage.universe = this.universe;
/* 202 */       j3dMessage.threads = i;
/* 203 */       j3dMessage.args[0] = this.updateTargets;
/* 204 */       j3dMessage.args[2] = paramArrayList;
/* 205 */       UnorderList unorderList1 = this.updateTargets.targetList[5];
/*     */ 
/*     */       
/* 208 */       if (unorderList1 != null) {
/*     */         
/* 210 */         int j = unorderList1.size();
/*     */         
/* 212 */         Object[] arrayOfObject2 = new Object[j];
/* 213 */         Object[] arrayOfObject1 = unorderList1.toArray(false);
/* 214 */         for (byte b = 0; b < j; b++) {
/* 215 */           Object[] arrayOfObject3 = (Object[])arrayOfObject1[b];
/* 216 */           Object[] arrayOfObject4 = new Object[arrayOfObject3.length];
/* 217 */           arrayOfObject2[b] = arrayOfObject4;
/* 218 */           for (byte b1 = 0; b1 < arrayOfObject3.length; b1++) {
/* 219 */             BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)arrayOfObject3[b1];
/* 220 */             arrayOfObject4[b1] = boundingLeafRetained.users.toArray();
/*     */           } 
/*     */         } 
/* 223 */         j3dMessage.args[1] = arrayOfObject2;
/*     */       } 
/* 225 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */     
/* 228 */     UnorderList unorderList = this.updateTargets.targetList[4];
/* 229 */     if (unorderList != null) {
/*     */       
/* 231 */       int j = unorderList.size();
/*     */       
/* 233 */       Object[] arrayOfObject = unorderList.toArray(false);
/* 234 */       for (byte b = 0; b < j; b++) {
/* 235 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 236 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/* 237 */           ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject1[b1];
/* 238 */           viewPlatformRetained.processSwitchChanged();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   final BitSet getChildMask() { return (BitSet)this.childMask.clone(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Node currentChild() {
/* 257 */     if (this.whichChild < 0 || this.whichChild >= this.children.size()) {
/* 258 */       return null;
/*     */     }
/* 260 */     return getChild(this.whichChild);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateSwitchChild(int paramInt, boolean paramBoolean, ArrayList paramArrayList) {
/* 267 */     if (this.inSharedGroup) {
/* 268 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 269 */         int i = ((Integer)this.switchLevels.get(b)).intValue();
/* 270 */         traverseSwitchChild(paramInt, this.localToVworldKeys[b], b, this, false, false, paramBoolean, i, paramArrayList);
/*     */       } 
/*     */     } else {
/*     */       
/* 274 */       int i = ((Integer)this.switchLevels.get(0)).intValue();
/* 275 */       traverseSwitchChild(paramInt, null, 0, this, false, false, paramBoolean, i, paramArrayList);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAuxData(SetLiveState paramSetLiveState, int paramInt1, int paramInt2) {
/* 286 */     super.setAuxData(paramSetLiveState, paramInt1, paramInt2);
/* 287 */     this.switchLevels.add(new Integer(paramSetLiveState.switchLevels[paramInt1]));
/* 288 */     int i = this.children.size();
/* 289 */     for (byte b = 0; b < i; b++) {
/* 290 */       ArrayList arrayList = (ArrayList)this.childrenSwitchStates.get(b);
/* 291 */       arrayList.add(paramInt2, new SwitchState(true));
/*     */     } 
/*     */   }
/*     */   
/*     */   void setNodeData(SetLiveState paramSetLiveState) {
/* 296 */     super.setNodeData(paramSetLiveState);
/*     */     
/* 298 */     if (paramSetLiveState.childSwitchLinks != null && (
/* 299 */       !this.inSharedGroup || !paramSetLiveState.childSwitchLinks.contains(this)))
/*     */     {
/*     */       
/* 302 */       paramSetLiveState.childSwitchLinks.add(this);
/*     */     }
/*     */ 
/*     */     
/* 306 */     paramSetLiveState.parentSwitchLink = this;
/*     */     
/* 308 */     if (!this.inSharedGroup) {
/* 309 */       setAuxData(paramSetLiveState, 0, 0);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 314 */       paramSetLiveState.hashkeyIndex = new int[paramSetLiveState.keys.length];
/* 315 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 316 */         int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */         
/* 318 */         if (i >= 0) {
/* 319 */           setAuxData(paramSetLiveState, b, i);
/*     */         } else {
/* 321 */           MasterControl.getCoreLogger().severe("Can't Find matching hashKey in setNodeData.");
/*     */         } 
/* 323 */         paramSetLiveState.hashkeyIndex[b] = i;
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
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 335 */     Targets[] arrayOfTargets = paramSetLiveState.switchTargets;
/* 336 */     ArrayList arrayList1 = paramSetLiveState.switchStates;
/* 337 */     SwitchRetained[] arrayOfSwitchRetained = paramSetLiveState.closestSwitchParents;
/* 338 */     int[] arrayOfInt1 = paramSetLiveState.closestSwitchIndices;
/* 339 */     ArrayList arrayList2 = paramSetLiveState.childSwitchLinks;
/* 340 */     GroupRetained groupRetained = paramSetLiveState.parentSwitchLink;
/* 341 */     int[] arrayOfInt2 = paramSetLiveState.hashkeyIndex;
/*     */ 
/*     */     
/* 344 */     paramSetLiveState.closestSwitchParents = (SwitchRetained[])arrayOfSwitchRetained.clone();
/*     */     
/* 346 */     paramSetLiveState.closestSwitchIndices = (int[])arrayOfInt1.clone();
/*     */     
/*     */     byte b;
/*     */     
/* 350 */     for (b = 0; b < paramSetLiveState.switchLevels.length; b++) {
/* 351 */       paramSetLiveState.switchLevels[b] = paramSetLiveState.switchLevels[b] + 1;
/* 352 */       paramSetLiveState.closestSwitchParents[b] = this;
/*     */     } 
/*     */     
/* 355 */     doSetLive(paramSetLiveState);
/*     */     
/* 357 */     initRenderChildMask();
/*     */ 
/*     */ 
/*     */     
/* 361 */     if (this.inSharedGroup) {
/* 362 */       for (b = 0; b < paramSetLiveState.keys.length; b++) {
/* 363 */         int i = paramSetLiveState.hashkeyIndex[b];
/*     */         
/* 365 */         if (i < this.localToVworldKeys.length) {
/* 366 */           SwitchRetained switchRetained = (paramSetLiveState.switchLevels[b] == 0) ? this : null;
/* 367 */           int j = this.children.size();
/* 368 */           for (byte b1 = 0; b1 < j; b1++) {
/* 369 */             boolean bool = this.renderChildMask.get(b1);
/* 370 */             traverseSwitchChild(b1, paramSetLiveState.keys[b], i, switchRetained, true, false, bool, paramSetLiveState.switchLevels[b], null);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 376 */       SwitchRetained switchRetained = (paramSetLiveState.switchLevels[0] == 0) ? this : null;
/* 377 */       int i = this.children.size();
/* 378 */       for (b = 0; b < i; b++) {
/* 379 */         boolean bool = this.renderChildMask.get(b);
/* 380 */         traverseSwitchChild(b, null, 0, switchRetained, true, false, bool, paramSetLiveState.switchLevels[0], null);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 386 */     paramSetLiveState.switchTargets = arrayOfTargets;
/* 387 */     paramSetLiveState.switchStates = arrayList1;
/* 388 */     paramSetLiveState.closestSwitchParents = arrayOfSwitchRetained;
/* 389 */     paramSetLiveState.closestSwitchIndices = arrayOfInt1;
/* 390 */     for (b = 0; b < paramSetLiveState.switchLevels.length; b++) {
/* 391 */       paramSetLiveState.switchLevels[b] = paramSetLiveState.switchLevels[b] - 1;
/*     */     }
/* 393 */     paramSetLiveState.childSwitchLinks = arrayList2;
/* 394 */     paramSetLiveState.parentSwitchLink = groupRetained;
/* 395 */     paramSetLiveState.hashkeyIndex = arrayOfInt2;
/* 396 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 402 */     int i = this.children.size();
/*     */ 
/*     */ 
/*     */     
/* 406 */     if (this.refCount <= 0) {
/*     */ 
/*     */ 
/*     */       
/* 410 */       if (this.parentSwitchLink != null) {
/* 411 */         for (byte b1 = 0; b1 < this.parentSwitchLink.childrenSwitchLinks.size(); b1++) {
/* 412 */           ArrayList arrayList = (ArrayList)this.parentSwitchLink.childrenSwitchLinks.get(b1);
/*     */           
/* 414 */           if (arrayList.contains(this)) {
/* 415 */             arrayList.remove(this);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 420 */       for (byte b = 0; b < i; b++) {
/* 421 */         ArrayList arrayList = (ArrayList)this.childrenSwitchStates.get(b);
/* 422 */         arrayList.clear();
/*     */       } 
/* 424 */       this.switchLevels.remove(0);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 430 */       for (int j = paramSetLiveState.keys.length - 1; j >= 0; j--) {
/* 431 */         int k = paramSetLiveState.keys[j].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */         
/* 433 */         if (k >= 0) {
/* 434 */           for (byte b = 0; b < i; b++) {
/* 435 */             ArrayList arrayList = (ArrayList)this.childrenSwitchStates.get(b);
/* 436 */             arrayList.remove(k);
/*     */           } 
/* 438 */           this.switchLevels.remove(k);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 443 */     super.removeNodeData(paramSetLiveState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 450 */     Targets[] arrayOfTargets = paramSetLiveState.switchTargets;
/* 451 */     paramSetLiveState.switchTargets = null;
/* 452 */     super.clearLive(paramSetLiveState);
/* 453 */     paramSetLiveState.switchTargets = arrayOfTargets;
/*     */   }
/*     */ 
/*     */   
/*     */   void initRenderChildMask() {
/* 458 */     int i = this.children.size();
/* 459 */     switch (this.whichChild) {
/*     */       case -2:
/* 461 */         for (b = 0; b < i; b++) {
/* 462 */           this.renderChildMask.set(b);
/*     */         }
/*     */         return;
/*     */       case -1:
/* 466 */         for (b = 0; b < i; b++) {
/* 467 */           this.renderChildMask.clear(b);
/*     */         }
/*     */         return;
/*     */       case -3:
/* 471 */         for (b = 0; b < i; b++) {
/* 472 */           if (this.childMask.get(b) == true) {
/* 473 */             this.renderChildMask.set(b);
/*     */           } else {
/* 475 */             this.renderChildMask.clear(b);
/*     */           } 
/*     */         } 
/*     */         return;
/*     */     } 
/* 480 */     for (byte b = 0; b < i; b++) {
/*     */       
/* 482 */       if (b == this.whichChild) {
/* 483 */         this.renderChildMask.set(b);
/*     */       } else {
/* 485 */         this.renderChildMask.clear(b);
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
/*     */   
/*     */   void traverseSwitchChild(int paramInt1, HashKey paramHashKey, int paramInt2, SwitchRetained paramSwitchRetained, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, ArrayList paramArrayList) {
/* 501 */     boolean bool = false;
/* 502 */     ArrayList arrayList2 = (ArrayList)this.childrenSwitchStates.get(paramInt1);
/*     */     
/* 504 */     SwitchState switchState = (SwitchState)arrayList2.get(paramInt2);
/* 505 */     switchState.updateCompositeSwitchMask(paramInt3, paramBoolean3);
/*     */     
/* 507 */     if (paramSwitchRetained != null) {
/* 508 */       if (paramBoolean1) {
/* 509 */         if (!switchState.initialized) {
/* 510 */           switchState.initSwitchOn();
/*     */         }
/*     */       } else {
/* 513 */         boolean bool1 = switchState.evalCompositeSwitchOn();
/* 514 */         if (switchState.cachedSwitchOn != bool1) {
/* 515 */           switchState.updateCachedSwitchOn();
/*     */           
/* 517 */           paramSwitchRetained.updateTargets.addCachedTargets(switchState.cachedTargets);
/*     */           
/* 519 */           bool = true;
/* 520 */           paramArrayList.add(switchState);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 526 */     ArrayList arrayList1 = (ArrayList)this.childrenSwitchLinks.get(paramInt1);
/* 527 */     int i = arrayList1.size();
/* 528 */     for (byte b = 0; b < i; b++) {
/*     */       
/* 530 */       Object object = arrayList1.get(b);
/* 531 */       if (object instanceof SwitchRetained) {
/* 532 */         SwitchRetained switchRetained = (SwitchRetained)object;
/* 533 */         int j = switchRetained.children.size();
/* 534 */         for (byte b1 = 0; b1 < j; b1++) {
/* 535 */           switchRetained.traverseSwitchChild(b1, paramHashKey, paramInt2, paramSwitchRetained, paramBoolean1, bool, paramBoolean3, paramInt3, paramArrayList);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 540 */         LinkRetained linkRetained = (LinkRetained)object;
/* 541 */         if (paramHashKey == null) {
/* 542 */           this.switchKey.reset();
/* 543 */           this.switchKey.append(this.locale.nodeId);
/*     */         } else {
/* 545 */           this.switchKey.set(paramHashKey);
/*     */         } 
/* 547 */         this.switchKey.append(LinkRetained.plus).append(linkRetained.nodeId);
/*     */         
/* 549 */         if (linkRetained.sharedGroup != null && linkRetained.sharedGroup.localToVworldKeys != null) {
/*     */ 
/*     */           
/* 552 */           int j = this.switchKey.equals(linkRetained.sharedGroup.localToVworldKeys, 0, linkRetained.sharedGroup.localToVworldKeys.length);
/*     */           
/* 554 */           if (j < 0) {
/* 555 */             System.err.println("SwitchRetained : Can't find hashKey");
/*     */           }
/*     */           
/* 558 */           if (j < linkRetained.sharedGroup.localToVworldKeys.length) {
/* 559 */             int k = linkRetained.sharedGroup.children.size();
/* 560 */             for (byte b1 = 0; b1 < k; b1++) {
/* 561 */               linkRetained.sharedGroup.traverseSwitchChild(b1, linkRetained.sharedGroup.localToVworldKeys[j], j, paramSwitchRetained, paramBoolean1, bool, paramBoolean3, paramInt3, paramArrayList);
/*     */             }
/*     */           } 
/*     */         } 
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
/*     */ 
/*     */   
/*     */   void traverseSwitchParent() {
/* 581 */     if (this.inSharedGroup) {
/* 582 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 583 */         int i = ((Integer)this.switchLevels.get(b)).intValue();
/* 584 */         SwitchRetained switchRetained = (i == 0) ? this : null;
/* 585 */         int j = this.children.size();
/* 586 */         for (byte b1 = 0; b1 < j; b1++) {
/* 587 */           boolean bool = this.renderChildMask.get(b1);
/* 588 */           traverseSwitchChild(b1, this.localToVworldKeys[b], b, switchRetained, true, false, bool, i, null);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 593 */       int i = ((Integer)this.switchLevels.get(0)).intValue();
/* 594 */       SwitchRetained switchRetained = (i == 0) ? this : null;
/* 595 */       int j = this.children.size();
/* 596 */       for (byte b = 0; b < j; b++) {
/* 597 */         boolean bool = this.renderChildMask.get(b);
/* 598 */         traverseSwitchChild(b, null, 0, switchRetained, true, false, bool, i, null);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 604 */     if (this.parentSwitchLink != null) {
/* 605 */       if (this.parentSwitchLink instanceof SwitchRetained) {
/* 606 */         ((SwitchRetained)this.parentSwitchLink).traverseSwitchParent();
/* 607 */       } else if (this.parentSwitchLink instanceof SharedGroupRetained) {
/* 608 */         ((SharedGroupRetained)this.parentSwitchLink).traverseSwitchParent();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void computeCombineBounds(Bounds paramBounds) {
/* 618 */     if (this.boundsAutoCompute) {
/* 619 */       if (!VirtualUniverse.mc.cacheAutoComputedBounds) {
/* 620 */         if (this.whichChild == -2) {
/* 621 */           for (byte b = 0; b < this.children.size(); b++) {
/* 622 */             NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 623 */             if (nodeRetained != null)
/* 624 */               nodeRetained.computeCombineBounds(paramBounds); 
/*     */           } 
/* 626 */         } else if (this.whichChild == -3) {
/* 627 */           for (byte b = 0; b < this.children.size(); b++) {
/* 628 */             if (this.childMask.get(b)) {
/* 629 */               NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 630 */               if (nodeRetained != null)
/* 631 */                 nodeRetained.computeCombineBounds(paramBounds); 
/*     */             } 
/*     */           } 
/* 634 */         } else if (this.whichChild != -1 && 
/* 635 */           this.whichChild < this.children.size()) {
/* 636 */           NodeRetained nodeRetained = (NodeRetained)this.children.get(this.whichChild);
/* 637 */           if (nodeRetained != null) {
/* 638 */             nodeRetained.computeCombineBounds(paramBounds);
/*     */           }
/*     */         } 
/*     */       } else {
/* 642 */         if (this.cachedBounds == null) {
/* 643 */           this.cachedBounds = new BoundingSphere();
/* 644 */           ((BoundingSphere)this.cachedBounds).setRadius(-1.0D);
/* 645 */           if (this.whichChild == -2) {
/* 646 */             for (byte b = 0; b < this.children.size(); b++) {
/* 647 */               NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 648 */               if (nodeRetained != null)
/* 649 */                 nodeRetained.computeCombineBounds(this.cachedBounds); 
/*     */             } 
/* 651 */           } else if (this.whichChild == -3) {
/* 652 */             for (byte b = 0; b < this.children.size(); b++) {
/* 653 */               if (this.childMask.get(b)) {
/* 654 */                 NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 655 */                 if (nodeRetained != null)
/* 656 */                   nodeRetained.computeCombineBounds(this.cachedBounds); 
/*     */               } 
/*     */             } 
/* 659 */           } else if (this.whichChild != -1 && 
/* 660 */             this.whichChild < this.children.size()) {
/* 661 */             NodeRetained nodeRetained = (NodeRetained)this.children.get(this.whichChild);
/* 662 */             if (nodeRetained != null) {
/* 663 */               nodeRetained.computeCombineBounds(this.cachedBounds);
/*     */             }
/*     */           } 
/*     */         } 
/* 667 */         paramBounds.combine(this.cachedBounds);
/*     */       } 
/*     */     } else {
/*     */       
/* 671 */       synchronized (this.localBounds) {
/* 672 */         paramBounds.combine(this.localBounds);
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
/*     */   Bounds getBounds() {
/* 687 */     if (this.boundsAutoCompute) {
/* 688 */       if (this.cachedBounds != null) {
/* 689 */         return (Bounds)this.cachedBounds.clone();
/*     */       }
/*     */       
/* 692 */       BoundingSphere boundingSphere = new BoundingSphere();
/* 693 */       boundingSphere.setRadius(-1.0D);
/*     */       
/* 695 */       if (this.whichChild == -2) {
/* 696 */         for (byte b = 0; b < this.children.size(); b++) {
/* 697 */           NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 698 */           if (nodeRetained != null) {
/* 699 */             nodeRetained.computeCombineBounds(boundingSphere);
/*     */           }
/*     */         } 
/* 702 */       } else if (this.whichChild == -3) {
/* 703 */         for (byte b = 0; b < this.children.size(); b++) {
/* 704 */           if (this.childMask.get(b)) {
/* 705 */             NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 706 */             if (nodeRetained != null) {
/* 707 */               nodeRetained.computeCombineBounds(boundingSphere);
/*     */             }
/*     */           } 
/*     */         } 
/* 711 */       } else if (this.whichChild != -1 && this.whichChild >= 0 && this.whichChild < this.children.size()) {
/*     */ 
/*     */ 
/*     */         
/* 715 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(this.whichChild);
/* 716 */         if (nodeRetained != null) {
/* 717 */           nodeRetained.computeCombineBounds(boundingSphere);
/*     */         }
/*     */       } 
/* 720 */       return boundingSphere;
/*     */     } 
/*     */     
/* 723 */     return super.getBounds();
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
/*     */   void compile(CompileState paramCompileState) {
/* 743 */     super.compile(paramCompileState);
/*     */ 
/*     */     
/* 746 */     this.mergeFlag = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void insertChildrenData(int paramInt) {
/* 754 */     if (this.childrenSwitchStates == null) {
/* 755 */       this.childrenSwitchStates = new ArrayList(1);
/* 756 */       this.childrenSwitchLinks = new ArrayList(1);
/*     */     } 
/*     */     
/* 759 */     this.childrenSwitchLinks.add(paramInt, new ArrayList(1));
/*     */     
/* 761 */     ArrayList arrayList = new ArrayList(1);
/* 762 */     this.childrenSwitchStates.add(paramInt, arrayList);
/* 763 */     if (this.source != null && this.source.isLive()) {
/* 764 */       for (byte b = 0; b < this.localToVworld.length; b++) {
/* 765 */         arrayList.add(new SwitchState(true));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void appendChildrenData() {
/* 771 */     if (this.childrenSwitchStates == null) {
/* 772 */       this.childrenSwitchStates = new ArrayList(1);
/* 773 */       this.childrenSwitchLinks = new ArrayList(1);
/*     */     } 
/* 775 */     this.childrenSwitchLinks.add(new ArrayList(1));
/*     */     
/* 777 */     ArrayList arrayList = new ArrayList(1);
/* 778 */     this.childrenSwitchStates.add(arrayList);
/* 779 */     if (this.source != null && this.source.isLive()) {
/* 780 */       for (byte b = 0; b < this.localToVworld.length; b++) {
/* 781 */         arrayList.add(new SwitchState(true));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void removeChildrenData(int paramInt) {
/* 787 */     ArrayList arrayList1 = (ArrayList)this.childrenSwitchStates.get(paramInt);
/*     */     
/* 789 */     arrayList1.clear();
/* 790 */     this.childrenSwitchStates.remove(paramInt);
/*     */     
/* 792 */     ArrayList arrayList2 = (ArrayList)this.childrenSwitchLinks.get(paramInt);
/* 793 */     arrayList2.clear();
/* 794 */     this.childrenSwitchLinks.remove(paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   void childDoSetLive(NodeRetained paramNodeRetained, int paramInt, SetLiveState paramSetLiveState) {
/* 799 */     int i = this.inSharedGroup ? paramSetLiveState.keys.length : 1;
/* 800 */     paramSetLiveState.childSwitchLinks = (ArrayList)this.childrenSwitchLinks.get(paramInt);
/* 801 */     for (byte b1 = 0; b1 < i; b1++) {
/* 802 */       paramSetLiveState.closestSwitchIndices[b1] = this.switchIndexCount;
/* 803 */       paramSetLiveState.closestSwitchParents[b1] = this;
/*     */     } 
/*     */ 
/*     */     
/* 807 */     this.switchIndexCount++;
/*     */     
/* 809 */     Targets[] arrayOfTargets = new Targets[i];
/* 810 */     for (byte b2 = 0; b2 < i; b2++) {
/* 811 */       arrayOfTargets[b2] = new Targets();
/*     */     }
/* 813 */     paramSetLiveState.switchTargets = arrayOfTargets;
/* 814 */     paramSetLiveState.switchStates = (ArrayList)this.childrenSwitchStates.get(paramInt);
/*     */     
/* 816 */     if (paramNodeRetained != null) {
/* 817 */       paramNodeRetained.setLive(paramSetLiveState);
/*     */     }
/*     */ 
/*     */     
/* 821 */     if (!this.inSharedGroup) {
/* 822 */       CachedTargets cachedTargets = paramSetLiveState.switchTargets[0].snapShotInit();
/* 823 */       SwitchState switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/* 824 */       switchState.cachedTargets = cachedTargets;
/*     */     } else {
/* 826 */       for (byte b = 0; b < i; b++) {
/* 827 */         CachedTargets cachedTargets = paramSetLiveState.switchTargets[b].snapShotInit();
/* 828 */         SwitchState switchState = (SwitchState)paramSetLiveState.switchStates.get(paramSetLiveState.hashkeyIndex[b]);
/*     */         
/* 830 */         switchState.cachedTargets = cachedTargets;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 840 */   TargetsInterface getClosestTargetsInterface(int paramInt) { return (paramInt == 1) ? this : (TargetsInterface)this.parentTransformLink; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CachedTargets getCachedTargets(int paramInt1, int paramInt2, int paramInt3) {
/* 846 */     if (paramInt1 == 1) {
/* 847 */       ArrayList arrayList = (ArrayList)this.childrenSwitchStates.get(paramInt3);
/*     */       
/* 849 */       if (paramInt2 < arrayList.size()) {
/* 850 */         SwitchState switchState = (SwitchState)arrayList.get(paramInt2);
/*     */         
/* 852 */         return switchState.cachedTargets;
/*     */       } 
/* 854 */       return null;
/*     */     } 
/*     */     
/* 857 */     System.err.println("getCachedTargets: wrong arguments");
/* 858 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetCachedTargets(int paramInt1, CachedTargets[] paramArrayOfCachedTargets, int paramInt2) {
/* 864 */     if (paramInt1 == 1) {
/* 865 */       ArrayList arrayList = (ArrayList)this.childrenSwitchStates.get(paramInt2);
/*     */       
/* 867 */       if (paramArrayOfCachedTargets.length != arrayList.size()) {
/* 868 */         System.err.println("resetCachedTargets: unmatched length!" + paramArrayOfCachedTargets.length + " " + arrayList.size());
/*     */         
/* 870 */         System.err.println("  resetCachedTargets: " + this);
/*     */       } 
/*     */       
/* 873 */       for (byte b = 0; b < paramArrayOfCachedTargets.length; b++) {
/* 874 */         SwitchState switchState = (SwitchState)arrayList.get(b);
/* 875 */         switchState.cachedTargets = paramArrayOfCachedTargets[b];
/*     */       } 
/*     */     } else {
/* 878 */       System.err.println("resetCachedTargets: wrong arguments");
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayList getTargetsData(int paramInt1, int paramInt2) {
/* 883 */     if (paramInt1 == 1) {
/* 884 */       return (ArrayList)this.childrenSwitchStates.get(paramInt2);
/*     */     }
/* 886 */     System.err.println("getTargetsData: wrong arguments");
/* 887 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTargetThreads(int paramInt) {
/* 892 */     System.err.println("getTargetsThreads: wrong arguments");
/* 893 */     return -1;
/*     */   }
/*     */ 
/*     */   
/* 897 */   public void updateCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets) { System.err.println("updateCachedTarget: wrong arguments"); }
/*     */ 
/*     */ 
/*     */   
/* 901 */   public void computeTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets) { System.err.println("computeTargetThreads: wrong arguments"); }
/*     */ 
/*     */ 
/*     */   
/* 905 */   public void updateTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets) { System.err.println("updateTargetThreads: wrong arguments"); }
/*     */ 
/*     */ 
/*     */   
/* 909 */   public void propagateTargetThreads(int paramInt1, int paramInt2) { System.err.println("propagateTargetThreads: wrong arguments"); }
/*     */ 
/*     */ 
/*     */   
/* 913 */   public void copyCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets) { System.err.println("copyCachedTarget: wrong arguments"); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\SwitchRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */