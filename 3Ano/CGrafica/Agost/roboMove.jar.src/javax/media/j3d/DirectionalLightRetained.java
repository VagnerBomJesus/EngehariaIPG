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
/*     */ class DirectionalLightRetained
/*     */   extends LightRetained
/*     */ {
/*     */   static final int DIRECTION_CHANGED = 128;
/*     */   Vector3f direction;
/*     */   Vector3f xformDirection;
/*     */   
/*     */   DirectionalLightRetained() {
/*  26 */     this.direction = new Vector3f(0.0F, 0.0F, -1.0F);
/*     */ 
/*     */     
/*  29 */     this.xformDirection = new Vector3f(0.0F, 0.0F, -1.0F);
/*     */ 
/*     */     
/*  32 */     this.nodeType = 6;
/*  33 */     this.lightType = 2;
/*  34 */     this.localBounds = new BoundingBox();
/*  35 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  36 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initDirection(Vector3f paramVector3f) {
/*  44 */     this.direction.set(paramVector3f);
/*  45 */     if (this.staticTransform != null) {
/*  46 */       this.staticTransform.transform.transform(this.direction, this.direction);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDirection(Vector3f paramVector3f) {
/*  57 */     initDirection(paramVector3f);
/*  58 */     J3dMessage j3dMessage = new J3dMessage();
/*  59 */     j3dMessage.threads = 4224;
/*  60 */     j3dMessage.type = 19;
/*  61 */     j3dMessage.universe = this.universe;
/*  62 */     j3dMessage.args[0] = this;
/*  63 */     j3dMessage.args[1] = new Integer(128);
/*  64 */     if (this.inSharedGroup) {
/*  65 */       j3dMessage.args[2] = new Integer(this.numMirrorLights);
/*     */     } else {
/*  67 */       j3dMessage.args[2] = new Integer(1);
/*  68 */     }  j3dMessage.args[3] = this.mirrorLights.clone();
/*  69 */     j3dMessage.args[4] = new Vector3f(paramVector3f);
/*  70 */     VirtualUniverse.mc.processMessage(j3dMessage);
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
/*     */   void initDirection(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  82 */     this.direction.x = paramFloat1;
/*  83 */     this.direction.y = paramFloat2;
/*  84 */     this.direction.z = paramFloat3;
/*     */     
/*  86 */     if (this.staticTransform != null) {
/*  87 */       this.staticTransform.transform.transform(this.direction, this.direction);
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
/*  99 */   void setDirection(float paramFloat1, float paramFloat2, float paramFloat3) { setDirection(new Vector3f(paramFloat1, paramFloat2, paramFloat3)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getDirection(Vector3f paramVector3f) {
/* 109 */     paramVector3f.set(this.direction);
/* 110 */     if (this.staticTransform != null) {
/* 111 */       Transform3D transform3D = this.staticTransform.getInvTransform();
/* 112 */       transform3D.transform(paramVector3f, paramVector3f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 119 */     super.setLive(paramSetLiveState);
/* 120 */     J3dMessage j3dMessage = initMessage(8);
/* 121 */     Object[] arrayOfObject = (Object[])j3dMessage.args[4];
/* 122 */     arrayOfObject[7] = new Vector3f(this.direction);
/* 123 */     VirtualUniverse.mc.processMessage(j3dMessage);
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
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 136 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*     */     
/* 138 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/*     */     
/* 140 */     LightRetained[] arrayOfLightRetained = (LightRetained[])paramArrayOfObject[3];
/*     */     
/* 142 */     if ((i & 0x80) != 0)
/*     */     {
/* 144 */       for (byte b = 0; b < j; b++) {
/* 145 */         if ((arrayOfLightRetained[b]).nodeType == 6) {
/* 146 */           DirectionalLightRetained directionalLightRetained = (DirectionalLightRetained)arrayOfLightRetained[b];
/* 147 */           directionalLightRetained.direction = (Vector3f)paramArrayOfObject[4];
/* 148 */           directionalLightRetained.getLastLocalToVworld().transform(directionalLightRetained.direction, directionalLightRetained.xformDirection);
/*     */           
/* 150 */           directionalLightRetained.xformDirection.normalize();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 155 */     if ((i & 0x20) != 0) {
/* 156 */       for (byte b = 0; b < j; b++) {
/* 157 */         if ((arrayOfLightRetained[b]).nodeType == 6) {
/* 158 */           DirectionalLightRetained directionalLightRetained = (DirectionalLightRetained)arrayOfLightRetained[b];
/* 159 */           directionalLightRetained.direction = (Vector3f)(Object[])paramArrayOfObject[4][7];
/* 160 */           directionalLightRetained.getLastLocalToVworld().transform(directionalLightRetained.direction, directionalLightRetained.xformDirection);
/*     */           
/* 162 */           directionalLightRetained.xformDirection.normalize();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 167 */     super.updateMirrorObject(paramArrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 172 */   void update(Context paramContext, int paramInt, double paramDouble) { Pipeline.getPipeline().updateDirectionalLight(paramContext, paramInt, this.color.x, this.color.y, this.color.z, this.xformDirection.x, this.xformDirection.y, this.xformDirection.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 180 */     DirectionalLightRetained directionalLightRetained = (DirectionalLightRetained)super.clone();
/*     */     
/* 182 */     directionalLightRetained.direction = new Vector3f(this.direction);
/* 183 */     directionalLightRetained.xformDirection = new Vector3f(0.0F, 0.0F, -1.0F);
/* 184 */     return directionalLightRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformChange() {
/* 190 */     super.updateTransformChange();
/*     */     
/* 192 */     getLastLocalToVworld().transform(this.direction, this.xformDirection);
/* 193 */     this.xformDirection.normalize();
/*     */   }
/*     */ 
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 198 */     super.mergeTransform(paramTransformGroupRetained);
/* 199 */     paramTransformGroupRetained.transform.transform(this.direction, this.direction);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\DirectionalLightRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */