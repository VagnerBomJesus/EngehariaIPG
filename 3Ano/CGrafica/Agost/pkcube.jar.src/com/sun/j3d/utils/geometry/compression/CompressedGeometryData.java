/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import javax.media.j3d.J3DBuffer;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompressedGeometryData
/*     */ {
/*     */   private Header cgHeader;
/*     */   private CompressedGeometryRetained retained;
/*     */   
/* 118 */   public CompressedGeometryData(Header paramHeader, byte[] paramArrayOfByte) { this(paramHeader, paramArrayOfByte, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompressedGeometryData(Header paramHeader, byte[] paramArrayOfByte, boolean paramBoolean) {
/* 147 */     if (paramHeader.size + paramHeader.start > paramArrayOfByte.length) {
/* 148 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("CompressedGeometry0"));
/*     */     }
/*     */ 
/*     */     
/* 152 */     this.cgHeader = new Header();
/* 153 */     paramHeader.copy(this.cgHeader);
/*     */ 
/*     */     
/* 156 */     this.retained = new CompressedGeometryRetained();
/* 157 */     this.retained.createCompressedGeometry(this.cgHeader, paramArrayOfByte, paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (!paramBoolean) {
/* 165 */       this.cgHeader.start = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public CompressedGeometryData(Header paramHeader, J3DBuffer paramJ3DBuffer) { throw new UnsupportedOperationException("not implemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public int getByteCount() { return this.cgHeader.size; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public void getCompressedGeometryHeader(Header paramHeader) { this.cgHeader.copy(paramHeader); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getCompressedGeometry(byte[] paramArrayOfByte) {
/* 242 */     if (isByReference()) {
/* 243 */       throw new IllegalStateException(J3dUtilsI18N.getString("CompressedGeometry7"));
/*     */     }
/*     */ 
/*     */     
/* 247 */     if (this.cgHeader.size > paramArrayOfByte.length) {
/* 248 */       throw new ArrayIndexOutOfBoundsException(J3dUtilsI18N.getString("CompressedGeometry4"));
/*     */     }
/*     */ 
/*     */     
/* 252 */     this.retained.copy(paramArrayOfByte);
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
/*     */   public Shape3D[] decompress() {
/* 266 */     CompressedGeometryRetained compressedGeometryRetained = this.retained;
/*     */     
/* 268 */     GeometryDecompressorShape3D geometryDecompressorShape3D = new GeometryDecompressorShape3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     return geometryDecompressorShape3D.toTriangleStripArrays(compressedGeometryRetained);
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
/* 294 */   public boolean isByReference() { return this.retained.isByReference(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getCompressedGeometryRef() {
/* 307 */     if (!isByReference()) {
/* 308 */       throw new IllegalStateException(J3dUtilsI18N.getString("CompressedGeometry8"));
/*     */     }
/*     */ 
/*     */     
/* 312 */     return this.retained.getReference();
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
/* 324 */   public J3DBuffer getCompressedGeometryBuffer() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Header
/*     */   {
/*     */     public static final int POINT_BUFFER = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int LINE_BUFFER = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int TRIANGLE_BUFFER = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int NORMAL_IN_BUFFER = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int COLOR_IN_BUFFER = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int ALPHA_IN_BUFFER = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int majorVersionNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int minorVersionNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int minorMinorVersionNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int bufferType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int bufferDataPresent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int size;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int start;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 460 */     public Point3d lowerBound = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 471 */     public Point3d upperBound = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void copy(Header param1Header) {
/* 493 */       param1Header.majorVersionNumber = this.majorVersionNumber;
/* 494 */       param1Header.minorVersionNumber = this.minorVersionNumber;
/* 495 */       param1Header.minorMinorVersionNumber = this.minorMinorVersionNumber;
/* 496 */       param1Header.bufferType = this.bufferType;
/* 497 */       param1Header.bufferDataPresent = this.bufferDataPresent;
/* 498 */       param1Header.size = this.size;
/* 499 */       param1Header.start = this.start;
/* 500 */       param1Header.lowerBound = this.lowerBound;
/* 501 */       param1Header.upperBound = this.upperBound;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 511 */       String str1 = "UNKNOWN";
/* 512 */       switch (this.bufferType) { case 0:
/* 513 */           str1 = "POINT_BUFFER"; break;
/* 514 */         case 1: str1 = "LINE_BUFFER"; break;
/* 515 */         case 2: str1 = "TRIANGLE_BUFFER";
/*     */           break; }
/*     */       
/* 518 */       String str2 = "";
/* 519 */       if ((this.bufferDataPresent & true) != 0)
/* 520 */         str2 = str2 + "NORMALS "; 
/* 521 */       if ((this.bufferDataPresent & 0x2) != 0)
/* 522 */         str2 = str2 + "COLORS "; 
/* 523 */       if ((this.bufferDataPresent & 0x4) != 0) {
/* 524 */         str2 = str2 + "ALPHA ";
/*     */       }
/* 526 */       String str3 = "null";
/* 527 */       if (this.lowerBound != null) {
/* 528 */         str3 = this.lowerBound.toString();
/*     */       }
/* 530 */       String str4 = "null";
/* 531 */       if (this.upperBound != null) {
/* 532 */         str4 = this.upperBound.toString();
/*     */       }
/* 534 */       return "majorVersionNumber: " + this.majorVersionNumber + "  " + "minorVersionNumber: " + this.minorVersionNumber + "  " + "minorMinorVersionNumber: " + this.minorMinorVersionNumber + "\n" + "bufferType: " + str1 + "  " + "bufferDataPresent: " + str2 + "\n" + "size: " + this.size + "  " + "start: " + this.start + "\n" + "lower bound: " + str3 + "\n" + "upper bound: " + str4 + "  ";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\compression\CompressedGeometryData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */