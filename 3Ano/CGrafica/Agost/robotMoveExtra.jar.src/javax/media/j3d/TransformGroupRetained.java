/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class TransformGroupRetained
/*      */   extends GroupRetained
/*      */   implements TargetsInterface
/*      */ {
/*   27 */   Transform3D transform = new Transform3D();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   32 */   Transform3D invTransform = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   37 */   Transform3D normalTransform = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   42 */   Transform3D currentTransform = new Transform3D();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   47 */   Transform3D[][] childLocalToVworld = (Transform3D[][])null;
/*   48 */   int[][] childLocalToVworldIndex = (int[][])null;
/*      */ 
/*      */   
/*   51 */   Transform3D[][] childTrans = (Transform3D[][])null;
/*   52 */   int[][] childTransIndex = (int[][])null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   58 */   int localTargetThreads = 0;
/*      */ 
/*      */   
/*   61 */   int targetThreads = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   66 */   WakeupIndexedList transformChange = null;
/*      */ 
/*      */ 
/*      */   
/*   70 */   ArrayList childTransformLinks = new ArrayList(1);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean needNormalsTransform = false;
/*      */ 
/*      */ 
/*      */   
/*   79 */   HashKey currentKey = new HashKey();
/*      */ 
/*      */   
/*      */   boolean aboveAViewPlatform = false;
/*      */   
/*   84 */   int maxTransformLevel = -1;
/*      */ 
/*      */   
/*   87 */   int[] transformLevels = null;
/*      */ 
/*      */   
/*   90 */   CachedTargets[] j3dCTs = null;
/*      */ 
/*      */   
/*   93 */   CachedTargets[] cachedTargets = null;
/*      */ 
/*      */   
/*   96 */   TransformGroupData[] perPathData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTransform(Transform3D paramTransform3D) {
/*  112 */     J3dMessage j3dMessage = null;
/*      */     
/*  114 */     Transform3D transform3D = null;
/*      */     
/*  116 */     if (this.staticTransform != null) {
/*      */ 
/*      */ 
/*      */       
/*  120 */       transform3D = new Transform3D(this.staticTransform.transform);
/*  121 */       transform3D.mul(paramTransform3D);
/*      */       
/*  123 */       this.transform.setWithLock(transform3D);
/*      */     } else {
/*      */       
/*  126 */       transform3D = new Transform3D(paramTransform3D);
/*  127 */       this.transform.setWithLock(paramTransform3D);
/*      */     } 
/*      */     
/*  130 */     if (this.transformChange != null) {
/*  131 */       notifyConditions();
/*      */     }
/*      */     
/*  134 */     if (this.source.isLive()) {
/*      */       
/*  136 */       if (this.aboveAViewPlatform && !paramTransform3D.isCongruent()) {
/*  137 */         throw new BadTransformException(J3dI18N.getString("ViewPlatformRetained0"));
/*      */       }
/*      */       
/*  140 */       j3dMessage = new J3dMessage();
/*  141 */       j3dMessage.type = 3;
/*  142 */       j3dMessage.threads = this.targetThreads;
/*  143 */       j3dMessage.args[1] = this;
/*  144 */       j3dMessage.args[2] = transform3D;
/*      */       
/*  146 */       j3dMessage.universe = this.universe;
/*      */       
/*  148 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*  150 */     dirtyBoundsCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getTransform(Transform3D paramTransform3D) {
/*  159 */     this.transform.getWithLock(paramTransform3D);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  164 */     if (this.staticTransform != null) {
/*  165 */       Transform3D transform3D = this.staticTransform.getInvTransform();
/*  166 */       paramTransform3D.mul(transform3D, paramTransform3D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Transform3D getInvTransform() {
/*  175 */     if (this.invTransform == null) {
/*  176 */       this.invTransform = new Transform3D(this.transform);
/*  177 */       this.invTransform.invert();
/*      */     } 
/*  179 */     return this.invTransform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Transform3D getNormalTransform() {
/*  187 */     if (this.normalTransform == null) {
/*  188 */       this.normalTransform = new Transform3D(this.transform);
/*  189 */       this.normalTransform.invert();
/*  190 */       this.normalTransform.transpose();
/*      */     } 
/*  192 */     return this.normalTransform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setNodeData(SetLiveState paramSetLiveState) {
/*  199 */     super.setNodeData(paramSetLiveState);
/*      */     
/*  201 */     this.childTrans = new Transform3D[paramSetLiveState.currentTransforms.length][2];
/*  202 */     this.childTransIndex = new int[paramSetLiveState.currentTransforms.length][2];
/*      */     int i;
/*  204 */     for (i = 0; i < paramSetLiveState.currentTransforms.length; i++) {
/*  205 */       this.childTrans[i][0] = new Transform3D();
/*      */       
/*  207 */       this.childTrans[i][0].mul(paramSetLiveState.currentTransforms[i][paramSetLiveState.currentTransformsIndex[i][1]], this.currentTransform);
/*      */ 
/*      */       
/*  210 */       this.childTrans[i][1] = new Transform3D(this.childTrans[i][0]);
/*  211 */       this.childTransIndex[i][0] = 0;
/*  212 */       this.childTransIndex[i][1] = 0;
/*      */     } 
/*      */ 
/*      */     
/*  216 */     if (!paramSetLiveState.inSharedGroup) {
/*  217 */       paramSetLiveState.transformLevels[0] = paramSetLiveState.transformLevels[0] + 1;
/*  218 */       this.maxTransformLevel = paramSetLiveState.transformLevels[0];
/*      */     } else {
/*  220 */       for (i = 0; i < paramSetLiveState.keys.length; i++) {
/*  221 */         paramSetLiveState.transformLevels[i] = paramSetLiveState.transformLevels[i] + 1;
/*  222 */         if (paramSetLiveState.transformLevels[i] > this.maxTransformLevel) {
/*  223 */           this.maxTransformLevel = paramSetLiveState.transformLevels[i];
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  228 */     if (!this.inSharedGroup) {
/*  229 */       if (this.childLocalToVworld == null) {
/*      */ 
/*      */         
/*  232 */         this.childLocalToVworld = new Transform3D[1][];
/*  233 */         this.childLocalToVworldIndex = new int[1][];
/*  234 */         this.transformLevels = new int[1];
/*      */         
/*  236 */         this.cachedTargets = new CachedTargets[1];
/*  237 */         this.perPathData = new TransformGroupData[1];
/*      */       } 
/*  239 */       this.childLocalToVworld[0] = this.childTrans[0];
/*  240 */       this.childLocalToVworldIndex[0] = this.childTransIndex[0];
/*  241 */       this.transformLevels[0] = paramSetLiveState.transformLevels[0];
/*      */       
/*  243 */       setAuxData(paramSetLiveState, 0, 0);
/*      */     } else {
/*      */       int j;
/*      */ 
/*      */ 
/*      */       
/*  249 */       if (this.childLocalToVworld == null) {
/*  250 */         this.childLocalToVworld = new Transform3D[paramSetLiveState.keys.length][];
/*  251 */         this.childLocalToVworldIndex = new int[paramSetLiveState.keys.length][];
/*  252 */         this.transformLevels = new int[paramSetLiveState.keys.length];
/*  253 */         this.cachedTargets = new CachedTargets[paramSetLiveState.keys.length];
/*  254 */         this.perPathData = new TransformGroupData[paramSetLiveState.keys.length];
/*  255 */         j = 0;
/*      */       } else {
/*      */         
/*  258 */         j = this.localToVworld.length - paramSetLiveState.keys.length;
/*      */         
/*  260 */         int k = this.localToVworld.length;
/*      */         
/*  262 */         Transform3D[][] arrayOfTransform3D = new Transform3D[k][];
/*  263 */         int[][] arrayOfInt = new int[k][];
/*  264 */         int[] arrayOfInt1 = new int[k];
/*  265 */         CachedTargets[] arrayOfCachedTargets = new CachedTargets[k];
/*  266 */         TransformGroupData[] arrayOfTransformGroupData = new TransformGroupData[k];
/*      */         
/*  268 */         System.arraycopy(this.childLocalToVworld, 0, arrayOfTransform3D, 0, this.childLocalToVworld.length);
/*      */         
/*  270 */         System.arraycopy(this.childLocalToVworldIndex, 0, arrayOfInt, 0, this.childLocalToVworldIndex.length);
/*      */         
/*  272 */         System.arraycopy(this.transformLevels, 0, arrayOfInt1, 0, this.transformLevels.length);
/*      */ 
/*      */         
/*  275 */         System.arraycopy(this.cachedTargets, 0, arrayOfCachedTargets, 0, this.cachedTargets.length);
/*      */ 
/*      */         
/*  278 */         System.arraycopy(this.perPathData, 0, arrayOfTransformGroupData, 0, this.perPathData.length);
/*      */ 
/*      */         
/*  281 */         this.childLocalToVworld = arrayOfTransform3D;
/*  282 */         this.childLocalToVworldIndex = arrayOfInt;
/*  283 */         this.transformLevels = arrayOfInt1;
/*  284 */         this.cachedTargets = arrayOfCachedTargets;
/*  285 */         this.perPathData = arrayOfTransformGroupData;
/*      */       } 
/*      */ 
/*      */       
/*      */       byte b;
/*      */       
/*  291 */       for (i = j, b = 0; i < this.localToVworld.length; i++, b++) {
/*  292 */         int k = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */ 
/*      */         
/*  295 */         if (k < 0) {
/*  296 */           MasterControl.getCoreLogger().severe("Can't Find matching hashKey in setNodeData."); break;
/*      */         } 
/*  298 */         if (k >= i) {
/*  299 */           this.childLocalToVworld[i] = this.childTrans[b];
/*  300 */           this.childLocalToVworldIndex[i] = this.childTransIndex[b];
/*  301 */           this.transformLevels[i] = paramSetLiveState.transformLevels[b];
/*      */         } else {
/*  303 */           int m = k + 1;
/*  304 */           int n = i - k;
/*      */           
/*  306 */           System.arraycopy(this.childLocalToVworld, k, this.childLocalToVworld, m, n);
/*      */ 
/*      */           
/*  309 */           System.arraycopy(this.childLocalToVworldIndex, k, this.childLocalToVworldIndex, m, n);
/*      */ 
/*      */           
/*  312 */           System.arraycopy(this.transformLevels, k, this.transformLevels, m, n);
/*      */ 
/*      */           
/*  315 */           System.arraycopy(this.cachedTargets, k, this.cachedTargets, m, n);
/*      */ 
/*      */           
/*  318 */           System.arraycopy(this.perPathData, k, this.perPathData, m, n);
/*      */ 
/*      */           
/*  321 */           this.childLocalToVworld[k] = this.childTrans[b];
/*  322 */           this.childLocalToVworldIndex[k] = this.childTransIndex[b];
/*  323 */           this.transformLevels[k] = paramSetLiveState.transformLevels[b];
/*      */         } 
/*      */         
/*  326 */         setAuxData(paramSetLiveState, b, k);
/*      */       } 
/*      */     } 
/*  329 */     if (paramSetLiveState.childTransformLinks != null)
/*      */     {
/*  331 */       synchronized (paramSetLiveState.childTransformLinks) {
/*  332 */         if (!this.inSharedGroup || !paramSetLiveState.childTransformLinks.contains(this)) {
/*  333 */           paramSetLiveState.childTransformLinks.add(this);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  338 */     paramSetLiveState.localToVworld = this.childLocalToVworld;
/*  339 */     paramSetLiveState.localToVworldIndex = this.childLocalToVworldIndex;
/*  340 */     paramSetLiveState.currentTransforms = this.childTrans;
/*  341 */     paramSetLiveState.currentTransformsIndex = this.childTransIndex;
/*      */     
/*  343 */     paramSetLiveState.childTransformLinks = this.childTransformLinks;
/*  344 */     paramSetLiveState.parentTransformLink = this;
/*      */   }
/*      */   
/*      */   void setAuxData(SetLiveState paramSetLiveState, int paramInt1, int paramInt2) {
/*  348 */     super.setAuxData(paramSetLiveState, paramInt1, paramInt2);
/*  349 */     this.perPathData[paramInt2] = new TransformGroupData();
/*  350 */     (this.perPathData[paramInt2]).switchState = (SwitchState)paramSetLiveState.switchStates.get(paramInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeCondition(WakeupOnTransformChange paramWakeupOnTransformChange) {
/*  357 */     synchronized (this.transformChange) {
/*  358 */       this.transformChange.remove(paramWakeupOnTransformChange);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void addCondition(WakeupOnTransformChange paramWakeupOnTransformChange) {
/*  364 */     synchronized (this.transformChange) {
/*  365 */       this.transformChange.add(paramWakeupOnTransformChange);
/*      */     } 
/*      */   }
/*      */   
/*      */   void notifyConditions() {
/*  370 */     synchronized (this.transformChange) {
/*  371 */       WakeupOnTransformChange[] arrayOfWakeupOnTransformChange = (WakeupOnTransformChange[])this.transformChange.toArray(false);
/*      */       
/*  373 */       for (int i = this.transformChange.size() - 1; i >= 0; i--) {
/*  374 */         arrayOfWakeupOnTransformChange[i].setTriggered();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean isStatic() {
/*  380 */     if (!super.isStatic() || this.source.getCapability(17) || this.source.getCapability(18))
/*      */     {
/*      */       
/*  383 */       return false;
/*      */     }
/*  385 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/*  390 */     super.mergeTransform(paramTransformGroupRetained);
/*  391 */     this.transform.mul(paramTransformGroupRetained.transform, this.transform);
/*      */   }
/*      */ 
/*      */   
/*      */   void traverse(boolean paramBoolean, int paramInt) {
/*  396 */     System.err.println();
/*  397 */     for (byte b = 0; b < paramInt; b++) {
/*  398 */       System.err.print(".");
/*      */     }
/*  400 */     System.err.print(this);
/*      */     
/*  402 */     if (isStatic()) {
/*  403 */       System.err.print(" (s)");
/*      */     } else {
/*  405 */       System.err.print(" (w)");
/*      */     } 
/*  407 */     System.err.println();
/*  408 */     System.err.println(this.transform.toString());
/*  409 */     super.traverse(true, paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void compile(CompileState paramCompileState) {
/*  416 */     boolean bool1 = paramCompileState.keepTG;
/*  417 */     paramCompileState.keepTG = false;
/*      */     
/*  419 */     boolean bool2 = paramCompileState.needNormalsTransform;
/*  420 */     paramCompileState.needNormalsTransform = false;
/*      */     
/*  422 */     super.compile(paramCompileState);
/*      */     
/*  424 */     if (paramCompileState.keepTG)
/*      */     {
/*      */       
/*  427 */       this.mergeFlag = 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  438 */     if (this.mergeFlag == 0) {
/*      */ 
/*      */       
/*  441 */       paramCompileState.staticTransform = null;
/*  442 */       paramCompileState.parentGroup = null;
/*  443 */       super.merge(paramCompileState);
/*      */     }
/*      */     else {
/*      */       
/*  447 */       this.mergeFlag = 1;
/*      */     } 
/*      */ 
/*      */     
/*  451 */     paramCompileState.keepTG = bool1;
/*  452 */     this.needNormalsTransform = paramCompileState.needNormalsTransform;
/*  453 */     paramCompileState.needNormalsTransform = bool2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void merge(CompileState paramCompileState) {
/*  461 */     if (paramCompileState.staticTransform != null) {
/*  462 */       this.staticTransform = paramCompileState.staticTransform;
/*  463 */       mergeTransform(paramCompileState.staticTransform);
/*      */     } 
/*      */     
/*  466 */     if (this.mergeFlag == 1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  474 */       if (this.needNormalsTransform) {
/*  475 */         Transform3D transform3D = getNormalTransform();
/*  476 */         if (!transform3D.isCongruent()) {
/*  477 */           this.mergeFlag = 0;
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  482 */     if (this.mergeFlag == 1) {
/*  483 */       TransformGroupRetained transformGroupRetained = paramCompileState.staticTransform;
/*  484 */       paramCompileState.staticTransform = this;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  489 */       super.merge(paramCompileState);
/*      */ 
/*      */       
/*  492 */       paramCompileState.staticTransform = transformGroupRetained;
/*      */     } else {
/*      */       
/*  495 */       paramCompileState.parentGroup.compiledChildrenList.add(this);
/*  496 */       this.parent = paramCompileState.parentGroup;
/*      */     } 
/*      */     
/*  499 */     this.mergeFlag = 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(SetLiveState paramSetLiveState) {
/*  508 */     Transform3D transform3D = null;
/*  509 */     Targets[] arrayOfTargets1 = null;
/*  510 */     Targets[] arrayOfTargets2 = null;
/*  511 */     int i = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  521 */     i = paramSetLiveState.traverseFlags;
/*      */     
/*  523 */     arrayOfTargets2 = paramSetLiveState.transformTargets;
/*      */     
/*  525 */     int j = paramSetLiveState.inSharedGroup ? paramSetLiveState.keys.length : 1;
/*  526 */     arrayOfTargets1 = new Targets[j]; byte b;
/*  527 */     for (b = 0; b < j; b++) {
/*  528 */       arrayOfTargets1[b] = new Targets();
/*      */     }
/*      */     
/*  531 */     paramSetLiveState.transformTargets = arrayOfTargets1;
/*  532 */     paramSetLiveState.traverseFlags = 0;
/*      */ 
/*      */     
/*  535 */     this.inSharedGroup = paramSetLiveState.inSharedGroup;
/*      */     
/*  537 */     transform3D = new Transform3D();
/*  538 */     this.transform.getWithLock(transform3D);
/*  539 */     this.currentTransform.set(transform3D);
/*      */ 
/*      */     
/*  542 */     ArrayList arrayList = paramSetLiveState.childTransformLinks;
/*  543 */     GroupRetained groupRetained = paramSetLiveState.parentTransformLink;
/*  544 */     Transform3D[][] arrayOfTransform3D = paramSetLiveState.currentTransforms;
/*  545 */     int[][] arrayOfInt = paramSetLiveState.currentTransformsIndex;
/*      */ 
/*      */     
/*  548 */     doSetLive(paramSetLiveState);
/*      */ 
/*      */     
/*  551 */     if (!this.inSharedGroup) {
/*  552 */       if (paramSetLiveState.transformTargets[false] != null) {
/*  553 */         this.cachedTargets[0] = paramSetLiveState.transformTargets[0].snapShotInit();
/*      */       }
/*  555 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */       {
/*  557 */         paramSetLiveState.switchTargets[0].addNode(this, 6);
/*      */       }
/*      */     } else {
/*      */       
/*  561 */       for (b = 0; b < j; b++) {
/*  562 */         if (paramSetLiveState.transformTargets[b] != null) {
/*  563 */           int k = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */           
/*  565 */           this.cachedTargets[k] = paramSetLiveState.transformTargets[b].snapShotInit();
/*      */         } 
/*  567 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b] != null)
/*      */         {
/*  569 */           paramSetLiveState.switchTargets[b].addNode(this, 6);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  575 */     this.j3dCTs = new CachedTargets[this.cachedTargets.length];
/*  576 */     copyCachedTargets(0, this.j3dCTs);
/*      */     
/*  578 */     computeTargetThreads(0, this.cachedTargets);
/*      */ 
/*      */ 
/*      */     
/*  582 */     paramSetLiveState.localToVworld = this.localToVworld;
/*  583 */     paramSetLiveState.localToVworldIndex = this.localToVworldIndex;
/*  584 */     paramSetLiveState.currentTransforms = arrayOfTransform3D;
/*  585 */     paramSetLiveState.currentTransformsIndex = arrayOfInt;
/*      */     
/*  587 */     paramSetLiveState.childTransformLinks = arrayList;
/*  588 */     paramSetLiveState.parentTransformLink = groupRetained;
/*      */     
/*  590 */     paramSetLiveState.transformTargets = arrayOfTargets2;
/*      */     
/*  592 */     if (!paramSetLiveState.inSharedGroup) {
/*  593 */       paramSetLiveState.transformLevels[0] = paramSetLiveState.transformLevels[0] - 1;
/*      */     } else {
/*  595 */       for (b = 0; b < paramSetLiveState.keys.length; b++) {
/*  596 */         paramSetLiveState.transformLevels[b] = paramSetLiveState.transformLevels[b] - 1;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  601 */     if ((paramSetLiveState.traverseFlags & true) != 0) {
/*  602 */       this.aboveAViewPlatform = true;
/*      */     }
/*  604 */     paramSetLiveState.traverseFlags |= i;
/*      */     
/*  606 */     if (this.aboveAViewPlatform && !transform3D.isCongruent()) {
/*  607 */       throw new BadTransformException(J3dI18N.getString("ViewPlatformRetained0"));
/*      */     }
/*      */     
/*  610 */     markAsLive();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeNodeData(SetLiveState paramSetLiveState) {
/*  619 */     synchronized (this) {
/*      */       
/*  621 */       if (this.refCount <= 0) {
/*  622 */         this.childLocalToVworld = (Transform3D[][])null;
/*  623 */         this.childLocalToVworldIndex = (int[][])null;
/*  624 */         this.transformLevels = null;
/*      */         
/*  626 */         this.cachedTargets = null;
/*  627 */         this.perPathData = null;
/*  628 */         this.targetThreads = 0;
/*      */ 
/*      */         
/*  631 */         if (this.parentTransformLink != null) {
/*      */           ArrayList arrayList;
/*  633 */           if (this.parentTransformLink instanceof TransformGroupRetained) {
/*      */             
/*  635 */             arrayList = ((TransformGroupRetained)this.parentTransformLink).childTransformLinks;
/*      */           } else {
/*      */             
/*  638 */             arrayList = ((SharedGroupRetained)this.parentTransformLink).childTransformLinks;
/*      */           } 
/*      */           
/*  641 */           synchronized (arrayList) {
/*  642 */             arrayList.remove(this);
/*      */           } 
/*      */         } 
/*  645 */         this.aboveAViewPlatform = false;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  650 */         int i = this.localToVworld.length - paramSetLiveState.keys.length;
/*      */         
/*  652 */         Transform3D[][] arrayOfTransform3D = new Transform3D[i][];
/*  653 */         int[][] arrayOfInt = new int[i][];
/*  654 */         int[] arrayOfInt1 = new int[i];
/*  655 */         ArrayList[] arrayOfArrayList = new ArrayList[i];
/*  656 */         CachedTargets[] arrayOfCachedTargets = new CachedTargets[i];
/*  657 */         TransformGroupData[] arrayOfTransformGroupData = new TransformGroupData[i];
/*      */         
/*  659 */         int[] arrayOfInt2 = new int[paramSetLiveState.keys.length];
/*  660 */         int j = 0, k = 0;
/*  661 */         boolean bool = false;
/*  662 */         for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/*  663 */           int m = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */           
/*  665 */           arrayOfInt2[b] = m;
/*      */           
/*  667 */           if (m >= 0) {
/*  668 */             bool = true;
/*      */             
/*  670 */             if (m == j) {
/*  671 */               j++;
/*      */             } else {
/*      */               
/*  674 */               int n = m - j;
/*  675 */               System.arraycopy(this.childLocalToVworld, j, arrayOfTransform3D, k, n);
/*      */               
/*  677 */               System.arraycopy(this.childLocalToVworldIndex, j, arrayOfInt, k, n);
/*      */               
/*  679 */               System.arraycopy(this.transformLevels, j, arrayOfInt1, k, n);
/*      */               
/*  681 */               System.arraycopy(this.cachedTargets, j, arrayOfCachedTargets, k, n);
/*  682 */               System.arraycopy(this.perPathData, j, arrayOfTransformGroupData, k, n);
/*      */ 
/*      */               
/*  685 */               j = m + 1;
/*  686 */               k += n;
/*      */             } 
/*      */           } else {
/*      */             
/*  690 */             bool = false;
/*  691 */             MasterControl.getCoreLogger().severe("TG.removeNodeData-Can't find matching hashKey.");
/*      */           } 
/*      */         } 
/*      */         
/*  695 */         if (bool == true && j < this.localToVworld.length) {
/*  696 */           int m = this.localToVworld.length - j;
/*  697 */           System.arraycopy(this.childLocalToVworld, j, arrayOfTransform3D, k, m);
/*      */           
/*  699 */           System.arraycopy(this.childLocalToVworldIndex, j, arrayOfInt, k, m);
/*      */           
/*  701 */           System.arraycopy(this.transformLevels, j, arrayOfInt1, k, m);
/*      */           
/*  703 */           System.arraycopy(this.cachedTargets, j, arrayOfCachedTargets, k, m);
/*  704 */           System.arraycopy(this.perPathData, j, arrayOfTransformGroupData, k, m);
/*      */         } 
/*      */ 
/*      */         
/*  708 */         this.childLocalToVworld = arrayOfTransform3D;
/*  709 */         this.childLocalToVworldIndex = arrayOfInt;
/*  710 */         this.transformLevels = arrayOfInt1;
/*  711 */         this.cachedTargets = arrayOfCachedTargets;
/*  712 */         this.perPathData = arrayOfTransformGroupData;
/*      */       } 
/*  714 */       super.removeNodeData(paramSetLiveState);
/*      */ 
/*      */       
/*  717 */       paramSetLiveState.localToVworld = this.childLocalToVworld;
/*  718 */       paramSetLiveState.localToVworldIndex = this.childLocalToVworldIndex;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void clearLive(SetLiveState paramSetLiveState) {
/*  724 */     Targets[] arrayOfTargets = null;
/*      */     
/*  726 */     arrayOfTargets = paramSetLiveState.transformTargets;
/*      */     
/*  728 */     paramSetLiveState.transformTargets = null;
/*      */     
/*  730 */     super.clearLive(paramSetLiveState);
/*      */ 
/*      */ 
/*      */     
/*  734 */     paramSetLiveState.localToVworld = this.localToVworld;
/*  735 */     paramSetLiveState.localToVworldIndex = this.localToVworldIndex;
/*  736 */     paramSetLiveState.transformTargets = arrayOfTargets;
/*      */     
/*  738 */     synchronized (this) {
/*  739 */       if (this.inSharedGroup) {
/*  740 */         if (this.transformLevels != null) {
/*  741 */           this.maxTransformLevel = this.transformLevels[0];
/*  742 */           for (byte b = 1; b < this.transformLevels.length; b++) {
/*  743 */             if (this.transformLevels[b] > this.maxTransformLevel) {
/*  744 */               this.maxTransformLevel = this.transformLevels[b];
/*      */             }
/*      */           } 
/*      */         } else {
/*  748 */           this.maxTransformLevel = -1;
/*      */         } 
/*      */         
/*  751 */         if (paramSetLiveState.switchTargets != null) {
/*  752 */           for (byte b = 0; b < paramSetLiveState.switchTargets.length; b++) {
/*  753 */             if (paramSetLiveState.switchTargets[b] != null) {
/*  754 */               paramSetLiveState.switchTargets[b].addNode(this, 6);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } else {
/*      */         
/*  760 */         this.maxTransformLevel = -1;
/*  761 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */         {
/*  763 */           paramSetLiveState.switchTargets[0].addNode(this, 6);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void computeCombineBounds(Bounds paramBounds) {
/*  773 */     if (this.cachedBounds != null && this.boundsAutoCompute) {
/*  774 */       Bounds bounds = (Bounds)this.cachedBounds.clone();
/*      */ 
/*      */ 
/*      */       
/*  778 */       synchronized (this.transform) {
/*  779 */         bounds.transform(this.transform);
/*      */       } 
/*  781 */       paramBounds.combine(bounds);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  786 */     BoundingSphere boundingSphere = new BoundingSphere();
/*  787 */     boundingSphere.setRadius(-1.0D);
/*      */     
/*  789 */     if (this.boundsAutoCompute) {
/*  790 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  791 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  792 */         if (nodeRetained != null) {
/*  793 */           nodeRetained.computeCombineBounds(boundingSphere);
/*      */         }
/*      */       } 
/*  796 */       if (VirtualUniverse.mc.cacheAutoComputedBounds) {
/*  797 */         this.cachedBounds = (Bounds)boundingSphere.clone();
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  802 */       synchronized (this.localBounds) {
/*  803 */         boundingSphere.set(this.localBounds);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  810 */     synchronized (this.transform) {
/*  811 */       boundingSphere.transform(this.transform);
/*      */     } 
/*  813 */     paramBounds.combine(boundingSphere);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processChildLocalToVworld(ArrayList paramArrayList1, ArrayList paramArrayList2, UpdateTargets paramUpdateTargets, ArrayList paramArrayList3) {
/*  822 */     synchronized (this) {
/*      */       
/*  824 */       if (this.inSharedGroup) {
/*  825 */         if (this.localToVworldKeys != null) {
/*  826 */           for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  827 */             if ((this.perPathData[b]).markedDirty) {
/*  828 */               updateChildLocalToVworld(this.localToVworldKeys[b], b, paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  838 */       else if (this.perPathData != null && (this.perPathData[0]).markedDirty) {
/*  839 */         updateChildLocalToVworld(paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateChildLocalToVworld(HashKey paramHashKey, int paramInt, ArrayList paramArrayList1, ArrayList paramArrayList2, UpdateTargets paramUpdateTargets, ArrayList paramArrayList3) {
/*  862 */     synchronized (this) {
/*      */       
/*  864 */       if (this.localToVworld != null) {
/*  865 */         (this.perPathData[paramInt]).markedDirty = false;
/*      */ 
/*      */         
/*  868 */         if ((this.perPathData[paramInt]).switchState.currentSwitchOn) {
/*  869 */           Transform3D transform3D1 = getCurrentLocalToVworld(paramInt);
/*  870 */           Transform3D transform3D2 = getUpdateChildLocalToVworld(paramInt);
/*  871 */           transform3D2.mul(transform3D1, this.currentTransform);
/*  872 */           paramArrayList1.add(this);
/*  873 */           paramArrayList2.add(paramHashKey);
/*  874 */           CachedTargets cachedTargets1 = this.j3dCTs[paramInt];
/*  875 */           if (cachedTargets1 != null) {
/*  876 */             paramUpdateTargets.addCachedTargets(cachedTargets1);
/*  877 */             if (cachedTargets1.targetArr[5] != null) {
/*  878 */               gatherBlUsers(paramArrayList3, cachedTargets1.targetArr[5]);
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           
/*  883 */           (this.perPathData[paramInt]).switchDirty = true;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  891 */         synchronized (this.childTransformLinks) {
/*  892 */           for (byte b = 0; b < this.childTransformLinks.size(); b++) {
/*  893 */             Object object = this.childTransformLinks.get(b);
/*      */             
/*  895 */             if (object instanceof TransformGroupRetained) {
/*  896 */               TransformGroupRetained transformGroupRetained = (TransformGroupRetained)object;
/*  897 */               transformGroupRetained.updateChildLocalToVworld(transformGroupRetained.localToVworldKeys[paramInt], paramInt, paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  902 */               LinkRetained linkRetained = (LinkRetained)object;
/*  903 */               this.currentKey.set(this.localToVworldKeys[paramInt]);
/*  904 */               this.currentKey.append(LinkRetained.plus).append(linkRetained.nodeId);
/*  905 */               if (linkRetained.sharedGroup != null && linkRetained.sharedGroup.localToVworldKeys != null) {
/*      */                 
/*  907 */                 int i = this.currentKey.equals(linkRetained.sharedGroup.localToVworldKeys, 0, linkRetained.sharedGroup.localToVworldKeys.length);
/*      */                 
/*  909 */                 if (i < 0) {
/*  910 */                   System.err.println("TransformGroupRetained : Can't find hashKey");
/*      */                 }
/*      */ 
/*      */                 
/*  914 */                 if (i < linkRetained.sharedGroup.localToVworldKeys.length) {
/*  915 */                   linkRetained.sharedGroup.updateChildLocalToVworld(linkRetained.sharedGroup.localToVworldKeys[i], i, paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateChildLocalToVworld(ArrayList paramArrayList1, ArrayList paramArrayList2, UpdateTargets paramUpdateTargets, ArrayList paramArrayList3) {
/*  941 */     synchronized (this) {
/*      */       
/*  943 */       if (this.localToVworld != null) {
/*  944 */         (this.perPathData[0]).markedDirty = false;
/*      */ 
/*      */         
/*  947 */         if ((this.perPathData[0]).switchState.currentSwitchOn) {
/*  948 */           Transform3D transform3D1 = getCurrentLocalToVworld(0);
/*  949 */           Transform3D transform3D2 = getUpdateChildLocalToVworld(0);
/*  950 */           transform3D2.mul(transform3D1, this.currentTransform);
/*  951 */           paramArrayList1.add(this);
/*  952 */           CachedTargets cachedTargets1 = this.j3dCTs[0];
/*  953 */           if (cachedTargets1 != null) {
/*  954 */             paramUpdateTargets.addCachedTargets(cachedTargets1);
/*  955 */             if (cachedTargets1.targetArr[5] != null) {
/*  956 */               gatherBlUsers(paramArrayList3, cachedTargets1.targetArr[5]);
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           
/*  961 */           (this.perPathData[0]).switchDirty = true;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  969 */         synchronized (this.childTransformLinks) {
/*  970 */           for (byte b = 0; b < this.childTransformLinks.size(); b++) {
/*  971 */             Object object = this.childTransformLinks.get(b);
/*      */             
/*  973 */             if (object instanceof TransformGroupRetained) {
/*  974 */               TransformGroupRetained transformGroupRetained = (TransformGroupRetained)object;
/*  975 */               transformGroupRetained.updateChildLocalToVworld(paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*      */             }
/*      */             else {
/*      */               
/*  979 */               LinkRetained linkRetained = (LinkRetained)object;
/*  980 */               this.currentKey.reset();
/*  981 */               this.currentKey.append(this.locale.nodeId);
/*  982 */               this.currentKey.append(LinkRetained.plus).append(linkRetained.nodeId);
/*  983 */               if (linkRetained.sharedGroup != null && linkRetained.sharedGroup.localToVworldKeys != null) {
/*      */                 
/*  985 */                 int i = this.currentKey.equals(linkRetained.sharedGroup.localToVworldKeys, 0, linkRetained.sharedGroup.localToVworldKeys.length);
/*      */                 
/*  987 */                 if (i < 0) {
/*  988 */                   System.err.println("TransformGroupRetained : Can't find hashKey");
/*      */                 }
/*      */ 
/*      */                 
/*  992 */                 if (i < linkRetained.sharedGroup.localToVworldKeys.length) {
/*  993 */                   linkRetained.sharedGroup.updateChildLocalToVworld(linkRetained.sharedGroup.localToVworldKeys[i], i, paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void transformBounds(SceneGraphPath paramSceneGraphPath, Bounds paramBounds) {
/* 1015 */     if (!((NodeRetained)paramSceneGraphPath.item.retained).inSharedGroup) {
/* 1016 */       paramBounds.transform(getCurrentChildLocalToVworld());
/*      */     } else {
/* 1018 */       HashKey hashKey = new HashKey("");
/* 1019 */       paramSceneGraphPath.getHashKey(hashKey);
/* 1020 */       paramBounds.transform(getCurrentChildLocalToVworld(hashKey));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Transform3D getUpdateChildLocalToVworld(int paramInt) {
/* 1029 */     int i = this.childLocalToVworldIndex[paramInt][1];
/*      */     
/* 1031 */     if (i == this.childLocalToVworldIndex[paramInt][0]) {
/* 1032 */       i ^= 0x1;
/* 1033 */       this.childLocalToVworldIndex[paramInt][1] = i;
/*      */     } 
/* 1035 */     return this.childLocalToVworld[paramInt][i];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1043 */   Transform3D getCurrentChildLocalToVworld() { return getCurrentChildLocalToVworld(0); }
/*      */ 
/*      */ 
/*      */   
/* 1047 */   Transform3D getCurrentChildLocalToVworld(int paramInt) { return this.childLocalToVworld[paramInt][this.childLocalToVworldIndex[paramInt][1]]; }
/*      */ 
/*      */   
/*      */   Transform3D getCurrentChildLocalToVworld(HashKey paramHashKey) {
/* 1051 */     if (!this.inSharedGroup) {
/* 1052 */       return this.childLocalToVworld[0][this.childLocalToVworldIndex[0][1]];
/*      */     }
/* 1054 */     int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1055 */     if (i >= 0) {
/* 1056 */       return this.childLocalToVworld[i][this.childLocalToVworldIndex[i][1]];
/*      */     }
/*      */ 
/*      */     
/* 1060 */     return new Transform3D();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Transform3D getLastChildLocalToVworld(HashKey paramHashKey) {
/* 1069 */     if (!this.inSharedGroup) {
/* 1070 */       return this.childLocalToVworld[0][this.childLocalToVworldIndex[0][0]];
/*      */     }
/* 1072 */     int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1073 */     if (i >= 0) {
/* 1074 */       return this.childLocalToVworld[i][this.childLocalToVworldIndex[i][0]];
/*      */     }
/*      */ 
/*      */     
/* 1078 */     return new Transform3D();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTargetThreads(int paramInt) {
/* 1087 */     if (paramInt == 0) {
/* 1088 */       return this.targetThreads;
/*      */     }
/* 1090 */     System.err.println("getTargetsThreads: wrong arguments");
/* 1091 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CachedTargets getCachedTargets(int paramInt1, int paramInt2, int paramInt3) {
/* 1098 */     if (paramInt1 == 0) {
/* 1099 */       return this.cachedTargets[paramInt2];
/*      */     }
/* 1101 */     System.err.println("getCachedTargets: wrong arguments");
/* 1102 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1107 */   TargetsInterface getClosestTargetsInterface(int paramInt) { return (paramInt == 0) ? this : (TargetsInterface)this.parentSwitchLink; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void computeTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 1118 */     if (paramInt == 0) {
/* 1119 */       this.localTargetThreads = 8192;
/*      */       int i;
/* 1121 */       for (i = 0; i < paramArrayOfCachedTargets.length; i++) {
/* 1122 */         if (paramArrayOfCachedTargets[i] != null) {
/* 1123 */           this.localTargetThreads |= paramArrayOfCachedTargets[i].computeTargetThreads();
/*      */         }
/*      */       } 
/* 1126 */       this.targetThreads = this.localTargetThreads;
/*      */       
/* 1128 */       i = this.childTransformLinks.size();
/*      */ 
/*      */       
/* 1131 */       for (byte b = 0; b < i; b++) {
/*      */         TargetsInterface targetsInterface;
/* 1133 */         NodeRetained nodeRetained = (NodeRetained)this.childTransformLinks.get(b);
/* 1134 */         if (nodeRetained.nodeType == 9) {
/* 1135 */           targetsInterface = ((LinkRetained)nodeRetained).sharedGroup;
/*      */         } else {
/*      */           
/* 1138 */           targetsInterface = (TargetsInterface)nodeRetained;
/*      */         } 
/* 1140 */         if (targetsInterface != null) {
/* 1141 */           this.targetThreads |= targetsInterface.getTargetThreads(0);
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 1146 */       System.err.println("computeTargetsThreads: wrong arguments");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 1156 */     if (paramInt == 0) {
/* 1157 */       computeTargetThreads(paramInt, paramArrayOfCachedTargets);
/* 1158 */       if (this.parentTransformLink != null) {
/* 1159 */         TargetsInterface targetsInterface = (TargetsInterface)this.parentTransformLink;
/* 1160 */         targetsInterface.propagateTargetThreads(0, this.targetThreads);
/*      */       } 
/*      */     } else {
/*      */       
/* 1164 */       System.err.println("updateTargetThreads: wrong arguments");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void propagateTargetThreads(int paramInt1, int paramInt2) {
/* 1173 */     if (paramInt1 == 0) {
/*      */ 
/*      */       
/* 1176 */       this.targetThreads |= paramInt2;
/* 1177 */       if (this.parentTransformLink != null) {
/* 1178 */         TargetsInterface targetsInterface = (TargetsInterface)this.parentTransformLink;
/* 1179 */         targetsInterface.propagateTargetThreads(0, this.targetThreads);
/*      */       } 
/*      */     } else {
/*      */       
/* 1183 */       System.err.println("propagateTargetThreads: wrong arguments");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 1189 */     if (paramInt == 0) {
/* 1190 */       this.j3dCTs = paramArrayOfCachedTargets;
/*      */     } else {
/* 1192 */       System.err.println("updateCachedTargets: wrong arguments");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void copyCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 1198 */     if (paramInt == 0) {
/* 1199 */       int i = this.cachedTargets.length;
/* 1200 */       for (byte b = 0; b < i; b++) {
/* 1201 */         paramArrayOfCachedTargets[b] = this.cachedTargets[b];
/*      */       }
/*      */     } else {
/* 1204 */       System.err.println("copyCachedTargets: wrong arguments");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetCachedTargets(int paramInt1, CachedTargets[] paramArrayOfCachedTargets, int paramInt2) {
/* 1212 */     if (paramInt1 == 0) {
/* 1213 */       this.cachedTargets = paramArrayOfCachedTargets;
/*      */     } else {
/* 1215 */       System.err.println("resetCachedTargets: wrong arguments");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1221 */   public ArrayList getTargetsData(int paramInt1, int paramInt2) { return null; }
/*      */ 
/*      */ 
/*      */   
/*      */   void childCheckSetLive(NodeRetained paramNodeRetained1, int paramInt, SetLiveState paramSetLiveState, NodeRetained paramNodeRetained2) {
/* 1226 */     paramSetLiveState.currentTransforms = this.childLocalToVworld;
/* 1227 */     paramSetLiveState.currentTransformsIndex = this.childLocalToVworldIndex;
/* 1228 */     paramSetLiveState.parentTransformLink = this;
/* 1229 */     paramSetLiveState.childTransformLinks = this.childTransformLinks;
/* 1230 */     paramSetLiveState.localToVworld = paramSetLiveState.currentTransforms;
/* 1231 */     paramSetLiveState.localToVworldIndex = paramSetLiveState.currentTransformsIndex;
/*      */     
/* 1233 */     paramNodeRetained1.setLive(paramSetLiveState);
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\TransformGroupRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */