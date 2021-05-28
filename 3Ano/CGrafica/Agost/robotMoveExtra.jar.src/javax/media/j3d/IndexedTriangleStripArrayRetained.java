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
/*     */ class IndexedTriangleStripArrayRetained
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
/*  36 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*  37 */     double[] arrayOfDouble = new double[1];
/*  38 */     double d1 = Double.MAX_VALUE;
/*  39 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  40 */     byte b1 = 0;
/*  41 */     byte b2 = 0;
/*  42 */     arrayOfPoint3d[0] = new Point3d();
/*  43 */     arrayOfPoint3d[1] = new Point3d();
/*  44 */     arrayOfPoint3d[2] = new Point3d();
/*  45 */     int[] arrayOfInt = new int[3];
/*     */     
/*  47 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  49 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  51 */         while (b1 < this.stripIndexCounts.length) {
/*  52 */           for (byte b4 = 0; b4 < 2; b4++) {
/*  53 */             arrayOfInt[b4] = this.indexCoord[b2];
/*  54 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/*  56 */           int i = this.stripIndexCounts[b1++];
/*  57 */           for (byte b3 = 2; b3 < i; b3++) {
/*  58 */             arrayOfInt[2] = this.indexCoord[b2];
/*  59 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/*  60 */             if (intersectRay(arrayOfPoint3d, pickRay, arrayOfDouble, paramPoint3d)) {
/*  61 */               if (paramInt1 == 0) {
/*  62 */                 return true;
/*     */               }
/*  64 */               if (arrayOfDouble[0] < d1) {
/*  65 */                 d1 = arrayOfDouble[0];
/*  66 */                 d2 = paramPoint3d.x;
/*  67 */                 d3 = paramPoint3d.y;
/*  68 */                 d4 = paramPoint3d.z;
/*  69 */                 if ((paramInt1 & 0x20) != 0) {
/*  70 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/*  74 */               if ((paramInt1 & 0x40) != 0) {
/*  75 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  79 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*  80 */             arrayOfInt[0] = arrayOfInt[1];
/*  81 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*  82 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  87 */         pickSegment = (PickSegment)paramPickShape;
/*     */         
/*  89 */         while (b1 < this.stripIndexCounts.length) {
/*  90 */           for (byte b4 = 0; b4 < 2; b4++) {
/*  91 */             arrayOfInt[b4] = this.indexCoord[b2];
/*  92 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/*  94 */           int i = this.stripIndexCounts[b1++];
/*  95 */           for (byte b3 = 2; b3 < i; b3++) {
/*  96 */             arrayOfInt[2] = this.indexCoord[b2];
/*  97 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/*  98 */             if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 100 */               if (paramInt1 == 0) {
/* 101 */                 return true;
/*     */               }
/* 103 */               if (arrayOfDouble[0] < d1) {
/* 104 */                 d1 = arrayOfDouble[0];
/* 105 */                 d2 = paramPoint3d.x;
/* 106 */                 d3 = paramPoint3d.y;
/* 107 */                 d4 = paramPoint3d.z;
/* 108 */                 if ((paramInt1 & 0x20) != 0) {
/* 109 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 113 */               if ((paramInt1 & 0x40) != 0) {
/* 114 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 118 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 119 */             arrayOfInt[0] = arrayOfInt[1];
/* 120 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 121 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 6:
/* 126 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 129 */         while (b1 < this.stripIndexCounts.length) {
/* 130 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 131 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 132 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 134 */           int i = this.stripIndexCounts[b1++];
/* 135 */           for (byte b3 = 2; b3 < i; b3++) {
/* 136 */             arrayOfInt[2] = this.indexCoord[b2];
/* 137 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 138 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 139 */               if (paramInt1 == 0) {
/* 140 */                 return true;
/*     */               }
/* 142 */               if (arrayOfDouble[0] < d1) {
/* 143 */                 d1 = arrayOfDouble[0];
/* 144 */                 d2 = paramPoint3d.x;
/* 145 */                 d3 = paramPoint3d.y;
/* 146 */                 d4 = paramPoint3d.z;
/* 147 */                 if ((paramInt1 & 0x20) != 0) {
/* 148 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 152 */               if ((paramInt1 & 0x40) != 0) {
/* 153 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 157 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 158 */             arrayOfInt[0] = arrayOfInt[1];
/* 159 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 160 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 7:
/* 165 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 168 */         while (b1 < this.stripIndexCounts.length) {
/* 169 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 170 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 171 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 173 */           int i = this.stripIndexCounts[b1++];
/* 174 */           for (byte b3 = 2; b3 < i; b3++) {
/* 175 */             arrayOfInt[2] = this.indexCoord[b2];
/* 176 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 177 */             if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 179 */               if (paramInt1 == 0) {
/* 180 */                 return true;
/*     */               }
/* 182 */               if (arrayOfDouble[0] < d1) {
/* 183 */                 d1 = arrayOfDouble[0];
/* 184 */                 d2 = paramPoint3d.x;
/* 185 */                 d3 = paramPoint3d.y;
/* 186 */                 d4 = paramPoint3d.z;
/* 187 */                 if ((paramInt1 & 0x20) != 0) {
/* 188 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 192 */               if ((paramInt1 & 0x40) != 0) {
/* 193 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 197 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 198 */             arrayOfInt[0] = arrayOfInt[1];
/* 199 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 200 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 8:
/* 205 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 208 */         while (b1 < this.stripIndexCounts.length) {
/* 209 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 210 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 211 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 213 */           int i = this.stripIndexCounts[b1++];
/* 214 */           for (byte b3 = 2; b3 < i; b3++) {
/* 215 */             arrayOfInt[2] = this.indexCoord[b2];
/* 216 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 217 */             if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 219 */               if (paramInt1 == 0) {
/* 220 */                 return true;
/*     */               }
/* 222 */               if (arrayOfDouble[0] < d1) {
/* 223 */                 d1 = arrayOfDouble[0];
/* 224 */                 d2 = paramPoint3d.x;
/* 225 */                 d3 = paramPoint3d.y;
/* 226 */                 d4 = paramPoint3d.z;
/* 227 */                 if ((paramInt1 & 0x20) != 0) {
/* 228 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 232 */               if ((paramInt1 & 0x40) != 0) {
/* 233 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 237 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 238 */             arrayOfInt[0] = arrayOfInt[1];
/* 239 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 240 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 245 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 247 */         while (b1 < this.stripIndexCounts.length) {
/* 248 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 249 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 250 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 252 */           int i = this.stripIndexCounts[b1++];
/* 253 */           for (byte b3 = 2; b3 < i; b3++) {
/* 254 */             arrayOfInt[2] = this.indexCoord[b2];
/* 255 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 256 */             if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 257 */               if (paramInt1 == 0) {
/* 258 */                 return true;
/*     */               }
/* 260 */               if (arrayOfDouble[0] < d1) {
/* 261 */                 d1 = arrayOfDouble[0];
/* 262 */                 d2 = paramPoint3d.x;
/* 263 */                 d3 = paramPoint3d.y;
/* 264 */                 d4 = paramPoint3d.z;
/* 265 */                 if ((paramInt1 & 0x20) != 0) {
/* 266 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 270 */               if ((paramInt1 & 0x40) != 0) {
/* 271 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 275 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 276 */             arrayOfInt[0] = arrayOfInt[1];
/* 277 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 278 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 283 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 285 */         while (b1 < this.stripIndexCounts.length) {
/* 286 */           for (byte b4 = 0; b4 < 2; b4++) {
/* 287 */             arrayOfInt[b4] = this.indexCoord[b2];
/* 288 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[b4]);
/*     */           } 
/* 290 */           int i = this.stripIndexCounts[b1++];
/* 291 */           for (byte b3 = 2; b3 < i; b3++) {
/* 292 */             arrayOfInt[2] = this.indexCoord[b2];
/* 293 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 294 */             if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 295 */               if (paramInt1 == 0) {
/* 296 */                 return true;
/*     */               }
/* 298 */               if (arrayOfDouble[0] < d1) {
/* 299 */                 d1 = arrayOfDouble[0];
/* 300 */                 d2 = paramPoint3d.x;
/* 301 */                 d3 = paramPoint3d.y;
/* 302 */                 d4 = paramPoint3d.z;
/* 303 */                 if ((paramInt1 & 0x20) != 0) {
/* 304 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 308 */               if ((paramInt1 & 0x40) != 0) {
/* 309 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 313 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 314 */             arrayOfInt[0] = arrayOfInt[1];
/* 315 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 316 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 322 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArrayRetained0"));
/*     */       default:
/* 324 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 327 */     if (d1 < Double.MAX_VALUE) {
/* 328 */       paramPoint3d.x = d2;
/* 329 */       paramPoint3d.y = d3;
/* 330 */       paramPoint3d.z = d4;
/* 331 */       return true;
/*     */     } 
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 339 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 340 */     double[] arrayOfDouble = new double[1];
/* 341 */     byte b1 = 0, b2 = 0;
/*     */     
/* 343 */     arrayOfPoint3d[0] = new Point3d();
/* 344 */     arrayOfPoint3d[1] = new Point3d();
/* 345 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 347 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 349 */         while (b1 < this.stripIndexCounts.length) {
/* 350 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 351 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 352 */           int i = this.stripIndexCounts[b1++];
/* 353 */           for (byte b = 2; b < i; b++) {
/* 354 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 355 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */             {
/* 357 */               return true;
/*     */             }
/* 359 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 360 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 365 */         while (b1 < this.stripIndexCounts.length) {
/* 366 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 367 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 368 */           int i = this.stripIndexCounts[b1++];
/* 369 */           for (byte b = 2; b < i; b++) {
/* 370 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 371 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */             {
/*     */ 
/*     */               
/* 375 */               return true;
/*     */             }
/* 377 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 378 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 383 */         while (b1 < this.stripIndexCounts.length) {
/* 384 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 385 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 386 */           int i = this.stripIndexCounts[b1++];
/* 387 */           for (byte b = 2; b < i; b++) {
/* 388 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 389 */             if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */             {
/* 391 */               return true;
/*     */             }
/* 393 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 394 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 399 */         while (b1 < this.stripIndexCounts.length) {
/* 400 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 401 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 402 */           int i = this.stripIndexCounts[b1++];
/* 403 */           for (byte b = 2; b < i; b++) {
/* 404 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 405 */             if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]))
/*     */             {
/* 407 */               return true;
/*     */             }
/* 409 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 410 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/* 415 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 419 */     byte b1 = 0, b2 = 0;
/* 420 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 421 */     arrayOfPoint3d[0] = new Point3d();
/* 422 */     arrayOfPoint3d[1] = new Point3d();
/* 423 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 425 */     while (b1 < this.stripIndexCounts.length) {
/* 426 */       getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 427 */       getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 428 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 429 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 430 */       int i = this.stripIndexCounts[b1++];
/* 431 */       for (byte b = 2; b < i; b++) {
/* 432 */         getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 433 */         paramTransform3D.transform(arrayOfPoint3d[2]);
/* 434 */         if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 435 */           return true;
/*     */         }
/* 437 */         arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 438 */         arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */       } 
/*     */     } 
/* 441 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 446 */     byte b1 = 0;
/* 447 */     byte b2 = 0;
/* 448 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 449 */     arrayOfPoint3d[0] = new Point3d();
/* 450 */     arrayOfPoint3d[1] = new Point3d();
/* 451 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 453 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 455 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 457 */         while (b1 < this.stripIndexCounts.length) {
/* 458 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 459 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 460 */           int i = this.stripIndexCounts[b1++];
/* 461 */           for (byte b = 2; b < i; b++) {
/* 462 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]);
/* 463 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 464 */               return true;
/*     */             }
/* 466 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 467 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
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
/* 509 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (b1 < this.stripIndexCounts.length) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]); int i = this.stripIndexCounts[b1++]; for (byte b = 2; b < i; b++) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (b1 < this.stripIndexCounts.length) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]); int i = this.stripIndexCounts[b1++]; for (byte b = 2; b < i; b++) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[2]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/* 513 */   int getClassType() { return 3; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\IndexedTriangleStripArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */