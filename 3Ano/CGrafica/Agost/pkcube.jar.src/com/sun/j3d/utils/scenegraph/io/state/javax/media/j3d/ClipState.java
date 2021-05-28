/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.BoundingLeaf;
/*    */ import javax.media.j3d.Clip;
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
/*    */ public abstract class ClipState
/*    */   extends LeafState
/*    */ {
/*    */   private int boundingLeaf;
/*    */   
/*    */   public ClipState(SymbolTableData paramSymbolTableData, Controller paramController) {
/* 61 */     super(paramSymbolTableData, paramController);
/*    */     
/* 63 */     if (this.node != null)
/* 64 */       this.boundingLeaf = paramController.getSymbolTable().addReference(((Clip)this.node).getApplicationBoundingLeaf()); 
/*    */   }
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 68 */     super.writeObject(paramDataOutput);
/* 69 */     paramDataOutput.writeInt(this.boundingLeaf);
/* 70 */     this.control.writeBounds(paramDataOutput, ((Clip)this.node).getApplicationBounds());
/* 71 */     paramDataOutput.writeDouble(((Clip)this.node).getBackDistance());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 75 */     super.readObject(paramDataInput);
/* 76 */     this.boundingLeaf = paramDataInput.readInt();
/* 77 */     ((Clip)this.node).setApplicationBounds(this.control.readBounds(paramDataInput));
/* 78 */     ((Clip)this.node).setBackDistance(paramDataInput.readDouble());
/*    */   }
/*    */   
/*    */   public void buildGraph() {
/* 82 */     ((Clip)this.node).setApplicationBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.boundingLeaf));
/* 83 */     super.buildGraph();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ClipState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */