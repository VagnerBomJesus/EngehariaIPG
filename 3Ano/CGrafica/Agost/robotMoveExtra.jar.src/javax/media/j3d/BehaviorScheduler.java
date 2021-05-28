/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BehaviorScheduler
/*     */   extends J3dThread
/*     */ {
/*  23 */   VirtualUniverse univ = null;
/*     */ 
/*     */   
/*     */   UnorderList[] processList;
/*     */ 
/*     */   
/*     */   IndexedUnorderSet scheduleList;
/*     */ 
/*     */   
/*     */   BehaviorStructure behaviorStructure;
/*     */ 
/*     */   
/*  35 */   int stopCount = -1;
/*     */ 
/*     */   
/*     */   long lastStartTime;
/*     */ 
/*     */   
/*     */   long lastStopTime;
/*     */ 
/*     */   
/*  44 */   Object intervalTimeLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private static int numInstances = 0;
/*  50 */   private int instanceNum = -1;
/*     */ 
/*     */   
/*  53 */   private int newInstanceNum() { return ++numInstances; }
/*     */ 
/*     */   
/*     */   int getInstanceNum() {
/*  57 */     if (this.instanceNum == -1)
/*  58 */       this.instanceNum = newInstanceNum(); 
/*  59 */     return this.instanceNum;
/*     */   }
/*     */ 
/*     */   
/*     */   BehaviorScheduler(ThreadGroup paramThreadGroup, VirtualUniverse paramVirtualUniverse) {
/*  64 */     super(paramThreadGroup);
/*  65 */     setName("J3D-BehaviorScheduler-" + getInstanceNum());
/*  66 */     this.univ = paramVirtualUniverse;
/*  67 */     this.behaviorStructure = paramVirtualUniverse.behaviorStructure;
/*  68 */     this.scheduleList = this.behaviorStructure.scheduleList;
/*  69 */     this.processList = this.behaviorStructure.processList;
/*  70 */     this.type = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   void stopBehaviorScheduler(long[] paramArrayOfLong) {
/*  75 */     this.stopCount = 2;
/*  76 */     VirtualUniverse.mc.sendRunMessage(this.univ, 1);
/*  77 */     while (!this.userStop) {
/*  78 */       MasterControl.threadYield();
/*     */     }
/*  80 */     synchronized (this.intervalTimeLock) {
/*  81 */       paramArrayOfLong[0] = this.lastStartTime;
/*  82 */       paramArrayOfLong[1] = this.lastStopTime;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void startBehaviorScheduler() {
/*  88 */     synchronized (this.intervalTimeLock) {
/*  89 */       this.stopCount = -1;
/*  90 */       this.userStop = false;
/*  91 */       VirtualUniverse.mc.setWork();
/*     */     } 
/*     */   }
/*     */   
/*     */   void deactivate() {
/*  96 */     this.active = false;
/*  97 */     if (this.stopCount >= 0) {
/*  98 */       this.userStop = true;
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
/*     */   void doWork(long paramLong) {
/* 115 */     this.lastStartTime = J3dClock.currentTimeMillis();
/*     */ 
/*     */     
/* 118 */     VirtualUniverse.mc.sendRunMessage(this.univ, 1);
/* 119 */     if (this.stopCount >= 0 && --this.stopCount == 0) {
/* 120 */       this.userStop = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 125 */     byte b = 0;
/* 126 */     for (; b < 10; 
/* 127 */       b++) {
/*     */       
/* 129 */       UnorderList unorderList = this.processList[b];
/*     */       
/* 131 */       if (!unorderList.isEmpty()) {
/*     */ 
/*     */         
/* 134 */         BehaviorRetained[] arrayOfBehaviorRetained = (BehaviorRetained[])unorderList.toArray(false);
/*     */         
/* 136 */         int i = unorderList.arraySize();
/*     */         
/* 138 */         for (byte b1 = 0; b1 < i; b1++) {
/* 139 */           BehaviorRetained behaviorRetained = arrayOfBehaviorRetained[b1];
/*     */ 
/*     */           
/* 142 */           synchronized (behaviorRetained) {
/* 143 */             Behavior behavior = (Behavior)behaviorRetained.source;
/*     */             
/* 145 */             if (!behavior.isLive() || !behaviorRetained.conditionSet || behaviorRetained.wakeupCondition == null) {
/*     */ 
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 151 */               if (behaviorRetained.wakeupCondition.trigEnum == null) {
/* 152 */                 behaviorRetained.wakeupCondition.trigEnum = new WakeupCriteriaEnumerator(behaviorRetained.wakeupCondition, 1);
/*     */               }
/*     */               else {
/*     */                 
/* 156 */                 behaviorRetained.wakeupCondition.trigEnum.reset(behaviorRetained.wakeupCondition, 1);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 166 */               behaviorRetained.conditionSet = false;
/* 167 */               WakeupCondition wakeupCondition = behaviorRetained.wakeupCondition;
/*     */               
/* 169 */               synchronized (behaviorRetained) {
/* 170 */                 behaviorRetained.inCallback = true;
/* 171 */                 this.univ.inBehavior = true;
/*     */                 try {
/* 173 */                   behavior.processStimulus(wakeupCondition.trigEnum);
/*     */                 }
/* 175 */                 catch (RuntimeException runtimeException) {
/*     */ 
/*     */                   
/* 178 */                   behaviorRetained.conditionSet = false;
/* 179 */                   System.err.println("Exception occurred during Behavior execution:");
/* 180 */                   runtimeException.printStackTrace();
/*     */                 }
/* 182 */                 catch (Error error) {
/*     */ 
/*     */                   
/* 185 */                   behaviorRetained.conditionSet = false;
/* 186 */                   System.err.println("Error occurred during Behavior execution:");
/* 187 */                   error.printStackTrace();
/*     */                 } 
/* 189 */                 this.univ.inBehavior = false;
/* 190 */                 behaviorRetained.inCallback = false;
/*     */               } 
/*     */ 
/*     */               
/* 194 */               if (!behaviorRetained.conditionSet) {
/* 195 */                 if (wakeupCondition != null) {
/* 196 */                   wakeupCondition.cleanTree(this.behaviorStructure);
/*     */                 }
/* 198 */                 behaviorRetained.wakeupCondition = null;
/* 199 */                 behaviorRetained.active = false;
/* 200 */                 this.scheduleList.remove(behaviorRetained);
/*     */               } else {
/* 202 */                 behaviorRetained.handleLastWakeupOn(wakeupCondition, this.behaviorStructure);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 207 */         unorderList.clear();
/*     */       } 
/*     */     } 
/* 210 */     this.behaviorStructure.handleAWTEvent();
/* 211 */     this.behaviorStructure.handleBehaviorPost();
/* 212 */     this.lastStopTime = J3dClock.currentTimeMillis();
/*     */     
/* 214 */     if (MasterControl.isStatsLoggable(Level.FINE)) {
/* 215 */       VirtualUniverse.mc.recordTime(MasterControl.TimeType.BEHAVIOR, (this.lastStopTime - this.lastStartTime) * 1000000L);
/*     */     }
/*     */   }
/*     */   
/*     */   void free() {
/* 220 */     this.behaviorStructure = null;
/* 221 */     (getThreadData(null, null)).thread = null;
/* 222 */     this.univ = null;
/* 223 */     byte b = 9;
/* 224 */     for (; b >= 0; b--) {
/* 225 */       this.processList[b].clear();
/*     */     }
/* 227 */     this.scheduleList.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\BehaviorScheduler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */