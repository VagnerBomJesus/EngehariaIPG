/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.Transform3D;
/*    */ import javax.media.j3d.TransformGroup;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransformGroupState
/*    */   extends GroupState
/*    */ {
/* 59 */   public TransformGroupState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 63 */     super.writeObject(paramDataOutput);
/* 64 */     Transform3D transform3D = new Transform3D();
/* 65 */     ((TransformGroup)this.node).getTransform(transform3D);
/* 66 */     double[] arrayOfDouble = new double[16];
/* 67 */     transform3D.get(arrayOfDouble);
/*    */     
/* 69 */     for (byte b = 0; b < 16; b++) {
/* 70 */       paramDataOutput.writeDouble(arrayOfDouble[b]);
/*    */     }
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 75 */     super.readObject(paramDataInput);
/* 76 */     Transform3D transform3D = new Transform3D();
/* 77 */     double[] arrayOfDouble = new double[16];
/*    */     
/* 79 */     for (byte b = 0; b < 16; b++) {
/* 80 */       arrayOfDouble[b] = paramDataInput.readDouble();
/*    */     }
/* 82 */     transform3D.set(arrayOfDouble);
/* 83 */     ((TransformGroup)this.node).setTransform(transform3D);
/*    */   }
/*    */ 
/*    */   
/* 87 */   protected SceneGraphObject createNode() { return new TransformGroup(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TransformGroupState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */