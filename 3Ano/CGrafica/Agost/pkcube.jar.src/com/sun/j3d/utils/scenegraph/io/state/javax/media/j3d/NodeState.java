/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.Node;
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
/*    */ public class NodeState
/*    */   extends SceneGraphObjectState
/*    */ {
/* 58 */   public NodeState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 64 */     super.writeObject(paramDataOutput);
/*    */     
/* 66 */     this.control.writeBounds(paramDataOutput, ((Node)this.node).getBounds());
/*    */     
/* 68 */     paramDataOutput.writeBoolean(((Node)this.node).getPickable());
/* 69 */     paramDataOutput.writeBoolean(((Node)this.node).getCollidable());
/* 70 */     paramDataOutput.writeBoolean(((Node)this.node).getBoundsAutoCompute());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 76 */     super.readObject(paramDataInput);
/*    */     
/* 78 */     ((Node)this.node).setBounds(this.control.readBounds(paramDataInput));
/*    */     
/* 80 */     ((Node)this.node).setPickable(paramDataInput.readBoolean());
/* 81 */     ((Node)this.node).setCollidable(paramDataInput.readBoolean());
/* 82 */     ((Node)this.node).setBoundsAutoCompute(paramDataInput.readBoolean());
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\NodeState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */