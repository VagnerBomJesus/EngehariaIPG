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
/*     */ 
/*     */ class LineStripArrayRetained
/*     */   extends GeometryStripArrayRetained
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
/*  35 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*  36 */     double[] arrayOfDouble = new double[1];
/*  37 */     double d1 = Double.MAX_VALUE;
/*  38 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*     */     
/*  40 */     byte b = 0;
/*  41 */     arrayOfPoint3d[0] = new Point3d();
/*  42 */     arrayOfPoint3d[1] = new Point3d();
/*  43 */     int[] arrayOfInt = new int[2];
/*     */     
/*  45 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  47 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  49 */         while (b < this.stripVertexCounts.length) {
/*  50 */           int i = this.stripStartVertexIndices[b];
/*  51 */           int j = i + this.stripVertexCounts[b++];
/*  52 */           arrayOfInt[0] = i;
/*  53 */           getVertexData(i++, arrayOfPoint3d[0]);
/*  54 */           while (i < j) {
/*  55 */             arrayOfInt[1] = i;
/*  56 */             getVertexData(i++, arrayOfPoint3d[1]);
/*  57 */             if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickRay.origin, pickRay.direction, arrayOfDouble, paramPoint3d)) {
/*     */ 
/*     */               
/*  60 */               if (paramInt1 == 0) {
/*  61 */                 return true;
/*     */               }
/*  63 */               if (arrayOfDouble[0] < d1) {
/*  64 */                 d1 = arrayOfDouble[0];
/*  65 */                 d2 = paramPoint3d.x;
/*  66 */                 d3 = paramPoint3d.y;
/*  67 */                 d4 = paramPoint3d.z;
/*  68 */                 if ((paramInt1 & 0x20) != 0) {
/*  69 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/*  73 */               if ((paramInt1 & 0x40) != 0) {
/*  74 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  78 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*  79 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  84 */         pickSegment = (PickSegment)paramPickShape;
/*  85 */         vector3d = new Vector3d(pickSegment.end.x - pickSegment.start.x, pickSegment.end.y - pickSegment.start.y, pickSegment.end.z - pickSegment.start.z);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  90 */         while (b < this.stripVertexCounts.length) {
/*  91 */           int i = this.stripStartVertexIndices[b];
/*  92 */           int j = i + this.stripVertexCounts[b++];
/*  93 */           arrayOfInt[0] = i;
/*  94 */           getVertexData(i++, arrayOfPoint3d[0]);
/*  95 */           while (i < j) {
/*  96 */             arrayOfInt[1] = i;
/*  97 */             getVertexData(i++, arrayOfPoint3d[1]);
/*  98 */             if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickSegment.start, vector3d, arrayOfDouble, paramPoint3d) && arrayOfDouble[0] <= 1.0D) {
/*     */ 
/*     */ 
/*     */               
/* 102 */               if (paramInt1 == 0) {
/* 103 */                 return true;
/*     */               }
/* 105 */               if (arrayOfDouble[0] < d1) {
/* 106 */                 d1 = arrayOfDouble[0];
/* 107 */                 d2 = paramPoint3d.x;
/* 108 */                 d3 = paramPoint3d.y;
/* 109 */                 d4 = paramPoint3d.z;
/* 110 */                 if ((paramInt1 & 0x20) != 0) {
/* 111 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 115 */               if ((paramInt1 & 0x40) != 0) {
/* 116 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 120 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 121 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 6:
/* 126 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 129 */         while (b < this.stripVertexCounts.length) {
/* 130 */           int i = this.stripStartVertexIndices[b];
/* 131 */           int j = i + this.stripVertexCounts[b++];
/* 132 */           arrayOfInt[0] = i;
/* 133 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 134 */           while (i < j) {
/* 135 */             arrayOfInt[1] = i;
/* 136 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 137 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 138 */               if (paramInt1 == 0) {
/* 139 */                 return true;
/*     */               }
/* 141 */               if (arrayOfDouble[0] < d1) {
/* 142 */                 d1 = arrayOfDouble[0];
/* 143 */                 d2 = paramPoint3d.x;
/* 144 */                 d3 = paramPoint3d.y;
/* 145 */                 d4 = paramPoint3d.z;
/* 146 */                 if ((paramInt1 & 0x20) != 0) {
/* 147 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 151 */               if ((paramInt1 & 0x40) != 0) {
/* 152 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 156 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 157 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 7:
/* 163 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 166 */         while (b < this.stripVertexCounts.length) {
/* 167 */           int i = this.stripStartVertexIndices[b];
/* 168 */           int j = i + this.stripVertexCounts[b++];
/* 169 */           arrayOfInt[0] = i;
/* 170 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 171 */           while (i < j) {
/* 172 */             arrayOfInt[1] = i;
/* 173 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 174 */             if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/* 175 */               if (paramInt1 == 0) {
/* 176 */                 return true;
/*     */               }
/* 178 */               if (arrayOfDouble[0] < d1) {
/* 179 */                 d1 = arrayOfDouble[0];
/* 180 */                 d2 = paramPoint3d.x;
/* 181 */                 d3 = paramPoint3d.y;
/* 182 */                 d4 = paramPoint3d.z;
/* 183 */                 if ((paramInt1 & 0x20) != 0) {
/* 184 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 188 */               if ((paramInt1 & 0x40) != 0) {
/* 189 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 193 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 194 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 8:
/* 199 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 202 */         while (b < this.stripVertexCounts.length) {
/* 203 */           int i = this.stripStartVertexIndices[b];
/* 204 */           int j = i + this.stripVertexCounts[b++];
/* 205 */           arrayOfInt[0] = i;
/* 206 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 207 */           while (i < j) {
/* 208 */             arrayOfInt[1] = i;
/* 209 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 210 */             if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/* 211 */               if (paramInt1 == 0) {
/* 212 */                 return true;
/*     */               }
/* 214 */               if (arrayOfDouble[0] < d1) {
/* 215 */                 d1 = arrayOfDouble[0];
/* 216 */                 d2 = paramPoint3d.x;
/* 217 */                 d3 = paramPoint3d.y;
/* 218 */                 d4 = paramPoint3d.z;
/* 219 */                 if ((paramInt1 & 0x20) != 0) {
/* 220 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 224 */               if ((paramInt1 & 0x40) != 0) {
/* 225 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 229 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 230 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 235 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 237 */         while (b < this.stripVertexCounts.length) {
/* 238 */           int i = this.stripStartVertexIndices[b];
/* 239 */           int j = i + this.stripVertexCounts[b++];
/* 240 */           arrayOfInt[0] = i;
/* 241 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 242 */           while (i < j) {
/* 243 */             arrayOfInt[1] = i;
/* 244 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 245 */             if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 246 */               if (paramInt1 == 0) {
/* 247 */                 return true;
/*     */               }
/* 249 */               if (arrayOfDouble[0] < d1) {
/* 250 */                 d1 = arrayOfDouble[0];
/* 251 */                 d2 = paramPoint3d.x;
/* 252 */                 d3 = paramPoint3d.y;
/* 253 */                 d4 = paramPoint3d.z;
/* 254 */                 if ((paramInt1 & 0x20) != 0) {
/* 255 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 259 */               if ((paramInt1 & 0x40) != 0) {
/* 260 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 264 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 265 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 270 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 272 */         while (b < this.stripVertexCounts.length) {
/* 273 */           int i = this.stripStartVertexIndices[b];
/* 274 */           int j = i + this.stripVertexCounts[b++];
/* 275 */           arrayOfInt[0] = i;
/* 276 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 277 */           while (i < j) {
/* 278 */             arrayOfInt[1] = i;
/* 279 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 280 */             if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 281 */               if (paramInt1 == 0) {
/* 282 */                 return true;
/*     */               }
/* 284 */               if (arrayOfDouble[0] < d1) {
/* 285 */                 d1 = arrayOfDouble[0];
/* 286 */                 d2 = paramPoint3d.x;
/* 287 */                 d3 = paramPoint3d.y;
/* 288 */                 d4 = paramPoint3d.z;
/* 289 */                 if ((paramInt1 & 0x20) != 0) {
/* 290 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 294 */               if ((paramInt1 & 0x40) != 0) {
/* 295 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 299 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 300 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 306 */         throw new IllegalArgumentException(J3dI18N.getString("LineStripArrayRetained0"));
/*     */       default:
/* 308 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 311 */     if (d1 < Double.MAX_VALUE) {
/* 312 */       paramPoint3d.x = d2;
/* 313 */       paramPoint3d.y = d3;
/* 314 */       paramPoint3d.z = d4;
/* 315 */       return true;
/*     */     } 
/* 317 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/*     */     Vector3d vector3d;
/* 322 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 323 */     double[] arrayOfDouble = new double[1];
/*     */     
/* 325 */     byte b = 0;
/*     */     
/* 327 */     arrayOfPoint3d[0] = new Point3d();
/* 328 */     arrayOfPoint3d[1] = new Point3d();
/*     */ 
/*     */ 
/*     */     
/* 332 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/*     */       case 4:
/* 335 */         while (b < this.stripVertexCounts.length) {
/* 336 */           int i = this.stripStartVertexIndices[b];
/* 337 */           int j = i + this.stripVertexCounts[b++];
/* 338 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 339 */           while (i < j) {
/* 340 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 341 */             if (intersectSegment(paramArrayOfPoint3d, arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfDouble, null))
/*     */             {
/* 343 */               return true;
/*     */             }
/* 345 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 350 */         vector3d = new Vector3d();
/* 351 */         while (b < this.stripVertexCounts.length) {
/* 352 */           int i = this.stripStartVertexIndices[b];
/* 353 */           int j = i + this.stripVertexCounts[b++];
/* 354 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 355 */           while (i < j) {
/* 356 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 357 */             vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 358 */             vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 359 */             vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 360 */             if (intersectLineAndRay(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfPoint3d[0], vector3d, arrayOfDouble, null) && arrayOfDouble[0] <= 1.0D)
/*     */             {
/*     */               
/* 363 */               return true;
/*     */             }
/* 365 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 370 */         vector3d = new Vector3d();
/* 371 */         while (b < this.stripVertexCounts.length) {
/* 372 */           int i = this.stripStartVertexIndices[b];
/* 373 */           int j = i + this.stripVertexCounts[b++];
/* 374 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 375 */           while (i < j) {
/* 376 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 377 */             vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 378 */             vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 379 */             vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 380 */             if (intersectPntAndRay(paramArrayOfPoint3d[0], arrayOfPoint3d[0], vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D)
/*     */             {
/*     */               
/* 383 */               return true;
/*     */             }
/* 385 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/* 390 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 395 */     byte b = 0;
/*     */     
/* 397 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 398 */     arrayOfPoint3d[0] = new Point3d();
/* 399 */     arrayOfPoint3d[1] = new Point3d();
/*     */ 
/*     */     
/* 402 */     while (b < this.stripVertexCounts.length) {
/* 403 */       int i = this.stripStartVertexIndices[b];
/* 404 */       int j = i + this.stripVertexCounts[b++];
/* 405 */       getVertexData(i++, arrayOfPoint3d[0]);
/* 406 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 407 */       while (i < j) {
/* 408 */         getVertexData(i++, arrayOfPoint3d[1]);
/* 409 */         paramTransform3D.transform(arrayOfPoint3d[1]);
/* 410 */         if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 411 */           return true;
/*     */         }
/* 413 */         arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */       } 
/*     */     } 
/* 416 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 421 */     byte b = 0;
/*     */     
/* 423 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 424 */     arrayOfPoint3d[0] = new Point3d();
/* 425 */     arrayOfPoint3d[1] = new Point3d();
/*     */ 
/*     */ 
/*     */     
/* 429 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 431 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 433 */         while (b < this.stripVertexCounts.length) {
/* 434 */           int i = this.stripStartVertexIndices[b];
/* 435 */           int j = i + this.stripVertexCounts[b++];
/* 436 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 437 */           while (i < j) {
/* 438 */             getVertexData(i++, arrayOfPoint3d[1]);
/* 439 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 440 */               return true;
/*     */             }
/* 442 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 484 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (b < this.stripVertexCounts.length) { int i = this.stripStartVertexIndices[b]; int j = i + this.stripVertexCounts[b++]; getVertexData(i++, arrayOfPoint3d[0]); while (i < j) { getVertexData(i++, arrayOfPoint3d[1]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); }  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (b < this.stripVertexCounts.length) { int i = this.stripStartVertexIndices[b]; int j = i + this.stripVertexCounts[b++]; getVertexData(i++, arrayOfPoint3d[0]); while (i < j) { getVertexData(i++, arrayOfPoint3d[1]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); }  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/*     */   void computeCentroid() {
/* 489 */     byte b = 0;
/*     */ 
/*     */     
/* 492 */     double d = 0.0D;
/*     */ 
/*     */     
/* 495 */     Point3d point3d1 = new Point3d();
/* 496 */     Point3d point3d2 = new Point3d();
/*     */     
/* 498 */     this.centroid.x = 0.0D;
/* 499 */     this.centroid.y = 0.0D;
/* 500 */     this.centroid.z = 0.0D;
/*     */ 
/*     */ 
/*     */     
/* 504 */     while (b < this.stripVertexCounts.length) {
/* 505 */       int i = this.stripStartVertexIndices[b];
/* 506 */       int j = i + this.stripVertexCounts[b++];
/* 507 */       getVertexData(i++, point3d1);
/* 508 */       boolean bool = true;
/* 509 */       while (i < j) {
/* 510 */         if (bool) {
/* 511 */           getVertexData(i++, point3d2);
/* 512 */           bool = false;
/*     */         } else {
/* 514 */           getVertexData(i++, point3d1);
/* 515 */           bool = true;
/*     */         } 
/* 517 */         double d1 = point3d1.distance(point3d2);
/* 518 */         this.centroid.x += (point3d1.x + point3d2.x) * d1;
/* 519 */         this.centroid.y += (point3d1.y + point3d2.y) * d1;
/* 520 */         this.centroid.z += (point3d1.z + point3d2.z) * d1;
/* 521 */         d += d1;
/*     */       } 
/*     */     } 
/* 524 */     if (d != 0.0D) {
/* 525 */       double d1 = 1.0D / 2.0D * d;
/* 526 */       this.centroid.x *= d1;
/* 527 */       this.centroid.y *= d1;
/* 528 */       this.centroid.z *= d1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 533 */   int getClassType() { return 2; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\LineStripArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */