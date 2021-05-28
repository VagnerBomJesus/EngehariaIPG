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
/*    */ 
/*    */ class ListNode
/*    */ {
/*    */   int index;
/*    */   int prev;
/*    */   int next;
/*    */   int convex;
/*    */   int vcntIndex;
/*    */   
/*    */   ListNode(int paramInt) {
/* 72 */     this.index = paramInt;
/* 73 */     this.prev = -1;
/* 74 */     this.next = -1;
/* 75 */     this.convex = 0;
/* 76 */     this.vcntIndex = -1;
/*    */   }
/*    */ 
/*    */   
/* 80 */   void setCommonIndex(int paramInt) { this.vcntIndex = paramInt; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   int getCommonIndex() { return this.vcntIndex; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\ListNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */