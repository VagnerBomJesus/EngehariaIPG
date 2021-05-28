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
/*     */ 
/*     */ 
/*     */ abstract class CompressionStreamElement
/*     */ {
/*     */   int length;
/*     */   int shift;
/*     */   boolean absolute;
/*     */   static final int[] quantizationMask = { 
/*  72 */       -65536, -32768, -16384, -8192, -4096, -2048, -1024, -512, -256, -128, -64, -32, -16, -8, -4, -2, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final long[] lengthMask = { 
/*  86 */       0L, 1L, 3L, 7L, 15L, 31L, 63L, 127L, 255L, 511L, 1023L, 2047L, 4095L, 8191L, 16383L, 32767L, 65535L, 131071L, 262143L, 524287L, 1048575L, 2097151L, 4194303L, 8388607L, 16777215L, 33554431L, 67108863L, 134217727L, 268435455L, 536870911L, 1073741823L, 2147483647L, 4294967295L, 8589934591L, 17179869183L, 34359738367L, 68719476735L, 137438953471L, 274877906943L, 549755813887L, 1099511627775L, 2199023255551L, 4398046511103L, 8796093022207L, 17592186044415L, 35184372088831L, 70368744177663L, 140737488355327L, 281474976710655L, 562949953421311L, 1125899906842623L, 2251799813685247L, 4503599627370495L, 9007199254740991L, 18014398509481983L, 36028797018963967L, 72057594037927935L, 144115188075855871L, 288230376151711743L, 576460752303423487L, 1152921504606846975L, 2305843009213693951L, 4611686018427387903L, Float.MAX_VALUE, -1L };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void quantize(CompressionStream paramCompressionStream, HuffmanTable paramHuffmanTable);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputCommand(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int getLength(int paramInt) {
/* 162 */     if (paramInt == 0) {
/* 163 */       return 0;
/*     */     }
/* 165 */     if ((paramInt & 0x8000) > 0) {
/*     */       
/* 167 */       if ((paramInt & 0x4000) == 0) return 16; 
/* 168 */       if ((paramInt & 0x2000) == 0) return 15; 
/* 169 */       if ((paramInt & 0x1000) == 0) return 14; 
/* 170 */       if ((paramInt & 0x800) == 0) return 13; 
/* 171 */       if ((paramInt & 0x400) == 0) return 12; 
/* 172 */       if ((paramInt & 0x200) == 0) return 11; 
/* 173 */       if ((paramInt & 0x100) == 0) return 10; 
/* 174 */       if ((paramInt & 0x80) == 0) return 9; 
/* 175 */       if ((paramInt & 0x40) == 0) return 8; 
/* 176 */       if ((paramInt & 0x20) == 0) return 7; 
/* 177 */       if ((paramInt & 0x10) == 0) return 6; 
/* 178 */       if ((paramInt & 0x8) == 0) return 5; 
/* 179 */       if ((paramInt & 0x4) == 0) return 4; 
/* 180 */       if ((paramInt & 0x2) == 0) return 3; 
/* 181 */       if ((paramInt & true) == 0) return 2;
/*     */       
/* 183 */       return 1;
/*     */     } 
/*     */ 
/*     */     
/* 187 */     if ((paramInt & 0x4000) > 0) return 16; 
/* 188 */     if ((paramInt & 0x2000) > 0) return 15; 
/* 189 */     if ((paramInt & 0x1000) > 0) return 14; 
/* 190 */     if ((paramInt & 0x800) > 0) return 13; 
/* 191 */     if ((paramInt & 0x400) > 0) return 12; 
/* 192 */     if ((paramInt & 0x200) > 0) return 11; 
/* 193 */     if ((paramInt & 0x100) > 0) return 10; 
/* 194 */     if ((paramInt & 0x80) > 0) return 9; 
/* 195 */     if ((paramInt & 0x40) > 0) return 8; 
/* 196 */     if ((paramInt & 0x20) > 0) return 7; 
/* 197 */     if ((paramInt & 0x10) > 0) return 6; 
/* 198 */     if ((paramInt & 0x8) > 0) return 5; 
/* 199 */     if ((paramInt & 0x4) > 0) return 4; 
/* 200 */     if ((paramInt & 0x2) > 0) return 3;
/*     */     
/* 202 */     return 2;
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
/*     */   private static final int getShift(int paramInt) {
/* 217 */     if (paramInt == 0) return 0;
/*     */     
/* 219 */     if ((paramInt & true) > 0) return 0; 
/* 220 */     if ((paramInt & 0x2) > 0) return 1; 
/* 221 */     if ((paramInt & 0x4) > 0) return 2; 
/* 222 */     if ((paramInt & 0x8) > 0) return 3; 
/* 223 */     if ((paramInt & 0x10) > 0) return 4; 
/* 224 */     if ((paramInt & 0x20) > 0) return 5; 
/* 225 */     if ((paramInt & 0x40) > 0) return 6; 
/* 226 */     if ((paramInt & 0x80) > 0) return 7; 
/* 227 */     if ((paramInt & 0x100) > 0) return 8; 
/* 228 */     if ((paramInt & 0x200) > 0) return 9; 
/* 229 */     if ((paramInt & 0x400) > 0) return 10; 
/* 230 */     if ((paramInt & 0x800) > 0) return 11; 
/* 231 */     if ((paramInt & 0x1000) > 0) return 12; 
/* 232 */     if ((paramInt & 0x2000) > 0) return 13; 
/* 233 */     if ((paramInt & 0x4000) > 0) return 14;
/*     */     
/* 235 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void computeLengthShift(int paramInt1, int paramInt2) {
/* 242 */     int i = paramInt1 & 0x8000;
/* 243 */     int j = paramInt2 & 0x8000;
/*     */ 
/*     */     
/* 246 */     if (i == j)
/* 247 */     { if (i == 0) {
/* 248 */         this.length = getLength(paramInt1 | paramInt2);
/*     */       } else {
/* 250 */         this.length = getLength(paramInt1 & paramInt2);
/*     */       }  }
/* 252 */     else { this.length = getMaximum(getLength(paramInt1), getLength(paramInt2)); }
/*     */     
/* 254 */     this.shift = getShift(paramInt1 | paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void computeLengthShift(int paramInt1, int paramInt2, int paramInt3) {
/* 262 */     int i = paramInt1 & 0x8000;
/* 263 */     int j = paramInt2 & 0x8000;
/* 264 */     int k = paramInt3 & 0x8000;
/*     */ 
/*     */     
/* 267 */     if (i == j) {
/* 268 */       if (j == k) {
/* 269 */         if (k == 0) {
/* 270 */           this.length = getLength(paramInt1 | paramInt2 | paramInt3);
/*     */         } else {
/* 272 */           this.length = getLength(paramInt1 & paramInt2 & paramInt3);
/*     */         } 
/* 274 */       } else if (j == 0) {
/* 275 */         this.length = getMaximum(getLength(paramInt1 | paramInt2), getLength(paramInt3));
/*     */       } else {
/*     */         
/* 278 */         this.length = getMaximum(getLength(paramInt1 & paramInt2), getLength(paramInt3));
/*     */       }
/*     */     
/* 281 */     } else if (j == k) {
/* 282 */       if (k == 0) {
/* 283 */         this.length = getMaximum(getLength(paramInt2 | paramInt3), getLength(paramInt1));
/*     */       } else {
/*     */         
/* 286 */         this.length = getMaximum(getLength(paramInt2 & paramInt3), getLength(paramInt1));
/*     */       }
/*     */     
/* 289 */     } else if (i == 0) {
/* 290 */       this.length = getMaximum(getLength(paramInt1 | paramInt3), getLength(paramInt2));
/*     */     } else {
/*     */       
/* 293 */       this.length = getMaximum(getLength(paramInt1 & paramInt3), getLength(paramInt2));
/*     */     } 
/*     */     
/* 296 */     this.shift = getShift(paramInt1 | paramInt2 | paramInt3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void computeLengthShift(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 304 */     this.length = getMaximum(getLength(paramInt1), getLength(paramInt2), getLength(paramInt3), getLength(paramInt4));
/*     */ 
/*     */     
/* 307 */     this.shift = getShift(paramInt1 | paramInt2 | paramInt3 | paramInt4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int getMaximum(int paramInt1, int paramInt2) {
/* 315 */     if (paramInt1 > paramInt2) {
/* 316 */       return paramInt1;
/*     */     }
/* 318 */     return paramInt2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int getMaximum(int paramInt1, int paramInt2, int paramInt3) {
/* 325 */     if (paramInt1 > paramInt2) {
/* 326 */       if (paramInt1 > paramInt3) {
/* 327 */         return paramInt1;
/*     */       }
/* 329 */       return paramInt3;
/*     */     } 
/* 331 */     if (paramInt2 > paramInt3) {
/* 332 */       return paramInt2;
/*     */     }
/* 334 */     return paramInt3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int getMaximum(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*     */     int j;
/*     */     int i;
/* 343 */     if (paramInt1 > paramInt2) {
/* 344 */       i = paramInt1;
/*     */     } else {
/* 346 */       i = paramInt2;
/*     */     } 
/* 348 */     if (paramInt3 > paramInt4) {
/* 349 */       j = paramInt3;
/*     */     } else {
/* 351 */       j = paramInt4;
/*     */     } 
/* 353 */     if (i > j) {
/* 354 */       return i;
/*     */     }
/* 356 */     return j;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\compression\CompressionStreamElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */