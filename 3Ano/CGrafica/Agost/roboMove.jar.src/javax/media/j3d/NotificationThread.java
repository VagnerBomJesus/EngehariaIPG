/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NotificationThread
/*     */   extends Thread
/*     */ {
/*     */   private static final int WAIT = 0;
/*     */   private static final int NOTIFY = 1;
/*     */   private static final int STOP = 2;
/*     */   private boolean waiting = false;
/*     */   private boolean ready = false;
/*  31 */   private LinkedList notificationQueue = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   NotificationThread(ThreadGroup paramThreadGroup) { super(paramThreadGroup, "J3D-NotificationThread"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addNotification(J3dNotification paramJ3dNotification) {
/*  45 */     this.notificationQueue.add(paramJ3dNotification);
/*  46 */     runMonitor(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private J3dNotification[] getNotifications() {
/*  53 */     J3dNotification[] arrayOfJ3dNotification = (J3dNotification[])this.notificationQueue.toArray(new J3dNotification[0]);
/*  54 */     this.notificationQueue.clear();
/*  55 */     return arrayOfJ3dNotification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processNotifications() {
/*  62 */     J3dNotification[] arrayOfJ3dNotification = getNotifications();
/*     */     
/*  64 */     for (byte b = 0; b < arrayOfJ3dNotification.length; b++) {
/*  65 */       J3dNotification j3dNotification = arrayOfJ3dNotification[b];
/*  66 */       switch (j3dNotification.type) {
/*     */         case 0:
/*  68 */           j3dNotification.universe.notifyShaderErrorListeners((ShaderError)j3dNotification.args[0]);
/*     */           break;
/*     */         case 1:
/*  71 */           VirtualUniverse.notifyRenderingErrorListeners((RenderingError)j3dNotification.args[0]);
/*     */           break;
/*     */         default:
/*  74 */           System.err.println("J3dNotification.processNotifications: unrecognized type = " + j3dNotification.type);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  81 */   void finish() { runMonitor(2); }
/*     */ 
/*     */   
/*     */   public void run() {
/*  85 */     while (this.running) {
/*  86 */       runMonitor(0);
/*     */       
/*  88 */       processNotifications();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void runMonitor(int paramInt) {
/*  95 */     switch (paramInt) {
/*     */       case 0:
/*  97 */         while (this.running && !this.ready) {
/*  98 */           this.waiting = true;
/*     */           try {
/* 100 */             wait();
/* 101 */           } catch (InterruptedException interruptedException) {}
/*     */           
/* 103 */           this.waiting = false;
/*     */         } 
/* 105 */         this.ready = false;
/*     */         return;
/*     */       case 1:
/* 108 */         this.ready = true;
/* 109 */         if (this.waiting) {
/* 110 */           notify();
/*     */         }
/*     */         return;
/*     */       case 2:
/* 114 */         this.running = false;
/* 115 */         notify();
/*     */         return;
/*     */     } 
/*     */     assert false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\NotificationThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */