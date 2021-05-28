/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.BitSet;
/*    */ import javax.media.j3d.SceneGraphObject;
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
/*    */ public class SwitchState
/*    */   extends GroupState
/*    */ {
/* 58 */   public SwitchState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/* 63 */     Switch switch = (Switch)this.node;
/* 64 */     this.control.writeSerializedData(paramDataOutput, switch.getChildMask());
/* 65 */     paramDataOutput.writeInt(switch.getWhichChild());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 69 */     super.readObject(paramDataInput);
/* 70 */     Switch switch = (Switch)this.node;
/*    */     
/* 72 */     switch.setChildMask((BitSet)this.control.readSerializedData(paramDataInput));
/* 73 */     switch.setWhichChild(paramDataInput.readInt());
/*    */   }
/*    */ 
/*    */   
/* 77 */   protected SceneGraphObject createNode() { return new Switch(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\SwitchState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */