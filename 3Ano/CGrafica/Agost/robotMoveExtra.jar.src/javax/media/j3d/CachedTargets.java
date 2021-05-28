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
/*     */ class CachedTargets
/*     */ {
/*  31 */   static String[] typeString = { "GEO_TARGETS", "ENV_TARGETS", "BEH_TARGETS", "SND_TARGETS", "VPF_TARGETS", "BLN_TARGETS", "GRP_TARGETS" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   static int[] updateTargetThreads = { 8384, 4224, 256, 514, 898, 4992, 8256 };
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
/*  68 */   NnuId[][] targetArr = new NnuId[7][];
/*     */   
/*     */   int computeTargetThreads() {
/*  71 */     int i = 0;
/*     */     
/*  73 */     for (byte b = 0; b < 7; b++) {
/*  74 */       if (this.targetArr[b] != null) {
/*  75 */         i |= updateTargetThreads[b];
/*     */       }
/*     */     } 
/*  78 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   void copy(CachedTargets paramCachedTargets) {
/*  83 */     for (byte b = 0; b < 7; b++) {
/*  84 */       this.targetArr[b] = paramCachedTargets.targetArr[b];
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void replace(NnuId paramNnuId1, NnuId paramNnuId2, int paramInt) {
/*  90 */     NnuId[] arrayOfNnuId = new NnuId[this.targetArr[paramInt].length];
/*  91 */     System.arraycopy(this.targetArr[paramInt], 0, arrayOfNnuId, 0, this.targetArr[paramInt].length);
/*     */     
/*  93 */     this.targetArr[paramInt] = arrayOfNnuId;
/*  94 */     NnuIdManager.replace(paramNnuId1, paramNnuId2, (NnuId[])this.targetArr[paramInt]);
/*     */   }
/*     */ 
/*     */   
/*     */   void dump() {
/*  99 */     for (byte b = 0; b < 7; b++) {
/* 100 */       if (this.targetArr[b] != null) {
/* 101 */         System.err.println("  " + typeString[b]);
/* 102 */         for (byte b1 = 0; b1 < this.targetArr[b].length; b1++)
/* 103 */           System.err.println("  " + this.targetArr[b][b1]); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\CachedTargets.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */