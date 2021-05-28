/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.RenderingAttributes;
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
/*    */ public class RenderingAttributesState
/*    */   extends NodeComponentState
/*    */ {
/* 58 */   public RenderingAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/* 63 */     RenderingAttributes renderingAttributes = (RenderingAttributes)this.node;
/* 64 */     paramDataOutput.writeInt(renderingAttributes.getAlphaTestFunction());
/* 65 */     paramDataOutput.writeFloat(renderingAttributes.getAlphaTestValue());
/* 66 */     paramDataOutput.writeBoolean(renderingAttributes.getDepthBufferEnable());
/* 67 */     paramDataOutput.writeBoolean(renderingAttributes.getDepthBufferWriteEnable());
/* 68 */     paramDataOutput.writeBoolean(renderingAttributes.getIgnoreVertexColors());
/* 69 */     paramDataOutput.writeInt(renderingAttributes.getRasterOp());
/* 70 */     paramDataOutput.writeBoolean(renderingAttributes.getRasterOpEnable());
/* 71 */     paramDataOutput.writeBoolean(renderingAttributes.getVisible());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 75 */     super.readObject(paramDataInput);
/* 76 */     RenderingAttributes renderingAttributes = (RenderingAttributes)this.node;
/* 77 */     renderingAttributes.setAlphaTestFunction(paramDataInput.readInt());
/* 78 */     renderingAttributes.setAlphaTestValue(paramDataInput.readFloat());
/* 79 */     renderingAttributes.setDepthBufferEnable(paramDataInput.readBoolean());
/* 80 */     renderingAttributes.setDepthBufferWriteEnable(paramDataInput.readBoolean());
/* 81 */     renderingAttributes.setIgnoreVertexColors(paramDataInput.readBoolean());
/* 82 */     renderingAttributes.setRasterOp(paramDataInput.readInt());
/* 83 */     renderingAttributes.setRasterOpEnable(paramDataInput.readBoolean());
/* 84 */     renderingAttributes.setVisible(paramDataInput.readBoolean());
/*    */   }
/*    */ 
/*    */   
/* 88 */   protected SceneGraphObject createNode() { return new RenderingAttributes(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\RenderingAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */