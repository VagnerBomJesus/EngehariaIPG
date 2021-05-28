/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point2f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConeSound
/*     */   extends PointSound
/*     */ {
/*     */   public static final int ALLOW_DIRECTION_READ = 36;
/*     */   public static final int ALLOW_DIRECTION_WRITE = 37;
/*     */   public static final int ALLOW_ANGULAR_ATTENUATION_READ = 38;
/*     */   public static final int ALLOW_ANGULAR_ATTENUATION_WRITE = 39;
/* 151 */   private static final int[] readCapabilities = { 36, 38 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public ConeSound() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat, Point3f paramPoint3f, Vector3f paramVector3f) {
/* 187 */     super(paramMediaContainer, paramFloat, paramPoint3f);
/*     */ 
/*     */     
/* 190 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 192 */     ((ConeSoundRetained)this.retained).setDirection(paramVector3f);
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
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7) {
/* 215 */     super(paramMediaContainer, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*     */ 
/*     */     
/* 218 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 220 */     ((ConeSoundRetained)this.retained).setDirection(paramFloat5, paramFloat6, paramFloat7);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, Point3f paramPoint3f, Point2f[] paramArrayOfPoint2f1, Point2f[] paramArrayOfPoint2f2, Vector3f paramVector3f) {
/* 265 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2, paramPoint3f, paramArrayOfPoint2f1);
/*     */ 
/*     */ 
/*     */     
/* 269 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 271 */     ((ConeSoundRetained)this.retained).setBackDistanceGain(paramArrayOfPoint2f2);
/*     */     
/* 273 */     ((ConeSoundRetained)this.retained).setDirection(paramVector3f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4, float paramFloat6, float paramFloat7, float paramFloat8) {
/* 321 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramArrayOfFloat1, paramArrayOfFloat2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 328 */     ((ConeSoundRetained)this.retained).setDirection(paramFloat6, paramFloat7, paramFloat8);
/* 329 */     ((ConeSoundRetained)this.retained).setBackDistanceGain(paramArrayOfFloat3, paramArrayOfFloat4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, Point3f paramPoint3f, Point2f[] paramArrayOfPoint2f, Vector3f paramVector3f, Point3f[] paramArrayOfPoint3f) {
/* 371 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2, paramPoint3f, paramArrayOfPoint2f);
/*     */ 
/*     */ 
/*     */     
/* 375 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 377 */     ((ConeSoundRetained)this.retained).setDirection(paramVector3f);
/* 378 */     ((ConeSoundRetained)this.retained).setAngularAttenuation(paramArrayOfPoint3f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float paramFloat6, float paramFloat7, float paramFloat8, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4, float[] paramArrayOfFloat5) {
/* 430 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramArrayOfFloat1, paramArrayOfFloat2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 435 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 437 */     ((ConeSoundRetained)this.retained).setDirection(paramFloat6, paramFloat7, paramFloat8);
/* 438 */     ((ConeSoundRetained)this.retained).setAngularAttenuation(paramArrayOfFloat3, paramArrayOfFloat4, paramArrayOfFloat5);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, Point3f paramPoint3f, Point2f[] paramArrayOfPoint2f1, Point2f[] paramArrayOfPoint2f2, Vector3f paramVector3f, Point3f[] paramArrayOfPoint3f) {
/* 477 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2, paramPoint3f, paramArrayOfPoint2f1);
/*     */ 
/*     */ 
/*     */     
/* 481 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 483 */     ((ConeSoundRetained)this.retained).setBackDistanceGain(paramArrayOfPoint2f2);
/*     */     
/* 485 */     ((ConeSoundRetained)this.retained).setDirection(paramVector3f);
/* 486 */     ((ConeSoundRetained)this.retained).setAngularAttenuation(paramArrayOfPoint3f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConeSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4, float paramFloat6, float paramFloat7, float paramFloat8, float[] paramArrayOfFloat5, float[] paramArrayOfFloat6, float[] paramArrayOfFloat7) {
/* 533 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramArrayOfFloat1, paramArrayOfFloat2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 538 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 540 */     ((ConeSoundRetained)this.retained).setBackDistanceGain(paramArrayOfFloat3, paramArrayOfFloat4);
/*     */     
/* 542 */     ((ConeSoundRetained)this.retained).setDirection(paramFloat6, paramFloat7, paramFloat8);
/* 543 */     ((ConeSoundRetained)this.retained).setAngularAttenuation(paramArrayOfFloat5, paramArrayOfFloat6, paramArrayOfFloat7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 552 */     this.retained = new ConeSoundRetained();
/* 553 */     this.retained.setSource(this);
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
/*     */   public void setDistanceGain(Point2f[] paramArrayOfPoint2f1, Point2f[] paramArrayOfPoint2f2) {
/* 570 */     if (isLiveOrCompiled() && 
/* 571 */       !getCapability(35)) {
/* 572 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound0"));
/*     */     }
/* 574 */     ((ConeSoundRetained)this.retained).setDistanceGain(paramArrayOfPoint2f1, paramArrayOfPoint2f2);
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
/*     */   public void setDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4) {
/* 589 */     if (isLiveOrCompiled() && 
/* 590 */       !getCapability(35)) {
/* 591 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound0"));
/*     */     }
/* 593 */     ((ConeSoundRetained)this.retained).setDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3, paramArrayOfFloat4);
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
/*     */   public void setBackDistanceGain(Point2f[] paramArrayOfPoint2f) {
/* 606 */     if (isLiveOrCompiled() && 
/* 607 */       !getCapability(35)) {
/* 608 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound0"));
/*     */     }
/* 610 */     ((ConeSoundRetained)this.retained).setBackDistanceGain(paramArrayOfPoint2f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 621 */     if (isLiveOrCompiled() && 
/* 622 */       !getCapability(35)) {
/* 623 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound0"));
/*     */     }
/* 625 */     ((ConeSoundRetained)this.retained).setBackDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2);
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
/*     */   public void getDistanceGain(Point2f[] paramArrayOfPoint2f1, Point2f[] paramArrayOfPoint2f2) {
/* 645 */     if (isLiveOrCompiled() && 
/* 646 */       !getCapability(34)) {
/* 647 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound2"));
/*     */     }
/* 649 */     ((ConeSoundRetained)this.retained).getDistanceGain(paramArrayOfPoint2f1, paramArrayOfPoint2f2);
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
/*     */   public void getDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4) {
/* 669 */     if (isLiveOrCompiled() && 
/* 670 */       !getCapability(34)) {
/* 671 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound10"));
/*     */     }
/* 673 */     ((ConeSoundRetained)this.retained).getDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3, paramArrayOfFloat4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(Vector3f paramVector3f) {
/* 684 */     if (isLiveOrCompiled() && 
/* 685 */       !getCapability(37)) {
/* 686 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound3"));
/*     */     }
/* 688 */     ((ConeSoundRetained)this.retained).setDirection(paramVector3f);
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
/* 700 */     if (isLiveOrCompiled() && 
/* 701 */       !getCapability(37)) {
/* 702 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound3"));
/*     */     }
/* 704 */     ((ConeSoundRetained)this.retained).setDirection(paramFloat1, paramFloat2, paramFloat3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getDirection(Vector3f paramVector3f) {
/* 715 */     if (isLiveOrCompiled() && 
/* 716 */       !getCapability(36)) {
/* 717 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound5"));
/*     */     }
/* 719 */     ((ConeSoundRetained)this.retained).getDirection(paramVector3f);
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
/*     */   public void setAngularAttenuation(Point2f[] paramArrayOfPoint2f) {
/* 732 */     if (isLiveOrCompiled() && 
/* 733 */       !getCapability(39)) {
/* 734 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound6"));
/*     */     }
/* 736 */     ((ConeSoundRetained)this.retained).setAngularAttenuation(paramArrayOfPoint2f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAngularAttenuation(Point3f[] paramArrayOfPoint3f) {
/* 747 */     if (isLiveOrCompiled() && 
/* 748 */       !getCapability(39)) {
/* 749 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound6"));
/*     */     }
/* 751 */     ((ConeSoundRetained)this.retained).setAngularAttenuation(paramArrayOfPoint3f);
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
/*     */   public void setAngularAttenuation(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3) {
/* 772 */     if (isLiveOrCompiled() && 
/* 773 */       !getCapability(39)) {
/* 774 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound6"));
/*     */     }
/* 776 */     ((ConeSoundRetained)this.retained).setAngularAttenuation(paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAngularAttenuationLength() {
/* 787 */     if (isLiveOrCompiled() && 
/* 788 */       !getCapability(38)) {
/* 789 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound9"));
/*     */     }
/* 791 */     return ((ConeSoundRetained)this.retained).getAngularAttenuationLength();
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
/*     */   public void getAngularAttenuation(Point3f[] paramArrayOfPoint3f) {
/* 808 */     if (isLiveOrCompiled() && 
/* 809 */       !getCapability(38)) {
/* 810 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound9"));
/*     */     }
/* 812 */     ((ConeSoundRetained)this.retained).getAngularAttenuation(paramArrayOfPoint3f);
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
/*     */   public void getAngularAttenuation(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3) {
/* 827 */     if (isLiveOrCompiled() && 
/* 828 */       !getCapability(39)) {
/* 829 */       throw new CapabilityNotSetException(J3dI18N.getString("ConeSound9"));
/*     */     }
/* 831 */     ((ConeSoundRetained)this.retained).getAngularAttenuation(paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 850 */     ConeSound coneSound = new ConeSound();
/* 851 */     coneSound.duplicateNode(this, paramBoolean);
/* 852 */     return coneSound;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 888 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { checkDuplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 914 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 916 */     ConeSoundRetained coneSoundRetained1 = (ConeSoundRetained)paramNode.retained;
/* 917 */     ConeSoundRetained coneSoundRetained2 = (ConeSoundRetained)this.retained;
/*     */ 
/*     */ 
/*     */     
/* 921 */     int i = coneSoundRetained1.getDistanceGainLength();
/* 922 */     float[] arrayOfFloat1 = new float[i];
/* 923 */     float[] arrayOfFloat2 = new float[i];
/* 924 */     coneSoundRetained1.getDistanceGain(null, null, arrayOfFloat1, arrayOfFloat2);
/* 925 */     coneSoundRetained2.setBackDistanceGain(arrayOfFloat1, arrayOfFloat2);
/*     */     
/* 927 */     Vector3f vector3f = new Vector3f();
/* 928 */     coneSoundRetained1.getDirection(vector3f);
/* 929 */     coneSoundRetained2.setDirection(vector3f);
/*     */     
/* 931 */     i = coneSoundRetained1.getAngularAttenuationLength();
/* 932 */     arrayOfFloat1 = arrayOfFloat2 = null;
/* 933 */     float[] arrayOfFloat3 = new float[i];
/* 934 */     float[] arrayOfFloat4 = new float[i];
/* 935 */     float[] arrayOfFloat5 = new float[i];
/*     */     
/* 937 */     coneSoundRetained1.getAngularAttenuation(arrayOfFloat3, arrayOfFloat4, arrayOfFloat5);
/*     */ 
/*     */     
/* 940 */     coneSoundRetained2.setAngularAttenuation(arrayOfFloat3, arrayOfFloat4, arrayOfFloat5);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\ConeSound.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */