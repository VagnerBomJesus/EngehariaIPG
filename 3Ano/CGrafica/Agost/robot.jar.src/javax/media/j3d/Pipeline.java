/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.nio.Buffer;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class Pipeline
/*      */ {
/*      */   private static final String CLASSNAME_NATIVE = "javax.media.j3d.NativePipeline";
/*      */   private static final String CLASSNAME_JOGL = "javax.media.j3d.JoglPipeline";
/*      */   private static final String CLASSNAME_NOOP = "javax.media.j3d.NoopPipeline";
/*      */   private static Pipeline pipeline;
/*      */   
/*      */   enum Type
/*      */   {
/*   26 */     NATIVE_OGL,
/*   27 */     NATIVE_D3D,
/*      */ 
/*      */     
/*   30 */     JOGL,
/*      */ 
/*      */     
/*   33 */     NOOP;
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
/*   44 */   private Type pipelineType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean useNativeOgl(boolean paramBoolean1, boolean paramBoolean2) {
/*      */     String str;
/*      */     try {
/*   62 */       str = NativePipeline.getSupportedOglVendor();
/*   63 */     } catch (Exception exception) {
/*   64 */       MasterControl.getCoreLogger().severe(exception.toString());
/*   65 */       return false;
/*   66 */     } catch (Error error) {
/*   67 */       MasterControl.getCoreLogger().severe(error.toString());
/*   68 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*   72 */     if (str == null) {
/*   73 */       return false;
/*      */     }
/*      */ 
/*      */     
/*   77 */     if (paramBoolean2) {
/*   78 */       return true;
/*      */     }
/*      */ 
/*      */     
/*   82 */     return preferOgl(paramBoolean1, str);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean preferOgl(boolean paramBoolean, String paramString) {
/*   88 */     if (!paramBoolean) {
/*   89 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*   94 */     String[] arrayOfString = { "microsoft", "ati" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  100 */     String str = paramString.toLowerCase();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  105 */     for (byte b = 0; b < arrayOfString.length; b++) {
/*  106 */       if (str.startsWith(arrayOfString[b])) {
/*  107 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  111 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void createPipeline(Type paramType) {
/*  120 */     String str1 = null;
/*  121 */     switch (paramType) {
/*      */       case NATIVE_OGL:
/*      */       case NATIVE_D3D:
/*  124 */         str1 = "javax.media.j3d.NativePipeline";
/*      */         break;
/*      */       case JOGL:
/*  127 */         str1 = "javax.media.j3d.JoglPipeline";
/*      */         break;
/*      */       case NOOP:
/*  130 */         str1 = "javax.media.j3d.NoopPipeline";
/*      */         break;
/*      */       
/*      */       default:
/*  134 */         throw new AssertionError("missing case statement");
/*      */     } 
/*      */     
/*  137 */     final String pipelineClassName = str1;
/*  138 */     pipeline = (Pipeline)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*      */             try {
/*  143 */               Class clazz = Class.forName(pipelineClassName);
/*  144 */               return clazz.newInstance();
/*  145 */             } catch (Exception exception) {
/*  146 */               throw new RuntimeException(exception);
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  151 */     pipeline.initialize(paramType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  158 */   static Pipeline getPipeline() { return pipeline; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  167 */   void initialize(Type paramType) { setPipelineType(paramType); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  174 */   private void setPipelineType(Type paramType) { this.pipelineType = paramType; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  181 */   Type getPipelineType() { return this.pipelineType; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getPipelineName() {
/*  188 */     switch (this.pipelineType) {
/*      */       case NATIVE_OGL:
/*  190 */         return "NATIVE_OGL";
/*      */       case NATIVE_D3D:
/*  192 */         return "NATIVE_D3D";
/*      */       case JOGL:
/*  194 */         return "JOGL";
/*      */       case NOOP:
/*  196 */         return "NOOP";
/*      */     } 
/*      */     
/*  199 */     throw new AssertionError("missing case statement");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getRendererName() {
/*  207 */     switch (this.pipelineType) {
/*      */       case NATIVE_OGL:
/*      */       case JOGL:
/*  210 */         return "OpenGL";
/*      */       case NATIVE_D3D:
/*  212 */         return "DirectX";
/*      */       case NOOP:
/*  214 */         return "None";
/*      */     } 
/*      */     
/*  217 */     throw new AssertionError("missing case statement");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void loadLibraries(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean isCgLibraryAvailable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean isGLSLLibraryAvailable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void freeD3DArray(GeometryArrayRetained paramGeometryArrayRetained, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void execute(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int paramInt8, int[] paramArrayOfInt3, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt9);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void executeVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject, int paramInt12);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void executeVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Object paramObject1, int paramInt6, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, int paramInt7, Object paramObject3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, Object[] paramArrayOfObject1, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject2, int paramInt12);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void executeInterleavedBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, Object paramObject, float[] paramArrayOfFloat, int paramInt8);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setVertexFormat(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void disableGlobalAlpha(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void buildGA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void buildGAForByRef(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble1, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt10, Object[] paramArrayOfObject, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void executeIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, int paramInt9, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt10, int[] paramArrayOfInt4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void executeIndexedGeometryBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, Object paramObject, float[] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void executeIndexedGeometryVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, float[] paramArrayOfFloat3, int paramInt7, int[] paramArrayOfInt1, float[][] paramArrayOfFloat, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject, int paramInt11, int[] paramArrayOfInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void executeIndexedGeometryVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, Object paramObject3, int paramInt7, int[] paramArrayOfInt1, Object[] paramArrayOfObject1, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject2, int paramInt11, int[] paramArrayOfInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void buildIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat, int[] paramArrayOfInt4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void readRaster(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject1, int paramInt9, Object paramObject2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setCgUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError createCgShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError destroyCgShader(Context paramContext, ShaderId paramShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError compileCgShader(Context paramContext, ShaderId paramShaderId, String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError createCgShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError destroyCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError linkCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void lookupCgVertexAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, boolean[] paramArrayOfBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void lookupCgShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError useCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setGLSLUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError createGLSLShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError destroyGLSLShader(Context paramContext, ShaderId paramShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError compileGLSLShader(Context paramContext, ShaderId paramShaderId, String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError createGLSLShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError destroyGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError linkGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError bindGLSLVertexAttrName(Context paramContext, ShaderProgramId paramShaderProgramId, String paramString, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void lookupGLSLShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError useGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  791 */   boolean checkNativeBufferAccess(Buffer paramBuffer) { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void cleanupRenderer();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateDirectionalLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updatePointLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateSpotLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateExponentialFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateLinearFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble1, double paramDouble2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateLineAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateMaterial(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, float paramFloat17, int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateModelClip(Context paramContext, int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updatePointAttributes(Context paramContext, float paramFloat, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updatePolygonAttributes(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, float paramFloat, int paramInt2, boolean paramBoolean5, boolean paramBoolean6, int paramInt3, boolean paramBoolean7, boolean paramBoolean8, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexCoordGeneration(Context paramContext, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, double[] paramArrayOfDouble);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTransparencyAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, int paramInt4, int paramInt5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureAttributes(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateRegisterCombiners(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt6, int paramInt7);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureColorTable(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateCombiner(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt3, int paramInt4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureUnitState(Context paramContext, int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void bindTexture2D(Context paramContext, int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, Object paramObject, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DFilterModes(Context paramContext, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture2DAnisotropicFilter(Context paramContext, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void bindTexture3D(Context paramContext, int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, Object paramObject, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DBoundary(Context paramContext, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DFilterModes(Context paramContext, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTexture3DAnisotropicFilter(Context paramContext, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void bindTextureCubeMap(Context paramContext, int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapFilterModes(Context paramContext, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateTextureCubeMapAnisotropicFilter(Context paramContext, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract long getAWT();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean initializeJ3D(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int getMaximumLights();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract Context createNewContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void createQueryContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, boolean paramBoolean3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract Drawable createOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void destroyOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, Drawable paramDrawable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void readOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, int paramInt1, int paramInt2, Object paramObject, int paramInt3, int paramInt4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int swapBuffers(Canvas3D paramCanvas3D, Context paramContext, long paramLong, Drawable paramDrawable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int resizeD3DCanvas(Canvas3D paramCanvas3D, Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int toggleFullScreenMode(Canvas3D paramCanvas3D, Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateMaterialColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void destroyContext(long paramLong, Drawable paramDrawable, Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void accum(Context paramContext, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void accumReturn(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void clearAccum(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int getNumCtxLights(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean decal1stChildSetup(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void decalNthChildSetup(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void decalReset(Context paramContext, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void ctxUpdateEyeLightingEnable(Context paramContext, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setBlendColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setBlendFunc(Context paramContext, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setFogEnableFlag(Context paramContext, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setFullSceneAntialiasing(Context paramContext, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setGlobalAlpha(Context paramContext, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void updateSeparateSpecularColorEnable(Context paramContext, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void beginScene(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void endScene(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean validGraphicsMode();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setLightEnables(Context paramContext, long paramLong, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void setSceneAmbient(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void disableFog(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void disableModelClip(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetTextureNative(Context paramContext, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void activeTextureUnit(Context paramContext, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetTexCoordGeneration(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetTextureAttributes(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetPolygonAttributes(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetLineAttributes(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetPointAttributes(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetTransparency(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void resetColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void syncRender(Context paramContext, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean useCtx(Context paramContext, long paramLong, Drawable paramDrawable);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1354 */   boolean releaseCtx(Context paramContext, long paramLong) { return false; }
/*      */   
/*      */   abstract void clear(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean);
/*      */   
/*      */   abstract void textureFillBackground(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, boolean paramBoolean);
/*      */   
/*      */   abstract void textureFillRaster(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, boolean paramBoolean);
/*      */   
/*      */   abstract void executeRasterDepth(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Object paramObject);
/*      */   
/*      */   abstract void setModelViewMatrix(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2);
/*      */   
/*      */   abstract void setProjectionMatrix(Context paramContext, double[] paramArrayOfDouble);
/*      */   
/*      */   abstract void setViewport(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*      */   
/*      */   abstract void newDisplayList(Context paramContext, int paramInt);
/*      */   
/*      */   abstract void endDisplayList(Context paramContext);
/*      */   
/*      */   abstract void callDisplayList(Context paramContext, int paramInt, boolean paramBoolean);
/*      */   
/*      */   abstract void freeDisplayList(Context paramContext, int paramInt);
/*      */   
/*      */   abstract void freeTexture(Context paramContext, int paramInt);
/*      */   
/*      */   abstract void texturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte, int paramInt12, int paramInt13);
/*      */   
/*      */   abstract boolean initTexturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3);
/*      */   
/*      */   abstract void setRenderMode(Context paramContext, int paramInt, boolean paramBoolean);
/*      */   
/*      */   abstract void setDepthBufferWriteEnable(Context paramContext, boolean paramBoolean);
/*      */   
/*      */   abstract GraphicsConfiguration getGraphicsConfig(GraphicsConfiguration paramGraphicsConfiguration);
/*      */   
/*      */   abstract long getFbConfig(GraphicsConfigInfo paramGraphicsConfigInfo);
/*      */   
/*      */   abstract GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration[] paramArrayOfGraphicsConfiguration);
/*      */   
/*      */   abstract boolean isGraphicsConfigSupported(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration paramGraphicsConfiguration);
/*      */   
/*      */   abstract boolean hasDoubleBuffer(Canvas3D paramCanvas3D);
/*      */   
/*      */   abstract boolean hasStereo(Canvas3D paramCanvas3D);
/*      */   
/*      */   abstract int getStencilSize(Canvas3D paramCanvas3D);
/*      */   
/*      */   abstract boolean hasSceneAntialiasingMultisample(Canvas3D paramCanvas3D);
/*      */   
/*      */   abstract boolean hasSceneAntialiasingAccum(Canvas3D paramCanvas3D);
/*      */   
/*      */   abstract long getDisplay();
/*      */   
/*      */   abstract int getScreen(GraphicsDevice paramGraphicsDevice);
/*      */   
/*      */   abstract DrawingSurfaceObject createDrawingSurfaceObject(Canvas3D paramCanvas3D);
/*      */   
/*      */   abstract void freeDrawingSurface(Canvas3D paramCanvas3D, DrawingSurfaceObject paramDrawingSurfaceObject);
/*      */   
/*      */   abstract void freeDrawingSurfaceNative(Object paramObject);
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Pipeline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */