/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.awt.IllegalComponentStateException;
/*    */ import java.awt.event.ComponentAdapter;
/*    */ import java.awt.event.ComponentEvent;
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
/*    */ class CanvasViewEventCatcher
/*    */   extends ComponentAdapter
/*    */ {
/*    */   private Canvas3D canvas;
/*    */   private static final boolean DEBUG = false;
/*    */   
/* 31 */   CanvasViewEventCatcher(Canvas3D paramCanvas3D) { this.canvas = paramCanvas3D; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentResized(ComponentEvent paramComponentEvent) {
/* 39 */     if (paramComponentEvent.getComponent() == this.canvas) {
/*    */ 
/*    */ 
/*    */       
/* 43 */       synchronized (this.canvas) {
/* 44 */         synchronized (this.canvas.dirtyMaskLock) {
/* 45 */           this.canvas.cvDirtyMask[0] = this.canvas.cvDirtyMask[0] | 0x8;
/* 46 */           this.canvas.cvDirtyMask[1] = this.canvas.cvDirtyMask[1] | 0x8;
/*    */         } 
/* 48 */         this.canvas.resizeGraphics2D = true;
/*    */       } 
/*    */ 
/*    */       
/*    */       try {
/* 53 */         this.canvas.newSize = this.canvas.getSize();
/* 54 */         this.canvas.newPosition = this.canvas.getLocationOnScreen();
/* 55 */       } catch (IllegalComponentStateException illegalComponentStateException) {}
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentMoved(ComponentEvent paramComponentEvent) {
/* 65 */     synchronized (this.canvas) {
/* 66 */       synchronized (this.canvas.dirtyMaskLock) {
/* 67 */         this.canvas.cvDirtyMask[0] = this.canvas.cvDirtyMask[0] | 0x8;
/* 68 */         this.canvas.cvDirtyMask[1] = this.canvas.cvDirtyMask[1] | 0x8;
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 77 */       this.canvas.newSize = this.canvas.getSize();
/* 78 */       this.canvas.newPosition = this.canvas.getLocationOnScreen();
/* 79 */     } catch (IllegalComponentStateException illegalComponentStateException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\CanvasViewEventCatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */