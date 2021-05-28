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
/*     */ abstract class BHNode
/*     */ {
/*     */   static final byte BH_TYPE_INTERNAL = 1;
/*     */   static final byte BH_TYPE_LEAF = 2;
/*     */   static final int NUMBER_OF_PLANES = 6;
/*     */   static final boolean debug = false;
/*     */   static final boolean debug2 = false;
/*     */   BHNode parent;
/*     */   byte nodeType;
/*  29 */   BoundingBox bHull = null;
/*     */   boolean mark;
/*     */   
/*     */   BHNode() {
/*  33 */     this.parent = null;
/*  34 */     this.mark = false;
/*     */   }
/*     */   
/*     */   BHNode(BHNode paramBHNode) {
/*  38 */     this.parent = paramBHNode;
/*  39 */     this.mark = false;
/*     */   }
/*     */   
/*     */   BHNode(BHNode paramBHNode, BoundingBox paramBoundingBox) {
/*  43 */     this.parent = paramBHNode;
/*  44 */     this.mark = false;
/*     */     
/*  46 */     this.bHull = paramBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*  50 */   BHNode getParent() { return this.parent; }
/*     */   
/*     */   abstract void computeBoundingHull();
/*     */   
/*     */   abstract void updateMarkedBoundingHull();
/*     */   
/*     */   abstract void destroyTree(BHNode[] paramArrayOfBHNode, int[] paramArrayOfInt);
/*     */   
/*  58 */   void setParent(BHNode paramBHNode) { this.parent = paramBHNode; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   BoundingBox getBoundingHull() { return this.bHull; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   void setBoundingHull(BoundingBox paramBoundingBox) { this.bHull = paramBoundingBox; }
/*     */ 
/*     */ 
/*     */   
/*     */   void combineBHull(BHNode paramBHNode1, BHNode paramBHNode2) {
/*  71 */     BoundingBox boundingBox1 = null;
/*  72 */     BoundingBox boundingBox2 = null;
/*     */     
/*  74 */     boundingBox1 = paramBHNode1.getBoundingHull();
/*  75 */     boundingBox2 = paramBHNode2.getBoundingHull();
/*     */     
/*  77 */     if (this.bHull == null) {
/*  78 */       this.bHull = new BoundingBox(boundingBox1);
/*     */     } else {
/*  80 */       this.bHull.set(boundingBox1);
/*     */     } 
/*  82 */     this.bHull.combine(boundingBox2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isInside(BoundingBox paramBoundingBox) {
/*  90 */     if (paramBoundingBox == null) {
/*  91 */       return false;
/*     */     }
/*  93 */     if (this.bHull.isEmpty() || paramBoundingBox.isEmpty()) {
/*  94 */       return false;
/*     */     }
/*     */     
/*  97 */     if (this.bHull.upper.x < paramBoundingBox.upper.x || this.bHull.upper.y < paramBoundingBox.upper.y || this.bHull.upper.z < paramBoundingBox.upper.z || this.bHull.lower.x > paramBoundingBox.lower.x || this.bHull.lower.y > paramBoundingBox.lower.y || this.bHull.lower.z > paramBoundingBox.lower.z)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 103 */       return false;
/*     */     }
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   BHNode findNode(BHNode paramBHNode) {
/* 111 */     BHNode bHNode = null;
/*     */     
/* 113 */     if (this.nodeType == 2) {
/* 114 */       if (this == paramBHNode) {
/* 115 */         return this;
/*     */       }
/*     */     } else {
/*     */       
/* 119 */       if (((BHInternalNode)this).rChild.isInside(paramBHNode.bHull)) {
/* 120 */         bHNode = ((BHInternalNode)this).rChild.findNode(paramBHNode);
/* 121 */         if (bHNode != null) {
/* 122 */           return bHNode;
/*     */         }
/*     */       } 
/* 125 */       if (((BHInternalNode)this).lChild.isInside(paramBHNode.bHull)) {
/* 126 */         return ((BHInternalNode)this).lChild.findNode(paramBHNode);
/*     */       }
/*     */     } 
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void deleteFromParent() {
/* 136 */     BHInternalNode bHInternalNode = (BHInternalNode)this.parent;
/* 137 */     if (bHInternalNode != null) {
/* 138 */       if (bHInternalNode.rChild == this) {
/* 139 */         bHInternalNode.rChild = null;
/* 140 */       } else if (bHInternalNode.lChild == this) {
/* 141 */         bHInternalNode.lChild = null;
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BHNode deleteAndUpdateMarkedNodes() {
/* 162 */     if (this.mark == true) {
/* 163 */       if (this.nodeType == 2) {
/* 164 */         deleteFromParent();
/* 165 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 174 */       if (((BHInternalNode)this).rChild != null) {
/* 175 */         ((BHInternalNode)this).rChild = ((BHInternalNode)this).rChild.deleteAndUpdateMarkedNodes();
/*     */       }
/* 177 */       if (((BHInternalNode)this).lChild != null) {
/* 178 */         ((BHInternalNode)this).lChild = ((BHInternalNode)this).lChild.deleteAndUpdateMarkedNodes();
/*     */       }
/*     */       
/* 181 */       if (((BHInternalNode)this).rChild == null && ((BHInternalNode)this).lChild == null) {
/*     */         
/* 183 */         deleteFromParent();
/* 184 */         return null;
/*     */       } 
/* 186 */       if (((BHInternalNode)this).rChild == null) {
/* 187 */         BHNode bHNode = ((BHInternalNode)this).lChild;
/* 188 */         bHNode.parent = this.parent;
/*     */         
/* 190 */         deleteFromParent();
/* 191 */         return bHNode;
/* 192 */       }  if (((BHInternalNode)this).lChild == null) {
/* 193 */         BHNode bHNode = ((BHInternalNode)this).rChild;
/* 194 */         bHNode.parent = this.parent;
/*     */         
/* 196 */         deleteFromParent();
/* 197 */         return bHNode;
/*     */       } 
/*     */       
/* 200 */       combineBHull(((BHInternalNode)this).rChild, ((BHInternalNode)this).lChild);
/*     */ 
/*     */       
/* 203 */       ((BHInternalNode)this).rChild.parent = this;
/* 204 */       ((BHInternalNode)this).lChild.parent = this;
/* 205 */       this.mark = false;
/* 206 */       return this;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int countNumberOfInternals() {
/* 220 */     if (this.nodeType == 2) {
/* 221 */       return 0;
/*     */     }
/* 223 */     return ((BHInternalNode)this).rChild.countNumberOfInternals() + ((BHInternalNode)this).lChild.countNumberOfInternals() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int countNumberOfLeaves() {
/* 230 */     if (this.nodeType == 2) {
/* 231 */       return 1;
/*     */     }
/* 233 */     return ((BHInternalNode)this).rChild.countNumberOfLeaves() + ((BHInternalNode)this).lChild.countNumberOfLeaves();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int computeMaxDepth(int paramInt) {
/* 241 */     if (this.nodeType == 2) {
/* 242 */       return paramInt;
/*     */     }
/* 244 */     int i = ((BHInternalNode)this).rChild.computeMaxDepth(paramInt + 1);
/* 245 */     int j = ((BHInternalNode)this).lChild.computeMaxDepth(paramInt + 1);
/* 246 */     if (i > j)
/* 247 */       return i; 
/* 248 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   float computeAverageLeafDepth(int paramInt1, int paramInt2) {
/* 254 */     int i = computeSumOfDepths(0);
/* 255 */     return i / paramInt1;
/*     */   }
/*     */   
/*     */   int computeSumOfDepths(int paramInt) {
/* 259 */     if (this.nodeType == 2) {
/* 260 */       return paramInt;
/*     */     }
/* 262 */     return ((BHInternalNode)this).rChild.computeSumOfDepths(paramInt + 1) + ((BHInternalNode)this).lChild.computeSumOfDepths(paramInt + 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\BHNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */