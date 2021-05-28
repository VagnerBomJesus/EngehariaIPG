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
/*     */ public class CgShaderProgram
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
/*  47 */     ((CgShaderProgramRetained)this.retained).setVertexAttrNames(paramArrayOfString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getVertexAttrNames() {
/*  53 */     if (isLiveOrCompiled() && 
/*  54 */       !getCapability(1)) {
/*  55 */       throw new CapabilityNotSetException(J3dI18N.getString("CgShaderProgram0"));
/*     */     }
/*     */ 
/*     */     
/*  59 */     return ((CgShaderProgramRetained)this.retained).getVertexAttrNames();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShaderAttrNames(String[] paramArrayOfString) {
/*  64 */     checkForLiveOrCompiled();
/*     */     
/*  66 */     if (paramArrayOfString != null) {
/*  67 */       for (byte b = 0; b < paramArrayOfString.length; b++) {
/*  68 */         if (paramArrayOfString[b] == null) {
/*  69 */           throw new NullPointerException();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  74 */     ((CgShaderProgramRetained)this.retained).setShaderAttrNames(paramArrayOfString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getShaderAttrNames() {
/*  80 */     if (isLiveOrCompiled() && 
/*  81 */       !getCapability(1)) {
/*  82 */       throw new CapabilityNotSetException(J3dI18N.getString("CgShaderProgram0"));
/*     */     }
/*     */ 
/*     */     
/*  86 */     return ((CgShaderProgramRetained)this.retained).getShaderAttrNames();
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
/*     */   
/*     */   public void setShaders(Shader[] paramArrayOfShader) {
/* 117 */     checkForLiveOrCompiled();
/*     */     
/* 119 */     if (paramArrayOfShader != null)
/*     */     {
/* 121 */       for (byte b = 0; b < paramArrayOfShader.length; b++) {
/* 122 */         boolean bool1 = false;
/* 123 */         boolean bool2 = false;
/*     */ 
/*     */         
/* 126 */         if (paramArrayOfShader[b].getShadingLanguage() != 2) {
/* 127 */           throw new IllegalArgumentException(J3dI18N.getString("CgShaderProgram2"));
/*     */         }
/*     */ 
/*     */         
/* 131 */         if (paramArrayOfShader[b].getShaderType() == 1) {
/* 132 */           if (bool1) {
/* 133 */             throw new IllegalArgumentException(J3dI18N.getString("CgShaderProgram3"));
/*     */           }
/* 135 */           bool1 = true;
/*     */         } else {
/*     */           
/* 138 */           if (bool2) {
/* 139 */             throw new IllegalArgumentException(J3dI18N.getString("CgShaderProgram4"));
/*     */           }
/* 141 */           bool2 = true;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 146 */         SourceCodeShader sourceCodeShader = (SourceCodeShader)paramArrayOfShader[b];
/*     */       } 
/*     */     }
/*     */     
/* 150 */     ((CgShaderProgramRetained)this.retained).setShaders(paramArrayOfShader);
/*     */   }
/*     */ 
/*     */   
/*     */   public Shader[] getShaders() {
/* 155 */     if (isLiveOrCompiled() && 
/* 156 */       !getCapability(0)) {
/* 157 */       throw new CapabilityNotSetException(J3dI18N.getString("CgShaderProgram1"));
/*     */     }
/*     */ 
/*     */     
/* 161 */     return ((CgShaderProgramRetained)this.retained).getShaders();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 169 */     this.retained = new CgShaderProgramRetained();
/* 170 */     this.retained.setSource(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\CgShaderProgram.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */