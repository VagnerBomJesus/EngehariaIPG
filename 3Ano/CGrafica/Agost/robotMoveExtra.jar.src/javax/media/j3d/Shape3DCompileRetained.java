/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ class Shape3DCompileRetained
/*     */   extends Shape3DRetained
/*     */ {
/*  24 */   int numShapes = 0;
/*     */ 
/*     */ 
/*     */   
/*  28 */   ArrayList geometryInfo = null;
/*     */ 
/*     */   
/*  31 */   Object[] srcList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Shape3DCompileRetained(Shape3DRetained[] paramArrayOfShape3DRetained, int paramInt1, int paramInt2) {
/*  39 */     Object[] arrayOfObject1 = new Object[15];
/*     */     
/*  41 */     Object[] arrayOfObject2 = new Object[15];
/*     */ 
/*     */     
/*  44 */     this.numShapes = paramInt1;
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.srcList = new Object[paramInt1];
/*     */     
/*  50 */     if (paramInt1 > 0) {
/*  51 */       this.boundsAutoCompute = (paramArrayOfShape3DRetained[0]).boundsAutoCompute;
/*  52 */       this.source = (paramArrayOfShape3DRetained[0]).source;
/*     */     } 
/*     */ 
/*     */     
/*  56 */     this.geometryList.remove(0);
/*  57 */     boolean bool = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     this.boundsAutoCompute = (paramArrayOfShape3DRetained[0]).boundsAutoCompute;
/*  63 */     this.isPickable = (paramArrayOfShape3DRetained[0]).isPickable;
/*  64 */     this.isCollidable = (paramArrayOfShape3DRetained[0]).isCollidable;
/*  65 */     this.appearanceOverrideEnable = (paramArrayOfShape3DRetained[0]).appearanceOverrideEnable;
/*  66 */     this.appearance = (paramArrayOfShape3DRetained[0]).appearance;
/*  67 */     this.collisionBound = (paramArrayOfShape3DRetained[0]).collisionBound;
/*  68 */     this.localBounds = (paramArrayOfShape3DRetained[0]).localBounds;
/*     */ 
/*     */     
/*  71 */     if ((paramInt2 & 0x2) != 0)
/*  72 */       this.geometryInfo = new ArrayList(); 
/*     */     byte b;
/*  74 */     for (b = 0; b < paramInt1; b++) {
/*  75 */       Shape3DRetained shape3DRetained = paramArrayOfShape3DRetained[b];
/*  76 */       ((Shape3D)shape3DRetained.source).id = b;
/*  77 */       shape3DRetained.source.retained = this;
/*  78 */       this.srcList[b] = shape3DRetained.source;
/*     */ 
/*     */ 
/*     */       
/*     */       byte b1;
/*     */ 
/*     */       
/*  85 */       for (b1 = 0; b1 < shape3DRetained.geometryList.size(); b1++) {
/*  86 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)shape3DRetained.geometryList.get(b1);
/*  87 */         if (geometryArrayRetained != null) {
/*  88 */           if (shape3DRetained.willRemainOpaque(geometryArrayRetained.geoType) && geometryArrayRetained.isMergeable()) {
/*  89 */             if (arrayOfObject1[geometryArrayRetained.geoType] == null) {
/*  90 */               arrayOfObject1[geometryArrayRetained.geoType] = new ArrayList();
/*     */             }
/*  92 */             ((ArrayList)arrayOfObject1[geometryArrayRetained.geoType]).add(geometryArrayRetained);
/*     */           }
/*     */           else {
/*     */             
/*  96 */             if (arrayOfObject2[geometryArrayRetained.geoType] == null) {
/*  97 */               arrayOfObject2[geometryArrayRetained.geoType] = new ArrayList();
/*     */             }
/*     */             
/* 100 */             ((ArrayList)arrayOfObject2[geometryArrayRetained.geoType]).add(geometryArrayRetained);
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 108 */       if ((paramInt2 & 0x2) != 0) {
/* 109 */         ArrayList arrayList = new ArrayList();
/* 110 */         for (b1 = 0; b1 < shape3DRetained.geometryList.size(); b1++) {
/* 111 */           GeometryRetained geometryRetained = (GeometryRetained)shape3DRetained.geometryList.get(b1);
/* 112 */           if (geometryRetained != null) {
/* 113 */             arrayList.add(geometryRetained.source);
/*     */           } else {
/* 115 */             arrayList.add(null);
/*     */           } 
/* 117 */         }  this.geometryInfo.add(arrayList);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 123 */     for (b = 1; b <= 14; b++) {
/* 124 */       QuadArrayRetained quadArrayRetained = null;
/*     */       
/* 126 */       switch (b) {
/*     */         case 1:
/* 128 */           if (arrayOfObject1[b] != null) {
/* 129 */             quadArrayRetained = new QuadArrayRetained();
/* 130 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 131 */             quadArrayRetained.setCompiled(arrayList);
/* 132 */             this.geometryList.add(quadArrayRetained);
/* 133 */             quadArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 135 */           if (arrayOfObject2[b] != null) {
/* 136 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 137 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 138 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 2:
/* 144 */           if (arrayOfObject1[b] != null) {
/* 145 */             TriangleArrayRetained triangleArrayRetained = new TriangleArrayRetained();
/* 146 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 147 */             triangleArrayRetained.setCompiled(arrayList);
/* 148 */             this.geometryList.add(triangleArrayRetained);
/* 149 */             triangleArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 151 */           if (arrayOfObject2[b] != null) {
/* 152 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 153 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 154 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 3:
/* 160 */           if (arrayOfObject1[b] != null) {
/* 161 */             PointArrayRetained pointArrayRetained = new PointArrayRetained();
/* 162 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 163 */             pointArrayRetained.setCompiled(arrayList);
/* 164 */             this.geometryList.add(pointArrayRetained);
/* 165 */             pointArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 167 */           if (arrayOfObject2[b] != null) {
/* 168 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 169 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 170 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 4:
/* 176 */           if (arrayOfObject1[b] != null) {
/* 177 */             LineArrayRetained lineArrayRetained = new LineArrayRetained();
/* 178 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 179 */             lineArrayRetained.setCompiled(arrayList);
/* 180 */             this.geometryList.add(lineArrayRetained);
/* 181 */             lineArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 183 */           if (arrayOfObject2[b] != null) {
/* 184 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 185 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 186 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 5:
/* 192 */           if (arrayOfObject1[b] != null) {
/* 193 */             TriangleStripArrayRetained triangleStripArrayRetained = new TriangleStripArrayRetained();
/* 194 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 195 */             triangleStripArrayRetained.setCompiled(arrayList);
/* 196 */             this.geometryList.add(triangleStripArrayRetained);
/* 197 */             triangleStripArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 199 */           if (arrayOfObject2[b] != null) {
/* 200 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 201 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 202 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 6:
/* 208 */           if (arrayOfObject1[b] != null) {
/* 209 */             TriangleFanArrayRetained triangleFanArrayRetained = new TriangleFanArrayRetained();
/* 210 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 211 */             triangleFanArrayRetained.setCompiled(arrayList);
/* 212 */             this.geometryList.add(triangleFanArrayRetained);
/* 213 */             triangleFanArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 215 */           if (arrayOfObject2[b] != null) {
/* 216 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 217 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 218 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 7:
/* 224 */           if (arrayOfObject1[b] != null) {
/* 225 */             LineStripArrayRetained lineStripArrayRetained = new LineStripArrayRetained();
/* 226 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 227 */             lineStripArrayRetained.setCompiled(arrayList);
/* 228 */             this.geometryList.add(lineStripArrayRetained);
/* 229 */             lineStripArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 231 */           if (arrayOfObject2[b] != null) {
/* 232 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 233 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 234 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 8:
/* 240 */           if (arrayOfObject1[b] != null) {
/* 241 */             IndexedQuadArrayRetained indexedQuadArrayRetained = new IndexedQuadArrayRetained();
/* 242 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 243 */             indexedQuadArrayRetained.setCompiled(arrayList);
/* 244 */             this.geometryList.add(indexedQuadArrayRetained);
/* 245 */             indexedQuadArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 247 */           if (arrayOfObject2[b] != null) {
/* 248 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 249 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 250 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 9:
/* 256 */           if (arrayOfObject1[b] != null) {
/* 257 */             IndexedTriangleArrayRetained indexedTriangleArrayRetained = new IndexedTriangleArrayRetained();
/* 258 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 259 */             indexedTriangleArrayRetained.setCompiled(arrayList);
/* 260 */             this.geometryList.add(indexedTriangleArrayRetained);
/* 261 */             indexedTriangleArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 263 */           if (arrayOfObject2[b] != null) {
/* 264 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 265 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 266 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 10:
/* 272 */           if (arrayOfObject1[b] != null) {
/* 273 */             IndexedPointArrayRetained indexedPointArrayRetained = new IndexedPointArrayRetained();
/* 274 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 275 */             indexedPointArrayRetained.setCompiled(arrayList);
/* 276 */             this.geometryList.add(indexedPointArrayRetained);
/* 277 */             indexedPointArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 279 */           if (arrayOfObject2[b] != null) {
/* 280 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 281 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 282 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 11:
/* 288 */           if (arrayOfObject1[b] != null) {
/* 289 */             IndexedLineArrayRetained indexedLineArrayRetained = new IndexedLineArrayRetained();
/* 290 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 291 */             indexedLineArrayRetained.setCompiled(arrayList);
/* 292 */             this.geometryList.add(indexedLineArrayRetained);
/* 293 */             indexedLineArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 295 */           if (arrayOfObject2[b] != null) {
/* 296 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 297 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 298 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 12:
/* 304 */           if (arrayOfObject1[b] != null) {
/* 305 */             IndexedTriangleStripArrayRetained indexedTriangleStripArrayRetained = new IndexedTriangleStripArrayRetained();
/* 306 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 307 */             indexedTriangleStripArrayRetained.setCompiled(arrayList);
/* 308 */             this.geometryList.add(indexedTriangleStripArrayRetained);
/* 309 */             indexedTriangleStripArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 311 */           if (arrayOfObject2[b] != null) {
/* 312 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 313 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 314 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 13:
/* 320 */           if (arrayOfObject1[b] != null) {
/* 321 */             IndexedTriangleFanArrayRetained indexedTriangleFanArrayRetained = new IndexedTriangleFanArrayRetained();
/* 322 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 323 */             indexedTriangleFanArrayRetained.setCompiled(arrayList);
/* 324 */             this.geometryList.add(indexedTriangleFanArrayRetained);
/* 325 */             indexedTriangleFanArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 327 */           if (arrayOfObject2[b] != null) {
/* 328 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 329 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 330 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 14:
/* 336 */           if (arrayOfObject1[b] != null) {
/* 337 */             IndexedLineStripArrayRetained indexedLineStripArrayRetained = new IndexedLineStripArrayRetained();
/* 338 */             ArrayList arrayList = (ArrayList)arrayOfObject1[b];
/* 339 */             indexedLineStripArrayRetained.setCompiled(arrayList);
/* 340 */             this.geometryList.add(indexedLineStripArrayRetained);
/* 341 */             indexedLineStripArrayRetained.setSource(((SceneGraphObjectRetained)arrayList.get(0)).source);
/*     */           } 
/* 343 */           if (arrayOfObject2[b] != null) {
/* 344 */             ArrayList arrayList = (ArrayList)arrayOfObject2[b];
/* 345 */             for (byte b1 = 0; b1 < arrayList.size(); b1++) {
/* 346 */               this.geometryList.add(arrayList.get(b1));
/*     */             }
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   Bounds getCollisionBounds(int paramInt) { return this.collisionBound; }
/*     */ 
/*     */ 
/*     */   
/*     */   int numGeometries(int paramInt) {
/* 364 */     ArrayList arrayList = (ArrayList)this.geometryInfo.get(paramInt);
/* 365 */     return arrayList.size();
/*     */   }
/*     */ 
/*     */   
/*     */   Geometry getGeometry(int paramInt1, int paramInt2) {
/* 370 */     ArrayList arrayList = (ArrayList)this.geometryInfo.get(paramInt2);
/* 371 */     return (Geometry)arrayList.get(paramInt1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   Enumeration getAllGeometries(int paramInt) {
/* 377 */     ArrayList arrayList = (ArrayList)this.geometryInfo.get(paramInt);
/* 378 */     Vector vector = new Vector();
/*     */     
/* 380 */     for (byte b = 0; b < arrayList.size(); b++) {
/* 381 */       vector.add(arrayList.get(b));
/*     */     }
/*     */     
/* 384 */     return vector.elements();
/*     */   }
/*     */   
/*     */   Bounds getBounds(int paramInt) {
/* 388 */     if (this.boundsAutoCompute) {
/* 389 */       ArrayList arrayList = (ArrayList)this.geometryInfo.get(paramInt);
/*     */       
/* 391 */       if (arrayList != null) {
/* 392 */         BoundingBox boundingBox = new BoundingBox((Bounds)null);
/* 393 */         for (byte b = 0; b < arrayList.size(); b++) {
/* 394 */           Geometry geometry = (Geometry)arrayList.get(b);
/* 395 */           if (geometry != null) {
/* 396 */             GeometryRetained geometryRetained = (GeometryRetained)geometry.retained;
/* 397 */             if (geometryRetained.geoType != -1) {
/* 398 */               geometryRetained.computeBoundingBox();
/* 399 */               synchronized (geometryRetained.geoBounds) {
/* 400 */                 boundingBox.combine(geometryRetained.geoBounds);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 406 */         return boundingBox;
/*     */       } 
/*     */       
/* 409 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 413 */     return getBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(SceneGraphPath paramSceneGraphPath, PickShape paramPickShape, double[] paramArrayOfDouble) {
/* 431 */     PickInfo pickInfo = new PickInfo();
/*     */     
/* 433 */     Transform3D transform3D = paramSceneGraphPath.getTransform();
/* 434 */     if (transform3D == null) {
/* 435 */       throw new IllegalArgumentException(J3dI18N.getString("Shape3DRetained3"));
/*     */     }
/* 437 */     pickInfo.setLocalToVWorldRef(transform3D);
/*     */     
/* 439 */     Shape3D shape3D = (Shape3D)paramSceneGraphPath.getObject();
/*     */ 
/*     */     
/* 442 */     ArrayList arrayList = (ArrayList)this.geometryInfo.get(shape3D.id);
/*     */ 
/*     */     
/* 445 */     if (paramArrayOfDouble == null)
/*     */     {
/* 447 */       return intersect(pickInfo, paramPickShape, 0, arrayList);
/*     */     }
/*     */     
/* 450 */     byte b = 16;
/* 451 */     if (intersect(pickInfo, paramPickShape, b, arrayList)) {
/* 452 */       paramArrayOfDouble[0] = pickInfo.getClosestDistance();
/* 453 */       return true;
/*     */     } 
/*     */     
/* 456 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(PickInfo paramPickInfo, PickShape paramPickShape, int paramInt, ArrayList paramArrayList) {
/* 463 */     Transform3D transform3D1 = paramPickInfo.getLocalToVWorldRef();
/*     */     
/* 465 */     Transform3D transform3D2 = new Transform3D();
/* 466 */     transform3D2.invert(transform3D1);
/* 467 */     PickShape pickShape = paramPickShape.transform(transform3D2);
/*     */     
/* 469 */     int i = paramArrayList.size();
/*     */ 
/*     */     
/* 472 */     if ((paramInt & 0x8) == 0 && (paramInt & 0x10) == 0 && (paramInt & 0x20) == 0 && (paramInt & 0x40) == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 477 */       for (byte b = 0; b < i; b++) {
/* 478 */         GeometryRetained geometryRetained = (GeometryRetained)paramArrayList.get(b);
/* 479 */         if (geometryRetained != null) {
/* 480 */           if (geometryRetained.mirrorGeometry != null) {
/* 481 */             geometryRetained = geometryRetained.mirrorGeometry;
/*     */           }
/*     */ 
/*     */           
/* 485 */           if (geometryRetained.intersect(pickShape, null, 0, null, null, 0)) {
/* 486 */             return true;
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 493 */       double d = Double.POSITIVE_INFINITY;
/* 494 */       Point3d point3d1 = new Point3d();
/* 495 */       Point3d point3d2 = new Point3d();
/* 496 */       Point3d point3d3 = new Point3d();
/*     */       
/* 498 */       for (byte b = 0; b < i; b++) {
/* 499 */         GeometryRetained geometryRetained = (GeometryRetained)paramArrayList.get(b);
/* 500 */         if (geometryRetained != null) {
/* 501 */           if (geometryRetained.mirrorGeometry != null) {
/* 502 */             geometryRetained = geometryRetained.mirrorGeometry;
/*     */           }
/* 504 */           if (geometryRetained.intersect(pickShape, paramPickInfo, paramInt, point3d2, geometryRetained, b)) {
/*     */             
/* 506 */             point3d3.set(point3d2);
/* 507 */             transform3D1.transform(point3d3);
/* 508 */             double d1 = paramPickShape.distance(point3d3);
/*     */             
/* 510 */             if (d > d1) {
/* 511 */               d = d1;
/* 512 */               point3d1.set(point3d2);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 518 */       if (d < Double.POSITIVE_INFINITY) {
/* 519 */         if ((paramInt & 0x10) != 0) {
/* 520 */           paramPickInfo.setClosestDistance(d);
/*     */         }
/* 522 */         if ((paramInt & 0x8) != 0) {
/* 523 */           paramPickInfo.setClosestIntersectionPoint(point3d1);
/*     */         }
/* 525 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 529 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Shape3DCompileRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */