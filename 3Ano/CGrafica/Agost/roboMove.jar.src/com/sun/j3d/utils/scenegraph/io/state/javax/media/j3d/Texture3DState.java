/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.Texture3D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Texture3DState
/*    */   extends TextureState
/*    */ {
/*    */   private int depth;
/*    */   
/* 60 */   public Texture3DState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 64 */     super.writeObject(paramDataOutput);
/* 65 */     paramDataOutput.writeInt(((Texture3D)this.node).getBoundaryModeR());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 69 */     super.readObject(paramDataInput);
/* 70 */     ((Texture3D)this.node).setBoundaryModeR(paramDataInput.readInt());
/*    */   }
/*    */   
/*    */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 74 */     super.writeConstructorParams(paramDataOutput);
/* 75 */     paramDataOutput.writeInt(((Texture3D)this.node).getDepth());
/*    */   }
/*    */   
/*    */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 79 */     super.readConstructorParams(paramDataInput);
/* 80 */     this.depth = paramDataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/* 84 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(this.mipMapMode), new Integer(this.format), new Integer(this.width), new Integer(this.height), new Integer(this.depth), new Integer(this.boundaryWidth) }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 99 */   protected SceneGraphObject createNode() { return new Texture3D(this.mipMapMode, this.format, this.width, this.height, this.depth, this.boundaryWidth); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\Texture3DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */