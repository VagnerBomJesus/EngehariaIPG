/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.Rectangle;
/*     */ import sun.awt.X11GraphicsConfig;
/*     */ import sun.awt.X11GraphicsDevice;
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
/*     */ class X11NativeConfigTemplate3D
/*     */   extends NativeConfigTemplate3D
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   
/*     */   native int chooseOglVisual(long paramLong, int paramInt, int[] paramArrayOfInt, long[] paramArrayOfLong);
/*     */   
/*     */   static native void freeFBConfig(long paramLong);
/*     */   
/*     */   native boolean isStereoAvailable(long paramLong, int paramInt1, int paramInt2);
/*     */   
/*     */   native boolean isDoubleBufferAvailable(long paramLong, int paramInt1, int paramInt2);
/*     */   
/*     */   native boolean isSceneAntialiasingAccumAvailable(long paramLong, int paramInt1, int paramInt2);
/*     */   
/*     */   native boolean isSceneAntialiasingMultisampleAvailable(long paramLong, int paramInt1, int paramInt2);
/*     */   
/*     */   native int getStencilSize(long paramLong, int paramInt1, int paramInt2);
/*     */   
/*     */   GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration[] paramArrayOfGraphicsConfiguration) {
/*  56 */     X11GraphicsDevice x11GraphicsDevice = (X11GraphicsDevice)((X11GraphicsConfig)paramArrayOfGraphicsConfiguration[0]).getDevice();
/*     */ 
/*     */     
/*  59 */     if (!X11NativeScreenInfo.isGLX13()) {
/*  60 */       return null;
/*     */     }
/*     */     
/*  63 */     long l = NativeScreenInfo.getNativeScreenInfo().getDisplay();
/*  64 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(x11GraphicsDevice);
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
/*  76 */     Rectangle rectangle = paramArrayOfGraphicsConfiguration[0].getBounds();
/*  77 */     if ((rectangle.x != 0 || rectangle.y != 0) && !VirtualUniverse.mc.xineramaDisabled)
/*     */     {
/*     */ 
/*     */       
/*  81 */       i = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     int[] arrayOfInt = new int[10];
/*     */ 
/*     */     
/*  95 */     arrayOfInt[0] = paramGraphicsConfigTemplate3D.getRedSize();
/*  96 */     arrayOfInt[1] = paramGraphicsConfigTemplate3D.getGreenSize();
/*  97 */     arrayOfInt[2] = paramGraphicsConfigTemplate3D.getBlueSize();
/*     */     
/*  99 */     arrayOfInt[5] = paramGraphicsConfigTemplate3D.getDepthSize();
/* 100 */     arrayOfInt[6] = paramGraphicsConfigTemplate3D.getDoubleBuffer();
/* 101 */     arrayOfInt[7] = paramGraphicsConfigTemplate3D.getStereo();
/* 102 */     arrayOfInt[8] = paramGraphicsConfigTemplate3D.getSceneAntialiasing();
/* 103 */     arrayOfInt[9] = paramGraphicsConfigTemplate3D.getStencilSize();
/*     */ 
/*     */ 
/*     */     
/* 107 */     long[] arrayOfLong = new long[1];
/* 108 */     int j = chooseOglVisual(l, i, arrayOfInt, arrayOfLong);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (j == 0 || arrayOfLong[0] == 0L) {
/* 116 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 121 */     X11GraphicsConfig x11GraphicsConfig1 = null;
/* 122 */     for (byte b = 0; b < paramArrayOfGraphicsConfiguration.length; b++) {
/* 123 */       if (((X11GraphicsConfig)paramArrayOfGraphicsConfiguration[b]).getVisual() == j) {
/* 124 */         x11GraphicsConfig1 = (X11GraphicsConfig)paramArrayOfGraphicsConfiguration[b];
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     if (x11GraphicsConfig1 == null) {
/* 131 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 135 */     X11GraphicsConfig x11GraphicsConfig2 = X11GraphicsConfig.getConfig(x11GraphicsDevice, x11GraphicsConfig1.getVisual(), x11GraphicsConfig1.getDepth(), x11GraphicsConfig1.getColormap(), false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     synchronized (Canvas3D.graphicsConfigTable) {
/* 142 */       if (Canvas3D.graphicsConfigTable.get(x11GraphicsConfig2) == null) {
/* 143 */         GraphicsConfigInfo graphicsConfigInfo = new GraphicsConfigInfo(paramGraphicsConfigTemplate3D);
/* 144 */         graphicsConfigInfo.setPrivateData(new Long(arrayOfLong[0]));
/* 145 */         Canvas3D.graphicsConfigTable.put(x11GraphicsConfig2, graphicsConfigInfo);
/*     */       } else {
/* 147 */         freeFBConfig(arrayOfLong[0]);
/*     */       } 
/*     */     } 
/* 150 */     return x11GraphicsConfig2;
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
/* 161 */     X11GraphicsDevice x11GraphicsDevice = (X11GraphicsDevice)((X11GraphicsConfig)paramGraphicsConfiguration).getDevice();
/*     */ 
/*     */     
/* 164 */     if (!X11NativeScreenInfo.isGLX13()) {
/* 165 */       return false;
/*     */     }
/*     */     
/* 168 */     long l = NativeScreenInfo.getNativeScreenInfo().getDisplay();
/* 169 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(x11GraphicsDevice);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     int[] arrayOfInt = new int[10];
/*     */ 
/*     */     
/* 177 */     arrayOfInt[0] = paramGraphicsConfigTemplate3D.getRedSize();
/* 178 */     arrayOfInt[1] = paramGraphicsConfigTemplate3D.getGreenSize();
/* 179 */     arrayOfInt[2] = paramGraphicsConfigTemplate3D.getBlueSize();
/*     */     
/* 181 */     arrayOfInt[5] = paramGraphicsConfigTemplate3D.getDepthSize();
/* 182 */     arrayOfInt[6] = paramGraphicsConfigTemplate3D.getDoubleBuffer();
/* 183 */     arrayOfInt[7] = paramGraphicsConfigTemplate3D.getStereo();
/* 184 */     arrayOfInt[8] = paramGraphicsConfigTemplate3D.getSceneAntialiasing();
/* 185 */     arrayOfInt[9] = paramGraphicsConfigTemplate3D.getStencilSize();
/*     */ 
/*     */ 
/*     */     
/* 189 */     long[] arrayOfLong = new long[1];
/* 190 */     int j = chooseOglVisual(l, i, arrayOfInt, arrayOfLong);
/*     */     
/* 192 */     if (j == 0 || arrayOfLong[0] == 0L) {
/* 193 */       return false;
/*     */     }
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasStereo(Canvas3D paramCanvas3D) {
/* 202 */     GraphicsConfiguration graphicsConfiguration = paramCanvas3D.graphicsConfiguration;
/*     */     
/* 204 */     X11GraphicsDevice x11GraphicsDevice = (X11GraphicsDevice)((X11GraphicsConfig)graphicsConfiguration).getDevice();
/*     */ 
/*     */     
/* 207 */     long l = NativeScreenInfo.getNativeScreenInfo().getDisplay();
/* 208 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(x11GraphicsDevice);
/* 209 */     int j = ((X11GraphicsConfig)graphicsConfiguration).getVisual();
/*     */     
/* 211 */     return isStereoAvailable(l, i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int getStencilSize(Canvas3D paramCanvas3D) {
/* 217 */     GraphicsConfiguration graphicsConfiguration = paramCanvas3D.graphicsConfiguration;
/*     */     
/* 219 */     X11GraphicsDevice x11GraphicsDevice = (X11GraphicsDevice)((X11GraphicsConfig)graphicsConfiguration).getDevice();
/*     */ 
/*     */     
/* 222 */     long l = NativeScreenInfo.getNativeScreenInfo().getDisplay();
/* 223 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(x11GraphicsDevice);
/* 224 */     int j = ((X11GraphicsConfig)graphicsConfiguration).getVisual();
/*     */     
/* 226 */     return getStencilSize(l, i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasDoubleBuffer(Canvas3D paramCanvas3D) {
/* 232 */     GraphicsConfiguration graphicsConfiguration = paramCanvas3D.graphicsConfiguration;
/*     */     
/* 234 */     X11GraphicsDevice x11GraphicsDevice = (X11GraphicsDevice)((X11GraphicsConfig)graphicsConfiguration).getDevice();
/*     */ 
/*     */     
/* 237 */     long l = NativeScreenInfo.getNativeScreenInfo().getDisplay();
/* 238 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(x11GraphicsDevice);
/* 239 */     int j = ((X11GraphicsConfig)graphicsConfiguration).getVisual();
/*     */     
/* 241 */     return isDoubleBufferAvailable(l, i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasSceneAntialiasingAccum(Canvas3D paramCanvas3D) {
/* 247 */     GraphicsConfiguration graphicsConfiguration = paramCanvas3D.graphicsConfiguration;
/*     */     
/* 249 */     X11GraphicsDevice x11GraphicsDevice = (X11GraphicsDevice)((X11GraphicsConfig)graphicsConfiguration).getDevice();
/*     */ 
/*     */     
/* 252 */     long l = NativeScreenInfo.getNativeScreenInfo().getDisplay();
/* 253 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(x11GraphicsDevice);
/* 254 */     int j = ((X11GraphicsConfig)graphicsConfiguration).getVisual();
/*     */     
/* 256 */     return isSceneAntialiasingAccumAvailable(l, i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasSceneAntialiasingMultisample(Canvas3D paramCanvas3D) {
/* 263 */     GraphicsConfiguration graphicsConfiguration = paramCanvas3D.graphicsConfiguration;
/*     */     
/* 265 */     X11GraphicsDevice x11GraphicsDevice = (X11GraphicsDevice)((X11GraphicsConfig)graphicsConfiguration).getDevice();
/*     */ 
/*     */     
/* 268 */     long l = NativeScreenInfo.getNativeScreenInfo().getDisplay();
/* 269 */     int i = NativeScreenInfo.getNativeScreenInfo().getScreen(x11GraphicsDevice);
/* 270 */     int j = ((X11GraphicsConfig)graphicsConfiguration).getVisual();
/*     */     
/* 272 */     return isSceneAntialiasingMultisampleAvailable(l, i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   static  {
/* 277 */     VirtualUniverse.loadLibraries();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\X11NativeConfigTemplate3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */