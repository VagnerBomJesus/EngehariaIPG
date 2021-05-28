/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BHInsertStructure
/*     */ {
/*     */   static boolean debug = false;
/*     */   static boolean debug2 = false;
/*  23 */   ArrayList[] bhListArr = null;
/*  24 */   ArrayList[] oldBhListArr = null;
/*  25 */   BHNode[] bhListArrRef = null;
/*  26 */   BHNode[] oldBhListArrRef = null;
/*  27 */   int bhListArrCnt = 0;
/*  28 */   int bhListArrMaxCnt = 0;
/*  29 */   int blockSize = 0;
/*     */ 
/*     */   
/*  32 */   Random randomNumber = new Random(0L);
/*     */   BHInsertStructure(int paramInt) {
/*  34 */     if (paramInt > 50) {
/*  35 */       paramInt = 50;
/*     */     }
/*     */     
/*  38 */     this.blockSize = 50;
/*  39 */     this.bhListArr = new ArrayList[paramInt];
/*  40 */     this.bhListArrRef = new BHNode[paramInt];
/*  41 */     this.bhListArrCnt = 0;
/*  42 */     this.bhListArrMaxCnt = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void clear() {
/*  48 */     for (byte b = 0; b < this.bhListArrCnt; b++) {
/*  49 */       this.bhListArr[b].clear();
/*  50 */       this.bhListArrRef[b] = null;
/*     */     } 
/*  52 */     this.bhListArrCnt = 0;
/*     */   }
/*     */   
/*     */   void lookupAndInsert(BHNode paramBHNode1, BHNode paramBHNode2) {
/*  56 */     boolean bool = false;
/*     */     
/*  58 */     for (byte b = 0; b < this.bhListArrCnt; b++) {
/*     */       
/*  60 */       if (this.bhListArrRef[b] == paramBHNode1) {
/*     */         
/*  62 */         this.bhListArr[b].add(paramBHNode2);
/*  63 */         bool = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  68 */     if (!bool) {
/*     */       
/*  70 */       if (this.bhListArrCnt >= this.bhListArrMaxCnt) {
/*     */         
/*  72 */         if (debug)
/*  73 */           System.err.println("(1) Expanding bhListArr array ..."); 
/*  74 */         this.bhListArrMaxCnt += this.blockSize;
/*  75 */         this.oldBhListArr = this.bhListArr;
/*  76 */         this.oldBhListArrRef = this.bhListArrRef;
/*     */         
/*  78 */         this.bhListArr = new ArrayList[this.bhListArrMaxCnt];
/*  79 */         this.bhListArrRef = new BHNode[this.bhListArrMaxCnt];
/*  80 */         System.arraycopy(this.oldBhListArr, 0, this.bhListArr, 0, this.oldBhListArr.length);
/*  81 */         System.arraycopy(this.oldBhListArrRef, 0, this.bhListArrRef, 0, this.oldBhListArrRef.length);
/*     */       } 
/*     */ 
/*     */       
/*  85 */       this.bhListArrRef[this.bhListArrCnt] = paramBHNode1;
/*  86 */       this.bhListArr[this.bhListArrCnt] = new ArrayList();
/*  87 */       this.bhListArr[this.bhListArrCnt].add(paramBHNode2);
/*  88 */       this.bhListArrCnt++;
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
/*     */   void updateBoundingTree(BHTree paramBHTree) {
/* 101 */     for (byte b = 0; b < this.bhListArrCnt; b++) {
/*     */       
/* 103 */       int j = 0;
/* 104 */       BHNode bHNode1 = ((BHInternalNode)this.bhListArrRef[b]).getLeftChild();
/* 105 */       BHNode bHNode2 = ((BHInternalNode)this.bhListArrRef[b]).getRightChild();
/* 106 */       if (bHNode1 != null) j++; 
/* 107 */       if (bHNode2 != null) j++;
/*     */       
/* 109 */       int i = this.bhListArr[b].size();
/*     */       
/* 111 */       BHNode[] arrayOfBHNode = new BHNode[j + i];
/*     */       
/* 113 */       this.bhListArr[b].toArray(arrayOfBHNode);
/*     */ 
/*     */       
/* 116 */       j = 0;
/* 117 */       if (bHNode1 != null) {
/* 118 */         arrayOfBHNode[i] = bHNode1;
/* 119 */         j++;
/* 120 */         arrayOfBHNode[i + j] = bHNode2;
/*     */       } 
/*     */       
/* 123 */       if (debug2 && (
/* 124 */         bHNode1 == null || bHNode2 == null)) {
/* 125 */         System.err.println("child1 or child2 is null ...");
/* 126 */         System.err.println("This is bad, it shouldn't happen");
/*     */       } 
/*     */ 
/*     */       
/* 130 */       ((BHInternalNode)this.bhListArrRef[b]).setRightChild(null);
/* 131 */       ((BHInternalNode)this.bhListArrRef[b]).setLeftChild(null);
/*     */       
/* 133 */       paramBHTree.cluster((BHInternalNode)this.bhListArrRef[b], arrayOfBHNode);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\BHInsertStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */