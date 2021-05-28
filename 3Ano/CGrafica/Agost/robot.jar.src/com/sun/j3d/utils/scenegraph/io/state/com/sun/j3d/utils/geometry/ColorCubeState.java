/*    */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.geometry;
/*    */ 
/*    */ import com.sun.j3d.utils.geometry.ColorCube;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.Shape3DState;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.Shape3D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColorCubeState
/*    */   extends Shape3DState
/*    */ {
/* 58 */   private double scale = 1.0D;
/*    */ 
/*    */   
/* 61 */   public ColorCubeState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 66 */     super.writeConstructorParams(paramDataOutput);
/*    */     
/* 68 */     paramDataOutput.writeDouble(((ColorCube)this.node).getScale());
/*    */   }
/*    */ 
/*    */   
/*    */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 73 */     super.readConstructorParams(paramDataInput);
/*    */     
/* 75 */     this.scale = paramDataInput.readDouble();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   protected boolean processChildren() { return false; }
/*    */ 
/*    */ 
/*    */   
/* 88 */   public SceneGraphObject createNode(Class paramClass) { return (Shape3D)createNode(paramClass, new Class[] { double.class }, new Object[] { new Double(this.scale) }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 94 */   protected SceneGraphObject createNode() { return new ColorCube(this.scale); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\geometry\ColorCubeState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */