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
/*     */ public class ShaderAttributeArray
/*     */   extends ShaderAttributeObject
/*     */ {
/*  61 */   public ShaderAttributeArray(String paramString, Object paramObject) { super(paramString, paramObject); }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*  66 */     if (isLiveOrCompiled() && 
/*  67 */       !getCapability(0)) {
/*  68 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeObject0"));
/*     */     }
/*  70 */     return ((ShaderAttributeArrayRetained)this.retained).getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Object paramObject) {
/*  75 */     if (paramObject == null) {
/*  76 */       throw new NullPointerException();
/*     */     }
/*     */     
/*  79 */     if (isLiveOrCompiled() && 
/*  80 */       !getCapability(1)) {
/*  81 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeObject1"));
/*     */     }
/*  83 */     if (isLive()) {
/*  84 */       ((ShaderAttributeArrayRetained)this.retained).setValue(paramObject);
/*     */     } else {
/*  86 */       ((ShaderAttributeArrayRetained)this.retained).initValue(paramObject);
/*     */     } 
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
/*     */   public void setValue(int paramInt, Object paramObject) {
/* 108 */     if (paramObject == null) {
/* 109 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 112 */     if (isLiveOrCompiled() && 
/* 113 */       !getCapability(1)) {
/* 114 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeObject1"));
/*     */     }
/* 116 */     if (isLive()) {
/* 117 */       ((ShaderAttributeArrayRetained)this.retained).setValue(paramInt, paramObject);
/*     */     } else {
/* 119 */       ((ShaderAttributeArrayRetained)this.retained).initValue(paramInt, paramObject);
/*     */     } 
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
/*     */   public int length() {
/* 132 */     if (isLiveOrCompiled() && 
/* 133 */       !getCapability(0)) {
/* 134 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeObject0"));
/*     */     }
/* 136 */     return ((ShaderAttributeArrayRetained)this.retained).length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 144 */     this.retained = new ShaderAttributeArrayRetained();
/* 145 */     this.retained.setSource(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ShaderAttributeArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */