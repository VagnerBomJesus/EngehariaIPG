/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.PointLight;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.vecmath.Point3f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PointLightState
/*    */   extends LightState
/*    */ {
/* 56 */   public PointLightState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 61 */     super.writeObject(paramDataOutput);
/* 62 */     Point3f point3f = new Point3f();
/* 63 */     ((PointLight)this.node).getAttenuation(point3f);
/* 64 */     this.control.writePoint3f(paramDataOutput, point3f);
/* 65 */     ((PointLight)this.node).getPosition(point3f);
/* 66 */     this.control.writePoint3f(paramDataOutput, point3f);
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 70 */     super.readObject(paramDataInput);
/* 71 */     ((PointLight)this.node).setAttenuation(this.control.readPoint3f(paramDataInput));
/* 72 */     ((PointLight)this.node).setPosition(this.control.readPoint3f(paramDataInput));
/*    */   }
/*    */ 
/*    */   
/* 76 */   protected SceneGraphObject createNode() { return new PointLight(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\PointLightState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */