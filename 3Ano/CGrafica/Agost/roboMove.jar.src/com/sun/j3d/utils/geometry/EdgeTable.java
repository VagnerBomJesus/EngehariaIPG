/*    */ package com.sun.j3d.utils.geometry;
/*    */ 
/*    */ import java.util.HashMap;
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
/*    */ class EdgeTable
/*    */ {
/*    */   private HashMap edgeTable;
/*    */   private static final int DEBUG = 0;
/*    */   
/* 61 */   Integer get(int paramInt1, int paramInt2) { return (Integer)this.edgeTable.get(new Edge(paramInt1, paramInt2)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   Integer get(Edge paramEdge) { return (Integer)this.edgeTable.get(paramEdge); }
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
/*    */   EdgeTable(int[] paramArrayOfInt) {
/* 80 */     this.edgeTable = new HashMap(paramArrayOfInt.length * 2);
/*    */ 
/*    */ 
/*    */     
/* 84 */     for (byte b = 0; b < paramArrayOfInt.length; b += 3) {
/*    */       
/* 86 */       for (byte b1 = 0; b1 < 3; b1++) {
/* 87 */         Edge edge = new Edge(paramArrayOfInt[b + b1], paramArrayOfInt[b + (b1 + 1) % 3]);
/*    */ 
/*    */         
/* 90 */         if (this.edgeTable.get(edge) == null)
/*    */         {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 98 */           this.edgeTable.put(edge, new Integer(b + (b1 + 2) % 3));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\EdgeTable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */