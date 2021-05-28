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
/*    */ class FreeListManager
/*    */ {
/*    */   private static final boolean DEBUG = false;
/*    */   static final int DISPLAYLIST = 0;
/*    */   static final int TEXTURE2D = 1;
/*    */   static final int TEXTURE3D = 2;
/* 25 */   private static int maxFreeListNum = 2;
/*    */ 
/*    */   
/* 28 */   private static int currlist = 0;
/*    */   
/* 30 */   static MemoryFreeList[] freelist = null;
/*    */   
/*    */   static void createFreeLists() {
/* 33 */     maxFreeListNum = 2;
/* 34 */     freelist = new MemoryFreeList[maxFreeListNum + 1];
/* 35 */     freelist[0] = new IntegerFreeList();
/* 36 */     freelist[1] = new IntegerFreeList();
/* 37 */     freelist[2] = new IntegerFreeList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static void manageLists() {
/* 44 */     if (freelist[currlist] != null) {
/* 45 */       freelist[currlist].shrink();
/*    */     }
/*    */     
/* 48 */     currlist++;
/* 49 */     if (currlist > maxFreeListNum) currlist = 0;
/*    */   
/*    */   }
/*    */   
/*    */   static MemoryFreeList getFreeList(int paramInt) {
/* 54 */     if (paramInt < 0 || paramInt > maxFreeListNum)
/*    */     {
/* 56 */       return null;
/*    */     }
/*    */     
/* 59 */     return freelist[paramInt];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 64 */   static Object getObject(int paramInt) { return freelist[paramInt].getObject(); }
/*    */ 
/*    */ 
/*    */   
/* 68 */   static void freeObject(int paramInt, Object paramObject) { freelist[paramInt].add(paramObject); }
/*    */ 
/*    */ 
/*    */   
/* 72 */   static void clearList(int paramInt) { freelist[paramInt].clear(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\FreeListManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */