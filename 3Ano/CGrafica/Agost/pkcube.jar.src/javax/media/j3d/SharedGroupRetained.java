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
/*     */ class SharedGroupRetained
/*     */   extends GroupRetained
/*     */   implements TargetsInterface
/*     */ {
/*  38 */   ArrayList childTransformLinks = new ArrayList(1);
/*     */ 
/*     */ 
/*     */   
/*  42 */   HashKey currentKey = new HashKey();
/*     */ 
/*     */   
/*  45 */   HashKey switchKey = new HashKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   Vector parents = new Vector(1);
/*     */ 
/*     */   
/*  53 */   CachedTargets[] j3dCTs = null;
/*     */ 
/*     */   
/*  56 */   CachedTargets[] cachedTargets = null;
/*     */ 
/*     */   
/*  59 */   int localTargetThreads = 0;
/*     */   
/*  61 */   int targetThreads = 0;
/*     */   
/*  63 */   ArrayList switchStates = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAuxData(SetLiveState paramSetLiveState, int paramInt1, int paramInt2) {
/*  74 */     super.setAuxData(paramSetLiveState, paramInt1, paramInt2);
/*     */     
/*  76 */     this.branchGroupPaths.add(paramInt2, paramSetLiveState.branchGroupPaths.get(paramInt1));
/*     */     
/*  78 */     if (this.orderedPaths == null) {
/*  79 */       this.orderedPaths = new ArrayList(1);
/*     */     }
/*  81 */     this.orderedPaths.add(paramInt2, paramSetLiveState.orderedPaths.get(paramInt1));
/*     */     
/*  83 */     if (this.switchStates == null) {
/*  84 */       this.switchStates = new ArrayList(1);
/*     */     }
/*  86 */     this.switchStates.add(paramInt2, paramSetLiveState.switchStates.get(paramInt1));
/*     */     
/*  88 */     if (this.viewLists == null) {
/*  89 */       this.viewLists = new ArrayList(1);
/*     */     }
/*     */ 
/*     */     
/*  93 */     if (paramSetLiveState.viewLists != null) {
/*  94 */       this.viewLists.add(paramInt2, paramSetLiveState.viewLists.get(paramInt1));
/*     */     } else {
/*     */       
/*  97 */       this.viewLists.add(paramInt2, null);
/*     */     } 
/*     */     
/* 100 */     if (this.lights == null) {
/* 101 */       this.lights = new ArrayList(1);
/*     */     }
/* 103 */     if (paramSetLiveState.lights != null) {
/* 104 */       this.lights.add(paramInt2, paramSetLiveState.lights.get(paramInt1));
/*     */     } else {
/*     */       
/* 107 */       this.lights.add(paramInt2, null);
/*     */     } 
/*     */     
/* 110 */     if (this.fogs == null) {
/* 111 */       this.fogs = new ArrayList(1);
/*     */     }
/* 113 */     if (paramSetLiveState.fogs != null) {
/* 114 */       this.fogs.add(paramInt2, paramSetLiveState.fogs.get(paramInt1));
/*     */     } else {
/*     */       
/* 117 */       this.fogs.add(paramInt2, null);
/*     */     } 
/*     */ 
/*     */     
/* 121 */     if (this.modelClips == null) {
/* 122 */       this.modelClips = new ArrayList(1);
/*     */     }
/* 124 */     if (paramSetLiveState.modelClips != null) {
/* 125 */       this.modelClips.add(paramInt2, paramSetLiveState.modelClips.get(paramInt1));
/*     */     } else {
/*     */       
/* 128 */       this.modelClips.add(paramInt2, null);
/*     */     } 
/*     */ 
/*     */     
/* 132 */     if (this.altAppearances == null) {
/* 133 */       this.altAppearances = new ArrayList(1);
/*     */     }
/* 135 */     if (paramSetLiveState.altAppearances != null) {
/* 136 */       this.altAppearances.add(paramInt2, paramSetLiveState.altAppearances.get(paramInt1));
/*     */     } else {
/*     */       
/* 139 */       this.altAppearances.add(paramInt2, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNodeData(SetLiveState paramSetLiveState) {
/*     */     int j;
/* 149 */     if (this.localToVworld == null) {
/* 150 */       this.localToVworld = new Transform3D[paramSetLiveState.keys.length][];
/* 151 */       this.localToVworldIndex = new int[paramSetLiveState.keys.length][];
/* 152 */       this.localToVworldKeys = new HashKey[paramSetLiveState.keys.length];
/* 153 */       this.cachedTargets = new CachedTargets[paramSetLiveState.keys.length];
/* 154 */       j = 0;
/*     */     }
/*     */     else {
/*     */       
/* 158 */       int k = this.localToVworld.length + paramSetLiveState.keys.length;
/*     */       
/* 160 */       Transform3D[][] arrayOfTransform3D = new Transform3D[k][];
/* 161 */       HashKey[] arrayOfHashKey = new HashKey[k];
/* 162 */       int[][] arrayOfInt1 = new int[k][];
/* 163 */       CachedTargets[] arrayOfCachedTargets = new CachedTargets[k];
/*     */       
/* 165 */       j = this.localToVworld.length;
/*     */ 
/*     */       
/* 168 */       System.arraycopy(this.localToVworld, 0, arrayOfTransform3D, 0, this.localToVworld.length);
/* 169 */       System.arraycopy(this.localToVworldIndex, 0, arrayOfInt1, 0, this.localToVworldIndex.length);
/*     */       
/* 171 */       System.arraycopy(this.localToVworldKeys, 0, arrayOfHashKey, 0, this.localToVworldKeys.length);
/*     */       
/* 173 */       System.arraycopy(this.cachedTargets, 0, arrayOfCachedTargets, 0, this.cachedTargets.length);
/*     */ 
/*     */       
/* 176 */       this.localToVworld = arrayOfTransform3D;
/* 177 */       this.localToVworldIndex = arrayOfInt1;
/* 178 */       this.localToVworldKeys = arrayOfHashKey;
/* 179 */       this.cachedTargets = arrayOfCachedTargets;
/*     */     } 
/*     */     
/* 182 */     int[] arrayOfInt = new int[1];
/*     */ 
/*     */     
/* 185 */     paramSetLiveState.hashkeyIndex = new int[paramSetLiveState.keys.length];
/*     */ 
/*     */     
/* 188 */     paramSetLiveState.parentBranchGroupPaths = this.branchGroupPaths; int i;
/*     */     byte b;
/* 190 */     for (i = j, b = 0; i < this.localToVworld.length; i++, b++) {
/*     */       
/* 192 */       if (paramSetLiveState.keys[b].equals(this.localToVworldKeys, arrayOfInt, 0, i)) {
/* 193 */         MasterControl.getCoreLogger().severe("Found matching hashKey in setNodeData.");
/*     */       }
/* 195 */       paramSetLiveState.hashkeyIndex[b] = arrayOfInt[0];
/*     */ 
/*     */       
/* 198 */       if (arrayOfInt[0] == i) {
/* 199 */         this.localToVworldKeys[i] = paramSetLiveState.keys[b];
/* 200 */         this.localToVworld[i] = paramSetLiveState.currentTransforms[b];
/* 201 */         this.localToVworldIndex[i] = paramSetLiveState.currentTransformsIndex[b];
/*     */       } else {
/*     */         
/* 204 */         int k = arrayOfInt[0] + 1;
/* 205 */         int m = i - arrayOfInt[0];
/*     */ 
/*     */ 
/*     */         
/* 209 */         System.arraycopy(this.localToVworldKeys, arrayOfInt[0], this.localToVworldKeys, k, m);
/*     */         
/* 211 */         System.arraycopy(this.localToVworld, arrayOfInt[0], this.localToVworld, k, m);
/*     */         
/* 213 */         System.arraycopy(this.localToVworldIndex, arrayOfInt[0], this.localToVworldIndex, k, m);
/*     */         
/* 215 */         System.arraycopy(this.cachedTargets, arrayOfInt[0], this.cachedTargets, k, m);
/*     */ 
/*     */         
/* 218 */         this.localToVworldKeys[arrayOfInt[0]] = paramSetLiveState.keys[b];
/* 219 */         this.localToVworld[arrayOfInt[0]] = paramSetLiveState.currentTransforms[b];
/* 220 */         this.localToVworldIndex[arrayOfInt[0]] = paramSetLiveState.currentTransformsIndex[b];
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 226 */       setAuxData(paramSetLiveState, b, arrayOfInt[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     paramSetLiveState.localToVworld = this.localToVworld;
/* 233 */     paramSetLiveState.localToVworldIndex = this.localToVworldIndex;
/* 234 */     paramSetLiveState.localToVworldKeys = this.localToVworldKeys;
/* 235 */     paramSetLiveState.orderedPaths = this.orderedPaths;
/* 236 */     paramSetLiveState.switchStates = this.switchStates;
/*     */ 
/*     */     
/* 239 */     paramSetLiveState.childTransformLinks = this.childTransformLinks;
/* 240 */     paramSetLiveState.parentTransformLink = this;
/* 241 */     paramSetLiveState.parentSwitchLink = this;
/* 242 */     paramSetLiveState.viewLists = this.viewLists;
/* 243 */     paramSetLiveState.lights = this.lights;
/* 244 */     paramSetLiveState.fogs = this.fogs;
/* 245 */     paramSetLiveState.altAppearances = this.altAppearances;
/* 246 */     paramSetLiveState.modelClips = this.modelClips;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 252 */     Targets[] arrayOfTargets1 = null;
/*     */ 
/*     */     
/* 255 */     Transform3D[][] arrayOfTransform3D = paramSetLiveState.localToVworld;
/* 256 */     int[][] arrayOfInt = paramSetLiveState.localToVworldIndex;
/* 257 */     HashKey[] arrayOfHashKey = paramSetLiveState.localToVworldKeys;
/* 258 */     ArrayList arrayList1 = paramSetLiveState.orderedPaths;
/* 259 */     ArrayList arrayList2 = paramSetLiveState.viewLists;
/* 260 */     ArrayList arrayList3 = paramSetLiveState.lights;
/* 261 */     ArrayList arrayList4 = paramSetLiveState.fogs;
/* 262 */     ArrayList arrayList5 = paramSetLiveState.modelClips;
/* 263 */     ArrayList arrayList6 = paramSetLiveState.altAppearances;
/*     */     
/* 265 */     SharedGroupRetained sharedGroupRetained = paramSetLiveState.lastSharedGroup;
/* 266 */     Targets[] arrayOfTargets2 = paramSetLiveState.switchTargets;
/* 267 */     ArrayList arrayList7 = paramSetLiveState.switchStates;
/* 268 */     ArrayList arrayList8 = paramSetLiveState.childSwitchLinks;
/* 269 */     GroupRetained groupRetained1 = paramSetLiveState.parentSwitchLink;
/* 270 */     ArrayList arrayList9 = paramSetLiveState.childTransformLinks;
/* 271 */     GroupRetained groupRetained2 = paramSetLiveState.parentTransformLink;
/* 272 */     int[] arrayOfInt1 = paramSetLiveState.hashkeyIndex;
/*     */ 
/*     */ 
/*     */     
/* 276 */     paramSetLiveState.lastSharedGroup = this;
/*     */     
/* 278 */     Targets[] arrayOfTargets3 = paramSetLiveState.transformTargets;
/*     */     
/* 280 */     int i = paramSetLiveState.keys.length;
/* 281 */     arrayOfTargets1 = new Targets[i]; byte b;
/* 282 */     for (b = 0; b < i; b++) {
/* 283 */       if (paramSetLiveState.transformLevels[b] >= 0) {
/* 284 */         arrayOfTargets1[b] = new Targets();
/*     */       } else {
/* 286 */         arrayOfTargets1[b] = null;
/*     */       } 
/*     */     } 
/* 289 */     paramSetLiveState.transformTargets = arrayOfTargets1;
/*     */     
/* 291 */     super.setLive(paramSetLiveState);
/*     */ 
/*     */     
/* 294 */     for (b = 0; b < i; b++) {
/* 295 */       if (paramSetLiveState.transformTargets[b] != null) {
/* 296 */         int j = paramSetLiveState.hashkeyIndex[b];
/* 297 */         this.cachedTargets[j] = paramSetLiveState.transformTargets[b].snapShotInit();
/*     */       } 
/*     */     } 
/*     */     
/* 301 */     this.j3dCTs = new CachedTargets[this.cachedTargets.length];
/* 302 */     copyCachedTargets(0, this.j3dCTs);
/*     */     
/* 304 */     computeTargetThreads(0, this.cachedTargets);
/*     */ 
/*     */ 
/*     */     
/* 308 */     paramSetLiveState.localToVworld = arrayOfTransform3D;
/* 309 */     paramSetLiveState.localToVworldIndex = arrayOfInt;
/* 310 */     paramSetLiveState.localToVworldKeys = arrayOfHashKey;
/* 311 */     paramSetLiveState.orderedPaths = arrayList1;
/* 312 */     paramSetLiveState.viewLists = arrayList2;
/*     */     
/* 314 */     paramSetLiveState.lights = arrayList3;
/* 315 */     paramSetLiveState.fogs = arrayList4;
/* 316 */     paramSetLiveState.modelClips = arrayList5;
/* 317 */     paramSetLiveState.altAppearances = arrayList6;
/*     */     
/* 319 */     paramSetLiveState.lastSharedGroup = sharedGroupRetained;
/* 320 */     paramSetLiveState.switchTargets = arrayOfTargets2;
/* 321 */     paramSetLiveState.switchStates = arrayList7;
/*     */     
/* 323 */     paramSetLiveState.childSwitchLinks = arrayList8;
/* 324 */     paramSetLiveState.parentSwitchLink = groupRetained1;
/* 325 */     paramSetLiveState.childTransformLinks = arrayList9;
/* 326 */     paramSetLiveState.parentTransformLink = groupRetained2;
/*     */     
/* 328 */     paramSetLiveState.transformTargets = arrayOfTargets3;
/* 329 */     paramSetLiveState.hashkeyIndex = arrayOfInt1;
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
/*     */ 
/*     */ 
/*     */   
/*     */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 345 */     int i = this.children.size();
/*     */ 
/*     */ 
/*     */     
/* 349 */     if (this.refCount <= 0) {
/* 350 */       this.localToVworld = (Transform3D[][])null;
/* 351 */       this.localToVworldIndex = (int[][])null;
/* 352 */       this.localToVworldKeys = null;
/*     */ 
/*     */ 
/*     */       
/* 356 */       this.branchGroupPaths = new ArrayList(1);
/* 357 */       this.orderedPaths = null;
/* 358 */       this.switchStates = null;
/* 359 */       this.cachedTargets = null;
/* 360 */       this.targetThreads = 0;
/* 361 */       this.lights.clear();
/* 362 */       this.fogs.clear();
/* 363 */       this.modelClips.clear();
/* 364 */       this.altAppearances.clear();
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 370 */       int k = this.localToVworld.length - paramSetLiveState.keys.length;
/*     */       
/* 372 */       Transform3D[][] arrayOfTransform3D1 = new Transform3D[k][];
/* 373 */       HashKey[] arrayOfHashKey = new HashKey[k];
/* 374 */       Transform3D[][] arrayOfTransform3D2 = (Transform3D[][])null;
/* 375 */       int[][] arrayOfInt = new int[k][];
/* 376 */       CachedTargets[] arrayOfCachedTargets = new CachedTargets[k];
/*     */       
/* 378 */       int[] arrayOfInt1 = new int[paramSetLiveState.keys.length];
/* 379 */       int m = 0, n = 0;
/* 380 */       boolean bool = false;
/*     */       int j;
/* 382 */       for (j = 0; j < paramSetLiveState.keys.length; j++) {
/* 383 */         int i1 = paramSetLiveState.keys[j].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */         
/* 385 */         arrayOfInt1[j] = i1;
/*     */         
/* 387 */         if (i1 >= 0) {
/* 388 */           bool = true;
/* 389 */           if (i1 == m) {
/* 390 */             m++;
/*     */           } else {
/*     */             
/* 393 */             int i2 = i1 - m;
/* 394 */             System.arraycopy(this.localToVworld, m, arrayOfTransform3D1, n, i2);
/* 395 */             System.arraycopy(this.localToVworldIndex, m, arrayOfInt, n, i2);
/*     */             
/* 397 */             System.arraycopy(this.localToVworldKeys, m, arrayOfHashKey, n, i2);
/* 398 */             System.arraycopy(this.cachedTargets, m, arrayOfCachedTargets, n, i2);
/*     */ 
/*     */             
/* 401 */             m = i1 + 1;
/* 402 */             n += i2;
/*     */           } 
/*     */         } else {
/*     */           
/* 406 */           bool = false;
/* 407 */           MasterControl.getCoreLogger().severe("Can't Find matching hashKey in SG.removeNodeData.");
/*     */         } 
/*     */       } 
/*     */       
/* 411 */       if (bool == true && m < this.localToVworld.length) {
/* 412 */         int i1 = this.localToVworld.length - m;
/* 413 */         System.arraycopy(this.localToVworld, m, arrayOfTransform3D1, n, i1);
/* 414 */         System.arraycopy(this.localToVworldIndex, m, arrayOfInt, n, i1);
/*     */         
/* 416 */         System.arraycopy(this.localToVworldKeys, m, arrayOfHashKey, n, i1);
/* 417 */         System.arraycopy(this.cachedTargets, m, arrayOfCachedTargets, n, i1);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 422 */       for (j = arrayOfInt1.length - 1; j >= 0; j--) {
/* 423 */         if (arrayOfInt1[j] >= 0) {
/* 424 */           this.branchGroupPaths.remove(arrayOfInt1[j]);
/* 425 */           this.orderedPaths.remove(arrayOfInt1[j]);
/* 426 */           this.switchStates.remove(arrayOfInt1[j]);
/* 427 */           this.lights.remove(arrayOfInt1[j]);
/* 428 */           this.fogs.remove(arrayOfInt1[j]);
/* 429 */           this.modelClips.remove(arrayOfInt1[j]);
/* 430 */           this.altAppearances.remove(arrayOfInt1[j]);
/*     */         } 
/*     */       } 
/*     */       
/* 434 */       this.localToVworld = arrayOfTransform3D1;
/* 435 */       this.localToVworldIndex = arrayOfInt;
/* 436 */       this.localToVworldKeys = arrayOfHashKey;
/* 437 */       this.cachedTargets = arrayOfCachedTargets;
/*     */     } 
/* 439 */     paramSetLiveState.localToVworld = this.localToVworld;
/* 440 */     paramSetLiveState.localToVworldIndex = this.localToVworldIndex;
/* 441 */     paramSetLiveState.localToVworldKeys = this.localToVworldKeys;
/* 442 */     paramSetLiveState.orderedPaths = this.orderedPaths;
/* 443 */     paramSetLiveState.switchStates = this.switchStates;
/* 444 */     paramSetLiveState.viewLists = this.viewLists;
/* 445 */     paramSetLiveState.lights = this.lights;
/* 446 */     paramSetLiveState.fogs = this.fogs;
/* 447 */     paramSetLiveState.modelClips = this.modelClips;
/* 448 */     paramSetLiveState.altAppearances = this.altAppearances;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 455 */     Transform3D[][] arrayOfTransform3D = paramSetLiveState.localToVworld;
/* 456 */     int[][] arrayOfInt = paramSetLiveState.localToVworldIndex;
/* 457 */     HashKey[] arrayOfHashKey = paramSetLiveState.localToVworldKeys;
/* 458 */     ArrayList arrayList1 = paramSetLiveState.orderedPaths;
/* 459 */     ArrayList arrayList2 = paramSetLiveState.viewLists;
/*     */     
/* 461 */     ArrayList arrayList3 = paramSetLiveState.lights;
/* 462 */     ArrayList arrayList4 = paramSetLiveState.fogs;
/* 463 */     ArrayList arrayList5 = paramSetLiveState.modelClips;
/* 464 */     ArrayList arrayList6 = paramSetLiveState.altAppearances;
/*     */     
/* 466 */     Targets[] arrayOfTargets1 = paramSetLiveState.switchTargets;
/* 467 */     Targets[] arrayOfTargets2 = paramSetLiveState.transformTargets;
/*     */     
/* 469 */     paramSetLiveState.transformTargets = null;
/* 470 */     paramSetLiveState.switchTargets = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 475 */     int[] arrayOfInt1 = null;
/*     */     
/* 477 */     if (paramSetLiveState.keys.length != this.localToVworld.length) {
/* 478 */       arrayOfInt1 = new int[paramSetLiveState.keys.length];
/* 479 */       for (int i = paramSetLiveState.keys.length - 1; i >= 0; i--) {
/* 480 */         arrayOfInt1[i] = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */       }
/*     */     } 
/*     */     
/* 484 */     super.clearLive(paramSetLiveState);
/*     */ 
/*     */     
/* 487 */     if (this.refCount <= 0) {
/* 488 */       this.viewLists.clear();
/*     */     }
/*     */     else {
/*     */       
/* 492 */       for (int i = arrayOfInt1.length - 1; i >= 0; i--) {
/* 493 */         if (arrayOfInt1[i] >= 0) {
/* 494 */           this.viewLists.remove(arrayOfInt1[i]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 501 */     paramSetLiveState.localToVworld = arrayOfTransform3D;
/* 502 */     paramSetLiveState.localToVworldIndex = arrayOfInt;
/* 503 */     paramSetLiveState.localToVworldKeys = arrayOfHashKey;
/* 504 */     paramSetLiveState.orderedPaths = arrayList1;
/* 505 */     paramSetLiveState.viewLists = arrayList2;
/* 506 */     paramSetLiveState.lights = arrayList3;
/* 507 */     paramSetLiveState.fogs = arrayList4;
/* 508 */     paramSetLiveState.modelClips = arrayList5;
/* 509 */     paramSetLiveState.altAppearances = arrayList6;
/* 510 */     paramSetLiveState.transformTargets = arrayOfTargets2;
/* 511 */     paramSetLiveState.switchTargets = arrayOfTargets1;
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
/*     */   
/*     */   void updateChildLocalToVworld(HashKey paramHashKey, int paramInt, ArrayList paramArrayList1, ArrayList paramArrayList2, UpdateTargets paramUpdateTargets, ArrayList paramArrayList3) {
/* 525 */     CachedTargets cachedTargets1 = this.j3dCTs[paramInt];
/* 526 */     if (cachedTargets1 != null) {
/* 527 */       paramUpdateTargets.addCachedTargets(cachedTargets1);
/* 528 */       if (cachedTargets1.targetArr[5] != null) {
/* 529 */         gatherBlUsers(paramArrayList3, cachedTargets1.targetArr[5]);
/*     */       }
/*     */     } 
/*     */     
/* 533 */     synchronized (this.childTransformLinks) {
/* 534 */       for (byte b = 0; b < this.childTransformLinks.size(); b++) {
/* 535 */         Object object = this.childTransformLinks.get(b);
/*     */         
/* 537 */         if (object instanceof TransformGroupRetained) {
/* 538 */           TransformGroupRetained transformGroupRetained = (TransformGroupRetained)object;
/* 539 */           transformGroupRetained.updateChildLocalToVworld(transformGroupRetained.localToVworldKeys[paramInt], paramInt, paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 546 */           LinkRetained linkRetained = (LinkRetained)object;
/* 547 */           this.currentKey.set(paramHashKey);
/* 548 */           this.currentKey.append(LinkRetained.plus).append(linkRetained.nodeId);
/* 549 */           if (linkRetained.sharedGroup.localToVworldKeys != null) {
/* 550 */             int i = this.currentKey.equals(linkRetained.sharedGroup.localToVworldKeys, 0, linkRetained.sharedGroup.localToVworldKeys.length);
/*     */             
/* 552 */             if (i < 0) {
/* 553 */               System.err.println("SharedGroupRetained : Can't find hashKey");
/*     */             }
/*     */             
/* 556 */             if (i < linkRetained.sharedGroup.localToVworldKeys.length) {
/* 557 */               linkRetained.sharedGroup.updateChildLocalToVworld(linkRetained.sharedGroup.localToVworldKeys[i], i, paramArrayList1, paramArrayList2, paramUpdateTargets, paramArrayList3);
/*     */             }
/*     */           } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void traverseSwitchChild(int paramInt1, HashKey paramHashKey, int paramInt2, SwitchRetained paramSwitchRetained, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, ArrayList paramArrayList) {
/* 580 */     ArrayList arrayList = (ArrayList)this.childrenSwitchLinks.get(paramInt1);
/* 581 */     for (byte b = 0; b < arrayList.size(); b++) {
/* 582 */       Object object = arrayList.get(b);
/*     */       
/* 584 */       if (object instanceof SwitchRetained) {
/* 585 */         SwitchRetained switchRetained = (SwitchRetained)object;
/* 586 */         for (byte b1 = 0; b1 < switchRetained.children.size(); b1++) {
/* 587 */           switchRetained.traverseSwitchChild(b1, paramHashKey, paramInt2, paramSwitchRetained, paramBoolean1, paramBoolean2, paramBoolean3, paramInt3, paramArrayList);
/*     */         }
/*     */       } else {
/*     */         
/* 591 */         LinkRetained linkRetained = (LinkRetained)object;
/* 592 */         this.switchKey.set(paramHashKey);
/* 593 */         this.switchKey.append(LinkRetained.plus).append(linkRetained.nodeId);
/*     */         
/* 595 */         if (linkRetained.sharedGroup.localToVworldKeys != null) {
/*     */           
/* 597 */           int i = this.switchKey.equals(linkRetained.sharedGroup.localToVworldKeys, 0, linkRetained.sharedGroup.localToVworldKeys.length);
/*     */           
/* 599 */           if (i < 0) {
/* 600 */             System.err.println("SharedGroupRetained : Can't find hashKey");
/*     */           }
/*     */           
/* 603 */           if (i < linkRetained.sharedGroup.localToVworldKeys.length) {
/* 604 */             for (byte b1 = 0; b1 < linkRetained.sharedGroup.children.size(); b1++) {
/* 605 */               linkRetained.sharedGroup.traverseSwitchChild(b1, linkRetained.sharedGroup.localToVworldKeys[i], i, paramSwitchRetained, paramBoolean1, paramBoolean2, paramBoolean3, paramInt3, paramArrayList);
/*     */             }
/*     */           }
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
/*     */ 
/*     */ 
/*     */   
/*     */   void traverseSwitchParent() {
/* 622 */     for (byte b = 0; b < this.parents.size(); b++) {
/* 623 */       NodeRetained nodeRetained = (NodeRetained)this.parents.elementAt(b);
/* 624 */       if (nodeRetained.parentSwitchLink != null) {
/* 625 */         if (this.parentSwitchLink instanceof SwitchRetained) {
/* 626 */           ((SwitchRetained)this.parentSwitchLink).traverseSwitchParent();
/* 627 */         } else if (this.parentSwitchLink instanceof SharedGroupRetained) {
/* 628 */           ((SharedGroupRetained)this.parentSwitchLink).traverseSwitchParent();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void compile() {
/* 637 */     if (this.source.isCompiled() || VirtualUniverse.mc.disableCompile) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 645 */     CompileState compileState = new CompileState();
/*     */     
/* 647 */     this.isRoot = true;
/*     */     
/* 649 */     compile(compileState);
/* 650 */     merge(compileState);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Link[] getLinks() {
/*     */     Link[] arrayOfLink;
/* 671 */     synchronized (this.parents) {
/* 672 */       int i = this.parents.size();
/*     */       
/* 674 */       arrayOfLink = new Link[i];
/* 675 */       for (byte b = 0; b < i; b++)
/*     */       {
/* 677 */         arrayOfLink[b] = (Link)((LinkRetained)this.parents.elementAt(b)).source;
/*     */       }
/*     */     } 
/* 680 */     return arrayOfLink;
/*     */   }
/*     */   
/*     */   void insertChildrenData(int paramInt) {
/* 684 */     if (this.childrenSwitchLinks == null) {
/* 685 */       this.childrenSwitchLinks = new ArrayList(1);
/*     */     }
/* 687 */     this.childrenSwitchLinks.add(paramInt, new ArrayList(1));
/*     */   }
/*     */   
/*     */   void appendChildrenData() {
/* 691 */     if (this.childrenSwitchLinks == null) {
/* 692 */       this.childrenSwitchLinks = new ArrayList(1);
/*     */     }
/* 694 */     this.childrenSwitchLinks.add(new ArrayList(1));
/*     */   }
/*     */   
/*     */   void removeChildrenData(int paramInt) {
/* 698 */     ArrayList arrayList = (ArrayList)this.childrenSwitchLinks.get(paramInt);
/* 699 */     arrayList.clear();
/* 700 */     this.childrenSwitchLinks.remove(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTargetThreads(int paramInt) {
/* 709 */     if (paramInt == 0) {
/* 710 */       return this.targetThreads;
/*     */     }
/* 712 */     System.err.println("getTargetThreads: wrong arguments");
/* 713 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 718 */   TargetsInterface getClosestTargetsInterface(int paramInt) { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void computeTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 726 */     this.localTargetThreads = 0;
/* 727 */     if (paramInt == 0) {
/* 728 */       int i; for (i = 0; i < paramArrayOfCachedTargets.length; i++) {
/* 729 */         if (paramArrayOfCachedTargets[i] != null) {
/* 730 */           this.localTargetThreads |= paramArrayOfCachedTargets[i].computeTargetThreads();
/*     */         }
/*     */       } 
/* 733 */       this.targetThreads = this.localTargetThreads;
/*     */       
/* 735 */       i = this.childTransformLinks.size();
/*     */ 
/*     */ 
/*     */       
/* 739 */       for (byte b = 0; b < i; b++) {
/* 740 */         TargetsInterface targetsInterface; NodeRetained nodeRetained = (NodeRetained)this.childTransformLinks.get(b);
/* 741 */         if (nodeRetained.nodeType == 9) {
/* 742 */           targetsInterface = ((LinkRetained)nodeRetained).sharedGroup;
/*     */         } else {
/*     */           
/* 745 */           targetsInterface = (TargetsInterface)nodeRetained;
/*     */         } 
/* 747 */         if (targetsInterface != null) {
/* 748 */           this.targetThreads |= targetsInterface.getTargetThreads(0);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 754 */       System.err.println("computeTargetsThreads: wrong arguments");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 762 */     if (paramInt == 0) {
/* 763 */       computeTargetThreads(paramInt, paramArrayOfCachedTargets);
/* 764 */       if (this.parentTransformLink != null) {
/* 765 */         TargetsInterface targetsInterface = (TargetsInterface)this.parentTransformLink;
/* 766 */         targetsInterface.propagateTargetThreads(0, this.targetThreads);
/*     */       } 
/*     */     } else {
/*     */       
/* 770 */       System.err.println("updateTargetThreads: wrong arguments");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void propagateTargetThreads(int paramInt1, int paramInt2) {
/* 777 */     if (paramInt1 == 0) {
/*     */ 
/*     */ 
/*     */       
/* 781 */       this.targetThreads |= paramInt2;
/* 782 */       for (byte b = 0; b < this.parents.size(); b++) {
/* 783 */         LinkRetained linkRetained = (LinkRetained)this.parents.elementAt(b);
/* 784 */         if (linkRetained.parentTransformLink != null) {
/* 785 */           TargetsInterface targetsInterface = (TargetsInterface)linkRetained.parentTransformLink;
/*     */           
/* 787 */           targetsInterface.propagateTargetThreads(paramInt1, this.targetThreads);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 791 */       System.err.println("propagateTargetThreads: wrong arguments");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 796 */     if (paramInt == 0) {
/* 797 */       this.j3dCTs = paramArrayOfCachedTargets;
/*     */     } else {
/* 799 */       System.err.println("updateCachedTargets: wrong arguments");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void copyCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets) {
/* 804 */     if (paramInt == 0) {
/* 805 */       int i = this.cachedTargets.length;
/* 806 */       for (byte b = 0; b < i; b++) {
/* 807 */         paramArrayOfCachedTargets[b] = this.cachedTargets[b];
/*     */       }
/*     */     } else {
/* 810 */       System.err.println("copyCachedTargets: wrong arguments");
/*     */     } 
/*     */   }
/*     */   
/*     */   public CachedTargets getCachedTargets(int paramInt1, int paramInt2, int paramInt3) {
/* 815 */     if (paramInt1 == 1) {
/*     */       
/* 817 */       if (paramInt2 < this.switchStates.size()) {
/* 818 */         SwitchState switchState = (SwitchState)this.switchStates.get(paramInt2);
/* 819 */         return switchState.cachedTargets;
/*     */       } 
/* 821 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 825 */     return this.cachedTargets[paramInt2];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetCachedTargets(int paramInt1, CachedTargets[] paramArrayOfCachedTargets, int paramInt2) {
/* 831 */     if (paramInt1 == 1) {
/*     */ 
/*     */       
/* 834 */       if (paramArrayOfCachedTargets.length != this.switchStates.size()) {
/* 835 */         System.err.println("resetCachedTargets: unmatched length!" + paramArrayOfCachedTargets.length + " " + this.switchStates.size());
/*     */         
/* 837 */         System.err.println("  resetCachedTargets: " + this);
/*     */       } 
/* 839 */       for (byte b = 0; b < paramArrayOfCachedTargets.length; b++) {
/* 840 */         SwitchState switchState = (SwitchState)this.switchStates.get(b);
/* 841 */         switchState.cachedTargets = paramArrayOfCachedTargets[b];
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 846 */       this.cachedTargets = paramArrayOfCachedTargets;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getTargetsData(int paramInt1, int paramInt2) {
/* 852 */     if (paramInt1 == 1) {
/* 853 */       return this.switchStates;
/*     */     }
/* 855 */     System.err.println("getTargetsData: wrong arguments");
/* 856 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void childDoSetLive(NodeRetained paramNodeRetained, int paramInt, SetLiveState paramSetLiveState) {
/* 864 */     paramSetLiveState.childSwitchLinks = (ArrayList)this.childrenSwitchLinks.get(paramInt);
/* 865 */     paramSetLiveState.switchStates = this.switchStates;
/*     */     
/* 867 */     if (paramNodeRetained != null)
/* 868 */       paramNodeRetained.setLive(paramSetLiveState); 
/*     */   }
/*     */   
/*     */   void childCheckSetLive(NodeRetained paramNodeRetained, int paramInt, SetLiveState paramSetLiveState) {
/* 872 */     paramSetLiveState.childTransformLinks = this.childTransformLinks;
/* 873 */     paramSetLiveState.parentTransformLink = this;
/* 874 */     paramNodeRetained.setLive(paramSetLiveState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dirtyBoundsCache() {
/* 884 */     if (VirtualUniverse.mc.cacheAutoComputedBounds) {
/* 885 */       this.cachedBounds = null;
/* 886 */       synchronized (this.parents) {
/* 887 */         Enumeration enumeration = this.parents.elements();
/* 888 */         while (enumeration.hasMoreElements()) {
/* 889 */           LinkRetained linkRetained = (LinkRetained)enumeration.nextElement();
/* 890 */           if (linkRetained != null)
/* 891 */             linkRetained.dirtyBoundsCache(); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\SharedGroupRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */