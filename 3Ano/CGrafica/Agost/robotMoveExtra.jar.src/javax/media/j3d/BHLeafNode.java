/*    */ package javax.media.j3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class BHLeafNode
/*    */   extends BHNode
/*    */ {
/*    */   BHLeafInterface leafIF;
/*    */   
/*    */   BHLeafNode() {
/* 23 */     this.nodeType = 2;
/* 24 */     this.leafIF = null;
/*    */   }
/*    */   
/*    */   BHLeafNode(BHNode paramBHNode) {
/* 28 */     super(paramBHNode);
/* 29 */     this.nodeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   BHLeafNode(BHLeafInterface paramBHLeafInterface) {
/* 34 */     this.nodeType = 2;
/* 35 */     this.leafIF = paramBHLeafInterface;
/*    */   }
/*    */   
/*    */   BHLeafNode(BHNode paramBHNode, BHLeafInterface paramBHLeafInterface) {
/* 39 */     super(paramBHNode);
/* 40 */     this.leafIF = paramBHLeafInterface;
/* 41 */     this.nodeType = 2;
/*    */   }
/*    */   
/*    */   BHLeafNode(BHNode paramBHNode, BoundingBox paramBoundingBox) {
/* 45 */     super(paramBHNode, paramBoundingBox);
/* 46 */     this.nodeType = 2;
/*    */   }
/*    */   
/*    */   BHLeafNode(BHNode paramBHNode, BHLeafInterface paramBHLeafInterface, BoundingBox paramBoundingBox) {
/* 50 */     super(paramBHNode, paramBoundingBox);
/* 51 */     this.leafIF = paramBHLeafInterface;
/* 52 */     this.nodeType = 2;
/*    */   }
/*    */ 
/*    */   
/* 56 */   void computeBoundingHull() { this.bHull = this.leafIF.computeBoundingHull(); }
/*    */ 
/*    */ 
/*    */   
/*    */   void updateMarkedBoundingHull() {
/* 61 */     if (!this.mark) {
/*    */       return;
/*    */     }
/* 64 */     computeBoundingHull();
/* 65 */     this.mark = false;
/*    */   }
/*    */ 
/*    */   
/* 69 */   boolean isEnable() { return this.leafIF.isEnable(); }
/*    */ 
/*    */ 
/*    */   
/* 73 */   boolean isEnable(int paramInt) { return this.leafIF.isEnable(paramInt); }
/*    */ 
/*    */ 
/*    */   
/* 77 */   Locale getLocale() { return this.leafIF.getLocale2(); }
/*    */ 
/*    */   
/*    */   void destroyTree(BHNode[] paramArrayOfBHNode, int[] paramArrayOfInt) {
/* 81 */     if (paramArrayOfBHNode.length <= paramArrayOfInt[0]) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 86 */     this.parent = null;
/* 87 */     paramArrayOfBHNode[paramArrayOfInt[0]] = this;
/* 88 */     paramArrayOfInt[0] = paramArrayOfInt[0] + 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\BHLeafNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */