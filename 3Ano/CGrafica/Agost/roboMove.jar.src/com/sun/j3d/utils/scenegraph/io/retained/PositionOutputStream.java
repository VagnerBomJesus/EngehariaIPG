/*    */ package com.sun.j3d.utils.scenegraph.io.retained;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ class PositionOutputStream
/*    */   extends OutputStream
/*    */ {
/*    */   private long pos;
/*    */   private OutputStream stream;
/*    */   
/*    */   public PositionOutputStream(OutputStream paramOutputStream) {
/* 52 */     this.pos = 0L;
/*    */ 
/*    */ 
/*    */     
/* 56 */     this.stream = paramOutputStream;
/*    */   }
/*    */   
/*    */   public void write(int paramInt) throws IOException {
/* 60 */     this.pos++;
/* 61 */     this.stream.write(paramInt);
/*    */   }
/*    */   
/*    */   public void write(byte[] paramArrayOfByte) throws IOException {
/* 65 */     this.pos += paramArrayOfByte.length;
/* 66 */     this.stream.write(paramArrayOfByte);
/*    */   }
/*    */   
/*    */   public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException {
/* 70 */     this.pos += paramInt2;
/* 71 */     this.stream.write(paramArrayOfByte, paramInt1, paramInt2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void seekForward(long paramLong) throws IOException {
/* 79 */     if (this.pos > paramLong) {
/* 80 */       throw new SGIORuntimeException("Seeking Backward " + this.pos + "  " + paramLong);
/*    */     }
/* 82 */     for (byte b = 0; b < (int)(paramLong - this.pos); b++) {
/* 83 */       this.stream.write(0);
/*    */     }
/* 85 */     this.pos = paramLong;
/*    */   }
/*    */ 
/*    */   
/* 89 */   public long getFilePointer() { return this.pos; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\retained\PositionOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */