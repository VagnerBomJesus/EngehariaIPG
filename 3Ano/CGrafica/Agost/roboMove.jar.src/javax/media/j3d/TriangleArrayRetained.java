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
/*     */ 
/*     */ 
/*     */ 
/*     */ class TriangleArrayRetained
/*     */   extends GeometryArrayRetained
/*     */ {
/*     */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) {
/*     */     PickCone pickCone;
/*     */     PickCylinder pickCylinder;
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/*     */     PickSegment pickSegment;
/*     */     PickRay pickRay;
/*  32 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*  33 */     double[] arrayOfDouble = new double[1];
/*  34 */     double d1 = Double.MAX_VALUE;
/*  35 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  36 */     int[] arrayOfInt = new int[3];
/*     */     
/*  38 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/*  40 */     arrayOfPoint3d[0] = new Point3d();
/*  41 */     arrayOfPoint3d[1] = new Point3d();
/*  42 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/*  44 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  46 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  48 */         while (i < this.validVertexCount) {
/*  49 */           for (byte b = 0; b < 3; b++) {
/*  50 */             arrayOfInt[b] = i;
/*  51 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/*  53 */           if (intersectRay(arrayOfPoint3d, pickRay, arrayOfDouble, paramPoint3d)) {
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
/*     */         
/*  77 */         while (i < this.validVertexCount) {
/*  78 */           for (byte b = 0; b < 3; b++) {
/*  79 */             arrayOfInt[b] = i;
/*  80 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/*  82 */           if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
/*     */             
/*  84 */             if (paramInt1 == 0) {
/*  85 */               return true;
/*     */             }
/*  87 */             if (arrayOfDouble[0] < d1) {
/*  88 */               d1 = arrayOfDouble[0];
/*  89 */               d2 = paramPoint3d.x;
/*  90 */               d3 = paramPoint3d.y;
/*  91 */               d4 = paramPoint3d.z;
/*  92 */               if ((paramInt1 & 0x20) != 0) {
/*  93 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  97 */             if ((paramInt1 & 0x40) != 0) {
/*  98 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/* 105 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 108 */         while (i < this.validVertexCount) {
/* 109 */           for (byte b = 0; b < 3; b++) {
/* 110 */             arrayOfInt[b] = i;
/* 111 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 113 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 114 */             if (paramInt1 == 0) {
/* 115 */               return true;
/*     */             }
/* 117 */             if (arrayOfDouble[0] < d1) {
/* 118 */               d1 = arrayOfDouble[0];
/* 119 */               d2 = paramPoint3d.x;
/* 120 */               d3 = paramPoint3d.y;
/* 121 */               d4 = paramPoint3d.z;
/* 122 */               if ((paramInt1 & 0x20) != 0) {
/* 123 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 127 */             if ((paramInt1 & 0x40) != 0) {
/* 128 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 7:
/* 135 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 138 */         while (i < this.validVertexCount) {
/* 139 */           for (byte b = 0; b < 3; b++) {
/* 140 */             arrayOfInt[b] = i;
/* 141 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 143 */           if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/* 144 */             if (paramInt1 == 0) {
/* 145 */               return true;
/*     */             }
/* 147 */             if (arrayOfDouble[0] < d1) {
/* 148 */               d1 = arrayOfDouble[0];
/* 149 */               d2 = paramPoint3d.x;
/* 150 */               d3 = paramPoint3d.y;
/* 151 */               d4 = paramPoint3d.z;
/* 152 */               if ((paramInt1 & 0x20) != 0) {
/* 153 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 157 */             if ((paramInt1 & 0x40) != 0) {
/* 158 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 8:
/* 165 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 168 */         while (i < this.validVertexCount) {
/* 169 */           for (byte b = 0; b < 3; b++) {
/* 170 */             arrayOfInt[b] = i;
/* 171 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 173 */           if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/*     */             
/* 175 */             if (paramInt1 == 0) {
/* 176 */               return true;
/*     */             }
/* 178 */             if (arrayOfDouble[0] < d1) {
/* 179 */               d1 = arrayOfDouble[0];
/* 180 */               d2 = paramPoint3d.x;
/* 181 */               d3 = paramPoint3d.y;
/* 182 */               d4 = paramPoint3d.z;
/* 183 */               if ((paramInt1 & 0x20) != 0) {
/* 184 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 188 */             if ((paramInt1 & 0x40) != 0) {
/* 189 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 196 */         pickCylinder = (PickCylinder)paramPickShape;
/* 197 */         while (i < this.validVertexCount) {
/* 198 */           for (byte b = 0; b < 3; b++) {
/* 199 */             arrayOfInt[b] = i;
/* 200 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 202 */           if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/*     */             
/* 204 */             if (paramInt1 == 0) {
/* 205 */               return true;
/*     */             }
/* 207 */             if (arrayOfDouble[0] < d1) {
/* 208 */               d1 = arrayOfDouble[0];
/* 209 */               d2 = paramPoint3d.x;
/* 210 */               d3 = paramPoint3d.y;
/* 211 */               d4 = paramPoint3d.z;
/* 212 */               if ((paramInt1 & 0x20) != 0) {
/* 213 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 217 */             if ((paramInt1 & 0x40) != 0) {
/* 218 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 225 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 227 */         while (i < this.validVertexCount) {
/* 228 */           for (byte b = 0; b < 3; b++) {
/* 229 */             arrayOfInt[b] = i;
/* 230 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 232 */           if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 233 */             if (paramInt1 == 0) {
/* 234 */               return true;
/*     */             }
/* 236 */             if (arrayOfDouble[0] < d1) {
/* 237 */               d1 = arrayOfDouble[0];
/* 238 */               d2 = paramPoint3d.x;
/* 239 */               d3 = paramPoint3d.y;
/* 240 */               d4 = paramPoint3d.z;
/* 241 */               if ((paramInt1 & 0x20) != 0) {
/* 242 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 246 */             if ((paramInt1 & 0x40) != 0) {
/* 247 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 255 */         throw new IllegalArgumentException(J3dI18N.getString("TriangleArrayRetained0"));
/*     */       default:
/* 257 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */ 
/*     */     
/* 261 */     if (d1 < Double.MAX_VALUE) {
/* 262 */       paramPoint3d.x = d2;
/* 263 */       paramPoint3d.y = d3;
/* 264 */       paramPoint3d.z = d4;
/* 265 */       return true;
/*     */     } 
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 272 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 273 */     double[] arrayOfDouble = new double[1];
/* 274 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 277 */     arrayOfPoint3d[0] = new Point3d();
/* 278 */     arrayOfPoint3d[1] = new Point3d();
/* 279 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 281 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 283 */         while (i < this.validVertexCount) {
/* 284 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 285 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 286 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 287 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */           {
/* 289 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 294 */         while (i < this.validVertexCount) {
/* 295 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 296 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 297 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 298 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */           {
/*     */ 
/*     */             
/* 302 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 307 */         while (i < this.validVertexCount) {
/* 308 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 309 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 310 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 311 */           if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */           {
/* 313 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 318 */         while (i < this.validVertexCount) {
/* 319 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 320 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 321 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 322 */           if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]))
/*     */           {
/* 324 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 329 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 335 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 336 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 338 */     arrayOfPoint3d[0] = new Point3d();
/* 339 */     arrayOfPoint3d[1] = new Point3d();
/* 340 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 342 */     while (i < this.validVertexCount) {
/* 343 */       getVertexData(i++, arrayOfPoint3d[0]);
/* 344 */       getVertexData(i++, arrayOfPoint3d[1]);
/* 345 */       getVertexData(i++, arrayOfPoint3d[2]);
/* 346 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 347 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 348 */       paramTransform3D.transform(arrayOfPoint3d[2]);
/* 349 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 350 */         return true;
/*     */       }
/*     */     } 
/* 353 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 358 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 359 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 361 */     arrayOfPoint3d[0] = new Point3d();
/* 362 */     arrayOfPoint3d[1] = new Point3d();
/* 363 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 365 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 367 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 369 */         while (i < this.validVertexCount) {
/* 370 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 371 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 372 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 373 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 374 */             return true;
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
/*     */         
/* 409 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (i < this.validVertexCount) { getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); getVertexData(i++, arrayOfPoint3d[1]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (i < this.validVertexCount) { getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); getVertexData(i++, arrayOfPoint3d[2]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/*     */   void computeCentroid() {
/* 414 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 417 */     Point3d point3d1 = new Point3d();
/* 418 */     Point3d point3d2 = new Point3d();
/* 419 */     Point3d point3d3 = new Point3d();
/* 420 */     Vector3d vector3d1 = new Vector3d();
/* 421 */     Vector3d vector3d2 = new Vector3d();
/* 422 */     Vector3d vector3d3 = new Vector3d();
/*     */ 
/*     */     
/* 425 */     double d = 0.0D;
/*     */     
/* 427 */     this.centroid.x = 0.0D;
/* 428 */     this.centroid.y = 0.0D;
/* 429 */     this.centroid.z = 0.0D;
/*     */ 
/*     */     
/* 432 */     while (i < this.validVertexCount) {
/* 433 */       getVertexData(i++, point3d1);
/* 434 */       getVertexData(i++, point3d2);
/* 435 */       getVertexData(i++, point3d3);
/*     */ 
/*     */       
/* 438 */       vector3d1.sub(point3d1, point3d2);
/* 439 */       vector3d3.sub(point3d2, point3d3);
/*     */ 
/*     */       
/* 442 */       vector3d2.cross(vector3d1, vector3d3);
/* 443 */       vector3d2.normalize();
/*     */ 
/*     */       
/* 446 */       if (Double.isNaN(vector3d2.x + vector3d2.y + vector3d2.z)) {
/*     */         continue;
/*     */       }
/*     */       
/* 450 */       getCrossValue(point3d1, point3d2, vector3d3);
/* 451 */       getCrossValue(point3d2, point3d3, vector3d3);
/* 452 */       getCrossValue(point3d3, point3d1, vector3d3);
/* 453 */       double d1 = vector3d2.dot(vector3d3);
/* 454 */       this.centroid.x += (point3d1.x + point3d2.x + point3d3.x) * d1;
/* 455 */       this.centroid.y += (point3d1.y + point3d2.y + point3d3.y) * d1;
/* 456 */       this.centroid.z += (point3d1.z + point3d2.z + point3d3.z) * d1;
/* 457 */       d += d1;
/*     */     } 
/*     */     
/* 460 */     if (d != 0.0D) {
/* 461 */       double d1 = 1.0D / 3.0D * d;
/* 462 */       this.centroid.x *= d1;
/* 463 */       this.centroid.y *= d1;
/* 464 */       this.centroid.z *= d1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 469 */   int getClassType() { return 3; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TriangleArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */