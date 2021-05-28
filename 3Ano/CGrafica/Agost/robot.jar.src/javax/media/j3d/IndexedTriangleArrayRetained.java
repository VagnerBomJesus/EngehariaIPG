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
/*     */ class IndexedTriangleArrayRetained
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
/*  32 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*  33 */     double[] arrayOfDouble = new double[1];
/*  34 */     double d1 = Double.MAX_VALUE;
/*  35 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  36 */     int[] arrayOfInt = new int[3];
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
/*     */     
/*  48 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  50 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  52 */         while (i < j) {
/*  53 */           for (byte b = 0; b < 3; b++) {
/*  54 */             arrayOfInt[b] = this.indexCoord[i];
/*  55 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/*  57 */           if (intersectRay(arrayOfPoint3d, pickRay, arrayOfDouble, paramPoint3d)) {
/*  58 */             if (paramInt1 == 0) {
/*  59 */               return true;
/*     */             }
/*  61 */             if (arrayOfDouble[0] < d1) {
/*  62 */               d1 = arrayOfDouble[0];
/*  63 */               d2 = paramPoint3d.x;
/*  64 */               d3 = paramPoint3d.y;
/*  65 */               d4 = paramPoint3d.z;
/*  66 */               if ((paramInt1 & 0x20) != 0) {
/*  67 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  71 */             if ((paramInt1 & 0x40) != 0) {
/*  72 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  79 */         pickSegment = (PickSegment)paramPickShape;
/*  80 */         while (i < j) {
/*  81 */           for (byte b = 0; b < 3; b++) {
/*  82 */             arrayOfInt[b] = this.indexCoord[i];
/*  83 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/*  85 */           if (intersectSegment(arrayOfPoint3d, pickSegment.start, pickSegment.end, arrayOfDouble, paramPoint3d)) {
/*     */             
/*  87 */             if (paramInt1 == 0) {
/*  88 */               return true;
/*     */             }
/*  90 */             if (arrayOfDouble[0] < d1) {
/*  91 */               d1 = arrayOfDouble[0];
/*  92 */               d2 = paramPoint3d.x;
/*  93 */               d3 = paramPoint3d.y;
/*  94 */               d4 = paramPoint3d.z;
/*  95 */               if ((paramInt1 & 0x20) != 0) {
/*  96 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 100 */             if ((paramInt1 & 0x40) != 0) {
/* 101 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/* 108 */         boundingBox = (BoundingBox)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 111 */         while (i < j) {
/* 112 */           for (byte b = 0; b < 3; b++) {
/* 113 */             arrayOfInt[b] = this.indexCoord[i];
/* 114 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 116 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, arrayOfDouble, paramPoint3d)) {
/* 117 */             if (paramInt1 == 0) {
/* 118 */               return true;
/*     */             }
/* 120 */             if (arrayOfDouble[0] < d1) {
/* 121 */               d1 = arrayOfDouble[0];
/* 122 */               d2 = paramPoint3d.x;
/* 123 */               d3 = paramPoint3d.y;
/* 124 */               d4 = paramPoint3d.z;
/* 125 */               if ((paramInt1 & 0x20) != 0) {
/* 126 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 130 */             if ((paramInt1 & 0x40) != 0) {
/* 131 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 7:
/* 138 */         boundingSphere = (BoundingSphere)((PickBounds)paramPickShape).bounds;
/*     */ 
/*     */         
/* 141 */         while (i < j) {
/* 142 */           for (byte b = 0; b < 3; b++) {
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
/*     */         
/* 171 */         while (i < j) {
/* 172 */           for (byte b = 0; b < 3; b++) {
/* 173 */             arrayOfInt[b] = this.indexCoord[i];
/* 174 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 176 */           if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, arrayOfDouble, paramPoint3d)) {
/*     */             
/* 178 */             if (paramInt1 == 0) {
/* 179 */               return true;
/*     */             }
/* 181 */             if (arrayOfDouble[0] < d1) {
/* 182 */               d1 = arrayOfDouble[0];
/* 183 */               d2 = paramPoint3d.x;
/* 184 */               d3 = paramPoint3d.y;
/* 185 */               d4 = paramPoint3d.z;
/* 186 */               if ((paramInt1 & 0x20) != 0) {
/* 187 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 191 */             if ((paramInt1 & 0x40) != 0) {
/* 192 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 199 */         pickCylinder = (PickCylinder)paramPickShape;
/* 200 */         while (i < j) {
/* 201 */           for (byte b = 0; b < 3; b++) {
/* 202 */             arrayOfInt[b] = this.indexCoord[i];
/* 203 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 205 */           if (intersectCylinder(arrayOfPoint3d, pickCylinder, arrayOfDouble, paramPoint3d)) {
/*     */             
/* 207 */             if (paramInt1 == 0) {
/* 208 */               return true;
/*     */             }
/* 210 */             if (arrayOfDouble[0] < d1) {
/* 211 */               d1 = arrayOfDouble[0];
/* 212 */               d2 = paramPoint3d.x;
/* 213 */               d3 = paramPoint3d.y;
/* 214 */               d4 = paramPoint3d.z;
/* 215 */               if ((paramInt1 & 0x20) != 0) {
/* 216 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 220 */             if ((paramInt1 & 0x40) != 0) {
/* 221 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 228 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 230 */         while (i < j) {
/* 231 */           for (byte b = 0; b < 3; b++) {
/* 232 */             arrayOfInt[b] = this.indexCoord[i];
/* 233 */             getVertexData(this.indexCoord[i++], arrayOfPoint3d[b]);
/*     */           } 
/* 235 */           if (intersectCone(arrayOfPoint3d, pickCone, arrayOfDouble, paramPoint3d)) {
/* 236 */             if (paramInt1 == 0) {
/* 237 */               return true;
/*     */             }
/* 239 */             if (arrayOfDouble[0] < d1) {
/* 240 */               d1 = arrayOfDouble[0];
/* 241 */               d2 = paramPoint3d.x;
/* 242 */               d3 = paramPoint3d.y;
/* 243 */               d4 = paramPoint3d.z;
/* 244 */               if ((paramInt1 & 0x20) != 0) {
/* 245 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 249 */             if ((paramInt1 & 0x40) != 0) {
/* 250 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 258 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleArrayRetained0"));
/*     */       default:
/* 260 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 263 */     if (d1 < Double.MAX_VALUE) {
/* 264 */       paramPoint3d.x = d2;
/* 265 */       paramPoint3d.y = d3;
/* 266 */       paramPoint3d.z = d4;
/* 267 */       return true;
/*     */     } 
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 274 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/* 275 */     double[] arrayOfDouble = new double[1];
/*     */ 
/*     */     
/* 278 */     int i = this.initialIndexIndex;
/* 279 */     int j = this.initialIndexIndex + this.validIndexCount;
/*     */     
/* 281 */     arrayOfPoint3d[0] = new Point3d();
/* 282 */     arrayOfPoint3d[1] = new Point3d();
/* 283 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 285 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 287 */         while (i < j) {
/* 288 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 289 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 290 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 291 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]))
/*     */           {
/* 293 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 298 */         while (i < j) {
/* 299 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 300 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 301 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 302 */           if (intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2]) || intersectTriTri(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3]))
/*     */           {
/*     */ 
/*     */             
/* 306 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 311 */         while (i < j) {
/* 312 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 313 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 314 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 315 */           if (intersectSegment(arrayOfPoint3d, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], arrayOfDouble, null))
/*     */           {
/* 317 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 322 */         while (i < j) {
/* 323 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 324 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 325 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 326 */           if (intersectTriPnt(arrayOfPoint3d[0], arrayOfPoint3d[1], arrayOfPoint3d[2], paramArrayOfPoint3d[0]))
/*     */           {
/* 328 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 338 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*     */ 
/*     */     
/* 341 */     int i = this.initialIndexIndex;
/* 342 */     int j = this.initialIndexIndex + this.validIndexCount;
/* 343 */     arrayOfPoint3d[0] = new Point3d();
/* 344 */     arrayOfPoint3d[1] = new Point3d();
/* 345 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 347 */     while (i < j) {
/* 348 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 349 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 350 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 351 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 352 */       paramTransform3D.transform(arrayOfPoint3d[1]);
/* 353 */       paramTransform3D.transform(arrayOfPoint3d[2]);
/* 354 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 355 */         return true;
/*     */       }
/*     */     } 
/* 358 */     return false;
/*     */   } boolean intersect(Bounds paramBounds) {
/*     */     BoundingPolytope boundingPolytope;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/* 363 */     Point3d[] arrayOfPoint3d = new Point3d[3];
/*     */ 
/*     */     
/* 366 */     int i = this.initialIndexIndex;
/* 367 */     int j = this.initialIndexIndex + this.validIndexCount;
/* 368 */     arrayOfPoint3d[0] = new Point3d();
/* 369 */     arrayOfPoint3d[1] = new Point3d();
/* 370 */     arrayOfPoint3d[2] = new Point3d();
/*     */     
/* 372 */     switch (paramBounds.getPickType()) {
/*     */       case 6:
/* 374 */         boundingBox = (BoundingBox)paramBounds;
/*     */         
/* 376 */         while (i < j) {
/* 377 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 378 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]);
/* 379 */           getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]);
/* 380 */           if (intersectBoundingBox(arrayOfPoint3d, boundingBox, null, null)) {
/* 381 */             return true;
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
/* 415 */         return false;case 7: boundingSphere = (BoundingSphere)paramBounds; while (i < j) { getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]); if (intersectBoundingSphere(arrayOfPoint3d, boundingSphere, null, null)) return true;  }  return false;case 8: boundingPolytope = (BoundingPolytope)paramBounds; while (i < j) { getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[1]); getVertexData(this.indexCoord[i++], arrayOfPoint3d[2]); if (intersectBoundingPolytope(arrayOfPoint3d, boundingPolytope, null, null)) return true;  }  return false;
/*     */     } 
/*     */     throw new RuntimeException("Bounds not supported for intersection " + paramBounds);
/*     */   }
/* 419 */   int getClassType() { return 3; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedTriangleArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */