/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.Material;
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
/*    */ public class MaterialState
/*    */   extends NodeComponentState
/*    */ {
/* 59 */   public MaterialState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 63 */     super.writeObject(paramDataOutput);
/* 64 */     Material material = (Material)this.node;
/* 65 */     Color3f color3f = new Color3f();
/* 66 */     material.getAmbientColor(color3f);
/* 67 */     this.control.writeColor3f(paramDataOutput, color3f);
/* 68 */     material.getDiffuseColor(color3f);
/* 69 */     this.control.writeColor3f(paramDataOutput, color3f);
/* 70 */     material.getEmissiveColor(color3f);
/* 71 */     this.control.writeColor3f(paramDataOutput, color3f);
/* 72 */     material.getSpecularColor(color3f);
/* 73 */     this.control.writeColor3f(paramDataOutput, color3f);
/*    */     
/* 75 */     paramDataOutput.writeBoolean(material.getLightingEnable());
/* 76 */     paramDataOutput.writeInt(material.getColorTarget());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 80 */     super.readObject(paramDataInput);
/* 81 */     Material material = (Material)this.node;
/* 82 */     material.setAmbientColor(this.control.readColor3f(paramDataInput));
/* 83 */     material.setDiffuseColor(this.control.readColor3f(paramDataInput));
/* 84 */     material.setEmissiveColor(this.control.readColor3f(paramDataInput));
/* 85 */     material.setSpecularColor(this.control.readColor3f(paramDataInput));
/* 86 */     material.setLightingEnable(paramDataInput.readBoolean());
/* 87 */     material.setColorTarget(paramDataInput.readInt());
/*    */   }
/*    */ 
/*    */   
/* 91 */   protected SceneGraphObject createNode() { return new Material(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\MaterialState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */