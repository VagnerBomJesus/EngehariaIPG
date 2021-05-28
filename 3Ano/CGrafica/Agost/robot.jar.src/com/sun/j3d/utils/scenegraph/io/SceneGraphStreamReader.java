/*     */ package com.sun.j3d.utils.scenegraph.io;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.StreamControl;
/*     */ import com.sun.j3d.utils.universe.ConfiguredUniverse;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SceneGraphStreamReader
/*     */ {
/*     */   private StreamControl control;
/*     */   private DataInputStream in;
/*     */   
/*     */   public SceneGraphStreamReader(InputStream paramInputStream) throws IOException {
/*  68 */     this.in = new DataInputStream(paramInputStream);
/*  69 */     this.control = new StreamControl(this.in);
/*  70 */     this.control.readStreamHeader();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public ConfiguredUniverse readUniverse() throws IOException { return this.control.readUniverse(this.in, true, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public ConfiguredUniverse readUniverse(Canvas3D paramCanvas3D) throws IOException { return this.control.readUniverse(this.in, true, paramCanvas3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public BranchGroup readBranchGraph(HashMap paramHashMap) throws IOException { return this.control.readBranchGraph(paramHashMap); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setClassLoader(ClassLoader paramClassLoader) { this.control.setClassLoader(paramClassLoader); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public ClassLoader getClassLoader() { return this.control.getClassLoader(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 121 */     this.in.close();
/* 122 */     this.control.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\SceneGraphStreamReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */