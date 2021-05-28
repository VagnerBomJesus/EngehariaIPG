/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ImageScaler
/*     */ {
/*     */   int origW;
/*     */   int origH;
/*     */   Image origImage;
/*     */   
/*     */   ImageScaler(Image paramImage, int paramInt1, int paramInt2) {
/*  64 */     this.origImage = paramImage;
/*  65 */     this.origW = paramInt1;
/*  66 */     this.origH = paramInt2;
/*     */   }
/*     */   
/*     */   ImageScaler(BufferedImage paramBufferedImage) {
/*  70 */     this.origImage = paramBufferedImage;
/*  71 */     this.origW = paramBufferedImage.getWidth();
/*  72 */     this.origH = paramBufferedImage.getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getClosestPowerOf2(int paramInt) {
/*  80 */     if (paramInt < 1) {
/*  81 */       return paramInt;
/*     */     }
/*  83 */     int i = 1;
/*  84 */     for (byte b = 1; b < 20; b++) {
/*  85 */       i *= 2;
/*  86 */       if (paramInt < i) {
/*     */         
/*  88 */         int j = i / 2;
/*  89 */         if (i - paramInt > paramInt - j)
/*     */         {
/*  91 */           return j;
/*     */         }
/*  93 */         return i;
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Image getScaledImage() {
/* 105 */     int i = getClosestPowerOf2(this.origW);
/* 106 */     int j = getClosestPowerOf2(this.origH);
/*     */ 
/*     */     
/* 109 */     if (i == this.origW && j == this.origH)
/*     */     {
/* 111 */       return this.origImage;
/*     */     }
/* 113 */     Image image = null;
/*     */     
/* 115 */     if (this.origImage instanceof BufferedImage) {
/*     */       
/* 117 */       BufferedImage bufferedImage1 = (BufferedImage)this.origImage;
/* 118 */       image = new BufferedImage(i, j, bufferedImage1.getType());
/*     */ 
/*     */ 
/*     */       
/* 122 */       BufferedImage bufferedImage2 = (BufferedImage)image;
/* 123 */       float f1 = this.origW / i;
/* 124 */       float f2 = this.origH / j;
/* 125 */       int[] arrayOfInt1 = ((DataBufferInt)bufferedImage1.getRaster().getDataBuffer()).getData();
/* 126 */       int[] arrayOfInt2 = ((DataBufferInt)bufferedImage2.getRaster().getDataBuffer()).getData();
/* 127 */       for (int k = 0; k < j; k++) {
/* 128 */         for (int m = 0; m < i; m++) {
/* 129 */           int n = Math.min(this.origH - 1, (int)(k * f2 + 0.5F));
/*     */ 
/*     */           
/* 132 */           int i1 = Math.min(this.origW - 1, (int)(m * f1 + 0.5F));
/*     */ 
/*     */           
/* 135 */           arrayOfInt2[k * i + m] = arrayOfInt1[n * this.origW + i1];
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 142 */       image = this.origImage.getScaledInstance(i, j, 1);
/*     */     } 
/*     */ 
/*     */     
/* 146 */     return image;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\lw3d\ImageScaler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */