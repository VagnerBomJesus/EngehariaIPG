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
/*    */ class IntegerFreeList
/*    */   extends MemoryFreeList
/*    */ {
/* 17 */   int count = 0;
/*    */ 
/*    */ 
/*    */   
/* 21 */   IntegerFreeList() { super("java.lang.Integer"); }
/*    */ 
/*    */ 
/*    */   
/*    */   IntegerFreeList(int paramInt1, int paramInt2) {
/* 26 */     super("java.lang.Integer", paramInt2);
/* 27 */     this.count = paramInt1;
/*    */   }
/*    */   
/*    */   Object getObject() {
/* 31 */     if (this.size > 0) return removeLastElement(); 
/* 32 */     return new Integer(++this.count);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 36 */     super.clear();
/* 37 */     this.count = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\IntegerFreeList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */