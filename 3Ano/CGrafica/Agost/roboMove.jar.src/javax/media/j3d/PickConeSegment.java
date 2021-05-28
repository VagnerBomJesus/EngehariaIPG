/*     */ package javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.internal.Distance;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PickConeSegment
/*     */   extends PickCone
/*     */ {
/*     */   Point3d end;
/*     */   
/*  38 */   public PickConeSegment() { this.end = new Point3d(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickConeSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, double paramDouble) {
/*  49 */     this.origin = new Point3d(paramPoint3d1);
/*  50 */     this.end = new Point3d(paramPoint3d2);
/*  51 */     this.direction = new Vector3d();
/*  52 */     this.spreadAngle = paramDouble;
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
/*  65 */     this.spreadAngle = paramDouble;
/*  66 */     calcDirection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void getEnd(Point3d paramPoint3d) { paramPoint3d.set(this.end); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calcDirection() {
/*  82 */     this.direction.x = this.end.x - this.origin.x;
/*  83 */     this.direction.y = this.end.y - this.origin.y;
/*  84 */     this.direction.z = this.end.z - this.origin.z;
/*     */   }
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
/*  96 */     Vector3d vector3d = new Vector3d();
/*  97 */     Point3d point3d = new Point3d();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (paramBounds instanceof BoundingSphere) {
/* 106 */       Point3d point3d1 = ((BoundingSphere)paramBounds).getCenter();
/* 107 */       double d3 = ((BoundingSphere)paramBounds).getRadius();
/* 108 */       double d4 = Distance.pointToSegment(point3d1, this.origin, this.end, point3d, null);
/*     */ 
/*     */       
/* 111 */       vector3d.sub(point3d, this.origin);
/* 112 */       double d1 = vector3d.length();
/* 113 */       double d2 = getRadius(d1);
/* 114 */       if (d4 <= (d3 + d2) * (d3 + d2)) {
/* 115 */         return true;
/*     */       }
/* 117 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (paramBounds instanceof BoundingBox) {
/*     */       
/* 124 */       Point3d point3d1 = new Point3d();
/* 125 */       ((BoundingBox)paramBounds).getLower(point3d1);
/*     */       
/* 127 */       Point3d point3d2 = ((BoundingBox)paramBounds).getCenter();
/*     */ 
/*     */       
/* 130 */       double d3 = Distance.pointToSegment(point3d2, this.origin, this.end, point3d, null);
/*     */ 
/*     */       
/* 133 */       vector3d.sub(point3d, this.origin);
/* 134 */       double d1 = vector3d.length();
/* 135 */       double d2 = getRadius(d1);
/*     */       
/* 137 */       double d4 = point3d2.x - point3d1.x + d2;
/* 138 */       double d5 = d4 * d4;
/* 139 */       d4 = point3d2.y - point3d1.y + d2;
/* 140 */       d5 += d4 * d4;
/* 141 */       d4 = point3d2.z - point3d1.z + d2;
/* 142 */       d5 += d4 * d4;
/*     */       
/* 144 */       if (d3 > d5) {
/* 145 */         return false;
/*     */       }
/* 147 */       if (d3 < d2 * d2) {
/* 148 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 152 */       if (((BoundingBox)paramBounds).intersect(this.origin, this.direction, point4d)) {
/* 153 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 157 */       Point3d point3d3 = new Point3d();
/* 158 */       ((BoundingBox)paramBounds).getUpper(point3d3);
/*     */       
/* 160 */       Point3d[][] arrayOfPoint3d = { { point3d3, new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d3.z), new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d1.y, point3d3.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d3.z), point3d3 }, { point3d1, new Point3d(point3d1.x, point3d3.y, point3d1.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d1.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d1.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), point3d1 }, { point3d1, new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) } };
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
/* 177 */       for (byte b = 0; b < arrayOfPoint3d.length; b++) {
/*     */         
/* 179 */         double d = Distance.segmentToSegment(this.origin, this.end, arrayOfPoint3d[b][0], arrayOfPoint3d[b][1], point3d, null, null);
/*     */ 
/*     */ 
/*     */         
/* 183 */         vector3d.sub(point3d, this.origin);
/* 184 */         d1 = vector3d.length();
/* 185 */         d2 = getRadius(d1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 191 */         if (d <= d2 * d2)
/*     */         {
/* 193 */           return true;
/*     */         }
/*     */       } 
/* 196 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 201 */     if (paramBounds instanceof BoundingPolytope) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 206 */       Point3d point3d1 = new Point3d();
/* 207 */       BoundingSphere boundingSphere = new BoundingSphere(paramBounds);
/*     */       
/* 209 */       boundingSphere.getCenter(point3d1);
/* 210 */       double d3 = boundingSphere.getRadius();
/*     */       
/* 212 */       double d4 = Distance.pointToSegment(point3d1, this.origin, this.end, point3d, null);
/*     */ 
/*     */       
/* 215 */       vector3d.sub(point3d, this.origin);
/* 216 */       double d1 = vector3d.length();
/* 217 */       double d2 = getRadius(d1);
/*     */       
/* 219 */       if (d4 > (d3 + d2) * (d3 + d2)) {
/* 220 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 224 */       if (paramBounds.intersect(this.origin, this.direction, point4d)) {
/* 225 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 231 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 232 */       Point3d point3d2 = new Point3d();
/*     */       
/* 234 */       for (byte b = 0; b < boundingPolytope.nVerts; b++) {
/* 235 */         for (boolean bool = b; b < boundingPolytope.nVerts; b++) {
/*     */ 
/*     */           
/* 238 */           point3d2.x = ((boundingPolytope.verts[b]).x + (boundingPolytope.verts[bool]).x) * 0.5D;
/* 239 */           point3d2.y = ((boundingPolytope.verts[b]).y + (boundingPolytope.verts[bool]).y) * 0.5D;
/* 240 */           point3d2.z = ((boundingPolytope.verts[b]).z + (boundingPolytope.verts[bool]).z) * 0.5D;
/*     */           
/* 242 */           if (PickCylinder.pointInPolytope(boundingPolytope, point3d2.x, point3d2.y, point3d2.z)) {
/*     */ 
/*     */ 
/*     */             
/* 246 */             double d = Distance.segmentToSegment(this.origin, this.end, boundingPolytope.verts[b], boundingPolytope.verts[bool], point3d, null, null);
/*     */ 
/*     */ 
/*     */             
/* 250 */             vector3d.sub(point3d, this.origin);
/* 251 */             d1 = vector3d.length();
/* 252 */             d2 = getRadius(d1);
/* 253 */             if (d <= d2 * d2)
/* 254 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 258 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PickShape transform(Transform3D paramTransform3D) {
/* 272 */     PickConeSegment pickConeSegment = new PickConeSegment();
/*     */     
/* 274 */     pickConeSegment.origin.x = this.origin.x;
/* 275 */     pickConeSegment.origin.y = this.origin.y;
/* 276 */     pickConeSegment.origin.z = this.origin.z;
/* 277 */     pickConeSegment.spreadAngle = this.spreadAngle;
/* 278 */     pickConeSegment.end.x = this.end.x;
/* 279 */     pickConeSegment.end.y = this.end.y;
/* 280 */     pickConeSegment.end.z = this.end.z;
/*     */     
/* 282 */     paramTransform3D.transform(pickConeSegment.origin);
/* 283 */     paramTransform3D.transform(pickConeSegment.end);
/*     */     
/* 285 */     pickConeSegment.direction.x = pickConeSegment.end.x - pickConeSegment.origin.x;
/* 286 */     pickConeSegment.direction.y = pickConeSegment.end.y - pickConeSegment.origin.y;
/* 287 */     pickConeSegment.direction.z = pickConeSegment.end.z - pickConeSegment.origin.z;
/* 288 */     pickConeSegment.direction.normalize();
/*     */     
/* 290 */     return pickConeSegment;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\PickConeSegment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */