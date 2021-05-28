/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.PositionInterpolator;
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
/*    */ public class PositionInterpolatorState
/*    */   extends TransformInterpolatorState
/*    */ {
/* 61 */   public PositionInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 65 */     super.writeObject(paramDataOutput);
/*    */     
/* 67 */     paramDataOutput.writeFloat(((PositionInterpolator)this.node).getStartPosition());
/* 68 */     paramDataOutput.writeFloat(((PositionInterpolator)this.node).getEndPosition());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 72 */     super.readObject(paramDataInput);
/*    */     
/* 74 */     ((PositionInterpolator)this.node).setStartPosition(paramDataInput.readFloat());
/* 75 */     ((PositionInterpolator)this.node).setEndPosition(paramDataInput.readFloat());
/*    */   }
/*    */ 
/*    */   
/* 79 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class }, new Object[] { null, null }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 86 */   protected SceneGraphObject createNode() { return new PositionInterpolator(null, null); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\PositionInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */