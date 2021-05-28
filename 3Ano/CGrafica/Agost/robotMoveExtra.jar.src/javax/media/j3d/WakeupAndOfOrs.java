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
/*     */ 
/*     */ public final class WakeupAndOfOrs
/*     */   extends WakeupCondition
/*     */ {
/*     */   WakeupOr[] conditions;
/*     */   boolean[] conditionsMet;
/*     */   
/*     */   public WakeupAndOfOrs(WakeupOr[] paramArrayOfWakeupOr) {
/*  38 */     this.conditions = new WakeupOr[paramArrayOfWakeupOr.length];
/*  39 */     this.conditionsMet = new boolean[paramArrayOfWakeupOr.length];
/*     */     
/*  41 */     for (byte b = 0; b < paramArrayOfWakeupOr.length; b++) {
/*  42 */       this.conditions[b] = paramArrayOfWakeupOr[b];
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
/*  53 */     this.conditionsMet[paramInt] = true;
/*     */     
/*  55 */     for (byte b = 0; b < this.conditionsMet.length; b++) {
/*  56 */       if (!this.conditionsMet[b]) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/*  61 */     if (this.parent == null) {
/*  62 */       super.setConditionMet(this.id, paramBoolean);
/*     */     } else {
/*  64 */       this.parent.setConditionMet(this.id, paramBoolean);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void buildTree(WakeupCondition paramWakeupCondition, int paramInt, BehaviorRetained paramBehaviorRetained) {
/*  73 */     super.buildTree(paramWakeupCondition, paramInt, paramBehaviorRetained);
/*     */     
/*  75 */     for (byte b = 0; b < this.conditions.length; b++) {
/*  76 */       if (this.conditions[b] != null) {
/*  77 */         this.conditions[b].buildTree(this, b, paramBehaviorRetained);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void cleanTree(BehaviorStructure paramBehaviorStructure) {
/*  87 */     for (byte b = 0; b < this.conditions.length; b++) {
/*  88 */       this.conditions[b].cleanTree(paramBehaviorStructure);
/*  89 */       this.conditionsMet[b] = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void reInsertElapseTimeCond() {
/*  95 */     super.reInsertElapseTimeCond();
/*  96 */     for (byte b = 0; b < this.conditions.length; b++) {
/*  97 */       if (this.conditions[b] != null) {
/*  98 */         this.conditions[b].reInsertElapseTimeCond();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void resetTree() {
/* 108 */     super.resetTree();
/* 109 */     for (byte b = 0; b < this.conditions.length; b++) {
/* 110 */       if (this.conditions[b] != null)
/* 111 */         this.conditions[b].resetTree(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\WakeupAndOfOrs.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */