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
/*    */ class GeometryLock
/*    */ {
/* 20 */   Thread threadId = null;
/*    */ 
/*    */   
/*    */   boolean lockOwned = false;
/*    */ 
/*    */   
/* 26 */   int count = 0;
/*    */ 
/*    */   
/* 29 */   int waiting = 0;
/*    */ 
/*    */   
/*    */   void getLock() {
/* 33 */     Thread thread = Thread.currentThread();
/*    */ 
/*    */     
/* 36 */     if (this.threadId == thread) {
/* 37 */       this.count++;
/*    */       
/*    */       return;
/*    */     } 
/* 41 */     while (this.lockOwned) {
/*    */       try {
/* 43 */         this.waiting++;
/* 44 */         wait();
/* 45 */       } catch (InterruptedException interruptedException) {
/* 46 */         System.err.println(interruptedException);
/*    */       } 
/* 48 */       this.waiting--;
/*    */     } 
/* 50 */     this.count++;
/*    */     
/* 52 */     this.lockOwned = true;
/* 53 */     this.threadId = thread;
/*    */   }
/*    */   
/*    */   void unLock() {
/* 57 */     Thread thread = Thread.currentThread();
/* 58 */     if (this.threadId == thread) {
/*    */       
/* 60 */       if (--this.count > 0) {
/*    */         return;
/*    */       }
/* 63 */       this.lockOwned = false;
/* 64 */       this.threadId = null;
/* 65 */       if (this.waiting > 0)
/* 66 */         notify(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\GeometryLock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */