/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.LineAttributes;
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
/*    */ public class LineAttributesState
/*    */   extends NodeComponentState
/*    */ {
/* 58 */   public LineAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/* 63 */     LineAttributes lineAttributes = (LineAttributes)this.node;
/* 64 */     paramDataOutput.writeBoolean(lineAttributes.getLineAntialiasingEnable());
/* 65 */     paramDataOutput.writeInt(lineAttributes.getLinePattern());
/* 66 */     paramDataOutput.writeFloat(lineAttributes.getLineWidth());
/* 67 */     paramDataOutput.writeInt(lineAttributes.getPatternMask());
/* 68 */     paramDataOutput.writeInt(lineAttributes.getPatternScaleFactor());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 72 */     super.readObject(paramDataInput);
/* 73 */     LineAttributes lineAttributes = (LineAttributes)this.node;
/* 74 */     lineAttributes.setLineAntialiasingEnable(paramDataInput.readBoolean());
/* 75 */     lineAttributes.setLinePattern(paramDataInput.readInt());
/* 76 */     lineAttributes.setLineWidth(paramDataInput.readFloat());
/* 77 */     lineAttributes.setPatternMask(paramDataInput.readInt());
/* 78 */     lineAttributes.setPatternScaleFactor(paramDataInput.readInt());
/*    */   }
/*    */ 
/*    */   
/* 82 */   protected SceneGraphObject createNode() { return new LineAttributes(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\LineAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */