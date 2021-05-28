/*     */ package com.sun.j3d.utils.image;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.Texture;
/*     */ import javax.media.j3d.Texture2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureLoader
/*     */ {
/*     */   public static final int GENERATE_MIPMAP = 1;
/*     */   public static final int BY_REFERENCE = 2;
/*     */   public static final int Y_UP = 4;
/*     */   public static final int ALLOW_NON_POWER_OF_TWO = 8;
/* 116 */   private static ColorSpace cs = ColorSpace.getInstance(1000);
/* 117 */   private static int[] nBits = { 8, 8, 8, 8 };
/* 118 */   private static int[] bandOffset = { 0, 1, 2, 3 };
/* 119 */   private static ComponentColorModel colorModel = new ComponentColorModel(cs, nBits, true, false, 3, 0);
/*     */   
/* 121 */   private Texture2D tex = null;
/* 122 */   private BufferedImage bufferedImage = null;
/* 123 */   private ImageComponent2D imageComponent = null;
/* 124 */   private int textureFormat = 6;
/* 125 */   private int imageComponentFormat = 2;
/*     */ 
/*     */   
/*     */   private int flags;
/*     */ 
/*     */   
/*     */   private boolean byRef = false;
/*     */ 
/*     */   
/*     */   private boolean yUp = false;
/*     */   
/*     */   private boolean forcePowerOfTwo = true;
/*     */ 
/*     */   
/* 139 */   public TextureLoader(BufferedImage paramBufferedImage) { this(paramBufferedImage, null, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public TextureLoader(BufferedImage paramBufferedImage, String paramString) { this(paramBufferedImage, paramString, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public TextureLoader(BufferedImage paramBufferedImage, int paramInt) { this(paramBufferedImage, null, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureLoader(BufferedImage paramBufferedImage, String paramString, int paramInt) {
/* 176 */     if (paramBufferedImage == null) {
/* 177 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 180 */     parseFormat(paramString);
/* 181 */     this.flags = paramInt;
/* 182 */     this.bufferedImage = paramBufferedImage;
/* 183 */     if (paramString == null) {
/* 184 */       chooseFormat(this.bufferedImage);
/*     */     }
/* 186 */     if ((paramInt & 0x2) != 0) {
/* 187 */       this.byRef = true;
/*     */     }
/* 189 */     if ((paramInt & 0x4) != 0) {
/* 190 */       this.yUp = true;
/*     */     }
/* 192 */     if ((paramInt & 0x8) != 0) {
/* 193 */       this.forcePowerOfTwo = false;
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
/* 207 */   public TextureLoader(Image paramImage, Component paramComponent) { this(paramImage, null, 0, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public TextureLoader(Image paramImage, String paramString, Component paramComponent) { this(paramImage, paramString, 0, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public TextureLoader(Image paramImage, int paramInt, Component paramComponent) { this(paramImage, null, paramInt, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureLoader(Image paramImage, String paramString, int paramInt, Component paramComponent) {
/* 252 */     if (paramImage == null) {
/* 253 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 256 */     if (paramComponent == null) {
/* 257 */       paramComponent = new Container();
/*     */     }
/*     */     
/* 260 */     parseFormat(paramString);
/* 261 */     this.flags = paramInt;
/* 262 */     this.bufferedImage = createBufferedImage(paramImage, paramComponent);
/*     */     
/* 264 */     if (this.bufferedImage == null) {
/* 265 */       throw new ImageException("Error loading image: " + paramImage.toString());
/*     */     }
/*     */     
/* 268 */     if (paramString == null) {
/* 269 */       chooseFormat(this.bufferedImage);
/*     */     }
/* 271 */     if ((paramInt & 0x2) != 0) {
/* 272 */       this.byRef = true;
/*     */     }
/* 274 */     if ((paramInt & 0x4) != 0) {
/* 275 */       this.yUp = true;
/*     */     }
/* 277 */     if ((paramInt & 0x8) != 0) {
/* 278 */       this.forcePowerOfTwo = false;
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
/* 291 */   public TextureLoader(String paramString, Component paramComponent) { this(paramString, null, 0, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public TextureLoader(String paramString1, String paramString2, Component paramComponent) { this(paramString1, paramString2, 0, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 317 */   public TextureLoader(String paramString, int paramInt, Component paramComponent) { this(paramString, null, paramInt, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureLoader(final String fname, String paramString2, int paramInt, Component paramComponent) {
/* 333 */     if (paramComponent == null) {
/* 334 */       paramComponent = new Container();
/*     */     }
/*     */     
/* 337 */     this.bufferedImage = (BufferedImage)AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/*     */             try {
/* 342 */               return ImageIO.read(new File(fname));
/* 343 */             } catch (IOException iOException) {
/* 344 */               throw new ImageException(iOException);
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 350 */     if (this.bufferedImage == null) {
/* 351 */       throw new ImageException("Error loading image: " + paramString1);
/*     */     }
/*     */     
/* 354 */     parseFormat(paramString2);
/* 355 */     this.flags = paramInt;
/*     */     
/* 357 */     if (paramString2 == null) {
/* 358 */       chooseFormat(this.bufferedImage);
/*     */     }
/* 360 */     if ((paramInt & 0x2) != 0) {
/* 361 */       this.byRef = true;
/*     */     }
/* 363 */     if ((paramInt & 0x4) != 0) {
/* 364 */       this.yUp = true;
/*     */     }
/* 366 */     if ((paramInt & 0x8) != 0) {
/* 367 */       this.forcePowerOfTwo = false;
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
/* 380 */   public TextureLoader(URL paramURL, Component paramComponent) { this(paramURL, null, 0, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   public TextureLoader(URL paramURL, String paramString, Component paramComponent) { this(paramURL, paramString, 0, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 406 */   public TextureLoader(URL paramURL, int paramInt, Component paramComponent) { this(paramURL, null, paramInt, paramComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureLoader(final URL url, String paramString, int paramInt, Component paramComponent) {
/* 421 */     if (paramComponent == null) {
/* 422 */       paramComponent = new Container();
/*     */     }
/*     */     
/* 425 */     this.bufferedImage = (BufferedImage)AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/*     */             try {
/* 430 */               return ImageIO.read(url);
/* 431 */             } catch (IOException iOException) {
/* 432 */               throw new ImageException(iOException);
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 438 */     if (this.bufferedImage == null) {
/* 439 */       throw new ImageException("Error loading image: " + paramURL.toString());
/*     */     }
/*     */     
/* 442 */     parseFormat(paramString);
/* 443 */     this.flags = paramInt;
/*     */     
/* 445 */     if (paramString == null) {
/* 446 */       chooseFormat(this.bufferedImage);
/*     */     }
/* 448 */     if ((paramInt & 0x2) != 0) {
/* 449 */       this.byRef = true;
/*     */     }
/* 451 */     if ((paramInt & 0x4) != 0) {
/* 452 */       this.yUp = true;
/*     */     }
/* 454 */     if ((paramInt & 0x8) != 0) {
/* 455 */       this.forcePowerOfTwo = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent2D getImage() {
/* 466 */     if (this.imageComponent == null) {
/* 467 */       this.imageComponent = new ImageComponent2D(this.imageComponentFormat, this.bufferedImage, this.byRef, this.yUp);
/*     */     }
/* 469 */     return this.imageComponent;
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
/*     */   public ImageComponent2D getScaledImage(float paramFloat1, float paramFloat2) {
/* 481 */     if (paramFloat1 == 1.0F && paramFloat2 == 1.0F) {
/* 482 */       return getImage();
/*     */     }
/* 484 */     return new ImageComponent2D(this.imageComponentFormat, getScaledImage(this.bufferedImage, paramFloat1, paramFloat2), this.byRef, this.yUp);
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
/*     */   public ImageComponent2D getScaledImage(int paramInt1, int paramInt2) {
/* 500 */     if (this.bufferedImage.getWidth() == paramInt1 && this.bufferedImage.getHeight() == paramInt2)
/*     */     {
/* 502 */       return getImage();
/*     */     }
/* 504 */     return new ImageComponent2D(this.imageComponentFormat, getScaledImage(this.bufferedImage, paramInt1, paramInt2), this.byRef, this.yUp);
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
/*     */   public Texture getTexture() {
/* 516 */     ImageComponent2D[] arrayOfImageComponent2D = null;
/* 517 */     BufferedImage[] arrayOfBufferedImage = null;
/* 518 */     if (this.tex == null) {
/*     */       int j, i;
/*     */ 
/*     */ 
/*     */       
/* 523 */       if (this.forcePowerOfTwo) {
/* 524 */         i = getClosestPowerOf2(this.bufferedImage.getWidth());
/* 525 */         j = getClosestPowerOf2(this.bufferedImage.getHeight());
/*     */       } else {
/* 527 */         i = this.bufferedImage.getWidth();
/* 528 */         j = this.bufferedImage.getHeight();
/*     */       } 
/*     */       
/* 531 */       if ((this.flags & true) != 0) {
/*     */         
/* 533 */         BufferedImage bufferedImage1 = this.bufferedImage;
/* 534 */         int k = i;
/* 535 */         int m = j;
/* 536 */         int n = Math.max(computeLog(i), computeLog(j)) + 1;
/* 537 */         arrayOfImageComponent2D = new ImageComponent2D[n];
/* 538 */         arrayOfBufferedImage = new BufferedImage[n];
/* 539 */         this.tex; this.tex = new Texture2D(2, this.textureFormat, i, j);
/*     */ 
/*     */         
/* 542 */         for (byte b = 0; b < n; b++) {
/* 543 */           arrayOfBufferedImage[b] = getScaledImage(bufferedImage1, k, m);
/* 544 */           arrayOfImageComponent2D[b] = new ImageComponent2D(this.imageComponentFormat, arrayOfBufferedImage[b], this.byRef, this.yUp);
/*     */ 
/*     */ 
/*     */           
/* 548 */           this.tex.setImage(b, arrayOfImageComponent2D[b]);
/* 549 */           if (this.forcePowerOfTwo) {
/* 550 */             if (k > 1) k >>= 1; 
/* 551 */             if (m > 1) m >>= 1; 
/*     */           } else {
/* 553 */             if (k > 1) {
/* 554 */               k = (int)Math.floor(k / 2.0D);
/*     */             }
/* 556 */             if (m > 1) {
/* 557 */               m = (int)Math.floor(m / 2.0D);
/*     */             }
/*     */           } 
/* 560 */           bufferedImage1 = arrayOfBufferedImage[b];
/*     */         } 
/*     */       } else {
/*     */         
/* 564 */         arrayOfImageComponent2D = new ImageComponent2D[1];
/* 565 */         arrayOfBufferedImage = new BufferedImage[1];
/*     */ 
/*     */         
/* 568 */         arrayOfBufferedImage[0] = getScaledImage(this.bufferedImage, i, j);
/*     */         
/* 570 */         arrayOfImageComponent2D[0] = new ImageComponent2D(this.imageComponentFormat, arrayOfBufferedImage[0], this.byRef, this.yUp);
/*     */ 
/*     */ 
/*     */         
/* 574 */         this.tex; this.tex = new Texture2D(1, this.textureFormat, i, j);
/*     */         
/* 576 */         this.tex.setImage(0, arrayOfImageComponent2D[0]);
/*     */       } 
/* 578 */       this.tex; this.tex.setMinFilter(3);
/* 579 */       this.tex; this.tex.setMagFilter(3);
/*     */     } 
/*     */     
/* 582 */     return this.tex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage createBufferedImage(Image paramImage, Component paramComponent) {
/* 591 */     paramComponent.prepareImage(paramImage, null);
/*     */     while (true) {
/* 593 */       int k = paramComponent.checkImage(paramImage, null);
/* 594 */       if ((k & 0x40) != 0)
/* 595 */         return null; 
/* 596 */       if ((k & 0x20) != 0) {
/*     */         break;
/*     */       }
/*     */       try {
/* 600 */         Thread.sleep(100L);
/* 601 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */     
/* 604 */     int i = paramImage.getWidth(paramComponent);
/* 605 */     int j = paramImage.getHeight(paramComponent);
/*     */     
/* 607 */     WritableRaster writableRaster = Raster.createInterleavedRaster(0, i, j, i * 4, 4, bandOffset, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 612 */     BufferedImage bufferedImage1 = new BufferedImage(colorModel, writableRaster, false, null);
/*     */     
/* 614 */     Graphics graphics = bufferedImage1.getGraphics();
/* 615 */     graphics.drawImage(paramImage, 0, 0, paramComponent);
/*     */     
/* 617 */     return bufferedImage1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void chooseFormat(BufferedImage paramBufferedImage) {
/* 625 */     switch (paramBufferedImage.getType()) {
/*     */       case 2:
/*     */       case 6:
/* 628 */         this.imageComponentFormat = 2;
/* 629 */         this.textureFormat = 6;
/*     */         return;
/*     */       case 1:
/*     */       case 4:
/*     */       case 5:
/* 634 */         this.imageComponentFormat = 1;
/* 635 */         this.textureFormat = 5;
/*     */         return;
/*     */       case 0:
/* 638 */         if (is4ByteRGBAOr3ByteRGB(paramBufferedImage)) {
/* 639 */           SampleModel sampleModel = paramBufferedImage.getSampleModel();
/* 640 */           if (sampleModel.getNumBands() == 3) {
/*     */             
/* 642 */             this.imageComponentFormat = 1;
/* 643 */             this.textureFormat = 5;
/*     */           } else {
/*     */             
/* 646 */             this.imageComponentFormat = 2;
/*     */             
/* 648 */             this.textureFormat = 6;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */     } 
/*     */     
/* 654 */     this.imageComponentFormat = 2;
/* 655 */     this.textureFormat = 6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean is4ByteRGBAOr3ByteRGB(RenderedImage paramRenderedImage) {
/* 661 */     boolean bool1 = false;
/*     */     
/* 663 */     int i = getImageType(paramRenderedImage);
/* 664 */     if (i != 0)
/* 665 */       return false; 
/* 666 */     ColorModel colorModel1 = paramRenderedImage.getColorModel();
/* 667 */     ColorSpace colorSpace = colorModel1.getColorSpace();
/* 668 */     SampleModel sampleModel = paramRenderedImage.getSampleModel();
/* 669 */     boolean bool2 = colorModel1.isAlphaPremultiplied();
/* 670 */     int j = colorSpace.getType();
/* 671 */     if (j == 5) {
/* 672 */       int k = sampleModel.getNumBands();
/* 673 */       if (sampleModel.getDataType() == 0 && 
/* 674 */         colorModel1 instanceof ComponentColorModel && sampleModel instanceof PixelInterleavedSampleModel) {
/*     */         
/* 676 */         PixelInterleavedSampleModel pixelInterleavedSampleModel = (PixelInterleavedSampleModel)sampleModel;
/*     */         
/* 678 */         int[] arrayOfInt1 = pixelInterleavedSampleModel.getBandOffsets();
/* 679 */         ComponentColorModel componentColorModel = (ComponentColorModel)colorModel1;
/* 680 */         int[] arrayOfInt2 = componentColorModel.getComponentSize();
/* 681 */         boolean bool = true;
/* 682 */         for (byte b = 0; b < k; b++) {
/* 683 */           if (arrayOfInt2[b] != 8) {
/* 684 */             bool = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 688 */         if (bool && arrayOfInt1[0] == 0 && arrayOfInt1[1] == 1 && arrayOfInt1[2] == 2)
/*     */         {
/*     */ 
/*     */           
/* 692 */           if (k == 3) {
/* 693 */             bool1 = true;
/*     */           }
/* 695 */           else if (arrayOfInt1[3] == 3 && !bool2) {
/* 696 */             bool1 = true;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 702 */     return bool1;
/*     */   }
/*     */   
/*     */   private int getImageType(RenderedImage paramRenderedImage) {
/* 706 */     byte b = 0;
/*     */ 
/*     */     
/* 709 */     if (paramRenderedImage instanceof BufferedImage) {
/* 710 */       return ((BufferedImage)paramRenderedImage).getType();
/*     */     }
/* 712 */     ColorModel colorModel1 = paramRenderedImage.getColorModel();
/* 713 */     ColorSpace colorSpace = colorModel1.getColorSpace();
/* 714 */     SampleModel sampleModel = paramRenderedImage.getSampleModel();
/* 715 */     int i = colorSpace.getType();
/* 716 */     boolean bool = colorModel1.isAlphaPremultiplied();
/* 717 */     if (i != 5) {
/* 718 */       if (i == 6 && colorModel1 instanceof ComponentColorModel)
/*     */       {
/* 720 */         if (sampleModel.getDataType() == 0) {
/* 721 */           b = 10;
/* 722 */         } else if (sampleModel.getDataType() == 1) {
/* 723 */           b = 11;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 730 */       int j = sampleModel.getNumBands();
/* 731 */       if (sampleModel.getDataType() == 0 && 
/* 732 */         colorModel1 instanceof ComponentColorModel && sampleModel instanceof PixelInterleavedSampleModel) {
/*     */         
/* 734 */         PixelInterleavedSampleModel pixelInterleavedSampleModel = (PixelInterleavedSampleModel)sampleModel;
/*     */         
/* 736 */         int[] arrayOfInt1 = pixelInterleavedSampleModel.getBandOffsets();
/* 737 */         ComponentColorModel componentColorModel = (ComponentColorModel)colorModel1;
/* 738 */         int[] arrayOfInt2 = componentColorModel.getComponentSize();
/* 739 */         boolean bool1 = true;
/* 740 */         for (byte b1 = 0; b1 < j; b1++) {
/* 741 */           if (arrayOfInt2[b1] != 8) {
/* 742 */             bool1 = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 746 */         if (bool1 && arrayOfInt1[0] == j - 1 && arrayOfInt1[1] == j - 2 && arrayOfInt1[2] == j - 3)
/*     */         {
/*     */ 
/*     */           
/* 750 */           if (j == 3) {
/* 751 */             b = 5;
/*     */           }
/* 753 */           else if (arrayOfInt1[3] == 0) {
/* 754 */             b = bool ? 7 : 6;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 762 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseFormat(String paramString) {
/* 767 */     if (paramString == null) {
/*     */       return;
/*     */     }
/* 770 */     if (paramString.equals("RGBA")) {
/* 771 */       this.imageComponentFormat = 2;
/* 772 */       this.textureFormat = 6;
/*     */     }
/* 774 */     else if (paramString.equals("RGBA4")) {
/* 775 */       this.imageComponentFormat = 6;
/* 776 */       this.textureFormat = 6;
/*     */     }
/* 778 */     else if (paramString.equals("RGB5_A1")) {
/* 779 */       this.imageComponentFormat = 4;
/* 780 */       this.textureFormat = 6;
/*     */     }
/* 782 */     else if (paramString.equals("RGB")) {
/* 783 */       this.imageComponentFormat = 1;
/* 784 */       this.textureFormat = 5;
/*     */     }
/* 786 */     else if (paramString.equals("RGB4")) {
/* 787 */       this.imageComponentFormat = 5;
/* 788 */       this.textureFormat = 5;
/*     */     }
/* 790 */     else if (paramString.equals("RGB5")) {
/* 791 */       this.imageComponentFormat = 3;
/* 792 */       this.textureFormat = 5;
/*     */     }
/* 794 */     else if (paramString.equals("R3_G3_B2")) {
/* 795 */       this.imageComponentFormat = 9;
/* 796 */       this.textureFormat = 5;
/*     */     }
/* 798 */     else if (paramString.equals("LUM8_ALPHA8")) {
/* 799 */       this.imageComponentFormat = 8;
/* 800 */       this.textureFormat = 4;
/*     */     }
/* 802 */     else if (paramString.equals("LUM4_ALPHA4")) {
/* 803 */       this.imageComponentFormat = 7;
/* 804 */       this.textureFormat = 4;
/*     */     }
/* 806 */     else if (paramString.equals("LUMINANCE")) {
/* 807 */       this.imageComponentFormat = 10;
/* 808 */       this.textureFormat = 2;
/*     */     }
/* 810 */     else if (paramString.equals("ALPHA")) {
/* 811 */       this.imageComponentFormat = 10;
/* 812 */       this.textureFormat = 3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage getScaledImage(BufferedImage paramBufferedImage, int paramInt1, int paramInt2) {
/* 820 */     int i = paramBufferedImage.getWidth();
/* 821 */     int j = paramBufferedImage.getHeight();
/* 822 */     float f1 = paramInt1 / i;
/* 823 */     float f2 = paramInt2 / j;
/*     */     
/* 825 */     return getScaledImage(paramBufferedImage, f1, f2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage getScaledImage(BufferedImage paramBufferedImage, float paramFloat1, float paramFloat2) {
/*     */     WritableRaster writableRaster;
/*     */     BufferedImage bufferedImage1;
/* 835 */     if (paramFloat1 == 1.0F && paramFloat2 == 1.0F) {
/* 836 */       return paramBufferedImage;
/*     */     }
/* 838 */     int i = (int)((paramBufferedImage.getWidth() * paramFloat1) + 0.5D);
/* 839 */     int j = (int)((paramBufferedImage.getHeight() * paramFloat2) + 0.5D);
/*     */     
/* 841 */     int k = paramBufferedImage.getType();
/*     */ 
/*     */ 
/*     */     
/* 845 */     if (k != 0) {
/* 846 */       WritableRaster writableRaster1 = paramBufferedImage.getRaster();
/* 847 */       writableRaster = writableRaster1.createCompatibleWritableRaster(0, 0, i, j);
/* 848 */       bufferedImage1 = new BufferedImage(i, j, k);
/*     */     } else {
/* 850 */       int m = paramBufferedImage.getSampleModel().getNumBands();
/* 851 */       int[] arrayOfInt1 = new int[m];
/* 852 */       int[] arrayOfInt2 = new int[m]; byte b;
/* 853 */       for (b = 0; b < m; b++) {
/* 854 */         arrayOfInt1[b] = b;
/* 855 */         arrayOfInt2[b] = 8;
/*     */       } 
/*     */       
/* 858 */       writableRaster = Raster.createInterleavedRaster(0, i, j, i * m, m, arrayOfInt1, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 865 */       switch (m) {
/*     */         case 1:
/* 867 */           b = 10;
/*     */           break;
/*     */         case 3:
/* 870 */           b = 5;
/*     */           break;
/*     */         case 4:
/* 873 */           b = 6;
/*     */           break;
/*     */         default:
/* 876 */           throw new ImageException("Illegal number of bands : " + m);
/*     */       } 
/*     */ 
/*     */       
/* 880 */       bufferedImage1 = new BufferedImage(i, j, b);
/*     */     } 
/*     */     
/* 883 */     bufferedImage1.setData(writableRaster);
/* 884 */     Graphics2D graphics2D = bufferedImage1.createGraphics();
/* 885 */     AffineTransform affineTransform = AffineTransform.getScaleInstance(paramFloat1, paramFloat2);
/*     */     
/* 887 */     graphics2D.transform(affineTransform);
/* 888 */     graphics2D.drawImage(paramBufferedImage, 0, 0, null);
/*     */     
/* 890 */     return bufferedImage1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int computeLog(int paramInt) {
/* 895 */     byte b = 0;
/*     */     
/* 897 */     if (paramInt == 0) return -1; 
/*     */     while (true) {
/* 899 */       if (paramInt == 1)
/* 900 */         return b; 
/* 901 */       paramInt >>= 1;
/* 902 */       b++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getClosestPowerOf2(int paramInt) {
/* 908 */     if (paramInt < 1) {
/* 909 */       return paramInt;
/*     */     }
/* 911 */     int i = 1;
/*     */     do {
/* 913 */       i *= 2;
/* 914 */     } while (paramInt >= i);
/*     */     
/* 916 */     int j = i / 2;
/* 917 */     if (i - paramInt > paramInt - j)
/*     */     {
/* 919 */       return j;
/*     */     }
/* 921 */     return i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\image\TextureLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */