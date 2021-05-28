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
/*     */ class CgShaderProgramRetained
/*     */   extends ShaderProgramRetained
/*     */ {
/*     */   void createMirrorObject() {
/*  31 */     if (this.mirror == null) {
/*  32 */       CgShaderProgramRetained cgShaderProgramRetained = new CgShaderProgramRetained();
/*  33 */       this.mirror = cgShaderProgramRetained;
/*     */     } 
/*  35 */     initMirrorObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   ShaderError setUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) { return Pipeline.getPipeline().setCgUniform1i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt); }
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
/*  56 */   ShaderError setUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) { return Pipeline.getPipeline().setCgUniform1f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramFloat); }
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
/*  67 */   ShaderError setUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return Pipeline.getPipeline().setCgUniform2i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfInt); }
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
/*  78 */   ShaderError setUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniform2f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/*  89 */   ShaderError setUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return Pipeline.getPipeline().setCgUniform3i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfInt); }
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
/* 100 */   ShaderError setUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniform3f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 111 */   ShaderError setUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) { return Pipeline.getPipeline().setCgUniform4i(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfInt); }
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
/* 122 */   ShaderError setUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniform4f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 133 */   ShaderError setUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniformMatrix3f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 144 */   ShaderError setUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniformMatrix4f(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramArrayOfFloat); }
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
/* 158 */   ShaderError setUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setCgUniform1iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 171 */   ShaderError setUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniform1fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 184 */   ShaderError setUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setCgUniform2iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 197 */   ShaderError setUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniform2fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 210 */   ShaderError setUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setCgUniform3iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 223 */   ShaderError setUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniform3fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 236 */   ShaderError setUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) { return Pipeline.getPipeline().setCgUniform4iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfInt); }
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
/* 249 */   ShaderError setUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniform4fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 262 */   ShaderError setUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniformMatrix3fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 275 */   ShaderError setUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) { return Pipeline.getPipeline().setCgUniformMatrix4fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramInt, paramArrayOfFloat); }
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
/* 288 */   boolean isSupported(Canvas3D paramCanvas3D) { return paramCanvas3D.shadingLanguageCg; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   ShaderError createShader(Context paramContext, ShaderRetained paramShaderRetained, ShaderId[] paramArrayOfShaderId) { return Pipeline.getPipeline().createCgShader(paramContext, paramShaderRetained.shaderType, paramArrayOfShaderId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   ShaderError destroyShader(Context paramContext, ShaderId paramShaderId) { return Pipeline.getPipeline().destroyCgShader(paramContext, paramShaderId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   ShaderError compileShader(Context paramContext, ShaderId paramShaderId, String paramString) { return Pipeline.getPipeline().compileCgShader(paramContext, paramShaderId, paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   ShaderError createShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) { return Pipeline.getPipeline().createCgShaderProgram(paramContext, paramArrayOfShaderProgramId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   ShaderError destroyShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return Pipeline.getPipeline().destroyCgShaderProgram(paramContext, paramShaderProgramId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   ShaderError linkShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) { return Pipeline.getPipeline().linkCgShaderProgram(paramContext, paramShaderProgramId, paramArrayOfShaderId); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 335 */   ShaderError bindVertexAttrName(Context paramContext, ShaderProgramId paramShaderProgramId, String paramString, int paramInt) { return null; }
/*     */ 
/*     */ 
/*     */   
/* 339 */   void lookupVertexAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, String[] paramArrayOfString, boolean[] paramArrayOfBoolean) { Pipeline.getPipeline().lookupCgVertexAttrNames(paramContext, paramShaderProgramId, paramArrayOfString.length, paramArrayOfString, paramArrayOfBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void lookupShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, String[] paramArrayOfString, ShaderProgramRetained.AttrNameInfo[] paramArrayOfAttrNameInfo) {
/* 345 */     int i = paramArrayOfString.length;
/*     */     
/* 347 */     ShaderAttrLoc[] arrayOfShaderAttrLoc = new ShaderAttrLoc[i];
/* 348 */     int[] arrayOfInt1 = new int[i];
/* 349 */     int[] arrayOfInt2 = new int[i];
/* 350 */     boolean[] arrayOfBoolean = new boolean[i];
/*     */     
/* 352 */     Pipeline.getPipeline().lookupCgShaderAttrNames(paramContext, paramShaderProgramId, i, paramArrayOfString, arrayOfShaderAttrLoc, arrayOfInt1, arrayOfInt2, arrayOfBoolean);
/*     */ 
/*     */     
/* 355 */     for (byte b = 0; b < i; b++) {
/* 356 */       paramArrayOfAttrNameInfo[b] = new ShaderProgramRetained.AttrNameInfo(this);
/* 357 */       paramArrayOfAttrNameInfo[b].setLocation(arrayOfShaderAttrLoc[b]);
/* 358 */       paramArrayOfAttrNameInfo[b].setArray(arrayOfBoolean[b]);
/* 359 */       paramArrayOfAttrNameInfo[b].setType(arrayOfInt1[b]);
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
/*     */ 
/*     */   
/* 374 */   ShaderError enableShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return Pipeline.getPipeline().useCgShaderProgram(paramContext, paramShaderProgramId); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 381 */   ShaderError disableShaderProgram(Context paramContext) { return Pipeline.getPipeline().useCgShaderProgram(paramContext, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\CgShaderProgramRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */