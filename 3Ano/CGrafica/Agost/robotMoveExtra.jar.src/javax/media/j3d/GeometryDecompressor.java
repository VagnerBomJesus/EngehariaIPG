/*      */ package javax.media.j3d;
/*      */ 
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
/*      */ abstract class GeometryDecompressor
/*      */ {
/*      */   static class HuffmanTableEntry
/*      */   {
/*      */     int tagLength;
/*      */     int dataLength;
/*      */     int rightShift;
/*      */     int absolute;
/*      */     
/*  107 */     public String toString() { return " tag length: " + this.tagLength + " data length: " + this.dataLength + " shift: " + this.rightShift + " abs/rel: " + this.absolute; }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   private int meshIndex = 15;
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
/*  151 */   private int currentHeader = 0;
/*  152 */   private int nextHeader = 0;
/*  153 */   private int bitBuffer = 0;
/*  154 */   private int bitBufferCount = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int[] BMASK = { 
/*  161 */       0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
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
/*  189 */   private static final double[][][] gcNormals = new double[65][65][3];
/*      */   static  {
/*  191 */     for (byte b = 0; b < 65; b++) {
/*  192 */       for (byte b1 = 0; b1 < 65; b1++) {
/*  193 */         if (b + b1 <= 64) {
/*      */           
/*  195 */           double d2 = 0.615479709D * b / 64.0D;
/*  196 */           double d1 = Math.asin(Math.tan(0.615479709D * (64 - b1) / 64.0D));
/*      */           
/*  198 */           double d3 = Math.cos(d1) * Math.cos(d2);
/*  199 */           double d4 = Math.sin(d2);
/*  200 */           double d5 = Math.sin(d1) * Math.cos(d2);
/*      */ 
/*      */ 
/*      */           
/*  204 */           d3 *= 16384.0D; int i = (int)d3;
/*  205 */           d3 = i; d3 /= 16384.0D;
/*      */           
/*  207 */           d4 *= 16384.0D; int j = (int)d4;
/*  208 */           d4 = j; d4 /= 16384.0D;
/*      */           
/*  210 */           d5 *= 16384.0D; int k = (int)d5;
/*  211 */           d5 = k; d5 /= 16384.0D;
/*      */           
/*  213 */           gcNormals[b][b1][0] = d3;
/*  214 */           gcNormals[b][b1][1] = d4;
/*  215 */           gcNormals[b][b1][2] = d5;
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
/*  241 */   private Point3f curPos = new Point3f();
/*  242 */   private Vector3f curNorm = new Vector3f();
/*  243 */   private Color4f curColor = new Color4f(); private static final boolean debug = false; private static final boolean benchmark = false; static final int majorVersionNumber = 1; static final int minorVersionNumber = 0; static final int minorMinorVersionNumber = 2; private static final int GC_VERTEX = 64; private static final int GC_SET_NORM = 192; private static final int GC_SET_COLOR = 128; private static final int GC_MESH_B_R = 32; private static final int GC_SET_STATE = 24;
/*  244 */   private HuffmanTableEntry[][] gctables = new HuffmanTableEntry[3][64]; private static final int GC_SET_TABLE = 16; private static final int GC_PASS_THROUGH = 8; private static final int GC_EOS = 0; private static final int GC_V_NO_OP = 1; private static final int GC_SKIP_8 = 7; private MeshBufferEntry[] meshBuffer; private int meshState; private static final int USE_MESH_NORMAL = 1; private static final int USE_MESH_COLOR = 2; private short curX; GeometryDecompressor() {
/*      */     byte b;
/*  246 */     for (b = 0; b < 64; b++) {
/*  247 */       this.gctables[0][b] = new HuffmanTableEntry();
/*  248 */       this.gctables[1][b] = new HuffmanTableEntry();
/*  249 */       this.gctables[2][b] = new HuffmanTableEntry();
/*      */     } 
/*      */     
/*  252 */     this.meshBuffer = new MeshBufferEntry[16];
/*  253 */     for (b = 0; b < 16; b++)
/*  254 */       this.meshBuffer[b] = new MeshBufferEntry(); 
/*      */   }
/*      */   private short curY; private short curZ; private short curR; private short curG; private short curB; private short curA; private int curSex; private int curOct; private int curU; private int curV; private int repCode; private boolean bundlingNorm; private boolean bundlingColor; private boolean doingAlpha; private long startTime; private int vertexCount; private byte[] gcData;
/*      */   private int gcIndex;
/*      */   private static final double NORMAL_MAX_Y_ANG = 0.615479709D;
/*      */   private static final boolean printNormalTable = false;
/*      */   
/*  261 */   boolean checkVersion(int paramInt1, int paramInt2) { this; this; this; return (paramInt1 < 1 || (paramInt1 == 1 && paramInt2 <= 0)); }
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
/*  284 */     if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
/*  285 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryDecompressor0"));
/*      */     }
/*      */ 
/*      */     
/*  289 */     this.gcData = paramArrayOfByte;
/*  290 */     this.gcIndex = paramInt1;
/*      */ 
/*      */     
/*  293 */     this.bitBufferCount = 0;
/*  294 */     this.meshState = 0;
/*  295 */     this.bundlingNorm = false;
/*  296 */     this.bundlingColor = false;
/*  297 */     this.doingAlpha = false;
/*  298 */     this.repCode = 0;
/*      */ 
/*      */ 
/*      */     
/*  302 */     this.nextHeader = 1;
/*      */ 
/*      */     
/*  305 */     while (this.gcIndex < paramInt1 + paramInt2) {
/*  306 */       processDecompression();
/*      */     }
/*      */     
/*  309 */     while (this.bitBufferCount > 0) {
/*  310 */       processDecompression();
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
/*  327 */     if (paramInt == 0)
/*      */     {
/*  329 */       return 0;
/*      */     }
/*      */     
/*  332 */     if (this.bitBufferCount == 0) {
/*  333 */       this.bitBuffer = (this.gcData[this.gcIndex++] & 0xFF) << 24 | (this.gcData[this.gcIndex++] & 0xFF) << 16 | (this.gcData[this.gcIndex++] & 0xFF) << 8 | this.gcData[this.gcIndex++] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  338 */       this.bitBufferCount = 32;
/*      */     } 
/*      */     
/*  341 */     if (this.bitBufferCount >= paramInt) {
/*  342 */       i = this.bitBuffer >>> 32 - paramInt & BMASK[paramInt];
/*  343 */       this.bitBuffer <<= paramInt;
/*  344 */       this.bitBufferCount -= paramInt;
/*      */     } else {
/*  346 */       i = this.bitBuffer >>> 32 - paramInt & BMASK[paramInt];
/*  347 */       i >>>= paramInt - this.bitBufferCount;
/*  348 */       i <<= paramInt - this.bitBufferCount;
/*      */       
/*  350 */       this.bitBuffer = (this.gcData[this.gcIndex++] & 0xFF) << 24 | (this.gcData[this.gcIndex++] & 0xFF) << 16 | (this.gcData[this.gcIndex++] & 0xFF) << 8 | this.gcData[this.gcIndex++] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  355 */       i |= this.bitBuffer >>> 32 - paramInt - this.bitBufferCount & BMASK[paramInt - this.bitBufferCount];
/*      */ 
/*      */ 
/*      */       
/*  359 */       this.bitBuffer <<= paramInt - this.bitBufferCount;
/*  360 */       this.bitBufferCount = 32 - paramInt - this.bitBufferCount;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  366 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processDecompression() {
/*  374 */     this.currentHeader = this.nextHeader;
/*      */     
/*  376 */     if ((this.currentHeader & 0xC0) == 64) {
/*      */       
/*  378 */       if (!this.bundlingNorm && !this.bundlingColor) {
/*      */         
/*  380 */         this.nextHeader = getBits(8, "header");
/*  381 */         int i = processDecompressionOpcode(0);
/*      */       }
/*  383 */       else if (this.bundlingNorm && !this.bundlingColor) {
/*      */         
/*  385 */         this.nextHeader = getBits(6, "normal");
/*  386 */         int i = processDecompressionOpcode(0);
/*  387 */         this.currentHeader = this.nextHeader | 0xC0;
/*      */ 
/*      */         
/*  390 */         this.nextHeader = getBits(8, "header");
/*  391 */         processDecompressionOpcode(i);
/*      */       }
/*  393 */       else if (!this.bundlingNorm && this.bundlingColor) {
/*      */         
/*  395 */         this.nextHeader = getBits(6, "color");
/*  396 */         int i = processDecompressionOpcode(0);
/*  397 */         this.currentHeader = this.nextHeader | 0x80;
/*      */ 
/*      */         
/*  400 */         this.nextHeader = getBits(8, "header");
/*  401 */         processDecompressionOpcode(i);
/*      */       }
/*      */       else {
/*      */         
/*  405 */         this.nextHeader = getBits(6, "normal");
/*  406 */         int i = processDecompressionOpcode(0);
/*  407 */         this.currentHeader = this.nextHeader | 0xC0;
/*      */ 
/*      */         
/*  410 */         this.nextHeader = getBits(6, "color");
/*  411 */         processDecompressionOpcode(i);
/*  412 */         this.currentHeader = this.nextHeader | 0x80;
/*      */ 
/*      */         
/*  415 */         this.nextHeader = getBits(8, "header");
/*  416 */         processDecompressionOpcode(i);
/*      */       } 
/*      */ 
/*      */       
/*  420 */       outputVertex(this.curPos, this.curNorm, this.curColor, this.repCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  426 */       this.meshState |= 0x1;
/*  427 */       this.meshState |= 0x2;
/*      */     }
/*      */     else {
/*      */       
/*  431 */       this.nextHeader = getBits(8, "header");
/*  432 */       processDecompressionOpcode(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int processDecompressionOpcode(int paramInt) {
/*  441 */     if ((this.currentHeader & 0xC0) == 192)
/*  442 */     { processSetNormal(paramInt); }
/*  443 */     else if ((this.currentHeader & 0xC0) == 128)
/*  444 */     { processSetColor(paramInt); }
/*  445 */     else { if ((this.currentHeader & 0xC0) == 64)
/*      */       {
/*      */         
/*  448 */         return processVertex(); } 
/*  449 */       if ((this.currentHeader & 0xE0) == 32) {
/*  450 */         processMeshBR();
/*      */ 
/*      */         
/*  453 */         outputVertex(this.curPos, this.curNorm, this.curColor, this.repCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  459 */         this.meshState |= 0x1;
/*  460 */         this.meshState |= 0x2;
/*      */       }
/*  462 */       else if ((this.currentHeader & 0xF8) == 24) {
/*  463 */         processSetState();
/*  464 */       } else if ((this.currentHeader & 0xF8) == 16) {
/*  465 */         processSetTable();
/*  466 */       } else if ((this.currentHeader & 0xFF) == 0) {
/*  467 */         processEos();
/*  468 */       } else if ((this.currentHeader & 0xFF) == 1) {
/*  469 */         processVNoop();
/*  470 */       } else if ((this.currentHeader & 0xFF) == 8) {
/*  471 */         processPassThrough();
/*  472 */       } else if ((this.currentHeader & 0xFF) == 7) {
/*  473 */         processSkip8();
/*      */       }  }
/*  475 */      return 0;
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
/*  486 */     int i = getBits(3, "bundling");
/*      */     
/*  488 */     this.bundlingNorm = ((this.currentHeader & true) != 0);
/*  489 */     this.bundlingColor = ((i >>> 2 & true) != 0);
/*  490 */     this.doingAlpha = ((i >>> 1 & true) != 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  498 */     outputVertexFormat(this.bundlingNorm, this.bundlingColor, this.doingAlpha);
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
/*  516 */     int i3 = (this.currentHeader & 0x6) >>> 1;
/*  517 */     HuffmanTableEntry[] arrayOfHuffmanTableEntry = this.gctables[i3];
/*      */ 
/*      */     
/*  520 */     int i2 = getBits(15, "set table");
/*      */ 
/*      */     
/*  523 */     int j = (this.currentHeader & true) << 6 | i2 >>> 9 & 0x3F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  528 */     int m = i2 >>> 5 & 0xF;
/*  529 */     if (m == 0 && i3 != 2) {
/*  530 */       m = 16;
/*      */     }
/*  532 */     int n = i2 & 0xF;
/*  533 */     int i1 = i2 >>> 4 & true;
/*      */ 
/*      */     
/*      */     int k;
/*      */ 
/*      */     
/*  539 */     for (k = 6; k > 0 && 
/*  540 */       j >> k == 0; k--);
/*      */ 
/*      */ 
/*      */     
/*  544 */     j = j << 6 - k & 0x3F;
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
/*  555 */     for (int i = 0; i < 1 << 6 - k; i++) {
/*  556 */       (arrayOfHuffmanTableEntry[j + i]).tagLength = k;
/*  557 */       (arrayOfHuffmanTableEntry[j + i]).dataLength = m;
/*  558 */       (arrayOfHuffmanTableEntry[j + i]).rightShift = n;
/*  559 */       (arrayOfHuffmanTableEntry[j + i]).absolute = i1;
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
/*  577 */     this.meshState = 0;
/*      */ 
/*      */     
/*  580 */     HuffmanTableEntry huffmanTableEntry = this.gctables[0][this.currentHeader & 0x3F];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  586 */     int n = huffmanTableEntry.dataLength - huffmanTableEntry.rightShift;
/*      */ 
/*      */ 
/*      */     
/*  590 */     if (6 - 3 * n - huffmanTableEntry.tagLength > 0) {
/*  591 */       int i2 = 6 - 3 * n - huffmanTableEntry.tagLength;
/*      */ 
/*      */       
/*  594 */       int i3 = this.currentHeader & BMASK[i2];
/*  595 */       i1 = getBits(3 - i2, "repcode/mbp");
/*  596 */       i1 |= i3 << 3 - i2;
/*      */     } else {
/*      */       
/*  599 */       i1 = getBits(3, "repcode/mbp");
/*      */     } 
/*  601 */     this.repCode = i1 >>> 1;
/*  602 */     int i = i1 & true;
/*      */ 
/*      */     
/*  605 */     int j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*      */     
/*  607 */     if (huffmanTableEntry.tagLength + n == 6) {
/*  608 */       k = getBits(n, "y");
/*  609 */       m = getBits(n, "z");
/*  610 */     } else if (huffmanTableEntry.tagLength + n < 6) {
/*  611 */       j >>= 6 - huffmanTableEntry.tagLength - n;
/*      */       
/*  613 */       k = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - n];
/*  614 */       if (huffmanTableEntry.tagLength + 2 * n == 6) {
/*  615 */         m = getBits(n, "z");
/*  616 */       } else if (huffmanTableEntry.tagLength + 2 * n < 6) {
/*  617 */         k >>= 6 - huffmanTableEntry.tagLength - 2 * n;
/*      */         
/*  619 */         m = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - 2 * n];
/*  620 */         if (huffmanTableEntry.tagLength + 3 * n < 6) {
/*  621 */           m >>= 6 - huffmanTableEntry.tagLength - 3 * n;
/*  622 */         } else if (huffmanTableEntry.tagLength + 3 * n > 6) {
/*  623 */           i1 = getBits(n - 6 - huffmanTableEntry.tagLength - 2 * n, "z");
/*      */           
/*  625 */           m = m << n - 6 - huffmanTableEntry.tagLength - 2 * n | i1;
/*      */         } 
/*      */       } else {
/*      */         
/*  629 */         i1 = getBits(n - 6 - huffmanTableEntry.tagLength - n, "y");
/*  630 */         k = k << n - 6 - huffmanTableEntry.tagLength - n | i1;
/*  631 */         m = getBits(n, "z");
/*      */       } 
/*      */     } else {
/*  634 */       i1 = getBits(n - 6 - huffmanTableEntry.tagLength, "x");
/*  635 */       j = j << n - 6 - huffmanTableEntry.tagLength | i1;
/*  636 */       k = getBits(n, "y");
/*  637 */       m = getBits(n, "z");
/*      */     } 
/*      */ 
/*      */     
/*  641 */     j <<= 32 - n; j >>= 32 - n;
/*  642 */     k <<= 32 - n; k >>= 32 - n;
/*  643 */     m <<= 32 - n; m >>= 32 - n;
/*      */ 
/*      */     
/*  646 */     short s1 = (short)(j << huffmanTableEntry.rightShift);
/*  647 */     short s2 = (short)(k << huffmanTableEntry.rightShift);
/*  648 */     short s3 = (short)(m << huffmanTableEntry.rightShift);
/*      */ 
/*      */     
/*  651 */     if (huffmanTableEntry.absolute != 0) {
/*  652 */       this.curX = s1; this.curY = s2; this.curZ = s3;
/*      */     }
/*      */     else {
/*      */       
/*  656 */       this.curX = (short)(this.curX + s1); this.curY = (short)(this.curY + s2); this.curZ = (short)(this.curZ + s3);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  662 */     if (i != 0) {
/*      */       
/*  664 */       this.meshIndex = this.meshIndex + 1 & 0xF;
/*  665 */       (this.meshBuffer[this.meshIndex]).x = this.curX;
/*  666 */       (this.meshBuffer[this.meshIndex]).y = this.curY;
/*  667 */       (this.meshBuffer[this.meshIndex]).z = this.curZ;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  674 */     float f1 = this.curX; f1 = (float)(f1 / 32768.0D);
/*  675 */     float f2 = this.curY; f2 = (float)(f2 / 32768.0D);
/*  676 */     float f3 = this.curZ; f3 = (float)(f3 / 32768.0D);
/*      */ 
/*      */ 
/*      */     
/*  680 */     this.curPos.set(f1, f2, f3);
/*  681 */     return i;
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
/*  694 */     this.meshState &= 0xFFFFFFFE;
/*      */ 
/*      */     
/*  697 */     HuffmanTableEntry huffmanTableEntry = this.gctables[2][this.currentHeader & 0x3F];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  704 */     int i = huffmanTableEntry.dataLength - huffmanTableEntry.rightShift;
/*      */     
/*  706 */     if (huffmanTableEntry.absolute != 0) {
/*      */ 
/*      */ 
/*      */       
/*  710 */       int j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*      */       
/*  712 */       if (huffmanTableEntry.tagLength != 0) {
/*      */         
/*  714 */         int k = getBits(6 - 6 - huffmanTableEntry.tagLength, "sex/oct");
/*  715 */         j = j << 6 - 6 - huffmanTableEntry.tagLength | k;
/*      */       } 
/*      */ 
/*      */       
/*  719 */       this.curU = getBits(i, "u");
/*  720 */       this.curV = getBits(i, "v");
/*      */ 
/*      */       
/*  723 */       this.curU <<= huffmanTableEntry.rightShift;
/*  724 */       this.curV <<= huffmanTableEntry.rightShift;
/*      */       
/*  726 */       this.curSex = j >> 3 & 0x7;
/*  727 */       this.curOct = j & 0x7;
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
/*  742 */       int k, j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*      */       
/*  744 */       if (huffmanTableEntry.tagLength + i < 6) {
/*      */         
/*  746 */         j >>= 6 - huffmanTableEntry.tagLength - i;
/*  747 */         k = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - i];
/*      */         
/*  749 */         if (huffmanTableEntry.tagLength + 2 * i < 6) {
/*      */           
/*  751 */           k >>= 6 - huffmanTableEntry.tagLength - 2 * i;
/*  752 */         } else if (huffmanTableEntry.tagLength + 2 * i > 6) {
/*      */           
/*  754 */           int m = getBits(i - 6 - huffmanTableEntry.tagLength - i, "dv");
/*      */           
/*  756 */           k = k << i - 6 - huffmanTableEntry.tagLength - i | m;
/*      */         }
/*      */       
/*  759 */       } else if (huffmanTableEntry.tagLength + i > 6) {
/*      */         
/*  761 */         int m = getBits(i - 6 - huffmanTableEntry.tagLength, "du");
/*  762 */         j = j << i - 6 - huffmanTableEntry.tagLength | m;
/*      */         
/*  764 */         k = getBits(i, "dv");
/*      */       } else {
/*      */         
/*  767 */         k = getBits(i, "dv");
/*      */       } 
/*      */ 
/*      */       
/*  771 */       j <<= 32 - i; j >>= 32 - i;
/*  772 */       k <<= 32 - i; k >>= 32 - i;
/*      */ 
/*      */       
/*  775 */       j <<= huffmanTableEntry.rightShift;
/*  776 */       k <<= huffmanTableEntry.rightShift;
/*      */ 
/*      */       
/*  779 */       this.curU += j;
/*  780 */       this.curV += k;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  788 */       if (this.curU < 0 || this.curV < 0 || this.curU + this.curV > 64) {
/*  789 */         if (this.curU < 0 && this.curV >= 0) {
/*      */           
/*  791 */           this.curU = -this.curU;
/*  792 */           switch (this.curSex) { case 0:
/*  793 */               this.curSex = 4; break;
/*  794 */             case 1: this.curSex = 5; break;
/*  795 */             case 2: this.curSex = 3; break;
/*  796 */             case 3: this.curSex = 2; break;
/*  797 */             case 4: this.curSex = 0; break;
/*  798 */             case 5: this.curSex = 1; break; }
/*      */         
/*  800 */         } else if (this.curU >= 0 && this.curV < 0) {
/*      */           
/*  802 */           this.curV = -this.curV;
/*  803 */           switch (this.curSex) { case 1:
/*      */             case 5:
/*  805 */               this.curOct ^= 0x4; break;
/*      */             case 0:
/*      */             case 4:
/*  808 */               this.curOct ^= 0x2; break;
/*      */             case 2:
/*      */             case 3:
/*  811 */               this.curOct ^= 0x1;
/*      */               break; }
/*      */         
/*  814 */         } else if (this.curU + this.curV > 64) {
/*      */           
/*  816 */           this.curU = 64 - this.curU;
/*  817 */           this.curV = 64 - this.curV;
/*  818 */           switch (this.curSex) { case 0:
/*  819 */               this.curSex = 2; break;
/*  820 */             case 1: this.curSex = 3; break;
/*  821 */             case 2: this.curSex = 0; break;
/*  822 */             case 3: this.curSex = 1; break;
/*  823 */             case 4: this.curSex = 5; break;
/*  824 */             case 5: this.curSex = 4; break; }
/*      */         
/*      */         } else {
/*  827 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryDecompressor1"));
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  833 */     if (paramInt != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  838 */       (this.meshBuffer[this.meshIndex]).sextant = (short)this.curSex;
/*  839 */       (this.meshBuffer[this.meshIndex]).octant = (short)this.curOct;
/*  840 */       (this.meshBuffer[this.meshIndex]).u = (short)this.curU;
/*  841 */       (this.meshBuffer[this.meshIndex]).v = (short)this.curV;
/*      */     } 
/*      */ 
/*      */     
/*  845 */     indexNormal(this.curSex, this.curOct, this.curU, this.curV, this.curNorm);
/*      */ 
/*      */ 
/*      */     
/*  849 */     if (!this.bundlingNorm) outputNormal(this.curNorm);
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
/*  861 */     if (paramInt1 > 5) {
/*      */       
/*  863 */       switch (paramInt2 & true) {
/*      */         case 0:
/*  865 */           switch ((paramInt1 & true) << 1 | (paramInt2 & 0x4) >> 2) { case 0:
/*  866 */               f1 = 1.0F; f2 = f3 = 0.0F; break;
/*  867 */             case 1: f2 = 1.0F; f1 = f3 = 0.0F; break;
/*      */             default:
/*  869 */               f3 = 1.0F; f1 = f2 = 0.0F; break; }
/*      */           
/*  871 */           paramInt1 = 0; paramInt2 = (paramInt2 & 0x2) >> 1;
/*  872 */           paramInt2 = paramInt2 << 2 | paramInt2 << 1 | paramInt2;
/*      */           break;
/*      */         
/*      */         default:
/*  876 */           paramInt2 = (paramInt1 & true) << 2 | paramInt2 >> 1;
/*  877 */           paramInt1 = 0;
/*  878 */           f1 = f2 = f3 = (float)(1.0D / Math.sqrt(3.0D));
/*      */           break;
/*      */       } 
/*  881 */       if ((paramInt2 & true) != 0) f3 = -f3; 
/*  882 */       if ((paramInt2 & 0x2) != 0) f2 = -f2; 
/*  883 */       if ((paramInt2 & 0x4) != 0) f1 = -f1;
/*      */     
/*      */     } else {
/*      */       
/*  887 */       f1 = (float)gcNormals[paramInt4][paramInt3][0];
/*  888 */       f2 = (float)gcNormals[paramInt4][paramInt3][1];
/*  889 */       f3 = (float)gcNormals[paramInt4][paramInt3][2];
/*      */ 
/*      */       
/*  892 */       if ((paramInt1 & 0x4) != 0) { float f = f1; f1 = f3; f3 = f; }
/*  893 */        if ((paramInt1 & 0x2) != 0) { float f = f2; f2 = f3; f3 = f; }
/*  894 */        if ((paramInt1 & true) != 0) { float f = f1; f1 = f2; f2 = f; }
/*      */ 
/*      */       
/*  897 */       if ((paramInt2 & true) != 0) f3 = -f3; 
/*  898 */       if ((paramInt2 & 0x2) != 0) f2 = -f2; 
/*  899 */       if ((paramInt2 & 0x4) != 0) f1 = -f1;
/*      */     
/*      */     } 
/*      */     
/*  903 */     paramVector3f.set(f1, f2, f3);
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
/*  920 */     this.meshState &= 0xFFFFFFFD;
/*      */ 
/*      */     
/*  923 */     HuffmanTableEntry huffmanTableEntry = this.gctables[1][this.currentHeader & 0x3F];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  930 */     int n = huffmanTableEntry.dataLength - huffmanTableEntry.rightShift;
/*      */ 
/*      */     
/*  933 */     int i = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength];
/*  934 */     int m = 0;
/*      */     
/*  936 */     if (huffmanTableEntry.tagLength + n == 6) {
/*  937 */       j = getBits(n, "g");
/*  938 */       k = getBits(n, "b");
/*  939 */       if (this.doingAlpha) {
/*  940 */         m = getBits(n, "a");
/*      */       }
/*  942 */     } else if (huffmanTableEntry.tagLength + n < 6) {
/*  943 */       i >>= 6 - huffmanTableEntry.tagLength - n;
/*      */       
/*  945 */       j = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - n];
/*  946 */       if (huffmanTableEntry.tagLength + 2 * n == 6) {
/*  947 */         k = getBits(n, "b");
/*  948 */         if (this.doingAlpha) {
/*  949 */           m = getBits(n, "a");
/*      */         }
/*  951 */       } else if (huffmanTableEntry.tagLength + 2 * n < 6) {
/*  952 */         j >>= 6 - huffmanTableEntry.tagLength - 2 * n;
/*      */         
/*  954 */         k = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - 2 * n];
/*  955 */         if (huffmanTableEntry.tagLength + 3 * n == 6) {
/*  956 */           if (this.doingAlpha) {
/*  957 */             m = getBits(n, "a");
/*      */           }
/*  959 */         } else if (huffmanTableEntry.tagLength + 3 * n < 6) {
/*  960 */           k >>= 6 - huffmanTableEntry.tagLength - 3 * n;
/*      */           
/*  962 */           if (this.doingAlpha) {
/*  963 */             m = this.currentHeader & BMASK[6 - huffmanTableEntry.tagLength - 4 * n];
/*      */             
/*  965 */             if (huffmanTableEntry.tagLength + 4 * n < 6) {
/*  966 */               m >>= 6 - huffmanTableEntry.tagLength - 3 * n;
/*      */             }
/*  968 */             else if (huffmanTableEntry.tagLength + 4 * n > 6) {
/*  969 */               int i1 = getBits(n - 6 - huffmanTableEntry.tagLength - 3 * n, "a");
/*      */               
/*  971 */               m = m << n - 6 - huffmanTableEntry.tagLength - 3 * n | i1;
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/*  976 */           int i1 = getBits(n - 6 - huffmanTableEntry.tagLength - 2 * n, "b");
/*      */           
/*  978 */           k = k << n - 6 - huffmanTableEntry.tagLength - 2 * n | i1;
/*      */           
/*  980 */           if (this.doingAlpha)
/*  981 */             m = getBits(n, "a"); 
/*      */         } 
/*      */       } else {
/*  984 */         int i1 = getBits(n - 6 - huffmanTableEntry.tagLength - n, "g");
/*      */         
/*  986 */         j = j << n - 6 - huffmanTableEntry.tagLength - n | i1;
/*      */         
/*  988 */         k = getBits(n, "b");
/*  989 */         if (this.doingAlpha)
/*  990 */           m = getBits(n, "a"); 
/*      */       } 
/*      */     } else {
/*  993 */       int i1 = getBits(n - 6 - huffmanTableEntry.tagLength, "r");
/*  994 */       i = i << n - 6 - huffmanTableEntry.tagLength | i1;
/*  995 */       j = getBits(n, "g");
/*  996 */       k = getBits(n, "b");
/*  997 */       if (this.doingAlpha) {
/*  998 */         m = getBits(n, "a");
/*      */       }
/*      */     } 
/*      */     
/* 1002 */     i <<= 32 - n; i >>= 32 - n;
/* 1003 */     j <<= 32 - n; j >>= 32 - n;
/* 1004 */     k <<= 32 - n; k >>= 32 - n;
/* 1005 */     m <<= 32 - n; m >>= 32 - n;
/*      */ 
/*      */     
/* 1008 */     short s1 = (short)(i << huffmanTableEntry.rightShift);
/* 1009 */     short s2 = (short)(j << huffmanTableEntry.rightShift);
/* 1010 */     short s3 = (short)(k << huffmanTableEntry.rightShift);
/* 1011 */     short s4 = (short)(m << huffmanTableEntry.rightShift);
/*      */ 
/*      */     
/* 1014 */     if (huffmanTableEntry.absolute != 0) {
/* 1015 */       this.curR = s1; this.curG = s2; this.curB = s3;
/* 1016 */       if (this.doingAlpha) this.curA = s4;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1021 */       this.curR = (short)(this.curR + s1); this.curG = (short)(this.curG + s2); this.curB = (short)(this.curB + s3);
/* 1022 */       if (this.doingAlpha) this.curA = (short)(this.curA + s4);
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1029 */     if (paramInt != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1034 */       (this.meshBuffer[this.meshIndex]).r = this.curR;
/* 1035 */       (this.meshBuffer[this.meshIndex]).g = this.curG;
/* 1036 */       (this.meshBuffer[this.meshIndex]).b = this.curB;
/* 1037 */       (this.meshBuffer[this.meshIndex]).a = this.curA;
/*      */     } 
/*      */ 
/*      */     
/* 1041 */     float f1 = this.curR; f1 = (float)(f1 / 32768.0D);
/* 1042 */     float f2 = this.curG; f2 = (float)(f2 / 32768.0D);
/* 1043 */     float f3 = this.curB; f3 = (float)(f3 / 32768.0D);
/* 1044 */     float f4 = this.curA; f4 = (float)(f4 / 32768.0D);
/*      */     
/* 1046 */     this.curColor.set(f1, f2, f3, f4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1052 */     if (!this.bundlingColor) outputColor(this.curColor);
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
/* 1067 */     int j = getBits(1, "mbr");
/*      */     
/* 1069 */     int i = this.currentHeader >>> 1 & 0xF;
/* 1070 */     this.repCode = (this.currentHeader & true) << 1 | j;
/*      */ 
/*      */     
/* 1073 */     i = this.meshIndex - i & 0xF;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1078 */     MeshBufferEntry meshBufferEntry = this.meshBuffer[i];
/* 1079 */     this.curX = meshBufferEntry.x;
/* 1080 */     this.curY = meshBufferEntry.y;
/* 1081 */     this.curZ = meshBufferEntry.z;
/*      */ 
/*      */     
/* 1084 */     this.curPos.set(this.curX / 32768.0F, this.curY / 32768.0F, this.curZ / 32768.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1093 */     if (this.bundlingNorm && (this.meshState & true) != 0) {
/* 1094 */       this.curSex = meshBufferEntry.sextant;
/* 1095 */       this.curOct = meshBufferEntry.octant;
/* 1096 */       this.curU = meshBufferEntry.u;
/* 1097 */       this.curV = meshBufferEntry.v;
/*      */ 
/*      */       
/* 1100 */       int k = this.curSex << 15 | this.curOct << 12 | this.curU << 6 | this.curV;
/*      */ 
/*      */       
/* 1103 */       indexNormal(this.curSex, this.curOct, this.curU, this.curV, this.curNorm);
/*      */     } 
/*      */ 
/*      */     
/* 1107 */     if (this.bundlingColor && (this.meshState & 0x2) != 0) {
/* 1108 */       this.curR = meshBufferEntry.r;
/* 1109 */       this.curG = meshBufferEntry.g;
/* 1110 */       this.curB = meshBufferEntry.b;
/*      */ 
/*      */       
/* 1113 */       this.curColor.x = this.curR; this.curColor.x = (float)(this.curColor.x / 32768.0D);
/* 1114 */       this.curColor.y = this.curG; this.curColor.y = (float)(this.curColor.y / 32768.0D);
/* 1115 */       this.curColor.z = this.curB; this.curColor.z = (float)(this.curColor.z / 32768.0D);
/*      */       
/* 1117 */       if (this.doingAlpha) {
/* 1118 */         this.curA = meshBufferEntry.a;
/* 1119 */         this.curColor.w = this.curA; this.curColor.w = (float)(this.curColor.w / 32768.0D);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1128 */     this.meshState = 0;
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
/* 1142 */     int j = getBits(5, "noop count");
/* 1143 */     int i = getBits(j, "noop bits");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processPassThrough() {
/* 1152 */     int i = getBits(24, "passthrough");
/* 1153 */     i = getBits(32, "passthrough");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1161 */   private void processSkip8() { int i = getBits(8, "skip8"); }
/*      */ 
/*      */   
/*      */   private void benchmarkStart(int paramInt) {
/* 1165 */     this.vertexCount = 0;
/* 1166 */     System.err.println(" GeometryDecompressor: decompressing " + paramInt + " bytes...");
/*      */     
/* 1168 */     this.startTime = J3dClock.currentTimeMillis();
/*      */   }
/*      */   
/*      */   private void benchmarkPrint(int paramInt) {
/* 1172 */     float f = (float)(J3dClock.currentTimeMillis() - this.startTime) / 1000.0F;
/* 1173 */     System.err.println("  done in " + f + " sec." + "\n" + "  decompressed " + this.vertexCount + " vertices at " + (this.vertexCount / f) + " vertices/sec\n");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1178 */     System.err.print("  vertex data present: coords");
/* 1179 */     int i = 12;
/* 1180 */     if (this.bundlingNorm) {
/* 1181 */       System.err.print(" normals");
/* 1182 */       i += 12;
/*      */     } 
/* 1184 */     if (this.bundlingColor) {
/* 1185 */       System.err.println(" colors");
/* 1186 */       i += 12;
/*      */     } 
/* 1188 */     if (this.doingAlpha) {
/* 1189 */       System.err.println(" alpha");
/* 1190 */       i += 4;
/*      */     } 
/* 1192 */     System.err.println();
/*      */     
/* 1194 */     System.err.println("  bytes of data in generalized strip output: " + (this.vertexCount * i) + "\n" + "  compression ratio: " + (paramInt / (this.vertexCount * i)) + "\n");
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


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\GeometryDecompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */