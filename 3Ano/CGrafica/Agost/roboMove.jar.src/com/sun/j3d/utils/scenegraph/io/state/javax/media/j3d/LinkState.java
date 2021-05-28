/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.Link;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.SharedGroup;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LinkState
/*    */   extends LeafState
/*    */ {
/*    */   private int sharedGroup;
/*    */   private SymbolTableData sharedGroupSymbol;
/*    */   
/*    */   public LinkState(SymbolTableData paramSymbolTableData, Controller paramController) {
/* 62 */     super(paramSymbolTableData, paramController);
/* 63 */     if (this.node != null) {
/* 64 */       SharedGroup sharedGroup1 = ((Link)this.node).getSharedGroup();
/* 65 */       this.sharedGroup = paramController.getSymbolTable().addReference(sharedGroup1);
/* 66 */       this.sharedGroupSymbol = paramController.getSymbolTable().getSymbol(this.sharedGroup);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 71 */     super.writeObject(paramDataOutput);
/*    */     
/* 73 */     if (this.sharedGroupSymbol.nodeState == null) {
/* 74 */       paramDataOutput.writeInt(-1);
/* 75 */       SharedGroup sharedGroup1 = ((Link)this.node).getSharedGroup();
/* 76 */       this.control.writeSharedGroup(paramDataOutput, sharedGroup1, this.sharedGroupSymbol);
/*    */     } else {
/* 78 */       paramDataOutput.writeInt(this.sharedGroupSymbol.nodeID);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 83 */     super.readObject(paramDataInput);
/* 84 */     this.sharedGroup = paramDataInput.readInt();
/*    */     
/* 86 */     if (this.sharedGroup == -1) {
/* 87 */       this.sharedGroup = this.control.readSharedGroup(paramDataInput);
/*    */     }
/*    */   }
/*    */   
/*    */   public void buildGraph() {
/* 92 */     this.sharedGroupSymbol = this.control.getSymbolTable().getSymbol(this.sharedGroup);
/* 93 */     ((SharedGroupState)this.sharedGroupSymbol.nodeState).buildGraph();
/* 94 */     ((Link)this.node).setSharedGroup((SharedGroup)this.sharedGroupSymbol.j3dNode);
/* 95 */     super.buildGraph();
/*    */   }
/*    */ 
/*    */   
/* 99 */   protected SceneGraphObject createNode() { return new Link(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\LinkState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */