/*     */ package javax.media.j3d;
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
/*     */ class GLSLShaderProgramRetained
/*     */   extends ShaderProgramRetained
/*     */ {
/*     */   void createMirrorObject() {
/*  31 */     if (this.mirror == null) {
/*  32 */       GLSLShaderProgramRetained gLSLShaderProgramRetained = new GLSLShaderProgramRetained();
/*  33 */       this.mirror = gLSLShaderProgramRetained;
/*  34 */       this.mirror.source = this.source;
/*     */     } 
/*  36 */     initMirrorObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   ShaderError setUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) { return Pipeline.getPipeline().setGLSLUniform1i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt); }
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
/*  57 */   ShaderError setUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) { return Pipeline.getPipeline().setGLSLUniform1f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramFloat); }
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
/*  68 */   ShaderError setUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return Pipeline.getPipeline().setGLSLUniform2i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfInt); }
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
/*  79 */   ShaderError setUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniform2f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/*  90 */   ShaderError setUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return Pipeline.getPipeline().setGLSLUniform3i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfInt); }
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
/* 101 */   ShaderError setUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniform3f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 112 */   ShaderError setUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return Pipeline.getPipeline().setGLSLUniform4i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfInt); }
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
/* 123 */   ShaderError setUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniform4f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 134 */   ShaderError setUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniformMatrix3f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 145 */   ShaderError setUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniformMatrix4f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 159 */   ShaderError setUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setGLSLUniform1iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 172 */   ShaderError setUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniform1fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 185 */   ShaderError setUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setGLSLUniform2iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 198 */   ShaderError setUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniform2fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 211 */   ShaderError setUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setGLSLUniform3iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 224 */   ShaderError setUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniform3fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 237 */   ShaderError setUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setGLSLUniform4iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 250 */   ShaderError setUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniform4fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 263 */   ShaderError setUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniformMatrix3fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 276 */   ShaderError setUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setGLSLUniformMatrix4fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 288 */   boolean isSupported(Canvas3D paramCanvas3D) { return paramCanvas3D.shadingLanguageGLSL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   ShaderError createShader(Context paramContext, ShaderRetained paramShaderRetained, ShaderId[] paramArrayOfShaderId) { return Pipeline.getPipeline().createGLSLShader(paramContext, paramShaderRetained.shaderType, paramArrayOfShaderId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   ShaderError destroyShader(Context paramContext, ShaderId paramShaderId) { return Pipeline.getPipeline().destroyGLSLShader(paramContext, paramShaderId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   ShaderError compileShader(Context paramContext, ShaderId paramShaderId, String paramString) { return Pipeline.getPipeline().compileGLSLShader(paramContext, paramShaderId, paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   ShaderError createShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) { return Pipeline.getPipeline().createGLSLShaderProgram(paramContext, paramArrayOfShaderProgramId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   ShaderError destroyShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return Pipeline.getPipeline().destroyGLSLShaderProgram(paramContext, paramShaderProgramId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   ShaderError linkShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) { return Pipeline.getPipeline().linkGLSLShaderProgram(paramContext, paramShaderProgramId, paramArrayOfShaderId); }
/*     */ 
/*     */ 
/*     */   
/* 334 */   ShaderError bindVertexAttrName(Context paramContext, ShaderProgramId paramShaderProgramId, String paramString, int paramInt) { return Pipeline.getPipeline().bindGLSLVertexAttrName(paramContext, paramShaderProgramId, paramString, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void lookupVertexAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, String[] paramArrayOfString, boolean[] paramArrayOfBoolean) {}
/*     */ 
/*     */ 
/*     */   
/*     */   void lookupShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, String[] paramArrayOfString, ShaderProgramRetained.AttrNameInfo[] paramArrayOfAttrNameInfo) {
/* 344 */     int i = paramArrayOfString.length;
/*     */     
/* 346 */     ShaderAttrLoc[] arrayOfShaderAttrLoc = new ShaderAttrLoc[i];
/* 347 */     int[] arrayOfInt1 = new int[i];
/* 348 */     int[] arrayOfInt2 = new int[i];
/* 349 */     boolean[] arrayOfBoolean = new boolean[i];
/*     */     
/* 351 */     Pipeline.getPipeline().lookupGLSLShaderAttrNames(paramContext, paramShaderProgramId, i, paramArrayOfString, arrayOfShaderAttrLoc, arrayOfInt1, arrayOfInt2, arrayOfBoolean);
/*     */ 
/*     */     
/* 354 */     for (byte b = 0; b < i; b++) {
/* 355 */       paramArrayOfAttrNameInfo[b] = new ShaderProgramRetained.AttrNameInfo(this);
/* 356 */       paramArrayOfAttrNameInfo[b].setLocation(arrayOfShaderAttrLoc[b]);
/* 357 */       paramArrayOfAttrNameInfo[b].setArray(arrayOfBoolean[b]);
/* 358 */       paramArrayOfAttrNameInfo[b].setType(arrayOfInt1[b]);
/*     */     } 
/*     */   }
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
/* 371 */   ShaderError enableShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return Pipeline.getPipeline().useGLSLShaderProgram(paramContext, paramShaderProgramId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   ShaderError disableShaderProgram(Context paramContext) { return Pipeline.getPipeline().useGLSLShaderProgram(paramContext, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\GLSLShaderProgramRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */