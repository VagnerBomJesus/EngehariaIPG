/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.SpotLight;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpotLightState
/*    */   extends LightState
/*    */ {
/* 56 */   public SpotLightState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 61 */     super.writeObject(paramDataOutput);
/* 62 */     Vector3f vector3f = new Vector3f();
/* 63 */     ((SpotLight)this.node).getDirection(vector3f);
/* 64 */     this.control.writeVector3f(paramDataOutput, vector3f);
/*    */     
/* 66 */     paramDataOutput.writeFloat(((SpotLight)this.node).getSpreadAngle());
/* 67 */     paramDataOutput.writeFloat(((SpotLight)this.node).getConcentration());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 71 */     super.readObject(paramDataInput);
/* 72 */     ((SpotLight)this.node).setDirection(this.control.readVector3f(paramDataInput));
/* 73 */     ((SpotLight)this.node).setSpreadAngle(paramDataInput.readFloat());
/* 74 */     ((SpotLight)this.node).setConcentration(paramDataInput.readFloat());
/*    */   }
/*    */ 
/*    */   
/* 78 */   protected SceneGraphObject createNode() { return new SpotLight(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\SpotLightState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */