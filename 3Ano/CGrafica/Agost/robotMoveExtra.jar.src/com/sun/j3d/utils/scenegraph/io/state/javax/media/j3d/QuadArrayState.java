/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.QuadArray;
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
/*    */ public class QuadArrayState
/*    */   extends GeometryArrayState
/*    */ {
/* 62 */   public QuadArrayState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public void writeObject(DataOutput paramDataOutput) throws IOException { super.writeObject(paramDataOutput); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public void readObject(DataInput paramDataInput) throws IOException { super.readObject(paramDataInput); }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class }, new Object[] { new Integer(this.vertexCount), new Integer(this.vertexFormat) }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 82 */   protected SceneGraphObject createNode() { return new QuadArray(this.vertexCount, this.vertexFormat); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\QuadArrayState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */