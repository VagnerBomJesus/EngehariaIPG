/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.DepthComponent;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.Raster;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RasterState
/*     */   extends GeometryState
/*     */ {
/*     */   int image;
/*     */   int depthComponent;
/*     */   
/*     */   public RasterState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  66 */     super(paramSymbolTableData, paramController);
/*     */ 
/*     */     
/*  69 */     if (this.node != null) {
/*  70 */       this.image = paramController.getSymbolTable().addReference(((Raster)this.node).getImage());
/*  71 */       this.depthComponent = paramController.getSymbolTable().addReference(((Raster)this.node).getDepthComponent());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  77 */     super.writeObject(paramDataOutput);
/*     */     
/*  79 */     paramDataOutput.writeInt(this.image);
/*  80 */     paramDataOutput.writeInt(this.depthComponent);
/*     */     
/*  82 */     Point3f point3f = new Point3f();
/*  83 */     ((Raster)this.node).getPosition(point3f);
/*  84 */     this.control.writePoint3f(paramDataOutput, point3f);
/*     */     
/*  86 */     paramDataOutput.writeInt(((Raster)this.node).getType());
/*  87 */     paramDataOutput.writeInt(((Raster)this.node).getClipMode());
/*     */     
/*  89 */     Point point = new Point();
/*  90 */     ((Raster)this.node).getSrcOffset(point);
/*  91 */     paramDataOutput.writeInt(point.x);
/*  92 */     paramDataOutput.writeInt(point.y);
/*     */     
/*  94 */     Dimension dimension = new Dimension();
/*  95 */     ((Raster)this.node).getSize(dimension);
/*  96 */     paramDataOutput.writeInt(dimension.width);
/*  97 */     paramDataOutput.writeInt(dimension.height);
/*     */     
/*  99 */     ((Raster)this.node).getDstOffset(point);
/* 100 */     paramDataOutput.writeInt(point.x);
/* 101 */     paramDataOutput.writeInt(point.y);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/* 105 */     super.readObject(paramDataInput);
/*     */     
/* 107 */     this.image = paramDataInput.readInt();
/* 108 */     this.depthComponent = paramDataInput.readInt();
/*     */     
/* 110 */     ((Raster)this.node).setPosition(this.control.readPoint3f(paramDataInput));
/* 111 */     ((Raster)this.node).setType(paramDataInput.readInt());
/* 112 */     ((Raster)this.node).setClipMode(paramDataInput.readInt());
/* 113 */     ((Raster)this.node).setSrcOffset(new Point(paramDataInput.readInt(), paramDataInput.readInt()));
/* 114 */     ((Raster)this.node).setSize(new Dimension(paramDataInput.readInt(), paramDataInput.readInt()));
/* 115 */     ((Raster)this.node).setDstOffset(new Point(paramDataInput.readInt(), paramDataInput.readInt()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubReference() {
/* 124 */     this.control.getSymbolTable().incNodeComponentRefCount(this.image);
/* 125 */     this.control.getSymbolTable().incNodeComponentRefCount(this.depthComponent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 130 */     ((Raster)this.node).setImage((ImageComponent2D)this.control.getSymbolTable().getJ3dNode(this.image));
/* 131 */     ((Raster)this.node).setDepthComponent((DepthComponent)this.control.getSymbolTable().getJ3dNode(this.depthComponent));
/*     */     
/* 133 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 137 */   protected SceneGraphObject createNode() { return new Raster(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\RasterState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */