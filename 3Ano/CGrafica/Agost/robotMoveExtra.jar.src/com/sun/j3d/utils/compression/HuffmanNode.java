/*     */ package com.sun.j3d.utils.compression;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
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
/*     */ class HuffmanNode
/*     */ {
/*     */   int tag;
/*     */   int tagLength;
/*     */   int shift;
/*     */   int dataLength;
/*     */   boolean absolute;
/*     */   private int frequency;
/*     */   private HuffmanNode child0;
/*     */   private HuffmanNode child1;
/*     */   private HuffmanNode mergeNode;
/*     */   private boolean merged;
/*     */   private boolean unmergeable;
/*     */   private boolean cleared;
/*     */   
/*     */   void clear() {
/*  86 */     this.tag = -1;
/*  87 */     this.tagLength = -1;
/*     */     
/*  89 */     this.shift = -1;
/*  90 */     this.dataLength = -1;
/*  91 */     this.absolute = false;
/*     */     
/*  93 */     this.child0 = null;
/*  94 */     this.child1 = null;
/*  95 */     this.mergeNode = null;
/*     */     
/*  97 */     this.frequency = 0;
/*  98 */     this.merged = false;
/*  99 */     this.unmergeable = false;
/* 100 */     this.cleared = true;
/*     */   }
/*     */ 
/*     */   
/* 104 */   HuffmanNode() { clear(); }
/*     */ 
/*     */   
/*     */   HuffmanNode(int paramInt1, int paramInt2, boolean paramBoolean) {
/* 108 */     this();
/* 109 */     set(paramInt1, paramInt2, paramBoolean);
/*     */   }
/*     */   
/*     */   final void set(int paramInt1, int paramInt2, boolean paramBoolean) {
/* 113 */     this.dataLength = paramInt1;
/* 114 */     this.shift = paramInt2;
/* 115 */     this.absolute = paramBoolean;
/* 116 */     this.cleared = false;
/*     */   }
/*     */ 
/*     */   
/* 120 */   final boolean cleared() { return this.cleared; }
/*     */ 
/*     */ 
/*     */   
/* 124 */   final void addCount() { this.frequency++; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   final boolean hasCount() { return (this.frequency > 0); }
/*     */ 
/*     */ 
/*     */   
/* 132 */   final boolean tokenEquals(HuffmanNode paramHuffmanNode) { return (this.absolute == paramHuffmanNode.absolute && this.dataLength == paramHuffmanNode.dataLength && this.shift == paramHuffmanNode.shift); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addChildren(HuffmanNode paramHuffmanNode1, HuffmanNode paramHuffmanNode2) {
/* 139 */     this.child0 = paramHuffmanNode1;
/* 140 */     this.child1 = paramHuffmanNode2;
/* 141 */     paramHuffmanNode1.frequency += paramHuffmanNode2.frequency;
/*     */   }
/*     */   
/*     */   void collectLeaves(int paramInt1, int paramInt2, Collection paramCollection) {
/* 145 */     if (this.child0 == null) {
/* 146 */       this.tag = paramInt1;
/* 147 */       this.tagLength = paramInt2;
/* 148 */       paramCollection.add(this);
/*     */     } else {
/* 150 */       this.child0.collectLeaves(paramInt1 << 1 | false, paramInt2 + 1, paramCollection);
/* 151 */       this.child1.collectLeaves(paramInt1 << 1 | true, paramInt2 + 1, paramCollection);
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean mergeInto(HuffmanNode paramHuffmanNode) {
/* 156 */     if (this.absolute == paramHuffmanNode.absolute) {
/* 157 */       if (this.dataLength > paramHuffmanNode.dataLength) {
/* 158 */         paramHuffmanNode.dataLength = this.dataLength;
/*     */       }
/* 160 */       if (this.shift < paramHuffmanNode.shift) {
/* 161 */         paramHuffmanNode.shift = this.shift;
/*     */       }
/* 163 */       paramHuffmanNode.frequency += this.frequency;
/* 164 */       this.mergeNode = paramHuffmanNode;
/* 165 */       this.merged = true;
/* 166 */       return true;
/*     */     } 
/*     */     
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   int incrementLength() {
/* 173 */     if (this.shift > 0) {
/* 174 */       this.shift--;
/*     */     } else {
/* 176 */       this.dataLength++;
/*     */     } 
/* 178 */     return this.dataLength - this.shift;
/*     */   }
/*     */ 
/*     */   
/* 182 */   final boolean merged() { return this.merged; }
/*     */ 
/*     */ 
/*     */   
/* 186 */   final HuffmanNode getMergeNode() { return this.mergeNode; }
/*     */ 
/*     */ 
/*     */   
/* 190 */   void setUnmergeable() { this.unmergeable = true; }
/*     */ 
/*     */ 
/*     */   
/* 194 */   final boolean unmergeable() { return this.unmergeable; }
/*     */ 
/*     */ 
/*     */   
/* 198 */   public String toString() { return "shift " + this.shift + " data length " + this.dataLength + (this.absolute ? " absolute " : " relative ") + "\ntag 0x" + Integer.toHexString(this.tag) + " tag length " + this.tagLength + "\nfrequency: " + this.frequency; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class FrequencyComparator
/*     */     implements Comparator
/*     */   {
/* 210 */     public final int compare(Object param1Object1, Object param1Object2) { return ((HuffmanNode)param1Object1).frequency - ((HuffmanNode)param1Object2).frequency; }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class TagLengthComparator
/*     */     implements Comparator
/*     */   {
/* 219 */     public final int compare(Object param1Object1, Object param1Object2) { return ((HuffmanNode)param1Object2).tagLength - ((HuffmanNode)param1Object1).tagLength; }
/*     */   }
/*     */ 
/*     */   
/* 223 */   static FrequencyComparator frequencyComparator = new FrequencyComparator();
/* 224 */   static TagLengthComparator tagLengthComparator = new TagLengthComparator();
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\compression\HuffmanNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */