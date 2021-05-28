/*    */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.behaviors.mouse;
/*    */ 
/*    */ import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.BehaviorState;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.TransformGroup;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MouseBehaviorState
/*    */   extends BehaviorState
/*    */ {
/* 61 */   private int target = 0;
/*    */ 
/*    */   
/* 64 */   public MouseBehaviorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 68 */     super.writeObject(paramDataOutput);
/*    */     
/* 70 */     paramDataOutput.writeInt(this.control.getSymbolTable().addReference(((MouseBehavior)this.node).getTransformGroup()));
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 74 */     super.readObject(paramDataInput);
/*    */     
/* 76 */     this.target = paramDataInput.readInt();
/*    */   }
/*    */   
/*    */   public void buildGraph() {
/* 80 */     ((MouseBehavior)this.node).setTransformGroup((TransformGroup)this.control.getSymbolTable().getJ3dNode(this.target));
/*    */ 
/*    */     
/* 83 */     super.buildGraph();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\behaviors\mouse\MouseBehaviorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */