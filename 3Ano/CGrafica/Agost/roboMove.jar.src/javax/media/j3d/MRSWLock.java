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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class MRSWLock
/*    */ {
/*    */   static boolean debug = false;
/* 31 */   private int readCount = 0;
/*    */   private boolean write = false;
/* 33 */   private int writeRequested = 0;
/* 34 */   private int lockRequested = 0;
/*    */ 
/*    */   
/*    */   final void readLock() {
/* 38 */     this.lockRequested++;
/* 39 */     while (this.write == true || this.writeRequested > 0) { 
/* 40 */       try { wait(); } catch (InterruptedException interruptedException) {} }
/*    */     
/* 42 */     this.lockRequested--;
/* 43 */     this.readCount++;
/*    */   }
/*    */   
/*    */   final void readUnlock() {
/* 47 */     if (this.readCount > 0)
/* 48 */     { this.readCount--; }
/*    */     
/* 50 */     else if (debug) { System.err.println("ReadWriteLock.java : Problem! readCount is >= 0."); }
/*    */     
/* 52 */     if (this.lockRequested > 0)
/* 53 */       notifyAll(); 
/*    */   }
/*    */   
/*    */   final void writeLock() {
/* 57 */     this.lockRequested++;
/* 58 */     this.writeRequested++;
/* 59 */     while (this.readCount > 0 || this.write == true) { 
/* 60 */       try { wait(); } catch (InterruptedException interruptedException) {} }
/*    */     
/* 62 */     this.write = true;
/* 63 */     this.lockRequested--;
/* 64 */     this.writeRequested--;
/*    */   }
/*    */   
/*    */   final void writeUnlock() {
/* 68 */     this.write = false;
/*    */     
/* 70 */     if (this.lockRequested > 0)
/* 71 */       notifyAll(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\MRSWLock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */