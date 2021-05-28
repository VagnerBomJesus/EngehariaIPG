/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.ViewPlatform;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ViewPlatformState
/*    */   extends LeafState
/*    */ {
/*    */   private int[] geometry;
/*    */   private int appearance;
/*    */   
/* 61 */   public ViewPlatformState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 65 */     super.writeObject(paramDataOutput);
/*    */     
/* 67 */     paramDataOutput.writeFloat(((ViewPlatform)this.node).getActivationRadius());
/* 68 */     paramDataOutput.writeInt(((ViewPlatform)this.node).getViewAttachPolicy());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 72 */     super.readObject(paramDataInput);
/*    */     
/* 74 */     ((ViewPlatform)this.node).setActivationRadius(paramDataInput.readFloat());
/* 75 */     ((ViewPlatform)this.node).setViewAttachPolicy(paramDataInput.readInt());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 80 */   protected SceneGraphObject createNode() { return new ViewPlatform(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ViewPlatformState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */