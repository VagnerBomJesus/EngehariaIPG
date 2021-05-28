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
/*     */ 
/*     */ class QuadArrayRetained
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
/*  33 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*  34 */     double[] arrayOfDouble = new double[1];
/*  35 */     double d1 = Double.MAX_VALUE;
/*  36 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  37 */     int[] arrayOfInt = new int[4];
/*     */     
/*  39 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/*  41 */     arrayOfPoint3d[0] = new Point3d();
/*  42 */     arrayOfPoint3d[1] = new Point3d();
/*  43 */     arrayOfPoint3d[2] = new Point3d();
/*  44 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/*  46 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  48 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  50 */         while (i < this.validVertexCount) {
/*  51 */           for (byte b = 0; b < 4; b++) {
/*  52 */             arrayOfInt[b] = i;
/*  53 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/*  55 */           if (intersectRay(arrayOfPoint3d, pickRay, arrayOfDouble, paramPoint3d)) {
/*  56 */             if (paramInt1 == 0) {
/*  57 */               return true;
/*     */             }
/*  59 */             if (arrayOfDouble[0] < d1) {
/*  60 */               d1 = arrayOfDouble[0];
/*  61 */               d2 = paramPoint3d.x;
/*  62 */               d3 = paramPoint3d.y;
/*  63 */               d4 = paramPoint3d.z;
/*  64 */               if ((paramInt1 & 0x20) != 0) {
/*  65 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  69 */             if ((paramInt1 & 0x40) != 0) {
/*  70 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  77 */         pickSegment = (PickSegment)paramPickShape;
/*     */         
/*  79 */         while (i < this.validVertexCount) {
/*  80 */           for (byte b = 0; b < 4; b++) {
/*  81 */             arrayOfInt[b] = i;
/*  82 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/*  84 */           if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
/*     */             
/*  86 */             if (paramInt1 == 0) {
/*  87 */               return true;
/*     */             }
/*  89 */             if (arrayOfDouble[0] < d1) {
/*  90 */               d1 = arrayOfDouble[0];
/*  91 */               d2 = paramPoint3d.x;
/*  92 */               d3 = paramPoint3d.y;
/*  93 */               d4 = paramPoint3d.z;
/*  94 */               if ((paramInt1 & 0x20) != 0) {
/*  95 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  99 */             if ((paramInt1 & 0x40) != 0) {
/* 100 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/* 107 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */         
/* 109 */         while (i < this.validVertexCount) {
/* 110 */           for (byte b = 0; b < 4; b++) {
/* 111 */             arrayOfInt[b] = i;
/* 112 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 114 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 115 */             if (paramInt1 == 0) {
/* 116 */               return true;
/*     */             }
/* 118 */             if (arrayOfDouble[0] < d1) {
/* 119 */               d1 = arrayOfDouble[0];
/* 120 */               d2 = paramPoint3d.x;
/* 121 */               d3 = paramPoint3d.y;
/* 122 */               d4 = paramPoint3d.z;
/* 123 */               if ((paramInt1 & 0x20) != 0) {
/* 124 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 128 */             if ((paramInt1 & 0x40) != 0) {
/* 129 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 7:
/* 136 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 139 */         while (i < this.validVertexCount) {
/* 140 */           for (byte b = 0; b < 4; b++) {
/* 141 */             arrayOfInt[b] = i;
/* 142 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 144 */           if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/* 145 */             if (paramInt1 == 0) {
/* 146 */               return true;
/*     */             }
/* 148 */             if (arrayOfDouble[0] < d1) {
/* 149 */               d1 = arrayOfDouble[0];
/* 150 */               d2 = paramPoint3d.x;
/* 151 */               d3 = paramPoint3d.y;
/* 152 */               d4 = paramPoint3d.z;
/* 153 */               if ((paramInt1 & 0x20) != 0) {
/* 154 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 158 */             if ((paramInt1 & 0x40) != 0) {
/* 159 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 8:
/* 167 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 170 */         while (i < this.validVertexCount) {
/* 171 */           for (byte b = 0; b < 4; b++) {
/* 172 */             arrayOfInt[b] = i;
/* 173 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 175 */           if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/* 176 */             if (paramInt1 == 0) {
/* 177 */               return true;
/*     */             }
/* 179 */             if (arrayOfDouble[0] < d1) {
/* 180 */               d1 = arrayOfDouble[0];
/* 181 */               d2 = paramPoint3d.x;
/* 182 */               d3 = paramPoint3d.y;
/* 183 */               d4 = paramPoint3d.z;
/* 184 */               if ((paramInt1 & 0x20) != 0) {
/* 185 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 189 */             if ((paramInt1 & 0x40) != 0) {
/* 190 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 197 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 199 */         while (i < this.validVertexCount) {
/* 200 */           for (byte b = 0; b < 4; b++) {
/* 201 */             arrayOfInt[b] = i;
/* 202 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 204 */           if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 205 */             if (paramInt1 == 0) {
/* 206 */               return true;
/*     */             }
/* 208 */             if (arrayOfDouble[0] < d1) {
/* 209 */               d1 = arrayOfDouble[0];
/* 210 */               d2 = paramPoint3d.x;
/* 211 */               d3 = paramPoint3d.y;
/* 212 */               d4 = paramPoint3d.z;
/* 213 */               if ((paramInt1 & 0x20) != 0) {
/* 214 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 218 */             if ((paramInt1 & 0x40) != 0) {
/* 219 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 226 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 228 */         while (i < this.validVertexCount) {
/* 229 */           for (byte b = 0; b < 4; b++) {
/* 230 */             arrayOfInt[b] = i;
/* 231 */             getVertexData(i++, arrayOfPoint3d[b]);
/*     */           } 
/* 233 */           if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 234 */             if (paramInt1 == 0) {
/* 235 */               return true;
/*     */             }
/* 237 */             if (arrayOfDouble[0] < d1) {
/* 238 */               d1 = arrayOfDouble[0];
/* 239 */               d2 = paramPoint3d.x;
/* 240 */               d3 = paramPoint3d.y;
/* 241 */               d4 = paramPoint3d.z;
/* 242 */               if ((paramInt1 & 0x20) != 0) {
/* 243 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 247 */             if ((paramInt1 & 0x40) != 0) {
/* 248 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 256 */         throw new IllegalArgumentException(J3dI18N.getString("QuadArrayRetained0"));
/*     */       default:
/* 258 */         throw new RuntimeException("PickShape not supported for intersection ");
/*     */     } 
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
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 273 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/* 274 */     double[] arrayOfDouble = new double[1];
/* 275 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 278 */     arrayOfPoint3d[0] = new Point3d();
/* 279 */     arrayOfPoint3d[1] = new Point3d();
/* 280 */     arrayOfPoint3d[2] = new Point3d();
/* 281 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/* 283 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 285 */         while (i < this.validVertexCount) {
/* 286 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 287 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 288 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 289 */           getVertexData(i++, arrayOfPoint3d[3]);
/* 290 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */           {
/*     */ 
/*     */             
/* 294 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 300 */         while (i < this.validVertexCount) {
/* 301 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 302 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 303 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 304 */           getVertexData(i++, arrayOfPoint3d[3]);
/* 305 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 313 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 318 */         while (i < this.validVertexCount) {
/* 319 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 320 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 321 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 322 */           getVertexData(i++, arrayOfPoint3d[3]);
/* 323 */           if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */           {
/* 325 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 330 */         while (i < this.validVertexCount) {
/* 331 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 332 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 333 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 334 */           getVertexData(i++, arrayOfPoint3d[3]);
/* 335 */           if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]) || intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0]))
/*     */           {
/*     */ 
/*     */             
/* 339 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 344 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 350 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/* 351 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 354 */     arrayOfPoint3d[0] = new Point3d();
/* 355 */     arrayOfPoint3d[1] = new Point3d();
/* 356 */     arrayOfPoint3d[2] = new Point3d();
/* 357 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/* 359 */     while (i < this.validVertexCount) {
/* 360 */       getVertexData(i++, arrayOfPoint3d[0]);
/* 361 */       getVertexData(i++, arrayOfPoint3d[1]);
/* 362 */       getVertexData(i++, arrayOfPoint3d[2]);
/* 363 */       getVertexData(i++, arrayOfPoint3d[3]);
/* 364 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 365 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 366 */       paramTransform3D.transform(arrayOfPoint3d[2]);
/* 367 */       paramTransform3D.transform(arrayOfPoint3d[3]);
/* 368 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 369 */         return true;
/*     */       }
/*     */     } 
/* 372 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 377 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/* 378 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 381 */     arrayOfPoint3d[0] = new Point3d();
/* 382 */     arrayOfPoint3d[1] = new Point3d();
/* 383 */     arrayOfPoint3d[2] = new Point3d();
/* 384 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/* 386 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 388 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 390 */         while (i < this.validVertexCount) {
/* 391 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 392 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 393 */           getVertexData(i++, arrayOfPoint3d[2]);
/* 394 */           getVertexData(i++, arrayOfPoint3d[3]);
/* 395 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 396 */             return true;
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
/* 431 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (i < this.validVertexCount) { getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); getVertexData(i++, arrayOfPoint3d[2]); getVertexData(i++, arrayOfPoint3d[3]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (i < this.validVertexCount) { getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); getVertexData(i++, arrayOfPoint3d[2]); getVertexData(i++, arrayOfPoint3d[3]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/*     */ 
/*     */   
/*     */   void computeCentroid() {
/* 438 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 441 */     Point3d point3d1 = new Point3d();
/* 442 */     Point3d point3d2 = new Point3d();
/* 443 */     Point3d point3d3 = new Point3d();
/* 444 */     Point3d point3d4 = new Point3d();
/* 445 */     Vector3d vector3d1 = new Vector3d();
/* 446 */     Vector3d vector3d2 = new Vector3d();
/* 447 */     Vector3d vector3d3 = new Vector3d();
/*     */ 
/*     */     
/* 450 */     double d = 0.0D;
/*     */     
/* 452 */     this.centroid.x = 0.0D;
/* 453 */     this.centroid.y = 0.0D;
/* 454 */     this.centroid.z = 0.0D;
/*     */     
/* 456 */     while (i < this.validVertexCount) {
/* 457 */       getVertexData(i++, point3d1);
/* 458 */       getVertexData(i++, point3d2);
/* 459 */       getVertexData(i++, point3d3);
/* 460 */       getVertexData(i++, point3d4);
/*     */ 
/*     */       
/* 463 */       vector3d3.sub(point3d1, point3d2);
/* 464 */       vector3d1.sub(point3d2, point3d3);
/*     */ 
/*     */       
/* 467 */       vector3d2.cross(vector3d3, vector3d1);
/* 468 */       vector3d2.normalize();
/*     */       
/* 470 */       if (Double.isNaN(vector3d2.x + vector3d2.y + vector3d2.z))
/*     */         continue; 
/* 472 */       vector3d3.set(0.0D, 0.0D, 0.0D);
/*     */       
/* 474 */       getCrossValue(point3d1, point3d2, vector3d3);
/* 475 */       getCrossValue(point3d2, point3d3, vector3d3);
/* 476 */       getCrossValue(point3d3, point3d1, vector3d3);
/* 477 */       double d1 = vector3d2.dot(vector3d3);
/* 478 */       d += d1;
/* 479 */       this.centroid.x += (point3d1.x + point3d2.x + point3d3.x) * d1;
/* 480 */       this.centroid.y += (point3d1.y + point3d2.y + point3d3.y) * d1;
/* 481 */       this.centroid.z += (point3d1.z + point3d2.z + point3d3.z) * d1;
/*     */ 
/*     */       
/* 484 */       vector3d3.set(0.0D, 0.0D, 0.0D);
/* 485 */       getCrossValue(point3d1, point3d3, vector3d3);
/* 486 */       getCrossValue(point3d3, point3d4, vector3d3);
/* 487 */       getCrossValue(point3d4, point3d1, vector3d3);
/* 488 */       d1 = vector3d2.dot(vector3d3);
/* 489 */       d += d1;
/* 490 */       this.centroid.x += (point3d4.x + point3d1.x + point3d3.x) * d1;
/* 491 */       this.centroid.y += (point3d4.y + point3d1.y + point3d3.y) * d1;
/* 492 */       this.centroid.z += (point3d4.z + point3d1.z + point3d3.z) * d1;
/*     */     } 
/* 494 */     if (d != 0.0D) {
/* 495 */       double d1 = 1.0D / 3.0D * d;
/* 496 */       this.centroid.x *= d1;
/* 497 */       this.centroid.y *= d1;
/* 498 */       this.centroid.z *= d1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 503 */   int getClassType() { return 4; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\QuadArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */