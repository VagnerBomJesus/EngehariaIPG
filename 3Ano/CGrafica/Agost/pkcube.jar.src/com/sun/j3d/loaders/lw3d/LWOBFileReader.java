/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LWOBFileReader
/*     */   extends BufferedInputStream
/*     */ {
/*     */   static final int TRACE = 1;
/*     */   static final int VALUES = 2;
/*     */   static final int MISC = 4;
/*     */   static final int LINE_TRACE = 8;
/*     */   static final int NONE = 0;
/*     */   static final int EXCEPTION = 16;
/*     */   protected DebugOutput debugPrinter;
/*     */   protected String theFilename;
/*     */   protected int marker;
/*     */   
/*     */   protected void debugOutputLn(int paramInt, String paramString) {
/*  78 */     if (paramString.equals("")) {
/*  79 */       this.debugPrinter.println(paramInt, paramString);
/*     */     } else {
/*  81 */       this.debugPrinter.println(paramInt, getClass().getName() + "::" + paramString);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToken() throws ParsingErrorException {
/*  89 */     byte[] arrayOfByte = new byte[4];
/*     */     try {
/*  91 */       int i = read(arrayOfByte, 0, 4);
/*  92 */       if (i == -1) {
/*  93 */         debugOutputLn(8, "no token - returning null");
/*  94 */         return null;
/*     */       } 
/*  96 */       return new String(arrayOfByte);
/*     */     }
/*  98 */     catch (IOException iOException) {
/*  99 */       debugOutputLn(16, "getToken: " + iOException);
/* 100 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skipLength(int paramInt) throws ParsingErrorException {
/*     */     try {
/* 111 */       skip(paramInt);
/* 112 */       this.marker += paramInt;
/*     */     }
/* 114 */     catch (IOException iOException) {
/* 115 */       debugOutputLn(16, "skipLength: " + iOException);
/* 116 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt() throws ParsingErrorException {
/*     */     try {
/* 127 */       int i = 0;
/* 128 */       for (byte b = 0; b < 4; b++) {
/* 129 */         int j = read();
/* 130 */         if (j == -1)
/* 131 */           throw new ParsingErrorException("Unexpected EOF"); 
/* 132 */         i = i << 8 | j;
/*     */       } 
/* 134 */       return i;
/*     */     }
/* 136 */     catch (IOException iOException) {
/* 137 */       debugOutputLn(16, "getInt: " + iOException);
/* 138 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public float getFloat() throws ParsingErrorException { return Float.intBitsToFloat(getInt()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public String getFilename() throws ParsingErrorException { return this.theFilename; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() throws ParsingErrorException {
/* 167 */     byte[] arrayOfByte = new byte[512];
/*     */     try {
/*     */       byte b;
/* 170 */       byte b1 = 0;
/*     */       do {
/* 172 */         b = (byte)read();
/* 173 */         arrayOfByte[b1++] = b;
/* 174 */       } while (b != 0);
/*     */       
/* 176 */       if (b1 % 2 != 0) read();
/*     */     
/* 178 */     } catch (IOException iOException) {
/* 179 */       debugOutputLn(16, "getString: " + iOException);
/* 180 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/* 182 */     return new String(arrayOfByte);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getVerts(float[] paramArrayOfFloat, int paramInt) throws ParsingErrorException {
/* 191 */     for (byte b = 0; b < paramInt; b++) {
/* 192 */       paramArrayOfFloat[b * 3 + 0] = getFloat();
/* 193 */       paramArrayOfFloat[b * 3 + 1] = getFloat();
/* 194 */       paramArrayOfFloat[b * 3 + 2] = -getFloat();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getShortInt() throws ParsingErrorException {
/* 204 */     int i = 0;
/*     */     try {
/* 206 */       i = read();
/* 207 */       i = i << 8 | read();
/*     */       
/* 209 */       if ((i & 0x8000) != 0) i |= 0xFFFF0000;
/*     */     
/* 211 */     } catch (IOException iOException) {
/* 212 */       debugOutputLn(16, "getShortInt: " + iOException);
/* 213 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/* 215 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public int getMarker() throws ParsingErrorException { return this.marker; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws ParsingErrorException {
/* 231 */     this.marker++;
/* 232 */     return super.read();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException {
/* 238 */     int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
/* 239 */     if (i != -1) this.marker += i; 
/* 240 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LWOBFileReader(String paramString) throws FileNotFoundException {
/* 249 */     super(new FileInputStream(paramString));
/*     */ 
/*     */     
/* 252 */     this.debugPrinter = new DebugOutput(127);
/*     */     
/* 254 */     this.marker = 0;
/*     */   }
/*     */   
/*     */   public LWOBFileReader(URL paramURL) throws IOException {
/* 258 */     super(paramURL.openStream());
/*     */ 
/*     */     
/* 261 */     this.debugPrinter = new DebugOutput(127);
/*     */     
/* 263 */     this.marker = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\LWOBFileReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */