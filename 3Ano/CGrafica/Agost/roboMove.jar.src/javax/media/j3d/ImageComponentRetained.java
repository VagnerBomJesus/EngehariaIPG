/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.image.AffineTransformOp;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.PixelInterleavedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.logging.Level;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class ImageComponentRetained
/*      */   extends NodeComponentRetained
/*      */ {
/*      */   static final int IMAGE_CHANGED = 1;
/*      */   static final int SUBIMAGE_CHANGED = 2;
/*      */   static final int TYPE_BYTE_BGR = 1;
/*      */   static final int TYPE_BYTE_RGB = 2;
/*      */   static final int TYPE_BYTE_ABGR = 4;
/*      */   static final int TYPE_BYTE_RGBA = 8;
/*      */   static final int TYPE_BYTE_LA = 16;
/*      */   static final int TYPE_BYTE_GRAY = 32;
/*      */   static final int TYPE_USHORT_GRAY = 64;
/*      */   static final int TYPE_INT_BGR = 128;
/*      */   static final int TYPE_INT_RGB = 256;
/*      */   static final int TYPE_INT_ARGB = 512;
/*      */   static final int IMAGE_SIZE_512X512 = 262144;
/*      */   static final int IMAGE_DATA_TYPE_BYTE_ARRAY = 4096;
/*      */   static final int IMAGE_DATA_TYPE_INT_ARRAY = 8192;
/*      */   static final int IMAGE_DATA_TYPE_BYTE_BUFFER = 16384;
/*      */   static final int IMAGE_DATA_TYPE_INT_BUFFER = 32768;
/*      */   private int apiFormat;
/*      */   int width;
/*      */   int height;
/*      */   int depth;
/*      */   
/*      */   enum ImageFormatType
/*      */   {
/*   55 */     TYPE_UNKNOWN,
/*   56 */     TYPE_BYTE_BGR,
/*   57 */     TYPE_BYTE_RGB,
/*   58 */     TYPE_BYTE_ABGR,
/*   59 */     TYPE_BYTE_RGBA,
/*   60 */     TYPE_BYTE_LA,
/*   61 */     TYPE_BYTE_GRAY,
/*   62 */     TYPE_USHORT_GRAY,
/*   63 */     TYPE_INT_BGR,
/*   64 */     TYPE_INT_RGB,
/*   65 */     TYPE_INT_ARGB;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   enum ImageDataType
/*      */   {
/*   74 */     TYPE_NULL,
/*   75 */     TYPE_BYTE_ARRAY,
/*   76 */     TYPE_INT_ARRAY,
/*   77 */     TYPE_BYTE_BUFFER,
/*   78 */     TYPE_INT_BUFFER;
/*      */   }
/*      */   
/*      */   boolean byReference = false;
/*      */   
/*      */   boolean yUp = false;
/*      */   
/*      */   boolean imageTypeIsSupported;
/*      */   
/*      */   boolean abgrSupported = true;
/*      */   
/*      */   boolean npotSupported = true;
/*      */   
/*      */   private int unitsPerPixel;
/*      */   
/*      */   private int numberOfComponents;
/*      */   
/*      */   private int imageType;
/*      */   
/*   97 */   private ImageFormatType imageFormatType = ImageFormatType.TYPE_UNKNOWN;
/*      */   ImageData imageData;
/*   99 */   private ImageComponent.ImageClass imageClass = ImageComponent.ImageClass.BUFFERED_IMAGE;
/*      */ 
/*      */ 
/*      */   
/*      */   private ImageData imageDataPowerOfTwo;
/*      */ 
/*      */   
/*      */   private AffineTransformOp powerOfTwoATOp;
/*      */ 
/*      */   
/*      */   private boolean enforceNonPowerOfTwoSupport = false;
/*      */ 
/*      */   
/*      */   private boolean usedByOffScreenCanvas = false;
/*      */ 
/*      */   
/*  115 */   private Object[] refImage = null;
/*      */ 
/*      */   
/*  118 */   Object evaluateExtLock = new Object();
/*      */ 
/*      */   
/*  121 */   GeometryLock geomLock = new GeometryLock();
/*      */   
/*  123 */   int tilew = 0;
/*  124 */   int tileh = 0;
/*  125 */   int numXTiles = 0;
/*  126 */   int numYTiles = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  131 */   ArrayList userList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  138 */   int getWidth() { return this.width; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   int getHeight() { return this.height; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  155 */   int getFormat() { return this.apiFormat; }
/*      */ 
/*      */ 
/*      */   
/*  159 */   void setFormat(int paramInt) { this.apiFormat = paramInt; }
/*      */ 
/*      */ 
/*      */   
/*  163 */   void setByReference(boolean paramBoolean) { this.byReference = paramBoolean; }
/*      */ 
/*      */ 
/*      */   
/*  167 */   boolean isByReference() { return this.byReference; }
/*      */ 
/*      */ 
/*      */   
/*  171 */   void setYUp(boolean paramBoolean) { this.yUp = paramBoolean; }
/*      */ 
/*      */ 
/*      */   
/*  175 */   boolean isYUp() { return this.yUp; }
/*      */ 
/*      */ 
/*      */   
/*  179 */   int getUnitsPerPixel() { return this.unitsPerPixel; }
/*      */ 
/*      */ 
/*      */   
/*  183 */   void setUnitsPerPixel(int paramInt) { this.unitsPerPixel = paramInt; }
/*      */ 
/*      */ 
/*      */   
/*  187 */   ImageComponent.ImageClass getImageClass() { return this.imageClass; }
/*      */ 
/*      */   
/*      */   void setImageClass(RenderedImage paramRenderedImage) {
/*  191 */     if (paramRenderedImage instanceof BufferedImage) {
/*  192 */       this.imageClass = ImageComponent.ImageClass.BUFFERED_IMAGE;
/*      */     } else {
/*  194 */       this.imageClass = ImageComponent.ImageClass.RENDERED_IMAGE;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  199 */   void setImageClass(NioImageBuffer paramNioImageBuffer) { this.imageClass = ImageComponent.ImageClass.NIO_IMAGE_BUFFER; }
/*      */ 
/*      */ 
/*      */   
/*  203 */   void setEnforceNonPowerOfTwoSupport(boolean paramBoolean) { this.enforceNonPowerOfTwoSupport = paramBoolean; }
/*      */ 
/*      */ 
/*      */   
/*  207 */   void setUsedByOffScreen(boolean paramBoolean) { this.usedByOffScreenCanvas = paramBoolean; }
/*      */ 
/*      */ 
/*      */   
/*  211 */   boolean getUsedByOffScreen() { return this.usedByOffScreenCanvas; }
/*      */ 
/*      */ 
/*      */   
/*  215 */   int getNumberOfComponents() { return this.numberOfComponents; }
/*      */ 
/*      */ 
/*      */   
/*  219 */   void setNumberOfComponents(int paramInt) { this.numberOfComponents = paramInt; }
/*      */ 
/*      */   
/*      */   int getImageDataTypeIntValue() {
/*  223 */     null = -1;
/*  224 */     switch (this.imageData.imageDataType) {
/*      */       case TYPE_4BYTE_ABGR:
/*  226 */         return 4096;
/*      */       
/*      */       case TYPE_4BYTE_RGBA:
/*  229 */         return 8192;
/*      */       
/*      */       case TYPE_INT_ARGB:
/*  232 */         return 16384;
/*      */       
/*      */       case TYPE_3BYTE_BGR:
/*  235 */         return 32768;
/*      */     } 
/*      */ 
/*      */     
/*      */     assert false;
/*  240 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   int getImageFormatTypeIntValue(boolean paramBoolean) {
/*  245 */     null = -1;
/*  246 */     switch (this.imageFormatType)
/*      */     { case TYPE_4BYTE_ABGR:
/*  248 */         return 1;
/*      */       
/*      */       case TYPE_4BYTE_RGBA:
/*  251 */         return 2;
/*      */       
/*      */       case TYPE_INT_ARGB:
/*  254 */         return 4;
/*      */       
/*      */       case TYPE_3BYTE_BGR:
/*  257 */         if (this.imageDataPowerOfTwo != null && paramBoolean) {
/*  258 */           null = 4;
/*      */         } else {
/*      */           
/*  261 */           null = 8;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  285 */         return null;case TYPE_3BYTE_RGB: return 16;
/*      */       case TYPE_INT_BGR: return 32;
/*      */       case TYPE_INT_RGB: return 64;
/*      */       case null: return 128;
/*      */       case null: return 256;
/*  290 */       case null: return 512; }  throw new AssertionError(); } int getImageType() { return this.imageType; }
/*      */ 
/*      */ 
/*      */   
/*  294 */   void setImageFormatType(ImageFormatType paramImageFormatType) { this.imageFormatType = paramImageFormatType; }
/*      */ 
/*      */ 
/*      */   
/*  298 */   ImageFormatType getImageFormatType() { return this.imageFormatType; }
/*      */ 
/*      */ 
/*      */   
/*  302 */   void setRefImage(Object paramObject, int paramInt) { this.refImage[paramInt] = paramObject; }
/*      */ 
/*      */ 
/*      */   
/*  306 */   Object getRefImage(int paramInt) { return this.refImage[paramInt]; }
/*      */ 
/*      */   
/*      */   ImageData getImageData(boolean paramBoolean) {
/*  310 */     if (paramBoolean) {
/*  311 */       assert this.enforceNonPowerOfTwoSupport;
/*  312 */       if (this.imageDataPowerOfTwo != null) {
/*  313 */         return this.imageDataPowerOfTwo;
/*      */       }
/*      */     } 
/*  316 */     return this.imageData;
/*      */   }
/*      */   
/*      */   boolean useBilinearFilter() {
/*  320 */     if (this.imageDataPowerOfTwo != null) {
/*  321 */       return true;
/*      */     }
/*      */     
/*  324 */     return false;
/*      */   }
/*      */ 
/*      */   
/*  328 */   boolean isImageTypeSupported() { return this.imageTypeIsSupported; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  335 */     if (paramInt2 < 1) {
/*  336 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponentRetained0"));
/*      */     }
/*  338 */     if (paramInt3 < 1) {
/*  339 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponentRetained1"));
/*      */     }
/*  341 */     if (paramInt4 < 1) {
/*  342 */       throw new IllegalArgumentException(J3dI18N.getString("ImageComponentRetained2"));
/*      */     }
/*      */ 
/*      */     
/*  346 */     switch (paramInt1) {
/*      */       case 1:
/*      */       case 3:
/*      */       case 5:
/*      */       case 9:
/*  351 */         this.numberOfComponents = 3;
/*      */         break;
/*      */       case 2:
/*      */       case 4:
/*      */       case 6:
/*  356 */         this.numberOfComponents = 4;
/*      */         break;
/*      */       case 7:
/*      */       case 8:
/*  360 */         this.numberOfComponents = 2;
/*      */         break;
/*      */       case 10:
/*  363 */         this.numberOfComponents = 1;
/*      */         break;
/*      */       default:
/*  366 */         throw new IllegalArgumentException(J3dI18N.getString("ImageComponentRetained3"));
/*      */     } 
/*      */     
/*  369 */     setFormat(paramInt1);
/*  370 */     this.width = paramInt2;
/*  371 */     this.height = paramInt3;
/*  372 */     this.depth = paramInt4;
/*  373 */     this.refImage = new Object[paramInt4];
/*      */   }
/*      */   
/*      */   int evaluateImageType(RenderedImage paramRenderedImage) {
/*  377 */     int i = 0;
/*      */     
/*  379 */     if (paramRenderedImage instanceof BufferedImage) {
/*  380 */       i = ((BufferedImage)paramRenderedImage).getType();
/*      */       
/*  382 */       if (i != 0) {
/*  383 */         return i;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  388 */       return i;
/*      */     } 
/*      */ 
/*      */     
/*  392 */     ColorModel colorModel = paramRenderedImage.getColorModel();
/*  393 */     ColorSpace colorSpace = colorModel.getColorSpace();
/*  394 */     SampleModel sampleModel = paramRenderedImage.getSampleModel();
/*      */     
/*  396 */     int j = colorSpace.getType();
/*  397 */     boolean bool = colorModel.isAlphaPremultiplied();
/*      */ 
/*      */     
/*  400 */     if (j == 6 && colorModel instanceof ComponentColorModel) {
/*  401 */       if (sampleModel.getDataType() == 0) {
/*  402 */         i = 10;
/*  403 */       } else if (sampleModel.getDataType() == 1) {
/*  404 */         i = 11;
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  410 */     else if (j == 5) {
/*  411 */       byte b = 0;
/*  412 */       int k = sampleModel.getDataType();
/*  413 */       if (k == 0) {
/*  414 */         b = 8;
/*  415 */       } else if (k == 3) {
/*  416 */         b = 32;
/*      */       } 
/*      */       
/*  419 */       if (b != 0) {
/*  420 */         int m = sampleModel.getNumBands();
/*  421 */         if (colorModel instanceof ComponentColorModel && sampleModel instanceof PixelInterleavedSampleModel) {
/*      */           
/*  423 */           PixelInterleavedSampleModel pixelInterleavedSampleModel = (PixelInterleavedSampleModel)sampleModel;
/*      */           
/*  425 */           int[] arrayOfInt1 = pixelInterleavedSampleModel.getBandOffsets();
/*  426 */           ComponentColorModel componentColorModel = (ComponentColorModel)colorModel;
/*  427 */           int[] arrayOfInt2 = componentColorModel.getComponentSize();
/*  428 */           boolean bool1 = true;
/*  429 */           for (byte b1 = 0; b1 < m; b1++) {
/*  430 */             if (arrayOfInt2[b1] != b) {
/*  431 */               bool1 = false;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           
/*  437 */           if (b == 8) {
/*  438 */             if (bool1 && arrayOfInt1[0] == m - 1 && arrayOfInt1[1] == m - 2 && arrayOfInt1[2] == m - 3)
/*      */             {
/*      */ 
/*      */               
/*  442 */               if (m == 3) {
/*  443 */                 i = 5;
/*  444 */               } else if (arrayOfInt1[3] == 0) {
/*  445 */                 i = bool ? 7 : 6;
/*      */               
/*      */               }
/*      */ 
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  453 */           else if (bool1) {
/*  454 */             if (m == 3) {
/*  455 */               if (arrayOfInt1[0] == m - 1 && arrayOfInt1[1] == m - 2 && arrayOfInt1[2] == m - 3) {
/*      */ 
/*      */                 
/*  458 */                 i = 4;
/*  459 */               } else if (arrayOfInt1[0] == 0 && arrayOfInt1[1] == 1 && arrayOfInt1[2] == 2) {
/*      */ 
/*      */                 
/*  462 */                 i = 1;
/*      */               } 
/*  464 */             } else if (arrayOfInt1[0] == 3 && arrayOfInt1[1] == 0 && arrayOfInt1[2] == 1 && arrayOfInt1[3] == 2) {
/*      */ 
/*      */ 
/*      */               
/*  468 */               i = bool ? 3 : 2;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  478 */     return i;
/*      */   }
/*      */ 
/*      */   
/*      */   boolean is3ByteRGB(RenderedImage paramRenderedImage) {
/*  483 */     boolean bool1 = false;
/*      */     
/*  485 */     ColorModel colorModel = paramRenderedImage.getColorModel();
/*  486 */     ColorSpace colorSpace = colorModel.getColorSpace();
/*  487 */     SampleModel sampleModel = paramRenderedImage.getSampleModel();
/*  488 */     boolean bool2 = colorModel.isAlphaPremultiplied();
/*  489 */     int i = colorSpace.getType();
/*  490 */     if (i == 5) {
/*  491 */       int j = sampleModel.getNumBands();
/*  492 */       if (j == 3 && sampleModel.getDataType() == 0 && 
/*  493 */         colorModel instanceof ComponentColorModel && sampleModel instanceof PixelInterleavedSampleModel) {
/*      */         
/*  495 */         PixelInterleavedSampleModel pixelInterleavedSampleModel = (PixelInterleavedSampleModel)sampleModel;
/*      */         
/*  497 */         int[] arrayOfInt1 = pixelInterleavedSampleModel.getBandOffsets();
/*  498 */         ComponentColorModel componentColorModel = (ComponentColorModel)colorModel;
/*  499 */         int[] arrayOfInt2 = componentColorModel.getComponentSize();
/*  500 */         boolean bool = true;
/*  501 */         for (byte b = 0; b < j; b++) {
/*  502 */           if (arrayOfInt2[b] != 8) {
/*  503 */             bool = false;
/*      */             break;
/*      */           } 
/*      */         } 
/*  507 */         if (bool && arrayOfInt1[0] == 0 && arrayOfInt1[1] == 1 && arrayOfInt1[2] == 2)
/*      */         {
/*      */ 
/*      */           
/*  511 */           bool1 = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  516 */     return bool1;
/*      */   }
/*      */ 
/*      */   
/*      */   boolean is4ByteRGBA(RenderedImage paramRenderedImage) {
/*  521 */     boolean bool1 = false;
/*      */     
/*  523 */     ColorModel colorModel = paramRenderedImage.getColorModel();
/*  524 */     ColorSpace colorSpace = colorModel.getColorSpace();
/*  525 */     SampleModel sampleModel = paramRenderedImage.getSampleModel();
/*  526 */     boolean bool2 = colorModel.isAlphaPremultiplied();
/*  527 */     int i = colorSpace.getType();
/*  528 */     if (i == 5) {
/*  529 */       int j = sampleModel.getNumBands();
/*  530 */       if (j == 4 && sampleModel.getDataType() == 0 && 
/*  531 */         colorModel instanceof ComponentColorModel && sampleModel instanceof PixelInterleavedSampleModel) {
/*      */         
/*  533 */         PixelInterleavedSampleModel pixelInterleavedSampleModel = (PixelInterleavedSampleModel)sampleModel;
/*      */         
/*  535 */         int[] arrayOfInt1 = pixelInterleavedSampleModel.getBandOffsets();
/*  536 */         ComponentColorModel componentColorModel = (ComponentColorModel)colorModel;
/*  537 */         int[] arrayOfInt2 = componentColorModel.getComponentSize();
/*  538 */         boolean bool = true;
/*  539 */         for (byte b = 0; b < j; b++) {
/*  540 */           if (arrayOfInt2[b] != 8) {
/*  541 */             bool = false;
/*      */             break;
/*      */           } 
/*      */         } 
/*  545 */         if (bool && arrayOfInt1[0] == 0 && arrayOfInt1[1] == 1 && arrayOfInt1[2] == 2 && arrayOfInt1[3] == 3 && !bool2)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  550 */           bool1 = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  555 */     return bool1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isSubImageTypeEqual(RenderedImage paramRenderedImage) {
/*  561 */     int i = evaluateImageType(paramRenderedImage);
/*      */ 
/*      */ 
/*      */     
/*  565 */     if (this.imageType == i) {
/*  566 */       return true;
/*      */     }
/*  568 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createBlankImageData() {
/*  576 */     assert this.imageData == null;
/*      */     
/*  578 */     switch (this.numberOfComponents) {
/*      */       case 4:
/*  580 */         this.imageType = 2;
/*  581 */         this.imageFormatType = ImageFormatType.TYPE_INT_ARGB;
/*  582 */         this.unitsPerPixel = 1;
/*      */         break;
/*      */       
/*      */       case 3:
/*  586 */         this.imageType = 1;
/*  587 */         this.imageFormatType = ImageFormatType.TYPE_INT_RGB;
/*  588 */         this.unitsPerPixel = 1;
/*      */         break;
/*      */       
/*      */       default:
/*      */         assert false;
/*      */         break;
/*      */     } 
/*  595 */     this.imageTypeIsSupported = true;
/*  596 */     this.imageData = createRenderedImageDataObject(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isImageTypeSupported(NioImageBuffer paramNioImageBuffer) {
/*  605 */     boolean bool = true;
/*  606 */     NioImageBuffer.ImageType imageType1 = paramNioImageBuffer.getImageType();
/*      */     
/*  608 */     switch (this.numberOfComponents) {
/*      */       case 4:
/*  610 */         switch (imageType1)
/*      */         
/*      */         { 
/*      */           case TYPE_4BYTE_ABGR:
/*  614 */             if (this.abgrSupported) {
/*  615 */               this.imageFormatType = ImageFormatType.TYPE_BYTE_ABGR;
/*      */             } else {
/*      */               
/*  618 */               this.imageFormatType = ImageFormatType.TYPE_BYTE_RGBA;
/*  619 */               bool = false;
/*      */             } 
/*  621 */             this.unitsPerPixel = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  674 */             return bool;case TYPE_4BYTE_RGBA: this.imageFormatType = ImageFormatType.TYPE_BYTE_RGBA; this.unitsPerPixel = 4; return bool;case TYPE_INT_ARGB: this.imageFormatType = ImageFormatType.TYPE_INT_ARGB; this.unitsPerPixel = 1; return bool; }  throw new IllegalArgumentException(J3dI18N.getString("ImageComponent5"));case 3: switch (imageType1) { case TYPE_3BYTE_BGR: this.imageFormatType = ImageFormatType.TYPE_BYTE_BGR; this.unitsPerPixel = 3; return bool;case TYPE_3BYTE_RGB: this.imageFormatType = ImageFormatType.TYPE_BYTE_RGB; this.unitsPerPixel = 3; return bool;case TYPE_INT_BGR: this.imageFormatType = ImageFormatType.TYPE_INT_BGR; this.unitsPerPixel = 1; return bool;case TYPE_INT_RGB: this.imageFormatType = ImageFormatType.TYPE_INT_RGB; this.unitsPerPixel = 1; return bool; }  throw new IllegalArgumentException(J3dI18N.getString("ImageComponent5"));case 2: throw new IllegalArgumentException(J3dI18N.getString("ImageComponent5"));case 1: if (imageType1 == NioImageBuffer.ImageType.TYPE_BYTE_GRAY) { this.imageFormatType = ImageFormatType.TYPE_BYTE_GRAY; this.unitsPerPixel = 1; } else { throw new IllegalArgumentException(J3dI18N.getString("ImageComponent5")); }  return bool;
/*      */     } 
/*      */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isImageTypeSupported(RenderedImage paramRenderedImage) {
/*  682 */     boolean bool = true;
/*  683 */     this.imageType = evaluateImageType(paramRenderedImage);
/*      */     
/*  685 */     switch (this.numberOfComponents) {
/*      */       case 4:
/*  687 */         if (this.imageType == 6) {
/*      */ 
/*      */ 
/*      */           
/*  691 */           if (this.abgrSupported) {
/*  692 */             this.imageFormatType = ImageFormatType.TYPE_BYTE_ABGR;
/*      */           } else {
/*      */             
/*  695 */             this.imageFormatType = ImageFormatType.TYPE_BYTE_RGBA;
/*  696 */             bool = false;
/*      */           } 
/*  698 */           this.unitsPerPixel = 4;
/*  699 */         } else if (this.imageType == 2) {
/*  700 */           this.imageFormatType = ImageFormatType.TYPE_INT_ARGB;
/*  701 */           this.unitsPerPixel = 1;
/*  702 */         } else if (is4ByteRGBA(paramRenderedImage)) {
/*  703 */           this.imageFormatType = ImageFormatType.TYPE_BYTE_RGBA;
/*  704 */           this.unitsPerPixel = 4;
/*      */         }
/*      */         else {
/*      */           
/*  708 */           this.imageFormatType = ImageFormatType.TYPE_BYTE_RGBA;
/*  709 */           bool = false;
/*  710 */           this.unitsPerPixel = 4;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  761 */         return bool;case 3: if (this.imageType == 5) { this.imageFormatType = ImageFormatType.TYPE_BYTE_BGR; this.unitsPerPixel = 3; } else if (this.imageType == 4) { this.imageFormatType = ImageFormatType.TYPE_INT_BGR; this.unitsPerPixel = 1; } else if (this.imageType == 1) { this.imageFormatType = ImageFormatType.TYPE_INT_RGB; this.unitsPerPixel = 1; } else if (is3ByteRGB(paramRenderedImage)) { this.imageFormatType = ImageFormatType.TYPE_BYTE_RGB; this.unitsPerPixel = 3; } else { this.imageFormatType = ImageFormatType.TYPE_BYTE_RGB; bool = false; this.unitsPerPixel = 3; }  return bool;case 2: this.imageFormatType = ImageFormatType.TYPE_BYTE_LA; bool = false; this.unitsPerPixel = 2; return bool;case 1: if (this.imageType == 10) { this.imageFormatType = ImageFormatType.TYPE_BYTE_GRAY; this.unitsPerPixel = 1; } else { this.imageFormatType = ImageFormatType.TYPE_BYTE_GRAY; bool = false; this.unitsPerPixel = 1; }  return bool;
/*      */     } 
/*      */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ImageData createNioImageBufferDataObject(NioImageBuffer paramNioImageBuffer) {
/*  770 */     switch (this.imageFormatType) {
/*      */       case TYPE_4BYTE_ABGR:
/*      */       case TYPE_4BYTE_RGBA:
/*      */       case TYPE_INT_ARGB:
/*      */       case TYPE_3BYTE_BGR:
/*      */       case TYPE_3BYTE_RGB:
/*      */       case TYPE_INT_BGR:
/*  777 */         if (paramNioImageBuffer != null) {
/*  778 */           return new ImageData(ImageDataType.TYPE_BYTE_BUFFER, this.width * this.height * this.depth * this.unitsPerPixel, this.width, this.height, paramNioImageBuffer);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  783 */         return new ImageData(ImageDataType.TYPE_BYTE_BUFFER, this.width * this.height * this.depth * this.unitsPerPixel, this.width, this.height);
/*      */ 
/*      */ 
/*      */       
/*      */       case null:
/*      */       case null:
/*      */       case null:
/*  790 */         return new ImageData(ImageDataType.TYPE_INT_BUFFER, this.width * this.height * this.depth * this.unitsPerPixel, this.width, this.height, paramNioImageBuffer);
/*      */     } 
/*      */ 
/*      */     
/*  794 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ImageData createRenderedImageDataObject(RenderedImage paramRenderedImage, int paramInt1, int paramInt2) {
/*  803 */     switch (this.imageFormatType) {
/*      */       case TYPE_4BYTE_ABGR:
/*      */       case TYPE_4BYTE_RGBA:
/*      */       case TYPE_INT_ARGB:
/*      */       case TYPE_3BYTE_BGR:
/*      */       case TYPE_3BYTE_RGB:
/*      */       case TYPE_INT_BGR:
/*  810 */         if (paramRenderedImage != null) {
/*  811 */           return new ImageData(ImageDataType.TYPE_BYTE_ARRAY, paramInt1 * paramInt2 * this.depth * this.unitsPerPixel, paramInt1, paramInt2, paramRenderedImage);
/*      */         }
/*      */ 
/*      */         
/*  815 */         return new ImageData(ImageDataType.TYPE_BYTE_ARRAY, paramInt1 * paramInt2 * this.depth * this.unitsPerPixel, paramInt1, paramInt2);
/*      */ 
/*      */ 
/*      */       
/*      */       case null:
/*      */       case null:
/*      */       case null:
/*  822 */         if (paramRenderedImage != null) {
/*  823 */           return new ImageData(ImageDataType.TYPE_INT_ARRAY, paramInt1 * paramInt2 * this.depth * this.unitsPerPixel, paramInt1, paramInt2, paramRenderedImage);
/*      */         }
/*      */ 
/*      */         
/*  827 */         return new ImageData(ImageDataType.TYPE_INT_ARRAY, paramInt1 * paramInt2 * this.depth * this.unitsPerPixel, paramInt1, paramInt2);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  832 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateImageDataPowerOfTwo(int paramInt) {
/*  837 */     assert this.enforceNonPowerOfTwoSupport;
/*  838 */     BufferedImage bufferedImage1 = this.imageData.createBufferedImage(paramInt);
/*  839 */     BufferedImage bufferedImage2 = this.powerOfTwoATOp.filter(bufferedImage1, null);
/*  840 */     copySupportedImageToImageData(bufferedImage2, 0, this.imageDataPowerOfTwo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  849 */   ImageData createRenderedImageDataObject(RenderedImage paramRenderedImage) { return createRenderedImageDataObject(paramRenderedImage, this.width, this.height); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copySupportedImageToImageData(RenderedImage paramRenderedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, ImageData paramImageData) {
/*      */     int i13, i12, i11;
/*  861 */     assert paramImageData != null;
/*      */     
/*  863 */     ColorModel colorModel = paramRenderedImage.getColorModel();
/*      */     
/*  865 */     int i = paramRenderedImage.getTileGridXOffset();
/*  866 */     int j = paramRenderedImage.getTileGridYOffset();
/*  867 */     int k = paramRenderedImage.getMinTileX();
/*  868 */     int m = paramRenderedImage.getMinTileY();
/*  869 */     this.tilew = paramRenderedImage.getTileWidth();
/*  870 */     this.tileh = paramRenderedImage.getTileHeight();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  875 */     float f = (paramInt1 - i) / this.tilew;
/*  876 */     if (f < 0.0F) {
/*  877 */       k = (int)(f - 1.0F);
/*      */     } else {
/*  879 */       k = (int)f;
/*      */     } 
/*      */     
/*  882 */     f = (paramInt2 - j) / this.tileh;
/*  883 */     if (f < 0.0F) {
/*  884 */       m = (int)(f - 1.0F);
/*      */     } else {
/*  886 */       m = (int)f;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  891 */     int n = k * this.tilew + i;
/*  892 */     int i1 = m * this.tileh + j;
/*      */ 
/*      */     
/*  895 */     int i2 = n + this.tilew - paramInt1;
/*  896 */     int i3 = i1 + this.tileh - paramInt2;
/*      */ 
/*      */ 
/*      */     
/*  900 */     if (i2 > paramInt6) {
/*  901 */       i2 = paramInt6;
/*      */     }
/*      */     
/*  904 */     if (i3 > paramInt7) {
/*  905 */       i3 = paramInt7;
/*      */     }
/*      */ 
/*      */     
/*  909 */     int i4 = i2;
/*      */ 
/*      */     
/*  912 */     int i5 = paramInt6;
/*  913 */     int i6 = paramInt7;
/*      */ 
/*      */ 
/*      */     
/*  917 */     int i7 = paramInt1 - n;
/*  918 */     int i8 = paramInt2 - i1;
/*      */ 
/*      */ 
/*      */     
/*  922 */     this.numXTiles = (paramInt6 + i7) / this.tilew;
/*  923 */     this.numYTiles = (paramInt7 + i8) / this.tileh;
/*      */     
/*  925 */     if ((paramInt6 + i7) % this.tilew > 0.0F) {
/*  926 */       this.numXTiles++;
/*      */     }
/*      */     
/*  929 */     if ((paramInt7 + i8) % this.tileh > 0.0F) {
/*  930 */       this.numYTiles++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  936 */     Object object = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  944 */     byte[] arrayOfByte = null;
/*  945 */     int[] arrayOfInt = null;
/*      */     
/*  947 */     switch (paramImageData.getType()) {
/*      */       case TYPE_4BYTE_ABGR:
/*  949 */         arrayOfByte = paramImageData.getAsByteArray();
/*      */         break;
/*      */       case TYPE_4BYTE_RGBA:
/*  952 */         arrayOfInt = paramImageData.getAsIntArray();
/*      */         break;
/*      */       default:
/*      */         assert false;
/*      */         break;
/*      */     } 
/*  958 */     int i14 = paramImageData.dataWidth;
/*  959 */     int i15 = paramImageData.dataHeight;
/*      */     
/*  961 */     int i10 = i14 * this.unitsPerPixel;
/*  962 */     if (this.yUp) {
/*      */       
/*  964 */       i13 = (paramInt5 * i14 * i15 + paramInt4 * i14 + paramInt3) * this.unitsPerPixel;
/*  965 */       i11 = 1;
/*  966 */       i12 = i10;
/*      */     } else {
/*      */       
/*  969 */       i13 = (paramInt5 * i14 * i15 + (i15 - paramInt4 - 1) * i14 + paramInt3) * this.unitsPerPixel;
/*  970 */       i11 = -1;
/*  971 */       i12 = -i10;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  980 */     Raster raster = paramRenderedImage.getTile(k, m);
/*  981 */     object = getDataElementBuffer(raster);
/*      */ 
/*      */     
/*  984 */     int i16 = this.tilew * this.unitsPerPixel;
/*      */ 
/*      */     
/*  987 */     for (int i9 = m; i9 < m + this.numYTiles; i9++) {
/*      */       
/*  989 */       int i18 = i13;
/*  990 */       i5 = paramInt6;
/*  991 */       i2 = i4;
/*      */       
/*  993 */       i7 = paramInt1 - n;
/*      */ 
/*      */       
/*  996 */       for (int i17 = k; i17 < k + this.numXTiles; i17++) {
/*      */         int[] arrayOfInt1; byte[] arrayOfByte1;
/*      */         byte b;
/*  999 */         raster = paramRenderedImage.getTile(i17, i9);
/*      */         
/* 1001 */         int i19 = (i8 * this.tilew + i7) * this.unitsPerPixel;
/* 1002 */         int i20 = i18;
/*      */         
/* 1004 */         int i21 = i2 * this.unitsPerPixel;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1009 */         switch (paramImageData.getType()) {
/*      */           case TYPE_4BYTE_ABGR:
/* 1011 */             arrayOfByte1 = ((DataBufferByte)raster.getDataBuffer()).getData();
/* 1012 */             for (b = 0; b < i3; b++) {
/* 1013 */               System.arraycopy(arrayOfByte1, i19, arrayOfByte, i20, i21);
/*      */               
/* 1015 */               i19 += i16;
/* 1016 */               i20 += i12;
/*      */             } 
/*      */             break;
/*      */           case TYPE_4BYTE_RGBA:
/* 1020 */             arrayOfInt1 = ((DataBufferInt)raster.getDataBuffer()).getData();
/* 1021 */             for (b = 0; b < i3; b++) {
/* 1022 */               System.arraycopy(arrayOfInt1, i19, arrayOfInt, i20, i21);
/*      */               
/* 1024 */               i19 += i16;
/* 1025 */               i20 += i12;
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/*      */             assert false;
/*      */             break;
/*      */         } 
/* 1033 */         i18 += i2 * this.unitsPerPixel;
/*      */ 
/*      */         
/* 1036 */         i7 = 0;
/*      */ 
/*      */ 
/*      */         
/* 1040 */         i5 -= i2;
/* 1041 */         if (i5 < this.tilew) {
/* 1042 */           i2 = i5;
/*      */         } else {
/* 1044 */           i2 = this.tilew;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1050 */       i13 += i14 * this.unitsPerPixel * i3 * i11;
/*      */ 
/*      */       
/* 1053 */       i8 = 0;
/*      */ 
/*      */ 
/*      */       
/* 1057 */       i6 -= i3;
/* 1058 */       if (i6 < this.tileh) {
/* 1059 */         i3 = i6;
/*      */       } else {
/* 1061 */         i3 = this.tileh;
/*      */       } 
/*      */     } 
/*      */     
/* 1065 */     if (this.imageData == paramImageData && this.imageDataPowerOfTwo != null)
/* 1066 */       updateImageDataPowerOfTwo(paramInt5); 
/*      */   }
/*      */   
/*      */   void copyImageLineByLine(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, ImageData paramImageData) {
/*      */     int[] arrayOfInt2, arrayOfInt1;
/*      */     byte[] arrayOfByte2, arrayOfByte1;
/*      */     int k;
/*      */     byte b;
/* 1074 */     assert paramImageData != null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1081 */     int m = paramImageData.dataWidth;
/* 1082 */     int n = paramImageData.dataHeight;
/* 1083 */     int i1 = m * this.unitsPerPixel;
/* 1084 */     int i = paramInt2;
/*      */     
/* 1086 */     if (this.yUp) {
/* 1087 */       k = (paramInt5 * m * n + paramInt4 * m + paramInt3) * this.unitsPerPixel;
/*      */     } else {
/* 1089 */       k = (paramInt5 * m * n + (n - paramInt4 - 1) * m + paramInt3) * this.unitsPerPixel;
/* 1090 */       i1 = -1 * i1;
/*      */     } 
/*      */     
/* 1093 */     int i2 = paramInt6 * this.unitsPerPixel;
/* 1094 */     int i3 = paramBufferedImage.getWidth();
/* 1095 */     int i4 = i3 * this.unitsPerPixel;
/* 1096 */     int j = (i * i3 + paramInt1) * this.unitsPerPixel;
/*      */     
/* 1098 */     switch (paramImageData.getType()) {
/*      */       case TYPE_4BYTE_ABGR:
/* 1100 */         arrayOfByte1 = ((DataBufferByte)paramBufferedImage.getRaster().getDataBuffer()).getData();
/* 1101 */         arrayOfByte2 = paramImageData.getAsByteArray();
/* 1102 */         for (b = 0; b < paramInt7; b++) {
/* 1103 */           System.arraycopy(arrayOfByte1, j, arrayOfByte2, k, i2);
/* 1104 */           k += i1;
/* 1105 */           j += i4;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case TYPE_4BYTE_RGBA:
/* 1110 */         arrayOfInt1 = ((DataBufferInt)paramBufferedImage.getRaster().getDataBuffer()).getData();
/* 1111 */         arrayOfInt2 = paramImageData.getAsIntArray();
/* 1112 */         for (b = 0; b < paramInt7; b++) {
/* 1113 */           System.arraycopy(arrayOfInt1, j, arrayOfInt2, k, i2);
/* 1114 */           k += i1;
/* 1115 */           j += i4;
/*      */         } 
/*      */         break;
/*      */       default:
/*      */         assert false;
/*      */         break;
/*      */     } 
/* 1122 */     if (this.imageData == paramImageData && this.imageDataPowerOfTwo != null) {
/* 1123 */       updateImageDataPowerOfTwo(paramInt5);
/*      */     }
/*      */   }
/*      */   
/*      */   void copyImageByBlock(BufferedImage paramBufferedImage, int paramInt, ImageData paramImageData) {
/*      */     int[] arrayOfInt2, arrayOfInt1;
/*      */     byte[] arrayOfByte2, arrayOfByte1;
/* 1130 */     assert paramImageData != null && this.yUp;
/*      */     
/* 1132 */     int i = paramImageData.dataWidth;
/* 1133 */     int j = paramImageData.dataHeight;
/*      */ 
/*      */     
/* 1136 */     int k = paramInt * i * j * this.unitsPerPixel;
/*      */     
/* 1138 */     switch (this.imageData.getType()) {
/*      */       case TYPE_4BYTE_ABGR:
/* 1140 */         arrayOfByte1 = ((DataBufferByte)paramBufferedImage.getRaster().getDataBuffer()).getData();
/* 1141 */         arrayOfByte2 = paramImageData.getAsByteArray();
/* 1142 */         System.arraycopy(arrayOfByte1, 0, arrayOfByte2, k, i * j * this.unitsPerPixel);
/*      */         break;
/*      */       case TYPE_4BYTE_RGBA:
/* 1145 */         arrayOfInt1 = ((DataBufferInt)paramBufferedImage.getRaster().getDataBuffer()).getData();
/* 1146 */         arrayOfInt2 = paramImageData.getAsIntArray();
/* 1147 */         System.arraycopy(arrayOfInt1, 0, arrayOfInt2, k, i * j * this.unitsPerPixel);
/*      */         break;
/*      */       default:
/*      */         assert false;
/*      */         break;
/*      */     } 
/* 1153 */     if (this.imageData == paramImageData && this.imageDataPowerOfTwo != null) {
/* 1154 */       updateImageDataPowerOfTwo(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copySupportedImageToImageData(RenderedImage paramRenderedImage, int paramInt, ImageData paramImageData) {
/* 1164 */     if (paramRenderedImage instanceof BufferedImage) {
/* 1165 */       if (this.yUp) {
/*      */ 
/*      */         
/* 1168 */         copyImageByBlock((BufferedImage)paramRenderedImage, paramInt, paramImageData);
/*      */       }
/*      */       else {
/*      */         
/* 1172 */         copyImageLineByLine((BufferedImage)paramRenderedImage, 0, 0, 0, 0, paramInt, paramImageData.dataWidth, paramImageData.dataHeight, paramImageData);
/*      */       } 
/*      */     } else {
/*      */       
/* 1176 */       copySupportedImageToImageData(paramRenderedImage, paramRenderedImage.getMinX(), paramRenderedImage.getMinY(), 0, 0, paramInt, paramImageData.dataWidth, paramImageData.dataHeight, paramImageData);
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
/*      */   void copyUnsupportedNioImageToImageData(NioImageBuffer paramNioImageBuffer, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, ImageData paramImageData) {
/* 1203 */     if (MasterControl.isDevLoggable(Level.INFO)) {
/* 1204 */       MasterControl.getDevLogger().info("ImageComponent - Copying Unsupported NioImage, use a different image type");
/*      */     }
/*      */     
/* 1207 */     assert paramImageData.getType() == ImageDataType.TYPE_BYTE_BUFFER;
/* 1208 */     assert getImageFormatType() == ImageFormatType.TYPE_BYTE_RGBA;
/*      */     
/* 1210 */     int i = paramInt5 * paramInt6;
/* 1211 */     ByteBuffer byteBuffer1 = (ByteBuffer)paramNioImageBuffer.getDataBuffer();
/* 1212 */     byteBuffer1.rewind();
/* 1213 */     ByteBuffer byteBuffer2 = paramImageData.getAsByteBuffer();
/* 1214 */     byteBuffer2.rewind();
/*      */ 
/*      */     
/* 1217 */     for (byte b = 0; b < i; b += 4) {
/* 1218 */       byteBuffer2.put(b, byteBuffer1.get(b + 3));
/* 1219 */       byteBuffer2.put(b + 1, byteBuffer1.get(b + 2));
/* 1220 */       byteBuffer2.put(b + 2, byteBuffer1.get(b + 1));
/* 1221 */       byteBuffer2.put(b + 3, byteBuffer1.get(b));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copyUnsupportedImageToImageData(RenderedImage paramRenderedImage, int paramInt, ImageData paramImageData) {
/* 1230 */     assert paramImageData.getType() == ImageDataType.TYPE_BYTE_ARRAY;
/*      */     
/* 1232 */     if (MasterControl.isDevLoggable(Level.INFO)) {
/* 1233 */       MasterControl.getDevLogger().info("ImageComponent - Copying Unsupported Image, use a different image type");
/*      */     }
/*      */     
/* 1236 */     if (paramRenderedImage instanceof BufferedImage) {
/* 1237 */       copyUnsupportedImageToImageData((BufferedImage)paramRenderedImage, 0, 0, 0, 0, paramInt, paramImageData.dataWidth, paramImageData.dataHeight, paramImageData);
/*      */     } else {
/*      */       
/* 1240 */       copyUnsupportedImageToImageData(paramRenderedImage, paramRenderedImage.getMinX(), paramRenderedImage.getMinY(), 0, 0, paramInt, paramImageData.dataWidth, paramImageData.dataHeight, paramImageData);
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
/*      */   void copyUnsupportedImageToImageData(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, ImageData paramImageData) {
/*      */     int m, j;
/*      */     byte b;
/* 1257 */     int i = paramInt2;
/* 1258 */     int k = 1;
/*      */     
/* 1260 */     assert paramImageData != null;
/*      */     
/* 1262 */     int n = paramImageData.dataWidth;
/* 1263 */     int i1 = paramImageData.dataHeight;
/* 1264 */     int i2 = n * this.unitsPerPixel;
/*      */     
/* 1266 */     if (this.yUp) {
/* 1267 */       j = (paramInt5 * n * i1 + paramInt4 * n + paramInt3) * this.unitsPerPixel;
/*      */     } else {
/* 1269 */       j = (paramInt5 * n * i1 + (i1 - paramInt4 - 1) * n + paramInt3) * this.unitsPerPixel;
/* 1270 */       i2 = -1 * i2;
/*      */     } 
/*      */     
/* 1273 */     WritableRaster writableRaster = paramBufferedImage.getRaster();
/* 1274 */     ColorModel colorModel = paramBufferedImage.getColorModel();
/* 1275 */     Object object = getDataElementBuffer(writableRaster);
/*      */     
/* 1277 */     byte[] arrayOfByte = paramImageData.getAsByteArray();
/*      */     
/* 1279 */     switch (this.numberOfComponents) {
/*      */       case 4:
/* 1281 */         m = i; b = 0;
/* 1282 */         for (; b < paramInt7; b++, m += k) {
/* 1283 */           int i4 = j;
/* 1284 */           for (int i3 = paramInt1; i3 < paramInt6 + paramInt1; i3++) {
/* 1285 */             writableRaster.getDataElements(i3, m, object);
/* 1286 */             arrayOfByte[i4++] = (byte)colorModel.getRed(object);
/* 1287 */             arrayOfByte[i4++] = (byte)colorModel.getGreen(object);
/* 1288 */             arrayOfByte[i4++] = (byte)colorModel.getBlue(object);
/* 1289 */             arrayOfByte[i4++] = (byte)colorModel.getAlpha(object);
/*      */           } 
/* 1291 */           j += i2;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/* 1297 */         m = i; b = 0;
/* 1298 */         for (; b < paramInt7; b++, m += k) {
/* 1299 */           int i4 = j;
/* 1300 */           for (int i3 = paramInt1; i3 < paramInt6 + paramInt1; i3++) {
/* 1301 */             writableRaster.getDataElements(i3, m, object);
/* 1302 */             arrayOfByte[i4++] = (byte)colorModel.getRed(object);
/* 1303 */             arrayOfByte[i4++] = (byte)colorModel.getGreen(object);
/* 1304 */             arrayOfByte[i4++] = (byte)colorModel.getBlue(object);
/*      */           } 
/* 1306 */           j += i2;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/* 1312 */         m = i; b = 0;
/* 1313 */         for (; b < paramInt7; b++, m += k) {
/* 1314 */           int i4 = j;
/* 1315 */           for (int i3 = paramInt1; i3 < paramInt6 + paramInt1; i3++) {
/* 1316 */             writableRaster.getDataElements(i3, m, object);
/* 1317 */             arrayOfByte[i4++] = (byte)colorModel.getRed(object);
/* 1318 */             arrayOfByte[i4++] = (byte)colorModel.getAlpha(object);
/*      */           } 
/* 1320 */           j += i2;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1:
/* 1326 */         m = i; b = 0;
/* 1327 */         for (; b < paramInt7; b++, m += k) {
/* 1328 */           int i4 = j;
/* 1329 */           for (int i3 = paramInt1; i3 < paramInt6 + paramInt1; i3++) {
/* 1330 */             writableRaster.getDataElements(i3, m, object);
/* 1331 */             arrayOfByte[i4++] = (byte)colorModel.getRed(object);
/*      */           } 
/* 1333 */           j += i2;
/*      */         } 
/*      */         break;
/*      */       
/*      */       default:
/*      */         assert false;
/*      */         break;
/*      */     } 
/* 1341 */     if (this.imageData == paramImageData && this.imageDataPowerOfTwo != null) {
/* 1342 */       updateImageDataPowerOfTwo(paramInt5);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copyUnsupportedImageToImageData(RenderedImage paramRenderedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, ImageData paramImageData) {
/*      */     int m, k, j, i;
/* 1351 */     Object object = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1361 */     ColorModel colorModel = paramRenderedImage.getColorModel();
/*      */     
/* 1363 */     int n = paramRenderedImage.getTileGridXOffset();
/* 1364 */     int i1 = paramRenderedImage.getTileGridYOffset();
/* 1365 */     int i2 = paramRenderedImage.getMinTileX();
/* 1366 */     int i3 = paramRenderedImage.getMinTileY();
/* 1367 */     this.tilew = paramRenderedImage.getTileWidth();
/* 1368 */     this.tileh = paramRenderedImage.getTileHeight();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1374 */     float f = (paramInt1 - n) / this.tilew;
/* 1375 */     if (f < 0.0F) {
/* 1376 */       i2 = (int)(f - 1.0F);
/*      */     } else {
/* 1378 */       i2 = (int)f;
/*      */     } 
/*      */     
/* 1381 */     f = (paramInt2 - i1) / this.tileh;
/* 1382 */     if (f < 0.0F) {
/* 1383 */       i3 = (int)(f - 1.0F);
/*      */     } else {
/* 1385 */       i3 = (int)f;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1390 */     int i4 = i2 * this.tilew + n;
/* 1391 */     int i5 = i3 * this.tileh + i1;
/*      */ 
/*      */ 
/*      */     
/* 1395 */     int i6 = i4 + this.tilew - paramInt1;
/* 1396 */     int i7 = i5 + this.tileh - paramInt2;
/*      */ 
/*      */ 
/*      */     
/* 1400 */     if (i6 > paramInt6) {
/* 1401 */       i6 = paramInt6;
/*      */     }
/*      */     
/* 1404 */     if (i7 > paramInt7) {
/* 1405 */       i7 = paramInt7;
/*      */     }
/*      */ 
/*      */     
/* 1409 */     int i8 = i6;
/*      */ 
/*      */ 
/*      */     
/* 1413 */     int i9 = paramInt6;
/* 1414 */     int i10 = paramInt7;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1419 */     int i11 = paramInt1 - i4;
/* 1420 */     int i12 = paramInt2 - i5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1426 */     this.numXTiles = (paramInt6 + i11) / this.tilew;
/* 1427 */     this.numYTiles = (paramInt7 + i12) / this.tileh;
/*      */     
/* 1429 */     if ((paramInt6 + i11) % this.tilew > 0.0F) {
/* 1430 */       this.numXTiles++;
/*      */     }
/*      */     
/* 1433 */     if ((paramInt7 + i12) % this.tileh > 0.0F) {
/* 1434 */       this.numYTiles++;
/*      */     }
/*      */     
/* 1437 */     assert paramImageData != null;
/* 1438 */     int i13 = paramImageData.dataWidth;
/* 1439 */     int i14 = paramImageData.dataHeight;
/* 1440 */     int i15 = i13 * this.unitsPerPixel;
/*      */     
/* 1442 */     if (this.yUp) {
/*      */       
/* 1444 */       m = (paramInt5 * i13 * i14 + paramInt4 * i13 + paramInt3) * this.unitsPerPixel;
/* 1445 */       j = 1;
/* 1446 */       k = i15;
/*      */     } else {
/*      */       
/* 1449 */       m = (paramInt5 * i13 * i14 + (i14 - paramInt4 - 1) * i13 + paramInt3) * this.unitsPerPixel;
/* 1450 */       j = -1;
/* 1451 */       k = -i15;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1460 */     Raster raster = paramRenderedImage.getTile(i2, i3);
/* 1461 */     object = getDataElementBuffer(raster);
/* 1462 */     byte[] arrayOfByte = this.imageData.getAsByteArray();
/*      */     
/* 1464 */     switch (this.numberOfComponents) {
/*      */       
/*      */       case 4:
/* 1467 */         for (i = i3; i < i3 + this.numYTiles; i++) {
/*      */           
/* 1469 */           int i17 = m;
/* 1470 */           i9 = paramInt6;
/* 1471 */           i6 = i8;
/*      */           
/* 1473 */           i11 = paramInt1 - i4;
/*      */ 
/*      */           
/* 1476 */           for (int i16 = i2; i16 < i2 + this.numXTiles; i16++) {
/*      */ 
/*      */             
/* 1479 */             raster = paramRenderedImage.getTile(i16, i);
/*      */             
/* 1481 */             int i19 = i17;
/* 1482 */             int i20 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1487 */             for (int i18 = i12; i18 < i12 + i7; i18++) {
/*      */               
/* 1489 */               for (int i21 = i11; i21 < i11 + i6; i21++) {
/* 1490 */                 raster.getDataElements(i21, i18, object);
/* 1491 */                 arrayOfByte[i19++] = (byte)colorModel.getRed(object);
/* 1492 */                 arrayOfByte[i19++] = (byte)colorModel.getGreen(object);
/* 1493 */                 arrayOfByte[i19++] = (byte)colorModel.getBlue(object);
/* 1494 */                 arrayOfByte[i19++] = (byte)colorModel.getAlpha(object);
/*      */               } 
/* 1496 */               i20 += k;
/* 1497 */               i19 = i17 + i20;
/*      */             } 
/*      */ 
/*      */             
/* 1501 */             i17 += i6 * this.unitsPerPixel;
/*      */ 
/*      */             
/* 1504 */             i11 = 0;
/*      */ 
/*      */ 
/*      */             
/* 1508 */             i9 -= i6;
/* 1509 */             if (i9 < this.tilew) {
/* 1510 */               i6 = i9;
/*      */             } else {
/* 1512 */               i6 = this.tilew;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1519 */           m += i13 * this.unitsPerPixel * i7 * j;
/*      */ 
/*      */           
/* 1522 */           i12 = 0;
/*      */ 
/*      */ 
/*      */           
/* 1526 */           i10 -= i7;
/* 1527 */           if (i10 < this.tileh) {
/* 1528 */             i7 = i10;
/*      */           } else {
/* 1530 */             i7 = this.tileh;
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/* 1536 */         for (i = i3; i < i3 + this.numYTiles; i++) {
/*      */           
/* 1538 */           int i17 = m;
/* 1539 */           i9 = paramInt6;
/* 1540 */           i6 = i8;
/*      */           
/* 1542 */           i11 = paramInt1 - i4;
/*      */ 
/*      */           
/* 1545 */           for (int i16 = i2; i16 < i2 + this.numXTiles; i16++) {
/*      */ 
/*      */             
/* 1548 */             raster = paramRenderedImage.getTile(i16, i);
/*      */             
/* 1550 */             int i19 = i17;
/* 1551 */             int i20 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1556 */             for (int i18 = i12; i18 < i12 + i7; i18++) {
/*      */               
/* 1558 */               for (int i21 = i11; i21 < i11 + i6; i21++) {
/* 1559 */                 raster.getDataElements(i21, i18, object);
/* 1560 */                 arrayOfByte[i19++] = (byte)colorModel.getRed(object);
/* 1561 */                 arrayOfByte[i19++] = (byte)colorModel.getGreen(object);
/* 1562 */                 arrayOfByte[i19++] = (byte)colorModel.getBlue(object);
/*      */               } 
/* 1564 */               i20 += k;
/* 1565 */               i19 = i17 + i20;
/*      */             } 
/*      */ 
/*      */             
/* 1569 */             i17 += i6 * this.unitsPerPixel;
/*      */ 
/*      */             
/* 1572 */             i11 = 0;
/*      */ 
/*      */ 
/*      */             
/* 1576 */             i9 -= i6;
/* 1577 */             if (i9 < this.tilew) {
/* 1578 */               i6 = i9;
/*      */             } else {
/* 1580 */               i6 = this.tilew;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1587 */           m += i13 * this.unitsPerPixel * i7 * j;
/*      */ 
/*      */           
/* 1590 */           i12 = 0;
/*      */ 
/*      */ 
/*      */           
/* 1594 */           i10 -= i7;
/* 1595 */           if (i10 < this.tileh) {
/* 1596 */             i7 = i10;
/*      */           } else {
/* 1598 */             i7 = this.tileh;
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 2:
/* 1604 */         for (i = i3; i < i3 + this.numYTiles; i++) {
/*      */           
/* 1606 */           int i17 = m;
/* 1607 */           i9 = paramInt6;
/* 1608 */           i6 = i8;
/*      */           
/* 1610 */           i11 = paramInt1 - i4;
/*      */ 
/*      */           
/* 1613 */           for (int i16 = i2; i16 < i2 + this.numXTiles; i16++) {
/*      */ 
/*      */             
/* 1616 */             raster = paramRenderedImage.getTile(i16, i);
/*      */             
/* 1618 */             int i19 = i17;
/* 1619 */             int i20 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1624 */             for (int i18 = i12; i18 < i12 + i7; i18++) {
/*      */               
/* 1626 */               for (int i21 = i11; i21 < i11 + i6; i21++) {
/* 1627 */                 raster.getDataElements(i21, i18, object);
/* 1628 */                 arrayOfByte[i19++] = (byte)colorModel.getRed(object);
/* 1629 */                 arrayOfByte[i19++] = (byte)colorModel.getAlpha(object);
/*      */               } 
/* 1631 */               i20 += k;
/* 1632 */               i19 = i17 + i20;
/*      */             } 
/*      */ 
/*      */             
/* 1636 */             i17 += i6 * this.unitsPerPixel;
/*      */ 
/*      */             
/* 1639 */             i11 = 0;
/*      */ 
/*      */ 
/*      */             
/* 1643 */             i9 -= i6;
/* 1644 */             if (i9 < this.tilew) {
/* 1645 */               i6 = i9;
/*      */             } else {
/* 1647 */               i6 = this.tilew;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1655 */           m += i13 * this.unitsPerPixel * i7 * j;
/*      */ 
/*      */ 
/*      */           
/* 1659 */           i12 = 0;
/*      */ 
/*      */ 
/*      */           
/* 1663 */           i10 -= i7;
/* 1664 */           if (i10 < this.tileh) {
/* 1665 */             i7 = i10;
/*      */           } else {
/* 1667 */             i7 = this.tileh;
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 1:
/* 1673 */         for (i = i3; i < i3 + this.numYTiles; i++) {
/*      */           
/* 1675 */           int i17 = m;
/* 1676 */           i9 = paramInt6;
/* 1677 */           i6 = i8;
/*      */           
/* 1679 */           i11 = paramInt1 - i4;
/*      */ 
/*      */           
/* 1682 */           for (int i16 = i2; i16 < i2 + this.numXTiles; i16++) {
/*      */ 
/*      */             
/* 1685 */             raster = paramRenderedImage.getTile(i16, i);
/*      */             
/* 1687 */             int i19 = i17;
/* 1688 */             int i20 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1693 */             for (int i18 = i12; i18 < i12 + i7; i18++) {
/*      */               
/* 1695 */               for (int i21 = i11; i21 < i11 + i6; i21++) {
/* 1696 */                 raster.getDataElements(i21, i18, object);
/* 1697 */                 arrayOfByte[i19++] = (byte)colorModel.getRed(object);
/*      */               } 
/* 1699 */               i20 += k;
/* 1700 */               i19 = i17 + i20;
/*      */             } 
/*      */ 
/*      */             
/* 1704 */             i17 += i6 * this.unitsPerPixel;
/*      */ 
/*      */             
/* 1707 */             i11 = 0;
/*      */ 
/*      */ 
/*      */             
/* 1711 */             i9 -= i6;
/* 1712 */             if (i9 < this.tilew) {
/* 1713 */               i6 = i9;
/*      */             } else {
/* 1715 */               i6 = this.tilew;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1721 */           m += i13 * this.unitsPerPixel * i7 * j;
/*      */ 
/*      */           
/* 1724 */           i12 = 0;
/*      */ 
/*      */ 
/*      */           
/* 1728 */           i10 -= i7;
/* 1729 */           if (i10 < this.tileh) {
/* 1730 */             i7 = i10;
/*      */           } else {
/* 1732 */             i7 = this.tileh;
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       
/*      */       default:
/*      */         assert false;
/*      */         break;
/*      */     } 
/*      */     
/* 1742 */     if (this.imageData == paramImageData && this.imageDataPowerOfTwo != null) {
/* 1743 */       updateImageDataPowerOfTwo(paramInt5);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void evaluateExtensions(Canvas3D paramCanvas3D) {
/* 1751 */     synchronized (this.evaluateExtLock) {
/*      */ 
/*      */       
/* 1754 */       evaluateExtABGR(paramCanvas3D.extensionsSupported);
/* 1755 */       evaluateExtNonPowerOfTwo(paramCanvas3D.textureExtendedFeatures);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void evaluateExtABGR(int paramInt) {
/* 1766 */     if (!this.abgrSupported) {
/*      */       return;
/*      */     }
/*      */     
/* 1770 */     if (getImageFormatType() != ImageFormatType.TYPE_BYTE_ABGR) {
/*      */       return;
/*      */     }
/*      */     
/* 1774 */     if ((paramInt & 0x2) != 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1779 */     this.abgrSupported = false;
/* 1780 */     convertImageDataFromABGRToRGBA();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getClosestPowerOf2(int paramInt) {
/* 1786 */     if (paramInt < 1) {
/* 1787 */       return paramInt;
/*      */     }
/* 1789 */     int i = 1;
/*      */     do {
/* 1791 */       i *= 2;
/* 1792 */     } while (paramInt >= i);
/*      */     
/* 1794 */     int j = i / 2;
/* 1795 */     if (i - paramInt > paramInt - j)
/*      */     {
/* 1797 */       return j;
/*      */     }
/* 1799 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getCeilPowerOf2(int paramInt) {
/* 1806 */     if (paramInt < 1) {
/* 1807 */       return paramInt;
/*      */     }
/* 1809 */     byte b = 1;
/*      */     do {
/* 1811 */       b *= 2;
/* 1812 */     } while (paramInt > b);
/*      */     
/* 1814 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void evaluateExtNonPowerOfTwo(int paramInt) {
/*      */     int j, i;
/* 1821 */     if (!this.enforceNonPowerOfTwoSupport) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1827 */     if (!this.npotSupported) {
/*      */       return;
/*      */     }
/*      */     
/* 1831 */     if (this.imageData == null && !isByReference()) {
/*      */       return;
/*      */     }
/*      */     
/* 1835 */     if ((paramInt & 0x8000) != 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1840 */     this.npotSupported = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1845 */     if (this.width * this.height < 262144) {
/* 1846 */       i = getCeilPowerOf2(this.width);
/* 1847 */       j = getCeilPowerOf2(this.height);
/*      */     } else {
/* 1849 */       i = getClosestPowerOf2(this.width);
/* 1850 */       j = getClosestPowerOf2(this.height);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1855 */     float f1 = i / this.width;
/* 1856 */     float f2 = j / this.height;
/*      */ 
/*      */     
/* 1859 */     if (f1 != 1.0F || f2 != 1.0F) {
/*      */       
/* 1861 */       if (this.imageData == null) {
/*      */ 
/*      */         
/* 1864 */         RenderedImage renderedImage = (RenderedImage)getRefImage(0);
/*      */         
/* 1866 */         assert !(renderedImage instanceof BufferedImage);
/*      */ 
/*      */         
/* 1869 */         ColorModel colorModel = renderedImage.getColorModel();
/* 1870 */         WritableRaster writableRaster = renderedImage.copyData(null);
/* 1871 */         renderedImage = new BufferedImage(colorModel, writableRaster, colorModel.isAlphaPremultiplied(), null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1878 */         this.imageData = createRenderedImageDataObject(null);
/* 1879 */         copySupportedImageToImageData(renderedImage, 0, this.imageData);
/*      */       } 
/*      */ 
/*      */       
/* 1883 */       assert this.imageData != null;
/*      */ 
/*      */       
/* 1886 */       BufferedImage bufferedImage1 = this.imageData.createBufferedImage(0);
/*      */       
/* 1888 */       int k = bufferedImage1.getType();
/* 1889 */       BufferedImage bufferedImage2 = new BufferedImage(i, j, k);
/*      */       
/* 1891 */       AffineTransform affineTransform = AffineTransform.getScaleInstance(f1, f2);
/*      */ 
/*      */       
/* 1894 */       this.powerOfTwoATOp = new AffineTransformOp(affineTransform, 2);
/*      */ 
/*      */       
/* 1897 */       this.powerOfTwoATOp.filter(bufferedImage1, bufferedImage2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1902 */       this.imageDataPowerOfTwo = createRenderedImageDataObject(null, i, j);
/*      */       
/* 1904 */       copySupportedImageToImageData(bufferedImage2, 0, this.imageDataPowerOfTwo);
/*      */     } else {
/*      */       
/* 1907 */       this.imageDataPowerOfTwo = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void convertImageDataFromABGRToRGBA() {
/* 1914 */     this.imageFormatType = ImageFormatType.TYPE_BYTE_RGBA;
/* 1915 */     this.imageTypeIsSupported = false;
/*      */ 
/*      */     
/* 1918 */     this.imageData.convertFromABGRToRGBA();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copyToRefImage(int paramInt) {
/*      */     int[] arrayOfInt2, arrayOfInt1;
/*      */     byte[] arrayOfByte2, arrayOfByte1;
/*      */     int k;
/*      */     byte b;
/* 1932 */     assert this.refImage[paramInt] != null;
/* 1933 */     assert this.refImage[paramInt] instanceof BufferedImage;
/*      */     
/* 1935 */     BufferedImage bufferedImage = (BufferedImage)this.refImage[paramInt];
/* 1936 */     int m = this.width * this.unitsPerPixel;
/* 1937 */     int i = 0;
/*      */     
/* 1939 */     if (this.yUp) {
/* 1940 */       k = paramInt * this.width * this.height * this.unitsPerPixel;
/*      */     } else {
/* 1942 */       k = (paramInt * this.width * this.height + (this.height - 1) * this.width) * this.unitsPerPixel;
/* 1943 */       m = -1 * m;
/*      */     } 
/*      */     
/* 1946 */     int n = this.width * this.unitsPerPixel;
/* 1947 */     int j = i * this.width * this.unitsPerPixel;
/*      */     
/* 1949 */     switch (this.imageData.getType()) {
/*      */       case TYPE_4BYTE_ABGR:
/* 1951 */         arrayOfByte1 = ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
/* 1952 */         arrayOfByte2 = this.imageData.getAsByteArray();
/* 1953 */         for (b = 0; b < this.height; b++) {
/* 1954 */           System.arraycopy(arrayOfByte2, j, arrayOfByte1, k, n);
/* 1955 */           k += m;
/* 1956 */           j += n;
/*      */         } 
/*      */         return;
/*      */       
/*      */       case TYPE_4BYTE_RGBA:
/* 1961 */         arrayOfInt1 = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
/* 1962 */         arrayOfInt2 = this.imageData.getAsIntArray();
/* 1963 */         for (b = 0; b < this.height; b++) {
/* 1964 */           System.arraycopy(arrayOfInt2, j, arrayOfInt1, k, n);
/* 1965 */           k += m;
/* 1966 */           j += n;
/*      */         } 
/*      */         return;
/*      */     } 
/*      */     assert false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copyToRefImageWithFormatConversion(int paramInt) {
/*      */     byte[] arrayOfByte2;
/*      */     int arrayOfInt[], j, i;
/*      */     boolean bool;
/*      */     byte b;
/* 1982 */     assert this.refImage[paramInt] != null;
/* 1983 */     assert this.refImage[paramInt] instanceof BufferedImage;
/*      */     
/* 1985 */     BufferedImage bufferedImage = (BufferedImage)this.refImage[paramInt];
/* 1986 */     int k = bufferedImage.getType();
/* 1987 */     byte[] arrayOfByte1 = this.imageData.getAsByteArray();
/*      */ 
/*      */     
/* 1990 */     if (!this.yUp) {
/* 1991 */       j = -1 * this.width;
/* 1992 */       i = (this.height - 1) * this.width;
/* 1993 */       int m = this.height - 1;
/* 1994 */       byte b1 = -1;
/*      */     } else {
/* 1996 */       j = this.width;
/* 1997 */       i = 0;
/* 1998 */       boolean bool1 = false;
/* 1999 */       boolean bool2 = true;
/*      */     } 
/*      */     
/* 2002 */     switch (k) {
/*      */       case 2:
/* 2004 */         arrayOfInt = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
/*      */ 
/*      */         
/* 2007 */         bool = false;
/* 2008 */         switch (this.imageFormatType) {
/*      */           case TYPE_3BYTE_BGR:
/* 2010 */             for (b = 0; b < this.height; b++, i += j) {
/* 2011 */               byte b2 = i;
/* 2012 */               for (byte b1 = 0; b1 < this.width; b1++, bool += true, b2++) {
/* 2013 */                 arrayOfInt[b2] = (arrayOfByte1[bool + 3] & 0xFF) << 24 | (arrayOfByte1[bool] & 0xFF) << 16 | (arrayOfByte1[bool + true] & 0xFF) << 8 | arrayOfByte1[bool + 2] & 0xFF;
/*      */               }
/*      */             } 
/*      */             return;
/*      */ 
/*      */ 
/*      */           
/*      */           case TYPE_4BYTE_RGBA:
/* 2021 */             for (b = 0; b < this.height; b++, i += j) {
/* 2022 */               int m = i;
/* 2023 */               for (byte b1 = 0; b1 < this.width; b1++, bool += true, m++) {
/* 2024 */                 arrayOfInt[m] = 0xFF000000 | (arrayOfByte1[bool] & 0xFF) << 16 | (arrayOfByte1[bool + true] & 0xFF) << 8 | arrayOfByte1[bool + 2] & 0xFF;
/*      */               }
/*      */             } 
/*      */             return;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         assert false;
/*      */         return;
/*      */ 
/*      */       
/*      */       case 1:
/* 2037 */         arrayOfInt = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
/*      */ 
/*      */         
/* 2040 */         bool = false;
/* 2041 */         for (b = 0; b < this.height; b++, i += j) {
/* 2042 */           int m = i;
/* 2043 */           for (byte b1 = 0; b1 < this.width; b1++, bool += true, m++) {
/* 2044 */             arrayOfInt[m] = 0xFF000000 | (arrayOfByte1[bool] & 0xFF) << 16 | (arrayOfByte1[bool + true] & 0xFF) << 8 | arrayOfByte1[bool + 2] & 0xFF;
/*      */           }
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/* 2053 */         arrayOfByte2 = ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
/*      */ 
/*      */         
/* 2056 */         bool = false;
/*      */ 
/*      */         
/* 2059 */         i <<= 2;
/*      */         
/* 2061 */         switch (this.imageFormatType) {
/*      */           case TYPE_3BYTE_BGR:
/* 2063 */             for (b = 0; b < this.height; b++, i += (j << 2)) {
/* 2064 */               int m = i;
/* 2065 */               for (byte b1 = 0; b1 < this.width; b1++, bool += true) {
/* 2066 */                 arrayOfByte2[m++] = arrayOfByte1[bool + 3];
/* 2067 */                 arrayOfByte2[m++] = arrayOfByte1[bool + 2];
/* 2068 */                 arrayOfByte2[m++] = arrayOfByte1[bool + true];
/* 2069 */                 arrayOfByte2[m++] = arrayOfByte1[bool];
/*      */               } 
/*      */             } 
/*      */             return;
/*      */           case TYPE_4BYTE_RGBA:
/* 2074 */             for (b = 0; b < this.height; b++, i += (j << 2)) {
/* 2075 */               int m = i;
/* 2076 */               for (byte b1 = 0; b1 < this.width; b1++, bool += true) {
/* 2077 */                 arrayOfByte2[m++] = -1;
/* 2078 */                 arrayOfByte2[m++] = arrayOfByte1[bool + 2];
/* 2079 */                 arrayOfByte2[m++] = arrayOfByte1[bool + true];
/* 2080 */                 arrayOfByte2[m++] = arrayOfByte1[bool];
/*      */               } 
/*      */             } 
/*      */             return;
/*      */         } 
/*      */         
/*      */         assert false;
/*      */         return;
/*      */       
/*      */       case 4:
/* 2090 */         arrayOfInt = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
/*      */ 
/*      */         
/* 2093 */         bool = false;
/*      */         
/* 2095 */         for (b = 0; b < this.height; b++, i += j) {
/* 2096 */           int m = i;
/* 2097 */           for (byte b1 = 0; b1 < this.width; b1++, bool += true, m++) {
/* 2098 */             arrayOfInt[m] = 0xFF000000 | arrayOfByte1[bool] & 0xFF | (arrayOfByte1[bool + true] & 0xFF) << 8 | (arrayOfByte1[bool + 2] & 0xFF) << 16;
/*      */           }
/*      */         } 
/*      */         return;
/*      */     } 
/*      */     assert false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2113 */   void addUser(NodeComponentRetained paramNodeComponentRetained) { this.userList.add(paramNodeComponentRetained); }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeUser(NodeComponentRetained paramNodeComponentRetained) {
/* 2118 */     int i = this.userList.indexOf(paramNodeComponentRetained);
/* 2119 */     if (i >= 0) {
/* 2120 */       this.userList.remove(i);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(boolean paramBoolean, int paramInt) {
/* 2131 */     if (getUsedByOffScreen()) {
/* 2132 */       throw new IllegalSharingException(J3dI18N.getString("ImageComponent3"));
/*      */     }
/* 2134 */     super.setLive(paramBoolean, paramInt);
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
/*      */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 2148 */     if ((paramInt & true) != 0 || (paramInt & 0x2) != 0)
/*      */     {
/* 2150 */       synchronized (this.userList) {
/* 2151 */         for (int i = this.userList.size() - 1; i >= 0; i--) {
/* 2152 */           Object object = this.userList.get(i);
/* 2153 */           if (object != null) {
/* 2154 */             if (object instanceof TextureRetained) {
/* 2155 */               ((TextureRetained)object).notifyImageComponentImageChanged(this, (ImageComponentUpdateInfo)paramObject);
/* 2156 */             } else if (object instanceof RasterRetained) {
/* 2157 */               ((RasterRetained)object).notifyImageComponentImageChanged(this, (ImageComponentUpdateInfo)paramObject);
/* 2158 */             } else if (object instanceof BackgroundRetained) {
/* 2159 */               ((BackgroundRetained)object).notifyImageComponentImageChanged(this, (ImageComponentUpdateInfo)paramObject);
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   final void sendMessage(int paramInt, Object paramObject) {
/* 2169 */     J3dMessage j3dMessage = new J3dMessage();
/* 2170 */     j3dMessage.threads = 1152;
/*      */     
/* 2172 */     j3dMessage.type = 54;
/* 2173 */     j3dMessage.universe = null;
/* 2174 */     j3dMessage.args[0] = this;
/* 2175 */     j3dMessage.args[1] = new Integer(paramInt);
/* 2176 */     j3dMessage.args[2] = paramObject;
/* 2177 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 2178 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */   
/*      */   void handleFrequencyChange(int paramInt) {
/* 2182 */     if (paramInt == 3) {
/* 2183 */       setFrequencyChangeMask(3, 1);
/*      */     }
/*      */   }
/*      */   
/*      */   static Object getDataElementBuffer(Raster paramRaster) {
/* 2188 */     int i = paramRaster.getNumDataElements();
/*      */     
/* 2190 */     switch (paramRaster.getTransferType()) {
/*      */       case 3:
/* 2192 */         return new int[i];
/*      */       case 0:
/* 2194 */         return new byte[i];
/*      */       case 1:
/*      */       case 2:
/* 2197 */         return new short[i];
/*      */       case 4:
/* 2199 */         return new float[i];
/*      */       case 5:
/* 2201 */         return new double[i];
/*      */     } 
/*      */     
/* 2204 */     return null;
/*      */   }
/*      */   class ImageData { private Object data;
/*      */     private ImageComponentRetained.ImageDataType imageDataType;
/*      */     private int length;
/*      */     private boolean dataIsByRef;
/*      */     private int dataWidth;
/*      */     private int dataHeight;
/*      */     
/*      */     ImageData(ImageComponentRetained.ImageDataType param1ImageDataType, int param1Int1, int param1Int2, int param1Int3) {
/* 2214 */       this.data = null;
/* 2215 */       this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_NULL;
/* 2216 */       this.length = 0;
/* 2217 */       this.dataIsByRef = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2225 */       this.imageDataType = param1ImageDataType;
/* 2226 */       this.length = param1Int1;
/* 2227 */       this.dataWidth = param1Int2;
/* 2228 */       this.dataHeight = param1Int3;
/* 2229 */       this.dataIsByRef = false;
/*      */       
/* 2231 */       switch (ImageComponentRetained.null.$SwitchMap$javax$media$j3d$ImageComponentRetained$ImageDataType[param1ImageDataType.ordinal()]) {
/*      */         case 1:
/* 2233 */           this.data = new byte[param1Int1];
/*      */           return;
/*      */         case 2:
/* 2236 */           this.data = new int[param1Int1];
/*      */           return;
/*      */         case 3:
/* 2239 */           byteOrder = ByteOrder.nativeOrder();
/* 2240 */           this.data = ByteBuffer.allocateDirect(param1Int1).order(byteOrder);
/*      */           return;
/*      */       } 
/*      */       
/* 2244 */       throw new AssertionError();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ImageData(ImageComponentRetained.ImageDataType param1ImageDataType, int param1Int1, int param1Int2, int param1Int3, Object param1Object) {
/*      */       this.data = null;
/*      */       this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_NULL;
/*      */       this.length = 0;
/*      */       this.dataIsByRef = false;
/* 2257 */       this.imageDataType = param1ImageDataType;
/* 2258 */       this.length = param1Int1;
/* 2259 */       this.dataWidth = param1Int2;
/* 2260 */       this.dataHeight = param1Int3;
/* 2261 */       this.dataIsByRef = true;
/*      */       
/* 2263 */       switch (ImageComponentRetained.null.$SwitchMap$javax$media$j3d$ImageComponentRetained$ImageDataType[param1ImageDataType.ordinal()]) {
/*      */         case 1:
/* 2265 */           bufferedImage = (BufferedImage)param1Object;
/* 2266 */           this.data = ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
/*      */           return;
/*      */         case 2:
/* 2269 */           bufferedImage = (BufferedImage)param1Object;
/* 2270 */           this.data = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
/*      */           return;
/*      */         case 3:
/*      */         case 4:
/* 2274 */           nioImageBuffer = (NioImageBuffer)param1Object;
/* 2275 */           this.data = nioImageBuffer.getDataBuffer();
/*      */           return;
/*      */       } 
/* 2278 */       throw new AssertionError();
/*      */     }
/*      */ 
/*      */     
/*      */     ImageData(Object param1Object, boolean param1Boolean) {
/*      */       this.data = null;
/*      */       this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_NULL;
/*      */       this.length = 0;
/*      */       this.dataIsByRef = false;
/* 2287 */       this.data = param1Object;
/* 2288 */       this.dataIsByRef = param1Boolean;
/* 2289 */       this.dataWidth = ((ImageData)param1Object).dataWidth;
/* 2290 */       this.dataHeight = ((ImageData)param1Object).dataHeight;
/*      */       
/* 2292 */       if (param1Object == null) {
/* 2293 */         this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_NULL;
/* 2294 */         this.length = 0;
/* 2295 */       } else if (param1Object instanceof byte[]) {
/* 2296 */         this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_BYTE_ARRAY;
/* 2297 */         this.length = (byte[])param1Object.length;
/* 2298 */       } else if (param1Object instanceof int[]) {
/* 2299 */         this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_INT_ARRAY;
/* 2300 */         this.length = (int[])param1Object.length;
/* 2301 */       } else if (param1Object instanceof ByteBuffer) {
/* 2302 */         this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_BYTE_BUFFER;
/* 2303 */         this.length = ((ByteBuffer)param1Object).limit();
/* 2304 */       } else if (param1Object instanceof IntBuffer) {
/* 2305 */         this.imageDataType = ImageComponentRetained.ImageDataType.TYPE_INT_BUFFER;
/* 2306 */         this.length = ((IntBuffer)param1Object).limit();
/*      */       } else {
/*      */         assert false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2316 */     ImageComponentRetained.ImageDataType getType() { return this.imageDataType; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2323 */     int length() { return this.length; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2330 */     int getWidth() { return this.dataWidth; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2337 */     int getHeight() { return this.dataHeight; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2344 */     Object get() { return this.data; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2351 */     boolean isDataByRef() { return this.dataIsByRef; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2359 */     byte[] getAsByteArray() { return (byte[])this.data; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2366 */     int[] getAsIntArray() { return (int[])this.data; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2373 */     ByteBuffer getAsByteBuffer() { return (ByteBuffer)this.data; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2380 */     IntBuffer getAsIntBuffer() { return (IntBuffer)this.data; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void copyByLineAndExpand(BufferedImage param1BufferedImage, int param1Int) {
/*      */       int i;
/* 2389 */       assert ImageComponentRetained.this.imageData.getType() == ImageComponentRetained.ImageDataType.TYPE_BYTE_ARRAY;
/* 2390 */       assert ImageComponentRetained.this.imageFormatType == ImageComponentRetained.ImageFormatType.TYPE_BYTE_LA;
/*      */       
/* 2392 */       int k = ImageComponentRetained.this.width * ImageComponentRetained.this.unitsPerPixel;
/* 2393 */       int m = k;
/* 2394 */       if (ImageComponentRetained.this.yUp) {
/* 2395 */         i = param1Int * ImageComponentRetained.this.width * ImageComponentRetained.this.height * ImageComponentRetained.this.unitsPerPixel;
/*      */       } else {
/* 2397 */         i = (param1Int * ImageComponentRetained.this.width * ImageComponentRetained.this.height + (ImageComponentRetained.this.height - 1) * ImageComponentRetained.this.width) * ImageComponentRetained.this.unitsPerPixel;
/* 2398 */         k = -1 * k;
/*      */       } 
/*      */       
/* 2401 */       int j = 0;
/*      */       
/* 2403 */       int n = ImageComponentRetained.this.width * 4;
/*      */       
/* 2405 */       byte[] arrayOfByte1 = ((DataBufferByte)param1BufferedImage.getRaster().getDataBuffer()).getData();
/* 2406 */       byte[] arrayOfByte2 = ImageComponentRetained.this.imageData.getAsByteArray();
/* 2407 */       for (byte b = 0; b < ImageComponentRetained.this.height; b++) {
/* 2408 */         byte b1; int i1; for (b1 = 0, i1 = 0; i1 < m; i1 += ImageComponentRetained.this.unitsPerPixel, b1 += 4) {
/* 2409 */           arrayOfByte1[j + b1] = arrayOfByte2[i + i1 + 1];
/* 2410 */           arrayOfByte1[j + b1 + 1] = 0;
/* 2411 */           arrayOfByte1[j + b1 + 2] = 0;
/* 2412 */           arrayOfByte1[j + b1 + 3] = arrayOfByte2[i + i1];
/*      */         } 
/*      */         
/* 2415 */         j += n;
/* 2416 */         i += k;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void copyByLine(BufferedImage param1BufferedImage, int param1Int, boolean param1Boolean) {
/*      */       int[] arrayOfInt2, arrayOfInt1;
/*      */       byte[] arrayOfByte2, arrayOfByte1;
/*      */       int i;
/*      */       byte b;
/* 2428 */       int k = ImageComponentRetained.this.width * ImageComponentRetained.this.unitsPerPixel;
/* 2429 */       int m = k;
/* 2430 */       if (ImageComponentRetained.this.yUp) {
/* 2431 */         i = param1Int * ImageComponentRetained.this.width * ImageComponentRetained.this.height * ImageComponentRetained.this.unitsPerPixel;
/*      */       } else {
/* 2433 */         i = (param1Int * ImageComponentRetained.this.width * ImageComponentRetained.this.height + (ImageComponentRetained.this.height - 1) * ImageComponentRetained.this.width) * ImageComponentRetained.this.unitsPerPixel;
/* 2434 */         k = -1 * k;
/*      */       } 
/*      */       
/* 2437 */       int j = 0;
/*      */       
/* 2439 */       switch (ImageComponentRetained.null.$SwitchMap$javax$media$j3d$ImageComponentRetained$ImageDataType[ImageComponentRetained.this.imageData.getType().ordinal()]) {
/*      */         case 1:
/* 2441 */           arrayOfByte1 = ((DataBufferByte)param1BufferedImage.getRaster().getDataBuffer()).getData();
/* 2442 */           arrayOfByte2 = ImageComponentRetained.this.imageData.getAsByteArray();
/* 2443 */           for (b = 0; b < ImageComponentRetained.this.height; b++) {
/* 2444 */             if (!param1Boolean) {
/* 2445 */               System.arraycopy(arrayOfByte2, i, arrayOfByte1, j, m);
/*      */             
/*      */             }
/* 2448 */             else if (ImageComponentRetained.this.imageFormatType == ImageComponentRetained.ImageFormatType.TYPE_BYTE_RGB) {
/* 2449 */               assert ImageComponentRetained.this.unitsPerPixel == 3; int n;
/* 2450 */               for (n = 0; n < m; n += ImageComponentRetained.this.unitsPerPixel) {
/* 2451 */                 arrayOfByte1[j + n] = arrayOfByte2[i + n + 2];
/* 2452 */                 arrayOfByte1[j + n + 1] = arrayOfByte2[i + n + 1];
/* 2453 */                 arrayOfByte1[j + n + 2] = arrayOfByte2[i + n];
/*      */               } 
/* 2455 */             } else if (ImageComponentRetained.this.imageFormatType == ImageComponentRetained.ImageFormatType.TYPE_BYTE_RGBA) {
/* 2456 */               assert ImageComponentRetained.this.unitsPerPixel == 4; int n;
/* 2457 */               for (n = 0; n < m; n += ImageComponentRetained.this.unitsPerPixel) {
/* 2458 */                 arrayOfByte1[j + n] = arrayOfByte2[i + n + 3];
/* 2459 */                 arrayOfByte1[j + n + 1] = arrayOfByte2[i + n + 2];
/* 2460 */                 arrayOfByte1[j + n + 2] = arrayOfByte2[i + n + 1];
/* 2461 */                 arrayOfByte1[j + n + 3] = arrayOfByte2[i + n];
/*      */               } 
/*      */             } else {
/*      */               assert false;
/*      */             } 
/*      */             
/* 2467 */             j += m;
/* 2468 */             i += k;
/*      */           } 
/*      */           return;
/*      */ 
/*      */         
/*      */         case 2:
/* 2474 */           assert !param1Boolean;
/* 2475 */           arrayOfInt1 = ((DataBufferInt)param1BufferedImage.getRaster().getDataBuffer()).getData();
/* 2476 */           arrayOfInt2 = ImageComponentRetained.this.imageData.getAsIntArray();
/* 2477 */           for (b = 0; b < ImageComponentRetained.this.height; b++) {
/* 2478 */             System.arraycopy(arrayOfInt2, i, arrayOfInt1, j, m);
/* 2479 */             j += m;
/* 2480 */             i += k;
/*      */           } 
/*      */           return;
/*      */       } 
/*      */       assert false;
/*      */     }
/*      */     
/*      */     void copyByBlock(BufferedImage param1BufferedImage, int param1Int) {
/*      */       int[] arrayOfInt2, arrayOfInt1;
/*      */       byte[] arrayOfByte2, arrayOfByte1;
/* 2490 */       int i = param1Int * ImageComponentRetained.this.width * ImageComponentRetained.this.height * ImageComponentRetained.this.unitsPerPixel;
/*      */       
/* 2492 */       switch (ImageComponentRetained.null.$SwitchMap$javax$media$j3d$ImageComponentRetained$ImageDataType[ImageComponentRetained.this.imageData.getType().ordinal()]) {
/*      */         case 1:
/* 2494 */           arrayOfByte1 = ((DataBufferByte)param1BufferedImage.getRaster().getDataBuffer()).getData();
/* 2495 */           arrayOfByte2 = ImageComponentRetained.this.imageData.getAsByteArray();
/* 2496 */           System.arraycopy(arrayOfByte2, i, arrayOfByte1, 0, ImageComponentRetained.this.height * ImageComponentRetained.this.width * ImageComponentRetained.this.unitsPerPixel);
/*      */           return;
/*      */         case 2:
/* 2499 */           arrayOfInt1 = ((DataBufferInt)param1BufferedImage.getRaster().getDataBuffer()).getData();
/* 2500 */           arrayOfInt2 = ImageComponentRetained.this.imageData.getAsIntArray();
/* 2501 */           System.arraycopy(arrayOfInt2, i, arrayOfInt1, 0, ImageComponentRetained.this.height * ImageComponentRetained.this.width * ImageComponentRetained.this.unitsPerPixel);
/*      */           return;
/*      */       } 
/*      */       assert false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     BufferedImage createBufferedImage(int param1Int) {
/* 2510 */       if (this.data != null) {
/* 2511 */         byte b = 0;
/* 2512 */         boolean bool = false;
/*      */         
/* 2514 */         switch (ImageComponentRetained.null.$SwitchMap$javax$media$j3d$ImageComponentRetained$ImageFormatType[ImageComponentRetained.this.imageFormatType.ordinal()]) {
/*      */           case 1:
/* 2516 */             b = 5;
/*      */             break;
/*      */           case 2:
/* 2519 */             b = 5;
/* 2520 */             bool = true;
/*      */             break;
/*      */           case 3:
/* 2523 */             b = 6;
/*      */             break;
/*      */           case 4:
/* 2526 */             b = 6;
/* 2527 */             bool = true;
/*      */             break;
/*      */           
/*      */           case 5:
/* 2531 */             b = 6;
/*      */             break;
/*      */           case 6:
/* 2534 */             b = 10;
/*      */             break;
/*      */           case 8:
/* 2537 */             b = 4;
/*      */             break;
/*      */           case 9:
/* 2540 */             b = 1;
/*      */             break;
/*      */           case 10:
/* 2543 */             b = 2;
/*      */             break;
/*      */           
/*      */           case 7:
/* 2547 */             b = 11;
/*      */           
/*      */           default:
/*      */             assert false;
/*      */             break;
/*      */         } 
/* 2553 */         BufferedImage bufferedImage = new BufferedImage(ImageComponentRetained.this.width, ImageComponentRetained.this.height, b);
/* 2554 */         if (!bool && ImageComponentRetained.this.imageFormatType != ImageComponentRetained.ImageFormatType.TYPE_BYTE_LA) {
/* 2555 */           if (ImageComponentRetained.this.yUp) {
/* 2556 */             copyByBlock(bufferedImage, param1Int);
/*      */           } else {
/* 2558 */             copyByLine(bufferedImage, param1Int, false);
/*      */           } 
/* 2560 */         } else if (bool) {
/* 2561 */           copyByLine(bufferedImage, param1Int, bool);
/* 2562 */         } else if (ImageComponentRetained.this.imageFormatType == ImageComponentRetained.ImageFormatType.TYPE_BYTE_LA) {
/* 2563 */           copyByLineAndExpand(bufferedImage, param1Int);
/*      */         } else {
/*      */           assert false;
/*      */         } 
/*      */         
/* 2568 */         return bufferedImage;
/*      */       } 
/*      */       
/* 2571 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void convertFromABGRToRGBA() {
/* 2577 */       if (this.imageDataType == ImageComponentRetained.ImageDataType.TYPE_BYTE_ARRAY) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2582 */         byte[] arrayOfByte = getAsByteArray();
/*      */         
/* 2584 */         if (this.dataIsByRef) {
/* 2585 */           byte[] arrayOfByte1 = new byte[this.length];
/*      */           
/* 2587 */           for (boolean bool = false; bool < this.length; bool += true) {
/* 2588 */             arrayOfByte1[bool] = arrayOfByte[bool + 3];
/* 2589 */             arrayOfByte1[bool + true] = arrayOfByte[bool + 2];
/* 2590 */             arrayOfByte1[bool + 2] = arrayOfByte[bool + true];
/* 2591 */             arrayOfByte1[bool + 3] = arrayOfByte[bool];
/*      */           } 
/* 2593 */           this.data = arrayOfByte1;
/* 2594 */           this.dataIsByRef = false;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 2599 */           for (boolean bool = false; bool < this.length; bool += true) {
/* 2600 */             byte b1 = arrayOfByte[bool];
/* 2601 */             byte b2 = arrayOfByte[bool + true];
/* 2602 */             arrayOfByte[bool] = arrayOfByte[bool + 3];
/* 2603 */             arrayOfByte[bool + true] = arrayOfByte[bool + 2];
/* 2604 */             arrayOfByte[bool + 2] = b2;
/* 2605 */             arrayOfByte[bool + 3] = b1;
/*      */           }
/*      */         
/*      */         } 
/* 2609 */       } else if (this.imageDataType == ImageComponentRetained.ImageDataType.TYPE_BYTE_BUFFER) {
/*      */         
/* 2611 */         assert this.dataIsByRef;
/*      */ 
/*      */         
/* 2614 */         ByteBuffer byteBuffer1 = getAsByteBuffer();
/* 2615 */         byteBuffer1.rewind();
/*      */         
/* 2617 */         ByteOrder byteOrder = ByteOrder.nativeOrder();
/* 2618 */         ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(this.length).order(byteOrder);
/* 2619 */         byteBuffer2.rewind();
/*      */ 
/*      */         
/* 2622 */         for (byte b = 0; b < this.length; b += 4) {
/* 2623 */           byteBuffer2.put(b, byteBuffer1.get(b + 3));
/* 2624 */           byteBuffer2.put(b + 1, byteBuffer1.get(b + 2));
/* 2625 */           byteBuffer2.put(b + 2, byteBuffer1.get(b + 1));
/* 2626 */           byteBuffer2.put(b + 3, byteBuffer1.get(b));
/*      */         } 
/*      */         
/* 2629 */         this.dataIsByRef = false;
/*      */       } 
/*      */     } }
/*      */ 
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ImageComponentRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */