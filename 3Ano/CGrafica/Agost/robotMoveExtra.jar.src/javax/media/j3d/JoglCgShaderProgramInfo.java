/*    */ package javax.media.j3d;
/*    */ 
/*    */ import com.sun.opengl.cg.CGparameter;
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
/*    */ class JoglCgShaderProgramInfo
/*    */   extends JoglShaderObject
/*    */ {
/*    */   private JoglCgShaderInfo vShader;
/*    */   private JoglCgShaderInfo fShader;
/*    */   private CGparameter[] vtxAttrs;
/*    */   
/* 24 */   JoglCgShaderProgramInfo() { super(0); }
/*    */ 
/*    */   
/* 27 */   public JoglCgShaderInfo getVertexShader() { return this.vShader; }
/* 28 */   public void setVertexShader(JoglCgShaderInfo paramJoglCgShaderInfo) { this.vShader = paramJoglCgShaderInfo; }
/* 29 */   public JoglCgShaderInfo getFragmentShader() { return this.fShader; }
/* 30 */   public void setFragmentShader(JoglCgShaderInfo paramJoglCgShaderInfo) { this.fShader = paramJoglCgShaderInfo; }
/* 31 */   public CGparameter[] getVertexAttributes() { return this.vtxAttrs; }
/* 32 */   public void setVertexAttributes(CGparameter[] paramArrayOfCGparameter) { this.vtxAttrs = paramArrayOfCGparameter; }
/*    */   public int getNumVertexAttributes() {
/* 34 */     if (this.vtxAttrs == null)
/* 35 */       return 0; 
/* 36 */     return this.vtxAttrs.length;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\JoglCgShaderProgramInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */