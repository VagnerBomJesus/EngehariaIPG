/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
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
/*     */ class SoundStructure
/*     */   extends J3dStructure
/*     */ {
/*  28 */   UnorderList nonViewScopedSounds = new UnorderList(SoundRetained.class);
/*  29 */   HashMap viewScopedSounds = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   UnorderList nonViewScopedSoundscapes = new UnorderList(SoundscapeRetained.class);
/*  35 */   HashMap viewScopedSoundscapes = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   UnorderList viewPlatforms = new UnorderList(ViewPlatformRetained.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   BoundingSphere tempSphere = new BoundingSphere();
/*  46 */   BoundingSphere vpsphere = new BoundingSphere();
/*     */ 
/*     */ 
/*     */   
/*  50 */   ArrayList objList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  54 */   ArrayList xformChangeList = new ArrayList();
/*     */ 
/*     */   
/*  57 */   ArrayList switchChangeLeafNodes = new ArrayList();
/*  58 */   ArrayList switchChangeLeafMasks = new ArrayList();
/*     */   
/*     */   boolean transformMsg = false;
/*     */   
/*  62 */   UpdateTargets targets = null;
/*     */   
/*     */   static final boolean debugFlag = false;
/*     */   
/*     */   static final boolean internalErrors = false;
/*     */   
/*  68 */   SoundStructure(VirtualUniverse paramVirtualUniverse) { super(paramVirtualUniverse, 512); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processMessages(long paramLong) {
/*  74 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/*  75 */     int i = getNumMessage();
/*     */ 
/*     */     
/*  78 */     if (i <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  83 */     for (byte b = 0; b < i; b++) {
/*  84 */       J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/*     */       
/*  86 */       switch (j3dMessage.type) {
/*     */         
/*     */         case 0:
/*  89 */           insertNodes(j3dMessage);
/*     */           break;
/*     */         case 1:
/*  92 */           removeNodes(j3dMessage);
/*     */           break;
/*     */         case 38:
/*  95 */           changeNodeAttrib(j3dMessage);
/*     */           break;
/*     */         case 45:
/*  98 */           changeNodeState(j3dMessage);
/*     */           break;
/*     */         
/*     */         case 39:
/*     */         case 40:
/* 103 */           changeNodeAttrib(j3dMessage);
/*     */           break;
/*     */         case 3:
/* 106 */           this.transformMsg = true;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 27:
/* 112 */           if (this.universe.transformStructure.getLazyUpdate()) {
/* 113 */             this.transformMsg = true;
/*     */           }
/*     */           break;
/*     */         case 56:
/* 117 */           updateViewSpecificGroupChanged(j3dMessage);
/*     */           break;
/*     */       } 
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
/* 139 */       j3dMessage.decRefcount();
/*     */     } 
/* 141 */     if (this.transformMsg) {
/* 142 */       this.targets = this.universe.transformStructure.getTargetList();
/* 143 */       updateTransformChange(this.targets, paramLong);
/* 144 */       this.transformMsg = false;
/* 145 */       this.targets = null;
/*     */     } 
/*     */     
/* 148 */     Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*     */   }
/*     */   
/*     */   void insertNodes(J3dMessage paramJ3dMessage) {
/* 152 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/* 153 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/* 154 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*     */     
/*     */     int i;
/* 157 */     for (i = 0; i < arrayOfObject.length; i++) {
/* 158 */       Object object = arrayOfObject[i];
/* 159 */       if (object instanceof SoundRetained) {
/* 160 */         addNonScopedSound((SoundRetained)object);
/*     */       }
/* 162 */       if (object instanceof SoundscapeRetained) {
/* 163 */         addNonSoundscape((SoundscapeRetained)object);
/*     */       }
/*     */     } 
/*     */     
/* 167 */     if (arrayList1 != null) {
/* 168 */       i = arrayList1.size();
/*     */       
/* 170 */       for (byte b = 0; b < i; b++) {
/* 171 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b);
/* 172 */         ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 173 */         int j = arrayList.size();
/* 174 */         if (nodeRetained instanceof SoundRetained) {
/* 175 */           ((SoundRetained)nodeRetained).isViewScoped = true;
/* 176 */           for (byte b1 = 0; b1 < j; b1++) {
/* 177 */             View view = (View)arrayList.get(b1);
/* 178 */             addScopedSound((SoundRetained)nodeRetained, view);
/*     */           }
/*     */         
/* 181 */         } else if (nodeRetained instanceof SoundscapeRetained) {
/* 182 */           ((SoundscapeRetained)nodeRetained).isViewScoped = true;
/* 183 */           for (byte b1 = 0; b1 < j; b1++) {
/* 184 */             View view = (View)arrayList.get(b1);
/* 185 */             addScopedSoundscape((SoundscapeRetained)nodeRetained, view);
/*     */           } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addScopedSound(SoundRetained paramSoundRetained, View paramView) {
/* 207 */     ArrayList arrayList = (ArrayList)this.viewScopedSounds.get(paramView);
/* 208 */     if (arrayList == null) {
/* 209 */       arrayList = new ArrayList();
/* 210 */       this.viewScopedSounds.put(paramView, arrayList);
/*     */     } 
/* 212 */     arrayList.add(paramSoundRetained);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   void addNonScopedSound(SoundRetained paramSoundRetained) { this.nonViewScopedSounds.add(paramSoundRetained); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addScopedSoundscape(SoundscapeRetained paramSoundscapeRetained, View paramView) {
/* 225 */     ArrayList arrayList = (ArrayList)this.viewScopedSoundscapes.get(paramView);
/* 226 */     if (arrayList == null) {
/* 227 */       arrayList = new ArrayList();
/* 228 */       this.viewScopedSoundscapes.put(paramView, arrayList);
/*     */     } 
/* 230 */     arrayList.add(paramSoundscapeRetained);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   void addNonSoundscape(SoundscapeRetained paramSoundscapeRetained) { this.nonViewScopedSoundscapes.add(paramSoundscapeRetained); }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeNodes(J3dMessage paramJ3dMessage) {
/* 241 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/* 242 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/* 243 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*     */     
/*     */     int i;
/* 246 */     for (i = 0; i < arrayOfObject.length; i++) {
/* 247 */       Object object = arrayOfObject[i];
/* 248 */       if (object instanceof SoundRetained) {
/* 249 */         deleteNonScopedSound((SoundRetained)object);
/*     */       }
/* 251 */       if (object instanceof SoundscapeRetained) {
/* 252 */         deleteNonScopedSoundscape((SoundscapeRetained)object);
/*     */       }
/*     */     } 
/*     */     
/* 256 */     if (arrayList1 != null) {
/* 257 */       i = arrayList1.size();
/*     */       
/* 259 */       for (byte b = 0; b < i; b++) {
/* 260 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b);
/* 261 */         ArrayList arrayList = (ArrayList)arrayList2.get(b);
/*     */         
/* 263 */         int j = arrayList.size();
/*     */         
/* 265 */         if (nodeRetained instanceof SoundRetained) {
/* 266 */           ((SoundRetained)nodeRetained).isViewScoped = false;
/* 267 */           for (byte b1 = 0; b1 < j; b1++) {
/* 268 */             View view = (View)arrayList.get(b1);
/* 269 */             deleteScopedSound((SoundRetained)nodeRetained, view);
/*     */           }
/*     */         
/* 272 */         } else if (nodeRetained instanceof SoundscapeRetained) {
/* 273 */           ((SoundscapeRetained)nodeRetained).isViewScoped = false;
/* 274 */           for (byte b1 = 0; b1 < j; b1++) {
/* 275 */             View view = (View)arrayList.get(b1);
/* 276 */             deleteScopedSoundscape((SoundscapeRetained)nodeRetained, view);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void deleteNonScopedSound(SoundRetained paramSoundRetained) {
/* 284 */     if (!this.nonViewScopedSounds.isEmpty()) {
/*     */       
/* 286 */       int i = this.nonViewScopedSounds.indexOf(paramSoundRetained);
/* 287 */       this.nonViewScopedSounds.remove(i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 292 */   void deleteNonScopedSoundscape(SoundscapeRetained paramSoundscapeRetained) { boolean bool = this.nonViewScopedSoundscapes.remove(paramSoundscapeRetained); }
/*     */ 
/*     */   
/*     */   void deleteScopedSound(SoundRetained paramSoundRetained, View paramView) {
/* 296 */     ArrayList arrayList = (ArrayList)this.viewScopedSounds.get(paramView);
/* 297 */     if (!arrayList.isEmpty()) {
/*     */       
/* 299 */       int i = arrayList.indexOf(paramSoundRetained);
/* 300 */       arrayList.remove(i);
/*     */     } 
/* 302 */     if (arrayList.isEmpty())
/* 303 */       this.viewScopedSounds.remove(paramView); 
/*     */   }
/*     */   
/*     */   void deleteScopedSoundscape(SoundscapeRetained paramSoundscapeRetained, View paramView) {
/* 307 */     ArrayList arrayList = (ArrayList)this.viewScopedSoundscapes.get(paramView);
/* 308 */     if (!arrayList.isEmpty()) {
/*     */       
/* 310 */       int i = arrayList.indexOf(paramSoundscapeRetained);
/* 311 */       arrayList.remove(i);
/*     */     } 
/* 313 */     if (arrayList.isEmpty()) {
/* 314 */       this.viewScopedSoundscapes.remove(paramView);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void changeNodeAttrib(J3dMessage paramJ3dMessage) {
/* 320 */     Object object1 = paramJ3dMessage.args[0];
/* 321 */     Object object2 = paramJ3dMessage.args[1];
/*     */ 
/*     */ 
/*     */     
/* 325 */     if (object1 instanceof SoundRetained) {
/* 326 */       int i = ((Integer)object2).intValue();
/*     */ 
/*     */       
/* 329 */       if ((i & 0x20) > 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 335 */       if ((i & true) > 0) {
/* 336 */         loadSound((SoundRetained)object1, true);
/*     */       }
/* 338 */       ((SoundRetained)object1).updateMirrorObject(paramJ3dMessage.args);
/*     */     } 
/* 340 */     if (object1 instanceof SoundscapeRetained)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 346 */       ((SoundscapeRetained)object1).updateTransformChange();
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
/*     */   void changeNodeState(J3dMessage paramJ3dMessage) {
/* 359 */     Object object1 = paramJ3dMessage.args[0];
/* 360 */     Object object2 = paramJ3dMessage.args[1];
/*     */ 
/*     */     
/* 363 */     if (object1 instanceof SoundRetained) {
/* 364 */       int i = ((Integer)object2).intValue();
/*     */ 
/*     */       
/* 367 */       if ((i & true) > 0) {
/* 368 */         loadSound((SoundRetained)object1, false);
/*     */       }
/* 370 */       if ((i & 0x20) > 0) {
/* 371 */         enableSound((SoundRetained)object1);
/*     */       }
/* 373 */       ((SoundRetained)object1).updateMirrorObject(paramJ3dMessage.args);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Bounds paramBounds) {
/* 379 */     if (paramBounds == null) {
/* 380 */       return false;
/*     */     }
/* 382 */     ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.viewPlatforms.toArray(false);
/*     */ 
/*     */     
/* 385 */     for (int i = this.viewPlatforms.arraySize() - 1; i >= 0; i--) {
/* 386 */       (arrayOfViewPlatformRetained[i]).schedSphere.getWithLock(this.tempSphere);
/* 387 */       if (this.tempSphere.intersect(paramBounds)) {
/* 388 */         return true;
/*     */       }
/*     */     } 
/* 391 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   void loadSound(SoundRetained paramSoundRetained, boolean paramBoolean) {
/* 396 */     MediaContainer mediaContainer = paramSoundRetained.getSoundData();
/* 397 */     ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.viewPlatforms.toArray(false);
/*     */ 
/*     */     
/* 400 */     for (int i = this.viewPlatforms.arraySize() - 1; i >= 0; i--) {
/* 401 */       View[] arrayOfView = arrayOfViewPlatformRetained[i].getViewList();
/* 402 */       for (int j = arrayOfView.length - 1; j >= 0; j--) {
/* 403 */         View view = arrayOfView[j];
/*     */         
/* 405 */         view.soundScheduler.loadSound(paramSoundRetained, paramBoolean);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void enableSound(SoundRetained paramSoundRetained) {
/* 411 */     ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.viewPlatforms.toArray(false);
/*     */     
/* 413 */     for (int i = this.viewPlatforms.arraySize() - 1; i >= 0; i--) {
/* 414 */       View[] arrayOfView = arrayOfViewPlatformRetained[i].getViewList();
/* 415 */       for (int j = arrayOfView.length - 1; j >= 0; j--) {
/* 416 */         View view = arrayOfView[j];
/* 417 */         view.soundScheduler.enableSound(paramSoundRetained);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void muteSound(SoundRetained paramSoundRetained) {
/* 423 */     ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.viewPlatforms.toArray(false);
/*     */     
/* 425 */     for (int i = this.viewPlatforms.arraySize() - 1; i >= 0; i--) {
/* 426 */       View[] arrayOfView = arrayOfViewPlatformRetained[i].getViewList();
/* 427 */       for (int j = arrayOfView.length - 1; j >= 0; j--) {
/* 428 */         View view = arrayOfView[j];
/* 429 */         view.soundScheduler.muteSound(paramSoundRetained);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void pauseSound(SoundRetained paramSoundRetained) {
/* 435 */     ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.viewPlatforms.toArray(false);
/*     */     
/* 437 */     for (int i = this.viewPlatforms.arraySize() - 1; i >= 0; i--) {
/* 438 */       View[] arrayOfView = arrayOfViewPlatformRetained[i].getViewList();
/* 439 */       for (int j = arrayOfView.length - 1; j >= 0; j--) {
/* 440 */         View view = arrayOfView[j];
/* 441 */         view.soundScheduler.pauseSound(paramSoundRetained);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processSwitchChanged(J3dMessage paramJ3dMessage) {}
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
/*     */   UnorderList getSoundList(View paramView) {
/* 480 */     ArrayList arrayList = (ArrayList)this.viewScopedSounds.get(paramView);
/*     */     
/* 482 */     if (arrayList == null)
/* 483 */       return this.nonViewScopedSounds; 
/* 484 */     UnorderList unorderList = (UnorderList)this.nonViewScopedSounds.clone();
/* 485 */     int i = arrayList.size();
/* 486 */     for (byte b = 0; b < i; b++) {
/* 487 */       unorderList.add(arrayList.get(b));
/*     */     }
/* 489 */     return unorderList;
/*     */   }
/*     */   
/*     */   UnorderList getSoundscapeList(View paramView) {
/* 493 */     ArrayList arrayList = (ArrayList)this.viewScopedSoundscapes.get(paramView);
/*     */     
/* 495 */     if (arrayList == null)
/* 496 */       return this.nonViewScopedSoundscapes; 
/* 497 */     UnorderList unorderList = (UnorderList)this.nonViewScopedSoundscapes.clone();
/* 498 */     int i = arrayList.size();
/* 499 */     for (byte b = 0; b < i; b++) {
/* 500 */       unorderList.add(arrayList.get(b));
/*     */     }
/* 502 */     return unorderList;
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
/*     */   void updateTransformChange(UpdateTargets paramUpdateTargets, long paramLong) {
/* 563 */     UnorderList unorderList = paramUpdateTargets.targetList[3];
/* 564 */     if (unorderList != null) {
/*     */ 
/*     */       
/* 567 */       int i = unorderList.size();
/* 568 */       Object[] arrayOfObject = unorderList.toArray(false);
/*     */       
/* 570 */       for (byte b = 0; b < i; b++) {
/* 571 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/*     */         
/* 573 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*     */           
/* 575 */           if (arrayOfObject1[b1] instanceof ConeSoundRetained) {
/* 576 */             this.xformChangeList.add(arrayOfObject1[b1]);
/* 577 */             ConeSoundRetained coneSoundRetained = (ConeSoundRetained)arrayOfObject1[b1];
/*     */             
/* 579 */             coneSoundRetained.updateTransformChange();
/*     */           }
/* 581 */           else if (arrayOfObject1[b1] instanceof PointSoundRetained) {
/* 582 */             this.xformChangeList.add(arrayOfObject1[b1]);
/* 583 */             PointSoundRetained pointSoundRetained = (PointSoundRetained)arrayOfObject1[b1];
/*     */             
/* 585 */             pointSoundRetained.updateTransformChange();
/*     */           }
/* 587 */           else if (arrayOfObject1[b1] instanceof SoundRetained) {
/* 588 */             this.xformChangeList.add(arrayOfObject1[b1]);
/* 589 */             SoundRetained soundRetained = (SoundRetained)arrayOfObject1[b1];
/* 590 */             soundRetained.updateTransformChange();
/*     */           }
/* 592 */           else if (arrayOfObject1[b1] instanceof SoundscapeRetained) {
/* 593 */             this.xformChangeList.add(arrayOfObject1[b1]);
/* 594 */             SoundscapeRetained soundscapeRetained = (SoundscapeRetained)arrayOfObject1[b1];
/*     */             
/* 596 */             soundscapeRetained.updateTransformChange();
/*     */           }
/* 598 */           else if (arrayOfObject1[b1] instanceof AuralAttributesRetained) {
/* 599 */             this.xformChangeList.add(arrayOfObject1[b1]);
/*     */           } 
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
/*     */   void debugPrint(String paramString) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isSoundScopedToView(Object paramObject, View paramView) {
/* 618 */     SoundRetained soundRetained = (SoundRetained)paramObject;
/* 619 */     if (soundRetained.isViewScoped) {
/* 620 */       ArrayList arrayList = (ArrayList)this.viewScopedSounds.get(paramView);
/* 621 */       if (!arrayList.contains(soundRetained))
/* 622 */         return false; 
/*     */     } 
/* 624 */     return true;
/*     */   }
/*     */   
/*     */   boolean isSoundscapeScopedToView(Object paramObject, View paramView) {
/* 628 */     SoundscapeRetained soundscapeRetained = (SoundscapeRetained)paramObject;
/* 629 */     if (soundscapeRetained.isViewScoped) {
/* 630 */       ArrayList arrayList = (ArrayList)this.viewScopedSoundscapes.get(paramView);
/* 631 */       if (!arrayList.contains(soundscapeRetained))
/* 632 */         return false; 
/*     */     } 
/* 634 */     return true;
/*     */   }
/*     */   
/*     */   void updateViewSpecificGroupChanged(J3dMessage paramJ3dMessage) {
/* 638 */     int i = ((Integer)paramJ3dMessage.args[0]).intValue();
/* 639 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[1];
/*     */     
/* 641 */     ArrayList arrayList1 = null;
/* 642 */     ArrayList arrayList2 = null;
/*     */     
/* 644 */     if ((i & 0x2) != 0 || (i & true) != 0) {
/*     */ 
/*     */ 
/*     */       
/* 648 */       View view = (View)arrayOfObject[0];
/* 649 */       ArrayList arrayList = (ArrayList)arrayOfObject[2];
/* 650 */       int j = arrayList.size();
/*     */       
/* 652 */       if (j > 0)
/*     */       {
/* 654 */         for (byte b = 0; b < j; b++) {
/* 655 */           Object object = arrayList.get(b);
/* 656 */           if (object instanceof SoundRetained) {
/* 657 */             if (arrayList1 == null && (
/* 658 */               arrayList1 = (ArrayList)this.viewScopedSounds.get(view)) == null) {
/* 659 */               arrayList1 = new ArrayList();
/* 660 */               this.viewScopedSounds.put(view, arrayList1);
/*     */             } 
/*     */             
/* 663 */             arrayList1.add(object);
/*     */           }
/* 665 */           else if (object instanceof SoundscapeRetained) {
/* 666 */             if (arrayList2 == null && (
/* 667 */               arrayList2 = (ArrayList)this.viewScopedSoundscapes.get(view)) == null) {
/* 668 */               arrayList2 = new ArrayList();
/* 669 */               this.viewScopedSoundscapes.put(view, arrayList2);
/*     */             } 
/*     */             
/* 672 */             arrayList2.add(object);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 677 */     if ((i & 0x4) != 0 || (i & true) != 0) {
/*     */       View view;
/*     */ 
/*     */ 
/*     */       
/*     */       ArrayList arrayList;
/*     */ 
/*     */       
/* 685 */       if ((i & 0x4) != 0) {
/* 686 */         view = (View)arrayOfObject[0];
/* 687 */         arrayList = (ArrayList)arrayOfObject[2];
/*     */       } else {
/*     */         
/* 690 */         view = (View)arrayOfObject[4];
/* 691 */         arrayList = (ArrayList)arrayOfObject[6];
/*     */       } 
/* 693 */       int j = arrayList.size();
/*     */       
/* 695 */       if (j > 0) {
/*     */         
/* 697 */         for (byte b = 0; b < j; b++) {
/* 698 */           Object object = arrayList.get(b);
/* 699 */           if (object instanceof SoundRetained) {
/* 700 */             if (arrayList1 == null) {
/* 701 */               arrayList1 = (ArrayList)this.viewScopedSounds.get(view);
/*     */             }
/* 703 */             arrayList1.remove(object);
/*     */           } 
/* 705 */           if (object instanceof SoundscapeRetained) {
/* 706 */             if (arrayList2 == null) {
/* 707 */               arrayList2 = (ArrayList)this.viewScopedSoundscapes.get(view);
/*     */             }
/* 709 */             arrayList2.remove(object);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 714 */         if (arrayList1 != null && arrayList1.size() == 0)
/* 715 */           this.viewScopedSounds.remove(view); 
/* 716 */         if (arrayList2 != null && arrayList2.size() == 0)
/* 717 */           this.viewScopedSoundscapes.remove(view); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void cleanup() {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SoundStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */