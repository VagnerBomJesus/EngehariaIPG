/*      */ package javax.media.j3d;
/*      */ 
/*      */ import javax.vecmath.Point2f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AuralAttributes
/*      */   extends NodeComponent
/*      */ {
/*      */   public static final int ALLOW_ATTRIBUTE_GAIN_READ = 0;
/*      */   public static final int ALLOW_ATTRIBUTE_GAIN_WRITE = 1;
/*      */   public static final int ALLOW_ROLLOFF_READ = 2;
/*      */   public static final int ALLOW_ROLLOFF_WRITE = 3;
/*      */   public static final int ALLOW_REFLECTION_COEFFICIENT_READ = 4;
/*      */   public static final int ALLOW_REFLECTION_COEFFICIENT_WRITE = 5;
/*      */   public static final int ALLOW_REFLECTION_DELAY_READ = 16;
/*      */   public static final int ALLOW_REFLECTION_DELAY_WRITE = 17;
/*      */   public static final int ALLOW_REVERB_COEFFICIENT_READ = 18;
/*      */   public static final int ALLOW_REVERB_COEFFICIENT_WRITE = 19;
/*      */   public static final int ALLOW_REVERB_DELAY_READ = 6;
/*      */   public static final int ALLOW_REVERB_DELAY_WRITE = 7;
/*      */   public static final int ALLOW_REVERB_ORDER_READ = 8;
/*      */   public static final int ALLOW_REVERB_ORDER_WRITE = 9;
/*      */   public static final int ALLOW_DECAY_TIME_READ = 20;
/*      */   public static final int ALLOW_DECAY_TIME_WRITE = 21;
/*      */   public static final int ALLOW_DECAY_FILTER_READ = 22;
/*      */   public static final int ALLOW_DECAY_FILTER_WRITE = 23;
/*      */   public static final int ALLOW_DIFFUSION_READ = 24;
/*      */   public static final int ALLOW_DIFFUSION_WRITE = 25;
/*      */   public static final int ALLOW_DENSITY_READ = 26;
/*      */   public static final int ALLOW_DENSITY_WRITE = 27;
/*      */   public static final int ALLOW_DISTANCE_FILTER_READ = 10;
/*      */   public static final int ALLOW_DISTANCE_FILTER_WRITE = 11;
/*      */   public static final int ALLOW_FREQUENCY_SCALE_FACTOR_READ = 12;
/*      */   public static final int ALLOW_FREQUENCY_SCALE_FACTOR_WRITE = 13;
/*      */   public static final int ALLOW_VELOCITY_SCALE_FACTOR_READ = 14;
/*      */   public static final int ALLOW_VELOCITY_SCALE_FACTOR_WRITE = 15;
/*      */   private static final int[] readCapabilities = { 
/*  477 */       0, 22, 20, 26, 24, 10, 12, 4, 16, 18, 6, 8, 2, 14 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  523 */   public AuralAttributes() { setDefaultReadCapabilities(readCapabilities); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AuralAttributes(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Point2f[] paramArrayOfPoint2f, float paramFloat5, float paramFloat6) {
/*  547 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  549 */     ((AuralAttributesRetained)this.retained).setAttributeGain(paramFloat1);
/*  550 */     ((AuralAttributesRetained)this.retained).setRolloff(paramFloat2);
/*  551 */     ((AuralAttributesRetained)this.retained).setReflectionCoefficient(paramFloat3);
/*      */     
/*  553 */     ((AuralAttributesRetained)this.retained).setReverbDelay(paramFloat4);
/*  554 */     ((AuralAttributesRetained)this.retained).setReverbOrder(paramInt);
/*  555 */     ((AuralAttributesRetained)this.retained).setDistanceFilter(paramArrayOfPoint2f);
/*      */     
/*  557 */     ((AuralAttributesRetained)this.retained).setFrequencyScaleFactor(paramFloat5);
/*      */     
/*  559 */     ((AuralAttributesRetained)this.retained).setVelocityScaleFactor(paramFloat6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AuralAttributes(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float paramFloat5, float paramFloat6) {
/*  585 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  587 */     ((AuralAttributesRetained)this.retained).setAttributeGain(paramFloat1);
/*  588 */     ((AuralAttributesRetained)this.retained).setRolloff(paramFloat2);
/*  589 */     ((AuralAttributesRetained)this.retained).setReflectionCoefficient(paramFloat3);
/*      */     
/*  591 */     ((AuralAttributesRetained)this.retained).setReverbDelay(paramFloat4);
/*  592 */     ((AuralAttributesRetained)this.retained).setReverbOrder(paramInt);
/*  593 */     ((AuralAttributesRetained)this.retained).setDistanceFilter(paramArrayOfFloat1, paramArrayOfFloat2);
/*      */     
/*  595 */     ((AuralAttributesRetained)this.retained).setFrequencyScaleFactor(paramFloat5);
/*      */     
/*  597 */     ((AuralAttributesRetained)this.retained).setVelocityScaleFactor(paramFloat6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AuralAttributes(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float paramFloat11, float paramFloat12) {
/*  636 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  638 */     ((AuralAttributesRetained)this.retained).setAttributeGain(paramFloat1);
/*  639 */     ((AuralAttributesRetained)this.retained).setRolloff(paramFloat2);
/*  640 */     ((AuralAttributesRetained)this.retained).setReflectionCoefficient(paramFloat3);
/*      */     
/*  642 */     ((AuralAttributesRetained)this.retained).setReflectionDelay(paramFloat4);
/*      */     
/*  644 */     ((AuralAttributesRetained)this.retained).setReverbCoefficient(paramFloat5);
/*      */     
/*  646 */     ((AuralAttributesRetained)this.retained).setReverbDelay(paramFloat6);
/*      */     
/*  648 */     ((AuralAttributesRetained)this.retained).setDecayTime(paramFloat7);
/*  649 */     ((AuralAttributesRetained)this.retained).setDecayFilter(paramFloat8);
/*  650 */     ((AuralAttributesRetained)this.retained).setDiffusion(paramFloat9);
/*  651 */     ((AuralAttributesRetained)this.retained).setDensity(paramFloat10);
/*  652 */     ((AuralAttributesRetained)this.retained).setDistanceFilter(paramArrayOfFloat1, paramArrayOfFloat2);
/*      */     
/*  654 */     ((AuralAttributesRetained)this.retained).setFrequencyScaleFactor(paramFloat11);
/*      */     
/*  656 */     ((AuralAttributesRetained)this.retained).setVelocityScaleFactor(paramFloat12);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createRetained() {
/*  665 */     this.retained = new AuralAttributesRetained();
/*  666 */     this.retained.setSource(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttributeGain(float paramFloat) {
/*  681 */     if (isLiveOrCompiled() && 
/*  682 */       !getCapability(1))
/*  683 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes0")); 
/*  684 */     ((AuralAttributesRetained)this.retained).setAttributeGain(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAttributeGain() {
/*  694 */     if (isLiveOrCompiled() && 
/*  695 */       !getCapability(0))
/*  696 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes1")); 
/*  697 */     return ((AuralAttributesRetained)this.retained).getAttributeGain();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRolloff(float paramFloat) {
/*  707 */     if (isLiveOrCompiled() && 
/*  708 */       !getCapability(3))
/*  709 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes2")); 
/*  710 */     ((AuralAttributesRetained)this.retained).setRolloff(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getRolloff() {
/*  720 */     if (isLiveOrCompiled() && 
/*  721 */       !getCapability(2))
/*  722 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes3")); 
/*  723 */     return ((AuralAttributesRetained)this.retained).getRolloff();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReflectionCoefficient(float paramFloat) {
/*  734 */     if (isLiveOrCompiled() && 
/*  735 */       !getCapability(5))
/*  736 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes4")); 
/*  737 */     ((AuralAttributesRetained)this.retained).setReflectionCoefficient(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getReflectionCoefficient() {
/*  747 */     if (isLiveOrCompiled() && 
/*  748 */       !getCapability(4))
/*  749 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes21")); 
/*  750 */     return ((AuralAttributesRetained)this.retained).getReflectionCoefficient();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReflectionDelay(float paramFloat) {
/*  769 */     if (isLiveOrCompiled() && 
/*  770 */       !getCapability(17))
/*  771 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes22")); 
/*  772 */     ((AuralAttributesRetained)this.retained).setReflectionDelay(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getReflectionDelay() {
/*  783 */     if (isLiveOrCompiled() && 
/*  784 */       !getCapability(16))
/*  785 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes23")); 
/*  786 */     return ((AuralAttributesRetained)this.retained).getReflectionDelay();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReverbCoefficient(float paramFloat) {
/*  804 */     if (isLiveOrCompiled() && 
/*  805 */       !getCapability(19))
/*  806 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes24")); 
/*  807 */     ((AuralAttributesRetained)this.retained).setReverbCoefficient(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getReverbCoefficient() {
/*  818 */     if (isLiveOrCompiled() && 
/*  819 */       !getCapability(18))
/*  820 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes25")); 
/*  821 */     return ((AuralAttributesRetained)this.retained).getReverbCoefficient();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReverbDelay(float paramFloat) {
/*  839 */     if (isLiveOrCompiled() && 
/*  840 */       !getCapability(7))
/*  841 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes5")); 
/*  842 */     ((AuralAttributesRetained)this.retained).setReverbDelay(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getReverbDelay() {
/*  852 */     if (isLiveOrCompiled() && 
/*  853 */       !getCapability(6))
/*  854 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes7")); 
/*  855 */     return ((AuralAttributesRetained)this.retained).getReverbDelay();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDecayTime(float paramFloat) {
/*  873 */     if (isLiveOrCompiled() && 
/*  874 */       !getCapability(21))
/*  875 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes28")); 
/*  876 */     ((AuralAttributesRetained)this.retained).setDecayTime(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDecayTime() {
/*  887 */     if (isLiveOrCompiled() && 
/*  888 */       !getCapability(20))
/*  889 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes29")); 
/*  890 */     return ((AuralAttributesRetained)this.retained).getDecayTime();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDecayFilter(float paramFloat) {
/*  910 */     if (isLiveOrCompiled() && 
/*  911 */       !getCapability(23))
/*  912 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes30")); 
/*  913 */     ((AuralAttributesRetained)this.retained).setDecayFilter(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDecayFilter() {
/*  924 */     if (isLiveOrCompiled() && 
/*  925 */       !getCapability(22))
/*  926 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes31")); 
/*  927 */     return ((AuralAttributesRetained)this.retained).getDecayFilter();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDiffusion(float paramFloat) {
/*  946 */     if (isLiveOrCompiled() && 
/*  947 */       !getCapability(25))
/*  948 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes32")); 
/*  949 */     ((AuralAttributesRetained)this.retained).setDiffusion(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDiffusion() {
/*  960 */     if (isLiveOrCompiled() && 
/*  961 */       !getCapability(24))
/*  962 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes33")); 
/*  963 */     return ((AuralAttributesRetained)this.retained).getDiffusion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDensity(float paramFloat) {
/*  982 */     if (isLiveOrCompiled() && 
/*  983 */       !getCapability(27))
/*  984 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes34")); 
/*  985 */     ((AuralAttributesRetained)this.retained).setDensity(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDensity() {
/*  996 */     if (isLiveOrCompiled() && 
/*  997 */       !getCapability(26))
/*  998 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes35")); 
/*  999 */     return ((AuralAttributesRetained)this.retained).getDensity();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReverbDelay(Bounds paramBounds) {
/* 1007 */     if (isLiveOrCompiled() && 
/* 1008 */       !getCapability(7))
/* 1009 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes5")); 
/* 1010 */     ((AuralAttributesRetained)this.retained).setReverbBounds(paramBounds);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReverbBounds(Bounds paramBounds) {
/* 1027 */     if (isLiveOrCompiled() && 
/* 1028 */       !getCapability(7))
/* 1029 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes26")); 
/* 1030 */     ((AuralAttributesRetained)this.retained).setReverbBounds(paramBounds);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bounds getReverbBounds() {
/* 1043 */     if (isLiveOrCompiled() && 
/* 1044 */       !getCapability(6))
/* 1045 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes27")); 
/* 1046 */     return ((AuralAttributesRetained)this.retained).getReverbBounds();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReverbOrder(int paramInt) {
/* 1064 */     if (isLiveOrCompiled() && 
/* 1065 */       !getCapability(9))
/* 1066 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes8")); 
/* 1067 */     ((AuralAttributesRetained)this.retained).setReverbOrder(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReverbOrder() {
/* 1077 */     if (!getCapability(8) && 
/* 1078 */       isLiveOrCompiled())
/* 1079 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes9")); 
/* 1080 */     return ((AuralAttributesRetained)this.retained).getReverbOrder();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDistanceFilter(Point2f[] paramArrayOfPoint2f) {
/* 1091 */     if (isLiveOrCompiled() && 
/* 1092 */       !getCapability(11))
/* 1093 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes10")); 
/* 1094 */     ((AuralAttributesRetained)this.retained).setDistanceFilter(paramArrayOfPoint2f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDistanceFilter(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 1112 */     if (isLiveOrCompiled() && 
/* 1113 */       !getCapability(11))
/* 1114 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes10")); 
/* 1115 */     ((AuralAttributesRetained)this.retained).setDistanceFilter(paramArrayOfFloat1, paramArrayOfFloat2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDistanceFilterLength() {
/* 1126 */     if (isLiveOrCompiled() && 
/* 1127 */       !getCapability(10))
/* 1128 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes12")); 
/* 1129 */     return ((AuralAttributesRetained)this.retained).getDistanceFilterLength();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getDistanceFilter(Point2f[] paramArrayOfPoint2f) {
/* 1142 */     if (isLiveOrCompiled() && 
/* 1143 */       !getCapability(10))
/* 1144 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes12")); 
/* 1145 */     ((AuralAttributesRetained)this.retained).getDistanceFilter(paramArrayOfPoint2f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getDistanceFilter(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 1159 */     if (isLiveOrCompiled() && 
/* 1160 */       !getCapability(10))
/* 1161 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes12")); 
/* 1162 */     ((AuralAttributesRetained)this.retained).getDistanceFilter(paramArrayOfFloat1, paramArrayOfFloat2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFrequencyScaleFactor(float paramFloat) {
/* 1180 */     if (isLiveOrCompiled() && 
/* 1181 */       !getCapability(13))
/* 1182 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes15")); 
/* 1183 */     ((AuralAttributesRetained)this.retained).setFrequencyScaleFactor(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFrequencyScaleFactor() {
/* 1194 */     if (isLiveOrCompiled() && 
/* 1195 */       !getCapability(12))
/* 1196 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes17")); 
/* 1197 */     return ((AuralAttributesRetained)this.retained).getFrequencyScaleFactor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVelocityScaleFactor(float paramFloat) {
/* 1219 */     if (isLiveOrCompiled() && 
/* 1220 */       !getCapability(15))
/* 1221 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes19")); 
/* 1222 */     ((AuralAttributesRetained)this.retained).setVelocityScaleFactor(paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getVelocityScaleFactor() {
/* 1233 */     if (isLiveOrCompiled() && 
/* 1234 */       !getCapability(14))
/* 1235 */       throw new CapabilityNotSetException(J3dI18N.getString("AuralAttributes20")); 
/* 1236 */     return ((AuralAttributesRetained)this.retained).getVelocityScaleFactor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeComponent cloneNodeComponent() {
/* 1245 */     AuralAttributes auralAttributes = new AuralAttributes();
/* 1246 */     auralAttributes.duplicateNodeComponent(this, this.forceDuplicate);
/* 1247 */     return auralAttributes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 1270 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*      */ 
/*      */     
/* 1273 */     AuralAttributesRetained auralAttributesRetained1 = (AuralAttributesRetained)paramNodeComponent.retained;
/* 1274 */     AuralAttributesRetained auralAttributesRetained2 = (AuralAttributesRetained)this.retained;
/*      */     
/* 1276 */     auralAttributesRetained2.setAttributeGain(auralAttributesRetained1.getAttributeGain());
/* 1277 */     auralAttributesRetained2.setRolloff(auralAttributesRetained1.getRolloff());
/* 1278 */     auralAttributesRetained2.setReflectionCoefficient(auralAttributesRetained1.getReflectionCoefficient());
/* 1279 */     auralAttributesRetained2.setReverbDelay(auralAttributesRetained1.getReverbDelay());
/* 1280 */     auralAttributesRetained2.setReverbOrder(auralAttributesRetained1.getReverbOrder());
/* 1281 */     auralAttributesRetained2.setReverbBounds(auralAttributesRetained1.getReverbBounds());
/* 1282 */     auralAttributesRetained2.setFrequencyScaleFactor(auralAttributesRetained1.getFrequencyScaleFactor());
/* 1283 */     auralAttributesRetained2.setVelocityScaleFactor(auralAttributesRetained1.getVelocityScaleFactor());
/* 1284 */     int i = auralAttributesRetained1.getDistanceFilterLength();
/* 1285 */     float[] arrayOfFloat1 = new float[i];
/* 1286 */     float[] arrayOfFloat2 = new float[i];
/* 1287 */     auralAttributesRetained1.getDistanceFilter(arrayOfFloat1, arrayOfFloat2);
/* 1288 */     auralAttributesRetained2.setDistanceFilter(arrayOfFloat1, arrayOfFloat2);
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\AuralAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */