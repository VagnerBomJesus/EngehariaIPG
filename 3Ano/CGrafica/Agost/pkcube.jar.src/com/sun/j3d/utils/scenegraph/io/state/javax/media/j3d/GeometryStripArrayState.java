/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.GeometryStripArray;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class GeometryStripArrayState
/*    */   extends GeometryArrayState
/*    */ {
/*    */   protected int[] stripVertexCounts;
/*    */   
/* 59 */   public GeometryStripArrayState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 64 */     super.writeConstructorParams(paramDataOutput);
/*    */     
/* 66 */     this.stripVertexCounts = new int[((GeometryStripArray)this.node).getNumStrips()];
/* 67 */     ((GeometryStripArray)this.node).getStripVertexCounts(this.stripVertexCounts);
/*    */     
/* 69 */     paramDataOutput.writeInt(this.stripVertexCounts.length);
/* 70 */     for (byte b = 0; b < this.stripVertexCounts.length; b++) {
/* 71 */       paramDataOutput.writeInt(this.stripVertexCounts[b]);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 76 */     super.readConstructorParams(paramDataInput);
/* 77 */     this.stripVertexCounts = new int[paramDataInput.readInt()];
/* 78 */     for (byte b = 0; b < this.stripVertexCounts.length; b++)
/* 79 */       this.stripVertexCounts[b] = paramDataInput.readInt(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\GeometryStripArrayState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */