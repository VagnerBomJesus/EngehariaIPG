/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransparencyAttributes
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_MODE_READ = 0;
/*     */   public static final int ALLOW_MODE_WRITE = 1;
/*     */   public static final int ALLOW_VALUE_READ = 2;
/*     */   public static final int ALLOW_VALUE_WRITE = 3;
/*     */   public static final int ALLOW_BLEND_FUNCTION_READ = 4;
/*     */   public static final int ALLOW_BLEND_FUNCTION_WRITE = 5;
/*     */   public static final int FASTEST = 0;
/*     */   public static final int NICEST = 1;
/*     */   public static final int BLENDED = 2;
/*     */   public static final int SCREEN_DOOR = 3;
/*     */   public static final int NONE = 4;
/*     */   public static final int BLEND_ZERO = 0;
/*     */   public static final int BLEND_ONE = 1;
/*     */   public static final int BLEND_SRC_ALPHA = 2;
/*     */   public static final int BLEND_ONE_MINUS_SRC_ALPHA = 3;
/*     */   public static final int BLEND_DST_COLOR = 4;
/*     */   public static final int BLEND_ONE_MINUS_DST_COLOR = 5;
/*     */   public static final int BLEND_SRC_COLOR = 6;
/*     */   public static final int BLEND_ONE_MINUS_SRC_COLOR = 7;
/*     */   static final int BLEND_CONSTANT_COLOR = 8;
/*     */   static final int MAX_BLEND_FUNC_TABLE_SIZE = 9;
/* 268 */   private static final int[] readCapabilities = { 4, 0, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public TransparencyAttributes() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 301 */   public TransparencyAttributes(int paramInt, float paramFloat) { this(paramInt, paramFloat, 2, 3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransparencyAttributes(int paramInt1, float paramFloat, int paramInt2, int paramInt3) {
/* 331 */     if (paramInt1 < 0 || paramInt1 > 4) {
/* 332 */       throw new IllegalArgumentException(J3dI18N.getString("TransparencyAttributes6"));
/*     */     }
/*     */     
/* 335 */     switch (paramInt2) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */         break;
/*     */       default:
/* 344 */         throw new IllegalArgumentException(J3dI18N.getString("TransparencyAttributes7"));
/*     */     } 
/*     */     
/* 347 */     switch (paramInt3) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 6:
/*     */       case 7:
/*     */         break;
/*     */       default:
/* 356 */         throw new IllegalArgumentException(J3dI18N.getString("TransparencyAttributes8"));
/*     */     } 
/*     */ 
/*     */     
/* 360 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 362 */     ((TransparencyAttributesRetained)this.retained).initTransparencyMode(paramInt1);
/* 363 */     ((TransparencyAttributesRetained)this.retained).initTransparency(paramFloat);
/* 364 */     ((TransparencyAttributesRetained)this.retained).initSrcBlendFunction(paramInt2);
/* 365 */     ((TransparencyAttributesRetained)this.retained).initDstBlendFunction(paramInt3);
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
/*     */   public void setTransparencyMode(int paramInt) {
/* 382 */     if (isLiveOrCompiled() && 
/* 383 */       !getCapability(1)) {
/* 384 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes0"));
/*     */     }
/* 386 */     if (paramInt < 0 || paramInt > 4) {
/* 387 */       throw new IllegalArgumentException(J3dI18N.getString("TransparencyAttributes6"));
/*     */     }
/*     */     
/* 390 */     if (isLive()) {
/* 391 */       ((TransparencyAttributesRetained)this.retained).setTransparencyMode(paramInt);
/*     */     } else {
/* 393 */       ((TransparencyAttributesRetained)this.retained).initTransparencyMode(paramInt);
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
/*     */   public int getTransparencyMode() {
/* 406 */     if (isLiveOrCompiled() && 
/* 407 */       !getCapability(0)) {
/* 408 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes1"));
/*     */     }
/* 410 */     return ((TransparencyAttributesRetained)this.retained).getTransparencyMode();
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
/*     */   public void setTransparency(float paramFloat) {
/* 422 */     if (isLiveOrCompiled() && 
/* 423 */       !getCapability(3)) {
/* 424 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes2"));
/*     */     }
/*     */     
/* 427 */     if (isLive()) {
/* 428 */       ((TransparencyAttributesRetained)this.retained).setTransparency(paramFloat);
/*     */     } else {
/* 430 */       ((TransparencyAttributesRetained)this.retained).initTransparency(paramFloat);
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
/*     */   public float getTransparency() {
/* 442 */     if (isLiveOrCompiled() && 
/* 443 */       !getCapability(2)) {
/* 444 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes3"));
/*     */     }
/* 446 */     return ((TransparencyAttributesRetained)this.retained).getTransparency();
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
/*     */   public void setSrcBlendFunction(int paramInt) {
/* 470 */     if (isLiveOrCompiled() && 
/* 471 */       !getCapability(5)) {
/* 472 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes4"));
/*     */     }
/*     */     
/* 475 */     switch (paramInt) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */         break;
/*     */       default:
/* 484 */         throw new IllegalArgumentException(J3dI18N.getString("TransparencyAttributes7"));
/*     */     } 
/*     */     
/* 487 */     if (isLive()) {
/* 488 */       ((TransparencyAttributesRetained)this.retained).setSrcBlendFunction(paramInt);
/*     */     } else {
/* 490 */       ((TransparencyAttributesRetained)this.retained).initSrcBlendFunction(paramInt);
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
/*     */   public int getSrcBlendFunction() {
/* 505 */     if (isLiveOrCompiled() && 
/* 506 */       !getCapability(4))
/* 507 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes5")); 
/* 508 */     return ((TransparencyAttributesRetained)this.retained).getSrcBlendFunction();
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
/*     */   public void setDstBlendFunction(int paramInt) {
/* 532 */     if (isLiveOrCompiled() && 
/* 533 */       !getCapability(5)) {
/* 534 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes4"));
/*     */     }
/* 536 */     switch (paramInt) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 6:
/*     */       case 7:
/*     */         break;
/*     */       default:
/* 545 */         throw new IllegalArgumentException(J3dI18N.getString("TransparencyAttributes8"));
/*     */     } 
/*     */     
/* 548 */     if (isLive()) {
/* 549 */       ((TransparencyAttributesRetained)this.retained).setDstBlendFunction(paramInt);
/*     */     } else {
/* 551 */       ((TransparencyAttributesRetained)this.retained).initDstBlendFunction(paramInt);
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
/*     */   public int getDstBlendFunction() {
/* 566 */     if (isLiveOrCompiled() && 
/* 567 */       !getCapability(4)) {
/* 568 */       throw new CapabilityNotSetException(J3dI18N.getString("TransparencyAttributes5"));
/*     */     }
/* 570 */     return ((TransparencyAttributesRetained)this.retained).getDstBlendFunction();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 578 */     this.retained = new TransparencyAttributesRetained();
/* 579 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 587 */     TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
/* 588 */     transparencyAttributes.duplicateNodeComponent(this);
/* 589 */     return transparencyAttributes;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 613 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 615 */     TransparencyAttributesRetained transparencyAttributesRetained1 = (TransparencyAttributesRetained)paramNodeComponent.retained;
/*     */     
/* 617 */     TransparencyAttributesRetained transparencyAttributesRetained2 = (TransparencyAttributesRetained)this.retained;
/*     */ 
/*     */     
/* 620 */     transparencyAttributesRetained2.initTransparencyMode(transparencyAttributesRetained1.getTransparencyMode());
/* 621 */     transparencyAttributesRetained2.initTransparency(transparencyAttributesRetained1.getTransparency());
/* 622 */     transparencyAttributesRetained2.initSrcBlendFunction(transparencyAttributesRetained1.getSrcBlendFunction());
/* 623 */     transparencyAttributesRetained2.initDstBlendFunction(transparencyAttributesRetained1.getDstBlendFunction());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TransparencyAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */