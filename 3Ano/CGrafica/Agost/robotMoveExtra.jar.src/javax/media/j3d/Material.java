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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Material
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_COMPONENT_READ = 0;
/*     */   public static final int ALLOW_COMPONENT_WRITE = 1;
/*     */   public static final int AMBIENT = 0;
/*     */   public static final int EMISSIVE = 1;
/*     */   public static final int DIFFUSE = 2;
/*     */   public static final int SPECULAR = 3;
/*     */   public static final int AMBIENT_AND_DIFFUSE = 4;
/* 107 */   private static final int[] readCapabilities = { 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public Material() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Material(Color3f paramColor3f1, Color3f paramColor3f2, Color3f paramColor3f3, Color3f paramColor3f4, float paramFloat) {
/* 149 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 151 */     ((MaterialRetained)this.retained).createMaterial(paramColor3f1, paramColor3f2, paramColor3f3, paramColor3f4, paramFloat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 161 */     this.retained = new MaterialRetained();
/* 162 */     this.retained.setSource(this);
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
/*     */   public void setAmbientColor(Color3f paramColor3f) {
/* 184 */     if (isLiveOrCompiled() && 
/* 185 */       !getCapability(1))
/* 186 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0")); 
/* 187 */     if (isLive()) {
/* 188 */       ((MaterialRetained)this.retained).setAmbientColor(paramColor3f);
/*     */     } else {
/*     */       
/* 191 */       ((MaterialRetained)this.retained).initAmbientColor(paramColor3f);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAmbientColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 216 */     if (isLiveOrCompiled() && 
/* 217 */       !getCapability(1))
/* 218 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0")); 
/* 219 */     if (isLive()) {
/* 220 */       ((MaterialRetained)this.retained).setAmbientColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/*     */       
/* 223 */       ((MaterialRetained)this.retained).initAmbientColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getAmbientColor(Color3f paramColor3f) {
/* 234 */     if (isLiveOrCompiled() && 
/* 235 */       !getCapability(0)) {
/* 236 */       throw new CapabilityNotSetException(J3dI18N.getString("Material2"));
/*     */     }
/* 238 */     ((MaterialRetained)this.retained).getAmbientColor(paramColor3f);
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
/*     */   public void setEmissiveColor(Color3f paramColor3f) {
/* 259 */     if (isLiveOrCompiled() && 
/* 260 */       !getCapability(1))
/* 261 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0")); 
/* 262 */     if (isLive()) {
/* 263 */       ((MaterialRetained)this.retained).setEmissiveColor(paramColor3f);
/*     */     } else {
/* 265 */       ((MaterialRetained)this.retained).initEmissiveColor(paramColor3f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmissiveColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 292 */     if (isLiveOrCompiled() && 
/* 293 */       !getCapability(1)) {
/* 294 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0"));
/*     */     }
/* 296 */     if (isLive()) {
/* 297 */       ((MaterialRetained)this.retained).setEmissiveColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 299 */       ((MaterialRetained)this.retained).initEmissiveColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getEmissiveColor(Color3f paramColor3f) {
/* 310 */     if (isLiveOrCompiled() && 
/* 311 */       !getCapability(0)) {
/* 312 */       throw new CapabilityNotSetException(J3dI18N.getString("Material2"));
/*     */     }
/* 314 */     ((MaterialRetained)this.retained).getEmissiveColor(paramColor3f);
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
/*     */   public void setDiffuseColor(Color3f paramColor3f) {
/* 335 */     if (isLiveOrCompiled() && 
/* 336 */       !getCapability(1)) {
/* 337 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0"));
/*     */     }
/* 339 */     if (isLive()) {
/* 340 */       ((MaterialRetained)this.retained).setDiffuseColor(paramColor3f);
/*     */     } else {
/* 342 */       ((MaterialRetained)this.retained).initDiffuseColor(paramColor3f);
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
/*     */   
/*     */   public void setDiffuseColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 365 */     if (isLiveOrCompiled() && 
/* 366 */       !getCapability(1)) {
/* 367 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0"));
/*     */     }
/* 369 */     if (isLive()) {
/* 370 */       ((MaterialRetained)this.retained).setDiffuseColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 372 */       ((MaterialRetained)this.retained).initDiffuseColor(paramFloat1, paramFloat2, paramFloat3);
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
/*     */ 
/*     */   
/*     */   public void setDiffuseColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 396 */     if (isLiveOrCompiled() && 
/* 397 */       !getCapability(1)) {
/* 398 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0"));
/*     */     }
/* 400 */     if (isLive()) {
/* 401 */       ((MaterialRetained)this.retained).setDiffuseColor(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*     */     } else {
/* 403 */       ((MaterialRetained)this.retained).initDiffuseColor(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getDiffuseColor(Color3f paramColor3f) {
/* 413 */     if (isLiveOrCompiled() && 
/* 414 */       !getCapability(0)) {
/* 415 */       throw new CapabilityNotSetException(J3dI18N.getString("Material2"));
/*     */     }
/* 417 */     ((MaterialRetained)this.retained).getDiffuseColor(paramColor3f);
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
/*     */   public void setSpecularColor(Color3f paramColor3f) {
/* 438 */     if (isLiveOrCompiled() && 
/* 439 */       !getCapability(1)) {
/* 440 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0"));
/*     */     }
/* 442 */     if (isLive()) {
/* 443 */       ((MaterialRetained)this.retained).setSpecularColor(paramColor3f);
/*     */     } else {
/* 445 */       ((MaterialRetained)this.retained).initSpecularColor(paramColor3f);
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
/*     */   
/*     */   public void setSpecularColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 468 */     if (isLiveOrCompiled() && 
/* 469 */       !getCapability(1)) {
/* 470 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0"));
/*     */     }
/* 472 */     if (isLive()) {
/* 473 */       ((MaterialRetained)this.retained).setSpecularColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 475 */       ((MaterialRetained)this.retained).initSpecularColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSpecularColor(Color3f paramColor3f) {
/* 485 */     if (isLiveOrCompiled() && 
/* 486 */       !getCapability(0)) {
/* 487 */       throw new CapabilityNotSetException(J3dI18N.getString("Material2"));
/*     */     }
/* 489 */     ((MaterialRetained)this.retained).getSpecularColor(paramColor3f);
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
/*     */   public void setShininess(float paramFloat) {
/* 503 */     if (isLiveOrCompiled() && 
/* 504 */       !getCapability(1)) {
/* 505 */       throw new CapabilityNotSetException(J3dI18N.getString("Material0"));
/*     */     }
/* 507 */     if (isLive()) {
/* 508 */       ((MaterialRetained)this.retained).setShininess(paramFloat);
/*     */     } else {
/* 510 */       ((MaterialRetained)this.retained).initShininess(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShininess() {
/* 520 */     if (isLiveOrCompiled() && 
/* 521 */       !getCapability(0)) {
/* 522 */       throw new CapabilityNotSetException(J3dI18N.getString("Material2"));
/*     */     }
/* 524 */     return ((MaterialRetained)this.retained).getShininess();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLightingEnable(boolean paramBoolean) {
/* 534 */     if (isLiveOrCompiled() && 
/* 535 */       !getCapability(1))
/* 536 */       throw new CapabilityNotSetException(J3dI18N.getString("Material15")); 
/* 537 */     if (isLive()) {
/* 538 */       ((MaterialRetained)this.retained).setLightingEnable(paramBoolean);
/*     */     } else {
/* 540 */       ((MaterialRetained)this.retained).initLightingEnable(paramBoolean);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getLightingEnable() {
/* 550 */     if (isLiveOrCompiled() && 
/* 551 */       !getCapability(0))
/* 552 */       throw new CapabilityNotSetException(J3dI18N.getString("Material16")); 
/* 553 */     return ((MaterialRetained)this.retained).getLightingEnable();
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
/*     */   public void setColorTarget(int paramInt) {
/* 579 */     if (isLiveOrCompiled() && 
/* 580 */       !getCapability(1)) {
/* 581 */       throw new CapabilityNotSetException(J3dI18N.getString("Material3"));
/*     */     }
/* 583 */     if (isLive()) {
/* 584 */       ((MaterialRetained)this.retained).setColorTarget(paramInt);
/*     */     } else {
/* 586 */       ((MaterialRetained)this.retained).initColorTarget(paramInt);
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
/*     */   public int getColorTarget() {
/* 601 */     if (isLiveOrCompiled() && 
/* 602 */       !getCapability(0)) {
/* 603 */       throw new CapabilityNotSetException(J3dI18N.getString("Material4"));
/*     */     }
/* 605 */     return ((MaterialRetained)this.retained).getColorTarget();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 614 */     StringBuffer stringBuffer = new StringBuffer(getNamePrefix());
/* 615 */     stringBuffer.append("javax.media.j3d.Material: ");
/* 616 */     Color3f color3f = new Color3f();
/*     */     
/* 618 */     try { getAmbientColor(color3f);
/* 619 */       stringBuffer.append("AmbientColor=" + color3f); }
/* 620 */     catch (CapabilityNotSetException capabilityNotSetException) { stringBuffer.append("AmbientColor=N/A"); }
/*     */      
/* 622 */     try { getEmissiveColor(color3f);
/* 623 */       stringBuffer.append(" EmissiveColor=" + color3f); }
/* 624 */     catch (CapabilityNotSetException capabilityNotSetException) { stringBuffer.append(" EmissiveColor=N/A"); }
/*     */      
/* 626 */     try { getDiffuseColor(color3f);
/* 627 */       stringBuffer.append(" DiffuseColor=" + color3f); }
/* 628 */     catch (CapabilityNotSetException capabilityNotSetException) { stringBuffer.append(" DiffuseColor=N/A"); }
/*     */      
/* 630 */     try { getSpecularColor(color3f);
/* 631 */       stringBuffer.append(" SpecularColor=" + color3f); }
/* 632 */     catch (CapabilityNotSetException capabilityNotSetException) { stringBuffer.append(" SpecularColor=N/A"); }
/*     */      
/* 634 */     try { float f = getShininess();
/* 635 */       stringBuffer.append(" Shininess=" + f); }
/* 636 */     catch (CapabilityNotSetException capabilityNotSetException) { stringBuffer.append(" Shininess=N/A"); }
/*     */      
/* 638 */     try { boolean bool = getLightingEnable();
/* 639 */       stringBuffer.append(" LightingEnable=" + bool); }
/* 640 */     catch (CapabilityNotSetException capabilityNotSetException) { stringBuffer.append(" LightingEnable=N/A"); }
/*     */      
/* 642 */     try { int i = getColorTarget();
/* 643 */       stringBuffer.append(" ColorTarget=" + i); }
/* 644 */     catch (CapabilityNotSetException capabilityNotSetException) { stringBuffer.append(" ColorTarget=N/A"); }
/* 645 */      return new String(stringBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 652 */     Material material = new Material();
/* 653 */     material.duplicateNodeComponent(this);
/* 654 */     return material;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 677 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */ 
/*     */     
/* 680 */     MaterialRetained materialRetained1 = (MaterialRetained)paramNodeComponent.retained;
/*     */     
/* 682 */     MaterialRetained materialRetained2 = (MaterialRetained)this.retained;
/*     */     
/* 684 */     Color3f color3f = new Color3f();
/* 685 */     materialRetained1.getAmbientColor(color3f);
/*     */     
/* 687 */     materialRetained2.initAmbientColor(color3f);
/* 688 */     materialRetained1.getEmissiveColor(color3f);
/* 689 */     materialRetained2.initEmissiveColor(color3f);
/* 690 */     materialRetained1.getDiffuseColor(color3f);
/* 691 */     materialRetained2.initDiffuseColor(color3f);
/* 692 */     materialRetained1.getSpecularColor(color3f);
/* 693 */     materialRetained2.initSpecularColor(color3f);
/* 694 */     materialRetained2.initShininess(materialRetained1.getShininess());
/* 695 */     materialRetained2.initLightingEnable(materialRetained1.getLightingEnable());
/* 696 */     materialRetained2.initColorTarget(materialRetained1.getColorTarget());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Material.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */