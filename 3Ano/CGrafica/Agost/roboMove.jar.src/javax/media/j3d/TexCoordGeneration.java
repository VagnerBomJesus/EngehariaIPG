/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TexCoordGeneration
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_ENABLE_READ = 0;
/*     */   public static final int ALLOW_ENABLE_WRITE = 1;
/*     */   public static final int ALLOW_FORMAT_READ = 2;
/*     */   public static final int ALLOW_MODE_READ = 3;
/*     */   public static final int ALLOW_PLANE_READ = 4;
/*     */   public static final int ALLOW_PLANE_WRITE = 5;
/*     */   public static final int OBJECT_LINEAR = 0;
/*     */   public static final int EYE_LINEAR = 1;
/*     */   public static final int SPHERE_MAP = 2;
/*     */   public static final int NORMAL_MAP = 3;
/*     */   public static final int REFLECTION_MAP = 4;
/*     */   public static final int TEXTURE_COORDINATE_2 = 0;
/*     */   public static final int TEXTURE_COORDINATE_3 = 1;
/*     */   public static final int TEXTURE_COORDINATE_4 = 2;
/* 234 */   private static final int[] readCapabilities = { 0, 2, 3, 4 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public TexCoordGeneration() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TexCoordGeneration(int paramInt1, int paramInt2) {
/* 273 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 275 */     ((TexCoordGenerationRetained)this.retained).initGenMode(paramInt1);
/* 276 */     ((TexCoordGenerationRetained)this.retained).initFormat(paramInt2);
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
/*     */   public TexCoordGeneration(int paramInt1, int paramInt2, Vector4f paramVector4f) {
/* 293 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 295 */     ((TexCoordGenerationRetained)this.retained).initGenMode(paramInt1);
/* 296 */     ((TexCoordGenerationRetained)this.retained).initFormat(paramInt2);
/* 297 */     ((TexCoordGenerationRetained)this.retained).initPlaneS(paramVector4f);
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
/*     */   public TexCoordGeneration(int paramInt1, int paramInt2, Vector4f paramVector4f1, Vector4f paramVector4f2) {
/* 316 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 318 */     ((TexCoordGenerationRetained)this.retained).initGenMode(paramInt1);
/* 319 */     ((TexCoordGenerationRetained)this.retained).initFormat(paramInt2);
/* 320 */     ((TexCoordGenerationRetained)this.retained).initPlaneS(paramVector4f1);
/* 321 */     ((TexCoordGenerationRetained)this.retained).initPlaneT(paramVector4f2);
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
/*     */   public TexCoordGeneration(int paramInt1, int paramInt2, Vector4f paramVector4f1, Vector4f paramVector4f2, Vector4f paramVector4f3) {
/* 340 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 342 */     ((TexCoordGenerationRetained)this.retained).initGenMode(paramInt1);
/* 343 */     ((TexCoordGenerationRetained)this.retained).initFormat(paramInt2);
/* 344 */     ((TexCoordGenerationRetained)this.retained).initPlaneS(paramVector4f1);
/* 345 */     ((TexCoordGenerationRetained)this.retained).initPlaneT(paramVector4f2);
/* 346 */     ((TexCoordGenerationRetained)this.retained).initPlaneR(paramVector4f3);
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
/*     */   public TexCoordGeneration(int paramInt1, int paramInt2, Vector4f paramVector4f1, Vector4f paramVector4f2, Vector4f paramVector4f3, Vector4f paramVector4f4) {
/* 369 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 371 */     ((TexCoordGenerationRetained)this.retained).initGenMode(paramInt1);
/* 372 */     ((TexCoordGenerationRetained)this.retained).initFormat(paramInt2);
/* 373 */     ((TexCoordGenerationRetained)this.retained).initPlaneS(paramVector4f1);
/* 374 */     ((TexCoordGenerationRetained)this.retained).initPlaneT(paramVector4f2);
/* 375 */     ((TexCoordGenerationRetained)this.retained).initPlaneR(paramVector4f3);
/* 376 */     ((TexCoordGenerationRetained)this.retained).initPlaneQ(paramVector4f4);
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
/*     */   public void setEnable(boolean paramBoolean) {
/* 388 */     if (isLiveOrCompiled() && 
/* 389 */       !getCapability(1))
/* 390 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration0")); 
/* 391 */     if (isLive()) {
/* 392 */       ((TexCoordGenerationRetained)this.retained).setEnable(paramBoolean);
/*     */     } else {
/* 394 */       ((TexCoordGenerationRetained)this.retained).initEnable(paramBoolean);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getEnable() {
/* 405 */     if (isLiveOrCompiled() && 
/* 406 */       !getCapability(0))
/* 407 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration1")); 
/* 408 */     return ((TexCoordGenerationRetained)this.retained).getEnable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(int paramInt) {
/* 418 */     checkForLiveOrCompiled();
/* 419 */     ((TexCoordGenerationRetained)this.retained).initFormat(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFormat() {
/* 430 */     if (isLiveOrCompiled() && 
/* 431 */       !getCapability(2))
/* 432 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration2")); 
/* 433 */     return ((TexCoordGenerationRetained)this.retained).getFormat();
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
/*     */   public void setGenMode(int paramInt) {
/* 451 */     checkForLiveOrCompiled();
/*     */     
/* 453 */     if (paramInt < 0 || paramInt > 4) {
/* 454 */       throw new IllegalArgumentException(J3dI18N.getString("TexCoordGeneration5"));
/*     */     }
/*     */     
/* 457 */     ((TexCoordGenerationRetained)this.retained).initGenMode(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGenMode() {
/* 467 */     if (isLiveOrCompiled() && 
/* 468 */       !getCapability(3))
/* 469 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration3")); 
/* 470 */     return ((TexCoordGenerationRetained)this.retained).getGenMode();
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
/*     */   public void setPlaneS(Vector4f paramVector4f) {
/* 482 */     if (isLiveOrCompiled() && 
/* 483 */       !getCapability(5)) {
/* 484 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration6"));
/*     */     }
/* 486 */     if (isLive()) {
/* 487 */       ((TexCoordGenerationRetained)this.retained).setPlaneS(paramVector4f);
/*     */     } else {
/* 489 */       ((TexCoordGenerationRetained)this.retained).initPlaneS(paramVector4f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPlaneS(Vector4f paramVector4f) {
/* 500 */     if (isLiveOrCompiled() && 
/* 501 */       !getCapability(4))
/* 502 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration4")); 
/* 503 */     ((TexCoordGenerationRetained)this.retained).getPlaneS(paramVector4f);
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
/*     */   public void setPlaneT(Vector4f paramVector4f) {
/* 515 */     if (isLiveOrCompiled() && 
/* 516 */       !getCapability(5)) {
/* 517 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration6"));
/*     */     }
/* 519 */     if (isLive()) {
/* 520 */       ((TexCoordGenerationRetained)this.retained).setPlaneT(paramVector4f);
/*     */     } else {
/* 522 */       ((TexCoordGenerationRetained)this.retained).initPlaneT(paramVector4f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPlaneT(Vector4f paramVector4f) {
/* 533 */     if (isLiveOrCompiled() && 
/* 534 */       !getCapability(4))
/* 535 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration4")); 
/* 536 */     ((TexCoordGenerationRetained)this.retained).getPlaneT(paramVector4f);
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
/*     */   public void setPlaneR(Vector4f paramVector4f) {
/* 548 */     if (isLiveOrCompiled() && 
/* 549 */       !getCapability(5)) {
/* 550 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration6"));
/*     */     }
/* 552 */     if (isLive()) {
/* 553 */       ((TexCoordGenerationRetained)this.retained).setPlaneR(paramVector4f);
/*     */     } else {
/* 555 */       ((TexCoordGenerationRetained)this.retained).initPlaneR(paramVector4f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPlaneR(Vector4f paramVector4f) {
/* 566 */     if (isLiveOrCompiled() && 
/* 567 */       !getCapability(4))
/* 568 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration4")); 
/* 569 */     ((TexCoordGenerationRetained)this.retained).getPlaneR(paramVector4f);
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
/*     */   public void setPlaneQ(Vector4f paramVector4f) {
/* 583 */     if (isLiveOrCompiled() && 
/* 584 */       !getCapability(5)) {
/* 585 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration6"));
/*     */     }
/* 587 */     if (isLive()) {
/* 588 */       ((TexCoordGenerationRetained)this.retained).setPlaneQ(paramVector4f);
/*     */     } else {
/* 590 */       ((TexCoordGenerationRetained)this.retained).initPlaneQ(paramVector4f);
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
/*     */   public void getPlaneQ(Vector4f paramVector4f) {
/* 603 */     if (isLiveOrCompiled() && 
/* 604 */       !getCapability(4))
/* 605 */       throw new CapabilityNotSetException(J3dI18N.getString("TexCoordGeneration4")); 
/* 606 */     ((TexCoordGenerationRetained)this.retained).getPlaneQ(paramVector4f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 614 */     this.retained = new TexCoordGenerationRetained();
/* 615 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 622 */     TexCoordGeneration texCoordGeneration = new TexCoordGeneration();
/* 623 */     texCoordGeneration.duplicateNodeComponent(this);
/* 624 */     return texCoordGeneration;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 649 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 651 */     TexCoordGenerationRetained texCoordGenerationRetained1 = (TexCoordGenerationRetained)paramNodeComponent.retained;
/*     */     
/* 653 */     TexCoordGenerationRetained texCoordGenerationRetained2 = (TexCoordGenerationRetained)this.retained;
/*     */     
/* 655 */     Vector4f vector4f = new Vector4f();
/*     */     
/* 657 */     texCoordGenerationRetained2.initGenMode(texCoordGenerationRetained1.getGenMode());
/* 658 */     texCoordGenerationRetained1.getPlaneS(vector4f);
/* 659 */     texCoordGenerationRetained2.initPlaneS(vector4f);
/* 660 */     texCoordGenerationRetained1.getPlaneT(vector4f);
/* 661 */     texCoordGenerationRetained2.initPlaneT(vector4f);
/* 662 */     texCoordGenerationRetained1.getPlaneR(vector4f);
/* 663 */     texCoordGenerationRetained2.initPlaneR(vector4f);
/* 664 */     texCoordGenerationRetained1.getPlaneQ(vector4f);
/* 665 */     texCoordGenerationRetained2.initPlaneQ(vector4f);
/* 666 */     texCoordGenerationRetained2.initFormat(texCoordGenerationRetained1.getFormat());
/* 667 */     texCoordGenerationRetained2.initEnable(texCoordGenerationRetained1.getEnable());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TexCoordGeneration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */