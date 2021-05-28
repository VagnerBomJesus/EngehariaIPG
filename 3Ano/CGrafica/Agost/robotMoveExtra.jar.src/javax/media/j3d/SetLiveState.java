/*     */ package javax.media.j3d;class SetLiveState { VirtualUniverse universe; Locale locale; Transform3D[][] currentTransforms; int[][] currentTransformsIndex; HashKey[] keys;
/*     */   boolean inSharedGroup;
/*     */   boolean inBackgroundGroup;
/*     */   boolean inViewSpecificGroup;
/*     */   ArrayList nodeList;
/*     */   ArrayList viewScopedNodeList;
/*     */   ArrayList scopedNodesViewList;
/*     */   int notifyThreads;
/*     */   Targets[] transformTargets;
/*     */   int[] transformLevels;
/*     */   ArrayList lights;
/*     */   ArrayList fogs;
/*     */   ArrayList modelClips;
/*     */   ArrayList altAppearances;
/*     */   ArrayList viewLists;
/*     */   ArrayList changedViewGroup;
/*     */   ArrayList changedViewList;
/*     */   int[] keyList;
/*     */   ArrayList orderedPaths;
/*     */   ArrayList ogList;
/*     */   ArrayList ogChildIdList;
/*     */   
/*     */   SetLiveState(VirtualUniverse paramVirtualUniverse) {
/*  24 */     this.universe = null;
/*     */ 
/*     */     
/*  27 */     this.locale = null;
/*     */ 
/*     */     
/*  30 */     this.currentTransforms = new Transform3D[1][];
/*  31 */     this.currentTransformsIndex = new int[1][];
/*     */ 
/*     */     
/*  34 */     this.keys = null;
/*     */ 
/*     */     
/*  37 */     this.inSharedGroup = false;
/*  38 */     this.inBackgroundGroup = false;
/*  39 */     this.inViewSpecificGroup = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     this.nodeList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.viewScopedNodeList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.scopedNodesViewList = null;
/*     */ 
/*     */     
/*  62 */     this.notifyThreads = 0;
/*     */ 
/*     */     
/*  65 */     this.transformTargets = null;
/*     */ 
/*     */     
/*  68 */     this.transformLevels = new int[] { -1 };
/*     */ 
/*     */     
/*  71 */     this.lights = null;
/*     */ 
/*     */     
/*  74 */     this.fogs = null;
/*     */ 
/*     */     
/*  77 */     this.modelClips = null;
/*     */ 
/*     */     
/*  80 */     this.altAppearances = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     this.viewLists = null;
/*  88 */     this.changedViewGroup = null;
/*  89 */     this.changedViewList = null;
/*  90 */     this.keyList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.orderedPaths = null;
/*     */     
/*  98 */     this.ogList = new ArrayList(5);
/*  99 */     this.ogChildIdList = new ArrayList(5);
/* 100 */     this.ogOrderedIdList = new ArrayList(5);
/*     */     
/* 102 */     this.ogCIOList = new ArrayList(5);
/*     */     
/* 104 */     this.ogCIOTableList = new ArrayList(5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     this.branchGroupPaths = null;
/* 114 */     this.parentBranchGroupPaths = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     this.pickable = new boolean[] { true };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     this.collidable = new boolean[] { true };
/*     */ 
/*     */ 
/*     */     
/* 132 */     this.refCount = 1;
/*     */ 
/*     */     
/* 135 */     this.geometryBackground = null;
/*     */ 
/*     */     
/* 138 */     this.behaviorNodes = new ArrayList(1);
/*     */ 
/*     */ 
/*     */     
/* 142 */     this.childTransformLinks = null;
/*     */ 
/*     */     
/* 145 */     this.parentTransformLink = null;
/*     */ 
/*     */ 
/*     */     
/* 149 */     this.switchLevels = new int[] { -1 };
/*     */ 
/*     */     
/* 152 */     this.closestSwitchParents = new SwitchRetained[] { null };
/*     */ 
/*     */     
/* 155 */     this.closestSwitchIndices = new int[] { -1 };
/*     */ 
/*     */     
/* 158 */     this.switchTargets = null;
/*     */ 
/*     */ 
/*     */     
/* 162 */     this.childSwitchLinks = null;
/*     */ 
/*     */     
/* 165 */     this.parentSwitchLink = null;
/*     */     
/* 167 */     this.lastSharedGroup = null;
/*     */     
/* 169 */     this.traverseFlags = 0;
/*     */ 
/*     */     
/* 172 */     this.localToVworld = (Transform3D[][])null;
/* 173 */     this.localToVworldIndex = (int[][])null;
/* 174 */     this.localToVworldKeys = null;
/*     */ 
/*     */ 
/*     */     
/* 178 */     this.hashkeyIndex = null;
/*     */     
/* 180 */     this.switchStates = null;
/*     */ 
/*     */     
/* 183 */     this.universe = paramVirtualUniverse;
/*     */   }
/*     */   ArrayList ogOrderedIdList; ArrayList ogCIOList; ArrayList ogCIOTableList; ArrayList branchGroupPaths; ArrayList parentBranchGroupPaths; boolean[] pickable; boolean[] collidable; int refCount; BackgroundRetained geometryBackground; ArrayList behaviorNodes; ArrayList childTransformLinks; GroupRetained parentTransformLink; int[] switchLevels; SwitchRetained[] closestSwitchParents; int[] closestSwitchIndices; Targets[] switchTargets; ArrayList childSwitchLinks; GroupRetained parentSwitchLink; SharedGroupRetained lastSharedGroup; int traverseFlags; Transform3D[][] localToVworld; int[][] localToVworldIndex; HashKey[] localToVworldKeys; int[] hashkeyIndex; ArrayList switchStates;
/*     */   
/*     */   void reset(Locale paramLocale) {
/* 188 */     this.locale = paramLocale;
/* 189 */     clear();
/*     */   }
/*     */   
/*     */   void clear() {
/* 193 */     this.inSharedGroup = false;
/* 194 */     this.inBackgroundGroup = false;
/* 195 */     this.inViewSpecificGroup = false;
/* 196 */     this.nodeList.clear();
/* 197 */     this.viewScopedNodeList = null;
/* 198 */     this.scopedNodesViewList = null;
/*     */     
/* 200 */     this.notifyThreads = 0;
/* 201 */     this.transformTargets = null;
/* 202 */     this.lights = null;
/* 203 */     this.fogs = null;
/* 204 */     this.modelClips = null;
/* 205 */     this.altAppearances = null;
/* 206 */     this.viewLists = null;
/* 207 */     this.changedViewGroup = null;
/* 208 */     this.changedViewList = null;
/* 209 */     this.keyList = null;
/*     */     
/* 211 */     this.behaviorNodes.clear();
/* 212 */     this.traverseFlags = 0;
/*     */     
/* 214 */     this.ogList.clear();
/* 215 */     this.ogChildIdList.clear();
/* 216 */     this.ogOrderedIdList.clear();
/* 217 */     this.ogCIOList.clear();
/* 218 */     this.ogCIOTableList.clear();
/*     */     
/* 220 */     this.pickable = new boolean[] { true };
/* 221 */     this.collidable = new boolean[] { true };
/* 222 */     this.refCount = 1;
/* 223 */     this.geometryBackground = null;
/* 224 */     this.transformLevels = new int[] { -1 };
/* 225 */     this.childTransformLinks = null;
/* 226 */     this.parentTransformLink = null;
/*     */     
/* 228 */     this.switchTargets = null;
/* 229 */     this.switchLevels = new int[] { -1 };
/* 230 */     this.switchStates = null;
/* 231 */     this.closestSwitchIndices = new int[] { -1 };
/* 232 */     this.closestSwitchParents = new SwitchRetained[] { null };
/* 233 */     this.childSwitchLinks = null;
/* 234 */     this.parentSwitchLink = null;
/*     */     
/* 236 */     this.lastSharedGroup = null;
/*     */     
/* 238 */     this.keys = null;
/* 239 */     this.currentTransforms = new Transform3D[1][];
/* 240 */     this.currentTransformsIndex = new int[1][];
/*     */     
/* 242 */     this.localToVworld = (Transform3D[][])null;
/* 243 */     this.localToVworldIndex = (int[][])null;
/* 244 */     this.localToVworldKeys = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     this.hashkeyIndex = null;
/*     */ 
/*     */     
/* 253 */     this.parentBranchGroupPaths = null;
/* 254 */     this.branchGroupPaths = null;
/* 255 */     this.orderedPaths = null;
/*     */   } }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SetLiveState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */