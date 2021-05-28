/*      */ package com.sun.j3d.utils.picking;
/*      */ 
/*      */ import javax.media.j3d.Bounds;
/*      */ import javax.media.j3d.BranchGroup;
/*      */ import javax.media.j3d.CompressedGeometry;
/*      */ import javax.media.j3d.Geometry;
/*      */ import javax.media.j3d.GeometryArray;
/*      */ import javax.media.j3d.IndexedGeometryArray;
/*      */ import javax.media.j3d.Locale;
/*      */ import javax.media.j3d.Morph;
/*      */ import javax.media.j3d.Node;
/*      */ import javax.media.j3d.PickBounds;
/*      */ import javax.media.j3d.PickConeRay;
/*      */ import javax.media.j3d.PickConeSegment;
/*      */ import javax.media.j3d.PickCylinderRay;
/*      */ import javax.media.j3d.PickCylinderSegment;
/*      */ import javax.media.j3d.PickRay;
/*      */ import javax.media.j3d.PickSegment;
/*      */ import javax.media.j3d.PickShape;
/*      */ import javax.media.j3d.SceneGraphPath;
/*      */ import javax.media.j3d.Shape3D;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Vector3d;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PickTool
/*      */ {
/*      */   private final boolean debug = true;
/*      */   protected boolean userDefineShape;
/*      */   PickShape pickShape;
/*      */   BranchGroup pickRootBG;
/*      */   Locale pickRootL;
/*      */   Point3d start;
/*      */   int mode;
/*      */   public static final int BOUNDS = 512;
/*      */   public static final int GEOMETRY = 256;
/*      */   public static final int GEOMETRY_INTERSECT_INFO = 1024;
/*      */   public static final int INTERSECT_TEST = 4097;
/*      */   public static final int INTERSECT_COORD = 4098;
/*      */   public static final int INTERSECT_FULL = 4100;
/*      */   
/*      */   public PickTool(BranchGroup paramBranchGroup) {
/*  128 */     this.debug = true;
/*  129 */     this.userDefineShape = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  134 */     this.pickRootBG = null;
/*      */     
/*  136 */     this.pickRootL = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  141 */     this.start = null;
/*      */ 
/*      */     
/*  144 */     this.mode = 512;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  194 */     this.pickRootBG = paramBranchGroup;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  201 */   public BranchGroup getBranchGroup() { return this.pickRootBG; } public PickTool(Locale paramLocale) {
/*      */     this.debug = true;
/*      */     this.userDefineShape = false;
/*      */     this.pickRootBG = null;
/*      */     this.pickRootL = null;
/*      */     this.start = null;
/*      */     this.mode = 512;
/*  208 */     this.pickRootL = paramLocale;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   public Locale getLocale() { return this.pickRootL; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  225 */   public Locale setBranchGroup(Locale paramLocale) { return paramLocale; }
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setCapabilities(Node paramNode, int paramInt) {
/*  250 */     if (paramNode instanceof Morph) {
/*  251 */       Morph morph = (Morph)paramNode;
/*  252 */       switch (paramInt) {
/*      */         
/*      */         case 4098:
/*      */         case 4100:
/*  256 */           morph.setCapability(12);
/*      */           break;
/*      */         case 4097:
/*      */           break;
/*      */         default:
/*  261 */           throw new IllegalArgumentException("Improper level");
/*      */       } 
/*  263 */       double[] arrayOfDouble = morph.getWeights();
/*  264 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/*  265 */         GeometryArray geometryArray = morph.getGeometryArray(b);
/*  266 */         setCapabilities(geometryArray, paramInt);
/*      */       } 
/*  268 */     } else if (paramNode instanceof Shape3D) {
/*  269 */       Shape3D shape3D = (Shape3D)paramNode;
/*  270 */       switch (paramInt) {
/*      */         
/*      */         case 4098:
/*      */         case 4100:
/*  274 */           shape3D.setCapability(12);
/*      */           break;
/*      */         case 4097:
/*      */           break;
/*      */         default:
/*  279 */           throw new IllegalArgumentException("Improper level");
/*      */       } 
/*  281 */       for (byte b = 0; b < shape3D.numGeometries(); b++) {
/*  282 */         Geometry geometry = shape3D.getGeometry(b);
/*  283 */         if (geometry instanceof GeometryArray) {
/*  284 */           setCapabilities((GeometryArray)geometry, paramInt);
/*  285 */         } else if (geometry instanceof CompressedGeometry) {
/*  286 */           setCapabilities((CompressedGeometry)geometry, paramInt);
/*      */         } 
/*      */       } 
/*      */     } else {
/*  290 */       throw new IllegalArgumentException("Improper node type");
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void setCapabilities(GeometryArray paramGeometryArray, int paramInt) {
/*  295 */     switch (paramInt) {
/*      */       case 4100:
/*  297 */         paramGeometryArray.setCapability(2);
/*  298 */         paramGeometryArray.setCapability(4);
/*  299 */         paramGeometryArray.setCapability(6);
/*      */       
/*      */       case 4098:
/*  302 */         paramGeometryArray.setCapability(8);
/*  303 */         paramGeometryArray.setCapability(17);
/*  304 */         paramGeometryArray.setCapability(0);
/*      */       
/*      */       case 4097:
/*  307 */         paramGeometryArray.setCapability(18);
/*      */         break;
/*      */     } 
/*  310 */     if (paramGeometryArray instanceof IndexedGeometryArray) {
/*  311 */       setCapabilities((IndexedGeometryArray)paramGeometryArray, paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void setCapabilities(IndexedGeometryArray paramIndexedGeometryArray, int paramInt) {
/*  316 */     switch (paramInt) {
/*      */       case 4100:
/*  318 */         paramIndexedGeometryArray.setCapability(11);
/*  319 */         paramIndexedGeometryArray.setCapability(13);
/*  320 */         paramIndexedGeometryArray.setCapability(15);
/*      */       
/*      */       case 4098:
/*  323 */         paramIndexedGeometryArray.setCapability(9);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void setCapabilities(CompressedGeometry paramCompressedGeometry, int paramInt) {
/*  331 */     switch (paramInt) {
/*      */       
/*      */       case 4098:
/*      */       case 4100:
/*  335 */         paramCompressedGeometry.setCapability(2);
/*      */       
/*      */       case 4097:
/*  338 */         paramCompressedGeometry.setCapability(18);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShape(PickShape paramPickShape, Point3d paramPoint3d) {
/*  350 */     this.pickShape = paramPickShape;
/*  351 */     this.start = paramPoint3d;
/*  352 */     this.userDefineShape = (paramPickShape != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShapeBounds(Bounds paramBounds, Point3d paramPoint3d) {
/*  360 */     this.pickShape = new PickBounds(paramBounds);
/*  361 */     this.start = paramPoint3d;
/*  362 */     this.userDefineShape = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMode(int paramInt) {
/*  370 */     if (paramInt != 512 && paramInt != 256 && paramInt != 1024)
/*      */     {
/*  372 */       throw new IllegalArgumentException();
/*      */     }
/*  374 */     this.mode = paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  380 */   public int getMode() { return this.mode; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShapeRay(Point3d paramPoint3d, Vector3d paramVector3d) {
/*  388 */     this.pickShape = new PickRay(paramPoint3d, paramVector3d);
/*  389 */     this.start = paramPoint3d;
/*  390 */     this.userDefineShape = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShapeSegment(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*  398 */     this.pickShape = new PickSegment(paramPoint3d1, paramPoint3d2);
/*  399 */     this.start = paramPoint3d1;
/*  400 */     this.userDefineShape = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShapeCylinderSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, double paramDouble) {
/*  410 */     this.pickShape = new PickCylinderSegment(paramPoint3d1, paramPoint3d2, paramDouble);
/*      */     
/*  412 */     this.start = paramPoint3d1;
/*  413 */     this.userDefineShape = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShapeCylinderRay(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/*  423 */     this.pickShape = new PickCylinderRay(paramPoint3d, paramVector3d, paramDouble);
/*  424 */     this.start = paramPoint3d;
/*  425 */     this.userDefineShape = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShapeConeSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, double paramDouble) {
/*  435 */     this.pickShape = new PickConeSegment(paramPoint3d1, paramPoint3d2, paramDouble);
/*  436 */     this.start = paramPoint3d1;
/*  437 */     this.userDefineShape = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShapeConeRay(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/*  447 */     this.pickShape = new PickConeRay(paramPoint3d, paramVector3d, paramDouble);
/*  448 */     this.start = paramPoint3d;
/*  449 */     this.userDefineShape = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  454 */   public PickShape getPickShape() { return this.pickShape; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  459 */   public Point3d getStartPosition() { return this.start; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PickResult[] pickAll() {
/*  468 */     null = null;
/*  469 */     switch (this.mode) {
/*      */       case 512:
/*  471 */         return pickAll(this.pickShape);
/*      */       
/*      */       case 256:
/*  474 */         return pickGeomAll(this.pickShape);
/*      */       
/*      */       case 1024:
/*  477 */         return pickGeomAllIntersect(this.pickShape);
/*      */     } 
/*      */     
/*  480 */     throw new RuntimeException("Invalid pick mode");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PickResult pickAny() {
/*  491 */     null = null;
/*  492 */     switch (this.mode) {
/*      */       case 512:
/*  494 */         return pickAny(this.pickShape);
/*      */       
/*      */       case 256:
/*  497 */         return pickGeomAny(this.pickShape);
/*      */       
/*      */       case 1024:
/*  500 */         return pickGeomAnyIntersect(this.pickShape);
/*      */     } 
/*      */     
/*  503 */     throw new RuntimeException("Invalid pick mode");
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
/*      */   public PickResult[] pickAllSorted() {
/*  517 */     null = null;
/*      */ 
/*      */ 
/*      */     
/*  521 */     switch (this.mode) {
/*      */       
/*      */       case 512:
/*  524 */         return pickAllSorted(this.pickShape);
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
/*      */       case 256:
/*  536 */         return pickGeomAllSorted(this.pickShape);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1024:
/*  542 */         return pickGeomAllSortedIntersect(this.pickShape);
/*      */     } 
/*      */     
/*  545 */     throw new RuntimeException("Invalid pick mode");
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
/*      */   public PickResult pickClosest() {
/*  559 */     null = null;
/*  560 */     switch (this.mode) {
/*      */       case 512:
/*  562 */         return pickClosest(this.pickShape);
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
/*      */       case 256:
/*  574 */         return pickGeomClosest(this.pickShape);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1024:
/*  580 */         return pickGeomClosestIntersect(this.pickShape);
/*      */     } 
/*      */     
/*  583 */     throw new RuntimeException("Invalid pick mode");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private PickResult[] pickAll(PickShape paramPickShape) {
/*  589 */     PickResult[] arrayOfPickResult = null;
/*  590 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*      */     
/*  592 */     if (this.pickRootBG != null) {
/*  593 */       arrayOfSceneGraphPath = this.pickRootBG.pickAll(paramPickShape);
/*  594 */     } else if (this.pickRootL != null) {
/*  595 */       arrayOfSceneGraphPath = this.pickRootL.pickAll(paramPickShape);
/*      */     } 
/*  597 */     if (arrayOfSceneGraphPath == null) return null;
/*      */ 
/*      */     
/*  600 */     arrayOfPickResult = new PickResult[arrayOfSceneGraphPath.length];
/*  601 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/*  602 */       arrayOfPickResult[b] = new PickResult(arrayOfSceneGraphPath[b], paramPickShape);
/*      */     }
/*  604 */     return arrayOfPickResult;
/*      */   }
/*      */   
/*      */   private PickResult[] pickAllSorted(PickShape paramPickShape) {
/*  608 */     PickResult[] arrayOfPickResult = null;
/*  609 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*      */     
/*  611 */     if (this.pickRootBG != null) {
/*  612 */       arrayOfSceneGraphPath = this.pickRootBG.pickAllSorted(paramPickShape);
/*  613 */     } else if (this.pickRootL != null) {
/*  614 */       arrayOfSceneGraphPath = this.pickRootL.pickAllSorted(paramPickShape);
/*      */     } 
/*  616 */     if (arrayOfSceneGraphPath == null) return null;
/*      */ 
/*      */     
/*  619 */     arrayOfPickResult = new PickResult[arrayOfSceneGraphPath.length];
/*  620 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/*  621 */       arrayOfPickResult[b] = new PickResult(arrayOfSceneGraphPath[b], paramPickShape);
/*      */     }
/*  623 */     return arrayOfPickResult;
/*      */   }
/*      */   
/*      */   private PickResult pickAny(PickShape paramPickShape) {
/*  627 */     null = null;
/*  628 */     SceneGraphPath sceneGraphPath = null;
/*      */     
/*  630 */     if (this.pickRootBG != null) {
/*  631 */       sceneGraphPath = this.pickRootBG.pickAny(paramPickShape);
/*  632 */     } else if (this.pickRootL != null) {
/*  633 */       sceneGraphPath = this.pickRootL.pickAny(paramPickShape);
/*      */     } 
/*  635 */     if (sceneGraphPath == null) return null;
/*      */ 
/*      */     
/*  638 */     return new PickResult(sceneGraphPath, paramPickShape);
/*      */   }
/*      */ 
/*      */   
/*      */   private PickResult pickClosest(PickShape paramPickShape) {
/*  643 */     null = null;
/*  644 */     SceneGraphPath sceneGraphPath = null;
/*      */     
/*  646 */     if (this.pickRootBG != null) {
/*  647 */       sceneGraphPath = this.pickRootBG.pickClosest(paramPickShape);
/*  648 */     } else if (this.pickRootL != null) {
/*  649 */       sceneGraphPath = this.pickRootL.pickClosest(paramPickShape);
/*      */     } 
/*  651 */     if (sceneGraphPath == null) return null;
/*      */ 
/*      */     
/*  654 */     return new PickResult(sceneGraphPath, paramPickShape);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PickResult[] pickGeomAll(PickShape paramPickShape) {
/*  663 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*  664 */     Node[] arrayOfNode = null;
/*  665 */     byte b2 = 0;
/*      */ 
/*      */     
/*  668 */     if (this.pickRootBG != null) {
/*  669 */       arrayOfSceneGraphPath = this.pickRootBG.pickAll(paramPickShape);
/*  670 */     } else if (this.pickRootL != null) {
/*  671 */       arrayOfSceneGraphPath = this.pickRootL.pickAll(paramPickShape);
/*      */     } 
/*  673 */     if (arrayOfSceneGraphPath == null) return null;
/*      */ 
/*      */     
/*  676 */     boolean[] arrayOfBoolean = new boolean[arrayOfSceneGraphPath.length];
/*      */     
/*  678 */     arrayOfNode = new Node[arrayOfSceneGraphPath.length];
/*  679 */     PickResult[] arrayOfPickResult1 = new PickResult[arrayOfSceneGraphPath.length]; byte b1;
/*  680 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  681 */       arrayOfNode[b1] = arrayOfSceneGraphPath[b1].getObject();
/*  682 */       arrayOfPickResult1[b1] = new PickResult(arrayOfSceneGraphPath[b1], paramPickShape);
/*      */       
/*  684 */       if (arrayOfNode[b1] instanceof Shape3D) {
/*  685 */         arrayOfBoolean[b1] = ((Shape3D)arrayOfNode[b1]).intersect(arrayOfSceneGraphPath[b1], paramPickShape);
/*  686 */       } else if (arrayOfNode[b1] instanceof Morph) {
/*  687 */         arrayOfBoolean[b1] = ((Morph)arrayOfNode[b1]).intersect(arrayOfSceneGraphPath[b1], paramPickShape);
/*      */       } 
/*  689 */       if (arrayOfBoolean[b1] == true) b2++;
/*      */     
/*      */     } 
/*  692 */     if (b2 == 0) return null;
/*      */     
/*  694 */     PickResult[] arrayOfPickResult2 = new PickResult[b2];
/*  695 */     b2 = 0;
/*  696 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  697 */       if (arrayOfBoolean[b1] == true) {
/*  698 */         arrayOfPickResult1[b2++] = arrayOfPickResult1[b1];
/*      */       }
/*      */     } 
/*  701 */     return arrayOfPickResult1;
/*      */   }
/*      */   
/*      */   private PickResult[] pickGeomAllSorted(PickShape paramPickShape) {
/*  705 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*  706 */     Node[] arrayOfNode = null;
/*  707 */     byte b2 = 0;
/*  708 */     double[] arrayOfDouble1 = new double[1];
/*      */ 
/*      */     
/*  711 */     if (this.pickRootBG != null) {
/*  712 */       arrayOfSceneGraphPath = this.pickRootBG.pickAll(paramPickShape);
/*  713 */     } else if (this.pickRootL != null) {
/*  714 */       arrayOfSceneGraphPath = this.pickRootL.pickAll(paramPickShape);
/*      */     } 
/*  716 */     if (arrayOfSceneGraphPath == null) return null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  723 */     boolean[] arrayOfBoolean = new boolean[arrayOfSceneGraphPath.length];
/*  724 */     double[] arrayOfDouble2 = new double[arrayOfSceneGraphPath.length];
/*  725 */     arrayOfNode = new Node[arrayOfSceneGraphPath.length];
/*  726 */     PickResult[] arrayOfPickResult1 = new PickResult[arrayOfSceneGraphPath.length];
/*      */     byte b1;
/*  728 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  729 */       arrayOfNode[b1] = arrayOfSceneGraphPath[b1].getObject();
/*  730 */       arrayOfPickResult1[b1] = new PickResult(arrayOfSceneGraphPath[b1], paramPickShape);
/*  731 */       if (arrayOfNode[b1] instanceof Shape3D) {
/*  732 */         arrayOfBoolean[b1] = ((Shape3D)arrayOfNode[b1]).intersect(arrayOfSceneGraphPath[b1], paramPickShape, arrayOfDouble1);
/*      */         
/*  734 */         arrayOfDouble2[b1] = arrayOfDouble1[0];
/*  735 */       } else if (arrayOfNode[b1] instanceof Morph) {
/*  736 */         arrayOfBoolean[b1] = ((Morph)arrayOfNode[b1]).intersect(arrayOfSceneGraphPath[b1], paramPickShape, arrayOfDouble1);
/*      */         
/*  738 */         arrayOfDouble2[b1] = arrayOfDouble1[0];
/*      */       } 
/*  740 */       if (arrayOfBoolean[b1] == true) b2++; 
/*      */     } 
/*  742 */     if (b2 == 0) return null;
/*      */     
/*  744 */     PickResult[] arrayOfPickResult2 = new PickResult[b2];
/*  745 */     double[] arrayOfDouble3 = new double[b2];
/*  746 */     b2 = 0;
/*  747 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  748 */       if (arrayOfBoolean[b1] == true) {
/*  749 */         arrayOfDouble3[b2] = arrayOfDouble2[b1];
/*  750 */         arrayOfPickResult2[b2++] = arrayOfPickResult1[b1];
/*      */       } 
/*      */     } 
/*  753 */     if (b2 > 1) {
/*  754 */       return sortPickResults(arrayOfPickResult2, arrayOfDouble3);
/*      */     }
/*  756 */     return arrayOfPickResult2;
/*      */   }
/*      */ 
/*      */   
/*      */   private PickResult pickGeomAny(PickShape paramPickShape) {
/*  761 */     Node node = null;
/*      */     
/*  763 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*      */     
/*  765 */     if (this.pickRootBG != null) {
/*  766 */       arrayOfSceneGraphPath = this.pickRootBG.pickAll(paramPickShape);
/*  767 */     } else if (this.pickRootL != null) {
/*  768 */       arrayOfSceneGraphPath = this.pickRootL.pickAll(paramPickShape);
/*      */     } 
/*      */     
/*  771 */     if (arrayOfSceneGraphPath == null) return null;
/*      */     
/*  773 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/*  774 */       node = arrayOfSceneGraphPath[b].getObject();
/*  775 */       PickResult pickResult = new PickResult(arrayOfSceneGraphPath[b], paramPickShape);
/*  776 */       if (node instanceof Shape3D) {
/*  777 */         if (((Shape3D)node).intersect(arrayOfSceneGraphPath[b], paramPickShape)) {
/*  778 */           return pickResult;
/*      */         }
/*  780 */       } else if (node instanceof Morph && (
/*  781 */         (Morph)node).intersect(arrayOfSceneGraphPath[b], paramPickShape)) {
/*  782 */         return pickResult;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  787 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private PickResult pickGeomClosest(PickShape paramPickShape) {
/*  792 */     PickResult[] arrayOfPickResult = pickGeomAllSorted(paramPickShape);
/*  793 */     if (arrayOfPickResult == null) {
/*  794 */       return null;
/*      */     }
/*  796 */     return arrayOfPickResult[0];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PickResult[] pickGeomAllIntersect(PickShape paramPickShape) {
/*  805 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*  806 */     Object object = null;
/*  807 */     byte b2 = 0;
/*      */ 
/*      */     
/*  810 */     if (this.pickRootBG != null) {
/*  811 */       arrayOfSceneGraphPath = this.pickRootBG.pickAll(paramPickShape);
/*  812 */     } else if (this.pickRootL != null) {
/*  813 */       arrayOfSceneGraphPath = this.pickRootL.pickAll(paramPickShape);
/*      */     } 
/*  815 */     if (arrayOfSceneGraphPath == null) return null;
/*      */ 
/*      */     
/*  818 */     boolean[] arrayOfBoolean = new boolean[arrayOfSceneGraphPath.length];
/*      */     
/*  820 */     PickResult[] arrayOfPickResult1 = new PickResult[arrayOfSceneGraphPath.length]; byte b1;
/*  821 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  822 */       arrayOfPickResult1[b1] = new PickResult(arrayOfSceneGraphPath[b1], paramPickShape);
/*  823 */       if (arrayOfPickResult1[b1].numIntersections() > 0) {
/*  824 */         arrayOfBoolean[b1] = true;
/*  825 */         b2++;
/*      */       } 
/*      */     } 
/*      */     
/*  829 */     if (b2 == 0) return null;
/*      */     
/*  831 */     PickResult[] arrayOfPickResult2 = new PickResult[b2];
/*  832 */     b2 = 0;
/*  833 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  834 */       if (arrayOfBoolean[b1] == true) {
/*  835 */         arrayOfPickResult1[b2++] = arrayOfPickResult1[b1];
/*      */       }
/*      */     } 
/*  838 */     return arrayOfPickResult1;
/*      */   }
/*      */   
/*      */   private PickResult[] pickGeomAllSortedIntersect(PickShape paramPickShape) {
/*  842 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*  843 */     Object object = null;
/*  844 */     byte b2 = 0;
/*  845 */     double[] arrayOfDouble1 = new double[1];
/*      */ 
/*      */     
/*  848 */     if (this.pickRootBG != null) {
/*  849 */       arrayOfSceneGraphPath = this.pickRootBG.pickAll(paramPickShape);
/*  850 */     } else if (this.pickRootL != null) {
/*  851 */       arrayOfSceneGraphPath = this.pickRootL.pickAll(paramPickShape);
/*      */     } 
/*  853 */     if (arrayOfSceneGraphPath == null) return null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  861 */     boolean[] arrayOfBoolean = new boolean[arrayOfSceneGraphPath.length];
/*  862 */     double[] arrayOfDouble2 = new double[arrayOfSceneGraphPath.length];
/*      */     
/*  864 */     PickResult[] arrayOfPickResult1 = new PickResult[arrayOfSceneGraphPath.length]; byte b1;
/*  865 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  866 */       arrayOfPickResult1[b1] = new PickResult(arrayOfSceneGraphPath[b1], paramPickShape);
/*  867 */       int i = arrayOfPickResult1[b1].numIntersections();
/*  868 */       if (i > 0) {
/*      */         
/*  870 */         arrayOfBoolean[b1] = true;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  875 */         boolean bool = false;
/*  876 */         double d = arrayOfPickResult1[b1].getIntersection(0).getDistance();
/*  877 */         byte b3 = 0;
/*  878 */         for (byte b4 = 1; b4 < i; b4++) {
/*      */ 
/*      */           
/*  881 */           double d1 = arrayOfPickResult1[b1].getIntersection(b4).getDistance();
/*  882 */           if (d > d1) {
/*  883 */             d = d1;
/*  884 */             b3 = b4;
/*  885 */             bool = true;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  890 */         if (bool) {
/*      */           
/*  892 */           PickIntersection pickIntersection1 = arrayOfPickResult1[b1].getIntersection(0);
/*  893 */           PickIntersection pickIntersection2 = arrayOfPickResult1[b1].getIntersection(b3);
/*  894 */           (arrayOfPickResult1[b1]).intersections.set(0, pickIntersection2);
/*  895 */           (arrayOfPickResult1[b1]).intersections.set(b3, pickIntersection1);
/*      */         } 
/*      */         
/*  898 */         arrayOfDouble2[b1] = arrayOfPickResult1[b1].getIntersection(0).getDistance();
/*  899 */         b2++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  908 */     if (b2 == 0) return null;
/*      */     
/*  910 */     PickResult[] arrayOfPickResult2 = new PickResult[b2];
/*  911 */     double[] arrayOfDouble3 = new double[b2];
/*  912 */     b2 = 0;
/*  913 */     for (b1 = 0; b1 < arrayOfSceneGraphPath.length; b1++) {
/*  914 */       if (arrayOfBoolean[b1] == true) {
/*  915 */         arrayOfDouble3[b2] = arrayOfDouble2[b1];
/*  916 */         arrayOfPickResult2[b2++] = arrayOfPickResult1[b1];
/*      */       } 
/*      */     } 
/*      */     
/*  920 */     if (b2 > 1) {
/*  921 */       return sortPickResults(arrayOfPickResult2, arrayOfDouble3);
/*      */     }
/*  923 */     return arrayOfPickResult2;
/*      */   }
/*      */ 
/*      */   
/*      */   private PickResult pickGeomClosestIntersect(PickShape paramPickShape) {
/*  928 */     PickResult[] arrayOfPickResult = pickGeomAllSortedIntersect(paramPickShape);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  937 */     if (arrayOfPickResult == null) {
/*  938 */       return null;
/*      */     }
/*  940 */     return arrayOfPickResult[0];
/*      */   }
/*      */   
/*      */   private PickResult pickGeomAnyIntersect(PickShape paramPickShape) {
/*  944 */     Object object = null;
/*      */     
/*  946 */     SceneGraphPath[] arrayOfSceneGraphPath = null;
/*      */     
/*  948 */     if (this.pickRootBG != null) {
/*  949 */       arrayOfSceneGraphPath = this.pickRootBG.pickAll(paramPickShape);
/*  950 */     } else if (this.pickRootL != null) {
/*  951 */       arrayOfSceneGraphPath = this.pickRootL.pickAll(paramPickShape);
/*      */     } 
/*  953 */     if (arrayOfSceneGraphPath == null) return null; 
/*  954 */     for (byte b = 0; b < arrayOfSceneGraphPath.length; b++) {
/*  955 */       PickResult pickResult = new PickResult(arrayOfSceneGraphPath[b], paramPickShape);
/*  956 */       pickResult.setFirstIntersectOnly(true);
/*  957 */       if (pickResult.numIntersections() > 0) {
/*  958 */         return pickResult;
/*      */       }
/*      */     } 
/*      */     
/*  962 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PickResult[] sortPickResults(PickResult[] paramArrayOfPickResult, double[] paramArrayOfDouble) {
/*  969 */     int[] arrayOfInt = new int[paramArrayOfPickResult.length];
/*  970 */     PickResult[] arrayOfPickResult = new PickResult[paramArrayOfPickResult.length];
/*      */     
/*      */     byte b;
/*  973 */     for (b = 0; b < paramArrayOfPickResult.length; b++) {
/*  974 */       arrayOfInt[b] = b;
/*      */     }
/*      */     
/*  977 */     quicksort(0, paramArrayOfDouble.length - 1, paramArrayOfDouble, arrayOfInt);
/*      */ 
/*      */     
/*  980 */     for (b = 0; b < paramArrayOfPickResult.length; b++) {
/*  981 */       arrayOfPickResult[b] = paramArrayOfPickResult[arrayOfInt[b]];
/*      */     }
/*  983 */     return arrayOfPickResult;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void quicksort(int paramInt1, int paramInt2, double[] paramArrayOfDouble, int[] paramArrayOfInt) {
/*  991 */     int i = paramInt1;
/*  992 */     int j = paramInt2;
/*  993 */     double d = paramArrayOfDouble[(paramInt1 + paramInt2) / 2];
/*      */     do {
/*  995 */       for (; paramArrayOfDouble[i] < d; i++);
/*  996 */       for (; d < paramArrayOfDouble[j]; j--);
/*  997 */       if (i > j)
/*  998 */         continue;  double d1 = paramArrayOfDouble[i];
/*  999 */       paramArrayOfDouble[i] = paramArrayOfDouble[j];
/* 1000 */       paramArrayOfDouble[j] = d1;
/*      */       
/* 1002 */       int k = paramArrayOfInt[i];
/* 1003 */       paramArrayOfInt[i] = paramArrayOfInt[j];
/* 1004 */       paramArrayOfInt[j] = k;
/* 1005 */       i++;
/* 1006 */       j--;
/*      */     }
/* 1008 */     while (i <= j);
/*      */     
/* 1010 */     if (paramInt1 < j) quicksort(paramInt1, j, paramArrayOfDouble, paramArrayOfInt); 
/* 1011 */     if (paramInt1 < paramInt2) quicksort(i, paramInt2, paramArrayOfDouble, paramArrayOfInt); 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\picking\PickTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */