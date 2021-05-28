/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Heap
/*     */ {
/*     */   static void printHeapData(Triangulator paramTriangulator) {
/*  70 */     System.out.println("\nHeap Data : numZero " + paramTriangulator.numZero + " numHeap " + paramTriangulator.numHeap);
/*     */     
/*  72 */     for (byte b = 0; b < paramTriangulator.numHeap; b++) {
/*  73 */       System.out.println(b + " ratio " + (paramTriangulator.heap[b]).ratio + ", index " + (paramTriangulator.heap[b]).index + ", prev " + (paramTriangulator.heap[b]).prev + ", next " + (paramTriangulator.heap[b]).next);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  78 */     System.out.println(" ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void initHeap(Triangulator paramTriangulator) {
/*  86 */     paramTriangulator.maxNumHeap = paramTriangulator.numPoints;
/*  87 */     paramTriangulator.heap = new HeapNode[paramTriangulator.maxNumHeap];
/*     */     
/*  89 */     paramTriangulator.numHeap = 0;
/*  90 */     paramTriangulator.numZero = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void storeHeapData(Triangulator paramTriangulator, int paramInt1, double paramDouble, int paramInt2, int paramInt3, int paramInt4) {
/*  96 */     paramTriangulator.heap[paramInt1] = new HeapNode();
/*  97 */     (paramTriangulator.heap[paramInt1]).ratio = paramDouble;
/*  98 */     (paramTriangulator.heap[paramInt1]).index = paramInt2;
/*  99 */     (paramTriangulator.heap[paramInt1]).prev = paramInt3;
/* 100 */     (paramTriangulator.heap[paramInt1]).next = paramInt4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void dumpOnHeap(Triangulator paramTriangulator, double paramDouble, int paramInt1, int paramInt2, int paramInt3) {
/*     */     int i;
/* 107 */     if (paramTriangulator.numHeap >= paramTriangulator.maxNumHeap) {
/*     */       
/* 109 */       HeapNode[] arrayOfHeapNode = paramTriangulator.heap;
/* 110 */       paramTriangulator.maxNumHeap += paramTriangulator.numPoints;
/* 111 */       paramTriangulator.heap = new HeapNode[paramTriangulator.maxNumHeap];
/* 112 */       System.arraycopy(arrayOfHeapNode, 0, paramTriangulator.heap, 0, arrayOfHeapNode.length);
/*     */     } 
/* 114 */     if (paramDouble == 0.0D) {
/* 115 */       if (paramTriangulator.numZero < paramTriangulator.numHeap) {
/* 116 */         if (paramTriangulator.heap[paramTriangulator.numHeap] == null) {
/* 117 */           storeHeapData(paramTriangulator, paramTriangulator.numHeap, (paramTriangulator.heap[paramTriangulator.numZero]).ratio, (paramTriangulator.heap[paramTriangulator.numZero]).index, (paramTriangulator.heap[paramTriangulator.numZero]).prev, (paramTriangulator.heap[paramTriangulator.numZero]).next);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 122 */           paramTriangulator.heap[paramTriangulator.numHeap].copy(paramTriangulator.heap[paramTriangulator.numZero]);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       i = paramTriangulator.numZero;
/* 130 */       paramTriangulator.numZero++;
/*     */     } else {
/*     */       
/* 133 */       i = paramTriangulator.numHeap;
/*     */     } 
/*     */     
/* 136 */     storeHeapData(paramTriangulator, i, paramDouble, paramInt1, paramInt2, paramInt3);
/* 137 */     paramTriangulator.numHeap++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   static void insertIntoHeap(Triangulator paramTriangulator, double paramDouble, int paramInt1, int paramInt2, int paramInt3) { dumpOnHeap(paramTriangulator, paramDouble, paramInt1, paramInt2, paramInt3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean deleteFromHeap(Triangulator paramTriangulator, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 155 */     if (paramTriangulator.numZero > 0) {
/*     */       
/* 157 */       paramTriangulator.numZero--;
/* 158 */       paramTriangulator.numHeap--;
/*     */       
/* 160 */       paramArrayOfInt1[0] = (paramTriangulator.heap[paramTriangulator.numZero]).index;
/* 161 */       paramArrayOfInt2[0] = (paramTriangulator.heap[paramTriangulator.numZero]).prev;
/* 162 */       paramArrayOfInt3[0] = (paramTriangulator.heap[paramTriangulator.numZero]).next;
/* 163 */       if (paramTriangulator.numZero < paramTriangulator.numHeap) {
/* 164 */         paramTriangulator.heap[paramTriangulator.numZero].copy(paramTriangulator.heap[paramTriangulator.numHeap]);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 171 */       return true;
/*     */     } 
/* 173 */     if (paramTriangulator.earsRandom) {
/* 174 */       if (paramTriangulator.numHeap <= 0) {
/* 175 */         paramTriangulator.numHeap = 0;
/* 176 */         return false;
/*     */       } 
/* 178 */       double d = paramTriangulator.randomGen.nextDouble();
/* 179 */       int i = (int)(d * paramTriangulator.numHeap);
/* 180 */       paramTriangulator.numHeap--;
/* 181 */       if (i > paramTriangulator.numHeap) i = paramTriangulator.numHeap;
/*     */       
/* 183 */       paramArrayOfInt1[0] = (paramTriangulator.heap[i]).index;
/* 184 */       paramArrayOfInt2[0] = (paramTriangulator.heap[i]).prev;
/* 185 */       paramArrayOfInt3[0] = (paramTriangulator.heap[i]).next;
/* 186 */       if (i < paramTriangulator.numHeap) {
/* 187 */         paramTriangulator.heap[i].copy(paramTriangulator.heap[paramTriangulator.numHeap]);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 194 */       return true;
/*     */     } 
/*     */     
/* 197 */     if (paramTriangulator.numHeap <= 0) {
/* 198 */       paramTriangulator.numHeap = 0;
/* 199 */       return false;
/*     */     } 
/* 201 */     paramTriangulator.numHeap--;
/* 202 */     paramArrayOfInt1[0] = (paramTriangulator.heap[paramTriangulator.numHeap]).index;
/* 203 */     paramArrayOfInt2[0] = (paramTriangulator.heap[paramTriangulator.numHeap]).prev;
/* 204 */     paramArrayOfInt3[0] = (paramTriangulator.heap[paramTriangulator.numHeap]).next;
/*     */     
/* 206 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\Heap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */