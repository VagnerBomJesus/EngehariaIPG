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
/*     */ class TimerThread
/*     */   extends Thread
/*     */ {
/*     */   private static final int WAIT = 0;
/*     */   private static final int NOTIFY = 1;
/*     */   private static final int STOP = 2;
/*  27 */   private WakeupOnElapsedTimeHeap heap = new WakeupOnElapsedTimeHeap();
/*     */ 
/*     */   
/*  30 */   private WakeupOnElapsedTime inputDeviceSchedCond = new WakeupOnElapsedTime(InputDeviceScheduler.samplingTime);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private WakeupOnElapsedTime soundSchedCond = new WakeupOnElapsedTime(120000L);
/*     */ 
/*     */   
/*     */   private boolean waiting = false;
/*     */   
/*     */   private boolean ready = false;
/*     */ 
/*     */   
/*  46 */   TimerThread(ThreadGroup paramThreadGroup) { super(paramThreadGroup, "J3D-TimerThread"); }
/*     */ 
/*     */ 
/*     */   
/*     */   void add(WakeupOnElapsedTime paramWakeupOnElapsedTime) {
/*  51 */     synchronized (this.heap) {
/*  52 */       this.heap.insert(paramWakeupOnElapsedTime);
/*     */     } 
/*  54 */     runMonitor(1, 0L);
/*     */   }
/*     */   
/*     */   void addInputDeviceSchedCond() {
/*  58 */     this.inputDeviceSchedCond.triggeredTime = InputDeviceScheduler.samplingTime + J3dClock.currentTimeMillis();
/*     */ 
/*     */     
/*  61 */     add(this.inputDeviceSchedCond);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addSoundSchedCond(long paramLong) {
/*  69 */     this.soundSchedCond.triggeredTime = paramLong;
/*  70 */     add(this.soundSchedCond);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  75 */   void finish() { runMonitor(2, 0L); }
/*     */ 
/*     */   
/*     */   void remove(WakeupOnElapsedTime paramWakeupOnElapsedTime) {
/*  79 */     synchronized (this.heap) {
/*  80 */       this.heap.extract(paramWakeupOnElapsedTime);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void run() {
/*  85 */     long l = -1L;
/*     */ 
/*     */ 
/*     */     
/*  89 */     label26: while (this.running) {
/*  90 */       runMonitor(0, l);
/*  91 */       long l1 = J3dClock.currentTimeMillis();
/*     */       
/*     */       while (true) {
/*  94 */         WakeupOnElapsedTime wakeupOnElapsedTime = null;
/*  95 */         l = -1L;
/*  96 */         synchronized (this.heap) {
/*  97 */           if (!this.heap.isEmpty()) {
/*  98 */             l = (this.heap.getMin()).triggeredTime - l1;
/*  99 */             if (l <= 0L) {
/* 100 */               wakeupOnElapsedTime = this.heap.extractMin();
/*     */             }
/*     */           } 
/*     */         } 
/* 104 */         if (wakeupOnElapsedTime == null)
/*     */           continue label26; 
/* 106 */         if (wakeupOnElapsedTime == this.inputDeviceSchedCond) {
/* 107 */           VirtualUniverse.mc.sendRunMessage(4); continue;
/*     */         } 
/* 109 */         if (wakeupOnElapsedTime == this.soundSchedCond) {
/* 110 */           VirtualUniverse.mc.sendRunMessage(2);
/*     */           continue;
/*     */         } 
/* 113 */         wakeupOnElapsedTime.setTriggered();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void runMonitor(int paramInt, long paramLong) {
/* 121 */     switch (paramInt) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 126 */         if (this.running && !this.ready) {
/* 127 */           this.waiting = true;
/*     */           try {
/* 129 */             if (paramLong < 0L) {
/* 130 */               wait();
/*     */             } else {
/* 132 */               wait(paramLong);
/*     */             } 
/* 134 */           } catch (InterruptedException interruptedException) {}
/* 135 */           this.waiting = false;
/*     */         } 
/* 137 */         this.ready = false;
/*     */         break;
/*     */       case 1:
/* 140 */         this.ready = true;
/* 141 */         if (this.waiting) {
/* 142 */           notify();
/*     */         }
/*     */         break;
/*     */       case 2:
/* 146 */         this.running = false;
/* 147 */         notify();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\TimerThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */