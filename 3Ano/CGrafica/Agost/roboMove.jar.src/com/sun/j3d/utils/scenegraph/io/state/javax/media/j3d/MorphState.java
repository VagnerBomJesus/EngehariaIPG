/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.GeometryArray;
/*     */ import javax.media.j3d.Morph;
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
/*     */ public class MorphState
/*     */   extends LeafState
/*     */ {
/*     */   private int[] geometry;
/*     */   private double[] weights;
/*     */   private int appearance;
/*     */   
/*     */   public MorphState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  64 */     super(paramSymbolTableData, paramController);
/*     */     
/*  66 */     if (this.node != null) {
/*  67 */       this.appearance = paramController.getSymbolTable().addReference(((Morph)this.node).getAppearance());
/*  68 */       this.weights = ((Morph)this.node).getWeights();
/*  69 */       this.geometry = new int[this.weights.length];
/*  70 */       for (byte b = 0; b < this.weights.length; b++)
/*  71 */         this.geometry[b] = paramController.getSymbolTable().addReference(((Morph)this.node).getGeometryArray(b)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  76 */     super.writeObject(paramDataOutput);
/*     */     
/*  78 */     this.control.writeBounds(paramDataOutput, ((Morph)this.node).getCollisionBounds());
/*     */     
/*  80 */     paramDataOutput.writeInt(this.appearance);
/*  81 */     paramDataOutput.writeBoolean(((Morph)this.node).getAppearanceOverrideEnable());
/*     */     
/*  83 */     paramDataOutput.writeInt(this.geometry.length);
/*  84 */     for (byte b = 0; b < this.geometry.length; b++) {
/*  85 */       paramDataOutput.writeInt(this.geometry[b]);
/*  86 */       paramDataOutput.writeDouble(this.weights[b]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  91 */     super.readObject(paramDataInput);
/*  92 */     ((Morph)this.node).setCollisionBounds(this.control.readBounds(paramDataInput));
/*  93 */     this.appearance = paramDataInput.readInt();
/*  94 */     ((Morph)this.node).setAppearanceOverrideEnable(paramDataInput.readBoolean());
/*     */     
/*  96 */     int i = paramDataInput.readInt();
/*  97 */     this.geometry = new int[i];
/*  98 */     this.weights = new double[i];
/*  99 */     for (byte b = 0; b < this.geometry.length; b++) {
/* 100 */       this.geometry[b] = paramDataInput.readInt();
/* 101 */       this.weights[b] = paramDataInput.readDouble();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubReference() {
/* 111 */     this.control.getSymbolTable().incNodeComponentRefCount(this.appearance);
/* 112 */     for (byte b = 0; b < this.geometry.length; b++)
/* 113 */       this.control.getSymbolTable().incNodeComponentRefCount(this.geometry[b]); 
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/* 117 */     ((Morph)this.node).setAppearance((Appearance)this.control.getSymbolTable().getJ3dNode(this.appearance));
/*     */     
/* 119 */     GeometryArray[] arrayOfGeometryArray = new GeometryArray[this.geometry.length];
/* 120 */     for (byte b = 0; b < this.geometry.length; b++) {
/* 121 */       arrayOfGeometryArray[b] = (GeometryArray)this.control.getSymbolTable().getJ3dNode(this.geometry[b]);
/*     */     }
/*     */     
/* 124 */     ((Morph)this.node).setGeometryArrays(arrayOfGeometryArray);
/* 125 */     ((Morph)this.node).setWeights(this.weights);
/* 126 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 130 */   protected SceneGraphObject createNode() { return new Morph(null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\MorphState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */