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
/*     */ public final class WakeupOnSensorExit
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_BS_LIST = 0;
/*     */   static final int SENSOREXIT_IN_BS_LIST = 1;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*     */   Bounds region;
/*     */   Bounds transformedRegion;
/*     */   Sensor armingSensor;
/*     */   
/*     */   public WakeupOnSensorExit(Bounds paramBounds) {
/*  40 */     this.region = (Bounds)paramBounds.clone();
/*  41 */     WakeupIndexedList.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public Bounds getBounds() { return (Bounds)this.region.clone(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformRegion() {
/*  56 */     if (this.transformedRegion != null) {
/*  57 */       this.transformedRegion.set(this.region);
/*     */     }
/*     */     else {
/*     */       
/*  61 */       this.transformedRegion = (Bounds)this.region.clone();
/*     */     } 
/*  63 */     this.transformedRegion.transform(this.behav.getCurrentLocalToVworld(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/*  71 */     paramBehaviorStructure.addSensorExitCondition(this);
/*  72 */     if (this.behav != null && this.behav.enable) {
/*  73 */       paramBehaviorStructure.activeWakeupOnSensorCount++;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/*  83 */     paramBehaviorStructure.removeSensorExitCondition(this);
/*  84 */     if (this.behav != null && this.behav.enable) {
/*  85 */       paramBehaviorStructure.activeWakeupOnSensorCount--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   void setTarget(Sensor paramSensor) { this.armingSensor = paramSensor; }
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
/*     */   public Sensor getTriggeringSensor() {
/* 109 */     if (this.behav == null) {
/* 110 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnSensorExit0"));
/*     */     }
/*     */     
/* 113 */     synchronized (this.behav) {
/* 114 */       if (!this.behav.inCallback) {
/* 115 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnSensorExit0"));
/*     */       }
/*     */     } 
/*     */     
/* 119 */     return this.armingSensor;
/*     */   }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\WakeupOnSensorExit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */