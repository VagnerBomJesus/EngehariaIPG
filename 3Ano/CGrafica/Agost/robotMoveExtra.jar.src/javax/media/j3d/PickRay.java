/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point4d;
/*     */ import javax.vecmath.Vector3d;
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
/*     */ public final class PickRay
/*     */   extends PickShape
/*     */ {
/*     */   Point3d origin;
/*     */   Vector3d direction;
/*     */   
/*     */   public PickRay() {
/*  34 */     this.origin = new Point3d();
/*  35 */     this.direction = new Vector3d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickRay(Point3d paramPoint3d, Vector3d paramVector3d) {
/*  45 */     this.origin = new Point3d(paramPoint3d);
/*  46 */     this.direction = new Vector3d(paramVector3d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Point3d paramPoint3d, Vector3d paramVector3d) {
/*  56 */     this.origin.x = paramPoint3d.x;
/*  57 */     this.origin.y = paramPoint3d.y;
/*  58 */     this.origin.z = paramPoint3d.z;
/*  59 */     this.direction.x = paramVector3d.x;
/*  60 */     this.direction.y = paramVector3d.y;
/*  61 */     this.direction.z = paramVector3d.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void get(Point3d paramPoint3d, Vector3d paramVector3d) {
/*  71 */     paramPoint3d.x = this.origin.x;
/*  72 */     paramPoint3d.y = this.origin.y;
/*  73 */     paramPoint3d.z = this.origin.z;
/*  74 */     paramVector3d.x = this.direction.x;
/*  75 */     paramVector3d.y = this.direction.y;
/*  76 */     paramVector3d.z = this.direction.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   final boolean intersect(Bounds paramBounds, Point4d paramPoint4d) { return paramBounds.intersect(this.origin, this.direction, paramPoint4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PickShape transform(Transform3D paramTransform3D) {
/*  92 */     Point3d point3d = new Point3d();
/*     */     
/*  94 */     PickRay pickRay = new PickRay(this.origin, this.direction);
/*     */     
/*  96 */     this.origin.x += this.direction.x;
/*  97 */     this.origin.y += this.direction.y;
/*  98 */     this.origin.z += this.direction.z;
/*     */     
/* 100 */     paramTransform3D.transform(pickRay.origin);
/* 101 */     paramTransform3D.transform(point3d);
/*     */     
/* 103 */     pickRay.direction.x = point3d.x - pickRay.origin.x;
/* 104 */     pickRay.direction.y = point3d.y - pickRay.origin.y;
/* 105 */     pickRay.direction.z = point3d.z - pickRay.origin.z;
/* 106 */     pickRay.direction.normalize();
/*     */     
/* 108 */     return pickRay;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 113 */   Point3d getStartPoint() { return this.origin; }
/*     */ 
/*     */ 
/*     */   
/* 117 */   int getPickType() { return 1; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PickRay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */