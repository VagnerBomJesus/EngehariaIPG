/*    */ package com.sun.j3d.utils.scenegraph.io.retained;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ class PositionInputStream
/*    */   extends InputStream
/*    */ {
/*    */   private long pos;
/*    */   private InputStream stream;
/*    */   
/*    */   public PositionInputStream(InputStream paramInputStream) {
/* 52 */     this.pos = 0L;
/*    */ 
/*    */ 
/*    */     
/* 56 */     this.stream = paramInputStream;
/*    */   }
/*    */   
/*    */   public int read() throws IOException {
/* 60 */     this.pos++;
/* 61 */     return this.stream.read();
/*    */   }
/*    */   
/*    */   public int read(byte[] paramArrayOfByte) throws IOException {
/* 65 */     int i = this.stream.read(paramArrayOfByte);
/* 66 */     this.pos += i;
/* 67 */     return i;
/*    */   }
/*    */   
/*    */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException {
/* 71 */     int i = this.stream.read(paramArrayOfByte, paramInt1, paramInt2);
/* 72 */     this.pos += i;
/* 73 */     return i;
/*    */   }
/*    */   
/*    */   public long skip(long paramLong) throws IOException {
/* 77 */     long l = this.stream.skip(paramLong);
/* 78 */     this.pos += l;
/* 79 */     return l;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void seekForward(long paramLong) throws IOException {
/* 87 */     if (this.pos > paramLong) {
/* 88 */       throw new SGIORuntimeException("Seeking Backward " + this.pos + "  " + paramLong);
/*    */     }
/* 90 */     this.stream.skip((int)(paramLong - this.pos));
/*    */     
/* 92 */     this.pos = paramLong;
/*    */   }
/*    */ 
/*    */   
/* 96 */   public long getFilePointer() { return this.pos; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\retained\PositionInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */