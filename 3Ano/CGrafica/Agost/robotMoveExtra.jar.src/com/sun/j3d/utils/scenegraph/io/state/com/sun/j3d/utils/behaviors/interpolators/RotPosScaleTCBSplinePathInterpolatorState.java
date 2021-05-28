/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.interpolators.RotPosScaleTCBSplinePathInterpolator;
/*     */ import com.sun.j3d.utils.behaviors.interpolators.TCBKeyFrame;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.TransformInterpolatorState;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Transform3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotPosScaleTCBSplinePathInterpolatorState
/*     */   extends TransformInterpolatorState
/*     */ {
/*     */   private TCBKeyFrame[] keyFrames;
/*     */   private Transform3D axisOfTranslation;
/*     */   
/*  69 */   public RotPosScaleTCBSplinePathInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  73 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  75 */     this.control.writeTransform3D(paramDataOutput, ((RotPosScaleTCBSplinePathInterpolator)this.node).getAxisOfRotPosScale());
/*     */     
/*  77 */     int i = ((RotPosScaleTCBSplinePathInterpolator)this.node).getArrayLength();
/*  78 */     paramDataOutput.writeInt(i);
/*     */     
/*  80 */     for (byte b = 0; b < i; b++) {
/*  81 */       TCBKeyFrame tCBKeyFrame = ((RotPosScaleTCBSplinePathInterpolator)this.node).getKeyFrame(b);
/*  82 */       paramDataOutput.writeFloat(tCBKeyFrame.knot);
/*  83 */       paramDataOutput.writeInt(tCBKeyFrame.linear);
/*  84 */       this.control.writePoint3f(paramDataOutput, tCBKeyFrame.position);
/*  85 */       this.control.writeQuat4f(paramDataOutput, tCBKeyFrame.quat);
/*  86 */       this.control.writePoint3f(paramDataOutput, tCBKeyFrame.scale);
/*  87 */       paramDataOutput.writeFloat(tCBKeyFrame.tension);
/*  88 */       paramDataOutput.writeFloat(tCBKeyFrame.continuity);
/*  89 */       paramDataOutput.writeFloat(tCBKeyFrame.bias);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  94 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  96 */     this.axisOfTranslation = this.control.readTransform3D(paramDataInput);
/*     */     
/*  98 */     this.keyFrames = new TCBKeyFrame[paramDataInput.readInt()];
/*  99 */     for (byte b = 0; b < this.keyFrames.length; b++) {
/* 100 */       this.keyFrames[b] = new TCBKeyFrame(paramDataInput.readFloat(), paramDataInput.readInt(), this.control.readPoint3f(paramDataInput), this.control.readQuat4f(paramDataInput), this.control.readPoint3f(paramDataInput), paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class, Transform3D.class, this.keyFrames.getClass() }, new Object[] { null, null, this.axisOfTranslation, this.keyFrames }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   protected SceneGraphObject createNode() { return new RotPosScaleTCBSplinePathInterpolator(null, null, this.axisOfTranslation, this.keyFrames); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\behaviors\interpolators\RotPosScaleTCBSplinePathInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */