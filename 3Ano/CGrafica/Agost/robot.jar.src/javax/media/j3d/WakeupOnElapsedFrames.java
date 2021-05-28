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
/*     */ public final class WakeupOnElapsedFrames
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_BS_LIST = 0;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 1;
/*     */   boolean passive;
/*     */   int frameCount;
/*     */   int countdown;
/*     */   
/*  61 */   public WakeupOnElapsedFrames(int paramInt) { this(paramInt, false); }
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
/*     */   public WakeupOnElapsedFrames(int paramInt, boolean paramBoolean) {
/*  82 */     if (paramInt < 0) {
/*  83 */       throw new IllegalArgumentException(J3dI18N.getString("WakeupOnElapsedFrames0"));
/*     */     }
/*  85 */     this.frameCount = paramInt;
/*  86 */     this.passive = paramBoolean;
/*  87 */     WakeupIndexedList.init(this, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public int getElapsedFrameCount() { return this.frameCount; }
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
/* 110 */   public boolean isPassive() { return this.passive; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void newFrame() {
/* 117 */     if (this.countdown == 0) {
/* 118 */       setTriggered();
/*     */     } else {
/* 120 */       this.countdown--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 131 */     this.countdown = this.frameCount;
/* 132 */     paramBehaviorStructure.wakeupOnElapsedFrames.add(this);
/* 133 */     if (!this.passive && this.behav != null && this.behav.enable) {
/* 134 */       paramBehaviorStructure.activeWakeupOnFrameCount++;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 139 */     VirtualUniverse.mc.sendRunMessage(paramBehaviorStructure.universe, 256);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 149 */     paramBehaviorStructure.wakeupOnElapsedFrames.remove(this);
/* 150 */     if (!this.passive && this.behav != null && this.behav.enable) {
/* 151 */       paramBehaviorStructure.activeWakeupOnFrameCount--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) { this.countdown = this.frameCount; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\WakeupOnElapsedFrames.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */