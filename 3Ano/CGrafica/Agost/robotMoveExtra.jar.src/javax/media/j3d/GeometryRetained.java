/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ abstract class GeometryRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int GEO_TYPE_NONE = -1;
/*     */   static final int GEO_TYPE_QUAD_SET = 1;
/*     */   static final int GEO_TYPE_TRI_SET = 2;
/*     */   static final int GEO_TYPE_POINT_SET = 3;
/*     */   static final int GEO_TYPE_LINE_SET = 4;
/*     */   static final int GEO_TYPE_TRI_STRIP_SET = 5;
/*     */   static final int GEO_TYPE_TRI_FAN_SET = 6;
/*     */   static final int GEO_TYPE_LINE_STRIP_SET = 7;
/*     */   static final int GEO_TYPE_INDEXED_QUAD_SET = 8;
/*     */   static final int GEO_TYPE_INDEXED_TRI_SET = 9;
/*     */   static final int GEO_TYPE_INDEXED_POINT_SET = 10;
/*     */   static final int GEO_TYPE_INDEXED_LINE_SET = 11;
/*     */   static final int GEO_TYPE_INDEXED_TRI_STRIP_SET = 12;
/*     */   static final int GEO_TYPE_INDEXED_TRI_FAN_SET = 13;
/*     */   static final int GEO_TYPE_INDEXED_LINE_STRIP_SET = 14;
/*     */   static final int GEO_TYPE_RASTER = 15;
/*     */   static final int GEO_TYPE_TEXT3D = 16;
/*     */   static final int GEO_TYPE_COMPRESSED = 17;
/*     */   static final int GEO_TYPE_TOTAL = 17;
/*     */   static final int GEO_TYPE_GEOMETRYARRAY = 14;
/*  45 */   BoundingBox geoBounds = new BoundingBox();
/*     */ 
/*     */ 
/*     */   
/*     */   boolean boundsDirty = true;
/*     */ 
/*     */   
/*  52 */   int computeGeoBounds = 0;
/*     */ 
/*     */   
/*  55 */   int geoType = -1;
/*     */ 
/*     */   
/*  58 */   int nativeId = -1;
/*     */ 
/*     */   
/*  61 */   int isDirty = 65535;
/*     */ 
/*     */ 
/*     */   
/*  65 */   GeometryLock geomLock = new GeometryLock();
/*     */ 
/*     */   
/*  68 */   Object liveStateLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   GeometryRetained mirrorGeometry = null;
/*     */ 
/*     */   
/*     */   boolean isEditable = true;
/*     */ 
/*     */   
/*  79 */   ArrayList universeList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   ArrayList<ArrayList<Shape3DRetained>> userLists = new ArrayList();
/*     */   
/*     */   boolean noAlpha = false;
/*     */   
/*     */   static final double EPSILON = 1.0E-6D;
/*     */   
/*  90 */   Point3d centroid = new Point3d();
/*     */   
/*     */   boolean recompCentroid = true;
/*     */   
/*  94 */   int cachedChangedFrequent = 0;
/*     */   static final int POINT_TYPE = 1;
/*     */   static final int LINE_TYPE = 2;
/*     */   static final int TRIANGLE_TYPE = 3;
/*     */   static final int QUAD_TYPE = 4;
/*     */   static final int RASTER_TYPE = 5;
/*     */   static final int TEXT3D_TYPE = 6;
/*     */   static final int COMPRESS_TYPE = 7;
/*     */   
/*     */   abstract void update();
/*     */   
/*     */   boolean isEquivalenceClass(GeometryRetained paramGeometryRetained) {
/* 106 */     int i = getClassType();
/* 107 */     int j = paramGeometryRetained.getClassType();
/*     */     
/* 109 */     if (i == 4) {
/* 110 */       i = 3;
/*     */     }
/* 112 */     if (j == 4) {
/* 113 */       j = 3;
/*     */     }
/* 115 */     return (i == j);
/*     */   }
/*     */   
/*     */   void incrComputeGeoBounds() {
/* 119 */     synchronized (this.geoBounds) {
/* 120 */       this.computeGeoBounds++;
/*     */       
/* 122 */       if (this.computeGeoBounds == 1 && this.source.isLive()) {
/* 123 */         computeBoundingBox();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   void decrComputeGeoBounds() {
/* 129 */     synchronized (this.geoBounds) {
/* 130 */       this.computeGeoBounds--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addUser(Shape3DRetained paramShape3DRetained) {
/* 140 */     if (paramShape3DRetained.sourceNode.boundsAutoCompute) {
/* 141 */       incrComputeGeoBounds();
/*     */     }
/*     */ 
/*     */     
/* 145 */     if (this instanceof GeometryArrayRetained && (
/* 146 */       (GeometryArrayRetained)this).isWriteStatic()) {
/*     */       return;
/*     */     }
/*     */     
/* 150 */     synchronized (this.universeList) {
/* 151 */       if (this.universeList.contains(paramShape3DRetained.universe)) {
/* 152 */         int i = this.universeList.indexOf(paramShape3DRetained.universe);
/* 153 */         ArrayList arrayList = (ArrayList)this.userLists.get(i);
/* 154 */         arrayList.add(paramShape3DRetained);
/*     */       } else {
/* 156 */         this.universeList.add(paramShape3DRetained.universe);
/* 157 */         ArrayList arrayList = new ArrayList();
/* 158 */         arrayList.add(paramShape3DRetained);
/* 159 */         this.userLists.add(arrayList);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeUser(Shape3DRetained paramShape3DRetained) {
/* 170 */     if (paramShape3DRetained.sourceNode.boundsAutoCompute) {
/* 171 */       decrComputeGeoBounds();
/*     */     }
/*     */     
/* 174 */     if (this instanceof GeometryArrayRetained && (
/* 175 */       (GeometryArrayRetained)this).isWriteStatic()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 180 */     synchronized (this.universeList) {
/* 181 */       int i = this.universeList.indexOf(paramShape3DRetained.universe);
/* 182 */       ArrayList arrayList = (ArrayList)this.userLists.get(i);
/* 183 */       arrayList.remove(arrayList.indexOf(paramShape3DRetained));
/* 184 */       if (arrayList.size() == 0) {
/* 185 */         this.userLists.remove(i);
/* 186 */         this.universeList.remove(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 193 */   public void updateObject() { update(); }
/*     */ 
/*     */   
/*     */   abstract void computeBoundingBox();
/*     */ 
/*     */   
/*     */   void setLive(boolean paramBoolean, int paramInt) {
/* 200 */     doSetLive(paramBoolean, paramInt);
/* 201 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void doSetLive(boolean paramBoolean, int paramInt) {
/* 209 */     super.doSetLive(paramBoolean, paramInt);
/* 210 */     update();
/* 211 */     computeBoundingBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void execute(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, int paramInt, boolean paramBoolean3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   int getVertexFormat() { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2);
/*     */ 
/*     */   
/*     */   abstract boolean intersect(Bounds paramBounds);
/*     */ 
/*     */   
/*     */   abstract boolean intersect(Point3d[] paramArrayOfPoint3d);
/*     */ 
/*     */   
/*     */   abstract boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained);
/*     */ 
/*     */   
/*     */   void storeInterestData(PickInfo paramPickInfo, int paramInt1, GeometryRetained paramGeometryRetained, int paramInt2, int[] paramArrayOfInt, Point3d paramPoint3d, double paramDouble) {
/* 244 */     PickInfo.IntersectionInfo intersectionInfo = null;
/*     */     
/* 246 */     if ((paramInt1 & 0x20) != 0) {
/* 247 */       PickInfo.IntersectionInfo[] arrayOfIntersectionInfo = paramPickInfo.getIntersectionInfos();
/* 248 */       if (arrayOfIntersectionInfo == null || arrayOfIntersectionInfo.length == 0) {
/* 249 */         intersectionInfo = paramPickInfo.createIntersectionInfo();
/* 250 */         paramPickInfo.insertIntersectionInfo(intersectionInfo);
/*     */       } else {
/*     */         
/* 253 */         assert arrayOfIntersectionInfo.length == 1;
/* 254 */         intersectionInfo = arrayOfIntersectionInfo[0];
/*     */       }
/*     */     
/* 257 */     } else if ((paramInt1 & 0x40) != 0) {
/* 258 */       intersectionInfo = paramPickInfo.createIntersectionInfo();
/* 259 */       paramPickInfo.insertIntersectionInfo(intersectionInfo);
/*     */     } else {
/*     */       assert false;
/*     */     } 
/*     */ 
/*     */     
/* 265 */     intersectionInfo.setGeometry((Geometry)paramGeometryRetained.source);
/*     */     
/* 267 */     intersectionInfo.setGeometryIndex(paramInt2);
/* 268 */     intersectionInfo.setDistance(paramDouble);
/* 269 */     intersectionInfo.setIntersectionPoint(paramPoint3d);
/* 270 */     intersectionInfo.setVertexIndices(paramArrayOfInt);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D1, Transform3D paramTransform3D2, GeometryRetained paramGeometryRetained) {
/* 275 */     Transform3D transform3D = new Transform3D();
/* 276 */     transform3D.invert(paramTransform3D2);
/* 277 */     transform3D.mul(paramTransform3D1);
/* 278 */     return intersect(transform3D, paramGeometryRetained);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, Bounds paramBounds) {
/* 283 */     Bounds bounds = (Bounds)paramBounds.clone();
/*     */     
/* 285 */     Transform3D transform3D = new Transform3D();
/* 286 */     transform3D.invert(paramTransform3D);
/* 287 */     bounds.transform(transform3D);
/* 288 */     return intersect(bounds);
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
/*     */   boolean canBeInDisplayList(boolean paramBoolean) {
/* 301 */     if (!VirtualUniverse.mc.isDisplayList) {
/* 302 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 314 */     if (this.isEditable) {
/* 315 */       if (this.cachedChangedFrequent != 0) {
/* 316 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 321 */       if (!VirtualUniverse.mc.buildDisplayListIfPossible) {
/* 322 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 326 */     if (this instanceof GeometryArrayRetained) {
/* 327 */       int i = ((GeometryArrayRetained)this).vertexFormat;
/*     */ 
/*     */ 
/*     */       
/* 331 */       if ((i & 0x1000) != 0 && !VirtualUniverse.mc.vertexAttrsInDisplayList)
/*     */       {
/* 333 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 338 */       if (paramBoolean && (i & 0x4) != 0) {
/* 339 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 344 */       if ((i & 0x80) != 0) {
/* 345 */         if (!VirtualUniverse.mc.buildDisplayListIfPossible) {
/* 346 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 353 */         if ((i & 0x800) != 0) {
/* 354 */           return false;
/*     */         }
/*     */         
/* 357 */         if ((i & 0x200) != 0) {
/* 358 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 362 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 367 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 372 */   void computeCentroid() { this.centroid.set(this.geoBounds.getCenter()); }
/*     */   
/*     */   abstract int getClassType();
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\GeometryRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */