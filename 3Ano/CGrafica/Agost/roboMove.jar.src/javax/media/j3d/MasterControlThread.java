/*    */ package javax.media.j3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class MasterControlThread
/*    */   extends Thread
/*    */ {
/* 24 */   private static int numInstances = 0;
/* 25 */   private int instanceNum = -1;
/*    */ 
/*    */   
/* 28 */   private static int newInstanceNum() { return ++numInstances; }
/*    */ 
/*    */   
/*    */   private int getInstanceNum() {
/* 32 */     if (this.instanceNum == -1)
/* 33 */       this.instanceNum = newInstanceNum(); 
/* 34 */     return this.instanceNum;
/*    */   }
/*    */   
/*    */   MasterControlThread(ThreadGroup paramThreadGroup) {
/* 38 */     super(paramThreadGroup, "");
/* 39 */     setName("J3D-MasterControl-" + getInstanceNum());
/* 40 */     VirtualUniverse.mc.createMCThreads();
/* 41 */     start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     do {
/* 47 */       while (VirtualUniverse.mc.running) {
/* 48 */         VirtualUniverse.mc.doWork();
/*    */ 
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 54 */     while (!VirtualUniverse.mc.mcThreadDone());
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\MasterControlThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */