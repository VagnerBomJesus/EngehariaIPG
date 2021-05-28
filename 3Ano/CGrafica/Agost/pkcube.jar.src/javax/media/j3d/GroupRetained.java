/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ class GroupRetained
/*      */   extends NodeRetained
/*      */   implements BHLeafInterface
/*      */ {
/*      */   ArrayList children;
/*      */   Bounds collisionBound;
/*      */   Locale locale;
/*      */   ArrayList lights;
/*      */   ArrayList fogs;
/*      */   ArrayList modelClips;
/*      */   ArrayList altAppearances;
/*      */   boolean collisionTarget;
/*      */   ArrayList childrenSwitchLinks;
/*      */   int parentSwitchLinkChildIndex;
/*      */   ArrayList orderedPaths;
/*      */   BoundingBox collisionVwcBounds;
/*      */   ArrayList mirrorGroup;
/*      */   
/*      */   GroupRetained() {
/*   28 */     this.children = new ArrayList(1);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   33 */     this.collisionBound = null;
/*      */ 
/*      */     
/*   36 */     this.locale = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   41 */     this.lights = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   46 */     this.fogs = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   51 */     this.modelClips = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   57 */     this.altAppearances = null;
/*      */ 
/*      */ 
/*      */     
/*   61 */     this.collisionTarget = false;
/*      */ 
/*      */     
/*   64 */     this.childrenSwitchLinks = null;
/*      */ 
/*      */     
/*   67 */     this.parentSwitchLinkChildIndex = -1;
/*      */ 
/*      */     
/*   70 */     this.orderedPaths = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  103 */     this.bhLeafNode = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  110 */     this.isRoot = false;
/*      */     
/*  112 */     this.allocatedLights = false;
/*      */     
/*  114 */     this.allocatedFogs = false;
/*      */     
/*  116 */     this.allocatedMclips = false;
/*      */     
/*  118 */     this.allocatedAltApps = false;
/*      */ 
/*      */     
/*  121 */     this.scopingRefCount = 0;
/*      */ 
/*      */     
/*  124 */     this.compiledChildrenList = null;
/*      */     
/*  126 */     this.isInClearLive = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  135 */     this.viewLists = null;
/*      */ 
/*      */     
/*  138 */     this.inViewSpecificGroup = false;
/*      */ 
/*      */     
/*  141 */     this.nodeType = 23;
/*  142 */     this.localBounds = new BoundingSphere();
/*  143 */     ((BoundingSphere)this.localBounds).setRadius(-1.0D);
/*      */   }
/*      */   HashKey key; GroupRetained sourceNode; BHLeafNode bhLeafNode; boolean isRoot; boolean allocatedLights; boolean allocatedFogs; boolean allocatedMclips; boolean allocatedAltApps; int scopingRefCount; ArrayList compiledChildrenList;
/*      */   boolean isInClearLive;
/*      */   ArrayList viewLists;
/*      */   boolean inViewSpecificGroup;
/*      */   
/*      */   void setCollisionBounds(Bounds paramBounds) {
/*  151 */     if (paramBounds == null) {
/*  152 */       this.collisionBound = null;
/*      */     } else {
/*  154 */       this.collisionBound = (Bounds)paramBounds.clone();
/*      */     } 
/*      */     
/*  157 */     if (this.source.isLive()) {
/*  158 */       J3dMessage j3dMessage = new J3dMessage();
/*  159 */       j3dMessage.type = 34;
/*  160 */       j3dMessage.threads = 8256;
/*      */       
/*  162 */       j3dMessage.universe = this.universe;
/*  163 */       j3dMessage.args[0] = this;
/*  164 */       VirtualUniverse.mc.processMessage(j3dMessage);
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
/*  175 */   Bounds getCollisionBounds() { return (this.collisionBound == null) ? null : (Bounds)this.collisionBound.clone(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setChild(Node paramNode, int paramInt) {
/*  185 */     checkValidChild(paramNode, "GroupRetained0");
/*  186 */     if (this.source.isLive()) {
/*  187 */       this.universe.resetWaitMCFlag();
/*  188 */       synchronized (this.universe.sceneGraphLock) {
/*  189 */         doSetChild(paramNode, paramInt);
/*  190 */         this.universe.setLiveState.clear();
/*      */       } 
/*  192 */       this.universe.waitForMC();
/*      */     } else {
/*      */       
/*  195 */       doSetChild(paramNode, paramInt);
/*  196 */       if (this.universe != null) {
/*  197 */         synchronized (this.universe.sceneGraphLock) {
/*  198 */           this.universe.setLiveState.clear();
/*      */         } 
/*      */       }
/*      */     } 
/*  202 */     dirtyBoundsCache();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void doSetChild(Node paramNode, int paramInt) {
/*  208 */     J3dMessage[] arrayOfJ3dMessage = null;
/*  209 */     boolean bool = false;
/*  210 */     byte b = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  219 */     NodeRetained nodeRetained1 = (NodeRetained)this.children.get(paramInt);
/*      */     
/*  221 */     if (this.source.isLive()) {
/*  222 */       if (nodeRetained1 != null) {
/*  223 */         bool += true;
/*      */         
/*  225 */         b = 3;
/*      */       } 
/*      */       
/*  228 */       if (paramNode != null) {
/*  229 */         bool += true;
/*      */       }
/*      */ 
/*      */       
/*  233 */       arrayOfJ3dMessage = new J3dMessage[bool];
/*  234 */       for (byte b1 = 0; b1 < bool; b1++) {
/*  235 */         arrayOfJ3dMessage[b1] = new J3dMessage();
/*      */       }
/*      */     } 
/*      */     
/*  239 */     if (nodeRetained1 != null) {
/*  240 */       nodeRetained1.setParent(null);
/*  241 */       checkClearLive(nodeRetained1, arrayOfJ3dMessage, 0, paramInt, null);
/*  242 */       if (this.source.isLive()) {
/*  243 */         this.universe.notifyStructureChangeListeners(false, this.source, (BranchGroup)nodeRetained1.source);
/*      */       }
/*      */     } 
/*  246 */     removeChildrenData(paramInt);
/*      */     
/*  248 */     if (paramNode == null) {
/*  249 */       this.children.set(paramInt, null);
/*  250 */       if (arrayOfJ3dMessage != null) {
/*  251 */         VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  256 */     if (this.source.isLive()) {
/*  257 */       this.universe.notifyStructureChangeListeners(true, this.source, (BranchGroup)paramNode);
/*      */     }
/*  259 */     NodeRetained nodeRetained2 = (NodeRetained)paramNode.retained;
/*  260 */     nodeRetained2.setParent(this);
/*  261 */     this.children.set(paramInt, nodeRetained2);
/*      */ 
/*      */     
/*  264 */     insertChildrenData(paramInt);
/*  265 */     checkSetLive(nodeRetained2, paramInt, arrayOfJ3dMessage, b, null);
/*  266 */     if (this.source.isLive()) {
/*  267 */       ((BranchGroupRetained)nodeRetained2).isNew = true;
/*      */     }
/*      */     
/*  270 */     if (arrayOfJ3dMessage != null) {
/*  271 */       VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void insertChild(Node paramNode, int paramInt) {
/*  282 */     checkValidChild(paramNode, "GroupRetained1");
/*  283 */     if (this.source.isLive()) {
/*  284 */       this.universe.resetWaitMCFlag();
/*  285 */       synchronized (this.universe.sceneGraphLock) {
/*  286 */         this.universe.notifyStructureChangeListeners(true, this.source, (BranchGroup)paramNode);
/*  287 */         doInsertChild(paramNode, paramInt);
/*  288 */         this.universe.setLiveState.clear();
/*      */       } 
/*  290 */       this.universe.waitForMC();
/*      */     } else {
/*  292 */       doInsertChild(paramNode, paramInt);
/*  293 */       if (this.universe != null) {
/*  294 */         synchronized (this.universe.sceneGraphLock) {
/*  295 */           this.universe.setLiveState.clear();
/*      */         } 
/*      */       }
/*      */     } 
/*  299 */     dirtyBoundsCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doInsertChild(Node paramNode, int paramInt) {
/*  307 */     insertChildrenData(paramInt);
/*  308 */     for (int i = paramInt; i < this.children.size(); i++) {
/*  309 */       NodeRetained nodeRetained1 = (NodeRetained)this.children.get(i);
/*  310 */       if (nodeRetained1 != null)
/*  311 */         nodeRetained1.childIndex++; 
/*      */     } 
/*  313 */     if (paramNode == null) {
/*  314 */       this.children.add(paramInt, null);
/*      */       
/*      */       return;
/*      */     } 
/*  318 */     NodeRetained nodeRetained = (NodeRetained)paramNode.retained;
/*  319 */     nodeRetained.setParent(this);
/*  320 */     this.children.add(paramInt, nodeRetained);
/*  321 */     checkSetLive(nodeRetained, paramInt, null, 0, null);
/*  322 */     if (this.source.isLive()) {
/*  323 */       ((BranchGroupRetained)nodeRetained).isNew = true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeChild(int paramInt) {
/*  333 */     if (this.source.isLive()) {
/*  334 */       this.universe.resetWaitMCFlag();
/*  335 */       synchronized (this.universe.sceneGraphLock) {
/*  336 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(paramInt);
/*  337 */         doRemoveChild(paramInt, null, 0);
/*  338 */         this.universe.setLiveState.clear();
/*  339 */         this.universe.notifyStructureChangeListeners(false, this.source, (BranchGroup)nodeRetained.source);
/*      */       } 
/*  341 */       this.universe.waitForMC();
/*      */     } else {
/*  343 */       doRemoveChild(paramInt, null, 0);
/*  344 */       if (this.universe != null) {
/*  345 */         synchronized (this.universe.sceneGraphLock) {
/*  346 */           this.universe.setLiveState.clear();
/*      */         } 
/*      */       }
/*      */     } 
/*  350 */     dirtyBoundsCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int indexOfChild(Node paramNode) {
/*  359 */     if (paramNode != null) {
/*  360 */       return this.children.indexOf((NodeRetained)paramNode.retained);
/*      */     }
/*  362 */     return this.children.indexOf(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeChild(Node paramNode) {
/*  373 */     int i = indexOfChild(paramNode);
/*  374 */     if (i >= 0)
/*  375 */       removeChild(i); 
/*      */   }
/*      */   
/*      */   void removeAllChildren() {
/*  379 */     int i = this.children.size();
/*  380 */     for (int j = i - 1; j >= 0; j--) {
/*  381 */       removeChild(j);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doRemoveChild(int paramInt1, J3dMessage[] paramArrayOfJ3dMessage, int paramInt2) {
/*  391 */     NodeRetained nodeRetained = (NodeRetained)this.children.get(paramInt1);
/*      */     
/*  393 */     int j = this.children.size();
/*  394 */     for (int i = paramInt1; i < j; i++) {
/*  395 */       NodeRetained nodeRetained1 = (NodeRetained)this.children.get(i);
/*  396 */       if (nodeRetained1 != null) {
/*  397 */         nodeRetained1.childIndex--;
/*      */       }
/*      */     } 
/*  400 */     if (nodeRetained != null) {
/*  401 */       nodeRetained.setParent(null);
/*  402 */       checkClearLive(nodeRetained, paramArrayOfJ3dMessage, paramInt2, paramInt1, null);
/*      */     } 
/*      */     
/*  405 */     this.children.remove(paramInt1);
/*  406 */     removeChildrenData(paramInt1);
/*      */     
/*  408 */     if (this.nodeType == 18) {
/*      */       
/*  410 */       SwitchRetained switchRetained = (SwitchRetained)this;
/*  411 */       switchRetained.setWhichChild(switchRetained.whichChild, true);
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
/*      */   Node getChild(int paramInt) {
/*  423 */     SceneGraphObjectRetained sceneGraphObjectRetained = (SceneGraphObjectRetained)this.children.get(paramInt);
/*  424 */     if (sceneGraphObjectRetained == null) {
/*  425 */       return null;
/*      */     }
/*  427 */     return (Node)sceneGraphObjectRetained.source;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Enumeration getAllChildren() {
/*  435 */     Vector vector = new Vector(this.children.size());
/*      */ 
/*      */     
/*  438 */     for (byte b = 0; b < this.children.size(); b++) {
/*  439 */       SceneGraphObjectRetained sceneGraphObjectRetained = (SceneGraphObjectRetained)this.children.get(b);
/*  440 */       if (sceneGraphObjectRetained != null) {
/*  441 */         vector.add(sceneGraphObjectRetained.source);
/*      */       } else {
/*  443 */         vector.add(null);
/*      */       } 
/*      */     } 
/*  446 */     return vector.elements();
/*      */   }
/*      */ 
/*      */   
/*      */   void checkValidChild(Node paramNode, String paramString) {
/*  451 */     if (paramNode != null && ((paramNode instanceof BranchGroup && ((BranchGroupRetained)paramNode.retained).attachedToLocale) || ((NodeRetained)paramNode.retained).parent != null))
/*      */     {
/*      */ 
/*      */       
/*  455 */       throw new MultipleParentException(J3dI18N.getString(paramString));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addChild(Node paramNode) {
/*  464 */     checkValidChild(paramNode, "GroupRetained2");
/*      */     
/*  466 */     if (this.source.isLive()) {
/*  467 */       this.universe.resetWaitMCFlag();
/*  468 */       synchronized (this.universe.sceneGraphLock) {
/*  469 */         this.universe.notifyStructureChangeListeners(true, this.source, (BranchGroup)paramNode);
/*  470 */         doAddChild(paramNode, null, 0);
/*  471 */         this.universe.setLiveState.clear();
/*      */       } 
/*  473 */       this.universe.waitForMC();
/*      */     } else {
/*  475 */       doAddChild(paramNode, null, 0);
/*  476 */       if (this.universe != null) {
/*  477 */         synchronized (this.universe.sceneGraphLock) {
/*  478 */           this.universe.setLiveState.clear();
/*      */         } 
/*      */       }
/*      */     } 
/*  482 */     dirtyBoundsCache();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void doAddChild(Node paramNode, J3dMessage[] paramArrayOfJ3dMessage, int paramInt) {
/*  488 */     appendChildrenData();
/*      */     
/*  490 */     if (paramNode == null) {
/*  491 */       this.children.add(null);
/*      */       
/*      */       return;
/*      */     } 
/*  495 */     NodeRetained nodeRetained = (NodeRetained)paramNode.retained;
/*  496 */     nodeRetained.setParent(this);
/*  497 */     this.children.add(nodeRetained);
/*  498 */     checkSetLive(nodeRetained, this.children.size() - 1, paramArrayOfJ3dMessage, paramInt, null);
/*  499 */     if (this.source.isLive()) {
/*  500 */       ((BranchGroupRetained)nodeRetained).isNew = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void moveTo(BranchGroup paramBranchGroup) {
/*  506 */     if (paramBranchGroup != null) {
/*  507 */       ((GroupRetained)paramBranchGroup.retained).dirtyBoundsCache();
/*      */     }
/*  509 */     if (this.source.isLive()) {
/*  510 */       this.universe.resetWaitMCFlag();
/*  511 */       synchronized (this.universe.sceneGraphLock) {
/*  512 */         GroupRetained groupRetained = (GroupRetained)((BranchGroupRetained)paramBranchGroup.retained).parent;
/*  513 */         doMoveTo(paramBranchGroup);
/*  514 */         this.universe.setLiveState.clear();
/*  515 */         if (groupRetained == null) {
/*  516 */           this.universe.notifyStructureChangeListeners(((BranchGroupRetained)paramBranchGroup.retained).locale, this.source, paramBranchGroup);
/*      */         } else {
/*  518 */           this.universe.notifyStructureChangeListeners(groupRetained.source, this.source, paramBranchGroup);
/*      */         } 
/*  520 */       }  this.universe.waitForMC();
/*      */     } else {
/*  522 */       doMoveTo(paramBranchGroup);
/*  523 */       if (this.universe != null) {
/*  524 */         synchronized (this.universe.sceneGraphLock) {
/*  525 */           this.universe.setLiveState.clear();
/*      */         } 
/*      */       }
/*      */     } 
/*  529 */     dirtyBoundsCache();
/*      */   }
/*      */ 
/*      */   
/*      */   void doMoveTo(BranchGroup paramBranchGroup) {
/*  534 */     J3dMessage[] arrayOfJ3dMessage = null;
/*  535 */     byte b1 = 0;
/*  536 */     byte b2 = 0;
/*  537 */     byte b3 = 0;
/*  538 */     if (paramBranchGroup != null) {
/*  539 */       BranchGroupRetained branchGroupRetained = (BranchGroupRetained)paramBranchGroup.retained;
/*  540 */       GroupRetained groupRetained = (GroupRetained)branchGroupRetained.parent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  549 */       if (groupRetained != null) {
/*  550 */         if (groupRetained.source.isLive()) {
/*  551 */           b1 = 3;
/*  552 */           b3 = 3;
/*      */         } else {
/*      */           
/*  555 */           b1 = 0;
/*  556 */           b3 = 0;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  561 */         b1 = 3;
/*  562 */         b3 = 3;
/*      */       } 
/*      */ 
/*      */       
/*  566 */       if (this.source.isLive()) {
/*  567 */         b1 += 4;
/*      */       }
/*      */ 
/*      */       
/*  571 */       arrayOfJ3dMessage = new J3dMessage[b1];
/*  572 */       for (byte b = 0; b < b1; b++) {
/*  573 */         arrayOfJ3dMessage[b] = new J3dMessage();
/*  574 */         (arrayOfJ3dMessage[b]).type = -1;
/*      */       } 
/*      */ 
/*      */       
/*  578 */       if (groupRetained == null) {
/*  579 */         if (branchGroupRetained.locale != null) {
/*  580 */           branchGroupRetained.locale.doRemoveBranchGraph(paramBranchGroup, arrayOfJ3dMessage, b2);
/*      */         }
/*      */       } else {
/*      */         
/*  584 */         groupRetained.doRemoveChild(groupRetained.children.indexOf(branchGroupRetained), arrayOfJ3dMessage, b2);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  592 */     doAddChild(paramBranchGroup, arrayOfJ3dMessage, b3);
/*      */     
/*  594 */     if (b1 > 0) {
/*  595 */       byte b4 = 0;
/*  596 */       for (byte b5 = 0; b5 < b1; b5++) {
/*  597 */         if ((arrayOfJ3dMessage[b5]).type != -1) {
/*  598 */           b4++;
/*      */         }
/*      */       } 
/*  601 */       if (b4 == b1) {
/*      */         
/*  603 */         VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*      */       } else {
/*  605 */         J3dMessage[] arrayOfJ3dMessage1 = null;
/*      */         
/*  607 */         if (b4 > 0) {
/*  608 */           arrayOfJ3dMessage1 = new J3dMessage[b4];
/*      */         }
/*      */         
/*  611 */         byte b6 = 0;
/*  612 */         for (byte b7 = 0; b7 < b1; b7++) {
/*  613 */           if ((arrayOfJ3dMessage[b7]).type != -1) {
/*  614 */             arrayOfJ3dMessage1[b6++] = arrayOfJ3dMessage[b7];
/*      */           }
/*      */         } 
/*  617 */         if (arrayOfJ3dMessage1 != null) {
/*  618 */           VirtualUniverse.mc.processMessage(arrayOfJ3dMessage1);
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
/*  630 */   int numChildren() { return this.children.size(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeLight(int paramInt, LightRetained[] paramArrayOfLightRetained, HashKey paramHashKey) {
/*  637 */     if (this.inSharedGroup) {
/*  638 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*  639 */       ArrayList arrayList = (ArrayList)this.lights.get(i);
/*  640 */       if (arrayList != null) {
/*  641 */         for (byte b = 0; b < paramInt; b++) {
/*  642 */           int j = arrayList.indexOf(paramArrayOfLightRetained[b]);
/*  643 */           arrayList.remove(j);
/*      */         } 
/*      */       }
/*      */     } else {
/*      */       
/*  648 */       ArrayList arrayList = (ArrayList)this.lights.get(0);
/*  649 */       for (byte b = 0; b < paramInt; b++) {
/*  650 */         int i = arrayList.indexOf(paramArrayOfLightRetained[b]);
/*  651 */         arrayList.remove(i);
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
/*      */   void addAllNodesForScopedLight(int paramInt, LightRetained[] paramArrayOfLightRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  670 */     if (this.inSharedGroup) {
/*  671 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  672 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  673 */         processAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  677 */       processAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */   
/*      */   void processAllNodesForScopedLight(int paramInt, LightRetained[] paramArrayOfLightRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  682 */     if (this.allocatedLights) {
/*  683 */       addLight(paramArrayOfLightRetained, paramInt, paramHashKey);
/*      */     }
/*  685 */     if (this.source.isLive() || isInSetLive()) {
/*  686 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  687 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  688 */         if (nodeRetained != null) {
/*  689 */           if (nodeRetained instanceof GroupRetained && (nodeRetained.source.isLive() || nodeRetained.isInSetLive())) {
/*  690 */             ((GroupRetained)nodeRetained).processAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey);
/*  691 */           } else if (nodeRetained instanceof LinkRetained && (nodeRetained.source.isLive() || nodeRetained.isInSetLive())) {
/*  692 */             int j = paramHashKey.count;
/*  693 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/*  694 */             if (paramHashKey.count == 0) {
/*  695 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/*  697 */             linkRetained.sharedGroup.processAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/*  700 */             paramHashKey.count = j;
/*  701 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/*  702 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*  703 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/*  704 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeAllNodesForScopedLight(int paramInt, LightRetained[] paramArrayOfLightRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  715 */     if (this.inSharedGroup) {
/*  716 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  717 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  718 */         processRemoveAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  722 */       processRemoveAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */   
/*      */   void processRemoveAllNodesForScopedLight(int paramInt, LightRetained[] paramArrayOfLightRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  727 */     if (this.allocatedLights) {
/*  728 */       removeLight(paramInt, paramArrayOfLightRetained, paramHashKey);
/*      */     }
/*      */     
/*  731 */     if (this.source.isLive() && !this.isInClearLive) {
/*  732 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  733 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  734 */         if (nodeRetained != null) {
/*  735 */           if (nodeRetained instanceof GroupRetained && nodeRetained.source.isLive() && !((GroupRetained)nodeRetained).isInClearLive) {
/*      */             
/*  737 */             ((GroupRetained)nodeRetained).processRemoveAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey);
/*  738 */           } else if (nodeRetained instanceof LinkRetained && nodeRetained.source.isLive()) {
/*  739 */             int j = paramHashKey.count;
/*  740 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/*  741 */             if (paramHashKey.count == 0) {
/*  742 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/*  744 */             linkRetained.sharedGroup.processRemoveAllNodesForScopedLight(paramInt, paramArrayOfLightRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/*  747 */             paramHashKey.count = j;
/*  748 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/*  749 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*  750 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/*  751 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void addAllNodesForScopedFog(FogRetained paramFogRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  760 */     if (this.inSharedGroup) {
/*  761 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  762 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  763 */         processAddNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  767 */       processAddNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void processAddNodesForScopedFog(FogRetained paramFogRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  773 */     if (this.allocatedFogs) {
/*  774 */       addFog(paramFogRetained, paramHashKey);
/*      */     }
/*  776 */     if (this.source.isLive() || isInSetLive()) {
/*  777 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  778 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  779 */         if (nodeRetained != null) {
/*  780 */           if (nodeRetained instanceof GroupRetained && (nodeRetained.source.isLive() || nodeRetained.isInSetLive())) {
/*  781 */             ((GroupRetained)nodeRetained).processAddNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey);
/*  782 */           } else if (nodeRetained instanceof LinkRetained && (nodeRetained.source.isLive() || nodeRetained.isInSetLive())) {
/*  783 */             int j = paramHashKey.count;
/*  784 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/*  785 */             if (paramHashKey.count == 0) {
/*  786 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/*  788 */             linkRetained.sharedGroup.processAddNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/*  791 */             paramHashKey.count = j;
/*  792 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/*  793 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*  794 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/*  795 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeAllNodesForScopedFog(FogRetained paramFogRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  806 */     if (this.inSharedGroup) {
/*  807 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  808 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  809 */         processRemoveAllNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  813 */       processRemoveAllNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */   
/*      */   void processRemoveAllNodesForScopedFog(FogRetained paramFogRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  818 */     if (this.allocatedFogs)
/*  819 */       removeFog(paramFogRetained, paramHashKey); 
/*  820 */     if (this.source.isLive() && !this.isInClearLive) {
/*  821 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  822 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  823 */         if (nodeRetained != null) {
/*  824 */           if (nodeRetained instanceof GroupRetained && nodeRetained.source.isLive() && !((GroupRetained)nodeRetained).isInClearLive) {
/*      */             
/*  826 */             ((GroupRetained)nodeRetained).processRemoveAllNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey);
/*  827 */           } else if (nodeRetained instanceof LinkRetained && nodeRetained.source.isLive()) {
/*  828 */             int j = paramHashKey.count;
/*  829 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/*  830 */             if (paramHashKey.count == 0) {
/*  831 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/*  833 */             linkRetained.sharedGroup.processRemoveAllNodesForScopedFog(paramFogRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/*  836 */             paramHashKey.count = j;
/*  837 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/*  838 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*  839 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/*  840 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   void addAllNodesForScopedModelClip(ModelClipRetained paramModelClipRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  848 */     if (this.inSharedGroup) {
/*  849 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  850 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  851 */         processAddNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  855 */       processAddNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void processAddNodesForScopedModelClip(ModelClipRetained paramModelClipRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  862 */     if (this.allocatedMclips) {
/*  863 */       addModelClip(paramModelClipRetained, paramHashKey);
/*      */     }
/*  865 */     if (this.source.isLive() || isInSetLive())
/*  866 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  867 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  868 */         if (nodeRetained != null) {
/*  869 */           if (nodeRetained instanceof GroupRetained && (nodeRetained.source.isLive() || nodeRetained.isInSetLive())) {
/*  870 */             ((GroupRetained)nodeRetained).processAddNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey);
/*      */           }
/*  872 */           else if (nodeRetained instanceof LinkRetained && (nodeRetained.source.isLive() || nodeRetained.isInSetLive())) {
/*  873 */             int j = paramHashKey.count;
/*  874 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/*  875 */             if (paramHashKey.count == 0) {
/*  876 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/*  878 */             linkRetained.sharedGroup.processAddNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/*  881 */             paramHashKey.count = j;
/*  882 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/*  883 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*  884 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/*  885 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       }  
/*      */   }
/*      */   
/*      */   void removeAllNodesForScopedModelClip(ModelClipRetained paramModelClipRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  892 */     if (this.inSharedGroup) {
/*  893 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  894 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  895 */         processRemoveAllNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  899 */       processRemoveAllNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processRemoveAllNodesForScopedModelClip(ModelClipRetained paramModelClipRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  909 */     if (this.allocatedMclips)
/*  910 */       removeModelClip(paramModelClipRetained, paramHashKey); 
/*  911 */     if (this.source.isLive() && !this.isInClearLive) {
/*  912 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  913 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  914 */         if (nodeRetained != null) {
/*  915 */           if (nodeRetained instanceof GroupRetained && nodeRetained.source.isLive() && !((GroupRetained)nodeRetained).isInClearLive) {
/*      */             
/*  917 */             ((GroupRetained)nodeRetained).processRemoveAllNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey);
/*  918 */           } else if (nodeRetained instanceof LinkRetained && nodeRetained.source.isLive()) {
/*  919 */             int j = paramHashKey.count;
/*  920 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/*  921 */             if (paramHashKey.count == 0) {
/*  922 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/*  924 */             linkRetained.sharedGroup.processRemoveAllNodesForScopedModelClip(paramModelClipRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/*  927 */             paramHashKey.count = j;
/*  928 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/*  929 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*  930 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/*  931 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   void addAllNodesForScopedAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  939 */     if (this.inSharedGroup) {
/*  940 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  941 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  942 */         processAddNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  946 */       processAddNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processAddNodesForScopedAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  955 */     if (this.allocatedAltApps)
/*  956 */       addAltApp(paramAlternateAppearanceRetained, paramHashKey); 
/*  957 */     if (this.source.isLive() || isInSetLive()) {
/*  958 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/*  959 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/*  960 */         if (nodeRetained != null) {
/*  961 */           if (nodeRetained instanceof GroupRetained && (nodeRetained.source.isLive() || nodeRetained.isInSetLive())) {
/*  962 */             ((GroupRetained)nodeRetained).processAddNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey);
/*  963 */           } else if (nodeRetained instanceof LinkRetained && nodeRetained.source.isLive()) {
/*  964 */             int j = paramHashKey.count;
/*  965 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/*  966 */             if (paramHashKey.count == 0) {
/*  967 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/*  969 */             linkRetained.sharedGroup.processAddNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/*  972 */             paramHashKey.count = j;
/*  973 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/*  974 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*  975 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/*  976 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   void removeAllNodesForScopedAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/*  984 */     if (this.inSharedGroup) {
/*  985 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  986 */         paramHashKey.set(this.localToVworldKeys[b]);
/*  987 */         processRemoveNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey);
/*      */       } 
/*      */     } else {
/*      */       
/*  991 */       processAddNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processRemoveNodesForScopedAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained, ArrayList paramArrayList, HashKey paramHashKey) {
/* 1000 */     if (this.allocatedAltApps)
/* 1001 */       removeAltApp(paramAlternateAppearanceRetained, paramHashKey); 
/* 1002 */     if (this.source.isLive() && !this.isInClearLive) {
/* 1003 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/* 1004 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/* 1005 */         if (nodeRetained != null) {
/* 1006 */           if (nodeRetained instanceof GroupRetained && nodeRetained.source.isLive() && !((GroupRetained)nodeRetained).isInClearLive) {
/*      */             
/* 1008 */             ((GroupRetained)nodeRetained).processRemoveNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey);
/* 1009 */           } else if (nodeRetained instanceof LinkRetained && nodeRetained.source.isLive()) {
/* 1010 */             int j = paramHashKey.count;
/* 1011 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/* 1012 */             if (paramHashKey.count == 0) {
/* 1013 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/* 1015 */             linkRetained.sharedGroup.processRemoveNodesForScopedAltApp(paramAlternateAppearanceRetained, paramArrayList, paramHashKey.append("+").append(linkRetained.nodeId));
/*      */ 
/*      */             
/* 1018 */             paramHashKey.count = j;
/* 1019 */           } else if (nodeRetained instanceof Shape3DRetained && nodeRetained.source.isLive()) {
/* 1020 */             ((Shape3DRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/* 1021 */           } else if (nodeRetained instanceof MorphRetained && nodeRetained.source.isLive()) {
/* 1022 */             ((MorphRetained)nodeRetained).getMirrorObjects(paramArrayList, paramHashKey);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setLightScope() {
/* 1032 */     if (!this.allocatedLights) {
/* 1033 */       ArrayList arrayList; this.allocatedLights = true;
/* 1034 */       if (this.lights != null) {
/* 1035 */         arrayList = new ArrayList(this.lights.size());
/* 1036 */         int i = this.lights.size();
/* 1037 */         for (byte b = 0; b < i; b++) {
/* 1038 */           ArrayList arrayList1 = (ArrayList)this.lights.get(b);
/* 1039 */           if (arrayList1 != null) {
/* 1040 */             arrayList.add(arrayList1.clone());
/*      */           } else {
/*      */             
/* 1043 */             arrayList.add(null);
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1048 */       } else if (this.inSharedGroup) {
/* 1049 */         arrayList = new ArrayList();
/* 1050 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 1051 */           arrayList.add(new ArrayList());
/*      */         }
/*      */       } else {
/*      */         
/* 1055 */         arrayList = new ArrayList();
/* 1056 */         arrayList.add(new ArrayList());
/*      */       } 
/*      */       
/* 1059 */       this.lights = arrayList;
/*      */     } 
/*      */     
/* 1062 */     this.scopingRefCount++;
/*      */   }
/*      */   
/* 1065 */   void removeLightScope() { this.scopingRefCount--; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFogScope() {
/* 1072 */     if (!this.allocatedFogs) {
/* 1073 */       ArrayList arrayList; this.allocatedFogs = true;
/* 1074 */       if (this.fogs != null) {
/* 1075 */         arrayList = new ArrayList(this.fogs.size());
/* 1076 */         int i = this.fogs.size();
/* 1077 */         for (byte b = 0; b < i; b++) {
/* 1078 */           ArrayList arrayList1 = (ArrayList)this.fogs.get(b);
/* 1079 */           if (arrayList1 != null) {
/* 1080 */             arrayList.add(arrayList1.clone());
/*      */           } else {
/*      */             
/* 1083 */             arrayList.add(null);
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1088 */       } else if (this.inSharedGroup) {
/* 1089 */         arrayList = new ArrayList();
/* 1090 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 1091 */           arrayList.add(new ArrayList());
/*      */         }
/*      */       } else {
/*      */         
/* 1095 */         arrayList = new ArrayList();
/* 1096 */         arrayList.add(new ArrayList());
/*      */       } 
/*      */       
/* 1099 */       this.fogs = arrayList;
/*      */     } 
/*      */     
/* 1102 */     this.scopingRefCount++;
/*      */   }
/*      */   
/* 1105 */   void removeFogScope() { this.scopingRefCount--; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMclipScope() {
/* 1112 */     if (!this.allocatedMclips) {
/* 1113 */       ArrayList arrayList; this.allocatedMclips = true;
/* 1114 */       if (this.modelClips != null) {
/* 1115 */         arrayList = new ArrayList(this.modelClips.size());
/* 1116 */         int i = this.modelClips.size();
/* 1117 */         for (byte b = 0; b < i; b++) {
/* 1118 */           ArrayList arrayList1 = (ArrayList)this.modelClips.get(b);
/* 1119 */           if (arrayList1 != null) {
/* 1120 */             arrayList.add(arrayList1.clone());
/*      */           } else {
/*      */             
/* 1123 */             arrayList.add(null);
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1128 */       } else if (this.inSharedGroup) {
/* 1129 */         arrayList = new ArrayList();
/* 1130 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 1131 */           arrayList.add(new ArrayList());
/*      */         }
/*      */       } else {
/*      */         
/* 1135 */         arrayList = new ArrayList();
/* 1136 */         arrayList.add(new ArrayList());
/*      */       } 
/*      */       
/* 1139 */       this.modelClips = arrayList;
/*      */     } 
/*      */     
/* 1142 */     this.scopingRefCount++;
/*      */   }
/*      */   
/* 1145 */   void removeMclipScope() { this.scopingRefCount--; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAltAppScope() {
/* 1152 */     if (!this.allocatedAltApps) {
/* 1153 */       ArrayList arrayList; this.allocatedAltApps = true;
/* 1154 */       if (this.altAppearances != null) {
/* 1155 */         arrayList = new ArrayList(this.altAppearances.size());
/* 1156 */         int i = this.altAppearances.size();
/* 1157 */         for (byte b = 0; b < i; b++) {
/* 1158 */           ArrayList arrayList1 = (ArrayList)this.altAppearances.get(b);
/* 1159 */           if (arrayList1 != null) {
/* 1160 */             arrayList.add(arrayList1.clone());
/*      */           } else {
/*      */             
/* 1163 */             arrayList.add(null);
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1168 */       } else if (this.inSharedGroup) {
/* 1169 */         arrayList = new ArrayList();
/* 1170 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 1171 */           arrayList.add(new ArrayList());
/*      */         }
/*      */       } else {
/*      */         
/* 1175 */         arrayList = new ArrayList();
/* 1176 */         arrayList.add(new ArrayList());
/*      */       } 
/*      */       
/* 1179 */       this.altAppearances = arrayList;
/*      */     } 
/*      */     
/* 1182 */     this.scopingRefCount++;
/*      */   }
/*      */ 
/*      */   
/* 1186 */   void removeAltAppScope() { this.scopingRefCount--; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1191 */   boolean usedInScoping() { return (this.scopingRefCount > 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addLight(LightRetained[] paramArrayOfLightRetained, int paramInt, HashKey paramHashKey) {
/* 1197 */     if (this.inSharedGroup) {
/* 1198 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1199 */       ArrayList arrayList = (ArrayList)this.lights.get(i);
/* 1200 */       if (arrayList != null) {
/* 1201 */         for (byte b = 0; b < paramInt; b++) {
/* 1202 */           arrayList.add(paramArrayOfLightRetained[b]);
/*      */         }
/*      */       }
/*      */     } else {
/*      */       
/* 1207 */       ArrayList arrayList = (ArrayList)this.lights.get(0);
/* 1208 */       for (byte b = 0; b < paramInt; b++) {
/* 1209 */         arrayList.add(paramArrayOfLightRetained[b]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void addFog(FogRetained paramFogRetained, HashKey paramHashKey) {
/* 1217 */     if (this.inSharedGroup) {
/* 1218 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1219 */       ArrayList arrayList = (ArrayList)this.fogs.get(i);
/* 1220 */       if (arrayList != null) {
/* 1221 */         arrayList.add(paramFogRetained);
/*      */       }
/*      */     } else {
/*      */       
/* 1225 */       ArrayList arrayList = (ArrayList)this.fogs.get(0);
/* 1226 */       arrayList.add(paramFogRetained);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addModelClip(ModelClipRetained paramModelClipRetained, HashKey paramHashKey) {
/* 1234 */     if (this.inSharedGroup) {
/* 1235 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1236 */       ArrayList arrayList = (ArrayList)this.modelClips.get(i);
/* 1237 */       if (arrayList != null) {
/* 1238 */         arrayList.add(paramModelClipRetained);
/*      */       }
/*      */     } else {
/*      */       
/* 1242 */       ArrayList arrayList = (ArrayList)this.modelClips.get(0);
/* 1243 */       arrayList.add(paramModelClipRetained);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void addAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained, HashKey paramHashKey) {
/* 1250 */     if (this.inSharedGroup) {
/* 1251 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1252 */       ArrayList arrayList = (ArrayList)this.altAppearances.get(i);
/* 1253 */       if (arrayList != null) {
/* 1254 */         arrayList.add(paramAlternateAppearanceRetained);
/*      */       }
/*      */     } else {
/*      */       
/* 1258 */       ArrayList arrayList = (ArrayList)this.altAppearances.get(0);
/* 1259 */       arrayList.add(paramAlternateAppearanceRetained);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeFog(FogRetained paramFogRetained, HashKey paramHashKey) {
/* 1269 */     if (this.inSharedGroup) {
/* 1270 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1271 */       ArrayList arrayList = (ArrayList)this.fogs.get(i);
/* 1272 */       if (arrayList != null) {
/* 1273 */         int j = arrayList.indexOf(paramFogRetained);
/* 1274 */         arrayList.remove(j);
/*      */       } 
/*      */     } else {
/*      */       
/* 1278 */       ArrayList arrayList = (ArrayList)this.fogs.get(0);
/* 1279 */       int i = arrayList.indexOf(paramFogRetained);
/* 1280 */       arrayList.remove(i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeModelClip(ModelClipRetained paramModelClipRetained, HashKey paramHashKey) {
/* 1290 */     if (this.inSharedGroup) {
/* 1291 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1292 */       ArrayList arrayList = (ArrayList)this.modelClips.get(i);
/* 1293 */       if (arrayList != null) {
/* 1294 */         int j = arrayList.indexOf(paramModelClipRetained);
/* 1295 */         arrayList.remove(j);
/*      */       } 
/*      */     } else {
/*      */       
/* 1299 */       ArrayList arrayList = (ArrayList)this.modelClips.get(0);
/* 1300 */       int i = arrayList.indexOf(paramModelClipRetained);
/* 1301 */       arrayList.remove(i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained, HashKey paramHashKey) {
/* 1311 */     if (this.inSharedGroup) {
/* 1312 */       int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1313 */       ArrayList arrayList = (ArrayList)this.altAppearances.get(i);
/* 1314 */       if (arrayList != null) {
/* 1315 */         int j = arrayList.indexOf(paramAlternateAppearanceRetained);
/* 1316 */         arrayList.remove(j);
/*      */       } 
/*      */     } else {
/*      */       
/* 1320 */       ArrayList arrayList = (ArrayList)this.altAppearances.get(0);
/* 1321 */       int i = arrayList.indexOf(paramAlternateAppearanceRetained);
/* 1322 */       arrayList.remove(i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePickable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 1329 */     int i = this.children.size() - 1;
/* 1330 */     super.updatePickable(paramArrayOfHashKey, paramArrayOfBoolean);
/* 1331 */     byte b = 0;
/*      */ 
/*      */     
/* 1334 */     for (b = 0; b < i; b++) {
/* 1335 */       NodeRetained nodeRetained1 = (NodeRetained)this.children.get(b);
/* 1336 */       if (nodeRetained1 != null) {
/* 1337 */         nodeRetained1.updatePickable(paramArrayOfHashKey, (boolean[])paramArrayOfBoolean.clone());
/*      */       }
/*      */     } 
/*      */     
/* 1341 */     NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 1342 */     if (nodeRetained != null) {
/* 1343 */       nodeRetained.updatePickable(paramArrayOfHashKey, paramArrayOfBoolean);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void updateCollidable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 1349 */     int i = this.children.size() - 1;
/* 1350 */     super.updateCollidable(paramArrayOfHashKey, paramArrayOfBoolean);
/* 1351 */     byte b = 0;
/*      */ 
/*      */     
/* 1354 */     for (b = 0; b < i; b++) {
/* 1355 */       NodeRetained nodeRetained1 = (NodeRetained)this.children.get(b);
/* 1356 */       if (nodeRetained1 != null) {
/* 1357 */         nodeRetained1.updateCollidable(paramArrayOfHashKey, (boolean[])paramArrayOfBoolean.clone());
/*      */       }
/*      */     } 
/* 1360 */     NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 1361 */     if (nodeRetained != null)
/* 1362 */       nodeRetained.updateCollidable(paramArrayOfHashKey, paramArrayOfBoolean); 
/*      */   }
/*      */   
/*      */   void setAlternateCollisionTarget(boolean paramBoolean) {
/* 1366 */     if (this.collisionTarget == paramBoolean) {
/*      */       return;
/*      */     }
/* 1369 */     this.collisionTarget = paramBoolean;
/*      */     
/* 1371 */     if (this.source.isLive()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1376 */       J3dMessage j3dMessage = new J3dMessage();
/* 1377 */       j3dMessage.threads = 64;
/* 1378 */       j3dMessage.universe = this.universe;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1383 */       CachedTargets[] arrayOfCachedTargets = null;
/*      */       
/* 1385 */       if (paramBoolean) {
/* 1386 */         createMirrorGroup();
/*      */         
/* 1388 */         TargetsInterface targetsInterface = getClosestTargetsInterface(0);
/*      */         
/* 1390 */         if (targetsInterface != null) {
/*      */ 
/*      */ 
/*      */           
/* 1394 */           Targets targets = new Targets();
/* 1395 */           int i = this.mirrorGroup.size();
/* 1396 */           arrayOfCachedTargets = new CachedTargets[i];
/* 1397 */           for (byte b = 0; b < i; b++) {
/* 1398 */             CachedTargets cachedTargets = targetsInterface.getCachedTargets(0, b, -1);
/* 1399 */             if (cachedTargets != null) {
/* 1400 */               targets.addNode((NnuId)this.mirrorGroup.get(b), 6);
/*      */               
/* 1402 */               arrayOfCachedTargets[b] = targets.snapShotAdd(cachedTargets);
/*      */             } else {
/* 1404 */               arrayOfCachedTargets[b] = null;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1411 */           targetsInterface.updateTargetThreads(0, arrayOfCachedTargets);
/*      */           
/* 1413 */           targetsInterface.resetCachedTargets(0, arrayOfCachedTargets, -1);
/*      */         } 
/*      */ 
/*      */         
/* 1417 */         j3dMessage.type = 0;
/* 1418 */         j3dMessage.args[0] = this.mirrorGroup.toArray();
/* 1419 */         j3dMessage.args[1] = targetsInterface;
/* 1420 */         j3dMessage.args[2] = arrayOfCachedTargets;
/*      */       } else {
/*      */         
/* 1423 */         TargetsInterface targetsInterface = getClosestTargetsInterface(0);
/*      */         
/* 1425 */         if (targetsInterface != null) {
/*      */ 
/*      */           
/* 1428 */           Targets targets = new Targets();
/*      */           
/* 1430 */           int i = this.mirrorGroup.size();
/* 1431 */           arrayOfCachedTargets = new CachedTargets[i];
/* 1432 */           for (byte b = 0; b < i; b++) {
/* 1433 */             CachedTargets cachedTargets = targetsInterface.getCachedTargets(0, b, -1);
/* 1434 */             if (cachedTargets != null) {
/* 1435 */               targets.addNode((NnuId)this.mirrorGroup.get(b), 6);
/*      */ 
/*      */               
/* 1438 */               arrayOfCachedTargets[b] = targets.snapShotRemove(cachedTargets);
/*      */             } else {
/* 1440 */               arrayOfCachedTargets[b] = null;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1445 */           targetsInterface.updateTargetThreads(0, arrayOfCachedTargets);
/*      */           
/* 1447 */           targetsInterface.resetCachedTargets(0, arrayOfCachedTargets, -1);
/*      */         } 
/*      */ 
/*      */         
/* 1451 */         j3dMessage.type = 1;
/* 1452 */         j3dMessage.args[0] = this.mirrorGroup.toArray();
/* 1453 */         j3dMessage.args[1] = targetsInterface;
/* 1454 */         j3dMessage.args[2] = arrayOfCachedTargets;
/* 1455 */         this.mirrorGroup = null;
/*      */       } 
/* 1457 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 1462 */   boolean getAlternateCollisionTarget() { return this.collisionTarget; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1473 */   void checkSetLive(NodeRetained paramNodeRetained1, int paramInt1, J3dMessage[] paramArrayOfJ3dMessage, int paramInt2, NodeRetained paramNodeRetained2) { checkSetLive(paramNodeRetained1, paramInt1, this.localToVworldKeys, this.inSharedGroup, paramArrayOfJ3dMessage, paramInt2, paramNodeRetained2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void checkSetLive(NodeRetained paramNodeRetained1, int paramInt1, HashKey[] paramArrayOfHashKey, boolean paramBoolean, J3dMessage[] paramArrayOfJ3dMessage, int paramInt2, NodeRetained paramNodeRetained2) {
/* 1486 */     SceneGraphObject sceneGraphObject = this.source;
/*      */ 
/*      */     
/* 1489 */     boolean bool1 = false;
/* 1490 */     boolean bool2 = true;
/* 1491 */     boolean bool3 = true;
/*      */     
/* 1493 */     if (sceneGraphObject.isLive()) {
/*      */       boolean[] arrayOfBoolean2, arrayOfBoolean1;
/* 1495 */       SetLiveState setLiveState = this.universe.setLiveState;
/* 1496 */       setLiveState.reset(this.locale);
/* 1497 */       setLiveState.refCount = this.refCount;
/* 1498 */       setLiveState.inSharedGroup = paramBoolean;
/* 1499 */       setLiveState.inBackgroundGroup = this.inBackgroundGroup;
/* 1500 */       setLiveState.inViewSpecificGroup = this.inViewSpecificGroup;
/* 1501 */       setLiveState.geometryBackground = this.geometryBackground;
/* 1502 */       setLiveState.keys = paramArrayOfHashKey;
/* 1503 */       setLiveState.viewLists = this.viewLists;
/* 1504 */       setLiveState.parentBranchGroupPaths = this.branchGroupPaths;
/*      */ 
/*      */ 
/*      */       
/* 1508 */       setLiveState.branchGroupPaths = (ArrayList)this.branchGroupPaths.clone();
/* 1509 */       setLiveState.orderedPaths = this.orderedPaths;
/*      */ 
/*      */ 
/*      */       
/* 1513 */       setLiveState.lights = this.lights;
/* 1514 */       setLiveState.altAppearances = this.altAppearances;
/* 1515 */       setLiveState.fogs = this.fogs;
/* 1516 */       setLiveState.modelClips = this.modelClips;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1521 */       if (!this.inSharedGroup) {
/* 1522 */         arrayOfBoolean1 = new boolean[1];
/* 1523 */         arrayOfBoolean2 = new boolean[1];
/*      */       } else {
/* 1525 */         arrayOfBoolean1 = new boolean[this.localToVworldKeys.length];
/* 1526 */         arrayOfBoolean2 = new boolean[this.localToVworldKeys.length];
/*      */       } 
/* 1528 */       findPickableFlags(arrayOfBoolean1);
/* 1529 */       super.updatePickable(null, arrayOfBoolean1);
/* 1530 */       setLiveState.pickable = arrayOfBoolean1;
/*      */       
/* 1532 */       findCollidableFlags(arrayOfBoolean2);
/* 1533 */       super.updateCollidable(null, arrayOfBoolean2);
/* 1534 */       setLiveState.collidable = arrayOfBoolean2;
/*      */ 
/*      */       
/* 1537 */       TargetsInterface targetsInterface1 = initTransformStates(setLiveState, true);
/* 1538 */       TargetsInterface targetsInterface2 = initSwitchStates(setLiveState, this, paramNodeRetained1, paramNodeRetained2, true);
/*      */ 
/*      */       
/* 1541 */       if (setLiveState.inViewSpecificGroup && setLiveState.changedViewGroup == null) {
/*      */         
/* 1543 */         setLiveState.changedViewGroup = new ArrayList();
/* 1544 */         setLiveState.changedViewList = new ArrayList();
/* 1545 */         setLiveState.keyList = new int[10];
/* 1546 */         setLiveState.viewScopedNodeList = new ArrayList();
/* 1547 */         setLiveState.scopedNodesViewList = new ArrayList();
/*      */       } 
/*      */       
/* 1550 */       childCheckSetLive(paramNodeRetained1, paramInt1, setLiveState, paramNodeRetained2);
/*      */       
/* 1552 */       CachedTargets[] arrayOfCachedTargets = null;
/* 1553 */       arrayOfCachedTargets = updateTransformStates(setLiveState, targetsInterface1, true);
/* 1554 */       updateSwitchStates(setLiveState, targetsInterface2, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1562 */       if (paramArrayOfJ3dMessage == null) {
/* 1563 */         byte b1 = 2;
/* 1564 */         if (setLiveState.ogList.size() > 0) {
/* 1565 */           b1++;
/*      */         } else {
/*      */           
/* 1568 */           bool2 = false;
/*      */         } 
/* 1570 */         if (setLiveState.changedViewGroup != null) {
/* 1571 */           b1++;
/*      */         } else {
/*      */           
/* 1574 */           bool3 = false;
/*      */         } 
/*      */         
/* 1577 */         paramArrayOfJ3dMessage = new J3dMessage[b1];
/* 1578 */         paramInt2 = 0;
/* 1579 */         for (byte b2 = 0; b2 < b1; b2++) {
/* 1580 */           paramArrayOfJ3dMessage[b2] = new J3dMessage();
/*      */         }
/* 1582 */         bool1 = true;
/*      */       } 
/*      */       
/* 1585 */       if (bool2) {
/* 1586 */         J3dMessage j3dMessage1 = paramArrayOfJ3dMessage[paramInt2++];
/* 1587 */         j3dMessage1.threads = 4224;
/*      */         
/* 1589 */         j3dMessage1.type = 32;
/* 1590 */         j3dMessage1.universe = this.universe;
/* 1591 */         j3dMessage1.args[0] = setLiveState.ogList.toArray();
/* 1592 */         j3dMessage1.args[1] = setLiveState.ogChildIdList.toArray();
/* 1593 */         j3dMessage1.args[2] = setLiveState.ogOrderedIdList.toArray();
/* 1594 */         j3dMessage1.args[3] = setLiveState.ogCIOList.toArray();
/* 1595 */         j3dMessage1.args[4] = setLiveState.ogCIOTableList.toArray();
/*      */       } 
/*      */ 
/*      */       
/* 1599 */       if (bool3) {
/* 1600 */         J3dMessage j3dMessage1 = paramArrayOfJ3dMessage[paramInt2++];
/* 1601 */         j3dMessage1.threads = 4096;
/* 1602 */         j3dMessage1.type = 57;
/* 1603 */         j3dMessage1.universe = this.universe;
/* 1604 */         j3dMessage1.args[0] = setLiveState.changedViewGroup;
/* 1605 */         j3dMessage1.args[1] = setLiveState.changedViewList;
/* 1606 */         j3dMessage1.args[2] = setLiveState.keyList;
/*      */       } 
/*      */       
/* 1609 */       J3dMessage j3dMessage = paramArrayOfJ3dMessage[paramInt2++];
/* 1610 */       j3dMessage.threads = setLiveState.notifyThreads;
/* 1611 */       j3dMessage.type = 0;
/* 1612 */       j3dMessage.universe = this.universe;
/* 1613 */       j3dMessage.args[0] = setLiveState.nodeList.toArray();
/* 1614 */       if (arrayOfCachedTargets != null) {
/* 1615 */         j3dMessage.args[1] = targetsInterface1;
/* 1616 */         j3dMessage.args[2] = arrayOfCachedTargets;
/*      */       } else {
/* 1618 */         j3dMessage.args[1] = null;
/* 1619 */         j3dMessage.args[2] = null;
/*      */       } 
/*      */       
/* 1622 */       if (setLiveState.viewScopedNodeList != null) {
/* 1623 */         j3dMessage.args[3] = setLiveState.viewScopedNodeList;
/* 1624 */         j3dMessage.args[4] = setLiveState.scopedNodesViewList;
/*      */       } 
/*      */ 
/*      */       
/* 1628 */       int i = setLiveState.behaviorNodes.size();
/*      */       
/* 1630 */       for (byte b = 0; b < i; b++) {
/*      */         
/* 1632 */         BehaviorRetained behaviorRetained = (BehaviorRetained)setLiveState.behaviorNodes.get(b);
/* 1633 */         behaviorRetained.executeInitialize();
/*      */       } 
/*      */       
/* 1636 */       setLiveState.behaviorNodes.clear();
/*      */       
/* 1638 */       j3dMessage = paramArrayOfJ3dMessage[paramInt2++];
/*      */       
/* 1640 */       j3dMessage.threads = 256;
/* 1641 */       j3dMessage.type = 49;
/* 1642 */       j3dMessage.universe = this.universe;
/*      */       
/* 1644 */       if (bool1 == true) {
/* 1645 */         VirtualUniverse.mc.processMessage(paramArrayOfJ3dMessage);
/*      */       }
/*      */       
/* 1648 */       if (this.nodeType == 18) {
/*      */         
/* 1650 */         SwitchRetained switchRetained = (SwitchRetained)this;
/* 1651 */         switchRetained.setWhichChild(switchRetained.whichChild, true);
/*      */       } 
/*      */ 
/*      */       
/* 1655 */       setLiveState.reset(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1664 */   void checkClearLive(NodeRetained paramNodeRetained1, J3dMessage[] paramArrayOfJ3dMessage, int paramInt1, int paramInt2, NodeRetained paramNodeRetained2) { checkClearLive(paramNodeRetained1, this.localToVworldKeys, this.inSharedGroup, paramArrayOfJ3dMessage, paramInt1, paramInt2, paramNodeRetained2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void checkClearLive(NodeRetained paramNodeRetained1, HashKey[] paramArrayOfHashKey, boolean paramBoolean, J3dMessage[] paramArrayOfJ3dMessage, int paramInt1, int paramInt2, NodeRetained paramNodeRetained2) {
/* 1679 */     SceneGraphObject sceneGraphObject = this.source;
/*      */     
/* 1681 */     boolean bool1 = false;
/* 1682 */     boolean bool2 = true;
/* 1683 */     boolean bool3 = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1688 */     if (sceneGraphObject.isLive()) {
/* 1689 */       SetLiveState setLiveState = this.universe.setLiveState;
/*      */       
/* 1691 */       setLiveState.reset(this.locale);
/* 1692 */       setLiveState.refCount = this.refCount;
/* 1693 */       setLiveState.inSharedGroup = paramBoolean;
/* 1694 */       setLiveState.inBackgroundGroup = this.inBackgroundGroup;
/* 1695 */       setLiveState.inViewSpecificGroup = this.inViewSpecificGroup;
/* 1696 */       setLiveState.keys = paramArrayOfHashKey;
/* 1697 */       setLiveState.fogs = this.fogs;
/* 1698 */       setLiveState.lights = this.lights;
/* 1699 */       setLiveState.altAppearances = this.altAppearances;
/* 1700 */       setLiveState.modelClips = this.modelClips;
/*      */ 
/*      */       
/* 1703 */       if (setLiveState.inViewSpecificGroup && setLiveState.changedViewGroup == null) {
/*      */         
/* 1705 */         setLiveState.changedViewGroup = new ArrayList();
/* 1706 */         setLiveState.changedViewList = new ArrayList();
/* 1707 */         setLiveState.keyList = new int[10];
/* 1708 */         setLiveState.viewScopedNodeList = new ArrayList();
/* 1709 */         setLiveState.scopedNodesViewList = new ArrayList();
/*      */       } 
/*      */       
/* 1712 */       if (this instanceof OrderedGroupRetained && paramNodeRetained2 == null) {
/*      */         
/* 1714 */         setLiveState.ogList.add(this);
/* 1715 */         setLiveState.ogChildIdList.add(new Integer(paramInt2));
/* 1716 */         setLiveState.ogCIOList.add(this);
/* 1717 */         int[] arrayOfInt = null;
/* 1718 */         OrderedGroupRetained orderedGroupRetained = (OrderedGroupRetained)this;
/* 1719 */         if (orderedGroupRetained.userChildIndexOrder != null) {
/* 1720 */           arrayOfInt = new int[orderedGroupRetained.userChildIndexOrder.length];
/* 1721 */           System.arraycopy(orderedGroupRetained.userChildIndexOrder, 0, arrayOfInt, 0, orderedGroupRetained.userChildIndexOrder.length);
/*      */         } 
/*      */         
/* 1724 */         setLiveState.ogCIOTableList.add(arrayOfInt);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1729 */       setLiveState.viewLists = this.viewLists;
/*      */ 
/*      */       
/* 1732 */       TargetsInterface targetsInterface1 = initTransformStates(setLiveState, false);
/* 1733 */       TargetsInterface targetsInterface2 = initSwitchStates(setLiveState, this, paramNodeRetained1, paramNodeRetained2, false);
/*      */       
/* 1735 */       paramNodeRetained1.clearLive(setLiveState);
/*      */       
/* 1737 */       CachedTargets[] arrayOfCachedTargets = null;
/* 1738 */       arrayOfCachedTargets = updateTransformStates(setLiveState, targetsInterface1, false);
/* 1739 */       updateSwitchStates(setLiveState, targetsInterface2, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1747 */       if (paramArrayOfJ3dMessage == null) {
/* 1748 */         byte b1 = 1;
/* 1749 */         if (setLiveState.ogList.size() > 0) {
/* 1750 */           b1++;
/*      */         } else {
/*      */           
/* 1753 */           bool2 = false;
/*      */         } 
/*      */         
/* 1756 */         if (setLiveState.changedViewGroup != null) {
/* 1757 */           b1++;
/*      */         } else {
/*      */           
/* 1760 */           bool3 = false;
/*      */         } 
/*      */         
/* 1763 */         paramArrayOfJ3dMessage = new J3dMessage[b1];
/* 1764 */         paramInt1 = 0;
/* 1765 */         for (byte b2 = 0; b2 < b1; b2++) {
/* 1766 */           paramArrayOfJ3dMessage[b2] = new J3dMessage();
/*      */         }
/* 1768 */         bool1 = true;
/*      */       } 
/*      */       
/* 1771 */       if (bool2) {
/* 1772 */         J3dMessage j3dMessage1 = paramArrayOfJ3dMessage[paramInt1++];
/* 1773 */         j3dMessage1.threads = 4224;
/*      */         
/* 1775 */         j3dMessage1.type = 33;
/* 1776 */         j3dMessage1.universe = this.universe;
/* 1777 */         j3dMessage1.args[0] = setLiveState.ogList.toArray();
/* 1778 */         j3dMessage1.args[1] = setLiveState.ogChildIdList.toArray();
/* 1779 */         j3dMessage1.args[3] = setLiveState.ogCIOList.toArray();
/* 1780 */         j3dMessage1.args[4] = setLiveState.ogCIOTableList.toArray();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1787 */       J3dMessage j3dMessage = paramArrayOfJ3dMessage[paramInt1++];
/* 1788 */       j3dMessage.threads = setLiveState.notifyThreads;
/* 1789 */       j3dMessage.type = 1;
/* 1790 */       j3dMessage.universe = this.universe;
/* 1791 */       j3dMessage.args[0] = setLiveState.nodeList.toArray();
/*      */       
/* 1793 */       if (arrayOfCachedTargets != null) {
/* 1794 */         j3dMessage.args[1] = targetsInterface1;
/* 1795 */         j3dMessage.args[2] = arrayOfCachedTargets;
/*      */       } else {
/* 1797 */         j3dMessage.args[1] = null;
/* 1798 */         j3dMessage.args[2] = null;
/*      */       } 
/* 1800 */       if (setLiveState.viewScopedNodeList != null) {
/* 1801 */         j3dMessage.args[3] = setLiveState.viewScopedNodeList;
/* 1802 */         j3dMessage.args[4] = setLiveState.scopedNodesViewList;
/*      */       } 
/*      */       
/* 1805 */       if (bool3) {
/* 1806 */         j3dMessage = paramArrayOfJ3dMessage[paramInt1++];
/* 1807 */         j3dMessage.threads = 4096;
/* 1808 */         j3dMessage.type = 58;
/* 1809 */         j3dMessage.universe = this.universe;
/* 1810 */         j3dMessage.args[0] = setLiveState.changedViewGroup;
/* 1811 */         j3dMessage.args[1] = setLiveState.keyList;
/*      */       } 
/*      */       
/* 1814 */       if (bool1 == true) {
/* 1815 */         VirtualUniverse.mc.processMessage(paramArrayOfJ3dMessage);
/*      */       }
/*      */       
/* 1818 */       setLiveState.reset(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   TargetsInterface initTransformStates(SetLiveState paramSetLiveState, boolean paramBoolean) {
/* 1824 */     int i = this.inSharedGroup ? paramSetLiveState.keys.length : 1;
/* 1825 */     TargetsInterface targetsInterface = getClosestTargetsInterface(0);
/*      */ 
/*      */ 
/*      */     
/* 1829 */     if (paramBoolean) {
/* 1830 */       paramSetLiveState.currentTransforms = this.localToVworld;
/* 1831 */       paramSetLiveState.currentTransformsIndex = this.localToVworldIndex;
/* 1832 */       paramSetLiveState.localToVworldKeys = this.localToVworldKeys;
/* 1833 */       paramSetLiveState.localToVworld = paramSetLiveState.currentTransforms;
/* 1834 */       paramSetLiveState.localToVworldIndex = paramSetLiveState.currentTransformsIndex;
/*      */       
/* 1836 */       paramSetLiveState.parentTransformLink = this.parentTransformLink;
/* 1837 */       if (this.parentTransformLink != null) {
/* 1838 */         if (this.parentTransformLink instanceof TransformGroupRetained) {
/*      */           
/* 1840 */           TransformGroupRetained transformGroupRetained = (TransformGroupRetained)this.parentTransformLink;
/* 1841 */           paramSetLiveState.childTransformLinks = transformGroupRetained.childTransformLinks;
/*      */         } else {
/*      */           
/* 1844 */           SharedGroupRetained sharedGroupRetained = (SharedGroupRetained)this.parentTransformLink;
/* 1845 */           paramSetLiveState.childTransformLinks = sharedGroupRetained.childTransformLinks;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1850 */     int[] arrayOfInt = new int[i];
/* 1851 */     findTransformLevels(arrayOfInt);
/* 1852 */     paramSetLiveState.transformLevels = arrayOfInt;
/*      */     
/* 1854 */     if (targetsInterface != null) {
/* 1855 */       Targets[] arrayOfTargets = new Targets[i];
/* 1856 */       for (byte b = 0; b < i; b++) {
/* 1857 */         if (paramSetLiveState.transformLevels[b] >= 0) {
/* 1858 */           arrayOfTargets[b] = new Targets();
/*      */         } else {
/* 1860 */           arrayOfTargets[b] = null;
/*      */         } 
/*      */       } 
/* 1863 */       paramSetLiveState.transformTargets = arrayOfTargets;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1870 */     return targetsInterface;
/*      */   }
/*      */ 
/*      */   
/*      */   CachedTargets[] updateTransformStates(SetLiveState paramSetLiveState, TargetsInterface paramTargetsInterface, boolean paramBoolean) {
/* 1875 */     CachedTargets[] arrayOfCachedTargets = null;
/*      */     
/* 1877 */     if (paramTargetsInterface != null) {
/* 1878 */       if (paramBoolean) {
/*      */         
/* 1880 */         boolean bool = false;
/*      */ 
/*      */         
/* 1883 */         arrayOfCachedTargets = new CachedTargets[this.localToVworld.length];
/*      */ 
/*      */         
/* 1886 */         if (!this.inSharedGroup) {
/* 1887 */           if (paramSetLiveState.transformTargets[false] != null) {
/* 1888 */             CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(0, 0, -1);
/*      */             
/* 1890 */             if (cachedTargets != null) {
/* 1891 */               arrayOfCachedTargets[0] = paramSetLiveState.transformTargets[0].snapShotAdd(cachedTargets);
/*      */             }
/*      */           } else {
/* 1894 */             arrayOfCachedTargets[0] = null;
/*      */           } 
/*      */         } else {
/* 1897 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++)
/*      */           {
/* 1899 */             if (paramSetLiveState.transformTargets[b] != null) {
/* 1900 */               CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(0, b, -1);
/*      */               
/* 1902 */               if (cachedTargets != null) {
/* 1903 */                 arrayOfCachedTargets[b] = paramSetLiveState.transformTargets[b].snapShotAdd(cachedTargets);
/*      */               }
/*      */             } else {
/*      */               
/* 1907 */               arrayOfCachedTargets[b] = null;
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1916 */         arrayOfCachedTargets = new CachedTargets[this.localToVworld.length];
/*      */         
/* 1918 */         if (!this.inSharedGroup) {
/* 1919 */           if (paramSetLiveState.transformTargets[false] != null) {
/* 1920 */             CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(0, 0, -1);
/*      */             
/* 1922 */             if (cachedTargets != null) {
/* 1923 */               arrayOfCachedTargets[0] = paramSetLiveState.transformTargets[0].snapShotRemove(cachedTargets);
/*      */             }
/*      */           } else {
/*      */             
/* 1927 */             arrayOfCachedTargets[0] = null;
/*      */           } 
/*      */         } else {
/* 1930 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 1931 */             if (paramSetLiveState.transformTargets[b] != null) {
/* 1932 */               CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(0, b, -1);
/*      */               
/* 1934 */               if (cachedTargets != null) {
/* 1935 */                 arrayOfCachedTargets[b] = paramSetLiveState.transformTargets[b].snapShotRemove(cachedTargets);
/*      */               }
/*      */             } else {
/*      */               
/* 1939 */               arrayOfCachedTargets[b] = null;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1947 */       paramTargetsInterface.updateTargetThreads(0, arrayOfCachedTargets);
/*      */       
/* 1949 */       paramTargetsInterface.resetCachedTargets(0, arrayOfCachedTargets, -1);
/*      */     } 
/*      */ 
/*      */     
/* 1953 */     return arrayOfCachedTargets;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   TargetsInterface initSwitchStates(SetLiveState paramSetLiveState, NodeRetained paramNodeRetained1, NodeRetained paramNodeRetained2, NodeRetained paramNodeRetained3, boolean paramBoolean) {
/* 1963 */     findSwitchInfo(paramSetLiveState, paramNodeRetained1, paramNodeRetained2, paramNodeRetained3);
/* 1964 */     TargetsInterface targetsInterface = getClosestTargetsInterface(1);
/*      */     
/* 1966 */     if (targetsInterface != null) {
/* 1967 */       Targets[] arrayOfTargets = null;
/* 1968 */       int i = this.inSharedGroup ? paramSetLiveState.keys.length : 1;
/* 1969 */       arrayOfTargets = new Targets[i];
/* 1970 */       for (byte b = 0; b < i; b++) {
/* 1971 */         if (paramSetLiveState.switchLevels[b] >= 0) {
/* 1972 */           arrayOfTargets[b] = new Targets();
/*      */         } else {
/* 1974 */           arrayOfTargets[b] = null;
/*      */         } 
/*      */       } 
/* 1977 */       paramSetLiveState.switchTargets = arrayOfTargets;
/*      */     } 
/*      */     
/* 1980 */     if (paramBoolean) {
/*      */       
/* 1982 */       if (this.nodeType == 18) {
/* 1983 */         int i = this.parentSwitchLinkChildIndex;
/* 1984 */         paramSetLiveState.childSwitchLinks = (ArrayList)this.childrenSwitchLinks.get(i);
/* 1985 */         paramSetLiveState.parentSwitchLink = this;
/*      */       
/*      */       }
/* 1988 */       else if (this.nodeType == 22) {
/* 1989 */         int i = this.parentSwitchLinkChildIndex;
/* 1990 */         paramSetLiveState.childSwitchLinks = (ArrayList)this.childrenSwitchLinks.get(i);
/* 1991 */         paramSetLiveState.parentSwitchLink = this;
/*      */       } else {
/*      */         
/* 1994 */         paramSetLiveState.parentSwitchLink = this.parentSwitchLink;
/* 1995 */         if (this.parentSwitchLink != null) {
/* 1996 */           int i = this.parentSwitchLinkChildIndex;
/* 1997 */           paramSetLiveState.childSwitchLinks = (ArrayList)this.parentSwitchLink.childrenSwitchLinks.get(i);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2002 */       if (targetsInterface != null) {
/* 2003 */         paramSetLiveState.switchStates = targetsInterface.getTargetsData(1, this.parentSwitchLinkChildIndex);
/*      */       }
/*      */       else {
/*      */         
/* 2007 */         paramSetLiveState.switchStates = new ArrayList(1);
/* 2008 */         paramSetLiveState.switchStates.add(new SwitchState(false));
/*      */       } 
/*      */     } 
/* 2011 */     return targetsInterface;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateSwitchStates(SetLiveState paramSetLiveState, TargetsInterface paramTargetsInterface, boolean paramBoolean) {
/* 2020 */     if (paramTargetsInterface != null) {
/* 2021 */       if (paramBoolean) {
/* 2022 */         CachedTargets[] arrayOfCachedTargets = null;
/*      */ 
/*      */         
/* 2025 */         arrayOfCachedTargets = new CachedTargets[this.localToVworld.length];
/*      */ 
/*      */         
/* 2028 */         if (!this.inSharedGroup) {
/*      */           
/* 2030 */           if (paramSetLiveState.switchTargets[false] != null) {
/* 2031 */             CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(1, 0, this.parentSwitchLinkChildIndex);
/*      */ 
/*      */             
/* 2034 */             if (cachedTargets != null) {
/* 2035 */               arrayOfCachedTargets[0] = paramSetLiveState.switchTargets[0].snapShotAdd(cachedTargets);
/*      */             } else {
/* 2037 */               arrayOfCachedTargets[0] = paramSetLiveState.switchTargets[0].snapShotInit();
/*      */             } 
/*      */           } else {
/* 2040 */             arrayOfCachedTargets[0] = null;
/*      */           } 
/*      */         } else {
/* 2043 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 2044 */             if (paramSetLiveState.switchTargets[b] != null) {
/* 2045 */               CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(1, b, this.parentSwitchLinkChildIndex);
/*      */ 
/*      */               
/* 2048 */               if (cachedTargets != null) {
/* 2049 */                 arrayOfCachedTargets[b] = paramSetLiveState.switchTargets[b].snapShotAdd(cachedTargets);
/*      */               } else {
/*      */                 
/* 2052 */                 arrayOfCachedTargets[b] = paramSetLiveState.switchTargets[b].snapShotInit();
/*      */               } 
/*      */             } else {
/*      */               
/* 2056 */               arrayOfCachedTargets[b] = null;
/*      */             } 
/*      */           } 
/*      */         } 
/* 2060 */         paramTargetsInterface.resetCachedTargets(1, arrayOfCachedTargets, this.parentSwitchLinkChildIndex);
/*      */         
/* 2062 */         if (paramTargetsInterface instanceof SwitchRetained) {
/* 2063 */           ((SwitchRetained)paramTargetsInterface).traverseSwitchParent();
/* 2064 */         } else if (paramTargetsInterface instanceof SharedGroupRetained) {
/* 2065 */           ((SharedGroupRetained)paramTargetsInterface).traverseSwitchParent();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2070 */         CachedTargets[] arrayOfCachedTargets = new CachedTargets[this.localToVworld.length];
/*      */ 
/*      */         
/* 2073 */         if (!this.inSharedGroup) {
/* 2074 */           if (paramSetLiveState.switchTargets[false] != null) {
/* 2075 */             CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(1, 0, this.parentSwitchLinkChildIndex);
/*      */ 
/*      */             
/* 2078 */             if (cachedTargets != null) {
/* 2079 */               arrayOfCachedTargets[0] = paramSetLiveState.switchTargets[0].snapShotRemove(cachedTargets);
/*      */             }
/*      */           } else {
/*      */             
/* 2083 */             arrayOfCachedTargets[0] = null;
/*      */           } 
/*      */         } else {
/* 2086 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 2087 */             if (paramSetLiveState.switchTargets[b] != null) {
/* 2088 */               CachedTargets cachedTargets = paramTargetsInterface.getCachedTargets(1, b, this.parentSwitchLinkChildIndex);
/*      */ 
/*      */ 
/*      */               
/* 2092 */               if (cachedTargets != null) {
/* 2093 */                 arrayOfCachedTargets[b] = paramSetLiveState.switchTargets[b].snapShotRemove(cachedTargets);
/*      */               }
/*      */             } else {
/*      */               
/* 2097 */               arrayOfCachedTargets[b] = null;
/*      */             } 
/*      */           } 
/*      */         } 
/* 2101 */         paramTargetsInterface.resetCachedTargets(1, arrayOfCachedTargets, this.parentSwitchLinkChildIndex);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void appendChildrenData() {}
/*      */ 
/*      */   
/*      */   void insertChildrenData(int paramInt) {}
/*      */ 
/*      */   
/*      */   void removeChildrenData(int paramInt) {}
/*      */   
/* 2115 */   TargetsInterface getClosestTargetsInterface(int paramInt) { return (paramInt == 0) ? (TargetsInterface)this.parentTransformLink : (TargetsInterface)this.parentSwitchLink; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateLocalToVworld() {
/* 2125 */     for (int i = this.children.size() - 1; i >= 0; i--) {
/* 2126 */       NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/* 2127 */       if (nodeRetained != null)
/* 2128 */         nodeRetained.updateLocalToVworld(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   void setNodeData(SetLiveState paramSetLiveState) {
/* 2133 */     super.setNodeData(paramSetLiveState);
/* 2134 */     this.orderedPaths = paramSetLiveState.orderedPaths;
/*      */   }
/*      */ 
/*      */   
/*      */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 2139 */     if (!this.inSharedGroup || paramSetLiveState.keys.length == this.localToVworld.length) {
/* 2140 */       this.orderedPaths = null;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2145 */       this.orderedPaths = paramSetLiveState.orderedPaths;
/*      */     } 
/* 2147 */     super.removeNodeData(paramSetLiveState);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(SetLiveState paramSetLiveState) {
/* 2153 */     doSetLive(paramSetLiveState);
/* 2154 */     markAsLive();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void childDoSetLive(NodeRetained paramNodeRetained, int paramInt, SetLiveState paramSetLiveState) {
/* 2160 */     if (paramNodeRetained != null) {
/* 2161 */       paramNodeRetained.setLive(paramSetLiveState);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2168 */   void childCheckSetLive(NodeRetained paramNodeRetained1, int paramInt, SetLiveState paramSetLiveState, NodeRetained paramNodeRetained2) { paramNodeRetained1.setLive(paramSetLiveState); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doSetLive(SetLiveState paramSetLiveState) {
/* 2177 */     BoundingSphere boundingSphere = new BoundingSphere();
/*      */     
/* 2179 */     super.doSetLive(paramSetLiveState);
/* 2180 */     this.locale = paramSetLiveState.locale;
/*      */     
/* 2182 */     this.inViewSpecificGroup = paramSetLiveState.inViewSpecificGroup;
/* 2183 */     int i = this.children.size();
/* 2184 */     ArrayList arrayList1 = paramSetLiveState.lights;
/* 2185 */     ArrayList arrayList2 = paramSetLiveState.fogs;
/* 2186 */     ArrayList arrayList3 = paramSetLiveState.altAppearances;
/* 2187 */     ArrayList arrayList4 = paramSetLiveState.modelClips;
/*      */     
/* 2189 */     boolean[] arrayOfBoolean1 = (boolean[])paramSetLiveState.pickable.clone();
/* 2190 */     boolean[] arrayOfBoolean2 = (boolean[])paramSetLiveState.collidable.clone();
/* 2191 */     boolean[] arrayOfBoolean3 = new boolean[arrayOfBoolean1.length];
/* 2192 */     boolean[] arrayOfBoolean4 = new boolean[arrayOfBoolean2.length];
/* 2193 */     ArrayList arrayList5 = paramSetLiveState.branchGroupPaths;
/* 2194 */     setScopingInfo(paramSetLiveState);
/*      */ 
/*      */     
/* 2197 */     if (!(this instanceof ViewSpecificGroupRetained)) {
/* 2198 */       this.viewLists = paramSetLiveState.viewLists;
/*      */     }
/*      */     
/* 2201 */     for (byte b = 0; b < i; b++) {
/* 2202 */       NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/*      */ 
/*      */       
/* 2205 */       System.arraycopy(arrayOfBoolean1, 0, arrayOfBoolean3, 0, arrayOfBoolean1.length);
/*      */       
/* 2207 */       System.arraycopy(arrayOfBoolean2, 0, arrayOfBoolean4, 0, arrayOfBoolean2.length);
/*      */       
/* 2209 */       paramSetLiveState.pickable = arrayOfBoolean3;
/* 2210 */       paramSetLiveState.collidable = arrayOfBoolean4;
/*      */ 
/*      */       
/* 2213 */       paramSetLiveState.parentBranchGroupPaths = this.branchGroupPaths;
/* 2214 */       paramSetLiveState.branchGroupPaths = (ArrayList)arrayList5.clone();
/* 2215 */       paramSetLiveState.inViewSpecificGroup = this.inViewSpecificGroup;
/* 2216 */       childDoSetLive(nodeRetained, b, paramSetLiveState);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2221 */     if (this.collisionTarget) {
/* 2222 */       processCollisionTarget(paramSetLiveState);
/*      */     }
/*      */     
/* 2225 */     paramSetLiveState.lights = arrayList1;
/* 2226 */     paramSetLiveState.fogs = arrayList2;
/* 2227 */     paramSetLiveState.altAppearances = arrayList3;
/* 2228 */     paramSetLiveState.modelClips = arrayList4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setScopingInfo(SetLiveState paramSetLiveState) {
/* 2236 */     if (this.allocatedLights) {
/* 2237 */       if (paramSetLiveState.lights != null)
/*      */       {
/* 2239 */         if (this.inSharedGroup) {
/* 2240 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 2241 */             int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */             
/* 2243 */             ArrayList arrayList1 = (ArrayList)this.lights.get(i);
/* 2244 */             ArrayList arrayList2 = (ArrayList)paramSetLiveState.lights.get(b);
/* 2245 */             if (arrayList2 != null) {
/* 2246 */               int j = arrayList2.size();
/* 2247 */               for (byte b1 = 0; b1 < j; b1++) {
/* 2248 */                 arrayList1.add(arrayList2.get(b1));
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 2255 */           ArrayList arrayList1 = (ArrayList)this.lights.get(0);
/* 2256 */           ArrayList arrayList2 = (ArrayList)paramSetLiveState.lights.get(0);
/* 2257 */           int i = arrayList2.size();
/* 2258 */           for (byte b = 0; b < i; b++) {
/* 2259 */             arrayList1.add(arrayList2.get(b));
/*      */           }
/*      */         } 
/*      */       }
/* 2263 */       paramSetLiveState.lights = this.lights;
/*      */     } else {
/*      */       
/* 2266 */       this.lights = paramSetLiveState.lights;
/*      */     } 
/*      */     
/* 2269 */     if (this.allocatedFogs) {
/* 2270 */       if (paramSetLiveState.fogs != null)
/*      */       {
/* 2272 */         if (this.inSharedGroup) {
/* 2273 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 2274 */             int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */             
/* 2276 */             ArrayList arrayList1 = (ArrayList)this.fogs.get(i);
/* 2277 */             ArrayList arrayList2 = (ArrayList)paramSetLiveState.fogs.get(b);
/* 2278 */             if (arrayList2 != null) {
/* 2279 */               int j = arrayList2.size();
/* 2280 */               for (byte b1 = 0; b1 < j; b1++) {
/* 2281 */                 arrayList1.add(arrayList2.get(b1));
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 2288 */           ArrayList arrayList1 = (ArrayList)this.fogs.get(0);
/* 2289 */           ArrayList arrayList2 = (ArrayList)paramSetLiveState.fogs.get(0);
/* 2290 */           int i = arrayList2.size();
/* 2291 */           for (byte b = 0; b < i; b++) {
/* 2292 */             arrayList1.add(arrayList2.get(b));
/*      */           }
/*      */         } 
/*      */       }
/* 2296 */       paramSetLiveState.fogs = this.fogs;
/*      */     } else {
/*      */       
/* 2299 */       this.fogs = paramSetLiveState.fogs;
/*      */     } 
/*      */     
/* 2302 */     if (this.allocatedMclips) {
/* 2303 */       if (paramSetLiveState.modelClips != null)
/*      */       {
/* 2305 */         if (this.inSharedGroup) {
/* 2306 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 2307 */             int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */             
/* 2309 */             ArrayList arrayList1 = (ArrayList)this.modelClips.get(i);
/* 2310 */             ArrayList arrayList2 = (ArrayList)paramSetLiveState.modelClips.get(b);
/* 2311 */             if (arrayList2 != null) {
/* 2312 */               int j = arrayList2.size();
/* 2313 */               for (byte b1 = 0; b1 < j; b1++) {
/* 2314 */                 arrayList1.add(arrayList2.get(b1));
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 2321 */           ArrayList arrayList1 = (ArrayList)this.modelClips.get(0);
/* 2322 */           ArrayList arrayList2 = (ArrayList)paramSetLiveState.modelClips.get(0);
/* 2323 */           int i = arrayList2.size();
/* 2324 */           for (byte b = 0; b < i; b++) {
/* 2325 */             arrayList1.add(arrayList2.get(b));
/*      */           }
/*      */         } 
/*      */       }
/* 2329 */       paramSetLiveState.modelClips = this.modelClips;
/*      */     } else {
/*      */       
/* 2332 */       this.modelClips = paramSetLiveState.modelClips;
/*      */     } 
/*      */     
/* 2335 */     if (this.allocatedAltApps) {
/* 2336 */       if (paramSetLiveState.altAppearances != null)
/*      */       {
/* 2338 */         if (this.inSharedGroup) {
/* 2339 */           for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 2340 */             int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */             
/* 2342 */             ArrayList arrayList1 = (ArrayList)this.altAppearances.get(i);
/* 2343 */             ArrayList arrayList2 = (ArrayList)paramSetLiveState.altAppearances.get(b);
/* 2344 */             if (arrayList2 != null) {
/* 2345 */               int j = arrayList2.size();
/* 2346 */               for (byte b1 = 0; b1 < j; b1++) {
/* 2347 */                 arrayList1.add(arrayList2.get(b1));
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 2354 */           ArrayList arrayList1 = (ArrayList)this.altAppearances.get(0);
/* 2355 */           ArrayList arrayList2 = (ArrayList)paramSetLiveState.altAppearances.get(0);
/* 2356 */           int i = arrayList2.size();
/* 2357 */           for (byte b = 0; b < i; b++) {
/* 2358 */             arrayList1.add(arrayList2.get(b));
/*      */           }
/*      */         } 
/*      */       }
/* 2362 */       paramSetLiveState.altAppearances = this.altAppearances;
/*      */     } else {
/*      */       
/* 2365 */       this.altAppearances = paramSetLiveState.altAppearances;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void processCollisionTarget(SetLiveState paramSetLiveState) {
/* 2372 */     if (this.mirrorGroup == null) {
/* 2373 */       this.mirrorGroup = new ArrayList();
/*      */     }
/* 2375 */     Bounds bounds = (this.collisionBound != null) ? this.collisionBound : getEffectiveBounds();
/*      */     
/* 2377 */     if (this.inSharedGroup) {
/* 2378 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/*      */         
/* 2380 */         GroupRetained groupRetained = new GroupRetained();
/* 2381 */         groupRetained.key = paramSetLiveState.keys[b];
/* 2382 */         groupRetained.localToVworld = new Transform3D[1][];
/* 2383 */         groupRetained.localToVworldIndex = new int[1][];
/*      */         
/* 2385 */         int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */         
/* 2387 */         if (i < 0) {
/* 2388 */           System.err.println("GroupRetained : Can't find hashKey");
/*      */         }
/*      */         
/* 2391 */         groupRetained.localToVworld[0] = this.localToVworld[i];
/* 2392 */         groupRetained.localToVworldIndex[0] = this.localToVworldIndex[i];
/* 2393 */         groupRetained.collisionVwcBounds = new BoundingBox();
/* 2394 */         groupRetained.collisionVwcBounds.transform(bounds, groupRetained.getCurrentLocalToVworld(0));
/* 2395 */         groupRetained.sourceNode = this;
/* 2396 */         groupRetained.locale = this.locale;
/* 2397 */         this.mirrorGroup.add(groupRetained);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2403 */         if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null)
/*      */         {
/* 2405 */           paramSetLiveState.transformTargets[b].addNode(groupRetained, 6);
/*      */         }
/* 2407 */         paramSetLiveState.nodeList.add(groupRetained);
/*      */       } 
/*      */     } else {
/* 2410 */       GroupRetained groupRetained = new GroupRetained();
/* 2411 */       groupRetained.localToVworld = new Transform3D[1][];
/* 2412 */       groupRetained.localToVworldIndex = new int[1][];
/* 2413 */       groupRetained.localToVworld[0] = this.localToVworld[0];
/* 2414 */       groupRetained.localToVworldIndex[0] = this.localToVworldIndex[0];
/* 2415 */       groupRetained.collisionVwcBounds = new BoundingBox();
/* 2416 */       groupRetained.collisionVwcBounds.transform(bounds, groupRetained.getCurrentLocalToVworld(0));
/* 2417 */       groupRetained.sourceNode = this;
/* 2418 */       groupRetained.locale = this.locale;
/* 2419 */       this.mirrorGroup.add(groupRetained);
/* 2420 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null)
/*      */       {
/* 2422 */         paramSetLiveState.transformTargets[0].addNode(groupRetained, 6);
/*      */       }
/* 2424 */       paramSetLiveState.nodeList.add(groupRetained);
/*      */     } 
/*      */   }
/*      */   
/*      */   void computeCombineBounds(Bounds paramBounds) {
/* 2429 */     if (!VirtualUniverse.mc.cacheAutoComputedBounds) {
/* 2430 */       if (this.boundsAutoCompute) {
/* 2431 */         for (int i = this.children.size() - 1; i >= 0; i--) {
/* 2432 */           NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/* 2433 */           if (nodeRetained != null) {
/* 2434 */             nodeRetained.computeCombineBounds(paramBounds);
/*      */           }
/*      */         } 
/*      */       } else {
/* 2438 */         synchronized (this.localBounds) {
/* 2439 */           paramBounds.combine(this.localBounds);
/*      */         } 
/*      */       } 
/*      */     } else {
/* 2443 */       if (this.cachedBounds != null && this.boundsAutoCompute) {
/* 2444 */         paramBounds.combine(this.cachedBounds);
/*      */         
/*      */         return;
/*      */       } 
/* 2448 */       if (this.boundsAutoCompute) {
/* 2449 */         this.cachedBounds = new BoundingSphere();
/* 2450 */         ((BoundingSphere)this.cachedBounds).setRadius(-1.0D);
/* 2451 */         for (int i = this.children.size() - 1; i >= 0; i--) {
/* 2452 */           NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/* 2453 */           if (nodeRetained != null)
/* 2454 */             nodeRetained.computeCombineBounds(this.cachedBounds); 
/*      */         } 
/* 2456 */         paramBounds.combine(this.cachedBounds);
/*      */       } else {
/*      */         
/* 2459 */         synchronized (this.localBounds) {
/* 2460 */           paramBounds.combine(this.localBounds);
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
/*      */   Bounds getBounds() {
/* 2474 */     if (this.boundsAutoCompute) {
/* 2475 */       if (this.cachedBounds != null) {
/* 2476 */         return (Bounds)this.cachedBounds.clone();
/*      */       }
/*      */       
/* 2479 */       BoundingSphere boundingSphere = new BoundingSphere();
/* 2480 */       boundingSphere.setRadius(-1.0D);
/*      */       
/* 2482 */       for (int i = this.children.size() - 1; i >= 0; i--) {
/* 2483 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/* 2484 */         if (nodeRetained != null) {
/* 2485 */           nodeRetained.computeCombineBounds(boundingSphere);
/*      */         }
/*      */       } 
/* 2488 */       return boundingSphere;
/*      */     } 
/* 2490 */     return super.getBounds();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds getEffectiveBounds() {
/* 2498 */     if (this.boundsAutoCompute) {
/* 2499 */       return getBounds();
/*      */     }
/* 2501 */     return super.getEffectiveBounds();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isStaticChildren() {
/* 2507 */     if (this.source.getCapability(12) || this.source.getCapability(13))
/*      */     {
/* 2509 */       return false;
/*      */     }
/*      */     
/* 2512 */     for (int i = this.children.size() - 1; i >= 0; i--) {
/* 2513 */       SceneGraphObjectRetained sceneGraphObjectRetained = (SceneGraphObjectRetained)this.children.get(i);
/*      */       
/* 2515 */       if (sceneGraphObjectRetained != null && sceneGraphObjectRetained.source.getCapability(46)) {
/* 2516 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 2520 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 2525 */   boolean isStatic() { return (super.isStatic() && isStaticChildren()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setCompiled() {
/* 2532 */     super.setCompiled();
/* 2533 */     for (int i = this.children.size() - 1; i >= 0; i--) {
/* 2534 */       SceneGraphObjectRetained sceneGraphObjectRetained = (SceneGraphObjectRetained)this.children.get(i);
/*      */       
/* 2536 */       if (sceneGraphObjectRetained != null) {
/* 2537 */         sceneGraphObjectRetained.setCompiled();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void traverse(boolean paramBoolean, int paramInt) {
/* 2544 */     if (!paramBoolean) {
/* 2545 */       super.traverse(true, paramInt);
/*      */       
/* 2547 */       if (this.source.getCapability(12)) {
/* 2548 */         System.err.print(" (r)");
/* 2549 */       } else if (isStatic()) {
/* 2550 */         System.err.print(" (s)");
/* 2551 */       } else if (this.source.getCapability(13)) {
/* 2552 */         System.err.print(" (w)");
/*      */       } 
/*      */     } 
/*      */     
/* 2556 */     paramInt++;
/* 2557 */     for (byte b = 0; b < this.children.size(); b++) {
/* 2558 */       SceneGraphObjectRetained sceneGraphObjectRetained = (SceneGraphObjectRetained)this.children.get(b);
/* 2559 */       if (sceneGraphObjectRetained != null) {
/* 2560 */         sceneGraphObjectRetained.traverse(false, paramInt);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void compile(CompileState paramCompileState) {
/* 2569 */     super.compile(paramCompileState);
/*      */     
/* 2571 */     this.mergeFlag = 1;
/*      */     
/* 2573 */     if (!isStatic()) {
/* 2574 */       paramCompileState.keepTG = true;
/* 2575 */       this.mergeFlag = 0;
/*      */     } 
/*      */     
/* 2578 */     if (this.isRoot || usedInScoping() || this.parent instanceof SwitchRetained)
/*      */     {
/* 2580 */       this.mergeFlag = 0;
/*      */     }
/*      */     
/* 2583 */     this.compiledChildrenList = new ArrayList(5);
/*      */     
/* 2585 */     for (byte b = 0; b < this.children.size(); b++) {
/* 2586 */       SceneGraphObjectRetained sceneGraphObjectRetained = (SceneGraphObjectRetained)this.children.get(b);
/* 2587 */       if (sceneGraphObjectRetained != null) {
/* 2588 */         sceneGraphObjectRetained.compile(paramCompileState);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void merge(CompileState paramCompileState) {
/* 2599 */     GroupRetained groupRetained = null;
/*      */ 
/*      */     
/* 2602 */     if (this.mergeFlag != 2) {
/* 2603 */       if (this.mergeFlag == 0) {
/*      */ 
/*      */         
/* 2606 */         super.merge(paramCompileState);
/*      */         
/* 2608 */         groupRetained = paramCompileState.parentGroup;
/* 2609 */         paramCompileState.parentGroup = this;
/*      */       } 
/*      */       
/* 2612 */       for (byte b = 0; b < this.children.size(); b++) {
/* 2613 */         SceneGraphObjectRetained sceneGraphObjectRetained = (SceneGraphObjectRetained)this.children.get(b);
/* 2614 */         if (sceneGraphObjectRetained != null) {
/* 2615 */           sceneGraphObjectRetained.merge(paramCompileState);
/*      */         }
/*      */       } 
/*      */       
/* 2619 */       if (paramCompileState.parentGroup == this) {
/* 2620 */         this.children = this.compiledChildrenList;
/* 2621 */         paramCompileState.doShapeMerge();
/* 2622 */         this.compiledChildrenList = null;
/* 2623 */         paramCompileState.parentGroup = groupRetained;
/*      */       } else {
/*      */         
/* 2626 */         this.children.clear();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2633 */       this.mergeFlag = 2;
/*      */     
/*      */     }
/* 2636 */     else if (paramCompileState.parentGroup != null) {
/* 2637 */       paramCompileState.parentGroup.compiledChildrenList.add(this);
/* 2638 */       this.parent = paramCompileState.parentGroup;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearLive(SetLiveState paramSetLiveState) {
/* 2649 */     boolean bool1 = false;
/* 2650 */     boolean bool2 = false;
/* 2651 */     boolean bool3 = false;
/* 2652 */     boolean bool4 = false;
/* 2653 */     boolean bool5 = false;
/* 2654 */     boolean bool6 = false;
/* 2655 */     boolean bool7 = false;
/* 2656 */     boolean bool8 = false;
/*      */ 
/*      */     
/* 2659 */     this.isInClearLive = true;
/*      */ 
/*      */     
/* 2662 */     HashKey[] arrayOfHashKey = this.localToVworldKeys;
/*      */     
/* 2664 */     super.clearLive(paramSetLiveState);
/*      */ 
/*      */     
/* 2667 */     int j = this.children.size();
/*      */     
/* 2669 */     if (!(this instanceof ViewSpecificGroupRetained)) {
/* 2670 */       this.viewLists = paramSetLiveState.viewLists;
/*      */     }
/*      */     
/* 2673 */     ArrayList arrayList1 = paramSetLiveState.lights;
/* 2674 */     if (this.allocatedLights) {
/* 2675 */       paramSetLiveState.lights = this.lights;
/*      */     }
/*      */     
/* 2678 */     ArrayList arrayList2 = paramSetLiveState.fogs;
/* 2679 */     if (this.allocatedFogs) {
/* 2680 */       paramSetLiveState.fogs = this.fogs;
/*      */     }
/*      */     
/* 2683 */     ArrayList arrayList3 = paramSetLiveState.modelClips;
/* 2684 */     if (this.allocatedMclips) {
/* 2685 */       paramSetLiveState.modelClips = this.modelClips;
/*      */     }
/*      */ 
/*      */     
/* 2689 */     ArrayList arrayList4 = paramSetLiveState.altAppearances;
/* 2690 */     if (this.allocatedAltApps) {
/* 2691 */       paramSetLiveState.altAppearances = this.altAppearances;
/*      */     }
/*      */     
/*      */     int i;
/* 2695 */     for (i = j - 1; i >= 0; i--) {
/* 2696 */       NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/* 2697 */       if (this instanceof OrderedGroupRetained) {
/* 2698 */         OrderedGroupRetained orderedGroupRetained = (OrderedGroupRetained)this;
/*      */ 
/*      */ 
/*      */         
/* 2702 */         if (this.refCount + 1 == paramSetLiveState.refCount) {
/*      */ 
/*      */           
/* 2705 */           paramSetLiveState.ogList.add(this);
/* 2706 */           paramSetLiveState.ogChildIdList.add(new Integer(i));
/*      */         } 
/* 2708 */         paramSetLiveState.orderedPaths = (ArrayList)orderedGroupRetained.childrenOrderedPaths.get(i);
/*      */       } 
/*      */       
/* 2711 */       if (nodeRetained != null) {
/* 2712 */         nodeRetained.clearLive(paramSetLiveState);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2718 */     if (arrayList1 != null && 
/* 2719 */       this.allocatedLights) {
/* 2720 */       if (this.inSharedGroup) {
/*      */         
/* 2722 */         for (i = 0; i < paramSetLiveState.keys.length; i++) {
/* 2723 */           int k = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */           
/* 2725 */           ArrayList arrayList5 = (ArrayList)arrayList1.get(k);
/* 2726 */           ArrayList arrayList6 = (ArrayList)this.lights.get(k);
/* 2727 */           if (arrayList5 != null) {
/* 2728 */             int m = arrayList5.size();
/* 2729 */             for (byte b = 0; b < m; b++) {
/* 2730 */               arrayList6.remove(arrayList5.get(b));
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 2737 */         ArrayList arrayList5 = (ArrayList)arrayList1.get(0);
/* 2738 */         ArrayList arrayList6 = (ArrayList)this.lights.get(0);
/* 2739 */         int k = arrayList5.size();
/* 2740 */         for (byte b = 0; b < k; b++) {
/* 2741 */           arrayList6.remove(arrayList5.get(b));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2747 */     if (arrayList2 != null && 
/* 2748 */       this.allocatedFogs) {
/* 2749 */       if (this.inSharedGroup) {
/* 2750 */         for (i = 0; i < paramSetLiveState.keys.length; i++) {
/* 2751 */           int k = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */           
/* 2753 */           ArrayList arrayList5 = (ArrayList)arrayList2.get(k);
/* 2754 */           ArrayList arrayList6 = (ArrayList)this.fogs.get(k);
/* 2755 */           if (arrayList5 != null) {
/* 2756 */             int m = arrayList5.size();
/* 2757 */             for (byte b = 0; b < m; b++) {
/* 2758 */               arrayList6.remove(arrayList5.get(b));
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 2765 */         ArrayList arrayList = (ArrayList)arrayList2.get(0);
/* 2766 */         int k = arrayList.size();
/* 2767 */         for (byte b = 0; b < k; b++) {
/* 2768 */           this.fogs.remove(arrayList.get(b));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2774 */     if (arrayList3 != null && 
/* 2775 */       this.allocatedMclips) {
/* 2776 */       if (this.inSharedGroup) {
/* 2777 */         for (i = 0; i < paramSetLiveState.keys.length; i++) {
/* 2778 */           int k = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */           
/* 2780 */           ArrayList arrayList5 = (ArrayList)arrayList3.get(k);
/* 2781 */           ArrayList arrayList6 = (ArrayList)this.modelClips.get(k);
/* 2782 */           if (arrayList5 != null) {
/* 2783 */             int m = arrayList5.size();
/* 2784 */             for (byte b = 0; b < m; b++) {
/* 2785 */               arrayList6.remove(arrayList5.get(b));
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 2792 */         ArrayList arrayList = (ArrayList)arrayList3.get(0);
/* 2793 */         int k = arrayList.size();
/* 2794 */         for (byte b = 0; b < k; b++) {
/* 2795 */           this.modelClips.remove(arrayList.get(b));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2801 */     if (arrayList4 != null && 
/* 2802 */       this.allocatedAltApps) {
/* 2803 */       if (this.inSharedGroup) {
/* 2804 */         for (i = 0; i < paramSetLiveState.keys.length; i++) {
/* 2805 */           int k = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */           
/* 2807 */           ArrayList arrayList5 = (ArrayList)arrayList4.get(k);
/* 2808 */           ArrayList arrayList6 = (ArrayList)this.altAppearances.get(k);
/* 2809 */           if (arrayList5 != null) {
/* 2810 */             int m = arrayList5.size();
/* 2811 */             for (byte b = 0; b < m; b++) {
/* 2812 */               arrayList6.remove(arrayList5.get(b));
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 2819 */         ArrayList arrayList = (ArrayList)arrayList4.get(0);
/* 2820 */         int k = arrayList.size();
/* 2821 */         for (byte b = 0; b < k; b++) {
/* 2822 */           this.altAppearances.remove(arrayList.get(b));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2828 */     if (this.collisionTarget)
/*      */     {
/* 2830 */       if (this.inSharedGroup) {
/* 2831 */         for (i = paramSetLiveState.keys.length - 1; i >= 0; i--) {
/* 2832 */           HashKey hashKey = paramSetLiveState.keys[i];
/* 2833 */           for (int k = this.mirrorGroup.size() - 1; k >= 0; k--) {
/* 2834 */             GroupRetained groupRetained = (GroupRetained)this.mirrorGroup.get(k);
/* 2835 */             if (groupRetained.key.equals(hashKey)) {
/* 2836 */               paramSetLiveState.nodeList.add(this.mirrorGroup.remove(k));
/* 2837 */               if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[k] != null)
/*      */               {
/* 2839 */                 paramSetLiveState.transformTargets[k].addNode(groupRetained, 6);
/*      */               }
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/* 2847 */         GroupRetained groupRetained = (GroupRetained)this.mirrorGroup.get(0);
/* 2848 */         if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null)
/*      */         {
/* 2850 */           paramSetLiveState.transformTargets[0].addNode(groupRetained, 6);
/*      */         }
/* 2852 */         paramSetLiveState.nodeList.add(this.mirrorGroup.remove(0));
/*      */       } 
/*      */     }
/* 2855 */     paramSetLiveState.lights = arrayList1;
/* 2856 */     paramSetLiveState.modelClips = arrayList3;
/* 2857 */     paramSetLiveState.fogs = arrayList2;
/* 2858 */     paramSetLiveState.altAppearances = arrayList4;
/* 2859 */     this.isInClearLive = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 2864 */   public BoundingBox computeBoundingHull() { return this.collisionVwcBounds; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2869 */   public boolean isEnable() { return isNodeSwitchOn(this.sourceNode, this.key); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2875 */   public boolean isEnable(int paramInt) { return isNodeSwitchOn(this.sourceNode, this.key); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2880 */   public Locale getLocale2() { return this.locale; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean isNodeSwitchOn(NodeRetained paramNodeRetained, HashKey paramHashKey) {
/* 2888 */     NodeRetained nodeRetained = null;
/* 2889 */     if (paramHashKey != null) {
/* 2890 */       paramHashKey = new HashKey(paramHashKey);
/*      */     }
/*      */     
/* 2893 */     synchronized (paramNodeRetained.universe.sceneGraphLock) {
/*      */       do {
/* 2895 */         if (paramNodeRetained instanceof SwitchRetained && nodeRetained != null && !validSwitchChild((SwitchRetained)paramNodeRetained, nodeRetained))
/*      */         {
/*      */           
/* 2898 */           return false;
/*      */         }
/* 2900 */         nodeRetained = paramNodeRetained;
/* 2901 */         if (paramNodeRetained instanceof SharedGroupRetained) {
/*      */           
/* 2903 */           String str = paramHashKey.getLastNodeId();
/* 2904 */           Vector vector = ((SharedGroupRetained)paramNodeRetained).parents;
/*      */           
/* 2906 */           for (int i = vector.size() - 1; i >= 0; i--) {
/* 2907 */             NodeRetained nodeRetained1 = (NodeRetained)vector.get(i);
/* 2908 */             if (nodeRetained1.nodeId.equals(str)) {
/* 2909 */               paramNodeRetained = nodeRetained1;
/*      */               break;
/*      */             } 
/*      */           } 
/* 2913 */           if (paramNodeRetained == nodeRetained)
/*      */           {
/*      */ 
/*      */ 
/*      */             
/* 2918 */             return false;
/*      */           }
/*      */         } else {
/* 2921 */           paramNodeRetained = paramNodeRetained.parent;
/*      */         } 
/* 2923 */       } while (paramNodeRetained != null);
/*      */     } 
/*      */     
/* 2926 */     return true;
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
/*      */   static boolean validSwitchChild(SwitchRetained paramSwitchRetained, NodeRetained paramNodeRetained) {
/* 2938 */     int i = paramSwitchRetained.whichChild;
/*      */     
/* 2940 */     if (i == -1) {
/* 2941 */       return false;
/*      */     }
/*      */     
/* 2944 */     if (i == -2) {
/* 2945 */       return true;
/*      */     }
/*      */     
/* 2948 */     ArrayList arrayList = paramSwitchRetained.children;
/*      */     
/* 2950 */     if (i >= 0) {
/* 2951 */       return (arrayList.get(i) == paramNodeRetained);
/*      */     }
/*      */ 
/*      */     
/* 2955 */     for (int j = arrayList.size() - 1; j >= 0; j--) {
/* 2956 */       if (paramSwitchRetained.childMask.get(j) && arrayList.get(j) == paramNodeRetained)
/*      */       {
/* 2958 */         return true;
/*      */       }
/*      */     } 
/* 2961 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createMirrorGroup() {
/* 2972 */     this.mirrorGroup = new ArrayList();
/*      */     
/* 2974 */     Bounds bounds = (this.collisionBound != null) ? this.collisionBound : getEffectiveBounds();
/*      */ 
/*      */     
/* 2977 */     if (this.inSharedGroup) {
/* 2978 */       for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 2979 */         GroupRetained groupRetained = new GroupRetained();
/* 2980 */         groupRetained.key = this.localToVworldKeys[b];
/* 2981 */         groupRetained.localToVworld = new Transform3D[1][];
/* 2982 */         groupRetained.localToVworldIndex = new int[1][];
/* 2983 */         groupRetained.localToVworld[0] = this.localToVworld[b];
/* 2984 */         groupRetained.localToVworldIndex[0] = this.localToVworldIndex[b];
/* 2985 */         groupRetained.collisionVwcBounds = new BoundingBox();
/* 2986 */         groupRetained.collisionVwcBounds.transform(bounds, groupRetained.getCurrentLocalToVworld());
/* 2987 */         groupRetained.sourceNode = this;
/* 2988 */         groupRetained.locale = this.locale;
/* 2989 */         this.mirrorGroup.add(groupRetained);
/*      */       } 
/*      */     } else {
/* 2992 */       GroupRetained groupRetained = new GroupRetained();
/* 2993 */       groupRetained.localToVworld = new Transform3D[1][];
/* 2994 */       groupRetained.localToVworldIndex = new int[1][];
/* 2995 */       groupRetained.localToVworld[0] = this.localToVworld[0];
/* 2996 */       groupRetained.localToVworldIndex[0] = this.localToVworldIndex[0];
/* 2997 */       groupRetained.collisionVwcBounds = new BoundingBox();
/* 2998 */       groupRetained.collisionVwcBounds.transform(bounds, groupRetained.getCurrentLocalToVworld());
/* 2999 */       groupRetained.sourceNode = this;
/* 3000 */       groupRetained.locale = this.locale;
/* 3001 */       this.mirrorGroup.add(groupRetained);
/*      */     } 
/*      */   }
/*      */   
/*      */   void setBoundsAutoCompute(boolean paramBoolean) {
/* 3006 */     if (paramBoolean != this.boundsAutoCompute) {
/* 3007 */       super.setBoundsAutoCompute(paramBoolean);
/* 3008 */       if (!paramBoolean) {
/* 3009 */         this.localBounds = getEffectiveBounds();
/*      */       }
/* 3011 */       if (this.source.isLive() && this.collisionBound == null && paramBoolean && this.mirrorGroup != null) {
/*      */ 
/*      */         
/* 3014 */         J3dMessage j3dMessage = new J3dMessage();
/* 3015 */         j3dMessage.type = 34;
/* 3016 */         j3dMessage.threads = 8256;
/*      */         
/* 3018 */         j3dMessage.universe = this.universe;
/* 3019 */         j3dMessage.args[0] = this;
/* 3020 */         VirtualUniverse.mc.processMessage(j3dMessage);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void setBounds(Bounds paramBounds) {
/* 3026 */     super.setBounds(paramBounds);
/* 3027 */     if (this.source.isLive() && !this.boundsAutoCompute && this.collisionBound == null && this.mirrorGroup != null) {
/*      */ 
/*      */       
/* 3030 */       J3dMessage j3dMessage = new J3dMessage();
/* 3031 */       j3dMessage.type = 34;
/* 3032 */       j3dMessage.threads = 8256;
/*      */       
/* 3034 */       j3dMessage.universe = this.universe;
/* 3035 */       j3dMessage.args[0] = this;
/* 3036 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int[] processViewSpecificInfo(int paramInt, HashKey paramHashKey, View paramView, ArrayList paramArrayList1, int[] paramArrayOfInt, ArrayList paramArrayList2) {
/* 3043 */     int i = this.children.size();
/* 3044 */     if (this.source.isLive()) {
/* 3045 */       for (byte b = 0; b < i; b++) {
/* 3046 */         NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 3047 */         if (nodeRetained instanceof LeafRetained) {
/* 3048 */           if (nodeRetained instanceof LinkRetained) {
/* 3049 */             int j = paramHashKey.count;
/* 3050 */             LinkRetained linkRetained = (LinkRetained)nodeRetained;
/* 3051 */             if (paramHashKey.count == 0) {
/* 3052 */               paramHashKey.append(this.locale.nodeId);
/*      */             }
/* 3054 */             paramArrayOfInt = linkRetained.sharedGroup.processViewSpecificInfo(paramInt, paramHashKey.append("+").append(linkRetained.nodeId), paramView, paramArrayList1, paramArrayOfInt, paramArrayList2);
/*      */ 
/*      */             
/* 3057 */             paramHashKey.count = j;
/*      */           } else {
/*      */             
/* 3060 */             ((LeafRetained)nodeRetained).getMirrorObjects(paramArrayList2, paramHashKey);
/*      */           } 
/*      */         } else {
/* 3063 */           paramArrayOfInt = nodeRetained.processViewSpecificInfo(paramInt, paramHashKey, paramView, paramArrayList1, paramArrayOfInt, paramArrayList2);
/*      */         } 
/*      */       } 
/*      */     }
/* 3067 */     return paramArrayOfInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void findSwitchInfo(SetLiveState paramSetLiveState, NodeRetained paramNodeRetained1, NodeRetained paramNodeRetained2, NodeRetained paramNodeRetained3) {
/* 3076 */     this.parentSwitchLinkChildIndex = -1;
/*      */ 
/*      */     
/* 3079 */     if (!this.inSharedGroup) {
/* 3080 */       NodeRetained nodeRetained1 = (paramNodeRetained3 == null) ? paramNodeRetained2 : paramNodeRetained3;
/* 3081 */       NodeRetained nodeRetained2 = paramNodeRetained1;
/* 3082 */       while (nodeRetained2 != null) {
/* 3083 */         if (nodeRetained2 instanceof SwitchRetained) {
/* 3084 */           paramSetLiveState.switchLevels[0] = paramSetLiveState.switchLevels[0] + 1;
/* 3085 */           if (paramSetLiveState.closestSwitchParents[false] == null) {
/* 3086 */             paramSetLiveState.closestSwitchParents[0] = (SwitchRetained)nodeRetained2;
/* 3087 */             paramSetLiveState.closestSwitchIndices[0] = ((SwitchRetained)nodeRetained2).switchIndexCount++;
/*      */           } 
/*      */           
/* 3090 */           if (this.parentSwitchLinkChildIndex == -1) {
/* 3091 */             this.parentSwitchLinkChildIndex = ((GroupRetained)nodeRetained2).children.indexOf(nodeRetained1);
/*      */           }
/*      */         }
/* 3094 */         else if (nodeRetained2 instanceof SharedGroupRetained && 
/* 3095 */           this.parentSwitchLinkChildIndex == -1) {
/* 3096 */           this.parentSwitchLinkChildIndex = ((GroupRetained)nodeRetained2).children.indexOf(nodeRetained1);
/*      */         } 
/*      */ 
/*      */         
/* 3100 */         nodeRetained1 = nodeRetained2;
/* 3101 */         nodeRetained2 = nodeRetained1.parent;
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 3107 */       paramSetLiveState.switchLevels = new int[this.localToVworldKeys.length];
/* 3108 */       paramSetLiveState.closestSwitchParents = new SwitchRetained[this.localToVworldKeys.length];
/*      */       
/* 3110 */       paramSetLiveState.closestSwitchIndices = new int[this.localToVworldKeys.length]; byte b;
/* 3111 */       for (b = 0; b < this.localToVworldKeys.length; b++) {
/* 3112 */         paramSetLiveState.switchLevels[b] = -1;
/* 3113 */         paramSetLiveState.closestSwitchParents[b] = null;
/* 3114 */         paramSetLiveState.closestSwitchIndices[b] = -1;
/*      */       } 
/*      */       
/* 3117 */       for (b = 0; b < this.localToVworldKeys.length; b++) {
/* 3118 */         NodeRetained nodeRetained1 = (paramNodeRetained3 == null) ? paramNodeRetained2 : paramNodeRetained3;
/* 3119 */         NodeRetained nodeRetained2 = paramNodeRetained1;
/* 3120 */         HashKey hashKey = new HashKey(this.localToVworldKeys[b]);
/*      */         
/* 3122 */         while (nodeRetained2 != null) {
/*      */           
/* 3124 */           if (nodeRetained2 instanceof SwitchRetained) {
/* 3125 */             paramSetLiveState.switchLevels[b] = paramSetLiveState.switchLevels[b] + 1;
/* 3126 */             if (paramSetLiveState.closestSwitchParents[b] == null) {
/* 3127 */               paramSetLiveState.closestSwitchParents[b] = (SwitchRetained)nodeRetained2;
/* 3128 */               paramSetLiveState.closestSwitchIndices[b] = ((SwitchRetained)nodeRetained2).switchIndexCount++;
/*      */             } 
/*      */ 
/*      */             
/* 3132 */             if (this.parentSwitchLinkChildIndex == -1) {
/* 3133 */               this.parentSwitchLinkChildIndex = ((GroupRetained)nodeRetained2).children.indexOf(nodeRetained1);
/*      */             }
/*      */           }
/* 3136 */           else if (nodeRetained2 instanceof SharedGroupRetained) {
/* 3137 */             String str = hashKey.getLastNodeId();
/* 3138 */             Vector vector = ((SharedGroupRetained)nodeRetained2).parents;
/*      */ 
/*      */             
/* 3141 */             if (this.parentSwitchLinkChildIndex == -1) {
/* 3142 */               this.parentSwitchLinkChildIndex = ((GroupRetained)nodeRetained2).children.indexOf(nodeRetained1);
/*      */             }
/*      */ 
/*      */             
/* 3146 */             for (byte b1 = 0; b1 < vector.size(); b1++) {
/* 3147 */               NodeRetained nodeRetained = (NodeRetained)vector.get(b1);
/* 3148 */               if (nodeRetained.nodeId.equals(str)) {
/* 3149 */                 nodeRetained2 = nodeRetained;
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } 
/* 3154 */           nodeRetained1 = nodeRetained2;
/* 3155 */           nodeRetained2 = nodeRetained1.parent;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void gatherBlUsers(ArrayList paramArrayList, Object[] paramArrayOfObject) {
/* 3164 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 3165 */       ArrayList arrayList = ((BoundingLeafRetained)paramArrayOfObject[b]).users;
/* 3166 */       synchronized (arrayList) {
/* 3167 */         paramArrayList.addAll(arrayList);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void searchGeometryAtoms(UnorderList paramUnorderList) {
/* 3174 */     for (int i = this.children.size() - 1; i >= 0; i--) {
/* 3175 */       NodeRetained nodeRetained = (NodeRetained)this.children.get(i);
/* 3176 */       nodeRetained.searchGeometryAtoms(paramUnorderList);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\GroupRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */