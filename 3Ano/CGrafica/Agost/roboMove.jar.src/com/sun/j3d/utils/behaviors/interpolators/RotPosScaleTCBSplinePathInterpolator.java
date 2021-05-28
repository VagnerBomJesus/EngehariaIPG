/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotPosScaleTCBSplinePathInterpolator
/*     */   extends TCBSplinePathInterpolator
/*     */ {
/*  67 */   private Transform3D rotation = new Transform3D();
/*  68 */   private Matrix4d tMat = new Matrix4d();
/*  69 */   private Matrix4d sMat = new Matrix4d();
/*  70 */   private Quat4f iQuat = new Quat4f();
/*  71 */   private Vector3f iPos = new Vector3f();
/*  72 */   private Point3f iScale = new Point3f();
/*     */   
/*  74 */   CubicSplineCurve cubicSplineCurve = new CubicSplineCurve();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CubicSplineSegment[] cubicSplineSegments;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int numSegments;
/*     */ 
/*     */ 
/*     */   
/*     */   int currentSegmentIndex;
/*     */ 
/*     */ 
/*     */   
/*     */   CubicSplineSegment currentSegment;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RotPosScaleTCBSplinePathInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RotPosScaleTCBSplinePathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, TCBKeyFrame[] paramArrayOfTCBKeyFrame) {
/* 103 */     super(paramAlpha, paramTransformGroup, paramTransform3D, paramArrayOfTCBKeyFrame);
/*     */     
/* 105 */     this.cubicSplineCurve = new CubicSplineCurve(this.keyFrames);
/* 106 */     this.numSegments = this.cubicSplineCurve.numSegments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void setAxisOfRotPosScale(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public Transform3D getAxisOfRotPosScale() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void computeTransform(float paramFloat, Transform3D paramTransform3D) {
/* 141 */     computePathInterpolation(paramFloat);
/*     */ 
/*     */     
/* 144 */     this.currentSegmentIndex = this.lowerKnot - 1;
/*     */ 
/*     */     
/* 147 */     if (this.currentSegmentIndex == 0 && this.currentU == 0.0F) {
/*     */       
/* 149 */       this.iQuat.set((this.keyFrames[1]).quat);
/* 150 */       this.iPos.set((this.keyFrames[1]).position);
/* 151 */       this.iScale.set((this.keyFrames[1]).scale);
/*     */     
/*     */     }
/* 154 */     else if (this.currentSegmentIndex == this.numSegments - 1 && this.currentU == 1.0D) {
/*     */ 
/*     */       
/* 157 */       this.iQuat.set((this.keyFrames[this.upperKnot]).quat);
/* 158 */       this.iPos.set((this.keyFrames[this.upperKnot]).position);
/* 159 */       this.iScale.set((this.keyFrames[this.upperKnot]).scale);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 166 */       this.currentSegment = this.cubicSplineCurve.getSegment(this.currentSegmentIndex);
/*     */ 
/*     */ 
/*     */       
/* 170 */       this.currentSegment.getInterpolatedQuaternion(this.currentU, this.iQuat);
/*     */ 
/*     */       
/* 173 */       this.currentSegment.getInterpolatedPositionVector(this.currentU, this.iPos);
/*     */ 
/*     */       
/* 176 */       this.currentSegment.getInterpolatedScale(this.currentU, this.iScale);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     this.iQuat.normalize();
/* 182 */     this.tMat.set(this.iQuat);
/*     */ 
/*     */     
/* 185 */     this.tMat.m03 = this.iPos.x;
/* 186 */     this.tMat.m13 = this.iPos.y;
/* 187 */     this.tMat.m23 = this.iPos.z;
/* 188 */     this.rotation.set(this.tMat);
/*     */ 
/*     */     
/* 191 */     paramTransform3D.mul(this.axis, this.rotation);
/* 192 */     paramTransform3D.setScale(new Vector3d(this.iScale));
/* 193 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 212 */     RotPosScaleTCBSplinePathInterpolator rotPosScaleTCBSplinePathInterpolator = new RotPosScaleTCBSplinePathInterpolator();
/*     */     
/* 214 */     rotPosScaleTCBSplinePathInterpolator.duplicateNode(this, paramBoolean);
/* 215 */     return rotPosScaleTCBSplinePathInterpolator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void duplicateNode(Node paramNode, boolean paramBoolean) {
/* 240 */     super.duplicateNode(paramNode, paramBoolean);
/* 241 */     RotPosScaleTCBSplinePathInterpolator rotPosScaleTCBSplinePathInterpolator = (RotPosScaleTCBSplinePathInterpolator)paramNode;
/*     */ 
/*     */     
/* 244 */     this.cubicSplineCurve = new CubicSplineCurve(rotPosScaleTCBSplinePathInterpolator.keyFrames);
/* 245 */     this.numSegments = this.cubicSplineCurve.numSegments;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\behaviors\interpolators\RotPosScaleTCBSplinePathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */