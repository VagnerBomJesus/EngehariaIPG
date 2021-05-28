/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.AWTEvent;
/*      */ import java.io.InputStream;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Enumeration;
/*      */ import javax.vecmath.Point2f;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Vector3d;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class SoundScheduler
/*      */   extends J3dStructure
/*      */ {
/*   36 */   View view = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean ready = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   47 */   ViewPlatformRetained viewPlatform = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   52 */   GraphicsContext3D graphicsCtx = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   59 */   AuralAttributesRetained lastAA = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean resetAA = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   73 */   AudioDevice audioDevice = null;
/*   74 */   AudioDevice3D audioDevice3D = null;
/*   75 */   AudioDevice3DL2 audioDevice3DL2 = null;
/*   76 */   int totalChannels = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   84 */   SoundscapeRetained[] intersectedSoundscapes = new SoundscapeRetained[32];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   92 */   Bounds[] intersectedRegions = new Bounds[32];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   98 */   Bounds region = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   ArrayList prioritizedSounds = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   int nRetainedSounds = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   int nImmedSounds = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   AuralAttributesRetained aaRetained = null;
/*      */   
/*      */   boolean transformMsg = false;
/*      */   
/*  126 */   UpdateTargets targets = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  132 */   AuralAttributesRetained aaImmed = null;
/*      */   
/*      */   static final int EAR_POSITIONS_CHANGED = 1;
/*      */   
/*      */   static final int EYE_POSITIONS_CHANGED = 2;
/*      */   
/*      */   static final int IMAGE_PLATE_TO_VWORLD_CHANGED = 4;
/*      */   
/*      */   static final int HEAD_TO_VWORLD_CHANGED = 8;
/*      */   
/*      */   static final int LISTENER_CHANGED = 15;
/*  143 */   private int listenerUpdated = 15;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean positionalSoundUpdated = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean auralAttribsChanged = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean stallThread = false;
/*      */ 
/*      */   
/*  158 */   int lastEventReceived = 202;
/*      */   
/*      */   static final boolean debugFlag = false;
/*      */   static final boolean internalErrors = false;
/*      */   
/*      */   SoundScheduler(VirtualUniverse paramVirtualUniverse, View paramView) {
/*  164 */     super(paramVirtualUniverse, 2);
/*      */ 
/*      */     
/*  167 */     if (paramView == null) {
/*  168 */       System.err.println("WARNING: SoundScheduler constructed with null view");
/*      */     }
/*  170 */     if (paramVirtualUniverse == null) {
/*  171 */       System.err.println("WARNING: SoundScheduler constructed with null universe");
/*      */     }
/*      */     
/*  174 */     this.universe = paramVirtualUniverse;
/*  175 */     this.view = paramView;
/*  176 */     reset();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void processMessages(long paramLong) {
/*  182 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/*  183 */     int i = getNumMessage();
/*      */ 
/*      */ 
/*      */     
/*  187 */     if (i > 0) {
/*  188 */       for (byte b = 0; b < i; b++) {
/*  189 */         SoundscapeRetained soundscapeRetained; J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/*      */         
/*  191 */         switch (j3dMessage.type) {
/*      */           case 0:
/*  193 */             insertNodes(j3dMessage);
/*      */             break;
/*      */           case 1:
/*  196 */             removeNodes(j3dMessage);
/*      */             break;
/*      */           case 38:
/*  199 */             changeNodeAttrib(j3dMessage);
/*      */             break;
/*      */           case 45:
/*  202 */             changeNodeState(j3dMessage);
/*      */             break;
/*      */           case 23:
/*  205 */             processBoundingLeafChanged(j3dMessage);
/*      */             break;
/*      */           case 40:
/*  208 */             soundscapeRetained = (SoundscapeRetained)j3dMessage.args[0];
/*  209 */             if (this.universe.soundStructure.isSoundscapeScopedToView(soundscapeRetained, this.view)) {
/*  210 */               this.auralAttribsChanged = true;
/*  211 */               changeNodeAttrib(j3dMessage);
/*      */             } 
/*      */             break;
/*      */           case 39:
/*  215 */             this.auralAttribsChanged = true;
/*  216 */             changeNodeAttrib(j3dMessage);
/*      */             break;
/*      */           case 51:
/*  219 */             changeNodeAttrib(j3dMessage);
/*      */             break;
/*      */           case 3:
/*  222 */             this.transformMsg = true;
/*  223 */             this.auralAttribsChanged = true;
/*      */             break;
/*      */           case 44:
/*  226 */             processImmediateNodes(j3dMessage.args, paramLong);
/*      */             break;
/*      */           case 56:
/*  229 */             processViewSpecificGroupChanged(j3dMessage);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 4:
/*  242 */             if (this.prioritizedSounds.isEmpty()) {
/*  243 */               int j = prioritizeSounds();
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  252 */         j3dMessage.decRefcount();
/*      */       } 
/*  254 */       if (this.transformMsg) {
/*  255 */         this.targets = this.universe.transformStructure.getTargetList();
/*  256 */         updateTransformChange(this.targets, paramLong);
/*  257 */         this.transformMsg = false;
/*  258 */         this.targets = null;
/*      */       } 
/*  260 */       Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  266 */       renderChanges();
/*      */     }
/*  268 */     catch (RuntimeException runtimeException) {
/*  269 */       System.err.println("Exception occurred during Sound rendering:");
/*      */       
/*  271 */       runtimeException.printStackTrace();
/*      */     }
/*  273 */     catch (Error error) {
/*      */       
/*  275 */       System.err.println("Error occurred during Sound rendering:");
/*      */       
/*  277 */       error.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  286 */     long l = shortestTimeToFinish();
/*      */     
/*  288 */     if (l == 0L) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  293 */       VirtualUniverse.mc.sendRunMessage(this.universe, 2);
/*      */     
/*      */     }
/*  296 */     else if (l > 0L) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  306 */       VirtualUniverse.mc.sendRunMessage(l, this.view, 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void insertNodes(J3dMessage paramJ3dMessage) {
/*  312 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/*  313 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/*  314 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*      */     
/*      */     int i;
/*  317 */     for (i = 0; i < arrayOfObject.length; i++) {
/*  318 */       Object object = arrayOfObject[i];
/*  319 */       if (object instanceof SoundRetained) {
/*  320 */         this.nRetainedSounds++;
/*      */         
/*  322 */         addSound((SoundRetained)object);
/*      */       }
/*  324 */       else if (object instanceof SoundscapeRetained) {
/*  325 */         this.auralAttribsChanged = true;
/*      */       }
/*  327 */       else if (object instanceof AuralAttributesRetained) {
/*  328 */         this.auralAttribsChanged = true;
/*      */       }
/*  330 */       else if (object instanceof ViewPlatformRetained) {
/*      */       
/*      */       } 
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
/*  343 */     if (arrayList1 != null) {
/*  344 */       i = arrayList1.size();
/*      */       
/*  346 */       for (byte b = 0; b < i; b++) {
/*  347 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b);
/*  348 */         ArrayList arrayList = (ArrayList)arrayList2.get(b);
/*      */         
/*  350 */         if (arrayList.contains(this.view)) {
/*  351 */           if (nodeRetained instanceof SoundRetained) {
/*  352 */             this.nRetainedSounds++;
/*      */             
/*  354 */             addSound((SoundRetained)nodeRetained);
/*      */           }
/*  356 */           else if (nodeRetained instanceof SoundscapeRetained) {
/*  357 */             this.auralAttribsChanged = true;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addSound(SoundRetained paramSoundRetained) {
/*  368 */     if (paramSoundRetained == null) {
/*      */       return;
/*      */     }
/*      */     
/*  372 */     synchronized (this.prioritizedSounds) {
/*  373 */       addPrioritizedSound(paramSoundRetained);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeNodes(J3dMessage paramJ3dMessage) {
/*  382 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/*  383 */     ArrayList arrayList1 = (ArrayList)paramJ3dMessage.args[3];
/*  384 */     ArrayList arrayList2 = (ArrayList)paramJ3dMessage.args[4];
/*      */     
/*      */     int i;
/*  387 */     for (i = 0; i < arrayOfObject.length; i++) {
/*  388 */       Object object = arrayOfObject[i];
/*  389 */       if (object instanceof SoundRetained) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  406 */         SoundSchedulerAtom soundSchedulerAtom = null;
/*  407 */         for (byte b = 1;; b++) {
/*  408 */           soundSchedulerAtom = findSoundAtom((SoundRetained)object, b);
/*      */           
/*  410 */           if (soundSchedulerAtom == null)
/*      */             break; 
/*  412 */           stopSound(soundSchedulerAtom, false);
/*      */         }
/*      */       
/*  415 */       } else if (object instanceof SoundscapeRetained) {
/*  416 */         this.auralAttribsChanged = true;
/*      */       } 
/*      */     } 
/*      */     
/*  420 */     if (arrayList1 != null) {
/*  421 */       i = arrayList1.size();
/*      */       
/*  423 */       for (byte b = 0; b < i; b++) {
/*  424 */         NodeRetained nodeRetained = (NodeRetained)arrayList1.get(b);
/*  425 */         ArrayList arrayList = (ArrayList)arrayList2.get(b);
/*      */         
/*  427 */         if (arrayList.contains(this.view)) {
/*  428 */           if (nodeRetained instanceof SoundRetained) {
/*  429 */             SoundSchedulerAtom soundSchedulerAtom = null;
/*  430 */             for (byte b1 = 1;; b1++) {
/*  431 */               soundSchedulerAtom = findSoundAtom((SoundRetained)nodeRetained, b1);
/*      */               
/*  433 */               if (soundSchedulerAtom == null)
/*      */                 break; 
/*  435 */               stopSound(soundSchedulerAtom, false);
/*      */             }
/*      */           
/*  438 */           } else if (nodeRetained instanceof SoundscapeRetained) {
/*  439 */             this.auralAttribsChanged = true;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void deleteSound(SoundRetained paramSoundRetained) {
/*  450 */     if (paramSoundRetained != null) {
/*      */       return;
/*      */     }
/*      */     
/*  454 */     synchronized (this.prioritizedSounds) {
/*  455 */       if (!this.prioritizedSounds.isEmpty()) {
/*      */         
/*  457 */         int i = this.prioritizedSounds.size();
/*  458 */         for (byte b = 0; b < i; b++) {
/*  459 */           SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b);
/*      */ 
/*      */           
/*  462 */           if (soundSchedulerAtom.sound == paramSoundRetained || soundSchedulerAtom.sound.sgSound == paramSoundRetained) {
/*      */             
/*  464 */             stopSound(soundSchedulerAtom, false);
/*  465 */             this.prioritizedSounds.remove(b);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void changeNodeAttrib(J3dMessage paramJ3dMessage) {
/*  474 */     Object object1 = paramJ3dMessage.args[0];
/*  475 */     Object object2 = paramJ3dMessage.args[1];
/*  476 */     int i = ((Integer)object2).intValue();
/*      */ 
/*      */ 
/*      */     
/*  480 */     if (object1 instanceof SoundRetained && this.universe.soundStructure.isSoundScopedToView(object1, this.view)) {
/*      */ 
/*      */       
/*  483 */       setAttribsDirtyFlag((SoundRetained)object1, i);
/*      */ 
/*      */       
/*  486 */       if ((i & 0x20) > 0) {
/*  487 */         shuffleSound((SoundRetained)object1);
/*      */       }
/*  489 */       if ((i & true) > 0)
/*      */       {
/*      */ 
/*      */         
/*  493 */         loadSound((SoundRetained)object1, true);
/*      */       }
/*  495 */       if ((i & 0x40) > 0)
/*      */       {
/*      */         
/*  498 */         muteSound((SoundRetained)object1);
/*      */       }
/*  500 */       if ((i & 0x80) > 0)
/*      */       {
/*      */         
/*  503 */         pauseSound((SoundRetained)object1);
/*      */       }
/*      */     }
/*  506 */     else if (object1 instanceof SoundscapeRetained && this.universe.soundStructure.isSoundscapeScopedToView(object1, this.view)) {
/*      */       
/*  508 */       this.auralAttribsChanged = true;
/*      */     }
/*  510 */     else if (object1 instanceof AuralAttributesRetained) {
/*  511 */       this.auralAttribsChanged = true;
/*      */     }
/*  513 */     else if (object1 instanceof MediaContainerRetained) {
/*  514 */       int j = ((Integer)paramJ3dMessage.args[2]).intValue();
/*  515 */       ArrayList arrayList = (ArrayList)paramJ3dMessage.args[3];
/*  516 */       for (byte b = 0; b < j; b++) {
/*  517 */         SoundRetained soundRetained = (SoundRetained)arrayList.get(b);
/*  518 */         if (soundRetained != null) {
/*  519 */           loadSound(soundRetained, true);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void changeNodeState(J3dMessage paramJ3dMessage) {
/*  530 */     Object object1 = paramJ3dMessage.args[0];
/*  531 */     Object object2 = paramJ3dMessage.args[1];
/*      */ 
/*      */     
/*  534 */     if (object1 instanceof SoundRetained && this.universe.soundStructure.isSoundScopedToView(object1, this.view)) {
/*  535 */       int i = ((Integer)object2).intValue();
/*  536 */       setStateDirtyFlag((SoundRetained)object1, i);
/*      */ 
/*      */       
/*  539 */       if ((i & true) > 0)
/*      */       {
/*      */ 
/*      */         
/*  543 */         loadSound((SoundRetained)object1, false);
/*      */       }
/*  545 */       if ((i & 0x20) > 0)
/*      */       {
/*      */         
/*  548 */         if (((Boolean)paramJ3dMessage.args[4]).booleanValue()) {
/*  549 */           enableSound((SoundRetained)object1);
/*      */         } else {
/*      */           
/*  552 */           SoundRetained soundRetained = (SoundRetained)object1;
/*  553 */           for (int j = this.prioritizedSounds.size() - 1; j >= 0; j--) {
/*  554 */             SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(j);
/*  555 */             if (soundSchedulerAtom.sound.sgSound == soundRetained) {
/*      */ 
/*      */               
/*  558 */               turnOff(soundSchedulerAtom);
/*      */               
/*  560 */               soundSchedulerAtom.enable(soundRetained.enable);
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
/*      */   void shuffleSound(SoundRetained paramSoundRetained) {
/*  574 */     deleteSound(paramSoundRetained);
/*  575 */     addSound(paramSoundRetained);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void loadSound(SoundRetained paramSoundRetained, boolean paramBoolean) {
/*  582 */     SoundSchedulerAtom soundSchedulerAtom = null;
/*  583 */     for (byte b = 1;; b++) {
/*  584 */       soundSchedulerAtom = findSoundAtom(paramSoundRetained, b);
/*  585 */       if (soundSchedulerAtom == null)
/*      */         break; 
/*  587 */       MediaContainer mediaContainer = paramSoundRetained.getSoundData();
/*  588 */       if (paramBoolean || soundSchedulerAtom.loadStatus != 2)
/*      */       {
/*      */ 
/*      */         
/*  592 */         attachSoundData(soundSchedulerAtom, mediaContainer, paramBoolean);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void enableSound(SoundRetained paramSoundRetained) {
/*  602 */     SoundSchedulerAtom soundSchedulerAtom = null;
/*  603 */     for (byte b = 1;; b++) {
/*  604 */       soundSchedulerAtom = findSoundAtom(paramSoundRetained, b);
/*  605 */       if (soundSchedulerAtom == null) {
/*      */         break;
/*      */       }
/*      */       
/*  609 */       soundSchedulerAtom.enable(paramSoundRetained.enable);
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
/*      */   void muteSound(SoundRetained paramSoundRetained) {
/*  621 */     SoundSchedulerAtom soundSchedulerAtom = null;
/*  622 */     for (byte b = 1;; b++) {
/*  623 */       soundSchedulerAtom = findSoundAtom(paramSoundRetained, b);
/*  624 */       if (soundSchedulerAtom == null) {
/*      */         break;
/*      */       }
/*      */       
/*  628 */       soundSchedulerAtom.mute(paramSoundRetained.mute);
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
/*      */   void pauseSound(SoundRetained paramSoundRetained) {
/*  644 */     SoundSchedulerAtom soundSchedulerAtom = null;
/*  645 */     for (byte b = 1;; b++) {
/*  646 */       soundSchedulerAtom = findSoundAtom(paramSoundRetained, b);
/*  647 */       if (soundSchedulerAtom == null) {
/*      */         break;
/*      */       }
/*      */       
/*  651 */       soundSchedulerAtom.pause(paramSoundRetained.pause);
/*      */     } 
/*      */   }
/*      */   
/*      */   void processImmediateNodes(Object[] paramArrayOfObject, long paramLong) {
/*  656 */     Object object1 = paramArrayOfObject[0];
/*  657 */     Object object2 = paramArrayOfObject[1];
/*  658 */     Object object3 = paramArrayOfObject[2];
/*  659 */     Sound sound1 = (Sound)object3;
/*  660 */     Sound sound2 = (Sound)object2;
/*  661 */     int i = ((Integer)object1).intValue();
/*      */ 
/*      */ 
/*      */     
/*  665 */     switch (i) {
/*      */       case 15:
/*      */       case 17:
/*  668 */         addSound((SoundRetained)sound2.retained);
/*  669 */         this.nImmedSounds++;
/*      */         break;
/*      */       case 16:
/*  672 */         deleteSound((SoundRetained)sound1.retained);
/*  673 */         this.nImmedSounds--;
/*      */         break;
/*      */       case 14:
/*  676 */         deleteSound((SoundRetained)sound1.retained);
/*  677 */         addSound((SoundRetained)sound2.retained);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTransformChange(UpdateTargets paramUpdateTargets, long paramLong) {
/*  688 */     UnorderList unorderList = paramUpdateTargets.targetList[3];
/*  689 */     if (unorderList != null) {
/*      */ 
/*      */       
/*  692 */       int i = unorderList.size();
/*  693 */       Object[] arrayOfObject = unorderList.toArray(false);
/*      */       
/*  695 */       for (byte b = 0; b < i; b++) {
/*  696 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/*      */ 
/*      */         
/*  699 */         for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/*  700 */           if (arrayOfObject1[b1] instanceof ConeSoundRetained && this.universe.soundStructure.isSoundScopedToView(arrayOfObject1[b1], this.view)) {
/*  701 */             ConeSoundRetained coneSoundRetained = (ConeSoundRetained)arrayOfObject1[b1];
/*      */             
/*  703 */             synchronized (coneSoundRetained) {
/*  704 */               coneSoundRetained.updateTransformChange();
/*      */             } 
/*      */             
/*  707 */             setStateDirtyFlag((SoundRetained)arrayOfObject1[b1], 32768);
/*      */           }
/*  709 */           else if (arrayOfObject1[b1] instanceof PointSoundRetained && this.universe.soundStructure.isSoundScopedToView(arrayOfObject1[b1], this.view)) {
/*  710 */             PointSoundRetained pointSoundRetained = (PointSoundRetained)arrayOfObject1[b1];
/*      */             
/*  712 */             synchronized (pointSoundRetained) {
/*  713 */               pointSoundRetained.updateTransformChange();
/*      */             } 
/*      */             
/*  716 */             setStateDirtyFlag((SoundRetained)arrayOfObject1[b1], 32768);
/*      */           }
/*  718 */           else if (arrayOfObject1[b1] instanceof SoundscapeRetained && this.universe.soundStructure.isSoundscapeScopedToView(arrayOfObject1[b1], this.view)) {
/*  719 */             SoundscapeRetained soundscapeRetained = (SoundscapeRetained)arrayOfObject1[b1];
/*      */             
/*  721 */             synchronized (soundscapeRetained) {
/*  722 */               soundscapeRetained.updateTransformChange();
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void updateTransformedFields(SoundRetained paramSoundRetained) {
/*  732 */     if (paramSoundRetained instanceof ConeSoundRetained && this.universe.soundStructure.isSoundScopedToView(paramSoundRetained, this.view)) {
/*  733 */       ConeSoundRetained coneSoundRetained = (ConeSoundRetained)paramSoundRetained;
/*  734 */       synchronized (coneSoundRetained) {
/*  735 */         coneSoundRetained.updateTransformChange();
/*      */       }
/*      */     
/*  738 */     } else if (paramSoundRetained instanceof PointSoundRetained && this.universe.soundStructure.isSoundScopedToView(paramSoundRetained, this.view)) {
/*  739 */       PointSoundRetained pointSoundRetained = (PointSoundRetained)paramSoundRetained;
/*  740 */       synchronized (pointSoundRetained) {
/*  741 */         pointSoundRetained.updateTransformChange();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void activate() {
/*  748 */     this.updateThread.active = true;
/*      */ 
/*      */     
/*  751 */     VirtualUniverse.mc.sendRunMessage(this.universe, 2);
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
/*      */   void deactivate() {
/*  784 */     if (checkState()) {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  793 */         deactivateAllSounds();
/*      */       }
/*  795 */       catch (RuntimeException runtimeException) {
/*  796 */         System.err.println("Exception occurred during sound deactivation:");
/*      */         
/*  798 */         runtimeException.printStackTrace();
/*      */       }
/*  800 */       catch (Error error) {
/*      */         
/*  802 */         System.err.println("Error occurred during sound deactivation:");
/*      */         
/*  804 */         error.printStackTrace();
/*      */       } 
/*  806 */       this.updateThread.active = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean checkState() {
/*  813 */     boolean bool = false;
/*  814 */     if (this.stallThread)
/*      */     {
/*      */       
/*  817 */       bool = false;
/*      */     }
/*      */     
/*  820 */     if (this.ready) {
/*      */ 
/*      */       
/*  823 */       bool = true;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  828 */       reset();
/*  829 */       if (this.ready) {
/*      */ 
/*      */         
/*  832 */         bool = true;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  838 */         bool = false;
/*      */       } 
/*      */     } 
/*  841 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void reset() {
/*  847 */     if (this.universe == null || this.view == null || this.view.physicalEnvironment == null || this.view.physicalEnvironment.audioDevice == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  852 */       this.audioDevice = null;
/*  853 */       this.ready = false;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  858 */     this.audioDevice = this.view.physicalEnvironment.audioDevice;
/*      */ 
/*      */     
/*  861 */     ViewPlatform viewPlatform1 = this.view.getViewPlatform();
/*  862 */     if (viewPlatform1 == null || viewPlatform1.retained == null) {
/*      */       
/*  864 */       this.viewPlatform = null;
/*  865 */       this.ready = false;
/*      */       
/*      */       return;
/*      */     } 
/*  869 */     this.viewPlatform = (ViewPlatformRetained)viewPlatform1.retained;
/*  870 */     if (!viewPlatform1.isLive()) {
/*  871 */       this.ready = false;
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  880 */     Canvas3D canvas3D = this.view.getFirstCanvas();
/*  881 */     if (canvas3D != null) {
/*  882 */       this.graphicsCtx = canvas3D.getGraphicsContext3D();
/*      */     }
/*      */ 
/*      */     
/*  886 */     this.audioDevice3DL2 = null;
/*  887 */     this.audioDevice3D = null;
/*  888 */     if (this.audioDevice instanceof AudioDevice3DL2) {
/*  889 */       this.audioDevice3DL2 = (AudioDevice3DL2)this.audioDevice;
/*      */     }
/*  891 */     if (this.audioDevice instanceof AudioDevice3D) {
/*  892 */       this.audioDevice3D = (AudioDevice3D)this.audioDevice;
/*      */ 
/*      */       
/*  895 */       this.audioDevice3D.setView(this.view);
/*  896 */       this.totalChannels = this.audioDevice.getTotalChannels();
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  904 */       this.totalChannels = 0;
/*      */     } 
/*      */     
/*  907 */     if (this.totalChannels == 0) {
/*  908 */       this.ready = false;
/*      */       
/*      */       return;
/*      */     } 
/*  912 */     this.ready = true;
/*      */ 
/*      */     
/*  915 */     this.view.setUserHeadToVworldEnable(true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void receiveAWTEvent(AWTEvent paramAWTEvent) {
/*  921 */     int i = paramAWTEvent.getID();
/*      */ 
/*      */     
/*  924 */     if (this.ready && i == 203) {
/*  925 */       this.lastEventReceived = i;
/*      */     }
/*  927 */     else if (this.ready && this.lastEventReceived == 203 && i == 204) {
/*      */ 
/*      */       
/*  930 */       this.lastEventReceived = i;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderChanges() {
/*  940 */     boolean bool1 = false;
/*  941 */     boolean bool2 = false;
/*  942 */     int i = 0;
/*  943 */     int j = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  950 */     if (!checkState()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  959 */     i = this.prioritizedSounds.size();
/*      */ 
/*      */     
/*  962 */     if (i == 0) {
/*      */       return;
/*      */     }
/*  965 */     if (this.auralAttribsChanged) {
/*      */       
/*  967 */       int k = findActiveSoundscapes();
/*  968 */       if (k > 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  974 */         this.aaRetained = (AuralAttributesRetained)findClosestAAttribs(k).clone();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  981 */     if (i > 0) {
/*  982 */       calcSchedulingAction();
/*  983 */       muteSilentSounds();
/*      */ 
/*      */       
/*  986 */       this.positionalSoundUpdated = false;
/*      */ 
/*      */       
/*  989 */       if (testListenerFlag())
/*      */       {
/*      */         
/*  992 */         this.audioDevice3D.setView(this.view);
/*      */       }
/*      */       
/*  995 */       j = performActions();
/*      */       
/*  997 */       if (this.positionalSoundUpdated)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1002 */         clearListenerFlag();
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
/*      */   int prioritizeSounds() {
/*      */     int i;
/* 1020 */     synchronized (this.prioritizedSounds) {
/* 1021 */       if (!this.prioritizedSounds.isEmpty()) {
/* 1022 */         this.prioritizedSounds.clear();
/*      */       }
/*      */       
/* 1025 */       UnorderList unorderList = this.universe.soundStructure.getSoundList(this.view);
/*      */ 
/*      */       
/* 1028 */       this.nRetainedSounds = 0;
/* 1029 */       this.nImmedSounds = 0;
/*      */ 
/*      */ 
/*      */       
/* 1033 */       for (byte b = 0; b < unorderList.size(); b++) {
/* 1034 */         addPrioritizedSound((SoundRetained)unorderList.get(b));
/* 1035 */         this.nRetainedSounds++;
/*      */       } 
/*      */       
/* 1038 */       Enumeration enumeration = this.view.getAllCanvas3Ds();
/* 1039 */       while (enumeration.hasMoreElements()) {
/* 1040 */         Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/* 1041 */         GraphicsContext3D graphicsContext3D = canvas3D.getGraphicsContext3D();
/* 1042 */         Enumeration enumeration1 = graphicsContext3D.getAllSounds();
/* 1043 */         while (enumeration1.hasMoreElements()) {
/*      */ 
/*      */           
/* 1046 */           Sound sound = (Sound)enumeration1.nextElement();
/* 1047 */           if (sound == null) {
/*      */             continue;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1053 */           addPrioritizedSound((SoundRetained)sound.retained);
/* 1054 */           this.nImmedSounds++;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1060 */       debugPrint(" prioritizeSound , num of processed non-retained sounds" + this.nImmedSounds);
/*      */       
/* 1062 */       i = this.prioritizedSounds.size();
/*      */     } 
/* 1064 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void addPrioritizedSound(SoundRetained paramSoundRetained) {
/* 1070 */     SoundRetained soundRetained = paramSoundRetained.sgSound;
/* 1071 */     if (soundRetained == null) {
/*      */       
/* 1073 */       paramSoundRetained.sgSound = paramSoundRetained;
/* 1074 */       soundRetained = paramSoundRetained;
/*      */     } 
/*      */ 
/*      */     
/* 1078 */     boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1083 */     SoundSchedulerAtom soundSchedulerAtom = null;
/* 1084 */     soundSchedulerAtom = findSoundAtom(paramSoundRetained, 1);
/* 1085 */     if (soundSchedulerAtom == null) {
/* 1086 */       soundSchedulerAtom = new SoundSchedulerAtom();
/* 1087 */       soundSchedulerAtom.soundScheduler = this;
/* 1088 */       bool = true;
/*      */     } 
/*      */ 
/*      */     
/* 1092 */     soundSchedulerAtom.sound = paramSoundRetained;
/* 1093 */     updateTransformedFields(paramSoundRetained);
/*      */     
/* 1095 */     if (!bool) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1100 */     soundSchedulerAtom.enable(soundRetained.enable);
/*      */     
/* 1102 */     if (this.prioritizedSounds.isEmpty()) {
/*      */ 
/*      */       
/* 1105 */       this.prioritizedSounds.add(soundSchedulerAtom);
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
/* 1120 */       int j = this.prioritizedSounds.size() - 1;
/* 1121 */       float f = soundRetained.priority; int i;
/* 1122 */       for (i = j; i >= 0; i--) {
/* 1123 */         SoundSchedulerAtom soundSchedulerAtom1 = (SoundSchedulerAtom)this.prioritizedSounds.get(i);
/* 1124 */         SoundRetained soundRetained1 = soundSchedulerAtom1.sound;
/*      */ 
/*      */ 
/*      */         
/* 1128 */         if (f <= soundRetained1.sgSound.priority) {
/* 1129 */           if (i == j) {
/*      */ 
/*      */ 
/*      */             
/* 1133 */             this.prioritizedSounds.add(soundSchedulerAtom);
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1143 */           this.prioritizedSounds.add(i + 1, soundSchedulerAtom);
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1148 */       if (i < 0)
/*      */       {
/*      */         
/* 1151 */         this.prioritizedSounds.add(0, soundSchedulerAtom);
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
/*      */   int findActiveSoundscapes() {
/* 1165 */     boolean bool1 = false;
/* 1166 */     byte b = 0;
/* 1167 */     Object object = null;
/* 1168 */     SoundscapeRetained soundscapeRetained = null;
/* 1169 */     boolean bool2 = false;
/* 1170 */     int i = 0;
/* 1171 */     UnorderList unorderList = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1176 */     if (this.universe == null)
/*      */     {
/*      */       
/* 1179 */       return 0;
/*      */     }
/* 1181 */     unorderList = this.universe.soundStructure.getSoundscapeList(this.view);
/* 1182 */     if (unorderList == null)
/*      */     {
/*      */       
/* 1185 */       return 0;
/*      */     }
/*      */     
/* 1188 */     synchronized (unorderList) {
/* 1189 */       i = unorderList.size;
/* 1190 */       if (i == 0)
/*      */       {
/*      */ 
/*      */         
/* 1194 */         return 0;
/*      */       }
/*      */ 
/*      */       
/* 1198 */       if (this.intersectedRegions.length < bool1) {
/* 1199 */         this.intersectedRegions = new Bounds[bool1 + 32];
/*      */       }
/* 1201 */       if (this.intersectedSoundscapes.length < bool1) {
/* 1202 */         this.intersectedSoundscapes = new SoundscapeRetained[bool1 + 32];
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1209 */       b = 0;
/* 1210 */       for (byte b1 = 0; b1 < i; b1++) {
/* 1211 */         soundscapeRetained = (SoundscapeRetained)unorderList.get(b1);
/*      */ 
/*      */         
/* 1214 */         if (soundscapeRetained.transformedRegion != null) {
/*      */ 
/*      */           
/* 1217 */           if ((this.region instanceof BoundingSphere && soundscapeRetained.transformedRegion instanceof BoundingSphere) || (this.region instanceof BoundingBox && soundscapeRetained.transformedRegion instanceof BoundingBox) || (this.region instanceof BoundingPolytope && soundscapeRetained.transformedRegion instanceof BoundingPolytope)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1223 */             soundscapeRetained.transformedRegion.getWithLock(this.region);
/*      */           }
/*      */           else {
/*      */             
/* 1227 */             this.region = (Bounds)soundscapeRetained.transformedRegion.clone();
/*      */           } 
/*      */ 
/*      */           
/* 1231 */           if (this.region != null && this.viewPlatform.schedSphere.intersect(this.region)) {
/* 1232 */             this.intersectedRegions[b] = (Bounds)this.region.clone();
/*      */             
/* 1234 */             this.intersectedSoundscapes[b] = soundscapeRetained;
/*      */ 
/*      */ 
/*      */             
/* 1238 */             b++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1250 */     return b;
/*      */   }
/*      */ 
/*      */   
/*      */   AuralAttributesRetained findClosestAAttribs(int paramInt) {
/* 1255 */     AuralAttributes auralAttributes = null;
/* 1256 */     SoundscapeRetained soundscapeRetained = null;
/* 1257 */     boolean bool = false;
/*      */ 
/*      */     
/* 1260 */     soundscapeRetained = null;
/* 1261 */     if (paramInt == 1) {
/* 1262 */       soundscapeRetained = this.intersectedSoundscapes[0];
/* 1263 */     } else if (paramInt > 1) {
/*      */       
/* 1265 */       Bounds bounds = this.viewPlatform.schedSphere.closestIntersection(this.intersectedRegions);
/*      */       
/* 1267 */       for (byte b = 0; b < this.intersectedRegions.length; b++) {
/*      */ 
/*      */ 
/*      */         
/* 1271 */         if (this.intersectedRegions[b] == bounds) {
/* 1272 */           soundscapeRetained = this.intersectedSoundscapes[b];
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1280 */     if (soundscapeRetained != null) {
/*      */ 
/*      */       
/* 1283 */       auralAttributes = soundscapeRetained.getAuralAttributes();
/* 1284 */       if (auralAttributes != null);
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
/* 1303 */     return (AuralAttributesRetained)auralAttributes.retained;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateAuralAttribs(AuralAttributesRetained paramAuralAttributesRetained) {
/* 1313 */     if (this.auralAttribsChanged) {
/* 1314 */       if (paramAuralAttributesRetained != null) {
/* 1315 */         synchronized (paramAuralAttributesRetained) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1326 */           this.audioDevice3D.setRolloff(paramAuralAttributesRetained.rolloff);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1331 */           int i = paramAuralAttributesRetained.getDistanceFilterLength();
/* 1332 */           if (paramAuralAttributesRetained.filterType == -1 || i == 0) {
/*      */ 
/*      */             
/* 1335 */             paramAuralAttributesRetained; this.audioDevice3D.setDistanceFilter(-1, null, null);
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 1341 */             Point2f[] arrayOfPoint2f = new Point2f[i];
/* 1342 */             for (byte b1 = 0; b1 < i; b1++)
/* 1343 */               arrayOfPoint2f[b1] = new Point2f(); 
/* 1344 */             paramAuralAttributesRetained.getDistanceFilter(arrayOfPoint2f);
/* 1345 */             double[] arrayOfDouble = new double[i];
/* 1346 */             float[] arrayOfFloat = new float[i];
/* 1347 */             for (byte b2 = 0; b2 < i; b2++) {
/* 1348 */               arrayOfDouble[b2] = (arrayOfPoint2f[b2]).x;
/* 1349 */               arrayOfFloat[b2] = (arrayOfPoint2f[b2]).y;
/*      */             } 
/* 1351 */             this.audioDevice3D.setDistanceFilter(paramAuralAttributesRetained.filterType, arrayOfDouble, arrayOfFloat);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1361 */           this.audioDevice3D.setFrequencyScaleFactor(paramAuralAttributesRetained.frequencyScaleFactor);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1366 */           this.audioDevice3D.setVelocityScaleFactor(paramAuralAttributesRetained.velocityScaleFactor);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1371 */           this.audioDevice3D.setReflectionCoefficient(paramAuralAttributesRetained.reflectionCoefficient);
/*      */           
/* 1373 */           if (this.audioDevice3DL2 != null) {
/* 1374 */             this.audioDevice3DL2.setReverbCoefficient(paramAuralAttributesRetained.reverbCoefficient);
/*      */             
/* 1376 */             this.audioDevice3DL2.setDecayFilter(paramAuralAttributesRetained.decayFilter);
/* 1377 */             this.audioDevice3DL2.setDiffusion(paramAuralAttributesRetained.diffusion);
/* 1378 */             this.audioDevice3DL2.setDensity(paramAuralAttributesRetained.density);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1401 */           Bounds bounds = paramAuralAttributesRetained.reverbBounds;
/* 1402 */           if (bounds == null) {
/* 1403 */             this.audioDevice3D.setReverbDelay(paramAuralAttributesRetained.reverbDelay);
/*      */           } else {
/*      */             float f;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1410 */             if (bounds instanceof BoundingSphere) {
/*      */ 
/*      */               
/* 1413 */               f = (float)(2.0D * ((BoundingSphere)bounds).radius / 0.3440000116825104D);
/*      */ 
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */               
/* 1420 */               BoundingSphere boundingSphere = new BoundingSphere(bounds);
/*      */               
/* 1422 */               f = (float)(2.0D * boundingSphere.radius / 0.3440000116825104D);
/*      */             } 
/*      */ 
/*      */             
/* 1426 */             this.audioDevice3D.setReverbDelay(f);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1435 */           this.audioDevice3D.setReverbOrder(paramAuralAttributesRetained.reverbOrder);
/* 1436 */           if (this.audioDevice3DL2 != null)
/* 1437 */             this.audioDevice3DL2.setDecayTime(paramAuralAttributesRetained.decayTime); 
/*      */         } 
/* 1439 */         this.resetAA = true;
/*      */       
/*      */       }
/* 1442 */       else if (this.lastAA != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1451 */         this.audioDevice3D.setRolloff(1.0F);
/* 1452 */         this.audioDevice3D.setReflectionCoefficient(0.0F);
/* 1453 */         this.audioDevice3D.setReverbDelay(40.0F);
/* 1454 */         this.audioDevice3D.setReverbOrder(0);
/*      */         
/* 1456 */         this.audioDevice3D.setDistanceFilter(-1, null, null);
/*      */         
/* 1458 */         this.audioDevice3D.setFrequencyScaleFactor(1.0F);
/* 1459 */         this.audioDevice3D.setVelocityScaleFactor(0.0F);
/* 1460 */         if (this.audioDevice3DL2 != null) {
/* 1461 */           this.audioDevice3DL2.setReverbCoefficient(0.0F);
/* 1462 */           this.audioDevice3DL2.setReflectionDelay(20.0F);
/* 1463 */           this.audioDevice3DL2.setDecayTime(1000.0F);
/* 1464 */           this.audioDevice3DL2.setDecayFilter(5000.0F);
/* 1465 */           this.audioDevice3DL2.setDiffusion(1.0F);
/* 1466 */           this.audioDevice3DL2.setDensity(1.0F);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1471 */         this.resetAA = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1480 */       this.lastAA = paramAuralAttributesRetained;
/* 1481 */       this.auralAttribsChanged = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   void processSoundAtom(SoundSchedulerAtom paramSoundSchedulerAtom) {
/*      */     long l;
/* 1487 */     SoundRetained soundRetained1 = paramSoundSchedulerAtom.sound;
/* 1488 */     SoundRetained soundRetained2 = soundRetained1.sgSound;
/*      */ 
/*      */     
/* 1491 */     if (paramSoundSchedulerAtom.status == 4 && paramSoundSchedulerAtom.enabled != 2) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1502 */     if (paramSoundSchedulerAtom.loadStatus != 2)
/*      */     {
/*      */ 
/*      */       
/* 1506 */       attachSoundData(paramSoundSchedulerAtom, soundRetained2.soundData, false);
/*      */     }
/*      */ 
/*      */     
/* 1510 */     if (paramSoundSchedulerAtom.loadStatus != 2) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1517 */     if (this.resetAA) {
/* 1518 */       paramSoundSchedulerAtom.setAttribsDirtyFlag(2);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1525 */     boolean bool = false;
/* 1526 */     if (!this.updateThread.active) {
/* 1527 */       this.region = null;
/* 1528 */       bool = false;
/*      */ 
/*      */     
/*      */     }
/* 1532 */     else if (soundRetained2.getInImmCtx()) {
/* 1533 */       this.region = null;
/* 1534 */       bool = true;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1539 */       if (soundRetained2.schedulingRegion != null && soundRetained1.transformedRegion != null) {
/*      */ 
/*      */ 
/*      */         
/* 1543 */         if ((this.region instanceof BoundingSphere && soundRetained1.transformedRegion instanceof BoundingSphere) || (this.region instanceof BoundingBox && soundRetained1.transformedRegion instanceof BoundingBox) || (this.region instanceof BoundingPolytope && soundRetained1.transformedRegion instanceof BoundingPolytope)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1549 */           soundRetained1.transformedRegion.getWithLock(this.region);
/*      */         } else {
/* 1551 */           this.region = (Bounds)soundRetained1.transformedRegion.clone();
/*      */         } 
/*      */       } else {
/* 1554 */         this.region = null;
/*      */       } 
/*      */       
/* 1557 */       if (this.region != null) {
/*      */ 
/*      */         
/* 1560 */         bool = this.viewPlatform.schedSphere.intersect(this.region);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1566 */         bool = false;
/*      */       } 
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
/* 1584 */     paramSoundSchedulerAtom; paramSoundSchedulerAtom; if ((soundRetained2.getInImmCtx() || (bool && soundRetained1.switchState != null && soundRetained1.switchState.currentSwitchOn)) && (paramSoundSchedulerAtom.muted == 0 || paramSoundSchedulerAtom.muted == 2)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1592 */       paramSoundSchedulerAtom.schedulingAction = paramSoundSchedulerAtom.calcActiveSchedAction();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1597 */       paramSoundSchedulerAtom.schedulingAction = paramSoundSchedulerAtom.calcInactiveSchedAction();
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
/* 1612 */     if (paramSoundSchedulerAtom.schedulingAction == 3 && (paramSoundSchedulerAtom.testDirtyFlags() || (testListenerFlag() && !(soundRetained2 instanceof BackgroundSoundRetained))))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1623 */       paramSoundSchedulerAtom.schedulingAction = 18;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1630 */     switch (paramSoundSchedulerAtom.schedulingAction) {
/*      */       case 17:
/* 1632 */         paramSoundSchedulerAtom.status = 0;
/* 1633 */         turnOff(paramSoundSchedulerAtom);
/*      */ 
/*      */ 
/*      */         
/* 1637 */         paramSoundSchedulerAtom.schedulingAction = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*      */       case 14:
/*      */       case 15:
/*      */       case 16:
/*      */       case 18:
/* 1655 */         l = J3dClock.currentTimeMillis();
/* 1656 */         if (paramSoundSchedulerAtom.endTime > 0L && paramSoundSchedulerAtom.endTime <= l) {
/*      */           
/* 1658 */           paramSoundSchedulerAtom.schedulingAction = 19;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1664 */           paramSoundSchedulerAtom.status = 4;
/* 1665 */           turnOff(paramSoundSchedulerAtom);
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1679 */     paramSoundSchedulerAtom.schedulingAction = 0;
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
/*      */   int calcSchedulingAction() {
/* 1698 */     int i = 0;
/*      */ 
/*      */     
/* 1701 */     byte b = 0;
/*      */     
/* 1703 */     if (this.universe == null)
/*      */     {
/*      */ 
/*      */       
/* 1707 */       return 0;
/*      */     }
/* 1709 */     if (this.universe.soundStructure == null)
/*      */     {
/*      */ 
/*      */       
/* 1713 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1719 */     synchronized (this.prioritizedSounds) {
/* 1720 */       i = this.prioritizedSounds.size();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1735 */       for (byte b1 = 0; b1 < i; b1++) {
/* 1736 */         SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b1);
/* 1737 */         SoundRetained soundRetained2 = soundSchedulerAtom.sound;
/* 1738 */         SoundRetained soundRetained1 = soundRetained2.sgSound;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1746 */         boolean bool = false;
/* 1747 */         if (!soundRetained1.source.isLive() && !soundRetained1.getInImmCtx()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1758 */           if (soundSchedulerAtom.playing || soundSchedulerAtom.enabled == 1) {
/*      */             
/* 1760 */             soundSchedulerAtom.setEnableState(3);
/* 1761 */             bool = true;
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 1766 */           else if (soundSchedulerAtom.enabled == 3) {
/* 1767 */             bool = true;
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 1772 */           else if (soundSchedulerAtom.enabled == 2) {
/* 1773 */             soundSchedulerAtom.setEnableState(0);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1781 */           bool = true;
/*      */         } 
/*      */         
/* 1784 */         if (bool) {
/* 1785 */           b++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1794 */           processSoundAtom(soundSchedulerAtom);
/*      */         } else {
/*      */           
/* 1797 */           soundSchedulerAtom.schedulingAction = 0;
/*      */         } 
/*      */       } 
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
/* 1810 */     return b;
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
/*      */   void muteSilentSounds() {
/* 1848 */     int i = 0;
/*      */ 
/*      */     
/* 1851 */     synchronized (this.prioritizedSounds) {
/* 1852 */       int j = this.prioritizedSounds.size();
/*      */ 
/*      */ 
/*      */       
/* 1856 */       for (byte b = 0; b < j; b++) {
/* 1857 */         SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b);
/* 1858 */         SoundRetained soundRetained2 = soundSchedulerAtom.sound;
/* 1859 */         SoundRetained soundRetained1 = soundRetained2.sgSound;
/* 1860 */         int k = soundSchedulerAtom.sampleId;
/* 1861 */         int m = soundSchedulerAtom.status;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1869 */         if (soundSchedulerAtom.status != 4)
/*      */         {
/*      */           
/* 1872 */           if (soundSchedulerAtom.schedulingAction != 0)
/*      */           {
/*      */             
/* 1875 */             if (k != -1)
/*      */             {
/*      */ 
/*      */               
/* 1879 */               if (soundSchedulerAtom.schedulingAction == 12 || soundSchedulerAtom.schedulingAction == 7 || soundSchedulerAtom.schedulingAction == 2 || soundSchedulerAtom.schedulingAction == 8) {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1884 */                 if (m != 2)
/*      */                 {
/* 1886 */                   this.audioDevice3D.muteSample(k);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1893 */                 int n = this.audioDevice3D.getNumberOfChannelsUsed(k);
/*      */                 
/* 1895 */                 soundSchedulerAtom.numberChannels = n;
/* 1896 */                 i += n;
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 1901 */                 int n = this.audioDevice3D.getNumberOfChannelsUsed(k, false);
/*      */ 
/*      */                 
/* 1904 */                 if (i + n > this.totalChannels) {
/* 1905 */                   if (soundSchedulerAtom.schedulingAction == 11 || soundSchedulerAtom.schedulingAction == 3) {
/*      */                     
/* 1907 */                     soundSchedulerAtom.schedulingAction = 12;
/*      */                   }
/* 1909 */                   else if (soundSchedulerAtom.schedulingAction == 5) {
/* 1910 */                     soundSchedulerAtom.schedulingAction = 7;
/* 1911 */                   } else if (soundSchedulerAtom.schedulingAction == 6) {
/* 1912 */                     soundSchedulerAtom.schedulingAction = 8;
/* 1913 */                   } else if (soundSchedulerAtom.schedulingAction == 13) {
/* 1914 */                     soundSchedulerAtom.schedulingAction = 14;
/* 1915 */                   } else if (soundSchedulerAtom.schedulingAction == 15) {
/* 1916 */                     soundSchedulerAtom.schedulingAction = 16;
/* 1917 */                   }  this.audioDevice3D.muteSample(k);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 }
/* 1930 */                 else if (m != 1) {
/*      */                   
/* 1932 */                   this.audioDevice3D.unmuteSample(k);
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1940 */                 n = this.audioDevice3D.getNumberOfChannelsUsed(k);
/*      */                 
/* 1942 */                 soundSchedulerAtom.numberChannels = n;
/* 1943 */                 i += n;
/*      */               } 
/*      */             }
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void muteSilentSound(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 1955 */     SoundRetained soundRetained2 = paramSoundSchedulerAtom.sound;
/* 1956 */     SoundRetained soundRetained1 = soundRetained2.sgSound;
/* 1957 */     int i = paramSoundSchedulerAtom.sampleId;
/* 1958 */     int j = paramSoundSchedulerAtom.status;
/*      */     
/* 1960 */     if (j == 4) {
/*      */       return;
/*      */     }
/* 1963 */     if (i == -1) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1972 */     if (paramSoundSchedulerAtom.schedulingAction == 12 || paramSoundSchedulerAtom.schedulingAction == 7 || paramSoundSchedulerAtom.schedulingAction == 2 || paramSoundSchedulerAtom.schedulingAction == 8)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1977 */       if (j != 2)
/*      */       {
/* 1979 */         this.audioDevice3D.muteSample(i);
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
/*      */   long shortestTimeToFinish() {
/* 1998 */     long l1 = J3dClock.currentTimeMillis();
/* 1999 */     long l2 = -1L;
/*      */     
/* 2001 */     synchronized (this.prioritizedSounds) {
/* 2002 */       int i = this.prioritizedSounds.size();
/* 2003 */       for (byte b = 0; b < i; b++) {
/* 2004 */         SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b);
/* 2005 */         if (soundSchedulerAtom.status != 0 && soundSchedulerAtom.status != 4) {
/*      */ 
/*      */           
/* 2008 */           long l = soundSchedulerAtom.endTime;
/* 2009 */           if (l >= 0L) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2019 */             long l3 = l - l1;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2024 */             if (l3 < 0L)
/*      */             {
/*      */               
/* 2027 */               l3 = 0L;
/*      */             }
/* 2029 */             if (l2 < 0L) {
/*      */               
/* 2031 */               l2 = l3;
/*      */             }
/* 2033 */             else if (l3 < l2) {
/* 2034 */               l2 = l3;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2040 */     return l2;
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
/*      */   int performActions() {
/* 2061 */     byte b = 0;
/*      */ 
/*      */     
/* 2064 */     synchronized (this.prioritizedSounds) {
/* 2065 */       int i = this.prioritizedSounds.size();
/* 2066 */       for (byte b1 = 0; b1 < i; b1++) {
/*      */ 
/*      */         
/* 2069 */         SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b1);
/* 2070 */         SoundRetained soundRetained2 = soundSchedulerAtom.sound;
/* 2071 */         SoundRetained soundRetained1 = soundRetained2.sgSound;
/* 2072 */         int j = soundSchedulerAtom.sampleId;
/*      */         
/* 2074 */         if (j != -1) {
/*      */           AuralAttributesRetained auralAttributesRetained;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2082 */           this.resetAA = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2088 */           if (soundRetained1.getInImmCtx()) {
/* 2089 */             if (this.graphicsCtx != null && this.graphicsCtx.auralAttributes != null) {
/* 2090 */               this.aaImmed = (AuralAttributesRetained)this.graphicsCtx.auralAttributes.retained;
/*      */               
/* 2092 */               auralAttributesRetained = this.aaImmed;
/*      */             } else {
/*      */               
/* 2095 */               auralAttributesRetained = null;
/*      */             } 
/*      */           } else {
/*      */             
/* 2099 */             auralAttributesRetained = this.aaRetained;
/*      */           } 
/* 2101 */           updateAuralAttribs(auralAttributesRetained);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2109 */           switch (soundSchedulerAtom.schedulingAction) {
/*      */             
/*      */             case 5:
/* 2112 */               turnOff(soundSchedulerAtom);
/*      */ 
/*      */             
/*      */             case 6:
/* 2116 */               soundSchedulerAtom; if (soundSchedulerAtom.paused == 3)
/* 2117 */                 pause(soundSchedulerAtom); 
/* 2118 */               soundSchedulerAtom; if (soundSchedulerAtom.paused == 2)
/* 2119 */                 unpause(soundSchedulerAtom); 
/* 2120 */               soundSchedulerAtom; if (soundSchedulerAtom.paused == 0) {
/*      */                 
/* 2122 */                 soundSchedulerAtom.status = 1;
/* 2123 */                 render(true, soundSchedulerAtom, auralAttributesRetained);
/*      */               } else {
/*      */                 
/* 2126 */                 soundSchedulerAtom.status = 3;
/*      */                 
/* 2128 */                 soundSchedulerAtom.setEnableState(2);
/*      */               } 
/* 2130 */               b++;
/*      */               break;
/*      */ 
/*      */             
/*      */             case 7:
/* 2135 */               turnOff(soundSchedulerAtom);
/*      */ 
/*      */             
/*      */             case 8:
/* 2139 */               soundSchedulerAtom; if (soundSchedulerAtom.paused == 3)
/* 2140 */                 pause(soundSchedulerAtom); 
/* 2141 */               soundSchedulerAtom; if (soundSchedulerAtom.paused == 2)
/* 2142 */                 unpause(soundSchedulerAtom); 
/* 2143 */               soundSchedulerAtom; if (soundSchedulerAtom.paused == 0) {
/*      */                 
/* 2145 */                 soundSchedulerAtom.status = 2;
/* 2146 */                 render(true, soundSchedulerAtom, auralAttributesRetained);
/*      */               } else {
/*      */                 
/* 2149 */                 soundSchedulerAtom.status = 3;
/*      */                 
/* 2151 */                 soundSchedulerAtom.setEnableState(2);
/*      */               } 
/* 2153 */               b++;
/*      */               break;
/*      */ 
/*      */             
/*      */             case 15:
/* 2158 */               unpause(soundSchedulerAtom);
/*      */             
/*      */             case 11:
/* 2161 */               soundSchedulerAtom.status = 1;
/* 2162 */               render(false, soundSchedulerAtom, auralAttributesRetained);
/* 2163 */               b++;
/*      */               break;
/*      */             
/*      */             case 16:
/* 2167 */               unpause(soundSchedulerAtom);
/*      */ 
/*      */ 
/*      */             
/*      */             case 12:
/* 2172 */               render(false, soundSchedulerAtom, auralAttributesRetained);
/* 2173 */               soundSchedulerAtom.status = 2;
/* 2174 */               b++;
/*      */               break;
/*      */             
/*      */             case 13:
/*      */             case 14:
/* 2179 */               pause(soundSchedulerAtom);
/* 2180 */               soundSchedulerAtom.status = 3;
/* 2181 */               b++;
/*      */               break;
/*      */             
/*      */             case 18:
/* 2185 */               render(false, soundSchedulerAtom, auralAttributesRetained);
/* 2186 */               b++;
/*      */               break;
/*      */             
/*      */             case 2:
/*      */             case 3:
/*      */             case 4:
/* 2192 */               if (this.resetAA || soundSchedulerAtom.testDirtyFlags()) {
/* 2193 */                 render(false, soundSchedulerAtom, auralAttributesRetained);
/*      */               }
/*      */ 
/*      */               
/* 2197 */               b++;
/*      */               break;
/*      */             
/*      */             case 17:
/* 2201 */               turnOff(soundSchedulerAtom);
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2216 */           soundSchedulerAtom.clearStateDirtyFlag();
/* 2217 */           soundSchedulerAtom.clearAttribsDirtyFlag();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2224 */     this.resetAA = false;
/*      */     
/* 2226 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void render(boolean paramBoolean, SoundSchedulerAtom paramSoundSchedulerAtom, AuralAttributesRetained paramAuralAttributesRetained) {
/* 2237 */     SoundRetained soundRetained1 = paramSoundSchedulerAtom.sound;
/* 2238 */     SoundRetained soundRetained2 = soundRetained1.sgSound;
/*      */ 
/*      */ 
/*      */     
/* 2242 */     if (paramSoundSchedulerAtom.sampleId == -1 || paramSoundSchedulerAtom.soundData == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2250 */     int i = paramSoundSchedulerAtom.sampleId;
/*      */ 
/*      */     
/* 2253 */     if (paramBoolean) {
/* 2254 */       if (soundRetained2 instanceof PointSoundRetained || soundRetained2 instanceof ConeSoundRetained)
/*      */       {
/* 2256 */         updateXformedParams(true, paramSoundSchedulerAtom);
/*      */       }
/* 2258 */       updateSoundParams(true, paramSoundSchedulerAtom, paramAuralAttributesRetained);
/* 2259 */       start(paramSoundSchedulerAtom);
/*      */     
/*      */     }
/* 2262 */     else if (paramSoundSchedulerAtom.status == 1) {
/* 2263 */       if (soundRetained2 instanceof PointSoundRetained || soundRetained2 instanceof ConeSoundRetained)
/*      */       {
/* 2265 */         updateXformedParams(false, paramSoundSchedulerAtom);
/*      */       }
/* 2267 */       updateSoundParams(false, paramSoundSchedulerAtom, paramAuralAttributesRetained);
/* 2268 */       update(paramSoundSchedulerAtom);
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
/*      */   void start(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 2281 */     SoundRetained soundRetained = paramSoundSchedulerAtom.sound.sgSound;
/* 2282 */     int i = paramSoundSchedulerAtom.sampleId;
/* 2283 */     int j = -1;
/* 2284 */     if (i != -1 && (j = this.audioDevice3D.startSample(i)) >= 0) {
/*      */ 
/*      */ 
/*      */       
/* 2288 */       paramSoundSchedulerAtom.playing = true;
/* 2289 */       paramSoundSchedulerAtom.startTime = this.audioDevice3D.getStartTime(i);
/* 2290 */       paramSoundSchedulerAtom.calculateEndTime();
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2296 */       paramSoundSchedulerAtom.startTime = 0L;
/* 2297 */       paramSoundSchedulerAtom.endTime = 0L;
/* 2298 */       paramSoundSchedulerAtom.playing = false;
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
/*      */   void update(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 2314 */     int i = paramSoundSchedulerAtom.sampleId;
/*      */     
/* 2316 */     if (i == -1) {
/*      */       return;
/*      */     }
/* 2319 */     SoundRetained soundRetained = paramSoundSchedulerAtom.sound;
/* 2320 */     this.audioDevice3D.updateSample(i);
/*      */ 
/*      */ 
/*      */     
/* 2324 */     paramSoundSchedulerAtom.calculateEndTime();
/* 2325 */     if (soundRetained instanceof PointSoundRetained || soundRetained instanceof ConeSoundRetained)
/*      */     {
/* 2327 */       this.positionalSoundUpdated = true;
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
/*      */   void stopSound(SoundSchedulerAtom paramSoundSchedulerAtom, boolean paramBoolean) {
/* 2339 */     if (this.audioDevice3D == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2345 */     switch (paramSoundSchedulerAtom.enabled) {
/*      */       case 1:
/* 2347 */         if (paramBoolean) {
/* 2348 */           paramSoundSchedulerAtom.setEnableState(2); break;
/*      */         } 
/* 2350 */         paramSoundSchedulerAtom.setEnableState(0);
/*      */         break;
/*      */       case 3:
/* 2353 */         paramSoundSchedulerAtom.setEnableState(0);
/*      */         break;
/*      */       case 2:
/* 2356 */         if (!paramBoolean)
/*      */         {
/* 2358 */           paramSoundSchedulerAtom.setEnableState(0);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 2363 */     paramSoundSchedulerAtom.status = 0;
/* 2364 */     turnOff(paramSoundSchedulerAtom);
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
/*      */   void deactivateAllSounds() {
/* 2377 */     if (this.audioDevice3D == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2384 */     synchronized (this.prioritizedSounds) {
/* 2385 */       if (this.prioritizedSounds != null) {
/* 2386 */         int i = this.prioritizedSounds.size();
/*      */ 
/*      */         
/* 2389 */         for (byte b = 0; b < i; b++) {
/*      */ 
/*      */           
/* 2392 */           SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b);
/* 2393 */           SoundRetained soundRetained2 = soundSchedulerAtom.sound;
/* 2394 */           SoundRetained soundRetained1 = soundRetained2.sgSound;
/* 2395 */           if (soundRetained1.continuous) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2401 */             soundSchedulerAtom.schedulingAction = soundSchedulerAtom.calcInactiveSchedAction();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2408 */             muteSilentSound(soundSchedulerAtom);
/*      */           }
/*      */           else {
/*      */             
/* 2412 */             stopSound(soundSchedulerAtom, true);
/* 2413 */             soundSchedulerAtom; soundSchedulerAtom.schedulingAction = 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2422 */     performActions();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void pauseAllSounds() {
/* 2433 */     if (this.audioDevice3D == null) {
/*      */       return;
/*      */     }
/* 2436 */     this.stallThread = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2441 */     synchronized (this.prioritizedSounds) {
/* 2442 */       if (this.prioritizedSounds != null) {
/* 2443 */         int i = this.prioritizedSounds.size();
/*      */ 
/*      */         
/* 2446 */         for (byte b = 0; b < i; b++) {
/*      */ 
/*      */           
/* 2449 */           SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b);
/*      */           
/* 2451 */           SoundRetained soundRetained2 = soundSchedulerAtom.sound;
/* 2452 */           SoundRetained soundRetained1 = soundRetained2.sgSound;
/*      */           
/* 2454 */           switch (soundSchedulerAtom.enabled) {
/*      */             case 1:
/*      */             case 3:
/* 2457 */               pause(soundSchedulerAtom);
/*      */               break;
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
/*      */   void resumeAllSounds() {
/* 2478 */     if (this.audioDevice3D == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2485 */     synchronized (this.prioritizedSounds) {
/* 2486 */       if (this.prioritizedSounds != null) {
/* 2487 */         int i = this.prioritizedSounds.size();
/*      */ 
/*      */ 
/*      */         
/* 2491 */         for (byte b = 0; b < i; b++) {
/*      */ 
/*      */           
/* 2494 */           SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b);
/*      */           
/* 2496 */           SoundRetained soundRetained2 = soundSchedulerAtom.sound;
/* 2497 */           SoundRetained soundRetained1 = soundRetained2.sgSound;
/*      */           
/* 2499 */           switch (soundSchedulerAtom.enabled) {
/*      */             case 1:
/*      */             case 3:
/* 2502 */               unpause(soundSchedulerAtom);
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/*      */       } 
/*      */     } 
/* 2512 */     this.stallThread = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2519 */   void stopAllSounds() { stopAllSounds(false); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void stopAllSounds(boolean paramBoolean) {
/* 2530 */     if (this.audioDevice3D == null) {
/*      */       return;
/*      */     }
/* 2533 */     if (this.lastEventReceived == 203) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 2538 */     synchronized (this.prioritizedSounds) {
/* 2539 */       if (this.prioritizedSounds != null) {
/* 2540 */         int i = this.prioritizedSounds.size();
/*      */ 
/*      */ 
/*      */         
/* 2544 */         for (byte b = 0; b < i; b++) {
/*      */ 
/*      */           
/* 2547 */           SoundSchedulerAtom soundSchedulerAtom = (SoundSchedulerAtom)this.prioritizedSounds.get(b);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2552 */           stopSound(soundSchedulerAtom, paramBoolean);
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
/*      */   
/*      */   void pause(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 2573 */     if (paramSoundSchedulerAtom.sampleId == -1) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 2578 */     this.audioDevice3D.pauseSample(paramSoundSchedulerAtom.sampleId);
/* 2579 */     paramSoundSchedulerAtom; paramSoundSchedulerAtom.setPauseState(1);
/*      */   }
/*      */   
/*      */   void unpause(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 2583 */     if (paramSoundSchedulerAtom.sampleId == -1) {
/*      */       return;
/*      */     }
/*      */     
/* 2587 */     this.audioDevice3D.unpauseSample(paramSoundSchedulerAtom.sampleId);
/* 2588 */     paramSoundSchedulerAtom; paramSoundSchedulerAtom.setPauseState(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void turnOff(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 2596 */     if (paramSoundSchedulerAtom.sampleId == -1) {
/*      */       return;
/*      */     }
/*      */     
/* 2600 */     if (this.audioDevice3D.stopSample(paramSoundSchedulerAtom.sampleId) < 0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2605 */     paramSoundSchedulerAtom.playing = false;
/* 2606 */     paramSoundSchedulerAtom.startTime = 0L;
/* 2607 */     paramSoundSchedulerAtom.endTime = 0L;
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
/*      */   void updateXformedParams(boolean paramBoolean, SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 2625 */     PointSoundRetained pointSoundRetained1 = (PointSoundRetained)paramSoundSchedulerAtom.sound;
/* 2626 */     PointSoundRetained pointSoundRetained2 = (PointSoundRetained)pointSoundRetained1.sgSound;
/* 2627 */     int i = paramSoundSchedulerAtom.sampleId;
/* 2628 */     if (i == -1)
/*      */       return; 
/* 2630 */     PointSoundRetained pointSoundRetained3 = pointSoundRetained1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2676 */     if (paramBoolean || testListenerFlag() || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.attribsDirty, 64) || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.stateDirty, 32768)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2682 */       Point3f point3f = new Point3f();
/* 2683 */       pointSoundRetained1.getXformPosition(point3f);
/* 2684 */       Point3d point3d = new Point3d(point3f);
/*      */ 
/*      */ 
/*      */       
/* 2688 */       this.audioDevice3D.setPosition(i, point3d);
/*      */     } 
/*      */ 
/*      */     
/* 2692 */     if (pointSoundRetained1 instanceof ConeSoundRetained) {
/* 2693 */       ConeSoundRetained coneSoundRetained1 = (ConeSoundRetained)pointSoundRetained1;
/* 2694 */       ConeSoundRetained coneSoundRetained2 = (ConeSoundRetained)pointSoundRetained1.sgSound;
/* 2695 */       if (paramBoolean || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.attribsDirty, 33280)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2701 */         Vector3f vector3f = new Vector3f();
/* 2702 */         coneSoundRetained1.getXformDirection(vector3f);
/* 2703 */         Vector3d vector3d = new Vector3d(vector3f);
/* 2704 */         this.audioDevice3D.setDirection(i, vector3d);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateSoundParams(boolean paramBoolean, SoundSchedulerAtom paramSoundSchedulerAtom, AuralAttributesRetained paramAuralAttributesRetained) {
/* 2713 */     SoundRetained soundRetained1 = paramSoundSchedulerAtom.sound;
/* 2714 */     SoundRetained soundRetained2 = soundRetained1.sgSound;
/* 2715 */     int i = paramSoundSchedulerAtom.sampleId;
/*      */ 
/*      */     
/* 2718 */     if (i == -1) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2726 */     if (paramBoolean || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.attribsDirty, 2))
/*      */     {
/*      */       
/* 2729 */       if (paramAuralAttributesRetained != null) {
/* 2730 */         this.audioDevice3D.setSampleGain(i, soundRetained2.initialGain * paramAuralAttributesRetained.attributeGain);
/*      */       }
/*      */       else {
/*      */         
/* 2734 */         this.audioDevice3D.setSampleGain(i, soundRetained2.initialGain);
/*      */       } 
/*      */     }
/*      */     
/* 2738 */     if (paramBoolean || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.attribsDirty, 4))
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 2743 */       this.audioDevice3D.setLoop(i, soundRetained2.loopCount);
/*      */     }
/*      */     
/* 2746 */     if (paramBoolean || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.attribsDirty, 2048))
/*      */     {
/* 2748 */       if (this.audioDevice3DL2 != null)
/*      */       {
/*      */ 
/*      */         
/* 2752 */         this.audioDevice3DL2.setRateScaleFactor(i, soundRetained2.rate);
/*      */       }
/*      */     }
/*      */     
/* 2756 */     if (paramBoolean || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.attribsDirty, 128))
/*      */     {
/* 2758 */       if (soundRetained2 instanceof ConeSoundRetained) {
/* 2759 */         ConeSoundRetained coneSoundRetained = (ConeSoundRetained)soundRetained2;
/*      */ 
/*      */         
/* 2762 */         int j = coneSoundRetained.getDistanceGainLength();
/* 2763 */         if (j == 0) {
/*      */           
/* 2765 */           this.audioDevice3D.setDistanceGain(i, null, null, null, null);
/*      */         } else {
/*      */           
/* 2768 */           Point2f[] arrayOfPoint2f1 = new Point2f[j];
/* 2769 */           Point2f[] arrayOfPoint2f2 = new Point2f[j];
/* 2770 */           for (byte b1 = 0; b1 < j; b1++) {
/* 2771 */             arrayOfPoint2f1[b1] = new Point2f();
/* 2772 */             arrayOfPoint2f2[b1] = new Point2f();
/*      */           } 
/* 2774 */           coneSoundRetained.getDistanceGain(arrayOfPoint2f1, arrayOfPoint2f2);
/* 2775 */           double[] arrayOfDouble1 = new double[j];
/* 2776 */           float[] arrayOfFloat1 = new float[j];
/* 2777 */           double[] arrayOfDouble2 = new double[j];
/* 2778 */           float[] arrayOfFloat2 = new float[j];
/* 2779 */           for (byte b2 = 0; b2 < j; b2++) {
/* 2780 */             arrayOfDouble1[b2] = (arrayOfPoint2f1[b2]).x;
/* 2781 */             arrayOfFloat1[b2] = (arrayOfPoint2f1[b2]).y;
/* 2782 */             arrayOfDouble2[b2] = (arrayOfPoint2f2[b2]).x;
/* 2783 */             arrayOfFloat2[b2] = (arrayOfPoint2f2[b2]).y;
/*      */           } 
/* 2785 */           this.audioDevice3D.setDistanceGain(i, arrayOfDouble1, arrayOfFloat1, arrayOfDouble2, arrayOfFloat2);
/*      */         }
/*      */       
/*      */       }
/* 2789 */       else if (soundRetained2 instanceof PointSoundRetained) {
/* 2790 */         PointSoundRetained pointSoundRetained = (PointSoundRetained)soundRetained2;
/*      */ 
/*      */         
/* 2793 */         int j = pointSoundRetained.getDistanceGainLength();
/* 2794 */         if (j == 0) {
/*      */           
/* 2796 */           this.audioDevice3D.setDistanceGain(i, null, null, null, null);
/*      */         } else {
/*      */           
/* 2799 */           Point2f[] arrayOfPoint2f = new Point2f[j];
/* 2800 */           for (byte b1 = 0; b1 < j; b1++)
/* 2801 */             arrayOfPoint2f[b1] = new Point2f(); 
/* 2802 */           pointSoundRetained.getDistanceGain(arrayOfPoint2f);
/* 2803 */           double[] arrayOfDouble = new double[j];
/* 2804 */           float[] arrayOfFloat = new float[j];
/* 2805 */           for (byte b2 = 0; b2 < j; b2++) {
/* 2806 */             arrayOfDouble[b2] = (arrayOfPoint2f[b2]).x;
/* 2807 */             arrayOfFloat[b2] = (arrayOfPoint2f[b2]).y;
/*      */           } 
/* 2809 */           this.audioDevice3D.setDistanceGain(i, arrayOfDouble, arrayOfFloat, null, null);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2815 */     if (soundRetained2 instanceof ConeSoundRetained && (paramBoolean || paramSoundSchedulerAtom.testDirtyFlag(paramSoundSchedulerAtom.attribsDirty, 1024))) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2820 */       ConeSoundRetained coneSoundRetained = (ConeSoundRetained)soundRetained2;
/* 2821 */       int j = coneSoundRetained.getAngularAttenuationLength();
/* 2822 */       if (j == 0) {
/*      */         
/* 2824 */         double[] arrayOfDouble = new double[2];
/* 2825 */         float[] arrayOfFloat = new float[2];
/* 2826 */         arrayOfDouble[0] = 0.0D;
/* 2827 */         arrayOfDouble[1] = 1.5707963267948966D;
/* 2828 */         arrayOfFloat[0] = 1.0F;
/* 2829 */         arrayOfFloat[1] = 0.0F;
/* 2830 */         coneSoundRetained; this.audioDevice3D.setAngularAttenuation(i, -1, arrayOfDouble, arrayOfFloat, null);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2835 */         Point3f[] arrayOfPoint3f = new Point3f[j];
/* 2836 */         for (byte b1 = 0; b1 < j; b1++) {
/* 2837 */           arrayOfPoint3f[b1] = new Point3f();
/*      */         }
/* 2839 */         coneSoundRetained.getAngularAttenuation(arrayOfPoint3f);
/* 2840 */         double[] arrayOfDouble = new double[j];
/* 2841 */         float[] arrayOfFloat1 = new float[j];
/* 2842 */         float[] arrayOfFloat2 = new float[j];
/* 2843 */         for (byte b2 = 0; b2 < j; b2++) {
/* 2844 */           arrayOfDouble[b2] = (arrayOfPoint3f[b2]).x;
/* 2845 */           arrayOfFloat1[b2] = (arrayOfPoint3f[b2]).y;
/* 2846 */           arrayOfFloat2[b2] = (arrayOfPoint3f[b2]).z;
/*      */         } 
/* 2848 */         this.audioDevice3D.setAngularAttenuation(i, coneSoundRetained.filterType, arrayOfDouble, arrayOfFloat1, arrayOfFloat2);
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
/*      */   boolean checkAudioDevice3D() {
/* 2860 */     if (this.universe != null && 
/* 2861 */       this.universe.currentView != null && 
/* 2862 */       this.universe.currentView.physicalEnvironment != null) {
/* 2863 */       this.audioDevice = this.universe.currentView.physicalEnvironment.audioDevice;
/* 2864 */       if (this.audioDevice != null) {
/* 2865 */         if (this.audioDevice instanceof AudioDevice3DL2) {
/* 2866 */           this.audioDevice3DL2 = (AudioDevice3DL2)this.audioDevice;
/*      */         }
/* 2868 */         if (this.audioDevice instanceof AudioDevice3D) {
/* 2869 */           this.audioDevice3D = (AudioDevice3D)this.audioDevice;
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 2879 */         this.audioDevice3DL2 = null;
/* 2880 */         this.audioDevice3D = null;
/*      */       } 
/*      */     } 
/*      */     
/* 2884 */     if (this.audioDevice3D == null) {
/* 2885 */       return false;
/*      */     }
/* 2887 */     if (this.audioDevice3D.getTotalChannels() == 0) {
/* 2888 */       return false;
/*      */     }
/* 2890 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearSoundData(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 2900 */     if (checkAudioDevice3D() && paramSoundSchedulerAtom.sampleId != -1) {
/*      */       
/* 2902 */       stopSound(paramSoundSchedulerAtom, false);
/*      */       
/* 2904 */       this.audioDevice3D.clearSound(paramSoundSchedulerAtom.sampleId);
/*      */     } 
/*      */     
/* 2907 */     paramSoundSchedulerAtom.sampleId = -1;
/*      */     
/* 2909 */     paramSoundSchedulerAtom.loadStatus = 0;
/*      */ 
/*      */     
/* 2912 */     SoundRetained soundRetained = paramSoundSchedulerAtom.sound;
/* 2913 */     paramSoundSchedulerAtom.loadStatus = 0;
/* 2914 */     paramSoundSchedulerAtom.soundData = null;
/* 2915 */     soundRetained.changeAtomList(paramSoundSchedulerAtom, 0);
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
/*      */   void attachSoundData(SoundSchedulerAtom paramSoundSchedulerAtom, MediaContainer paramMediaContainer, boolean paramBoolean) {
/* 2932 */     if (!paramBoolean && paramSoundSchedulerAtom.soundData == paramMediaContainer) {
/*      */       return;
/*      */     }
/* 2935 */     SoundRetained soundRetained = paramSoundSchedulerAtom.sound.sgSound;
/* 2936 */     if (!checkAudioDevice3D()) {
/*      */ 
/*      */       
/* 2939 */       paramSoundSchedulerAtom.loadStatus = 1;
/* 2940 */       soundRetained.changeAtomList(paramSoundSchedulerAtom, 1);
/*      */       return;
/*      */     } 
/* 2943 */     if (paramSoundSchedulerAtom.soundData != null) {
/*      */       
/* 2945 */       clearSoundData(paramSoundSchedulerAtom);
/* 2946 */       if (paramMediaContainer == null) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2953 */     URL uRL = ((MediaContainerRetained)soundRetained.soundData.retained).url;
/* 2954 */     String str = ((MediaContainerRetained)soundRetained.soundData.retained).urlString;
/* 2955 */     InputStream inputStream = ((MediaContainerRetained)soundRetained.soundData.retained).inputStream;
/* 2956 */     if (uRL == null && str == null && inputStream == null) {
/*      */ 
/*      */ 
/*      */       
/* 2960 */       if (paramSoundSchedulerAtom.sampleId != -1) {
/* 2961 */         clearSoundData(paramSoundSchedulerAtom);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2967 */     if (soundRetained instanceof ConeSoundRetained) {
/* 2968 */       soundRetained.soundType = 3;
/* 2969 */     } else if (soundRetained instanceof PointSoundRetained) {
/* 2970 */       soundRetained.soundType = 2;
/*      */     } else {
/* 2972 */       soundRetained.soundType = 1;
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
/* 2983 */     MediaContainer mediaContainer = new MediaContainer();
/* 2984 */     mediaContainer.duplicateAttributes(paramMediaContainer, true);
/* 2985 */     mediaContainer.setCapability(0);
/* 2986 */     mediaContainer.setCapability(2);
/*      */     
/* 2988 */     int i = this.audioDevice3D.prepareSound(soundRetained.soundType, mediaContainer);
/*      */ 
/*      */ 
/*      */     
/* 2992 */     if (i == -1) {
/* 2993 */       paramSoundSchedulerAtom.loadStatus = -1;
/*      */ 
/*      */       
/* 2996 */       soundRetained.changeAtomList(paramSoundSchedulerAtom, -1);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 3002 */       paramSoundSchedulerAtom.sampleId = i;
/*      */ 
/*      */       
/* 3005 */       long l = this.audioDevice3D.getSampleDuration(i);
/* 3006 */       paramSoundSchedulerAtom.sampleLength = l;
/* 3007 */       paramSoundSchedulerAtom.loopLength = paramSoundSchedulerAtom.sampleLength;
/*      */ 
/*      */       
/* 3010 */       paramSoundSchedulerAtom.loopStartOffset = 0L;
/* 3011 */       paramSoundSchedulerAtom.attackLength = 0L;
/* 3012 */       paramSoundSchedulerAtom.releaseLength = 0L;
/* 3013 */       paramSoundSchedulerAtom.loadStatus = 2;
/* 3014 */       paramSoundSchedulerAtom.soundData = paramMediaContainer;
/* 3015 */       soundRetained.changeAtomList(paramSoundSchedulerAtom, 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   SoundSchedulerAtom findSoundAtom(SoundRetained paramSoundRetained, int paramInt) {
/* 3026 */     if (paramSoundRetained == null)
/* 3027 */       return null; 
/* 3028 */     SoundSchedulerAtom soundSchedulerAtom = null;
/* 3029 */     synchronized (this.prioritizedSounds) {
/* 3030 */       if (!this.prioritizedSounds.isEmpty()) {
/* 3031 */         SoundSchedulerAtom soundSchedulerAtom1 = null;
/* 3032 */         byte b1 = 0;
/*      */         
/* 3034 */         int i = this.prioritizedSounds.size();
/* 3035 */         for (byte b2 = 0; b2 < i; b2++) {
/* 3036 */           soundSchedulerAtom1 = (SoundSchedulerAtom)this.prioritizedSounds.get(b2);
/* 3037 */           if (soundSchedulerAtom1.sound != null)
/*      */           {
/*      */             
/* 3040 */             if (soundSchedulerAtom1.sound.sgSound == paramSoundRetained) {
/* 3041 */               b1++;
/*      */ 
/*      */ 
/*      */               
/* 3045 */               if (b1 == paramInt) {
/* 3046 */                 soundSchedulerAtom = soundSchedulerAtom1;
/*      */                 
/*      */                 break;
/*      */               } 
/* 3050 */             } else if (soundSchedulerAtom1.sound.sgSound == paramSoundRetained.sgSound) {
/* 3051 */               b1++;
/*      */               
/* 3053 */               soundSchedulerAtom1.sound = paramSoundRetained;
/* 3054 */               if (b1 == paramInt) {
/* 3055 */                 soundSchedulerAtom = soundSchedulerAtom1;
/*      */                 break;
/*      */               } 
/*      */             }  } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 3062 */     return soundSchedulerAtom;
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
/* 3074 */   void setListenerFlag(int paramInt) { this.listenerUpdated |= paramInt; }
/*      */ 
/*      */ 
/*      */   
/* 3078 */   void clearListenerFlag() { this.listenerUpdated = 0; }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean testListenerFlag() {
/* 3083 */     if (this.listenerUpdated > 0) {
/* 3084 */       return true;
/*      */     }
/* 3086 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAttribsDirtyFlag(SoundRetained paramSoundRetained, int paramInt) {
/* 3096 */     SoundSchedulerAtom soundSchedulerAtom = null;
/* 3097 */     for (byte b = 1;; b++) {
/* 3098 */       soundSchedulerAtom = findSoundAtom(paramSoundRetained, b);
/* 3099 */       if (soundSchedulerAtom == null)
/*      */         break; 
/* 3101 */       soundSchedulerAtom.setAttribsDirtyFlag(paramInt);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setStateDirtyFlag(SoundRetained paramSoundRetained, int paramInt) {
/* 3109 */     SoundSchedulerAtom soundSchedulerAtom = null;
/* 3110 */     for (byte b = 1;; b++) {
/* 3111 */       soundSchedulerAtom = findSoundAtom(paramSoundRetained, b);
/* 3112 */       if (soundSchedulerAtom == null)
/*      */         break; 
/* 3114 */       soundSchedulerAtom.setStateDirtyFlag(paramInt);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void printAtomState(SoundSchedulerAtom paramSoundSchedulerAtom) {
/* 3120 */     SoundRetained soundRetained = paramSoundSchedulerAtom.sound.sgSound;
/* 3121 */     debugPrint("                  this atom = " + paramSoundSchedulerAtom + "       ");
/* 3122 */     debugPrint("                 references sound = " + soundRetained + "       ");
/* 3123 */     debugPrint("                 enabled " + paramSoundSchedulerAtom.enabled);
/* 3124 */     debugPrint("                 status " + paramSoundSchedulerAtom.status);
/* 3125 */     debugPrint("                 activated " + paramSoundSchedulerAtom.activated);
/* 3126 */     debugPrint("                 released " + soundRetained.release);
/* 3127 */     debugPrint("                 continuous " + soundRetained.continuous);
/* 3128 */     debugPrint("                 scheduling " + paramSoundSchedulerAtom.schedulingAction);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void debugPrint(String paramString) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processViewSpecificGroupChanged(J3dMessage paramJ3dMessage) {
/* 3142 */     int i = ((Integer)paramJ3dMessage.args[0]).intValue();
/* 3143 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[1];
/* 3144 */     if ((i & 0x2) != 0 || (i & true) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 3148 */       View view1 = (View)arrayOfObject[0];
/* 3149 */       ArrayList arrayList = (ArrayList)arrayOfObject[2];
/*      */       
/* 3151 */       if (view1 == this.view) {
/* 3152 */         int j = arrayList.size();
/* 3153 */         for (byte b = 0; b < j; b++) {
/* 3154 */           Object object = arrayList.get(b);
/* 3155 */           if (object instanceof SoundRetained) {
/* 3156 */             this.nRetainedSounds++;
/* 3157 */             addSound((SoundRetained)object);
/*      */           }
/* 3159 */           else if (object instanceof SoundscapeRetained) {
/* 3160 */             this.auralAttribsChanged = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3167 */     if ((i & 0x4) != 0 || (i & true) != 0) {
/*      */       View view1;
/*      */ 
/*      */       
/*      */       ArrayList arrayList;
/*      */ 
/*      */       
/* 3174 */       if ((i & 0x4) != 0) {
/* 3175 */         view1 = (View)arrayOfObject[0];
/* 3176 */         arrayList = (ArrayList)arrayOfObject[2];
/*      */       } else {
/*      */         
/* 3179 */         view1 = (View)arrayOfObject[4];
/* 3180 */         arrayList = (ArrayList)arrayOfObject[6];
/*      */       } 
/* 3182 */       if (view1 == this.view) {
/* 3183 */         int j = arrayList.size();
/* 3184 */         for (byte b = 0; b < j; b++) {
/* 3185 */           Object object = arrayList.get(b);
/* 3186 */           if (object instanceof SoundRetained) {
/* 3187 */             SoundSchedulerAtom soundSchedulerAtom = null;
/* 3188 */             for (byte b1 = 1;; b1++) {
/* 3189 */               soundSchedulerAtom = findSoundAtom((SoundRetained)object, b1);
/*      */               
/* 3191 */               if (soundSchedulerAtom == null)
/*      */                 break; 
/* 3193 */               stopSound(soundSchedulerAtom, false);
/*      */             }
/*      */           
/* 3196 */           } else if (object instanceof SoundscapeRetained) {
/* 3197 */             this.auralAttribsChanged = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processBoundingLeafChanged(J3dMessage paramJ3dMessage) {
/* 3208 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[3];
/*      */ 
/*      */     
/* 3211 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/* 3212 */       LeafRetained leafRetained = (LeafRetained)arrayOfObject[b];
/* 3213 */       if (leafRetained instanceof SoundRetained && this.universe.soundStructure.isSoundScopedToView(leafRetained, this.view)) {
/* 3214 */         this.auralAttribsChanged = true;
/*      */       }
/* 3216 */       else if (leafRetained instanceof SoundscapeRetained && this.universe.soundStructure.isSoundscapeScopedToView(leafRetained, this.view)) {
/* 3217 */         this.auralAttribsChanged = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void cleanup() {}
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\SoundScheduler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */