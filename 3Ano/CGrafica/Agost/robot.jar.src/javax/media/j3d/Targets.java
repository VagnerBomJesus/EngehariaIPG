/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ class Targets
/*     */ {
/*     */   static final int MAX_NODELIST = 7;
/*     */   static final int GEO_TARGETS = 0;
/*     */   static final int ENV_TARGETS = 1;
/*     */   static final int BEH_TARGETS = 2;
/*     */   static final int SND_TARGETS = 3;
/*     */   static final int VPF_TARGETS = 4;
/*     */   static final int BLN_TARGETS = 5;
/*     */   static final int GRP_TARGETS = 6;
/*  29 */   ArrayList[] targetList = new ArrayList[7];
/*     */   
/*     */   void addNode(NnuId paramNnuId, int paramInt) {
/*  32 */     if (this.targetList[paramInt] == null) {
/*  33 */       this.targetList[paramInt] = new ArrayList(1);
/*     */     }
/*  35 */     this.targetList[paramInt].add(paramNnuId);
/*     */   }
/*     */   
/*     */   void addNodeArray(NnuId[] paramArrayOfNnuId, int paramInt) {
/*  39 */     if (this.targetList[paramInt] == null) {
/*  40 */       this.targetList[paramInt] = new ArrayList(1);
/*     */     }
/*  42 */     this.targetList[paramInt].add(paramArrayOfNnuId);
/*     */   }
/*     */ 
/*     */   
/*     */   void removeNode(int paramInt1, int paramInt2) {
/*  47 */     if (this.targetList[paramInt2] != null) {
/*  48 */       this.targetList[paramInt2].remove(paramInt1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void addNodes(ArrayList paramArrayList, int paramInt) {
/*  54 */     if (this.targetList[paramInt] == null) {
/*  55 */       this.targetList[paramInt] = new ArrayList(1);
/*     */     }
/*  57 */     this.targetList[paramInt].addAll(paramArrayList);
/*     */   }
/*     */ 
/*     */   
/*     */   void clearNodes() {
/*  62 */     for (byte b = 0; b < 7; b++) {
/*  63 */       if (this.targetList[b] != null) {
/*  64 */         this.targetList[b].clear();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   CachedTargets snapShotInit() {
/*  71 */     CachedTargets cachedTargets = new CachedTargets();
/*     */ 
/*     */     
/*  74 */     for (byte b = 0; b < 7; b++) {
/*  75 */       if (this.targetList[b] != null) {
/*  76 */         int i = this.targetList[b].size();
/*  77 */         NnuId[] arrayOfNnuId = new NnuId[i];
/*  78 */         this.targetList[b].toArray(arrayOfNnuId);
/*  79 */         cachedTargets.targetArr[b] = arrayOfNnuId;
/*     */ 
/*     */         
/*  82 */         NnuIdManager.sort((NnuId[])cachedTargets.targetArr[b]);
/*     */       }
/*     */       else {
/*     */         
/*  86 */         cachedTargets.targetArr[b] = null;
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     clearNodes();
/*     */     
/*  92 */     return cachedTargets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CachedTargets snapShotAdd(CachedTargets paramCachedTargets) {
/* 100 */     CachedTargets cachedTargets = new CachedTargets();
/*     */     
/* 102 */     for (byte b = 0; b < 7; b++) {
/* 103 */       if (this.targetList[b] != null && paramCachedTargets.targetArr[b] == null) {
/* 104 */         int i = this.targetList[b].size();
/* 105 */         NnuId[] arrayOfNnuId = new NnuId[i];
/* 106 */         this.targetList[b].toArray(arrayOfNnuId);
/* 107 */         cachedTargets.targetArr[b] = arrayOfNnuId;
/* 108 */         NnuIdManager.sort(cachedTargets.targetArr[b]);
/*     */       
/*     */       }
/* 111 */       else if (this.targetList[b] != null && paramCachedTargets.targetArr[b] != null) {
/*     */         
/* 113 */         int i = this.targetList[b].size();
/* 114 */         NnuId[] arrayOfNnuId = new NnuId[i];
/* 115 */         this.targetList[b].toArray(arrayOfNnuId);
/* 116 */         NnuIdManager.sort(arrayOfNnuId);
/* 117 */         cachedTargets.targetArr[b] = NnuIdManager.merge(paramCachedTargets.targetArr[b], arrayOfNnuId);
/*     */ 
/*     */       
/*     */       }
/* 121 */       else if (this.targetList[b] == null && paramCachedTargets.targetArr[b] != null) {
/* 122 */         cachedTargets.targetArr[b] = paramCachedTargets.targetArr[b];
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     clearNodes();
/*     */     
/* 128 */     return cachedTargets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CachedTargets snapShotRemove(CachedTargets paramCachedTargets) {
/* 139 */     CachedTargets cachedTargets = new CachedTargets();
/*     */     
/* 141 */     for (byte b = 0; b < 7; b++) {
/*     */       
/* 143 */       if (this.targetList[b] != null && paramCachedTargets.targetArr[b] != null) {
/* 144 */         int i = this.targetList[b].size();
/* 145 */         NnuId[] arrayOfNnuId = new NnuId[i];
/* 146 */         this.targetList[b].toArray(arrayOfNnuId);
/* 147 */         NnuIdManager.sort(arrayOfNnuId);
/* 148 */         cachedTargets.targetArr[b] = NnuIdManager.delete(paramCachedTargets.targetArr[b], arrayOfNnuId);
/*     */ 
/*     */       
/*     */       }
/* 152 */       else if (this.targetList[b] == null && paramCachedTargets.targetArr[b] != null) {
/* 153 */         cachedTargets.targetArr[b] = paramCachedTargets.targetArr[b];
/*     */       
/*     */       }
/* 156 */       else if (this.targetList[b] != null && paramCachedTargets.targetArr[b] == null) {
/* 157 */         System.err.println("You can't remove something that isn't there");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 162 */     clearNodes();
/*     */     
/* 164 */     return cachedTargets;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 169 */     boolean bool = true;
/*     */     
/* 171 */     for (byte b = 0; b < 7; b++) {
/* 172 */       if (this.targetList[b] != null) {
/* 173 */         bool = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 177 */     return bool;
/*     */   }
/*     */   
/*     */   void addCachedTargets(CachedTargets paramCachedTargets) {
/* 181 */     for (byte b = 0; b < 7; b++) {
/* 182 */       if (paramCachedTargets.targetArr[b] != null) {
/* 183 */         addNodeArray(paramCachedTargets.targetArr[b], b);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   void dump() {
/* 189 */     for (byte b = 0; b < 7; b++) {
/* 190 */       if (this.targetList[b] != null) {
/* 191 */         System.err.println("  " + CachedTargets.typeString[b]);
/* 192 */         for (byte b1 = 0; b1 < this.targetList[b].size(); b1++)
/* 193 */           System.err.println("  " + this.targetList[b].get(b1)); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Targets.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */