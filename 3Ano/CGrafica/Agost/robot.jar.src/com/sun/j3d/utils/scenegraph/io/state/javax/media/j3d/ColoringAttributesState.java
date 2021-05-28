/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.ColoringAttributes;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.vecmath.Color3f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColoringAttributesState
/*    */   extends NodeComponentState
/*    */ {
/* 59 */   public ColoringAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 63 */     super.writeObject(paramDataOutput);
/* 64 */     ColoringAttributes coloringAttributes = (ColoringAttributes)this.node;
/* 65 */     Color3f color3f = new Color3f();
/* 66 */     coloringAttributes.getColor(color3f);
/* 67 */     this.control.writeColor3f(paramDataOutput, color3f);
/* 68 */     paramDataOutput.writeInt(coloringAttributes.getShadeModel());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 72 */     super.readObject(paramDataInput);
/* 73 */     ColoringAttributes coloringAttributes = (ColoringAttributes)this.node;
/* 74 */     coloringAttributes.setColor(this.control.readColor3f(paramDataInput));
/* 75 */     coloringAttributes.setShadeModel(paramDataInput.readInt());
/*    */   }
/*    */ 
/*    */   
/* 79 */   protected SceneGraphObject createNode() { return new ColoringAttributes(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ColoringAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */