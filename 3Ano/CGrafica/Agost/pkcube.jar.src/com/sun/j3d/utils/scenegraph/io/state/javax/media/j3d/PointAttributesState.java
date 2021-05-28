/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.PointAttributes;
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
/*    */ public class PointAttributesState
/*    */   extends NodeComponentState
/*    */ {
/* 58 */   public PointAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/* 63 */     PointAttributes pointAttributes = (PointAttributes)this.node;
/* 64 */     paramDataOutput.writeBoolean(pointAttributes.getPointAntialiasingEnable());
/* 65 */     paramDataOutput.writeFloat(pointAttributes.getPointSize());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 69 */     super.readObject(paramDataInput);
/* 70 */     PointAttributes pointAttributes = (PointAttributes)this.node;
/* 71 */     pointAttributes.setPointAntialiasingEnable(paramDataInput.readBoolean());
/* 72 */     pointAttributes.setPointSize(paramDataInput.readFloat());
/*    */   }
/*    */ 
/*    */   
/* 76 */   protected SceneGraphObject createNode() { return new PointAttributes(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\PointAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */