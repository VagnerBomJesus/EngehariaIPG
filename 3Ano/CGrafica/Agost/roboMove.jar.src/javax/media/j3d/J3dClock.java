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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class J3dClock
/*    */ {
/*    */   private static long deltaTime;
/*    */   private static final long nsecPerMsec = 1000000L;
/*    */   
/* 38 */   static long currentTimeMillis() { return System.nanoTime() / 1000000L + deltaTime; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static  {
/*    */     long l2, l1;
/* 45 */     System.currentTimeMillis();
/* 46 */     System.nanoTime();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     synchronized (J3dClock.class) {
/* 53 */       l1 = System.currentTimeMillis();
/* 54 */       l2 = System.nanoTime();
/*    */     } 
/* 56 */     deltaTime = l1 - l2 / 1000000L;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\J3dClock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */