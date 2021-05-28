/*    */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.geometry;
/*    */ 
/*    */ import com.sun.j3d.utils.geometry.Primitive;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.GroupState;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
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
/*    */ public class PrimitiveState
/*    */   extends GroupState
/*    */ {
/*    */   protected int primflags;
/*    */   
/* 58 */   public PrimitiveState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 63 */     super.writeConstructorParams(paramDataOutput);
/*    */     
/* 65 */     paramDataOutput.writeInt(((Primitive)this.node).getPrimitiveFlags());
/*    */   }
/*    */   
/*    */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 69 */     super.readConstructorParams(paramDataInput);
/*    */     
/* 71 */     this.primflags = paramDataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/* 75 */   public void buildGraph() { super.buildGraph(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   protected boolean processChildren() { return false; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\geometry\PrimitiveState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */