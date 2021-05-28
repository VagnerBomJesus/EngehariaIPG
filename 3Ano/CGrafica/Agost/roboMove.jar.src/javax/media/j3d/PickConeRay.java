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
/*     */ public final class PickConeRay
/*     */   extends PickCone
/*     */ {
/*     */   public PickConeRay() {}
/*     */   
/*     */   public PickConeRay(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/*  46 */     this.origin = new Point3d(paramPoint3d);
/*  47 */     this.direction = new Vector3d(paramVector3d);
/*  48 */     this.spreadAngle = paramDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/*  58 */     this.origin.set(paramPoint3d);
/*  59 */     this.direction.set(paramVector3d);
/*  60 */     this.spreadAngle = paramDouble;
/*     */   }
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
/*  72 */     Point4d point4d = new Point4d();
/*  73 */     Vector3d vector3d = new Vector3d();
/*     */ 
/*     */     
/*  76 */     Point3d point3d = new Point3d();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (paramBounds instanceof BoundingSphere) {
/*  82 */       Point3d point3d1 = ((BoundingSphere)paramBounds).getCenter();
/*  83 */       double d3 = ((BoundingSphere)paramBounds).getRadius();
/*  84 */       double d4 = Distance.pointToRay(point3d1, this.origin, this.direction, point3d, null);
/*     */       
/*  86 */       vector3d.sub(point3d, this.origin);
/*  87 */       double d1 = vector3d.length();
/*  88 */       double d2 = getRadius(d1);
/*  89 */       if (d4 <= (d3 + d2) * (d3 + d2)) {
/*  90 */         return true;
/*     */       }
/*  92 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (paramBounds instanceof BoundingBox) {
/*     */       
/*  99 */       Point3d point3d1 = new Point3d();
/* 100 */       ((BoundingBox)paramBounds).getLower(point3d1);
/*     */       
/* 102 */       Point3d point3d2 = ((BoundingBox)paramBounds).getCenter();
/*     */ 
/*     */       
/* 105 */       double d3 = Distance.pointToRay(point3d2, this.origin, this.direction, point3d, null);
/*     */ 
/*     */       
/* 108 */       vector3d.sub(point3d, this.origin);
/* 109 */       double d1 = vector3d.length();
/* 110 */       double d2 = getRadius(d1);
/*     */       
/* 112 */       double d4 = point3d2.x - point3d1.x + d2;
/* 113 */       double d5 = d4 * d4;
/* 114 */       d4 = point3d2.y - point3d1.y + d2;
/* 115 */       d5 += d4 * d4;
/* 116 */       d4 = point3d2.z - point3d1.z + d2;
/* 117 */       d5 += d4 * d4;
/*     */ 
/*     */       
/* 120 */       if (d3 > d5) {
/* 121 */         return false;
/*     */       }
/* 123 */       if (d3 < d2 * d2) {
/* 124 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 128 */       if (((BoundingBox)paramBounds).intersect(this.origin, this.direction, point4d)) {
/* 129 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 133 */       Point3d point3d3 = new Point3d();
/* 134 */       ((BoundingBox)paramBounds).getUpper(point3d3);
/*     */       
/* 136 */       Point3d[][] arrayOfPoint3d = { { point3d3, new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d3.z), new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d1.y, point3d3.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d3.z), point3d3 }, { point3d1, new Point3d(point3d1.x, point3d3.y, point3d1.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d1.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d1.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), point3d1 }, { point3d1, new Point3d(point3d1.x, point3d1.y, point3d3.z) }, { new Point3d(point3d1.x, point3d3.y, point3d1.z), new Point3d(point3d1.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d3.y, point3d1.z), new Point3d(point3d3.x, point3d3.y, point3d3.z) }, { new Point3d(point3d3.x, point3d1.y, point3d1.z), new Point3d(point3d3.x, point3d1.y, point3d3.z) } };
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
/* 154 */       for (byte b = 0; b < arrayOfPoint3d.length; b++) {
/*     */         
/* 156 */         double d = Distance.rayToSegment(this.origin, this.direction, arrayOfPoint3d[b][0], arrayOfPoint3d[b][1], point3d, null, null);
/*     */ 
/*     */ 
/*     */         
/* 160 */         vector3d.sub(point3d, this.origin);
/* 161 */         d1 = vector3d.length();
/* 162 */         d2 = getRadius(d1);
/*     */         
/* 164 */         if (d <= d2 * d2)
/*     */         {
/* 166 */           return true;
/*     */         }
/*     */       } 
/* 169 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 174 */     if (paramBounds instanceof BoundingPolytope) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       Point3d point3d1 = new Point3d();
/* 180 */       BoundingSphere boundingSphere = new BoundingSphere(paramBounds);
/*     */       
/* 182 */       boundingSphere.getCenter(point3d1);
/* 183 */       double d3 = boundingSphere.getRadius();
/*     */       
/* 185 */       double d4 = Distance.pointToRay(point3d1, this.origin, this.direction, point3d, null);
/*     */       
/* 187 */       vector3d.sub(point3d, this.origin);
/* 188 */       double d1 = vector3d.length();
/* 189 */       double d2 = getRadius(d1);
/* 190 */       if (d4 > (d3 + d2) * (d3 + d2)) {
/* 191 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 195 */       if (paramBounds.intersect(this.origin, this.direction, point4d)) {
/* 196 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 203 */       Point3d point3d2 = new Point3d();
/*     */       
/* 205 */       for (byte b = 0; b < boundingPolytope.nVerts; b++) {
/* 206 */         for (boolean bool = b; b < boundingPolytope.nVerts; b++) {
/*     */ 
/*     */           
/* 209 */           point3d2.x = ((boundingPolytope.verts[b]).x + (boundingPolytope.verts[bool]).x) * 0.5D;
/* 210 */           point3d2.y = ((boundingPolytope.verts[b]).y + (boundingPolytope.verts[bool]).y) * 0.5D;
/* 211 */           point3d2.z = ((boundingPolytope.verts[b]).z + (boundingPolytope.verts[bool]).z) * 0.5D;
/*     */           
/* 213 */           if (PickCylinder.pointInPolytope(boundingPolytope, point3d2.x, point3d2.y, point3d2.z)) {
/*     */ 
/*     */ 
/*     */             
/* 217 */             double d = Distance.rayToSegment(this.origin, this.direction, boundingPolytope.verts[b], boundingPolytope.verts[bool], point3d, null, null);
/*     */ 
/*     */ 
/*     */             
/* 221 */             vector3d.sub(point3d, this.origin);
/* 222 */             d1 = vector3d.length();
/* 223 */             d2 = getRadius(d1);
/* 224 */             if (d <= d2 * d2)
/* 225 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 229 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PickShape transform(Transform3D paramTransform3D) {
/* 243 */     Point3d point3d = new Point3d();
/*     */     
/* 245 */     PickConeRay pickConeRay = new PickConeRay();
/*     */     
/* 247 */     pickConeRay.origin.x = this.origin.x;
/* 248 */     pickConeRay.origin.y = this.origin.y;
/* 249 */     pickConeRay.origin.z = this.origin.z;
/* 250 */     pickConeRay.spreadAngle = this.spreadAngle;
/*     */     
/* 252 */     this.origin.x += this.direction.x;
/* 253 */     this.origin.y += this.direction.y;
/* 254 */     this.origin.z += this.direction.z;
/*     */     
/* 256 */     paramTransform3D.transform(pickConeRay.origin);
/* 257 */     paramTransform3D.transform(point3d);
/*     */     
/* 259 */     pickConeRay.direction.x = point3d.x - pickConeRay.origin.x;
/* 260 */     pickConeRay.direction.y = point3d.y - pickConeRay.origin.y;
/* 261 */     pickConeRay.direction.z = point3d.z - pickConeRay.origin.z;
/* 262 */     pickConeRay.direction.normalize();
/*     */     
/* 264 */     return pickConeRay;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\PickConeRay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */