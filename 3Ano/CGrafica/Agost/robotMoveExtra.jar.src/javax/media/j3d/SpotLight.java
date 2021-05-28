/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Point3f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpotLight
/*     */   extends PointLight
/*     */ {
/*     */   public static final int ALLOW_SPREAD_ANGLE_WRITE = 22;
/*     */   public static final int ALLOW_SPREAD_ANGLE_READ = 23;
/*     */   public static final int ALLOW_CONCENTRATION_WRITE = 24;
/*     */   public static final int ALLOW_CONCENTRATION_READ = 25;
/*     */   public static final int ALLOW_DIRECTION_WRITE = 26;
/*     */   public static final int ALLOW_DIRECTION_READ = 27;
/*  98 */   private static final int[] readCapabilities = { 23, 25, 27 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public SpotLight() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpotLight(Color3f paramColor3f, Point3f paramPoint3f1, Point3f paramPoint3f2, Vector3f paramVector3f, float paramFloat1, float paramFloat2) {
/* 135 */     super(paramColor3f, paramPoint3f1, paramPoint3f2);
/*     */ 
/*     */     
/* 138 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 140 */     ((SpotLightRetained)this.retained).initDirection(paramVector3f);
/* 141 */     ((SpotLightRetained)this.retained).initSpreadAngle(paramFloat1);
/* 142 */     ((SpotLightRetained)this.retained).initConcentration(paramFloat2);
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
/*     */   public SpotLight(boolean paramBoolean, Color3f paramColor3f, Point3f paramPoint3f1, Point3f paramPoint3f2, Vector3f paramVector3f, float paramFloat1, float paramFloat2) {
/* 163 */     super(paramBoolean, paramColor3f, paramPoint3f1, paramPoint3f2);
/*     */ 
/*     */     
/* 166 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 168 */     ((SpotLightRetained)this.retained).initDirection(paramVector3f);
/* 169 */     ((SpotLightRetained)this.retained).initSpreadAngle(paramFloat1);
/* 170 */     ((SpotLightRetained)this.retained).initConcentration(paramFloat2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 178 */     this.retained = new SpotLightRetained();
/* 179 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpreadAngle(float paramFloat) {
/* 190 */     if (isLiveOrCompiled() && 
/* 191 */       !getCapability(22)) {
/* 192 */       throw new CapabilityNotSetException(J3dI18N.getString("SpotLight0"));
/*     */     }
/*     */     
/* 195 */     if (isLive()) {
/* 196 */       ((SpotLightRetained)this.retained).setSpreadAngle(paramFloat);
/*     */     } else {
/* 198 */       ((SpotLightRetained)this.retained).initSpreadAngle(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSpreadAngle() {
/* 209 */     if (isLiveOrCompiled() && 
/* 210 */       !getCapability(23)) {
/* 211 */       throw new CapabilityNotSetException(J3dI18N.getString("SpotLight1"));
/*     */     }
/*     */     
/* 214 */     return ((SpotLightRetained)this.retained).getSpreadAngle();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConcentration(float paramFloat) {
/* 224 */     if (isLiveOrCompiled() && 
/* 225 */       !getCapability(24)) {
/* 226 */       throw new CapabilityNotSetException(J3dI18N.getString("SpotLight2"));
/*     */     }
/* 228 */     if (isLive()) {
/* 229 */       ((SpotLightRetained)this.retained).setConcentration(paramFloat);
/*     */     } else {
/* 231 */       ((SpotLightRetained)this.retained).initConcentration(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getConcentration() {
/* 241 */     if (isLiveOrCompiled() && 
/* 242 */       !getCapability(25)) {
/* 243 */       throw new CapabilityNotSetException(J3dI18N.getString("SpotLight3"));
/*     */     }
/* 245 */     return ((SpotLightRetained)this.retained).getConcentration();
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
/*     */   public void setDirection(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 257 */     if (isLiveOrCompiled() && 
/* 258 */       !getCapability(26)) {
/* 259 */       throw new CapabilityNotSetException(J3dI18N.getString("SpotLight4"));
/*     */     }
/*     */     
/* 262 */     if (isLive()) {
/* 263 */       ((SpotLightRetained)this.retained).setDirection(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 265 */       ((SpotLightRetained)this.retained).initDirection(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(Vector3f paramVector3f) {
/* 275 */     if (isLiveOrCompiled() && 
/* 276 */       !getCapability(26)) {
/* 277 */       throw new CapabilityNotSetException(J3dI18N.getString("SpotLight4"));
/*     */     }
/*     */     
/* 280 */     if (isLive()) {
/* 281 */       ((SpotLightRetained)this.retained).setDirection(paramVector3f);
/*     */     } else {
/* 283 */       ((SpotLightRetained)this.retained).initDirection(paramVector3f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getDirection(Vector3f paramVector3f) {
/* 294 */     if (isLiveOrCompiled() && 
/* 295 */       !getCapability(27)) {
/* 296 */       throw new CapabilityNotSetException(J3dI18N.getString("SpotLight6"));
/*     */     }
/* 298 */     ((SpotLightRetained)this.retained).getDirection(paramVector3f);
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
/* 316 */     SpotLight spotLight = new SpotLight();
/* 317 */     spotLight.duplicateNode(this, paramBoolean);
/* 318 */     return spotLight;
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
/*     */ 
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 346 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 348 */     SpotLightRetained spotLightRetained1 = (SpotLightRetained)paramNode.retained;
/* 349 */     SpotLightRetained spotLightRetained2 = (SpotLightRetained)this.retained;
/*     */     
/* 351 */     spotLightRetained2.initSpreadAngle(spotLightRetained1.getSpreadAngle());
/* 352 */     spotLightRetained2.initConcentration(spotLightRetained1.getConcentration());
/* 353 */     Vector3f vector3f = new Vector3f();
/* 354 */     spotLightRetained1.getDirection(vector3f);
/* 355 */     spotLightRetained2.initDirection(vector3f);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SpotLight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */