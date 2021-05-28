/*     */ package com.sun.j3d.utils.scenegraph.io.retained;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public class J3fInputStream
/*     */   implements DataInput
/*     */ {
/*     */   private PositionInputStream positionInputStream;
/*     */   private DataInputStream dataInputStream;
/*     */   
/*     */   public J3fInputStream(InputStream paramInputStream) {
/*  60 */     this.positionInputStream = new PositionInputStream(paramInputStream);
/*  61 */     this.dataInputStream = new DataInputStream(this.positionInputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public void seekForward(long paramLong) throws IOException { this.positionInputStream.seekForward(paramLong); }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public long getFilePointer() { return this.positionInputStream.getFilePointer(); }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public int readUnsignedShort() throws IOException { return this.dataInputStream.readUnsignedShort(); }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void readFully(byte[] paramArrayOfByte) throws IOException { this.dataInputStream.readFully(paramArrayOfByte); }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public char readChar() throws IOException { return this.dataInputStream.readChar(); }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public int readUnsignedByte() throws IOException { return this.dataInputStream.readUnsignedByte(); }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public int readInt() throws IOException { return this.dataInputStream.readInt(); }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public short readShort() throws IOException { return this.dataInputStream.readShort(); }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public float readFloat() throws IOException { return this.dataInputStream.readFloat(); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException { this.dataInputStream.readFully(paramArrayOfByte, paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public boolean readBoolean() throws IOException { return this.dataInputStream.readBoolean(); }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public int skipBytes(int paramInt) throws IOException { return this.dataInputStream.skipBytes(paramInt); }
/*     */ 
/*     */ 
/*     */   
/* 117 */   public double readDouble() throws IOException { return this.dataInputStream.readDouble(); }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public long readLong() { return this.dataInputStream.readLong(); }
/*     */ 
/*     */ 
/*     */   
/* 125 */   public String readLine() throws IOException { return this.dataInputStream.readLine(); }
/*     */ 
/*     */ 
/*     */   
/* 129 */   public byte readByte() throws IOException { return this.dataInputStream.readByte(); }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public String readUTF() throws IOException { return this.dataInputStream.readUTF(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\retained\J3fInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */