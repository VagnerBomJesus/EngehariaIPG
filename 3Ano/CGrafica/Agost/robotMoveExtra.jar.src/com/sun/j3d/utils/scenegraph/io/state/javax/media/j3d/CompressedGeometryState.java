/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.CompressedGeometry;
/*     */ import javax.media.j3d.CompressedGeometryHeader;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompressedGeometryState
/*     */   extends GeometryState
/*     */ {
/*     */   private byte[] bytes;
/*     */   private boolean isByReference;
/*     */   private CompressedGeometryHeader header;
/*     */   
/*  64 */   public CompressedGeometryState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  69 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  71 */     paramDataOutput.writeBoolean(((CompressedGeometry)this.node).isByReference());
/*     */     
/*  73 */     int i = ((CompressedGeometry)this.node).getByteCount();
/*  74 */     paramDataOutput.writeInt(i);
/*  75 */     this.bytes = new byte[i];
/*  76 */     ((CompressedGeometry)this.node).getCompressedGeometry(this.bytes);
/*  77 */     paramDataOutput.write(this.bytes);
/*     */     
/*  79 */     this.header = new CompressedGeometryHeader();
/*  80 */     ((CompressedGeometry)this.node).getCompressedGeometryHeader(this.header);
/*  81 */     writeCompressedGeometryHeader(paramDataOutput);
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  85 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  87 */     this.isByReference = paramDataInput.readBoolean();
/*  88 */     this.bytes = new byte[paramDataInput.readInt()];
/*  89 */     paramDataInput.readFully(this.bytes);
/*     */     
/*  91 */     this.header = new CompressedGeometryHeader();
/*  92 */     readCompressedGeometryHeader(paramDataInput);
/*     */   }
/*     */   
/*     */   private void writeCompressedGeometryHeader(DataOutput paramDataOutput) throws IOException {
/*  96 */     paramDataOutput.writeInt(this.header.majorVersionNumber);
/*  97 */     paramDataOutput.writeInt(this.header.minorVersionNumber);
/*  98 */     paramDataOutput.writeInt(this.header.minorMinorVersionNumber);
/*  99 */     paramDataOutput.writeInt(this.header.bufferType);
/* 100 */     paramDataOutput.writeInt(this.header.bufferDataPresent);
/* 101 */     paramDataOutput.writeInt(this.header.size);
/* 102 */     paramDataOutput.writeInt(this.header.start);
/* 103 */     if (this.header.lowerBound == null) {
/* 104 */       this.control.writePoint3d(paramDataOutput, new Point3d(-1.0D, -1.0D, -1.0D));
/*     */     } else {
/* 106 */       this.control.writePoint3d(paramDataOutput, this.header.lowerBound);
/*     */     } 
/* 108 */     if (this.header.upperBound == null) {
/* 109 */       this.control.writePoint3d(paramDataOutput, new Point3d(1.0D, 1.0D, 1.0D));
/*     */     } else {
/* 111 */       this.control.writePoint3d(paramDataOutput, this.header.upperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readCompressedGeometryHeader(DataInput paramDataInput) throws IOException {
/* 116 */     this.header.majorVersionNumber = paramDataInput.readInt();
/* 117 */     this.header.minorVersionNumber = paramDataInput.readInt();
/* 118 */     this.header.minorMinorVersionNumber = paramDataInput.readInt();
/* 119 */     this.header.bufferType = paramDataInput.readInt();
/* 120 */     this.header.bufferDataPresent = paramDataInput.readInt();
/* 121 */     this.header.size = paramDataInput.readInt();
/* 122 */     this.header.start = paramDataInput.readInt();
/* 123 */     this.header.lowerBound = this.control.readPoint3d(paramDataInput);
/* 124 */     if (this.header.lowerBound.x == -1.0D && this.header.lowerBound.y == -1.0D && this.header.lowerBound.z == -1.0D)
/*     */     {
/*     */       
/* 127 */       this.header.lowerBound = null;
/*     */     }
/* 129 */     this.header.upperBound = this.control.readPoint3d(paramDataInput);
/* 130 */     if (this.header.upperBound.x == 1.0D && this.header.upperBound.y == 1.0D && this.header.upperBound.z == 1.0D)
/*     */     {
/*     */       
/* 133 */       this.header.upperBound = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 139 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { CompressedGeometryHeader.class, this.bytes.getClass(), boolean.class }, new Object[] { this.header, this.bytes, new Boolean(this.isByReference) }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   protected SceneGraphObject createNode() { return new CompressedGeometry(this.header, this.bytes, this.isByReference); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\CompressedGeometryState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */