/*     */ package com.sun.j3d.internal;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
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
/*     */ public class ByteBufferWrapper
/*     */   extends BufferWrapper
/*     */ {
/*     */   private ByteBuffer buffer;
/*     */   
/*     */   public ByteBufferWrapper(ByteBuffer paramByteBuffer) {
/*  64 */     this.buffer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     this.buffer = paramByteBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBufferWrapper(J3DBuffer paramJ3DBuffer) {
/*     */     this.buffer = null;
/*  79 */     this.buffer = (ByteBuffer)paramJ3DBuffer.getBuffer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteBufferWrapper allocateDirect(int paramInt) {
/*  88 */     ByteBuffer byteBuffer = ByteBuffer.allocateDirect(paramInt);
/*  89 */     return new ByteBufferWrapper(byteBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public Buffer getBuffer() { return this.buffer; }
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
/* 108 */   public boolean isDirect() { return this.buffer.isDirect(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public byte get() { return this.buffer.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public byte get(int paramInt) { return this.buffer.get(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBufferWrapper get(byte[] paramArrayOfByte) {
/* 133 */     this.buffer.get(paramArrayOfByte);
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBufferWrapper get(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
/* 143 */     this.buffer.get(paramArrayOfByte, paramInt1, paramInt2);
/* 144 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOrderWrapper order() {
/* 151 */     if (this.buffer.order() == ByteOrder.BIG_ENDIAN) return ByteOrderWrapper.BIG_ENDIAN; 
/* 152 */     return ByteOrderWrapper.LITTLE_ENDIAN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBufferWrapper order(ByteOrderWrapper paramByteOrderWrapper) {
/* 160 */     if (paramByteOrderWrapper == ByteOrderWrapper.BIG_ENDIAN) { this.buffer.order(ByteOrder.BIG_ENDIAN); }
/* 161 */     else { this.buffer.order(ByteOrder.LITTLE_ENDIAN); }
/* 162 */      return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public FloatBufferWrapper asFloatBuffer() { return new FloatBufferWrapper(this.buffer.asFloatBuffer()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public DoubleBufferWrapper asDoubleBuffer() { return new DoubleBufferWrapper(this.buffer.asDoubleBuffer()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBufferWrapper put(byte[] paramArrayOfByte) {
/* 186 */     this.buffer.put(paramArrayOfByte);
/* 187 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public J3DBuffer getJ3DBuffer() { return new J3DBuffer(this.buffer); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\internal\ByteBufferWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */