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
/*    */ class NoopDrawingSurfaceObject
/*    */   extends DrawingSurfaceObject
/*    */ {
/*    */   NoopDrawingSurfaceObject(Canvas3D paramCanvas3D) {
/* 21 */     super(paramCanvas3D);
/*    */     
/* 23 */     System.err.println("NoopDrawingSurfaceObject constructed");
/*    */   }
/*    */ 
/*    */   
/*    */   boolean renderLock() {
/* 28 */     this.gotDsiLock = true;
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 34 */   void unLock() { this.gotDsiLock = false; }
/*    */ 
/*    */   
/*    */   void getDrawingSurfaceObjectInfo() {
/* 38 */     if (this.canvas.drawable == null) {
/* 39 */       System.err.println("NoopDrawingSurfaceObject.getDrawingSurfaceObjectInfo: window = " + this.canvas.drawable);
/*    */ 
/*    */ 
/*    */       
/* 43 */       this.canvas.drawable = new NoopDrawable();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 48 */   void invalidate() { System.err.println("NoopDrawingSurfaceObject.invalidate()"); }
/*    */   
/*    */   static class NoopDrawable implements Drawable {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\NoopDrawingSurfaceObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */