/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ class OrderedPath
/*    */ {
/* 18 */   ArrayList pathElements = new ArrayList(1);
/*    */ 
/*    */ 
/*    */   
/* 22 */   void addElementToPath(OrderedGroupRetained paramOrderedGroupRetained, Integer paramInteger) { this.pathElements.add(new OrderedPathElement(paramOrderedGroupRetained, paramInteger)); }
/*    */ 
/*    */   
/*    */   OrderedPath clonePath() {
/* 26 */     OrderedPath orderedPath = new OrderedPath();
/* 27 */     orderedPath.pathElements = (ArrayList)this.pathElements.clone();
/* 28 */     return orderedPath;
/*    */   }
/*    */   
/*    */   void printPath() {
/* 32 */     System.err.println("orderedPath: [");
/*    */     
/* 34 */     for (byte b = 0; b < this.pathElements.size(); b++) {
/* 35 */       OrderedPathElement orderedPathElement = (OrderedPathElement)this.pathElements.get(b);
/* 36 */       System.err.println("(" + orderedPathElement.orderedGroup + "," + orderedPathElement.childId);
/*    */     } 
/* 38 */     System.err.println("]");
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\OrderedPath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */