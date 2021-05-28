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
/*     */ class PointArrayRetained
/*     */   extends GeometryArrayRetained
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
/*  33 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/*  35 */     Point3d point3d = new Point3d();
/*  36 */     int[] arrayOfInt = new int[1];
/*     */     
/*  38 */     switch (paramPickShape.getPickType()) {
/*     */       case 1:
/*  40 */         pickRay = (PickRay)paramPickShape;
/*     */         
/*  42 */         while (i < this.validVertexCount) {
/*  43 */           arrayOfInt[0] = i;
/*  44 */           getVertexData(i++, point3d);
/*  45 */           if (intersectPntAndRay(point3d, pickRay.origin, pickRay.direction, arrayOfDouble)) {
/*     */             
/*  47 */             if (paramInt1 == 0) {
/*  48 */               return true;
/*     */             }
/*  50 */             if (arrayOfDouble[0] < d1) {
/*  51 */               d1 = arrayOfDouble[0];
/*  52 */               d2 = point3d.x;
/*  53 */               d3 = point3d.y;
/*  54 */               d4 = point3d.z;
/*  55 */               if ((paramInt1 & 0x20) != 0) {
/*  56 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  60 */             if ((paramInt1 & 0x40) != 0) {
/*  61 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  68 */         pickSegment = (PickSegment)paramPickShape;
/*  69 */         vector3d = new Vector3d(pickSegment.end.x - pickSegment.start.x, pickSegment.end.y - pickSegment.start.y, pickSegment.end.z - pickSegment.start.z);
/*     */ 
/*     */ 
/*     */         
/*  73 */         while (i < this.validVertexCount) {
/*  74 */           arrayOfInt[0] = i;
/*  75 */           getVertexData(i++, point3d);
/*  76 */           if (intersectPntAndRay(point3d, pickSegment.start, vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D) {
/*     */ 
/*     */             
/*  79 */             if (paramInt1 == 0) {
/*  80 */               return true;
/*     */             }
/*  82 */             if (arrayOfDouble[0] < d1) {
/*  83 */               d1 = arrayOfDouble[0];
/*  84 */               d2 = point3d.x;
/*  85 */               d3 = point3d.y;
/*  86 */               d4 = point3d.z;
/*  87 */               if ((paramInt1 & 0x20) != 0) {
/*  88 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/*  92 */             if ((paramInt1 & 0x40) != 0) {
/*  93 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/* 102 */         bounds = ((PickBounds)paramPickShape).bounds;
/*     */         
/* 104 */         while (i < this.validVertexCount) {
/* 105 */           arrayOfInt[0] = i;
/* 106 */           getVertexData(i++, point3d);
/* 107 */           if (bounds.intersect(point3d)) {
/* 108 */             if (paramInt1 == 0) {
/* 109 */               return true;
/*     */             }
/* 111 */             arrayOfDouble[0] = paramPickShape.distance(point3d);
/* 112 */             if (arrayOfDouble[0] < d1) {
/* 113 */               d1 = arrayOfDouble[0];
/* 114 */               d2 = point3d.x;
/* 115 */               d3 = point3d.y;
/* 116 */               d4 = point3d.z;
/* 117 */               if ((paramInt1 & 0x20) != 0) {
/* 118 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 122 */             if ((paramInt1 & 0x40) != 0) {
/* 123 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 131 */         pickCylinder = (PickCylinder)paramPickShape;
/*     */         
/* 133 */         while (i < this.validVertexCount) {
/* 134 */           arrayOfInt[0] = i;
/* 135 */           getVertexData(i++, point3d);
/* 136 */           if (intersectCylinder(point3d, pickCylinder, arrayOfDouble)) {
/* 137 */             if (paramInt1 == 0) {
/* 138 */               return true;
/*     */             }
/* 140 */             if (arrayOfDouble[0] < d1) {
/* 141 */               d1 = arrayOfDouble[0];
/* 142 */               d2 = point3d.x;
/* 143 */               d3 = point3d.y;
/* 144 */               d4 = point3d.z;
/* 145 */               if ((paramInt1 & 0x20) != 0) {
/* 146 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 150 */             if ((paramInt1 & 0x40) != 0) {
/* 151 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 158 */         pickCone = (PickCone)paramPickShape;
/*     */         
/* 160 */         while (i < this.validVertexCount) {
/* 161 */           arrayOfInt[0] = i;
/* 162 */           getVertexData(i++, point3d);
/* 163 */           if (intersectCone(point3d, pickCone, arrayOfDouble)) {
/* 164 */             if (paramInt1 == 0) {
/* 165 */               return true;
/*     */             }
/* 167 */             if (arrayOfDouble[0] < d1) {
/* 168 */               d1 = arrayOfDouble[0];
/* 169 */               d2 = point3d.x;
/* 170 */               d3 = point3d.y;
/* 171 */               d4 = point3d.z;
/* 172 */               if ((paramInt1 & 0x20) != 0) {
/* 173 */                 storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */               }
/*     */             } 
/*     */             
/* 177 */             if ((paramInt1 & 0x40) != 0) {
/* 178 */               storeInterestData(paramPickInfo, paramInt1, paramGeometryRetained, paramInt2, arrayOfInt, paramPoint3d, arrayOfDouble[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 186 */         throw new IllegalArgumentException(J3dI18N.getString("PointArrayRetained0"));
/*     */       default:
/* 188 */         throw new RuntimeException("PickShape not supported for intersection");
/*     */     } 
/*     */     
/* 191 */     if (d1 < Double.MAX_VALUE) {
/* 192 */       paramPoint3d.x = d2;
/* 193 */       paramPoint3d.y = d3;
/* 194 */       paramPoint3d.z = d4;
/* 195 */       return true;
/*     */     } 
/* 197 */     return false;
/*     */   } boolean intersect(Point3d[] paramArrayOfPoint3d) {
/*     */     Vector3d vector3d;
/*     */     double[] arrayOfDouble;
/* 201 */     Point3d point3d = new Point3d();
/* 202 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */ 
/*     */     
/* 205 */     switch (paramArrayOfPoint3d.length) {
/*     */       case 3:
/* 207 */         while (i < this.validVertexCount) {
/* 208 */           getVertexData(i++, point3d);
/* 209 */           if (intersectTriPnt(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], point3d)) {
/* 210 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 215 */         while (i < this.validVertexCount) {
/* 216 */           getVertexData(i++, point3d);
/* 217 */           if (intersectTriPnt(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], point3d) || intersectTriPnt(paramArrayOfPoint3d[0], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3], point3d))
/*     */           {
/* 219 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 224 */         arrayOfDouble = new double[1];
/* 225 */         vector3d = new Vector3d();
/*     */         
/* 227 */         while (i < this.validVertexCount) {
/* 228 */           getVertexData(i++, point3d);
/* 229 */           vector3d.x = (paramArrayOfPoint3d[1]).x - (paramArrayOfPoint3d[0]).x;
/* 230 */           vector3d.y = (paramArrayOfPoint3d[1]).y - (paramArrayOfPoint3d[0]).y;
/* 231 */           vector3d.z = (paramArrayOfPoint3d[1]).z - (paramArrayOfPoint3d[0]).z;
/* 232 */           if (intersectPntAndRay(point3d, paramArrayOfPoint3d[0], vector3d, arrayOfDouble) && arrayOfDouble[0] <= 1.0D)
/*     */           {
/* 234 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 239 */         while (i < this.validVertexCount) {
/* 240 */           getVertexData(i++, point3d);
/* 241 */           if ((paramArrayOfPoint3d[0]).x == point3d.x && (paramArrayOfPoint3d[0]).y == point3d.y && (paramArrayOfPoint3d[0]).z == point3d.z)
/*     */           {
/*     */             
/* 244 */             return true;
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 256 */     Point3d[] arrayOfPoint3d = new Point3d[1];
/* 257 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 259 */     arrayOfPoint3d[0] = new Point3d();
/*     */     
/* 261 */     while (i < this.validVertexCount) {
/* 262 */       getVertexData(i++, arrayOfPoint3d[0]);
/* 263 */       paramTransform3D.transform(arrayOfPoint3d[0]);
/* 264 */       if (paramGeometryRetained.intersect(arrayOfPoint3d)) {
/* 265 */         return true;
/*     */       }
/*     */     } 
/* 268 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Bounds paramBounds) {
/* 273 */     int i = ((this.vertexFormat & 0x80) == 0) ? this.initialVertexIndex : this.initialCoordIndex;
/*     */     
/* 275 */     Point3d point3d = new Point3d();
/*     */     
/* 277 */     while (i < this.validVertexCount) {
/* 278 */       getVertexData(i++, point3d);
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


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\PointArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */