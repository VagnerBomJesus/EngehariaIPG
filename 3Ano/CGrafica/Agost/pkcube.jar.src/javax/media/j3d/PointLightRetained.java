/*     */ package javax.media.j3d;
/*     */ 
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
/*     */ class PointLightRetained
/*     */   extends LightRetained
/*     */ {
/*     */   static final int POSITION_CHANGED = 128;
/*     */   static final int ATTENUATION_CHANGED = 256;
/*     */   static final int LAST_POINTLIGHT_DEFINED_BIT = 256;
/*     */   Point3f attenuation;
/*     */   Point3f position;
/*     */   Point3f xformPosition;
/*     */   double localToVworldScale;
/*     */   float linearAttenuationInEc;
/*     */   float quadraticAttenuationInEc;
/*     */   
/*     */   PointLightRetained() {
/*  29 */     this.attenuation = new Point3f(1.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*  32 */     this.position = new Point3f();
/*     */ 
/*     */     
/*  35 */     this.xformPosition = new Point3f();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.nodeType = 7;
/*  48 */     this.lightType = 3;
/*  49 */     this.localBounds = new BoundingBox();
/*  50 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  51 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initPosition(Point3f paramPoint3f) {
/*  59 */     this.position.set(paramPoint3f);
/*     */     
/*  61 */     if (this.staticTransform != null) {
/*  62 */       this.staticTransform.transform.transform(this.position, this.position);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPosition(Point3f paramPoint3f) {
/*  71 */     initPosition(paramPoint3f);
/*  72 */     sendMessage(128, new Point3f(paramPoint3f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initPosition(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  83 */     this.position.x = paramFloat1;
/*  84 */     this.position.y = paramFloat2;
/*  85 */     this.position.z = paramFloat3;
/*     */     
/*  87 */     if (this.staticTransform != null) {
/*  88 */       this.staticTransform.transform.transform(this.position, this.position);
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
/* 100 */   void setPosition(float paramFloat1, float paramFloat2, float paramFloat3) { setPosition(new Point3f(paramFloat1, paramFloat2, paramFloat3)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getPosition(Point3f paramPoint3f) {
/* 110 */     paramPoint3f.set(this.position);
/*     */     
/* 112 */     if (this.staticTransform != null) {
/* 113 */       Transform3D transform3D = this.staticTransform.getInvTransform();
/* 114 */       transform3D.transform(paramPoint3f, paramPoint3f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   void initAttenuation(Point3f paramPoint3f) { this.attenuation.set(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAttenuation(Point3f paramPoint3f) {
/* 132 */     initAttenuation(paramPoint3f);
/* 133 */     sendMessage(256, new Point3f(paramPoint3f));
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
/*     */   void initAttenuation(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 145 */     this.attenuation.x = paramFloat1;
/* 146 */     this.attenuation.y = paramFloat2;
/* 147 */     this.attenuation.z = paramFloat3;
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
/* 159 */   void setAttenuation(float paramFloat1, float paramFloat2, float paramFloat3) { setAttenuation(new Point3f(paramFloat1, paramFloat2, paramFloat3)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   void getAttenuation(Point3f paramPoint3f) { paramPoint3f.set(this.attenuation); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void update(Context paramContext, int paramInt, double paramDouble) {
/* 177 */     validateAttenuationInEc(paramDouble);
/* 178 */     Pipeline.getPipeline().updatePointLight(paramContext, paramInt, this.color.x, this.color.y, this.color.z, this.attenuation.x, this.linearAttenuationInEc, this.quadraticAttenuationInEc, this.xformPosition.x, this.xformPosition.y, this.xformPosition.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 187 */     super.setLive(paramSetLiveState);
/* 188 */     J3dMessage j3dMessage = super.initMessage(9);
/* 189 */     Object[] arrayOfObject = (Object[])j3dMessage.args[4];
/* 190 */     arrayOfObject[7] = new Point3f(this.position);
/* 191 */     arrayOfObject[8] = new Point3f(this.attenuation);
/*     */     
/* 193 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   void doSetLive(SetLiveState paramSetLiveState) { super.setLive(paramSetLiveState); }
/*     */ 
/*     */   
/*     */   J3dMessage initMessage(int paramInt) {
/* 204 */     J3dMessage j3dMessage = super.initMessage(paramInt);
/* 205 */     Object[] arrayOfObject = (Object[])j3dMessage.args[4];
/* 206 */     arrayOfObject[7] = new Point3f(this.position);
/* 207 */     arrayOfObject[8] = new Point3f(this.attenuation);
/* 208 */     return j3dMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 216 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*     */ 
/*     */     
/* 219 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/* 220 */     LightRetained[] arrayOfLightRetained = (LightRetained[])paramArrayOfObject[3];
/*     */ 
/*     */     
/* 223 */     if ((i & 0x80) != 0) {
/* 224 */       for (byte b = 0; b < j; b++) {
/* 225 */         if (arrayOfLightRetained[b] instanceof PointLightRetained) {
/* 226 */           PointLightRetained pointLightRetained = (PointLightRetained)arrayOfLightRetained[b];
/* 227 */           Transform3D transform3D = pointLightRetained.getLastLocalToVworld();
/* 228 */           pointLightRetained.position = (Point3f)paramArrayOfObject[4];
/* 229 */           transform3D.transform(pointLightRetained.position, pointLightRetained.xformPosition);
/*     */           
/* 231 */           pointLightRetained.localToVworldScale = transform3D.getDistanceScale();
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 236 */     } else if ((i & 0x100) != 0) {
/* 237 */       for (byte b = 0; b < j; b++) {
/* 238 */         if (arrayOfLightRetained[b] instanceof PointLightRetained) {
/* 239 */           PointLightRetained pointLightRetained = (PointLightRetained)arrayOfLightRetained[b];
/* 240 */           pointLightRetained.attenuation.set((Point3f)paramArrayOfObject[4]);
/*     */         }
/*     */       
/*     */       } 
/* 244 */     } else if ((i & 0x20) != 0) {
/* 245 */       for (byte b = 0; b < j; b++) {
/* 246 */         if (arrayOfLightRetained[b] instanceof PointLightRetained) {
/* 247 */           PointLightRetained pointLightRetained = (PointLightRetained)this.mirrorLights[b];
/* 248 */           pointLightRetained.position = (Point3f)(Object[])paramArrayOfObject[4][7];
/* 249 */           pointLightRetained.attenuation.set((Point3f)(Object[])paramArrayOfObject[4][8]);
/* 250 */           Transform3D transform3D = pointLightRetained.getLastLocalToVworld();
/* 251 */           transform3D.transform(pointLightRetained.position, pointLightRetained.xformPosition);
/*     */           
/* 253 */           pointLightRetained.localToVworldScale = transform3D.getDistanceScale();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 260 */     super.updateMirrorObject(paramArrayOfObject);
/*     */   }
/*     */   
/*     */   void validateAttenuationInEc(double paramDouble) {
/* 264 */     double d = this.localToVworldScale * paramDouble;
/*     */     
/* 266 */     this.linearAttenuationInEc = (float)(this.attenuation.y / d);
/* 267 */     this.quadraticAttenuationInEc = (float)(this.attenuation.z / d * d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 275 */     PointLightRetained pointLightRetained = (PointLightRetained)super.clone();
/*     */ 
/*     */     
/* 278 */     pointLightRetained.attenuation = new Point3f(this.attenuation);
/* 279 */     pointLightRetained.position = new Point3f(this.position);
/* 280 */     pointLightRetained.xformPosition = new Point3f();
/* 281 */     return pointLightRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformChange() {
/* 287 */     super.updateTransformChange();
/*     */     
/* 289 */     Transform3D transform3D = getLastLocalToVworld();
/*     */     
/* 291 */     transform3D.transform(this.position, this.xformPosition);
/* 292 */     this.localToVworldScale = transform3D.getDistanceScale();
/*     */     
/* 294 */     validateAttenuationInEc(0.0861328125D);
/*     */   }
/*     */ 
/*     */   
/*     */   void sendMessage(int paramInt, Object paramObject) {
/* 299 */     J3dMessage j3dMessage = new J3dMessage();
/* 300 */     j3dMessage.threads = 4224;
/* 301 */     j3dMessage.universe = this.universe;
/* 302 */     j3dMessage.type = 19;
/* 303 */     j3dMessage.args[0] = this;
/* 304 */     j3dMessage.args[1] = new Integer(paramInt);
/* 305 */     if (this.inSharedGroup) {
/* 306 */       j3dMessage.args[2] = new Integer(this.numMirrorLights);
/*     */     } else {
/* 308 */       j3dMessage.args[2] = new Integer(1);
/* 309 */     }  j3dMessage.args[3] = this.mirrorLights.clone();
/* 310 */     j3dMessage.args[4] = paramObject;
/* 311 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 315 */     super.mergeTransform(paramTransformGroupRetained);
/* 316 */     paramTransformGroupRetained.transform.transform(this.position, this.position);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\PointLightRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */