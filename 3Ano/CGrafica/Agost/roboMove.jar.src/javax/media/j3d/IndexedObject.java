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
/*    */ abstract class IndexedObject
/*    */ {
/*    */   int[][] listIdx;
/*    */   
/*    */   abstract VirtualUniverse getVirtualUniverse();
/*    */   
/*    */   int getIdxUsed(VirtualUniverse paramVirtualUniverse) {
/* 38 */     int i = this.listIdx[2][0];
/* 39 */     if (paramVirtualUniverse == getVirtualUniverse()) {
/* 40 */       return i;
/*    */     }
/* 42 */     return (i == 0) ? 1 : 0;
/*    */   }
/*    */   
/*    */   void incIdxUsed() {
/* 46 */     if (this.listIdx[2][0] == 0) {
/* 47 */       this.listIdx[2][0] = 1;
/*    */     } else {
/* 49 */       this.listIdx[2][0] = 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\IndexedObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */