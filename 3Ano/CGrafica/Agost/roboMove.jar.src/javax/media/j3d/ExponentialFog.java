/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExponentialFog
/*     */   extends Fog
/*     */ {
/*     */   public static final int ALLOW_DENSITY_READ = 16;
/*     */   public static final int ALLOW_DENSITY_WRITE = 17;
/*  50 */   private static final int[] readCapabilities = { 16 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public ExponentialFog() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExponentialFog(Color3f paramColor3f) {
/*  72 */     super(paramColor3f);
/*     */ 
/*     */     
/*  75 */     setDefaultReadCapabilities(readCapabilities);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExponentialFog(Color3f paramColor3f, float paramFloat) {
/*  85 */     super(paramColor3f);
/*     */ 
/*     */     
/*  88 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  90 */     ((ExponentialFogRetained)this.retained).initDensity(paramFloat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExponentialFog(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 100 */     super(paramFloat1, paramFloat2, paramFloat3);
/*     */ 
/*     */     
/* 103 */     setDefaultReadCapabilities(readCapabilities);
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
/*     */   public ExponentialFog(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 115 */     super(paramFloat1, paramFloat2, paramFloat3);
/*     */ 
/*     */     
/* 118 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 120 */     ((ExponentialFogRetained)this.retained).initDensity(paramFloat4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDensity(float paramFloat) {
/* 130 */     if (isLiveOrCompiled() && 
/* 131 */       !getCapability(17)) {
/* 132 */       throw new CapabilityNotSetException(J3dI18N.getString("ExponentialFog0"));
/*     */     }
/* 134 */     if (isLive()) {
/* 135 */       ((ExponentialFogRetained)this.retained).setDensity(paramFloat);
/*     */     } else {
/* 137 */       ((ExponentialFogRetained)this.retained).initDensity(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDensity() {
/* 147 */     if (isLiveOrCompiled() && 
/* 148 */       !getCapability(16)) {
/* 149 */       throw new CapabilityNotSetException(J3dI18N.getString("ExponentialFog1"));
/*     */     }
/* 151 */     return ((ExponentialFogRetained)this.retained).getDensity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 159 */     this.retained = new ExponentialFogRetained();
/* 160 */     this.retained.setSource(this);
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
/* 178 */     ExponentialFog exponentialFog = new ExponentialFog();
/* 179 */     exponentialFog.duplicateNode(this, paramBoolean);
/* 180 */     return exponentialFog;
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
/* 206 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 208 */     ((ExponentialFogRetained)this.retained).initDensity(((ExponentialFogRetained)paramNode.retained).getDensity());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ExponentialFog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */