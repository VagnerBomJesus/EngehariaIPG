/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class NoopPipeline
/*      */   extends Pipeline
/*      */ {
/*      */   private boolean cgLibraryAvailable = false;
/*      */   
/*      */   void initialize(Pipeline.Type paramType) {
/*   38 */     super.initialize(paramType);
/*      */     
/*   40 */     assert paramType == Pipeline.Type.NOOP;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void loadLibraries(int paramInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   54 */   boolean isCgLibraryAvailable() { return this.cgLibraryAvailable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   62 */   boolean isGLSLLibraryAvailable() { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void freeD3DArray(GeometryArrayRetained paramGeometryArrayRetained, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void execute(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int paramInt8, int[] paramArrayOfInt3, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt9) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject, int paramInt12) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Object paramObject1, int paramInt6, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, int paramInt7, Object paramObject3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, Object[] paramArrayOfObject1, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject2, int paramInt12) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeInterleavedBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, Object paramObject, float[] paramArrayOfFloat, int paramInt8) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setVertexFormat(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableGlobalAlpha(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void buildGA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void buildGAForByRef(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble1, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt10, Object[] paramArrayOfObject, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, int paramInt9, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt10, int[] paramArrayOfInt4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeIndexedGeometryBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, Object paramObject, float[] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeIndexedGeometryVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, float[] paramArrayOfFloat3, int paramInt7, int[] paramArrayOfInt1, float[][] paramArrayOfFloat, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject, int paramInt11, int[] paramArrayOfInt3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeIndexedGeometryVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, Object paramObject3, int paramInt7, int[] paramArrayOfInt1, Object[] paramArrayOfObject1, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject2, int paramInt11, int[] paramArrayOfInt3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void buildIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat, int[] paramArrayOfInt4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readRaster(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject1, int paramInt9, Object paramObject2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  329 */   ShaderError setCgUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  336 */   ShaderError setCgUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  343 */   ShaderError setCgUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  350 */   ShaderError setCgUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  357 */   ShaderError setCgUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  364 */   ShaderError setCgUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  371 */   ShaderError setCgUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  378 */   ShaderError setCgUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  385 */   ShaderError setCgUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   ShaderError setCgUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  402 */   ShaderError setCgUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  410 */   ShaderError setCgUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  418 */   ShaderError setCgUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  426 */   ShaderError setCgUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  434 */   ShaderError setCgUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  442 */   ShaderError setCgUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  450 */   ShaderError setCgUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  458 */   ShaderError setCgUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  466 */   ShaderError setCgUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  474 */   ShaderError setCgUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  479 */   ShaderError createCgShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId) { return null; }
/*      */ 
/*      */   
/*  482 */   ShaderError destroyCgShader(Context paramContext, ShaderId paramShaderId) { return null; }
/*      */ 
/*      */   
/*  485 */   ShaderError compileCgShader(Context paramContext, ShaderId paramShaderId, String paramString) { return null; }
/*      */ 
/*      */ 
/*      */   
/*  489 */   ShaderError createCgShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) { return null; }
/*      */ 
/*      */   
/*  492 */   ShaderError destroyCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return null; }
/*      */ 
/*      */ 
/*      */   
/*  496 */   ShaderError linkCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) { return null; }
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupCgVertexAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, boolean[] paramArrayOfBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupCgShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean) {}
/*      */ 
/*      */   
/*  507 */   ShaderError useCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  523 */   ShaderError setGLSLUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  530 */   ShaderError setGLSLUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  537 */   ShaderError setGLSLUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  544 */   ShaderError setGLSLUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  551 */   ShaderError setGLSLUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  558 */   ShaderError setGLSLUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  565 */   ShaderError setGLSLUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  572 */   ShaderError setGLSLUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  579 */   ShaderError setGLSLUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  586 */   ShaderError setGLSLUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  596 */   ShaderError setGLSLUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  604 */   ShaderError setGLSLUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  612 */   ShaderError setGLSLUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  620 */   ShaderError setGLSLUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  628 */   ShaderError setGLSLUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  636 */   ShaderError setGLSLUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  644 */   ShaderError setGLSLUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  652 */   ShaderError setGLSLUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  660 */   ShaderError setGLSLUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  668 */   ShaderError setGLSLUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  673 */   ShaderError createGLSLShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId) { return null; }
/*      */ 
/*      */   
/*  676 */   ShaderError destroyGLSLShader(Context paramContext, ShaderId paramShaderId) { return null; }
/*      */ 
/*      */   
/*  679 */   ShaderError compileGLSLShader(Context paramContext, ShaderId paramShaderId, String paramString) { return null; }
/*      */ 
/*      */ 
/*      */   
/*  683 */   ShaderError createGLSLShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) { return null; }
/*      */ 
/*      */   
/*  686 */   ShaderError destroyGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return null; }
/*      */ 
/*      */ 
/*      */   
/*  690 */   ShaderError linkGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) { return null; }
/*      */ 
/*      */ 
/*      */   
/*  694 */   ShaderError bindGLSLVertexAttrName(Context paramContext, ShaderProgramId paramShaderProgramId, String paramString, int paramInt) { return null; }
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupGLSLShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*  702 */   ShaderError useGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void cleanupRenderer() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean, int paramInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateDirectionalLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePointLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateSpotLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateExponentialFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateLinearFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble1, double paramDouble2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateLineAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMaterial(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, float paramFloat17, int paramInt, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateModelClip(Context paramContext, int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePointAttributes(Context paramContext, float paramFloat, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePolygonAttributes(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, float paramFloat, int paramInt2, boolean paramBoolean5, boolean paramBoolean6, int paramInt3, boolean paramBoolean7, boolean paramBoolean8, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexCoordGeneration(Context paramContext, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, double[] paramArrayOfDouble) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTransparencyAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, int paramInt4, int paramInt5) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureAttributes(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateRegisterCombiners(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt6, int paramInt7) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureColorTable(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateCombiner(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt3, int paramInt4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureUnitState(Context paramContext, int paramInt, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void bindTexture2D(Context paramContext, int paramInt, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, Object paramObject, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DFilterModes(Context paramContext, int paramInt1, int paramInt2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture2DAnisotropicFilter(Context paramContext, float paramFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void bindTexture3D(Context paramContext, int paramInt, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, Object paramObject, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DBoundary(Context paramContext, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DFilterModes(Context paramContext, int paramInt1, int paramInt2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexture3DAnisotropicFilter(Context paramContext, float paramFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void bindTextureCubeMap(Context paramContext, int paramInt, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapFilterModes(Context paramContext, int paramInt1, int paramInt2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureCubeMapAnisotropicFilter(Context paramContext, float paramFloat) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1154 */   long getAWT() { return 0L; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1159 */   boolean initializeJ3D(boolean paramBoolean) { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1164 */   int getMaximumLights() { return 8; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1180 */   Context createNewContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) { return new NoopContext(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createQueryContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, boolean paramBoolean3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1191 */   Drawable createOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, int paramInt1, int paramInt2) { return null; }
/*      */ 
/*      */ 
/*      */   
/*      */   void destroyOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, Drawable paramDrawable) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void readOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, int paramInt1, int paramInt2, Object paramObject, int paramInt3, int paramInt4) {}
/*      */ 
/*      */ 
/*      */   
/* 1203 */   int swapBuffers(Canvas3D paramCanvas3D, Context paramContext, long paramLong, Drawable paramDrawable) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1208 */   int resizeD3DCanvas(Canvas3D paramCanvas3D, Context paramContext) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1213 */   int toggleFullScreenMode(Canvas3D paramCanvas3D, Context paramContext) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMaterialColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void destroyContext(long paramLong, Drawable paramDrawable, Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void accum(Context paramContext, float paramFloat) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void accumReturn(Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void clearAccum(Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/* 1238 */   int getNumCtxLights(Context paramContext) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1243 */   boolean decal1stChildSetup(Context paramContext) { return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void decalNthChildSetup(Context paramContext) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void decalReset(Context paramContext, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void ctxUpdateEyeLightingEnable(Context paramContext, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void setBlendColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void setBlendFunc(Context paramContext, int paramInt1, int paramInt2) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void setFogEnableFlag(Context paramContext, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void setFullSceneAntialiasing(Context paramContext, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void setGlobalAlpha(Context paramContext, float paramFloat) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void updateSeparateSpecularColorEnable(Context paramContext, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void beginScene(Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void endScene(Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/* 1293 */   boolean validGraphicsMode() { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLightEnables(Context paramContext, long paramLong, int paramInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSceneAmbient(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableFog(Context paramContext) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableModelClip(Context paramContext) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetTextureNative(Context paramContext, int paramInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void activeTextureUnit(Context paramContext, int paramInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetTexCoordGeneration(Context paramContext) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetTextureAttributes(Context paramContext) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetPolygonAttributes(Context paramContext) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetLineAttributes(Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void resetPointAttributes(Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void resetTransparency(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void resetColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void syncRender(Context paramContext, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/* 1368 */   boolean useCtx(Context paramContext, long paramLong, Drawable paramDrawable) { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clear(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void textureFillBackground(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void textureFillRaster(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeRasterDepth(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Object paramObject) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setModelViewMatrix(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void setProjectionMatrix(Context paramContext, double[] paramArrayOfDouble) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void setViewport(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void newDisplayList(Context paramContext, int paramInt) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void endDisplayList(Context paramContext) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void callDisplayList(Context paramContext, int paramInt, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void freeDisplayList(Context paramContext, int paramInt) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void freeTexture(Context paramContext, int paramInt) {}
/*      */ 
/*      */ 
/*      */   
/*      */   void texturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte, int paramInt12, int paramInt13) {}
/*      */ 
/*      */ 
/*      */   
/* 1429 */   boolean initTexturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3) { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setRenderMode(Context paramContext, int paramInt, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setDepthBufferWriteEnable(Context paramContext, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   GraphicsConfiguration getGraphicsConfig(GraphicsConfiguration paramGraphicsConfiguration) {
/* 1459 */     System.err.println("NoopPipeline.getGraphicsConfig()");
/* 1460 */     return paramGraphicsConfiguration;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1465 */   long getFbConfig(GraphicsConfigInfo paramGraphicsConfigInfo) { return 0L; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration[] paramArrayOfGraphicsConfiguration) {
/* 1473 */     GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
/*      */ 
/*      */     
/* 1476 */     synchronized (Canvas3D.graphicsConfigTable) {
/* 1477 */       if (Canvas3D.graphicsConfigTable.get(graphicsConfiguration) == null) {
/* 1478 */         GraphicsConfigInfo graphicsConfigInfo = new GraphicsConfigInfo(paramGraphicsConfigTemplate3D);
/*      */         
/* 1480 */         Canvas3D.graphicsConfigTable.put(graphicsConfiguration, graphicsConfigInfo);
/*      */       } 
/*      */     } 
/* 1483 */     return graphicsConfiguration;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1489 */   boolean isGraphicsConfigSupported(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration paramGraphicsConfiguration) { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1494 */   boolean hasDoubleBuffer(Canvas3D paramCanvas3D) { return true; }
/*      */ 
/*      */ 
/*      */   
/* 1498 */   boolean hasStereo(Canvas3D paramCanvas3D) { return false; }
/*      */ 
/*      */ 
/*      */   
/* 1502 */   int getStencilSize(Canvas3D paramCanvas3D) { return 0; }
/*      */ 
/*      */ 
/*      */   
/* 1506 */   boolean hasSceneAntialiasingMultisample(Canvas3D paramCanvas3D) { return false; }
/*      */ 
/*      */ 
/*      */   
/* 1510 */   boolean hasSceneAntialiasingAccum(Canvas3D paramCanvas3D) { return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1515 */   long getDisplay() { return 0L; }
/*      */ 
/*      */ 
/*      */   
/* 1519 */   int getScreen(GraphicsDevice paramGraphicsDevice) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1531 */   DrawingSurfaceObject createDrawingSurfaceObject(Canvas3D paramCanvas3D) { return new NoopDrawingSurfaceObject(paramCanvas3D); }
/*      */   
/*      */   void freeDrawingSurface(Canvas3D paramCanvas3D, DrawingSurfaceObject paramDrawingSurfaceObject) {}
/*      */   
/*      */   void freeDrawingSurfaceNative(Object paramObject) {}
/*      */   
/*      */   static class NoopContext implements Context {}
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\NoopPipeline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */