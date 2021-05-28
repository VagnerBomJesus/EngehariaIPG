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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LinearFog
/*     */   extends Fog
/*     */ {
/*     */   public static final int ALLOW_DISTANCE_READ = 16;
/*     */   public static final int ALLOW_DISTANCE_WRITE = 17;
/*  55 */   private static final int[] readCapabilities = { 16 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public LinearFog() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinearFog(Color3f paramColor3f) {
/*  78 */     super(paramColor3f);
/*     */ 
/*     */     
/*  81 */     setDefaultReadCapabilities(readCapabilities);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinearFog(Color3f paramColor3f, double paramDouble1, double paramDouble2) {
/*  91 */     super(paramColor3f);
/*     */ 
/*     */     
/*  94 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  96 */     ((LinearFogRetained)this.retained).initFrontDistance(paramDouble1);
/*  97 */     ((LinearFogRetained)this.retained).initBackDistance(paramDouble2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinearFog(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 107 */     super(paramFloat1, paramFloat2, paramFloat3);
/*     */ 
/*     */     
/* 110 */     setDefaultReadCapabilities(readCapabilities);
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
/*     */   public LinearFog(float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble1, double paramDouble2) {
/* 123 */     super(paramFloat1, paramFloat2, paramFloat3);
/*     */ 
/*     */     
/* 126 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 128 */     ((LinearFogRetained)this.retained).initFrontDistance(paramDouble1);
/* 129 */     ((LinearFogRetained)this.retained).initBackDistance(paramDouble2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFrontDistance(double paramDouble) {
/* 139 */     if (isLiveOrCompiled() && 
/* 140 */       !getCapability(17)) {
/* 141 */       throw new CapabilityNotSetException(J3dI18N.getString("LinearFog0"));
/*     */     }
/* 143 */     if (isLive()) {
/* 144 */       ((LinearFogRetained)this.retained).setFrontDistance(paramDouble);
/*     */     } else {
/* 146 */       ((LinearFogRetained)this.retained).initFrontDistance(paramDouble);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFrontDistance() {
/* 157 */     if (isLiveOrCompiled() && 
/* 158 */       !getCapability(16)) {
/* 159 */       throw new CapabilityNotSetException(J3dI18N.getString("LinearFog1"));
/*     */     }
/* 161 */     return ((LinearFogRetained)this.retained).getFrontDistance();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackDistance(double paramDouble) {
/* 171 */     if (isLiveOrCompiled() && 
/* 172 */       !getCapability(17))
/* 173 */       throw new CapabilityNotSetException(J3dI18N.getString("LinearFog0")); 
/* 174 */     if (isLive()) {
/* 175 */       ((LinearFogRetained)this.retained).setBackDistance(paramDouble);
/*     */     } else {
/* 177 */       ((LinearFogRetained)this.retained).initBackDistance(paramDouble);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getBackDistance() {
/* 188 */     if (isLiveOrCompiled() && 
/* 189 */       !getCapability(16)) {
/* 190 */       throw new CapabilityNotSetException(J3dI18N.getString("LinearFog1"));
/*     */     }
/* 192 */     return ((LinearFogRetained)this.retained).getBackDistance();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 200 */     this.retained = new LinearFogRetained();
/* 201 */     this.retained.setSource(this);
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
/* 219 */     LinearFog linearFog = new LinearFog();
/* 220 */     linearFog.duplicateNode(this, paramBoolean);
/* 221 */     return linearFog;
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
/* 247 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 249 */     LinearFogRetained linearFogRetained1 = (LinearFogRetained)paramNode.retained;
/* 250 */     LinearFogRetained linearFogRetained2 = (LinearFogRetained)this.retained;
/*     */     
/* 252 */     linearFogRetained2.initFrontDistance(linearFogRetained1.getFrontDistance());
/* 253 */     linearFogRetained2.initBackDistance(linearFogRetained1.getBackDistance());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\LinearFog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */