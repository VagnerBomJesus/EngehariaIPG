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
/*     */ class IndexedPointArrayRetained
/*     */   extends IndexedGeometryArrayRetained
/*     */ {
/*     */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) {
/*     */     PickCone pickCone;
/*     */     PickCylinder pickCylinder;
/*     */     Bounds bounds;
/*     */     Vector3d vector3d;
/*     */     PickSegment pickSegment;
/*     */     PickRay pickRay;
/*  30 */     double[] arrayOfDouble = new double[1];
/*  31 */     double d1 = Double.MAX_VALUE;
/*  32 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*  33 */     Point3d point3d = new Point3d();
/*  34 */     int[] arrayOfInt = new int[1];
/*     */     
/*  36 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/*  39 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  41 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  43 */         while (i < this.validVertexCount) {
/*  44 */           arrayOfInt[0] = this.indexCoord[i];
/*  45 */           getVertexData(this.indexCoord[i++], point3d);
/*  46 */           if (intersectPntAndRay(point3d, pickRay.origin, pickRay.direction, arrayOfDouble)) {
/*     */             
/*  48 */             if (paramInt1 == 0) {
/*  49 */               return true;
/*     */             }
/*  51 */             if (arrayOfDouble[0] < d1) {
/*  52 */               d1 = arrayOfDouble[0];
/*  53 */               d2 = point3d.x;
/*  54 */               d3 = point3d.y;
/*  55 */               d4 = point3d.z;
/*  56 */               if ((paramInt1 & 0x20) != 0) {
/*  57 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  61 */             if ((paramInt1 & 0x40) != 0) {
/*  62 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  69 */         pickSegment = (PickSegment)paramPickShape;
/*  70 */         vector3d = new Vector3d(pickSegment.end.x - pickSegment.start.x, pickSegment.end.y - pickSegment.start.y, pickSegment.end.z - pickSegment.start.z);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  75 */         while (i < this.validVertexCount) {
/*  76 */           arrayOfInt[0] = this.indexCoord[i];
/*  77 */           getVertexData(this.indexCoord[i++], point3d);
/*  78 */           if (intersectPntAndRay(point3d, pickSegment.start, vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D) {
/*     */ 
/*     */             
/*  81 */             if (paramInt1 == 0) {
/*  82 */               return true;
/*     */             }
/*  84 */             if (arrayOfDouble[0] < d1) {
/*  85 */               d1 = arrayOfDouble[0];
/*  86 */               d2 = point3d.x;
/*  87 */               d3 = point3d.y;
/*  88 */               d4 = point3d.z;
/*  89 */               if ((paramInt1 & 0x20) != 0) {
/*  90 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  94 */             if ((paramInt1 & 0x40) != 0) {
/*  95 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/* 104 */         bounds = ((PickBounds)paramPickShape).bounds;
/*     */         
/* 106 */         while (i < this.validVertexCount) {
/* 107 */           arrayOfInt[0] = this.indexCoord[i];
/* 108 */           getVertexData(this.indexCoord[i++], point3d);
/* 109 */           if (bounds.intersect(point3d)) {
/* 110 */             if (paramInt1 == 0) {
/* 111 */               return true;
/*     */             }
/* 113 */             arrayOfDouble[0] = paramPickShape.distance(point3d);
/* 114 */             if (arrayOfDouble[0] < d1) {
/* 115 */               d1 = arrayOfDouble[0];
/* 116 */               d2 = point3d.x;
/* 117 */               d3 = point3d.y;
/* 118 */               d4 = point3d.z;
/* 119 */               if ((paramInt1 & 0x20) != 0) {
/* 120 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 124 */             if ((paramInt1 & 0x40) != 0) {
/* 125 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 132 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 134 */         while (i < this.validVertexCount) {
/* 135 */           arrayOfInt[0] = this.indexCoord[i];
/* 136 */           getVertexData(this.indexCoord[i++], point3d);
/* 137 */           if (intersectCylinder(point3d, pickCylinder, arrayOfDouble)) {
/* 138 */             if (paramInt1 == 0) {
/* 139 */               return true;
/*     */             }
/* 141 */             if (arrayOfDouble[0] < d1) {
/* 142 */               d1 = arrayOfDouble[0];
/* 143 */               d2 = point3d.x;
/* 144 */               d3 = point3d.y;
/* 145 */               d4 = point3d.z;
/* 146 */               if ((paramInt1 & 0x20) != 0) {
/* 147 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 151 */             if ((paramInt1 & 0x40) != 0) {
/* 152 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 159 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 161 */         while (i < this.validVertexCount) {
/* 162 */           arrayOfInt[0] = this.indexCoord[i];
/* 163 */           getVertexData(this.indexCoord[i++], point3d);
/* 164 */           if (intersectCone(point3d, pickCone, arrayOfDouble)) {
/* 165 */             if (paramInt1 == 0) {
/* 166 */               return true;
/*     */             }
/* 168 */             if (arrayOfDouble[0] < d1) {
/* 169 */               d1 = arrayOfDouble[0];
/* 170 */               d2 = point3d.x;
/* 171 */               d3 = point3d.y;
/* 172 */               d4 = point3d.z;
/* 173 */               if ((paramInt1 & 0x20) != 0) {
/* 174 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 178 */             if ((paramInt1 & 0x40) != 0) {
/* 179 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 187 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedPointArrayRetained0"));
/*     */       default:
/* 189 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 192 */     if (d1 < Double.MAX_VALUE) {
/* 193 */       paramPoint3d.x = d2;
/* 194 */       paramPoint3d.y = d3;
/* 195 */       paramPoint3d.z = d4;
/* 196 */       return true;
/*     */     } 
/* 198 */     return false;
/*     */   } boolean intersect(Point3d[] paramArrayOfPoint3d) {
/*     */     Vector3d vector3d;
/*     */     double[] arrayOfDouble;
/* 202 */     Point3d point3d = new Point3d();
/* 203 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 206 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 208 */         while (i < this.validVertexCount) {
/* 209 */           getVertexData(this.indexCoord[i++], point3d);
/* 210 */           if (intersectTriPnt(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], point3d)) {
/* 211 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 216 */         while (i < this.validVertexCount) {
/* 217 */           getVertexData(this.indexCoord[i++], point3d);
/* 218 */           if (intersectTriPnt(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], point3d) || intersectTriPnt(paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3], point3d))
/*     */           {
/* 220 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 225 */         arrayOfDouble = new double[1];
/* 226 */         vector3d = new Vector3d();
/*     */         
/* 228 */         while (i < this.validVertexCount) {
/* 229 */           getVertexData(this.indexCoord[i++], point3d);
/* 230 */           vector3d.x = (paramArrayOfPoint3d[1]).x - (paramArrayOfPoint3d[0]).x;
/* 231 */           vector3d.y = (paramArrayOfPoint3d[1]).y - (paramArrayOfPoint3d[0]).y;
/* 232 */           vector3d.z = (paramArrayOfPoint3d[1]).z - (paramArrayOfPoint3d[0]).z;
/* 233 */           if (intersectPntAndRay(point3d, paramArrayOfPoint3d[0], vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D)
/*     */           {
/* 235 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 240 */         while (i < this.validVertexCount) {
/* 241 */           getVertexData(this.indexCoord[i++], point3d);
/* 242 */           if ((paramArrayOfPoint3d[0]).x == point3d.x && (paramArrayOfPoint3d[0]).y == point3d.y && (paramArrayOfPoint3d[0]).z == point3d.z)
/*     */           {
/*     */             
/* 245 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 250 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 255 */     Point3d[] arrayOfPoint3d = new Point3d[1];
/* 256 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 258 */     arrayOfPoint3d[0] = new Point3d();
/*     */     
/* 260 */     while (i < this.validVertexCount) {
/* 261 */       getVertexData(this.indexCoord[i++], arrayOfPoint3d[0]);
/* 262 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 263 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 264 */         return true;
/*     */       }
/*     */     } 
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Bounds paramBounds) {
/* 273 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 275 */     Point3d point3d = new Point3d();
/*     */     
/* 277 */     while (i < this.validVertexCount) {
/* 278 */       getVertexData(this.indexCoord[i++], point3d);
/* 279 */       if (paramBounds.intersect(point3d)) {
/* 280 */         return true;
/*     */       }
/*     */     } 
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 287 */   int getClassType() { return 1; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\IndexedPointArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */