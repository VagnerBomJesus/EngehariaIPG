/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Background;
/*     */ import javax.media.j3d.BoundingLeaf;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BackgroundState
/*     */   extends LeafState
/*     */ {
/*     */   private int image;
/*     */   private int boundingLeaf;
/*     */   private int geometry;
/*     */   
/*     */   public BackgroundState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  67 */     super(paramSymbolTableData, paramController);
/*     */     
/*  69 */     if (this.node != null) {
/*  70 */       this.boundingLeaf = paramController.getSymbolTable().addReference(((Background)this.node).getApplicationBoundingLeaf());
/*  71 */       this.geometry = paramController.getSymbolTable().addReference(((Background)this.node).getGeometry());
/*  72 */       this.image = paramController.getSymbolTable().addReference(((Background)this.node).getImage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  77 */     super.writeObject(paramDataOutput);
/*     */     
/*  79 */     paramDataOutput.writeInt(this.boundingLeaf);
/*  80 */     paramDataOutput.writeInt(this.geometry);
/*  81 */     paramDataOutput.writeInt(this.image);
/*  82 */     this.control.writeBounds(paramDataOutput, ((Background)this.node).getApplicationBounds());
/*  83 */     Color3f color3f = new Color3f();
/*  84 */     ((Background)this.node).getColor(color3f);
/*  85 */     this.control.writeColor3f(paramDataOutput, color3f);
/*  86 */     paramDataOutput.writeInt(((Background)this.node).getImageScaleMode());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  90 */     super.readObject(paramDataInput);
/*     */     
/*  92 */     this.boundingLeaf = paramDataInput.readInt();
/*  93 */     this.geometry = paramDataInput.readInt();
/*  94 */     this.image = paramDataInput.readInt();
/*     */     
/*  96 */     ((Background)this.node).setApplicationBounds(this.control.readBounds(paramDataInput));
/*  97 */     ((Background)this.node).setColor(this.control.readColor3f(paramDataInput));
/*     */     
/*  99 */     ((Background)this.node).setImageScaleMode(paramDataInput.readInt());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.image); }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 113 */     ((Background)this.node).setApplicationBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.boundingLeaf));
/* 114 */     ((Background)this.node).setGeometry((BranchGroup)this.control.getSymbolTable().getJ3dNode(this.geometry));
/* 115 */     ((Background)this.node).setImage((ImageComponent2D)this.control.getSymbolTable().getJ3dNode(this.image));
/* 116 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 121 */   protected SceneGraphObject createNode() { return new Background(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\BackgroundState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */