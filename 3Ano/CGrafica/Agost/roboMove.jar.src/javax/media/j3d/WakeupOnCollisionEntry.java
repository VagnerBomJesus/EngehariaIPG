/*     */ package javax.media.j3d;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WakeupOnCollisionEntry
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_GS_LIST = 0;
/*     */   static final int COLLIDEENTRY_IN_BS_LIST = 1;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 2;
/*     */   public static final int USE_GEOMETRY = 10;
/*     */   public static final int USE_BOUNDS = 11;
/*     */   static final int GROUP = 23;
/*     */   static final int BOUNDINGLEAF = 25;
/*     */   static final int SHAPE = 11;
/*     */   static final int MORPH = 10;
/*     */   static final int ORIENTEDSHAPE3D = 28;
/*     */   static final int BOUND = 0;
/*     */   int accuracyMode;
/*     */   NodeRetained armingNode;
/*  58 */   Bounds vwcBounds = null;
/*     */ 
/*     */ 
/*     */   
/*  62 */   BoundingLeafRetained boundingLeaf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   UnorderList geometryAtoms = null;
/*     */ 
/*     */   
/*     */   int nodeType;
/*     */   
/*  73 */   SceneGraphPath armingPath = null;
/*  74 */   Bounds armingBounds = null;
/*     */ 
/*     */ 
/*     */   
/*  78 */   Bounds collidingBounds = null;
/*  79 */   SceneGraphPath collidingPath = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public WakeupOnCollisionEntry(SceneGraphPath paramSceneGraphPath) { this(paramSceneGraphPath, 11); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public WakeupOnCollisionEntry(SceneGraphPath paramSceneGraphPath, int paramInt) { this(new SceneGraphPath(paramSceneGraphPath), paramInt, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public WakeupOnCollisionEntry(Node paramNode) { this(paramNode, 11); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public WakeupOnCollisionEntry(Node paramNode, int paramInt) { this(new SceneGraphPath(null, paramNode), paramInt, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public WakeupOnCollisionEntry(Bounds paramBounds) { this(null, 11, (Bounds)paramBounds.clone()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WakeupOnCollisionEntry(SceneGraphPath paramSceneGraphPath, int paramInt, Bounds paramBounds) {
/* 165 */     if (paramSceneGraphPath != null) {
/* 166 */       this.armingNode = (NodeRetained)(paramSceneGraphPath.getObject()).retained;
/* 167 */       this.nodeType = getNodeType(this.armingNode, paramSceneGraphPath, "WakeupOnCollisionEntry");
/*     */       
/* 169 */       this.armingPath = paramSceneGraphPath;
/* 170 */       validateSpeedHint(paramInt, "WakeupOnCollisionEntry4");
/*     */     } else {
/* 172 */       this.armingBounds = paramBounds;
/* 173 */       this.nodeType = 0;
/*     */     } 
/* 175 */     this.accuracyMode = paramInt;
/* 176 */     WakeupIndexedList.init(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public SceneGraphPath getArmingPath() { return (this.armingPath != null) ? new SceneGraphPath(this.armingPath) : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public Bounds getArmingBounds() { return (this.armingBounds != null) ? (Bounds)this.armingBounds.clone() : null; }
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
/* 206 */     if (this.behav == null) {
/* 207 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionEntry5"));
/*     */     }
/*     */     
/* 210 */     synchronized (this.behav) {
/* 211 */       if (!this.behav.inCallback) {
/* 212 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionEntry5"));
/*     */       }
/*     */     } 
/*     */     
/* 216 */     return (this.collidingPath != null) ? new SceneGraphPath(this.collidingPath) : null;
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
/* 227 */     if (this.behav == null) {
/* 228 */       throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionEntry6"));
/*     */     }
/*     */     
/* 231 */     synchronized (this.behav) {
/* 232 */       if (!this.behav.inCallback) {
/* 233 */         throw new IllegalStateException(J3dI18N.getString("WakeupOnCollisionEntry6"));
/*     */       }
/*     */     } 
/*     */     
/* 237 */     return (this.collidingBounds != null) ? (Bounds)this.collidingBounds.clone() : null;
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
/*     */   static int getNodeType(NodeRetained paramNodeRetained, SceneGraphPath paramSceneGraphPath, String paramString) throws IllegalArgumentException {
/* 254 */     if (!paramSceneGraphPath.validate()) {
/* 255 */       throw new IllegalArgumentException(J3dI18N.getString(paramString + "7"));
/*     */     }
/*     */     
/* 258 */     if (paramNodeRetained.inBackgroundGroup) {
/* 259 */       throw new IllegalArgumentException(J3dI18N.getString(paramString + "1"));
/*     */     }
/*     */ 
/*     */     
/* 263 */     if (paramNodeRetained instanceof OrientedShape3DRetained) {
/* 264 */       return 28;
/*     */     }
/*     */     
/* 267 */     if (paramNodeRetained instanceof Shape3DRetained) {
/* 268 */       return 11;
/*     */     }
/*     */     
/* 271 */     if (paramNodeRetained instanceof MorphRetained) {
/* 272 */       return 10;
/*     */     }
/*     */     
/* 275 */     if (paramNodeRetained instanceof GroupRetained) {
/* 276 */       return 23;
/*     */     }
/*     */     
/* 279 */     if (paramNodeRetained instanceof BoundingLeafRetained) {
/* 280 */       return 25;
/*     */     }
/*     */     
/* 283 */     throw new IllegalArgumentException(J3dI18N.getString(paramString + "0"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateSpeedHint(int paramInt, String paramString) throws IllegalArgumentException {
/* 292 */     if (paramInt != 10 && paramInt != 11) {
/* 293 */       throw new IllegalArgumentException(J3dI18N.getString(paramString));
/*     */     }
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
/* 305 */     switch (this.nodeType) {
/*     */       case 11:
/*     */       case 28:
/* 308 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 311 */         if (this.geometryAtoms == null) {
/* 312 */           this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */         }
/* 314 */         shape3DRetained = (Shape3DRetained)this.armingNode;
/* 315 */         this.geometryAtoms.add(Shape3DRetained.getGeomAtom(shape3DRetained.getMirrorShape(this.armingPath)));
/*     */         break;
/*     */       case 10:
/* 318 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 321 */         if (this.geometryAtoms == null) {
/* 322 */           this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */         }
/* 324 */         morphRetained = (MorphRetained)this.armingNode;
/* 325 */         this.geometryAtoms.add(Shape3DRetained.getGeomAtom(morphRetained.getMirrorShape(this.armingPath)));
/*     */         break;
/*     */       case 25:
/* 328 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 331 */         this.boundingLeaf = ((BoundingLeafRetained)this.armingNode).mirrorBoundingLeaf;
/*     */         break;
/*     */       case 0:
/* 334 */         this.vwcBounds = (Bounds)this.armingBounds.clone();
/* 335 */         this.armingNode = this.behav;
/*     */         break;
/*     */       case 23:
/* 338 */         if (!this.armingNode.source.isLive()) {
/*     */           return;
/*     */         }
/* 341 */         if (this.accuracyMode == 10) {
/* 342 */           if (this.geometryAtoms == null) {
/* 343 */             this.geometryAtoms = new UnorderList(1, GeometryAtom.class);
/*     */           }
/* 345 */           ((GroupRetained)this.armingNode).searchGeometryAtoms(this.geometryAtoms);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 351 */     this.behav.universe.geometryStructure.addWakeupOnCollision(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 359 */     this.vwcBounds = null;
/* 360 */     if (this.geometryAtoms != null) {
/* 361 */       this.geometryAtoms.clear();
/*     */     }
/* 363 */     this.boundingLeaf = null;
/* 364 */     this.behav.universe.geometryStructure.removeWakeupOnCollision(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setTarget(BHLeafInterface paramBHLeafInterface) {
/*     */     Bounds bounds;
/*     */     SceneGraphPath sceneGraphPath;
/* 373 */     if (paramBHLeafInterface instanceof GeometryAtom) {
/*     */       
/* 375 */       GeometryAtom geometryAtom = (GeometryAtom)paramBHLeafInterface;
/* 376 */       Shape3DRetained shape3DRetained = geometryAtom.source;
/*     */       
/* 378 */       sceneGraphPath = getSceneGraphPath(shape3DRetained.sourceNode, shape3DRetained.key, shape3DRetained.getCurrentLocalToVworld(0));
/*     */ 
/*     */       
/* 381 */       bounds = getTriggeringBounds(shape3DRetained);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 386 */       GroupRetained groupRetained = (GroupRetained)paramBHLeafInterface;
/* 387 */       sceneGraphPath = getSceneGraphPath(groupRetained);
/* 388 */       bounds = getTriggeringBounds(groupRetained);
/*     */     } 
/*     */     
/* 391 */     if (sceneGraphPath != null) {
/*     */ 
/*     */       
/* 394 */       this.collidingPath = sceneGraphPath;
/* 395 */       this.collidingBounds = bounds;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateCollisionBounds(boolean paramBoolean) {
/* 402 */     if (this.nodeType == 23) {
/* 403 */       GroupRetained groupRetained = (GroupRetained)this.armingNode;
/* 404 */       if (groupRetained.collisionBound != null) {
/* 405 */         this.vwcBounds = (Bounds)groupRetained.collisionBound.clone();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 410 */         this.vwcBounds = groupRetained.getEffectiveBounds();
/*     */       } 
/* 412 */       groupRetained.transformBounds(this.armingPath, this.vwcBounds);
/* 413 */     } else if (this.nodeType == 0) {
/* 414 */       this.vwcBounds.transform(this.armingBounds, this.behav.getCurrentLocalToVworld());
/*     */     } 
/*     */     
/* 417 */     if (paramBoolean && this.nodeType == 23 && this.accuracyMode == 10) {
/*     */ 
/*     */       
/* 420 */       this.geometryAtoms.clear();
/* 421 */       ((GroupRetained)this.armingNode).searchGeometryAtoms(this.geometryAtoms);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Bounds getTriggeringBounds(Shape3DRetained paramShape3DRetained) {
/* 430 */     NodeRetained nodeRetained = paramShape3DRetained.sourceNode;
/*     */     
/* 432 */     if (nodeRetained instanceof Shape3DRetained) {
/* 433 */       Shape3DRetained shape3DRetained = (Shape3DRetained)nodeRetained;
/* 434 */       if (shape3DRetained.collisionBound == null)
/*     */       {
/* 436 */         return shape3DRetained.getEffectiveBounds();
/*     */       }
/* 438 */       return shape3DRetained.collisionBound;
/*     */     } 
/*     */ 
/*     */     
/* 442 */     MorphRetained morphRetained = (MorphRetained)nodeRetained;
/* 443 */     if (morphRetained.collisionBound == null)
/*     */     {
/* 445 */       return morphRetained.getEffectiveBounds();
/*     */     }
/* 447 */     return morphRetained.collisionBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Bounds getTriggeringBounds(GroupRetained paramGroupRetained) {
/* 455 */     if (paramGroupRetained.collisionBound == null)
/*     */     {
/* 457 */       return paramGroupRetained.getEffectiveBounds();
/*     */     }
/* 459 */     return paramGroupRetained.collisionBound;
/*     */   }
/*     */ 
/*     */   
/*     */   static SceneGraphPath getSceneGraphPath(GroupRetained paramGroupRetained) {
/* 464 */     Transform3D transform3D = null;
/* 465 */     GroupRetained groupRetained = paramGroupRetained.sourceNode;
/*     */     
/* 467 */     synchronized (groupRetained.universe.sceneGraphLock) {
/* 468 */       if (paramGroupRetained.key == null) {
/* 469 */         transform3D = groupRetained.getCurrentLocalToVworld();
/*     */       } else {
/* 471 */         HashKey[] arrayOfHashKey = groupRetained.localToVworldKeys;
/* 472 */         if (arrayOfHashKey == null)
/*     */         {
/*     */           
/* 475 */           return null;
/*     */         }
/* 477 */         transform3D = groupRetained.getCurrentLocalToVworld(paramGroupRetained.key);
/*     */       } 
/* 479 */       return getSceneGraphPath(groupRetained, paramGroupRetained.key, transform3D);
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
/*     */   static SceneGraphPath getSceneGraphPath(NodeRetained paramNodeRetained, HashKey paramHashKey, Transform3D paramTransform3D) {
/* 491 */     synchronized (paramNodeRetained.universe.sceneGraphLock) {
/* 492 */       Node[] arrayOfNode; NodeRetained nodeRetained1 = paramNodeRetained;
/*     */       
/* 494 */       UnorderList unorderList = new UnorderList(5, Node.class);
/* 495 */       NodeRetained nodeRetained2 = nodeRetained1;
/* 496 */       Locale locale = nodeRetained2.locale;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 501 */       if (nodeRetained2.inSharedGroup)
/*     */       {
/* 503 */         if (paramHashKey != null) {
/* 504 */           paramHashKey = new HashKey(paramHashKey);
/*     */         } else {
/* 506 */           paramHashKey = new HashKey(paramNodeRetained.localToVworldKeys[0]);
/*     */         } 
/*     */       }
/*     */       
/*     */       do {
/* 511 */         if (nodeRetained2.source.getCapability(0)) {
/* 512 */           unorderList.add(nodeRetained2.source);
/*     */         }
/*     */         
/* 515 */         if (nodeRetained2 instanceof SharedGroupRetained) {
/*     */ 
/*     */           
/* 518 */           String str = paramHashKey.getLastNodeId();
/* 519 */           Vector vector = ((SharedGroupRetained)nodeRetained2).parents;
/* 520 */           arrayOfNode = nodeRetained2;
/* 521 */           for (int i = vector.size() - 1; i >= 0; i--) {
/* 522 */             NodeRetained nodeRetained = (NodeRetained)vector.elementAt(i);
/* 523 */             if (nodeRetained.nodeId.equals(str)) {
/* 524 */               nodeRetained2 = nodeRetained;
/*     */               break;
/*     */             } 
/*     */           } 
/* 528 */           if (nodeRetained2 == arrayOfNode)
/*     */           {
/*     */             
/* 531 */             return null;
/*     */           }
/* 533 */         } else if (nodeRetained2 instanceof GroupRetained && ((GroupRetained)nodeRetained2).collisionTarget) {
/*     */ 
/*     */ 
/*     */           
/* 537 */           nodeRetained1 = nodeRetained2;
/*     */           
/* 539 */           if (paramHashKey == null) {
/* 540 */             paramTransform3D = nodeRetained2.getCurrentLocalToVworld(null);
/*     */           } else {
/* 542 */             paramTransform3D = nodeRetained2.getCurrentLocalToVworld(paramHashKey);
/*     */           } 
/*     */         } 
/* 545 */         nodeRetained2 = nodeRetained2.parent;
/* 546 */       } while (nodeRetained2 != null);
/*     */ 
/*     */       
/* 549 */       if (nodeRetained1 == paramNodeRetained) {
/* 550 */         arrayOfNode = (Node[])unorderList.toArray(false);
/*     */       } else {
/* 552 */         arrayOfNode = (Node[])unorderList.toArray(nodeRetained1);
/*     */       } 
/* 554 */       SceneGraphPath sceneGraphPath = new SceneGraphPath(locale, arrayOfNode, (Node)nodeRetained1.source);
/*     */ 
/*     */       
/* 557 */       sceneGraphPath.setTransform(paramTransform3D);
/* 558 */       return sceneGraphPath;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setTriggered() {
/* 565 */     if (this.collidingPath != null)
/* 566 */       super.setTriggered(); 
/*     */   }
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\WakeupOnCollisionEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */