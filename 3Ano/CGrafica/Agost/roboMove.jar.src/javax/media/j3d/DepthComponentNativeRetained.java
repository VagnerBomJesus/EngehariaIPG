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
/*    */ class DepthComponentNativeRetained
/*    */   extends DepthComponentRetained
/*    */ {
/*    */   int[] depthData;
/*    */   
/*    */   void initialize(int paramInt1, int paramInt2) {
/* 32 */     this.type = 1;
/* 33 */     this.depthData = new int[paramInt1 * paramInt2];
/* 34 */     this.width = paramInt1;
/* 35 */     this.height = paramInt2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void getDepthData(int[] paramArrayOfInt) {
/* 45 */     for (byte b = 0; b < this.depthData.length; b++) {
/* 46 */       paramArrayOfInt[b] = this.depthData[b];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   final void retrieveDepth(int[] paramArrayOfInt, int paramInt1, int paramInt2) {
/* 56 */     int i = (paramInt2 - 1) * paramInt1, j = 0;
/* 57 */     for (byte b = 0; b < paramInt2; b++, 
/* 58 */       i -= paramInt1, j += this.width)
/*    */     {
/* 60 */       System.arraycopy(paramArrayOfInt, i, this.depthData, j, paramInt1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\DepthComponentNativeRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */