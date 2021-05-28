/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Color4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TextureAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int TRANSFORM_CHANGED = 1;
/*     */   static final int MODE_CHANGED = 2;
/*     */   static final int COLOR_CHANGED = 4;
/*     */   static final int CORRECTION_CHANGED = 8;
/*     */   static final int TEXTURE_COLOR_TABLE_CHANGED = 16;
/*     */   static final int COMBINE_RGB_MODE_CHANGED = 32;
/*     */   static final int COMBINE_ALPHA_MODE_CHANGED = 64;
/*     */   static final int COMBINE_RGB_SRC_CHANGED = 128;
/*     */   static final int COMBINE_ALPHA_SRC_CHANGED = 256;
/*     */   static final int COMBINE_RGB_FCN_CHANGED = 512;
/*     */   static final int COMBINE_ALPHA_FCN_CHANGED = 1024;
/*     */   static final int COMBINE_RGB_SCALE_CHANGED = 2048;
/*     */   static final int COMBINE_ALPHA_SCALE_CHANGED = 4096;
/*  41 */   static Integer[] commandInt = null;
/*     */ 
/*     */   
/*  44 */   static Integer[] enums = null;
/*     */ 
/*     */   
/*     */   Transform3D transform;
/*     */ 
/*     */   
/*     */   int textureMode;
/*     */ 
/*     */   
/*     */   Color4f textureBlendColor;
/*     */ 
/*     */   
/*     */   int[] textureColorTable;
/*     */ 
/*     */   
/*     */   int numTextureColorTableComponents;
/*     */ 
/*     */   
/*     */   int textureColorTableSize;
/*     */ 
/*     */   
/*     */   int combineRgbMode;
/*     */ 
/*     */   
/*     */   int combineAlphaMode;
/*     */ 
/*     */   
/*     */   int[] combineRgbSrc;
/*     */ 
/*     */   
/*     */   int[] combineAlphaSrc;
/*     */   
/*     */   int[] combineRgbFcn;
/*     */   
/*     */   int[] combineAlphaFcn;
/*     */   
/*     */   int combineRgbScale;
/*     */   
/*     */   int combineAlphaScale;
/*     */   
/*     */   int perspCorrectionMode;
/*     */   
/*     */   boolean mirrorCompDirty;
/*     */ 
/*     */   
/*     */   static final void initTextureEnums() {
/*  90 */     if (enums == null) {
/*  91 */       enums = new Integer[10];
/*  92 */       for (byte b = 0; b < 10; b++)
/*  93 */         enums[b] = new Integer(b); 
/*     */     }  } TextureAttributesRetained() { this.transform = new Transform3D(); this.textureMode = 5; this.textureBlendColor = new Color4f(0.0F, 0.0F, 0.0F, 0.0F); this.textureColorTable = null; this.numTextureColorTableComponents = 0; this.textureColorTableSize = 0; this.combineRgbMode = 1; this.combineAlphaMode = 1; this.combineRgbSrc = null; this.combineAlphaSrc = null; this.combineRgbFcn = null;
/*     */     this.combineAlphaFcn = null;
/*     */     this.combineRgbScale = 1;
/*     */     this.combineAlphaScale = 1;
/*     */     this.perspCorrectionMode = 1;
/*     */     this.mirrorCompDirty = false;
/* 100 */     initTextureEnums(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initCombineMode(TextureAttributesRetained paramTextureAttributesRetained) {
/* 108 */     paramTextureAttributesRetained.combineRgbSrc = new int[3];
/* 109 */     paramTextureAttributesRetained.combineAlphaSrc = new int[3];
/* 110 */     paramTextureAttributesRetained.combineRgbFcn = new int[3];
/* 111 */     paramTextureAttributesRetained.combineAlphaFcn = new int[3];
/*     */ 
/*     */ 
/*     */     
/* 115 */     paramTextureAttributesRetained.combineRgbSrc[0] = 1;
/* 116 */     paramTextureAttributesRetained.combineRgbSrc[1] = 3;
/* 117 */     paramTextureAttributesRetained.combineRgbSrc[2] = 2;
/*     */     
/* 119 */     paramTextureAttributesRetained.combineAlphaSrc[0] = 1;
/* 120 */     paramTextureAttributesRetained.combineAlphaSrc[1] = 3;
/* 121 */     paramTextureAttributesRetained.combineAlphaSrc[2] = 2;
/*     */     
/* 123 */     paramTextureAttributesRetained.combineRgbFcn[0] = 0;
/* 124 */     paramTextureAttributesRetained.combineRgbFcn[1] = 0;
/* 125 */     paramTextureAttributesRetained.combineRgbFcn[2] = 0;
/*     */     
/* 127 */     paramTextureAttributesRetained.combineAlphaFcn[0] = 2;
/* 128 */     paramTextureAttributesRetained.combineAlphaFcn[1] = 2;
/* 129 */     paramTextureAttributesRetained.combineAlphaFcn[2] = 2;
/*     */   }
/*     */   
/*     */   final void initTextureMode(int paramInt) {
/* 133 */     this.textureMode = paramInt;
/*     */     
/* 135 */     if (paramInt == 6 && 
/* 136 */       this.combineRgbSrc == null) {
/* 137 */       initCombineMode(this);
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
/*     */   final void setTextureMode(int paramInt) {
/* 149 */     initTextureMode(paramInt);
/* 150 */     sendMessage(2, enums[paramInt], null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   final int getTextureMode() { return this.textureMode; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   final void initTextureBlendColor(Color4f paramColor4f) { this.textureBlendColor.set(paramColor4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setTextureBlendColor(Color4f paramColor4f) {
/* 174 */     this.textureBlendColor.set(paramColor4f);
/* 175 */     sendMessage(4, new Color4f(paramColor4f), null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 180 */   final void initTextureBlendColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { this.textureBlendColor.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setTextureBlendColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 194 */     this.textureBlendColor.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/* 195 */     sendMessage(4, new Color4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4), null);
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
/* 206 */   final void getTextureBlendColor(Color4f paramColor4f) { paramColor4f.set(this.textureBlendColor); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   final void initTextureTransform(Transform3D paramTransform3D) { this.transform.set(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setTextureTransform(Transform3D paramTransform3D) {
/* 222 */     this.transform.set(paramTransform3D);
/* 223 */     sendMessage(1, new Transform3D(paramTransform3D), null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   final void getTextureTransform(Transform3D paramTransform3D) { paramTransform3D.set(this.transform); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   final void initPerspectiveCorrectionMode(int paramInt) { this.perspCorrectionMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPerspectiveCorrectionMode(int paramInt) {
/* 252 */     this.perspCorrectionMode = paramInt;
/* 253 */     sendMessage(8, enums[paramInt], null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   final int getPerspectiveCorrectionMode() { return this.perspCorrectionMode; }
/*     */ 
/*     */   
/*     */   final void setTextureColorTable(int[][] paramArrayOfInt) {
/* 265 */     initTextureColorTable(paramArrayOfInt);
/*     */ 
/*     */     
/* 268 */     if (paramArrayOfInt == null) {
/* 269 */       sendMessage(16, null, null);
/*     */     } else {
/* 271 */       int[] arrayOfInt = new int[this.textureColorTableSize * this.numTextureColorTableComponents];
/*     */       
/* 273 */       System.arraycopy(this.textureColorTable, 0, arrayOfInt, 0, this.textureColorTable.length);
/*     */       
/* 275 */       Object[] arrayOfObject = new Object[3];
/*     */       
/* 277 */       arrayOfObject[0] = new Integer(this.numTextureColorTableComponents);
/* 278 */       arrayOfObject[1] = new Integer(this.textureColorTableSize);
/* 279 */       arrayOfObject[2] = arrayOfInt;
/* 280 */       sendMessage(16, arrayOfObject, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   final void initTextureColorTable(int[][] paramArrayOfInt) {
/* 286 */     this.numTextureColorTableComponents = 0;
/* 287 */     this.textureColorTableSize = 0;
/*     */     
/* 289 */     if (paramArrayOfInt == null) {
/* 290 */       this.textureColorTable = null;
/*     */       
/*     */       return;
/*     */     } 
/* 294 */     if (paramArrayOfInt.length < 3 || paramArrayOfInt.length > 4) {
/* 295 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes13"));
/*     */     }
/*     */     
/* 298 */     if (Texture.getPowerOf2(paramArrayOfInt[0].length) == -1) {
/* 299 */       throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes14"));
/*     */     }
/*     */     byte b1;
/* 302 */     for (b1 = 1; b1 < paramArrayOfInt.length; b1++) {
/* 303 */       if (paramArrayOfInt[b1].length != paramArrayOfInt[0].length) {
/* 304 */         throw new IllegalArgumentException(J3dI18N.getString("TextureAttributes15"));
/*     */       }
/*     */     } 
/* 307 */     this.numTextureColorTableComponents = paramArrayOfInt.length;
/* 308 */     this.textureColorTableSize = paramArrayOfInt[0].length;
/*     */     
/* 310 */     if (this.textureColorTable == null || this.textureColorTable.length != this.numTextureColorTableComponents * this.textureColorTableSize)
/*     */     {
/*     */       
/* 313 */       this.textureColorTable = new int[this.numTextureColorTableComponents * this.textureColorTableSize];
/*     */     }
/*     */ 
/*     */     
/* 317 */     b1 = 0;
/* 318 */     for (byte b2 = 0; b2 < this.textureColorTableSize; b2++) {
/* 319 */       for (byte b = 0; b < this.numTextureColorTableComponents; b++) {
/* 320 */         this.textureColorTable[b1++] = paramArrayOfInt[b][b2];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   final void getTextureColorTable(int[][] paramArrayOfInt) {
/* 328 */     if (this.textureColorTable == null) {
/*     */       return;
/*     */     }
/* 331 */     byte b1 = 0;
/* 332 */     for (byte b2 = 0; b2 < this.textureColorTableSize; b2++) {
/* 333 */       for (byte b = 0; b < this.numTextureColorTableComponents; b++) {
/* 334 */         paramArrayOfInt[b][b2] = this.textureColorTable[b1++];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 340 */   final int getNumTextureColorTableComponents() { return this.numTextureColorTableComponents; }
/*     */ 
/*     */ 
/*     */   
/* 344 */   final int getTextureColorTableSize() { return this.textureColorTableSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   final void initCombineRgbMode(int paramInt) { this.combineRgbMode = paramInt; }
/*     */ 
/*     */   
/*     */   final void setCombineRgbMode(int paramInt) {
/* 353 */     initCombineRgbMode(paramInt);
/* 354 */     sendMessage(32, enums[paramInt], null);
/*     */   }
/*     */ 
/*     */   
/* 358 */   final int getCombineRgbMode() { return this.combineRgbMode; }
/*     */ 
/*     */ 
/*     */   
/* 362 */   final void initCombineAlphaMode(int paramInt) { this.combineAlphaMode = paramInt; }
/*     */ 
/*     */   
/*     */   final void setCombineAlphaMode(int paramInt) {
/* 366 */     initCombineAlphaMode(paramInt);
/* 367 */     sendMessage(64, enums[paramInt], null);
/*     */   }
/*     */ 
/*     */   
/* 371 */   final int getCombineAlphaMode() { return this.combineAlphaMode; }
/*     */ 
/*     */   
/*     */   final void initCombineRgbSource(int paramInt1, int paramInt2) {
/* 375 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 379 */       initCombineMode(this);
/*     */     }
/* 381 */     this.combineRgbSrc[paramInt1] = paramInt2;
/*     */   }
/*     */   
/*     */   final void setCombineRgbSource(int paramInt1, int paramInt2) {
/* 385 */     initCombineRgbSource(paramInt1, paramInt2);
/* 386 */     sendMessage(128, enums[paramInt1], enums[paramInt2]);
/*     */   }
/*     */   
/*     */   final int getCombineRgbSource(int paramInt) {
/* 390 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 394 */       initCombineMode(this);
/*     */     }
/* 396 */     return this.combineRgbSrc[paramInt];
/*     */   }
/*     */   
/*     */   final void initCombineAlphaSource(int paramInt1, int paramInt2) {
/* 400 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 404 */       initCombineMode(this);
/*     */     }
/* 406 */     this.combineAlphaSrc[paramInt1] = paramInt2;
/*     */   }
/*     */   
/*     */   final void setCombineAlphaSource(int paramInt1, int paramInt2) {
/* 410 */     initCombineAlphaSource(paramInt1, paramInt2);
/* 411 */     sendMessage(256, enums[paramInt1], enums[paramInt2]);
/*     */   }
/*     */   
/*     */   final int getCombineAlphaSource(int paramInt) {
/* 415 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 419 */       initCombineMode(this);
/*     */     }
/* 421 */     return this.combineAlphaSrc[paramInt];
/*     */   }
/*     */   
/*     */   final void initCombineRgbFunction(int paramInt1, int paramInt2) {
/* 425 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 429 */       initCombineMode(this);
/*     */     }
/* 431 */     this.combineRgbFcn[paramInt1] = paramInt2;
/*     */   }
/*     */   
/*     */   final void setCombineRgbFunction(int paramInt1, int paramInt2) {
/* 435 */     initCombineRgbFunction(paramInt1, paramInt2);
/* 436 */     sendMessage(512, enums[paramInt1], enums[paramInt2]);
/*     */   }
/*     */   
/*     */   final int getCombineRgbFunction(int paramInt) {
/* 440 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 444 */       initCombineMode(this);
/*     */     }
/* 446 */     return this.combineRgbFcn[paramInt];
/*     */   }
/*     */   
/*     */   final void initCombineAlphaFunction(int paramInt1, int paramInt2) {
/* 450 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 454 */       initCombineMode(this);
/*     */     }
/* 456 */     this.combineAlphaFcn[paramInt1] = paramInt2;
/*     */   }
/*     */   
/*     */   final void setCombineAlphaFunction(int paramInt1, int paramInt2) {
/* 460 */     initCombineAlphaFunction(paramInt1, paramInt2);
/* 461 */     sendMessage(1024, enums[paramInt1], enums[paramInt2]);
/*     */   }
/*     */   
/*     */   final int getCombineAlphaFunction(int paramInt) {
/* 465 */     if (this.combineRgbSrc == null)
/*     */     {
/*     */ 
/*     */       
/* 469 */       initCombineMode(this);
/*     */     }
/* 471 */     return this.combineAlphaFcn[paramInt];
/*     */   }
/*     */ 
/*     */   
/* 475 */   final void initCombineRgbScale(int paramInt) { this.combineRgbScale = paramInt; }
/*     */ 
/*     */   
/*     */   final void setCombineRgbScale(int paramInt) {
/* 479 */     initCombineRgbScale(paramInt);
/* 480 */     sendMessage(2048, enums[paramInt], null);
/*     */   }
/*     */ 
/*     */   
/* 484 */   final int getCombineRgbScale() { return this.combineRgbScale; }
/*     */ 
/*     */ 
/*     */   
/* 488 */   final void initCombineAlphaScale(int paramInt) { this.combineAlphaScale = paramInt; }
/*     */ 
/*     */   
/*     */   final void setCombineAlphaScale(int paramInt) {
/* 492 */     initCombineAlphaScale(paramInt);
/* 493 */     sendMessage(4096, enums[paramInt], null);
/*     */   }
/*     */ 
/*     */   
/* 497 */   final int getCombineAlphaScale() { return this.combineAlphaScale; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateNative(Canvas3D paramCanvas3D, boolean paramBoolean, int paramInt) {
/* 510 */     boolean bool = ((this.transform.getType() & 0x2) != 0);
/*     */ 
/*     */     
/* 513 */     if (!paramBoolean) {
/* 514 */       if (VirtualUniverse.mc.useCombiners && (paramCanvas3D.textureExtendedFeatures & 0x40) != 0) {
/*     */ 
/*     */         
/* 517 */         Pipeline.getPipeline().updateRegisterCombiners(paramCanvas3D.ctx, this.transform.mat, bool, this.textureMode, this.perspCorrectionMode, this.textureBlendColor.x, this.textureBlendColor.y, this.textureBlendColor.z, this.textureBlendColor.w, paramInt, this.combineRgbMode, this.combineAlphaMode, this.combineRgbSrc, this.combineAlphaSrc, this.combineRgbFcn, this.combineAlphaFcn, this.combineRgbScale, this.combineAlphaScale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 526 */       else if (this.textureMode == 6) {
/*     */         
/* 528 */         if ((paramCanvas3D.textureExtendedFeatures & 0x8) != 0)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 533 */           int i = this.combineRgbMode;
/* 534 */           int j = this.combineAlphaMode;
/*     */           
/* 536 */           Pipeline.getPipeline().updateTextureAttributes(paramCanvas3D.ctx, this.transform.mat, bool, this.textureMode, this.perspCorrectionMode, this.textureBlendColor.x, this.textureBlendColor.y, this.textureBlendColor.z, this.textureBlendColor.w, paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 544 */           if ((this.combineRgbMode == 6 && (paramCanvas3D.textureExtendedFeatures & 0x10) == 0) || (this.combineRgbMode == 4 && (paramCanvas3D.textureExtendedFeatures & 0x20) == 0))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 554 */             i = 0;
/*     */           }
/*     */           
/* 557 */           if ((this.combineAlphaMode == 6 && (paramCanvas3D.textureExtendedFeatures & 0x10) == 0) || (this.combineAlphaMode == 4 && (paramCanvas3D.textureExtendedFeatures & 0x20) == 0))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 567 */             j = 0;
/*     */           }
/*     */           
/* 570 */           Pipeline.getPipeline().updateCombiner(paramCanvas3D.ctx, i, j, this.combineRgbSrc, this.combineAlphaSrc, this.combineRgbFcn, this.combineAlphaFcn, this.combineRgbScale, this.combineAlphaScale);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 581 */           Pipeline.getPipeline().updateTextureAttributes(paramCanvas3D.ctx, this.transform.mat, bool, 5, this.perspCorrectionMode, this.textureBlendColor.x, this.textureBlendColor.y, this.textureBlendColor.z, this.textureBlendColor.w, paramInt);
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 590 */         Pipeline.getPipeline().updateTextureAttributes(paramCanvas3D.ctx, this.transform.mat, bool, this.textureMode, this.perspCorrectionMode, this.textureBlendColor.x, this.textureBlendColor.y, this.textureBlendColor.z, this.textureBlendColor.w, paramInt);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 600 */       if ((paramCanvas3D.textureExtendedFeatures & 0x2) != 0 && this.textureColorTable != null)
/*     */       {
/*     */         
/* 603 */         Pipeline.getPipeline().updateTextureColorTable(paramCanvas3D.ctx, this.numTextureColorTableComponents, this.textureColorTableSize, this.textureColorTable);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 611 */       Pipeline.getPipeline().updateTextureAttributes(paramCanvas3D.ctx, this.transform.mat, bool, 5, this.perspCorrectionMode, this.textureBlendColor.x, this.textureBlendColor.y, this.textureBlendColor.z, this.textureBlendColor.w, paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 617 */       if ((paramCanvas3D.textureExtendedFeatures & 0x2) != 0 && this.textureColorTable != null)
/*     */       {
/*     */         
/* 620 */         Pipeline.getPipeline().updateTextureColorTable(paramCanvas3D.ctx, this.numTextureColorTableComponents, this.textureColorTableSize, this.textureColorTable);
/*     */       }
/*     */ 
/*     */       
/* 624 */       switch (this.textureMode) {
/*     */         case 5:
/*     */         case 6:
/* 627 */           paramCanvas3D.setBlendFunc(paramCanvas3D.ctx, 1, 0);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 632 */           paramCanvas3D.setBlendFunc(paramCanvas3D.ctx, 4, 0);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 637 */           if (paramInt == 6) {
/* 638 */             paramCanvas3D.setBlendFunc(paramCanvas3D.ctx, 2, 3);
/*     */             
/*     */             break;
/*     */           } 
/* 642 */           paramCanvas3D.setBlendFunc(paramCanvas3D.ctx, 1, 0);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 648 */           paramCanvas3D.setBlendColor(paramCanvas3D.ctx, this.textureBlendColor.x, this.textureBlendColor.y, this.textureBlendColor.z, this.textureBlendColor.w);
/*     */           
/* 650 */           paramCanvas3D.setBlendFunc(paramCanvas3D.ctx, 8, 7);
/*     */           break;
/*     */       } 
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
/*     */   void createMirrorObject() {
/* 665 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 668 */       if (isStatic()) {
/* 669 */         this.mirror = this;
/*     */       } else {
/* 671 */         TextureAttributesRetained textureAttributesRetained = new TextureAttributesRetained();
/* 672 */         textureAttributesRetained.source = this.source;
/* 673 */         textureAttributesRetained.set(this);
/* 674 */         this.mirror = textureAttributesRetained;
/*     */       } 
/*     */     } else {
/* 677 */       ((TextureAttributesRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 685 */   void initMirrorObject() { ((TextureAttributesRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject1, Object paramObject2) {
/* 695 */     TextureAttributesRetained textureAttributesRetained = (TextureAttributesRetained)this.mirror;
/* 696 */     textureAttributesRetained.mirrorCompDirty = true;
/*     */     
/* 698 */     if ((paramInt & true) != 0) {
/* 699 */       textureAttributesRetained.transform.set((Transform3D)paramObject1);
/*     */     }
/* 701 */     else if ((paramInt & 0x2) != 0) {
/* 702 */       textureAttributesRetained.textureMode = ((Integer)paramObject1).intValue();
/*     */       
/* 704 */       if (textureAttributesRetained.textureMode == 6 && textureAttributesRetained.combineRgbSrc == null)
/*     */       {
/* 706 */         initCombineMode(textureAttributesRetained);
/*     */       }
/*     */     }
/* 709 */     else if ((paramInt & 0x4) != 0) {
/* 710 */       textureAttributesRetained.textureBlendColor.set((Color4f)paramObject1);
/*     */     }
/* 712 */     else if ((paramInt & 0x8) != 0) {
/* 713 */       textureAttributesRetained.perspCorrectionMode = ((Integer)paramObject1).intValue();
/*     */     }
/* 715 */     else if ((paramInt & 0x10) != 0) {
/* 716 */       if (paramObject1 == null) {
/* 717 */         textureAttributesRetained.textureColorTable = null;
/* 718 */         textureAttributesRetained.numTextureColorTableComponents = 0;
/* 719 */         textureAttributesRetained.textureColorTableSize = 0;
/*     */       } else {
/* 721 */         Object[] arrayOfObject = (Object[])paramObject1;
/* 722 */         textureAttributesRetained.textureColorTable = (int[])arrayOfObject[2];
/* 723 */         textureAttributesRetained.numTextureColorTableComponents = ((Integer)arrayOfObject[0]).intValue();
/*     */         
/* 725 */         textureAttributesRetained.textureColorTableSize = ((Integer)arrayOfObject[1]).intValue();
/*     */       }
/*     */     
/*     */     }
/* 729 */     else if ((paramInt & 0x20) != 0) {
/* 730 */       textureAttributesRetained.combineRgbMode = ((Integer)paramObject1).intValue();
/*     */     }
/* 732 */     else if ((paramInt & 0x40) != 0) {
/* 733 */       textureAttributesRetained.combineAlphaMode = ((Integer)paramObject1).intValue();
/*     */     }
/* 735 */     else if ((paramInt & 0x80) != 0) {
/* 736 */       if (textureAttributesRetained.combineRgbSrc == null)
/*     */       {
/* 738 */         initCombineMode(textureAttributesRetained);
/*     */       }
/* 740 */       int i = ((Integer)paramObject1).intValue();
/* 741 */       textureAttributesRetained.combineRgbSrc[i] = ((Integer)paramObject2).intValue();
/*     */     }
/* 743 */     else if ((paramInt & 0x100) != 0) {
/* 744 */       if (textureAttributesRetained.combineRgbSrc == null)
/*     */       {
/* 746 */         initCombineMode(textureAttributesRetained);
/*     */       }
/* 748 */       int i = ((Integer)paramObject1).intValue();
/* 749 */       textureAttributesRetained.combineAlphaSrc[i] = ((Integer)paramObject2).intValue();
/*     */     }
/* 751 */     else if ((paramInt & 0x200) != 0) {
/* 752 */       if (textureAttributesRetained.combineRgbSrc == null)
/*     */       {
/* 754 */         initCombineMode(textureAttributesRetained);
/*     */       }
/* 756 */       int i = ((Integer)paramObject1).intValue();
/* 757 */       textureAttributesRetained.combineRgbFcn[i] = ((Integer)paramObject2).intValue();
/*     */     }
/* 759 */     else if ((paramInt & 0x400) != 0) {
/* 760 */       if (textureAttributesRetained.combineRgbSrc == null)
/*     */       {
/* 762 */         initCombineMode(textureAttributesRetained);
/*     */       }
/* 764 */       int i = ((Integer)paramObject1).intValue();
/* 765 */       textureAttributesRetained.combineAlphaFcn[i] = ((Integer)paramObject2).intValue();
/*     */     }
/* 767 */     else if ((paramInt & 0x800) != 0) {
/* 768 */       textureAttributesRetained.combineRgbScale = ((Integer)paramObject1).intValue();
/*     */     }
/* 770 */     else if ((paramInt & 0x1000) != 0) {
/* 771 */       textureAttributesRetained.combineAlphaScale = ((Integer)paramObject1).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean equivalent(TextureAttributesRetained paramTextureAttributesRetained) {
/* 778 */     if (paramTextureAttributesRetained == null) {
/* 779 */       return false;
/*     */     }
/* 781 */     if (this.changedFrequent != 0 || paramTextureAttributesRetained.changedFrequent != 0) {
/* 782 */       return (this == paramTextureAttributesRetained);
/*     */     }
/*     */     
/* 785 */     if (!paramTextureAttributesRetained.transform.equals(this.transform) || !paramTextureAttributesRetained.textureBlendColor.equals(this.textureBlendColor) || paramTextureAttributesRetained.textureMode != this.textureMode || paramTextureAttributesRetained.perspCorrectionMode != this.perspCorrectionMode)
/*     */     {
/*     */ 
/*     */       
/* 789 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 796 */     if (this.textureMode == 6) {
/*     */       
/* 798 */       if (paramTextureAttributesRetained.combineRgbMode != this.combineRgbMode || paramTextureAttributesRetained.combineAlphaMode != this.combineAlphaMode || paramTextureAttributesRetained.combineRgbScale != this.combineRgbScale || paramTextureAttributesRetained.combineAlphaScale != this.combineAlphaScale)
/*     */       {
/*     */ 
/*     */         
/* 802 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 808 */       byte b1 = 0;
/*     */       
/* 810 */       if (this.combineRgbMode == 0) {
/* 811 */         b1 = 1;
/* 812 */       } else if (this.combineRgbMode == 5) {
/* 813 */         b1 = 3;
/*     */       } else {
/* 815 */         b1 = 2;
/*     */       } 
/*     */       
/* 818 */       for (byte b2 = 0; b2 < b1; b2++) {
/* 819 */         if (paramTextureAttributesRetained.combineRgbSrc[b2] != this.combineRgbSrc[b2] || paramTextureAttributesRetained.combineAlphaSrc[b2] != this.combineAlphaSrc[b2] || paramTextureAttributesRetained.combineRgbFcn[b2] != this.combineRgbFcn[b2] || paramTextureAttributesRetained.combineAlphaFcn[b2] != this.combineAlphaFcn[b2])
/*     */         {
/*     */ 
/*     */           
/* 823 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 830 */     if (paramTextureAttributesRetained.textureColorTable == null) {
/* 831 */       if (this.textureColorTable == null) {
/* 832 */         return true;
/*     */       }
/* 834 */       return false;
/* 835 */     }  if (this.textureColorTable == null)
/*     */     {
/* 837 */       return false;
/*     */     }
/* 839 */     if (paramTextureAttributesRetained.textureColorTable.length != this.textureColorTable.length) {
/* 840 */       return false;
/*     */     }
/* 842 */     for (byte b = 0; b < this.textureColorTable.length; b++) {
/* 843 */       if (this.textureColorTable[b] != paramTextureAttributesRetained.textureColorTable[b]) {
/* 844 */         return false;
/*     */       }
/*     */     } 
/* 847 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 854 */     TextureAttributesRetained textureAttributesRetained = (TextureAttributesRetained)super.clone();
/* 855 */     textureAttributesRetained.transform = new Transform3D(this.transform);
/* 856 */     textureAttributesRetained.textureBlendColor = new Color4f(this.textureBlendColor);
/* 857 */     if (this.textureColorTable != null) {
/* 858 */       textureAttributesRetained.textureColorTable = new int[this.textureColorTable.length];
/* 859 */       System.arraycopy(this.textureColorTable, 0, textureAttributesRetained.textureColorTable, 0, this.textureColorTable.length);
/*     */     } else {
/*     */       
/* 862 */       textureAttributesRetained.textureColorTable = null;
/*     */     } 
/*     */ 
/*     */     
/* 866 */     if (this.combineRgbSrc != null) {
/* 867 */       textureAttributesRetained.combineRgbSrc = new int[3];
/* 868 */       textureAttributesRetained.combineAlphaSrc = new int[3];
/* 869 */       textureAttributesRetained.combineRgbFcn = new int[3];
/* 870 */       textureAttributesRetained.combineAlphaFcn = new int[3];
/*     */       
/* 872 */       for (byte b = 0; b < 3; b++) {
/* 873 */         textureAttributesRetained.combineRgbSrc[b] = this.combineRgbSrc[b];
/* 874 */         textureAttributesRetained.combineAlphaSrc[b] = this.combineAlphaSrc[b];
/* 875 */         textureAttributesRetained.combineRgbFcn[b] = this.combineRgbFcn[b];
/* 876 */         textureAttributesRetained.combineAlphaFcn[b] = this.combineAlphaFcn[b];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 881 */     return textureAttributesRetained;
/*     */   }
/*     */   
/*     */   protected void set(TextureAttributesRetained paramTextureAttributesRetained) {
/* 885 */     set(paramTextureAttributesRetained);
/* 886 */     this.transform.set(paramTextureAttributesRetained.transform);
/* 887 */     this.textureBlendColor.set(paramTextureAttributesRetained.textureBlendColor);
/* 888 */     this.textureMode = paramTextureAttributesRetained.textureMode;
/* 889 */     this.perspCorrectionMode = paramTextureAttributesRetained.perspCorrectionMode;
/*     */ 
/*     */ 
/*     */     
/* 893 */     if (paramTextureAttributesRetained.textureColorTable != null) {
/* 894 */       if (this.textureColorTable == null || this.textureColorTable.length != paramTextureAttributesRetained.textureColorTable.length)
/*     */       {
/* 896 */         this.textureColorTable = new int[paramTextureAttributesRetained.textureColorTable.length];
/*     */       }
/* 898 */       System.arraycopy(paramTextureAttributesRetained.textureColorTable, 0, this.textureColorTable, 0, paramTextureAttributesRetained.textureColorTable.length);
/*     */     } else {
/*     */       
/* 901 */       this.textureColorTable = null;
/*     */     } 
/* 903 */     this.numTextureColorTableComponents = paramTextureAttributesRetained.numTextureColorTableComponents;
/* 904 */     this.textureColorTableSize = paramTextureAttributesRetained.textureColorTableSize;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 909 */     this.combineRgbMode = paramTextureAttributesRetained.combineRgbMode;
/* 910 */     this.combineAlphaMode = paramTextureAttributesRetained.combineAlphaMode;
/* 911 */     this.combineRgbScale = paramTextureAttributesRetained.combineRgbScale;
/* 912 */     this.combineAlphaScale = paramTextureAttributesRetained.combineAlphaScale;
/*     */     
/* 914 */     if (paramTextureAttributesRetained.combineRgbSrc != null) {
/* 915 */       if (this.combineRgbSrc == null) {
/* 916 */         this.combineRgbSrc = new int[3];
/* 917 */         this.combineAlphaSrc = new int[3];
/* 918 */         this.combineRgbFcn = new int[3];
/* 919 */         this.combineAlphaFcn = new int[3];
/*     */       } 
/*     */       
/* 922 */       for (byte b = 0; b < 3; b++) {
/* 923 */         this.combineRgbSrc[b] = paramTextureAttributesRetained.combineRgbSrc[b];
/* 924 */         this.combineAlphaSrc[b] = paramTextureAttributesRetained.combineAlphaSrc[b];
/* 925 */         this.combineRgbFcn[b] = paramTextureAttributesRetained.combineRgbFcn[b];
/* 926 */         this.combineAlphaFcn[b] = paramTextureAttributesRetained.combineAlphaFcn[b];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject1, Object paramObject2) {
/* 934 */     ArrayList arrayList1 = new ArrayList();
/* 935 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 940 */     J3dMessage j3dMessage = new J3dMessage();
/* 941 */     j3dMessage.threads = 1024;
/* 942 */     j3dMessage.type = 11;
/* 943 */     j3dMessage.universe = null;
/* 944 */     j3dMessage.args[0] = this;
/* 945 */     j3dMessage.args[1] = new Integer(paramInt);
/* 946 */     j3dMessage.args[2] = paramObject1;
/* 947 */     j3dMessage.args[3] = paramObject2;
/* 948 */     j3dMessage.args[4] = new Integer(this.changedFrequent);
/* 949 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */ 
/*     */     
/* 953 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 954 */       j3dMessage = new J3dMessage();
/* 955 */       j3dMessage.threads = 128;
/* 956 */       j3dMessage.type = 11;
/*     */       
/* 958 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 959 */       j3dMessage.args[0] = this;
/* 960 */       j3dMessage.args[1] = new Integer(paramInt);
/* 961 */       j3dMessage.args[2] = paramObject1;
/*     */       
/* 963 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 964 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 965 */       arrayList.toArray(arrayOfGeometryAtom);
/* 966 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 968 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 973 */     switch (paramInt) {
/*     */       case 1:
/*     */       case 3:
/*     */       case 5:
/*     */       case 7:
/*     */       case 9:
/* 979 */         setFrequencyChangeMask(paramInt, paramInt);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\TextureAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */