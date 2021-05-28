/*    */ package javax.media.j3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class SourceCodeShaderRetained
/*    */   extends ShaderRetained
/*    */ {
/* 24 */   private String shaderSource = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   final void initShaderSource(String paramString) { this.shaderSource = paramString; }
/*    */ 
/*    */   
/*    */   final void set(int paramInt1, int paramInt2, String paramString) {
/* 42 */     this.shadingLanguage = paramInt1;
/* 43 */     this.shaderType = paramInt2;
/* 44 */     this.shaderSource = paramString;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   final String getShaderSource() { return this.shaderSource; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   final void setShaderSource(String paramString) { this.shaderSource = paramString; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void createMirrorObject() {
/* 63 */     if (this.mirror == null) {
/* 64 */       SourceCodeShaderRetained sourceCodeShaderRetained = new SourceCodeShaderRetained();
/* 65 */       this.mirror = sourceCodeShaderRetained;
/*    */     } 
/*    */     
/* 68 */     initMirrorObject();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void initMirrorObject() {
/* 75 */     this.mirror.source = this.source;
/*    */     
/* 77 */     ((SourceCodeShaderRetained)this.mirror).set(this.shadingLanguage, this.shaderType, this.shaderSource);
/* 78 */     ((SourceCodeShaderRetained)this.mirror).shaderData = null;
/*    */   }
/*    */ 
/*    */   
/* 82 */   void updateMirrorObject(int paramInt, Object paramObject) { System.err.println("SourceCodeShader.updateMirrorObject not implemented yet!"); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SourceCodeShaderRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */