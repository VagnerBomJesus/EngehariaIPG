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
/*     */ class BHInternalNode
/*     */   extends BHNode
/*     */ {
/*     */   static boolean debug2 = true;
/*     */   BHNode rChild;
/*     */   BHNode lChild;
/*     */   
/*     */   BHInternalNode() {
/*  26 */     this.nodeType = 1;
/*  27 */     this.rChild = null;
/*  28 */     this.lChild = null;
/*     */   }
/*     */   
/*     */   BHInternalNode(BHNode paramBHNode) {
/*  32 */     super(paramBHNode);
/*  33 */     this.nodeType = 1;
/*  34 */     this.rChild = null;
/*  35 */     this.lChild = null;
/*     */   }
/*     */   
/*     */   BHInternalNode(BHNode paramBHNode1, BHNode paramBHNode2, BHNode paramBHNode3) {
/*  39 */     super(paramBHNode1);
/*  40 */     this.nodeType = 1;
/*  41 */     this.rChild = paramBHNode2;
/*  42 */     this.lChild = paramBHNode3;
/*     */   }
/*     */   
/*     */   BHInternalNode(BHNode paramBHNode, BoundingBox paramBoundingBox) {
/*  46 */     super(paramBHNode, paramBoundingBox);
/*  47 */     this.nodeType = 1;
/*  48 */     this.rChild = null;
/*  49 */     this.lChild = null;
/*     */   }
/*     */   
/*     */   BHInternalNode(BHNode paramBHNode1, BHNode paramBHNode2, BHNode paramBHNode3, BoundingBox paramBoundingBox) {
/*  53 */     super(paramBHNode1, paramBoundingBox);
/*  54 */     this.nodeType = 1;
/*  55 */     this.rChild = paramBHNode2;
/*  56 */     this.lChild = paramBHNode3;
/*     */   }
/*     */ 
/*     */   
/*  60 */   BHNode getLeftChild() { return this.lChild; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   BHNode getRightChild() { return this.rChild; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   void setLeftChild(BHNode paramBHNode) { this.lChild = paramBHNode; }
/*     */ 
/*     */ 
/*     */   
/*  72 */   void setRightChild(BHNode paramBHNode) { this.rChild = paramBHNode; }
/*     */ 
/*     */   
/*     */   void computeBoundingHull(BoundingBox paramBoundingBox) {
/*  76 */     computeBoundingHull();
/*  77 */     paramBoundingBox.set(this.bHull);
/*     */   }
/*     */   
/*     */   void computeBoundingHull() {
/*  81 */     BoundingBox boundingBox1 = null;
/*  82 */     BoundingBox boundingBox2 = null;
/*     */ 
/*     */     
/*  85 */     if (this.lChild == null && this.rChild == null) {
/*  86 */       this.bHull = null;
/*     */       
/*     */       return;
/*     */     } 
/*  90 */     if (this.lChild != null) {
/*  91 */       boundingBox2 = this.lChild.getBoundingHull();
/*     */     }
/*  93 */     if (this.rChild != null) {
/*  94 */       boundingBox1 = this.rChild.getBoundingHull();
/*     */     }
/*  96 */     if (this.bHull == null) {
/*  97 */       this.bHull = new BoundingBox();
/*     */     }
/*     */     
/* 100 */     if (this.lChild == null) {
/* 101 */       this.bHull.set(boundingBox1);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 106 */     if (this.rChild == null) {
/* 107 */       this.bHull.set(boundingBox2);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 112 */     this.bHull.set(boundingBox1);
/* 113 */     this.bHull.combine(boundingBox2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMarkedBoundingHull() {
/* 119 */     if (!this.mark) {
/*     */       return;
/*     */     }
/* 122 */     this.rChild.updateMarkedBoundingHull();
/* 123 */     this.lChild.updateMarkedBoundingHull();
/* 124 */     computeBoundingHull();
/* 125 */     this.mark = false;
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
/*     */   void insert(BHNode paramBHNode, BHInsertStructure paramBHInsertStructure) {
/* 140 */     if (debug2 && 
/* 141 */       !isInside(paramBHNode.bHull)) {
/* 142 */       System.err.println("Incorrect use of insertion, current node");
/* 143 */       System.err.println("must contain the input element ...");
/*     */     } 
/*     */     
/* 146 */     boolean bool1 = false;
/* 147 */     boolean bool2 = false;
/*     */ 
/*     */     
/* 150 */     if (this.rChild.nodeType == 2) {
/* 151 */       bool1 = false;
/*     */     } else {
/* 153 */       bool1 = this.rChild.isInside(paramBHNode.bHull);
/*     */     } 
/* 155 */     if (this.lChild.nodeType == 2) {
/* 156 */       bool2 = false;
/*     */     } else {
/* 158 */       bool2 = this.lChild.isInside(paramBHNode.bHull);
/*     */     } 
/*     */     
/* 161 */     if (bool2 && !bool1) {
/* 162 */       ((BHInternalNode)this.lChild).insert(paramBHNode, paramBHInsertStructure);
/* 163 */     } else if (!bool2 && bool1) {
/* 164 */       ((BHInternalNode)this.rChild).insert(paramBHNode, paramBHInsertStructure);
/* 165 */     } else if (bool2 && bool1) {
/*     */       
/* 167 */       if (paramBHInsertStructure.randomNumber.nextBoolean()) {
/* 168 */         ((BHInternalNode)this.lChild).insert(paramBHNode, paramBHInsertStructure);
/*     */       } else {
/* 170 */         ((BHInternalNode)this.rChild).insert(paramBHNode, paramBHInsertStructure);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 177 */       paramBHInsertStructure.lookupAndInsert(this, paramBHNode);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void destroyTree(BHNode[] paramArrayOfBHNode, int[] paramArrayOfInt) {
/* 183 */     if (this.rChild != null) {
/* 184 */       this.rChild.destroyTree(paramArrayOfBHNode, paramArrayOfInt);
/*     */     }
/* 186 */     if (this.lChild != null) {
/* 187 */       this.lChild.destroyTree(paramArrayOfBHNode, paramArrayOfInt);
/*     */     }
/* 189 */     this.rChild = null;
/* 190 */     this.lChild = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\BHInternalNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */