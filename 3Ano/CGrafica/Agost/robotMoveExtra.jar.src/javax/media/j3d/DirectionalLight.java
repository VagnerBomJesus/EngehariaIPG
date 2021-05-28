/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DirectionalLight
/*     */   extends Light
/*     */ {
/*     */   public static final int ALLOW_DIRECTION_READ = 18;
/*     */   public static final int ALLOW_DIRECTION_WRITE = 19;
/*  45 */   private static final int[] readCapabilities = { 18 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public DirectionalLight() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DirectionalLight(Color3f paramColor3f, Vector3f paramVector3f) {
/*  68 */     super(paramColor3f);
/*     */ 
/*     */     
/*  71 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  73 */     ((DirectionalLightRetained)this.retained).initDirection(paramVector3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DirectionalLight(boolean paramBoolean, Color3f paramColor3f, Vector3f paramVector3f) {
/*  84 */     super(paramBoolean, paramColor3f);
/*     */ 
/*     */     
/*  87 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  89 */     ((DirectionalLightRetained)this.retained).initDirection(paramVector3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  97 */     this.retained = new DirectionalLightRetained();
/*  98 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(Vector3f paramVector3f) {
/* 108 */     if (isLiveOrCompiled() && 
/* 109 */       !getCapability(19)) {
/* 110 */       throw new CapabilityNotSetException(J3dI18N.getString("DirectionalLight0"));
/*     */     }
/*     */     
/* 113 */     if (isLive()) {
/* 114 */       ((DirectionalLightRetained)this.retained).setDirection(paramVector3f);
/*     */     } else {
/* 116 */       ((DirectionalLightRetained)this.retained).initDirection(paramVector3f);
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
/*     */   public void setDirection(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 128 */     if (isLiveOrCompiled() && 
/* 129 */       !getCapability(19)) {
/* 130 */       throw new CapabilityNotSetException(J3dI18N.getString("DirectionalLight1"));
/*     */     }
/*     */     
/* 133 */     if (isLive()) {
/* 134 */       ((DirectionalLightRetained)this.retained).setDirection(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 136 */       ((DirectionalLightRetained)this.retained).initDirection(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getDirection(Vector3f paramVector3f) {
/* 146 */     if (isLiveOrCompiled() && 
/* 147 */       !getCapability(18)) {
/* 148 */       throw new CapabilityNotSetException(J3dI18N.getString("DirectionalLight2"));
/*     */     }
/*     */     
/* 151 */     ((DirectionalLightRetained)this.retained).getDirection(paramVector3f);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 169 */     DirectionalLight directionalLight = new DirectionalLight();
/* 170 */     directionalLight.duplicateNode(this, paramBoolean);
/* 171 */     return directionalLight;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 197 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 199 */     Vector3f vector3f = new Vector3f();
/* 200 */     ((DirectionalLightRetained)paramNode.retained).getDirection(vector3f);
/* 201 */     ((DirectionalLightRetained)this.retained).initDirection(vector3f);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\DirectionalLight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */