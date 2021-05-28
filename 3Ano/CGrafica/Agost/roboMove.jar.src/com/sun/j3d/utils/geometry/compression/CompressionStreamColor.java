/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Color4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CompressionStreamColor
/*     */   extends CompressionStreamElement
/*     */ {
/*     */   private int R;
/*     */   private int G;
/*     */   private int B;
/*     */   private int A;
/*     */   private boolean color3;
/*     */   private boolean color4;
/*     */   private float colorR;
/*     */   private float colorG;
/*     */   private float colorB;
/*     */   private float colorA;
/*     */   int rAbsolute;
/*     */   int gAbsolute;
/*     */   int bAbsolute;
/*     */   int aAbsolute;
/*     */   
/*     */   CompressionStreamColor(CompressionStream paramCompressionStream, Color3f paramColor3f) {
/*  70 */     this.color4 = false;
/*  71 */     this.color3 = true;
/*  72 */     this.colorR = paramColor3f.x;
/*  73 */     this.colorG = paramColor3f.y;
/*  74 */     this.colorB = paramColor3f.z;
/*  75 */     this.colorA = 0.0F;
/*  76 */     paramCompressionStream.byteCount += 12;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CompressionStreamColor(CompressionStream paramCompressionStream, Color4f paramColor4f) {
/*  86 */     this.color3 = false;
/*  87 */     this.color4 = true;
/*  88 */     this.colorR = paramColor4f.x;
/*  89 */     this.colorG = paramColor4f.y;
/*  90 */     this.colorB = paramColor4f.z;
/*  91 */     this.colorA = paramColor4f.w;
/*  92 */     paramCompressionStream.byteCount += 16;
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
/*     */   void quantize(CompressionStream paramCompressionStream, HuffmanTable paramHuffmanTable) {
/* 112 */     byte b = (paramCompressionStream.colorQuant < 2) ? 2 : ((paramCompressionStream.colorQuant > 16) ? 16 : paramCompressionStream.colorQuant);
/*     */ 
/*     */ 
/*     */     
/* 116 */     this.absolute = false;
/* 117 */     if (paramCompressionStream.firstColor || paramCompressionStream.colorQuantChanged) {
/* 118 */       this.absolute = true;
/* 119 */       paramCompressionStream.lastColor[0] = 0;
/* 120 */       paramCompressionStream.lastColor[1] = 0;
/* 121 */       paramCompressionStream.lastColor[2] = 0;
/* 122 */       paramCompressionStream.lastColor[3] = 0;
/* 123 */       paramCompressionStream.firstColor = false;
/* 124 */       paramCompressionStream.colorQuantChanged = false;
/*     */     } 
/*     */ 
/*     */     
/* 128 */     if (this.color3) {
/* 129 */       this.R = (int)(this.colorR * 32768.0D);
/* 130 */       this.G = (int)(this.colorG * 32768.0D);
/* 131 */       this.B = (int)(this.colorB * 32768.0D);
/* 132 */       this.A = 0;
/* 133 */     } else if (this.color4) {
/* 134 */       this.R = (int)(this.colorR * 32768.0D);
/* 135 */       this.G = (int)(this.colorG * 32768.0D);
/* 136 */       this.B = (int)(this.colorB * 32768.0D);
/* 137 */       this.A = (int)(this.colorA * 32768.0D);
/*     */     } 
/*     */ 
/*     */     
/* 141 */     this.R = (this.R > 32767) ? 32767 : ((this.R < 0) ? 0 : this.R);
/* 142 */     this.G = (this.G > 32767) ? 32767 : ((this.G < 0) ? 0 : this.G);
/* 143 */     this.B = (this.B > 32767) ? 32767 : ((this.B < 0) ? 0 : this.B);
/* 144 */     this.A = (this.A > 32767) ? 32767 : ((this.A < 0) ? 0 : this.A);
/*     */ 
/*     */     
/* 147 */     this.R &= quantizationMask[b];
/* 148 */     this.G &= quantizationMask[b];
/* 149 */     this.B &= quantizationMask[b];
/* 150 */     this.A &= quantizationMask[b];
/*     */ 
/*     */     
/* 153 */     this.rAbsolute = this.R;
/* 154 */     this.gAbsolute = this.G;
/* 155 */     this.bAbsolute = this.B;
/* 156 */     this.aAbsolute = this.A;
/*     */ 
/*     */     
/* 159 */     this.R -= paramCompressionStream.lastColor[0];
/* 160 */     this.G -= paramCompressionStream.lastColor[1];
/* 161 */     this.B -= paramCompressionStream.lastColor[2];
/* 162 */     this.A -= paramCompressionStream.lastColor[3];
/*     */ 
/*     */     
/* 165 */     paramCompressionStream.lastColor[0] = paramCompressionStream.lastColor[0] + this.R;
/* 166 */     paramCompressionStream.lastColor[1] = paramCompressionStream.lastColor[1] + this.G;
/* 167 */     paramCompressionStream.lastColor[2] = paramCompressionStream.lastColor[2] + this.B;
/* 168 */     paramCompressionStream.lastColor[3] = paramCompressionStream.lastColor[3] + this.A;
/*     */ 
/*     */     
/* 171 */     if (this.color3) {
/* 172 */       computeLengthShift(this.R, this.G, this.B);
/*     */     }
/* 174 */     else if (this.color4) {
/* 175 */       computeLengthShift(this.R, this.G, this.B, this.A);
/*     */     } 
/*     */     
/* 178 */     if (this.length == 0) {
/* 179 */       this.length = 1;
/*     */     }
/*     */     
/* 182 */     paramHuffmanTable.addColorEntry(this.length, this.shift, this.absolute);
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
/* 193 */   void outputCommand(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream) { outputColor(paramHuffmanTable, paramCommandStream, 128, 8); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   void outputSubcommand(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream) { outputColor(paramHuffmanTable, paramCommandStream, 0, 6); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void outputColor(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream, int paramInt1, int paramInt2) {
/* 216 */     HuffmanNode huffmanNode = paramHuffmanTable.getColorEntry(this.length, this.shift, this.absolute);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     int i = huffmanNode.dataLength - huffmanNode.shift;
/* 225 */     int j = huffmanNode.tagLength + 3 * i;
/*     */     
/* 227 */     this.R = this.R >> huffmanNode.shift & (int)lengthMask[i];
/* 228 */     this.G = this.G >> huffmanNode.shift & (int)lengthMask[i];
/* 229 */     this.B = this.B >> huffmanNode.shift & (int)lengthMask[i];
/*     */     
/* 231 */     long l = huffmanNode.tag << 3 * i | this.R << 2 * i | this.G << 1 * i | this.B << 0 * i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (j < 6) {
/*     */ 
/*     */       
/* 240 */       paramInt1 |= (int)(l << 6 - j);
/* 241 */       j = 0;
/*     */     }
/*     */     else {
/*     */       
/* 245 */       paramInt1 |= (int)(l >>> j - 6);
/* 246 */       j -= 6;
/*     */     } 
/*     */ 
/*     */     
/* 250 */     if (this.color4) {
/* 251 */       this.A = this.A >> huffmanNode.shift & (int)lengthMask[i];
/* 252 */       l = l << i | this.A;
/* 253 */       j += i;
/*     */     } 
/*     */ 
/*     */     
/* 257 */     paramCommandStream.addCommand(paramInt1, paramInt2, l, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 262 */     String str1 = this.absolute ? "" : "delta ";
/* 263 */     String str2 = this.colorR + " " + this.colorG + " " + this.colorB + (this.color4 ? (" " + this.colorA) : "");
/*     */ 
/*     */     
/* 266 */     return "color: " + str2 + "\n" + " fixed point " + str1 + this.R + " " + this.G + " " + this.B + "\n" + " length " + this.length + " shift " + this.shift + (this.absolute ? " absolute" : " relative");
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\compression\CompressionStreamColor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */