/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KBRotPosScaleSplinePathInterpolator
/*     */   extends KBSplinePathInterpolator
/*     */ {
/*  73 */   private Transform3D rotation = new Transform3D();
/*     */   
/*  75 */   private Matrix4d pitchMat = new Matrix4d();
/*  76 */   private Matrix4d bankMat = new Matrix4d();
/*  77 */   private Matrix4d tMat = new Matrix4d();
/*  78 */   private Matrix4d sMat = new Matrix4d();
/*     */   
/*  80 */   private Vector3f iPos = new Vector3f();
/*  81 */   private Point3f iScale = new Point3f();
/*     */   float iHeading;
/*     */   float iPitch;
/*     */   float iBank;
/*  85 */   KBCubicSplineCurve cubicSplineCurve = new KBCubicSplineCurve();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   KBCubicSplineSegment[] cubicSplineSegments;
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
/*     */   KBCubicSplineSegment currentSegment;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   KBRotPosScaleSplinePathInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KBRotPosScaleSplinePathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, KBKeyFrame[] paramArrayOfKBKeyFrame) {
/* 114 */     super(paramAlpha, paramTransformGroup, paramTransform3D, paramArrayOfKBKeyFrame);
/*     */ 
/*     */     
/* 117 */     this.cubicSplineCurve = new KBCubicSplineCurve(this.keyFrames);
/* 118 */     this.numSegments = this.cubicSplineCurve.numSegments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void setAxisOfRotPosScale(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public Transform3D getAxisOfRotPosScale() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyFrame(int paramInt, KBKeyFrame paramKBKeyFrame) {
/* 144 */     super.setKeyFrame(paramInt, paramKBKeyFrame);
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.cubicSplineCurve = new KBCubicSplineCurve(this.keyFrames);
/* 149 */     this.numSegments = this.cubicSplineCurve.numSegments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyFrames(KBKeyFrame[] paramArrayOfKBKeyFrame) {
/* 157 */     super.setKeyFrames(paramArrayOfKBKeyFrame);
/*     */ 
/*     */     
/* 160 */     this.cubicSplineCurve = new KBCubicSplineCurve(this.keyFrames);
/* 161 */     this.numSegments = this.cubicSplineCurve.numSegments;
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
/*     */   public void computeTransform(float paramFloat, Transform3D paramTransform3D) {
/* 177 */     computePathInterpolation(paramFloat);
/*     */ 
/*     */     
/* 180 */     this.currentSegmentIndex = this.lowerKnot - 1;
/*     */ 
/*     */     
/* 183 */     if (this.currentSegmentIndex == 0 && this.currentU == 0.0F) {
/*     */       
/* 185 */       this.iHeading = (this.keyFrames[1]).heading;
/* 186 */       this.iPitch = (this.keyFrames[1]).pitch;
/* 187 */       this.iBank = (this.keyFrames[1]).bank;
/* 188 */       this.iPos.set((this.keyFrames[1]).position);
/* 189 */       this.iScale.set((this.keyFrames[1]).scale);
/*     */     
/*     */     }
/* 192 */     else if (this.currentSegmentIndex == this.numSegments - 1 && this.currentU == 1.0D) {
/*     */ 
/*     */       
/* 195 */       this.iHeading = (this.keyFrames[this.upperKnot]).heading;
/* 196 */       this.iPitch = (this.keyFrames[this.upperKnot]).pitch;
/* 197 */       this.iBank = (this.keyFrames[this.upperKnot]).bank;
/* 198 */       this.iPos.set((this.keyFrames[this.upperKnot]).position);
/* 199 */       this.iScale.set((this.keyFrames[this.upperKnot]).scale);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 206 */       this.currentSegment = this.cubicSplineCurve.getSegment(this.currentSegmentIndex);
/*     */ 
/*     */ 
/*     */       
/* 210 */       this.iHeading = this.currentSegment.getInterpolatedHeading(this.currentU);
/* 211 */       this.iPitch = this.currentSegment.getInterpolatedPitch(this.currentU);
/* 212 */       this.iBank = this.currentSegment.getInterpolatedBank(this.currentU);
/*     */ 
/*     */       
/* 215 */       this.currentSegment.getInterpolatedPositionVector(this.currentU, this.iPos);
/*     */ 
/*     */       
/* 218 */       this.currentSegment.getInterpolatedScale(this.currentU, this.iScale);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     this.pitchMat.setIdentity();
/* 225 */     this.pitchMat.rotX(-this.iPitch);
/* 226 */     this.bankMat.setIdentity();
/* 227 */     this.bankMat.rotZ(this.iBank);
/* 228 */     this.tMat.setIdentity();
/* 229 */     this.tMat.rotY(-this.iHeading);
/* 230 */     this.tMat.mul(this.pitchMat);
/* 231 */     this.tMat.mul(this.bankMat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     this.sMat.set(this.iScale.x);
/* 239 */     this.tMat.mul(this.sMat);
/*     */ 
/*     */     
/* 242 */     this.tMat.m03 = this.iPos.x;
/* 243 */     this.tMat.m13 = this.iPos.y;
/* 244 */     this.tMat.m23 = this.iPos.z;
/* 245 */     this.rotation.set(this.tMat);
/*     */ 
/*     */     
/* 248 */     paramTransform3D.mul(this.axis, this.rotation);
/* 249 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 273 */     KBRotPosScaleSplinePathInterpolator kBRotPosScaleSplinePathInterpolator = new KBRotPosScaleSplinePathInterpolator();
/*     */ 
/*     */     
/* 276 */     kBRotPosScaleSplinePathInterpolator.duplicateNode(this, paramBoolean);
/* 277 */     return kBRotPosScaleSplinePathInterpolator;
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
/* 302 */     super.duplicateNode(paramNode, paramBoolean);
/*     */     
/* 304 */     KBRotPosScaleSplinePathInterpolator kBRotPosScaleSplinePathInterpolator = (KBRotPosScaleSplinePathInterpolator)paramNode;
/*     */     
/* 306 */     setAxisOfRotPosScale(kBRotPosScaleSplinePathInterpolator.axis);
/* 307 */     this.target = kBRotPosScaleSplinePathInterpolator.target;
/* 308 */     this.cubicSplineCurve = new KBCubicSplineCurve(kBRotPosScaleSplinePathInterpolator.keyFrames);
/* 309 */     this.numSegments = this.cubicSplineCurve.numSegments;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\interpolators\KBRotPosScaleSplinePathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */