/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Matrix3d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point4d;
/*     */ import javax.vecmath.Vector3d;
/*     */ import javax.vecmath.Vector4d;
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
/*     */ public abstract class Bounds
/*     */   implements Cloneable
/*     */ {
/*     */   static final double EPSILON = 1.0E-6D;
/*     */   static final boolean debug = false;
/*     */   static final int BOUNDING_BOX = 1;
/*     */   static final int BOUNDING_SPHERE = 2;
/*     */   static final int BOUNDING_POLYTOPE = 4;
/*     */   boolean boundsIsEmpty = false;
/*     */   boolean boundsIsInfinite = false;
/*  34 */   int boundId = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Object clone();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int hashCode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean intersect(Point3d paramPoint3d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d, Point4d paramPoint4d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean intersect(Point3d paramPoint3d, Point4d paramPoint4d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean intersect(Point3d paramPoint3d1, Point3d paramPoint3d2, Point4d paramPoint4d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean intersect(Bounds paramBounds, Point4d paramPoint4d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean intersect(Bounds paramBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean intersect(Bounds[] paramArrayOfBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Bounds closestIntersection(Bounds[] paramArrayOfBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract Point3d getCenter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void combine(Bounds paramBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void combine(Bounds[] paramArrayOfBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void combine(Point3d paramPoint3d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void combine(Point3d[] paramArrayOfPoint3d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void transform(Transform3D paramTransform3D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void transform(Bounds paramBounds, Transform3D paramTransform3D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean isEmpty();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void set(Bounds paramBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract Bounds copy(Bounds paramBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void test_point(Vector4d[] paramArrayOfVector4d, Point3d paramPoint3d) {
/* 220 */     for (byte b = 0; b < paramArrayOfVector4d.length; b++) {
/* 221 */       double d = paramPoint3d.x * (paramArrayOfVector4d[b]).x + paramPoint3d.y * (paramArrayOfVector4d[b]).y + paramPoint3d.z * (paramArrayOfVector4d[b]).z + (paramArrayOfVector4d[b]).w;
/*     */       
/* 223 */       if (d > 1.0E-6D) {
/* 224 */         System.err.println("new point is outside of plane[" + b + "] dist = " + d);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean closest_point(Point3d paramPoint3d1, Vector4d[] paramArrayOfVector4d, Point3d paramPoint3d2) {
/* 246 */     Point3d point3d1 = new Point3d();
/* 247 */     Point3d point3d2 = new Point3d();
/* 248 */     Object object = null;
/*     */ 
/*     */     
/* 251 */     Matrix3d matrix3d = new Matrix3d();
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
/* 313 */     double d = 1.0E8D;
/*     */ 
/*     */     
/* 316 */     byte b = 0;
/* 317 */     point3d2.set(paramPoint3d1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     boolean bool1 = false;
/* 325 */     boolean bool2 = true;
/* 326 */     boolean bool = false;
/*     */ 
/*     */ 
/*     */     
/* 330 */     while (!bool1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 338 */       boolean bool3 = true;
/* 339 */       double d10 = 0.0D, d11 = 0.0D, d12 = 0.0D;
/* 340 */       double d4 = 0.0D, d5 = 0.0D, d6 = 0.0D, d7 = 0.0D, d8 = 0.0D, d9 = 0.0D;
/* 341 */       for (byte b1 = 0; b1 < paramArrayOfVector4d.length; b1++) {
/* 342 */         Vector4d vector4d = paramArrayOfVector4d[b1];
/* 343 */         double d31 = point3d2.x * vector4d.x + point3d2.y * vector4d.y + point3d2.z * vector4d.z + vector4d.w;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 350 */         if (d31 > -1.0E-6D) {
/* 351 */           d10 += vector4d.x * vector4d.x;
/* 352 */           d11 += vector4d.y * vector4d.y;
/* 353 */           d12 += vector4d.z * vector4d.z;
/* 354 */           d4 += vector4d.x * vector4d.y;
/* 355 */           d5 += vector4d.x * vector4d.z;
/* 356 */           d6 += vector4d.y * vector4d.z;
/* 357 */           d7 += vector4d.x * vector4d.w;
/* 358 */           d8 += vector4d.y * vector4d.w;
/* 359 */           d9 += vector4d.z * vector4d.w;
/*     */         } 
/*     */         
/* 362 */         if (d31 > 1.0E-6D) {
/* 363 */           bool3 = false;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 372 */       if (bool3) {
/*     */ 
/*     */ 
/*     */         
/* 376 */         if (bool2) {
/* 377 */           bool = true;
/*     */         }
/* 379 */         paramPoint3d2.set(point3d2);
/* 380 */         bool1 = true; continue;
/*     */       } 
/* 382 */       bool2 = false;
/*     */ 
/*     */ 
/*     */       
/* 386 */       double d22 = 1.0D + d10 * d;
/* 387 */       double d23 = d4 * d;
/* 388 */       double d24 = d5 * d;
/* 389 */       double d25 = 1.0D + d11 * d;
/* 390 */       double d26 = d6 * d;
/* 391 */       double d27 = 1.0D + d12 * d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 401 */       double d13 = paramPoint3d1.x - d * d7;
/* 402 */       double d14 = paramPoint3d1.y - d * d8;
/* 403 */       double d15 = paramPoint3d1.z - d * d9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 410 */       double d16 = 1.0D / d22;
/* 411 */       double d28 = d16 * d23;
/* 412 */       double d29 = d16 * d24;
/* 413 */       double d2 = d25 - d28 * d23;
/* 414 */       double d17 = 1.0D / d2;
/* 415 */       double d1 = d26 - d23 * d29;
/* 416 */       double d30 = d17 * d1;
/* 417 */       double d18 = 1.0D / (d27 - d24 * d29 - d1 * d30);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 425 */       double d19 = d16 * d13;
/* 426 */       double d20 = d17 * (d14 - d23 * d19);
/* 427 */       double d21 = d18 * (d15 - d24 * d19 - d1 * d20);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 434 */       point3d1.z = d21;
/* 435 */       point3d1.y = d20 - d30 * point3d1.z;
/* 436 */       point3d1.x = d19 - d29 * point3d1.z - d28 * point3d1.y;
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
/* 467 */       double d3 = (point3d2.x - point3d1.x) * (point3d2.x - point3d1.x) + (point3d2.y - point3d1.y) * (point3d2.y - point3d1.y) + (point3d2.z - point3d1.z) * (point3d2.z - point3d1.z);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 474 */       if (d3 < 1.0E-6D) {
/* 475 */         bool1 = true;
/* 476 */         paramPoint3d2.set(point3d1); continue;
/*     */       } 
/* 478 */       point3d2.set(point3d1);
/* 479 */       b++;
/* 480 */       if (b > 4) {
/* 481 */         paramPoint3d2.set(point3d1);
/* 482 */         bool1 = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 493 */     return bool;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect_ptope_sphere(BoundingPolytope paramBoundingPolytope, BoundingSphere paramBoundingSphere) {
/* 498 */     Point3d point3d = new Point3d();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 505 */     boolean bool = closest_point(paramBoundingSphere.center, paramBoundingPolytope.planes, point3d);
/*     */ 
/*     */ 
/*     */     
/* 509 */     if (!bool) {
/*     */ 
/*     */       
/* 512 */       if (point3d.distanceSquared(paramBoundingSphere.center) > paramBoundingSphere.radius * paramBoundingSphere.radius)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 517 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 522 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 528 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect_ptope_abox(BoundingPolytope paramBoundingPolytope, BoundingBox paramBoundingBox) {
/* 533 */     Vector4d[] arrayOfVector4d = new Vector4d[6];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 538 */     arrayOfVector4d[0] = new Vector4d(-1.0D, 0.0D, 0.0D, paramBoundingBox.lower.x);
/* 539 */     arrayOfVector4d[1] = new Vector4d(1.0D, 0.0D, 0.0D, -paramBoundingBox.upper.x);
/* 540 */     arrayOfVector4d[2] = new Vector4d(0.0D, -1.0D, 0.0D, paramBoundingBox.lower.y);
/* 541 */     arrayOfVector4d[3] = new Vector4d(0.0D, 1.0D, 0.0D, -paramBoundingBox.upper.y);
/* 542 */     arrayOfVector4d[4] = new Vector4d(0.0D, 0.0D, -1.0D, paramBoundingBox.lower.z);
/* 543 */     arrayOfVector4d[5] = new Vector4d(0.0D, 0.0D, 1.0D, -paramBoundingBox.upper.z);
/*     */ 
/*     */     
/* 546 */     BoundingPolytope boundingPolytope = new BoundingPolytope(arrayOfVector4d);
/*     */     
/* 548 */     return intersect_ptope_ptope(paramBoundingPolytope, boundingPolytope);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect_ptope_ptope(BoundingPolytope paramBoundingPolytope1, BoundingPolytope paramBoundingPolytope2) {
/* 559 */     Point3d point3d1 = new Point3d();
/* 560 */     Point3d point3d2 = new Point3d();
/* 561 */     Point3d point3d3 = new Point3d();
/* 562 */     Point3d point3d4 = new Point3d();
/*     */     
/* 564 */     boolean bool = false;
/*     */     
/* 566 */     point3d1.x = 0.0D;
/* 567 */     point3d1.y = 0.0D;
/* 568 */     point3d1.z = 0.0D;
/*     */ 
/*     */     
/* 571 */     closest_point(point3d1, paramBoundingPolytope1.planes, point3d2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 577 */     bool = closest_point(point3d2, paramBoundingPolytope2.planes, point3d1);
/*     */     
/* 579 */     if (bool) {
/* 580 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 586 */     bool = closest_point(point3d1, paramBoundingPolytope1.planes, point3d3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 593 */     double d = point3d1.distanceSquared(point3d2);
/*     */ 
/*     */     
/* 596 */     while (!bool) {
/*     */       
/* 598 */       double d1 = point3d1.distanceSquared(point3d3);
/*     */       
/* 600 */       if (d1 < d) {
/* 601 */         point3d2.set(point3d3);
/* 602 */         bool = closest_point(point3d2, paramBoundingPolytope2.planes, point3d4);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 607 */         point3d2.set(point3d3);
/*     */         break;
/*     */       } 
/* 610 */       d = d1;
/* 611 */       d1 = point3d4.distanceSquared(point3d2);
/*     */       
/* 613 */       if (d1 < d) {
/* 614 */         point3d1.set(point3d4);
/* 615 */         if (!bool) {
/* 616 */           bool = closest_point(point3d1, paramBoundingPolytope1.planes, point3d3);
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 623 */         point3d1.set(point3d4);
/*     */         break;
/*     */       } 
/* 626 */       d = d1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 633 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 638 */   void setWithLock(Bounds paramBounds) { set(paramBounds); }
/*     */ 
/*     */ 
/*     */   
/* 642 */   void getWithLock(Bounds paramBounds) { paramBounds.set(this); }
/*     */   
/*     */   abstract int getPickType();
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Bounds.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */