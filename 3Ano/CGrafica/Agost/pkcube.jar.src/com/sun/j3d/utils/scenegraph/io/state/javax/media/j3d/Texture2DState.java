/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Texture2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Texture2DState
/*     */   extends TextureState
/*     */ {
/*  58 */   private int detailImage = 0;
/*     */   
/*     */   public Texture2DState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  61 */     super(paramSymbolTableData, paramController);
/*     */ 
/*     */     
/*  64 */     if (this.node != null) {
/*  65 */       Texture2D texture2D = (Texture2D)this.node;
/*  66 */       this.detailImage = paramController.getSymbolTable().addReference(texture2D.getDetailImage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  71 */     super.writeObject(paramDataOutput);
/*     */     
/*  73 */     paramDataOutput.writeInt(this.detailImage);
/*     */     
/*  75 */     paramDataOutput.writeInt(((Texture2D)this.node).getDetailTextureMode());
/*  76 */     paramDataOutput.writeInt(((Texture2D)this.node).getDetailTextureLevel());
/*     */     
/*  78 */     int i = ((Texture2D)this.node).getDetailTextureFuncPointsCount();
/*  79 */     paramDataOutput.writeInt(i);
/*  80 */     float[] arrayOfFloat1 = new float[i];
/*  81 */     float[] arrayOfFloat2 = new float[i];
/*  82 */     ((Texture2D)this.node).getDetailTextureFunc(arrayOfFloat1, arrayOfFloat2);
/*  83 */     for (byte b = 0; b < i; b++) {
/*  84 */       paramDataOutput.writeFloat(arrayOfFloat1[b]);
/*  85 */       paramDataOutput.writeFloat(arrayOfFloat2[b]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  90 */     super.readObject(paramDataInput);
/*     */     
/*  92 */     this.detailImage = paramDataInput.readInt();
/*     */     
/*  94 */     ((Texture2D)this.node).setDetailTextureMode(paramDataInput.readInt());
/*  95 */     ((Texture2D)this.node).setDetailTextureLevel(paramDataInput.readInt());
/*  96 */     int i = paramDataInput.readInt();
/*  97 */     float[] arrayOfFloat1 = new float[i];
/*  98 */     float[] arrayOfFloat2 = new float[i];
/*  99 */     for (byte b = 0; b < i; b++) {
/* 100 */       arrayOfFloat1[b] = paramDataInput.readFloat();
/* 101 */       arrayOfFloat2[b] = paramDataInput.readFloat();
/*     */     } 
/* 103 */     ((Texture2D)this.node).setDetailTextureFunc(arrayOfFloat1, arrayOfFloat2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.detailImage); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 117 */     Texture2D texture2D = (Texture2D)this.node;
/* 118 */     texture2D.setDetailImage((ImageComponent2D)this.control.getSymbolTable().getJ3dNode(this.detailImage));
/* 119 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 123 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(this.mipMapMode), new Integer(this.format), new Integer(this.width), new Integer(this.height), new Integer(this.boundaryWidth) }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   protected SceneGraphObject createNode() { return new Texture2D(this.mipMapMode, this.format, this.width, this.height, this.boundaryWidth); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\Texture2DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */