/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.awt.GraphicsDevice;
/*    */ import sun.awt.Win32GraphicsDevice;
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
/*    */ class Win32NativeScreenInfo
/*    */   extends NativeScreenInfo
/*    */ {
/*    */   private static final long display = 0L;
/*    */   private static boolean wglARBChecked = false;
/*    */   private static boolean isWglARB;
/*    */   
/*    */   private static native boolean queryWglARB();
/*    */   
/*    */   static boolean isWglARB() {
/* 37 */     if (!wglARBChecked) {
/*    */       
/* 39 */       isWglARB = queryWglARB();
/* 40 */       wglARBChecked = true;
/*    */     } 
/* 42 */     return isWglARB;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 47 */   long getDisplay() { return 0L; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   int getScreen(GraphicsDevice paramGraphicsDevice) { return ((Win32GraphicsDevice)paramGraphicsDevice).getScreen(); }
/*    */ 
/*    */ 
/*    */   
/*    */   static  {
/* 57 */     VirtualUniverse.loadLibraries();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Win32NativeScreenInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */