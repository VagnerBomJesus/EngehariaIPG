/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.DirectionalLight;
/*    */ import javax.media.j3d.SceneGraphObject;
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
/*    */ public class DirectionalLightState
/*    */   extends LightState
/*    */ {
/* 56 */   public DirectionalLightState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 61 */     super.writeObject(paramDataOutput);
/* 62 */     Vector3f vector3f = new Vector3f();
/* 63 */     ((DirectionalLight)this.node).getDirection(vector3f);
/*    */     
/* 65 */     this.control.writeVector3f(paramDataOutput, vector3f);
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 69 */     super.readObject(paramDataInput);
/* 70 */     ((DirectionalLight)this.node).setDirection(this.control.readVector3f(paramDataInput));
/*    */   }
/*    */ 
/*    */   
/* 74 */   protected SceneGraphObject createNode() { return new DirectionalLight(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\DirectionalLightState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */