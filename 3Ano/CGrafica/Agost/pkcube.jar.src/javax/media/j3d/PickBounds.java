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
/*    */ public final class PickBounds
/*    */   extends PickShape
/*    */ {
/*    */   Bounds bounds;
/*    */   
/* 32 */   public PickBounds() { this.bounds = null; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public PickBounds(Bounds paramBounds) { this.bounds = paramBounds; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void set(Bounds paramBounds) { this.bounds = paramBounds; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public Bounds get() { return this.bounds; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   final boolean intersect(Bounds paramBounds, Point4d paramPoint4d) { return paramBounds.intersect(this.bounds, paramPoint4d); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   PickShape transform(Transform3D paramTransform3D) {
/* 74 */     Bounds bounds1 = (Bounds)this.bounds.clone();
/* 75 */     bounds1.transform(paramTransform3D);
/* 76 */     return new PickBounds(bounds1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 82 */   Point3d getStartPoint() { return this.bounds.getCenter(); }
/*    */ 
/*    */ 
/*    */   
/* 86 */   int getPickType() { return (this.bounds != null) ? this.bounds.getPickType() : 9; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\PickBounds.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */