/*     */ package com.sun.j3d.utils.compression;
/*     */ 
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
/*     */ class CompressionStreamNormal
/*     */   extends CompressionStreamElement
/*     */ {
/*     */   private int u;
/*     */   private int v;
/*     */   private int specialOctant;
/*     */   private int specialSextant;
/*     */   private float normalX;
/*     */   private float normalY;
/*     */   private float normalZ;
/*     */   int octant;
/*     */   int sextant;
/*     */   boolean specialNormal;
/*     */   int uAbsolute;
/*     */   int vAbsolute;
/*     */   private static final int MAX_UV_BITS = 6;
/*     */   private static final int MAX_UV_ENTRIES = 64;
/*     */   
/*     */   CompressionStreamNormal(CompressionStream paramCompressionStream, Vector3f paramVector3f) {
/*  70 */     this.normalX = paramVector3f.x;
/*  71 */     this.normalY = paramVector3f.y;
/*  72 */     this.normalZ = paramVector3f.z;
/*  73 */     paramCompressionStream.byteCount += 12;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   private static final double[][][][] cgNormals = new double[7][65][65][3];
/*     */ 
/*     */   
/*     */   private static final double MAX_Y_ANG = 0.615479709D;
/*     */ 
/*     */   
/*     */   private static final double UNITY_14 = 16384.0D;
/*     */ 
/*     */   
/*     */   private static void computeNormals() {
/* 154 */     for (byte b = 0; b <= 6; b++) {
/* 155 */       byte b1 = true << b;
/*     */       
/* 157 */       for (byte b2 = 0; b2 <= b1; b2++) {
/* 158 */         for (byte b3 = 0; b3 <= b1; b3++) {
/* 159 */           if (b3 + b2 <= b1) {
/*     */             
/* 161 */             double d2 = 0.615479709D * b2 / b1;
/* 162 */             double d1 = Math.asin(Math.tan(0.615479709D * (b1 - b3) / b1));
/*     */             
/* 164 */             double d3 = Math.cos(d1) * Math.cos(d2);
/* 165 */             double d4 = Math.sin(d2);
/* 166 */             double d5 = Math.sin(d1) * Math.cos(d2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 173 */             d3 *= 16384.0D; int i = (int)d3;
/* 174 */             d3 = i; d3 /= 16384.0D;
/*     */             
/* 176 */             d4 *= 16384.0D; int j = (int)d4;
/* 177 */             d4 = j; d4 /= 16384.0D;
/*     */             
/* 179 */             d5 *= 16384.0D; int k = (int)d5;
/* 180 */             d5 = k; d5 /= 16384.0D;
/*     */             
/* 182 */             cgNormals[b][b2][b3][0] = d3;
/* 183 */             cgNormals[b][b2][b3][1] = d4;
/* 184 */             cgNormals[b][b2][b3][2] = d5;
/*     */           } 
/*     */         } 
/*     */       } 
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
/* 215 */   private static final short[][] inverseSine = new short[7][];
/*     */ 
/*     */   
/*     */   private static final short MAX_SIN_14BIT = 9459;
/*     */ 
/*     */ 
/*     */   
/*     */   private static void computeInverseSineTables() {
/* 223 */     short[] arrayOfShort = new short[65];
/*     */     
/*     */     byte b;
/*     */     
/* 227 */     for (b = 0; b <= 64; b++) {
/* 228 */       arrayOfShort[b] = (short)(int)(16384.0D * Math.sin(b * 0.615479709D / 64.0D));
/*     */     }
/*     */     
/* 231 */     for (b = 0; b <= 6; b++) {
/* 232 */       short s2; switch (b) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 239 */           s2 = 64;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 5:
/* 246 */           s2 = 128;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 253 */           s2 = 256;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 260 */           s2 = 512;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 267 */           s2 = 1024;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 274 */           s2 = 2048;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 0:
/* 281 */           s2 = 4096;
/*     */           break;
/*     */       } 
/*     */       
/* 285 */       inverseSine[b] = new short['⓳' / s2 + '\001'];
/*     */       
/* 287 */       short s1 = 0;
/* 288 */       for (byte b1 = 0; b1 < inverseSine[b].length; b1++) {
/*     */ 
/*     */         
/* 291 */         double d1 = s1 / 16384.0D;
/*     */ 
/*     */         
/* 294 */         double d2 = Math.asin(d1);
/* 295 */         int i = (int)(d2 / 0.615479709D * (1 << b));
/*     */ 
/*     */ 
/*     */         
/* 299 */         if (i > 0 && 
/* 300 */           Math.abs(arrayOfShort[i << 6 - b] - s1) > Math.abs(arrayOfShort[i - 1 << 6 - b] - s1))
/*     */         {
/* 302 */           i--;
/*     */         }
/*     */         
/* 305 */         if (i < 1 << b && 
/* 306 */           Math.abs(arrayOfShort[i << 6 - b] - s1) > Math.abs(arrayOfShort[i + 1 << 6 - b] - s1))
/*     */         {
/* 308 */           i++;
/*     */         }
/*     */         
/* 311 */         inverseSine[b][b1] = (short)i;
/* 312 */         s1 += s2;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/* 321 */     computeNormals();
/* 322 */     computeInverseSineTables();
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
/*     */   void quantize(CompressionStream paramCompressionStream, HuffmanTable paramHuffmanTable) {
/* 338 */     boolean bool1 = (paramCompressionStream.normalQuant < 0) ? 0 : ((paramCompressionStream.normalQuant > 6) ? 6 : paramCompressionStream.normalQuant);
/*     */ 
/*     */ 
/*     */     
/* 342 */     double d1 = this.normalX;
/* 343 */     double d2 = this.normalY;
/* 344 */     double d3 = this.normalZ;
/*     */     
/* 346 */     this.octant = 0;
/* 347 */     this.sextant = 0;
/* 348 */     this.u = 0;
/* 349 */     this.v = 0;
/*     */ 
/*     */     
/* 352 */     if (d1 < 0.0D) {
/* 353 */       this.octant |= 0x4;
/* 354 */       d1 = -d1;
/*     */     } 
/* 356 */     if (d2 < 0.0D) {
/* 357 */       this.octant |= 0x2;
/* 358 */       d2 = -d2;
/*     */     } 
/* 360 */     if (d3 < 0.0D) {
/* 361 */       this.octant |= 0x1;
/* 362 */       d3 = -d3;
/*     */     } 
/*     */ 
/*     */     
/* 366 */     if (d1 < d2) {
/* 367 */       this.sextant |= 0x1;
/* 368 */       double d = d1;
/* 369 */       d1 = d2;
/* 370 */       d2 = d;
/*     */     } 
/* 372 */     if (d3 < d2) {
/* 373 */       this.sextant |= 0x2;
/* 374 */       double d = d2;
/* 375 */       d2 = d3;
/* 376 */       d3 = d;
/*     */     } 
/* 378 */     if (d1 < d3) {
/* 379 */       this.sextant |= 0x4;
/* 380 */       double d = d1;
/* 381 */       d1 = d3;
/* 382 */       d3 = d;
/*     */     } 
/*     */ 
/*     */     
/* 386 */     int i = (int)(d2 * 16384.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 394 */     short s = inverseSine[bool1][i >> 12 - bool1];
/*     */ 
/*     */     
/* 397 */     int j = 0;
/* 398 */     int k = 0;
/* 399 */     boolean bool2 = true << bool1;
/* 400 */     double d4 = -1.0D;
/*     */     int m;
/* 402 */     for (m = s - 1; m < s + 1 && m <= bool2; m++) {
/* 403 */       if (m >= 0)
/*     */       {
/*     */         
/* 406 */         for (short s1 = 0; s1 <= bool2; s1++) {
/* 407 */           if (s1 + m <= bool2) {
/*     */ 
/*     */             
/* 410 */             double d = d1 * cgNormals[bool1][m][s1][0] + d2 * cgNormals[bool1][m][s1][1] + d3 * cgNormals[bool1][m][s1][2];
/*     */ 
/*     */ 
/*     */             
/* 414 */             if (d > d4) {
/* 415 */               d4 = d;
/* 416 */               j = s1;
/* 417 */               k = m;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 423 */     this.u = j << 6 - bool1;
/* 424 */     this.v = k << 6 - bool1;
/*     */ 
/*     */     
/* 427 */     this.specialNormal = false;
/* 428 */     if (this.u == 64 && this.v == 0) {
/*     */       
/* 430 */       if (this.sextant == 0 || this.sextant == 2) {
/*     */         
/* 432 */         this.specialSextant = 6;
/* 433 */         this.specialOctant = ((this.octant & 0x4) != 0) ? 2 : 0;
/*     */       }
/* 435 */       else if (this.sextant == 3 || this.sextant == 1) {
/*     */         
/* 437 */         this.specialSextant = 6;
/* 438 */         this.specialOctant = 0x4 | (((this.octant & 0x2) != 0) ? 2 : 0);
/*     */       }
/* 440 */       else if (this.sextant == 5 || this.sextant == 4) {
/*     */         
/* 442 */         this.specialSextant = 7;
/* 443 */         this.specialOctant = ((this.octant & true) != 0) ? 2 : 0;
/*     */       } 
/* 445 */       this.specialNormal = true;
/* 446 */       this.u = this.v = 0;
/*     */     }
/* 448 */     else if (this.u == 0 && this.v == 64) {
/*     */       
/* 450 */       this.specialSextant = 0x6 | this.octant >> 2;
/* 451 */       this.specialOctant = (this.octant & 0x3) << 1 | true;
/* 452 */       this.specialNormal = true;
/* 453 */       this.u = this.v = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 458 */     m = 0;
/* 459 */     int n = 0;
/* 460 */     int i1 = 64 >> 6 - bool1;
/*     */     
/* 462 */     this.absolute = false;
/* 463 */     if (paramCompressionStream.firstNormal || paramCompressionStream.normalQuantChanged || paramCompressionStream.lastSpecialNormal || this.specialNormal) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 468 */       this.absolute = true;
/* 469 */       paramCompressionStream.firstNormal = false;
/* 470 */       paramCompressionStream.normalQuantChanged = false;
/*     */     }
/* 472 */     else if (paramCompressionStream.lastOctant == this.octant && paramCompressionStream.lastSextant == this.sextant) {
/*     */ 
/*     */       
/* 475 */       int i2 = j - paramCompressionStream.lastU;
/* 476 */       n = k - paramCompressionStream.lastV;
/*     */     }
/* 478 */     else if (paramCompressionStream.lastOctant != this.octant && paramCompressionStream.lastSextant == this.sextant && (((this.sextant == 1 || this.sextant == 5) && (paramCompressionStream.lastOctant & 0x3) == (this.octant & 0x3)) || ((this.sextant == 0 || this.sextant == 4) && (paramCompressionStream.lastOctant & 0x5) == (this.octant & 0x5)) || ((this.sextant == 2 || this.sextant == 3) && (paramCompressionStream.lastOctant & 0x6) == (this.octant & 0x6)))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 489 */       int i2 = j - paramCompressionStream.lastU;
/* 490 */       n = -k - paramCompressionStream.lastV;
/*     */ 
/*     */       
/* 493 */       if (n < -i1) this.absolute = true;
/*     */ 
/*     */       
/* 496 */       if (k == 0) this.absolute = true;
/*     */     
/* 498 */     } else if (paramCompressionStream.lastOctant == this.octant && paramCompressionStream.lastSextant != this.sextant && ((this.sextant == 0 && paramCompressionStream.lastSextant == 4) || (this.sextant == 4 && paramCompressionStream.lastSextant == 0) || (this.sextant == 1 && paramCompressionStream.lastSextant == 5) || (this.sextant == 5 && paramCompressionStream.lastSextant == 1) || (this.sextant == 2 && paramCompressionStream.lastSextant == 3) || (this.sextant == 3 && paramCompressionStream.lastSextant == 2))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 508 */       int i2 = -j - paramCompressionStream.lastU;
/* 509 */       n = k - paramCompressionStream.lastV;
/*     */ 
/*     */       
/* 512 */       if (i2 < -i1) this.absolute = true;
/*     */ 
/*     */       
/* 515 */       if (j == 0) this.absolute = true;
/*     */     
/* 517 */     } else if (paramCompressionStream.lastOctant == this.octant && paramCompressionStream.lastSextant != this.sextant && ((this.sextant == 0 && paramCompressionStream.lastSextant == 2) || (this.sextant == 2 && paramCompressionStream.lastSextant == 0) || (this.sextant == 1 && paramCompressionStream.lastSextant == 3) || (this.sextant == 3 && paramCompressionStream.lastSextant == 1) || (this.sextant == 4 && paramCompressionStream.lastSextant == 5) || (this.sextant == 5 && paramCompressionStream.lastSextant == 4))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 527 */       if (j + k != i1 && j != 0 && k != 0) {
/* 528 */         m = i1 - j - paramCompressionStream.lastU;
/* 529 */         n = i1 - k - paramCompressionStream.lastV;
/*     */ 
/*     */         
/* 532 */         if (m >= i1 || n >= i1) {
/* 533 */           this.absolute = true;
/*     */         }
/*     */       } else {
/* 536 */         this.absolute = true;
/*     */       } 
/*     */     } else {
/*     */       
/* 540 */       this.absolute = true;
/*     */     } 
/* 542 */     if (!this.absolute) {
/*     */       
/* 544 */       this.u = m << 6 - bool1;
/* 545 */       this.v = n << 6 - bool1;
/*     */     } 
/*     */ 
/*     */     
/* 549 */     computeLengthShift(this.u, this.v);
/*     */     
/* 551 */     if (this.absolute && this.length > 6)
/*     */     {
/*     */       
/* 554 */       this.length = 6;
/*     */     }
/*     */ 
/*     */     
/* 558 */     paramHuffmanTable.addNormalEntry(this.length, this.shift, this.absolute);
/*     */ 
/*     */     
/* 561 */     paramCompressionStream.lastSextant = this.sextant;
/* 562 */     paramCompressionStream.lastOctant = this.octant;
/* 563 */     paramCompressionStream.lastU = j;
/* 564 */     paramCompressionStream.lastV = k;
/* 565 */     paramCompressionStream.lastSpecialNormal = this.specialNormal;
/*     */ 
/*     */     
/* 568 */     this.uAbsolute = j;
/* 569 */     this.vAbsolute = k;
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
/* 580 */   void outputCommand(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream) { outputNormal(paramHuffmanTable, paramCommandStream, 192, 8); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 591 */   void outputSubcommand(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream) { outputNormal(paramHuffmanTable, paramCommandStream, 0, 6); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void outputNormal(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream, int paramInt1, int paramInt2) {
/* 603 */     HuffmanNode huffmanNode = paramHuffmanTable.getNormalEntry(this.length, this.shift, this.absolute);
/*     */ 
/*     */     
/* 606 */     int i = huffmanNode.dataLength - huffmanNode.shift;
/* 607 */     int j = 0;
/* 608 */     long l = 0L;
/*     */     
/* 610 */     if (this.absolute) {
/*     */       
/* 612 */       j = huffmanNode.tagLength + 6;
/*     */       
/* 614 */       if (this.specialNormal) {
/*     */         
/* 616 */         l = (huffmanNode.tag << 6 | this.specialSextant << 3 | this.specialOctant);
/*     */       }
/*     */       else {
/*     */         
/* 620 */         l = (huffmanNode.tag << 6 | this.sextant << 3 | this.octant);
/*     */       } 
/*     */     } else {
/*     */       
/* 624 */       j = huffmanNode.tagLength;
/* 625 */       l = huffmanNode.tag;
/*     */     } 
/*     */ 
/*     */     
/* 629 */     j += 2 * i;
/*     */     
/* 631 */     this.u = this.u >> huffmanNode.shift & (int)lengthMask[i];
/* 632 */     this.v = this.v >> huffmanNode.shift & (int)lengthMask[i];
/*     */     
/* 634 */     l = l << 2 * i | (this.u << 1 * i) | (this.v << 0 * i);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 639 */     if (j < 6) {
/*     */ 
/*     */       
/* 642 */       paramInt1 |= (int)(l << 6 - j);
/* 643 */       j = 0;
/*     */     }
/*     */     else {
/*     */       
/* 647 */       paramInt1 |= (int)(l >>> j - 6);
/* 648 */       j -= 6;
/*     */     } 
/*     */ 
/*     */     
/* 652 */     paramCommandStream.addCommand(paramInt1, paramInt2, l, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     String str;
/* 659 */     if (this.specialNormal) {
/* 660 */       str = " special normal, sextant " + this.specialSextant + " octant " + this.specialOctant;
/*     */     
/*     */     }
/* 663 */     else if (this.absolute) {
/* 664 */       str = " sextant " + this.sextant + " octant " + this.octant + " u " + this.u + " v " + this.v;
/*     */     } else {
/*     */       
/* 667 */       str = " du " + this.u + " dv " + this.v;
/*     */     } 
/* 669 */     return "normal: " + this.normalX + " " + this.normalY + " " + this.normalZ + "\n" + str + "\n" + " length " + this.length + " shift " + this.shift + (this.absolute ? " absolute" : " relative");
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\compression\CompressionStreamNormal.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */