/*    */ package javax.media.j3d;
/*    */ 
/*    */ import javax.vecmath.Point3d;
/*    */ import javax.vecmath.Point4d;
/*    */ import javax.vecmath.Vector3d;
/*    */ import javax.vecmath.Vector4d;
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
/*    */ public abstract class PickCylinder
/*    */   extends PickShape
/*    */ {
/* 35 */   Point3d origin = new Point3d();
/* 36 */   Vector3d direction = new Vector3d();
/* 37 */   double radius = 0.0D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void getOrigin(Point3d paramPoint3d) { paramPoint3d.set(this.origin); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public double getRadius() { return this.radius; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void getDirection(Vector3d paramVector3d) { paramVector3d.set(this.direction); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   abstract boolean intersect(Bounds paramBounds, Point4d paramPoint4d);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static boolean pointInPolytope(BoundingPolytope paramBoundingPolytope, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 79 */     int i = paramBoundingPolytope.planes.length - 1;
/*    */     
/* 81 */     while (i >= 0) {
/* 82 */       Vector4d vector4d = paramBoundingPolytope.planes[i--];
/* 83 */       if (paramDouble1 * vector4d.x + paramDouble2 * vector4d.y + paramDouble3 * vector4d.z + vector4d.w > 1.0E-6D) {
/* 84 */         return false;
/*    */       }
/*    */     } 
/* 87 */     return true;
/*    */   }
/*    */ 
/*    */   
/* 91 */   Point3d getStartPoint() { return this.origin; }
/*    */ 
/*    */ 
/*    */   
/* 95 */   int getPickType() { return 4; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\PickCylinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */