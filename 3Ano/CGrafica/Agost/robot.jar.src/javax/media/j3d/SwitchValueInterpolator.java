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
/*     */ public class SwitchValueInterpolator
/*     */   extends Interpolator
/*     */ {
/*     */   Switch target;
/*     */   int firstSwitchIndex;
/*     */   int lastSwitchIndex;
/*     */   int childCount;
/*  36 */   private float prevAlphaValue = NaNF;
/*  37 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SwitchValueInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SwitchValueInterpolator(Alpha paramAlpha, Switch paramSwitch) {
/*  54 */     super(paramAlpha);
/*     */     
/*  56 */     this.target = paramSwitch;
/*  57 */     this.firstSwitchIndex = 0;
/*  58 */     this.childCount = paramSwitch.numChildren();
/*  59 */     this.lastSwitchIndex = this.childCount - 1;
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
/*     */   public SwitchValueInterpolator(Alpha paramAlpha, Switch paramSwitch, int paramInt1, int paramInt2) {
/*  78 */     super(paramAlpha);
/*     */     
/*  80 */     this.target = paramSwitch;
/*  81 */     this.firstSwitchIndex = paramInt1;
/*  82 */     this.lastSwitchIndex = paramInt2;
/*  83 */     computeChildCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstChildIndex(int paramInt) {
/*  91 */     this.firstSwitchIndex = paramInt;
/*  92 */     computeChildCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public int getFirstChildIndex() { return this.firstSwitchIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastChildIndex(int paramInt) {
/* 108 */     this.lastSwitchIndex = paramInt;
/* 109 */     computeChildCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public int getLastChildIndex() { return this.lastSwitchIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public void setTarget(Switch paramSwitch) { this.target = paramSwitch; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public Switch getTarget() { return this.target; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */       
/* 155 */       if (f != this.prevAlphaValue) {
/*     */         int i;
/*     */         
/* 158 */         if (this.lastSwitchIndex > this.firstSwitchIndex) {
/* 159 */           i = this.firstSwitchIndex + (int)(f * (this.childCount - 1) + 0.5F);
/*     */         } else {
/*     */           
/* 162 */           i = this.firstSwitchIndex - (int)(f * (this.childCount - 1) + 0.5F);
/*     */         } 
/*     */         
/* 165 */         this.target.setWhichChild(i);
/* 166 */         this.prevAlphaValue = f;
/*     */       } 
/* 168 */       if (!this.alpha.finished() && !this.alpha.isPaused()) {
/* 169 */         wakeupCriterion = this.defaultWakeupCriterion;
/*     */       }
/*     */     } 
/* 172 */     wakeupOn(wakeupCriterion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void computeChildCount() {
/* 180 */     if (this.lastSwitchIndex >= this.firstSwitchIndex) {
/* 181 */       this.childCount = this.lastSwitchIndex - this.firstSwitchIndex + 1;
/*     */     } else {
/* 183 */       this.childCount = this.firstSwitchIndex - this.lastSwitchIndex + 1;
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 202 */     SwitchValueInterpolator switchValueInterpolator = new SwitchValueInterpolator();
/* 203 */     switchValueInterpolator.duplicateNode(this, paramBoolean);
/* 204 */     return switchValueInterpolator;
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
/* 230 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 232 */     SwitchValueInterpolator switchValueInterpolator = (SwitchValueInterpolator)paramNode;
/*     */ 
/*     */     
/* 235 */     setFirstChildIndex(switchValueInterpolator.getFirstChildIndex());
/* 236 */     setLastChildIndex(switchValueInterpolator.getLastChildIndex());
/*     */     
/* 238 */     setTarget(switchValueInterpolator.getTarget());
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
/* 267 */     super.updateNodeReferences(paramNodeReferenceTable);
/*     */ 
/*     */     
/* 270 */     Switch switch = getTarget();
/*     */     
/* 272 */     if (switch != null)
/* 273 */       setTarget((Switch)paramNodeReferenceTable.getNewObjectReference(switch)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\SwitchValueInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */