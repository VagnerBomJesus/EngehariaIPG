/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Point4d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class BHTree
/*      */ {
/*      */   Locale locale;
/*      */   private BHNode root;
/*   24 */   private BHInsertStructure insertStructure = null;
/*      */ 
/*      */   
/*   27 */   Point4d tPoint4d = new Point4d();
/*      */ 
/*      */   
/*      */   private boolean stable = false;
/*      */ 
/*      */   
/*      */   int estMaxDepth;
/*      */   
/*   35 */   static final double LOG_OF_2 = Math.log(2.0D);
/*      */   
/*      */   static final int DEPTH_UPPER_BOUND = 56;
/*      */   
/*      */   static final int INCR_DEPTH_BOUND = 5;
/*      */   
/*   41 */   int depthUpperBound = 56;
/*      */   
/*      */   BHTree() {
/*   44 */     this.locale = null;
/*   45 */     this.root = null;
/*      */   }
/*      */   
/*      */   BHTree(Locale paramLocale) {
/*   49 */     this.locale = paramLocale;
/*   50 */     this.root = null;
/*      */   }
/*      */   
/*      */   BHTree(BHNode[] paramArrayOfBHNode) {
/*   54 */     this.locale = null;
/*   55 */     this.root = null;
/*   56 */     create(paramArrayOfBHNode);
/*      */   }
/*      */ 
/*      */   
/*   60 */   void setLocale(Locale paramLocale) { this.locale = paramLocale; }
/*      */ 
/*      */ 
/*      */   
/*   64 */   Locale getLocale() { return this.locale; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void cluster(BHInternalNode paramBHInternalNode, BHNode[] paramArrayOfBHNode) {
/*   80 */     if (paramArrayOfBHNode == null || paramArrayOfBHNode.length < 2 || paramBHInternalNode == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   87 */     int[] arrayOfInt = new int[paramArrayOfBHNode.length];
/*   88 */     float[][] arrayOfFloat = computeCenterValues(paramArrayOfBHNode, arrayOfInt);
/*      */     
/*   90 */     constructTree(paramBHInternalNode, paramArrayOfBHNode, arrayOfFloat, arrayOfInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void boundsChanged(BHNode[] paramArrayOfBHNode, int paramInt) {
/*   98 */     markParentChain(paramArrayOfBHNode, paramInt);
/*      */ 
/*      */     
/*  101 */     this.root.updateMarkedBoundingHull();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getVisibleBHTrees(RenderBin paramRenderBin, ArrayList paramArrayList, BoundingBox paramBoundingBox, long paramLong, boolean paramBoolean1, int paramInt, boolean paramBoolean2) {
/*  113 */     if (paramBoundingBox != null && this.root != null) {
/*      */       
/*  115 */       boolean bool = aEncompassB(paramBoundingBox, this.root.bHull);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  123 */       if (paramBoolean2 && !paramBoolean1 && bool && this.stable) {
/*      */ 
/*      */         
/*  126 */         paramArrayList.add(this.root);
/*  127 */         return true;
/*      */       } 
/*  129 */       if (!paramBoolean1 && bool) {
/*      */ 
/*      */ 
/*      */         
/*  133 */         select(paramRenderBin, paramArrayList, paramBoundingBox, this.root, paramLong, paramInt, true);
/*      */ 
/*      */         
/*  136 */         paramArrayList.add(this.root);
/*  137 */         this.stable = true;
/*      */       } else {
/*      */         
/*  140 */         select(paramRenderBin, paramArrayList, paramBoundingBox, this.root, paramLong, paramInt, false);
/*      */ 
/*      */         
/*  143 */         this.stable = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  148 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void select(RenderBin paramRenderBin, ArrayList paramArrayList, BoundingBox paramBoundingBox, BHNode paramBHNode, long paramLong, int paramInt, boolean paramBoolean) {
/*  155 */     if (paramBHNode == null || paramBHNode.bHull.isEmpty()) {
/*      */       return;
/*      */     }
/*      */     
/*  159 */     switch (paramBHNode.nodeType) {
/*      */       case 2:
/*  161 */         if (((BHLeafNode)paramBHNode).leafIF instanceof GeometryAtom && ((BHLeafNode)paramBHNode).isEnable(paramInt) && (paramBoolean || paramBoundingBox.intersect(paramBHNode.bHull))) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  166 */           paramRenderBin.processGeometryAtom((GeometryAtom)((BHLeafNode)paramBHNode).leafIF, paramLong);
/*      */ 
/*      */           
/*  169 */           if (!paramBoolean) {
/*  170 */             paramArrayList.add(paramBHNode);
/*      */           }
/*      */         } 
/*      */         break;
/*      */       case 1:
/*  175 */         if (paramBoolean) {
/*  176 */           select(paramRenderBin, paramArrayList, paramBoundingBox, ((BHInternalNode)paramBHNode).getRightChild(), paramLong, paramInt, true);
/*      */ 
/*      */           
/*  179 */           select(paramRenderBin, paramArrayList, paramBoundingBox, ((BHInternalNode)paramBHNode).getLeftChild(), paramLong, paramInt, true);
/*      */           
/*      */           break;
/*      */         } 
/*  183 */         if (aEncompassB(paramBoundingBox, paramBHNode.bHull)) {
/*  184 */           paramArrayList.add(paramBHNode);
/*  185 */           select(paramRenderBin, paramArrayList, paramBoundingBox, ((BHInternalNode)paramBHNode).getRightChild(), paramLong, paramInt, true);
/*      */ 
/*      */           
/*  188 */           select(paramRenderBin, paramArrayList, paramBoundingBox, ((BHInternalNode)paramBHNode).getLeftChild(), paramLong, paramInt, true);
/*      */           
/*      */           break;
/*      */         } 
/*  192 */         if (paramBoundingBox.intersect(paramBHNode.bHull)) {
/*  193 */           select(paramRenderBin, paramArrayList, paramBoundingBox, ((BHInternalNode)paramBHNode).getRightChild(), paramLong, paramInt, false);
/*      */ 
/*      */           
/*  196 */           select(paramRenderBin, paramArrayList, paramBoundingBox, ((BHInternalNode)paramBHNode).getLeftChild(), paramLong, paramInt, false);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  207 */   static boolean aEncompassB(BoundingBox paramBoundingBox1, BoundingBox paramBoundingBox2) { return (paramBoundingBox1.upper.x >= paramBoundingBox2.upper.x && paramBoundingBox1.upper.y >= paramBoundingBox2.upper.y && paramBoundingBox1.upper.z >= paramBoundingBox2.upper.z && paramBoundingBox1.lower.x <= paramBoundingBox2.lower.x && paramBoundingBox1.lower.y <= paramBoundingBox2.lower.y && paramBoundingBox1.lower.z <= paramBoundingBox2.lower.z); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   BHLeafInterface selectAny(GeometryAtom paramGeometryAtom, int paramInt) {
/*  217 */     if (paramGeometryAtom.source.geometryList == null)
/*  218 */       return null; 
/*  219 */     BHNode bHNode = doSelectAny(paramGeometryAtom, this.root, paramInt);
/*  220 */     if (bHNode == null) {
/*  221 */       return null;
/*      */     }
/*      */     
/*  224 */     return ((BHLeafNode)bHNode).leafIF;
/*      */   }
/*      */ 
/*      */   
/*      */   BHLeafInterface selectAny(GeometryAtom[] paramArrayOfGeometryAtom, int paramInt1, int paramInt2) {
/*  229 */     BHNode bHNode = doSelectAny(paramArrayOfGeometryAtom, paramInt1, this.root, paramInt2);
/*  230 */     if (bHNode == null) {
/*  231 */       return null;
/*      */     }
/*      */     
/*  234 */     return ((BHLeafNode)bHNode).leafIF;
/*      */   }
/*      */   
/*      */   private BHNode doSelectAny(GeometryAtom[] paramArrayOfGeometryAtom, int paramInt1, BHNode paramBHNode, int paramInt2) {
/*      */     int i;
/*      */     BHLeafInterface bHLeafInterface;
/*  240 */     if (paramBHNode == null || paramBHNode.bHull.isEmpty()) {
/*  241 */       return null;
/*      */     }
/*  243 */     switch (paramBHNode.nodeType) {
/*      */       case 2:
/*  245 */         bHLeafInterface = ((BHLeafNode)paramBHNode).leafIF;
/*      */ 
/*      */ 
/*      */         
/*  249 */         if (bHLeafInterface instanceof GeometryAtom) {
/*  250 */           GeometryAtom geometryAtom = (GeometryAtom)bHLeafInterface;
/*      */           
/*  252 */           if (((BHLeafNode)paramBHNode).isEnable() && geometryAtom.source.isCollidable) {
/*      */             int j;
/*      */ 
/*      */             
/*  256 */             for (j = paramInt1 - 1; j >= 0; j--) {
/*  257 */               if (paramArrayOfGeometryAtom[j] == geometryAtom) {
/*  258 */                 return null;
/*      */               }
/*      */             } 
/*  261 */             for (j = paramInt1 - 1; j >= 0; j--) {
/*  262 */               GeometryAtom geometryAtom1 = paramArrayOfGeometryAtom[j];
/*  263 */               if (geometryAtom1.source.sourceNode != geometryAtom.source.sourceNode && geometryAtom1.source.collisionVwcBound.intersect(geometryAtom.source.collisionVwcBound) && (paramInt2 == 11 || (geometryAtom.source.geometryList != null && geometryAtom1.source.intersectGeometryList(geometryAtom.source))))
/*      */               {
/*      */ 
/*      */ 
/*      */                 
/*  268 */                 return paramBHNode;
/*      */               }
/*      */             } 
/*      */           } 
/*  272 */         } else if (bHLeafInterface instanceof GroupRetained && (
/*  273 */           (BHLeafNode)paramBHNode).isEnable() && ((GroupRetained)bHLeafInterface).sourceNode.collidable) {
/*      */           
/*  275 */           for (int j = paramInt1 - 1; j >= 0; j--) {
/*  276 */             GeometryAtom geometryAtom = paramArrayOfGeometryAtom[j];
/*  277 */             if (geometryAtom.source.collisionVwcBound.intersect(paramBHNode.bHull) && (paramInt2 == 11 || geometryAtom.source.intersectGeometryList(geometryAtom.source.getCurrentLocalToVworld(0), paramBHNode.bHull)))
/*      */             {
/*      */ 
/*      */               
/*  281 */               return paramBHNode;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  286 */         return null;
/*      */       case 1:
/*  288 */         for (i = paramInt1 - 1; i >= 0; i--) {
/*  289 */           GeometryAtom geometryAtom = paramArrayOfGeometryAtom[i];
/*  290 */           if (geometryAtom.source.collisionVwcBound.intersect(paramBHNode.bHull)) {
/*      */             
/*  292 */             BHNode bHNode = doSelectAny(paramArrayOfGeometryAtom, paramInt1, ((BHInternalNode)paramBHNode).getRightChild(), paramInt2);
/*      */ 
/*      */ 
/*      */             
/*  296 */             if (bHNode != null) {
/*  297 */               return bHNode;
/*      */             }
/*  299 */             return doSelectAny(paramArrayOfGeometryAtom, paramInt1, ((BHInternalNode)paramBHNode).getLeftChild(), paramInt2);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  304 */         return null;
/*      */     } 
/*  306 */     return null;
/*      */   }
/*      */   
/*      */   private BHNode doSelectAny(GeometryAtom paramGeometryAtom, BHNode paramBHNode, int paramInt) {
/*      */     BHLeafInterface bHLeafInterface;
/*  311 */     if (paramBHNode == null || paramBHNode.bHull.isEmpty()) {
/*  312 */       return null;
/*      */     }
/*  314 */     switch (paramBHNode.nodeType) {
/*      */       case 2:
/*  316 */         bHLeafInterface = ((BHLeafNode)paramBHNode).leafIF;
/*  317 */         if (bHLeafInterface instanceof GeometryAtom) {
/*  318 */           GeometryAtom geometryAtom = (GeometryAtom)bHLeafInterface;
/*  319 */           if (paramGeometryAtom.source.sourceNode != geometryAtom.source.sourceNode && ((BHLeafNode)paramBHNode).isEnable() && geometryAtom.source.isCollidable && paramGeometryAtom.source.collisionVwcBound.intersect(geometryAtom.source.collisionVwcBound) && (paramInt == 11 || (geometryAtom.source.geometryList != null && paramGeometryAtom.source.intersectGeometryList(geometryAtom.source))))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  326 */             return paramBHNode;
/*      */           }
/*  328 */         } else if (bHLeafInterface instanceof GroupRetained && (
/*  329 */           (BHLeafNode)paramBHNode).isEnable() && ((GroupRetained)bHLeafInterface).sourceNode.collidable && paramGeometryAtom.source.collisionVwcBound.intersect(paramBHNode.bHull) && (paramInt == 11 || paramGeometryAtom.source.intersectGeometryList(paramGeometryAtom.source.getCurrentLocalToVworld(0), paramBHNode.bHull))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  335 */           return paramBHNode;
/*      */         } 
/*      */         
/*  338 */         return null;
/*      */       case 1:
/*  340 */         if (paramGeometryAtom.source.collisionVwcBound.intersect(paramBHNode.bHull)) {
/*  341 */           BHNode bHNode = doSelectAny(paramGeometryAtom, ((BHInternalNode)paramBHNode).getRightChild(), paramInt);
/*      */ 
/*      */           
/*  344 */           if (bHNode != null) {
/*  345 */             return bHNode;
/*      */           }
/*  347 */           return doSelectAny(paramGeometryAtom, ((BHInternalNode)paramBHNode).getLeftChild(), paramInt);
/*      */         } 
/*      */ 
/*      */         
/*  351 */         return null;
/*      */     } 
/*  353 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   BHLeafInterface selectAny(Bounds paramBounds, int paramInt, NodeRetained paramNodeRetained) {
/*  358 */     if (paramBounds == null) {
/*  359 */       return null;
/*      */     }
/*  361 */     BHNode bHNode = doSelectAny(paramBounds, this.root, paramInt, paramNodeRetained);
/*  362 */     if (bHNode == null) {
/*  363 */       return null;
/*      */     }
/*  365 */     return ((BHLeafNode)bHNode).leafIF;
/*      */   }
/*      */   
/*      */   private BHNode doSelectAny(Bounds paramBounds, BHNode paramBHNode, int paramInt, NodeRetained paramNodeRetained) {
/*      */     BHLeafInterface bHLeafInterface;
/*  370 */     if (paramBHNode == null || paramBHNode.bHull.isEmpty()) {
/*  371 */       return null;
/*      */     }
/*      */     
/*  374 */     switch (paramBHNode.nodeType) {
/*      */       case 2:
/*  376 */         bHLeafInterface = ((BHLeafNode)paramBHNode).leafIF;
/*  377 */         if (bHLeafInterface instanceof GeometryAtom) {
/*  378 */           GeometryAtom geometryAtom = (GeometryAtom)bHLeafInterface;
/*  379 */           if (((BHLeafNode)paramBHNode).isEnable() && geometryAtom.source.isCollidable && paramBounds.intersect(geometryAtom.source.collisionVwcBound) && (paramInt == 11 || (geometryAtom.source.geometryList != null && geometryAtom.source.intersectGeometryList(geometryAtom.source.getCurrentLocalToVworld(0), paramBounds))))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  386 */             return paramBHNode;
/*      */           }
/*  388 */         } else if (bHLeafInterface instanceof GroupRetained && 
/*  389 */           bHLeafInterface != paramNodeRetained && ((BHLeafNode)paramBHNode).isEnable() && ((GroupRetained)bHLeafInterface).sourceNode.collidable && paramBounds.intersect(paramBHNode.bHull)) {
/*      */ 
/*      */ 
/*      */           
/*  393 */           return paramBHNode;
/*      */         } 
/*      */         
/*  396 */         return null;
/*      */       case 1:
/*  398 */         if (paramBounds.intersect(paramBHNode.bHull)) {
/*  399 */           BHNode bHNode = doSelectAny(paramBounds, ((BHInternalNode)paramBHNode).getRightChild(), paramInt, paramNodeRetained);
/*      */ 
/*      */ 
/*      */           
/*  403 */           if (bHNode != null) {
/*  404 */             return bHNode;
/*      */           }
/*  406 */           return doSelectAny(paramBounds, ((BHInternalNode)paramBHNode).getLeftChild(), paramInt, paramNodeRetained);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  411 */         return null;
/*      */     } 
/*  413 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   BHLeafInterface selectAny(Bounds paramBounds, int paramInt, GroupRetained paramGroupRetained) {
/*  419 */     if (paramBounds == null) {
/*  420 */       return null;
/*      */     }
/*  422 */     BHNode bHNode = doSelectAny(paramBounds, this.root, paramInt, paramGroupRetained);
/*  423 */     if (bHNode == null) {
/*  424 */       return null;
/*      */     }
/*  426 */     return ((BHLeafNode)bHNode).leafIF;
/*      */   }
/*      */   
/*      */   private BHNode doSelectAny(Bounds paramBounds, BHNode paramBHNode, int paramInt, GroupRetained paramGroupRetained) {
/*      */     BHLeafInterface bHLeafInterface;
/*  431 */     if (paramBHNode == null || paramBHNode.bHull.isEmpty()) {
/*  432 */       return null;
/*      */     }
/*  434 */     switch (paramBHNode.nodeType) {
/*      */       case 2:
/*  436 */         bHLeafInterface = ((BHLeafNode)paramBHNode).leafIF;
/*      */         
/*  438 */         if (bHLeafInterface instanceof GeometryAtom) {
/*  439 */           GeometryAtom geometryAtom = (GeometryAtom)bHLeafInterface;
/*  440 */           if (((BHLeafNode)paramBHNode).isEnable() && geometryAtom.source.isCollidable && paramBounds.intersect(geometryAtom.source.collisionVwcBound) && !isDescendent(geometryAtom.source.sourceNode, paramGroupRetained, geometryAtom.source.key) && (paramInt == 11 || (geometryAtom.source.geometryList != null && geometryAtom.source.intersectGeometryList(geometryAtom.source.getCurrentLocalToVworld(0), paramBounds))))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  449 */             return paramBHNode;
/*      */           }
/*  451 */         } else if (bHLeafInterface instanceof GroupRetained) {
/*  452 */           GroupRetained groupRetained = (GroupRetained)bHLeafInterface;
/*  453 */           if (((BHLeafNode)paramBHNode).isEnable() && groupRetained.sourceNode.collidable && paramBounds.intersect(paramBHNode.bHull) && !isDescendent(groupRetained.sourceNode, paramGroupRetained, groupRetained.key))
/*      */           {
/*      */ 
/*      */             
/*  457 */             return paramBHNode;
/*      */           }
/*      */         } 
/*  460 */         return null;
/*      */       case 1:
/*  462 */         if (paramBounds.intersect(paramBHNode.bHull)) {
/*  463 */           BHNode bHNode = doSelectAny(paramBounds, ((BHInternalNode)paramBHNode).getRightChild(), paramInt, paramGroupRetained);
/*      */ 
/*      */ 
/*      */           
/*  467 */           if (bHNode != null) {
/*  468 */             return bHNode;
/*      */           }
/*  470 */           return doSelectAny(paramBounds, ((BHInternalNode)paramBHNode).getLeftChild(), paramInt, paramGroupRetained);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  475 */         return null;
/*      */     } 
/*  477 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isDescendent(NodeRetained paramNodeRetained, GroupRetained paramGroupRetained, HashKey paramHashKey) {
/*  485 */     synchronized (paramGroupRetained.universe.sceneGraphLock) {
/*  486 */       if (paramNodeRetained.inSharedGroup)
/*      */       {
/*  488 */         if (paramHashKey != null) {
/*  489 */           paramHashKey = new HashKey(paramHashKey);
/*      */         }
/*      */       }
/*      */       
/*      */       do {
/*  494 */         if (paramNodeRetained == paramGroupRetained) {
/*  495 */           return true;
/*      */         }
/*  497 */         if (paramNodeRetained instanceof SharedGroupRetained) {
/*      */           
/*  499 */           String str = paramHashKey.getLastNodeId();
/*  500 */           NodeRetained nodeRetained = paramNodeRetained;
/*  501 */           Vector vector = ((SharedGroupRetained)paramNodeRetained).parents;
/*  502 */           for (int i = vector.size() - 1; i >= 0; i--) {
/*  503 */             NodeRetained nodeRetained1 = (NodeRetained)vector.elementAt(i);
/*  504 */             if (nodeRetained1.nodeId.equals(str)) {
/*  505 */               paramNodeRetained = nodeRetained1;
/*      */               break;
/*      */             } 
/*      */           } 
/*  509 */           if (nodeRetained == paramNodeRetained)
/*      */           {
/*  511 */             return true;
/*      */           }
/*      */         } 
/*  514 */         paramNodeRetained = paramNodeRetained.parent;
/*  515 */       } while (paramNodeRetained != null);
/*      */     } 
/*  517 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void select(PickShape paramPickShape, UnorderList paramUnorderList) {
/*  523 */     if (paramPickShape == null || this.root == null) {
/*      */       return;
/*      */     }
/*  526 */     doSelect(paramPickShape, paramUnorderList, this.root, this.tPoint4d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doSelect(PickShape paramPickShape, UnorderList paramUnorderList, BHNode paramBHNode, Point4d paramPoint4d) {
/*  534 */     if (paramBHNode == null || paramBHNode.bHull.isEmpty()) {
/*      */       return;
/*      */     }
/*      */     
/*  538 */     switch (paramBHNode.nodeType) {
/*      */       case 2:
/*  540 */         if (((BHLeafNode)paramBHNode).isEnable() && ((BHLeafNode)paramBHNode).leafIF instanceof GeometryAtom && ((GeometryAtom)((BHLeafNode)paramBHNode).leafIF).source.isPickable && paramPickShape.intersect(paramBHNode.bHull, paramPoint4d))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  545 */           paramUnorderList.add(paramBHNode);
/*      */         }
/*      */         break;
/*      */       case 1:
/*  549 */         if (paramPickShape.intersect(paramBHNode.bHull, paramPoint4d)) {
/*  550 */           doSelect(paramPickShape, paramUnorderList, ((BHInternalNode)paramBHNode).getRightChild(), paramPoint4d);
/*      */ 
/*      */ 
/*      */           
/*  554 */           doSelect(paramPickShape, paramUnorderList, ((BHInternalNode)paramBHNode).getLeftChild(), paramPoint4d);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   BHNode selectAny(PickShape paramPickShape) {
/*  565 */     if (paramPickShape == null || this.root == null) {
/*  566 */       return null;
/*      */     }
/*  568 */     return doSelectAny(paramPickShape, this.root, this.tPoint4d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private BHNode doSelectAny(PickShape paramPickShape, BHNode paramBHNode, Point4d paramPoint4d) {
/*  575 */     BHNode bHNode = null;
/*      */     
/*  577 */     if (paramBHNode == null || paramBHNode.bHull.isEmpty()) {
/*  578 */       return null;
/*      */     }
/*  580 */     switch (paramBHNode.nodeType) {
/*      */       case 2:
/*  582 */         if (((BHLeafNode)paramBHNode).isEnable() && ((BHLeafNode)paramBHNode).leafIF instanceof GeometryAtom && ((GeometryAtom)((BHLeafNode)paramBHNode).leafIF).source.isPickable && paramPickShape.intersect(paramBHNode.bHull, paramPoint4d))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  587 */           return paramBHNode;
/*      */         }
/*      */         break;
/*      */       case 1:
/*  591 */         if (paramPickShape.intersect(paramBHNode.bHull, paramPoint4d)) {
/*  592 */           bHNode = doSelectAny(paramPickShape, ((BHInternalNode)paramBHNode).getRightChild(), paramPoint4d);
/*      */ 
/*      */ 
/*      */           
/*  596 */           if (bHNode != null) {
/*  597 */             return bHNode;
/*      */           }
/*      */           
/*  600 */           return doSelectAny(paramPickShape, ((BHInternalNode)paramBHNode).getLeftChild(), paramPoint4d);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  606 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void create(BHNode[] paramArrayOfBHNode) {
/*  613 */     if (paramArrayOfBHNode == null) {
/*  614 */       this.root = null;
/*      */       
/*      */       return;
/*      */     } 
/*  618 */     if (paramArrayOfBHNode.length == 1) {
/*  619 */       paramArrayOfBHNode[0].computeBoundingHull();
/*  620 */       this.root = paramArrayOfBHNode[0];
/*      */       
/*      */       return;
/*      */     } 
/*  624 */     int[] arrayOfInt = new int[paramArrayOfBHNode.length];
/*  625 */     float[][] arrayOfFloat = computeCenterValues(paramArrayOfBHNode, arrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  635 */     this.root = new BHInternalNode();
/*  636 */     constructTree((BHInternalNode)this.root, paramArrayOfBHNode, arrayOfFloat, arrayOfInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void insert(BHNode[] paramArrayOfBHNode, int paramInt) {
/*  657 */     if (paramArrayOfBHNode == null || paramInt < 1 || paramArrayOfBHNode.length < 1) {
/*      */       return;
/*      */     }
/*  660 */     if (this.root == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  667 */       BHNode[] arrayOfBHNode = new BHNode[paramInt];
/*  668 */       System.arraycopy(paramArrayOfBHNode, 0, arrayOfBHNode, 0, paramInt);
/*  669 */       create(arrayOfBHNode);
/*      */       
/*      */       return;
/*      */     } 
/*  673 */     if (this.root.nodeType == 2) {
/*  674 */       BHNode[] arrayOfBHNode = paramArrayOfBHNode;
/*  675 */       paramArrayOfBHNode = new BHNode[paramInt + 1];
/*  676 */       System.arraycopy(arrayOfBHNode, 0, paramArrayOfBHNode, 0, paramInt);
/*  677 */       paramArrayOfBHNode[paramInt] = this.root;
/*  678 */       create(paramArrayOfBHNode);
/*      */       
/*      */       return;
/*      */     } 
/*  682 */     if (this.insertStructure == null) {
/*  683 */       this.insertStructure = new BHInsertStructure(paramInt);
/*      */     } else {
/*      */       
/*  686 */       this.insertStructure.clear();
/*      */     } 
/*      */     int i;
/*  689 */     for (i = 0; i < paramInt; i++) {
/*      */       
/*  691 */       if (this.root.isInside((paramArrayOfBHNode[i]).bHull)) {
/*  692 */         ((BHInternalNode)this.root).insert(paramArrayOfBHNode[i], this.insertStructure);
/*      */       }
/*      */       else {
/*      */         
/*  696 */         this.root.bHull.combine((paramArrayOfBHNode[i]).bHull);
/*  697 */         this.insertStructure.lookupAndInsert(this.root, paramArrayOfBHNode[i]);
/*      */       } 
/*      */     } 
/*      */     
/*  701 */     this.insertStructure.updateBoundingTree(this);
/*      */ 
/*      */ 
/*      */     
/*  705 */     this.insertStructure.clear();
/*      */ 
/*      */     
/*  708 */     this.estMaxDepth += (int)(Math.log(paramInt) / LOG_OF_2) + 1;
/*      */     
/*  710 */     if (this.estMaxDepth > this.depthUpperBound) {
/*  711 */       i = this.root.computeMaxDepth(0);
/*  712 */       int j = this.root.countNumberOfLeaves();
/*  713 */       double d = Math.log(j) / LOG_OF_2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  722 */       if (i > this.depthUpperBound) {
/*  723 */         reConstructTree(j);
/*  724 */         i = this.root.computeMaxDepth(0);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  736 */       if (i > this.depthUpperBound) {
/*  737 */         this.depthUpperBound += 5;
/*  738 */       } else if (this.depthUpperBound != 56 && i * 1.5D < this.depthUpperBound) {
/*      */         
/*  740 */         this.depthUpperBound -= 5;
/*      */         
/*  742 */         if (this.depthUpperBound < 56)
/*      */         {
/*  744 */           this.depthUpperBound = 56;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  752 */       this.estMaxDepth = i;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void markParentChain(BHNode[] paramArrayOfBHNode, int paramInt) {
/*  762 */     for (byte b = 0; b < paramInt; b++) {
/*  763 */       BHNode bHNode = paramArrayOfBHNode[b];
/*  764 */       bHNode.mark = true;
/*  765 */       while (bHNode.parent != null && !bHNode.parent.mark) {
/*  766 */         bHNode = bHNode.parent;
/*  767 */         bHNode.mark = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void markParentChain(BHNode paramBHNode) {
/*  774 */     paramBHNode.mark = true;
/*  775 */     while (paramBHNode.parent != null && !paramBHNode.parent.mark) {
/*  776 */       paramBHNode = paramBHNode.parent;
/*  777 */       paramBHNode.mark = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void delete(BHNode[] paramArrayOfBHNode, int paramInt) {
/*  801 */     for (byte b = 0; b < paramInt; b++) {
/*  802 */       if (paramArrayOfBHNode[b] != null && (paramArrayOfBHNode[b]).nodeType == 2) {
/*  803 */         markParentChain(paramArrayOfBHNode[b]);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  816 */     this.root = this.root.deleteAndUpdateMarkedNodes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float[][] computeCenterValues(BHNode[] paramArrayOfBHNode, int[] paramArrayOfInt) {
/*  828 */     float[][] arrayOfFloat = new float[paramArrayOfBHNode.length][3];
/*      */ 
/*      */     
/*  831 */     for (byte b = 0; b < paramArrayOfBHNode.length; b++) {
/*  832 */       paramArrayOfInt[b] = b;
/*      */       
/*  834 */       paramArrayOfBHNode[b].computeBoundingHull();
/*      */       
/*  836 */       arrayOfFloat[b][0] = (float)((paramArrayOfBHNode[b]).bHull.upper.x + (paramArrayOfBHNode[b]).bHull.lower.x) / 2.0F;
/*      */       
/*  838 */       arrayOfFloat[b][1] = (float)((paramArrayOfBHNode[b]).bHull.upper.y + (paramArrayOfBHNode[b]).bHull.lower.y) / 2.0F;
/*      */       
/*  840 */       arrayOfFloat[b][2] = (float)((paramArrayOfBHNode[b]).bHull.upper.z + (paramArrayOfBHNode[b]).bHull.lower.z) / 2.0F;
/*      */     } 
/*      */ 
/*      */     
/*  844 */     return arrayOfFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void computeMeansAndSumSquares(float[][] paramArrayOfFloat, int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/*  852 */     float[] arrayOfFloat = new float[3];
/*  853 */     float f = 0.0F;
/*      */     
/*  855 */     int j = paramArrayOfInt.length;
/*      */     int i;
/*  857 */     for (i = 2; i >= 0; i--) {
/*  858 */       arrayOfFloat[i] = 0.0F;
/*  859 */       paramArrayOfFloat2[i] = 0.0F;
/*      */     } 
/*      */     
/*  862 */     for (i = j - 1; i >= 0; i--) {
/*  863 */       arrayOfFloat[0] = arrayOfFloat[0] + paramArrayOfFloat[paramArrayOfInt[i]][0];
/*  864 */       arrayOfFloat[1] = arrayOfFloat[1] + paramArrayOfFloat[paramArrayOfInt[i]][1];
/*  865 */       arrayOfFloat[2] = arrayOfFloat[2] + paramArrayOfFloat[paramArrayOfInt[i]][2];
/*      */     } 
/*      */     
/*  868 */     paramArrayOfFloat1[0] = arrayOfFloat[0] / j;
/*  869 */     paramArrayOfFloat1[1] = arrayOfFloat[1] / j;
/*  870 */     paramArrayOfFloat1[2] = arrayOfFloat[2] / j;
/*      */     
/*  872 */     for (i = j - 1; i >= 0; i--) {
/*  873 */       f = paramArrayOfFloat[paramArrayOfInt[i]][0] - paramArrayOfFloat1[0];
/*  874 */       paramArrayOfFloat2[0] = paramArrayOfFloat2[0] + f * f;
/*  875 */       f = paramArrayOfFloat[paramArrayOfInt[i]][1] - paramArrayOfFloat1[1];
/*  876 */       paramArrayOfFloat2[1] = paramArrayOfFloat2[1] + f * f;
/*  877 */       f = paramArrayOfFloat[paramArrayOfInt[i]][2] - paramArrayOfFloat1[2];
/*  878 */       paramArrayOfFloat2[2] = paramArrayOfFloat2[2] + f * f;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int findSplitAxis(float[] paramArrayOfFloat) {
/*  887 */     byte b1 = -1;
/*  888 */     float f = 0.0F;
/*      */ 
/*      */     
/*  891 */     for (byte b2 = 0; b2 < 3; b2++) {
/*  892 */       if (paramArrayOfFloat[b2] > f) {
/*  893 */         f = paramArrayOfFloat[b2];
/*  894 */         b1 = b2;
/*      */       } 
/*      */     } 
/*  897 */     return b1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void constructTree(BHInternalNode paramBHInternalNode, BHNode[] paramArrayOfBHNode, float[][] paramArrayOfFloat, int[] paramArrayOfInt) {
/*  906 */     byte b2 = 0;
/*  907 */     byte b3 = 0;
/*  908 */     float[] arrayOfFloat1 = new float[3];
/*  909 */     float[] arrayOfFloat2 = new float[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  919 */     computeMeansAndSumSquares(paramArrayOfFloat, paramArrayOfInt, arrayOfFloat1, arrayOfFloat2);
/*      */     
/*  921 */     int i = findSplitAxis(arrayOfFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  926 */     boolean[] arrayOfBoolean = new boolean[paramArrayOfBHNode.length];
/*      */     
/*  928 */     if (i == -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  935 */       for (byte b = 0; b < paramArrayOfBHNode.length; b++) {
/*  936 */         if (b3 > b2) {
/*  937 */           b2++;
/*  938 */           arrayOfBoolean[b] = false;
/*      */         } else {
/*  940 */           b3++;
/*  941 */           arrayOfBoolean[b] = true;
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  946 */       for (byte b = 0; b < paramArrayOfBHNode.length; b++) {
/*      */         
/*  948 */         if (paramArrayOfFloat[paramArrayOfInt[b]][i] < arrayOfFloat1[i]) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  954 */           b3++;
/*  955 */           arrayOfBoolean[b] = true;
/*  956 */         } else if (paramArrayOfFloat[paramArrayOfInt[b]][i] > arrayOfFloat1[i]) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  961 */           b2++;
/*  962 */           arrayOfBoolean[b] = false;
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  967 */         else if (b3 > b2) {
/*  968 */           b2++;
/*  969 */           arrayOfBoolean[b] = false;
/*      */         } else {
/*  971 */           b3++;
/*  972 */           arrayOfBoolean[b] = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  986 */     if (b3 == paramArrayOfBHNode.length) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  992 */       b2 = 0;
/*  993 */       b3 = 0;
/*  994 */       for (byte b = 0; b < paramArrayOfBHNode.length; b++) {
/*  995 */         if (b3 > b2) {
/*  996 */           b2++;
/*  997 */           arrayOfBoolean[b] = false;
/*      */         } else {
/*  999 */           b3++;
/* 1000 */           arrayOfBoolean[b] = true;
/*      */         } 
/*      */       } 
/* 1003 */     } else if (b2 == paramArrayOfBHNode.length) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1009 */       b2 = 0;
/* 1010 */       b3 = 0;
/* 1011 */       for (byte b = 0; b < paramArrayOfBHNode.length; b++) {
/* 1012 */         if (b3 > b2) {
/* 1013 */           b2++;
/* 1014 */           arrayOfBoolean[b] = false;
/*      */         } else {
/* 1016 */           b3++;
/* 1017 */           arrayOfBoolean[b] = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1030 */     BHNode[] arrayOfBHNode1 = new BHNode[b2];
/* 1031 */     BHNode[] arrayOfBHNode2 = new BHNode[b3];
/* 1032 */     int[] arrayOfInt1 = new int[b2];
/* 1033 */     int[] arrayOfInt2 = new int[b3];
/*      */     
/* 1035 */     b2 = 0;
/* 1036 */     b3 = 0;
/*      */     
/* 1038 */     for (byte b1 = 0; b1 < paramArrayOfBHNode.length; b1++) {
/* 1039 */       if (arrayOfBoolean[b1]) {
/* 1040 */         arrayOfBHNode2[b3] = paramArrayOfBHNode[b1];
/* 1041 */         arrayOfInt2[b3] = paramArrayOfInt[b1];
/* 1042 */         b3++;
/*      */       } else {
/* 1044 */         arrayOfBHNode1[b2] = paramArrayOfBHNode[b1];
/* 1045 */         arrayOfInt1[b2] = paramArrayOfInt[b1];
/* 1046 */         b2++;
/*      */       } 
/*      */     } 
/*      */     
/* 1050 */     if (arrayOfBHNode1.length != 1) {
/* 1051 */       paramBHInternalNode.rChild = new BHInternalNode();
/* 1052 */       paramBHInternalNode.rChild.setParent(paramBHInternalNode);
/* 1053 */       constructTree((BHInternalNode)paramBHInternalNode.rChild, arrayOfBHNode1, paramArrayOfFloat, arrayOfInt1);
/*      */     } else {
/*      */       
/* 1056 */       paramBHInternalNode.rChild = arrayOfBHNode1[0];
/* 1057 */       paramBHInternalNode.rChild.setParent(paramBHInternalNode);
/*      */     } 
/*      */     
/* 1060 */     if (arrayOfBHNode2.length != 1) {
/* 1061 */       paramBHInternalNode.lChild = new BHInternalNode();
/* 1062 */       paramBHInternalNode.lChild.setParent(paramBHInternalNode);
/* 1063 */       constructTree((BHInternalNode)paramBHInternalNode.lChild, arrayOfBHNode2, paramArrayOfFloat, arrayOfInt2);
/*      */     } else {
/*      */       
/* 1066 */       paramBHInternalNode.lChild = arrayOfBHNode2[0];
/* 1067 */       paramBHInternalNode.lChild.setParent(paramBHInternalNode);
/*      */     } 
/*      */     
/* 1070 */     paramBHInternalNode.combineBHull(paramBHInternalNode.rChild, paramBHInternalNode.lChild);
/*      */   }
/*      */ 
/*      */   
/*      */   void reConstructTree(int paramInt) {
/* 1075 */     if (this.root == null) {
/*      */       return;
/*      */     }
/* 1078 */     BHNode[] arrayOfBHNode = new BHNode[paramInt];
/* 1079 */     int[] arrayOfInt = new int[1];
/* 1080 */     arrayOfInt[0] = 0;
/* 1081 */     this.root.destroyTree(arrayOfBHNode, arrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1089 */     create(arrayOfBHNode);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void gatherTreeStatistics() {
/* 1095 */     int i = this.root.countNumberOfLeaves();
/* 1096 */     int j = this.root.countNumberOfInternals();
/* 1097 */     int k = this.root.computeMaxDepth(0);
/* 1098 */     float f = this.root.computeAverageLeafDepth(i, 0);
/*      */ 
/*      */     
/* 1101 */     System.err.println("Statistics for tree = " + this);
/* 1102 */     System.err.println("Total Number of nodes in tree = " + (i + j));
/*      */     
/* 1104 */     System.err.println("Number of Leaf Nodes = " + i);
/* 1105 */     System.err.println("Number of Internal Nodes = " + j);
/* 1106 */     System.err.println("Maximum Leaf depth = " + k);
/* 1107 */     System.err.println("Average Leaf depth = " + f);
/* 1108 */     System.err.println("root.bHull = " + this.root.bHull);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void printTree(BHNode paramBHNode) {
/* 1115 */     if (paramBHNode != null)
/* 1116 */       if (paramBHNode.nodeType == 1) {
/* 1117 */         System.err.println("BH_TYPE_INTERNAL - bHull : " + paramBHNode);
/* 1118 */         System.err.println(paramBHNode.bHull);
/* 1119 */         System.err.println("rChild : " + ((BHInternalNode)paramBHNode).rChild + " lChild : " + ((BHInternalNode)paramBHNode).lChild);
/*      */         
/* 1121 */         printTree(((BHInternalNode)paramBHNode).rChild);
/* 1122 */         printTree(((BHInternalNode)paramBHNode).lChild);
/*      */       }
/* 1124 */       else if (paramBHNode.nodeType == 2) {
/* 1125 */         System.err.println("BH_TYPE_LEAF - bHull : " + paramBHNode);
/* 1126 */         System.err.println(paramBHNode.bHull);
/*      */       }  
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\BHTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */