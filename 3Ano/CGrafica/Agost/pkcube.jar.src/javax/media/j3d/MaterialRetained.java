/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ class MaterialRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*  24 */   Color3f ambientColor = new Color3f(0.2F, 0.2F, 0.2F);
/*  25 */   Color3f emissiveColor = new Color3f(0.0F, 0.0F, 0.0F);
/*  26 */   Color3f diffuseColor = new Color3f(1.0F, 1.0F, 1.0F);
/*  27 */   Color3f specularColor = new Color3f(1.0F, 1.0F, 1.0F);
/*  28 */   float shininess = 64.0F;
/*  29 */   int colorTarget = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean lightingEnable = true;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int AMBIENT_COLOR_CHANGED = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int EMISSIVE_COLOR_CHANGED = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int DIFFUSE_COLOR_CHANGED = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int SPECULAR_COLOR_CHANGED = 8;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int SHININESS_CHANGED = 16;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int ENABLE_CHANGED = 32;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int COLORTARGET_CHANGED = 64;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMaterial(Color3f paramColor3f1, Color3f paramColor3f2, Color3f paramColor3f3, Color3f paramColor3f4, float paramFloat) {
/*  68 */     this.ambientColor.set(paramColor3f1);
/*  69 */     this.emissiveColor.set(paramColor3f2);
/*  70 */     this.diffuseColor.set(paramColor3f3);
/*  71 */     this.specularColor.set(paramColor3f4);
/*  72 */     this.shininess = paramFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   final void initAmbientColor(Color3f paramColor3f) { this.ambientColor.set(paramColor3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setAmbientColor(Color3f paramColor3f) {
/*  94 */     initAmbientColor(paramColor3f);
/*  95 */     sendMessage(1, new Color3f(paramColor3f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   final void initAmbientColor(float paramFloat1, float paramFloat2, float paramFloat3) { this.ambientColor.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setAmbientColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 117 */     initAmbientColor(paramFloat1, paramFloat2, paramFloat3);
/* 118 */     sendMessage(1, new Color3f(paramFloat1, paramFloat2, paramFloat3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   final void getAmbientColor(Color3f paramColor3f) { paramColor3f.set(this.ambientColor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   final void initEmissiveColor(Color3f paramColor3f) { this.emissiveColor.set(paramColor3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setEmissiveColor(Color3f paramColor3f) {
/* 145 */     initEmissiveColor(paramColor3f);
/* 146 */     sendMessage(2, new Color3f(paramColor3f));
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
/* 157 */   final void initEmissiveColor(float paramFloat1, float paramFloat2, float paramFloat3) { this.emissiveColor.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setEmissiveColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 169 */     initEmissiveColor(paramFloat1, paramFloat2, paramFloat3);
/* 170 */     sendMessage(2, new Color3f(paramFloat1, paramFloat2, paramFloat3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   final void getEmissiveColor(Color3f paramColor3f) { paramColor3f.set(this.emissiveColor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   final void initDiffuseColor(Color3f paramColor3f) { this.diffuseColor.set(paramColor3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDiffuseColor(Color3f paramColor3f) {
/* 198 */     initDiffuseColor(paramColor3f);
/* 199 */     sendMessage(4, new Color3f(paramColor3f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   final void initDiffuseColor(float paramFloat1, float paramFloat2, float paramFloat3) { this.diffuseColor.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDiffuseColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 220 */     initDiffuseColor(paramFloat1, paramFloat2, paramFloat3);
/* 221 */     sendMessage(4, new Color3f(paramFloat1, paramFloat2, paramFloat3));
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
/* 233 */   final void initDiffuseColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { this.diffuseColor.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDiffuseColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 246 */     initDiffuseColor(paramFloat1, paramFloat2, paramFloat3);
/* 247 */     sendMessage(4, new Color3f(paramFloat1, paramFloat2, paramFloat3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   final void getDiffuseColor(Color3f paramColor3f) { paramColor3f.set(this.diffuseColor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   final void initSpecularColor(Color3f paramColor3f) { this.specularColor.set(paramColor3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setSpecularColor(Color3f paramColor3f) {
/* 274 */     initSpecularColor(paramColor3f);
/* 275 */     sendMessage(8, new Color3f(paramColor3f));
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
/* 286 */   final void initSpecularColor(float paramFloat1, float paramFloat2, float paramFloat3) { this.specularColor.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setSpecularColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 299 */     initSpecularColor(paramFloat1, paramFloat2, paramFloat3);
/* 300 */     sendMessage(8, new Color3f(paramFloat1, paramFloat2, paramFloat3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   final void getSpecularColor(Color3f paramColor3f) { paramColor3f.set(this.specularColor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initShininess(float paramFloat) {
/* 320 */     if (paramFloat < 1.0F) {
/* 321 */       this.shininess = 1.0F;
/* 322 */     } else if (paramFloat > 128.0F) {
/* 323 */       this.shininess = 128.0F;
/*     */     } else {
/* 325 */       this.shininess = paramFloat;
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
/*     */   final void setShininess(float paramFloat) {
/* 338 */     initShininess(paramFloat);
/* 339 */     sendMessage(16, new Float(this.shininess));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 347 */   final float getShininess() { return this.shininess; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   void initLightingEnable(boolean paramBoolean) { this.lightingEnable = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLightingEnable(boolean paramBoolean) {
/* 365 */     initLightingEnable(paramBoolean);
/* 366 */     sendMessage(32, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   boolean getLightingEnable() { return this.lightingEnable; }
/*     */ 
/*     */ 
/*     */   
/* 379 */   void initColorTarget(int paramInt) { this.colorTarget = paramInt; }
/*     */ 
/*     */   
/*     */   final void setColorTarget(int paramInt) {
/* 383 */     initColorTarget(paramInt);
/* 384 */     sendMessage(64, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */   
/* 388 */   final int getColorTarget() { return this.colorTarget; }
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 392 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 395 */       if (isStatic()) {
/* 396 */         this.mirror = this;
/*     */       } else {
/* 398 */         MaterialRetained materialRetained = new MaterialRetained();
/* 399 */         materialRetained.set(this);
/* 400 */         materialRetained.source = this.source;
/* 401 */         this.mirror = materialRetained;
/*     */       } 
/*     */     } else {
/* 404 */       ((MaterialRetained)this.mirror).set(this);
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
/* 415 */   void updateNative(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) { Pipeline.getPipeline().updateMaterial(paramContext, paramFloat1, paramFloat2, paramFloat3, paramFloat4, this.ambientColor.x, this.ambientColor.y, this.ambientColor.z, this.emissiveColor.x, this.emissiveColor.y, this.emissiveColor.z, this.diffuseColor.x, this.diffuseColor.y, this.diffuseColor.z, this.specularColor.x, this.specularColor.y, this.specularColor.z, this.shininess, this.colorTarget, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initMirrorObject() {
/* 429 */     MaterialRetained materialRetained = (MaterialRetained)this.mirror;
/* 430 */     materialRetained.set(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 438 */     MaterialRetained materialRetained = (MaterialRetained)this.mirror;
/* 439 */     if ((paramInt & true) != 0) {
/* 440 */       materialRetained.ambientColor = (Color3f)paramObject;
/*     */     }
/* 442 */     else if ((paramInt & 0x2) != 0) {
/* 443 */       materialRetained.emissiveColor = (Color3f)paramObject;
/*     */     }
/* 445 */     else if ((paramInt & 0x4) != 0) {
/* 446 */       materialRetained.diffuseColor = (Color3f)paramObject;
/*     */     }
/* 448 */     else if ((paramInt & 0x8) != 0) {
/* 449 */       materialRetained.specularColor = (Color3f)paramObject;
/*     */     }
/* 451 */     else if ((paramInt & 0x10) != 0) {
/* 452 */       materialRetained.shininess = ((Float)paramObject).floatValue();
/*     */     }
/* 454 */     else if ((paramInt & 0x20) != 0) {
/* 455 */       materialRetained.lightingEnable = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 457 */     else if ((paramInt & 0x40) != 0) {
/* 458 */       materialRetained.colorTarget = ((Integer)paramObject).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   boolean equivalent(MaterialRetained paramMaterialRetained) { return (paramMaterialRetained != null && this.lightingEnable == paramMaterialRetained.lightingEnable && this.diffuseColor.equals(paramMaterialRetained.diffuseColor) && this.emissiveColor.equals(paramMaterialRetained.emissiveColor) && this.specularColor.equals(paramMaterialRetained.specularColor) && this.ambientColor.equals(paramMaterialRetained.ambientColor) && this.colorTarget == paramMaterialRetained.colorTarget && this.shininess == paramMaterialRetained.shininess); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 479 */     MaterialRetained materialRetained = (MaterialRetained)super.clone();
/*     */     
/* 481 */     materialRetained.ambientColor = new Color3f(this.ambientColor);
/* 482 */     materialRetained.emissiveColor = new Color3f(this.emissiveColor);
/* 483 */     materialRetained.diffuseColor = new Color3f(this.diffuseColor);
/* 484 */     materialRetained.specularColor = new Color3f(this.specularColor);
/*     */     
/* 486 */     return materialRetained;
/*     */   }
/*     */   
/*     */   protected void set(MaterialRetained paramMaterialRetained) {
/* 490 */     set(paramMaterialRetained);
/*     */ 
/*     */     
/* 493 */     this.ambientColor.set(paramMaterialRetained.ambientColor);
/* 494 */     this.emissiveColor.set(paramMaterialRetained.emissiveColor);
/* 495 */     this.diffuseColor.set(paramMaterialRetained.diffuseColor);
/* 496 */     this.specularColor.set(paramMaterialRetained.specularColor);
/* 497 */     this.shininess = paramMaterialRetained.shininess;
/* 498 */     this.lightingEnable = paramMaterialRetained.lightingEnable;
/* 499 */     this.colorTarget = paramMaterialRetained.colorTarget;
/*     */   }
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 504 */     ArrayList arrayList1 = new ArrayList();
/* 505 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */     
/* 508 */     J3dMessage j3dMessage = new J3dMessage();
/* 509 */     j3dMessage.threads = 1024;
/* 510 */     j3dMessage.type = 13;
/* 511 */     j3dMessage.universe = null;
/* 512 */     j3dMessage.args[0] = this;
/* 513 */     j3dMessage.args[1] = new Integer(paramInt);
/* 514 */     j3dMessage.args[2] = paramObject;
/* 515 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 516 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     
/* 518 */     int i = arrayList1.size();
/* 519 */     for (byte b = 0; b < i; b++) {
/* 520 */       j3dMessage = new J3dMessage();
/* 521 */       j3dMessage.threads = 128;
/* 522 */       j3dMessage.type = 13;
/*     */       
/* 524 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 525 */       j3dMessage.args[0] = this;
/* 526 */       j3dMessage.args[1] = new Integer(paramInt);
/* 527 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 529 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 530 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 531 */       arrayList.toArray(arrayOfGeometryAtom);
/* 532 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/* 533 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 539 */     if (paramInt == 1)
/* 540 */       setFrequencyChangeMask(1, 1); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\MaterialRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */