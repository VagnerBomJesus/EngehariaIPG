/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.DepthComponentNative;
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
/*    */ 
/*    */ public class DepthComponentNativeState
/*    */   extends NodeComponentState
/*    */ {
/*    */   private int height;
/*    */   private int width;
/*    */   
/* 66 */   public DepthComponentNativeState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 70 */     super.writeConstructorParams(paramDataOutput);
/* 71 */     paramDataOutput.writeInt(this.width);
/* 72 */     paramDataOutput.writeInt(this.height);
/*    */   }
/*    */   
/*    */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 76 */     super.readConstructorParams(paramDataInput);
/*    */     
/* 78 */     this.width = paramDataInput.readInt();
/* 79 */     this.height = paramDataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/* 83 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class }, new Object[] { new Integer(this.width), new Integer(this.height) }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 90 */   protected SceneGraphObject createNode() { return new DepthComponentNative(this.width, this.height); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\DepthComponentNativeState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */