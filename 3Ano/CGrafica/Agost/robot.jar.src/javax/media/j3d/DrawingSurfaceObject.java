/*    */ package javax.media.j3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class DrawingSurfaceObject
/*    */ {
/*    */   Canvas3D canvas;
/*    */   boolean gotDsiLock;
/*    */   boolean onScreen;
/*    */   
/*    */   abstract boolean renderLock();
/*    */   
/*    */   abstract void unLock();
/*    */   
/*    */   abstract void getDrawingSurfaceObjectInfo();
/*    */   
/*    */   abstract void invalidate();
/*    */   
/*    */   DrawingSurfaceObject(Canvas3D paramCanvas3D) {
/* 22 */     this.gotDsiLock = false;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     this.canvas = paramCanvas3D;
/* 32 */     this.onScreen = !paramCanvas3D.offScreen;
/*    */   }
/*    */ 
/*    */   
/* 36 */   boolean isLocked() { return this.gotDsiLock; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   void contextValidated() { this.canvas.validCtx = true; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\DrawingSurfaceObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */