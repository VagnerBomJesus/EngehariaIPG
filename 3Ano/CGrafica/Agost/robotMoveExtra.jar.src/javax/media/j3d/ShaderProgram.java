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
/*     */ public abstract class ShaderProgram
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_SHADERS_READ = 0;
/*     */   public static final int ALLOW_NAMES_READ = 1;
/*  51 */   private static final int[] readCapabilities = { 0, 1 };
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
/*  68 */   ShaderProgram() { setDefaultReadCapabilities(readCapabilities); }
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
/* 177 */   private static ShaderErrorListener defaultErrorListener = null; public abstract void setVertexAttrNames(String[] paramArrayOfString); public abstract String[] getVertexAttrNames();
/*     */   public abstract void setShaderAttrNames(String[] paramArrayOfString);
/*     */   static ShaderErrorListener getDefaultErrorListener() {
/* 180 */     if (defaultErrorListener == null) {
/* 181 */       defaultErrorListener = new DefaultErrorListener();
/*     */     }
/*     */     
/* 184 */     return defaultErrorListener;
/*     */   } public abstract String[] getShaderAttrNames();
/*     */   public abstract void setShaders(Shader[] paramArrayOfShader);
/*     */   public abstract Shader[] getShaders();
/*     */   static class DefaultErrorListener implements ShaderErrorListener { public void errorOccurred(ShaderError param1ShaderError) {
/* 189 */       System.err.println();
/* 190 */       System.err.println("DefaultShaderErrorListener.errorOccurred:");
/* 191 */       param1ShaderError.printVerbose();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ShaderProgram.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */