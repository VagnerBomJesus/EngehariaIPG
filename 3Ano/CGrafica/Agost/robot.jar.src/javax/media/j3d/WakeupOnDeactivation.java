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
/*    */ public final class WakeupOnDeactivation
/*    */   extends WakeupCriterion
/*    */ {
/*    */   static final int COND_IN_BS_LIST = 0;
/*    */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 1;
/*    */   
/* 33 */   public WakeupOnDeactivation() { WakeupIndexedList.init(this, 1); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void setTriggered() {
/* 42 */     this.triggered = true;
/* 43 */     if (this.parent == null) {
/* 44 */       setConditionMet(this.id, Boolean.FALSE);
/*    */     } else {
/* 46 */       this.parent.setConditionMet(this.id, Boolean.FALSE);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 55 */     this.behav.wakeupArray[1] = this.behav.wakeupArray[1] + 1;
/* 56 */     this.behav.wakeupMask |= 0x2;
/* 57 */     paramBehaviorStructure.wakeupOnDeactivation.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 66 */     this.behav.wakeupArray[1] = this.behav.wakeupArray[1] - 1;
/* 67 */     if (this.behav.wakeupArray[1] == 0) {
/* 68 */       this.behav.wakeupMask &= 0xFFFFFFFD;
/*    */     }
/* 70 */     paramBehaviorStructure.wakeupOnDeactivation.remove(this);
/*    */   }
/*    */   
/*    */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\WakeupOnDeactivation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */