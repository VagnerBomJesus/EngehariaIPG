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
/*     */ public final class WakeupOnSensorEntry
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_BS_LIST = 0;
/*     */   static final int SENSORENTRY_IN_BS_LIST = 1;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*     */   Bounds region;
/*     */   Bounds transformedRegion;
/*     */   Sensor armingSensor;
/*     */   
/*     */   public WakeupOnSensorEntry(Bounds paramBounds) {
/*  41 */     this.region = (Bounds)paramBounds.clone();
/*  42 */     WakeupIndexedList.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public Bounds getBounds() { return (Bounds)this.region.clone(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformRegion() {
/*  57 */     if (this.transformedRegion != null) {
/*  58 */       this.transformedRegion.set(this.region);
/*     */     }
/*     */     else {
/*     */       
/*  62 */       this.transformedRegion = (Bounds)this.region.clone();
/*     */     } 
/*  64 */     this.transformedRegion.transform(this.behav.getCurrentLocalToVworld(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/*  73 */     paramBehaviorStructure.addSensorEntryCondition(this);
/*  74 */     if (this.behav != null && this.behav.enable) {
/*  75 */       paramBehaviorStructure.activeWakeupOnSensorCount++;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/*  85 */     paramBehaviorStructure.removeSensorEntryCondition(this);
/*  86 */     if (this.behav != null && this.behav.enable) {
/*  87 */       paramBehaviorStructure.activeWakeupOnSensorCount--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   void setTarget(Sensor paramSensor) { this.armingSensor = paramSensor; }
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
/* 110 */     if (this.behav == null) {
/* 111 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnSensorEntry0"));
/*     */     }
/*     */     
/* 114 */     synchronized (this.behav) {
/* 115 */       if (!this.behav.inCallback) {
/* 116 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnSensorEntry0"));
/*     */       }
/*     */     } 
/*     */     
/* 120 */     return this.armingSensor;
/*     */   }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\WakeupOnSensorEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */