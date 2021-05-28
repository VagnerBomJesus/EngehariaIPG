/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.RotationInterpolator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RotationInterpolatorState
/*    */   extends TransformInterpolatorState
/*    */ {
/* 62 */   public RotationInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 67 */     super.writeObject(paramDataOutput);
/*    */     
/* 69 */     RotationInterpolator rotationInterpolator = (RotationInterpolator)this.node;
/* 70 */     paramDataOutput.writeFloat(rotationInterpolator.getMinimumAngle());
/* 71 */     paramDataOutput.writeFloat(rotationInterpolator.getMaximumAngle());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 75 */     super.readObject(paramDataInput);
/*    */     
/* 77 */     RotationInterpolator rotationInterpolator = (RotationInterpolator)this.node;
/* 78 */     rotationInterpolator.setMinimumAngle(paramDataInput.readFloat());
/* 79 */     rotationInterpolator.setMaximumAngle(paramDataInput.readFloat());
/*    */   }
/*    */ 
/*    */   
/* 83 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class }, new Object[] { null, null }); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 88 */   protected SceneGraphObject createNode() { return new RotationInterpolator(null, null); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\RotationInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */