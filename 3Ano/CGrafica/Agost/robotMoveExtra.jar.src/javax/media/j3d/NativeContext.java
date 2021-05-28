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
/*    */ class NativeContext
/*    */   implements Context
/*    */ {
/*    */   private long nativeCtx;
/*    */   
/* 24 */   NativeContext(long paramLong) { this.nativeCtx = paramLong; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   long getNativeCtx() { return this.nativeCtx; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\NativeContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */