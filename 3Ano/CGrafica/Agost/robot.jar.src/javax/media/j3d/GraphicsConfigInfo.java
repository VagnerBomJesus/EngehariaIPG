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
/*    */ class GraphicsConfigInfo
/*    */ {
/*    */   private GraphicsConfigTemplate3D graphicsConfigTemplate3D;
/*    */   private Object privateData;
/*    */   
/*    */   GraphicsConfigInfo(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D) {
/* 21 */     this.graphicsConfigTemplate3D = null;
/* 22 */     this.privateData = null;
/*    */ 
/*    */     
/* 25 */     setGraphicsConfigTemplate3D(paramGraphicsConfigTemplate3D);
/*    */   }
/*    */ 
/*    */   
/* 29 */   GraphicsConfigTemplate3D getGraphicsConfigTemplate3D() { return this.graphicsConfigTemplate3D; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   void setGraphicsConfigTemplate3D(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D) { this.graphicsConfigTemplate3D = paramGraphicsConfigTemplate3D; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   Object getPrivateData() { return this.privateData; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   void setPrivateData(Object paramObject) { this.privateData = paramObject; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\GraphicsConfigInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */