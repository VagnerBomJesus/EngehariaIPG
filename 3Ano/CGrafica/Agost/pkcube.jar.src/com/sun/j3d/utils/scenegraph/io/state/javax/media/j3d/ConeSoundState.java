/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.ConeSound;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConeSoundState
/*     */   extends PointSoundState
/*     */ {
/*  59 */   public ConeSoundState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  64 */     super.writeObject(paramDataOutput);
/*     */     
/*  66 */     float[] arrayOfFloat1 = new float[((ConeSound)this.node).getAngularAttenuationLength()];
/*  67 */     float[] arrayOfFloat2 = new float[arrayOfFloat1.length];
/*  68 */     float[] arrayOfFloat3 = new float[arrayOfFloat1.length];
/*  69 */     float[] arrayOfFloat4 = new float[arrayOfFloat1.length];
/*  70 */     float[] arrayOfFloat5 = new float[arrayOfFloat1.length];
/*  71 */     float[] arrayOfFloat6 = new float[arrayOfFloat1.length];
/*  72 */     float[] arrayOfFloat7 = new float[arrayOfFloat1.length];
/*     */     
/*  74 */     ((ConeSound)this.node).getDistanceGain(arrayOfFloat6, arrayOfFloat7, arrayOfFloat4, arrayOfFloat5);
/*  75 */     ((ConeSound)this.node).getAngularAttenuation(arrayOfFloat1, arrayOfFloat2, arrayOfFloat3);
/*  76 */     paramDataOutput.writeInt(arrayOfFloat1.length);
/*  77 */     for (byte b = 0; b < arrayOfFloat1.length; b++) {
/*  78 */       paramDataOutput.writeFloat(arrayOfFloat1[b]);
/*  79 */       paramDataOutput.writeFloat(arrayOfFloat2[b]);
/*  80 */       paramDataOutput.writeFloat(arrayOfFloat3[b]);
/*  81 */       paramDataOutput.writeFloat(arrayOfFloat4[b]);
/*  82 */       paramDataOutput.writeFloat(arrayOfFloat5[b]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     Vector3f vector3f = new Vector3f();
/*     */     
/*  90 */     ((ConeSound)this.node).getDirection(vector3f);
/*  91 */     this.control.writeVector3f(paramDataOutput, vector3f);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  95 */     super.readObject(paramDataInput);
/*     */     
/*  97 */     float[] arrayOfFloat1 = new float[paramDataInput.readInt()];
/*  98 */     float[] arrayOfFloat2 = new float[arrayOfFloat1.length];
/*  99 */     float[] arrayOfFloat3 = new float[arrayOfFloat1.length];
/* 100 */     float[] arrayOfFloat4 = new float[arrayOfFloat1.length];
/* 101 */     float[] arrayOfFloat5 = new float[arrayOfFloat1.length];
/*     */     
/* 103 */     for (byte b = 0; b < arrayOfFloat1.length; b++) {
/* 104 */       arrayOfFloat1[b] = paramDataInput.readFloat();
/* 105 */       arrayOfFloat2[b] = paramDataInput.readFloat();
/* 106 */       arrayOfFloat3[b] = paramDataInput.readFloat();
/* 107 */       arrayOfFloat4[b] = paramDataInput.readFloat();
/* 108 */       arrayOfFloat5[b] = paramDataInput.readFloat();
/*     */     } 
/*     */     
/* 111 */     ((ConeSound)this.node).setBackDistanceGain(arrayOfFloat4, arrayOfFloat5);
/* 112 */     ((ConeSound)this.node).setAngularAttenuation(arrayOfFloat1, arrayOfFloat2, arrayOfFloat3);
/*     */     
/* 114 */     ((ConeSound)this.node).setDirection(this.control.readVector3f(paramDataInput));
/*     */   }
/*     */ 
/*     */   
/* 118 */   protected SceneGraphObject createNode() { return new ConeSound(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ConeSoundState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */