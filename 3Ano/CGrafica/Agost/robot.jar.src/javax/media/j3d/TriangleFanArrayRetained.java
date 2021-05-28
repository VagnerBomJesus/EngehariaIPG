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
/*     */ 
/*     */ class TriangleFanArrayRetained
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
/*  37 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*  38 */     double[] arrayOfDouble = new double[1];
/*  39 */     double d1 = Double.MAX_VALUE;
/*  40 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  41 */     byte b = 0;
/*     */     
/*  43 */     arrayOfPoint3d[0] = new Point3d();
/*  44 */     arrayOfPoint3d[1] = new Point3d();
/*  45 */     arrayOfPoint3d[2] = new Point3d();
/*  46 */     int[] arrayOfInt = new int[3];
/*     */     
/*  48 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  50 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  52 */         while (b < this.stripVertexCounts.length) {
/*  53 */           int i = this.stripStartVertexIndices[b];
/*  54 */           int j = i + this.stripVertexCounts[b++];
/*  55 */           for (byte b1 = 0; b1 < 2; b1++) {
/*  56 */             arrayOfInt[b1] = i;
/*  57 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/*  59 */           while (i < j) {
/*  60 */             arrayOfInt[2] = i;
/*  61 */             getVertexData(i++, arrayOfPoint3d[2]);
/*  62 */             if (intersectRay(arrayOfPoint3d, pickRay, arrayOfDouble, paramPoint3d)) {
/*  63 */               if (paramInt1 == 0) {
/*  64 */                 return true;
/*     */               }
/*  66 */               if (arrayOfDouble[0] < d1) {
/*  67 */                 d1 = arrayOfDouble[0];
/*  68 */                 d2 = paramPoint3d.x;
/*  69 */                 d3 = paramPoint3d.y;
/*  70 */                 d4 = paramPoint3d.z;
/*  71 */                 if ((paramInt1 & 0x20) != 0) {
/*  72 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/*  76 */               if ((paramInt1 & 0x40) != 0) {
/*  77 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  81 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*  82 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  87 */         pickSegment = (PickSegment)paramPickShape;
/*     */         
/*  89 */         while (b < this.stripVertexCounts.length) {
/*  90 */           int i = this.stripStartVertexIndices[b];
/*  91 */           int j = i + this.stripVertexCounts[b++];
/*  92 */           for (byte b1 = 0; b1 < 2; b1++) {
/*  93 */             arrayOfInt[b1] = i;
/*  94 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/*  96 */           while (i < j) {
/*  97 */             arrayOfInt[2] = i;
/*  98 */             getVertexData(i++, arrayOfPoint3d[2]);
/*  99 */             if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 101 */               if (paramInt1 == 0) {
/* 102 */                 return true;
/*     */               }
/* 104 */               if (arrayOfDouble[0] < d1) {
/* 105 */                 d1 = arrayOfDouble[0];
/* 106 */                 d2 = paramPoint3d.x;
/* 107 */                 d3 = paramPoint3d.y;
/* 108 */                 d4 = paramPoint3d.z;
/* 109 */                 if ((paramInt1 & 0x20) != 0) {
/* 110 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 114 */               if ((paramInt1 & 0x40) != 0) {
/* 115 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 119 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 120 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 6:
/* 125 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 128 */         while (b < this.stripVertexCounts.length) {
/* 129 */           int i = this.stripStartVertexIndices[b];
/* 130 */           int j = i + this.stripVertexCounts[b++];
/* 131 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 132 */             arrayOfInt[b1] = i;
/* 133 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 135 */           while (i < j) {
/* 136 */             arrayOfInt[2] = i;
/* 137 */             getVertexData(i++, arrayOfPoint3d[2]);
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
/* 157 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 158 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 7:
/* 163 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 166 */         while (b < this.stripVertexCounts.length) {
/* 167 */           int i = this.stripStartVertexIndices[b];
/* 168 */           int j = i + this.stripVertexCounts[b++];
/* 169 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 170 */             arrayOfInt[b1] = i;
/* 171 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 173 */           while (i < j) {
/* 174 */             arrayOfInt[2] = i;
/* 175 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 176 */             if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 178 */               if (paramInt1 == 0) {
/* 179 */                 return true;
/*     */               }
/* 181 */               if (arrayOfDouble[0] < d1) {
/* 182 */                 d1 = arrayOfDouble[0];
/* 183 */                 d2 = paramPoint3d.x;
/* 184 */                 d3 = paramPoint3d.y;
/* 185 */                 d4 = paramPoint3d.z;
/* 186 */                 if ((paramInt1 & 0x20) != 0) {
/* 187 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 191 */               if ((paramInt1 & 0x40) != 0) {
/* 192 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 196 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 197 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 8:
/* 202 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 205 */         while (b < this.stripVertexCounts.length) {
/* 206 */           int i = this.stripStartVertexIndices[b];
/* 207 */           int j = i + this.stripVertexCounts[b++];
/* 208 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 209 */             arrayOfInt[b1] = i;
/* 210 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 212 */           while (i < j) {
/* 213 */             arrayOfInt[2] = i;
/* 214 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 215 */             if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/*     */               
/* 217 */               if (paramInt1 == 0) {
/* 218 */                 return true;
/*     */               }
/* 220 */               if (arrayOfDouble[0] < d1) {
/* 221 */                 d1 = arrayOfDouble[0];
/* 222 */                 d2 = paramPoint3d.x;
/* 223 */                 d3 = paramPoint3d.y;
/* 224 */                 d4 = paramPoint3d.z;
/* 225 */                 if ((paramInt1 & 0x20) != 0) {
/* 226 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 230 */               if ((paramInt1 & 0x40) != 0) {
/* 231 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 235 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 236 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 241 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 243 */         while (b < this.stripVertexCounts.length) {
/* 244 */           int i = this.stripStartVertexIndices[b];
/* 245 */           int j = i + this.stripVertexCounts[b++];
/* 246 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 247 */             arrayOfInt[b1] = i;
/* 248 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 250 */           while (i < j) {
/* 251 */             arrayOfInt[2] = i;
/* 252 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 253 */             if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 254 */               if (paramInt1 == 0) {
/* 255 */                 return true;
/*     */               }
/* 257 */               if (arrayOfDouble[0] < d1) {
/* 258 */                 d1 = arrayOfDouble[0];
/* 259 */                 d2 = paramPoint3d.x;
/* 260 */                 d3 = paramPoint3d.y;
/* 261 */                 d4 = paramPoint3d.z;
/* 262 */                 if ((paramInt1 & 0x20) != 0) {
/* 263 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 267 */               if ((paramInt1 & 0x40) != 0) {
/* 268 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 272 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 273 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 278 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 280 */         while (b < this.stripVertexCounts.length) {
/* 281 */           int i = this.stripStartVertexIndices[b];
/* 282 */           int j = i + this.stripVertexCounts[b++];
/* 283 */           for (byte b1 = 0; b1 < 2; b1++) {
/* 284 */             arrayOfInt[b1] = i;
/* 285 */             getVertexData(i++, arrayOfPoint3d[b1]);
/*     */           } 
/* 287 */           while (i < j) {
/* 288 */             arrayOfInt[2] = i;
/* 289 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 290 */             if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 291 */               if (paramInt1 == 0) {
/* 292 */                 return true;
/*     */               }
/* 294 */               if (arrayOfDouble[0] < d1) {
/* 295 */                 d1 = arrayOfDouble[0];
/* 296 */                 d2 = paramPoint3d.x;
/* 297 */                 d3 = paramPoint3d.y;
/* 298 */                 d4 = paramPoint3d.z;
/* 299 */                 if ((paramInt1 & 0x20) != 0) {
/* 300 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 304 */               if ((paramInt1 & 0x40) != 0) {
/* 305 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 309 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/* 310 */             arrayOfInt[1] = arrayOfInt[2];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 316 */         throw new IllegalArgumentException(J3dI18N.getString("TriangleFanArrayRetained0"));
/*     */       default:
/* 318 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 321 */     if (d1 < Double.MAX_VALUE) {
/* 322 */       paramPoint3d.x = d2;
/* 323 */       paramPoint3d.y = d3;
/* 324 */       paramPoint3d.z = d4;
/* 325 */       return true;
/*     */     } 
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 333 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 334 */     double[] arrayOfDouble = new double[1];
/* 335 */     byte b = 0;
/*     */     
/* 337 */     arrayOfPoint3d[0] = new Point3d();
/* 338 */     arrayOfPoint3d[1] = new Point3d();
/* 339 */     arrayOfPoint3d[2] = new Point3d();
/*     */ 
/*     */ 
/*     */     
/* 343 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 345 */         while (b < this.stripVertexCounts.length) {
/* 346 */           int i = this.stripStartVertexIndices[b];
/* 347 */           int j = i + this.stripVertexCounts[b++];
/* 348 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 349 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 350 */           while (i < j) {
/* 351 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 352 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */             {
/* 354 */               return true;
/*     */             }
/* 356 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 361 */         while (b < this.stripVertexCounts.length) {
/* 362 */           int i = this.stripStartVertexIndices[b];
/* 363 */           int j = i + this.stripVertexCounts[b++];
/* 364 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 365 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 366 */           while (i < j) {
/* 367 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 368 */             if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */             {
/*     */ 
/*     */               
/* 372 */               return true;
/*     */             }
/* 374 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 379 */         while (b < this.stripVertexCounts.length) {
/* 380 */           int i = this.stripStartVertexIndices[b];
/* 381 */           int j = i + this.stripVertexCounts[b++];
/* 382 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 383 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 384 */           while (i < j) {
/* 385 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 386 */             if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */             {
/* 388 */               return true;
/*     */             }
/* 390 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 395 */         while (b < this.stripVertexCounts.length) {
/* 396 */           int i = this.stripStartVertexIndices[b];
/* 397 */           int j = i + this.stripVertexCounts[b++];
/* 398 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 399 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 400 */           while (i < j) {
/* 401 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 402 */             if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]))
/*     */             {
/* 404 */               return true;
/*     */             }
/* 406 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/* 411 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 415 */     byte b = 0;
/* 416 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 417 */     arrayOfPoint3d[0] = new Point3d();
/* 418 */     arrayOfPoint3d[1] = new Point3d();
/* 419 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 421 */     while (b < this.stripVertexCounts.length) {
/* 422 */       int i = this.stripStartVertexIndices[b];
/* 423 */       int j = i + this.stripVertexCounts[b++];
/* 424 */       getVertexData(i++, arrayOfPoint3d[0]);
/* 425 */       getVertexData(i++, arrayOfPoint3d[1]);
/* 426 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 427 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 428 */       while (i < j) {
/* 429 */         getVertexData(i++, arrayOfPoint3d[2]);
/* 430 */         paramTransform3D.transform(arrayOfPoint3d[2]);
/* 431 */         if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 432 */           return true;
/*     */         }
/* 434 */         arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
/*     */       } 
/*     */     } 
/* 437 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 442 */     byte b = 0;
/*     */     
/* 444 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 445 */     arrayOfPoint3d[0] = new Point3d();
/* 446 */     arrayOfPoint3d[1] = new Point3d();
/* 447 */     arrayOfPoint3d[2] = new Point3d();
/*     */ 
/*     */ 
/*     */     
/* 451 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 453 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 455 */         while (b < this.stripVertexCounts.length) {
/* 456 */           int i = this.stripStartVertexIndices[b];
/* 457 */           getVertexData(i++, arrayOfPoint3d[0]);
/* 458 */           getVertexData(i++, arrayOfPoint3d[1]);
/* 459 */           int j = i + this.stripVertexCounts[b++];
/* 460 */           while (i < j) {
/* 461 */             getVertexData(i++, arrayOfPoint3d[2]);
/* 462 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 463 */               return true;
/*     */             }
/* 465 */             arrayOfPoint3d[1].set(arrayOfPoint3d[2]);
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
/* 507 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (b < this.stripVertexCounts.length) { int i = this.stripStartVertexIndices[b]; int j = i + this.stripVertexCounts[b++]; getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); while (i < j) { getVertexData(i++, arrayOfPoint3d[2]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (b < this.stripVertexCounts.length) { int i = this.stripStartVertexIndices[b]; int j = i + this.stripVertexCounts[b++]; getVertexData(i++, arrayOfPoint3d[0]); getVertexData(i++, arrayOfPoint3d[1]); while (i < j) { getVertexData(i++, arrayOfPoint3d[2]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  arrayOfPoint3d[1].set(arrayOfPoint3d[2]); }  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/*     */   void computeCentroid() {
/* 512 */     Vector3d vector3d1 = new Vector3d();
/* 513 */     Vector3d vector3d2 = new Vector3d();
/* 514 */     Vector3d vector3d3 = new Vector3d();
/* 515 */     Point3d point3d1 = new Point3d();
/* 516 */     Point3d point3d2 = new Point3d();
/* 517 */     Point3d point3d3 = new Point3d();
/* 518 */     double d = 0.0D;
/* 519 */     byte b = 0;
/* 520 */     this.centroid.x = 0.0D;
/* 521 */     this.centroid.y = 0.0D;
/* 522 */     this.centroid.z = 0.0D;
/*     */     
/* 524 */     while (b < this.stripVertexCounts.length) {
/* 525 */       int j = this.stripStartVertexIndices[b];
/* 526 */       int i = j + this.stripVertexCounts[b++];
/* 527 */       getVertexData(j++, point3d1);
/* 528 */       getVertexData(j++, point3d2);
/* 529 */       byte b1 = 2;
/* 530 */       while (j < i) {
/* 531 */         double d1 = 0.0D;
/* 532 */         if (b1 == 2) {
/* 533 */           getVertexData(j++, point3d3);
/* 534 */           b1 = 1;
/*     */         } else {
/* 536 */           getVertexData(j++, point3d2);
/* 537 */           b1 = 2;
/*     */         } 
/*     */ 
/*     */         
/* 541 */         vector3d1.sub(point3d1, point3d2);
/* 542 */         vector3d3.sub(point3d2, point3d3);
/*     */ 
/*     */         
/* 545 */         vector3d2.cross(vector3d1, vector3d3);
/* 546 */         vector3d2.normalize();
/*     */         
/* 548 */         if (Double.isNaN(vector3d2.x + vector3d2.y + vector3d2.z)) {
/*     */           continue;
/*     */         }
/* 551 */         vector3d3.set(0.0D, 0.0D, 0.0D);
/*     */ 
/*     */         
/* 554 */         getCrossValue(point3d1, point3d2, vector3d3);
/* 555 */         getCrossValue(point3d2, point3d3, vector3d3);
/* 556 */         getCrossValue(point3d3, point3d1, vector3d3);
/* 557 */         d1 = vector3d2.dot(vector3d3);
/* 558 */         d += d1;
/* 559 */         this.centroid.x += (point3d1.x + point3d2.x + point3d3.x) * d1;
/* 560 */         this.centroid.y += (point3d1.y + point3d2.y + point3d3.y) * d1;
/* 561 */         this.centroid.z += (point3d1.z + point3d2.z + point3d3.z) * d1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 566 */     if (d != 0.0D) {
/* 567 */       double d1 = 1.0D / 3.0D * d;
/* 568 */       this.centroid.x *= d1;
/* 569 */       this.centroid.y *= d1;
/* 570 */       this.centroid.z *= d1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 575 */   int getClassType() { return 3; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TriangleFanArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */