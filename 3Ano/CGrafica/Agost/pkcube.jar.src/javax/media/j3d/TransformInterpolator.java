/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TransformInterpolator
/*     */   extends Interpolator
/*     */ {
/*  29 */   protected TransformGroup target = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   protected Transform3D axis = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   protected Transform3D axisInverse = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   private Transform3D currentTransform = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private float prevAlphaValue = NaNF;
/*  52 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup) {
/*  70 */     super(paramAlpha);
/*  71 */     this.target = paramTransformGroup;
/*  72 */     this.axis.setIdentity();
/*  73 */     this.axisInverse.setIdentity();
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
/*     */   public TransformInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D) {
/*  87 */     super(paramAlpha);
/*  88 */     this.target = paramTransformGroup;
/*  89 */     this.axis.set(paramTransform3D);
/*  90 */     this.axisInverse.invert(this.axis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setTarget(TransformGroup paramTransformGroup) { this.target = paramTransformGroup; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public TransformGroup getTarget() { return this.target; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransformAxis(Transform3D paramTransform3D) {
/* 117 */     this.axis.set(paramTransform3D);
/* 118 */     this.axisInverse.invert(this.axis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public Transform3D getTransformAxis() { return new Transform3D(this.axis); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void computeTransform(float paramFloat, Transform3D paramTransform3D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 150 */     WakeupCriterion wakeupCriterion = this.passiveWakeupCriterion;
/*     */     
/* 152 */     if (this.alpha != null) {
/* 153 */       float f = this.alpha.value();
/* 154 */       if (f != this.prevAlphaValue) {
/* 155 */         computeTransform(f, this.currentTransform);
/* 156 */         this.target.setTransform(this.currentTransform);
/* 157 */         this.prevAlphaValue = f;
/*     */       } 
/* 159 */       if (!this.alpha.finished() && !this.alpha.isPaused()) {
/* 160 */         wakeupCriterion = this.defaultWakeupCriterion;
/*     */       }
/*     */     } 
/* 163 */     wakeupOn(wakeupCriterion);
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
/* 188 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 190 */     TransformInterpolator transformInterpolator = (TransformInterpolator)paramNode;
/*     */     
/* 192 */     setTransformAxis(transformInterpolator.getTransformAxis());
/*     */ 
/*     */     
/* 195 */     setTarget(transformInterpolator.getTarget());
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 225 */     super.updateNodeReferences(paramNodeReferenceTable);
/*     */ 
/*     */     
/* 228 */     TransformGroup transformGroup = getTarget();
/*     */     
/* 230 */     if (transformGroup != null)
/* 231 */       setTarget((TransformGroup)paramNodeReferenceTable.getNewObjectReference(transformGroup)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\TransformInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */