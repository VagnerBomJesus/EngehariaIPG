/*     */ package com.sun.j3d.internal;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import javax.media.j3d.J3DBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BufferWrapper
/*     */ {
/*     */   public static final int TYPE_NULL = 0;
/*     */   public static final int TYPE_UNKNOWN = 1;
/*     */   public static final int TYPE_BYTE = 2;
/*     */   public static final int TYPE_FLOAT = 3;
/*     */   public static final int TYPE_DOUBLE = 4;
/*     */   
/*     */   abstract Buffer getBuffer();
/*     */   
/* 109 */   public Object getBufferAsObject() { return getBuffer(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public int capacity() { return getBuffer().capacity(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int limit() { return getBuffer().limit(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public int position() { return getBuffer().position(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferWrapper position(int paramInt) {
/* 142 */     getBuffer().position(paramInt);
/* 143 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferWrapper rewind() {
/* 152 */     getBuffer().rewind();
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBufferType(J3DBuffer paramJ3DBuffer) {
/*     */     byte b;
/* 167 */     Buffer buffer = paramJ3DBuffer.getBuffer();
/*     */     
/* 169 */     if (buffer == null) {
/* 170 */       b = 0;
/*     */     }
/* 172 */     else if (buffer instanceof java.nio.ByteBuffer) {
/* 173 */       b = 2;
/*     */     }
/* 175 */     else if (buffer instanceof java.nio.FloatBuffer) {
/* 176 */       b = 3;
/*     */     }
/* 178 */     else if (buffer instanceof java.nio.DoubleBuffer) {
/* 179 */       b = 4;
/*     */     } else {
/*     */       
/* 182 */       b = 1;
/*     */     } 
/* 184 */     return b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\internal\BufferWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */