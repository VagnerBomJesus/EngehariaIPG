/*     */ package com.sun.j3d.utils.compression;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CompressionStreamVertex
/*     */   extends CompressionStreamElement
/*     */ {
/*     */   private int X;
/*     */   private int Y;
/*     */   private int Z;
/*     */   private int meshFlag;
/*     */   private int stripFlag;
/*     */   private float floatX;
/*     */   private float floatY;
/*     */   private float floatZ;
/*     */   int xAbsolute;
/*     */   int yAbsolute;
/*     */   int zAbsolute;
/*     */   CompressionStreamColor color;
/*     */   CompressionStreamNormal normal;
/*     */   
/*     */   CompressionStreamVertex(CompressionStream paramCompressionStream, Point3f paramPoint3f, Vector3f paramVector3f, Color3f paramColor3f, int paramInt1, int paramInt2) {
/*  86 */     this(paramCompressionStream, paramPoint3f, paramVector3f, paramInt1, paramInt2);
/*     */     
/*  88 */     if (paramCompressionStream.vertexColor3) {
/*  89 */       this.color = new CompressionStreamColor(paramCompressionStream, paramColor3f);
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
/*     */   CompressionStreamVertex(CompressionStream paramCompressionStream, Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt1, int paramInt2) {
/* 108 */     this(paramCompressionStream, paramPoint3f, paramVector3f, paramInt1, paramInt2);
/*     */     
/* 110 */     if (paramCompressionStream.vertexColor4) {
/* 111 */       this.color = new CompressionStreamColor(paramCompressionStream, paramColor4f);
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
/*     */   CompressionStreamVertex(CompressionStream paramCompressionStream, Point3f paramPoint3f, Vector3f paramVector3f, int paramInt1, int paramInt2) {
/*     */     this.color = null;
/*     */     this.normal = null;
/* 128 */     this.stripFlag = paramInt1;
/* 129 */     this.meshFlag = paramInt2;
/* 130 */     this.floatX = paramPoint3f.x;
/* 131 */     this.floatY = paramPoint3f.y;
/* 132 */     this.floatZ = paramPoint3f.z;
/*     */     
/* 134 */     paramCompressionStream.byteCount += 12;
/* 135 */     paramCompressionStream.vertexCount++;
/*     */     
/* 137 */     if (paramPoint3f.x < (paramCompressionStream.mcBounds[0]).x) (paramCompressionStream.mcBounds[0]).x = paramPoint3f.x; 
/* 138 */     if (paramPoint3f.y < (paramCompressionStream.mcBounds[0]).y) (paramCompressionStream.mcBounds[0]).y = paramPoint3f.y; 
/* 139 */     if (paramPoint3f.z < (paramCompressionStream.mcBounds[0]).z) (paramCompressionStream.mcBounds[0]).z = paramPoint3f.z;
/*     */     
/* 141 */     if (paramPoint3f.x > (paramCompressionStream.mcBounds[1]).x) (paramCompressionStream.mcBounds[1]).x = paramPoint3f.x; 
/* 142 */     if (paramPoint3f.y > (paramCompressionStream.mcBounds[1]).y) (paramCompressionStream.mcBounds[1]).y = paramPoint3f.y; 
/* 143 */     if (paramPoint3f.z > (paramCompressionStream.mcBounds[1]).z) (paramCompressionStream.mcBounds[1]).z = paramPoint3f.z;
/*     */     
/* 145 */     if (paramCompressionStream.vertexNormals) {
/* 146 */       this.normal = new CompressionStreamNormal(paramCompressionStream, paramVector3f);
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
/*     */   void quantize(CompressionStream paramCompressionStream, HuffmanTable paramHuffmanTable) {
/* 161 */     boolean bool = (paramCompressionStream.positionQuant < 1) ? 1 : ((paramCompressionStream.positionQuant > 16) ? 16 : paramCompressionStream.positionQuant);
/*     */ 
/*     */ 
/*     */     
/* 165 */     this.absolute = false;
/* 166 */     if (paramCompressionStream.firstPosition || paramCompressionStream.positionQuantChanged) {
/* 167 */       this.absolute = true;
/* 168 */       paramCompressionStream.lastPosition[0] = 0;
/* 169 */       paramCompressionStream.lastPosition[1] = 0;
/* 170 */       paramCompressionStream.lastPosition[2] = 0;
/* 171 */       paramCompressionStream.firstPosition = false;
/* 172 */       paramCompressionStream.positionQuantChanged = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 177 */     double d1 = (this.floatX - paramCompressionStream.center[0]) * paramCompressionStream.scale;
/* 178 */     double d2 = (this.floatY - paramCompressionStream.center[1]) * paramCompressionStream.scale;
/* 179 */     double d3 = (this.floatZ - paramCompressionStream.center[2]) * paramCompressionStream.scale;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     this.X = (int)(d1 * 32768.0D);
/* 185 */     this.Y = (int)(d2 * 32768.0D);
/* 186 */     this.Z = (int)(d3 * 32768.0D);
/*     */ 
/*     */     
/* 189 */     this.X &= quantizationMask[bool];
/* 190 */     this.Y &= quantizationMask[bool];
/* 191 */     this.Z &= quantizationMask[bool];
/*     */ 
/*     */     
/* 194 */     if (this.X < (paramCompressionStream.qcBounds[0]).x) (paramCompressionStream.qcBounds[0]).x = this.X; 
/* 195 */     if (this.Y < (paramCompressionStream.qcBounds[0]).y) (paramCompressionStream.qcBounds[0]).y = this.Y; 
/* 196 */     if (this.Z < (paramCompressionStream.qcBounds[0]).z) (paramCompressionStream.qcBounds[0]).z = this.Z;
/*     */     
/* 198 */     if (this.X > (paramCompressionStream.qcBounds[1]).x) (paramCompressionStream.qcBounds[1]).x = this.X; 
/* 199 */     if (this.Y > (paramCompressionStream.qcBounds[1]).y) (paramCompressionStream.qcBounds[1]).y = this.Y; 
/* 200 */     if (this.Z > (paramCompressionStream.qcBounds[1]).z) (paramCompressionStream.qcBounds[1]).z = this.Z;
/*     */ 
/*     */     
/* 203 */     this.xAbsolute = this.X;
/* 204 */     this.yAbsolute = this.Y;
/* 205 */     this.zAbsolute = this.Z;
/*     */ 
/*     */     
/* 208 */     this.X -= paramCompressionStream.lastPosition[0];
/* 209 */     this.Y -= paramCompressionStream.lastPosition[1];
/* 210 */     this.Z -= paramCompressionStream.lastPosition[2];
/*     */ 
/*     */     
/* 213 */     paramCompressionStream.lastPosition[0] = paramCompressionStream.lastPosition[0] + this.X;
/* 214 */     paramCompressionStream.lastPosition[1] = paramCompressionStream.lastPosition[1] + this.Y;
/* 215 */     paramCompressionStream.lastPosition[2] = paramCompressionStream.lastPosition[2] + this.Z;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     this.X = this.X << 16 >> 16;
/* 221 */     this.Y = this.Y << 16 >> 16;
/* 222 */     this.Z = this.Z << 16 >> 16;
/*     */ 
/*     */     
/* 225 */     computeLengthShift(this.X, this.Y, this.Z);
/*     */ 
/*     */     
/* 228 */     if (this.length == 0) {
/* 229 */       this.length = 1;
/*     */     }
/*     */     
/* 232 */     paramHuffmanTable.addPositionEntry(this.length, this.shift, this.absolute);
/*     */ 
/*     */     
/* 235 */     if (this.color != null) {
/* 236 */       this.color.quantize(paramCompressionStream, paramHuffmanTable);
/*     */     }
/* 238 */     if (this.normal != null) {
/* 239 */       this.normal.quantize(paramCompressionStream, paramHuffmanTable);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 244 */     paramCompressionStream; if (this.meshFlag == 1) {
/* 245 */       paramCompressionStream.meshBuffer.push(this);
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
/*     */   void outputCommand(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream) {
/* 258 */     int i = 64;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     HuffmanNode huffmanNode = paramHuffmanTable.getPositionEntry(this.length, this.shift, this.absolute);
/*     */ 
/*     */     
/* 267 */     int j = huffmanNode.dataLength - huffmanNode.shift;
/* 268 */     int k = huffmanNode.tagLength + 3 * j;
/*     */     
/* 270 */     this.X = this.X >> huffmanNode.shift & (int)lengthMask[j];
/* 271 */     this.Y = this.Y >> huffmanNode.shift & (int)lengthMask[j];
/* 272 */     this.Z = this.Z >> huffmanNode.shift & (int)lengthMask[j];
/*     */     
/* 274 */     long l1 = huffmanNode.tag << 3 * j | this.X << 2 * j | this.Y << 1 * j | this.Z << 0 * j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     if (k < 6) {
/*     */ 
/*     */       
/* 283 */       i |= (int)(l1 << 6 - k);
/* 284 */       k = 0;
/*     */     }
/*     */     else {
/*     */       
/* 288 */       i |= (int)(l1 >>> k - 6);
/* 289 */       k -= 6;
/*     */     } 
/*     */ 
/*     */     
/* 293 */     long l2 = this.stripFlag << k + 1 | this.meshFlag << k + 0 | l1 & lengthMask[k];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     paramCommandStream.addCommand(i, 8, l2, k + 3);
/*     */ 
/*     */     
/* 302 */     if (this.normal != null) {
/* 303 */       this.normal.outputSubcommand(paramHuffmanTable, paramCommandStream);
/*     */     }
/* 305 */     if (this.color != null)
/* 306 */       this.color.outputSubcommand(paramHuffmanTable, paramCommandStream); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 310 */     String str1 = this.absolute ? "" : "delta ";
/* 311 */     String str2 = (this.color == null) ? "" : ("\n\n " + this.color.toString());
/* 312 */     String str3 = (this.normal == null) ? "" : ("\n\n " + this.normal.toString());
/*     */     
/* 314 */     return "position: " + this.floatX + " " + this.floatY + " " + this.floatZ + "\n" + "fixed point " + str1 + this.X + " " + this.Y + " " + this.Z + "\n" + "length " + this.length + " shift " + this.shift + (this.absolute ? " absolute" : " relative") + "\n" + "strip flag " + this.stripFlag + " mesh flag " + this.meshFlag + str2 + str3;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\compression\CompressionStreamVertex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */