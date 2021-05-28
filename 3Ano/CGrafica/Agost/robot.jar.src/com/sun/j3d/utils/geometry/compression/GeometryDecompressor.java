/*      */ package com.sun.j3d.utils.geometry.compression;
/*      */ 
/*      */ import com.sun.j3d.internal.J3dUtilsI18N;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class GeometryDecompressor
/*      */ {
/*      */   static class HuffmanTableEntry
/*      */   {
/*      */     int tagLength;
/*      */     int dataLength;
/*      */     int rightShift;
/*      */     int absolute;
/*      */     
/*  143 */     public String toString() { return " tag length: " + this.tagLength + " data length: " + this.dataLength + " shift: " + this.rightShift + " abs/rel: " + this.absolute; }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  153 */   private int meshIndex = 15;
/*      */ 
/*      */ 
/*      */   
/*      */   static class MeshBufferEntry
/*      */   {
/*      */     short x;
/*      */ 
/*      */     
/*      */     short y;
/*      */ 
/*      */     
/*      */     short z;
/*      */ 
/*      */     
/*      */     short octant;
/*      */ 
/*      */     
/*      */     short sextant;
/*      */ 
/*      */     
/*      */     short u;
/*      */     
/*      */     short v;
/*      */     
/*      */     short r;
/*      */     
/*      */     short g;
/*      */     
/*      */     short b;
/*      */     
/*      */     short a;
/*      */   }
/*      */   
/*  187 */   private int currentHeader = 0;
/*  188 */   private int nextHeader = 0;
/*  189 */   private int bitBuffer = 0;
/*  190 */   private int bitBufferCount = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int[] BMASK = { 
/*  197 */       0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  225 */   private static final double[][][] gcNormals = new double[65][65][3];
/*      */   static  {
/*  227 */     for (byte b = 0; b < 65; b++) {
/*  228 */       for (byte b1 = 0; b1 < 65; b1++) {
/*  229 */         if (b + b1 <= 64) {
/*      */           
/*  231 */           double d2 = 0.615479709D * b / 64.0D;
/*  232 */           double d1 = Math.asin(Math.tan(0.615479709D * (64 - b1) / 64.0D));
/*      */           
/*  234 */           double d3 = Math.cos(d1) * Math.cos(d2);
/*  235 */           double d4 = Math.sin(d2);
/*  236 */           double d5 = Math.sin(d1) * Math.cos(d2);
/*      */ 
/*      */ 
/*      */           
/*  240 */           d3 *= 16384.0D; int i = (int)d3;
/*  241 */           d3 = i; d3 /= 16384.0D;
/*      */           
/*  243 */           d4 *= 16384.0D; int j = (int)d4;
/*  244 */           d4 = j; d4 /= 16384.0D;
/*      */           
/*  246 */           d5 *= 16384.0D; int k = (int)d5;
/*  247 */           d5 = k; d5 /= 16384.0D;
/*      */           
/*  249 */           gcNormals[b][b1][0] = d3;
/*  250 */           gcNormals[b][b1][1] = d4;
/*  251 */           gcNormals[b][b1][2] = d5;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  277 */   private Point3f curPos = new Point3f();
/*  278 */   private Vector3f curNorm = new Vector3f();
/*  279 */   private Color4f curColor = new Color4f(); private static final boolean debug = false; private static final boolean benchmark = false; static final int majorVersionNumber = 1; static final int minorVersionNumber = 0; static final int minorMinorVersionNumber = 2; private static final int GC_VERTEX = 64; private static final int GC_SET_NORM = 192; private static final int GC_SET_COLOR = 128; private static final int GC_MESH_B_R = 32; private static final int GC_SET_STATE = 24;
/*  280 */   private HuffmanTableEntry[][] gctables = new HuffmanTableEntry[3][64]; private static final int GC_SET_TABLE = 16; private static final int GC_PASS_THROUGH = 8; private static final int GC_EOS = 0; private static final int GC_V_NO_OP = 1; private static final int GC_SKIP_8 = 7; private MeshBufferEntry[] meshBuffer; private int meshState; private static final int USE_MESH_NORMAL = 1; private static final int USE_MESH_COLOR = 2; private short curX; GeometryDecompressor() {
/*      */     byte b;
/*  282 */     for (b = 0; b < 64; b++) {
/*  283 */       this.gctables[0][b] = new HuffmanTableEntry();
/*  284 */       this.gctables[1][b] = new HuffmanTableEntry();
/*  285 */       this.gctables[2][b] = new HuffmanTableEntry();
/*      */     } 
/*      */     
/*  288 */     this.meshBuffer = new MeshBufferEntry[16];
/*  289 */     for (b = 0; b < 16; b++)
/*  290 */       this.meshBuffer[b] = new MeshBufferEntry(); 
/*      */   }
/*      */   private short curY; private short curZ; private short curR; private short curG; private short curB; private short curA; private int curSex; private int curOct; private int curU; private int curV; private int repCode; private boolean bundlingNorm; private boolean bundlingColor; private boolean doingAlpha; private long startTime; private int vertexCount; private byte[] gcData;
/*      */   private int gcIndex;
/*      */   private static final double NORMAL_MAX_Y_ANG = 0.615479709D;
/*      */   private static final boolean printNormalTable = false;
/*      */   
/*  297 */   boolean checkVersion(int paramInt1, int paramInt2) { this; this; this; return (paramInt1 < 1 || (paramInt1 == 1 && paramInt2 <= 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void decompress(int paramInt1, int paramInt2, byte[] paramArrayOfByte) {
/*  320 */     if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
/*  321 */       throw new ArrayIndexOutOfBoundsException(J3dUtilsI18N.getString("GeometryDecompressor0"));
/*      */     }
/*      */ 
/*      */     
/*  325 */     this.gcData = paramArrayOfByte;
/*  326 */     this.gcIndex = paramInt1;
/*      */ 
/*      */     
/*  329 */     this.bitBufferCount = 0;
/*  330 */     this.meshState = 0;
/*  331 */     this.bundlingNorm = false;
/*  332 */     this.bundlingColor = false;
/*  333 */     this.doingAlpha = false;
/*  334 */     this.repCode = 0;
/*      */ 
/*      */ 
/*      */     
/*  338 */     this.nextHeader = 1;
/*      */ 
/*      */     
/*  341 */     while (this.gcIndex < paramInt1 + paramInt2) {
/*  342 */       processDecompression();
/*      */     }
/*      */     
/*  345 */     while (this.bitBufferCount > 0) {
/*  346 */       processDecompression();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getBits(int paramInt, String paramString) {
/*      */     int i;
/*  363 */     if (paramInt == 0)
/*      */     {
/*  365 */       return 0;
/*      */     }
/*      */     
/*  368 */     if (this.bitBufferCount == 0) {
/*  369 */       this.bitBuffer = (this.gcData[this.gcIndex++] & 0xFF) << 24 | (this.gcData[this.gcIndex++] & 0xFF) << 16 | (this.gcData[this.gcIndex++] & 0xFF) << 8 | this.gcData[this.gcIndex++] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  374 */       this.bitBufferCount = 32;
/*      */     } 
/*      */     
/*  377 */     if (this.bitBufferCount >= paramInt) {
/*  378 */       i = this.bitBuffer >>> 32 - paramInt & BMASK[paramInt];
/*  379 */       this.bitBuffer <<= paramInt;
/*  380 */       this.bitBufferCount -= paramInt;
/*      */     } else {
/*  382 */       i = this.bitBuffer >>> 32 - paramInt & BMASK[paramInt];
/*  383 */       i >>>= paramInt - this.bitBufferCount;
/*  384 */       i <<= paramInt - this.bitBufferCount;
/*      */       
/*  386 */       this.bitBuffer = (this.gcData[this.gcIndex++] & 0xFF) << 24 | (this.gcData[this.gcIndex++] & 0xFF) << 16 | (this.gcData[this.gcIndex++] & 0xFF) << 8 | this.gcData[this.gcIndex++] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  391 */       i |= this.bitBuffer >>> 32 - paramInt - this.bitBufferCount & BMASK[paramInt - this.bitBufferCount];
/*      */ 
/*      */ 
/*      */       
/*  395 */       this.bitBuffer <<= paramInt - this.bitBufferCount;
/*  396 */       this.bitBufferCount = 32 - paramInt - this.bitBufferCount;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  402 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processDecompression() {
/*  410 */     this.currentHeader = this.nextHeader;
/*      */     
/*  412 */     if ((this.currentHeader & 0xC0) == 64) {
/*      */       
/*  414 */       if (!this.bundlingNorm && !this.bundlingColor) {
/*      */         
/*  416 */         this.nextHeader = getBits(8, "header");
/*  417 */         int i = processDecompressionOpcode(0);
/*      */       }
/*  419 */       else if (this.bundlingNorm && !this.bundlingColor) {
/*      */         
/*  421 */         this.nextHeader = getBits(6, "normal");
/*  422 */         int i = processDecompressionOpcode(0);
/*  423 */         this.currentHeader = this.nextHeader | 0xC0;
/*      */ 
/*      */         
/*  426 */         this.nextHeader = getBits(8, "header");
/*  427 */         processDecompressionOpcode(i);
/*      */       }
/*  429 */       else if (!this.bundlingNorm && this.bundlingColor) {
/*      */         
/*  431 */         this.nextHeader = getBits(6, "color");
/*  432 */         int i = processDecompressionOpcode(0);
/*  433 */         this.currentHeader = this.nextHeader | 0x80;
/*      */ 
/*      */         
/*  436 */         this.nextHeader = getBits(8, "header");
/*  437 */         processDecompressionOpcode(i);
/*      */       }
/*      */       else {
/*      */         
/*  441 */         this.nextHeader = getBits(6, "normal");
/*  442 */         int i = processDecompressionOpcode(0);
/*  443 */         this.currentHeader = this.nextHeader | 0xC0;
/*      */ 
/*      */         
/*  446 */         this.nextHeader = getBits(6, "color");
/*  447 */         processDecompressionOpcode(i);
/*  448 */         this.currentHeader = this.nextHeader | 0x80;
/*      */ 
/*      */         
/*  451 */         this.nextHeader = getBits(8, "header");
/*  452 */         processDecompressionOpcode(i);
/*      */       } 
/*      */ 
/*      */       
/*  456 */       outputVertex(this.curPos, this.curNorm, this.curColor, this.repCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  462 */       this.meshState |= 0x1;
/*  463 */       this.meshState |= 0x2;
/*      */     }
/*      */     else {
/*      */       
/*  467 */       this.nextHeader = getBits(8, "header");
/*  468 */       processDecompressionOpcode(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int processDecompressionOpcode(int paramInt) {
/*  477 */     if ((this.currentHeader & 0xC0) == 192)
/*  478 */     { processSetNormal(paramInt); }
/*  479 */     else if ((this.currentHeader & 0xC0) == 128)
/*  480 */     { processSetColor(paramInt); }
/*  481 */     else { if ((this.currentHeader & 0xC0) == 64)
/*      */       {
/*      */         
/*  484 */         return processVertex(); } 
/*  485 */       if ((this.currentHeader & 0xE0) == 32) {
/*  486 */         processMeshBR();
/*      */ 
/*      */         
/*  489 */         outputVertex(this.curPos, this.curNorm, this.curColor, this.repCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  495 */         this.meshState |= 0x1;
/*  496 */         this.meshState |= 0x2;
/*      */       }
/*  498 */       else if ((this.currentHeader & 0xF8) == 24) {
/*  499 */         processSetState();
/*  500 */       } else if ((this.currentHeader & 0xF8) == 16) {
/*  501 */         processSetTable();
/*  502 */       } else if ((this.currentHeader & 0xFF) == 0) {
/*  503 */         processEos();
/*  504 */       } else if ((this.currentHeader & 0xFF) == 1) {
/*  505 */         processVNoop();
/*  506 */       } else if ((this.currentHeader & 0xFF) == 8) {
/*  507 */         processPassThrough();
/*  508 */       } else if ((this.currentHeader & 0xFF) == 7) {
/*  509 */         processSkip8();
/*      */       }  }
/*  511 */      return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processSetState() {
/*  522 */     int i = getBits(3, "bundling");
/*      */     
/*  524 */     this.bundlingNorm = ((this.currentHeader & true) != 0);
/*  525 */     this.bundlingColor = ((i >>> 2 & true) != 0);
/*  526 */     this.doingAlpha = ((i >>> 1 & true) != 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  534 */     outputVertexFormat(this.bundlingNorm, this.bundlingColor, this.doingAlpha);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processSetTable() {
/*  552 */     int i3 = (this.currentHeader & 0x6) >>> 1;
/*  553 */     HuffmanTableEntry[] arrayOfHuffmanTableEntry = this.gctables[i3];
/*      */ 
/*      */     
/*  556 */     int i2 = getBits(15, "set table");
/*      */ 
/*      */     
/*  559 */     int j = (this.currentHeader & true) << 6 | i2 >>> 9 & 0x3F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  564 */     int m = i2 >>> 5 & 0xF;
/*  565 */     if (m == 0 && i3 != 2) {
/*  566 */       m = 16;
/*      */     }
/*  568 */     int n = i2 & 0xF;
/*  569 */     int i1 = i2 >>> 4 & true;
/*      */ 
/*      */     
/*      */     int k;
/*      */ 
/*      */     
/*  575 */     for (k = 6; k > 0 && 
/*  576 */       j >> k == 0; k--);
/*      */ 
/*      */ 
/*      */     
/*  580 */     j = j << 6 - k & 0x3F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  591 */     for (int i = 0; i < 1 << 6 - k; i++) {
/*  592 */       (arrayOfHuffmanTableEntry[j + i]).tagLength = k;
/*  593 */       (arrayOfHuffmanTableEntry[j + i]).dataLength = m;
/*  594 */       (arrayOfHuffmanTableEntry[j + i]).rightShift = n;
/*  595 */       (arrayOfHuffmanTableEntry[j + i]).absolute = i1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int processVertex() {
/*      */     int i1, m, k;
/*  613 */     this.meshState = 0;
/*      */ 
/*      */     
/*  616 */     HuffmanTableEntry huffmanTableEntry = this.gctables[0][this.currentHeader & 0x3F];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  622 */     int n = huffmanTableEntry.dataLength - huffmanTableEntry.rightShift;
/*      */ 
/*      */ 
/*      */     
/*  626 */     if (6 - 3 * n - huffmanTableEntry.tagLength > 0) {
/*  627 */       int i2 = 6 - 3 * n - huffmanTableEntry.tagLength;
/*      */ 
/*      */       
/*  630 */       int i3 = this.currentHeader & BMASK[i2];
/*  631 */       i1 = getBits(3 - i2, "repcode/mbp");
/*  632 */       i1 |= i3 << 3 - i2;
/*      */     } else {
/*      */       
/*  635 */       i1 = getBits(3, "repcode/mbp");
/*      */     } 
/*  637 */     this.repCode = i1 >>> 1;
/*  638 */     int i = i1 & true;
/*      */ 
/*      */     
/*  641 */     int j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*      */     
/*  643 */     if (huffmanTableEntry.tagLength + n == 6) {
/*  644 */       k = getBits(n, "y");
/*  645 */       m = getBits(n, "z");
/*  646 */     } else if (huffmanTableEntry.tagLength + n < 6) {
/*  647 */       j >>= 6 - huffmanTableEntry.tagLength - n;
/*      */       
/*  649 */       k = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - n];
/*  650 */       if (huffmanTableEntry.tagLength + 2 * n == 6) {
/*  651 */         m = getBits(n, "z");
/*  652 */       } else if (huffmanTableEntry.tagLength + 2 * n < 6) {
/*  653 */         k >>= 6 - huffmanTableEntry.tagLength - 2 * n;
/*      */         
/*  655 */         m = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - 2 * n];
/*  656 */         if (huffmanTableEntry.tagLength + 3 * n < 6) {
/*  657 */           m >>= 6 - huffmanTableEntry.tagLength - 3 * n;
/*  658 */         } else if (huffmanTableEntry.tagLength + 3 * n > 6) {
/*  659 */           i1 = getBits(n - 6 - huffmanTableEntry.tagLength - 2 * n, "z");
/*      */           
/*  661 */           m = m << n - 6 - huffmanTableEntry.tagLength - 2 * n | i1;
/*      */         } 
/*      */       } else {
/*      */         
/*  665 */         i1 = getBits(n - 6 - huffmanTableEntry.tagLength - n, "y");
/*  666 */         k = k << n - 6 - huffmanTableEntry.tagLength - n | i1;
/*  667 */         m = getBits(n, "z");
/*      */       } 
/*      */     } else {
/*  670 */       i1 = getBits(n - 6 - huffmanTableEntry.tagLength, "x");
/*  671 */       j = j << n - 6 - huffmanTableEntry.tagLength | i1;
/*  672 */       k = getBits(n, "y");
/*  673 */       m = getBits(n, "z");
/*      */     } 
/*      */ 
/*      */     
/*  677 */     j <<= 32 - n; j >>= 32 - n;
/*  678 */     k <<= 32 - n; k >>= 32 - n;
/*  679 */     m <<= 32 - n; m >>= 32 - n;
/*      */ 
/*      */     
/*  682 */     short s1 = (short)(j << huffmanTableEntry.rightShift);
/*  683 */     short s2 = (short)(k << huffmanTableEntry.rightShift);
/*  684 */     short s3 = (short)(m << huffmanTableEntry.rightShift);
/*      */ 
/*      */     
/*  687 */     if (huffmanTableEntry.absolute != 0) {
/*  688 */       this.curX = s1; this.curY = s2; this.curZ = s3;
/*      */     }
/*      */     else {
/*      */       
/*  692 */       this.curX = (short)(this.curX + s1); this.curY = (short)(this.curY + s2); this.curZ = (short)(this.curZ + s3);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  698 */     if (i != 0) {
/*      */       
/*  700 */       this.meshIndex = this.meshIndex + 1 & 0xF;
/*  701 */       (this.meshBuffer[this.meshIndex]).x = this.curX;
/*  702 */       (this.meshBuffer[this.meshIndex]).y = this.curY;
/*  703 */       (this.meshBuffer[this.meshIndex]).z = this.curZ;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  710 */     float f1 = this.curX; f1 = (float)(f1 / 32768.0D);
/*  711 */     float f2 = this.curY; f2 = (float)(f2 / 32768.0D);
/*  712 */     float f3 = this.curZ; f3 = (float)(f3 / 32768.0D);
/*      */ 
/*      */ 
/*      */     
/*  716 */     this.curPos.set(f1, f2, f3);
/*  717 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processSetNormal(int paramInt) {
/*  730 */     this.meshState &= 0xFFFFFFFE;
/*      */ 
/*      */     
/*  733 */     HuffmanTableEntry huffmanTableEntry = this.gctables[2][this.currentHeader & 0x3F];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  740 */     int i = huffmanTableEntry.dataLength - huffmanTableEntry.rightShift;
/*      */     
/*  742 */     if (huffmanTableEntry.absolute != 0) {
/*      */ 
/*      */ 
/*      */       
/*  746 */       int j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*      */       
/*  748 */       if (huffmanTableEntry.tagLength != 0) {
/*      */         
/*  750 */         int k = getBits(6 - 6 - huffmanTableEntry.tagLength, "sex/oct");
/*  751 */         j = j << 6 - 6 - huffmanTableEntry.tagLength | k;
/*      */       } 
/*      */ 
/*      */       
/*  755 */       this.curU = getBits(i, "u");
/*  756 */       this.curV = getBits(i, "v");
/*      */ 
/*      */       
/*  759 */       this.curU <<= huffmanTableEntry.rightShift;
/*  760 */       this.curV <<= huffmanTableEntry.rightShift;
/*      */       
/*  762 */       this.curSex = j >> 3 & 0x7;
/*  763 */       this.curOct = j & 0x7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  778 */       int k, j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*      */       
/*  780 */       if (huffmanTableEntry.tagLength + i < 6) {
/*      */         
/*  782 */         j >>= 6 - huffmanTableEntry.tagLength - i;
/*  783 */         k = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - i];
/*      */         
/*  785 */         if (huffmanTableEntry.tagLength + 2 * i < 6) {
/*      */           
/*  787 */           k >>= 6 - huffmanTableEntry.tagLength - 2 * i;
/*  788 */         } else if (huffmanTableEntry.tagLength + 2 * i > 6) {
/*      */           
/*  790 */           int m = getBits(i - 6 - huffmanTableEntry.tagLength - i, "dv");
/*      */           
/*  792 */           k = k << i - 6 - huffmanTableEntry.tagLength - i | m;
/*      */         }
/*      */       
/*  795 */       } else if (huffmanTableEntry.tagLength + i > 6) {
/*      */         
/*  797 */         int m = getBits(i - 6 - huffmanTableEntry.tagLength, "du");
/*  798 */         j = j << i - 6 - huffmanTableEntry.tagLength | m;
/*      */         
/*  800 */         k = getBits(i, "dv");
/*      */       } else {
/*      */         
/*  803 */         k = getBits(i, "dv");
/*      */       } 
/*      */ 
/*      */       
/*  807 */       j <<= 32 - i; j >>= 32 - i;
/*  808 */       k <<= 32 - i; k >>= 32 - i;
/*      */ 
/*      */       
/*  811 */       j <<= huffmanTableEntry.rightShift;
/*  812 */       k <<= huffmanTableEntry.rightShift;
/*      */ 
/*      */       
/*  815 */       this.curU += j;
/*  816 */       this.curV += k;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  824 */       if (this.curU < 0 || this.curV < 0 || this.curU + this.curV > 64) {
/*  825 */         if (this.curU < 0 && this.curV >= 0) {
/*      */           
/*  827 */           this.curU = -this.curU;
/*  828 */           switch (this.curSex) { case 0:
/*  829 */               this.curSex = 4; break;
/*  830 */             case 1: this.curSex = 5; break;
/*  831 */             case 2: this.curSex = 3; break;
/*  832 */             case 3: this.curSex = 2; break;
/*  833 */             case 4: this.curSex = 0; break;
/*  834 */             case 5: this.curSex = 1; break; }
/*      */         
/*  836 */         } else if (this.curU >= 0 && this.curV < 0) {
/*      */           
/*  838 */           this.curV = -this.curV;
/*  839 */           switch (this.curSex) { case 1:
/*      */             case 5:
/*  841 */               this.curOct ^= 0x4; break;
/*      */             case 0:
/*      */             case 4:
/*  844 */               this.curOct ^= 0x2; break;
/*      */             case 2:
/*      */             case 3:
/*  847 */               this.curOct ^= 0x1;
/*      */               break; }
/*      */         
/*  850 */         } else if (this.curU + this.curV > 64) {
/*      */           
/*  852 */           this.curU = 64 - this.curU;
/*  853 */           this.curV = 64 - this.curV;
/*  854 */           switch (this.curSex) { case 0:
/*  855 */               this.curSex = 2; break;
/*  856 */             case 1: this.curSex = 3; break;
/*  857 */             case 2: this.curSex = 0; break;
/*  858 */             case 3: this.curSex = 1; break;
/*  859 */             case 4: this.curSex = 5; break;
/*  860 */             case 5: this.curSex = 4; break; }
/*      */         
/*      */         } else {
/*  863 */           throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryDecompressor1"));
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  869 */     if (paramInt != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  874 */       (this.meshBuffer[this.meshIndex]).sextant = (short)this.curSex;
/*  875 */       (this.meshBuffer[this.meshIndex]).octant = (short)this.curOct;
/*  876 */       (this.meshBuffer[this.meshIndex]).u = (short)this.curU;
/*  877 */       (this.meshBuffer[this.meshIndex]).v = (short)this.curV;
/*      */     } 
/*      */ 
/*      */     
/*  881 */     indexNormal(this.curSex, this.curOct, this.curU, this.curV, this.curNorm);
/*      */ 
/*      */ 
/*      */     
/*  885 */     if (!this.bundlingNorm) outputNormal(this.curNorm);
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void indexNormal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Vector3f paramVector3f) {
/*      */     float f3, f2, f1;
/*  897 */     if (paramInt1 > 5) {
/*      */       
/*  899 */       switch (paramInt2 & true) {
/*      */         case 0:
/*  901 */           switch ((paramInt1 & true) << 1 | (paramInt2 & 0x4) >> 2) { case 0:
/*  902 */               f1 = 1.0F; f2 = f3 = 0.0F; break;
/*  903 */             case 1: f2 = 1.0F; f1 = f3 = 0.0F; break;
/*      */             default:
/*  905 */               f3 = 1.0F; f1 = f2 = 0.0F; break; }
/*      */           
/*  907 */           paramInt1 = 0; paramInt2 = (paramInt2 & 0x2) >> 1;
/*  908 */           paramInt2 = paramInt2 << 2 | paramInt2 << 1 | paramInt2;
/*      */           break;
/*      */         
/*      */         default:
/*  912 */           paramInt2 = (paramInt1 & true) << 2 | paramInt2 >> 1;
/*  913 */           paramInt1 = 0;
/*  914 */           f1 = f2 = f3 = (float)(1.0D / Math.sqrt(3.0D));
/*      */           break;
/*      */       } 
/*  917 */       if ((paramInt2 & true) != 0) f3 = -f3; 
/*  918 */       if ((paramInt2 & 0x2) != 0) f2 = -f2; 
/*  919 */       if ((paramInt2 & 0x4) != 0) f1 = -f1;
/*      */     
/*      */     } else {
/*      */       
/*  923 */       f1 = (float)gcNormals[paramInt4][paramInt3][0];
/*  924 */       f2 = (float)gcNormals[paramInt4][paramInt3][1];
/*  925 */       f3 = (float)gcNormals[paramInt4][paramInt3][2];
/*      */ 
/*      */       
/*  928 */       if ((paramInt1 & 0x4) != 0) { float f = f1; f1 = f3; f3 = f; }
/*  929 */        if ((paramInt1 & 0x2) != 0) { float f = f2; f2 = f3; f3 = f; }
/*  930 */        if ((paramInt1 & true) != 0) { float f = f1; f1 = f2; f2 = f; }
/*      */ 
/*      */       
/*  933 */       if ((paramInt2 & true) != 0) f3 = -f3; 
/*  934 */       if ((paramInt2 & 0x2) != 0) f2 = -f2; 
/*  935 */       if ((paramInt2 & 0x4) != 0) f1 = -f1;
/*      */     
/*      */     } 
/*      */     
/*  939 */     paramVector3f.set(f1, f2, f3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processSetColor(int paramInt) {
/*      */     int k, j;
/*  956 */     this.meshState &= 0xFFFFFFFD;
/*      */ 
/*      */     
/*  959 */     HuffmanTableEntry huffmanTableEntry = this.gctables[1][this.currentHeader & 0x3F];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  966 */     int n = huffmanTableEntry.dataLength - huffmanTableEntry.rightShift;
/*      */ 
/*      */     
/*  969 */     int i = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*  970 */     int m = 0;
/*      */     
/*  972 */     if (huffmanTableEntry.tagLength + n == 6) {
/*  973 */       j = getBits(n, "g");
/*  974 */       k = getBits(n, "b");
/*  975 */       if (this.doingAlpha) {
/*  976 */         m = getBits(n, "a");
/*      */       }
/*  978 */     } else if (huffmanTableEntry.tagLength + n < 6) {
/*  979 */       i >>= 6 - huffmanTableEntry.tagLength - n;
/*      */       
/*  981 */       j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - n];
/*  982 */       if (huffmanTableEntry.tagLength + 2 * n == 6) {
/*  983 */         k = getBits(n, "b");
/*  984 */         if (this.doingAlpha) {
/*  985 */           m = getBits(n, "a");
/*      */         }
/*  987 */       } else if (huffmanTableEntry.tagLength + 2 * n < 6) {
/*  988 */         j >>= 6 - huffmanTableEntry.tagLength - 2 * n;
/*      */         
/*  990 */         k = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - 2 * n];
/*  991 */         if (huffmanTableEntry.tagLength + 3 * n == 6) {
/*  992 */           if (this.doingAlpha) {
/*  993 */             m = getBits(n, "a");
/*      */           }
/*  995 */         } else if (huffmanTableEntry.tagLength + 3 * n < 6) {
/*  996 */           k >>= 6 - huffmanTableEntry.tagLength - 3 * n;
/*      */           
/*  998 */           if (this.doingAlpha) {
/*  999 */             m = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - 4 * n];
/*      */             
/* 1001 */             if (huffmanTableEntry.tagLength + 4 * n < 6) {
/* 1002 */               m >>= 6 - huffmanTableEntry.tagLength - 3 * n;
/*      */             }
/* 1004 */             else if (huffmanTableEntry.tagLength + 4 * n > 6) {
/* 1005 */               int i1 = getBits(n - 6 - huffmanTableEntry.tagLength - 3 * n, "a");
/*      */               
/* 1007 */               m = m << n - 6 - huffmanTableEntry.tagLength - 3 * n | i1;
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/* 1012 */           int i1 = getBits(n - 6 - huffmanTableEntry.tagLength - 2 * n, "b");
/*      */           
/* 1014 */           k = k << n - 6 - huffmanTableEntry.tagLength - 2 * n | i1;
/*      */           
/* 1016 */           if (this.doingAlpha)
/* 1017 */             m = getBits(n, "a"); 
/*      */         } 
/*      */       } else {
/* 1020 */         int i1 = getBits(n - 6 - huffmanTableEntry.tagLength - n, "g");
/*      */         
/* 1022 */         j = j << n - 6 - huffmanTableEntry.tagLength - n | i1;
/*      */         
/* 1024 */         k = getBits(n, "b");
/* 1025 */         if (this.doingAlpha)
/* 1026 */           m = getBits(n, "a"); 
/*      */       } 
/*      */     } else {
/* 1029 */       int i1 = getBits(n - 6 - huffmanTableEntry.tagLength, "r");
/* 1030 */       i = i << n - 6 - huffmanTableEntry.tagLength | i1;
/* 1031 */       j = getBits(n, "g");
/* 1032 */       k = getBits(n, "b");
/* 1033 */       if (this.doingAlpha) {
/* 1034 */         m = getBits(n, "a");
/*      */       }
/*      */     } 
/*      */     
/* 1038 */     i <<= 32 - n; i >>= 32 - n;
/* 1039 */     j <<= 32 - n; j >>= 32 - n;
/* 1040 */     k <<= 32 - n; k >>= 32 - n;
/* 1041 */     m <<= 32 - n; m >>= 32 - n;
/*      */ 
/*      */     
/* 1044 */     short s1 = (short)(i << huffmanTableEntry.rightShift);
/* 1045 */     short s2 = (short)(j << huffmanTableEntry.rightShift);
/* 1046 */     short s3 = (short)(k << huffmanTableEntry.rightShift);
/* 1047 */     short s4 = (short)(m << huffmanTableEntry.rightShift);
/*      */ 
/*      */     
/* 1050 */     if (huffmanTableEntry.absolute != 0) {
/* 1051 */       this.curR = s1; this.curG = s2; this.curB = s3;
/* 1052 */       if (this.doingAlpha) this.curA = s4;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1057 */       this.curR = (short)(this.curR + s1); this.curG = (short)(this.curG + s2); this.curB = (short)(this.curB + s3);
/* 1058 */       if (this.doingAlpha) this.curA = (short)(this.curA + s4);
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1065 */     if (paramInt != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1070 */       (this.meshBuffer[this.meshIndex]).r = this.curR;
/* 1071 */       (this.meshBuffer[this.meshIndex]).g = this.curG;
/* 1072 */       (this.meshBuffer[this.meshIndex]).b = this.curB;
/* 1073 */       (this.meshBuffer[this.meshIndex]).a = this.curA;
/*      */     } 
/*      */ 
/*      */     
/* 1077 */     float f1 = this.curR; f1 = (float)(f1 / 32768.0D);
/* 1078 */     float f2 = this.curG; f2 = (float)(f2 / 32768.0D);
/* 1079 */     float f3 = this.curB; f3 = (float)(f3 / 32768.0D);
/* 1080 */     float f4 = this.curA; f4 = (float)(f4 / 32768.0D);
/*      */     
/* 1082 */     this.curColor.set(f1, f2, f3, f4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1088 */     if (!this.bundlingColor) outputColor(this.curColor);
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processMeshBR() {
/* 1103 */     int j = getBits(1, "mbr");
/*      */     
/* 1105 */     int i = this.currentHeader >>> 1 & 0xF;
/* 1106 */     this.repCode = (this.currentHeader & true) << 1 | j;
/*      */ 
/*      */     
/* 1109 */     i = this.meshIndex - i & 0xF;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1114 */     MeshBufferEntry meshBufferEntry = this.meshBuffer[i];
/* 1115 */     this.curX = meshBufferEntry.x;
/* 1116 */     this.curY = meshBufferEntry.y;
/* 1117 */     this.curZ = meshBufferEntry.z;
/*      */ 
/*      */     
/* 1120 */     this.curPos.set(this.curX / 32768.0F, this.curY / 32768.0F, this.curZ / 32768.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1129 */     if (this.bundlingNorm && (this.meshState & true) != 0) {
/* 1130 */       this.curSex = meshBufferEntry.sextant;
/* 1131 */       this.curOct = meshBufferEntry.octant;
/* 1132 */       this.curU = meshBufferEntry.u;
/* 1133 */       this.curV = meshBufferEntry.v;
/*      */ 
/*      */       
/* 1136 */       int k = this.curSex << 15 | this.curOct << 12 | this.curU << 6 | this.curV;
/*      */ 
/*      */       
/* 1139 */       indexNormal(this.curSex, this.curOct, this.curU, this.curV, this.curNorm);
/*      */     } 
/*      */ 
/*      */     
/* 1143 */     if (this.bundlingColor && (this.meshState & 0x2) != 0) {
/* 1144 */       this.curR = meshBufferEntry.r;
/* 1145 */       this.curG = meshBufferEntry.g;
/* 1146 */       this.curB = meshBufferEntry.b;
/*      */ 
/*      */       
/* 1149 */       this.curColor.x = this.curR; this.curColor.x = (float)(this.curColor.x / 32768.0D);
/* 1150 */       this.curColor.y = this.curG; this.curColor.y = (float)(this.curColor.y / 32768.0D);
/* 1151 */       this.curColor.z = this.curB; this.curColor.z = (float)(this.curColor.z / 32768.0D);
/*      */       
/* 1153 */       if (this.doingAlpha) {
/* 1154 */         this.curA = meshBufferEntry.a;
/* 1155 */         this.curColor.w = this.curA; this.curColor.w = (float)(this.curColor.w / 32768.0D);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1164 */     this.meshState = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processEos() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processVNoop() {
/* 1178 */     int j = getBits(5, "noop count");
/* 1179 */     int i = getBits(j, "noop bits");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processPassThrough() {
/* 1188 */     int i = getBits(24, "passthrough");
/* 1189 */     i = getBits(32, "passthrough");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1197 */   private void processSkip8() { int i = getBits(8, "skip8"); }
/*      */ 
/*      */   
/*      */   private void benchmarkStart(int paramInt) {
/* 1201 */     this.vertexCount = 0;
/* 1202 */     System.out.println(" GeometryDecompressor: decompressing " + paramInt + " bytes...");
/*      */     
/* 1204 */     this.startTime = System.currentTimeMillis();
/*      */   }
/*      */   
/*      */   private void benchmarkPrint(int paramInt) {
/* 1208 */     float f = (float)(System.currentTimeMillis() - this.startTime) / 1000.0F;
/* 1209 */     System.out.println("  done in " + f + " sec." + "\n" + "  decompressed " + this.vertexCount + " vertices at " + (this.vertexCount / f) + " vertices/sec\n");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1214 */     System.out.print("  vertex data present: coords");
/* 1215 */     int i = 12;
/* 1216 */     if (this.bundlingNorm) {
/* 1217 */       System.out.print(" normals");
/* 1218 */       i += 12;
/*      */     } 
/* 1220 */     if (this.bundlingColor) {
/* 1221 */       System.out.println(" colors");
/* 1222 */       i += 12;
/*      */     } 
/* 1224 */     if (this.doingAlpha) {
/* 1225 */       System.out.println(" alpha");
/* 1226 */       i += 4;
/*      */     } 
/* 1228 */     System.out.println();
/*      */     
/* 1230 */     System.out.println("  bytes of data in generalized strip output: " + (this.vertexCount * i) + "\n" + "  compression ratio: " + (paramInt / (this.vertexCount * i)) + "\n");
/*      */   }
/*      */   
/*      */   abstract void outputVertexFormat(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
/*      */   
/*      */   abstract void outputVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt);
/*      */   
/*      */   abstract void outputColor(Color4f paramColor4f);
/*      */   
/*      */   abstract void outputNormal(Vector3f paramVector3f);
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\compression\GeometryDecompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */