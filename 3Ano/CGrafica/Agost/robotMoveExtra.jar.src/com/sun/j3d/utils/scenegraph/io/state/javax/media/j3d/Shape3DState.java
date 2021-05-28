/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Geometry;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Shape3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Shape3DState
/*     */   extends LeafState
/*     */ {
/*     */   private int[] geometry;
/*     */   private int appearance;
/*     */   
/*     */   public Shape3DState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  63 */     super(paramSymbolTableData, paramController);
/*     */     
/*  65 */     if (this.node != null) {
/*  66 */       this.appearance = paramController.getSymbolTable().addReference(((Shape3D)this.node).getAppearance());
/*  67 */       int i = ((Shape3D)this.node).numGeometries();
/*  68 */       this.geometry = new int[i];
/*  69 */       for (byte b = 0; b < i; b++)
/*  70 */         this.geometry[b] = paramController.getSymbolTable().addReference(((Shape3D)this.node).getGeometry(b)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  75 */     super.writeObject(paramDataOutput);
/*     */     
/*  77 */     this.control.writeBounds(paramDataOutput, ((Shape3D)this.node).getCollisionBounds());
/*     */     
/*  79 */     paramDataOutput.writeInt(this.appearance);
/*     */     
/*  81 */     paramDataOutput.writeInt(this.geometry.length);
/*  82 */     for (byte b = 0; b < this.geometry.length; b++)
/*  83 */       paramDataOutput.writeInt(this.geometry[b]); 
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  87 */     super.readObject(paramDataInput);
/*  88 */     ((Shape3D)this.node).setCollisionBounds(this.control.readBounds(paramDataInput));
/*  89 */     this.appearance = paramDataInput.readInt();
/*     */     
/*  91 */     this.geometry = new int[paramDataInput.readInt()];
/*  92 */     for (byte b = 0; b < this.geometry.length; b++) {
/*  93 */       this.geometry[b] = paramDataInput.readInt();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.appearance); }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 107 */     ((Shape3D)this.node).setAppearance((Appearance)this.control.getSymbolTable().getJ3dNode(this.appearance));
/*     */     
/* 109 */     ((Shape3D)this.node).setGeometry((Geometry)this.control.getSymbolTable().getJ3dNode(this.geometry[0]));
/* 110 */     for (byte b = 1; b < this.geometry.length; b++) {
/* 111 */       ((Shape3D)this.node).addGeometry((Geometry)this.control.getSymbolTable().getJ3dNode(this.geometry[b]));
/*     */     }
/* 113 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 117 */   protected SceneGraphObject createNode() { return new Shape3D(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\Shape3DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */