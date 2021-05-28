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
/*    */ class J3dNotification
/*    */ {
/*    */   static final int INVALID_TYPE = -1;
/*    */   static final int SHADER_ERROR = 0;
/*    */   static final int RENDERING_ERROR = 1;
/* 30 */   int type = -1;
/*    */ 
/*    */ 
/*    */   
/*    */   VirtualUniverse universe;
/*    */ 
/*    */ 
/*    */   
/*    */   static final int MAX_ARGS = 6;
/*    */ 
/*    */ 
/*    */   
/* 42 */   Object[] args = new Object[6];
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\J3dNotification.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */