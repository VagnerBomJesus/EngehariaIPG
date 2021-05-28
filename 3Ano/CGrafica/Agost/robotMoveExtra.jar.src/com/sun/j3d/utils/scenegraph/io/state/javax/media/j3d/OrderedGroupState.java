/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.OrderedGroup;
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
/*    */ public class OrderedGroupState
/*    */   extends GroupState
/*    */ {
/* 58 */   public OrderedGroupState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/*    */     
/* 64 */     int[] arrayOfInt = ((OrderedGroup)this.node).getChildIndexOrder();
/* 65 */     paramDataOutput.writeInt(arrayOfInt.length);
/* 66 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/* 67 */       paramDataOutput.writeInt(arrayOfInt[b]);
/*    */     }
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 72 */     super.readObject(paramDataInput);
/*    */     
/* 74 */     int[] arrayOfInt = new int[paramDataInput.readInt()];
/* 75 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/* 76 */       arrayOfInt[b] = paramDataInput.readInt();
/*    */     }
/* 78 */     ((OrderedGroup)this.node).setChildIndexOrder(arrayOfInt);
/*    */   }
/*    */ 
/*    */   
/* 82 */   protected SceneGraphObject createNode() { return new OrderedGroup(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\OrderedGroupState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */