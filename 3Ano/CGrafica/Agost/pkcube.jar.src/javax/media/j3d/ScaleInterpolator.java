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
/*     */ public class ScaleInterpolator
/*     */   extends TransformInterpolator
/*     */ {
/*     */   float minimumScale;
/*     */   float maximumScale;
/*  29 */   private Transform3D scale = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private float prevAlphaValue = NaNF;
/*  37 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ScaleInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScaleInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup) {
/*  55 */     super(paramAlpha, paramTransformGroup);
/*  56 */     this.minimumScale = 0.1F;
/*  57 */     this.maximumScale = 1.0F;
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
/*     */   public ScaleInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float paramFloat1, float paramFloat2) {
/*  78 */     super(paramAlpha, paramTransformGroup, paramTransform3D);
/*     */     
/*  80 */     this.minimumScale = paramFloat1;
/*  81 */     this.maximumScale = paramFloat2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void setMinimumScale(float paramFloat) { this.minimumScale = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public float getMinimumScale() { return this.minimumScale; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setMaximumScale(float paramFloat) { this.maximumScale = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public float getMaximumScale() { return this.maximumScale; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setAxisOfScale(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public Transform3D getAxisOfScale() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 145 */     double d = (1.0D - paramFloat) * this.minimumScale + (paramFloat * this.maximumScale);
/*     */ 
/*     */     
/* 148 */     this.scale.set(d);
/* 149 */     paramTransform3D.mul(this.axis, this.scale);
/* 150 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/* 168 */     ScaleInterpolator scaleInterpolator = new ScaleInterpolator();
/* 169 */     scaleInterpolator.duplicateNode(this, paramBoolean);
/* 170 */     return scaleInterpolator;
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
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 197 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 199 */     ScaleInterpolator scaleInterpolator = (ScaleInterpolator)paramNode;
/*     */     
/* 201 */     setMinimumScale(scaleInterpolator.getMinimumScale());
/* 202 */     setMaximumScale(scaleInterpolator.getMaximumScale());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\ScaleInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */