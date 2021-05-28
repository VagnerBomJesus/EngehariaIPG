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
/*    */ class DebugOutput
/*    */ {
/*    */   int validOutput;
/*    */   static final int TRACE = 1;
/*    */   static final int VALUES = 2;
/*    */   static final int MISC = 4;
/*    */   static final int LINE_TRACE = 8;
/*    */   static final int NONE = 0;
/*    */   static final int EXCEPTION = 16;
/*    */   static final int TIME = 32;
/*    */   static final int WARNING = 64;
/*    */   
/*    */   DebugOutput(int paramInt) {
/* 54 */     this.validOutput = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     this.validOutput = paramInt;
/*    */   }
/*    */ 
/*    */   
/* 63 */   void setValidOutput(int paramInt) { this.validOutput = paramInt; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   int getValidOutput() { return this.validOutput; }
/*    */ 
/*    */   
/*    */   void print(int paramInt, String paramString) {
/* 71 */     if ((paramInt & this.validOutput) > 0) {
/* 72 */       System.out.print(paramString);
/*    */     }
/*    */   }
/*    */   
/*    */   void println(int paramInt, String paramString) {
/* 77 */     if ((paramInt & this.validOutput) > 0)
/* 78 */       System.out.println(paramString); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\DebugOutput.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */