/*      */ package javax.media.j3d;
/*      */ 
/*      */ import javax.vecmath.Color4f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TextureAttributes
/*      */   extends NodeComponent
/*      */ {
/*      */   public static final int ALLOW_MODE_READ = 0;
/*      */   public static final int ALLOW_MODE_WRITE = 1;
/*      */   public static final int ALLOW_BLEND_COLOR_READ = 2;
/*      */   public static final int ALLOW_BLEND_COLOR_WRITE = 3;
/*      */   public static final int ALLOW_TRANSFORM_READ = 4;
/*      */   public static final int ALLOW_TRANSFORM_WRITE = 5;
/*      */   public static final int ALLOW_COLOR_TABLE_READ = 6;
/*      */   public static final int ALLOW_COLOR_TABLE_WRITE = 7;
/*      */   public static final int ALLOW_COMBINE_READ = 8;
/*      */   public static final int ALLOW_COMBINE_WRITE = 9;
/*      */   public static final int FASTEST = 0;
/*      */   public static final int NICEST = 1;
/*      */   public static final int MODULATE = 2;
/*      */   public static final int DECAL = 3;
/*      */   public static final int BLEND = 4;
/*      */   public static final int REPLACE = 5;
/*      */   public static final int COMBINE = 6;
/*      */   public static final int COMBINE_REPLACE = 0;
/*      */   public static final int COMBINE_MODULATE = 1;
/*      */   public static final int COMBINE_ADD = 2;
/*      */   public static final int COMBINE_ADD_SIGNED = 3;
/*      */   public static final int COMBINE_SUBTRACT = 4;
/*      */   public static final int COMBINE_INTERPOLATE = 5;
/*      */   public static final int COMBINE_DOT3 = 6;
/*      */   public static final int COMBINE_OBJECT_COLOR = 0;
/*      */   public static final int COMBINE_TEXTURE_COLOR = 1;
/*      */   public static final int COMBINE_CONSTANT_COLOR = 2;
/*      */   public static final int COMBINE_PREVIOUS_TEXTURE_UNIT_STATE = 3;
/*      */   public static final int COMBINE_SRC_COLOR = 0;
/*      */   public static final int COMBINE_ONE_MINUS_SRC_COLOR = 1;
/*      */   public static final int COMBINE_SRC_ALPHA = 2;
/*      */   public static final int COMBINE_ONE_MINUS_SRC_ALPHA = 3;
/*  415 */   private static final int[] readCapabilities = { 2, 6, 8, 0, 4 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  454 */   public TextureAttributes() { setDefaultReadCapabilities(readCapabilities); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextureAttributes(int paramInt1, Transform3D paramTransform3D, Color4f paramColor4f, int paramInt2) {
/*  479 */     if (paramInt1 < 2 || paramInt1 > 6) {
/*  480 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes10"));
/*      */     }
/*      */     
/*  483 */     if (paramInt2 != 0 && paramInt2 != 1)
/*      */     {
/*  485 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes9"));
/*      */     }
/*      */ 
/*      */     
/*  489 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  491 */     ((TextureAttributesRetained)this.retained).initTextureMode(paramInt1);
/*  492 */     ((TextureAttributesRetained)this.retained).initTextureBlendColor(paramColor4f);
/*  493 */     ((TextureAttributesRetained)this.retained).initTextureTransform(paramTransform3D);
/*  494 */     ((TextureAttributesRetained)this.retained).initPerspectiveCorrectionMode(paramInt2);
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
/*      */   public void setTextureMode(int paramInt) {
/*  511 */     if (isLiveOrCompiled() && 
/*  512 */       !getCapability(1)) {
/*  513 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes0"));
/*      */     }
/*  515 */     if (paramInt < 2 || paramInt > 6) {
/*  516 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes10"));
/*      */     }
/*      */     
/*  519 */     if (isLive()) {
/*  520 */       ((TextureAttributesRetained)this.retained).setTextureMode(paramInt);
/*      */     } else {
/*  522 */       ((TextureAttributesRetained)this.retained).initTextureMode(paramInt);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTextureMode() {
/*  533 */     if (isLiveOrCompiled() && 
/*  534 */       !getCapability(0)) {
/*  535 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes1"));
/*      */     }
/*  537 */     return ((TextureAttributesRetained)this.retained).getTextureMode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureBlendColor(Color4f paramColor4f) {
/*  548 */     if (isLiveOrCompiled() && 
/*  549 */       !getCapability(3)) {
/*  550 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes2"));
/*      */     }
/*  552 */     if (isLive()) {
/*  553 */       ((TextureAttributesRetained)this.retained).setTextureBlendColor(paramColor4f);
/*      */     } else {
/*  555 */       ((TextureAttributesRetained)this.retained).initTextureBlendColor(paramColor4f);
/*      */     } 
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
/*      */   public void setTextureBlendColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  569 */     if (isLiveOrCompiled() && 
/*  570 */       !getCapability(3)) {
/*  571 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes3"));
/*      */     }
/*  573 */     if (isLive()) {
/*  574 */       ((TextureAttributesRetained)this.retained).setTextureBlendColor(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*      */     } else {
/*  576 */       ((TextureAttributesRetained)this.retained).initTextureBlendColor(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureBlendColor(Color4f paramColor4f) {
/*  588 */     if (isLiveOrCompiled() && 
/*  589 */       !getCapability(2)) {
/*  590 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes4"));
/*      */     }
/*  592 */     ((TextureAttributesRetained)this.retained).getTextureBlendColor(paramColor4f);
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
/*      */   public void setTextureTransform(Transform3D paramTransform3D) {
/*  604 */     if (isLiveOrCompiled() && 
/*  605 */       !getCapability(5)) {
/*  606 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes5"));
/*      */     }
/*  608 */     if (isLive()) {
/*  609 */       ((TextureAttributesRetained)this.retained).setTextureTransform(paramTransform3D);
/*      */     } else {
/*  611 */       ((TextureAttributesRetained)this.retained).initTextureTransform(paramTransform3D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureTransform(Transform3D paramTransform3D) {
/*  622 */     if (isLiveOrCompiled() && 
/*  623 */       !getCapability(4)) {
/*  624 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes6"));
/*      */     }
/*  626 */     ((TextureAttributesRetained)this.retained).getTextureTransform(paramTransform3D);
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
/*      */   public void setPerspectiveCorrectionMode(int paramInt) {
/*  644 */     if (isLiveOrCompiled() && 
/*  645 */       !getCapability(1)) {
/*  646 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes7"));
/*      */     }
/*  648 */     if (paramInt != 0 && paramInt != 1) {
/*  649 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes9"));
/*      */     }
/*  651 */     if (isLive()) {
/*  652 */       ((TextureAttributesRetained)this.retained).setPerspectiveCorrectionMode(paramInt);
/*      */     } else {
/*  654 */       ((TextureAttributesRetained)this.retained).initPerspectiveCorrectionMode(paramInt);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPerspectiveCorrectionMode() {
/*  664 */     if (isLiveOrCompiled() && 
/*  665 */       !getCapability(0))
/*  666 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes8")); 
/*  667 */     return ((TextureAttributesRetained)this.retained).getPerspectiveCorrectionMode();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureColorTable(int[][] paramArrayOfInt) {
/*  712 */     if (isLiveOrCompiled() && 
/*  713 */       !getCapability(7)) {
/*  714 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes11"));
/*      */     }
/*  716 */     if (isLive()) {
/*  717 */       ((TextureAttributesRetained)this.retained).setTextureColorTable(paramArrayOfInt);
/*      */     } else {
/*  719 */       ((TextureAttributesRetained)this.retained).initTextureColorTable(paramArrayOfInt);
/*      */     } 
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
/*      */   public void getTextureColorTable(int[][] paramArrayOfInt) {
/*  739 */     if (isLiveOrCompiled() && 
/*  740 */       !getCapability(6))
/*  741 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes12")); 
/*  742 */     ((TextureAttributesRetained)this.retained).getTextureColorTable(paramArrayOfInt);
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
/*  757 */   public int getNumTextureColorTableComponents() { return ((TextureAttributesRetained)this.retained).getNumTextureColorTableComponents(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  770 */   public int getTextureColorTableSize() { return ((TextureAttributesRetained)this.retained).getTextureColorTableSize(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCombineRgbMode(int paramInt) {
/*  797 */     if (isLiveOrCompiled() && 
/*  798 */       !getCapability(9)) {
/*  799 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes16"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  804 */     if (paramInt < 0 || paramInt > 6) {
/*  805 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes20"));
/*      */     }
/*      */ 
/*      */     
/*  809 */     if (isLive()) {
/*  810 */       ((TextureAttributesRetained)this.retained).setCombineRgbMode(paramInt);
/*      */     } else {
/*  812 */       ((TextureAttributesRetained)this.retained).initCombineRgbMode(paramInt);
/*      */     } 
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
/*      */   public void setCombineAlphaMode(int paramInt) {
/*  839 */     if (isLiveOrCompiled() && 
/*  840 */       !getCapability(9)) {
/*  841 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes18"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  846 */     if (paramInt < 0 || paramInt > 6) {
/*  847 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes20"));
/*      */     }
/*      */ 
/*      */     
/*  851 */     if (isLive()) {
/*  852 */       ((TextureAttributesRetained)this.retained).setCombineAlphaMode(paramInt);
/*      */     } else {
/*  854 */       ((TextureAttributesRetained)this.retained).initCombineAlphaMode(paramInt);
/*      */     } 
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
/*      */   public int getCombineRgbMode() {
/*  869 */     if (isLiveOrCompiled() && 
/*  870 */       !getCapability(8)) {
/*  871 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes17"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  876 */     return ((TextureAttributesRetained)this.retained).getCombineRgbMode();
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
/*      */   public int getCombineAlphaMode() {
/*  890 */     if (isLiveOrCompiled() && 
/*  891 */       !getCapability(8)) {
/*  892 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes19"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  897 */     return ((TextureAttributesRetained)this.retained).getCombineAlphaMode();
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
/*      */   public void setCombineRgbSource(int paramInt1, int paramInt2) {
/*  925 */     if (isLiveOrCompiled() && 
/*  926 */       !getCapability(9)) {
/*  927 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes21"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  932 */     if (paramInt1 < 0 || paramInt1 > 2) {
/*  933 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/*  937 */     if (paramInt2 < 0 || paramInt2 > 3)
/*      */     {
/*  939 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes26"));
/*      */     }
/*      */ 
/*      */     
/*  943 */     if (isLive()) {
/*  944 */       ((TextureAttributesRetained)this.retained).setCombineRgbSource(paramInt1, paramInt2);
/*      */     } else {
/*      */       
/*  947 */       ((TextureAttributesRetained)this.retained).initCombineRgbSource(paramInt1, paramInt2);
/*      */     } 
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
/*      */   public void setCombineAlphaSource(int paramInt1, int paramInt2) {
/*  977 */     if (isLiveOrCompiled() && 
/*  978 */       !getCapability(9)) {
/*  979 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes23"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  984 */     if (paramInt1 < 0 || paramInt1 > 2) {
/*  985 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/*  989 */     if (paramInt2 < 0 || paramInt2 > 3)
/*      */     {
/*  991 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes26"));
/*      */     }
/*      */ 
/*      */     
/*  995 */     if (isLive()) {
/*  996 */       ((TextureAttributesRetained)this.retained).setCombineAlphaSource(paramInt1, paramInt2);
/*      */     } else {
/*      */       
/*  999 */       ((TextureAttributesRetained)this.retained).initCombineAlphaSource(paramInt1, paramInt2);
/*      */     } 
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
/*      */   public int getCombineRgbSource(int paramInt) {
/* 1021 */     if (isLiveOrCompiled() && 
/* 1022 */       !getCapability(8)) {
/* 1023 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes22"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1028 */     if (paramInt < 0 || paramInt > 2) {
/* 1029 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/* 1033 */     return ((TextureAttributesRetained)this.retained).getCombineRgbSource(paramInt);
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
/*      */   public int getCombineAlphaSource(int paramInt) {
/* 1053 */     if (isLiveOrCompiled() && 
/* 1054 */       !getCapability(8)) {
/* 1055 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes24"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1060 */     if (paramInt < 0 || paramInt > 2) {
/* 1061 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/* 1065 */     return ((TextureAttributesRetained)this.retained).getCombineAlphaSource(paramInt);
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
/*      */   public void setCombineRgbFunction(int paramInt1, int paramInt2) {
/* 1094 */     if (isLiveOrCompiled() && 
/* 1095 */       !getCapability(9)) {
/* 1096 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes27"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1101 */     if (paramInt1 < 0 || paramInt1 > 2) {
/* 1102 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/* 1106 */     if (paramInt2 < 0 || paramInt2 > 3)
/*      */     {
/* 1108 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes31"));
/*      */     }
/*      */ 
/*      */     
/* 1112 */     if (isLive()) {
/* 1113 */       ((TextureAttributesRetained)this.retained).setCombineRgbFunction(paramInt1, paramInt2);
/*      */     } else {
/*      */       
/* 1116 */       ((TextureAttributesRetained)this.retained).initCombineRgbFunction(paramInt1, paramInt2);
/*      */     } 
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
/*      */   public void setCombineAlphaFunction(int paramInt1, int paramInt2) {
/* 1144 */     if (isLiveOrCompiled() && 
/* 1145 */       !getCapability(9)) {
/* 1146 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes29"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1151 */     if (paramInt1 < 0 || paramInt1 > 2) {
/* 1152 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/* 1156 */     if (paramInt2 < 2 || paramInt2 > 3)
/*      */     {
/* 1158 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes31"));
/*      */     }
/*      */ 
/*      */     
/* 1162 */     if (isLive()) {
/* 1163 */       ((TextureAttributesRetained)this.retained).setCombineAlphaFunction(paramInt1, paramInt2);
/*      */     } else {
/*      */       
/* 1166 */       ((TextureAttributesRetained)this.retained).initCombineAlphaFunction(paramInt1, paramInt2);
/*      */     } 
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
/*      */   public int getCombineRgbFunction(int paramInt) {
/* 1188 */     if (isLiveOrCompiled() && 
/* 1189 */       !getCapability(8)) {
/* 1190 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes28"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1195 */     if (paramInt < 0 || paramInt > 2) {
/* 1196 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/* 1200 */     return ((TextureAttributesRetained)this.retained).getCombineRgbFunction(paramInt);
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
/*      */   public int getCombineAlphaFunction(int paramInt) {
/* 1220 */     if (isLiveOrCompiled() && 
/* 1221 */       !getCapability(8)) {
/* 1222 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes30"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1227 */     if (paramInt < 0 || paramInt > 2) {
/* 1228 */       throw new IndexOutOfBoundsException(J3dI18N.getString("TextureAttributes25"));
/*      */     }
/*      */ 
/*      */     
/* 1232 */     return ((TextureAttributesRetained)this.retained).getCombineAlphaFunction(paramInt);
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
/*      */   public void setCombineRgbScale(int paramInt) {
/* 1252 */     if (isLiveOrCompiled() && 
/* 1253 */       !getCapability(9)) {
/* 1254 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes32"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1260 */     if (paramInt != 1 && paramInt != 2 && paramInt != 4) {
/* 1261 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes36"));
/*      */     }
/*      */ 
/*      */     
/* 1265 */     if (isLive()) {
/* 1266 */       ((TextureAttributesRetained)this.retained).setCombineRgbScale(paramInt);
/*      */     } else {
/* 1268 */       ((TextureAttributesRetained)this.retained).initCombineRgbScale(paramInt);
/*      */     } 
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
/*      */   public void setCombineAlphaScale(int paramInt) {
/* 1289 */     if (isLiveOrCompiled() && 
/* 1290 */       !getCapability(9)) {
/* 1291 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes34"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1296 */     if (paramInt != 1 && paramInt != 2 && paramInt != 4) {
/* 1297 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes36"));
/*      */     }
/*      */ 
/*      */     
/* 1301 */     if (isLive()) {
/* 1302 */       ((TextureAttributesRetained)this.retained).setCombineAlphaScale(paramInt);
/*      */     } else {
/* 1304 */       ((TextureAttributesRetained)this.retained).initCombineAlphaScale(paramInt);
/*      */     } 
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
/*      */   public int getCombineRgbScale() {
/* 1320 */     if (isLiveOrCompiled() && 
/* 1321 */       !getCapability(8)) {
/* 1322 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes33"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1327 */     return ((TextureAttributesRetained)this.retained).getCombineRgbScale();
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
/*      */   public int getCombineAlphaScale() {
/* 1342 */     if (isLiveOrCompiled() && 
/* 1343 */       !getCapability(8)) {
/* 1344 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureAttributes35"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1349 */     return ((TextureAttributesRetained)this.retained).getCombineAlphaScale();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createRetained() {
/* 1358 */     this.retained = new TextureAttributesRetained();
/* 1359 */     this.retained.setSource(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeComponent cloneNodeComponent() {
/* 1367 */     TextureAttributes textureAttributes = new TextureAttributes();
/* 1368 */     textureAttributes.duplicateNodeComponent(this);
/* 1369 */     return textureAttributes;
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
/*      */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 1393 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*      */     
/* 1395 */     TextureAttributesRetained textureAttributesRetained1 = (TextureAttributesRetained)paramNodeComponent.retained;
/*      */     
/* 1397 */     TextureAttributesRetained textureAttributesRetained2 = (TextureAttributesRetained)this.retained;
/*      */     
/* 1399 */     Color4f color4f = new Color4f();
/* 1400 */     textureAttributesRetained1.getTextureBlendColor(color4f);
/* 1401 */     Transform3D transform3D = new Transform3D();
/* 1402 */     textureAttributesRetained1.getTextureTransform(transform3D);
/*      */     
/* 1404 */     textureAttributesRetained2.initTextureMode(textureAttributesRetained1.getTextureMode());
/* 1405 */     textureAttributesRetained2.initPerspectiveCorrectionMode(textureAttributesRetained1.getPerspectiveCorrectionMode());
/* 1406 */     textureAttributesRetained2.initTextureBlendColor(color4f);
/* 1407 */     textureAttributesRetained2.initTextureTransform(transform3D);
/*      */     
/* 1409 */     if (textureAttributesRetained1.getNumTextureColorTableComponents() != 0 && textureAttributesRetained1.getTextureColorTableSize() != 0) {
/*      */       
/* 1411 */       int[][] arrayOfInt = new int[textureAttributesRetained1.getNumTextureColorTableComponents()][textureAttributesRetained1.getTextureColorTableSize()];
/*      */       
/* 1413 */       textureAttributesRetained1.getTextureColorTable(arrayOfInt);
/* 1414 */       textureAttributesRetained2.initTextureColorTable(arrayOfInt);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TextureAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */