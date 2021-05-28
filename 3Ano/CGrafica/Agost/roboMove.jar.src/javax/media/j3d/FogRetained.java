/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class FogRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int COLOR_CHANGED = 1;
/*     */   static final int SCOPE_CHANGED = 2;
/*     */   static final int BOUNDS_CHANGED = 4;
/*     */   static final int BOUNDINGLEAF_CHANGED = 8;
/*     */   static final int INIT_MIRROR = 16;
/*     */   static final int CLEAR_MIRROR = 32;
/*     */   static final int LAST_DEFINED_BIT = 32;
/*     */   Color3f color;
/*     */   Bounds regionOfInfluence;
/*     */   BoundingLeafRetained boundingLeaf;
/*     */   Vector scopes;
/*     */   int isDirty;
/*     */   boolean inImmCtx;
/*     */   Bounds region;
/*     */   FogRetained sgFog;
/*     */   FogRetained mirrorFog;
/*     */   static final int targetThreads = 4224;
/*     */   boolean isScoped;
/*     */   HashKey tempKey;
/*     */   UnorderList environmentSets;
/*     */   boolean isViewScoped;
/*     */   private double localToVworldScale;
/*     */   
/*     */   FogRetained() {
/*  37 */     this.color = new Color3f(0.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.regionOfInfluence = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.scopes = new Vector();
/*     */ 
/*     */     
/*  55 */     this.isDirty = 65535;
/*     */ 
/*     */     
/*  58 */     this.inImmCtx = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     this.region = null;
/*     */ 
/*     */     
/*  66 */     this.sgFog = null;
/*     */ 
/*     */     
/*  69 */     this.mirrorFog = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     this.isScoped = false;
/*     */ 
/*     */ 
/*     */     
/*  80 */     this.tempKey = new HashKey(250);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.environmentSets = new UnorderList(1, EnvironmentSet.class);
/*     */ 
/*     */     
/*  91 */     this.isViewScoped = false;
/*     */ 
/*     */     
/*  94 */     this.localToVworldScale = 1.0D;
/*     */ 
/*     */     
/*  97 */     this.localBounds = new BoundingBox();
/*  98 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  99 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   void initColor(Color3f paramColor3f) { this.color.set(paramColor3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setColor(Color3f paramColor3f) {
/* 112 */     this.color.set(paramColor3f);
/* 113 */     sendMessage(1, new Color3f(paramColor3f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 120 */     this.color.x = paramFloat1;
/* 121 */     this.color.y = paramFloat2;
/* 122 */     this.color.z = paramFloat3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 128 */     initColor(paramFloat1, paramFloat2, paramFloat3);
/* 129 */     sendMessage(1, new Color3f(paramFloat1, paramFloat2, paramFloat3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   void getColor(Color3f paramColor3f) { paramColor3f.set(this.color); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initInfluencingBounds(Bounds paramBounds) {
/* 143 */     if (paramBounds != null) {
/* 144 */       this.regionOfInfluence = (Bounds)paramBounds.clone();
/*     */     } else {
/* 146 */       this.regionOfInfluence = null;
/*     */     } 
/* 148 */     if (this.staticTransform != null) {
/* 149 */       this.regionOfInfluence.transform(this.staticTransform.transform);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInfluencingBounds(Bounds paramBounds) {
/* 157 */     initInfluencingBounds(paramBounds);
/* 158 */     sendMessage(4, (paramBounds != null) ? paramBounds.clone() : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Bounds getInfluencingBounds() {
/* 166 */     Bounds bounds = null;
/* 167 */     if (this.regionOfInfluence != null) {
/* 168 */       bounds = (Bounds)this.regionOfInfluence.clone();
/* 169 */       if (this.staticTransform != null) {
/* 170 */         Transform3D transform3D = this.staticTransform.getInvTransform();
/* 171 */         bounds.transform(transform3D);
/*     */       } 
/*     */     } 
/* 174 */     return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 181 */     if (paramBoundingLeaf != null) {
/* 182 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*     */     } else {
/* 184 */       this.boundingLeaf = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 192 */     if (this.boundingLeaf != null)
/* 193 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this.mirrorFog); 
/* 194 */     if (paramBoundingLeaf != null) {
/* 195 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/* 196 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorFog);
/*     */     } else {
/* 198 */       this.boundingLeaf = null;
/*     */     } 
/* 200 */     sendMessage(8, (this.boundingLeaf != null) ? this.boundingLeaf.mirrorBoundingLeaf : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   BoundingLeaf getInfluencingBoundingLeaf() { return (this.boundingLeaf != null) ? (BoundingLeaf)this.boundingLeaf.source : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   void initScope(Group paramGroup, int paramInt) { this.scopes.setElementAt((GroupRetained)paramGroup.retained, paramInt); }
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
/* 231 */     ArrayList arrayList1 = new ArrayList();
/* 232 */     ArrayList arrayList2 = new ArrayList();
/*     */     
/* 234 */     Object[] arrayOfObject = new Object[3];
/*     */ 
/*     */     
/* 237 */     GroupRetained groupRetained = (GroupRetained)this.scopes.get(paramInt);
/* 238 */     this.tempKey.reset();
/* 239 */     groupRetained.removeAllNodesForScopedFog(this.mirrorFog, arrayList2, this.tempKey);
/*     */     
/* 241 */     groupRetained = (GroupRetained)paramGroup.retained;
/* 242 */     initScope(paramGroup, paramInt);
/* 243 */     this.tempKey.reset();
/*     */ 
/*     */ 
/*     */     
/* 247 */     groupRetained.addAllNodesForScopedFog(this.mirrorFog, arrayList1, this.tempKey);
/*     */     
/* 249 */     arrayOfObject[0] = arrayList1;
/* 250 */     arrayOfObject[1] = arrayList2;
/* 251 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 252 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initInsertScope(Node paramNode, int paramInt) {
/* 262 */     GroupRetained groupRetained = (GroupRetained)paramNode.retained;
/* 263 */     groupRetained.setFogScope();
/* 264 */     this.scopes.insertElementAt((GroupRetained)paramNode.retained, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void insertScope(Node paramNode, int paramInt) {
/* 274 */     Object[] arrayOfObject = new Object[3];
/* 275 */     ArrayList arrayList = new ArrayList();
/*     */     
/* 277 */     initInsertScope(paramNode, paramInt);
/* 278 */     GroupRetained groupRetained = (GroupRetained)paramNode.retained;
/* 279 */     this.tempKey.reset();
/* 280 */     groupRetained.addAllNodesForScopedFog(this.mirrorFog, arrayList, this.tempKey);
/* 281 */     arrayOfObject[0] = arrayList;
/* 282 */     arrayOfObject[1] = null;
/* 283 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 284 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */   
/*     */   void initRemoveScope(int paramInt) {
/* 289 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/* 290 */     this.scopes.removeElementAt(paramInt);
/* 291 */     groupRetained.removeFogScope();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeScope(int paramInt) {
/* 297 */     Object[] arrayOfObject = new Object[3];
/* 298 */     ArrayList arrayList = new ArrayList();
/* 299 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/*     */     
/* 301 */     this.tempKey.reset();
/* 302 */     groupRetained.removeAllNodesForScopedFog(this.mirrorFog, arrayList, this.tempKey);
/*     */     
/* 304 */     initRemoveScope(paramInt);
/* 305 */     arrayOfObject[0] = null;
/* 306 */     arrayOfObject[1] = arrayList;
/* 307 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 308 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   Group getScope(int paramInt) { return (Group)((GroupRetained)this.scopes.elementAt(paramInt)).source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Enumeration getAllScopes() {
/* 326 */     Enumeration enumeration = this.scopes.elements();
/* 327 */     Vector vector = new Vector(this.scopes.size());
/* 328 */     while (enumeration.hasMoreElements()) {
/* 329 */       vector.add(((GroupRetained)enumeration.nextElement()).source);
/*     */     }
/* 331 */     return vector.elements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initAddScope(Group paramGroup) {
/* 340 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/* 341 */     this.scopes.addElement((GroupRetained)paramGroup.retained);
/* 342 */     groupRetained.setFogScope();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addScope(Group paramGroup) {
/* 351 */     Object[] arrayOfObject = new Object[3];
/* 352 */     ArrayList arrayList = new ArrayList();
/* 353 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*     */     
/* 355 */     initAddScope(paramGroup);
/* 356 */     this.tempKey.reset();
/* 357 */     groupRetained.addAllNodesForScopedFog(this.mirrorFog, arrayList, this.tempKey);
/* 358 */     arrayOfObject[0] = arrayList;
/* 359 */     arrayOfObject[1] = null;
/* 360 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 361 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   int numScopes() { return this.scopes.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int indexOfScope(Group paramGroup) {
/* 378 */     if (paramGroup != null) {
/* 379 */       return this.scopes.indexOf((GroupRetained)paramGroup.retained);
/*     */     }
/* 381 */     return this.scopes.indexOf(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeScope(Group paramGroup) {
/* 390 */     int i = indexOfScope(paramGroup);
/* 391 */     if (i >= 0)
/* 392 */       removeScope(i); 
/*     */   }
/*     */   
/*     */   void initRemoveScope(Group paramGroup) {
/* 396 */     int i = indexOfScope(paramGroup);
/* 397 */     if (i >= 0) {
/* 398 */       initRemoveScope(i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeAllScopes() {
/* 407 */     Object[] arrayOfObject = new Object[3];
/* 408 */     ArrayList arrayList = new ArrayList();
/*     */     
/* 410 */     int i = this.scopes.size();
/*     */     
/* 412 */     this.tempKey.reset();
/* 413 */     for (int j = i - 1; j >= 0; j--) {
/* 414 */       GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(j);
/* 415 */       groupRetained.removeAllNodesForScopedFog(this.mirrorFog, arrayList, this.tempKey);
/* 416 */       initRemoveScope(j);
/*     */     } 
/* 418 */     arrayOfObject[0] = null;
/* 419 */     arrayOfObject[1] = arrayList;
/* 420 */     arrayOfObject[2] = Boolean.FALSE;
/* 421 */     sendMessage(2, arrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initRemoveAllScopes() {
/* 428 */     int i = this.scopes.size();
/* 429 */     for (int j = i - 1; j >= 0; j--) {
/* 430 */       initRemoveScope(j);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 437 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   boolean getInImmCtx() { return this.inImmCtx; }
/*     */ 
/*     */ 
/*     */   
/* 448 */   boolean isScoped() { return (this.scopes != null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void update(Context paramContext, double paramDouble);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/* 464 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 465 */     if ((i & 0x4) != 0) {
/* 466 */       this.mirrorFog.regionOfInfluence = (Bounds)paramArrayOfObject[2];
/* 467 */       if (this.mirrorFog.boundingLeaf == null) {
/* 468 */         if (paramArrayOfObject[2] != null) {
/* 469 */           this.mirrorFog.region = this.mirrorFog.regionOfInfluence.copy(this.mirrorFog.region);
/* 470 */           this.mirrorFog.region.transform(this.mirrorFog.regionOfInfluence, getCurrentLocalToVworld());
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 475 */           this.mirrorFog.region = null;
/*     */         }
/*     */       
/*     */       }
/* 479 */     } else if ((i & 0x8) != 0) {
/* 480 */       this.mirrorFog.boundingLeaf = (BoundingLeafRetained)paramArrayOfObject[2];
/* 481 */       if (paramArrayOfObject[2] != null) {
/* 482 */         this.mirrorFog.region = this.mirrorFog.boundingLeaf.transformedRegion;
/*     */       
/*     */       }
/* 485 */       else if (this.mirrorFog.regionOfInfluence != null) {
/* 486 */         this.mirrorFog.region = this.mirrorFog.regionOfInfluence.copy(this.mirrorFog.region);
/* 487 */         this.mirrorFog.region.transform(this.mirrorFog.regionOfInfluence, getCurrentLocalToVworld());
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 492 */         this.mirrorFog.region = null;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 497 */     else if ((i & 0x2) != 0) {
/* 498 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[2];
/* 499 */       ArrayList arrayList1 = (ArrayList)arrayOfObject[0];
/* 500 */       ArrayList arrayList2 = (ArrayList)arrayOfObject[1];
/* 501 */       boolean bool = ((Boolean)arrayOfObject[2]).booleanValue();
/*     */       
/* 503 */       if (arrayList1 != null) {
/* 504 */         this.mirrorFog.isScoped = bool;
/* 505 */         for (byte b = 0; b < arrayList1.size(); b++) {
/* 506 */           Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b)).source;
/* 507 */           shape3DRetained.addFog(this.mirrorFog);
/*     */         } 
/*     */       } 
/*     */       
/* 511 */       if (arrayList2 != null) {
/* 512 */         this.mirrorFog.isScoped = bool;
/* 513 */         for (byte b = 0; b < arrayList2.size(); b++) {
/* 514 */           Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList2.get(b)).source;
/* 515 */           shape3DRetained.removeFog(this.mirrorFog);
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
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 528 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 529 */     if ((i & true) != 0) {
/* 530 */       this.mirrorFog.color.set((Color3f)paramArrayOfObject[2]);
/*     */     }
/* 532 */     if ((i & 0x10) != 0) {
/* 533 */       this.mirrorFog.color.set((Color3f)paramArrayOfObject[3]);
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
/* 544 */     if (this.boundingLeaf != null && this.boundingLeaf.switchState.currentSwitchOn) {
/* 545 */       this.region = this.boundingLeaf.transformedRegion;
/*     */     }
/* 547 */     else if (this.regionOfInfluence != null) {
/* 548 */       this.region = this.regionOfInfluence.copy(this.region);
/* 549 */       this.region.transform(this.regionOfInfluence, getCurrentLocalToVworld());
/*     */     } else {
/*     */       
/* 552 */       this.region = null;
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
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 568 */     if (this.inImmCtx) {
/* 569 */       throw new IllegalSharingException(J3dI18N.getString("FogRetained0"));
/*     */     }
/* 571 */     doSetLive(paramSetLiveState);
/*     */     
/* 573 */     if (this.inSharedGroup) {
/* 574 */       throw new IllegalSharingException(J3dI18N.getString("FogRetained1"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 582 */     if (this.mirrorFog == null) {
/*     */       
/* 584 */       this.mirrorFog = (FogRetained)clone();
/*     */ 
/*     */ 
/*     */       
/* 588 */       this.mirrorFog.boundingLeaf = null;
/* 589 */       this.mirrorFog.sgFog = this;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 594 */     if (this.boundingLeaf != null) {
/* 595 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorFog);
/*     */     }
/*     */     
/* 598 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 599 */       paramSetLiveState.viewScopedNodeList.add(this.mirrorFog);
/* 600 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 602 */       paramSetLiveState.nodeList.add(this.mirrorFog);
/*     */     } 
/*     */     
/* 605 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 606 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorFog, 1);
/* 607 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 613 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 615 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorFog, 1);
/*     */     }
/* 617 */     this.mirrorFog.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*     */     
/* 619 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 624 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initMirrorObject(Object[] paramArrayOfObject) {
/* 631 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[2];
/* 632 */     Boolean bool = (Boolean)arrayOfObject[0];
/* 633 */     ArrayList arrayList = (ArrayList)arrayOfObject[1];
/* 634 */     BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)(Object[])paramArrayOfObject[4][0];
/* 635 */     Bounds bounds = (Bounds)(Object[])paramArrayOfObject[4][1];
/*     */     
/* 637 */     this.mirrorFog.inBackgroundGroup = ((Boolean)(Object[])paramArrayOfObject[4][2]).booleanValue();
/* 638 */     this.mirrorFog.geometryBackground = (BackgroundRetained)(Object[])paramArrayOfObject[4][3];
/* 639 */     for (byte b = 0; b < arrayList.size(); b++) {
/* 640 */       Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList.get(b)).source;
/* 641 */       shape3DRetained.addFog(this.mirrorFog);
/*     */     } 
/* 643 */     this.mirrorFog.isScoped = bool.booleanValue();
/*     */     
/* 645 */     if (boundingLeafRetained != null) {
/* 646 */       this.mirrorFog.boundingLeaf = boundingLeafRetained.mirrorBoundingLeaf;
/* 647 */       this.mirrorFog.region = this.boundingLeaf.transformedRegion;
/*     */     } else {
/* 649 */       this.mirrorFog.boundingLeaf = null;
/* 650 */       this.mirrorFog.region = null;
/*     */     } 
/*     */     
/* 653 */     if (bounds != null) {
/* 654 */       this.mirrorFog.regionOfInfluence = bounds;
/* 655 */       if (this.mirrorFog.region == null) {
/* 656 */         this.mirrorFog.region = (Bounds)this.regionOfInfluence.clone();
/* 657 */         this.mirrorFog.region.transform(this.regionOfInfluence, getLastLocalToVworld());
/*     */       } 
/*     */     } else {
/*     */       
/* 661 */       this.mirrorFog.regionOfInfluence = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearMirrorObject(Object[] paramArrayOfObject) {
/* 669 */     ArrayList arrayList1 = (ArrayList)paramArrayOfObject[2];
/* 670 */     ArrayList arrayList2 = new ArrayList();
/*     */     
/* 672 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 673 */       Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b)).source;
/* 674 */       shape3DRetained.removeFog(this.mirrorFog);
/*     */     } 
/* 676 */     this.mirrorFog.isScoped = false;
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
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 692 */     super.clearLive(paramSetLiveState);
/*     */     
/* 694 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 696 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorFog, 1);
/*     */     }
/* 698 */     paramSetLiveState.notifyThreads |= 0x1080;
/*     */ 
/*     */ 
/*     */     
/* 702 */     if (this.mirrorFog.boundingLeaf != null) {
/* 703 */       this.mirrorFog.boundingLeaf.removeUser(this.mirrorFog);
/*     */     }
/* 705 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 706 */       paramSetLiveState.viewScopedNodeList.add(this.mirrorFog);
/* 707 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*     */     } else {
/* 709 */       paramSetLiveState.nodeList.add(this.mirrorFog);
/*     */     } 
/* 711 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 712 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorFog, 1);
/* 713 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */ 
/*     */     
/* 717 */     if (this.scopes.size() > 0) {
/* 718 */       J3dMessage j3dMessage = new J3dMessage();
/* 719 */       j3dMessage.threads = 4096;
/* 720 */       j3dMessage.universe = this.universe;
/* 721 */       j3dMessage.type = 22;
/* 722 */       j3dMessage.args[0] = this;
/* 723 */       j3dMessage.args[1] = new Integer(32);
/* 724 */       ArrayList arrayList = new ArrayList();
/* 725 */       for (byte b = 0; b < this.scopes.size(); b++) {
/* 726 */         GroupRetained groupRetained = (GroupRetained)this.scopes.get(b);
/* 727 */         this.tempKey.reset();
/* 728 */         groupRetained.removeAllNodesForScopedFog(this.mirrorFog, arrayList, this.tempKey);
/*     */       } 
/* 730 */       j3dMessage.args[2] = arrayList;
/* 731 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 737 */     FogRetained fogRetained = (FogRetained)super.clone();
/*     */     
/* 739 */     fogRetained.color = new Color3f(this.color);
/* 740 */     Bounds bounds = getInfluencingBounds();
/* 741 */     if (bounds != null) {
/* 742 */       fogRetained.initInfluencingBounds(bounds);
/*     */     }
/*     */     
/* 745 */     fogRetained.scopes = new Vector();
/* 746 */     fogRetained.isDirty = 65535;
/* 747 */     fogRetained.inImmCtx = false;
/* 748 */     fogRetained.region = null;
/* 749 */     fogRetained.sgFog = null;
/* 750 */     fogRetained.mirrorFog = null;
/* 751 */     fogRetained.environmentSets = new UnorderList(1, EnvironmentSet.class);
/* 752 */     return fogRetained;
/*     */   }
/*     */   
/*     */   void updateTransformChange() {
/* 756 */     super.updateTransformChange();
/* 757 */     setLocalToVworldScale(this.sgFog.getLastLocalToVworld().getDistanceScale());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateTransformChange() {
/* 763 */     if (this.boundingLeaf == null && 
/* 764 */       this.regionOfInfluence != null) {
/* 765 */       this.region = this.regionOfInfluence.copy(this.region);
/* 766 */       this.region.transform(this.regionOfInfluence, this.sgFog.getCurrentLocalToVworld());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 774 */     J3dMessage j3dMessage = new J3dMessage();
/* 775 */     j3dMessage.threads = 4224;
/* 776 */     j3dMessage.universe = this.universe;
/* 777 */     j3dMessage.type = 22;
/* 778 */     j3dMessage.args[0] = this;
/* 779 */     j3dMessage.args[1] = new Integer(paramInt);
/* 780 */     j3dMessage.args[2] = paramObject;
/* 781 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 785 */     super.mergeTransform(paramTransformGroupRetained);
/* 786 */     if (this.regionOfInfluence != null) {
/* 787 */       this.regionOfInfluence.transform(paramTransformGroupRetained.transform);
/*     */     }
/*     */   }
/*     */   
/* 791 */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) { paramArrayList.add(this.mirrorFog); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 798 */   protected void validateDistancesInEc(double paramDouble) { assert false : "subclasses should override this method"; }
/*     */ 
/*     */ 
/*     */   
/* 802 */   double getLocalToVworldScale() { return this.localToVworldScale; }
/*     */ 
/*     */ 
/*     */   
/* 806 */   void setLocalToVworldScale(double paramDouble) { this.localToVworldScale = paramDouble; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\FogRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */