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
/*     */ public abstract class WakeupCondition
/*     */ {
/*     */   static final int ALL_ELEMENTS = 0;
/*     */   static final int TRIGGERED_ELEMENTS = 1;
/*     */   boolean conditionMet = false;
/*  38 */   WakeupCondition parent = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int id;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   BehaviorRetained behav = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   WakeupCriteriaEnumerator allEnum = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   WakeupCriteriaEnumerator trigEnum = null;
/*     */ 
/*     */ 
/*     */   
/*     */   int[][] listIdx;
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration allElements() {
/*  67 */     if (this.allEnum == null) {
/*  68 */       this.allEnum = new WakeupCriteriaEnumerator(this, 0);
/*     */     } else {
/*  70 */       this.allEnum.reset(this, 0);
/*     */     } 
/*  72 */     return this.allEnum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration triggeredElements() {
/*  79 */     if (this.trigEnum == null) {
/*  80 */       this.trigEnum = new WakeupCriteriaEnumerator(this, 1);
/*     */     } else {
/*  82 */       this.trigEnum.reset(this, 1);
/*     */     } 
/*  84 */     return this.trigEnum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setConditionMet(int paramInt, Boolean paramBoolean) {
/*  92 */     if (!this.conditionMet) {
/*  93 */       this.conditionMet = true;
/*  94 */       J3dMessage j3dMessage = new J3dMessage();
/*  95 */       j3dMessage.type = 28;
/*  96 */       j3dMessage.threads = 256;
/*  97 */       j3dMessage.universe = this.behav.universe;
/*  98 */       j3dMessage.args[0] = this.behav;
/*  99 */       j3dMessage.args[1] = paramBoolean;
/* 100 */       j3dMessage.args[2] = this;
/* 101 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void buildTree(WakeupCondition paramWakeupCondition, int paramInt, BehaviorRetained paramBehaviorRetained) {
/* 109 */     this.parent = paramWakeupCondition;
/* 110 */     this.behav = paramBehaviorRetained;
/* 111 */     this.id = paramInt;
/* 112 */     this.conditionMet = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   void cleanTree(BehaviorStructure paramBehaviorStructure) { this.conditionMet = false; }
/*     */ 
/*     */ 
/*     */   
/* 126 */   void reInsertElapseTimeCond() { this.conditionMet = false; }
/*     */ 
/*     */ 
/*     */   
/* 130 */   void resetTree() { this.conditionMet = false; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\WakeupCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */