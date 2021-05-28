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
/*     */ 
/*     */ 
/*     */ class OrderedBin
/*     */ {
/*  23 */   ArrayList orderedCollections = new ArrayList();
/*     */   
/*     */   OrderedGroupRetained source;
/*     */   
/*  27 */   OrderedChildInfo childInfoList = null;
/*  28 */   OrderedChildInfo lastChildInfo = null;
/*     */ 
/*     */   
/*     */   boolean onUpdateList = false;
/*     */   
/*  33 */   ArrayList setOCForCI = new ArrayList();
/*  34 */   ArrayList valueOfSetOCForCI = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  38 */   ArrayList setOCForOI = new ArrayList();
/*  39 */   ArrayList valueOfSetOCForOI = new ArrayList();
/*     */ 
/*     */   
/*     */   OrderedBin(int paramInt, OrderedGroupRetained paramOrderedGroupRetained) {
/*  43 */     for (byte b = 0; b < paramInt; b++) {
/*  44 */       this.orderedCollections.add(null);
/*     */     }
/*  46 */     this.source = paramOrderedGroupRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addRemoveOrderedCollection() {
/*     */     byte b;
/*  54 */     for (b = 0; b < this.setOCForCI.size(); b++) {
/*  55 */       int i = ((Integer)this.setOCForCI.get(b)).intValue();
/*  56 */       OrderedCollection orderedCollection = (OrderedCollection)this.valueOfSetOCForCI.get(b);
/*  57 */       this.orderedCollections.set(i, orderedCollection);
/*     */     } 
/*     */     
/*  60 */     this.setOCForCI.clear();
/*  61 */     this.valueOfSetOCForCI.clear();
/*     */     
/*  63 */     while (this.childInfoList != null) {
/*  64 */       if (this.childInfoList.type == OrderedChildInfo.ADD) {
/*  65 */         this.orderedCollections.add(this.childInfoList.childId, this.childInfoList.value);
/*     */       }
/*  67 */       else if (this.childInfoList.type == OrderedChildInfo.REMOVE) {
/*  68 */         this.orderedCollections.remove(this.childInfoList.childId);
/*     */       } 
/*  70 */       this.childInfoList = this.childInfoList.next;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     for (b = 0; b < this.setOCForOI.size(); b++) {
/*  77 */       int i = ((Integer)this.setOCForOI.get(b)).intValue();
/*  78 */       OrderedCollection orderedCollection = (OrderedCollection)this.valueOfSetOCForOI.get(b);
/*  79 */       int j = this.source.orderedChildIdTable[i];
/*  80 */       this.orderedCollections.set(j, orderedCollection);
/*     */     } 
/*  82 */     this.setOCForOI.clear();
/*  83 */     this.valueOfSetOCForOI.clear();
/*     */     
/*  85 */     this.onUpdateList = false;
/*  86 */     this.lastChildInfo = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void addChildInfo(OrderedChildInfo paramOrderedChildInfo) {
/*  92 */     if (this.childInfoList == null) {
/*  93 */       this.childInfoList = paramOrderedChildInfo;
/*  94 */       this.lastChildInfo = paramOrderedChildInfo;
/*     */     }
/*     */     else {
/*     */       
/*  98 */       paramOrderedChildInfo.prev = this.lastChildInfo;
/*  99 */       this.lastChildInfo.next = paramOrderedChildInfo;
/* 100 */       paramOrderedChildInfo.next = null;
/*     */       
/* 102 */       this.lastChildInfo = paramOrderedChildInfo;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\OrderedBin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */