/*     */ package com.sun.j3d.loaders.objectfile;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RgbFile
/*     */   extends BufferedInputStream
/*     */ {
/*     */   short dimension;
/*     */   short xSize;
/*     */   short ySize;
/*     */   short zSize;
/*     */   String filename;
/*     */   private static final int DEBUG = 0;
/*     */   
/*     */   short getShort() throws IOException {
/*  74 */     short s1 = (short)read();
/*  75 */     if (s1 == -1) throw new IOException("Unexpected EOF"); 
/*  76 */     short s2 = (short)read();
/*  77 */     if (s2 == -1) throw new IOException("Unexpected EOF"); 
/*  78 */     return (short)(s1 << 8 | s2);
/*     */   }
/*     */ 
/*     */   
/*     */   byte getByte() throws IOException {
/*  83 */     int i = read();
/*  84 */     if (i == -1) throw new IOException("Unexpected EOF"); 
/*  85 */     return (byte)i;
/*     */   }
/*     */ 
/*     */   
/*     */   int getInt() throws IOException {
/*  90 */     int i = 0;
/*  91 */     for (byte b = 0; b < 4; b++) {
/*  92 */       int j = read();
/*  93 */       if (j == -1) throw new IOException("Unexpected EOF"); 
/*  94 */       i = i << 8 | j;
/*     */     } 
/*  96 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public BufferedImage getImage() throws IOException {
/* 101 */     short s = getShort();
/*     */     
/* 103 */     if (s != 474) throw new IOException("Unrecognized file format.");
/*     */     
/* 105 */     byte b1 = getByte();
/*     */     
/* 107 */     if (b1 != 0) {
/* 108 */       throw new IOException("RLE Compressed files not supported");
/*     */     }
/* 110 */     byte b2 = getByte();
/* 111 */     this.dimension = getShort();
/* 112 */     this.xSize = getShort();
/* 113 */     this.ySize = getShort();
/* 114 */     this.zSize = getShort();
/* 115 */     int i = getInt();
/* 116 */     int j = getInt();
/* 117 */     skip(84L);
/* 118 */     int k = getInt();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     if (i != 0 || j != 255 || k != 0 || b2 != 1) {
/* 133 */       throw new IOException("Unsupported options in file");
/*     */     }
/* 135 */     skip(404L);
/*     */     
/* 137 */     ComponentColorModel componentColorModel = null;
/* 138 */     if (this.zSize == 1) {
/*     */       
/* 140 */       ColorSpace colorSpace = ColorSpace.getInstance(1003);
/*     */       
/* 142 */       int[] arrayOfInt = { 8 };
/* 143 */       componentColorModel = new ComponentColorModel(colorSpace, arrayOfInt, false, false, 1, 0);
/*     */ 
/*     */     
/*     */     }
/* 147 */     else if (this.zSize == 2) {
/*     */       
/* 149 */       ColorSpace colorSpace = ColorSpace.getInstance(1003);
/*     */       
/* 151 */       int[] arrayOfInt = { 8, 8 };
/* 152 */       componentColorModel = new ComponentColorModel(colorSpace, arrayOfInt, true, false, 3, 0);
/*     */ 
/*     */     
/*     */     }
/* 156 */     else if (this.zSize == 3) {
/*     */       
/* 158 */       ColorSpace colorSpace = ColorSpace.getInstance(1000);
/*     */       
/* 160 */       int[] arrayOfInt = { 8, 8, 8 };
/* 161 */       componentColorModel = new ComponentColorModel(colorSpace, arrayOfInt, false, false, 1, 0);
/*     */ 
/*     */     
/*     */     }
/* 165 */     else if (this.zSize == 4) {
/*     */       
/* 167 */       ColorSpace colorSpace = ColorSpace.getInstance(1000);
/*     */       
/* 169 */       int[] arrayOfInt = { 8, 8, 8, 8 };
/* 170 */       componentColorModel = new ComponentColorModel(colorSpace, arrayOfInt, true, false, 3, 0);
/*     */     }
/*     */     else {
/*     */       
/* 174 */       throw new IOException("Unsupported options in file");
/*     */     } 
/*     */     
/* 177 */     WritableRaster writableRaster = componentColorModel.createCompatibleWritableRaster(this.xSize, this.ySize);
/* 178 */     BufferedImage bufferedImage = new BufferedImage(componentColorModel, writableRaster, false, null);
/*     */ 
/*     */     
/* 181 */     byte[] arrayOfByte = ((DataBufferByte)writableRaster.getDataBuffer()).getData(); short s1;
/* 182 */     for (s1 = 0; s1 < this.zSize; s1 = (short)(s1 + 1)) {
/* 183 */       for (short s2 = this.ySize - 1; s2 >= 0; s2--) {
/* 184 */         short s3; for (s3 = 0; s3 < this.xSize; s3 = (short)(s3 + 1)) {
/* 185 */           int m = read();
/* 186 */           if (m == -1) throw new IOException("Unexpected EOF"); 
/* 187 */           arrayOfByte[s2 * this.xSize * this.zSize + s3 * this.zSize + s1] = (byte)m;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     return bufferedImage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 197 */   public RgbFile(InputStream paramInputStream) { super(paramInputStream); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\objectfile\RgbFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */