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
/*     */ class IndexedLineArrayRetained
/*     */   extends IndexedGeometryArrayRetained
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     int i = this.initialIndexIndex;
/*  40 */     int j = this.initialIndexIndex + this.validIndexCount;
/*  41 */     arrayOfPoint3d[0] = new Point3d();
/*  42 */     arrayOfPoint3d[1] = new Point3d();
/*  43 */     int[] arrayOfInt = new int[2];
/*     */     
/*  45 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  47 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  49 */         while (i < j) {
/*  50 */           for (byte b = 0; b < 2; b++) {
/*  51 */             arrayOfInt[b] = this.indexCoord[i];
/*  52 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/*  54 */           if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickRay.origin, pickRay.direction, arrayOfDouble, paramPoint3d)) {
/*     */ 
/*     */             
/*  57 */             if (paramInt1 == 0) {
/*  58 */               return true;
/*     */             }
/*  60 */             if (arrayOfDouble[0] < d1) {
/*  61 */               d1 = arrayOfDouble[0];
/*  62 */               d2 = paramPoint3d.x;
/*  63 */               d3 = paramPoint3d.y;
/*  64 */               d4 = paramPoint3d.z;
/*  65 */               if ((paramInt1 & 0x20) != 0) {
/*  66 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  70 */             if ((paramInt1 & 0x40) != 0) {
/*  71 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  78 */         pickSegment = (PickSegment)paramPickShape;
/*  79 */         vector3d = new Vector3d(pickSegment.end.x - pickSegment.start.x, pickSegment.end.y - pickSegment.start.y, pickSegment.end.z - pickSegment.start.z);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  84 */         while (i < j) {
/*  85 */           for (byte b = 0; b < 2; b++) {
/*  86 */             arrayOfInt[b] = this.indexCoord[i];
/*  87 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/*  89 */           if (intersectLineAndRay(arrayOfPoint3d[0], arrayOfPoint3d[1], pickSegment.start, vector3d, arrayOfDouble, paramPoint3d) && arrayOfDouble[0] <= 1.0D) {
/*     */ 
/*     */ 
/*     */             
/*  93 */             if (paramInt1 == 0) {
/*  94 */               return true;
/*     */             }
/*  96 */             if (arrayOfDouble[0] < d1) {
/*  97 */               d1 = arrayOfDouble[0];
/*  98 */               d2 = paramPoint3d.x;
/*  99 */               d3 = paramPoint3d.y;
/* 100 */               d4 = paramPoint3d.z;
/* 101 */               if ((paramInt1 & 0x20) != 0) {
/* 102 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 106 */             if ((paramInt1 & 0x40) != 0) {
/* 107 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/* 114 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 117 */         while (i < j) {
/* 118 */           for (byte b = 0; b < 2; b++) {
/* 119 */             arrayOfInt[b] = this.indexCoord[i];
/* 120 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 122 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 123 */             if (paramInt1 == 0) {
/* 124 */               return true;
/*     */             }
/* 126 */             if (arrayOfDouble[0] < d1) {
/* 127 */               d1 = arrayOfDouble[0];
/* 128 */               d2 = paramPoint3d.x;
/* 129 */               d3 = paramPoint3d.y;
/* 130 */               d4 = paramPoint3d.z;
/* 131 */               if ((paramInt1 & 0x20) != 0) {
/* 132 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 136 */             if ((paramInt1 & 0x40) != 0) {
/* 137 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 7:
/* 144 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 147 */         while (i < j) {
/* 148 */           for (byte b = 0; b < 2; b++) {
/* 149 */             arrayOfInt[b] = this.indexCoord[i];
/* 150 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 152 */           if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, arrayOfDouble, paramPoint3d)) {
/* 153 */             if (paramInt1 == 0) {
/* 154 */               return true;
/*     */             }
/* 156 */             if (arrayOfDouble[0] < d1) {
/* 157 */               d1 = arrayOfDouble[0];
/* 158 */               d2 = paramPoint3d.x;
/* 159 */               d3 = paramPoint3d.y;
/* 160 */               d4 = paramPoint3d.z;
/* 161 */               if ((paramInt1 & 0x20) != 0) {
/* 162 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 166 */             if ((paramInt1 & 0x40) != 0) {
/* 167 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 8:
/* 174 */         boundingPolytope = (BoundingPolytope)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 177 */         while (i < j) {
/* 178 */           for (byte b = 0; b < 2; b++) {
/* 179 */             arrayOfInt[b] = this.indexCoord[i];
/* 180 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 182 */           if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/* 183 */             if (paramInt1 == 0) {
/* 184 */               return true;
/*     */             }
/* 186 */             if (arrayOfDouble[0] < d1) {
/* 187 */               d1 = arrayOfDouble[0];
/* 188 */               d2 = paramPoint3d.x;
/* 189 */               d3 = paramPoint3d.y;
/* 190 */               d4 = paramPoint3d.z;
/* 191 */               if ((paramInt1 & 0x20) != 0) {
/* 192 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 196 */             if ((paramInt1 & 0x40) != 0) {
/* 197 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 204 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 206 */         while (i < j) {
/* 207 */           for (byte b = 0; b < 2; b++) {
/* 208 */             arrayOfInt[b] = this.indexCoord[i];
/* 209 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 211 */           if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/* 212 */             if (paramInt1 == 0) {
/* 213 */               return true;
/*     */             }
/* 215 */             if (arrayOfDouble[0] < d1) {
/* 216 */               d1 = arrayOfDouble[0];
/* 217 */               d2 = paramPoint3d.x;
/* 218 */               d3 = paramPoint3d.y;
/* 219 */               d4 = paramPoint3d.z;
/* 220 */               if ((paramInt1 & 0x20) != 0) {
/* 221 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 225 */             if ((paramInt1 & 0x40) != 0) {
/* 226 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 233 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 235 */         while (i < j) {
/* 236 */           for (byte b = 0; b < 2; b++) {
/* 237 */             arrayOfInt[b] = this.indexCoord[i];
/* 238 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 240 */           if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 241 */             if (paramInt1 == 0) {
/* 242 */               return true;
/*     */             }
/* 244 */             if (arrayOfDouble[0] < d1) {
/* 245 */               d1 = arrayOfDouble[0];
/* 246 */               d2 = paramPoint3d.x;
/* 247 */               d3 = paramPoint3d.y;
/* 248 */               d4 = paramPoint3d.z;
/* 249 */               if ((paramInt1 & 0x20) != 0) {
/* 250 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 254 */             if ((paramInt1 & 0x40) != 0) {
/* 255 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 263 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedLineArrayRetained0"));
/*     */       default:
/* 265 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 268 */     if (d1 < Double.MAX_VALUE) {
/* 269 */       paramPoint3d.x = d2;
/* 270 */       paramPoint3d.y = d3;
/* 271 */       paramPoint3d.z = d4;
/* 272 */       return true;
/*     */     } 
/* 274 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/*     */     Vector3d vector3d;
/* 279 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*     */     
/* 281 */     double[] arrayOfDouble = new double[1];
/*     */ 
/*     */     
/* 284 */     int i = this.initialIndexIndex;
/* 285 */     int j = this.initialIndexIndex + this.validIndexCount;
/* 286 */     arrayOfPoint3d[0] = new Point3d();
/* 287 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 289 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/*     */       case 4:
/* 292 */         while (i < j) {
/* 293 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 294 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 295 */           if (intersectSegment(paramArrayOfPoint3d, arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfDouble, null))
/*     */           {
/* 297 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 302 */         vector3d = new Vector3d();
/* 303 */         while (i < j) {
/* 304 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 305 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 306 */           vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 307 */           vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 308 */           vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 309 */           if (intersectLineAndRay(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfPoint3d[0], vector3d, arrayOfDouble, null) && arrayOfDouble[0] <= 1.0D)
/*     */           {
/*     */             
/* 312 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 317 */         vector3d = new Vector3d();
/* 318 */         while (i < j) {
/* 319 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 320 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 321 */           vector3d.x = (arrayOfPoint3d[1]).x - (arrayOfPoint3d[0]).x;
/* 322 */           vector3d.y = (arrayOfPoint3d[1]).y - (arrayOfPoint3d[0]).y;
/* 323 */           vector3d.z = (arrayOfPoint3d[1]).z - (arrayOfPoint3d[0]).z;
/* 324 */           if (intersectPntAndRay(paramArrayOfPoint3d[0], arrayOfPoint3d[0], vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D)
/*     */           {
/* 326 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 331 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 338 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*     */ 
/*     */     
/* 341 */     int i = this.initialIndexIndex;
/* 342 */     int j = this.initialIndexIndex + this.validIndexCount;
/* 343 */     arrayOfPoint3d[0] = new Point3d();
/* 344 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 346 */     while (i < j) {
/* 347 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 348 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 349 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 350 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 351 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 352 */         return true;
/*     */       }
/*     */     } 
/* 355 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 360 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*     */ 
/*     */     
/* 363 */     int i = this.initialIndexIndex;
/* 364 */     int j = this.initialIndexIndex + this.validIndexCount;
/* 365 */     arrayOfPoint3d[0] = new Point3d();
/* 366 */     arrayOfPoint3d[1] = new Point3d();
/*     */     
/* 368 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 370 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 372 */         while (i < j) {
/* 373 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 374 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 375 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 376 */             return true;
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
/* 406 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (i < j) { getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (i < j) { getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/* 410 */   int getClassType() { return 2; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\IndexedLineArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */