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
/*    */ public final class WakeupOnElapsedTime
/*    */   extends WakeupCriterion
/*    */ {
/*    */   long wait;
/*    */   long triggeredTime;
/*    */   
/*    */   public WakeupOnElapsedTime(long paramLong) {
/* 35 */     if (paramLong <= 0L)
/* 36 */       throw new IllegalArgumentException(J3dI18N.getString("WakeupOnElapsedTime0")); 
/* 37 */     this.wait = paramLong;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public long getElapsedFrameTime() { return this.wait; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 54 */     this.triggeredTime = this.wait + J3dClock.currentTimeMillis();
/* 55 */     this.behav.wakeupArray[4] = this.behav.wakeupArray[4] + 1;
/* 56 */     this.behav.wakeupMask |= 0x10;
/* 57 */     VirtualUniverse.mc.timerThread.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 66 */     this.behav.wakeupArray[4] = this.behav.wakeupArray[4] - 1;
/* 67 */     if (this.behav.wakeupArray[4] == 0) {
/* 68 */       this.behav.wakeupMask &= 0xFFFFFFEF;
/*    */     }
/* 70 */     VirtualUniverse.mc.timerThread.remove(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void reInsertElapseTimeCond() {
/* 80 */     super.reInsertElapseTimeCond();
/* 81 */     this.triggeredTime = this.wait + J3dClock.currentTimeMillis();
/* 82 */     VirtualUniverse.mc.timerThread.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 91 */     this.triggeredTime = this.wait + J3dClock.currentTimeMillis();
/* 92 */     VirtualUniverse.mc.timerThread.add(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\WakeupOnElapsedTime.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */