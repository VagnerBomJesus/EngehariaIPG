/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ class ViewSpecificGroupRetained
/*     */   extends GroupRetained
/*     */ {
/*  23 */   ArrayList apiViewList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  27 */   ArrayList cachedViewList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  31 */   HashKey tempKey = new HashKey(250);
/*     */ 
/*     */   
/*  34 */   ArrayList parentLists = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   static final int SET_VIEW = 1;
/*     */ 
/*     */   
/*     */   static final int ADD_VIEW = 2;
/*     */ 
/*     */   
/*     */   static final int REMOVE_VIEW = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   void addView(View paramView) {
/*  49 */     Integer integer = new Integer(2);
/*     */     
/*  51 */     this.apiViewList.add(paramView);
/*  52 */     if (this.source.isLive() && paramView != null)
/*     */     {
/*     */       
/*  55 */       if (this.inSharedGroup) {
/*     */         
/*  57 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/*  58 */           ArrayList arrayList = (ArrayList)this.parentLists.get(b);
/*     */ 
/*     */           
/*  61 */           if (arrayList == null || arrayList.contains(paramView)) {
/*  62 */             Object[] arrayOfObject = new Object[4];
/*  63 */             ArrayList arrayList1 = new ArrayList();
/*  64 */             ArrayList arrayList2 = new ArrayList();
/*  65 */             int[] arrayOfInt = new int[10];
/*     */             
/*  67 */             HashKey hashKey = this.localToVworldKeys[b];
/*  68 */             arrayList1.add(this);
/*  69 */             arrayOfInt[0] = b;
/*  70 */             arrayOfObject[0] = paramView;
/*  71 */             arrayOfObject[1] = arrayList1;
/*  72 */             arrayOfObject[2] = arrayList2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  78 */             arrayOfObject[3] = super.processViewSpecificInfo(2, hashKey, paramView, arrayList1, arrayOfInt, arrayList2);
/*     */ 
/*     */             
/*  81 */             J3dMessage j3dMessage = new J3dMessage();
/*  82 */             j3dMessage.type = 56;
/*  83 */             j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */             
/*  87 */             j3dMessage.universe = this.universe;
/*  88 */             j3dMessage.args[0] = integer;
/*  89 */             j3dMessage.args[1] = arrayOfObject;
/*  90 */             VirtualUniverse.mc.processMessage(j3dMessage);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/*  96 */         ArrayList arrayList = (ArrayList)this.parentLists.get(0);
/*     */ 
/*     */ 
/*     */         
/* 100 */         if (arrayList == null || arrayList.contains(paramView)) {
/* 101 */           Object[] arrayOfObject = new Object[4];
/* 102 */           ArrayList arrayList1 = new ArrayList();
/* 103 */           ArrayList arrayList2 = new ArrayList();
/* 104 */           int[] arrayOfInt = new int[10];
/*     */           
/* 106 */           arrayOfObject[0] = paramView;
/* 107 */           arrayOfObject[1] = arrayList1;
/* 108 */           arrayOfObject[2] = arrayList2;
/*     */           
/* 110 */           arrayList1.add(this);
/* 111 */           arrayOfInt[0] = 0;
/* 112 */           this.tempKey.reset();
/* 113 */           arrayOfObject[3] = super.processViewSpecificInfo(2, this.tempKey, paramView, arrayList1, arrayOfInt, arrayList2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 123 */           J3dMessage j3dMessage = new J3dMessage();
/* 124 */           j3dMessage.type = 56;
/* 125 */           j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */           
/* 129 */           j3dMessage.universe = this.universe;
/* 130 */           j3dMessage.args[0] = integer;
/* 131 */           j3dMessage.args[1] = arrayOfObject;
/* 132 */           VirtualUniverse.mc.processMessage(j3dMessage);
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
/*     */   void setView(View paramView, int paramInt) {
/* 144 */     View view = (View)this.apiViewList.get(paramInt);
/* 145 */     Integer integer = new Integer(1);
/*     */     
/* 147 */     if (view == paramView) {
/*     */       return;
/*     */     }
/* 150 */     this.apiViewList.set(paramInt, paramView);
/* 151 */     if (this.source.isLive())
/*     */     {
/*     */       
/* 154 */       if (this.inSharedGroup) {
/*     */         
/* 156 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 157 */           ArrayList arrayList1 = (ArrayList)this.parentLists.get(b);
/* 158 */           Object[] arrayOfObject = new Object[8];
/* 159 */           ArrayList arrayList2 = new ArrayList();
/* 160 */           ArrayList arrayList3 = new ArrayList();
/* 161 */           ArrayList arrayList4 = new ArrayList();
/* 162 */           ArrayList arrayList5 = new ArrayList();
/* 163 */           int[] arrayOfInt1 = new int[10];
/* 164 */           int[] arrayOfInt2 = new int[10];
/*     */           
/* 166 */           arrayOfObject[0] = paramView;
/* 167 */           arrayOfObject[1] = arrayList2;
/* 168 */           arrayOfObject[2] = arrayList4;
/* 169 */           arrayOfObject[4] = view;
/* 170 */           arrayOfObject[5] = arrayList3;
/* 171 */           arrayOfObject[6] = arrayList5;
/*     */           
/* 173 */           HashKey hashKey = this.localToVworldKeys[b];
/* 174 */           if (view != null && (arrayList1 == null || arrayList1.contains(view))) {
/* 175 */             arrayList3.add(this);
/* 176 */             arrayOfInt2[0] = b;
/* 177 */             arrayOfObject[7] = super.processViewSpecificInfo(4, hashKey, view, arrayList3, arrayOfInt2, arrayList5);
/*     */           } 
/*     */ 
/*     */           
/* 181 */           if (paramView != null && (arrayList1 == null || arrayList1.contains(paramView))) {
/* 182 */             arrayList2.add(this);
/* 183 */             arrayOfInt1[0] = b;
/* 184 */             arrayOfObject[3] = super.processViewSpecificInfo(2, hashKey, paramView, arrayList2, arrayOfInt1, arrayList4);
/*     */           } 
/*     */           
/* 187 */           J3dMessage j3dMessage = new J3dMessage();
/* 188 */           j3dMessage.type = 56;
/* 189 */           j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */           
/* 193 */           j3dMessage.universe = this.universe;
/* 194 */           j3dMessage.args[0] = integer;
/* 195 */           j3dMessage.args[1] = arrayOfObject;
/* 196 */           VirtualUniverse.mc.processMessage(j3dMessage);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 201 */         ArrayList arrayList1 = (ArrayList)this.parentLists.get(0);
/* 202 */         Object[] arrayOfObject = new Object[8];
/* 203 */         ArrayList arrayList2 = new ArrayList();
/* 204 */         ArrayList arrayList3 = new ArrayList();
/* 205 */         ArrayList arrayList4 = new ArrayList();
/* 206 */         ArrayList arrayList5 = new ArrayList();
/* 207 */         int[] arrayOfInt1 = new int[10];
/* 208 */         int[] arrayOfInt2 = new int[10];
/*     */         
/* 210 */         arrayOfObject[0] = paramView;
/* 211 */         arrayOfObject[1] = arrayList2;
/* 212 */         arrayOfObject[2] = arrayList4;
/* 213 */         arrayOfObject[4] = view;
/* 214 */         arrayOfObject[5] = arrayList3;
/* 215 */         arrayOfObject[6] = arrayList5;
/*     */ 
/*     */ 
/*     */         
/* 219 */         this.tempKey.reset();
/* 220 */         if (view != null && (arrayList1 == null || arrayList1.contains(view))) {
/* 221 */           arrayList3.add(this);
/* 222 */           arrayOfInt2[0] = 0;
/* 223 */           arrayOfObject[7] = super.processViewSpecificInfo(4, this.tempKey, view, arrayList3, arrayOfInt2, arrayList5);
/*     */         } 
/*     */         
/* 226 */         if (paramView != null && (arrayList1 == null || arrayList1.contains(paramView))) {
/* 227 */           this.tempKey.reset();
/* 228 */           arrayList2.add(this);
/* 229 */           arrayOfInt1[0] = 0;
/* 230 */           arrayOfObject[3] = super.processViewSpecificInfo(2, this.tempKey, paramView, arrayList2, arrayOfInt1, arrayList4);
/*     */         } 
/*     */         
/* 233 */         J3dMessage j3dMessage = new J3dMessage();
/* 234 */         j3dMessage.type = 56;
/* 235 */         j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 240 */         j3dMessage.universe = this.universe;
/* 241 */         j3dMessage.args[0] = integer;
/* 242 */         j3dMessage.args[1] = arrayOfObject;
/* 243 */         VirtualUniverse.mc.processMessage(j3dMessage);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int[] processViewSpecificInfo(int paramInt, HashKey paramHashKey, View paramView, ArrayList paramArrayList1, int[] paramArrayOfInt, ArrayList paramArrayList2) {
/* 252 */     int i = 0;
/* 253 */     Object object = null;
/* 254 */     int[] arrayOfInt = null;
/*     */ 
/*     */     
/* 257 */     if (this.source.isLive()) {
/* 258 */       if (this.inSharedGroup) {
/* 259 */         i = paramHashKey.equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */       }
/*     */       
/* 262 */       if (paramInt == 2) {
/* 263 */         ArrayList arrayList = (ArrayList)this.parentLists.get(i);
/* 264 */         arrayList.add(paramView);
/*     */       }
/* 266 */       else if (paramInt == 4) {
/* 267 */         ArrayList arrayList = (ArrayList)this.parentLists.get(i);
/* 268 */         arrayList.remove(paramView);
/*     */       } 
/* 270 */       if (this.apiViewList.contains(paramView)) {
/*     */         
/* 272 */         paramArrayList1.add(this);
/* 273 */         if (paramArrayOfInt.length < paramArrayList1.size()) {
/*     */           
/* 275 */           arrayOfInt = new int[paramArrayOfInt.length + 20];
/* 276 */           System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramArrayOfInt.length);
/* 277 */           paramArrayOfInt = arrayOfInt;
/*     */         } 
/* 279 */         if (paramInt == 2) {
/* 280 */           if (this.inSharedGroup) {
/* 281 */             paramArrayOfInt[paramArrayList1.size() - 1] = i;
/*     */           }
/*     */           else {
/*     */             
/* 285 */             paramArrayOfInt[paramArrayList1.size() - 1] = 0;
/*     */           }
/*     */         
/* 288 */         } else if (paramInt == 4) {
/* 289 */           if (this.inSharedGroup) {
/* 290 */             paramArrayOfInt[paramArrayList1.size() - 1] = i;
/*     */           } else {
/*     */             
/* 293 */             paramArrayOfInt[paramArrayList1.size() - 1] = 0;
/*     */           } 
/*     */         } 
/* 296 */         return super.processViewSpecificInfo(paramInt, paramHashKey, paramView, paramArrayList1, paramArrayOfInt, paramArrayList2);
/*     */       } 
/*     */     } 
/* 299 */     return paramArrayOfInt;
/*     */   }
/*     */ 
/*     */   
/* 303 */   View getView(int paramInt) { return (View)this.apiViewList.get(paramInt); }
/*     */ 
/*     */ 
/*     */   
/*     */   void insertView(View paramView, int paramInt) {
/* 308 */     Integer integer = new Integer(2);
/*     */     
/* 310 */     this.apiViewList.add(paramInt, paramView);
/* 311 */     if (this.source.isLive() && paramView != null)
/*     */     {
/*     */       
/* 314 */       if (this.inSharedGroup) {
/*     */         
/* 316 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 317 */           ArrayList arrayList = (ArrayList)this.parentLists.get(b);
/*     */ 
/*     */           
/* 320 */           if (arrayList == null || arrayList.contains(paramView)) {
/* 321 */             Object[] arrayOfObject = new Object[4];
/* 322 */             ArrayList arrayList1 = new ArrayList();
/* 323 */             ArrayList arrayList2 = new ArrayList();
/* 324 */             int[] arrayOfInt = new int[10];
/*     */             
/* 326 */             HashKey hashKey = this.localToVworldKeys[b];
/* 327 */             arrayList1.add(this);
/* 328 */             arrayOfInt[0] = b;
/* 329 */             arrayOfObject[0] = paramView;
/* 330 */             arrayOfObject[1] = arrayList1;
/* 331 */             arrayOfObject[2] = arrayList2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 337 */             arrayOfObject[3] = super.processViewSpecificInfo(2, hashKey, paramView, arrayList1, arrayOfInt, arrayList2);
/*     */ 
/*     */             
/* 340 */             J3dMessage j3dMessage = new J3dMessage();
/* 341 */             j3dMessage.type = 56;
/* 342 */             j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */             
/* 346 */             j3dMessage.universe = this.universe;
/* 347 */             j3dMessage.args[0] = integer;
/* 348 */             j3dMessage.args[1] = arrayOfObject;
/* 349 */             VirtualUniverse.mc.processMessage(j3dMessage);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 355 */         ArrayList arrayList = (ArrayList)this.parentLists.get(0);
/*     */ 
/*     */ 
/*     */         
/* 359 */         if (arrayList == null || arrayList.contains(paramView)) {
/* 360 */           Object[] arrayOfObject = new Object[4];
/* 361 */           ArrayList arrayList1 = new ArrayList();
/* 362 */           ArrayList arrayList2 = new ArrayList();
/* 363 */           int[] arrayOfInt = new int[10];
/*     */           
/* 365 */           arrayOfObject[0] = paramView;
/* 366 */           arrayOfObject[1] = arrayList1;
/* 367 */           arrayOfObject[2] = arrayList2;
/*     */           
/* 369 */           arrayList1.add(this);
/* 370 */           arrayOfInt[0] = 0;
/* 371 */           this.tempKey.reset();
/* 372 */           arrayOfObject[3] = super.processViewSpecificInfo(2, this.tempKey, paramView, arrayList1, arrayOfInt, arrayList2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 382 */           J3dMessage j3dMessage = new J3dMessage();
/* 383 */           j3dMessage.type = 56;
/* 384 */           j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */           
/* 388 */           j3dMessage.universe = this.universe;
/* 389 */           j3dMessage.args[0] = integer;
/* 390 */           j3dMessage.args[1] = arrayOfObject;
/* 391 */           VirtualUniverse.mc.processMessage(j3dMessage);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeView(int paramInt) {
/* 401 */     View view = (View)this.apiViewList.remove(paramInt);
/* 402 */     if (this.source.isLive() && view != null)
/*     */     {
/*     */       
/* 405 */       if (this.inSharedGroup) {
/*     */         
/* 407 */         for (byte b = 0; b < this.localToVworldKeys.length; b++) {
/* 408 */           ArrayList arrayList = (ArrayList)this.parentLists.get(b);
/*     */ 
/*     */           
/* 411 */           if (arrayList == null || arrayList.contains(view)) {
/* 412 */             Object[] arrayOfObject = new Object[4];
/* 413 */             ArrayList arrayList1 = new ArrayList();
/* 414 */             ArrayList arrayList2 = new ArrayList();
/* 415 */             int[] arrayOfInt = new int[10];
/*     */             
/* 417 */             arrayOfObject[0] = view;
/* 418 */             arrayOfObject[1] = arrayList1;
/* 419 */             arrayOfObject[2] = arrayList2;
/* 420 */             HashKey hashKey = this.localToVworldKeys[b];
/*     */             
/* 422 */             arrayList1.add(this);
/* 423 */             arrayOfInt[0] = b;
/*     */             
/* 425 */             arrayOfObject[3] = super.processViewSpecificInfo(4, hashKey, view, arrayList1, arrayOfInt, arrayList2);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 430 */             J3dMessage j3dMessage = new J3dMessage();
/* 431 */             j3dMessage.type = 56;
/* 432 */             j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */             
/* 436 */             j3dMessage.universe = this.universe;
/* 437 */             j3dMessage.args[0] = new Integer(4);
/* 438 */             j3dMessage.args[1] = arrayOfObject;
/* 439 */             VirtualUniverse.mc.processMessage(j3dMessage);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 445 */         ArrayList arrayList = (ArrayList)this.parentLists.get(0);
/*     */ 
/*     */ 
/*     */         
/* 449 */         if (arrayList == null || arrayList.contains(view)) {
/* 450 */           Object[] arrayOfObject = new Object[4];
/* 451 */           ArrayList arrayList1 = new ArrayList();
/* 452 */           ArrayList arrayList2 = new ArrayList();
/* 453 */           int[] arrayOfInt = new int[10];
/*     */           
/* 455 */           arrayOfObject[0] = view;
/* 456 */           arrayOfObject[1] = arrayList1;
/* 457 */           arrayOfObject[2] = arrayList2;
/* 458 */           arrayList1.add(this);
/* 459 */           arrayOfInt[0] = 0;
/*     */           
/* 461 */           this.tempKey.reset();
/* 462 */           arrayOfObject[3] = super.processViewSpecificInfo(4, this.tempKey, view, arrayList1, arrayOfInt, arrayList2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 471 */           J3dMessage j3dMessage = new J3dMessage();
/* 472 */           j3dMessage.type = 56;
/* 473 */           j3dMessage.threads = 4738;
/*     */ 
/*     */ 
/*     */           
/* 477 */           j3dMessage.universe = this.universe;
/* 478 */           j3dMessage.args[0] = new Integer(4);
/* 479 */           j3dMessage.args[1] = arrayOfObject;
/* 480 */           VirtualUniverse.mc.processMessage(j3dMessage);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   Enumeration getAllViews() {
/* 488 */     Vector vector = new Vector();
/* 489 */     for (byte b = 0; b < this.apiViewList.size(); b++) {
/* 490 */       vector.add(this.apiViewList.get(b));
/*     */     }
/* 492 */     return vector.elements();
/*     */   }
/*     */ 
/*     */   
/* 496 */   int numViews() { return this.apiViewList.size(); }
/*     */ 
/*     */ 
/*     */   
/* 500 */   int indexOfView(View paramView) { return this.apiViewList.indexOf(paramView); }
/*     */ 
/*     */ 
/*     */   
/* 504 */   void removeView(View paramView) { removeView(this.apiViewList.indexOf(paramView)); }
/*     */ 
/*     */   
/*     */   void removeAllViews() {
/* 508 */     int i = this.apiViewList.size();
/* 509 */     for (byte b = 0; b < i; b++) {
/* 510 */       removeView(0);
/*     */     }
/*     */   }
/*     */   
/*     */   void compile(CompileState paramCompileState) {
/* 515 */     super.compile(paramCompileState);
/*     */ 
/*     */     
/* 518 */     this.mergeFlag = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 524 */     if (this.inBackgroundGroup) {
/* 525 */       throw new IllegalSceneGraphException(J3dI18N.getString("ViewSpecificGroup3"));
/*     */     }
/*     */ 
/*     */     
/* 529 */     paramSetLiveState.inViewSpecificGroup = true;
/* 530 */     ArrayList arrayList = paramSetLiveState.viewLists;
/* 531 */     if (paramSetLiveState.changedViewGroup == null) {
/* 532 */       paramSetLiveState.changedViewGroup = new ArrayList();
/* 533 */       paramSetLiveState.changedViewList = new ArrayList();
/* 534 */       paramSetLiveState.keyList = new int[10];
/* 535 */       paramSetLiveState.viewScopedNodeList = new ArrayList();
/* 536 */       paramSetLiveState.scopedNodesViewList = new ArrayList();
/*     */     } 
/* 538 */     super.setLive(paramSetLiveState);
/* 539 */     paramSetLiveState.viewLists = arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 544 */     ArrayList arrayList = paramSetLiveState.viewLists;
/* 545 */     if (paramSetLiveState.changedViewGroup == null) {
/* 546 */       paramSetLiveState.changedViewGroup = new ArrayList();
/* 547 */       paramSetLiveState.changedViewList = new ArrayList();
/* 548 */       paramSetLiveState.keyList = new int[10];
/* 549 */       paramSetLiveState.viewScopedNodeList = new ArrayList();
/* 550 */       paramSetLiveState.scopedNodesViewList = new ArrayList();
/*     */     } 
/*     */ 
/*     */     
/* 554 */     int[] arrayOfInt = null;
/*     */     
/* 556 */     if (this.inSharedGroup && paramSetLiveState.keys.length != this.localToVworld.length) {
/* 557 */       arrayOfInt = new int[paramSetLiveState.keys.length];
/* 558 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 559 */         arrayOfInt[b] = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */       }
/*     */     } 
/* 562 */     super.clearLive(paramSetLiveState);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 567 */     if (!this.inSharedGroup || this.localToVworld == null) {
/* 568 */       this.viewLists.clear();
/*     */     }
/*     */     else {
/*     */       
/* 572 */       for (int i = arrayOfInt.length - 1; i >= 0; i--) {
/* 573 */         if (arrayOfInt[i] >= 0) {
/* 574 */           this.viewLists.remove(arrayOfInt[i]);
/*     */         }
/*     */       } 
/*     */     } 
/* 578 */     paramSetLiveState.viewLists = arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 583 */     if (!this.inSharedGroup || paramSetLiveState.keys.length == this.localToVworld.length) {
/* 584 */       paramSetLiveState.changedViewGroup.add(this);
/*     */       
/* 586 */       int i = paramSetLiveState.changedViewGroup.size();
/* 587 */       if (paramSetLiveState.keyList.length < i) {
/* 588 */         int[] arrayOfInt = new int[paramSetLiveState.keyList.length + 20];
/* 589 */         System.arraycopy(paramSetLiveState.keyList, 0, arrayOfInt, 0, paramSetLiveState.keyList.length);
/* 590 */         paramSetLiveState.keyList = arrayOfInt;
/*     */       } 
/*     */       
/* 593 */       paramSetLiveState.keyList[i - 1] = -1;
/* 594 */       this.parentLists.clear();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 599 */       int j = paramSetLiveState.changedViewGroup.size();
/* 600 */       if (paramSetLiveState.keyList.length < j + 1 + paramSetLiveState.keys.length) {
/* 601 */         int[] arrayOfInt = new int[paramSetLiveState.keyList.length + paramSetLiveState.keys.length + 20];
/* 602 */         System.arraycopy(paramSetLiveState.keyList, 0, arrayOfInt, 0, paramSetLiveState.keyList.length);
/* 603 */         paramSetLiveState.keyList = arrayOfInt;
/*     */       } 
/*     */ 
/*     */       
/* 607 */       for (int i = paramSetLiveState.keys.length - 1; i >= 0; i--) {
/* 608 */         int k = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 609 */         if (k >= 0) {
/* 610 */           paramSetLiveState.changedViewGroup.add(this);
/* 611 */           paramSetLiveState.keyList[paramSetLiveState.changedViewGroup.size() - 1] = k;
/* 612 */           this.parentLists.remove(k);
/*     */         } 
/*     */       } 
/*     */     } 
/* 616 */     paramSetLiveState.viewLists = this.viewLists;
/* 617 */     super.removeNodeData(paramSetLiveState);
/*     */   }
/*     */   
/*     */   void updateCachedInformation(int paramInt1, View paramView, int paramInt2) {
/* 621 */     ArrayList arrayList = (ArrayList)this.cachedViewList.get(paramInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 629 */     if ((paramInt1 & 0x2) != 0) {
/* 630 */       arrayList.add(paramView);
/*     */     }
/* 632 */     else if ((paramInt1 & 0x4) != 0) {
/* 633 */       arrayList.remove(paramView);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNodeData(SetLiveState paramSetLiveState) {
/* 645 */     super.setNodeData(paramSetLiveState);
/* 646 */     if (!this.inSharedGroup) {
/* 647 */       int i = paramSetLiveState.changedViewGroup.size();
/* 648 */       if (paramSetLiveState.keyList.length < i + 1) {
/* 649 */         int[] arrayOfInt = new int[paramSetLiveState.keyList.length + 20];
/* 650 */         System.arraycopy(paramSetLiveState.keyList, 0, arrayOfInt, 0, paramSetLiveState.keyList.length);
/* 651 */         paramSetLiveState.keyList = arrayOfInt;
/*     */       } 
/*     */       
/* 654 */       setAuxData(paramSetLiveState, 0, 0);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 659 */       int i = paramSetLiveState.changedViewGroup.size();
/* 660 */       if (paramSetLiveState.keyList.length < i + 1 + paramSetLiveState.keys.length) {
/* 661 */         int[] arrayOfInt = new int[paramSetLiveState.keyList.length + paramSetLiveState.keys.length + 20];
/* 662 */         System.arraycopy(paramSetLiveState.keyList, 0, arrayOfInt, 0, paramSetLiveState.keyList.length);
/* 663 */         paramSetLiveState.keyList = arrayOfInt;
/*     */       } 
/*     */ 
/*     */       
/* 667 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 668 */         int j = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */ 
/*     */         
/* 671 */         if (j >= 0) {
/* 672 */           setAuxData(paramSetLiveState, b, j);
/*     */         } else {
/*     */           
/* 675 */           MasterControl.getCoreLogger().severe("Can't Find matching hashKey in setNodeData.");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 680 */     paramSetLiveState.viewLists = this.viewLists;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setAuxData(SetLiveState paramSetLiveState, int paramInt1, int paramInt2) {
/* 686 */     ArrayList arrayList1, arrayList2 = null;
/* 687 */     int i = this.apiViewList.size();
/* 688 */     if (paramSetLiveState.viewLists != null) {
/*     */       
/* 690 */       arrayList2 = (ArrayList)paramSetLiveState.viewLists.get(paramInt2);
/* 691 */       if (arrayList2 != null) {
/* 692 */         arrayList1 = new ArrayList();
/* 693 */         for (byte b = 0; b < i; b++) {
/* 694 */           Object object = this.apiViewList.get(b);
/*     */           
/* 696 */           for (byte b1 = 0; b1 < arrayList2.size(); b1++) {
/* 697 */             if (object == arrayList2.get(b1)) {
/* 698 */               arrayList1.add(object);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 705 */         arrayList1 = new ArrayList();
/*     */         
/* 707 */         for (byte b = 0; b < i; b++) {
/* 708 */           Object object = this.apiViewList.get(b);
/* 709 */           if (object != null) {
/* 710 */             arrayList1.add(object);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 716 */       arrayList1 = new ArrayList();
/*     */       
/* 718 */       for (byte b = 0; b < i; b++) {
/* 719 */         Object object = this.apiViewList.get(b);
/* 720 */         if (object != null) {
/* 721 */           arrayList1.add(object);
/*     */         }
/*     */       } 
/*     */     } 
/* 725 */     if (arrayList2 != null) {
/* 726 */       this.parentLists.add(paramInt2, arrayList2.clone());
/*     */     } else {
/*     */       
/* 729 */       this.parentLists.add(paramInt2, null);
/*     */     } 
/*     */     
/* 732 */     this.viewLists.add(paramInt2, arrayList1);
/* 733 */     paramSetLiveState.changedViewGroup.add(this);
/* 734 */     paramSetLiveState.changedViewList.add(arrayList1);
/* 735 */     if (this.localToVworldKeys != null) {
/* 736 */       paramSetLiveState.keyList[paramSetLiveState.changedViewGroup.size() - 1] = paramInt2;
/*     */     } else {
/*     */       
/* 739 */       paramSetLiveState.keyList[paramSetLiveState.changedViewGroup.size() - 1] = 0;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ViewSpecificGroupRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */