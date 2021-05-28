/*     */ package com.sun.j3d.utils.compression;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.CompressedGeometry;
/*     */ import javax.media.j3d.CompressedGeometryHeader;
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
/*     */ public class GeometryCompressor
/*     */ {
/*     */   private static final boolean benchmark = false;
/*     */   private static final boolean printStream = false;
/*     */   private static final boolean printHuffman = false;
/*     */   private HuffmanTable huffmanTable;
/*     */   private CommandStream outputBuffer;
/*     */   private CompressedGeometryHeader cgHeader;
/*     */   private long startTime;
/*     */   
/*     */   public GeometryCompressor() {
/*  79 */     this.cgHeader = new CompressedGeometryHeader();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     this.cgHeader.majorVersionNumber = 1;
/*  85 */     this.cgHeader.minorVersionNumber = 0;
/*  86 */     this.cgHeader.minorMinorVersionNumber = 2;
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
/*     */   public CompressedGeometry compress(CompressionStream paramCompressionStream) {
/*  98 */     compressStream(paramCompressionStream);
/*  99 */     CompressedGeometry compressedGeometry = new CompressedGeometry(this.cgHeader, this.outputBuffer.getBytes());
/*     */     
/* 101 */     this.outputBuffer.clear();
/* 102 */     return compressedGeometry;
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
/* 117 */     compressStream(paramCompressionStream);
/* 118 */     paramCompressedGeometryFile.write(this.cgHeader, this.outputBuffer.getBytes());
/*     */     
/* 120 */     this.outputBuffer.clear();
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
/* 131 */     this.huffmanTable = new HuffmanTable();
/*     */ 
/*     */ 
/*     */     
/* 135 */     paramCompressionStream.quantize(this.huffmanTable);
/*     */ 
/*     */     
/* 138 */     this.huffmanTable.computeTags();
/*     */ 
/*     */     
/* 141 */     this.outputBuffer = new CommandStream(paramCompressionStream.getByteCount() / 3);
/* 142 */     paramCompressionStream.outputCommands(this.huffmanTable, this.outputBuffer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     this.cgHeader.bufferType = paramCompressionStream.streamType;
/* 151 */     this.cgHeader.bufferDataPresent = 0;
/* 152 */     this.cgHeader.lowerBound = new Point3d(paramCompressionStream.ncBounds[0]);
/* 153 */     this.cgHeader.upperBound = new Point3d(paramCompressionStream.ncBounds[1]);
/*     */     
/* 155 */     if (paramCompressionStream.vertexNormals) {
/* 156 */       this.cgHeader.bufferDataPresent |= 0x1;
/*     */     }
/*     */     
/* 159 */     if (paramCompressionStream.vertexColor3 || paramCompressionStream.vertexColor4) {
/* 160 */       this.cgHeader.bufferDataPresent |= 0x2;
/*     */     }
/*     */     
/* 163 */     if (paramCompressionStream.vertexColor4) {
/* 164 */       this.cgHeader.bufferDataPresent |= 0x4;
/*     */     }
/*     */     
/* 167 */     this.cgHeader.start = 0;
/* 168 */     this.cgHeader.size = this.outputBuffer.getByteCount();
/*     */ 
/*     */     
/* 171 */     this.huffmanTable.clear();
/*     */   }
/*     */   
/*     */   private void printBench(CompressionStream paramCompressionStream) {
/* 175 */     long l = System.currentTimeMillis() - this.startTime;
/* 176 */     int i = paramCompressionStream.getVertexCount();
/* 177 */     int j = paramCompressionStream.getMeshReferenceCount();
/* 178 */     int k = j + i;
/* 179 */     float f1 = 100.0F * j / k;
/*     */     
/* 181 */     float f2 = paramCompressionStream.getByteCount() / this.outputBuffer.getByteCount();
/*     */ 
/*     */     
/* 184 */     byte b = 12 + (paramCompressionStream.vertexColor3 ? 12 : 0) + (paramCompressionStream.vertexColor4 ? 16 : 0) + (paramCompressionStream.vertexNormals ? 12 : 0);
/*     */ 
/*     */ 
/*     */     
/* 188 */     float f3 = this.outputBuffer.getByteCount() / k;
/*     */ 
/*     */     
/* 191 */     System.out.println("\nGeometryCompressor:\n" + k + " total vertices\n" + i + " streamed vertices\n" + j + " mesh buffer references (" + f1 + "%)\n" + paramCompressionStream.getByteCount() + " bytes streamed geometry compressed to " + this.outputBuffer.getByteCount() + " in " + ((float)l / 1000.0F) + " sec\n" + (paramCompressionStream.getByteCount() / (float)l) + " kbytes/sec, " + "stream compression ratio " + f2 + "\n\n" + b + " original bytes per vertex, " + f3 + " compressed bytes per vertex\n" + "total vertex compression ratio " + (b / f3) + "\n\n" + "lower bound " + paramCompressionStream.ncBounds[0].toString() + "\n" + "upper bound " + paramCompressionStream.ncBounds[1].toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\compression\GeometryCompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */