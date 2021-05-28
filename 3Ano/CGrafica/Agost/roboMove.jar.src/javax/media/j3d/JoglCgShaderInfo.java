/*    */ package javax.media.j3d;
/*    */ 
/*    */ import com.sun.opengl.cg.CGprogram;
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
/*    */ class JoglCgShaderInfo
/*    */   extends JoglShaderObject
/*    */ {
/*    */   private CGprogram cgShader;
/*    */   private int j3dShaderType;
/*    */   private int shaderProfile;
/*    */   
/* 23 */   JoglCgShaderInfo() { super(0); }
/*    */ 
/*    */   
/* 26 */   public void setCgShader(CGprogram paramCGprogram) { this.cgShader = paramCGprogram; }
/* 27 */   public CGprogram getCgShader() { return this.cgShader; }
/* 28 */   public void setJ3DShaderType(int paramInt) { this.j3dShaderType = paramInt; }
/* 29 */   public int getJ3DShaderType() { return this.j3dShaderType; }
/* 30 */   public void setShaderProfile(int paramInt) { this.shaderProfile = paramInt; }
/* 31 */   public int getShaderProfile() { return this.shaderProfile; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\JoglCgShaderInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */