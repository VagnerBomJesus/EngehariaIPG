/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.TriangleStripArray;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TriangleStripArrayState
/*    */   extends GeometryStripArrayState
/*    */ {
/* 62 */   public TriangleStripArrayState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
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
/*    */   
/* 76 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class, int.class, this.texCoordSetMap.getClass(), this.stripVertexCounts.getClass() }, new Object[] { new Integer(this.vertexCount), new Integer(this.vertexFormat), new Integer(this.texCoordSetCount), this.texCoordSetMap, this.stripVertexCounts }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 91 */   protected SceneGraphObject createNode() { return new TriangleStripArray(this.vertexCount, this.vertexFormat, this.texCoordSetCount, this.texCoordSetMap, this.stripVertexCounts); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TriangleStripArrayState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */