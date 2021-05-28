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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransparencyInterpolator
/*     */   extends Interpolator
/*     */ {
/*     */   TransparencyAttributes target;
/*     */   float minimumTransparency;
/*     */   float maximumTransparency;
/*  52 */   private float prevAlphaValue = NaNF;
/*  53 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TransparencyInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransparencyInterpolator(Alpha paramAlpha, TransparencyAttributes paramTransparencyAttributes) {
/*  70 */     super(paramAlpha);
/*     */     
/*  72 */     this.target = paramTransparencyAttributes;
/*  73 */     this.minimumTransparency = 0.0F;
/*  74 */     this.maximumTransparency = 1.0F;
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
/*     */   public TransparencyInterpolator(Alpha paramAlpha, TransparencyAttributes paramTransparencyAttributes, float paramFloat1, float paramFloat2) {
/*  91 */     super(paramAlpha);
/*     */     
/*  93 */     this.target = paramTransparencyAttributes;
/*  94 */     this.minimumTransparency = paramFloat1;
/*  95 */     this.maximumTransparency = paramFloat2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setMinimumTransparency(float paramFloat) { this.minimumTransparency = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public float getMinimumTransparency() { return this.minimumTransparency; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setMaximumTransparency(float paramFloat) { this.maximumTransparency = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public float getMaximumTransparency() { return this.maximumTransparency; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setTarget(TransparencyAttributes paramTransparencyAttributes) { this.target = paramTransparencyAttributes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public TransparencyAttributes getTarget() { return this.target; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 160 */     WakeupCriterion wakeupCriterion = this.passiveWakeupCriterion;
/*     */     
/* 162 */     if (this.alpha != null) {
/* 163 */       float f = this.alpha.value();
/*     */       
/* 165 */       if (f != this.prevAlphaValue) {
/* 166 */         float f1 = (float)((1.0D - f) * this.minimumTransparency + (f * this.maximumTransparency));
/*     */ 
/*     */         
/* 169 */         this.target.setTransparency(f1);
/* 170 */         this.prevAlphaValue = f;
/*     */       } 
/* 172 */       if (!this.alpha.finished() && !this.alpha.isPaused()) {
/* 173 */         wakeupCriterion = this.defaultWakeupCriterion;
/*     */       }
/*     */     } 
/* 176 */     wakeupOn(wakeupCriterion);
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
/* 194 */     TransparencyInterpolator transparencyInterpolator = new TransparencyInterpolator();
/* 195 */     transparencyInterpolator.duplicateNode(this, paramBoolean);
/* 196 */     return transparencyInterpolator;
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
/* 222 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 224 */     TransparencyInterpolator transparencyInterpolator = (TransparencyInterpolator)paramNode;
/*     */ 
/*     */     
/* 227 */     setMinimumTransparency(transparencyInterpolator.getMinimumTransparency());
/* 228 */     setMaximumTransparency(transparencyInterpolator.getMaximumTransparency());
/*     */ 
/*     */     
/* 231 */     setTarget(transparencyInterpolator.getTarget());
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
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 260 */     super.updateNodeReferences(paramNodeReferenceTable);
/*     */ 
/*     */     
/* 263 */     TransparencyAttributes transparencyAttributes = getTarget();
/*     */     
/* 265 */     if (transparencyAttributes != null)
/* 266 */       setTarget((TransparencyAttributes)paramNodeReferenceTable.getNewObjectReference(transparencyAttributes)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\TransparencyInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */