/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.Hashtable;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point2f;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Tuple3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Texture
/*      */   extends NodeComponent
/*      */ {
/*      */   public static final int ALLOW_ENABLE_READ = 0;
/*      */   public static final int ALLOW_ENABLE_WRITE = 1;
/*      */   public static final int ALLOW_BOUNDARY_MODE_READ = 2;
/*      */   public static final int ALLOW_FILTER_READ = 3;
/*      */   public static final int ALLOW_IMAGE_READ = 4;
/*      */   public static final int ALLOW_IMAGE_WRITE = 7;
/*      */   public static final int ALLOW_FORMAT_READ = 9;
/*      */   public static final int ALLOW_SIZE_READ = 8;
/*      */   public static final int ALLOW_MIPMAP_MODE_READ = 5;
/*      */   public static final int ALLOW_BOUNDARY_COLOR_READ = 6;
/*      */   public static final int ALLOW_LOD_RANGE_READ = 10;
/*      */   public static final int ALLOW_LOD_RANGE_WRITE = 11;
/*      */   public static final int ALLOW_ANISOTROPIC_FILTER_READ = 12;
/*      */   public static final int ALLOW_SHARPEN_TEXTURE_READ = 13;
/*      */   public static final int ALLOW_FILTER4_READ = 14;
/*      */   public static final int FASTEST = 0;
/*      */   public static final int NICEST = 1;
/*      */   public static final int BASE_LEVEL_POINT = 2;
/*      */   public static final int BASE_LEVEL_LINEAR = 3;
/*      */   public static final int MULTI_LEVEL_POINT = 4;
/*      */   public static final int MULTI_LEVEL_LINEAR = 5;
/*      */   public static final int LINEAR_SHARPEN = 9;
/*      */   public static final int LINEAR_SHARPEN_RGB = 10;
/*      */   public static final int LINEAR_SHARPEN_ALPHA = 11;
/*      */   public static final int FILTER4 = 12;
/*      */   public static final int CLAMP = 2;
/*      */   public static final int WRAP = 3;
/*      */   public static final int CLAMP_TO_EDGE = 4;
/*      */   public static final int CLAMP_TO_BOUNDARY = 5;
/*      */   public static final int BASE_LEVEL = 1;
/*      */   public static final int MULTI_LEVEL_MIPMAP = 2;
/*      */   public static final int INTENSITY = 1;
/*      */   public static final int LUMINANCE = 2;
/*      */   public static final int ALPHA = 3;
/*      */   public static final int LUMINANCE_ALPHA = 4;
/*      */   public static final int RGB = 5;
/*      */   public static final int RGBA = 6;
/*      */   public static final int ANISOTROPIC_NONE = 0;
/*      */   public static final int ANISOTROPIC_SINGLE_VALUE = 1;
/*      */   private static final int[] readCapabilities = { 
/*  546 */       12, 6, 2, 0, 14, 3, 9, 4, 10, 5, 13, 8 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  594 */   public Texture() { setDefaultReadCapabilities(readCapabilities); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Texture(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  625 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  627 */     if (paramInt1 != 1 && paramInt1 != 2) {
/*  628 */       throw new IllegalArgumentException(J3dI18N.getString("Texture0"));
/*      */     }
/*  630 */     if (paramInt2 != 1 && paramInt2 != 2 && paramInt2 != 3 && paramInt2 != 4 && paramInt2 != 5 && paramInt2 != 6)
/*      */     {
/*      */       
/*  633 */       throw new IllegalArgumentException(J3dI18N.getString("Texture1"));
/*      */     }
/*      */     
/*  636 */     if (paramInt3 < 1) {
/*  637 */       throw new IllegalArgumentException(J3dI18N.getString("Texture46"));
/*      */     }
/*      */     
/*  640 */     if (paramInt4 < 1) {
/*  641 */       throw new IllegalArgumentException(J3dI18N.getString("Texture47"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  647 */     int i = getLevelsNPOT(paramInt3);
/*  648 */     int j = getLevelsNPOT(paramInt4);
/*      */     
/*  650 */     ((TextureRetained)this.retained).initialize(paramInt2, paramInt3, i, paramInt4, j, paramInt1, 0);
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
/*      */   public Texture(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/*  690 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  692 */     if (paramInt1 != 1 && paramInt1 != 2) {
/*  693 */       throw new IllegalArgumentException(J3dI18N.getString("Texture0"));
/*      */     }
/*  695 */     if (paramInt2 != 1 && paramInt2 != 2 && paramInt2 != 3 && paramInt2 != 4 && paramInt2 != 5 && paramInt2 != 6)
/*      */     {
/*      */       
/*  698 */       throw new IllegalArgumentException(J3dI18N.getString("Texture1"));
/*      */     }
/*      */     
/*  701 */     if (paramInt3 < 1) {
/*  702 */       throw new IllegalArgumentException(J3dI18N.getString("Texture46"));
/*      */     }
/*      */     
/*  705 */     if (paramInt4 < 1) {
/*  706 */       throw new IllegalArgumentException(J3dI18N.getString("Texture47"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  712 */     int i = getLevelsNPOT(paramInt3);
/*  713 */     int j = getLevelsNPOT(paramInt4);
/*      */     
/*  715 */     if (paramInt5 < 0 || paramInt5 > 1) {
/*  716 */       throw new IllegalArgumentException(J3dI18N.getString("Texture30"));
/*      */     }
/*  718 */     ((TextureRetained)this.retained).initialize(paramInt2, paramInt3, i, paramInt4, j, paramInt1, paramInt5);
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
/*      */   public void setBoundaryModeS(int paramInt) {
/*  733 */     checkForLiveOrCompiled();
/*  734 */     switch (paramInt) {
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */         break;
/*      */       default:
/*  741 */         throw new IllegalArgumentException(J3dI18N.getString("Texture31"));
/*      */     } 
/*  743 */     ((TextureRetained)this.retained).initBoundaryModeS(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBoundaryModeS() {
/*  753 */     if (isLiveOrCompiled() && 
/*  754 */       !getCapability(2))
/*  755 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture4")); 
/*  756 */     return ((TextureRetained)this.retained).getBoundaryModeS();
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
/*      */   public void setBoundaryModeT(int paramInt) {
/*  770 */     checkForLiveOrCompiled();
/*  771 */     switch (paramInt) {
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */         break;
/*      */       default:
/*  778 */         throw new IllegalArgumentException(J3dI18N.getString("Texture31"));
/*      */     } 
/*  780 */     ((TextureRetained)this.retained).initBoundaryModeT(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBoundaryModeT() {
/*  790 */     if (isLiveOrCompiled() && 
/*  791 */       !getCapability(2))
/*  792 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture4")); 
/*  793 */     return ((TextureRetained)this.retained).getBoundaryModeT();
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
/*      */   public void setMinFilter(int paramInt) {
/*  814 */     checkForLiveOrCompiled();
/*      */     
/*  816 */     switch (paramInt) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 12:
/*      */         break;
/*      */       default:
/*  826 */         throw new IllegalArgumentException(J3dI18N.getString("Texture28"));
/*      */     } 
/*      */     
/*  829 */     ((TextureRetained)this.retained).initMinFilter(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMinFilter() {
/*  839 */     if (isLiveOrCompiled() && 
/*  840 */       !getCapability(3))
/*  841 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture6")); 
/*  842 */     return ((TextureRetained)this.retained).getMinFilter();
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
/*      */   public void setMagFilter(int paramInt) {
/*  865 */     checkForLiveOrCompiled();
/*      */     
/*  867 */     switch (paramInt) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 9:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */         break;
/*      */       default:
/*  878 */         throw new IllegalArgumentException(J3dI18N.getString("Texture29"));
/*      */     } 
/*      */     
/*  881 */     ((TextureRetained)this.retained).initMagFilter(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMagFilter() {
/*  891 */     if (isLiveOrCompiled() && 
/*  892 */       !getCapability(3))
/*  893 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture6")); 
/*  894 */     return ((TextureRetained)this.retained).getMagFilter();
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
/*      */   public void setImage(int paramInt, ImageComponent paramImageComponent) {
/*  929 */     if (isLiveOrCompiled() && 
/*  930 */       !getCapability(7)) {
/*  931 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture15"));
/*      */     }
/*      */ 
/*      */     
/*  935 */     validateImageIllegalSharing(paramImageComponent);
/*      */     
/*  937 */     if (isLive()) {
/*  938 */       ((TextureRetained)this.retained).setImage(paramInt, paramImageComponent);
/*      */     } else {
/*  940 */       ((TextureRetained)this.retained).initImage(paramInt, paramImageComponent);
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
/*      */   public ImageComponent getImage(int paramInt) {
/*  952 */     if (isLiveOrCompiled() && 
/*  953 */       !getCapability(4)) {
/*  954 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture9"));
/*      */     }
/*      */     
/*  957 */     return ((TextureRetained)this.retained).getImage(paramInt);
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
/*      */   public void setImages(ImageComponent[] paramArrayOfImageComponent) {
/*  997 */     if (isLiveOrCompiled() && 
/*  998 */       !getCapability(7)) {
/*  999 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture15"));
/*      */     }
/*      */ 
/*      */     
/* 1003 */     for (byte b = 0; b < paramArrayOfImageComponent.length; b++) {
/* 1004 */       validateImageIllegalSharing(paramArrayOfImageComponent[b]);
/*      */     }
/*      */     
/* 1007 */     if (paramArrayOfImageComponent == null) {
/* 1008 */       throw new IllegalArgumentException(J3dI18N.getString("Texture20"));
/*      */     }
/* 1010 */     if (isLive()) {
/* 1011 */       ((TextureRetained)this.retained).setImages(paramArrayOfImageComponent);
/*      */     } else {
/* 1013 */       ((TextureRetained)this.retained).initImages(paramArrayOfImageComponent);
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
/*      */   public ImageComponent[] getImages() {
/* 1025 */     if (isLiveOrCompiled() && 
/* 1026 */       !getCapability(4)) {
/* 1027 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture9"));
/*      */     }
/* 1029 */     return ((TextureRetained)this.retained).getImages();
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
/*      */   public int getFormat() {
/* 1041 */     if (isLiveOrCompiled() && 
/* 1042 */       !getCapability(9)) {
/* 1043 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture19"));
/*      */     }
/* 1045 */     return ((TextureRetained)this.retained).getFormat();
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
/*      */   public int getWidth() {
/* 1057 */     if (isLiveOrCompiled() && 
/* 1058 */       !getCapability(8)) {
/* 1059 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture16"));
/*      */     }
/* 1061 */     return ((TextureRetained)this.retained).getWidth();
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
/*      */   public int getHeight() {
/* 1073 */     if (isLiveOrCompiled() && 
/* 1074 */       !getCapability(8)) {
/* 1075 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture17"));
/*      */     }
/* 1077 */     return ((TextureRetained)this.retained).getHeight();
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
/*      */   public int getBoundaryWidth() {
/* 1090 */     if (isLiveOrCompiled() && 
/* 1091 */       !getCapability(8)) {
/* 1092 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture17"));
/*      */     }
/*      */     
/* 1095 */     return ((TextureRetained)this.retained).getBoundaryWidth();
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
/*      */   public int numMipMapLevels() {
/* 1109 */     if (isLiveOrCompiled() && 
/* 1110 */       !getCapability(8)) {
/* 1111 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture18"));
/*      */     }
/* 1113 */     return ((TextureRetained)this.retained).numMipMapLevels();
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
/*      */   public void setMipMapMode(int paramInt) {
/* 1127 */     checkForLiveOrCompiled();
/* 1128 */     ((TextureRetained)this.retained).initMipMapMode(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMipMapMode() {
/* 1138 */     if (isLiveOrCompiled() && 
/* 1139 */       !getCapability(5))
/* 1140 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture10")); 
/* 1141 */     return ((TextureRetained)this.retained).getMipMapMode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEnable(boolean paramBoolean) {
/* 1152 */     if (isLiveOrCompiled() && 
/* 1153 */       !getCapability(1)) {
/* 1154 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture11"));
/*      */     }
/* 1156 */     if (isLive()) {
/* 1157 */       ((TextureRetained)this.retained).setEnable(paramBoolean);
/*      */     } else {
/* 1159 */       ((TextureRetained)this.retained).initEnable(paramBoolean);
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
/*      */   public boolean getEnable() {
/* 1171 */     if (isLiveOrCompiled() && 
/* 1172 */       !getCapability(0)) {
/* 1173 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture12"));
/*      */     }
/* 1175 */     return ((TextureRetained)this.retained).getEnable();
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
/*      */   static int getPowerOf2(int paramInt) {
/* 1187 */     if (paramInt < 1) return -1;  byte b;
/*      */     int i;
/* 1189 */     for (b = 0, i = paramInt; b < 32; b++) {
/*      */       
/* 1191 */       if ((i & 0x80000000) != 0) {
/*      */         
/* 1193 */         if ((i & 0x7FFFFFFF) == 0) {
/* 1194 */           return 31 - b;
/*      */         }
/* 1196 */         return -1;
/*      */       } 
/* 1198 */       i <<= 1;
/*      */     } 
/*      */     
/* 1201 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static int getLevelsNPOT(int paramInt) {
/* 1207 */     byte b = 0;
/* 1208 */     int i = paramInt;
/* 1209 */     while (i > 1) {
/* 1210 */       i /= 2;
/* 1211 */       b++;
/*      */     } 
/* 1213 */     return b;
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
/*      */   public void setBoundaryColor(Color4f paramColor4f) {
/* 1226 */     checkForLiveOrCompiled();
/* 1227 */     ((TextureRetained)this.retained).initBoundaryColor(paramColor4f);
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
/*      */   public void setBoundaryColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 1243 */     checkForLiveOrCompiled();
/* 1244 */     ((TextureRetained)this.retained).initBoundaryColor(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getBoundaryColor(Color4f paramColor4f) {
/* 1255 */     if (isLiveOrCompiled() && 
/* 1256 */       !getCapability(6)) {
/* 1257 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture13"));
/*      */     }
/* 1259 */     ((TextureRetained)this.retained).getBoundaryColor(paramColor4f);
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
/*      */   public void setBaseLevel(int paramInt) {
/* 1274 */     if (isLiveOrCompiled() && 
/* 1275 */       !getCapability(11)) {
/* 1276 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture32"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1281 */     if (isLive()) {
/* 1282 */       ((TextureRetained)this.retained).setBaseLevel(paramInt);
/*      */     } else {
/* 1284 */       ((TextureRetained)this.retained).initBaseLevel(paramInt);
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
/*      */   public int getBaseLevel() {
/* 1297 */     if (isLiveOrCompiled() && 
/* 1298 */       !getCapability(10)) {
/* 1299 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture34"));
/*      */     }
/*      */ 
/*      */     
/* 1303 */     return ((TextureRetained)this.retained).getBaseLevel();
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
/*      */   public void setMaximumLevel(int paramInt) {
/* 1321 */     if (isLiveOrCompiled() && 
/* 1322 */       !getCapability(11)) {
/* 1323 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture33"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1328 */     if (isLive()) {
/* 1329 */       ((TextureRetained)this.retained).setMaximumLevel(paramInt);
/*      */     } else {
/* 1331 */       ((TextureRetained)this.retained).initMaximumLevel(paramInt);
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
/*      */   public int getMaximumLevel() {
/* 1344 */     if (isLiveOrCompiled() && 
/* 1345 */       !getCapability(10)) {
/* 1346 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture35"));
/*      */     }
/*      */ 
/*      */     
/* 1350 */     return ((TextureRetained)this.retained).getMaximumLevel();
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
/*      */   public void setMinimumLOD(float paramFloat) {
/* 1364 */     if (isLiveOrCompiled() && 
/* 1365 */       !getCapability(11)) {
/* 1366 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture38"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1371 */     if (isLive()) {
/* 1372 */       ((TextureRetained)this.retained).setMinimumLOD(paramFloat);
/*      */     } else {
/* 1374 */       ((TextureRetained)this.retained).initMinimumLOD(paramFloat);
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
/*      */   public float getMinimumLOD() {
/* 1387 */     if (isLiveOrCompiled() && 
/* 1388 */       !getCapability(10)) {
/* 1389 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture40"));
/*      */     }
/*      */ 
/*      */     
/* 1393 */     return ((TextureRetained)this.retained).getMinimumLOD();
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
/*      */   public void setMaximumLOD(float paramFloat) {
/* 1407 */     if (isLiveOrCompiled() && 
/* 1408 */       !getCapability(11)) {
/* 1409 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture39"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1414 */     if (isLive()) {
/* 1415 */       ((TextureRetained)this.retained).setMaximumLOD(paramFloat);
/*      */     } else {
/* 1417 */       ((TextureRetained)this.retained).initMaximumLOD(paramFloat);
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
/*      */   public float getMaximumLOD() {
/* 1430 */     if (isLiveOrCompiled() && 
/* 1431 */       !getCapability(10)) {
/* 1432 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture41"));
/*      */     }
/*      */ 
/*      */     
/* 1436 */     return ((TextureRetained)this.retained).getMaximumLOD();
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
/*      */   public void setLodOffset(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 1451 */     if (isLiveOrCompiled() && 
/* 1452 */       !getCapability(11)) {
/* 1453 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture44"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1458 */     if (isLive()) {
/* 1459 */       ((TextureRetained)this.retained).setLodOffset(paramFloat1, paramFloat2, paramFloat3);
/*      */     } else {
/* 1461 */       ((TextureRetained)this.retained).initLodOffset(paramFloat1, paramFloat2, paramFloat3);
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
/*      */   public void setLodOffset(Tuple3f paramTuple3f) {
/* 1475 */     if (isLiveOrCompiled() && 
/* 1476 */       !getCapability(11)) {
/* 1477 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture44"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1482 */     if (isLive()) {
/* 1483 */       ((TextureRetained)this.retained).setLodOffset(paramTuple3f.x, paramTuple3f.y, paramTuple3f.z);
/*      */     } else {
/*      */       
/* 1486 */       ((TextureRetained)this.retained).initLodOffset(paramTuple3f.x, paramTuple3f.y, paramTuple3f.z);
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
/*      */   public void getLodOffset(Tuple3f paramTuple3f) {
/* 1501 */     if (isLiveOrCompiled() && 
/* 1502 */       !getCapability(10)) {
/* 1503 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture45"));
/*      */     }
/*      */ 
/*      */     
/* 1507 */     ((TextureRetained)this.retained).getLodOffset(paramTuple3f);
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
/*      */   public void setAnisotropicFilterMode(int paramInt) {
/* 1524 */     checkForLiveOrCompiled();
/* 1525 */     if (paramInt != 0 && paramInt != 1)
/*      */     {
/* 1527 */       throw new IllegalArgumentException(J3dI18N.getString("Texture25"));
/*      */     }
/*      */     
/* 1530 */     ((TextureRetained)this.retained).initAnisotropicFilterMode(paramInt);
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
/*      */   public int getAnisotropicFilterMode() {
/* 1542 */     if (isLiveOrCompiled() && 
/* 1543 */       !getCapability(12)) {
/* 1544 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture26"));
/*      */     }
/*      */ 
/*      */     
/* 1548 */     return ((TextureRetained)this.retained).getAnisotropicFilterMode();
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
/*      */   public void setAnisotropicFilterDegree(float paramFloat) {
/* 1566 */     checkForLiveOrCompiled();
/* 1567 */     if (paramFloat < 1.0D) {
/* 1568 */       throw new IllegalArgumentException(J3dI18N.getString("Texture27"));
/*      */     }
/*      */     
/* 1571 */     ((TextureRetained)this.retained).initAnisotropicFilterDegree(paramFloat);
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
/*      */   public float getAnisotropicFilterDegree() {
/* 1583 */     if (isLiveOrCompiled() && 
/* 1584 */       !getCapability(12)) {
/* 1585 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture26"));
/*      */     }
/*      */ 
/*      */     
/* 1589 */     return ((TextureRetained)this.retained).getAnisotropicFilterDegree();
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
/*      */   public void setSharpenTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 1607 */     checkForLiveOrCompiled();
/* 1608 */     if ((paramArrayOfFloat1 != null && paramArrayOfFloat2 != null && paramArrayOfFloat1.length == paramArrayOfFloat2.length) || (paramArrayOfFloat1 == null && paramArrayOfFloat2 == null)) {
/*      */       
/* 1610 */       ((TextureRetained)this.retained).initSharpenTextureFunc(paramArrayOfFloat1, paramArrayOfFloat2);
/*      */     } else {
/* 1612 */       throw new IllegalStateException(J3dI18N.getString("Texture22"));
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
/*      */   public void setSharpenTextureFunc(Point2f[] paramArrayOfPoint2f) {
/* 1632 */     checkForLiveOrCompiled();
/* 1633 */     ((TextureRetained)this.retained).initSharpenTextureFunc(paramArrayOfPoint2f);
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
/*      */   public int getSharpenTextureFuncPointsCount() {
/* 1648 */     if (isLiveOrCompiled() && 
/* 1649 */       !getCapability(13)) {
/* 1650 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture21"));
/*      */     }
/*      */ 
/*      */     
/* 1654 */     return ((TextureRetained)this.retained).getSharpenTextureFuncPointsCount();
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
/*      */   public void getSharpenTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 1672 */     if (isLiveOrCompiled() && 
/* 1673 */       !getCapability(13)) {
/* 1674 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture21"));
/*      */     }
/*      */ 
/*      */     
/* 1678 */     ((TextureRetained)this.retained).getSharpenTextureFunc(paramArrayOfFloat1, paramArrayOfFloat2);
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
/*      */   public void getSharpenTextureFunc(Point2f[] paramArrayOfPoint2f) {
/* 1696 */     if (isLiveOrCompiled() && 
/* 1697 */       !getCapability(13)) {
/* 1698 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture21"));
/*      */     }
/*      */ 
/*      */     
/* 1702 */     ((TextureRetained)this.retained).getSharpenTextureFunc(paramArrayOfPoint2f);
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
/*      */   public void setFilter4Func(float[] paramArrayOfFloat) {
/* 1718 */     checkForLiveOrCompiled();
/* 1719 */     if (paramArrayOfFloat == null || paramArrayOfFloat.length < 4) {
/* 1720 */       throw new IllegalArgumentException(J3dI18N.getString("Texture24"));
/*      */     }
/*      */     
/* 1723 */     ((TextureRetained)this.retained).initFilter4Func(paramArrayOfFloat);
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
/*      */   public int getFilter4FuncPointsCount() {
/* 1739 */     if (isLiveOrCompiled() && 
/* 1740 */       !getCapability(14)) {
/* 1741 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture23"));
/*      */     }
/*      */ 
/*      */     
/* 1745 */     return ((TextureRetained)this.retained).getFilter4FuncPointsCount();
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
/*      */   public void getFilter4Func(float[] paramArrayOfFloat) {
/* 1760 */     if (isLiveOrCompiled() && 
/* 1761 */       !getCapability(14)) {
/* 1762 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture23"));
/*      */     }
/*      */ 
/*      */     
/* 1766 */     ((TextureRetained)this.retained).getFilter4Func(paramArrayOfFloat);
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
/* 1790 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*      */     
/* 1792 */     Hashtable hashtable = paramNodeComponent.nodeHashtable;
/*      */     
/* 1794 */     TextureRetained textureRetained1 = (TextureRetained)paramNodeComponent.retained;
/* 1795 */     TextureRetained textureRetained2 = (TextureRetained)this.retained;
/*      */     
/* 1797 */     textureRetained2.initBoundaryModeS(textureRetained1.getBoundaryModeS());
/* 1798 */     textureRetained2.initBoundaryModeT(textureRetained1.getBoundaryModeT());
/* 1799 */     textureRetained2.initMinFilter(textureRetained1.getMinFilter());
/* 1800 */     textureRetained2.initMagFilter(textureRetained1.getMagFilter());
/* 1801 */     textureRetained2.initMipMapMode(textureRetained1.getMipMapMode());
/* 1802 */     textureRetained2.initEnable(textureRetained1.getEnable());
/* 1803 */     textureRetained2.initAnisotropicFilterMode(textureRetained1.getAnisotropicFilterMode());
/* 1804 */     textureRetained2.initAnisotropicFilterDegree(textureRetained1.getAnisotropicFilterDegree());
/* 1805 */     textureRetained2.initSharpenTextureFunc(textureRetained1.getSharpenTextureFunc());
/* 1806 */     textureRetained2.initFilter4Func(textureRetained1.getFilter4Func());
/*      */     
/* 1808 */     textureRetained2.initBaseLevel(textureRetained1.getBaseLevel());
/* 1809 */     textureRetained2.initMaximumLevel(textureRetained1.getMaximumLevel());
/* 1810 */     textureRetained2.initMinimumLOD(textureRetained1.getMinimumLOD());
/* 1811 */     textureRetained2.initMaximumLOD(textureRetained1.getMaximumLOD());
/*      */     
/* 1813 */     Point3f point3f = new Point3f();
/* 1814 */     textureRetained1.getLodOffset(point3f);
/* 1815 */     textureRetained2.initLodOffset(point3f.x, point3f.y, point3f.z);
/*      */     
/* 1817 */     Color4f color4f = new Color4f();
/* 1818 */     textureRetained1.getBoundaryColor(color4f);
/* 1819 */     textureRetained2.initBoundaryColor(color4f);
/*      */ 
/*      */     
/* 1822 */     for (int i = textureRetained1.maxLevels - 1; i >= 0; i--) {
/* 1823 */       ImageComponent imageComponent = (ImageComponent)getNodeComponent(textureRetained1.getImage(i), paramBoolean, hashtable);
/*      */ 
/*      */ 
/*      */       
/* 1827 */       if (imageComponent != null) {
/* 1828 */         textureRetained2.initImage(i, imageComponent);
/*      */       }
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
/*      */   boolean duplicateChild() {
/* 1843 */     if (getDuplicateOnCloneTree()) {
/* 1844 */       return true;
/*      */     }
/* 1846 */     int i = ((TextureRetained)this.retained).maxLevels;
/* 1847 */     TextureRetained textureRetained = (TextureRetained)this.retained;
/*      */     
/* 1849 */     for (byte b = 0; b < i; b++) {
/* 1850 */       ImageComponent imageComponent = textureRetained.getImage(b);
/* 1851 */       if (imageComponent != null && imageComponent.getDuplicateOnCloneTree())
/* 1852 */         return true; 
/*      */     } 
/* 1854 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Texture.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */