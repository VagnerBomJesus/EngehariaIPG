/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.TransparencyAttributes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransparencyAttributesState
/*    */   extends NodeComponentState
/*    */ {
/* 59 */   public TransparencyAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 63 */     super.writeObject(paramDataOutput);
/* 64 */     TransparencyAttributes transparencyAttributes = (TransparencyAttributes)this.node;
/* 65 */     paramDataOutput.writeInt(transparencyAttributes.getDstBlendFunction());
/* 66 */     paramDataOutput.writeInt(transparencyAttributes.getSrcBlendFunction());
/* 67 */     paramDataOutput.writeFloat(transparencyAttributes.getTransparency());
/* 68 */     paramDataOutput.writeInt(transparencyAttributes.getTransparencyMode());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 72 */     super.readObject(paramDataInput);
/* 73 */     TransparencyAttributes transparencyAttributes = (TransparencyAttributes)this.node;
/* 74 */     transparencyAttributes.setDstBlendFunction(paramDataInput.readInt());
/* 75 */     transparencyAttributes.setSrcBlendFunction(paramDataInput.readInt());
/* 76 */     transparencyAttributes.setTransparency(paramDataInput.readFloat());
/* 77 */     transparencyAttributes.setTransparencyMode(paramDataInput.readInt());
/*    */   }
/*    */ 
/*    */   
/* 81 */   protected SceneGraphObject createNode() { return new TransparencyAttributes(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TransparencyAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */