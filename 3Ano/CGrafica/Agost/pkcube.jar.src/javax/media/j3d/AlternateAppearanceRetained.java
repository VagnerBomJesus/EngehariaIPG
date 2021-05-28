/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class AlternateAppearanceRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int APPEARANCE_CHANGED = 1;
/*     */   static final int SCOPE_CHANGED = 2;
/*     */   static final int BOUNDS_CHANGED = 4;
/*     */   static final int BOUNDINGLEAF_CHANGED = 8;
/*     */   static final int INIT_MIRROR = 16;
/*     */   static final int CLEAR_MIRROR = 32;
/*     */   Bounds regionOfInfluence;
/*     */   BoundingLeafRetained boundingLeaf;
/*     */   Vector scopes;
/*     */   boolean inImmCtx;
/*     */   static final int targetThreads = 4224;
/*     */   boolean isScoped;
/*     */   HashKey tempKey;
/*     */   Bounds region;
/*     */   AlternateAppearanceRetained mirrorAltApp;
/*     */   AppearanceRetained appearance;
/*     */   AlternateAppearanceRetained sgAltApp;
/*     */   boolean isViewScoped;
/*     */   
/*     */   AlternateAppearanceRetained() {
/*  37 */     this.regionOfInfluence = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.scopes = new Vector();
/*     */ 
/*     */     
/*  50 */     this.inImmCtx = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     this.isScoped = false;
/*     */ 
/*     */ 
/*     */     
/*  61 */     this.tempKey = new HashKey(250);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.region = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     this.mirrorAltApp = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     this.sgAltApp = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     this.isViewScoped = false;
/*     */ 
/*     */     
/*  89 */     this.nodeType = 27;
/*  90 */     this.localBounds = new BoundingBox();
/*  91 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  92 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initAppearance(Appearance paramAppearance) {
/*  99 */     if (paramAppearance != null) {
/* 100 */       this.appearance = (AppearanceRetained)paramAppearance.retained;
/*     */     } else {
/* 102 */       this.appearance = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAppearance(Appearance paramAppearance) {
/* 110 */     if (this.appearance != null)
/* 111 */       synchronized (this.appearance.liveStateLock) {
/* 112 */         this.appearance.clearLive(this.refCount);
/*     */       }  
/* 114 */     initAppearance(paramAppearance);
/* 115 */     if (this.appearance != null) {
/* 116 */       synchronized (this.appearance.liveStateLock) {
/* 117 */         this.appearance.setLive(this.inBackgroundGroup, this.refCount);
/*     */       } 
/*     */     }
/*     */     
/* 121 */     sendMessage(1, (this.appearance != null) ? this.appearance.mirror : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   Appearance getAppearance() { return (this.appearance == null) ? null : (Appearance)this.appearance.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initInfluencingBounds(Bounds paramBounds) {
/* 136 */     if (paramBounds != null) {
/* 137 */       this.regionOfInfluence = (Bounds)paramBounds.clone();
/*     */     } else {
/* 139 */       this.regionOfInfluence = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInfluencingBounds(Bounds paramBounds) {
/* 147 */     initInfluencingBounds(paramBounds);
/* 148 */     sendMessage(4, (paramBounds != null) ? paramBounds.clone() : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   Bounds getInfluencingBounds() { return (this.regionOfInfluence != null) ? (Bounds)this.regionOfInfluence.clone() : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 164 */     if (paramBoundingLeaf != null) {
/* 165 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*     */     } else {
/* 167 */       this.boundingLeaf = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 175 */     if (this.boundingLeaf != null)
/* 176 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this.mirrorAltApp); 
/* 177 */     if (paramBoundingLeaf != null) {
/* 178 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/* 179 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorAltApp);
/*     */     } else {
/* 181 */       this.boundingLeaf = null;
/*     */     } 
/* 183 */     sendMessage(8, (this.boundingLeaf != null) ? this.boundingLeaf.mirrorBoundingLeaf : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   BoundingLeaf getInfluencingBoundingLeaf() { return (this.boundingLeaf != null) ? (BoundingLeaf)this.boundingLeaf.source : null; }
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
/* 204 */   void initScope(Group paramGroup, int paramInt) { this.scopes.setElementAt((GroupRetained)paramGroup.retained, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScope(Group paramGroup, int paramInt) {
/* 215 */     ArrayList arrayList1 = new ArrayList();
/*     */     
/* 217 */     ArrayList arrayList2 = new ArrayList();
/* 218 */     Object[] arrayOfObject = new Object[3];
/*     */     
/* 220 */     GroupRetained groupRetained = (GroupRetained)this.scopes.get(paramInt);
/* 221 */     this.tempKey.reset();
/* 222 */     groupRetained.removeAllNodesForScopedAltApp(this.mirrorAltApp, arrayList1, this.tempKey);
/*     */     
/* 224 */     groupRetained = (GroupRetained)paramGroup.retained;
/* 225 */     initScope(paramGroup, paramInt);
/* 226 */     this.tempKey.reset();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     groupRetained.addAllNodesForScopedAltApp(this.mirrorAltApp, arrayList2, this.tempKey);
/* 232 */     arrayOfObject[0] = arrayList2;
/* 233 */     arrayOfObject[1] = arrayList1;
/* 234 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 235 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */   
/* 239 */   Group getScope(int paramInt) { return (Group)((GroupRetained)this.scopes.elementAt(paramInt)).source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initInsertScope(Node paramNode, int paramInt) {
/* 250 */     GroupRetained groupRetained = (GroupRetained)paramNode.retained;
/* 251 */     this.scopes.insertElementAt((GroupRetained)paramNode.retained, paramInt);
/* 252 */     groupRetained.setAltAppScope();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void insertScope(Node paramNode, int paramInt) {
/* 262 */     Object[] arrayOfObject = new Object[3];
/* 263 */     ArrayList arrayList = new ArrayList();
/* 264 */     GroupRetained groupRetained = (GroupRetained)paramNode.retained;
/*     */     
/* 266 */     initInsertScope(paramNode, paramInt);
/* 267 */     groupRetained = (GroupRetained)paramNode.retained;
/* 268 */     this.tempKey.reset();
/* 269 */     groupRetained.addAllNodesForScopedAltApp(this.mirrorAltApp, arrayList, this.tempKey);
/*     */     
/* 271 */     arrayOfObject[0] = arrayList;
/* 272 */     arrayOfObject[1] = null;
/* 273 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 274 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void initRemoveScope(int paramInt) {
/* 280 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/* 281 */     this.scopes.removeElementAt(paramInt);
/* 282 */     groupRetained.removeAltAppScope();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeScope(int paramInt) {
/* 288 */     Object[] arrayOfObject = new Object[3];
/* 289 */     ArrayList arrayList = new ArrayList();
/* 290 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/*     */     
/* 292 */     this.tempKey.reset();
/* 293 */     groupRetained.removeAllNodesForScopedAltApp(this.mirrorAltApp, arrayList, this.tempKey);
/*     */     
/* 295 */     initRemoveScope(paramInt);
/* 296 */     arrayOfObject[0] = null;
/* 297 */     arrayOfObject[1] = arrayList;
/* 298 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 299 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeScope(Group paramGroup) {
/* 309 */     int i = indexOfScope(paramGroup);
/* 310 */     if (i >= 0)
/* 311 */       removeScope(i); 
/*     */   }
/*     */   
/*     */   void initRemoveScope(Group paramGroup) {
/* 315 */     int i = indexOfScope(paramGroup);
/* 316 */     if (i >= 0) {
/* 317 */       initRemoveScope(i);
/*     */     }
/*     */   }
/*     */   
/*     */   void removeAllScopes() {
/* 322 */     ArrayList arrayList = new ArrayList();
/* 323 */     int i = this.scopes.size();
/* 324 */     for (int j = i - 1; j >= 0; j--) {
/* 325 */       GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(j);
/* 326 */       this.tempKey.reset();
/* 327 */       groupRetained.removeAllNodesForScopedAltApp(this.mirrorAltApp, arrayList, this.tempKey);
/* 328 */       initRemoveScope(j);
/*     */     } 
/* 330 */     Object[] arrayOfObject = new Object[3];
/* 331 */     arrayOfObject[0] = null;
/* 332 */     arrayOfObject[1] = arrayList;
/* 333 */     arrayOfObject[2] = Boolean.FALSE;
/* 334 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */   
/*     */   void initRemoveAllScopes() {
/* 338 */     int i = this.scopes.size();
/* 339 */     for (int j = i - 1; j >= 0; j--) {
/* 340 */       initRemoveScope(j);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Enumeration getAllScopes() {
/* 348 */     Enumeration enumeration = this.scopes.elements();
/* 349 */     Vector vector = new Vector(this.scopes.size());
/* 350 */     while (enumeration.hasMoreElements()) {
/* 351 */       vector.add(((GroupRetained)enumeration.nextElement()).source);
/*     */     }
/* 353 */     return vector.elements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int indexOfScope(Group paramGroup) {
/* 361 */     if (paramGroup != null) {
/* 362 */       return this.scopes.indexOf((GroupRetained)paramGroup.retained);
/*     */     }
/* 364 */     return this.scopes.indexOf(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initAddScope(Group paramGroup) {
/* 373 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/* 374 */     this.scopes.addElement((GroupRetained)paramGroup.retained);
/* 375 */     groupRetained.setAltAppScope();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addScope(Group paramGroup) {
/* 384 */     Object[] arrayOfObject = new Object[3];
/* 385 */     ArrayList arrayList = new ArrayList();
/* 386 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*     */     
/* 388 */     initAddScope(paramGroup);
/* 389 */     this.tempKey.reset();
/* 390 */     groupRetained.addAllNodesForScopedAltApp(this.mirrorAltApp, arrayList, this.tempKey);
/* 391 */     arrayOfObject[0] = arrayList;
/* 392 */     arrayOfObject[1] = null;
/* 393 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 394 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   int numScopes() { return this.scopes.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 419 */   boolean getInImmCtx() { return this.inImmCtx; }
/*     */ 
/*     */ 
/*     */   
/* 423 */   boolean isScoped() { return (this.scopes != null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/* 433 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 434 */     if ((i & true) != 0) {
/* 435 */       this.mirrorAltApp.appearance = (AppearanceRetained)paramArrayOfObject[2];
/*     */     }
/* 437 */     if ((i & 0x4) != 0) {
/* 438 */       this.mirrorAltApp.regionOfInfluence = (Bounds)paramArrayOfObject[2];
/* 439 */       if (this.mirrorAltApp.boundingLeaf == null) {
/* 440 */         if (paramArrayOfObject[2] != null) {
/* 441 */           this.mirrorAltApp.region = this.mirrorAltApp.regionOfInfluence.copy(this.mirrorAltApp.region);
/* 442 */           this.mirrorAltApp.region.transform(this.mirrorAltApp.regionOfInfluence, getCurrentLocalToVworld());
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 447 */           this.mirrorAltApp.region = null;
/*     */         }
/*     */       
/*     */       }
/* 451 */     } else if ((i & 0x8) != 0) {
/* 452 */       this.mirrorAltApp.boundingLeaf = (BoundingLeafRetained)paramArrayOfObject[2];
/* 453 */       if (paramArrayOfObject[2] != null) {
/* 454 */         this.mirrorAltApp.region = this.mirrorAltApp.boundingLeaf.transformedRegion;
/*     */       
/*     */       }
/* 457 */       else if (this.mirrorAltApp.regionOfInfluence != null) {
/* 458 */         this.mirrorAltApp.region = this.mirrorAltApp.regionOfInfluence.copy(this.mirrorAltApp.region);
/* 459 */         this.mirrorAltApp.region.transform(this.mirrorAltApp.regionOfInfluence, getCurrentLocalToVworld());
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 464 */         this.mirrorAltApp.region = null;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 469 */     else if ((i & 0x2) != 0) {
/* 470 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[2];
/* 471 */       ArrayList arrayList1 = (ArrayList)arrayOfObject[0];
/* 472 */       ArrayList arrayList2 = (ArrayList)arrayOfObject[1];
/* 473 */       boolean bool = ((Boolean)arrayOfObject[2]).booleanValue();
/*     */       
/* 475 */       if (arrayList1 != null) {
/* 476 */         this.mirrorAltApp.isScoped = bool;
/* 477 */         for (byte b = 0; b < arrayList1.size(); b++) {
/* 478 */           Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b)).source;
/* 479 */           shape3DRetained.addAltApp(this.mirrorAltApp);
/*     */         } 
/*     */       } 
/*     */       
/* 483 */       if (arrayList2 != null) {
/* 484 */         this.mirrorAltApp.isScoped = bool;
/* 485 */         for (byte b = 0; b < arrayList2.size(); b++) {
/* 486 */           Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList2.get(b)).source;
/* 487 */           shape3DRetained.removeAltApp(this.mirrorAltApp);
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
/*     */   void updateBoundingLeaf() {
/* 502 */     if (this.boundingLeaf != null && this.boundingLeaf.switchState.currentSwitchOn) {
/* 503 */       this.region = this.boundingLeaf.transformedRegion;
/*     */     }
/* 505 */     else if (this.regionOfInfluence != null) {
/* 506 */       this.region = this.regionOfInfluence.copy(this.region);
/* 507 */       this.region.transform(this.regionOfInfluence, getCurrentLocalToVworld());
/*     */     } else {
/* 509 */       this.region = null;
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
/* 520 */     if (this.inImmCtx) {
/* 521 */       throw new IllegalSharingException(J3dI18N.getString("AlternateAppearanceRetained13"));
/*     */     }
/*     */     
/* 524 */     if (this.inSharedGroup) {
/* 525 */       throw new IllegalSharingException(J3dI18N.getString("AlternateAppearanceRetained15"));
/*     */     }
/*     */ 
/*     */     
/* 529 */     if (this.inBackgroundGroup) {
/* 530 */       throw new IllegalSceneGraphException(J3dI18N.getString("AlternateAppearanceRetained16"));
/*     */     }
/*     */ 
/*     */     
/* 534 */     doSetLive(paramSetLiveState);
/*     */     
/* 536 */     if (this.appearance != null) {
/* 537 */       if (this.appearance.getInImmCtx()) {
/* 538 */         throw new IllegalSharingException(J3dI18N.getString("AlternateAppearanceRetained14"));
/*     */       }
/* 540 */       synchronized (this.appearance.liveStateLock) {
/* 541 */         this.appearance.setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 548 */     if (this.mirrorAltApp == null) {
/* 549 */       this.mirrorAltApp = (AlternateAppearanceRetained)clone();
/*     */ 
/*     */ 
/*     */       
/* 553 */       this.mirrorAltApp.boundingLeaf = null;
/* 554 */       this.mirrorAltApp.sgAltApp = this;
/*     */     } 
/*     */ 
/*     */     
/* 558 */     if (this.boundingLeaf != null) {
/* 559 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorAltApp);
/*     */     }
/*     */     
/* 562 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 563 */       paramSetLiveState.viewScopedNodeList.add(this.mirrorAltApp);
/* 564 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 566 */       paramSetLiveState.nodeList.add(this.mirrorAltApp);
/*     */     } 
/*     */     
/* 569 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 570 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorAltApp, 1);
/*     */       
/* 572 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */ 
/*     */     
/* 576 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 578 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorAltApp, 1);
/*     */     }
/* 580 */     this.mirrorAltApp.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*     */     
/* 582 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */ 
/*     */ 
/*     */     
/* 586 */     markAsLive();
/*     */ 
/*     */ 
/*     */     
/* 590 */     J3dMessage j3dMessage = new J3dMessage();
/* 591 */     j3dMessage.threads = 4096;
/* 592 */     j3dMessage.universe = this.universe;
/* 593 */     j3dMessage.type = 41;
/* 594 */     j3dMessage.args[0] = this;
/*     */ 
/*     */     
/* 597 */     j3dMessage.args[1] = new Integer(16);
/* 598 */     ArrayList arrayList = new ArrayList();
/* 599 */     for (byte b = 0; b < this.scopes.size(); b++) {
/* 600 */       GroupRetained groupRetained = (GroupRetained)this.scopes.get(b);
/* 601 */       this.tempKey.reset();
/* 602 */       groupRetained.addAllNodesForScopedAltApp(this.mirrorAltApp, arrayList, this.tempKey);
/*     */     } 
/* 604 */     Object[] arrayOfObject1 = new Object[2];
/* 605 */     arrayOfObject1[0] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 606 */     arrayOfObject1[1] = arrayList;
/* 607 */     j3dMessage.args[2] = arrayOfObject1;
/* 608 */     if (this.appearance != null) {
/* 609 */       j3dMessage.args[3] = this.appearance.mirror;
/*     */     } else {
/*     */       
/* 612 */       j3dMessage.args[3] = null;
/*     */     } 
/* 614 */     Object[] arrayOfObject2 = new Object[2];
/* 615 */     arrayOfObject2[0] = this.boundingLeaf;
/* 616 */     arrayOfObject2[1] = (this.regionOfInfluence != null) ? this.regionOfInfluence.clone() : null;
/* 617 */     j3dMessage.args[4] = arrayOfObject2;
/* 618 */     VirtualUniverse.mc.processMessage(j3dMessage);
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
/*     */   void initMirrorObject(Object[] paramArrayOfObject) {
/* 631 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[2];
/* 632 */     Boolean bool = (Boolean)arrayOfObject[0];
/* 633 */     ArrayList arrayList = (ArrayList)arrayOfObject[1];
/* 634 */     AppearanceRetained appearanceRetained = (AppearanceRetained)paramArrayOfObject[3];
/* 635 */     BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)(Object[])paramArrayOfObject[4][0];
/* 636 */     Bounds bounds = (Bounds)(Object[])paramArrayOfObject[4][1];
/*     */     
/* 638 */     for (byte b = 0; b < arrayList.size(); b++) {
/* 639 */       Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList.get(b)).source;
/* 640 */       shape3DRetained.addAltApp(this.mirrorAltApp);
/*     */     } 
/* 642 */     this.mirrorAltApp.isScoped = bool.booleanValue();
/*     */     
/* 644 */     if (appearanceRetained != null) {
/* 645 */       this.mirrorAltApp.appearance = appearanceRetained;
/*     */     }
/* 647 */     if (boundingLeafRetained != null) {
/* 648 */       this.mirrorAltApp.boundingLeaf = boundingLeafRetained.mirrorBoundingLeaf;
/* 649 */       this.mirrorAltApp.region = this.boundingLeaf.transformedRegion;
/*     */     } else {
/* 651 */       this.mirrorAltApp.boundingLeaf = null;
/* 652 */       this.mirrorAltApp.region = null;
/*     */     } 
/*     */     
/* 655 */     if (bounds != null) {
/* 656 */       this.mirrorAltApp.regionOfInfluence = bounds;
/* 657 */       if (this.mirrorAltApp.region == null) {
/* 658 */         this.mirrorAltApp.region = (Bounds)this.regionOfInfluence.clone();
/* 659 */         this.mirrorAltApp.region.transform(this.regionOfInfluence, getLastLocalToVworld());
/*     */       } 
/*     */     } else {
/*     */       
/* 663 */       this.mirrorAltApp.regionOfInfluence = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void clearMirrorObject(Object[] paramArrayOfObject) {
/* 670 */     ArrayList arrayList1 = (ArrayList)paramArrayOfObject[2];
/* 671 */     ArrayList arrayList2 = new ArrayList();
/*     */     
/* 673 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 674 */       Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b)).source;
/* 675 */       shape3DRetained.removeAltApp(this.mirrorAltApp);
/*     */     } 
/* 677 */     this.mirrorAltApp.isScoped = false;
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
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 692 */     if (this.appearance != null) {
/* 693 */       synchronized (this.appearance.liveStateLock) {
/* 694 */         this.appearance.clearLive(paramSetLiveState.refCount);
/*     */       } 
/*     */     }
/*     */     
/* 698 */     super.clearLive(paramSetLiveState);
/* 699 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */ 
/*     */ 
/*     */     
/* 703 */     if (this.mirrorAltApp.boundingLeaf != null) {
/* 704 */       this.mirrorAltApp.boundingLeaf.removeUser(this.mirrorAltApp);
/*     */     }
/* 706 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 707 */       paramSetLiveState.viewScopedNodeList.add(this.mirrorAltApp);
/* 708 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 710 */       paramSetLiveState.nodeList.add(this.mirrorAltApp);
/*     */     } 
/* 712 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 713 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorAltApp, 1);
/*     */       
/* 715 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/* 717 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 719 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorAltApp, 1);
/*     */     }
/*     */ 
/*     */     
/* 723 */     if (this.scopes.size() > 0) {
/* 724 */       J3dMessage j3dMessage = new J3dMessage();
/* 725 */       j3dMessage.threads = 4096;
/* 726 */       j3dMessage.universe = this.universe;
/* 727 */       j3dMessage.type = 41;
/* 728 */       j3dMessage.args[0] = this;
/* 729 */       j3dMessage.args[1] = new Integer(32);
/* 730 */       ArrayList arrayList = new ArrayList();
/* 731 */       for (byte b = 0; b < this.scopes.size(); b++) {
/* 732 */         GroupRetained groupRetained = (GroupRetained)this.scopes.get(b);
/* 733 */         this.tempKey.reset();
/* 734 */         groupRetained.removeAllNodesForScopedAltApp(this.mirrorAltApp, arrayList, this.tempKey);
/*     */       } 
/* 736 */       j3dMessage.args[2] = arrayList;
/* 737 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformChange() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateTransformChange() {
/* 751 */     if (this.boundingLeaf == null && 
/* 752 */       this.regionOfInfluence != null) {
/* 753 */       this.region = this.regionOfInfluence.copy(this.region);
/* 754 */       this.region.transform(this.regionOfInfluence, this.sgAltApp.getCurrentLocalToVworld());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 762 */     J3dMessage j3dMessage = new J3dMessage();
/* 763 */     j3dMessage.threads = 4224;
/* 764 */     j3dMessage.universe = this.universe;
/* 765 */     j3dMessage.type = 41;
/* 766 */     j3dMessage.args[0] = this;
/* 767 */     j3dMessage.args[1] = new Integer(paramInt);
/* 768 */     j3dMessage.args[2] = paramObject;
/* 769 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 774 */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) { paramArrayList.add(this.mirrorAltApp); }
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
/* 800 */   void duplicateAttributes(Node paramNode, boolean paramBoolean) { throw new RuntimeException("method not implemented"); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\AlternateAppearanceRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */