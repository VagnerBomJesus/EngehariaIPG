/*     */ package com.sun.j3d.audioengines.javasound;
/*     */ 
/*     */ import com.sun.j3d.audioengines.AudioEngine3DL2;
/*     */ import javax.media.j3d.MediaContainer;
/*     */ import javax.media.j3d.PhysicalEnvironment;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Vector3d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JavaSoundMixer
/*     */   extends AudioEngine3DL2
/*     */ {
/*     */   static final boolean debugFlag = false;
/*     */   static final boolean internalErrors = false;
/*     */   static final int ADD_TO_LIST = 1;
/*     */   static final int SET_INTO_LIST = 2;
/*     */   
/*     */   void debugPrint(String paramString) {}
/*     */   
/*     */   void debugPrintln(String paramString) {}
/*     */   
/*  88 */   JSAuralParameters auralParams = null;
/*     */ 
/*     */ 
/*     */   
/*  92 */   JSThread thread = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected float deviceGain = 1.0F;
/*     */   
/*     */   protected static final int NOT_PAUSED = 0;
/*     */   protected static final int PAUSE_PENDING = 1;
/*     */   protected static final int PAUSED = 2;
/*     */   protected static final int RESUME_PENDING = 3;
/* 103 */   protected int pause = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JavaSoundMixer(PhysicalEnvironment paramPhysicalEnvironment) {
/* 111 */     super(paramPhysicalEnvironment);
/* 112 */     this.thread = new JSThread(Thread.currentThread().getThreadGroup(), this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalChannels() {
/* 122 */     if (this.thread != null) {
/* 123 */       return this.thread.getTotalChannels();
/*     */     }
/* 125 */     return 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean initialize() {
/* 134 */     if (this.thread == null) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     this.thread.initialize();
/* 139 */     this.auralParams = new JSAuralParameters();
/*     */ 
/*     */     
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean close() {
/* 151 */     if (this.thread == null)
/* 152 */       return false; 
/* 153 */     if (this.thread.close())
/*     */     {
/*     */       
/* 156 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 161 */     return false;
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
/*     */   public int prepareSound(int paramInt, MediaContainer paramMediaContainer) {
/* 183 */     int i = -1;
/* 184 */     boolean bool = true;
/* 185 */     if (paramMediaContainer == null)
/* 186 */       return -1; 
/* 187 */     synchronized (this.samples) {
/*     */       
/* 189 */       int j = this.samples.size();
/* 190 */       i = j;
/* 191 */       this.samples.ensureCapacity(i + 1);
/* 192 */       boolean bool1 = false;
/*     */       
/* 194 */       if (paramInt == 3) {
/*     */ 
/*     */         
/* 197 */         JSDirectionalSample jSDirectionalSample = new JSDirectionalSample();
/* 198 */         bool1 = jSDirectionalSample.load(paramMediaContainer);
/* 199 */         if (bool1)
/* 200 */           return -1; 
/* 201 */         if (bool == 2) {
/* 202 */           this.samples.set(i, jSDirectionalSample);
/*     */         } else {
/* 204 */           this.samples.add(i, jSDirectionalSample);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 209 */         jSDirectionalSample.setDirtyFlags(65535);
/* 210 */         jSDirectionalSample.setSoundType(paramInt);
/* 211 */         jSDirectionalSample.setSoundData(paramMediaContainer);
/*     */       
/*     */       }
/* 214 */       else if (paramInt == 2) {
/*     */ 
/*     */         
/* 217 */         JSPositionalSample jSPositionalSample = new JSPositionalSample();
/* 218 */         bool1 = jSPositionalSample.load(paramMediaContainer);
/* 219 */         if (bool1)
/* 220 */           return -1; 
/* 221 */         if (bool == 2) {
/* 222 */           this.samples.set(i, jSPositionalSample);
/*     */         } else {
/* 224 */           this.samples.add(i, jSPositionalSample);
/* 225 */         }  jSPositionalSample.setDirtyFlags(65535);
/* 226 */         jSPositionalSample.setSoundType(paramInt);
/* 227 */         jSPositionalSample.setSoundData(paramMediaContainer);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 232 */         JSSample jSSample = null;
/* 233 */         jSSample = new JSSample();
/* 234 */         bool1 = jSSample.load(paramMediaContainer);
/* 235 */         if (bool1)
/* 236 */           return -1; 
/* 237 */         if (bool == 2) {
/* 238 */           this.samples.set(i, jSSample);
/*     */         } else {
/* 240 */           this.samples.add(i, jSSample);
/* 241 */         }  jSSample.setDirtyFlags(65535);
/* 242 */         jSSample.setSoundType(paramInt);
/* 243 */         jSSample.setSoundData(paramMediaContainer);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearSound(int paramInt) {
/* 260 */     JSSample jSSample = null;
/* 261 */     if ((jSSample = (JSSample)getSample(paramInt)) == null)
/*     */       return; 
/* 263 */     jSSample.clear();
/* 264 */     synchronized (this.samples) {
/* 265 */       this.samples.set(paramInt, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVworldXfrm(int paramInt, Transform3D paramTransform3D) {
/* 276 */     super.setVworldXfrm(paramInt, paramTransform3D);
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
/* 290 */     JSSample jSSample = null;
/* 291 */     if ((jSSample = (JSSample)getSample(paramInt)) == null)
/*     */       return; 
/* 293 */     int i = jSSample.getSoundType();
/*     */     
/* 295 */     if (i == 3) {
/* 296 */       JSDirectionalSample jSDirectionalSample = null;
/* 297 */       if ((jSDirectionalSample = (JSDirectionalSample)getSample(paramInt)) == null)
/*     */         return; 
/* 299 */       jSDirectionalSample.setXformedDirection();
/* 300 */       jSDirectionalSample.setXformedPosition();
/*     */       
/* 302 */       jSDirectionalSample.setVWrldXfrmFlag(true);
/*     */     }
/* 304 */     else if (i == 2) {
/* 305 */       JSPositionalSample jSPositionalSample = null;
/* 306 */       if ((jSPositionalSample = (JSPositionalSample)getSample(paramInt)) == null)
/*     */         return; 
/* 308 */       jSPositionalSample.setXformedPosition();
/*     */       
/* 310 */       jSPositionalSample.setVWrldXfrmFlag(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(int paramInt, Point3d paramPoint3d) {
/* 320 */     super.setPosition(paramInt, paramPoint3d);
/* 321 */     JSPositionalSample jSPositionalSample = null;
/* 322 */     if ((jSPositionalSample = (JSPositionalSample)getSample(paramInt)) == null)
/*     */       return; 
/* 324 */     int i = jSPositionalSample.getSoundType();
/* 325 */     if (i == 2 || i == 3)
/*     */     {
/* 327 */       jSPositionalSample.setXformedPosition();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(int paramInt, Vector3d paramVector3d) {
/* 338 */     super.setDirection(paramInt, paramVector3d);
/* 339 */     JSDirectionalSample jSDirectionalSample = null;
/* 340 */     if ((jSDirectionalSample = (JSDirectionalSample)getSample(paramInt)) == null)
/*     */       return; 
/* 342 */     int i = jSDirectionalSample.getSoundType();
/* 343 */     if (i == 3) {
/* 344 */       jSDirectionalSample.setXformedDirection();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReflectionCoefficient(float paramFloat) {
/* 353 */     super.setReflectionCoefficient(paramFloat);
/* 354 */     this.auralParams.reverbDirty |= JSAuralParameters.REFLECTION_COEFF_CHANGED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReverbDelay(float paramFloat) {
/* 362 */     super.setReverbDelay(paramFloat);
/* 363 */     this.auralParams.reverbDirty |= JSAuralParameters.REVERB_DELAY_CHANGED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReverbOrder(int paramInt) {
/* 371 */     super.setReverbOrder(paramInt);
/* 372 */     this.auralParams.reverbDirty |= JSAuralParameters.REVERB_ORDER_CHANGED;
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
/*     */   public int startSample(int paramInt) {
/* 391 */     JSSample jSSample = null;
/* 392 */     if ((jSSample = (JSSample)getSample(paramInt)) == null || this.thread == null)
/*     */     {
/* 394 */       return -1;
/*     */     }
/* 396 */     int i = jSSample.getSoundType();
/* 397 */     boolean bool1 = jSSample.getMuteFlag();
/* 398 */     if (bool1) {
/*     */ 
/*     */       
/* 401 */       this.thread.muteSample(jSSample);
/* 402 */       if (i != 1) {
/* 403 */         setFilter(paramInt, false, -1.0F);
/*     */       }
/*     */     } else {
/* 406 */       jSSample.render(jSSample.getDirtyFlags(), getView(), this.auralParams);
/* 407 */       scaleSampleRate(paramInt, jSSample.rateRatio);
/*     */       
/* 409 */       if (i != 1) {
/* 410 */         setFilter(paramInt, jSSample.getFilterFlag(), jSSample.getFilterFreq());
/*     */       }
/*     */     } 
/*     */     
/* 414 */     boolean bool2 = this.thread.startSample(jSSample);
/*     */     
/* 416 */     jSSample.channel.startSample(jSSample.getLoopCount(), jSSample.getGain(), 0);
/*     */     
/* 418 */     if (!bool2)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 423 */       return -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     if (!bool1) {
/* 432 */       if (this.auralParams.reverbDirty > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 440 */         float f = this.auralParams.reverbDelay * this.auralParams.rolloff;
/* 441 */         calcReverb(jSSample);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 446 */       setReverb(jSSample);
/*     */     } 
/* 448 */     return paramInt;
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
/*     */   public int stopSample(int paramInt) {
/* 460 */     JSSample jSSample = null;
/* 461 */     if ((jSSample = (JSSample)getSample(paramInt)) == null) {
/* 462 */       return -1;
/*     */     }
/* 464 */     int i = jSSample.getDataType();
/* 465 */     int j = jSSample.getSoundType();
/*     */     
/* 467 */     boolean bool = true;
/* 468 */     bool = this.thread.stopSample(jSSample);
/*     */     
/* 470 */     jSSample.channel.stopSample();
/*     */     
/* 472 */     if (!bool)
/*     */     {
/*     */ 
/*     */       
/* 476 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 480 */     jSSample.reset();
/*     */ 
/*     */ 
/*     */     
/* 484 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pauseSample(int paramInt) {
/* 494 */     JSSample jSSample = null;
/* 495 */     if ((jSSample = (JSSample)getSample(paramInt)) == null) {
/*     */       return;
/*     */     }
/* 498 */     this.thread.pauseSample(jSSample);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unpauseSample(int paramInt) {
/* 507 */     JSSample jSSample = null;
/* 508 */     if ((jSSample = (JSSample)getSample(paramInt)) == null)
/*     */       return; 
/* 510 */     this.thread.unpauseSample(jSSample);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSample(int paramInt) {
/* 521 */     JSSample jSSample = null;
/* 522 */     if ((jSSample = (JSSample)getSample(paramInt)) == null || this.thread == null) {
/*     */       return;
/*     */     }
/*     */     
/* 526 */     int i = jSSample.getSoundType();
/* 527 */     boolean bool = jSSample.getMuteFlag();
/*     */     
/* 529 */     if (bool) {
/* 530 */       if (i != 1)
/* 531 */         setFilter(paramInt, false, -1.0F); 
/* 532 */       this.thread.muteSample(jSSample);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 538 */       if (this.auralParams.reverbDirty > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 545 */         float f1 = this.auralParams.reverbDelay * this.auralParams.rolloff;
/* 546 */         calcReverb(jSSample);
/*     */       } 
/*     */ 
/*     */       
/* 550 */       setReverb(jSSample);
/*     */ 
/*     */       
/* 553 */       float f = 0.0F;
/* 554 */       if (!bool && this.auralParams.reverbFlag) {
/* 555 */         f = jSSample.getGain() * this.auralParams.reflectionCoefficient;
/*     */       }
/*     */       
/* 558 */       jSSample.render(jSSample.getDirtyFlags(), getView(), this.auralParams);
/*     */ 
/*     */       
/* 561 */       if (i != 1)
/* 562 */         setFilter(paramInt, jSSample.getFilterFlag(), jSSample.getFilterFreq()); 
/* 563 */       this.thread.setSampleGain(jSSample, this.auralParams);
/* 564 */       this.thread.setSampleRate(jSSample, this.auralParams);
/* 565 */       this.thread.setSampleDelay(jSSample, this.auralParams);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void muteSample(int paramInt) {
/* 574 */     JSSample jSSample = null;
/* 575 */     if ((jSSample = (JSSample)getSample(paramInt)) == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 580 */     jSSample.setMuteFlag(true);
/* 581 */     this.thread.muteSample(jSSample);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unmuteSample(int paramInt) {
/* 589 */     JSSample jSSample = null;
/* 590 */     if ((jSSample = (JSSample)getSample(paramInt)) == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 595 */     jSSample.setMuteFlag(false);
/*     */ 
/*     */ 
/*     */     
/* 599 */     this.auralParams.reverbDirty = 65535;
/* 600 */     jSSample.setDirtyFlags(65535);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 605 */     this.thread.unmuteSample(jSSample);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSampleDuration(int paramInt) {
/*     */     long l;
/* 613 */     JSSample jSSample = null;
/* 614 */     if ((jSSample = (JSSample)getSample(paramInt)) == null) {
/* 615 */       return -1L;
/*     */     }
/*     */     
/* 618 */     if (jSSample != null) {
/* 619 */       l = jSSample.getDuration();
/*     */     } else {
/* 621 */       l = -1L;
/*     */     } 
/*     */     
/* 624 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfChannelsUsed(int paramInt) {
/* 635 */     JSSample jSSample = null;
/* 636 */     if ((jSSample = (JSSample)getSample(paramInt)) == null) {
/* 637 */       return 0;
/*     */     }
/* 639 */     return getNumberOfChannelsUsed(paramInt, jSSample.getMuteFlag());
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
/*     */   public int getNumberOfChannelsUsed(int paramInt, boolean paramBoolean) {
/* 657 */     JSSample jSSample = null;
/* 658 */     if ((jSSample = (JSSample)getSample(paramInt)) == null) {
/* 659 */       return 0;
/*     */     }
/* 661 */     int i = jSSample.getSoundType();
/* 662 */     int j = jSSample.getDataType();
/*     */ 
/*     */     
/* 665 */     if (j == 3 || j == 3)
/*     */     {
/* 667 */       return 1;
/*     */     }
/* 669 */     if (i == 1) {
/* 670 */       return 1;
/*     */     }
/* 672 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStartTime(int paramInt) {
/* 679 */     JSSample jSSample = null;
/* 680 */     if ((jSSample = (JSSample)getSample(paramInt)) == null)
/* 681 */       return 0L; 
/* 682 */     if (jSSample.channel == null)
/* 683 */       return 0L; 
/* 684 */     return jSSample.channel.startTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void scaleSampleRate(int paramInt, float paramFloat) {
/* 694 */     JSSample jSSample = null;
/* 695 */     if ((jSSample = (JSSample)getSample(paramInt)) == null || this.thread == null) {
/*     */       return;
/*     */     }
/* 698 */     int i = jSSample.getDataType();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 703 */     int j = jSSample.getSoundType();
/*     */     
/* 705 */     if (i == 1 || i == 2) {
/*     */       
/* 707 */       this.thread.setSampleRate(jSSample, paramFloat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 718 */     else if (i == 3 || i == 3) {
/*     */       
/* 720 */       this.thread.setSampleRate(jSSample, paramFloat);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void calcReverb(JSSample paramJSSample) {
/* 745 */     int i = paramJSSample.getDataType();
/* 746 */     int j = paramJSSample.getSoundType();
/* 747 */     float f1 = this.auralParams.decayTime;
/* 748 */     float f2 = this.auralParams.reverbDelay * this.auralParams.rolloff;
/* 749 */     float f3 = this.auralParams.reflectionCoefficient;
/* 750 */     int k = this.auralParams.reverbOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 755 */     if (this.auralParams.reflectionCoefficient == 0.0F || this.auralParams.reverbCoefficient == 0.0F) {
/*     */       
/* 757 */       this.auralParams.reverbFlag = false;
/*     */     } else {
/* 759 */       this.auralParams.reverbFlag = true;
/* 760 */       if (k > 0) {
/*     */         
/* 762 */         float f = k * f2;
/* 763 */         if (f < f1)
/* 764 */           f1 = f; 
/*     */       } 
/* 766 */       if (f2 < 100.0F) {
/*     */         
/* 768 */         if (f1 <= 1500.0F) {
/* 769 */           this.auralParams.reverbType = 2;
/*     */         } else {
/* 771 */           this.auralParams.reverbType = 4;
/*     */         } 
/* 773 */       } else if (f2 < 500.0F) {
/*     */         
/* 775 */         if (f1 <= 1500.0F) {
/* 776 */           this.auralParams.reverbType = 3;
/*     */         } else {
/* 778 */           this.auralParams.reverbType = 6;
/*     */         }
/*     */       
/*     */       }
/* 782 */       else if (f1 <= 1500.0F) {
/* 783 */         this.auralParams.reverbType = 6;
/*     */       } else {
/* 785 */         this.auralParams.reverbType = 5;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 793 */     this.auralParams.reverbDirty = 0;
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
/*     */   void setReverb(JSSample paramJSSample) {
/* 805 */     int i = paramJSSample.getSoundType();
/* 806 */     int j = paramJSSample.getDataType();
/*     */ 
/*     */     
/* 809 */     if (i == 3 || i == 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 814 */       if (paramJSSample == null)
/*     */         return; 
/* 816 */       JSPositionalSample jSPositionalSample = (JSPositionalSample)paramJSSample;
/* 817 */       if (jSPositionalSample.channel == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 824 */       if (j == 1) {
/* 825 */         JSStream jSStream = (JSStream)jSPositionalSample.channel;
/* 826 */         jSStream.setSampleReverb(this.auralParams.reverbType, this.auralParams.reverbFlag);
/*     */       }
/* 828 */       else if (j == 2) {
/* 829 */         JSClip jSClip = (JSClip)jSPositionalSample.channel;
/* 830 */         jSClip.setSampleReverb(this.auralParams.reverbType, this.auralParams.reverbFlag);
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
/*     */   public void setLoop(int paramInt1, int paramInt2) {
/* 850 */     JSSample jSSample = null;
/* 851 */     if ((jSSample = (JSSample)getSample(paramInt1)) == null)
/*     */       return; 
/* 853 */     int i = jSSample.getDataType();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 858 */     if (i == 1 || i == 2)
/*     */     {
/* 860 */       if (paramInt2 == -1)
/*     */       {
/* 862 */         paramInt2 = 134217727;
/*     */       }
/*     */     }
/* 865 */     super.setLoop(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFilter(int paramInt, boolean paramBoolean, float paramFloat) {
/* 875 */     JSPositionalSample jSPositionalSample = null;
/* 876 */     if ((jSPositionalSample = (JSPositionalSample)getSample(paramInt)) == null)
/*     */       return; 
/* 878 */     if (jSPositionalSample.channel == null)
/*     */       return; 
/* 880 */     int i = jSPositionalSample.getDataType();
/*     */ 
/*     */     
/* 883 */     if (i == 3 || i == 3) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 892 */     if (i == 2) {
/* 893 */       JSClip jSClip = (JSClip)jSPositionalSample.channel;
/* 894 */       jSClip.setSampleFiltering(paramBoolean, paramFloat);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 900 */       JSStream jSStream = (JSStream)jSPositionalSample.channel;
/* 901 */       jSStream.setSampleFiltering(paramBoolean, paramFloat);
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
/*     */   public void setGain(float paramFloat) {
/* 919 */     float f1 = this.deviceGain;
/* 920 */     float f2 = paramFloat / f1;
/*     */     
/* 922 */     this.deviceGain = paramFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRateScaleFactor(int paramInt, float paramFloat) {
/* 931 */     JSSample jSSample = null;
/* 932 */     if ((jSSample = (JSSample)getSample(paramInt)) == null)
/*     */       return; 
/* 934 */     jSSample.setRateScaleFactor(paramFloat);
/* 935 */     scaleSampleRate(paramInt, paramFloat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 945 */   public void pause() { this.pause = 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 955 */   public void resume() { this.pause = 3; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\audioengines\javasound\JavaSoundMixer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */