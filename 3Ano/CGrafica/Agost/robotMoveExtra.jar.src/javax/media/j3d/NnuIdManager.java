/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NnuIdManager
/*     */ {
/*  16 */   static int nnuId = 0;
/*     */   
/*     */   static final int getId() {
/*  19 */     if (nnuId == Integer.MAX_VALUE) {
/*  20 */       nnuId = 0;
/*     */     }
/*     */     
/*  23 */     return nnuId++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static final int equals(NnuId[] paramArrayOfNnuId, NnuId paramNnuId, int paramInt1, int paramInt2) {
/*  29 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  30 */     if (paramArrayOfNnuId[i] != null) {
/*  31 */       int j = paramNnuId.equal(paramArrayOfNnuId[i]);
/*     */       
/*  33 */       if (j < 0 && paramInt1 != i)
/*  34 */         return equals(paramArrayOfNnuId, paramNnuId, paramInt1, i); 
/*  35 */       if (j > 0 && paramInt1 != i)
/*  36 */         return equals(paramArrayOfNnuId, paramNnuId, i, paramInt2); 
/*  37 */       if (j == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  42 */         if (paramNnuId == paramArrayOfNnuId[i]) {
/*  43 */           return i;
/*     */         }
/*     */         
/*  46 */         int k = i - 1;
/*     */         
/*  48 */         while (k >= paramInt1 && paramNnuId.equal(paramArrayOfNnuId[k]) == 0) {
/*  49 */           if (paramNnuId == paramArrayOfNnuId[k]) {
/*  50 */             return k;
/*     */           }
/*  52 */           k--;
/*     */         } 
/*     */ 
/*     */         
/*  56 */         k = i + 1;
/*  57 */         while (k < paramInt2 && paramNnuId.equal(paramArrayOfNnuId[k]) == 0) {
/*  58 */           if (paramNnuId == paramArrayOfNnuId[k]) {
/*  59 */             return k;
/*     */           }
/*  61 */           k++;
/*     */         } 
/*     */ 
/*     */         
/*  65 */         return -1;
/*     */       } 
/*     */       
/*  68 */       return -1;
/*     */     } 
/*     */     
/*  71 */     return -2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean equals(NnuId[] paramArrayOfNnuId, NnuId paramNnuId, int[] paramArrayOfInt, int paramInt1, int paramInt2) {
/*  79 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*     */     
/*  81 */     if (paramArrayOfNnuId[i] != null) {
/*  82 */       int j = paramNnuId.equal(paramArrayOfNnuId[i]);
/*     */       
/*  84 */       if (paramInt1 != i) {
/*  85 */         if (j < 0) {
/*  86 */           return equals(paramArrayOfNnuId, paramNnuId, paramArrayOfInt, paramInt1, i);
/*     */         }
/*  88 */         if (j > 0) {
/*  89 */           return equals(paramArrayOfNnuId, paramNnuId, paramArrayOfInt, i, paramInt2);
/*     */         }
/*     */       } else {
/*     */         
/*  93 */         if (j < 0) {
/*  94 */           paramArrayOfInt[0] = i;
/*  95 */           return false;
/*     */         } 
/*  97 */         if (j > 0) {
/*  98 */           paramArrayOfInt[0] = i + 1;
/*  99 */           return false;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 108 */       if (paramNnuId == paramArrayOfNnuId[i]) {
/* 109 */         paramArrayOfInt[0] = i;
/* 110 */         return true;
/*     */       } 
/*     */       
/* 113 */       int k = i - 1;
/*     */       
/* 115 */       while (k >= paramInt1 && paramNnuId.equal(paramArrayOfNnuId[k]) == 0) {
/* 116 */         if (paramNnuId == paramArrayOfNnuId[k]) {
/* 117 */           paramArrayOfInt[0] = k;
/* 118 */           return true;
/*     */         } 
/* 120 */         k--;
/*     */       } 
/*     */ 
/*     */       
/* 124 */       k = i + 1;
/* 125 */       while (k < paramInt2 && paramNnuId.equal(paramArrayOfNnuId[k]) == 0) {
/* 126 */         if (paramNnuId == paramArrayOfNnuId[k]) {
/* 127 */           paramArrayOfInt[0] = k;
/* 128 */           return true;
/*     */         } 
/* 130 */         k++;
/*     */       } 
/*     */ 
/*     */       
/* 134 */       paramArrayOfInt[0] = k;
/* 135 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 140 */     paramArrayOfInt[0] = i;
/* 141 */     return false;
/*     */   }
/*     */   
/*     */   static final void sort(NnuId[] paramArrayOfNnuId) {
/* 145 */     if (paramArrayOfNnuId.length < 20) {
/* 146 */       insertSort(paramArrayOfNnuId);
/*     */     } else {
/* 148 */       quicksort(paramArrayOfNnuId, 0, paramArrayOfNnuId.length - 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final void insertSort(NnuId[] paramArrayOfNnuId) {
/* 156 */     for (byte b = 0; b < paramArrayOfNnuId.length; b++) {
/* 157 */       for (byte b1 = b; b1 && paramArrayOfNnuId[b1 - true].getId() > paramArrayOfNnuId[b1].getId(); 
/* 158 */         b1--) {
/* 159 */         NnuId nnuId1 = paramArrayOfNnuId[b1];
/* 160 */         paramArrayOfNnuId[b1] = paramArrayOfNnuId[b1 - true];
/* 161 */         paramArrayOfNnuId[b1 - true] = nnuId1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static final void quicksort(NnuId[] paramArrayOfNnuId, int paramInt1, int paramInt2) {
/* 167 */     int i = paramInt1;
/* 168 */     int j = paramInt2;
/* 169 */     int k = paramArrayOfNnuId[(paramInt1 + paramInt2) / 2].getId();
/*     */     
/*     */     do {
/* 172 */       for (; paramArrayOfNnuId[i].getId() < k; i++);
/* 173 */       for (; k < paramArrayOfNnuId[j].getId(); j--);
/* 174 */       if (i > j)
/* 175 */         continue;  NnuId nnuId1 = paramArrayOfNnuId[i];
/* 176 */       paramArrayOfNnuId[i] = paramArrayOfNnuId[j];
/* 177 */       paramArrayOfNnuId[j] = nnuId1;
/*     */       
/* 179 */       i++;
/* 180 */       j--;
/*     */     }
/* 182 */     while (i <= j);
/*     */     
/* 184 */     if (paramInt1 < j) quicksort(paramArrayOfNnuId, paramInt1, j); 
/* 185 */     if (paramInt1 < paramInt2) quicksort(paramArrayOfNnuId, i, paramInt2);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final NnuId[] delete(NnuId[] paramArrayOfNnuId1, NnuId[] paramArrayOfNnuId2) {
/* 194 */     int i = 0, j = 0;
/* 195 */     boolean bool = false;
/*     */     
/* 197 */     int k = paramArrayOfNnuId1.length - paramArrayOfNnuId2.length;
/*     */     
/* 199 */     if (k > 0) {
/* 200 */       NnuId[] arrayOfNnuId = new NnuId[k];
/*     */       
/* 202 */       for (byte b = 0; b < paramArrayOfNnuId2.length; b++) {
/* 203 */         int m = equals(paramArrayOfNnuId1, paramArrayOfNnuId2[b], 0, paramArrayOfNnuId1.length);
/*     */         
/* 205 */         if (m >= 0) {
/* 206 */           bool = true;
/* 207 */           if (m == i) {
/* 208 */             i++;
/*     */           } else {
/*     */             
/* 211 */             int n = m - i;
/* 212 */             System.arraycopy(paramArrayOfNnuId1, i, arrayOfNnuId, j, n);
/*     */ 
/*     */             
/* 215 */             i = m + 1;
/* 216 */             j += n;
/*     */           } 
/*     */         } else {
/*     */           
/* 220 */           bool = false;
/* 221 */           MasterControl.getCoreLogger().severe("Can't Find matching nnuId.");
/*     */         } 
/*     */       } 
/*     */       
/* 225 */       if (bool == true && i < paramArrayOfNnuId1.length) {
/* 226 */         int m = paramArrayOfNnuId1.length - i;
/* 227 */         System.arraycopy(paramArrayOfNnuId1, i, arrayOfNnuId, j, m);
/*     */       } 
/*     */       
/* 230 */       return arrayOfNnuId;
/*     */     } 
/* 232 */     if (k == 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 239 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final NnuId[] merge(NnuId[] paramArrayOfNnuId1, NnuId[] paramArrayOfNnuId2) {
/* 247 */     int[] arrayOfInt = new int[1];
/*     */ 
/*     */     
/* 250 */     int j = paramArrayOfNnuId1.length + paramArrayOfNnuId2.length;
/*     */     
/* 252 */     NnuId[] arrayOfNnuId = new NnuId[j];
/*     */ 
/*     */     
/* 255 */     System.arraycopy(paramArrayOfNnuId1, 0, arrayOfNnuId, 0, paramArrayOfNnuId1.length); int i;
/*     */     byte b;
/* 257 */     for (i = paramArrayOfNnuId1.length, b = 0; i < j; i++, b++) {
/*     */       
/* 259 */       equals((NnuId[])arrayOfNnuId, paramArrayOfNnuId2[b], arrayOfInt, 0, i);
/*     */       
/* 261 */       if (arrayOfInt[0] == i) {
/* 262 */         arrayOfNnuId[i] = paramArrayOfNnuId2[b];
/*     */       } else {
/*     */         
/* 265 */         int k = arrayOfInt[0] + 1;
/* 266 */         int m = i - arrayOfInt[0];
/*     */ 
/*     */ 
/*     */         
/* 270 */         System.arraycopy(arrayOfNnuId, arrayOfInt[0], arrayOfNnuId, k, m);
/*     */ 
/*     */         
/* 273 */         arrayOfNnuId[arrayOfInt[0]] = paramArrayOfNnuId2[b];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 278 */     return arrayOfNnuId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final void replace(NnuId paramNnuId1, NnuId paramNnuId2, NnuId[] paramArrayOfNnuId) {
/* 286 */     int[] arrayOfInt = new int[1];
/* 287 */     int i = paramArrayOfNnuId.length - 1;
/*     */ 
/*     */ 
/*     */     
/* 291 */     arrayOfInt[0] = equals(paramArrayOfNnuId, paramNnuId1, 0, paramArrayOfNnuId.length);
/* 292 */     if (arrayOfInt[0] == i) {
/* 293 */       paramArrayOfNnuId[arrayOfInt[0]] = null;
/*     */     }
/* 295 */     else if (arrayOfInt[0] >= 0) {
/* 296 */       int j = i - arrayOfInt[0];
/* 297 */       System.arraycopy(paramArrayOfNnuId, arrayOfInt[0] + 1, paramArrayOfNnuId, arrayOfInt[0], j);
/*     */       
/* 299 */       paramArrayOfNnuId[i] = null;
/*     */     } else {
/*     */       
/* 302 */       MasterControl.getCoreLogger().severe("Can't Find matching nnuId.");
/*     */     } 
/*     */ 
/*     */     
/* 306 */     equals(paramArrayOfNnuId, paramNnuId2, arrayOfInt, 0, i);
/*     */     
/* 308 */     if (arrayOfInt[0] == i) {
/* 309 */       paramArrayOfNnuId[arrayOfInt[0]] = paramNnuId2;
/*     */     } else {
/*     */       
/* 312 */       int j = i - arrayOfInt[0];
/*     */ 
/*     */ 
/*     */       
/* 316 */       System.arraycopy(paramArrayOfNnuId, arrayOfInt[0], paramArrayOfNnuId, arrayOfInt[0] + 1, j);
/*     */ 
/*     */       
/* 319 */       paramArrayOfNnuId[arrayOfInt[0]] = paramNnuId2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final void printIds(NnuId[] paramArrayOfNnuId) {
/* 327 */     for (byte b = 0; b < paramArrayOfNnuId.length; b++)
/* 328 */       System.err.println("[" + b + "] is " + paramArrayOfNnuId[b].getId()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\NnuIdManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */