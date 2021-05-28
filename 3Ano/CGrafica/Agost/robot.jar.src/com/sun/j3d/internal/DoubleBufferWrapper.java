/*     */ package com.sun.j3d.internal;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.DoubleBuffer;
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
/*     */ public class DoubleBufferWrapper
/*     */   extends BufferWrapper
/*     */ {
/*     */   private DoubleBuffer buffer;
/*     */   
/*     */   public DoubleBufferWrapper(DoubleBuffer paramDoubleBuffer) {
/*  63 */     this.buffer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.buffer = paramDoubleBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleBufferWrapper(J3DBuffer paramJ3DBuffer) {
/*     */     this.buffer = null;
/*  78 */     this.buffer = (DoubleBuffer)paramJ3DBuffer.getBuffer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Buffer getBuffer() { return this.buffer; }
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
/*  97 */   public boolean isDirect() { return this.buffer.isDirect(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public double get() { return this.buffer.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public double get(int paramInt) { return this.buffer.get(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleBufferWrapper get(double[] paramArrayOfDouble) {
/* 122 */     this.buffer.get(paramArrayOfDouble);
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleBufferWrapper get(double[] paramArrayOfDouble, int paramInt1, int paramInt2) {
/* 132 */     this.buffer.get(paramArrayOfDouble, paramInt1, paramInt2);
/* 133 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleBufferWrapper put(double[] paramArrayOfDouble) {
/* 141 */     this.buffer.put(paramArrayOfDouble);
/* 142 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public J3DBuffer getJ3DBuffer() { return new J3DBuffer(this.buffer); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\internal\DoubleBufferWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */