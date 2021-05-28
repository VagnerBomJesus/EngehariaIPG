/*     */ package javax.media.j3d;
/*     */ 
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
/*     */ class SpotLightRetained
/*     */   extends PointLightRetained
/*     */ {
/*     */   static final int DIRECTION_CHANGED = 512;
/*     */   static final int ANGLE_CHANGED = 1024;
/*     */   static final int CONCENTRATION_CHANGED = 2048;
/*  29 */   Vector3f direction = new Vector3f(0.0F, 0.0F, -1.0F);
/*     */ 
/*     */   
/*  32 */   Vector3f xformDirection = new Vector3f(0.0F, 0.0F, -1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   float spreadAngle = 3.1415927F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   float concentration = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initSpreadAngle(float paramFloat) {
/*  55 */     if (paramFloat < 0.0D) {
/*  56 */       this.spreadAngle = 0.0F;
/*     */     }
/*  58 */     else if (paramFloat > 1.5707964F) {
/*  59 */       this.spreadAngle = 3.1415927F;
/*     */     } else {
/*     */       
/*  62 */       this.spreadAngle = paramFloat;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/*  68 */     doSetLive(paramSetLiveState);
/*  69 */     J3dMessage j3dMessage = initMessage(12);
/*  70 */     Object[] arrayOfObject = (Object[])j3dMessage.args[4];
/*  71 */     arrayOfObject[9] = new Float(this.spreadAngle);
/*  72 */     arrayOfObject[10] = new Float(this.concentration);
/*  73 */     arrayOfObject[11] = new Vector3f(this.direction);
/*  74 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSpreadAngle(float paramFloat) {
/*  82 */     initSpreadAngle(paramFloat);
/*  83 */     sendMessage(1024, new Float(this.spreadAngle));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   float getSpreadAngle() { return this.spreadAngle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   void initConcentration(float paramFloat) { this.concentration = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setConcentration(float paramFloat) {
/* 107 */     initConcentration(paramFloat);
/* 108 */     sendMessage(2048, new Float(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   float getConcentration() { return this.concentration; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initDirection(Vector3f paramVector3f) {
/* 124 */     this.direction.set(paramVector3f);
/*     */     
/* 126 */     if (this.staticTransform != null) {
/* 127 */       this.staticTransform.transform.transform(this.direction, this.direction);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDirection(Vector3f paramVector3f) {
/* 136 */     initDirection(paramVector3f);
/* 137 */     sendMessage(512, new Vector3f(paramVector3f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initDirection(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 148 */     this.direction.x = paramFloat1;
/* 149 */     this.direction.y = paramFloat2;
/* 150 */     this.direction.z = paramFloat3;
/* 151 */     if (this.staticTransform != null) {
/* 152 */       this.staticTransform.transform.transform(this.direction, this.direction);
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
/* 163 */   void setDirection(float paramFloat1, float paramFloat2, float paramFloat3) { setDirection(new Vector3f(paramFloat1, paramFloat2, paramFloat3)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getDirection(Vector3f paramVector3f) {
/* 173 */     paramVector3f.set(this.direction);
/* 174 */     if (this.staticTransform != null) {
/* 175 */       Transform3D transform3D = this.staticTransform.getInvTransform();
/* 176 */       transform3D.transform(paramVector3f, paramVector3f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void update(Context paramContext, int paramInt, double paramDouble) {
/* 187 */     validateAttenuationInEc(paramDouble);
/* 188 */     Pipeline.getPipeline().updateSpotLight(paramContext, paramInt, this.color.x, this.color.y, this.color.z, this.attenuation.x, this.linearAttenuationInEc, this.quadraticAttenuationInEc, this.xformPosition.x, this.xformPosition.y, this.xformPosition.z, this.spreadAngle, this.concentration, this.xformDirection.x, this.xformDirection.y, this.xformDirection.z);
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
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 208 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*     */ 
/*     */     
/* 211 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/* 212 */     LightRetained[] arrayOfLightRetained = (LightRetained[])paramArrayOfObject[3];
/* 213 */     if ((i & 0x200) != 0) {
/* 214 */       for (byte b = 0; b < j; b++) {
/* 215 */         if ((arrayOfLightRetained[b]).nodeType == 8) {
/* 216 */           SpotLightRetained spotLightRetained = (SpotLightRetained)arrayOfLightRetained[b];
/* 217 */           spotLightRetained.direction = (Vector3f)paramArrayOfObject[4];
/* 218 */           spotLightRetained.getLastLocalToVworld().transform(spotLightRetained.direction, spotLightRetained.xformDirection);
/*     */           
/* 220 */           spotLightRetained.xformDirection.normalize();
/*     */         }
/*     */       
/*     */       } 
/* 224 */     } else if ((i & 0x400) != 0) {
/* 225 */       for (byte b = 0; b < j; b++) {
/* 226 */         if ((arrayOfLightRetained[b]).nodeType == 8) {
/* 227 */           SpotLightRetained spotLightRetained = (SpotLightRetained)arrayOfLightRetained[b];
/* 228 */           spotLightRetained.spreadAngle = ((Float)paramArrayOfObject[4]).floatValue();
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 233 */     } else if ((i & 0x800) != 0) {
/*     */       
/* 235 */       for (byte b = 0; b < j; b++) {
/* 236 */         if ((arrayOfLightRetained[b]).nodeType == 8) {
/* 237 */           SpotLightRetained spotLightRetained = (SpotLightRetained)arrayOfLightRetained[b];
/* 238 */           spotLightRetained.concentration = ((Float)paramArrayOfObject[4]).floatValue();
/*     */         }
/*     */       
/*     */       } 
/* 242 */     } else if ((i & 0x20) != 0) {
/* 243 */       for (byte b = 0; b < j; b++) {
/* 244 */         if ((arrayOfLightRetained[b]).nodeType == 8) {
/* 245 */           SpotLightRetained spotLightRetained = (SpotLightRetained)arrayOfLightRetained[b];
/* 246 */           spotLightRetained.spreadAngle = ((Float)(Object[])paramArrayOfObject[4][9]).floatValue();
/* 247 */           spotLightRetained.concentration = ((Float)(Object[])paramArrayOfObject[4][10]).floatValue();
/* 248 */           spotLightRetained.direction = (Vector3f)(Object[])paramArrayOfObject[4][11];
/* 249 */           spotLightRetained.getLastLocalToVworld().transform(spotLightRetained.direction, spotLightRetained.xformDirection);
/*     */           
/* 251 */           spotLightRetained.xformDirection.normalize();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 257 */     super.updateMirrorObject(paramArrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 263 */     SpotLightRetained spotLightRetained = (SpotLightRetained)super.clone();
/* 264 */     spotLightRetained.direction = new Vector3f(this.direction);
/* 265 */     spotLightRetained.xformDirection = new Vector3f();
/* 266 */     return spotLightRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformChange() {
/* 273 */     super.updateTransformChange();
/*     */     
/* 275 */     getLastLocalToVworld().transform(this.direction, this.xformDirection);
/* 276 */     this.xformDirection.normalize();
/*     */   }
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 281 */     J3dMessage j3dMessage = new J3dMessage();
/* 282 */     j3dMessage.threads = 4224;
/* 283 */     j3dMessage.universe = this.universe;
/* 284 */     j3dMessage.type = 19;
/* 285 */     j3dMessage.args[0] = this;
/* 286 */     j3dMessage.args[1] = new Integer(paramInt);
/* 287 */     if (this.inSharedGroup) {
/* 288 */       j3dMessage.args[2] = new Integer(this.numMirrorLights);
/*     */     } else {
/* 290 */       j3dMessage.args[2] = new Integer(1);
/* 291 */     }  j3dMessage.args[3] = this.mirrorLights.clone();
/* 292 */     j3dMessage.args[4] = paramObject;
/* 293 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 298 */     super.mergeTransform(paramTransformGroupRetained);
/* 299 */     paramTransformGroupRetained.transform.transform(this.direction, this.direction);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\SpotLightRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */