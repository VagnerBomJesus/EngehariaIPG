/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.NoSuchElementException;
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
/*     */ class WakeupCriteriaEnumerator
/*     */   implements Enumeration
/*     */ {
/*     */   WakeupCriterion[] criterion;
/*     */   int currentIndex;
/*     */   int length;
/*     */   
/*     */   WakeupCriteriaEnumerator(WakeupCondition paramWakeupCondition, int paramInt) {
/*  25 */     this.criterion = null;
/*     */ 
/*     */     
/*  28 */     this.currentIndex = 0;
/*     */ 
/*     */     
/*  31 */     this.length = 0;
/*     */ 
/*     */     
/*  34 */     reset(paramWakeupCondition, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void reset(WakeupCondition paramWakeupCondition, int paramInt) {
/*  40 */     this.currentIndex = 0;
/*  41 */     this.length = 0;
/*  42 */     if (paramWakeupCondition instanceof WakeupCriterion) {
/*  43 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramWakeupCondition;
/*     */       
/*  45 */       if (this.criterion == null || this.criterion.length < 1) {
/*  46 */         this.criterion = new WakeupCriterion[1];
/*     */       }
/*  48 */       if (wakeupCriterion.triggered || paramInt == 0) {
/*  49 */         this.criterion[0] = wakeupCriterion;
/*  50 */         this.length = 1;
/*     */       }
/*     */     
/*  53 */     } else if (paramWakeupCondition instanceof WakeupAnd) {
/*  54 */       WakeupAnd wakeupAnd = (WakeupAnd)paramWakeupCondition;
/*     */       
/*  56 */       if (this.criterion == null || this.criterion.length < wakeupAnd.conditions.length) {
/*  57 */         this.criterion = new WakeupCriterion[wakeupAnd.conditions.length];
/*     */       }
/*  59 */       for (byte b = 0; b < wakeupAnd.conditions.length; b++) {
/*  60 */         if ((wakeupAnd.conditions[b]).triggered || paramInt == 0) {
/*  61 */           this.criterion[this.length++] = wakeupAnd.conditions[b];
/*     */         }
/*     */       } 
/*  64 */     } else if (paramWakeupCondition instanceof WakeupOr) {
/*  65 */       WakeupOr wakeupOr = (WakeupOr)paramWakeupCondition;
/*     */       
/*  67 */       if (this.criterion == null || this.criterion.length < wakeupOr.conditions.length) {
/*  68 */         this.criterion = new WakeupCriterion[wakeupOr.conditions.length];
/*     */       }
/*  70 */       for (byte b = 0; b < wakeupOr.conditions.length; b++) {
/*  71 */         if ((wakeupOr.conditions[b]).triggered || paramInt == 0) {
/*  72 */           this.criterion[this.length++] = wakeupOr.conditions[b];
/*     */         }
/*     */       } 
/*  75 */     } else if (paramWakeupCondition instanceof WakeupOrOfAnds) {
/*  76 */       WakeupOrOfAnds wakeupOrOfAnds = (WakeupOrOfAnds)paramWakeupCondition;
/*  77 */       int i = 0;
/*     */       byte b;
/*  79 */       for (b = 0; b < wakeupOrOfAnds.conditions.length; b++) {
/*  80 */         i += (wakeupOrOfAnds.conditions[b]).conditions.length;
/*     */       }
/*     */       
/*  83 */       if (this.criterion == null || this.criterion.length < i) {
/*  84 */         this.criterion = new WakeupCriterion[i];
/*     */       }
/*     */       
/*  87 */       for (b = 0; b < wakeupOrOfAnds.conditions.length; b++) {
/*  88 */         for (byte b1 = 0; b1 < (wakeupOrOfAnds.conditions[b]).conditions.length; b1++) {
/*  89 */           if (((wakeupOrOfAnds.conditions[b]).conditions[b1]).triggered || paramInt == 0)
/*     */           {
/*  91 */             this.criterion[this.length++] = (wakeupOrOfAnds.conditions[b]).conditions[b1];
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*  96 */       WakeupAndOfOrs wakeupAndOfOrs = (WakeupAndOfOrs)paramWakeupCondition;
/*  97 */       int i = 0;
/*     */       byte b;
/*  99 */       for (b = 0; b < wakeupAndOfOrs.conditions.length; b++) {
/* 100 */         i += (wakeupAndOfOrs.conditions[b]).conditions.length;
/*     */       }
/*     */       
/* 103 */       if (this.criterion == null || this.criterion.length < i) {
/* 104 */         this.criterion = new WakeupCriterion[i];
/*     */       }
/*     */       
/* 107 */       for (b = 0; b < wakeupAndOfOrs.conditions.length; b++) {
/* 108 */         for (byte b1 = 0; b1 < (wakeupAndOfOrs.conditions[b]).conditions.length; b1++) {
/* 109 */           if (((wakeupAndOfOrs.conditions[b]).conditions[b1]).triggered || paramInt == 0)
/*     */           {
/* 111 */             this.criterion[this.length++] = (wakeupAndOfOrs.conditions[b]).conditions[b1];
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMoreElements() {
/* 120 */     if (this.currentIndex == this.length) {
/* 121 */       return false;
/*     */     }
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public Object nextElement() {
/* 127 */     if (this.currentIndex < this.length) {
/* 128 */       return this.criterion[this.currentIndex++];
/*     */     }
/* 130 */     throw new NoSuchElementException(J3dI18N.getString("WakeupCriteriaEnumerator0"));
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\WakeupCriteriaEnumerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */