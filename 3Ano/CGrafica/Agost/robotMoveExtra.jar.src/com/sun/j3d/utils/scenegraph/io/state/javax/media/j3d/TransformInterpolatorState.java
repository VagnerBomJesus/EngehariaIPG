/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.TransformGroup;
/*    */ import javax.media.j3d.TransformInterpolator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TransformInterpolatorState
/*    */   extends InterpolatorState
/*    */ {
/* 60 */   private int target = 0;
/*    */ 
/*    */   
/* 63 */   public TransformInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 67 */     super.writeObject(paramDataOutput);
/*    */     
/* 69 */     TransformInterpolator transformInterpolator = (TransformInterpolator)this.node;
/*    */     
/* 71 */     this.control.writeTransform3D(paramDataOutput, transformInterpolator.getTransformAxis());
/* 72 */     paramDataOutput.writeInt(this.control.getSymbolTable().addReference(transformInterpolator.getTarget()));
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 76 */     super.readObject(paramDataInput);
/*    */     
/* 78 */     TransformInterpolator transformInterpolator = (TransformInterpolator)this.node;
/* 79 */     transformInterpolator.setTransformAxis(this.control.readTransform3D(paramDataInput));
/* 80 */     this.target = paramDataInput.readInt();
/*    */   }
/*    */   
/*    */   public void buildGraph() {
/* 84 */     ((TransformInterpolator)this.node).setTarget((TransformGroup)this.control.getSymbolTable().getJ3dNode(this.target));
/*    */     
/* 86 */     super.buildGraph();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TransformInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */