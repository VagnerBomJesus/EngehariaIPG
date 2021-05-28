/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import sun.awt.Win32GraphicsConfig;
/*     */ import sun.awt.Win32GraphicsDevice;
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
/*     */ class Win32NativeConfigTemplate3D
/*     */   extends NativeConfigTemplate3D
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   
/*     */   native int choosePixelFormat(long paramLong, int paramInt, int[] paramArrayOfInt, long[] paramArrayOfLong);
/*     */   
/*     */   static native void freePixelFormatInfo(long paramLong);
/*     */   
/*     */   native boolean isStereoAvailable(long paramLong, boolean paramBoolean);
/*     */   
/*     */   native boolean isDoubleBufferAvailable(long paramLong, boolean paramBoolean);
/*     */   
/*     */   native boolean isSceneAntialiasingAccumAvailable(long paramLong, boolean paramBoolean);
/*     */   
/*     */   native boolean isSceneAntialiasingMultisampleAvailable(long paramLong, boolean paramBoolean, int paramInt);
/*     */   
/*     */   native int getStencilSize(long paramLong, boolean paramBoolean);
/*     */   
/*     */   GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration[] paramArrayOfGraphicsConfiguration) {
/*  57 */     Win32GraphicsDevice win32GraphicsDevice = (Win32GraphicsDevice)((Win32GraphicsConfig)paramArrayOfGraphicsConfiguration[0]).getDevice();
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
/*  73 */     int[] arrayOfInt = new int[10];
/*     */     
/*  75 */     arrayOfInt[0] = paramGraphicsConfigTemplate3D.getRedSize();
/*  76 */     arrayOfInt[1] = paramGraphicsConfigTemplate3D.getGreenSize();
/*  77 */     arrayOfInt[2] = paramGraphicsConfigTemplate3D.getBlueSize();
/*     */     
/*  79 */     arrayOfInt[5] = paramGraphicsConfigTemplate3D.getDepthSize();
/*  80 */     arrayOfInt[6] = paramGraphicsConfigTemplate3D.getDoubleBuffer();
/*  81 */     arrayOfInt[7] = paramGraphicsConfigTemplate3D.getStereo();
/*  82 */     arrayOfInt[8] = paramGraphicsConfigTemplate3D.getSceneAntialiasing();
/*  83 */     arrayOfInt[9] = paramGraphicsConfigTemplate3D.getStencilSize();
/*     */ 
/*     */ 
/*     */     
/*  87 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(win32GraphicsDevice);
/*     */     
/*  89 */     long[] arrayOfLong = new long[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     arrayOfLong[0] = -1L;
/*     */     
/*  98 */     int j = choosePixelFormat(0L, i, arrayOfInt, arrayOfLong);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (j < 0)
/*     */     {
/* 107 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     Win32GraphicsConfig win32GraphicsConfig = Win32GraphicsConfig.getConfig(win32GraphicsDevice, 0);
/*     */ 
/*     */ 
/*     */     
/* 117 */     synchronized (Canvas3D.graphicsConfigTable) {
/* 118 */       if (Canvas3D.graphicsConfigTable.get(win32GraphicsConfig) == null) {
/* 119 */         GraphicsConfigInfo graphicsConfigInfo = new GraphicsConfigInfo(paramGraphicsConfigTemplate3D);
/* 120 */         graphicsConfigInfo.setPrivateData(new Long(arrayOfLong[0]));
/* 121 */         Canvas3D.graphicsConfigTable.put(win32GraphicsConfig, graphicsConfigInfo);
/*     */       } else {
/* 123 */         freePixelFormatInfo(arrayOfLong[0]);
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     return win32GraphicsConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isGraphicsConfigSupported(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration paramGraphicsConfiguration) {
/* 138 */     Win32GraphicsDevice win32GraphicsDevice = (Win32GraphicsDevice)((Win32GraphicsConfig)paramGraphicsConfiguration).getDevice();
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
/* 154 */     int[] arrayOfInt = new int[10];
/*     */     
/* 156 */     arrayOfInt[0] = paramGraphicsConfigTemplate3D.getRedSize();
/* 157 */     arrayOfInt[1] = paramGraphicsConfigTemplate3D.getGreenSize();
/* 158 */     arrayOfInt[2] = paramGraphicsConfigTemplate3D.getBlueSize();
/*     */     
/* 160 */     arrayOfInt[5] = paramGraphicsConfigTemplate3D.getDepthSize();
/* 161 */     arrayOfInt[6] = paramGraphicsConfigTemplate3D.getDoubleBuffer();
/* 162 */     arrayOfInt[7] = paramGraphicsConfigTemplate3D.getStereo();
/* 163 */     arrayOfInt[8] = paramGraphicsConfigTemplate3D.getSceneAntialiasing();
/* 164 */     arrayOfInt[9] = paramGraphicsConfigTemplate3D.getStencilSize();
/*     */ 
/*     */ 
/*     */     
/* 168 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(win32GraphicsDevice);
/*     */     
/* 170 */     long[] arrayOfLong = new long[1];
/*     */     
/* 172 */     int j = choosePixelFormat(0L, i, arrayOfInt, arrayOfLong);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     if (j < 0)
/*     */     {
/* 181 */       return false;
/*     */     }
/* 183 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   boolean hasStereo(Canvas3D paramCanvas3D) { return isStereoAvailable(paramCanvas3D.fbConfig, paramCanvas3D.offScreen); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   int getStencilSize(Canvas3D paramCanvas3D) { return getStencilSize(paramCanvas3D.fbConfig, paramCanvas3D.offScreen); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   boolean hasDoubleBuffer(Canvas3D paramCanvas3D) { return isDoubleBufferAvailable(paramCanvas3D.fbConfig, paramCanvas3D.offScreen); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   boolean hasSceneAntialiasingAccum(Canvas3D paramCanvas3D) { return isSceneAntialiasingAccumAvailable(paramCanvas3D.fbConfig, paramCanvas3D.offScreen); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasSceneAntialiasingMultisample(Canvas3D paramCanvas3D) {
/* 214 */     GraphicsConfiguration graphicsConfiguration = paramCanvas3D.graphicsConfiguration;
/*     */     
/* 216 */     Win32GraphicsDevice win32GraphicsDevice = (Win32GraphicsDevice)((Win32GraphicsConfig)graphicsConfiguration).getDevice();
/*     */     
/* 218 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(win32GraphicsDevice);
/*     */     
/* 220 */     return isSceneAntialiasingMultisampleAvailable(paramCanvas3D.fbConfig, paramCanvas3D.offScreen, i);
/*     */   }
/*     */ 
/*     */   
/*     */   static  {
/* 225 */     VirtualUniverse.loadLibraries();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Win32NativeConfigTemplate3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */