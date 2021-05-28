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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WakeupOnBehaviorPost
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_BS_LIST = 0;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 1;
/*     */   Behavior armingBehavior;
/*     */   Behavior triggeringBehavior;
/*     */   int post;
/*     */   int triggeringPost;
/*     */   
/*     */   public WakeupOnBehaviorPost(Behavior paramBehavior, int paramInt) {
/*  45 */     this.armingBehavior = paramBehavior;
/*  46 */     this.post = paramInt;
/*  47 */     this.triggeringPost = -1;
/*  48 */     this.triggeringBehavior = null;
/*  49 */     WakeupIndexedList.init(this, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public int getPostId() { return this.post; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public Behavior getBehavior() { return this.armingBehavior; }
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
/*  77 */   public int getTriggeringPostId() { return this.triggeringPost; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public Behavior getTriggeringBehavior() { return this.triggeringBehavior; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) { paramBehaviorStructure.wakeupOnBehaviorPost.add(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) { paramBehaviorStructure.wakeupOnBehaviorPost.remove(this); }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\WakeupOnBehaviorPost.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */