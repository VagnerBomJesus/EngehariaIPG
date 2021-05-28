/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class GeometryCompressor
/*     */ {
/*     */   private static final boolean benchmark = false;
/*     */   private static final boolean printStream = false;
/*     */   private static final boolean printHuffman = false;
/*     */   private HuffmanTable huffmanTable;
/*     */   private CommandStream outputBuffer;
/*     */   private CompressedGeometryData.Header cgHeader;
/*     */   private long startTime;
/*     */   
/*     */   public GeometryCompressor() {
/*  76 */     this.cgHeader = new CompressedGeometryData.Header();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     this.cgHeader.majorVersionNumber = 1;
/*  82 */     this.cgHeader.minorVersionNumber = 0;
/*  83 */     this.cgHeader.minorMinorVersionNumber = 2;
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
/*     */   public CompressedGeometryData compress(CompressionStream paramCompressionStream) {
/*  96 */     compressStream(paramCompressionStream);
/*  97 */     CompressedGeometryData compressedGeometryData = new CompressedGeometryData(this.cgHeader, this.outputBuffer.getBytes());
/*     */     
/*  99 */     this.outputBuffer.clear();
/* 100 */     return compressedGeometryData;
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
/*     */   public void compress(CompressionStream paramCompressionStream, CompressedGeometryFile paramCompressedGeometryFile) throws IOException {
/* 115 */     compressStream(paramCompressionStream);
/* 116 */     paramCompressedGeometryFile.write(this.cgHeader, this.outputBuffer.getBytes());
/*     */     
/* 118 */     this.outputBuffer.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void compressStream(CompressionStream paramCompressionStream) {
/* 129 */     this.huffmanTable = new HuffmanTable();
/*     */ 
/*     */ 
/*     */     
/* 133 */     paramCompressionStream.quantize(this.huffmanTable);
/*     */ 
/*     */     
/* 136 */     this.huffmanTable.computeTags();
/*     */ 
/*     */     
/* 139 */     this.outputBuffer = new CommandStream(paramCompressionStream.getByteCount() / 3);
/* 140 */     paramCompressionStream.outputCommands(this.huffmanTable, this.outputBuffer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.cgHeader.bufferType = paramCompressionStream.streamType;
/* 149 */     this.cgHeader.bufferDataPresent = 0;
/* 150 */     this.cgHeader.lowerBound = new Point3d(paramCompressionStream.ncBounds[0]);
/* 151 */     this.cgHeader.upperBound = new Point3d(paramCompressionStream.ncBounds[1]);
/*     */     
/* 153 */     if (paramCompressionStream.vertexNormals) {
/* 154 */       this.cgHeader.bufferDataPresent |= 0x1;
/*     */     }
/*     */     
/* 157 */     if (paramCompressionStream.vertexColor3 || paramCompressionStream.vertexColor4) {
/* 158 */       this.cgHeader.bufferDataPresent |= 0x2;
/*     */     }
/*     */     
/* 161 */     if (paramCompressionStream.vertexColor4) {
/* 162 */       this.cgHeader.bufferDataPresent |= 0x4;
/*     */     }
/*     */     
/* 165 */     this.cgHeader.start = 0;
/* 166 */     this.cgHeader.size = this.outputBuffer.getByteCount();
/*     */ 
/*     */     
/* 169 */     this.huffmanTable.clear();
/*     */   }
/*     */   
/*     */   private void printBench(CompressionStream paramCompressionStream) {
/* 173 */     long l = System.currentTimeMillis() - this.startTime;
/* 174 */     int i = paramCompressionStream.getVertexCount();
/* 175 */     int j = paramCompressionStream.getMeshReferenceCount();
/* 176 */     int k = j + i;
/* 177 */     float f1 = 100.0F * j / k;
/*     */     
/* 179 */     float f2 = paramCompressionStream.getByteCount() / this.outputBuffer.getByteCount();
/*     */ 
/*     */     
/* 182 */     byte b = 12 + (paramCompressionStream.vertexColor3 ? 12 : 0) + (paramCompressionStream.vertexColor4 ? 16 : 0) + (paramCompressionStream.vertexNormals ? 12 : 0);
/*     */ 
/*     */ 
/*     */     
/* 186 */     float f3 = this.outputBuffer.getByteCount() / k;
/*     */ 
/*     */     
/* 189 */     System.out.println("\nGeometryCompressor:\n" + k + " total vertices\n" + i + " streamed vertices\n" + j + " mesh buffer references (" + f1 + "%)\n" + paramCompressionStream.getByteCount() + " bytes streamed geometry compressed to " + this.outputBuffer.getByteCount() + " in " + ((float)l / 1000.0F) + " sec\n" + (paramCompressionStream.getByteCount() / (float)l) + " kbytes/sec, " + "stream compression ratio " + f2 + "\n\n" + b + " original bytes per vertex, " + f3 + " compressed bytes per vertex\n" + "total vertex compression ratio " + (b / f3) + "\n\n" + "lower bound " + paramCompressionStream.ncBounds[0].toString() + "\n" + "upper bound " + paramCompressionStream.ncBounds[1].toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\compression\GeometryCompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */