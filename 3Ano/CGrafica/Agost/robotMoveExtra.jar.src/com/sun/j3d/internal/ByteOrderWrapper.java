/*    */ package com.sun.j3d.internal;
/*    */ 
/*    */ import java.nio.ByteOrder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ByteOrderWrapper
/*    */ {
/*    */   private final String enum_name;
/*    */   
/* 74 */   private ByteOrderWrapper(String paramString) { this.enum_name = paramString; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 80 */   public static final ByteOrderWrapper BIG_ENDIAN = new ByteOrderWrapper("BIG_ENDIAN");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 86 */   public static final ByteOrderWrapper LITTLE_ENDIAN = new ByteOrderWrapper("LITTLE_ENDIAN");
/*    */ 
/*    */ 
/*    */   
/* 90 */   public String toString() { return this.enum_name; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ByteOrderWrapper nativeOrder() {
/* 97 */     if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
/* 98 */       return BIG_ENDIAN; 
/* 99 */     return LITTLE_ENDIAN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\internal\ByteOrderWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */