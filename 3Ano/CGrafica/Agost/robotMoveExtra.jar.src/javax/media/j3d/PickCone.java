/*    */ package javax.media.j3d;
/*    */ 
/*    */ import javax.vecmath.Point3d;
/*    */ import javax.vecmath.Point4d;
/*    */ import javax.vecmath.Vector3d;
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
/*    */ public abstract class PickCone
/*    */   extends PickShape
/*    */ {
/* 35 */   Point3d origin = new Point3d();
/* 36 */   Vector3d direction = new Vector3d();
/* 37 */   double spreadAngle = 0.04908738521234052D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void getOrigin(Point3d paramPoint3d) { paramPoint3d.set(this.origin); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void getDirection(Vector3d paramVector3d) { paramVector3d.set(this.direction); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public double getSpreadAngle() { return this.spreadAngle; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   double getRadius(double paramDouble) { return paramDouble * Math.tan(this.spreadAngle); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   abstract boolean intersect(Bounds paramBounds, Point4d paramPoint4d);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 83 */   Point3d getStartPoint() { return this.origin; }
/*    */ 
/*    */ 
/*    */   
/* 87 */   int getPickType() { return 5; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PickCone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */