/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.interpolators.KBKeyFrame;
/*     */ import com.sun.j3d.utils.behaviors.interpolators.KBRotPosScaleSplinePathInterpolator;
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
/*     */ public class KBRotPosScaleSplinePathInterpolatorState
/*     */   extends TransformInterpolatorState
/*     */ {
/*     */   private KBKeyFrame[] keyFrames;
/*     */   private Transform3D axisOfTranslation;
/*     */   
/*  68 */   public KBRotPosScaleSplinePathInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  72 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  74 */     this.control.writeTransform3D(paramDataOutput, ((KBRotPosScaleSplinePathInterpolator)this.node).getAxisOfRotPosScale());
/*     */     
/*  76 */     int i = ((KBRotPosScaleSplinePathInterpolator)this.node).getArrayLength();
/*  77 */     paramDataOutput.writeInt(i);
/*     */     
/*  79 */     for (byte b = 0; b < i; b++) {
/*  80 */       KBKeyFrame kBKeyFrame = ((KBRotPosScaleSplinePathInterpolator)this.node).getKeyFrame(b);
/*  81 */       paramDataOutput.writeFloat(kBKeyFrame.knot);
/*  82 */       paramDataOutput.writeInt(kBKeyFrame.linear);
/*  83 */       this.control.writePoint3f(paramDataOutput, kBKeyFrame.position);
/*  84 */       paramDataOutput.writeFloat(kBKeyFrame.heading);
/*  85 */       paramDataOutput.writeFloat(kBKeyFrame.pitch);
/*  86 */       paramDataOutput.writeFloat(kBKeyFrame.bank);
/*  87 */       this.control.writePoint3f(paramDataOutput, kBKeyFrame.scale);
/*  88 */       paramDataOutput.writeFloat(kBKeyFrame.tension);
/*  89 */       paramDataOutput.writeFloat(kBKeyFrame.continuity);
/*  90 */       paramDataOutput.writeFloat(kBKeyFrame.bias);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  95 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  97 */     this.axisOfTranslation = this.control.readTransform3D(paramDataInput);
/*     */     
/*  99 */     this.keyFrames = new KBKeyFrame[paramDataInput.readInt()];
/* 100 */     for (byte b = 0; b < this.keyFrames.length; b++) {
/* 101 */       this.keyFrames[b] = new KBKeyFrame(paramDataInput.readFloat(), paramDataInput.readInt(), this.control.readPoint3f(paramDataInput), paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat(), this.control.readPoint3f(paramDataInput), paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat());
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
/*     */ 
/*     */   
/* 115 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class, Transform3D.class, this.keyFrames.getClass() }, new Object[] { null, null, this.axisOfTranslation, this.keyFrames }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   protected SceneGraphObject createNode() { return new KBRotPosScaleSplinePathInterpolator(null, null, this.axisOfTranslation, this.keyFrames); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\behaviors\interpolators\KBRotPosScaleSplinePathInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */