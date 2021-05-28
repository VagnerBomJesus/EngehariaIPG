/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.IndexedGeometryStripArray;
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
/*    */ public abstract class IndexedGeometryStripArrayState
/*    */   extends IndexedGeometryArrayState
/*    */ {
/*    */   protected int[] stripIndexCounts;
/*    */   
/* 59 */   public IndexedGeometryStripArrayState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 64 */     super.writeConstructorParams(paramDataOutput);
/*    */     
/* 66 */     this.stripIndexCounts = new int[((IndexedGeometryStripArray)this.node).getNumStrips()];
/* 67 */     ((IndexedGeometryStripArray)this.node).getStripIndexCounts(this.stripIndexCounts);
/*    */     
/* 69 */     paramDataOutput.writeInt(this.stripIndexCounts.length);
/* 70 */     for (byte b = 0; b < this.stripIndexCounts.length; b++) {
/* 71 */       paramDataOutput.writeInt(this.stripIndexCounts[b]);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 76 */     super.readConstructorParams(paramDataInput);
/* 77 */     this.stripIndexCounts = new int[paramDataInput.readInt()];
/* 78 */     for (byte b = 0; b < this.stripIndexCounts.length; b++)
/* 79 */       this.stripIndexCounts[b] = paramDataInput.readInt(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\IndexedGeometryStripArrayState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */