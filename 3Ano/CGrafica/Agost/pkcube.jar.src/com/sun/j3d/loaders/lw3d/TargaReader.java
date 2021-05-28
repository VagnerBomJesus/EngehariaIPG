/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TargaReader
/*     */   extends ParserObject
/*     */ {
/*     */   BufferedInputStream bufferedReader;
/*  73 */   Image theImage = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TargaReader(String paramString, int paramInt) throws FileNotFoundException {
/*  80 */     super(paramInt);
/*  81 */     debugOutputLn(1, "constructor");
/*  82 */     this.bufferedReader = new BufferedInputStream(new DataInputStream(new FileInputStream(paramString)));
/*     */     
/*  84 */     if (this.bufferedReader != null) {
/*  85 */       parseFile();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   Image getImage() { return this.theImage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void parseFile() throws IncorrectFormatException, ParsingErrorException {
/*     */     try {
/* 134 */       int i = this.bufferedReader.read();
/* 135 */       int j = this.bufferedReader.read();
/* 136 */       int k = this.bufferedReader.read();
/* 137 */       this.bufferedReader.skip(9L);
/* 138 */       int m = this.bufferedReader.read() | this.bufferedReader.read() << 8;
/* 139 */       int n = this.bufferedReader.read() | this.bufferedReader.read() << 8;
/* 140 */       int i1 = this.bufferedReader.read();
/* 141 */       int i2 = this.bufferedReader.read();
/* 142 */       boolean bool1 = ((i2 & 0x20) == 0) ? 1 : 0;
/* 143 */       boolean bool2 = ((i2 & 0x10) == 0) ? 1 : 0;
/* 144 */       this.bufferedReader.skip(i);
/*     */ 
/*     */       
/* 147 */       if (j == 1 || k != 2 || (i1 != 24 && i1 != 32))
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 152 */         throw new IncorrectFormatException("This format is not readable by the Lightwave loader.  Only 24- or 32-bit true-color uncompressed Targa images will work");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 159 */       BufferedImage bufferedImage = new BufferedImage(m, n, 2);
/*     */       
/* 161 */       int[] arrayOfInt = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       for (int i3 = 0; i3 < n; i3++) {
/* 168 */         int i4; if (bool1) {
/* 169 */           i4 = n - i3 - 1;
/*     */         } else {
/* 171 */           i4 = i3;
/* 172 */         }  for (int i5 = 0; i5 < m; i5++) {
/*     */           int i6;
/* 174 */           if (bool2) {
/* 175 */             i6 = i5;
/*     */           } else {
/* 177 */             i6 = m - i5 - 1;
/*     */           } 
/* 179 */           int i7 = this.bufferedReader.read();
/* 180 */           int i8 = this.bufferedReader.read();
/* 181 */           int i9 = this.bufferedReader.read();
/* 182 */           int i10 = 255;
/* 183 */           if (i1 == 32)
/* 184 */             i10 = this.bufferedReader.read(); 
/* 185 */           arrayOfInt[i4 * m + i6] = i10 << 24 | i9 << 16 | i8 << 8 | i7;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 191 */       this.theImage = bufferedImage;
/*     */     }
/* 193 */     catch (IOException iOException) {
/* 194 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\TargaReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */