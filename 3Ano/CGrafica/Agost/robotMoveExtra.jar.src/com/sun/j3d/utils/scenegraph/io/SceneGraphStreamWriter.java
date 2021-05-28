/*     */ package com.sun.j3d.utils.scenegraph.io;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.StreamControl;
/*     */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.DanglingReferenceException;
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
/*     */ public class SceneGraphStreamWriter
/*     */ {
/*     */   private StreamControl control;
/*     */   private DataOutputStream out;
/*     */   
/*     */   public SceneGraphStreamWriter(OutputStream paramOutputStream) throws IOException {
/*  71 */     this.out = new DataOutputStream(paramOutputStream);
/*  72 */     this.control = new StreamControl(this.out);
/*  73 */     this.control.writeStreamHeader();
/*     */   }
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
/*  98 */   public void writeUniverse(SimpleUniverse paramSimpleUniverse, boolean paramBoolean) throws IOException, UnsupportedUniverseException { this.control.writeUniverse(this.out, paramSimpleUniverse, paramBoolean); }
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
/*     */   public void writeBranchGraph(BranchGroup paramBranchGroup, HashMap paramHashMap) throws IOException, DanglingReferenceException, NamedObjectException {
/* 117 */     this.control.addNamedObjects(paramHashMap);
/* 118 */     this.control.writeBranchGraph(paramBranchGroup, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 125 */     this.control.close();
/* 126 */     this.out.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\SceneGraphStreamWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */