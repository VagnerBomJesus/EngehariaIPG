/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import javax.vecmath.Vector3d;
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
/*      */ 
/*      */ class GeometryStructure
/*      */   extends J3dStructure
/*      */ {
/*   29 */   UpdateTargets targets = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   37 */   private MRSWLock lock = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   42 */   private Object visLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   49 */   private Object collideListLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   57 */   private BHTree[] bhTreeArr = null;
/*      */   private int bhTreeCount;
/*      */   private int bhTreeMax;
/*   60 */   private int bhTreeBlockSize = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   67 */   private BHNode[] bhNodeArr = null;
/*      */   private int bhNodeCount;
/*   69 */   private int bhNodeBlockSize = 50;
/*      */   
/*      */   private int bhNodeMax;
/*   72 */   private Vector3d localeTrans = new Vector3d();
/*      */ 
/*      */   
/*      */   WakeupIndexedList collideEntryList;
/*      */ 
/*      */   
/*      */   WakeupIndexedList collideExitList;
/*      */ 
/*      */   
/*      */   WakeupIndexedList collideMovementList;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnCollisionEntry;
/*      */   
/*      */   WakeupIndexedList wakeupOnCollisionExit;
/*      */   
/*      */   WakeupIndexedList wakeupOnCollisionMovement;
/*      */   
/*      */   boolean reEvaluateWakeupCollisionGAs;
/*      */   
/*      */   private boolean transformMsg = false;
/*      */ 
/*      */   
/*      */   GeometryStructure(VirtualUniverse paramVirtualUniverse) {
/*   96 */     super(paramVirtualUniverse, 64);
/*   97 */     this.bhNodeCount = 0;
/*   98 */     this.bhNodeMax = this.bhNodeBlockSize;
/*   99 */     this.bhNodeArr = new BHNode[this.bhNodeMax];
/*  100 */     this.bhTreeMax = 1;
/*  101 */     this.bhTreeArr = new BHTree[this.bhTreeMax];
/*  102 */     this.bhTreeCount = 0;
/*  103 */     this.lock = new MRSWLock();
/*  104 */     this.collideEntryList = new WakeupIndexedList(WakeupOnCollisionEntry.class, 1, paramVirtualUniverse);
/*      */     
/*  106 */     this.collideExitList = new WakeupIndexedList(WakeupOnCollisionExit.class, 1, paramVirtualUniverse);
/*      */     
/*  108 */     this.collideMovementList = new WakeupIndexedList(WakeupOnCollisionMovement.class, 1, paramVirtualUniverse);
/*      */     
/*  110 */     this.wakeupOnCollisionEntry = new WakeupIndexedList(WakeupOnCollisionEntry.class, 0, paramVirtualUniverse);
/*      */     
/*  112 */     this.wakeupOnCollisionExit = new WakeupIndexedList(WakeupOnCollisionExit.class, 0, paramVirtualUniverse);
/*      */     
/*  114 */     this.wakeupOnCollisionMovement = new WakeupIndexedList(WakeupOnCollisionMovement.class, 0, paramVirtualUniverse);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void processMessages(long paramLong) {
/*  120 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/*  121 */     int i = getNumMessage();
/*      */     
/*  123 */     if (i > 0) {
/*  124 */       this.reEvaluateWakeupCollisionGAs = false;
/*  125 */       for (byte b = 0; b < i; b++) {
/*  126 */         int j; this.lock.writeLock();
/*  127 */         J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/*  128 */         switch (j3dMessage.type) {
/*      */           case 3:
/*  130 */             this.transformMsg = true;
/*      */             break;
/*      */           case 27:
/*  133 */             processSwitchChanged(j3dMessage);
/*      */             
/*  135 */             if (this.universe.transformStructure.getLazyUpdate()) {
/*  136 */               this.transformMsg = true;
/*      */             }
/*      */             break;
/*      */           case 0:
/*  140 */             insertNodes((Object[])j3dMessage.args[0]);
/*  141 */             this.reEvaluateWakeupCollisionGAs = true;
/*      */             break;
/*      */           case 1:
/*  144 */             removeNodes(j3dMessage);
/*  145 */             this.reEvaluateWakeupCollisionGAs = true;
/*      */             break;
/*      */           case 24:
/*  148 */             j = ((Integer)j3dMessage.args[1]).intValue();
/*  149 */             if (j == 1) {
/*  150 */               j3dMessage.args[0] = j3dMessage.args[2];
/*  151 */               removeNodes(j3dMessage);
/*  152 */               insertNodes((Object[])j3dMessage.args[3]);
/*  153 */               this.reEvaluateWakeupCollisionGAs = true; break;
/*      */             } 
/*  155 */             if (j == 2) {
/*  156 */               processVisibleChanged(j3dMessage.args[2], (GeometryAtom[])j3dMessage.args[3]);
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 26:
/*  162 */             removeNodes(j3dMessage);
/*  163 */             insertNodes((Object[])j3dMessage.args[1]);
/*      */             break;
/*      */           case 25:
/*  166 */             processBoundsChanged((Object[])j3dMessage.args[0], false);
/*      */             break;
/*      */           case 16:
/*  169 */             j = ((Integer)j3dMessage.args[1]).intValue();
/*  170 */             if (j == 1) {
/*  171 */               processBoundsChanged((Object[])j3dMessage.args[3], false); break;
/*      */             } 
/*  173 */             if (j == 2) {
/*  174 */               processVisibleChanged(j3dMessage.args[2], (GeometryAtom[])j3dMessage.args[3]);
/*      */             }
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 35:
/*      */           case 37:
/*  182 */             processBoundsChanged((Object[])j3dMessage.args[0], false);
/*      */             break;
/*      */           
/*      */           case 17:
/*  186 */             processBoundsChanged((Object[])j3dMessage.args[0], false);
/*      */             break;
/*      */           case 10:
/*  189 */             processVisibleChanged(j3dMessage.args[2], (GeometryAtom[])j3dMessage.args[3]);
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  194 */         this.lock.writeUnlock();
/*  195 */         j3dMessage.decRefcount();
/*      */       } 
/*      */       
/*  198 */       if (this.transformMsg) {
/*  199 */         this.targets = this.universe.transformStructure.getTargetList();
/*  200 */         this.lock.writeLock();
/*      */         
/*  202 */         processTransformChanged(this.targets);
/*      */         
/*  204 */         this.lock.writeUnlock();
/*      */         
/*  206 */         this.transformMsg = false;
/*  207 */         this.targets = null;
/*      */       } 
/*      */       
/*  210 */       Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*      */     } 
/*      */     
/*  213 */     processCollisionDetection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getBHTreeIndex(Locale paramLocale) {
/*  220 */     for (byte b = 0; b < this.bhTreeCount; b++) {
/*  221 */       if ((this.bhTreeArr[b]).locale == paramLocale) {
/*  222 */         return b;
/*      */       }
/*      */     } 
/*      */     
/*  226 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getOrAddBHTreeIndex(Locale paramLocale) {
/*      */     byte b;
/*  232 */     for (b = 0; b < this.bhTreeCount; b++) {
/*  233 */       if ((this.bhTreeArr[b]).locale == paramLocale) {
/*  234 */         return b;
/*      */       }
/*      */     } 
/*  237 */     if (this.bhTreeCount >= this.bhTreeMax) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  242 */       this.bhTreeMax += this.bhTreeBlockSize;
/*  243 */       BHTree[] arrayOfBHTree = this.bhTreeArr;
/*      */       
/*  245 */       this.bhTreeArr = new BHTree[this.bhTreeMax];
/*  246 */       System.arraycopy(arrayOfBHTree, 0, this.bhTreeArr, 0, arrayOfBHTree.length);
/*      */     } 
/*      */     
/*  249 */     this.bhTreeArr[this.bhTreeCount] = new BHTree(paramLocale);
/*  250 */     this.bhTreeCount++;
/*  251 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void clearBhNodeArr() {
/*  258 */     for (byte b = 0; b < this.bhNodeCount; b++) {
/*  259 */       this.bhNodeArr[b] = null;
/*      */     }
/*      */     
/*  262 */     this.bhNodeCount = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addToBhNodeArr(BHNode paramBHNode) {
/*  268 */     if (this.bhNodeCount >= this.bhNodeMax) {
/*  269 */       this.bhNodeMax += this.bhNodeBlockSize;
/*  270 */       BHNode[] arrayOfBHNode = this.bhNodeArr;
/*      */       
/*  272 */       this.bhNodeArr = new BHNode[this.bhNodeMax];
/*  273 */       System.arraycopy(arrayOfBHNode, 0, this.bhNodeArr, 0, arrayOfBHNode.length);
/*      */     } 
/*      */     
/*  276 */     this.bhNodeArr[this.bhNodeCount] = paramBHNode;
/*  277 */     this.bhNodeCount++;
/*      */   }
/*      */   
/*      */   private void processVisibleChanged(Object paramObject, GeometryAtom[] paramArrayOfGeometryAtom) {
/*  281 */     boolean bool = true;
/*      */ 
/*      */     
/*  284 */     if (paramArrayOfGeometryAtom == null || paramArrayOfGeometryAtom.length < 1) {
/*      */       return;
/*      */     }
/*  287 */     int j = getBHTreeIndex((paramArrayOfGeometryAtom[0]).locale);
/*      */     
/*  289 */     bool = ((Boolean)paramObject).booleanValue();
/*      */     
/*  291 */     for (int i = paramArrayOfGeometryAtom.length - 1; i >= 0; i--) {
/*  292 */       (paramArrayOfGeometryAtom[i]).visible = bool;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void insertNodes(Object[] paramArrayOfObject) {
/*  300 */     BHTree bHTree = null;
/*      */     
/*  302 */     clearBhNodeArr();
/*      */     
/*      */     int i;
/*      */     
/*  306 */     for (i = 0; i < paramArrayOfObject.length; i++) {
/*  307 */       Object object = paramArrayOfObject[i];
/*  308 */       if (object instanceof GeometryAtom) {
/*  309 */         synchronized (object) {
/*  310 */           GeometryAtom geometryAtom = (GeometryAtom)object;
/*  311 */           if (geometryAtom.source.inBackgroundGroup)
/*  312 */           { geometryAtom.source.geometryBackground.addBgGeometryAtomList(geometryAtom); }
/*      */           
/*      */           else
/*      */           
/*  316 */           { BHLeafNode bHLeafNode = new BHLeafNode();
/*  317 */             bHLeafNode.leafIF = geometryAtom;
/*  318 */             geometryAtom.bhLeafNode = bHLeafNode;
/*  319 */             bHLeafNode.computeBoundingHull();
/*      */             
/*  321 */             addToBhNodeArr(bHLeafNode); } 
/*      */         } 
/*  323 */       } else if (object instanceof GroupRetained) {
/*  324 */         synchronized (object) {
/*  325 */           GroupRetained groupRetained = (GroupRetained)object;
/*  326 */           BHLeafNode bHLeafNode = new BHLeafNode();
/*  327 */           bHLeafNode.leafIF = groupRetained;
/*  328 */           groupRetained.bhLeafNode = bHLeafNode;
/*  329 */           bHLeafNode.computeBoundingHull();
/*  330 */           addToBhNodeArr(bHLeafNode);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  335 */     if (this.bhNodeCount < 1) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  340 */     if (bHTree == null) {
/*      */ 
/*      */ 
/*      */       
/*  344 */       i = getOrAddBHTreeIndex(((BHLeafNode)this.bhNodeArr[0]).getLocale());
/*  345 */       bHTree = this.bhTreeArr[i];
/*      */     } 
/*      */ 
/*      */     
/*  349 */     bHTree.insert(this.bhNodeArr, this.bhNodeCount);
/*      */ 
/*      */     
/*  352 */     clearBhNodeArr();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeNodes(J3dMessage paramJ3dMessage) {
/*  358 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/*  359 */     BHTree bHTree = null;
/*      */ 
/*      */ 
/*      */     
/*  363 */     clearBhNodeArr();
/*      */     
/*  365 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/*  366 */       Object object = arrayOfObject[b];
/*  367 */       if (object instanceof GeometryAtom) {
/*  368 */         synchronized (object) {
/*  369 */           GeometryAtom geometryAtom = (GeometryAtom)object;
/*  370 */           if (geometryAtom.source != null && geometryAtom.source.inBackgroundGroup) {
/*      */             
/*  372 */             geometryAtom.source.geometryBackground.removeBgGeometryAtomList(geometryAtom);
/*      */ 
/*      */           
/*      */           }
/*  376 */           else if (geometryAtom.bhLeafNode != null) {
/*  377 */             addToBhNodeArr(geometryAtom.bhLeafNode);
/*      */             
/*  379 */             geometryAtom.bhLeafNode = null;
/*      */           }
/*      */         
/*      */         } 
/*  383 */       } else if (object instanceof GroupRetained) {
/*  384 */         if (((NodeRetained)object).nodeType != 20) {
/*  385 */           synchronized (object) {
/*  386 */             GroupRetained groupRetained = (GroupRetained)object;
/*  387 */             if (groupRetained.bhLeafNode != null) {
/*  388 */               addToBhNodeArr(groupRetained.bhLeafNode);
/*      */               
/*  390 */               groupRetained.bhLeafNode = null;
/*      */             } 
/*      */           } 
/*      */         }
/*  394 */       } else if (object instanceof BehaviorRetained) {
/*  395 */         synchronized (object) {
/*  396 */           BehaviorRetained behaviorRetained = (BehaviorRetained)object;
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
/*  408 */           WakeupOnCollisionEntry[] arrayOfWakeupOnCollisionEntry = (WakeupOnCollisionEntry[])this.collideEntryList.toArray();
/*      */           
/*  410 */           for (int i = this.collideEntryList.arraySize() - 1; i >= 0; i--) {
/*  411 */             WakeupOnCollisionEntry wakeupOnCollisionEntry1 = arrayOfWakeupOnCollisionEntry[i];
/*  412 */             if (wakeupOnCollisionEntry1.behav == behaviorRetained) {
/*  413 */               this.collideEntryList.remove(wakeupOnCollisionEntry1);
/*      */             }
/*      */           } 
/*      */           
/*  417 */           WakeupOnCollisionExit[] arrayOfWakeupOnCollisionExit = (WakeupOnCollisionExit[])this.collideExitList.toArray();
/*      */           
/*  419 */           for (int j = this.collideExitList.arraySize() - 1; j >= 0; j--) {
/*  420 */             WakeupOnCollisionExit wakeupOnCollisionExit1 = arrayOfWakeupOnCollisionExit[j];
/*  421 */             if (wakeupOnCollisionExit1.behav == behaviorRetained) {
/*  422 */               this.collideExitList.remove(wakeupOnCollisionExit1);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  429 */     if (this.bhNodeCount < 1) {
/*      */       return;
/*      */     }
/*      */     
/*  433 */     if (bHTree == null) {
/*  434 */       int i = getBHTreeIndex(((BHLeafNode)this.bhNodeArr[0]).getLocale());
/*  435 */       if (i < 0) {
/*      */         
/*  437 */         clearBhNodeArr();
/*      */         
/*      */         return;
/*      */       } 
/*  441 */       bHTree = this.bhTreeArr[i];
/*      */     } 
/*  443 */     bHTree.delete(this.bhNodeArr, this.bhNodeCount);
/*      */ 
/*      */     
/*  446 */     clearBhNodeArr();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  451 */     this.wakeupOnCollisionEntry.clearMirror();
/*  452 */     this.wakeupOnCollisionMovement.clearMirror();
/*  453 */     this.wakeupOnCollisionExit.clearMirror();
/*      */     
/*  455 */     synchronized (this.collideListLock) {
/*  456 */       this.collideEntryList.clearMirror();
/*  457 */       this.collideExitList.clearMirror();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processBoundsChanged(Object[] paramArrayOfObject, boolean paramBoolean) {
/*  467 */     clearBhNodeArr();
/*      */     
/*  469 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/*  470 */       Object object = paramArrayOfObject[b];
/*  471 */       if (object instanceof GeometryAtom) {
/*  472 */         synchronized (object) {
/*      */           
/*  474 */           GeometryAtom geometryAtom = (GeometryAtom)object;
/*  475 */           if (geometryAtom.bhLeafNode != null) {
/*  476 */             addToBhNodeArr(geometryAtom.bhLeafNode);
/*      */           }
/*      */         } 
/*  479 */       } else if (object instanceof GroupRetained) {
/*      */         
/*  481 */         GroupRetained groupRetained = (GroupRetained)object;
/*  482 */         if (groupRetained.nodeType != 18) {
/*  483 */           synchronized (object) {
/*  484 */             if (groupRetained.bhLeafNode != null) {
/*  485 */               addToBhNodeArr(groupRetained.bhLeafNode);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  492 */     if (this.bhNodeCount < 1) {
/*      */       return;
/*      */     }
/*      */     
/*  496 */     int i = getBHTreeIndex(((BHLeafNode)this.bhNodeArr[0]).getLocale());
/*      */     
/*  498 */     if (i >= 0) {
/*  499 */       this.bhTreeArr[i].boundsChanged(this.bhNodeArr, this.bhNodeCount);
/*      */     }
/*      */ 
/*      */     
/*  503 */     clearBhNodeArr();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processTransformChanged(UpdateTargets paramUpdateTargets) {
/*  514 */     clearBhNodeArr();
/*      */     
/*  516 */     UnorderList unorderList = paramUpdateTargets.targetList[0];
/*      */     
/*  518 */     if (unorderList != null) {
/*  519 */       int j = unorderList.size();
/*  520 */       Object[] arrayOfObject = unorderList.toArray(false);
/*      */       
/*  522 */       for (byte b = 0; b < j; b++) {
/*  523 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/*  524 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*  525 */           GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject1[b1];
/*  526 */           synchronized (geometryAtom) {
/*  527 */             if (geometryAtom.bhLeafNode != null) {
/*  528 */               addToBhNodeArr(geometryAtom.bhLeafNode);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  536 */     unorderList = paramUpdateTargets.targetList[6];
/*  537 */     if (unorderList != null) {
/*  538 */       int j = unorderList.size();
/*  539 */       Object[] arrayOfObject = unorderList.toArray(false);
/*  540 */       for (byte b = 0; b < j; b++) {
/*  541 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/*  542 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*  543 */           GroupRetained groupRetained = (GroupRetained)arrayOfObject1[b1];
/*  544 */           if (groupRetained.nodeType != 18) {
/*  545 */             synchronized (groupRetained) {
/*  546 */               if (groupRetained.bhLeafNode != null) {
/*  547 */                 addToBhNodeArr(groupRetained.bhLeafNode);
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  555 */     if (this.bhNodeCount < 1) {
/*      */       return;
/*      */     }
/*      */     
/*  559 */     int i = getBHTreeIndex(((BHLeafNode)this.bhNodeArr[0]).getLocale());
/*      */     
/*  561 */     if (i >= 0) {
/*  562 */       this.bhTreeArr[i].boundsChanged(this.bhNodeArr, this.bhNodeCount);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  567 */     clearBhNodeArr();
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
/*      */   boolean getVisibleBHTrees(RenderBin paramRenderBin, BoundingBox paramBoundingBox, Locale paramLocale, long paramLong, boolean paramBoolean, int paramInt) {
/*  583 */     boolean bool = true;
/*      */ 
/*      */     
/*  586 */     this.lock.readLock();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  591 */     ArrayList arrayList = new ArrayList();
/*  592 */     if (this.bhTreeCount == 1) {
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
/*  604 */       synchronized (this.visLock) {
/*  605 */         bool = this.bhTreeArr[0].getVisibleBHTrees(paramRenderBin, arrayList, paramBoundingBox, paramLong, paramBoolean, paramInt, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  621 */       BoundingBox boundingBox = new BoundingBox();
/*      */       
/*  623 */       synchronized (this.visLock) {
/*      */         
/*  625 */         for (byte b = 0; b < this.bhTreeCount; b++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  632 */           if (!paramLocale.hiRes.equals((this.bhTreeArr[b]).locale.hiRes)) {
/*  633 */             (this.bhTreeArr[b]).locale.hiRes.difference(paramLocale.hiRes, this.localeTrans);
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
/*  644 */             paramBoundingBox.lower.x += this.localeTrans.x;
/*  645 */             paramBoundingBox.lower.y += this.localeTrans.y;
/*  646 */             paramBoundingBox.lower.z += this.localeTrans.z;
/*  647 */             paramBoundingBox.upper.x += this.localeTrans.x;
/*  648 */             paramBoundingBox.upper.y += this.localeTrans.y;
/*  649 */             paramBoundingBox.upper.z += this.localeTrans.z;
/*      */           } else {
/*      */             
/*  652 */             paramBoundingBox.copy(boundingBox);
/*      */           } 
/*      */           
/*  655 */           if (!this.bhTreeArr[b].getVisibleBHTrees(paramRenderBin, arrayList, boundingBox, paramLong, paramBoolean, paramInt, false))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  661 */             bool = false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  667 */     this.lock.readUnlock();
/*  668 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   GeometryAtom[] pickAll(Locale paramLocale, PickShape paramPickShape) {
/*  674 */     UnorderList unorderList = new UnorderList(BHNode.class);
/*  675 */     unorderList.clear();
/*      */     
/*  677 */     this.lock.readLock();
/*      */     
/*  679 */     int i = getBHTreeIndex(paramLocale);
/*  680 */     if (i < 0) {
/*  681 */       this.lock.readUnlock();
/*  682 */       return null;
/*      */     } 
/*      */     
/*  685 */     this.bhTreeArr[i].select(paramPickShape, unorderList);
/*  686 */     this.lock.readUnlock();
/*      */     
/*  688 */     int j = unorderList.size();
/*      */     
/*  690 */     if (j < 1) {
/*  691 */       return null;
/*      */     }
/*  693 */     BHNode[] arrayOfBHNode = (BHNode[])unorderList.toArray(false);
/*      */     
/*  695 */     GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[j];
/*  696 */     for (i = 0; i < j; i++) {
/*  697 */       arrayOfGeometryAtom[i] = (GeometryAtom)((BHLeafNode)arrayOfBHNode[i]).leafIF;
/*      */     }
/*      */     
/*  700 */     return arrayOfGeometryAtom;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   GeometryAtom pickAny(Locale paramLocale, PickShape paramPickShape) {
/*  707 */     BHNode bHNode = null;
/*      */     
/*  709 */     this.lock.readLock();
/*      */     
/*  711 */     int i = getBHTreeIndex(paramLocale);
/*  712 */     if (i < 0) {
/*  713 */       this.lock.readUnlock();
/*  714 */       return null;
/*      */     } 
/*      */     
/*  717 */     bHNode = this.bhTreeArr[i].selectAny(paramPickShape);
/*      */     
/*  719 */     this.lock.readUnlock();
/*      */     
/*  721 */     if (bHNode == null) {
/*  722 */       return null;
/*      */     }
/*  724 */     return (GeometryAtom)((BHLeafNode)bHNode).leafIF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addWakeupOnCollision(WakeupOnCollisionEntry paramWakeupOnCollisionEntry) {
/*  731 */     boolean bool = true;
/*      */ 
/*      */ 
/*      */     
/*  735 */     synchronized (this.collideListLock) {
/*  736 */       WakeupOnCollisionEntry[] arrayOfWakeupOnCollisionEntry = (WakeupOnCollisionEntry[])this.collideEntryList.toArray();
/*      */ 
/*      */       
/*  739 */       for (int i = this.collideEntryList.arraySize() - 1; i >= 0; i--) {
/*  740 */         WakeupOnCollisionEntry wakeupOnCollisionEntry1 = arrayOfWakeupOnCollisionEntry[i];
/*  741 */         if (wakeupOnCollisionEntry1.behav == paramWakeupOnCollisionEntry.behav && wakeupOnCollisionEntry1.geometryAtoms == paramWakeupOnCollisionEntry.geometryAtoms) {
/*      */           
/*  743 */           this.collideEntryList.remove(i);
/*  744 */           bool = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  751 */     this.wakeupOnCollisionEntry.add(paramWakeupOnCollisionEntry);
/*  752 */     paramWakeupOnCollisionEntry.updateCollisionBounds(false);
/*      */     
/*  754 */     BHLeafInterface bHLeafInterface = collide(paramWakeupOnCollisionEntry.behav.locale, paramWakeupOnCollisionEntry.accuracyMode, paramWakeupOnCollisionEntry.geometryAtoms, paramWakeupOnCollisionEntry.vwcBounds, paramWakeupOnCollisionEntry.boundingLeaf, paramWakeupOnCollisionEntry.armingNode, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  762 */     if (bHLeafInterface != null) {
/*  763 */       this.collideEntryList.add(paramWakeupOnCollisionEntry);
/*  764 */       paramWakeupOnCollisionEntry.setTarget(bHLeafInterface);
/*      */     } 
/*      */     
/*  767 */     if (bHLeafInterface != null && bool) {
/*  768 */       paramWakeupOnCollisionEntry.setTriggered();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addWakeupOnCollision(WakeupOnCollisionExit paramWakeupOnCollisionExit) {
/*  777 */     boolean bool = true;
/*      */     
/*  779 */     synchronized (this.collideListLock) {
/*  780 */       WakeupOnCollisionExit[] arrayOfWakeupOnCollisionExit = (WakeupOnCollisionExit[])this.collideExitList.toArray();
/*      */ 
/*      */       
/*  783 */       for (int i = this.collideExitList.arraySize() - 1; i >= 0; i--) {
/*  784 */         WakeupOnCollisionExit wakeupOnCollisionExit1 = arrayOfWakeupOnCollisionExit[i];
/*  785 */         if (wakeupOnCollisionExit1.behav == paramWakeupOnCollisionExit.behav && wakeupOnCollisionExit1.geometryAtoms == paramWakeupOnCollisionExit.geometryAtoms) {
/*      */           
/*  787 */           this.collideExitList.remove(i);
/*  788 */           bool = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  795 */     this.wakeupOnCollisionExit.add(paramWakeupOnCollisionExit);
/*  796 */     paramWakeupOnCollisionExit.updateCollisionBounds(false);
/*  797 */     BHLeafInterface bHLeafInterface = collide(paramWakeupOnCollisionExit.behav.locale, paramWakeupOnCollisionExit.accuracyMode, paramWakeupOnCollisionExit.geometryAtoms, paramWakeupOnCollisionExit.vwcBounds, paramWakeupOnCollisionExit.boundingLeaf, paramWakeupOnCollisionExit.armingNode, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  805 */     if (bHLeafInterface != null) {
/*      */ 
/*      */       
/*  808 */       paramWakeupOnCollisionExit.setTarget(bHLeafInterface);
/*  809 */       this.collideExitList.add(paramWakeupOnCollisionExit);
/*      */     } 
/*      */     
/*  812 */     if (!bool) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  818 */     synchronized (this.collideListLock) {
/*  819 */       WakeupOnCollisionEntry[] arrayOfWakeupOnCollisionEntry = (WakeupOnCollisionEntry[])this.collideEntryList.toArray();
/*      */ 
/*      */ 
/*      */       
/*  823 */       for (int i = this.collideEntryList.arraySize() - 1; i >= 0; i--) {
/*  824 */         WakeupOnCollisionEntry wakeupOnCollisionEntry1 = arrayOfWakeupOnCollisionEntry[i];
/*  825 */         if (wakeupOnCollisionEntry1.behav == paramWakeupOnCollisionExit.behav && wakeupOnCollisionEntry1.geometryAtoms == paramWakeupOnCollisionExit.geometryAtoms) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  830 */           if (bHLeafInterface == null) {
/*  831 */             paramWakeupOnCollisionExit.setTriggered();
/*      */           }
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void addWakeupOnCollision(WakeupOnCollisionMovement paramWakeupOnCollisionMovement) {
/*  840 */     this.wakeupOnCollisionMovement.add(paramWakeupOnCollisionMovement);
/*  841 */     paramWakeupOnCollisionMovement.updateCollisionBounds(false);
/*  842 */     BHLeafInterface bHLeafInterface = collide(paramWakeupOnCollisionMovement.behav.locale, paramWakeupOnCollisionMovement.accuracyMode, paramWakeupOnCollisionMovement.geometryAtoms, paramWakeupOnCollisionMovement.vwcBounds, paramWakeupOnCollisionMovement.boundingLeaf, paramWakeupOnCollisionMovement.armingNode, paramWakeupOnCollisionMovement);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  849 */     if (bHLeafInterface != null) {
/*  850 */       paramWakeupOnCollisionMovement.setTarget(bHLeafInterface);
/*  851 */       this.collideMovementList.add(paramWakeupOnCollisionMovement);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  856 */   void removeWakeupOnCollision(WakeupOnCollisionEntry paramWakeupOnCollisionEntry) { this.wakeupOnCollisionEntry.remove(paramWakeupOnCollisionEntry); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  863 */   void removeWakeupOnCollision(WakeupOnCollisionExit paramWakeupOnCollisionExit) { this.wakeupOnCollisionExit.remove(paramWakeupOnCollisionExit); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeWakeupOnCollision(WakeupOnCollisionMovement paramWakeupOnCollisionMovement) {
/*  871 */     this.wakeupOnCollisionMovement.remove(paramWakeupOnCollisionMovement);
/*  872 */     this.collideMovementList.remove(paramWakeupOnCollisionMovement);
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
/*      */   void processCollisionDetection() {
/*  885 */     WakeupOnCollisionEntry[] arrayOfWakeupOnCollisionEntry = (WakeupOnCollisionEntry[])this.wakeupOnCollisionEntry.toArray();
/*      */     
/*      */     int i;
/*  888 */     for (i = this.wakeupOnCollisionEntry.arraySize() - 1; i >= 0; i--) {
/*  889 */       WakeupOnCollisionEntry wakeupOnCollisionEntry1 = arrayOfWakeupOnCollisionEntry[i];
/*  890 */       wakeupOnCollisionEntry1.updateCollisionBounds(this.reEvaluateWakeupCollisionGAs);
/*  891 */       BHLeafInterface bHLeafInterface = collide(wakeupOnCollisionEntry1.behav.locale, wakeupOnCollisionEntry1.accuracyMode, wakeupOnCollisionEntry1.geometryAtoms, wakeupOnCollisionEntry1.vwcBounds, wakeupOnCollisionEntry1.boundingLeaf, wakeupOnCollisionEntry1.armingNode, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  898 */       int j = this.collideEntryList.indexOf(wakeupOnCollisionEntry1);
/*      */       
/*  900 */       if (bHLeafInterface != null) {
/*  901 */         if (j < 0) {
/*  902 */           this.collideEntryList.add(wakeupOnCollisionEntry1);
/*  903 */           wakeupOnCollisionEntry1.setTarget(bHLeafInterface);
/*  904 */           wakeupOnCollisionEntry1.setTriggered();
/*      */         }
/*      */       
/*  907 */       } else if (j >= 0) {
/*  908 */         this.collideEntryList.remove(j);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  916 */     WakeupOnCollisionMovement[] arrayOfWakeupOnCollisionMovement = (WakeupOnCollisionMovement[])this.wakeupOnCollisionMovement.toArray();
/*      */ 
/*      */     
/*  919 */     for (i = this.wakeupOnCollisionMovement.arraySize() - 1; i >= 0; i--) {
/*  920 */       WakeupOnCollisionMovement wakeupOnCollisionMovement1 = arrayOfWakeupOnCollisionMovement[i];
/*  921 */       wakeupOnCollisionMovement1.updateCollisionBounds(this.reEvaluateWakeupCollisionGAs);
/*  922 */       BHLeafInterface bHLeafInterface = collide(wakeupOnCollisionMovement1.behav.locale, wakeupOnCollisionMovement1.accuracyMode, wakeupOnCollisionMovement1.geometryAtoms, wakeupOnCollisionMovement1.vwcBounds, wakeupOnCollisionMovement1.boundingLeaf, wakeupOnCollisionMovement1.armingNode, wakeupOnCollisionMovement1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  929 */       int j = this.collideMovementList.indexOf(wakeupOnCollisionMovement1);
/*  930 */       if (bHLeafInterface != null) {
/*  931 */         if (j < 0) {
/*  932 */           this.collideMovementList.add(wakeupOnCollisionMovement1);
/*  933 */           wakeupOnCollisionMovement1.setTarget(bHLeafInterface);
/*      */         }
/*  935 */         else if (!wakeupOnCollisionMovement1.duplicateEvent) {
/*  936 */           wakeupOnCollisionMovement1.setTriggered();
/*      */         }
/*      */       
/*      */       }
/*  940 */       else if (j >= 0) {
/*  941 */         this.collideMovementList.remove(j);
/*  942 */         wakeupOnCollisionMovement1.lastSrcBounds = null;
/*  943 */         wakeupOnCollisionMovement1.lastDstBounds = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  952 */     WakeupOnCollisionExit[] arrayOfWakeupOnCollisionExit = (WakeupOnCollisionExit[])this.wakeupOnCollisionExit.toArray();
/*      */ 
/*      */     
/*  955 */     for (i = this.wakeupOnCollisionExit.arraySize() - 1; i >= 0; i--) {
/*  956 */       WakeupOnCollisionExit wakeupOnCollisionExit1 = arrayOfWakeupOnCollisionExit[i];
/*  957 */       wakeupOnCollisionExit1.updateCollisionBounds(this.reEvaluateWakeupCollisionGAs);
/*  958 */       BHLeafInterface bHLeafInterface = collide(wakeupOnCollisionExit1.behav.locale, wakeupOnCollisionExit1.accuracyMode, wakeupOnCollisionExit1.geometryAtoms, wakeupOnCollisionExit1.vwcBounds, wakeupOnCollisionExit1.boundingLeaf, wakeupOnCollisionExit1.armingNode, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  965 */       int j = this.collideExitList.indexOf(wakeupOnCollisionExit1);
/*  966 */       if (bHLeafInterface != null) {
/*  967 */         if (j < 0) {
/*  968 */           this.collideExitList.add(wakeupOnCollisionExit1);
/*  969 */           wakeupOnCollisionExit1.setTarget(bHLeafInterface);
/*      */         }
/*      */       
/*  972 */       } else if (j >= 0) {
/*  973 */         this.collideExitList.remove(j);
/*  974 */         wakeupOnCollisionExit1.setTriggered();
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
/*      */   void checkDuplicateEvent(WakeupOnCollisionMovement paramWakeupOnCollisionMovement, Bounds paramBounds, BHLeafInterface paramBHLeafInterface) {
/*  994 */     if (paramWakeupOnCollisionMovement.lastSrcBounds != null && paramWakeupOnCollisionMovement.lastSrcBounds.equals(paramBounds)) {
/*      */       BoundingBox boundingBox;
/*  996 */       if (paramBHLeafInterface instanceof GeometryAtom) {
/*  997 */         boundingBox = ((GeometryAtom)paramBHLeafInterface).source.vwcBounds;
/*      */       } else {
/*  999 */         boundingBox = ((GroupRetained)paramBHLeafInterface).collisionVwcBounds;
/*      */       } 
/* 1001 */       if (paramWakeupOnCollisionMovement.lastDstBounds != null && paramWakeupOnCollisionMovement.lastDstBounds.equals(boundingBox)) {
/*      */         
/* 1003 */         paramWakeupOnCollisionMovement.duplicateEvent = true;
/*      */       } else {
/* 1005 */         paramWakeupOnCollisionMovement.duplicateEvent = false;
/* 1006 */         paramWakeupOnCollisionMovement.lastDstBounds = (Bounds)boundingBox.clone();
/*      */       } 
/*      */     } else {
/* 1009 */       paramWakeupOnCollisionMovement.duplicateEvent = false;
/* 1010 */       paramWakeupOnCollisionMovement.lastSrcBounds = (Bounds)paramBounds.clone();
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
/*      */ 
/*      */ 
/*      */   
/*      */   BHLeafInterface collide(Locale paramLocale, int paramInt, UnorderList paramUnorderList, Bounds paramBounds, BoundingLeafRetained paramBoundingLeafRetained, NodeRetained paramNodeRetained, WakeupCriterion paramWakeupCriterion) {
/* 1035 */     this.lock.readLock();
/* 1036 */     int i = getBHTreeIndex(paramLocale);
/*      */     
/* 1038 */     if (i < 0) {
/* 1039 */       this.lock.readUnlock();
/* 1040 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1044 */     if (paramUnorderList != null) {
/* 1045 */       synchronized (this.bhTreeArr[i]) {
/* 1046 */         if (paramBounds != null && paramNodeRetained instanceof GroupRetained) {
/*      */ 
/*      */ 
/*      */           
/* 1050 */           BHLeafInterface bHLeafInterface = this.bhTreeArr[i].selectAny(paramBounds, paramInt, (GroupRetained)paramNodeRetained);
/*      */ 
/*      */ 
/*      */           
/* 1054 */           if (bHLeafInterface == null) {
/* 1055 */             this.lock.readUnlock();
/* 1056 */             return null;
/*      */           } 
/* 1058 */           GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramUnorderList.toArray(false);
/*      */ 
/*      */           
/* 1061 */           bHLeafInterface = this.bhTreeArr[i].selectAny(arrayOfGeometryAtom, paramUnorderList.arraySize(), paramInt);
/*      */ 
/*      */ 
/*      */           
/* 1065 */           if (bHLeafInterface != null) {
/* 1066 */             this.lock.readUnlock();
/* 1067 */             if (paramWakeupCriterion != null) {
/* 1068 */               checkDuplicateEvent((WakeupOnCollisionMovement)paramWakeupCriterion, paramBounds, bHLeafInterface);
/*      */             }
/*      */             
/* 1071 */             return bHLeafInterface;
/*      */           } 
/*      */         } else {
/* 1074 */           GeometryAtom geometryAtom = (GeometryAtom)paramUnorderList.get(0);
/* 1075 */           BHLeafInterface bHLeafInterface = this.bhTreeArr[i].selectAny(geometryAtom, paramInt);
/*      */           
/* 1077 */           if (bHLeafInterface != null) {
/* 1078 */             this.lock.readUnlock();
/* 1079 */             if (paramWakeupCriterion != null) {
/* 1080 */               checkDuplicateEvent((WakeupOnCollisionMovement)paramWakeupCriterion, geometryAtom.source.vwcBounds, bHLeafInterface);
/*      */             }
/*      */ 
/*      */             
/* 1084 */             return bHLeafInterface;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1089 */       if (paramBounds == null) {
/* 1090 */         if (paramBoundingLeafRetained == null) {
/* 1091 */           this.lock.readUnlock();
/* 1092 */           return null;
/*      */         } 
/* 1094 */         paramBounds = paramBoundingLeafRetained.transformedRegion;
/*      */       } 
/* 1096 */       if (paramBounds == null) {
/* 1097 */         this.lock.readUnlock();
/* 1098 */         return null;
/*      */       } 
/* 1100 */       if (paramNodeRetained instanceof GroupRetained) {
/* 1101 */         synchronized (this.bhTreeArr[i]) {
/* 1102 */           BHLeafInterface bHLeafInterface = this.bhTreeArr[i].selectAny(paramBounds, paramInt, (GroupRetained)paramNodeRetained);
/*      */ 
/*      */ 
/*      */           
/* 1106 */           this.lock.readUnlock();
/* 1107 */           if (bHLeafInterface != null && paramWakeupCriterion != null) {
/* 1108 */             checkDuplicateEvent((WakeupOnCollisionMovement)paramWakeupCriterion, paramBounds, bHLeafInterface);
/*      */           }
/*      */           
/* 1111 */           return bHLeafInterface;
/*      */         } 
/*      */       }
/* 1114 */       synchronized (this.bhTreeArr[i]) {
/* 1115 */         BHLeafInterface bHLeafInterface = this.bhTreeArr[i].selectAny(paramBounds, paramInt, paramNodeRetained);
/*      */         
/* 1117 */         this.lock.readUnlock();
/* 1118 */         if (bHLeafInterface != null && paramWakeupCriterion != null) {
/* 1119 */           checkDuplicateEvent((WakeupOnCollisionMovement)paramWakeupCriterion, paramBounds, bHLeafInterface);
/*      */         }
/*      */         
/* 1122 */         return bHLeafInterface;
/*      */       } 
/*      */     } 
/*      */     
/* 1126 */     this.lock.readUnlock();
/* 1127 */     return null;
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
/*      */   void resetConditionMet() {
/* 1139 */     BehaviorStructure.resetConditionMet(this.wakeupOnCollisionEntry);
/* 1140 */     BehaviorStructure.resetConditionMet(this.wakeupOnCollisionExit);
/* 1141 */     BehaviorStructure.resetConditionMet(this.wakeupOnCollisionMovement);
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
/*      */   private void processSwitchChanged(J3dMessage paramJ3dMessage) {}
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
/*      */   void cleanup() {
/* 1175 */     this.collideEntryList.clear();
/* 1176 */     this.collideExitList.clear();
/* 1177 */     this.collideMovementList.clear();
/* 1178 */     this.wakeupOnCollisionEntry.clear();
/* 1179 */     this.wakeupOnCollisionExit.clear();
/* 1180 */     this.wakeupOnCollisionMovement.clear();
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\GeometryStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */