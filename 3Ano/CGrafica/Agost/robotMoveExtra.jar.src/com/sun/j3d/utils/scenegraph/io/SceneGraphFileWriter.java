/*     */ package com.sun.j3d.utils.scenegraph.io;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.RandomAccessFileControl;
/*     */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import javax.media.j3d.BranchGroup;
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
/*     */ public class SceneGraphFileWriter
/*     */ {
/*     */   private RandomAccessFileControl fileControl;
/*     */   private File file;
/*     */   
/*     */   public SceneGraphFileWriter(File paramFile, SimpleUniverse paramSimpleUniverse, boolean paramBoolean, String paramString, Serializable paramSerializable) throws IOException, UnsupportedUniverseException {
/* 105 */     this.fileControl = new RandomAccessFileControl();
/* 106 */     this.file = paramFile;
/* 107 */     paramFile.createNewFile();
/*     */     
/* 109 */     if (!paramFile.canWrite()) {
/* 110 */       throw new IOException("Can not Write to File");
/*     */     }
/* 112 */     this.fileControl.createFile(paramFile, paramSimpleUniverse, paramBoolean, paramString, paramSerializable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void writeBranchGraph(BranchGroup paramBranchGroup) throws IOException { writeBranchGraph(paramBranchGroup, null); }
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
/* 134 */   public void writeBranchGraph(BranchGroup paramBranchGroup, Serializable paramSerializable) throws IOException { this.fileControl.writeBranchGraph(paramBranchGroup, paramSerializable); }
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
/* 147 */   public void addObjectName(String paramString, SceneGraphObject paramSceneGraphObject) throws NamedObjectException { this.fileControl.addNamedObject(paramString, paramSceneGraphObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void close() throws IOException { this.fileControl.close(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\SceneGraphFileWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */