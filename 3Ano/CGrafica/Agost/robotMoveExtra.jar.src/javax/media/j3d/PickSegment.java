/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point4d;
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
/*     */ public final class PickSegment
/*     */   extends PickShape
/*     */ {
/*     */   Point3d start;
/*     */   Point3d end;
/*     */   
/*     */   public PickSegment() {
/*  35 */     this.start = new Point3d();
/*  36 */     this.end = new Point3d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickSegment(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*  46 */     this.start = new Point3d(paramPoint3d1);
/*  47 */     this.end = new Point3d(paramPoint3d2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*  56 */     this.start.x = paramPoint3d1.x;
/*  57 */     this.start.y = paramPoint3d1.y;
/*  58 */     this.start.z = paramPoint3d1.z;
/*  59 */     this.end.x = paramPoint3d2.x;
/*  60 */     this.end.y = paramPoint3d2.y;
/*  61 */     this.end.z = paramPoint3d2.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void get(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*  72 */     paramPoint3d1.x = this.start.x;
/*  73 */     paramPoint3d1.y = this.start.y;
/*  74 */     paramPoint3d1.z = this.start.z;
/*  75 */     paramPoint3d2.x = this.end.x;
/*  76 */     paramPoint3d2.y = this.end.y;
/*  77 */     paramPoint3d2.z = this.end.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   final boolean intersect(Bounds paramBounds, Point4d paramPoint4d) { return paramBounds.intersect(this.start, this.end, paramPoint4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PickShape transform(Transform3D paramTransform3D) {
/*  93 */     PickSegment pickSegment = new PickSegment(this.start, this.end);
/*  94 */     paramTransform3D.transform(pickSegment.start);
/*  95 */     paramTransform3D.transform(pickSegment.end);
/*  96 */     return pickSegment;
/*     */   }
/*     */ 
/*     */   
/* 100 */   Point3d getStartPoint() { return this.start; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   int getPickType() { return 2; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PickSegment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */