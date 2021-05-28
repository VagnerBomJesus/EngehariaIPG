/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Vector4d;
/*      */ 
/*      */ 
/*      */ class ModelClipRetained
/*      */   extends LeafRetained
/*      */ {
/*      */   static final int PLANE_CHANGED = 1;
/*      */   static final int PLANES_CHANGED = 2;
/*      */   static final int ENABLE_CHANGED = 4;
/*      */   static final int ENABLES_CHANGED = 8;
/*      */   static final int BOUNDS_CHANGED = 16;
/*      */   static final int BOUNDINGLEAF_CHANGED = 32;
/*      */   static final int SCOPE_CHANGED = 64;
/*      */   static final int INIT_MIRROR = 128;
/*      */   static final int CLEAR_MIRROR = 256;
/*      */   static final int LAST_DEFINED_BIT = 256;
/*      */   Vector4d[] planes;
/*      */   boolean[] enables;
/*      */   Vector4d[] xformPlanes;
/*      */   boolean enableFlag;
/*      */   Bounds regionOfInfluence;
/*      */   BoundingLeafRetained boundingLeaf;
/*      */   Bounds region;
/*      */   Vector scopes;
/*      */   boolean isScoped;
/*      */   HashKey tempKey;
/*      */   boolean inImmCtx;
/*      */   ModelClipRetained mirrorModelClip;
/*      */   ModelClipRetained sgModelClip;
/*      */   static final int targetThreads = 4224;
/*      */   UnorderList environmentSets;
/*      */   boolean isViewScoped;
/*      */   
/*      */   ModelClipRetained() {
/*   40 */     this.planes = new Vector4d[6];
/*   41 */     this.enables = new boolean[6];
/*      */     
/*   43 */     this.xformPlanes = new Vector4d[6];
/*      */ 
/*      */ 
/*      */     
/*   47 */     this.enableFlag = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   52 */     this.regionOfInfluence = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   57 */     this.boundingLeaf = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   62 */     this.region = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   67 */     this.scopes = new Vector();
/*      */ 
/*      */     
/*   70 */     this.isScoped = false;
/*      */ 
/*      */ 
/*      */     
/*   74 */     this.tempKey = new HashKey(250);
/*      */ 
/*      */     
/*   77 */     this.inImmCtx = false;
/*      */ 
/*      */     
/*   80 */     this.mirrorModelClip = null;
/*      */ 
/*      */     
/*   83 */     this.sgModelClip = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   95 */     this.environmentSets = new UnorderList(1, EnvironmentSet.class);
/*      */ 
/*      */     
/*   98 */     this.isViewScoped = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  106 */     this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -1.0D);
/*  107 */     this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, -1.0D);
/*  108 */     this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -1.0D);
/*  109 */     this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, -1.0D);
/*  110 */     this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -1.0D);
/*  111 */     this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, -1.0D);
/*      */     
/*  113 */     for (byte b = 0; b < 6; b++) {
/*  114 */       this.xformPlanes[b] = new Vector4d(this.planes[b]);
/*      */     }
/*  116 */     this.enables[5] = true; this.enables[4] = true; this.enables[3] = true; this.enables[2] = true; this.enables[1] = true; this.enables[0] = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initPlanes(Vector4d[] paramArrayOfVector4d) {
/*  125 */     if (this.staticTransform != null) {
/*  126 */       Transform3D transform3D = this.staticTransform.getNormalTransform();
/*  127 */       for (byte b = 0; b < 6; b++) {
/*  128 */         this.planes[b].set(paramArrayOfVector4d[b]);
/*  129 */         transform3D.transform(this.planes[b], this.xformPlanes[b]);
/*      */       } 
/*      */     } else {
/*  132 */       for (byte b = 0; b < 6; b++) {
/*  133 */         this.planes[b].set(paramArrayOfVector4d[b]);
/*  134 */         this.xformPlanes[b].set(this.planes[b]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlanes(Vector4d[] paramArrayOfVector4d) {
/*  143 */     Vector4d[] arrayOfVector4d = new Vector4d[6];
/*  144 */     initPlanes(paramArrayOfVector4d);
/*      */     
/*  146 */     for (byte b = 0; b < 6; b++) {
/*  147 */       arrayOfVector4d[b] = new Vector4d(this.xformPlanes[b]);
/*      */     }
/*      */     
/*  150 */     sendMessage(2, arrayOfVector4d, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initPlane(int paramInt, Vector4d paramVector4d) {
/*  157 */     if (paramInt < 0 || paramInt > 5) {
/*  158 */       throw new IllegalArgumentException(J3dI18N.getString("ModelClip6"));
/*      */     }
/*  160 */     if (this.staticTransform != null) {
/*  161 */       Transform3D transform3D = this.staticTransform.getNormalTransform();
/*  162 */       this.planes[paramInt].set(paramVector4d);
/*  163 */       transform3D.transform(this.planes[paramInt], this.xformPlanes[paramInt]);
/*      */     } else {
/*  165 */       this.planes[paramInt].set(paramVector4d);
/*  166 */       this.xformPlanes[paramInt].set(paramVector4d);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlane(int paramInt, Vector4d paramVector4d) {
/*  174 */     initPlane(paramInt, paramVector4d);
/*  175 */     sendMessage(1, new Integer(paramInt), new Vector4d(this.xformPlanes[paramInt]));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getPlanes(Vector4d[] paramArrayOfVector4d) {
/*  185 */     for (byte b = 0; b < 6; b++) {
/*  186 */       paramArrayOfVector4d[b].set(this.planes[b]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getPlane(int paramInt, Vector4d paramVector4d) {
/*  194 */     if (paramInt < 0 || paramInt > 5)
/*  195 */       throw new IllegalArgumentException(J3dI18N.getString("ModelClip6")); 
/*  196 */     paramVector4d.set(this.planes[paramInt]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initEnables(boolean[] paramArrayOfBoolean) {
/*  203 */     this.enables[0] = paramArrayOfBoolean[0];
/*  204 */     this.enables[1] = paramArrayOfBoolean[1];
/*  205 */     this.enables[2] = paramArrayOfBoolean[2];
/*  206 */     this.enables[3] = paramArrayOfBoolean[3];
/*  207 */     this.enables[4] = paramArrayOfBoolean[4];
/*  208 */     this.enables[5] = paramArrayOfBoolean[5];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setEnables(boolean[] paramArrayOfBoolean) {
/*  215 */     Boolean[] arrayOfBoolean = new Boolean[6];
/*      */     
/*  217 */     initEnables(paramArrayOfBoolean);
/*  218 */     arrayOfBoolean[0] = paramArrayOfBoolean[0] ? Boolean.TRUE : Boolean.FALSE;
/*  219 */     arrayOfBoolean[1] = paramArrayOfBoolean[1] ? Boolean.TRUE : Boolean.FALSE;
/*  220 */     arrayOfBoolean[2] = paramArrayOfBoolean[2] ? Boolean.TRUE : Boolean.FALSE;
/*  221 */     arrayOfBoolean[3] = paramArrayOfBoolean[3] ? Boolean.TRUE : Boolean.FALSE;
/*  222 */     arrayOfBoolean[4] = paramArrayOfBoolean[4] ? Boolean.TRUE : Boolean.FALSE;
/*  223 */     arrayOfBoolean[5] = paramArrayOfBoolean[5] ? Boolean.TRUE : Boolean.FALSE;
/*      */     
/*  225 */     sendMessage(8, arrayOfBoolean, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initEnable(int paramInt, boolean paramBoolean) {
/*  232 */     if (paramInt < 0 || paramInt > 5)
/*  233 */       throw new IllegalArgumentException(J3dI18N.getString("ModelClip6")); 
/*  234 */     this.enables[paramInt] = paramBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setEnable(int paramInt, boolean paramBoolean) {
/*  241 */     initEnable(paramInt, paramBoolean);
/*  242 */     sendMessage(4, new Integer(paramInt), paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getEnables(boolean[] paramArrayOfBoolean) {
/*  251 */     paramArrayOfBoolean[0] = this.enables[0];
/*  252 */     paramArrayOfBoolean[1] = this.enables[1];
/*  253 */     paramArrayOfBoolean[2] = this.enables[2];
/*  254 */     paramArrayOfBoolean[3] = this.enables[3];
/*  255 */     paramArrayOfBoolean[4] = this.enables[4];
/*  256 */     paramArrayOfBoolean[5] = this.enables[5];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getEnable(int paramInt) {
/*  263 */     if (paramInt < 0 || paramInt > 5)
/*  264 */       throw new IllegalArgumentException(J3dI18N.getString("ModelClip6")); 
/*  265 */     return this.enables[paramInt];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initInfluencingBounds(Bounds paramBounds) {
/*  272 */     if (paramBounds != null) {
/*  273 */       this.regionOfInfluence = (Bounds)paramBounds.clone();
/*  274 */       if (this.staticTransform != null) {
/*  275 */         this.regionOfInfluence.transform(this.staticTransform.transform);
/*      */       }
/*      */     } else {
/*  278 */       this.regionOfInfluence = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setInfluencingBounds(Bounds paramBounds) {
/*  286 */     initInfluencingBounds(paramBounds);
/*  287 */     sendMessage(16, (paramBounds != null) ? (Bounds)paramBounds.clone() : null, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds getInfluencingBounds() {
/*  296 */     Bounds bounds = null;
/*      */     
/*  298 */     if (this.regionOfInfluence != null) {
/*  299 */       bounds = (Bounds)this.regionOfInfluence.clone();
/*  300 */       if (this.staticTransform != null) {
/*  301 */         Transform3D transform3D = this.staticTransform.getInvTransform();
/*  302 */         bounds.transform(transform3D);
/*      */       } 
/*      */     } 
/*  305 */     return bounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/*  312 */     if (paramBoundingLeaf != null) {
/*  313 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*      */     } else {
/*  315 */       this.boundingLeaf = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/*  323 */     if (this.boundingLeaf != null)
/*  324 */       this.boundingLeaf.mirrorBoundingLeaf.removeUser(this.mirrorModelClip); 
/*  325 */     if (paramBoundingLeaf != null) {
/*  326 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*  327 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorModelClip);
/*      */     } else {
/*  329 */       this.boundingLeaf = null;
/*      */     } 
/*      */     
/*  332 */     sendMessage(32, (this.boundingLeaf != null) ? this.boundingLeaf.mirrorBoundingLeaf : null, null);
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
/*  343 */   BoundingLeaf getInfluencingBoundingLeaf() { return (this.boundingLeaf != null) ? (BoundingLeaf)this.boundingLeaf.source : null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  353 */   void initScope(Group paramGroup, int paramInt) { this.scopes.setElementAt((GroupRetained)paramGroup.retained, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setScope(Group paramGroup, int paramInt) {
/*  363 */     ArrayList arrayList1 = new ArrayList();
/*  364 */     ArrayList arrayList2 = new ArrayList();
/*      */     
/*  366 */     Object[] arrayOfObject = new Object[3];
/*      */     
/*  368 */     GroupRetained groupRetained = (GroupRetained)this.scopes.get(paramInt);
/*  369 */     this.tempKey.reset();
/*  370 */     groupRetained.removeAllNodesForScopedModelClip(this.mirrorModelClip, arrayList2, this.tempKey);
/*      */     
/*  372 */     groupRetained = (GroupRetained)paramGroup.retained;
/*  373 */     initScope(paramGroup, paramInt);
/*  374 */     this.tempKey.reset();
/*      */ 
/*      */ 
/*      */     
/*  378 */     groupRetained.addAllNodesForScopedModelClip(this.mirrorModelClip, arrayList1, this.tempKey);
/*  379 */     arrayOfObject[0] = arrayList1;
/*  380 */     arrayOfObject[1] = arrayList2;
/*  381 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  382 */     sendMessage(64, arrayOfObject, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initInsertScope(Node paramNode, int paramInt) {
/*  391 */     GroupRetained groupRetained = (GroupRetained)paramNode.retained;
/*  392 */     groupRetained.setMclipScope();
/*  393 */     this.scopes.insertElementAt((GroupRetained)paramNode.retained, paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void insertScope(Node paramNode, int paramInt) {
/*  403 */     Object[] arrayOfObject = new Object[3];
/*  404 */     ArrayList arrayList = new ArrayList();
/*      */     
/*  406 */     initInsertScope(paramNode, paramInt);
/*  407 */     GroupRetained groupRetained = (GroupRetained)paramNode.retained;
/*  408 */     this.tempKey.reset();
/*  409 */     groupRetained.addAllNodesForScopedModelClip(this.mirrorModelClip, arrayList, this.tempKey);
/*  410 */     arrayOfObject[0] = arrayList;
/*  411 */     arrayOfObject[1] = null;
/*  412 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  413 */     sendMessage(64, arrayOfObject, null);
/*      */   }
/*      */ 
/*      */   
/*      */   void initRemoveScope(int paramInt) {
/*  418 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/*  419 */     groupRetained.removeMclipScope();
/*  420 */     this.scopes.removeElementAt(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeScope(int paramInt) {
/*  426 */     Object[] arrayOfObject = new Object[3];
/*  427 */     ArrayList arrayList = new ArrayList();
/*  428 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/*      */     
/*  430 */     initRemoveScope(paramInt);
/*  431 */     this.tempKey.reset();
/*  432 */     groupRetained.removeAllNodesForScopedModelClip(this.mirrorModelClip, arrayList, this.tempKey);
/*      */     
/*  434 */     arrayOfObject[0] = null;
/*  435 */     arrayOfObject[1] = arrayList;
/*  436 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  437 */     sendMessage(64, arrayOfObject, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeScope(Group paramGroup) {
/*  448 */     int i = indexOfScope(paramGroup);
/*  449 */     if (i >= 0)
/*  450 */       removeScope(i); 
/*      */   }
/*      */   
/*      */   void initRemoveScope(Group paramGroup) {
/*  454 */     int i = indexOfScope(paramGroup);
/*  455 */     if (i >= 0) {
/*  456 */       initRemoveScope(i);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeAllScopes() {
/*  464 */     Object[] arrayOfObject = new Object[3];
/*  465 */     ArrayList arrayList = new ArrayList();
/*  466 */     int i = this.scopes.size();
/*  467 */     for (int j = i - 1; j >= 0; j--) {
/*  468 */       GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(j);
/*  469 */       initRemoveScope(j);
/*  470 */       this.tempKey.reset();
/*  471 */       groupRetained.removeAllNodesForScopedModelClip(this.mirrorModelClip, arrayList, this.tempKey);
/*      */     } 
/*      */     
/*  474 */     arrayOfObject[0] = null;
/*  475 */     arrayOfObject[1] = arrayList;
/*  476 */     arrayOfObject[2] = Boolean.FALSE;
/*  477 */     sendMessage(64, arrayOfObject, null);
/*      */   }
/*      */ 
/*      */   
/*      */   void initRemoveAllScopes() {
/*  482 */     int i = this.scopes.size();
/*  483 */     for (int j = i - 1; j >= 0; j--) {
/*  484 */       initRemoveScope(j);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  494 */   Group getScope(int paramInt) { return (Group)((GroupRetained)this.scopes.elementAt(paramInt)).source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Enumeration getAllScopes() {
/*  502 */     Enumeration enumeration = this.scopes.elements();
/*  503 */     Vector vector = new Vector(this.scopes.size());
/*  504 */     while (enumeration.hasMoreElements()) {
/*  505 */       vector.add(((GroupRetained)enumeration.nextElement()).source);
/*      */     }
/*  507 */     return vector.elements();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initAddScope(Group paramGroup) {
/*  516 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*  517 */     this.scopes.addElement((GroupRetained)paramGroup.retained);
/*  518 */     groupRetained.setMclipScope();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addScope(Group paramGroup) {
/*  527 */     Object[] arrayOfObject = new Object[3];
/*  528 */     ArrayList arrayList = new ArrayList();
/*  529 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*      */     
/*  531 */     initAddScope(paramGroup);
/*  532 */     this.tempKey.reset();
/*  533 */     groupRetained.addAllNodesForScopedModelClip(this.mirrorModelClip, arrayList, this.tempKey);
/*  534 */     arrayOfObject[0] = arrayList;
/*  535 */     arrayOfObject[1] = null;
/*  536 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  537 */     sendMessage(64, arrayOfObject, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  545 */   int numScopes() { return this.scopes.size(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int indexOfScope(Group paramGroup) {
/*  555 */     if (paramGroup != null) {
/*  556 */       return this.scopes.indexOf((GroupRetained)paramGroup.retained);
/*      */     }
/*  558 */     return this.scopes.indexOf(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  565 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  572 */   boolean getInImmCtx() { return this.inImmCtx; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void update(Canvas3D paramCanvas3D, int paramInt) {
/*  581 */     paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, getLastLocalToVworld());
/*      */ 
/*      */     
/*  584 */     update(paramCanvas3D.ctx, paramInt, getLastLocalToVworld());
/*      */   }
/*      */   
/*      */   void update(Context paramContext, int paramInt, Transform3D paramTransform3D) {
/*  588 */     if (!VirtualUniverse.mc.isD3D()) {
/*  589 */       for (byte b1 = 0; b1 < 6; b1++) {
/*  590 */         Pipeline.getPipeline().updateModelClip(paramContext, b1, ((paramInt & true << b1) != 0), (this.xformPlanes[b1]).x, (this.xformPlanes[b1]).y, (this.xformPlanes[b1]).z, (this.xformPlanes[b1]).w);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  599 */     Transform3D transform3D = new Transform3D(paramTransform3D);
/*      */ 
/*      */ 
/*      */     
/*  603 */     transform3D.invert();
/*  604 */     transform3D.transpose();
/*      */     
/*  606 */     for (byte b = 0; b < 6; b++) {
/*  607 */       if ((paramInt & true << b) != 0) {
/*      */         
/*  609 */         Vector4d vector4d = new Vector4d((this.xformPlanes[b]).x, (this.xformPlanes[b]).y, (this.xformPlanes[b]).z, (this.xformPlanes[b]).w);
/*      */         
/*  611 */         vector4d.normalize();
/*  612 */         transform3D.transform(vector4d);
/*  613 */         Pipeline.getPipeline().updateModelClip(paramContext, b, true, vector4d.x, vector4d.y, vector4d.z, vector4d.w);
/*      */       } else {
/*      */         
/*  616 */         Pipeline.getPipeline().updateModelClip(paramContext, b, false, 0.0D, 0.0D, 0.0D, 0.0D);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void initMirrorObject(Object[] paramArrayOfObject) {
/*  623 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[2];
/*  624 */     Boolean bool = (Boolean)arrayOfObject[0];
/*  625 */     ArrayList arrayList = (ArrayList)arrayOfObject[1];
/*  626 */     BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)(Object[])paramArrayOfObject[4][0];
/*  627 */     Bounds bounds = (Bounds)(Object[])paramArrayOfObject[4][1];
/*      */     
/*  629 */     for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/*  630 */       Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList.get(b1)).source;
/*  631 */       shape3DRetained.addModelClip(this.mirrorModelClip);
/*      */     } 
/*  633 */     this.mirrorModelClip.isScoped = bool.booleanValue();
/*      */     
/*  635 */     if (boundingLeafRetained != null) {
/*  636 */       this.mirrorModelClip.boundingLeaf = boundingLeafRetained.mirrorBoundingLeaf;
/*  637 */       this.mirrorModelClip.region = this.boundingLeaf.transformedRegion;
/*      */     } else {
/*  639 */       this.mirrorModelClip.boundingLeaf = null;
/*  640 */       this.mirrorModelClip.region = null;
/*      */     } 
/*      */     
/*  643 */     if (bounds != null) {
/*  644 */       this.mirrorModelClip.regionOfInfluence = bounds;
/*  645 */       if (this.mirrorModelClip.region == null) {
/*  646 */         this.mirrorModelClip.region = (Bounds)this.regionOfInfluence.clone();
/*  647 */         this.mirrorModelClip.region.transform(this.regionOfInfluence, getLastLocalToVworld());
/*      */       } 
/*      */     } else {
/*      */       
/*  651 */       this.mirrorModelClip.regionOfInfluence = null;
/*      */     } 
/*  653 */     boolean[] arrayOfBoolean = (boolean[])(Object[])paramArrayOfObject[4][2];
/*      */     
/*  655 */     for (byte b2 = 0; b2 < arrayOfBoolean.length; b2++) {
/*  656 */       this.mirrorModelClip.enables[b2] = arrayOfBoolean[b2];
/*      */     }
/*  658 */     this.mirrorModelClip.enableFlag = this.mirrorModelClip.enables[0] | this.mirrorModelClip.enables[1] | this.mirrorModelClip.enables[2] | this.mirrorModelClip.enables[3] | this.mirrorModelClip.enables[4] | this.mirrorModelClip.enables[5];
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
/*      */   void updateMirrorObject(Object[] paramArrayOfObject) {
/*  670 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*  671 */     if ((i & 0x2) != 0) {
/*  672 */       Vector4d[] arrayOfVector4d = (Vector4d[])paramArrayOfObject[2];
/*      */       
/*  674 */       for (byte b = 0; b < 6; b++) {
/*  675 */         this.mirrorModelClip.xformPlanes[b].set(arrayOfVector4d[b]);
/*      */       }
/*      */     }
/*  678 */     else if ((i & true) != 0) {
/*  679 */       int j = ((Integer)paramArrayOfObject[2]).intValue();
/*      */       
/*  681 */       this.mirrorModelClip.xformPlanes[j].set((Vector4d)paramArrayOfObject[3]);
/*      */     }
/*  683 */     else if ((i & 0x80) != 0) {
/*  684 */       Vector4d[] arrayOfVector4d = (Vector4d[])paramArrayOfObject[3];
/*  685 */       for (byte b = 0; b < 6; b++) {
/*  686 */         this.mirrorModelClip.xformPlanes[b].set(arrayOfVector4d[b]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/*  693 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */ 
/*      */ 
/*      */     
/*  697 */     if ((i & 0x20) != 0) {
/*  698 */       this.mirrorModelClip.boundingLeaf = (BoundingLeafRetained)paramArrayOfObject[2];
/*  699 */       if (paramArrayOfObject[2] != null) {
/*  700 */         this.mirrorModelClip.region = this.mirrorModelClip.boundingLeaf.transformedRegion;
/*      */ 
/*      */       
/*      */       }
/*  704 */       else if (this.mirrorModelClip.regionOfInfluence != null) {
/*  705 */         this.mirrorModelClip.region = this.mirrorModelClip.regionOfInfluence.copy(this.mirrorModelClip.region);
/*      */         
/*  707 */         this.mirrorModelClip.region.transform(this.mirrorModelClip.regionOfInfluence, getCurrentLocalToVworld());
/*      */       }
/*      */       else {
/*      */         
/*  711 */         this.mirrorModelClip.region = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  717 */     if ((i & 0x10) != 0) {
/*  718 */       this.mirrorModelClip.regionOfInfluence = (Bounds)paramArrayOfObject[2];
/*  719 */       if (this.mirrorModelClip.boundingLeaf == null) {
/*  720 */         if (paramArrayOfObject[2] != null) {
/*  721 */           this.mirrorModelClip.region = this.mirrorModelClip.regionOfInfluence.copy(this.mirrorModelClip.region);
/*      */ 
/*      */           
/*  724 */           this.mirrorModelClip.region.transform(this.mirrorModelClip.regionOfInfluence, getCurrentLocalToVworld());
/*      */         }
/*      */         else {
/*      */           
/*  728 */           this.mirrorModelClip.region = null;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  733 */     if ((i & 0x40) != 0) {
/*  734 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[2];
/*  735 */       ArrayList arrayList1 = (ArrayList)arrayOfObject[0];
/*  736 */       ArrayList arrayList2 = (ArrayList)arrayOfObject[1];
/*  737 */       boolean bool = ((Boolean)arrayOfObject[2]).booleanValue();
/*      */       
/*  739 */       if (arrayList1 != null) {
/*  740 */         this.mirrorModelClip.isScoped = bool;
/*  741 */         for (byte b = 0; b < arrayList1.size(); b++) {
/*  742 */           Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b)).source;
/*  743 */           shape3DRetained.addModelClip(this.mirrorModelClip);
/*      */         } 
/*      */       } 
/*      */       
/*  747 */       if (arrayList2 != null) {
/*  748 */         this.mirrorModelClip.isScoped = bool;
/*  749 */         for (byte b = 0; b < arrayList2.size(); b++) {
/*  750 */           Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList2.get(b)).source;
/*  751 */           shape3DRetained.removeModelClip(this.mirrorModelClip);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  756 */     if ((i & 0x8) != 0) {
/*  757 */       Boolean[] arrayOfBoolean = (Boolean[])paramArrayOfObject[2];
/*      */       
/*  759 */       this.mirrorModelClip.enables[0] = arrayOfBoolean[0].booleanValue();
/*  760 */       this.mirrorModelClip.enables[1] = arrayOfBoolean[1].booleanValue();
/*  761 */       this.mirrorModelClip.enables[2] = arrayOfBoolean[2].booleanValue();
/*  762 */       this.mirrorModelClip.enables[3] = arrayOfBoolean[3].booleanValue();
/*  763 */       this.mirrorModelClip.enables[4] = arrayOfBoolean[4].booleanValue();
/*  764 */       this.mirrorModelClip.enables[5] = arrayOfBoolean[5].booleanValue();
/*  765 */       this.mirrorModelClip.enableFlag = this.mirrorModelClip.enables[0] | this.mirrorModelClip.enables[1] | this.mirrorModelClip.enables[2] | this.mirrorModelClip.enables[3] | this.mirrorModelClip.enables[4] | this.mirrorModelClip.enables[5];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  771 */     else if ((i & 0x4) != 0) {
/*  772 */       int j = ((Integer)paramArrayOfObject[2]).intValue();
/*      */       
/*  774 */       this.mirrorModelClip.enables[j] = ((Boolean)paramArrayOfObject[3]).booleanValue();
/*  775 */       this.mirrorModelClip.enableFlag = this.mirrorModelClip.enables[0] | this.mirrorModelClip.enables[1] | this.mirrorModelClip.enables[2] | this.mirrorModelClip.enables[3] | this.mirrorModelClip.enables[4] | this.mirrorModelClip.enables[5];
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
/*      */   void updateBoundingLeaf() {
/*  790 */     if (this.boundingLeaf != null && this.boundingLeaf.switchState.currentSwitchOn) {
/*  791 */       this.region = this.boundingLeaf.transformedRegion;
/*      */     }
/*  793 */     else if (this.regionOfInfluence != null) {
/*  794 */       this.region = this.regionOfInfluence.copy(this.region);
/*  795 */       this.region.transform(this.regionOfInfluence, getCurrentLocalToVworld());
/*      */     } else {
/*  797 */       this.region = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(SetLiveState paramSetLiveState) {
/*  806 */     doSetLive(paramSetLiveState);
/*      */     
/*  808 */     if (this.inSharedGroup) {
/*  809 */       throw new IllegalSharingException(J3dI18N.getString("ModelClipRetained1"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  816 */     if (this.mirrorModelClip == null) {
/*  817 */       this.mirrorModelClip = (ModelClipRetained)clone();
/*  818 */       this.mirrorModelClip.boundingLeaf = null;
/*  819 */       this.mirrorModelClip.sgModelClip = this;
/*      */     } 
/*  821 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/*  822 */       paramSetLiveState.viewScopedNodeList.add(this.mirrorModelClip);
/*  823 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*      */     } else {
/*  825 */       paramSetLiveState.nodeList.add(this.mirrorModelClip);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  830 */     if (this.boundingLeaf != null) {
/*  831 */       this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorModelClip);
/*      */     }
/*      */     
/*  834 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */     {
/*  836 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorModelClip, 1);
/*      */     }
/*  838 */     this.mirrorModelClip.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*      */ 
/*      */     
/*  841 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/*  842 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorModelClip, 1);
/*  843 */       paramSetLiveState.notifyThreads |= 0x2000;
/*      */     } 
/*      */     
/*  846 */     paramSetLiveState.notifyThreads |= 0x1080;
/*      */     
/*  848 */     markAsLive();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  853 */     J3dMessage j3dMessage = new J3dMessage();
/*  854 */     j3dMessage.threads = 4096;
/*  855 */     j3dMessage.universe = this.universe;
/*  856 */     j3dMessage.type = 36;
/*  857 */     j3dMessage.args[0] = this;
/*      */ 
/*      */     
/*  860 */     j3dMessage.args[1] = new Integer(128);
/*  861 */     ArrayList arrayList = new ArrayList();
/*  862 */     for (byte b = 0; b < this.scopes.size(); b++) {
/*  863 */       GroupRetained groupRetained = (GroupRetained)this.scopes.get(b);
/*  864 */       this.tempKey.reset();
/*  865 */       groupRetained.addAllNodesForScopedModelClip(this.mirrorModelClip, arrayList, this.tempKey);
/*      */     } 
/*  867 */     Object[] arrayOfObject1 = new Object[2];
/*  868 */     arrayOfObject1[0] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  869 */     arrayOfObject1[1] = arrayList;
/*  870 */     j3dMessage.args[2] = arrayOfObject1;
/*  871 */     j3dMessage.args[3] = this.xformPlanes.clone();
/*      */     
/*  873 */     Object[] arrayOfObject2 = new Object[3];
/*  874 */     arrayOfObject2[0] = this.boundingLeaf;
/*  875 */     arrayOfObject2[1] = (this.regionOfInfluence != null) ? this.regionOfInfluence.clone() : null;
/*  876 */     arrayOfObject2[2] = this.enables.clone();
/*  877 */     j3dMessage.args[4] = arrayOfObject2;
/*  878 */     VirtualUniverse.mc.processMessage(j3dMessage);
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
/*  890 */     super.clearLive(paramSetLiveState);
/*  891 */     paramSetLiveState.notifyThreads |= 0x1080;
/*      */     
/*  893 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */     {
/*  895 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorModelClip, 1);
/*      */     }
/*      */     
/*  898 */     if (this.mirrorModelClip.boundingLeaf != null) {
/*  899 */       this.mirrorModelClip.boundingLeaf.removeUser(this.mirrorModelClip);
/*      */     }
/*  901 */     if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/*  902 */       paramSetLiveState.viewScopedNodeList.add(this.mirrorModelClip);
/*  903 */       paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*      */     } else {
/*  905 */       paramSetLiveState.nodeList.add(this.mirrorModelClip);
/*      */     } 
/*  907 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/*  908 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorModelClip, 1);
/*  909 */       paramSetLiveState.notifyThreads |= 0x2000;
/*      */     } 
/*      */ 
/*      */     
/*  913 */     if (this.scopes.size() > 0) {
/*  914 */       J3dMessage j3dMessage = new J3dMessage();
/*  915 */       j3dMessage.threads = 4096;
/*  916 */       j3dMessage.universe = this.universe;
/*  917 */       j3dMessage.type = 36;
/*  918 */       j3dMessage.args[0] = this;
/*  919 */       j3dMessage.args[1] = new Integer(256);
/*  920 */       ArrayList arrayList = new ArrayList();
/*  921 */       for (byte b = 0; b < this.scopes.size(); b++) {
/*  922 */         GroupRetained groupRetained = (GroupRetained)this.scopes.get(b);
/*  923 */         this.tempKey.reset();
/*  924 */         groupRetained.removeAllNodesForScopedModelClip(this.mirrorModelClip, arrayList, this.tempKey);
/*      */       } 
/*  926 */       j3dMessage.args[2] = arrayList;
/*  927 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void clearMirrorObject(Object[] paramArrayOfObject) {
/*  934 */     ArrayList arrayList1 = (ArrayList)paramArrayOfObject[2];
/*  935 */     ArrayList arrayList2 = new ArrayList();
/*      */     
/*  937 */     for (byte b = 0; b < arrayList1.size(); b++) {
/*  938 */       Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b)).source;
/*  939 */       shape3DRetained.removeModelClip(this.mirrorModelClip);
/*      */     } 
/*      */     
/*  942 */     this.mirrorModelClip.isScoped = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object clone() {
/*  949 */     ModelClipRetained modelClipRetained = (ModelClipRetained)super.clone();
/*      */     
/*  951 */     modelClipRetained.planes = new Vector4d[6];
/*  952 */     for (byte b = 0; b < 6; b++) {
/*  953 */       modelClipRetained.planes[b] = new Vector4d(this.planes[b]);
/*  954 */       modelClipRetained.xformPlanes[b] = new Vector4d(this.xformPlanes[b]);
/*      */     } 
/*      */     
/*  957 */     modelClipRetained.enables = new boolean[6];
/*  958 */     getEnables(modelClipRetained.enables);
/*      */ 
/*      */     
/*  961 */     modelClipRetained.enableFlag = modelClipRetained.enables[0] | modelClipRetained.enables[1] | modelClipRetained.enables[2] | modelClipRetained.enables[3] | modelClipRetained.enables[4] | modelClipRetained.enables[5];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  968 */     modelClipRetained.inImmCtx = false;
/*  969 */     modelClipRetained.region = null;
/*  970 */     modelClipRetained.sgModelClip = null;
/*  971 */     modelClipRetained.mirrorModelClip = null;
/*  972 */     modelClipRetained.environmentSets = new UnorderList(1, EnvironmentSet.class);
/*      */     
/*  974 */     if (this.regionOfInfluence != null) {
/*  975 */       modelClipRetained.regionOfInfluence = (Bounds)this.regionOfInfluence.clone();
/*      */     }
/*      */     
/*  978 */     return modelClipRetained;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateImmediateTransformChange() {
/*  985 */     if (this.boundingLeaf == null && 
/*  986 */       this.regionOfInfluence != null) {
/*  987 */       this.region = this.regionOfInfluence.copy(this.region);
/*  988 */       this.region.transform(this.regionOfInfluence, this.sgModelClip.getCurrentLocalToVworld());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  998 */   void printPlane(int paramInt, String paramString) { System.err.println(paramString + " : < " + this.planes[paramInt].toString() + " > " + this.enables[paramInt]); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void printPlanes(String paramString, Vector4d[] paramArrayOfVector4d) {
/* 1004 */     System.err.println(paramString);
/* 1005 */     printPlane(0, "[0]");
/* 1006 */     printPlane(1, "[1]");
/* 1007 */     printPlane(2, "[2]");
/* 1008 */     printPlane(3, "[3]");
/* 1009 */     printPlane(4, "[4]");
/* 1010 */     printPlane(5, "[5]");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void printEnables(String paramString, boolean[] paramArrayOfBoolean) {
/* 1016 */     System.err.println(paramString);
/* 1017 */     System.err.println("[0] : < " + paramArrayOfBoolean[0] + " >");
/* 1018 */     System.err.println("[1] : < " + paramArrayOfBoolean[1] + " >");
/* 1019 */     System.err.println("[2] : < " + paramArrayOfBoolean[2] + " >");
/* 1020 */     System.err.println("[3] : < " + paramArrayOfBoolean[3] + " >");
/* 1021 */     System.err.println("[4] : < " + paramArrayOfBoolean[4] + " >");
/* 1022 */     System.err.println("[5] : < " + paramArrayOfBoolean[5] + " >");
/*      */   }
/*      */   
/*      */   final void sendMessage(int paramInt, Object paramObject1, Object paramObject2) {
/* 1026 */     J3dMessage j3dMessage = new J3dMessage();
/* 1027 */     j3dMessage.threads = 4224;
/* 1028 */     j3dMessage.type = 36;
/* 1029 */     j3dMessage.universe = this.universe;
/* 1030 */     j3dMessage.args[0] = this;
/* 1031 */     j3dMessage.args[1] = new Integer(paramInt);
/* 1032 */     j3dMessage.args[2] = paramObject1;
/* 1033 */     j3dMessage.args[3] = paramObject2;
/* 1034 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */   
/*      */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 1038 */     super.mergeTransform(paramTransformGroupRetained);
/*      */     
/* 1040 */     if (this.regionOfInfluence != null) {
/* 1041 */       this.regionOfInfluence.transform(paramTransformGroupRetained.transform);
/*      */     }
/*      */     
/* 1044 */     Transform3D transform3D = paramTransformGroupRetained.getNormalTransform();
/* 1045 */     for (byte b = 0; b < 6; b++) {
/* 1046 */       transform3D.transform(this.planes[b], this.xformPlanes[b]);
/*      */     }
/*      */   }
/*      */   
/* 1050 */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) { paramArrayList.add(this.mirrorModelClip); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ModelClipRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */