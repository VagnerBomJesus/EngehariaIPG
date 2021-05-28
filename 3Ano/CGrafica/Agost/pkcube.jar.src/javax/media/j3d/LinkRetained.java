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
/*     */ class LinkRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   SharedGroupRetained sharedGroup;
/*  26 */   static String plus = "+";
/*     */   
/*     */   LinkRetained() {
/*  29 */     this.visited = false;
/*     */ 
/*     */     
/*  32 */     this.nodeType = 9;
/*  33 */     this.localBounds = new BoundingBox();
/*  34 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  35 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean visited;
/*     */ 
/*     */ 
/*     */   
/*     */   void setSharedGroup(SharedGroup paramSharedGroup) {
/*  45 */     HashKey[] arrayOfHashKey = null;
/*  46 */     boolean bool = false;
/*     */     
/*  48 */     if (this.source.isLive()) {
/*     */       
/*  50 */       if (paramSharedGroup != null) {
/*  51 */         synchronized (this.universe.sceneGraphLock) {
/*     */           
/*  53 */           for (NodeRetained nodeRetained = this.parent; nodeRetained != null; nodeRetained = nodeRetained.parent) {
/*  54 */             if (nodeRetained == (NodeRetained)paramSharedGroup.retained) {
/*  55 */               bool = true;
/*  56 */               throw new SceneGraphCycleException(J3dI18N.getString("LinkRetained1"));
/*     */             } 
/*     */           } 
/*     */         } 
/*  60 */         if (bool) {
/*     */           return;
/*     */         }
/*     */       } 
/*  64 */       arrayOfHashKey = getNewKeys(this.locale.nodeId, this.localToVworldKeys);
/*     */       
/*  66 */       if (this.sharedGroup != null) {
/*  67 */         ((GroupRetained)this.parent).checkClearLive(this.sharedGroup, arrayOfHashKey, true, null, 0, 0, this);
/*     */ 
/*     */         
/*  70 */         this.sharedGroup.parents.removeElement(this);
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     if (paramSharedGroup != null) {
/*  75 */       this.sharedGroup = (SharedGroupRetained)paramSharedGroup.retained;
/*     */     } else {
/*     */       
/*  78 */       this.sharedGroup = null;
/*     */     } 
/*     */     
/*  81 */     if (this.source.isLive() && paramSharedGroup != null) {
/*     */       
/*  83 */       this.sharedGroup.parents.addElement(this);
/*  84 */       this.visited = true;
/*     */       try {
/*  86 */         int i = ((GroupRetained)this.parent).indexOfChild((Node)this.sharedGroup.source);
/*  87 */         ((GroupRetained)this.parent).checkSetLive(this.sharedGroup, i, arrayOfHashKey, true, null, 0, this);
/*     */       
/*     */       }
/*  90 */       catch (SceneGraphCycleException sceneGraphCycleException) {
/*  91 */         throw sceneGraphCycleException;
/*     */       } finally {
/*  93 */         this.visited = false;
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
/* 104 */   SharedGroup getSharedGroup() { return (this.sharedGroup != null) ? (SharedGroup)this.sharedGroup.source : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void computeCombineBounds(Bounds paramBounds) {
/* 110 */     if (this.boundsAutoCompute) {
/* 111 */       this.sharedGroup.computeCombineBounds(paramBounds);
/*     */     } else {
/*     */       
/* 114 */       synchronized (this.localBounds) {
/* 115 */         paramBounds.combine(this.localBounds);
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
/* 126 */   Bounds getBounds() { return this.boundsAutoCompute ? (Bounds)this.sharedGroup.getBounds().clone() : super.getBounds(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 137 */     doSetLive(paramSetLiveState);
/*     */     
/* 139 */     if (this.inBackgroundGroup) {
/* 140 */       throw new IllegalSceneGraphException(J3dI18N.getString("LinkRetained0"));
/*     */     }
/*     */ 
/*     */     
/* 144 */     if (this.nodeId == null) {
/* 145 */       this.nodeId = this.universe.getNodeId();
/*     */     }
/*     */     
/* 148 */     if (this.sharedGroup != null) {
/* 149 */       this.sharedGroup.parents.addElement(this);
/* 150 */       HashKey[] arrayOfHashKey1 = getNewKeys(paramSetLiveState.locale.nodeId, paramSetLiveState.keys);
/* 151 */       HashKey[] arrayOfHashKey2 = paramSetLiveState.keys;
/* 152 */       paramSetLiveState.keys = arrayOfHashKey1;
/* 153 */       paramSetLiveState.inSharedGroup = true;
/* 154 */       if (this.visited) {
/* 155 */         throw new SceneGraphCycleException(J3dI18N.getString("LinkRetained1"));
/*     */       }
/* 157 */       this.visited = true;
/*     */       try {
/* 159 */         this.sharedGroup.setLive(paramSetLiveState);
/* 160 */       } catch (SceneGraphCycleException sceneGraphCycleException) {
/* 161 */         throw sceneGraphCycleException;
/*     */       } finally {
/* 163 */         this.visited = false;
/*     */       } 
/*     */       
/* 166 */       paramSetLiveState.inSharedGroup = this.inSharedGroup;
/* 167 */       paramSetLiveState.keys = arrayOfHashKey2;
/*     */       
/* 169 */       this.localBounds.setWithLock(this.sharedGroup.localBounds);
/*     */     } 
/*     */     
/* 172 */     markAsLive();
/*     */   }
/*     */ 
/*     */   
/*     */   void setNodeData(SetLiveState paramSetLiveState) {
/* 177 */     super.setNodeData(paramSetLiveState);
/*     */ 
/*     */     
/* 180 */     if (paramSetLiveState.childTransformLinks != null)
/*     */     {
/* 182 */       synchronized (paramSetLiveState.childTransformLinks) {
/* 183 */         if (!this.inSharedGroup || !paramSetLiveState.childTransformLinks.contains(this)) {
/* 184 */           paramSetLiveState.childTransformLinks.add(this);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 190 */     if (paramSetLiveState.childSwitchLinks != null && (
/* 191 */       !this.inSharedGroup || !paramSetLiveState.childSwitchLinks.contains(this)))
/*     */     {
/*     */       
/* 194 */       paramSetLiveState.childSwitchLinks.add(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void recombineAbove() {
/* 201 */     this.localBounds.setWithLock(this.sharedGroup.localBounds);
/* 202 */     this.parent.recombineAbove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 210 */     if (this.sharedGroup != null) {
/* 211 */       HashKey[] arrayOfHashKey1 = getNewKeys(paramSetLiveState.locale.nodeId, paramSetLiveState.keys);
/* 212 */       super.clearLive(paramSetLiveState);
/* 213 */       HashKey[] arrayOfHashKey2 = paramSetLiveState.keys;
/* 214 */       paramSetLiveState.keys = arrayOfHashKey1;
/* 215 */       paramSetLiveState.inSharedGroup = true;
/* 216 */       this.sharedGroup.parents.removeElement(this);
/* 217 */       this.sharedGroup.clearLive(paramSetLiveState);
/* 218 */       paramSetLiveState.inSharedGroup = this.inSharedGroup;
/* 219 */       paramSetLiveState.keys = arrayOfHashKey2;
/*     */     } else {
/* 221 */       super.clearLive(paramSetLiveState);
/*     */     } 
/*     */   }
/*     */   
/*     */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 226 */     if (this.refCount <= 0) {
/*     */ 
/*     */       
/* 229 */       if (this.parentTransformLink != null) {
/*     */         ArrayList arrayList;
/* 231 */         if (this.parentTransformLink instanceof TransformGroupRetained) {
/* 232 */           arrayList = ((TransformGroupRetained)this.parentTransformLink).childTransformLinks;
/*     */         } else {
/*     */           
/* 235 */           arrayList = ((SharedGroupRetained)this.parentTransformLink).childTransformLinks;
/*     */         } 
/*     */         
/* 238 */         synchronized (arrayList) {
/* 239 */           arrayList.remove(this);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 244 */       if (this.parentSwitchLink != null)
/*     */       {
/* 246 */         for (byte b = 0; b < this.parentSwitchLink.childrenSwitchLinks.size(); b++) {
/* 247 */           ArrayList arrayList = (ArrayList)this.parentSwitchLink.childrenSwitchLinks.get(b);
/*     */           
/* 249 */           if (arrayList.contains(this)) {
/* 250 */             arrayList.remove(this);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 256 */     super.removeNodeData(paramSetLiveState);
/*     */   }
/*     */ 
/*     */   
/*     */   void updatePickable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 261 */     super.updatePickable(paramArrayOfHashKey, paramArrayOfBoolean);
/*     */     
/* 263 */     if (this.sharedGroup != null) {
/* 264 */       HashKey[] arrayOfHashKey = getNewKeys(this.locale.nodeId, paramArrayOfHashKey);
/* 265 */       this.sharedGroup.updatePickable(arrayOfHashKey, paramArrayOfBoolean);
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateCollidable(HashKey[] paramArrayOfHashKey, boolean[] paramArrayOfBoolean) {
/* 270 */     super.updateCollidable(paramArrayOfHashKey, paramArrayOfBoolean);
/*     */     
/* 272 */     if (this.sharedGroup != null) {
/* 273 */       HashKey[] arrayOfHashKey = getNewKeys(this.locale.nodeId, paramArrayOfHashKey);
/* 274 */       this.sharedGroup.updateCollidable(arrayOfHashKey, paramArrayOfBoolean);
/*     */     } 
/*     */   }
/*     */   
/*     */   void setBoundsAutoCompute(boolean paramBoolean) {
/* 279 */     super.setBoundsAutoCompute(paramBoolean);
/* 280 */     if (!paramBoolean) {
/* 281 */       this.localBounds = getBounds();
/*     */     }
/*     */   }
/*     */   
/*     */   void setCompiled() {
/* 286 */     super.setCompiled();
/* 287 */     if (this.sharedGroup != null) {
/* 288 */       this.sharedGroup.setCompiled();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void compile(CompileState paramCompileState) {
/* 294 */     super.compile(paramCompileState);
/*     */ 
/*     */     
/* 297 */     paramCompileState.keepTG = true;
/*     */ 
/*     */     
/* 300 */     this.mergeFlag = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   HashKey[] getNewKeys(String paramString, HashKey[] paramArrayOfHashKey) {
/*     */     HashKey[] arrayOfHashKey;
/* 310 */     if (!this.inSharedGroup) {
/* 311 */       arrayOfHashKey = new HashKey[1];
/* 312 */       arrayOfHashKey[0] = new HashKey(paramString);
/* 313 */       arrayOfHashKey[0].append(plus + this.nodeId);
/*     */     } else {
/*     */       
/* 316 */       arrayOfHashKey = new HashKey[paramArrayOfHashKey.length];
/* 317 */       for (int i = paramArrayOfHashKey.length - 1; i >= 0; i--) {
/* 318 */         arrayOfHashKey[i] = new HashKey(paramArrayOfHashKey[i].toString() + plus + this.nodeId);
/*     */       }
/*     */     } 
/*     */     
/* 322 */     return arrayOfHashKey;
/*     */   }
/*     */   
/*     */   void searchGeometryAtoms(UnorderList paramUnorderList) {
/* 326 */     if (this.sharedGroup != null)
/* 327 */       this.sharedGroup.searchGeometryAtoms(paramUnorderList); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\LinkRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */