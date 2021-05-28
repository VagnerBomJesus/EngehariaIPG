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
/*    */ class DepthComponentIntRetained
/*    */   extends DepthComponentRetained
/*    */ {
/*    */   int[] depthData;
/*    */   
/*    */   void initialize(int paramInt1, int paramInt2) {
/* 31 */     this.type = 1;
/* 32 */     this.depthData = new int[paramInt1 * paramInt2];
/* 33 */     this.width = paramInt1;
/* 34 */     this.height = paramInt2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void setDepthData(int[] paramArrayOfInt) {
/* 43 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 44 */       this.depthData[b] = paramArrayOfInt[b];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void getDepthData(int[] paramArrayOfInt) {
/* 56 */     for (byte b = 0; b < this.depthData.length; b++) {
/* 57 */       paramArrayOfInt[b] = this.depthData[b];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   final void retrieveDepth(int[] paramArrayOfInt, int paramInt1, int paramInt2) {
/* 67 */     int i = (paramInt2 - 1) * paramInt1, j = 0;
/* 68 */     for (byte b = 0; b < paramInt2; b++, 
/* 69 */       i -= paramInt1, j += this.width)
/*    */     {
/* 71 */       System.arraycopy(paramArrayOfInt, i, this.depthData, j, paramInt1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\DepthComponentIntRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */