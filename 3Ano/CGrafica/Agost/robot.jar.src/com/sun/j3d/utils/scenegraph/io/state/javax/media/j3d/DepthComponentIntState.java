/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.DepthComponentInt;
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
/*     */ public class DepthComponentIntState
/*     */   extends NodeComponentState
/*     */ {
/*     */   private int height;
/*     */   private int width;
/*     */   
/*  61 */   public DepthComponentIntState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  65 */     super.writeObject(paramDataOutput);
/*     */     
/*  67 */     int[] arrayOfInt = new int[this.width * this.height];
/*  68 */     ((DepthComponentInt)this.node).getDepthData(arrayOfInt);
/*     */     
/*  70 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/*  71 */       paramDataOutput.writeInt(arrayOfInt[b]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  76 */     super.readObject(paramDataInput);
/*     */     
/*  78 */     int[] arrayOfInt = new int[this.width * this.height];
/*     */     
/*  80 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/*  81 */       arrayOfInt[b] = paramDataInput.readInt();
/*     */     }
/*  83 */     ((DepthComponentInt)this.node).setDepthData(arrayOfInt);
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
/* 107 */   protected SceneGraphObject createNode() { return new DepthComponentInt(this.width, this.height); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\DepthComponentIntState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */