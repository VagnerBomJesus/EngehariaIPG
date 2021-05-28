/*      */ package javax.media.j3d;
/*      */ import java.util.ArrayList;
/*      */ import javax.vecmath.Color3b;
/*      */ import javax.vecmath.Color4b;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ class MorphRetained extends LeafRetained implements GeometryUpdater {
/*      */   static final int GEOMETRY_CHANGED = 1;
/*      */   static final int APPEARANCE_CHANGED = 2;
/*      */   static final int COLLISION_CHANGED = 4;
/*      */   static final int BOUNDS_CHANGED = 8;
/*      */   static final int APPEARANCEOVERRIDE_CHANGED = 16;
/*      */   static final int UPDATE_MORPH = 32;
/*      */   private static final double TOLERANCE = 1.0E-4D;
/*      */   ArrayList mirrorShape3D;
/*      */   static final int targetThreads = 192;
/*      */   AppearanceRetained appearance;
/*      */   GeometryArrayRetained[] geometryArrays;
/*      */   private int numGeometryArrays;
/*      */   double[] weights;
/*      */   BranchGroupRetained[] branchGroupPath;
/*      */   boolean isPickable;
/*      */   boolean isCollidable;
/*      */   SwitchRetained closestSwitchParent;
/*      */   int closestSwitchIndex;
/*      */   boolean visible;
/*      */   Bounds bounds;
/*      */   BoundingBox vwcBounds;
/*      */   Bounds collisionBound;
/*      */   Bounds collisionVwcBound;
/*      */   GeometryArray morphedGeometryArray;
/*      */   float[] Mcoord;
/*      */   float[] Mcolor;
/*      */   float[] Mnormal;
/*      */   float[][] MtexCoord;
/*      */   boolean appearanceOverrideEnable;
/*      */   int changedFrequent;
/*      */   
/*      */   MorphRetained() {
/*   42 */     this.mirrorShape3D = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   52 */     this.appearance = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   59 */     this.numGeometryArrays = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   75 */     this.isPickable = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   80 */     this.isCollidable = true;
/*      */ 
/*      */ 
/*      */     
/*   84 */     this.closestSwitchParent = null;
/*      */ 
/*      */     
/*   87 */     this.closestSwitchIndex = -1;
/*      */ 
/*      */     
/*   90 */     this.visible = true;
/*      */ 
/*      */     
/*   93 */     this.bounds = null;
/*      */ 
/*      */     
/*   96 */     this.vwcBounds = new BoundingBox();
/*      */ 
/*      */     
/*   99 */     this.collisionBound = null;
/*      */ 
/*      */     
/*  102 */     this.collisionVwcBound = null;
/*      */ 
/*      */     
/*  105 */     this.morphedGeometryArray = null;
/*      */ 
/*      */     
/*  108 */     this.Mcoord = null;
/*  109 */     this.Mcolor = null;
/*  110 */     this.Mnormal = null;
/*      */ 
/*      */     
/*  113 */     this.MtexCoord = (float[][])null;
/*      */ 
/*      */     
/*  116 */     this.appearanceOverrideEnable = false;
/*      */     
/*  118 */     this.changedFrequent = 0;
/*      */ 
/*      */ 
/*      */     
/*  122 */     this.nodeType = 10;
/*  123 */     this.localBounds = new BoundingBox();
/*  124 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  125 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setCollisionBounds(Bounds paramBounds) {
/*  133 */     if (paramBounds != null) {
/*  134 */       this.collisionBound = (Bounds)paramBounds.clone();
/*      */     } else {
/*  136 */       this.collisionBound = null;
/*      */     } 
/*  138 */     if (this.source.isLive()) {
/*      */ 
/*      */       
/*  141 */       J3dMessage j3dMessage = new J3dMessage();
/*  142 */       j3dMessage.type = 34;
/*  143 */       j3dMessage.threads = 8192;
/*  144 */       j3dMessage.universe = this.universe;
/*  145 */       j3dMessage.args[1] = this.collisionBound;
/*  146 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBounds(Bounds paramBounds) {
/*  155 */     super.setBounds(paramBounds);
/*  156 */     if (this.source.isLive() && !this.boundsAutoCompute) {
/*  157 */       J3dMessage j3dMessage = new J3dMessage();
/*  158 */       j3dMessage.type = 35;
/*  159 */       j3dMessage.threads = 8384;
/*      */       
/*  161 */       j3dMessage.universe = this.universe;
/*  162 */       j3dMessage.args[0] = Shape3DRetained.getGeomAtomsArray(this.mirrorShape3D);
/*  163 */       j3dMessage.args[1] = this.localBounds;
/*  164 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  173 */   Bounds getCollisionBounds() { return (this.collisionBound == null) ? null : (Bounds)this.collisionBound.clone(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setGeometryArrays(GeometryArray[] paramArrayOfGeometryArray) {
/*  183 */     if ((paramArrayOfGeometryArray == null || paramArrayOfGeometryArray.length == 0) && this.numGeometryArrays == 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  188 */     if (this.numGeometryArrays != 0 && (paramArrayOfGeometryArray == null || this.numGeometryArrays != paramArrayOfGeometryArray.length)) {
/*  189 */       throw new IllegalArgumentException(J3dI18N.getString("MorphRetained0"));
/*      */     }
/*      */     byte b;
/*  192 */     for (b = 1; b < paramArrayOfGeometryArray.length; b++) {
/*  193 */       if (paramArrayOfGeometryArray[b] == null || paramArrayOfGeometryArray[b - true] == null)
/*  194 */         throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1")); 
/*  195 */       GeometryArrayRetained geometryArrayRetained1 = (GeometryArrayRetained)(paramArrayOfGeometryArray[b]).retained;
/*  196 */       GeometryArrayRetained geometryArrayRetained2 = (GeometryArrayRetained)(paramArrayOfGeometryArray[b - true]).retained;
/*  197 */       if (geometryArrayRetained2 == null || geometryArrayRetained1 == null) {
/*  198 */         throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1"));
/*      */       }
/*      */       
/*  201 */       doErrorCheck(geometryArrayRetained2, geometryArrayRetained1);
/*      */     } 
/*      */ 
/*      */     
/*  205 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)(paramArrayOfGeometryArray[0]).retained;
/*  206 */     if ((geometryArrayRetained.vertexFormat & 0x1000) != 0) {
/*  207 */       throw new UnsupportedOperationException(J3dI18N.getString("MorphRetained9"));
/*      */     }
/*      */ 
/*      */     
/*  211 */     if (paramArrayOfGeometryArray[false] != null) {
/*  212 */       geometryArrayRetained = (GeometryArrayRetained)(paramArrayOfGeometryArray[0]).retained;
/*      */     }
/*      */     
/*  215 */     if (this.numGeometryArrays == 0) {
/*  216 */       this.geometryArrays = new GeometryArrayRetained[paramArrayOfGeometryArray.length];
/*  217 */       this.numGeometryArrays = paramArrayOfGeometryArray.length;
/*      */     } 
/*      */     
/*  220 */     for (b = 0; b < this.numGeometryArrays; b++) {
/*  221 */       geometryArrayRetained = (GeometryArrayRetained)(paramArrayOfGeometryArray[b]).retained;
/*  222 */       if (((Morph)this.source).isLive()) {
/*  223 */         if (this.geometryArrays[b] != null) {
/*  224 */           this.geometryArrays[b].clearLive(this.refCount);
/*  225 */           this.geometryArrays[b].removeMorphUser(this);
/*      */         } 
/*  227 */         if (geometryArrayRetained != null) {
/*  228 */           geometryArrayRetained.setLive(this.inBackgroundGroup, this.refCount);
/*  229 */           geometryArrayRetained.addMorphUser(this);
/*      */         } 
/*      */       } 
/*      */       
/*  233 */       this.geometryArrays[b] = geometryArrayRetained;
/*      */     } 
/*  235 */     if (this.geometryArrays[false] == null) {
/*      */       return;
/*      */     }
/*      */     
/*  239 */     if (this.weights == null) {
/*  240 */       this.weights = new double[this.numGeometryArrays];
/*  241 */       this.weights[0] = 1.0D;
/*  242 */       int i = (this.geometryArrays[0]).vertexFormat;
/*      */ 
/*      */ 
/*      */       
/*  246 */       int j = this.geometryArrays[0].getTexCoordSetCount();
/*  247 */       if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained) {
/*  248 */         this.Mcoord = new float[this.geometryArrays[0].getNumCoordCount() * 3];
/*      */         
/*  250 */         if ((i & 0xC) == 4) {
/*  251 */           this.Mcolor = new float[this.geometryArrays[0].getNumColorCount() * 3];
/*  252 */         } else if ((i & 0xC) == 12) {
/*  253 */           this.Mcolor = new float[this.geometryArrays[0].getNumColorCount() * 4];
/*      */         } 
/*  255 */         this.MtexCoord = new float[j][];
/*  256 */         if ((i & 0x2) != 0)
/*  257 */           this.Mnormal = new float[this.geometryArrays[0].getNumNormalCount() * 3]; 
/*  258 */         for (byte b1 = 0; b1 < j; b1++) {
/*  259 */           if ((i & 0x20) != 0) {
/*  260 */             this.MtexCoord[b1] = new float[this.geometryArrays[0].getNumTexCoordCount(b1) * 2];
/*  261 */           } else if ((i & 0x40) != 0) {
/*  262 */             this.MtexCoord[b1] = new float[this.geometryArrays[0].getNumTexCoordCount(b1) * 3];
/*  263 */           } else if ((i & 0x400) != 0) {
/*  264 */             this.MtexCoord[b1] = new float[this.geometryArrays[0].getNumTexCoordCount(b1) * 4];
/*      */           } 
/*      */         } 
/*      */       } else {
/*  268 */         this.Mcoord = new float[(this.geometryArrays[0]).validVertexCount * 3];
/*      */         
/*  270 */         if ((i & 0xC) == 4) {
/*  271 */           this.Mcolor = new float[(this.geometryArrays[0]).validVertexCount * 3];
/*  272 */         } else if ((i & 0xC) == 12) {
/*  273 */           this.Mcolor = new float[(this.geometryArrays[0]).validVertexCount * 4];
/*      */         } 
/*  275 */         this.MtexCoord = new float[j][];
/*  276 */         if ((i & 0x2) != 0) {
/*  277 */           this.Mnormal = new float[(this.geometryArrays[0]).validVertexCount * 3];
/*      */         }
/*  279 */         for (byte b1 = 0; b1 < j; b1++) {
/*  280 */           if ((i & 0x20) != 0) {
/*  281 */             this.MtexCoord[b1] = new float[(this.geometryArrays[0]).validVertexCount * 2];
/*  282 */           } else if ((i & 0x40) != 0) {
/*  283 */             this.MtexCoord[b1] = new float[(this.geometryArrays[0]).validVertexCount * 3];
/*  284 */           } else if ((i & 0x400) != 0) {
/*  285 */             this.MtexCoord[b1] = new float[(this.geometryArrays[0]).validVertexCount * 4];
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  291 */     initMorphedGeometry();
/*      */     
/*  293 */     if (this.source.isLive()) {
/*      */       
/*  295 */       Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */       
/*  297 */       shape3DRetained.setMorphGeometry(this.morphedGeometryArray, this.mirrorShape3D);
/*      */       
/*  299 */       J3dMessage j3dMessage = null;
/*  300 */       j3dMessage = new J3dMessage();
/*  301 */       j3dMessage.type = 16;
/*  302 */       j3dMessage.threads = 8256;
/*      */ 
/*      */       
/*  305 */       if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained)
/*  306 */         j3dMessage.threads |= 0x400; 
/*  307 */       j3dMessage.args[0] = this;
/*  308 */       j3dMessage.args[1] = new Integer(1);
/*      */       
/*  310 */       j3dMessage.args[3] = Shape3DRetained.getGeomAtomsArray(this.mirrorShape3D);
/*  311 */       j3dMessage.universe = this.universe;
/*  312 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */       
/*  314 */       if (this.boundsAutoCompute) {
/*  315 */         GeometryArrayRetained geometryArrayRetained1 = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/*      */         
/*  317 */         geometryArrayRetained1.incrComputeGeoBounds();
/*  318 */         geometryArrayRetained1.decrComputeGeoBounds();
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
/*  332 */   GeometryArray getGeometryArray(int paramInt) { return (GeometryArray)(this.geometryArrays[paramInt]).source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAppearance(Appearance paramAppearance) {
/*  340 */     boolean bool = false;
/*      */     
/*  342 */     if (((Morph)this.source).isLive()) {
/*      */       
/*  344 */       if (this.appearance != null) {
/*  345 */         this.appearance.clearLive(this.refCount);
/*  346 */         for (int i = this.mirrorShape3D.size() - 1; i >= 0; i--) {
/*  347 */           this.appearance.removeAMirrorUser((Shape3DRetained)this.mirrorShape3D.get(i));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  352 */       if (paramAppearance != null) {
/*  353 */         ((AppearanceRetained)paramAppearance.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  354 */         this.appearance = (AppearanceRetained)paramAppearance.retained;
/*  355 */         int i = this.mirrorShape3D.size();
/*  356 */         for (byte b1 = 0; b1 < i; b1++) {
/*  357 */           this.appearance.addAMirrorUser((Shape3DRetained)this.mirrorShape3D.get(b1));
/*      */         }
/*  359 */         if (this.appearance.renderingAttributes != null && this.visible != this.appearance.renderingAttributes.visible)
/*      */         {
/*  361 */           this.visible = this.appearance.renderingAttributes.visible;
/*  362 */           bool = true;
/*      */         }
/*      */       
/*      */       }
/*  366 */       else if (!this.visible) {
/*  367 */         this.visible = true;
/*  368 */         bool = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  373 */       byte b = 0;
/*      */       
/*  375 */       if (bool) {
/*  376 */         b = 2;
/*      */       } else {
/*  378 */         b = 1;
/*  379 */       }  J3dMessage[] arrayOfJ3dMessage = new J3dMessage[b];
/*  380 */       arrayOfJ3dMessage[0] = new J3dMessage();
/*  381 */       (arrayOfJ3dMessage[0]).threads = 4224;
/*      */       
/*  383 */       (arrayOfJ3dMessage[0]).type = 16;
/*  384 */       (arrayOfJ3dMessage[0]).universe = this.universe;
/*  385 */       (arrayOfJ3dMessage[0]).args[0] = this;
/*  386 */       (arrayOfJ3dMessage[0]).args[1] = new Integer(2);
/*  387 */       Shape3DRetained[] arrayOfShape3DRetained = new Shape3DRetained[this.mirrorShape3D.size()];
/*  388 */       this.mirrorShape3D.toArray(arrayOfShape3DRetained);
/*  389 */       (arrayOfJ3dMessage[0]).args[2] = arrayOfShape3DRetained;
/*  390 */       Object[] arrayOfObject = new Object[2];
/*  391 */       if (paramAppearance == null) {
/*  392 */         arrayOfObject[0] = null;
/*      */       } else {
/*      */         
/*  395 */         arrayOfObject[0] = this.appearance.mirror;
/*      */       } 
/*  397 */       arrayOfObject[1] = new Integer(this.changedFrequent);
/*  398 */       (arrayOfJ3dMessage[0]).args[3] = arrayOfObject;
/*  399 */       (arrayOfJ3dMessage[0]).args[4] = Shape3DRetained.getGeomAtomsArray(this.mirrorShape3D);
/*  400 */       if (bool) {
/*  401 */         arrayOfJ3dMessage[1] = new J3dMessage();
/*  402 */         (arrayOfJ3dMessage[1]).threads = 64;
/*  403 */         (arrayOfJ3dMessage[1]).type = 24;
/*  404 */         (arrayOfJ3dMessage[1]).universe = this.universe;
/*  405 */         (arrayOfJ3dMessage[1]).args[0] = this;
/*  406 */         (arrayOfJ3dMessage[1]).args[1] = new Integer(2);
/*  407 */         (arrayOfJ3dMessage[1]).args[2] = this.visible ? Boolean.TRUE : Boolean.FALSE;
/*  408 */         (arrayOfJ3dMessage[1]).args[3] = (arrayOfJ3dMessage[0]).args[4];
/*      */       } 
/*  410 */       VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*      */     
/*      */     }
/*  413 */     else if (paramAppearance == null) {
/*  414 */       this.appearance = null;
/*      */     } else {
/*  416 */       this.appearance = (AppearanceRetained)paramAppearance.retained;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  426 */   Appearance getAppearance() { return (this.appearance == null) ? null : (Appearance)this.appearance.source; }
/*      */ 
/*      */ 
/*      */   
/*      */   void setAppearanceOverrideEnable(boolean paramBoolean) {
/*  431 */     if (((Morph)this.source).isLive()) {
/*      */ 
/*      */       
/*  434 */       J3dMessage j3dMessage = new J3dMessage();
/*  435 */       j3dMessage.threads = 4224;
/*      */       
/*  437 */       j3dMessage.type = 16;
/*  438 */       j3dMessage.universe = this.universe;
/*  439 */       j3dMessage.args[0] = this;
/*  440 */       j3dMessage.args[1] = new Integer(16);
/*  441 */       Shape3DRetained[] arrayOfShape3DRetained = new Shape3DRetained[this.mirrorShape3D.size()];
/*  442 */       this.mirrorShape3D.toArray(arrayOfShape3DRetained);
/*  443 */       j3dMessage.args[2] = arrayOfShape3DRetained;
/*  444 */       Object[] arrayOfObject = new Object[2];
/*  445 */       if (paramBoolean) {
/*  446 */         arrayOfObject[0] = Boolean.TRUE;
/*      */       } else {
/*      */         
/*  449 */         arrayOfObject[0] = Boolean.FALSE;
/*      */       } 
/*  451 */       arrayOfObject[1] = new Integer(this.changedFrequent);
/*  452 */       j3dMessage.args[3] = arrayOfObject;
/*  453 */       j3dMessage.args[4] = Shape3DRetained.getGeomAtomsArray(this.mirrorShape3D);
/*  454 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*  456 */     this.appearanceOverrideEnable = paramBoolean;
/*      */   }
/*      */ 
/*      */   
/*  460 */   boolean getAppearanceOverrideEnable() { return this.appearanceOverrideEnable; }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersect(PickInfo paramPickInfo, PickShape paramPickShape, int paramInt) {
/*  465 */     Transform3D transform3D1 = paramPickInfo.getLocalToVWorldRef();
/*      */     
/*  467 */     Transform3D transform3D2 = new Transform3D();
/*  468 */     transform3D2.invert(transform3D1);
/*  469 */     PickShape pickShape = paramPickShape.transform(transform3D2);
/*      */     
/*  471 */     GeometryRetained geometryRetained = (GeometryRetained)this.morphedGeometryArray.retained;
/*      */     
/*  473 */     if (geometryRetained.mirrorGeometry != null) {
/*  474 */       geometryRetained = geometryRetained.mirrorGeometry;
/*      */     }
/*      */     
/*  477 */     if ((paramInt & 0x8) == 0 && (paramInt & 0x10) == 0 && (paramInt & 0x20) == 0 && (paramInt & 0x40) == 0)
/*      */     {
/*      */ 
/*      */       
/*  481 */       return geometryRetained.intersect(pickShape, null, 0, null, null, 0);
/*      */     }
/*  483 */     Point3d point3d1 = new Point3d();
/*  484 */     Point3d point3d2 = new Point3d();
/*  485 */     Point3d point3d3 = new Point3d();
/*      */     
/*  487 */     if (geometryRetained.intersect(pickShape, paramPickInfo, paramInt, point3d2, geometryRetained, 0)) {
/*      */       
/*  489 */       point3d3.set(point3d2);
/*  490 */       transform3D1.transform(point3d3);
/*  491 */       double d = paramPickShape.distance(point3d3);
/*      */       
/*  493 */       if ((paramInt & 0x10) != 0) {
/*  494 */         paramPickInfo.setClosestDistance(d);
/*      */       }
/*  496 */       if ((paramInt & 0x8) != 0) {
/*  497 */         paramPickInfo.setClosestIntersectionPoint(point3d2);
/*      */       }
/*  499 */       return true;
/*      */     } 
/*      */     
/*  502 */     return false;
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
/*      */   boolean intersect(SceneGraphPath paramSceneGraphPath, PickShape paramPickShape, double[] paramArrayOfDouble) {
/*  521 */     PickInfo pickInfo = new PickInfo();
/*      */     
/*  523 */     Transform3D transform3D = paramSceneGraphPath.getTransform();
/*  524 */     if (transform3D == null) {
/*  525 */       throw new RuntimeException(J3dI18N.getString("MorphRetained5"));
/*      */     }
/*      */     
/*  528 */     pickInfo.setLocalToVWorldRef(transform3D);
/*      */     
/*  530 */     if (paramArrayOfDouble == null)
/*      */     {
/*  532 */       return intersect(pickInfo, paramPickShape, 0);
/*      */     }
/*      */     
/*  535 */     byte b = 16;
/*  536 */     if (intersect(pickInfo, paramPickShape, b)) {
/*  537 */       paramArrayOfDouble[0] = pickInfo.getClosestDistance();
/*  538 */       return true;
/*      */     } 
/*      */     
/*  541 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setWeights(double[] paramArrayOfDouble) {
/*  551 */     double d = 0.0D;
/*      */     
/*  553 */     if (paramArrayOfDouble.length != this.numGeometryArrays)
/*  554 */       throw new IllegalArgumentException(J3dI18N.getString("MorphRetained7")); 
/*      */     int i;
/*  556 */     for (i = paramArrayOfDouble.length - 1; i >= 0; i--) {
/*  557 */       d += paramArrayOfDouble[i];
/*      */     }
/*      */     
/*  560 */     if (Math.abs(d - 1.0D) > 1.0E-4D) {
/*  561 */       throw new IllegalArgumentException(J3dI18N.getString("MorphRetained8"));
/*      */     }
/*      */     
/*  564 */     for (i = this.numGeometryArrays - 1; i >= 0; i--) {
/*  565 */       this.weights[i] = paramArrayOfDouble[i];
/*      */     }
/*      */     
/*  568 */     if (this.source.isLive()) {
/*  569 */       ((GeometryArrayRetained)this.morphedGeometryArray.retained).updateData(this);
/*  570 */       J3dMessage j3dMessage = null;
/*  571 */       j3dMessage = new J3dMessage();
/*  572 */       j3dMessage.type = 16;
/*  573 */       j3dMessage.threads = 8256;
/*      */ 
/*      */       
/*  576 */       if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained)
/*  577 */         j3dMessage.threads |= 0x400; 
/*  578 */       j3dMessage.args[0] = this;
/*  579 */       j3dMessage.args[1] = new Integer(1);
/*  580 */       j3dMessage.args[3] = Shape3DRetained.getGeomAtomsArray(this.mirrorShape3D);
/*  581 */       j3dMessage.universe = this.universe;
/*  582 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  592 */   double[] getWeights() { return (double[])this.weights.clone(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds getBounds() {
/*  600 */     if (this.boundsAutoCompute) {
/*  601 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/*      */       
/*  603 */       if (geometryArrayRetained != null) {
/*  604 */         synchronized (geometryArrayRetained.geoBounds) {
/*  605 */           return (Bounds)geometryArrayRetained.geoBounds.clone();
/*      */         } 
/*      */       }
/*  608 */       return null;
/*      */     } 
/*      */     
/*  611 */     return super.getBounds();
/*      */   }
/*      */ 
/*      */   
/*      */   Bounds getEffectiveBounds() {
/*  616 */     if (this.boundsAutoCompute) {
/*  617 */       return getBounds();
/*      */     }
/*      */     
/*  620 */     return super.getEffectiveBounds();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void computeCombineBounds(Bounds paramBounds) {
/*  630 */     if (this.boundsAutoCompute) {
/*  631 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/*      */       
/*  633 */       if (geometryArrayRetained != null) {
/*  634 */         synchronized (geometryArrayRetained.geoBounds) {
/*  635 */           paramBounds.combine(geometryArrayRetained.geoBounds);
/*      */         } 
/*      */       }
/*      */     } else {
/*      */       
/*  640 */       synchronized (this.localBounds) {
/*  641 */         paramBounds.combine(this.localBounds);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  648 */   int getNumGeometryArrays() { return this.numGeometryArrays; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMorphedGeometryArray(GeometryArrayRetained paramGeometryArrayRetained, boolean paramBoolean) {
/*  654 */     if (this.numGeometryArrays > 0)
/*      */     {
/*  656 */       if (this.geometryArrays[false] != paramGeometryArrayRetained) {
/*  657 */         doErrorCheck(paramGeometryArrayRetained, this.geometryArrays[0]);
/*      */ 
/*      */       
/*      */       }
/*  661 */       else if (this.numGeometryArrays > 1) {
/*  662 */         doErrorCheck(paramGeometryArrayRetained, this.geometryArrays[1]);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  669 */     ((GeometryArrayRetained)this.morphedGeometryArray.retained).updateData(this);
/*      */     
/*  671 */     if (this.boundsAutoCompute && paramBoolean) {
/*  672 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/*  673 */       geometryArrayRetained.incrComputeGeoBounds();
/*  674 */       geometryArrayRetained.decrComputeGeoBounds();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateData(Geometry paramGeometry) {
/*  685 */     boolean bool1 = false;
/*  686 */     boolean bool2 = false;
/*  687 */     int m = 0;
/*  688 */     float[] arrayOfFloat1 = new float[3], arrayOfFloat2 = new float[4];
/*  689 */     float[] arrayOfFloat3 = new float[3], arrayOfFloat4 = new float[3];
/*      */     
/*  691 */     int j = (this.geometryArrays[0]).vertexFormat;
/*  692 */     int k = (this.geometryArrays[0]).geoType;
/*  693 */     m = this.geometryArrays[0].getTexCoordSetCount();
/*      */ 
/*      */ 
/*      */     
/*  697 */     byte b1 = 0, b2 = 0, b3 = 0; boolean bool3 = false;
/*  698 */     int n = 0;
/*  699 */     if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained) {
/*  700 */       n = this.geometryArrays[0].getNumCoordCount();
/*      */     } else {
/*  702 */       n = (this.geometryArrays[0]).validVertexCount;
/*      */     } 
/*      */     int i;
/*  705 */     for (i = 0; i < n; i++) {
/*  706 */       this.Mcoord[b1++] = 0.0F; this.Mcoord[b1++] = 0.0F; this.Mcoord[b1++] = 0.0F;
/*      */     } 
/*      */ 
/*      */     
/*  710 */     if ((j & 0x4) != 0) {
/*  711 */       if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained) {
/*  712 */         n = this.geometryArrays[0].getNumColorCount();
/*      */       } else {
/*  714 */         n = (this.geometryArrays[0]).validVertexCount;
/*      */       } 
/*  716 */       for (i = 0; i < n; i++) {
/*  717 */         if ((j & 0xC) == 4) {
/*  718 */           this.Mcolor[b3++] = 0.0F; this.Mcolor[b3++] = 0.0F; this.Mcolor[b3++] = 0.0F;
/*      */         }
/*  720 */         else if ((j & 0xC) == 12) {
/*  721 */           this.Mcolor[b3++] = 0.0F; this.Mcolor[b3++] = 0.0F; this.Mcolor[b3++] = 0.0F; this.Mcolor[b3++] = 0.0F;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  726 */     if ((j & 0x2) != 0) {
/*  727 */       if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained) {
/*  728 */         n = this.geometryArrays[0].getNumNormalCount();
/*      */       } else {
/*  730 */         n = (this.geometryArrays[0]).validVertexCount;
/*      */       } 
/*  732 */       for (i = 0; i < n; i++) {
/*  733 */         this.Mnormal[b2++] = 0.0F; this.Mnormal[b2++] = 0.0F; this.Mnormal[b2++] = 0.0F;
/*      */       } 
/*      */     } 
/*      */     
/*  737 */     if ((j & 0x460) != 0) {
/*  738 */       for (byte b = 0; b < m; b++) {
/*  739 */         if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained) {
/*  740 */           n = this.geometryArrays[0].getNumTexCoordCount(b);
/*      */         } else {
/*  742 */           n = (this.geometryArrays[0]).validVertexCount;
/*      */         } 
/*  744 */         byte b4 = 0;
/*  745 */         for (i = 0; i < n; i++) {
/*  746 */           this.MtexCoord[b][b4++] = 0.0F; this.MtexCoord[b][b4++] = 0.0F;
/*  747 */           if ((j & 0x40) != 0) {
/*  748 */             this.MtexCoord[b][b4++] = 0.0F;
/*  749 */           } else if ((j & 0x400) != 0) {
/*  750 */             this.MtexCoord[b][b4++] = 0.0F;
/*  751 */             this.MtexCoord[b][b4++] = 0.0F;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  757 */     if ((j & 0x80) == 0) {
/*  758 */       n = 0;
/*  759 */       for (byte b = 0; b < this.numGeometryArrays; b++) {
/*  760 */         double d = this.weights[b];
/*  761 */         if (d != 0.0D) {
/*  762 */           b1 = 0; b2 = 0; b3 = 0;
/*  763 */           int i1 = 0;
/*  764 */           if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  765 */             i1 = 0;
/*  766 */             n = this.geometryArrays[b].getNumCoordCount();
/*      */           } else {
/*      */             
/*  769 */             i1 = this.geometryArrays[b].getInitialVertexIndex();
/*  770 */             n = (this.geometryArrays[b]).validVertexCount;
/*      */           } 
/*  772 */           int i2 = i1 + n;
/*  773 */           for (i = i1; i < i2; i++) {
/*  774 */             this.geometryArrays[b].getCoordinate(i, arrayOfFloat1);
/*  775 */             this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat1[0] * d);
/*  776 */             this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat1[1] * d);
/*  777 */             this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat1[2] * d);
/*      */           } 
/*      */           
/*  780 */           if ((j & 0x4) != 0) {
/*  781 */             if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  782 */               n = this.geometryArrays[b].getNumColorCount();
/*      */             }
/*  784 */             i2 = i1 + n;
/*  785 */             for (i = i1; i < i2; i++) {
/*  786 */               this.geometryArrays[b].getColor(i, arrayOfFloat2);
/*  787 */               this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat2[0] * d);
/*  788 */               this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat2[1] * d);
/*  789 */               this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat2[2] * d);
/*  790 */               if ((j & 0x8) != 0)
/*  791 */                 this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat2[3] * d); 
/*      */             } 
/*      */           } 
/*  794 */           if ((j & 0x2) != 0) {
/*  795 */             if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  796 */               n = this.geometryArrays[b].getNumNormalCount();
/*      */             }
/*  798 */             i2 = i1 + n;
/*  799 */             for (i = i1; i < i2; i++) {
/*  800 */               this.geometryArrays[b].getNormal(i, arrayOfFloat3);
/*  801 */               this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat3[0] * d);
/*  802 */               this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat3[1] * d);
/*  803 */               this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat3[2] * d);
/*      */             } 
/*      */           } 
/*      */           
/*  807 */           if ((j & 0x460) != 0) {
/*  808 */             for (byte b4 = 0; b4 < m; b4++) {
/*  809 */               byte b5 = 0;
/*  810 */               if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  811 */                 n = this.geometryArrays[b].getNumTexCoordCount(i);
/*      */               }
/*  813 */               i2 = i1 + n;
/*  814 */               for (i = i1; i < i2; i++) {
/*  815 */                 this.geometryArrays[b].getTextureCoordinate(b4, i, arrayOfFloat4);
/*  816 */                 this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat4[0] * d);
/*  817 */                 this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat4[1] * d);
/*  818 */                 if ((j & 0x40) != 0) {
/*  819 */                   this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat4[2] * d);
/*  820 */                 } else if ((j & 0x400) != 0) {
/*  821 */                   this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat4[2] * d);
/*  822 */                   this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat4[3] * d);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  831 */       int i1 = 0, i2 = 0;
/*  832 */       if ((j & 0x460) != 0) {
/*  833 */         if ((j & 0x20) != 0) {
/*  834 */           i1 = 2;
/*  835 */         } else if ((j & 0x40) != 0) {
/*  836 */           i1 = 3;
/*      */         } else {
/*  838 */           i1 = 4;
/*      */         } 
/*      */       }
/*      */       
/*  842 */       if ((j & 0x4) != 0) {
/*  843 */         i2 = 3;
/*  844 */         if ((j & 0x8) != 0) {
/*  845 */           i2 = 4;
/*      */         }
/*      */       } 
/*  848 */       if ((j & 0x100) != 0) {
/*      */ 
/*      */ 
/*      */         
/*  852 */         int i3 = this.geometryArrays[0].stride();
/*  853 */         int i4 = this.geometryArrays[0].colorOffset();
/*  854 */         int i5 = this.geometryArrays[0].normalOffset();
/*  855 */         int i6 = this.geometryArrays[0].coordinateOffset();
/*  856 */         int i7 = 0;
/*      */         
/*  858 */         boolean bool = false;
/*  859 */         for (byte b = 0; b < this.numGeometryArrays; b++) {
/*  860 */           double d = this.weights[b];
/*  861 */           if (d != 0.0D) {
/*  862 */             int i8; b1 = 0; b2 = 0; b3 = 0; bool3 = false;
/*  863 */             float[] arrayOfFloat = this.geometryArrays[b].getInterleavedVertices();
/*  864 */             if ((j & 0x460) != 0) {
/*  865 */               for (byte b4 = 0; b4 < m; b4++) {
/*  866 */                 int i9; if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  867 */                   i9 = 0;
/*  868 */                   n = this.geometryArrays[b].getNumCoordCount();
/*      */                 } else {
/*      */                   
/*  871 */                   i9 = this.geometryArrays[b].getInitialVertexIndex();
/*  872 */                   n = (this.geometryArrays[b]).validVertexCount;
/*      */                 } 
/*  874 */                 i7 = i9 * i3 + b4 * i1;
/*  875 */                 byte b5 = 0;
/*  876 */                 for (i = 0; i < n; i++, i7 += i3) {
/*  877 */                   this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat[i7] * d);
/*  878 */                   this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat[i7 + 1] * d);
/*  879 */                   if ((j & 0x40) != 0) {
/*  880 */                     this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat[i7 + 2] * d);
/*  881 */                   } else if ((j & 0x400) != 0) {
/*  882 */                     this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat[i7 + 2] * d);
/*  883 */                     this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat[i7 + 3] * d);
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             }
/*      */             
/*  889 */             if ((j & 0x4) != 0) {
/*  890 */               int i9; if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  891 */                 i9 = 0;
/*  892 */                 n = this.geometryArrays[b].getNumCoordCount();
/*      */               } else {
/*      */                 
/*  895 */                 i9 = this.geometryArrays[b].getInitialVertexIndex();
/*  896 */                 n = (this.geometryArrays[b]).validVertexCount;
/*      */               } 
/*  898 */               i7 = i9 * i3 + i4;
/*  899 */               for (i = 0; i < n; i++, i7 += i3) {
/*  900 */                 this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat[i7] * d);
/*  901 */                 this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat[i7 + 1] * d);
/*  902 */                 this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat[i7 + 2] * d);
/*  903 */                 if ((j & 0x8) != 0) {
/*  904 */                   this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat[i7 + 3] * d);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */             
/*  909 */             if ((j & 0x2) != 0) {
/*  910 */               int i9; if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  911 */                 i9 = 0;
/*  912 */                 n = this.geometryArrays[b].getNumCoordCount();
/*      */               } else {
/*      */                 
/*  915 */                 i9 = this.geometryArrays[b].getInitialVertexIndex();
/*  916 */                 n = (this.geometryArrays[b]).validVertexCount;
/*      */               } 
/*  918 */               i7 = i9 * i3 + i5;
/*  919 */               for (i = 0; i < n; i++, i7 += i3) {
/*  920 */                 this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat[i7] * d);
/*  921 */                 this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat[i7 + 1] * d);
/*  922 */                 this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat[i7 + 2] * d);
/*      */               } 
/*      */             } 
/*  925 */             if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  926 */               i8 = 0;
/*  927 */               n = this.geometryArrays[b].getNumCoordCount();
/*      */             } else {
/*      */               
/*  930 */               i8 = this.geometryArrays[b].getInitialVertexIndex();
/*  931 */               n = (this.geometryArrays[b]).validVertexCount;
/*      */             } 
/*  933 */             i7 = i8 * i3 + i6;
/*  934 */             for (i = 0; i < n; i++, i7 += i3) {
/*  935 */               this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat[i7] * d);
/*  936 */               this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat[i7 + 1] * d);
/*  937 */               this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat[i7 + 2] * d);
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/*  944 */         float f = 0.003921569F;
/*  945 */         for (byte b = 0; b < this.numGeometryArrays; b++) {
/*  946 */           double d = this.weights[b];
/*  947 */           if (d != 0.0D) {
/*  948 */             Point3d[] arrayOfPoint3d; Point3f[] arrayOfPoint3f; double[] arrayOfDouble; float[] arrayOfFloat; int i3; if ((j & 0x460) != 0) {
/*  949 */               byte b4; switch ((this.geometryArrays[b]).vertexType & 0x7000) {
/*      */                 case 4096:
/*  951 */                   for (b4 = 0; b4 < m; b4++) {
/*  952 */                     int i4; float[] arrayOfFloat5 = this.geometryArrays[b].getTexCoordRefFloat(b4);
/*  953 */                     if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  954 */                       i4 = 0;
/*  955 */                       n = this.geometryArrays[b].getNumTexCoordCount(b4);
/*      */                     } else {
/*      */                       
/*  958 */                       i4 = this.geometryArrays[b].getInitialTexCoordIndex(b4);
/*  959 */                       n = (this.geometryArrays[b]).validVertexCount;
/*      */                     } 
/*  961 */                     i4 *= i1;
/*  962 */                     byte b5 = 0;
/*  963 */                     for (i = 0; i < n; i++) {
/*  964 */                       this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat5[i4++] * d);
/*  965 */                       this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat5[i4++] * d);
/*  966 */                       if ((j & 0x40) != 0)
/*  967 */                         this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + arrayOfFloat5[i4++] * d); 
/*      */                     } 
/*      */                   } 
/*      */                   break;
/*      */                 case 8192:
/*  972 */                   for (b4 = 0; b4 < m; b4++) {
/*  973 */                     int i4; byte b5 = 0;
/*  974 */                     float[] arrayOfFloat5 = this.geometryArrays[b].getTexCoordRefFloat(b4);
/*  975 */                     if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  976 */                       i4 = 0;
/*  977 */                       n = this.geometryArrays[b].getNumTexCoordCount(b4);
/*      */                     } else {
/*      */                       
/*  980 */                       i4 = this.geometryArrays[b].getInitialTexCoordIndex(b4);
/*  981 */                       n = (this.geometryArrays[b]).validVertexCount;
/*      */                     } 
/*  983 */                     TexCoord2f[] arrayOfTexCoord2f = this.geometryArrays[b].getTexCoordRef2f(b4);
/*  984 */                     for (i = 0; i < n; i++, i4++) {
/*  985 */                       this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + (arrayOfTexCoord2f[i4]).x * d);
/*  986 */                       this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + (arrayOfTexCoord2f[i4]).y * d);
/*      */                     } 
/*      */                   } 
/*      */                   break;
/*      */                 case 16384:
/*  991 */                   for (b4 = 0; b4 < m; b4++) {
/*  992 */                     int i4; byte b5 = 0;
/*  993 */                     TexCoord3f[] arrayOfTexCoord3f = this.geometryArrays[b].getTexCoordRef3f(b4);
/*  994 */                     if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/*  995 */                       i4 = 0;
/*  996 */                       n = this.geometryArrays[b].getNumTexCoordCount(b4);
/*      */                     } else {
/*      */                       
/*  999 */                       i4 = this.geometryArrays[b].getInitialTexCoordIndex(b4);
/* 1000 */                       n = (this.geometryArrays[b]).validVertexCount;
/*      */                     } 
/* 1002 */                     for (i = 0; i < n; i++, i4++) {
/* 1003 */                       this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + (arrayOfTexCoord3f[i4]).x * d);
/* 1004 */                       this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + (arrayOfTexCoord3f[i4]).y * d);
/* 1005 */                       this.MtexCoord[b4][b5++] = (float)(this.MtexCoord[b4][b5++] + (arrayOfTexCoord3f[i4]).z * d);
/*      */                     } 
/*      */                   } 
/*      */                   break;
/*      */               } 
/*      */             
/*      */             } 
/* 1012 */             if ((j & 0x4) != 0) {
/* 1013 */               Color4b[] arrayOfColor4b; Color3b[] arrayOfColor3b; Color4f[] arrayOfColor4f; Color3f[] arrayOfColor3f; byte[] arrayOfByte; float[] arrayOfFloat5; int i4; double d1 = f * d;
/* 1014 */               if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/* 1015 */                 i4 = 0;
/* 1016 */                 n = this.geometryArrays[b].getNumColorCount();
/*      */               } else {
/*      */                 
/* 1019 */                 i4 = this.geometryArrays[b].getInitialColorIndex();
/* 1020 */                 n = (this.geometryArrays[b]).validVertexCount;
/*      */               } 
/*      */               
/* 1023 */               switch ((this.geometryArrays[b]).vertexType & 0x3F0) {
/*      */                 case 16:
/* 1025 */                   arrayOfFloat5 = this.geometryArrays[b].getColorRefFloat();
/* 1026 */                   b3 = 0;
/* 1027 */                   i4 *= i2;
/* 1028 */                   for (i = 0; i < n; i++) {
/* 1029 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat5[i4++] * d);
/* 1030 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat5[i4++] * d);
/* 1031 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat5[i4++] * d);
/* 1032 */                     if ((j & 0x8) != 0)
/* 1033 */                       this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + arrayOfFloat5[i4++] * d); 
/*      */                   } 
/*      */                   break;
/*      */                 case 32:
/* 1037 */                   arrayOfByte = this.geometryArrays[b].getColorRefByte();
/* 1038 */                   b3 = 0;
/* 1039 */                   i4 *= i2;
/* 1040 */                   for (i = 0; i < n; i++) {
/* 1041 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfByte[i4++] & 0xFF) * d1);
/* 1042 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfByte[i4++] & 0xFF) * d1);
/* 1043 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfByte[i4++] & 0xFF) * d1);
/* 1044 */                     if ((j & 0x8) != 0) {
/* 1045 */                       this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfByte[i4++] & 0xFF) * d1);
/*      */                     }
/*      */                   } 
/*      */                   break;
/*      */                 case 64:
/* 1050 */                   arrayOfColor3f = this.geometryArrays[b].getColorRef3f();
/* 1051 */                   b3 = 0;
/* 1052 */                   for (i = 0; i < n; i++, i4++) {
/* 1053 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfColor3f[i4]).x * d);
/* 1054 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfColor3f[i4]).y * d);
/* 1055 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfColor3f[i4]).z * d);
/*      */                   } 
/*      */                   break;
/*      */                 case 128:
/* 1059 */                   arrayOfColor4f = this.geometryArrays[b].getColorRef4f();
/* 1060 */                   b3 = 0;
/* 1061 */                   for (i = 0; i < n; i++, i4++) {
/* 1062 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfColor4f[i4]).x * d);
/* 1063 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfColor4f[i4]).y * d);
/* 1064 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfColor4f[i4]).z * d);
/* 1065 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + (arrayOfColor4f[i4]).w * d);
/*      */                   } 
/*      */                   break;
/*      */                 case 256:
/* 1069 */                   arrayOfColor3b = this.geometryArrays[b].getColorRef3b();
/* 1070 */                   b3 = 0;
/* 1071 */                   for (i = 0; i < n; i++, i4++) {
/* 1072 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + ((arrayOfColor3b[i4]).x & 0xFF) * d1);
/* 1073 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + ((arrayOfColor3b[i4]).y & 0xFF) * d1);
/* 1074 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + ((arrayOfColor3b[i4]).z & 0xFF) * d1);
/*      */                   } 
/*      */                   break;
/*      */                 case 512:
/* 1078 */                   arrayOfColor4b = this.geometryArrays[b].getColorRef4b();
/* 1079 */                   b3 = 0;
/* 1080 */                   for (i = 0; i < n; i++, i4++) {
/* 1081 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + ((arrayOfColor4b[i4]).x & 0xFF) * d1);
/* 1082 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + ((arrayOfColor4b[i4]).y & 0xFF) * d1);
/* 1083 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + ((arrayOfColor4b[i4]).z & 0xFF) * d1);
/* 1084 */                     this.Mcolor[b3++] = (float)(this.Mcolor[b3++] + ((arrayOfColor4b[i4]).w & 0xFF) * d1);
/*      */                   } 
/*      */                   break;
/*      */               } 
/*      */             
/*      */             } 
/* 1090 */             if ((j & 0x2) != 0) {
/* 1091 */               Vector3f[] arrayOfVector3f; float[] arrayOfFloat5; int i4; b2 = 0;
/* 1092 */               if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/* 1093 */                 i4 = 0;
/* 1094 */                 n = this.geometryArrays[b].getNumNormalCount();
/*      */               } else {
/*      */                 
/* 1097 */                 i4 = this.geometryArrays[b].getInitialNormalIndex();
/* 1098 */                 n = (this.geometryArrays[b]).validVertexCount;
/*      */               } 
/* 1100 */               switch ((this.geometryArrays[b]).vertexType & 0xC00) {
/*      */                 case 1024:
/* 1102 */                   arrayOfFloat5 = this.geometryArrays[b].getNormalRefFloat();
/* 1103 */                   i4 *= 3;
/* 1104 */                   for (i = 0; i < n; i++) {
/* 1105 */                     this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat5[i4++] * d);
/* 1106 */                     this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat5[i4++] * d);
/* 1107 */                     this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + arrayOfFloat5[i4++] * d);
/*      */                   } 
/*      */                   break;
/*      */                 case 2048:
/* 1111 */                   arrayOfVector3f = this.geometryArrays[b].getNormalRef3f();
/* 1112 */                   for (i = 0; i < n; i++, i4++) {
/* 1113 */                     this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + (arrayOfVector3f[i4]).x * d);
/* 1114 */                     this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + (arrayOfVector3f[i4]).y * d);
/* 1115 */                     this.Mnormal[b2++] = (float)(this.Mnormal[b2++] + (arrayOfVector3f[i4]).z * d);
/*      */                   } 
/*      */                   break;
/*      */               } 
/*      */             
/*      */             } 
/* 1121 */             b1 = 0;
/* 1122 */             if (this.geometryArrays[b] instanceof IndexedGeometryArrayRetained) {
/* 1123 */               i3 = 0;
/* 1124 */               n = this.geometryArrays[b].getNumCoordCount();
/*      */             } else {
/*      */               
/* 1127 */               i3 = this.geometryArrays[b].getInitialCoordIndex();
/* 1128 */               n = (this.geometryArrays[b]).validVertexCount;
/*      */             } 
/* 1130 */             switch ((this.geometryArrays[b]).vertexType & 0xF) {
/*      */               case 1:
/* 1132 */                 arrayOfFloat = this.geometryArrays[b].getCoordRefFloat();
/* 1133 */                 i3 *= 3;
/* 1134 */                 for (i = 0; i < n; i++) {
/* 1135 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat[i3++] * d);
/* 1136 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat[i3++] * d);
/* 1137 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + arrayOfFloat[i3++] * d);
/*      */                 } 
/*      */                 break;
/*      */               case 2:
/* 1141 */                 arrayOfDouble = this.geometryArrays[b].getCoordRefDouble();
/* 1142 */                 i3 *= 3;
/* 1143 */                 for (i = 0; i < n; i++) {
/* 1144 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (float)arrayOfDouble[i3++] * d);
/* 1145 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (float)arrayOfDouble[i3++] * d);
/* 1146 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (float)arrayOfDouble[i3++] * d);
/*      */                 } 
/*      */                 break;
/*      */               case 4:
/* 1150 */                 arrayOfPoint3f = this.geometryArrays[b].getCoordRef3f();
/* 1151 */                 for (i = 0; i < n; i++, i3++) {
/* 1152 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (arrayOfPoint3f[i3]).x * d);
/* 1153 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (arrayOfPoint3f[i3]).y * d);
/* 1154 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (arrayOfPoint3f[i3]).z * d);
/*      */                 } 
/*      */                 break;
/*      */               case 8:
/* 1158 */                 arrayOfPoint3d = this.geometryArrays[b].getCoordRef3d();
/* 1159 */                 for (i = 0; i < n; i++, i3++) {
/* 1160 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (float)(arrayOfPoint3d[i3]).x * d);
/* 1161 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (float)(arrayOfPoint3d[i3]).y * d);
/* 1162 */                   this.Mcoord[b1++] = (float)(this.Mcoord[b1++] + (float)(arrayOfPoint3d[i3]).z * d);
/*      */                 } 
/*      */                 break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1173 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramGeometry.retained;
/*      */ 
/*      */     
/* 1176 */     geometryArrayRetained.setCoordRefFloat(this.Mcoord);
/*      */     
/* 1178 */     if ((j & 0x4) != 0) {
/* 1179 */       geometryArrayRetained.setColorRefFloat(this.Mcolor);
/*      */     }
/*      */     
/* 1182 */     if ((j & 0x2) != 0) {
/* 1183 */       geometryArrayRetained.setNormalRefFloat(this.Mnormal);
/*      */     }
/* 1185 */     if ((j & 0x460) != 0) {
/* 1186 */       for (byte b = 0; b < m; b++) {
/* 1187 */         geometryArrayRetained.setTexCoordRefFloat(b, this.MtexCoord[b]);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/* 1195 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 1196 */     Shape3DRetained[] arrayOfShape3DRetained = (Shape3DRetained[])paramArrayOfObject[2];
/* 1197 */     if ((i & 0x2) != 0) {
/* 1198 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[3];
/* 1199 */       int k = ((Integer)arrayOfObject[1]).intValue();
/* 1200 */       for (int j = arrayOfShape3DRetained.length - 1; j >= 0; j--) {
/* 1201 */         (arrayOfShape3DRetained[j]).appearance = (AppearanceRetained)arrayOfObject[0];
/* 1202 */         (arrayOfShape3DRetained[j]).changedFrequent = k;
/*      */       } 
/*      */     } 
/* 1205 */     if ((i & 0x10) != 0) {
/* 1206 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[3];
/* 1207 */       int k = ((Integer)arrayOfObject[1]).intValue();
/* 1208 */       System.err.println("ChangedFrequent = " + this.changedFrequent);
/* 1209 */       for (int j = arrayOfShape3DRetained.length - 1; j >= 0; j--) {
/* 1210 */         (arrayOfShape3DRetained[j]).appearanceOverrideEnable = ((Boolean)arrayOfObject[0]).booleanValue();
/* 1211 */         (arrayOfShape3DRetained[j]).changedFrequent = k;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(SetLiveState paramSetLiveState) {
/* 1222 */     ArrayList arrayList = new ArrayList();
/*      */     
/* 1224 */     int i = this.refCount;
/*      */     
/* 1226 */     doSetLive(paramSetLiveState);
/* 1227 */     this.nodeId = this.universe.getNodeId();
/*      */     
/*      */     byte b;
/* 1230 */     for (b = 0; b < this.numGeometryArrays; b++) {
/* 1231 */       synchronized ((this.geometryArrays[b]).liveStateLock) {
/* 1232 */         this.geometryArrays[b].setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/*      */         
/* 1234 */         if (i <= 0) {
/* 1235 */           this.geometryArrays[b].addMorphUser(this);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1240 */     if (this.morphedGeometryArray == null) {
/* 1241 */       initMorphedGeometry();
/*      */     }
/*      */     
/* 1244 */     ((GeometryArrayRetained)this.morphedGeometryArray.retained).setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/*      */     
/* 1246 */     if (this.boundsAutoCompute) {
/* 1247 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/*      */       
/* 1249 */       geometryArrayRetained.incrComputeGeoBounds();
/* 1250 */       geometryArrayRetained.decrComputeGeoBounds();
/* 1251 */       this.localBounds.setWithLock(geometryArrayRetained.geoBounds);
/*      */     } 
/*      */ 
/*      */     
/* 1255 */     if (this.inSharedGroup) {
/* 1256 */       for (b = 0; b < paramSetLiveState.keys.length; b++) {
/* 1257 */         Shape3DRetained shape3DRetained = new Shape3DRetained();
/* 1258 */         shape3DRetained.key = paramSetLiveState.keys[b];
/* 1259 */         shape3DRetained.localToVworld = new Transform3D[1][];
/* 1260 */         shape3DRetained.localToVworldIndex = new int[1][];
/*      */ 
/*      */         
/* 1263 */         int j = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */         
/* 1265 */         if (j < 0) {
/* 1266 */           System.err.println("MorphRetained : Can't find hashKey");
/*      */         }
/*      */         
/* 1269 */         shape3DRetained.localToVworld[0] = this.localToVworld[j];
/* 1270 */         shape3DRetained.localToVworldIndex[0] = this.localToVworldIndex[j];
/* 1271 */         shape3DRetained.branchGroupPath = (BranchGroupRetained[])this.branchGroupPaths.get(j);
/* 1272 */         shape3DRetained.isPickable = paramSetLiveState.pickable[b];
/* 1273 */         shape3DRetained.isCollidable = paramSetLiveState.collidable[b];
/*      */         
/* 1275 */         shape3DRetained.initMirrorShape3D(paramSetLiveState, this, j);
/* 1276 */         this.mirrorShape3D.add(j, shape3DRetained);
/*      */         
/* 1278 */         arrayList.add(shape3DRetained);
/*      */         
/* 1280 */         if (paramSetLiveState.lights != null) {
/* 1281 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.lights.get(j);
/* 1282 */           if (arrayList1 != null) {
/* 1283 */             for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1284 */               shape3DRetained.addLight((LightRetained)arrayList1.get(b1));
/*      */             }
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1290 */         if (paramSetLiveState.fogs != null) {
/* 1291 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.fogs.get(j);
/* 1292 */           if (arrayList1 != null) {
/* 1293 */             for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1294 */               shape3DRetained.addFog((FogRetained)arrayList1.get(b1));
/*      */             }
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1300 */         if (paramSetLiveState.modelClips != null) {
/* 1301 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.modelClips.get(j);
/* 1302 */           if (arrayList1 != null) {
/* 1303 */             for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1304 */               shape3DRetained.addModelClip((ModelClipRetained)arrayList1.get(b1));
/*      */             }
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1310 */         if (paramSetLiveState.altAppearances != null) {
/* 1311 */           ArrayList arrayList1 = (ArrayList)paramSetLiveState.altAppearances.get(j);
/* 1312 */           if (arrayList1 != null) {
/* 1313 */             for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1314 */               shape3DRetained.addAltApp((AlternateAppearanceRetained)arrayList1.get(b1));
/*      */             }
/*      */           }
/*      */         } 
/*      */         
/* 1319 */         if (paramSetLiveState.viewLists != null) {
/* 1320 */           shape3DRetained.viewList = (ArrayList)paramSetLiveState.viewLists.get(b);
/*      */         } else {
/* 1322 */           shape3DRetained.viewList = null;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1327 */         GeometryAtom geometryAtom = Shape3DRetained.getGeomAtom(shape3DRetained);
/*      */ 
/*      */         
/* 1330 */         paramSetLiveState.nodeList.add(geometryAtom);
/*      */         
/* 1332 */         if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null)
/*      */         {
/* 1334 */           paramSetLiveState.transformTargets[b].addNode(geometryAtom, 0);
/*      */         }
/* 1336 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b] != null) {
/*      */           
/* 1338 */           paramSetLiveState.switchTargets[b].addNode(shape3DRetained, 0);
/* 1339 */           shape3DRetained.closestSwitchParent = paramSetLiveState.closestSwitchParents[b];
/* 1340 */           shape3DRetained.closestSwitchIndex = paramSetLiveState.closestSwitchIndices[b];
/*      */         } 
/* 1342 */         shape3DRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(j);
/*      */       } 
/*      */     } else {
/*      */       
/* 1346 */       Shape3DRetained shape3DRetained = new Shape3DRetained();
/* 1347 */       shape3DRetained.localToVworld = new Transform3D[1][];
/* 1348 */       shape3DRetained.localToVworldIndex = new int[1][];
/* 1349 */       shape3DRetained.localToVworld[0] = this.localToVworld[0];
/* 1350 */       shape3DRetained.localToVworldIndex[0] = this.localToVworldIndex[0];
/* 1351 */       shape3DRetained.branchGroupPath = (BranchGroupRetained[])this.branchGroupPaths.get(0);
/* 1352 */       shape3DRetained.isPickable = paramSetLiveState.pickable[0];
/* 1353 */       shape3DRetained.isCollidable = paramSetLiveState.collidable[0];
/* 1354 */       shape3DRetained.initMirrorShape3D(paramSetLiveState, this, 0);
/* 1355 */       this.mirrorShape3D.add(shape3DRetained);
/*      */       
/* 1357 */       arrayList.add(shape3DRetained);
/*      */       
/* 1359 */       if (paramSetLiveState.lights != null) {
/* 1360 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.lights.get(0);
/* 1361 */         if (arrayList1 != null) {
/* 1362 */           for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1363 */             shape3DRetained.addLight((LightRetained)arrayList1.get(b1));
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1369 */       if (paramSetLiveState.fogs != null) {
/* 1370 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.fogs.get(0);
/* 1371 */         if (arrayList1 != null) {
/* 1372 */           for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1373 */             shape3DRetained.addFog((FogRetained)arrayList1.get(b1));
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1379 */       if (paramSetLiveState.modelClips != null) {
/* 1380 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.modelClips.get(0);
/* 1381 */         if (arrayList1 != null) {
/* 1382 */           for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1383 */             shape3DRetained.addModelClip((ModelClipRetained)arrayList1.get(b1));
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1389 */       if (paramSetLiveState.altAppearances != null) {
/* 1390 */         ArrayList arrayList1 = (ArrayList)paramSetLiveState.altAppearances.get(0);
/* 1391 */         if (arrayList1 != null) {
/* 1392 */           for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 1393 */             shape3DRetained.addAltApp((AlternateAppearanceRetained)arrayList1.get(b1));
/*      */           }
/*      */         }
/*      */       } 
/*      */       
/* 1398 */       if (paramSetLiveState.viewLists != null) {
/* 1399 */         shape3DRetained.viewList = (ArrayList)paramSetLiveState.viewLists.get(0);
/*      */       } else {
/* 1401 */         shape3DRetained.viewList = null;
/*      */       } 
/*      */ 
/*      */       
/* 1405 */       GeometryAtom geometryAtom = Shape3DRetained.getGeomAtom(shape3DRetained);
/*      */ 
/*      */       
/* 1408 */       paramSetLiveState.nodeList.add(geometryAtom);
/*      */       
/* 1410 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null)
/*      */       {
/* 1412 */         paramSetLiveState.transformTargets[0].addNode(geometryAtom, 0);
/*      */       }
/* 1414 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null) {
/*      */         
/* 1416 */         paramSetLiveState.switchTargets[0].addNode(shape3DRetained, 0);
/* 1417 */         shape3DRetained.closestSwitchParent = paramSetLiveState.closestSwitchParents[0];
/* 1418 */         shape3DRetained.closestSwitchIndex = paramSetLiveState.closestSwitchIndices[0];
/*      */       } 
/* 1420 */       shape3DRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*      */     } 
/* 1422 */     if (this.appearance != null) {
/* 1423 */       synchronized (this.appearance.liveStateLock) {
/* 1424 */         this.appearance.setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/* 1425 */         this.appearance.initMirrorObject();
/* 1426 */         if (this.appearance.renderingAttributes != null)
/* 1427 */           this.visible = this.appearance.renderingAttributes.visible; 
/* 1428 */         for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 1429 */           Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList.get(b1);
/* 1430 */           shape3DRetained.appearance = (AppearanceRetained)this.appearance.mirror;
/* 1431 */           this.appearance.addAMirrorUser(shape3DRetained);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1437 */       for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 1438 */         Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList.get(b1);
/* 1439 */         shape3DRetained.appearance = null;
/*      */       } 
/*      */     } 
/*      */     
/* 1443 */     paramSetLiveState.notifyThreads |= 0x24C0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1449 */     if (this.refCount == 1 && this.geometryArrays[0] instanceof IndexedGeometryArrayRetained) {
/* 1450 */       J3dMessage j3dMessage = new J3dMessage();
/* 1451 */       j3dMessage.type = 16;
/* 1452 */       j3dMessage.threads = 1024;
/* 1453 */       j3dMessage.args[0] = this;
/* 1454 */       j3dMessage.args[1] = new Integer(1);
/* 1455 */       j3dMessage.universe = this.universe;
/* 1456 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/* 1458 */     markAsLive();
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
/*      */   void clearLive(SetLiveState paramSetLiveState) {
/* 1470 */     ArrayList arrayList = new ArrayList();
/*      */ 
/*      */     
/* 1473 */     super.clearLive(paramSetLiveState);
/*      */     byte b;
/* 1475 */     for (b = 0; b < this.numGeometryArrays; b++) {
/* 1476 */       synchronized ((this.geometryArrays[b]).liveStateLock) {
/* 1477 */         this.geometryArrays[b].clearLive(paramSetLiveState.refCount);
/*      */ 
/*      */         
/* 1480 */         if (this.refCount <= 0) {
/* 1481 */           this.geometryArrays[b].removeMorphUser(this);
/*      */         }
/*      */       } 
/*      */     } 
/* 1485 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/*      */     
/* 1487 */     geometryArrayRetained.clearLive(paramSetLiveState.refCount);
/*      */     
/* 1489 */     if (this.inSharedGroup) {
/* 1490 */       Object[] arrayOfObject = this.mirrorShape3D.toArray();
/* 1491 */       for (b = 0; b < paramSetLiveState.keys.length; b++) {
/* 1492 */         for (byte b1 = 0; b1 < arrayOfObject.length; b1++) {
/* 1493 */           Shape3DRetained shape3DRetained = (Shape3DRetained)arrayOfObject[b1];
/* 1494 */           if (shape3DRetained.key.equals(paramSetLiveState.keys[b]))
/*      */           {
/* 1496 */             this.mirrorShape3D.remove(b1);
/* 1497 */             if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b] != null)
/*      */             {
/* 1499 */               paramSetLiveState.switchTargets[b].addNode(shape3DRetained, 0);
/*      */             }
/*      */             
/* 1502 */             if (this.appearance != null) {
/* 1503 */               arrayList.add(shape3DRetained);
/*      */             }
/*      */             
/* 1506 */             GeometryAtom geometryAtom = Shape3DRetained.getGeomAtom(shape3DRetained);
/*      */ 
/*      */             
/* 1509 */             paramSetLiveState.nodeList.add(geometryAtom);
/* 1510 */             if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null)
/*      */             {
/* 1512 */               paramSetLiveState.transformTargets[b].addNode(geometryAtom, 0);
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 1520 */       Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */       
/* 1522 */       this.mirrorShape3D.remove(0);
/* 1523 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */       {
/* 1525 */         paramSetLiveState.switchTargets[0].addNode(shape3DRetained, 0);
/*      */       }
/* 1527 */       if (this.appearance != null) {
/* 1528 */         arrayList.add(shape3DRetained);
/*      */       }
/*      */       
/* 1531 */       GeometryAtom geometryAtom = Shape3DRetained.getGeomAtom(shape3DRetained);
/*      */ 
/*      */       
/* 1534 */       paramSetLiveState.nodeList.add(geometryAtom);
/* 1535 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null)
/*      */       {
/* 1537 */         paramSetLiveState.transformTargets[0].addNode(geometryAtom, 0);
/*      */       }
/*      */     } 
/* 1540 */     if (this.appearance != null) {
/* 1541 */       synchronized (this.appearance.liveStateLock) {
/* 1542 */         this.appearance.clearLive(paramSetLiveState.refCount);
/* 1543 */         for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 1544 */           this.appearance.removeAMirrorUser((Shape3DRetained)arrayList.get(b1));
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1549 */     paramSetLiveState.notifyThreads |= 0x30C0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePickable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 1560 */     super.updatePickable(paramArrayOfHashKey, paramArrayOfBoolean);
/*      */ 
/*      */ 
/*      */     
/* 1564 */     if (!this.inSharedGroup) {
/* 1565 */       Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/* 1566 */       shape3DRetained.isPickable = paramArrayOfBoolean[0];
/*      */     } else {
/* 1568 */       int i = this.mirrorShape3D.size();
/* 1569 */       for (byte b = 0; b < paramArrayOfHashKey.length; b++) {
/* 1570 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1571 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b1);
/* 1572 */           if (paramArrayOfHashKey[b].equals(shape3DRetained.key)) {
/* 1573 */             shape3DRetained.isPickable = paramArrayOfBoolean[b];
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
/* 1584 */     super.updateCollidable(paramArrayOfHashKey, paramArrayOfBoolean);
/*      */ 
/*      */     
/* 1587 */     if (!this.inSharedGroup) {
/* 1588 */       Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/* 1589 */       shape3DRetained.isCollidable = paramArrayOfBoolean[0];
/*      */     } else {
/* 1591 */       int i = this.mirrorShape3D.size();
/* 1592 */       for (byte b = 0; b < paramArrayOfHashKey.length; b++) {
/* 1593 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1594 */           Shape3DRetained shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(b1);
/* 1595 */           if (paramArrayOfHashKey[b].equals(shape3DRetained.key)) {
/* 1596 */             shape3DRetained.isCollidable = paramArrayOfBoolean[b];
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   Shape3DRetained getMirrorShape(SceneGraphPath paramSceneGraphPath) {
/* 1606 */     if (!this.inSharedGroup) {
/* 1607 */       return (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */     }
/* 1609 */     HashKey hashKey = new HashKey("");
/* 1610 */     paramSceneGraphPath.getHashKey(hashKey);
/* 1611 */     return getMirrorShape(hashKey);
/*      */   }
/*      */   
/*      */   Shape3DRetained getMirrorShape(HashKey paramHashKey) {
/* 1615 */     int i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 1616 */     if (i >= 0) {
/* 1617 */       return (Shape3DRetained)this.mirrorShape3D.get(i);
/*      */     }
/*      */ 
/*      */     
/* 1621 */     throw new RuntimeException("Shape3DRetained: MirrorShape Not found!");
/*      */   }
/*      */   
/*      */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) {
/*      */     Shape3DRetained shape3DRetained;
/* 1626 */     if (this.inSharedGroup) {
/* 1627 */       shape3DRetained = getMirrorShape(paramHashKey);
/*      */     } else {
/*      */       
/* 1630 */       shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */     } 
/* 1632 */     GeometryAtom geometryAtom = Shape3DRetained.getGeomAtom(shape3DRetained);
/* 1633 */     paramArrayList.add(geometryAtom);
/*      */   }
/*      */ 
/*      */   
/*      */   void setBoundsAutoCompute(boolean paramBoolean) {
/* 1638 */     if (paramBoolean != this.boundsAutoCompute) {
/* 1639 */       if (paramBoolean) {
/*      */         
/* 1641 */         this.localBounds = new BoundingBox();
/* 1642 */         if (this.source.isLive() && this.morphedGeometryArray != null) {
/* 1643 */           GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/* 1644 */           geometryArrayRetained.incrComputeGeoBounds();
/* 1645 */           geometryArrayRetained.decrComputeGeoBounds();
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1650 */       this.localBounds = getBounds();
/* 1651 */       super.setBoundsAutoCompute(paramBoolean);
/* 1652 */       if (this.source.isLive()) {
/* 1653 */         J3dMessage j3dMessage = new J3dMessage();
/* 1654 */         j3dMessage.type = 37;
/* 1655 */         j3dMessage.threads = 8384;
/*      */ 
/*      */         
/* 1658 */         j3dMessage.universe = this.universe;
/* 1659 */         j3dMessage.args[0] = Shape3DRetained.getGeomAtomsArray(this.mirrorShape3D);
/* 1660 */         j3dMessage.args[1] = this.localBounds;
/* 1661 */         VirtualUniverse.mc.processMessage(j3dMessage);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void updateBounds() {
/* 1667 */     this.localBounds = getEffectiveBounds();
/* 1668 */     if (this.source.isLive()) {
/* 1669 */       J3dMessage j3dMessage = new J3dMessage();
/* 1670 */       j3dMessage.type = 37;
/* 1671 */       j3dMessage.threads = 8384;
/*      */ 
/*      */       
/* 1674 */       j3dMessage.universe = this.universe;
/* 1675 */       j3dMessage.args[0] = Shape3DRetained.getGeomAtomsArray(this.mirrorShape3D);
/* 1676 */       j3dMessage.args[1] = this.localBounds;
/* 1677 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initMorphedGeometry() {
/* 1686 */     int arrayOfInt1[], k = 0;
/* 1687 */     int m = 0;
/* 1688 */     int n = 0;
/* 1689 */     int i1 = 0;
/* 1690 */     int[] arrayOfInt2 = null;
/*      */     
/* 1692 */     GeometryArrayRetained geometryArrayRetained1 = this.geometryArrays[0];
/* 1693 */     int i = (geometryArrayRetained1.getVertexFormat() | 0x80) & 0xFFFFFEFF;
/* 1694 */     n = geometryArrayRetained1.getTexCoordSetCount();
/* 1695 */     i1 = geometryArrayRetained1.getTexCoordSetMapLength();
/* 1696 */     if (i1 > 0) {
/* 1697 */       arrayOfInt2 = new int[i1];
/* 1698 */       geometryArrayRetained1.getTexCoordSetMap(arrayOfInt2);
/*      */     } 
/* 1700 */     int j = geometryArrayRetained1.geoType;
/*      */     
/* 1702 */     switch (j) {
/*      */       case 1:
/* 1704 */         this.morphedGeometryArray = new QuadArray((this.geometryArrays[0]).validVertexCount, i, n, arrayOfInt2);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/* 1709 */         this.morphedGeometryArray = new TriangleArray((this.geometryArrays[0]).validVertexCount, i, n, arrayOfInt2);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/* 1714 */         this.morphedGeometryArray = new PointArray((this.geometryArrays[0]).validVertexCount, i, n, arrayOfInt2);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/* 1719 */         this.morphedGeometryArray = new LineArray((this.geometryArrays[0]).validVertexCount, i, n, arrayOfInt2);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 5:
/* 1724 */         m = ((TriangleStripArrayRetained)geometryArrayRetained1).getNumStrips();
/* 1725 */         arrayOfInt1 = new int[m];
/* 1726 */         ((TriangleStripArrayRetained)geometryArrayRetained1).getStripVertexCounts(arrayOfInt1);
/* 1727 */         this.morphedGeometryArray = new TriangleStripArray((this.geometryArrays[0]).validVertexCount, i, n, arrayOfInt2, arrayOfInt1);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/* 1732 */         m = ((TriangleFanArrayRetained)geometryArrayRetained1).getNumStrips();
/* 1733 */         arrayOfInt1 = new int[m];
/* 1734 */         ((TriangleFanArrayRetained)geometryArrayRetained1).getStripVertexCounts(arrayOfInt1);
/* 1735 */         this.morphedGeometryArray = new TriangleFanArray((this.geometryArrays[0]).validVertexCount, i, n, arrayOfInt2, arrayOfInt1);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 7:
/* 1740 */         m = ((LineStripArrayRetained)geometryArrayRetained1).getNumStrips();
/* 1741 */         arrayOfInt1 = new int[m];
/* 1742 */         ((LineStripArrayRetained)geometryArrayRetained1).getStripVertexCounts(arrayOfInt1);
/* 1743 */         this.morphedGeometryArray = new LineStripArray((this.geometryArrays[0]).validVertexCount, i, n, arrayOfInt2, arrayOfInt1);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 8:
/* 1749 */         k = ((IndexedGeometryArrayRetained)geometryArrayRetained1).getIndexCount();
/* 1750 */         this.morphedGeometryArray = new IndexedQuadArray(this.geometryArrays[0].getNumCoordCount(), i, n, arrayOfInt2, k);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 9:
/* 1755 */         k = ((IndexedGeometryArrayRetained)geometryArrayRetained1).getIndexCount();
/* 1756 */         this.morphedGeometryArray = new IndexedTriangleArray(this.geometryArrays[0].getNumCoordCount(), i, n, arrayOfInt2, k);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 10:
/* 1761 */         k = ((IndexedGeometryArrayRetained)geometryArrayRetained1).getIndexCount();
/* 1762 */         this.morphedGeometryArray = new IndexedPointArray(this.geometryArrays[0].getNumCoordCount(), i, n, arrayOfInt2, k);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 11:
/* 1767 */         k = ((IndexedGeometryArrayRetained)geometryArrayRetained1).getIndexCount();
/* 1768 */         this.morphedGeometryArray = new IndexedLineArray(this.geometryArrays[0].getNumCoordCount(), i, n, arrayOfInt2, k);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 12:
/* 1773 */         k = ((IndexedGeometryArrayRetained)geometryArrayRetained1).getIndexCount();
/* 1774 */         m = ((IndexedTriangleStripArrayRetained)geometryArrayRetained1).getNumStrips();
/* 1775 */         arrayOfInt1 = new int[m];
/* 1776 */         ((IndexedTriangleStripArrayRetained)geometryArrayRetained1).getStripIndexCounts(arrayOfInt1);
/* 1777 */         this.morphedGeometryArray = new IndexedTriangleStripArray(this.geometryArrays[0].getNumCoordCount(), i, n, arrayOfInt2, k, arrayOfInt1);
/*      */         break;
/*      */       
/*      */       case 13:
/* 1781 */         k = ((IndexedGeometryArrayRetained)geometryArrayRetained1).getIndexCount();
/* 1782 */         m = ((IndexedTriangleFanArrayRetained)geometryArrayRetained1).getNumStrips();
/* 1783 */         arrayOfInt1 = new int[m];
/* 1784 */         ((IndexedTriangleFanArrayRetained)geometryArrayRetained1).getStripIndexCounts(arrayOfInt1);
/* 1785 */         this.morphedGeometryArray = new IndexedTriangleFanArray(this.geometryArrays[0].getNumCoordCount(), i, n, arrayOfInt2, k, arrayOfInt1);
/*      */         break;
/*      */       
/*      */       case 14:
/* 1789 */         k = ((IndexedGeometryArrayRetained)geometryArrayRetained1).getIndexCount();
/* 1790 */         m = ((IndexedLineStripArrayRetained)geometryArrayRetained1).getNumStrips();
/* 1791 */         arrayOfInt1 = new int[m];
/* 1792 */         ((IndexedLineStripArrayRetained)geometryArrayRetained1).getStripIndexCounts(arrayOfInt1);
/* 1793 */         this.morphedGeometryArray = new IndexedLineStripArray(this.geometryArrays[0].getNumCoordCount(), i, n, arrayOfInt2, k, arrayOfInt1);
/*      */         break;
/*      */     } 
/*      */     
/* 1797 */     if (this.geometryArrays[0] instanceof IndexedGeometryArrayRetained) {
/* 1798 */       IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this.geometryArrays[0];
/*      */       
/* 1800 */       IndexedGeometryArray indexedGeometryArray = (IndexedGeometryArray)this.morphedGeometryArray;
/*      */       
/* 1802 */       if ((i & true) != 0) {
/* 1803 */         indexedGeometryArray.setCoordinateIndices(0, indexedGeometryArrayRetained.indexCoord);
/*      */       }
/* 1805 */       if ((i & 0x200) == 0) {
/* 1806 */         if ((i & 0x2) != 0) {
/* 1807 */           indexedGeometryArray.setNormalIndices(0, indexedGeometryArrayRetained.indexNormal);
/*      */         }
/* 1809 */         if ((i & 0x4) != 0) {
/* 1810 */           indexedGeometryArray.setColorIndices(0, indexedGeometryArrayRetained.indexColor);
/*      */         }
/* 1812 */         if ((i & 0x460) != 0) {
/* 1813 */           for (byte b = 0; b < n; b++) {
/* 1814 */             indexedGeometryArray.setTextureCoordinateIndices(b, 0, indexedGeometryArrayRetained.indexTexCoord[b]);
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1820 */     this.morphedGeometryArray.setCapability(19);
/*      */     
/* 1822 */     GeometryArrayRetained geometryArrayRetained2 = (GeometryArrayRetained)this.morphedGeometryArray.retained;
/* 1823 */     geometryArrayRetained2.updateData(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void getMirrorShape3D(ArrayList paramArrayList, HashKey paramHashKey) {
/*      */     Shape3DRetained shape3DRetained;
/* 1830 */     if (this.inSharedGroup) {
/* 1831 */       shape3DRetained = getMirrorShape(paramHashKey);
/*      */     } else {
/*      */       
/* 1834 */       shape3DRetained = (Shape3DRetained)this.mirrorShape3D.get(0);
/*      */     } 
/* 1836 */     paramArrayList.add(shape3DRetained);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void compile(CompileState paramCompileState) {
/* 1842 */     super.compile(paramCompileState);
/*      */ 
/*      */     
/* 1845 */     paramCompileState.keepTG = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doErrorCheck(GeometryArrayRetained paramGeometryArrayRetained1, GeometryArrayRetained paramGeometryArrayRetained2) {
/* 1855 */     if (paramGeometryArrayRetained1.vertexFormat != paramGeometryArrayRetained2.vertexFormat || paramGeometryArrayRetained1.validVertexCount != paramGeometryArrayRetained2.validVertexCount || paramGeometryArrayRetained1.geoType != paramGeometryArrayRetained2.geoType || paramGeometryArrayRetained1.texCoordSetCount != paramGeometryArrayRetained2.texCoordSetCount)
/*      */     {
/*      */ 
/*      */       
/* 1859 */       throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1"));
/*      */     }
/* 1861 */     if (paramGeometryArrayRetained2.getTexCoordSetMapLength() != paramGeometryArrayRetained1.getTexCoordSetMapLength()) {
/* 1862 */       throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1"));
/*      */     }
/* 1864 */     int i = paramGeometryArrayRetained2.getTexCoordSetMapLength();
/* 1865 */     int[] arrayOfInt1 = paramGeometryArrayRetained1.texCoordSetMap;
/* 1866 */     int[] arrayOfInt2 = paramGeometryArrayRetained2.texCoordSetMap; int j;
/* 1867 */     for (j = 0; j < i; j++) {
/* 1868 */       if (arrayOfInt1[j] != arrayOfInt2[j]) {
/* 1869 */         throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1"));
/*      */       }
/*      */     } 
/* 1872 */     if (paramGeometryArrayRetained2 instanceof GeometryStripArrayRetained) {
/* 1873 */       arrayOfInt1 = ((GeometryStripArrayRetained)paramGeometryArrayRetained1).stripVertexCounts;
/* 1874 */       arrayOfInt2 = ((GeometryStripArrayRetained)paramGeometryArrayRetained2).stripVertexCounts;
/* 1875 */       if (arrayOfInt1.length != arrayOfInt2.length)
/* 1876 */         throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1")); 
/* 1877 */       for (j = 0; j < arrayOfInt1.length; j++) {
/* 1878 */         if (arrayOfInt1[j] != arrayOfInt2[j])
/* 1879 */           throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1")); 
/*      */       } 
/* 1881 */     } else if (paramGeometryArrayRetained2 instanceof IndexedGeometryArrayRetained) {
/* 1882 */       if (((IndexedGeometryArrayRetained)paramGeometryArrayRetained1).validIndexCount != ((IndexedGeometryArrayRetained)paramGeometryArrayRetained2).validIndexCount) {
/* 1883 */         throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1"));
/*      */       }
/*      */       
/* 1886 */       if (paramGeometryArrayRetained2.getNumCoordCount() != paramGeometryArrayRetained1.getNumCoordCount() || paramGeometryArrayRetained2.getNumColorCount() != paramGeometryArrayRetained1.getNumColorCount() || paramGeometryArrayRetained2.getNumNormalCount() != paramGeometryArrayRetained1.getNumNormalCount())
/*      */       {
/*      */         
/* 1889 */         throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1"));
/*      */       }
/* 1891 */       j = paramGeometryArrayRetained2.getTexCoordSetCount(); byte b;
/* 1892 */       for (b = 0; b < j; b++) {
/* 1893 */         if (paramGeometryArrayRetained2.getNumTexCoordCount(b) != paramGeometryArrayRetained1.getNumTexCoordCount(b)) {
/* 1894 */           throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1"));
/*      */         }
/*      */       } 
/*      */       
/* 1898 */       if (paramGeometryArrayRetained2 instanceof IndexedGeometryStripArrayRetained) {
/* 1899 */         arrayOfInt1 = ((IndexedGeometryStripArrayRetained)paramGeometryArrayRetained1).stripIndexCounts;
/* 1900 */         arrayOfInt2 = ((IndexedGeometryStripArrayRetained)paramGeometryArrayRetained2).stripIndexCounts;
/* 1901 */         if (arrayOfInt1.length != arrayOfInt2.length)
/* 1902 */           throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1")); 
/* 1903 */         for (b = 0; b < arrayOfInt1.length; b++) {
/* 1904 */           if (arrayOfInt1[b] != arrayOfInt2[b])
/* 1905 */             throw new IllegalArgumentException(J3dI18N.getString("MorphRetained1")); 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void handleFrequencyChange(int paramInt) {
/* 1912 */     int i = 0;
/* 1913 */     if (paramInt == 13) {
/* 1914 */       i = 1;
/*      */     }
/* 1916 */     else if (paramInt == 15) {
/* 1917 */       i = 2;
/*      */     }
/* 1919 */     else if (paramInt == 21) {
/* 1920 */       i = 16;
/*      */     } 
/* 1922 */     if (i != 0) {
/* 1923 */       if (this.source.getCapabilityIsFrequent(paramInt)) {
/* 1924 */         this.changedFrequent |= i;
/* 1925 */       } else if (!this.source.isLive()) {
/* 1926 */         this.changedFrequent &= (i ^ 0xFFFFFFFF);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/* 1932 */   void searchGeometryAtoms(UnorderList paramUnorderList) { paramUnorderList.add(Shape3DRetained.getGeomAtom((Shape3DRetained)this.mirrorShape3D.get(0))); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\MorphRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */