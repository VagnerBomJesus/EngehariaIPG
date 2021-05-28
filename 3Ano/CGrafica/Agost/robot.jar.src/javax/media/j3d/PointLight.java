/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PointLight
/*     */   extends Light
/*     */ {
/*     */   public static final int ALLOW_POSITION_READ = 18;
/*     */   public static final int ALLOW_POSITION_WRITE = 19;
/*     */   public static final int ALLOW_ATTENUATION_READ = 20;
/*     */   public static final int ALLOW_ATTENUATION_WRITE = 21;
/*  84 */   private static final int[] readCapabilities = { 18, 20 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public PointLight() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointLight(Color3f paramColor3f, Point3f paramPoint3f1, Point3f paramPoint3f2) {
/* 111 */     super(paramColor3f);
/*     */ 
/*     */     
/* 114 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 116 */     ((PointLightRetained)this.retained).initPosition(paramPoint3f1);
/* 117 */     ((PointLightRetained)this.retained).initAttenuation(paramPoint3f2);
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
/*     */   public PointLight(boolean paramBoolean, Color3f paramColor3f, Point3f paramPoint3f1, Point3f paramPoint3f2) {
/* 131 */     super(paramBoolean, paramColor3f);
/*     */ 
/*     */     
/* 134 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 136 */     ((PointLightRetained)this.retained).initPosition(paramPoint3f1);
/* 137 */     ((PointLightRetained)this.retained).initAttenuation(paramPoint3f2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 145 */     this.retained = new PointLightRetained();
/* 146 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(Point3f paramPoint3f) {
/* 157 */     if (isLiveOrCompiled() && 
/* 158 */       !getCapability(19)) {
/* 159 */       throw new CapabilityNotSetException(J3dI18N.getString("PointLight0"));
/*     */     }
/* 161 */     if (isLive()) {
/* 162 */       ((PointLightRetained)this.retained).setPosition(paramPoint3f);
/*     */     } else {
/* 164 */       ((PointLightRetained)this.retained).initPosition(paramPoint3f);
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
/*     */   public void setPosition(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 176 */     if (isLiveOrCompiled() && 
/* 177 */       !getCapability(19)) {
/* 178 */       throw new CapabilityNotSetException(J3dI18N.getString("PointLight1"));
/*     */     }
/* 180 */     if (isLive()) {
/* 181 */       ((PointLightRetained)this.retained).setPosition(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 183 */       ((PointLightRetained)this.retained).initPosition(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPosition(Point3f paramPoint3f) {
/* 193 */     if (isLiveOrCompiled() && 
/* 194 */       !getCapability(18)) {
/* 195 */       throw new CapabilityNotSetException(J3dI18N.getString("PointLight2"));
/*     */     }
/* 197 */     ((PointLightRetained)this.retained).getPosition(paramPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttenuation(Point3f paramPoint3f) {
/* 207 */     if (isLiveOrCompiled() && 
/* 208 */       !getCapability(21)) {
/* 209 */       throw new CapabilityNotSetException(J3dI18N.getString("PointLight3"));
/*     */     }
/* 211 */     if (isLive()) {
/* 212 */       ((PointLightRetained)this.retained).setAttenuation(paramPoint3f);
/*     */     } else {
/* 214 */       ((PointLightRetained)this.retained).initAttenuation(paramPoint3f);
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
/*     */   public void setAttenuation(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 226 */     if (isLiveOrCompiled() && 
/* 227 */       !getCapability(21)) {
/* 228 */       throw new CapabilityNotSetException(J3dI18N.getString("PointLight3"));
/*     */     }
/* 230 */     if (isLive()) {
/* 231 */       ((PointLightRetained)this.retained).setAttenuation(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 233 */       ((PointLightRetained)this.retained).initAttenuation(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getAttenuation(Point3f paramPoint3f) {
/* 243 */     if (isLiveOrCompiled() && 
/* 244 */       !getCapability(20)) {
/* 245 */       throw new CapabilityNotSetException(J3dI18N.getString("PointLight5"));
/*     */     }
/* 247 */     ((PointLightRetained)this.retained).getAttenuation(paramPoint3f);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 268 */     PointLight pointLight = new PointLight();
/* 269 */     pointLight.duplicateNode(this, paramBoolean);
/* 270 */     return pointLight;
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
/* 296 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 298 */     PointLightRetained pointLightRetained1 = (PointLightRetained)paramNode.retained;
/* 299 */     PointLightRetained pointLightRetained2 = (PointLightRetained)this.retained;
/*     */     
/* 301 */     Point3f point3f = new Point3f();
/* 302 */     pointLightRetained1.getPosition(point3f);
/* 303 */     pointLightRetained2.initPosition(point3f);
/*     */     
/* 305 */     pointLightRetained1.getAttenuation(point3f);
/* 306 */     pointLightRetained2.initAttenuation(point3f);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\PointLight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */