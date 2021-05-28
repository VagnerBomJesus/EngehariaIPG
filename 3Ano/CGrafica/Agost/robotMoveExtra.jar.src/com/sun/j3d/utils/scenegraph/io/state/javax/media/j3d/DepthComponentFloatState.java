/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.DepthComponentFloat;
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
/*     */ public class DepthComponentFloatState
/*     */   extends NodeComponentState
/*     */ {
/*     */   private int height;
/*     */   private int width;
/*     */   
/*  61 */   public DepthComponentFloatState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  65 */     super.writeObject(paramDataOutput);
/*     */     
/*  67 */     float[] arrayOfFloat = new float[this.width * this.height];
/*  68 */     ((DepthComponentFloat)this.node).getDepthData(arrayOfFloat);
/*     */     
/*  70 */     for (byte b = 0; b < arrayOfFloat.length; b++) {
/*  71 */       paramDataOutput.writeFloat(arrayOfFloat[b]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  76 */     super.readObject(paramDataInput);
/*     */     
/*  78 */     float[] arrayOfFloat = new float[this.width * this.height];
/*     */     
/*  80 */     for (byte b = 0; b < arrayOfFloat.length; b++) {
/*  81 */       arrayOfFloat[b] = paramDataInput.readFloat();
/*     */     }
/*  83 */     ((DepthComponentFloat)this.node).setDepthData(arrayOfFloat);
/*     */   }
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  87 */     super.writeConstructorParams(paramDataOutput);
/*  88 */     paramDataOutput.writeInt(this.width);
/*  89 */     paramDataOutput.writeInt(this.height);
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  93 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  95 */     this.width = paramDataInput.readInt();
/*  96 */     this.height = paramDataInput.readInt();
/*     */   }
/*     */ 
/*     */   
/* 100 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class }, new Object[] { new Integer(this.width), new Integer(this.height) }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   protected SceneGraphObject createNode() { return new DepthComponentFloat(this.width, this.height); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\DepthComponentFloatState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */