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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class OrderedChildInfo
/*    */ {
/* 23 */   static int ADD = 1;
/* 24 */   static int REMOVE = 2;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   int type;
/*    */ 
/*    */ 
/*    */   
/*    */   int orderedId;
/*    */ 
/*    */ 
/*    */   
/*    */   int childId;
/*    */ 
/*    */ 
/*    */   
/*    */   OrderedCollection value;
/*    */ 
/*    */ 
/*    */   
/*    */   OrderedChildInfo next;
/*    */ 
/*    */ 
/*    */   
/*    */   OrderedChildInfo prev;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   OrderedChildInfo(int paramInt1, int paramInt2, int paramInt3, OrderedCollection paramOrderedCollection) {
/* 55 */     this.type = paramInt1;
/* 56 */     this.orderedId = paramInt3;
/* 57 */     this.childId = paramInt2;
/* 58 */     this.value = paramOrderedCollection;
/* 59 */     this.prev = null;
/* 60 */     this.next = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\OrderedChildInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */