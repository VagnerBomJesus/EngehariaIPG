/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.awt.GraphicsDevice;
/*    */ import sun.awt.X11GraphicsDevice;
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
/*    */ class X11NativeScreenInfo
/*    */   extends NativeScreenInfo
/*    */ {
/* 23 */   private static long display = 0L;
/*    */   
/*    */   private static boolean glxChecked = false;
/*    */   
/*    */   private static boolean isGLX13;
/*    */ 
/*    */   
/*    */   private static native long openDisplay();
/*    */ 
/*    */   
/*    */   private static native boolean queryGLX13(long paramLong);
/*    */ 
/*    */   
/*    */   static boolean isGLX13() {
/* 37 */     if (!glxChecked) {
/*    */       
/* 39 */       getStaticDisplay();
/*    */ 
/*    */       
/* 42 */       isGLX13 = queryGLX13(getStaticDisplay());
/* 43 */       glxChecked = true;
/*    */     } 
/*    */     
/* 46 */     return isGLX13;
/*    */   }
/*    */   
/*    */   private static long getStaticDisplay() {
/* 50 */     if (display == 0L) {
/* 51 */       display = openDisplay();
/*    */     }
/* 53 */     return display;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   long getDisplay() { return getStaticDisplay(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   int getScreen(GraphicsDevice paramGraphicsDevice) { return ((X11GraphicsDevice)paramGraphicsDevice).getScreen(); }
/*    */ 
/*    */ 
/*    */   
/*    */   static  {
/* 70 */     VirtualUniverse.loadLibraries();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\X11NativeScreenInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */