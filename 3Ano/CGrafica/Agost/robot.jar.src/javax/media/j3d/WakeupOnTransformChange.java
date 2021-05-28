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
/*    */ public final class WakeupOnTransformChange
/*    */   extends WakeupCriterion
/*    */ {
/*    */   static final int COND_IN_BS_LIST = 0;
/*    */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 1;
/*    */   TransformGroupRetained transform;
/*    */   
/*    */   public WakeupOnTransformChange(TransformGroup paramTransformGroup) {
/* 37 */     this.transform = (TransformGroupRetained)paramTransformGroup.retained;
/* 38 */     synchronized (this.transform) {
/* 39 */       if (this.transform.transformChange == null) {
/* 40 */         this.transform.transformChange = new WakeupIndexedList(1, WakeupOnTransformChange.class, 0, this.transform.universe);
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 46 */     WakeupIndexedList.init(this, 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public TransformGroup getTransformGroup() { return (TransformGroup)this.transform.source; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) { this.transform.addCondition(this); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) { this.transform.removeCondition(this); }
/*    */   
/*    */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\WakeupOnTransformChange.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */