/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Point3d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class Shape3DRetained
/*      */   extends LeafRetained
/*      */ {
/*      */   static final int GEOMETRY_CHANGED = 1;
/*      */   static final int APPEARANCE_CHANGED = 2;
/*      */   static final int COLLISION_CHANGED = 4;
/*      */   static final int BOUNDS_CHANGED = 8;
/*      */   static final int APPEARANCEOVERRIDE_CHANGED = 16;
/*      */   static final int LAST_DEFINED_BIT = 16;
/*      */   static final int targetThreads = 4224;
/*      */   AppearanceRetained appearance;
/*      */   ArrayList geometryList;
/*      */   private GeometryAtom geomAtom;
/*      */   private MRSWLock mirrorShape3DLock;
/*      */   ArrayList mirrorShape3D;
/*      */   NodeRetained sourceNode;
/*      */   HashKey key;
/*      */   boolean inImmCtx;
/*      */   int isDirty;
/*      */   LightRetained[] lights;
/*      */   int numlights;
/*      */   FogRetained[] fogs;
/*      */   
/*      */   Shape3DRetained() {
/*   41 */     this.appearance = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   46 */     this.geometryList = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   56 */     this.geomAtom = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   64 */     this.mirrorShape3DLock = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   73 */     this.mirrorShape3D = new ArrayList(1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   80 */     this.sourceNode = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   85 */     this.key = null;
/*      */ 
/*      */     
/*   88 */     this.inImmCtx = false;
/*      */ 
/*      */     
/*   91 */     this.isDirty = 65535;
/*      */ 
/*      */     
/*   94 */     this.lights = null;
/*      */ 
/*      */     
/*   97 */     this.numlights = 0;
/*      */ 
/*      */     
/*  100 */     this.fogs = null;
/*      */ 
/*      */     
/*  103 */     this.numfogs = 0;
/*      */ 
/*      */     
/*  106 */     this.modelClips = null;
/*      */ 
/*      */     
/*  109 */     this.numModelClips = 0;
/*      */ 
/*      */     
/*  112 */     this.altApps = null;
/*      */ 
/*      */     
/*  115 */     this.numAltApps = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  125 */     this.isPickable = true;
/*      */ 
/*      */ 
/*      */     
/*  129 */     this.isCollidable = true;
/*      */ 
/*      */     
/*  132 */     this.closestSwitchParent = null;
/*      */ 
/*      */     
/*  135 */     this.closestSwitchIndex = -1;
/*      */ 
/*      */     
/*  138 */     this.visible = true;
/*      */ 
/*      */     
/*  141 */     this.appearanceOverrideEnable = false;
/*      */ 
/*      */ 
/*      */     
/*  145 */     this.otherAppearance = null;
/*      */ 
/*      */     
/*  148 */     this.bounds = null;
/*      */ 
/*      */     
/*  151 */     this.vwcBounds = null;
/*      */ 
/*      */     
/*  154 */     this.collisionBound = null;
/*      */ 
/*      */     
/*  157 */     this.collisionVwcBound = null;
/*      */ 
/*      */     
/*  160 */     this.orderedPath = null;
/*      */ 
/*      */     
/*  163 */     this.viewList = null;
/*      */     
/*  165 */     this.changedFrequent = 0;
/*      */ 
/*      */ 
/*      */     
/*  169 */     this.nodeType = 11;
/*  170 */     this.numlights = 0;
/*  171 */     this.numfogs = 0;
/*  172 */     this.numModelClips = 0;
/*  173 */     this.numAltApps = 0;
/*  174 */     this.localBounds = new BoundingBox((BoundingBox)null);
/*      */     
/*  176 */     this.mirrorShape3DLock = new MRSWLock();
/*  177 */     this.geometryList = new ArrayList(1);
/*  178 */     this.geometryList.add(null);
/*      */   }
/*      */   int numfogs; ModelClipRetained[] modelClips; int numModelClips; AlternateAppearanceRetained[] altApps; int numAltApps; BranchGroupRetained[] branchGroupPath; boolean isPickable; boolean isCollidable; SwitchRetained closestSwitchParent; int closestSwitchIndex; boolean visible; boolean appearanceOverrideEnable; AppearanceRetained otherAppearance; Bounds bounds; BoundingBox vwcBounds; Bounds collisionBound; Bounds collisionVwcBound;
/*      */   OrderedPath orderedPath;
/*      */   ArrayList viewList;
/*      */   int changedFrequent;
/*      */   
/*      */   void setCollisionBounds(Bounds paramBounds) {
/*  186 */     if (paramBounds == null) {
/*  187 */       this.collisionBound = null;
/*      */     } else {
/*  189 */       this.collisionBound = (Bounds)paramBounds.clone();
/*      */     } 
/*      */     
/*  192 */     if (this.source.isLive()) {
/*      */       
/*  194 */       J3dMessage j3dMessage = new J3dMessage();
/*  195 */       j3dMessage.type = 34;
/*  196 */       j3dMessage.threads = 8192;
/*  197 */       j3dMessage.universe = this.universe;
/*  198 */       j3dMessage.args[0] = getGeomAtomsArray(this.mirrorShape3D);
/*      */       
/*  200 */       j3dMessage.args[1] = this.collisionBound;
/*  201 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */   
/*      */   Bounds getLocalBounds(Bounds paramBounds) {
/*  206 */     if (this.localBounds != null) {
/*  207 */       this.localBounds.set(paramBounds);
/*      */     } else {
/*      */       
/*  210 */       this.localBounds = new BoundingBox(paramBounds);
/*      */     } 
/*  212 */     return this.localBounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBounds(Bounds paramBounds) {
/*  221 */     super.setBounds(paramBounds);
/*      */     
/*  223 */     if (this.source.isLive() && !this.boundsAutoCompute) {
/*  224 */       J3dMessage j3dMessage = new J3dMessage();
/*  225 */       j3dMessage.type = 35;
/*  226 */       j3dMessage.threads = 8384;
/*      */ 
/*      */ 
/*      */       
/*  230 */       j3dMessage.universe = this.universe;
/*  231 */       j3dMessage.args[0] = getGeomAtomsArray(this.mirrorShape3D);
/*      */       
/*  233 */       j3dMessage.args[1] = this.localBounds;
/*  234 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  243 */   Bounds getCollisionBounds(int paramInt) { return (this.collisionBound == null) ? null : (Bounds)this.collisionBound.clone(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addGeometry(Geometry paramGeometry) {
/*  264 */     GeometryRetained geometryRetained = null;
/*      */     
/*  266 */     checkEquivalenceClass(paramGeometry, -1);
/*      */     
/*  268 */     if (((Shape3D)this.source).isLive()) {
/*  269 */       if (paramGeometry != null) {
/*      */         
/*  271 */         geometryRetained = (GeometryRetained)paramGeometry.retained;
/*  272 */         geometryRetained.setLive(this.inBackgroundGroup, this.refCount);
/*      */         
/*  274 */         this.geometryList.add(geometryRetained);
/*      */       } else {
/*      */         
/*  277 */         this.geometryList.add(null);
/*  278 */         geometryRetained = null;
/*      */       } 
/*  280 */       sendDataChangedMessage(geometryRetained);
/*      */     
/*      */     }
/*  283 */     else if (paramGeometry != null) {
/*  284 */       this.geometryList.add((GeometryRetained)paramGeometry.retained);
/*      */     } else {
/*  286 */       this.geometryList.add(null);
/*      */     } 
/*      */     
/*  289 */     dirtyBoundsCache();
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
/*      */   void setGeometry(Geometry paramGeometry, int paramInt) {
/*  312 */     GeometryRetained geometryRetained1 = null;
/*  313 */     GeometryRetained geometryRetained2 = null;
/*      */     
/*  315 */     checkEquivalenceClass(paramGeometry, paramInt);
/*      */     
/*  317 */     if (((Shape3D)this.source).isLive()) {
/*      */       
/*  319 */       geometryRetained2 = (GeometryRetained)this.geometryList.get(paramInt);
/*  320 */       if (geometryRetained2 != null) {
/*  321 */         geometryRetained2.clearLive(this.refCount);
/*  322 */         for (byte b = 0; b < this.mirrorShape3D.size(); b++) {
/*  323 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b);
/*  324 */           geometryRetained2.removeUser(shape3DRetained);
/*      */         } 
/*  326 */         geometryRetained2.decRefCnt();
/*      */       } 
/*      */       
/*  329 */       if (paramGeometry != null) {
/*  330 */         geometryRetained1 = (GeometryRetained)paramGeometry.retained;
/*  331 */         geometryRetained1.incRefCnt();
/*  332 */         geometryRetained1.setLive(this.inBackgroundGroup, this.refCount);
/*  333 */         this.geometryList.set(paramInt, geometryRetained1);
/*  334 */         sendDataChangedMessage(geometryRetained1);
/*      */       } else {
/*  336 */         this.geometryList.set(paramInt, null);
/*  337 */         sendDataChangedMessage(null);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  342 */       geometryRetained2 = (GeometryRetained)this.geometryList.get(paramInt);
/*  343 */       if (geometryRetained2 != null) {
/*  344 */         geometryRetained2.decRefCnt();
/*      */       }
/*  346 */       if (paramGeometry != null) {
/*  347 */         this.geometryList.set(paramInt, (GeometryRetained)paramGeometry.retained);
/*  348 */         ((GeometryRetained)paramGeometry.retained).incRefCnt();
/*      */       } else {
/*  350 */         this.geometryList.set(paramInt, null);
/*      */       } 
/*      */     } 
/*  353 */     dirtyBoundsCache();
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
/*      */   void insertGeometry(Geometry paramGeometry, int paramInt) {
/*  372 */     GeometryRetained geometryRetained = null;
/*  373 */     Object object = null;
/*      */     
/*  375 */     checkEquivalenceClass(paramGeometry, -1);
/*      */     
/*  377 */     if (((Shape3D)this.source).isLive()) {
/*      */       
/*  379 */       if (paramGeometry != null) {
/*      */ 
/*      */         
/*  382 */         geometryRetained = (GeometryRetained)paramGeometry.retained;
/*  383 */         geometryRetained.incRefCnt();
/*  384 */         this.geometryList.add(paramInt, geometryRetained);
/*  385 */         geometryRetained.setLive(this.inBackgroundGroup, this.refCount);
/*  386 */         sendDataChangedMessage(geometryRetained);
/*      */       } else {
/*  388 */         this.geometryList.add(paramInt, null);
/*  389 */         sendDataChangedMessage(null);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  394 */     else if (paramGeometry != null) {
/*  395 */       this.geometryList.add(paramInt, (GeometryRetained)paramGeometry.retained);
/*  396 */       ((GeometryRetained)paramGeometry.retained).incRefCnt();
/*      */     } else {
/*  398 */       this.geometryList.add(paramInt, null);
/*      */     } 
/*      */     
/*  401 */     dirtyBoundsCache();
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
/*      */   void removeGeometry(int paramInt) {
/*  414 */     GeometryRetained geometryRetained = null;
/*      */     
/*  416 */     if (((Shape3D)this.source).isLive()) {
/*      */       
/*  418 */       geometryRetained = (GeometryRetained)this.geometryList.get(paramInt);
/*  419 */       if (geometryRetained != null) {
/*  420 */         geometryRetained.clearLive(this.refCount);
/*  421 */         geometryRetained.decRefCnt();
/*  422 */         for (byte b = 0; b < this.mirrorShape3D.size(); b++) {
/*  423 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b);
/*  424 */           geometryRetained.removeUser(shape3DRetained);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  429 */       this.geometryList.remove(paramInt);
/*  430 */       sendDataChangedMessage(null);
/*      */     } else {
/*      */       
/*  433 */       geometryRetained = (GeometryRetained)this.geometryList.get(paramInt);
/*  434 */       if (geometryRetained != null) {
/*  435 */         geometryRetained.decRefCnt();
/*      */       }
/*  437 */       this.geometryList.remove(paramInt);
/*      */     } 
/*      */     
/*  440 */     dirtyBoundsCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Geometry getGeometry(int paramInt1, int paramInt2) {
/*  451 */     GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(paramInt1);
/*  452 */     return (geometryRetained == null) ? null : (Geometry)geometryRetained.source;
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
/*      */   Enumeration getAllGeometries(int paramInt) {
/*  465 */     GeometryRetained geometryRetained = null;
/*  466 */     Vector vector = new Vector(this.geometryList.size());
/*      */     
/*  468 */     for (byte b = 0; b < this.geometryList.size(); b++) {
/*  469 */       geometryRetained = (GeometryRetained)this.geometryList.get(b);
/*  470 */       if (geometryRetained != null) {
/*  471 */         vector.add((Geometry)geometryRetained.source);
/*      */       } else {
/*  473 */         vector.add(null);
/*      */       } 
/*      */     } 
/*  476 */     return vector.elements();
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
/*  489 */   int numGeometries(int paramInt) { return this.geometryList.size(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAppearance(Appearance paramAppearance) {
/*  499 */     boolean bool = false;
/*      */     
/*  501 */     if (((Shape3D)this.source).isLive()) {
/*  502 */       if (this.appearance != null) {
/*  503 */         this.appearance.clearLive(this.refCount);
/*  504 */         for (byte b1 = 0; b1 < this.mirrorShape3D.size(); b1++) {
/*  505 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b1);
/*  506 */           this.appearance.removeAMirrorUser(shape3DRetained);
/*      */         } 
/*      */       } 
/*      */       
/*  510 */       if (paramAppearance != null) {
/*  511 */         ((AppearanceRetained)paramAppearance.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  512 */         this.appearance = (AppearanceRetained)paramAppearance.retained;
/*  513 */         for (byte b1 = 0; b1 < this.mirrorShape3D.size(); b1++) {
/*  514 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b1);
/*  515 */           this.appearance.addAMirrorUser(shape3DRetained);
/*      */         } 
/*  517 */         if (this.appearance.renderingAttributes != null && this.visible != this.appearance.renderingAttributes.visible)
/*      */         {
/*  519 */           this.visible = this.appearance.renderingAttributes.visible;
/*  520 */           bool = true;
/*      */         }
/*      */       
/*      */       }
/*  524 */       else if (!this.visible) {
/*  525 */         this.visible = true;
/*  526 */         bool = true;
/*      */       } 
/*      */       
/*  529 */       byte b = 0;
/*  530 */       if (bool) {
/*  531 */         b = 2;
/*      */       } else {
/*  533 */         b = 1;
/*  534 */       }  J3dMessage[] arrayOfJ3dMessage = new J3dMessage[b];
/*      */       
/*  536 */       arrayOfJ3dMessage[0] = new J3dMessage();
/*  537 */       (arrayOfJ3dMessage[0]).threads = 4224;
/*  538 */       (arrayOfJ3dMessage[0]).type = 24;
/*  539 */       (arrayOfJ3dMessage[0]).universe = this.universe;
/*  540 */       (arrayOfJ3dMessage[0]).args[0] = this;
/*  541 */       (arrayOfJ3dMessage[0]).args[1] = new Integer(2);
/*  542 */       Shape3DRetained[] arrayOfShape3DRetained = new Shape3DRetained[this.mirrorShape3D.size()];
/*  543 */       this.mirrorShape3D.toArray(arrayOfShape3DRetained);
/*  544 */       (arrayOfJ3dMessage[0]).args[2] = arrayOfShape3DRetained;
/*  545 */       Object[] arrayOfObject = new Object[2];
/*  546 */       if (paramAppearance == null) {
/*  547 */         arrayOfObject[0] = null;
/*      */       } else {
/*      */         
/*  550 */         arrayOfObject[0] = this.appearance.mirror;
/*      */       } 
/*  552 */       arrayOfObject[1] = new Integer(this.changedFrequent);
/*  553 */       (arrayOfJ3dMessage[0]).args[3] = arrayOfObject;
/*  554 */       (arrayOfJ3dMessage[0]).args[4] = getGeomAtomsArray(this.mirrorShape3D);
/*  555 */       if (bool) {
/*  556 */         arrayOfJ3dMessage[1] = new J3dMessage();
/*  557 */         (arrayOfJ3dMessage[1]).threads = 64;
/*  558 */         (arrayOfJ3dMessage[1]).type = 24;
/*  559 */         (arrayOfJ3dMessage[1]).universe = this.universe;
/*  560 */         (arrayOfJ3dMessage[1]).args[0] = this;
/*  561 */         (arrayOfJ3dMessage[1]).args[1] = new Integer(2);
/*  562 */         (arrayOfJ3dMessage[1]).args[2] = this.visible ? Boolean.TRUE : Boolean.FALSE;
/*  563 */         (arrayOfJ3dMessage[1]).args[3] = (arrayOfJ3dMessage[0]).args[4];
/*      */       } 
/*  565 */       VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*      */ 
/*      */     
/*      */     }
/*  569 */     else if (paramAppearance == null) {
/*  570 */       this.appearance = null;
/*      */     } else {
/*  572 */       this.appearance = (AppearanceRetained)paramAppearance.retained;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  582 */   Appearance getAppearance() { return (this.appearance == null) ? null : (Appearance)this.appearance.source; }
/*      */ 
/*      */   
/*      */   void setAppearanceOverrideEnable(boolean paramBoolean) {
/*  586 */     if (((Shape3D)this.source).isLive()) {
/*      */ 
/*      */       
/*  589 */       J3dMessage j3dMessage = new J3dMessage();
/*  590 */       j3dMessage.threads = 4224;
/*  591 */       j3dMessage.type = 24;
/*  592 */       j3dMessage.universe = this.universe;
/*  593 */       j3dMessage.args[0] = this;
/*  594 */       j3dMessage.args[1] = new Integer(16);
/*  595 */       Shape3DRetained[] arrayOfShape3DRetained = new Shape3DRetained[this.mirrorShape3D.size()];
/*  596 */       this.mirrorShape3D.toArray(arrayOfShape3DRetained);
/*  597 */       j3dMessage.args[2] = arrayOfShape3DRetained;
/*  598 */       Object[] arrayOfObject = new Object[2];
/*  599 */       if (paramBoolean) {
/*  600 */         arrayOfObject[0] = Boolean.TRUE;
/*      */       } else {
/*      */         
/*  603 */         arrayOfObject[0] = Boolean.FALSE;
/*      */       } 
/*  605 */       arrayOfObject[1] = new Integer(this.changedFrequent);
/*  606 */       j3dMessage.args[3] = arrayOfObject;
/*  607 */       j3dMessage.args[4] = getGeomAtomsArray(this.mirrorShape3D);
/*      */       
/*  609 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*  611 */     this.appearanceOverrideEnable = paramBoolean;
/*      */   }
/*      */ 
/*      */   
/*  615 */   boolean getAppearanceOverrideEnable() { return this.appearanceOverrideEnable; }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersect(PickInfo paramPickInfo, PickShape paramPickShape, int paramInt) {
/*  620 */     Transform3D transform3D1 = paramPickInfo.getLocalToVWorldRef();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  625 */     if (this instanceof OrientedShape3DRetained) {
/*  626 */       Transform3D transform3D = ((OrientedShape3DRetained)this).getOrientedTransform(getPrimaryViewIdx());
/*      */       
/*  628 */       transform3D1.mul(transform3D);
/*      */     } 
/*      */     
/*  631 */     Transform3D transform3D2 = new Transform3D();
/*  632 */     transform3D2.invert(transform3D1);
/*  633 */     PickShape pickShape = paramPickShape.transform(transform3D2);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  638 */     int i = this.geometryList.size();
/*      */ 
/*      */     
/*  641 */     if ((paramInt & 0x8) == 0 && (paramInt & 0x10) == 0 && (paramInt & 0x20) == 0 && (paramInt & 0x40) == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  646 */       for (byte b = 0; b < i; b++) {
/*  647 */         GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/*  648 */         if (geometryRetained != null) {
/*  649 */           if (geometryRetained.mirrorGeometry != null) {
/*  650 */             geometryRetained = geometryRetained.mirrorGeometry;
/*      */           }
/*  652 */           if (geometryRetained.intersect(pickShape, null, 0, null, null, 0)) {
/*  653 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  659 */       double d = Double.POSITIVE_INFINITY;
/*  660 */       Point3d point3d1 = new Point3d();
/*  661 */       Point3d point3d2 = new Point3d();
/*  662 */       Point3d point3d3 = new Point3d();
/*      */       
/*  664 */       for (byte b = 0; b < i; b++) {
/*  665 */         GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/*  666 */         if (geometryRetained != null) {
/*  667 */           if (geometryRetained.mirrorGeometry != null) {
/*  668 */             geometryRetained = geometryRetained.mirrorGeometry;
/*      */           }
/*      */           
/*  671 */           if (geometryRetained.intersect(pickShape, paramPickInfo, paramInt, point3d2, geometryRetained, b)) {
/*      */             
/*  673 */             point3d3.set(point3d2);
/*  674 */             transform3D1.transform(point3d3);
/*  675 */             double d1 = paramPickShape.distance(point3d3);
/*      */             
/*  677 */             if (d > d1) {
/*  678 */               d = d1;
/*  679 */               point3d1.set(point3d2);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  685 */       if (d < Double.POSITIVE_INFINITY) {
/*  686 */         if ((paramInt & 0x10) != 0) {
/*  687 */           paramPickInfo.setClosestDistance(d);
/*      */         }
/*  689 */         if ((paramInt & 0x8) != 0) {
/*  690 */           paramPickInfo.setClosestIntersectionPoint(point3d1);
/*      */         }
/*  692 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/*  696 */     return false;
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
/*      */   boolean intersect(SceneGraphPath paramSceneGraphPath, PickShape paramPickShape, double[] paramArrayOfDouble) {
/*  714 */     PickInfo pickInfo = new PickInfo();
/*      */     
/*  716 */     Transform3D transform3D = paramSceneGraphPath.getTransform();
/*  717 */     if (transform3D == null) {
/*  718 */       throw new IllegalArgumentException(J3dI18N.getString("Shape3DRetained3"));
/*      */     }
/*  720 */     pickInfo.setLocalToVWorldRef(transform3D);
/*      */     
/*  722 */     if (paramArrayOfDouble == null)
/*      */     {
/*  724 */       return intersect(pickInfo, paramPickShape, 0);
/*      */     }
/*      */     
/*  727 */     byte b = 16;
/*  728 */     if (intersect(pickInfo, paramPickShape, b)) {
/*  729 */       paramArrayOfDouble[0] = pickInfo.getClosestDistance();
/*  730 */       return true;
/*      */     } 
/*      */     
/*  733 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  741 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  748 */   boolean getInImmCtx() { return this.inImmCtx; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initMirrorShape3D(SetLiveState paramSetLiveState, Shape3DRetained paramShape3DRetained, int paramInt) {
/*  759 */     paramShape3DRetained.inBackgroundGroup = this.inBackgroundGroup;
/*  760 */     paramShape3DRetained.geometryBackground = this.geometryBackground;
/*  761 */     paramShape3DRetained.source = this.source;
/*  762 */     paramShape3DRetained.universe = this.universe;
/*      */     
/*  764 */     paramShape3DRetained.inSharedGroup = false;
/*  765 */     paramShape3DRetained.locale = this.locale;
/*  766 */     paramShape3DRetained.parent = this.parent;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  771 */     paramShape3DRetained.boundsAutoCompute = this.boundsAutoCompute;
/*  772 */     paramShape3DRetained.localBounds = this.localBounds;
/*      */ 
/*      */     
/*  775 */     OrderedPath orderedPath1 = (OrderedPath)paramSetLiveState.orderedPaths.get(paramInt);
/*  776 */     if (orderedPath1.pathElements.size() == 0) {
/*  777 */       paramShape3DRetained.orderedPath = null;
/*      */     } else {
/*  779 */       paramShape3DRetained.orderedPath = orderedPath1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  788 */     paramShape3DRetained.staticTransform = this.staticTransform;
/*      */ 
/*      */     
/*  791 */     paramShape3DRetained.appearanceOverrideEnable = this.appearanceOverrideEnable;
/*      */     
/*  793 */     paramShape3DRetained.geometryList = this.geometryList;
/*      */ 
/*      */     
/*  796 */     paramShape3DRetained.sourceNode = this;
/*      */     
/*  798 */     if (this instanceof OrientedShape3DRetained) {
/*  799 */       OrientedShape3DRetained orientedShape3DRetained1 = (OrientedShape3DRetained)this;
/*  800 */       OrientedShape3DRetained orientedShape3DRetained2 = (OrientedShape3DRetained)paramShape3DRetained;
/*  801 */       orientedShape3DRetained2.initAlignmentMode(orientedShape3DRetained1.mode);
/*  802 */       orientedShape3DRetained2.initAlignmentAxis(orientedShape3DRetained1.axis);
/*  803 */       orientedShape3DRetained2.initRotationPoint(orientedShape3DRetained1.rotationPoint);
/*  804 */       orientedShape3DRetained2.initConstantScaleEnable(orientedShape3DRetained1.constantScale);
/*  805 */       orientedShape3DRetained2.initScale(orientedShape3DRetained1.scaleFactor);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/*  811 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */ 
/*      */     
/*  814 */     Shape3DRetained[] arrayOfShape3DRetained = (Shape3DRetained[])paramArrayOfObject[2];
/*      */     
/*  816 */     if ((i & 0x2) != 0) {
/*  817 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[3];
/*  818 */       int k = ((Integer)arrayOfObject[1]).intValue();
/*  819 */       for (int j = arrayOfShape3DRetained.length - 1; j >= 0; j--) {
/*  820 */         (arrayOfShape3DRetained[j]).appearance = (AppearanceRetained)arrayOfObject[0];
/*  821 */         (arrayOfShape3DRetained[j]).changedFrequent = k;
/*      */       } 
/*      */     } 
/*  824 */     if ((i & 0x10) != 0) {
/*  825 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[3];
/*  826 */       int k = ((Integer)arrayOfObject[1]).intValue();
/*  827 */       for (int j = arrayOfShape3DRetained.length - 1; j >= 0; j--) {
/*  828 */         (arrayOfShape3DRetained[j]).appearanceOverrideEnable = ((Boolean)arrayOfObject[0]).booleanValue();
/*  829 */         (arrayOfShape3DRetained[j]).changedFrequent = k;
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
/*      */   Bounds getBounds() {
/*  841 */     if (this.boundsAutoCompute) {
/*      */       
/*  843 */       if (this.cachedBounds != null) {
/*  844 */         return (Bounds)this.cachedBounds.clone();
/*      */       }
/*      */       
/*  847 */       if (this.geometryList != null) {
/*  848 */         BoundingBox boundingBox = new BoundingBox((Bounds)null);
/*      */         
/*  850 */         for (byte b = 0; b < this.geometryList.size(); b++) {
/*  851 */           GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/*  852 */           if (geometryRetained != null && geometryRetained.geoType != -1) {
/*      */             
/*  854 */             geometryRetained.computeBoundingBox();
/*  855 */             synchronized (geometryRetained.geoBounds) {
/*  856 */               boundingBox.combine(geometryRetained.geoBounds);
/*      */             } 
/*      */           } 
/*      */         } 
/*  860 */         return boundingBox;
/*      */       } 
/*      */       
/*  863 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  867 */     return super.getBounds();
/*      */   }
/*      */ 
/*      */   
/*      */   Bounds getEffectiveBounds() {
/*  872 */     if (this.boundsAutoCompute) {
/*  873 */       return getBounds();
/*      */     }
/*      */     
/*  876 */     return super.getEffectiveBounds();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void computeCombineBounds(Bounds paramBounds) {
/*  887 */     if (this.boundsAutoCompute) {
/*  888 */       if (this.geometryList != null) {
/*      */         
/*  890 */         BoundingBox boundingBox = null;
/*      */         
/*  892 */         if (this.staticTransform != null) {
/*  893 */           boundingBox = new BoundingBox((BoundingBox)null);
/*      */         }
/*      */         
/*  896 */         if (!VirtualUniverse.mc.cacheAutoComputedBounds) {
/*  897 */           for (byte b = 0; b < this.geometryList.size(); b++) {
/*  898 */             GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/*  899 */             if (geometryRetained != null && geometryRetained.geoType != -1) {
/*      */               
/*  901 */               geometryRetained.computeBoundingBox();
/*      */               
/*  903 */               synchronized (geometryRetained.geoBounds) {
/*  904 */                 if (this.staticTransform != null) {
/*  905 */                   boundingBox.set(geometryRetained.geoBounds);
/*  906 */                   boundingBox.transform(this.staticTransform.transform);
/*  907 */                   paramBounds.combine(boundingBox);
/*      */                 } else {
/*  909 */                   paramBounds.combine(geometryRetained.geoBounds);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } else {
/*  915 */           if (this.cachedBounds == null) {
/*  916 */             this.cachedBounds = new BoundingBox((BoundingBox)null);
/*      */             
/*  918 */             for (byte b = 0; b < this.geometryList.size(); b++) {
/*  919 */               GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/*  920 */               if (geometryRetained != null && geometryRetained.geoType != -1) {
/*      */                 
/*  922 */                 geometryRetained.computeBoundingBox();
/*      */                 
/*  924 */                 synchronized (geometryRetained.geoBounds) {
/*  925 */                   if (this.staticTransform != null) {
/*  926 */                     boundingBox.set(geometryRetained.geoBounds);
/*  927 */                     boundingBox.transform(this.staticTransform.transform);
/*  928 */                     this.cachedBounds.combine(boundingBox);
/*      */                   } else {
/*  930 */                     this.cachedBounds.combine(geometryRetained.geoBounds);
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*  936 */           paramBounds.combine(this.cachedBounds);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  942 */       synchronized (this.localBounds) {
/*  943 */         paramBounds.combine(this.localBounds);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(SetLiveState paramSetLiveState) {
/*  953 */     doSetLive(paramSetLiveState);
/*  954 */     markAsLive();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doSetLive(SetLiveState paramSetLiveState) {
/*  962 */     ArrayList arrayList = new ArrayList();
/*      */     
/*  964 */     super.doSetLive(paramSetLiveState);
/*      */     
/*  966 */     this.nodeId = this.universe.getNodeId();
/*      */ 
/*      */     
/*  969 */     if (this.inSharedGroup) {
/*  970 */       for (byte b1 = 0; b1 < paramSetLiveState.keys.length; b1++) {
/*  971 */         Shape3DRetained shape3DRetained; if (this instanceof OrientedShape3DRetained) {
/*  972 */           shape3DRetained = new OrientedShape3DRetained();
/*      */         } else {
/*  974 */           shape3DRetained = new Shape3DRetained();
/*      */         } 
/*  976 */         shape3DRetained.key = paramSetLiveState.keys[b1];
/*  977 */         shape3DRetained.localToVworld = new Transform3D[1][];
/*  978 */         shape3DRetained.localToVworldIndex = new int[1][];
/*      */         
/*  980 */         int i = paramSetLiveState.keys[b1].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  988 */         shape3DRetained.localToVworld[0] = this.localToVworld[i];
/*  989 */         shape3DRetained.localToVworldIndex[0] = this.localToVworldIndex[i];
/*  990 */         shape3DRetained.branchGroupPath = (BranchGroupRetained[])this.branchGroupPaths.get(i);
/*  991 */         shape3DRetained.isPickable = paramSetLiveState.pickable[b1];
/*  992 */         shape3DRetained.isCollidable = paramSetLiveState.collidable[b1];
/*      */         
/*  994 */         initMirrorShape3D(paramSetLiveState, shape3DRetained, i);
/*      */         
/*  996 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b1] != null) {
/*      */           
/*  998 */           paramSetLiveState.switchTargets[b1].addNode(shape3DRetained, 0);
/*  999 */           shape3DRetained.closestSwitchParent = paramSetLiveState.closestSwitchParents[b1];
/* 1000 */           shape3DRetained.closestSwitchIndex = paramSetLiveState.closestSwitchIndices[b1];
/*      */         } 
/* 1002 */         shape3DRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(i);
/*      */ 
/*      */ 
/*      */         
/* 1006 */         if (paramSetLiveState.lights != null) {
/* 1007 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.lights.get(i);
/* 1008 */           if (arrayList1 != null) {
/* 1009 */             for (byte b2 = 0; b2 < arrayList1.size(); b2++) {
/* 1010 */               shape3DRetained.addLight((LightRetained)arrayList1.get(b2));
/*      */             }
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1016 */         if (paramSetLiveState.fogs != null) {
/* 1017 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.fogs.get(i);
/* 1018 */           if (arrayList1 != null) {
/* 1019 */             for (byte b2 = 0; b2 < arrayList1.size(); b2++) {
/* 1020 */               shape3DRetained.addFog((FogRetained)arrayList1.get(b2));
/*      */             }
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1026 */         if (paramSetLiveState.modelClips != null) {
/* 1027 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.modelClips.get(i);
/* 1028 */           if (arrayList1 != null) {
/* 1029 */             for (byte b2 = 0; b2 < arrayList1.size(); b2++) {
/* 1030 */               shape3DRetained.addModelClip((ModelClipRetained)arrayList1.get(b2));
/*      */             }
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1036 */         if (paramSetLiveState.altAppearances != null) {
/* 1037 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.altAppearances.get(i);
/* 1038 */           if (arrayList1 != null) {
/* 1039 */             for (byte b2 = 0; b2 < arrayList1.size(); b2++) {
/* 1040 */               shape3DRetained.addAltApp((AlternateAppearanceRetained)arrayList1.get(b2));
/*      */             }
/*      */           }
/*      */         } 
/* 1044 */         synchronized (this.mirrorShape3D) {
/* 1045 */           this.mirrorShape3D.add(i, shape3DRetained);
/*      */         } 
/*      */         
/* 1048 */         arrayList.add(shape3DRetained);
/* 1049 */         if (paramSetLiveState.viewLists != null) {
/* 1050 */           shape3DRetained.viewList = (ArrayList)paramSetLiveState.viewLists.get(i);
/*      */         } else {
/* 1052 */           shape3DRetained.viewList = null;
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1056 */       Shape3DRetained shape3DRetained; if (this instanceof OrientedShape3DRetained) {
/* 1057 */         shape3DRetained = new OrientedShape3DRetained();
/*      */       } else {
/* 1059 */         shape3DRetained = new Shape3DRetained();
/*      */       } 
/*      */       
/* 1062 */       shape3DRetained.localToVworld = new Transform3D[1][];
/* 1063 */       shape3DRetained.localToVworldIndex = new int[1][];
/* 1064 */       shape3DRetained.localToVworld[0] = this.localToVworld[0];
/* 1065 */       shape3DRetained.localToVworldIndex[0] = this.localToVworldIndex[0];
/* 1066 */       shape3DRetained.branchGroupPath = (BranchGroupRetained[])this.branchGroupPaths.get(0);
/* 1067 */       shape3DRetained.isPickable = paramSetLiveState.pickable[0];
/* 1068 */       shape3DRetained.isCollidable = paramSetLiveState.collidable[0];
/* 1069 */       initMirrorShape3D(paramSetLiveState, shape3DRetained, 0);
/*      */ 
/*      */       
/* 1072 */       if (paramSetLiveState.lights != null) {
/* 1073 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.lights.get(0);
/* 1074 */         for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1075 */           shape3DRetained.addLight((LightRetained)arrayList1.get(b1));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1080 */       if (paramSetLiveState.fogs != null) {
/* 1081 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.fogs.get(0);
/* 1082 */         for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1083 */           shape3DRetained.addFog((FogRetained)arrayList1.get(b1));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1088 */       if (paramSetLiveState.modelClips != null) {
/* 1089 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.modelClips.get(0);
/* 1090 */         for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1091 */           shape3DRetained.addModelClip((ModelClipRetained)arrayList1.get(b1));
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1097 */       if (paramSetLiveState.altAppearances != null) {
/* 1098 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.altAppearances.get(0);
/* 1099 */         for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1100 */           shape3DRetained.addAltApp((AlternateAppearanceRetained)arrayList1.get(b1));
/*      */         }
/*      */       } 
/* 1103 */       synchronized (this.mirrorShape3D) {
/* 1104 */         this.mirrorShape3D.add(shape3DRetained);
/*      */       } 
/*      */       
/* 1107 */       arrayList.add(shape3DRetained);
/* 1108 */       if (paramSetLiveState.viewLists != null) {
/* 1109 */         shape3DRetained.viewList = (ArrayList)paramSetLiveState.viewLists.get(0);
/*      */       } else {
/* 1111 */         shape3DRetained.viewList = null;
/*      */       } 
/* 1113 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null) {
/*      */         
/* 1115 */         paramSetLiveState.switchTargets[0].addNode(shape3DRetained, 0);
/* 1116 */         shape3DRetained.closestSwitchParent = paramSetLiveState.closestSwitchParents[0];
/* 1117 */         shape3DRetained.closestSwitchIndex = paramSetLiveState.closestSwitchIndices[0];
/*      */       } 
/* 1119 */       shape3DRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*      */     } 
/*      */     
/* 1122 */     for (byte b = 0; b < arrayList.size(); b++) {
/* 1123 */       Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList.get(b);
/*      */       
/* 1125 */       if (this.appearance != null) {
/* 1126 */         synchronized (this.appearance.liveStateLock) {
/* 1127 */           if (b == 0) {
/* 1128 */             this.appearance.setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/* 1129 */             this.appearance.initMirrorObject();
/* 1130 */             if (this.appearance.renderingAttributes != null)
/* 1131 */               this.visible = this.appearance.renderingAttributes.visible; 
/*      */           } 
/* 1133 */           shape3DRetained.appearance = (AppearanceRetained)this.appearance.mirror;
/* 1134 */           this.appearance.addAMirrorUser(shape3DRetained);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1139 */         shape3DRetained.appearance = null;
/*      */       } 
/*      */       
/* 1142 */       if (this.geometryList != null) {
/* 1143 */         for (byte b1 = 0; b1 < this.geometryList.size(); b1++) {
/* 1144 */           GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b1);
/* 1145 */           if (geometryRetained != null) {
/* 1146 */             synchronized (geometryRetained.liveStateLock) {
/* 1147 */               if (b == 0) {
/* 1148 */                 geometryRetained.setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/*      */               }
/* 1150 */               geometryRetained.addUser(shape3DRetained);
/*      */             } 
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1158 */       if (b == 0 && this.boundsAutoCompute) {
/*      */         
/* 1160 */         if (!(this.localBounds instanceof BoundingBox)) {
/* 1161 */           this.localBounds = new BoundingBox((BoundingBox)null);
/*      */         }
/* 1163 */         getCombineBounds((BoundingBox)this.localBounds);
/*      */       } 
/*      */ 
/*      */       
/* 1167 */       initializeGAtom(shape3DRetained);
/*      */       
/* 1169 */       GeometryAtom geometryAtom = getGeomAtom(shape3DRetained);
/*      */ 
/*      */       
/* 1172 */       paramSetLiveState.nodeList.add(geometryAtom);
/*      */       
/* 1174 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null)
/*      */       {
/*      */ 
/*      */         
/* 1178 */         paramSetLiveState.transformTargets[b].addNode(geometryAtom, 0);
/*      */       }
/*      */     } 
/*      */     
/* 1182 */     paramSetLiveState.notifyThreads |= 0x30C0;
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
/*      */   void clearMirrorShape() {
/* 1198 */     this.source = null;
/* 1199 */     this.sourceNode = null;
/* 1200 */     this.parent = null;
/*      */     
/* 1202 */     if (this.otherAppearance != null) {
/* 1203 */       this.otherAppearance.sgApp.removeAMirrorUser(this);
/* 1204 */       this.otherAppearance = null;
/*      */     } 
/*      */     
/* 1207 */     this.appearance = null;
/*      */     
/* 1209 */     this.branchGroupPath = null;
/* 1210 */     this.isPickable = true;
/* 1211 */     this.isCollidable = true;
/* 1212 */     this.branchGroupPath = null;
/*      */ 
/*      */ 
/*      */     
/* 1216 */     this.geometryList = null;
/*      */     
/*      */     byte b;
/*      */     
/* 1220 */     for (b = 0; b < this.numfogs; b++)
/* 1221 */       this.fogs[b] = null; 
/* 1222 */     this.numfogs = 0;
/*      */ 
/*      */     
/* 1225 */     for (b = 0; b < this.numModelClips; b++)
/* 1226 */       this.modelClips[b] = null; 
/* 1227 */     this.numModelClips = 0;
/*      */ 
/*      */     
/* 1230 */     for (b = 0; b < this.numlights; b++)
/* 1231 */       this.lights[b] = null; 
/* 1232 */     this.numlights = 0;
/*      */ 
/*      */     
/* 1235 */     for (b = 0; b < this.numAltApps; b++)
/* 1236 */       this.altApps[b] = null; 
/* 1237 */     this.numAltApps = 0;
/*      */     
/* 1239 */     this.viewList = null;
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
/*      */   void clearLive(SetLiveState paramSetLiveState) {
/* 1254 */     ArrayList arrayList = new ArrayList();
/*      */     
/* 1256 */     super.clearLive(paramSetLiveState);
/*      */ 
/*      */ 
/*      */     
/* 1260 */     if (this.inSharedGroup) {
/* 1261 */       synchronized (this.mirrorShape3D) {
/* 1262 */         Object[] arrayOfObject = this.mirrorShape3D.toArray();
/* 1263 */         for (byte b1 = 0; b1 < paramSetLiveState.keys.length; b1++) {
/* 1264 */           for (byte b2 = 0; b2 < arrayOfObject.length; b2++) {
/* 1265 */             Shape3DRetained shape3DRetained = (Shape3DRetained)arrayOfObject[b2];
/* 1266 */             if (shape3DRetained.key.equals(paramSetLiveState.keys[b1])) {
/* 1267 */               this.mirrorShape3D.remove(this.mirrorShape3D.indexOf(shape3DRetained));
/* 1268 */               if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b1] != null)
/*      */               {
/* 1270 */                 paramSetLiveState.switchTargets[b1].addNode(shape3DRetained, 0);
/*      */               }
/*      */               
/* 1273 */               arrayList.add(shape3DRetained);
/* 1274 */               GeometryAtom geometryAtom = getGeomAtom(shape3DRetained);
/*      */ 
/*      */               
/* 1277 */               paramSetLiveState.nodeList.add(geometryAtom);
/* 1278 */               if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b1] != null)
/*      */               {
/* 1280 */                 paramSetLiveState.transformTargets[b1].addNode(geometryAtom, 0);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 1288 */       Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/* 1289 */       synchronized (this.mirrorShape3D) {
/* 1290 */         this.mirrorShape3D.remove(0);
/*      */       } 
/*      */       
/* 1293 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */       {
/* 1295 */         paramSetLiveState.switchTargets[0].addNode(shape3DRetained, 0);
/*      */       }
/*      */ 
/*      */       
/* 1299 */       arrayList.add(shape3DRetained);
/*      */       
/* 1301 */       GeometryAtom geometryAtom = getGeomAtom(shape3DRetained);
/*      */ 
/*      */       
/* 1304 */       paramSetLiveState.nodeList.add(geometryAtom);
/* 1305 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null)
/*      */       {
/* 1307 */         paramSetLiveState.transformTargets[0].addNode(geometryAtom, 0);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1312 */     for (byte b = 0; b < arrayList.size(); b++) {
/* 1313 */       Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList.get(b);
/* 1314 */       if (this.appearance != null) {
/* 1315 */         synchronized (this.appearance.liveStateLock) {
/* 1316 */           if (b == 0) {
/* 1317 */             this.appearance.clearLive(paramSetLiveState.refCount);
/*      */           }
/* 1319 */           this.appearance.removeAMirrorUser(shape3DRetained);
/*      */         } 
/*      */       }
/* 1322 */       if (this.geometryList != null) {
/* 1323 */         for (byte b1 = 0; b1 < this.geometryList.size(); b1++) {
/* 1324 */           GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b1);
/* 1325 */           if (geometryRetained != null) {
/* 1326 */             synchronized (geometryRetained.liveStateLock) {
/* 1327 */               if (b == 0) {
/* 1328 */                 geometryRetained.clearLive(paramSetLiveState.refCount);
/*      */               }
/* 1330 */               geometryRetained.removeUser(shape3DRetained);
/*      */             } 
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1337 */     paramSetLiveState.notifyThreads |= 0x30C0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1344 */     if (!this.source.isLive()) {
/*      */       byte b1;
/*      */       
/* 1347 */       for (b1 = 0; b1 < this.numfogs; b1++)
/* 1348 */         this.fogs[b1] = null; 
/* 1349 */       this.numfogs = 0;
/*      */ 
/*      */       
/* 1352 */       for (b1 = 0; b1 < this.numModelClips; b1++)
/* 1353 */         this.modelClips[b1] = null; 
/* 1354 */       this.numModelClips = 0;
/*      */ 
/*      */       
/* 1357 */       for (b1 = 0; b1 < this.numlights; b1++)
/* 1358 */         this.lights[b1] = null; 
/* 1359 */       this.numlights = 0;
/*      */ 
/*      */       
/* 1362 */       for (b1 = 0; b1 < this.numAltApps; b1++)
/* 1363 */         this.altApps[b1] = null; 
/* 1364 */       this.numAltApps = 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean isStatic() {
/* 1369 */     if (this.source.getCapability(15) || this.source.getCapability(13) || this.source.getCapability(19))
/*      */     {
/*      */       
/* 1372 */       return false;
/*      */     }
/* 1374 */     return true;
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
/*      */   boolean staticXformCanBeApplied() {
/* 1391 */     if (this.isPickable || this.isCollidable || this.source.getCapability(6) || this.source.getCapability(8))
/*      */     {
/*      */       
/* 1394 */       return false;
/*      */     }
/*      */     
/* 1397 */     if (this.appearance != null && this.appearance.transparencyAttributes != null && this.appearance.transparencyAttributes.transparencyMode != 4)
/*      */     {
/* 1399 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1404 */     for (byte b = 0; b < this.geometryList.size(); b++) {
/* 1405 */       GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 1406 */       if (geometryRetained != null) {
/* 1407 */         if (geometryRetained.refCnt > 1) {
/* 1408 */           return false;
/*      */         }
/* 1410 */         boolean bool = isAlphaEditable(geometryRetained);
/* 1411 */         if (geometryRetained instanceof GeometryArrayRetained) {
/* 1412 */           geometryRetained.isEditable = !((GeometryArrayRetained)geometryRetained).isWriteStatic();
/*      */ 
/*      */ 
/*      */           
/* 1416 */           if (geometryRetained.source.getCapability(0) || geometryRetained.source.getCapability(4))
/*      */           {
/*      */ 
/*      */             
/* 1420 */             return false;
/*      */           }
/*      */         } 
/*      */         
/* 1424 */         if (!geometryRetained.canBeInDisplayList(bool)) {
/* 1425 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/* 1429 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void compile(CompileState paramCompileState) {
/* 1436 */     super.compile(paramCompileState);
/*      */     
/* 1438 */     if (isStatic() && staticXformCanBeApplied()) {
/* 1439 */       this.mergeFlag = 1;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1445 */       this.mergeFlag = 0;
/* 1446 */       paramCompileState.keepTG = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1453 */     if (this.appearance != null) {
/* 1454 */       this.appearance.compile(paramCompileState);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1460 */       if (this.appearance.isStatic()) {
/* 1461 */         AppearanceRetained appearanceRetained = paramCompileState.getAppearance(this.appearance);
/* 1462 */         this.appearance = appearanceRetained;
/*      */       } 
/*      */     } 
/*      */     
/* 1466 */     for (byte b = 0; b < this.geometryList.size(); b++) {
/* 1467 */       GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 1468 */       if (geometryRetained != null) {
/* 1469 */         geometryRetained.compile(paramCompileState);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void merge(CompileState paramCompileState) {
/* 1477 */     if (this.mergeFlag == 0) {
/*      */ 
/*      */ 
/*      */       
/* 1481 */       TransformGroupRetained transformGroupRetained = paramCompileState.staticTransform;
/*      */       
/* 1483 */       paramCompileState.staticTransform = null;
/* 1484 */       super.merge(paramCompileState);
/* 1485 */       paramCompileState.staticTransform = transformGroupRetained;
/*      */     } else {
/* 1487 */       super.merge(paramCompileState);
/*      */     } 
/*      */     
/* 1490 */     if (shapeIsMergeable(paramCompileState)) {
/* 1491 */       paramCompileState.addShape(this);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   boolean shapeIsMergeable(CompileState paramCompileState) {
/* 1497 */     boolean bool = true;
/*      */ 
/*      */ 
/*      */     
/* 1501 */     GeometryRetained geometryRetained = null;
/* 1502 */     byte b2 = 0;
/* 1503 */     byte b1 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1510 */     if (this.staticTransform != null) {
/* 1511 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1515 */     if (this.parent instanceof OrderedGroupRetained || this.parent instanceof SwitchRetained)
/*      */     {
/* 1517 */       return false;
/*      */     }
/*      */     
/* 1520 */     while (geometryRetained == null && b2 < this.geometryList.size()) {
/* 1521 */       geometryRetained = (GeometryRetained)this.geometryList.get(b2);
/* 1522 */       b2++;
/*      */     } 
/*      */     
/* 1525 */     if (!(geometryRetained instanceof GeometryArrayRetained)) {
/* 1526 */       return false;
/*      */     }
/*      */     
/* 1529 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)geometryRetained;
/*      */     
/* 1531 */     for (b1 = b2; b1 < this.geometryList.size() && bool; b1++) {
/* 1532 */       geometryRetained = (GeometryRetained)this.geometryList.get(b1);
/* 1533 */       if (geometryRetained != null) {
/* 1534 */         GeometryArrayRetained geometryArrayRetained1 = (GeometryArrayRetained)geometryRetained;
/*      */         
/* 1536 */         if (!geometryArrayRetained1.isWriteStatic()) {
/* 1537 */           bool = false;
/*      */         }
/* 1539 */         if (geometryArrayRetained1.vertexFormat != geometryArrayRetained.vertexFormat) {
/* 1540 */           bool = false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1547 */     if (this.source.getCapability(17) || this.source.getCapability(15) || this.source.getCapability(19) || this.source.getCapability(10) || this.source.getCapability(4) || this.source.getCapability(8) || this.source.getCapability(6) || this.source.getCapability(13))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1555 */       bool = false;
/*      */     }
/*      */     
/* 1558 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) {
/*      */     Shape3DRetained shape3DRetained;
/* 1565 */     if (this.inSharedGroup) {
/* 1566 */       if (paramHashKey.count == 0) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1571 */       shape3DRetained = getMirrorShape(paramHashKey);
/*      */     }
/*      */     else {
/*      */       
/* 1575 */       shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */     } 
/*      */     
/* 1578 */     paramArrayList.add(getGeomAtom(shape3DRetained));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addLight(LightRetained paramLightRetained) {
/* 1589 */     if (this.lights == null) {
/* 1590 */       this.lights = new LightRetained[10];
/*      */     }
/* 1592 */     else if (this.lights.length == this.numlights) {
/* 1593 */       LightRetained[] arrayOfLightRetained = new LightRetained[this.numlights * 2];
/* 1594 */       for (byte b = 0; b < this.numlights; b++) {
/* 1595 */         arrayOfLightRetained[b] = this.lights[b];
/*      */       }
/* 1597 */       this.lights = arrayOfLightRetained;
/*      */     } 
/* 1599 */     this.lights[this.numlights] = paramLightRetained;
/* 1600 */     this.numlights++;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeLight(LightRetained paramLightRetained) {
/*      */     byte b;
/* 1607 */     for (b = 0; b < this.numlights; b++) {
/* 1608 */       if (this.lights[b] == paramLightRetained) {
/* 1609 */         this.lights[b] = null;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 1615 */     for (; ++b < this.numlights; b++) {
/* 1616 */       this.lights[b - 1] = this.lights[b];
/*      */     }
/* 1618 */     this.numlights--;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addFog(FogRetained paramFogRetained) {
/* 1626 */     if (this.fogs == null) {
/* 1627 */       this.fogs = new FogRetained[10];
/*      */     }
/* 1629 */     else if (this.fogs.length == this.numfogs) {
/* 1630 */       FogRetained[] arrayOfFogRetained = new FogRetained[this.numfogs * 2];
/* 1631 */       for (byte b = 0; b < this.numfogs; b++) {
/* 1632 */         arrayOfFogRetained[b] = this.fogs[b];
/*      */       }
/* 1634 */       this.fogs = arrayOfFogRetained;
/*      */     } 
/* 1636 */     this.fogs[this.numfogs] = paramFogRetained;
/* 1637 */     this.numfogs++;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeFog(FogRetained paramFogRetained) {
/*      */     byte b;
/* 1644 */     for (b = 0; b < this.numfogs; b++) {
/* 1645 */       if (this.fogs[b] == paramFogRetained) {
/* 1646 */         this.fogs[b] = null;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 1652 */     for (; ++b < this.numfogs; b++) {
/* 1653 */       this.fogs[b - 1] = this.fogs[b];
/*      */     }
/* 1655 */     this.numfogs--;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addModelClip(ModelClipRetained paramModelClipRetained) {
/* 1665 */     if (this.modelClips == null) {
/* 1666 */       this.modelClips = new ModelClipRetained[10];
/*      */     }
/* 1668 */     else if (this.modelClips.length == this.numModelClips) {
/* 1669 */       ModelClipRetained[] arrayOfModelClipRetained = new ModelClipRetained[this.numModelClips * 2];
/* 1670 */       for (byte b = 0; b < this.numModelClips; b++) {
/* 1671 */         arrayOfModelClipRetained[b] = this.modelClips[b];
/*      */       }
/* 1673 */       this.modelClips = arrayOfModelClipRetained;
/*      */     } 
/* 1675 */     this.modelClips[this.numModelClips] = paramModelClipRetained;
/* 1676 */     this.numModelClips++;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeModelClip(ModelClipRetained paramModelClipRetained) {
/*      */     byte b;
/* 1683 */     for (b = 0; b < this.numModelClips; b++) {
/* 1684 */       if (this.modelClips[b] == paramModelClipRetained) {
/* 1685 */         this.modelClips[b] = null;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 1691 */     for (; ++b < this.numModelClips; b++) {
/* 1692 */       this.modelClips[b - 1] = this.modelClips[b];
/*      */     }
/* 1694 */     this.numModelClips--;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained) {
/* 1702 */     if (this.altApps == null) {
/* 1703 */       this.altApps = new AlternateAppearanceRetained[10];
/*      */     }
/* 1705 */     else if (this.altApps.length == this.numAltApps) {
/* 1706 */       AlternateAppearanceRetained[] arrayOfAlternateAppearanceRetained = new AlternateAppearanceRetained[this.numAltApps * 2];
/* 1707 */       for (byte b = 0; b < this.numAltApps; b++) {
/* 1708 */         arrayOfAlternateAppearanceRetained[b] = this.altApps[b];
/*      */       }
/* 1710 */       this.altApps = arrayOfAlternateAppearanceRetained;
/*      */     } 
/* 1712 */     this.altApps[this.numAltApps] = paramAlternateAppearanceRetained;
/* 1713 */     this.numAltApps++;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeAltApp(AlternateAppearanceRetained paramAlternateAppearanceRetained) {
/*      */     byte b;
/* 1720 */     for (b = 0; b < this.numAltApps; b++) {
/* 1721 */       if (this.altApps[b] == paramAlternateAppearanceRetained) {
/* 1722 */         this.altApps[b] = null;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 1728 */     for (; ++b < this.numAltApps; b++) {
/* 1729 */       this.altApps[b - 1] = this.altApps[b];
/*      */     }
/* 1731 */     this.numAltApps--;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePickable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 1738 */     super.updatePickable(paramArrayOfHashKey, paramArrayOfBoolean);
/*      */ 
/*      */     
/* 1741 */     if (!this.inSharedGroup) {
/* 1742 */       Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/* 1743 */       shape3DRetained.isPickable = paramArrayOfBoolean[0];
/*      */     } else {
/* 1745 */       int i = this.mirrorShape3D.size();
/* 1746 */       for (byte b = 0; b < paramArrayOfHashKey.length; b++) {
/* 1747 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1748 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b1);
/* 1749 */           if (paramArrayOfHashKey[b].equals(shape3DRetained.key)) {
/* 1750 */             shape3DRetained.isPickable = paramArrayOfBoolean[b];
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateCollidable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 1761 */     super.updateCollidable(paramArrayOfHashKey, paramArrayOfBoolean);
/*      */ 
/*      */     
/* 1764 */     if (!this.inSharedGroup) {
/* 1765 */       Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/* 1766 */       shape3DRetained.isCollidable = paramArrayOfBoolean[0];
/*      */     } else {
/* 1768 */       int i = this.mirrorShape3D.size();
/* 1769 */       for (byte b = 0; b < paramArrayOfHashKey.length; b++) {
/* 1770 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1771 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b1);
/* 1772 */           if (paramArrayOfHashKey[b].equals(shape3DRetained.key)) {
/* 1773 */             shape3DRetained.isCollidable = paramArrayOfBoolean[b];
/*      */             break;
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
/*      */   private void sendDataChangedMessage(GeometryRetained paramGeometryRetained) {
/* 1794 */     GeometryAtom[] arrayOfGeometryAtom1 = null;
/* 1795 */     GeometryAtom[] arrayOfGeometryAtom2 = null;
/* 1796 */     Object object = null;
/* 1797 */     int i = 0;
/* 1798 */     GeometryRetained geometryRetained = null;
/*      */     
/* 1800 */     int j = this.mirrorShape3D.size();
/*      */     
/* 1802 */     if (j < 1) {
/*      */       return;
/*      */     }
/* 1805 */     Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */     
/* 1807 */     shape3DRetained.mirrorShape3DLock.writeLock();
/*      */     
/* 1809 */     GeometryAtom geometryAtom1 = shape3DRetained.geomAtom;
/*      */     
/* 1811 */     GeometryAtom geometryAtom2 = new GeometryAtom();
/*      */     
/* 1813 */     if (paramGeometryRetained != null) {
/* 1814 */       paramGeometryRetained.addUser(shape3DRetained);
/*      */     }
/*      */     
/* 1817 */     int k = this.geometryList.size();
/*      */     byte b1;
/* 1819 */     for (b1 = 0; b1 < k; b1++) {
/* 1820 */       geometryRetained = (GeometryRetained)this.geometryList.get(b1);
/* 1821 */       if (geometryRetained != null) {
/* 1822 */         geometryAtom2.geoType = geometryRetained.geoType;
/* 1823 */         geometryAtom2.alphaEditable = shape3DRetained.isAlphaEditable(geometryRetained);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1828 */     if (geometryRetained != null && geometryRetained.geoType == 16) {
/*      */ 
/*      */       
/* 1831 */       for (b1 = 0; b1 < k; b1++) {
/* 1832 */         geometryRetained = (GeometryRetained)this.geometryList.get(b1);
/* 1833 */         if (geometryRetained != null) {
/* 1834 */           Text3DRetained text3DRetained = (Text3DRetained)geometryRetained;
/* 1835 */           i += text3DRetained.numChars;
/*      */         }
/*      */         else {
/*      */           
/* 1839 */           i++;
/*      */         } 
/*      */       } 
/* 1842 */       geometryAtom2.geometryArray = new GeometryRetained[i];
/* 1843 */       geometryAtom2.lastLocalTransformArray = new Transform3D[i];
/*      */       
/* 1845 */       i = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1849 */       geometryAtom2.geometryArray = new GeometryRetained[k];
/*      */     } 
/*      */     
/* 1852 */     geometryAtom2.locale = shape3DRetained.locale;
/* 1853 */     geometryAtom2.visible = this.visible;
/* 1854 */     geometryAtom2.source = shape3DRetained;
/*      */ 
/*      */     
/* 1857 */     for (byte b2 = 0; b2 < k; b2++) {
/* 1858 */       geometryRetained = (GeometryRetained)this.geometryList.get(b2);
/* 1859 */       if (geometryRetained == null) {
/* 1860 */         geometryAtom2.geometryArray[i++] = null;
/*      */       
/*      */       }
/* 1863 */       else if (geometryRetained.geoType == 16) {
/* 1864 */         Text3DRetained text3DRetained = (Text3DRetained)geometryRetained;
/*      */         
/* 1866 */         for (b1 = 0; b1 < text3DRetained.numChars; b1++, i++) {
/* 1867 */           GeometryArrayRetained geometryArrayRetained = text3DRetained.geometryList[b1];
/* 1868 */           if (geometryArrayRetained != null) {
/* 1869 */             geometryAtom2.geometryArray[i] = geometryArrayRetained;
/* 1870 */             geometryAtom2.lastLocalTransformArray[i] = text3DRetained.charTransforms[b1];
/*      */           }
/*      */           else {
/*      */             
/* 1874 */             geometryAtom2.geometryArray[i] = null;
/* 1875 */             geometryAtom2.lastLocalTransformArray[i] = null;
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 1881 */         geometryAtom2.geometryArray[i++] = geometryRetained;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1886 */     arrayOfGeometryAtom2 = new GeometryAtom[j];
/* 1887 */     arrayOfGeometryAtom1 = new GeometryAtom[j];
/* 1888 */     arrayOfGeometryAtom2[0] = geometryAtom1;
/* 1889 */     arrayOfGeometryAtom1[0] = geometryAtom2;
/*      */     
/* 1891 */     shape3DRetained.geomAtom = geometryAtom2;
/* 1892 */     shape3DRetained.mirrorShape3DLock.writeUnlock();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1897 */     for (b1 = 1; b1 < j; b1++) {
/* 1898 */       shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b1);
/* 1899 */       shape3DRetained.mirrorShape3DLock.writeLock();
/* 1900 */       geometryAtom1 = shape3DRetained.geomAtom;
/* 1901 */       geometryAtom2 = new GeometryAtom();
/*      */       
/* 1903 */       if (paramGeometryRetained != null) {
/* 1904 */         paramGeometryRetained.addUser(shape3DRetained);
/*      */       }
/*      */       
/* 1907 */       geometryAtom2.geoType = (arrayOfGeometryAtom1[0]).geoType;
/* 1908 */       geometryAtom2.locale = shape3DRetained.locale;
/* 1909 */       geometryAtom2.visible = this.visible;
/* 1910 */       geometryAtom2.source = shape3DRetained;
/* 1911 */       geometryAtom2.alphaEditable = (arrayOfGeometryAtom1[0]).alphaEditable;
/*      */       
/* 1913 */       geometryAtom2.geometryArray = new GeometryRetained[(arrayOfGeometryAtom1[0]).geometryArray.length];
/* 1914 */       for (byte b = 0; b < geometryAtom2.geometryArray.length; b++) {
/* 1915 */         geometryAtom2.geometryArray[b] = (arrayOfGeometryAtom1[0]).geometryArray[b];
/*      */       }
/*      */       
/* 1918 */       arrayOfGeometryAtom2[b1] = geometryAtom1;
/* 1919 */       arrayOfGeometryAtom1[b1] = geometryAtom2;
/*      */       
/* 1921 */       shape3DRetained.geomAtom = geometryAtom2;
/* 1922 */       shape3DRetained.mirrorShape3DLock.writeUnlock();
/*      */     } 
/*      */     
/* 1925 */     TargetsInterface targetsInterface = ((GroupRetained)this.parent).getClosestTargetsInterface(0);
/*      */ 
/*      */     
/* 1928 */     CachedTargets[] arrayOfCachedTargets = null;
/*      */     
/* 1930 */     if (targetsInterface != null) {
/*      */       
/* 1932 */       arrayOfCachedTargets = new CachedTargets[j];
/*      */       
/* 1934 */       for (b1 = 0; b1 < j; b1++) {
/*      */         
/* 1936 */         CachedTargets cachedTargets = targetsInterface.getCachedTargets(0, b1, -1);
/*      */         
/* 1938 */         if (cachedTargets != null) {
/* 1939 */           arrayOfCachedTargets[b1] = new CachedTargets();
/* 1940 */           arrayOfCachedTargets[b1].copy(cachedTargets);
/* 1941 */           arrayOfCachedTargets[b1].replace(arrayOfGeometryAtom2[b1], arrayOfGeometryAtom1[b1], 0);
/*      */         } else {
/*      */           
/* 1944 */           arrayOfCachedTargets[b1] = null;
/*      */         } 
/*      */       } 
/* 1947 */       targetsInterface.resetCachedTargets(0, arrayOfCachedTargets, -1);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1952 */     J3dMessage j3dMessage = new J3dMessage();
/* 1953 */     j3dMessage.type = 24;
/*      */     
/* 1955 */     j3dMessage.threads = 8384;
/*      */ 
/*      */     
/* 1958 */     j3dMessage.universe = this.universe;
/* 1959 */     j3dMessage.args[0] = this;
/* 1960 */     j3dMessage.args[1] = new Integer(1);
/* 1961 */     j3dMessage.args[2] = arrayOfGeometryAtom2;
/* 1962 */     j3dMessage.args[3] = arrayOfGeometryAtom1;
/* 1963 */     if (targetsInterface != null) {
/* 1964 */       j3dMessage.args[4] = targetsInterface;
/* 1965 */       j3dMessage.args[5] = arrayOfCachedTargets;
/*      */     } 
/* 1967 */     if (this.boundsAutoCompute) {
/* 1968 */       getCombineBounds((BoundingBox)this.localBounds);
/*      */     }
/* 1970 */     VirtualUniverse.mc.processMessage(j3dMessage);
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
/*      */   Shape3DRetained getMirrorShape(SceneGraphPath paramSceneGraphPath) {
/* 1982 */     if (!this.inSharedGroup) {
/* 1983 */       return (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */     }
/* 1985 */     HashKey hashKey = new HashKey("");
/* 1986 */     paramSceneGraphPath.getHashKey(hashKey);
/* 1987 */     return getMirrorShape(hashKey);
/*      */   }
/*      */   
/*      */   Shape3DRetained getMirrorShape(HashKey paramHashKey) {
/* 1991 */     if (paramHashKey == null) {
/* 1992 */       return (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */     }
/* 1994 */     int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */     
/* 1996 */     if (i >= 0) {
/* 1997 */       return (Shape3DRetained)this.mirrorShape3D.get(i);
/*      */     }
/*      */ 
/*      */     
/* 2001 */     throw new RuntimeException("Shape3DRetained: MirrorShape Not found!");
/*      */   }
/*      */ 
/*      */   
/*      */   void setBoundsAutoCompute(boolean paramBoolean) {
/* 2006 */     if (paramBoolean != this.boundsAutoCompute) {
/* 2007 */       if (paramBoolean) {
/*      */         
/* 2009 */         this.localBounds = new BoundingBox((BoundingBox)null);
/* 2010 */         if (this.source.isLive() && this.geometryList != null) {
/* 2011 */           int i = this.geometryList.size() * this.mirrorShape3D.size();
/* 2012 */           for (byte b = 0; b < i; b++) {
/* 2013 */             GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 2014 */             geometryRetained.incrComputeGeoBounds();
/*      */           } 
/*      */         } 
/*      */         
/* 2018 */         getCombineBounds((BoundingBox)this.localBounds);
/*      */       
/*      */       }
/* 2021 */       else if (this.source.isLive() && this.geometryList != null) {
/* 2022 */         int i = this.geometryList.size() * this.mirrorShape3D.size();
/* 2023 */         for (byte b = 0; b < i; b++) {
/* 2024 */           GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 2025 */           geometryRetained.decrComputeGeoBounds();
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2030 */       super.setBoundsAutoCompute(paramBoolean);
/* 2031 */       if (this.source.isLive()) {
/* 2032 */         J3dMessage j3dMessage = new J3dMessage();
/* 2033 */         j3dMessage.type = 37;
/* 2034 */         j3dMessage.threads = 8384;
/*      */ 
/*      */         
/* 2037 */         j3dMessage.universe = this.universe;
/* 2038 */         j3dMessage.args[0] = getGeomAtomsArray(this.mirrorShape3D);
/*      */         
/* 2040 */         j3dMessage.args[1] = this.localBounds;
/* 2041 */         VirtualUniverse.mc.processMessage(j3dMessage);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateBounds() {
/* 2049 */     this.localBounds = new BoundingBox((BoundingBox)null);
/* 2050 */     getCombineBounds((BoundingBox)this.localBounds);
/* 2051 */     synchronized (this.mirrorShape3D) {
/* 2052 */       if (this.source.isLive()) {
/* 2053 */         J3dMessage j3dMessage = new J3dMessage();
/* 2054 */         j3dMessage.type = 37;
/* 2055 */         j3dMessage.threads = 8384;
/*      */ 
/*      */         
/* 2058 */         j3dMessage.universe = this.universe;
/* 2059 */         j3dMessage.args[0] = getGeomAtomsArray(this.mirrorShape3D);
/*      */         
/* 2061 */         j3dMessage.args[1] = this.localBounds;
/* 2062 */         VirtualUniverse.mc.processMessage(j3dMessage);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean allowIntersect() {
/* 2068 */     GeometryRetained geometryRetained = null;
/*      */     
/* 2070 */     for (byte b = 0; b < this.geometryList.size(); b++) {
/* 2071 */       geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 2072 */       if (geometryRetained != null && 
/* 2073 */         !geometryRetained.source.getCapability(18)) {
/* 2074 */         return false;
/*      */       }
/*      */     } 
/* 2077 */     return true;
/*      */   }
/*      */   
/*      */   boolean intersectGeometryList(Shape3DRetained paramShape3DRetained) {
/* 2081 */     ArrayList arrayList = paramShape3DRetained.geometryList;
/* 2082 */     int i = arrayList.size();
/* 2083 */     Transform3D transform3D1 = paramShape3DRetained.getCurrentLocalToVworld();
/* 2084 */     Transform3D transform3D2 = getCurrentLocalToVworld();
/* 2085 */     Object object = null;
/* 2086 */     int j = -1;
/*      */ 
/*      */     
/* 2089 */     if (this instanceof OrientedShape3DRetained) {
/* 2090 */       j = getPrimaryViewIdx();
/* 2091 */       transform3D2.mul(((OrientedShape3DRetained)this).getOrientedTransform(j));
/*      */     } 
/*      */ 
/*      */     
/* 2095 */     if (paramShape3DRetained instanceof OrientedShape3DRetained) {
/* 2096 */       if (j < 0) {
/* 2097 */         j = getPrimaryViewIdx();
/*      */       }
/* 2099 */       transform3D1.mul(((OrientedShape3DRetained)paramShape3DRetained).getOrientedTransform(j));
/*      */     } 
/*      */ 
/*      */     
/* 2103 */     for (int k = this.geometryList.size() - 1; k >= 0; k--) {
/* 2104 */       GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(k);
/* 2105 */       if (geometryRetained != null) {
/* 2106 */         for (int m = i - 1; m >= 0; m--) {
/* 2107 */           GeometryRetained geometryRetained1 = (GeometryRetained)arrayList.get(m);
/* 2108 */           if (geometryRetained1 != null && geometryRetained.intersect(transform3D2, transform3D1, geometryRetained1))
/*      */           {
/*      */             
/* 2111 */             return true;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2117 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectGeometryList(Transform3D paramTransform3D, Bounds paramBounds) {
/* 2124 */     if (this instanceof OrientedShape3DRetained) {
/* 2125 */       Transform3D transform3D = ((OrientedShape3DRetained)this).getOrientedTransform(getPrimaryViewIdx());
/*      */ 
/*      */       
/* 2128 */       paramTransform3D.mul(transform3D);
/*      */     } 
/*      */     
/* 2131 */     for (int i = this.geometryList.size() - 1; i >= 0; i--) {
/* 2132 */       GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(i);
/* 2133 */       if (geometryRetained != null && geometryRetained.intersect(paramTransform3D, paramBounds))
/*      */       {
/* 2135 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 2139 */     return false;
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
/*      */   void initMirrorShape3D(SetLiveState paramSetLiveState, MorphRetained paramMorphRetained, int paramInt) {
/* 2152 */     Object object = null;
/*      */     
/* 2154 */     this.universe = paramMorphRetained.universe;
/* 2155 */     this.inSharedGroup = paramMorphRetained.inSharedGroup;
/* 2156 */     this.inBackgroundGroup = paramMorphRetained.inBackgroundGroup;
/* 2157 */     this.geometryBackground = paramMorphRetained.geometryBackground;
/* 2158 */     this.parent = paramMorphRetained.parent;
/* 2159 */     this.locale = paramMorphRetained.locale;
/*      */     
/* 2161 */     OrderedPath orderedPath1 = (OrderedPath)paramSetLiveState.orderedPaths.get(paramInt);
/* 2162 */     if (orderedPath1.pathElements.size() == 0) {
/* 2163 */       this.orderedPath = null;
/*      */     } else {
/* 2165 */       this.orderedPath = orderedPath1;
/*      */     } 
/*      */     
/* 2168 */     this.staticTransform = paramMorphRetained.staticTransform;
/* 2169 */     if (paramMorphRetained.boundsAutoCompute) {
/* 2170 */       this.localBounds.set(paramMorphRetained.localBounds);
/*      */     }
/* 2172 */     this.bounds = this.localBounds;
/* 2173 */     this.vwcBounds = new BoundingBox((BoundingBox)null);
/* 2174 */     this.vwcBounds.transform(this.bounds, getCurrentLocalToVworld(0));
/*      */     
/* 2176 */     if (paramMorphRetained.collisionBound == null) {
/* 2177 */       this.collisionBound = null;
/* 2178 */       this.collisionVwcBound = this.vwcBounds;
/*      */     } else {
/* 2180 */       this.collisionBound = paramMorphRetained.collisionBound;
/* 2181 */       this.collisionVwcBound = (Bounds)this.collisionBound.clone();
/* 2182 */       this.collisionVwcBound.transform(getCurrentLocalToVworld(0));
/*      */     } 
/*      */     
/* 2185 */     this.appearanceOverrideEnable = paramMorphRetained.appearanceOverrideEnable;
/*      */ 
/*      */     
/* 2188 */     this.geometryList = new ArrayList(1);
/* 2189 */     this.geometryList.add((GeometryArrayRetained)paramMorphRetained.morphedGeometryArray.retained);
/*      */     
/* 2191 */     GeometryAtom geometryAtom = new GeometryAtom();
/* 2192 */     geometryAtom.geometryArray = new GeometryRetained[1];
/*      */     
/* 2194 */     geometryAtom.locale = this.locale;
/* 2195 */     geometryAtom.visible = paramMorphRetained.visible;
/* 2196 */     geometryAtom.source = this;
/*      */     
/* 2198 */     GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(0);
/*      */     
/* 2200 */     if (geometryRetained == null) {
/* 2201 */       geometryAtom.geometryArray[0] = null;
/*      */     } else {
/* 2203 */       geometryAtom.geometryArray[0] = (GeometryArrayRetained)paramMorphRetained.morphedGeometryArray.retained;
/*      */       
/* 2205 */       geometryAtom.geoType = (geometryAtom.geometryArray[0]).geoType;
/*      */     } 
/* 2207 */     this.geomAtom = geometryAtom;
/*      */ 
/*      */     
/* 2210 */     this.sourceNode = paramMorphRetained;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMorphGeometry(Geometry paramGeometry, ArrayList paramArrayList) {
/* 2220 */     int i = paramArrayList.size();
/*      */ 
/*      */     
/* 2223 */     GeometryAtom[] arrayOfGeometryAtom1 = new GeometryAtom[i];
/* 2224 */     GeometryAtom[] arrayOfGeometryAtom2 = new GeometryAtom[i];
/*      */     
/*      */     byte b;
/* 2227 */     for (b = 0; b < i; b++) {
/* 2228 */       Shape3DRetained shape3DRetained = (Shape3DRetained)paramArrayList.get(b);
/*      */       
/* 2230 */       GeometryAtom geometryAtom1 = getGeomAtom(shape3DRetained);
/*      */       
/* 2232 */       shape3DRetained.geometryList = new ArrayList(1);
/* 2233 */       shape3DRetained.geometryList.add((GeometryArrayRetained)paramGeometry.retained);
/*      */       
/* 2235 */       GeometryAtom geometryAtom2 = new GeometryAtom();
/* 2236 */       geometryAtom2.geometryArray = new GeometryRetained[1];
/*      */       
/* 2238 */       if (paramGeometry == null) {
/* 2239 */         geometryAtom2.geometryArray[0] = null;
/*      */       } else {
/* 2241 */         geometryAtom2.geometryArray[0] = (GeometryArrayRetained)paramGeometry.retained;
/*      */         
/* 2243 */         geometryAtom2.geoType = (geometryAtom2.geometryArray[0]).geoType;
/*      */       } 
/*      */       
/* 2246 */       geometryAtom2.locale = this.locale;
/* 2247 */       geometryAtom2.visible = geometryAtom1.visible;
/* 2248 */       geometryAtom2.source = this;
/*      */       
/* 2250 */       arrayOfGeometryAtom1[b] = geometryAtom1;
/* 2251 */       arrayOfGeometryAtom2[b] = geometryAtom2;
/*      */       
/* 2253 */       setGeomAtom(shape3DRetained, geometryAtom2);
/*      */     } 
/*      */     
/* 2256 */     TargetsInterface targetsInterface = ((GroupRetained)this.parent).getClosestTargetsInterface(0);
/*      */ 
/*      */     
/* 2259 */     CachedTargets[] arrayOfCachedTargets = null;
/*      */     
/* 2261 */     if (targetsInterface != null) {
/*      */       
/* 2263 */       arrayOfCachedTargets = new CachedTargets[i];
/*      */       
/* 2265 */       for (b = 0; b < i; b++) {
/*      */         
/* 2267 */         CachedTargets cachedTargets = targetsInterface.getCachedTargets(0, b, -1);
/*      */         
/* 2269 */         if (cachedTargets != null) {
/* 2270 */           arrayOfCachedTargets[b] = new CachedTargets();
/* 2271 */           arrayOfCachedTargets[b].copy(cachedTargets);
/* 2272 */           arrayOfCachedTargets[b].replace(arrayOfGeometryAtom1[b], arrayOfGeometryAtom2[b], 0);
/*      */         } else {
/*      */           
/* 2275 */           arrayOfCachedTargets[b] = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2282 */     J3dMessage j3dMessage = new J3dMessage();
/* 2283 */     j3dMessage.type = 24;
/* 2284 */     j3dMessage.threads = 8384;
/*      */ 
/*      */     
/* 2287 */     j3dMessage.universe = this.universe;
/* 2288 */     j3dMessage.args[0] = this;
/* 2289 */     j3dMessage.args[1] = new Integer(1);
/* 2290 */     j3dMessage.args[2] = arrayOfGeometryAtom1;
/* 2291 */     j3dMessage.args[3] = arrayOfGeometryAtom2;
/* 2292 */     if (targetsInterface != null) {
/* 2293 */       j3dMessage.args[4] = targetsInterface;
/* 2294 */       j3dMessage.args[5] = arrayOfCachedTargets;
/*      */     } 
/* 2296 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final GeometryAtom[] getGeomAtomsArray(ArrayList paramArrayList) {
/*      */     int i;
/* 2307 */     Shape3DRetained shape3DRetained = null;
/* 2308 */     GeometryAtom[] arrayOfGeometryAtom1 = null;
/* 2309 */     int j = 0;
/*      */     
/* 2311 */     synchronized (paramArrayList) {
/* 2312 */       i = paramArrayList.size();
/* 2313 */       arrayOfGeometryAtom1 = new GeometryAtom[i];
/* 2314 */       for (byte b = 0; b < i; b++) {
/* 2315 */         shape3DRetained = (Shape3DRetained)paramArrayList.get(b);
/* 2316 */         shape3DRetained.mirrorShape3DLock.readLock();
/* 2317 */         if (shape3DRetained.geomAtom == null) {
/* 2318 */           j++;
/*      */         }
/* 2320 */         arrayOfGeometryAtom1[b] = shape3DRetained.geomAtom;
/* 2321 */         shape3DRetained.mirrorShape3DLock.readUnlock();
/*      */       } 
/*      */     } 
/* 2324 */     if (j == 0) {
/* 2325 */       return arrayOfGeometryAtom1;
/*      */     }
/* 2327 */     if (j == i) {
/* 2328 */       return null;
/*      */     }
/*      */     
/* 2331 */     GeometryAtom[] arrayOfGeometryAtom2 = new GeometryAtom[i - j];
/*      */     
/* 2333 */     for (byte b1 = 0, b2 = 0; b1 < i; b1++) {
/* 2334 */       if (arrayOfGeometryAtom1[b1] != null) {
/* 2335 */         arrayOfGeometryAtom2[b2++] = arrayOfGeometryAtom1[b1];
/*      */       }
/*      */     } 
/* 2338 */     return arrayOfGeometryAtom2;
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
/*      */   static final ArrayList getGeomAtomsList(ArrayList paramArrayList1, ArrayList paramArrayList2) {
/* 2351 */     ArrayList arrayList1 = new ArrayList();
/*      */     
/* 2353 */     ArrayList arrayList2 = null;
/* 2354 */     Shape3DRetained shape3DRetained = null;
/* 2355 */     boolean bool = false;
/* 2356 */     VirtualUniverse virtualUniverse = null;
/*      */     
/* 2358 */     synchronized (paramArrayList1) {
/* 2359 */       for (int i = paramArrayList1.size() - 1; i >= 0; i--) {
/* 2360 */         shape3DRetained = (Shape3DRetained)paramArrayList1.get(i);
/*      */         
/* 2362 */         if (!bool) {
/* 2363 */           if (virtualUniverse == null) {
/* 2364 */             virtualUniverse = shape3DRetained.universe;
/* 2365 */             paramArrayList2.add(shape3DRetained.universe);
/*      */             
/* 2367 */             arrayList2 = new ArrayList();
/* 2368 */             arrayList1.add(arrayList2);
/*      */           }
/* 2370 */           else if (virtualUniverse != shape3DRetained.universe) {
/* 2371 */             bool = true;
/* 2372 */             paramArrayList2.add(shape3DRetained.universe);
/* 2373 */             arrayList2 = new ArrayList();
/* 2374 */             arrayList1.add(arrayList2);
/*      */           } 
/*      */         } else {
/*      */           
/* 2378 */           int j = paramArrayList2.indexOf(shape3DRetained.universe);
/* 2379 */           if (j < 0) {
/* 2380 */             paramArrayList2.add(shape3DRetained.universe);
/* 2381 */             arrayList2 = new ArrayList();
/* 2382 */             arrayList1.add(arrayList2);
/*      */           } else {
/*      */             
/* 2385 */             arrayList2 = (ArrayList)arrayList1.get(j);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 2390 */         shape3DRetained.mirrorShape3DLock.readLock();
/*      */         
/* 2392 */         if (shape3DRetained.geomAtom != null) {
/* 2393 */           arrayList2.add(shape3DRetained.geomAtom);
/*      */         }
/* 2395 */         shape3DRetained.mirrorShape3DLock.readUnlock();
/*      */       } 
/*      */     } 
/*      */     
/* 2399 */     return arrayList1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static final GeometryAtom getGeomAtom(Shape3DRetained paramShape3DRetained) {
/* 2405 */     paramShape3DRetained.mirrorShape3DLock.readLock();
/* 2406 */     GeometryAtom geometryAtom = paramShape3DRetained.geomAtom;
/* 2407 */     paramShape3DRetained.mirrorShape3DLock.readUnlock();
/*      */     
/* 2409 */     return geometryAtom;
/*      */   }
/*      */   
/*      */   static final void setGeomAtom(Shape3DRetained paramShape3DRetained, GeometryAtom paramGeometryAtom) {
/* 2413 */     paramShape3DRetained.mirrorShape3DLock.writeLock();
/* 2414 */     paramShape3DRetained.geomAtom = paramGeometryAtom;
/* 2415 */     paramShape3DRetained.mirrorShape3DLock.writeUnlock();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isAlphaEditable(GeometryRetained paramGeometryRetained) {
/* 2422 */     boolean bool = false;
/*      */     
/* 2424 */     if (this.appearanceOverrideEnable) {
/* 2425 */       bool = true;
/* 2426 */     } else if (paramGeometryRetained != null && this.appearance != null) {
/*      */ 
/*      */       
/* 2429 */       AppearanceRetained appearanceRetained = this.appearance;
/*      */       
/* 2431 */       if (this.source.getCapability(15) || this.source.getCapability(19) || appearanceRetained.source.getCapability(13) || appearanceRetained.source.getCapability(11) || (appearanceRetained.renderingAttributes != null && (appearanceRetained.renderingAttributes.source.getCapability(3) || appearanceRetained.renderingAttributes.source.getCapability(10))) || (appearanceRetained.transparencyAttributes != null && (appearanceRetained.transparencyAttributes.source.getCapability(1) || appearanceRetained.transparencyAttributes.source.getCapability(3)))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2454 */         bool = true;
/*      */       }
/* 2456 */       else if (paramGeometryRetained instanceof GeometryArrayRetained && (appearanceRetained.source.getCapability(7) || (appearanceRetained.textureAttributes != null && appearanceRetained.textureAttributes.source.getCapability(1)))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2464 */         bool = true;
/*      */       }
/* 2466 */       else if (paramGeometryRetained instanceof RasterRetained && (
/* 2467 */         ((RasterRetained)paramGeometryRetained).type & true) != 0 && ((RasterRetained)paramGeometryRetained).source.getCapability(5)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2472 */         bool = true;
/*      */       } 
/*      */     } 
/*      */     
/* 2476 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getCombineBounds(BoundingBox paramBoundingBox) {
/* 2483 */     if (this.geometryList != null) {
/* 2484 */       BoundingBox boundingBox = null;
/*      */ 
/*      */       
/* 2487 */       if (this.staticTransform != null) {
/* 2488 */         boundingBox = new BoundingBox((BoundingBox)null);
/*      */       }
/*      */       
/* 2491 */       synchronized (paramBoundingBox) {
/* 2492 */         paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 2493 */         paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/* 2494 */         for (byte b = 0; b < this.geometryList.size(); b++) {
/* 2495 */           GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 2496 */           if (geometryRetained != null && geometryRetained.geoType != -1)
/*      */           {
/* 2498 */             synchronized (geometryRetained.geoBounds) {
/* 2499 */               if (this.staticTransform != null) {
/* 2500 */                 boundingBox.set(geometryRetained.geoBounds);
/* 2501 */                 boundingBox.transform(this.staticTransform.transform);
/* 2502 */                 paramBoundingBox.combine(boundingBox);
/*      */               } else {
/* 2504 */                 paramBoundingBox.combine(geometryRetained.geoBounds);
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2514 */       if (this instanceof OrientedShape3DRetained) {
/* 2515 */         double d1 = Math.abs(paramBoundingBox.lower.x);
/* 2516 */         double d2 = Math.abs(paramBoundingBox.upper.x);
/* 2517 */         if (d2 > d1)
/* 2518 */           d1 = d2; 
/* 2519 */         d2 = Math.abs(paramBoundingBox.lower.y);
/* 2520 */         if (d2 > d1)
/* 2521 */           d1 = d2; 
/* 2522 */         d2 = Math.abs(paramBoundingBox.upper.y);
/* 2523 */         if (d2 > d1)
/* 2524 */           d1 = d2; 
/* 2525 */         d2 = Math.abs(paramBoundingBox.lower.z);
/* 2526 */         if (d2 > d1)
/* 2527 */           d1 = d2; 
/* 2528 */         d2 = Math.abs(paramBoundingBox.upper.z);
/* 2529 */         if (d2 > d1) {
/* 2530 */           d1 = d2;
/*      */         }
/*      */         
/* 2533 */         paramBoundingBox.setLower(-d1, -d1, -d1);
/* 2534 */         paramBoundingBox.setUpper(d1, d1, d1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isEquivalent(Shape3DRetained paramShape3DRetained) {
/* 2543 */     if (this.appearance != paramShape3DRetained.appearance || this.appearanceOverrideEnable != paramShape3DRetained.appearanceOverrideEnable || this.isPickable != paramShape3DRetained.isPickable || this.isCollidable != paramShape3DRetained.isCollidable)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2549 */       return false;
/*      */     }
/* 2551 */     if (this.boundsAutoCompute) {
/* 2552 */       if (!paramShape3DRetained.boundsAutoCompute) {
/* 2553 */         return false;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2558 */     else if (this.localBounds != null) {
/* 2559 */       if (paramShape3DRetained.localBounds != null) {
/* 2560 */         return this.localBounds.equals(paramShape3DRetained.localBounds);
/*      */       }
/*      */     }
/* 2563 */     else if (paramShape3DRetained.localBounds != null) {
/* 2564 */       return false;
/*      */     } 
/*      */     
/* 2567 */     if (this.collisionBound != null) {
/* 2568 */       if (paramShape3DRetained.collisionBound == null) {
/* 2569 */         return false;
/*      */       }
/* 2571 */       return this.collisionBound.equals(paramShape3DRetained.collisionBound);
/*      */     } 
/* 2573 */     if (paramShape3DRetained.collisionBound != null) {
/* 2574 */       return false;
/*      */     }
/* 2576 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initializeGAtom(Shape3DRetained paramShape3DRetained) {
/* 2583 */     int i = 0;
/* 2584 */     int j = this.geometryList.size();
/* 2585 */     GeometryRetained geometryRetained = null;
/*      */     
/* 2587 */     paramShape3DRetained.bounds = this.localBounds;
/* 2588 */     paramShape3DRetained.vwcBounds = new BoundingBox((BoundingBox)null);
/* 2589 */     paramShape3DRetained.vwcBounds.transform(paramShape3DRetained.bounds, paramShape3DRetained.getCurrentLocalToVworld(0));
/*      */     
/* 2591 */     if (this.collisionBound == null) {
/* 2592 */       paramShape3DRetained.collisionBound = null;
/* 2593 */       paramShape3DRetained.collisionVwcBound = paramShape3DRetained.vwcBounds;
/*      */     } else {
/* 2595 */       paramShape3DRetained.collisionBound = this.collisionBound;
/* 2596 */       paramShape3DRetained.collisionVwcBound = (Bounds)paramShape3DRetained.collisionBound.clone();
/* 2597 */       paramShape3DRetained.collisionVwcBound.transform(paramShape3DRetained.getCurrentLocalToVworld(0));
/*      */     } 
/* 2599 */     GeometryAtom geometryAtom = new GeometryAtom(); byte b;
/* 2600 */     for (b = 0; b < j; b++) {
/* 2601 */       geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 2602 */       if (geometryRetained != null) {
/* 2603 */         geometryAtom.geoType = geometryRetained.geoType;
/* 2604 */         geometryAtom.alphaEditable = paramShape3DRetained.isAlphaEditable(geometryRetained);
/*      */         break;
/*      */       } 
/*      */     } 
/* 2608 */     if (geometryRetained != null && geometryRetained.geoType == 16) {
/*      */ 
/*      */       
/* 2611 */       for (b = 0; b < j; b++) {
/* 2612 */         geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 2613 */         if (geometryRetained != null) {
/* 2614 */           Text3DRetained text3DRetained = (Text3DRetained)geometryRetained;
/* 2615 */           i += text3DRetained.numChars;
/*      */         }
/*      */         else {
/*      */           
/* 2619 */           i++;
/*      */         } 
/*      */       } 
/* 2622 */       geometryAtom.geometryArray = new GeometryRetained[i];
/* 2623 */       geometryAtom.lastLocalTransformArray = new Transform3D[i];
/*      */       
/* 2625 */       i = 0;
/*      */     }
/*      */     else {
/*      */       
/* 2629 */       geometryAtom.geometryArray = new GeometryRetained[j];
/*      */     } 
/*      */ 
/*      */     
/* 2633 */     for (b = 0; b < this.geometryList.size(); b++) {
/* 2634 */       geometryRetained = (GeometryRetained)this.geometryList.get(b);
/* 2635 */       if (geometryRetained == null) {
/* 2636 */         geometryAtom.geometryArray[b] = null;
/*      */       
/*      */       }
/* 2639 */       else if (geometryRetained.geoType == 16) {
/* 2640 */         Text3DRetained text3DRetained = (Text3DRetained)geometryRetained;
/*      */         
/* 2642 */         for (byte b1 = 0; b1 < text3DRetained.numChars; b1++, i++) {
/* 2643 */           GeometryArrayRetained geometryArrayRetained = text3DRetained.geometryList[b1];
/* 2644 */           if (geometryArrayRetained != null) {
/* 2645 */             geometryAtom.geometryArray[i] = geometryArrayRetained;
/* 2646 */             geometryAtom.lastLocalTransformArray[i] = text3DRetained.charTransforms[b1];
/*      */           } else {
/*      */             
/* 2649 */             geometryAtom.geometryArray[i] = null;
/* 2650 */             geometryAtom.lastLocalTransformArray[i] = null;
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 2656 */         geometryAtom.geometryArray[b] = geometryRetained;
/*      */       } 
/*      */     } 
/*      */     
/* 2660 */     geometryAtom.locale = paramShape3DRetained.locale;
/* 2661 */     geometryAtom.visible = this.visible;
/* 2662 */     geometryAtom.source = paramShape3DRetained;
/* 2663 */     paramShape3DRetained.geomAtom = geometryAtom;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void checkEquivalenceClass(Geometry paramGeometry, int paramInt) {
/* 2669 */     if (paramGeometry != null) {
/* 2670 */       for (int i = this.geometryList.size() - 1; i >= 0; i--) {
/* 2671 */         GeometryRetained geometryRetained = (GeometryRetained)this.geometryList.get(i);
/* 2672 */         if (geometryRetained != null && paramInt != i) {
/*      */ 
/*      */           
/* 2675 */           if (!geometryRetained.isEquivalenceClass((GeometryRetained)paramGeometry.retained)) {
/* 2676 */             throw new IllegalArgumentException(J3dI18N.getString("Shape3DRetained5"));
/*      */           }
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   int indexOfGeometry(Geometry paramGeometry) {
/* 2685 */     if (paramGeometry != null) {
/* 2686 */       return this.geometryList.indexOf(paramGeometry.retained);
/*      */     }
/* 2688 */     return this.geometryList.indexOf(null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeGeometry(Geometry paramGeometry) {
/* 2694 */     int i = indexOfGeometry(paramGeometry);
/* 2695 */     if (i >= 0) {
/* 2696 */       removeGeometry(i);
/*      */     }
/*      */   }
/*      */   
/*      */   void removeAllGeometries() {
/* 2701 */     int i = this.geometryList.size();
/*      */ 
/*      */ 
/*      */     
/* 2705 */     GeometryRetained geometryRetained = null;
/*      */     
/* 2707 */     if (((Shape3D)this.source).isLive()) {
/* 2708 */       for (int j = i - 1; j >= 0; j--) {
/* 2709 */         geometryRetained = (GeometryRetained)this.geometryList.get(j);
/* 2710 */         if (geometryRetained != null) {
/* 2711 */           geometryRetained.clearLive(this.refCount);
/* 2712 */           geometryRetained.decRefCnt();
/* 2713 */           for (byte b = 0; b < this.mirrorShape3D.size(); b++) {
/* 2714 */             Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b);
/* 2715 */             geometryRetained.removeUser(shape3DRetained);
/*      */           } 
/*      */         } 
/* 2718 */         this.geometryList.remove(j);
/*      */       } 
/* 2720 */       sendDataChangedMessage(null);
/*      */     } else {
/* 2722 */       for (int j = i - 1; j >= 0; j--) {
/* 2723 */         geometryRetained = (GeometryRetained)this.geometryList.get(j);
/* 2724 */         if (geometryRetained != null) {
/* 2725 */           geometryRetained.decRefCnt();
/*      */         }
/* 2727 */         this.geometryList.remove(j);
/*      */       } 
/*      */     } 
/* 2730 */     dirtyBoundsCache();
/*      */   }
/*      */   
/*      */   boolean willRemainOpaque(int paramInt) {
/* 2734 */     if (this.appearance == null || (this.appearance.isStatic() && this.appearance.isOpaque(paramInt)))
/*      */     {
/*      */       
/* 2737 */       return true;
/*      */     }
/*      */     
/* 2740 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void handleFrequencyChange(int paramInt) {
/* 2746 */     int i = 0;
/* 2747 */     if (paramInt == 13) {
/* 2748 */       i = 1;
/*      */     }
/* 2750 */     else if (paramInt == 15) {
/* 2751 */       i = 2;
/*      */     }
/* 2753 */     else if (paramInt == 19) {
/* 2754 */       i = 16;
/*      */     } 
/* 2756 */     if (i != 0) {
/* 2757 */       if (this.source.getCapabilityIsFrequent(paramInt)) {
/* 2758 */         this.changedFrequent |= i;
/* 2759 */       } else if (!this.source.isLive()) {
/* 2760 */         this.changedFrequent &= (i ^ 0xFFFFFFFF);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isAlphaFrequentlyEditable(GeometryRetained paramGeometryRetained) {
/* 2769 */     boolean bool = false;
/* 2770 */     if (this.appearanceOverrideEnable) {
/* 2771 */       bool = true;
/* 2772 */     } else if (paramGeometryRetained != null && this.appearance != null) {
/*      */       
/* 2774 */       AppearanceRetained appearanceRetained = this.appearance;
/*      */       
/* 2776 */       if ((this.changedFrequent & 0x12) != 0 || (appearanceRetained.changedFrequent & 0x60) != 0 || (appearanceRetained.renderingAttributes != null && (appearanceRetained.renderingAttributes.changedFrequent & 0x28) != 0) || (appearanceRetained.transparencyAttributes != null && appearanceRetained.transparencyAttributes.changedFrequent != 0)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2784 */         bool = true;
/*      */       }
/* 2786 */       else if ((paramGeometryRetained instanceof GeometryArrayRetained && (appearanceRetained.changedFrequent & 0x8) != 0) || (appearanceRetained.textureAttributes != null && (appearanceRetained.textureAttributes.changedFrequent & true) != 0)) {
/*      */ 
/*      */ 
/*      */         
/* 2790 */         bool = true;
/*      */       }
/* 2792 */       else if (paramGeometryRetained instanceof RasterRetained && (
/* 2793 */         ((RasterRetained)paramGeometryRetained).type & true) != 0 && ((RasterRetained)paramGeometryRetained).cachedChangedFrequent != 0) {
/*      */ 
/*      */ 
/*      */         
/* 2797 */         bool = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2802 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int getPrimaryViewIdx() {
/* 2808 */     UnorderList unorderList = VirtualUniverse.mc.cloneView();
/* 2809 */     View[] arrayOfView = (View[])unorderList.toArray(false);
/* 2810 */     int i = unorderList.arraySize();
/*      */     
/* 2812 */     for (byte b = 0; b < i; b++) {
/* 2813 */       if ((arrayOfView[b]).primaryView) {
/* 2814 */         return (arrayOfView[b]).viewIndex;
/*      */       }
/*      */     } 
/* 2817 */     return 0;
/*      */   }
/*      */ 
/*      */   
/* 2821 */   void searchGeometryAtoms(UnorderList paramUnorderList) { paramUnorderList.add(getGeomAtom(getMirrorShape(this.key))); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Shape3DRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */