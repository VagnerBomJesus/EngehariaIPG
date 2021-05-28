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
/*     */ class BranchGroupRetained
/*     */   extends GroupRetained
/*     */ {
/*     */   boolean isDirty = false;
/*     */   boolean isNew = false;
/*     */   boolean attachedToLocale = false;
/*     */   
/*  44 */   void setLocale(Locale paramLocale) { this.locale = paramLocale; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   Locale getLocale() { return this.locale; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void detach() {
/*  58 */     dirtyBoundsCache();
/*  59 */     if (this.universe != null) {
/*  60 */       this.universe.resetWaitMCFlag();
/*  61 */       synchronized (this.universe.sceneGraphLock) {
/*  62 */         boolean bool = this.source.isLive();
/*  63 */         if (bool) {
/*  64 */           notifySceneGraphChanged(true);
/*     */         }
/*  66 */         GroupRetained groupRetained = (GroupRetained)this.parent;
/*  67 */         do_detach();
/*  68 */         this.universe.setLiveState.clear();
/*  69 */         if (bool) {
/*  70 */           if (groupRetained == null) {
/*  71 */             this.universe.notifyStructureChangeListeners(false, this.locale, (BranchGroup)this.source);
/*     */           } else {
/*  73 */             this.universe.notifyStructureChangeListeners(false, groupRetained.source, (BranchGroup)this.source);
/*     */           } 
/*     */         }
/*     */       } 
/*  77 */       this.universe.waitForMC();
/*     */     } else {
/*  79 */       do_detach();
/*  80 */       if (this.universe != null) {
/*  81 */         synchronized (this.universe.sceneGraphLock) {
/*  82 */           this.universe.setLiveState.clear();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void do_detach() {
/*  90 */     if (this.attachedToLocale) {
/*  91 */       this.locale.doRemoveBranchGraph((BranchGroup)this.source, null, 0);
/*  92 */     } else if (this.parent != null) {
/*  93 */       GroupRetained groupRetained = (GroupRetained)this.parent;
/*  94 */       groupRetained.doRemoveChild(groupRetained.children.indexOf(this), null, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNodeData(SetLiveState paramSetLiveState) {
/* 102 */     paramSetLiveState.parentBranchGroupPaths = this.branchGroupPaths;
/*     */     
/* 104 */     super.setNodeData(paramSetLiveState);
/*     */     
/* 106 */     if (!this.inSharedGroup) {
/* 107 */       setAuxData(paramSetLiveState, 0, 0);
/*     */     }
/*     */     else {
/*     */       
/* 111 */       for (byte b = 0; b < paramSetLiveState.keys.length; b++) {
/* 112 */         int i = paramSetLiveState.keys[b].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/*     */ 
/*     */         
/* 115 */         if (i >= 0) {
/* 116 */           setAuxData(paramSetLiveState, b, i);
/*     */         }
/*     */         else {
/*     */           
/* 120 */           MasterControl.getCoreLogger().severe("Can't Find matching hashKey in setNodeData.");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void setAuxData(SetLiveState paramSetLiveState, int paramInt1, int paramInt2) {
/* 128 */     super.setAuxData(paramSetLiveState, paramInt1, paramInt2);
/*     */     
/* 130 */     BranchGroupRetained[] arrayOfBranchGroupRetained1 = (BranchGroupRetained[])paramSetLiveState.branchGroupPaths.get(paramInt1);
/*     */     
/* 132 */     BranchGroupRetained[] arrayOfBranchGroupRetained2 = new BranchGroupRetained[arrayOfBranchGroupRetained1.length + 1];
/*     */     
/* 134 */     System.arraycopy(arrayOfBranchGroupRetained1, 0, arrayOfBranchGroupRetained2, 0, arrayOfBranchGroupRetained1.length);
/* 135 */     arrayOfBranchGroupRetained2[arrayOfBranchGroupRetained1.length] = this;
/* 136 */     paramSetLiveState.branchGroupPaths.set(paramInt1, arrayOfBranchGroupRetained2);
/* 137 */     this.branchGroupPaths.add(paramInt2, arrayOfBranchGroupRetained2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeNodeData(SetLiveState paramSetLiveState) {
/* 146 */     if (!this.inSharedGroup || paramSetLiveState.keys.length == this.localToVworld.length) {
/*     */ 
/*     */       
/* 149 */       this.branchGroupPaths = new ArrayList(1);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 154 */       for (int i = paramSetLiveState.keys.length - 1; i >= 0; i--) {
/* 155 */         int j = paramSetLiveState.keys[i].equals(this.localToVworldKeys, 0, this.localToVworldKeys.length);
/* 156 */         if (j >= 0) {
/* 157 */           this.branchGroupPaths.remove(j);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 163 */     super.removeNodeData(paramSetLiveState);
/*     */   }
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 168 */     doSetLive(paramSetLiveState);
/* 169 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void compile() {
/* 176 */     if (this.source.isCompiled() || VirtualUniverse.mc.disableCompile) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     CompileState compileState = new CompileState();
/*     */     
/* 186 */     this.isRoot = true;
/*     */     
/* 188 */     compile(compileState);
/* 189 */     merge(compileState);
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
/*     */   void compile(CompileState paramCompileState) {
/* 207 */     if (this.mergeFlag == 2) {
/* 208 */       paramCompileState.keepTG = true;
/*     */       
/*     */       return;
/*     */     } 
/* 212 */     super.compile(paramCompileState);
/*     */ 
/*     */ 
/*     */     
/* 216 */     this.mergeFlag = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   SceneGraphPath[] pickAll(PickShape paramPickShape) {
/* 221 */     PickInfo[] arrayOfPickInfo = pickAll(1, 1, paramPickShape);
/*     */ 
/*     */     
/* 224 */     if (arrayOfPickInfo == null) {
/* 225 */       return null;
/*     */     }
/*     */     
/* 228 */     SceneGraphPath[] arrayOfSceneGraphPath = new SceneGraphPath[arrayOfPickInfo.length];
/* 229 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/* 230 */       arrayOfSceneGraphPath[b] = arrayOfPickInfo[b].getSceneGraphPath();
/*     */     }
/*     */     
/* 233 */     return arrayOfSceneGraphPath;
/*     */   }
/*     */ 
/*     */   
/*     */   PickInfo[] pickAll(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 238 */     if (this.inSharedGroup) {
/* 239 */       throw new RestrictedAccessException(J3dI18N.getString("BranchGroup9"));
/*     */     }
/*     */     
/* 242 */     GeometryAtom[] arrayOfGeometryAtom = this.locale.universe.geometryStructure.pickAll(this.locale, paramPickShape);
/*     */ 
/*     */     
/* 245 */     return PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   SceneGraphPath[] pickAllSorted(PickShape paramPickShape) {
/* 251 */     PickInfo[] arrayOfPickInfo = pickAllSorted(1, 1, paramPickShape);
/*     */ 
/*     */     
/* 254 */     if (arrayOfPickInfo == null) {
/* 255 */       return null;
/*     */     }
/*     */     
/* 258 */     SceneGraphPath[] arrayOfSceneGraphPath = new SceneGraphPath[arrayOfPickInfo.length];
/* 259 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/* 260 */       arrayOfSceneGraphPath[b] = arrayOfPickInfo[b].getSceneGraphPath();
/*     */     }
/*     */     
/* 263 */     return arrayOfSceneGraphPath;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   PickInfo[] pickAllSorted(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 269 */     if (this.inSharedGroup) {
/* 270 */       throw new RestrictedAccessException(J3dI18N.getString("BranchGroup9"));
/*     */     }
/*     */     
/* 273 */     GeometryAtom[] arrayOfGeometryAtom = this.locale.universe.geometryStructure.pickAll(this.locale, paramPickShape);
/*     */ 
/*     */     
/* 276 */     PickInfo[] arrayOfPickInfo = null;
/*     */ 
/*     */     
/* 279 */     if (arrayOfGeometryAtom == null || arrayOfGeometryAtom.length == 0) {
/* 280 */       return null;
/*     */     }
/*     */     
/* 283 */     if (paramInt1 == 2) {
/*     */       
/* 285 */       paramInt2 |= 0x10;
/* 286 */       arrayOfPickInfo = PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 1);
/*     */       
/* 288 */       if (arrayOfPickInfo != null) {
/* 289 */         PickInfo.sortPickInfoArray(arrayOfPickInfo);
/*     */       }
/*     */     } else {
/*     */       
/* 293 */       PickInfo.sortGeomAtoms(arrayOfGeometryAtom, paramPickShape);
/* 294 */       arrayOfPickInfo = PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 1);
/*     */     } 
/*     */ 
/*     */     
/* 298 */     return arrayOfPickInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   SceneGraphPath pickClosest(PickShape paramPickShape) {
/* 304 */     PickInfo pickInfo = pickClosest(1, 1, paramPickShape);
/*     */ 
/*     */     
/* 307 */     if (pickInfo == null) {
/* 308 */       return null;
/*     */     }
/*     */     
/* 311 */     return pickInfo.getSceneGraphPath();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   PickInfo pickClosest(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 317 */     PickInfo[] arrayOfPickInfo = null;
/*     */     
/* 319 */     arrayOfPickInfo = pickAllSorted(paramInt1, paramInt2, paramPickShape);
/*     */     
/* 321 */     if (arrayOfPickInfo == null) {
/* 322 */       return null;
/*     */     }
/*     */     
/* 325 */     return arrayOfPickInfo[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   SceneGraphPath pickAny(PickShape paramPickShape) {
/* 331 */     PickInfo pickInfo = pickAny(1, 1, paramPickShape);
/*     */ 
/*     */     
/* 334 */     if (pickInfo == null) {
/* 335 */       return null;
/*     */     }
/* 337 */     return pickInfo.getSceneGraphPath();
/*     */   }
/*     */ 
/*     */   
/*     */   PickInfo pickAny(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 342 */     if (this.inSharedGroup) {
/* 343 */       throw new RestrictedAccessException(J3dI18N.getString("BranchGroup9"));
/*     */     }
/*     */     
/* 346 */     GeometryAtom[] arrayOfGeometryAtom = this.locale.universe.geometryStructure.pickAll(this.locale, paramPickShape);
/*     */ 
/*     */     
/* 349 */     PickInfo[] arrayOfPickInfo = PickInfo.pick(this, arrayOfGeometryAtom, paramInt1, paramInt2, paramPickShape, 2);
/*     */ 
/*     */     
/* 352 */     if (arrayOfPickInfo == null) {
/* 353 */       return null;
/*     */     }
/*     */     
/* 356 */     return arrayOfPickInfo[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\BranchGroupRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */