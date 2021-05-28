/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MemoryFreeList
/*     */ {
/*  24 */   ArrayList elementData = null;
/*  25 */   int size = 0;
/*  26 */   int currBlockSize = 10;
/*  27 */   Object[] currBlock = null;
/*  28 */   int currBlockIndex = 0;
/*  29 */   int spaceUsed = 0;
/*  30 */   int numBlocks = 0;
/*  31 */   int capacity = 0;
/*  32 */   int minBlockSize = 0;
/*     */   boolean justShrunk = false;
/*  34 */   int initcap = 10;
/*     */ 
/*     */   
/*  37 */   int minSize = 0;
/*     */   
/*  39 */   Class c = null;
/*     */ 
/*     */   
/*  42 */   MemoryFreeList(String paramString) { this(paramString, 10); }
/*     */ 
/*     */   
/*     */   MemoryFreeList(String paramString, int paramInt) {
/*  46 */     if (paramInt < 0) {
/*  47 */       throw new IllegalArgumentException("Illegal Capacity: " + paramInt);
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  52 */       this.c = Class.forName(paramString);
/*     */     }
/*  54 */     catch (Exception exception) {
/*  55 */       System.err.println(exception);
/*     */     } 
/*     */     
/*  58 */     this.initcap = paramInt;
/*  59 */     this.currBlockSize = paramInt;
/*  60 */     this.minBlockSize = this.currBlockSize;
/*  61 */     this.elementData = new ArrayList();
/*     */     
/*  63 */     this.currBlock = new Object[this.currBlockSize];
/*  64 */     this.elementData.add(this.currBlock);
/*  65 */     this.numBlocks++;
/*  66 */     this.capacity += this.currBlockSize;
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
/*  93 */   int size() { return this.size; }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean add(Object paramObject) {
/*  98 */     if (this.justShrunk) {
/*     */ 
/*     */       
/* 101 */       if (this.currBlockSize / 2 < this.spaceUsed) {
/* 102 */         this.size -= this.spaceUsed - this.currBlockSize / 2;
/* 103 */         this.spaceUsed = this.currBlockSize / 2;
/* 104 */         Arrays.fill(this.currBlock, this.spaceUsed, this.currBlockSize - 1, null);
/*     */       } 
/* 106 */       this.justShrunk = false;
/* 107 */       return false;
/*     */     } 
/*     */     
/* 110 */     ensureCapacity(this.size + 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (this.currBlockIndex == -1 || this.spaceUsed >= this.currBlockSize) {
/* 118 */       this.currBlockIndex++;
/* 119 */       this.currBlock = (Object[])this.elementData.get(this.currBlockIndex);
/* 120 */       this.currBlockSize = this.currBlock.length;
/* 121 */       this.spaceUsed = 0;
/*     */     } 
/* 123 */     int i = this.spaceUsed++;
/* 124 */     this.currBlock[i] = paramObject;
/* 125 */     this.size++;
/*     */     
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object removeLastElement() {
/* 133 */     int i = --this.spaceUsed;
/*     */     
/* 135 */     Object object = this.currBlock[i];
/* 136 */     this.currBlock[i] = null;
/* 137 */     this.size--;
/*     */ 
/*     */ 
/*     */     
/* 141 */     if (this.spaceUsed == 0) {
/* 142 */       this.currBlockIndex--;
/* 143 */       if (this.currBlockIndex < 0) {
/* 144 */         this.currBlock = null;
/* 145 */         this.currBlockSize = 0;
/*     */       } else {
/*     */         
/* 148 */         this.currBlock = (Object[])this.elementData.get(this.currBlockIndex);
/* 149 */         this.currBlockSize = this.currBlock.length;
/*     */       } 
/* 151 */       this.spaceUsed = this.currBlockSize;
/*     */     } 
/*     */     
/* 154 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void shrink() {
/* 161 */     if (this.minSize > this.minBlockSize && this.numBlocks > 1) {
/* 162 */       this.justShrunk = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 171 */       Object[] arrayOfObject = (Object[])this.elementData.remove(this.numBlocks - 1);
/* 172 */       this.numBlocks--;
/* 173 */       this.capacity -= arrayOfObject.length;
/*     */ 
/*     */ 
/*     */       
/* 177 */       if (this.numBlocks == this.currBlockIndex) {
/* 178 */         this.size -= this.spaceUsed;
/*     */         
/* 180 */         this.currBlockIndex = this.numBlocks - 1;
/* 181 */         this.currBlock = (Object[])this.elementData.get(this.currBlockIndex);
/* 182 */         this.currBlockSize = this.currBlock.length;
/*     */         
/* 184 */         this.spaceUsed = this.currBlockSize;
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 193 */       this.justShrunk = false;
/*     */     } 
/* 195 */     this.minSize = this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void ensureCapacity(int paramInt) {
/* 204 */     if (paramInt > this.capacity) {
/*     */       
/* 206 */       int i = (Object[])this.elementData.get(this.numBlocks - 1).length;
/*     */       
/* 208 */       int j = 0;
/* 209 */       if (this.numBlocks > 1) {
/* 210 */         j = (Object[])this.elementData.get(this.numBlocks - 2).length;
/*     */       }
/*     */       
/* 213 */       this.currBlockSize = i + j;
/* 214 */       this.currBlock = new Object[this.currBlockSize];
/* 215 */       this.elementData.add(this.currBlock);
/* 216 */       this.numBlocks++;
/* 217 */       this.currBlockIndex++;
/* 218 */       this.capacity += this.currBlockSize;
/*     */       
/* 220 */       this.spaceUsed = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   void rangeCheck(int paramInt) {
/* 225 */     if (paramInt >= this.size || paramInt < 0) {
/* 226 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 233 */     this.elementData.clear();
/*     */ 
/*     */     
/* 236 */     this.currBlockSize = this.initcap;
/* 237 */     this.minBlockSize = this.currBlockSize;
/* 238 */     this.currBlock = new Object[this.currBlockSize];
/* 239 */     this.elementData.add(this.currBlock);
/* 240 */     this.numBlocks = 1;
/* 241 */     this.capacity = this.currBlockSize;
/* 242 */     this.spaceUsed = 0;
/* 243 */     this.size = 0;
/* 244 */     this.currBlockIndex = 0;
/* 245 */     this.justShrunk = false;
/*     */   }
/*     */   
/*     */   Object getObject() {
/* 249 */     if (this.size > 0) {
/* 250 */       return removeLastElement();
/*     */     }
/*     */     
/*     */     try {
/* 254 */       return this.c.newInstance();
/*     */     }
/* 256 */     catch (Exception exception) {
/* 257 */       System.err.println(exception);
/* 258 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\MemoryFreeList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */