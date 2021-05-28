/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
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
/*     */ class LineArrayRetained
/*     */   extends GeometryArrayRetained
/*     */   implements Cloneable
/*     */ {
/*     */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) {
/*     */     PickCone pickCone;
/*     */     PickCylinder pickCylinder;
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/*     */     Vector3d vector3d;
/*     */     PickSegment pickSegment;
/*     */     PickRay pickRay;
/*  31 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*  32 */     double[] arrayOfDouble = new double[1];
/*  33 */     double d1 = Double.MAX_VALUE;
/*  34 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  35 */     int[] arrayOfInt = new int[2];
/*     */     
/*  37 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/*  39 */     arrayOfPoint3d[0] = new Point3d();
/*  40 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/*  42 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  44 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  46 */         while (i < this.validVertexCount) {
/*  47 */           for (byte b = 0; b < 2; b++) {
/*  48 */             arrayOfInt[b] = i;
/*  49 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/*  51 */           if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickRay.origin, pickRay.direction, arrayOfDouble, paramPoint3d)) {
/*     */ 
/*     */             
/*  54 */             if (paramInt1 == 0) {
/*  55 */               return true;
/*     */             }
/*  57 */             if (arrayOfDouble[0] < d1) {
/*  58 */               d1 = arrayOfDouble[0];
/*  59 */               d2 = paramPoint3d.x;
/*  60 */               d3 = paramPoint3d.y;
/*  61 */               d4 = paramPoint3d.z;
/*  62 */               if ((paramInt1 & 0x20) != 0) {
/*  63 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  67 */             if ((paramInt1 & 0x40) != 0) {
/*  68 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  75 */         pickSegment = (PickSegment)paramPickShape;
/*  76 */         vector3d = new Vector3d(pickSegment.end.x - pickSegment.start.x, pickSegment.end.y - pickSegment.start.y, pickSegment.end.z - pickSegment.start.z);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  81 */         while (i < this.validVertexCount) {
/*  82 */           for (byte b = 0; b < 2; b++) {
/*  83 */             arrayOfInt[b] = i;
/*  84 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/*  86 */           if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickSegment.start, vector3d, arrayOfDouble, paramPoint3d) && arrayOfDouble[0] <= 1.0D) {
/*     */ 
/*     */ 
/*     */             
/*  90 */             if (paramInt1 == 0) {
/*  91 */               return true;
/*     */             }
/*  93 */             if (arrayOfDouble[0] < d1) {
/*  94 */               d1 = arrayOfDouble[0];
/*  95 */               d2 = paramPoint3d.x;
/*  96 */               d3 = paramPoint3d.y;
/*  97 */               d4 = paramPoint3d.z;
/*  98 */               if ((paramInt1 & 0x20) != 0) {
/*  99 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 103 */             if ((paramInt1 & 0x40) != 0) {
/* 104 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/* 111 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */         
/* 113 */         while (i < this.validVertexCount) {
/* 114 */           for (byte b = 0; b < 2; b++) {
/* 115 */             arrayOfInt[b] = i;
/* 116 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 118 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 119 */             if (paramInt1 == 0) {
/* 120 */               return true;
/*     */             }
/* 122 */             if (arrayOfDouble[0] < d1) {
/* 123 */               d1 = arrayOfDouble[0];
/* 124 */               d2 = paramPoint3d.x;
/* 125 */               d3 = paramPoint3d.y;
/* 126 */               d4 = paramPoint3d.z;
/* 127 */               if ((paramInt1 & 0x20) != 0) {
/* 128 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 132 */             if ((paramInt1 & 0x40) != 0) {
/* 133 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 7:
/* 141 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 144 */         while (i < this.validVertexCount) {
/* 145 */           for (byte b = 0; b < 2; b++) {
/* 146 */             arrayOfInt[b] = i;
/* 147 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 149 */           if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/* 150 */             if (paramInt1 == 0) {
/* 151 */               return true;
/*     */             }
/* 153 */             if (arrayOfDouble[0] < d1) {
/* 154 */               d1 = arrayOfDouble[0];
/* 155 */               d2 = paramPoint3d.x;
/* 156 */               d3 = paramPoint3d.y;
/* 157 */               d4 = paramPoint3d.z;
/* 158 */               if ((paramInt1 & 0x20) != 0) {
/* 159 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 163 */             if ((paramInt1 & 0x40) != 0) {
/* 164 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 8:
/* 171 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 174 */         while (i < this.validVertexCount) {
/* 175 */           for (byte b = 0; b < 2; b++) {
/* 176 */             arrayOfInt[b] = i;
/* 177 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 179 */           if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/* 180 */             if (paramInt1 == 0) {
/* 181 */               return true;
/*     */             }
/* 183 */             if (arrayOfDouble[0] < d1) {
/* 184 */               d1 = arrayOfDouble[0];
/* 185 */               d2 = paramPoint3d.x;
/* 186 */               d3 = paramPoint3d.y;
/* 187 */               d4 = paramPoint3d.z;
/* 188 */               if ((paramInt1 & 0x20) != 0) {
/* 189 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 193 */             if ((paramInt1 & 0x40) != 0) {
/* 194 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 201 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 203 */         while (i < this.validVertexCount) {
/* 204 */           for (byte b = 0; b < 2; b++) {
/* 205 */             arrayOfInt[b] = i;
/* 206 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 208 */           if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 209 */             if (paramInt1 == 0) {
/* 210 */               return true;
/*     */             }
/* 212 */             if (arrayOfDouble[0] < d1) {
/* 213 */               d1 = arrayOfDouble[0];
/* 214 */               d2 = paramPoint3d.x;
/* 215 */               d3 = paramPoint3d.y;
/* 216 */               d4 = paramPoint3d.z;
/* 217 */               if ((paramInt1 & 0x20) != 0) {
/* 218 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 222 */             if ((paramInt1 & 0x40) != 0) {
/* 223 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 230 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 232 */         while (i < this.validVertexCount) {
/* 233 */           for (byte b = 0; b < 2; b++) {
/* 234 */             arrayOfInt[b] = i;
/* 235 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 237 */           if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 238 */             if (paramInt1 == 0) {
/* 239 */               return true;
/*     */             }
/* 241 */             if (arrayOfDouble[0] < d1) {
/* 242 */               d1 = arrayOfDouble[0];
/* 243 */               d2 = paramPoint3d.x;
/* 244 */               d3 = paramPoint3d.y;
/* 245 */               d4 = paramPoint3d.z;
/* 246 */               if ((paramInt1 & 0x20) != 0) {
/* 247 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 251 */             if ((paramInt1 & 0x40) != 0) {
/* 252 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 260 */         throw new IllegalArgumentException(J3dI18N.getString("LineArrayRetained0"));
/*     */       default:
/* 262 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 265 */     if (d1 < Double.MAX_VALUE) {
/* 266 */       paramPoint3d.x = d2;
/* 267 */       paramPoint3d.y = d3;
/* 268 */       paramPoint3d.z = d4;
/* 269 */       return true;
/*     */     } 
/* 271 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/*     */     Vector3d vector3d;
/* 276 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 277 */     double[] arrayOfDouble = new double[1];
/*     */     
/* 279 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 282 */     arrayOfPoint3d[0] = new Point3d();
/* 283 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 285 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/*     */       case 4:
/* 288 */         while (i < this.validVertexCount) {
/* 289 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 290 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 291 */           if (intersectSegment(paramArrayOfPoint3d, arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfDouble, null))
/*     */           {
/* 293 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 298 */         vector3d = new Vector3d();
/* 299 */         while (i < this.validVertexCount) {
/* 300 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 301 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 302 */           vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 303 */           vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 304 */           vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 305 */           if (intersectLineAndRay(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfPoint3d[0], vector3d, arrayOfDouble, null) && arrayOfDouble[0] <= 1.0D)
/*     */           {
/*     */             
/* 308 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 313 */         vector3d = new Vector3d();
/* 314 */         while (i < this.validVertexCount) {
/* 315 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 316 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 317 */           vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 318 */           vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 319 */           vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 320 */           if (intersectPntAndRay(paramArrayOfPoint3d[0], arrayOfPoint3d[0], vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D)
/*     */           {
/* 322 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 332 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 333 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 335 */     arrayOfPoint3d[0] = new Point3d();
/* 336 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 338 */     while (i < this.validVertexCount) {
/* 339 */       getVertexData(i++, arrayOfPoint3d[0]);
/* 340 */       getVertexData(i++, arrayOfPoint3d[1]);
/* 341 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 342 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 343 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 344 */         return true;
/*     */       }
/*     */     } 
/* 347 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 352 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 353 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 355 */     arrayOfPoint3d[0] = new Point3d();
/* 356 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 358 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 360 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 362 */         while (i < this.validVertexCount) {
/* 363 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 364 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 365 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 366 */             return true;
/*     */           }
/*     */         } 
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
/* 400 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (i < this.validVertexCount) { getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (i < this.validVertexCount) { getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   } void computeCentroid() {
/* 404 */     Point3d point3d1 = new Point3d();
/* 405 */     Point3d point3d2 = new Point3d();
/*     */     
/* 407 */     double d = 0.0D;
/* 408 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 411 */     this.centroid.x = 0.0D;
/* 412 */     this.centroid.y = 0.0D;
/* 413 */     this.centroid.z = 0.0D;
/*     */     
/* 415 */     while (i < this.validVertexCount) {
/* 416 */       getVertexData(i++, point3d1);
/* 417 */       getVertexData(i++, point3d2);
/* 418 */       double d1 = point3d1.distance(point3d2);
/* 419 */       this.centroid.x += (point3d1.x + point3d2.x) * d1;
/* 420 */       this.centroid.y += (point3d1.y + point3d2.y) * d1;
/* 421 */       this.centroid.z += (point3d1.z + point3d2.z) * d1;
/* 422 */       d += d1;
/*     */     } 
/*     */     
/* 425 */     if (d != 0.0D) {
/* 426 */       double d1 = 1.0D / 2.0D * d;
/* 427 */       this.centroid.x *= d1;
/* 428 */       this.centroid.y *= d1;
/* 429 */       this.centroid.z *= d1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 434 */   int getClassType() { return 2; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\LineArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */