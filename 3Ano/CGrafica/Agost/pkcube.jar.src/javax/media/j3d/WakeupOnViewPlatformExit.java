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
/*     */ public final class WakeupOnViewPlatformExit
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_BS_LIST = 0;
/*     */   static final int BOUNDSEXIT_IN_BS_LIST = 1;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*     */   Bounds region;
/*     */   Bounds transformedRegion;
/*     */   ViewPlatformRetained triggeredVP;
/*     */   
/*     */   public WakeupOnViewPlatformExit(Bounds paramBounds) {
/*  47 */     this.region = (Bounds)paramBounds.clone();
/*  48 */     WakeupIndexedList.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public Bounds getBounds() { return (Bounds)this.region.clone(); }
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
/*  72 */     if (this.behav == null) {
/*  73 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnViewPlatformExit0"));
/*     */     }
/*     */     
/*  76 */     synchronized (this.behav) {
/*  77 */       if (!this.behav.inCallback) {
/*  78 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnViewPlatformExit0"));
/*     */       }
/*     */     } 
/*     */     
/*  82 */     return (this.triggeredVP != null) ? (ViewPlatform)this.triggeredVP.source : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformRegion(BehaviorRetained paramBehaviorRetained) {
/*  90 */     if (this.transformedRegion != null) {
/*  91 */       this.transformedRegion.set(this.region);
/*     */     }
/*     */     else {
/*     */       
/*  95 */       this.transformedRegion = (Bounds)this.region.clone();
/*     */     } 
/*  97 */     this.transformedRegion.transform(paramBehaviorRetained.getCurrentLocalToVworld(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 106 */     updateTransformRegion(this.behav);
/* 107 */     this.behav.wakeupArray[3] = this.behav.wakeupArray[3] + 1;
/* 108 */     this.behav.wakeupMask |= 0x8;
/* 109 */     paramBehaviorStructure.addVPExitCondition(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 118 */     paramBehaviorStructure.removeVPExitCondition(this);
/* 119 */     this.behav.wakeupArray[3] = this.behav.wakeupArray[3] - 1;
/* 120 */     if (this.behav.wakeupArray[3] == 0)
/* 121 */       this.behav.wakeupMask &= 0xFFFFFFF7; 
/*     */   }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\WakeupOnViewPlatformExit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */