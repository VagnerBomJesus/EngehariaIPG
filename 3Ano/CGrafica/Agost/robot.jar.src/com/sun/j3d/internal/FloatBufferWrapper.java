/*     */ package com.sun.j3d.internal;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.FloatBuffer;
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
/*     */ public class FloatBufferWrapper
/*     */   extends BufferWrapper
/*     */ {
/*     */   private FloatBuffer buffer;
/*     */   
/*     */   public FloatBufferWrapper(FloatBuffer paramFloatBuffer) {
/*  63 */     this.buffer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.buffer = paramFloatBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FloatBufferWrapper(J3DBuffer paramJ3DBuffer) {
/*     */     this.buffer = null;
/*  78 */     this.buffer = (FloatBuffer)paramJ3DBuffer.getBuffer();
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
/* 105 */   public float get() { return this.buffer.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public float get(int paramInt) { return this.buffer.get(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FloatBufferWrapper get(float[] paramArrayOfFloat) {
/* 122 */     this.buffer.get(paramArrayOfFloat);
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FloatBufferWrapper get(float[] paramArrayOfFloat, int paramInt1, int paramInt2) {
/* 132 */     this.buffer.get(paramArrayOfFloat, paramInt1, paramInt2);
/* 133 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FloatBufferWrapper put(float[] paramArrayOfFloat) {
/* 141 */     this.buffer.put(paramArrayOfFloat);
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


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\internal\FloatBufferWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */