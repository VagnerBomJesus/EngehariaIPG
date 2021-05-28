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
/*    */ public final class WakeupOr
/*    */   extends WakeupCondition
/*    */ {
/*    */   WakeupCriterion[] conditions;
/*    */   
/*    */   public WakeupOr(WakeupCriterion[] paramArrayOfWakeupCriterion) {
/* 36 */     this.conditions = new WakeupCriterion[paramArrayOfWakeupCriterion.length];
/*    */     
/* 38 */     for (byte b = 0; b < paramArrayOfWakeupCriterion.length; b++) {
/* 39 */       this.conditions[b] = paramArrayOfWakeupCriterion[b];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void setConditionMet(int paramInt, Boolean paramBoolean) {
/* 47 */     if (this.parent == null) {
/* 48 */       super.setConditionMet(this.id, paramBoolean);
/*    */     } else {
/* 50 */       this.parent.setConditionMet(this.id, paramBoolean);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void buildTree(WakeupCondition paramWakeupCondition, int paramInt, BehaviorRetained paramBehaviorRetained) {
/* 59 */     super.buildTree(paramWakeupCondition, paramInt, paramBehaviorRetained);
/*    */     
/* 61 */     for (byte b = 0; b < this.conditions.length; b++) {
/* 62 */       if (this.conditions[b] != null) {
/* 63 */         this.conditions[b].buildTree(this, b, paramBehaviorRetained);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void cleanTree(BehaviorStructure paramBehaviorStructure) {
/* 73 */     for (byte b = 0; b < this.conditions.length; b++) {
/* 74 */       this.conditions[b].cleanTree(paramBehaviorStructure);
/*    */     }
/*    */   }
/*    */   
/*    */   void reInsertElapseTimeCond() {
/* 79 */     super.reInsertElapseTimeCond();
/* 80 */     for (byte b = 0; b < this.conditions.length; b++) {
/* 81 */       if (this.conditions[b] != null) {
/* 82 */         this.conditions[b].reInsertElapseTimeCond();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void resetTree() {
/* 92 */     super.resetTree();
/* 93 */     for (byte b = 0; b < this.conditions.length; b++) {
/* 94 */       if (this.conditions[b] != null)
/* 95 */         this.conditions[b].resetTree(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\WakeupOr.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */