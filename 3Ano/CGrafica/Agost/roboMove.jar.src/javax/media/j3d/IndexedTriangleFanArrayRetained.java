/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class IndexedTriangleFanArrayRetained
/*     */   extends IndexedGeometryStripArrayRetained
/*     */ {
/*     */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) {
/*     */     PickCone pickCone;
/*     */     PickCylinder pickCylinder;
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/*     */     PickSegment pickSegment;
/*     */     PickRay pickRay;
/*  37 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*  38 */     double[] arrayOfDouble = new double[1];
/*  39 */     double d1 = Double.MAX_VALUE;
/*  40 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  41 */     byte b1 = 0;
/*  42 */     byte b2 = 0;
/*  43 */     arrayOfPoint3d[0] = new Point3d();
/*  44 */     arrayOfPoint3d[1] = new Point3d();
/*  45 */     arrayOfPoint3d[2] = new Point3d();
/*  46 */     int[] arrayOfInt = new int[3];
/*     */     
/*  48 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  50 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  52 */         while (b1 < this.stripIndexCounts.length) {
/*  53 */           for (byte b4 = 0; b4 < 2; b4++) {
/*  54 */             arrayOfInt[b4] = this.indexCoord[b2];
/*  55 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/*  57 */           int i = this.stripIndexCounts[b1++];
/*  58 */           for (byte b3 = 2; b3 < i; b3++) {
/*  59 */             arrayOfInt[2] = this.indexCoord[b2];
/*  60 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/*  61 */             if (intersectRay(arrayOfPoint3d, pickRay, arrayOfDouble, paramPoint3d)) {
/*  62 */               if (paramInt1 == 0) {
/*  63 */                 return true;
/*     */               }
/*  65 */               if (arrayOfDouble[0] < d1) {
/*  66 */                 d1 = arrayOfDouble[0];
/*  67 */                 d2 = paramPoint3d.x;
/*  68 */                 d3 = paramPoint3d.y;
/*  69 */                 d4 = paramPoint3d.z;
/*  70 */                 if ((paramInt1 & 0x20) != 0) {
/*  71 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/*  75 */               if ((paramInt1 & 0x40) != 0) {
/*  76 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  80 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*  81 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  86 */         pickSegment = (PickSegment)paramPickShape;
/*     */         
/*  88 */         while (b1 < this.stripIndexCounts.length) {
/*  89 */           for (byte b4 = 0; b4 < 2; b4++) {
/*  90 */             arrayOfInt[b4] = this.indexCoord[b2];
/*  91 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/*  93 */           int i = this.stripIndexCounts[b1++];
/*  94 */           for (byte b3 = 2; b3 < i; b3++) {
/*  95 */             arrayOfInt[2] = this.indexCoord[b2];
/*  96 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/*  97 */             if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
/*     */               
/*  99 */               if (paramInt1 == 0) {
/* 100 */                 return true;
/*     */               }
/* 102 */               if (arrayOfDouble[0] < d1) {
/* 103 */                 d1 = arrayOfDouble[0];
/* 104 */                 d2 = paramPoint3d.x;
/* 105 */                 d3 = paramPoint3d.y;
/* 106 */                 d4 = paramPoint3d.z;
/* 107 */                 if ((paramInt1 & 0x20) != 0) {
/* 108 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 112 */               if ((paramInt1 & 0x40) != 0) {
/* 113 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 117 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 118 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 6:
/* 123 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 126 */         while (b1 < this.stripIndexCounts.length) {
/* 127 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 128 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 129 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 131 */           int i = this.stripIndexCounts[b1++];
/* 132 */           for (byte b3 = 2; b3 < i; b3++) {
/* 133 */             arrayOfInt[2] = this.indexCoord[b2];
/* 134 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 135 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 136 */               if (paramInt1 == 0) {
/* 137 */                 return true;
/*     */               }
/* 139 */               if (arrayOfDouble[0] < d1) {
/* 140 */                 d1 = arrayOfDouble[0];
/* 141 */                 d2 = paramPoint3d.x;
/* 142 */                 d3 = paramPoint3d.y;
/* 143 */                 d4 = paramPoint3d.z;
/* 144 */                 if ((paramInt1 & 0x20) != 0) {
/* 145 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 149 */               if ((paramInt1 & 0x40) != 0) {
/* 150 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 154 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 155 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 7:
/* 160 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 163 */         while (b1 < this.stripIndexCounts.length) {
/* 164 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 165 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 166 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 168 */           int i = this.stripIndexCounts[b1++];
/* 169 */           for (byte b3 = 2; b3 < i; b3++) {
/* 170 */             arrayOfInt[2] = this.indexCoord[b2];
/* 171 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 172 */             if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 174 */               if (paramInt1 == 0) {
/* 175 */                 return true;
/*     */               }
/* 177 */               if (arrayOfDouble[0] < d1) {
/* 178 */                 d1 = arrayOfDouble[0];
/* 179 */                 d2 = paramPoint3d.x;
/* 180 */                 d3 = paramPoint3d.y;
/* 181 */                 d4 = paramPoint3d.z;
/* 182 */                 if ((paramInt1 & 0x20) != 0) {
/* 183 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 187 */               if ((paramInt1 & 0x40) != 0) {
/* 188 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 192 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 193 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 8:
/* 198 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 201 */         while (b1 < this.stripIndexCounts.length) {
/* 202 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 203 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 204 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 206 */           int i = this.stripIndexCounts[b1++];
/* 207 */           for (byte b3 = 2; b3 < i; b3++) {
/* 208 */             arrayOfInt[2] = this.indexCoord[b2];
/* 209 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 210 */             if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 212 */               if (paramInt1 == 0) {
/* 213 */                 return true;
/*     */               }
/* 215 */               if (arrayOfDouble[0] < d1) {
/* 216 */                 d1 = arrayOfDouble[0];
/* 217 */                 d2 = paramPoint3d.x;
/* 218 */                 d3 = paramPoint3d.y;
/* 219 */                 d4 = paramPoint3d.z;
/* 220 */                 if ((paramInt1 & 0x20) != 0) {
/* 221 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 225 */               if ((paramInt1 & 0x40) != 0) {
/* 226 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 230 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 231 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 236 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 238 */         while (b1 < this.stripIndexCounts.length) {
/* 239 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 240 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 241 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 243 */           int i = this.stripIndexCounts[b1++];
/* 244 */           for (byte b3 = 2; b3 < i; b3++) {
/* 245 */             arrayOfInt[2] = this.indexCoord[b2];
/* 246 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 247 */             if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 248 */               if (paramInt1 == 0) {
/* 249 */                 return true;
/*     */               }
/* 251 */               if (arrayOfDouble[0] < d1) {
/* 252 */                 d1 = arrayOfDouble[0];
/* 253 */                 d2 = paramPoint3d.x;
/* 254 */                 d3 = paramPoint3d.y;
/* 255 */                 d4 = paramPoint3d.z;
/* 256 */                 if ((paramInt1 & 0x20) != 0) {
/* 257 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 261 */               if ((paramInt1 & 0x40) != 0) {
/* 262 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 266 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 267 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 272 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 274 */         while (b1 < this.stripIndexCounts.length) {
/* 275 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 276 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 277 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 279 */           int i = this.stripIndexCounts[b1++];
/* 280 */           for (byte b3 = 2; b3 < i; b3++) {
/* 281 */             arrayOfInt[2] = this.indexCoord[b2];
/* 282 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 283 */             if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 284 */               if (paramInt1 == 0) {
/* 285 */                 return true;
/*     */               }
/* 287 */               if (arrayOfDouble[0] < d1) {
/* 288 */                 d1 = arrayOfDouble[0];
/* 289 */                 d2 = paramPoint3d.x;
/* 290 */                 d3 = paramPoint3d.y;
/* 291 */                 d4 = paramPoint3d.z;
/* 292 */                 if ((paramInt1 & 0x20) != 0) {
/* 293 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 297 */               if ((paramInt1 & 0x40) != 0) {
/* 298 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 302 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 303 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 309 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArrayRetained0"));
/*     */       default:
/* 311 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 314 */     if (d1 < Double.MAX_VALUE) {
/* 315 */       paramPoint3d.x = d2;
/* 316 */       paramPoint3d.y = d3;
/* 317 */       paramPoint3d.z = d4;
/* 318 */       return true;
/*     */     } 
/* 320 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 326 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 327 */     double[] arrayOfDouble = new double[1];
/* 328 */     byte b1 = 0, b2 = 0;
/*     */     
/* 330 */     arrayOfPoint3d[0] = new Point3d();
/* 331 */     arrayOfPoint3d[1] = new Point3d();
/* 332 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 334 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 336 */         while (b1 < this.stripIndexCounts.length) {
/* 337 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 338 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 339 */           int i = this.stripIndexCounts[b1++];
/* 340 */           for (byte b = 2; b < i; b++) {
/* 341 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 342 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */             {
/* 344 */               return true;
/*     */             }
/* 346 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 351 */         while (b1 < this.stripIndexCounts.length) {
/* 352 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 353 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 354 */           int i = this.stripIndexCounts[b1++];
/* 355 */           for (byte b = 2; b < i; b++) {
/* 356 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 357 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */             {
/*     */ 
/*     */               
/* 361 */               return true;
/*     */             }
/* 363 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 368 */         while (b1 < this.stripIndexCounts.length) {
/* 369 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 370 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 371 */           int i = this.stripIndexCounts[b1++];
/* 372 */           for (byte b = 2; b < i; b++) {
/* 373 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 374 */             if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */             {
/* 376 */               return true;
/*     */             }
/* 378 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 383 */         while (b1 < this.stripIndexCounts.length) {
/* 384 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 385 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 386 */           int i = this.stripIndexCounts[b1++];
/* 387 */           for (byte b = 2; b < i; b++) {
/* 388 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 389 */             if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]))
/*     */             {
/* 391 */               return true;
/*     */             }
/* 393 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/* 398 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 402 */     byte b1 = 0, b2 = 0;
/* 403 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 404 */     arrayOfPoint3d[0] = new Point3d();
/* 405 */     arrayOfPoint3d[1] = new Point3d();
/* 406 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 408 */     while (b1 < this.stripIndexCounts.length) {
/* 409 */       getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 410 */       getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 411 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 412 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 413 */       int i = this.stripIndexCounts[b1++];
/* 414 */       for (byte b = 2; b < i; b++) {
/* 415 */         getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 416 */         paramTransform3D.transform(arrayOfPoint3d[2]);
/* 417 */         if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 418 */           return true;
/*     */         }
/* 420 */         arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */       } 
/*     */     } 
/* 423 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 428 */     byte b1 = 0;
/* 429 */     byte b2 = 0;
/* 430 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 431 */     arrayOfPoint3d[0] = new Point3d();
/* 432 */     arrayOfPoint3d[1] = new Point3d();
/* 433 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 435 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 437 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 439 */         while (b1 < this.stripIndexCounts.length) {
/* 440 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 441 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 442 */           int i = this.stripIndexCounts[b1++];
/* 443 */           for (byte b = 2; b < i; b++) {
/* 444 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 445 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 446 */               return true;
/*     */             }
/* 448 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
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
/* 488 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (b1 < this.stripIndexCounts.length) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]); int i = this.stripIndexCounts[b1++]; for (byte b = 2; b < i; b++) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (b1 < this.stripIndexCounts.length) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]); int i = this.stripIndexCounts[b1++]; for (byte b = 2; b < i; b++) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/* 492 */   int getClassType() { return 3; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\IndexedTriangleFanArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */