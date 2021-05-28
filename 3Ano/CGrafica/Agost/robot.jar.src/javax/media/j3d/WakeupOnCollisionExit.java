/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WakeupOnCollisionExit
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_GS_LIST = 0;
/*     */   static final int COLLIDEEXIT_IN_BS_LIST = 1;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*     */   public static final int USE_GEOMETRY = 10;
/*     */   public static final int USE_BOUNDS = 11;
/*     */   int accuracyMode;
/*     */   NodeRetained armingNode;
/*     */   Bounds vwcBounds;
/*  53 */   Bounds localBounds = null;
/*     */ 
/*     */ 
/*     */   
/*  57 */   BoundingLeafRetained boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   UnorderList geometryAtoms = null;
/*     */ 
/*     */   
/*     */   int nodeType;
/*     */   
/*  68 */   SceneGraphPath armingPath = null;
/*  69 */   Bounds armingBounds = null;
/*     */ 
/*     */ 
/*     */   
/*  73 */   SceneGraphPath collidingPath = null;
/*  74 */   Bounds collidingBounds = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public WakeupOnCollisionExit(SceneGraphPath paramSceneGraphPath) { this(paramSceneGraphPath, 11); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public WakeupOnCollisionExit(SceneGraphPath paramSceneGraphPath, int paramInt) { this(new SceneGraphPath(paramSceneGraphPath), paramInt, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public WakeupOnCollisionExit(Node paramNode) { this(paramNode, 11); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public WakeupOnCollisionExit(Node paramNode, int paramInt) { this(new SceneGraphPath(null, paramNode), paramInt, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public WakeupOnCollisionExit(Bounds paramBounds) { this(null, 11, (Bounds)paramBounds.clone()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WakeupOnCollisionExit(SceneGraphPath paramSceneGraphPath, int paramInt, Bounds paramBounds) {
/* 155 */     if (paramSceneGraphPath != null) {
/* 156 */       this.armingNode = (NodeRetained)(paramSceneGraphPath.getObject()).retained;
/* 157 */       this.nodeType = WakeupOnCollisionEntry.getNodeType(this.armingNode, paramSceneGraphPath, "WakeupOnCollisionExit");
/*     */       
/* 159 */       this.armingPath = paramSceneGraphPath;
/* 160 */       WakeupOnCollisionEntry.validateSpeedHint(paramInt, "WakeupOnCollisionExit4");
/*     */     } else {
/*     */       
/* 163 */       this.armingBounds = paramBounds;
/* 164 */       this.nodeType = 0;
/*     */     } 
/* 166 */     this.accuracyMode = paramInt;
/* 167 */     WakeupIndexedList.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public SceneGraphPath getArmingPath() { return (this.armingPath != null) ? new SceneGraphPath(this.armingPath) : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public Bounds getArmingBounds() { return (this.armingBounds != null) ? (Bounds)this.armingBounds.clone() : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath getTriggeringPath() {
/* 197 */     if (this.behav == null) {
/* 198 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionExit5"));
/*     */     }
/*     */     
/* 201 */     synchronized (this.behav) {
/* 202 */       if (!this.behav.inCallback) {
/* 203 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionExit5"));
/*     */       }
/*     */     } 
/*     */     
/* 207 */     return (this.collidingPath != null) ? new SceneGraphPath(this.collidingPath) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getTriggeringBounds() {
/* 218 */     if (this.behav == null) {
/* 219 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionExit6"));
/*     */     }
/*     */     
/* 222 */     synchronized (this.behav) {
/* 223 */       if (!this.behav.inCallback) {
/* 224 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionExit6"));
/*     */       }
/*     */     } 
/*     */     
/* 228 */     return (this.collidingBounds != null) ? (Bounds)this.collidingBounds.clone() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/*     */     MorphRetained morphRetained;
/*     */     Shape3DRetained shape3DRetained;
/* 238 */     switch (this.nodeType) {
/*     */       case 11:
/*     */       case 28:
/* 241 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 244 */         if (this.geometryAtoms == null) {
/* 245 */           this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */         }
/* 247 */         shape3DRetained = (Shape3DRetained)this.armingNode;
/* 248 */         this.geometryAtoms.add(Shape3DRetained.getGeomAtom(shape3DRetained.getMirrorShape(this.armingPath)));
/*     */         break;
/*     */       case 10:
/* 251 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 254 */         if (this.geometryAtoms == null) {
/* 255 */           this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */         }
/* 257 */         morphRetained = (MorphRetained)this.armingNode;
/* 258 */         this.geometryAtoms.add(Shape3DRetained.getGeomAtom(morphRetained.getMirrorShape(this.armingPath)));
/*     */         break;
/*     */       case 25:
/* 261 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 264 */         this.boundingLeaf = ((BoundingLeafRetained)this.armingNode).mirrorBoundingLeaf;
/*     */         break;
/*     */       case 0:
/* 267 */         this.vwcBounds = (Bounds)this.armingBounds.clone();
/* 268 */         this.armingNode = this.behav;
/*     */         break;
/*     */       case 23:
/* 271 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 274 */         if (this.accuracyMode == 10) {
/* 275 */           if (this.geometryAtoms == null) {
/* 276 */             this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */           }
/* 278 */           ((GroupRetained)this.armingNode).searchGeometryAtoms(this.geometryAtoms);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 284 */     this.behav.universe.geometryStructure.addWakeupOnCollision(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 293 */     this.vwcBounds = null;
/* 294 */     if (this.geometryAtoms != null) {
/* 295 */       this.geometryAtoms.clear();
/*     */     }
/* 297 */     this.boundingLeaf = null;
/* 298 */     this.behav.universe.geometryStructure.removeWakeupOnCollision(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setTarget(BHLeafInterface paramBHLeafInterface) {
/*     */     Bounds bounds;
/*     */     SceneGraphPath sceneGraphPath;
/* 308 */     if (paramBHLeafInterface instanceof GeometryAtom) {
/*     */       
/* 310 */       GeometryAtom geometryAtom = (GeometryAtom)paramBHLeafInterface;
/* 311 */       Shape3DRetained shape3DRetained = geometryAtom.source;
/* 312 */       sceneGraphPath = WakeupOnCollisionEntry.getSceneGraphPath(shape3DRetained.sourceNode, shape3DRetained.key, shape3DRetained.getCurrentLocalToVworld(0));
/*     */ 
/*     */ 
/*     */       
/* 316 */       bounds = WakeupOnCollisionEntry.getTriggeringBounds(shape3DRetained);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 322 */       GroupRetained groupRetained = (GroupRetained)paramBHLeafInterface;
/* 323 */       sceneGraphPath = WakeupOnCollisionEntry.getSceneGraphPath(groupRetained);
/* 324 */       bounds = WakeupOnCollisionEntry.getTriggeringBounds(groupRetained);
/*     */     } 
/*     */     
/* 327 */     if (sceneGraphPath != null) {
/*     */ 
/*     */       
/* 330 */       this.collidingPath = sceneGraphPath;
/* 331 */       this.collidingBounds = bounds;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateCollisionBounds(boolean paramBoolean) {
/* 338 */     if (this.nodeType == 23) {
/* 339 */       GroupRetained groupRetained = (GroupRetained)this.armingNode;
/* 340 */       if (groupRetained.collisionBound != null) {
/* 341 */         this.vwcBounds = (Bounds)groupRetained.collisionBound.clone();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 346 */         this.vwcBounds = groupRetained.getEffectiveBounds();
/*     */       } 
/* 348 */       groupRetained.transformBounds(this.armingPath, this.vwcBounds);
/* 349 */     } else if (this.nodeType == 0) {
/* 350 */       this.vwcBounds.transform(this.armingBounds, this.behav.getCurrentLocalToVworld());
/*     */     } 
/*     */     
/* 353 */     if (paramBoolean && this.nodeType == 23 && this.accuracyMode == 10) {
/*     */ 
/*     */       
/* 356 */       this.geometryAtoms.clear();
/* 357 */       ((GroupRetained)this.armingNode).searchGeometryAtoms(this.geometryAtoms);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void setTriggered() {
/* 363 */     if (this.collidingPath != null)
/* 364 */       super.setTriggered(); 
/*     */   }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\WakeupOnCollisionExit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */