/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.DistanceLOD;
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
/*     */ 
/*     */ public class DistanceLODState
/*     */   extends LODState
/*     */ {
/*     */   private int numDistances;
/*     */   
/*  61 */   public DistanceLODState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  65 */     super.writeObject(paramDataOutput);
/*     */     
/*  67 */     for (byte b = 0; b < this.numDistances; b++) {
/*  68 */       paramDataOutput.writeDouble(((DistanceLOD)this.node).getDistance(b));
/*     */     }
/*  70 */     Point3f point3f = new Point3f();
/*  71 */     ((DistanceLOD)this.node).getPosition(point3f);
/*  72 */     this.control.writePoint3f(paramDataOutput, point3f);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  76 */     super.readObject(paramDataInput);
/*     */     
/*  78 */     for (byte b = 0; b < this.numDistances; b++) {
/*  79 */       ((DistanceLOD)this.node).setDistance(b, paramDataInput.readDouble());
/*     */     }
/*  81 */     ((DistanceLOD)this.node).setPosition(this.control.readPoint3f(paramDataInput));
/*     */   }
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  85 */     super.writeConstructorParams(paramDataOutput);
/*  86 */     this.numDistances = ((DistanceLOD)this.node).numDistances();
/*  87 */     paramDataOutput.writeInt(this.numDistances);
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  91 */     super.readConstructorParams(paramDataInput);
/*  92 */     this.numDistances = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public SceneGraphObject createNode(Class paramClass) {
/*  96 */     float[] arrayOfFloat = new float[this.numDistances];
/*     */     
/*  98 */     return createNode(paramClass, new Class[] { arrayOfFloat.getClass() }, new Object[] { arrayOfFloat });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 103 */   protected SceneGraphObject createNode() { return new DistanceLOD(new float[this.numDistances]); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\DistanceLODState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */