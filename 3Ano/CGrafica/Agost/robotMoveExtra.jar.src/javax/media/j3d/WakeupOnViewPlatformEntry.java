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
/*     */ public final class WakeupOnViewPlatformEntry
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_BS_LIST = 0;
/*     */   static final int BOUNDSENTRY_IN_BS_LIST = 1;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*     */   Bounds region;
/*     */   Bounds transformedRegion;
/*     */   ViewPlatformRetained triggeredVP;
/*     */   
/*     */   public WakeupOnViewPlatformEntry(Bounds paramBounds) {
/*  46 */     this.region = (Bounds)paramBounds.clone();
/*  47 */     WakeupIndexedList.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public Bounds getBounds() { return (Bounds)this.region.clone(); }
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
/*     */   public ViewPlatform getTriggeringViewPlatform() {
/*  70 */     if (this.behav == null) {
/*  71 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnViewPlatformEntry0"));
/*     */     }
/*     */     
/*  74 */     synchronized (this.behav) {
/*  75 */       if (!this.behav.inCallback) {
/*  76 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnViewPlatformEntry0"));
/*     */       }
/*     */     } 
/*     */     
/*  80 */     return (this.triggeredVP != null) ? (ViewPlatform)this.triggeredVP.source : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformRegion(BehaviorRetained paramBehaviorRetained) {
/*  88 */     if (this.transformedRegion != null) {
/*  89 */       this.transformedRegion.set(this.region);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       this.transformedRegion = (Bounds)this.region.clone();
/*     */     } 
/*  95 */     this.transformedRegion.transform(paramBehaviorRetained.getCurrentLocalToVworld(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 103 */     updateTransformRegion(this.behav);
/* 104 */     this.behav.wakeupArray[2] = this.behav.wakeupArray[2] + 1;
/* 105 */     this.behav.wakeupMask |= 0x4;
/* 106 */     paramBehaviorStructure.addVPEntryCondition(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 115 */     this.behav.wakeupArray[2] = this.behav.wakeupArray[2] - 1;
/* 116 */     if (this.behav.wakeupArray[2] == 0) {
/* 117 */       this.behav.wakeupMask &= 0xFFFFFFFB;
/*     */     }
/* 119 */     paramBehaviorStructure.removeVPEntryCondition(this);
/*     */   }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\WakeupOnViewPlatformEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */