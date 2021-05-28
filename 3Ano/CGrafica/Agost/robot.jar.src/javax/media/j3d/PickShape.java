/*    */ package javax.media.j3d;
/*    */ 
/*    */ import javax.vecmath.Point3d;
/*    */ import javax.vecmath.Point4d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PickShape
/*    */ {
/*    */   static final int PICKRAY = 1;
/*    */   static final int PICKSEGMENT = 2;
/*    */   static final int PICKPOINT = 3;
/*    */   static final int PICKCYLINDER = 4;
/*    */   static final int PICKCONE = 5;
/*    */   static final int PICKBOUNDINGBOX = 6;
/*    */   static final int PICKBOUNDINGSPHERE = 7;
/*    */   static final int PICKBOUNDINGPOLYTOPE = 8;
/*    */   static final int PICKUNKNOWN = 9;
/*    */   
/*    */   abstract boolean intersect(Bounds paramBounds, Point4d paramPoint4d);
/*    */   
/*    */   abstract PickShape transform(Transform3D paramTransform3D);
/*    */   
/*    */   abstract Point3d getStartPoint();
/*    */   
/*    */   double distance(Point3d paramPoint3d) {
/* 60 */     Point3d point3d = getStartPoint();
/* 61 */     double d1 = paramPoint3d.x - point3d.x;
/* 62 */     double d2 = paramPoint3d.y - point3d.y;
/* 63 */     double d3 = paramPoint3d.z - point3d.z;
/* 64 */     return Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/*    */   }
/*    */   
/*    */   abstract int getPickType();
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\PickShape.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */