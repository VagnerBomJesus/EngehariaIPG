/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.PolygonAttributes;
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
/*    */ public class PolygonAttributesState
/*    */   extends NodeComponentState
/*    */ {
/* 58 */   public PolygonAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/* 63 */     PolygonAttributes polygonAttributes = (PolygonAttributes)this.node;
/* 64 */     paramDataOutput.writeBoolean(polygonAttributes.getBackFaceNormalFlip());
/* 65 */     paramDataOutput.writeInt(polygonAttributes.getCullFace());
/* 66 */     paramDataOutput.writeInt(polygonAttributes.getPolygonMode());
/* 67 */     paramDataOutput.writeFloat(polygonAttributes.getPolygonOffset());
/* 68 */     paramDataOutput.writeFloat(polygonAttributes.getPolygonOffsetFactor());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 72 */     super.readObject(paramDataInput);
/* 73 */     PolygonAttributes polygonAttributes = (PolygonAttributes)this.node;
/* 74 */     polygonAttributes.setBackFaceNormalFlip(paramDataInput.readBoolean());
/* 75 */     polygonAttributes.setCullFace(paramDataInput.readInt());
/* 76 */     polygonAttributes.setPolygonMode(paramDataInput.readInt());
/* 77 */     polygonAttributes.setPolygonOffset(paramDataInput.readFloat());
/* 78 */     polygonAttributes.setPolygonOffsetFactor(paramDataInput.readFloat());
/*    */   }
/*    */ 
/*    */   
/* 82 */   protected SceneGraphObject createNode() { return new PolygonAttributes(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\PolygonAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */