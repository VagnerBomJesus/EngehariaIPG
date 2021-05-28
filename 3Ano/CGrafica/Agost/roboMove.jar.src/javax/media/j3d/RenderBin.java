/*      */ package javax.media.j3d;
/*      */ 
/*      */ import com.sun.j3d.utils.scenegraph.transparency.TransparencySortController;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashSet;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Point3d;
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
/*      */ class RenderBin
/*      */   extends J3dStructure
/*      */   implements ObjectUpdate
/*      */ {
/*   28 */   ArrayList renderAtoms = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   33 */   ArrayList lightMessageList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   J3dMessage[] m;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   43 */   ArrayList rmUpdateList = new ArrayList();
/*   44 */   ArrayList aBinUpdateList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   50 */   ArrayList sBinUpdateList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   56 */   ArrayList tbUpdateList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   65 */   ArrayList updateCheckList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int maxLights;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   75 */   LightBin opaqueBin = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   LightBin addOpaqueBin = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   85 */   ArrayList allTransparentObjects = new ArrayList(5);
/*      */ 
/*      */ 
/*      */   
/*      */   TransparentRenderingInfo transparentInfo;
/*      */ 
/*      */ 
/*      */   
/*   93 */   ArrayList positionDirtyList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   98 */   Color3f white = new Color3f(1.0F, 1.0F, 1.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  103 */   Color3f black = new Color3f(0.0F, 0.0F, 0.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  108 */   BackgroundRetained background = new BackgroundRetained();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  114 */   Transform3D vworldToVpc = new Transform3D();
/*      */ 
/*      */   
/*  117 */   Transform3D vpcToVworld = new Transform3D();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  123 */   BoundingSphere vpSchedSphereInVworld = new BoundingSphere();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   BoundingBox viewFrustumBBox = new BoundingBox();
/*  129 */   BoundingBox canvasFrustumBBox = new BoundingBox();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean afterFirst = false;
/*      */ 
/*      */ 
/*      */   
/*      */   double backClipDistanceInVworld;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean backClipActive = false;
/*      */ 
/*      */ 
/*      */   
/*  146 */   int frameCount = 0;
/*  147 */   int frameCountCutoff = 150;
/*  148 */   int notVisibleCount = 75;
/*  149 */   long removeCutoffTime = -1L;
/*      */ 
/*      */   
/*      */   boolean transformMsg = false;
/*      */ 
/*      */   
/*  155 */   UpdateTargets targets = null;
/*  156 */   ArrayList blUsers = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  161 */   View view = null;
/*      */   
/*  163 */   private Comparator transparencySortComparator = null;
/*      */   
/*  165 */   private ArrayList toBeAddedTextureResourceFreeList = new ArrayList(5);
/*  166 */   private ArrayList displayListResourceFreeList = new ArrayList(5);
/*      */ 
/*      */   
/*  169 */   ArrayList orderedBins = new ArrayList(5);
/*      */ 
/*      */ 
/*      */   
/*  173 */   ArrayList changedLts = new ArrayList(5);
/*  174 */   ArrayList changedFogs = new ArrayList(5);
/*  175 */   ArrayList changedModelClips = new ArrayList(5);
/*      */ 
/*      */   
/*  178 */   static int REEVALUATE_LIGHTS = 1;
/*  179 */   static int REEVALUATE_FOG = 2;
/*  180 */   static int REEVALUATE_MCLIP = 4;
/*  181 */   static int REEVALUATE_ALL_ENV = REEVALUATE_LIGHTS | REEVALUATE_FOG | REEVALUATE_MCLIP;
/*  182 */   int envDirty = 0;
/*      */ 
/*      */   
/*      */   private boolean reEvaluateBg = true;
/*      */ 
/*      */   
/*      */   private boolean reloadBgTexture = true;
/*      */ 
/*      */   
/*      */   boolean reEvaluateClip = true;
/*      */ 
/*      */   
/*      */   boolean reEvaluateSortMode = false;
/*      */   
/*  196 */   IndexedUnorderSet renderMoleculeList = new IndexedUnorderSet(RenderMolecule.class, 1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  203 */   Collection sharedDList = new HashSet();
/*      */   
/*  205 */   ArrayList dirtyRenderMoleculeList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  211 */   ArrayList objUpdateList = new ArrayList(5);
/*      */   
/*  213 */   ArrayList raLocaleVwcBoundsUpdateList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  218 */   IndexedUnorderSet removeRenderAtomInRMList = new IndexedUnorderSet(RenderMolecule.class, 0, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  226 */   ArrayList ogCIOList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  231 */   ArrayList obList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  236 */   ArrayList orderedBinsList = new ArrayList(5);
/*  237 */   ArrayList toBeAddedBinList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  244 */   ArrayList lockGeometryList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  250 */   ArrayList dlistLockList = new ArrayList(5);
/*      */ 
/*      */   
/*  253 */   BackgroundRetained geometryBackground = null;
/*      */ 
/*      */   
/*  256 */   LightBin bgOpaqueBin = null;
/*  257 */   LightBin bgAddOpaqueBin = null;
/*  258 */   ArrayList bgOrderedBins = new ArrayList(5);
/*      */ 
/*      */   
/*      */   TransparentRenderingInfo bgTransparentInfo;
/*      */   
/*  263 */   Transform3D infVworldToVpc = new Transform3D();
/*      */ 
/*      */   
/*      */   boolean vpcToVworldDirty = true;
/*      */ 
/*      */   
/*  269 */   BackgroundRetained currentActiveBackground = new BackgroundRetained();
/*      */ 
/*      */ 
/*      */   
/*      */   boolean altAppearanceDirty = true;
/*      */ 
/*      */ 
/*      */   
/*  277 */   ArrayList nodeComponentList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  282 */   ArrayList newNodeComponentList = new ArrayList(5);
/*  283 */   ArrayList removeNodeComponentList = new ArrayList(5);
/*  284 */   ArrayList dirtyNodeComponentList = new ArrayList(5);
/*      */   
/*  286 */   ArrayList textureBinList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  292 */   ArrayList dirtyReferenceGeomList = new ArrayList(5);
/*      */ 
/*      */   
/*  295 */   ArrayList orientedRAs = new ArrayList(5);
/*      */ 
/*      */   
/*  298 */   ArrayList dirtyOrientedRAs = new ArrayList(5);
/*      */ 
/*      */   
/*  301 */   ArrayList cachedDirtyOrientedRAs = null;
/*      */ 
/*      */   
/*  304 */   ArrayList offScreenMessage = new ArrayList(5);
/*      */ 
/*      */   
/*  307 */   Vector3d localeTranslation = new Vector3d();
/*      */ 
/*      */   
/*  310 */   private HashSet addDlist = new HashSet();
/*  311 */   private HashSet removeDlist = new HashSet();
/*      */ 
/*      */   
/*  314 */   ArrayList addDlistPerRinfo = new ArrayList(5);
/*  315 */   ArrayList removeDlistPerRinfo = new ArrayList(5);
/*      */   
/*  317 */   Locale locale = null;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean localeChanged = false;
/*      */ 
/*      */   
/*  324 */   DisplayListRenderMethod dlistRenderMethod = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean reactivateView = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean visGAIsDirty = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean visQuery = false;
/*      */ 
/*      */ 
/*      */   
/*  341 */   ArrayList dirtyList = new ArrayList(5);
/*      */ 
/*      */   
/*  344 */   int transpSortMode = 0;
/*  345 */   int cachedTranspSortMode = 0;
/*      */ 
/*      */ 
/*      */   
/*  349 */   private LinkedHashSet dirtyDepthSortRenderAtom = new LinkedHashSet();
/*  350 */   private int numDirtyTinfo = 0;
/*      */ 
/*      */   
/*  353 */   Point3d eyeInVworld = new Point3d();
/*      */   
/*  355 */   int nElements = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderBin(VirtualUniverse paramVirtualUniverse, View paramView) {
/*  363 */     super(paramVirtualUniverse, 128);
/*  364 */     this.vworldToVpc.setIdentity();
/*  365 */     this.universe = paramVirtualUniverse;
/*  366 */     this.view = paramView;
/*  367 */     this.transpSortMode = paramView.transparencySortingPolicy;
/*  368 */     this.cachedTranspSortMode = paramView.transparencySortingPolicy;
/*  369 */     this.maxLights = VirtualUniverse.mc.maxLights;
/*  370 */     ViewPlatform viewPlatform = this.view.getViewPlatform();
/*  371 */     if (viewPlatform != null) {
/*  372 */       this.locale = ((ViewPlatformRetained)viewPlatform.retained).locale;
/*      */     }
/*  374 */     this.dlistRenderMethod = (DisplayListRenderMethod)VirtualUniverse.mc.getDisplayListRenderMethod();
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
/*      */   public void updateObject() {
/*  404 */     int i = this.removeRenderAtomInRMList.size();
/*  405 */     if (i > 0) {
/*  406 */       RenderMolecule[] arrayOfRenderMolecule = (RenderMolecule[])this.removeRenderAtomInRMList.toArray(false);
/*      */       
/*  408 */       for (byte b = 0; b < i; b++) {
/*  409 */         arrayOfRenderMolecule[b].updateRemoveRenderAtoms();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  416 */     i = this.obList.size();
/*  417 */     if (i > 0) {
/*  418 */       for (byte b = 0; b < i; b++) {
/*  419 */         OrderedBin orderedBin = (OrderedBin)this.obList.get(b);
/*  420 */         orderedBin.addRemoveOrderedCollection();
/*      */       } 
/*      */     }
/*      */     
/*  424 */     i = this.ogCIOList.size();
/*  425 */     if (i > 0)
/*      */     {
/*  427 */       for (byte b = 0; b < i; b++) {
/*  428 */         OrderedGroupRetained orderedGroupRetained; J3dMessage j3dMessage = (J3dMessage)this.ogCIOList.get(b);
/*      */         
/*  430 */         switch (j3dMessage.type) {
/*      */           case 59:
/*  432 */             orderedGroupRetained = (OrderedGroupRetained)j3dMessage.args[3];
/*  433 */             if (orderedGroupRetained != null) {
/*  434 */               orderedGroupRetained.childIndexOrder = (int[])j3dMessage.args[4];
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 32:
/*      */           case 33:
/*  441 */             if (j3dMessage.args[3] != null) {
/*  442 */               Object[] arrayOfObject1 = (Object[])j3dMessage.args[3];
/*  443 */               Object[] arrayOfObject2 = (Object[])j3dMessage.args[4];
/*  444 */               for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*  445 */                 if (arrayOfObject1[b1] != null) {
/*  446 */                   ((OrderedGroupRetained)arrayOfObject1[b1]).childIndexOrder = (int[])arrayOfObject2[b1];
/*      */                 }
/*      */               } 
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  454 */         j3dMessage.decRefcount();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  459 */     if (this.addOpaqueBin != null)
/*      */     {
/*  461 */       if (this.opaqueBin != null) {
/*  462 */         LightBin lightBin = this.opaqueBin;
/*  463 */         while (lightBin.next != null) {
/*  464 */           lightBin = lightBin.next;
/*      */         }
/*  466 */         this.addOpaqueBin.prev = lightBin;
/*  467 */         lightBin.next = this.addOpaqueBin;
/*      */       } else {
/*      */         
/*  470 */         this.opaqueBin = this.addOpaqueBin;
/*      */       } 
/*      */     }
/*      */     
/*  474 */     if (this.bgAddOpaqueBin != null) {
/*  475 */       if (this.bgOpaqueBin != null) {
/*  476 */         LightBin lightBin = this.bgOpaqueBin;
/*  477 */         while (lightBin.next != null) {
/*  478 */           lightBin = lightBin.next;
/*      */         }
/*  480 */         this.bgAddOpaqueBin.prev = lightBin;
/*  481 */         lightBin.next = this.bgAddOpaqueBin;
/*      */       } else {
/*      */         
/*  484 */         this.bgOpaqueBin = this.bgAddOpaqueBin;
/*      */       } 
/*      */     }
/*      */     
/*  488 */     i = this.orderedBinsList.size();
/*  489 */     if (i > 0)
/*      */     {
/*  491 */       for (byte b = 0; b < i; b++) {
/*  492 */         ArrayList arrayList1 = (ArrayList)this.orderedBinsList.get(b);
/*  493 */         ArrayList arrayList2 = (ArrayList)this.toBeAddedBinList.get(b);
/*      */         
/*  495 */         int j = arrayList2.size();
/*  496 */         for (byte b1 = 0; b1 < j; b1++) {
/*  497 */           arrayList1.add(arrayList2.get(b1));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  503 */     i = this.raLocaleVwcBoundsUpdateList.size();
/*  504 */     if (i > 0)
/*      */     {
/*  506 */       for (byte b = 0; b < i; b++) {
/*  507 */         RenderAtom renderAtom = (RenderAtom)this.raLocaleVwcBoundsUpdateList.get(b);
/*  508 */         renderAtom.updateLocaleVwcBounds();
/*      */       } 
/*      */     }
/*      */     
/*  512 */     if ((i = this.aBinUpdateList.size()) > 0) {
/*  513 */       for (byte b = 0; b < i; b++) {
/*  514 */         AttributeBin attributeBin = (AttributeBin)this.aBinUpdateList.get(b);
/*  515 */         attributeBin.updateNodeComponent();
/*      */       } 
/*      */     }
/*      */     
/*  519 */     if ((i = this.sBinUpdateList.size()) > 0) {
/*  520 */       for (byte b = 0; b < i; b++) {
/*  521 */         ShaderBin shaderBin = (ShaderBin)this.sBinUpdateList.get(b);
/*  522 */         shaderBin.updateNodeComponent();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  527 */     if (this.tbUpdateList.size() > 0) {
/*      */       
/*  529 */       i = this.tbUpdateList.size(); byte b;
/*  530 */       for (b = 0; b < i; b++) {
/*  531 */         TextureBin textureBin = (TextureBin)this.tbUpdateList.get(b);
/*  532 */         textureBin.updateNodeComponent();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  537 */       for (b = 0; b < i; b++) {
/*  538 */         TextureBin textureBin = (TextureBin)this.tbUpdateList.get(b);
/*      */ 
/*      */         
/*  541 */         if ((textureBin.tbFlag & 0x10) != 0 && textureBin.shaderBin != null) {
/*      */ 
/*      */           
/*  544 */           textureBin.shaderBin.reInsertTextureBin(textureBin);
/*  545 */           textureBin.tbFlag &= 0xFFFFFFEF;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  554 */     if ((i = this.rmUpdateList.size()) > 0) {
/*  555 */       byte b; for (b = 0; b < i; b++) {
/*  556 */         RenderMolecule renderMolecule = (RenderMolecule)this.rmUpdateList.get(b);
/*      */         
/*  558 */         boolean bool1 = renderMolecule.updateNodeComponent();
/*      */ 
/*      */         
/*  561 */         if (bool1 && renderMolecule.textureBin != null) {
/*  562 */           renderMolecule.textureBin.changeLists(renderMolecule);
/*      */         }
/*      */       } 
/*  565 */       for (b = 0; b < i; b++) {
/*  566 */         RenderMolecule renderMolecule = (RenderMolecule)this.rmUpdateList.get(b);
/*  567 */         renderMolecule.reEvaluateEquivalence();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  573 */     i = this.objUpdateList.size();
/*  574 */     if (i > 0) {
/*  575 */       for (byte b = 0; b < i; b++) {
/*  576 */         ObjectUpdate objectUpdate = (ObjectUpdate)this.objUpdateList.get(b);
/*  577 */         objectUpdate.updateObject();
/*      */       } 
/*      */     }
/*      */     
/*  581 */     i = this.dirtyReferenceGeomList.size();
/*  582 */     if (i > 0) {
/*      */       
/*  584 */       Canvas3D[] arrayOfCanvas3D = this.view.getCanvases();
/*      */       
/*  586 */       for (byte b = 0; b < i; b++) {
/*  587 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.dirtyReferenceGeomList.get(b);
/*      */         
/*  589 */         geometryArrayRetained.geomLock.getLock();
/*  590 */         byte b1 = 0;
/*      */         
/*  592 */         boolean bool1 = false;
/*  593 */         while (b1 < arrayOfCanvas3D.length && !bool1) {
/*  594 */           if (((arrayOfCanvas3D[b1]).extensionsSupported & true) == 0) {
/*  595 */             if ((geometryArrayRetained.vertexFormat & 0x100) != 0) {
/*  596 */               geometryArrayRetained.setupMirrorInterleavedColorPointer(true);
/*  597 */               bool1 = true;
/*      */             } else {
/*      */               
/*  600 */               geometryArrayRetained.setupMirrorColorPointer(geometryArrayRetained.vertexType & 0x3F0, true);
/*  601 */               bool1 = true;
/*      */             } 
/*      */           }
/*  604 */           b1++;
/*      */         } 
/*  606 */         geometryArrayRetained.geomLock.unLock();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  612 */     if (this.reEvaluateBg) {
/*  613 */       setBackground(this.currentActiveBackground);
/*      */     }
/*      */     
/*  616 */     i = this.textureBinList.size();
/*      */     
/*  618 */     if (i > 0) {
/*  619 */       Canvas3D[][] arrayOfCanvas3D = this.view.getCanvasList(false);
/*      */       
/*  621 */       boolean bool1 = false;
/*      */ 
/*      */       
/*      */       byte b1;
/*      */       
/*  626 */       for (b1 = 0; b1 < arrayOfCanvas3D.length && !bool1; b1++) {
/*  627 */         Canvas3D canvas3D = arrayOfCanvas3D[b1][0];
/*  628 */         if (canvas3D.useSharedCtx) {
/*  629 */           bool1 = true;
/*      */         }
/*      */       } 
/*      */       
/*  633 */       for (byte b2 = 0; b2 < i; b2++) {
/*  634 */         boolean bool2 = false;
/*  635 */         TextureBin textureBin = (TextureBin)this.textureBinList.get(b2);
/*  636 */         textureBin.tbFlag |= 0x1;
/*      */         
/*  638 */         if (textureBin.texUnitState != null)
/*      */         {
/*      */           
/*  641 */           for (byte b = 0; b < textureBin.texUnitState.length; b++) {
/*  642 */             if (textureBin.texUnitState[b] != null && (textureBin.texUnitState[b]).texture != null) {
/*      */ 
/*      */               
/*  645 */               TextureRetained textureRetained = (textureBin.texUnitState[b]).texture;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  656 */               if (bool1) {
/*  657 */                 synchronized (textureRetained.resourceLock) {
/*  658 */                   for (b1 = 0; b1 < arrayOfCanvas3D.length; b1++) {
/*  659 */                     Canvas3D canvas3D = arrayOfCanvas3D[b1][0];
/*  660 */                     if (canvas3D.useSharedCtx && canvas3D.screen.renderer != null && (canvas3D.screen.renderer.rendererBit & (textureRetained.resourceCreationMask | textureRetained.resourceInReloadList)) == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                       
/*  666 */                       canvas3D.screen.renderer.textureReloadList.add(textureRetained);
/*      */ 
/*      */                       
/*  669 */                       textureRetained.resourceInReloadList |= canvas3D.screen.renderer.rendererBit;
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               }
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  680 */     i = this.newNodeComponentList.size();
/*  681 */     if (i > 0) {
/*      */       
/*  683 */       Canvas3D[] arrayOfCanvas3D = this.view.getCanvases();
/*  684 */       for (byte b = 0; b < i; b++) {
/*      */         
/*  686 */         ImageComponentRetained imageComponentRetained = (ImageComponentRetained)this.newNodeComponentList.get(b);
/*  687 */         if (imageComponentRetained.isByReference()) {
/*  688 */           imageComponentRetained.geomLock.getLock();
/*  689 */           for (byte b1 = 0; b1 < arrayOfCanvas3D.length; b1++) {
/*      */ 
/*      */ 
/*      */             
/*  693 */             if ((arrayOfCanvas3D[b1]).ctx != null) {
/*  694 */               imageComponentRetained.evaluateExtensions(arrayOfCanvas3D[b1]);
/*      */             }
/*      */           } 
/*  697 */           imageComponentRetained.geomLock.unLock();
/*      */         } else {
/*  699 */           for (byte b1 = 0; b1 < arrayOfCanvas3D.length; b1++) {
/*      */ 
/*      */ 
/*      */             
/*  703 */             if ((arrayOfCanvas3D[b1]).ctx != null) {
/*  704 */               imageComponentRetained.evaluateExtensions(arrayOfCanvas3D[b1]);
/*      */             }
/*      */           } 
/*      */         } 
/*  708 */         this.nodeComponentList.add(imageComponentRetained);
/*      */       } 
/*      */     } 
/*      */     
/*  712 */     i = this.removeNodeComponentList.size();
/*  713 */     if (i > 0) {
/*  714 */       for (byte b = 0; b < i; b++) {
/*  715 */         this.nodeComponentList.remove(this.removeNodeComponentList.get(b));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  721 */     i = this.dirtyNodeComponentList.size();
/*  722 */     if (i > 0) {
/*  723 */       Canvas3D[] arrayOfCanvas3D = this.view.getCanvases();
/*  724 */       for (byte b = 0; b < i; b++) {
/*      */         
/*  726 */         ImageComponentRetained imageComponentRetained = (ImageComponentRetained)this.dirtyNodeComponentList.get(b);
/*      */         
/*  728 */         if (imageComponentRetained.isByReference()) {
/*  729 */           imageComponentRetained.geomLock.getLock();
/*  730 */           for (byte b1 = 0; b1 < arrayOfCanvas3D.length; b1++) {
/*      */ 
/*      */ 
/*      */             
/*  734 */             if ((arrayOfCanvas3D[b1]).ctx != null) {
/*  735 */               imageComponentRetained.evaluateExtensions(arrayOfCanvas3D[b1]);
/*      */             }
/*      */           } 
/*  738 */           imageComponentRetained.geomLock.unLock();
/*      */         } else {
/*      */           
/*  741 */           for (byte b1 = 0; b1 < arrayOfCanvas3D.length; b1++) {
/*      */ 
/*      */ 
/*      */             
/*  745 */             if ((arrayOfCanvas3D[b1]).ctx != null) {
/*  746 */               imageComponentRetained.evaluateExtensions(arrayOfCanvas3D[b1]);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  754 */     if (this.reEvaluateClip) {
/*  755 */       double[] arrayOfDouble = null;
/*  756 */       if ((arrayOfDouble = this.universe.renderingEnvironmentStructure.backClipDistanceInVworld(this.vpSchedSphereInVworld, this.view)) != null) {
/*  757 */         this.backClipDistanceInVworld = arrayOfDouble[0];
/*  758 */         this.backClipActive = true;
/*      */       } else {
/*      */         
/*  761 */         this.backClipActive = false;
/*      */       } 
/*  763 */       this.view.vDirtyMask |= 0x20;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  770 */     i = this.offScreenMessage.size();
/*  771 */     if (i > 0)
/*      */     {
/*  773 */       for (int j = i - 1; j >= 0; j--) {
/*  774 */         J3dMessage j3dMessage = (J3dMessage)this.offScreenMessage.get(j);
/*  775 */         j3dMessage.threads = 16;
/*  776 */         ((Canvas3D)j3dMessage.args[0]).screen.renderer.rendererStructure.addMessage(j3dMessage);
/*      */ 
/*      */         
/*  779 */         j3dMessage.decRefcount();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  786 */     if (this.dirtyOrientedRAs.size() > 0)
/*      */     {
/*  788 */       this.cachedDirtyOrientedRAs = (ArrayList)this.dirtyOrientedRAs.clone();
/*      */     }
/*  790 */     boolean bool = false;
/*  791 */     if (this.reEvaluateSortMode && this.transpSortMode != this.cachedTranspSortMode) {
/*  792 */       convertTransparentRenderingStruct(this.transpSortMode, this.cachedTranspSortMode);
/*  793 */       this.transpSortMode = this.cachedTranspSortMode;
/*  794 */       if (this.transpSortMode == 1 && 
/*  795 */         this.transparentInfo != null) {
/*  796 */         bool = true;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  801 */     if (this.vpcToVworldDirty) {
/*  802 */       this.vworldToVpc.invert(this.vpcToVworld);
/*      */ 
/*      */       
/*  805 */       Canvas3D[] arrayOfCanvas3D = this.view.getCanvases();
/*  806 */       for (byte b = 0; b < arrayOfCanvas3D.length; b++) {
/*  807 */         (arrayOfCanvas3D[b]).lightBin = null;
/*      */       }
/*  809 */       if (arrayOfCanvas3D.length > 0) {
/*      */         
/*  811 */         arrayOfCanvas3D[0].getCenterEyeInImagePlate(this.eyeInVworld);
/*      */         
/*  813 */         Transform3D transform3D = (arrayOfCanvas3D[0]).canvasViewCache.getImagePlateToVworld();
/*  814 */         transform3D.transform(this.eyeInVworld);
/*      */       } 
/*  816 */       if (this.transpSortMode == 1 && this.transparentInfo != null)
/*      */       {
/*  818 */         bool = true;
/*      */       }
/*      */     } 
/*  821 */     i = this.dirtyDepthSortRenderAtom.size();
/*      */ 
/*      */     
/*  824 */     if (bool || i > 0) {
/*  825 */       int j = this.allTransparentObjects.size();
/*      */ 
/*      */       
/*  828 */       for (byte b = 0; b < j; b++) {
/*  829 */         RenderAtom renderAtom = (RenderAtom)this.allTransparentObjects.get(b);
/*  830 */         for (byte b1 = 0; b1 < renderAtom.rListInfo.length; b1++) {
/*  831 */           if (renderAtom.rListInfo[b1].geometry() != null) {
/*      */             
/*  833 */             double d = renderAtom.geometryAtom.centroid[b1].distanceSquared(this.eyeInVworld);
/*  834 */             (renderAtom.parentTInfo[b1]).zVal = d;
/*  835 */             (renderAtom.parentTInfo[b1]).geometryAtom = renderAtom.geometryAtom;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  844 */       if (i > 0 && 1.5F * this.numDirtyTinfo > this.nElements)
/*      */       {
/*  846 */         bool = true;
/*      */       }
/*      */       
/*  849 */       if (i > 0) {
/*  850 */         TransparentRenderingInfo transparentRenderingInfo = null;
/*  851 */         Iterator iterator = this.dirtyDepthSortRenderAtom.iterator();
/*  852 */         while (iterator.hasNext()) {
/*  853 */           RenderAtom renderAtom = (RenderAtom)iterator.next();
/*  854 */           if (!renderAtom.inRenderBin())
/*      */             continue; 
/*  856 */           renderAtom.dirtyMask &= (RenderAtom.IN_SORTED_POS_DIRTY_TRANSP_LIST ^ 0xFFFFFFFF);
/*  857 */           if (!bool) {
/*  858 */             transparentRenderingInfo = collectDirtyTRInfo(transparentRenderingInfo, renderAtom);
/*      */           }
/*      */         } 
/*      */         
/*  862 */         if (transparentRenderingInfo != null) {
/*      */           
/*  864 */           transparentRenderingInfo = depthSortAll(transparentRenderingInfo);
/*      */           
/*  866 */           this.transparentInfo = mergeDepthSort(this.transparentInfo, transparentRenderingInfo);
/*      */         } 
/*      */       } 
/*      */       
/*  870 */       if (bool) {
/*  871 */         this.transparentInfo = depthSortAll(this.transparentInfo);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  876 */     if (this.addDlist.size() > 0 && this.removeDlist.size() > 0) {
/*  877 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[this.addDlist.size()];
/*  878 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.addDlist.toArray(arrayOfRenderAtomListInfo);
/*  879 */       for (byte b = 0; b < arrayOfRenderAtomListInfo.length; b++) {
/*  880 */         if (this.removeDlist.contains(arrayOfRenderAtomListInfo[b])) {
/*  881 */           this.addDlist.remove(arrayOfRenderAtomListInfo[b]);
/*  882 */           this.removeDlist.remove(arrayOfRenderAtomListInfo[b]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  887 */     if (this.addDlist.size() > 0 || this.removeDlist.size() > 0) {
/*  888 */       Canvas3D[][] arrayOfCanvas3D = this.view.getCanvasList(false);
/*      */       
/*  890 */       ArrayList arrayList = new ArrayList(5);
/*      */       
/*  892 */       for (byte b = 0; b < arrayOfCanvas3D.length; b++) {
/*  893 */         Canvas3D canvas3D = arrayOfCanvas3D[b][0];
/*  894 */         if (canvas3D.useSharedCtx) {
/*      */           
/*  896 */           if (!arrayList.contains(canvas3D.screen.renderer)) {
/*  897 */             arrayList.add(canvas3D.screen.renderer);
/*  898 */             updateDlistRendererResource(canvas3D.screen.renderer);
/*      */           } 
/*      */         } else {
/*  901 */           updateDlistCanvasResource(arrayOfCanvas3D[b]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  909 */     if (this.dirtyRenderMoleculeList.size() > 0 || this.addDlistPerRinfo.size() > 0 || this.removeDlistPerRinfo.size() > 0 || this.displayListResourceFreeList.size() > 0 || this.toBeAddedTextureResourceFreeList.size() > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  915 */       Canvas3D[][] arrayOfCanvas3D = this.view.getCanvasList(false);
/*      */       
/*      */       byte b;
/*  918 */       for (b = 0; b < arrayOfCanvas3D.length; b++) {
/*  919 */         Canvas3D canvas3D = arrayOfCanvas3D[b][0];
/*  920 */         if (canvas3D.useSharedCtx && canvas3D.screen.renderer != null) {
/*  921 */           updateRendererResource(canvas3D.screen.renderer);
/*      */         } else {
/*  923 */           updateCanvasResource(arrayOfCanvas3D[b]);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  928 */       i = this.displayListResourceFreeList.size();
/*  929 */       for (b = 0; b < i; b++) {
/*  930 */         Integer integer = (Integer)this.displayListResourceFreeList.get(b);
/*  931 */         VirtualUniverse.mc.freeDisplayListId(integer);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  937 */       i = this.dirtyRenderMoleculeList.size();
/*  938 */       for (b = 0; b < i; b++) {
/*  939 */         RenderMolecule renderMolecule = (RenderMolecule)this.dirtyRenderMoleculeList.get(b);
/*  940 */         renderMolecule.onUpdateList = 0;
/*  941 */         RenderAtomListInfo renderAtomListInfo = renderMolecule.primaryRenderAtomList;
/*  942 */         while (renderAtomListInfo != null) {
/*  943 */           this.dlistLockList.add(renderAtomListInfo.geometry());
/*  944 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         } 
/*      */       } 
/*  947 */       i = this.addDlistPerRinfo.size();
/*  948 */       for (b = 0; b < i; b++) {
/*  949 */         RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)this.addDlistPerRinfo.get(b);
/*  950 */         if (renderAtomListInfo.geometry() != null) {
/*  951 */           this.dlistLockList.add(renderAtomListInfo.geometry());
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  957 */     clearAllUpdateObjectState();
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
/*      */   void updateDlistRendererResource(Renderer paramRenderer) {
/*  972 */     int i = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  982 */     if (paramRenderer == null) {
/*      */       return;
/*      */     }
/*      */     
/*  986 */     if ((i = this.addDlist.size()) > 0) {
/*  987 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/*  988 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.addDlist.toArray(arrayOfRenderAtomListInfo);
/*  989 */       for (byte b = 0; b < i; b++) {
/*  990 */         RenderAtomListInfo renderAtomListInfo = arrayOfRenderAtomListInfo[b];
/*  991 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  996 */         this.sharedDList.add(renderAtomListInfo);
/*  997 */         geometryArrayRetained.addDlistUser(this, renderAtomListInfo);
/*      */         
/*  999 */         if ((geometryArrayRetained.resourceCreationMask & paramRenderer.rendererBit) == 0 || geometryArrayRetained.getDlistTimeStamp(paramRenderer.rendererBit) != paramRenderer.sharedCtxTimeStamp) {
/*      */ 
/*      */           
/* 1002 */           geometryArrayRetained.resourceCreationMask |= paramRenderer.rendererBit;
/* 1003 */           this.dirtyList.add(renderAtomListInfo);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1008 */     if ((i = this.removeDlist.size()) > 0) {
/* 1009 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 1010 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.removeDlist.toArray(arrayOfRenderAtomListInfo);
/* 1011 */       for (byte b = 0; b < i; b++) {
/* 1012 */         RenderAtomListInfo renderAtomListInfo = arrayOfRenderAtomListInfo[b];
/* 1013 */         this.sharedDList.remove(renderAtomListInfo);
/*      */         
/* 1015 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 1016 */         geometryArrayRetained.removeDlistUser(this, renderAtomListInfo);
/*      */ 
/*      */         
/* 1019 */         if (geometryArrayRetained.isDlistUserSetEmpty(this)) {
/* 1020 */           paramRenderer.displayListResourceFreeList.add(geometryArrayRetained.dlistObj);
/* 1021 */           geometryArrayRetained.resourceCreationMask &= (paramRenderer.rendererBit ^ 0xFFFFFFFF);
/*      */           
/* 1023 */           if (geometryArrayRetained.resourceCreationMask == 0) {
/* 1024 */             geometryArrayRetained.freeDlistId();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 1029 */     if ((i = this.dirtyList.size()) > 0) {
/* 1030 */       for (byte b = 0; b < i; b++) {
/* 1031 */         RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)this.dirtyList.get(b);
/* 1032 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 1033 */         if ((geometryArrayRetained.resourceCreationMask & paramRenderer.rendererBit) != 0) {
/* 1034 */           paramRenderer.dirtyRenderAtomList.add(renderAtomListInfo);
/*      */         }
/*      */       } 
/* 1037 */       paramRenderer.dirtyDisplayList = true;
/* 1038 */       this.dirtyList.clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateDlistCanvasResource(Canvas3D[] paramArrayOfCanvas3D) {
/* 1046 */     int i = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1051 */     if ((i = this.addDlist.size()) > 0) {
/* 1052 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 1053 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.addDlist.toArray(arrayOfRenderAtomListInfo);
/* 1054 */       for (byte b1 = 0; b1 < i; b1++) {
/* 1055 */         this.sharedDList.add(arrayOfRenderAtomListInfo[b1]);
/*      */ 
/*      */         
/* 1058 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)arrayOfRenderAtomListInfo[b1].geometry();
/* 1059 */         geometryArrayRetained.addDlistUser(this, arrayOfRenderAtomListInfo[b1]);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1064 */     if ((i = this.removeDlist.size()) > 0) {
/* 1065 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 1066 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.removeDlist.toArray(arrayOfRenderAtomListInfo);
/* 1067 */       for (byte b1 = 0; b1 < i; b1++) {
/* 1068 */         this.sharedDList.remove(arrayOfRenderAtomListInfo[b1]);
/*      */ 
/*      */         
/* 1071 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)arrayOfRenderAtomListInfo[b1].geometry();
/* 1072 */         geometryArrayRetained.removeDlistUser(this, arrayOfRenderAtomListInfo[b1]);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1077 */     for (byte b = 0; b < paramArrayOfCanvas3D.length; b++) {
/* 1078 */       Canvas3D canvas3D = paramArrayOfCanvas3D[b];
/*      */       
/* 1080 */       if ((i = this.addDlist.size()) > 0) {
/* 1081 */         RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 1082 */         arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.addDlist.toArray(arrayOfRenderAtomListInfo);
/* 1083 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1084 */           RenderAtomListInfo renderAtomListInfo = arrayOfRenderAtomListInfo[b1];
/* 1085 */           GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/*      */           
/* 1087 */           if ((canvas3D.ctx != null && (geometryArrayRetained.resourceCreationMask & canvas3D.canvasBit) == 0) || geometryArrayRetained.getDlistTimeStamp(canvas3D.canvasBit) != canvas3D.ctxTimeStamp) {
/*      */ 
/*      */ 
/*      */             
/* 1091 */             geometryArrayRetained.resourceCreationMask |= canvas3D.canvasBit;
/* 1092 */             this.dirtyList.add(renderAtomListInfo);
/*      */           } 
/*      */         } 
/*      */       } 
/* 1096 */       if ((i = this.removeDlist.size()) > 0) {
/* 1097 */         RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 1098 */         arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.removeDlist.toArray(arrayOfRenderAtomListInfo);
/* 1099 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1100 */           GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)arrayOfRenderAtomListInfo[b1].geometry();
/*      */ 
/*      */ 
/*      */           
/* 1104 */           if (geometryArrayRetained.isDlistUserSetEmpty(this)) {
/* 1105 */             if (canvas3D.ctx != null) {
/* 1106 */               (paramArrayOfCanvas3D[b]).displayListResourceFreeList.add(geometryArrayRetained.dlistObj);
/*      */             }
/* 1108 */             geometryArrayRetained.resourceCreationMask &= ((paramArrayOfCanvas3D[b]).canvasBit ^ 0xFFFFFFFF);
/*      */             
/* 1110 */             if (geometryArrayRetained.resourceCreationMask == 0)
/* 1111 */               geometryArrayRetained.freeDlistId(); 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1115 */       if ((i = this.dirtyList.size()) > 0) {
/* 1116 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1117 */           RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)this.dirtyList.get(b1);
/* 1118 */           GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 1119 */           if ((geometryArrayRetained.resourceCreationMask & canvas3D.canvasBit) != 0) {
/* 1120 */             canvas3D.dirtyRenderAtomList.add(renderAtomListInfo);
/*      */           }
/*      */         } 
/* 1123 */         canvas3D.dirtyDisplayList = true;
/* 1124 */         this.dirtyList.clear();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void clearAllUpdateObjectState() {
/* 1132 */     this.localeChanged = false;
/* 1133 */     this.obList.clear();
/* 1134 */     this.rmUpdateList.clear();
/* 1135 */     this.ogCIOList.clear();
/* 1136 */     this.aBinUpdateList.clear();
/* 1137 */     this.sBinUpdateList.clear();
/* 1138 */     this.tbUpdateList.clear();
/* 1139 */     this.removeRenderAtomInRMList.clear();
/* 1140 */     this.addOpaqueBin = null;
/* 1141 */     this.bgAddOpaqueBin = null;
/* 1142 */     this.orderedBinsList.clear();
/* 1143 */     this.toBeAddedBinList.clear();
/* 1144 */     this.objUpdateList.clear();
/* 1145 */     this.raLocaleVwcBoundsUpdateList.clear();
/* 1146 */     this.displayListResourceFreeList.clear();
/* 1147 */     this.toBeAddedTextureResourceFreeList.clear();
/* 1148 */     this.dirtyRenderMoleculeList.clear();
/* 1149 */     this.dirtyReferenceGeomList.clear();
/* 1150 */     this.reEvaluateBg = false;
/* 1151 */     this.reloadBgTexture = false;
/* 1152 */     this.textureBinList.clear();
/* 1153 */     this.newNodeComponentList.clear();
/* 1154 */     this.removeNodeComponentList.clear();
/* 1155 */     this.dirtyNodeComponentList.clear();
/* 1156 */     this.reEvaluateClip = false;
/* 1157 */     this.vpcToVworldDirty = false;
/* 1158 */     this.offScreenMessage.clear();
/* 1159 */     this.addDlist.clear();
/* 1160 */     this.removeDlist.clear();
/* 1161 */     this.addDlistPerRinfo.clear();
/* 1162 */     this.removeDlistPerRinfo.clear();
/* 1163 */     clearDirtyOrientedRAs();
/* 1164 */     this.reEvaluateSortMode = false;
/* 1165 */     this.dirtyDepthSortRenderAtom.clear();
/* 1166 */     this.numDirtyTinfo = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateRendererResource(Renderer paramRenderer) {
/* 1174 */     if (paramRenderer == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1178 */     int i = this.addDlistPerRinfo.size();
/*      */     
/* 1180 */     if (i > 0) {
/* 1181 */       for (byte b = 0; b < i; b++) {
/* 1182 */         RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)this.addDlistPerRinfo.get(b);
/* 1183 */         if (renderAtomListInfo.renderAtom.inRenderBin()) {
/* 1184 */           Object[] arrayOfObject = new Object[2];
/* 1185 */           arrayOfObject[0] = renderAtomListInfo;
/* 1186 */           arrayOfObject[1] = renderAtomListInfo.renderAtom.renderMolecule;
/* 1187 */           paramRenderer.dirtyDlistPerRinfoList.add(arrayOfObject);
/*      */         } 
/*      */       } 
/* 1190 */       paramRenderer.dirtyDisplayList = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1195 */     i = this.dirtyRenderMoleculeList.size();
/* 1196 */     if (i > 0) {
/* 1197 */       for (byte b = 0; b < i; b++) {
/* 1198 */         RenderMolecule renderMolecule = (RenderMolecule)this.dirtyRenderMoleculeList.get(b);
/* 1199 */         paramRenderer.dirtyRenderMoleculeList.add(renderMolecule);
/*      */       } 
/* 1201 */       paramRenderer.dirtyDisplayList = true;
/*      */     } 
/*      */ 
/*      */     
/* 1205 */     i = this.toBeAddedTextureResourceFreeList.size();
/*      */     
/* 1207 */     for (byte b1 = 0; b1 < i; b1++) {
/* 1208 */       TextureRetained textureRetained = (TextureRetained)this.toBeAddedTextureResourceFreeList.get(b1);
/* 1209 */       int j = textureRetained.objectId;
/* 1210 */       if (j >= paramRenderer.textureIDResourceTable.size() || j <= 0 || paramRenderer.textureIDResourceTable.get(j) != textureRetained) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1216 */         j = paramRenderer.textureIDResourceTable.indexOf(textureRetained);
/*      */         
/* 1218 */         if (j <= 0) {
/*      */           continue;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1227 */       if ((textureRetained.resourceCreationMask & paramRenderer.rendererBit) != 0) {
/* 1228 */         Integer integer = new Integer(j);
/* 1229 */         if (!paramRenderer.textureIdResourceFreeList.contains(integer)) {
/* 1230 */           paramRenderer.textureIdResourceFreeList.add(integer);
/* 1231 */           textureRetained.resourceCreationMask &= (paramRenderer.rendererBit ^ 0xFFFFFFFF);
/*      */         } 
/*      */       } 
/*      */       
/*      */       continue;
/*      */     } 
/* 1237 */     i = this.displayListResourceFreeList.size();
/*      */     
/*      */     byte b2;
/* 1240 */     for (b2 = 0; b2 < i; b2++) {
/* 1241 */       Integer integer = (Integer)this.displayListResourceFreeList.get(b2);
/*      */ 
/*      */       
/* 1244 */       paramRenderer.displayListResourceFreeList.add(integer);
/*      */     } 
/*      */     
/* 1247 */     i = this.removeDlistPerRinfo.size();
/* 1248 */     for (b2 = 0; b2 < i; b2++) {
/* 1249 */       RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)this.removeDlistPerRinfo.get(b2);
/* 1250 */       paramRenderer.displayListResourceFreeList.add(new Integer(renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index]));
/* 1251 */       renderAtomListInfo.groupType = 0;
/* 1252 */       renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] = -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateCanvasResource(Canvas3D[] paramArrayOfCanvas3D) {
/* 1263 */     for (byte b = 0; b < paramArrayOfCanvas3D.length; b++) {
/* 1264 */       Canvas3D canvas3D = paramArrayOfCanvas3D[b];
/*      */ 
/*      */       
/* 1267 */       int i = this.addDlistPerRinfo.size();
/* 1268 */       if (i > 0) {
/* 1269 */         for (byte b2 = 0; b2 < i; b2++) {
/* 1270 */           RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)this.addDlistPerRinfo.get(b2);
/* 1271 */           if (renderAtomListInfo.renderAtom.inRenderBin()) {
/* 1272 */             Object[] arrayOfObject = new Object[2];
/* 1273 */             arrayOfObject[0] = renderAtomListInfo;
/* 1274 */             arrayOfObject[1] = renderAtomListInfo.renderAtom.renderMolecule;
/* 1275 */             canvas3D.dirtyDlistPerRinfoList.add(arrayOfObject);
/*      */           } 
/*      */         } 
/* 1278 */         canvas3D.dirtyDisplayList = true;
/*      */       } 
/*      */       
/* 1281 */       i = this.dirtyRenderMoleculeList.size();
/* 1282 */       if (i > 0) {
/* 1283 */         for (byte b2 = 0; b2 < i; b2++) {
/* 1284 */           RenderMolecule renderMolecule = (RenderMolecule)this.dirtyRenderMoleculeList.get(b2);
/* 1285 */           canvas3D.dirtyRenderMoleculeList.add(renderMolecule);
/*      */         } 
/* 1287 */         canvas3D.dirtyDisplayList = true;
/*      */       } 
/*      */       
/* 1290 */       i = this.toBeAddedTextureResourceFreeList.size();
/*      */       byte b1;
/* 1292 */       for (b1 = 0; b1 < i; b1++) {
/* 1293 */         TextureRetained textureRetained = (TextureRetained)this.toBeAddedTextureResourceFreeList.get(b1);
/* 1294 */         int j = textureRetained.objectId;
/* 1295 */         if (j >= canvas3D.textureIDResourceTable.size() || j <= 0 || canvas3D.textureIDResourceTable.get(j) != textureRetained) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1301 */           j = canvas3D.textureIDResourceTable.indexOf(textureRetained);
/*      */           
/* 1303 */           if (j <= 0) {
/*      */             continue;
/*      */           }
/*      */         } 
/*      */         
/* 1308 */         if ((textureRetained.resourceCreationMask & canvas3D.canvasBit) != 0) {
/* 1309 */           Integer integer = new Integer(j);
/* 1310 */           canvas3D.textureIdResourceFreeList.add(integer);
/* 1311 */           textureRetained.resourceCreationMask &= (canvas3D.canvasBit ^ 0xFFFFFFFF);
/*      */         } 
/*      */         continue;
/*      */       } 
/* 1315 */       i = this.displayListResourceFreeList.size();
/* 1316 */       for (b1 = 0; b1 < i; b1++) {
/* 1317 */         canvas3D.displayListResourceFreeList.add(this.displayListResourceFreeList.get(b1));
/*      */       }
/*      */       
/* 1320 */       i = this.removeDlistPerRinfo.size();
/* 1321 */       for (b1 = 0; b1 < i; b1++) {
/* 1322 */         RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)this.removeDlistPerRinfo.get(b1);
/* 1323 */         canvas3D.displayListResourceFreeList.add(new Integer(renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index]));
/* 1324 */         renderAtomListInfo.groupType = 0;
/* 1325 */         renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] = -1;
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
/*      */   void processMessages(long paramLong) {
/* 1338 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/* 1339 */     int i = getNumMessage();
/*      */     
/* 1341 */     if (i > 0) {
/* 1342 */       for (byte b = 0; b < i; b++) {
/* 1343 */         int i6, i5, n, i3, i2; ViewPlatform viewPlatform; int k, i1, i4; byte b2; View view1; ModelClipRetained modelClipRetained; RenderAtom renderAtom; GeometryAtom[] arrayOfGeometryAtom; AlternateAppearanceRetained alternateAppearanceRetained; FogRetained fogRetained; NodeComponentRetained nodeComponentRetained; ClipRetained clipRetained; byte b1; BackgroundRetained backgroundRetained; LightRetained[] arrayOfLightRetained; int j; J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/* 1344 */         switch (j3dMessage.type) {
/*      */           case 0:
/* 1346 */             insertNodes(j3dMessage);
/* 1347 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 1:
/* 1350 */             removeNodes(j3dMessage);
/* 1351 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 3:
/* 1354 */             this.transformMsg = true;
/* 1355 */             j3dMessage.decRefcount();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 19:
/* 1360 */             arrayOfLightRetained = (LightRetained[])j3dMessage.args[3];
/* 1361 */             for (b1 = 0; b1 < arrayOfLightRetained.length; b1++) {
/* 1362 */               if (this.universe.renderingEnvironmentStructure.isLightScopedToThisView(arrayOfLightRetained[b1], this.view)) {
/* 1363 */                 this.lightMessageList.add(j3dMessage);
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */             break;
/*      */           
/*      */           case 27:
/* 1370 */             this.visGAIsDirty = true;
/* 1371 */             this.visQuery = true;
/* 1372 */             processSwitchChanged(j3dMessage, paramLong);
/*      */             
/* 1374 */             if (this.universe.transformStructure.getLazyUpdate()) {
/* 1375 */               this.transformMsg = true;
/*      */             }
/* 1377 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 20:
/* 1380 */             backgroundRetained = (BackgroundRetained)j3dMessage.args[0];
/* 1381 */             if (this.universe.renderingEnvironmentStructure.isBgScopedToThisView(backgroundRetained, this.view)) {
/* 1382 */               this.reEvaluateBg = true;
/* 1383 */               this.reloadBgTexture = true;
/*      */             } 
/* 1385 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 21:
/* 1388 */             clipRetained = (ClipRetained)j3dMessage.args[0];
/* 1389 */             if (this.universe.renderingEnvironmentStructure.isClipScopedToThisView(clipRetained, this.view))
/* 1390 */               this.reEvaluateClip = true; 
/* 1391 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           
/*      */           case 12:
/* 1395 */             nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 1396 */             arrayOfGeometryAtom = (GeometryAtom[])j3dMessage.args[3];
/* 1397 */             renderAtom = null;
/* 1398 */             b2 = -1;
/*      */ 
/*      */             
/* 1401 */             for (i4 = 0; i4 < arrayOfGeometryAtom.length && b2 < 0; i4++) {
/* 1402 */               renderAtom = arrayOfGeometryAtom[i4].getRenderAtom(this.view);
/* 1403 */               if (renderAtom != null && renderAtom.inRenderBin())
/*      */               {
/*      */ 
/*      */                 
/* 1407 */                 b2 = i4;
/*      */               }
/*      */             } 
/*      */             
/* 1411 */             if (b2 >= 0) {
/* 1412 */               i4 = (nodeComponentRetained.mirror.changedFrequent == 0 || renderAtom.renderMolecule.definingTransparency != nodeComponentRetained.mirror) ? 1 : 0;
/*      */               
/* 1414 */               processRenderMoleculeNodeComponentChanged(j3dMessage.args, 32, b2, i4);
/*      */             } 
/*      */ 
/*      */             
/* 1418 */             j3dMessage.decRefcount();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 9:
/* 1423 */             nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 1424 */             arrayOfGeometryAtom = (GeometryAtom[])j3dMessage.args[3];
/* 1425 */             renderAtom = null;
/* 1426 */             b2 = -1;
/*      */ 
/*      */ 
/*      */             
/* 1430 */             for (i3 = 0; i3 < arrayOfGeometryAtom.length && b2 < 0; i3++) {
/* 1431 */               renderAtom = arrayOfGeometryAtom[i3].getRenderAtom(this.view);
/* 1432 */               if (renderAtom != null && renderAtom.inRenderBin())
/*      */               {
/*      */ 
/*      */                 
/* 1436 */                 b2 = i3;
/*      */               }
/*      */             } 
/*      */             
/* 1440 */             if (b2 >= 0) {
/* 1441 */               i3 = (nodeComponentRetained.mirror.changedFrequent == 0 || renderAtom.renderMolecule.definingPolygonAttributes != nodeComponentRetained.mirror) ? 1 : 0;
/*      */               
/* 1443 */               processRenderMoleculeNodeComponentChanged(j3dMessage.args, 128, b2, i3);
/*      */             } 
/*      */ 
/*      */             
/* 1447 */             j3dMessage.decRefcount();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 7:
/* 1452 */             nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 1453 */             arrayOfGeometryAtom = (GeometryAtom[])j3dMessage.args[3];
/* 1454 */             renderAtom = null;
/* 1455 */             b2 = -1;
/*      */ 
/*      */ 
/*      */             
/* 1459 */             for (i2 = 0; i2 < arrayOfGeometryAtom.length && b2 < 0; i2++) {
/* 1460 */               renderAtom = arrayOfGeometryAtom[i2].getRenderAtom(this.view);
/* 1461 */               if (renderAtom != null && renderAtom.inRenderBin())
/*      */               {
/*      */ 
/*      */                 
/* 1465 */                 b2 = i2;
/*      */               }
/*      */             } 
/*      */             
/* 1469 */             if (b2 >= 0) {
/* 1470 */               i2 = (nodeComponentRetained.mirror.changedFrequent == 0 || renderAtom.renderMolecule.definingLineAttributes != nodeComponentRetained.mirror) ? 1 : 0;
/*      */               
/* 1472 */               processRenderMoleculeNodeComponentChanged(j3dMessage.args, 256, b2, i2);
/*      */             } 
/*      */ 
/*      */             
/* 1476 */             j3dMessage.decRefcount();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 8:
/* 1481 */             nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 1482 */             arrayOfGeometryAtom = (GeometryAtom[])j3dMessage.args[3];
/* 1483 */             renderAtom = null;
/* 1484 */             b2 = -1;
/*      */ 
/*      */             
/* 1487 */             for (i1 = 0; i1 < arrayOfGeometryAtom.length && b2 < 0; i1++) {
/* 1488 */               renderAtom = arrayOfGeometryAtom[i1].getRenderAtom(this.view);
/* 1489 */               if (renderAtom != null && renderAtom.inRenderBin())
/*      */               {
/*      */ 
/*      */                 
/* 1493 */                 b2 = i1;
/*      */               }
/*      */             } 
/*      */             
/* 1497 */             if (b2 >= 0) {
/* 1498 */               i1 = (nodeComponentRetained.mirror.changedFrequent == 0 || renderAtom.renderMolecule.definingPointAttributes != nodeComponentRetained.mirror) ? 1 : 0;
/*      */ 
/*      */               
/* 1501 */               processRenderMoleculeNodeComponentChanged(j3dMessage.args, 512, b2, i1);
/*      */             } 
/*      */ 
/*      */             
/* 1505 */             j3dMessage.decRefcount();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 13:
/* 1510 */             nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 1511 */             arrayOfGeometryAtom = (GeometryAtom[])j3dMessage.args[3];
/* 1512 */             renderAtom = null;
/* 1513 */             b2 = -1;
/*      */ 
/*      */ 
/*      */             
/* 1517 */             for (n = 0; n < arrayOfGeometryAtom.length && b2 < 0; n++) {
/* 1518 */               renderAtom = arrayOfGeometryAtom[n].getRenderAtom(this.view);
/* 1519 */               if (renderAtom != null && renderAtom.inRenderBin())
/*      */               {
/*      */ 
/*      */                 
/* 1523 */                 b2 = n;
/*      */               }
/*      */             } 
/*      */             
/* 1527 */             if (b2 >= 0) {
/* 1528 */               n = (nodeComponentRetained.mirror.changedFrequent == 0 || renderAtom.renderMolecule.definingMaterial != nodeComponentRetained.mirror) ? 1 : 0;
/*      */               
/* 1530 */               processRenderMoleculeNodeComponentChanged(j3dMessage.args, 1, b2, n);
/*      */             } 
/*      */ 
/*      */             
/* 1534 */             j3dMessage.decRefcount();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 6:
/* 1539 */             nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 1540 */             arrayOfGeometryAtom = (GeometryAtom[])j3dMessage.args[3];
/* 1541 */             renderAtom = null;
/* 1542 */             b2 = -1;
/*      */ 
/*      */ 
/*      */             
/* 1546 */             for (k = 0; k < arrayOfGeometryAtom.length && b2 < 0; k++) {
/* 1547 */               renderAtom = arrayOfGeometryAtom[k].getRenderAtom(this.view);
/* 1548 */               if (renderAtom != null && renderAtom.inRenderBin())
/*      */               {
/*      */ 
/*      */                 
/* 1552 */                 b2 = k;
/*      */               }
/*      */             } 
/*      */             
/* 1556 */             if (b2 >= 0) {
/* 1557 */               k = (nodeComponentRetained.mirror.changedFrequent == 0 || renderAtom.renderMolecule.definingColoringAttributes != nodeComponentRetained.mirror) ? 1 : 0;
/*      */               
/* 1559 */               processRenderMoleculeNodeComponentChanged(j3dMessage.args, 16, b2, k);
/*      */             } 
/*      */ 
/*      */             
/* 1563 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           
/*      */           case 11:
/* 1567 */             processTextureAttributesChanged((NodeComponentRetained)j3dMessage.args[0], (GeometryAtom[])j3dMessage.args[3]);
/*      */ 
/*      */             
/* 1570 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 54:
/* 1573 */             addDirtyNodeComponent((NodeComponentRetained)j3dMessage.args[0]);
/* 1574 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 47:
/* 1577 */             processTextureUnitStateChanged((NodeComponentRetained)j3dMessage.args[0], (GeometryAtom[])j3dMessage.args[3]);
/*      */ 
/*      */             
/* 1580 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 14:
/* 1583 */             processTexCoordGenerationChanged((NodeComponentRetained)j3dMessage.args[0], (GeometryAtom[])j3dMessage.args[3]);
/*      */             
/* 1585 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           
/*      */           case 15:
/* 1589 */             processTextureChanged((NodeComponentRetained)j3dMessage.args[0], (GeometryAtom[])j3dMessage.args[3], j3dMessage.args);
/*      */ 
/*      */             
/* 1592 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 63:
/*      */           case 64:
/*      */           case 65:
/* 1597 */             processShaderComponentChanged(j3dMessage.args);
/* 1598 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 10:
/* 1601 */             processAttributeBinNodeComponentChanged(j3dMessage.args);
/* 1602 */             j = ((Integer)j3dMessage.args[1]).intValue();
/* 1603 */             if (j == 16) {
/* 1604 */               this.visGAIsDirty = true;
/* 1605 */               this.visQuery = true;
/*      */             } 
/* 1607 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 18:
/* 1610 */             processAppearanceChanged(j3dMessage.args);
/* 1611 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 22:
/* 1614 */             fogRetained = ((FogRetained)j3dMessage.args[0]).mirrorFog;
/* 1615 */             if (this.universe.renderingEnvironmentStructure.isFogScopedToThisView(fogRetained, this.view)) {
/* 1616 */               processFogChanged(j3dMessage.args);
/*      */             }
/* 1618 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 41:
/* 1621 */             alternateAppearanceRetained = ((AlternateAppearanceRetained)j3dMessage.args[0]).mirrorAltApp;
/* 1622 */             if (this.universe.renderingEnvironmentStructure.isAltAppScopedToThisView(alternateAppearanceRetained, this.view)) {
/* 1623 */               this.altAppearanceDirty = true;
/*      */             }
/* 1625 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 36:
/* 1628 */             modelClipRetained = ((ModelClipRetained)j3dMessage.args[0]).mirrorModelClip;
/* 1629 */             if (this.universe.renderingEnvironmentStructure.isMclipScopedToThisView(modelClipRetained, this.view)) {
/* 1630 */               processModelClipChanged(j3dMessage.args);
/*      */             }
/* 1632 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 23:
/* 1635 */             processBoundingLeafChanged(j3dMessage.args, paramLong);
/*      */             
/* 1637 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 24:
/* 1640 */             processShapeChanged(j3dMessage.args, paramLong);
/* 1641 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 46:
/* 1644 */             processOrientedShape3DChanged((Object[])j3dMessage.args[0]);
/* 1645 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 16:
/* 1648 */             processMorphChanged(j3dMessage.args, paramLong);
/* 1649 */             j = ((Integer)j3dMessage.args[1]).intValue();
/* 1650 */             if ((j & true) == 0) {
/* 1651 */               this.visGAIsDirty = true;
/* 1652 */               this.visQuery = true;
/*      */             } 
/* 1654 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           
/*      */           case 4:
/* 1658 */             view1 = (View)j3dMessage.args[0];
/* 1659 */             viewPlatform = view1.getViewPlatform();
/* 1660 */             i5 = ((Integer)j3dMessage.args[2]).intValue();
/* 1661 */             i6 = ((Integer)j3dMessage.args[3]).intValue();
/* 1662 */             if (i5 == 1) {
/* 1663 */               if (i6 != this.transpSortMode) {
/* 1664 */                 this.reEvaluateSortMode = true;
/* 1665 */                 this.cachedTranspSortMode = i6;
/*      */               } 
/* 1667 */             } else if (viewPlatform != null) {
/* 1668 */               if (i6 != this.transpSortMode) {
/* 1669 */                 this.reEvaluateSortMode = true;
/* 1670 */                 this.cachedTranspSortMode = i6;
/*      */               } 
/* 1672 */               updateViewPlatform((ViewPlatformRetained)viewPlatform.retained, ((Float)j3dMessage.args[1]).floatValue());
/*      */               
/* 1674 */               this.visQuery = true;
/*      */               
/* 1676 */               if (2 != this.view.viewCache.visibilityPolicy || this.locale != ((ViewPlatformRetained)viewPlatform.retained).locale) {
/*      */ 
/*      */ 
/*      */                 
/* 1680 */                 for (int i7 = this.renderAtoms.size() - 1; i7 >= 0; i7--) {
/* 1681 */                   removeARenderAtom((RenderAtom)this.renderAtoms.get(i7));
/*      */                 }
/* 1683 */                 this.renderAtoms.clear();
/* 1684 */                 this.visGAIsDirty = true;
/* 1685 */                 if (this.locale != ((ViewPlatformRetained)viewPlatform.retained).locale) {
/* 1686 */                   this.locale = ((ViewPlatformRetained)viewPlatform.retained).locale;
/* 1687 */                   this.localeChanged = true;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1691 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           
/*      */           case 48:
/* 1695 */             updateViewPlatform((ViewPlatformRetained)j3dMessage.args[0], ((Float)j3dMessage.args[1]).floatValue());
/*      */             
/* 1697 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 26:
/* 1700 */             processDataChanged((Object[])j3dMessage.args[0], (Object[])j3dMessage.args[1], paramLong);
/*      */ 
/*      */             
/* 1703 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 17:
/* 1706 */             processGeometryChanged(j3dMessage.args);
/* 1707 */             this.visGAIsDirty = true;
/* 1708 */             this.visQuery = true;
/* 1709 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           
/*      */           case 35:
/*      */           case 37:
/* 1714 */             processGeometryAtomsChanged((Object[])j3dMessage.args[0]);
/* 1715 */             this.visGAIsDirty = true;
/* 1716 */             this.visQuery = true;
/* 1717 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 25:
/* 1720 */             processText3DTransformChanged((Object[])j3dMessage.args[0], (Object[])j3dMessage.args[1], paramLong);
/*      */ 
/*      */             
/* 1723 */             this.visQuery = true;
/* 1724 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           case 32:
/* 1727 */             processOrderedGroupInserted(j3dMessage);
/*      */             
/* 1729 */             this.ogCIOList.add(j3dMessage);
/*      */             break;
/*      */           case 33:
/* 1732 */             processOrderedGroupRemoved(j3dMessage);
/*      */             
/* 1734 */             this.ogCIOList.add(j3dMessage);
/*      */             break;
/*      */           
/*      */           case 59:
/* 1738 */             this.ogCIOList.add(j3dMessage);
/*      */             break;
/*      */           case 42:
/* 1741 */             this.offScreenMessage.add(j3dMessage);
/*      */             break;
/*      */           case 56:
/* 1744 */             processViewSpecificGroupChanged(j3dMessage);
/* 1745 */             this.visQuery = true;
/* 1746 */             j3dMessage.decRefcount();
/*      */             break;
/*      */           default:
/* 1749 */             j3dMessage.decRefcount();
/*      */             break;
/*      */         } 
/*      */       } 
/* 1753 */       if (this.transformMsg) {
/* 1754 */         processTransformChanged(paramLong);
/* 1755 */         this.transformMsg = false;
/*      */       } 
/* 1757 */       if (this.lightMessageList.size() > 0) {
/* 1758 */         processLightChanged();
/* 1759 */         this.lightMessageList.clear();
/*      */       } 
/* 1761 */       VirtualUniverse.mc.addMirrorObject(this);
/*      */ 
/*      */       
/* 1764 */       Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*      */     } 
/*      */     
/* 1767 */     if (this.reEvaluateBg) {
/* 1768 */       this.currentActiveBackground = this.universe.renderingEnvironmentStructure.getApplicationBackground(this.vpSchedSphereInVworld, this.locale, this.view);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1773 */     if (this.visQuery) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1778 */       computeViewFrustumBBox(this.viewFrustumBBox);
/*      */ 
/*      */       
/* 1781 */       ViewPlatform viewPlatform = this.view.getViewPlatform();
/* 1782 */       if (viewPlatform != null) {
/* 1783 */         boolean bool = this.universe.geometryStructure.getVisibleBHTrees(this, this.viewFrustumBBox, this.locale, paramLong, (this.visGAIsDirty || this.reactivateView || this.localeChanged || (this.view.viewCache.vcDirtyMask & 0x10000) != 0), this.view.viewCache.visibilityPolicy);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1791 */         this.reactivateView = false;
/*      */         
/* 1793 */         if (this.currentActiveBackground != null && this.currentActiveBackground.geometryBranch != null) {
/*      */           
/* 1795 */           GeometryAtom[] arrayOfGeometryAtom = this.currentActiveBackground.getBackgroundGeometryAtoms();
/*      */           
/* 1797 */           if (arrayOfGeometryAtom != null) {
/* 1798 */             processBgGeometryAtoms(arrayOfGeometryAtom, paramLong);
/*      */           }
/*      */         } 
/*      */         
/* 1802 */         if (!bool) {
/*      */           
/* 1804 */           this.frameCount++;
/* 1805 */           if (this.frameCount > this.frameCountCutoff) {
/* 1806 */             this.frameCount = 0;
/* 1807 */             checkForCompaction();
/*      */           }
/* 1809 */           else if (this.frameCount == this.notVisibleCount) {
/* 1810 */             this.removeCutoffTime = paramLong;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1815 */       this.visGAIsDirty = false;
/* 1816 */       this.visQuery = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1823 */     if (this.envDirty == REEVALUATE_ALL_ENV || this.envDirty == 3 || this.envDirty > 4) {
/*      */       
/* 1825 */       reEvaluateEnv(this.changedLts, this.changedFogs, this.changedModelClips, true, this.altAppearanceDirty);
/*      */     
/*      */     }
/* 1828 */     else if (this.envDirty == 0 && this.altAppearanceDirty) {
/* 1829 */       reEvaluateAlternateAppearance();
/*      */     
/*      */     }
/* 1832 */     else if ((this.envDirty & REEVALUATE_LIGHTS) != 0) {
/* 1833 */       reEvaluateLights(this.altAppearanceDirty);
/*      */     }
/* 1835 */     else if ((this.envDirty & REEVALUATE_FOG) != 0) {
/* 1836 */       reEvaluateFog(this.changedFogs, (this.changedFogs.size() > 0), this.altAppearanceDirty);
/* 1837 */     } else if ((this.envDirty & REEVALUATE_MCLIP) != 0) {
/* 1838 */       reEvaluateModelClip(this.changedModelClips, (this.changedModelClips.size() > 0), this.altAppearanceDirty);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1845 */     if (this.updateCheckList.size() > 0) {
/* 1846 */       int j = this.updateCheckList.size();
/*      */       
/* 1848 */       for (byte b = 0; b < j; b++) {
/* 1849 */         NodeComponentUpdate nodeComponentUpdate = (NodeComponentUpdate)this.updateCheckList.get(b);
/* 1850 */         nodeComponentUpdate.updateNodeComponentCheck();
/*      */       } 
/* 1852 */       this.updateCheckList.clear();
/*      */     } 
/*      */ 
/*      */     
/* 1856 */     this.changedLts.clear();
/* 1857 */     this.changedFogs.clear();
/* 1858 */     this.changedModelClips.clear();
/* 1859 */     this.envDirty = 0;
/* 1860 */     this.altAppearanceDirty = false;
/*      */     
/* 1862 */     this.view.renderBinReady = true;
/*      */     
/* 1864 */     VirtualUniverse.mc.sendRunMessage(this.view, 16);
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
/*      */   void processSwitchChanged(J3dMessage paramJ3dMessage, long paramLong) {
/* 1876 */     RenderingEnvironmentStructure renderingEnvironmentStructure = this.universe.renderingEnvironmentStructure;
/*      */ 
/*      */     
/* 1879 */     UpdateTargets updateTargets = (UpdateTargets)paramJ3dMessage.args[0];
/* 1880 */     UnorderList unorderList = updateTargets.targetList[1];
/*      */     
/* 1882 */     if (unorderList != null) {
/* 1883 */       int i = unorderList.size();
/* 1884 */       Object[] arrayOfObject = unorderList.toArray(false);
/*      */       
/* 1886 */       for (byte b = 0; b < i; b++) {
/* 1887 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 1888 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*      */           
/* 1890 */           if (arrayOfObject1[b1] instanceof LightRetained && renderingEnvironmentStructure.isLightScopedToThisView(arrayOfObject1[b1], this.view)) {
/*      */             
/* 1892 */             this.envDirty |= REEVALUATE_LIGHTS;
/* 1893 */           } else if (arrayOfObject1[b1] instanceof FogRetained && renderingEnvironmentStructure.isFogScopedToThisView(arrayOfObject1[b1], this.view)) {
/*      */             
/* 1895 */             this.envDirty |= REEVALUATE_FOG;
/* 1896 */           } else if (arrayOfObject1[b1] instanceof ModelClipRetained && renderingEnvironmentStructure.isMclipScopedToThisView(arrayOfObject1[b1], this.view)) {
/*      */             
/* 1898 */             this.envDirty |= REEVALUATE_MCLIP;
/* 1899 */           } else if (arrayOfObject1[b1] instanceof BackgroundRetained && renderingEnvironmentStructure.isBgScopedToThisView(arrayOfObject1[b1], this.view)) {
/*      */             
/* 1901 */             this.reEvaluateBg = true;
/* 1902 */           } else if (arrayOfObject1[b1] instanceof ClipRetained && renderingEnvironmentStructure.isClipScopedToThisView(arrayOfObject1[b1], this.view)) {
/*      */             
/* 1904 */             this.reEvaluateClip = true;
/* 1905 */           } else if (arrayOfObject1[b1] instanceof AlternateAppearanceRetained && renderingEnvironmentStructure.isAltAppScopedToThisView(arrayOfObject1[b1], this.view)) {
/*      */             
/* 1907 */             this.altAppearanceDirty = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1913 */     unorderList = updateTargets.targetList[5];
/* 1914 */     if (unorderList != null) {
/* 1915 */       int i = unorderList.size();
/* 1916 */       Object[] arrayOfObject1 = unorderList.toArray(false);
/* 1917 */       Object[] arrayOfObject2 = (Object[])paramJ3dMessage.args[1];
/*      */ 
/*      */ 
/*      */       
/* 1921 */       for (byte b = 0; b < i; b++) {
/* 1922 */         Object[] arrayOfObject3 = (Object[])arrayOfObject1[b];
/* 1923 */         Object[] arrayOfObject4 = (Object[])arrayOfObject2[b];
/* 1924 */         for (byte b1 = 0; b1 < arrayOfObject3.length; b1++) {
/*      */           
/* 1926 */           Object[] arrayOfObject = (Object[])arrayOfObject4[b1];
/* 1927 */           BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)arrayOfObject3[b1];
/* 1928 */           for (byte b2 = 0; b2 < arrayOfObject.length; b2++) {
/*      */             
/* 1930 */             if (arrayOfObject[b2] instanceof FogRetained && renderingEnvironmentStructure.isFogScopedToThisView(arrayOfObject[b2], this.view)) {
/*      */               
/* 1932 */               this.envDirty |= REEVALUATE_FOG;
/* 1933 */             } else if (arrayOfObject[b2] instanceof LightRetained && renderingEnvironmentStructure.isLightScopedToThisView(arrayOfObject[b2], this.view)) {
/*      */               
/* 1935 */               this.envDirty |= REEVALUATE_LIGHTS;
/* 1936 */             } else if (arrayOfObject[b2] instanceof ModelClipRetained && renderingEnvironmentStructure.isMclipScopedToThisView(arrayOfObject[b2], this.view)) {
/*      */               
/* 1938 */               this.envDirty |= REEVALUATE_MCLIP;
/* 1939 */             } else if (arrayOfObject[b2] instanceof AlternateAppearanceRetained && renderingEnvironmentStructure.isAltAppScopedToThisView(arrayOfObject[b2], this.view)) {
/*      */ 
/*      */               
/* 1942 */               this.altAppearanceDirty = true;
/* 1943 */             } else if (arrayOfObject[b2] instanceof BackgroundRetained && renderingEnvironmentStructure.isBgScopedToThisView(arrayOfObject[b2], this.view)) {
/*      */               
/* 1945 */               this.reEvaluateBg = true;
/* 1946 */             } else if (arrayOfObject[b2] instanceof ClipRetained && renderingEnvironmentStructure.isClipScopedToThisView(arrayOfObject[b2], this.view)) {
/*      */               
/* 1948 */               this.reEvaluateClip = true;
/*      */             } 
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
/*      */   void processPossibleBinChanged(Object[] paramArrayOfObject) {
/* 1964 */     GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[3];
/* 1965 */     for (byte b = 0; b < arrayOfGeometryAtom.length; b++) {
/* 1966 */       RenderAtom renderAtom = arrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 1967 */       if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1974 */         TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/* 1975 */         renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 1976 */         reInsertRenderAtom(textureBin, renderAtom);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processRenderMoleculeNodeComponentChanged(Object[] paramArrayOfObject, int paramInt1, int paramInt2, boolean paramBoolean) {
/* 1987 */     NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)paramArrayOfObject[0];
/* 1988 */     GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[3];
/* 1989 */     for (int i = paramInt2; i < arrayOfGeometryAtom.length; i++) {
/* 1990 */       RenderAtom renderAtom = arrayOfGeometryAtom[i].getRenderAtom(this.view);
/* 1991 */       if (renderAtom != null && renderAtom.inRenderBin())
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2001 */         if (paramBoolean && !renderAtom.renderMolecule.soleUser) {
/* 2002 */           TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/* 2003 */           renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2004 */           reInsertRenderAtom(textureBin, renderAtom);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2015 */           if ((renderAtom.renderMolecule.soleUserCompDirty & 0x3B1) == 0) {
/* 2016 */             this.rmUpdateList.add(renderAtom.renderMolecule);
/*      */           }
/* 2018 */           renderAtom.renderMolecule.soleUserCompDirty |= paramInt1;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processTextureAttributesChanged(NodeComponentRetained paramNodeComponentRetained, GeometryAtom[] paramArrayOfGeometryAtom) {
/* 2028 */     RenderAtom renderAtom = null;
/*      */ 
/*      */     
/* 2031 */     boolean bool = false;
/*      */     
/* 2033 */     if (paramNodeComponentRetained.mirror.changedFrequent == 0) {
/* 2034 */       bool = true;
/*      */     }
/*      */     
/* 2037 */     for (byte b = 0; b < paramArrayOfGeometryAtom.length; b++) {
/* 2038 */       renderAtom = paramArrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2039 */       if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */ 
/*      */         
/* 2043 */         TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/*      */         
/* 2045 */         if (!bool)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2054 */           for (byte b1 = 0; b1 < textureBin.texUnitState.length; b1++) {
/*      */             
/* 2056 */             if (textureBin.texUnitState[b1] != null)
/*      */             {
/*      */               
/* 2059 */               if ((textureBin.texUnitState[b1]).texAttrs == paramNodeComponentRetained.mirror) {
/*      */                 return;
/*      */               }
/*      */             }
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2068 */         if ((textureBin.tbFlag & 0x4) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2074 */           if (textureBin.soleUserCompDirty == 0) {
/* 2075 */             this.tbUpdateList.add(textureBin);
/*      */           }
/*      */           
/* 2078 */           textureBin.soleUserCompDirty |= 0x2;
/*      */         } else {
/*      */           
/* 2081 */           ShaderBin shaderBin = renderAtom.renderMolecule.textureBin.shaderBin;
/* 2082 */           renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2083 */           reInsertTextureBin(shaderBin, renderAtom);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void processTexCoordGenerationChanged(NodeComponentRetained paramNodeComponentRetained, GeometryAtom[] paramArrayOfGeometryAtom) {
/* 2091 */     RenderAtom renderAtom = null;
/*      */ 
/*      */     
/* 2094 */     boolean bool = false;
/*      */     
/* 2096 */     if (paramNodeComponentRetained.mirror.changedFrequent == 0) {
/* 2097 */       bool = true;
/*      */     }
/*      */     
/* 2100 */     for (byte b = 0; b < paramArrayOfGeometryAtom.length; b++) {
/* 2101 */       renderAtom = paramArrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2102 */       if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */ 
/*      */         
/* 2106 */         TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/*      */         
/* 2108 */         if (!bool)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2117 */           for (byte b1 = 0; b1 < textureBin.texUnitState.length; b1++) {
/*      */             
/* 2119 */             if (textureBin.texUnitState[b1] != null)
/*      */             {
/*      */               
/* 2122 */               if ((textureBin.texUnitState[b1]).texGen == paramNodeComponentRetained.mirror) {
/*      */                 return;
/*      */               }
/*      */             }
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2131 */         if ((textureBin.tbFlag & 0x4) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2137 */           if (textureBin.soleUserCompDirty == 0) {
/* 2138 */             this.tbUpdateList.add(textureBin);
/*      */           }
/*      */           
/* 2141 */           textureBin.soleUserCompDirty |= 0x4;
/*      */         } else {
/*      */           
/* 2144 */           ShaderBin shaderBin = renderAtom.renderMolecule.textureBin.shaderBin;
/* 2145 */           renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2146 */           reInsertTextureBin(shaderBin, renderAtom);
/*      */         } 
/*      */       } 
/*      */     }  } void processTextureChanged(NodeComponentRetained paramNodeComponentRetained, GeometryAtom[] paramArrayOfGeometryAtom, Object[] paramArrayOfObject) { ImageComponentRetained imageComponentRetained; byte b2; ImageComponent imageComponent; int k; TextureRetained textureRetained2;
/*      */     int j;
/*      */     Object[] arrayOfObject2;
/*      */     ImageComponent[] arrayOfImageComponent;
/*      */     Object[] arrayOfObject1;
/*      */     TextureRetained textureRetained1;
/*      */     byte b1;
/* 2156 */     RenderAtom renderAtom = null;
/*      */ 
/*      */     
/* 2159 */     boolean bool = false;
/* 2160 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */     
/* 2162 */     switch (i) {
/*      */       case 1:
/* 2164 */         for (b1 = 0; b1 < paramArrayOfGeometryAtom.length; b1++) {
/* 2165 */           renderAtom = paramArrayOfGeometryAtom[b1].getRenderAtom(this.view);
/*      */           
/* 2167 */           if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */             
/* 2170 */             TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/*      */             
/* 2172 */             if (textureBin.soleUserCompDirty == 0)
/*      */             {
/*      */               
/* 2175 */               this.tbUpdateList.add(textureBin);
/*      */             }
/*      */             
/* 2178 */             textureBin.soleUserCompDirty |= 0x8;
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 4:
/* 2184 */         textureRetained1 = (TextureRetained)paramNodeComponentRetained.mirror;
/* 2185 */         arrayOfObject2 = (Object[])paramArrayOfObject[2];
/* 2186 */         j = ((Integer)arrayOfObject2[0]).intValue();
/* 2187 */         k = ((Integer)arrayOfObject2[2]).intValue();
/* 2188 */         imageComponent = (ImageComponent)arrayOfObject2[1];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2197 */         imageComponentRetained = textureRetained1.images[k][j];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2203 */         if (imageComponentRetained != null) {
/* 2204 */           removeNodeComponent(imageComponentRetained);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2210 */         if (imageComponent != null) {
/* 2211 */           addNodeComponent(imageComponent.retained);
/*      */         }
/*      */         break;
/*      */       
/*      */       case 32:
/* 2216 */         arrayOfObject1 = (Object[])paramArrayOfObject[2];
/* 2217 */         arrayOfImageComponent = (ImageComponent[])arrayOfObject1[0];
/* 2218 */         j = ((Integer)arrayOfObject1[1]).intValue();
/* 2219 */         textureRetained2 = (TextureRetained)paramNodeComponentRetained.mirror;
/*      */ 
/*      */         
/* 2222 */         for (b2 = 0; b2 < textureRetained2.maxLevels; b2++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2230 */           ImageComponentRetained imageComponentRetained1 = textureRetained2.images[j][b2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2236 */           if (imageComponentRetained1 != null) {
/* 2237 */             removeNodeComponent(imageComponentRetained1);
/*      */           }
/*      */ 
/*      */           
/* 2241 */           if (arrayOfImageComponent[b2] != null) {
/* 2242 */             addNodeComponent((arrayOfImageComponent[b2]).retained);
/*      */           }
/*      */         } 
/*      */         break;
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processTextureUnitStateChanged(NodeComponentRetained paramNodeComponentRetained, GeometryAtom[] paramArrayOfGeometryAtom) {
/* 2253 */     RenderAtom renderAtom = null;
/*      */ 
/*      */     
/* 2256 */     boolean bool1 = false;
/* 2257 */     boolean bool2 = true;
/*      */     
/* 2259 */     for (byte b = 0; b < paramArrayOfGeometryAtom.length; b++) {
/* 2260 */       renderAtom = paramArrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2261 */       if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */ 
/*      */         
/* 2265 */         TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/*      */         
/* 2267 */         if (bool2) {
/*      */           
/* 2269 */           byte b1 = 0;
/* 2270 */           for (; b1 < textureBin.texUnitState.length && !bool1; 
/* 2271 */             b1++) {
/*      */             
/* 2273 */             if (textureBin.texUnitState[b1] != null)
/*      */             {
/*      */               
/* 2276 */               if ((textureBin.texUnitState[b1]).mirror == paramNodeComponentRetained.mirror) {
/* 2277 */                 bool1 = true;
/* 2278 */                 bool2 = false;
/*      */               }  } 
/*      */           } 
/* 2281 */           bool2 = false;
/*      */         } 
/*      */         
/* 2284 */         if (bool1) {
/*      */           
/* 2286 */           if (textureBin.soleUserCompDirty == 0) {
/* 2287 */             this.tbUpdateList.add(textureBin);
/*      */           }
/*      */           
/* 2290 */           textureBin.soleUserCompDirty |= 0x10;
/*      */         } else {
/*      */           
/* 2293 */           ShaderBin shaderBin = renderAtom.renderMolecule.textureBin.shaderBin;
/* 2294 */           renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2295 */           reInsertTextureBin(shaderBin, renderAtom);
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
/*      */   void processAttributeBinNodeComponentChanged(Object[] paramArrayOfObject) {
/* 2307 */     GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[3];
/* 2308 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 2309 */     NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)paramArrayOfObject[0];
/*      */     
/* 2311 */     RenderAtom renderAtom = null;
/* 2312 */     byte b2 = -1;
/*      */     
/*      */     byte b1;
/* 2315 */     for (b1 = 0; b1 < arrayOfGeometryAtom.length && b2 < 0; b1++) {
/* 2316 */       renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2317 */       if (renderAtom != null && renderAtom.inRenderBin())
/*      */       {
/*      */ 
/*      */         
/* 2321 */         b2 = b1;
/*      */       }
/*      */     } 
/* 2324 */     if (b2 >= 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2330 */       boolean bool = (nodeComponentRetained.mirror.changedFrequent == 0 || renderAtom.renderMolecule.textureBin.attributeBin.definingRenderingAttributes != nodeComponentRetained.mirror) ? 1 : 0;
/*      */ 
/*      */       
/* 2333 */       if (i != 16) {
/* 2334 */         for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2335 */           renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2336 */           if (renderAtom != null && renderAtom.inRenderBin())
/*      */           {
/* 2338 */             if (bool && !renderAtom.renderMolecule.textureBin.attributeBin.soleUser) {
/* 2339 */               EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 2340 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2341 */               reInsertAttributeBin(environmentSet, renderAtom);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2359 */               AttributeBin attributeBin = renderAtom.renderMolecule.textureBin.attributeBin;
/* 2360 */               if ((attributeBin.onUpdateList & AttributeBin.ON_CHANGED_FREQUENT_UPDATE_LIST) == 0) {
/* 2361 */                 this.aBinUpdateList.add(attributeBin);
/* 2362 */                 attributeBin.onUpdateList |= AttributeBin.ON_CHANGED_FREQUENT_UPDATE_LIST;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } else {
/* 2368 */         for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2369 */           renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/*      */           
/* 2371 */           if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */             
/* 2373 */             this.renderAtoms.remove(this.renderAtoms.indexOf(renderAtom));
/* 2374 */             removeARenderAtom(renderAtom);
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
/*      */   void processShaderComponentChanged(Object[] paramArrayOfObject) {
/* 2387 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */     
/* 2389 */     GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[3];
/*      */     
/* 2391 */     RenderAtom renderAtom = null;
/*      */ 
/*      */ 
/*      */     
/* 2395 */     byte b2 = -1;
/*      */     
/*      */     byte b1;
/*      */     
/* 2399 */     for (b1 = 0; b1 < arrayOfGeometryAtom.length && b2 < 0; b1++) {
/* 2400 */       renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2401 */       if (renderAtom != null && renderAtom.inRenderBin())
/*      */       {
/*      */ 
/*      */         
/* 2405 */         b2 = b1;
/*      */       }
/*      */     } 
/* 2408 */     if (b2 >= 0) {
/*      */ 
/*      */ 
/*      */       
/* 2412 */       boolean bool1 = ((i & 0x800) != 0) ? 1 : 0;
/*      */       
/* 2414 */       boolean bool2 = ((i & 0x1000) != 0 || (i & true) != 0 || (i & 0x2) != 0 || (i & 0x4) != 0) ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2420 */       if (bool1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2449 */         for (b1 = 0; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2450 */           renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2451 */           if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */             
/* 2454 */             AttributeBin attributeBin = renderAtom.renderMolecule.textureBin.attributeBin;
/* 2455 */             renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2456 */             reInsertShaderBin(attributeBin, renderAtom);
/*      */           } 
/*      */         } 
/* 2459 */       } else if (bool2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2489 */         for (b1 = 0; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2490 */           renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2491 */           if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */             
/* 2494 */             AttributeBin attributeBin = renderAtom.renderMolecule.textureBin.attributeBin;
/* 2495 */             renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2496 */             reInsertShaderBin(attributeBin, renderAtom);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void processFogChanged(Object[] paramArrayOfObject) {
/* 2506 */     FogRetained fogRetained = (FogRetained)paramArrayOfObject[0];
/*      */     
/* 2508 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */     
/* 2510 */     if ((i & 0xE) != 0) {
/*      */ 
/*      */       
/* 2513 */       this.envDirty |= REEVALUATE_FOG;
/*      */     } else {
/*      */       
/* 2516 */       UnorderList unorderList = fogRetained.mirrorFog.environmentSets;
/* 2517 */       synchronized (unorderList) {
/* 2518 */         EnvironmentSet[] arrayOfEnvironmentSet = (EnvironmentSet[])unorderList.toArray(false);
/* 2519 */         int j = unorderList.size();
/* 2520 */         for (byte b = 0; b < j; b++) {
/* 2521 */           EnvironmentSet environmentSet = arrayOfEnvironmentSet[b];
/* 2522 */           environmentSet.canvasDirty |= 0x2000;
/* 2523 */           if (!environmentSet.onUpdateList) {
/* 2524 */             this.objUpdateList.add(environmentSet);
/* 2525 */             environmentSet.onUpdateList = true;
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
/*      */   void processAppearanceChanged(Object[] paramArrayOfObject) {
/* 2538 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */     
/* 2540 */     GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[3];
/*      */     
/* 2542 */     RenderAtom renderAtom = null;
/* 2543 */     AppearanceRetained appearanceRetained = (AppearanceRetained)paramArrayOfObject[0];
/* 2544 */     int j = 1038;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2550 */     byte b2 = -1;
/*      */     
/*      */     byte b1;
/* 2553 */     for (b1 = 0; b1 < arrayOfGeometryAtom.length && b2 < 0; b1++) {
/* 2554 */       renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2555 */       if (renderAtom != null && renderAtom.inRenderBin())
/*      */       {
/*      */ 
/*      */         
/* 2559 */         b2 = b1;
/*      */       }
/*      */     } 
/*      */     
/* 2563 */     if (b2 >= 0) {
/*      */       
/* 2565 */       if ((i & j) != 0) {
/*      */ 
/*      */         
/* 2568 */         if ((appearanceRetained.mirror.changedFrequent & j) != 0 && (renderAtom.renderMolecule.textureBin.tbFlag & 0x4) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2581 */           for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2582 */             renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2583 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             {
/*      */               
/* 2586 */               TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/* 2587 */               if (textureBin.soleUserCompDirty == 0) {
/* 2588 */                 this.tbUpdateList.add(textureBin);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2594 */               textureBin.soleUserCompDirty |= 0x1;
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 2610 */           for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2611 */             renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2612 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             
/* 2614 */             { ShaderBin shaderBin = renderAtom.renderMolecule.textureBin.shaderBin;
/* 2615 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2616 */               reInsertTextureBin(shaderBin, renderAtom); } 
/*      */           } 
/*      */         } 
/* 2619 */       } else if ((i & 0x40) != 0) {
/* 2620 */         boolean bool = ((Boolean)paramArrayOfObject[4]).booleanValue();
/* 2621 */         this.visGAIsDirty = true;
/* 2622 */         this.visQuery = true;
/* 2623 */         if (!bool) {
/*      */           
/* 2625 */           for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2626 */             renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/*      */             
/* 2628 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             {
/* 2630 */               this.renderAtoms.remove(this.renderAtoms.indexOf(renderAtom));
/* 2631 */               removeARenderAtom(renderAtom);
/*      */             }
/*      */           
/*      */           } 
/* 2635 */         } else if ((appearanceRetained.mirror.changedFrequent & i) != 0 && renderAtom.renderMolecule.textureBin.attributeBin.soleUser) {
/*      */           
/* 2637 */           for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2638 */             renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2639 */             if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */               
/* 2642 */               AttributeBin attributeBin = renderAtom.renderMolecule.textureBin.attributeBin;
/* 2643 */               if ((attributeBin.onUpdateList & AttributeBin.ON_CHANGED_FREQUENT_UPDATE_LIST) == 0) {
/* 2644 */                 this.aBinUpdateList.add(attributeBin);
/* 2645 */                 attributeBin.onUpdateList |= AttributeBin.ON_CHANGED_FREQUENT_UPDATE_LIST;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/* 2651 */           for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2652 */             renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2653 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             {
/* 2655 */               EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 2656 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2657 */               reInsertAttributeBin(environmentSet, renderAtom);
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } 
/* 2663 */       } else if ((i & 0x3B1) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2672 */         if ((appearanceRetained.mirror.changedFrequent & i) != 0 && renderAtom.renderMolecule.soleUser) {
/*      */           
/* 2674 */           for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2675 */             renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2676 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             {
/*      */               
/* 2679 */               if ((renderAtom.renderMolecule.soleUserCompDirty & 0x3B1) == 0) {
/* 2680 */                 this.rmUpdateList.add(renderAtom.renderMolecule);
/*      */               }
/* 2682 */               renderAtom.renderMolecule.soleUserCompDirty |= i;
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 2688 */           for (b1 = b2; b1 < arrayOfGeometryAtom.length; b1++) {
/* 2689 */             renderAtom = arrayOfGeometryAtom[b1].getRenderAtom(this.view);
/* 2690 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             {
/* 2692 */               TextureBin textureBin = renderAtom.renderMolecule.textureBin;
/* 2693 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2694 */               reInsertRenderAtom(textureBin, renderAtom);
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/* 2701 */     } else if ((i & 0x40) != 0) {
/*      */       
/* 2703 */       this.visGAIsDirty = true;
/* 2704 */       this.visQuery = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processModelClipChanged(Object[] paramArrayOfObject) {
/* 2713 */     ModelClipRetained modelClipRetained = (ModelClipRetained)paramArrayOfObject[0];
/*      */ 
/*      */     
/* 2716 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */     
/* 2718 */     if ((i & 0x70) != 0) {
/*      */ 
/*      */       
/* 2721 */       this.envDirty |= REEVALUATE_MCLIP;
/*      */     }
/* 2723 */     else if ((i & 0xC) != 0) {
/*      */ 
/*      */       
/* 2726 */       if (!this.changedModelClips.contains(modelClipRetained.mirrorModelClip)) {
/* 2727 */         this.changedModelClips.add(modelClipRetained.mirrorModelClip);
/*      */       }
/*      */       
/* 2730 */       this.envDirty |= REEVALUATE_MCLIP;
/*      */     } else {
/*      */       
/* 2733 */       UnorderList unorderList = modelClipRetained.mirrorModelClip.environmentSets;
/* 2734 */       synchronized (unorderList) {
/* 2735 */         EnvironmentSet[] arrayOfEnvironmentSet = (EnvironmentSet[])unorderList.toArray(false);
/* 2736 */         int j = unorderList.size();
/* 2737 */         for (byte b = 0; b < j; b++) {
/* 2738 */           EnvironmentSet environmentSet = arrayOfEnvironmentSet[b];
/* 2739 */           environmentSet.canvasDirty |= 0x4000;
/* 2740 */           if (!environmentSet.onUpdateList) {
/* 2741 */             this.objUpdateList.add(environmentSet);
/* 2742 */             environmentSet.onUpdateList = true;
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
/*      */   void processBoundingLeafChanged(Object[] paramArrayOfObject, long paramLong) {
/* 2757 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[3];
/*      */ 
/*      */ 
/*      */     
/* 2761 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/* 2762 */       LeafRetained leafRetained = (LeafRetained)arrayOfObject[b];
/* 2763 */       switch (leafRetained.nodeType) {
/*      */         case 5:
/*      */         case 6:
/*      */         case 7:
/*      */         case 8:
/* 2768 */           if (this.universe.renderingEnvironmentStructure.isLightScopedToThisView(leafRetained, this.view))
/* 2769 */             this.envDirty |= REEVALUATE_LIGHTS; 
/*      */           break;
/*      */         case 3:
/*      */         case 4:
/* 2773 */           if (this.universe.renderingEnvironmentStructure.isFogScopedToThisView(leafRetained, this.view))
/* 2774 */             this.envDirty |= REEVALUATE_FOG; 
/*      */           break;
/*      */         case 1:
/* 2777 */           if (this.universe.renderingEnvironmentStructure.isBgScopedToThisView(leafRetained, this.view))
/* 2778 */             this.reEvaluateBg = true; 
/*      */           break;
/*      */         case 2:
/* 2781 */           if (this.universe.renderingEnvironmentStructure.isClipScopedToThisView(leafRetained, this.view))
/* 2782 */             this.reEvaluateClip = true; 
/*      */           break;
/*      */         case 26:
/* 2785 */           if (this.universe.renderingEnvironmentStructure.isMclipScopedToThisView(leafRetained, this.view))
/* 2786 */             this.envDirty |= REEVALUATE_MCLIP; 
/*      */           break;
/*      */         case 27:
/* 2789 */           if (this.universe.renderingEnvironmentStructure.isAltAppScopedToThisView(leafRetained, this.view)) this.altAppearanceDirty = true;
/*      */           
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processOrientedShape3DChanged(Object[] paramArrayOfObject) {
/* 2801 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 2802 */       RenderAtom renderAtom = ((GeometryAtom)paramArrayOfObject[b]).getRenderAtom(this.view);
/* 2803 */       if (renderAtom != null && renderAtom.inRenderBin() && !renderAtom.inDirtyOrientedRAs()) {
/* 2804 */         this.dirtyOrientedRAs.add(renderAtom);
/* 2805 */         renderAtom.dirtyMask |= RenderAtom.IN_DIRTY_ORIENTED_RAs;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void processShapeChanged(Object[] paramArrayOfObject, long paramLong) {
/* 2813 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2819 */     if ((i & 0x2) != 0) {
/* 2820 */       GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[4];
/* 2821 */       if (arrayOfGeometryAtom.length > 0)
/* 2822 */         if (!(arrayOfGeometryAtom[0]).source.appearanceOverrideEnable) {
/* 2823 */           for (byte b = 0; b < arrayOfGeometryAtom.length; b++) {
/* 2824 */             RenderAtom renderAtom = arrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2825 */             if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */               
/* 2828 */               renderAtom.app = renderAtom.geometryAtom.source.appearance;
/* 2829 */               EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 2830 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2831 */               reInsertAttributeBin(environmentSet, renderAtom);
/*      */             } 
/*      */           } 
/*      */         } else {
/* 2835 */           for (byte b = 0; b < arrayOfGeometryAtom.length; b++) {
/* 2836 */             RenderAtom renderAtom = arrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2837 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             {
/*      */ 
/*      */               
/* 2841 */               if (renderAtom.app != renderAtom.geometryAtom.source.otherAppearance) {
/*      */                 
/* 2843 */                 renderAtom.app = renderAtom.geometryAtom.source.appearance;
/* 2844 */                 EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 2845 */                 renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2846 */                 reInsertAttributeBin(environmentSet, renderAtom);
/*      */               } 
/*      */             }
/*      */           } 
/*      */         }  
/* 2851 */     } else if ((i & true) != 0) {
/* 2852 */       processDataChanged((Object[])paramArrayOfObject[2], (Object[])paramArrayOfObject[3], paramLong);
/*      */     }
/* 2854 */     else if ((i & 0x10) != 0) {
/* 2855 */       AppearanceRetained appearanceRetained = null;
/* 2856 */       Shape3DRetained shape3DRetained = null;
/* 2857 */       GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[4];
/*      */       
/* 2859 */       for (byte b = 0; b < arrayOfGeometryAtom.length; b++) {
/* 2860 */         RenderAtom renderAtom = arrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2861 */         if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */           AppearanceRetained appearanceRetained1;
/*      */ 
/*      */ 
/*      */           
/* 2866 */           if (shape3DRetained != renderAtom.geometryAtom.source) {
/* 2867 */             shape3DRetained = renderAtom.geometryAtom.source;
/* 2868 */             if (renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/* 2869 */               Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/* 2870 */               shape3DRetained.otherAppearance = (AppearanceRetained)arrayOfObject[1];
/* 2871 */               if (arrayOfObject[false] == Boolean.TRUE) {
/* 2872 */                 appearanceRetained1 = (AppearanceRetained)arrayOfObject[1];
/* 2873 */                 if (appearanceRetained1 != null) {
/* 2874 */                   appearanceRetained1.sgApp.addAMirrorUser(shape3DRetained);
/*      */                 }
/*      */               } else {
/*      */                 
/* 2878 */                 appearanceRetained1 = renderAtom.geometryAtom.source.appearance;
/*      */               }
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 2884 */               if (renderAtom.app == shape3DRetained.otherAppearance && renderAtom.app != null)
/*      */               {
/* 2886 */                 renderAtom.app.sgApp.removeAMirrorUser(shape3DRetained);
/*      */               }
/* 2888 */               appearanceRetained1 = renderAtom.geometryAtom.source.appearance;
/* 2889 */               shape3DRetained.otherAppearance = null;
/*      */             } 
/* 2891 */             appearanceRetained = appearanceRetained1;
/*      */           } else {
/*      */             
/* 2894 */             appearanceRetained1 = appearanceRetained;
/*      */           } 
/* 2896 */           renderAtom.app = appearanceRetained1;
/* 2897 */           EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 2898 */           renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2899 */           reInsertAttributeBin(environmentSet, renderAtom);
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
/*      */   void processDataChanged(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, long paramLong) {
/* 2917 */     ArrayList arrayList = new ArrayList(5);
/*      */ 
/*      */ 
/*      */     
/* 2921 */     for (byte b = 0; b < paramArrayOfObject1.length; b++) {
/* 2922 */       GeometryAtom geometryAtom = (GeometryAtom)paramArrayOfObject1[b];
/*      */ 
/*      */       
/* 2925 */       GeometryRetained geometryRetained = null;
/* 2926 */       for (byte b1 = 0; b1 < geometryAtom.geometryArray.length && geometryRetained == null; b1++) {
/* 2927 */         geometryRetained = geometryAtom.geometryArray[b1];
/*      */       }
/* 2929 */       if (geometryRetained != null) {
/*      */ 
/*      */ 
/*      */         
/* 2933 */         RenderAtom renderAtom = geometryAtom.getRenderAtom(this.view);
/*      */         
/* 2935 */         if (renderAtom != null && renderAtom.inRenderBin()) {
/* 2936 */           this.renderAtoms.remove(this.renderAtoms.indexOf(renderAtom));
/* 2937 */           removeARenderAtom(renderAtom);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2941 */     this.visQuery = true;
/* 2942 */     this.visGAIsDirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void processMorphChanged(Object[] paramArrayOfObject, long paramLong) {
/* 2948 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2954 */     if ((i & 0x2) != 0) {
/* 2955 */       GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[4];
/* 2956 */       if (arrayOfGeometryAtom.length > 0)
/* 2957 */         if (!(arrayOfGeometryAtom[0]).source.appearanceOverrideEnable) {
/* 2958 */           for (byte b = 0; b < arrayOfGeometryAtom.length; b++) {
/* 2959 */             RenderAtom renderAtom = arrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2960 */             if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */               
/* 2963 */               renderAtom.app = renderAtom.geometryAtom.source.appearance;
/* 2964 */               EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 2965 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2966 */               reInsertAttributeBin(environmentSet, renderAtom);
/*      */             } 
/*      */           } 
/*      */         } else {
/* 2970 */           for (byte b = 0; b < arrayOfGeometryAtom.length; b++) {
/* 2971 */             RenderAtom renderAtom = arrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2972 */             if (renderAtom != null && renderAtom.inRenderBin())
/*      */             {
/*      */ 
/*      */               
/* 2976 */               if (renderAtom.app != renderAtom.geometryAtom.source.otherAppearance) {
/*      */                 
/* 2978 */                 renderAtom.app = renderAtom.geometryAtom.source.appearance;
/* 2979 */                 EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 2980 */                 renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 2981 */                 reInsertAttributeBin(environmentSet, renderAtom);
/*      */               } 
/*      */             }
/*      */           } 
/*      */         }  
/* 2986 */     } else if ((i & 0x10) != 0) {
/* 2987 */       AppearanceRetained appearanceRetained = null;
/* 2988 */       Shape3DRetained shape3DRetained = null;
/* 2989 */       GeometryAtom[] arrayOfGeometryAtom = (GeometryAtom[])paramArrayOfObject[4];
/*      */ 
/*      */       
/* 2992 */       for (byte b = 0; b < arrayOfGeometryAtom.length; b++) {
/* 2993 */         RenderAtom renderAtom = arrayOfGeometryAtom[b].getRenderAtom(this.view);
/* 2994 */         if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */           AppearanceRetained appearanceRetained1;
/*      */ 
/*      */ 
/*      */           
/* 2999 */           if (shape3DRetained != renderAtom.geometryAtom.source) {
/* 3000 */             shape3DRetained = renderAtom.geometryAtom.source;
/* 3001 */             if (renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/* 3002 */               Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/* 3003 */               shape3DRetained.otherAppearance = (AppearanceRetained)arrayOfObject[1];
/* 3004 */               if (arrayOfObject[false] == Boolean.TRUE) {
/* 3005 */                 appearanceRetained1 = (AppearanceRetained)arrayOfObject[1];
/* 3006 */                 if (appearanceRetained1 != null) {
/* 3007 */                   appearanceRetained1.sgApp.addAMirrorUser(shape3DRetained);
/*      */                 }
/*      */               } else {
/*      */                 
/* 3011 */                 appearanceRetained1 = renderAtom.geometryAtom.source.appearance;
/*      */               }
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 3017 */               if (renderAtom.app == shape3DRetained.otherAppearance && renderAtom.app != null)
/*      */               {
/* 3019 */                 renderAtom.app.sgApp.removeAMirrorUser(shape3DRetained);
/*      */               }
/* 3021 */               appearanceRetained1 = renderAtom.geometryAtom.source.appearance;
/* 3022 */               shape3DRetained.otherAppearance = null;
/*      */             } 
/* 3024 */             appearanceRetained = appearanceRetained1;
/*      */           } else {
/*      */             
/* 3027 */             appearanceRetained1 = appearanceRetained;
/*      */           } 
/* 3029 */           renderAtom.app = appearanceRetained1;
/* 3030 */           EnvironmentSet environmentSet = renderAtom.renderMolecule.textureBin.environmentSet;
/* 3031 */           renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 3032 */           reInsertAttributeBin(environmentSet, renderAtom);
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
/*      */   void updateViewPlatform(ViewPlatformRetained paramViewPlatformRetained, float paramFloat) {
/* 3045 */     Object object = null;
/* 3046 */     ViewPlatform viewPlatform = this.view.getViewPlatform();
/* 3047 */     if (viewPlatform != null && (ViewPlatformRetained)viewPlatform.retained == paramViewPlatformRetained) {
/* 3048 */       this.vpcToVworld = paramViewPlatformRetained.getCurrentLocalToVworld(null);
/* 3049 */       this.vpcToVworldDirty = true;
/* 3050 */       synchronized (paramViewPlatformRetained) {
/* 3051 */         paramViewPlatformRetained.vprDirtyMask |= 0x20000;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3057 */       this.vpSchedSphereInVworld = paramViewPlatformRetained.schedSphere;
/* 3058 */       this.reEvaluateBg = true;
/* 3059 */       this.reEvaluateClip = true;
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
/*      */   void processGeometryAtomsChanged(Object[] paramArrayOfObject) {
/* 3073 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 3074 */       RenderAtom renderAtom = ((GeometryAtom)paramArrayOfObject[b]).getRenderAtom(this.view);
/* 3075 */       if (renderAtom != null && renderAtom.inRenderBin()) {
/* 3076 */         this.renderAtoms.remove(this.renderAtoms.indexOf(renderAtom));
/* 3077 */         removeARenderAtom(renderAtom);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processGeometryChanged(Object[] paramArrayOfObject) {
/* 3088 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[0];
/*      */     
/* 3090 */     GeometryRetained geometryRetained = (GeometryRetained)paramArrayOfObject[1];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3095 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/* 3096 */       GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject[b];
/* 3097 */       RenderAtom renderAtom = geometryAtom.getRenderAtom(this.view);
/* 3098 */       if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3105 */         byte b1 = 0;
/* 3106 */         for (b1 = 0; b1 < renderAtom.rListInfo.length && 
/* 3107 */           geometryRetained != renderAtom.rListInfo[b1].geometry(); b1++);
/*      */ 
/*      */         
/* 3110 */         RenderAtomListInfo renderAtomListInfo = renderAtom.rListInfo[b1];
/* 3111 */         if ((renderAtomListInfo.groupType & RenderAtom.DLIST) != 0) {
/* 3112 */           addDirtyRenderMolecule(renderAtomListInfo.renderAtom.renderMolecule);
/*      */         }
/* 3114 */         if ((renderAtomListInfo.groupType & RenderAtom.SEPARATE_DLIST_PER_RINFO) != 0) {
/* 3115 */           this.addDlistPerRinfo.add(renderAtomListInfo);
/*      */         }
/*      */         
/* 3118 */         if ((renderAtomListInfo.groupType & RenderAtom.SEPARATE_DLIST_PER_GEO) != 0) {
/* 3119 */           addGeometryDlist(renderAtomListInfo);
/*      */         }
/*      */         
/* 3122 */         if (geometryRetained instanceof RasterRetained) {
/* 3123 */           Object[] arrayOfObject1 = (Object[])paramArrayOfObject[2];
/* 3124 */           Texture2DRetained texture2DRetained1 = (Texture2DRetained)arrayOfObject1[0];
/* 3125 */           Texture2DRetained texture2DRetained2 = (Texture2DRetained)arrayOfObject1[1];
/*      */           
/* 3127 */           RasterRetained rasterRetained = (RasterRetained)renderAtomListInfo.geometry();
/* 3128 */           if (texture2DRetained1 != null) {
/* 3129 */             addTextureResourceFreeList(texture2DRetained1);
/* 3130 */             ImageComponentRetained imageComponentRetained = texture2DRetained1.images[0][0];
/* 3131 */             if (imageComponentRetained != null) {
/* 3132 */               removeNodeComponent(imageComponentRetained);
/*      */             }
/*      */           } 
/* 3135 */           if (texture2DRetained2 != null) {
/* 3136 */             ImageComponentRetained imageComponentRetained = texture2DRetained2.images[0][0];
/* 3137 */             if (imageComponentRetained != null) {
/* 3138 */               addNodeComponent(imageComponentRetained);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 3148 */   void addTextureBin(TextureBin paramTextureBin) { this.textureBinList.add(paramTextureBin); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3153 */   void removeTextureBin(TextureBin paramTextureBin) { this.textureBinList.remove(paramTextureBin); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addDirtyRenderMolecule(RenderMolecule paramRenderMolecule) {
/* 3159 */     if ((paramRenderMolecule.onUpdateList & RenderMolecule.IN_DIRTY_RENDERMOLECULE_LIST) == 0) {
/* 3160 */       if (paramRenderMolecule.onUpdateList == 0) {
/* 3161 */         this.objUpdateList.add(paramRenderMolecule);
/*      */       }
/* 3163 */       paramRenderMolecule.onUpdateList |= RenderMolecule.IN_DIRTY_RENDERMOLECULE_LIST;
/* 3164 */       this.dirtyRenderMoleculeList.add(paramRenderMolecule);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeDirtyRenderMolecule(RenderMolecule paramRenderMolecule) {
/* 3172 */     if ((paramRenderMolecule.onUpdateList & RenderMolecule.IN_DIRTY_RENDERMOLECULE_LIST) != 0) {
/* 3173 */       paramRenderMolecule.onUpdateList &= (RenderMolecule.IN_DIRTY_RENDERMOLECULE_LIST ^ 0xFFFFFFFF);
/* 3174 */       if (paramRenderMolecule.onUpdateList == 0) {
/* 3175 */         this.objUpdateList.remove(paramRenderMolecule);
/*      */       }
/* 3177 */       this.dirtyRenderMoleculeList.remove(this.dirtyRenderMoleculeList.indexOf(paramRenderMolecule));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateDirtyDisplayLists(Canvas3D paramCanvas3D, ArrayList paramArrayList1, ArrayList paramArrayList2, ArrayList paramArrayList3, boolean paramBoolean) {
/*      */     long l;
/*      */     Context context;
/*      */     int j;
/* 3188 */     if (paramBoolean) {
/* 3189 */       context = paramCanvas3D.screen.renderer.sharedCtx;
/* 3190 */       paramCanvas3D.makeCtxCurrent(context);
/* 3191 */       j = paramCanvas3D.screen.renderer.rendererBit;
/* 3192 */       l = paramCanvas3D.screen.renderer.sharedCtxTimeStamp;
/*      */     } else {
/* 3194 */       context = paramCanvas3D.ctx;
/* 3195 */       j = paramCanvas3D.canvasBit;
/* 3196 */       l = paramCanvas3D.ctxTimeStamp;
/*      */     } 
/*      */     
/* 3199 */     int i = paramArrayList1.size();
/*      */     
/* 3201 */     if (i > 0) {
/* 3202 */       for (int k = i - 1; k >= 0; k--) {
/* 3203 */         RenderMolecule renderMolecule = (RenderMolecule)paramArrayList1.get(k);
/* 3204 */         renderMolecule.updateDisplayList(paramCanvas3D);
/*      */       } 
/* 3206 */       paramArrayList1.clear();
/*      */     } 
/*      */     
/* 3209 */     i = paramArrayList2.size();
/*      */     
/* 3211 */     if (i > 0) {
/* 3212 */       for (int k = i - 1; k >= 0; k--) {
/* 3213 */         Object[] arrayOfObject = (Object[])paramArrayList2.get(k);
/* 3214 */         this.dlistRenderMethod.buildDlistPerRinfo((RenderAtomListInfo)arrayOfObject[0], (RenderMolecule)arrayOfObject[1], paramCanvas3D);
/*      */       } 
/* 3216 */       paramArrayList2.clear();
/*      */     } 
/*      */     
/* 3219 */     i = paramArrayList3.size();
/* 3220 */     if (i > 0) {
/*      */       int k;
/*      */ 
/*      */       
/* 3224 */       for (k = i - 1; k >= 0; k--) {
/* 3225 */         RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)paramArrayList3.get(k);
/* 3226 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 3227 */         geometryArrayRetained.resourceCreationMask &= (j ^ 0xFFFFFFFF);
/*      */       } 
/*      */       
/* 3230 */       for (k = i - 1; k >= 0; k--) {
/* 3231 */         RenderAtomListInfo renderAtomListInfo = (RenderAtomListInfo)paramArrayList3.get(k);
/* 3232 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 3233 */         if ((geometryArrayRetained.resourceCreationMask & j) == 0) {
/* 3234 */           this.dlistRenderMethod.buildIndividualDisplayList(renderAtomListInfo, paramCanvas3D, context);
/* 3235 */           geometryArrayRetained.resourceCreationMask |= j;
/* 3236 */           geometryArrayRetained.setDlistTimeStamp(j, l);
/*      */         } 
/*      */       } 
/* 3239 */       paramArrayList3.clear();
/*      */     } 
/*      */     
/* 3242 */     if (paramBoolean) {
/* 3243 */       paramCanvas3D.makeCtxCurrent(paramCanvas3D.ctx);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void removeRenderMolecule(RenderMolecule paramRenderMolecule) {
/* 3249 */     if ((paramRenderMolecule.primaryMoleculeType & (RenderMolecule.DLIST_MOLECULE | RenderMolecule.SEPARATE_DLIST_PER_RINFO_MOLECULE)) != 0) {
/* 3250 */       this.renderMoleculeList.remove(paramRenderMolecule);
/*      */     }
/*      */   }
/*      */   
/*      */   void updateAllRenderMolecule(Canvas3D paramCanvas3D) {
/* 3255 */     int i = this.renderMoleculeList.size();
/*      */     
/* 3257 */     if (i > 0) {
/* 3258 */       RenderMolecule[] arrayOfRenderMolecule = (RenderMolecule[])this.renderMoleculeList.toArray(false);
/*      */       
/* 3260 */       for (int j = i - 1; j >= 0; j--) {
/* 3261 */         arrayOfRenderMolecule[j].updateAllPrimaryDisplayLists(paramCanvas3D);
/*      */       }
/*      */     } 
/*      */     
/* 3265 */     i = this.sharedDList.size();
/* 3266 */     if (i > 0) {
/*      */ 
/*      */       
/* 3269 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 3270 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.sharedDList.toArray(arrayOfRenderAtomListInfo);
/* 3271 */       int k = paramCanvas3D.canvasBit;
/*      */ 
/*      */ 
/*      */       
/*      */       int j;
/*      */ 
/*      */       
/* 3278 */       for (j = i - 1; j >= 0; j--) {
/* 3279 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)arrayOfRenderAtomListInfo[j].geometry();
/* 3280 */         geometryArrayRetained.resourceCreationMask &= (k ^ 0xFFFFFFFF);
/*      */       } 
/*      */       
/* 3283 */       for (j = i - 1; j >= 0; j--) {
/* 3284 */         RenderAtomListInfo renderAtomListInfo = arrayOfRenderAtomListInfo[j];
/* 3285 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 3286 */         if ((geometryArrayRetained.resourceCreationMask & k) == 0) {
/* 3287 */           this.dlistRenderMethod.buildIndividualDisplayList(renderAtomListInfo, paramCanvas3D, paramCanvas3D.ctx);
/* 3288 */           geometryArrayRetained.resourceCreationMask |= k;
/* 3289 */           geometryArrayRetained.setDlistTimeStamp(k, paramCanvas3D.ctxTimeStamp);
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
/*      */   void updateAllRenderMolecule(Renderer paramRenderer, Canvas3D paramCanvas3D) {
/* 3301 */     boolean bool = false;
/*      */     
/* 3303 */     int i = this.renderMoleculeList.size();
/*      */     
/* 3305 */     if (i > 0) {
/* 3306 */       RenderMolecule[] arrayOfRenderMolecule = (RenderMolecule[])this.renderMoleculeList.toArray(false);
/*      */ 
/*      */       
/* 3309 */       paramCanvas3D.makeCtxCurrent(paramRenderer.sharedCtx);
/* 3310 */       bool = true;
/* 3311 */       for (int j = i - 1; j >= 0; j--) {
/* 3312 */         arrayOfRenderMolecule[j].updateAllPrimaryDisplayLists(paramCanvas3D);
/*      */       }
/*      */     } 
/*      */     
/* 3316 */     i = this.sharedDList.size();
/* 3317 */     if (i > 0) {
/* 3318 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 3319 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.sharedDList.toArray(arrayOfRenderAtomListInfo);
/*      */ 
/*      */       
/* 3322 */       if (!bool) {
/* 3323 */         paramCanvas3D.makeCtxCurrent(paramRenderer.sharedCtx);
/* 3324 */         bool = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3330 */       int k = paramCanvas3D.screen.renderer.rendererBit;
/* 3331 */       long l = paramCanvas3D.screen.renderer.sharedCtxTimeStamp;
/*      */       int j;
/* 3333 */       for (j = i - 1; j >= 0; j--) {
/* 3334 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)arrayOfRenderAtomListInfo[j].geometry();
/* 3335 */         geometryArrayRetained.resourceCreationMask &= (k ^ 0xFFFFFFFF);
/*      */       } 
/*      */       
/* 3338 */       for (j = i - 1; j >= 0; j--) {
/* 3339 */         RenderAtomListInfo renderAtomListInfo = arrayOfRenderAtomListInfo[j];
/* 3340 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 3341 */         if ((geometryArrayRetained.resourceCreationMask & k) == 0) {
/* 3342 */           this.dlistRenderMethod.buildIndividualDisplayList(renderAtomListInfo, paramCanvas3D, paramRenderer.sharedCtx);
/*      */           
/* 3344 */           geometryArrayRetained.resourceCreationMask |= k;
/* 3345 */           geometryArrayRetained.setDlistTimeStamp(k, l);
/*      */         } 
/*      */       } 
/*      */     } 
/* 3349 */     if (bool) {
/* 3350 */       paramCanvas3D.makeCtxCurrent(paramCanvas3D.ctx);
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
/*      */   private void processText3DTransformChanged(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, long paramLong) {
/* 3362 */     if (paramArrayOfObject2.length != 0) {
/* 3363 */       int i = paramArrayOfObject1.length;
/* 3364 */       for (byte b = 0; b < i; b++) {
/*      */         
/* 3366 */         GeometryAtom geometryAtom = (GeometryAtom)paramArrayOfObject1[b];
/* 3367 */         RenderAtom renderAtom = geometryAtom.getRenderAtom(this.view);
/* 3368 */         if (renderAtom != null && renderAtom.inRenderBin())
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3375 */           for (byte b1 = 0; b1 < paramArrayOfObject2.length; b1++) {
/*      */             
/* 3377 */             geometryAtom.lastLocalTransformArray[b1] = (Transform3D)paramArrayOfObject2[b1];
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3382 */             for (byte b2 = 0; b2 < renderAtom.rListInfo.length; b2++) {
/* 3383 */               if ((renderAtom.rListInfo[b2]).localToVworld == null) {
/* 3384 */                 (renderAtom.rListInfo[b2]).localToVworld = new Transform3D();
/*      */               }
/*      */             } 
/*      */             
/* 3388 */             if (renderAtom.isOriented() && !renderAtom.inDirtyOrientedRAs()) {
/* 3389 */               this.dirtyOrientedRAs.add(renderAtom);
/* 3390 */               renderAtom.dirtyMask |= RenderAtom.IN_DIRTY_ORIENTED_RAs;
/* 3391 */             } else if (!renderAtom.onUpdateList()) {
/* 3392 */               renderAtom.dirtyMask |= RenderAtom.ON_UPDATELIST;
/* 3393 */               this.objUpdateList.add(renderAtom);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void processOrderedGroupRemoved(J3dMessage paramJ3dMessage) {
/* 3403 */     Object[] arrayOfObject1 = (Object[])paramJ3dMessage.args[0];
/* 3404 */     Object[] arrayOfObject2 = (Object[])paramJ3dMessage.args[1];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3409 */     OrderedChildInfo orderedChildInfo = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3418 */     for (byte b = 0; b < arrayOfObject1.length; b++) {
/* 3419 */       OrderedGroupRetained orderedGroupRetained = (OrderedGroupRetained)arrayOfObject1[b];
/* 3420 */       int i = ((Integer)arrayOfObject2[b]).intValue();
/*      */       
/* 3422 */       OrderedBin orderedBin = orderedGroupRetained.getOrderedBin(this.view.viewIndex);
/*      */       
/* 3424 */       if (orderedBin != null) {
/*      */ 
/*      */         
/* 3427 */         orderedChildInfo = new OrderedChildInfo(OrderedChildInfo.REMOVE, i, -1, null);
/* 3428 */         orderedBin.addChildInfo(orderedChildInfo);
/*      */         
/* 3430 */         if (!orderedBin.onUpdateList) {
/* 3431 */           this.obList.add(orderedBin);
/* 3432 */           orderedBin.onUpdateList = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void processOrderedGroupInserted(J3dMessage paramJ3dMessage) {
/* 3441 */     Object[] arrayOfObject1 = (Object[])paramJ3dMessage.args[0];
/* 3442 */     Object[] arrayOfObject2 = (Object[])paramJ3dMessage.args[1];
/* 3443 */     Object[] arrayOfObject3 = (Object[])paramJ3dMessage.args[2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3455 */     if (arrayOfObject1 == null) {
/*      */       return;
/*      */     }
/* 3458 */     for (byte b = 0; b < arrayOfObject1.length; b++) {
/* 3459 */       OrderedGroupRetained orderedGroupRetained = (OrderedGroupRetained)arrayOfObject1[b];
/* 3460 */       int i = ((Integer)arrayOfObject2[b]).intValue();
/* 3461 */       int j = ((Integer)arrayOfObject3[b]).intValue();
/* 3462 */       OrderedBin orderedBin = orderedGroupRetained.getOrderedBin(this.view.viewIndex);
/* 3463 */       OrderedChildInfo orderedChildInfo = null;
/*      */ 
/*      */       
/* 3466 */       if (orderedBin != null) {
/*      */         
/* 3468 */         orderedChildInfo = new OrderedChildInfo(OrderedChildInfo.ADD, i, j, null);
/* 3469 */         orderedBin.addChildInfo(orderedChildInfo);
/*      */         
/* 3471 */         if (!orderedBin.onUpdateList) {
/* 3472 */           this.obList.add(orderedBin);
/* 3473 */           orderedBin.onUpdateList = true;
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
/*      */   
/*      */   private void processTransformChanged(long paramLong) {
/* 3493 */     this.targets = this.universe.transformStructure.getTargetList();
/*      */ 
/*      */     
/* 3496 */     UnorderList unorderList = this.targets.targetList[0];
/* 3497 */     if (unorderList != null) {
/*      */       
/* 3499 */       int i = unorderList.size();
/* 3500 */       Object[] arrayOfObject = unorderList.toArray(false);
/*      */ 
/*      */       
/* 3503 */       for (byte b = 0; b < i; b++) {
/* 3504 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/*      */         
/* 3506 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*      */           
/* 3508 */           GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject1[b1];
/*      */           
/* 3510 */           RenderAtom renderAtom = geometryAtom.getRenderAtom(this.view);
/* 3511 */           if (renderAtom != null && renderAtom.inRenderBin()) {
/*      */ 
/*      */             
/* 3514 */             RenderMolecule renderMolecule = renderAtom.renderMolecule;
/*      */             
/* 3516 */             if (renderMolecule != null && renderMolecule.renderBin == this) {
/*      */               AppearanceRetained appearanceRetained;
/* 3518 */               if (geometryAtom.source.inBackgroundGroup && (renderMolecule.onUpdateList & RenderMolecule.UPDATE_BACKGROUND_TRANSFORM) == 0) {
/*      */                 
/* 3520 */                 if (renderMolecule.onUpdateList == 0) {
/* 3521 */                   this.objUpdateList.add(renderMolecule);
/*      */                 }
/* 3523 */                 renderMolecule.onUpdateList |= RenderMolecule.UPDATE_BACKGROUND_TRANSFORM;
/*      */               } 
/*      */ 
/*      */               
/* 3527 */               LightRetained[] arrayOfLightRetained = this.universe.renderingEnvironmentStructure.getInfluencingLights(renderAtom, this.view);
/*      */               
/* 3529 */               FogRetained fogRetained = this.universe.renderingEnvironmentStructure.getInfluencingFog(renderAtom, this.view);
/*      */               
/* 3531 */               ModelClipRetained modelClipRetained = this.universe.renderingEnvironmentStructure.getInfluencingModelClip(renderAtom, this.view);
/*      */ 
/*      */               
/* 3534 */               if (renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/* 3535 */                 Object[] arrayOfObject2 = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/* 3536 */                 if (arrayOfObject2[false] == Boolean.TRUE) {
/* 3537 */                   appearanceRetained = (AppearanceRetained)arrayOfObject2[1];
/*      */                 } else {
/*      */                   
/* 3540 */                   appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */                 } 
/*      */               } else {
/*      */                 
/* 3544 */                 appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */               } 
/*      */               
/* 3547 */               if (renderAtom.envSet.equals(renderAtom, arrayOfLightRetained, fogRetained, modelClipRetained) && appearanceRetained == renderAtom.app) {
/*      */ 
/*      */                 
/* 3550 */                 if (renderAtom.hasSeparateLocaleVwcBounds() && !renderAtom.onLocaleVwcBoundsUpdateList()) {
/*      */                   
/* 3552 */                   renderAtom; renderAtom.dirtyMask |= RenderAtom.ON_LOCALE_VWC_BOUNDS_UPDATELIST;
/* 3553 */                   this.raLocaleVwcBoundsUpdateList.add(renderAtom);
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 3559 */                 if (this.locale != geometryAtom.source.locale) {
/* 3560 */                   if (renderMolecule.onUpdateList == 0) {
/* 3561 */                     this.objUpdateList.add(renderMolecule);
/*      */                   }
/* 3563 */                   renderMolecule.onUpdateList |= RenderMolecule.LOCALE_TRANSLATION;
/*      */                 } 
/*      */                 
/* 3566 */                 if ((renderMolecule.primaryMoleculeType & RenderMolecule.DLIST_MOLECULE) != 0) {
/* 3567 */                   if (renderMolecule.onUpdateList == 0) {
/* 3568 */                     this.objUpdateList.add(renderMolecule);
/*      */                   }
/* 3570 */                   renderMolecule.onUpdateList |= RenderMolecule.BOUNDS_RECOMPUTE_UPDATE;
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 }
/* 3576 */                 else if ((renderMolecule.primaryMoleculeType & RenderMolecule.TEXT3D_MOLECULE) != 0) {
/*      */                   
/* 3578 */                   if (!renderAtom.onUpdateList()) {
/* 3579 */                     renderAtom.dirtyMask |= RenderAtom.ON_UPDATELIST;
/* 3580 */                     this.objUpdateList.add(renderAtom);
/*      */                   } 
/*      */                 } 
/* 3583 */                 if (renderAtom.isOriented() && !renderAtom.inDirtyOrientedRAs()) {
/* 3584 */                   this.dirtyOrientedRAs.add(renderAtom);
/* 3585 */                   renderAtom.dirtyMask |= RenderAtom.IN_DIRTY_ORIENTED_RAs;
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/* 3590 */                 if (!renderAtom.renderMolecule.isOpaqueOrInOG && renderAtom.geometryAtom.source.geometryBackground == null && this.transpSortMode == 1 && !renderAtom.inDepthSortList())
/*      */                 {
/*      */                   
/* 3593 */                   renderAtom.geometryAtom.updateCentroid();
/*      */                   
/* 3595 */                   if (this.dirtyDepthSortRenderAtom.add(renderAtom)) {
/* 3596 */                     this.numDirtyTinfo += renderAtom.rListInfo.length;
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 3603 */                   renderAtom.dirtyMask |= RenderAtom.IN_SORTED_POS_DIRTY_TRANSP_LIST;
/*      */                 }
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 3609 */                 if (renderAtom.app != appearanceRetained && 
/* 3610 */                   renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/*      */                   
/* 3612 */                   if (renderAtom.app == renderAtom.geometryAtom.source.otherAppearance && 
/* 3613 */                     renderAtom.app != null) {
/*      */                     
/* 3615 */                     renderAtom.geometryAtom.source.otherAppearance.sgApp.removeAMirrorUser(renderAtom.geometryAtom.source);
/* 3616 */                     renderAtom.geometryAtom.source.otherAppearance = null;
/*      */                   } 
/*      */ 
/*      */ 
/*      */                   
/* 3621 */                   if (appearanceRetained != renderAtom.geometryAtom.source.appearance)
/*      */                   {
/*      */ 
/*      */ 
/*      */                     
/* 3626 */                     if (appearanceRetained != null && appearanceRetained != renderAtom.geometryAtom.source.otherAppearance) {
/* 3627 */                       appearanceRetained.sgApp.addAMirrorUser(renderAtom.geometryAtom.source);
/* 3628 */                       renderAtom.geometryAtom.source.otherAppearance = appearanceRetained;
/*      */                     } 
/*      */                   }
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 3638 */                 getNewEnvironment(renderAtom, arrayOfLightRetained, fogRetained, modelClipRetained, appearanceRetained);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 3645 */     unorderList = this.targets.targetList[1];
/* 3646 */     if (unorderList != null) {
/* 3647 */       int i = unorderList.size();
/* 3648 */       Object[] arrayOfObject = unorderList.toArray(false);
/* 3649 */       for (byte b = 0; b < i; b++) {
/* 3650 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 3651 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*      */           
/* 3653 */           if (arrayOfObject1[b1] instanceof LightRetained && this.universe.renderingEnvironmentStructure.isLightScopedToThisView(arrayOfObject1[b1], this.view)) {
/* 3654 */             if (!this.changedLts.contains(arrayOfObject1[b1]))
/* 3655 */               this.changedLts.add(arrayOfObject1[b1]); 
/* 3656 */             this.envDirty |= REEVALUATE_LIGHTS;
/*      */           }
/* 3658 */           else if (arrayOfObject1[b1] instanceof ModelClipRetained && this.universe.renderingEnvironmentStructure.isMclipScopedToThisView(arrayOfObject1[b1], this.view)) {
/* 3659 */             if (!this.changedModelClips.contains(arrayOfObject1[b1]))
/* 3660 */               this.changedModelClips.add(arrayOfObject1[b1]); 
/* 3661 */             this.envDirty |= REEVALUATE_MCLIP;
/*      */           }
/* 3663 */           else if (arrayOfObject1[b1] instanceof FogRetained && this.universe.renderingEnvironmentStructure.isFogScopedToThisView(arrayOfObject1[b1], this.view)) {
/* 3664 */             if (!this.changedFogs.contains(arrayOfObject1[b1]))
/* 3665 */               this.changedFogs.add(arrayOfObject1[b1]); 
/* 3666 */             this.envDirty |= REEVALUATE_FOG;
/*      */           }
/* 3668 */           else if (arrayOfObject1[b1] instanceof AlternateAppearanceRetained && this.universe.renderingEnvironmentStructure.isAltAppScopedToThisView(arrayOfObject1[b1], this.view)) {
/* 3669 */             this.altAppearanceDirty = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3676 */     unorderList = this.targets.targetList[4];
/* 3677 */     if (unorderList != null) {
/* 3678 */       int i = unorderList.size();
/* 3679 */       Object[] arrayOfObject = unorderList.toArray(false);
/* 3680 */       for (byte b = 0; b < i; b++) {
/* 3681 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 3682 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*      */           float f;
/* 3684 */           synchronized (arrayOfObject1[b1]) {
/* 3685 */             f = (float)((ViewPlatformRetained)arrayOfObject1[b1]).sphere.radius;
/*      */           } 
/* 3687 */           updateViewPlatform((ViewPlatformRetained)arrayOfObject1[b1], f);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3692 */     this.targets = null;
/*      */     
/* 3694 */     this.blUsers = this.universe.transformStructure.getBlUsers();
/* 3695 */     if (this.blUsers != null) {
/* 3696 */       int i = this.blUsers.size();
/* 3697 */       for (byte b = 0; b < i; b++) {
/* 3698 */         LeafRetained leafRetained = (LeafRetained)this.blUsers.get(b);
/* 3699 */         if (leafRetained instanceof LightRetained && this.universe.renderingEnvironmentStructure.isLightScopedToThisView(leafRetained, this.view)) {
/* 3700 */           this.envDirty |= REEVALUATE_LIGHTS;
/*      */         }
/* 3702 */         else if (leafRetained instanceof FogRetained && this.universe.renderingEnvironmentStructure.isFogScopedToThisView(leafRetained, this.view)) {
/* 3703 */           this.envDirty |= REEVALUATE_FOG;
/*      */         }
/* 3705 */         else if (leafRetained instanceof ModelClipRetained && this.universe.renderingEnvironmentStructure.isMclipScopedToThisView(leafRetained, this.view)) {
/* 3706 */           this.envDirty |= REEVALUATE_MCLIP;
/*      */         }
/* 3708 */         else if (leafRetained instanceof AlternateAppearanceRetained && this.universe.renderingEnvironmentStructure.isAltAppScopedToThisView(leafRetained, this.view)) {
/* 3709 */           this.altAppearanceDirty = true;
/*      */         } 
/*      */       } 
/* 3712 */       this.blUsers = null;
/*      */     } 
/*      */     
/* 3715 */     this.visQuery = true;
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
/*      */   private void processLightChanged() {
/* 3731 */     int i = this.lightMessageList.size();
/*      */     
/* 3733 */     for (byte b = 0; b < i; b++) {
/* 3734 */       J3dMessage j3dMessage = (J3dMessage)this.lightMessageList.get(b);
/* 3735 */       Object[] arrayOfObject = j3dMessage.args;
/* 3736 */       LightRetained[] arrayOfLightRetained = (LightRetained[])arrayOfObject[3];
/* 3737 */       int j = ((Integer)arrayOfObject[1]).intValue();
/* 3738 */       LightRetained lightRetained = (LightRetained)arrayOfObject[0];
/*      */ 
/*      */       
/* 3741 */       if ((j & 0x16) != 0) {
/*      */ 
/*      */         
/* 3744 */         this.envDirty |= REEVALUATE_LIGHTS;
/* 3745 */         j &= 0xFFFFFFE9;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3753 */       if (j != 0) {
/* 3754 */         if (lightRetained.nodeType == 5) {
/*      */ 
/*      */           
/* 3757 */           for (byte b1 = 0; b1 < arrayOfLightRetained.length; b1++) {
/* 3758 */             LightRetained lightRetained1 = arrayOfLightRetained[b1];
/* 3759 */             UnorderList unorderList = lightRetained1.environmentSets;
/* 3760 */             synchronized (unorderList) {
/* 3761 */               int k = unorderList.size();
/* 3762 */               if (k > 0) {
/* 3763 */                 EnvironmentSet[] arrayOfEnvironmentSet = (EnvironmentSet[])unorderList.toArray(false);
/* 3764 */                 for (byte b2 = 0; b2 < k; b2++) {
/* 3765 */                   EnvironmentSet environmentSet = arrayOfEnvironmentSet[b2];
/* 3766 */                   environmentSet.canvasDirty |= 0x100;
/* 3767 */                   if (!environmentSet.onUpdateList) {
/* 3768 */                     this.objUpdateList.add(environmentSet);
/* 3769 */                     environmentSet.onUpdateList = true;
/*      */                   }
/*      */                 
/*      */                 } 
/* 3773 */               } else if ((j & true) != 0) {
/* 3774 */                 boolean bool = lightRetained1.lightOn;
/* 3775 */                 if (bool) {
/* 3776 */                   if (!this.changedLts.contains(lightRetained1))
/* 3777 */                     this.changedLts.add(lightRetained1); 
/* 3778 */                   this.envDirty |= REEVALUATE_LIGHTS;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/* 3785 */           for (byte b1 = 0; b1 < arrayOfLightRetained.length; b1++) {
/* 3786 */             LightRetained lightRetained1 = arrayOfLightRetained[b1];
/* 3787 */             if ((j & true) != 0) {
/* 3788 */               boolean bool = ((Boolean)arrayOfObject[4]).booleanValue();
/* 3789 */               if (bool) {
/* 3790 */                 if (!this.changedLts.contains(lightRetained1)) {
/* 3791 */                   this.changedLts.add(lightRetained1);
/*      */                 }
/* 3793 */                 this.envDirty |= REEVALUATE_LIGHTS;
/*      */               } 
/*      */             } 
/* 3796 */             UnorderList unorderList = lightRetained1.environmentSets;
/*      */             
/* 3798 */             synchronized (unorderList) {
/* 3799 */               int k = unorderList.size();
/*      */               
/* 3801 */               if (k > 0) {
/* 3802 */                 EnvironmentSet[] arrayOfEnvironmentSet = (EnvironmentSet[])unorderList.toArray(false);
/* 3803 */                 if ((j & true) != 0) {
/* 3804 */                   boolean bool = ((Boolean)arrayOfObject[4]).booleanValue();
/* 3805 */                   for (byte b2 = 0; b2 < k; b2++) {
/* 3806 */                     EnvironmentSet environmentSet = arrayOfEnvironmentSet[b2];
/* 3807 */                     int n = environmentSet.lights.size();
/* 3808 */                     for (byte b3 = 0; b3 < n; b3++) {
/* 3809 */                       if (environmentSet.lights.get(b3) == lightRetained1) {
/* 3810 */                         if (bool == true) {
/* 3811 */                           environmentSet.enableMaskCache |= (1 << environmentSet.ltPos[b3]); break;
/*      */                         } 
/* 3813 */                         environmentSet.enableMaskCache &= (1 << environmentSet.ltPos[b3] ^ 0xFFFFFFFF);
/*      */                         break;
/*      */                       } 
/*      */                     } 
/* 3817 */                     environmentSet.canvasDirty |= 0x80;
/* 3818 */                     if (!environmentSet.onUpdateList) {
/* 3819 */                       this.objUpdateList.add(environmentSet);
/* 3820 */                       environmentSet.onUpdateList = true;
/*      */                     } 
/*      */                   } 
/*      */                 } else {
/* 3824 */                   for (byte b2 = 0; b2 < k; b2++) {
/* 3825 */                     EnvironmentSet environmentSet = arrayOfEnvironmentSet[b2];
/* 3826 */                     int n = environmentSet.lights.size();
/* 3827 */                     for (byte b3 = 0; b3 < n; b3++) {
/* 3828 */                       if (environmentSet.lights.get(b3) == lightRetained1) {
/* 3829 */                         environmentSet.lightBin.canvasDirty |= 0x40;
/* 3830 */                         environmentSet.lightBin.lightDirtyMaskCache |= 1 << environmentSet.ltPos[b3];
/* 3831 */                         if (!environmentSet.lightBin.onUpdateList) {
/* 3832 */                           environmentSet.lightBin.onUpdateList = true;
/* 3833 */                           this.objUpdateList.add(environmentSet.lightBin);
/*      */                         } 
/*      */                         break;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/* 3845 */       j3dMessage.decRefcount();
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
/*      */   void processGeometryAtom(GeometryAtom paramGeometryAtom, long paramLong) {
/* 3857 */     GeometryRetained geometryRetained = null;
/* 3858 */     for (byte b = 0; b < paramGeometryAtom.geometryArray.length && geometryRetained == null; b++) {
/* 3859 */       geometryRetained = paramGeometryAtom.geometryArray[b];
/*      */     }
/* 3861 */     if (geometryRetained == null) {
/*      */       return;
/*      */     }
/*      */     
/* 3865 */     RenderAtom renderAtom = paramGeometryAtom.getRenderAtom(this.view);
/*      */     
/* 3867 */     if (renderAtom != null) {
/* 3868 */       renderAtom.lastVisibleTime = paramLong;
/*      */     }
/*      */     
/* 3871 */     if (renderAtom == null || renderAtom.inRenderBin()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3879 */     if (renderAtom.geometryAtom.source.viewList != null) {
/* 3880 */       if (renderAtom.geometryAtom.source.viewList.contains(this.view))
/*      */       {
/*      */         
/* 3883 */         RenderMolecule renderMolecule = insertRenderAtom(renderAtom);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 3888 */       RenderMolecule renderMolecule = insertRenderAtom(renderAtom);
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
/*      */   private void processBgGeometryAtoms(GeometryAtom[] paramArrayOfGeometryAtom, long paramLong) {
/* 3903 */     for (byte b = 0; b < paramArrayOfGeometryAtom.length; b++) {
/* 3904 */       GeometryAtom geometryAtom = paramArrayOfGeometryAtom[b];
/*      */ 
/*      */       
/* 3907 */       GeometryRetained geometryRetained = null;
/* 3908 */       for (byte b1 = 0; b1 < geometryAtom.geometryArray.length && geometryRetained == null; b1++) {
/* 3909 */         geometryRetained = geometryAtom.geometryArray[b1];
/*      */       }
/* 3911 */       if (geometryRetained != null) {
/*      */ 
/*      */ 
/*      */         
/* 3915 */         RenderAtom renderAtom = geometryAtom.getRenderAtom(this.view);
/* 3916 */         if (renderAtom == null) {
/*      */           return;
/*      */         }
/* 3919 */         renderAtom.lastVisibleTime = paramLong;
/* 3920 */         if (!renderAtom.inRenderBin())
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3927 */           RenderMolecule renderMolecule = insertRenderAtom(renderAtom);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkForCompaction() {
/* 3938 */     int j = 0;
/* 3939 */     int k = 0;
/*      */ 
/*      */     
/* 3942 */     if (!VirtualUniverse.mc.doCompaction) {
/*      */       return;
/*      */     }
/*      */     
/* 3946 */     int i = this.renderAtoms.size();
/* 3947 */     for (byte b = 0; b < i; b++) {
/* 3948 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/*      */ 
/*      */       
/* 3951 */       if (renderAtom.lastVisibleTime < this.removeCutoffTime) {
/* 3952 */         j++;
/*      */       }
/*      */     } 
/*      */     
/* 3956 */     k = i - j;
/* 3957 */     if (k * 2 < j) {
/* 3958 */       compact();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3967 */   void setFrameCountCutoff(int paramInt) { this.frameCountCutoff = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void compact() {
/* 3977 */     for (byte b = 0; b < this.renderAtoms.size(); ) {
/* 3978 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/* 3979 */       if (renderAtom.lastVisibleTime < this.removeCutoffTime) {
/* 3980 */         this.renderAtoms.remove(b);
/* 3981 */         removeARenderAtom(renderAtom);
/*      */         continue;
/*      */       } 
/* 3984 */       b++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void reEvaluateAlternateAppearance() {
/* 3993 */     int i = this.renderAtoms.size();
/*      */     
/* 3995 */     for (byte b = 0; b < i; b++) {
/* 3996 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/* 3997 */       if (renderAtom.inRenderBin() && renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/*      */         AppearanceRetained appearanceRetained;
/*      */         
/* 4000 */         Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/*      */         
/* 4002 */         if (arrayOfObject[false] == Boolean.TRUE) {
/* 4003 */           appearanceRetained = (AppearanceRetained)arrayOfObject[1];
/*      */         } else {
/*      */           
/* 4006 */           appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */         } 
/*      */         
/* 4009 */         if (appearanceRetained != renderAtom.app) {
/*      */ 
/*      */           
/* 4012 */           if (renderAtom.geometryAtom.source.otherAppearance != appearanceRetained) {
/* 4013 */             if (renderAtom.geometryAtom.source.otherAppearance != null) {
/* 4014 */               renderAtom.geometryAtom.source.otherAppearance.sgApp.removeAMirrorUser(renderAtom.geometryAtom.source);
/*      */             }
/* 4016 */             if (appearanceRetained != renderAtom.geometryAtom.source.appearance) {
/* 4017 */               if (appearanceRetained != null) {
/* 4018 */                 appearanceRetained.sgApp.addAMirrorUser(renderAtom.geometryAtom.source);
/*      */               }
/* 4020 */               renderAtom.geometryAtom.source.otherAppearance = appearanceRetained;
/*      */             } else {
/*      */               
/* 4023 */               renderAtom.geometryAtom.source.otherAppearance = null;
/*      */             } 
/*      */           } 
/* 4026 */           renderAtom.app = appearanceRetained;
/* 4027 */           EnvironmentSet environmentSet = renderAtom.envSet;
/* 4028 */           renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 4029 */           reInsertAttributeBin(environmentSet, renderAtom);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void reEvaluateAllRenderAtoms(boolean paramBoolean) {
/* 4036 */     int i = this.renderAtoms.size();
/*      */     
/* 4038 */     for (byte b = 0; b < i; b++) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4043 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/*      */ 
/*      */       
/* 4046 */       if (renderAtom.inRenderBin()) {
/*      */         AppearanceRetained appearanceRetained;
/*      */         
/* 4049 */         LightRetained[] arrayOfLightRetained = this.universe.renderingEnvironmentStructure.getInfluencingLights(renderAtom, this.view);
/* 4050 */         FogRetained fogRetained = this.universe.renderingEnvironmentStructure.getInfluencingFog(renderAtom, this.view);
/* 4051 */         ModelClipRetained modelClipRetained = this.universe.renderingEnvironmentStructure.getInfluencingModelClip(renderAtom, this.view);
/*      */ 
/*      */         
/* 4054 */         if (paramBoolean) {
/* 4055 */           if (renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/* 4056 */             Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/* 4057 */             if (arrayOfObject[false] == Boolean.TRUE) {
/* 4058 */               appearanceRetained = (AppearanceRetained)arrayOfObject[1];
/*      */             } else {
/*      */               
/* 4061 */               appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 4066 */             appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */           } 
/*      */         } else {
/*      */           
/* 4070 */           appearanceRetained = renderAtom.app;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4077 */         if (!renderAtom.envSet.equals(renderAtom, arrayOfLightRetained, fogRetained, modelClipRetained) || appearanceRetained != renderAtom.app) {
/*      */ 
/*      */ 
/*      */           
/* 4081 */           if (paramBoolean && renderAtom.geometryAtom.source.appearanceOverrideEnable && 
/* 4082 */             appearanceRetained != renderAtom.app && 
/* 4083 */             renderAtom.geometryAtom.source.otherAppearance != appearanceRetained) {
/* 4084 */             if (renderAtom.geometryAtom.source.otherAppearance != null) {
/* 4085 */               renderAtom.geometryAtom.source.otherAppearance.sgApp.removeAMirrorUser(renderAtom.geometryAtom.source);
/*      */             }
/* 4087 */             if (appearanceRetained != renderAtom.geometryAtom.source.appearance) {
/* 4088 */               if (appearanceRetained != null) {
/* 4089 */                 appearanceRetained.sgApp.addAMirrorUser(renderAtom.geometryAtom.source);
/*      */               }
/* 4091 */               renderAtom.geometryAtom.source.otherAppearance = appearanceRetained;
/*      */             } else {
/*      */               
/* 4094 */               renderAtom.geometryAtom.source.otherAppearance = null;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 4099 */           getNewEnvironment(renderAtom, arrayOfLightRetained, fogRetained, modelClipRetained, appearanceRetained);
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
/*      */   private void getNewEnvironment(RenderAtom paramRenderAtom, LightRetained[] paramArrayOfLightRetained, FogRetained paramFogRetained, ModelClipRetained paramModelClipRetained, AppearanceRetained paramAppearanceRetained) {
/*      */     LightBin lightBin3, lightBin1;
/* 4112 */     EnvironmentSet environmentSet = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4118 */     OrderedCollection orderedCollection = null;
/*      */ 
/*      */ 
/*      */     
/* 4122 */     paramRenderAtom.renderMolecule.removeRenderAtom(paramRenderAtom);
/*      */     
/* 4124 */     environmentSet = null;
/* 4125 */     if (paramRenderAtom.geometryAtom.source.geometryBackground == null) {
/* 4126 */       if (paramRenderAtom.geometryAtom.source.orderedPath != null) {
/* 4127 */         orderedCollection = findOrderedCollection(paramRenderAtom.geometryAtom, false);
/* 4128 */         lightBin1 = orderedCollection.nextFrameLightBin;
/* 4129 */         lightBin3 = orderedCollection.addLightBins;
/*      */       } else {
/* 4131 */         lightBin3 = this.addOpaqueBin;
/* 4132 */         lightBin1 = this.opaqueBin;
/*      */       }
/*      */     
/* 4135 */     } else if (paramRenderAtom.geometryAtom.source.orderedPath != null) {
/* 4136 */       orderedCollection = findOrderedCollection(paramRenderAtom.geometryAtom, true);
/* 4137 */       lightBin1 = orderedCollection.nextFrameLightBin;
/* 4138 */       lightBin3 = orderedCollection.addLightBins;
/*      */     } else {
/* 4140 */       lightBin3 = this.bgAddOpaqueBin;
/* 4141 */       lightBin1 = this.bgOpaqueBin;
/*      */     } 
/*      */     
/* 4144 */     LightBin lightBin2 = lightBin1;
/*      */     
/* 4146 */     while (lightBin1 != null && environmentSet == null) {
/*      */ 
/*      */       
/* 4149 */       if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground) {
/*      */ 
/*      */         
/* 4152 */         EnvironmentSet environmentSet1 = lightBin1.environmentSetList;
/* 4153 */         while (environmentSet1 != null) {
/* 4154 */           if (environmentSet1.equals(paramRenderAtom, paramArrayOfLightRetained, paramFogRetained, paramModelClipRetained)) {
/* 4155 */             environmentSet = environmentSet1;
/*      */             break;
/*      */           } 
/* 4158 */           environmentSet1 = environmentSet1.next;
/*      */         } 
/*      */ 
/*      */         
/* 4162 */         if (environmentSet == null) {
/* 4163 */           int i = lightBin1.insertEnvSet.size();
/* 4164 */           for (byte b = 0; b < i; b++) {
/* 4165 */             EnvironmentSet environmentSet2 = (EnvironmentSet)lightBin1.insertEnvSet.get(b);
/* 4166 */             if (environmentSet2.equals(paramRenderAtom, paramArrayOfLightRetained, paramFogRetained, paramModelClipRetained)) {
/* 4167 */               environmentSet = environmentSet2;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 4173 */       lightBin1 = lightBin1.next;
/*      */     } 
/*      */ 
/*      */     
/* 4177 */     if (environmentSet == null) {
/* 4178 */       lightBin1 = lightBin3;
/* 4179 */       while (lightBin1 != null) {
/*      */ 
/*      */         
/* 4182 */         if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground) {
/*      */ 
/*      */ 
/*      */           
/* 4186 */           int i = lightBin1.insertEnvSet.size();
/* 4187 */           for (byte b = 0; b < i; b++) {
/* 4188 */             EnvironmentSet environmentSet1 = (EnvironmentSet)lightBin1.insertEnvSet.get(b);
/* 4189 */             if (environmentSet1.equals(paramRenderAtom, paramArrayOfLightRetained, paramFogRetained, paramModelClipRetained)) {
/* 4190 */               environmentSet = environmentSet1;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/* 4195 */         lightBin1 = lightBin1.next;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 4200 */     if (environmentSet == null) {
/*      */       
/* 4202 */       EnvironmentSet environmentSet1 = getEnvironmentSet(paramRenderAtom, paramArrayOfLightRetained, paramFogRetained, paramModelClipRetained);
/*      */       
/* 4204 */       lightBin1 = lightBin2;
/* 4205 */       while (lightBin1 != null) {
/*      */ 
/*      */         
/* 4208 */         if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground && lightBin1.willEnvironmentSetFit(environmentSet1)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4216 */           for (byte b = 0; b < paramArrayOfLightRetained.length; b++) {
/* 4217 */             if (!this.changedLts.contains(paramArrayOfLightRetained[b]))
/* 4218 */               this.changedLts.add(paramArrayOfLightRetained[b]); 
/* 4219 */             this.envDirty |= REEVALUATE_LIGHTS;
/*      */           } 
/*      */           
/*      */           break;
/*      */         } 
/* 4224 */         lightBin1 = lightBin1.next;
/*      */       } 
/*      */ 
/*      */       
/* 4228 */       if (lightBin1 == null) {
/* 4229 */         lightBin1 = lightBin3;
/* 4230 */         while (lightBin1 != null) {
/*      */ 
/*      */           
/* 4233 */           if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground && lightBin1.willEnvironmentSetFit(environmentSet1)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4241 */             for (byte b = 0; b < paramArrayOfLightRetained.length; b++) {
/* 4242 */               if (!this.changedLts.contains(paramArrayOfLightRetained[b]))
/* 4243 */                 this.changedLts.add(paramArrayOfLightRetained[b]); 
/* 4244 */               this.envDirty |= REEVALUATE_LIGHTS;
/*      */             } 
/*      */             
/*      */             break;
/*      */           } 
/* 4249 */           lightBin1 = lightBin1.next;
/*      */         } 
/*      */       } 
/*      */       
/* 4253 */       if (lightBin1 == null) {
/*      */         
/* 4255 */         lightBin1 = getLightBin(this.maxLights, paramRenderAtom.geometryAtom.source.geometryBackground, false);
/*      */         
/* 4257 */         if (lightBin3 != null) {
/* 4258 */           lightBin1.next = lightBin3;
/* 4259 */           lightBin3.prev = lightBin1;
/*      */         } 
/* 4261 */         if (paramRenderAtom.geometryAtom.source.orderedPath != null) {
/* 4262 */           if (!orderedCollection.onUpdateList) {
/* 4263 */             this.objUpdateList.add(orderedCollection);
/* 4264 */             orderedCollection.onUpdateList = true;
/*      */           } 
/* 4266 */           orderedCollection.addLightBins = lightBin1;
/*      */         }
/* 4268 */         else if (paramRenderAtom.geometryAtom.source.geometryBackground == null) {
/* 4269 */           this.addOpaqueBin = lightBin1;
/*      */         } else {
/* 4271 */           this.bgAddOpaqueBin = lightBin1;
/*      */         } 
/*      */       } 
/*      */       
/* 4275 */       environmentSet = environmentSet1;
/* 4276 */       lightBin1.addEnvironmentSet(environmentSet, this);
/*      */     } 
/*      */     
/* 4279 */     paramRenderAtom.fog = paramFogRetained;
/* 4280 */     paramRenderAtom.lights = paramArrayOfLightRetained;
/* 4281 */     paramRenderAtom.modelClip = paramModelClipRetained;
/* 4282 */     paramRenderAtom.app = paramAppearanceRetained;
/* 4283 */     reInsertAttributeBin(environmentSet, paramRenderAtom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void reInsertAttributeBin(EnvironmentSet paramEnvironmentSet, RenderAtom paramRenderAtom) {
/* 4290 */     AttributeBin attributeBin = findAttributeBin(paramEnvironmentSet, paramRenderAtom);
/* 4291 */     reInsertShaderBin(attributeBin, paramRenderAtom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void reInsertShaderBin(AttributeBin paramAttributeBin, RenderAtom paramRenderAtom) {
/* 4298 */     ShaderBin shaderBin = findShaderBin(paramAttributeBin, paramRenderAtom);
/* 4299 */     reInsertTextureBin(shaderBin, paramRenderAtom);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void reInsertTextureBin(ShaderBin paramShaderBin, RenderAtom paramRenderAtom) {
/* 4305 */     TextureBin textureBin = findTextureBin(paramShaderBin, paramRenderAtom);
/* 4306 */     reInsertRenderAtom(textureBin, paramRenderAtom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4312 */   private void reInsertRenderAtom(TextureBin paramTextureBin, RenderAtom paramRenderAtom) { RenderMolecule renderMolecule = findRenderMolecule(paramTextureBin, paramRenderAtom); }
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeViewFrustumBBox(BoundingBox paramBoundingBox) {
/* 4317 */     paramBoundingBox.lower.x = Double.POSITIVE_INFINITY;
/* 4318 */     paramBoundingBox.lower.y = Double.POSITIVE_INFINITY;
/* 4319 */     paramBoundingBox.lower.z = Double.POSITIVE_INFINITY;
/* 4320 */     paramBoundingBox.upper.x = Double.NEGATIVE_INFINITY;
/* 4321 */     paramBoundingBox.upper.y = Double.NEGATIVE_INFINITY;
/* 4322 */     paramBoundingBox.upper.z = Double.NEGATIVE_INFINITY;
/*      */     
/* 4324 */     Canvas3D[] arrayOfCanvas3D = this.view.getCanvases();
/* 4325 */     for (byte b = 0; b < arrayOfCanvas3D.length; b++) {
/* 4326 */       Canvas3D canvas3D = arrayOfCanvas3D[b];
/*      */ 
/*      */       
/* 4329 */       this.canvasFrustumBBox.lower.x = Double.POSITIVE_INFINITY;
/* 4330 */       this.canvasFrustumBBox.lower.y = Double.POSITIVE_INFINITY;
/* 4331 */       this.canvasFrustumBBox.lower.z = Double.POSITIVE_INFINITY;
/* 4332 */       this.canvasFrustumBBox.upper.x = Double.NEGATIVE_INFINITY;
/* 4333 */       this.canvasFrustumBBox.upper.y = Double.NEGATIVE_INFINITY;
/* 4334 */       this.canvasFrustumBBox.upper.z = Double.NEGATIVE_INFINITY;
/*      */       
/* 4336 */       canvas3D.updateViewCache(true, null, this.canvasFrustumBBox, false);
/*      */       
/* 4338 */       if (paramBoundingBox.lower.x > this.canvasFrustumBBox.lower.x)
/* 4339 */         paramBoundingBox.lower.x = this.canvasFrustumBBox.lower.x; 
/* 4340 */       if (paramBoundingBox.lower.y > this.canvasFrustumBBox.lower.y)
/* 4341 */         paramBoundingBox.lower.y = this.canvasFrustumBBox.lower.y; 
/* 4342 */       if (paramBoundingBox.lower.z > this.canvasFrustumBBox.lower.z) {
/* 4343 */         paramBoundingBox.lower.z = this.canvasFrustumBBox.lower.z;
/*      */       }
/* 4345 */       if (paramBoundingBox.upper.x < this.canvasFrustumBBox.upper.x)
/* 4346 */         paramBoundingBox.upper.x = this.canvasFrustumBBox.upper.x; 
/* 4347 */       if (paramBoundingBox.upper.y < this.canvasFrustumBBox.upper.y)
/* 4348 */         paramBoundingBox.upper.y = this.canvasFrustumBBox.upper.y; 
/* 4349 */       if (paramBoundingBox.upper.z < this.canvasFrustumBBox.upper.z) {
/* 4350 */         paramBoundingBox.upper.z = this.canvasFrustumBBox.upper.z;
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
/*      */   private RenderMolecule insertRenderAtom(RenderAtom paramRenderAtom) {
/* 4369 */     GeometryAtom geometryAtom = paramRenderAtom.geometryAtom;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4375 */     if (paramRenderAtom.localeVwcBounds == null)
/*      */     {
/* 4377 */       if (!this.locale.hiRes.equals(geometryAtom.source.locale.hiRes)) {
/* 4378 */         geometryAtom.source.locale.hiRes.difference(this.locale.hiRes, this.localeTranslation);
/*      */         
/* 4380 */         paramRenderAtom.localeVwcBounds = new BoundingBox();
/* 4381 */         paramRenderAtom.localeVwcBounds.translate(geometryAtom.source.vwcBounds, this.localeTranslation);
/*      */         
/* 4383 */         paramRenderAtom.dirtyMask |= RenderAtom.HAS_SEPARATE_LOCALE_VWC_BOUNDS;
/*      */       } else {
/*      */         
/* 4386 */         paramRenderAtom.dirtyMask &= (RenderAtom.HAS_SEPARATE_LOCALE_VWC_BOUNDS ^ 0xFFFFFFFF);
/* 4387 */         paramRenderAtom.localeVwcBounds = geometryAtom.source.vwcBounds;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4394 */     if (geometryAtom.source.appearanceOverrideEnable) {
/* 4395 */       Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(paramRenderAtom, this.view);
/*      */       
/* 4397 */       if (arrayOfObject[false] == Boolean.TRUE) {
/* 4398 */         AppearanceRetained appearanceRetained = (AppearanceRetained)arrayOfObject[1];
/* 4399 */         paramRenderAtom.app = appearanceRetained;
/* 4400 */         if (geometryAtom.source.otherAppearance != appearanceRetained) {
/* 4401 */           if (geometryAtom.source.otherAppearance != null) {
/* 4402 */             geometryAtom.source.otherAppearance.sgApp.removeAMirrorUser(geometryAtom.source);
/*      */           }
/* 4404 */           geometryAtom.source.otherAppearance = appearanceRetained;
/* 4405 */           if (appearanceRetained != null) {
/* 4406 */             paramRenderAtom.app.sgApp.addAMirrorUser(geometryAtom.source);
/*      */           }
/*      */         } 
/*      */       } else {
/* 4410 */         paramRenderAtom.app = geometryAtom.source.appearance;
/*      */       } 
/*      */     } else {
/*      */       
/* 4414 */       paramRenderAtom.app = geometryAtom.source.appearance;
/*      */     } 
/*      */ 
/*      */     
/* 4418 */     EnvironmentSet environmentSet = findEnvironmentSet(paramRenderAtom);
/* 4419 */     AttributeBin attributeBin = findAttributeBin(environmentSet, paramRenderAtom);
/*      */ 
/*      */     
/* 4422 */     ShaderBin shaderBin = findShaderBin(attributeBin, paramRenderAtom);
/*      */     
/* 4424 */     TextureBin textureBin = findTextureBin(shaderBin, paramRenderAtom);
/* 4425 */     RenderMolecule renderMolecule = findRenderMolecule(textureBin, paramRenderAtom);
/* 4426 */     paramRenderAtom.setRenderBin(true);
/* 4427 */     this.renderAtoms.add(paramRenderAtom);
/*      */     
/* 4429 */     if (geometryAtom.source instanceof OrientedShape3DRetained) {
/*      */       
/* 4431 */       this.dirtyOrientedRAs.add(paramRenderAtom);
/* 4432 */       paramRenderAtom.dirtyMask |= RenderAtom.IN_DIRTY_ORIENTED_RAs;
/* 4433 */       paramRenderAtom.dirtyMask |= RenderAtom.IS_ORIENTED;
/* 4434 */       for (byte b = 0; b < paramRenderAtom.rListInfo.length; b++) {
/* 4435 */         if ((paramRenderAtom.rListInfo[b]).localToVworld == null) {
/* 4436 */           (paramRenderAtom.rListInfo[b]).localToVworld = new Transform3D();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 4441 */     if (renderMolecule.primaryMoleculeType == RenderMolecule.TEXT3D_MOLECULE)
/*      */     {
/* 4443 */       if (!paramRenderAtom.onUpdateList()) {
/* 4444 */         paramRenderAtom.dirtyMask |= RenderAtom.ON_UPDATELIST;
/* 4445 */         this.objUpdateList.add(paramRenderAtom);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4454 */     if (paramRenderAtom.needSeparateLocaleVwcBounds()) {
/* 4455 */       if (!paramRenderAtom.hasSeparateLocaleVwcBounds()) {
/* 4456 */         paramRenderAtom.dirtyMask |= RenderAtom.HAS_SEPARATE_LOCALE_VWC_BOUNDS;
/* 4457 */         paramRenderAtom.localeVwcBounds = new BoundingBox(geometryAtom.source.vwcBounds);
/* 4458 */         paramRenderAtom; paramRenderAtom.dirtyMask |= RenderAtom.ON_LOCALE_VWC_BOUNDS_UPDATELIST;
/* 4459 */         this.raLocaleVwcBoundsUpdateList.add(paramRenderAtom);
/*      */       } else {
/*      */         
/* 4462 */         paramRenderAtom.localeVwcBounds.set(geometryAtom.source.vwcBounds);
/* 4463 */         paramRenderAtom; paramRenderAtom.dirtyMask |= RenderAtom.ON_LOCALE_VWC_BOUNDS_UPDATELIST;
/* 4464 */         this.raLocaleVwcBoundsUpdateList.add(paramRenderAtom);
/*      */       } 
/*      */     }
/* 4467 */     return renderMolecule;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private OrderedCollection findOrderedCollection(GeometryAtom paramGeometryAtom, boolean paramBoolean) {
/* 4476 */     ArrayList arrayList2, arrayList1 = null;
/*      */ 
/*      */ 
/*      */     
/* 4480 */     OrderedCollection orderedCollection = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4494 */     if (paramBoolean) {
/* 4495 */       arrayList2 = this.bgOrderedBins;
/*      */     } else {
/* 4497 */       arrayList2 = this.orderedBins;
/*      */     } 
/*      */     
/* 4500 */     OrderedBin orderedBin = null;
/* 4501 */     int i = -1;
/*      */     
/* 4503 */     for (byte b = 0; b < paramGeometryAtom.source.orderedPath.pathElements.size(); b++) {
/* 4504 */       OrderedPathElement orderedPathElement = (OrderedPathElement)paramGeometryAtom.source.orderedPath.pathElements.get(b);
/* 4505 */       OrderedGroupRetained orderedGroupRetained = orderedPathElement.orderedGroup;
/* 4506 */       int j = orderedPathElement.childId.intValue();
/*      */       
/* 4508 */       OrderedBin orderedBin1 = orderedGroupRetained.getOrderedBin(this.view.viewIndex);
/* 4509 */       if (orderedBin1 == null) {
/*      */         
/* 4511 */         orderedBin1 = new OrderedBin(orderedGroupRetained.childCount, orderedGroupRetained);
/* 4512 */         orderedGroupRetained.setOrderedBin(orderedBin1, this.view.viewIndex);
/*      */         
/* 4514 */         byte b3 = -1;
/* 4515 */         for (byte b2 = 0; b2 < this.orderedBinsList.size(); b2++) {
/* 4516 */           if (arrayList2 == this.orderedBinsList.get(b2)) {
/* 4517 */             b3 = b2;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 4522 */         if (b3 == -1) {
/* 4523 */           this.orderedBinsList.add(arrayList2);
/* 4524 */           arrayList1 = new ArrayList(5);
/* 4525 */           arrayList1.add(orderedBin1);
/* 4526 */           this.toBeAddedBinList.add(arrayList1);
/*      */         } else {
/*      */           
/* 4529 */           arrayList1 = (ArrayList)this.toBeAddedBinList.get(b3);
/* 4530 */           arrayList1.add(orderedBin1);
/*      */         } 
/*      */       } 
/* 4533 */       ArrayList arrayList = orderedBin1.orderedCollections;
/* 4534 */       OrderedChildInfo orderedChildInfo = orderedBin1.lastChildInfo;
/* 4535 */       boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4541 */       while (orderedChildInfo != null && !bool) {
/* 4542 */         if (orderedChildInfo.type == OrderedChildInfo.ADD && 
/* 4543 */           orderedChildInfo.orderedId == j) {
/* 4544 */           orderedCollection = orderedChildInfo.value;
/* 4545 */           if (orderedCollection == null) {
/* 4546 */             orderedCollection = new OrderedCollection();
/* 4547 */             orderedChildInfo.value = orderedCollection;
/*      */           } 
/* 4549 */           bool = true;
/*      */         } 
/*      */         
/* 4552 */         orderedChildInfo = orderedChildInfo.prev;
/*      */       } 
/*      */       
/*      */       byte b1;
/* 4556 */       for (b1 = 0; b1 < orderedBin1.setOCForOI.size(); b1++) {
/* 4557 */         int k = ((Integer)orderedBin1.setOCForOI.get(b1)).intValue();
/* 4558 */         if (j == k) {
/* 4559 */           orderedCollection = (OrderedCollection)orderedBin1.valueOfSetOCForOI.get(b1);
/* 4560 */           bool = true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4567 */       if (!bool)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 4572 */         if (orderedGroupRetained.orderedChildIdTable == null || j >= orderedGroupRetained.orderedChildIdTable.length) {
/*      */ 
/*      */ 
/*      */           
/* 4576 */           orderedBin1.setOCForOI.add(new Integer(j));
/* 4577 */           orderedCollection = new OrderedCollection();
/* 4578 */           orderedBin1.valueOfSetOCForOI.add(orderedCollection);
/* 4579 */           if (!orderedBin1.onUpdateList) {
/* 4580 */             this.obList.add(orderedBin1);
/* 4581 */             orderedBin1.onUpdateList = true;
/*      */           } 
/*      */         } else {
/*      */           
/* 4585 */           int k = orderedGroupRetained.orderedChildIdTable[j];
/*      */           
/* 4587 */           for (b1 = 0; b1 < orderedBin1.setOCForCI.size(); b1++) {
/* 4588 */             int n = ((Integer)orderedBin1.setOCForCI.get(b1)).intValue();
/* 4589 */             if (n == k) {
/*      */               
/* 4591 */               orderedCollection = (OrderedCollection)orderedBin1.valueOfSetOCForCI.get(b1);
/* 4592 */               if (orderedCollection == null) {
/* 4593 */                 orderedCollection = new OrderedCollection();
/* 4594 */                 orderedBin1.valueOfSetOCForCI.set(b1, orderedCollection);
/*      */               } 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/* 4600 */           if (b1 == orderedBin1.setOCForCI.size()) {
/* 4601 */             orderedCollection = (OrderedCollection)arrayList.get(k);
/* 4602 */             if (orderedCollection == null) {
/* 4603 */               orderedCollection = new OrderedCollection();
/* 4604 */               orderedBin1.setOCForCI.add(new Integer(k));
/* 4605 */               orderedBin1.valueOfSetOCForCI.add(orderedCollection);
/* 4606 */               if (!orderedBin1.onUpdateList) {
/* 4607 */                 this.obList.add(orderedBin1);
/* 4608 */                 orderedBin1.onUpdateList = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/* 4614 */       if (orderedCollection.nextFrameLightBin == null) {
/* 4615 */         orderedCollection.nextFrameLightBin = getLightBin(this.maxLights, paramGeometryAtom.source.geometryBackground, false);
/*      */         
/* 4617 */         orderedCollection.nextFrameLightBin.setOrderedInfo(orderedCollection);
/*      */         
/* 4619 */         if (!orderedCollection.onUpdateList) {
/* 4620 */           this.objUpdateList.add(orderedCollection);
/* 4621 */           orderedCollection.onUpdateList = true;
/*      */         } 
/*      */       } 
/*      */       
/* 4625 */       arrayList2 = orderedCollection.childOrderedBins;
/* 4626 */       orderedBin = orderedBin1;
/* 4627 */       i = j;
/*      */     } 
/* 4629 */     return orderedCollection;
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
/*      */   private void removeOrderedHeadLightBin(LightBin paramLightBin) {
/* 4643 */     OrderedCollection orderedCollection = paramLightBin.orderedCollection;
/*      */     
/* 4645 */     orderedCollection.lightBin = paramLightBin.next;
/* 4646 */     orderedCollection.nextFrameLightBin = orderedCollection.lightBin;
/*      */ 
/*      */ 
/*      */     
/* 4650 */     if (orderedCollection.lightBin != null) {
/*      */       
/* 4652 */       orderedCollection.lightBin.prev = null;
/* 4653 */       orderedCollection.lightBin.orderedCollection = orderedCollection;
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
/* 4668 */   private EnvironmentSet getEnvironmentSet(RenderAtom paramRenderAtom, LightRetained[] paramArrayOfLightRetained, FogRetained paramFogRetained, ModelClipRetained paramModelClipRetained) { return new EnvironmentSet(paramRenderAtom, paramArrayOfLightRetained, paramFogRetained, paramModelClipRetained, this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AttributeBin findAttributeBin(EnvironmentSet paramEnvironmentSet, RenderAtom paramRenderAtom) {
/*      */     RenderingAttributesRetained renderingAttributesRetained;
/* 4679 */     if (paramRenderAtom.app == null) {
/* 4680 */       renderingAttributesRetained = null;
/*      */     } else {
/* 4682 */       renderingAttributesRetained = paramRenderAtom.app.renderingAttributes;
/*      */     } 
/*      */     
/* 4685 */     AttributeBin attributeBin = paramEnvironmentSet.attributeBinList;
/* 4686 */     while (attributeBin != null) {
/* 4687 */       if (attributeBin.equals(renderingAttributesRetained, paramRenderAtom)) {
/* 4688 */         return attributeBin;
/*      */       }
/* 4690 */       attributeBin = attributeBin.next;
/*      */     } 
/*      */     
/* 4693 */     for (byte b = 0; b < paramEnvironmentSet.addAttributeBins.size(); b++) {
/* 4694 */       attributeBin = (AttributeBin)paramEnvironmentSet.addAttributeBins.get(b);
/* 4695 */       if (attributeBin.equals(renderingAttributesRetained, paramRenderAtom)) {
/* 4696 */         return attributeBin;
/*      */       }
/*      */     } 
/* 4699 */     attributeBin = getAttributeBin(paramRenderAtom.app, renderingAttributesRetained);
/* 4700 */     paramEnvironmentSet.addAttributeBin(attributeBin, this);
/* 4701 */     return attributeBin;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ShaderBin findShaderBin(AttributeBin paramAttributeBin, RenderAtom paramRenderAtom) {
/*      */     ShaderAppearanceRetained shaderAppearanceRetained;
/* 4712 */     if (paramRenderAtom != null && paramRenderAtom.app instanceof ShaderAppearanceRetained) {
/* 4713 */       shaderAppearanceRetained = (ShaderAppearanceRetained)paramRenderAtom.app;
/*      */     } else {
/* 4715 */       shaderAppearanceRetained = null;
/*      */     } 
/* 4717 */     ShaderBin shaderBin = paramAttributeBin.shaderBinList;
/* 4718 */     while (shaderBin != null) {
/* 4719 */       if (shaderBin.equals(shaderAppearanceRetained)) {
/* 4720 */         return shaderBin;
/*      */       }
/* 4722 */       shaderBin = shaderBin.next;
/*      */     } 
/*      */ 
/*      */     
/* 4726 */     int i = paramAttributeBin.addShaderBins.size();
/* 4727 */     for (byte b = 0; b < i; b++) {
/* 4728 */       shaderBin = (ShaderBin)paramAttributeBin.addShaderBins.get(b);
/* 4729 */       if (shaderBin.equals(shaderAppearanceRetained)) {
/* 4730 */         return shaderBin;
/*      */       }
/*      */     } 
/*      */     
/* 4734 */     shaderBin = getShaderBin(shaderAppearanceRetained);
/* 4735 */     paramAttributeBin.addShaderBin(shaderBin, this, shaderAppearanceRetained);
/* 4736 */     return shaderBin;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private TextureBin findTextureBin(ShaderBin paramShaderBin, RenderAtom paramRenderAtom) {
/*      */     TextureUnitStateRetained[] arrayOfTextureUnitStateRetained;
/* 4748 */     if (paramRenderAtom.app == null) {
/* 4749 */       arrayOfTextureUnitStateRetained = null;
/*      */     } else {
/* 4751 */       arrayOfTextureUnitStateRetained = paramRenderAtom.app.texUnitState;
/*      */     } 
/*      */     
/* 4754 */     TextureBin textureBin = paramShaderBin.textureBinList;
/* 4755 */     while (textureBin != null) {
/* 4756 */       if (textureBin.equals(arrayOfTextureUnitStateRetained, paramRenderAtom))
/*      */       {
/* 4758 */         return textureBin;
/*      */       }
/* 4760 */       textureBin = textureBin.next;
/*      */     } 
/*      */     
/* 4763 */     int i = paramShaderBin.addTextureBins.size();
/* 4764 */     for (byte b = 0; b < i; b++) {
/* 4765 */       textureBin = (TextureBin)paramShaderBin.addTextureBins.get(b);
/* 4766 */       if (textureBin.equals(arrayOfTextureUnitStateRetained, paramRenderAtom))
/*      */       {
/* 4768 */         return textureBin;
/*      */       }
/*      */     } 
/*      */     
/* 4772 */     textureBin = getTextureBin(arrayOfTextureUnitStateRetained, paramRenderAtom.app);
/* 4773 */     paramShaderBin.addTextureBin(textureBin, this, paramRenderAtom);
/* 4774 */     return textureBin;
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
/*      */   private RenderMolecule findRenderMolecule(TextureBin paramTextureBin, RenderAtom paramRenderAtom) {
/*      */     RenderingAttributesRetained renderingAttributesRetained;
/*      */     TextureUnitStateRetained[] arrayOfTextureUnitStateRetained;
/*      */     TransparencyAttributesRetained transparencyAttributesRetained;
/*      */     ColoringAttributesRetained coloringAttributesRetained;
/*      */     MaterialRetained materialRetained;
/*      */     PointAttributesRetained pointAttributesRetained;
/*      */     LineAttributesRetained lineAttributesRetained;
/*      */     PolygonAttributesRetained polygonAttributesRetained;
/* 4794 */     HashMap hashMap1 = null, hashMap2 = null;
/*      */     
/* 4796 */     if (paramRenderAtom.app == null) {
/* 4797 */       polygonAttributesRetained = null;
/* 4798 */       lineAttributesRetained = null;
/* 4799 */       pointAttributesRetained = null;
/* 4800 */       materialRetained = null;
/* 4801 */       coloringAttributesRetained = null;
/* 4802 */       transparencyAttributesRetained = null;
/* 4803 */       renderingAttributesRetained = null;
/* 4804 */       arrayOfTextureUnitStateRetained = null;
/*      */     } else {
/* 4806 */       polygonAttributesRetained = paramRenderAtom.app.polygonAttributes;
/* 4807 */       lineAttributesRetained = paramRenderAtom.app.lineAttributes;
/* 4808 */       pointAttributesRetained = paramRenderAtom.app.pointAttributes;
/* 4809 */       materialRetained = paramRenderAtom.app.material;
/* 4810 */       coloringAttributesRetained = paramRenderAtom.app.coloringAttributes;
/* 4811 */       transparencyAttributesRetained = paramRenderAtom.app.transparencyAttributes;
/* 4812 */       renderingAttributesRetained = paramRenderAtom.app.renderingAttributes;
/* 4813 */       arrayOfTextureUnitStateRetained = paramRenderAtom.app.texUnitState;
/*      */     } 
/*      */ 
/*      */     
/* 4817 */     if (paramRenderAtom.isOpaque()) {
/* 4818 */       hashMap1 = paramTextureBin.opaqueRenderMoleculeMap;
/* 4819 */       hashMap2 = paramTextureBin.addOpaqueRMs;
/*      */     } else {
/*      */       
/* 4822 */       hashMap1 = paramTextureBin.transparentRenderMoleculeMap;
/* 4823 */       hashMap2 = paramTextureBin.addTransparentRMs;
/*      */     } 
/* 4825 */     RenderMolecule renderMolecule = (RenderMolecule)hashMap1.get(paramRenderAtom.geometryAtom.source.localToVworld[0]);
/*      */     
/* 4827 */     while (renderMolecule != null) {
/* 4828 */       if (renderMolecule.equals(paramRenderAtom, polygonAttributesRetained, lineAttributesRetained, pointAttributesRetained, materialRetained, coloringAttributesRetained, transparencyAttributesRetained, paramRenderAtom.geometryAtom.source.localToVworld[0])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4835 */         renderMolecule.addRenderAtom(paramRenderAtom, this);
/* 4836 */         paramRenderAtom.envSet = paramRenderAtom.renderMolecule.textureBin.environmentSet;
/*      */ 
/*      */         
/* 4839 */         return renderMolecule;
/*      */       } 
/* 4841 */       renderMolecule = renderMolecule.next;
/*      */     } 
/*      */     ArrayList arrayList;
/* 4844 */     if ((arrayList = (ArrayList)hashMap2.get(paramRenderAtom.geometryAtom.source.localToVworld[false])) != null) {
/* 4845 */       for (byte b = 0; b < arrayList.size(); b++) {
/* 4846 */         renderMolecule = (RenderMolecule)arrayList.get(b);
/* 4847 */         if (renderMolecule.equals(paramRenderAtom, polygonAttributesRetained, lineAttributesRetained, pointAttributesRetained, materialRetained, coloringAttributesRetained, transparencyAttributesRetained, paramRenderAtom.geometryAtom.source.localToVworld[0])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4853 */           renderMolecule.addRenderAtom(paramRenderAtom, this);
/* 4854 */           return renderMolecule;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 4860 */     renderMolecule = getRenderMolecule(paramRenderAtom.geometryAtom, polygonAttributesRetained, lineAttributesRetained, pointAttributesRetained, materialRetained, coloringAttributesRetained, transparencyAttributesRetained, renderingAttributesRetained, arrayOfTextureUnitStateRetained, paramRenderAtom.geometryAtom.source.localToVworld[0], paramRenderAtom.geometryAtom.source.localToVworldIndex[0]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4871 */     paramTextureBin.addRenderMolecule(renderMolecule, this);
/* 4872 */     renderMolecule.addRenderAtom(paramRenderAtom, this);
/* 4873 */     return renderMolecule;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4881 */   private ShaderBin getShaderBin(ShaderAppearanceRetained paramShaderAppearanceRetained) { return new ShaderBin(paramShaderAppearanceRetained, this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4889 */   private AttributeBin getAttributeBin(AppearanceRetained paramAppearanceRetained, RenderingAttributesRetained paramRenderingAttributesRetained) { return new AttributeBin(paramAppearanceRetained, paramRenderingAttributesRetained, this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LightBin getLightBin(int paramInt, BackgroundRetained paramBackgroundRetained, boolean paramBoolean) {
/* 4899 */     LightBin lightBin = new LightBin(paramInt, this, paramBoolean);
/*      */     
/* 4901 */     lightBin.geometryBackground = paramBackgroundRetained;
/* 4902 */     return lightBin;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4911 */   private TextureBin getTextureBin(TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained, AppearanceRetained paramAppearanceRetained) { return new TextureBin(paramArrayOfTextureUnitStateRetained, paramAppearanceRetained, this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4930 */   private RenderMolecule getRenderMolecule(GeometryAtom paramGeometryAtom, PolygonAttributesRetained paramPolygonAttributesRetained, LineAttributesRetained paramLineAttributesRetained, PointAttributesRetained paramPointAttributesRetained, MaterialRetained paramMaterialRetained, ColoringAttributesRetained paramColoringAttributesRetained, TransparencyAttributesRetained paramTransparencyAttributesRetained, RenderingAttributesRetained paramRenderingAttributesRetained, TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained, Transform3D[] paramArrayOfTransform3D, int[] paramArrayOfInt) { return new RenderMolecule(paramGeometryAtom, paramPolygonAttributesRetained, paramLineAttributesRetained, paramPointAttributesRetained, paramMaterialRetained, paramColoringAttributesRetained, paramTransparencyAttributesRetained, paramRenderingAttributesRetained, paramArrayOfTextureUnitStateRetained, paramArrayOfTransform3D, paramArrayOfInt, this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private EnvironmentSet findEnvironmentSet(RenderAtom paramRenderAtom) {
/* 4945 */     LightBin lightBin3 = null;
/* 4946 */     OrderedCollection orderedCollection = null;
/*      */     
/* 4948 */     if (paramRenderAtom.geometryAtom.source.geometryBackground == null) {
/* 4949 */       if (paramRenderAtom.geometryAtom.source.orderedPath != null) {
/* 4950 */         orderedCollection = findOrderedCollection(paramRenderAtom.geometryAtom, false);
/* 4951 */         lightBin1 = orderedCollection.nextFrameLightBin;
/* 4952 */         lightBin3 = orderedCollection.addLightBins;
/*      */       } else {
/* 4954 */         lightBin1 = this.opaqueBin;
/* 4955 */         lightBin3 = this.addOpaqueBin;
/*      */       }
/*      */     
/* 4958 */     } else if (paramRenderAtom.geometryAtom.source.orderedPath != null) {
/* 4959 */       orderedCollection = findOrderedCollection(paramRenderAtom.geometryAtom, true);
/* 4960 */       lightBin1 = orderedCollection.nextFrameLightBin;
/* 4961 */       lightBin3 = orderedCollection.addLightBins;
/*      */     } else {
/* 4963 */       lightBin1 = this.bgOpaqueBin;
/* 4964 */       lightBin3 = this.bgAddOpaqueBin;
/*      */     } 
/*      */ 
/*      */     
/* 4968 */     LightBin lightBin2 = lightBin1;
/*      */ 
/*      */     
/* 4971 */     paramRenderAtom.lights = this.universe.renderingEnvironmentStructure.getInfluencingLights(paramRenderAtom, this.view);
/*      */     
/* 4973 */     paramRenderAtom.fog = this.universe.renderingEnvironmentStructure.getInfluencingFog(paramRenderAtom, this.view);
/*      */     
/* 4975 */     paramRenderAtom.modelClip = this.universe.renderingEnvironmentStructure.getInfluencingModelClip(paramRenderAtom, this.view);
/*      */ 
/*      */     
/* 4978 */     while (lightBin1 != null) {
/*      */       
/* 4980 */       if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground) {
/*      */ 
/*      */         
/* 4983 */         EnvironmentSet environmentSet1 = lightBin1.environmentSetList;
/* 4984 */         while (environmentSet1 != null) {
/* 4985 */           if (environmentSet1.equals(paramRenderAtom, paramRenderAtom.lights, paramRenderAtom.fog, paramRenderAtom.modelClip)) {
/* 4986 */             return environmentSet1;
/*      */           }
/* 4988 */           environmentSet1 = environmentSet1.next;
/*      */         } 
/*      */         
/* 4991 */         for (byte b = 0; b < lightBin1.insertEnvSet.size(); b++) {
/* 4992 */           EnvironmentSet environmentSet2 = (EnvironmentSet)lightBin1.insertEnvSet.get(b);
/* 4993 */           if (environmentSet2.equals(paramRenderAtom, paramRenderAtom.lights, paramRenderAtom.fog, paramRenderAtom.modelClip)) {
/* 4994 */             return environmentSet2;
/*      */           }
/*      */         } 
/*      */       } 
/* 4998 */       lightBin1 = lightBin1.next;
/*      */     } 
/*      */ 
/*      */     
/* 5002 */     LightBin lightBin1 = lightBin3;
/* 5003 */     while (lightBin1 != null) {
/*      */ 
/*      */       
/* 5006 */       if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground)
/*      */       {
/*      */ 
/*      */         
/* 5010 */         for (byte b = 0; b < lightBin1.insertEnvSet.size(); b++) {
/* 5011 */           EnvironmentSet environmentSet1 = (EnvironmentSet)lightBin1.insertEnvSet.get(b);
/* 5012 */           if (environmentSet1.equals(paramRenderAtom, paramRenderAtom.lights, paramRenderAtom.fog, paramRenderAtom.modelClip)) {
/* 5013 */             return environmentSet1;
/*      */           }
/*      */         } 
/*      */       }
/* 5017 */       lightBin1 = lightBin1.next;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5022 */     EnvironmentSet environmentSet = getEnvironmentSet(paramRenderAtom, paramRenderAtom.lights, paramRenderAtom.fog, paramRenderAtom.modelClip);
/* 5023 */     lightBin1 = lightBin2;
/*      */ 
/*      */     
/* 5026 */     while (lightBin1 != null) {
/*      */ 
/*      */       
/* 5029 */       if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground && lightBin1.willEnvironmentSetFit(environmentSet)) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/* 5034 */       lightBin1 = lightBin1.next;
/*      */     } 
/*      */ 
/*      */     
/* 5038 */     if (lightBin1 == null) {
/* 5039 */       lightBin1 = lightBin3;
/* 5040 */       while (lightBin1 != null) {
/*      */ 
/*      */         
/* 5043 */         if (lightBin1.geometryBackground == paramRenderAtom.geometryAtom.source.geometryBackground && lightBin1.willEnvironmentSetFit(environmentSet)) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 5049 */         lightBin1 = lightBin1.next;
/*      */       } 
/*      */     } 
/*      */     
/* 5053 */     if (lightBin1 == null) {
/*      */       
/* 5055 */       lightBin1 = getLightBin(this.maxLights, paramRenderAtom.geometryAtom.source.geometryBackground, false);
/*      */       
/* 5057 */       if (lightBin3 != null) {
/* 5058 */         lightBin1.next = lightBin3;
/* 5059 */         lightBin3.prev = lightBin1;
/*      */       } 
/* 5061 */       if (paramRenderAtom.geometryAtom.source.orderedPath != null) {
/* 5062 */         if (!orderedCollection.onUpdateList) {
/* 5063 */           this.objUpdateList.add(orderedCollection);
/* 5064 */           orderedCollection.onUpdateList = true;
/*      */         } 
/* 5066 */         orderedCollection.addLightBins = lightBin1;
/*      */       }
/* 5068 */       else if (paramRenderAtom.geometryAtom.source.geometryBackground == null) {
/* 5069 */         this.addOpaqueBin = lightBin1;
/*      */       } else {
/* 5071 */         this.bgAddOpaqueBin = lightBin1;
/*      */       } 
/*      */     } 
/*      */     
/* 5075 */     lightBin1.addEnvironmentSet(environmentSet, this);
/* 5076 */     return environmentSet;
/*      */   }
/*      */   
/*      */   void removeLightBin(LightBin paramLightBin) {
/* 5080 */     if (paramLightBin.prev == null) {
/*      */       
/* 5082 */       if (paramLightBin.orderedCollection != null) {
/* 5083 */         removeOrderedHeadLightBin(paramLightBin);
/*      */       }
/* 5085 */       if (paramLightBin.geometryBackground == null) {
/* 5086 */         if (this.opaqueBin == paramLightBin) {
/* 5087 */           this.opaqueBin = paramLightBin.next;
/*      */         }
/*      */       }
/* 5090 */       else if (this.bgOpaqueBin == paramLightBin) {
/* 5091 */         this.bgOpaqueBin = paramLightBin.next;
/*      */       } 
/*      */       
/* 5094 */       if (paramLightBin.next != null) {
/* 5095 */         paramLightBin.next.prev = null;
/*      */       }
/*      */     } else {
/* 5098 */       paramLightBin.prev.next = paramLightBin.next;
/* 5099 */       if (paramLightBin.next != null) {
/* 5100 */         paramLightBin.next.prev = paramLightBin.prev;
/*      */       }
/*      */     } 
/* 5103 */     Canvas3D[] arrayOfCanvas3D = this.view.getCanvases();
/* 5104 */     for (byte b = 0; b < arrayOfCanvas3D.length; b++)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5110 */       (arrayOfCanvas3D[b]).lightBin = null;
/*      */     }
/* 5112 */     paramLightBin.prev = null;
/* 5113 */     paramLightBin.next = null;
/*      */   }
/*      */ 
/*      */   
/* 5117 */   void addDisplayListResourceFreeList(RenderMolecule paramRenderMolecule) { this.displayListResourceFreeList.add(paramRenderMolecule.displayListIdObj); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderBackground(Canvas3D paramCanvas3D) {
/* 5127 */     paramCanvas3D.setDepthBufferWriteEnableOverride(true);
/* 5128 */     boolean bool = paramCanvas3D.depthBufferWriteEnable;
/* 5129 */     paramCanvas3D.setDepthBufferWriteEnable(false);
/*      */     
/* 5131 */     LightBin lightBin = this.bgOpaqueBin;
/* 5132 */     while (lightBin != null) {
/* 5133 */       if (lightBin.geometryBackground == this.geometryBackground)
/* 5134 */         lightBin.render(paramCanvas3D); 
/* 5135 */       lightBin = lightBin.next;
/*      */     } 
/*      */ 
/*      */     
/* 5139 */     if (this.bgOrderedBins.size() > 0) {
/* 5140 */       renderOrderedBins(paramCanvas3D, this.bgOrderedBins, true);
/*      */     }
/*      */     
/* 5143 */     TransparentRenderingInfo transparentRenderingInfo = this.bgTransparentInfo;
/* 5144 */     while (transparentRenderingInfo != null) {
/* 5145 */       transparentRenderingInfo.render(paramCanvas3D);
/* 5146 */       transparentRenderingInfo = transparentRenderingInfo.next;
/*      */     } 
/* 5148 */     paramCanvas3D.setDepthBufferWriteEnableOverride(false);
/* 5149 */     paramCanvas3D.setDepthBufferWriteEnable(bool);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderOpaque(Canvas3D paramCanvas3D) {
/* 5156 */     LightBin lightBin = this.opaqueBin;
/*      */     
/* 5158 */     while (lightBin != null) {
/*      */       
/* 5160 */       lightBin.render(paramCanvas3D);
/* 5161 */       lightBin = lightBin.next;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderTransparent(Canvas3D paramCanvas3D) {
/* 5170 */     boolean bool = true;
/*      */ 
/*      */     
/* 5173 */     TransparentRenderingInfo transparentRenderingInfo = this.transparentInfo;
/* 5174 */     if (transparentRenderingInfo != null) {
/*      */ 
/*      */       
/* 5177 */       if (paramCanvas3D.view.depthBufferFreezeTransparent) {
/* 5178 */         paramCanvas3D.setDepthBufferWriteEnableOverride(true);
/* 5179 */         bool = paramCanvas3D.depthBufferWriteEnable;
/* 5180 */         paramCanvas3D.setDepthBufferWriteEnable(false);
/*      */       } 
/*      */       
/* 5183 */       if (this.transpSortMode == 0) {
/* 5184 */         while (transparentRenderingInfo != null) {
/* 5185 */           transparentRenderingInfo.render(paramCanvas3D);
/* 5186 */           transparentRenderingInfo = transparentRenderingInfo.next;
/*      */         }
/*      */       
/* 5189 */       } else if (this.transpSortMode == 1) {
/* 5190 */         while (transparentRenderingInfo != null) {
/* 5191 */           transparentRenderingInfo.sortRender(paramCanvas3D);
/* 5192 */           transparentRenderingInfo = transparentRenderingInfo.next;
/*      */         } 
/*      */       } 
/* 5195 */       if (paramCanvas3D.view.depthBufferFreezeTransparent) {
/* 5196 */         paramCanvas3D.setDepthBufferWriteEnableOverride(false);
/* 5197 */         paramCanvas3D.setDepthBufferWriteEnable(bool);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderOrdered(Canvas3D paramCanvas3D) {
/* 5207 */     if (this.orderedBins.size() > 0)
/* 5208 */       renderOrderedBins(paramCanvas3D, this.orderedBins, false); 
/*      */   }
/*      */   
/*      */   void renderOrderedBins(Canvas3D paramCanvas3D, ArrayList paramArrayList, boolean paramBoolean) {
/* 5212 */     int i = paramArrayList.size();
/*      */     
/* 5214 */     for (byte b = 0; b < i; b++) {
/* 5215 */       renderOrderedBin(paramCanvas3D, (OrderedBin)paramArrayList.get(b), paramBoolean);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderOrderedBin(Canvas3D paramCanvas3D, OrderedBin paramOrderedBin, boolean paramBoolean) {
/* 5226 */     boolean bool = true;
/* 5227 */     OrderedGroupRetained orderedGroupRetained = paramOrderedBin.source;
/* 5228 */     boolean bool1 = (orderedGroupRetained instanceof DecalGroupRetained && paramCanvas3D.systemStencilAvailable) ? 1 : 0;
/* 5229 */     int i = paramOrderedBin.orderedCollections.size();
/*      */ 
/*      */     
/* 5232 */     for (byte b = 0; b < i; b++) {
/* 5233 */       byte b1; if (orderedGroupRetained != null && orderedGroupRetained.childIndexOrder != null) {
/* 5234 */         b1 = orderedGroupRetained.childIndexOrder[b];
/*      */       } else {
/*      */         
/* 5237 */         b1 = b;
/*      */       } 
/* 5239 */       OrderedCollection orderedCollection = (OrderedCollection)paramOrderedBin.orderedCollections.get(b1);
/* 5240 */       if (bool1) {
/* 5241 */         if (b1 == 0) {
/* 5242 */           paramCanvas3D.setDepthBufferEnableOverride(true);
/* 5243 */           bool = paramCanvas3D.decal1stChildSetup(paramCanvas3D.ctx);
/* 5244 */         } else if (b1 == 1) {
/*      */           
/* 5246 */           paramCanvas3D.decalNthChildSetup(paramCanvas3D.ctx);
/*      */         } 
/*      */       }
/* 5249 */       if (orderedCollection != null) {
/* 5250 */         LightBin lightBin = orderedCollection.lightBin;
/* 5251 */         while (lightBin != null) {
/* 5252 */           if (!paramBoolean || lightBin.geometryBackground == this.geometryBackground)
/*      */           {
/* 5254 */             lightBin.render(paramCanvas3D);
/*      */           }
/* 5256 */           lightBin = lightBin.next;
/*      */         } 
/* 5258 */         renderOrderedBins(paramCanvas3D, orderedCollection.childOrderedBins, paramBoolean);
/*      */       } 
/*      */     } 
/* 5261 */     if (bool1) {
/* 5262 */       paramCanvas3D.decalReset(paramCanvas3D.ctx, bool);
/* 5263 */       paramCanvas3D.setDepthBufferEnableOverride(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBackground(BackgroundRetained paramBackgroundRetained) {
/* 5273 */     boolean bool = false;
/* 5274 */     BackgroundRetained backgroundRetained = this.geometryBackground;
/* 5275 */     this.geometryBackground = null;
/*      */     
/* 5277 */     if (paramBackgroundRetained != null) {
/* 5278 */       this.background.initColor(paramBackgroundRetained.color);
/* 5279 */       this.background.initImageScaleMode(paramBackgroundRetained.imageScaleMode);
/* 5280 */       this.background.geometryBranch = paramBackgroundRetained.geometryBranch;
/* 5281 */       if (this.background.geometryBranch != null) {
/* 5282 */         this.geometryBackground = paramBackgroundRetained;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5287 */       if (this.background.image != paramBackgroundRetained.image || this.reloadBgTexture) {
/* 5288 */         if (this.background.image != null) {
/* 5289 */           assert this.background.texture != null;
/* 5290 */           addTextureResourceFreeList(this.background.texture);
/* 5291 */           removeNodeComponent(this.background.image);
/*      */         } 
/* 5293 */         if (paramBackgroundRetained.image != null) {
/*      */           
/* 5295 */           this.background.initImage((ImageComponent2D)paramBackgroundRetained.image.source);
/* 5296 */           addNodeComponent(paramBackgroundRetained.image);
/*      */         } else {
/* 5298 */           this.background.initImage(null);
/*      */         } 
/*      */       } 
/* 5301 */       if (backgroundRetained == null) {
/* 5302 */         bool = true;
/*      */       }
/*      */     } else {
/* 5305 */       this.background.initColor(this.black);
/* 5306 */       this.background.geometryBranch = null;
/* 5307 */       if (this.background.image != null) {
/* 5308 */         assert this.background.texture != null;
/* 5309 */         addTextureResourceFreeList(this.background.texture);
/* 5310 */         removeNodeComponent(this.background.image);
/*      */       } 
/* 5312 */       this.background.initImage(null);
/* 5313 */       if (backgroundRetained != null) {
/* 5314 */         bool = true;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5320 */     Canvas3D[] arrayOfCanvas3D = this.view.getCanvases();
/* 5321 */     for (byte b = 0; b < arrayOfCanvas3D.length; b++) {
/* 5322 */       Canvas3D canvas3D = arrayOfCanvas3D[b];
/* 5323 */       synchronized (canvas3D.dirtyMaskLock) {
/* 5324 */         if (bool) {
/* 5325 */           canvas3D.cvDirtyMask[0] = canvas3D.cvDirtyMask[0] | 0x10;
/* 5326 */           canvas3D.cvDirtyMask[1] = canvas3D.cvDirtyMask[1] | 0x10;
/*      */         } 
/* 5328 */         canvas3D.cvDirtyMask[0] = canvas3D.cvDirtyMask[0] | 0x20;
/* 5329 */         canvas3D.cvDirtyMask[1] = canvas3D.cvDirtyMask[1] | 0x20;
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
/*      */   void reEvaluateFog(ArrayList paramArrayList, boolean paramBoolean1, boolean paramBoolean2) {
/* 5344 */     int i = this.renderAtoms.size();
/* 5345 */     for (byte b = 0; b < i; b++) {
/* 5346 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/* 5347 */       if (renderAtom.inRenderBin()) {
/*      */ 
/*      */         
/* 5350 */         FogRetained fogRetained = this.universe.renderingEnvironmentStructure.getInfluencingFog(renderAtom, this.view);
/*      */ 
/*      */ 
/*      */         
/* 5354 */         if (paramBoolean2 && renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/* 5355 */           AppearanceRetained appearanceRetained; Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/* 5356 */           if (arrayOfObject[false] == Boolean.TRUE) {
/* 5357 */             appearanceRetained = (AppearanceRetained)arrayOfObject[1];
/*      */           } else {
/*      */             
/* 5360 */             appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */           } 
/*      */           
/* 5363 */           if (appearanceRetained == renderAtom.app) {
/* 5364 */             if (renderAtom.envSet.fog != fogRetained)
/*      */             {
/*      */               
/* 5367 */               getNewEnvironment(renderAtom, renderAtom.lights, fogRetained, renderAtom.modelClip, renderAtom.app);
/*      */             }
/*      */           } else {
/*      */             
/* 5371 */             if (renderAtom.geometryAtom.source.otherAppearance != appearanceRetained) {
/* 5372 */               if (renderAtom.geometryAtom.source.otherAppearance != null)
/* 5373 */                 renderAtom.geometryAtom.source.otherAppearance.sgApp.removeAMirrorUser(renderAtom.geometryAtom.source); 
/* 5374 */               if (appearanceRetained != renderAtom.geometryAtom.source.appearance) {
/* 5375 */                 if (appearanceRetained != null) {
/* 5376 */                   appearanceRetained.sgApp.addAMirrorUser(renderAtom.geometryAtom.source);
/*      */                 }
/* 5378 */                 renderAtom.geometryAtom.source.otherAppearance = appearanceRetained;
/*      */               } else {
/*      */                 
/* 5381 */                 renderAtom.geometryAtom.source.otherAppearance = null;
/*      */               } 
/*      */             } 
/*      */             
/* 5385 */             if (renderAtom.envSet.fog == fogRetained) {
/* 5386 */               renderAtom.app = appearanceRetained;
/* 5387 */               EnvironmentSet environmentSet = renderAtom.envSet;
/* 5388 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 5389 */               reInsertAttributeBin(environmentSet, renderAtom);
/*      */             } else {
/*      */               
/* 5392 */               getNewEnvironment(renderAtom, renderAtom.lights, fogRetained, renderAtom.modelClip, appearanceRetained);
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 5398 */         else if (renderAtom.envSet.fog != fogRetained) {
/*      */           
/* 5400 */           getNewEnvironment(renderAtom, renderAtom.lights, fogRetained, renderAtom.modelClip, renderAtom.app);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5405 */     if (paramBoolean1) {
/* 5406 */       updateCanvasForDirtyFog(paramArrayList);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateCanvasForDirtyFog(ArrayList paramArrayList) {
/* 5416 */     int i = paramArrayList.size();
/*      */     
/* 5418 */     for (byte b = 0; b < i; b++) {
/* 5419 */       FogRetained fogRetained = (FogRetained)paramArrayList.get(b);
/* 5420 */       UnorderList unorderList = fogRetained.environmentSets;
/* 5421 */       synchronized (unorderList) {
/* 5422 */         int j = unorderList.size();
/* 5423 */         EnvironmentSet[] arrayOfEnvironmentSet = (EnvironmentSet[])unorderList.toArray(false);
/* 5424 */         for (byte b1 = 0; b1 < j; b1++) {
/* 5425 */           EnvironmentSet environmentSet = arrayOfEnvironmentSet[b1];
/* 5426 */           environmentSet.canvasDirty |= 0x2000;
/* 5427 */           if (!environmentSet.onUpdateList) {
/* 5428 */             this.objUpdateList.add(environmentSet);
/* 5429 */             environmentSet.onUpdateList = true;
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
/*      */   void reEvaluateModelClip(ArrayList paramArrayList, boolean paramBoolean1, boolean paramBoolean2) {
/* 5445 */     int i = this.renderAtoms.size();
/* 5446 */     for (byte b = 0; b < i; b++) {
/* 5447 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/* 5448 */       if (renderAtom.inRenderBin()) {
/*      */ 
/*      */         
/* 5451 */         ModelClipRetained modelClipRetained = this.universe.renderingEnvironmentStructure.getInfluencingModelClip(renderAtom, this.view);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5457 */         if (paramBoolean2 && renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/* 5458 */           AppearanceRetained appearanceRetained; Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/* 5459 */           if (arrayOfObject[false] == Boolean.TRUE) {
/* 5460 */             appearanceRetained = (AppearanceRetained)arrayOfObject[1];
/*      */           } else {
/*      */             
/* 5463 */             appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */           } 
/*      */           
/* 5466 */           if (appearanceRetained == renderAtom.app) {
/* 5467 */             if (renderAtom.envSet.modelClip != modelClipRetained)
/*      */             {
/*      */               
/* 5470 */               getNewEnvironment(renderAtom, renderAtom.lights, renderAtom.fog, renderAtom.envSet.modelClip, renderAtom.app);
/*      */             }
/*      */           }
/*      */           else {
/*      */             
/* 5475 */             if (renderAtom.geometryAtom.source.otherAppearance != appearanceRetained) {
/* 5476 */               if (renderAtom.geometryAtom.source.otherAppearance != null)
/* 5477 */                 renderAtom.geometryAtom.source.otherAppearance.sgApp.removeAMirrorUser(renderAtom.geometryAtom.source); 
/* 5478 */               if (appearanceRetained != renderAtom.geometryAtom.source.appearance) {
/* 5479 */                 if (appearanceRetained != null) {
/* 5480 */                   appearanceRetained.sgApp.addAMirrorUser(renderAtom.geometryAtom.source);
/*      */                 }
/* 5482 */                 renderAtom.geometryAtom.source.otherAppearance = appearanceRetained;
/*      */               } else {
/*      */                 
/* 5485 */                 renderAtom.geometryAtom.source.otherAppearance = null;
/*      */               } 
/*      */             } 
/* 5488 */             if (renderAtom.envSet.modelClip == modelClipRetained) {
/* 5489 */               renderAtom.app = appearanceRetained;
/* 5490 */               EnvironmentSet environmentSet = renderAtom.envSet;
/* 5491 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 5492 */               reInsertAttributeBin(environmentSet, renderAtom);
/*      */             }
/*      */             else {
/*      */               
/* 5496 */               getNewEnvironment(renderAtom, renderAtom.lights, renderAtom.fog, modelClipRetained, appearanceRetained);
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 5502 */         else if (renderAtom.envSet.modelClip != modelClipRetained) {
/*      */           
/* 5504 */           getNewEnvironment(renderAtom, renderAtom.lights, renderAtom.fog, modelClipRetained, renderAtom.app);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5509 */     if (paramBoolean1) {
/* 5510 */       updateCanvasForDirtyModelClip(paramArrayList);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateCanvasForDirtyModelClip(ArrayList paramArrayList) {
/* 5517 */     boolean bool = false;
/*      */ 
/*      */     
/* 5520 */     int i = paramArrayList.size();
/*      */ 
/*      */     
/* 5523 */     for (byte b = 0; b < i; b++) {
/* 5524 */       ModelClipRetained modelClipRetained = (ModelClipRetained)paramArrayList.get(b);
/*      */ 
/*      */       
/* 5527 */       bool = false; byte b1;
/* 5528 */       for (b1 = 0; b1 < 6; b1++) {
/* 5529 */         if (modelClipRetained.enables[b1])
/* 5530 */           bool |= true << b1; 
/*      */       } 
/* 5532 */       UnorderList unorderList = modelClipRetained.environmentSets;
/* 5533 */       synchronized (unorderList) {
/* 5534 */         int j = unorderList.size();
/* 5535 */         EnvironmentSet[] arrayOfEnvironmentSet = (EnvironmentSet[])unorderList.toArray(false);
/* 5536 */         for (b1 = 0; b1 < j; b1++) {
/* 5537 */           EnvironmentSet environmentSet = arrayOfEnvironmentSet[b1];
/* 5538 */           environmentSet.canvasDirty |= 0x4000;
/* 5539 */           environmentSet.enableMCMaskCache = bool;
/* 5540 */           if (!environmentSet.onUpdateList) {
/* 5541 */             this.objUpdateList.add(environmentSet);
/* 5542 */             environmentSet.onUpdateList = true;
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
/*      */   void reEvaluateLights(boolean paramBoolean) {
/* 5556 */     int i = this.renderAtoms.size();
/* 5557 */     for (byte b = 0; b < i; b++) {
/* 5558 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/* 5559 */       if (renderAtom.inRenderBin()) {
/*      */ 
/*      */         
/* 5562 */         LightRetained[] arrayOfLightRetained = this.universe.renderingEnvironmentStructure.getInfluencingLights(renderAtom, this.view);
/*      */ 
/*      */ 
/*      */         
/* 5566 */         if (paramBoolean && renderAtom.geometryAtom.source.appearanceOverrideEnable) {
/* 5567 */           AppearanceRetained appearanceRetained; Object[] arrayOfObject = this.universe.renderingEnvironmentStructure.getInfluencingAppearance(renderAtom, this.view);
/* 5568 */           if (arrayOfObject[false] == Boolean.TRUE) {
/* 5569 */             appearanceRetained = (AppearanceRetained)arrayOfObject[1];
/*      */           } else {
/*      */             
/* 5572 */             appearanceRetained = renderAtom.geometryAtom.source.appearance;
/*      */           } 
/*      */           
/* 5575 */           if (appearanceRetained == renderAtom.app) {
/* 5576 */             if (renderAtom.lights != arrayOfLightRetained && !renderAtom.envSet.equalLights(arrayOfLightRetained))
/*      */             {
/*      */               
/* 5579 */               getNewEnvironment(renderAtom, arrayOfLightRetained, renderAtom.fog, renderAtom.modelClip, renderAtom.app);
/*      */             }
/*      */           } else {
/*      */             
/* 5583 */             if (renderAtom.geometryAtom.source.otherAppearance != appearanceRetained) {
/* 5584 */               if (renderAtom.geometryAtom.source.otherAppearance != null)
/* 5585 */                 renderAtom.geometryAtom.source.otherAppearance.sgApp.removeAMirrorUser(renderAtom.geometryAtom.source); 
/* 5586 */               if (appearanceRetained != renderAtom.geometryAtom.source.appearance) {
/* 5587 */                 if (appearanceRetained != null) {
/* 5588 */                   appearanceRetained.sgApp.addAMirrorUser(renderAtom.geometryAtom.source);
/*      */                 }
/* 5590 */                 renderAtom.geometryAtom.source.otherAppearance = appearanceRetained;
/*      */               } else {
/*      */                 
/* 5593 */                 renderAtom.geometryAtom.source.otherAppearance = null;
/*      */               } 
/*      */             } 
/* 5596 */             if (renderAtom.lights == arrayOfLightRetained || renderAtom.envSet.equalLights(arrayOfLightRetained)) {
/* 5597 */               renderAtom.app = appearanceRetained;
/* 5598 */               EnvironmentSet environmentSet = renderAtom.envSet;
/* 5599 */               renderAtom.renderMolecule.removeRenderAtom(renderAtom);
/* 5600 */               reInsertAttributeBin(environmentSet, renderAtom);
/*      */             } else {
/*      */               
/* 5603 */               getNewEnvironment(renderAtom, arrayOfLightRetained, renderAtom.fog, renderAtom.modelClip, appearanceRetained);
/*      */             }
/*      */           
/*      */           }
/*      */         
/* 5608 */         } else if (renderAtom.lights != arrayOfLightRetained && !renderAtom.envSet.equalLights(arrayOfLightRetained)) {
/*      */           
/* 5610 */           getNewEnvironment(renderAtom, arrayOfLightRetained, renderAtom.fog, renderAtom.modelClip, renderAtom.app);
/*      */         } 
/*      */       } 
/*      */     } 
/* 5614 */     if (this.changedLts.size() > 0) {
/* 5615 */       updateCanvasForDirtyLights(this.changedLts);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateCanvasForDirtyLights(ArrayList paramArrayList) {
/* 5624 */     int i = paramArrayList.size();
/*      */ 
/*      */ 
/*      */     
/* 5628 */     for (byte b = 0; b < i; b++) {
/* 5629 */       LightRetained lightRetained = (LightRetained)paramArrayList.get(b);
/* 5630 */       UnorderList unorderList = lightRetained.environmentSets;
/* 5631 */       synchronized (unorderList) {
/* 5632 */         EnvironmentSet[] arrayOfEnvironmentSet = (EnvironmentSet[])unorderList.toArray(false);
/* 5633 */         int j = unorderList.size();
/*      */         
/* 5635 */         if (lightRetained.nodeType == 5) {
/* 5636 */           for (byte b1 = 0; b1 < j; b1++) {
/* 5637 */             EnvironmentSet environmentSet = arrayOfEnvironmentSet[b1];
/* 5638 */             environmentSet.canvasDirty |= 0x100;
/* 5639 */             if (!environmentSet.onUpdateList) {
/* 5640 */               this.objUpdateList.add(environmentSet);
/* 5641 */               environmentSet.onUpdateList = true;
/*      */             } 
/*      */           } 
/*      */         } else {
/* 5645 */           for (byte b1 = 0; b1 < j; b1++) {
/* 5646 */             EnvironmentSet environmentSet = arrayOfEnvironmentSet[b1];
/* 5647 */             int k = 0;
/* 5648 */             int n = environmentSet.lights.size();
/* 5649 */             for (byte b2 = 0; b2 < n; b2++) {
/* 5650 */               LightRetained lightRetained1 = (LightRetained)environmentSet.lights.get(b2);
/* 5651 */               if (lightRetained == lightRetained1) {
/* 5652 */                 k = 1 << environmentSet.ltPos[b2];
/* 5653 */                 if (lightRetained1.lightOn == true) {
/* 5654 */                   environmentSet.enableMaskCache |= (1 << environmentSet.ltPos[b2]);
/*      */                   break;
/*      */                 } 
/* 5657 */                 environmentSet.enableMaskCache &= (1 << environmentSet.ltPos[b2]);
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 5662 */             environmentSet.canvasDirty |= 0x80;
/* 5663 */             if (!environmentSet.onUpdateList) {
/* 5664 */               this.objUpdateList.add(environmentSet);
/* 5665 */               environmentSet.onUpdateList = true;
/*      */             } 
/* 5667 */             if (environmentSet.lightBin != null) {
/* 5668 */               environmentSet.lightBin.canvasDirty |= 0x40;
/* 5669 */               environmentSet.lightBin.lightDirtyMaskCache |= k;
/* 5670 */               if (!environmentSet.lightBin.onUpdateList) {
/* 5671 */                 environmentSet.lightBin.onUpdateList = true;
/* 5672 */                 this.objUpdateList.add(environmentSet.lightBin);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 5682 */   void addTextureResourceFreeList(TextureRetained paramTextureRetained) { this.toBeAddedTextureResourceFreeList.add(paramTextureRetained); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reEvaluateEnv(ArrayList paramArrayList1, ArrayList paramArrayList2, ArrayList paramArrayList3, boolean paramBoolean1, boolean paramBoolean2) {
/* 5691 */     reEvaluateAllRenderAtoms(paramBoolean2);
/*      */ 
/*      */     
/* 5694 */     if (paramBoolean1) {
/*      */       
/* 5696 */       if (paramArrayList1.size() > 0)
/* 5697 */         updateCanvasForDirtyLights(paramArrayList1); 
/* 5698 */       if (paramArrayList2.size() > 0)
/* 5699 */         updateCanvasForDirtyFog(paramArrayList2); 
/* 5700 */       if (paramArrayList3.size() > 0) {
/* 5701 */         updateCanvasForDirtyModelClip(paramArrayList3);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 5707 */   void updateInfVworldToVpc() { this.vworldToVpc.getRotation(this.infVworldToVpc); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void lockGeometry() {
/* 5721 */     int i = this.lockGeometryList.size(); byte b;
/* 5722 */     for (b = 0; b < i; b++) {
/* 5723 */       GeometryRetained geometryRetained = (GeometryRetained)this.lockGeometryList.get(b);
/* 5724 */       geometryRetained.geomLock.getLock();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5729 */     i = this.dlistLockList.size();
/* 5730 */     for (b = 0; b < i; b++) {
/* 5731 */       GeometryRetained geometryRetained = (GeometryRetained)this.dlistLockList.get(b);
/* 5732 */       geometryRetained.geomLock.getLock();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5737 */     i = this.nodeComponentList.size();
/* 5738 */     for (b = 0; b < i; b++) {
/* 5739 */       ImageComponentRetained imageComponentRetained = (ImageComponentRetained)this.nodeComponentList.get(b);
/* 5740 */       imageComponentRetained.geomLock.getLock();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void releaseGeometry() {
/* 5749 */     int i = this.lockGeometryList.size(); byte b;
/* 5750 */     for (b = 0; b < i; b++) {
/* 5751 */       GeometryRetained geometryRetained = (GeometryRetained)this.lockGeometryList.get(b);
/* 5752 */       geometryRetained.geomLock.unLock();
/*      */     } 
/*      */     
/* 5755 */     i = this.dlistLockList.size();
/* 5756 */     for (b = 0; b < i; b++) {
/* 5757 */       GeometryRetained geometryRetained = (GeometryRetained)this.dlistLockList.get(b);
/* 5758 */       geometryRetained.geomLock.unLock();
/*      */     } 
/*      */     
/* 5761 */     this.dlistLockList.clear();
/*      */     
/* 5763 */     i = this.nodeComponentList.size();
/* 5764 */     for (b = 0; b < i; b++) {
/* 5765 */       ImageComponentRetained imageComponentRetained = (ImageComponentRetained)this.nodeComponentList.get(b);
/* 5766 */       imageComponentRetained.geomLock.unLock();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5774 */   void addGeometryToLockList(Object paramObject) { this.lockGeometryList.add(paramObject); }
/*      */ 
/*      */ 
/*      */   
/* 5778 */   void removeGeometryFromLockList(Object paramObject) { this.lockGeometryList.remove(paramObject); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5787 */   void addDirtyReferenceGeometry(Object paramObject) { this.dirtyReferenceGeomList.add(paramObject); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5792 */   void addNodeComponent(Object paramObject) { this.newNodeComponentList.add(paramObject); }
/*      */ 
/*      */ 
/*      */   
/* 5796 */   void removeNodeComponent(Object paramObject) { this.removeNodeComponentList.add(paramObject); }
/*      */ 
/*      */ 
/*      */   
/* 5800 */   void addDirtyNodeComponent(Object paramObject) { this.dirtyNodeComponentList.add(paramObject); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearDirtyOrientedRAs() {
/* 5809 */     int i = this.dirtyOrientedRAs.size();
/*      */ 
/*      */     
/* 5812 */     for (byte b = 0; b < i; b++) {
/* 5813 */       RenderAtom renderAtom = (RenderAtom)this.dirtyOrientedRAs.get(b);
/* 5814 */       renderAtom.dirtyMask &= (RenderAtom.IN_DIRTY_ORIENTED_RAs ^ 0xFFFFFFFF);
/*      */     } 
/* 5816 */     this.dirtyOrientedRAs.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateOrientedRAs() {
/* 5827 */     Canvas3D canvas3D = this.view.getCanvas3D(0);
/* 5828 */     if (this.view.viewCache.vcDirtyMask != 0) {
/* 5829 */       int i = this.orientedRAs.size();
/*      */ 
/*      */       
/*      */       byte b;
/*      */       
/* 5834 */       for (b = 0; b < i; b++) {
/* 5835 */         RenderAtom renderAtom = (RenderAtom)this.orientedRAs.get(b);
/* 5836 */         OrientedShape3DRetained orientedShape3DRetained = (OrientedShape3DRetained)renderAtom.geometryAtom.source;
/* 5837 */         orientedShape3DRetained.orientedTransformDirty = true;
/*      */       } 
/*      */       
/* 5840 */       for (b = 0; b < i; b++) {
/* 5841 */         RenderAtom renderAtom = (RenderAtom)this.orientedRAs.get(b);
/* 5842 */         OrientedShape3DRetained orientedShape3DRetained = (OrientedShape3DRetained)renderAtom.geometryAtom.source;
/* 5843 */         if (orientedShape3DRetained.orientedTransformDirty) {
/* 5844 */           orientedShape3DRetained.updateOrientedTransform(canvas3D, this.view.viewIndex);
/* 5845 */           orientedShape3DRetained.orientedTransformDirty = false;
/*      */         } 
/* 5847 */         renderAtom.updateOrientedTransform();
/*      */       } 
/*      */     } else {
/* 5850 */       int i = this.cachedDirtyOrientedRAs.size();
/*      */       
/*      */       byte b;
/*      */       
/* 5854 */       for (b = 0; b < i; b++) {
/* 5855 */         RenderAtom renderAtom = (RenderAtom)this.cachedDirtyOrientedRAs.get(b);
/* 5856 */         OrientedShape3DRetained orientedShape3DRetained = (OrientedShape3DRetained)renderAtom.geometryAtom.source;
/* 5857 */         orientedShape3DRetained.orientedTransformDirty = true;
/*      */       } 
/*      */       
/* 5860 */       for (b = 0; b < i; b++) {
/* 5861 */         RenderAtom renderAtom = (RenderAtom)this.cachedDirtyOrientedRAs.get(b);
/* 5862 */         OrientedShape3DRetained orientedShape3DRetained = (OrientedShape3DRetained)renderAtom.geometryAtom.source;
/* 5863 */         if (orientedShape3DRetained.orientedTransformDirty) {
/* 5864 */           orientedShape3DRetained.updateOrientedTransform(canvas3D, this.view.viewIndex);
/* 5865 */           orientedShape3DRetained.orientedTransformDirty = false;
/*      */         } 
/*      */         
/* 5868 */         renderAtom.updateOrientedTransform();
/*      */       } 
/*      */     } 
/* 5871 */     this.cachedDirtyOrientedRAs.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeARenderAtom(RenderAtom paramRenderAtom) {
/* 5880 */     paramRenderAtom.setRenderBin(false);
/* 5881 */     paramRenderAtom.renderMolecule.removeRenderAtom(paramRenderAtom);
/* 5882 */     if (paramRenderAtom.inDirtyOrientedRAs()) {
/* 5883 */       this.dirtyOrientedRAs.remove(this.dirtyOrientedRAs.indexOf(paramRenderAtom));
/* 5884 */       paramRenderAtom.dirtyMask &= (RenderAtom.IN_DIRTY_ORIENTED_RAs ^ 0xFFFFFFFF);
/*      */     } 
/* 5886 */     if (paramRenderAtom.inDepthSortList()) {
/* 5887 */       this.dirtyDepthSortRenderAtom.remove(paramRenderAtom);
/* 5888 */       paramRenderAtom.dirtyMask &= (RenderAtom.IN_SORTED_POS_DIRTY_TRANSP_LIST ^ 0xFFFFFFFF);
/* 5889 */       this.numDirtyTinfo -= paramRenderAtom.rListInfo.length;
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
/*      */   void removeAllRenderAtoms() {
/* 5903 */     int i = this.renderAtoms.size();
/*      */     byte b;
/* 5905 */     for (b = 0; b < i; b++) {
/* 5906 */       RenderAtom renderAtom = (RenderAtom)this.renderAtoms.get(b);
/* 5907 */       RenderMolecule renderMolecule = renderAtom.renderMolecule;
/* 5908 */       removeARenderAtom(renderAtom);
/* 5909 */       renderMolecule.updateRemoveRenderAtoms();
/*      */     } 
/* 5911 */     this.renderAtoms.clear();
/*      */     
/* 5913 */     clearAllUpdateObjectState();
/*      */ 
/*      */     
/* 5916 */     this.renderMoleculeList.clear();
/* 5917 */     this.sharedDList.clear();
/* 5918 */     this.lockGeometryList.clear();
/*      */     
/* 5920 */     for (b = 0; b < this.orderedBins.size(); b++) {
/* 5921 */       removeOrderedBin((OrderedBin)this.orderedBins.get(b));
/*      */     }
/* 5923 */     this.orderedBins.clear();
/* 5924 */     this.bgOrderedBins.clear();
/* 5925 */     this.nodeComponentList.clear();
/* 5926 */     this.orientedRAs.clear();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5931 */     this.geometryBackground = null;
/*      */   }
/*      */ 
/*      */   
/*      */   void removeOrderedBin(OrderedBin paramOrderedBin) {
/* 5936 */     for (byte b = 0; b < paramOrderedBin.orderedCollections.size(); b++) {
/* 5937 */       OrderedCollection orderedCollection = (OrderedCollection)paramOrderedBin.orderedCollections.get(b);
/* 5938 */       if (orderedCollection != null)
/*      */       {
/*      */         
/* 5941 */         for (byte b1 = 0; b1 < orderedCollection.childOrderedBins.size(); b1++)
/* 5942 */           removeOrderedBin((OrderedBin)orderedCollection.childOrderedBins.get(b1)); 
/*      */       }
/*      */     } 
/* 5945 */     if (paramOrderedBin.source != null) {
/* 5946 */       paramOrderedBin.source.setOrderedBin(null, this.view.viewIndex);
/* 5947 */       paramOrderedBin.source = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 5953 */   void removeGeometryDlist(RenderAtomListInfo paramRenderAtomListInfo) { this.removeDlist.add(paramRenderAtomListInfo); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5958 */   void addGeometryDlist(RenderAtomListInfo paramRenderAtomListInfo) { this.addDlist.add(paramRenderAtomListInfo); }
/*      */ 
/*      */ 
/*      */   
/*      */   void dumpBin(LightBin paramLightBin) {
/* 5963 */     LightBin lightBin = paramLightBin;
/* 5964 */     while (lightBin != null) {
/* 5965 */       System.err.println("LightBin = " + lightBin);
/* 5966 */       EnvironmentSet environmentSet = lightBin.environmentSetList;
/* 5967 */       while (environmentSet != null) {
/* 5968 */         System.err.println("   EnvSet = " + environmentSet);
/* 5969 */         AttributeBin attributeBin = environmentSet.attributeBinList;
/* 5970 */         while (attributeBin != null) {
/* 5971 */           System.err.println("      ABin = " + attributeBin);
/* 5972 */           ShaderBin shaderBin = attributeBin.shaderBinList;
/* 5973 */           while (shaderBin != null) {
/* 5974 */             System.err.println("         SBin = " + shaderBin);
/* 5975 */             TextureBin textureBin = shaderBin.textureBinList;
/* 5976 */             while (textureBin != null) {
/* 5977 */               System.err.println("             Tbin = " + textureBin);
/* 5978 */               RenderMolecule renderMolecule = textureBin.opaqueRMList;
/* 5979 */               System.err.println("===> Begin Dumping OpaqueBin");
/* 5980 */               dumpRM(renderMolecule);
/* 5981 */               System.err.println("===> End Dumping OpaqueBin");
/* 5982 */               renderMolecule = textureBin.transparentRMList;
/* 5983 */               System.err.println("===> Begin Dumping transparentBin");
/* 5984 */               dumpRM(renderMolecule);
/* 5985 */               System.err.println("===> End Dumping transparentBin");
/* 5986 */               textureBin = textureBin.next;
/*      */             } 
/* 5988 */             shaderBin = shaderBin.next;
/*      */           } 
/* 5990 */           attributeBin = attributeBin.next;
/*      */         } 
/* 5992 */         environmentSet = environmentSet.next;
/*      */       } 
/* 5994 */       lightBin = lightBin.next;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void dumpRM(RenderMolecule paramRenderMolecule) {
/* 6000 */     while (paramRenderMolecule != null) {
/* 6001 */       System.err.println("            rm = " + paramRenderMolecule + " numRAs = " + paramRenderMolecule.numRenderAtoms);
/* 6002 */       System.err.println("            primaryRenderAtomList = " + paramRenderMolecule.primaryRenderAtomList);
/*      */       
/* 6004 */       RenderAtomListInfo renderAtomListInfo = paramRenderMolecule.primaryRenderAtomList;
/* 6005 */       while (renderAtomListInfo != null) {
/* 6006 */         System.err.println("             rinfo = " + renderAtomListInfo);
/* 6007 */         System.err.println("             rinfo.ra.localeVwcBounds = " + renderAtomListInfo.renderAtom.localeVwcBounds);
/*      */         
/* 6009 */         System.err.println("             rinfo.ra.ga.so.vwcBounds = " + renderAtomListInfo.renderAtom.geometryAtom.source.vwcBounds);
/*      */         
/* 6011 */         System.err.println("             geometry = " + renderAtomListInfo.geometry());
/*      */         
/* 6013 */         renderAtomListInfo = renderAtomListInfo.next;
/*      */       } 
/* 6015 */       System.err.println("            separateDlistRenderAtomList = " + paramRenderMolecule.separateDlistRenderAtomList);
/*      */       
/* 6017 */       renderAtomListInfo = paramRenderMolecule.separateDlistRenderAtomList;
/* 6018 */       while (renderAtomListInfo != null) {
/* 6019 */         System.err.println("             rinfo = " + renderAtomListInfo);
/* 6020 */         System.err.println("             rinfo.ra.localeVwcBounds = " + renderAtomListInfo.renderAtom.localeVwcBounds);
/*      */         
/* 6022 */         System.err.println("             rinfo.ra.ga.so.vwcBounds = " + renderAtomListInfo.renderAtom.geometryAtom.source.vwcBounds);
/*      */         
/* 6024 */         System.err.println("             geometry = " + renderAtomListInfo.geometry());
/* 6025 */         renderAtomListInfo = renderAtomListInfo.next;
/*      */       } 
/* 6027 */       System.err.println("            vertexArrayRenderAtomList = " + paramRenderMolecule.vertexArrayRenderAtomList);
/*      */       
/* 6029 */       if (paramRenderMolecule.next == null) {
/* 6030 */         paramRenderMolecule = paramRenderMolecule.nextMap;
/*      */         continue;
/*      */       } 
/* 6033 */       paramRenderMolecule = paramRenderMolecule.next;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeTransparentObject(Object paramObject) {
/* 6040 */     if (paramObject instanceof TextureBin) {
/* 6041 */       TextureBin textureBin = (TextureBin)paramObject;
/* 6042 */       if (textureBin.environmentSet.lightBin.geometryBackground != null) {
/* 6043 */         TransparentRenderingInfo transparentRenderingInfo = textureBin.parentTInfo;
/*      */ 
/*      */         
/* 6046 */         if (transparentRenderingInfo == this.bgTransparentInfo) {
/* 6047 */           this.bgTransparentInfo = this.bgTransparentInfo.next;
/* 6048 */           if (this.bgTransparentInfo != null) {
/* 6049 */             this.bgTransparentInfo.prev = null;
/*      */           }
/*      */         } else {
/* 6052 */           transparentRenderingInfo.prev.next = transparentRenderingInfo.next;
/* 6053 */           if (transparentRenderingInfo.next != null)
/* 6054 */             transparentRenderingInfo.next.prev = transparentRenderingInfo.prev; 
/*      */         } 
/* 6056 */         transparentRenderingInfo.prev = null;
/* 6057 */         transparentRenderingInfo.next = null;
/* 6058 */         textureBin.parentTInfo = null;
/*      */       } else {
/*      */         
/* 6061 */         int i = this.allTransparentObjects.indexOf(paramObject);
/* 6062 */         if (i == -1) {
/*      */           return;
/*      */         }
/*      */         
/* 6066 */         this.allTransparentObjects.remove(i);
/*      */         
/* 6068 */         TransparentRenderingInfo transparentRenderingInfo = textureBin.parentTInfo;
/*      */ 
/*      */         
/* 6071 */         if (transparentRenderingInfo == this.transparentInfo) {
/* 6072 */           this.transparentInfo = this.transparentInfo.next;
/* 6073 */           if (this.transparentInfo != null) {
/* 6074 */             this.transparentInfo.prev = null;
/*      */           }
/*      */         } else {
/* 6077 */           transparentRenderingInfo.prev.next = transparentRenderingInfo.next;
/* 6078 */           if (transparentRenderingInfo.next != null)
/* 6079 */             transparentRenderingInfo.next.prev = transparentRenderingInfo.prev; 
/*      */         } 
/* 6081 */         transparentRenderingInfo.prev = null;
/* 6082 */         transparentRenderingInfo.next = null;
/* 6083 */         textureBin.parentTInfo = null;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 6088 */       int i = this.allTransparentObjects.indexOf(paramObject);
/* 6089 */       if (i == -1) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 6094 */       this.allTransparentObjects.remove(i);
/* 6095 */       RenderAtom renderAtom = (RenderAtom)paramObject;
/* 6096 */       for (byte b = 0; b < renderAtom.parentTInfo.length; b++) {
/*      */         
/* 6098 */         TransparentRenderingInfo transparentRenderingInfo = renderAtom.parentTInfo[b];
/*      */         
/* 6100 */         if (transparentRenderingInfo != null) {
/*      */ 
/*      */ 
/*      */           
/* 6104 */           if (transparentRenderingInfo == this.transparentInfo) {
/* 6105 */             this.transparentInfo = this.transparentInfo.next;
/* 6106 */             if (this.transparentInfo != null) {
/* 6107 */               this.transparentInfo.prev = null;
/*      */             }
/*      */           } else {
/* 6110 */             transparentRenderingInfo.prev.next = transparentRenderingInfo.next;
/* 6111 */             if (transparentRenderingInfo.next != null)
/* 6112 */               transparentRenderingInfo.next.prev = transparentRenderingInfo.prev; 
/*      */           } 
/* 6114 */           transparentRenderingInfo.prev = null;
/* 6115 */           transparentRenderingInfo.next = null;
/* 6116 */           this.nElements--;
/* 6117 */           renderAtom.parentTInfo[b] = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void updateTransparentInfo(RenderAtom paramRenderAtom) {
/* 6125 */     for (byte b = 0; b < paramRenderAtom.parentTInfo.length; b++) {
/*      */       
/* 6127 */       if (paramRenderAtom.parentTInfo[b] != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 6134 */         (paramRenderAtom.parentTInfo[b]).rm = paramRenderAtom.renderMolecule;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   void addTransparentObject(Object paramObject) {
/* 6140 */     if (paramObject instanceof TextureBin) {
/* 6141 */       TextureBin textureBin = (TextureBin)paramObject;
/*      */       
/* 6143 */       if (textureBin.environmentSet.lightBin.geometryBackground != null) {
/* 6144 */         this.bgTransparentInfo = computeDirtyAcrossTransparentBins(textureBin, this.bgTransparentInfo);
/*      */       } else {
/*      */         
/* 6147 */         this.allTransparentObjects.add(paramObject);
/* 6148 */         this.transparentInfo = computeDirtyAcrossTransparentBins(textureBin, this.transparentInfo);
/*      */       } 
/*      */     } else {
/*      */       
/* 6152 */       this.allTransparentObjects.add(paramObject);
/* 6153 */       RenderAtom renderAtom = (RenderAtom)paramObject;
/* 6154 */       if (renderAtom.parentTInfo == null) {
/* 6155 */         renderAtom.parentTInfo = new TransparentRenderingInfo[renderAtom.rListInfo.length];
/*      */       }
/* 6157 */       computeDirtyAcrossTransparentBins(renderAtom);
/*      */       
/* 6159 */       renderAtom.geometryAtom.updateCentroid();
/* 6160 */       if (this.dirtyDepthSortRenderAtom.add(renderAtom)) {
/* 6161 */         this.numDirtyTinfo += renderAtom.rListInfo.length;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6168 */       renderAtom.dirtyMask |= RenderAtom.IN_SORTED_POS_DIRTY_TRANSP_LIST;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 6174 */   TransparentRenderingInfo getTransparentInfo() { return new TransparentRenderingInfo(); }
/*      */ 
/*      */   
/*      */   TransparentRenderingInfo computeDirtyAcrossTransparentBins(TextureBin paramTextureBin, TransparentRenderingInfo paramTransparentRenderingInfo) {
/* 6178 */     TransparentRenderingInfo transparentRenderingInfo = getTransparentInfo();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6184 */     transparentRenderingInfo.rm = paramTextureBin.transparentRMList;
/* 6185 */     paramTextureBin.parentTInfo = transparentRenderingInfo;
/* 6186 */     if (paramTransparentRenderingInfo == null) {
/* 6187 */       paramTransparentRenderingInfo = transparentRenderingInfo;
/* 6188 */       transparentRenderingInfo.prev = null;
/* 6189 */       transparentRenderingInfo.next = null;
/*      */     }
/*      */     else {
/*      */       
/* 6193 */       transparentRenderingInfo.next = paramTransparentRenderingInfo;
/* 6194 */       paramTransparentRenderingInfo.prev = transparentRenderingInfo;
/* 6195 */       paramTransparentRenderingInfo = transparentRenderingInfo;
/*      */     } 
/* 6197 */     return paramTransparentRenderingInfo;
/*      */   }
/*      */   
/*      */   void computeDirtyAcrossTransparentBins(RenderAtom paramRenderAtom) {
/* 6201 */     for (byte b = 0; b < paramRenderAtom.parentTInfo.length; b++) {
/* 6202 */       if (paramRenderAtom.rListInfo[b].geometry() == null) {
/* 6203 */         paramRenderAtom.parentTInfo[b] = null;
/*      */       } else {
/*      */         
/* 6206 */         this.nElements++;
/* 6207 */         TransparentRenderingInfo transparentRenderingInfo = getTransparentInfo();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 6213 */         transparentRenderingInfo.rm = paramRenderAtom.renderMolecule;
/* 6214 */         transparentRenderingInfo.rInfo = paramRenderAtom.rListInfo[b];
/* 6215 */         paramRenderAtom.parentTInfo[b] = transparentRenderingInfo;
/* 6216 */         if (this.transparentInfo == null) {
/* 6217 */           this.transparentInfo = transparentRenderingInfo;
/* 6218 */           transparentRenderingInfo.prev = null;
/* 6219 */           transparentRenderingInfo.next = null;
/*      */         } else {
/*      */           
/* 6222 */           transparentRenderingInfo.prev = null;
/* 6223 */           transparentRenderingInfo.next = this.transparentInfo;
/* 6224 */           this.transparentInfo.prev = transparentRenderingInfo;
/* 6225 */           this.transparentInfo = transparentRenderingInfo;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void processRenderAtomTransparentInfo(RenderAtomListInfo paramRenderAtomListInfo, ArrayList paramArrayList) {
/* 6233 */     while (paramRenderAtomListInfo != null) {
/*      */ 
/*      */ 
/*      */       
/* 6237 */       if (paramRenderAtomListInfo.renderAtom.parentTInfo == null) {
/* 6238 */         paramRenderAtomListInfo.renderAtom.parentTInfo = new TransparentRenderingInfo[paramRenderAtomListInfo.renderAtom.rListInfo.length];
/* 6239 */         computeDirtyAcrossTransparentBins(paramRenderAtomListInfo.renderAtom);
/* 6240 */         paramRenderAtomListInfo.renderAtom.geometryAtom.updateCentroid();
/* 6241 */         paramArrayList.add(paramRenderAtomListInfo.renderAtom);
/*      */       } else {
/*      */         
/* 6244 */         GeometryRetained geometryRetained = null;
/* 6245 */         byte b = 0;
/* 6246 */         while (geometryRetained == null && b < paramRenderAtomListInfo.renderAtom.rListInfo.length) {
/* 6247 */           geometryRetained = paramRenderAtomListInfo.renderAtom.rListInfo[b].geometry();
/* 6248 */           b++;
/*      */         } 
/*      */         
/* 6251 */         if (geometryRetained != null && 
/* 6252 */           paramRenderAtomListInfo.renderAtom.parentTInfo[b - true] == null) {
/* 6253 */           computeDirtyAcrossTransparentBins(paramRenderAtomListInfo.renderAtom);
/* 6254 */           paramRenderAtomListInfo.renderAtom.geometryAtom.updateCentroid();
/* 6255 */           paramArrayList.add(paramRenderAtomListInfo.renderAtom);
/*      */         } 
/*      */       } 
/*      */       
/* 6259 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void convertTransparentRenderingStruct(int paramInt1, int paramInt2) {
/* 6266 */     ArrayList arrayList = new ArrayList(5);
/*      */ 
/*      */     
/* 6269 */     this.transparentInfo = null;
/* 6270 */     if (paramInt1 == 0 && paramInt2 == 1) {
/* 6271 */       int i = this.allTransparentObjects.size();
/*      */       
/* 6273 */       for (byte b = 0; b < i; b++) {
/* 6274 */         TextureBin textureBin = (TextureBin)this.allTransparentObjects.get(b);
/* 6275 */         textureBin.parentTInfo = null;
/* 6276 */         RenderMolecule renderMolecule = textureBin.transparentRMList;
/*      */         
/* 6278 */         while (renderMolecule != null) {
/*      */ 
/*      */           
/* 6281 */           if ((renderMolecule.primaryMoleculeType & RenderMolecule.DLIST_MOLECULE) != 0) {
/*      */             
/* 6283 */             addDisplayListResourceFreeList(renderMolecule);
/* 6284 */             removeDirtyRenderMolecule(renderMolecule);
/*      */             
/* 6286 */             renderMolecule.vwcBounds.set(null);
/* 6287 */             renderMolecule.displayListId = 0;
/* 6288 */             renderMolecule.displayListIdObj = null;
/*      */             
/* 6290 */             RenderAtomListInfo renderAtomListInfo = renderMolecule.primaryRenderAtomList;
/* 6291 */             while (renderAtomListInfo != null) {
/* 6292 */               renderAtomListInfo.groupType = RenderAtom.SEPARATE_DLIST_PER_RINFO;
/* 6293 */               if (renderAtomListInfo.renderAtom.dlistIds == null) {
/* 6294 */                 renderAtomListInfo.renderAtom.dlistIds = new int[renderAtomListInfo.renderAtom.rListInfo.length];
/*      */                 
/* 6296 */                 for (byte b1 = 0; b1 < renderAtomListInfo.renderAtom.dlistIds.length; b1++) {
/* 6297 */                   renderAtomListInfo.renderAtom.dlistIds[b1] = -1;
/*      */                 }
/*      */               } 
/* 6300 */               if (renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] == -1) {
/* 6301 */                 renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] = VirtualUniverse.mc.getDisplayListId().intValue();
/* 6302 */                 this.addDlistPerRinfo.add(renderAtomListInfo);
/*      */               } 
/* 6304 */               renderAtomListInfo = renderAtomListInfo.next;
/*      */             } 
/* 6306 */             renderMolecule.primaryMoleculeType = RenderMolecule.SEPARATE_DLIST_PER_RINFO_MOLECULE;
/*      */           } 
/*      */           
/* 6309 */           processRenderAtomTransparentInfo(renderMolecule.primaryRenderAtomList, arrayList);
/* 6310 */           processRenderAtomTransparentInfo(renderMolecule.vertexArrayRenderAtomList, arrayList);
/* 6311 */           processRenderAtomTransparentInfo(renderMolecule.separateDlistRenderAtomList, arrayList);
/* 6312 */           if (renderMolecule.next == null) {
/* 6313 */             renderMolecule = renderMolecule.nextMap;
/*      */             continue;
/*      */           } 
/* 6316 */           renderMolecule = renderMolecule.next;
/*      */         } 
/*      */       } 
/*      */       
/* 6320 */       this.allTransparentObjects = arrayList;
/*      */     }
/* 6322 */     else if (paramInt1 == 1 && paramInt2 == 0) {
/*      */       
/* 6324 */       int i = this.allTransparentObjects.size();
/* 6325 */       for (byte b = 0; b < i; b++) {
/* 6326 */         RenderAtom renderAtom = (RenderAtom)this.allTransparentObjects.get(b);
/* 6327 */         renderAtom.dirtyMask &= (RenderAtom.IN_SORTED_POS_DIRTY_TRANSP_LIST ^ 0xFFFFFFFF);
/* 6328 */         for (byte b1 = 0; b1 < renderAtom.parentTInfo.length; b1++) {
/*      */           
/* 6330 */           if (renderAtom.parentTInfo[b1] != null)
/*      */           {
/*      */             
/* 6333 */             renderAtom.parentTInfo[b1] = null; } 
/*      */         } 
/* 6335 */         if (renderAtom.renderMolecule.textureBin.parentTInfo == null) {
/* 6336 */           this.transparentInfo = computeDirtyAcrossTransparentBins(renderAtom.renderMolecule.textureBin, this.transparentInfo);
/* 6337 */           arrayList.add(renderAtom.renderMolecule.textureBin);
/*      */         } 
/*      */       } 
/* 6340 */       this.allTransparentObjects = arrayList;
/* 6341 */       this.dirtyDepthSortRenderAtom.clear();
/* 6342 */       this.numDirtyTinfo = 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   TransparentRenderingInfo mergeDepthSort(TransparentRenderingInfo paramTransparentRenderingInfo1, TransparentRenderingInfo paramTransparentRenderingInfo2) {
/* 6347 */     TransparentRenderingInfo transparentRenderingInfo1 = paramTransparentRenderingInfo1, transparentRenderingInfo2 = paramTransparentRenderingInfo2;
/* 6348 */     TransparentRenderingInfo transparentRenderingInfo3 = paramTransparentRenderingInfo1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6366 */     while (transparentRenderingInfo1 != null && transparentRenderingInfo2 != null) {
/* 6367 */       transparentRenderingInfo3 = transparentRenderingInfo1;
/* 6368 */       TransparentRenderingInfo transparentRenderingInfo = transparentRenderingInfo2.next;
/* 6369 */       double d1 = transparentRenderingInfo1.zVal;
/* 6370 */       double d2 = transparentRenderingInfo2.zVal;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6380 */       if ((this.transparencySortComparator == null && d2 > d1) || (this.transparencySortComparator != null && this.transparencySortComparator.compare(transparentRenderingInfo2, transparentRenderingInfo1) > 0)) {
/*      */ 
/*      */         
/* 6383 */         if (transparentRenderingInfo1.prev == null) {
/* 6384 */           transparentRenderingInfo1.prev = transparentRenderingInfo2;
/* 6385 */           transparentRenderingInfo2.prev = null;
/* 6386 */           transparentRenderingInfo2.next = paramTransparentRenderingInfo1;
/* 6387 */           paramTransparentRenderingInfo1 = transparentRenderingInfo2;
/*      */         }
/*      */         else {
/*      */           
/* 6391 */           transparentRenderingInfo2.prev = transparentRenderingInfo1.prev;
/* 6392 */           transparentRenderingInfo1.prev.next = transparentRenderingInfo2;
/* 6393 */           transparentRenderingInfo2.next = transparentRenderingInfo1;
/* 6394 */           transparentRenderingInfo1.prev = transparentRenderingInfo2;
/*      */         } 
/* 6396 */         transparentRenderingInfo2 = transparentRenderingInfo;
/*      */         
/*      */         continue;
/*      */       } 
/* 6400 */       transparentRenderingInfo1 = transparentRenderingInfo1.next;
/*      */     } 
/*      */     
/* 6403 */     if (transparentRenderingInfo1 == null && transparentRenderingInfo2 != null)
/*      */     {
/* 6405 */       if (transparentRenderingInfo3 == null) {
/* 6406 */         paramTransparentRenderingInfo1 = transparentRenderingInfo2;
/* 6407 */         transparentRenderingInfo2.prev = null;
/*      */       } else {
/*      */         
/* 6410 */         transparentRenderingInfo3.next = transparentRenderingInfo2;
/* 6411 */         transparentRenderingInfo2.prev = transparentRenderingInfo3;
/*      */       } 
/*      */     }
/* 6414 */     return paramTransparentRenderingInfo1;
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
/*      */   TransparentRenderingInfo collectDirtyTRInfo(TransparentRenderingInfo paramTransparentRenderingInfo, RenderAtom paramRenderAtom) { // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore_3
/*      */     //   2: iload_3
/*      */     //   3: aload_2
/*      */     //   4: getfield rListInfo : [Ljavax/media/j3d/RenderAtomListInfo;
/*      */     //   7: arraylength
/*      */     //   8: if_icmpge -> 166
/*      */     //   11: aload_2
/*      */     //   12: getfield parentTInfo : [Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   15: iload_3
/*      */     //   16: aaload
/*      */     //   17: astore #4
/*      */     //   19: aload #4
/*      */     //   21: ifnonnull -> 27
/*      */     //   24: goto -> 160
/*      */     //   27: aload #4
/*      */     //   29: aload_0
/*      */     //   30: getfield transparentInfo : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   33: if_acmpne -> 65
/*      */     //   36: aload_0
/*      */     //   37: aload_0
/*      */     //   38: getfield transparentInfo : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   41: getfield next : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   44: putfield transparentInfo : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   47: aload_0
/*      */     //   48: getfield transparentInfo : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   51: ifnull -> 117
/*      */     //   54: aload_0
/*      */     //   55: getfield transparentInfo : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   58: aconst_null
/*      */     //   59: putfield prev : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   62: goto -> 117
/*      */     //   65: aload #4
/*      */     //   67: aload_1
/*      */     //   68: if_acmpne -> 83
/*      */     //   71: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   74: ldc_w 'collectDirtyTRInfo: ERROR: t == dirtyList'
/*      */     //   77: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   80: goto -> 160
/*      */     //   83: aload #4
/*      */     //   85: getfield prev : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   88: aload #4
/*      */     //   90: getfield next : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   93: putfield next : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   96: aload #4
/*      */     //   98: getfield next : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   101: ifnull -> 117
/*      */     //   104: aload #4
/*      */     //   106: getfield next : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   109: aload #4
/*      */     //   111: getfield prev : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   114: putfield prev : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   117: aload_1
/*      */     //   118: ifnonnull -> 139
/*      */     //   121: aload #4
/*      */     //   123: astore_1
/*      */     //   124: aload #4
/*      */     //   126: aconst_null
/*      */     //   127: putfield prev : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   130: aload #4
/*      */     //   132: aconst_null
/*      */     //   133: putfield next : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   136: goto -> 160
/*      */     //   139: aload #4
/*      */     //   141: aload_1
/*      */     //   142: putfield next : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   145: aload #4
/*      */     //   147: aconst_null
/*      */     //   148: putfield prev : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   151: aload_1
/*      */     //   152: aload #4
/*      */     //   154: putfield prev : Ljavax/media/j3d/TransparentRenderingInfo;
/*      */     //   157: aload #4
/*      */     //   159: astore_1
/*      */     //   160: iinc #3, 1
/*      */     //   163: goto -> 2
/*      */     //   166: aload_1
/*      */     //   167: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #6463	-> 0
/*      */     //   #6464	-> 11
/*      */     //   #6465	-> 19
/*      */     //   #6466	-> 24
/*      */     //   #6467	-> 27
/*      */     //   #6468	-> 36
/*      */     //   #6469	-> 47
/*      */     //   #6470	-> 54
/*      */     //   #6473	-> 65
/*      */     //   #6479	-> 71
/*      */     //   #6480	-> 80
/*      */     //   #6484	-> 83
/*      */     //   #6485	-> 96
/*      */     //   #6486	-> 104
/*      */     //   #6488	-> 117
/*      */     //   #6489	-> 121
/*      */     //   #6490	-> 124
/*      */     //   #6491	-> 130
/*      */     //   #6493	-> 139
/*      */     //   #6494	-> 145
/*      */     //   #6495	-> 151
/*      */     //   #6496	-> 157
/*      */     //   #6463	-> 160
/*      */     //   #6500	-> 166 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   TransparentRenderingInfo depthSortAll(TransparentRenderingInfo paramTransparentRenderingInfo) {
/* 6505 */     this.transparencySortComparator = TransparencySortController.getComparator(this.view);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6517 */     TransparentRenderingInfo transparentRenderingInfo = paramTransparentRenderingInfo.next;
/* 6518 */     while (transparentRenderingInfo != null) {
/*      */       
/* 6520 */       TransparentRenderingInfo transparentRenderingInfo2 = transparentRenderingInfo.next;
/* 6521 */       double d = transparentRenderingInfo.zVal;
/* 6522 */       TransparentRenderingInfo transparentRenderingInfo1 = transparentRenderingInfo.prev;
/*      */ 
/*      */       
/* 6525 */       if (this.transparencySortComparator == null) {
/* 6526 */         while (transparentRenderingInfo1 != null && transparentRenderingInfo1.zVal < d) {
/* 6527 */           transparentRenderingInfo1 = transparentRenderingInfo1.prev;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 6535 */         while (transparentRenderingInfo1 != null && this.transparencySortComparator.compare(transparentRenderingInfo1, transparentRenderingInfo) < 0) {
/* 6536 */           transparentRenderingInfo1 = transparentRenderingInfo1.prev;
/*      */         }
/*      */       } 
/*      */       
/* 6540 */       if (transparentRenderingInfo.prev != transparentRenderingInfo1) {
/* 6541 */         if (transparentRenderingInfo1 == null) {
/* 6542 */           if (transparentRenderingInfo.next != null) {
/* 6543 */             transparentRenderingInfo.next.prev = transparentRenderingInfo.prev;
/*      */           }
/*      */           
/* 6546 */           transparentRenderingInfo.prev.next = transparentRenderingInfo.next;
/* 6547 */           transparentRenderingInfo.next = paramTransparentRenderingInfo;
/* 6548 */           paramTransparentRenderingInfo.prev = transparentRenderingInfo;
/* 6549 */           paramTransparentRenderingInfo = transparentRenderingInfo;
/* 6550 */           transparentRenderingInfo.prev = null;
/*      */         } else {
/*      */           
/* 6553 */           if (transparentRenderingInfo.next != null) {
/* 6554 */             transparentRenderingInfo.next.prev = transparentRenderingInfo.prev;
/*      */           }
/* 6556 */           if (transparentRenderingInfo.prev != null) {
/* 6557 */             transparentRenderingInfo.prev.next = transparentRenderingInfo.next;
/*      */           }
/* 6559 */           transparentRenderingInfo.next = transparentRenderingInfo1.next;
/* 6560 */           if (transparentRenderingInfo1.next != null)
/* 6561 */             transparentRenderingInfo1.next.prev = transparentRenderingInfo; 
/* 6562 */           transparentRenderingInfo.prev = transparentRenderingInfo1;
/* 6563 */           transparentRenderingInfo1.next = transparentRenderingInfo;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6577 */       transparentRenderingInfo = transparentRenderingInfo2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6625 */     return paramTransparentRenderingInfo;
/*      */   }
/*      */   
/*      */   void processViewSpecificGroupChanged(J3dMessage paramJ3dMessage) {
/* 6629 */     int i = ((Integer)paramJ3dMessage.args[0]).intValue();
/* 6630 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[1];
/* 6631 */     if ((i & 0x2) != 0 || (i & true) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 6635 */       View view1 = (View)arrayOfObject[0];
/* 6636 */       ArrayList arrayList = (ArrayList)arrayOfObject[2];
/*      */       
/* 6638 */       if (view1 == this.view) {
/* 6639 */         int j = arrayList.size();
/* 6640 */         for (byte b = 0; b < j; b++) {
/* 6641 */           Object object = arrayList.get(b);
/* 6642 */           if (object instanceof LightRetained) {
/* 6643 */             this.envDirty |= REEVALUATE_LIGHTS;
/* 6644 */             if (!this.changedLts.contains(object)) {
/* 6645 */               this.changedLts.add(object);
/*      */             }
/* 6647 */           } else if (object instanceof FogRetained) {
/* 6648 */             this.envDirty |= REEVALUATE_FOG;
/* 6649 */             if (!this.changedFogs.contains(object)) {
/* 6650 */               this.changedFogs.add(object);
/*      */             }
/* 6652 */           } else if (object instanceof AlternateAppearanceRetained) {
/* 6653 */             this.altAppearanceDirty = true;
/*      */           
/*      */           }
/* 6656 */           else if (object instanceof ModelClipRetained) {
/* 6657 */             this.envDirty |= REEVALUATE_MCLIP;
/* 6658 */             if (!this.changedModelClips.contains(object)) {
/* 6659 */               this.changedModelClips.add(object);
/*      */             }
/* 6661 */           } else if (object instanceof BackgroundRetained) {
/* 6662 */             this.reEvaluateBg = true;
/*      */           
/*      */           }
/* 6665 */           else if (object instanceof ClipRetained) {
/* 6666 */             this.reEvaluateClip = true;
/*      */           }
/* 6668 */           else if (object instanceof GeometryAtom) {
/* 6669 */             this.visGAIsDirty = true;
/* 6670 */             this.visQuery = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 6677 */     if ((i & 0x4) != 0 || (i & true) != 0) {
/*      */       View view1;
/*      */ 
/*      */       
/*      */       ArrayList arrayList;
/*      */ 
/*      */       
/* 6684 */       if ((i & 0x4) != 0) {
/* 6685 */         view1 = (View)arrayOfObject[0];
/* 6686 */         arrayList = (ArrayList)arrayOfObject[2];
/*      */       } else {
/*      */         
/* 6689 */         view1 = (View)arrayOfObject[4];
/* 6690 */         arrayList = (ArrayList)arrayOfObject[6];
/*      */       } 
/* 6692 */       if (view1 == this.view) {
/* 6693 */         int j = arrayList.size();
/* 6694 */         for (byte b = 0; b < j; b++) {
/* 6695 */           Object object = arrayList.get(b);
/* 6696 */           if (object instanceof GeometryAtom) {
/* 6697 */             RenderAtom renderAtom = ((GeometryAtom)object).getRenderAtom(this.view);
/* 6698 */             if (renderAtom != null && renderAtom.inRenderBin()) {
/* 6699 */               this.renderAtoms.remove(this.renderAtoms.indexOf(renderAtom));
/* 6700 */               removeARenderAtom(renderAtom);
/*      */             }
/*      */           
/* 6703 */           } else if (object instanceof LightRetained) {
/* 6704 */             this.envDirty |= REEVALUATE_LIGHTS;
/*      */           }
/* 6706 */           else if (object instanceof FogRetained) {
/* 6707 */             this.envDirty |= REEVALUATE_FOG;
/*      */           }
/* 6709 */           else if (object instanceof AlternateAppearanceRetained) {
/* 6710 */             this.altAppearanceDirty = true;
/*      */           
/*      */           }
/* 6713 */           else if (object instanceof ModelClipRetained) {
/* 6714 */             this.envDirty |= REEVALUATE_MCLIP;
/*      */           
/*      */           }
/* 6717 */           else if (object instanceof BackgroundRetained) {
/* 6718 */             this.reEvaluateBg = true;
/*      */           
/*      */           }
/* 6721 */           else if (object instanceof ClipRetained) {
/* 6722 */             this.reEvaluateClip = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void insertNodes(J3dMessage paramJ3dMessage) {
/* 6733 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/* 6734 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*      */ 
/*      */     
/* 6737 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/* 6738 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/* 6739 */       if (arrayOfObject[b] instanceof LightRetained) {
/* 6740 */         this.envDirty |= REEVALUATE_LIGHTS;
/* 6741 */         if (!this.changedLts.contains(arrayOfObject[b]))
/* 6742 */           this.changedLts.add(arrayOfObject[b]); 
/* 6743 */       } else if (arrayOfObject[b] instanceof FogRetained) {
/* 6744 */         this.envDirty |= REEVALUATE_FOG;
/* 6745 */         if (!this.changedFogs.contains(arrayOfObject[b]))
/* 6746 */           this.changedFogs.add(arrayOfObject[b]); 
/* 6747 */       } else if (arrayOfObject[b] instanceof BackgroundRetained) {
/*      */ 
/*      */ 
/*      */         
/* 6751 */         this.reEvaluateBg = true;
/* 6752 */       } else if (arrayOfObject[b] instanceof ClipRetained) {
/* 6753 */         this.reEvaluateClip = true;
/* 6754 */       } else if (arrayOfObject[b] instanceof ModelClipRetained) {
/* 6755 */         this.envDirty |= REEVALUATE_MCLIP;
/* 6756 */         if (!this.changedModelClips.contains(arrayOfObject[b]))
/* 6757 */           this.changedModelClips.add(arrayOfObject[b]); 
/* 6758 */       } else if (arrayOfObject[b] instanceof GeometryAtom) {
/* 6759 */         this.visGAIsDirty = true;
/* 6760 */         this.visQuery = true;
/* 6761 */       } else if (arrayOfObject[b] instanceof AlternateAppearanceRetained) {
/* 6762 */         this.altAppearanceDirty = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 6767 */     if (arrayList1 != null) {
/* 6768 */       int i = arrayList1.size();
/*      */       
/* 6770 */       for (byte b1 = 0; b1 < i; b1++) {
/* 6771 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b1);
/* 6772 */         ArrayList arrayList = (ArrayList)arrayList2.get(b1);
/*      */         
/* 6774 */         if (arrayList.contains(this.view)) {
/* 6775 */           if (nodeRetained instanceof LightRetained) {
/* 6776 */             this.envDirty |= REEVALUATE_LIGHTS;
/* 6777 */             if (!this.changedLts.contains(nodeRetained))
/* 6778 */               this.changedLts.add(nodeRetained); 
/* 6779 */           } else if (nodeRetained instanceof FogRetained) {
/* 6780 */             this.envDirty |= REEVALUATE_FOG;
/* 6781 */             if (!this.changedFogs.contains(nodeRetained))
/* 6782 */               this.changedFogs.add(nodeRetained); 
/* 6783 */           } else if (nodeRetained instanceof BackgroundRetained) {
/*      */ 
/*      */ 
/*      */             
/* 6787 */             this.reEvaluateBg = true;
/* 6788 */           } else if (nodeRetained instanceof ClipRetained) {
/* 6789 */             this.reEvaluateClip = true;
/* 6790 */           } else if (nodeRetained instanceof ModelClipRetained) {
/* 6791 */             this.envDirty |= REEVALUATE_MCLIP;
/* 6792 */             if (!this.changedModelClips.contains(nodeRetained))
/* 6793 */               this.changedModelClips.add(nodeRetained); 
/* 6794 */           } else if (nodeRetained instanceof AlternateAppearanceRetained) {
/* 6795 */             this.altAppearanceDirty = true;
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
/*      */   void removeNodes(J3dMessage paramJ3dMessage) {
/* 6808 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/* 6809 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*      */     
/* 6811 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0]; int i;
/* 6812 */     for (i = 0; i < arrayOfObject.length; i++) {
/* 6813 */       if (arrayOfObject[i] instanceof GeometryAtom) {
/* 6814 */         this.visGAIsDirty = true;
/* 6815 */         this.visQuery = true;
/* 6816 */         RenderAtom renderAtom = ((GeometryAtom)arrayOfObject[i]).getRenderAtom(this.view);
/*      */         
/* 6818 */         if (renderAtom != null && renderAtom.inRenderBin()) {
/* 6819 */           this.renderAtoms.remove(this.renderAtoms.indexOf(renderAtom));
/* 6820 */           removeARenderAtom(renderAtom);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 6825 */         GeometryAtom geometryAtom = (GeometryAtom)arrayOfObject[i];
/* 6826 */         if (geometryAtom.geometryArray != null) {
/* 6827 */           for (byte b = 0; b < geometryAtom.geometryArray.length; b++) {
/* 6828 */             GeometryRetained geometryRetained = geometryAtom.geometryArray[b];
/* 6829 */             if (geometryRetained != null && geometryRetained instanceof RasterRetained)
/*      */             {
/* 6831 */               addTextureResourceFreeList(((RasterRetained)geometryRetained).texture);
/*      */             }
/*      */           } 
/*      */         }
/* 6835 */       } else if (arrayOfObject[i] instanceof AlternateAppearanceRetained) {
/* 6836 */         this.altAppearanceDirty = true;
/* 6837 */       } else if (arrayOfObject[i] instanceof BackgroundRetained) {
/* 6838 */         this.reEvaluateBg = true;
/* 6839 */       } else if (arrayOfObject[i] instanceof ClipRetained) {
/* 6840 */         this.reEvaluateClip = true;
/* 6841 */       } else if (arrayOfObject[i] instanceof ModelClipRetained) {
/* 6842 */         this.envDirty |= REEVALUATE_MCLIP;
/* 6843 */       } else if (arrayOfObject[i] instanceof FogRetained) {
/* 6844 */         this.envDirty |= REEVALUATE_FOG;
/*      */       } 
/* 6846 */       if (arrayOfObject[i] instanceof LightRetained) {
/* 6847 */         this.envDirty |= REEVALUATE_LIGHTS;
/*      */       }
/*      */     } 
/*      */     
/* 6851 */     if (arrayList1 != null) {
/* 6852 */       i = arrayList1.size();
/*      */ 
/*      */       
/* 6855 */       for (byte b = 0; b < i; b++) {
/* 6856 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b);
/* 6857 */         ArrayList arrayList = (ArrayList)arrayList2.get(b);
/*      */         
/* 6859 */         if (arrayList.contains(this.view)) {
/* 6860 */           if (nodeRetained instanceof LightRetained) {
/* 6861 */             this.envDirty |= REEVALUATE_LIGHTS;
/* 6862 */           } else if (nodeRetained instanceof FogRetained) {
/* 6863 */             this.envDirty |= REEVALUATE_FOG;
/* 6864 */           } else if (nodeRetained instanceof BackgroundRetained) {
/*      */ 
/*      */ 
/*      */             
/* 6868 */             this.reEvaluateBg = true;
/* 6869 */           } else if (nodeRetained instanceof ClipRetained) {
/* 6870 */             this.reEvaluateClip = true;
/* 6871 */           } else if (nodeRetained instanceof ModelClipRetained) {
/* 6872 */             this.envDirty |= REEVALUATE_MCLIP;
/*      */           }
/* 6874 */           else if (nodeRetained instanceof AlternateAppearanceRetained) {
/* 6875 */             this.altAppearanceDirty = true;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void cleanup() {
/* 6886 */     releaseAllDisplayListID();
/* 6887 */     removeAllRenderAtoms();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void freeAllDisplayListResources(Canvas3D paramCanvas3D, Context paramContext) {
/* 6893 */     assert paramContext != null;
/*      */ 
/*      */     
/* 6896 */     int i = this.renderMoleculeList.size();
/* 6897 */     Renderer renderer = paramCanvas3D.screen.renderer;
/*      */     
/* 6899 */     if (i > 0) {
/* 6900 */       RenderMolecule[] arrayOfRenderMolecule = (RenderMolecule[])this.renderMoleculeList.toArray(false);
/*      */ 
/*      */       
/* 6903 */       for (byte b = 0; b < i; b++) {
/* 6904 */         arrayOfRenderMolecule[b].releaseAllPrimaryDisplayListResources(paramCanvas3D, paramContext);
/*      */       }
/*      */     } 
/*      */     
/* 6908 */     i = this.sharedDList.size();
/* 6909 */     if (i > 0) {
/* 6910 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 6911 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.sharedDList.toArray(arrayOfRenderAtomListInfo);
/*      */ 
/*      */       
/* 6914 */       int j = paramCanvas3D.useSharedCtx ? renderer.rendererBit : paramCanvas3D.canvasBit;
/*      */       
/* 6916 */       for (byte b = 0; b < i; b++) {
/* 6917 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)arrayOfRenderAtomListInfo[b].geometry();
/*      */ 
/*      */ 
/*      */         
/* 6921 */         if (geometryArrayRetained.dlistId > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 6927 */           paramCanvas3D.freeDisplayList(paramContext, geometryArrayRetained.dlistId);
/* 6928 */           geometryArrayRetained.resourceCreationMask &= (j ^ 0xFFFFFFFF);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void releaseAllDisplayListID() {
/* 6938 */     int i = this.renderMoleculeList.size();
/*      */     
/* 6940 */     if (i > 0) {
/* 6941 */       RenderMolecule[] arrayOfRenderMolecule = (RenderMolecule[])this.renderMoleculeList.toArray(false);
/*      */ 
/*      */       
/* 6944 */       for (byte b = 0; b < i; b++) {
/* 6945 */         arrayOfRenderMolecule[b].releaseAllPrimaryDisplayListID();
/*      */       }
/*      */     } 
/*      */     
/* 6949 */     i = this.sharedDList.size();
/* 6950 */     if (i > 0) {
/* 6951 */       RenderAtomListInfo[] arrayOfRenderAtomListInfo = new RenderAtomListInfo[i];
/* 6952 */       arrayOfRenderAtomListInfo = (RenderAtomListInfo[])this.sharedDList.toArray(arrayOfRenderAtomListInfo);
/*      */ 
/*      */       
/* 6955 */       for (byte b = 0; b < i; b++) {
/* 6956 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)arrayOfRenderAtomListInfo[b].geometry();
/* 6957 */         if (geometryArrayRetained.resourceCreationMask == 0)
/* 6958 */           geometryArrayRetained.freeDlistId(); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\RenderBin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */