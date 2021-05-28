/*      */ package javax.media.j3d;
/*      */ 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class RenderMolecule
/*      */   extends IndexedObject
/*      */   implements ObjectUpdate, NodeComponentUpdate
/*      */ {
/*      */   static final int REMOVE_RENDER_ATOM_IN_RM_LIST = 0;
/*      */   static final int RENDER_MOLECULE_LIST = 1;
/*      */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*      */   static final int POINT = 1;
/*      */   static final int LINE = 2;
/*      */   static final int SURFACE = 4;
/*      */   static final int RASTER = 8;
/*      */   static final int COMPRESSED = 16;
/*   41 */   static int RM_COMPONENTS = 945;
/*      */ 
/*      */ 
/*      */   
/*      */   PolygonAttributesRetained polygonAttributes;
/*      */ 
/*      */ 
/*      */   
/*      */   LineAttributesRetained lineAttributes;
/*      */ 
/*      */ 
/*      */   
/*      */   PointAttributesRetained pointAttributes;
/*      */ 
/*      */ 
/*      */   
/*      */   MaterialRetained material;
/*      */ 
/*      */ 
/*      */   
/*      */   ColoringAttributesRetained coloringAttributes;
/*      */ 
/*      */ 
/*      */   
/*      */   TransparencyAttributesRetained transparency;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean normalPresent;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int POINTATTRS_DIRTY = 512;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int LINEATTRS_DIRTY = 256;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int POLYGONATTRS_DIRTY = 128;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int MATERIAL_DIRTY = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int TRANSPARENCY_DIRTY = 32;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int COLORINGATTRS_DIRTY = 16;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int ALL_DIRTY_BITS = 945;
/*      */ 
/*      */ 
/*      */   
/*      */   int dirtyAttrsAcrossRms;
/*      */ 
/*      */ 
/*      */   
/*      */   int soleUserCompDirty;
/*      */ 
/*      */ 
/*      */   
/*      */   PolygonAttributesRetained definingPolygonAttributes;
/*      */ 
/*      */ 
/*      */   
/*      */   LineAttributesRetained definingLineAttributes;
/*      */ 
/*      */ 
/*      */   
/*      */   PointAttributesRetained definingPointAttributes;
/*      */ 
/*      */ 
/*      */   
/*      */   TextureBin textureBin;
/*      */ 
/*      */ 
/*      */   
/*      */   Transform3D[] localToVworld;
/*      */ 
/*      */ 
/*      */   
/*      */   int[] localToVworldIndex;
/*      */ 
/*      */   
/*      */   MaterialRetained definingMaterial;
/*      */ 
/*      */   
/*      */   ColoringAttributesRetained definingColoringAttributes;
/*      */ 
/*      */   
/*      */   TransparencyAttributesRetained definingTransparency;
/*      */ 
/*      */   
/*      */   Transform3D[] trans;
/*      */ 
/*      */   
/*      */   boolean isNonUniformScale;
/*      */ 
/*      */   
/*      */   int numRenderAtoms;
/*      */ 
/*      */   
/*      */   int numEditingRenderAtoms;
/*      */ 
/*      */   
/*      */   RenderAtom addRAs;
/*      */ 
/*      */   
/*      */   RenderAtom removeRAs;
/*      */ 
/*      */   
/*      */   float red;
/*      */ 
/*      */   
/*      */   float green;
/*      */ 
/*      */   
/*      */   float blue;
/*      */ 
/*      */   
/*      */   float dRed;
/*      */ 
/*      */   
/*      */   float dGreen;
/*      */ 
/*      */   
/*      */   float dBlue;
/*      */ 
/*      */   
/*      */   float alpha;
/*      */ 
/*      */   
/*      */   int geometryType;
/*      */ 
/*      */   
/*      */   boolean enableLighting;
/*      */ 
/*      */   
/*      */   int primaryMoleculeType;
/*      */ 
/*      */   
/*  189 */   static int COMPRESSED_MOLECULE = 1;
/*  190 */   static int TEXT3D_MOLECULE = 2;
/*  191 */   static int DLIST_MOLECULE = 4;
/*  192 */   static int RASTER_MOLECULE = 8;
/*  193 */   static int ORIENTEDSHAPE3D_MOLECULE = 16;
/*  194 */   static int SEPARATE_DLIST_PER_RINFO_MOLECULE = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int polygonMode;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean lineAA;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean pointAA;
/*      */ 
/*      */ 
/*      */   
/*      */   int vertexFormat;
/*      */ 
/*      */ 
/*      */   
/*      */   int texCoordSetMapLen;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod primaryRenderMethod;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod secondaryRenderMethod;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderBin renderBin;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMolecule next;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMolecule prev;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderAtomListInfo primaryRenderAtomList;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderAtomListInfo separateDlistRenderAtomList;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderAtomListInfo vertexArrayRenderAtomList;
/*      */ 
/*      */ 
/*      */   
/*      */   BoundingBox vwcBounds;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMolecule nextMap;
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMolecule prevMap;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean soleUser;
/*      */ 
/*      */ 
/*      */   
/*      */   Object appHandle;
/*      */ 
/*      */ 
/*      */   
/*      */   VertexArrayRenderMethod cachedVertexArrayRenderMethod;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isQuadGeometryArray;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isTriGeometryArray;
/*      */ 
/*      */ 
/*      */   
/*      */   int displayListId;
/*      */ 
/*      */ 
/*      */   
/*      */   Integer displayListIdObj;
/*      */ 
/*      */ 
/*      */   
/*      */   int onUpdateList;
/*      */ 
/*      */ 
/*      */   
/*  297 */   static int NEW_RENDERATOMS_UPDATE = 1;
/*  298 */   static int BOUNDS_RECOMPUTE_UPDATE = 2;
/*  299 */   static int LOCALE_TRANSLATION = 4;
/*  300 */   static int UPDATE_BACKGROUND_TRANSFORM = 8;
/*  301 */   static int IN_DIRTY_RENDERMOLECULE_LIST = 16;
/*  302 */   static int LOCALE_CHANGED = 32;
/*  303 */   static int ON_UPDATE_CHECK_LIST = 64; RenderMolecule(GeometryAtom paramGeometryAtom, PolygonAttributesRetained paramPolygonAttributesRetained, LineAttributesRetained paramLineAttributesRetained, PointAttributesRetained paramPointAttributesRetained, MaterialRetained paramMaterialRetained, ColoringAttributesRetained paramColoringAttributesRetained, TransparencyAttributesRetained paramTransparencyAttributesRetained, RenderingAttributesRetained paramRenderingAttributesRetained, TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained, Transform3D[] paramArrayOfTransform3D, int[] paramArrayOfInt, RenderBin paramRenderBin) { this.polygonAttributes = null; this.lineAttributes = null; this.pointAttributes = null; this.material = null; this.coloringAttributes = null; this.transparency = null; this.normalPresent = true; this.dirtyAttrsAcrossRms = 945; this.soleUserCompDirty = 0; this.definingPolygonAttributes = null; this.definingLineAttributes = null; this.definingPointAttributes = null; this.textureBin = null; this.localToVworld = null; this.localToVworldIndex = null; this.definingMaterial = null; this.definingColoringAttributes = null; this.definingTransparency = null; this.trans = null; this.isNonUniformScale = false; this.numRenderAtoms = 0; this.numEditingRenderAtoms = 0; this.addRAs = null; this.removeRAs = null; this.red = 1.0F; this.green = 1.0F; this.blue = 1.0F; this.dRed = 1.0F; this.dGreen = 1.0F; this.dBlue = 1.0F; this.alpha = 0.0F; this.geometryType = -1; this.enableLighting = false; this.primaryMoleculeType = 0; this.polygonMode = 2; this.lineAA = false; this.pointAA = false; this.vertexFormat = -1; this.texCoordSetMapLen = 0; this.primaryRenderMethod = null; this.secondaryRenderMethod = null; this.renderBin = null; this.next = null; this.prev = null; this.primaryRenderAtomList = null; this.separateDlistRenderAtomList = null; this.vertexArrayRenderAtomList = null; this.vwcBounds = null; this.nextMap = null; this.prevMap = null; this.soleUser = false;
/*      */     this.appHandle = null;
/*      */     this.cachedVertexArrayRenderMethod = (VertexArrayRenderMethod)VirtualUniverse.mc.getVertexArrayRenderMethod();
/*      */     this.isQuadGeometryArray = false;
/*      */     this.isTriGeometryArray = false;
/*      */     this.displayListId = 0;
/*      */     this.displayListIdObj = null;
/*      */     this.onUpdateList = 0;
/*  311 */     this.useAlpha = false;
/*      */ 
/*      */     
/*  314 */     this.locale = null;
/*      */ 
/*      */     
/*  317 */     this.localeLocalToVworld = null;
/*      */ 
/*      */     
/*  320 */     this.localeTranslation = null;
/*      */     
/*  322 */     this.primaryChanged = false;
/*      */     
/*  324 */     this.isOpaqueOrInOG = true;
/*  325 */     this.inOrderedGroup = false;
/*      */ 
/*      */ 
/*      */     
/*  329 */     this.closestSwitchParent = null;
/*      */ 
/*      */     
/*  332 */     this.closestSwitchIndex = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  346 */     this.renderBin = paramRenderBin;
/*  347 */     IndexedUnorderSet.init(this, 2);
/*      */     
/*  349 */     reset(paramGeometryAtom, paramPolygonAttributesRetained, paramLineAttributesRetained, paramPointAttributesRetained, paramMaterialRetained, paramColoringAttributesRetained, paramTransparencyAttributesRetained, paramRenderingAttributesRetained, paramArrayOfTextureUnitStateRetained, paramArrayOfTransform3D, paramArrayOfInt); }
/*      */ 
/*      */   
/*      */   boolean doInfinite;
/*      */   Transform3D[] infLocalToVworld;
/*      */   boolean useAlpha;
/*      */   Locale locale;
/*      */   Transform3D[] localeLocalToVworld;
/*      */   Vector3d localeTranslation;
/*      */   boolean primaryChanged;
/*      */   boolean isOpaqueOrInOG;
/*      */   boolean inOrderedGroup;
/*      */   SwitchRetained closestSwitchParent;
/*      */   int closestSwitchIndex;
/*      */   
/*      */   void reset(GeometryAtom paramGeometryAtom, PolygonAttributesRetained paramPolygonAttributesRetained, LineAttributesRetained paramLineAttributesRetained, PointAttributesRetained paramPointAttributesRetained, MaterialRetained paramMaterialRetained, ColoringAttributesRetained paramColoringAttributesRetained, TransparencyAttributesRetained paramTransparencyAttributesRetained, RenderingAttributesRetained paramRenderingAttributesRetained, TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained, Transform3D[] paramArrayOfTransform3D, int[] paramArrayOfInt) {
/*  365 */     this.primaryMoleculeType = 0;
/*  366 */     this.numRenderAtoms = 0;
/*  367 */     this.numEditingRenderAtoms = 0;
/*  368 */     this.onUpdateList = 0;
/*  369 */     this.dirtyAttrsAcrossRms = 945;
/*  370 */     this.primaryRenderMethod = null;
/*  371 */     this.isNonUniformScale = false;
/*  372 */     this.primaryChanged = false;
/*  373 */     this.material = paramMaterialRetained;
/*  374 */     this.polygonAttributes = paramPolygonAttributesRetained;
/*  375 */     this.lineAttributes = paramLineAttributesRetained;
/*  376 */     this.pointAttributes = paramPointAttributesRetained;
/*  377 */     this.coloringAttributes = paramColoringAttributesRetained;
/*  378 */     this.transparency = paramTransparencyAttributesRetained;
/*      */     
/*  380 */     this.closestSwitchParent = paramGeometryAtom.source.closestSwitchParent;
/*  381 */     this.closestSwitchIndex = paramGeometryAtom.source.closestSwitchIndex;
/*      */ 
/*      */ 
/*      */     
/*  385 */     GeometryRetained geometryRetained = null;
/*  386 */     byte b = 0;
/*  387 */     this.isOpaqueOrInOG = true;
/*  388 */     this.inOrderedGroup = false;
/*  389 */     while (geometryRetained == null && b < paramGeometryAtom.geometryArray.length) {
/*  390 */       geometryRetained = paramGeometryAtom.geometryArray[b];
/*  391 */       b++;
/*      */     } 
/*      */ 
/*      */     
/*  395 */     this.soleUser = false;
/*  396 */     if (VirtualUniverse.mc.allowSoleUser && 
/*  397 */       paramGeometryAtom.source.appearance != null) {
/*  398 */       this.soleUser = ((paramGeometryAtom.source.appearance.changedFrequent & RM_COMPONENTS) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  403 */     if (this.soleUser) {
/*  404 */       this.appHandle = paramGeometryAtom.source.appearance;
/*      */     } else {
/*  406 */       this.appHandle = this;
/*      */     } 
/*      */     
/*  409 */     if (paramGeometryAtom.geoType <= 14 || paramGeometryAtom.geoType == 16) {
/*      */ 
/*      */       
/*  412 */       if (paramGeometryAtom.source instanceof OrientedShape3DRetained) {
/*  413 */         this.primaryRenderMethod = VirtualUniverse.mc.getOrientedShape3DRenderMethod();
/*      */         
/*  415 */         this.primaryMoleculeType = ORIENTEDSHAPE3D_MOLECULE;
/*  416 */       } else if (paramGeometryAtom.geoType == 16) {
/*  417 */         this.primaryRenderMethod = VirtualUniverse.mc.getText3DRenderMethod();
/*      */         
/*  419 */         this.primaryMoleculeType = TEXT3D_MOLECULE;
/*      */       } else {
/*      */         
/*  422 */         this.secondaryRenderMethod = this.cachedVertexArrayRenderMethod;
/*      */       }
/*      */     
/*      */     }
/*  426 */     else if (paramGeometryAtom.geoType == 17) {
/*  427 */       this.primaryRenderMethod = VirtualUniverse.mc.getCompressedGeometryRenderMethod();
/*      */       
/*  429 */       this.primaryMoleculeType = COMPRESSED_MOLECULE;
/*      */     }
/*  431 */     else if (geometryRetained instanceof RasterRetained) {
/*  432 */       this.primaryRenderMethod = VirtualUniverse.mc.getDefaultRenderMethod();
/*      */       
/*  434 */       this.primaryMoleculeType = RASTER_MOLECULE;
/*      */     } 
/*      */ 
/*      */     
/*  438 */     this.prev = null;
/*  439 */     this.next = null;
/*  440 */     this.prevMap = null;
/*  441 */     this.nextMap = null;
/*      */     
/*  443 */     this.primaryRenderAtomList = null;
/*  444 */     this.vertexArrayRenderAtomList = null;
/*      */ 
/*      */ 
/*      */     
/*  448 */     switch (paramGeometryAtom.geoType) {
/*      */       case 3:
/*      */       case 10:
/*  451 */         this.geometryType = 1;
/*      */         break;
/*      */       case 4:
/*      */       case 7:
/*      */       case 11:
/*      */       case 14:
/*  457 */         this.geometryType = 2;
/*      */         break;
/*      */       case 15:
/*  460 */         this.geometryType = 8;
/*      */         break;
/*      */       case 17:
/*  463 */         this.geometryType = 16;
/*      */         
/*  465 */         switch (((CompressedGeometryRetained)geometryRetained).getBufferType()) {
/*      */           case 0:
/*  467 */             this.geometryType |= 0x1;
/*      */             break;
/*      */           case 1:
/*  470 */             this.geometryType |= 0x2;
/*      */             break;
/*      */         } 
/*      */         
/*  474 */         this.geometryType |= 0x4;
/*  475 */         if (paramPolygonAttributesRetained != null) {
/*  476 */           if (paramPolygonAttributesRetained.polygonMode == 0) {
/*      */             
/*  478 */             this.geometryType |= 0x1; break;
/*  479 */           }  if (paramPolygonAttributesRetained.polygonMode == 1)
/*      */           {
/*  481 */             this.geometryType |= 0x2;
/*      */           }
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/*  488 */         this.geometryType = 4;
/*  489 */         if (paramPolygonAttributesRetained != null) {
/*  490 */           if (paramPolygonAttributesRetained.polygonMode == 0) {
/*      */             
/*  492 */             this.geometryType |= 0x1; break;
/*  493 */           }  if (paramPolygonAttributesRetained.polygonMode == 1)
/*      */           {
/*  495 */             this.geometryType |= 0x2;
/*      */           }
/*      */         } 
/*      */         break;
/*      */     } 
/*      */     
/*  501 */     this.isQuadGeometryArray = (geometryRetained.getClassType() == 4);
/*      */     
/*  503 */     this.isTriGeometryArray = (geometryRetained.getClassType() == 3);
/*      */ 
/*      */     
/*  506 */     this.localToVworld = paramArrayOfTransform3D;
/*  507 */     this.localToVworldIndex = paramArrayOfInt;
/*  508 */     this.doInfinite = paramGeometryAtom.source.inBackgroundGroup;
/*  509 */     if (this.doInfinite) {
/*  510 */       if (this.infLocalToVworld == null) {
/*  511 */         this.infLocalToVworld = new Transform3D[2];
/*  512 */         this.infLocalToVworld[1] = new Transform3D(); this.infLocalToVworld[0] = new Transform3D();
/*      */       } 
/*  514 */       this.localToVworld[0].getRotation(this.infLocalToVworld[0]);
/*      */     } 
/*  516 */     int i = 0;
/*  517 */     if (paramPolygonAttributesRetained != null) {
/*  518 */       if (paramPolygonAttributesRetained.changedFrequent != 0) {
/*  519 */         this.definingPolygonAttributes = paramPolygonAttributesRetained;
/*      */         
/*  521 */         i |= 0x80;
/*      */       
/*      */       }
/*  524 */       else if (this.definingPolygonAttributes != null) {
/*  525 */         this.definingPolygonAttributes.set(paramPolygonAttributesRetained);
/*      */       } else {
/*      */         
/*  528 */         this.definingPolygonAttributes = (PolygonAttributesRetained)paramPolygonAttributesRetained.clone();
/*      */       } 
/*      */       
/*  531 */       this.polygonMode = this.definingPolygonAttributes.polygonMode;
/*      */     } else {
/*  533 */       this.polygonMode = 2;
/*  534 */       this.definingPolygonAttributes = null;
/*      */     } 
/*      */     
/*  537 */     if (paramLineAttributesRetained != null) {
/*  538 */       if (paramLineAttributesRetained.changedFrequent != 0) {
/*  539 */         this.definingLineAttributes = paramLineAttributesRetained;
/*  540 */         i |= 0x100;
/*      */       
/*      */       }
/*  543 */       else if (this.definingLineAttributes != null) {
/*  544 */         this.definingLineAttributes.set(paramLineAttributesRetained);
/*      */       } else {
/*      */         
/*  547 */         this.definingLineAttributes = (LineAttributesRetained)paramLineAttributesRetained.clone();
/*      */       } 
/*      */       
/*  550 */       this.lineAA = this.definingLineAttributes.lineAntialiasing;
/*      */     } else {
/*  552 */       this.lineAA = false;
/*  553 */       this.definingLineAttributes = null;
/*      */     } 
/*      */     
/*  556 */     if (paramPointAttributesRetained != null) {
/*  557 */       if (paramPointAttributesRetained.changedFrequent != 0) {
/*  558 */         this.definingPointAttributes = paramPointAttributesRetained;
/*  559 */         i |= 0x200;
/*      */ 
/*      */       
/*      */       }
/*  563 */       else if (this.definingPointAttributes != null) {
/*  564 */         this.definingPointAttributes.set(paramPointAttributesRetained);
/*      */       } else {
/*      */         
/*  567 */         this.definingPointAttributes = (PointAttributesRetained)paramPointAttributesRetained.clone();
/*      */       } 
/*      */       
/*  570 */       this.pointAA = this.definingPointAttributes.pointAntialiasing;
/*      */     } else {
/*  572 */       this.pointAA = false;
/*  573 */       this.definingPointAttributes = null;
/*      */     } 
/*      */     
/*  576 */     this.normalPresent = true;
/*  577 */     if (geometryRetained instanceof GeometryArrayRetained) {
/*  578 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)geometryRetained;
/*  579 */       this.vertexFormat = geometryArrayRetained.vertexFormat;
/*      */       
/*  581 */       if (geometryArrayRetained.texCoordSetMap != null) {
/*  582 */         this.texCoordSetMapLen = geometryArrayRetained.texCoordSetMap.length;
/*      */       } else {
/*  584 */         this.texCoordSetMapLen = 0;
/*      */       } 
/*      */ 
/*      */       
/*  588 */       if ((this.vertexFormat & 0x2) == 0)
/*      */       {
/*  590 */         this.normalPresent = false;
/*      */       
/*      */       }
/*      */     }
/*  594 */     else if (geometryRetained instanceof CompressedGeometryRetained) {
/*  595 */       this.vertexFormat = ((CompressedGeometryRetained)geometryRetained).getVertexFormat();
/*      */ 
/*      */       
/*  598 */       if ((this.vertexFormat & 0x2) == 0)
/*      */       {
/*  600 */         this.normalPresent = false;
/*      */       }
/*      */       
/*  603 */       this.texCoordSetMapLen = 0;
/*      */     } else {
/*      */       
/*  606 */       this.vertexFormat = -1;
/*  607 */       this.texCoordSetMapLen = 0;
/*      */     } 
/*      */     
/*  610 */     if (paramMaterialRetained != null) {
/*  611 */       if (paramMaterialRetained.changedFrequent != 0) {
/*  612 */         this.definingMaterial = paramMaterialRetained;
/*  613 */         i |= 0x1;
/*      */       
/*      */       }
/*  616 */       else if (this.definingMaterial != null) {
/*  617 */         this.definingMaterial.set(paramMaterialRetained);
/*      */       } else {
/*  619 */         this.definingMaterial = (MaterialRetained)paramMaterialRetained.clone();
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  625 */       this.definingMaterial = null;
/*      */     } 
/*  627 */     evalMaterialCachedState();
/*  628 */     if (paramColoringAttributesRetained != null) {
/*  629 */       if (paramColoringAttributesRetained.changedFrequent != 0) {
/*  630 */         this.definingColoringAttributes = paramColoringAttributesRetained;
/*  631 */         i |= 0x10;
/*      */       
/*      */       }
/*  634 */       else if (this.definingColoringAttributes != null) {
/*  635 */         this.definingColoringAttributes.set(paramColoringAttributesRetained);
/*      */       } else {
/*      */         
/*  638 */         this.definingColoringAttributes = (ColoringAttributesRetained)paramColoringAttributesRetained.clone();
/*      */       } 
/*      */       
/*  641 */       this.red = paramColoringAttributesRetained.color.x;
/*  642 */       this.green = paramColoringAttributesRetained.color.y;
/*  643 */       this.blue = paramColoringAttributesRetained.color.z;
/*      */     } else {
/*  645 */       this.red = 1.0F;
/*  646 */       this.green = 1.0F;
/*  647 */       this.blue = 1.0F;
/*  648 */       this.definingColoringAttributes = null;
/*      */     } 
/*      */     
/*  651 */     if (paramTransparencyAttributesRetained != null) {
/*      */       
/*  653 */       if (paramTransparencyAttributesRetained.changedFrequent != 0) {
/*  654 */         this.definingTransparency = paramTransparencyAttributesRetained;
/*  655 */         i |= 0x20;
/*      */       
/*      */       }
/*  658 */       else if (this.definingTransparency != null) {
/*  659 */         this.definingTransparency.set(paramTransparencyAttributesRetained);
/*      */       } else {
/*      */         
/*  662 */         this.definingTransparency = (TransparencyAttributesRetained)paramTransparencyAttributesRetained.clone();
/*      */       } 
/*      */ 
/*      */       
/*  666 */       this.alpha = 1.0F - paramTransparencyAttributesRetained.transparency;
/*      */     } else {
/*      */       
/*  669 */       this.alpha = 1.0F;
/*  670 */       this.definingTransparency = null;
/*      */     } 
/*      */ 
/*      */     
/*  674 */     this.locale = paramGeometryAtom.source.locale;
/*  675 */     if (this.locale != this.renderBin.locale) {
/*  676 */       if (this.localeLocalToVworld == null) {
/*  677 */         this.localeLocalToVworld = new Transform3D[2];
/*      */       }
/*  679 */       this.localeLocalToVworld[0] = new Transform3D();
/*  680 */       this.localeLocalToVworld[1] = new Transform3D();
/*  681 */       this.localeTranslation = new Vector3d();
/*  682 */       paramGeometryAtom.locale.hiRes.difference(this.renderBin.locale.hiRes, this.localeTranslation);
/*  683 */       translate();
/*      */     } else {
/*      */       
/*  686 */       this.localeLocalToVworld = this.localToVworld;
/*      */     } 
/*      */     
/*  689 */     if (this.doInfinite) {
/*  690 */       this.trans = this.infLocalToVworld;
/*      */     } else {
/*      */       
/*  693 */       this.trans = this.localeLocalToVworld;
/*      */     } 
/*      */     
/*  696 */     evalAlphaUsage(paramRenderingAttributesRetained, paramArrayOfTextureUnitStateRetained);
/*  697 */     this.isOpaqueOrInOG = (isOpaque() || paramGeometryAtom.source.orderedPath != null);
/*  698 */     this.inOrderedGroup = (paramGeometryAtom.source.orderedPath != null);
/*      */     
/*  700 */     if (i != 0) {
/*  701 */       if ((this.soleUserCompDirty & 0x3B1) == 0) {
/*  702 */         this.renderBin.rmUpdateList.add(this);
/*      */       }
/*  704 */       this.soleUserCompDirty |= i;
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
/*      */   boolean equals(RenderAtom paramRenderAtom, PolygonAttributesRetained paramPolygonAttributesRetained, LineAttributesRetained paramLineAttributesRetained, PointAttributesRetained paramPointAttributesRetained, MaterialRetained paramMaterialRetained, ColoringAttributesRetained paramColoringAttributesRetained, TransparencyAttributesRetained paramTransparencyAttributesRetained, Transform3D[] paramArrayOfTransform3D) {
/*  720 */     byte b1 = 0;
/*  721 */     GeometryAtom geometryAtom = paramRenderAtom.geometryAtom;
/*  722 */     boolean bool = false;
/*      */     
/*  724 */     if (this.localToVworld != paramArrayOfTransform3D) {
/*  725 */       return false;
/*      */     }
/*      */     
/*  728 */     if (this.locale != paramRenderAtom.geometryAtom.source.locale) {
/*  729 */       return false;
/*      */     }
/*      */     
/*  732 */     if (paramRenderAtom.geometryAtom.source.closestSwitchParent != this.closestSwitchParent || paramRenderAtom.geometryAtom.source.closestSwitchIndex != this.closestSwitchIndex)
/*      */     {
/*  734 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  738 */     GeometryRetained geometryRetained = null;
/*  739 */     byte b2 = 0;
/*  740 */     while (geometryRetained == null && b2 < geometryAtom.geometryArray.length) {
/*  741 */       geometryRetained = geometryAtom.geometryArray[b2];
/*  742 */       b2++;
/*      */     } 
/*      */ 
/*      */     
/*  746 */     switch (geometryAtom.geoType) {
/*      */       case 3:
/*      */       case 10:
/*  749 */         b1 = 1;
/*      */         break;
/*      */       case 4:
/*      */       case 7:
/*      */       case 11:
/*      */       case 14:
/*  755 */         b1 = 2;
/*      */         break;
/*      */       case 15:
/*  758 */         b1 = 8;
/*      */         break;
/*      */       case 17:
/*  761 */         b1 = 16;
/*  762 */         switch (((CompressedGeometryRetained)geometryRetained).getBufferType()) {
/*      */           case 0:
/*  764 */             b1 |= 0x1;
/*      */             break;
/*      */           case 1:
/*  767 */             b1 |= 0x2;
/*      */             break;
/*      */         } 
/*      */         
/*  771 */         b1 |= 0x4;
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/*  776 */         b1 = 4;
/*  777 */         if (paramPolygonAttributesRetained != null) {
/*  778 */           if (paramPolygonAttributesRetained.polygonMode == 0) {
/*      */             
/*  780 */             b1 |= 0x1; break;
/*  781 */           }  if (paramPolygonAttributesRetained.polygonMode == 1)
/*      */           {
/*  783 */             b1 |= 0x2;
/*      */           }
/*      */         } 
/*      */         break;
/*      */     } 
/*      */     
/*  789 */     if (this.geometryType != b1) {
/*  790 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  802 */     if (geometryAtom.geoType == 16 && this.primaryMoleculeType != 0 && (this.primaryMoleculeType & TEXT3D_MOLECULE) == 0)
/*      */     {
/*      */       
/*  805 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  809 */     if (!(paramRenderAtom.geometryAtom.source instanceof OrientedShape3DRetained) && (this.primaryMoleculeType & ORIENTEDSHAPE3D_MOLECULE) != 0)
/*      */     {
/*      */ 
/*      */       
/*  813 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  818 */     if (geometryRetained instanceof GeometryArrayRetained) {
/*  819 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)geometryRetained;
/*  820 */       if (this.vertexFormat != geometryArrayRetained.vertexFormat) {
/*  821 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  830 */       if ((geometryArrayRetained.texCoordSetMap != null && this.texCoordSetMapLen != geometryArrayRetained.texCoordSetMap.length) || (geometryArrayRetained.texCoordSetMap == null && this.texCoordSetMapLen != 0))
/*      */       {
/*      */         
/*  833 */         return false;
/*      */       }
/*      */       
/*  836 */       if (VirtualUniverse.mc.isD3D() && ((geometryRetained.getClassType() == 4 && !this.isQuadGeometryArray) || (geometryRetained.getClassType() == 3 && !this.isTriGeometryArray)))
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  841 */         return false;
/*      */       }
/*      */     }
/*  844 */     else if (geometryRetained instanceof CompressedGeometryRetained) {
/*  845 */       if (this.vertexFormat != ((CompressedGeometryRetained)geometryRetained).getVertexFormat())
/*      */       {
/*  847 */         return false;
/*      */       
/*      */       }
/*      */     }
/*  851 */     else if (this.vertexFormat != -1) {
/*  852 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  858 */     if (this.soleUser || (paramRenderAtom.geometryAtom.source.appearance != null && (paramRenderAtom.geometryAtom.source.appearance.changedFrequent & RM_COMPONENTS) != 0)) {
/*      */       
/*  860 */       if (this.appHandle == paramRenderAtom.geometryAtom.source.appearance) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  871 */         if (this.numEditingRenderAtoms == 0) {
/*      */           
/*  873 */           if ((this.soleUserCompDirty & 0x3B1) == 0) {
/*  874 */             this.renderBin.rmUpdateList.add(this);
/*      */           }
/*  876 */           this.soleUserCompDirty |= 0x3B1;
/*      */         } 
/*  878 */         return true;
/*      */       } 
/*      */       
/*  881 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  891 */     if (this.definingPolygonAttributes != null) {
/*  892 */       if (this.definingPolygonAttributes.changedFrequent != 0 || (paramPolygonAttributesRetained != null && paramPolygonAttributesRetained.changedFrequent != 0)) {
/*      */         
/*  894 */         if (this.definingPolygonAttributes == paramPolygonAttributesRetained) {
/*  895 */           if (this.definingPolygonAttributes.compChanged != 0) {
/*  896 */             if ((this.soleUserCompDirty & 0x3B1) == 0) {
/*  897 */               this.renderBin.rmUpdateList.add(this);
/*      */             }
/*  899 */             this.soleUserCompDirty |= 0x80;
/*      */           } 
/*      */         } else {
/*      */           
/*  903 */           return false;
/*      */         } 
/*  905 */       } else if (!this.definingPolygonAttributes.equivalent(paramPolygonAttributesRetained)) {
/*  906 */         return false;
/*      */       }
/*      */     
/*  909 */     } else if (paramPolygonAttributesRetained != null) {
/*  910 */       return false;
/*      */     } 
/*      */     
/*  913 */     if (this.definingLineAttributes != null) {
/*  914 */       if (this.definingLineAttributes.changedFrequent != 0 || (paramLineAttributesRetained != null && paramLineAttributesRetained.changedFrequent != 0)) {
/*      */         
/*  916 */         if (this.definingLineAttributes == paramLineAttributesRetained) {
/*  917 */           if (this.definingLineAttributes.compChanged != 0) {
/*  918 */             if ((this.soleUserCompDirty & 0x3B1) == 0) {
/*  919 */               this.renderBin.rmUpdateList.add(this);
/*      */             }
/*  921 */             this.soleUserCompDirty |= 0x100;
/*      */           } 
/*      */         } else {
/*      */           
/*  925 */           return false;
/*      */         } 
/*  927 */       } else if (!this.definingLineAttributes.equivalent(paramLineAttributesRetained)) {
/*  928 */         return false;
/*      */       }
/*      */     
/*  931 */     } else if (paramLineAttributesRetained != null) {
/*  932 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  936 */     if (this.definingPointAttributes != null) {
/*  937 */       if (this.definingPointAttributes.changedFrequent != 0 || (paramPointAttributesRetained != null && paramPointAttributesRetained.changedFrequent != 0)) {
/*      */         
/*  939 */         if (this.definingPointAttributes == paramPointAttributesRetained) {
/*  940 */           if (this.definingPointAttributes.compChanged != 0) {
/*  941 */             if ((this.soleUserCompDirty & 0x3B1) == 0) {
/*  942 */               this.renderBin.rmUpdateList.add(this);
/*      */             }
/*  944 */             this.soleUserCompDirty |= 0x200;
/*      */           } 
/*      */         } else {
/*      */           
/*  948 */           return false;
/*      */         } 
/*  950 */       } else if (!this.definingPointAttributes.equivalent(paramPointAttributesRetained)) {
/*  951 */         return false;
/*      */       }
/*      */     
/*  954 */     } else if (paramPointAttributesRetained != null) {
/*  955 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  961 */     if (this.definingMaterial != null) {
/*  962 */       if (this.definingMaterial.changedFrequent != 0 || (paramMaterialRetained != null && paramMaterialRetained.changedFrequent != 0)) {
/*      */         
/*  964 */         if (this.definingMaterial == paramMaterialRetained) {
/*  965 */           if (this.definingMaterial.compChanged != 0) {
/*  966 */             if ((this.soleUserCompDirty & 0x3B1) == 0) {
/*  967 */               this.renderBin.rmUpdateList.add(this);
/*      */             }
/*  969 */             this.soleUserCompDirty |= 0x1;
/*      */           } 
/*      */         } else {
/*      */           
/*  973 */           return false;
/*      */         } 
/*  975 */       } else if (!this.definingMaterial.equivalent(paramMaterialRetained)) {
/*  976 */         return false;
/*      */       }
/*      */     
/*  979 */     } else if (paramMaterialRetained != null) {
/*  980 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  985 */     if (this.definingColoringAttributes != null) {
/*  986 */       if (this.definingColoringAttributes.changedFrequent != 0 || (paramColoringAttributesRetained != null && paramColoringAttributesRetained.changedFrequent != 0)) {
/*      */         
/*  988 */         if (this.definingColoringAttributes == paramColoringAttributesRetained) {
/*  989 */           if (this.definingColoringAttributes.compChanged != 0) {
/*  990 */             if ((this.soleUserCompDirty & 0x3B1) == 0) {
/*  991 */               this.renderBin.rmUpdateList.add(this);
/*      */             }
/*  993 */             this.soleUserCompDirty |= 0x10;
/*      */           } 
/*      */         } else {
/*      */           
/*  997 */           return false;
/*      */         } 
/*  999 */       } else if (!this.definingColoringAttributes.equivalent(paramColoringAttributesRetained)) {
/* 1000 */         return false;
/*      */       }
/*      */     
/* 1003 */     } else if (paramColoringAttributesRetained != null) {
/* 1004 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1014 */     if (this.definingTransparency != null) {
/* 1015 */       if (this.definingTransparency.changedFrequent != 0 || (paramTransparencyAttributesRetained != null && paramTransparencyAttributesRetained.changedFrequent != 0)) {
/*      */         
/* 1017 */         if (this.definingTransparency == paramTransparencyAttributesRetained) {
/* 1018 */           if (this.definingTransparency.compChanged != 0) {
/* 1019 */             if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 1020 */               this.renderBin.rmUpdateList.add(this);
/*      */             }
/* 1022 */             this.soleUserCompDirty |= 0x20;
/*      */           } 
/*      */         } else {
/*      */           
/* 1026 */           return false;
/*      */         } 
/* 1028 */       } else if (!this.definingTransparency.equivalent(paramTransparencyAttributesRetained)) {
/* 1029 */         return false;
/*      */       }
/*      */     
/* 1032 */     } else if (paramTransparencyAttributesRetained != null) {
/* 1033 */       return false;
/*      */     } 
/*      */     
/* 1036 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRemoveRenderAtoms() {
/* 1046 */     if (this.numRenderAtoms == 0 && this.removeRAs == null && this.addRAs == null) {
/* 1047 */       this.textureBin.removeRenderMolecule(this);
/*      */       
/*      */       return;
/*      */     } 
/* 1051 */     while (this.removeRAs != null) {
/* 1052 */       RenderAtom renderAtom = this.removeRAs;
/* 1053 */       renderAtom.removed = null;
/* 1054 */       this.numRenderAtoms--;
/*      */ 
/*      */ 
/*      */       
/* 1058 */       for (byte b = 0; b < renderAtom.rListInfo.length; b++) {
/* 1059 */         RenderAtomListInfo renderAtomListInfo = renderAtom.rListInfo[b];
/*      */         
/* 1061 */         if (renderAtomListInfo.geometry() != null) {
/*      */ 
/*      */           
/* 1064 */           if ((renderAtomListInfo.groupType & RenderAtom.PRIMARY) != 0) {
/* 1065 */             this.primaryChanged = true;
/* 1066 */             if (renderAtomListInfo.prev == null) {
/* 1067 */               this.primaryRenderAtomList = renderAtomListInfo.next;
/* 1068 */               if (renderAtomListInfo.next != null) {
/* 1069 */                 renderAtomListInfo.next.prev = null;
/*      */               }
/*      */             } else {
/* 1072 */               renderAtomListInfo.prev.next = renderAtomListInfo.next;
/* 1073 */               if (renderAtomListInfo.next != null) {
/* 1074 */                 renderAtomListInfo.next.prev = renderAtomListInfo.prev;
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/* 1079 */             if (this.primaryMoleculeType == 8) {
/* 1080 */               RasterRetained rasterRetained = (RasterRetained)renderAtomListInfo.geometry();
/* 1081 */               this.renderBin.removeGeometryFromLockList(rasterRetained);
/* 1082 */               if (rasterRetained.image != null) {
/* 1083 */                 this.renderBin.removeNodeComponent(rasterRetained.image);
/*      */               }
/*      */             }
/* 1086 */             else if ((renderAtomListInfo.groupType & RenderAtom.SEPARATE_DLIST_PER_RINFO) != 0 && 
/* 1087 */               !renderAtomListInfo.renderAtom.inRenderBin()) {
/* 1088 */               this.renderBin.removeDlistPerRinfo.add(renderAtomListInfo);
/*      */             }
/*      */           
/*      */           }
/* 1092 */           else if ((renderAtomListInfo.groupType & RenderAtom.SEPARATE_DLIST_PER_GEO) != 0) {
/* 1093 */             if (renderAtomListInfo.prev == null) {
/* 1094 */               this.separateDlistRenderAtomList = renderAtomListInfo.next;
/* 1095 */               if (renderAtomListInfo.next != null) {
/* 1096 */                 renderAtomListInfo.next.prev = null;
/*      */               }
/*      */             } else {
/* 1099 */               renderAtomListInfo.prev.next = renderAtomListInfo.next;
/* 1100 */               if (renderAtomListInfo.next != null) {
/* 1101 */                 renderAtomListInfo.next.prev = renderAtomListInfo.prev;
/*      */               }
/*      */             } 
/* 1104 */             this.renderBin.removeGeometryDlist(renderAtomListInfo);
/*      */           }
/*      */           else {
/*      */             
/* 1108 */             if (renderAtomListInfo.prev == null) {
/* 1109 */               this.vertexArrayRenderAtomList = renderAtomListInfo.next;
/* 1110 */               if (renderAtomListInfo.next != null) {
/* 1111 */                 renderAtomListInfo.next.prev = null;
/*      */               }
/*      */             } else {
/* 1114 */               renderAtomListInfo.prev.next = renderAtomListInfo.next;
/* 1115 */               if (renderAtomListInfo.next != null) {
/* 1116 */                 renderAtomListInfo.next.prev = renderAtomListInfo.prev;
/*      */               }
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1125 */             GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 1126 */             if (!(geometryArrayRetained instanceof IndexedGeometryArrayRetained) || (geometryArrayRetained.vertexFormat & 0x200) != 0)
/*      */             {
/* 1128 */               this.renderBin.removeGeometryFromLockList(geometryArrayRetained);
/*      */             }
/*      */           } 
/* 1131 */           renderAtomListInfo.prev = null;
/* 1132 */           renderAtomListInfo.next = null;
/*      */         } 
/* 1134 */       }  this.removeRAs = this.removeRAs.nextRemove;
/* 1135 */       renderAtom.nextRemove = null;
/* 1136 */       renderAtom.prevRemove = null;
/* 1137 */       if (renderAtom.isOriented()) {
/* 1138 */         this.renderBin.orientedRAs.remove(this.renderBin.orientedRAs.indexOf(renderAtom));
/*      */       }
/*      */       
/* 1141 */       if (this.textureBin.environmentSet.lightBin.geometryBackground == null && !this.isOpaqueOrInOG && this.renderBin.transpSortMode == 1)
/*      */       {
/* 1143 */         this.renderBin.removeTransparentObject(renderAtom);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1148 */     if (this.addRAs == null)
/*      */     {
/*      */       
/* 1151 */       if (this.numRenderAtoms == 0) {
/*      */         
/* 1153 */         if ((this.primaryMoleculeType & DLIST_MOLECULE) != 0) {
/* 1154 */           this.renderBin.addDisplayListResourceFreeList(this);
/* 1155 */           this.vwcBounds.set(null);
/* 1156 */           this.displayListId = 0;
/* 1157 */           this.displayListIdObj = null;
/*      */         } 
/* 1159 */         if (this.locale != this.renderBin.locale) {
/* 1160 */           this.localeLocalToVworld = null;
/*      */         }
/* 1162 */         this.textureBin.removeRenderMolecule(this);
/*      */       }
/* 1164 */       else if ((this.primaryMoleculeType & DLIST_MOLECULE) != 0 && this.primaryChanged) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1169 */         this.renderBin.addDirtyRenderMolecule(this);
/* 1170 */         this.vwcBounds.set(null);
/* 1171 */         RenderAtomListInfo renderAtomListInfo = this.primaryRenderAtomList;
/* 1172 */         while (renderAtomListInfo != null) {
/* 1173 */           this.vwcBounds.combine(renderAtomListInfo.renderAtom.localeVwcBounds);
/* 1174 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         } 
/* 1176 */         this.primaryChanged = false;
/*      */       } 
/*      */     }
/*      */     
/* 1180 */     this.numEditingRenderAtoms = this.numRenderAtoms;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateObject() {
/* 1187 */     if (this.textureBin == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1191 */     if (this.addRAs != null) {
/* 1192 */       while (this.addRAs != null) {
/*      */         
/* 1194 */         this.numRenderAtoms++;
/* 1195 */         RenderAtom renderAtom = this.addRAs;
/* 1196 */         renderAtom.renderMolecule = this;
/* 1197 */         renderAtom.added = null;
/* 1198 */         for (byte b = 0; b < renderAtom.rListInfo.length; b++) {
/* 1199 */           RenderAtomListInfo renderAtomListInfo = renderAtom.rListInfo[b];
/*      */           
/* 1201 */           if (renderAtomListInfo.geometry() != null) {
/*      */             
/* 1203 */             renderAtomListInfo.groupType = evalRinfoGroupType(renderAtomListInfo);
/* 1204 */             if ((renderAtomListInfo.groupType & RenderAtom.PRIMARY) != 0) {
/* 1205 */               if ((renderAtomListInfo.groupType & RenderAtom.DLIST) != 0 && this.primaryRenderMethod == null) {
/* 1206 */                 this.primaryMoleculeType = DLIST_MOLECULE;
/* 1207 */                 this.renderBin.renderMoleculeList.add(this);
/*      */                 
/* 1209 */                 if (this.vwcBounds == null)
/* 1210 */                   this.vwcBounds = new BoundingBox((BoundingBox)null); 
/* 1211 */                 this.primaryRenderMethod = VirtualUniverse.mc.getDisplayListRenderMethod();
/*      */ 
/*      */                 
/* 1214 */                 if (this.displayListId == 0) {
/* 1215 */                   this.displayListIdObj = VirtualUniverse.mc.getDisplayListId();
/* 1216 */                   this.displayListId = this.displayListIdObj.intValue();
/*      */                 }
/*      */               
/* 1219 */               } else if ((renderAtomListInfo.groupType & RenderAtom.SEPARATE_DLIST_PER_RINFO) != 0 && this.primaryRenderMethod == null) {
/*      */                 
/* 1221 */                 this.primaryMoleculeType = SEPARATE_DLIST_PER_RINFO_MOLECULE;
/* 1222 */                 this.renderBin.renderMoleculeList.add(this);
/* 1223 */                 this.primaryRenderMethod = VirtualUniverse.mc.getDisplayListRenderMethod();
/*      */               } 
/*      */ 
/*      */               
/* 1227 */               this.primaryChanged = true;
/* 1228 */               if (this.primaryRenderAtomList == null) {
/* 1229 */                 this.primaryRenderAtomList = renderAtomListInfo;
/*      */               } else {
/*      */                 
/* 1232 */                 renderAtomListInfo.next = this.primaryRenderAtomList;
/* 1233 */                 this.primaryRenderAtomList.prev = renderAtomListInfo;
/* 1234 */                 this.primaryRenderAtomList = renderAtomListInfo;
/*      */               } 
/* 1236 */               if (this.primaryMoleculeType == SEPARATE_DLIST_PER_RINFO_MOLECULE) {
/* 1237 */                 if (renderAtomListInfo.renderAtom.dlistIds == null) {
/* 1238 */                   renderAtomListInfo.renderAtom.dlistIds = new int[renderAtomListInfo.renderAtom.rListInfo.length];
/*      */                   
/* 1240 */                   for (byte b1 = 0; b1 < renderAtomListInfo.renderAtom.dlistIds.length; b1++) {
/* 1241 */                     renderAtomListInfo.renderAtom.dlistIds[b1] = -1;
/*      */                   }
/*      */                 } 
/* 1244 */                 if (renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] == -1) {
/* 1245 */                   renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] = VirtualUniverse.mc.getDisplayListId().intValue();
/* 1246 */                   this.renderBin.addDlistPerRinfo.add(renderAtomListInfo);
/*      */                 } 
/*      */               } 
/*      */ 
/*      */               
/* 1251 */               if (this.primaryMoleculeType == 8) {
/* 1252 */                 RasterRetained rasterRetained = (RasterRetained)renderAtomListInfo.geometry();
/* 1253 */                 this.renderBin.addGeometryToLockList(rasterRetained);
/* 1254 */                 if (rasterRetained.image != null) {
/* 1255 */                   this.renderBin.addNodeComponent(rasterRetained.image);
/*      */                 }
/*      */               } 
/* 1258 */             } else if ((renderAtomListInfo.groupType & RenderAtom.SEPARATE_DLIST_PER_GEO) != 0) {
/* 1259 */               if (this.separateDlistRenderAtomList == null) {
/* 1260 */                 this.separateDlistRenderAtomList = renderAtomListInfo;
/*      */               } else {
/*      */                 
/* 1263 */                 renderAtomListInfo.next = this.separateDlistRenderAtomList;
/* 1264 */                 this.separateDlistRenderAtomList.prev = renderAtomListInfo;
/* 1265 */                 this.separateDlistRenderAtomList = renderAtomListInfo;
/*      */               } 
/* 1267 */               ((GeometryArrayRetained)renderAtomListInfo.geometry()).assignDlistId();
/* 1268 */               this.renderBin.addGeometryDlist(renderAtomListInfo);
/*      */             } else {
/*      */               
/* 1271 */               if (this.secondaryRenderMethod == null)
/* 1272 */                 this.secondaryRenderMethod = this.cachedVertexArrayRenderMethod; 
/* 1273 */               if (this.vertexArrayRenderAtomList == null) {
/* 1274 */                 this.vertexArrayRenderAtomList = renderAtomListInfo;
/*      */               } else {
/*      */                 
/* 1277 */                 renderAtomListInfo.next = this.vertexArrayRenderAtomList;
/* 1278 */                 this.vertexArrayRenderAtomList.prev = renderAtomListInfo;
/* 1279 */                 this.vertexArrayRenderAtomList = renderAtomListInfo;
/*      */               } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1287 */               GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 1288 */               if (!(geometryArrayRetained instanceof IndexedGeometryArrayRetained) || (geometryArrayRetained.vertexFormat & 0x200) != 0) {
/*      */                 
/* 1290 */                 this.renderBin.addGeometryToLockList(geometryArrayRetained);
/*      */ 
/*      */ 
/*      */                 
/* 1294 */                 if ((geometryArrayRetained.vertexFormat & 0x80) != 0 && geometryArrayRetained.c4fAllocated == 0 && (geometryArrayRetained.vertexFormat & 0x4) != 0 && this.useAlpha)
/*      */                 {
/*      */ 
/*      */                   
/* 1298 */                   this.renderBin.addDirtyReferenceGeometry(geometryArrayRetained); } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/* 1303 */         this.addRAs = this.addRAs.nextAdd;
/* 1304 */         renderAtom.nextAdd = null;
/* 1305 */         renderAtom.prevAdd = null;
/* 1306 */         if (renderAtom.isOriented()) {
/* 1307 */           this.renderBin.orientedRAs.add(renderAtom);
/*      */         }
/*      */ 
/*      */         
/* 1311 */         if (!this.isOpaqueOrInOG && this.textureBin.environmentSet.lightBin.geometryBackground == null && this.renderBin.transpSortMode == 1) {
/*      */           
/* 1313 */           GeometryRetained geometryRetained = null;
/* 1314 */           byte b1 = 0;
/* 1315 */           while (geometryRetained == null && b1 < renderAtom.rListInfo.length) {
/* 1316 */             geometryRetained = renderAtom.rListInfo[b1].geometry();
/* 1317 */             b1++;
/*      */           } 
/* 1319 */           if (geometryRetained != null) {
/* 1320 */             if (renderAtom.parentTInfo != null && renderAtom.parentTInfo[b1 - true] != null) {
/* 1321 */               this.renderBin.updateTransparentInfo(renderAtom);
/*      */               
/*      */               continue;
/*      */             } 
/* 1325 */             this.renderBin.addTransparentObject(renderAtom);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1333 */       if ((this.primaryMoleculeType & DLIST_MOLECULE) != 0 && this.primaryChanged) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1338 */         this.renderBin.addDirtyRenderMolecule(this);
/* 1339 */         this.vwcBounds.set(null);
/* 1340 */         RenderAtomListInfo renderAtomListInfo = this.primaryRenderAtomList;
/* 1341 */         while (renderAtomListInfo != null) {
/* 1342 */           this.vwcBounds.combine(renderAtomListInfo.renderAtom.localeVwcBounds);
/* 1343 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         } 
/* 1345 */         this.primaryChanged = false;
/*      */       } 
/*      */ 
/*      */       
/* 1349 */       if ((this.onUpdateList & LOCALE_CHANGED) != 0) {
/* 1350 */         handleLocaleChange();
/*      */       }
/*      */       
/* 1353 */       if (this.locale != this.renderBin.locale) {
/* 1354 */         translate();
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1362 */       if (this.renderBin.localeChanged) {
/* 1363 */         handleLocaleChange();
/*      */       }
/*      */       
/* 1366 */       if (this.locale != this.renderBin.locale) {
/* 1367 */         translate();
/*      */       }
/*      */       
/* 1370 */       if ((this.onUpdateList & UPDATE_BACKGROUND_TRANSFORM) != 0) {
/* 1371 */         int i = this.localToVworldIndex[0];
/* 1372 */         this.localeLocalToVworld[i].getRotation(this.infLocalToVworld[i]);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1377 */       if ((this.onUpdateList & BOUNDS_RECOMPUTE_UPDATE) != 0) {
/* 1378 */         this.vwcBounds.set(null);
/* 1379 */         RenderAtomListInfo renderAtomListInfo = this.primaryRenderAtomList;
/* 1380 */         while (renderAtomListInfo != null) {
/* 1381 */           this.vwcBounds.combine(renderAtomListInfo.renderAtom.localeVwcBounds);
/* 1382 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1388 */     this.onUpdateList &= IN_DIRTY_RENDERMOLECULE_LIST;
/*      */     
/* 1390 */     this.numEditingRenderAtoms = this.numRenderAtoms;
/*      */   }
/*      */   
/*      */   boolean canBeInDisplayList(GeometryRetained paramGeometryRetained, GeometryAtom paramGeometryAtom) {
/* 1394 */     if (paramGeometryAtom.source.sourceNode instanceof MorphRetained) {
/* 1395 */       return false;
/*      */     }
/*      */     
/* 1398 */     return paramGeometryRetained.canBeInDisplayList(paramGeometryAtom.alphaEditable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1404 */   final boolean geoNotAltered(GeometryArrayRetained paramGeometryArrayRetained) { return ((paramGeometryArrayRetained.vertexFormat & 0x4) == 0 || (!this.textureBin.attributeBin.ignoreVertexColors && !this.useAlpha)); }
/*      */ 
/*      */ 
/*      */   
/*      */   int evalRinfoGroupType(RenderAtomListInfo paramRenderAtomListInfo) {
/* 1409 */     int i = 0;
/*      */     
/* 1411 */     GeometryRetained geometryRetained = paramRenderAtomListInfo.geometry();
/* 1412 */     if (geometryRetained == null) {
/* 1413 */       return i;
/*      */     }
/* 1415 */     if ((this.primaryMoleculeType & (COMPRESSED_MOLECULE | RASTER_MOLECULE | TEXT3D_MOLECULE | ORIENTEDSHAPE3D_MOLECULE)) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1419 */       i = RenderAtom.OTHER;
/*      */     }
/* 1421 */     else if (canBeInDisplayList(geometryRetained, paramRenderAtomListInfo.renderAtom.geometryAtom)) {
/*      */ 
/*      */       
/* 1424 */       if (!((GeometryArrayRetained)geometryRetained).isShared || paramRenderAtomListInfo.renderAtom.geometryAtom.source.staticTransform != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1432 */         if (this.primaryMoleculeType == SEPARATE_DLIST_PER_RINFO_MOLECULE) {
/* 1433 */           i = RenderAtom.SEPARATE_DLIST_PER_RINFO;
/*      */         
/*      */         }
/* 1436 */         else if (this.isOpaqueOrInOG || this.renderBin.transpSortMode == 0) {
/*      */           
/* 1438 */           i = RenderAtom.DLIST;
/*      */         } else {
/*      */           
/* 1441 */           i = RenderAtom.SEPARATE_DLIST_PER_RINFO;
/*      */         }
/*      */       
/*      */       }
/* 1445 */       else if (geoNotAltered((GeometryArrayRetained)paramRenderAtomListInfo.geometry())) {
/* 1446 */         i = RenderAtom.SEPARATE_DLIST_PER_GEO;
/*      */       } else {
/*      */         
/* 1449 */         i = RenderAtom.VARRAY;
/*      */       } 
/*      */     } else {
/*      */       
/* 1453 */       i = RenderAtom.VARRAY;
/*      */     } 
/* 1455 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addRenderAtom(RenderAtom paramRenderAtom, RenderBin paramRenderBin) {
/* 1466 */     paramRenderAtom.envSet = this.textureBin.environmentSet;
/* 1467 */     paramRenderAtom.renderMolecule = this;
/* 1468 */     paramRenderAtom.dirtyMask &= (RenderAtom.NEED_SEPARATE_LOCALE_VWC_BOUNDS ^ 0xFFFFFFFF);
/*      */     
/* 1470 */     AppearanceRetained appearanceRetained = paramRenderAtom.geometryAtom.source.appearance;
/*      */     
/* 1472 */     MaterialRetained materialRetained = (appearanceRetained == null) ? null : appearanceRetained.material;
/* 1473 */     if (!this.soleUser && this.material != materialRetained)
/*      */     {
/* 1475 */       this.material = this.definingMaterial;
/*      */     }
/*      */     
/* 1478 */     if ((this.geometryType & 0x4) != 0) {
/* 1479 */       PolygonAttributesRetained polygonAttributesRetained = (appearanceRetained == null) ? null : appearanceRetained.polygonAttributes;
/*      */       
/* 1481 */       if (!this.soleUser && this.polygonAttributes != polygonAttributesRetained)
/*      */       {
/* 1483 */         this.polygonAttributes = this.definingPolygonAttributes;
/*      */       }
/*      */     } 
/*      */     
/* 1487 */     if ((this.geometryType & 0x2) != 0) {
/* 1488 */       LineAttributesRetained lineAttributesRetained = (appearanceRetained == null) ? null : appearanceRetained.lineAttributes;
/*      */       
/* 1490 */       if (!this.soleUser && this.lineAttributes != lineAttributesRetained)
/*      */       {
/* 1492 */         this.lineAttributes = this.definingLineAttributes;
/*      */       }
/*      */     } 
/*      */     
/* 1496 */     if ((this.geometryType & true) != 0) {
/* 1497 */       PointAttributesRetained pointAttributesRetained = (appearanceRetained == null) ? null : appearanceRetained.pointAttributes;
/*      */       
/* 1499 */       if (!this.soleUser && this.pointAttributes != pointAttributesRetained)
/*      */       {
/* 1501 */         this.pointAttributes = this.definingPointAttributes;
/*      */       }
/*      */     } 
/*      */     
/* 1505 */     ColoringAttributesRetained coloringAttributesRetained = (appearanceRetained == null) ? null : appearanceRetained.coloringAttributes;
/*      */     
/* 1507 */     if (!this.soleUser && this.coloringAttributes != coloringAttributesRetained)
/*      */     {
/* 1509 */       this.coloringAttributes = this.definingColoringAttributes;
/*      */     }
/*      */     
/* 1512 */     TransparencyAttributesRetained transparencyAttributesRetained = (appearanceRetained == null) ? null : appearanceRetained.transparencyAttributes;
/*      */     
/* 1514 */     if (!this.soleUser && this.transparency != transparencyAttributesRetained)
/*      */     {
/* 1516 */       this.transparency = this.definingTransparency;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1523 */     if (!paramRenderAtom.inRenderBin())
/* 1524 */       for (byte b = 0; b < paramRenderAtom.rListInfo.length; b++) {
/* 1525 */         if (paramRenderAtom.rListInfo[b].geometry() != null) {
/*      */           
/* 1527 */           int i = evalRinfoGroupType(paramRenderAtom.rListInfo[b]);
/* 1528 */           if (i != RenderAtom.DLIST) {
/* 1529 */             paramRenderAtom.dirtyMask |= RenderAtom.NEED_SEPARATE_LOCALE_VWC_BOUNDS;
/*      */           }
/*      */         } 
/*      */       }  
/* 1533 */     if (paramRenderAtom.removed == this) {
/*      */ 
/*      */       
/* 1536 */       if (paramRenderAtom == this.removeRAs) {
/* 1537 */         this.removeRAs = paramRenderAtom.nextRemove;
/* 1538 */         if (this.removeRAs != null)
/* 1539 */           this.removeRAs.prevRemove = null; 
/* 1540 */         paramRenderAtom.nextRemove = null;
/* 1541 */         paramRenderAtom.prevRemove = null;
/*      */       }
/*      */       else {
/*      */         
/* 1545 */         paramRenderAtom.prevRemove.nextRemove = paramRenderAtom.nextRemove;
/* 1546 */         if (paramRenderAtom.nextRemove != null)
/* 1547 */           paramRenderAtom.nextRemove.prevRemove = paramRenderAtom.prevRemove; 
/* 1548 */         paramRenderAtom.nextRemove = null;
/* 1549 */         paramRenderAtom.prevRemove = null;
/*      */       } 
/*      */       
/* 1552 */       paramRenderAtom.removed = null;
/*      */       
/* 1554 */       for (byte b = 0; b < paramRenderAtom.rListInfo.length; b++) {
/* 1555 */         if (paramRenderAtom.rListInfo[b].geometry() != null)
/*      */         {
/* 1557 */           if (((paramRenderAtom.rListInfo[b]).groupType & RenderAtom.DLIST) != 0) {
/* 1558 */             this.renderBin.addDirtyRenderMolecule(this);
/* 1559 */           } else if (((paramRenderAtom.rListInfo[b]).groupType & RenderAtom.SEPARATE_DLIST_PER_RINFO) != 0) {
/* 1560 */             this.renderBin.addDlistPerRinfo.add(paramRenderAtom.rListInfo[b]);
/*      */           }
/* 1562 */           else if (((paramRenderAtom.rListInfo[b]).groupType & RenderAtom.SEPARATE_DLIST_PER_GEO) != 0) {
/* 1563 */             this.renderBin.addGeometryDlist(paramRenderAtom.rListInfo[b]);
/*      */           }  } 
/*      */       } 
/* 1566 */       if (this.removeRAs == null) {
/* 1567 */         paramRenderBin.removeRenderAtomInRMList.remove(this);
/*      */       }
/*      */     } else {
/*      */       
/* 1571 */       if (this.addRAs == null) {
/* 1572 */         this.addRAs = paramRenderAtom;
/* 1573 */         paramRenderAtom.nextAdd = null;
/* 1574 */         paramRenderAtom.prevAdd = null;
/*      */       } else {
/*      */         
/* 1577 */         paramRenderAtom.nextAdd = this.addRAs;
/* 1578 */         paramRenderAtom.prevAdd = null;
/* 1579 */         this.addRAs.prevAdd = paramRenderAtom;
/* 1580 */         this.addRAs = paramRenderAtom;
/*      */       } 
/* 1582 */       paramRenderAtom.added = this;
/* 1583 */       if (this.onUpdateList == 0)
/* 1584 */         paramRenderBin.objUpdateList.add(this); 
/* 1585 */       this.onUpdateList |= NEW_RENDERATOMS_UPDATE;
/*      */     } 
/*      */     
/* 1588 */     if (this.renderBin.localeChanged && !this.doInfinite) {
/* 1589 */       if (this.onUpdateList == 0)
/* 1590 */         paramRenderBin.objUpdateList.add(this); 
/* 1591 */       this.onUpdateList |= LOCALE_CHANGED;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1597 */     if (this.numEditingRenderAtoms == 0) {
/* 1598 */       this.textureBin.incrActiveRenderMolecule();
/*      */     }
/* 1600 */     this.numEditingRenderAtoms++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeRenderAtom(RenderAtom paramRenderAtom) {
/* 1609 */     paramRenderAtom.renderMolecule = null;
/* 1610 */     if (paramRenderAtom.added == this) {
/*      */ 
/*      */ 
/*      */       
/* 1614 */       if (paramRenderAtom == this.addRAs) {
/* 1615 */         this.addRAs = paramRenderAtom.nextAdd;
/* 1616 */         if (this.addRAs != null)
/* 1617 */           this.addRAs.prevAdd = null; 
/* 1618 */         paramRenderAtom.nextAdd = null;
/* 1619 */         paramRenderAtom.prevAdd = null;
/*      */       }
/*      */       else {
/*      */         
/* 1623 */         paramRenderAtom.prevAdd.nextAdd = paramRenderAtom.nextAdd;
/* 1624 */         if (paramRenderAtom.nextAdd != null)
/* 1625 */           paramRenderAtom.nextAdd.prevAdd = paramRenderAtom.prevAdd; 
/* 1626 */         paramRenderAtom.nextAdd = null;
/* 1627 */         paramRenderAtom.prevAdd = null;
/*      */       } 
/*      */       
/* 1630 */       paramRenderAtom.added = null;
/* 1631 */       paramRenderAtom.envSet = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1650 */       if (this.removeRAs == null) {
/* 1651 */         this.removeRAs = paramRenderAtom;
/* 1652 */         paramRenderAtom.nextRemove = null;
/* 1653 */         paramRenderAtom.prevRemove = null;
/*      */       } else {
/*      */         
/* 1656 */         paramRenderAtom.nextRemove = this.removeRAs;
/* 1657 */         paramRenderAtom.prevRemove = null;
/* 1658 */         this.removeRAs.prevRemove = paramRenderAtom;
/* 1659 */         this.removeRAs = paramRenderAtom;
/*      */       } 
/* 1661 */       paramRenderAtom.removed = this;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1666 */     if (!this.renderBin.removeRenderAtomInRMList.contains(this)) {
/* 1667 */       this.renderBin.removeRenderAtomInRMList.add(this);
/*      */     }
/*      */ 
/*      */     
/* 1671 */     this.numEditingRenderAtoms--;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1676 */     if (this.numEditingRenderAtoms == 0) {
/* 1677 */       this.textureBin.decrActiveRenderMolecule();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void recalcBounds() {
/* 1687 */     if (this.primaryRenderMethod == VirtualUniverse.mc.getDisplayListRenderMethod()) {
/*      */       
/* 1689 */       this.vwcBounds.set(null);
/* 1690 */       RenderAtomListInfo renderAtomListInfo = this.primaryRenderAtomList;
/* 1691 */       while (renderAtomListInfo != null) {
/* 1692 */         this.vwcBounds.combine(renderAtomListInfo.renderAtom.localeVwcBounds);
/* 1693 */         renderAtomListInfo = renderAtomListInfo.next;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void evalAlphaUsage(RenderingAttributesRetained paramRenderingAttributesRetained, TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained) {
/* 1700 */     boolean bool3 = false;
/*      */     
/* 1702 */     boolean bool1 = (this.definingTransparency != null && this.definingTransparency.transparencyMode != 4 && (VirtualUniverse.mc.isD3D() || (!VirtualUniverse.mc.isD3D() && this.definingTransparency.transparencyMode != 3))) ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1712 */     if (paramArrayOfTextureUnitStateRetained != null) {
/* 1713 */       byte b = 0;
/* 1714 */       for (; !bool3 && b < paramArrayOfTextureUnitStateRetained.length; 
/* 1715 */         b++) {
/* 1716 */         if (paramArrayOfTextureUnitStateRetained[b] != null && (paramArrayOfTextureUnitStateRetained[b]).texAttrs != null)
/*      */         {
/* 1718 */           bool3 = (bool3 || (paramArrayOfTextureUnitStateRetained[b]).texAttrs.textureMode == 4) ? 1 : 0;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1725 */     boolean bool2 = (paramRenderingAttributesRetained != null && paramRenderingAttributesRetained.alphaTestFunction != 0) ? 1 : 0;
/*      */ 
/*      */     
/* 1728 */     boolean bool = this.useAlpha;
/* 1729 */     this.useAlpha = (bool1 || bool2 || bool3);
/*      */     
/* 1731 */     if (!bool && this.useAlpha) {
/* 1732 */       GeometryArrayRetained geometryArrayRetained = null;
/*      */       
/* 1734 */       if (this.vertexArrayRenderAtomList != null) {
/* 1735 */         geometryArrayRetained = (GeometryArrayRetained)this.vertexArrayRenderAtomList.geometry();
/*      */       }
/* 1737 */       if (geometryArrayRetained != null && (
/* 1738 */         !(geometryArrayRetained instanceof IndexedGeometryArrayRetained) || (geometryArrayRetained.vertexFormat & 0x200) != 0)) {
/*      */         
/* 1740 */         this.renderBin.addGeometryToLockList(geometryArrayRetained);
/*      */ 
/*      */ 
/*      */         
/* 1744 */         if ((geometryArrayRetained.vertexFormat & 0x80) != 0 && geometryArrayRetained.c4fAllocated == 0 && (geometryArrayRetained.vertexFormat & 0x4) != 0 && this.useAlpha)
/*      */         {
/*      */ 
/*      */           
/* 1748 */           this.renderBin.addDirtyReferenceGeometry(geometryArrayRetained);
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
/*      */   final boolean isSwitchOn() {
/* 1760 */     if (this.primaryRenderAtomList != null) {
/* 1761 */       return this.primaryRenderAtomList.renderAtom.geometryAtom.source.switchState.lastSwitchOn;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1766 */     if (this.vertexArrayRenderAtomList != null) {
/* 1767 */       return this.vertexArrayRenderAtomList.renderAtom.geometryAtom.source.switchState.lastSwitchOn;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1772 */     if (this.separateDlistRenderAtomList != null) {
/* 1773 */       return this.separateDlistRenderAtomList.renderAtom.geometryAtom.source.switchState.lastSwitchOn;
/*      */     }
/*      */     
/* 1776 */     return false;
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
/*      */   boolean render(Canvas3D paramCanvas3D, int paramInt1, int paramInt2) { // Byte code:
/*      */     //   0: getstatic javax/media/j3d/RenderMolecule.$assertionsDisabled : Z
/*      */     //   3: ifne -> 18
/*      */     //   6: iload_2
/*      */     //   7: iflt -> 18
/*      */     //   10: new java/lang/AssertionError
/*      */     //   13: dup
/*      */     //   14: invokespecial <init> : ()V
/*      */     //   17: athrow
/*      */     //   18: aload_0
/*      */     //   19: invokevirtual isSwitchOn : ()Z
/*      */     //   22: istore #4
/*      */     //   24: iload #4
/*      */     //   26: ifne -> 31
/*      */     //   29: iconst_0
/*      */     //   30: ireturn
/*      */     //   31: iconst_0
/*      */     //   32: istore #4
/*      */     //   34: aload_1
/*      */     //   35: iconst_4
/*      */     //   36: aload_0
/*      */     //   37: invokevirtual setStateToUpdate : (ILjava/lang/Object;)V
/*      */     //   40: iconst_1
/*      */     //   41: istore #5
/*      */     //   43: aload_0
/*      */     //   44: aload_0
/*      */     //   45: getfield trans : [Ljavax/media/j3d/Transform3D;
/*      */     //   48: aload_0
/*      */     //   49: getfield localToVworldIndex : [I
/*      */     //   52: iconst_0
/*      */     //   53: iaload
/*      */     //   54: aaload
/*      */     //   55: invokevirtual isCongruent : ()Z
/*      */     //   58: ifne -> 65
/*      */     //   61: iconst_1
/*      */     //   62: goto -> 66
/*      */     //   65: iconst_0
/*      */     //   66: putfield isNonUniformScale : Z
/*      */     //   69: iload_2
/*      */     //   70: bipush #-2
/*      */     //   72: if_icmpne -> 144
/*      */     //   75: aload_0
/*      */     //   76: getfield texCoordSetMapLen : I
/*      */     //   79: aload_1
/*      */     //   80: getfield maxTexCoordSets : I
/*      */     //   83: if_icmpgt -> 144
/*      */     //   86: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   89: invokevirtual isD3D : ()Z
/*      */     //   92: ifeq -> 147
/*      */     //   95: aload_0
/*      */     //   96: getfield definingPolygonAttributes : Ljavax/media/j3d/PolygonAttributesRetained;
/*      */     //   99: ifnull -> 137
/*      */     //   102: aload_0
/*      */     //   103: getfield isQuadGeometryArray : Z
/*      */     //   106: ifeq -> 120
/*      */     //   109: aload_0
/*      */     //   110: getfield definingPolygonAttributes : Ljavax/media/j3d/PolygonAttributesRetained;
/*      */     //   113: getfield polygonMode : I
/*      */     //   116: iconst_1
/*      */     //   117: if_icmpeq -> 144
/*      */     //   120: aload_0
/*      */     //   121: getfield isTriGeometryArray : Z
/*      */     //   124: ifeq -> 137
/*      */     //   127: aload_0
/*      */     //   128: getfield definingPolygonAttributes : Ljavax/media/j3d/PolygonAttributesRetained;
/*      */     //   131: getfield polygonMode : I
/*      */     //   134: ifeq -> 144
/*      */     //   137: aload_1
/*      */     //   138: getfield texLinearMode : Z
/*      */     //   141: ifeq -> 147
/*      */     //   144: iconst_0
/*      */     //   145: istore #5
/*      */     //   147: aload_0
/*      */     //   148: getfield primaryMoleculeType : I
/*      */     //   151: getstatic javax/media/j3d/RenderMolecule.TEXT3D_MOLECULE : I
/*      */     //   154: getstatic javax/media/j3d/RenderMolecule.ORIENTEDSHAPE3D_MOLECULE : I
/*      */     //   157: ior
/*      */     //   158: iand
/*      */     //   159: ifne -> 271
/*      */     //   162: aload_0
/*      */     //   163: getfield primaryRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   166: ifnull -> 300
/*      */     //   169: aload_0
/*      */     //   170: getfield primaryRenderMethod : Ljavax/media/j3d/RenderMethod;
/*      */     //   173: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   176: invokevirtual getDisplayListRenderMethod : ()Ljavax/media/j3d/RenderMethod;
/*      */     //   179: if_acmpne -> 187
/*      */     //   182: iload #5
/*      */     //   184: ifeq -> 248
/*      */     //   187: aload_0
/*      */     //   188: getfield primaryMoleculeType : I
/*      */     //   191: getstatic javax/media/j3d/RenderMolecule.SEPARATE_DLIST_PER_RINFO_MOLECULE : I
/*      */     //   194: if_icmpeq -> 222
/*      */     //   197: aload_0
/*      */     //   198: getfield primaryRenderMethod : Ljavax/media/j3d/RenderMethod;
/*      */     //   201: aload_0
/*      */     //   202: aload_1
/*      */     //   203: aload_0
/*      */     //   204: getfield primaryRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   207: iload_3
/*      */     //   208: invokeinterface render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   213: ifeq -> 300
/*      */     //   216: iconst_1
/*      */     //   217: istore #4
/*      */     //   219: goto -> 300
/*      */     //   222: aload_0
/*      */     //   223: getfield renderBin : Ljavax/media/j3d/RenderBin;
/*      */     //   226: getfield dlistRenderMethod : Ljavax/media/j3d/DisplayListRenderMethod;
/*      */     //   229: aload_0
/*      */     //   230: aload_1
/*      */     //   231: aload_0
/*      */     //   232: getfield primaryRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   235: iload_3
/*      */     //   236: invokevirtual renderSeparateDlistPerRinfo : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   239: ifeq -> 300
/*      */     //   242: iconst_1
/*      */     //   243: istore #4
/*      */     //   245: goto -> 300
/*      */     //   248: aload_0
/*      */     //   249: getfield cachedVertexArrayRenderMethod : Ljavax/media/j3d/VertexArrayRenderMethod;
/*      */     //   252: aload_0
/*      */     //   253: aload_1
/*      */     //   254: aload_0
/*      */     //   255: getfield primaryRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   258: iload_3
/*      */     //   259: invokevirtual render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   262: ifeq -> 300
/*      */     //   265: iconst_1
/*      */     //   266: istore #4
/*      */     //   268: goto -> 300
/*      */     //   271: aload_0
/*      */     //   272: getfield primaryRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   275: ifnull -> 300
/*      */     //   278: aload_0
/*      */     //   279: getfield primaryRenderMethod : Ljavax/media/j3d/RenderMethod;
/*      */     //   282: aload_0
/*      */     //   283: aload_1
/*      */     //   284: aload_0
/*      */     //   285: getfield primaryRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   288: iload_3
/*      */     //   289: invokeinterface render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   294: ifeq -> 300
/*      */     //   297: iconst_1
/*      */     //   298: istore #4
/*      */     //   300: aload_0
/*      */     //   301: getfield separateDlistRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   304: ifnull -> 358
/*      */     //   307: iload #5
/*      */     //   309: ifeq -> 338
/*      */     //   312: aload_0
/*      */     //   313: getfield renderBin : Ljavax/media/j3d/RenderBin;
/*      */     //   316: getfield dlistRenderMethod : Ljavax/media/j3d/DisplayListRenderMethod;
/*      */     //   319: aload_0
/*      */     //   320: aload_1
/*      */     //   321: aload_0
/*      */     //   322: getfield separateDlistRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   325: iload_3
/*      */     //   326: invokevirtual renderSeparateDlists : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   329: ifeq -> 358
/*      */     //   332: iconst_1
/*      */     //   333: istore #4
/*      */     //   335: goto -> 358
/*      */     //   338: aload_0
/*      */     //   339: getfield cachedVertexArrayRenderMethod : Ljavax/media/j3d/VertexArrayRenderMethod;
/*      */     //   342: aload_0
/*      */     //   343: aload_1
/*      */     //   344: aload_0
/*      */     //   345: getfield separateDlistRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   348: iload_3
/*      */     //   349: invokevirtual render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   352: ifeq -> 358
/*      */     //   355: iconst_1
/*      */     //   356: istore #4
/*      */     //   358: aload_0
/*      */     //   359: getfield vertexArrayRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   362: ifnull -> 385
/*      */     //   365: aload_0
/*      */     //   366: getfield cachedVertexArrayRenderMethod : Ljavax/media/j3d/VertexArrayRenderMethod;
/*      */     //   369: aload_0
/*      */     //   370: aload_1
/*      */     //   371: aload_0
/*      */     //   372: getfield vertexArrayRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   375: iload_3
/*      */     //   376: invokevirtual render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   379: ifeq -> 385
/*      */     //   382: iconst_1
/*      */     //   383: istore #4
/*      */     //   385: iload #4
/*      */     //   387: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1783	-> 0
/*      */     //   #1785	-> 18
/*      */     //   #1787	-> 24
/*      */     //   #1788	-> 29
/*      */     //   #1791	-> 31
/*      */     //   #1794	-> 34
/*      */     //   #1796	-> 40
/*      */     //   #1797	-> 43
/*      */     //   #1812	-> 69
/*      */     //   #1823	-> 144
/*      */     //   #1833	-> 147
/*      */     //   #1835	-> 162
/*      */     //   #1836	-> 169
/*      */     //   #1838	-> 187
/*      */     //   #1840	-> 197
/*      */     //   #1841	-> 216
/*      */     //   #1844	-> 222
/*      */     //   #1845	-> 242
/*      */     //   #1849	-> 248
/*      */     //   #1852	-> 265
/*      */     //   #1859	-> 271
/*      */     //   #1860	-> 278
/*      */     //   #1862	-> 297
/*      */     //   #1867	-> 300
/*      */     //   #1868	-> 307
/*      */     //   #1869	-> 312
/*      */     //   #1872	-> 332
/*      */     //   #1876	-> 338
/*      */     //   #1879	-> 355
/*      */     //   #1887	-> 358
/*      */     //   #1888	-> 365
/*      */     //   #1891	-> 382
/*      */     //   #1894	-> 385 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateAttributes(Canvas3D paramCanvas3D, int paramInt) {
/* 1900 */     boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1905 */     int i = this.geometryType | 0x8 | 0x20 | 0x10;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1910 */     if ((paramCanvas3D.canvasDirty & i) != 0) {
/* 1911 */       if ((this.geometryType & 0x4) != 0) {
/* 1912 */         if (this.definingPolygonAttributes == null) {
/* 1913 */           paramCanvas3D.resetPolygonAttributes(paramCanvas3D.ctx);
/*      */         } else {
/* 1915 */           this.definingPolygonAttributes.updateNative(paramCanvas3D.ctx);
/*      */         } 
/* 1917 */         paramCanvas3D.polygonAttributes = this.polygonAttributes;
/*      */       } 
/* 1919 */       if ((this.geometryType & 0x2) != 0) {
/* 1920 */         if (this.definingLineAttributes == null) {
/* 1921 */           paramCanvas3D.resetLineAttributes(paramCanvas3D.ctx);
/*      */         } else {
/* 1923 */           this.definingLineAttributes.updateNative(paramCanvas3D.ctx);
/*      */         } 
/* 1925 */         paramCanvas3D.lineAttributes = this.lineAttributes;
/*      */       } 
/* 1927 */       if ((this.geometryType & true) != 0) {
/* 1928 */         if (this.definingPointAttributes == null) {
/* 1929 */           paramCanvas3D.resetPointAttributes(paramCanvas3D.ctx);
/*      */         } else {
/* 1931 */           this.definingPointAttributes.updateNative(paramCanvas3D.ctx);
/*      */         } 
/* 1933 */         paramCanvas3D.pointAttributes = this.pointAttributes;
/*      */       } 
/*      */       
/* 1936 */       if (this.definingTransparency == null) {
/* 1937 */         paramCanvas3D.resetTransparency(paramCanvas3D.ctx, this.geometryType, this.polygonMode, this.lineAA, this.pointAA);
/*      */       } else {
/*      */         
/* 1940 */         this.definingTransparency.updateNative(paramCanvas3D.ctx, this.alpha, this.geometryType, this.polygonMode, this.lineAA, this.pointAA);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1945 */       paramCanvas3D.transparency = this.transparency;
/*      */       
/* 1947 */       if (this.definingMaterial == null) {
/* 1948 */         paramCanvas3D.updateMaterial(paramCanvas3D.ctx, this.red, this.green, this.blue, this.alpha);
/*      */       } else {
/* 1950 */         this.definingMaterial.updateNative(paramCanvas3D.ctx, this.red, this.green, this.blue, this.alpha, this.enableLighting);
/*      */       } 
/*      */ 
/*      */       
/* 1954 */       paramCanvas3D.material = this.material;
/* 1955 */       paramCanvas3D.enableLighting = this.enableLighting;
/*      */       
/* 1957 */       if (this.definingColoringAttributes == null) {
/* 1958 */         paramCanvas3D.resetColoringAttributes(paramCanvas3D.ctx, this.red, this.green, this.blue, this.alpha, this.enableLighting);
/*      */       } else {
/*      */         
/* 1961 */         this.definingColoringAttributes.updateNative(paramCanvas3D.ctx, this.dRed, this.dBlue, this.dGreen, this.alpha, this.enableLighting);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1967 */       paramCanvas3D.coloringAttributes = this.coloringAttributes;
/*      */ 
/*      */ 
/*      */       
/* 1971 */       paramCanvas3D.appHandle = this.appHandle;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1979 */     else if (paramCanvas3D.renderMolecule != this && paramInt != 0) {
/*      */ 
/*      */       
/* 1982 */       if (paramCanvas3D.appHandle != this.appHandle) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1987 */         if (paramCanvas3D.transparency != this.transparency && (paramInt & 0x20) != 0) {
/*      */           
/* 1989 */           bool = true;
/* 1990 */           if (this.definingTransparency == null) {
/*      */             
/* 1992 */             paramCanvas3D.resetTransparency(paramCanvas3D.ctx, this.geometryType, this.polygonMode, this.lineAA, this.pointAA);
/*      */           } else {
/*      */             
/* 1995 */             this.definingTransparency.updateNative(paramCanvas3D.ctx, this.alpha, this.geometryType, this.polygonMode, this.lineAA, this.pointAA);
/*      */           } 
/*      */ 
/*      */           
/* 1999 */           paramCanvas3D.transparency = this.transparency;
/*      */         } 
/*      */         
/* 2002 */         if (bool || paramCanvas3D.enableLighting != this.enableLighting || (paramCanvas3D.material != this.material && (paramInt & true) != 0)) {
/*      */ 
/*      */           
/* 2005 */           if (this.definingMaterial == null) {
/* 2006 */             paramCanvas3D.updateMaterial(paramCanvas3D.ctx, this.red, this.green, this.blue, this.alpha);
/*      */           } else {
/* 2008 */             this.definingMaterial.updateNative(paramCanvas3D.ctx, this.red, this.green, this.blue, this.alpha, this.enableLighting);
/*      */           } 
/*      */ 
/*      */           
/* 2012 */           paramCanvas3D.material = this.material;
/* 2013 */           paramCanvas3D.enableLighting = this.enableLighting;
/*      */         } 
/*      */         
/* 2016 */         if ((this.geometryType & 0x4) != 0 && paramCanvas3D.polygonAttributes != this.polygonAttributes && (paramInt & 0x80) != 0) {
/*      */ 
/*      */ 
/*      */           
/* 2020 */           if (this.definingPolygonAttributes == null) {
/* 2021 */             paramCanvas3D.resetPolygonAttributes(paramCanvas3D.ctx);
/*      */           } else {
/* 2023 */             this.definingPolygonAttributes.updateNative(paramCanvas3D.ctx);
/*      */           } 
/* 2025 */           paramCanvas3D.polygonAttributes = this.polygonAttributes;
/*      */         } 
/*      */         
/* 2028 */         if ((this.geometryType & 0x2) != 0 && paramCanvas3D.lineAttributes != this.lineAttributes && (paramInt & 0x100) != 0) {
/*      */ 
/*      */ 
/*      */           
/* 2032 */           if (this.definingLineAttributes == null) {
/* 2033 */             paramCanvas3D.resetLineAttributes(paramCanvas3D.ctx);
/*      */           } else {
/* 2035 */             this.definingLineAttributes.updateNative(paramCanvas3D.ctx);
/*      */           } 
/* 2037 */           paramCanvas3D.lineAttributes = this.lineAttributes;
/*      */         } 
/*      */         
/* 2040 */         if ((this.geometryType & true) != 0 && paramCanvas3D.pointAttributes != this.pointAttributes && (paramInt & 0x200) != 0) {
/*      */ 
/*      */ 
/*      */           
/* 2044 */           if (this.definingPointAttributes == null) {
/* 2045 */             paramCanvas3D.resetPointAttributes(paramCanvas3D.ctx);
/*      */           } else {
/* 2047 */             this.definingPointAttributes.updateNative(paramCanvas3D.ctx);
/*      */           } 
/* 2049 */           paramCanvas3D.pointAttributes = this.pointAttributes;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2054 */         paramCanvas3D.appHandle = this.appHandle;
/*      */       } 
/*      */ 
/*      */       
/* 2058 */       if (bool || (paramInt & 0x10) != 0) {
/*      */         
/* 2060 */         if (this.definingColoringAttributes == null) {
/* 2061 */           paramCanvas3D.resetColoringAttributes(paramCanvas3D.ctx, this.red, this.green, this.blue, this.alpha, this.enableLighting);
/*      */         }
/*      */         else {
/*      */           
/* 2065 */           this.definingColoringAttributes.updateNative(paramCanvas3D.ctx, this.dRed, this.dBlue, this.dGreen, this.alpha, this.enableLighting);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2072 */         paramCanvas3D.coloringAttributes = this.coloringAttributes;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2077 */     if ((this.primaryMoleculeType & (TEXT3D_MOLECULE | ORIENTEDSHAPE3D_MOLECULE)) == 0) {
/*      */ 
/*      */       
/* 2080 */       Transform3D transform3D = this.trans[this.localToVworldIndex[0]];
/*      */ 
/*      */       
/* 2083 */       if (paramCanvas3D.modelMatrix != transform3D)
/*      */       {
/*      */         
/* 2086 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, transform3D);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2091 */     paramCanvas3D.canvasDirty &= (i ^ 0xFFFFFFFF);
/* 2092 */     paramCanvas3D.renderMolecule = this;
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
/*      */   void transparentSortRender(Canvas3D paramCanvas3D, int paramInt, TransparentRenderingInfo paramTransparentRenderingInfo) { // Byte code:
/*      */     //   0: getstatic javax/media/j3d/RenderMolecule.$assertionsDisabled : Z
/*      */     //   3: ifne -> 18
/*      */     //   6: iload_2
/*      */     //   7: iflt -> 18
/*      */     //   10: new java/lang/AssertionError
/*      */     //   13: dup
/*      */     //   14: invokespecial <init> : ()V
/*      */     //   17: athrow
/*      */     //   18: aload_0
/*      */     //   19: getfield trans : [Ljavax/media/j3d/Transform3D;
/*      */     //   22: aload_0
/*      */     //   23: getfield localToVworldIndex : [I
/*      */     //   26: iconst_0
/*      */     //   27: iaload
/*      */     //   28: aaload
/*      */     //   29: astore #4
/*      */     //   31: aload_1
/*      */     //   32: iconst_4
/*      */     //   33: aload_0
/*      */     //   34: invokevirtual setStateToUpdate : (ILjava/lang/Object;)V
/*      */     //   37: iconst_1
/*      */     //   38: istore #5
/*      */     //   40: iload_2
/*      */     //   41: bipush #-2
/*      */     //   43: if_icmpne -> 115
/*      */     //   46: aload_0
/*      */     //   47: getfield texCoordSetMapLen : I
/*      */     //   50: aload_1
/*      */     //   51: getfield maxTexCoordSets : I
/*      */     //   54: if_icmpgt -> 115
/*      */     //   57: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   60: invokevirtual isD3D : ()Z
/*      */     //   63: ifeq -> 118
/*      */     //   66: aload_0
/*      */     //   67: getfield definingPolygonAttributes : Ljavax/media/j3d/PolygonAttributesRetained;
/*      */     //   70: ifnull -> 108
/*      */     //   73: aload_0
/*      */     //   74: getfield isQuadGeometryArray : Z
/*      */     //   77: ifeq -> 91
/*      */     //   80: aload_0
/*      */     //   81: getfield definingPolygonAttributes : Ljavax/media/j3d/PolygonAttributesRetained;
/*      */     //   84: getfield polygonMode : I
/*      */     //   87: iconst_1
/*      */     //   88: if_icmpeq -> 115
/*      */     //   91: aload_0
/*      */     //   92: getfield isTriGeometryArray : Z
/*      */     //   95: ifeq -> 108
/*      */     //   98: aload_0
/*      */     //   99: getfield definingPolygonAttributes : Ljavax/media/j3d/PolygonAttributesRetained;
/*      */     //   102: getfield polygonMode : I
/*      */     //   105: ifeq -> 115
/*      */     //   108: aload_1
/*      */     //   109: getfield texLinearMode : Z
/*      */     //   112: ifeq -> 118
/*      */     //   115: iconst_0
/*      */     //   116: istore #5
/*      */     //   118: aload_3
/*      */     //   119: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   122: getfield groupType : I
/*      */     //   125: getstatic javax/media/j3d/RenderAtom.SEPARATE_DLIST_PER_RINFO : I
/*      */     //   128: iand
/*      */     //   129: ifeq -> 206
/*      */     //   132: aload_3
/*      */     //   133: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   136: getfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   139: astore #6
/*      */     //   141: aload_3
/*      */     //   142: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   145: aconst_null
/*      */     //   146: putfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   149: iload #5
/*      */     //   151: ifeq -> 177
/*      */     //   154: aload_0
/*      */     //   155: getfield renderBin : Ljavax/media/j3d/RenderBin;
/*      */     //   158: getfield dlistRenderMethod : Ljavax/media/j3d/DisplayListRenderMethod;
/*      */     //   161: aload_0
/*      */     //   162: aload_1
/*      */     //   163: aload_3
/*      */     //   164: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   167: sipush #945
/*      */     //   170: invokevirtual renderSeparateDlistPerRinfo : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   173: pop
/*      */     //   174: goto -> 194
/*      */     //   177: aload_0
/*      */     //   178: getfield cachedVertexArrayRenderMethod : Ljavax/media/j3d/VertexArrayRenderMethod;
/*      */     //   181: aload_0
/*      */     //   182: aload_1
/*      */     //   183: aload_3
/*      */     //   184: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   187: sipush #945
/*      */     //   190: invokevirtual render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   193: pop
/*      */     //   194: aload_3
/*      */     //   195: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   198: aload #6
/*      */     //   200: putfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   203: goto -> 395
/*      */     //   206: aload_3
/*      */     //   207: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   210: getfield groupType : I
/*      */     //   213: getstatic javax/media/j3d/RenderAtom.VARRAY : I
/*      */     //   216: getstatic javax/media/j3d/RenderAtom.DLIST : I
/*      */     //   219: ior
/*      */     //   220: iand
/*      */     //   221: ifeq -> 270
/*      */     //   224: aload_3
/*      */     //   225: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   228: getfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   231: astore #6
/*      */     //   233: aload_3
/*      */     //   234: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   237: aconst_null
/*      */     //   238: putfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   241: aload_0
/*      */     //   242: getfield cachedVertexArrayRenderMethod : Ljavax/media/j3d/VertexArrayRenderMethod;
/*      */     //   245: aload_0
/*      */     //   246: aload_1
/*      */     //   247: aload_3
/*      */     //   248: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   251: sipush #945
/*      */     //   254: invokevirtual render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   257: pop
/*      */     //   258: aload_3
/*      */     //   259: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   262: aload #6
/*      */     //   264: putfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   267: goto -> 395
/*      */     //   270: aload_3
/*      */     //   271: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   274: getfield groupType : I
/*      */     //   277: getstatic javax/media/j3d/RenderAtom.SEPARATE_DLIST_PER_GEO : I
/*      */     //   280: iand
/*      */     //   281: ifeq -> 358
/*      */     //   284: aload_3
/*      */     //   285: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   288: getfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   291: astore #6
/*      */     //   293: aload_3
/*      */     //   294: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   297: aconst_null
/*      */     //   298: putfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   301: iload #5
/*      */     //   303: ifeq -> 329
/*      */     //   306: aload_0
/*      */     //   307: getfield renderBin : Ljavax/media/j3d/RenderBin;
/*      */     //   310: getfield dlistRenderMethod : Ljavax/media/j3d/DisplayListRenderMethod;
/*      */     //   313: aload_0
/*      */     //   314: aload_1
/*      */     //   315: aload_3
/*      */     //   316: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   319: sipush #945
/*      */     //   322: invokevirtual renderSeparateDlists : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   325: pop
/*      */     //   326: goto -> 346
/*      */     //   329: aload_0
/*      */     //   330: getfield cachedVertexArrayRenderMethod : Ljavax/media/j3d/VertexArrayRenderMethod;
/*      */     //   333: aload_0
/*      */     //   334: aload_1
/*      */     //   335: aload_3
/*      */     //   336: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   339: sipush #945
/*      */     //   342: invokevirtual render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   345: pop
/*      */     //   346: aload_3
/*      */     //   347: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   350: aload #6
/*      */     //   352: putfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   355: goto -> 395
/*      */     //   358: aload_3
/*      */     //   359: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   362: getfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   365: astore #6
/*      */     //   367: aload_0
/*      */     //   368: getfield primaryRenderMethod : Ljavax/media/j3d/RenderMethod;
/*      */     //   371: aload_0
/*      */     //   372: aload_1
/*      */     //   373: aload_0
/*      */     //   374: getfield primaryRenderAtomList : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   377: sipush #945
/*      */     //   380: invokeinterface render : (Ljavax/media/j3d/RenderMolecule;Ljavax/media/j3d/Canvas3D;Ljavax/media/j3d/RenderAtomListInfo;I)Z
/*      */     //   385: pop
/*      */     //   386: aload_3
/*      */     //   387: getfield rInfo : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   390: aload #6
/*      */     //   392: putfield next : Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   395: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #2096	-> 0
/*      */     //   #2098	-> 18
/*      */     //   #2102	-> 31
/*      */     //   #2105	-> 37
/*      */     //   #2119	-> 40
/*      */     //   #2131	-> 115
/*      */     //   #2138	-> 118
/*      */     //   #2139	-> 132
/*      */     //   #2141	-> 141
/*      */     //   #2144	-> 149
/*      */     //   #2145	-> 154
/*      */     //   #2150	-> 177
/*      */     //   #2152	-> 194
/*      */     //   #2153	-> 203
/*      */     //   #2154	-> 206
/*      */     //   #2155	-> 224
/*      */     //   #2157	-> 233
/*      */     //   #2160	-> 241
/*      */     //   #2162	-> 258
/*      */     //   #2163	-> 267
/*      */     //   #2166	-> 270
/*      */     //   #2167	-> 284
/*      */     //   #2168	-> 293
/*      */     //   #2169	-> 301
/*      */     //   #2170	-> 306
/*      */     //   #2175	-> 329
/*      */     //   #2178	-> 346
/*      */     //   #2179	-> 355
/*      */     //   #2181	-> 358
/*      */     //   #2182	-> 367
/*      */     //   #2184	-> 386
/*      */     //   #2187	-> 395 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTransparencyAttributes(Canvas3D paramCanvas3D) {
/* 2196 */     if (this.definingTransparency == null) {
/* 2197 */       paramCanvas3D.resetTransparency(paramCanvas3D.ctx, this.geometryType, this.polygonMode, this.lineAA, this.pointAA);
/*      */     } else {
/*      */       
/* 2200 */       this.definingTransparency.updateNative(paramCanvas3D.ctx, this.alpha, this.geometryType, this.polygonMode, this.lineAA, this.pointAA);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateDisplayList(Canvas3D paramCanvas3D) {
/* 2207 */     if (this.primaryRenderAtomList != null) {
/* 2208 */       ((DisplayListRenderMethod)this.primaryRenderMethod).buildDisplayList(this, paramCanvas3D);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void releaseAllPrimaryDisplayListID() {
/* 2214 */     if (this.primaryRenderAtomList != null) {
/* 2215 */       if (this.primaryMoleculeType == SEPARATE_DLIST_PER_RINFO_MOLECULE) {
/* 2216 */         RenderAtomListInfo renderAtomListInfo = this.primaryRenderAtomList;
/*      */ 
/*      */         
/* 2219 */         while (renderAtomListInfo != null) {
/* 2220 */           int i = renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index];
/*      */           
/* 2222 */           if (i > 0) {
/* 2223 */             VirtualUniverse.mc.freeDisplayListId(new Integer(i));
/* 2224 */             renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] = -1;
/*      */           } 
/* 2226 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         }
/*      */       
/* 2229 */       } else if (this.primaryMoleculeType == DLIST_MOLECULE && 
/* 2230 */         this.displayListIdObj != null) {
/* 2231 */         VirtualUniverse.mc.freeDisplayListId(this.displayListIdObj);
/* 2232 */         this.displayListIdObj = null;
/* 2233 */         this.displayListId = -1;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void releaseAllPrimaryDisplayListResources(Canvas3D paramCanvas3D, Context paramContext) {
/* 2241 */     if (this.primaryRenderAtomList != null) {
/* 2242 */       if (this.primaryMoleculeType == SEPARATE_DLIST_PER_RINFO_MOLECULE) {
/* 2243 */         RenderAtomListInfo renderAtomListInfo = this.primaryRenderAtomList;
/*      */         
/* 2245 */         while (renderAtomListInfo != null) {
/* 2246 */           int i = renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index];
/* 2247 */           if (i > 0) {
/* 2248 */             paramCanvas3D.freeDisplayList(paramContext, i);
/*      */           }
/* 2250 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         }
/*      */       
/* 2253 */       } else if (this.primaryMoleculeType == DLIST_MOLECULE && 
/* 2254 */         this.displayListId > 0) {
/* 2255 */         paramCanvas3D.freeDisplayList(paramContext, this.displayListId);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateAllPrimaryDisplayLists(Canvas3D paramCanvas3D) {
/* 2263 */     if (this.primaryRenderAtomList != null) {
/* 2264 */       if (this.primaryMoleculeType == SEPARATE_DLIST_PER_RINFO_MOLECULE) {
/* 2265 */         RenderAtomListInfo renderAtomListInfo = this.primaryRenderAtomList;
/* 2266 */         while (renderAtomListInfo != null) {
/* 2267 */           this.renderBin.dlistRenderMethod.buildDlistPerRinfo(renderAtomListInfo, this, paramCanvas3D);
/* 2268 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         }
/*      */       
/* 2271 */       } else if (this.primaryMoleculeType == DLIST_MOLECULE) {
/* 2272 */         ((DisplayListRenderMethod)this.primaryRenderMethod).buildDisplayList(this, paramCanvas3D);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   void checkEquivalenceWithBothNeighbors(int paramInt) {
/* 2278 */     RenderMolecule renderMolecule1 = this.prev;
/* 2279 */     RenderMolecule renderMolecule2 = this.next;
/* 2280 */     this.dirtyAttrsAcrossRms = 945;
/* 2281 */     boolean bool = true;
/*      */     
/* 2283 */     if (this.prev != null) {
/* 2284 */       checkEquivalenceWithLeftNeighbor(this.prev, paramInt);
/*      */     }
/* 2286 */     if (this.next != null) {
/* 2287 */       this.next.checkEquivalenceWithLeftNeighbor(this, paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */   boolean reloadColor(RenderMolecule paramRenderMolecule) {
/* 2292 */     if ((paramRenderMolecule.vertexFormat & 0x4) == 0 || ((paramRenderMolecule.vertexFormat & 0x4) != 0 && (this.vertexFormat & 0x4) != 0))
/*      */     {
/*      */       
/* 2295 */       return false;
/*      */     }
/* 2297 */     return true;
/*      */   }
/*      */   
/*      */   void checkEquivalenceWithLeftNeighbor(RenderMolecule paramRenderMolecule, int paramInt) {
/* 2301 */     boolean bool = reloadColor(paramRenderMolecule);
/*      */     
/* 2303 */     this.dirtyAttrsAcrossRms = 945;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2312 */     int i = 49;
/*      */ 
/*      */ 
/*      */     
/* 2316 */     int j = 928;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2321 */     if ((this.dirtyAttrsAcrossRms & 0x80) != 0 && 
/* 2322 */       paramRenderMolecule.geometryType == this.geometryType && (paramRenderMolecule.polygonAttributes == this.polygonAttributes || (paramRenderMolecule.definingPolygonAttributes != null && paramRenderMolecule.definingPolygonAttributes.equivalent(this.definingPolygonAttributes))))
/*      */     {
/*      */ 
/*      */       
/* 2326 */       this.dirtyAttrsAcrossRms &= 0xFFFFFF7F;
/*      */     }
/*      */ 
/*      */     
/* 2330 */     if ((this.dirtyAttrsAcrossRms & 0x200) != 0 && 
/* 2331 */       paramRenderMolecule.geometryType == this.geometryType && (paramRenderMolecule.pointAttributes == this.pointAttributes || (paramRenderMolecule.definingPointAttributes != null && paramRenderMolecule.definingPointAttributes.equivalent(this.definingPointAttributes))))
/*      */     {
/*      */ 
/*      */       
/* 2335 */       this.dirtyAttrsAcrossRms &= 0xFFFFFDFF;
/*      */     }
/*      */ 
/*      */     
/* 2339 */     if ((this.dirtyAttrsAcrossRms & 0x100) != 0 && 
/* 2340 */       paramRenderMolecule.geometryType == this.geometryType && (paramRenderMolecule.lineAttributes == this.lineAttributes || (paramRenderMolecule.definingLineAttributes != null && paramRenderMolecule.definingLineAttributes.equivalent(this.definingLineAttributes))))
/*      */     {
/*      */ 
/*      */       
/* 2344 */       this.dirtyAttrsAcrossRms &= 0xFFFFFEFF;
/*      */     }
/*      */     
/* 2347 */     if ((this.dirtyAttrsAcrossRms & i) != 0) {
/* 2348 */       if (materialEquivalent(paramRenderMolecule, bool)) {
/* 2349 */         this.dirtyAttrsAcrossRms &= 0xFFFFFFFE;
/*      */       } else {
/*      */         
/* 2352 */         this.dirtyAttrsAcrossRms |= 0x1;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2359 */     if ((this.dirtyAttrsAcrossRms & i) != 0) {
/* 2360 */       if (coloringEquivalent(paramRenderMolecule, bool)) {
/* 2361 */         this.dirtyAttrsAcrossRms &= 0xFFFFFFEF;
/*      */       } else {
/*      */         
/* 2364 */         this.dirtyAttrsAcrossRms |= 0x10;
/*      */       } 
/*      */     }
/*      */     
/* 2368 */     if ((this.dirtyAttrsAcrossRms & j) != 0) {
/* 2369 */       if (transparencyEquivalent(paramRenderMolecule)) {
/* 2370 */         this.dirtyAttrsAcrossRms &= 0xFFFFFFDF;
/*      */       } else {
/*      */         
/* 2373 */         this.dirtyAttrsAcrossRms |= 0x20;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   void translate() {
/* 2379 */     int i = this.localToVworldIndex[0];
/*      */     
/* 2381 */     (this.localeLocalToVworld[i]).mat[0] = (this.localToVworld[i]).mat[0];
/* 2382 */     (this.localeLocalToVworld[i]).mat[1] = (this.localToVworld[i]).mat[1];
/* 2383 */     (this.localeLocalToVworld[i]).mat[2] = (this.localToVworld[i]).mat[2];
/* 2384 */     (this.localeLocalToVworld[i]).mat[3] = (this.localToVworld[i]).mat[3] + this.localeTranslation.x;
/* 2385 */     (this.localeLocalToVworld[i]).mat[4] = (this.localToVworld[i]).mat[4];
/* 2386 */     (this.localeLocalToVworld[i]).mat[5] = (this.localToVworld[i]).mat[5];
/* 2387 */     (this.localeLocalToVworld[i]).mat[6] = (this.localToVworld[i]).mat[6];
/* 2388 */     (this.localeLocalToVworld[i]).mat[7] = (this.localToVworld[i]).mat[7] + this.localeTranslation.y;
/* 2389 */     (this.localeLocalToVworld[i]).mat[8] = (this.localToVworld[i]).mat[8];
/* 2390 */     (this.localeLocalToVworld[i]).mat[9] = (this.localToVworld[i]).mat[9];
/* 2391 */     (this.localeLocalToVworld[i]).mat[10] = (this.localToVworld[i]).mat[10];
/* 2392 */     (this.localeLocalToVworld[i]).mat[11] = (this.localToVworld[i]).mat[11] + this.localeTranslation.z;
/* 2393 */     (this.localeLocalToVworld[i]).mat[12] = (this.localToVworld[i]).mat[12];
/* 2394 */     (this.localeLocalToVworld[i]).mat[13] = (this.localToVworld[i]).mat[13];
/* 2395 */     (this.localeLocalToVworld[i]).mat[14] = (this.localToVworld[i]).mat[14];
/* 2396 */     (this.localeLocalToVworld[i]).mat[15] = (this.localToVworld[i]).mat[15];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isOpaque() {
/* 2402 */     if (!VirtualUniverse.mc.isD3D()) {
/*      */       
/* 2404 */       if ((this.geometryType & 0x4) != 0) {
/* 2405 */         if (this.definingPolygonAttributes != null) {
/* 2406 */           if (this.definingPolygonAttributes.polygonMode == 0 && this.definingPointAttributes != null && this.definingPointAttributes.pointAntialiasing)
/*      */           {
/*      */ 
/*      */             
/* 2410 */             return false; } 
/* 2411 */           if (this.definingPolygonAttributes.polygonMode == 1 && this.definingLineAttributes != null && this.definingLineAttributes.lineAntialiasing)
/*      */           {
/*      */ 
/*      */             
/* 2415 */             return false;
/*      */           }
/*      */         } 
/* 2418 */       } else if ((this.geometryType & true) != 0) {
/* 2419 */         if (this.definingPointAttributes != null && this.definingPointAttributes.pointAntialiasing)
/*      */         {
/* 2421 */           return false;
/*      */         }
/* 2423 */       } else if ((this.geometryType & 0x2) != 0 && 
/* 2424 */         this.definingLineAttributes != null && this.definingLineAttributes.lineAntialiasing) {
/*      */         
/* 2426 */         return false;
/*      */       } 
/*      */       
/* 2429 */       return (this.definingTransparency == null || this.definingTransparency.transparencyMode == 4 || this.definingTransparency.transparencyMode == 3);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2435 */     return (this.definingTransparency == null || this.definingTransparency.transparencyMode == 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean updateNodeComponent() {
/* 2444 */     if ((this.soleUserCompDirty & true) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2452 */       if (this.soleUser) {
/* 2453 */         boolean bool1 = (this.definingMaterial != null && this.definingMaterial != this.material) ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2459 */         this.material = ((AppearanceRetained)this.appHandle).material;
/* 2460 */         if (this.material == null) {
/* 2461 */           this.definingMaterial = null;
/*      */         }
/* 2463 */         else if (this.material.changedFrequent != 0) {
/* 2464 */           this.definingMaterial = this.material;
/*      */ 
/*      */         
/*      */         }
/* 2468 */         else if (bool1) {
/* 2469 */           this.definingMaterial.set(this.material);
/*      */         } else {
/*      */           
/* 2472 */           this.definingMaterial = (MaterialRetained)this.material.clone();
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2477 */       evalMaterialCachedState();
/*      */     } 
/* 2479 */     if ((this.soleUserCompDirty & 0x100) != 0) {
/* 2480 */       if (this.soleUser) {
/*      */         
/* 2482 */         boolean bool1 = (this.definingLineAttributes != null && this.definingLineAttributes != this.lineAttributes) ? 1 : 0;
/*      */         
/* 2484 */         this.lineAttributes = ((AppearanceRetained)this.appHandle).lineAttributes;
/* 2485 */         if (this.lineAttributes == null) {
/* 2486 */           this.lineAA = false;
/* 2487 */           this.definingLineAttributes = null;
/*      */         } else {
/* 2489 */           if (this.lineAttributes.changedFrequent != 0) {
/* 2490 */             this.definingLineAttributes = this.lineAttributes;
/*      */ 
/*      */           
/*      */           }
/* 2494 */           else if (bool1) {
/* 2495 */             this.definingLineAttributes.set(this.lineAttributes);
/*      */           } else {
/*      */             
/* 2498 */             this.definingLineAttributes = (LineAttributesRetained)this.lineAttributes.clone();
/*      */           } 
/*      */           
/* 2501 */           this.lineAA = this.definingLineAttributes.lineAntialiasing;
/*      */         } 
/*      */       } else {
/*      */         
/* 2505 */         this.lineAA = this.definingLineAttributes.lineAntialiasing;
/*      */       } 
/*      */     }
/* 2508 */     if ((this.soleUserCompDirty & 0x200) != 0) {
/* 2509 */       if (this.soleUser) {
/*      */         
/* 2511 */         boolean bool1 = (this.definingPointAttributes != null && this.definingPointAttributes != this.pointAttributes) ? 1 : 0;
/*      */         
/* 2513 */         this.pointAttributes = ((AppearanceRetained)this.appHandle).pointAttributes;
/* 2514 */         if (this.pointAttributes == null) {
/* 2515 */           this.pointAA = false;
/* 2516 */           this.definingPointAttributes = null;
/*      */         } else {
/* 2518 */           if (this.pointAttributes.changedFrequent != 0) {
/* 2519 */             this.definingPointAttributes = this.pointAttributes;
/*      */ 
/*      */           
/*      */           }
/* 2523 */           else if (bool1) {
/* 2524 */             this.definingPointAttributes.set(this.pointAttributes);
/*      */           } else {
/*      */             
/* 2527 */             this.definingPointAttributes = (PointAttributesRetained)this.pointAttributes.clone();
/*      */           } 
/*      */           
/* 2530 */           this.pointAA = this.definingPointAttributes.pointAntialiasing;
/*      */         } 
/*      */       } else {
/*      */         
/* 2534 */         this.pointAA = this.definingPointAttributes.pointAntialiasing;
/*      */       } 
/*      */     }
/*      */     
/* 2538 */     if ((this.soleUserCompDirty & 0x80) != 0) {
/* 2539 */       if (this.soleUser) {
/*      */         
/* 2541 */         boolean bool1 = (this.definingPolygonAttributes != null && this.definingPolygonAttributes != this.polygonAttributes) ? 1 : 0;
/*      */ 
/*      */         
/* 2544 */         this.polygonAttributes = ((AppearanceRetained)this.appHandle).polygonAttributes;
/*      */         
/* 2546 */         if (this.polygonAttributes == null) {
/* 2547 */           this.polygonMode = 2;
/* 2548 */           this.definingPolygonAttributes = null;
/*      */         } else {
/* 2550 */           if (this.polygonAttributes.changedFrequent != 0) {
/* 2551 */             this.definingPolygonAttributes = this.polygonAttributes;
/*      */ 
/*      */           
/*      */           }
/* 2555 */           else if (bool1) {
/* 2556 */             this.definingPolygonAttributes.set(this.polygonAttributes);
/*      */           } else {
/*      */             
/* 2559 */             this.definingPolygonAttributes = (PolygonAttributesRetained)this.polygonAttributes.clone();
/*      */           } 
/*      */ 
/*      */           
/* 2563 */           this.polygonMode = this.definingPolygonAttributes.polygonMode;
/*      */         } 
/*      */       } else {
/*      */         
/* 2567 */         this.polygonMode = this.definingPolygonAttributes.polygonMode;
/*      */       } 
/*      */       
/* 2570 */       if (this.polygonMode == 1) {
/* 2571 */         this.geometryType |= 0x2;
/* 2572 */       } else if (this.polygonMode == 0) {
/* 2573 */         this.geometryType |= 0x1;
/*      */       } 
/*      */     } 
/*      */     
/* 2577 */     if ((this.soleUserCompDirty & 0x20) != 0) {
/* 2578 */       if (this.soleUser) {
/*      */         
/* 2580 */         boolean bool1 = (this.definingTransparency != null && this.definingTransparency != this.transparency) ? 1 : 0;
/* 2581 */         this.transparency = ((AppearanceRetained)this.appHandle).transparencyAttributes;
/*      */         
/* 2583 */         if (this.transparency == null) {
/* 2584 */           this.alpha = 1.0F;
/* 2585 */           this.definingTransparency = null;
/*      */         } else {
/* 2587 */           if (this.transparency.changedFrequent != 0) {
/* 2588 */             this.definingTransparency = this.transparency;
/*      */ 
/*      */           
/*      */           }
/* 2592 */           else if (bool1) {
/* 2593 */             this.definingTransparency.set(this.transparency);
/*      */           } else {
/*      */             
/* 2596 */             this.definingTransparency = (TransparencyAttributesRetained)this.transparency.clone();
/*      */           } 
/*      */ 
/*      */           
/* 2600 */           this.alpha = 1.0F - this.definingTransparency.transparency;
/*      */         } 
/*      */       } else {
/*      */         
/* 2604 */         this.alpha = 1.0F - this.definingTransparency.transparency;
/*      */       } 
/*      */     }
/*      */     
/* 2608 */     if ((this.soleUserCompDirty & 0x10) != 0) {
/* 2609 */       if (this.soleUser) {
/*      */         
/* 2611 */         boolean bool1 = (this.definingColoringAttributes != null && this.definingColoringAttributes != this.coloringAttributes) ? 1 : 0;
/*      */         
/* 2613 */         this.coloringAttributes = ((AppearanceRetained)this.appHandle).coloringAttributes;
/*      */ 
/*      */         
/* 2616 */         if (this.coloringAttributes == null) {
/* 2617 */           this.definingColoringAttributes = null;
/* 2618 */           this.red = 1.0F;
/* 2619 */           this.green = 1.0F;
/* 2620 */           this.blue = 1.0F;
/*      */         } else {
/*      */           
/* 2623 */           if (this.coloringAttributes.changedFrequent != 0) {
/* 2624 */             this.definingColoringAttributes = this.coloringAttributes;
/*      */ 
/*      */           
/*      */           }
/* 2628 */           else if (bool1) {
/* 2629 */             this.definingColoringAttributes.set(this.coloringAttributes);
/*      */           } else {
/*      */             
/* 2632 */             this.definingColoringAttributes = (ColoringAttributesRetained)this.coloringAttributes.clone();
/*      */           } 
/*      */           
/* 2635 */           this.red = this.definingColoringAttributes.color.x;
/* 2636 */           this.green = this.definingColoringAttributes.color.y;
/* 2637 */           this.blue = this.definingColoringAttributes.color.z;
/*      */         } 
/*      */       } else {
/*      */         
/* 2641 */         this.red = this.definingColoringAttributes.color.x;
/* 2642 */         this.green = this.definingColoringAttributes.color.y;
/* 2643 */         this.blue = this.definingColoringAttributes.color.z;
/*      */       } 
/*      */     }
/*      */     
/* 2647 */     boolean bool = (isOpaque() || this.inOrderedGroup) ? 1 : 0;
/* 2648 */     return (this.isOpaqueOrInOG != bool);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addRemoveTransparentObject(RenderBin paramRenderBin, boolean paramBoolean) {
/* 2657 */     addRemoveTransparentObject(paramRenderBin, paramBoolean, this.primaryRenderAtomList);
/* 2658 */     addRemoveTransparentObject(paramRenderBin, paramBoolean, this.separateDlistRenderAtomList);
/* 2659 */     addRemoveTransparentObject(paramRenderBin, paramBoolean, this.vertexArrayRenderAtomList);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addRemoveTransparentObject(RenderBin paramRenderBin, boolean paramBoolean, RenderAtomListInfo paramRenderAtomListInfo) {
/* 2665 */     while (paramRenderAtomListInfo != null) {
/* 2666 */       if (paramBoolean) {
/* 2667 */         paramRenderBin.addTransparentObject(paramRenderAtomListInfo.renderAtom);
/*      */       } else {
/*      */         
/* 2670 */         paramRenderBin.removeTransparentObject(paramRenderAtomListInfo.renderAtom);
/*      */       } 
/* 2672 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*      */     } 
/*      */   }
/*      */   
/*      */   void evalMaterialCachedState() {
/* 2677 */     if (this.definingMaterial == null) {
/* 2678 */       this.enableLighting = false;
/* 2679 */       this.definingMaterial = null;
/* 2680 */       this.dRed = 1.0F;
/* 2681 */       this.dGreen = 1.0F;
/* 2682 */       this.dBlue = 1.0F;
/*      */     
/*      */     }
/* 2685 */     else if ((this.geometryType & 0x8) != 0) {
/* 2686 */       this.enableLighting = false;
/* 2687 */       this.dRed = 1.0F;
/* 2688 */       this.dGreen = 1.0F;
/* 2689 */       this.dBlue = 1.0F;
/*      */     } else {
/* 2691 */       if (this.normalPresent) {
/* 2692 */         this.enableLighting = this.definingMaterial.lightingEnable;
/*      */       } else {
/* 2694 */         this.enableLighting = false;
/* 2695 */       }  this.dRed = this.definingMaterial.diffuseColor.x;
/* 2696 */       this.dGreen = this.definingMaterial.diffuseColor.y;
/* 2697 */       this.dBlue = this.definingMaterial.diffuseColor.z;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void markBitsAsDirty(int paramInt1, int paramInt2) {
/* 2704 */     if (this.prev != null) {
/* 2705 */       checkEquivalenceWithLeftNeighbor(this.prev, paramInt1);
/* 2706 */       this.prev.soleUserCompDirty &= 0xFFFFFC4E;
/*      */     }
/* 2708 */     else if (this.prevMap != null) {
/* 2709 */       checkEquivalenceWithLeftNeighbor(this.prevMap, paramInt1);
/* 2710 */       this.prevMap.soleUserCompDirty &= 0xFFFFFC4E;
/*      */     } 
/* 2712 */     if (this.next != null) {
/* 2713 */       if ((this.next.soleUserCompDirty & 0x3B1) == 0) {
/* 2714 */         this.next.checkEquivalenceWithLeftNeighbor(this, paramInt2);
/*      */       } else {
/* 2716 */         this.next.soleUserCompDirty = paramInt2;
/*      */       }
/*      */     
/* 2719 */     } else if (this.nextMap != null) {
/* 2720 */       if ((this.nextMap.soleUserCompDirty & 0x3B1) == 0) {
/* 2721 */         this.nextMap.checkEquivalenceWithLeftNeighbor(this, paramInt2);
/*      */       } else {
/* 2723 */         this.nextMap.soleUserCompDirty = paramInt2;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void handleMaterialEquivalence() {
/* 2732 */     RenderMolecule renderMolecule1 = null;
/* 2733 */     RenderMolecule renderMolecule2 = null;
/* 2734 */     boolean bool = false;
/* 2735 */     int i = 945;
/* 2736 */     int j = 945;
/* 2737 */     if (this.prev != null) {
/* 2738 */       renderMolecule1 = this.prev.prev;
/* 2739 */       if (materialEquivalent(this.prev, reloadColor(this.prev))) {
/* 2740 */         bool = true;
/* 2741 */         i = (this.soleUserCompDirty | this.prev.soleUserCompDirty) & 0x3B1 & 0xFFFFFFFE;
/* 2742 */         j = this.soleUserCompDirty & 0x3B1;
/* 2743 */         markBitsAsDirty(i, j);
/*      */       }
/*      */     
/* 2746 */     } else if (!bool && this.next != null) {
/* 2747 */       renderMolecule2 = this.next.next;
/*      */       
/* 2749 */       if (materialEquivalent(this.next, reloadColor(this.next))) {
/* 2750 */         bool = true;
/* 2751 */         int k = 0;
/* 2752 */         if (this.prev != null) {
/* 2753 */           k = this.prev.soleUserCompDirty;
/* 2754 */         } else if (this.prevMap != null) {
/* 2755 */           k = this.prevMap.soleUserCompDirty;
/*      */         } 
/* 2757 */         i = (this.soleUserCompDirty | k) & 0x3B1;
/* 2758 */         j = this.soleUserCompDirty & 0x3B1 & 0xFFFFFFFE;
/* 2759 */         markBitsAsDirty(i, j);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2764 */     while (!bool && renderMolecule1 != null) {
/* 2765 */       if (materialEquivalent(renderMolecule1, reloadColor(renderMolecule1))) {
/* 2766 */         bool = true;
/*      */         
/* 2768 */         this.prev.next = this.next;
/* 2769 */         this.prev.nextMap = this.nextMap;
/* 2770 */         if (this.next != null) {
/* 2771 */           this.next.prev = this.prev;
/* 2772 */           if ((this.next.soleUserCompDirty & 0x3B1) == 0) {
/* 2773 */             this.next.checkEquivalenceWithLeftNeighbor(this.prev, 945);
/*      */           } else {
/*      */             
/* 2776 */             this.next.soleUserCompDirty = 945;
/*      */           }
/*      */         
/* 2779 */         } else if (this.nextMap != null) {
/* 2780 */           this.nextMap.prevMap = this.prev;
/* 2781 */           if ((this.nextMap.soleUserCompDirty & 0x3B1) == 0) {
/* 2782 */             this.nextMap.checkEquivalenceWithLeftNeighbor(this.prev, 945);
/*      */           } else {
/*      */             
/* 2785 */             this.nextMap.soleUserCompDirty |= 0x3B1;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 2790 */         this.next = renderMolecule1.next;
/* 2791 */         this.nextMap = renderMolecule1.nextMap;
/* 2792 */         renderMolecule1.nextMap = null;
/* 2793 */         if (this.next != null) {
/* 2794 */           this.next.prev = this;
/*      */         }
/* 2796 */         else if (this.nextMap != null) {
/* 2797 */           this.nextMap.prevMap = this;
/*      */         } 
/* 2799 */         this.prev = renderMolecule1;
/* 2800 */         renderMolecule1.next = this;
/* 2801 */         i = 944;
/* 2802 */         markBitsAsDirty(i, 945);
/*      */       } 
/* 2804 */       renderMolecule1 = renderMolecule1.prev;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2809 */     while (!bool && renderMolecule2 != null) {
/* 2810 */       if (materialEquivalent(renderMolecule2, reloadColor(renderMolecule2))) {
/* 2811 */         bool = true;
/*      */         
/* 2813 */         this.next.prev = this.prev;
/* 2814 */         this.next.prevMap = this.prevMap;
/* 2815 */         if (this.prev != null) {
/* 2816 */           this.prev.next = this.next;
/* 2817 */           if ((this.next.soleUserCompDirty & 0x3B1) == 0) {
/* 2818 */             this.next.checkEquivalenceWithLeftNeighbor(this.prev, 945);
/*      */           } else {
/*      */             
/* 2821 */             this.next.soleUserCompDirty = 945;
/*      */           }
/*      */         
/* 2824 */         } else if (this.prevMap != null) {
/* 2825 */           this.prevMap.nextMap = this.next;
/* 2826 */           if ((this.next.soleUserCompDirty & 0x3B1) == 0) {
/* 2827 */             this.next.checkEquivalenceWithLeftNeighbor(this.prevMap, 945);
/*      */           } else {
/*      */             
/* 2830 */             this.next.soleUserCompDirty = 945;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 2835 */         this.prev = renderMolecule2.prev;
/* 2836 */         this.prevMap = renderMolecule2.prevMap;
/* 2837 */         renderMolecule2.prevMap = null;
/* 2838 */         if (renderMolecule2.prev != null) {
/* 2839 */           renderMolecule2.prev.next = this;
/*      */         }
/* 2841 */         else if (this.prevMap != null) {
/* 2842 */           this.prevMap.nextMap = this;
/*      */         } 
/* 2844 */         this.next = renderMolecule2;
/* 2845 */         renderMolecule2.prev = this;
/* 2846 */         j = 944;
/* 2847 */         markBitsAsDirty(945, j);
/*      */       } 
/* 2849 */       renderMolecule2 = renderMolecule2.next;
/*      */     } 
/*      */     
/* 2852 */     if (!bool) {
/* 2853 */       if (this.prev != null) {
/* 2854 */         i = (this.soleUserCompDirty | this.prev.soleUserCompDirty) & 0x3B1;
/*      */       }
/* 2856 */       else if (this.prevMap != null) {
/* 2857 */         i = (this.soleUserCompDirty | this.prevMap.soleUserCompDirty) & 0x3B1;
/*      */       } 
/* 2859 */       if (this.next != null) {
/* 2860 */         j = (this.soleUserCompDirty | this.next.soleUserCompDirty) & 0x3B1;
/*      */       }
/* 2862 */       else if (this.nextMap != null) {
/* 2863 */         j = (this.soleUserCompDirty | this.nextMap.soleUserCompDirty) & 0x3B1;
/*      */       } 
/* 2865 */       markBitsAsDirty(i, j);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reEvaluateEquivalence() {
/* 2876 */     if ((this.soleUserCompDirty & 0x3B1) != 0) {
/* 2877 */       if ((this.soleUserCompDirty & true) != 0) {
/* 2878 */         handleMaterialEquivalence();
/*      */       } else {
/*      */         
/* 2881 */         int i = this.soleUserCompDirty & 0x3B1;
/* 2882 */         if (this.prev != null) {
/* 2883 */           checkEquivalenceWithLeftNeighbor(this.prev, (i | this.prev.soleUserCompDirty) & 0x3B1);
/* 2884 */           this.prev.soleUserCompDirty = 0;
/* 2885 */         } else if (this.prevMap != null) {
/* 2886 */           checkEquivalenceWithLeftNeighbor(this.prevMap, (i | this.prevMap.soleUserCompDirty) & 0x3B1);
/* 2887 */           this.prevMap.soleUserCompDirty = 0;
/*      */         } 
/* 2889 */         if (this.next != null) {
/* 2890 */           this.next.checkEquivalenceWithLeftNeighbor(this, (this.next.soleUserCompDirty | this.soleUserCompDirty) & 0x3B1);
/* 2891 */         } else if (this.nextMap != null) {
/* 2892 */           this.nextMap.checkEquivalenceWithLeftNeighbor(this, (this.nextMap.soleUserCompDirty | this.soleUserCompDirty) & 0x3B1);
/*      */         } 
/*      */       } 
/*      */     }
/* 2896 */     this.soleUserCompDirty &= 0xFFFFFC4E;
/*      */   }
/*      */ 
/*      */   
/*      */   boolean materialEquivalent(RenderMolecule paramRenderMolecule, boolean paramBoolean) {
/* 2901 */     if (!paramBoolean && (
/* 2902 */       this.material == paramRenderMolecule.material || (paramRenderMolecule.definingMaterial != null && paramRenderMolecule.definingMaterial.equivalent(this.definingMaterial))) && paramRenderMolecule.alpha == this.alpha && this.enableLighting == paramRenderMolecule.enableLighting && (this.enableLighting || (!this.enableLighting && paramRenderMolecule.red == this.red && paramRenderMolecule.green == this.green && paramRenderMolecule.blue == this.blue)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2912 */       return true;
/*      */     }
/*      */     
/* 2915 */     return false;
/*      */   }
/*      */   
/*      */   boolean coloringEquivalent(RenderMolecule paramRenderMolecule, boolean paramBoolean) {
/* 2919 */     if (!paramBoolean && (
/* 2920 */       paramRenderMolecule.coloringAttributes == this.coloringAttributes || (paramRenderMolecule.definingColoringAttributes != null && paramRenderMolecule.definingColoringAttributes.equivalent(this.definingColoringAttributes))) && (!this.enableLighting || (this.enableLighting && this.dRed == paramRenderMolecule.dRed && this.dBlue == paramRenderMolecule.dBlue && this.dGreen == paramRenderMolecule.dGreen)))
/*      */     {
/*      */ 
/*      */       
/* 2924 */       return true;
/*      */     }
/*      */     
/* 2927 */     return false;
/*      */   }
/*      */   
/*      */   boolean transparencyEquivalent(RenderMolecule paramRenderMolecule) {
/* 2931 */     if (paramRenderMolecule.transparency == this.transparency || (paramRenderMolecule.definingTransparency != null && paramRenderMolecule.definingTransparency.equivalent(this.definingTransparency) && paramRenderMolecule.definingTransparency.transparencyMode < 3 && blendOn() == paramRenderMolecule.blendOn()))
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 2936 */       return true;
/*      */     }
/* 2938 */     return false;
/*      */   }
/*      */   
/*      */   boolean blendOn() {
/* 2942 */     if (this.lineAA && ((this.geometryType & 0x2) != 0 || this.polygonMode == 1))
/*      */     {
/* 2944 */       return true;
/*      */     }
/* 2946 */     if (this.pointAA && ((this.geometryType & true) != 0 || this.polygonMode == 0))
/*      */     {
/* 2948 */       return true;
/*      */     }
/* 2950 */     return false;
/*      */   }
/*      */ 
/*      */   
/* 2954 */   VirtualUniverse getVirtualUniverse() { return null; }
/*      */ 
/*      */ 
/*      */   
/*      */   void handleLocaleChange() {
/* 2959 */     if (this.locale == this.renderBin.locale) {
/* 2960 */       if (this.localToVworld != this.localeLocalToVworld) {
/* 2961 */         this.localeLocalToVworld = this.localToVworld;
/* 2962 */         this.localeTranslation = null;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2967 */     else if (this.localeTranslation == null) {
/* 2968 */       this.localeLocalToVworld = new Transform3D[2];
/* 2969 */       this.localeLocalToVworld[0] = new Transform3D();
/* 2970 */       this.localeLocalToVworld[1] = new Transform3D();
/*      */       
/* 2972 */       this.localeTranslation = new Vector3d();
/* 2973 */       this.locale.hiRes.difference(this.renderBin.locale.hiRes, this.localeTranslation);
/* 2974 */       translate();
/* 2975 */       int i = this.localToVworldIndex[1];
/*      */       
/* 2977 */       (this.localeLocalToVworld[i]).mat[0] = (this.localToVworld[i]).mat[0];
/* 2978 */       (this.localeLocalToVworld[i]).mat[1] = (this.localToVworld[i]).mat[1];
/* 2979 */       (this.localeLocalToVworld[i]).mat[2] = (this.localToVworld[i]).mat[2];
/* 2980 */       (this.localeLocalToVworld[i]).mat[3] = (this.localToVworld[i]).mat[3] + this.localeTranslation.x;
/* 2981 */       (this.localeLocalToVworld[i]).mat[4] = (this.localToVworld[i]).mat[4];
/* 2982 */       (this.localeLocalToVworld[i]).mat[5] = (this.localToVworld[i]).mat[5];
/* 2983 */       (this.localeLocalToVworld[i]).mat[6] = (this.localToVworld[i]).mat[6];
/* 2984 */       (this.localeLocalToVworld[i]).mat[7] = (this.localToVworld[i]).mat[7] + this.localeTranslation.y;
/* 2985 */       (this.localeLocalToVworld[i]).mat[8] = (this.localToVworld[i]).mat[8];
/* 2986 */       (this.localeLocalToVworld[i]).mat[9] = (this.localToVworld[i]).mat[9];
/* 2987 */       (this.localeLocalToVworld[i]).mat[10] = (this.localToVworld[i]).mat[10];
/* 2988 */       (this.localeLocalToVworld[i]).mat[11] = (this.localToVworld[i]).mat[11] + this.localeTranslation.z;
/* 2989 */       (this.localeLocalToVworld[i]).mat[12] = (this.localToVworld[i]).mat[12];
/* 2990 */       (this.localeLocalToVworld[i]).mat[13] = (this.localToVworld[i]).mat[13];
/* 2991 */       (this.localeLocalToVworld[i]).mat[14] = (this.localToVworld[i]).mat[14];
/* 2992 */       (this.localeLocalToVworld[i]).mat[15] = (this.localToVworld[i]).mat[15];
/*      */     } 
/*      */ 
/*      */     
/* 2996 */     this.trans = this.localeLocalToVworld;
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
/*      */   public void updateNodeComponentCheck() {
/* 3011 */     if ((this.onUpdateList & ON_UPDATE_CHECK_LIST) == 0) {
/*      */       return;
/*      */     }
/* 3014 */     this.onUpdateList &= (ON_UPDATE_CHECK_LIST ^ 0xFFFFFFFF);
/* 3015 */     NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)this.appHandle;
/* 3016 */     if ((nodeComponentRetained.compChanged & RM_COMPONENTS) != 0) {
/* 3017 */       if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 3018 */         this.renderBin.rmUpdateList.add(this);
/*      */       }
/* 3020 */       this.soleUserCompDirty |= nodeComponentRetained.compChanged & RM_COMPONENTS;
/*      */     } 
/* 3022 */     if (this.definingPolygonAttributes != null && this.definingPolygonAttributes == this.polygonAttributes)
/*      */     {
/* 3024 */       if (this.definingPolygonAttributes.compChanged != 0) {
/* 3025 */         if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 3026 */           this.renderBin.rmUpdateList.add(this);
/*      */         }
/* 3028 */         this.soleUserCompDirty |= 0x80;
/*      */       } 
/*      */     }
/* 3031 */     if (this.definingLineAttributes != null && this.definingLineAttributes == this.lineAttributes)
/*      */     {
/* 3033 */       if (this.definingLineAttributes.compChanged != 0) {
/* 3034 */         if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 3035 */           this.renderBin.rmUpdateList.add(this);
/*      */         }
/* 3037 */         this.soleUserCompDirty |= 0x100;
/*      */       } 
/*      */     }
/* 3040 */     if (this.definingPointAttributes != null && this.definingPointAttributes.compChanged != 0) {
/*      */       
/* 3042 */       if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 3043 */         this.renderBin.rmUpdateList.add(this);
/*      */       }
/* 3045 */       this.soleUserCompDirty |= 0x200;
/*      */     } 
/*      */     
/* 3048 */     if (this.definingMaterial != null && this.definingMaterial == this.material)
/*      */     {
/* 3050 */       if (this.definingMaterial.compChanged != 0) {
/* 3051 */         if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 3052 */           this.renderBin.rmUpdateList.add(this);
/*      */         }
/* 3054 */         this.soleUserCompDirty |= 0x1;
/*      */       } 
/*      */     }
/*      */     
/* 3058 */     if (this.definingColoringAttributes != null && this.definingColoringAttributes == this.coloringAttributes)
/*      */     {
/* 3060 */       if (this.definingColoringAttributes.compChanged != 0) {
/* 3061 */         if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 3062 */           this.renderBin.rmUpdateList.add(this);
/*      */         }
/* 3064 */         this.soleUserCompDirty |= 0x10;
/*      */       } 
/*      */     }
/*      */     
/* 3068 */     if (this.definingTransparency != null && this.definingTransparency == this.transparency)
/*      */     {
/* 3070 */       if (this.definingTransparency.compChanged != 0) {
/* 3071 */         if ((this.soleUserCompDirty & 0x3B1) == 0) {
/* 3072 */           this.renderBin.rmUpdateList.add(this);
/*      */         }
/* 3074 */         this.soleUserCompDirty |= 0x20;
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\RenderMolecule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */