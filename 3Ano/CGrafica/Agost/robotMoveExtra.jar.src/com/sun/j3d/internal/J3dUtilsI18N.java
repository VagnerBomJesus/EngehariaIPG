/*    */ package com.sun.j3d.internal;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class J3dUtilsI18N
/*    */ {
/*    */   public static String getString(String paramString) {
/*    */     String str;
/*    */     try {
/* 55 */       str = ResourceBundle.getBundle("com.sun.j3d.ExceptionStrings").getString(paramString);
/*    */     }
/* 57 */     catch (MissingResourceException missingResourceException) {
/* 58 */       System.err.println("J3dUtilsI18N: Error looking up: " + paramString);
/* 59 */       str = paramString;
/*    */     } 
/* 61 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\internal\J3dUtilsI18N.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */