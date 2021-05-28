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
/*     */ class IndexedQuadArrayRetained
/*     */   extends IndexedGeometryArrayRetained
/*     */ {
/*     */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) {
/*     */     PickCone pickCone;
/*     */     PickCylinder pickCylinder;
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/*     */     PickSegment pickSegment;
/*     */     PickRay pickRay;
/*  32 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*  33 */     double[] arrayOfDouble = new double[1];
/*  34 */     double d1 = Double.MAX_VALUE;
/*  35 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  36 */     int[] arrayOfInt = new int[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     int i = this.initialIndexIndex;
/*  43 */     int j = this.initialIndexIndex + this.validIndexCount;
/*  44 */     arrayOfPoint3d[0] = new Point3d();
/*  45 */     arrayOfPoint3d[1] = new Point3d();
/*  46 */     arrayOfPoint3d[2] = new Point3d();
/*  47 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/*  49 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  51 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  53 */         while (i < j) {
/*  54 */           for (byte b = 0; b < 4; b++) {
/*  55 */             arrayOfInt[b] = this.indexCoord[i];
/*  56 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/*  58 */           if (intersectRay(arrayOfPoint3d, pickRay, arrayOfDouble, paramPoint3d)) {
/*  59 */             if (paramInt1 == 0) {
/*  60 */               return true;
/*     */             }
/*  62 */             if (arrayOfDouble[0] < d1) {
/*  63 */               d1 = arrayOfDouble[0];
/*  64 */               d2 = paramPoint3d.x;
/*  65 */               d3 = paramPoint3d.y;
/*  66 */               d4 = paramPoint3d.z;
/*  67 */               if ((paramInt1 & 0x20) != 0) {
/*  68 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  72 */             if ((paramInt1 & 0x40) != 0) {
/*  73 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  80 */         pickSegment = (PickSegment)paramPickShape;
/*     */         
/*  82 */         while (i < j) {
/*  83 */           for (byte b = 0; b < 4; b++) {
/*  84 */             arrayOfInt[b] = this.indexCoord[i];
/*  85 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/*  87 */           if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
/*     */             
/*  89 */             if (paramInt1 == 0) {
/*  90 */               return true;
/*     */             }
/*  92 */             if (arrayOfDouble[0] < d1) {
/*  93 */               d1 = arrayOfDouble[0];
/*  94 */               d2 = paramPoint3d.x;
/*  95 */               d3 = paramPoint3d.y;
/*  96 */               d4 = paramPoint3d.z;
/*  97 */               if ((paramInt1 & 0x20) != 0) {
/*  98 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 102 */             if ((paramInt1 & 0x40) != 0) {
/* 103 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/* 110 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */         
/* 112 */         while (i < j) {
/* 113 */           for (byte b = 0; b < 4; b++) {
/* 114 */             arrayOfInt[b] = this.indexCoord[i];
/* 115 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 117 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 118 */             if (paramInt1 == 0) {
/* 119 */               return true;
/*     */             }
/* 121 */             if (arrayOfDouble[0] < d1) {
/* 122 */               d1 = arrayOfDouble[0];
/* 123 */               d2 = paramPoint3d.x;
/* 124 */               d3 = paramPoint3d.y;
/* 125 */               d4 = paramPoint3d.z;
/* 126 */               if ((paramInt1 & 0x20) != 0) {
/* 127 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 131 */             if ((paramInt1 & 0x40) != 0) {
/* 132 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 7:
/* 139 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */         
/* 141 */         while (i < j) {
/* 142 */           for (byte b = 0; b < 4; b++) {
/* 143 */             arrayOfInt[b] = this.indexCoord[i];
/* 144 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 146 */           if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/* 147 */             if (paramInt1 == 0) {
/* 148 */               return true;
/*     */             }
/* 150 */             if (arrayOfDouble[0] < d1) {
/* 151 */               d1 = arrayOfDouble[0];
/* 152 */               d2 = paramPoint3d.x;
/* 153 */               d3 = paramPoint3d.y;
/* 154 */               d4 = paramPoint3d.z;
/* 155 */               if ((paramInt1 & 0x20) != 0) {
/* 156 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 160 */             if ((paramInt1 & 0x40) != 0) {
/* 161 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 8:
/* 168 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */         
/* 170 */         while (i < j) {
/* 171 */           for (byte b = 0; b < 4; b++) {
/* 172 */             arrayOfInt[b] = this.indexCoord[i];
/* 173 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
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
/* 198 */         while (i < j) {
/* 199 */           for (byte b = 0; b < 4; b++) {
/* 200 */             arrayOfInt[b] = this.indexCoord[i];
/* 201 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 203 */           if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
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
/* 226 */         while (i < j) {
/* 227 */           for (byte b = 0; b < 4; b++) {
/* 228 */             arrayOfInt[b] = this.indexCoord[i];
/* 229 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 231 */           if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 232 */             if (paramInt1 == 0) {
/* 233 */               return true;
/*     */             }
/* 235 */             if (arrayOfDouble[0] < d1) {
/* 236 */               d1 = arrayOfDouble[0];
/* 237 */               d2 = paramPoint3d.x;
/* 238 */               d3 = paramPoint3d.y;
/* 239 */               d4 = paramPoint3d.z;
/* 240 */               if ((paramInt1 & 0x20) != 0) {
/* 241 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 245 */             if ((paramInt1 & 0x40) != 0) {
/* 246 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 254 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedQuadArrayRetained0"));
/*     */       default:
/* 256 */         throw new RuntimeException("PickShape not supported for intersection ");
/*     */     } 
/*     */     
/* 259 */     if (d1 < Double.MAX_VALUE) {
/* 260 */       paramPoint3d.x = d2;
/* 261 */       paramPoint3d.y = d3;
/* 262 */       paramPoint3d.z = d4;
/* 263 */       return true;
/*     */     } 
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 271 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/* 272 */     double[] arrayOfDouble = new double[1];
/*     */ 
/*     */     
/* 275 */     int i = this.initialIndexIndex;
/* 276 */     int j = this.initialIndexIndex + this.validIndexCount;
/*     */     
/* 278 */     arrayOfPoint3d[0] = new Point3d();
/* 279 */     arrayOfPoint3d[1] = new Point3d();
/* 280 */     arrayOfPoint3d[2] = new Point3d();
/* 281 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/* 283 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 285 */         while (i < j) {
/* 286 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 287 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 288 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 289 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]);
/* 290 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */           {
/*     */ 
/*     */             
/* 294 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 299 */         while (i < j) {
/* 300 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 301 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 302 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 303 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]);
/* 304 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 312 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 317 */         while (i < j) {
/* 318 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 319 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 320 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 321 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]);
/* 322 */           if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */           {
/* 324 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 329 */         while (i < j) {
/* 330 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 331 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 332 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 333 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]);
/* 334 */           if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]) || intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[2], arrayOfPoint3d[3], paramArrayOfPoint3d[0]))
/*     */           {
/*     */ 
/*     */             
/* 338 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 343 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 350 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*     */ 
/*     */     
/* 353 */     int i = this.initialIndexIndex;
/* 354 */     int j = this.initialIndexIndex + this.validIndexCount;
/*     */     
/* 356 */     arrayOfPoint3d[0] = new Point3d();
/* 357 */     arrayOfPoint3d[1] = new Point3d();
/* 358 */     arrayOfPoint3d[2] = new Point3d();
/* 359 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/* 361 */     while (i < j) {
/* 362 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 363 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 364 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 365 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]);
/* 366 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 367 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 368 */       paramTransform3D.transform(arrayOfPoint3d[2]);
/* 369 */       paramTransform3D.transform(arrayOfPoint3d[3]);
/* 370 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 371 */         return true;
/*     */       }
/*     */     } 
/* 374 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 379 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*     */ 
/*     */     
/* 382 */     int i = this.initialIndexIndex;
/* 383 */     int j = this.initialIndexIndex + this.validIndexCount;
/*     */     
/* 385 */     arrayOfPoint3d[0] = new Point3d();
/* 386 */     arrayOfPoint3d[1] = new Point3d();
/* 387 */     arrayOfPoint3d[2] = new Point3d();
/* 388 */     arrayOfPoint3d[3] = new Point3d();
/*     */     
/* 390 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 392 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 394 */         while (i < j) {
/* 395 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 396 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 397 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 398 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]);
/* 399 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 400 */             return true;
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
/* 434 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (i < j) { getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (i < j) { getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[3]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/*     */   
/* 439 */   int getClassType() { return 4; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedQuadArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */