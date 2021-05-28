/*    */ package com.sun.j3d.loaders.lw3d;
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
/*    */ class ParserObject
/*    */ {
/*    */   static final int TRACE = 1;
/*    */   static final int VALUES = 2;
/*    */   static final int MISC = 4;
/*    */   static final int LINE_TRACE = 8;
/*    */   static final int NONE = 0;
/*    */   static final int EXCEPTION = 16;
/*    */   static final int TIME = 32;
/*    */   static final int WARNING = 64;
/* 64 */   protected DebugOutput debugPrinter = new DebugOutput(16);
/*    */ 
/*    */   
/*    */   ParserObject(int paramInt) {
/* 68 */     this();
/* 69 */     this.debugPrinter.setValidOutput(paramInt);
/*    */   }
/*    */   ParserObject() {}
/*    */   
/*    */   protected void debugOutputLn(int paramInt, String paramString) {
/* 74 */     if (paramString.equals("")) {
/* 75 */       this.debugPrinter.println(paramInt, paramString);
/*    */     } else {
/* 77 */       this.debugPrinter.println(paramInt, getClass().getName() + "::" + paramString);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 82 */   protected void debugOutput(int paramInt, String paramString) { this.debugPrinter.print(paramInt, paramString); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\lw3d\ParserObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */