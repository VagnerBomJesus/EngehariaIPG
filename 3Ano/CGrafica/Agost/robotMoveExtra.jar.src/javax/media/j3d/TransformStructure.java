/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TransformStructure
/*     */   extends J3dStructure
/*     */   implements ObjectUpdate
/*     */ {
/*  26 */   private HashSet<TransformData> transformSet = new HashSet();
/*     */   
/*  28 */   private ArrayList objectList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private ArrayList blUsers = new ArrayList();
/*     */ 
/*     */   
/*  36 */   private UpdateTargets targets = new UpdateTargets();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   private ArrayList collisionObjectList = new ArrayList();
/*     */ 
/*     */   
/*  44 */   private ArrayList dirtyTransformGroups = new ArrayList();
/*     */ 
/*     */   
/*  47 */   private ArrayList keySet = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   private ArrayList<TransformGroupRetained> activeTraverseList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  56 */   private ArrayList switchDirtyTgList = new ArrayList(1);
/*     */ 
/*     */   
/*     */   private boolean lazyUpdate = false;
/*     */   
/*  61 */   private ArrayList switchChangedList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inUpdateObjectList = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   TransformStructure(VirtualUniverse paramVirtualUniverse) { super(paramVirtualUniverse, 8192); }
/*     */ 
/*     */   
/*     */   void processMessages(long paramLong) {
/*  74 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/*  75 */     int i = getNumMessage();
/*     */ 
/*     */ 
/*     */     
/*  79 */     if (i <= 0) {
/*     */       return;
/*     */     }
/*     */     
/*  83 */     this.targets.clearNodes();
/*  84 */     this.objectList.clear();
/*  85 */     this.blUsers.clear();
/*  86 */     this.inUpdateObjectList = false;
/*     */     
/*  88 */     synchronized (this.universe.sceneGraphLock) {
/*     */       int j;
/*     */       
/*  91 */       for (j = i - 1; j >= 0; j--) {
/*  92 */         J3dMessage j3dMessage = arrayOfJ3dMessage[j];
/*  93 */         if (j3dMessage.type == 3)
/*     */         {
/*     */           
/*  96 */           this.transformSet.add(new TransformData((TransformGroupRetained)j3dMessage.args[1], (Transform3D)j3dMessage.args[2]));
/*     */         }
/*     */       } 
/*     */       
/* 100 */       for (j = 0; j < i; j++) {
/* 101 */         Object[] arrayOfObject; J3dMessage j3dMessage = arrayOfJ3dMessage[j];
/*     */         
/* 103 */         switch (j3dMessage.type) {
/*     */           case 0:
/* 105 */             this.objectList.add(j3dMessage.args[0]);
/* 106 */             if (j3dMessage.args[true] != null) {
/* 107 */               TargetsInterface targetsInterface = (TargetsInterface)j3dMessage.args[1];
/* 108 */               targetsInterface.updateCachedTargets(0, (CachedTargets[])j3dMessage.args[2]);
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/* 114 */             removeNodes(j3dMessage);
/*     */             break;
/*     */           case 27:
/* 117 */             processSwitchChanged(j3dMessage);
/*     */             break;
/*     */           case 24:
/* 120 */             this.objectList.add(j3dMessage.args[3]);
/* 121 */             if (j3dMessage.args[4] != null) {
/* 122 */               TargetsInterface targetsInterface = (TargetsInterface)j3dMessage.args[4];
/* 123 */               targetsInterface.updateCachedTargets(0, (CachedTargets[])j3dMessage.args[5]);
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 17:
/* 129 */             this.objectList.add(j3dMessage.args[0]);
/*     */             break;
/*     */           case 16:
/* 132 */             this.objectList.add(j3dMessage.args[3]);
/*     */             break;
/*     */           case 26:
/* 135 */             this.objectList.add(j3dMessage.args[1]);
/* 136 */             arrayOfObject = (Object[])j3dMessage.args[2];
/* 137 */             if (arrayOfObject != null) {
/* 138 */               Object[] arrayOfObject1 = (Object[])j3dMessage.args[3];
/* 139 */               for (byte b = 0; b < arrayOfObject.length; b++) {
/* 140 */                 TargetsInterface targetsInterface = (TargetsInterface)arrayOfObject[b];
/*     */                 
/* 142 */                 targetsInterface.updateCachedTargets(0, (CachedTargets[])arrayOfObject1[b]);
/*     */               } 
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 25:
/* 149 */             this.objectList.add(j3dMessage.args[0]);
/*     */             break;
/*     */           case 37:
/* 152 */             processBoundsAutoComputeChanged(j3dMessage);
/*     */             break;
/*     */           case 35:
/* 155 */             processRegionBoundChanged(j3dMessage);
/*     */             break;
/*     */           case 34:
/* 158 */             processCollisionBoundChanged(j3dMessage);
/*     */             break;
/*     */         } 
/* 161 */         j3dMessage.decRefcount();
/*     */       } 
/* 163 */       processCurrentLocalToVworld();
/*     */ 
/*     */ 
/*     */       
/* 167 */       if (this.objectList.size() > 0) {
/* 168 */         processGeometryAtomVwcBounds();
/*     */       }
/* 170 */       processVwcBounds();
/*     */     } 
/*     */ 
/*     */     
/* 174 */     this.objectList.clear();
/*     */     
/* 176 */     Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processCurrentLocalToVworld() {
/* 186 */     this.lazyUpdate = false;
/*     */     
/* 188 */     int i = this.transformSet.size();
/* 189 */     int j = this.switchDirtyTgList.size();
/* 190 */     if (i <= 0 && j <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (i > 0) {
/* 197 */       Iterator iterator = this.transformSet.iterator();
/* 198 */       while (iterator.hasNext()) {
/* 199 */         TransformData transformData = (TransformData)iterator.next();
/* 200 */         TransformGroupRetained transformGroupRetained = transformData.getTransformGroupRetained();
/* 201 */         transformGroupRetained.currentTransform.set(transformData.getTransform3D());
/*     */         
/* 203 */         synchronized (transformGroupRetained) {
/* 204 */           if (transformGroupRetained.perPathData != null) {
/* 205 */             if (!transformGroupRetained.inSharedGroup) {
/* 206 */               TransformGroupData transformGroupData = transformGroupRetained.perPathData[0];
/* 207 */               if (!transformGroupData.switchState.inSwitch) {
/*     */                 
/* 209 */                 this.activeTraverseList.add(transformGroupRetained);
/* 210 */                 transformGroupData.markedDirty = true;
/* 211 */                 transformGroupData.switchDirty = false;
/*     */ 
/*     */ 
/*     */               
/*     */               }
/* 216 */               else if (transformGroupData.switchState.currentSwitchOn) {
/* 217 */                 this.activeTraverseList.add(transformGroupRetained);
/* 218 */                 transformGroupData.switchDirty = false;
/* 219 */                 transformGroupData.markedDirty = true;
/*     */               } else {
/* 221 */                 transformGroupData.switchDirty = true;
/* 222 */                 transformGroupData.markedDirty = false;
/*     */               } 
/*     */             } else {
/*     */               
/* 226 */               int k = transformGroupRetained.perPathData.length;
/* 227 */               boolean bool = false;
/*     */               
/* 229 */               for (byte b = 0; b < k; b++) {
/* 230 */                 TransformGroupData transformGroupData = transformGroupRetained.perPathData[b];
/* 231 */                 if (!transformGroupData.switchState.inSwitch) {
/* 232 */                   if (!bool) {
/*     */                     
/* 234 */                     bool = true;
/* 235 */                     this.activeTraverseList.add(transformGroupRetained);
/*     */                   } 
/* 237 */                   transformGroupData.markedDirty = true;
/* 238 */                   transformGroupData.switchDirty = false;
/*     */ 
/*     */ 
/*     */                 
/*     */                 }
/* 243 */                 else if (transformGroupData.switchState.currentSwitchOn) {
/* 244 */                   if (!bool) {
/* 245 */                     bool = true;
/* 246 */                     this.activeTraverseList.add(transformGroupRetained);
/*     */                   } 
/* 248 */                   transformGroupData.switchDirty = false;
/* 249 */                   transformGroupData.markedDirty = true;
/*     */                 } else {
/* 251 */                   transformGroupData.switchDirty = true;
/* 252 */                   transformGroupData.markedDirty = false;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 263 */     if (j > 0) {
/* 264 */       this.activeTraverseList.addAll(this.switchDirtyTgList);
/* 265 */       this.switchDirtyTgList.clear();
/* 266 */       this.lazyUpdate = true;
/*     */     } 
/*     */ 
/*     */     
/* 270 */     i = this.activeTraverseList.size();
/* 271 */     TransformGroupRetained[] arrayOfTransformGroupRetained = (TransformGroupRetained[])this.activeTraverseList.toArray(new TransformGroupRetained[i]);
/*     */ 
/*     */ 
/*     */     
/* 275 */     if (i > 0) {
/*     */       
/* 277 */       sortTransformGroups(i, arrayOfTransformGroupRetained);
/*     */ 
/*     */       
/* 280 */       for (byte b = 0; b < i; b++) {
/* 281 */         arrayOfTransformGroupRetained[b].processChildLocalToVworld(this.dirtyTransformGroups, this.keySet, this.targets, this.blUsers);
/*     */       }
/*     */       
/* 284 */       if (!this.inUpdateObjectList) {
/* 285 */         VirtualUniverse.mc.addMirrorObject(this);
/* 286 */         this.inUpdateObjectList = true;
/*     */       } 
/*     */     } 
/*     */     
/* 290 */     this.transformSet.clear();
/* 291 */     this.activeTraverseList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   private void sortTransformGroups(int paramInt, TransformGroupRetained[] paramArrayOfTransformGroupRetained) {
/* 296 */     if (paramInt < 7) {
/* 297 */       insertSort(paramInt, paramArrayOfTransformGroupRetained);
/*     */     } else {
/* 299 */       quicksort(0, paramInt - 1, paramArrayOfTransformGroupRetained);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertSort(int paramInt, TransformGroupRetained[] paramArrayOfTransformGroupRetained) {
/* 305 */     for (byte b = 0; b < paramInt; b++) {
/* 306 */       for (byte b1 = b; b1 && (paramArrayOfTransformGroupRetained[b1 - true]).maxTransformLevel > (paramArrayOfTransformGroupRetained[b1]).maxTransformLevel; 
/* 307 */         b1--) {
/* 308 */         TransformGroupRetained transformGroupRetained = paramArrayOfTransformGroupRetained[b1];
/* 309 */         paramArrayOfTransformGroupRetained[b1] = paramArrayOfTransformGroupRetained[b1 - true];
/* 310 */         paramArrayOfTransformGroupRetained[b1 - true] = transformGroupRetained;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void quicksort(int paramInt1, int paramInt2, TransformGroupRetained[] paramArrayOfTransformGroupRetained) {
/* 316 */     int i = paramInt1;
/* 317 */     int j = paramInt2;
/* 318 */     double d = (paramArrayOfTransformGroupRetained[(paramInt1 + paramInt2) / 2]).maxTransformLevel;
/*     */     do {
/* 320 */       for (; (paramArrayOfTransformGroupRetained[i]).maxTransformLevel < d; i++);
/* 321 */       for (; d < (paramArrayOfTransformGroupRetained[j]).maxTransformLevel; j--);
/* 322 */       if (i > j)
/* 323 */         continue;  TransformGroupRetained transformGroupRetained = paramArrayOfTransformGroupRetained[i];
/* 324 */       paramArrayOfTransformGroupRetained[i] = paramArrayOfTransformGroupRetained[j];
/* 325 */       paramArrayOfTransformGroupRetained[j] = transformGroupRetained;
/*     */       
/* 327 */       i++;
/* 328 */       j--;
/*     */     }
/* 330 */     while (i <= j);
/*     */     
/* 332 */     if (paramInt1 < j) quicksort(paramInt1, j, paramArrayOfTransformGroupRetained); 
/* 333 */     if (paramInt1 < paramInt2) quicksort(i, paramInt2, paramArrayOfTransformGroupRetained);
/*     */   
/*     */   }
/*     */   
/*     */   public void updateObject() {
/* 338 */     processLastLocalToVworld();
/* 339 */     processLastSwitchOn();
/*     */   }
/*     */ 
/*     */   
/*     */   void processLastSwitchOn() {
/* 344 */     int i = this.switchChangedList.size();
/* 345 */     if (i > 0) {
/*     */ 
/*     */       
/* 348 */       for (byte b = 0; b < i; b++) {
/* 349 */         SwitchState switchState = (SwitchState)this.switchChangedList.get(b);
/* 350 */         switchState.updateLastSwitchOn();
/*     */       } 
/* 352 */       this.switchChangedList.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processLastLocalToVworld() {
/* 363 */     int i = this.dirtyTransformGroups.size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 369 */     for (byte b1 = 0, b2 = 0; b1 < i; b1++) {
/* 370 */       TransformGroupRetained transformGroupRetained = (TransformGroupRetained)this.dirtyTransformGroups.get(b1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 376 */       synchronized (transformGroupRetained) {
/* 377 */         if (transformGroupRetained.childLocalToVworld != null) {
/* 378 */           if (transformGroupRetained.inSharedGroup) {
/* 379 */             HashKey hashKey = (HashKey)this.keySet.get(b2++); byte b;
/* 380 */             for (b = 0; b < transformGroupRetained.localToVworldKeys.length && 
/* 381 */               !transformGroupRetained.localToVworldKeys[b].equals(hashKey); b++);
/*     */ 
/*     */ 
/*     */             
/* 385 */             if (b < transformGroupRetained.localToVworldKeys.length)
/*     */             {
/* 387 */               transformGroupRetained.childLocalToVworldIndex[b][0] = transformGroupRetained.childLocalToVworldIndex[b][1];
/*     */             
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 393 */             transformGroupRetained.childLocalToVworldIndex[0][0] = transformGroupRetained.childLocalToVworldIndex[0][1];
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 401 */     this.dirtyTransformGroups.clear();
/* 402 */     this.keySet.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processGeometryAtomVwcBounds() {
/* 413 */     int i = this.objectList.size(); byte b;
/* 414 */     for (b = 0; b < i; b++) {
/* 415 */       Object[] arrayOfObject = (Object[])this.objectList.get(b);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 420 */       for (byte b1 = 0; b1 < arrayOfObject.length; b1++) {
/*     */         
/* 422 */         synchronized (arrayOfObject[b1]) {
/* 423 */           if (arrayOfObject[b1] instanceof GeometryAtom) {
/* 424 */             GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject[b1];
/* 425 */             Shape3DRetained shape3DRetained = geometryAtom.source;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 431 */             synchronized (shape3DRetained.bounds) {
/* 432 */               shape3DRetained.vwcBounds.transform(shape3DRetained.bounds, shape3DRetained.getCurrentLocalToVworld(0));
/*     */             } 
/*     */             
/* 435 */             if (shape3DRetained.collisionBound != null) {
/* 436 */               shape3DRetained.collisionVwcBound.transform(shape3DRetained.collisionBound, shape3DRetained.getCurrentLocalToVworld(0));
/*     */             }
/*     */ 
/*     */             
/* 440 */             geometryAtom.centroidIsDirty = true;
/* 441 */           } else if (arrayOfObject[b1] instanceof GroupRetained) {
/*     */             
/* 443 */             GroupRetained groupRetained = (GroupRetained)arrayOfObject[b1];
/* 444 */             Bounds bounds = (groupRetained.sourceNode.collisionBound != null) ? groupRetained.sourceNode.collisionBound : groupRetained.sourceNode.getEffectiveBounds();
/*     */ 
/*     */             
/* 447 */             groupRetained.collisionVwcBounds.transform(bounds, groupRetained.getCurrentLocalToVworld());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 454 */     for (b = 0; b < this.collisionObjectList.size(); b++) {
/* 455 */       Object[] arrayOfObject = (Object[])this.collisionObjectList.get(b);
/* 456 */       for (byte b1 = 0; b1 < arrayOfObject.length; b1++) {
/* 457 */         synchronized (arrayOfObject[b1]) {
/* 458 */           if (arrayOfObject[b1] instanceof GeometryAtom) {
/* 459 */             GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject[b1];
/* 460 */             Shape3DRetained shape3DRetained = geometryAtom.source;
/*     */             
/* 462 */             if (shape3DRetained.collisionVwcBound != null) {
/* 463 */               shape3DRetained.collisionVwcBound.transform(shape3DRetained.collisionBound, shape3DRetained.getCurrentLocalToVworld(0));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 471 */     this.collisionObjectList.clear();
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
/*     */   void processVwcBounds() {
/* 483 */     UnorderList unorderList = this.targets.targetList[0];
/* 484 */     if (unorderList != null) {
/* 485 */       int i = unorderList.size();
/* 486 */       Object[] arrayOfObject = unorderList.toArray(false);
/*     */       
/* 488 */       for (byte b1 = 0; b1 < i; b1++) {
/* 489 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b1];
/* 490 */         for (byte b2 = 0; b2 < arrayOfObject1.length; b2++) {
/* 491 */           synchronized (arrayOfObject1[b2]) {
/* 492 */             GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject1[b2];
/* 493 */             Shape3DRetained shape3DRetained = geometryAtom.source;
/* 494 */             synchronized (shape3DRetained.bounds) {
/* 495 */               shape3DRetained.vwcBounds.transform(shape3DRetained.bounds, shape3DRetained.getCurrentLocalToVworld(0));
/*     */             } 
/*     */             
/* 498 */             if (shape3DRetained.collisionBound != null) {
/* 499 */               shape3DRetained.collisionVwcBound.transform(shape3DRetained.collisionBound, shape3DRetained.getCurrentLocalToVworld(0));
/*     */             }
/*     */ 
/*     */             
/* 503 */             geometryAtom.centroidIsDirty = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 509 */     unorderList = this.targets.targetList[6];
/* 510 */     if (unorderList != null) {
/* 511 */       int i = unorderList.size();
/* 512 */       Object[] arrayOfObject = unorderList.toArray(false);
/*     */       
/* 514 */       for (byte b1 = 0; b1 < i; b1++) {
/* 515 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b1];
/* 516 */         for (byte b2 = 0; b2 < arrayOfObject1.length; b2++) {
/*     */           
/* 518 */           GroupRetained groupRetained = (GroupRetained)arrayOfObject1[b2];
/* 519 */           Bounds bounds = (groupRetained.sourceNode.collisionBound != null) ? groupRetained.sourceNode.collisionBound : groupRetained.sourceNode.getEffectiveBounds();
/*     */ 
/*     */           
/* 522 */           groupRetained.collisionVwcBounds.transform(bounds, groupRetained.getCurrentLocalToVworld());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 529 */     for (byte b = 0; b < this.collisionObjectList.size(); b++) {
/* 530 */       Object[] arrayOfObject = (Object[])this.collisionObjectList.get(b);
/* 531 */       for (byte b1 = 0; b1 < arrayOfObject.length; b1++) {
/* 532 */         synchronized (arrayOfObject[b1]) {
/* 533 */           if (arrayOfObject[b1] instanceof GeometryAtom) {
/* 534 */             GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject[b1];
/* 535 */             Shape3DRetained shape3DRetained = geometryAtom.source;
/*     */             
/* 537 */             if (shape3DRetained.collisionVwcBound != null) {
/* 538 */               shape3DRetained.collisionVwcBound.transform(shape3DRetained.collisionBound, shape3DRetained.getCurrentLocalToVworld(0));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 546 */     this.collisionObjectList.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 551 */   void processRegionBoundChanged(J3dMessage paramJ3dMessage) { processBoundsChanged((Object[])paramJ3dMessage.args[0], (Bounds)paramJ3dMessage.args[1]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processBoundsChanged(Object[] paramArrayOfObject, Bounds paramBounds) {
/* 559 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 560 */       GeometryAtom geometryAtom = (GeometryAtom)paramArrayOfObject[b];
/* 561 */       Shape3DRetained shape3DRetained = geometryAtom.source;
/*     */ 
/*     */ 
/*     */       
/* 565 */       shape3DRetained.bounds = paramBounds;
/* 566 */       if (shape3DRetained.collisionBound == null) {
/* 567 */         shape3DRetained.collisionVwcBound = shape3DRetained.vwcBounds;
/*     */       }
/*     */     } 
/* 570 */     this.objectList.add(paramArrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processCollisionBoundChanged(J3dMessage paramJ3dMessage) {
/* 577 */     Bounds bounds = (Bounds)paramJ3dMessage.args[1];
/*     */     
/* 579 */     if (paramJ3dMessage.args[0] instanceof GroupRetained) {
/* 580 */       GroupRetained groupRetained = (GroupRetained)paramJ3dMessage.args[0];
/* 581 */       if (groupRetained.mirrorGroup != null) {
/* 582 */         this.objectList.add(groupRetained.mirrorGroup);
/*     */       }
/*     */     } else {
/* 585 */       Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/*     */ 
/*     */       
/* 588 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/* 589 */         GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject[b];
/* 590 */         Shape3DRetained shape3DRetained = geometryAtom.source;
/*     */         
/* 592 */         shape3DRetained.collisionBound = bounds;
/*     */         
/* 594 */         if (shape3DRetained.collisionBound != null) {
/*     */ 
/*     */           
/* 597 */           shape3DRetained.collisionVwcBound = (Bounds)shape3DRetained.collisionBound.clone();
/*     */         } else {
/* 599 */           shape3DRetained.collisionVwcBound = shape3DRetained.vwcBounds;
/*     */         } 
/*     */       } 
/* 602 */       this.collisionObjectList.add(arrayOfObject);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 608 */   void processBoundsAutoComputeChanged(J3dMessage paramJ3dMessage) { processBoundsChanged((Object[])paramJ3dMessage.args[0], (Bounds)paramJ3dMessage.args[1]); }
/*     */ 
/*     */   
/*     */   void processSwitchChanged(J3dMessage paramJ3dMessage) {
/* 612 */     ArrayList arrayList = (ArrayList)paramJ3dMessage.args[2];
/*     */ 
/*     */     
/* 615 */     int i = arrayList.size();
/* 616 */     if (i > 0) {
/*     */ 
/*     */       
/* 619 */       for (byte b = 0; b < i; b++) {
/* 620 */         SwitchState switchState = (SwitchState)arrayList.get(b);
/* 621 */         switchState.updateCurrentSwitchOn();
/*     */       } 
/*     */ 
/*     */       
/* 625 */       UpdateTargets updateTargets = (UpdateTargets)paramJ3dMessage.args[0];
/* 626 */       UnorderList unorderList = updateTargets.targetList[6];
/*     */       
/* 628 */       if (unorderList != null) {
/*     */ 
/*     */         
/* 631 */         Object[] arrayOfObject = unorderList.toArray(false);
/* 632 */         int j = unorderList.size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 639 */         for (byte b1 = 0; b1 < j; b1++) {
/* 640 */           Object[] arrayOfObject1 = (Object[])arrayOfObject[b1];
/*     */           
/* 642 */           for (byte b2 = 0; b2 < arrayOfObject1.length; b2++) {
/* 643 */             boolean bool = false;
/* 644 */             TransformGroupRetained transformGroupRetained = (TransformGroupRetained)arrayOfObject1[b2];
/*     */             
/* 646 */             synchronized (transformGroupRetained) {
/* 647 */               if (transformGroupRetained.perPathData != null) {
/* 648 */                 int k = transformGroupRetained.perPathData.length;
/*     */                 
/* 650 */                 for (byte b3 = 0; b3 < k; b3++) {
/* 651 */                   TransformGroupData transformGroupData = transformGroupRetained.perPathData[b3];
/* 652 */                   if (transformGroupData.switchState.currentSwitchOn && transformGroupData.switchDirty) {
/*     */                     
/* 654 */                     if (!bool) {
/*     */                       
/* 656 */                       this.switchDirtyTgList.add(transformGroupRetained);
/* 657 */                       bool = true;
/*     */                     } 
/* 659 */                     transformGroupData.switchDirty = false;
/* 660 */                     transformGroupData.markedDirty = true;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 670 */       this.switchChangedList.addAll(arrayList);
/*     */       
/* 672 */       if (!this.inUpdateObjectList) {
/* 673 */         VirtualUniverse.mc.addMirrorObject(this);
/* 674 */         this.inUpdateObjectList = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 680 */   UpdateTargets getTargetList() { return this.targets; }
/*     */ 
/*     */ 
/*     */   
/* 684 */   ArrayList getBlUsers() { return this.blUsers; }
/*     */ 
/*     */ 
/*     */   
/* 688 */   boolean getLazyUpdate() { return this.lazyUpdate; }
/*     */ 
/*     */   
/*     */   void removeNodes(J3dMessage paramJ3dMessage) {
/* 692 */     if (paramJ3dMessage.args[true] != null) {
/* 693 */       TargetsInterface targetsInterface = (TargetsInterface)paramJ3dMessage.args[1];
/* 694 */       targetsInterface.updateCachedTargets(0, (CachedTargets[])paramJ3dMessage.args[2]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void cleanup() {}
/*     */ 
/*     */   
/*     */   private class TransformData
/*     */   {
/*     */     private TransformGroupRetained transformGroupRetained;
/*     */     
/*     */     private Transform3D transform3D;
/*     */ 
/*     */     
/*     */     TransformData(TransformGroupRetained param1TransformGroupRetained, Transform3D param1Transform3D) {
/* 710 */       this.transformGroupRetained = param1TransformGroupRetained;
/* 711 */       this.transform3D = param1Transform3D;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 717 */     public int hashCode() { return this.transformGroupRetained.hashCode(); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object param1Object) {
/* 723 */       if (!(param1Object instanceof TransformData)) {
/* 724 */         return false;
/*     */       }
/*     */       
/* 727 */       return this.transformGroupRetained.equals(((TransformData)param1Object).getTransformGroupRetained());
/*     */     }
/*     */ 
/*     */     
/* 731 */     TransformGroupRetained getTransformGroupRetained() { return this.transformGroupRetained; }
/*     */ 
/*     */ 
/*     */     
/* 735 */     Transform3D getTransform3D() { return this.transform3D; }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\TransformStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */