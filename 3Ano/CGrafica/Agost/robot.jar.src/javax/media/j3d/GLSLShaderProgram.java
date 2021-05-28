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
/*     */ public class GLSLShaderProgram
/*     */   extends ShaderProgram
/*     */ {
/*     */   public void setVertexAttrNames(String[] paramArrayOfString) {
/*  37 */     checkForLiveOrCompiled();
/*     */     
/*  39 */     if (paramArrayOfString != null) {
/*  40 */       for (byte b = 0; b < paramArrayOfString.length; b++) {
/*  41 */         if (paramArrayOfString[b] == null) {
/*  42 */           throw new NullPointerException();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  47 */     ((GLSLShaderProgramRetained)this.retained).setVertexAttrNames(paramArrayOfString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getVertexAttrNames() {
/*  53 */     if (isLiveOrCompiled() && 
/*  54 */       !getCapability(1)) {
/*  55 */       throw new CapabilityNotSetException(J3dI18N.getString("GLSLShaderProgram0"));
/*     */     }
/*     */ 
/*     */     
/*  59 */     return ((GLSLShaderProgramRetained)this.retained).getVertexAttrNames();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShaderAttrNames(String[] paramArrayOfString) {
/*  65 */     checkForLiveOrCompiled();
/*     */     
/*  67 */     if (paramArrayOfString != null) {
/*  68 */       for (byte b = 0; b < paramArrayOfString.length; b++) {
/*  69 */         if (paramArrayOfString[b] == null) {
/*  70 */           throw new NullPointerException();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  75 */     ((GLSLShaderProgramRetained)this.retained).setShaderAttrNames(paramArrayOfString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getShaderAttrNames() {
/*  81 */     if (isLiveOrCompiled() && 
/*  82 */       !getCapability(1)) {
/*  83 */       throw new CapabilityNotSetException(J3dI18N.getString("GLSLShaderProgram0"));
/*     */     }
/*     */ 
/*     */     
/*  87 */     return ((GLSLShaderProgramRetained)this.retained).getShaderAttrNames();
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
/*     */   public void setShaders(Shader[] paramArrayOfShader) {
/* 117 */     checkForLiveOrCompiled();
/*     */     
/* 119 */     if (paramArrayOfShader != null)
/*     */     {
/* 121 */       for (byte b = 0; b < paramArrayOfShader.length; b++) {
/* 122 */         if (paramArrayOfShader[b].getShadingLanguage() != 1) {
/* 123 */           throw new IllegalArgumentException(J3dI18N.getString("GLSLShaderProgram2"));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 128 */         SourceCodeShader sourceCodeShader = (SourceCodeShader)paramArrayOfShader[b];
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 133 */     ((GLSLShaderProgramRetained)this.retained).setShaders(paramArrayOfShader);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Shader[] getShaders() {
/* 139 */     if (isLiveOrCompiled() && 
/* 140 */       !getCapability(0)) {
/* 141 */       throw new CapabilityNotSetException(J3dI18N.getString("GLSLShaderProgram1"));
/*     */     }
/*     */ 
/*     */     
/* 145 */     return ((GLSLShaderProgramRetained)this.retained).getShaders();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 153 */     this.retained = new GLSLShaderProgramRetained();
/* 154 */     this.retained.setSource(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\GLSLShaderProgram.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */