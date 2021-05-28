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
/*     */ class IndexedLineStripArrayRetained
/*     */   extends IndexedGeometryStripArrayRetained
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
/*  34 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*  35 */     double[] arrayOfDouble = new double[1];
/*  36 */     double d1 = Double.MAX_VALUE;
/*  37 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  38 */     byte b1 = 0;
/*  39 */     byte b2 = 0;
/*  40 */     int[] arrayOfInt = new int[2];
/*     */     
/*  42 */     arrayOfPoint3d[0] = new Point3d();
/*  43 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/*  45 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  47 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  49 */         while (b1 < this.stripIndexCounts.length) {
/*  50 */           arrayOfInt[0] = this.indexCoord[b2];
/*  51 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/*  52 */           int i = this.stripIndexCounts[b1++];
/*  53 */           for (byte b = 1; b < i; b++) {
/*  54 */             arrayOfInt[1] = this.indexCoord[b2];
/*  55 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/*  56 */             if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickRay.origin, pickRay.direction, arrayOfDouble, paramPoint3d)) {
/*     */ 
/*     */               
/*  59 */               if (paramInt1 == 0) {
/*  60 */                 return true;
/*     */               }
/*  62 */               if (arrayOfDouble[0] < d1) {
/*  63 */                 d1 = arrayOfDouble[0];
/*  64 */                 d2 = paramPoint3d.x;
/*  65 */                 d3 = paramPoint3d.y;
/*  66 */                 d4 = paramPoint3d.z;
/*  67 */                 if ((paramInt1 & 0x20) != 0) {
/*  68 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/*  72 */               if ((paramInt1 & 0x40) != 0) {
/*  73 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  77 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*  78 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  83 */         pickSegment = (PickSegment)paramPickShape;
/*  84 */         vector3d = new Vector3d(pickSegment.end.x - pickSegment.start.x, pickSegment.end.y - pickSegment.start.y, pickSegment.end.z - pickSegment.start.z);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  89 */         while (b1 < this.stripIndexCounts.length) {
/*  90 */           arrayOfInt[0] = this.indexCoord[b2];
/*  91 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/*  92 */           int i = this.stripIndexCounts[b1++];
/*  93 */           for (byte b = 1; b < i; b++) {
/*  94 */             arrayOfInt[1] = this.indexCoord[b2];
/*  95 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/*  96 */             if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickSegment.start, vector3d, arrayOfDouble, paramPoint3d) && arrayOfDouble[0] <= 1.0D) {
/*     */ 
/*     */ 
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
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 6:
/* 124 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 127 */         while (b1 < this.stripIndexCounts.length) {
/* 128 */           arrayOfInt[0] = this.indexCoord[b2];
/* 129 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 130 */           int i = this.stripIndexCounts[b1++];
/* 131 */           for (byte b = 1; b < i; b++) {
/* 132 */             arrayOfInt[1] = this.indexCoord[b2];
/* 133 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 134 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 135 */               if (paramInt1 == 0) {
/* 136 */                 return true;
/*     */               }
/* 138 */               if (arrayOfDouble[0] < d1) {
/* 139 */                 d1 = arrayOfDouble[0];
/* 140 */                 d2 = paramPoint3d.x;
/* 141 */                 d3 = paramPoint3d.y;
/* 142 */                 d4 = paramPoint3d.z;
/* 143 */                 if ((paramInt1 & 0x20) != 0) {
/* 144 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 148 */               if ((paramInt1 & 0x40) != 0) {
/* 149 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 153 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 154 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 7:
/* 159 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 162 */         while (b1 < this.stripIndexCounts.length) {
/* 163 */           arrayOfInt[0] = this.indexCoord[b2];
/* 164 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 165 */           int i = this.stripIndexCounts[b1++];
/* 166 */           for (byte b = 1; b < i; b++) {
/* 167 */             arrayOfInt[1] = this.indexCoord[b2];
/* 168 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 169 */             if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/* 170 */               if (paramInt1 == 0) {
/* 171 */                 return true;
/*     */               }
/* 173 */               if (arrayOfDouble[0] < d1) {
/* 174 */                 d1 = arrayOfDouble[0];
/* 175 */                 d2 = paramPoint3d.x;
/* 176 */                 d3 = paramPoint3d.y;
/* 177 */                 d4 = paramPoint3d.z;
/* 178 */                 if ((paramInt1 & 0x20) != 0) {
/* 179 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 183 */               if ((paramInt1 & 0x40) != 0) {
/* 184 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 188 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 189 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 8:
/* 194 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 197 */         while (b1 < this.stripIndexCounts.length) {
/* 198 */           arrayOfInt[0] = this.indexCoord[b2];
/* 199 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 200 */           int i = this.stripIndexCounts[b1++];
/* 201 */           for (byte b = 1; b < i; b++) {
/* 202 */             arrayOfInt[1] = this.indexCoord[b2];
/* 203 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 204 */             if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/* 205 */               if (paramInt1 == 0) {
/* 206 */                 return true;
/*     */               }
/* 208 */               if (arrayOfDouble[0] < d1) {
/* 209 */                 d1 = arrayOfDouble[0];
/* 210 */                 d2 = paramPoint3d.x;
/* 211 */                 d3 = paramPoint3d.y;
/* 212 */                 d4 = paramPoint3d.z;
/* 213 */                 if ((paramInt1 & 0x20) != 0) {
/* 214 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 218 */               if ((paramInt1 & 0x40) != 0) {
/* 219 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 223 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 224 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 229 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 231 */         while (b1 < this.stripIndexCounts.length) {
/* 232 */           arrayOfInt[0] = this.indexCoord[b2];
/* 233 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 234 */           int i = this.stripIndexCounts[b1++];
/* 235 */           for (byte b = 1; b < i; b++) {
/* 236 */             arrayOfInt[1] = this.indexCoord[b2];
/* 237 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 238 */             if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 239 */               if (paramInt1 == 0) {
/* 240 */                 return true;
/*     */               }
/* 242 */               if (arrayOfDouble[0] < d1) {
/* 243 */                 d1 = arrayOfDouble[0];
/* 244 */                 d2 = paramPoint3d.x;
/* 245 */                 d3 = paramPoint3d.y;
/* 246 */                 d4 = paramPoint3d.z;
/* 247 */                 if ((paramInt1 & 0x20) != 0) {
/* 248 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 252 */               if ((paramInt1 & 0x40) != 0) {
/* 253 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 257 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 258 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 263 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 265 */         while (b1 < this.stripIndexCounts.length) {
/* 266 */           arrayOfInt[0] = this.indexCoord[b2];
/* 267 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 268 */           int i = this.stripIndexCounts[b1++];
/* 269 */           for (byte b = 1; b < i; b++) {
/* 270 */             arrayOfInt[1] = this.indexCoord[b2];
/* 271 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 272 */             if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 273 */               if (paramInt1 == 0) {
/* 274 */                 return true;
/*     */               }
/* 276 */               if (arrayOfDouble[0] < d1) {
/* 277 */                 d1 = arrayOfDouble[0];
/* 278 */                 d2 = paramPoint3d.x;
/* 279 */                 d3 = paramPoint3d.y;
/* 280 */                 d4 = paramPoint3d.z;
/* 281 */                 if ((paramInt1 & 0x20) != 0) {
/* 282 */                   storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */                 }
/*     */               } 
/*     */               
/* 286 */               if ((paramInt1 & 0x40) != 0) {
/* 287 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 291 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/* 292 */             arrayOfInt[0] = arrayOfInt[1];
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 298 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArrayRetained0"));
/*     */       default:
/* 300 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 303 */     if (d1 < Double.MAX_VALUE) {
/* 304 */       paramPoint3d.x = d2;
/* 305 */       paramPoint3d.y = d3;
/* 306 */       paramPoint3d.z = d4;
/* 307 */       return true;
/*     */     } 
/* 309 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/*     */     Vector3d vector3d;
/* 314 */     byte b1 = 0;
/* 315 */     byte b2 = 0;
/*     */     
/* 317 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 318 */     double[] arrayOfDouble = new double[1];
/*     */ 
/*     */     
/* 321 */     arrayOfPoint3d[0] = new Point3d();
/* 322 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 324 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/*     */       case 4:
/* 327 */         while (b1 < this.stripIndexCounts.length) {
/* 328 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 329 */           int i = this.stripIndexCounts[b1++];
/* 330 */           for (byte b = 1; b < i; b++) {
/* 331 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 332 */             if (intersectSegment(paramArrayOfPoint3d, arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfDouble, null))
/*     */             {
/* 334 */               return true;
/*     */             }
/* 336 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 341 */         vector3d = new Vector3d();
/* 342 */         while (b1 < this.stripIndexCounts.length) {
/* 343 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 344 */           int i = this.stripIndexCounts[b1++];
/* 345 */           for (byte b = 1; b < i; b++) {
/* 346 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 347 */             vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 348 */             vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 349 */             vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 350 */             if (intersectLineAndRay(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfPoint3d[0], vector3d, arrayOfDouble, null) && arrayOfDouble[0] <= 1.0D)
/*     */             {
/*     */               
/* 353 */               return true;
/*     */             }
/* 355 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 360 */         vector3d = new Vector3d();
/* 361 */         while (b1 < this.stripIndexCounts.length) {
/* 362 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 363 */           int i = this.stripIndexCounts[b1++];
/* 364 */           for (byte b = 1; b < i; b++) {
/* 365 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 366 */             vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 367 */             vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 368 */             vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 369 */             if (intersectPntAndRay(paramArrayOfPoint3d[0], arrayOfPoint3d[0], vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D)
/*     */             {
/*     */               
/* 372 */               return true;
/*     */             }
/* 374 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */     
/* 380 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 385 */     byte b1 = 0;
/* 386 */     byte b2 = 0;
/* 387 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*     */     
/* 389 */     arrayOfPoint3d[0] = new Point3d();
/* 390 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 392 */     while (b1 < this.stripIndexCounts.length) {
/* 393 */       getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 394 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 395 */       int i = this.stripIndexCounts[b1++];
/*     */       
/* 397 */       for (byte b = 1; b < i; b++) {
/* 398 */         getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 399 */         paramTransform3D.transform(arrayOfPoint3d[1]);
/* 400 */         if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 401 */           return true;
/*     */         }
/* 403 */         arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
/*     */       } 
/*     */     } 
/* 406 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 411 */     byte b1 = 0;
/* 412 */     byte b2 = 0;
/* 413 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*     */     
/* 415 */     arrayOfPoint3d[0] = new Point3d();
/* 416 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 418 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 420 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 422 */         while (b1 < this.stripIndexCounts.length) {
/* 423 */           getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]);
/* 424 */           int i = this.stripIndexCounts[b1++];
/* 425 */           for (byte b = 1; b < i; b++) {
/* 426 */             getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]);
/* 427 */             if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 428 */               return true;
/*     */             }
/* 430 */             arrayOfPoint3d[0].set(arrayOfPoint3d[1]);
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
/* 469 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (b1 < this.stripIndexCounts.length) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]); int i = this.stripIndexCounts[b1++]; for (byte b = 1; b < i; b++) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); }  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (b1 < this.stripIndexCounts.length) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[0]); int i = this.stripIndexCounts[b1++]; for (byte b = 1; b < i; b++) { getVertexData(this.indexCoord[b2++], arrayOfPoint3d[1]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  arrayOfPoint3d[0].set(arrayOfPoint3d[1]); }  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/* 473 */   int getClassType() { return 2; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedLineStripArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */