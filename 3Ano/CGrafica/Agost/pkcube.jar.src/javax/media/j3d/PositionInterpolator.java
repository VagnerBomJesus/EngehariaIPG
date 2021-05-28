/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Vector3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PositionInterpolator
/*     */   extends TransformInterpolator
/*     */ {
/*  30 */   private Transform3D translation = new Transform3D();
/*  31 */   private Vector3d transv = new Vector3d();
/*     */ 
/*     */   
/*     */   float startPosition;
/*     */ 
/*     */   
/*     */   float endPosition;
/*     */ 
/*     */   
/*  40 */   private float prevAlphaValue = NaNF;
/*  41 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PositionInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PositionInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup) {
/*  56 */     super(paramAlpha, paramTransformGroup);
/*     */     
/*  58 */     this.startPosition = 0.0F;
/*  59 */     this.endPosition = 1.0F;
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
/*     */   public PositionInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float paramFloat1, float paramFloat2) {
/*  80 */     super(paramAlpha, paramTransformGroup, paramTransform3D);
/*     */     
/*  82 */     this.startPosition = paramFloat1;
/*  83 */     this.endPosition = paramFloat2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setStartPosition(float paramFloat) { this.startPosition = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public float getStartPosition() { return this.startPosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setEndPosition(float paramFloat) { this.endPosition = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public float getEndPosition() { return this.endPosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void setAxisOfTranslation(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public Transform3D getAxisOfTranslation() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 145 */     double d = (1.0D - paramFloat) * this.startPosition + (paramFloat * this.endPosition);
/*     */ 
/*     */     
/* 148 */     this.transv.set(d, 0.0D, 0.0D);
/* 149 */     this.translation.setTranslation(this.transv);
/*     */     
/* 151 */     paramTransform3D.mul(this.axis, this.translation);
/* 152 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 170 */     PositionInterpolator positionInterpolator = new PositionInterpolator();
/* 171 */     positionInterpolator.duplicateNode(this, paramBoolean);
/* 172 */     return positionInterpolator;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 197 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 199 */     PositionInterpolator positionInterpolator = (PositionInterpolator)paramNode;
/*     */     
/* 201 */     setStartPosition(positionInterpolator.getStartPosition());
/* 202 */     setEndPosition(positionInterpolator.getEndPosition());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\PositionInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */