/*    */ package com.sun.j3d.utils.scenegraph.transparency;
/*    */ 
/*    */ import java.util.Comparator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleDistanceComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object paramObject1, Object paramObject2) {
/* 77 */     TransparencySortGeom transparencySortGeom1 = (TransparencySortGeom)paramObject1;
/* 78 */     TransparencySortGeom transparencySortGeom2 = (TransparencySortGeom)paramObject2;
/*    */     
/* 80 */     double d = transparencySortGeom1.getDistanceSquared() - transparencySortGeom2.getDistanceSquared();
/* 81 */     if (d < 0.0D)
/* 82 */       return -1; 
/* 83 */     if (d == 0.0D) {
/* 84 */       return 0;
/*    */     }
/* 86 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\transparency\SimpleDistanceComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */