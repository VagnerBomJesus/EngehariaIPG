/*      */ package com.sun.j3d.utils.picking;
/*      */ 
/*      */ import com.sun.j3d.internal.Distance;
/*      */ import java.util.ArrayList;
/*      */ import javax.media.j3d.BoundingBox;
/*      */ import javax.media.j3d.BoundingPolytope;
/*      */ import javax.media.j3d.BoundingSphere;
/*      */ import javax.media.j3d.Bounds;
/*      */ import javax.media.j3d.CompressedGeometry;
/*      */ import javax.media.j3d.Geometry;
/*      */ import javax.media.j3d.GeometryArray;
/*      */ import javax.media.j3d.IndexedGeometryArray;
/*      */ import javax.media.j3d.IndexedLineArray;
/*      */ import javax.media.j3d.IndexedLineStripArray;
/*      */ import javax.media.j3d.IndexedPointArray;
/*      */ import javax.media.j3d.IndexedQuadArray;
/*      */ import javax.media.j3d.IndexedTriangleArray;
/*      */ import javax.media.j3d.IndexedTriangleFanArray;
/*      */ import javax.media.j3d.IndexedTriangleStripArray;
/*      */ import javax.media.j3d.LineArray;
/*      */ import javax.media.j3d.LineStripArray;
/*      */ import javax.media.j3d.Morph;
/*      */ import javax.media.j3d.Node;
/*      */ import javax.media.j3d.PickBounds;
/*      */ import javax.media.j3d.PickCone;
/*      */ import javax.media.j3d.PickConeSegment;
/*      */ import javax.media.j3d.PickCylinder;
/*      */ import javax.media.j3d.PickCylinderSegment;
/*      */ import javax.media.j3d.PickPoint;
/*      */ import javax.media.j3d.PickRay;
/*      */ import javax.media.j3d.PickSegment;
/*      */ import javax.media.j3d.PickShape;
/*      */ import javax.media.j3d.PointArray;
/*      */ import javax.media.j3d.QuadArray;
/*      */ import javax.media.j3d.SceneGraphPath;
/*      */ import javax.media.j3d.Shape3D;
/*      */ import javax.media.j3d.Transform3D;
/*      */ import javax.media.j3d.TriangleArray;
/*      */ import javax.media.j3d.TriangleFanArray;
/*      */ import javax.media.j3d.TriangleStripArray;
/*      */ import javax.vecmath.Point2d;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Point4d;
/*      */ import javax.vecmath.Vector3d;
/*      */ import javax.vecmath.Vector4d;
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
/*      */ public class PickResult
/*      */ {
/*      */   public static final int SHAPE3D = 1;
/*      */   public static final int MORPH = 2;
/*      */   public static final int PRIMITIVE = 4;
/*      */   public static final int LINK = 8;
/*      */   public static final int GROUP = 16;
/*      */   public static final int TRANSFORM_GROUP = 32;
/*      */   public static final int BRANCH_GROUP = 64;
/*      */   public static final int SWITCH = 128;
/*      */   static boolean debug = false;
/*      */   private boolean firstIntersectOnly = false;
/*  191 */   private SceneGraphPath pickedSceneGraphPath = null;
/*      */ 
/*      */   
/*  194 */   private Node pickedNode = null;
/*      */ 
/*      */   
/*  197 */   private GeometryArray[] geometryArrays = null;
/*      */ 
/*      */   
/*  200 */   private Shape3D[] compressGeomShape3Ds = null;
/*      */ 
/*      */   
/*  203 */   private Transform3D localToVWorld = null;
/*      */ 
/*      */   
/*  206 */   private PickShape pickShape = null;
/*      */   
/*  208 */   private int pickShapeType = -1;
/*  209 */   private Vector3d pickShapeDir = null;
/*  210 */   private Point3d pickShapeStart = null;
/*  211 */   private Point3d pickShapeEnd = null;
/*  212 */   private Bounds pickShapeBounds = null;
/*      */   
/*  214 */   static final Point3d zeroPnt = new Point3d();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  219 */   ArrayList intersections = null;
/*      */   
/*      */   static final double FUZZ = 1.0E-6D;
/*      */   
/*      */   static final int PICK_SHAPE_RAY = 1;
/*      */   
/*      */   static final int PICK_SHAPE_SEGMENT = 2;
/*      */   
/*      */   static final int PICK_SHAPE_POINT = 3;
/*      */   
/*      */   static final int PICK_SHAPE_BOUNDING_BOX = 4;
/*      */   
/*      */   static final int PICK_SHAPE_BOUNDING_SPHERE = 5;
/*      */   
/*      */   static final int PICK_SHAPE_BOUNDING_POLYTOPE = 6;
/*      */   
/*      */   static final int PICK_SHAPE_CYLINDER = 7;
/*      */   
/*      */   static final int PICK_SHAPE_CONE = 8;
/*      */   
/*      */   static final double EPS = 1.0E-13D;
/*      */ 
/*      */   
/*      */   PickResult() {}
/*      */ 
/*      */   
/*      */   public PickResult(SceneGraphPath paramSceneGraphPath, PickShape paramPickShape) {
/*  246 */     this.pickedSceneGraphPath = paramSceneGraphPath;
/*  247 */     this.pickedNode = paramSceneGraphPath.getObject();
/*  248 */     this.localToVWorld = paramSceneGraphPath.getTransform();
/*  249 */     this.pickShape = paramPickShape;
/*  250 */     initPickShape();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PickResult(Node paramNode, Transform3D paramTransform3D, PickShape paramPickShape) {
/*  261 */     if (paramNode instanceof Shape3D || paramNode instanceof Morph) {
/*  262 */       this.pickedNode = paramNode;
/*  263 */       this.localToVWorld = paramTransform3D;
/*  264 */       this.pickShape = paramPickShape;
/*  265 */       initPickShape();
/*      */     } else {
/*  267 */       throw new IllegalArgumentException();
/*      */     } 
/*      */   }
/*      */   
/*      */   void initPickShape() {
/*  272 */     if (this.pickShape instanceof PickRay)
/*  273 */     { if (this.pickShapeStart == null) this.pickShapeStart = new Point3d(); 
/*  274 */       if (this.pickShapeDir == null) this.pickShapeDir = new Vector3d(); 
/*  275 */       ((PickRay)this.pickShape).get(this.pickShapeStart, this.pickShapeDir);
/*  276 */       this.pickShapeType = 1; }
/*  277 */     else if (this.pickShape instanceof PickSegment)
/*  278 */     { if (this.pickShapeStart == null) this.pickShapeStart = new Point3d(); 
/*  279 */       if (this.pickShapeEnd == null) this.pickShapeEnd = new Point3d(); 
/*  280 */       if (this.pickShapeDir == null) this.pickShapeDir = new Vector3d(); 
/*  281 */       ((PickSegment)this.pickShape).get(this.pickShapeStart, this.pickShapeEnd);
/*  282 */       this.pickShapeDir.set(this.pickShapeEnd.x - this.pickShapeStart.x, this.pickShapeEnd.y - this.pickShapeStart.y, this.pickShapeEnd.z - this.pickShapeStart.z);
/*      */ 
/*      */       
/*  285 */       this.pickShapeType = 2; }
/*  286 */     else if (this.pickShape instanceof PickBounds)
/*  287 */     { this.pickShapeBounds = ((PickBounds)this.pickShape).get();
/*  288 */       if (this.pickShapeBounds instanceof BoundingBox)
/*  289 */       { this.pickShapeType = 4; }
/*  290 */       else if (this.pickShapeBounds instanceof BoundingSphere)
/*  291 */       { this.pickShapeType = 5; }
/*  292 */       else if (this.pickShapeBounds instanceof BoundingPolytope)
/*  293 */       { this.pickShapeType = 6; }  }
/*  294 */     else { if (this.pickShape instanceof PickPoint)
/*  295 */         throw new RuntimeException("PickPoint doesn't make sense for geometry-based picking. Java 3D doesn't have spatial information of the surface. Should use PickBounds with BoundingSphere and set radius to a epsilon tolerance."); 
/*  296 */       if (this.pickShape instanceof PickCylinder) {
/*  297 */         this.pickShapeType = 7;
/*  298 */       } else if (this.pickShape instanceof PickCone) {
/*  299 */         this.pickShapeType = 8;
/*      */       } else {
/*  301 */         throw new RuntimeException("PickShape not supported for intersection");
/*      */       }  }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  311 */   public SceneGraphPath getSceneGraphPath() { return this.pickedSceneGraphPath; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  318 */   public Transform3D getLocalToVworld() { return this.localToVWorld; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GeometryArray getGeometryArray() {
/*  324 */     if (this.geometryArrays == null) {
/*  325 */       storeGeometry();
/*      */     }
/*  327 */     return this.geometryArrays[0];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public GeometryArray[] getGeometryArrays() {
/*  333 */     if (this.geometryArrays == null) {
/*  334 */       storeGeometry();
/*      */     }
/*  336 */     return this.geometryArrays;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int numGeometryArrays() {
/*  342 */     if (this.geometryArrays == null) {
/*  343 */       storeGeometry();
/*      */     }
/*  345 */     return this.geometryArrays.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int numCompressedGeometryShape3Ds() {
/*  352 */     if (this.geometryArrays == null) {
/*  353 */       storeGeometry();
/*      */     }
/*  355 */     if (this.compressGeomShape3Ds == null) {
/*  356 */       return 0;
/*      */     }
/*  358 */     return this.compressGeomShape3Ds.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Shape3D[] getCompressedGeometryShape3Ds() {
/*  366 */     if (this.geometryArrays == null) {
/*  367 */       storeGeometry();
/*      */     }
/*  369 */     if (this.compressGeomShape3Ds == null) {
/*  370 */       return null;
/*      */     }
/*  372 */     return this.compressGeomShape3Ds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  379 */   public PickShape getPickShape() { return this.pickShape; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  387 */   public void setFirstIntersectOnly(boolean paramBoolean) { this.firstIntersectOnly = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   public boolean getFirstPickEnable() { return this.firstIntersectOnly; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int numIntersections() {
/*  399 */     if (this.intersections == null) {
/*  400 */       generateIntersections();
/*      */     }
/*  402 */     return this.intersections.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PickIntersection getIntersection(int paramInt) {
/*  410 */     if (this.intersections == null) {
/*  411 */       generateIntersections();
/*      */     }
/*  413 */     return (PickIntersection)this.intersections.get(paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PickIntersection getClosestIntersection(Point3d paramPoint3d) {
/*  421 */     PickIntersection pickIntersection1 = null;
/*  422 */     PickIntersection pickIntersection2 = null;
/*  423 */     Point3d point3d = null;
/*  424 */     double d1 = Double.MAX_VALUE;
/*  425 */     double d2 = 0.0D;
/*      */     
/*  427 */     if (paramPoint3d == null) return null;
/*      */     
/*  429 */     if (this.intersections == null) {
/*  430 */       generateIntersections();
/*      */     }
/*      */     
/*  433 */     for (byte b = 0; b < this.intersections.size(); b++) {
/*  434 */       if (null != (pickIntersection2 = getIntersection(b)) && null != (point3d = pickIntersection2.getPointCoordinatesVW())) {
/*      */         
/*  436 */         d2 = paramPoint3d.distance(point3d);
/*  437 */         if (d2 < d1) {
/*  438 */           pickIntersection1 = pickIntersection2;
/*  439 */           d1 = d2;
/*      */         } 
/*      */       } 
/*      */     } 
/*  443 */     return pickIntersection1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  452 */     String str = new String("PickResult: sgp:" + this.pickedSceneGraphPath + "\n");
/*  453 */     if (this.pickedNode != null) str = str + " node:" + this.pickedNode;
/*      */ 
/*      */ 
/*      */     
/*  457 */     if (this.intersections == null) {
/*  458 */       generateIntersections();
/*      */     }
/*      */     
/*  461 */     if (this.intersections.size() > 0) {
/*  462 */       for (byte b = 0; b < this.intersections.size(); b++) {
/*  463 */         str = str + "\n";
/*  464 */         str = str + ((PickIntersection)this.intersections.get(b)).toString2();
/*      */       } 
/*      */     }
/*      */     
/*  468 */     return str;
/*      */   }
/*      */ 
/*      */   
/*      */   private void storeGeometry() {
/*  473 */     if (this.pickedNode instanceof Morph) {
/*  474 */       this.geometryArrays = new GeometryArray[1];
/*  475 */       this.geometryArrays[0] = ((Morph)this.pickedNode).getGeometryArray(0);
/*      */     }
/*  477 */     else if (this.pickedNode instanceof Shape3D) {
/*  478 */       Shape3D shape3D = (Shape3D)this.pickedNode;
/*  479 */       ArrayList arrayList = new ArrayList(); byte b;
/*  480 */       for (b = 0; b < shape3D.numGeometries(); b++) {
/*  481 */         Geometry geometry = shape3D.getGeometry(b);
/*  482 */         if (geometry instanceof CompressedGeometry) {
/*  483 */           Shape3D[] arrayOfShape3D = ((CompressedGeometry)geometry).decompress();
/*      */ 
/*      */           
/*  486 */           if (arrayOfShape3D != null) {
/*  487 */             for (byte b1 = 0; b1 < arrayOfShape3D.length; b1++) {
/*  488 */               for (byte b2 = 0; b2 < arrayOfShape3D[b1].numGeometries(); b2++) {
/*  489 */                 arrayList.add(arrayOfShape3D[b1].getGeometry(b2));
/*      */               }
/*      */             } 
/*      */           }
/*  493 */           if (this.compressGeomShape3Ds == null) {
/*      */             
/*  495 */             this.compressGeomShape3Ds = arrayOfShape3D;
/*      */           } else {
/*      */             
/*  498 */             Shape3D[] arrayOfShape3D1 = this.compressGeomShape3Ds;
/*  499 */             int i = arrayOfShape3D1.length + arrayOfShape3D.length;
/*  500 */             this.compressGeomShape3Ds = new Shape3D[i];
/*  501 */             System.arraycopy(arrayOfShape3D1, 0, this.compressGeomShape3Ds, 0, arrayOfShape3D1.length);
/*      */             
/*  503 */             System.arraycopy(arrayOfShape3D, 0, this.compressGeomShape3Ds, arrayOfShape3D1.length, arrayOfShape3D.length);
/*      */           }
/*      */         
/*  506 */         } else if (geometry instanceof GeometryArray) {
/*  507 */           arrayList.add(geometry);
/*      */         } 
/*      */       } 
/*  510 */       this.geometryArrays = new GeometryArray[arrayList.size()];
/*  511 */       for (b = 0; b < arrayList.size(); b++) {
/*  512 */         this.geometryArrays[b] = (GeometryArray)arrayList.get(b);
/*      */       }
/*      */     } 
/*  515 */     if (this.geometryArrays == null) {
/*  516 */       if (this.pickedNode instanceof Shape3D) {
/*  517 */         Shape3D shape3D = (Shape3D)this.pickedNode;
/*      */       }
/*  519 */       throw new RuntimeException("Type of the picked node is not supported");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getObject() {
/*  526 */     if (this.pickedNode == null) {
/*  527 */       storeNode();
/*      */     }
/*  529 */     return this.pickedNode;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  534 */   void setObject(Node paramNode) { this.pickedNode = paramNode; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getNode(int paramInt) {
/*  542 */     if (this.pickedNode == null) {
/*  543 */       storeNode();
/*      */     }
/*  545 */     if (this.pickedNode instanceof Shape3D && (paramInt & true) != 0) {
/*  546 */       if (debug) System.out.println("Shape3D found"); 
/*  547 */       return this.pickedNode;
/*      */     } 
/*  549 */     if (this.pickedNode instanceof Morph && (paramInt & 0x2) != 0) {
/*  550 */       if (debug) System.out.println("Morph found"); 
/*  551 */       return this.pickedNode;
/*      */     } 
/*      */     
/*  554 */     if (this.pickedSceneGraphPath == null) {
/*  555 */       return null;
/*      */     }
/*  557 */     for (int i = this.pickedSceneGraphPath.nodeCount() - 1; i >= 0; i--) {
/*  558 */       Node node = this.pickedSceneGraphPath.getNode(i);
/*  559 */       if (debug) System.out.println("looking at node " + node);
/*      */       
/*  561 */       if (node instanceof com.sun.j3d.utils.geometry.Primitive && (paramInt & 0x4) != 0) {
/*      */         
/*  563 */         if (debug) System.out.println("Primitive found"); 
/*  564 */         return node;
/*      */       } 
/*  566 */       if (node instanceof javax.media.j3d.Link && (paramInt & 0x8) != 0) {
/*  567 */         if (debug) System.out.println("Link found"); 
/*  568 */         return node;
/*      */       } 
/*  570 */       if (node instanceof javax.media.j3d.Switch && (paramInt & 0x80) != 0) {
/*  571 */         if (debug) System.out.println("Switch found"); 
/*  572 */         return node;
/*      */       } 
/*  574 */       if (node instanceof javax.media.j3d.TransformGroup && (paramInt & 0x20) != 0) {
/*      */         
/*  576 */         if (debug) System.out.println("xform group found"); 
/*  577 */         return node;
/*      */       } 
/*  579 */       if (node instanceof javax.media.j3d.BranchGroup && (paramInt & 0x40) != 0) {
/*      */         
/*  581 */         if (debug) System.out.println("Branch group found"); 
/*  582 */         return node;
/*      */       } 
/*  584 */       if (node instanceof javax.media.j3d.Group && (paramInt & 0x10) != 0) {
/*  585 */         if (debug) System.out.println("Group found"); 
/*  586 */         return node;
/*      */       } 
/*      */     } 
/*      */     
/*  590 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   void storeNode() {
/*  595 */     if (this.pickedSceneGraphPath == null) {
/*  596 */       throw new RuntimeException("SceneGraphPath missing");
/*      */     }
/*  598 */     this.pickedNode = this.pickedSceneGraphPath.getObject();
/*      */   }
/*      */ 
/*      */   
/*      */   boolean generateIntersections() {
/*  603 */     if (this.geometryArrays == null) {
/*  604 */       storeGeometry();
/*      */     }
/*  606 */     this.intersections = new ArrayList();
/*  607 */     byte b1 = 0;
/*      */     
/*  609 */     for (byte b2 = 0; b2 < this.geometryArrays.length; b2++) {
/*  610 */       if (intersect(b2, this.firstIntersectOnly)) {
/*  611 */         if (this.firstIntersectOnly) {
/*  612 */           return true;
/*      */         }
/*  614 */         b1++;
/*      */       } 
/*      */     } 
/*      */     
/*  618 */     return (b1 > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final boolean intersect(int paramInt, boolean paramBoolean) {
/*  628 */     GeometryArray geometryArray = this.geometryArrays[paramInt];
/*  629 */     int i = geometryArray.getVertexCount();
/*  630 */     double[] arrayOfDouble = null;
/*  631 */     float[] arrayOfFloat = null;
/*  632 */     Point3d[] arrayOfPoint3d1 = null;
/*  633 */     Point3f[] arrayOfPoint3f = null;
/*  634 */     int j = geometryArray.getVertexFormat();
/*      */     
/*  636 */     boolean bool = false;
/*      */     
/*  638 */     if ((j & 0x80) == 0) {
/*  639 */       arrayOfDouble = new double[i * 3];
/*  640 */       geometryArray.getCoordinates(0, arrayOfDouble);
/*      */     
/*      */     }
/*  643 */     else if ((j & 0x100) == 0) {
/*  644 */       arrayOfDouble = geometryArray.getCoordRefDouble();
/*      */       
/*  646 */       if (arrayOfDouble == null) {
/*  647 */         arrayOfFloat = geometryArray.getCoordRefFloat();
/*  648 */         if (arrayOfFloat == null) {
/*  649 */           arrayOfPoint3f = geometryArray.getCoordRef3f();
/*  650 */           if (arrayOfPoint3f == null) {
/*  651 */             arrayOfPoint3d1 = geometryArray.getCoordRef3d();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  657 */       arrayOfFloat = geometryArray.getInterleavedVertices();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  662 */     Point3d[] arrayOfPoint3d2 = new Point3d[i];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  672 */     if (debug) {
/*  673 */       System.out.println("localToVWorld = " + this.localToVWorld);
/*      */     }
/*  675 */     if ((j & 0x100) == 0) {
/*  676 */       if (arrayOfDouble != null) {
/*  677 */         byte b1 = 0;
/*  678 */         for (byte b2 = 0; b2 < i; b2++)
/*      */         {
/*      */           
/*  681 */           arrayOfPoint3d2[b2] = new Point3d();
/*  682 */           (arrayOfPoint3d2[b2]).x = arrayOfDouble[b1++];
/*  683 */           (arrayOfPoint3d2[b2]).y = arrayOfDouble[b1++];
/*  684 */           (arrayOfPoint3d2[b2]).z = arrayOfDouble[b1++];
/*      */           
/*  686 */           this.localToVWorld.transform(arrayOfPoint3d2[b2]);
/*      */         }
/*      */       
/*  689 */       } else if (arrayOfFloat != null) {
/*  690 */         byte b1 = 0;
/*  691 */         for (byte b2 = 0; b2 < i; b2++)
/*      */         {
/*      */           
/*  694 */           arrayOfPoint3d2[b2] = new Point3d();
/*  695 */           (arrayOfPoint3d2[b2]).x = arrayOfFloat[b1++];
/*  696 */           (arrayOfPoint3d2[b2]).y = arrayOfFloat[b1++];
/*  697 */           (arrayOfPoint3d2[b2]).z = arrayOfFloat[b1++];
/*      */           
/*  699 */           this.localToVWorld.transform(arrayOfPoint3d2[b2]);
/*      */         }
/*      */       
/*  702 */       } else if (arrayOfPoint3f != null) {
/*  703 */         for (byte b = 0; b < i; b++) {
/*      */ 
/*      */           
/*  706 */           arrayOfPoint3d2[b] = new Point3d();
/*  707 */           arrayOfPoint3d2[b].set(arrayOfPoint3f[b]);
/*  708 */           this.localToVWorld.transform(arrayOfPoint3d2[b]);
/*      */         } 
/*      */       } else {
/*      */         
/*  712 */         for (byte b = 0; b < i; b++)
/*      */         {
/*      */           
/*  715 */           arrayOfPoint3d2[b] = new Point3d();
/*  716 */           arrayOfPoint3d2[b].set(arrayOfPoint3d1[b]);
/*  717 */           this.localToVWorld.transform(arrayOfPoint3d2[b]);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  723 */       int k = 0;
/*  724 */       if ((j & 0x4) == 4) {
/*  725 */         k += true;
/*      */       }
/*  727 */       else if ((j & 0xC) == 12) {
/*  728 */         k += true;
/*      */       } 
/*  730 */       if ((j & 0x2) != 0)
/*  731 */         k += true; 
/*  732 */       if ((j & 0x20) == 32) {
/*  733 */         k += 2 * geometryArray.getTexCoordSetCount();
/*      */       }
/*  735 */       else if ((j & 0x40) == 64) {
/*  736 */         k += 3 * geometryArray.getTexCoordSetCount();
/*      */       } 
/*  738 */       int m = k + 3;
/*  739 */       for (byte b = 0; b < i; b++) {
/*      */ 
/*      */         
/*  742 */         arrayOfPoint3d2[b] = new Point3d();
/*  743 */         (arrayOfPoint3d2[b]).x = arrayOfFloat[k];
/*  744 */         (arrayOfPoint3d2[b]).y = arrayOfFloat[k + 1];
/*  745 */         (arrayOfPoint3d2[b]).z = arrayOfFloat[k + 2];
/*      */         
/*  747 */         this.localToVWorld.transform(arrayOfPoint3d2[b]);
/*  748 */         k += m;
/*      */       } 
/*      */     } 
/*      */     
/*  752 */     PickIntersection pickIntersection = new PickIntersection(this, geometryArray);
/*      */     
/*  754 */     if (geometryArray instanceof PointArray) {
/*  755 */       bool = intersectPA((PointArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*  756 */     } else if (geometryArray instanceof IndexedPointArray) {
/*  757 */       pickIntersection.iGeom = (IndexedGeometryArray)geometryArray;
/*  758 */       bool = intersectIPA((IndexedPointArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  760 */     else if (geometryArray instanceof LineArray) {
/*  761 */       bool = intersectLA((LineArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*  762 */     } else if (geometryArray instanceof LineStripArray) {
/*  763 */       bool = intersectLSA((LineStripArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  765 */     else if (geometryArray instanceof IndexedLineArray) {
/*  766 */       pickIntersection.iGeom = (IndexedGeometryArray)geometryArray;
/*  767 */       bool = intersectILA((IndexedLineArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  769 */     else if (geometryArray instanceof IndexedLineStripArray) {
/*  770 */       pickIntersection.iGeom = (IndexedGeometryArray)geometryArray;
/*  771 */       bool = intersectILSA((IndexedLineStripArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  773 */     else if (geometryArray instanceof TriangleArray) {
/*  774 */       bool = intersectTA((TriangleArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  776 */     else if (geometryArray instanceof TriangleStripArray) {
/*  777 */       bool = intersectTSA((TriangleStripArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  779 */     else if (geometryArray instanceof TriangleFanArray) {
/*  780 */       bool = intersectTFA((TriangleFanArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  782 */     else if (geometryArray instanceof IndexedTriangleArray) {
/*  783 */       pickIntersection.iGeom = (IndexedGeometryArray)geometryArray;
/*  784 */       bool = intersectITA((IndexedTriangleArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  786 */     else if (geometryArray instanceof IndexedTriangleStripArray) {
/*  787 */       pickIntersection.iGeom = (IndexedGeometryArray)geometryArray;
/*  788 */       bool = intersectITSA((IndexedTriangleStripArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  790 */     else if (geometryArray instanceof IndexedTriangleFanArray) {
/*  791 */       pickIntersection.iGeom = (IndexedGeometryArray)geometryArray;
/*  792 */       bool = intersectITFA((IndexedTriangleFanArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     }
/*  794 */     else if (geometryArray instanceof QuadArray) {
/*  795 */       bool = intersectQA((QuadArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*  796 */     } else if (geometryArray instanceof IndexedQuadArray) {
/*  797 */       pickIntersection.iGeom = (IndexedGeometryArray)geometryArray;
/*  798 */       bool = intersectIQA((IndexedQuadArray)geometryArray, paramInt, arrayOfPoint3d2, paramBoolean, pickIntersection);
/*      */     } else {
/*      */       
/*  801 */       throw new RuntimeException("incorrect class type");
/*      */     } 
/*  803 */     return bool;
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
/*      */   boolean intersectPoint(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, Point3d[] paramArrayOfPoint3d, PickIntersection paramPickIntersection) {
/*  817 */     Point3d[] arrayOfPoint3d = new Point3d[1];
/*  818 */     arrayOfPoint3d[0] = paramArrayOfPoint3d[paramArrayOfInt2[0]];
/*      */     
/*  820 */     if (debug) {
/*  821 */       System.out.println("intersect point, point = " + arrayOfPoint3d[0]);
/*      */     }
/*      */     
/*  824 */     boolean bool = false;
/*  825 */     switch (this.pickShapeType) {
/*      */       case 1:
/*  827 */         bool = intersectPntAndRay(arrayOfPoint3d[0], this.pickShapeStart, this.pickShapeDir, paramPickIntersection);
/*      */         break;
/*      */       
/*      */       case 2:
/*  831 */         if (intersectPntAndRay(arrayOfPoint3d[0], this.pickShapeStart, this.pickShapeDir, paramPickIntersection) && 
/*  832 */           paramPickIntersection.getDistance() <= 1.0D) {
/*  833 */           bool = true;
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/*  843 */         bool = ((BoundingBox)this.pickShapeBounds).intersect(arrayOfPoint3d[0]);
/*  844 */         paramPickIntersection.setPointCoordinatesVW(arrayOfPoint3d[0]);
/*      */         break;
/*      */       case 5:
/*  847 */         bool = ((BoundingSphere)this.pickShapeBounds).intersect(arrayOfPoint3d[0]);
/*  848 */         paramPickIntersection.setPointCoordinatesVW(arrayOfPoint3d[0]);
/*      */         break;
/*      */       case 6:
/*  851 */         bool = ((BoundingPolytope)this.pickShapeBounds).intersect(arrayOfPoint3d[0]);
/*  852 */         paramPickIntersection.setPointCoordinatesVW(arrayOfPoint3d[0]);
/*      */         break;
/*      */       case 7:
/*  855 */         bool = intersectCylinder(arrayOfPoint3d[0], (PickCylinder)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */       case 8:
/*  858 */         bool = intersectCone(arrayOfPoint3d[0], (PickCone)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */     } 
/*  861 */     if (bool) {
/*  862 */       PickIntersection pickIntersection = new PickIntersection(this, paramPickIntersection.geom);
/*  863 */       pickIntersection.iGeom = paramPickIntersection.iGeom;
/*  864 */       pickIntersection.setDistance(paramPickIntersection.distance);
/*  865 */       pickIntersection.setPointCoordinatesVW(paramPickIntersection.getPointCoordinatesVW());
/*      */ 
/*      */       
/*  868 */       pickIntersection.setGeomIndex(paramInt);
/*  869 */       pickIntersection.setVertexIndices(paramArrayOfInt1);
/*  870 */       pickIntersection.setPrimitiveCoordinatesVW(arrayOfPoint3d);
/*  871 */       this.intersections.add(pickIntersection);
/*  872 */       return true;
/*      */     } 
/*  874 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectLine(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, Point3d[] paramArrayOfPoint3d, PickIntersection paramPickIntersection) {
/*  880 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*  881 */     arrayOfPoint3d[0] = paramArrayOfPoint3d[paramArrayOfInt2[0]];
/*  882 */     arrayOfPoint3d[1] = paramArrayOfPoint3d[paramArrayOfInt2[1]];
/*      */     
/*  884 */     boolean bool = false;
/*  885 */     switch (this.pickShapeType) {
/*      */       case 1:
/*  887 */         bool = intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], this.pickShapeStart, this.pickShapeDir, paramPickIntersection);
/*      */         break;
/*      */       
/*      */       case 2:
/*  891 */         if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], this.pickShapeStart, this.pickShapeDir, paramPickIntersection))
/*      */         {
/*  893 */           if (paramPickIntersection.getDistance() <= 1.0D) {
/*  894 */             bool = true;
/*      */           }
/*      */         }
/*      */         break;
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
/*      */       case 4:
/*  911 */         bool = intersectBoundingBox(arrayOfPoint3d, (BoundingBox)this.pickShapeBounds);
/*      */         
/*  913 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 5:
/*  916 */         bool = intersectBoundingSphere(arrayOfPoint3d, (BoundingSphere)this.pickShapeBounds);
/*      */         
/*  918 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 6:
/*  921 */         bool = intersectBoundingPolytope(arrayOfPoint3d, (BoundingPolytope)this.pickShapeBounds);
/*      */         
/*  923 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 7:
/*  926 */         bool = intersectCylinder(arrayOfPoint3d, (PickCylinder)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */       case 8:
/*  929 */         bool = intersectCone(arrayOfPoint3d, (PickCone)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */     } 
/*  932 */     if (bool) {
/*  933 */       PickIntersection pickIntersection = new PickIntersection(this, paramPickIntersection.geom);
/*  934 */       pickIntersection.iGeom = paramPickIntersection.iGeom;
/*  935 */       pickIntersection.setDistance(paramPickIntersection.distance);
/*  936 */       pickIntersection.setPointCoordinatesVW(paramPickIntersection.getPointCoordinatesVW());
/*      */ 
/*      */       
/*  939 */       pickIntersection.setGeomIndex(paramInt);
/*  940 */       pickIntersection.setVertexIndices(paramArrayOfInt1);
/*  941 */       pickIntersection.setPrimitiveCoordinatesVW(arrayOfPoint3d);
/*  942 */       this.intersections.add(pickIntersection);
/*  943 */       return true;
/*      */     } 
/*  945 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectTri(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, Point3d[] paramArrayOfPoint3d, PickIntersection paramPickIntersection) {
/*  951 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*      */     
/*  953 */     arrayOfPoint3d[0] = paramArrayOfPoint3d[paramArrayOfInt2[0]];
/*  954 */     arrayOfPoint3d[1] = paramArrayOfPoint3d[paramArrayOfInt2[1]];
/*  955 */     arrayOfPoint3d[2] = paramArrayOfPoint3d[paramArrayOfInt2[2]];
/*      */ 
/*      */     
/*  958 */     boolean bool = false;
/*  959 */     switch (this.pickShapeType) {
/*      */       case 1:
/*  961 */         bool = intersectRay(arrayOfPoint3d, (PickRay)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */       case 2:
/*  964 */         bool = intersectSegment(arrayOfPoint3d, (PickSegment)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/*  972 */         bool = intersectBoundingBox(arrayOfPoint3d, (BoundingBox)this.pickShapeBounds);
/*      */         
/*  974 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 5:
/*  977 */         bool = intersectBoundingSphere(arrayOfPoint3d, (BoundingSphere)this.pickShapeBounds);
/*      */         
/*  979 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 6:
/*  982 */         bool = intersectBoundingPolytope(arrayOfPoint3d, (BoundingPolytope)this.pickShapeBounds);
/*      */         
/*  984 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 7:
/*  987 */         bool = intersectCylinder(arrayOfPoint3d, (PickCylinder)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */       case 8:
/*  990 */         bool = intersectCone(arrayOfPoint3d, (PickCone)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */     } 
/*  993 */     if (bool) {
/*  994 */       PickIntersection pickIntersection = new PickIntersection(this, paramPickIntersection.geom);
/*  995 */       pickIntersection.iGeom = paramPickIntersection.iGeom;
/*  996 */       pickIntersection.setDistance(paramPickIntersection.distance);
/*  997 */       pickIntersection.setPointCoordinatesVW(paramPickIntersection.getPointCoordinatesVW());
/*      */ 
/*      */       
/* 1000 */       pickIntersection.setGeomIndex(paramInt);
/* 1001 */       pickIntersection.setVertexIndices(paramArrayOfInt1);
/*      */       
/* 1003 */       pickIntersection.setPrimitiveCoordinatesVW(arrayOfPoint3d);
/* 1004 */       this.intersections.add(pickIntersection);
/* 1005 */       return true;
/*      */     } 
/* 1007 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectQuad(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, Point3d[] paramArrayOfPoint3d, PickIntersection paramPickIntersection) {
/* 1013 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*      */     
/* 1015 */     arrayOfPoint3d[0] = paramArrayOfPoint3d[paramArrayOfInt2[0]];
/* 1016 */     arrayOfPoint3d[1] = paramArrayOfPoint3d[paramArrayOfInt2[1]];
/* 1017 */     arrayOfPoint3d[2] = paramArrayOfPoint3d[paramArrayOfInt2[2]];
/* 1018 */     arrayOfPoint3d[3] = paramArrayOfPoint3d[paramArrayOfInt2[3]];
/*      */ 
/*      */ 
/*      */     
/* 1022 */     boolean bool = false;
/* 1023 */     switch (this.pickShapeType) {
/*      */       case 1:
/* 1025 */         bool = intersectRay(arrayOfPoint3d, (PickRay)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */       case 2:
/* 1028 */         bool = intersectSegment(arrayOfPoint3d, (PickSegment)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/* 1036 */         bool = intersectBoundingBox(arrayOfPoint3d, (BoundingBox)this.pickShapeBounds);
/*      */         
/* 1038 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 5:
/* 1041 */         bool = intersectBoundingSphere(arrayOfPoint3d, (BoundingSphere)this.pickShapeBounds);
/*      */         
/* 1043 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 6:
/* 1046 */         bool = intersectBoundingPolytope(arrayOfPoint3d, (BoundingPolytope)this.pickShapeBounds);
/*      */         
/* 1048 */         paramPickIntersection.setPointCoordinatesVW(zeroPnt);
/*      */         break;
/*      */       case 7:
/* 1051 */         bool = intersectCylinder(arrayOfPoint3d, (PickCylinder)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */       case 8:
/* 1054 */         bool = intersectCone(arrayOfPoint3d, (PickCone)this.pickShape, paramPickIntersection);
/*      */         break;
/*      */     } 
/* 1057 */     if (bool) {
/* 1058 */       PickIntersection pickIntersection = new PickIntersection(this, paramPickIntersection.geom);
/* 1059 */       pickIntersection.iGeom = paramPickIntersection.iGeom;
/* 1060 */       pickIntersection.setDistance(paramPickIntersection.distance);
/* 1061 */       pickIntersection.setPointCoordinatesVW(paramPickIntersection.getPointCoordinatesVW());
/*      */ 
/*      */       
/* 1064 */       pickIntersection.setGeomIndex(paramInt);
/* 1065 */       pickIntersection.setVertexIndices(paramArrayOfInt1);
/* 1066 */       pickIntersection.setPrimitiveCoordinatesVW(arrayOfPoint3d);
/* 1067 */       this.intersections.add(pickIntersection);
/* 1068 */       return true;
/*      */     } 
/* 1070 */     return false;
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
/*      */   boolean intersectPA(PointArray paramPointArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1083 */     if (debug) System.out.println("intersect: PointArray");
/*      */     
/* 1085 */     int[] arrayOfInt = new int[1];
/* 1086 */     byte b1 = 0;
/*      */     
/* 1088 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d.length; b2++) {
/* 1089 */       arrayOfInt[0] = b2;
/* 1090 */       if (intersectPoint(arrayOfInt, arrayOfInt, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1091 */         b1++;
/* 1092 */         if (paramBoolean) return true; 
/*      */       } 
/*      */     } 
/* 1095 */     if (b1 > 0) return true; 
/* 1096 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectIPA(IndexedPointArray paramIndexedPointArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1105 */     if (debug) System.out.println("intersect: IndexedPointArray");
/*      */     
/* 1107 */     int[] arrayOfInt1 = new int[1];
/* 1108 */     int[] arrayOfInt2 = new int[1];
/*      */     
/* 1110 */     byte b1 = 0;
/* 1111 */     int i = paramIndexedPointArray.getIndexCount();
/*      */     
/* 1113 */     for (byte b2 = 0; b2 < i; b2++) {
/* 1114 */       arrayOfInt1[0] = b2;
/* 1115 */       arrayOfInt2[0] = paramIndexedPointArray.getCoordinateIndex(b2);
/* 1116 */       if (intersectPoint(arrayOfInt1, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1117 */         b1++;
/* 1118 */         if (paramBoolean) return true; 
/*      */       } 
/*      */     } 
/* 1121 */     if (b1 > 0) return true; 
/* 1122 */     return false;
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
/*      */   boolean intersectLA(LineArray paramLineArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1135 */     if (debug) System.out.println("intersect: LineArray");
/*      */     
/* 1137 */     int[] arrayOfInt = new int[2];
/*      */     
/* 1139 */     byte b1 = 0;
/*      */     
/* 1141 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d.length; ) {
/*      */       
/* 1143 */       arrayOfInt[0] = b2++;
/* 1144 */       arrayOfInt[1] = b2++;
/* 1145 */       if (intersectLine(arrayOfInt, arrayOfInt, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1146 */         b1++;
/* 1147 */         if (paramBoolean) return true; 
/*      */       } 
/*      */     } 
/* 1150 */     if (b1 > 0) return true; 
/* 1151 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectLSA(LineStripArray paramLineStripArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1159 */     byte b1 = 0;
/*      */     
/* 1161 */     int[] arrayOfInt1 = new int[paramLineStripArray.getNumStrips()];
/* 1162 */     paramLineStripArray.getStripVertexCounts(arrayOfInt1);
/* 1163 */     int i = 0;
/*      */     
/* 1165 */     if (debug) System.out.println("intersect: LineStripArray");
/*      */     
/* 1167 */     int[] arrayOfInt2 = new int[2];
/*      */     
/* 1169 */     for (byte b2 = 0; b2 < arrayOfInt1.length; b2++) {
/* 1170 */       arrayOfInt2[0] = i;
/* 1171 */       int j = i + arrayOfInt1[b2];
/*      */       
/* 1173 */       for (int k = i + 1; k < j; k++) {
/* 1174 */         arrayOfInt2[1] = k;
/* 1175 */         if (intersectLine(arrayOfInt2, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1176 */           b1++;
/* 1177 */           if (paramBoolean) return true; 
/*      */         } 
/* 1179 */         arrayOfInt2[0] = arrayOfInt2[1];
/*      */       } 
/* 1181 */       i += arrayOfInt1[b2];
/*      */     } 
/* 1183 */     if (b1 > 0) return true; 
/* 1184 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectILA(IndexedLineArray paramIndexedLineArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1193 */     byte b1 = 0;
/* 1194 */     int i = paramIndexedLineArray.getIndexCount();
/* 1195 */     if (debug) System.out.println("intersect: IndexedLineArray");
/*      */     
/* 1197 */     int[] arrayOfInt1 = new int[2];
/* 1198 */     int[] arrayOfInt2 = new int[2];
/*      */     
/* 1200 */     for (byte b2 = 0; b2 < i; ) {
/* 1201 */       arrayOfInt1[0] = b2;
/* 1202 */       arrayOfInt2[0] = paramIndexedLineArray.getCoordinateIndex(b2++);
/* 1203 */       arrayOfInt1[1] = b2;
/* 1204 */       arrayOfInt2[1] = paramIndexedLineArray.getCoordinateIndex(b2++);
/* 1205 */       if (intersectLine(arrayOfInt1, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1206 */         b1++;
/* 1207 */         if (paramBoolean) return true; 
/*      */       } 
/*      */     } 
/* 1210 */     if (b1 > 0) return true; 
/* 1211 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectILSA(IndexedLineStripArray paramIndexedLineStripArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1219 */     if (debug) System.out.println("intersect: IndexedLineStripArray");
/*      */     
/* 1221 */     int[] arrayOfInt1 = new int[2];
/* 1222 */     int[] arrayOfInt2 = new int[2];
/*      */     
/* 1224 */     byte b1 = 0;
/* 1225 */     int[] arrayOfInt3 = new int[paramIndexedLineStripArray.getNumStrips()];
/* 1226 */     paramIndexedLineStripArray.getStripIndexCounts(arrayOfInt3);
/* 1227 */     int i = 0;
/*      */     
/* 1229 */     for (byte b2 = 0; b2 < arrayOfInt3.length; b2++) {
/*      */       
/* 1231 */       arrayOfInt1[0] = i;
/* 1232 */       arrayOfInt2[0] = paramIndexedLineStripArray.getCoordinateIndex(i);
/* 1233 */       int j = i + arrayOfInt3[b2];
/* 1234 */       for (int k = i + 1; k < j; k++) {
/* 1235 */         arrayOfInt1[1] = k;
/* 1236 */         arrayOfInt2[1] = paramIndexedLineStripArray.getCoordinateIndex(k);
/* 1237 */         if (intersectLine(arrayOfInt1, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1238 */           b1++;
/* 1239 */           if (paramBoolean) return true; 
/*      */         } 
/* 1241 */         arrayOfInt1[0] = arrayOfInt1[1];
/* 1242 */         arrayOfInt2[0] = arrayOfInt2[1];
/*      */       } 
/* 1244 */       i += arrayOfInt3[b2];
/*      */     } 
/* 1246 */     if (b1 > 0) return true; 
/* 1247 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectTA(TriangleArray paramTriangleArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1256 */     if (debug) {
/* 1257 */       System.out.println("intersect: TriangleArray");
/*      */     }
/* 1259 */     int[] arrayOfInt = new int[3];
/*      */     
/* 1261 */     byte b1 = 0;
/* 1262 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d.length; ) {
/* 1263 */       arrayOfInt[0] = b2++;
/* 1264 */       arrayOfInt[1] = b2++;
/* 1265 */       arrayOfInt[2] = b2++;
/* 1266 */       if (intersectTri(arrayOfInt, arrayOfInt, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1267 */         b1++;
/* 1268 */         if (paramBoolean) return true;
/*      */       
/*      */       } 
/*      */     } 
/* 1272 */     if (b1 > 0) return true; 
/* 1273 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectITA(IndexedTriangleArray paramIndexedTriangleArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1282 */     if (debug) {
/* 1283 */       System.out.println("intersect: IndexedTriangleArray");
/*      */     }
/* 1285 */     int[] arrayOfInt1 = new int[3];
/* 1286 */     int[] arrayOfInt2 = new int[3];
/*      */     
/* 1288 */     byte b1 = 0;
/* 1289 */     int i = paramIndexedTriangleArray.getIndexCount();
/* 1290 */     for (byte b2 = 0; b2 < i; ) {
/* 1291 */       arrayOfInt1[0] = b2;
/* 1292 */       arrayOfInt2[0] = paramIndexedTriangleArray.getCoordinateIndex(b2++);
/* 1293 */       arrayOfInt1[1] = b2;
/* 1294 */       arrayOfInt2[1] = paramIndexedTriangleArray.getCoordinateIndex(b2++);
/* 1295 */       arrayOfInt1[2] = b2;
/* 1296 */       arrayOfInt2[2] = paramIndexedTriangleArray.getCoordinateIndex(b2++);
/* 1297 */       if (intersectTri(arrayOfInt1, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1298 */         b1++;
/* 1299 */         if (paramBoolean) return true;
/*      */       
/*      */       } 
/*      */     } 
/* 1303 */     if (b1 > 0) return true; 
/* 1304 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectTSA(TriangleStripArray paramTriangleStripArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1312 */     if (debug) {
/* 1313 */       System.out.println("intersect: TriangleStripArray");
/*      */     }
/*      */     
/* 1316 */     byte b1 = 0;
/* 1317 */     int[] arrayOfInt1 = new int[paramTriangleStripArray.getNumStrips()];
/* 1318 */     paramTriangleStripArray.getStripVertexCounts(arrayOfInt1);
/* 1319 */     int i = 0;
/*      */     
/* 1321 */     int[] arrayOfInt2 = new int[3];
/*      */     
/* 1323 */     for (byte b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*      */       
/* 1325 */       int j = i;
/*      */       
/* 1327 */       boolean bool = true;
/* 1328 */       arrayOfInt2[0] = j++;
/* 1329 */       arrayOfInt2[1] = j++;
/*      */       
/* 1331 */       int k = j + arrayOfInt1[b2] - 2;
/* 1332 */       for (int m = j; m < k; m++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1340 */         arrayOfInt2[2] = m;
/* 1341 */         if (intersectTri(arrayOfInt2, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1342 */           b1++;
/* 1343 */           if (paramBoolean) return true;
/*      */         
/*      */         } 
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
/* 1359 */         arrayOfInt2[0] = arrayOfInt2[1];
/* 1360 */         arrayOfInt2[1] = arrayOfInt2[2];
/*      */       } 
/*      */       
/* 1363 */       i += arrayOfInt1[b2];
/*      */     } 
/*      */     
/* 1366 */     if (b1 > 0) return true; 
/* 1367 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectITSA(IndexedTriangleStripArray paramIndexedTriangleStripArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1376 */     if (debug)
/* 1377 */       System.out.println("intersect: IndexedTriangleStripArray"); 
/* 1378 */     byte b1 = 0;
/*      */ 
/*      */     
/* 1381 */     int[] arrayOfInt1 = new int[paramIndexedTriangleStripArray.getNumStrips()];
/* 1382 */     paramIndexedTriangleStripArray.getStripIndexCounts(arrayOfInt1);
/* 1383 */     int i = 0;
/*      */     
/* 1385 */     int[] arrayOfInt2 = new int[3];
/* 1386 */     int[] arrayOfInt3 = new int[3];
/*      */     
/* 1388 */     for (byte b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*      */       
/* 1390 */       int j = i;
/*      */       
/* 1392 */       boolean bool = true;
/* 1393 */       arrayOfInt3[0] = paramIndexedTriangleStripArray.getCoordinateIndex(j);
/* 1394 */       arrayOfInt2[0] = j++;
/* 1395 */       arrayOfInt3[1] = paramIndexedTriangleStripArray.getCoordinateIndex(j);
/* 1396 */       arrayOfInt2[1] = j++;
/*      */       
/* 1398 */       int k = j + arrayOfInt1[b2] - 2;
/* 1399 */       for (int m = j; m < k; m++) {
/* 1400 */         if (bool) {
/* 1401 */           arrayOfInt2[2] = m;
/* 1402 */           arrayOfInt3[2] = paramIndexedTriangleStripArray.getCoordinateIndex(m);
/*      */         } else {
/* 1404 */           arrayOfInt2[1] = m;
/* 1405 */           arrayOfInt3[1] = paramIndexedTriangleStripArray.getCoordinateIndex(m);
/*      */         } 
/*      */         
/* 1408 */         if (intersectTri(arrayOfInt2, arrayOfInt3, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1409 */           b1++;
/* 1410 */           if (paramBoolean) return true;
/*      */         
/*      */         } 
/*      */ 
/*      */         
/* 1415 */         if (bool) {
/* 1416 */           arrayOfInt2[0] = arrayOfInt2[1];
/*      */           
/* 1418 */           arrayOfInt3[0] = arrayOfInt3[1];
/* 1419 */           bool = false;
/*      */         } else {
/* 1421 */           arrayOfInt2[0] = arrayOfInt2[2];
/*      */           
/* 1423 */           arrayOfInt3[0] = arrayOfInt3[2];
/* 1424 */           bool = true;
/*      */         } 
/*      */       } 
/* 1427 */       i += arrayOfInt1[b2];
/*      */     } 
/*      */     
/* 1430 */     if (b1 > 0) return true; 
/* 1431 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectTFA(TriangleFanArray paramTriangleFanArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1441 */     if (debug) System.out.println("intersect: TriangleFanArray");
/*      */     
/* 1443 */     byte b1 = 0;
/*      */     
/* 1445 */     int[] arrayOfInt1 = new int[paramTriangleFanArray.getNumStrips()];
/* 1446 */     paramTriangleFanArray.getStripVertexCounts(arrayOfInt1);
/* 1447 */     int i = 0;
/*      */     
/* 1449 */     int[] arrayOfInt2 = new int[3];
/*      */ 
/*      */     
/* 1452 */     for (byte b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*      */       
/* 1454 */       int j = i;
/* 1455 */       arrayOfInt2[0] = j++;
/* 1456 */       arrayOfInt2[1] = j++;
/*      */       
/* 1458 */       int k = j + arrayOfInt1[b2] - 2;
/* 1459 */       for (int m = j; m < k; m++) {
/* 1460 */         arrayOfInt2[2] = m;
/* 1461 */         if (intersectTri(arrayOfInt2, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1462 */           b1++;
/* 1463 */           if (paramBoolean) return true; 
/*      */         } 
/* 1465 */         arrayOfInt2[1] = arrayOfInt2[2];
/*      */       } 
/* 1467 */       i += arrayOfInt1[b2];
/*      */     } 
/* 1469 */     if (b1 > 0) return true; 
/* 1470 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectITFA(IndexedTriangleFanArray paramIndexedTriangleFanArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1479 */     if (debug) System.out.println("intersect: IndexedTriangleFanArray");
/*      */     
/* 1481 */     byte b1 = 0;
/* 1482 */     int[] arrayOfInt1 = new int[paramIndexedTriangleFanArray.getNumStrips()];
/* 1483 */     paramIndexedTriangleFanArray.getStripIndexCounts(arrayOfInt1);
/* 1484 */     int i = 0;
/*      */     
/* 1486 */     int[] arrayOfInt2 = new int[3];
/* 1487 */     int[] arrayOfInt3 = new int[3];
/*      */     
/* 1489 */     for (byte b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*      */       
/* 1491 */       int j = i;
/* 1492 */       arrayOfInt3[0] = paramIndexedTriangleFanArray.getCoordinateIndex(j);
/* 1493 */       arrayOfInt2[0] = j++;
/* 1494 */       arrayOfInt3[1] = paramIndexedTriangleFanArray.getCoordinateIndex(j);
/* 1495 */       arrayOfInt2[1] = j++;
/*      */       
/* 1497 */       int k = j + arrayOfInt1[b2] - 2;
/* 1498 */       for (int m = j; m < k; m++) {
/* 1499 */         arrayOfInt2[2] = m;
/* 1500 */         arrayOfInt3[2] = paramIndexedTriangleFanArray.getCoordinateIndex(m);
/* 1501 */         if (intersectTri(arrayOfInt2, arrayOfInt3, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1502 */           b1++;
/* 1503 */           if (paramBoolean) return true; 
/*      */         } 
/* 1505 */         arrayOfInt2[1] = arrayOfInt2[2];
/* 1506 */         arrayOfInt3[1] = arrayOfInt3[2];
/*      */       } 
/* 1508 */       i += arrayOfInt1[b2];
/*      */     } 
/* 1510 */     if (b1 > 0) return true; 
/* 1511 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersectQA(QuadArray paramQuadArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1520 */     if (debug) System.out.println("intersect: QuadArray");
/*      */     
/* 1522 */     int[] arrayOfInt = new int[4];
/*      */     
/* 1524 */     byte b1 = 0;
/* 1525 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d.length; ) {
/* 1526 */       arrayOfInt[0] = b2++;
/* 1527 */       arrayOfInt[1] = b2++;
/* 1528 */       arrayOfInt[2] = b2++;
/* 1529 */       arrayOfInt[3] = b2++;
/* 1530 */       if (intersectQuad(arrayOfInt, arrayOfInt, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1531 */         b1++;
/* 1532 */         if (paramBoolean) return true;
/*      */       
/*      */       } 
/*      */     } 
/* 1536 */     if (b1 > 0) return true; 
/* 1537 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final boolean intersectIQA(IndexedQuadArray paramIndexedQuadArray, int paramInt, Point3d[] paramArrayOfPoint3d, boolean paramBoolean, PickIntersection paramPickIntersection) {
/* 1547 */     if (debug) System.out.println("intersect: IndexedQuadArray");
/*      */     
/* 1549 */     int[] arrayOfInt1 = new int[4];
/* 1550 */     int[] arrayOfInt2 = new int[4];
/*      */     
/* 1552 */     byte b1 = 0;
/* 1553 */     int i = paramIndexedQuadArray.getIndexCount();
/*      */     
/* 1555 */     for (byte b2 = 0; b2 < i; ) {
/* 1556 */       arrayOfInt1[0] = b2;
/* 1557 */       arrayOfInt2[0] = paramIndexedQuadArray.getCoordinateIndex(b2++);
/* 1558 */       arrayOfInt1[1] = b2;
/* 1559 */       arrayOfInt2[1] = paramIndexedQuadArray.getCoordinateIndex(b2++);
/* 1560 */       arrayOfInt1[2] = b2;
/* 1561 */       arrayOfInt2[2] = paramIndexedQuadArray.getCoordinateIndex(b2++);
/* 1562 */       arrayOfInt1[3] = b2;
/* 1563 */       arrayOfInt2[3] = paramIndexedQuadArray.getCoordinateIndex(b2++);
/*      */       
/* 1565 */       if (intersectQuad(arrayOfInt1, arrayOfInt2, paramInt, paramArrayOfPoint3d, paramPickIntersection)) {
/* 1566 */         b1++;
/* 1567 */         if (paramBoolean) return true;
/*      */       
/*      */       } 
/*      */     } 
/* 1571 */     if (b1 > 0) return true; 
/* 1572 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectBoundingBox(Point3d[] paramArrayOfPoint3d, BoundingBox paramBoundingBox) {
/* 1582 */     int[] arrayOfInt = new int[6];
/*      */     
/* 1584 */     Point3d point3d1 = new Point3d();
/* 1585 */     Point3d point3d2 = new Point3d();
/* 1586 */     paramBoundingBox.getLower(point3d1);
/* 1587 */     paramBoundingBox.getUpper(point3d2);
/*      */     
/*      */     byte b;
/* 1590 */     for (b = 0; b < 6; ) { arrayOfInt[b] = 0; b++; }
/* 1591 */      for (b = 0; b < paramArrayOfPoint3d.length; b++) {
/* 1592 */       if ((paramArrayOfPoint3d[b]).x >= point3d1.x && (paramArrayOfPoint3d[b]).x <= point3d2.x && (paramArrayOfPoint3d[b]).y >= point3d1.y && (paramArrayOfPoint3d[b]).y <= point3d2.y && (paramArrayOfPoint3d[b]).z >= point3d1.z && (paramArrayOfPoint3d[b]).z <= point3d2.z)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1599 */         return true;
/*      */       }
/* 1601 */       if ((paramArrayOfPoint3d[b]).x < point3d1.x) arrayOfInt[0] = arrayOfInt[0] + 1; 
/* 1602 */       if ((paramArrayOfPoint3d[b]).y < point3d1.y) arrayOfInt[1] = arrayOfInt[1] + 1; 
/* 1603 */       if ((paramArrayOfPoint3d[b]).z < point3d1.z) arrayOfInt[2] = arrayOfInt[2] + 1; 
/* 1604 */       if ((paramArrayOfPoint3d[b]).x > point3d2.x) arrayOfInt[3] = arrayOfInt[3] + 1; 
/* 1605 */       if ((paramArrayOfPoint3d[b]).y > point3d2.y) arrayOfInt[4] = arrayOfInt[4] + 1; 
/* 1606 */       if ((paramArrayOfPoint3d[b]).z > point3d2.z) arrayOfInt[5] = arrayOfInt[5] + 1;
/*      */     
/*      */     } 
/*      */     
/* 1610 */     if (arrayOfInt[0] == paramArrayOfPoint3d.length || arrayOfInt[1] == paramArrayOfPoint3d.length || arrayOfInt[2] == paramArrayOfPoint3d.length || arrayOfInt[3] == paramArrayOfPoint3d.length || arrayOfInt[4] == paramArrayOfPoint3d.length || arrayOfInt[5] == paramArrayOfPoint3d.length)
/*      */     {
/*      */ 
/*      */       
/* 1614 */       return false;
/*      */     }
/*      */     
/* 1617 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/* 1618 */     for (b = 0; b < 4; ) { arrayOfPoint3d[b] = new Point3d(); b++; }
/*      */ 
/*      */     
/* 1621 */     arrayOfPoint3d[0].set(point3d1.x, point3d1.y, point3d1.z);
/* 1622 */     arrayOfPoint3d[1].set(point3d1.x, point3d1.y, point3d2.z);
/* 1623 */     arrayOfPoint3d[2].set(point3d1.x, point3d2.y, point3d2.z);
/* 1624 */     arrayOfPoint3d[3].set(point3d1.x, point3d2.y, point3d1.z);
/* 1625 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d, false) == true) return true;
/*      */ 
/*      */     
/* 1628 */     arrayOfPoint3d[0].set(point3d2.x, point3d1.y, point3d1.z);
/* 1629 */     arrayOfPoint3d[1].set(point3d2.x, point3d2.y, point3d1.z);
/* 1630 */     arrayOfPoint3d[2].set(point3d2.x, point3d2.y, point3d2.z);
/* 1631 */     arrayOfPoint3d[3].set(point3d2.x, point3d1.y, point3d2.z);
/* 1632 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d, false) == true) return true;
/*      */ 
/*      */     
/* 1635 */     arrayOfPoint3d[0].set(point3d2.x, point3d1.y, point3d2.z);
/* 1636 */     arrayOfPoint3d[1].set(point3d1.x, point3d1.y, point3d2.z);
/* 1637 */     arrayOfPoint3d[2].set(point3d1.x, point3d1.y, point3d1.z);
/* 1638 */     arrayOfPoint3d[3].set(point3d2.x, point3d1.y, point3d1.z);
/* 1639 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d, false) == true) return true;
/*      */ 
/*      */     
/* 1642 */     arrayOfPoint3d[0].set(point3d2.x, point3d2.y, point3d2.z);
/* 1643 */     arrayOfPoint3d[1].set(point3d2.x, point3d2.y, point3d1.z);
/* 1644 */     arrayOfPoint3d[2].set(point3d1.x, point3d2.y, point3d1.z);
/* 1645 */     arrayOfPoint3d[3].set(point3d1.x, point3d2.y, point3d2.z);
/* 1646 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d, false) == true) return true;
/*      */ 
/*      */     
/* 1649 */     arrayOfPoint3d[0].set(point3d2.x, point3d2.y, point3d2.z);
/* 1650 */     arrayOfPoint3d[1].set(point3d1.x, point3d2.y, point3d2.z);
/* 1651 */     arrayOfPoint3d[2].set(point3d1.x, point3d1.y, point3d2.z);
/* 1652 */     arrayOfPoint3d[3].set(point3d2.x, point3d1.y, point3d2.z);
/* 1653 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d, false) == true) return true;
/*      */ 
/*      */     
/* 1656 */     arrayOfPoint3d[0].set(point3d2.x, point3d2.y, point3d1.z);
/* 1657 */     arrayOfPoint3d[1].set(point3d2.x, point3d1.y, point3d1.z);
/* 1658 */     arrayOfPoint3d[2].set(point3d1.x, point3d1.y, point3d1.z);
/* 1659 */     arrayOfPoint3d[3].set(point3d1.x, point3d2.y, point3d1.z);
/* 1660 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d, false) == true) return true;
/*      */     
/* 1662 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectBoundingSphere(Point3d[] paramArrayOfPoint3d, BoundingSphere paramBoundingSphere) {
/* 1670 */     Vector3d vector3d1 = new Vector3d();
/*      */     
/* 1672 */     Point3d point3d1 = new Point3d();
/* 1673 */     paramBoundingSphere.getCenter(point3d1);
/* 1674 */     double d1 = paramBoundingSphere.getRadius();
/*      */     
/*      */     byte b1;
/* 1677 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 1678 */       vector3d1.x = (paramArrayOfPoint3d[b1]).x - point3d1.x;
/* 1679 */       vector3d1.y = (paramArrayOfPoint3d[b1]).y - point3d1.y;
/* 1680 */       vector3d1.z = (paramArrayOfPoint3d[b1]).z - point3d1.z;
/*      */       
/* 1682 */       if (vector3d1.length() <= d1)
/*      */       {
/* 1684 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1688 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 1689 */       boolean bool; if (b1 < paramArrayOfPoint3d.length - 1) {
/* 1690 */         bool = edgeIntersectSphere(paramBoundingSphere, paramArrayOfPoint3d[b1], paramArrayOfPoint3d[b1 + 1]);
/*      */       } else {
/*      */         
/* 1693 */         bool = edgeIntersectSphere(paramBoundingSphere, paramArrayOfPoint3d[b1], paramArrayOfPoint3d[0]);
/*      */       } 
/*      */       
/* 1696 */       if (bool == true) {
/* 1697 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1701 */     if (paramArrayOfPoint3d.length < 3) return false;
/*      */ 
/*      */ 
/*      */     
/* 1705 */     Vector3d vector3d2 = new Vector3d();
/* 1706 */     Vector3d vector3d3 = new Vector3d();
/* 1707 */     Vector3d vector3d4 = new Vector3d();
/* 1708 */     Vector3d vector3d5 = new Vector3d();
/* 1709 */     Point3d point3d2 = new Point3d();
/*      */ 
/*      */ 
/*      */     
/* 1713 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length - 1; ) {
/* 1714 */       vector3d2.x = (paramArrayOfPoint3d[b1 + 1]).x - (paramArrayOfPoint3d[b1]).x;
/* 1715 */       vector3d2.y = (paramArrayOfPoint3d[b1 + 1]).y - (paramArrayOfPoint3d[b1]).y;
/* 1716 */       vector3d2.z = (paramArrayOfPoint3d[b1 + 1]).z - (paramArrayOfPoint3d[b1++]).z;
/* 1717 */       if (vector3d2.length() > 0.0D)
/*      */         break; 
/*      */     }  byte b2;
/* 1720 */     for (b2 = b1; b2 < paramArrayOfPoint3d.length - 1; b2++) {
/* 1721 */       vector3d3.x = (paramArrayOfPoint3d[b2 + 1]).x - (paramArrayOfPoint3d[b2]).x;
/* 1722 */       vector3d3.y = (paramArrayOfPoint3d[b2 + 1]).y - (paramArrayOfPoint3d[b2]).y;
/* 1723 */       vector3d3.z = (paramArrayOfPoint3d[b2 + 1]).z - (paramArrayOfPoint3d[b2]).z;
/* 1724 */       if (vector3d3.length() > 0.0D)
/*      */         break; 
/*      */     } 
/* 1727 */     if (b2 == paramArrayOfPoint3d.length - 1)
/*      */     {
/* 1729 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1739 */     vector3d4.cross(vector3d2, vector3d3);
/*      */     
/* 1741 */     double d2 = vector3d4.lengthSquared();
/* 1742 */     if (d2 == 0.0D)
/*      */     {
/* 1744 */       return false;
/*      */     }
/*      */     
/* 1747 */     vector3d5.x = (paramArrayOfPoint3d[0]).x - point3d1.x;
/* 1748 */     vector3d5.y = (paramArrayOfPoint3d[0]).y - point3d1.y;
/* 1749 */     vector3d5.z = (paramArrayOfPoint3d[0]).z - point3d1.z;
/*      */     
/* 1751 */     double d4 = vector3d4.dot(vector3d5);
/*      */     
/* 1753 */     double d3 = Math.sqrt(d4 * d4 / d2);
/*      */     
/* 1755 */     if (d3 > d1) {
/* 1756 */       return false;
/*      */     }
/* 1758 */     double d5 = d4 / d2;
/*      */     
/* 1760 */     point3d1.x += d5 * vector3d4.x;
/* 1761 */     point3d1.y += d5 * vector3d4.y;
/* 1762 */     point3d1.z += d5 * vector3d4.z;
/*      */ 
/*      */     
/* 1765 */     return pointIntersectPolygon2D(vector3d4, paramArrayOfPoint3d, point3d2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectBoundingPolytope(Point3d[] paramArrayOfPoint3d, BoundingPolytope paramBoundingPolytope) {
/* 1771 */     boolean bool1 = false;
/*      */ 
/*      */     
/* 1774 */     double d1 = -1.0D;
/*      */     
/* 1776 */     Point4d point4d = new Point4d();
/*      */     
/* 1778 */     Vector4d[] arrayOfVector4d = new Vector4d[paramBoundingPolytope.getNumPlanes()];
/*      */     byte b;
/* 1780 */     for (b = 0; b < arrayOfVector4d.length; b++) {
/* 1781 */       arrayOfVector4d[b] = new Vector4d();
/*      */     }
/* 1783 */     paramBoundingPolytope.getPlanes(arrayOfVector4d);
/*      */     
/* 1785 */     if (paramArrayOfPoint3d.length == 2)
/*      */     {
/* 1787 */       throw new RuntimeException("TODO: must make polytope.intersect(coordinates[0], coordinates[1], tP4d) public!");
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
/* 1799 */     if (bool1) {
/* 1800 */       System.out.println("The value of the input vertices are: ");
/* 1801 */       for (b = 0; b < paramArrayOfPoint3d.length; b++) {
/* 1802 */         System.out.println("The " + b + " th vertex is: " + paramArrayOfPoint3d[b]);
/*      */       }
/*      */       
/* 1805 */       System.out.println("The value of the input bounding Polytope's planes =");
/* 1806 */       for (b = 0; b < arrayOfVector4d.length; b++) {
/* 1807 */         System.out.println("The " + b + " th plane is: " + arrayOfVector4d[b]);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1813 */     double[] arrayOfDouble = new double[4];
/* 1814 */     arrayOfDouble[0] = 0.8D; arrayOfDouble[1] = 0.9D; arrayOfDouble[2] = 1.1D; arrayOfDouble[3] = 1.2D;
/*      */     
/* 1816 */     boolean bool2 = true;
/* 1817 */     boolean bool3 = false;
/*      */     
/* 1819 */     if (bool3)
/*      */     {
/* 1821 */       for (byte b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 1822 */         for (byte b2 = 0; b2 < arrayOfVector4d.length; b2++) {
/* 1823 */           if ((arrayOfVector4d[b2]).x * (paramArrayOfPoint3d[b1]).x + (arrayOfVector4d[b2]).y * (paramArrayOfPoint3d[b1]).y + (arrayOfVector4d[b2]).z * (paramArrayOfPoint3d[b1]).z <= d1 * (arrayOfVector4d[b2]).w) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1828 */             bool2 = true;
/*      */           } else {
/*      */             
/* 1831 */             bool2 = false;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1835 */         if (bool2)
/*      */         {
/* 1837 */           return true;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1845 */     int i = arrayOfVector4d.length + 2 + paramArrayOfPoint3d.length + 1;
/* 1846 */     int j = 1 + paramArrayOfPoint3d.length;
/*      */     
/* 1848 */     double[][] arrayOfDouble1 = new double[j][i];
/*      */     
/*      */     int k;
/* 1851 */     for (k = 0; k < arrayOfVector4d.length; k++) {
/* 1852 */       for (byte b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 1853 */         arrayOfDouble1[b1][k] = -1.0D * ((arrayOfVector4d[k]).x * (paramArrayOfPoint3d[b1]).x + (arrayOfVector4d[k]).y * (paramArrayOfPoint3d[b1]).y + (arrayOfVector4d[k]).z * (paramArrayOfPoint3d[b1]).z);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1860 */     for (k = 0; k < paramArrayOfPoint3d.length; k++) {
/* 1861 */       arrayOfDouble1[k][arrayOfVector4d.length] = -1.0D;
/* 1862 */       arrayOfDouble1[k][arrayOfVector4d.length + 1] = 1.0D;
/*      */       
/* 1864 */       for (int m = 0; m < paramArrayOfPoint3d.length; m++) {
/* 1865 */         if (k == m) {
/* 1866 */           arrayOfDouble1[k][m + arrayOfVector4d.length + 2] = 1.0D;
/*      */         } else {
/* 1868 */           arrayOfDouble1[k][m + arrayOfVector4d.length + 2] = 0.0D;
/*      */         } 
/*      */ 
/*      */         
/* 1872 */         arrayOfDouble1[k][i - 1] = arrayOfDouble[k];
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1877 */     for (k = 0; k < arrayOfVector4d.length; k++) {
/* 1878 */       arrayOfDouble1[j - 1][k] = d1 * (arrayOfVector4d[k]).w;
/*      */     }
/*      */     
/* 1881 */     arrayOfDouble1[j - 1][arrayOfVector4d.length] = 1.0D;
/* 1882 */     arrayOfDouble1[j - 1][arrayOfVector4d.length + 1] = -1.0D;
/* 1883 */     for (k = 0; k < paramArrayOfPoint3d.length; k++) {
/* 1884 */       arrayOfDouble1[j - 1][arrayOfVector4d.length + 2 + k] = 0.0D;
/*      */     }
/*      */     
/* 1887 */     if (bool1) {
/* 1888 */       System.out.println("The value of the problem tableau is: ");
/* 1889 */       for (k = 0; k < arrayOfDouble1.length; k++) {
/* 1890 */         for (byte b1 = 0; b1 < arrayOfDouble1[0].length; b1++) {
/* 1891 */           System.out.print(arrayOfDouble1[k][b1] + "  ");
/*      */         }
/* 1893 */         System.out.println();
/*      */       } 
/*      */     } 
/*      */     
/* 1897 */     double d2 = generalStandardSimplexSolver(arrayOfDouble1, Double.NEGATIVE_INFINITY);
/*      */     
/* 1899 */     if (bool1) {
/* 1900 */       System.out.println("The value returned by the general standard simplex = " + d2);
/*      */     }
/*      */     
/* 1903 */     if (d2 == Double.POSITIVE_INFINITY) {
/* 1904 */       return false;
/*      */     }
/* 1906 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static double generalStandardSimplexSolver(double[][] paramArrayOfDouble, double paramDouble) {
/* 1916 */     boolean bool1 = false;
/* 1917 */     int i = paramArrayOfDouble.length;
/* 1918 */     int j = paramArrayOfDouble[0].length;
/* 1919 */     boolean bool2 = false;
/*      */ 
/*      */     
/* 1922 */     boolean bool3 = false;
/*      */ 
/*      */     
/* 1925 */     if (bool1) {
/* 1926 */       System.out.println("The number of rows is : " + i);
/* 1927 */       System.out.println("The number of columns is : " + j);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1932 */     while (!bool2) {
/*      */       
/* 1934 */       if (bool1) {
/* 1935 */         System.out.println("input problem tableau is:");
/* 1936 */         for (byte b = 0; b < i; b++) {
/* 1937 */           for (byte b3 = 0; b3 < j; b3++) {
/* 1938 */             System.out.println("kth, jth value is:" + b + " " + b3 + " : " + paramArrayOfDouble[b][b3]);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*      */       byte b1;
/*      */       
/*      */       byte b2;
/*      */       double d;
/* 1947 */       for (b1 = 0, d = 0.0D, b2 = -1; b1 < j - 1; b1++) {
/*      */         
/* 1949 */         double d1 = paramArrayOfDouble[i - 1][b1];
/* 1950 */         if (d1 < d) {
/* 1951 */           d = d1;
/* 1952 */           b2 = b1;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1958 */       if (b2 == -1)
/*      */       {
/*      */         
/* 1961 */         bool2 = true;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1966 */       if (!bool2) {
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
/* 1977 */         double d2 = Double.POSITIVE_INFINITY;
/* 1978 */         double d1 = 0.0D;
/* 1979 */         byte b = -1;
/*      */ 
/*      */ 
/*      */         
/* 1983 */         for (b1 = 0; b1 < i - 1; b1++) {
/* 1984 */           double d4 = paramArrayOfDouble[b1][b2];
/* 1985 */           double d5 = paramArrayOfDouble[b1][j - 1];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1991 */           if (d4 == 0.0D) {
/* 1992 */             if (bool1) {
/* 1993 */               System.out.println("Division by zero has occurred");
/* 1994 */               System.out.println("Within the linear program solver");
/* 1995 */               System.out.println("Ignoring the zero as a potential pivot");
/*      */             } 
/* 1997 */           } else if (d4 < 0.0D || d5 < 0.0D) {
/* 1998 */             if (bool1) {
/* 1999 */               System.out.println("Ignoring cases where element is negative");
/* 2000 */               System.out.println("The value of element is: " + d4);
/* 2001 */               System.out.println("The value of end Element is: " + d5);
/*      */             } 
/*      */           } else {
/* 2004 */             d1 = d5 / d4;
/* 2005 */             if (bool1) {
/* 2006 */               System.out.println("The value of element is: " + d4);
/* 2007 */               System.out.println("The value of endElement is: " + d5);
/* 2008 */               System.out.println("The value of ratio is: " + d1);
/* 2009 */               System.out.println("The value of prevRatio is: " + d2);
/* 2010 */               System.out.println("Value of ratio <= prevRatio is :" + ((d1 <= d2) ? 1 : 0));
/*      */             } 
/*      */             
/* 2013 */             if (d1 <= d2) {
/* 2014 */               if (bool1) {
/* 2015 */                 System.out.println("updating prevRatio with ratio");
/*      */               }
/* 2017 */               d2 = d1;
/* 2018 */               b = b1;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2027 */         if (b == -1) {
/* 2028 */           if (bool1) {
/* 2029 */             System.out.println("UNABLE TO FIND SOLUTION");
/* 2030 */             System.out.println("The system is infeasable or unbounded");
/*      */           } 
/* 2032 */           return Double.POSITIVE_INFINITY;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2042 */         double d3 = paramArrayOfDouble[b][b2];
/*      */         
/* 2044 */         if (bool1) {
/* 2045 */           System.out.println("The value of row index is: " + b);
/* 2046 */           System.out.println("The value of col index is: " + b2);
/* 2047 */           System.out.println("The value of pivotValue is: " + d3);
/*      */         } 
/*      */         
/* 2050 */         for (b1 = 0; b1 < j; b1++) {
/* 2051 */           paramArrayOfDouble[b][b1] = paramArrayOfDouble[b][b1] / d3;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2057 */         for (b1 = 0; b1 < i; b1++) {
/* 2058 */           if (b1 != b) {
/* 2059 */             double d4 = paramArrayOfDouble[b1][b2];
/* 2060 */             for (byte b3 = 0; b3 < j; b3++) {
/* 2061 */               paramArrayOfDouble[b1][b3] = paramArrayOfDouble[b1][b3] - d4 * paramArrayOfDouble[b][b3];
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2069 */     return paramArrayOfDouble[i - 1][j - 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean edgeIntersectSphere(BoundingSphere paramBoundingSphere, Point3d paramPoint3d1, Point3d paramPoint3d2) {
/* 2076 */     Vector3d vector3d1 = new Vector3d();
/* 2077 */     Vector3d vector3d2 = new Vector3d();
/*      */     
/* 2079 */     Point3d point3d = new Point3d();
/* 2080 */     paramBoundingSphere.getCenter(point3d);
/* 2081 */     double d6 = paramBoundingSphere.getRadius();
/*      */     
/* 2083 */     vector3d1.x = paramPoint3d2.x - paramPoint3d1.x;
/* 2084 */     vector3d1.y = paramPoint3d2.y - paramPoint3d1.y;
/* 2085 */     vector3d1.z = paramPoint3d2.z - paramPoint3d1.z;
/*      */     
/* 2087 */     vector3d2.x = point3d.x - paramPoint3d1.x;
/* 2088 */     vector3d2.y = point3d.y - paramPoint3d1.y;
/* 2089 */     vector3d2.z = point3d.z - paramPoint3d1.z;
/*      */     
/* 2091 */     double d4 = vector3d1.dot(vector3d2);
/*      */     
/* 2093 */     if (d4 < 0.0D) {
/* 2094 */       return false;
/*      */     }
/* 2096 */     double d1 = vector3d1.lengthSquared();
/* 2097 */     double d2 = d4 * d4 / d1;
/*      */     
/* 2099 */     if (d2 < d1) {
/* 2100 */       return false;
/*      */     }
/* 2102 */     double d5 = d6 * d6;
/* 2103 */     double d3 = vector3d2.lengthSquared();
/*      */     
/* 2105 */     if (d3 - d2 <= d5) {
/* 2106 */       return true;
/*      */     }
/* 2108 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 2113 */   static double det2D(Point2d paramPoint2d1, Point2d paramPoint2d2, Point2d paramPoint2d3) { return (paramPoint2d3.x - paramPoint2d1.x) * (paramPoint2d1.y - paramPoint2d2.y) + (paramPoint2d1.y - paramPoint2d3.y) * (paramPoint2d1.x - paramPoint2d2.x); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean pointIntersectPolygon2D(Vector3d paramVector3d, Point3d[] paramArrayOfPoint3d, Point3d paramPoint3d) {
/*      */     byte b3;
/* 2122 */     Point2d[] arrayOfPoint2d = new Point2d[paramArrayOfPoint3d.length];
/* 2123 */     Point2d point2d = new Point2d();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2132 */     double d1 = Math.abs(paramVector3d.x);
/* 2133 */     double d2 = Math.abs(paramVector3d.y);
/* 2134 */     double d3 = Math.abs(paramVector3d.z);
/*      */     
/* 2136 */     if (d1 > d2) {
/* 2137 */       b3 = 0;
/*      */     } else {
/* 2139 */       b3 = 1;
/*      */     } 
/* 2141 */     if (!b3) {
/* 2142 */       if (d1 < d3) {
/* 2143 */         b3 = 2;
/*      */       }
/* 2145 */     } else if (b3 == 1 && 
/* 2146 */       d2 < d3) {
/* 2147 */       b3 = 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2152 */     for (byte b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 2153 */       arrayOfPoint2d[b1] = new Point2d();
/*      */       
/* 2155 */       switch (b3) {
/*      */         case 0:
/* 2157 */           (arrayOfPoint2d[b1]).x = (paramArrayOfPoint3d[b1]).y;
/* 2158 */           (arrayOfPoint2d[b1]).y = (paramArrayOfPoint3d[b1]).z;
/*      */           break;
/*      */         
/*      */         case 1:
/* 2162 */           (arrayOfPoint2d[b1]).x = (paramArrayOfPoint3d[b1]).x;
/* 2163 */           (arrayOfPoint2d[b1]).y = (paramArrayOfPoint3d[b1]).z;
/*      */           break;
/*      */         
/*      */         case 2:
/* 2167 */           (arrayOfPoint2d[b1]).x = (paramArrayOfPoint3d[b1]).x;
/* 2168 */           (arrayOfPoint2d[b1]).y = (paramArrayOfPoint3d[b1]).y;
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 2174 */     switch (b3) {
/*      */       case 0:
/* 2176 */         point2d.x = paramPoint3d.y;
/* 2177 */         point2d.y = paramPoint3d.z;
/*      */         break;
/*      */       
/*      */       case 1:
/* 2181 */         point2d.x = paramPoint3d.x;
/* 2182 */         point2d.y = paramPoint3d.z;
/*      */         break;
/*      */       
/*      */       case 2:
/* 2186 */         point2d.x = paramPoint3d.x;
/* 2187 */         point2d.y = paramPoint3d.y;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 2192 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d.length; b2++) {
/* 2193 */       if (b2 < paramArrayOfPoint3d.length - 1) {
/* 2194 */         if (det2D(arrayOfPoint2d[b2], arrayOfPoint2d[b2 + true], point2d) <= 0.0D)
/*      */         {
/*      */           
/* 2197 */           return false;
/*      */         }
/* 2199 */       } else if (det2D(arrayOfPoint2d[b2], arrayOfPoint2d[0], point2d) <= 0.0D) {
/*      */ 
/*      */         
/* 2202 */         return false;
/*      */       } 
/* 2204 */     }  return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean edgeIntersectPlane(Vector3d paramVector3d, Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4) {
/* 2211 */     Vector3d vector3d1 = new Vector3d();
/* 2212 */     Vector3d vector3d2 = new Vector3d();
/*      */ 
/*      */ 
/*      */     
/* 2216 */     vector3d1.set(paramPoint3d1);
/* 2217 */     double d1 = paramVector3d.dot(vector3d1);
/*      */     
/* 2219 */     vector3d2.x = paramPoint3d3.x - paramPoint3d2.x;
/* 2220 */     vector3d2.y = paramPoint3d3.y - paramPoint3d2.y;
/* 2221 */     vector3d2.z = paramPoint3d3.z - paramPoint3d2.z;
/*      */     
/* 2223 */     double d2 = paramVector3d.dot(vector3d2);
/*      */ 
/*      */     
/* 2226 */     if (d2 == 0.0D)
/*      */     {
/* 2228 */       return false;
/*      */     }
/*      */     
/* 2231 */     vector3d1.set(paramPoint3d2);
/*      */     
/* 2233 */     double d3 = (d1 - paramVector3d.dot(vector3d1)) / d2;
/*      */ 
/*      */ 
/*      */     
/* 2237 */     if (d3 < 0.0D || d3 > 1.0D)
/*      */     {
/* 2239 */       return false;
/*      */     }
/*      */     
/* 2242 */     paramPoint3d2.x += d3 * vector3d2.x;
/* 2243 */     paramPoint3d2.y += d3 * vector3d2.y;
/* 2244 */     paramPoint3d2.z += d3 * vector3d2.z;
/*      */     
/* 2246 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean edgeIntersectPolygon2D(Vector3d paramVector3d, Point3d[] paramArrayOfPoint3d1, Point3d[] paramArrayOfPoint3d2) {
/*      */     byte b3;
/* 2254 */     Point2d[] arrayOfPoint2d1 = new Point2d[paramArrayOfPoint3d1.length];
/* 2255 */     Point2d[] arrayOfPoint2d2 = new Point2d[2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2264 */     double d1 = Math.abs(paramVector3d.x);
/* 2265 */     double d2 = Math.abs(paramVector3d.y);
/* 2266 */     double d3 = Math.abs(paramVector3d.z);
/*      */     
/* 2268 */     if (d1 > d2) {
/* 2269 */       b3 = 0;
/*      */     } else {
/* 2271 */       b3 = 1;
/*      */     } 
/* 2273 */     if (!b3) {
/* 2274 */       if (d1 < d3) {
/* 2275 */         b3 = 2;
/*      */       }
/* 2277 */     } else if (b3 == 1 && 
/* 2278 */       d2 < d3) {
/* 2279 */       b3 = 2;
/*      */     } 
/*      */     
/*      */     byte b1;
/*      */     
/* 2284 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/* 2285 */       arrayOfPoint2d1[b1] = new Point2d();
/*      */       
/* 2287 */       switch (b3) {
/*      */         case 0:
/* 2289 */           (arrayOfPoint2d1[b1]).x = (paramArrayOfPoint3d1[b1]).y;
/* 2290 */           (arrayOfPoint2d1[b1]).y = (paramArrayOfPoint3d1[b1]).z;
/*      */           break;
/*      */         
/*      */         case 1:
/* 2294 */           (arrayOfPoint2d1[b1]).x = (paramArrayOfPoint3d1[b1]).x;
/* 2295 */           (arrayOfPoint2d1[b1]).y = (paramArrayOfPoint3d1[b1]).z;
/*      */           break;
/*      */         
/*      */         case 2:
/* 2299 */           (arrayOfPoint2d1[b1]).x = (paramArrayOfPoint3d1[b1]).x;
/* 2300 */           (arrayOfPoint2d1[b1]).y = (paramArrayOfPoint3d1[b1]).y;
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 2306 */     for (b1 = 0; b1 < 2; b1++) {
/* 2307 */       arrayOfPoint2d2[b1] = new Point2d();
/* 2308 */       switch (b3) {
/*      */         case 0:
/* 2310 */           (arrayOfPoint2d2[b1]).x = (paramArrayOfPoint3d2[b1]).y;
/* 2311 */           (arrayOfPoint2d2[b1]).y = (paramArrayOfPoint3d2[b1]).z;
/*      */           break;
/*      */         
/*      */         case 1:
/* 2315 */           (arrayOfPoint2d2[b1]).x = (paramArrayOfPoint3d2[b1]).x;
/* 2316 */           (arrayOfPoint2d2[b1]).y = (paramArrayOfPoint3d2[b1]).z;
/*      */           break;
/*      */         
/*      */         case 2:
/* 2320 */           (arrayOfPoint2d2[b1]).x = (paramArrayOfPoint3d2[b1]).x;
/* 2321 */           (arrayOfPoint2d2[b1]).y = (paramArrayOfPoint3d2[b1]).y;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 2328 */     boolean[][] arrayOfBoolean = new boolean[2][paramArrayOfPoint3d1.length];
/*      */ 
/*      */     
/* 2331 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d1.length; b2++) {
/* 2332 */       for (b1 = 0; b1 < 2; b1++) {
/* 2333 */         if (b2 < paramArrayOfPoint3d1.length - 1) {
/* 2334 */           arrayOfBoolean[b1][b2] = (det2D(arrayOfPoint2d1[b2], arrayOfPoint2d1[b2 + true], arrayOfPoint2d2[b1]) < 0.0D);
/*      */         } else {
/* 2336 */           arrayOfBoolean[b1][b2] = (det2D(arrayOfPoint2d1[b2], arrayOfPoint2d1[0], arrayOfPoint2d2[b1]) < 0.0D);
/*      */         } 
/*      */       } 
/* 2339 */       if (!arrayOfBoolean[0][b2] && !arrayOfBoolean[1][b2]) {
/* 2340 */         return false;
/*      */       }
/*      */     } 
/* 2343 */     boolean bool = true;
/* 2344 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/* 2345 */       if (!arrayOfBoolean[0][b1]) {
/* 2346 */         bool = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 2351 */     if (bool == true) {
/* 2352 */       return true;
/*      */     }
/* 2354 */     bool = true;
/* 2355 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/* 2356 */       if (!arrayOfBoolean[1][b1]) {
/* 2357 */         bool = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 2362 */     if (bool == true) {
/* 2363 */       return true;
/*      */     }
/*      */     
/* 2366 */     byte b4 = 0;
/* 2367 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/* 2368 */       if (det2D(arrayOfPoint2d2[0], arrayOfPoint2d2[1], arrayOfPoint2d1[b1]) < 0.0D) {
/* 2369 */         b4++;
/*      */       }
/*      */     } 
/* 2372 */     if (b4 == 0 || b4 == paramArrayOfPoint3d1.length) {
/* 2373 */       return false;
/*      */     }
/* 2375 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectPolygon(Point3d[] paramArrayOfPoint3d1, Point3d[] paramArrayOfPoint3d2, boolean paramBoolean) {
/* 2381 */     Vector3d vector3d1 = new Vector3d();
/* 2382 */     Vector3d vector3d2 = new Vector3d();
/* 2383 */     Vector3d vector3d3 = new Vector3d();
/*      */     
/*      */     byte b1;
/*      */     
/* 2387 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length - 1; ) {
/* 2388 */       vector3d1.x = (paramArrayOfPoint3d1[b1 + true]).x - (paramArrayOfPoint3d1[b1]).x;
/* 2389 */       vector3d1.y = (paramArrayOfPoint3d1[b1 + true]).y - (paramArrayOfPoint3d1[b1]).y;
/* 2390 */       vector3d1.z = (paramArrayOfPoint3d1[b1 + true]).z - (paramArrayOfPoint3d1[b1++]).z;
/* 2391 */       if (vector3d1.length() > 0.0D)
/*      */         break; 
/*      */     } 
/*      */     byte b2;
/* 2395 */     for (b2 = b1; b2 < paramArrayOfPoint3d1.length - 1; b2++) {
/* 2396 */       vector3d2.x = (paramArrayOfPoint3d1[b2 + 1]).x - (paramArrayOfPoint3d1[b2]).x;
/* 2397 */       vector3d2.y = (paramArrayOfPoint3d1[b2 + 1]).y - (paramArrayOfPoint3d1[b2]).y;
/* 2398 */       vector3d2.z = (paramArrayOfPoint3d1[b2 + 1]).z - (paramArrayOfPoint3d1[b2]).z;
/* 2399 */       if (vector3d2.length() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/* 2403 */     if (b2 == paramArrayOfPoint3d1.length - 1)
/*      */     {
/* 2405 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2415 */     vector3d3.cross(vector3d1, vector3d2);
/*      */     
/* 2417 */     if (vector3d3.length() == 0.0D)
/*      */     {
/* 2419 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2423 */     if (paramBoolean == true);
/*      */ 
/*      */ 
/*      */     
/* 2427 */     b2 = 0;
/* 2428 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 2429 */     arrayOfPoint3d[0] = new Point3d();
/* 2430 */     arrayOfPoint3d[1] = new Point3d();
/*      */     
/* 2432 */     for (b1 = 0; b1 < paramArrayOfPoint3d2.length; b1++) {
/* 2433 */       boolean bool; if (b1 < paramArrayOfPoint3d2.length - 1) {
/* 2434 */         bool = edgeIntersectPlane(vector3d3, paramArrayOfPoint3d1[0], paramArrayOfPoint3d2[b1], paramArrayOfPoint3d2[b1 + 1], arrayOfPoint3d[b2]);
/*      */       } else {
/*      */         
/* 2437 */         bool = edgeIntersectPlane(vector3d3, paramArrayOfPoint3d1[0], paramArrayOfPoint3d2[b1], paramArrayOfPoint3d2[0], arrayOfPoint3d[b2]);
/*      */       } 
/*      */       
/* 2440 */       b2++;
/* 2441 */       if (bool == true && b2 > 1) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/* 2446 */     if (b2 == 0) {
/* 2447 */       return false;
/*      */     }
/* 2449 */     if (paramArrayOfPoint3d2.length < 3) {
/* 2450 */       return pointIntersectPolygon2D(vector3d3, paramArrayOfPoint3d1, arrayOfPoint3d[0]);
/*      */     }
/* 2452 */     return edgeIntersectPolygon2D(vector3d3, paramArrayOfPoint3d1, arrayOfPoint3d);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 2457 */   static final boolean isNonZero(double paramDouble) { return (paramDouble > 1.0E-13D || paramDouble < -1.0E-13D); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectRay(Point3d[] paramArrayOfPoint3d, PickRay paramPickRay, PickIntersection paramPickIntersection) {
/* 2464 */     Point3d point3d = new Point3d();
/* 2465 */     Vector3d vector3d = new Vector3d();
/*      */     
/* 2467 */     paramPickRay.get(point3d, vector3d);
/* 2468 */     return intersectRayOrSegment(paramArrayOfPoint3d, vector3d, point3d, paramPickIntersection, false);
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
/*      */   static boolean intersectRayOrSegment(Point3d[] paramArrayOfPoint3d, Vector3d paramVector3d, Point3d paramPoint3d, PickIntersection paramPickIntersection, boolean paramBoolean) {
/* 2482 */     Vector3d vector3d1 = new Vector3d();
/* 2483 */     Vector3d vector3d2 = new Vector3d();
/* 2484 */     Vector3d vector3d3 = new Vector3d();
/*      */     
/* 2486 */     double d4 = 0.0D;
/* 2487 */     double d5 = 0.0D;
/*      */     
/* 2489 */     null = false;
/* 2490 */     int i = 0; boolean bool = false;
/*      */     
/*      */     byte b1;
/* 2493 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 2494 */       if (b1 != paramArrayOfPoint3d.length - 1) {
/* 2495 */         bool = b1 + true;
/*      */       } else {
/* 2497 */         bool = false;
/*      */       } 
/* 2499 */       vector3d1.x = (paramArrayOfPoint3d[bool]).x - (paramArrayOfPoint3d[b1]).x;
/* 2500 */       vector3d1.y = (paramArrayOfPoint3d[bool]).y - (paramArrayOfPoint3d[b1]).y;
/* 2501 */       vector3d1.z = (paramArrayOfPoint3d[bool]).z - (paramArrayOfPoint3d[b1]).z;
/* 2502 */       if (vector3d1.length() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2508 */     for (byte b2 = bool; b2 < paramArrayOfPoint3d.length; b2++) {
/* 2509 */       if (b2 != paramArrayOfPoint3d.length - 1) {
/* 2510 */         i = b2 + true;
/*      */       } else {
/* 2512 */         i = 0;
/*      */       } 
/* 2514 */       vector3d2.x = (paramArrayOfPoint3d[i]).x - (paramArrayOfPoint3d[b2]).x;
/* 2515 */       vector3d2.y = (paramArrayOfPoint3d[i]).y - (paramArrayOfPoint3d[b2]).y;
/* 2516 */       vector3d2.z = (paramArrayOfPoint3d[i]).z - (paramArrayOfPoint3d[b2]).z;
/* 2517 */       if (vector3d2.length() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/* 2522 */     vector3d3.cross(vector3d1, vector3d2);
/*      */     
/* 2524 */     if (vector3d2.length() == 0.0D || vector3d3.length() == 0.0D) {
/*      */ 
/*      */       
/* 2527 */       i = !bool ? (paramArrayOfPoint3d.length - 1) : (bool - true);
/* 2528 */       return intersectLineAndRay(paramArrayOfPoint3d[bool], paramArrayOfPoint3d[i], paramPoint3d, paramVector3d, paramPickIntersection);
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
/* 2539 */     d5 = vector3d3.dot(paramVector3d);
/*      */ 
/*      */     
/* 2542 */     if (d5 == 0.0D) {
/*      */ 
/*      */       
/* 2545 */       for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 2546 */         if (b1 != paramArrayOfPoint3d.length - 1) {
/* 2547 */           i = b1 + 1;
/*      */         } else {
/* 2549 */           i = 0;
/*      */         } 
/* 2551 */         if (intersectLineAndRay(paramArrayOfPoint3d[b1], paramArrayOfPoint3d[i], paramPoint3d, paramVector3d, paramPickIntersection)) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2556 */           null = true;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2560 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 2564 */     Vector3d vector3d4 = new Vector3d();
/* 2565 */     vector3d4.set(paramArrayOfPoint3d[0]);
/* 2566 */     d4 = vector3d3.dot(vector3d4);
/* 2567 */     vector3d4.set(paramPoint3d);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2573 */     double d6 = (d4 - vector3d3.dot(vector3d4)) / d5;
/*      */ 
/*      */     
/* 2576 */     if (d6 < -1.0E-13D || (paramBoolean && d6 > 1.0000000000001D))
/*      */     {
/*      */ 
/*      */       
/* 2580 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2585 */     Point3d point3d1 = new Point3d();
/* 2586 */     paramPoint3d.x += paramVector3d.x * d6;
/* 2587 */     paramPoint3d.y += paramVector3d.y * d6;
/* 2588 */     paramPoint3d.z += paramVector3d.z * d6;
/*      */ 
/*      */ 
/*      */     
/* 2592 */     double d1 = Math.abs(vector3d3.x);
/* 2593 */     double d2 = Math.abs(vector3d3.y);
/* 2594 */     double d3 = Math.abs(vector3d3.z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2601 */     double d7 = 0.0D;
/* 2602 */     Point3d point3d2 = paramArrayOfPoint3d[paramArrayOfPoint3d.length - 1];
/* 2603 */     Point3d point3d3 = paramArrayOfPoint3d[0];
/*      */     
/* 2605 */     null = true;
/*      */     
/* 2607 */     if (d1 > d2) {
/* 2608 */       if (d1 < d3) {
/* 2609 */         for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 2610 */           point3d2 = paramArrayOfPoint3d[b1];
/* 2611 */           point3d3 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/* 2612 */           double d = (point3d1.y - point3d2.y) * (point3d3.x - point3d2.x) - (point3d1.x - point3d2.x) * (point3d3.y - point3d2.y);
/*      */           
/* 2614 */           if (isNonZero(d)) {
/* 2615 */             if (d * d7 < 0.0D) {
/* 2616 */               null = false;
/*      */               break;
/*      */             } 
/* 2619 */             d7 = d;
/*      */           } else {
/* 2621 */             double d8 = point3d3.y - point3d2.y;
/* 2622 */             if (isNonZero(d8)) {
/* 2623 */               d8 = (point3d1.y - point3d2.y) / d8;
/* 2624 */               null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */               break;
/*      */             } 
/* 2627 */             d8 = point3d3.x - point3d2.x;
/* 2628 */             if (isNonZero(d8)) {
/* 2629 */               d8 = (point3d1.x - point3d2.x) / d8;
/* 2630 */               null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */ 
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/* 2639 */         for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 2640 */           point3d2 = paramArrayOfPoint3d[b1];
/* 2641 */           point3d3 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/* 2642 */           double d = (point3d1.y - point3d2.y) * (point3d3.z - point3d2.z) - (point3d1.z - point3d2.z) * (point3d3.y - point3d2.y);
/*      */           
/* 2644 */           if (isNonZero(d)) {
/* 2645 */             if (d * d7 < 0.0D) {
/* 2646 */               null = false;
/*      */               break;
/*      */             } 
/* 2649 */             d7 = d;
/*      */           } else {
/* 2651 */             double d8 = point3d3.y - point3d2.y;
/*      */             
/* 2653 */             if (isNonZero(d8)) {
/* 2654 */               d8 = (point3d1.y - point3d2.y) / d8;
/* 2655 */               null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */               
/*      */               break;
/*      */             } 
/* 2659 */             d8 = point3d3.z - point3d2.z;
/* 2660 */             if (isNonZero(d8)) {
/* 2661 */               d8 = (point3d1.z - point3d2.z) / d8;
/* 2662 */               null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 2672 */     } else if (d2 < d3) {
/* 2673 */       for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 2674 */         point3d2 = paramArrayOfPoint3d[b1];
/* 2675 */         point3d3 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/* 2676 */         double d = (point3d1.y - point3d2.y) * (point3d3.x - point3d2.x) - (point3d1.x - point3d2.x) * (point3d3.y - point3d2.y);
/*      */         
/* 2678 */         if (isNonZero(d)) {
/* 2679 */           if (d * d7 < 0.0D) {
/* 2680 */             null = false;
/*      */             break;
/*      */           } 
/* 2683 */           d7 = d;
/*      */         } else {
/* 2685 */           double d8 = point3d3.y - point3d2.y;
/* 2686 */           if (isNonZero(d8)) {
/* 2687 */             d8 = (point3d1.y - point3d2.y) / d8;
/* 2688 */             null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */             break;
/*      */           } 
/* 2691 */           d8 = point3d3.x - point3d2.x;
/* 2692 */           if (isNonZero(d8)) {
/* 2693 */             d8 = (point3d1.x - point3d2.x) / d8;
/* 2694 */             null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 2703 */       for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/* 2704 */         point3d2 = paramArrayOfPoint3d[b1];
/* 2705 */         point3d3 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/* 2706 */         double d = (point3d1.x - point3d2.x) * (point3d3.z - point3d2.z) - (point3d1.z - point3d2.z) * (point3d3.x - point3d2.x);
/*      */         
/* 2708 */         if (isNonZero(d)) {
/* 2709 */           if (d * d7 < 0.0D) {
/* 2710 */             null = false;
/*      */             break;
/*      */           } 
/* 2713 */           d7 = d;
/*      */         } else {
/* 2715 */           double d8 = point3d3.x - point3d2.x;
/* 2716 */           if (isNonZero(d8)) {
/* 2717 */             d8 = (point3d1.x - point3d2.x) / d8;
/* 2718 */             null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */             break;
/*      */           } 
/* 2721 */           d8 = point3d3.z - point3d2.z;
/* 2722 */           if (isNonZero(d8)) {
/* 2723 */             d8 = (point3d1.z - point3d2.z) / d8;
/* 2724 */             null = (d8 > -1.0E-13D && d8 < 1.0000000000001D);
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2735 */     if (null) {
/* 2736 */       paramPickIntersection.setDistance(d6 * paramVector3d.length());
/* 2737 */       paramPickIntersection.setPointCoordinatesVW(point3d1);
/*      */     } 
/* 2739 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectSegment(Point3d[] paramArrayOfPoint3d, PickSegment paramPickSegment, PickIntersection paramPickIntersection) {
/* 2749 */     Point3d point3d1 = new Point3d();
/* 2750 */     Point3d point3d2 = new Point3d();
/* 2751 */     Vector3d vector3d = new Vector3d();
/*      */     
/* 2753 */     paramPickSegment.get(point3d1, point3d2);
/* 2754 */     vector3d.x = point3d2.x - point3d1.x;
/* 2755 */     vector3d.y = point3d2.y - point3d1.y;
/* 2756 */     vector3d.z = point3d2.z - point3d1.z;
/* 2757 */     return intersectRayOrSegment(paramArrayOfPoint3d, vector3d, point3d1, paramPickIntersection, true);
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
/*      */   static boolean inside(Point3d[] paramArrayOfPoint3d, PickPoint paramPickPoint, int paramInt) {
/* 2769 */     Vector3d vector3d1 = new Vector3d();
/* 2770 */     Vector3d vector3d2 = new Vector3d();
/* 2771 */     Vector3d vector3d3 = new Vector3d();
/* 2772 */     double d1 = 0.0D;
/* 2773 */     Vector3d vector3d4 = new Vector3d();
/* 2774 */     double d2 = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2780 */     Point3d point3d = new Point3d();
/* 2781 */     paramPickPoint.get(point3d);
/*      */     
/*      */     byte b1;
/* 2784 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length - 1; ) {
/* 2785 */       vector3d1.x = (paramArrayOfPoint3d[b1 + true]).x - (paramArrayOfPoint3d[b1]).x;
/* 2786 */       vector3d1.y = (paramArrayOfPoint3d[b1 + true]).y - (paramArrayOfPoint3d[b1]).y;
/* 2787 */       vector3d1.z = (paramArrayOfPoint3d[b1 + true]).z - (paramArrayOfPoint3d[b1++]).z;
/* 2788 */       if (vector3d1.length() > 0.0D)
/*      */         break; 
/*      */     } 
/*      */     byte b2;
/* 2792 */     for (b2 = b1; b2 < paramArrayOfPoint3d.length - 1; b2++) {
/* 2793 */       vector3d2.x = (paramArrayOfPoint3d[b2 + 1]).x - (paramArrayOfPoint3d[b2]).x;
/* 2794 */       vector3d2.y = (paramArrayOfPoint3d[b2 + 1]).y - (paramArrayOfPoint3d[b2]).y;
/* 2795 */       vector3d2.z = (paramArrayOfPoint3d[b2 + 1]).z - (paramArrayOfPoint3d[b2]).z;
/* 2796 */       if (vector3d2.length() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/* 2800 */     if (b2 == paramArrayOfPoint3d.length - 1)
/*      */     {
/* 2802 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2812 */     if (paramInt == 1) {
/* 2813 */       vector3d3.cross(vector3d1, vector3d2);
/*      */     } else {
/* 2815 */       vector3d3.cross(vector3d2, vector3d1);
/*      */     } 
/* 2817 */     if (vector3d3.length() == 0.0D)
/*      */     {
/* 2819 */       return false;
/*      */     }
/*      */     
/* 2822 */     vector3d4.set(paramArrayOfPoint3d[0]);
/* 2823 */     d1 = vector3d3.dot(vector3d4);
/*      */     
/* 2825 */     vector3d4.set(point3d);
/*      */     
/* 2827 */     if (d1 - vector3d3.dot(vector3d4) > 0.0D)
/*      */     {
/* 2829 */       return false;
/*      */     }
/*      */     
/* 2832 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectPntAndPnt(Point3d paramPoint3d1, Point3d paramPoint3d2, PickIntersection paramPickIntersection) {
/* 2838 */     if (paramPoint3d1.x == paramPoint3d2.x && paramPoint3d1.y == paramPoint3d2.y && paramPoint3d1.z == paramPoint3d2.z) {
/* 2839 */       paramPickIntersection.setPointCoordinatesVW(paramPoint3d1);
/* 2840 */       paramPickIntersection.setDistance(0.0D);
/* 2841 */       return true;
/*      */     } 
/*      */     
/* 2844 */     return false;
/*      */   }
/*      */   
/*      */   static boolean intersectPntAndRay(Point3d paramPoint3d1, Point3d paramPoint3d2, Vector3d paramVector3d, PickIntersection paramPickIntersection) {
/*      */     double d;
/* 2849 */     byte b = 0;
/*      */ 
/*      */ 
/*      */     
/* 2853 */     if (paramVector3d.x != 0.0D) {
/* 2854 */       b = 0;
/* 2855 */       d = (paramPoint3d1.x - paramPoint3d2.x) / paramVector3d.x;
/*      */     }
/* 2857 */     else if (paramVector3d.y != 0.0D) {
/* 2858 */       if (paramPoint3d1.x != paramPoint3d2.x)
/* 2859 */         return false; 
/* 2860 */       b = 1;
/* 2861 */       d = (paramPoint3d1.y - paramPoint3d2.y) / paramVector3d.y;
/*      */     }
/* 2863 */     else if (paramVector3d.z != 0.0D) {
/* 2864 */       if (paramPoint3d1.x != paramPoint3d2.x || paramPoint3d1.y != paramPoint3d2.y)
/* 2865 */         return false; 
/* 2866 */       b = 2;
/* 2867 */       d = (paramPoint3d1.z - paramPoint3d2.z) / paramVector3d.z;
/*      */     }
/*      */     else {
/*      */       
/* 2871 */       return false;
/*      */     } 
/* 2873 */     if (d < 0.0D) {
/* 2874 */       return false;
/*      */     }
/* 2876 */     if (b == 0) {
/* 2877 */       double d1 = paramPoint3d2.y + d * paramVector3d.y;
/* 2878 */       if (paramPoint3d1.y < d1 - Double.MIN_VALUE || paramPoint3d1.y > d1 + Double.MIN_VALUE) {
/* 2879 */         return false;
/*      */       }
/*      */     } 
/* 2882 */     if (b < 2) {
/* 2883 */       double d1 = paramPoint3d2.z + d * paramVector3d.z;
/* 2884 */       if (paramPoint3d1.z < d1 - Double.MIN_VALUE || paramPoint3d1.z > d1 + Double.MIN_VALUE) {
/* 2885 */         return false;
/*      */       }
/*      */     } 
/* 2888 */     paramPickIntersection.setPointCoordinatesVW(paramPoint3d1);
/* 2889 */     paramPickIntersection.setDistance(d);
/*      */     
/* 2891 */     return true;
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
/*      */   static boolean intersectLineAndRay(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Vector3d paramVector3d, PickIntersection paramPickIntersection) {
/* 2909 */     Vector3d vector3d = new Vector3d(paramPoint3d2.x - paramPoint3d1.x, paramPoint3d2.y - paramPoint3d1.y, paramPoint3d2.z - paramPoint3d1.z);
/*      */ 
/*      */     
/* 2912 */     double d1 = vector3d.x;
/* 2913 */     double d2 = -paramVector3d.x;
/* 2914 */     double d3 = vector3d.y;
/* 2915 */     double d4 = -paramVector3d.y;
/*      */ 
/*      */     
/* 2918 */     double d9 = d1 * d4 - d3 * d2;
/*      */     
/* 2920 */     if (d9 == 0.0D) {
/*      */       
/* 2922 */       boolean bool = false;
/* 2923 */       if (vector3d.x == 0.0D && vector3d.y == 0.0D && vector3d.z == 0.0D) {
/* 2924 */         bool = intersectPntAndRay(paramPoint3d1, paramPoint3d3, paramVector3d, paramPickIntersection);
/* 2925 */         if (bool) {
/* 2926 */           paramPickIntersection.setPointCoordinatesVW(paramPoint3d1);
/* 2927 */           paramPickIntersection.setDistance(0.0D);
/*      */         } 
/*      */       } 
/* 2930 */       return bool;
/*      */     } 
/*      */     
/* 2933 */     double d12 = 1.0D / d9;
/*      */     
/* 2935 */     double d5 = d12 * d4;
/* 2936 */     double d6 = d12 * -d2;
/* 2937 */     double d7 = d12 * -d3;
/* 2938 */     double d8 = d12 * d1;
/*      */     
/* 2940 */     d12 = paramPoint3d3.x - paramPoint3d1.x;
/* 2941 */     double d13 = paramPoint3d3.y - paramPoint3d1.y;
/*      */     
/* 2943 */     double d10 = d5 * d12 + d6 * d13;
/* 2944 */     double d11 = d7 * d12 + d8 * d13;
/*      */     
/* 2946 */     if (d11 < 0.0D)
/*      */     {
/* 2948 */       return false;
/*      */     }
/* 2950 */     if (d10 < 0.0D || d10 > 1.0D)
/*      */     {
/* 2952 */       return false;
/*      */     }
/*      */     
/* 2955 */     d12 = paramPoint3d3.z + d11 * paramVector3d.z;
/* 2956 */     d13 = paramPoint3d1.z + d10 * vector3d.z;
/*      */     
/* 2958 */     if (d12 < d13 - Double.MIN_VALUE || d12 > d13 + Double.MIN_VALUE)
/*      */     {
/*      */       
/* 2961 */       return false;
/*      */     }
/* 2963 */     double d14 = d11;
/*      */     
/* 2965 */     paramPickIntersection.setDistance(d14);
/* 2966 */     Point3d point3d = new Point3d();
/* 2967 */     point3d.scaleAdd(d11, paramVector3d, paramPoint3d3);
/* 2968 */     paramPickIntersection.setPointCoordinatesVW(point3d);
/*      */ 
/*      */     
/* 2971 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectCylinder(Point3d[] paramArrayOfPoint3d, PickCylinder paramPickCylinder, PickIntersection paramPickIntersection) {
/* 2981 */     Point3d point3d1 = new Point3d();
/* 2982 */     Point3d point3d2 = new Point3d();
/* 2983 */     Vector3d vector3d1 = new Vector3d();
/* 2984 */     Point3d point3d3 = new Point3d();
/* 2985 */     Point3d point3d4 = new Point3d();
/* 2986 */     Vector3d vector3d2 = new Vector3d();
/*      */ 
/*      */     
/* 2989 */     paramPickCylinder.getOrigin(point3d1);
/* 2990 */     paramPickCylinder.getDirection(vector3d1);
/* 2991 */     double d = paramPickCylinder.getRadius();
/*      */     
/* 2993 */     if (paramPickCylinder instanceof PickCylinderSegment) {
/* 2994 */       ((PickCylinderSegment)paramPickCylinder).getEnd(point3d2);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2999 */     if (paramArrayOfPoint3d.length > 2) {
/* 3000 */       if (paramPickCylinder instanceof javax.media.j3d.PickCylinderRay) {
/* 3001 */         if (intersectRay(paramArrayOfPoint3d, new PickRay(point3d1, vector3d1), paramPickIntersection)) {
/* 3002 */           return true;
/*      */         
/*      */         }
/*      */       }
/* 3006 */       else if (intersectSegment(paramArrayOfPoint3d, new PickSegment(point3d1, point3d2), paramPickIntersection)) {
/* 3007 */         return true;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3014 */     for (byte b = 0; b < paramArrayOfPoint3d.length - 1; b++) {
/* 3015 */       double d1; if (paramPickCylinder instanceof PickCylinderSegment) {
/* 3016 */         d1 = Distance.segmentToSegment(point3d1, point3d2, paramArrayOfPoint3d[b], paramArrayOfPoint3d[b + true], point3d3, point3d4, null);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 3022 */         d1 = Distance.rayToSegment(point3d1, vector3d1, paramArrayOfPoint3d[b], paramArrayOfPoint3d[b + true], point3d3, point3d4, null);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3027 */       if (d1 <= d * d) {
/* 3028 */         paramPickIntersection.setPointCoordinatesVW(point3d4);
/* 3029 */         vector3d2.sub(point3d3, point3d1);
/* 3030 */         paramPickIntersection.setDistance(vector3d2.length());
/* 3031 */         return true;
/*      */       } 
/*      */     } 
/* 3034 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectCone(Point3d[] paramArrayOfPoint3d, PickCone paramPickCone, PickIntersection paramPickIntersection) {
/* 3044 */     Point3d point3d1 = new Point3d();
/* 3045 */     Point3d point3d2 = new Point3d();
/* 3046 */     Vector3d vector3d1 = new Vector3d();
/* 3047 */     Vector3d vector3d2 = new Vector3d();
/*      */ 
/*      */     
/* 3050 */     Point3d point3d3 = new Point3d();
/* 3051 */     Point3d point3d4 = new Point3d();
/* 3052 */     Vector3d vector3d3 = new Vector3d();
/*      */ 
/*      */     
/* 3055 */     paramPickCone.getOrigin(point3d1);
/* 3056 */     paramPickCone.getDirection(vector3d1);
/*      */ 
/*      */     
/* 3059 */     if (paramPickCone instanceof PickConeSegment) {
/* 3060 */       ((PickConeSegment)paramPickCone).getEnd(point3d2);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3065 */     if (paramArrayOfPoint3d.length > 2) {
/* 3066 */       if (paramPickCone instanceof javax.media.j3d.PickConeRay) {
/* 3067 */         if (intersectRay(paramArrayOfPoint3d, new PickRay(point3d1, vector3d1), paramPickIntersection)) {
/* 3068 */           return true;
/*      */         
/*      */         }
/*      */       }
/* 3072 */       else if (intersectSegment(paramArrayOfPoint3d, new PickSegment(point3d1, point3d2), paramPickIntersection)) {
/*      */         
/* 3074 */         return true;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3081 */     for (byte b = 0; b < paramArrayOfPoint3d.length - 1; b++) {
/* 3082 */       double d3; if (paramPickCone instanceof PickConeSegment) {
/* 3083 */         d3 = Distance.segmentToSegment(point3d1, point3d2, paramArrayOfPoint3d[b], paramArrayOfPoint3d[b + true], point3d3, point3d4, null);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 3089 */         d3 = Distance.rayToSegment(point3d1, vector3d1, paramArrayOfPoint3d[b], paramArrayOfPoint3d[b + true], point3d3, point3d4, null);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3094 */       vector3d2.sub(point3d3, point3d1);
/* 3095 */       double d1 = vector3d2.length();
/* 3096 */       double d2 = Math.tan(paramPickCone.getSpreadAngle()) * d1;
/* 3097 */       if (d3 <= d2 * d2) {
/*      */         
/* 3099 */         paramPickIntersection.setPointCoordinatesVW(point3d4);
/* 3100 */         paramPickIntersection.setDistance(d1);
/* 3101 */         return true;
/*      */       } 
/*      */     } 
/* 3104 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectCylinder(Point3d paramPoint3d, PickCylinder paramPickCylinder, PickIntersection paramPickIntersection) {
/*      */     double d2;
/* 3115 */     Point3d point3d1 = new Point3d();
/* 3116 */     Point3d point3d2 = new Point3d();
/* 3117 */     Vector3d vector3d1 = new Vector3d();
/* 3118 */     Point3d point3d3 = new Point3d();
/* 3119 */     Vector3d vector3d2 = new Vector3d();
/*      */ 
/*      */     
/* 3122 */     paramPickCylinder.getOrigin(point3d1);
/* 3123 */     paramPickCylinder.getDirection(vector3d1);
/* 3124 */     double d1 = paramPickCylinder.getRadius();
/*      */ 
/*      */     
/* 3127 */     if (paramPickCylinder instanceof PickCylinderSegment) {
/* 3128 */       ((PickCylinderSegment)paramPickCylinder).getEnd(point3d2);
/* 3129 */       d2 = Distance.pointToSegment(paramPoint3d, point3d1, point3d2, point3d3, null);
/*      */     } else {
/*      */       
/* 3132 */       d2 = Distance.pointToRay(paramPoint3d, point3d1, vector3d1, point3d3, null);
/*      */     } 
/* 3134 */     if (d2 <= d1 * d1) {
/* 3135 */       paramPickIntersection.setPointCoordinatesVW(paramPoint3d);
/* 3136 */       vector3d2.sub(point3d3, point3d1);
/* 3137 */       paramPickIntersection.setDistance(vector3d2.length());
/* 3138 */       return true;
/*      */     } 
/* 3140 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean intersectCone(Point3d paramPoint3d, PickCone paramPickCone, PickIntersection paramPickIntersection) {
/*      */     double d3;
/* 3151 */     Point3d point3d1 = new Point3d();
/* 3152 */     Point3d point3d2 = new Point3d();
/* 3153 */     Vector3d vector3d1 = new Vector3d();
/* 3154 */     Point3d point3d3 = new Point3d();
/* 3155 */     Vector3d vector3d2 = new Vector3d();
/*      */ 
/*      */     
/* 3158 */     paramPickCone.getOrigin(point3d1);
/* 3159 */     paramPickCone.getDirection(vector3d1);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3164 */     if (paramPickCone instanceof PickConeSegment) {
/* 3165 */       ((PickConeSegment)paramPickCone).getEnd(point3d2);
/* 3166 */       d3 = Distance.pointToSegment(paramPoint3d, point3d1, point3d2, point3d3, null);
/*      */     } else {
/*      */       
/* 3169 */       d3 = Distance.pointToRay(paramPoint3d, point3d1, vector3d1, point3d3, null);
/*      */     } 
/* 3171 */     vector3d2.sub(point3d3, point3d1);
/* 3172 */     double d2 = vector3d2.length();
/* 3173 */     double d1 = Math.tan(paramPickCone.getSpreadAngle()) * d2;
/* 3174 */     if (d3 <= d1 * d1) {
/* 3175 */       paramPickIntersection.setPointCoordinatesVW(paramPoint3d);
/* 3176 */       paramPickIntersection.setDistance(d2);
/* 3177 */       return true;
/*      */     } 
/* 3179 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\picking\PickResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */