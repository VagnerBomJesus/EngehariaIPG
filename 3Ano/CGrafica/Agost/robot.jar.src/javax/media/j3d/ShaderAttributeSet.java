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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShaderAttributeSet
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_ATTRIBUTES_READ = 0;
/*     */   public static final int ALLOW_ATTRIBUTES_WRITE = 1;
/*  80 */   private static final int[] readCapabilities = { 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public ShaderAttributeSet() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(ShaderAttribute paramShaderAttribute) {
/* 111 */     if (paramShaderAttribute == null) {
/* 112 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 115 */     if (isLiveOrCompiled() && 
/* 116 */       !getCapability(1)) {
/* 117 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeSet1"));
/*     */     }
/* 119 */     ((ShaderAttributeSetRetained)this.retained).put(paramShaderAttribute);
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
/*     */   public ShaderAttribute get(String paramString) {
/* 141 */     if (paramString == null) {
/* 142 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 145 */     if (isLiveOrCompiled() && 
/* 146 */       !getCapability(0)) {
/* 147 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeSet0"));
/*     */     }
/* 149 */     return ((ShaderAttributeSetRetained)this.retained).get(paramString);
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
/*     */   public void remove(String paramString) {
/* 165 */     if (paramString == null) {
/* 166 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 169 */     if (isLiveOrCompiled() && 
/* 170 */       !getCapability(1)) {
/* 171 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeSet1"));
/*     */     }
/* 173 */     ((ShaderAttributeSetRetained)this.retained).remove(paramString);
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
/*     */   public void remove(ShaderAttribute paramShaderAttribute) {
/* 193 */     if (paramShaderAttribute == null) {
/* 194 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 197 */     if (isLiveOrCompiled() && 
/* 198 */       !getCapability(1)) {
/* 199 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeSet1"));
/*     */     }
/* 201 */     ((ShaderAttributeSetRetained)this.retained).remove(paramShaderAttribute);
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
/*     */   public void clear() {
/* 213 */     if (isLiveOrCompiled() && 
/* 214 */       !getCapability(1)) {
/* 215 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeSet1"));
/*     */     }
/* 217 */     ((ShaderAttributeSetRetained)this.retained).clear();
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
/*     */   public ShaderAttribute[] getAll() {
/* 230 */     if (isLiveOrCompiled() && 
/* 231 */       !getCapability(0)) {
/* 232 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeSet0"));
/*     */     }
/* 234 */     return ((ShaderAttributeSetRetained)this.retained).getAll();
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
/*     */   public int size() {
/* 247 */     if (isLiveOrCompiled() && 
/* 248 */       !getCapability(0)) {
/* 249 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeSet0"));
/*     */     }
/* 251 */     return ((ShaderAttributeSetRetained)this.retained).size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 260 */     this.retained = new ShaderAttributeSetRetained();
/* 261 */     this.retained.setSource(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ShaderAttributeSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */