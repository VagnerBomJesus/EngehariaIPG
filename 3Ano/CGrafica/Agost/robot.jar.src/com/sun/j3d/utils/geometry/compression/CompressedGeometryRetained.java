/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import javax.media.j3d.BoundingBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CompressedGeometryRetained
/*     */ {
/*     */   private static final int HEADER_LENGTH = 48;
/*     */   private static final int HEADER_MAJOR_VERSION_OFFSET = 0;
/*     */   private static final int HEADER_MINOR_VERSION_OFFSET = 1;
/*     */   private static final int HEADER_MINOR_MINOR_VERSION_OFFSET = 2;
/*     */   private static final int HEADER_BUFFER_TYPE_OFFSET = 3;
/*     */   private static final int HEADER_BUFFER_DATA_OFFSET = 4;
/*     */   static final byte TYPE_POINT = 1;
/*     */   static final byte TYPE_LINE = 2;
/*     */   static final byte TYPE_TRIANGLE = 4;
/*     */   int majorVersionNumber;
/*     */   int minorVersionNumber;
/*     */   int minorMinorVersionNumber;
/*     */   int packedVersion;
/*     */   int bufferType;
/*     */   int bufferContents;
/*     */   int renderFlags;
/*     */   int offset;
/*     */   int size;
/*     */   byte[] compressedGeometry;
/*     */   private byte[] originalCompressedGeometry;
/*     */   private BoundingBox geoBounds;
/*     */   private boolean byReference;
/*     */   
/*     */   CompressedGeometryRetained() {
/* 109 */     this.originalCompressedGeometry = null;
/*     */ 
/*     */     
/* 112 */     this.geoBounds = new BoundingBox();
/*     */ 
/*     */     
/* 115 */     this.byReference = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     this.geoBounds.setUpper(1.0D, 1.0D, 1.0D);
/* 124 */     this.geoBounds.setLower(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   boolean isByReference() { return this.byReference; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createByCopy(byte[] paramArrayOfByte) {
/* 139 */     this.compressedGeometry = new byte[48 + this.size];
/*     */     
/* 141 */     this.compressedGeometry[0] = (byte)this.majorVersionNumber;
/*     */ 
/*     */     
/* 144 */     this.compressedGeometry[1] = (byte)this.minorVersionNumber;
/*     */ 
/*     */     
/* 147 */     this.compressedGeometry[2] = (byte)this.minorMinorVersionNumber;
/*     */ 
/*     */     
/* 150 */     this.compressedGeometry[3] = (byte)this.bufferType;
/*     */ 
/*     */     
/* 153 */     this.compressedGeometry[4] = (byte)this.bufferContents;
/*     */ 
/*     */     
/* 156 */     System.arraycopy(paramArrayOfByte, this.offset, this.compressedGeometry, 48, this.size);
/*     */ 
/*     */     
/* 159 */     this.offset = 48;
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
/*     */   void createCompressedGeometry(CompressedGeometryData.Header paramHeader, byte[] paramArrayOfByte, boolean paramBoolean) {
/* 174 */     this.byReference = paramBoolean;
/*     */     
/* 176 */     if (paramHeader.lowerBound != null) {
/* 177 */       this.geoBounds.setLower(paramHeader.lowerBound);
/*     */     }
/* 179 */     if (paramHeader.upperBound != null) {
/* 180 */       this.geoBounds.setUpper(paramHeader.upperBound);
/*     */     }
/*     */ 
/*     */     
/* 184 */     this.majorVersionNumber = paramHeader.majorVersionNumber;
/* 185 */     this.minorVersionNumber = paramHeader.minorVersionNumber;
/* 186 */     this.minorMinorVersionNumber = paramHeader.minorMinorVersionNumber;
/*     */     
/* 188 */     this.packedVersion = paramHeader.majorVersionNumber << 24 | paramHeader.minorVersionNumber << 16 | paramHeader.minorMinorVersionNumber << 8;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     switch (paramHeader.bufferType) {
/*     */       case 0:
/* 195 */         this.bufferType = 1;
/*     */         break;
/*     */       case 1:
/* 198 */         this.bufferType = 2;
/*     */         break;
/*     */       case 2:
/* 201 */         this.bufferType = 4;
/*     */         break;
/*     */     } 
/*     */     
/* 205 */     this.bufferContents = paramHeader.bufferDataPresent;
/* 206 */     this.renderFlags = 0;
/*     */     
/* 208 */     this.size = paramHeader.size;
/* 209 */     this.offset = paramHeader.start;
/*     */     
/* 211 */     if (paramBoolean) {
/*     */ 
/*     */       
/* 214 */       this.compressedGeometry = paramArrayOfByte;
/* 215 */       this.originalCompressedGeometry = paramArrayOfByte;
/*     */     }
/*     */     else {
/*     */       
/* 219 */       createByCopy(paramArrayOfByte);
/* 220 */       this.originalCompressedGeometry = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getVertexFormat() {
/* 229 */     byte b = 1;
/*     */     
/* 231 */     if ((this.bufferContents & true) != 0) {
/* 232 */       b |= 0x2;
/*     */     }
/*     */     
/* 235 */     if ((this.bufferContents & 0x2) != 0) {
/* 236 */       if ((this.bufferContents & 0x4) != 0) {
/* 237 */         b |= 0xC;
/*     */       } else {
/* 239 */         b |= 0x4;
/*     */       } 
/*     */     }
/*     */     
/* 243 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getBufferType() {
/* 250 */     switch (this.bufferType) {
/*     */       case 1:
/* 252 */         return 0;
/*     */       case 2:
/* 254 */         return 1;
/*     */     } 
/*     */     
/* 257 */     return 2;
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
/* 268 */   void copy(byte[] paramArrayOfByte) { System.arraycopy(this.compressedGeometry, this.offset, paramArrayOfByte, 0, this.size); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   byte[] getReference() { return this.originalCompressedGeometry; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\compression\CompressedGeometryRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */