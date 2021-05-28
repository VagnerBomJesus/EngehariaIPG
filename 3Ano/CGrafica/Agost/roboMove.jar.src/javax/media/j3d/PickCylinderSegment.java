/*     */ package javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.internal.Distance;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PickCylinderSegment
/*     */   extends PickCylinder
/*     */ {
/*     */   Point3d end;
/*     */   
/*  39 */   public PickCylinderSegment() { this.end = new Point3d(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickCylinderSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, double paramDouble) {
/*  50 */     this.origin = new Point3d(paramPoint3d1);
/*  51 */     this.end = new Point3d(paramPoint3d2);
/*  52 */     this.radius = paramDouble;
/*  53 */     calcDirection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Point3d paramPoint3d1, Point3d paramPoint3d2, double paramDouble) {
/*  63 */     this.origin.set(paramPoint3d1);
/*  64 */     this.end.set(paramPoint3d2);
/*  65 */     this.radius = paramDouble;
/*  66 */     calcDirection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calcDirection() {
/*  73 */     this.direction.x = this.end.x - this.origin.x;
/*  74 */     this.direction.y = this.end.y - this.origin.y;
/*  75 */     this.direction.z = this.end.z - this.origin.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void getEnd(Point3d paramPoint3d) { paramPoint3d.set(this.end); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final boolean intersect(Bounds paramBounds, Point4d paramPoint4d) {
/*  95 */     Point4d point4d = new Point4d();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     if (paramBounds instanceof BoundingSphere) {
/* 101 */       Point3d point3d = ((BoundingSphere)paramBounds).getCenter();
/* 102 */       double d1 = ((BoundingSphere)paramBounds).getRadius();
/* 103 */       double d2 = Distance.pointToSegment(point3d, this.origin, this.end);
/*     */       
/* 105 */       if (d2 <= (d1 + this.radius) * (d1 + this.radius)) {
/* 106 */         return true;
/*     */       }
/* 108 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (paramBounds instanceof BoundingBox) {
/*     */       
/* 115 */       Point3d point3d1 = new Point3d();
/* 116 */       ((BoundingBox)paramBounds).getLower(point3d1);
/*     */       
/* 118 */       Point3d point3d2 = ((BoundingBox)paramBounds).getCenter();
/*     */       
/* 120 */       double d1 = point3d2.x - point3d1.x + this.radius;
/* 121 */       double d2 = d1 * d1;
/* 122 */       d1 = point3d2.y - point3d1.y + this.radius;
/* 123 */       d2 += d1 * d1;
/* 124 */       d1 = point3d2.z - point3d1.z + this.radius;
/* 125 */       d2 += d1 * d1;
/*     */ 
/*     */       
/* 128 */       double d3 = Distance.pointToSegment(point3d2, this.origin, this.end);
/*     */       
/* 130 */       if (d3 > d2) {
/* 131 */         return false;
/*     */       }
/* 133 */       if (d3 < this.radius * this.radius) {
/* 134 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 138 */       if (((BoundingBox)paramBounds).intersect(this.origin, this.end, point4d)) {
/* 139 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 143 */       Point3d point3d3 = new Point3d();
/* 144 */       ((BoundingBox)paramBounds).getUpper(point3d3);
/*     */       
/* 146 */       Point3d[][] arrayOfPoint3d = { { point3d3, new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d3.z), new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d1.y, point3d3.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d3.z), point3d3 }, { point3d1, new Point3d(point3d1.x, point3d3.y, point3d1.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d1.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d1.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), point3d1 }, { point3d1, new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) } };
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
/* 163 */       for (byte b = 0; b < arrayOfPoint3d.length; b++) {
/*     */         
/* 165 */         double d = Distance.segmentToSegment(this.origin, this.end, arrayOfPoint3d[b][0], arrayOfPoint3d[b][1]);
/*     */ 
/*     */         
/* 168 */         if (d <= this.radius * this.radius)
/*     */         {
/* 170 */           return true;
/*     */         }
/*     */       } 
/* 173 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (paramBounds instanceof BoundingPolytope) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 183 */       Point3d point3d1 = new Point3d();
/* 184 */       BoundingSphere boundingSphere = new BoundingSphere(paramBounds);
/*     */       
/* 186 */       boundingSphere.getCenter(point3d1);
/* 187 */       double d1 = boundingSphere.getRadius();
/*     */       
/* 189 */       double d2 = Distance.pointToSegment(point3d1, this.origin, this.end);
/*     */       
/* 191 */       if (d2 > (d1 + this.radius) * (d1 + this.radius)) {
/* 192 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 196 */       if (paramBounds.intersect(this.origin, this.direction, point4d)) {
/* 197 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 203 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 204 */       Point3d point3d2 = new Point3d();
/*     */       
/* 206 */       for (byte b = 0; b < boundingPolytope.nVerts; b++) {
/* 207 */         for (boolean bool = b; b < boundingPolytope.nVerts; b++) {
/*     */ 
/*     */           
/* 210 */           point3d2.x = ((boundingPolytope.verts[b]).x + (boundingPolytope.verts[bool]).x) * 0.5D;
/* 211 */           point3d2.y = ((boundingPolytope.verts[b]).y + (boundingPolytope.verts[bool]).y) * 0.5D;
/* 212 */           point3d2.z = ((boundingPolytope.verts[b]).z + (boundingPolytope.verts[bool]).z) * 0.5D;
/*     */           
/* 214 */           if (PickCylinder.pointInPolytope(boundingPolytope, point3d2.x, point3d2.y, point3d2.z)) {
/*     */ 
/*     */ 
/*     */             
/* 218 */             double d = Distance.segmentToSegment(this.origin, this.end, boundingPolytope.verts[b], boundingPolytope.verts[bool]);
/*     */ 
/*     */             
/* 221 */             if (d <= this.radius * this.radius)
/* 222 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 226 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PickShape transform(Transform3D paramTransform3D) {
/* 241 */     PickCylinderSegment pickCylinderSegment = new PickCylinderSegment();
/*     */     
/* 243 */     pickCylinderSegment.origin.x = this.origin.x;
/* 244 */     pickCylinderSegment.origin.y = this.origin.y;
/* 245 */     pickCylinderSegment.origin.z = this.origin.z;
/* 246 */     this.radius *= paramTransform3D.getScale();
/* 247 */     pickCylinderSegment.end.x = this.end.x;
/* 248 */     pickCylinderSegment.end.y = this.end.y;
/* 249 */     pickCylinderSegment.end.z = this.end.z;
/*     */     
/* 251 */     paramTransform3D.transform(pickCylinderSegment.origin);
/* 252 */     paramTransform3D.transform(pickCylinderSegment.end);
/*     */     
/* 254 */     pickCylinderSegment.direction.x = pickCylinderSegment.end.x - pickCylinderSegment.origin.x;
/* 255 */     pickCylinderSegment.direction.y = pickCylinderSegment.end.y - pickCylinderSegment.origin.y;
/* 256 */     pickCylinderSegment.direction.z = pickCylinderSegment.end.z - pickCylinderSegment.origin.z;
/* 257 */     pickCylinderSegment.direction.normalize();
/*     */     
/* 259 */     return pickCylinderSegment;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\PickCylinderSegment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */