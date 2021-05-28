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
/*    */ class UpdateTargets
/*    */ {
/* 17 */   static int[] updateSwitchThreads = { 4288, 4224, 256, 514, 898, 4992, 0 };
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
/* 44 */   UnorderList[] targetList = new UnorderList[7];
/*    */   
/*    */   int computeSwitchThreads() {
/* 47 */     int i = 0;
/*    */     
/* 49 */     for (byte b = 0; b < 7; b++) {
/* 50 */       if (this.targetList[b] != null) {
/* 51 */         i |= updateSwitchThreads[b];
/*    */       }
/*    */     } 
/* 54 */     return i | 0x2000;
/*    */   }
/*    */   
/*    */   void addNode(Object paramObject, int paramInt) {
/* 58 */     if (this.targetList[paramInt] == null) {
/* 59 */       this.targetList[paramInt] = new UnorderList(1);
/*    */     }
/* 61 */     this.targetList[paramInt].add(paramObject);
/*    */   }
/*    */ 
/*    */   
/*    */   void addNodeArray(Object[] paramArrayOfObject, int paramInt) {
/* 66 */     if (this.targetList[paramInt] == null) {
/* 67 */       this.targetList[paramInt] = new UnorderList(1);
/*    */     }
/* 69 */     this.targetList[paramInt].add(paramArrayOfObject);
/*    */   }
/*    */ 
/*    */   
/*    */   void clearNodes() {
/* 74 */     for (byte b = 0; b < 7; b++) {
/* 75 */       if (this.targetList[b] != null) {
/* 76 */         this.targetList[b].clear();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   void addCachedTargets(CachedTargets paramCachedTargets) {
/* 82 */     for (byte b = 0; b < 7; b++) {
/* 83 */       if (paramCachedTargets.targetArr[b] != null) {
/* 84 */         addNodeArray(paramCachedTargets.targetArr[b], b);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   void dump() {
/* 90 */     for (byte b = 0; b < 7; b++) {
/* 91 */       if (this.targetList[b] != null) {
/* 92 */         System.err.println("  " + CachedTargets.typeString[b]);
/* 93 */         for (byte b1 = 0; b1 < this.targetList[b].size(); b1++)
/* 94 */           System.err.println("  " + this.targetList[b].get(b1)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\UpdateTargets.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */