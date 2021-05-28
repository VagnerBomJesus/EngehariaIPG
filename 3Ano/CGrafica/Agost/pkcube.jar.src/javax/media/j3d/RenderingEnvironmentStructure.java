/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
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
/*      */ class RenderingEnvironmentStructure
/*      */   extends J3dStructure
/*      */   implements ObjectUpdate
/*      */ {
/*   27 */   ArrayList nonViewScopedLights = new ArrayList();
/*   28 */   HashMap viewScopedLights = new HashMap();
/*   29 */   int numberOfLights = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   34 */   ArrayList nonViewScopedFogs = new ArrayList();
/*   35 */   HashMap viewScopedFogs = new HashMap();
/*   36 */   int numberOfFogs = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   41 */   ArrayList nonViewScopedAltAppearances = new ArrayList();
/*   42 */   HashMap viewScopedAltAppearances = new HashMap();
/*   43 */   int numberOfAltApps = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   48 */   ArrayList nonViewScopedModelClips = new ArrayList();
/*   49 */   HashMap viewScopedModelClips = new HashMap();
/*   50 */   int numberOfModelClips = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   55 */   ArrayList nonViewScopedBackgrounds = new ArrayList();
/*   56 */   HashMap viewScopedBackgrounds = new HashMap();
/*   57 */   int numberOfBgs = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   62 */   ArrayList nonViewScopedClips = new ArrayList();
/*   63 */   HashMap viewScopedClips = new HashMap();
/*   64 */   int numberOfClips = 0;
/*      */ 
/*      */   
/*   67 */   BackgroundRetained[] intersectedBacks = new BackgroundRetained[1];
/*      */ 
/*      */ 
/*      */   
/*   71 */   ClipRetained[] intersectedClips = new ClipRetained[1];
/*      */ 
/*      */   
/*   74 */   Bounds[] intersectedBounds = new Bounds[1];
/*      */   
/*   76 */   Transform3D localeXform = new Transform3D();
/*   77 */   Vector3d localeTranslation = new Vector3d();
/*   78 */   Bounds localeBounds = null;
/*      */ 
/*      */   
/*   81 */   FogRetained[] intersectedFogs = new FogRetained[1];
/*      */ 
/*      */   
/*   84 */   AlternateAppearanceRetained[] intersectedAltApps = new AlternateAppearanceRetained[1];
/*      */ 
/*      */   
/*   87 */   ModelClipRetained[] intersectedModelClips = new ModelClipRetained[1];
/*      */ 
/*      */ 
/*      */   
/*      */   double backClipDistance;
/*      */ 
/*      */ 
/*      */   
/*   95 */   ArrayList objList = new ArrayList();
/*      */ 
/*      */ 
/*      */   
/*   99 */   ArrayList xformChangeList = new ArrayList();
/*      */ 
/*      */ 
/*      */   
/*  103 */   ArrayList objFreeList = new ArrayList();
/*      */   
/*  105 */   LightRetained[] retlights = new LightRetained[5];
/*      */   
/*      */   boolean transformMsg = false;
/*      */   
/*  109 */   UpdateTargets targets = null;
/*  110 */   ArrayList blUsers = null;
/*      */   
/*  112 */   Integer ogInsert = new Integer(32);
/*  113 */   Integer ogRemove = new Integer(33);
/*      */ 
/*      */ 
/*      */   
/*  117 */   Object lockObj = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  124 */   RenderingEnvironmentStructure(VirtualUniverse paramVirtualUniverse) { super(paramVirtualUniverse, 4096); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Object[] getObjectArray() {
/*      */     Object[] arrayOfObject;
/*  135 */     int i = this.objFreeList.size();
/*  136 */     if (i == 0) {
/*  137 */       arrayOfObject = new Object[5];
/*      */     } else {
/*      */       
/*  140 */       arrayOfObject = (Object[])this.objFreeList.get(i - 1);
/*  141 */       this.objFreeList.remove(i - 1);
/*      */     } 
/*  143 */     return arrayOfObject;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void addObjArrayToFreeList(Object[] paramArrayOfObject) {
/*  149 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/*  150 */       paramArrayOfObject[b] = null;
/*      */     }
/*  152 */     this.objFreeList.add(paramArrayOfObject);
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
/*      */   public void updateObject() {
/*  164 */     int i = this.objList.size(); byte b;
/*  165 */     for (b = 0; b < i; b++) {
/*  166 */       Object[] arrayOfObject = (Object[])this.objList.get(b);
/*  167 */       LeafRetained leafRetained = (LeafRetained)arrayOfObject[0];
/*  168 */       leafRetained.updateMirrorObject(arrayOfObject);
/*  169 */       addObjArrayToFreeList(arrayOfObject);
/*      */     } 
/*  171 */     this.objList.clear();
/*      */     
/*  173 */     i = this.xformChangeList.size();
/*  174 */     for (b = 0; b < i; b++) {
/*  175 */       LeafRetained leafRetained = (LeafRetained)this.xformChangeList.get(b);
/*  176 */       leafRetained.updateTransformChange();
/*      */     } 
/*  178 */     this.xformChangeList.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   void processMessages(long paramLong) {
/*  183 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/*      */     
/*  185 */     int i = getNumMessage();
/*      */     
/*  187 */     if (i <= 0) {
/*      */       return;
/*      */     }
/*      */     
/*  191 */     for (byte b = 0; b < i; b++) {
/*  192 */       J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/*      */       
/*  194 */       switch (j3dMessage.type) {
/*      */         case 0:
/*  196 */           insertNodes(j3dMessage);
/*      */           break;
/*      */         case 1:
/*  199 */           removeNodes(j3dMessage);
/*      */           break;
/*      */         case 19:
/*  202 */           updateLight((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 23:
/*  205 */           updateBoundingLeaf((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 22:
/*  208 */           updateFog((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 41:
/*  211 */           updateAltApp((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 24:
/*  214 */           updateShape3D((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 46:
/*  217 */           updateOrientedShape3D((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 16:
/*  220 */           updateMorph((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 3:
/*  223 */           this.transformMsg = true;
/*      */           break;
/*      */         case 27:
/*  226 */           processSwitchChanged(j3dMessage);
/*      */           
/*  228 */           if (this.universe.transformStructure.getLazyUpdate()) {
/*  229 */             this.transformMsg = true;
/*      */           }
/*      */           break;
/*      */         case 36:
/*  233 */           updateModelClip((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 20:
/*  236 */           updateBackground((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 21:
/*  239 */           updateClip((Object[])j3dMessage.args);
/*      */           break;
/*      */         case 32:
/*  242 */           updateOrderedGroupInserted(j3dMessage);
/*      */           break;
/*      */         case 33:
/*  245 */           updateOrderedGroupsRemoved(j3dMessage);
/*      */           break;
/*      */         case 56:
/*  248 */           updateViewSpecificGroupChanged(j3dMessage);
/*      */           break;
/*      */         case 57:
/*  251 */           initViewSpecificInfo(j3dMessage);
/*      */           break;
/*      */         case 58:
/*  254 */           clearViewSpecificInfo(j3dMessage);
/*      */           break;
/*      */       } 
/*  257 */       j3dMessage.decRefcount();
/*      */     } 
/*      */     
/*  260 */     if (this.transformMsg) {
/*  261 */       updateTransformChange();
/*  262 */       this.transformMsg = false;
/*      */     } 
/*      */     
/*  265 */     VirtualUniverse.mc.addMirrorObject(this);
/*      */     
/*  267 */     Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*      */   }
/*      */   
/*      */   void updateOrderedGroupInserted(J3dMessage paramJ3dMessage) {
/*  271 */     Object[] arrayOfObject1 = (Object[])paramJ3dMessage.args[0];
/*  272 */     Object[] arrayOfObject2 = (Object[])paramJ3dMessage.args[1];
/*  273 */     Object[] arrayOfObject3 = (Object[])paramJ3dMessage.args[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  278 */     for (byte b = 0; b < arrayOfObject1.length; b++) {
/*  279 */       OrderedGroupRetained orderedGroupRetained = (OrderedGroupRetained)arrayOfObject1[b];
/*  280 */       orderedGroupRetained.updateChildIdTableInserted(((Integer)arrayOfObject2[b]).intValue(), ((Integer)arrayOfObject3[b]).intValue());
/*      */       
/*  282 */       orderedGroupRetained.incrChildCount();
/*      */     } 
/*      */   }
/*      */   
/*      */   void updateOrderedGroupsRemoved(J3dMessage paramJ3dMessage) {
/*  287 */     Object[] arrayOfObject1 = (Object[])paramJ3dMessage.args[0];
/*  288 */     Object[] arrayOfObject2 = (Object[])paramJ3dMessage.args[1];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  293 */     for (byte b = 0; b < arrayOfObject1.length; b++) {
/*  294 */       OrderedGroupRetained orderedGroupRetained = (OrderedGroupRetained)arrayOfObject1[b];
/*  295 */       orderedGroupRetained.updateChildIdTableRemoved(((Integer)arrayOfObject2[b]).intValue());
/*  296 */       orderedGroupRetained.decrChildCount();
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
/*      */   void processSwitchChanged(J3dMessage paramJ3dMessage) {
/*  310 */     UpdateTargets updateTargets = (UpdateTargets)paramJ3dMessage.args[0];
/*      */     
/*  312 */     UnorderList unorderList = updateTargets.targetList[5];
/*  313 */     if (unorderList != null) {
/*      */       
/*  315 */       Object[] arrayOfObject2 = (Object[])paramJ3dMessage.args[1];
/*      */       
/*  317 */       int i = unorderList.size();
/*  318 */       Object[] arrayOfObject1 = unorderList.toArray(false);
/*      */       
/*  320 */       for (byte b = 0; b < i; b++) {
/*  321 */         Object[] arrayOfObject3 = (Object[])arrayOfObject1[b];
/*  322 */         Object[] arrayOfObject4 = (Object[])arrayOfObject2[b];
/*      */         
/*  324 */         for (byte b1 = 0; b1 < arrayOfObject3.length; b1++) {
/*      */           
/*  326 */           Object[] arrayOfObject = (Object[])arrayOfObject4[b1];
/*  327 */           BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)arrayOfObject3[b1];
/*      */ 
/*      */           
/*  330 */           for (byte b2 = 0; b2 < arrayOfObject.length; b2++) {
/*  331 */             LeafRetained leafRetained = (LeafRetained)arrayOfObject[b2];
/*  332 */             if (leafRetained instanceof FogRetained || leafRetained instanceof LightRetained || leafRetained instanceof ModelClipRetained || leafRetained instanceof ClipRetained || leafRetained instanceof AlternateAppearanceRetained || leafRetained instanceof BackgroundRetained)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  338 */               leafRetained.updateBoundingLeaf();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void insertNodes(J3dMessage paramJ3dMessage) {
/*  347 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/*  348 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/*  349 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     byte b;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  359 */     for (b = 0; b < arrayOfObject.length; b++) {
/*  360 */       Object object = arrayOfObject[b];
/*  361 */       if (object instanceof LightRetained) {
/*  362 */         LightRetained lightRetained = (LightRetained)object;
/*  363 */         this.numberOfLights++;
/*      */ 
/*      */ 
/*      */         
/*  367 */         if (lightRetained.inBackgroundGroup) {
/*  368 */           lightRetained.geometryBackground.lights.add(lightRetained);
/*      */         } else {
/*  370 */           this.nonViewScopedLights.add(lightRetained);
/*      */         } 
/*  372 */       } else if (object instanceof FogRetained) {
/*  373 */         FogRetained fogRetained = (FogRetained)object;
/*  374 */         this.numberOfFogs++;
/*      */ 
/*      */         
/*  377 */         if (fogRetained.inBackgroundGroup) {
/*  378 */           fogRetained.geometryBackground.fogs.add(fogRetained);
/*      */         } else {
/*  380 */           this.nonViewScopedFogs.add(fogRetained);
/*      */         }
/*      */       
/*      */       }
/*  384 */       else if (object instanceof AlternateAppearanceRetained) {
/*  385 */         AlternateAppearanceRetained alternateAppearanceRetained = (AlternateAppearanceRetained)object;
/*      */         
/*  387 */         this.numberOfAltApps++;
/*      */         
/*  389 */         this.nonViewScopedAltAppearances.add(object);
/*      */       }
/*  391 */       else if (object instanceof BackgroundRetained) {
/*  392 */         BackgroundRetained backgroundRetained = (BackgroundRetained)object;
/*  393 */         this.numberOfBgs++;
/*      */         
/*  395 */         this.nonViewScopedBackgrounds.add(object);
/*      */       }
/*  397 */       else if (object instanceof ClipRetained) {
/*  398 */         ClipRetained clipRetained = (ClipRetained)object;
/*  399 */         this.numberOfClips++;
/*      */         
/*  401 */         this.nonViewScopedClips.add(object);
/*      */       }
/*  403 */       else if (object instanceof ModelClipRetained) {
/*  404 */         ModelClipRetained modelClipRetained = (ModelClipRetained)object;
/*  405 */         this.numberOfModelClips++;
/*      */         
/*  407 */         this.nonViewScopedModelClips.add(object);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  414 */     if (arrayList1 != null) {
/*  415 */       int i = arrayList1.size();
/*      */ 
/*      */ 
/*      */       
/*  419 */       for (b = 0; b < i; b++) {
/*  420 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b);
/*  421 */         ArrayList arrayList = (ArrayList)arrayList2.get(b);
/*  422 */         if (nodeRetained instanceof LightRetained) {
/*  423 */           ((LightRetained)nodeRetained).isViewScoped = true;
/*  424 */           this.numberOfLights++;
/*  425 */           int j = arrayList.size();
/*  426 */           for (byte b1 = 0; b1 < j; b1++) {
/*  427 */             View view = (View)arrayList.get(b1); ArrayList arrayList3;
/*  428 */             if ((arrayList3 = (ArrayList)this.viewScopedLights.get(view)) == null) {
/*  429 */               arrayList3 = new ArrayList();
/*  430 */               this.viewScopedLights.put(view, arrayList3);
/*      */             } 
/*  432 */             arrayList3.add(nodeRetained);
/*      */           } 
/*  434 */         } else if (nodeRetained instanceof FogRetained) {
/*  435 */           ((FogRetained)nodeRetained).isViewScoped = true;
/*  436 */           this.numberOfFogs++;
/*  437 */           int j = arrayList.size();
/*  438 */           for (byte b1 = 0; b1 < j; b1++) {
/*  439 */             View view = (View)arrayList.get(b1); ArrayList arrayList3;
/*  440 */             if ((arrayList3 = (ArrayList)this.viewScopedFogs.get(view)) == null) {
/*  441 */               arrayList3 = new ArrayList();
/*  442 */               this.viewScopedFogs.put(view, arrayList3);
/*      */             } 
/*  444 */             arrayList3.add(nodeRetained);
/*      */           } 
/*  446 */         } else if (nodeRetained instanceof AlternateAppearanceRetained) {
/*  447 */           ((AlternateAppearanceRetained)nodeRetained).isViewScoped = true;
/*  448 */           this.numberOfAltApps++;
/*  449 */           int j = arrayList.size();
/*  450 */           for (byte b1 = 0; b1 < j; b1++) {
/*  451 */             View view = (View)arrayList.get(b1); ArrayList arrayList3;
/*  452 */             if ((arrayList3 = (ArrayList)this.viewScopedAltAppearances.get(view)) == null) {
/*  453 */               arrayList3 = new ArrayList();
/*  454 */               this.viewScopedAltAppearances.put(view, arrayList3);
/*      */             } 
/*  456 */             arrayList3.add(nodeRetained);
/*      */           } 
/*  458 */         } else if (nodeRetained instanceof BackgroundRetained) {
/*  459 */           ((BackgroundRetained)nodeRetained).isViewScoped = true;
/*  460 */           this.numberOfBgs++;
/*  461 */           int j = arrayList.size();
/*  462 */           for (byte b1 = 0; b1 < j; b1++) {
/*  463 */             View view = (View)arrayList.get(b1); ArrayList arrayList3;
/*  464 */             if ((arrayList3 = (ArrayList)this.viewScopedBackgrounds.get(view)) == null) {
/*  465 */               arrayList3 = new ArrayList();
/*  466 */               this.viewScopedBackgrounds.put(view, arrayList3);
/*      */             } 
/*  468 */             arrayList3.add(nodeRetained);
/*      */           } 
/*  470 */         } else if (nodeRetained instanceof ClipRetained) {
/*  471 */           ((ClipRetained)nodeRetained).isViewScoped = true;
/*  472 */           this.numberOfClips++;
/*  473 */           int j = arrayList.size();
/*  474 */           for (byte b1 = 0; b1 < j; b1++) {
/*  475 */             View view = (View)arrayList.get(b1); ArrayList arrayList3;
/*  476 */             if ((arrayList3 = (ArrayList)this.viewScopedClips.get(view)) == null) {
/*  477 */               arrayList3 = new ArrayList();
/*  478 */               this.viewScopedClips.put(view, arrayList3);
/*      */             } 
/*  480 */             arrayList3.add(nodeRetained);
/*      */           } 
/*  482 */         } else if (nodeRetained instanceof ModelClipRetained) {
/*  483 */           ((ModelClipRetained)nodeRetained).isViewScoped = true;
/*  484 */           this.numberOfModelClips++;
/*  485 */           int j = arrayList.size();
/*  486 */           for (byte b1 = 0; b1 < j; b1++) {
/*  487 */             View view = (View)arrayList.get(b1); ArrayList arrayList3;
/*  488 */             if ((arrayList3 = (ArrayList)this.viewScopedModelClips.get(view)) == null) {
/*  489 */               arrayList3 = new ArrayList();
/*  490 */               this.viewScopedModelClips.put(view, arrayList3);
/*      */             } 
/*  492 */             arrayList3.add(nodeRetained);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  498 */     if (this.numberOfLights > this.retlights.length)
/*  499 */       this.retlights = new LightRetained[this.numberOfLights]; 
/*  500 */     if (this.intersectedFogs.length < this.numberOfFogs)
/*  501 */       this.intersectedFogs = new FogRetained[this.numberOfFogs]; 
/*  502 */     if (this.intersectedAltApps.length < this.numberOfAltApps)
/*  503 */       this.intersectedAltApps = new AlternateAppearanceRetained[this.numberOfAltApps]; 
/*  504 */     if (this.intersectedBacks.length < this.numberOfBgs)
/*  505 */       this.intersectedBacks = new BackgroundRetained[this.numberOfBgs]; 
/*  506 */     if (this.intersectedClips.length < this.numberOfClips)
/*  507 */       this.intersectedClips = new ClipRetained[this.numberOfClips]; 
/*  508 */     if (this.intersectedModelClips.length < this.numberOfModelClips)
/*  509 */       this.intersectedModelClips = new ModelClipRetained[this.numberOfModelClips]; 
/*      */   }
/*      */   
/*      */   void removeNodes(J3dMessage paramJ3dMessage) {
/*  513 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/*  514 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/*  515 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*      */ 
/*      */ 
/*      */     
/*  519 */     Shape3DRetained shape3DRetained = null;
/*      */ 
/*      */     
/*      */     byte b;
/*      */     
/*  524 */     for (b = 0; b < arrayOfObject.length; b++) {
/*  525 */       Object object = arrayOfObject[b];
/*  526 */       if (object instanceof LightRetained) {
/*  527 */         LightRetained lightRetained = (LightRetained)object;
/*  528 */         if (lightRetained.inBackgroundGroup) {
/*  529 */           lightRetained.geometryBackground.lights.remove(lightRetained);
/*      */         } else {
/*      */           
/*  532 */           this.nonViewScopedLights.remove(this.nonViewScopedLights.indexOf(object));
/*      */         } 
/*      */         
/*  535 */         this.numberOfLights--;
/*  536 */       } else if (object instanceof FogRetained) {
/*  537 */         this.numberOfFogs--;
/*  538 */         FogRetained fogRetained = (FogRetained)object;
/*  539 */         if (fogRetained.inBackgroundGroup) {
/*  540 */           fogRetained.geometryBackground.fogs.remove(fogRetained);
/*      */         } else {
/*  542 */           this.nonViewScopedFogs.remove(this.nonViewScopedFogs.indexOf(object));
/*      */         } 
/*  544 */       } else if (object instanceof AlternateAppearanceRetained) {
/*  545 */         this.numberOfAltApps--;
/*  546 */         this.nonViewScopedAltAppearances.remove(this.nonViewScopedAltAppearances.indexOf(object));
/*  547 */       } else if (object instanceof BackgroundRetained) {
/*  548 */         this.numberOfBgs--;
/*  549 */         this.nonViewScopedBackgrounds.remove(this.nonViewScopedBackgrounds.indexOf(object));
/*  550 */       } else if (object instanceof ClipRetained) {
/*  551 */         this.numberOfClips--;
/*  552 */         this.nonViewScopedClips.remove(this.nonViewScopedClips.indexOf(object));
/*  553 */       } else if (object instanceof ModelClipRetained) {
/*  554 */         ModelClipRetained modelClipRetained = (ModelClipRetained)object;
/*  555 */         this.numberOfModelClips--;
/*  556 */         this.nonViewScopedModelClips.remove(this.nonViewScopedModelClips.indexOf(object));
/*      */       
/*      */       }
/*  559 */       else if (object instanceof GeometryAtom) {
/*  560 */         GeometryAtom geometryAtom = (GeometryAtom)object;
/*      */ 
/*      */ 
/*      */         
/*  564 */         if (geometryAtom.source != shape3DRetained) {
/*  565 */           geometryAtom.source.clearMirrorShape();
/*  566 */           shape3DRetained = geometryAtom.source;
/*      */         }
/*      */       
/*  569 */       } else if (object instanceof OrderedGroupRetained) {
/*      */         
/*  571 */         ((OrderedGroupRetained)object).clearDerivedDataStructures();
/*      */       } 
/*      */     } 
/*  574 */     if (arrayList1 != null) {
/*  575 */       int i = arrayList1.size();
/*      */ 
/*      */       
/*  578 */       for (b = 0; b < i; b++) {
/*  579 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b);
/*  580 */         ArrayList arrayList = (ArrayList)arrayList2.get(b);
/*  581 */         if (nodeRetained instanceof LightRetained) {
/*  582 */           ((LightRetained)nodeRetained).isViewScoped = false;
/*  583 */           this.numberOfLights--;
/*  584 */           int j = arrayList.size();
/*  585 */           for (byte b1 = 0; b1 < j; b1++) {
/*  586 */             View view = (View)arrayList.get(b1);
/*  587 */             ArrayList arrayList3 = (ArrayList)this.viewScopedLights.get(view);
/*  588 */             arrayList3.remove(nodeRetained);
/*  589 */             if (arrayList3.size() == 0) {
/*  590 */               this.viewScopedLights.remove(view);
/*      */             }
/*      */           } 
/*  593 */         } else if (nodeRetained instanceof FogRetained) {
/*  594 */           ((FogRetained)nodeRetained).isViewScoped = false;
/*  595 */           this.numberOfFogs--;
/*  596 */           int j = arrayList.size();
/*  597 */           for (byte b1 = 0; b1 < j; b1++) {
/*  598 */             View view = (View)arrayList.get(b1);
/*  599 */             ArrayList arrayList3 = (ArrayList)this.viewScopedFogs.get(view);
/*  600 */             arrayList3.remove(nodeRetained);
/*  601 */             if (arrayList3.size() == 0) {
/*  602 */               this.viewScopedFogs.remove(view);
/*      */             }
/*      */           } 
/*  605 */         } else if (nodeRetained instanceof AlternateAppearanceRetained) {
/*  606 */           ((AlternateAppearanceRetained)nodeRetained).isViewScoped = false;
/*  607 */           this.numberOfAltApps--;
/*  608 */           int j = arrayList.size();
/*  609 */           for (byte b1 = 0; b1 < j; b1++) {
/*  610 */             View view = (View)arrayList.get(b1);
/*  611 */             ArrayList arrayList3 = (ArrayList)this.viewScopedAltAppearances.get(view);
/*  612 */             arrayList3.remove(nodeRetained);
/*  613 */             if (arrayList3.size() == 0) {
/*  614 */               this.viewScopedAltAppearances.remove(view);
/*      */             }
/*      */           } 
/*  617 */         } else if (nodeRetained instanceof BackgroundRetained) {
/*  618 */           ((BackgroundRetained)nodeRetained).isViewScoped = false;
/*  619 */           this.numberOfBgs--;
/*  620 */           int j = arrayList.size();
/*  621 */           for (byte b1 = 0; b1 < j; b1++) {
/*  622 */             View view = (View)arrayList.get(b1);
/*  623 */             ArrayList arrayList3 = (ArrayList)this.viewScopedBackgrounds.get(view);
/*  624 */             arrayList3.remove(nodeRetained);
/*  625 */             if (arrayList3.size() == 0) {
/*  626 */               this.viewScopedBackgrounds.remove(view);
/*      */             }
/*      */           } 
/*  629 */         } else if (nodeRetained instanceof ClipRetained) {
/*  630 */           ((ClipRetained)nodeRetained).isViewScoped = false;
/*  631 */           this.numberOfClips--;
/*  632 */           int j = arrayList.size();
/*  633 */           for (byte b1 = 0; b1 < j; b1++) {
/*  634 */             View view = (View)arrayList.get(b1);
/*  635 */             ArrayList arrayList3 = (ArrayList)this.viewScopedClips.get(view);
/*  636 */             arrayList3.remove(nodeRetained);
/*  637 */             if (arrayList3.size() == 0) {
/*  638 */               this.viewScopedClips.remove(view);
/*      */             }
/*      */           } 
/*  641 */         } else if (nodeRetained instanceof ModelClipRetained) {
/*  642 */           ((ModelClipRetained)nodeRetained).isViewScoped = false;
/*  643 */           this.numberOfModelClips--;
/*  644 */           int j = arrayList.size();
/*  645 */           for (byte b1 = 0; b1 < j; b1++) {
/*  646 */             View view = (View)arrayList.get(b1);
/*  647 */             ArrayList arrayList3 = (ArrayList)this.viewScopedModelClips.get(view);
/*  648 */             arrayList3.remove(nodeRetained);
/*  649 */             if (arrayList3.size() == 0) {
/*  650 */               this.viewScopedModelClips.remove(view);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   LightRetained[] getInfluencingLights(RenderAtom paramRenderAtom, View paramView) {
/*  660 */     LightRetained[] arrayOfLightRetained = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  668 */     synchronized (this.retlights) {
/*  669 */       int i = 0;
/*  670 */       if (paramRenderAtom.geometryAtom.source.inBackgroundGroup) {
/*  671 */         ArrayList arrayList = paramRenderAtom.geometryAtom.source.geometryBackground.lights;
/*  672 */         i = processLights(arrayList, paramRenderAtom, i);
/*      */       } else {
/*  674 */         ArrayList arrayList; if ((arrayList = (ArrayList)this.viewScopedLights.get(paramView)) != null) {
/*  675 */           i = processLights(arrayList, paramRenderAtom, i);
/*      */         }
/*      */         
/*  678 */         i = processLights(this.nonViewScopedLights, paramRenderAtom, i);
/*      */       } 
/*      */       
/*  681 */       boolean bool = false;
/*  682 */       if (paramRenderAtom.lights != null && paramRenderAtom.lights.length == i) {
/*  683 */         for (byte b = 0; b < paramRenderAtom.lights.length; b++) {
/*  684 */           byte b1; for (b1 = 0; b1 < i && 
/*  685 */             paramRenderAtom.lights[b] != this.retlights[b1]; b1++);
/*      */ 
/*      */ 
/*      */           
/*  689 */           if (b1 == i) {
/*  690 */             bool = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } else {
/*  695 */         bool = true;
/*      */       } 
/*  697 */       if (bool) {
/*  698 */         arrayOfLightRetained = new LightRetained[i];
/*  699 */         for (byte b = 0; b < i; b++) {
/*  700 */           arrayOfLightRetained[b] = this.retlights[b];
/*      */         }
/*  702 */         return arrayOfLightRetained;
/*      */       } 
/*  704 */       return paramRenderAtom.lights;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int processLights(ArrayList paramArrayList, RenderAtom paramRenderAtom, int paramInt) {
/*  714 */     BoundingBox boundingBox = paramRenderAtom.localeVwcBounds;
/*  715 */     int i = paramArrayList.size();
/*      */     
/*  717 */     if (i > 0) {
/*  718 */       for (byte b = 0; b < i; b++) {
/*  719 */         LightRetained lightRetained = (LightRetained)paramArrayList.get(b);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  727 */         if (lightRetained.lightOn && lightRetained.switchState.currentSwitchOn && (paramRenderAtom.geometryAtom.source.inBackgroundGroup || boundingBox.intersect(lightRetained.region))) {
/*      */ 
/*      */           
/*  730 */           int j = paramRenderAtom.geometryAtom.source.numlights;
/*  731 */           LightRetained[] arrayOfLightRetained = paramRenderAtom.geometryAtom.source.lights;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  737 */           if (lightRetained.isScoped) {
/*  738 */             for (byte b1 = 0; b1 < j; b1++) {
/*      */ 
/*      */               
/*  741 */               if (lightRetained == arrayOfLightRetained[b1]) {
/*  742 */                 this.retlights[paramInt++] = lightRetained;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } else {
/*  748 */             this.retlights[paramInt++] = lightRetained;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*  753 */     return paramInt;
/*      */   }
/*      */   
/*      */   FogRetained getInfluencingFog(RenderAtom paramRenderAtom, View paramView) {
/*  757 */     FogRetained fogRetained = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  766 */     synchronized (this.lockObj) {
/*  767 */       int i = 0;
/*  768 */       BoundingBox boundingBox = paramRenderAtom.localeVwcBounds;
/*      */       
/*  770 */       if (this.intersectedBounds.length < this.numberOfFogs) {
/*  771 */         this.intersectedBounds = new Bounds[this.numberOfFogs];
/*      */       }
/*  773 */       if (paramRenderAtom.geometryAtom.source.inBackgroundGroup) {
/*  774 */         ArrayList arrayList = paramRenderAtom.geometryAtom.source.geometryBackground.fogs;
/*  775 */         i = processFogs(arrayList, paramRenderAtom, i);
/*      */         
/*  777 */         if (i >= 1)
/*  778 */           fogRetained = this.intersectedFogs[0]; 
/*      */       } else {
/*      */         ArrayList arrayList;
/*  781 */         if ((arrayList = (ArrayList)this.viewScopedFogs.get(paramView)) != null) {
/*  782 */           i = processFogs(arrayList, paramRenderAtom, i);
/*      */         }
/*      */         
/*  785 */         i = processFogs(this.nonViewScopedFogs, paramRenderAtom, i);
/*      */ 
/*      */         
/*  788 */         if (i == 1) {
/*  789 */           fogRetained = this.intersectedFogs[0];
/*      */         }
/*  791 */         else if (i > 1) {
/*  792 */           Bounds bounds = boundingBox.closestIntersection(this.intersectedBounds);
/*  793 */           for (byte b = 0; b < i; b++) {
/*  794 */             if (this.intersectedBounds[b] == bounds) {
/*  795 */               fogRetained = this.intersectedFogs[b];
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  801 */       return fogRetained;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   int processFogs(ArrayList paramArrayList, RenderAtom paramRenderAtom, int paramInt) {
/*  807 */     int i = paramArrayList.size();
/*      */ 
/*      */     
/*  810 */     BoundingBox boundingBox = paramRenderAtom.localeVwcBounds;
/*      */ 
/*      */     
/*  813 */     if (paramArrayList.size() > 0) {
/*  814 */       for (byte b = 0; b < i; b++) {
/*  815 */         FogRetained fogRetained = (FogRetained)paramArrayList.get(b);
/*      */         
/*  817 */         if (fogRetained.region != null && fogRetained.switchState.currentSwitchOn && (paramRenderAtom.geometryAtom.source.inBackgroundGroup || fogRetained.region.intersect(boundingBox))) {
/*      */           
/*  819 */           int j = paramRenderAtom.geometryAtom.source.numfogs;
/*  820 */           FogRetained[] arrayOfFogRetained = paramRenderAtom.geometryAtom.source.fogs;
/*      */           
/*  822 */           if (fogRetained.isScoped) {
/*  823 */             for (byte b1 = 0; b1 < j; b1++) {
/*      */ 
/*      */               
/*  826 */               if (fogRetained == arrayOfFogRetained[b1]) {
/*  827 */                 this.intersectedBounds[paramInt] = fogRetained.region;
/*  828 */                 this.intersectedFogs[paramInt++] = fogRetained;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } else {
/*  834 */             this.intersectedBounds[paramInt] = fogRetained.region;
/*  835 */             this.intersectedFogs[paramInt++] = fogRetained;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*  840 */     return paramInt;
/*      */   }
/*      */   
/*      */   ModelClipRetained getInfluencingModelClip(RenderAtom paramRenderAtom, View paramView) {
/*  844 */     ModelClipRetained modelClipRetained = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  851 */     if (paramRenderAtom.geometryAtom.source.inBackgroundGroup) {
/*  852 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  857 */     synchronized (this.lockObj) {
/*  858 */       BoundingBox boundingBox = paramRenderAtom.localeVwcBounds;
/*  859 */       int i = 0;
/*  860 */       if (this.intersectedBounds.length < this.numberOfModelClips)
/*  861 */         this.intersectedBounds = new Bounds[this.numberOfModelClips]; 
/*      */       ArrayList arrayList;
/*  863 */       if ((arrayList = (ArrayList)this.viewScopedModelClips.get(paramView)) != null) {
/*  864 */         i = processModelClips(arrayList, paramRenderAtom, i);
/*      */       }
/*      */ 
/*      */       
/*  868 */       i = processModelClips(this.nonViewScopedModelClips, paramRenderAtom, i);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  874 */       modelClipRetained = null;
/*  875 */       if (i == 1) {
/*  876 */         modelClipRetained = this.intersectedModelClips[0];
/*  877 */       } else if (i > 1) {
/*  878 */         Bounds bounds = boundingBox.closestIntersection(this.intersectedBounds);
/*  879 */         for (byte b = 0; b < i; b++) {
/*  880 */           if (this.intersectedBounds[b] == bounds) {
/*  881 */             modelClipRetained = this.intersectedModelClips[b];
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  886 */       return modelClipRetained;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   int processModelClips(ArrayList paramArrayList, RenderAtom paramRenderAtom, int paramInt) {
/*  892 */     int i = paramArrayList.size();
/*      */ 
/*      */     
/*  895 */     BoundingBox boundingBox = paramRenderAtom.localeVwcBounds;
/*      */ 
/*      */     
/*  898 */     if (i > 0) {
/*  899 */       for (byte b = 0; b < i; b++) {
/*  900 */         ModelClipRetained modelClipRetained = (ModelClipRetained)paramArrayList.get(b);
/*  901 */         if (modelClipRetained.enableFlag == true && modelClipRetained.region != null && modelClipRetained.switchState.currentSwitchOn)
/*      */         {
/*  903 */           if (modelClipRetained.region.intersect(boundingBox) == true) {
/*  904 */             int j = paramRenderAtom.geometryAtom.source.numModelClips;
/*  905 */             ModelClipRetained[] arrayOfModelClipRetained = paramRenderAtom.geometryAtom.source.modelClips;
/*      */             
/*  907 */             if (modelClipRetained.isScoped) {
/*  908 */               for (byte b1 = 0; b1 < j; b1++) {
/*      */ 
/*      */                 
/*  911 */                 if (arrayOfModelClipRetained[b1] == modelClipRetained) {
/*      */                   
/*  913 */                   this.intersectedBounds[paramInt] = modelClipRetained.region;
/*  914 */                   this.intersectedModelClips[paramInt++] = modelClipRetained;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } else {
/*  920 */               this.intersectedBounds[paramInt] = modelClipRetained.region;
/*  921 */               this.intersectedModelClips[paramInt++] = modelClipRetained;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*  927 */     return paramInt;
/*      */   }
/*      */   
/*      */   BackgroundRetained getApplicationBackground(BoundingSphere paramBoundingSphere, Locale paramLocale, View paramView) {
/*  931 */     BackgroundRetained backgroundRetained = null;
/*      */ 
/*      */     
/*  934 */     boolean bool = false; byte b = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  943 */     synchronized (this.lockObj) {
/*  944 */       int i = 0;
/*  945 */       if (this.intersectedBounds.length < this.numberOfBgs) {
/*  946 */         this.intersectedBounds = new Bounds[this.numberOfBgs];
/*      */       }
/*      */       
/*      */       ArrayList arrayList;
/*  950 */       if ((arrayList = (ArrayList)this.viewScopedBackgrounds.get(paramView)) != null) {
/*  951 */         i = processBgs(arrayList, paramBoundingSphere, i, paramLocale);
/*      */       }
/*  953 */       i = processBgs(this.nonViewScopedBackgrounds, paramBoundingSphere, i, paramLocale);
/*      */ 
/*      */       
/*  956 */       if (i == 1) {
/*  957 */         backgroundRetained = this.intersectedBacks[0];
/*  958 */       } else if (i > 1) {
/*  959 */         Bounds bounds = paramBoundingSphere.closestIntersection(this.intersectedBounds);
/*      */         
/*  961 */         for (b = 0; b < i; b++) {
/*  962 */           if (this.intersectedBounds[b] == bounds) {
/*  963 */             backgroundRetained = this.intersectedBacks[b];
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  969 */       return backgroundRetained;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int processBgs(ArrayList paramArrayList, BoundingSphere paramBoundingSphere, int paramInt, Locale paramLocale) {
/*  976 */     int i = paramArrayList.size();
/*      */ 
/*      */ 
/*      */     
/*  980 */     for (byte b = 0; b < i; b++) {
/*  981 */       BackgroundRetained backgroundRetained = (BackgroundRetained)paramArrayList.get(b);
/*  982 */       if (backgroundRetained.transformedRegion != null && backgroundRetained.switchState.currentSwitchOn) {
/*  983 */         if (backgroundRetained.cachedLocale != paramLocale) {
/*  984 */           this.localeBounds = (Bounds)backgroundRetained.transformedRegion.clone();
/*      */           
/*  986 */           backgroundRetained.cachedLocale.hiRes.difference(paramLocale.hiRes, this.localeTranslation);
/*  987 */           this.localeXform.setIdentity();
/*  988 */           this.localeXform.setTranslation(this.localeTranslation);
/*  989 */           this.localeBounds.transform(this.localeXform);
/*  990 */           if (this.localeBounds.intersect(paramBoundingSphere) == true) {
/*  991 */             this.intersectedBounds[paramInt] = this.localeBounds;
/*  992 */             this.intersectedBacks[paramInt++] = backgroundRetained;
/*      */           }
/*      */         
/*      */         }
/*  996 */         else if (backgroundRetained.transformedRegion.intersect(paramBoundingSphere) == true) {
/*  997 */           this.intersectedBounds[paramInt] = backgroundRetained.transformedRegion;
/*  998 */           this.intersectedBacks[paramInt++] = backgroundRetained;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1003 */     return paramInt;
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
/*      */   double[] backClipDistanceInVworld(BoundingSphere paramBoundingSphere, View paramView) {
/* 1019 */     synchronized (this.lockObj) {
/* 1020 */       double[] arrayOfDouble = null;
/* 1021 */       boolean bool = false;
/* 1022 */       int i = 0;
/* 1023 */       double d = 0.0D;
/* 1024 */       if (this.intersectedBounds.length < this.numberOfClips)
/* 1025 */         this.intersectedBounds = new Bounds[this.numberOfClips]; 
/*      */       ArrayList arrayList;
/* 1027 */       if ((arrayList = (ArrayList)this.viewScopedClips.get(paramView)) != null) {
/* 1028 */         i = processClips(arrayList, paramBoundingSphere, i);
/*      */       }
/* 1030 */       i = processClips(this.nonViewScopedClips, paramBoundingSphere, i);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1035 */       if (i == 1) {
/* 1036 */         d = (this.intersectedClips[0]).backDistanceInVworld;
/* 1037 */         bool = true;
/* 1038 */       } else if (i > 1) {
/* 1039 */         Bounds bounds = paramBoundingSphere.closestIntersection(this.intersectedBounds);
/*      */         
/* 1041 */         for (byte b = 0; b < i; b++) {
/* 1042 */           if (this.intersectedBounds[b] == bounds) {
/* 1043 */             d = (this.intersectedClips[b]).backDistanceInVworld;
/* 1044 */             bool = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1049 */       if (bool) {
/* 1050 */         arrayOfDouble = new double[1];
/* 1051 */         arrayOfDouble[0] = d;
/*      */       } 
/* 1053 */       return arrayOfDouble;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   int processClips(ArrayList paramArrayList, BoundingSphere paramBoundingSphere, int paramInt) {
/* 1059 */     int i = paramArrayList.size();
/*      */ 
/*      */     
/* 1062 */     for (byte b = 0; b < i; b++) {
/* 1063 */       ClipRetained clipRetained = (ClipRetained)paramArrayList.get(b);
/* 1064 */       if (clipRetained.transformedRegion != null && clipRetained.transformedRegion.intersect(paramBoundingSphere) == true && clipRetained.switchState.currentSwitchOn) {
/*      */ 
/*      */         
/* 1067 */         this.intersectedBounds[paramInt] = clipRetained.transformedRegion;
/* 1068 */         this.intersectedClips[paramInt++] = clipRetained;
/*      */       } 
/*      */     } 
/* 1071 */     return paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateLight(Object[] paramArrayOfObject) {
/* 1077 */     LightRetained lightRetained = (LightRetained)paramArrayOfObject[0];
/* 1078 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */ 
/*      */     
/* 1081 */     if ((i & 0x20) != 0) {
/* 1082 */       lightRetained.initMirrorObject(paramArrayOfObject);
/*      */     }
/*      */     
/* 1085 */     if (lightRetained instanceof AmbientLightRetained && (i & 0x8) != 0) {
/*      */       
/* 1087 */       lightRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */     }
/* 1089 */     else if ((i & 0xFA8) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1097 */       Object[] arrayOfObject = getObjectArray();
/* 1098 */       arrayOfObject[0] = paramArrayOfObject[0];
/* 1099 */       arrayOfObject[1] = paramArrayOfObject[1];
/* 1100 */       arrayOfObject[2] = paramArrayOfObject[2];
/* 1101 */       arrayOfObject[3] = paramArrayOfObject[3];
/* 1102 */       arrayOfObject[4] = paramArrayOfObject[4];
/*      */       
/* 1104 */       this.objList.add(arrayOfObject);
/*      */     }
/* 1106 */     else if ((i & 0x40) != 0) {
/* 1107 */       lightRetained.clearMirrorObject(paramArrayOfObject);
/*      */     } else {
/*      */       
/* 1110 */       lightRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateBackground(Object[] paramArrayOfObject) {
/* 1118 */     BackgroundRetained backgroundRetained = (BackgroundRetained)paramArrayOfObject[0];
/* 1119 */     backgroundRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */   }
/*      */ 
/*      */   
/*      */   void updateFog(Object[] paramArrayOfObject) {
/* 1124 */     FogRetained fogRetained = (FogRetained)paramArrayOfObject[0];
/* 1125 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 1126 */     if ((i & 0x10) != 0) {
/* 1127 */       fogRetained.initMirrorObject(paramArrayOfObject);
/*      */ 
/*      */       
/* 1130 */       Object[] arrayOfObject = getObjectArray();
/* 1131 */       arrayOfObject[0] = paramArrayOfObject[0];
/* 1132 */       arrayOfObject[1] = paramArrayOfObject[1];
/* 1133 */       arrayOfObject[2] = paramArrayOfObject[2];
/* 1134 */       arrayOfObject[3] = paramArrayOfObject[3];
/* 1135 */       arrayOfObject[4] = paramArrayOfObject[4];
/* 1136 */       this.objList.add(arrayOfObject);
/*      */     }
/* 1138 */     else if ((i & 0x20) != 0) {
/* 1139 */       fogRetained.clearMirrorObject(paramArrayOfObject);
/*      */     }
/* 1141 */     else if ((i & 0xC1) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1145 */       Object[] arrayOfObject = getObjectArray();
/* 1146 */       arrayOfObject[0] = paramArrayOfObject[0];
/* 1147 */       arrayOfObject[1] = paramArrayOfObject[1];
/* 1148 */       arrayOfObject[2] = paramArrayOfObject[2];
/* 1149 */       arrayOfObject[3] = paramArrayOfObject[3];
/* 1150 */       arrayOfObject[4] = paramArrayOfObject[4];
/* 1151 */       this.objList.add(arrayOfObject);
/*      */     } else {
/*      */       
/* 1154 */       fogRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateAltApp(Object[] paramArrayOfObject) {
/* 1161 */     AlternateAppearanceRetained alternateAppearanceRetained = (AlternateAppearanceRetained)paramArrayOfObject[0];
/* 1162 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 1163 */     if ((i & 0x10) != 0) {
/* 1164 */       AlternateAppearanceRetained alternateAppearanceRetained1 = (AlternateAppearanceRetained)paramArrayOfObject[0];
/* 1165 */       alternateAppearanceRetained1.initMirrorObject(paramArrayOfObject);
/*      */     }
/* 1167 */     else if ((i & 0x20) != 0) {
/* 1168 */       AlternateAppearanceRetained alternateAppearanceRetained1 = (AlternateAppearanceRetained)paramArrayOfObject[0];
/* 1169 */       alternateAppearanceRetained1.clearMirrorObject(paramArrayOfObject);
/*      */     } else {
/*      */       
/* 1172 */       alternateAppearanceRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateClip(Object[] paramArrayOfObject) {
/* 1179 */     ClipRetained clipRetained = (ClipRetained)paramArrayOfObject[0];
/* 1180 */     clipRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */   }
/*      */   
/*      */   void updateModelClip(Object[] paramArrayOfObject) {
/* 1184 */     ModelClipRetained modelClipRetained = (ModelClipRetained)paramArrayOfObject[0];
/*      */     
/* 1186 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*      */     
/* 1188 */     if ((i & 0x80) != 0) {
/* 1189 */       modelClipRetained.initMirrorObject(paramArrayOfObject);
/*      */     }
/* 1191 */     if ((i & 0x100) != 0) {
/* 1192 */       modelClipRetained.clearMirrorObject(paramArrayOfObject);
/*      */     }
/* 1194 */     else if ((i & 0x83) != 0) {
/*      */ 
/*      */       
/* 1197 */       Object[] arrayOfObject = getObjectArray();
/* 1198 */       arrayOfObject[0] = paramArrayOfObject[0];
/* 1199 */       arrayOfObject[1] = paramArrayOfObject[1];
/* 1200 */       arrayOfObject[2] = paramArrayOfObject[2];
/* 1201 */       arrayOfObject[3] = paramArrayOfObject[3];
/* 1202 */       arrayOfObject[4] = paramArrayOfObject[4];
/* 1203 */       this.objList.add(arrayOfObject);
/*      */     } else {
/*      */       
/* 1206 */       modelClipRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void updateBoundingLeaf(Object[] paramArrayOfObject) {
/* 1212 */     BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)paramArrayOfObject[0];
/* 1213 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[3];
/* 1214 */     boundingLeafRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */     
/* 1216 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/* 1217 */       LeafRetained leafRetained = (LeafRetained)arrayOfObject[b];
/* 1218 */       leafRetained.updateBoundingLeaf();
/*      */     } 
/*      */   }
/*      */   
/*      */   void updateShape3D(Object[] paramArrayOfObject) {
/* 1223 */     Shape3DRetained shape3DRetained = (Shape3DRetained)paramArrayOfObject[0];
/* 1224 */     shape3DRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */   }
/*      */   
/*      */   void updateOrientedShape3D(Object[] paramArrayOfObject) {
/* 1228 */     OrientedShape3DRetained orientedShape3DRetained = (OrientedShape3DRetained)paramArrayOfObject[4];
/* 1229 */     orientedShape3DRetained.updateImmediateMirrorObject(paramArrayOfObject);
/*      */   }
/*      */   
/*      */   void updateMorph(Object[] paramArrayOfObject) {
/* 1233 */     MorphRetained morphRetained = (MorphRetained)paramArrayOfObject[0];
/* 1234 */     morphRetained.updateImmediateMirrorObject(paramArrayOfObject);
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
/*      */   void updateTransformChange() {
/* 1246 */     this.targets = this.universe.transformStructure.getTargetList();
/* 1247 */     this.blUsers = this.universe.transformStructure.getBlUsers();
/*      */ 
/*      */     
/* 1250 */     UnorderList unorderList = this.targets.targetList[1];
/* 1251 */     if (unorderList != null) {
/* 1252 */       int i = unorderList.size();
/* 1253 */       Object[] arrayOfObject = unorderList.toArray(false);
/*      */       
/* 1255 */       for (byte b = 0; b < i; b++) {
/* 1256 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/*      */         
/* 1258 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/* 1259 */           if (arrayOfObject1[b1] instanceof LightRetained) {
/* 1260 */             LightRetained lightRetained = (LightRetained)arrayOfObject1[b1];
/* 1261 */             lightRetained.updateImmediateTransformChange();
/* 1262 */             this.xformChangeList.add(arrayOfObject1[b1]);
/*      */           }
/* 1264 */           else if (arrayOfObject1[b1] instanceof FogRetained) {
/* 1265 */             FogRetained fogRetained = (FogRetained)arrayOfObject1[b1];
/* 1266 */             fogRetained.updateImmediateTransformChange();
/* 1267 */             this.xformChangeList.add(arrayOfObject1[b1]);
/*      */           }
/* 1269 */           else if (arrayOfObject1[b1] instanceof AlternateAppearanceRetained) {
/* 1270 */             AlternateAppearanceRetained alternateAppearanceRetained = (AlternateAppearanceRetained)arrayOfObject1[b1];
/*      */             
/* 1272 */             alternateAppearanceRetained.updateImmediateTransformChange();
/* 1273 */             this.xformChangeList.add(arrayOfObject1[b1]);
/*      */           }
/* 1275 */           else if (arrayOfObject1[b1] instanceof BackgroundRetained) {
/* 1276 */             BackgroundRetained backgroundRetained = (BackgroundRetained)arrayOfObject1[b1];
/* 1277 */             backgroundRetained.updateImmediateTransformChange();
/*      */           }
/* 1279 */           else if (arrayOfObject1[b1] instanceof ModelClipRetained) {
/* 1280 */             ModelClipRetained modelClipRetained = (ModelClipRetained)arrayOfObject1[b1];
/* 1281 */             modelClipRetained.updateImmediateTransformChange();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1288 */     unorderList = this.targets.targetList[5];
/* 1289 */     if (unorderList != null) {
/* 1290 */       int i = unorderList.size();
/* 1291 */       Object[] arrayOfObject = unorderList.toArray(false);
/* 1292 */       for (byte b = 0; b < i; b++) {
/* 1293 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 1294 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/* 1295 */           BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)arrayOfObject1[b1];
/* 1296 */           boundingLeafRetained.updateImmediateTransformChange();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1303 */     if (this.blUsers != null) {
/* 1304 */       for (byte b = 0; b < this.blUsers.size(); b++) {
/* 1305 */         LeafRetained leafRetained = (LeafRetained)this.blUsers.get(b);
/* 1306 */         leafRetained.updateBoundingLeaf();
/*      */       } 
/*      */     }
/* 1309 */     this.targets = null;
/* 1310 */     this.blUsers = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Object[] getInfluencingAppearance(RenderAtom paramRenderAtom, View paramView) {
/* 1320 */     AlternateAppearanceRetained alternateAppearanceRetained = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1326 */     Object[] arrayOfObject = new Object[2];
/*      */     
/* 1328 */     if (paramRenderAtom.geometryAtom.source.inBackgroundGroup) {
/* 1329 */       arrayOfObject[0] = Boolean.FALSE;
/* 1330 */       return arrayOfObject;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1336 */     synchronized (this.lockObj) {
/* 1337 */       int i = 0;
/* 1338 */       BoundingBox boundingBox = paramRenderAtom.localeVwcBounds;
/*      */       
/* 1340 */       if (this.intersectedBounds.length < this.numberOfAltApps)
/* 1341 */         this.intersectedBounds = new Bounds[this.numberOfAltApps]; 
/*      */       ArrayList arrayList;
/* 1343 */       if ((arrayList = (ArrayList)this.viewScopedAltAppearances.get(paramView)) != null) {
/* 1344 */         i = processAltApps(arrayList, paramRenderAtom, i);
/*      */       }
/* 1346 */       i = processAltApps(this.nonViewScopedAltAppearances, paramRenderAtom, i);
/*      */ 
/*      */       
/* 1349 */       alternateAppearanceRetained = null;
/* 1350 */       if (i == 1) {
/* 1351 */         alternateAppearanceRetained = this.intersectedAltApps[0];
/* 1352 */       } else if (i > 1) {
/* 1353 */         Bounds bounds = boundingBox.closestIntersection(this.intersectedBounds);
/* 1354 */         for (byte b = 0; b < i; b++) {
/* 1355 */           if (this.intersectedBounds[b] == bounds) {
/* 1356 */             alternateAppearanceRetained = this.intersectedAltApps[b];
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1361 */       if (alternateAppearanceRetained == null) {
/* 1362 */         arrayOfObject[0] = Boolean.FALSE;
/* 1363 */         return arrayOfObject;
/*      */       } 
/* 1365 */       arrayOfObject[0] = Boolean.TRUE;
/* 1366 */       arrayOfObject[1] = alternateAppearanceRetained.appearance;
/* 1367 */       return arrayOfObject;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int processAltApps(ArrayList paramArrayList, RenderAtom paramRenderAtom, int paramInt) {
/* 1374 */     int i = paramArrayList.size();
/*      */ 
/*      */     
/* 1377 */     BoundingBox boundingBox = paramRenderAtom.localeVwcBounds;
/*      */ 
/*      */ 
/*      */     
/* 1381 */     if (i > 0) {
/* 1382 */       for (byte b = 0; b < i; b++) {
/* 1383 */         AlternateAppearanceRetained alternateAppearanceRetained = (AlternateAppearanceRetained)paramArrayList.get(b);
/*      */ 
/*      */ 
/*      */         
/* 1387 */         if (alternateAppearanceRetained.region != null && alternateAppearanceRetained.switchState.currentSwitchOn && 
/* 1388 */           alternateAppearanceRetained.region.intersect(boundingBox) == true) {
/* 1389 */           int j = paramRenderAtom.geometryAtom.source.numAltApps;
/* 1390 */           AlternateAppearanceRetained[] arrayOfAlternateAppearanceRetained = paramRenderAtom.geometryAtom.source.altApps;
/* 1391 */           if (alternateAppearanceRetained.isScoped) {
/* 1392 */             for (byte b1 = 0; b1 < j; b1++) {
/*      */ 
/*      */               
/* 1395 */               if (alternateAppearanceRetained == arrayOfAlternateAppearanceRetained[b1]) {
/*      */                 
/* 1397 */                 this.intersectedBounds[paramInt] = alternateAppearanceRetained.region;
/* 1398 */                 this.intersectedAltApps[paramInt++] = alternateAppearanceRetained;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } else {
/* 1404 */             this.intersectedBounds[paramInt] = alternateAppearanceRetained.region;
/* 1405 */             this.intersectedAltApps[paramInt++] = alternateAppearanceRetained;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1411 */     return paramInt;
/*      */   }
/*      */   
/*      */   void initViewSpecificInfo(J3dMessage paramJ3dMessage) {
/* 1415 */     int[] arrayOfInt = (int[])paramJ3dMessage.args[2];
/* 1416 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[1];
/* 1417 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[0];
/* 1418 */     if (arrayList2 != null) {
/*      */       
/* 1420 */       int i = arrayList2.size();
/* 1421 */       for (byte b = 0; b < i; b++) {
/* 1422 */         ViewSpecificGroupRetained viewSpecificGroupRetained = (ViewSpecificGroupRetained)arrayList2.get(b);
/* 1423 */         ArrayList arrayList = (ArrayList)arrayList1.get(b);
/* 1424 */         int j = arrayOfInt[b];
/*      */         
/* 1426 */         viewSpecificGroupRetained.cachedViewList.add(j, arrayList);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearViewSpecificInfo(J3dMessage paramJ3dMessage) {
/* 1437 */     int[] arrayOfInt = (int[])paramJ3dMessage.args[1];
/* 1438 */     ArrayList arrayList = (ArrayList)paramJ3dMessage.args[0];
/* 1439 */     if (arrayList != null) {
/* 1440 */       int i = arrayList.size();
/* 1441 */       for (byte b = 0; b < i; b++) {
/* 1442 */         ViewSpecificGroupRetained viewSpecificGroupRetained = (ViewSpecificGroupRetained)arrayList.get(b);
/* 1443 */         int j = arrayOfInt[b];
/* 1444 */         if (j == -1) {
/* 1445 */           int k = viewSpecificGroupRetained.cachedViewList.size();
/* 1446 */           for (byte b1 = 0; b1 < k; b1++) {
/* 1447 */             ArrayList arrayList1 = (ArrayList)viewSpecificGroupRetained.cachedViewList.get(b1);
/* 1448 */             arrayList1.clear();
/*      */           } 
/* 1450 */           viewSpecificGroupRetained.cachedViewList.clear();
/*      */         } else {
/*      */           
/* 1453 */           ArrayList arrayList1 = (ArrayList)viewSpecificGroupRetained.cachedViewList.remove(j);
/* 1454 */           arrayList1.clear();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void updateViewSpecificGroupChanged(J3dMessage paramJ3dMessage) {
/* 1461 */     int i = ((Integer)paramJ3dMessage.args[0]).intValue();
/* 1462 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[1];
/*      */     
/* 1464 */     ArrayList arrayList1 = null;
/* 1465 */     ArrayList arrayList2 = null;
/* 1466 */     ArrayList arrayList3 = null;
/* 1467 */     ArrayList arrayList4 = null;
/* 1468 */     ArrayList arrayList5 = null;
/* 1469 */     ArrayList arrayList6 = null;
/*      */ 
/*      */     
/* 1472 */     if ((i & 0x2) != 0 || (i & true) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1476 */       View view = (View)arrayOfObject[0];
/* 1477 */       ArrayList arrayList7 = (ArrayList)arrayOfObject[1];
/* 1478 */       ArrayList arrayList8 = (ArrayList)arrayOfObject[2];
/* 1479 */       int[] arrayOfInt = (int[])arrayOfObject[3];
/* 1480 */       int j = arrayList7.size();
/*      */ 
/*      */       
/* 1483 */       if (view != null) {
/* 1484 */         byte b; for (b = 0; b < j; b++) {
/* 1485 */           ViewSpecificGroupRetained viewSpecificGroupRetained = (ViewSpecificGroupRetained)arrayList7.get(b);
/* 1486 */           int k = arrayOfInt[b];
/* 1487 */           viewSpecificGroupRetained.updateCachedInformation(2, view, k);
/*      */         } 
/*      */         
/* 1490 */         j = arrayList8.size();
/*      */ 
/*      */         
/* 1493 */         if (j > 0) {
/*      */           
/* 1495 */           for (b = 0; b < j; b++) {
/* 1496 */             Object object = arrayList8.get(b);
/* 1497 */             if (object instanceof LightRetained) {
/* 1498 */               ((LightRetained)object).isViewScoped = true;
/* 1499 */               this.numberOfLights++;
/* 1500 */               if (arrayList1 == null && (
/* 1501 */                 arrayList1 = (ArrayList)this.viewScopedLights.get(view)) == null) {
/* 1502 */                 arrayList1 = new ArrayList();
/* 1503 */                 this.viewScopedLights.put(view, arrayList1);
/*      */               } 
/*      */               
/* 1506 */               arrayList1.add(object);
/*      */             } 
/* 1508 */             if (object instanceof FogRetained) {
/* 1509 */               ((FogRetained)object).isViewScoped = true;
/* 1510 */               this.numberOfFogs++;
/* 1511 */               if (arrayList2 == null && (
/* 1512 */                 arrayList2 = (ArrayList)this.viewScopedFogs.get(view)) == null) {
/* 1513 */                 arrayList2 = new ArrayList();
/* 1514 */                 this.viewScopedFogs.put(view, arrayList2);
/*      */               } 
/*      */               
/* 1517 */               arrayList2.add(object);
/*      */             } 
/* 1519 */             if (object instanceof ModelClipRetained) {
/* 1520 */               ((ModelClipRetained)object).isViewScoped = true;
/* 1521 */               this.numberOfModelClips++;
/* 1522 */               if (arrayList3 == null && (
/* 1523 */                 arrayList3 = (ArrayList)this.viewScopedModelClips.get(view)) == null) {
/* 1524 */                 arrayList3 = new ArrayList();
/* 1525 */                 this.viewScopedModelClips.put(view, arrayList3);
/*      */               } 
/*      */               
/* 1528 */               arrayList3.add(object);
/*      */             } 
/* 1530 */             if (object instanceof AlternateAppearanceRetained) {
/* 1531 */               ((AlternateAppearanceRetained)object).isViewScoped = true;
/* 1532 */               this.numberOfAltApps++;
/* 1533 */               if (arrayList4 == null && (
/* 1534 */                 arrayList4 = (ArrayList)this.viewScopedAltAppearances.get(view)) == null) {
/* 1535 */                 arrayList4 = new ArrayList();
/* 1536 */                 this.viewScopedAltAppearances.put(view, arrayList4);
/*      */               } 
/*      */               
/* 1539 */               arrayList4.add(object);
/*      */             } 
/* 1541 */             if (object instanceof ClipRetained) {
/* 1542 */               ((ClipRetained)object).isViewScoped = true;
/* 1543 */               this.numberOfClips++;
/* 1544 */               if (arrayList6 == null && (
/* 1545 */                 arrayList6 = (ArrayList)this.viewScopedClips.get(view)) == null) {
/* 1546 */                 arrayList6 = new ArrayList();
/* 1547 */                 this.viewScopedClips.put(view, arrayList6);
/*      */               } 
/*      */               
/* 1550 */               arrayList6.add(object);
/*      */             } 
/* 1552 */             if (object instanceof BackgroundRetained) {
/* 1553 */               ((BackgroundRetained)object).isViewScoped = true;
/* 1554 */               this.numberOfBgs++;
/* 1555 */               if (arrayList5 == null && (
/* 1556 */                 arrayList5 = (ArrayList)this.viewScopedBackgrounds.get(view)) == null) {
/* 1557 */                 arrayList5 = new ArrayList();
/* 1558 */                 this.viewScopedBackgrounds.put(view, arrayList5);
/*      */               } 
/*      */               
/* 1561 */               arrayList5.add(object);
/*      */             } 
/*      */           } 
/* 1564 */           if (this.numberOfLights > this.retlights.length)
/* 1565 */             this.retlights = new LightRetained[this.numberOfLights]; 
/* 1566 */           if (this.intersectedFogs.length < this.numberOfFogs)
/* 1567 */             this.intersectedFogs = new FogRetained[this.numberOfFogs]; 
/* 1568 */           if (this.intersectedAltApps.length < this.numberOfAltApps)
/* 1569 */             this.intersectedAltApps = new AlternateAppearanceRetained[this.numberOfAltApps]; 
/* 1570 */           if (this.intersectedBacks.length < this.numberOfBgs)
/* 1571 */             this.intersectedBacks = new BackgroundRetained[this.numberOfBgs]; 
/* 1572 */           if (this.intersectedClips.length < this.numberOfClips)
/* 1573 */             this.intersectedClips = new ClipRetained[this.numberOfClips]; 
/* 1574 */           if (this.intersectedModelClips.length < this.numberOfModelClips)
/* 1575 */             this.intersectedModelClips = new ModelClipRetained[this.numberOfModelClips]; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1579 */     if ((i & 0x4) != 0 || (i & true) != 0) {
/*      */       View view;
/*      */       
/*      */       int[] arrayOfInt;
/*      */       
/*      */       ArrayList arrayList8;
/*      */       
/*      */       ArrayList arrayList7;
/*      */       
/* 1588 */       if ((i & 0x4) != 0) {
/* 1589 */         view = (View)arrayOfObject[0];
/* 1590 */         arrayList7 = (ArrayList)arrayOfObject[1];
/* 1591 */         arrayList8 = (ArrayList)arrayOfObject[2];
/* 1592 */         arrayOfInt = (int[])arrayOfObject[3];
/*      */       } else {
/*      */         
/* 1595 */         view = (View)arrayOfObject[4];
/* 1596 */         arrayList7 = (ArrayList)arrayOfObject[5];
/* 1597 */         arrayList8 = (ArrayList)arrayOfObject[6];
/* 1598 */         arrayOfInt = (int[])arrayOfObject[7];
/*      */       } 
/*      */       
/* 1601 */       if (view != null) {
/* 1602 */         int j = arrayList7.size(); byte b;
/* 1603 */         for (b = 0; b < j; b++) {
/* 1604 */           ViewSpecificGroupRetained viewSpecificGroupRetained = (ViewSpecificGroupRetained)arrayList7.get(b);
/* 1605 */           int k = arrayOfInt[b];
/* 1606 */           viewSpecificGroupRetained.updateCachedInformation(4, view, k);
/*      */         } 
/*      */         
/* 1609 */         j = arrayList8.size();
/*      */         
/* 1611 */         if (j > 0) {
/*      */           
/* 1613 */           for (b = 0; b < j; b++) {
/* 1614 */             Object object = arrayList8.get(b);
/* 1615 */             if (object instanceof LightRetained) {
/* 1616 */               ((LightRetained)object).isViewScoped = false;
/* 1617 */               this.numberOfLights--;
/* 1618 */               if (arrayList1 == null) {
/* 1619 */                 arrayList1 = (ArrayList)this.viewScopedLights.get(view);
/*      */               }
/* 1621 */               arrayList1.remove(object);
/*      */             } 
/* 1623 */             if (object instanceof FogRetained) {
/* 1624 */               ((FogRetained)object).isViewScoped = false;
/* 1625 */               this.numberOfFogs--;
/* 1626 */               if (arrayList2 == null) {
/* 1627 */                 arrayList2 = (ArrayList)this.viewScopedFogs.get(view);
/*      */               }
/* 1629 */               arrayList2.remove(object);
/*      */             } 
/* 1631 */             if (object instanceof ModelClipRetained) {
/* 1632 */               ((ModelClipRetained)object).isViewScoped = false;
/* 1633 */               this.numberOfModelClips--;
/* 1634 */               if (arrayList3 == null) {
/* 1635 */                 arrayList3 = (ArrayList)this.viewScopedModelClips.get(view);
/*      */               }
/* 1637 */               arrayList3.remove(object);
/*      */             } 
/* 1639 */             if (object instanceof AlternateAppearanceRetained) {
/* 1640 */               ((AlternateAppearanceRetained)object).isViewScoped = false;
/* 1641 */               this.numberOfAltApps--;
/* 1642 */               if (arrayList4 == null) {
/* 1643 */                 arrayList4 = (ArrayList)this.viewScopedAltAppearances.get(view);
/*      */               }
/* 1645 */               arrayList4.remove(object);
/*      */             } 
/* 1647 */             if (object instanceof ClipRetained) {
/* 1648 */               ((ClipRetained)object).isViewScoped = false;
/* 1649 */               this.numberOfClips--;
/* 1650 */               if (arrayList6 == null) {
/* 1651 */                 arrayList6 = (ArrayList)this.viewScopedClips.get(view);
/*      */               }
/* 1653 */               arrayList6.remove(object);
/*      */             } 
/* 1655 */             if (object instanceof BackgroundRetained) {
/* 1656 */               ((BackgroundRetained)object).isViewScoped = false;
/* 1657 */               this.numberOfBgs++;
/* 1658 */               if (arrayList5 == null) {
/* 1659 */                 arrayList5 = (ArrayList)this.viewScopedBackgrounds.get(view);
/*      */               }
/* 1661 */               arrayList5.remove(object);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1667 */           if (arrayList1 != null && arrayList1.size() == 0)
/* 1668 */             this.viewScopedLights.remove(view); 
/* 1669 */           if (arrayList2 != null && arrayList2.size() == 0)
/* 1670 */             this.viewScopedFogs.remove(view); 
/* 1671 */           if (arrayList3 != null && arrayList3.size() == 0)
/* 1672 */             this.viewScopedModelClips.remove(view); 
/* 1673 */           if (arrayList4 != null && arrayList4.size() == 0)
/* 1674 */             this.viewScopedAltAppearances.remove(view); 
/* 1675 */           if (arrayList6 != null && arrayList6.size() == 0)
/* 1676 */             this.viewScopedClips.remove(view); 
/* 1677 */           if (arrayList5 != null && arrayList5.size() == 0) {
/* 1678 */             this.viewScopedBackgrounds.remove(view);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean isLightScopedToThisView(Object paramObject, View paramView) {
/* 1686 */     LightRetained lightRetained = (LightRetained)paramObject;
/* 1687 */     if (lightRetained.isViewScoped) {
/* 1688 */       ArrayList arrayList = (ArrayList)this.viewScopedLights.get(paramView);
/*      */       
/* 1690 */       if (arrayList == null || !arrayList.contains(lightRetained))
/* 1691 */         return false; 
/*      */     } 
/* 1693 */     return true;
/*      */   }
/*      */   
/*      */   boolean isFogScopedToThisView(Object paramObject, View paramView) {
/* 1697 */     FogRetained fogRetained = (FogRetained)paramObject;
/* 1698 */     if (fogRetained.isViewScoped) {
/* 1699 */       ArrayList arrayList = (ArrayList)this.viewScopedFogs.get(paramView);
/*      */       
/* 1701 */       if (arrayList == null || !arrayList.contains(fogRetained))
/* 1702 */         return false; 
/*      */     } 
/* 1704 */     return true;
/*      */   }
/*      */   
/*      */   boolean isAltAppScopedToThisView(Object paramObject, View paramView) {
/* 1708 */     AlternateAppearanceRetained alternateAppearanceRetained = (AlternateAppearanceRetained)paramObject;
/* 1709 */     if (alternateAppearanceRetained.isViewScoped) {
/* 1710 */       ArrayList arrayList = (ArrayList)this.viewScopedAltAppearances.get(paramView);
/*      */       
/* 1712 */       if (arrayList == null || !arrayList.contains(alternateAppearanceRetained))
/* 1713 */         return false; 
/*      */     } 
/* 1715 */     return true;
/*      */   }
/*      */   
/*      */   boolean isBgScopedToThisView(Object paramObject, View paramView) {
/* 1719 */     BackgroundRetained backgroundRetained = (BackgroundRetained)paramObject;
/* 1720 */     if (backgroundRetained.isViewScoped) {
/* 1721 */       ArrayList arrayList = (ArrayList)this.viewScopedBackgrounds.get(paramView);
/*      */       
/* 1723 */       if (arrayList == null || !arrayList.contains(backgroundRetained))
/* 1724 */         return false; 
/*      */     } 
/* 1726 */     return true;
/*      */   }
/*      */   
/*      */   boolean isClipScopedToThisView(Object paramObject, View paramView) {
/* 1730 */     ClipRetained clipRetained = (ClipRetained)paramObject;
/* 1731 */     if (clipRetained.isViewScoped) {
/* 1732 */       ArrayList arrayList = (ArrayList)this.viewScopedClips.get(paramView);
/*      */       
/* 1734 */       if (arrayList == null || !arrayList.contains(clipRetained))
/* 1735 */         return false; 
/*      */     } 
/* 1737 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   boolean isMclipScopedToThisView(Object paramObject, View paramView) {
/* 1742 */     ModelClipRetained modelClipRetained = (ModelClipRetained)paramObject;
/* 1743 */     if (modelClipRetained.isViewScoped) {
/* 1744 */       ArrayList arrayList = (ArrayList)this.viewScopedModelClips.get(paramView);
/*      */       
/* 1746 */       if (arrayList == null || !arrayList.contains(modelClipRetained))
/* 1747 */         return false; 
/*      */     } 
/* 1749 */     return true;
/*      */   }
/*      */   
/*      */   void cleanup() {}
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\RenderingEnvironmentStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */