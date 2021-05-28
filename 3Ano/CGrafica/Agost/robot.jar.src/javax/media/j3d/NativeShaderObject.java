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
/*    */ class NativeShaderObject
/*    */   implements ShaderProgramId, ShaderId, ShaderAttrLoc
/*    */ {
/*    */   private long nativeId;
/*    */   
/* 24 */   NativeShaderObject(long paramLong) { this.nativeId = paramLong; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   long getNativeId() { return this.nativeId; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\NativeShaderObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */