/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.LOD;
/*    */ import javax.media.j3d.Switch;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LODState
/*    */   extends BehaviorState
/*    */ {
/*    */   private int[] switches;
/*    */   
/*    */   public LODState(SymbolTableData paramSymbolTableData, Controller paramController) {
/* 61 */     super(paramSymbolTableData, paramController);
/*    */     
/* 63 */     if (this.node != null) {
/* 64 */       this.switches = new int[((LOD)this.node).numSwitches()];
/* 65 */       for (byte b = 0; b < this.switches.length; b++)
/* 66 */         this.switches[b] = paramController.getSymbolTable().addReference(((LOD)this.node).getSwitch(b)); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 71 */     super.writeObject(paramDataOutput);
/*    */     
/* 73 */     paramDataOutput.writeInt(this.switches.length);
/* 74 */     for (byte b = 0; b < this.switches.length; b++)
/* 75 */       paramDataOutput.writeInt(this.switches[b]); 
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 79 */     super.readObject(paramDataInput);
/* 80 */     LOD lOD = (LOD)this.node;
/*    */     
/* 82 */     this.switches = new int[paramDataInput.readInt()];
/* 83 */     for (byte b = 0; b < this.switches.length; b++)
/* 84 */       this.switches[b] = paramDataInput.readInt(); 
/*    */   }
/*    */   
/*    */   public void buildGraph() {
/* 88 */     LOD lOD = (LOD)this.node;
/* 89 */     for (byte b = 0; b < this.switches.length; b++)
/* 90 */       lOD.addSwitch((Switch)this.control.getSymbolTable().getJ3dNode(this.switches[b])); 
/* 91 */     super.buildGraph();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\LODState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */