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
/*     */ public final class WakeupOnCollisionMovement
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_GS_LIST = 0;
/*     */   static final int COLLIDEMOVE_IN_BS_LIST = 1;
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
/*     */   
/*  58 */   Bounds lastSrcBounds = null;
/*  59 */   Bounds lastDstBounds = null;
/*     */ 
/*     */   
/*     */   boolean duplicateEvent = false;
/*     */   
/*  64 */   BoundingLeafRetained boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   UnorderList geometryAtoms = null;
/*     */ 
/*     */   
/*     */   int nodeType;
/*     */   
/*  75 */   SceneGraphPath armingPath = null;
/*  76 */   Bounds armingBounds = null;
/*     */ 
/*     */ 
/*     */   
/*  80 */   SceneGraphPath collidingPath = null;
/*  81 */   Bounds collidingBounds = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public WakeupOnCollisionMovement(SceneGraphPath paramSceneGraphPath) { this(paramSceneGraphPath, 11); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public WakeupOnCollisionMovement(SceneGraphPath paramSceneGraphPath, int paramInt) { this(new SceneGraphPath(paramSceneGraphPath), paramInt, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public WakeupOnCollisionMovement(Node paramNode) { this(paramNode, 11); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public WakeupOnCollisionMovement(Node paramNode, int paramInt) { this(new SceneGraphPath(null, paramNode), paramInt, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public WakeupOnCollisionMovement(Bounds paramBounds) { this(null, 11, (Bounds)paramBounds.clone()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WakeupOnCollisionMovement(SceneGraphPath paramSceneGraphPath, int paramInt, Bounds paramBounds) {
/* 163 */     if (paramSceneGraphPath != null) {
/* 164 */       this.armingNode = (NodeRetained)(paramSceneGraphPath.getObject()).retained;
/* 165 */       this.nodeType = WakeupOnCollisionEntry.getNodeType(this.armingNode, paramSceneGraphPath, "WakeupOnCollisionMovement");
/*     */       
/* 167 */       this.armingPath = paramSceneGraphPath;
/* 168 */       WakeupOnCollisionEntry.validateSpeedHint(paramInt, "WakeupOnCollisionMovement4");
/*     */     } else {
/*     */       
/* 171 */       this.armingBounds = paramBounds;
/* 172 */       this.nodeType = 0;
/*     */     } 
/* 174 */     this.accuracyMode = paramInt;
/* 175 */     WakeupIndexedList.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public SceneGraphPath getArmingPath() { return (this.armingPath != null) ? new SceneGraphPath(this.armingPath) : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public Bounds getArmingBounds() { return (this.armingBounds != null) ? (Bounds)this.armingBounds.clone() : null; }
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
/* 205 */     if (this.behav == null) {
/* 206 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionMovement5"));
/*     */     }
/*     */     
/* 209 */     synchronized (this.behav) {
/* 210 */       if (!this.behav.inCallback) {
/* 211 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionMovement5"));
/*     */       }
/*     */     } 
/*     */     
/* 215 */     return (this.collidingPath != null) ? new SceneGraphPath(this.collidingPath) : null;
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
/* 226 */     if (this.behav == null) {
/* 227 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionMovement6"));
/*     */     }
/*     */     
/* 230 */     synchronized (this.behav) {
/* 231 */       if (!this.behav.inCallback) {
/* 232 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionMovement6"));
/*     */       }
/*     */     } 
/*     */     
/* 236 */     return (this.collidingBounds != null) ? (Bounds)this.collidingBounds.clone() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/*     */     MorphRetained morphRetained;
/*     */     Shape3DRetained shape3DRetained;
/* 247 */     switch (this.nodeType) {
/*     */       case 11:
/*     */       case 28:
/* 250 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 253 */         if (this.geometryAtoms == null) {
/* 254 */           this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */         }
/* 256 */         shape3DRetained = (Shape3DRetained)this.armingNode;
/* 257 */         this.geometryAtoms.add(Shape3DRetained.getGeomAtom(shape3DRetained.getMirrorShape(this.armingPath)));
/*     */         break;
/*     */       case 10:
/* 260 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 263 */         if (this.geometryAtoms == null) {
/* 264 */           this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */         }
/* 266 */         morphRetained = (MorphRetained)this.armingNode;
/* 267 */         this.geometryAtoms.add(Shape3DRetained.getGeomAtom(morphRetained.getMirrorShape(this.armingPath)));
/*     */         break;
/*     */       case 25:
/* 270 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 273 */         this.boundingLeaf = ((BoundingLeafRetained)this.armingNode).mirrorBoundingLeaf;
/*     */         break;
/*     */       case 0:
/* 276 */         this.vwcBounds = (Bounds)this.armingBounds.clone();
/* 277 */         this.armingNode = this.behav;
/*     */         break;
/*     */       case 23:
/* 280 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 283 */         if (this.accuracyMode == 10) {
/* 284 */           if (this.geometryAtoms == null) {
/* 285 */             this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */           }
/* 287 */           ((GroupRetained)this.armingNode).searchGeometryAtoms(this.geometryAtoms);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 293 */     this.behav.universe.geometryStructure.addWakeupOnCollision(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 302 */     this.vwcBounds = null;
/* 303 */     if (this.geometryAtoms != null) {
/* 304 */       this.geometryAtoms.clear();
/*     */     }
/* 306 */     this.boundingLeaf = null;
/* 307 */     this.behav.universe.geometryStructure.removeWakeupOnCollision(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setTarget(BHLeafInterface paramBHLeafInterface) {
/*     */     Bounds bounds;
/*     */     SceneGraphPath sceneGraphPath;
/* 316 */     if (paramBHLeafInterface instanceof GeometryAtom) {
/*     */       
/* 318 */       GeometryAtom geometryAtom = (GeometryAtom)paramBHLeafInterface;
/* 319 */       Shape3DRetained shape3DRetained = geometryAtom.source;
/* 320 */       sceneGraphPath = WakeupOnCollisionEntry.getSceneGraphPath(shape3DRetained.sourceNode, shape3DRetained.key, shape3DRetained.getCurrentLocalToVworld(0));
/*     */ 
/*     */ 
/*     */       
/* 324 */       bounds = WakeupOnCollisionEntry.getTriggeringBounds(shape3DRetained);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 329 */       GroupRetained groupRetained = (GroupRetained)paramBHLeafInterface;
/* 330 */       sceneGraphPath = WakeupOnCollisionEntry.getSceneGraphPath(groupRetained);
/* 331 */       bounds = WakeupOnCollisionEntry.getTriggeringBounds(groupRetained);
/*     */     } 
/*     */     
/* 334 */     if (sceneGraphPath != null) {
/*     */ 
/*     */       
/* 337 */       this.collidingPath = sceneGraphPath;
/* 338 */       this.collidingBounds = bounds;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void updateCollisionBounds(boolean paramBoolean) {
/* 344 */     if (this.nodeType == 23) {
/* 345 */       GroupRetained groupRetained = (GroupRetained)this.armingNode;
/* 346 */       if (groupRetained.collisionBound != null) {
/* 347 */         this.vwcBounds = (Bounds)groupRetained.collisionBound.clone();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 352 */         this.vwcBounds = groupRetained.getEffectiveBounds();
/*     */       } 
/* 354 */       groupRetained.transformBounds(this.armingPath, this.vwcBounds);
/* 355 */     } else if (this.nodeType == 0) {
/* 356 */       this.vwcBounds.transform(this.armingBounds, this.behav.getCurrentLocalToVworld());
/*     */     } 
/*     */ 
/*     */     
/* 360 */     if (paramBoolean && this.nodeType == 23 && this.accuracyMode == 10) {
/*     */ 
/*     */       
/* 363 */       this.geometryAtoms.clear();
/* 364 */       ((GroupRetained)this.armingNode).searchGeometryAtoms(this.geometryAtoms);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void setTriggered() {
/* 370 */     if (this.collidingPath != null)
/* 371 */       super.setTriggered(); 
/*     */   }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\WakeupOnCollisionMovement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */