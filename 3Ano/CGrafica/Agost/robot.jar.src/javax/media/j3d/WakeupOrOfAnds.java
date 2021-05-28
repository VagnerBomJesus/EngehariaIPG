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
/*    */ public final class WakeupOrOfAnds
/*    */   extends WakeupCondition
/*    */ {
/*    */   WakeupAnd[] conditions;
/*    */   
/*    */   public WakeupOrOfAnds(WakeupAnd[] paramArrayOfWakeupAnd) {
/* 37 */     this.conditions = new WakeupAnd[paramArrayOfWakeupAnd.length];
/* 38 */     for (byte b = 0; b < paramArrayOfWakeupAnd.length; b++) {
/* 39 */       this.conditions[b] = paramArrayOfWakeupAnd[b];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void setConditionMet(int paramInt, Boolean paramBoolean) {
/* 48 */     if (this.parent == null) {
/* 49 */       super.setConditionMet(this.id, paramBoolean);
/*    */     } else {
/* 51 */       this.parent.setConditionMet(this.id, paramBoolean);
/*    */     } 
/*    */   }
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


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\WakeupOrOfAnds.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */