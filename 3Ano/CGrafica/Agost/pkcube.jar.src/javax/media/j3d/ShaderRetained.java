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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class ShaderRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   int shadingLanguage;
/*     */   int shaderType;
/*     */   ShaderData[] shaderData;
/*     */   boolean compileErrorOccurred = false;
/*  40 */   Object resourceLock = new Object();
/*     */   
/*     */   void initializeShader(int paramInt1, int paramInt2) {
/*  43 */     this.shadingLanguage = paramInt1;
/*  44 */     this.shaderType = paramInt2;
/*     */   }
/*     */ 
/*     */   
/*  48 */   int getShadingLanguage() { return this.shadingLanguage; }
/*     */ 
/*     */ 
/*     */   
/*  52 */   int getShaderType() { return this.shaderType; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   void setLive(boolean paramBoolean, int paramInt) { super.setLive(paramBoolean, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   void clearLive(int paramInt) { super.clearLive(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   void updateMirrorObject(int paramInt, Object paramObject) { System.err.println("Shader.updateMirrorObject not implemented yet!"); }
/*     */ 
/*     */ 
/*     */   
/*  75 */   void handleFrequencyChange(int paramInt) { System.err.println("Shader.handleFrequencyChange not implemented yet!"); }
/*     */ 
/*     */ 
/*     */   
/*     */   void createShaderData(int paramInt, long paramLong) {
/*  80 */     synchronized (this.resourceLock) {
/*  81 */       if (this.shaderData == null) {
/*  82 */         this.shaderData = new ShaderData[paramInt + 1];
/*  83 */       } else if (this.shaderData.length <= paramInt) {
/*  84 */         ShaderData[] arrayOfShaderData = new ShaderData[paramInt + 1];
/*     */         
/*  86 */         System.arraycopy(this.shaderData, 0, arrayOfShaderData, 0, this.shaderData.length);
/*     */ 
/*     */         
/*  89 */         this.shaderData = arrayOfShaderData;
/*     */       } 
/*     */       
/*  92 */       if (this.shaderData[paramInt] == null) {
/*  93 */         this.shaderData[paramInt] = new ShaderData();
/*  94 */       } else if (this.shaderData[paramInt].getCtxTimeStamp() != paramLong) {
/*     */ 
/*     */         
/*  97 */         this.shaderData[paramInt].reset();
/*     */       } 
/*  99 */       this.shaderData[paramInt].setCtxTimeStamp(paramLong);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class ShaderData
/*     */   {
/*     */     private long ctxTimeStamp;
/*     */ 
/*     */     
/* 111 */     private ShaderId shaderId = null;
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean compiled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void reset() {
/* 121 */       this.ctxTimeStamp = 0L;
/* 122 */       this.shaderId = null;
/* 123 */       this.compiled = false;
/*     */     }
/*     */ 
/*     */     
/* 127 */     long getCtxTimeStamp() { return this.ctxTimeStamp; }
/*     */ 
/*     */ 
/*     */     
/* 131 */     void setCtxTimeStamp(long param1Long) { this.ctxTimeStamp = param1Long; }
/*     */ 
/*     */ 
/*     */     
/* 135 */     ShaderId getShaderId() { return this.shaderId; }
/*     */ 
/*     */ 
/*     */     
/* 139 */     void setShaderId(ShaderId param1ShaderId) { this.shaderId = param1ShaderId; }
/*     */ 
/*     */ 
/*     */     
/* 143 */     boolean isCompiled() { return this.compiled; }
/*     */ 
/*     */ 
/*     */     
/* 147 */     void setCompiled(boolean param1Boolean) { this.compiled = param1Boolean; }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\ShaderRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */