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
/*     */ public class SourceCodeShader
/*     */   extends Shader
/*     */ {
/*     */   SourceCodeShader() {}
/*     */   
/*     */   public SourceCodeShader(int paramInt1, int paramInt2, String paramString) {
/*  52 */     super(paramInt1, paramInt2);
/*  53 */     if (paramString == null) {
/*  54 */       throw new NullPointerException();
/*     */     }
/*  56 */     ((SourceCodeShaderRetained)this.retained).initShaderSource(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public String getShaderSource() { return ((SourceCodeShaderRetained)this.retained).getShaderSource(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  74 */     this.retained = new SourceCodeShaderRetained();
/*  75 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/*  83 */     SourceCodeShaderRetained sourceCodeShaderRetained = (SourceCodeShaderRetained)this.retained;
/*     */     
/*  85 */     SourceCodeShader sourceCodeShader = new SourceCodeShader(sourceCodeShaderRetained.getShadingLanguage(), sourceCodeShaderRetained.getShaderType(), sourceCodeShaderRetained.getShaderSource());
/*     */ 
/*     */     
/*  88 */     sourceCodeShader.duplicateNodeComponent(this);
/*  89 */     return sourceCodeShader;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 112 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 114 */     String str = ((SourceCodeShaderRetained)paramNodeComponent.retained).getShaderSource();
/*     */     
/* 116 */     if (str != null)
/* 117 */       ((SourceCodeShaderRetained)this.retained).setShaderSource(str); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SourceCodeShader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */