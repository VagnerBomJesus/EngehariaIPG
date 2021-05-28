/*    */ package javax.media.j3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WakeupCriterion
/*    */   extends WakeupCondition
/*    */ {
/*    */   boolean triggered;
/*    */   
/* 43 */   public boolean hasTriggered() { return this.triggered; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void setTriggered() {
/* 50 */     this.triggered = true;
/* 51 */     if (this.parent == null) {
/* 52 */       setConditionMet(this.id, Boolean.TRUE);
/*    */     } else {
/* 54 */       this.parent.setConditionMet(this.id, Boolean.TRUE);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void buildTree(WakeupCondition paramWakeupCondition, int paramInt, BehaviorRetained paramBehaviorRetained) {
/* 63 */     super.buildTree(paramWakeupCondition, paramInt, paramBehaviorRetained);
/* 64 */     this.triggered = false;
/* 65 */     addBehaviorCondition(paramBehaviorRetained.universe.behaviorStructure);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void cleanTree(BehaviorStructure paramBehaviorStructure) {
/* 77 */     this.conditionMet = false;
/* 78 */     removeBehaviorCondition(paramBehaviorStructure);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void resetTree() {
/* 86 */     this.conditionMet = false;
/* 87 */     this.triggered = false;
/* 88 */     resetBehaviorCondition(this.behav.universe.behaviorStructure);
/*    */   }
/*    */   
/*    */   abstract void addBehaviorCondition(BehaviorStructure paramBehaviorStructure);
/*    */   
/*    */   abstract void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure);
/*    */   
/*    */   abstract void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure);
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\WakeupCriterion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */