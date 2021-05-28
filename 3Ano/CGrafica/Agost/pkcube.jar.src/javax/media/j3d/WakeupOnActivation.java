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
/*    */ public final class WakeupOnActivation
/*    */   extends WakeupCriterion
/*    */ {
/*    */   static final int COND_IN_BS_LIST = 0;
/*    */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 1;
/*    */   
/* 34 */   public WakeupOnActivation() { WakeupIndexedList.init(this, 1); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 42 */     this.behav.wakeupArray[0] = this.behav.wakeupArray[0] + 1;
/* 43 */     this.behav.wakeupMask |= 0x1;
/* 44 */     paramBehaviorStructure.wakeupOnActivation.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 53 */     this.behav.wakeupArray[0] = this.behav.wakeupArray[0] - 1;
/* 54 */     if (this.behav.wakeupArray[0] == 0) {
/* 55 */       this.behav.wakeupMask &= 0xFFFFFFFE;
/*    */     }
/* 57 */     paramBehaviorStructure.wakeupOnActivation.remove(this);
/*    */   }
/*    */   
/*    */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\WakeupOnActivation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */