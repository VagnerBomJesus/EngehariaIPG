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
/*     */ 
/*     */ 
/*     */ class TriangleStripArrayRetained
/*     */   extends GeometryStripArrayRetained
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
/*  40 */     byte b = 0;
/*     */     
/*  42 */     arrayOfPoint3d[0] = new Point3d();
/*  43 */     arrayOfPoint3d[1] = new Point3d();
/*  44 */     arrayOfPoint3d[2] = new Point3d();
/*  45 */     int[] arrayOfInt = new int[3];
/*     */     
/*  47 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  49 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  51 */         while (b < this.stripVertexCounts.length) {
/*  52 */           int i = this.stripStartVertexIndices[b];
/*  53 */           int j = i + this.stripVertexCounts[b++];
/*  54 */           for (byte b1 = 0; b1 < 2; b1++) {
/*  55 */             arrayOfInt[b1] = i;
/*  56 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/*  58 */           while (i < j) {
/*  59 */             arrayOfInt[2] = i;
/*  60 */             getVertexData(i++, arrayOfPoint3d[2]);
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
/*  80 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*  81 */             arrayOfInt[0] = arrayOfInt[1];
/*  82 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*  83 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  88 */         pickSegment = (PickSegment)paramPickShape;
/*     */         
/*  90 */         while (b < this.stripVertexCounts.length) {
/*  91 */           int i = this.stripStartVertexIndices[b];
/*  92 */           int j = i + this.stripVertexCounts[b++];
/*  93 */           for (byte b1 = 0; b1 < 2; b1++) {
/*  94 */             arrayOfInt[b1] = i;
/*  95 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/*  97 */           while (i < j) {
/*  98 */             arrayOfInt[2] = i;
/*  99 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 100 */             if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
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
/* 122 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 123 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 6:
/* 128 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 131 */         while (b < this.stripVertexCounts.length) {
/* 132 */           int i = this.stripStartVertexIndices[b];
/* 133 */           int j = i + this.stripVertexCounts[b++];
/* 134 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 135 */             arrayOfInt[b1] = i;
/* 136 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 138 */           while (i < j) {
/* 139 */             arrayOfInt[2] = i;
/* 140 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 141 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 142 */               if (paramInt1 == 0) {
/* 143 */                 return true;
/*     */               }
/* 145 */               if (arrayOfDouble[0] < d1) {
/* 146 */                 d1 = arrayOfDouble[0];
/* 147 */                 d2 = paramPoint3d.x;
/* 148 */                 d3 = paramPoint3d.y;
/* 149 */                 d4 = paramPoint3d.z;
/* 150 */                 if ((paramInt1 & 0x20) != 0) {
/* 151 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 155 */               if ((paramInt1 & 0x40) != 0) {
/* 156 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 160 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 161 */             arrayOfInt[0] = arrayOfInt[1];
/* 162 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 163 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 7:
/* 168 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 171 */         while (b < this.stripVertexCounts.length) {
/* 172 */           int i = this.stripStartVertexIndices[b];
/* 173 */           int j = i + this.stripVertexCounts[b++];
/* 174 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 175 */             arrayOfInt[b1] = i;
/* 176 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 178 */           while (i < j) {
/* 179 */             arrayOfInt[2] = i;
/* 180 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 181 */             if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 183 */               if (paramInt1 == 0) {
/* 184 */                 return true;
/*     */               }
/* 186 */               if (arrayOfDouble[0] < d1) {
/* 187 */                 d1 = arrayOfDouble[0];
/* 188 */                 d2 = paramPoint3d.x;
/* 189 */                 d3 = paramPoint3d.y;
/* 190 */                 d4 = paramPoint3d.z;
/* 191 */                 if ((paramInt1 & 0x20) != 0) {
/* 192 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 196 */               if ((paramInt1 & 0x40) != 0) {
/* 197 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 201 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 202 */             arrayOfInt[0] = arrayOfInt[1];
/* 203 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 204 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 8:
/* 209 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 212 */         while (b < this.stripVertexCounts.length) {
/* 213 */           int i = this.stripStartVertexIndices[b];
/* 214 */           int j = i + this.stripVertexCounts[b++];
/* 215 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 216 */             arrayOfInt[b1] = i;
/* 217 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 219 */           while (i < j) {
/* 220 */             arrayOfInt[2] = i;
/* 221 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 222 */             if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 224 */               if (paramInt1 == 0) {
/* 225 */                 return true;
/*     */               }
/* 227 */               if (arrayOfDouble[0] < d1) {
/* 228 */                 d1 = arrayOfDouble[0];
/* 229 */                 d2 = paramPoint3d.x;
/* 230 */                 d3 = paramPoint3d.y;
/* 231 */                 d4 = paramPoint3d.z;
/* 232 */                 if ((paramInt1 & 0x20) != 0) {
/* 233 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 237 */               if ((paramInt1 & 0x40) != 0) {
/* 238 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 242 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 243 */             arrayOfInt[0] = arrayOfInt[1];
/* 244 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 245 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 250 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 252 */         while (b < this.stripVertexCounts.length) {
/* 253 */           int i = this.stripStartVertexIndices[b];
/* 254 */           int j = i + this.stripVertexCounts[b++];
/* 255 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 256 */             arrayOfInt[b1] = i;
/* 257 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 259 */           while (i < j) {
/* 260 */             arrayOfInt[2] = i;
/* 261 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 262 */             if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 263 */               if (paramInt1 == 0) {
/* 264 */                 return true;
/*     */               }
/* 266 */               if (arrayOfDouble[0] < d1) {
/* 267 */                 d1 = arrayOfDouble[0];
/* 268 */                 d2 = paramPoint3d.x;
/* 269 */                 d3 = paramPoint3d.y;
/* 270 */                 d4 = paramPoint3d.z;
/* 271 */                 if ((paramInt1 & 0x20) != 0) {
/* 272 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 276 */               if ((paramInt1 & 0x40) != 0) {
/* 277 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 281 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 282 */             arrayOfInt[0] = arrayOfInt[1];
/* 283 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 284 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 289 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 291 */         while (b < this.stripVertexCounts.length) {
/* 292 */           int i = this.stripStartVertexIndices[b];
/* 293 */           int j = i + this.stripVertexCounts[b++];
/* 294 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 295 */             arrayOfInt[b1] = i;
/* 296 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 298 */           while (i < j) {
/* 299 */             arrayOfInt[2] = i;
/* 300 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 301 */             if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 302 */               if (paramInt1 == 0) {
/* 303 */                 return true;
/*     */               }
/* 305 */               if (arrayOfDouble[0] < d1) {
/* 306 */                 d1 = arrayOfDouble[0];
/* 307 */                 d2 = paramPoint3d.x;
/* 308 */                 d3 = paramPoint3d.y;
/* 309 */                 d4 = paramPoint3d.z;
/* 310 */                 if ((paramInt1 & 0x20) != 0) {
/* 311 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 315 */               if ((paramInt1 & 0x40) != 0) {
/* 316 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 320 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 321 */             arrayOfInt[0] = arrayOfInt[1];
/* 322 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 323 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 329 */         throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArrayRetained0"));
/*     */       default:
/* 331 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 334 */     if (d1 < Double.MAX_VALUE) {
/* 335 */       paramPoint3d.x = d2;
/* 336 */       paramPoint3d.y = d3;
/* 337 */       paramPoint3d.z = d4;
/* 338 */       return true;
/*     */     } 
/* 340 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 346 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 347 */     double[] arrayOfDouble = new double[1];
/* 348 */     byte b = 0;
/*     */     
/* 350 */     arrayOfPoint3d[0] = new Point3d();
/* 351 */     arrayOfPoint3d[1] = new Point3d();
/* 352 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 354 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 356 */         while (b < this.stripVertexCounts.length) {
/* 357 */           int i = this.stripStartVertexIndices[b];
/* 358 */           int j = i + this.stripVertexCounts[b++];
/* 359 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 360 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 361 */           while (i < j) {
/* 362 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 363 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */             {
/* 365 */               return true;
/*     */             }
/* 367 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 368 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 373 */         while (b < this.stripVertexCounts.length) {
/* 374 */           int i = this.stripStartVertexIndices[b];
/* 375 */           int j = i + this.stripVertexCounts[b++];
/* 376 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 377 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 378 */           while (i < j) {
/* 379 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 380 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */             {
/*     */ 
/*     */               
/* 384 */               return true;
/*     */             }
/* 386 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 387 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 392 */         while (b < this.stripVertexCounts.length) {
/* 393 */           int i = this.stripStartVertexIndices[b];
/* 394 */           int j = i + this.stripVertexCounts[b++];
/* 395 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 396 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 397 */           while (i < j) {
/* 398 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 399 */             if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */             {
/* 401 */               return true;
/*     */             }
/* 403 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 404 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 409 */         while (b < this.stripVertexCounts.length) {
/* 410 */           int i = this.stripStartVertexIndices[b];
/* 411 */           int j = i + this.stripVertexCounts[b++];
/* 412 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 413 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 414 */           while (i < j) {
/* 415 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 416 */             if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]))
/*     */             {
/* 418 */               return true;
/*     */             }
/* 420 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 421 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/* 426 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 430 */     byte b = 0;
/* 431 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 432 */     arrayOfPoint3d[0] = new Point3d();
/* 433 */     arrayOfPoint3d[1] = new Point3d();
/* 434 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 436 */     while (b < this.stripVertexCounts.length) {
/* 437 */       int i = this.stripStartVertexIndices[b];
/* 438 */       int j = i + this.stripVertexCounts[b++];
/* 439 */       getVertexData(i++, arrayOfPoint3d[0]);
/* 440 */       getVertexData(i++, arrayOfPoint3d[1]);
/* 441 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 442 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 443 */       while (i < j) {
/* 444 */         getVertexData(i++, arrayOfPoint3d[2]);
/* 445 */         paramTransform3D.transform(arrayOfPoint3d[2]);
/* 446 */         if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 447 */           return true;
/*     */         }
/* 449 */         arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 450 */         arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */       } 
/*     */     } 
/* 453 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 458 */     byte b = 0;
/*     */     
/* 460 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 461 */     arrayOfPoint3d[0] = new Point3d();
/* 462 */     arrayOfPoint3d[1] = new Point3d();
/* 463 */     arrayOfPoint3d[2] = new Point3d();
/*     */ 
/*     */     
/* 466 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 468 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 470 */         while (b < this.stripVertexCounts.length) {
/* 471 */           int i = this.stripStartVertexIndices[b];
/* 472 */           int j = i + this.stripVertexCounts[b++];
/* 473 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 474 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 475 */           while (i < j) {
/* 476 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 477 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 478 */               return true;
/*     */             }
/* 480 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 481 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
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
/*     */ 
/*     */         
/* 525 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (b < this.stripVertexCounts.length) { int i = this.stripStartVertexIndices[b]; int j = i + this.stripVertexCounts[b++]; getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); while (i < j) { getVertexData(i++, arrayOfPoint3d[2]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (b < this.stripVertexCounts.length) { int i = this.stripStartVertexIndices[b]; int j = i + this.stripVertexCounts[b++]; getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); while (i < j) { getVertexData(i++, arrayOfPoint3d[2]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/*     */   void computeCentroid() {
/* 530 */     Point3d point3d1 = new Point3d();
/* 531 */     Point3d point3d2 = new Point3d();
/* 532 */     Point3d point3d3 = new Point3d();
/* 533 */     Vector3d vector3d1 = new Vector3d();
/* 534 */     Vector3d vector3d2 = new Vector3d();
/* 535 */     Vector3d vector3d3 = new Vector3d();
/*     */     
/* 537 */     double d = 0.0D;
/* 538 */     byte b = 0;
/* 539 */     this.centroid.x = 0.0D;
/* 540 */     this.centroid.y = 0.0D;
/* 541 */     this.centroid.z = 0.0D;
/*     */     
/* 543 */     while (b < this.stripVertexCounts.length) {
/* 544 */       int j = this.stripStartVertexIndices[b];
/* 545 */       int i = j + this.stripVertexCounts[b++];
/* 546 */       getVertexData(j++, point3d1);
/* 547 */       getVertexData(j++, point3d2);
/* 548 */       byte b1 = 2;
/* 549 */       while (j < i) {
/* 550 */         double d1 = 0.0D;
/* 551 */         switch (b1) {
/*     */           case 0:
/* 553 */             getVertexData(j++, point3d1);
/* 554 */             b1 = 1;
/*     */             break;
/*     */           case 1:
/* 557 */             getVertexData(j++, point3d2);
/* 558 */             b1 = 2;
/*     */             break;
/*     */           default:
/* 561 */             getVertexData(j++, point3d3);
/* 562 */             b1 = 0;
/*     */             break;
/*     */         } 
/*     */         
/* 566 */         vector3d1.sub(point3d1, point3d2);
/* 567 */         vector3d3.sub(point3d2, point3d3);
/*     */ 
/*     */         
/* 570 */         vector3d2.cross(vector3d1, vector3d3);
/* 571 */         vector3d2.normalize();
/*     */         
/* 573 */         if (Double.isNaN(vector3d2.x + vector3d2.y + vector3d2.z)) {
/*     */           continue;
/*     */         }
/* 576 */         vector3d3.set(0.0D, 0.0D, 0.0D);
/*     */ 
/*     */         
/* 579 */         getCrossValue(point3d1, point3d2, vector3d3);
/* 580 */         getCrossValue(point3d2, point3d3, vector3d3);
/* 581 */         getCrossValue(point3d3, point3d1, vector3d3);
/* 582 */         d1 = vector3d2.dot(vector3d3);
/* 583 */         d += d1;
/* 584 */         this.centroid.x += (point3d1.x + point3d2.x + point3d3.x) * d1;
/* 585 */         this.centroid.y += (point3d1.y + point3d2.y + point3d3.y) * d1;
/* 586 */         this.centroid.z += (point3d1.z + point3d2.z + point3d3.z) * d1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 591 */     if (d != 0.0D) {
/* 592 */       double d1 = 1.0D / 3.0D * d;
/* 593 */       this.centroid.x *= d1;
/* 594 */       this.centroid.y *= d1;
/* 595 */       this.centroid.z *= d1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 601 */   int getClassType() { return 3; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TriangleStripArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */