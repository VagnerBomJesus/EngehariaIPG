/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class NativeConfigTemplate3D
/*     */ {
/*     */   static final int RED_SIZE = 0;
/*     */   static final int GREEN_SIZE = 1;
/*     */   static final int BLUE_SIZE = 2;
/*     */   static final int ALPHA_SIZE = 3;
/*     */   static final int ACCUM_BUFFER = 4;
/*     */   static final int DEPTH_SIZE = 5;
/*     */   static final int DOUBLEBUFFER = 6;
/*     */   static final int STEREO = 7;
/*     */   static final int ANTIALIASING = 8;
/*     */   static final int STENCIL_SIZE = 9;
/*     */   static final int NUM_ITEMS = 10;
/*     */   private static final String x11ClassName = "javax.media.j3d.X11NativeConfigTemplate3D";
/*     */   private static final String win32ClassName = "javax.media.j3d.Win32NativeConfigTemplate3D";
/*  39 */   private static NativeConfigTemplate3D nativeConfigTemplate3D = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void createNativeConfigTemplate3D() {
/*     */     String str1;
/*  48 */     if (MasterControl.isWindows()) {
/*  49 */       str1 = "javax.media.j3d.Win32NativeConfigTemplate3D";
/*     */     } else {
/*  51 */       str1 = "javax.media.j3d.X11NativeConfigTemplate3D";
/*     */     } 
/*     */     
/*  54 */     final String templateClassName = str1;
/*  55 */     nativeConfigTemplate3D = (NativeConfigTemplate3D)AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/*     */             try {
/*  60 */               Class clazz = Class.forName(templateClassName);
/*  61 */               return clazz.newInstance();
/*  62 */             } catch (Exception exception) {
/*  63 */               throw new RuntimeException(exception);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*  70 */   static NativeConfigTemplate3D getNativeConfigTemplate3D() { return nativeConfigTemplate3D; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration[] paramArrayOfGraphicsConfiguration);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean isGraphicsConfigSupported(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration paramGraphicsConfiguration);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean hasStereo(Canvas3D paramCanvas3D);
/*     */ 
/*     */ 
/*     */   
/*     */   abstract int getStencilSize(Canvas3D paramCanvas3D);
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean hasDoubleBuffer(Canvas3D paramCanvas3D);
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean hasSceneAntialiasingAccum(Canvas3D paramCanvas3D);
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean hasSceneAntialiasingMultisample(Canvas3D paramCanvas3D);
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/* 106 */     VirtualUniverse.loadLibraries();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\NativeConfigTemplate3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */