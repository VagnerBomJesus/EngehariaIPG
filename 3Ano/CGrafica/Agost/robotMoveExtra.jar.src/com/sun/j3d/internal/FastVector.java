/*     */ package com.sun.j3d.internal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastVector
/*     */ {
/*     */   private int[] data;
/*     */   private int capacity;
/*     */   private int increment;
/*     */   private int size;
/*     */   
/*     */   public void addElement(int paramInt) {
/*  65 */     if (this.size >= this.capacity) {
/*  66 */       this.capacity += ((this.increment == 0) ? this.capacity : this.increment);
/*  67 */       int[] arrayOfInt = new int[this.capacity];
/*  68 */       System.arraycopy(this.data, 0, arrayOfInt, 0, this.size);
/*  69 */       this.data = arrayOfInt;
/*     */     } 
/*  71 */     this.data[this.size++] = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public int getSize() { return this.size; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public int[] getData() { return this.data; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastVector(int paramInt1, int paramInt2) {
/* 105 */     this.data = new int[paramInt1];
/* 106 */     this.capacity = paramInt1;
/* 107 */     this.increment = paramInt2;
/* 108 */     this.size = 0;
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
/*     */   public FastVector(int paramInt) {
/* 121 */     this.data = new int[paramInt];
/* 122 */     this.capacity = paramInt;
/* 123 */     this.increment = 0;
/* 124 */     this.size = 0;
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
/*     */   public FastVector() {
/* 136 */     this.data = new int[1];
/* 137 */     this.capacity = 1;
/* 138 */     this.increment = 0;
/* 139 */     this.size = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\internal\FastVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */