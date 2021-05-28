/*     */ package com.sun.j3d.utils.compression;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
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
/*     */ class HuffmanTable
/*     */ {
/*  86 */   private HuffmanNode[] colors = new HuffmanNode[544];
/*  87 */   private HuffmanNode[] positions = new HuffmanNode[544];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private HuffmanNode[] normals = new HuffmanNode[112];
/*     */   
/*     */   private static final int MAX_TAG_LENGTH = 6;
/*     */   
/*  99 */   private final int getPositionIndex(int paramInt1, int paramInt2, boolean paramBoolean) { return (paramBoolean ? '\001' : Character.MIN_VALUE) * 'Đ' + paramInt1 * 16 + paramInt2; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   private final int getNormalIndex(int paramInt1, int paramInt2, boolean paramBoolean) { return (paramBoolean ? 1 : 0) * 56 + paramInt1 * 7 + paramInt2; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   private final int getColorIndex(int paramInt1, int paramInt2, boolean paramBoolean) { return getPositionIndex(paramInt1, paramInt2, paramBoolean); }
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
/* 121 */   void addPositionEntry(int paramInt1, int paramInt2, boolean paramBoolean) { addEntry(this.positions, getPositionIndex(paramInt1, paramInt2, paramBoolean), paramInt1, paramInt2, paramBoolean); }
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
/* 139 */   HuffmanNode getPositionEntry(int paramInt1, int paramInt2, boolean paramBoolean) { return getEntry(this.positions, getPositionIndex(paramInt1, paramInt2, paramBoolean)); }
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
/* 152 */   void addColorEntry(int paramInt1, int paramInt2, boolean paramBoolean) { addEntry(this.colors, getColorIndex(paramInt1, paramInt2, paramBoolean), paramInt1, paramInt2, paramBoolean); }
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
/* 170 */   HuffmanNode getColorEntry(int paramInt1, int paramInt2, boolean paramBoolean) { return getEntry(this.colors, getColorIndex(paramInt1, paramInt2, paramBoolean)); }
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
/* 183 */   void addNormalEntry(int paramInt1, int paramInt2, boolean paramBoolean) { addEntry(this.normals, getNormalIndex(paramInt1, paramInt2, paramBoolean), paramInt1, paramInt2, paramBoolean); }
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
/* 201 */   HuffmanNode getNormalEntry(int paramInt1, int paramInt2, boolean paramBoolean) { return getEntry(this.normals, getNormalIndex(paramInt1, paramInt2, paramBoolean)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addEntry(HuffmanNode[] paramArrayOfHuffmanNode, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
/* 208 */     if (paramArrayOfHuffmanNode[paramInt1] == null) {
/* 209 */       paramArrayOfHuffmanNode[paramInt1] = new HuffmanNode(paramInt2, paramInt3, paramBoolean);
/*     */     }
/* 211 */     else if (paramArrayOfHuffmanNode[paramInt1].cleared()) {
/* 212 */       paramArrayOfHuffmanNode[paramInt1].set(paramInt2, paramInt3, paramBoolean);
/*     */     } 
/* 214 */     paramArrayOfHuffmanNode[paramInt1].addCount();
/*     */   }
/*     */   
/*     */   private HuffmanNode getEntry(HuffmanNode[] paramArrayOfHuffmanNode, int paramInt) {
/* 218 */     HuffmanNode huffmanNode = paramArrayOfHuffmanNode[paramInt];
/*     */     
/* 220 */     while (huffmanNode.merged()) {
/* 221 */       huffmanNode = huffmanNode.getMergeNode();
/*     */     }
/* 223 */     return huffmanNode;
/*     */   }
/*     */   
/*     */   private void getEntries(HuffmanNode[] paramArrayOfHuffmanNode, Collection paramCollection) {
/* 227 */     for (byte b = 0; b < paramArrayOfHuffmanNode.length; b++) {
/* 228 */       if (paramArrayOfHuffmanNode[b] != null && !paramArrayOfHuffmanNode[b].cleared() && paramArrayOfHuffmanNode[b].hasCount() && !paramArrayOfHuffmanNode[b].merged())
/*     */       {
/* 230 */         paramCollection.add(paramArrayOfHuffmanNode[b]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void clear() {
/*     */     byte b;
/* 238 */     for (b = 0; b < this.positions.length; b++) {
/* 239 */       if (this.positions[b] != null)
/* 240 */         this.positions[b].clear(); 
/*     */     } 
/* 242 */     for (b = 0; b < this.colors.length; b++) {
/* 243 */       if (this.colors[b] != null)
/* 244 */         this.colors[b].clear(); 
/*     */     } 
/* 246 */     for (b = 0; b < this.normals.length; b++) {
/* 247 */       if (this.normals[b] != null) {
/* 248 */         this.normals[b].clear();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void computeTags() {
/* 255 */     LinkedList linkedList = new LinkedList();
/* 256 */     getEntries(this.positions, linkedList);
/* 257 */     computeTags(linkedList, 3);
/*     */     
/* 259 */     linkedList.clear();
/* 260 */     getEntries(this.colors, linkedList);
/* 261 */     computeTags(linkedList, 3);
/*     */     
/* 263 */     linkedList.clear();
/* 264 */     getEntries(this.normals, linkedList);
/* 265 */     computeTags(linkedList, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeTags(LinkedList paramLinkedList, int paramInt) {
/* 275 */     if (paramLinkedList.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/*     */     while (true) {
/* 280 */       Collections.sort(paramLinkedList, HuffmanNode.frequencyComparator);
/*     */ 
/*     */ 
/*     */       
/* 284 */       HuffmanNode huffmanNode = (HuffmanNode)paramLinkedList.removeFirst();
/* 285 */       while (paramLinkedList.size() > 0) {
/* 286 */         HuffmanNode huffmanNode1 = (HuffmanNode)paramLinkedList.removeFirst();
/* 287 */         HuffmanNode huffmanNode2 = new HuffmanNode();
/*     */         
/* 289 */         huffmanNode2.addChildren(huffmanNode, huffmanNode1);
/* 290 */         addNodeInOrder(paramLinkedList, huffmanNode2, HuffmanNode.frequencyComparator);
/*     */         
/* 292 */         huffmanNode = (HuffmanNode)paramLinkedList.removeFirst();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       huffmanNode.collectLeaves(0, 0, paramLinkedList);
/*     */ 
/*     */       
/* 301 */       Collections.sort(paramLinkedList, HuffmanNode.tagLengthComparator);
/*     */ 
/*     */       
/* 304 */       if (((HuffmanNode)paramLinkedList.getFirst()).tagLength > 6) {
/*     */ 
/*     */         
/* 307 */         merge(paramLinkedList); continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 311 */     expand(paramLinkedList, paramInt);
/*     */   }
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
/*     */   private void merge(LinkedList paramLinkedList) {
/* 324 */     ListIterator listIterator = paramLinkedList.listIterator(0);
/*     */     
/* 326 */     boolean bool = false;
/*     */     
/* 328 */     while (listIterator.hasNext()) {
/*     */       
/* 330 */       HuffmanNode huffmanNode = (HuffmanNode)listIterator.next();
/* 331 */       if (huffmanNode.unmergeable()) {
/*     */         continue;
/*     */       }
/*     */       
/* 335 */       listIterator.remove();
/* 336 */       while (listIterator.hasNext()) {
/* 337 */         HuffmanNode huffmanNode1 = (HuffmanNode)listIterator.next();
/* 338 */         if (huffmanNode.mergeInto(huffmanNode1)) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 343 */           listIterator.remove();
/* 344 */           while (listIterator.hasNext()) {
/* 345 */             HuffmanNode huffmanNode2 = (HuffmanNode)listIterator.next();
/* 346 */             if (huffmanNode1.tokenEquals(huffmanNode2)) {
/* 347 */               huffmanNode1.mergeInto(huffmanNode2);
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/* 352 */           listIterator.add(huffmanNode1);
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 361 */       huffmanNode.setUnmergeable();
/* 362 */       listIterator.add(huffmanNode);
/*     */ 
/*     */       
/* 365 */       listIterator = paramLinkedList.listIterator(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void expand(LinkedList paramLinkedList, int paramInt) {
/* 375 */     Iterator iterator = paramLinkedList.iterator();
/*     */     
/* 377 */     while (iterator.hasNext()) {
/* 378 */       HuffmanNode huffmanNode = (HuffmanNode)iterator.next();
/*     */ 
/*     */       
/* 381 */       while (huffmanNode.tagLength + paramInt * (huffmanNode.dataLength - huffmanNode.shift) < 6)
/*     */       {
/* 383 */         huffmanNode.incrementLength();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addNodeInOrder(LinkedList paramLinkedList, HuffmanNode paramHuffmanNode, Comparator paramComparator) {
/* 392 */     ListIterator listIterator = paramLinkedList.listIterator(0);
/*     */     
/* 394 */     while (listIterator.hasNext()) {
/* 395 */       HuffmanNode huffmanNode = (HuffmanNode)listIterator.next();
/* 396 */       if (paramComparator.compare(huffmanNode, paramHuffmanNode) > 0) {
/* 397 */         huffmanNode = (HuffmanNode)listIterator.previous();
/*     */         break;
/*     */       } 
/*     */     } 
/* 401 */     listIterator.add(paramHuffmanNode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputCommands(CommandStream paramCommandStream) {
/* 411 */     LinkedList linkedList = new LinkedList();
/* 412 */     getEntries(this.positions, linkedList);
/* 413 */     outputCommands(linkedList, paramCommandStream, 0);
/*     */     
/* 415 */     linkedList.clear();
/* 416 */     getEntries(this.colors, linkedList);
/* 417 */     outputCommands(linkedList, paramCommandStream, 1);
/*     */     
/* 419 */     linkedList.clear();
/* 420 */     getEntries(this.normals, linkedList);
/* 421 */     outputCommands(linkedList, paramCommandStream, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void outputCommands(Collection paramCollection, CommandStream paramCommandStream, int paramInt) {
/* 430 */     Iterator iterator = paramCollection.iterator();
/* 431 */     while (iterator.hasNext()) {
/* 432 */       HuffmanNode huffmanNode = (HuffmanNode)iterator.next();
/* 433 */       int i = 1 << huffmanNode.tagLength | huffmanNode.tag;
/* 434 */       boolean bool = (huffmanNode.dataLength == 16) ? 0 : huffmanNode.dataLength;
/*     */       
/* 436 */       int j = 0x10 | paramInt << 1 | i >> 6;
/*     */ 
/*     */       
/* 439 */       long l = ((i & 0x3F) << 9 | bool << 5 | (huffmanNode.absolute ? 16 : 0) | huffmanNode.shift);
/*     */ 
/*     */ 
/*     */       
/* 443 */       paramCommandStream.addCommand(j, 8, l, 15);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void print(String paramString, Collection paramCollection) {
/* 454 */     System.out.println(paramString + "\nentries: " + paramCollection.size() + "\n");
/*     */     
/* 456 */     Iterator iterator = paramCollection.iterator();
/* 457 */     while (iterator.hasNext()) {
/* 458 */       HuffmanNode huffmanNode = (HuffmanNode)iterator.next();
/* 459 */       System.out.println(huffmanNode.toString() + "\n");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void print() {
/* 467 */     LinkedList linkedList = new LinkedList();
/*     */     
/* 469 */     getEntries(this.positions, linkedList);
/* 470 */     Collections.sort(linkedList, HuffmanNode.frequencyComparator);
/* 471 */     print("\nposition tokens and tags", linkedList);
/*     */     
/* 473 */     linkedList.clear();
/* 474 */     getEntries(this.colors, linkedList);
/* 475 */     Collections.sort(linkedList, HuffmanNode.frequencyComparator);
/* 476 */     print("\ncolor tokens and tags", linkedList);
/*     */     
/* 478 */     linkedList.clear();
/* 479 */     getEntries(this.normals, linkedList);
/* 480 */     Collections.sort(linkedList, HuffmanNode.frequencyComparator);
/* 481 */     print("\nnormal tokens and tags", linkedList);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\compression\HuffmanTable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */