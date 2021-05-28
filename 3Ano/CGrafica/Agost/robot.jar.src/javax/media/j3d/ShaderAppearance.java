/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShaderAppearance
/*     */   extends Appearance
/*     */ {
/*     */   public static final int ALLOW_SHADER_PROGRAM_READ = 22;
/*     */   public static final int ALLOW_SHADER_PROGRAM_WRITE = 23;
/*     */   public static final int ALLOW_SHADER_ATTRIBUTE_SET_READ = 24;
/*     */   public static final int ALLOW_SHADER_ATTRIBUTE_SET_WRITE = 25;
/* 108 */   private static final int[] readCapabilities = { 22, 24 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public ShaderAppearance() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 129 */     this.retained = new ShaderAppearanceRetained();
/* 130 */     this.retained.setSource(this);
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
/*     */   public void setShaderProgram(ShaderProgram paramShaderProgram) {
/* 143 */     if (isLiveOrCompiled() && 
/* 144 */       !getCapability(23)) {
/* 145 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAppearance0"));
/*     */     }
/*     */     
/* 148 */     ((ShaderAppearanceRetained)this.retained).setShaderProgram(paramShaderProgram);
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
/*     */   public ShaderProgram getShaderProgram() {
/* 162 */     if (isLiveOrCompiled() && 
/* 163 */       !getCapability(22)) {
/* 164 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAppearance1"));
/*     */     }
/* 166 */     return ((ShaderAppearanceRetained)this.retained).getShaderProgram();
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
/*     */   public void setShaderAttributeSet(ShaderAttributeSet paramShaderAttributeSet) {
/* 179 */     if (isLiveOrCompiled() && 
/* 180 */       !getCapability(25)) {
/* 181 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAppearance2"));
/*     */     }
/*     */     
/* 184 */     ((ShaderAppearanceRetained)this.retained).setShaderAttributeSet(paramShaderAttributeSet);
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
/*     */   public ShaderAttributeSet getShaderAttributeSet() {
/* 196 */     if (isLiveOrCompiled() && 
/* 197 */       !getCapability(24)) {
/* 198 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAppearance3"));
/*     */     }
/* 200 */     return ((ShaderAppearanceRetained)this.retained).getShaderAttributeSet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 208 */     ShaderAppearance shaderAppearance = new ShaderAppearance();
/* 209 */     shaderAppearance.duplicateNodeComponent(this);
/* 210 */     return shaderAppearance;
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
/* 221 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { checkDuplicateNodeComponent(paramNodeComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 246 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 248 */     Hashtable hashtable = paramNodeComponent.nodeHashtable;
/*     */     
/* 250 */     ShaderAppearanceRetained shaderAppearanceRetained1 = (ShaderAppearanceRetained)paramNodeComponent.retained;
/*     */ 
/*     */     
/* 253 */     ShaderAppearanceRetained shaderAppearanceRetained2 = (ShaderAppearanceRetained)this.retained;
/*     */     
/* 255 */     shaderAppearanceRetained2.setShaderProgram((ShaderProgram)getNodeComponent(shaderAppearanceRetained1.getShaderProgram(), paramBoolean, hashtable));
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
/*     */   boolean duplicateChild() {
/* 268 */     if (super.duplicateChild()) {
/* 269 */       return true;
/*     */     }
/* 271 */     if (getDuplicateOnCloneTree()) {
/* 272 */       return true;
/*     */     }
/* 274 */     ShaderAppearanceRetained shaderAppearanceRetained = (ShaderAppearanceRetained)this.retained;
/*     */ 
/*     */ 
/*     */     
/* 278 */     ShaderProgram shaderProgram = shaderAppearanceRetained.getShaderProgram();
/* 279 */     if (shaderProgram != null && shaderProgram.getDuplicateOnCloneTree()) {
/* 280 */       return true;
/*     */     }
/* 282 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ShaderAppearance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */