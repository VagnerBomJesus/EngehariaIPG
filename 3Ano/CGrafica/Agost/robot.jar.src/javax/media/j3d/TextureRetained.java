/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
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
/*      */ abstract class TextureRetained
/*      */   extends NodeComponentRetained
/*      */ {
/*      */   static final int ENABLE_CHANGED = 1;
/*      */   static final int COLOR_CHANGED = 2;
/*      */   static final int IMAGE_CHANGED = 4;
/*      */   static final int STATE_CHANGED = 8;
/*      */   static final int UPDATE_IMAGE = 16;
/*      */   static final int IMAGES_CHANGED = 32;
/*      */   static final int BASE_LEVEL_CHANGED = 64;
/*      */   static final int MAX_LEVEL_CHANGED = 128;
/*      */   static final int MIN_LOD_CHANGED = 256;
/*      */   static final int MAX_LOD_CHANGED = 512;
/*      */   static final int LOD_OFFSET_CHANGED = 1024;
/*      */   static final int MIN_FILTER = 0;
/*      */   static final int MAG_FILTER = 1;
/*   47 */   int boundaryWidth = 0;
/*      */ 
/*      */   
/*   50 */   int boundaryModeS = 3;
/*   51 */   int boundaryModeT = 3;
/*      */ 
/*      */   
/*   54 */   int minFilter = 2;
/*   55 */   int magFilter = 2;
/*      */ 
/*      */ 
/*      */   
/*   59 */   int isDirty = 65535;
/*      */ 
/*      */   
/*   62 */   Color4f boundaryColor = new Color4f(0.0F, 0.0F, 0.0F, 0.0F);
/*      */ 
/*      */   
/*   65 */   int objectId = -1;
/*      */   
/*   67 */   int mipmapMode = 1;
/*   68 */   int format = 5;
/*   69 */   int width = 1;
/*   70 */   int height = 1;
/*      */ 
/*      */   
/*      */   private boolean widthOrHeightIsNPOT = false;
/*      */   
/*      */   ImageComponentRetained[][] images;
/*      */   
/*   77 */   int maxLevels = 0;
/*      */   
/*   79 */   private int maxMipMapLevels = 0;
/*      */   
/*   81 */   int numFaces = 1;
/*   82 */   int baseLevel = 0;
/*   83 */   int maximumLevel = 0;
/*   84 */   float minimumLod = -1000.0F;
/*   85 */   float maximumLod = 1000.0F;
/*   86 */   Point3f lodOffset = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useAsRaster = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean enable = true;
/*      */ 
/*      */   
/*      */   boolean userSpecifiedEnable = true;
/*      */ 
/*      */   
/*      */   boolean isAlphaNeedUpdate = false;
/*      */ 
/*      */   
/*  103 */   int numSharpenTextureFuncPts = 0;
/*  104 */   float[] sharpenTextureFuncPts = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  109 */   float[] filter4FuncPts = null;
/*      */ 
/*      */   
/*  112 */   int anisotropicFilterMode = 0;
/*  113 */   float anisotropicFilterDegree = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   int resourceCreationMask = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  131 */   int resourceUpdatedMask = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   int resourceLodUpdatedMask = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  147 */   int resourceInReloadList = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   ArrayList[][] imageUpdateInfo;
/*      */ 
/*      */ 
/*      */   
/*      */   int[] imageUpdatePruneMask;
/*      */ 
/*      */ 
/*      */   
/*  159 */   private HashMap<RenderBin, Integer> textureBinRefCount = new HashMap();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  164 */   private int texTimestamp = 0;
/*      */ 
/*      */   
/*  167 */   Object resourceLock = new Object();
/*      */ 
/*      */   
/*  170 */   private static boolean isPowerOfTwo(int paramInt) { return ((paramInt & paramInt - 1) == 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initialize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
/*  177 */     this.mipmapMode = paramInt6;
/*  178 */     this.format = paramInt1;
/*  179 */     this.width = paramInt2;
/*  180 */     this.height = paramInt4;
/*  181 */     this.boundaryWidth = paramInt7;
/*      */     
/*  183 */     if (!isPowerOfTwo(paramInt2) || !isPowerOfTwo(paramInt4)) {
/*  184 */       this.widthOrHeightIsNPOT = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  190 */     if (paramInt3 > paramInt5) {
/*  191 */       this.maxMipMapLevels = paramInt3 + 1;
/*      */     } else {
/*  193 */       this.maxMipMapLevels = paramInt5 + 1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  200 */     if (paramInt6 != 1) {
/*  201 */       this.baseLevel = 0;
/*  202 */       this.maximumLevel = this.maxMipMapLevels - 1;
/*  203 */       this.maxLevels = this.maxMipMapLevels;
/*      */     } else {
/*  205 */       this.baseLevel = 0;
/*  206 */       this.maximumLevel = 0;
/*  207 */       this.maxLevels = 1;
/*      */     } 
/*      */     
/*  210 */     this.images = new ImageComponentRetained[this.numFaces][this.maxLevels];
/*      */     
/*  212 */     for (byte b = 0; b < this.numFaces; b++) {
/*  213 */       for (byte b1 = 0; b1 < this.maxLevels; b1++) {
/*  214 */         this.images[b][b1] = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  220 */   final int getFormat() { return this.format; }
/*      */ 
/*      */ 
/*      */   
/*  224 */   final int getWidth() { return this.width; }
/*      */ 
/*      */ 
/*      */   
/*  228 */   final int getHeight() { return this.height; }
/*      */ 
/*      */ 
/*      */   
/*  232 */   final int numMipMapLevels() { return this.maximumLevel - this.baseLevel + 1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  243 */   final void initBoundaryModeS(int paramInt) { this.boundaryModeS = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  253 */   final int getBoundaryModeS() { return this.boundaryModeS; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  264 */   final void initBoundaryModeT(int paramInt) { this.boundaryModeT = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  274 */   final int getBoundaryModeT() { return this.boundaryModeT; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  282 */   final int getBoundaryWidth() { return this.boundaryWidth; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  296 */   final void initMinFilter(int paramInt) { this.minFilter = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  306 */   final int getMinFilter() { return this.minFilter; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  319 */   final void initMagFilter(int paramInt) { this.magFilter = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  329 */   final int getMagFilter() { return this.magFilter; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initImage(int paramInt, ImageComponent paramImageComponent) {
/*  345 */     checkImageSize(paramInt, paramImageComponent);
/*      */     
/*  347 */     if (this.images == null) {
/*  348 */       throw new IllegalArgumentException(J3dI18N.getString("TextureRetained0"));
/*      */     }
/*      */     
/*  351 */     if (this.source instanceof Texture2D) {
/*  352 */       if (paramImageComponent instanceof ImageComponent3D) {
/*  353 */         throw new IllegalArgumentException(J3dI18N.getString("Texture8"));
/*      */       
/*      */       }
/*      */     }
/*  357 */     else if (paramImageComponent instanceof ImageComponent2D) {
/*  358 */       throw new IllegalArgumentException(J3dI18N.getString("Texture14"));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  363 */     if (this.source.isLive()) {
/*      */       
/*  365 */       if (this.images[false][paramInt] != null) {
/*  366 */         this.images[0][paramInt].clearLive(this.refCount);
/*      */       }
/*      */ 
/*      */       
/*  370 */       if (paramImageComponent != null) {
/*  371 */         ((ImageComponentRetained)paramImageComponent.retained).setLive(this.inBackgroundGroup, this.refCount);
/*      */       }
/*      */     } 
/*      */     
/*  375 */     if (paramImageComponent != null) {
/*  376 */       this.images[0][paramInt] = (ImageComponentRetained)paramImageComponent.retained;
/*      */     } else {
/*      */       
/*  379 */       this.images[0][paramInt] = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   final void checkImageSize(int paramInt, ImageComponent paramImageComponent) {
/*  384 */     if (paramImageComponent != null) {
/*  385 */       int i = ((ImageComponentRetained)paramImageComponent.retained).width;
/*  386 */       int j = ((ImageComponentRetained)paramImageComponent.retained).height;
/*      */       
/*  388 */       int k = this.width;
/*  389 */       int m = this.height;
/*  390 */       for (byte b = 0; b < paramInt; b++) {
/*  391 */         k >>= 1;
/*  392 */         m >>= 1;
/*      */       } 
/*      */       
/*  395 */       if (k < 1) k = 1; 
/*  396 */       if (m < 1) m = 1;
/*      */       
/*  398 */       if (k != i - 2 * this.boundaryWidth || m != j - 2 * this.boundaryWidth)
/*      */       {
/*  400 */         throw new IllegalArgumentException(J3dI18N.getString("TextureRetained1"));
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
/*      */   final void checkSizes(ImageComponentRetained[] paramArrayOfImageComponentRetained) {
/*  412 */     if (paramArrayOfImageComponentRetained != null) {
/*  413 */       int i = this.height;
/*  414 */       int j = this.width;
/*  415 */       for (byte b = 0; b < paramArrayOfImageComponentRetained.length; b++) {
/*  416 */         int k = (paramArrayOfImageComponentRetained[b]).width;
/*  417 */         int m = (paramArrayOfImageComponentRetained[b]).height;
/*      */         
/*  419 */         assert j == k - 2 * this.boundaryWidth && i == m - 2 * this.boundaryWidth;
/*      */ 
/*      */         
/*  422 */         j /= 2;
/*  423 */         i /= 2;
/*  424 */         if (j < 1) j = 1; 
/*  425 */         if (i < 1) i = 1; 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   final void setImage(int paramInt, ImageComponent paramImageComponent) {
/*  431 */     initImage(paramInt, paramImageComponent);
/*      */     
/*  433 */     Object[] arrayOfObject = new Object[3];
/*  434 */     arrayOfObject[0] = new Integer(paramInt);
/*  435 */     arrayOfObject[1] = paramImageComponent;
/*  436 */     arrayOfObject[2] = new Integer(0);
/*  437 */     sendMessage(4, arrayOfObject);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  442 */     if (this.userSpecifiedEnable) {
/*  443 */       this.enable = this.userSpecifiedEnable;
/*  444 */       if (paramImageComponent != null && paramInt >= this.baseLevel && paramInt <= this.maximumLevel) {
/*  445 */         ImageComponentRetained imageComponentRetained = (ImageComponentRetained)paramImageComponent.retained;
/*  446 */         if (imageComponentRetained.isByReference()) {
/*  447 */           if (imageComponentRetained.getRefImage(false) == null) {
/*  448 */             this.enable = false;
/*      */           
/*      */           }
/*      */         }
/*  452 */         else if (imageComponentRetained.getImageData(isUseAsRaster()).get() == null) {
/*  453 */           this.enable = false;
/*      */         } 
/*      */         
/*  456 */         if (!this.enable) {
/*  457 */           sendMessage(1, Boolean.FALSE);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void initImages(ImageComponent[] paramArrayOfImageComponent) {
/*  464 */     if (paramArrayOfImageComponent.length != this.maxLevels) {
/*  465 */       throw new IllegalArgumentException(J3dI18N.getString("Texture20"));
/*      */     }
/*  467 */     for (byte b = 0; b < paramArrayOfImageComponent.length; b++) {
/*  468 */       initImage(b, paramArrayOfImageComponent[b]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void setImages(ImageComponent[] paramArrayOfImageComponent) {
/*  476 */     initImages(paramArrayOfImageComponent);
/*      */     
/*  478 */     ImageComponent[] arrayOfImageComponent = new ImageComponent[paramArrayOfImageComponent.length]; int i;
/*  479 */     for (i = 0; i < paramArrayOfImageComponent.length; i++) {
/*  480 */       arrayOfImageComponent[i] = paramArrayOfImageComponent[i];
/*      */     }
/*      */     
/*  483 */     Object[] arrayOfObject = new Object[2];
/*  484 */     arrayOfObject[0] = arrayOfImageComponent;
/*  485 */     arrayOfObject[1] = new Integer(0);
/*      */     
/*  487 */     sendMessage(32, arrayOfObject);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  492 */     if (this.userSpecifiedEnable) {
/*  493 */       this.enable = this.userSpecifiedEnable;
/*  494 */       for (i = this.baseLevel; i <= this.maximumLevel && this.enable; i++) {
/*  495 */         if (paramArrayOfImageComponent[i] != null) {
/*  496 */           ImageComponentRetained imageComponentRetained = (ImageComponentRetained)(paramArrayOfImageComponent[i]).retained;
/*      */           
/*  498 */           if (imageComponentRetained.isByReference()) {
/*  499 */             if (imageComponentRetained.getRefImage(false) == null) {
/*  500 */               this.enable = false;
/*      */             
/*      */             }
/*      */           }
/*  504 */           else if (imageComponentRetained.getImageData(isUseAsRaster()).get() == null) {
/*  505 */             this.enable = false;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  510 */       if (!this.enable) {
/*  511 */         sendMessage(1, Boolean.FALSE);
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
/*      */ 
/*      */   
/*  527 */   final ImageComponent getImage(int paramInt) { return (this.images != null && this.images[false][paramInt] != null) ? (ImageComponent)(this.images[0][paramInt]).source : null; }
/*      */ 
/*      */ 
/*      */   
/*      */   final ImageComponent[] getImages() {
/*  532 */     if (this.images == null) {
/*  533 */       return null;
/*      */     }
/*  535 */     ImageComponent[] arrayOfImageComponent = new ImageComponent[this.images[0].length];
/*  536 */     for (byte b = 0; b < this.images[0].length; b++) {
/*  537 */       if (this.images[false][b] != null) {
/*  538 */         arrayOfImageComponent[b] = (ImageComponent)(this.images[0][b]).source;
/*      */       } else {
/*  540 */         arrayOfImageComponent[b] = null;
/*      */       } 
/*  542 */     }  return arrayOfImageComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void initMipMapMode(int paramInt) {
/*  553 */     if (this.mipmapMode == paramInt) {
/*      */       return;
/*      */     }
/*      */     
/*  557 */     int i = this.maxLevels;
/*      */     
/*  559 */     this.mipmapMode = paramInt;
/*      */     
/*  561 */     if (paramInt != 1) {
/*  562 */       this.maxLevels = this.maxMipMapLevels;
/*      */     } else {
/*  564 */       this.baseLevel = 0;
/*  565 */       this.maximumLevel = 0;
/*  566 */       this.maxLevels = 1;
/*      */     } 
/*      */ 
/*      */     
/*  570 */     ImageComponentRetained[][] arrayOfImageComponentRetained = new ImageComponentRetained[this.numFaces][this.maxLevels];
/*      */ 
/*      */     
/*  573 */     if (i < this.maxLevels) {
/*  574 */       for (byte b = 0; b < this.numFaces; b++) {
/*  575 */         int j; for (j = 0; j < i; j++) {
/*  576 */           arrayOfImageComponentRetained[b][j] = this.images[b][j];
/*      */         }
/*      */         
/*  579 */         for (j = i; j < this.maxLevels; j++) {
/*  580 */           arrayOfImageComponentRetained[b][j] = null;
/*      */         }
/*      */       } 
/*      */     } else {
/*  584 */       for (byte b = 0; b < this.numFaces; b++) {
/*  585 */         for (byte b1 = 0; b1 < this.maxLevels; b1++)
/*  586 */           arrayOfImageComponentRetained[b][b1] = this.images[b][b1]; 
/*      */       } 
/*      */     } 
/*  589 */     this.images = arrayOfImageComponentRetained;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  598 */   final int getMipMapMode() { return this.mipmapMode; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  607 */   final void initEnable(boolean paramBoolean) { this.userSpecifiedEnable = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void setEnable(boolean paramBoolean) {
/*  618 */     initEnable(paramBoolean);
/*      */     
/*  620 */     if (paramBoolean == this.enable) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  627 */     this.enable = paramBoolean;
/*      */     
/*  629 */     for (byte b = 0; b < this.numFaces && this.enable; b++) {
/*  630 */       for (int i = this.baseLevel; i <= this.maximumLevel && this.enable; i++) {
/*  631 */         if (this.images[b][i].isByReference()) {
/*  632 */           if (this.images[b][i].getRefImage(false) == null) {
/*  633 */             this.enable = false;
/*      */           }
/*      */         }
/*  636 */         else if (this.images[b][i].getImageData(isUseAsRaster()).get() == null) {
/*  637 */           this.enable = false;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  642 */     sendMessage(1, this.enable ? Boolean.TRUE : Boolean.FALSE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  651 */   final boolean getEnable() { return this.userSpecifiedEnable; }
/*      */ 
/*      */ 
/*      */   
/*      */   final void initBaseLevel(int paramInt) {
/*  656 */     if (paramInt < 0 || paramInt > this.maximumLevel) {
/*  657 */       throw new IllegalArgumentException(J3dI18N.getString("Texture36"));
/*      */     }
/*      */     
/*  660 */     this.baseLevel = paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   final void setBaseLevel(int paramInt) {
/*  666 */     if (paramInt == this.baseLevel) {
/*      */       return;
/*      */     }
/*  669 */     initBaseLevel(paramInt);
/*  670 */     sendMessage(64, new Integer(paramInt));
/*      */   }
/*      */ 
/*      */   
/*  674 */   final int getBaseLevel() { return this.baseLevel; }
/*      */ 
/*      */ 
/*      */   
/*      */   final void initMaximumLevel(int paramInt) {
/*  679 */     if (paramInt < this.baseLevel || paramInt >= this.maxMipMapLevels) {
/*  680 */       throw new IllegalArgumentException(J3dI18N.getString("Texture37"));
/*      */     }
/*      */     
/*  683 */     if (this.mipmapMode == 1 && paramInt != 0) {
/*  684 */       throw new IllegalArgumentException(J3dI18N.getString("Texture48"));
/*      */     }
/*      */ 
/*      */     
/*  688 */     this.maximumLevel = paramInt;
/*      */   }
/*      */ 
/*      */   
/*      */   final void setMaximumLevel(int paramInt) {
/*  693 */     if (paramInt == this.maximumLevel) {
/*      */       return;
/*      */     }
/*  696 */     initMaximumLevel(paramInt);
/*  697 */     sendMessage(128, new Integer(paramInt));
/*      */   }
/*      */ 
/*      */   
/*  701 */   final int getMaximumLevel() { return this.maximumLevel; }
/*      */ 
/*      */   
/*      */   final void initMinimumLOD(float paramFloat) {
/*  705 */     if (paramFloat > this.maximumLod) {
/*  706 */       throw new IllegalArgumentException(J3dI18N.getString("Texture42"));
/*      */     }
/*      */     
/*  709 */     this.minimumLod = paramFloat;
/*      */   }
/*      */   
/*      */   final void setMinimumLOD(float paramFloat) {
/*  713 */     initMinimumLOD(paramFloat);
/*  714 */     sendMessage(256, new Float(paramFloat));
/*      */   }
/*      */ 
/*      */   
/*  718 */   final float getMinimumLOD() { return this.minimumLod; }
/*      */ 
/*      */ 
/*      */   
/*      */   final void initMaximumLOD(float paramFloat) {
/*  723 */     if (paramFloat < this.minimumLod) {
/*  724 */       throw new IllegalArgumentException(J3dI18N.getString("Texture42"));
/*      */     }
/*      */     
/*  727 */     this.maximumLod = paramFloat;
/*      */   }
/*      */   
/*      */   final void setMaximumLOD(float paramFloat) {
/*  731 */     initMaximumLOD(paramFloat);
/*  732 */     sendMessage(512, new Float(paramFloat));
/*      */   }
/*      */ 
/*      */   
/*  736 */   final float getMaximumLOD() { return this.maximumLod; }
/*      */ 
/*      */ 
/*      */   
/*      */   final void initLodOffset(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  741 */     if (this.lodOffset == null) {
/*  742 */       this.lodOffset = new Point3f(paramFloat1, paramFloat2, paramFloat3);
/*      */     } else {
/*  744 */       this.lodOffset.set(paramFloat1, paramFloat2, paramFloat3);
/*      */     } 
/*      */   }
/*      */   
/*      */   final void setLodOffset(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  749 */     initLodOffset(paramFloat1, paramFloat2, paramFloat3);
/*  750 */     sendMessage(1024, new Point3f(paramFloat1, paramFloat2, paramFloat3));
/*      */   }
/*      */   
/*      */   final void getLodOffset(Tuple3f paramTuple3f) {
/*  754 */     if (this.lodOffset == null) {
/*  755 */       paramTuple3f.set(0.0F, 0.0F, 0.0F);
/*      */     } else {
/*  757 */       paramTuple3f.set(this.lodOffset);
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
/*  769 */   final void initBoundaryColor(Color4f paramColor4f) { this.boundaryColor.set(paramColor4f); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  782 */   final void initBoundaryColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { this.boundaryColor.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  791 */   final void getBoundaryColor(Color4f paramColor4f) { paramColor4f.set(this.boundaryColor); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  799 */   final void initAnisotropicFilterMode(int paramInt) { this.anisotropicFilterMode = paramInt; }
/*      */ 
/*      */ 
/*      */   
/*  803 */   final int getAnisotropicFilterMode() { return this.anisotropicFilterMode; }
/*      */ 
/*      */ 
/*      */   
/*  807 */   final void initAnisotropicFilterDegree(float paramFloat) { this.anisotropicFilterDegree = paramFloat; }
/*      */ 
/*      */ 
/*      */   
/*  811 */   final float getAnisotropicFilterDegree() { return this.anisotropicFilterDegree; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void initSharpenTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/*  818 */     if (paramArrayOfFloat1 == null) {
/*  819 */       this.sharpenTextureFuncPts = null;
/*  820 */       this.numSharpenTextureFuncPts = 0;
/*      */     } else {
/*  822 */       this.numSharpenTextureFuncPts = paramArrayOfFloat1.length;
/*  823 */       if (this.sharpenTextureFuncPts == null || this.sharpenTextureFuncPts.length != paramArrayOfFloat1.length * 2)
/*      */       {
/*  825 */         this.sharpenTextureFuncPts = new float[paramArrayOfFloat1.length * 2];
/*      */       }
/*  827 */       for (byte b1 = 0, b2 = 0; b1 < paramArrayOfFloat1.length; b1++) {
/*  828 */         this.sharpenTextureFuncPts[b2++] = paramArrayOfFloat1[b1];
/*  829 */         this.sharpenTextureFuncPts[b2++] = paramArrayOfFloat2[b1];
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   final void initSharpenTextureFunc(Point2f[] paramArrayOfPoint2f) {
/*  835 */     if (paramArrayOfPoint2f == null) {
/*  836 */       this.sharpenTextureFuncPts = null;
/*  837 */       this.numSharpenTextureFuncPts = 0;
/*      */     } else {
/*  839 */       this.numSharpenTextureFuncPts = paramArrayOfPoint2f.length;
/*  840 */       if (this.sharpenTextureFuncPts == null || this.sharpenTextureFuncPts.length != paramArrayOfPoint2f.length * 2)
/*      */       {
/*  842 */         this.sharpenTextureFuncPts = new float[paramArrayOfPoint2f.length * 2];
/*      */       }
/*  844 */       for (byte b1 = 0, b2 = 0; b1 < paramArrayOfPoint2f.length; b1++) {
/*  845 */         this.sharpenTextureFuncPts[b2++] = (paramArrayOfPoint2f[b1]).x;
/*  846 */         this.sharpenTextureFuncPts[b2++] = (paramArrayOfPoint2f[b1]).y;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   final void initSharpenTextureFunc(float[] paramArrayOfFloat) {
/*  852 */     if (paramArrayOfFloat == null) {
/*  853 */       this.sharpenTextureFuncPts = null;
/*  854 */       this.numSharpenTextureFuncPts = 0;
/*      */     } else {
/*  856 */       this.numSharpenTextureFuncPts = paramArrayOfFloat.length / 2;
/*  857 */       if (this.sharpenTextureFuncPts == null || this.sharpenTextureFuncPts.length != paramArrayOfFloat.length)
/*      */       {
/*  859 */         this.sharpenTextureFuncPts = new float[paramArrayOfFloat.length];
/*      */       }
/*  861 */       for (byte b = 0; b < paramArrayOfFloat.length; b++) {
/*  862 */         this.sharpenTextureFuncPts[b] = paramArrayOfFloat[b];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  871 */   final int getSharpenTextureFuncPointsCount() { return this.numSharpenTextureFuncPts; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void getSharpenTextureFunc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/*  880 */     if (this.sharpenTextureFuncPts != null) {
/*  881 */       for (byte b1 = 0, b2 = 0; b1 < this.numSharpenTextureFuncPts; b1++) {
/*  882 */         paramArrayOfFloat1[b1] = this.sharpenTextureFuncPts[b2++];
/*  883 */         paramArrayOfFloat2[b1] = this.sharpenTextureFuncPts[b2++];
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   final void getSharpenTextureFunc(Point2f[] paramArrayOfPoint2f) {
/*  889 */     if (this.sharpenTextureFuncPts != null)
/*  890 */       for (byte b1 = 0, b2 = 0; b1 < this.numSharpenTextureFuncPts; b1++) {
/*  891 */         (paramArrayOfPoint2f[b1]).x = this.sharpenTextureFuncPts[b2++];
/*  892 */         (paramArrayOfPoint2f[b1]).y = this.sharpenTextureFuncPts[b2++];
/*      */       }  
/*      */   }
/*      */   
/*      */   final void initFilter4Func(float[] paramArrayOfFloat) {
/*  897 */     if (paramArrayOfFloat == null) {
/*  898 */       this.filter4FuncPts = null;
/*      */     } else {
/*  900 */       if (this.filter4FuncPts == null || this.filter4FuncPts.length != paramArrayOfFloat.length)
/*      */       {
/*  902 */         this.filter4FuncPts = new float[paramArrayOfFloat.length];
/*      */       }
/*  904 */       for (byte b = 0; b < paramArrayOfFloat.length; b++) {
/*  905 */         this.filter4FuncPts[b] = paramArrayOfFloat[b];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   final int getFilter4FuncPointsCount() {
/*  912 */     if (this.filter4FuncPts == null) {
/*  913 */       return 0;
/*      */     }
/*  915 */     return this.filter4FuncPts.length;
/*      */   }
/*      */ 
/*      */   
/*      */   final void getFilter4Func(float[] paramArrayOfFloat) {
/*  920 */     if (this.filter4FuncPts != null) {
/*  921 */       for (byte b = 0; b < this.filter4FuncPts.length; b++) {
/*  922 */         paramArrayOfFloat[b] = this.filter4FuncPts[b];
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  932 */   final float[] getSharpenTextureFunc() { return this.sharpenTextureFuncPts; }
/*      */ 
/*      */ 
/*      */   
/*  936 */   final float[] getFilter4Func() { return this.filter4FuncPts; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(boolean paramBoolean, int paramInt) {
/*  946 */     this.enable = this.userSpecifiedEnable;
/*      */     
/*  948 */     doSetLive(paramBoolean, paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  955 */     if (this.images != null)
/*      */     {
/*  957 */       for (byte b = 0; b < this.numFaces; b++) {
/*  958 */         for (byte b1 = 0; b1 < this.maxLevels; b1++) {
/*  959 */           if (this.images[b][b1] == null) {
/*  960 */             throw new IllegalArgumentException(J3dI18N.getString("TextureRetained3") + b1);
/*      */           }
/*      */           
/*  963 */           this.images[b][b1].setLive(paramBoolean, paramInt);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  970 */     if (this.images != null) {
/*  971 */       for (byte b = 0; b < this.numFaces; b++) {
/*  972 */         checkSizes(this.images[b]);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  977 */     J3dMessage j3dMessage = new J3dMessage();
/*  978 */     j3dMessage.threads = 1024;
/*  979 */     j3dMessage.type = 15;
/*  980 */     j3dMessage.args[0] = this;
/*  981 */     j3dMessage.args[1] = new Integer(16);
/*  982 */     j3dMessage.args[2] = null;
/*  983 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/*  984 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */ 
/*      */ 
/*      */     
/*  988 */     if (this.userSpecifiedEnable) {
/*  989 */       if (this.images != null) {
/*  990 */         for (byte b = 0; b < this.numFaces && this.enable; b++) {
/*  991 */           for (int i = this.baseLevel; i <= this.maximumLevel && this.enable; i++) {
/*  992 */             if (this.images[b][i].isByReference()) {
/*  993 */               if (this.images[b][i].getRefImage(false) == null) {
/*  994 */                 this.enable = false;
/*      */               }
/*      */             }
/*  997 */             else if (this.images[b][i].getImageData(isUseAsRaster()).get() == null) {
/*  998 */               this.enable = false;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 1004 */         this.enable = false;
/*      */       } 
/* 1006 */       if (!this.enable) {
/* 1007 */         sendMessage(1, Boolean.FALSE);
/*      */       }
/*      */     } 
/* 1010 */     markAsLive();
/*      */   }
/*      */   
/*      */   void clearLive(int paramInt) {
/* 1014 */     super.clearLive(paramInt);
/*      */     
/* 1016 */     if (this.images != null) {
/* 1017 */       for (byte b = 0; b < this.numFaces; b++) {
/* 1018 */         for (byte b1 = 0; b1 < this.maxLevels; b1++) {
/* 1019 */           this.images[b][b1].clearLive(paramInt);
/* 1020 */           this.images[b][b1].removeUser(this.mirror);
/*      */         } 
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
/* 1033 */   void bindTexture(Context paramContext, int paramInt, boolean paramBoolean) { Pipeline.getPipeline().bindTexture2D(paramContext, paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1041 */   void updateTextureBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { Pipeline.getPipeline().updateTexture2DBoundary(paramContext, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1050 */   void updateTextureFilterModes(Context paramContext, int paramInt1, int paramInt2) { Pipeline.getPipeline().updateTexture2DFilterModes(paramContext, paramInt1, paramInt2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1058 */   void updateTextureSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { Pipeline.getPipeline().updateTexture2DSharpenFunc(paramContext, paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1066 */   void updateTextureFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { Pipeline.getPipeline().updateTexture2DFilter4Func(paramContext, paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1071 */   void updateTextureAnisotropicFilter(Context paramContext, float paramFloat) { Pipeline.getPipeline().updateTexture2DAnisotropicFilter(paramContext, paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1078 */   void updateTextureLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { Pipeline.getPipeline().updateTexture2DLodRange(paramContext, paramInt1, paramInt2, paramFloat1, paramFloat2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1086 */   void updateTextureLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { Pipeline.getPipeline().updateTexture2DLodOffset(paramContext, paramFloat1, paramFloat2, paramFloat3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1093 */   int getTextureId() { return VirtualUniverse.mc.getTexture2DId(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void freeTextureId(int paramInt) {
/* 1099 */     synchronized (this.resourceLock) {
/* 1100 */       if (this.objectId == paramInt) {
/* 1101 */         this.objectId = -1;
/* 1102 */         VirtualUniverse.mc.freeTexture2DId(paramInt);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean isEnabled(Canvas3D paramCanvas3D) {
/* 1108 */     if (this.widthOrHeightIsNPOT && !isUseAsRaster() && (paramCanvas3D.textureExtendedFeatures & 0x8000) == 0)
/*      */     {
/* 1110 */       return false;
/*      */     }
/* 1112 */     return this.enable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void bindTexture(Canvas3D paramCanvas3D) {
/* 1119 */     synchronized (this.resourceLock) {
/* 1120 */       if (this.objectId == -1) {
/* 1121 */         this.objectId = getTextureId();
/*      */       }
/* 1123 */       paramCanvas3D.addTextureResource(this.objectId, this);
/*      */     } 
/* 1125 */     bindTexture(paramCanvas3D.ctx, this.objectId, isEnabled(paramCanvas3D));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureDimensions(Canvas3D paramCanvas3D) {
/* 1134 */     if (this.images[false][false] != null) {
/* 1135 */       updateTextureImage(paramCanvas3D, 0, this.maxLevels, 0, this.format, this.images[0][0].getImageFormatTypeIntValue(false), this.width, this.height, this.boundaryWidth, this.images[0][0].getImageDataTypeIntValue(), null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureLOD(Canvas3D paramCanvas3D) {
/* 1145 */     if ((paramCanvas3D.textureExtendedFeatures & 0x1000) != 0) {
/*      */       
/* 1147 */       int i = 0;
/* 1148 */       if (this.mipmapMode == 1) {
/* 1149 */         i = this.maxMipMapLevels;
/*      */       } else {
/*      */         
/* 1152 */         i = this.maximumLevel;
/*      */       } 
/*      */       
/* 1155 */       updateTextureLodRange(paramCanvas3D.ctx, this.baseLevel, i, this.minimumLod, this.maximumLod);
/*      */     } 
/*      */ 
/*      */     
/* 1159 */     if (this.lodOffset != null && (paramCanvas3D.textureExtendedFeatures & 0x2000) != 0)
/*      */     {
/* 1161 */       updateTextureLodOffset(paramCanvas3D.ctx, this.lodOffset.x, this.lodOffset.y, this.lodOffset.z);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1168 */   void updateTextureBoundary(Canvas3D paramCanvas3D) { updateTextureBoundary(paramCanvas3D.ctx, this.boundaryModeS, this.boundaryModeT, this.boundaryColor.x, this.boundaryColor.y, this.boundaryColor.z, this.boundaryColor.w); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureFields(Canvas3D paramCanvas3D) {
/* 1176 */     int i = this.magFilter;
/* 1177 */     int j = this.minFilter;
/*      */ 
/*      */ 
/*      */     
/* 1181 */     if (i >= 9 && i <= 11) {
/*      */ 
/*      */       
/* 1184 */       if ((paramCanvas3D.textureExtendedFeatures & 0x100) != 0) {
/*      */ 
/*      */ 
/*      */         
/* 1188 */         updateTextureSharpenFunc(paramCanvas3D.ctx, this.numSharpenTextureFuncPts, this.sharpenTextureFuncPts);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 1196 */         i = 3;
/*      */       } 
/* 1198 */     } else if (i >= 6 && i <= 8) {
/*      */       
/* 1200 */       if ((paramCanvas3D.textureExtendedFeatures & 0x200) == 0)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1205 */         i = 3;
/*      */       }
/*      */     } 
/*      */     
/* 1209 */     if (j == 12 || i == 12) {
/*      */       
/* 1211 */       boolean bool = false;
/*      */       
/* 1213 */       if ((paramCanvas3D.textureExtendedFeatures & 0x400) != 0) {
/*      */         
/* 1215 */         if (this.filter4FuncPts == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1220 */           bool = true;
/*      */         } else {
/* 1222 */           updateTextureFilter4Func(paramCanvas3D.ctx, this.filter4FuncPts.length, this.filter4FuncPts);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1230 */         bool = true;
/*      */       } 
/*      */       
/* 1233 */       if (bool) {
/* 1234 */         if (j == 12) {
/* 1235 */           j = 3;
/*      */         }
/* 1237 */         if (i == 12) {
/* 1238 */           i = 3;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1244 */     if (this.mipmapMode == 1 && (paramCanvas3D.textureExtendedFeatures & 0x10000) == 0)
/*      */     {
/*      */       
/* 1247 */       if (j == 1 || j == 5) {
/*      */         
/* 1249 */         j = 3;
/* 1250 */       } else if (j == 4) {
/* 1251 */         j = 2;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1256 */     updateTextureFilterModes(paramCanvas3D.ctx, j, i);
/*      */ 
/*      */     
/* 1259 */     if ((paramCanvas3D.textureExtendedFeatures & 0x800) != 0)
/*      */     {
/* 1261 */       if (this.anisotropicFilterMode == 0) {
/* 1262 */         updateTextureAnisotropicFilter(paramCanvas3D.ctx, 1.0F);
/*      */       } else {
/* 1264 */         updateTextureAnisotropicFilter(paramCanvas3D.ctx, this.anisotropicFilterDegree);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1270 */     updateTextureBoundary(paramCanvas3D);
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
/* 1284 */   void updateTextureImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject) { Pipeline.getPipeline().updateTexture2DImage(paramCanvas3D.ctx, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramObject, useAutoMipMapGeneration(paramCanvas3D)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1301 */   void updateTextureSubImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject) { Pipeline.getPipeline().updateTexture2DSubImage(paramCanvas3D.ctx, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramInt12, paramObject, useAutoMipMapGeneration(paramCanvas3D)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reloadTextureImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, ImageComponentRetained paramImageComponentRetained, int paramInt3) {
/* 1318 */     boolean bool = isUseAsRaster();
/* 1319 */     ImageComponentRetained.ImageData imageData = paramImageComponentRetained.getImageData(bool);
/*      */     
/* 1321 */     assert imageData != null;
/*      */     
/* 1323 */     updateTextureImage(paramCanvas3D, paramInt1, paramInt3, paramInt2, this.format, paramImageComponentRetained.getImageFormatTypeIntValue(bool), imageData.getWidth(), imageData.getHeight(), this.boundaryWidth, paramImageComponentRetained.getImageDataTypeIntValue(), imageData.get());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1335 */     if (imageData == null) {
/*      */ 
/*      */       
/* 1338 */       int i = 0, j = 0;
/* 1339 */       int k = paramImageComponentRetained.width;
/* 1340 */       int m = paramImageComponentRetained.height;
/* 1341 */       int n = paramImageComponentRetained.tilew;
/* 1342 */       int i1 = paramImageComponentRetained.tileh;
/* 1343 */       int i2 = n;
/* 1344 */       int i3 = i1;
/*      */       
/* 1346 */       if (k < i2) {
/* 1347 */         i2 = k;
/*      */       }
/*      */       
/* 1350 */       if (m < i3) {
/* 1351 */         i3 = m;
/*      */       }
/*      */       
/* 1354 */       int i4 = i2;
/* 1355 */       int i5 = paramImageComponentRetained.tilew - i2;
/* 1356 */       int i6 = paramImageComponentRetained.tileh - i3;
/* 1357 */       for (byte b = 0; b < paramImageComponentRetained.numYTiles; b++) {
/* 1358 */         i = 0;
/* 1359 */         k = this.width;
/* 1360 */         i2 = i4;
/* 1361 */         i5 = paramImageComponentRetained.tilew - i2;
/* 1362 */         for (byte b1 = 0; b1 < paramImageComponentRetained.numXTiles; b1++) {
/*      */           
/* 1364 */           Raster raster = ((RenderedImage)paramImageComponentRetained.getRefImage(0)).getTile(b1, b);
/* 1365 */           byte[] arrayOfByte = ((DataBufferByte)raster.getDataBuffer()).getData();
/* 1366 */           updateTextureSubImage(paramCanvas3D, paramInt1, paramInt2, i, j, this.format, paramImageComponentRetained.getImageFormatTypeIntValue(false), i5, i6, paramImageComponentRetained.tilew, i2, i3, 4096, arrayOfByte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1374 */           i += i2;
/* 1375 */           i5 = 0;
/* 1376 */           k -= i2;
/* 1377 */           if (k < paramImageComponentRetained.tilew) {
/* 1378 */             i2 = k;
/*      */           } else {
/* 1380 */             i2 = paramImageComponentRetained.tilew;
/*      */           } 
/* 1382 */         }  j += i3;
/* 1383 */         i6 = 0;
/* 1384 */         m -= i3;
/* 1385 */         if (m < paramImageComponentRetained.tileh) {
/* 1386 */           i3 = m;
/*      */         } else {
/* 1388 */           i3 = paramImageComponentRetained.tileh;
/*      */         } 
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
/*      */   void reloadTextureSubImage(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, ImageComponentUpdateInfo paramImageComponentUpdateInfo, ImageComponentRetained paramImageComponentRetained) {
/* 1403 */     int i = paramImageComponentUpdateInfo.x;
/* 1404 */     int j = paramImageComponentUpdateInfo.y;
/* 1405 */     int k = paramImageComponentUpdateInfo.width;
/* 1406 */     int m = paramImageComponentUpdateInfo.height;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1415 */     ImageComponentRetained.ImageData imageData = paramImageComponentRetained.getImageData(isUseAsRaster());
/* 1416 */     if (imageData != null) {
/* 1417 */       int n = i;
/* 1418 */       int i1 = j;
/*      */ 
/*      */       
/* 1421 */       if (!paramImageComponentRetained.yUp) {
/* 1422 */         i1 = paramImageComponentRetained.height - i1 - m;
/*      */       }
/*      */       
/* 1425 */       updateTextureSubImage(paramCanvas3D, paramInt1, paramInt2, n, i1, this.format, paramImageComponentRetained.getImageFormatTypeIntValue(false), n, i1, paramImageComponentRetained.width, k, m, paramImageComponentRetained.getImageDataTypeIntValue(), imageData.get());
/*      */     } else {
/*      */       int i1, n;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       assert false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1444 */       int i2 = i;
/* 1445 */       int i3 = j;
/*      */       
/* 1447 */       float f = i2 / paramImageComponentRetained.tilew;
/* 1448 */       if (f < 0.0F) {
/* 1449 */         n = (int)(f - 1.0F);
/*      */       } else {
/* 1451 */         n = (int)f;
/*      */       } 
/*      */       
/* 1454 */       f = i3 / paramImageComponentRetained.tileh;
/* 1455 */       if (f < 0.0F) {
/* 1456 */         i1 = (int)(f - 1.0F);
/*      */       } else {
/* 1458 */         i1 = (int)f;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1463 */       int i4 = n * paramImageComponentRetained.tilew;
/* 1464 */       int i5 = i1 * paramImageComponentRetained.tilew;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1469 */       int i6 = i4 + paramImageComponentRetained.tilew - i2;
/* 1470 */       int i7 = i5 + paramImageComponentRetained.tileh - i3;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1475 */       if (i6 > k) {
/* 1476 */         i6 = k;
/*      */       }
/*      */       
/* 1479 */       if (i7 > m) {
/* 1480 */         i7 = m;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1485 */       int i8 = i6;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1490 */       int i9 = k;
/* 1491 */       int i10 = m;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1497 */       int i11 = i2 - i4;
/* 1498 */       int i12 = i3 - i5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1504 */       int i13 = (k + i11) / paramImageComponentRetained.tilew;
/* 1505 */       int i14 = (m + i12) / paramImageComponentRetained.tileh;
/*      */       
/* 1507 */       if ((k + i11) % paramImageComponentRetained.tilew > 0.0F) {
/* 1508 */         i13++;
/*      */       }
/*      */       
/* 1511 */       if ((m + i12) % paramImageComponentRetained.tileh > 0.0F) {
/* 1512 */         i14++;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1517 */       int i15 = i;
/* 1518 */       int i16 = j;
/*      */       
/* 1520 */       for (int i17 = i1; i17 < i1 + i14; 
/* 1521 */         i17++) {
/*      */         
/* 1523 */         i9 = k;
/* 1524 */         i6 = i8;
/* 1525 */         i11 = i2 - i4;
/*      */         
/* 1527 */         for (int i18 = n; i18 < n + i13; 
/* 1528 */           i18++) {
/* 1529 */           Raster raster = ((RenderedImage)paramImageComponentRetained.getRefImage(0)).getTile(i18, i17);
/* 1530 */           byte[] arrayOfByte = ((DataBufferByte)raster.getDataBuffer()).getData();
/*      */           
/* 1532 */           updateTextureSubImage(paramCanvas3D, paramInt1, paramInt2, i15, i16, this.format, paramImageComponentRetained.getImageFormatTypeIntValue(false), i11, i12, paramImageComponentRetained.tilew, i6, i7, 4096, arrayOfByte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1542 */           i15 += i6;
/* 1543 */           i11 = 0;
/*      */ 
/*      */ 
/*      */           
/* 1547 */           i9 -= i6;
/* 1548 */           if (i9 < paramImageComponentRetained.tilew) {
/* 1549 */             i6 = i9;
/*      */           } else {
/* 1551 */             i6 = paramImageComponentRetained.tilew;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1556 */         i16 += i7;
/* 1557 */         i12 = 0;
/*      */ 
/*      */ 
/*      */         
/* 1561 */         i10 -= i7;
/* 1562 */         if (i10 < paramImageComponentRetained.tileh) {
/* 1563 */           i7 = i10;
/*      */         } else {
/* 1565 */           i7 = paramImageComponentRetained.tileh;
/*      */         } 
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
/*      */   void reloadTexture(Canvas3D paramCanvas3D) {
/*      */     int j;
/*      */     int i;
/* 1582 */     if ((paramCanvas3D.textureExtendedFeatures & 0x1000) == 0) {
/* 1583 */       i = 0;
/* 1584 */       j = this.maxLevels - 1;
/*      */     } else {
/* 1586 */       i = this.baseLevel;
/* 1587 */       j = this.maximumLevel;
/*      */     } 
/*      */     
/* 1590 */     if (i != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1595 */       updateTextureDimensions(paramCanvas3D);
/*      */     }
/*      */     
/* 1598 */     for (byte b = 0; b < this.numFaces; b++) {
/* 1599 */       for (int k = i; k <= j; k++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1605 */         ImageComponentRetained imageComponentRetained = this.images[b][k];
/* 1606 */         if (imageComponentRetained != null) {
/*      */ 
/*      */           
/* 1609 */           imageComponentRetained.evaluateExtensions(paramCanvas3D);
/* 1610 */           reloadTextureImage(paramCanvas3D, b, k, imageComponentRetained, this.maxLevels);
/*      */         } 
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
/*      */   void updateTexture(Canvas3D paramCanvas3D, int paramInt) {
/* 1625 */     for (byte b = 0; b < this.numFaces; b++) {
/* 1626 */       for (int i = this.baseLevel; i <= this.maximumLevel; i++) {
/* 1627 */         if (this.imageUpdateInfo[b][i] != null) {
/* 1628 */           for (byte b1 = 0; b1 < this.imageUpdateInfo[b][i].size(); b1++) {
/*      */             
/* 1630 */             ImageComponentUpdateInfo imageComponentUpdateInfo = (ImageComponentUpdateInfo)this.imageUpdateInfo[b][i].get(b1);
/*      */ 
/*      */ 
/*      */             
/* 1634 */             synchronized (this.resourceLock) {
/*      */ 
/*      */ 
/*      */               
/* 1638 */               if ((imageComponentUpdateInfo.updateMask & paramInt) == 0) {
/*      */ 
/*      */               
/*      */               } else {
/*      */                 
/* 1643 */                 imageComponentUpdateInfo.updateMask &= (paramInt ^ 0xFFFFFFFF);
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1648 */                 if ((imageComponentUpdateInfo.updateMask & this.resourceCreationMask) == 0) {
/*      */                   
/* 1650 */                   imageComponentUpdateInfo.updateMask = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1661 */                   if (this.imageUpdatePruneMask == null) {
/* 1662 */                     this.imageUpdatePruneMask = new int[this.numFaces];
/*      */                   }
/* 1664 */                   this.imageUpdatePruneMask[b] = 1 << i;
/*      */                 } 
/*      */ 
/*      */                 
/* 1668 */                 if (imageComponentUpdateInfo.entireImage == true) {
/* 1669 */                   reloadTextureImage(paramCanvas3D, b, i, this.images[b][i], this.maxLevels);
/*      */                 } else {
/*      */                   
/* 1672 */                   reloadTextureSubImage(paramCanvas3D, b, i, imageComponentUpdateInfo, this.images[b][i]);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
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
/*      */ 
/*      */   
/*      */   void reloadTextureSharedContext(Canvas3D paramCanvas3D) {
/* 1694 */     if (!isEnabled(paramCanvas3D)) {
/*      */       return;
/*      */     }
/*      */     
/* 1698 */     bindTexture(paramCanvas3D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1704 */     updateTextureFields(paramCanvas3D);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1709 */     updateTextureLOD(paramCanvas3D);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1714 */     reloadTexture(paramCanvas3D);
/*      */     
/* 1716 */     synchronized (this.resourceLock) {
/* 1717 */       this.resourceCreationMask |= paramCanvas3D.screen.renderer.rendererBit;
/* 1718 */       this.resourceUpdatedMask |= paramCanvas3D.screen.renderer.rendererBit;
/* 1719 */       this.resourceLodUpdatedMask |= paramCanvas3D.screen.renderer.rendererBit;
/* 1720 */       this.resourceInReloadList &= (paramCanvas3D.screen.renderer.rendererBit ^ 0xFFFFFFFF);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateNative(Canvas3D paramCanvas3D) {
/* 1730 */     boolean bool1 = false;
/* 1731 */     boolean bool2 = false;
/* 1732 */     boolean bool3 = false;
/*      */ 
/*      */ 
/*      */     
/* 1736 */     bindTexture(paramCanvas3D);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1741 */     if (!isEnabled(paramCanvas3D)) {
/*      */       return;
/*      */     }
/*      */     
/* 1745 */     if (paramCanvas3D.useSharedCtx && paramCanvas3D.screen.renderer.sharedCtx != null) {
/*      */       
/* 1747 */       if ((this.resourceCreationMask & paramCanvas3D.screen.renderer.rendererBit) == 0) {
/* 1748 */         bool1 = true;
/*      */       } else {
/* 1750 */         if ((this.resourceUpdatedMask & paramCanvas3D.screen.renderer.rendererBit) == 0 && this.imageUpdateInfo != null)
/*      */         {
/*      */           
/* 1753 */           bool2 = true;
/*      */         }
/*      */         
/* 1756 */         if ((this.resourceLodUpdatedMask & paramCanvas3D.screen.renderer.rendererBit) == 0)
/*      */         {
/* 1758 */           bool3 = true;
/*      */         }
/*      */       } 
/* 1761 */       if (bool1 || bool2 || bool3) {
/* 1762 */         paramCanvas3D.makeCtxCurrent(paramCanvas3D.screen.renderer.sharedCtx);
/* 1763 */         bindTexture(paramCanvas3D);
/*      */       }
/*      */     
/* 1766 */     } else if ((this.resourceCreationMask & paramCanvas3D.canvasBit) == 0) {
/* 1767 */       bool1 = true;
/*      */     } else {
/* 1769 */       if ((this.resourceUpdatedMask & paramCanvas3D.canvasBit) == 0 && this.imageUpdateInfo != null)
/*      */       {
/* 1771 */         bool2 = true;
/*      */       }
/*      */       
/* 1774 */       if ((this.resourceLodUpdatedMask & paramCanvas3D.canvasBit) == 0) {
/* 1775 */         bool3 = true;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1781 */     if (VirtualUniverse.mc.isD3D()) {
/* 1782 */       if (this.texTimestamp != VirtualUniverse.mc.resendTexTimestamp) {
/* 1783 */         this.texTimestamp = VirtualUniverse.mc.resendTexTimestamp;
/* 1784 */         bool1 = true;
/*      */       } 
/*      */       
/* 1787 */       if (!bool1)
/*      */       {
/* 1789 */         updateTextureFields(paramCanvas3D);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1801 */     if (bool1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1807 */       updateTextureFields(paramCanvas3D);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1812 */       updateTextureLOD(paramCanvas3D);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1817 */       reloadTexture(paramCanvas3D);
/*      */ 
/*      */       
/* 1820 */       if (paramCanvas3D.useSharedCtx) {
/* 1821 */         paramCanvas3D.makeCtxCurrent(paramCanvas3D.ctx);
/* 1822 */         synchronized (this.resourceLock) {
/* 1823 */           this.resourceCreationMask |= paramCanvas3D.screen.renderer.rendererBit;
/* 1824 */           this.resourceUpdatedMask |= paramCanvas3D.screen.renderer.rendererBit;
/* 1825 */           this.resourceLodUpdatedMask |= paramCanvas3D.screen.renderer.rendererBit;
/*      */         } 
/*      */       } else {
/*      */         
/* 1829 */         synchronized (this.resourceLock) {
/* 1830 */           this.resourceCreationMask |= paramCanvas3D.canvasBit;
/* 1831 */           this.resourceUpdatedMask |= paramCanvas3D.canvasBit;
/* 1832 */           this.resourceLodUpdatedMask |= paramCanvas3D.canvasBit;
/*      */         } 
/*      */       } 
/* 1835 */     } else if (bool3 || bool2) {
/*      */       
/* 1837 */       if (bool3) {
/* 1838 */         updateTextureLOD(paramCanvas3D);
/*      */       }
/*      */       
/* 1841 */       if (bool2) {
/*      */ 
/*      */ 
/*      */         
/* 1845 */         int i = 0;
/*      */         
/* 1847 */         if (paramCanvas3D.useSharedCtx) {
/* 1848 */           i = paramCanvas3D.screen.renderer.rendererBit;
/*      */         } else {
/* 1850 */           i = paramCanvas3D.canvasBit;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1855 */         updateTexture(paramCanvas3D, i);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1861 */       if (paramCanvas3D.useSharedCtx) {
/* 1862 */         paramCanvas3D.makeCtxCurrent(paramCanvas3D.ctx);
/* 1863 */         synchronized (this.resourceLock) {
/* 1864 */           this.resourceUpdatedMask |= paramCanvas3D.screen.renderer.rendererBit;
/* 1865 */           this.resourceLodUpdatedMask |= paramCanvas3D.screen.renderer.rendererBit;
/*      */         } 
/*      */       } else {
/* 1868 */         synchronized (this.resourceLock) {
/* 1869 */           this.resourceUpdatedMask |= paramCanvas3D.canvasBit;
/* 1870 */           this.resourceLodUpdatedMask |= paramCanvas3D.canvasBit;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void createMirrorObject() {
/* 1877 */     if (this.mirror == null) {
/* 1878 */       if (this instanceof Texture3DRetained) {
/* 1879 */         Texture3DRetained texture3DRetained = (Texture3DRetained)this;
/* 1880 */         Texture3D texture3D = new Texture3D(texture3DRetained.mipmapMode, texture3DRetained.format, texture3DRetained.width, texture3DRetained.height, texture3DRetained.depth, texture3DRetained.boundaryWidth);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1886 */         this.mirror = (Texture3DRetained)texture3D.retained;
/*      */       }
/* 1888 */       else if (this instanceof TextureCubeMapRetained) {
/* 1889 */         TextureCubeMap textureCubeMap = new TextureCubeMap(this.mipmapMode, this.format, this.width, this.boundaryWidth);
/*      */ 
/*      */         
/* 1892 */         this.mirror = (TextureCubeMapRetained)textureCubeMap.retained;
/*      */       } else {
/*      */         
/* 1895 */         Texture2D texture2D = new Texture2D(this.mipmapMode, this.format, this.width, this.height, this.boundaryWidth);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1900 */         this.mirror = (Texture2DRetained)texture2D.retained;
/*      */       } 
/*      */       
/* 1903 */       ((TextureRetained)this.mirror).objectId = -1;
/*      */     } 
/* 1905 */     initMirrorObject();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initMirrorObject() {
/* 1914 */     this.mirror.source = this.source;
/* 1915 */     if (this instanceof Texture3DRetained) {
/* 1916 */       Texture3DRetained texture3DRetained = (Texture3DRetained)this;
/*      */       
/* 1918 */       ((Texture3DRetained)this.mirror).boundaryModeR = texture3DRetained.boundaryModeR;
/* 1919 */       ((Texture3DRetained)this.mirror).depth = texture3DRetained.depth;
/*      */     } 
/* 1921 */     TextureRetained textureRetained = (TextureRetained)this.mirror;
/*      */     
/* 1923 */     textureRetained.boundaryModeS = this.boundaryModeS;
/* 1924 */     textureRetained.boundaryModeT = this.boundaryModeT;
/* 1925 */     textureRetained.minFilter = this.minFilter;
/* 1926 */     textureRetained.magFilter = this.magFilter;
/* 1927 */     textureRetained.boundaryColor.set(this.boundaryColor);
/* 1928 */     textureRetained.enable = this.enable;
/* 1929 */     textureRetained.userSpecifiedEnable = this.enable;
/* 1930 */     textureRetained.enable = this.enable;
/* 1931 */     textureRetained.numFaces = this.numFaces;
/* 1932 */     textureRetained.resourceCreationMask = 0;
/* 1933 */     textureRetained.resourceUpdatedMask = 0;
/* 1934 */     textureRetained.resourceLodUpdatedMask = 0;
/* 1935 */     textureRetained.resourceInReloadList = 0;
/*      */ 
/*      */     
/* 1938 */     textureRetained.baseLevel = this.baseLevel;
/* 1939 */     textureRetained.maximumLevel = this.maximumLevel;
/* 1940 */     textureRetained.minimumLod = this.minimumLod;
/* 1941 */     textureRetained.maximumLod = this.maximumLod;
/* 1942 */     textureRetained.lodOffset = this.lodOffset;
/*      */ 
/*      */ 
/*      */     
/* 1946 */     textureRetained.numSharpenTextureFuncPts = this.numSharpenTextureFuncPts;
/* 1947 */     if (this.sharpenTextureFuncPts == null) {
/* 1948 */       textureRetained.sharpenTextureFuncPts = null;
/*      */     } else {
/* 1950 */       if (textureRetained.sharpenTextureFuncPts == null || textureRetained.sharpenTextureFuncPts.length != this.sharpenTextureFuncPts.length)
/*      */       {
/*      */         
/* 1953 */         textureRetained.sharpenTextureFuncPts = new float[this.sharpenTextureFuncPts.length];
/*      */       }
/*      */       
/* 1956 */       for (byte b = 0; b < this.sharpenTextureFuncPts.length; b++) {
/* 1957 */         textureRetained.sharpenTextureFuncPts[b] = this.sharpenTextureFuncPts[b];
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1963 */     if (this.filter4FuncPts == null) {
/* 1964 */       textureRetained.filter4FuncPts = null;
/*      */     } else {
/* 1966 */       if (textureRetained.filter4FuncPts == null || textureRetained.filter4FuncPts.length != this.filter4FuncPts.length)
/*      */       {
/*      */         
/* 1969 */         textureRetained.filter4FuncPts = new float[this.filter4FuncPts.length];
/*      */       }
/*      */       
/* 1972 */       for (byte b = 0; b < this.filter4FuncPts.length; b++) {
/* 1973 */         textureRetained.filter4FuncPts[b] = this.filter4FuncPts[b];
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1979 */     textureRetained.anisotropicFilterMode = this.anisotropicFilterMode;
/* 1980 */     textureRetained.anisotropicFilterDegree = this.anisotropicFilterDegree;
/*      */     
/* 1982 */     textureRetained.maxLevels = this.maxLevels;
/* 1983 */     if (this.images != null)
/*      */     {
/* 1985 */       for (byte b = 0; b < this.numFaces; b++) {
/* 1986 */         for (byte b1 = 0; b1 < this.maxLevels; b1++) {
/* 1987 */           textureRetained.images[b][b1] = this.images[b][b1];
/*      */ 
/*      */           
/* 1990 */           if (this.images[b][b1] != null) {
/* 1991 */             this.images[b][b1].addUser(textureRetained);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   boolean useAutoMipMapGeneration(Canvas3D paramCanvas3D) {
/* 1999 */     if (this.mipmapMode == 1 && (this.minFilter == 1 || this.minFilter == 4 || this.minFilter == 5) && (paramCanvas3D.textureExtendedFeatures & 0x10000) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2005 */       return true;
/*      */     }
/*      */     
/* 2008 */     return false;
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
/*      */   void pruneImageUpdateInfo() {
/* 2021 */     for (byte b = 0; b < this.numFaces; b++) {
/* 2022 */       for (int i = this.baseLevel; i <= this.maximumLevel; i++) {
/* 2023 */         if ((this.imageUpdatePruneMask[b] & 1 << i) != 0) {
/* 2024 */           if (this.imageUpdateInfo[b][i] != null) {
/* 2025 */             for (byte b1 = 0; b1 < this.imageUpdateInfo[b][i].size(); b1++) {
/* 2026 */               ImageComponentUpdateInfo imageComponentUpdateInfo = (ImageComponentUpdateInfo)this.imageUpdateInfo[b][i].get(b1);
/*      */               
/* 2028 */               if (imageComponentUpdateInfo.updateMask == 0)
/*      */               {
/*      */                 
/* 2031 */                 this.imageUpdateInfo[b][i].remove(b1);
/*      */               }
/*      */             } 
/*      */           }
/* 2035 */           this.imageUpdatePruneMask[b] = this.imageUpdatePruneMask[b] & (1 << i ^ 0xFFFFFFFF);
/*      */         } 
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
/*      */   
/*      */   void addImageUpdateInfo(int paramInt1, int paramInt2, ImageComponentUpdateInfo paramImageComponentUpdateInfo) {
/* 2052 */     if (this.imageUpdateInfo == null) {
/* 2053 */       this.imageUpdateInfo = new ArrayList[this.numFaces][this.maxLevels];
/*      */     }
/*      */     
/* 2056 */     if (this.imageUpdateInfo[paramInt2][paramInt1] == null) {
/* 2057 */       this.imageUpdateInfo[paramInt2][paramInt1] = new ArrayList();
/*      */     }
/*      */     
/* 2060 */     ImageComponentUpdateInfo imageComponentUpdateInfo = new ImageComponentUpdateInfo();
/*      */ 
/*      */     
/* 2063 */     if (paramImageComponentUpdateInfo == null) {
/*      */       
/* 2065 */       imageComponentUpdateInfo.entireImage = true;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 2073 */       imageComponentUpdateInfo.entireImage = false;
/*      */     } 
/*      */     
/* 2076 */     if (imageComponentUpdateInfo.entireImage) {
/*      */ 
/*      */       
/* 2079 */       this.imageUpdateInfo[paramInt2][paramInt1].clear();
/*      */ 
/*      */       
/* 2082 */       if (this.imageUpdatePruneMask != null) {
/* 2083 */         this.imageUpdatePruneMask[paramInt2] = this.imageUpdatePruneMask[paramInt2] & (1 << paramInt1 ^ 0xFFFFFFFF);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 2088 */       imageComponentUpdateInfo.x = paramImageComponentUpdateInfo.x;
/* 2089 */       imageComponentUpdateInfo.y = paramImageComponentUpdateInfo.y;
/* 2090 */       imageComponentUpdateInfo.z = paramImageComponentUpdateInfo.z;
/* 2091 */       imageComponentUpdateInfo.width = paramImageComponentUpdateInfo.width;
/* 2092 */       imageComponentUpdateInfo.height = paramImageComponentUpdateInfo.height;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2098 */     imageComponentUpdateInfo.updateMask = this.resourceCreationMask;
/*      */ 
/*      */     
/* 2101 */     this.imageUpdateInfo[paramInt2][paramInt1].add(imageComponentUpdateInfo);
/*      */ 
/*      */     
/* 2104 */     if (this.imageUpdatePruneMask != null) {
/* 2105 */       pruneImageUpdateInfo();
/*      */     }
/*      */   }
/*      */   
/*      */   void validate() {
/* 2110 */     this.enable = true;
/* 2111 */     for (byte b = 0; b < this.numFaces && this.enable; b++) {
/* 2112 */       for (int i = this.baseLevel; i <= this.maximumLevel && this.enable; i++) {
/* 2113 */         if (this.images[b][i] == null) {
/* 2114 */           this.enable = false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 2126 */     TextureRetained textureRetained = (TextureRetained)this.mirror;
/*      */     
/* 2128 */     if ((paramInt & true) != 0) {
/* 2129 */       textureRetained.enable = ((Boolean)paramObject).booleanValue();
/*      */     }
/* 2131 */     else if ((paramInt & 0x4) != 0) {
/*      */       
/* 2133 */       Object[] arrayOfObject = (Object[])paramObject;
/* 2134 */       int i = ((Integer)arrayOfObject[0]).intValue();
/* 2135 */       ImageComponent imageComponent = (ImageComponent)arrayOfObject[1];
/* 2136 */       int j = ((Integer)arrayOfObject[2]).intValue();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2141 */       if (textureRetained.images[j][i] != null) {
/* 2142 */         textureRetained.images[j][i].removeUser(this.mirror);
/*      */       }
/*      */ 
/*      */       
/* 2146 */       if (imageComponent == null) {
/* 2147 */         textureRetained.images[j][i] = null;
/*      */       } else {
/*      */         
/* 2150 */         textureRetained.images[j][i] = (ImageComponentRetained)imageComponent.retained;
/*      */         
/* 2152 */         textureRetained.images[j][i].addUser(this.mirror);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2164 */       textureRetained.resourceUpdatedMask = 0;
/*      */ 
/*      */       
/* 2167 */       textureRetained.addImageUpdateInfo(i, j, null);
/*      */     }
/* 2169 */     else if ((paramInt & 0x20) != 0) {
/*      */       
/* 2171 */       Object[] arrayOfObject = (Object[])paramObject;
/* 2172 */       ImageComponent[] arrayOfImageComponent = (ImageComponent[])arrayOfObject[0];
/* 2173 */       int i = ((Integer)arrayOfObject[1]).intValue();
/*      */       
/* 2175 */       for (byte b = 0; b < arrayOfImageComponent.length; b++) {
/*      */ 
/*      */ 
/*      */         
/* 2179 */         if (textureRetained.images[i][b] != null) {
/* 2180 */           textureRetained.images[i][b].removeUser(this.mirror);
/*      */         }
/*      */ 
/*      */         
/* 2184 */         if (arrayOfImageComponent[b] == null) {
/* 2185 */           textureRetained.images[i][b] = null;
/*      */         } else {
/* 2187 */           textureRetained.images[i][b] = (ImageComponentRetained)(arrayOfImageComponent[b]).retained;
/*      */           
/* 2189 */           textureRetained.images[i][b].addUser(this.mirror);
/*      */         } 
/*      */       } 
/* 2192 */       textureRetained.updateResourceCreationMask();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2200 */     else if ((paramInt & 0x40) != 0) {
/* 2201 */       int i = ((Integer)paramObject).intValue();
/*      */       
/* 2203 */       if (i < textureRetained.baseLevel) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2208 */         for (byte b = 0; b < this.numFaces; b++) {
/* 2209 */           for (int j = i; j < textureRetained.baseLevel; j++) {
/*      */             
/* 2211 */             if (textureRetained.images[b][j] == null) {
/* 2212 */               textureRetained.enable = false;
/*      */             } else {
/* 2214 */               textureRetained.addImageUpdateInfo(j, b, null);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 2219 */         textureRetained.baseLevel = i;
/*      */ 
/*      */         
/* 2222 */         textureRetained.resourceUpdatedMask = 0;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2227 */         textureRetained.baseLevel = i;
/*      */         
/* 2229 */         if (this.userSpecifiedEnable && !textureRetained.enable)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2237 */           textureRetained.validate();
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2242 */       textureRetained.resourceLodUpdatedMask = 0;
/*      */     }
/* 2244 */     else if ((paramInt & 0x80) != 0) {
/* 2245 */       int i = ((Integer)paramObject).intValue();
/*      */       
/* 2247 */       if (i > textureRetained.maximumLevel) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2252 */         for (byte b = 0; b < this.numFaces; b++) {
/* 2253 */           for (int j = textureRetained.maximumLevel; j < i; j++) {
/*      */             
/* 2255 */             if (textureRetained.images[b][j] == null) {
/* 2256 */               textureRetained.enable = false;
/*      */             } else {
/* 2258 */               textureRetained.addImageUpdateInfo(j, b, null);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 2263 */         textureRetained.maximumLevel = i;
/*      */ 
/*      */         
/* 2266 */         textureRetained.resourceUpdatedMask = 0;
/*      */       }
/*      */       else {
/*      */         
/* 2270 */         textureRetained.maximumLevel = i;
/*      */         
/* 2272 */         if (this.userSpecifiedEnable && !textureRetained.enable)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2280 */           textureRetained.validate();
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2285 */       textureRetained.resourceLodUpdatedMask = 0;
/*      */     }
/* 2287 */     else if ((paramInt & 0x100) != 0) {
/* 2288 */       textureRetained.minimumLod = ((Float)paramObject).floatValue();
/*      */ 
/*      */       
/* 2291 */       textureRetained.resourceLodUpdatedMask = 0;
/*      */     }
/* 2293 */     else if ((paramInt & 0x200) != 0) {
/* 2294 */       textureRetained.maximumLod = ((Float)paramObject).floatValue();
/*      */ 
/*      */       
/* 2297 */       textureRetained.resourceLodUpdatedMask = 0;
/*      */     }
/* 2299 */     else if ((paramInt & 0x400) != 0) {
/* 2300 */       if (textureRetained.lodOffset == null) {
/* 2301 */         textureRetained.lodOffset = new Point3f((Point3f)paramObject);
/*      */       } else {
/*      */         
/* 2304 */         textureRetained.lodOffset.set((Point3f)paramObject);
/*      */       } 
/*      */ 
/*      */       
/* 2308 */       textureRetained.resourceLodUpdatedMask = 0;
/*      */     }
/* 2310 */     else if ((paramInt & 0x10) != 0) {
/* 2311 */       textureRetained.updateResourceCreationMask();
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
/*      */   void notifyImageComponentImageChanged(ImageComponentRetained paramImageComponentRetained, ImageComponentUpdateInfo paramImageComponentUpdateInfo) {
/* 2331 */     if (this.resourceCreationMask == 0) {
/*      */       
/* 2333 */       if (this.imageUpdateInfo != null)
/*      */       {
/*      */ 
/*      */         
/* 2337 */         for (byte b1 = 0; b1 < this.numFaces; b1++) {
/* 2338 */           for (byte b2 = 0; b2 < this.maxLevels; b2++) {
/* 2339 */             if (this.imageUpdateInfo[b1][b2] != null) {
/* 2340 */               this.imageUpdateInfo[b1][b2].clear();
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 2345 */           if (this.imageUpdatePruneMask != null) {
/* 2346 */             this.imageUpdatePruneMask[b1] = 0;
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2359 */     for (byte b = 0; b < this.numFaces; b++) {
/*      */       
/* 2361 */       boolean bool = false;
/* 2362 */       for (int i = this.baseLevel; i <= this.maximumLevel && !bool; i++) {
/* 2363 */         if (this.images[b][i] == paramImageComponentRetained) {
/*      */ 
/*      */ 
/*      */           
/* 2367 */           this.resourceUpdatedMask = 0;
/*      */ 
/*      */           
/* 2370 */           addImageUpdateInfo(i, b, paramImageComponentUpdateInfo);
/*      */ 
/*      */ 
/*      */           
/* 2374 */           bool = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2385 */   void updateResourceCreationMask() { this.resourceCreationMask = 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void incTextureBinRefCount(TextureBin paramTextureBin) {
/* 2392 */     setTextureBinRefCount(paramTextureBin, getTextureBinRefCount(paramTextureBin) + 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2399 */     for (byte b = 0; b < this.numFaces; b++) {
/* 2400 */       for (byte b1 = 0; b1 < this.maxLevels; b1++) {
/* 2401 */         ImageComponentRetained imageComponentRetained = this.images[b][b1];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2408 */         if (imageComponentRetained != null && (imageComponentRetained.isByReference() || (imageComponentRetained.source != null && imageComponentRetained.source.getCapability(3))))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 2413 */           paramTextureBin.renderBin.addNodeComponent(imageComponentRetained);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void decTextureBinRefCount(TextureBin paramTextureBin) {
/* 2423 */     setTextureBinRefCount(paramTextureBin, getTextureBinRefCount(paramTextureBin) - 1);
/*      */ 
/*      */ 
/*      */     
/* 2427 */     for (byte b = 0; b < this.numFaces; b++) {
/* 2428 */       for (byte b1 = 0; b1 < this.maxLevels; b1++) {
/* 2429 */         ImageComponentRetained imageComponentRetained = this.images[b][b1];
/* 2430 */         if (imageComponentRetained != null && (imageComponentRetained.isByReference() || (imageComponentRetained.source != null && imageComponentRetained.source.getCapability(3))))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 2435 */           paramTextureBin.renderBin.removeNodeComponent(imageComponentRetained);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   final void sendMessage(int paramInt, Object paramObject) {
/* 2444 */     ArrayList arrayList1 = new ArrayList();
/* 2445 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*      */ 
/*      */ 
/*      */     
/* 2449 */     J3dMessage j3dMessage = new J3dMessage();
/* 2450 */     j3dMessage.threads = 1024;
/* 2451 */     j3dMessage.type = 15;
/* 2452 */     j3dMessage.universe = null;
/* 2453 */     j3dMessage.args[0] = this;
/* 2454 */     j3dMessage.args[1] = new Integer(paramInt);
/* 2455 */     j3dMessage.args[2] = paramObject;
/* 2456 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 2457 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */ 
/*      */     
/* 2460 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 2461 */       j3dMessage = new J3dMessage();
/* 2462 */       j3dMessage.threads = 128;
/* 2463 */       j3dMessage.type = 15;
/*      */       
/* 2465 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 2466 */       j3dMessage.args[0] = this;
/* 2467 */       j3dMessage.args[1] = new Integer(paramInt);
/* 2468 */       j3dMessage.args[2] = paramObject;
/*      */       
/* 2470 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 2471 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 2472 */       arrayList.toArray(arrayOfGeometryAtom);
/* 2473 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*      */       
/* 2475 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void handleFrequencyChange(int paramInt) {
/* 2481 */     switch (paramInt) {
/*      */       case 1:
/*      */       case 7:
/*      */       case 11:
/* 2485 */         setFrequencyChangeMask(paramInt, paramInt);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2493 */   void setUseAsRaster(boolean paramBoolean) { this.useAsRaster = paramBoolean; }
/*      */ 
/*      */ 
/*      */   
/* 2497 */   boolean isUseAsRaster() { return this.useAsRaster; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTextureBinRefCount(TextureBin paramTextureBin) {
/* 2506 */     Integer integer = (Integer)this.textureBinRefCount.get(paramTextureBin.renderBin);
/* 2507 */     return (integer == null) ? 0 : integer.intValue();
/*      */   }
/*      */   
/*      */   private void setTextureBinRefCount(TextureBin paramTextureBin, int paramInt) {
/* 2511 */     if (paramInt == 0) {
/* 2512 */       this.textureBinRefCount.remove(paramTextureBin.renderBin);
/*      */     } else {
/* 2514 */       this.textureBinRefCount.put(paramTextureBin.renderBin, new Integer(paramInt));
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TextureRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */