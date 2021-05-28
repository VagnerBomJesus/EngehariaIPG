/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Color3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class LightRetained
/*      */   extends LeafRetained
/*      */ {
/*      */   static final int ENABLE_CHANGED = 1;
/*      */   static final int SCOPE_CHANGED = 2;
/*      */   static final int BOUNDS_CHANGED = 4;
/*      */   static final int COLOR_CHANGED = 8;
/*      */   static final int BOUNDINGLEAF_CHANGED = 16;
/*      */   static final int INIT_MIRROR = 32;
/*      */   static final int CLEAR_MIRROR = 64;
/*      */   static final int LAST_DEFINED_BIT = 64;
/*      */   boolean lightOn = true;
/*   41 */   Color3f color = new Color3f(1.0F, 1.0F, 1.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   46 */   Vector scopes = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   51 */   Bounds regionOfInfluence = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   56 */   BoundingLeafRetained boundingLeaf = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   61 */   Bounds region = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   66 */   int lightDirty = 65535;
/*      */ 
/*      */   
/*   69 */   int sgLightDirty = 65535;
/*      */ 
/*      */   
/*   72 */   int lightType = -1;
/*      */ 
/*      */   
/*      */   boolean isNeeded = false;
/*      */ 
/*      */   
/*      */   boolean inImmCtx = false;
/*      */ 
/*      */   
/*   81 */   LightRetained sgLight = null;
/*      */ 
/*      */   
/*   84 */   HashKey key = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   89 */   LightRetained[] mirrorLights = new LightRetained[1];
/*      */ 
/*      */   
/*   92 */   int numMirrorLights = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isScoped = false;
/*      */ 
/*      */   
/*   99 */   HashKey tempKey = new HashKey(250);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   UnorderList environmentSets = new UnorderList(1, EnvironmentSet.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isViewScoped = false;
/*      */ 
/*      */ 
/*      */   
/*  116 */   ArrayList newlyAddedMirrorLights = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final int targetThreads = 4224;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   void initEnable(boolean paramBoolean) { this.lightOn = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setEnable(boolean paramBoolean) {
/*  135 */     initEnable(paramBoolean);
/*  136 */     sendMessage(1, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   boolean getEnable() { return this.lightOn; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  154 */   void initColor(Color3f paramColor3f) { this.color.set(paramColor3f); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setColor(Color3f paramColor3f) {
/*  162 */     initColor(paramColor3f);
/*  163 */     sendMessage(8, new Color3f(paramColor3f));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  171 */   void getColor(Color3f paramColor3f) { paramColor3f.set(this.color); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initScope(Group paramGroup, int paramInt) {
/*  180 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*  181 */     this.scopes.setElementAt(groupRetained, paramInt);
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
/*      */   void setScope(Group paramGroup, int paramInt) {
/*  193 */     ArrayList arrayList1 = new ArrayList();
/*  194 */     ArrayList arrayList2 = new ArrayList();
/*      */     
/*  196 */     Object[] arrayOfObject = new Object[3];
/*      */     
/*  198 */     GroupRetained groupRetained = (GroupRetained)this.scopes.get(paramInt);
/*  199 */     this.tempKey.reset();
/*  200 */     groupRetained.removeAllNodesForScopedLight(this.inSharedGroup ? this.numMirrorLights : 1, this.mirrorLights, arrayList2, this.tempKey);
/*      */ 
/*      */     
/*  203 */     groupRetained = (GroupRetained)paramGroup.retained;
/*  204 */     this.tempKey.reset();
/*      */ 
/*      */ 
/*      */     
/*  208 */     groupRetained.addAllNodesForScopedLight(this.inSharedGroup ? this.numMirrorLights : 1, this.mirrorLights, arrayList1, this.tempKey);
/*      */ 
/*      */     
/*  211 */     initScope(paramGroup, paramInt);
/*  212 */     J3dMessage j3dMessage = new J3dMessage();
/*  213 */     arrayOfObject[0] = arrayList1;
/*  214 */     arrayOfObject[1] = arrayList2;
/*  215 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  216 */     sendMessage(2, arrayOfObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initInsertScope(Group paramGroup, int paramInt) {
/*  225 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*  226 */     this.scopes.insertElementAt(groupRetained, paramInt);
/*  227 */     groupRetained.setLightScope();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void insertScope(Group paramGroup, int paramInt) {
/*  237 */     Object[] arrayOfObject = new Object[3];
/*  238 */     ArrayList arrayList = new ArrayList();
/*  239 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*      */     
/*  241 */     this.tempKey.reset();
/*  242 */     groupRetained.addAllNodesForScopedLight(this.inSharedGroup ? this.numMirrorLights : 1, this.mirrorLights, arrayList, this.tempKey);
/*      */     
/*  244 */     initInsertScope(paramGroup, paramInt);
/*  245 */     arrayOfObject[0] = arrayList;
/*  246 */     arrayOfObject[1] = null;
/*  247 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  248 */     sendMessage(2, arrayOfObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initRemoveScope(int paramInt) {
/*  257 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/*  258 */     this.scopes.removeElementAt(paramInt);
/*  259 */     groupRetained.removeLightScope();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeScope(int paramInt) {
/*  269 */     Object[] arrayOfObject = new Object[3];
/*  270 */     ArrayList arrayList = new ArrayList();
/*      */     
/*  272 */     GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(paramInt);
/*  273 */     this.tempKey.reset();
/*  274 */     groupRetained.removeAllNodesForScopedLight(this.inSharedGroup ? this.numMirrorLights : 1, this.mirrorLights, arrayList, this.tempKey);
/*  275 */     initRemoveScope(paramInt); arrayOfObject[0] = null;
/*  276 */     arrayOfObject[1] = arrayList;
/*  277 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  278 */     sendMessage(2, arrayOfObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeScope(Group paramGroup) {
/*  287 */     int i = indexOfScope(paramGroup);
/*  288 */     if (i >= 0)
/*  289 */       removeScope(i); 
/*      */   }
/*      */   
/*      */   void initRemoveScope(Group paramGroup) {
/*  293 */     int i = indexOfScope(paramGroup);
/*  294 */     if (i >= 0) {
/*  295 */       initRemoveScope(i);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeAllScopes() {
/*  302 */     int i = this.scopes.size();
/*  303 */     Object[] arrayOfObject = new Object[3];
/*  304 */     ArrayList arrayList = new ArrayList();
/*      */ 
/*      */     
/*  307 */     for (int j = i - 1; j >= 0; j--) {
/*  308 */       GroupRetained groupRetained = (GroupRetained)this.scopes.elementAt(j);
/*  309 */       this.tempKey.reset();
/*  310 */       groupRetained.removeAllNodesForScopedLight(this.inSharedGroup ? this.numMirrorLights : 1, this.mirrorLights, arrayList, this.tempKey);
/*  311 */       initRemoveScope(j);
/*      */     } 
/*  313 */     arrayOfObject[0] = null;
/*  314 */     arrayOfObject[1] = arrayList;
/*  315 */     arrayOfObject[2] = Boolean.FALSE;
/*  316 */     sendMessage(2, arrayOfObject);
/*      */   }
/*      */   
/*      */   void initRemoveAllScopes() {
/*  320 */     int i = this.scopes.size();
/*  321 */     for (int j = i - 1; j >= 0; j--) {
/*  322 */       initRemoveScope(j);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  331 */   Group getScope(int paramInt) { return (Group)((GroupRetained)this.scopes.elementAt(paramInt)).source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Enumeration getAllScopes() {
/*  339 */     Enumeration enumeration = this.scopes.elements();
/*  340 */     Vector vector = new Vector(this.scopes.size());
/*  341 */     while (enumeration.hasMoreElements()) {
/*  342 */       vector.add(((GroupRetained)enumeration.nextElement()).source);
/*      */     }
/*  344 */     return vector.elements();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initAddScope(Group paramGroup) {
/*  352 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*  353 */     this.scopes.addElement(groupRetained);
/*  354 */     groupRetained.setLightScope();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addScope(Group paramGroup) {
/*  362 */     Object[] arrayOfObject = new Object[3];
/*  363 */     ArrayList arrayList = new ArrayList();
/*  364 */     GroupRetained groupRetained = (GroupRetained)paramGroup.retained;
/*      */     
/*  366 */     initAddScope(paramGroup);
/*  367 */     this.tempKey.reset();
/*  368 */     groupRetained.addAllNodesForScopedLight(this.inSharedGroup ? this.numMirrorLights : 1, this.mirrorLights, arrayList, this.tempKey);
/*  369 */     arrayOfObject[0] = arrayList;
/*  370 */     arrayOfObject[1] = null;
/*  371 */     arrayOfObject[2] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  372 */     sendMessage(2, arrayOfObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  380 */   int numScopes() { return this.scopes.size(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int indexOfScope(Group paramGroup) {
/*  388 */     if (paramGroup != null) {
/*  389 */       return this.scopes.indexOf((GroupRetained)paramGroup.retained);
/*      */     }
/*  391 */     return this.scopes.indexOf(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initInfluencingBounds(Bounds paramBounds) {
/*  399 */     if (paramBounds != null) {
/*  400 */       this.regionOfInfluence = (Bounds)paramBounds.clone();
/*  401 */       if (this.staticTransform != null) {
/*  402 */         this.regionOfInfluence.transform(this.staticTransform.transform);
/*      */       }
/*      */     } else {
/*  405 */       this.regionOfInfluence = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setInfluencingBounds(Bounds paramBounds) {
/*  415 */     initInfluencingBounds(paramBounds);
/*  416 */     sendMessage(4, (paramBounds != null) ? paramBounds.clone() : null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds getInfluencingBounds() {
/*  425 */     Bounds bounds = null;
/*      */     
/*  427 */     if (this.regionOfInfluence != null) {
/*  428 */       bounds = (Bounds)this.regionOfInfluence.clone();
/*  429 */       if (this.staticTransform != null) {
/*  430 */         Transform3D transform3D = this.staticTransform.getInvTransform();
/*  431 */         bounds.transform(transform3D);
/*      */       } 
/*      */     } 
/*  434 */     return bounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/*  441 */     if (paramBoundingLeaf != null) {
/*  442 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*      */     } else {
/*  444 */       this.boundingLeaf = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setInfluencingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/*  454 */     int i = this.numMirrorLights;
/*  455 */     if (this.numMirrorLights == 0) {
/*  456 */       i = 1;
/*      */     }
/*  458 */     if (this.boundingLeaf != null)
/*      */     {
/*  460 */       for (byte b = 0; b < i; b++) {
/*  461 */         this.boundingLeaf.mirrorBoundingLeaf.removeUser(this.mirrorLights[b]);
/*      */       }
/*      */     }
/*      */     
/*  465 */     if (paramBoundingLeaf != null) {
/*  466 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*      */       
/*  468 */       for (byte b = 0; b < i; b++) {
/*  469 */         this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorLights[b]);
/*      */       }
/*      */     } else {
/*  472 */       this.boundingLeaf = null;
/*      */     } 
/*      */     
/*  475 */     sendMessage(16, (this.boundingLeaf != null) ? this.boundingLeaf.mirrorBoundingLeaf : null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  484 */   BoundingLeaf getInfluencingBoundingLeaf() { return (this.boundingLeaf != null) ? (BoundingLeaf)this.boundingLeaf.source : null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  492 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  499 */   boolean getInImmCtx() { return this.inImmCtx; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initMirrorObject(Object[] paramArrayOfObject) {
/*  506 */     Object[] arrayOfObject = (Object[])(Object[])paramArrayOfObject[4][5];
/*  507 */     ArrayList arrayList = (ArrayList)arrayOfObject[1];
/*  508 */     Boolean bool = (Boolean)arrayOfObject[0];
/*  509 */     BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)(Object[])paramArrayOfObject[4][0];
/*  510 */     Bounds bounds = (Bounds)(Object[])paramArrayOfObject[4][1];
/*  511 */     int i = ((Integer)paramArrayOfObject[2]).intValue();
/*  512 */     LightRetained[] arrayOfLightRetained = (LightRetained[])paramArrayOfObject[3];
/*      */     
/*      */     byte b;
/*  515 */     for (b = 0; b < i; b++) {
/*  516 */       for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/*  517 */         Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList.get(b1)).source;
/*  518 */         shape3DRetained.addLight(arrayOfLightRetained[b]);
/*      */       } 
/*  520 */       (arrayOfLightRetained[b]).isScoped = bool.booleanValue();
/*      */     } 
/*      */     
/*  523 */     for (b = 0; b < i; b++) {
/*  524 */       (arrayOfLightRetained[b]).inBackgroundGroup = ((Boolean)(Object[])paramArrayOfObject[4][2]).booleanValue();
/*  525 */       (arrayOfLightRetained[b]).geometryBackground = (BackgroundRetained)(Object[])paramArrayOfObject[4][3];
/*      */ 
/*      */       
/*  528 */       if (boundingLeafRetained != null) {
/*  529 */         (arrayOfLightRetained[b]).boundingLeaf = boundingLeafRetained.mirrorBoundingLeaf;
/*  530 */         (arrayOfLightRetained[b]).region = (arrayOfLightRetained[b]).boundingLeaf.transformedRegion;
/*      */       } else {
/*  532 */         (arrayOfLightRetained[b]).boundingLeaf = null;
/*  533 */         (arrayOfLightRetained[b]).region = null;
/*      */       } 
/*      */       
/*  536 */       if (bounds != null) {
/*  537 */         (arrayOfLightRetained[b]).regionOfInfluence = bounds;
/*  538 */         if ((arrayOfLightRetained[b]).region == null) {
/*  539 */           (arrayOfLightRetained[b]).region = (Bounds)this.regionOfInfluence.clone();
/*  540 */           (arrayOfLightRetained[b]).region.transform(this.regionOfInfluence, getLastLocalToVworld());
/*      */         } 
/*      */       } else {
/*      */         
/*  544 */         (arrayOfLightRetained[b]).regionOfInfluence = null;
/*      */       } 
/*  546 */       (arrayOfLightRetained[b]).lightOn = ((Boolean)(Object[])paramArrayOfObject[4][4]).booleanValue();
/*      */     } 
/*      */ 
/*      */     
/*  550 */     if (this instanceof AmbientLightRetained) {
/*  551 */       Color3f color3f = (Color3f)(Object[])paramArrayOfObject[4][6];
/*  552 */       for (byte b1 = 0; b1 < i; b1++) {
/*  553 */         (arrayOfLightRetained[b1]).color.set(color3f);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void update(Context paramContext, int paramInt, double paramDouble);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/*  570 */     Object object = null;
/*  571 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*  572 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/*  573 */     LightRetained[] arrayOfLightRetained = (LightRetained[])paramArrayOfObject[3];
/*      */ 
/*      */     
/*  576 */     if ((i & 0x8) != 0) {
/*  577 */       for (byte b = 0; b < j; b++) {
/*  578 */         (arrayOfLightRetained[b]).color.set((Color3f)paramArrayOfObject[4]);
/*      */       }
/*      */     }
/*  581 */     else if ((i & true) != 0) {
/*  582 */       for (byte b = 0; b < j; b++) {
/*  583 */         (arrayOfLightRetained[b]).lightOn = ((Boolean)paramArrayOfObject[4]).booleanValue();
/*      */       }
/*  585 */     } else if ((i & 0x4) != 0) {
/*  586 */       for (byte b = 0; b < j; b++) {
/*  587 */         (arrayOfLightRetained[b]).regionOfInfluence = (Bounds)paramArrayOfObject[4];
/*  588 */         if ((arrayOfLightRetained[b]).boundingLeaf == null) {
/*  589 */           if (paramArrayOfObject[4] != null) {
/*  590 */             (arrayOfLightRetained[b]).region = (arrayOfLightRetained[b]).regionOfInfluence.copy((arrayOfLightRetained[b]).region);
/*      */             
/*  592 */             (arrayOfLightRetained[b]).region.transform((arrayOfLightRetained[b]).regionOfInfluence, arrayOfLightRetained[b].getCurrentLocalToVworld());
/*      */           }
/*      */           else {
/*      */             
/*  596 */             (arrayOfLightRetained[b]).region = null;
/*      */           }
/*      */         
/*      */         }
/*      */       } 
/*  601 */     } else if ((i & 0x10) != 0) {
/*  602 */       for (byte b = 0; b < j; b++) {
/*  603 */         (arrayOfLightRetained[b]).boundingLeaf = (BoundingLeafRetained)paramArrayOfObject[4];
/*  604 */         if (paramArrayOfObject[4] != null) {
/*  605 */           (arrayOfLightRetained[b]).region = (arrayOfLightRetained[b]).boundingLeaf.transformedRegion;
/*      */         
/*      */         }
/*  608 */         else if ((arrayOfLightRetained[b]).regionOfInfluence != null) {
/*  609 */           (arrayOfLightRetained[b]).region = (arrayOfLightRetained[b]).regionOfInfluence.copy((arrayOfLightRetained[b]).region);
/*      */           
/*  611 */           (arrayOfLightRetained[b]).region.transform((arrayOfLightRetained[b]).regionOfInfluence, arrayOfLightRetained[b].getCurrentLocalToVworld());
/*      */         }
/*      */         else {
/*      */           
/*  615 */           (arrayOfLightRetained[b]).region = null;
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  620 */     } else if ((i & 0x2) != 0) {
/*      */ 
/*      */ 
/*      */       
/*  624 */       Object[] arrayOfObject = (Object[])paramArrayOfObject[4];
/*  625 */       ArrayList arrayList1 = (ArrayList)arrayOfObject[0];
/*  626 */       ArrayList arrayList2 = (ArrayList)arrayOfObject[1];
/*  627 */       boolean bool = ((Boolean)arrayOfObject[2]).booleanValue();
/*      */       
/*  629 */       if (arrayList1 != null) {
/*  630 */         for (byte b = 0; b < j; b++) {
/*  631 */           (arrayOfLightRetained[b]).isScoped = bool;
/*  632 */           for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/*  633 */             Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b1)).source;
/*  634 */             shape3DRetained.addLight(arrayOfLightRetained[b]);
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*  639 */       if (arrayList2 != null) {
/*  640 */         for (byte b = 0; b < j; b++) {
/*  641 */           (arrayOfLightRetained[b]).isScoped = bool;
/*  642 */           for (byte b1 = 0; b1 < arrayList2.size(); b1++) {
/*  643 */             Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList2.get(b1)).source;
/*  644 */             shape3DRetained.removeLight(arrayOfLightRetained[b]);
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
/*      */   void updateMirrorObject(Object[] paramArrayOfObject) {
/*  660 */     Object object = null;
/*  661 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*  662 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/*  663 */     LightRetained[] arrayOfLightRetained = (LightRetained[])paramArrayOfObject[3];
/*      */     
/*  665 */     if ((i & 0x8) != 0) {
/*  666 */       for (byte b = 0; b < j; b++) {
/*  667 */         (arrayOfLightRetained[b]).color.set((Color3f)paramArrayOfObject[4]);
/*      */       }
/*      */     }
/*      */     
/*  671 */     if ((i & 0x20) != 0) {
/*  672 */       for (byte b = 0; b < j; b++) {
/*  673 */         Color3f color3f = (Color3f)(Object[])paramArrayOfObject[4][6];
/*  674 */         (arrayOfLightRetained[b]).color.set(color3f);
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
/*      */   void updateBoundingLeaf() {
/*  687 */     if (this.boundingLeaf != null && this.boundingLeaf.switchState.currentSwitchOn) {
/*  688 */       this.region = this.boundingLeaf.transformedRegion;
/*      */     }
/*  690 */     else if (this.regionOfInfluence != null) {
/*  691 */       this.region = this.regionOfInfluence.copy(this.region);
/*  692 */       this.region.transform(this.regionOfInfluence, getCurrentLocalToVworld());
/*      */     } else {
/*  694 */       this.region = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) {
/*  699 */     if (!this.inSharedGroup) {
/*  700 */       paramArrayList.add(this.mirrorLights[0]);
/*      */     } else {
/*      */       
/*  703 */       for (byte b = 0; b < this.numMirrorLights; b++) {
/*  704 */         if ((this.mirrorLights[b]).key.equals(paramHashKey)) {
/*  705 */           paramArrayList.add(this.mirrorLights[b]);
/*      */           break;
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
/*      */   LightRetained getMirrorLight(HashKey paramHashKey) {
/*  719 */     if (this.inSharedGroup) {
/*  720 */       byte b; for (b = 0; b < this.numMirrorLights; b++) {
/*  721 */         if ((this.mirrorLights[b]).key.equals(paramHashKey)) {
/*  722 */           return this.mirrorLights[b];
/*      */         }
/*      */       } 
/*  725 */       if (this.numMirrorLights == this.mirrorLights.length) {
/*  726 */         LightRetained[] arrayOfLightRetained = new LightRetained[this.numMirrorLights * 2];
/*  727 */         for (b = 0; b < this.numMirrorLights; b++) {
/*  728 */           arrayOfLightRetained[b] = this.mirrorLights[b];
/*      */         }
/*  730 */         this.mirrorLights = arrayOfLightRetained;
/*      */       } 
/*      */ 
/*      */       
/*  734 */       this.mirrorLights[this.numMirrorLights] = (LightRetained)clone();
/*      */ 
/*      */       
/*  737 */       if (this.boundingLeaf != null) {
/*  738 */         (this.mirrorLights[this.numMirrorLights]).boundingLeaf = this.boundingLeaf.mirrorBoundingLeaf;
/*  739 */         if ((this.mirrorLights[this.numMirrorLights]).boundingLeaf != null) {
/*  740 */           (this.mirrorLights[this.numMirrorLights]).boundingLeaf.addUser(this.mirrorLights[this.numMirrorLights]);
/*      */         }
/*      */       } 
/*  743 */       (this.mirrorLights[this.numMirrorLights]).key = paramHashKey;
/*  744 */       (this.mirrorLights[this.numMirrorLights]).sgLight = this;
/*  745 */       return this.mirrorLights[this.numMirrorLights++];
/*      */     } 
/*  747 */     if (this.mirrorLights[false] == null) {
/*      */       
/*  749 */       this.mirrorLights[0] = (LightRetained)clone();
/*      */ 
/*      */       
/*  752 */       if (this.boundingLeaf != null) {
/*  753 */         (this.mirrorLights[0]).boundingLeaf = this.boundingLeaf.mirrorBoundingLeaf;
/*  754 */         if ((this.mirrorLights[false]).boundingLeaf != null)
/*  755 */           (this.mirrorLights[0]).boundingLeaf.addUser(this.mirrorLights[0]); 
/*      */       } 
/*  757 */       (this.mirrorLights[0]).sgLight = this;
/*      */     } 
/*  759 */     return this.mirrorLights[0];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(SetLiveState paramSetLiveState) {
/*  767 */     this.newlyAddedMirrorLights.clear();
/*  768 */     if (this.inImmCtx) {
/*  769 */       throw new IllegalSharingException(J3dI18N.getString("LightRetained0"));
/*      */     }
/*  771 */     doSetLive(paramSetLiveState);
/*      */     
/*  773 */     if (paramSetLiveState.inSharedGroup) {
/*  774 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/*  775 */         LightRetained lightRetained = getMirrorLight(paramSetLiveState.keys[b]);
/*  776 */         lightRetained.localToVworld = new Transform3D[1][];
/*  777 */         lightRetained.localToVworldIndex = new int[1][];
/*      */         
/*  779 */         int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */         
/*  781 */         if (i < 0) {
/*  782 */           System.err.println("LightRetained : Can't find hashKey");
/*      */         }
/*      */         
/*  785 */         lightRetained.localToVworld[0] = this.localToVworld[i];
/*  786 */         lightRetained.localToVworldIndex[0] = this.localToVworldIndex[i];
/*      */ 
/*      */         
/*  789 */         if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/*  790 */           paramSetLiveState.viewScopedNodeList.add(lightRetained);
/*  791 */           paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(b));
/*      */         } else {
/*  793 */           paramSetLiveState.nodeList.add(lightRetained);
/*      */         } 
/*      */         
/*  796 */         this.newlyAddedMirrorLights.add(lightRetained);
/*  797 */         if (this.boundingLeaf != null) {
/*  798 */           this.boundingLeaf.mirrorBoundingLeaf.addUser(lightRetained);
/*      */         }
/*  800 */         if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null) {
/*      */           
/*  802 */           paramSetLiveState.transformTargets[b].addNode(lightRetained, 1);
/*  803 */           paramSetLiveState.notifyThreads |= 0x2000;
/*      */         } 
/*  805 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b] != null)
/*      */         {
/*  807 */           paramSetLiveState.switchTargets[b].addNode(lightRetained, 1);
/*      */         }
/*  809 */         lightRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(i);
/*      */       } 
/*      */     } else {
/*      */       
/*  813 */       LightRetained lightRetained = getMirrorLight(null);
/*  814 */       lightRetained.localToVworld = new Transform3D[1][];
/*  815 */       lightRetained.localToVworldIndex = new int[1][];
/*  816 */       lightRetained.localToVworld[0] = this.localToVworld[0];
/*  817 */       lightRetained.localToVworldIndex[0] = this.localToVworldIndex[0];
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  822 */       if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/*  823 */         paramSetLiveState.viewScopedNodeList.add(lightRetained);
/*  824 */         paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*      */       } else {
/*  826 */         paramSetLiveState.nodeList.add(lightRetained);
/*      */       } 
/*  828 */       this.newlyAddedMirrorLights.add(lightRetained);
/*  829 */       if (this.boundingLeaf != null) {
/*  830 */         this.boundingLeaf.mirrorBoundingLeaf.addUser(lightRetained);
/*      */       }
/*  832 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/*      */         
/*  834 */         paramSetLiveState.transformTargets[0].addNode(lightRetained, 1);
/*  835 */         paramSetLiveState.notifyThreads |= 0x2000;
/*      */       } 
/*  837 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */       {
/*  839 */         paramSetLiveState.switchTargets[0].addNode(lightRetained, 1);
/*      */       }
/*  841 */       lightRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/*      */     } 
/*  843 */     paramSetLiveState.notifyThreads |= 0x1080;
/*      */     
/*  845 */     markAsLive();
/*      */   }
/*      */ 
/*      */   
/*      */   J3dMessage initMessage(int paramInt) {
/*  850 */     J3dMessage j3dMessage = new J3dMessage();
/*  851 */     j3dMessage.threads = 4096;
/*  852 */     j3dMessage.universe = this.universe;
/*  853 */     j3dMessage.type = 19;
/*  854 */     j3dMessage.args[0] = this;
/*      */ 
/*      */     
/*  857 */     j3dMessage.args[1] = new Integer(32);
/*      */     
/*  859 */     LightRetained[] arrayOfLightRetained = new LightRetained[this.newlyAddedMirrorLights.size()];
/*  860 */     for (byte b1 = 0; b1 < arrayOfLightRetained.length; b1++) {
/*  861 */       arrayOfLightRetained[b1] = (LightRetained)this.newlyAddedMirrorLights.get(b1);
/*      */     }
/*  863 */     j3dMessage.args[2] = new Integer(arrayOfLightRetained.length);
/*  864 */     j3dMessage.args[3] = arrayOfLightRetained;
/*      */     
/*  866 */     Object[] arrayOfObject1 = new Object[paramInt];
/*  867 */     arrayOfObject1[0] = this.boundingLeaf;
/*  868 */     arrayOfObject1[1] = (this.regionOfInfluence != null) ? this.regionOfInfluence.clone() : null;
/*  869 */     arrayOfObject1[2] = this.inBackgroundGroup ? Boolean.TRUE : Boolean.FALSE;
/*  870 */     arrayOfObject1[3] = this.geometryBackground;
/*  871 */     arrayOfObject1[4] = this.lightOn ? Boolean.TRUE : Boolean.FALSE;
/*      */     
/*  873 */     ArrayList arrayList = new ArrayList();
/*  874 */     for (byte b2 = 0; b2 < this.scopes.size(); b2++) {
/*  875 */       GroupRetained groupRetained = (GroupRetained)this.scopes.get(b2);
/*  876 */       this.tempKey.reset();
/*  877 */       groupRetained.addAllNodesForScopedLight(arrayOfLightRetained.length, arrayOfLightRetained, arrayList, this.tempKey);
/*      */     } 
/*  879 */     Object[] arrayOfObject2 = new Object[2];
/*  880 */     arrayOfObject2[0] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  881 */     arrayOfObject2[1] = arrayList;
/*  882 */     arrayOfObject1[5] = arrayOfObject2;
/*  883 */     Color3f color3f = new Color3f(this.color);
/*  884 */     arrayOfObject1[6] = color3f;
/*  885 */     j3dMessage.args[4] = arrayOfObject1;
/*  886 */     return j3dMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearLive(SetLiveState paramSetLiveState) {
/*  893 */     this.newlyAddedMirrorLights.clear();
/*  894 */     super.clearLive(paramSetLiveState);
/*      */     
/*  896 */     if (this.inSharedGroup) {
/*  897 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/*  898 */         LightRetained lightRetained = getMirrorLight(paramSetLiveState.keys[b]);
/*  899 */         if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null) {
/*      */           
/*  901 */           paramSetLiveState.transformTargets[b].addNode(lightRetained, 1);
/*  902 */           paramSetLiveState.notifyThreads |= 0x2000;
/*      */         } 
/*  904 */         this.newlyAddedMirrorLights.add(lightRetained);
/*      */         
/*  906 */         if (lightRetained.boundingLeaf != null) {
/*  907 */           lightRetained.boundingLeaf.removeUser(lightRetained);
/*  908 */           lightRetained.boundingLeaf = null;
/*      */         } 
/*  910 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b] != null)
/*      */         {
/*  912 */           paramSetLiveState.switchTargets[b].addNode(lightRetained, 1);
/*      */         }
/*  914 */         if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/*  915 */           paramSetLiveState.viewScopedNodeList.add(lightRetained);
/*  916 */           paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(b));
/*      */         } else {
/*  918 */           paramSetLiveState.nodeList.add(lightRetained);
/*      */         } 
/*      */       } 
/*      */     } else {
/*  922 */       LightRetained lightRetained = getMirrorLight(null);
/*      */ 
/*      */       
/*  925 */       if (lightRetained.boundingLeaf != null) {
/*  926 */         lightRetained.boundingLeaf.removeUser(lightRetained);
/*  927 */         lightRetained.boundingLeaf = null;
/*      */       } 
/*  929 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */       {
/*  931 */         paramSetLiveState.switchTargets[0].addNode(lightRetained, 1);
/*      */       }
/*  933 */       if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/*  934 */         paramSetLiveState.viewScopedNodeList.add(lightRetained);
/*      */         
/*  936 */         paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*      */       } else {
/*  938 */         paramSetLiveState.nodeList.add(lightRetained);
/*      */       } 
/*  940 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/*      */         
/*  942 */         paramSetLiveState.transformTargets[0].addNode(lightRetained, 1);
/*  943 */         paramSetLiveState.notifyThreads |= 0x2000;
/*      */       } 
/*      */ 
/*      */       
/*  947 */       this.newlyAddedMirrorLights.add(lightRetained);
/*      */     } 
/*  949 */     paramSetLiveState.notifyThreads |= 0x1080;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  954 */     if (this.scopes.size() > 0) {
/*  955 */       J3dMessage j3dMessage = new J3dMessage();
/*  956 */       LightRetained[] arrayOfLightRetained = new LightRetained[this.newlyAddedMirrorLights.size()];
/*  957 */       for (byte b1 = 0; b1 < arrayOfLightRetained.length; b1++) {
/*  958 */         arrayOfLightRetained[b1] = (LightRetained)this.newlyAddedMirrorLights.get(b1);
/*      */       }
/*  960 */       j3dMessage.threads = 4096;
/*  961 */       j3dMessage.universe = this.universe;
/*  962 */       j3dMessage.type = 19;
/*  963 */       j3dMessage.args[0] = this;
/*  964 */       j3dMessage.args[1] = new Integer(64);
/*  965 */       ArrayList arrayList = new ArrayList();
/*  966 */       for (byte b2 = 0; b2 < this.scopes.size(); b2++) {
/*  967 */         GroupRetained groupRetained = (GroupRetained)this.scopes.get(b2);
/*  968 */         this.tempKey.reset();
/*  969 */         groupRetained.removeAllNodesForScopedLight(arrayOfLightRetained.length, arrayOfLightRetained, arrayList, this.tempKey);
/*      */       } 
/*  971 */       j3dMessage.args[2] = arrayList;
/*  972 */       j3dMessage.args[3] = new Integer(arrayOfLightRetained.length);
/*  973 */       j3dMessage.args[4] = arrayOfLightRetained;
/*  974 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void clearMirrorObject(Object[] paramArrayOfObject) {
/*  980 */     ArrayList arrayList1 = (ArrayList)paramArrayOfObject[2];
/*  981 */     ArrayList arrayList2 = new ArrayList();
/*  982 */     LightRetained[] arrayOfLightRetained = (LightRetained[])paramArrayOfObject[4];
/*  983 */     int i = ((Integer)paramArrayOfObject[3]).intValue();
/*      */     
/*  985 */     for (byte b = 0; b < i; b++) {
/*  986 */       for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/*  987 */         Shape3DRetained shape3DRetained = ((GeometryAtom)arrayList1.get(b1)).source;
/*  988 */         shape3DRetained.removeLight(arrayOfLightRetained[b]);
/*      */       } 
/*  990 */       (arrayOfLightRetained[b]).isScoped = false;
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
/*      */   protected Object clone() {
/* 1002 */     LightRetained lightRetained = (LightRetained)super.clone();
/* 1003 */     lightRetained.color = new Color3f(this.color);
/* 1004 */     lightRetained.scopes = (Vector)this.scopes.clone();
/* 1005 */     lightRetained.initInfluencingBoundingLeaf(getInfluencingBoundingLeaf());
/* 1006 */     lightRetained.region = null;
/* 1007 */     lightRetained.lightDirty = 65535;
/* 1008 */     lightRetained.sgLightDirty = 65535;
/* 1009 */     lightRetained.universe = null;
/* 1010 */     lightRetained.isNeeded = false;
/* 1011 */     lightRetained.inImmCtx = false;
/* 1012 */     lightRetained.sgLight = null;
/* 1013 */     lightRetained.key = null;
/* 1014 */     lightRetained.mirrorLights = new LightRetained[1];
/* 1015 */     lightRetained.numMirrorLights = 0;
/* 1016 */     lightRetained.environmentSets = new UnorderList(1, EnvironmentSet.class);
/* 1017 */     return lightRetained;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTransformChange() {}
/*      */ 
/*      */ 
/*      */   
/*      */   void updateImmediateTransformChange() {
/* 1028 */     if (this.boundingLeaf == null && 
/* 1029 */       this.regionOfInfluence != null) {
/* 1030 */       this.region = this.regionOfInfluence.copy(this.region);
/* 1031 */       this.region.transform(this.regionOfInfluence, getCurrentLocalToVworld());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendMessage(int paramInt, Object paramObject) {
/* 1039 */     J3dMessage j3dMessage = new J3dMessage();
/* 1040 */     j3dMessage.threads = 4224;
/* 1041 */     j3dMessage.type = 19;
/* 1042 */     j3dMessage.universe = this.universe;
/* 1043 */     j3dMessage.args[0] = this;
/* 1044 */     j3dMessage.args[1] = new Integer(paramInt);
/* 1045 */     if (this.inSharedGroup) {
/* 1046 */       j3dMessage.args[2] = new Integer(this.numMirrorLights);
/*      */     } else {
/* 1048 */       j3dMessage.args[2] = new Integer(1);
/*      */     } 
/* 1050 */     j3dMessage.args[3] = this.mirrorLights.clone();
/* 1051 */     j3dMessage.args[4] = paramObject;
/* 1052 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */   
/*      */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 1056 */     super.mergeTransform(paramTransformGroupRetained);
/* 1057 */     if (this.regionOfInfluence != null)
/* 1058 */       this.regionOfInfluence.transform(paramTransformGroupRetained.transform); 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\LightRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */