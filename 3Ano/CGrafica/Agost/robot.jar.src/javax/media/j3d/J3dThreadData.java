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
/*    */ class J3dThreadData
/*    */ {
/*    */   static final int WAIT_ALL_THREADS = 1;
/*    */   static final int CONT_THREAD = 2;
/*    */   static final int WAIT_THIS_THREAD = 4;
/*    */   static final int START_TIMER = 8;
/*    */   static final int STOP_TIMER = 16;
/*    */   static final int LAST_STOP_TIMER = 32;
/* 35 */   J3dThread thread = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   long lastUpdateTime = -1L;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   long lastRunTime = -1L;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   int threadType = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   int threadOpts = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   Object threadArgs = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   boolean needsRun = false;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   int type = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   View view = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   Canvas3D canvas = null;
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\J3dThreadData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */