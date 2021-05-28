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
/*    */ abstract class DepthComponentRetained
/*    */   extends NodeComponentRetained
/*    */ {
/*    */   static final int DEPTH_COMPONENT_TYPE_INT = 1;
/*    */   static final int DEPTH_COMPONENT_TYPE_FLOAT = 2;
/*    */   static final int DEPTH_COMPONENT_TYPE_NATIVE = 1;
/*    */   int width;
/*    */   int height;
/*    */   int type;
/*    */   
/* 37 */   int getWidth() { return this.width; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   int getHeight() { return this.height; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\DepthComponentRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */