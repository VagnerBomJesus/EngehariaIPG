/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.BoundingLeaf;
/*    */ import javax.media.j3d.SceneGraphObject;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BoundingLeafState
/*    */   extends SceneGraphObjectState
/*    */ {
/* 64 */   public BoundingLeafState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 70 */     super.writeObject(paramDataOutput);
/*    */     
/* 72 */     this.control.writeBounds(paramDataOutput, ((BoundingLeaf)this.node).getRegion());
/*    */   }
/*    */ 
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 77 */     super.readObject(paramDataInput);
/*    */     
/* 79 */     ((BoundingLeaf)this.node).setRegion(this.control.readBounds(paramDataInput));
/*    */   }
/*    */ 
/*    */   
/* 83 */   protected SceneGraphObject createNode() { return new BoundingLeaf(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\BoundingLeafState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */