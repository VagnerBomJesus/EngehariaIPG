/*    */ package com.sun.j3d.utils.geometry;
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
/*    */ class HeapNode
/*    */ {
/*    */   int index;
/*    */   int prev;
/*    */   int next;
/*    */   double ratio;
/*    */   
/*    */   void copy(HeapNode paramHeapNode) {
/* 70 */     this.index = paramHeapNode.index;
/* 71 */     this.prev = paramHeapNode.prev;
/* 72 */     this.next = paramHeapNode.next;
/* 73 */     this.ratio = paramHeapNode.ratio;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\HeapNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */