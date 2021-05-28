/*     */ package com.sun.j3d.utils.scenegraph.io;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.RandomAccessFileControl;
/*     */ import com.sun.j3d.utils.universe.ConfiguredUniverse;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
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
/*     */ public class SceneGraphFileReader
/*     */ {
/*     */   private RandomAccessFileControl fileControl;
/*     */   
/*     */   public SceneGraphFileReader(File paramFile) throws IOException {
/*  71 */     this.fileControl = new RandomAccessFileControl();
/*  72 */     this.fileControl.openFile(paramFile);
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
/*  87 */   public ConfiguredUniverse readUniverse(boolean paramBoolean) throws IOException { return this.fileControl.readUniverse(paramBoolean, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setClassLoader(ClassLoader paramClassLoader) { this.fileControl.setClassLoader(paramClassLoader); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public ClassLoader getClassLoader() { return this.fileControl.getClassLoader(); }
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
/* 118 */   public ConfiguredUniverse readUniverse(boolean paramBoolean, Canvas3D paramCanvas3D) throws IOException { return this.fileControl.readUniverse(paramBoolean, paramCanvas3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public Object readUserData() throws IOException { return this.fileControl.getUserData(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public String readDescription() throws IOException { return this.fileControl.readFileDescription(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public int getBranchGraphCount() { return this.fileControl.getBranchGraphCount(); }
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
/* 157 */   public BranchGroup[] readBranchGraph(int paramInt) throws IOException { return this.fileControl.readBranchGraph(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public BranchGroup[] readAllBranchGraphs() throws IOException { return this.fileControl.readAllBranchGraphs(); }
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
/* 178 */   public void dereferenceBranchGraph(BranchGroup paramBranchGroup) { throw new RuntimeException("Not implemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public int getBranchGraphPosition(BranchGroup paramBranchGroup) { return this.fileControl.getBranchGraphPosition(paramBranchGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public Object readBranchGraphUserData(int paramInt) throws IOException { return this.fileControl.readBranchGraphUserData(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public String[] getNames() { return this.fileControl.getNames(); }
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
/* 214 */   public SceneGraphObject getNamedObject(String paramString) throws NamedObjectException, ObjectNotLoadedException { return this.fileControl.getNamedObject(paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public void close() throws IOException { this.fileControl.close(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\SceneGraphFileReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */