/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.OrientedShape3D;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.vecmath.Point3f;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrientedShape3DState
/*    */   extends Shape3DState
/*    */ {
/* 60 */   public OrientedShape3DState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 65 */     super.writeObject(paramDataOutput);
/*    */     
/* 67 */     paramDataOutput.writeInt(((OrientedShape3D)this.node).getAlignmentMode());
/*    */     
/* 69 */     Vector3f vector3f = new Vector3f();
/* 70 */     ((OrientedShape3D)this.node).getAlignmentAxis(vector3f);
/*    */     
/* 72 */     Point3f point3f = new Point3f();
/* 73 */     ((OrientedShape3D)this.node).getRotationPoint(point3f);
/*    */     
/* 75 */     this.control.writeVector3f(paramDataOutput, vector3f);
/* 76 */     this.control.writePoint3f(paramDataOutput, point3f);
/* 77 */     paramDataOutput.writeBoolean(((OrientedShape3D)this.node).getConstantScaleEnable());
/* 78 */     paramDataOutput.writeDouble(((OrientedShape3D)this.node).getScale());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 82 */     super.readObject(paramDataInput);
/*    */     
/* 84 */     ((OrientedShape3D)this.node).setAlignmentMode(paramDataInput.readInt());
/* 85 */     ((OrientedShape3D)this.node).setAlignmentAxis(this.control.readVector3f(paramDataInput));
/* 86 */     ((OrientedShape3D)this.node).setRotationPoint(this.control.readPoint3f(paramDataInput));
/* 87 */     ((OrientedShape3D)this.node).setConstantScaleEnable(paramDataInput.readBoolean());
/* 88 */     ((OrientedShape3D)this.node).setScale(paramDataInput.readDouble());
/*    */   }
/*    */ 
/*    */   
/* 92 */   protected SceneGraphObject createNode() { return new OrientedShape3D(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\OrientedShape3DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */