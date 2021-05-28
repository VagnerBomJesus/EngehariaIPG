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
/*    */ class Edge
/*    */ {
/*    */   public int v1;
/*    */   public int v2;
/*    */   private static final int HASHCONST = -305419897;
/*    */   
/* 57 */   public int hashCode() { return this.v1 * -305419897 << 2 ^ this.v2 * -305419897; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 62 */     if (!(paramObject instanceof Edge)) return false; 
/* 63 */     Edge edge = (Edge)paramObject;
/* 64 */     return (this.v1 == edge.v1 && this.v2 == edge.v2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public String toString() { return "(" + this.v1 + ", " + this.v2 + ")"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public Edge(int paramInt1, int paramInt2) {
/* 74 */     this.v1 = paramInt1;
/* 75 */     this.v2 = paramInt2;
/*    */   }
/*    */ 
/*    */   
/*    */   public Edge(Edge paramEdge) {
/* 80 */     this.v1 = paramEdge.v1;
/* 81 */     this.v2 = paramEdge.v2;
/*    */   }
/*    */   
/*    */   public Edge() {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\Edge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */