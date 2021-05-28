/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.TransformInterpolator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TCBSplinePathInterpolator
/*     */   extends TransformInterpolator
/*     */ {
/*     */   private int keysLength;
/*     */   protected TCBKeyFrame[] keyFrames;
/*     */   protected float currentU;
/*     */   protected int lowerKnot;
/*     */   protected int upperKnot;
/*     */   
/*     */   TCBSplinePathInterpolator() {}
/*     */   
/*  99 */   public TCBSplinePathInterpolator(Alpha paramAlpha, TCBKeyFrame[] paramArrayOfTCBKeyFrame) { this(paramAlpha, null, paramArrayOfTCBKeyFrame); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TCBSplinePathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, TCBKeyFrame[] paramArrayOfTCBKeyFrame) {
/* 120 */     super(paramAlpha, paramTransformGroup);
/* 121 */     processKeyFrames(paramArrayOfTCBKeyFrame);
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
/*     */   public TCBSplinePathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, TCBKeyFrame[] paramArrayOfTCBKeyFrame) {
/* 144 */     super(paramAlpha, paramTransformGroup, paramTransform3D);
/* 145 */     processKeyFrames(paramArrayOfTCBKeyFrame);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processKeyFrames(TCBKeyFrame[] paramArrayOfTCBKeyFrame) {
/* 154 */     this.keysLength = paramArrayOfTCBKeyFrame.length;
/* 155 */     if (this.keysLength < 2) {
/* 156 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("TCBSplinePathInterpolator0"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 161 */     if ((paramArrayOfTCBKeyFrame[0]).knot < -1.0E-4D || (paramArrayOfTCBKeyFrame[0]).knot > 1.0E-4D) {
/* 162 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("TCBSplinePathInterpolator1"));
/*     */     }
/*     */ 
/*     */     
/* 166 */     if ((paramArrayOfTCBKeyFrame[this.keysLength - 1]).knot - 1.0D < -1.0E-4D || (paramArrayOfTCBKeyFrame[this.keysLength - 1]).knot - 1.0D > 1.0E-4D) {
/* 167 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("TCBSplinePathInterpolator2"));
/*     */     }
/*     */     
/*     */     byte b;
/* 171 */     for (b = 0; b < this.keysLength; b++) {
/* 172 */       if (b && (paramArrayOfTCBKeyFrame[b]).knot < (paramArrayOfTCBKeyFrame[b - true]).knot) {
/* 173 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("TCBSplinePathInterpolator3"));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 179 */     this.keyFrames = new TCBKeyFrame[this.keysLength + 2];
/* 180 */     this.keyFrames[0] = new TCBKeyFrame();
/* 181 */     this.keyFrames[0] = paramArrayOfTCBKeyFrame[0];
/* 182 */     for (b = 1; b < this.keysLength + 1; b++) {
/* 183 */       this.keyFrames[b] = paramArrayOfTCBKeyFrame[b - 1];
/*     */     }
/* 185 */     this.keyFrames[this.keysLength + 1] = new TCBKeyFrame();
/* 186 */     this.keyFrames[this.keysLength + 1] = paramArrayOfTCBKeyFrame[this.keysLength - 1];
/*     */ 
/*     */     
/* 189 */     this.keysLength += 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public int getArrayLength() { return this.keysLength - 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public TCBKeyFrame getKeyFrame(int paramInt) { return this.keyFrames[paramInt + 1]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computePathInterpolation(float paramFloat) {
/* 221 */     byte b = 1;
/* 222 */     int i = this.keysLength - 2;
/* 223 */     while (paramFloat > (this.keyFrames[b]).knot && b < i) {
/* 224 */       b++;
/*     */     }
/*     */     
/* 227 */     if (b == 1) {
/* 228 */       this.currentU = 0.0F;
/* 229 */       this.lowerKnot = 1;
/* 230 */       this.upperKnot = 2;
/*     */     } else {
/* 232 */       this.currentU = (paramFloat - (this.keyFrames[b - 1]).knot) / ((this.keyFrames[b]).knot - (this.keyFrames[b - 1]).knot);
/*     */       
/* 234 */       this.lowerKnot = b - 1;
/* 235 */       this.upperKnot = b;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computePathInterpolation() {
/* 244 */     float f = getAlpha().value();
/* 245 */     computePathInterpolation(f);
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
/* 270 */     super.duplicateNode(paramNode, paramBoolean);
/* 271 */     TCBSplinePathInterpolator tCBSplinePathInterpolator = (TCBSplinePathInterpolator)paramNode;
/* 272 */     setAlpha(tCBSplinePathInterpolator.getAlpha());
/* 273 */     this.keysLength = tCBSplinePathInterpolator.keysLength;
/* 274 */     this.keyFrames = new TCBKeyFrame[this.keysLength];
/* 275 */     System.arraycopy(tCBSplinePathInterpolator.keyFrames, 0, this.keyFrames, 0, this.keysLength);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\interpolators\TCBSplinePathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */