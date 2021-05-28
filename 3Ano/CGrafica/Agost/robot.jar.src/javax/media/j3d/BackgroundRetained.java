/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BackgroundRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int COLOR_CHANGED = 1;
/*     */   static final int IMAGE_CHANGED = 2;
/*     */   static final int GEOMETRY_CHANGED = 4;
/*     */   static final int BOUNDS_CHANGED = 8;
/*     */   static final int BOUNDINGLEAF_CHANGED = 16;
/*     */   static final int IMAGE_SCALE_CHANGED = 32;
/*     */   Color3f color;
/*     */   ImageComponent2DRetained image;
/*     */   Texture2DRetained texture;
/*     */   int imageScaleMode;
/*     */   Bounds applicationRegion;
/*     */   BoundingLeafRetained boundingLeaf;
/*     */   BranchGroup geometryBranch;
/*     */   Bounds transformedRegion;
/*     */   SetLiveState setLiveState;
/*     */   Locale cachedLocale;
/*     */   boolean inImmCtx;
/*     */   ArrayList lights;
/*     */   ArrayList fogs;
/*     */   ArrayList bgGeometryAtomList;
/*     */   boolean bgGeometryAtomListDirty;
/*     */   GeometryAtom[] bgGeometryAtoms;
/*     */   static final int targetThreads = 4224;
/*     */   boolean isViewScoped;
/*     */   
/*     */   BackgroundRetained() {
/*  38 */     this.color = new Color3f(0.0F, 0.0F, 0.0F);
/*  39 */     this.image = null;
/*  40 */     this.texture = null;
/*     */ 
/*     */     
/*  43 */     this.imageScaleMode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.applicationRegion = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.geometryBranch = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     this.transformedRegion = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     this.setLiveState = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.cachedLocale = null;
/*     */ 
/*     */     
/*  80 */     this.inImmCtx = false;
/*     */ 
/*     */     
/*  83 */     this.lights = new ArrayList();
/*     */ 
/*     */     
/*  86 */     this.fogs = new ArrayList();
/*     */ 
/*     */     
/*  89 */     this.bgGeometryAtomList = new ArrayList();
/*     */ 
/*     */     
/*  92 */     this.bgGeometryAtomListDirty = true;
/*     */ 
/*     */     
/*  95 */     this.bgGeometryAtoms = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     this.isViewScoped = false;
/*     */ 
/*     */     
/* 107 */     this.nodeType = 1;
/* 108 */     this.localBounds = new BoundingBox();
/* 109 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/* 110 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   final void initColor(Color3f paramColor3f) { this.color.set(paramColor3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setColor(Color3f paramColor3f) {
/* 130 */     initColor(paramColor3f);
/* 131 */     if (this.source.isLive()) {
/* 132 */       sendMessage(1, new Color3f(paramColor3f));
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
/*     */   final void initColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 145 */     this.color.x = paramFloat1;
/* 146 */     this.color.y = paramFloat2;
/* 147 */     this.color.z = paramFloat3;
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
/* 160 */   final void setColor(float paramFloat1, float paramFloat2, float paramFloat3) { setColor(new Color3f(paramFloat1, paramFloat2, paramFloat3)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   final void getColor(Color3f paramColor3f) { paramColor3f.set(this.color); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   final void initImageScaleMode(int paramInt) { this.imageScaleMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setImageScaleMode(int paramInt) {
/* 185 */     initImageScaleMode(paramInt);
/* 186 */     if (this.source.isLive()) {
/* 187 */       sendMessage(32, new Integer(paramInt));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   final int getImageScaleMode() { return this.imageScaleMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initImage(ImageComponent2D paramImageComponent2D) {
/* 205 */     if (paramImageComponent2D == null) {
/* 206 */       this.image = null;
/* 207 */       this.texture = null;
/*     */       
/*     */       return;
/*     */     } 
/* 211 */     if (paramImageComponent2D.retained != this.image) {
/* 212 */       byte b; this.image = (ImageComponent2DRetained)paramImageComponent2D.retained;
/* 213 */       this.image.setEnforceNonPowerOfTwoSupport(true);
/* 214 */       switch (this.image.getNumberOfComponents()) {
/*     */         case 1:
/* 216 */           b = 1;
/*     */           break;
/*     */         case 2:
/* 219 */           b = 4;
/*     */           break;
/*     */         case 3:
/* 222 */           b = 5;
/*     */           break;
/*     */         case 4:
/* 225 */           b = 6;
/*     */           break;
/*     */         
/*     */         default:
/*     */           assert false;
/*     */           return;
/*     */       } 
/* 232 */       Texture2D texture2D = new Texture2D(1, b, paramImageComponent2D.getWidth(), paramImageComponent2D.getHeight());
/*     */       
/* 234 */       this.texture = (Texture2DRetained)texture2D.retained;
/*     */       
/* 236 */       this.texture.setUseAsRaster(true);
/*     */       
/* 238 */       this.image.addUser(this.texture);
/* 239 */       this.texture.initImage(0, paramImageComponent2D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setImage(ImageComponent2D paramImageComponent2D) {
/* 248 */     if (this.source.isLive() && 
/* 249 */       this.texture != null) {
/* 250 */       this.texture.clearLive(this.refCount);
/*     */     }
/*     */     
/* 253 */     initImage(paramImageComponent2D);
/* 254 */     if (this.source.isLive()) {
/* 255 */       if (this.texture != null) {
/* 256 */         this.texture.setLive(this.inBackgroundGroup, this.refCount);
/*     */       }
/*     */       
/* 259 */       sendMessage(2, (this.texture != null) ? this.texture.mirror : null);
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
/* 270 */   final ImageComponent2D getImage() { return (this.image == null) ? null : (ImageComponent2D)this.image.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   final void initGeometry(BranchGroup paramBranchGroup) { this.geometryBranch = paramBranchGroup; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setGeometry(BranchGroup paramBranchGroup) {
/* 288 */     boolean bool = false;
/*     */ 
/*     */     
/* 291 */     if (this.source.isLive()) {
/*     */       
/* 293 */       if (this.geometryBranch != null)
/* 294 */         bool += true; 
/* 295 */       if (paramBranchGroup != null)
/* 296 */         bool += true; 
/* 297 */       J3dMessage[] arrayOfJ3dMessage = new J3dMessage[bool]; byte b;
/* 298 */       for (b = 0; b < bool; b++) {
/* 299 */         arrayOfJ3dMessage[b] = new J3dMessage();
/*     */       }
/* 301 */       b = 0;
/* 302 */       if (this.geometryBranch != null) {
/* 303 */         clearGeometryBranch((BranchGroupRetained)this.geometryBranch.retained);
/* 304 */         (arrayOfJ3dMessage[b]).threads = 4224;
/*     */         
/* 306 */         (arrayOfJ3dMessage[b]).type = 33;
/* 307 */         (arrayOfJ3dMessage[b]).universe = this.universe;
/* 308 */         (arrayOfJ3dMessage[b]).args[0] = this.setLiveState.ogList.toArray();
/* 309 */         (arrayOfJ3dMessage[b]).args[1] = this.setLiveState.ogChildIdList.toArray();
/* 310 */         (arrayOfJ3dMessage[b]).args[3] = this.setLiveState.ogCIOList.toArray();
/* 311 */         (arrayOfJ3dMessage[b]).args[4] = this.setLiveState.ogCIOTableList.toArray();
/* 312 */         b++;
/*     */         
/* 314 */         (arrayOfJ3dMessage[b]).threads = this.setLiveState.notifyThreads;
/* 315 */         (arrayOfJ3dMessage[b]).type = 1;
/* 316 */         (arrayOfJ3dMessage[b]).universe = this.universe;
/* 317 */         (arrayOfJ3dMessage[b]).args[0] = this.setLiveState.nodeList.toArray();
/* 318 */         b++;
/*     */       } 
/*     */       
/* 321 */       if (paramBranchGroup != null) {
/* 322 */         setGeometryBranch((BranchGroupRetained)paramBranchGroup.retained);
/* 323 */         (arrayOfJ3dMessage[b]).threads = 4224;
/*     */         
/* 325 */         (arrayOfJ3dMessage[b]).type = 32;
/* 326 */         (arrayOfJ3dMessage[b]).universe = this.universe;
/* 327 */         (arrayOfJ3dMessage[b]).args[0] = this.setLiveState.ogList.toArray();
/* 328 */         (arrayOfJ3dMessage[b]).args[1] = this.setLiveState.ogChildIdList.toArray();
/* 329 */         (arrayOfJ3dMessage[b]).args[2] = this.setLiveState.ogOrderedIdList.toArray();
/* 330 */         (arrayOfJ3dMessage[b]).args[3] = this.setLiveState.ogCIOList.toArray();
/* 331 */         (arrayOfJ3dMessage[b]).args[4] = this.setLiveState.ogCIOTableList.toArray();
/* 332 */         b++;
/*     */         
/* 334 */         (arrayOfJ3dMessage[b]).threads = this.setLiveState.notifyThreads;
/* 335 */         (arrayOfJ3dMessage[b]).type = 0;
/* 336 */         (arrayOfJ3dMessage[b]).universe = this.universe;
/* 337 */         (arrayOfJ3dMessage[b]).args[0] = this.setLiveState.nodeList.toArray();
/*     */       } 
/* 339 */       VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*     */       
/* 341 */       this.setLiveState.reset(null);
/*     */     } 
/* 343 */     initGeometry(paramBranchGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   final BranchGroup getGeometry() { return this.geometryBranch; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initApplicationBounds(Bounds paramBounds) {
/* 359 */     if (paramBounds != null) {
/* 360 */       this.applicationRegion = (Bounds)paramBounds.clone();
/*     */     } else {
/* 362 */       this.applicationRegion = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setApplicationBounds(Bounds paramBounds) {
/* 371 */     initApplicationBounds(paramBounds);
/*     */     
/* 373 */     if (this.boundingLeaf == null) {
/* 374 */       J3dMessage j3dMessage = new J3dMessage();
/* 375 */       j3dMessage.threads = 4224;
/*     */       
/* 377 */       j3dMessage.type = 20;
/* 378 */       j3dMessage.universe = this.universe;
/* 379 */       j3dMessage.args[0] = this;
/* 380 */       j3dMessage.args[1] = new Integer(8);
/* 381 */       if (paramBounds != null) {
/* 382 */         j3dMessage.args[2] = paramBounds.clone();
/*     */       } else {
/* 384 */         j3dMessage.args[2] = null;
/* 385 */       }  VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   final Bounds getApplicationBounds() { return (this.applicationRegion != null) ? (Bounds)this.applicationRegion.clone() : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initApplicationBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 402 */     if (paramBoundingLeaf != null) {
/* 403 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*     */     } else {
/* 405 */       this.boundingLeaf = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setApplicationBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 413 */     if (this.boundingLeaf != null) {
/* 414 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this);
/*     */     }
/* 416 */     if (paramBoundingLeaf != null) {
/* 417 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/* 418 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this);
/*     */     } else {
/* 420 */       this.boundingLeaf = null;
/*     */     } 
/* 422 */     J3dMessage j3dMessage = new J3dMessage();
/* 423 */     j3dMessage.threads = 4224;
/*     */     
/* 425 */     j3dMessage.type = 20;
/* 426 */     j3dMessage.universe = this.universe;
/* 427 */     j3dMessage.args[0] = this;
/* 428 */     j3dMessage.args[1] = new Integer(16);
/* 429 */     if (this.boundingLeaf != null) {
/* 430 */       j3dMessage.args[2] = this.boundingLeaf.mirrorBoundingLeaf;
/* 431 */       j3dMessage.args[3] = null;
/*     */     } else {
/* 433 */       j3dMessage.args[2] = null;
/* 434 */       if (this.applicationRegion != null) {
/* 435 */         j3dMessage.args[3] = this.applicationRegion.clone();
/*     */       } else {
/* 437 */         j3dMessage.args[3] = null;
/*     */       } 
/* 439 */     }  VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 447 */   BoundingLeaf getApplicationBoundingLeaf() { return (this.boundingLeaf != null) ? (BoundingLeaf)this.boundingLeaf.source : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 455 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 462 */   boolean getInImmCtx() { return this.inImmCtx; }
/*     */ 
/*     */   
/*     */   void setGeometryBranch(BranchGroupRetained paramBranchGroupRetained) {
/* 466 */     this.setLiveState.reset(this.locale);
/* 467 */     this.setLiveState.inBackgroundGroup = true;
/*     */     
/* 469 */     this.setLiveState.geometryBackground = this;
/* 470 */     this.setLiveState.currentTransforms[0] = new Transform3D[2];
/* 471 */     this.setLiveState.currentTransforms[0][0] = new Transform3D();
/* 472 */     this.setLiveState.currentTransforms[0][1] = new Transform3D();
/* 473 */     this.setLiveState.currentTransformsIndex[0] = new int[2];
/* 474 */     this.setLiveState.currentTransformsIndex[0][0] = 0;
/* 475 */     this.setLiveState.currentTransformsIndex[0][1] = 0;
/*     */     
/* 477 */     this.setLiveState.localToVworld = this.setLiveState.currentTransforms;
/* 478 */     this.setLiveState.localToVworldIndex = this.setLiveState.currentTransformsIndex;
/*     */     
/* 480 */     this.setLiveState.branchGroupPaths = new ArrayList();
/* 481 */     this.setLiveState.branchGroupPaths.add(new BranchGroupRetained[0]);
/*     */     
/* 483 */     this.setLiveState.orderedPaths = new ArrayList(1);
/* 484 */     this.setLiveState.orderedPaths.add(new OrderedPath());
/*     */     
/* 486 */     this.setLiveState.switchStates = new ArrayList(1);
/* 487 */     this.setLiveState.switchStates.add(new SwitchState(false));
/*     */ 
/*     */     
/* 490 */     paramBranchGroupRetained.setLive(this.setLiveState);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void clearGeometryBranch(BranchGroupRetained paramBranchGroupRetained) {
/* 496 */     this.setLiveState.reset(this.locale);
/* 497 */     this.setLiveState.inBackgroundGroup = true;
/* 498 */     this.setLiveState.geometryBackground = this;
/* 499 */     paramBranchGroupRetained.clearLive(this.setLiveState);
/* 500 */     paramBranchGroupRetained.setParent(null);
/* 501 */     paramBranchGroupRetained.setLocale(null);
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
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 513 */     doSetLive(paramSetLiveState);
/*     */     
/* 515 */     if (this.inImmCtx) {
/* 516 */       throw new IllegalSharingException(J3dI18N.getString("BackgroundRetained1"));
/*     */     }
/*     */     
/* 519 */     if (this.inBackgroundGroup) {
/* 520 */       throw new IllegalSceneGraphException(J3dI18N.getString("BackgroundRetained5"));
/*     */     }
/*     */ 
/*     */     
/* 524 */     if (this.inSharedGroup) {
/* 525 */       throw new IllegalSharingException(J3dI18N.getString("BackgroundRetained6"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 530 */     if (this.geometryBranch != null) {
/* 531 */       BranchGroupRetained branchGroupRetained = (BranchGroupRetained)this.geometryBranch.retained;
/*     */       
/* 533 */       if (branchGroupRetained.inBackgroundGroup == true) {
/* 534 */         throw new IllegalSharingException(J3dI18N.getString("BackgroundRetained0"));
/*     */       }
/*     */       
/* 537 */       if (branchGroupRetained.parent != null) {
/* 538 */         throw new IllegalSharingException(J3dI18N.getString("BackgroundRetained3"));
/*     */       }
/*     */       
/* 541 */       if (branchGroupRetained.locale != null) {
/* 542 */         throw new IllegalSharingException(J3dI18N.getString("BackgroundRetained4"));
/*     */       }
/*     */       
/* 545 */       if (this.setLiveState == null) {
/* 546 */         this.setLiveState = new SetLiveState(this.universe);
/* 547 */         this.setLiveState.universe = this.universe;
/*     */       } 
/* 549 */       setGeometryBranch((BranchGroupRetained)this.geometryBranch.retained);
/*     */       
/* 551 */       paramSetLiveState.nodeList.addAll(this.setLiveState.nodeList);
/* 552 */       paramSetLiveState.notifyThreads |= this.setLiveState.notifyThreads;
/* 553 */       paramSetLiveState.ogList.addAll(this.setLiveState.ogList);
/* 554 */       paramSetLiveState.ogChildIdList.addAll(this.setLiveState.ogChildIdList);
/* 555 */       paramSetLiveState.ogOrderedIdList.addAll(this.setLiveState.ogOrderedIdList);
/*     */       
/* 557 */       this.setLiveState.reset(null);
/*     */     } 
/*     */     
/* 560 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 561 */       paramSetLiveState.viewScopedNodeList.add(this);
/* 562 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 564 */       paramSetLiveState.nodeList.add(this);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 569 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null) {
/* 570 */       paramSetLiveState.switchTargets[0].addNode(this, 1);
/*     */     }
/* 572 */     this.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*     */ 
/*     */     
/* 575 */     if (this.boundingLeaf != null) {
/* 576 */       this.transformedRegion = this.boundingLeaf.mirrorBoundingLeaf.transformedRegion;
/*     */ 
/*     */     
/*     */     }
/* 580 */     else if (this.applicationRegion != null) {
/* 581 */       this.transformedRegion = (Bounds)this.applicationRegion.clone();
/* 582 */       this.transformedRegion.transform(this.applicationRegion, getLastLocalToVworld());
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 587 */       this.transformedRegion = null;
/*     */     } 
/*     */ 
/*     */     
/* 591 */     this.cachedLocale = paramSetLiveState.locale;
/*     */ 
/*     */     
/* 594 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 595 */       paramSetLiveState.transformTargets[0].addNode(this, 1);
/* 596 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */     
/* 599 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */ 
/*     */     
/* 602 */     if (this.texture != null) {
/* 603 */       this.texture.setLive(this.inBackgroundGroup, this.refCount);
/*     */     }
/* 605 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 614 */     super.clearLive(paramSetLiveState);
/* 615 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 616 */       paramSetLiveState.viewScopedNodeList.add(this);
/* 617 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 619 */       paramSetLiveState.nodeList.add(this);
/*     */     } 
/* 621 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 622 */       paramSetLiveState.transformTargets[0].addNode(this, 1);
/* 623 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */     
/* 626 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null) {
/* 627 */       paramSetLiveState.switchTargets[0].addNode(this, 1);
/*     */     }
/*     */     
/* 630 */     if (this.geometryBranch != null) {
/* 631 */       BranchGroupRetained branchGroupRetained = (BranchGroupRetained)this.geometryBranch.retained;
/*     */       
/* 633 */       clearGeometryBranch((BranchGroupRetained)this.geometryBranch.retained);
/*     */       
/* 635 */       paramSetLiveState.nodeList.addAll(this.setLiveState.nodeList);
/* 636 */       paramSetLiveState.ogList.addAll(this.setLiveState.ogList);
/* 637 */       paramSetLiveState.ogChildIdList.addAll(this.setLiveState.ogChildIdList);
/* 638 */       paramSetLiveState.notifyThreads |= this.setLiveState.notifyThreads;
/*     */       
/* 640 */       this.setLiveState.reset(null);
/* 641 */       this.lights.clear();
/* 642 */       this.fogs.clear();
/*     */     } 
/*     */     
/* 645 */     if (this.texture != null) {
/* 646 */       this.texture.clearLive(this.refCount);
/*     */     }
/*     */     
/* 649 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/* 655 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 660 */     if ((i & 0x8) != 0) {
/* 661 */       if (paramArrayOfObject[2] != null) {
/* 662 */         this.transformedRegion = ((Bounds)paramArrayOfObject[2]).copy(this.transformedRegion);
/* 663 */         this.transformedRegion.transform((Bounds)paramArrayOfObject[2], getCurrentLocalToVworld());
/*     */       }
/*     */       else {
/*     */         
/* 667 */         this.transformedRegion = null;
/*     */       }
/*     */     
/* 670 */     } else if ((i & 0x10) != 0) {
/* 671 */       if (paramArrayOfObject[2] != null) {
/* 672 */         this.transformedRegion = ((BoundingLeafRetained)paramArrayOfObject[2]).transformedRegion;
/*     */       } else {
/*     */         
/* 675 */         Bounds bounds = (Bounds)paramArrayOfObject[3];
/* 676 */         if (bounds != null) {
/* 677 */           this.transformedRegion = bounds.copy(this.transformedRegion);
/* 678 */           this.transformedRegion.transform(bounds, getCurrentLocalToVworld());
/*     */         }
/*     */         else {
/*     */           
/* 682 */           this.transformedRegion = null;
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
/*     */   void updateBoundingLeaf() {
/* 695 */     if (this.boundingLeaf != null && this.boundingLeaf.mirrorBoundingLeaf.switchState.currentSwitchOn) {
/*     */       
/* 697 */       this.transformedRegion = this.boundingLeaf.mirrorBoundingLeaf.transformedRegion;
/*     */     
/*     */     }
/* 700 */     else if (this.applicationRegion != null) {
/* 701 */       this.transformedRegion = this.applicationRegion.copy(this.transformedRegion);
/* 702 */       this.transformedRegion.transform(this.applicationRegion, getCurrentLocalToVworld());
/*     */     } else {
/*     */       
/* 705 */       this.transformedRegion = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateTransformChange() {
/* 712 */     if (this.boundingLeaf == null && 
/* 713 */       this.applicationRegion != null) {
/* 714 */       this.transformedRegion = this.applicationRegion.copy(this.transformedRegion);
/* 715 */       this.transformedRegion.transform(this.applicationRegion, getCurrentLocalToVworld());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 723 */     J3dMessage j3dMessage = new J3dMessage();
/* 724 */     j3dMessage.threads = 4224;
/* 725 */     j3dMessage.universe = this.universe;
/* 726 */     j3dMessage.type = 20;
/* 727 */     j3dMessage.args[0] = this;
/* 728 */     j3dMessage.args[1] = new Integer(paramInt);
/* 729 */     j3dMessage.args[2] = paramObject;
/* 730 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */   
/*     */   void addBgGeometryAtomList(GeometryAtom paramGeometryAtom) {
/* 734 */     this.bgGeometryAtomList.add(paramGeometryAtom);
/* 735 */     this.bgGeometryAtomListDirty = true;
/*     */   }
/*     */   
/*     */   void removeBgGeometryAtomList(GeometryAtom paramGeometryAtom) {
/* 739 */     this.bgGeometryAtomList.remove(this.bgGeometryAtomList.indexOf(paramGeometryAtom));
/* 740 */     this.bgGeometryAtomListDirty = true;
/*     */   }
/*     */   
/*     */   GeometryAtom[] getBackgroundGeometryAtoms() {
/* 744 */     if (this.bgGeometryAtomListDirty) {
/* 745 */       int i = this.bgGeometryAtomList.size();
/* 746 */       if (i == 0) {
/* 747 */         this.bgGeometryAtoms = null;
/*     */       } else {
/* 749 */         this.bgGeometryAtoms = new GeometryAtom[i];
/* 750 */         for (byte b = 0; b < this.bgGeometryAtoms.length; b++) {
/* 751 */           this.bgGeometryAtoms[b] = (GeometryAtom)this.bgGeometryAtomList.get(b);
/*     */         }
/* 753 */         this.bgGeometryAtomListDirty = false;
/*     */       } 
/*     */     } 
/* 756 */     return this.bgGeometryAtoms;
/*     */   }
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 760 */     super.mergeTransform(paramTransformGroupRetained);
/* 761 */     if (this.applicationRegion != null) {
/* 762 */       this.applicationRegion.transform(paramTransformGroupRetained.transform);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void notifyImageComponentImageChanged(ImageComponentRetained paramImageComponentRetained, ImageComponentUpdateInfo paramImageComponentUpdateInfo) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 774 */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) { paramArrayList.add(this); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\BackgroundRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */