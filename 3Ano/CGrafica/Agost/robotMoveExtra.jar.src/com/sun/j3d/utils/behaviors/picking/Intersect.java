/*      */ package com.sun.j3d.utils.behaviors.picking;
/*      */ 
/*      */ import com.sun.j3d.internal.J3dUtilsI18N;
/*      */ import javax.media.j3d.PickPoint;
/*      */ import javax.media.j3d.PickRay;
/*      */ import javax.media.j3d.PickSegment;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
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
/*      */ public class Intersect
/*      */ {
/*      */   public static boolean rayAndQuad(PickRay paramPickRay, Point3d[] paramArrayOfPoint3d, int paramInt, double[] paramArrayOfDouble) {
/*   86 */     if (paramArrayOfPoint3d.length - paramInt < 4) {
/*   87 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect0"));
/*      */     }
/*   89 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*      */     
/*   91 */     for (int i = 0; i < 4; i++) {
/*   92 */       arrayOfPoint3d[i] = paramArrayOfPoint3d[paramInt + i];
/*      */     }
/*   94 */     return rayAndPoly(arrayOfPoint3d, paramPickRay, paramArrayOfDouble);
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
/*      */   public static boolean rayAndTriangle(PickRay paramPickRay, Point3d[] paramArrayOfPoint3d, int paramInt, double[] paramArrayOfDouble) {
/*  117 */     if (paramArrayOfPoint3d.length - paramInt < 3) {
/*  118 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect1"));
/*      */     }
/*  120 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*      */     
/*  122 */     for (int i = 0; i < 3; i++) {
/*  123 */       arrayOfPoint3d[i] = paramArrayOfPoint3d[paramInt + i];
/*      */     }
/*  125 */     return rayAndPoly(arrayOfPoint3d, paramPickRay, paramArrayOfDouble);
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
/*      */   public static boolean rayAndTriangle(PickRay paramPickRay, Point3f[] paramArrayOfPoint3f, int paramInt, double[] paramArrayOfDouble) {
/*  145 */     if (paramArrayOfPoint3f.length - paramInt < 3) {
/*  146 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect1"));
/*      */     }
/*  148 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*      */     
/*  150 */     for (int i = 0; i < 3; i++) {
/*  151 */       arrayOfPoint3d[i] = new Point3d(paramArrayOfPoint3f[paramInt + i]);
/*      */     }
/*  153 */     return rayAndPoly(arrayOfPoint3d, paramPickRay, paramArrayOfDouble);
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
/*      */   public static boolean segmentAndQuad(PickSegment paramPickSegment, Point3d[] paramArrayOfPoint3d, int paramInt, double[] paramArrayOfDouble) {
/*  176 */     if (paramArrayOfPoint3d.length - paramInt < 4) {
/*  177 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect3"));
/*      */     }
/*  179 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*      */     
/*  181 */     for (int i = 0; i < 4; i++) {
/*  182 */       arrayOfPoint3d[i] = paramArrayOfPoint3d[paramInt + i];
/*      */     }
/*  184 */     return segmentAndPoly(arrayOfPoint3d, paramPickSegment, paramArrayOfDouble);
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
/*      */   public static boolean segmentAndQuad(PickSegment paramPickSegment, Point3f[] paramArrayOfPoint3f, int paramInt, double[] paramArrayOfDouble) {
/*  203 */     if (paramArrayOfPoint3f.length - paramInt < 4) {
/*  204 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect3"));
/*      */     }
/*  206 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*      */     
/*  208 */     for (int i = 0; i < 4; i++) {
/*  209 */       arrayOfPoint3d[i] = new Point3d(paramArrayOfPoint3f[paramInt + i]);
/*      */     }
/*  211 */     return segmentAndPoly(arrayOfPoint3d, paramPickSegment, paramArrayOfDouble);
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
/*      */   public static boolean segmentAndTriangle(PickSegment paramPickSegment, Point3d[] paramArrayOfPoint3d, int paramInt, double[] paramArrayOfDouble) {
/*  234 */     if (paramArrayOfPoint3d.length - paramInt < 3) {
/*  235 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect5"));
/*      */     }
/*  237 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*      */     
/*  239 */     for (int i = 0; i < 3; i++) {
/*  240 */       arrayOfPoint3d[i] = paramArrayOfPoint3d[paramInt + i];
/*      */     }
/*  242 */     return segmentAndPoly(arrayOfPoint3d, paramPickSegment, paramArrayOfDouble);
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
/*      */   public static boolean segmentAndTriangle(PickSegment paramPickSegment, Point3f[] paramArrayOfPoint3f, int paramInt, double[] paramArrayOfDouble) {
/*  262 */     if (paramArrayOfPoint3f.length - paramInt < 3) {
/*  263 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect6"));
/*      */     }
/*  265 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*      */     
/*  267 */     for (int i = 0; i < 3; i++) {
/*  268 */       arrayOfPoint3d[i] = new Point3d(paramArrayOfPoint3f[paramInt + i]);
/*      */     }
/*  270 */     return segmentAndPoly(arrayOfPoint3d, paramPickSegment, paramArrayOfDouble);
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
/*      */   private static boolean pointAndQuad(PickPoint paramPickPoint, Point3d[] paramArrayOfPoint3d, int paramInt) {
/*  291 */     if (paramArrayOfPoint3d.length - paramInt < 4) {
/*  292 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect7"));
/*      */     }
/*  294 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*      */     
/*  296 */     for (int i = 0; i < 4; i++) {
/*  297 */       arrayOfPoint3d[i] = paramArrayOfPoint3d[paramInt + i];
/*      */     }
/*  299 */     return pointAndPoly(arrayOfPoint3d, paramPickPoint);
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
/*      */   private static boolean pointAndQuad(PickPoint paramPickPoint, Point3f[] paramArrayOfPoint3f, int paramInt) {
/*  316 */     if (paramArrayOfPoint3f.length - paramInt < 4) {
/*  317 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect7"));
/*      */     }
/*  319 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*      */     
/*  321 */     for (int i = 0; i < 4; i++) {
/*  322 */       arrayOfPoint3d[i] = new Point3d(paramArrayOfPoint3f[paramInt + i]);
/*      */     }
/*  324 */     return pointAndPoly(arrayOfPoint3d, paramPickPoint);
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
/*      */   private static boolean pointAndTriangle(PickPoint paramPickPoint, Point3d[] paramArrayOfPoint3d, int paramInt) {
/*  345 */     if (paramArrayOfPoint3d.length - paramInt < 3) {
/*  346 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect9"));
/*      */     }
/*  348 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*      */     
/*  350 */     for (int i = 0; i < 3; i++) {
/*  351 */       arrayOfPoint3d[i] = paramArrayOfPoint3d[paramInt + i];
/*      */     }
/*  353 */     return pointAndPoly(arrayOfPoint3d, paramPickPoint);
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
/*      */   private static boolean pointAndTriangle(PickPoint paramPickPoint, Point3f[] paramArrayOfPoint3f, int paramInt) {
/*  370 */     if (paramArrayOfPoint3f.length - paramInt < 3) {
/*  371 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect10"));
/*      */     }
/*  373 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*      */     
/*  375 */     for (int i = 0; i < 3; i++) {
/*  376 */       arrayOfPoint3d[i] = new Point3d(paramArrayOfPoint3f[paramInt + i]);
/*      */     }
/*  378 */     return pointAndPoly(arrayOfPoint3d, paramPickPoint);
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
/*      */   public static boolean rayAndPoint(PickRay paramPickRay, Point3d paramPoint3d, double[] paramArrayOfDouble) {
/*  397 */     Point3d point3d = new Point3d();
/*  398 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  400 */     paramPickRay.get(point3d, vector3d);
/*      */     
/*  402 */     return rayAndPoint(paramPoint3d, point3d, vector3d, paramArrayOfDouble);
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
/*      */   public static boolean rayAndPoint(PickRay paramPickRay, Point3f paramPoint3f, double[] paramArrayOfDouble) {
/*  418 */     Point3d point3d = new Point3d();
/*  419 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  421 */     paramPickRay.get(point3d, vector3d);
/*      */     
/*  423 */     return rayAndPoint(new Point3d(paramPoint3f), point3d, vector3d, paramArrayOfDouble);
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
/*      */   public static boolean segmentAndPoint(PickSegment paramPickSegment, Point3d paramPoint3d, double[] paramArrayOfDouble) {
/*  441 */     Point3d point3d1 = new Point3d();
/*  442 */     Point3d point3d2 = new Point3d();
/*  443 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  445 */     paramPickSegment.get(point3d1, point3d2);
/*  446 */     vector3d.x = point3d2.x - point3d1.x;
/*  447 */     vector3d.y = point3d2.y - point3d1.y;
/*  448 */     vector3d.z = point3d2.z - point3d1.z;
/*      */     
/*  450 */     if (rayAndPoint(paramPoint3d, point3d1, vector3d, paramArrayOfDouble) == true && paramArrayOfDouble[0] <= 1.0D) {
/*  451 */       return true;
/*      */     }
/*  453 */     return false;
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
/*      */   public static boolean segmentAndPoint(PickSegment paramPickSegment, Point3f paramPoint3f, double[] paramArrayOfDouble) {
/*  470 */     Point3d point3d1 = new Point3d();
/*  471 */     Point3d point3d2 = new Point3d();
/*  472 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  474 */     paramPickSegment.get(point3d1, point3d2);
/*  475 */     vector3d.x = point3d2.x - point3d1.x;
/*  476 */     vector3d.y = point3d2.y - point3d1.y;
/*  477 */     vector3d.z = point3d2.z - point3d1.z;
/*      */     
/*  479 */     if (rayAndPoint(new Point3d(paramPoint3f), point3d1, vector3d, paramArrayOfDouble) == true && paramArrayOfDouble[0] <= 1.0D)
/*      */     {
/*  481 */       return true;
/*      */     }
/*  483 */     return false;
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
/*      */   public static boolean pointAndPoint(PickPoint paramPickPoint, Point3d paramPoint3d) {
/*  497 */     Point3d point3d = new Point3d();
/*      */     
/*  499 */     paramPickPoint.get(point3d);
/*      */     
/*  501 */     if (point3d.x == paramPoint3d.x && point3d.y == paramPoint3d.y && point3d.z == paramPoint3d.z)
/*      */     {
/*  503 */       return true;
/*      */     }
/*  505 */     return false;
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
/*      */   public static boolean pointAndPoint(PickPoint paramPickPoint, Point3f paramPoint3f) {
/*  518 */     Point3d point3d = new Point3d();
/*      */     
/*  520 */     paramPickPoint.get(point3d);
/*      */     
/*  522 */     if ((float)point3d.x == paramPoint3f.x && (float)point3d.y == paramPoint3f.y && (float)point3d.z == paramPoint3f.z)
/*      */     {
/*  524 */       return true;
/*      */     }
/*  526 */     return false;
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
/*      */   public static boolean rayAndLine(PickRay paramPickRay, Point3d[] paramArrayOfPoint3d, int paramInt, double[] paramArrayOfDouble) {
/*  546 */     Point3d point3d1 = new Point3d();
/*  547 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  549 */     if (paramArrayOfPoint3d.length - paramInt < 2) {
/*  550 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect11"));
/*      */     }
/*  552 */     paramPickRay.get(point3d1, vector3d);
/*  553 */     Point3d point3d2 = paramArrayOfPoint3d[paramInt++];
/*  554 */     Point3d point3d3 = paramArrayOfPoint3d[paramInt];
/*      */     
/*  556 */     return lineAndRay(point3d2, point3d3, point3d1, vector3d, paramArrayOfDouble);
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
/*      */   public static boolean rayAndLine(PickRay paramPickRay, Point3f[] paramArrayOfPoint3f, int paramInt, double[] paramArrayOfDouble) {
/*  575 */     Point3d point3d1 = new Point3d();
/*  576 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  578 */     if (paramArrayOfPoint3f.length - paramInt < 2) {
/*  579 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect11"));
/*      */     }
/*  581 */     paramPickRay.get(point3d1, vector3d);
/*  582 */     Point3d point3d2 = new Point3d(paramArrayOfPoint3f[paramInt++]);
/*  583 */     Point3d point3d3 = new Point3d(paramArrayOfPoint3f[paramInt]);
/*      */     
/*  585 */     return lineAndRay(point3d2, point3d3, point3d1, vector3d, paramArrayOfDouble);
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
/*      */   public static boolean segmentAndLine(PickSegment paramPickSegment, Point3d[] paramArrayOfPoint3d, int paramInt, double[] paramArrayOfDouble) {
/*  607 */     Point3d point3d1 = new Point3d();
/*  608 */     Point3d point3d2 = new Point3d();
/*  609 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  611 */     if (paramArrayOfPoint3d.length - paramInt < 2) {
/*  612 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect13"));
/*      */     }
/*  614 */     paramPickSegment.get(point3d1, point3d2);
/*  615 */     vector3d.x = point3d2.x - point3d1.x;
/*  616 */     vector3d.y = point3d2.y - point3d1.y;
/*  617 */     vector3d.z = point3d2.z - point3d1.z;
/*      */     
/*  619 */     Point3d point3d3 = paramArrayOfPoint3d[paramInt++];
/*  620 */     Point3d point3d4 = paramArrayOfPoint3d[paramInt];
/*      */     
/*  622 */     if (lineAndRay(point3d3, point3d4, point3d1, vector3d, paramArrayOfDouble) == true && 
/*  623 */       paramArrayOfDouble[0] <= 1.0D) {
/*  624 */       return true;
/*      */     }
/*  626 */     return false;
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
/*      */   public static boolean segmentAndLine(PickSegment paramPickSegment, Point3f[] paramArrayOfPoint3f, int paramInt, double[] paramArrayOfDouble) {
/*  645 */     Point3d point3d1 = new Point3d();
/*  646 */     Point3d point3d2 = new Point3d();
/*  647 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  649 */     if (paramArrayOfPoint3f.length - paramInt < 2) {
/*  650 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect13"));
/*      */     }
/*  652 */     paramPickSegment.get(point3d1, point3d2);
/*  653 */     vector3d.x = point3d2.x - point3d1.x;
/*  654 */     vector3d.y = point3d2.y - point3d1.y;
/*  655 */     vector3d.z = point3d2.z - point3d1.z;
/*      */     
/*  657 */     Point3d point3d3 = new Point3d(paramArrayOfPoint3f[paramInt++]);
/*  658 */     Point3d point3d4 = new Point3d(paramArrayOfPoint3f[paramInt]);
/*      */     
/*  660 */     if (lineAndRay(point3d3, point3d4, point3d1, vector3d, paramArrayOfDouble) == true && 
/*  661 */       paramArrayOfDouble[0] <= 1.0D) {
/*  662 */       return true;
/*      */     }
/*  664 */     return false;
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
/*      */   public static boolean pointAndLine(PickPoint paramPickPoint, Point3d[] paramArrayOfPoint3d, int paramInt) {
/*  681 */     if (paramArrayOfPoint3d.length - paramInt < 2) {
/*  682 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect13"));
/*      */     }
/*  684 */     double[] arrayOfDouble = new double[1];
/*  685 */     Point3d point3d1 = paramArrayOfPoint3d[paramInt++];
/*  686 */     Point3d point3d2 = paramArrayOfPoint3d[paramInt];
/*  687 */     Point3d point3d3 = new Point3d();
/*  688 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  690 */     paramPickPoint.get(point3d3);
/*  691 */     vector3d.x = point3d2.x - point3d1.x;
/*  692 */     vector3d.y = point3d2.y - point3d1.y;
/*  693 */     vector3d.z = point3d2.z - point3d1.z;
/*      */     
/*  695 */     if (rayAndPoint(point3d3, point3d1, vector3d, arrayOfDouble) == true && arrayOfDouble[0] <= 1.0D)
/*      */     {
/*  697 */       return true;
/*      */     }
/*  699 */     return false;
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
/*      */   public static boolean pointAndLine(PickPoint paramPickPoint, Point3f[] paramArrayOfPoint3f, int paramInt) {
/*  716 */     if (paramArrayOfPoint3f.length - paramInt < 2) {
/*  717 */       throw new RuntimeException(J3dUtilsI18N.getString("Intersect13"));
/*      */     }
/*  719 */     double[] arrayOfDouble = new double[1];
/*  720 */     Point3d point3d1 = new Point3d(paramArrayOfPoint3f[paramInt++]);
/*  721 */     Point3d point3d2 = new Point3d(paramArrayOfPoint3f[paramInt]);
/*  722 */     Point3d point3d3 = new Point3d();
/*  723 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  725 */     paramPickPoint.get(point3d3);
/*  726 */     vector3d.x = point3d2.x - point3d1.x;
/*  727 */     vector3d.y = point3d2.y - point3d1.y;
/*  728 */     vector3d.z = point3d2.z - point3d1.z;
/*      */     
/*  730 */     if (rayAndPoint(point3d3, point3d1, vector3d, arrayOfDouble) == true && arrayOfDouble[0] <= 1.0D) {
/*  731 */       return true;
/*      */     }
/*  733 */     return false;
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
/*      */   private static boolean pointAndPoly(Point3d[] paramArrayOfPoint3d, PickPoint paramPickPoint) {
/*  745 */     Vector3d vector3d1 = new Vector3d();
/*  746 */     Vector3d vector3d2 = new Vector3d();
/*  747 */     Vector3d vector3d3 = new Vector3d();
/*  748 */     double d1 = 0.0D;
/*  749 */     Vector3d vector3d4 = new Vector3d();
/*  750 */     double d2 = 0.0D;
/*      */ 
/*      */ 
/*      */     
/*      */     byte b1;
/*      */ 
/*      */     
/*  757 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length - 1; ) {
/*  758 */       vector3d1.x = (paramArrayOfPoint3d[b1 + true]).x - (paramArrayOfPoint3d[b1]).x;
/*  759 */       vector3d1.y = (paramArrayOfPoint3d[b1 + true]).y - (paramArrayOfPoint3d[b1]).y;
/*  760 */       vector3d1.z = (paramArrayOfPoint3d[b1 + true]).z - (paramArrayOfPoint3d[b1++]).z;
/*  761 */       if (vector3d1.length() > 0.0D)
/*      */         break; 
/*      */     } 
/*      */     byte b2;
/*  765 */     for (b2 = b1; b2 < paramArrayOfPoint3d.length - 1; b2++) {
/*  766 */       vector3d2.x = (paramArrayOfPoint3d[b2 + 1]).x - (paramArrayOfPoint3d[b2]).x;
/*  767 */       vector3d2.y = (paramArrayOfPoint3d[b2 + 1]).y - (paramArrayOfPoint3d[b2]).y;
/*  768 */       vector3d2.z = (paramArrayOfPoint3d[b2 + 1]).z - (paramArrayOfPoint3d[b2]).z;
/*  769 */       if (vector3d2.length() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/*  773 */     if (b2 == paramArrayOfPoint3d.length - 1)
/*      */     {
/*  775 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  785 */     vector3d3.cross(vector3d1, vector3d2);
/*      */     
/*  787 */     if (vector3d3.length() == 0.0D)
/*      */     {
/*  789 */       return false;
/*      */     }
/*      */     
/*  792 */     vector3d4.set(paramArrayOfPoint3d[0]);
/*  793 */     d1 = vector3d3.dot(vector3d4);
/*      */     
/*  795 */     Point3d point3d = new Point3d();
/*  796 */     paramPickPoint.get(point3d);
/*  797 */     vector3d4.set(point3d);
/*      */     
/*  799 */     if (d1 - vector3d3.dot(vector3d4) == 0.0D) {
/*  800 */       return true;
/*      */     }
/*  802 */     return false;
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
/*      */   private static boolean lineAndRay(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Vector3d paramVector3d, double[] paramArrayOfDouble) {
/*  815 */     Vector3d vector3d = new Vector3d(paramPoint3d2.x - paramPoint3d1.x, paramPoint3d2.y - paramPoint3d1.y, paramPoint3d2.z - paramPoint3d1.z);
/*      */ 
/*      */     
/*  818 */     double d1 = vector3d.x;
/*  819 */     double d2 = -paramVector3d.x;
/*  820 */     double d3 = vector3d.y;
/*  821 */     double d4 = -paramVector3d.y;
/*      */ 
/*      */     
/*  824 */     double d9 = d1 * d4 - d3 * d2;
/*      */     
/*  826 */     if (d9 == 0.0D) {
/*  827 */       return false;
/*      */     }
/*      */     
/*  830 */     double d12 = 1.0D / d9;
/*      */     
/*  832 */     double d5 = d12 * d4;
/*  833 */     double d6 = d12 * -d2;
/*  834 */     double d7 = d12 * -d3;
/*  835 */     double d8 = d12 * d1;
/*      */     
/*  837 */     d12 = paramPoint3d3.x - paramPoint3d1.x;
/*  838 */     double d13 = paramPoint3d3.y - paramPoint3d1.y;
/*      */     
/*  840 */     double d10 = d5 * d12 + d6 * d13;
/*  841 */     double d11 = d7 * d12 + d8 * d13;
/*      */     
/*  843 */     if (d11 < 0.0D)
/*  844 */       return false; 
/*  845 */     if (d10 < 0.0D || d10 > 1.0D) {
/*  846 */       return false;
/*      */     }
/*  848 */     d12 = paramPoint3d3.z + d11 * paramVector3d.z;
/*  849 */     d13 = paramPoint3d1.z + d10 * vector3d.z;
/*      */     
/*  851 */     if (d12 < d13 - Double.MIN_VALUE || d12 > d13 + Double.MIN_VALUE) {
/*  852 */       return false;
/*      */     }
/*  854 */     paramArrayOfDouble[0] = d11;
/*  855 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean rayAndPoint(Point3d paramPoint3d1, Point3d paramPoint3d2, Vector3d paramVector3d, double[] paramArrayOfDouble) {
/*  860 */     byte b = 0;
/*      */ 
/*      */     
/*  863 */     if (paramVector3d.x != 0.0D) {
/*  864 */       b = 0;
/*  865 */       paramArrayOfDouble[0] = (paramPoint3d1.x - paramPoint3d2.x) / paramVector3d.x;
/*      */     }
/*  867 */     else if (paramVector3d.y != 0.0D) {
/*  868 */       if (paramPoint3d1.x != paramPoint3d2.x)
/*  869 */         return false; 
/*  870 */       b = 1;
/*  871 */       paramArrayOfDouble[0] = (paramPoint3d1.y - paramPoint3d2.y) / paramVector3d.y;
/*      */     }
/*  873 */     else if (paramVector3d.z != 0.0D) {
/*  874 */       if (paramPoint3d1.x != paramPoint3d2.x || paramPoint3d1.y != paramPoint3d2.y)
/*  875 */         return false; 
/*  876 */       b = 2;
/*  877 */       paramArrayOfDouble[0] = (paramPoint3d1.z - paramPoint3d2.z) / paramVector3d.z;
/*      */     }
/*      */     else {
/*      */       
/*  881 */       return false;
/*      */     } 
/*  883 */     if (paramArrayOfDouble[0] < 0.0D) {
/*  884 */       return false;
/*      */     }
/*  886 */     if (b == 0) {
/*  887 */       double d = paramPoint3d2.y + paramArrayOfDouble[0] * paramVector3d.y;
/*  888 */       if (paramPoint3d1.y < d - Double.MIN_VALUE || paramPoint3d1.y > d + Double.MIN_VALUE) {
/*  889 */         return false;
/*      */       }
/*      */     } 
/*  892 */     if (b < 2) {
/*  893 */       double d = paramPoint3d2.z + paramArrayOfDouble[0] * paramVector3d.z;
/*  894 */       if (paramPoint3d1.z < d - Double.MIN_VALUE || paramPoint3d1.z > d + Double.MIN_VALUE) {
/*  895 */         return false;
/*      */       }
/*      */     } 
/*  898 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean rayAndPoly(Point3d[] paramArrayOfPoint3d, PickRay paramPickRay, double[] paramArrayOfDouble) {
/*      */     byte b3, b1;
/*  905 */     Vector3d vector3d1 = new Vector3d();
/*  906 */     Vector3d vector3d2 = new Vector3d();
/*  907 */     Vector3d vector3d3 = new Vector3d();
/*  908 */     double d4 = 0.0D;
/*  909 */     Vector3d vector3d4 = new Vector3d();
/*  910 */     double d5 = 0.0D;
/*      */     
/*  912 */     Point3d point3d1 = new Point3d();
/*  913 */     Vector3d vector3d5 = new Vector3d();
/*      */     
/*  915 */     Point3d point3d2 = new Point3d();
/*      */     
/*  917 */     double[] arrayOfDouble1 = new double[4];
/*  918 */     double[] arrayOfDouble2 = new double[4];
/*      */ 
/*      */     
/*      */     byte b4;
/*      */ 
/*      */     
/*  924 */     for (b4 = 0; b4 < paramArrayOfPoint3d.length - 1; ) {
/*  925 */       vector3d1.x = (paramArrayOfPoint3d[b4 + true]).x - (paramArrayOfPoint3d[b4]).x;
/*  926 */       vector3d1.y = (paramArrayOfPoint3d[b4 + true]).y - (paramArrayOfPoint3d[b4]).y;
/*  927 */       vector3d1.z = (paramArrayOfPoint3d[b4 + true]).z - (paramArrayOfPoint3d[b4++]).z;
/*  928 */       if (vector3d1.length() > 0.0D)
/*      */         break; 
/*      */     } 
/*      */     byte b5;
/*  932 */     for (b5 = b4; b5 < paramArrayOfPoint3d.length - 1; b5++) {
/*  933 */       vector3d2.x = (paramArrayOfPoint3d[b5 + 1]).x - (paramArrayOfPoint3d[b5]).x;
/*  934 */       vector3d2.y = (paramArrayOfPoint3d[b5 + 1]).y - (paramArrayOfPoint3d[b5]).y;
/*  935 */       vector3d2.z = (paramArrayOfPoint3d[b5 + 1]).z - (paramArrayOfPoint3d[b5]).z;
/*  936 */       if (vector3d2.length() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/*  940 */     if (b5 == paramArrayOfPoint3d.length - 1)
/*      */     {
/*  942 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  951 */     vector3d3.cross(vector3d1, vector3d2);
/*      */     
/*  953 */     if (vector3d3.length() == 0.0D)
/*      */     {
/*  955 */       return false;
/*      */     }
/*      */     
/*  958 */     paramPickRay.get(point3d1, vector3d5);
/*      */ 
/*      */ 
/*      */     
/*  962 */     vector3d4.set(paramArrayOfPoint3d[0]);
/*  963 */     d4 = vector3d3.dot(vector3d4);
/*      */     
/*  965 */     d5 = vector3d3.dot(vector3d5);
/*      */ 
/*      */     
/*  968 */     if (d5 == 0.0D)
/*      */     {
/*  970 */       return false;
/*      */     }
/*      */     
/*  973 */     vector3d4.set(point3d1);
/*      */     
/*  975 */     paramArrayOfDouble[0] = (d4 - vector3d3.dot(vector3d4)) / d5;
/*      */ 
/*      */     
/*  978 */     if (paramArrayOfDouble[0] < 0.0D)
/*      */     {
/*  980 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  985 */     point3d1.x += vector3d5.x * paramArrayOfDouble[0];
/*  986 */     point3d1.y += vector3d5.y * paramArrayOfDouble[0];
/*  987 */     point3d1.z += vector3d5.z * paramArrayOfDouble[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  996 */     double d1 = Math.abs(vector3d3.x);
/*  997 */     double d2 = Math.abs(vector3d3.y);
/*  998 */     double d3 = Math.abs(vector3d3.z);
/*      */     
/* 1000 */     if (d1 > d2) {
/* 1001 */       b1 = 0;
/*      */     } else {
/* 1003 */       b1 = 1;
/*      */     } 
/* 1005 */     if (!b1) {
/* 1006 */       if (d1 < d3) {
/* 1007 */         b1 = 2;
/*      */       }
/* 1009 */     } else if (b1 == 1 && 
/* 1010 */       d2 < d3) {
/* 1011 */       b1 = 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1016 */     for (b4 = 0; b4 < paramArrayOfPoint3d.length; b4++) {
/* 1017 */       switch (b1) {
/*      */         case 0:
/* 1019 */           arrayOfDouble1[b4] = (paramArrayOfPoint3d[b4]).y - point3d2.y;
/* 1020 */           arrayOfDouble2[b4] = (paramArrayOfPoint3d[b4]).z - point3d2.z;
/*      */           break;
/*      */         
/*      */         case 1:
/* 1024 */           arrayOfDouble1[b4] = (paramArrayOfPoint3d[b4]).x - point3d2.x;
/* 1025 */           arrayOfDouble2[b4] = (paramArrayOfPoint3d[b4]).z - point3d2.z;
/*      */           break;
/*      */         
/*      */         case 2:
/* 1029 */           arrayOfDouble1[b4] = (paramArrayOfPoint3d[b4]).x - point3d2.x;
/* 1030 */           arrayOfDouble2[b4] = (paramArrayOfPoint3d[b4]).y - point3d2.y;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1038 */     byte b2 = 0;
/*      */     
/* 1040 */     if (arrayOfDouble2[0] < 0.0D) {
/* 1041 */       b3 = -1;
/*      */     } else {
/* 1043 */       b3 = 1;
/*      */     } 
/* 1045 */     for (b4 = 0; b4 < paramArrayOfPoint3d.length; b4++) {
/* 1046 */       byte b; b5 = b4 + 1;
/* 1047 */       if (b5 == paramArrayOfPoint3d.length) {
/* 1048 */         b5 = 0;
/*      */       }
/* 1050 */       if (arrayOfDouble2[b5] < 0.0D) {
/* 1051 */         b = -1;
/*      */       } else {
/* 1053 */         b = 1;
/*      */       } 
/* 1055 */       if (b3 != b) {
/* 1056 */         if (arrayOfDouble1[b4] > 0.0D && arrayOfDouble1[b5] > 0.0D) {
/*      */           
/* 1058 */           b2++;
/*      */         }
/* 1060 */         else if (arrayOfDouble1[b4] > 0.0D || arrayOfDouble1[b5] > 0.0D) {
/*      */           
/* 1062 */           double d = arrayOfDouble1[b4] - arrayOfDouble2[b4] * (arrayOfDouble1[b5] - arrayOfDouble1[b4]) / (arrayOfDouble2[b5] - arrayOfDouble2[b4]);
/* 1063 */           if (d > 0.0D)
/*      */           {
/* 1065 */             b2++; } 
/*      */         } 
/* 1067 */         b3 = b;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1073 */     if (b2 % 2 == 1) {
/*      */ 
/*      */       
/* 1076 */       paramArrayOfDouble[0] = paramArrayOfDouble[0] * vector3d5.length();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1086 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1090 */     return false;
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
/*      */   private static boolean segmentAndPoly(Point3d[] paramArrayOfPoint3d, PickSegment paramPickSegment, double[] paramArrayOfDouble) {
/*      */     byte b3, b1;
/* 1103 */     Vector3d vector3d1 = new Vector3d();
/* 1104 */     Vector3d vector3d2 = new Vector3d();
/* 1105 */     Vector3d vector3d3 = new Vector3d();
/* 1106 */     double d4 = 0.0D;
/* 1107 */     Vector3d vector3d4 = new Vector3d();
/* 1108 */     Vector3d vector3d5 = new Vector3d();
/* 1109 */     double d5 = 0.0D;
/*      */     
/* 1111 */     Point3d point3d1 = new Point3d();
/* 1112 */     Point3d point3d2 = new Point3d();
/*      */     
/* 1114 */     Point3d point3d3 = new Point3d();
/*      */     
/* 1116 */     double[] arrayOfDouble1 = new double[4];
/* 1117 */     double[] arrayOfDouble2 = new double[4];
/*      */ 
/*      */     
/*      */     byte b4;
/*      */ 
/*      */     
/* 1123 */     for (b4 = 0; b4 < paramArrayOfPoint3d.length - 1; ) {
/* 1124 */       vector3d1.x = (paramArrayOfPoint3d[b4 + true]).x - (paramArrayOfPoint3d[b4]).x;
/* 1125 */       vector3d1.y = (paramArrayOfPoint3d[b4 + true]).y - (paramArrayOfPoint3d[b4]).y;
/* 1126 */       vector3d1.z = (paramArrayOfPoint3d[b4 + true]).z - (paramArrayOfPoint3d[b4++]).z;
/* 1127 */       if (vector3d1.length() > 0.0D)
/*      */         break; 
/*      */     } 
/*      */     byte b5;
/* 1131 */     for (b5 = b4; b5 < paramArrayOfPoint3d.length - 1; b5++) {
/* 1132 */       vector3d2.x = (paramArrayOfPoint3d[b5 + 1]).x - (paramArrayOfPoint3d[b5]).x;
/* 1133 */       vector3d2.y = (paramArrayOfPoint3d[b5 + 1]).y - (paramArrayOfPoint3d[b5]).y;
/* 1134 */       vector3d2.z = (paramArrayOfPoint3d[b5 + 1]).z - (paramArrayOfPoint3d[b5]).z;
/* 1135 */       if (vector3d2.length() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/* 1139 */     if (b5 == paramArrayOfPoint3d.length - 1)
/*      */     {
/* 1141 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1150 */     vector3d3.cross(vector3d1, vector3d2);
/*      */     
/* 1152 */     if (vector3d3.length() == 0.0D)
/*      */     {
/* 1154 */       return false;
/*      */     }
/*      */     
/* 1157 */     vector3d4.set(paramArrayOfPoint3d[0]);
/* 1158 */     d4 = vector3d3.dot(vector3d4);
/*      */     
/* 1160 */     paramPickSegment.get(point3d1, point3d2);
/*      */ 
/*      */     
/* 1163 */     vector3d5.x = point3d2.x - point3d1.x;
/* 1164 */     vector3d5.y = point3d2.y - point3d1.y;
/* 1165 */     vector3d5.z = point3d2.z - point3d1.z;
/*      */     
/* 1167 */     d5 = vector3d3.dot(vector3d5);
/*      */ 
/*      */     
/* 1170 */     if (d5 == 0.0D)
/*      */     {
/* 1172 */       return false;
/*      */     }
/*      */     
/* 1175 */     vector3d4.set(point3d1);
/*      */     
/* 1177 */     paramArrayOfDouble[0] = (d4 - vector3d3.dot(vector3d4)) / d5;
/*      */ 
/*      */ 
/*      */     
/* 1181 */     if (paramArrayOfDouble[0] < 0.0D || paramArrayOfDouble[0] > 1.0D)
/*      */     {
/* 1183 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1188 */     point3d1.x += vector3d5.x * paramArrayOfDouble[0];
/* 1189 */     point3d1.y += vector3d5.y * paramArrayOfDouble[0];
/* 1190 */     point3d1.z += vector3d5.z * paramArrayOfDouble[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1199 */     double d1 = Math.abs(vector3d3.x);
/* 1200 */     double d2 = Math.abs(vector3d3.y);
/* 1201 */     double d3 = Math.abs(vector3d3.z);
/*      */     
/* 1203 */     if (d1 > d2) {
/* 1204 */       b1 = 0;
/*      */     } else {
/* 1206 */       b1 = 1;
/*      */     } 
/* 1208 */     if (!b1) {
/* 1209 */       if (d1 < d3) {
/* 1210 */         b1 = 2;
/*      */       }
/* 1212 */     } else if (b1 == 1 && 
/* 1213 */       d2 < d3) {
/* 1214 */       b1 = 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1219 */     for (b4 = 0; b4 < paramArrayOfPoint3d.length; b4++) {
/* 1220 */       switch (b1) {
/*      */         case 0:
/* 1222 */           arrayOfDouble1[b4] = (paramArrayOfPoint3d[b4]).y - point3d3.y;
/* 1223 */           arrayOfDouble2[b4] = (paramArrayOfPoint3d[b4]).z - point3d3.z;
/*      */           break;
/*      */         
/*      */         case 1:
/* 1227 */           arrayOfDouble1[b4] = (paramArrayOfPoint3d[b4]).x - point3d3.x;
/* 1228 */           arrayOfDouble2[b4] = (paramArrayOfPoint3d[b4]).z - point3d3.z;
/*      */           break;
/*      */         
/*      */         case 2:
/* 1232 */           arrayOfDouble1[b4] = (paramArrayOfPoint3d[b4]).x - point3d3.x;
/* 1233 */           arrayOfDouble2[b4] = (paramArrayOfPoint3d[b4]).y - point3d3.y;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1241 */     byte b2 = 0;
/*      */     
/* 1243 */     if (arrayOfDouble2[0] < 0.0D) {
/* 1244 */       b3 = -1;
/*      */     } else {
/* 1246 */       b3 = 1;
/*      */     } 
/* 1248 */     for (b4 = 0; b4 < paramArrayOfPoint3d.length; b4++) {
/* 1249 */       byte b; b5 = b4 + 1;
/* 1250 */       if (b5 == paramArrayOfPoint3d.length) {
/* 1251 */         b5 = 0;
/*      */       }
/* 1253 */       if (arrayOfDouble2[b5] < 0.0D) {
/* 1254 */         b = -1;
/*      */       } else {
/* 1256 */         b = 1;
/*      */       } 
/* 1258 */       if (b3 != b) {
/* 1259 */         if (arrayOfDouble1[b4] > 0.0D && arrayOfDouble1[b5] > 0.0D) {
/*      */           
/* 1261 */           b2++;
/*      */         }
/* 1263 */         else if (arrayOfDouble1[b4] > 0.0D || arrayOfDouble1[b5] > 0.0D) {
/*      */           
/* 1265 */           double d = arrayOfDouble1[b4] - arrayOfDouble2[b4] * (arrayOfDouble1[b5] - arrayOfDouble1[b4]) / (arrayOfDouble2[b5] - arrayOfDouble2[b4]);
/* 1266 */           if (d > 0.0D)
/*      */           {
/* 1268 */             b2++; } 
/*      */         } 
/* 1270 */         b3 = b;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1276 */     if (b2 % 2 == 1) {
/*      */ 
/*      */       
/* 1279 */       paramArrayOfDouble[0] = paramArrayOfDouble[0] * vector3d5.length();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1289 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1293 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\behaviors\picking\Intersect.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */