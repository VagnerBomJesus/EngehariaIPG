/*    */ package javax.media.j3d;
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
/*    */ class J3dI18N
/*    */ {
/*    */   static String getString(String paramString) {
/*    */     String str;
/*    */     try {
/* 23 */       str = ResourceBundle.getBundle("javax.media.j3d.ExceptionStrings").getString(paramString);
/*    */     }
/* 25 */     catch (MissingResourceException missingResourceException) {
/* 26 */       System.err.println("J3dI18N: Error looking up: " + paramString);
/* 27 */       str = paramString;
/*    */     } 
/* 29 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\J3dI18N.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */