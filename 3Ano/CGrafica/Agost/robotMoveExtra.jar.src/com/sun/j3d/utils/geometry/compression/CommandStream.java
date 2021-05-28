/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CommandStream
/*     */ {
/*     */   static final int SET_NORM = 192;
/*     */   static final int SET_COLOR = 128;
/*     */   static final int VERTEX = 64;
/*     */   static final int MESH_B_R = 32;
/*     */   static final int SET_STATE = 24;
/*     */   static final int SET_TABLE = 16;
/*     */   static final int V_NO_OP = 1;
/*     */   static final int POSITION_TABLE = 0;
/*     */   static final int COLOR_TABLE = 1;
/*     */   static final int NORMAL_TABLE = 2;
/*     */   private byte[] bytes;
/*     */   private int byteOffset;
/*     */   private int bitOffset;
/*     */   private long lastBody;
/*     */   private int lastBodyLength;
/*     */   
/*  82 */   CommandStream() { this(65536); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CommandStream(int paramInt) {
/*  91 */     this.bytes = new byte[paramInt];
/*  92 */     clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clear() {
/* 101 */     this.bytes[0] = 0;
/*     */ 
/*     */     
/* 104 */     this.bitOffset = 0;
/* 105 */     this.byteOffset = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     this.lastBody = 0L;
/* 113 */     this.lastBodyLength = 5;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addCommand(int paramInt1, int paramInt2, long paramLong, int paramInt3) {
/* 141 */     addByte(paramInt1, paramInt2);
/* 142 */     addLong(this.lastBody, this.lastBodyLength);
/*     */     
/* 144 */     this.lastBody = paramLong;
/* 145 */     this.lastBodyLength = paramInt3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addByte(int paramInt1, int paramInt2) {
/* 152 */     int i = 8 - this.bitOffset;
/* 153 */     paramInt1 &= (int)CompressionStreamElement.lengthMask[paramInt2];
/*     */     
/* 155 */     if (paramInt2 <= i) {
/* 156 */       this.bytes[this.byteOffset] = (byte)(this.bytes[this.byteOffset] | paramInt1 << i - paramInt2);
/* 157 */       this.bitOffset += paramInt2;
/*     */       
/*     */       return;
/*     */     } 
/* 161 */     if (this.bytes.length == this.byteOffset + 1) {
/* 162 */       byte[] arrayOfByte = new byte[this.bytes.length * 2];
/* 163 */       System.arraycopy(this.bytes, 0, arrayOfByte, 0, this.bytes.length);
/* 164 */       this.bytes = arrayOfByte;
/*     */     } 
/*     */     
/* 167 */     this.bitOffset = paramInt2 - i;
/* 168 */     this.bytes[this.byteOffset] = (byte)(this.bytes[this.byteOffset] | paramInt1 >>> this.bitOffset);
/*     */     
/* 170 */     this.byteOffset++;
/* 171 */     this.bytes[this.byteOffset] = (byte)(paramInt1 << 8 - this.bitOffset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addLong(long paramLong, int paramInt) {
/* 178 */     int i = paramInt / 8;
/* 179 */     int j = paramInt - i * 8;
/*     */     
/* 181 */     if (j > 0) {
/* 182 */       addByte((int)(paramLong >>> i * 8), j);
/*     */     }
/* 184 */     while (i > 0) {
/* 185 */       addByte((int)(paramLong >>> (i - 1) * 8 & 0xFFL), 8);
/* 186 */       i--;
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
/*     */   void end() {
/*     */     int j;
/* 199 */     addByte(1, 8);
/* 200 */     addLong(this.lastBody, this.lastBodyLength);
/*     */     
/* 202 */     int i = (this.byteOffset + 1) % 8;
/* 203 */     if (i == 0 && this.bitOffset == 8) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 208 */     addByte(1, 8);
/* 209 */     i = (this.byteOffset + 1) % 8;
/*     */     
/* 211 */     if (i == 0) {
/* 212 */       j = 8 - this.bitOffset;
/*     */     } else {
/* 214 */       int k = 8 - i;
/* 215 */       j = 8 * k + 8 - this.bitOffset;
/*     */     } 
/*     */ 
/*     */     
/* 219 */     if (j < 5)
/*     */     {
/* 221 */       j += 64;
/*     */     }
/*     */ 
/*     */     
/* 225 */     if (j < 37) {
/*     */       
/* 227 */       addLong((j - 5 << j - 5), j);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 234 */     addLong(9961472L, 24);
/* 235 */     j -= 24;
/*     */ 
/*     */     
/* 238 */     addByte(1, 8);
/* 239 */     j -= 8;
/*     */ 
/*     */     
/* 242 */     addLong((j - 5 << j - 5), j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getByteCount() {
/* 251 */     if (this.byteOffset + this.bitOffset == 0) {
/* 252 */       return 0;
/*     */     }
/* 254 */     return this.byteOffset + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   byte[] getBytes() { return this.bytes; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\compression\CommandStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */