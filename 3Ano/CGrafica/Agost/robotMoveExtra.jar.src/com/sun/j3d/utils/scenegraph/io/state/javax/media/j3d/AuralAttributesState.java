/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.AuralAttributes;
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
/*     */ 
/*     */ public class AuralAttributesState
/*     */   extends NodeComponentState
/*     */ {
/*  59 */   public AuralAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  64 */     super.writeObject(paramDataOutput);
/*     */     
/*  66 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getAttributeGain());
/*     */     
/*  68 */     float[] arrayOfFloat1 = new float[((AuralAttributes)this.node).getDistanceFilterLength()];
/*  69 */     float[] arrayOfFloat2 = new float[arrayOfFloat1.length];
/*     */     
/*  71 */     ((AuralAttributes)this.node).getDistanceFilter(arrayOfFloat1, arrayOfFloat2);
/*  72 */     paramDataOutput.writeInt(arrayOfFloat1.length);
/*  73 */     for (byte b = 0; b < arrayOfFloat1.length; b++) {
/*  74 */       paramDataOutput.writeFloat(arrayOfFloat1[b]);
/*  75 */       paramDataOutput.writeFloat(arrayOfFloat2[b]);
/*     */     } 
/*     */     
/*  78 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getFrequencyScaleFactor());
/*  79 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getReflectionCoefficient());
/*  80 */     this.control.writeBounds(paramDataOutput, ((AuralAttributes)this.node).getReverbBounds());
/*  81 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getReverbDelay());
/*  82 */     paramDataOutput.writeInt(((AuralAttributes)this.node).getReverbOrder());
/*  83 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getRolloff());
/*  84 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getVelocityScaleFactor());
/*  85 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getReflectionDelay());
/*  86 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getReverbCoefficient());
/*  87 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getDecayTime());
/*  88 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getDecayFilter());
/*  89 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getDiffusion());
/*  90 */     paramDataOutput.writeFloat(((AuralAttributes)this.node).getDensity());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  94 */     super.readObject(paramDataInput);
/*     */     
/*  96 */     ((AuralAttributes)this.node).setAttributeGain(paramDataInput.readFloat());
/*     */     
/*  98 */     float[] arrayOfFloat1 = new float[paramDataInput.readInt()];
/*  99 */     float[] arrayOfFloat2 = new float[arrayOfFloat1.length];
/* 100 */     for (byte b = 0; b < arrayOfFloat1.length; b++) {
/* 101 */       arrayOfFloat1[b] = paramDataInput.readFloat();
/* 102 */       arrayOfFloat2[b] = paramDataInput.readFloat();
/*     */     } 
/* 104 */     ((AuralAttributes)this.node).setDistanceFilter(arrayOfFloat1, arrayOfFloat2);
/*     */     
/* 106 */     ((AuralAttributes)this.node).setFrequencyScaleFactor(paramDataInput.readFloat());
/* 107 */     ((AuralAttributes)this.node).setReflectionCoefficient(paramDataInput.readFloat());
/* 108 */     ((AuralAttributes)this.node).setReverbBounds(this.control.readBounds(paramDataInput));
/* 109 */     ((AuralAttributes)this.node).setReverbDelay(paramDataInput.readFloat());
/* 110 */     ((AuralAttributes)this.node).setReverbOrder(paramDataInput.readInt());
/* 111 */     ((AuralAttributes)this.node).setRolloff(paramDataInput.readFloat());
/* 112 */     ((AuralAttributes)this.node).setVelocityScaleFactor(paramDataInput.readFloat());
/* 113 */     ((AuralAttributes)this.node).setReflectionDelay(paramDataInput.readFloat());
/* 114 */     ((AuralAttributes)this.node).setReverbCoefficient(paramDataInput.readFloat());
/* 115 */     ((AuralAttributes)this.node).setDecayTime(paramDataInput.readFloat());
/* 116 */     ((AuralAttributes)this.node).setDecayFilter(paramDataInput.readFloat());
/* 117 */     ((AuralAttributes)this.node).setDiffusion(paramDataInput.readFloat());
/* 118 */     ((AuralAttributes)this.node).setDensity(paramDataInput.readFloat());
/*     */   }
/*     */ 
/*     */   
/* 122 */   protected SceneGraphObject createNode() { return new AuralAttributes(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\AuralAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */