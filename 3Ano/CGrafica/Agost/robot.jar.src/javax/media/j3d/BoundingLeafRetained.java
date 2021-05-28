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
/*     */ class BoundingLeafRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int REGION_CHANGED = 1;
/*  26 */   static final Integer REGION_CHANGED_MESSAGE = new Integer(1);
/*     */ 
/*     */   
/*  29 */   Bounds region = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   Bounds transformedRegion = null;
/*     */ 
/*     */   
/*     */   BoundingLeafRetained mirrorBoundingLeaf;
/*     */ 
/*     */   
/*  41 */   ArrayList users = new ArrayList();
/*     */ 
/*     */   
/*  44 */   int targetThreads = 4224;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   int transformTargetThreads = 4160;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createBoundingLeaf() {
/*  57 */     this.nodeType = 25;
/*  58 */     this.mirrorBoundingLeaf = new BoundingLeafRetained();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initRegion(Bounds paramBounds) {
/*  65 */     if (paramBounds != null) {
/*  66 */       this.region = (Bounds)paramBounds.clone();
/*     */     } else {
/*     */       
/*  69 */       this.region = null;
/*     */     } 
/*  71 */     if (this.staticTransform != null) {
/*  72 */       this.region.transform(this.staticTransform.transform);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setRegion(Bounds paramBounds) {
/*  80 */     initRegion(paramBounds);
/*  81 */     J3dMessage j3dMessage = new J3dMessage();
/*  82 */     j3dMessage.threads = this.mirrorBoundingLeaf.targetThreads;
/*  83 */     j3dMessage.type = 23;
/*  84 */     j3dMessage.universe = this.universe;
/*  85 */     j3dMessage.args[0] = this;
/*  86 */     j3dMessage.args[1] = REGION_CHANGED_MESSAGE;
/*  87 */     if (paramBounds != null) {
/*  88 */       j3dMessage.args[2] = (Bounds)paramBounds.clone();
/*     */     } else {
/*  90 */       j3dMessage.args[2] = null;
/*     */     } 
/*  92 */     j3dMessage.args[3] = this.mirrorBoundingLeaf.users.toArray();
/*  93 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Bounds getRegion() {
/* 101 */     Bounds bounds = null;
/* 102 */     if (this.region != null) {
/* 103 */       bounds = (Bounds)this.region.clone();
/* 104 */       if (this.staticTransform != null) {
/* 105 */         Transform3D transform3D = this.staticTransform.getInvTransform();
/* 106 */         bounds.transform(transform3D);
/*     */       } 
/*     */     } 
/* 109 */     return bounds;
/*     */   }
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 114 */     doSetLive(paramSetLiveState);
/*     */     
/* 116 */     if (this.inBackgroundGroup) {
/* 117 */       throw new IllegalSceneGraphException(J3dI18N.getString("BoundingLeafRetained0"));
/*     */     }
/*     */ 
/*     */     
/* 121 */     if (this.inSharedGroup) {
/* 122 */       throw new IllegalSharingException(J3dI18N.getString("BoundingLeafRetained1"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 127 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 128 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorBoundingLeaf, 5);
/*     */       
/* 130 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/* 132 */     this.mirrorBoundingLeaf.localToVworld = new Transform3D[1][];
/* 133 */     this.mirrorBoundingLeaf.localToVworldIndex = new int[1][];
/* 134 */     this.mirrorBoundingLeaf.localToVworld[0] = this.localToVworld[0];
/* 135 */     this.mirrorBoundingLeaf.localToVworldIndex[0] = this.localToVworldIndex[0];
/* 136 */     this.mirrorBoundingLeaf.parent = this.parent;
/* 137 */     if (this.region != null) {
/* 138 */       this.mirrorBoundingLeaf.region = (Bounds)this.region.clone();
/* 139 */       this.mirrorBoundingLeaf.transformedRegion = (Bounds)this.region.clone();
/* 140 */       this.mirrorBoundingLeaf.transformedRegion.transform(this.mirrorBoundingLeaf.getCurrentLocalToVworld());
/*     */     } else {
/*     */       
/* 143 */       this.mirrorBoundingLeaf.region = null;
/* 144 */       this.mirrorBoundingLeaf.transformedRegion = null;
/*     */     } 
/*     */     
/* 147 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 149 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorBoundingLeaf, 5);
/*     */     }
/*     */     
/* 152 */     this.mirrorBoundingLeaf.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/* 153 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/* 162 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 163 */     Bounds bounds = (Bounds)paramArrayOfObject[2];
/*     */ 
/*     */     
/* 166 */     if ((i & true) != 0) {
/* 167 */       this.mirrorBoundingLeaf.region = bounds;
/* 168 */       if (bounds != null) {
/* 169 */         this.mirrorBoundingLeaf.transformedRegion = (Bounds)bounds.clone();
/* 170 */         Transform3D transform3D = this.mirrorBoundingLeaf.getCurrentLocalToVworld();
/* 171 */         this.mirrorBoundingLeaf.transformedRegion.transform(bounds, transform3D);
/*     */       } else {
/*     */         
/* 174 */         this.mirrorBoundingLeaf.transformedRegion = null;
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
/*     */   void addUser(LeafRetained paramLeafRetained) {
/* 187 */     this.users.add(paramLeafRetained);
/* 188 */     if (paramLeafRetained.nodeType == 1 || paramLeafRetained.nodeType == 2 || paramLeafRetained.nodeType == 27 || paramLeafRetained instanceof FogRetained || paramLeafRetained instanceof LightRetained) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 193 */       this.transformTargetThreads |= 0x80;
/*     */     }
/* 195 */     else if (paramLeafRetained instanceof BehaviorRetained) {
/* 196 */       this.transformTargetThreads |= 0x100;
/* 197 */       this.targetThreads |= 0x100;
/*     */     }
/* 199 */     else if (paramLeafRetained instanceof SoundRetained || paramLeafRetained.nodeType == 15) {
/*     */       
/* 201 */       this.transformTargetThreads |= 0x200;
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
/*     */   void removeUser(LeafRetained paramLeafRetained) {
/* 214 */     this.users.remove(this.users.indexOf(paramLeafRetained));
/*     */     
/* 216 */     this.transformTargetThreads = 4096;
/* 217 */     this.targetThreads = 4224;
/*     */ 
/*     */     
/* 220 */     for (byte b = 0; b < this.users.size(); b++) {
/* 221 */       LeafRetained leafRetained = (LeafRetained)this.users.get(b);
/* 222 */       if (leafRetained.nodeType == 1 || leafRetained.nodeType == 2 || leafRetained.nodeType == 27 || leafRetained instanceof FogRetained || leafRetained instanceof LightRetained) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 227 */         this.transformTargetThreads |= 0x80;
/*     */       }
/* 229 */       else if (leafRetained.nodeType == 17) {
/* 230 */         this.transformTargetThreads |= 0x100;
/* 231 */         this.targetThreads |= 0x100;
/*     */       }
/* 233 */       else if (leafRetained instanceof SoundRetained || leafRetained.nodeType == 15) {
/*     */         
/* 235 */         this.transformTargetThreads |= 0x200;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateImmediateTransformChange() {
/* 244 */     Transform3D transform3D = getCurrentLocalToVworld();
/* 245 */     if (this.region != null) {
/* 246 */       this.transformedRegion.transform(this.region, transform3D);
/*     */     }
/*     */   }
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 251 */     clearLive();
/* 252 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 254 */       paramSetLiveState.switchTargets[0].addNode(this.mirrorBoundingLeaf, 5);
/*     */     }
/*     */     
/* 257 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 258 */       paramSetLiveState.transformTargets[0].addNode(this.mirrorBoundingLeaf, 5);
/*     */       
/* 260 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */   }
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 265 */     super.mergeTransform(paramTransformGroupRetained);
/* 266 */     this.region.transform(paramTransformGroupRetained.transform);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\BoundingLeafRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */