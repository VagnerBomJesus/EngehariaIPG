/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.awt.GraphicsDevice;
/*    */ import java.security.AccessController;
/*    */ import java.security.PrivilegedAction;
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
/*    */ abstract class NativeScreenInfo
/*    */ {
/*    */   private static final String x11ClassName = "javax.media.j3d.X11NativeScreenInfo";
/*    */   private static final String win32ClassName = "javax.media.j3d.Win32NativeScreenInfo";
/* 26 */   private static NativeScreenInfo nativeScreenInfo = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static void createNativeScreenInfo() {
/*    */     String str1;
/* 35 */     if (MasterControl.isWindows()) {
/* 36 */       str1 = "javax.media.j3d.Win32NativeScreenInfo";
/*    */     } else {
/* 38 */       str1 = "javax.media.j3d.X11NativeScreenInfo";
/*    */     } 
/*    */     
/* 41 */     final String scrInfoClassName = str1;
/* 42 */     nativeScreenInfo = (NativeScreenInfo)AccessController.doPrivileged(new PrivilegedAction()
/*    */         {
/*    */           public Object run()
/*    */           {
/*    */             try {
/* 47 */               Class clazz = Class.forName(scrInfoClassName);
/* 48 */               return clazz.newInstance();
/* 49 */             } catch (Exception exception) {
/* 50 */               throw new RuntimeException(exception);
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/* 57 */   static NativeScreenInfo getNativeScreenInfo() { return nativeScreenInfo; }
/*    */   
/*    */   abstract long getDisplay();
/*    */   
/*    */   abstract int getScreen(GraphicsDevice paramGraphicsDevice);
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\NativeScreenInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */