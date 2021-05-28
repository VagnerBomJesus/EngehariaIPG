/*    */ package javax.vecmath;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class VecMathUtil
/*    */ {
/*    */   static int floatToIntBits(float paramFloat) {
/* 41 */     if (paramFloat == 0.0F) {
/* 42 */       return 0;
/*    */     }
/*    */     
/* 45 */     return Float.floatToIntBits(paramFloat);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static long doubleToLongBits(double paramDouble) {
/* 70 */     if (paramDouble == 0.0D) {
/* 71 */       return 0L;
/*    */     }
/*    */     
/* 74 */     return Double.doubleToLongBits(paramDouble);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\VecMathUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */