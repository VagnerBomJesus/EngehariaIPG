/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NullSceneGraphObjectState
/*     */   extends SceneGraphObjectState
/*     */ {
/*     */   SymbolTableData symbolTableData;
/*     */   
/*     */   public NullSceneGraphObjectState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  71 */     super(null, paramController);
/*  72 */     this.symbolTableData = new SymbolTableData(-1, null, this, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public SceneGraphObject getNode() { return null; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public int getNodeID() { return -1; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public SymbolTableData getSymbol() { return this.symbolTableData; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   protected SceneGraphObject createNode() { return null; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\NullSceneGraphObjectState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */