/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SwitchState
/*     */ {
/*     */   long[] compositeSwitchMask;
/*     */   boolean cachedSwitchOn;
/*     */   boolean currentSwitchOn;
/*     */   boolean lastSwitchOn;
/*     */   boolean initialized;
/*     */   CachedTargets cachedTargets;
/*     */   boolean inSwitch;
/*     */   
/*     */   public SwitchState(boolean paramBoolean) {
/*  21 */     this.compositeSwitchMask = new long[] { 0L };
/*     */ 
/*     */     
/*  24 */     this.cachedSwitchOn = true;
/*     */ 
/*     */     
/*  27 */     this.currentSwitchOn = true;
/*     */ 
/*     */     
/*  30 */     this.lastSwitchOn = true;
/*     */     
/*  32 */     this.initialized = false;
/*     */     
/*  34 */     this.cachedTargets = null;
/*     */     
/*  36 */     this.inSwitch = false;
/*     */ 
/*     */     
/*  39 */     this.inSwitch = paramBoolean;
/*  40 */     this.initialized = !paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*  44 */   void dump() { System.err.println(" MASK " + this.compositeSwitchMask[0] + " CACH " + this.cachedSwitchOn + " CURR " + this.currentSwitchOn + " LAST " + this.lastSwitchOn); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateCompositeSwitchMask(int paramInt, boolean paramBoolean) {
/*  52 */     if (paramInt < 64) {
/*  53 */       if (paramBoolean) {
/*  54 */         this.compositeSwitchMask[0] = this.compositeSwitchMask[0] & (1 << paramInt ^ 0xFFFFFFFF);
/*     */       } else {
/*  56 */         this.compositeSwitchMask[0] = this.compositeSwitchMask[0] | (1 << paramInt);
/*     */       } 
/*     */     } else {
/*     */       
/*  60 */       int i = paramInt / 64;
/*  61 */       int j = paramInt % 64;
/*     */       
/*  63 */       if (i > this.compositeSwitchMask.length) {
/*  64 */         long[] arrayOfLong = new long[i + 1];
/*  65 */         System.arraycopy(this.compositeSwitchMask, 0, arrayOfLong, 0, i);
/*     */         
/*  67 */         this.compositeSwitchMask = arrayOfLong;
/*     */       } 
/*  69 */       if (paramBoolean) {
/*  70 */         this.compositeSwitchMask[i] = this.compositeSwitchMask[i] & (1 << j ^ 0xFFFFFFFF);
/*     */       } else {
/*  72 */         this.compositeSwitchMask[i] = this.compositeSwitchMask[i] | (1 << j);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void initSwitchOn() {
/*  78 */     boolean bool = evalCompositeSwitchOn();
/*  79 */     this.currentSwitchOn = this.lastSwitchOn = this.cachedSwitchOn = bool;
/*     */     
/*  81 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */   
/*  85 */   void updateCurrentSwitchOn() { this.currentSwitchOn = !this.currentSwitchOn; }
/*     */ 
/*     */ 
/*     */   
/*  89 */   void updateLastSwitchOn() { this.lastSwitchOn = this.currentSwitchOn; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   void updateCachedSwitchOn() { this.cachedSwitchOn = !this.cachedSwitchOn; }
/*     */ 
/*     */   
/*     */   boolean evalCompositeSwitchOn() {
/*     */     boolean bool;
/*  98 */     if (this.compositeSwitchMask.length == 1) {
/*  99 */       bool = (this.compositeSwitchMask[0] == 0L);
/*     */     } else {
/* 101 */       bool = true;
/* 102 */       for (byte b = 0; b < this.compositeSwitchMask.length; b++) {
/* 103 */         if (this.compositeSwitchMask[b] != 0L) {
/* 104 */           bool = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 109 */     return bool;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\SwitchState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */