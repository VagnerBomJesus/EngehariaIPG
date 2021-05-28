/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotationInterpolator
/*     */   extends TransformInterpolator
/*     */ {
/*     */   float minimumAngle;
/*     */   float maximumAngle;
/*  29 */   private Transform3D rotation = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private float prevAlphaValue = NaNF;
/*  36 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RotationInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RotationInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup) {
/*  51 */     super(paramAlpha, paramTransformGroup);
/*  52 */     this.minimumAngle = 0.0F;
/*  53 */     this.maximumAngle = 6.2831855F;
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
/*     */   public RotationInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float paramFloat1, float paramFloat2) {
/*  73 */     super(paramAlpha, paramTransformGroup, paramTransform3D);
/*  74 */     this.minimumAngle = paramFloat1;
/*  75 */     this.maximumAngle = paramFloat2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void setMinimumAngle(float paramFloat) { this.minimumAngle = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public float getMinimumAngle() { return this.minimumAngle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setMaximumAngle(float paramFloat) { this.maximumAngle = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public float getMaximumAngle() { return this.maximumAngle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setAxisOfRotation(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public Transform3D getAxisOfRotation() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
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
/* 141 */     double d = (1.0D - paramFloat) * this.minimumAngle + (paramFloat * this.maximumAngle);
/*     */ 
/*     */     
/* 144 */     this.rotation.rotY(d);
/* 145 */     paramTransform3D.mul(this.axis, this.rotation);
/* 146 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/* 164 */     RotationInterpolator rotationInterpolator = new RotationInterpolator();
/* 165 */     rotationInterpolator.duplicateNode(this, paramBoolean);
/* 166 */     return rotationInterpolator;
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
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 192 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 194 */     RotationInterpolator rotationInterpolator = (RotationInterpolator)paramNode;
/*     */     
/* 196 */     setMinimumAngle(rotationInterpolator.getMinimumAngle());
/* 197 */     setMaximumAngle(rotationInterpolator.getMaximumAngle());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\RotationInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */