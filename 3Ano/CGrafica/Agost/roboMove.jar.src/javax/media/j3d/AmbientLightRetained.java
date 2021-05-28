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
/*    */ class AmbientLightRetained
/*    */   extends LightRetained
/*    */ {
/*    */   AmbientLightRetained() {
/* 24 */     this.nodeType = 5;
/* 25 */     this.lightType = 1;
/* 26 */     this.localBounds = new BoundingBox();
/* 27 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/* 28 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*    */   }
/*    */   
/*    */   void setLive(SetLiveState paramSetLiveState) {
/* 32 */     super.setLive(paramSetLiveState);
/* 33 */     J3dMessage j3dMessage = initMessage(7);
/* 34 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*    */   }
/*    */   
/*    */   void update(Context paramContext, int paramInt, double paramDouble) {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\AmbientLightRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */