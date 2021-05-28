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
/*     */ 
/*     */ 
/*     */ class OrderedGroupRetained
/*     */   extends GroupRetained
/*     */ {
/*     */   int[] orderedChildIdTable;
/*  27 */   private int orderedChildIdCount = 0;
/*     */ 
/*     */   
/*  30 */   private ArrayList orderedChildIdFreeList = new ArrayList();
/*     */ 
/*     */   
/*  33 */   OrderedBin[] orderedBin = new OrderedBin[0];
/*     */ 
/*     */ 
/*     */   
/*     */   Integer newChildId;
/*     */ 
/*     */   
/*  40 */   int childCount = 0;
/*     */ 
/*     */   
/*  43 */   ArrayList childrenOrderedPaths = new ArrayList(1);
/*     */ 
/*     */ 
/*     */   
/*  47 */   int[] userChildIndexOrder = null;
/*     */ 
/*     */   
/*  50 */   int[] childIndexOrder = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setChildIndexOrder(int[] paramArrayOfInt) {
/*  59 */     if (paramArrayOfInt != null) {
/*  60 */       if (this.userChildIndexOrder == null || this.userChildIndexOrder.length != paramArrayOfInt.length)
/*     */       {
/*  62 */         this.userChildIndexOrder = new int[paramArrayOfInt.length];
/*     */       }
/*     */       
/*  65 */       System.arraycopy(paramArrayOfInt, 0, this.userChildIndexOrder, 0, this.userChildIndexOrder.length);
/*     */     }
/*     */     else {
/*     */       
/*  69 */       this.userChildIndexOrder = null;
/*     */     } 
/*     */     
/*  72 */     if (this.source.isLive()) {
/*  73 */       int[] arrayOfInt = new int[paramArrayOfInt.length];
/*  74 */       System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, arrayOfInt.length);
/*     */ 
/*     */       
/*  77 */       J3dMessage j3dMessage = new J3dMessage();
/*  78 */       j3dMessage.threads = 128;
/*  79 */       j3dMessage.type = 59;
/*  80 */       j3dMessage.universe = this.universe;
/*  81 */       j3dMessage.args[3] = this;
/*  82 */       j3dMessage.args[4] = arrayOfInt;
/*  83 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */   
/*     */   int[] getChildIndexOrder() {
/*  88 */     if (this.userChildIndexOrder == null) {
/*  89 */       return null;
/*     */     }
/*     */     
/*  92 */     int[] arrayOfInt = new int[this.userChildIndexOrder.length];
/*  93 */     System.arraycopy(this.userChildIndexOrder, 0, arrayOfInt, 0, this.userChildIndexOrder.length);
/*     */     
/*  95 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   
/*     */   Integer getOrderedChildId() {
/*     */     Integer integer;
/* 101 */     synchronized (this.orderedChildIdFreeList) {
/* 102 */       if (this.orderedChildIdFreeList.size() == 0) {
/* 103 */         integer = new Integer(this.orderedChildIdCount);
/* 104 */         this.orderedChildIdCount++;
/*     */       } else {
/* 106 */         integer = (Integer)this.orderedChildIdFreeList.remove(0);
/*     */       } 
/*     */     } 
/* 109 */     return integer;
/*     */   }
/*     */   
/*     */   void freeOrderedChildId(int paramInt) {
/* 113 */     synchronized (this.orderedChildIdFreeList) {
/* 114 */       this.orderedChildIdFreeList.add(new Integer(paramInt));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   int getOrderedChildCount() {
/*     */     int i;
/* 121 */     synchronized (this.orderedChildIdFreeList) {
/* 122 */       i = this.orderedChildIdCount;
/*     */     } 
/* 124 */     return i;
/*     */   }
/*     */   
/*     */   void addChild(Node paramNode) {
/* 128 */     if (this.userChildIndexOrder != null) {
/* 129 */       doAddChildIndexEntry();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 134 */     super.addChild(paramNode);
/*     */   }
/*     */ 
/*     */   
/*     */   void addChild(Node paramNode, int[] paramArrayOfInt) {
/* 139 */     if (paramArrayOfInt != null) {
/* 140 */       this.userChildIndexOrder = new int[paramArrayOfInt.length];
/*     */       
/* 142 */       System.arraycopy(paramArrayOfInt, 0, this.userChildIndexOrder, 0, this.userChildIndexOrder.length);
/*     */     }
/*     */     else {
/*     */       
/* 146 */       this.userChildIndexOrder = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 151 */     super.addChild(paramNode);
/*     */   }
/*     */ 
/*     */   
/*     */   void moveTo(BranchGroup paramBranchGroup) {
/* 156 */     if (this.userChildIndexOrder != null) {
/* 157 */       doAddChildIndexEntry();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 162 */     super.moveTo(paramBranchGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void doRemoveChildIndexEntry(int paramInt) {
/* 168 */     int[] arrayOfInt = new int[this.userChildIndexOrder.length - 1];
/*     */     
/* 170 */     for (byte b1 = 0, b2 = 0; b1 < this.userChildIndexOrder.length; b1++) {
/* 171 */       if (this.userChildIndexOrder[b1] > paramInt) {
/* 172 */         arrayOfInt[b2] = this.userChildIndexOrder[b1] - 1;
/* 173 */         b2++;
/*     */       }
/* 175 */       else if (this.userChildIndexOrder[b1] < paramInt) {
/* 176 */         arrayOfInt[b2] = this.userChildIndexOrder[b1];
/* 177 */         b2++;
/*     */       } 
/*     */     } 
/*     */     
/* 181 */     this.userChildIndexOrder = arrayOfInt;
/*     */   }
/*     */ 
/*     */   
/*     */   void doAddChildIndexEntry() {
/* 186 */     int[] arrayOfInt = new int[this.userChildIndexOrder.length + 1];
/*     */     
/* 188 */     System.arraycopy(this.userChildIndexOrder, 0, arrayOfInt, 0, this.userChildIndexOrder.length);
/*     */ 
/*     */     
/* 191 */     arrayOfInt[this.userChildIndexOrder.length] = this.userChildIndexOrder.length;
/*     */     
/* 193 */     this.userChildIndexOrder = arrayOfInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void compile(CompileState paramCompileState) {
/* 202 */     super.compile(paramCompileState);
/*     */ 
/*     */     
/* 205 */     this.mergeFlag = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setOrderedBin(OrderedBin paramOrderedBin, int paramInt) {
/* 213 */     synchronized (this.orderedBin) {
/* 214 */       this.orderedBin[paramInt] = paramOrderedBin;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   OrderedBin getOrderedBin(int paramInt) {
/* 222 */     synchronized (this.orderedBin) {
/* 223 */       if (paramInt >= this.orderedBin.length) {
/* 224 */         OrderedBin[] arrayOfOrderedBin = new OrderedBin[paramInt + 1];
/* 225 */         for (byte b = 0; b < this.orderedBin.length; b++) {
/* 226 */           arrayOfOrderedBin[b] = this.orderedBin[b];
/*     */         }
/* 228 */         arrayOfOrderedBin[paramInt] = null;
/* 229 */         this.orderedBin = arrayOfOrderedBin;
/*     */       } 
/*     */     } 
/* 232 */     return this.orderedBin[paramInt];
/*     */   }
/*     */   
/*     */   void updateChildIdTableInserted(int paramInt1, int paramInt2) {
/* 236 */     int i = 0;
/*     */ 
/*     */ 
/*     */     
/* 240 */     if (this.orderedChildIdTable != null) {
/* 241 */       i = this.orderedChildIdTable.length;
/* 242 */       for (byte b = 0; b < i; b++) {
/* 243 */         if (this.orderedChildIdTable[b] != -1 && 
/* 244 */           this.orderedChildIdTable[b] >= paramInt1) {
/* 245 */           this.orderedChildIdTable[b] = this.orderedChildIdTable[b] + 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 250 */     if (paramInt2 >= i) {
/*     */       
/* 252 */       int[] arrayOfInt = new int[paramInt2 + 1];
/* 253 */       if (i > 0) {
/* 254 */         System.arraycopy(this.orderedChildIdTable, 0, arrayOfInt, 0, this.orderedChildIdTable.length);
/*     */       }
/*     */       else {
/*     */         
/* 258 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/* 259 */           arrayOfInt[b] = -1;
/*     */         }
/*     */       } 
/* 262 */       this.orderedChildIdTable = arrayOfInt;
/*     */     } 
/* 264 */     this.orderedChildIdTable[paramInt2] = paramInt1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateChildIdTableRemoved(int paramInt) {
/* 274 */     if (this.orderedChildIdTable == null) {
/*     */       return;
/*     */     }
/* 277 */     for (byte b = 0; b < this.orderedChildIdTable.length; b++) {
/* 278 */       if (this.orderedChildIdTable[b] != -1) {
/* 279 */         if (this.orderedChildIdTable[b] > paramInt) {
/* 280 */           this.orderedChildIdTable[b] = this.orderedChildIdTable[b] - 1;
/*     */         }
/* 282 */         else if (this.orderedChildIdTable[b] == paramInt) {
/* 283 */           this.orderedChildIdTable[b] = -1;
/*     */           
/* 285 */           freeOrderedChildId(b);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAuxData(SetLiveState paramSetLiveState, int paramInt1, int paramInt2) {
/* 297 */     OrderedPath orderedPath = (OrderedPath)paramSetLiveState.orderedPaths.get(paramInt2);
/* 298 */     for (byte b = 0; b < this.children.size(); b++) {
/* 299 */       NodeRetained nodeRetained = (NodeRetained)this.children.get(b);
/* 300 */       if (this.refCount == paramSetLiveState.refCount)
/*     */       {
/*     */         
/* 303 */         nodeRetained.orderedId = getOrderedChildId();
/*     */       }
/*     */       
/* 306 */       OrderedPath orderedPath1 = orderedPath.clonePath();
/* 307 */       orderedPath1.addElementToPath(this, nodeRetained.orderedId);
/* 308 */       ArrayList arrayList = (ArrayList)this.childrenOrderedPaths.get(b);
/* 309 */       arrayList.add(paramInt2, orderedPath1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 315 */     super.setLive(paramSetLiveState);
/* 316 */     paramSetLiveState.orderedPaths = this.orderedPaths;
/* 317 */     if (this.userChildIndexOrder != null && this.refCount == 1) {
/*     */ 
/*     */       
/* 320 */       int[] arrayOfInt = new int[this.userChildIndexOrder.length];
/* 321 */       System.arraycopy(this.userChildIndexOrder, 0, arrayOfInt, 0, this.userChildIndexOrder.length);
/*     */       
/* 323 */       this.childIndexOrder = arrayOfInt;
/*     */     } 
/*     */   }
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 328 */     super.clearLive(paramSetLiveState);
/*     */ 
/*     */ 
/*     */     
/* 332 */     if (this.refCount == 0) {
/* 333 */       paramSetLiveState.notifyThreads |= 0x1000;
/*     */       
/* 335 */       paramSetLiveState.nodeList.add(this);
/* 336 */       paramSetLiveState.ogCIOList.add(this);
/* 337 */       paramSetLiveState.ogCIOTableList.add(null);
/* 338 */       this.userChildIndexOrder = null;
/*     */     } 
/* 340 */     paramSetLiveState.orderedPaths = this.orderedPaths;
/*     */   }
/*     */   
/*     */   void setNodeData(SetLiveState paramSetLiveState) {
/* 344 */     super.setNodeData(paramSetLiveState);
/* 345 */     if (!this.inSharedGroup) {
/* 346 */       setAuxData(paramSetLiveState, 0, 0);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 351 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 352 */         int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */ 
/*     */         
/* 355 */         if (i >= 0) {
/* 356 */           setAuxData(paramSetLiveState, b, i);
/*     */         } else {
/*     */           
/* 359 */           MasterControl.getCoreLogger().severe("Can't Find matching hashKey in setNodeData.");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 369 */     if (this.inSharedGroup && paramSetLiveState.keys.length != this.localToVworld.length)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 374 */       for (int i = paramSetLiveState.keys.length - 1; i >= 0; i--) {
/* 375 */         int j = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */         
/* 377 */         if (j >= 0) {
/* 378 */           for (byte b = 0; b < this.children.size(); b++) {
/* 379 */             ArrayList arrayList = (ArrayList)this.childrenOrderedPaths.get(b);
/* 380 */             arrayList.remove(j);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 387 */     super.removeNodeData(paramSetLiveState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearDerivedDataStructures() {
/*     */     byte b;
/* 398 */     for (b = 0; b < this.orderedBin.length; b++) {
/* 399 */       if (this.orderedBin[b] != null) {
/* 400 */         (this.orderedBin[b]).source = null;
/* 401 */         this.orderedBin[b] = null;
/*     */       } 
/*     */     } 
/* 404 */     if (this.orderedChildIdTable != null) {
/* 405 */       for (b = 0; b < this.orderedChildIdTable.length; b++) {
/* 406 */         if (this.orderedChildIdTable[b] != -1) {
/* 407 */           this.orderedChildIdTable[b] = -1;
/*     */           
/* 409 */           freeOrderedChildId(b);
/*     */         } 
/*     */       } 
/* 412 */       this.orderedChildIdTable = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 417 */   void incrChildCount() { this.childCount++; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 422 */   void decrChildCount() { this.childCount--; }
/*     */ 
/*     */   
/*     */   void printTable(int[] paramArrayOfInt) {
/* 426 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 427 */       System.err.print(" " + paramArrayOfInt[b]);
/*     */     }
/* 429 */     System.err.println("");
/*     */   }
/*     */ 
/*     */   
/* 433 */   void insertChildrenData(int paramInt) { this.childrenOrderedPaths.add(paramInt, new ArrayList(1)); }
/*     */ 
/*     */ 
/*     */   
/* 437 */   void appendChildrenData() { this.childrenOrderedPaths.add(new ArrayList(1)); }
/*     */ 
/*     */ 
/*     */   
/*     */   void doRemoveChild(int paramInt1, J3dMessage[] paramArrayOfJ3dMessage, int paramInt2) {
/* 442 */     if (this.userChildIndexOrder != null) {
/* 443 */       doRemoveChildIndexEntry(paramInt1);
/*     */     }
/*     */     
/* 446 */     super.doRemoveChild(paramInt1, paramArrayOfJ3dMessage, paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 451 */   void removeChildrenData(int paramInt) { this.childrenOrderedPaths.remove(paramInt); }
/*     */ 
/*     */   
/*     */   void childDoSetLive(NodeRetained paramNodeRetained, int paramInt, SetLiveState paramSetLiveState) {
/* 455 */     if (this.refCount == paramSetLiveState.refCount) {
/* 456 */       paramSetLiveState.ogList.add(this);
/* 457 */       paramSetLiveState.ogChildIdList.add(new Integer(paramInt));
/* 458 */       paramSetLiveState.ogOrderedIdList.add(paramNodeRetained.orderedId);
/*     */     } 
/* 460 */     paramSetLiveState.orderedPaths = (ArrayList)this.childrenOrderedPaths.get(paramInt);
/* 461 */     if (paramNodeRetained != null) {
/* 462 */       paramNodeRetained.setLive(paramSetLiveState);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void childCheckSetLive(NodeRetained paramNodeRetained1, int paramInt, SetLiveState paramSetLiveState, NodeRetained paramNodeRetained2) {
/*     */     ArrayList arrayList;
/* 470 */     if (paramNodeRetained2 != null) {
/* 471 */       int i = this.children.indexOf(paramNodeRetained2);
/* 472 */       arrayList = (ArrayList)this.childrenOrderedPaths.get(i);
/*     */     } else {
/* 474 */       paramNodeRetained1.orderedId = getOrderedChildId();
/*     */       
/* 476 */       paramSetLiveState.ogList.add(this);
/* 477 */       paramSetLiveState.ogChildIdList.add(new Integer(paramInt));
/* 478 */       paramSetLiveState.ogOrderedIdList.add(paramNodeRetained1.orderedId);
/*     */       
/* 480 */       if (this.userChildIndexOrder != null) {
/* 481 */         paramSetLiveState.ogCIOList.add(this);
/* 482 */         int[] arrayOfInt = new int[this.userChildIndexOrder.length];
/* 483 */         System.arraycopy(this.userChildIndexOrder, 0, arrayOfInt, 0, this.userChildIndexOrder.length);
/*     */ 
/*     */         
/* 486 */         paramSetLiveState.ogCIOTableList.add(arrayOfInt);
/*     */       } 
/*     */       
/* 489 */       arrayList = (ArrayList)this.childrenOrderedPaths.get(paramInt);
/*     */       
/* 491 */       for (byte b = 0; b < this.orderedPaths.size(); b++) {
/* 492 */         OrderedPath orderedPath = ((OrderedPath)this.orderedPaths.get(b)).clonePath();
/*     */         
/* 494 */         orderedPath.addElementToPath(this, paramNodeRetained1.orderedId);
/* 495 */         arrayList.add(orderedPath);
/*     */       } 
/*     */     } 
/* 498 */     paramSetLiveState.orderedPaths = arrayList;
/* 499 */     paramNodeRetained1.setLive(paramSetLiveState);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\OrderedGroupRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */