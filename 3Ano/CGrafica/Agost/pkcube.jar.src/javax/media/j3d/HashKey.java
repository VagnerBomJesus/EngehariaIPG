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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class HashKey
/*     */ {
/*     */   char[] value;
/*     */   int count;
/*     */   
/*  28 */   HashKey() { this(16); }
/*     */   
/*     */   HashKey(int paramInt) {
/*     */     this.count = 0;
/*  32 */     this.value = new char[paramInt];
/*     */   }
/*     */   HashKey(HashKey paramHashKey) {
/*     */     this.count = 0;
/*  36 */     set(paramHashKey);
/*     */   }
/*     */   
/*     */   HashKey(String paramString) {
/*  40 */     this(paramString.length() + 16);
/*  41 */     append(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void set(HashKey paramHashKey) {
/*  47 */     if (this.count < paramHashKey.count) {
/*  48 */       this.value = new char[paramHashKey.count];
/*     */     }
/*     */     
/*  51 */     for (byte b = 0; b < paramHashKey.count; b++) {
/*  52 */       this.value[b] = paramHashKey.value[b];
/*     */     }
/*  54 */     this.count = paramHashKey.count;
/*     */   }
/*     */ 
/*     */   
/*  58 */   void reset() { this.count = 0; }
/*     */ 
/*     */   
/*     */   void ensureCapacity(int paramInt) {
/*  62 */     int i = this.value.length;
/*     */     
/*  64 */     if (paramInt > i) {
/*  65 */       int j = (i + 1) * 2;
/*  66 */       if (paramInt > j) {
/*  67 */         j = paramInt;
/*     */       }
/*     */       
/*  70 */       char[] arrayOfChar = new char[j];
/*  71 */       System.arraycopy(this.value, 0, arrayOfChar, 0, this.count);
/*  72 */       this.value = arrayOfChar;
/*     */     } 
/*     */   }
/*     */   
/*     */   HashKey append(String paramString) {
/*  77 */     int i = 0;
/*     */     
/*  79 */     if (paramString == null) {
/*  80 */       return this;
/*     */     }
/*  82 */     i = paramString.length();
/*  83 */     ensureCapacity(this.count + i);
/*  84 */     paramString.getChars(0, i, this.value, this.count);
/*  85 */     this.count += i;
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  90 */     char c = Character.MIN_VALUE;
/*  91 */     int i = 0;
/*  92 */     char[] arrayOfChar = this.value;
/*  93 */     int j = this.count;
/*     */     
/*  95 */     if (j < 16) {
/*  96 */       for (int k = j; k > 0; k--) {
/*  97 */         c = c * 37 + arrayOfChar[i++];
/*     */       }
/*     */     } else {
/*     */       
/* 101 */       int k = j / 8;
/* 102 */       for (int m = j; m > 0; m -= k, i += k) {
/* 103 */         c = c * '\'' + arrayOfChar[i];
/*     */       }
/*     */     } 
/* 106 */     return c;
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 110 */     if (paramObject != null && paramObject instanceof HashKey) {
/* 111 */       HashKey hashKey = (HashKey)paramObject;
/* 112 */       int i = this.count;
/* 113 */       if (i == hashKey.count) {
/* 114 */         char[] arrayOfChar1 = this.value;
/* 115 */         char[] arrayOfChar2 = hashKey.value;
/* 116 */         byte b1 = 0;
/* 117 */         byte b2 = 0;
/* 118 */         while (i-- != 0) {
/* 119 */           if (arrayOfChar1[b1++] != arrayOfChar2[b2++]) {
/* 120 */             return false;
/*     */           }
/*     */         } 
/* 123 */         return true;
/*     */       } 
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int equals(HashKey paramHashKey) {
/* 132 */     byte b = 0;
/*     */     
/* 134 */     while (b < this.count && b < paramHashKey.count) {
/* 135 */       if (this.value[b] < paramHashKey.value[b])
/* 136 */         return -1; 
/* 137 */       if (this.value[b] > paramHashKey.value[b])
/* 138 */         return 1; 
/* 139 */       b++;
/*     */     } 
/*     */     
/* 142 */     if (this.count == paramHashKey.count)
/*     */     {
/* 144 */       return 0; } 
/* 145 */     if (this.count < paramHashKey.count) {
/* 146 */       return -1;
/*     */     }
/* 148 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int equals(HashKey[] paramArrayOfHashKey, int paramInt1, int paramInt2) {
/* 157 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 158 */     if (paramArrayOfHashKey[i] != null) {
/* 159 */       int j = equals(paramArrayOfHashKey[i]);
/*     */       
/* 161 */       if (j < 0 && paramInt1 != i)
/* 162 */         return equals(paramArrayOfHashKey, paramInt1, i); 
/* 163 */       if (j > 0 && paramInt1 != i)
/* 164 */         return equals(paramArrayOfHashKey, i, paramInt2); 
/* 165 */       if (j == 0) {
/* 166 */         return i;
/*     */       }
/* 168 */       return -1;
/*     */     } 
/*     */     
/* 171 */     return -2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean equals(HashKey[] paramArrayOfHashKey, int[] paramArrayOfInt, int paramInt1, int paramInt2) {
/* 180 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 181 */     if (paramArrayOfHashKey[i] != null) {
/* 182 */       int j = equals(paramArrayOfHashKey[i]);
/*     */       
/* 184 */       if (paramInt1 != i) {
/* 185 */         if (j < 0) {
/* 186 */           return equals(paramArrayOfHashKey, paramArrayOfInt, paramInt1, i);
/*     */         }
/* 188 */         if (j > 0) {
/* 189 */           return equals(paramArrayOfHashKey, paramArrayOfInt, i, paramInt2);
/*     */         }
/*     */       } else {
/*     */         
/* 193 */         if (j < 0) {
/* 194 */           paramArrayOfInt[0] = i;
/* 195 */           return false;
/*     */         } 
/* 197 */         if (j > 0) {
/* 198 */           paramArrayOfInt[0] = i + 1;
/* 199 */           return false;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 204 */       paramArrayOfInt[0] = i;
/* 205 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 210 */     paramArrayOfInt[0] = i;
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 215 */   public String toString() { return new String(this.value, 0, this.count); }
/*     */ 
/*     */ 
/*     */   
/*     */   String getLastNodeId() {
/*     */     int i;
/* 221 */     for (i = this.count - 1; i > 0 && 
/* 222 */       this.value[i] != '+'; i--);
/*     */ 
/*     */     
/* 225 */     if (i > 0) {
/* 226 */       this.value[i++] = Character.MIN_VALUE;
/* 227 */       int j = this.count - i;
/* 228 */       char[] arrayOfChar = new char[j];
/* 229 */       for (byte b = 0; b < j; b++, i++) {
/* 230 */         arrayOfChar[b] = this.value[i];
/* 231 */         this.value[i] = Character.MIN_VALUE;
/*     */       } 
/* 233 */       this.count -= j + 1;
/* 234 */       return new String(arrayOfChar);
/*     */     } 
/*     */     
/* 237 */     return new String(this.value, 0, this.count);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\HashKey.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */