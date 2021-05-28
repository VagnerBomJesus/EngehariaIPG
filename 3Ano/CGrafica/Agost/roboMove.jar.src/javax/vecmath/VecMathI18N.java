/*    */ package javax.vecmath;
/*    */ 
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class VecMathI18N
/*    */ {
/*    */   static String getString(String paramString) {
/*    */     String str;
/*    */     try {
/* 23 */       str = ResourceBundle.getBundle("javax.vecmath.ExceptionStrings").getString(paramString);
/*    */     }
/* 25 */     catch (MissingResourceException missingResourceException) {
/* 26 */       System.err.println("VecMathI18N: Error looking up: " + paramString);
/* 27 */       str = paramString;
/*    */     } 
/* 29 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\VecMathI18N.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */