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
/*    */ public final class PickPoint
/*    */   extends PickShape
/*    */ {
/*    */   Point3d location;
/*    */   
/* 37 */   public PickPoint() { this.location = new Point3d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public PickPoint(Point3d paramPoint3d) { this.location = new Point3d(paramPoint3d); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(Point3d paramPoint3d) {
/* 53 */     this.location.x = paramPoint3d.x;
/* 54 */     this.location.y = paramPoint3d.y;
/* 55 */     this.location.z = paramPoint3d.z;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void get(Point3d paramPoint3d) {
/* 63 */     paramPoint3d.x = this.location.x;
/* 64 */     paramPoint3d.y = this.location.y;
/* 65 */     paramPoint3d.z = this.location.z;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   final boolean intersect(Bounds paramBounds, Point4d paramPoint4d) { return paramBounds.intersect(this.location, paramPoint4d); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   PickShape transform(Transform3D paramTransform3D) {
/* 80 */     PickPoint pickPoint = new PickPoint();
/*    */     
/* 82 */     pickPoint.location.x = this.location.x;
/* 83 */     pickPoint.location.y = this.location.y;
/* 84 */     pickPoint.location.z = this.location.z;
/*    */     
/* 86 */     paramTransform3D.transform(pickPoint.location);
/*    */     
/* 88 */     return pickPoint;
/*    */   }
/*    */ 
/*    */   
/* 92 */   Point3d getStartPoint() { return this.location; }
/*    */ 
/*    */ 
/*    */   
/* 96 */   int getPickType() { return 3; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\PickPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */