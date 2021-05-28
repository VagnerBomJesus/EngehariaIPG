/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.IndexedGeometryArray;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class IndexedGeometryArrayState
/*     */   extends GeometryArrayState
/*     */ {
/*     */   protected int indexCount;
/*     */   
/*  61 */   public IndexedGeometryArrayState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  65 */     super.writeObject(paramDataOutput);
/*     */     
/*  67 */     int[] arrayOfInt = new int[((IndexedGeometryArray)this.node).getIndexCount()];
/*     */     
/*  69 */     boolean bool = ((this.vertexFormat & 0x200) != 0) ? 1 : 0;
/*     */     
/*  71 */     if (((this.vertexFormat & 0x4) != 0 || (this.vertexFormat & 0xC) != 0) && !bool) {
/*     */       
/*  73 */       ((IndexedGeometryArray)this.node).getColorIndices(0, arrayOfInt);
/*  74 */       writeIntArray(paramDataOutput, arrayOfInt);
/*     */     } 
/*     */     
/*  77 */     if ((this.vertexFormat & true) != 0) {
/*  78 */       ((IndexedGeometryArray)this.node).getCoordinateIndices(0, arrayOfInt);
/*  79 */       writeIntArray(paramDataOutput, arrayOfInt);
/*     */     } 
/*     */     
/*  82 */     if ((this.vertexFormat & 0x2) != 0 && !bool) {
/*  83 */       ((IndexedGeometryArray)this.node).getNormalIndices(0, arrayOfInt);
/*  84 */       writeIntArray(paramDataOutput, arrayOfInt);
/*     */     } 
/*     */     
/*  87 */     if (((this.vertexFormat & 0x20) != 0 || (this.vertexFormat & 0x40) != 0 || (this.vertexFormat & 0x400) != 0) && !bool)
/*     */     {
/*     */       
/*  90 */       for (byte b = 0; b < ((IndexedGeometryArray)this.node).getTexCoordSetCount(); b++) {
/*  91 */         ((IndexedGeometryArray)this.node).getTextureCoordinateIndices(b, 0, arrayOfInt);
/*  92 */         writeIntArray(paramDataOutput, arrayOfInt);
/*     */       } 
/*     */     }
/*     */     
/*  96 */     if (!(this.node instanceof javax.media.j3d.IndexedGeometryStripArray)) {
/*  97 */       paramDataOutput.writeInt(((IndexedGeometryArray)this.node).getValidIndexCount());
/*     */     }
/*  99 */     paramDataOutput.writeInt(((IndexedGeometryArray)this.node).getInitialIndexIndex());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/* 103 */     super.readObject(paramDataInput);
/*     */     
/* 105 */     int[] arrayOfInt = new int[this.indexCount];
/*     */     
/* 107 */     boolean bool = ((this.vertexFormat & 0x200) != 0) ? 1 : 0;
/*     */     
/* 109 */     if (((this.vertexFormat & 0x4) != 0 || (this.vertexFormat & 0xC) != 0) && !bool) {
/*     */       
/* 111 */       readIntArray(paramDataInput, arrayOfInt);
/* 112 */       ((IndexedGeometryArray)this.node).setColorIndices(0, arrayOfInt);
/*     */     } 
/*     */     
/* 115 */     if ((this.vertexFormat & true) != 0) {
/* 116 */       readIntArray(paramDataInput, arrayOfInt);
/* 117 */       ((IndexedGeometryArray)this.node).setCoordinateIndices(0, arrayOfInt);
/*     */     } 
/*     */     
/* 120 */     if ((this.vertexFormat & 0x2) != 0 && !bool) {
/* 121 */       readIntArray(paramDataInput, arrayOfInt);
/* 122 */       ((IndexedGeometryArray)this.node).setNormalIndices(0, arrayOfInt);
/*     */     } 
/*     */     
/* 125 */     if (((this.vertexFormat & 0x20) != 0 || (this.vertexFormat & 0x40) != 0 || (this.vertexFormat & 0x400) != 0) && !bool)
/*     */     {
/*     */       
/* 128 */       for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 129 */         readIntArray(paramDataInput, arrayOfInt);
/* 130 */         ((IndexedGeometryArray)this.node).setTextureCoordinateIndices(b, 0, arrayOfInt);
/*     */       } 
/*     */     }
/*     */     
/* 134 */     if (!(this.node instanceof javax.media.j3d.IndexedGeometryStripArray)) {
/* 135 */       ((IndexedGeometryArray)this.node).setValidIndexCount(paramDataInput.readInt());
/*     */     }
/* 137 */     ((IndexedGeometryArray)this.node).setInitialIndexIndex(paramDataInput.readInt());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 142 */     super.writeConstructorParams(paramDataOutput);
/* 143 */     paramDataOutput.writeInt(((IndexedGeometryArray)this.node).getIndexCount());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 148 */     super.readConstructorParams(paramDataInput);
/* 149 */     this.indexCount = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   protected void writeIntArray(DataOutput paramDataOutput, int[] paramArrayOfInt) throws IOException {
/* 153 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 154 */     DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
/*     */     
/* 156 */     for (byte b = 0; b < paramArrayOfInt.length; b++)
/* 157 */       dataOutputStream.writeInt(paramArrayOfInt[b]); 
/* 158 */     dataOutputStream.close();
/*     */     
/* 160 */     paramDataOutput.writeInt(byteArrayOutputStream.size());
/* 161 */     paramDataOutput.write(byteArrayOutputStream.toByteArray());
/*     */   }
/*     */   
/*     */   private void readIntArray(DataInput paramDataInput, int[] paramArrayOfInt) throws IOException {
/* 165 */     byte[] arrayOfByte = new byte[paramDataInput.readInt()];
/* 166 */     paramDataInput.readFully(arrayOfByte);
/* 167 */     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 168 */     DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
/*     */     
/* 170 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 171 */       paramArrayOfInt[b] = dataInputStream.readInt();
/*     */     }
/* 173 */     dataInputStream.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\IndexedGeometryArrayState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */