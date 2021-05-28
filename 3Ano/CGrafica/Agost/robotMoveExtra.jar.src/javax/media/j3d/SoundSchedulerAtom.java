/*     */ package javax.media.j3d;
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
/*     */ class SoundSchedulerAtom
/*     */ {
/*  31 */   SoundRetained sound = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   MediaContainer soundData = null;
/*     */ 
/*     */   
/*  39 */   long startTime = 0L;
/*  40 */   long endTime = 0L;
/*     */   
/*  42 */   long sampleLength = 0L;
/*  43 */   long loopStartOffset = 0L;
/*  44 */   long loopLength = 0L;
/*  45 */   long attackLength = 0L;
/*  46 */   long releaseLength = 0L;
/*     */   
/*  48 */   int loadStatus = 0;
/*     */   boolean playing = false;
/*  50 */   int numberChannels = 0;
/*     */ 
/*     */   
/*     */   boolean activated = false;
/*     */ 
/*     */   
/*     */   static final int OFF = 0;
/*     */   
/*     */   static final int ON = 1;
/*     */   
/*     */   static final int PENDING_ON = 2;
/*     */   
/*     */   static final int PENDING_OFF = 3;
/*     */   
/*  64 */   int enabled = 0;
/*     */   
/*     */   static final int UNMUTED = 0;
/*     */   
/*     */   static final int MUTED = 1;
/*     */   
/*     */   static final int PENDING_UNMUTE = 2;
/*     */   
/*     */   static final int PENDING_MUTE = 3;
/*  73 */   int muted = 0;
/*     */   
/*     */   static final int UNPAUSED = 0;
/*     */   
/*     */   static final int PAUSED = 1;
/*     */   
/*     */   static final int PENDING_UNPAUSE = 2;
/*     */   
/*     */   static final int PENDING_PAUSE = 3;
/*  82 */   int paused = 0;
/*     */   
/*     */   static final int DO_NOTHING = 0;
/*     */   
/*     */   static final int LEAVE_OFF = 1;
/*     */   
/*     */   static final int LEAVE_SILENT = 2;
/*     */   
/*     */   static final int LEAVE_AUDIBLE = 3;
/*     */   
/*     */   static final int LEAVE_PAUSED = 4;
/*     */   
/*     */   static final int RESTART_AUDIBLE = 5;
/*     */   
/*     */   static final int START_AUDIBLE = 6;
/*     */   static final int RESTART_SILENT = 7;
/*     */   static final int START_SILENT = 8;
/*     */   static final int MAKE_AUDIBLE = 11;
/*     */   static final int MAKE_SILENT = 12;
/*     */   static final int PAUSE_AUDIBLE = 13;
/*     */   static final int PAUSE_SILENT = 14;
/*     */   static final int RESUME_AUDIBLE = 15;
/*     */   static final int RESUME_SILENT = 16;
/*     */   static final int TURN_OFF = 17;
/*     */   static final int UPDATE = 18;
/*     */   static final int COMPLETE = 19;
/* 108 */   int schedulingAction = 0;
/*     */   
/*     */   static final int SOUND_OFF = 0;
/*     */   
/*     */   static final int SOUND_AUDIBLE = 1;
/*     */   
/*     */   static final int SOUND_SILENT = 2;
/*     */   
/*     */   static final int SOUND_PAUSED = 3;
/*     */   static final int SOUND_COMPLETE = 4;
/* 118 */   int status = 0;
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
/* 130 */   int attribsDirty = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   int stateDirty = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   int sampleId = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   SoundScheduler soundScheduler = null;
/*     */ 
/*     */   
/*     */   static final boolean debugFlag = false;
/*     */ 
/*     */   
/*     */   static final boolean internalErrors = false;
/*     */ 
/*     */ 
/*     */   
/*     */   void calculateEndTime() {
/* 159 */     SoundRetained soundRetained = this.sound.sgSound;
/* 160 */     int i = soundRetained.loopCount;
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (this.sampleLength <= 0L || this.loopLength <= 0L || i < 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 171 */       this.endTime = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 178 */     else if (this.playing && this.startTime > 0L) {
/* 179 */       this.endTime = this.startTime + this.attackLength + this.loopLength * (i + 1) + this.releaseLength;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 188 */       long l = J3dClock.currentTimeMillis();
/* 189 */       this.endTime = l + this.loopLength - (l - this.startTime - this.attackLength) % this.loopLength + this.releaseLength;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void enable(boolean paramBoolean) {
/* 200 */     if (paramBoolean) {
/* 201 */       setEnableState(2);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 207 */       setEnableState(3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void mute(boolean paramBoolean) {
/* 216 */     if (paramBoolean) {
/* 217 */       setMuteState(3);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 223 */       setMuteState(2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void pause(boolean paramBoolean) {
/* 231 */     if (paramBoolean) {
/* 232 */       setPauseState(3);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 237 */       setPauseState(2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setEnableState(int paramInt) {
/* 247 */     this.enabled = paramInt;
/* 248 */     switch (paramInt) {
/*     */     
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
/*     */   
/*     */   void setMuteState(int paramInt) {
/* 275 */     this.muted = paramInt;
/* 276 */     switch (paramInt) {
/*     */     
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
/*     */   
/*     */   void setPauseState(int paramInt) {
/* 303 */     this.paused = paramInt;
/* 304 */     switch (paramInt) {
/*     */     
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
/*     */   int calcActiveSchedAction() {
/* 341 */     SoundRetained soundRetained = this.sound.sgSound;
/* 342 */     byte b = 0;
/* 343 */     this.activated = true;
/* 344 */     switch (this.enabled) {
/*     */       case 2:
/* 346 */         setEnableState(1);
/*     */ 
/*     */         
/* 349 */         if (this.status == 0 || this.status == 3) {
/*     */           
/* 351 */           b = 6; break;
/*     */         } 
/* 353 */         b = 5;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 358 */         if (this.status == 0) {
/*     */           
/* 360 */           b = 6; break;
/* 361 */         }  if (this.status == 2) {
/* 362 */           b = 11; break;
/*     */         } 
/* 364 */         b = 3;
/*     */         break;
/*     */       case 3:
/* 367 */         setEnableState(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 375 */         if (this.status == 1) {
/* 376 */           if (soundRetained.release) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 381 */             if (this.enabled == 3)
/*     */             {
/* 383 */               calculateEndTime();
/*     */             }
/* 385 */             b = 3;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */ 
/*     */           
/* 392 */           b = 17;
/*     */           break;
/*     */         } 
/* 395 */         if (this.status == 2) {
/* 396 */           if (soundRetained.release) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 402 */             calculateEndTime();
/* 403 */             b = 11;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */ 
/*     */           
/* 410 */           b = 17;
/*     */           
/*     */           break;
/*     */         } 
/* 414 */         b = 1;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 420 */     if (this.paused == 3) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 426 */       switch (b) {
/*     */         case 3:
/*     */         case 11:
/*     */         case 15:
/* 430 */           b = 13;
/*     */           break;
/*     */         case 2:
/*     */         case 12:
/*     */         case 16:
/* 435 */           b = 14;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 443 */     } else if (this.paused == 2) {
/* 444 */       debugPrint("    PENDING_UNPAUSE");
/* 445 */       switch (b) {
/*     */ 
/*     */         
/*     */         case 3:
/*     */         case 11:
/*     */         case 13:
/* 451 */           b = 15;
/*     */           break;
/*     */         case 2:
/*     */         case 12:
/*     */         case 14:
/* 456 */           b = 16;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 463 */     return b;
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
/*     */   int calcInactiveSchedAction() {
/* 478 */     byte b = 0;
/* 479 */     SoundRetained soundRetained = this.sound.sgSound;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 484 */     this.activated = false;
/*     */     
/* 486 */     switch (this.enabled) {
/*     */ 
/*     */       
/*     */       case 2:
/* 490 */         setEnableState(1);
/* 491 */         if (soundRetained.continuous) {
/* 492 */           if (this.status == 0) {
/* 493 */             b = 8; break;
/*     */           } 
/* 495 */           b = 7;
/*     */           break;
/*     */         } 
/* 498 */         if (this.status == 0) {
/* 499 */           b = 1; break;
/*     */         } 
/* 501 */         b = 17;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 507 */         if (soundRetained.continuous) {
/* 508 */           if (this.status == 1) {
/* 509 */             b = 12; break;
/* 510 */           }  if (this.status == 0) {
/* 511 */             b = 8; break;
/*     */           } 
/* 513 */           b = 2;
/*     */           
/*     */           break;
/*     */         } 
/* 517 */         if (this.status == 0) {
/* 518 */           b = 1; break;
/*     */         } 
/* 520 */         b = 17;
/*     */         break;
/*     */       
/*     */       case 3:
/* 524 */         setEnableState(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 531 */         if (soundRetained.release && soundRetained.continuous) {
/* 532 */           if (this.enabled == 3)
/*     */           {
/* 534 */             calculateEndTime();
/*     */           }
/* 536 */           if (this.status == 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 541 */             b = 12; break;
/*     */           } 
/* 543 */           if (this.status == 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 548 */             b = 2;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 554 */           b = 1;
/*     */           
/*     */           break;
/*     */         } 
/* 558 */         if (this.status == 0) {
/*     */ 
/*     */ 
/*     */           
/* 562 */           b = 1;
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 568 */         b = 17;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 577 */     if (this.paused == 3) {
/*     */ 
/*     */ 
/*     */       
/* 581 */       switch (b) {
/*     */         case 2:
/*     */         case 12:
/*     */         case 16:
/* 585 */           b = 14;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 593 */     } else if (this.paused == 2) {
/* 594 */       switch (b) {
/*     */         case 2:
/* 596 */           b = 16;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 603 */     return b;
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
/*     */   void debugPrint(String paramString) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 625 */   void setAttribsDirtyFlag(int paramInt) { this.attribsDirty |= paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 631 */   void setStateDirtyFlag(int paramInt) { this.stateDirty |= paramInt; }
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
/* 644 */   void clearAttribsDirtyFlag(int paramInt) { this.attribsDirty &= (paramInt ^ 0xFFFFFFFF); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 651 */   void clearAttribsDirtyFlag() { this.attribsDirty = 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 657 */   void clearStateDirtyFlag(int paramInt) { this.stateDirty &= (paramInt ^ 0xFFFFFFFF); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 663 */   void clearStateDirtyFlag() { this.stateDirty = 0; }
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
/*     */   boolean testDirtyFlag(int paramInt1, int paramInt2) {
/* 675 */     if ((paramInt1 & paramInt2) > 0) {
/* 676 */       return true;
/*     */     }
/* 678 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean testDirtyFlags() {
/* 686 */     if ((this.attribsDirty & 0xFFFF) > 0)
/* 687 */       return true; 
/* 688 */     if ((this.stateDirty & 0xFFFF) > 0) {
/* 689 */       return true;
/*     */     }
/* 691 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SoundSchedulerAtom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */