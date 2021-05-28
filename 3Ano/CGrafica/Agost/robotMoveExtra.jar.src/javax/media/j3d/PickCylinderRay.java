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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PickCylinderRay
/*     */   extends PickCylinder
/*     */ {
/*     */   public PickCylinderRay() {}
/*     */   
/*     */   public PickCylinderRay(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/*  47 */     this.origin = new Point3d(paramPoint3d);
/*  48 */     this.direction = new Vector3d(paramVector3d);
/*  49 */     this.radius = paramDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/*  60 */     this.origin.set(paramPoint3d);
/*  61 */     this.direction.set(paramVector3d);
/*  62 */     this.radius = paramDouble;
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
/*  73 */     Point4d point4d = new Point4d();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     if (paramBounds instanceof BoundingSphere) {
/*  79 */       Point3d point3d = ((BoundingSphere)paramBounds).getCenter();
/*  80 */       double d1 = ((BoundingSphere)paramBounds).getRadius();
/*  81 */       double d2 = Distance.pointToRay(point3d, this.origin, this.direction);
/*     */       
/*  83 */       if (d2 <= (d1 + this.radius) * (d1 + this.radius)) {
/*  84 */         return true;
/*     */       }
/*  86 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  91 */     if (paramBounds instanceof BoundingBox) {
/*     */       
/*  93 */       Point3d point3d1 = new Point3d();
/*  94 */       ((BoundingBox)paramBounds).getLower(point3d1);
/*     */       
/*  96 */       Point3d point3d2 = ((BoundingBox)paramBounds).getCenter();
/*     */       
/*  98 */       double d1 = point3d2.x - point3d1.x + this.radius;
/*  99 */       double d2 = d1 * d1;
/* 100 */       d1 = point3d2.y - point3d1.y + this.radius;
/* 101 */       d2 += d1 * d1;
/* 102 */       d1 = point3d2.z - point3d1.z + this.radius;
/* 103 */       d2 += d1 * d1;
/*     */ 
/*     */       
/* 106 */       double d3 = Distance.pointToRay(point3d2, this.origin, this.direction);
/*     */ 
/*     */       
/* 109 */       if (d3 > d2) {
/* 110 */         return false;
/*     */       }
/* 112 */       if (d3 < this.radius * this.radius) {
/* 113 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 117 */       if (paramBounds.intersect(this.origin, this.direction, point4d)) {
/* 118 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 122 */       Point3d point3d3 = new Point3d();
/* 123 */       ((BoundingBox)paramBounds).getUpper(point3d3);
/*     */       
/* 125 */       Point3d[][] arrayOfPoint3d = { { point3d3, new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d3.z), new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d1.y, point3d3.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d3.z), point3d3 }, { point3d1, new Point3d(point3d1.x, point3d3.y, point3d1.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d1.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d1.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), point3d1 }, { point3d1, new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) } };
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
/* 143 */       for (byte b = 0; b < arrayOfPoint3d.length; b++) {
/*     */         
/* 145 */         double d = Distance.rayToSegment(this.origin, this.direction, arrayOfPoint3d[b][0], arrayOfPoint3d[b][1]);
/*     */         
/* 147 */         if (d <= this.radius * this.radius)
/*     */         {
/* 149 */           return true;
/*     */         }
/*     */       } 
/*     */       
/* 153 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 158 */     if (paramBounds instanceof BoundingPolytope) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       Point3d point3d1 = new Point3d();
/* 164 */       BoundingSphere boundingSphere = new BoundingSphere(paramBounds);
/*     */       
/* 166 */       boundingSphere.getCenter(point3d1);
/* 167 */       double d1 = boundingSphere.getRadius();
/*     */       
/* 169 */       double d2 = Distance.pointToRay(point3d1, this.origin, this.direction);
/*     */       
/* 171 */       if (d2 > (d1 + this.radius) * (d1 + this.radius)) {
/* 172 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 176 */       if (paramBounds.intersect(this.origin, this.direction, point4d)) {
/* 177 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 183 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 184 */       Point3d point3d2 = new Point3d();
/*     */       
/* 186 */       for (byte b = 0; b < boundingPolytope.nVerts; b++) {
/* 187 */         for (boolean bool = b; b < boundingPolytope.nVerts; b++) {
/*     */ 
/*     */           
/* 190 */           point3d2.x = ((boundingPolytope.verts[b]).x + (boundingPolytope.verts[bool]).x) * 0.5D;
/* 191 */           point3d2.y = ((boundingPolytope.verts[b]).y + (boundingPolytope.verts[bool]).y) * 0.5D;
/* 192 */           point3d2.z = ((boundingPolytope.verts[b]).z + (boundingPolytope.verts[bool]).z) * 0.5D;
/*     */           
/* 194 */           if (PickCylinder.pointInPolytope(boundingPolytope, point3d2.x, point3d2.y, point3d2.z)) {
/*     */ 
/*     */ 
/*     */             
/* 198 */             double d = Distance.rayToSegment(this.origin, this.direction, boundingPolytope.verts[b], boundingPolytope.verts[bool]);
/*     */ 
/*     */             
/* 201 */             if (d <= this.radius * this.radius)
/* 202 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 206 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PickShape transform(Transform3D paramTransform3D) {
/* 221 */     PickCylinderRay pickCylinderRay = new PickCylinderRay();
/* 222 */     Point3d point3d = new Point3d();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     pickCylinderRay.origin.x = this.origin.x;
/* 228 */     pickCylinderRay.origin.y = this.origin.y;
/* 229 */     pickCylinderRay.origin.z = this.origin.z;
/* 230 */     this.radius *= paramTransform3D.getScale();
/*     */     
/* 232 */     this.origin.x += this.direction.x;
/* 233 */     this.origin.y += this.direction.y;
/* 234 */     this.origin.z += this.direction.z;
/*     */     
/* 236 */     paramTransform3D.transform(pickCylinderRay.origin);
/* 237 */     paramTransform3D.transform(point3d);
/*     */     
/* 239 */     pickCylinderRay.direction.x = point3d.x - pickCylinderRay.origin.x;
/* 240 */     pickCylinderRay.direction.y = point3d.y - pickCylinderRay.origin.y;
/* 241 */     pickCylinderRay.direction.z = point3d.z - pickCylinderRay.origin.z;
/* 242 */     pickCylinderRay.direction.normalize();
/*     */     
/* 244 */     return pickCylinderRay;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PickCylinderRay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */