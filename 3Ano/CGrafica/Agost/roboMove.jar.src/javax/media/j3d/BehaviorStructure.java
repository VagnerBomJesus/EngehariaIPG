/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.AWTEvent;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class BehaviorStructure
/*      */   extends J3dStructure
/*      */ {
/*      */   IndexedUnorderSet behaviors;
/*      */   IndexedUnorderSet viewPlatforms;
/*      */   IndexedUnorderSet scheduleList;
/*   47 */   UnorderList[] processList = new UnorderList[10];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   54 */   Point3d vpCenter = new Point3d();
/*   55 */   Point3d vpTransCenter = new Point3d();
/*      */ 
/*      */   
/*      */   WakeupIndexedList boundsEntryList;
/*      */ 
/*      */   
/*      */   WakeupIndexedList boundsExitList;
/*      */ 
/*      */   
/*      */   WakeupIndexedList currentSensorEntryList;
/*      */ 
/*      */   
/*      */   WakeupIndexedList currentSensorExitList;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnAWTEvent;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnActivation;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnDeactivation;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnBehaviorPost;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnElapsedFrames;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnViewPlatformEntry;
/*      */ 
/*      */   
/*      */   WakeupIndexedList wakeupOnViewPlatformExit;
/*      */   
/*      */   WakeupIndexedList wakeupOnSensorEntry;
/*      */   
/*      */   WakeupIndexedList wakeupOnSensorExit;
/*      */   
/*   94 */   UnorderList transformViewPlatformList = new UnorderList(ViewPlatformRetained.class);
/*      */ 
/*      */ 
/*      */   
/*   98 */   int activeWakeupOnFrameCount = 0;
/*      */ 
/*      */   
/*  101 */   int activeWakeupOnSensorCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  109 */   UnorderList awtEventsBuffer = new UnorderList(AWTEvent.class);
/*      */ 
/*      */   
/*  112 */   int[] postIDBuffer = new int[10];
/*  113 */   int[] clonePostIDBuffer = new int[this.postIDBuffer.length];
/*      */   
/*  115 */   UnorderList behaviorPostBuffer = new UnorderList(Behavior.class);
/*      */ 
/*      */ 
/*      */   
/*  119 */   Transform3D sensorTransform = new Transform3D();
/*  120 */   Vector3d sensorLoc = new Vector3d();
/*  121 */   Point3d ptSensorLoc = new Point3d();
/*      */ 
/*      */   
/*  124 */   UnorderList physicalEnvironments = new UnorderList(1, PhysicalEnvironment.class);
/*      */ 
/*      */ 
/*      */   
/*  128 */   UnorderList pendingBehaviors = new UnorderList(BehaviorRetained.class);
/*      */ 
/*      */   
/*      */   boolean branchDetach = false;
/*      */ 
/*      */   
/*  134 */   long awtEventTimestamp = 1L;
/*      */   
/*      */   boolean transformMsg = false;
/*      */   
/*  138 */   UpdateTargets targets = null;
/*      */   
/*      */   BehaviorStructure(VirtualUniverse paramVirtualUniverse) {
/*  141 */     super(paramVirtualUniverse, 256);
/*      */     
/*  143 */     byte b = 9;
/*  144 */     for (; b >= 0; b--) {
/*  145 */       this.processList[b] = new UnorderList(BehaviorRetained.class);
/*      */     }
/*  147 */     this.behaviors = new IndexedUnorderSet(BehaviorRetained.class, 0, paramVirtualUniverse);
/*      */     
/*  149 */     this.viewPlatforms = new IndexedUnorderSet(ViewPlatformRetained.class, 0, paramVirtualUniverse);
/*      */     
/*  151 */     this.scheduleList = new IndexedUnorderSet(BehaviorRetained.class, 1, paramVirtualUniverse);
/*      */     
/*  153 */     this.boundsEntryList = new WakeupIndexedList(WakeupOnViewPlatformEntry.class, 1, paramVirtualUniverse);
/*      */     
/*  155 */     this.boundsExitList = new WakeupIndexedList(WakeupOnViewPlatformExit.class, 1, paramVirtualUniverse);
/*      */     
/*  157 */     this.currentSensorEntryList = new WakeupIndexedList(WakeupOnSensorEntry.class, 1, paramVirtualUniverse);
/*      */     
/*  159 */     this.currentSensorExitList = new WakeupIndexedList(WakeupOnSensorExit.class, 1, paramVirtualUniverse);
/*      */     
/*  161 */     this.wakeupOnAWTEvent = new WakeupIndexedList(WakeupOnAWTEvent.class, 0, paramVirtualUniverse);
/*      */     
/*  163 */     this.wakeupOnActivation = new WakeupIndexedList(WakeupOnActivation.class, 0, paramVirtualUniverse);
/*      */     
/*  165 */     this.wakeupOnDeactivation = new WakeupIndexedList(WakeupOnDeactivation.class, 0, paramVirtualUniverse);
/*      */     
/*  167 */     this.wakeupOnBehaviorPost = new WakeupIndexedList(WakeupOnBehaviorPost.class, 0, paramVirtualUniverse);
/*      */     
/*  169 */     this.wakeupOnElapsedFrames = new WakeupIndexedList(WakeupOnElapsedFrames.class, 0, paramVirtualUniverse);
/*      */     
/*  171 */     this.wakeupOnViewPlatformEntry = new WakeupIndexedList(WakeupOnViewPlatformEntry.class, 0, paramVirtualUniverse);
/*      */     
/*  173 */     this.wakeupOnViewPlatformExit = new WakeupIndexedList(WakeupOnViewPlatformExit.class, 0, paramVirtualUniverse);
/*      */     
/*  175 */     this.wakeupOnSensorEntry = new WakeupIndexedList(WakeupOnSensorEntry.class, 0, paramVirtualUniverse);
/*      */     
/*  177 */     this.wakeupOnSensorExit = new WakeupIndexedList(WakeupOnSensorExit.class, 0, paramVirtualUniverse);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processMessages(long paramLong) {
/*  184 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/*  185 */     int i = getNumMessage();
/*      */ 
/*      */     
/*  188 */     if (i > 0) {
/*  189 */       for (byte b = 0; b < i; b++) {
/*  190 */         BehaviorRetained behaviorRetained; ViewPlatformRetained viewPlatformRetained; ViewPlatform viewPlatform; J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/*      */         
/*  192 */         switch (j3dMessage.type) {
/*      */           case 3:
/*  194 */             this.transformMsg = true;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 28:
/*  199 */             processConditionMet((BehaviorRetained)j3dMessage.args[0], (Boolean)j3dMessage.args[1]);
/*      */             break;
/*      */           
/*      */           case 0:
/*  203 */             insertNodes((Object[])j3dMessage.args[0]);
/*      */             break;
/*      */           case 1:
/*  206 */             removeNodes(j3dMessage);
/*      */             break;
/*      */           case 49:
/*  209 */             activateBehaviors();
/*      */             break;
/*      */           case 29:
/*  212 */             addToScheduleList((BehaviorRetained)j3dMessage.args[1]);
/*  213 */             reEvaluateWakeupCount();
/*      */             break;
/*      */           case 30:
/*  216 */             removeFromScheduleList((BehaviorRetained)j3dMessage.args[1]);
/*  217 */             reEvaluateWakeupCount();
/*      */             break;
/*      */           case 55:
/*  220 */             ((BehaviorRetained)j3dMessage.args[1]).schedulingInterval = ((Integer)j3dMessage.args[2]).intValue();
/*      */             break;
/*      */           
/*      */           case 27:
/*  224 */             processSwitchChanged(j3dMessage);
/*      */             
/*  226 */             if (this.universe.transformStructure.getLazyUpdate()) {
/*  227 */               this.transformMsg = true;
/*      */             }
/*      */             break;
/*      */           case 23:
/*  231 */             processBoundingLeafChanged((Object[])j3dMessage.args[3], (Bounds)j3dMessage.args[2]);
/*      */             break;
/*      */           
/*      */           case 4:
/*  235 */             reEvaluatePhysicalEnvironments();
/*  236 */             viewPlatform = ((View)j3dMessage.args[0]).getViewPlatform();
/*      */             
/*  238 */             if (viewPlatform != null)
/*      */             {
/*  240 */               processViewPlatformTransform((ViewPlatformRetained)viewPlatform.retained);
/*      */             }
/*      */             break;
/*      */           case 48:
/*  244 */             viewPlatformRetained = (ViewPlatformRetained)j3dMessage.args[0];
/*      */             
/*  246 */             viewPlatformRetained.updateActivationRadius(((Float)j3dMessage.args[1]).floatValue());
/*      */             
/*  248 */             processViewPlatformTransform(viewPlatformRetained);
/*      */             break;
/*      */           
/*      */           case 35:
/*  252 */             behaviorRetained = (BehaviorRetained)j3dMessage.args[1];
/*  253 */             behaviorRetained.updateTransformRegion();
/*  254 */             processBehaviorTransform(behaviorRetained);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 60:
/*  259 */             behaviorRetained = (BehaviorRetained)j3dMessage.args[0];
/*  260 */             behaviorRetained.active = false;
/*  261 */             addToScheduleList(behaviorRetained);
/*      */             break;
/*      */         } 
/*      */         
/*  265 */         j3dMessage.decRefcount();
/*      */       } 
/*      */       
/*  268 */       if (this.transformMsg) {
/*      */         
/*  270 */         this.targets = this.universe.transformStructure.getTargetList();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  275 */         UnorderList unorderList = this.targets.targetList[2];
/*  276 */         if (unorderList != null) {
/*  277 */           processBehXformChanged(unorderList);
/*      */         }
/*      */         
/*  280 */         unorderList = this.targets.targetList[4];
/*  281 */         if (unorderList != null) {
/*  282 */           processVpfXformChanged(unorderList);
/*      */         }
/*      */         
/*  285 */         this.transformMsg = false;
/*  286 */         this.targets = null;
/*      */       } 
/*  288 */       Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  294 */     if (this.activeWakeupOnSensorCount <= 0) {
/*  295 */       if (this.activeWakeupOnFrameCount > 0) {
/*      */         
/*  297 */         VirtualUniverse.mc.sendRunMessage(this.universe, 17);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  302 */         VirtualUniverse.mc.sendRunMessage(this.universe, 1);
/*      */       } 
/*      */     } else {
/*      */       
/*  306 */       checkSensorEntryExit();
/*      */       
/*  308 */       if (this.activeWakeupOnFrameCount > 0) {
/*  309 */         VirtualUniverse.mc.sendRunMessage(this.universe, 273);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  315 */         VirtualUniverse.mc.sendRunMessage(this.universe, 257);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void insertNodes(Object[] paramArrayOfObject) {
/*  323 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/*  324 */       Object object = paramArrayOfObject[b];
/*      */       
/*  326 */       if (object instanceof BehaviorRetained) {
/*  327 */         this.pendingBehaviors.add(object);
/*      */       }
/*  329 */       else if (object instanceof ViewPlatformRetained) {
/*  330 */         addViewPlatform((ViewPlatformRetained)object);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void activateBehaviors() {
/*  337 */     BehaviorRetained[] arrayOfBehaviorRetained = (BehaviorRetained[])this.pendingBehaviors.toArray(false);
/*      */ 
/*      */     
/*  340 */     for (int i = this.pendingBehaviors.arraySize() - 1; i >= 0; i--) {
/*  341 */       BehaviorRetained behaviorRetained = arrayOfBehaviorRetained[i];
/*  342 */       behaviorRetained.wakeupCondition = behaviorRetained.newWakeupCondition;
/*  343 */       if (behaviorRetained.wakeupCondition != null) {
/*  344 */         behaviorRetained.wakeupCondition.buildTree(null, 0, behaviorRetained);
/*  345 */         behaviorRetained.conditionSet = true;
/*  346 */         this.behaviors.add(behaviorRetained);
/*  347 */         behaviorRetained.updateTransformRegion();
/*  348 */         addToScheduleList(behaviorRetained);
/*      */       } 
/*      */     } 
/*  351 */     this.pendingBehaviors.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void addViewPlatform(ViewPlatformRetained paramViewPlatformRetained) {
/*  357 */     BehaviorRetained[] arrayOfBehaviorRetained = (BehaviorRetained[])this.behaviors.toArray(false);
/*      */     
/*  359 */     this.viewPlatforms.add(paramViewPlatformRetained);
/*  360 */     paramViewPlatformRetained.updateTransformRegion();
/*      */     
/*  362 */     if (!paramViewPlatformRetained.isActiveViewPlatform()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*      */     int i;
/*      */     
/*  369 */     for (i = this.behaviors.arraySize() - 1; i >= 0; i--) {
/*  370 */       addToScheduleList(arrayOfBehaviorRetained[i]);
/*      */     }
/*      */ 
/*      */     
/*  374 */     WakeupOnViewPlatformEntry[] arrayOfWakeupOnViewPlatformEntry = (WakeupOnViewPlatformEntry[])this.wakeupOnViewPlatformEntry.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  378 */     for (i = this.wakeupOnViewPlatformEntry.arraySize() - 1; i >= 0; i--) {
/*  379 */       WakeupOnViewPlatformEntry wakeupOnViewPlatformEntry1 = arrayOfWakeupOnViewPlatformEntry[i];
/*  380 */       if (!this.boundsEntryList.contains(wakeupOnViewPlatformEntry1) && wakeupOnViewPlatformEntry1.transformedRegion.intersect(paramViewPlatformRetained.center)) {
/*      */         
/*  382 */         this.boundsEntryList.add(wakeupOnViewPlatformEntry1);
/*  383 */         wakeupOnViewPlatformEntry1.triggeredVP = paramViewPlatformRetained;
/*  384 */         wakeupOnViewPlatformEntry1.setTriggered();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  389 */     WakeupOnViewPlatformExit[] arrayOfWakeupOnViewPlatformExit = (WakeupOnViewPlatformExit[])this.wakeupOnViewPlatformExit.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  393 */     for (i = this.wakeupOnViewPlatformExit.arraySize() - 1; i >= 0; i--) {
/*  394 */       WakeupOnViewPlatformExit wakeupOnViewPlatformExit1 = arrayOfWakeupOnViewPlatformExit[i];
/*  395 */       if (!this.boundsExitList.contains(wakeupOnViewPlatformExit1) && wakeupOnViewPlatformExit1.transformedRegion.intersect(paramViewPlatformRetained.center)) {
/*      */         
/*  397 */         wakeupOnViewPlatformExit1.triggeredVP = paramViewPlatformRetained;
/*  398 */         this.boundsExitList.add(wakeupOnViewPlatformExit1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void removeNodes(J3dMessage paramJ3dMessage) {
/*  405 */     Object[] arrayOfObject = (Object[])paramJ3dMessage.args[0];
/*  406 */     boolean bool = false;
/*      */     
/*  408 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/*  409 */       Object object = arrayOfObject[b];
/*  410 */       if (object instanceof BehaviorRetained) {
/*  411 */         bool = true;
/*  412 */         removeBehavior((BehaviorRetained)object);
/*      */       }
/*  414 */       else if (object instanceof ViewPlatformRetained) {
/*  415 */         removeViewPlatform((ViewPlatformRetained)object);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  421 */     this.wakeupOnAWTEvent.clearMirror();
/*  422 */     this.awtEventsBuffer.clearMirror();
/*  423 */     this.wakeupOnBehaviorPost.clearMirror();
/*  424 */     this.behaviorPostBuffer.clearMirror();
/*  425 */     this.wakeupOnSensorEntry.clearMirror();
/*  426 */     this.wakeupOnSensorExit.clearMirror();
/*  427 */     this.branchDetach = true;
/*      */     
/*  429 */     if (bool) {
/*      */       
/*  431 */       WakeupOnAWTEvent[] arrayOfWakeupOnAWTEvent = (WakeupOnAWTEvent[])this.wakeupOnAWTEvent.toArray();
/*      */       
/*  433 */       int i = this.wakeupOnAWTEvent.arraySize();
/*      */ 
/*      */       
/*  436 */       boolean bool1 = false;
/*  437 */       boolean bool2 = false;
/*  438 */       boolean bool3 = false;
/*  439 */       boolean bool4 = false;
/*  440 */       boolean bool5 = false;
/*      */ 
/*      */ 
/*      */       
/*  444 */       boolean bool6 = false;
/*      */       
/*  446 */       for (byte b1 = 0; b1 < i; b1++) {
/*  447 */         WakeupOnAWTEvent wakeupOnAWTEvent1 = arrayOfWakeupOnAWTEvent[b1];
/*  448 */         int j = wakeupOnAWTEvent1.AwtId;
/*  449 */         long l = wakeupOnAWTEvent1.EventMask;
/*      */         
/*  451 */         if ((j >= 1004 && j <= 1005) || (l & 0x4L) != 0L)
/*      */         {
/*  453 */           bool1 = true;
/*      */         }
/*  455 */         if ((j >= 400 && j <= 402) || (l & 0x8L) != 0L)
/*      */         {
/*  457 */           bool2 = true;
/*      */         }
/*  459 */         if (j >= 500 && j <= 507) {
/*      */           
/*  461 */           if (j == 506 || j == 503) {
/*      */             
/*  463 */             bool3 = true;
/*      */           }
/*  465 */           else if (j == 504 || j == 505 || j == 500 || j == 501 || j == 502) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  470 */             bool4 = true;
/*      */           }
/*  472 */           else if (j == 507) {
/*  473 */             bool5 = true;
/*      */           } 
/*      */         } else {
/*  476 */           if ((l & 0x10L) != 0L) {
/*  477 */             bool4 = true;
/*      */           }
/*  479 */           if ((l & 0x20L) != 0L) {
/*  480 */             bool3 = true;
/*      */           }
/*  482 */           if ((l & 0x20000L) != 0L) {
/*  483 */             bool5 = true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  488 */       if (!bool1 && this.universe.enableFocus) {
/*  489 */         bool6 = true;
/*  490 */         this.universe.disableFocusEvents();
/*      */       } 
/*  492 */       if (!VirtualUniverse.mc.isD3D() && !bool2 && this.universe.enableKey) {
/*      */         
/*  494 */         bool6 = true;
/*  495 */         this.universe.disableKeyEvents();
/*      */       } 
/*  497 */       if (!bool5 && this.universe.enableMouseWheel) {
/*  498 */         bool6 = true;
/*  499 */         this.universe.disableMouseWheelEvents();
/*      */       } 
/*  501 */       if (!bool3 && this.universe.enableMouseMotion) {
/*  502 */         bool6 = true;
/*  503 */         this.universe.disableMouseMotionEvents();
/*      */       } 
/*  505 */       if (!bool4 && this.universe.enableMouse) {
/*  506 */         bool6 = true;
/*  507 */         this.universe.disableMouseEvents();
/*      */       } 
/*  509 */       if (bool6) {
/*  510 */         this.awtEventTimestamp++;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeViewPlatform(ViewPlatformRetained paramViewPlatformRetained) {
/*  519 */     this.viewPlatforms.remove(paramViewPlatformRetained);
/*      */     
/*  521 */     BehaviorRetained[] arrayOfBehaviorRetained = (BehaviorRetained[])this.scheduleList.toArray(false);
/*      */     
/*      */     int i;
/*      */     
/*  525 */     for (i = this.scheduleList.arraySize() - 1; i >= 0; i--) {
/*  526 */       BehaviorRetained behaviorRetained = arrayOfBehaviorRetained[i];
/*      */ 
/*      */       
/*  529 */       if (!intersectVPRegion(behaviorRetained.transformedRegion)) {
/*  530 */         removeFromScheduleList(behaviorRetained);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  535 */     WakeupOnViewPlatformEntry[] arrayOfWakeupOnViewPlatformEntry = (WakeupOnViewPlatformEntry[])this.boundsEntryList.toArray(false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  540 */     for (i = this.boundsEntryList.arraySize() - 1; i >= 0; i--) {
/*  541 */       WakeupOnViewPlatformEntry wakeupOnViewPlatformEntry1 = arrayOfWakeupOnViewPlatformEntry[i];
/*      */ 
/*      */       
/*  544 */       ViewPlatformRetained viewPlatformRetained = intersectVPCenter(wakeupOnViewPlatformEntry1.transformedRegion);
/*  545 */       if (viewPlatformRetained == null) {
/*  546 */         this.boundsEntryList.remove(wakeupOnViewPlatformEntry1);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  551 */     WakeupOnViewPlatformExit[] arrayOfWakeupOnViewPlatformExit = (WakeupOnViewPlatformExit[])this.boundsExitList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  555 */     for (i = this.boundsExitList.arraySize() - 1; i >= 0; i--) {
/*  556 */       WakeupOnViewPlatformExit wakeupOnViewPlatformExit1 = arrayOfWakeupOnViewPlatformExit[i];
/*      */ 
/*      */       
/*  559 */       ViewPlatformRetained viewPlatformRetained = intersectVPCenter(wakeupOnViewPlatformExit1.transformedRegion);
/*  560 */       if (viewPlatformRetained == null) {
/*  561 */         this.boundsExitList.remove(wakeupOnViewPlatformExit1);
/*  562 */         wakeupOnViewPlatformExit1.setTriggered();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void removeBehavior(BehaviorRetained paramBehaviorRetained) {
/*  568 */     this.behaviors.remove(paramBehaviorRetained);
/*      */     
/*  570 */     if (paramBehaviorRetained.wakeupCondition != null && paramBehaviorRetained.wakeupCondition.behav != null) {
/*      */       
/*  572 */       paramBehaviorRetained.wakeupCondition.cleanTree(this);
/*  573 */       if (paramBehaviorRetained.universe == this.universe) {
/*  574 */         paramBehaviorRetained.conditionSet = false;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  580 */     WakeupOnViewPlatformEntry[] arrayOfWakeupOnViewPlatformEntry = (WakeupOnViewPlatformEntry[])this.boundsEntryList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  584 */     for (int i = this.boundsEntryList.arraySize() - 1; i >= 0; i--) {
/*  585 */       WakeupOnViewPlatformEntry wakeupOnViewPlatformEntry1 = arrayOfWakeupOnViewPlatformEntry[i];
/*  586 */       if (wakeupOnViewPlatformEntry1.behav == paramBehaviorRetained) {
/*  587 */         this.boundsEntryList.remove(wakeupOnViewPlatformEntry1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  593 */     WakeupOnViewPlatformExit[] arrayOfWakeupOnViewPlatformExit = (WakeupOnViewPlatformExit[])this.boundsExitList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  597 */     for (int j = this.boundsExitList.arraySize() - 1; j >= 0; j--) {
/*  598 */       WakeupOnViewPlatformExit wakeupOnViewPlatformExit1 = arrayOfWakeupOnViewPlatformExit[j];
/*  599 */       if (wakeupOnViewPlatformExit1.behav == paramBehaviorRetained) {
/*  600 */         this.boundsExitList.remove(wakeupOnViewPlatformExit1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  607 */     WakeupOnSensorEntry[] arrayOfWakeupOnSensorEntry = (WakeupOnSensorEntry[])this.currentSensorEntryList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  611 */     for (int k = this.currentSensorEntryList.arraySize() - 1; k >= 0; k--) {
/*  612 */       WakeupOnSensorEntry wakeupOnSensorEntry1 = arrayOfWakeupOnSensorEntry[k];
/*  613 */       if (wakeupOnSensorEntry1.behav == paramBehaviorRetained) {
/*  614 */         this.currentSensorEntryList.remove(wakeupOnSensorEntry1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  621 */     WakeupOnSensorExit[] arrayOfWakeupOnSensorExit = (WakeupOnSensorExit[])this.currentSensorExitList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  625 */     for (int m = this.currentSensorExitList.arraySize() - 1; m >= 0; m--) {
/*  626 */       WakeupOnSensorExit wakeupOnSensorExit1 = arrayOfWakeupOnSensorExit[m];
/*  627 */       if (wakeupOnSensorExit1.behav == paramBehaviorRetained) {
/*  628 */         this.currentSensorExitList.remove(wakeupOnSensorExit1);
/*      */       }
/*      */     } 
/*  631 */     removeFromScheduleList(paramBehaviorRetained);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void handleAWTEvent(AWTEvent paramAWTEvent) {
/*  637 */     this.awtEventsBuffer.add(paramAWTEvent);
/*  638 */     VirtualUniverse.mc.sendRunMessage(this.universe, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void handleAWTEvent() {
/*      */     int j;
/*      */     AWTEvent[] arrayOfAWTEvent;
/*  647 */     WakeupOnAWTEvent[] arrayOfWakeupOnAWTEvent = (WakeupOnAWTEvent[])this.wakeupOnAWTEvent.toArray();
/*      */ 
/*      */     
/*  650 */     int i = this.wakeupOnAWTEvent.arraySize();
/*      */ 
/*      */     
/*  653 */     synchronized (this.awtEventsBuffer) {
/*  654 */       arrayOfAWTEvent = (AWTEvent[])this.awtEventsBuffer.toArray();
/*  655 */       j = this.awtEventsBuffer.size();
/*  656 */       this.awtEventsBuffer.clear();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  662 */     for (byte b = 0; b < i; b++) {
/*  663 */       WakeupOnAWTEvent wakeupOnAWTEvent1 = arrayOfWakeupOnAWTEvent[b];
/*  664 */       for (byte b1 = 0; b1 < j; b1++) {
/*  665 */         AWTEvent aWTEvent = arrayOfAWTEvent[b1];
/*  666 */         int k = aWTEvent.getID();
/*      */         
/*  668 */         if (wakeupOnAWTEvent1.AwtId != 0) {
/*  669 */           if (wakeupOnAWTEvent1.AwtId == k)
/*      */           {
/*      */             
/*  672 */             wakeupOnAWTEvent1.addAWTEvent(aWTEvent);
/*      */           }
/*      */         }
/*  675 */         else if (k >= 100 && k <= 103 && (wakeupOnAWTEvent1.EventMask & 0x1L) != 0L) {
/*      */ 
/*      */           
/*  678 */           wakeupOnAWTEvent1.addAWTEvent(aWTEvent);
/*      */         }
/*  680 */         else if (k >= 1004 && k <= 1005 && (wakeupOnAWTEvent1.EventMask & 0x4L) != 0L) {
/*      */ 
/*      */           
/*  683 */           wakeupOnAWTEvent1.addAWTEvent(aWTEvent);
/*      */         }
/*  685 */         else if (k >= 400 && k <= 402 && (wakeupOnAWTEvent1.EventMask & 0x8L) != 0L) {
/*      */ 
/*      */           
/*  688 */           wakeupOnAWTEvent1.addAWTEvent(aWTEvent);
/*      */         }
/*  690 */         else if ((k == 500 || k == 504 || k == 505 || k == 501 || k == 502) && (wakeupOnAWTEvent1.EventMask & 0x10L) != 0L) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  696 */           wakeupOnAWTEvent1.addAWTEvent(aWTEvent);
/*      */         }
/*  698 */         else if ((k == 506 || k == 503) && (wakeupOnAWTEvent1.EventMask & 0x20L) != 0L) {
/*      */ 
/*      */           
/*  701 */           wakeupOnAWTEvent1.addAWTEvent(aWTEvent);
/*      */         }
/*  703 */         else if (k == 507 && (wakeupOnAWTEvent1.EventMask & 0x20000L) != 0L) {
/*      */           
/*  705 */           wakeupOnAWTEvent1.addAWTEvent(aWTEvent);
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
/*      */   void handleBehaviorPost(Behavior paramBehavior, int paramInt) {
/*  718 */     synchronized (this.behaviorPostBuffer) {
/*  719 */       int i = this.behaviorPostBuffer.size();
/*  720 */       if (this.postIDBuffer.length == i) {
/*  721 */         int[] arrayOfInt = this.postIDBuffer;
/*  722 */         this.postIDBuffer = new int[i << 1];
/*  723 */         System.arraycopy(arrayOfInt, 0, this.postIDBuffer, 0, i);
/*      */       } 
/*  725 */       this.postIDBuffer[i] = paramInt;
/*  726 */       this.behaviorPostBuffer.add(paramBehavior);
/*      */     } 
/*  728 */     VirtualUniverse.mc.sendRunMessage(this.universe, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void handleBehaviorPost() {
/*      */     int i;
/*      */     Behavior[] arrayOfBehavior;
/*  739 */     WakeupOnBehaviorPost[] arrayOfWakeupOnBehaviorPost = (WakeupOnBehaviorPost[])this.wakeupOnBehaviorPost.toArray();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  744 */     synchronized (this.behaviorPostBuffer) {
/*  745 */       arrayOfBehavior = (Behavior[])this.behaviorPostBuffer.toArray();
/*  746 */       i = this.behaviorPostBuffer.size();
/*  747 */       if (this.clonePostIDBuffer.length < i) {
/*  748 */         this.clonePostIDBuffer = new int[i];
/*      */       }
/*  750 */       System.arraycopy(this.postIDBuffer, 0, this.clonePostIDBuffer, 0, i);
/*      */       
/*  752 */       this.behaviorPostBuffer.clear();
/*      */     } 
/*      */     
/*  755 */     int j = this.wakeupOnBehaviorPost.arraySize();
/*  756 */     for (byte b = 0; b < j; b++) {
/*  757 */       WakeupOnBehaviorPost wakeupOnBehaviorPost1 = arrayOfWakeupOnBehaviorPost[b];
/*  758 */       for (byte b1 = 0; b1 < i; b1++) {
/*  759 */         Behavior behavior = arrayOfBehavior[b1];
/*  760 */         int k = this.clonePostIDBuffer[b1];
/*  761 */         if ((wakeupOnBehaviorPost1.post == k || wakeupOnBehaviorPost1.post == 0) && (behavior == wakeupOnBehaviorPost1.armingBehavior || wakeupOnBehaviorPost1.armingBehavior == null)) {
/*      */           
/*  763 */           wakeupOnBehaviorPost1.triggeringBehavior = behavior;
/*  764 */           wakeupOnBehaviorPost1.triggeringPost = k;
/*  765 */           wakeupOnBehaviorPost1.setTriggered();
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
/*      */   void incElapsedFrames() {
/*  778 */     WakeupOnElapsedFrames[] arrayOfWakeupOnElapsedFrames = (WakeupOnElapsedFrames[])this.wakeupOnElapsedFrames.toArray(true);
/*      */     
/*  780 */     int i = this.wakeupOnElapsedFrames.arraySize();
/*  781 */     byte b = 0;
/*      */     
/*  783 */     while (b < i) {
/*  784 */       arrayOfWakeupOnElapsedFrames[b++].newFrame();
/*      */     }
/*      */     
/*  787 */     if (i > 0) {
/*  788 */       VirtualUniverse.mc.sendRunMessage(this.universe, 257);
/*      */     }
/*      */ 
/*      */     
/*  792 */     if (this.branchDetach) {
/*      */ 
/*      */       
/*  795 */       this.wakeupOnElapsedFrames.clearMirror();
/*  796 */       this.branchDetach = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  802 */   void removeVPEntryCondition(WakeupCondition paramWakeupCondition) { this.wakeupOnViewPlatformEntry.remove(paramWakeupCondition); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addVPEntryCondition(WakeupOnViewPlatformEntry paramWakeupOnViewPlatformEntry) {
/*  810 */     boolean bool = true;
/*      */ 
/*      */ 
/*      */     
/*  814 */     WakeupOnViewPlatformEntry[] arrayOfWakeupOnViewPlatformEntry = (WakeupOnViewPlatformEntry[])this.boundsEntryList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  818 */     for (int i = this.boundsEntryList.arraySize() - 1; i >= 0; i--) {
/*  819 */       WakeupOnViewPlatformEntry wakeupOnViewPlatformEntry1 = arrayOfWakeupOnViewPlatformEntry[i];
/*  820 */       if (wakeupOnViewPlatformEntry1.behav == paramWakeupOnViewPlatformEntry.behav && wakeupOnViewPlatformEntry1.region.equals(paramWakeupOnViewPlatformEntry.region)) {
/*      */         
/*  822 */         this.boundsEntryList.remove(i);
/*      */ 
/*      */         
/*  825 */         bool = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  830 */     this.wakeupOnViewPlatformEntry.add(paramWakeupOnViewPlatformEntry);
/*      */     
/*  832 */     ViewPlatformRetained viewPlatformRetained = intersectVPCenter(paramWakeupOnViewPlatformEntry.transformedRegion);
/*  833 */     if (viewPlatformRetained != null) {
/*  834 */       this.boundsEntryList.add(paramWakeupOnViewPlatformEntry);
/*      */     }
/*      */ 
/*      */     
/*  838 */     if (bool && viewPlatformRetained != null) {
/*  839 */       paramWakeupOnViewPlatformEntry.triggeredVP = viewPlatformRetained;
/*  840 */       paramWakeupOnViewPlatformEntry.setTriggered();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  845 */   void removeVPExitCondition(WakeupOnViewPlatformExit paramWakeupOnViewPlatformExit) { this.wakeupOnViewPlatformExit.remove(paramWakeupOnViewPlatformExit); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addVPExitCondition(WakeupOnViewPlatformExit paramWakeupOnViewPlatformExit) {
/*  854 */     boolean bool = true;
/*  855 */     WakeupOnViewPlatformExit[] arrayOfWakeupOnViewPlatformExit = (WakeupOnViewPlatformExit[])this.boundsExitList.toArray(false);
/*      */ 
/*      */     
/*  858 */     for (int i = this.boundsExitList.arraySize() - 1; i >= 0; i--) {
/*  859 */       WakeupOnViewPlatformExit wakeupOnViewPlatformExit1 = arrayOfWakeupOnViewPlatformExit[i];
/*  860 */       if (wakeupOnViewPlatformExit1.behav == paramWakeupOnViewPlatformExit.behav && wakeupOnViewPlatformExit1.region.equals(paramWakeupOnViewPlatformExit.region)) {
/*      */         
/*  862 */         this.boundsExitList.remove(i);
/*  863 */         bool = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  868 */     ViewPlatformRetained viewPlatformRetained = intersectVPCenter(paramWakeupOnViewPlatformExit.transformedRegion);
/*  869 */     this.wakeupOnViewPlatformExit.add(paramWakeupOnViewPlatformExit);
/*      */     
/*  871 */     if (viewPlatformRetained != null) {
/*  872 */       paramWakeupOnViewPlatformExit.triggeredVP = viewPlatformRetained;
/*  873 */       this.boundsExitList.add(paramWakeupOnViewPlatformExit);
/*      */     } 
/*      */     
/*  876 */     if (!bool) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  883 */     WakeupOnViewPlatformEntry[] arrayOfWakeupOnViewPlatformEntry = (WakeupOnViewPlatformEntry[])this.boundsEntryList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  887 */     for (int j = this.boundsEntryList.arraySize() - 1; j >= 0; j--) {
/*  888 */       WakeupOnViewPlatformEntry wakeupOnViewPlatformEntry1 = arrayOfWakeupOnViewPlatformEntry[j];
/*  889 */       if (wakeupOnViewPlatformEntry1.behav == paramWakeupOnViewPlatformExit.behav && wakeupOnViewPlatformEntry1.region.equals(paramWakeupOnViewPlatformExit.region)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  894 */         if (viewPlatformRetained == null) {
/*  895 */           paramWakeupOnViewPlatformExit.setTriggered();
/*      */         }
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  905 */   void removeSensorEntryCondition(WakeupOnSensorEntry paramWakeupOnSensorEntry) { this.wakeupOnSensorEntry.remove(paramWakeupOnSensorEntry); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addSensorEntryCondition(WakeupOnSensorEntry paramWakeupOnSensorEntry) {
/*  912 */     boolean bool = true;
/*      */ 
/*      */ 
/*      */     
/*  916 */     WakeupOnSensorEntry[] arrayOfWakeupOnSensorEntry = (WakeupOnSensorEntry[])this.currentSensorEntryList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  920 */     for (int i = this.currentSensorEntryList.arraySize() - 1; i >= 0; i--) {
/*  921 */       WakeupOnSensorEntry wakeupOnSensorEntry1 = arrayOfWakeupOnSensorEntry[i];
/*  922 */       if (wakeupOnSensorEntry1.behav == paramWakeupOnSensorEntry.behav && wakeupOnSensorEntry1.region.equals(paramWakeupOnSensorEntry.region)) {
/*      */         
/*  924 */         this.currentSensorEntryList.remove(i);
/*  925 */         bool = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  930 */     this.wakeupOnSensorEntry.add(paramWakeupOnSensorEntry);
/*      */     
/*  932 */     paramWakeupOnSensorEntry.updateTransformRegion();
/*  933 */     Sensor sensor = sensorIntersect(paramWakeupOnSensorEntry.transformedRegion);
/*  934 */     if (sensor != null) {
/*  935 */       paramWakeupOnSensorEntry.setTarget(sensor);
/*  936 */       this.currentSensorEntryList.add(paramWakeupOnSensorEntry);
/*      */     } 
/*      */     
/*  939 */     if (bool && sensor != null) {
/*  940 */       paramWakeupOnSensorEntry.setTriggered();
/*      */     }
/*      */     
/*  943 */     VirtualUniverse.mc.sendRunMessage(this.universe, 256);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  948 */   void removeSensorExitCondition(WakeupOnSensorExit paramWakeupOnSensorExit) { this.wakeupOnSensorExit.remove(paramWakeupOnSensorExit); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addSensorExitCondition(WakeupOnSensorExit paramWakeupOnSensorExit) {
/*  956 */     boolean bool = true;
/*      */     
/*  958 */     WakeupOnSensorExit[] arrayOfWakeupOnSensorExit = (WakeupOnSensorExit[])this.currentSensorExitList.toArray(false);
/*      */ 
/*      */     
/*  961 */     for (int i = this.currentSensorExitList.arraySize() - 1; i >= 0; i--) {
/*  962 */       WakeupOnSensorExit wakeupOnSensorExit1 = arrayOfWakeupOnSensorExit[i];
/*  963 */       if (wakeupOnSensorExit1.behav == paramWakeupOnSensorExit.behav && wakeupOnSensorExit1.region.equals(paramWakeupOnSensorExit.region)) {
/*      */         
/*  965 */         this.currentSensorExitList.remove(i);
/*  966 */         bool = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  971 */     paramWakeupOnSensorExit.updateTransformRegion();
/*  972 */     Sensor sensor = sensorIntersect(paramWakeupOnSensorExit.transformedRegion);
/*  973 */     this.wakeupOnSensorExit.add(paramWakeupOnSensorExit);
/*      */     
/*  975 */     if (sensor != null) {
/*  976 */       paramWakeupOnSensorExit.setTarget(sensor);
/*  977 */       this.currentSensorExitList.add(paramWakeupOnSensorExit);
/*      */     } 
/*      */     
/*  980 */     if (!bool) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  985 */     WakeupOnSensorEntry[] arrayOfWakeupOnSensorEntry = (WakeupOnSensorEntry[])this.currentSensorEntryList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/*  989 */     for (int j = this.currentSensorEntryList.arraySize() - 1; j >= 0; j--) {
/*  990 */       WakeupOnSensorEntry wakeupOnSensorEntry1 = arrayOfWakeupOnSensorEntry[j];
/*  991 */       if (wakeupOnSensorEntry1.behav == paramWakeupOnSensorExit.behav && wakeupOnSensorEntry1.region.equals(paramWakeupOnSensorExit.region)) {
/*      */ 
/*      */         
/*  994 */         if (sensor == null) {
/*  995 */           paramWakeupOnSensorExit.setTriggered();
/*      */         }
/*      */         break;
/*      */       } 
/*      */     } 
/* 1000 */     VirtualUniverse.mc.sendRunMessage(this.universe, 256);
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
/*      */   void processConditionMet(BehaviorRetained paramBehaviorRetained, Boolean paramBoolean) {
/* 1013 */     if (!paramBehaviorRetained.inCallback && (paramBoolean == Boolean.FALSE || paramBehaviorRetained.active)) {
/*      */ 
/*      */       
/* 1016 */       this.processList[paramBehaviorRetained.schedulingInterval].add(paramBehaviorRetained);
/*      */     }
/* 1018 */     else if ((paramBehaviorRetained.wakeupMask & 0x10) != 0 && paramBehaviorRetained.source != null && paramBehaviorRetained.source.isLive() && paramBehaviorRetained.wakeupCondition != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1025 */       paramBehaviorRetained.wakeupCondition.reInsertElapseTimeCond();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void processBehXformChanged(UnorderList paramUnorderList) {
/* 1034 */     int i = paramUnorderList.size();
/* 1035 */     Object[] arrayOfObject = paramUnorderList.toArray(false);
/*      */     
/* 1037 */     for (byte b = 0; b < i; b++) {
/* 1038 */       Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 1039 */       for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/* 1040 */         BehaviorRetained behaviorRetained = (BehaviorRetained)arrayOfObject1[b1];
/* 1041 */         behaviorRetained.updateTransformRegion();
/* 1042 */         processBehaviorTransform(behaviorRetained);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void processVpfXformChanged(UnorderList paramUnorderList) {
/* 1051 */     int i = paramUnorderList.size();
/* 1052 */     Object[] arrayOfObject = paramUnorderList.toArray(false);
/*      */     
/* 1054 */     for (byte b = 0; b < i; b++) {
/* 1055 */       Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 1056 */       for (byte b1 = 0; b1 < arrayOfObject1.length; b1++) {
/* 1057 */         processViewPlatformTransform((ViewPlatformRetained)arrayOfObject1[b1]);
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
/*      */   final void processTransformChanged(Object[] paramArrayOfObject) {
/*      */     byte b;
/* 1072 */     for (b = 0; b < paramArrayOfObject.length; b++) {
/* 1073 */       Object object = paramArrayOfObject[b];
/* 1074 */       if (object instanceof BehaviorRetained) {
/* 1075 */         ((BehaviorRetained)object).updateTransformRegion();
/* 1076 */         processBehaviorTransform((BehaviorRetained)object);
/*      */       }
/* 1078 */       else if (object instanceof ViewPlatformRetained) {
/* 1079 */         ((ViewPlatformRetained)object).updateTransformRegion();
/* 1080 */         this.transformViewPlatformList.add(object);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1085 */     if (this.transformViewPlatformList.size() > 0) {
/* 1086 */       ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.transformViewPlatformList.toArray(false);
/*      */ 
/*      */       
/* 1089 */       int i = this.transformViewPlatformList.arraySize();
/* 1090 */       for (b = 0; b < i; b++) {
/* 1091 */         processViewPlatformTransform(arrayOfViewPlatformRetained[b]);
/*      */       }
/*      */       
/* 1094 */       this.transformViewPlatformList.clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   final void processBehaviorTransform(BehaviorRetained paramBehaviorRetained) {
/* 1101 */     if ((paramBehaviorRetained.wakeupMask & 0x4) != 0) {
/* 1102 */       updateVPEntryTransformRegion(paramBehaviorRetained);
/*      */     }
/*      */     
/* 1105 */     if ((paramBehaviorRetained.wakeupMask & 0x8) != 0) {
/* 1106 */       updateVPExitTransformRegion(paramBehaviorRetained);
/*      */     }
/*      */     
/* 1109 */     if (paramBehaviorRetained.active) {
/* 1110 */       if (!intersectVPRegion(paramBehaviorRetained.transformedRegion)) {
/* 1111 */         removeFromScheduleList(paramBehaviorRetained);
/*      */       }
/*      */     } else {
/* 1114 */       addToScheduleList(paramBehaviorRetained);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processViewPlatformTransform(ViewPlatformRetained paramViewPlatformRetained) {
/* 1123 */     paramViewPlatformRetained.updateTransformRegion();
/*      */     
/* 1125 */     if (!paramViewPlatformRetained.isActiveViewPlatform()) {
/*      */       return;
/*      */     }
/*      */     
/* 1129 */     BehaviorRetained[] arrayOfBehaviorRetained = (BehaviorRetained[])this.behaviors.toArray(false);
/*      */     
/*      */     int i;
/* 1132 */     for (i = this.behaviors.arraySize() - 1; i >= 0; i--) {
/* 1133 */       BehaviorRetained behaviorRetained = arrayOfBehaviorRetained[i];
/* 1134 */       if (behaviorRetained.active) {
/* 1135 */         if (!intersectVPRegion(behaviorRetained.transformedRegion)) {
/* 1136 */           removeFromScheduleList(behaviorRetained);
/*      */         }
/*      */       } else {
/* 1139 */         addToScheduleList(behaviorRetained);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1144 */     WakeupOnViewPlatformEntry[] arrayOfWakeupOnViewPlatformEntry = (WakeupOnViewPlatformEntry[])this.wakeupOnViewPlatformEntry.toArray(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1150 */     for (i = this.wakeupOnViewPlatformEntry.arraySize() - 1; i >= 0; i--) {
/* 1151 */       WakeupOnViewPlatformEntry wakeupOnViewPlatformEntry1 = arrayOfWakeupOnViewPlatformEntry[i];
/* 1152 */       int j = this.boundsEntryList.indexOf(wakeupOnViewPlatformEntry1);
/* 1153 */       if (j < 0) {
/* 1154 */         if (wakeupOnViewPlatformEntry1.transformedRegion.intersect(paramViewPlatformRetained.center)) {
/* 1155 */           this.boundsEntryList.add(wakeupOnViewPlatformEntry1);
/* 1156 */           wakeupOnViewPlatformEntry1.triggeredVP = paramViewPlatformRetained;
/* 1157 */           wakeupOnViewPlatformEntry1.setTriggered();
/*      */         } 
/*      */       } else {
/* 1160 */         ViewPlatformRetained viewPlatformRetained = intersectVPCenter(wakeupOnViewPlatformEntry1.transformedRegion);
/* 1161 */         if (viewPlatformRetained == null) {
/* 1162 */           this.boundsEntryList.remove(j);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1168 */     WakeupOnViewPlatformExit[] arrayOfWakeupOnViewPlatformExit = (WakeupOnViewPlatformExit[])this.wakeupOnViewPlatformExit.toArray(false);
/*      */ 
/*      */ 
/*      */     
/* 1172 */     for (i = this.wakeupOnViewPlatformExit.arraySize() - 1; i >= 0; i--) {
/* 1173 */       WakeupOnViewPlatformExit wakeupOnViewPlatformExit1 = arrayOfWakeupOnViewPlatformExit[i];
/* 1174 */       int j = this.boundsExitList.indexOf(wakeupOnViewPlatformExit1);
/* 1175 */       if (j < 0) {
/* 1176 */         if (wakeupOnViewPlatformExit1.transformedRegion.intersect(paramViewPlatformRetained.center)) {
/* 1177 */           wakeupOnViewPlatformExit1.triggeredVP = paramViewPlatformRetained;
/* 1178 */           this.boundsExitList.add(wakeupOnViewPlatformExit1);
/*      */         } 
/*      */       } else {
/*      */         
/* 1182 */         ViewPlatformRetained viewPlatformRetained = intersectVPCenter(wakeupOnViewPlatformExit1.transformedRegion);
/* 1183 */         if (viewPlatformRetained == null) {
/* 1184 */           this.boundsExitList.remove(j);
/* 1185 */           wakeupOnViewPlatformExit1.setTriggered();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void updateVPEntryTransformRegion(BehaviorRetained paramBehaviorRetained) {
/* 1192 */     WakeupOnViewPlatformEntry[] arrayOfWakeupOnViewPlatformEntry = (WakeupOnViewPlatformEntry[])this.wakeupOnViewPlatformEntry.toArray(false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1197 */     for (int i = this.wakeupOnViewPlatformEntry.arraySize() - 1; i >= 0; i--) {
/* 1198 */       WakeupOnViewPlatformEntry wakeupOnViewPlatformEntry1 = arrayOfWakeupOnViewPlatformEntry[i];
/* 1199 */       if (wakeupOnViewPlatformEntry1.behav == paramBehaviorRetained) {
/* 1200 */         wakeupOnViewPlatformEntry1.updateTransformRegion(paramBehaviorRetained);
/* 1201 */         int j = this.boundsEntryList.indexOf(wakeupOnViewPlatformEntry1);
/*      */         
/* 1203 */         ViewPlatformRetained viewPlatformRetained = intersectVPCenter(wakeupOnViewPlatformEntry1.transformedRegion);
/* 1204 */         if (viewPlatformRetained != null) {
/* 1205 */           if (j < 0) {
/* 1206 */             this.boundsEntryList.add(wakeupOnViewPlatformEntry1);
/* 1207 */             wakeupOnViewPlatformEntry1.triggeredVP = viewPlatformRetained;
/* 1208 */             wakeupOnViewPlatformEntry1.setTriggered();
/*      */           }
/*      */         
/* 1211 */         } else if (j >= 0) {
/* 1212 */           this.boundsEntryList.remove(j);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateVPExitTransformRegion(BehaviorRetained paramBehaviorRetained) {
/* 1223 */     WakeupOnViewPlatformExit[] arrayOfWakeupOnViewPlatformExit = (WakeupOnViewPlatformExit[])this.wakeupOnViewPlatformExit.toArray(false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1228 */     for (int i = this.wakeupOnViewPlatformExit.arraySize() - 1; i >= 0; i--) {
/* 1229 */       WakeupOnViewPlatformExit wakeupOnViewPlatformExit1 = arrayOfWakeupOnViewPlatformExit[i];
/* 1230 */       if (wakeupOnViewPlatformExit1.behav == paramBehaviorRetained) {
/* 1231 */         wakeupOnViewPlatformExit1.updateTransformRegion(paramBehaviorRetained);
/* 1232 */         wakeupOnViewPlatformExit1 = arrayOfWakeupOnViewPlatformExit[i];
/* 1233 */         int j = this.boundsExitList.indexOf(wakeupOnViewPlatformExit1);
/* 1234 */         ViewPlatformRetained viewPlatformRetained = intersectVPCenter(wakeupOnViewPlatformExit1.transformedRegion);
/* 1235 */         if (viewPlatformRetained != null) {
/* 1236 */           if (j < 0) {
/* 1237 */             wakeupOnViewPlatformExit1.triggeredVP = viewPlatformRetained;
/* 1238 */             this.boundsExitList.add(wakeupOnViewPlatformExit1);
/*      */           }
/*      */         
/* 1241 */         } else if (j >= 0) {
/* 1242 */           this.boundsExitList.remove(j);
/* 1243 */           wakeupOnViewPlatformExit1.setTriggered();
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
/*      */   void reEvaluatePhysicalEnvironments() {
/* 1257 */     ArrayList arrayList = this.universe.viewPlatforms;
/*      */     
/* 1259 */     this.physicalEnvironments.clear();
/*      */     
/* 1261 */     for (int i = arrayList.size() - 1; i >= 0; i--) {
/* 1262 */       View[] arrayOfView = ((ViewPlatformRetained)arrayList.get(i)).getViewList();
/* 1263 */       for (int j = arrayOfView.length - 1; j >= 0; j--) {
/* 1264 */         View view = arrayOfView[j];
/* 1265 */         if (view.active && !this.physicalEnvironments.contains(view.physicalEnvironment))
/*      */         {
/* 1267 */           this.physicalEnvironments.add(view.physicalEnvironment);
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
/*      */   void checkSensorEntryExit() {
/* 1279 */     WakeupOnSensorEntry[] arrayOfWakeupOnSensorEntry = (WakeupOnSensorEntry[])this.wakeupOnSensorEntry.toArray();
/*      */     
/*      */     int i;
/* 1282 */     for (i = this.wakeupOnSensorEntry.arraySize() - 1; i >= 0; i--) {
/* 1283 */       WakeupOnSensorEntry wakeupOnSensorEntry1 = arrayOfWakeupOnSensorEntry[i];
/* 1284 */       int j = this.currentSensorEntryList.indexOf(wakeupOnSensorEntry1);
/* 1285 */       wakeupOnSensorEntry1.updateTransformRegion();
/* 1286 */       Sensor sensor = sensorIntersect(wakeupOnSensorEntry1.transformedRegion);
/* 1287 */       if (sensor != null) {
/* 1288 */         if (j < 0) {
/* 1289 */           this.currentSensorEntryList.add(wakeupOnSensorEntry1);
/* 1290 */           wakeupOnSensorEntry1.setTarget(sensor);
/* 1291 */           wakeupOnSensorEntry1.setTriggered();
/*      */         }
/*      */       
/* 1294 */       } else if (j >= 0) {
/* 1295 */         this.currentSensorEntryList.remove(j);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1302 */     WakeupOnSensorExit[] arrayOfWakeupOnSensorExit = (WakeupOnSensorExit[])this.wakeupOnSensorExit.toArray();
/*      */ 
/*      */     
/* 1305 */     for (i = this.wakeupOnSensorExit.arraySize() - 1; i >= 0; i--) {
/* 1306 */       WakeupOnSensorExit wakeupOnSensorExit1 = arrayOfWakeupOnSensorExit[i];
/* 1307 */       int j = this.currentSensorExitList.indexOf(wakeupOnSensorExit1);
/* 1308 */       wakeupOnSensorExit1.updateTransformRegion();
/* 1309 */       Sensor sensor = sensorIntersect(wakeupOnSensorExit1.transformedRegion);
/* 1310 */       if (sensor != null) {
/* 1311 */         if (j < 0) {
/* 1312 */           this.currentSensorExitList.add(wakeupOnSensorExit1);
/* 1313 */           wakeupOnSensorExit1.setTarget(sensor);
/*      */         }
/*      */       
/* 1316 */       } else if (j >= 0) {
/* 1317 */         this.currentSensorExitList.remove(j);
/* 1318 */         wakeupOnSensorExit1.setTriggered();
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
/*      */   Sensor sensorIntersect(Bounds paramBounds) {
/* 1331 */     if (paramBounds == null) {
/* 1332 */       return null;
/*      */     }
/* 1334 */     PhysicalEnvironment[] arrayOfPhysicalEnvironment = (PhysicalEnvironment[])this.physicalEnvironments.toArray(false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1339 */     for (int i = this.physicalEnvironments.arraySize() - 1; i >= 0; i--) {
/* 1340 */       if ((arrayOfPhysicalEnvironment[i]).activeViewRef > 0) {
/* 1341 */         Sensor[] arrayOfSensor = arrayOfPhysicalEnvironment[i].getSensorList();
/* 1342 */         if (arrayOfSensor != null) {
/* 1343 */           for (int j = (arrayOfPhysicalEnvironment[i]).users.size() - 1; j >= 0; j--) {
/* 1344 */             View view = (View)(arrayOfPhysicalEnvironment[i]).users.get(j);
/* 1345 */             synchronized (arrayOfSensor) {
/* 1346 */               for (int k = arrayOfSensor.length - 1; k >= 0; k--) {
/* 1347 */                 Sensor sensor = arrayOfSensor[k];
/* 1348 */                 if (sensor != null) {
/* 1349 */                   view.getSensorToVworld(sensor, this.sensorTransform);
/* 1350 */                   this.sensorTransform.get(this.sensorLoc);
/* 1351 */                   this.ptSensorLoc.set(this.sensorLoc);
/* 1352 */                   if (paramBounds.intersect(this.ptSensorLoc)) {
/* 1353 */                     return sensor;
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/* 1362 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final boolean intersectVPRegion(Bounds paramBounds) {
/* 1370 */     if (paramBounds == null) {
/* 1371 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1375 */     ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.viewPlatforms.toArray(false);
/*      */ 
/*      */     
/* 1378 */     for (int i = this.viewPlatforms.arraySize() - 1; i >= 0; i--) {
/* 1379 */       ViewPlatformRetained viewPlatformRetained = arrayOfViewPlatformRetained[i];
/* 1380 */       if (viewPlatformRetained.isActiveViewPlatform() && viewPlatformRetained.schedSphere.intersect(paramBounds))
/*      */       {
/* 1382 */         return true;
/*      */       }
/*      */     } 
/* 1385 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final ViewPlatformRetained intersectVPCenter(Bounds paramBounds) {
/* 1392 */     if (paramBounds == null) {
/* 1393 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1397 */     ViewPlatformRetained[] arrayOfViewPlatformRetained = (ViewPlatformRetained[])this.viewPlatforms.toArray(false);
/*      */ 
/*      */ 
/*      */     
/* 1401 */     for (int i = this.viewPlatforms.arraySize() - 1; i >= 0; i--) {
/* 1402 */       ViewPlatformRetained viewPlatformRetained = arrayOfViewPlatformRetained[i];
/* 1403 */       if (viewPlatformRetained.isActiveViewPlatform() && paramBounds.intersect(viewPlatformRetained.center))
/*      */       {
/* 1405 */         return viewPlatformRetained;
/*      */       }
/*      */     } 
/* 1408 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   void notifyDeactivationCondition(BehaviorRetained paramBehaviorRetained) {
/* 1413 */     WakeupOnDeactivation[] arrayOfWakeupOnDeactivation = (WakeupOnDeactivation[])this.wakeupOnDeactivation.toArray(false);
/*      */ 
/*      */     
/* 1416 */     for (int i = this.wakeupOnDeactivation.arraySize() - 1; i >= 0; i--) {
/* 1417 */       WakeupOnDeactivation wakeupOnDeactivation1 = arrayOfWakeupOnDeactivation[i];
/* 1418 */       if (wakeupOnDeactivation1.behav == paramBehaviorRetained) {
/* 1419 */         wakeupOnDeactivation1.setTriggered();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void notifyActivationCondition(BehaviorRetained paramBehaviorRetained) {
/* 1426 */     WakeupOnActivation[] arrayOfWakeupOnActivation = (WakeupOnActivation[])this.wakeupOnActivation.toArray(false);
/*      */ 
/*      */     
/* 1429 */     for (int i = this.wakeupOnActivation.arraySize() - 1; i >= 0; i--) {
/* 1430 */       WakeupOnActivation wakeupOnActivation1 = arrayOfWakeupOnActivation[i];
/* 1431 */       if (wakeupOnActivation1.behav == paramBehaviorRetained) {
/* 1432 */         wakeupOnActivation1.setTriggered();
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
/*      */   void processSwitchChanged(J3dMessage paramJ3dMessage) {
/* 1445 */     UpdateTargets updateTargets = (UpdateTargets)paramJ3dMessage.args[0];
/* 1446 */     UnorderList unorderList = updateTargets.targetList[4];
/*      */     
/* 1448 */     if (unorderList != null) {
/*      */       
/* 1450 */       int i = unorderList.size();
/* 1451 */       Object[] arrayOfObject = unorderList.toArray(false);
/*      */       
/* 1453 */       for (byte b = 0; b < i; b++) {
/* 1454 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 1455 */         for (int j = arrayOfObject1.length - 1; j >= 0; j--) {
/* 1456 */           ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject1[j];
/* 1457 */           viewPlatformRetained.processSwitchChanged();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1462 */     unorderList = updateTargets.targetList[2];
/*      */     
/* 1464 */     if (unorderList != null) {
/*      */       
/* 1466 */       int i = unorderList.size();
/* 1467 */       Object[] arrayOfObject = unorderList.toArray(false);
/*      */       
/* 1469 */       for (byte b = 0; b < i; b++) {
/* 1470 */         Object[] arrayOfObject1 = (Object[])arrayOfObject[b];
/* 1471 */         for (int j = arrayOfObject1.length - 1; j >= 0; j--) {
/* 1472 */           BehaviorRetained behaviorRetained = (BehaviorRetained)arrayOfObject1[j];
/* 1473 */           if (behaviorRetained.switchState.currentSwitchOn) {
/* 1474 */             addToScheduleList(behaviorRetained);
/*      */           } else {
/* 1476 */             removeFromScheduleList(behaviorRetained);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1482 */     unorderList = updateTargets.targetList[5];
/* 1483 */     if (unorderList != null) {
/* 1484 */       int i = unorderList.size();
/* 1485 */       Object[] arrayOfObject1 = unorderList.toArray(false);
/* 1486 */       Object[] arrayOfObject2 = (Object[])paramJ3dMessage.args[1];
/*      */ 
/*      */ 
/*      */       
/* 1490 */       for (byte b = 0; b < i; b++) {
/* 1491 */         Object[] arrayOfObject3 = (Object[])arrayOfObject1[b];
/* 1492 */         Object[] arrayOfObject4 = (Object[])arrayOfObject2[b];
/* 1493 */         for (int j = arrayOfObject3.length - 1; j >= 0; j--) {
/*      */           
/* 1495 */           Object[] arrayOfObject5 = (Object[])arrayOfObject4[j];
/* 1496 */           Object[] arrayOfObject6 = new Object[1];
/* 1497 */           BoundingLeafRetained boundingLeafRetained = (BoundingLeafRetained)arrayOfObject3[j];
/* 1498 */           for (byte b1 = 0; b1 < arrayOfObject5.length; b1++) {
/* 1499 */             if (arrayOfObject5[b1] instanceof BehaviorRetained) {
/* 1500 */               arrayOfObject6[0] = arrayOfObject5[b1];
/* 1501 */               processTransformChanged(arrayOfObject6);
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
/*      */   void processBoundingLeafChanged(Object[] paramArrayOfObject, Bounds paramBounds) {
/* 1514 */     for (int i = paramArrayOfObject.length - 1; i >= 0; i--) {
/* 1515 */       Object object = paramArrayOfObject[i];
/* 1516 */       if (object instanceof BehaviorRetained) {
/* 1517 */         BehaviorRetained behaviorRetained = (BehaviorRetained)object;
/* 1518 */         behaviorRetained.updateTransformRegion(paramBounds);
/* 1519 */         processBehaviorTransform(behaviorRetained);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   final void removeFromScheduleList(BehaviorRetained paramBehaviorRetained) {
/* 1525 */     if (paramBehaviorRetained.active) {
/* 1526 */       if ((paramBehaviorRetained.wakeupMask & 0x2) != 0)
/*      */       {
/* 1528 */         notifyDeactivationCondition(paramBehaviorRetained);
/*      */       }
/* 1530 */       this.scheduleList.remove(paramBehaviorRetained);
/* 1531 */       paramBehaviorRetained.active = false;
/* 1532 */       if (paramBehaviorRetained.universe != this.universe) {
/* 1533 */         J3dMessage j3dMessage = new J3dMessage();
/* 1534 */         j3dMessage.threads = 256;
/* 1535 */         j3dMessage.type = 60;
/* 1536 */         j3dMessage.universe = paramBehaviorRetained.universe;
/* 1537 */         j3dMessage.args[0] = paramBehaviorRetained;
/* 1538 */         VirtualUniverse.mc.processMessage(j3dMessage);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   final void addToScheduleList(BehaviorRetained paramBehaviorRetained) {
/* 1545 */     if (!paramBehaviorRetained.inCallback && !paramBehaviorRetained.active && paramBehaviorRetained.enable && paramBehaviorRetained.switchState.currentSwitchOn && paramBehaviorRetained.wakeupCondition != null && ((Behavior)paramBehaviorRetained.source).isLive() && intersectVPRegion(paramBehaviorRetained.transformedRegion)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1553 */       this.scheduleList.add(paramBehaviorRetained);
/* 1554 */       paramBehaviorRetained.active = true;
/* 1555 */       if ((paramBehaviorRetained.wakeupMask & true) != 0)
/*      */       {
/* 1557 */         notifyActivationCondition(paramBehaviorRetained);
/*      */       }
/*      */       
/* 1560 */       if (paramBehaviorRetained.wakeupCondition != null)
/*      */       {
/*      */ 
/*      */         
/* 1564 */         paramBehaviorRetained.wakeupCondition.conditionMet = false;
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
/*      */   void resetConditionMet() {
/* 1577 */     resetConditionMet(this.wakeupOnAWTEvent);
/* 1578 */     resetConditionMet(this.wakeupOnActivation);
/* 1579 */     resetConditionMet(this.wakeupOnDeactivation);
/* 1580 */     resetConditionMet(this.wakeupOnBehaviorPost);
/* 1581 */     resetConditionMet(this.wakeupOnElapsedFrames);
/* 1582 */     resetConditionMet(this.wakeupOnViewPlatformEntry);
/* 1583 */     resetConditionMet(this.wakeupOnViewPlatformExit);
/* 1584 */     resetConditionMet(this.wakeupOnSensorEntry);
/* 1585 */     resetConditionMet(this.wakeupOnSensorExit);
/*      */   }
/*      */   
/*      */   static void resetConditionMet(WakeupIndexedList paramWakeupIndexedList) {
/* 1589 */     WakeupCondition[] arrayOfWakeupCondition = (WakeupCondition[])paramWakeupIndexedList.toArray(false);
/* 1590 */     int i = paramWakeupIndexedList.size() - 1;
/* 1591 */     while (i >= 0) {
/* 1592 */       (arrayOfWakeupCondition[i--]).conditionMet = false;
/*      */     }
/*      */   }
/*      */   
/*      */   void reEvaluateWakeupCount() {
/* 1597 */     WakeupOnElapsedFrames[] arrayOfWakeupOnElapsedFrames = (WakeupOnElapsedFrames[])this.wakeupOnElapsedFrames.toArray(true);
/*      */     
/* 1599 */     int i = this.wakeupOnElapsedFrames.arraySize();
/* 1600 */     int j = 0;
/*      */ 
/*      */     
/* 1603 */     this.activeWakeupOnFrameCount = 0;
/*      */     
/* 1605 */     while (j < i) {
/* 1606 */       WakeupOnElapsedFrames wakeupOnElapsedFrames1 = arrayOfWakeupOnElapsedFrames[j++];
/* 1607 */       if (!wakeupOnElapsedFrames1.passive && wakeupOnElapsedFrames1.behav != null && wakeupOnElapsedFrames1.behav.enable)
/*      */       {
/*      */         
/* 1610 */         this.activeWakeupOnFrameCount++;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1615 */     this.activeWakeupOnSensorCount = 0;
/*      */     
/* 1617 */     WakeupOnSensorEntry[] arrayOfWakeupOnSensorEntry = (WakeupOnSensorEntry[])this.wakeupOnSensorEntry.toArray();
/*      */ 
/*      */     
/* 1620 */     for (j = this.wakeupOnSensorEntry.arraySize() - 1; j >= 0; j--) {
/* 1621 */       WakeupOnSensorEntry wakeupOnSensorEntry1 = arrayOfWakeupOnSensorEntry[j];
/* 1622 */       if (wakeupOnSensorEntry1.behav != null && wakeupOnSensorEntry1.behav.enable)
/*      */       {
/* 1624 */         this.activeWakeupOnSensorCount++;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1629 */     WakeupOnSensorExit[] arrayOfWakeupOnSensorExit = (WakeupOnSensorExit[])this.wakeupOnSensorExit.toArray();
/*      */ 
/*      */     
/* 1632 */     for (j = this.wakeupOnSensorExit.arraySize() - 1; j >= 0; j--) {
/* 1633 */       WakeupOnSensorExit wakeupOnSensorExit1 = arrayOfWakeupOnSensorExit[j];
/* 1634 */       if (wakeupOnSensorExit1.behav != null && wakeupOnSensorExit1.behav.enable)
/*      */       {
/* 1636 */         this.activeWakeupOnSensorCount++;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void cleanup() {
/* 1643 */     this.behaviors.clear();
/* 1644 */     this.viewPlatforms.clear();
/* 1645 */     this.scheduleList.clear();
/* 1646 */     this.boundsEntryList.clear();
/* 1647 */     this.boundsExitList.clear();
/* 1648 */     this.currentSensorEntryList.clear();
/* 1649 */     this.currentSensorExitList.clear();
/* 1650 */     this.wakeupOnAWTEvent.clear();
/* 1651 */     this.wakeupOnActivation.clear();
/* 1652 */     this.wakeupOnDeactivation.clear();
/* 1653 */     this.wakeupOnBehaviorPost.clear();
/* 1654 */     this.wakeupOnElapsedFrames.clear();
/* 1655 */     this.wakeupOnViewPlatformEntry.clear();
/* 1656 */     this.wakeupOnViewPlatformExit.clear();
/* 1657 */     this.wakeupOnSensorEntry.clear();
/* 1658 */     this.wakeupOnSensorExit.clear();
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\BehaviorStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */