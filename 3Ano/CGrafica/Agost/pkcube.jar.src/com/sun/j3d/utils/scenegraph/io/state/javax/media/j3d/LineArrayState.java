/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import javax.media.j3d.LineArray;
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
/*    */ public class LineArrayState
/*    */   extends GeometryArrayState
/*    */ {
/* 55 */   public LineArrayState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class, int.class, this.texCoordSetMap.getClass() }, new Object[] { new Integer(this.vertexCount), new Integer(this.vertexFormat), new Integer(this.texCoordSetCount), this.texCoordSetMap }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   protected SceneGraphObject createNode() { return new LineArray(this.vertexCount, this.vertexFormat, this.texCoordSetCount, this.texCoordSetMap); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\LineArrayState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */