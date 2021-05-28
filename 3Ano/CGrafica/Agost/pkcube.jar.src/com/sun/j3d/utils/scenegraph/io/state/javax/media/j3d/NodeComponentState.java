/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.NodeComponent;
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
/*    */ public abstract class NodeComponentState
/*    */   extends SceneGraphObjectState
/*    */ {
/* 59 */   public NodeComponentState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 63 */     super.readObject(paramDataInput);
/*    */ 
/*    */     
/* 66 */     if (this.control.getCurrentFileVersion() > 1) {
/* 67 */       ((NodeComponent)this.node).setDuplicateOnCloneTree(paramDataInput.readBoolean());
/*    */     }
/*    */   }
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 72 */     super.writeObject(paramDataOutput);
/* 73 */     paramDataOutput.writeBoolean(((NodeComponent)this.node).getDuplicateOnCloneTree());
/*    */   }
/*    */   
/*    */   public void addSubReference() {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\NodeComponentState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */