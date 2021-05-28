/*     */ package javax.media.j3d;
/*     */ 
/*     */ import com.sun.opengl.cg.CGcontext;
/*     */ import com.sun.opengl.cg.CgGL;
/*     */ import java.nio.Buffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.media.opengl.GL;
/*     */ import javax.media.opengl.GLContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JoglContext
/*     */   implements Context
/*     */ {
/*     */   private GLContext context;
/*     */   private int maxTexCoordSets;
/*     */   private float alphaClearValue;
/*     */   private int currentTextureUnit;
/*     */   private int currentCombinerUnit;
/*     */   private boolean hasMultisample;
/*     */   private JoglShaderObject shaderProgram;
/*     */   private VertexAttributeImpl vertexAttrImpl;
/*     */   private int glslVertexAttrOffset;
/*     */   private CGcontext cgContext;
/*     */   private int cgVertexProfile;
/*     */   private int cgFragmentProfile;
/*     */   
/*     */   class CgVertexAttributeImpl
/*     */     implements VertexAttributeImpl
/*     */   {
/*     */     public void vertexAttrPointer(GL param1GL, int param1Int1, int param1Int2, int param1Int3, int param1Int4, Buffer param1Buffer) {
/*  51 */       JoglCgShaderProgramInfo joglCgShaderProgramInfo = (JoglCgShaderProgramInfo)JoglContext.this.shaderProgram;
/*  52 */       if (joglCgShaderProgramInfo != null && param1Int1 < joglCgShaderProgramInfo.getNumVertexAttributes()) {
/*  53 */         CgGL.cgGLSetParameterPointer(joglCgShaderProgramInfo.getVertexAttributes()[param1Int1], param1Int2, param1Int3, param1Int4, param1Buffer);
/*     */       
/*     */       }
/*  56 */       else if (joglCgShaderProgramInfo == null) {
/*  57 */         System.err.println("    shaderProgramInfo is null");
/*     */       } else {
/*  59 */         System.err.println("    index (" + param1Int1 + ") out of range: numVtxAttrs = " + joglCgShaderProgramInfo.getNumVertexAttributes());
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void enableVertexAttrArray(GL param1GL, int param1Int) {
/*  66 */       JoglCgShaderProgramInfo joglCgShaderProgramInfo = (JoglCgShaderProgramInfo)JoglContext.this.shaderProgram;
/*  67 */       if (joglCgShaderProgramInfo != null && param1Int < joglCgShaderProgramInfo.getNumVertexAttributes()) {
/*  68 */         CgGL.cgGLEnableClientState(joglCgShaderProgramInfo.getVertexAttributes()[param1Int]);
/*     */       }
/*  70 */       else if (joglCgShaderProgramInfo == null) {
/*  71 */         System.err.println("    shaderProgramInfo is null");
/*     */       } else {
/*  73 */         System.err.println("    index (" + param1Int + ") out of range: numVtxAttrs = " + joglCgShaderProgramInfo.getNumVertexAttributes());
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void disableVertexAttrArray(GL param1GL, int param1Int) {
/*  80 */       JoglCgShaderProgramInfo joglCgShaderProgramInfo = (JoglCgShaderProgramInfo)JoglContext.this.shaderProgram;
/*  81 */       if (joglCgShaderProgramInfo != null && param1Int < joglCgShaderProgramInfo.getNumVertexAttributes()) {
/*  82 */         CgGL.cgGLDisableClientState(joglCgShaderProgramInfo.getVertexAttributes()[param1Int]);
/*     */       }
/*  84 */       else if (joglCgShaderProgramInfo == null) {
/*  85 */         System.err.println("    shaderProgramInfo is null");
/*     */       } else {
/*  87 */         System.err.println("    index (" + param1Int + ") out of range: numVtxAttrs = " + joglCgShaderProgramInfo.getNumVertexAttributes());
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     public void vertexAttr1fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { throw new RuntimeException("Java 3D ERROR : Assertion failed: invalid call to cgVertexAttr1fv"); }
/*     */ 
/*     */ 
/*     */     
/* 101 */     public void vertexAttr2fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { throw new RuntimeException("Java 3D ERROR : Assertion failed: invalid call to cgVertexAttr2fv"); }
/*     */ 
/*     */ 
/*     */     
/* 105 */     public void vertexAttr3fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { throw new RuntimeException("Java 3D ERROR : Assertion failed: invalid call to cgVertexAttr3fv"); }
/*     */ 
/*     */ 
/*     */     
/* 109 */     public void vertexAttr4fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { throw new RuntimeException("Java 3D ERROR : Assertion failed: invalid call to cgVertexAttr4fv"); }
/*     */   }
/*     */ 
/*     */   
/*     */   class GLSLVertexAttributeImpl
/*     */     implements VertexAttributeImpl
/*     */   {
/* 116 */     public void vertexAttrPointer(GL param1GL, int param1Int1, int param1Int2, int param1Int3, int param1Int4, Buffer param1Buffer) { param1GL.glVertexAttribPointerARB(param1Int1 + JoglContext.this.glslVertexAttrOffset, param1Int2, param1Int3, false, param1Int4, param1Buffer); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     public void enableVertexAttrArray(GL param1GL, int param1Int) { param1GL.glEnableVertexAttribArrayARB(param1Int + JoglContext.this.glslVertexAttrOffset); }
/*     */ 
/*     */ 
/*     */     
/* 125 */     public void disableVertexAttrArray(GL param1GL, int param1Int) { param1GL.glDisableVertexAttribArrayARB(param1Int + JoglContext.this.glslVertexAttrOffset); }
/*     */ 
/*     */ 
/*     */     
/* 129 */     public void vertexAttr1fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { param1GL.glVertexAttrib1fvARB(param1Int + JoglContext.this.glslVertexAttrOffset, param1FloatBuffer); }
/*     */ 
/*     */ 
/*     */     
/* 133 */     public void vertexAttr2fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { param1GL.glVertexAttrib2fvARB(param1Int + JoglContext.this.glslVertexAttrOffset, param1FloatBuffer); }
/*     */ 
/*     */ 
/*     */     
/* 137 */     public void vertexAttr3fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { param1GL.glVertexAttrib3fvARB(param1Int + JoglContext.this.glslVertexAttrOffset, param1FloatBuffer); }
/*     */ 
/*     */ 
/*     */     
/* 141 */     public void vertexAttr4fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer) { param1GL.glVertexAttrib4fvARB(param1Int + JoglContext.this.glslVertexAttrOffset, param1FloatBuffer); }
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
/* 154 */   JoglContext(GLContext paramGLContext) { this.context = paramGLContext; }
/*     */ 
/*     */ 
/*     */   
/* 158 */   GLContext getGLContext() { return this.context; }
/*     */ 
/*     */   
/* 161 */   int getMaxTexCoordSets() { return this.maxTexCoordSets; }
/* 162 */   void setMaxTexCoordSets(int paramInt) { this.maxTexCoordSets = paramInt; }
/* 163 */   float getAlphaClearValue() { return this.alphaClearValue; }
/* 164 */   void setAlphaClearValue(float paramFloat) { this.alphaClearValue = paramFloat; }
/* 165 */   int getCurrentTextureUnit() { return this.currentTextureUnit; }
/* 166 */   void setCurrentTextureUnit(int paramInt) { this.currentTextureUnit = paramInt; }
/* 167 */   int getCurrentCombinerUnit() { return this.currentCombinerUnit; }
/* 168 */   void setCurrentCombinerUnit(int paramInt) { this.currentCombinerUnit = paramInt; }
/* 169 */   boolean getHasMultisample() { return this.hasMultisample; }
/* 170 */   void setHasMultisample(boolean paramBoolean) { this.hasMultisample = paramBoolean; }
/*     */ 
/*     */   
/*     */   void initCgVertexAttributeImpl() {
/* 174 */     if (this.vertexAttrImpl != null) {
/* 175 */       throw new RuntimeException("Should not initialize the vertex attribute implementation twice");
/*     */     }
/* 177 */     this.vertexAttrImpl = new CgVertexAttributeImpl();
/*     */   }
/*     */   
/*     */   void initGLSLVertexAttributeImpl() {
/* 181 */     if (this.vertexAttrImpl != null) {
/* 182 */       throw new RuntimeException("Should not initialize the vertex attribute implementation twice");
/*     */     }
/* 184 */     this.vertexAttrImpl = new GLSLVertexAttributeImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 189 */   void vertexAttrPointer(GL paramGL, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Buffer paramBuffer) { this.vertexAttrImpl.vertexAttrPointer(paramGL, paramInt1, paramInt2, paramInt3, paramInt4, paramBuffer); }
/*     */ 
/*     */ 
/*     */   
/* 193 */   void enableVertexAttrArray(GL paramGL, int paramInt) { this.vertexAttrImpl.enableVertexAttrArray(paramGL, paramInt); }
/*     */ 
/*     */ 
/*     */   
/* 197 */   void disableVertexAttrArray(GL paramGL, int paramInt) { this.vertexAttrImpl.disableVertexAttrArray(paramGL, paramInt); }
/*     */ 
/*     */ 
/*     */   
/* 201 */   void vertexAttr1fv(GL paramGL, int paramInt, FloatBuffer paramFloatBuffer) { this.vertexAttrImpl.vertexAttr1fv(paramGL, paramInt, paramFloatBuffer); }
/*     */ 
/*     */ 
/*     */   
/* 205 */   void vertexAttr2fv(GL paramGL, int paramInt, FloatBuffer paramFloatBuffer) { this.vertexAttrImpl.vertexAttr2fv(paramGL, paramInt, paramFloatBuffer); }
/*     */ 
/*     */ 
/*     */   
/* 209 */   void vertexAttr3fv(GL paramGL, int paramInt, FloatBuffer paramFloatBuffer) { this.vertexAttrImpl.vertexAttr3fv(paramGL, paramInt, paramFloatBuffer); }
/*     */ 
/*     */ 
/*     */   
/* 213 */   void vertexAttr4fv(GL paramGL, int paramInt, FloatBuffer paramFloatBuffer) { this.vertexAttrImpl.vertexAttr4fv(paramGL, paramInt, paramFloatBuffer); }
/*     */ 
/*     */ 
/*     */   
/* 217 */   JoglShaderObject getShaderProgram() { return this.shaderProgram; }
/* 218 */   void setShaderProgram(JoglShaderObject paramJoglShaderObject) { this.shaderProgram = paramJoglShaderObject; }
/*     */ 
/*     */   
/* 221 */   int getGLSLVertexAttrOffset() { return this.glslVertexAttrOffset; }
/* 222 */   void setGLSLVertexAttrOffset(int paramInt) { this.glslVertexAttrOffset = paramInt; }
/*     */ 
/*     */   
/* 225 */   CGcontext getCgContext() { return this.cgContext; }
/* 226 */   void setCgContext(CGcontext paramCGcontext) { this.cgContext = paramCGcontext; }
/* 227 */   int getCgVertexProfile() { return this.cgVertexProfile; }
/* 228 */   void setCgVertexProfile(int paramInt) { this.cgVertexProfile = paramInt; }
/* 229 */   int getCgFragmentProfile() { return this.cgFragmentProfile; }
/* 230 */   void setCgFragmentProfile(int paramInt) { this.cgFragmentProfile = paramInt; }
/*     */   
/*     */   static interface VertexAttributeImpl {
/*     */     void vertexAttrPointer(GL param1GL, int param1Int1, int param1Int2, int param1Int3, int param1Int4, Buffer param1Buffer);
/*     */     
/*     */     void enableVertexAttrArray(GL param1GL, int param1Int);
/*     */     
/*     */     void disableVertexAttrArray(GL param1GL, int param1Int);
/*     */     
/*     */     void vertexAttr1fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer);
/*     */     
/*     */     void vertexAttr2fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer);
/*     */     
/*     */     void vertexAttr3fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer);
/*     */     
/*     */     void vertexAttr4fv(GL param1GL, int param1Int, FloatBuffer param1FloatBuffer);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\JoglContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */