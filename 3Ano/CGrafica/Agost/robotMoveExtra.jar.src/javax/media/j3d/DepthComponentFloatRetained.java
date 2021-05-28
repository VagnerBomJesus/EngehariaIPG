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
/*    */ class DepthComponentFloatRetained
/*    */   extends DepthComponentRetained
/*    */ {
/*    */   float[] depthData;
/*    */   
/*    */   void initialize(int paramInt1, int paramInt2) {
/* 32 */     this.type = 2;
/* 33 */     this.depthData = new float[paramInt1 * paramInt2];
/* 34 */     this.width = paramInt1;
/* 35 */     this.height = paramInt2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void setDepthData(float[] paramArrayOfFloat) {
/* 44 */     for (byte b = 0; b < paramArrayOfFloat.length; b++) {
/* 45 */       this.depthData[b] = paramArrayOfFloat[b];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void getDepthData(float[] paramArrayOfFloat) {
/* 56 */     for (byte b = 0; b < this.depthData.length; b++) {
/* 57 */       paramArrayOfFloat[b] = this.depthData[b];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   final void retrieveDepth(float[] paramArrayOfFloat, int paramInt1, int paramInt2) {
/* 67 */     int i = (paramInt2 - 1) * paramInt1, j = 0;
/* 68 */     for (byte b = 0; b < paramInt2; b++, 
/* 69 */       i -= paramInt1, j += this.width)
/*    */     {
/* 71 */       System.arraycopy(paramArrayOfFloat, i, this.depthData, j, paramInt1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\DepthComponentFloatRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */