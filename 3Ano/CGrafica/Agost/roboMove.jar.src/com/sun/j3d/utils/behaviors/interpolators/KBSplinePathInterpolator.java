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
/*     */ public abstract class KBSplinePathInterpolator
/*     */   extends TransformInterpolator
/*     */ {
/*     */   private int keysLength;
/*     */   protected KBKeyFrame[] keyFrames;
/*     */   protected float currentU;
/*     */   protected int lowerKnot;
/*     */   protected int upperKnot;
/*     */   
/*     */   KBSplinePathInterpolator() {}
/*     */   
/*  98 */   public KBSplinePathInterpolator(Alpha paramAlpha, KBKeyFrame[] paramArrayOfKBKeyFrame) { this(paramAlpha, null, paramArrayOfKBKeyFrame); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KBSplinePathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, KBKeyFrame[] paramArrayOfKBKeyFrame) {
/* 120 */     super(paramAlpha, paramTransformGroup);
/* 121 */     processKeyFrames(paramArrayOfKBKeyFrame);
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
/*     */   public KBSplinePathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, KBKeyFrame[] paramArrayOfKBKeyFrame) {
/* 146 */     super(paramAlpha, paramTransformGroup, paramTransform3D);
/* 147 */     processKeyFrames(paramArrayOfKBKeyFrame);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processKeyFrames(KBKeyFrame[] paramArrayOfKBKeyFrame) {
/* 156 */     this.keysLength = paramArrayOfKBKeyFrame.length;
/* 157 */     if (this.keysLength < 2) {
/* 158 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("KBSplinePathInterpolator0"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 163 */     if ((paramArrayOfKBKeyFrame[0]).knot < -1.0E-4D || (paramArrayOfKBKeyFrame[0]).knot > 1.0E-4D) {
/* 164 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("KBSplinePathInterpolator1"));
/*     */     }
/*     */ 
/*     */     
/* 168 */     if ((paramArrayOfKBKeyFrame[this.keysLength - 1]).knot - 1.0D < -1.0E-4D || (paramArrayOfKBKeyFrame[this.keysLength - 1]).knot - 1.0D > 1.0E-4D) {
/* 169 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("KBSplinePathInterpolator2"));
/*     */     }
/*     */     
/*     */     byte b;
/* 173 */     for (b = 0; b < this.keysLength; b++) {
/* 174 */       if (b && (paramArrayOfKBKeyFrame[b]).knot < (paramArrayOfKBKeyFrame[b - true]).knot) {
/* 175 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("KBSplinePathInterpolator3"));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     this.keyFrames = new KBKeyFrame[this.keysLength + 2];
/* 182 */     this.keyFrames[0] = new KBKeyFrame();
/* 183 */     this.keyFrames[0] = paramArrayOfKBKeyFrame[0];
/* 184 */     for (b = 1; b < this.keysLength + 1; b++) {
/* 185 */       this.keyFrames[b] = paramArrayOfKBKeyFrame[b - 1];
/*     */     }
/* 187 */     this.keyFrames[this.keysLength + 1] = new KBKeyFrame();
/* 188 */     this.keyFrames[this.keysLength + 1] = paramArrayOfKBKeyFrame[this.keysLength - 1];
/*     */ 
/*     */     
/* 191 */     this.keysLength += 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public int getArrayLength() { return this.keysLength - 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public KBKeyFrame getKeyFrame(int paramInt) { return this.keyFrames[paramInt + 1]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setKeyFrame(int paramInt, KBKeyFrame paramKBKeyFrame) { this.keyFrames[paramInt + 1] = paramKBKeyFrame; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public void setKeyFrames(KBKeyFrame[] paramArrayOfKBKeyFrame) { processKeyFrames(paramArrayOfKBKeyFrame); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   protected void computePathInterpolation() { computePathInterpolation(getAlpha().value()); }
/*     */ 
/*     */ 
/*     */ 
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
/* 251 */     byte b = 1;
/* 252 */     int i = this.keysLength - 2;
/* 253 */     while (paramFloat > (this.keyFrames[b]).knot && b < i) {
/* 254 */       b++;
/*     */     }
/*     */     
/* 257 */     if (b == 1) {
/* 258 */       this.currentU = 0.0F;
/* 259 */       this.lowerKnot = 1;
/* 260 */       this.upperKnot = 2;
/*     */     } else {
/* 262 */       this.currentU = (paramFloat - (this.keyFrames[b - 1]).knot) / ((this.keyFrames[b]).knot - (this.keyFrames[b - 1]).knot);
/*     */       
/* 264 */       this.lowerKnot = b - 1;
/* 265 */       this.upperKnot = b;
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
/*     */ 
/*     */ 
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
/* 291 */     super.duplicateNode(paramNode, paramBoolean);
/* 292 */     KBSplinePathInterpolator kBSplinePathInterpolator = (KBSplinePathInterpolator)paramNode;
/*     */     
/* 294 */     setAlpha(kBSplinePathInterpolator.getAlpha());
/* 295 */     this.keysLength = kBSplinePathInterpolator.keysLength;
/* 296 */     this.keyFrames = new KBKeyFrame[this.keysLength];
/* 297 */     System.arraycopy(kBSplinePathInterpolator.keyFrames, 0, this.keyFrames, 0, this.keysLength);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\behaviors\interpolators\KBSplinePathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */