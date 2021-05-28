/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.AWTError;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.Toolkit;
/*      */ import java.io.File;
/*      */ import java.lang.reflect.Method;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class NativePipeline
/*      */   extends Pipeline
/*      */ {
/*   30 */   private static final String[] systemPathProps = { "sun.boot.library.path", "java.library.path" };
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String libPrefix = "j3dcore";
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isD3D;
/*      */ 
/*      */   
/*      */   private String rendererName;
/*      */ 
/*      */   
/*      */   private boolean cgLibraryAvailable = false;
/*      */ 
/*      */   
/*      */   private boolean glslLibraryAvailable = false;
/*      */ 
/*      */   
/*      */   private static boolean oglChkLibraryLoaded = false;
/*      */ 
/*      */ 
/*      */   
/*      */   static String getSupportedOglVendor() {
/*   55 */     if (!oglChkLibraryLoaded) {
/*      */       try {
/*   57 */         loadLibrary("j3dcore-ogl-chk", true);
/*   58 */       } catch (Exception exception) {
/*   59 */         MasterControl.getCoreLogger().severe(exception.toString());
/*   60 */         return null;
/*   61 */       } catch (Error error) {
/*   62 */         MasterControl.getCoreLogger().severe(error.toString());
/*   63 */         return null;
/*      */       } 
/*   65 */       oglChkLibraryLoaded = true;
/*      */     } 
/*   67 */     return getSupportedOglVendorNative();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initialize(Pipeline.Type paramType) {
/*   83 */     super.initialize(paramType);
/*      */ 
/*      */     
/*      */     try {
/*   87 */       Toolkit toolkit = Toolkit.getDefaultToolkit();
/*   88 */       toolkit = null;
/*   89 */     } catch (AWTError aWTError) {}
/*      */ 
/*      */     
/*   92 */     switch (paramType) {
/*      */       case NATIVE_OGL:
/*   94 */         this.isD3D = false;
/*   95 */         this.rendererName = "ogl";
/*      */         break;
/*      */       case NATIVE_D3D:
/*   98 */         this.isD3D = true;
/*   99 */         this.rendererName = "d3d";
/*      */         break;
/*      */       
/*      */       default:
/*      */         assert false;
/*      */         break;
/*      */     } 
/*      */     
/*  107 */     NativeConfigTemplate3D.createNativeConfigTemplate3D();
/*  108 */     NativeScreenInfo.createNativeScreenInfo();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void loadLibraries(int paramInt) {
/*      */     try {
/*  122 */       loadLibrary("jawt", false);
/*  123 */     } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
/*  124 */       if (unsatisfiedLinkError.getMessage().indexOf("already loaded") == -1)
/*      */       {
/*      */         
/*  127 */         MasterControl.getCoreLogger().severe(unsatisfiedLinkError.toString());
/*      */       }
/*  129 */     } catch (Error error) {
/*  130 */       MasterControl.getCoreLogger().severe(error.toString());
/*  131 */     } catch (Exception exception) {
/*  132 */       MasterControl.getCoreLogger().severe(exception.toString());
/*      */     } 
/*      */ 
/*      */     
/*  136 */     String str = "j3dcore-" + this.rendererName;
/*  137 */     loadLibrary(str, true);
/*      */ 
/*      */     
/*  140 */     if (paramInt == 2) {
/*  141 */       String str1 = "j3dcore-" + this.rendererName + "-cg";
/*  142 */       String[] arrayOfString = setupLibPath(str1);
/*  143 */       this.cgLibraryAvailable = loadNativeCgLibrary(arrayOfString);
/*      */     } 
/*      */ 
/*      */     
/*  147 */     if (paramInt == 1 && 
/*  148 */       getPipelineType() == Pipeline.Type.NATIVE_OGL)
/*      */     {
/*      */       
/*  151 */       this.glslLibraryAvailable = true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  161 */   boolean isCgLibraryAvailable() { return this.cgLibraryAvailable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  169 */   boolean isGLSLLibraryAvailable() { return this.glslLibraryAvailable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void loadLibrary(final String libName, boolean paramBoolean) {
/*  179 */     final boolean useAppletLauncher = (paramBoolean && MasterControl.isAppletLauncher());
/*  180 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run() {
/*      */             try {
/*  184 */               if (useAppletLauncher) {
/*  185 */                 Class clazz = Class.forName("org.jdesktop.applet.util.JNLPAppletLauncher");
/*  186 */                 Method method = clazz.getDeclaredMethod("loadLibrary", new Class[] { String.class });
/*  187 */                 method.invoke(null, new Object[] { libName });
/*      */               } else {
/*  189 */                 System.loadLibrary(libName);
/*      */               } 
/*  191 */             } catch (Exception exception) {
/*  192 */               throw new RuntimeException(exception);
/*      */             } 
/*  194 */             return null;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String[] setupLibPath(String paramString) {
/*  207 */     final String libraryName = paramString;
/*  208 */     return (String[])AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  212 */             ArrayList arrayList = new ArrayList();
/*      */             
/*  214 */             String str = System.mapLibraryName(libraryName);
/*  215 */             for (byte b = 0; b < systemPathProps.length; b++) {
/*  216 */               String str1 = System.getProperty(systemPathProps[b]);
/*  217 */               boolean bool = false;
/*  218 */               int i = 0;
/*  219 */               while (!bool) {
/*  220 */                 int j = str1.indexOf(File.pathSeparator, i);
/*  221 */                 if (j == -1) {
/*  222 */                   j = str1.length();
/*  223 */                   bool = true;
/*      */                 } 
/*  225 */                 String str2 = str1.substring(i, j);
/*  226 */                 File file = new File(str2, str);
/*  227 */                 if (file.exists()) {
/*  228 */                   arrayList.add(file.getAbsolutePath());
/*      */                 }
/*      */                 
/*  231 */                 i = j + 1;
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*  236 */             if (arrayList.size() == 0) {
/*  237 */               arrayList.add(str);
/*      */             }
/*      */             
/*  240 */             return (String[])arrayList.toArray(new String[0]);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long unbox(Context paramContext) {
/*  254 */     if (paramContext == null) {
/*  255 */       return 0L;
/*      */     }
/*  257 */     return ((NativeContext)paramContext).getNativeCtx();
/*      */   }
/*      */ 
/*      */   
/*      */   private Context boxContext(long paramLong) {
/*  262 */     if (paramLong == 0L) {
/*  263 */       return null;
/*      */     }
/*  265 */     return new NativeContext(paramLong);
/*      */   }
/*      */ 
/*      */   
/*      */   private long unbox(Drawable paramDrawable) {
/*  270 */     if (paramDrawable == null) {
/*  271 */       return 0L;
/*      */     }
/*  273 */     return ((NativeDrawable)paramDrawable).getNativeDrawable();
/*      */   }
/*      */ 
/*      */   
/*      */   private Drawable boxDrawable(long paramLong) {
/*  278 */     if (paramLong == 0L) {
/*  279 */       return null;
/*      */     }
/*  281 */     return new NativeDrawable(paramLong);
/*      */   }
/*      */ 
/*      */   
/*      */   private long unbox(ShaderProgramId paramShaderProgramId) {
/*  286 */     if (paramShaderProgramId == null) {
/*  287 */       return 0L;
/*      */     }
/*  289 */     return ((NativeShaderObject)paramShaderProgramId).getNativeId();
/*      */   }
/*      */ 
/*      */   
/*      */   private ShaderProgramId boxShaderProgramId(long paramLong) {
/*  294 */     if (paramLong == 0L) {
/*  295 */       return null;
/*      */     }
/*  297 */     return new NativeShaderObject(paramLong);
/*      */   }
/*      */ 
/*      */   
/*      */   private long unbox(ShaderId paramShaderId) {
/*  302 */     if (paramShaderId == null) {
/*  303 */       return 0L;
/*      */     }
/*  305 */     return ((NativeShaderObject)paramShaderId).getNativeId();
/*      */   }
/*      */ 
/*      */   
/*      */   private ShaderId boxShaderId(long paramLong) {
/*  310 */     if (paramLong == 0L) {
/*  311 */       return null;
/*      */     }
/*  313 */     return new NativeShaderObject(paramLong);
/*      */   }
/*      */ 
/*      */   
/*      */   private long unbox(ShaderAttrLoc paramShaderAttrLoc) {
/*  318 */     if (paramShaderAttrLoc == null) {
/*  319 */       return -1L;
/*      */     }
/*  321 */     return ((NativeShaderObject)paramShaderAttrLoc).getNativeId();
/*      */   }
/*      */ 
/*      */   
/*      */   private ShaderAttrLoc boxShaderAttrLoc(long paramLong) {
/*  326 */     if (paramLong == -1L) {
/*  327 */       return null;
/*      */     }
/*  329 */     return new NativeShaderObject(paramLong);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  368 */   void execute(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int paramInt8, int[] paramArrayOfInt3, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt9) { execute(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramArrayOfInt1, paramInt6, paramArrayOfInt2, paramInt7, paramInt8, paramArrayOfInt3, paramArrayOfFloat1, paramArrayOfFloat2, paramInt9); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  418 */   void executeVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject, int paramInt12) { executeVA(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, paramArrayOfFloat1, paramArrayOfDouble, paramInt6, paramArrayOfFloat2, paramArrayOfByte, paramInt7, paramArrayOfFloat3, paramInt8, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfFloat, paramInt9, paramArrayOfInt3, paramInt10, paramArrayOfInt4, paramInt11, paramArrayOfObject, paramInt12); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  479 */   void executeVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Object paramObject1, int paramInt6, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, int paramInt7, Object paramObject3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, Object[] paramArrayOfObject1, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject2, int paramInt12) { executeVABuffer(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, paramObject1, paramInt6, paramObject2, paramArrayOfFloat, paramArrayOfByte, paramInt7, paramObject3, paramInt8, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfObject1, paramInt9, paramArrayOfInt3, paramInt10, paramArrayOfInt4, paramInt11, paramArrayOfObject2, paramInt12); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  525 */   void executeInterleavedBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, Object paramObject, float[] paramArrayOfFloat, int paramInt8) { executeInterleavedBuffer(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramArrayOfInt1, paramInt6, paramArrayOfInt2, paramInt7, paramObject, paramArrayOfFloat, paramInt8); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  543 */   void setVertexFormat(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2) { setVertexFormat(unbox(paramContext), paramGeometryArrayRetained, paramInt, paramBoolean1, paramBoolean2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  552 */   void disableGlobalAlpha(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2) { disableGlobalAlpha(unbox(paramContext), paramGeometryArrayRetained, paramInt, paramBoolean1, paramBoolean2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  582 */   void buildGA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat) { buildGA(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramFloat, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramArrayOfInt1, paramInt6, paramArrayOfInt2, paramInt7, paramArrayOfInt3, paramArrayOfDouble1, paramArrayOfDouble2, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  632 */   void buildGAForByRef(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble1, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt10, Object[] paramArrayOfObject, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3) { buildGAForByRef(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramFloat, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramArrayOfFloat1, paramArrayOfDouble1, paramInt6, paramArrayOfFloat2, paramArrayOfByte, paramInt7, paramArrayOfFloat3, paramInt8, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfFloat, paramInt9, paramArrayOfInt3, paramArrayOfInt4, paramInt10, paramArrayOfObject, paramArrayOfDouble2, paramArrayOfDouble3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  691 */   void executeIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, int paramInt9, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt10, int[] paramArrayOfInt4) { executeIndexedGeometry(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramArrayOfInt1, paramInt7, paramArrayOfInt2, paramInt8, paramArrayOfInt3, paramInt9, paramArrayOfFloat1, paramArrayOfFloat2, paramInt10, paramArrayOfInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  741 */   void executeIndexedGeometryBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, Object paramObject, float[] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3) { executeIndexedGeometryBuffer(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramArrayOfInt1, paramInt7, paramArrayOfInt2, paramInt8, paramObject, paramArrayOfFloat, paramInt9, paramArrayOfInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  800 */   void executeIndexedGeometryVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, float[] paramArrayOfFloat3, int paramInt7, int[] paramArrayOfInt1, float[][] paramArrayOfFloat, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject, int paramInt11, int[] paramArrayOfInt3) { executeIndexedGeometryVA(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramArrayOfFloat1, paramArrayOfDouble, paramArrayOfFloat2, paramArrayOfByte, paramArrayOfFloat3, paramInt7, paramArrayOfInt1, paramArrayOfFloat, paramInt8, paramArrayOfInt2, paramInt9, paramInt10, paramArrayOfObject, paramInt11, paramArrayOfInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  866 */   void executeIndexedGeometryVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, Object paramObject3, int paramInt7, int[] paramArrayOfInt1, Object[] paramArrayOfObject1, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject2, int paramInt11, int[] paramArrayOfInt3) { executeIndexedGeometryVABuffer(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramObject1, paramObject2, paramArrayOfFloat, paramArrayOfByte, paramObject3, paramInt7, paramArrayOfInt1, paramArrayOfObject1, paramInt8, paramArrayOfInt2, paramInt9, paramInt10, paramArrayOfObject2, paramInt11, paramArrayOfInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  921 */   void buildIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat, int[] paramArrayOfInt4) { buildIndexedGeometry(unbox(paramContext), paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramFloat, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramArrayOfInt1, paramInt7, paramArrayOfInt2, paramInt8, paramArrayOfInt3, paramArrayOfDouble1, paramArrayOfDouble2, paramArrayOfFloat, paramArrayOfInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  962 */   void readRaster(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject1, int paramInt9, Object paramObject2) { readRaster(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramObject1, paramInt9, paramObject2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  987 */   ShaderError setCgUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) { return setCgUniform1i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1002 */   ShaderError setCgUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) { return setCgUniform1f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1017 */   ShaderError setCgUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return setCgUniform2i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1032 */   ShaderError setCgUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setCgUniform2f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1047 */   ShaderError setCgUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return setCgUniform3i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1062 */   ShaderError setCgUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setCgUniform3f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1077 */   ShaderError setCgUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return setCgUniform4i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1092 */   ShaderError setCgUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setCgUniform4f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1107 */   ShaderError setCgUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setCgUniformMatrix3f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1122 */   ShaderError setCgUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setCgUniformMatrix4f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1141 */   ShaderError setCgUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setCgUniform1iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1159 */   ShaderError setCgUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setCgUniform1fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1177 */   ShaderError setCgUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setCgUniform2iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1195 */   ShaderError setCgUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setCgUniform2fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1213 */   ShaderError setCgUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setCgUniform3iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1231 */   ShaderError setCgUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setCgUniform3fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1249 */   ShaderError setCgUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setCgUniform4iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1267 */   ShaderError setCgUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setCgUniform4fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1285 */   ShaderError setCgUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setCgUniformMatrix3fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1303 */   ShaderError setCgUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setCgUniformMatrix4fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createCgShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId) {
/* 1314 */     long[] arrayOfLong = new long[1];
/* 1315 */     ShaderError shaderError = createCgShader(unbox(paramContext), paramInt, arrayOfLong);
/* 1316 */     paramArrayOfShaderId[0] = boxShaderId(arrayOfLong[0]);
/* 1317 */     return shaderError;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1323 */   ShaderError destroyCgShader(Context paramContext, ShaderId paramShaderId) { return destroyCgShader(unbox(paramContext), unbox(paramShaderId)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1329 */   ShaderError compileCgShader(Context paramContext, ShaderId paramShaderId, String paramString) { return compileCgShader(unbox(paramContext), unbox(paramShaderId), paramString); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createCgShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) {
/* 1335 */     long[] arrayOfLong = new long[1];
/* 1336 */     ShaderError shaderError = createCgShaderProgram(unbox(paramContext), arrayOfLong);
/* 1337 */     paramArrayOfShaderProgramId[0] = boxShaderProgramId(arrayOfLong[0]);
/* 1338 */     return shaderError;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1344 */   ShaderError destroyCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return destroyCgShaderProgram(unbox(paramContext), unbox(paramShaderProgramId)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError linkCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) {
/* 1353 */     assert paramArrayOfShaderId != null;
/* 1354 */     long[] arrayOfLong = new long[paramArrayOfShaderId.length];
/* 1355 */     for (byte b = 0; b < paramArrayOfShaderId.length; b++) {
/* 1356 */       arrayOfLong[b] = unbox(paramArrayOfShaderId[b]);
/*      */     }
/* 1358 */     return linkCgShaderProgram(unbox(paramContext), unbox(paramShaderProgramId), arrayOfLong);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1367 */   void lookupCgVertexAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, boolean[] paramArrayOfBoolean) { lookupCgVertexAttrNames(unbox(paramContext), unbox(paramShaderProgramId), paramInt, paramArrayOfString, paramArrayOfBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupCgShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean) {
/* 1379 */     assert paramInt == paramArrayOfShaderAttrLoc.length;
/* 1380 */     long[] arrayOfLong = new long[paramInt]; byte b;
/* 1381 */     for (b = 0; b < paramInt; b++)
/*      */     {
/* 1383 */       arrayOfLong[b] = -1L;
/*      */     }
/* 1385 */     lookupCgShaderAttrNames(unbox(paramContext), unbox(paramShaderProgramId), paramInt, paramArrayOfString, arrayOfLong, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfBoolean);
/*      */ 
/*      */     
/* 1388 */     for (b = 0; b < paramInt; b++) {
/* 1389 */       paramArrayOfShaderAttrLoc[b] = boxShaderAttrLoc(arrayOfLong[b]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1396 */   ShaderError useCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return useCgShaderProgram(unbox(paramContext), unbox(paramShaderProgramId)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1416 */   ShaderError setGLSLUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) { return setGLSLUniform1i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1431 */   ShaderError setGLSLUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) { return setGLSLUniform1f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1446 */   ShaderError setGLSLUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return setGLSLUniform2i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1461 */   ShaderError setGLSLUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setGLSLUniform2f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1476 */   ShaderError setGLSLUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return setGLSLUniform3i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1491 */   ShaderError setGLSLUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setGLSLUniform3f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1506 */   ShaderError setGLSLUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return setGLSLUniform4i(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1521 */   ShaderError setGLSLUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setGLSLUniform4f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1536 */   ShaderError setGLSLUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setGLSLUniformMatrix3f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1551 */   ShaderError setGLSLUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return setGLSLUniformMatrix4f(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1570 */   ShaderError setGLSLUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setGLSLUniform1iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1588 */   ShaderError setGLSLUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setGLSLUniform1fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1606 */   ShaderError setGLSLUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setGLSLUniform2iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1624 */   ShaderError setGLSLUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setGLSLUniform2fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1642 */   ShaderError setGLSLUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setGLSLUniform3iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1660 */   ShaderError setGLSLUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setGLSLUniform3fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1678 */   ShaderError setGLSLUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return setGLSLUniform4iArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1696 */   ShaderError setGLSLUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setGLSLUniform4fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1714 */   ShaderError setGLSLUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setGLSLUniformMatrix3fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1732 */   ShaderError setGLSLUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return setGLSLUniformMatrix4fArray(unbox(paramContext), unbox(paramShaderProgramId), unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createGLSLShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId) {
/* 1743 */     long[] arrayOfLong = new long[1];
/* 1744 */     ShaderError shaderError = createGLSLShader(unbox(paramContext), paramInt, arrayOfLong);
/* 1745 */     paramArrayOfShaderId[0] = boxShaderId(arrayOfLong[0]);
/* 1746 */     return shaderError;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1751 */   ShaderError destroyGLSLShader(Context paramContext, ShaderId paramShaderId) { return destroyGLSLShader(unbox(paramContext), unbox(paramShaderId)); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1756 */   ShaderError compileGLSLShader(Context paramContext, ShaderId paramShaderId, String paramString) { return compileGLSLShader(unbox(paramContext), unbox(paramShaderId), paramString); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createGLSLShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) {
/* 1762 */     long[] arrayOfLong = new long[1];
/* 1763 */     ShaderError shaderError = createGLSLShaderProgram(unbox(paramContext), arrayOfLong);
/* 1764 */     paramArrayOfShaderProgramId[0] = boxShaderProgramId(arrayOfLong[0]);
/* 1765 */     return shaderError;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1770 */   ShaderError destroyGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return destroyGLSLShaderProgram(unbox(paramContext), unbox(paramShaderProgramId)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError linkGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) {
/* 1777 */     assert paramArrayOfShaderId != null;
/* 1778 */     long[] arrayOfLong = new long[paramArrayOfShaderId.length];
/* 1779 */     for (byte b = 0; b < paramArrayOfShaderId.length; b++) {
/* 1780 */       arrayOfLong[b] = unbox(paramArrayOfShaderId[b]);
/*      */     }
/* 1782 */     return linkGLSLShaderProgram(unbox(paramContext), unbox(paramShaderProgramId), arrayOfLong);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1790 */   ShaderError bindGLSLVertexAttrName(Context paramContext, ShaderProgramId paramShaderProgramId, String paramString, int paramInt) { return bindGLSLVertexAttrName(unbox(paramContext), unbox(paramShaderProgramId), paramString, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupGLSLShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean) {
/* 1801 */     assert paramInt == paramArrayOfShaderAttrLoc.length;
/* 1802 */     long[] arrayOfLong = new long[paramInt]; byte b;
/* 1803 */     for (b = 0; b < paramInt; b++)
/*      */     {
/* 1805 */       arrayOfLong[b] = -1L;
/*      */     }
/* 1807 */     lookupGLSLShaderAttrNames(unbox(paramContext), unbox(paramShaderProgramId), paramInt, paramArrayOfString, arrayOfLong, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfBoolean);
/*      */ 
/*      */     
/* 1810 */     for (b = 0; b < paramInt; b++) {
/* 1811 */       paramArrayOfShaderAttrLoc[b] = boxShaderAttrLoc(arrayOfLong[b]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1818 */   ShaderError useGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return useGLSLShaderProgram(unbox(paramContext), unbox(paramShaderProgramId)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1850 */   void updateColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean, int paramInt) { updateColoringAttributes(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramBoolean, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1872 */   void updateDirectionalLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) { updateDirectionalLight(unbox(paramContext), paramInt, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1893 */   void updatePointLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9) { updatePointLight(unbox(paramContext), paramInt, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1919 */   void updateSpotLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14) { updateSpotLight(unbox(paramContext), paramInt, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1941 */   void updateExponentialFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateExponentialFog(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1960 */   void updateLinearFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble1, double paramDouble2) { updateLinearFog(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramDouble1, paramDouble2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1983 */   void updateLineAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) { updateLineAttributes(unbox(paramContext), paramFloat, paramInt1, paramInt2, paramInt3, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2012 */   void updateMaterial(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, float paramFloat17, int paramInt, boolean paramBoolean) { updateMaterial(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14, paramFloat15, paramFloat16, paramFloat17, paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2033 */   void updateModelClip(Context paramContext, int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) { updateModelClip(unbox(paramContext), paramInt, paramBoolean, paramDouble1, paramDouble2, paramDouble3, paramDouble4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2047 */   void updatePointAttributes(Context paramContext, float paramFloat, boolean paramBoolean) { updatePointAttributes(unbox(paramContext), paramFloat, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2068 */   void updatePolygonAttributes(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2) { updatePolygonAttributes(unbox(paramContext), paramInt1, paramInt2, paramBoolean, paramFloat1, paramFloat2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2110 */   void updateRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, float paramFloat, int paramInt2, boolean paramBoolean5, boolean paramBoolean6, int paramInt3, boolean paramBoolean7, boolean paramBoolean8, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10) { updateRenderingAttributes(unbox(paramContext), paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramInt1, paramFloat, paramInt2, paramBoolean5, paramBoolean6, paramInt3, paramBoolean7, paramBoolean8, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2152 */   void updateTexCoordGeneration(Context paramContext, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, double[] paramArrayOfDouble) { updateTexCoordGeneration(unbox(paramContext), paramBoolean, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14, paramFloat15, paramFloat16, paramArrayOfDouble); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2183 */   void updateTransparencyAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, int paramInt4, int paramInt5) { updateTransparencyAttributes(unbox(paramContext), paramFloat, paramInt1, paramInt2, paramBoolean1, paramBoolean2, paramInt3, paramInt4, paramInt5); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2210 */   void updateTextureAttributes(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3) { updateTextureAttributes(unbox(paramContext), paramArrayOfDouble, paramBoolean, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2236 */   void updateRegisterCombiners(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt6, int paramInt7) { updateRegisterCombiners(unbox(paramContext), paramArrayOfDouble, paramBoolean, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt3, paramInt4, paramInt5, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, paramArrayOfInt4, paramInt6, paramInt7); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2254 */   void updateTextureColorTable(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt) { updateTextureColorTable(unbox(paramContext), paramInt1, paramInt2, paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2270 */   void updateCombiner(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt3, int paramInt4) { updateCombiner(unbox(paramContext), paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, paramArrayOfInt4, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2287 */   void updateTextureUnitState(Context paramContext, int paramInt, boolean paramBoolean) { updateTextureUnitState(unbox(paramContext), paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2301 */   void bindTexture2D(Context paramContext, int paramInt, boolean paramBoolean) { bindTexture2D(unbox(paramContext), paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2317 */   void updateTexture2DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject, boolean paramBoolean) { updateTexture2DImage(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramObject, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2338 */   void updateTexture2DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, Object paramObject, boolean paramBoolean) { updateTexture2DSubImage(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramObject, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2353 */   void updateTexture2DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { updateTexture2DLodRange(unbox(paramContext), paramInt1, paramInt2, paramFloat1, paramFloat2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2365 */   void updateTexture2DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { updateTexture2DLodOffset(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2379 */   void updateTexture2DBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateTexture2DBoundary(unbox(paramContext), paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2390 */   void updateTexture2DFilterModes(Context paramContext, int paramInt1, int paramInt2) { updateTexture2DFilterModes(unbox(paramContext), paramInt1, paramInt2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2401 */   void updateTexture2DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTexture2DSharpenFunc(unbox(paramContext), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2413 */   void updateTexture2DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTexture2DFilter4Func(unbox(paramContext), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2421 */   void updateTexture2DAnisotropicFilter(Context paramContext, float paramFloat) { updateTexture2DAnisotropicFilter(unbox(paramContext), paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2434 */   void bindTexture3D(Context paramContext, int paramInt, boolean paramBoolean) { bindTexture3D(unbox(paramContext), paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2450 */   void updateTexture3DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean) { updateTexture3DImage(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramObject, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2475 */   void updateTexture3DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, Object paramObject, boolean paramBoolean) { updateTexture3DSubImage(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramInt12, paramInt13, paramInt14, paramInt15, paramObject, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2492 */   void updateTexture3DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { updateTexture3DLodRange(unbox(paramContext), paramInt1, paramInt2, paramFloat1, paramFloat2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2504 */   void updateTexture3DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { updateTexture3DLodOffset(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2521 */   void updateTexture3DBoundary(Context paramContext, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateTexture3DBoundary(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2533 */   void updateTexture3DFilterModes(Context paramContext, int paramInt1, int paramInt2) { updateTexture3DFilterModes(unbox(paramContext), paramInt1, paramInt2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2544 */   void updateTexture3DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTexture3DSharpenFunc(unbox(paramContext), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2556 */   void updateTexture3DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTexture3DFilter4Func(unbox(paramContext), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2564 */   void updateTexture3DAnisotropicFilter(Context paramContext, float paramFloat) { updateTexture3DAnisotropicFilter(unbox(paramContext), paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2577 */   void bindTextureCubeMap(Context paramContext, int paramInt, boolean paramBoolean) { bindTextureCubeMap(unbox(paramContext), paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2593 */   void updateTextureCubeMapImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean) { updateTextureCubeMapImage(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramObject, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2614 */   void updateTextureCubeMapSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject, boolean paramBoolean) { updateTextureCubeMapSubImage(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramInt12, paramObject, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2629 */   void updateTextureCubeMapLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { updateTextureCubeMapLodRange(unbox(paramContext), paramInt1, paramInt2, paramFloat1, paramFloat2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2641 */   void updateTextureCubeMapLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { updateTextureCubeMapLodOffset(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2655 */   void updateTextureCubeMapBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateTextureCubeMapBoundary(unbox(paramContext), paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2666 */   void updateTextureCubeMapFilterModes(Context paramContext, int paramInt1, int paramInt2) { updateTextureCubeMapFilterModes(unbox(paramContext), paramInt1, paramInt2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2677 */   void updateTextureCubeMapSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureCubeMapSharpenFunc(unbox(paramContext), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2689 */   void updateTextureCubeMapFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureCubeMapFilter4Func(unbox(paramContext), paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2697 */   void updateTextureCubeMapAnisotropicFilter(Context paramContext, float paramFloat) { updateTextureCubeMapAnisotropicFilter(unbox(paramContext), paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Context createNewContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
/* 2736 */     long l = createNewContext(paramCanvas3D, paramLong1, unbox(paramDrawable), paramLong2, unbox(paramContext), paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2742 */     return boxContext(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2755 */   void createQueryContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, boolean paramBoolean3) { createQueryContext(paramCanvas3D, paramLong1, unbox(paramDrawable), paramLong2, paramBoolean1, paramInt1, paramInt2, paramBoolean2, paramBoolean3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Drawable createOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, int paramInt1, int paramInt2) {
/* 2765 */     long l = createOffScreenBuffer(paramCanvas3D, unbox(paramContext), paramLong1, paramLong2, paramInt1, paramInt2);
/* 2766 */     return boxDrawable(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2772 */   void destroyOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, Drawable paramDrawable) { destroyOffScreenBuffer(paramCanvas3D, unbox(paramContext), paramLong1, paramLong2, unbox(paramDrawable)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2779 */   void readOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, int paramInt1, int paramInt2, Object paramObject, int paramInt3, int paramInt4) { readOffScreenBuffer(paramCanvas3D, unbox(paramContext), paramInt1, paramInt2, paramObject, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2786 */   int swapBuffers(Canvas3D paramCanvas3D, Context paramContext, long paramLong, Drawable paramDrawable) { return swapBuffers(paramCanvas3D, unbox(paramContext), paramLong, unbox(paramDrawable)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2794 */   int resizeD3DCanvas(Canvas3D paramCanvas3D, Context paramContext) { return resizeD3DCanvas(paramCanvas3D, unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2802 */   int toggleFullScreenMode(Canvas3D paramCanvas3D, Context paramContext) { return toggleFullScreenMode(paramCanvas3D, unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2810 */   void updateMaterialColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateMaterialColor(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void destroyContext(long paramLong, Drawable paramDrawable, Context paramContext) {
/* 2817 */     assert paramLong != 0L;
/* 2818 */     assert paramContext != null;
/* 2819 */     assert paramDrawable != null;
/* 2820 */     destroyContext(paramLong, unbox(paramDrawable), unbox(paramContext));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2828 */   void accum(Context paramContext, float paramFloat) { accum(unbox(paramContext), paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2836 */   void accumReturn(Context paramContext) { accumReturn(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2844 */   void clearAccum(Context paramContext) { clearAccum(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2853 */   int getNumCtxLights(Context paramContext) { return getNumCtxLights(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2861 */   boolean decal1stChildSetup(Context paramContext) { return decal1stChildSetup(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2869 */   void decalNthChildSetup(Context paramContext) { decalNthChildSetup(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2877 */   void decalReset(Context paramContext, boolean paramBoolean) { decalReset(unbox(paramContext), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2885 */   void ctxUpdateEyeLightingEnable(Context paramContext, boolean paramBoolean) { ctxUpdateEyeLightingEnable(unbox(paramContext), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2897 */   void setBlendColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { setBlendColor(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2906 */   void setBlendFunc(Context paramContext, int paramInt1, int paramInt2) { setBlendFunc(unbox(paramContext), paramInt1, paramInt2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2914 */   void setFogEnableFlag(Context paramContext, boolean paramBoolean) { setFogEnableFlag(unbox(paramContext), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2922 */   void setFullSceneAntialiasing(Context paramContext, boolean paramBoolean) { setFullSceneAntialiasing(unbox(paramContext), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2929 */   void setGlobalAlpha(Context paramContext, float paramFloat) { setGlobalAlpha(unbox(paramContext), paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2937 */   void updateSeparateSpecularColorEnable(Context paramContext, boolean paramBoolean) { updateSeparateSpecularColorEnable(unbox(paramContext), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2945 */   void beginScene(Context paramContext) { beginScene(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2951 */   void endScene(Context paramContext) { endScene(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2963 */   void setLightEnables(Context paramContext, long paramLong, int paramInt) { setLightEnables(unbox(paramContext), paramLong, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2971 */   void setSceneAmbient(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { setSceneAmbient(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2979 */   void disableFog(Context paramContext) { disableFog(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2987 */   void disableModelClip(Context paramContext) { disableModelClip(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2999 */   void resetRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) { resetRenderingAttributes(unbox(paramContext), paramBoolean1, paramBoolean2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3009 */   void resetTextureNative(Context paramContext, int paramInt) { resetTextureNative(unbox(paramContext), paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3017 */   void activeTextureUnit(Context paramContext, int paramInt) { activeTextureUnit(unbox(paramContext), paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3025 */   void resetTexCoordGeneration(Context paramContext) { resetTexCoordGeneration(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3033 */   void resetTextureAttributes(Context paramContext) { resetTextureAttributes(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3041 */   void resetPolygonAttributes(Context paramContext) { resetPolygonAttributes(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3049 */   void resetLineAttributes(Context paramContext) { resetLineAttributes(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3057 */   void resetPointAttributes(Context paramContext) { resetPointAttributes(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3069 */   void resetTransparency(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) { resetTransparency(unbox(paramContext), paramInt1, paramInt2, paramBoolean1, paramBoolean2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3085 */   void resetColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) { resetColoringAttributes(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3098 */   void syncRender(Context paramContext, boolean paramBoolean) { syncRender(unbox(paramContext), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean useCtx(Context paramContext, long paramLong, Drawable paramDrawable) {
/* 3106 */     assert paramLong != 0L;
/* 3107 */     return useCtx(unbox(paramContext), paramLong, unbox(paramDrawable));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3113 */   void clear(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) { clear(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3122 */   void textureFillBackground(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, boolean paramBoolean) { textureFillBackground(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3133 */   void textureFillRaster(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, boolean paramBoolean) { textureFillRaster(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9, paramFloat10, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3144 */   void executeRasterDepth(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Object paramObject) { executeRasterDepth(unbox(paramContext), paramFloat1, paramFloat2, paramFloat3, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramObject); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3152 */   void setModelViewMatrix(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) { setModelViewMatrix(unbox(paramContext), paramArrayOfDouble1, paramArrayOfDouble2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3160 */   void setProjectionMatrix(Context paramContext, double[] paramArrayOfDouble) { setProjectionMatrix(unbox(paramContext), paramArrayOfDouble); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3168 */   void setViewport(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4) { setViewport(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3176 */   void newDisplayList(Context paramContext, int paramInt) { newDisplayList(unbox(paramContext), paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3182 */   void endDisplayList(Context paramContext) { endDisplayList(unbox(paramContext)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3188 */   void callDisplayList(Context paramContext, int paramInt, boolean paramBoolean) { callDisplayList(unbox(paramContext), paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3195 */   void freeDisplayList(Context paramContext, int paramInt) { freeDisplayList(unbox(paramContext), paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3201 */   void freeTexture(Context paramContext, int paramInt) { freeTexture(unbox(paramContext), paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3221 */   void texturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte, int paramInt12, int paramInt13) { texturemapping(unbox(paramContext), paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramArrayOfByte, paramInt12, paramInt13); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3237 */   boolean initTexturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3) { return initTexturemapping(unbox(paramContext), paramInt1, paramInt2, paramInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3251 */   void setRenderMode(Context paramContext, int paramInt, boolean paramBoolean) { setRenderMode(unbox(paramContext), paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3259 */   void setDepthBufferWriteEnable(Context paramContext, boolean paramBoolean) { setDepthBufferWriteEnable(unbox(paramContext), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3280 */   GraphicsConfiguration getGraphicsConfig(GraphicsConfiguration paramGraphicsConfiguration) { return paramGraphicsConfiguration; }
/*      */ 
/*      */ 
/*      */   
/*      */   long getFbConfig(GraphicsConfigInfo paramGraphicsConfigInfo) {
/* 3285 */     long l = ((Long)paramGraphicsConfigInfo.getPrivateData()).longValue();
/* 3286 */     if (l == 0L) {
/* 3287 */       throw new IllegalArgumentException(J3dI18N.getString("Canvas3D23"));
/*      */     }
/*      */     
/* 3290 */     return l;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3296 */   GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration[] paramArrayOfGraphicsConfiguration) { return NativeConfigTemplate3D.getNativeConfigTemplate3D().getBestConfiguration(paramGraphicsConfigTemplate3D, paramArrayOfGraphicsConfiguration); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3302 */   boolean isGraphicsConfigSupported(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration paramGraphicsConfiguration) { return NativeConfigTemplate3D.getNativeConfigTemplate3D().isGraphicsConfigSupported(paramGraphicsConfigTemplate3D, paramGraphicsConfiguration); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3307 */   boolean hasDoubleBuffer(Canvas3D paramCanvas3D) { return NativeConfigTemplate3D.getNativeConfigTemplate3D().hasDoubleBuffer(paramCanvas3D); }
/*      */ 
/*      */ 
/*      */   
/* 3311 */   boolean hasStereo(Canvas3D paramCanvas3D) { return NativeConfigTemplate3D.getNativeConfigTemplate3D().hasStereo(paramCanvas3D); }
/*      */ 
/*      */ 
/*      */   
/* 3315 */   int getStencilSize(Canvas3D paramCanvas3D) { return NativeConfigTemplate3D.getNativeConfigTemplate3D().getStencilSize(paramCanvas3D); }
/*      */ 
/*      */ 
/*      */   
/* 3319 */   boolean hasSceneAntialiasingMultisample(Canvas3D paramCanvas3D) { return NativeConfigTemplate3D.getNativeConfigTemplate3D().hasSceneAntialiasingMultisample(paramCanvas3D); }
/*      */ 
/*      */ 
/*      */   
/* 3323 */   boolean hasSceneAntialiasingAccum(Canvas3D paramCanvas3D) { return NativeConfigTemplate3D.getNativeConfigTemplate3D().hasSceneAntialiasingAccum(paramCanvas3D); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3328 */   long getDisplay() { return NativeScreenInfo.getNativeScreenInfo().getDisplay(); }
/*      */ 
/*      */   
/* 3331 */   int getScreen(GraphicsDevice paramGraphicsDevice) { return NativeScreenInfo.getNativeScreenInfo().getScreen(paramGraphicsDevice); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3342 */   DrawingSurfaceObject createDrawingSurfaceObject(Canvas3D paramCanvas3D) { return new DrawingSurfaceObjectAWT(paramCanvas3D, VirtualUniverse.mc.awt, paramCanvas3D.screen.display, paramCanvas3D.screen.screen, VirtualUniverse.mc.xineramaDisabled); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void freeDrawingSurface(Canvas3D paramCanvas3D, DrawingSurfaceObject paramDrawingSurfaceObject) {
/* 3351 */     synchronized (paramDrawingSurfaceObject) {
/* 3352 */       DrawingSurfaceObjectAWT drawingSurfaceObjectAWT = (DrawingSurfaceObjectAWT)paramDrawingSurfaceObject;
/*      */ 
/*      */       
/* 3355 */       long l = drawingSurfaceObjectAWT.getDS();
/* 3356 */       long[] arrayOfLong = { l, drawingSurfaceObjectAWT.getDSI() };
/* 3357 */       if (l != 0L) {
/* 3358 */         VirtualUniverse.mc.postRequest(MasterControl.FREE_DRAWING_SURFACE, arrayOfLong);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3363 */       paramDrawingSurfaceObject.invalidate();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 3369 */   void freeDrawingSurfaceNative(Object paramObject) { DrawingSurfaceObjectAWT.freeDrawingSurface(paramObject); }
/*      */   
/*      */   private static native String getSupportedOglVendorNative();
/*      */   
/*      */   private native boolean loadNativeCgLibrary(String[] paramArrayOfString);
/*      */   
/*      */   native void freeD3DArray(GeometryArrayRetained paramGeometryArrayRetained, boolean paramBoolean);
/*      */   
/*      */   native void execute(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int paramInt8, int[] paramArrayOfInt3, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt9);
/*      */   
/*      */   native void executeVA(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject, int paramInt12);
/*      */   
/*      */   native void executeVABuffer(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Object paramObject1, int paramInt6, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, int paramInt7, Object paramObject3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, Object[] paramArrayOfObject1, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject2, int paramInt12);
/*      */   
/*      */   native void executeInterleavedBuffer(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, Object paramObject, float[] paramArrayOfFloat, int paramInt8);
/*      */   
/*      */   native void setVertexFormat(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2);
/*      */   
/*      */   native void disableGlobalAlpha(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2);
/*      */   
/*      */   native void buildGA(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat);
/*      */   
/*      */   native void buildGAForByRef(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble1, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt10, Object[] paramArrayOfObject, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3);
/*      */   
/*      */   native void executeIndexedGeometry(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, int paramInt9, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt10, int[] paramArrayOfInt4);
/*      */   
/*      */   native void executeIndexedGeometryBuffer(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, Object paramObject, float[] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3);
/*      */   
/*      */   native void executeIndexedGeometryVA(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, float[] paramArrayOfFloat3, int paramInt7, int[] paramArrayOfInt1, float[][] paramArrayOfFloat, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject, int paramInt11, int[] paramArrayOfInt3);
/*      */   
/*      */   native void executeIndexedGeometryVABuffer(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, Object paramObject3, int paramInt7, int[] paramArrayOfInt1, Object[] paramArrayOfObject1, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject2, int paramInt11, int[] paramArrayOfInt3);
/*      */   
/*      */   native void buildIndexedGeometry(long paramLong, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat, int[] paramArrayOfInt4);
/*      */   
/*      */   native void readRaster(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject1, int paramInt9, Object paramObject2);
/*      */   
/*      */   native ShaderError setCgUniform1i(long paramLong1, long paramLong2, long paramLong3, int paramInt);
/*      */   
/*      */   native ShaderError setCgUniform1f(long paramLong1, long paramLong2, long paramLong3, float paramFloat);
/*      */   
/*      */   native ShaderError setCgUniform2i(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setCgUniform2f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniform3i(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setCgUniform3f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniform4i(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setCgUniform4f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniformMatrix3f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniformMatrix4f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniform1iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setCgUniform1fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniform2iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setCgUniform2fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniform3iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setCgUniform3fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniform4iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setCgUniform4fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniformMatrix3fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setCgUniformMatrix4fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError createCgShader(long paramLong, int paramInt, long[] paramArrayOfLong);
/*      */   
/*      */   native ShaderError destroyCgShader(long paramLong1, long paramLong2);
/*      */   
/*      */   native ShaderError compileCgShader(long paramLong1, long paramLong2, String paramString);
/*      */   
/*      */   native ShaderError createCgShaderProgram(long paramLong, long[] paramArrayOfLong);
/*      */   
/*      */   native ShaderError destroyCgShaderProgram(long paramLong1, long paramLong2);
/*      */   
/*      */   native ShaderError linkCgShaderProgram(long paramLong1, long paramLong2, long[] paramArrayOfLong);
/*      */   
/*      */   native void lookupCgVertexAttrNames(long paramLong1, long paramLong2, int paramInt, String[] paramArrayOfString, boolean[] paramArrayOfBoolean);
/*      */   
/*      */   native void lookupCgShaderAttrNames(long paramLong1, long paramLong2, int paramInt, String[] paramArrayOfString, long[] paramArrayOfLong, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean);
/*      */   
/*      */   native ShaderError useCgShaderProgram(long paramLong1, long paramLong2);
/*      */   
/*      */   native ShaderError setGLSLUniform1i(long paramLong1, long paramLong2, long paramLong3, int paramInt);
/*      */   
/*      */   native ShaderError setGLSLUniform1f(long paramLong1, long paramLong2, long paramLong3, float paramFloat);
/*      */   
/*      */   native ShaderError setGLSLUniform2i(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setGLSLUniform2f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniform3i(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setGLSLUniform3f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniform4i(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setGLSLUniform4f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniformMatrix3f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniformMatrix4f(long paramLong1, long paramLong2, long paramLong3, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniform1iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setGLSLUniform1fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniform2iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setGLSLUniform2fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniform3iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setGLSLUniform3fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniform4iArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */   native ShaderError setGLSLUniform4fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniformMatrix3fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError setGLSLUniformMatrix4fArray(long paramLong1, long paramLong2, long paramLong3, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native ShaderError createGLSLShader(long paramLong, int paramInt, long[] paramArrayOfLong);
/*      */   
/*      */   native ShaderError destroyGLSLShader(long paramLong1, long paramLong2);
/*      */   
/*      */   native ShaderError compileGLSLShader(long paramLong1, long paramLong2, String paramString);
/*      */   
/*      */   native ShaderError createGLSLShaderProgram(long paramLong, long[] paramArrayOfLong);
/*      */   
/*      */   native ShaderError destroyGLSLShaderProgram(long paramLong1, long paramLong2);
/*      */   
/*      */   native ShaderError linkGLSLShaderProgram(long paramLong1, long paramLong2, long[] paramArrayOfLong);
/*      */   
/*      */   native ShaderError bindGLSLVertexAttrName(long paramLong1, long paramLong2, String paramString, int paramInt);
/*      */   
/*      */   native void lookupGLSLShaderAttrNames(long paramLong1, long paramLong2, int paramInt, String[] paramArrayOfString, long[] paramArrayOfLong, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean);
/*      */   
/*      */   native ShaderError useGLSLShaderProgram(long paramLong1, long paramLong2);
/*      */   
/*      */   native void cleanupRenderer();
/*      */   
/*      */   native void updateColoringAttributes(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean, int paramInt);
/*      */   
/*      */   native void updateDirectionalLight(long paramLong, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
/*      */   
/*      */   native void updatePointLight(long paramLong, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9);
/*      */   
/*      */   native void updateSpotLight(long paramLong, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14);
/*      */   
/*      */   native void updateExponentialFog(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */   
/*      */   native void updateLinearFog(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble1, double paramDouble2);
/*      */   
/*      */   native void updateLineAttributes(long paramLong, float paramFloat, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
/*      */   
/*      */   native void updateMaterial(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, float paramFloat17, int paramInt, boolean paramBoolean);
/*      */   
/*      */   native void updateModelClip(long paramLong, int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*      */   
/*      */   native void updatePointAttributes(long paramLong, float paramFloat, boolean paramBoolean);
/*      */   
/*      */   native void updatePolygonAttributes(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2);
/*      */   
/*      */   native void updateRenderingAttributes(long paramLong, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, float paramFloat, int paramInt2, boolean paramBoolean5, boolean paramBoolean6, int paramInt3, boolean paramBoolean7, boolean paramBoolean8, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10);
/*      */   
/*      */   native void updateTexCoordGeneration(long paramLong, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, double[] paramArrayOfDouble);
/*      */   
/*      */   native void updateTransparencyAttributes(long paramLong, float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, int paramInt4, int paramInt5);
/*      */   
/*      */   native void updateTextureAttributes(long paramLong, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3);
/*      */   
/*      */   native void updateRegisterCombiners(long paramLong, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt6, int paramInt7);
/*      */   
/*      */   native void updateTextureColorTable(long paramLong, int paramInt1, int paramInt2, int[] paramArrayOfInt);
/*      */   
/*      */   native void updateCombiner(long paramLong, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt3, int paramInt4);
/*      */   
/*      */   native void updateTextureUnitState(long paramLong, int paramInt, boolean paramBoolean);
/*      */   
/*      */   native void bindTexture2D(long paramLong, int paramInt, boolean paramBoolean);
/*      */   
/*      */   native void updateTexture2DImage(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject, boolean paramBoolean);
/*      */   
/*      */   native void updateTexture2DSubImage(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, Object paramObject, boolean paramBoolean);
/*      */   
/*      */   native void updateTexture2DLodRange(long paramLong, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2);
/*      */   
/*      */   native void updateTexture2DLodOffset(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */   
/*      */   native void updateTexture2DBoundary(long paramLong, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */   
/*      */   native void updateTexture2DFilterModes(long paramLong, int paramInt1, int paramInt2);
/*      */   
/*      */   native void updateTexture2DSharpenFunc(long paramLong, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native void updateTexture2DFilter4Func(long paramLong, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native void updateTexture2DAnisotropicFilter(long paramLong, float paramFloat);
/*      */   
/*      */   native void bindTexture3D(long paramLong, int paramInt, boolean paramBoolean);
/*      */   
/*      */   native void updateTexture3DImage(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean);
/*      */   
/*      */   native void updateTexture3DSubImage(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, Object paramObject, boolean paramBoolean);
/*      */   
/*      */   native void updateTexture3DLodRange(long paramLong, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2);
/*      */   
/*      */   native void updateTexture3DLodOffset(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */   
/*      */   native void updateTexture3DBoundary(long paramLong, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */   
/*      */   native void updateTexture3DFilterModes(long paramLong, int paramInt1, int paramInt2);
/*      */   
/*      */   native void updateTexture3DSharpenFunc(long paramLong, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native void updateTexture3DFilter4Func(long paramLong, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native void updateTexture3DAnisotropicFilter(long paramLong, float paramFloat);
/*      */   
/*      */   native void bindTextureCubeMap(long paramLong, int paramInt, boolean paramBoolean);
/*      */   
/*      */   native void updateTextureCubeMapImage(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean);
/*      */   
/*      */   native void updateTextureCubeMapSubImage(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject, boolean paramBoolean);
/*      */   
/*      */   native void updateTextureCubeMapLodRange(long paramLong, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2);
/*      */   
/*      */   native void updateTextureCubeMapLodOffset(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */   
/*      */   native void updateTextureCubeMapBoundary(long paramLong, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */   
/*      */   native void updateTextureCubeMapFilterModes(long paramLong, int paramInt1, int paramInt2);
/*      */   
/*      */   native void updateTextureCubeMapSharpenFunc(long paramLong, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native void updateTextureCubeMapFilter4Func(long paramLong, int paramInt, float[] paramArrayOfFloat);
/*      */   
/*      */   native void updateTextureCubeMapAnisotropicFilter(long paramLong, float paramFloat);
/*      */   
/*      */   native long getAWT();
/*      */   
/*      */   native boolean initializeJ3D(boolean paramBoolean);
/*      */   
/*      */   native int getMaximumLights();
/*      */   
/*      */   native long createNewContext(Canvas3D paramCanvas3D, long paramLong1, long paramLong2, long paramLong3, long paramLong4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4);
/*      */   
/*      */   native void createQueryContext(Canvas3D paramCanvas3D, long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, boolean paramBoolean3);
/*      */   
/*      */   native long createOffScreenBuffer(Canvas3D paramCanvas3D, long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2);
/*      */   
/*      */   native void destroyOffScreenBuffer(Canvas3D paramCanvas3D, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */   
/*      */   native void readOffScreenBuffer(Canvas3D paramCanvas3D, long paramLong, int paramInt1, int paramInt2, Object paramObject, int paramInt3, int paramInt4);
/*      */   
/*      */   native int swapBuffers(Canvas3D paramCanvas3D, long paramLong1, long paramLong2, long paramLong3);
/*      */   
/*      */   native int resizeD3DCanvas(Canvas3D paramCanvas3D, long paramLong);
/*      */   
/*      */   native int toggleFullScreenMode(Canvas3D paramCanvas3D, long paramLong);
/*      */   
/*      */   native void updateMaterialColor(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */   
/*      */   native void destroyContext(long paramLong1, long paramLong2, long paramLong3);
/*      */   
/*      */   native void accum(long paramLong, float paramFloat);
/*      */   
/*      */   native void accumReturn(long paramLong);
/*      */   
/*      */   native void clearAccum(long paramLong);
/*      */   
/*      */   native int getNumCtxLights(long paramLong);
/*      */   
/*      */   native boolean decal1stChildSetup(long paramLong);
/*      */   
/*      */   native void decalNthChildSetup(long paramLong);
/*      */   
/*      */   native void decalReset(long paramLong, boolean paramBoolean);
/*      */   
/*      */   native void ctxUpdateEyeLightingEnable(long paramLong, boolean paramBoolean);
/*      */   
/*      */   native void setBlendColor(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */   
/*      */   native void setBlendFunc(long paramLong, int paramInt1, int paramInt2);
/*      */   
/*      */   native void setFogEnableFlag(long paramLong, boolean paramBoolean);
/*      */   
/*      */   native void setFullSceneAntialiasing(long paramLong, boolean paramBoolean);
/*      */   
/*      */   native void setGlobalAlpha(long paramLong, float paramFloat);
/*      */   
/*      */   native void updateSeparateSpecularColorEnable(long paramLong, boolean paramBoolean);
/*      */   
/*      */   native void beginScene(long paramLong);
/*      */   
/*      */   native void endScene(long paramLong);
/*      */   
/*      */   native boolean validGraphicsMode();
/*      */   
/*      */   native void setLightEnables(long paramLong1, long paramLong2, int paramInt);
/*      */   
/*      */   native void setSceneAmbient(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */   
/*      */   native void disableFog(long paramLong);
/*      */   
/*      */   native void disableModelClip(long paramLong);
/*      */   
/*      */   native void resetRenderingAttributes(long paramLong, boolean paramBoolean1, boolean paramBoolean2);
/*      */   
/*      */   native void resetTextureNative(long paramLong, int paramInt);
/*      */   
/*      */   native void activeTextureUnit(long paramLong, int paramInt);
/*      */   
/*      */   native void resetTexCoordGeneration(long paramLong);
/*      */   
/*      */   native void resetTextureAttributes(long paramLong);
/*      */   
/*      */   native void resetPolygonAttributes(long paramLong);
/*      */   
/*      */   native void resetLineAttributes(long paramLong);
/*      */   
/*      */   native void resetPointAttributes(long paramLong);
/*      */   
/*      */   native void resetTransparency(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2);
/*      */   
/*      */   native void resetColoringAttributes(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean);
/*      */   
/*      */   native void syncRender(long paramLong, boolean paramBoolean);
/*      */   
/*      */   native boolean useCtx(long paramLong1, long paramLong2, long paramLong3);
/*      */   
/*      */   native void clear(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean);
/*      */   
/*      */   native void textureFillBackground(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, boolean paramBoolean);
/*      */   
/*      */   native void textureFillRaster(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, boolean paramBoolean);
/*      */   
/*      */   native void executeRasterDepth(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Object paramObject);
/*      */   
/*      */   native void setModelViewMatrix(long paramLong, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2);
/*      */   
/*      */   native void setProjectionMatrix(long paramLong, double[] paramArrayOfDouble);
/*      */   
/*      */   native void setViewport(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*      */   
/*      */   native void newDisplayList(long paramLong, int paramInt);
/*      */   
/*      */   native void endDisplayList(long paramLong);
/*      */   
/*      */   native void callDisplayList(long paramLong, int paramInt, boolean paramBoolean);
/*      */   
/*      */   native void freeDisplayList(long paramLong, int paramInt);
/*      */   
/*      */   native void freeTexture(long paramLong, int paramInt);
/*      */   
/*      */   native void texturemapping(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte, int paramInt12, int paramInt13);
/*      */   
/*      */   native boolean initTexturemapping(long paramLong, int paramInt1, int paramInt2, int paramInt3);
/*      */   
/*      */   native void setRenderMode(long paramLong, int paramInt, boolean paramBoolean);
/*      */   
/*      */   native void setDepthBufferWriteEnable(long paramLong, boolean paramBoolean);
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\NativePipeline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */