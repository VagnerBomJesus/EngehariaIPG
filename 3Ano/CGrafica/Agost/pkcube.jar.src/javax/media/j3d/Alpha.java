/*      */ package javax.media.j3d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Alpha
/*      */   extends NodeComponent
/*      */ {
/*      */   private int loopCount;
/*      */   public static final int INCREASING_ENABLE = 1;
/*      */   public static final int DECREASING_ENABLE = 2;
/*      */   private int mode;
/*      */   private float triggerTime;
/*      */   private float phaseDelay;
/*      */   private float increasingAlpha;
/*      */   private long increasingAlphaRamp;
/*      */   private float incAlphaRampInternal;
/*      */   private float alphaAtOne;
/*      */   private float decreasingAlpha;
/*      */   private long decreasingAlphaRamp;
/*      */   private float decAlphaRampInternal;
/*      */   private float alphaAtZero;
/*  124 */   private long pauseTime = 0L;
/*      */ 
/*      */   
/*      */   private boolean paused = false;
/*      */   
/*      */   private float stopTime;
/*      */   
/*  131 */   private long startTime = MasterControl.systemStartTime;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alpha() {
/*  153 */     this.loopCount = -1;
/*  154 */     this.mode = 1;
/*  155 */     this.increasingAlpha = 1.0F;
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
/*      */   public Alpha(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8) {
/*  214 */     this.loopCount = paramInt1;
/*  215 */     this.mode = paramInt2;
/*  216 */     this.triggerTime = (float)paramLong1 * 0.001F;
/*  217 */     this.phaseDelay = (float)paramLong2 * 0.001F;
/*      */     
/*  219 */     this.increasingAlpha = (float)paramLong3 * 0.001F;
/*  220 */     this.alphaAtOne = (float)paramLong5 * 0.001F;
/*  221 */     this.increasingAlphaRamp = paramLong4;
/*  222 */     this.incAlphaRampInternal = (float)paramLong4 * 0.001F;
/*  223 */     if (this.incAlphaRampInternal > 0.5F * this.increasingAlpha) {
/*  224 */       this.incAlphaRampInternal = 0.5F * this.increasingAlpha;
/*      */     }
/*      */     
/*  227 */     this.decreasingAlpha = (float)paramLong6 * 0.001F;
/*  228 */     this.alphaAtZero = (float)paramLong8 * 0.001F;
/*  229 */     this.decreasingAlphaRamp = paramLong7;
/*  230 */     this.decAlphaRampInternal = (float)paramLong7 * 0.001F;
/*  231 */     if (this.decAlphaRampInternal > 0.5F * this.decreasingAlpha) {
/*  232 */       this.decAlphaRampInternal = 0.5F * this.decreasingAlpha;
/*      */     }
/*  234 */     computeStopTime();
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
/*  264 */   public Alpha(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5) { this(paramInt, 1, paramLong1, paramLong2, paramLong3, paramLong4, paramLong5, 0L, 0L, 0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alpha(int paramInt, long paramLong) {
/*  282 */     this.mode = 1;
/*  283 */     this.increasingAlpha = (float)paramLong * 0.001F;
/*  284 */     this.loopCount = paramInt;
/*      */     
/*  286 */     if (paramInt >= 0) {
/*  287 */       this.stopTime = paramInt * this.increasingAlpha;
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
/*  302 */   public void pause() { pause(J3dClock.currentTimeMillis()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pause(long paramLong) {
/*  324 */     if (paramLong <= 0L) {
/*  325 */       throw new IllegalArgumentException(J3dI18N.getString("Alpha0"));
/*      */     }
/*      */     
/*  328 */     this.paused = true;
/*  329 */     this.pauseTime = paramLong;
/*  330 */     VirtualUniverse.mc.sendRunMessage(16);
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
/*  351 */   public void resume() { resume(J3dClock.currentTimeMillis()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resume(long paramLong) {
/*  374 */     if (paramLong <= 0L) {
/*  375 */       throw new IllegalArgumentException(J3dI18N.getString("Alpha0"));
/*      */     }
/*      */     
/*  378 */     if (this.paused) {
/*  379 */       long l = this.startTime + paramLong - this.pauseTime;
/*  380 */       this.paused = false;
/*  381 */       this.pauseTime = 0L;
/*  382 */       setStartTime(l);
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
/*  393 */   public boolean isPaused() { return this.paused; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  403 */   public long getPauseTime() { return this.pauseTime; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float value() {
/*  422 */     long l = this.paused ? this.pauseTime : J3dClock.currentTimeMillis();
/*  423 */     return value(l);
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
/*      */   public float value(long paramLong) {
/*  440 */     float f = (float)(paramLong - this.startTime) * 0.001F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  460 */     if ((this.mode & true) != 0 && (this.mode & 0x2) == 0) {
/*      */       float f1;
/*      */       
/*  463 */       if (f <= this.triggerTime + this.phaseDelay) {
/*  464 */         return 0.0F;
/*      */       }
/*  466 */       if (this.loopCount != -1 && f >= this.stopTime) {
/*  467 */         return 1.0F;
/*      */       }
/*      */       
/*  470 */       if (this.incAlphaRampInternal == 0.0F) {
/*      */         
/*  472 */         f1 = mfmod(f - this.triggerTime - this.phaseDelay + 6.0F * (this.increasingAlpha + this.alphaAtOne), this.increasingAlpha + this.alphaAtOne) / this.increasingAlpha;
/*      */ 
/*      */ 
/*      */         
/*  476 */         if (f1 > 1.0F) f1 = 1.0F; 
/*  477 */         return f1;
/*      */       } 
/*      */ 
/*      */       
/*  481 */       float f4 = this.incAlphaRampInternal;
/*      */       
/*  483 */       float f3 = mfmod(f - this.triggerTime - this.phaseDelay + 6.0F * (this.increasingAlpha + this.alphaAtOne), this.increasingAlpha + this.alphaAtOne);
/*      */ 
/*      */       
/*  486 */       if (f3 >= this.increasingAlpha) return 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  495 */       float f2 = 1.0F / (this.increasingAlpha * f4 - f4 * f4);
/*      */ 
/*      */       
/*  498 */       if (f3 < f4) {
/*  499 */         f1 = 0.5F * f2 * f3 * f3;
/*  500 */       } else if (f3 < this.increasingAlpha - f4) {
/*  501 */         f1 = 0.5F * f2 * f4 * f4 + (f3 - f4) * f2 * f4;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  506 */         f1 = f2 * f4 * f4 + (this.increasingAlpha - 2.0F * f4) * f2 * f4 - 0.5F * f2 * (this.increasingAlpha - f3) * (this.increasingAlpha - f3);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  512 */       return f1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  519 */     if ((this.mode & true) == 0 && (this.mode & 0x2) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  530 */       if (f <= this.triggerTime + this.phaseDelay) {
/*  531 */         return 1.0F;
/*      */       }
/*  533 */       if (this.loopCount != -1 && f >= this.stopTime) {
/*  534 */         return 0.0F;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  539 */       if (this.decAlphaRampInternal == 0.0F) {
/*  540 */         null = mfmod(f - this.triggerTime - this.phaseDelay + 6.0F * (this.decreasingAlpha + this.alphaAtZero), this.decreasingAlpha + this.alphaAtZero) / this.decreasingAlpha;
/*      */ 
/*      */ 
/*      */         
/*  544 */         if (null > 1.0F) return 0.0F; 
/*  545 */         return 1.0F - null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  550 */       float f3 = this.decAlphaRampInternal;
/*      */       
/*  552 */       float f2 = mfmod(f - this.triggerTime - this.phaseDelay + 6.0F * (this.decreasingAlpha + this.alphaAtZero), this.decreasingAlpha + this.alphaAtZero);
/*      */ 
/*      */       
/*  555 */       if (f2 >= this.decreasingAlpha) return 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  564 */       float f1 = 1.0F / (this.decreasingAlpha * f3 - f3 * f3);
/*      */ 
/*      */       
/*  567 */       if (f2 < f3) {
/*  568 */         null = 0.5F * f1 * f2 * f2;
/*  569 */       } else if (f2 < this.decreasingAlpha - f3) {
/*  570 */         null = 0.5F * f1 * f3 * f3 + (f2 - f3) * f1 * f3;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  575 */         null = f1 * f3 * f3 + (this.decreasingAlpha - 2.0F * f3) * f1 * f3 - 0.5F * f1 * (this.decreasingAlpha - f2) * (this.decreasingAlpha - f2);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  581 */       return 1.0F - null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  588 */     if ((this.mode & true) != 0 && (this.mode & 0x2) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  603 */       if (f <= this.triggerTime + this.phaseDelay) {
/*  604 */         return 0.0F;
/*      */       }
/*  606 */       if (this.loopCount != -1 && f >= this.stopTime) {
/*  607 */         return 0.0F;
/*      */       }
/*      */       
/*  610 */       if (this.incAlphaRampInternal == 0.0F && this.decAlphaRampInternal == 0.0F) {
/*      */         
/*  612 */         float f4 = mfmod(f - this.triggerTime - this.phaseDelay + 6.0F * (this.increasingAlpha + this.alphaAtOne + this.decreasingAlpha + this.alphaAtZero), this.increasingAlpha + this.alphaAtOne + this.decreasingAlpha + this.alphaAtZero);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  617 */         null = f4 / this.increasingAlpha;
/*  618 */         if (null < 1.0F) return null;
/*      */         
/*  620 */         f4 -= this.increasingAlpha;
/*  621 */         if (f4 < this.alphaAtOne) return 1.0F;
/*      */         
/*  623 */         f4 -= this.alphaAtOne;
/*  624 */         null = f4 / this.decreasingAlpha;
/*  625 */         if (null < 1.0F) { null = 1.0F - null; }
/*  626 */         else { null = 0.0F; }
/*  627 */          return null;
/*      */       } 
/*      */ 
/*      */       
/*  631 */       float f3 = this.incAlphaRampInternal;
/*      */ 
/*      */       
/*  634 */       if (f3 == 0.0F) {
/*  635 */         f3 = 1.0E-5F;
/*      */       }
/*  637 */       float f2 = mfmod(f - this.triggerTime - this.phaseDelay + 6.0F * (this.increasingAlpha + this.alphaAtOne + this.decreasingAlpha + this.alphaAtZero), this.increasingAlpha + this.alphaAtOne + this.decreasingAlpha + this.alphaAtZero);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  642 */       if (f2 <= this.increasingAlpha) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  651 */         float f4 = 1.0F / (this.increasingAlpha * f3 - f3 * f3);
/*      */ 
/*      */         
/*  654 */         if (f2 < f3) {
/*  655 */           null = 0.5F * f4 * f2 * f2;
/*  656 */         } else if (f2 < this.increasingAlpha - f3) {
/*  657 */           null = 0.5F * f4 * f3 * f3 + (f2 - f3) * f4 * f3;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  662 */           null = f4 * f3 * f3 + (this.increasingAlpha - 2.0F * f3) * f4 * f3 - 0.5F * f4 * (this.increasingAlpha - f2) * (this.increasingAlpha - f2);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  668 */         return null;
/*      */       } 
/*  670 */       if (f2 <= this.increasingAlpha + this.alphaAtOne) {
/*  671 */         return 1.0F;
/*      */       }
/*  673 */       if (f2 >= this.increasingAlpha + this.alphaAtOne + this.decreasingAlpha) {
/*  674 */         return 0.0F;
/*      */       }
/*      */       
/*  677 */       f2 -= this.increasingAlpha + this.alphaAtOne;
/*      */       
/*  679 */       f3 = this.decAlphaRampInternal;
/*      */ 
/*      */       
/*  682 */       if (f3 == 0.0F) {
/*  683 */         f3 = 1.0E-5F;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  692 */       float f1 = 1.0F / (this.decreasingAlpha * f3 - f3 * f3);
/*      */ 
/*      */       
/*  695 */       if (f2 < f3) {
/*  696 */         null = 0.5F * f1 * f2 * f2;
/*  697 */       } else if (f2 < this.decreasingAlpha - f3) {
/*  698 */         null = 0.5F * f1 * f3 * f3 + (f2 - f3) * f1 * f3;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  703 */         null = f1 * f3 * f3 + (this.decreasingAlpha - 2.0F * f3) * f1 * f3 - 0.5F * f1 * (this.decreasingAlpha - f2) * (this.decreasingAlpha - f2);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  710 */       return 1.0F - null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  715 */     return 0.0F;
/*      */   }
/*      */   
/*      */   float mfmod(float paramFloat1, float paramFloat2) {
/*  719 */     float f2 = paramFloat1, f3 = paramFloat2;
/*      */     
/*  721 */     if (f3 < 0.0F) f3 = -f3; 
/*  722 */     if (f2 < 0.0F) f2 = -f2;
/*      */     
/*  724 */     int i = (int)(f2 / f3);
/*  725 */     float f1 = f2 - i * f3;
/*      */     
/*  727 */     if (paramFloat1 < 0.0F) return paramFloat2 - f1; 
/*  728 */     return f1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  738 */   public long getStartTime() { return this.startTime; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStartTime(long paramLong) {
/*  749 */     this.startTime = paramLong;
/*      */ 
/*      */     
/*  752 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  760 */   public int getLoopCount() { return this.loopCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLoopCount(int paramInt) {
/*  768 */     this.loopCount = paramInt;
/*  769 */     computeStopTime();
/*  770 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  779 */   public int getMode() { return this.mode; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMode(int paramInt) {
/*  795 */     this.mode = paramInt;
/*  796 */     computeStopTime();
/*  797 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  805 */   public long getTriggerTime() { return (long)(this.triggerTime * 1000.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTriggerTime(long paramLong) {
/*  813 */     this.triggerTime = (float)paramLong * 0.001F;
/*  814 */     computeStopTime();
/*  815 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  823 */   public long getPhaseDelayDuration() { return (long)(this.phaseDelay * 1000.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPhaseDelayDuration(long paramLong) {
/*  832 */     this.phaseDelay = (float)paramLong * 0.001F;
/*  833 */     computeStopTime();
/*  834 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  842 */   public long getIncreasingAlphaDuration() { return (long)(this.increasingAlpha * 1000.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIncreasingAlphaDuration(long paramLong) {
/*  851 */     this.increasingAlpha = (float)paramLong * 0.001F;
/*  852 */     computeStopTime();
/*  853 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  861 */   public long getIncreasingAlphaRampDuration() { return this.increasingAlphaRamp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIncreasingAlphaRampDuration(long paramLong) {
/*  870 */     this.increasingAlphaRamp = paramLong;
/*  871 */     this.incAlphaRampInternal = (float)paramLong * 0.001F;
/*  872 */     if (this.incAlphaRampInternal > 0.5F * this.increasingAlpha) {
/*  873 */       this.incAlphaRampInternal = 0.5F * this.increasingAlpha;
/*      */     }
/*  875 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  883 */   public long getAlphaAtOneDuration() { return (long)(this.alphaAtOne * 1000.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlphaAtOneDuration(long paramLong) {
/*  892 */     this.alphaAtOne = (float)paramLong * 0.001F;
/*  893 */     computeStopTime();
/*  894 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  902 */   public long getDecreasingAlphaDuration() { return (long)(this.decreasingAlpha * 1000.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDecreasingAlphaDuration(long paramLong) {
/*  911 */     this.decreasingAlpha = (float)paramLong * 0.001F;
/*  912 */     computeStopTime();
/*  913 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  921 */   public long getDecreasingAlphaRampDuration() { return this.decreasingAlphaRamp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDecreasingAlphaRampDuration(long paramLong) {
/*  930 */     this.decreasingAlphaRamp = paramLong;
/*  931 */     this.decAlphaRampInternal = (float)paramLong * 0.001F;
/*  932 */     if (this.decAlphaRampInternal > 0.5F * this.decreasingAlpha) {
/*  933 */       this.decAlphaRampInternal = 0.5F * this.decreasingAlpha;
/*      */     }
/*  935 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  943 */   public long getAlphaAtZeroDuration() { return (long)(this.alphaAtZero * 1000.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlphaAtZeroDuration(long paramLong) {
/*  952 */     this.alphaAtZero = (float)paramLong * 0.001F;
/*  953 */     computeStopTime();
/*  954 */     VirtualUniverse.mc.sendRunMessage(16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean finished() {
/*  963 */     long l = this.paused ? this.pauseTime : J3dClock.currentTimeMillis();
/*  964 */     return (this.loopCount != -1 && (float)(l - this.startTime) * 0.001F > this.stopTime);
/*      */   }
/*      */ 
/*      */   
/*      */   private final void computeStopTime() {
/*  969 */     if (this.loopCount >= 0) {
/*  970 */       float f = 0.0F;
/*  971 */       if ((this.mode & true) != 0) {
/*  972 */         f = this.increasingAlpha + this.alphaAtOne;
/*      */       }
/*  974 */       if ((this.mode & 0x2) != 0) {
/*  975 */         f += this.decreasingAlpha + this.alphaAtZero;
/*      */       }
/*  977 */       this.stopTime = this.triggerTime + this.phaseDelay + this.loopCount * f;
/*      */     } else {
/*  979 */       this.stopTime = 0.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Alpha cloneAlpha() {
/*  989 */     Alpha alpha = new Alpha();
/*  990 */     alpha.setStartTime(getStartTime());
/*  991 */     alpha.setLoopCount(getLoopCount());
/*  992 */     alpha.setMode(getMode());
/*  993 */     alpha.setTriggerTime(getTriggerTime());
/*  994 */     alpha.setPhaseDelayDuration(getPhaseDelayDuration());
/*  995 */     alpha.setIncreasingAlphaDuration(getIncreasingAlphaDuration());
/*  996 */     alpha.setIncreasingAlphaRampDuration(getIncreasingAlphaRampDuration());
/*  997 */     alpha.setAlphaAtOneDuration(getAlphaAtOneDuration());
/*  998 */     alpha.setDecreasingAlphaDuration(getDecreasingAlphaDuration());
/*  999 */     alpha.setDecreasingAlphaRampDuration(getDecreasingAlphaRampDuration());
/* 1000 */     alpha.setAlphaAtZeroDuration(getAlphaAtZeroDuration());
/* 1001 */     return alpha;
/*      */   }
/*      */   
/*      */   static  {
/* 1005 */     VirtualUniverse.loadLibraries();
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Alpha.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */