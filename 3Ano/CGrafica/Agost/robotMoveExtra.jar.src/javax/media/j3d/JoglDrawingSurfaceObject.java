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
/*    */ class JoglDrawingSurfaceObject
/*    */   extends DrawingSurfaceObject
/*    */ {
/* 21 */   JoglDrawingSurfaceObject(Canvas3D paramCanvas3D) { super(paramCanvas3D); }
/*    */ 
/*    */ 
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void getDrawingSurfaceObjectInfo() {}
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
/* 58 */   void invalidate() { System.err.println("JoglDrawingSurfaceObject.invalidate()"); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\JoglDrawingSurfaceObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */