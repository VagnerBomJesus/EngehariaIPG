/*     */ package com.sun.j3d.utils.scenegraph.io.retained;
/*     */ 
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ public class J3fOutputStream
/*     */   implements DataOutput
/*     */ {
/*     */   private PositionOutputStream positionOutputStream;
/*     */   private DataOutputStream dataOutputStream;
/*     */   
/*     */   public J3fOutputStream(OutputStream paramOutputStream) {
/*  60 */     this.positionOutputStream = new PositionOutputStream(paramOutputStream);
/*  61 */     this.dataOutputStream = new DataOutputStream(this.positionOutputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public void seekForward(long paramLong) throws IOException { this.positionOutputStream.seekForward(paramLong); }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public long getFilePointer() { return this.positionOutputStream.getFilePointer(); }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException { this.dataOutputStream.write(paramArrayOfByte, paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void writeFloat(float paramFloat) throws IOException { this.dataOutputStream.writeFloat(paramFloat); }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void write(int paramInt) throws IOException { this.dataOutputStream.write(paramInt); }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void writeShort(int paramInt) throws IOException { this.dataOutputStream.writeShort(paramInt); }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void writeBytes(String paramString) throws IOException { this.dataOutputStream.writeBytes(paramString); }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void writeChar(int paramInt) throws IOException { this.dataOutputStream.writeChar(paramInt); }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public void writeByte(int paramInt) throws IOException { this.dataOutputStream.writeByte(paramInt); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void writeLong(long paramLong) throws IOException { this.dataOutputStream.writeLong(paramLong); }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void writeBoolean(boolean paramBoolean) throws IOException { this.dataOutputStream.writeBoolean(paramBoolean); }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void writeUTF(String paramString) throws IOException { this.dataOutputStream.writeUTF(paramString); }
/*     */ 
/*     */ 
/*     */   
/* 117 */   public void writeInt(int paramInt) throws IOException { this.dataOutputStream.writeInt(paramInt); }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void writeChars(String paramString) throws IOException { this.dataOutputStream.writeChars(paramString); }
/*     */ 
/*     */ 
/*     */   
/* 125 */   public void write(byte[] paramArrayOfByte) throws IOException { this.dataOutputStream.write(paramArrayOfByte); }
/*     */ 
/*     */ 
/*     */   
/* 129 */   public void writeDouble(double paramDouble) throws IOException { this.dataOutputStream.writeDouble(paramDouble); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\retained\J3fOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */