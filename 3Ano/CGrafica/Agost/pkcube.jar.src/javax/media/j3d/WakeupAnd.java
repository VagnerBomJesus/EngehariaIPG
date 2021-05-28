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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WakeupAnd
/*     */   extends WakeupCondition
/*     */ {
/*     */   WakeupCriterion[] conditions;
/*     */   boolean[] conditionsMet;
/*     */   
/*     */   public WakeupAnd(WakeupCriterion[] paramArrayOfWakeupCriterion) {
/*  37 */     this.conditions = new WakeupCriterion[paramArrayOfWakeupCriterion.length];
/*  38 */     this.conditionsMet = new boolean[paramArrayOfWakeupCriterion.length];
/*     */     
/*  40 */     for (byte b = 0; b < paramArrayOfWakeupCriterion.length; b++) {
/*  41 */       this.conditions[b] = paramArrayOfWakeupCriterion[b];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setConditionMet(int paramInt, Boolean paramBoolean) {
/*  52 */     this.conditionsMet[paramInt] = true;
/*     */     
/*  54 */     for (byte b = 0; b < this.conditionsMet.length; b++) {
/*  55 */       if (!this.conditionsMet[b]) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/*  60 */     if (this.parent == null) {
/*  61 */       super.setConditionMet(this.id, paramBoolean);
/*     */     } else {
/*  63 */       this.parent.setConditionMet(this.id, paramBoolean);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void buildTree(WakeupCondition paramWakeupCondition, int paramInt, BehaviorRetained paramBehaviorRetained) {
/*  71 */     super.buildTree(paramWakeupCondition, paramInt, paramBehaviorRetained);
/*     */     
/*  73 */     for (byte b = 0; b < this.conditions.length; b++) {
/*  74 */       if (this.conditions[b] != null) {
/*  75 */         this.conditions[b].buildTree(this, b, paramBehaviorRetained);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void cleanTree(BehaviorStructure paramBehaviorStructure) {
/*  85 */     for (byte b = 0; b < this.conditions.length; b++) {
/*  86 */       this.conditions[b].cleanTree(paramBehaviorStructure);
/*  87 */       this.conditionsMet[b] = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void reInsertElapseTimeCond() {
/*  93 */     super.reInsertElapseTimeCond();
/*  94 */     for (byte b = 0; b < this.conditions.length; b++) {
/*  95 */       if (this.conditions[b] != null) {
/*  96 */         this.conditions[b].reInsertElapseTimeCond();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void resetTree() {
/* 107 */     super.resetTree();
/* 108 */     for (byte b = 0; b < this.conditions.length; b++) {
/* 109 */       if (this.conditions[b] != null)
/* 110 */         this.conditions[b].resetTree(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\WakeupAnd.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */