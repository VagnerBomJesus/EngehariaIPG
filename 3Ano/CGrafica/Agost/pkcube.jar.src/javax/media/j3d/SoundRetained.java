/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class SoundRetained
/*      */   extends LeafRetained
/*      */ {
/*      */   static final int NULL_SOUND = -1;
/*   36 */   MediaContainer soundData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   41 */   float initialGain = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   46 */   int loopCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean enable = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean release = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean continuous = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean mute = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean pause = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   float priority = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   86 */   float rate = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   91 */   Bounds schedulingRegion = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   96 */   BoundingLeafRetained boundingLeaf = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  101 */   Bounds transformedRegion = null;
/*      */ 
/*      */   
/*      */   static final int SOUND_DATA_DIRTY_BIT = 1;
/*      */ 
/*      */   
/*      */   static final int INITIAL_GAIN_DIRTY_BIT = 2;
/*      */   
/*      */   static final int LOOP_COUNT_DIRTY_BIT = 4;
/*      */   
/*      */   static final int BOUNDS_DIRTY_BIT = 8;
/*      */   
/*      */   static final int BOUNDING_LEAF_DIRTY_BIT = 16;
/*      */   
/*      */   static final int PRIORITY_DIRTY_BIT = 32;
/*      */   
/*      */   static final int POSITION_DIRTY_BIT = 64;
/*      */   
/*      */   static final int DISTANCE_GAIN_DIRTY_BIT = 128;
/*      */   
/*      */   static final int BACK_DISTANCE_GAIN_DIRTY_BIT = 256;
/*      */   
/*      */   static final int DIRECTION_DIRTY_BIT = 512;
/*      */   
/*      */   static final int ANGULAR_ATTENUATION_DIRTY_BIT = 1024;
/*      */   
/*      */   static final int RATE_DIRTY_BIT = 2048;
/*      */   
/*      */   static final int BOUNDS_CHANGED = 24;
/*      */   
/*      */   static final int ATTRIBUTE_DIRTY_BITS = 2087;
/*      */   
/*      */   static final int POSITIONAL_DIRTY_BITS = 2279;
/*      */   
/*      */   static final int DIRECTIONAL_DIRTY_BITS = 4071;
/*      */   
/*      */   static final int ALL_ATTIBS_DIRTY_BITS = 4095;
/*      */   
/*      */   static final int LIVE_DIRTY_BIT = 1;
/*      */   
/*      */   static final int IMMEDIATE_MODE_DIRTY_BIT = 2;
/*      */   
/*      */   static final int LOAD_SOUND_DIRTY_BIT = 4;
/*      */   
/*      */   static final int RELEASE_DIRTY_BIT = 8;
/*      */   
/*      */   static final int CONTINUOUS_DIRTY_BIT = 16;
/*      */   
/*      */   static final int ENABLE_DIRTY_BIT = 32;
/*      */   
/*      */   static final int MUTE_DIRTY_BIT = 64;
/*      */   
/*      */   static final int PAUSE_DIRTY_BIT = 128;
/*      */   
/*      */   static final int XFORM_DIRTY_BIT = 32768;
/*      */   
/*      */   static final int ALL_STATE_DIRTY_BITS = 33023;
/*      */   
/*  159 */   int soundType = -1;
/*      */ 
/*      */   
/*  162 */   SoundRetained sgSound = null;
/*      */ 
/*      */   
/*  165 */   HashKey key = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  170 */   SoundRetained[] mirrorSounds = new SoundRetained[1];
/*      */ 
/*      */   
/*  173 */   int numMirrorSounds = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  181 */   private SoundSchedulerAtom[] loadedAtoms = new SoundSchedulerAtom[1];
/*  182 */   private int atomCount = 0;
/*      */ 
/*      */   
/*      */   boolean inImmCtx = false;
/*      */ 
/*      */   
/*      */   static final int LOAD_COMPLETE = 2;
/*      */ 
/*      */   
/*      */   static final int LOAD_PENDING = 1;
/*      */   
/*      */   static final int LOAD_NULL = 0;
/*      */   
/*      */   static final int LOAD_FAILED = -1;
/*      */   
/*  197 */   int loadStatus = 0;
/*  198 */   long duration = -1L;
/*      */   static final int targetThreads = 514;
/*      */   
/*      */   static  {
/*  202 */     VirtualUniverse.loadLibraries();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isViewScoped = false;
/*      */ 
/*      */   
/*      */   static final boolean debugFlag = false;
/*      */ 
/*      */   
/*      */   static final boolean internalErrors = false;
/*      */ 
/*      */ 
/*      */   
/*      */   void dispatchAttribChange(int paramInt, Object paramObject) {
/*  218 */     J3dMessage j3dMessage = new J3dMessage();
/*  219 */     j3dMessage.threads = 514;
/*      */     
/*  221 */     j3dMessage.type = 38;
/*  222 */     j3dMessage.universe = this.universe;
/*  223 */     j3dMessage.args[0] = this;
/*  224 */     j3dMessage.args[1] = new Integer(paramInt);
/*  225 */     if (this.inSharedGroup) {
/*  226 */       j3dMessage.args[2] = new Integer(this.numMirrorSounds);
/*      */     } else {
/*  228 */       j3dMessage.args[2] = new Integer(1);
/*  229 */     }  j3dMessage.args[3] = this.mirrorSounds.clone();
/*  230 */     j3dMessage.args[4] = paramObject;
/*      */ 
/*      */     
/*  233 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void dispatchStateChange(int paramInt, Object paramObject) {
/*  241 */     J3dMessage j3dMessage = new J3dMessage();
/*  242 */     j3dMessage.threads = 514;
/*      */     
/*  244 */     j3dMessage.type = 45;
/*  245 */     j3dMessage.universe = this.universe;
/*  246 */     j3dMessage.args[0] = this;
/*  247 */     j3dMessage.args[1] = new Integer(paramInt);
/*  248 */     if (this.inSharedGroup) {
/*  249 */       j3dMessage.args[2] = new Integer(this.numMirrorSounds);
/*      */     } else {
/*  251 */       j3dMessage.args[2] = new Integer(1);
/*  252 */     }  j3dMessage.args[3] = this.mirrorSounds.clone();
/*  253 */     j3dMessage.args[4] = paramObject;
/*      */ 
/*      */     
/*  256 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  264 */   void setSoundDataState(MediaContainer paramMediaContainer) { this.soundData = paramMediaContainer; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSoundData(MediaContainer paramMediaContainer) {
/*  274 */     if (this.soundData == paramMediaContainer) {
/*      */       return;
/*      */     }
/*      */     
/*  278 */     if (this.soundData != null)
/*      */     {
/*  280 */       ((MediaContainerRetained)this.soundData.retained).removeUser(this);
/*      */     }
/*      */     
/*  283 */     if (this.source != null && this.source.isLive()) {
/*  284 */       if (this.soundData != null) {
/*  285 */         ((MediaContainerRetained)this.soundData.retained).clearLive(this.refCount);
/*      */       }
/*      */       
/*  288 */       if (paramMediaContainer != null) {
/*  289 */         ((MediaContainerRetained)paramMediaContainer.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  290 */         ((MediaContainerRetained)paramMediaContainer.retained).addUser(this);
/*      */       } 
/*      */     } 
/*      */     
/*  294 */     this.soundData = paramMediaContainer;
/*  295 */     dispatchAttribChange(1, paramMediaContainer);
/*      */     
/*  297 */     if (this.source != null && this.source.isLive()) {
/*  298 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  307 */   MediaContainer getSoundData() { return this.soundData; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setInitialGain(float paramFloat) {
/*  316 */     if (paramFloat < 0.0F) {
/*  317 */       this.initialGain = 0.0F;
/*      */     } else {
/*  319 */       this.initialGain = paramFloat;
/*      */     } 
/*  321 */     dispatchAttribChange(2, new Float(paramFloat));
/*  322 */     if (this.source != null && this.source.isLive()) {
/*  323 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  331 */   float getInitialGain() { return this.initialGain; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLoop(int paramInt) {
/*  340 */     if (paramInt < -1) {
/*  341 */       this.loopCount = -1;
/*      */     } else {
/*  343 */       this.loopCount = paramInt;
/*      */     } 
/*      */ 
/*      */     
/*  347 */     dispatchAttribChange(4, new Integer(paramInt));
/*  348 */     if (this.source != null && this.source.isLive()) {
/*  349 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  358 */   int getLoop() { return this.loopCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setReleaseEnable(boolean paramBoolean) {
/*  366 */     this.release = paramBoolean;
/*  367 */     dispatchAttribChange(8, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*  368 */     if (this.source != null && this.source.isLive()) {
/*  369 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  378 */   boolean getReleaseEnable() { return this.release; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setContinuousEnable(boolean paramBoolean) {
/*  386 */     this.continuous = paramBoolean;
/*  387 */     dispatchAttribChange(16, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*  388 */     if (this.source != null && this.source.isLive()) {
/*  389 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  398 */   boolean getContinuousEnable() { return this.continuous; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setEnable(boolean paramBoolean) {
/*  412 */     this.enable = paramBoolean;
/*      */     
/*  414 */     if (this.source != null && this.source.isLive()) {
/*  415 */       notifySceneGraphChanged(false);
/*      */     }
/*  417 */     dispatchStateChange(32, new Boolean(this.enable));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  425 */   boolean getEnable() { return this.enable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSchedulingBounds(Bounds paramBounds) {
/*  433 */     if (paramBounds != null) {
/*  434 */       this.schedulingRegion = (Bounds)paramBounds.clone();
/*  435 */       if (this.staticTransform != null) {
/*  436 */         this.schedulingRegion.transform(this.staticTransform.transform);
/*      */       }
/*      */       
/*  439 */       this.transformedRegion = (Bounds)this.schedulingRegion.clone();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  444 */       this.schedulingRegion = null;
/*      */ 
/*      */       
/*  447 */       this.transformedRegion = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  453 */     dispatchAttribChange(8, paramBounds);
/*  454 */     if (this.source != null && this.source.isLive()) {
/*  455 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds getSchedulingBounds() {
/*  464 */     Bounds bounds = null;
/*      */     
/*  466 */     if (this.schedulingRegion != null) {
/*  467 */       bounds = (Bounds)this.schedulingRegion.clone();
/*  468 */       if (this.staticTransform != null) {
/*  469 */         Transform3D transform3D = this.staticTransform.getInvTransform();
/*  470 */         bounds.transform(transform3D);
/*      */       } 
/*      */     } 
/*  473 */     return bounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSchedulingBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/*  481 */     int i = this.numMirrorSounds;
/*  482 */     if (this.numMirrorSounds == 0) {
/*  483 */       i = 1;
/*      */     }
/*  485 */     if (this.boundingLeaf != null && this.source != null && this.source.isLive())
/*      */     {
/*      */       
/*  488 */       for (byte b = 0; b < i; b++) {
/*  489 */         this.boundingLeaf.mirrorBoundingLeaf.removeUser(this.mirrorSounds[b]);
/*      */       }
/*      */     }
/*      */     
/*  493 */     if (paramBoundingLeaf != null) {
/*  494 */       this.boundingLeaf = (BoundingLeafRetained)paramBoundingLeaf.retained;
/*      */       
/*  496 */       if (this.source != null && this.source.isLive()) {
/*  497 */         for (byte b = 0; b < i; b++) {
/*  498 */           this.boundingLeaf.mirrorBoundingLeaf.addUser(this.mirrorSounds[b]);
/*      */         }
/*      */       }
/*      */     } else {
/*  502 */       this.boundingLeaf = null;
/*      */     } 
/*      */ 
/*      */     
/*  506 */     dispatchAttribChange(16, paramBoundingLeaf);
/*  507 */     if (this.source != null && this.source.isLive()) {
/*  508 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   BoundingLeaf getSchedulingBoundingLeaf() {
/*  516 */     if (this.boundingLeaf != null) {
/*  517 */       return (BoundingLeaf)this.boundingLeaf.source;
/*      */     }
/*  519 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMirrorObject(Object[] paramArrayOfObject) {
/*  525 */     Object object = null;
/*  526 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*  527 */     if (i == -1)
/*      */     {
/*      */       
/*  530 */       initMirrorObject((SoundRetained)paramArrayOfObject[2]);
/*      */     }
/*      */ 
/*      */     
/*  534 */     super.updateMirrorObject(paramArrayOfObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateBoundingLeaf(long paramLong) {
/*  541 */     if (this.boundingLeaf != null && this.boundingLeaf.switchState.currentSwitchOn) {
/*  542 */       this.transformedRegion = this.boundingLeaf.transformedRegion;
/*      */     }
/*  544 */     else if (this.schedulingRegion != null) {
/*  545 */       this.transformedRegion = this.schedulingRegion.copy(this.transformedRegion);
/*  546 */       this.transformedRegion.transform(this.schedulingRegion, getLastLocalToVworld());
/*      */     } else {
/*      */       
/*  549 */       this.transformedRegion = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPriority(float paramFloat) {
/*  560 */     if (paramFloat == this.priority) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  566 */     this.priority = paramFloat;
/*  567 */     dispatchAttribChange(32, new Float(paramFloat));
/*  568 */     if (this.source != null && this.source.isLive()) {
/*  569 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  578 */   float getPriority() { return this.priority; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  588 */   long getDuration() { return this.duration; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setRateScaleFactor(float paramFloat) {
/*  597 */     this.rate = paramFloat;
/*  598 */     dispatchAttribChange(2048, new Float(paramFloat));
/*  599 */     if (this.source != null && this.source.isLive()) {
/*  600 */       notifySceneGraphChanged(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  609 */   float getRateScaleFactor() { return this.rate; }
/*      */ 
/*      */   
/*      */   void changeAtomList(SoundSchedulerAtom paramSoundSchedulerAtom, int paramInt) {
/*  613 */     if (paramSoundSchedulerAtom == null)
/*      */       return; 
/*  615 */     if (paramInt == 2) {
/*      */       int i;
/*      */       
/*  618 */       for (i = 0; i < this.atomCount; i++) {
/*  619 */         if (paramSoundSchedulerAtom == this.loadedAtoms[i]) {
/*      */           return;
/*      */         }
/*      */       } 
/*  623 */       this.atomCount++;
/*  624 */       i = this.loadedAtoms.length;
/*  625 */       if (this.atomCount > i)
/*      */       {
/*  627 */         this.loadedAtoms = new SoundSchedulerAtom[2 * i];
/*      */       }
/*  629 */       this.loadedAtoms[this.atomCount - 1] = paramSoundSchedulerAtom;
/*      */       
/*  631 */       this.duration = paramSoundSchedulerAtom.sampleLength;
/*      */     } else {
/*      */       
/*  634 */       if (this.atomCount == 0) {
/*      */         return;
/*      */       }
/*      */       
/*  638 */       boolean bool = false;
/*      */       byte b1;
/*  640 */       for (b1 = 0; b1 < this.atomCount; b1++) {
/*  641 */         if (paramSoundSchedulerAtom == this.loadedAtoms[b1]) {
/*  642 */           bool = true;
/*      */         }
/*      */       } 
/*      */       
/*  646 */       if (!bool) {
/*      */         return;
/*      */       }
/*      */       
/*  650 */       for (byte b2 = b1; b2 < this.atomCount; b2++) {
/*  651 */         this.loadedAtoms[b2] = this.loadedAtoms[b2 + 1];
/*      */       }
/*  653 */       this.atomCount--;
/*  654 */       if (this.atomCount == 0) {
/*  655 */         this.duration = -1L;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isReady() {
/*  680 */     boolean bool = true;
/*  681 */     for (byte b = 0; b < this.atomCount; b++) {
/*  682 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  683 */       if (soundSchedulerAtom != null && soundSchedulerAtom.soundScheduler != null)
/*      */       {
/*      */         
/*  686 */         if (soundSchedulerAtom.loadStatus == 2) {
/*  687 */           bool = true;
/*      */         }
/*      */         else {
/*      */           
/*  691 */           return false;
/*      */         }  } 
/*  693 */     }  if (bool) {
/*  694 */       return true;
/*      */     }
/*      */     
/*  697 */     return false;
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
/*      */   boolean isReady(View paramView) {
/*  722 */     if (paramView == null)
/*  723 */       return false; 
/*  724 */     for (byte b = 0; b < this.atomCount; b++) {
/*  725 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  726 */       if (soundSchedulerAtom != null && soundSchedulerAtom.soundScheduler != null)
/*      */       {
/*  728 */         if (soundSchedulerAtom.soundScheduler.view == paramView) {
/*  729 */           if (soundSchedulerAtom.loadStatus != 2) {
/*  730 */             return false;
/*      */           }
/*  732 */           return true;
/*      */         } 
/*      */       }
/*      */     } 
/*  736 */     return false;
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
/*      */   boolean isPlaying() {
/*  751 */     for (byte b = 0; b < this.atomCount; b++) {
/*  752 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  753 */       if (soundSchedulerAtom != null && soundSchedulerAtom.soundScheduler != null)
/*      */       {
/*  755 */         if (soundSchedulerAtom.status == 1) {
/*  756 */           return true;
/*      */         }
/*      */       }
/*      */     } 
/*      */     
/*  761 */     return false;
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
/*      */   boolean isPlaying(View paramView) {
/*  773 */     if (paramView == null)
/*  774 */       return false; 
/*  775 */     for (byte b = 0; b < this.atomCount; b++) {
/*  776 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  777 */       if (soundSchedulerAtom != null && soundSchedulerAtom.soundScheduler != null)
/*      */       {
/*  779 */         if (soundSchedulerAtom.soundScheduler.view == paramView) {
/*  780 */           if (soundSchedulerAtom.status == 1) {
/*  781 */             return true;
/*      */           }
/*  783 */           return false;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  788 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isPlayingSilently() {
/*  798 */     for (byte b = 0; b < this.atomCount; ) {
/*  799 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  800 */       if (soundSchedulerAtom == null || soundSchedulerAtom.soundScheduler == null) {
/*      */         b++; continue;
/*  802 */       }  if (soundSchedulerAtom.status == 2) {
/*  803 */         return true;
/*      */       }
/*  805 */       return false;
/*      */     } 
/*  807 */     return false;
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
/*      */   boolean isPlayingSilently(View paramView) {
/*  819 */     if (paramView == null)
/*  820 */       return false; 
/*  821 */     for (byte b = 0; b < this.atomCount; b++) {
/*  822 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  823 */       if (soundSchedulerAtom != null && soundSchedulerAtom.soundScheduler != null)
/*      */       {
/*  825 */         if (soundSchedulerAtom.soundScheduler.view == paramView) {
/*  826 */           if (soundSchedulerAtom.status == 2) {
/*  827 */             return true;
/*      */           }
/*  829 */           return false;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  834 */     return false;
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
/*      */   int getNumberOfChannelsUsed() {
/*  847 */     View view = this.universe.getCurrentView();
/*  848 */     if (view == null) {
/*  849 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  854 */     for (byte b = 0; b < this.atomCount; b++) {
/*  855 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  856 */       if (soundSchedulerAtom != null && soundSchedulerAtom.soundScheduler != null)
/*      */       {
/*  858 */         if (soundSchedulerAtom.soundScheduler.view == view)
/*  859 */           return soundSchedulerAtom.numberChannels; 
/*      */       }
/*      */     } 
/*  862 */     return 0;
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
/*      */   int getNumberOfChannelsUsed(View paramView) {
/*  876 */     if (paramView == null) {
/*  877 */       return 0;
/*      */     }
/*  879 */     for (byte b = 0; b < this.atomCount; b++) {
/*  880 */       SoundSchedulerAtom soundSchedulerAtom = this.loadedAtoms[b];
/*  881 */       if (soundSchedulerAtom != null && soundSchedulerAtom.soundScheduler != null)
/*      */       {
/*  883 */         if (soundSchedulerAtom.soundScheduler.view == paramView)
/*  884 */           return soundSchedulerAtom.numberChannels; 
/*      */       }
/*      */     } 
/*  887 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMute(boolean paramBoolean) {
/*  897 */     this.mute = paramBoolean;
/*  898 */     dispatchAttribChange(64, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*  899 */     if (this.source != null && this.source.isLive()) {
/*  900 */       notifySceneGraphChanged(false);
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
/*  912 */   boolean getMute() { return this.mute; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPause(boolean paramBoolean) {
/*  921 */     this.pause = paramBoolean;
/*  922 */     dispatchAttribChange(128, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*  923 */     if (this.source != null && this.source.isLive()) {
/*  924 */       notifySceneGraphChanged(false);
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
/*  936 */   boolean getPause() { return this.pause; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  944 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  951 */   boolean getInImmCtx() { return this.inImmCtx; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   SoundRetained getMirrorSound(HashKey paramHashKey) {
/*  961 */     if (this.inSharedGroup) {
/*  962 */       byte b; for (b = 0; b < this.numMirrorSounds; b++) {
/*  963 */         if ((this.mirrorSounds[b]).key.equals(paramHashKey)) {
/*  964 */           return this.mirrorSounds[b];
/*      */         }
/*      */       } 
/*  967 */       if (this.numMirrorSounds == this.mirrorSounds.length) {
/*  968 */         SoundRetained[] arrayOfSoundRetained = new SoundRetained[this.numMirrorSounds * 2];
/*  969 */         for (b = 0; b < this.numMirrorSounds; b++) {
/*  970 */           arrayOfSoundRetained[b] = this.mirrorSounds[b];
/*      */         }
/*  972 */         this.mirrorSounds = arrayOfSoundRetained;
/*      */       } 
/*      */       
/*  975 */       this.mirrorSounds[this.numMirrorSounds] = (SoundRetained)clone();
/*      */       
/*  977 */       (this.mirrorSounds[this.numMirrorSounds]).key = paramHashKey;
/*  978 */       (this.mirrorSounds[this.numMirrorSounds]).sgSound = this;
/*  979 */       return this.mirrorSounds[this.numMirrorSounds++];
/*      */     } 
/*  981 */     if (this.mirrorSounds[false] == null) {
/*      */       
/*  983 */       this.mirrorSounds[0] = (SoundRetained)clone();
/*  984 */       (this.mirrorSounds[0]).sgSound = this;
/*      */     } 
/*  986 */     return this.mirrorSounds[0];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initMirrorObject(SoundRetained paramSoundRetained) {
/*  993 */     Object object = null;
/*      */     
/*  995 */     paramSoundRetained.setSchedulingBounds(getSchedulingBounds());
/*  996 */     paramSoundRetained.setSchedulingBoundingLeaf(getSchedulingBoundingLeaf());
/*  997 */     paramSoundRetained.sgSound = this.sgSound;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1004 */     paramSoundRetained.inImmCtx = this.inImmCtx;
/* 1005 */     paramSoundRetained.setSoundData(getSoundData());
/*      */ 
/*      */ 
/*      */     
/* 1009 */     paramSoundRetained.parent = this.parent;
/* 1010 */     paramSoundRetained.inSharedGroup = false;
/* 1011 */     paramSoundRetained.locale = this.locale;
/* 1012 */     paramSoundRetained.parent = this.parent;
/* 1013 */     paramSoundRetained.localBounds = (Bounds)this.localBounds.clone();
/*      */     
/* 1015 */     paramSoundRetained.transformedRegion = null;
/* 1016 */     if (this.boundingLeaf != null) {
/* 1017 */       if (paramSoundRetained.boundingLeaf != null)
/* 1018 */         paramSoundRetained.boundingLeaf.removeUser(paramSoundRetained); 
/* 1019 */       paramSoundRetained.boundingLeaf = this.boundingLeaf.mirrorBoundingLeaf;
/*      */       
/* 1021 */       paramSoundRetained.boundingLeaf.addUser(paramSoundRetained);
/* 1022 */       paramSoundRetained.transformedRegion = paramSoundRetained.boundingLeaf.transformedRegion;
/*      */     } else {
/*      */       
/* 1025 */       paramSoundRetained.boundingLeaf = null;
/*      */     } 
/*      */     
/* 1028 */     if (this.schedulingRegion != null) {
/* 1029 */       paramSoundRetained.schedulingRegion = (Bounds)this.schedulingRegion.clone();
/*      */       
/* 1031 */       if (paramSoundRetained.transformedRegion == null) {
/* 1032 */         paramSoundRetained.transformedRegion = (Bounds)paramSoundRetained.schedulingRegion.clone();
/* 1033 */         paramSoundRetained.transformedRegion.transform(paramSoundRetained.schedulingRegion, paramSoundRetained.getLastLocalToVworld());
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1039 */       paramSoundRetained.schedulingRegion = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(SetLiveState paramSetLiveState) {
/* 1050 */     if (this.inImmCtx) {
/* 1051 */       throw new IllegalSharingException(J3dI18N.getString("SoundRetained2"));
/*      */     }
/*      */     
/* 1054 */     super.setLive(paramSetLiveState);
/* 1055 */     if (this.inBackgroundGroup) {
/* 1056 */       throw new IllegalSceneGraphException(J3dI18N.getString("SoundRetained3"));
/*      */     }
/*      */ 
/*      */     
/* 1060 */     if (this.loadStatus == 1)
/*      */     {
/*      */       
/* 1063 */       dispatchStateChange(4, this.soundData);
/*      */     }
/*      */     
/* 1066 */     if (this.soundData != null) {
/* 1067 */       ((MediaContainerRetained)this.soundData.retained).setLive(this.inBackgroundGroup, paramSetLiveState.refCount);
/*      */     }
/*      */     
/* 1070 */     if (paramSetLiveState.inSharedGroup) {
/* 1071 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 1072 */         SoundRetained soundRetained = getMirrorSound(paramSetLiveState.keys[b]);
/* 1073 */         soundRetained.localToVworld = new Transform3D[1][];
/* 1074 */         soundRetained.localToVworldIndex = new int[1][];
/*      */         
/* 1076 */         int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*      */         
/* 1078 */         if (i < 0) {
/* 1079 */           System.err.println("SoundRetained : Can't find hashKey");
/*      */         }
/*      */         
/* 1082 */         soundRetained.localToVworld[0] = this.localToVworld[i];
/* 1083 */         soundRetained.localToVworldIndex[0] = this.localToVworldIndex[i];
/*      */ 
/*      */         
/* 1086 */         if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 1087 */           paramSetLiveState.viewScopedNodeList.add(soundRetained);
/* 1088 */           paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(b));
/*      */         } else {
/* 1090 */           paramSetLiveState.nodeList.add(soundRetained);
/*      */         } 
/*      */ 
/*      */         
/* 1094 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b] != null) {
/* 1095 */           paramSetLiveState.switchTargets[b].addNode(soundRetained, 3);
/*      */         }
/* 1097 */         soundRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(i);
/* 1098 */         if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null) {
/*      */           
/* 1100 */           paramSetLiveState.transformTargets[b].addNode(soundRetained, 3);
/* 1101 */           paramSetLiveState.notifyThreads |= 0x2000;
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1105 */       SoundRetained soundRetained = getMirrorSound(null);
/* 1106 */       soundRetained.localToVworld = new Transform3D[1][];
/* 1107 */       soundRetained.localToVworldIndex = new int[1][];
/* 1108 */       soundRetained.localToVworld[0] = this.localToVworld[0];
/* 1109 */       soundRetained.localToVworldIndex[0] = this.localToVworldIndex[0];
/*      */ 
/*      */       
/* 1112 */       if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 1113 */         paramSetLiveState.viewScopedNodeList.add(soundRetained);
/* 1114 */         paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*      */       } else {
/* 1116 */         paramSetLiveState.nodeList.add(soundRetained);
/*      */       } 
/*      */ 
/*      */       
/* 1120 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null) {
/* 1121 */         paramSetLiveState.switchTargets[0].addNode(soundRetained, 3);
/*      */       }
/* 1123 */       soundRetained.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/* 1124 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/*      */         
/* 1126 */         paramSetLiveState.transformTargets[0].addNode(soundRetained, 3);
/* 1127 */         paramSetLiveState.notifyThreads |= 0x2000;
/*      */       } 
/*      */     } 
/* 1130 */     dispatchStateChange(1, this.soundData);
/* 1131 */     paramSetLiveState.notifyThreads |= 0x202;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void clearLive(SetLiveState paramSetLiveState) {
/* 1137 */     super.clearLive(paramSetLiveState);
/*      */ 
/*      */ 
/*      */     
/* 1141 */     if (paramSetLiveState.inSharedGroup) {
/* 1142 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 1143 */         SoundRetained soundRetained = getMirrorSound(paramSetLiveState.keys[b]);
/* 1144 */         if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[b] != null)
/*      */         {
/* 1146 */           paramSetLiveState.switchTargets[b].addNode(soundRetained, 3);
/*      */         }
/* 1148 */         if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[b] != null) {
/* 1149 */           paramSetLiveState.transformTargets[b].addNode(soundRetained, 3);
/* 1150 */           paramSetLiveState.notifyThreads |= 0x2000;
/*      */         } 
/*      */ 
/*      */         
/* 1154 */         if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 1155 */           paramSetLiveState.viewScopedNodeList.add(soundRetained);
/* 1156 */           paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(b));
/*      */         } else {
/* 1158 */           paramSetLiveState.nodeList.add(soundRetained);
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1162 */       SoundRetained soundRetained = getMirrorSound(null);
/* 1163 */       if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*      */       {
/* 1165 */         paramSetLiveState.switchTargets[0].addNode(soundRetained, 3);
/*      */       }
/* 1167 */       if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/*      */         
/* 1169 */         paramSetLiveState.transformTargets[0].addNode(soundRetained, 3);
/* 1170 */         paramSetLiveState.notifyThreads |= 0x2000;
/*      */       } 
/*      */ 
/*      */       
/* 1174 */       if (paramSetLiveState.viewScopedNodeList != null && paramSetLiveState.viewLists != null) {
/* 1175 */         paramSetLiveState.viewScopedNodeList.add(soundRetained);
/* 1176 */         paramSetLiveState.scopedNodesViewList.add(paramSetLiveState.viewLists.get(0));
/*      */       } else {
/* 1178 */         paramSetLiveState.nodeList.add(soundRetained);
/*      */       } 
/*      */     } 
/* 1181 */     paramSetLiveState.notifyThreads |= 0x202;
/*      */     
/* 1183 */     if (this.soundData != null) {
/* 1184 */       ((MediaContainerRetained)this.soundData.retained).clearLive(paramSetLiveState.refCount);
/*      */     }
/*      */   }
/*      */   
/*      */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 1189 */     super.mergeTransform(paramTransformGroupRetained);
/* 1190 */     if (this.schedulingRegion != null) {
/* 1191 */       this.schedulingRegion.transform(paramTransformGroupRetained.transform);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1258 */     if (this.boundingLeaf == null && 
/* 1259 */       this.schedulingRegion != null) {
/* 1260 */       this.transformedRegion = this.schedulingRegion.copy(this.transformedRegion);
/* 1261 */       this.transformedRegion.transform(this.schedulingRegion, getLastLocalToVworld());
/*      */     } 
/*      */ 
/*      */     
/* 1265 */     dispatchStateChange(32768, null);
/*      */   }
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) {
/* 1283 */     if (paramHashKey == null) {
/* 1284 */       paramArrayList.add(this.mirrorSounds[0]);
/*      */     } else {
/*      */       
/* 1287 */       for (byte b = 0; b < this.numMirrorSounds; b++) {
/* 1288 */         if ((this.mirrorSounds[b]).key.equals(paramHashKey)) {
/* 1289 */           paramArrayList.add(this.mirrorSounds[b]);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\SoundRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */