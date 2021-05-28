/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
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
/*      */ 
/*      */ 
/*      */ public class Locale
/*      */ {
/*      */   VirtualUniverse universe;
/*      */   HiResCoord hiRes;
/*      */   Vector branchGroups;
/*      */   String nodeId;
/*      */   
/*      */   public Locale(VirtualUniverse paramVirtualUniverse) {
/*   58 */     this.branchGroups = new Vector();
/*      */ 
/*      */     
/*   61 */     this.nodeId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   70 */     this.universe = paramVirtualUniverse;
/*   71 */     this.universe.addLocale(this);
/*   72 */     this.hiRes = new HiResCoord();
/*   73 */     this.nodeId = paramVirtualUniverse.getNodeId();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Locale(VirtualUniverse paramVirtualUniverse, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/*      */     this.branchGroups = new Vector();
/*      */     this.nodeId = null;
/*   86 */     this.universe = paramVirtualUniverse;
/*   87 */     this.universe.addLocale(this);
/*   88 */     this.hiRes = new HiResCoord(paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
/*   89 */     this.nodeId = paramVirtualUniverse.getNodeId();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Locale(VirtualUniverse paramVirtualUniverse, HiResCoord paramHiResCoord) {
/*      */     this.branchGroups = new Vector();
/*      */     this.nodeId = null;
/*  100 */     this.universe = paramVirtualUniverse;
/*  101 */     this.universe.addLocale(this);
/*  102 */     this.hiRes = new HiResCoord(paramHiResCoord);
/*  103 */     this.nodeId = paramVirtualUniverse.getNodeId();
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
/*  114 */   public VirtualUniverse getVirtualUniverse() { return this.universe; }
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
/*  125 */   public void setHiRes(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3) { this.hiRes.setHiResCoord(paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   public void setHiRes(HiResCoord paramHiResCoord) { this.hiRes.setHiResCoord(paramHiResCoord); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  143 */   public void getHiRes(HiResCoord paramHiResCoord) { this.hiRes.getHiResCoord(paramHiResCoord); }
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
/*      */   public void addBranchGraph(BranchGroup paramBranchGroup) {
/*  156 */     if (this.universe == null) {
/*  157 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  162 */     if (((BranchGroupRetained)paramBranchGroup.retained).parent != null || paramBranchGroup.isLive())
/*      */     {
/*  164 */       throw new MultipleParentException(J3dI18N.getString("Locale0"));
/*      */     }
/*      */     
/*  167 */     this.universe.notifyStructureChangeListeners(true, this, paramBranchGroup);
/*  168 */     this.universe.resetWaitMCFlag();
/*  169 */     synchronized (this.universe.sceneGraphLock) {
/*  170 */       doAddBranchGraph(paramBranchGroup);
/*  171 */       this.universe.setLiveState.reset(this);
/*      */     } 
/*  173 */     this.universe.waitForMC();
/*      */   }
/*      */ 
/*      */   
/*      */   void doAddBranchGraph(BranchGroup paramBranchGroup) {
/*  178 */     BranchGroupRetained branchGroupRetained = (BranchGroupRetained)paramBranchGroup.retained;
/*      */     
/*  180 */     SetLiveState setLiveState = this.universe.setLiveState;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  189 */     branchGroupRetained.attachedToLocale = true;
/*  190 */     this.branchGroups.addElement(paramBranchGroup);
/*  191 */     setLiveState.reset(this);
/*  192 */     setLiveState.currentTransforms[0] = new Transform3D[2];
/*  193 */     setLiveState.currentTransforms[0][0] = new Transform3D();
/*  194 */     setLiveState.currentTransforms[0][1] = new Transform3D();
/*  195 */     setLiveState.currentTransformsIndex[0] = new int[2];
/*  196 */     setLiveState.currentTransformsIndex[0][0] = 0;
/*  197 */     setLiveState.currentTransformsIndex[0][1] = 0;
/*      */     
/*  199 */     setLiveState.localToVworld = setLiveState.currentTransforms;
/*  200 */     setLiveState.localToVworldIndex = setLiveState.currentTransformsIndex;
/*      */     
/*  202 */     setLiveState.branchGroupPaths = new ArrayList();
/*  203 */     setLiveState.branchGroupPaths.add(new BranchGroupRetained[0]);
/*      */     
/*  205 */     setLiveState.orderedPaths = new ArrayList(1);
/*  206 */     setLiveState.orderedPaths.add(new OrderedPath());
/*      */     
/*  208 */     setLiveState.switchStates = new ArrayList(1);
/*  209 */     setLiveState.switchStates.add(new SwitchState(false));
/*      */     
/*  211 */     branchGroupRetained.setLive(setLiveState);
/*      */     
/*  213 */     J3dMessage j3dMessage = new J3dMessage();
/*  214 */     j3dMessage.threads = 4224;
/*  215 */     j3dMessage.type = 32;
/*  216 */     j3dMessage.universe = this.universe;
/*  217 */     j3dMessage.args[0] = setLiveState.ogList.toArray();
/*  218 */     j3dMessage.args[1] = setLiveState.ogChildIdList.toArray();
/*  219 */     j3dMessage.args[2] = setLiveState.ogOrderedIdList.toArray();
/*  220 */     j3dMessage.args[3] = setLiveState.ogCIOList.toArray();
/*  221 */     j3dMessage.args[4] = setLiveState.ogCIOTableList.toArray();
/*      */     
/*  223 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     
/*  225 */     j3dMessage = new J3dMessage();
/*  226 */     j3dMessage.threads = 4096;
/*  227 */     j3dMessage.type = 57;
/*  228 */     j3dMessage.universe = this.universe;
/*  229 */     j3dMessage.args[0] = setLiveState.changedViewGroup;
/*  230 */     j3dMessage.args[1] = setLiveState.changedViewList;
/*  231 */     j3dMessage.args[2] = setLiveState.keyList;
/*  232 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */ 
/*      */     
/*  235 */     j3dMessage = new J3dMessage();
/*  236 */     j3dMessage.threads = setLiveState.notifyThreads;
/*  237 */     j3dMessage.type = 0;
/*  238 */     j3dMessage.universe = this.universe;
/*  239 */     j3dMessage.args[0] = setLiveState.nodeList.toArray();
/*  240 */     j3dMessage.args[1] = null;
/*  241 */     j3dMessage.args[2] = null;
/*  242 */     if (setLiveState.viewScopedNodeList != null) {
/*  243 */       j3dMessage.args[3] = setLiveState.viewScopedNodeList;
/*  244 */       j3dMessage.args[4] = setLiveState.scopedNodesViewList;
/*      */     } 
/*  246 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     
/*  248 */     int i = setLiveState.behaviorNodes.size();
/*  249 */     for (byte b = 0; b < i; b++) {
/*      */       
/*  251 */       BehaviorRetained behaviorRetained = (BehaviorRetained)setLiveState.behaviorNodes.get(b);
/*  252 */       behaviorRetained.executeInitialize();
/*      */     } 
/*      */     
/*  255 */     j3dMessage = new J3dMessage();
/*  256 */     j3dMessage.threads = 256;
/*  257 */     j3dMessage.type = 49;
/*  258 */     j3dMessage.universe = this.universe;
/*  259 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */ 
/*      */     
/*  262 */     setLiveState.reset(null);
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
/*      */   public void removeBranchGraph(BranchGroup paramBranchGroup) {
/*  275 */     if (this.universe == null) {
/*  276 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  279 */     if (!paramBranchGroup.getCapability(17)) {
/*  280 */       throw new CapabilityNotSetException(J3dI18N.getString("Locale1"));
/*      */     }
/*  282 */     this.universe.resetWaitMCFlag();
/*  283 */     synchronized (this.universe.sceneGraphLock) {
/*  284 */       doRemoveBranchGraph(paramBranchGroup, null, 0);
/*  285 */       this.universe.setLiveState.reset(this);
/*      */     } 
/*  287 */     this.universe.waitForMC();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeFromUniverse() {
/*  294 */     if (this.branchGroups.size() > 0) {
/*  295 */       this.universe.resetWaitMCFlag();
/*  296 */       synchronized (this.universe.sceneGraphLock) {
/*      */ 
/*      */         
/*  299 */         Object[] arrayOfObject = this.branchGroups.toArray();
/*  300 */         for (byte b = 0; b < arrayOfObject.length; b++) {
/*  301 */           doRemoveBranchGraph((BranchGroup)arrayOfObject[b], null, 0);
/*      */         }
/*      */       } 
/*      */       
/*  305 */       this.universe.waitForMC();
/*      */     } 
/*      */ 
/*      */     
/*  309 */     if (this.nodeId != null) {
/*  310 */       this.universe.nodeIdFreeList.addElement(this.nodeId);
/*  311 */       this.nodeId = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  316 */     this.universe = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doRemoveBranchGraph(BranchGroup paramBranchGroup, J3dMessage[] paramArrayOfJ3dMessage, int paramInt) {
/*      */     J3dMessage j3dMessage;
/*  324 */     BranchGroupRetained branchGroupRetained = (BranchGroupRetained)paramBranchGroup.retained;
/*      */ 
/*      */     
/*  327 */     if (!paramBranchGroup.isLive())
/*      */       return; 
/*  329 */     branchGroupRetained.attachedToLocale = false;
/*  330 */     this.branchGroups.removeElement(paramBranchGroup);
/*  331 */     this.universe.setLiveState.reset(this);
/*  332 */     branchGroupRetained.clearLive(this.universe.setLiveState);
/*  333 */     branchGroupRetained.setParent(null);
/*  334 */     branchGroupRetained.setLocale(null);
/*      */     
/*  336 */     if (paramArrayOfJ3dMessage == null) {
/*  337 */       j3dMessage = new J3dMessage();
/*      */     } else {
/*  339 */       j3dMessage = paramArrayOfJ3dMessage[paramInt++];
/*      */     } 
/*  341 */     j3dMessage.threads = 4224;
/*  342 */     j3dMessage.type = 33;
/*  343 */     j3dMessage.universe = this.universe;
/*  344 */     j3dMessage.args[0] = this.universe.setLiveState.ogList.toArray();
/*  345 */     j3dMessage.args[1] = this.universe.setLiveState.ogChildIdList.toArray();
/*  346 */     j3dMessage.args[3] = this.universe.setLiveState.ogCIOList.toArray();
/*  347 */     j3dMessage.args[4] = this.universe.setLiveState.ogCIOTableList.toArray();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  353 */     if (paramArrayOfJ3dMessage == null) {
/*  354 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*  355 */       j3dMessage = new J3dMessage();
/*      */     } else {
/*  357 */       j3dMessage = paramArrayOfJ3dMessage[paramInt++];
/*      */     } 
/*  359 */     j3dMessage.threads = this.universe.setLiveState.notifyThreads;
/*  360 */     j3dMessage.type = 1;
/*  361 */     j3dMessage.universe = this.universe;
/*  362 */     j3dMessage.args[0] = this.universe.setLiveState.nodeList.toArray();
/*  363 */     if (this.universe.setLiveState.viewScopedNodeList != null) {
/*  364 */       j3dMessage.args[3] = this.universe.setLiveState.viewScopedNodeList;
/*  365 */       j3dMessage.args[4] = this.universe.setLiveState.scopedNodesViewList;
/*      */     } 
/*      */     
/*  368 */     if (paramArrayOfJ3dMessage == null) {
/*  369 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*  370 */       j3dMessage = new J3dMessage();
/*      */     } else {
/*  372 */       j3dMessage = paramArrayOfJ3dMessage[paramInt++];
/*      */     } 
/*  374 */     j3dMessage.threads = 4096;
/*  375 */     j3dMessage.type = 58;
/*  376 */     j3dMessage.universe = this.universe;
/*  377 */     j3dMessage.args[0] = this.universe.setLiveState.changedViewGroup;
/*  378 */     j3dMessage.args[1] = this.universe.setLiveState.keyList;
/*      */     
/*  380 */     if (paramArrayOfJ3dMessage == null) {
/*  381 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } else {
/*  383 */       j3dMessage = paramArrayOfJ3dMessage[paramInt++];
/*      */     } 
/*      */     
/*  386 */     if (this.universe.isEmpty()) {
/*  387 */       VirtualUniverse.mc.postRequest(MasterControl.EMPTY_UNIVERSE, this.universe);
/*      */     }
/*      */     
/*  390 */     this.universe.setLiveState.reset(null);
/*  391 */     this.universe.notifyStructureChangeListeners(false, this, paramBranchGroup);
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
/*      */   public void replaceBranchGraph(BranchGroup paramBranchGroup1, BranchGroup paramBranchGroup2) {
/*  411 */     if (this.universe == null) {
/*  412 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  415 */     if (!paramBranchGroup1.getCapability(17)) {
/*  416 */       throw new CapabilityNotSetException(J3dI18N.getString("Locale1"));
/*      */     }
/*      */     
/*  419 */     if (((BranchGroupRetained)paramBranchGroup2.retained).parent != null) {
/*  420 */       throw new MultipleParentException(J3dI18N.getString("Locale3"));
/*      */     }
/*  422 */     this.universe.resetWaitMCFlag();
/*  423 */     this.universe.notifyStructureChangeListeners(true, this, paramBranchGroup2);
/*  424 */     synchronized (this.universe.sceneGraphLock) {
/*  425 */       doReplaceBranchGraph(paramBranchGroup1, paramBranchGroup2);
/*  426 */       this.universe.setLiveState.reset(this);
/*      */     } 
/*  428 */     this.universe.notifyStructureChangeListeners(false, this, paramBranchGroup1);
/*  429 */     this.universe.waitForMC();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void doReplaceBranchGraph(BranchGroup paramBranchGroup1, BranchGroup paramBranchGroup2) {
/*  435 */     BranchGroupRetained branchGroupRetained1 = (BranchGroupRetained)paramBranchGroup1.retained;
/*  436 */     BranchGroupRetained branchGroupRetained2 = (BranchGroupRetained)paramBranchGroup2.retained;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  441 */     this.branchGroups.removeElement(paramBranchGroup1);
/*  442 */     branchGroupRetained1.attachedToLocale = false;
/*  443 */     this.universe.setLiveState.reset(this);
/*  444 */     branchGroupRetained1.clearLive(this.universe.setLiveState);
/*      */     
/*  446 */     J3dMessage j3dMessage2 = new J3dMessage();
/*      */     
/*  448 */     j3dMessage2.threads = 4224;
/*  449 */     j3dMessage2.type = 33;
/*  450 */     j3dMessage2.universe = this.universe;
/*  451 */     j3dMessage2.args[0] = this.universe.setLiveState.ogList.toArray();
/*  452 */     j3dMessage2.args[1] = this.universe.setLiveState.ogChildIdList.toArray();
/*  453 */     j3dMessage2.args[3] = this.universe.setLiveState.ogCIOList.toArray();
/*  454 */     j3dMessage2.args[4] = this.universe.setLiveState.ogCIOTableList.toArray();
/*  455 */     VirtualUniverse.mc.processMessage(j3dMessage2);
/*      */     
/*  457 */     j3dMessage2 = new J3dMessage();
/*  458 */     j3dMessage2.threads = 4096;
/*  459 */     j3dMessage2.type = 58;
/*  460 */     j3dMessage2.universe = this.universe;
/*  461 */     j3dMessage2.args[0] = this.universe.setLiveState.changedViewGroup;
/*  462 */     j3dMessage2.args[1] = this.universe.setLiveState.keyList;
/*  463 */     VirtualUniverse.mc.processMessage(j3dMessage2);
/*      */ 
/*      */     
/*  466 */     j3dMessage2 = new J3dMessage();
/*  467 */     j3dMessage2.threads = this.universe.setLiveState.notifyThreads;
/*  468 */     j3dMessage2.type = 1;
/*  469 */     j3dMessage2.universe = this.universe;
/*  470 */     j3dMessage2.args[0] = this.universe.setLiveState.nodeList.toArray();
/*  471 */     VirtualUniverse.mc.processMessage(j3dMessage2);
/*      */     
/*  473 */     this.branchGroups.addElement(paramBranchGroup2);
/*  474 */     branchGroupRetained2.attachedToLocale = true;
/*  475 */     this.universe.setLiveState.reset(this);
/*  476 */     this.universe.setLiveState.currentTransforms[0] = new Transform3D[2];
/*  477 */     this.universe.setLiveState.currentTransforms[0][0] = new Transform3D();
/*  478 */     this.universe.setLiveState.currentTransforms[0][1] = new Transform3D();
/*  479 */     this.universe.setLiveState.currentTransformsIndex[0] = new int[2];
/*  480 */     this.universe.setLiveState.currentTransformsIndex[0][0] = 0;
/*  481 */     this.universe.setLiveState.currentTransformsIndex[0][1] = 0;
/*      */     
/*  483 */     this.universe.setLiveState.localToVworld = this.universe.setLiveState.currentTransforms;
/*      */     
/*  485 */     this.universe.setLiveState.localToVworldIndex = this.universe.setLiveState.currentTransformsIndex;
/*      */ 
/*      */     
/*  488 */     this.universe.setLiveState.branchGroupPaths = new ArrayList();
/*  489 */     this.universe.setLiveState.branchGroupPaths.add(new BranchGroupRetained[0]);
/*      */     
/*  491 */     this.universe.setLiveState.orderedPaths = new ArrayList(1);
/*  492 */     this.universe.setLiveState.orderedPaths.add(new OrderedPath());
/*      */     
/*  494 */     this.universe.setLiveState.switchStates = new ArrayList(1);
/*  495 */     this.universe.setLiveState.switchStates.add(new SwitchState(false));
/*      */     
/*  497 */     branchGroupRetained2.setLive(this.universe.setLiveState);
/*      */ 
/*      */     
/*  500 */     J3dMessage j3dMessage1 = new J3dMessage();
/*  501 */     j3dMessage1.threads = 4224;
/*  502 */     j3dMessage1.type = 32;
/*  503 */     j3dMessage1.universe = this.universe;
/*  504 */     j3dMessage1.args[0] = this.universe.setLiveState.ogList.toArray();
/*  505 */     j3dMessage1.args[1] = this.universe.setLiveState.ogChildIdList.toArray();
/*  506 */     j3dMessage1.args[2] = this.universe.setLiveState.ogOrderedIdList.toArray();
/*  507 */     j3dMessage1.args[3] = this.universe.setLiveState.ogCIOList.toArray();
/*  508 */     j3dMessage1.args[4] = this.universe.setLiveState.ogCIOTableList.toArray();
/*  509 */     VirtualUniverse.mc.processMessage(j3dMessage1);
/*      */ 
/*      */     
/*  512 */     j3dMessage1 = new J3dMessage();
/*  513 */     j3dMessage1.threads = this.universe.setLiveState.notifyThreads;
/*  514 */     j3dMessage1.type = 0;
/*  515 */     j3dMessage1.universe = this.universe;
/*  516 */     j3dMessage1.args[0] = this.universe.setLiveState.nodeList.toArray();
/*  517 */     j3dMessage1.args[1] = null;
/*  518 */     j3dMessage1.args[2] = null;
/*  519 */     if (this.universe.setLiveState.viewScopedNodeList != null) {
/*  520 */       j3dMessage1.args[3] = this.universe.setLiveState.viewScopedNodeList;
/*  521 */       j3dMessage1.args[4] = this.universe.setLiveState.scopedNodesViewList;
/*      */     } 
/*  523 */     VirtualUniverse.mc.processMessage(j3dMessage1);
/*      */     
/*  525 */     Object[] arrayOfObject = this.universe.setLiveState.behaviorNodes.toArray();
/*      */     
/*  527 */     if (this.universe.isEmpty()) {
/*  528 */       VirtualUniverse.mc.postRequest(MasterControl.EMPTY_UNIVERSE, this.universe);
/*      */     }
/*      */ 
/*      */     
/*  532 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/*  533 */       ((BehaviorRetained)arrayOfObject[b]).executeInitialize();
/*      */     }
/*      */     
/*  536 */     j3dMessage1 = new J3dMessage();
/*  537 */     j3dMessage1.threads = 256;
/*  538 */     j3dMessage1.type = 49;
/*  539 */     j3dMessage1.universe = this.universe;
/*  540 */     VirtualUniverse.mc.processMessage(j3dMessage1);
/*      */ 
/*      */     
/*  543 */     this.universe.setLiveState.reset(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  551 */   public int numBranchGraphs() { return this.branchGroups.size(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration getAllBranchGraphs() {
/*  561 */     if (this.universe == null) {
/*  562 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  565 */     return this.branchGroups.elements();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void validateModeFlagAndPickShape(int paramInt1, int paramInt2, PickShape paramPickShape) {
/*  571 */     if (this.universe == null) {
/*  572 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  575 */     if (paramInt1 != 1 && paramInt1 != 2)
/*      */     {
/*  577 */       throw new IllegalArgumentException(J3dI18N.getString("Locale5"));
/*      */     }
/*      */     
/*  580 */     if (paramPickShape instanceof PickPoint && paramInt1 == 2) {
/*  581 */       throw new IllegalArgumentException(J3dI18N.getString("Locale6"));
/*      */     }
/*      */     
/*  584 */     if ((paramInt2 & 0x20) != 0 && (paramInt2 & 0x40) != 0)
/*      */     {
/*  586 */       throw new IllegalArgumentException(J3dI18N.getString("Locale7"));
/*      */     }
/*      */     
/*  589 */     if (paramInt1 == 1 && (paramInt2 & 0x78) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  595 */       throw new IllegalArgumentException(J3dI18N.getString("Locale8"));
/*      */     }
/*      */     
/*  598 */     if (paramPickShape instanceof PickBounds && (paramInt2 & 0x78) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  604 */       throw new IllegalArgumentException(J3dI18N.getString("Locale9"));
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
/*      */   public SceneGraphPath[] pickAll(PickShape paramPickShape) {
/*  621 */     if (this.universe == null) {
/*  622 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  625 */     PickInfo[] arrayOfPickInfo = pickAll(1, 1, paramPickShape);
/*      */ 
/*      */     
/*  628 */     if (arrayOfPickInfo == null) {
/*  629 */       return null;
/*      */     }
/*  631 */     SceneGraphPath[] arrayOfSceneGraphPath = new SceneGraphPath[arrayOfPickInfo.length];
/*  632 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/*  633 */       arrayOfSceneGraphPath[b] = arrayOfPickInfo[b].getSceneGraphPath();
/*      */     }
/*      */     
/*  636 */     return arrayOfSceneGraphPath;
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
/*      */   public PickInfo[] pickAll(int paramInt1, int paramInt2, PickShape paramPickShape) {
/*  712 */     validateModeFlagAndPickShape(paramInt1, paramInt2, paramPickShape);
/*      */     
/*  714 */     GeometryAtom[] arrayOfGeometryAtom = this.universe.geometryStructure.pickAll(this, paramPickShape);
/*      */     
/*  716 */     return PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 1);
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
/*      */   public SceneGraphPath[] pickAllSorted(PickShape paramPickShape) {
/*  737 */     if (this.universe == null) {
/*  738 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  741 */     PickInfo[] arrayOfPickInfo = pickAllSorted(1, 1, paramPickShape);
/*      */ 
/*      */     
/*  744 */     if (arrayOfPickInfo == null) {
/*  745 */       return null;
/*      */     }
/*  747 */     SceneGraphPath[] arrayOfSceneGraphPath = new SceneGraphPath[arrayOfPickInfo.length];
/*  748 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/*  749 */       arrayOfSceneGraphPath[b] = arrayOfPickInfo[b].getSceneGraphPath();
/*      */     }
/*      */     
/*  752 */     return arrayOfSceneGraphPath;
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
/*      */   public PickInfo[] pickAllSorted(int paramInt1, int paramInt2, PickShape paramPickShape) {
/*  829 */     validateModeFlagAndPickShape(paramInt1, paramInt2, paramPickShape);
/*  830 */     GeometryAtom[] arrayOfGeometryAtom = this.universe.geometryStructure.pickAll(this, paramPickShape);
/*      */     
/*  832 */     if (arrayOfGeometryAtom == null || arrayOfGeometryAtom.length == 0) {
/*  833 */       return null;
/*      */     }
/*      */     
/*  836 */     PickInfo[] arrayOfPickInfo = null;
/*      */     
/*  838 */     if (paramInt1 == 2) {
/*      */       
/*  840 */       paramInt2 |= 0x10;
/*  841 */       arrayOfPickInfo = PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 1);
/*  842 */       if (arrayOfPickInfo != null) {
/*  843 */         PickInfo.sortPickInfoArray(arrayOfPickInfo);
/*      */       }
/*      */     } else {
/*      */       
/*  847 */       PickInfo.sortGeomAtoms(arrayOfGeometryAtom, paramPickShape);
/*  848 */       arrayOfPickInfo = PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 1);
/*      */     } 
/*      */     
/*  851 */     return arrayOfPickInfo;
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
/*      */   public SceneGraphPath pickClosest(PickShape paramPickShape) {
/*  869 */     if (this.universe == null) {
/*  870 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  873 */     PickInfo pickInfo = pickClosest(1, 1, paramPickShape);
/*      */ 
/*      */     
/*  876 */     if (pickInfo == null) {
/*  877 */       return null;
/*      */     }
/*  879 */     return pickInfo.getSceneGraphPath();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PickInfo pickClosest(int paramInt1, int paramInt2, PickShape paramPickShape) {
/*  953 */     PickInfo[] arrayOfPickInfo = null;
/*      */     
/*  955 */     arrayOfPickInfo = pickAllSorted(paramInt1, paramInt2, paramPickShape);
/*      */     
/*  957 */     if (arrayOfPickInfo == null) {
/*  958 */       return null;
/*      */     }
/*      */     
/*  961 */     return arrayOfPickInfo[0];
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
/*      */   public SceneGraphPath pickAny(PickShape paramPickShape) {
/*  977 */     if (this.universe == null) {
/*  978 */       throw new IllegalStateException(J3dI18N.getString("Locale4"));
/*      */     }
/*      */     
/*  981 */     PickInfo pickInfo = pickAny(1, 1, paramPickShape);
/*      */ 
/*      */     
/*  984 */     if (pickInfo == null) {
/*  985 */       return null;
/*      */     }
/*  987 */     return pickInfo.getSceneGraphPath();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PickInfo pickAny(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 1062 */     validateModeFlagAndPickShape(paramInt1, paramInt2, paramPickShape);
/* 1063 */     GeometryAtom[] arrayOfGeometryAtom = this.universe.geometryStructure.pickAll(this, paramPickShape);
/*      */     
/* 1065 */     PickInfo[] arrayOfPickInfo = PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 2);
/*      */     
/* 1067 */     if (arrayOfPickInfo == null) {
/* 1068 */       return null;
/*      */     }
/*      */     
/* 1071 */     return arrayOfPickInfo[0];
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Locale.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */