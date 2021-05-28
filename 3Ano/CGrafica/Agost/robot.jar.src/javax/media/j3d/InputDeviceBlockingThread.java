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
/*     */ class InputDeviceBlockingThread
/*     */   extends Thread
/*     */ {
/*     */   private static final int WAIT = 0;
/*     */   private static final int NOTIFY = 1;
/*     */   private static final int STOP = 2;
/*     */   private InputDevice device;
/*     */   private boolean waiting = false;
/*     */   private boolean ready = false;
/*  28 */   private static int numInstances = 0;
/*  29 */   private int instanceNum = -1;
/*     */   
/*     */   InputDeviceBlockingThread(ThreadGroup paramThreadGroup, InputDevice paramInputDevice) {
/*  32 */     super(paramThreadGroup, "");
/*  33 */     setName("J3D-InputDeviceBlockingThread-" + getInstanceNum());
/*  34 */     this.device = paramInputDevice;
/*     */   }
/*     */ 
/*     */   
/*  38 */   private int newInstanceNum() { return ++numInstances; }
/*     */ 
/*     */   
/*     */   private int getInstanceNum() {
/*  42 */     if (this.instanceNum == -1)
/*  43 */       this.instanceNum = newInstanceNum(); 
/*  44 */     return this.instanceNum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  54 */     while (this.running) {
/*  55 */       while (!this.stop) {
/*  56 */         this.device.pollAndProcessInput();
/*  57 */         Thread.yield();
/*     */       } 
/*  59 */       runMonitor(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  64 */   void sleep() { this.stop = true; }
/*     */ 
/*     */   
/*     */   void restart() {
/*  68 */     this.stop = false;
/*  69 */     runMonitor(1);
/*     */   }
/*     */   
/*     */   void finish() {
/*  73 */     this.stop = true;
/*  74 */     runMonitor(2);
/*     */   }
/*     */ 
/*     */   
/*     */   void runMonitor(int paramInt) {
/*  79 */     switch (paramInt) {
/*     */       
/*     */       case 0:
/*  82 */         while (this.running && !this.ready) {
/*  83 */           this.waiting = true;
/*     */           try {
/*  85 */             wait();
/*  86 */           } catch (InterruptedException interruptedException) {}
/*  87 */           this.waiting = false;
/*     */         } 
/*  89 */         this.ready = false;
/*     */         break;
/*     */       case 1:
/*  92 */         this.ready = true;
/*  93 */         if (this.waiting) {
/*  94 */           notify();
/*     */         }
/*     */         break;
/*     */       case 2:
/*  98 */         this.running = false;
/*  99 */         if (this.waiting)
/* 100 */           notify(); 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\InputDeviceBlockingThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */