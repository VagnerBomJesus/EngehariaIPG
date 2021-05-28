/*      */ package com.sun.j3d.utils.picking;
/*      */ 
/*      */ import javax.media.j3d.GeometryArray;
/*      */ import javax.media.j3d.IndexedGeometryArray;
/*      */ import javax.vecmath.Color3b;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Color4b;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.TexCoord2f;
/*      */ import javax.vecmath.TexCoord3f;
/*      */ import javax.vecmath.Vector3d;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PickIntersection
/*      */ {
/*      */   PickResult pickResult;
/*      */   double distance;
/*      */   int geomIndex;
/*      */   int[] primitiveVertexIndices;
/*      */   Point3d[] primitiveCoordinatesVW;
/*      */   Point3d pointCoordinatesVW;
/*      */   GeometryArray geom;
/*      */   IndexedGeometryArray iGeom;
/*      */   boolean hasNormals;
/*      */   boolean hasColors;
/*      */   boolean hasTexCoords;
/*      */   int[] primitiveCoordinateIndices;
/*      */   int[] primitiveNormalIndices;
/*      */   int[] primitiveColorIndices;
/*      */   int[] primitiveTexCoordIndices;
/*      */   Point3d[] primitiveCoordinates;
/*      */   Vector3f[] primitiveNormals;
/*      */   Color4f[] primitiveColors;
/*      */   TexCoord3f[] primitiveTexCoords;
/*      */   Point3d pointCoordinates;
/*      */   Vector3f pointNormal;
/*      */   Color4f pointColor;
/*      */   TexCoord3f pointTexCoord;
/*      */   int closestVertexIndex;
/*      */   Point3d closestVertexCoordinates;
/*      */   Point3d closestVertexCoordinatesVW;
/*      */   double[] interpWeights;
/*      */   static final boolean debug = false;
/*      */   static final int X_AXIS = 1;
/*      */   static final int Y_AXIS = 2;
/*      */   static final int Z_AXIS = 3;
/*      */   static final double TOL = 1.0E-5D;
/*      */   
/*      */   PickIntersection(PickResult paramPickResult, GeometryArray paramGeometryArray) {
/*  124 */     this.pickResult = null;
/*      */ 
/*      */ 
/*      */     
/*  128 */     this.distance = -1.0D;
/*      */ 
/*      */     
/*  131 */     this.geomIndex = 0;
/*      */ 
/*      */     
/*  134 */     this.primitiveVertexIndices = null;
/*      */ 
/*      */     
/*  137 */     this.primitiveCoordinatesVW = null;
/*      */ 
/*      */     
/*  140 */     this.pointCoordinatesVW = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  145 */     this.geom = null;
/*  146 */     this.iGeom = null;
/*  147 */     this.hasNormals = false;
/*  148 */     this.hasColors = false;
/*  149 */     this.hasTexCoords = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  160 */     this.primitiveCoordinates = null;
/*      */ 
/*      */     
/*  163 */     this.primitiveNormals = null;
/*      */ 
/*      */     
/*  166 */     this.primitiveColors = null;
/*      */ 
/*      */     
/*  169 */     this.primitiveTexCoords = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  174 */     this.pointCoordinates = null;
/*      */ 
/*      */     
/*  177 */     this.pointNormal = null;
/*      */ 
/*      */     
/*  180 */     this.pointColor = null;
/*      */ 
/*      */     
/*  183 */     this.pointTexCoord = null;
/*      */ 
/*      */ 
/*      */     
/*  187 */     this.closestVertexIndex = -1;
/*      */ 
/*      */     
/*  190 */     this.closestVertexCoordinates = null;
/*      */ 
/*      */     
/*  193 */     this.closestVertexCoordinatesVW = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  218 */     this.pickResult = paramPickResult;
/*  219 */     this.geom = paramGeometryArray;
/*  220 */     if (this.geom == null) {
/*  221 */       GeometryArray[] arrayOfGeometryArray = this.pickResult.getGeometryArrays();
/*  222 */       this.geom = arrayOfGeometryArray[this.geomIndex];
/*      */     } 
/*      */ 
/*      */     
/*  226 */     if (this.geom instanceof IndexedGeometryArray) {
/*  227 */       this.iGeom = (IndexedGeometryArray)this.geom;
/*      */     }
/*  229 */     int i = this.geom.getVertexFormat();
/*  230 */     this.hasColors = (0 != (i & 0xC));
/*      */     
/*  232 */     this.hasNormals = (0 != (i & 0x2));
/*  233 */     this.hasTexCoords = (0 != (i & 0x60));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  242 */     String str = new String("PickIntersection: ");
/*  243 */     str = str + " pickResult = " + this.pickResult + "\n";
/*  244 */     str = str + " geomIndex = " + this.geomIndex + "\n";
/*  245 */     if (this.distance != -1.0D) str = str + " dist:" + this.distance + "\n"; 
/*  246 */     if (this.pointCoordinates != null) str = str + " pt:" + this.pointCoordinates + "\n"; 
/*  247 */     if (this.pointCoordinatesVW != null) str = str + " ptVW:" + this.pointCoordinatesVW + "\n";
/*      */     
/*  249 */     if (this.primitiveCoordinateIndices != null) {
/*  250 */       str = str + " prim coordinate ind:\n";
/*  251 */       for (byte b = 0; b < this.primitiveCoordinateIndices.length; b++) {
/*  252 */         str = str + " " + this.primitiveCoordinateIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  256 */     if (this.primitiveColorIndices != null) {
/*  257 */       str = str + " prim color ind:\n";
/*  258 */       for (byte b = 0; b < this.primitiveColorIndices.length; b++) {
/*  259 */         str = str + " " + this.primitiveColorIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  263 */     if (this.primitiveNormalIndices != null) {
/*  264 */       str = str + " prim normal ind:\n";
/*  265 */       for (byte b = 0; b < this.primitiveNormalIndices.length; b++) {
/*  266 */         str = str + " " + this.primitiveNormalIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  270 */     if (this.primitiveTexCoordIndices != null) {
/*  271 */       str = str + " prim texture ind:\n";
/*  272 */       for (byte b = 0; b < this.primitiveTexCoordIndices.length; b++) {
/*  273 */         str = str + " " + this.primitiveTexCoordIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  277 */     if (this.closestVertexCoordinates != null) {
/*  278 */       str = str + " clos. vert:" + this.closestVertexCoordinates + "\n";
/*      */     }
/*      */     
/*  281 */     if (this.closestVertexCoordinatesVW != null) {
/*  282 */       str = str + " clos. vert:" + this.closestVertexCoordinatesVW + "\n";
/*      */     }
/*      */     
/*  285 */     if (this.closestVertexIndex != -1) {
/*  286 */       str = str + " clos. vert. ind.:" + this.closestVertexIndex + "\n";
/*      */     }
/*  288 */     return str;
/*      */   }
/*      */ 
/*      */   
/*      */   String toString2() {
/*  293 */     String str = new String("PickIntersection: ");
/*      */     
/*  295 */     str = str + " geomIndex = " + this.geomIndex + "\n";
/*  296 */     if (this.distance != -1.0D) str = str + " dist:" + this.distance + "\n"; 
/*  297 */     if (this.pointCoordinates != null) str = str + " pt:" + this.pointCoordinates + "\n"; 
/*  298 */     if (this.pointCoordinatesVW != null) str = str + " ptVW:" + this.pointCoordinatesVW + "\n";
/*      */     
/*  300 */     if (this.primitiveCoordinateIndices != null) {
/*  301 */       str = str + " prim coordinate ind:\n";
/*  302 */       for (byte b = 0; b < this.primitiveCoordinateIndices.length; b++) {
/*  303 */         str = str + " " + this.primitiveCoordinateIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  307 */     if (this.primitiveColorIndices != null) {
/*  308 */       str = str + " prim color ind:\n";
/*  309 */       for (byte b = 0; b < this.primitiveColorIndices.length; b++) {
/*  310 */         str = str + " " + this.primitiveColorIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  314 */     if (this.primitiveNormalIndices != null) {
/*  315 */       str = str + " prim normal ind:\n";
/*  316 */       for (byte b = 0; b < this.primitiveNormalIndices.length; b++) {
/*  317 */         str = str + " " + this.primitiveNormalIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  321 */     if (this.primitiveTexCoordIndices != null) {
/*  322 */       str = str + " prim texture ind:\n";
/*  323 */       for (byte b = 0; b < this.primitiveTexCoordIndices.length; b++) {
/*  324 */         str = str + " " + this.primitiveTexCoordIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  328 */     if (this.closestVertexCoordinates != null) {
/*  329 */       str = str + " clos. vert:" + this.closestVertexCoordinates + "\n";
/*      */     }
/*      */     
/*  332 */     if (this.closestVertexCoordinatesVW != null) {
/*  333 */       str = str + " clos. vert:" + this.closestVertexCoordinatesVW + "\n";
/*      */     }
/*      */     
/*  336 */     if (this.closestVertexIndex != -1) {
/*  337 */       str = str + " clos. vert. ind.:" + this.closestVertexIndex + "\n";
/*      */     }
/*  339 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  346 */   PickResult getPickResult() { return this.pickResult; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setGeomIndex(int paramInt) {
/*  354 */     if (this.geomIndex != paramInt) {
/*  355 */       GeometryArray[] arrayOfGeometryArray = this.pickResult.getGeometryArrays();
/*  356 */       this.geom = arrayOfGeometryArray[paramInt];
/*      */       
/*  358 */       if (this.geom instanceof IndexedGeometryArray) {
/*  359 */         this.iGeom = (IndexedGeometryArray)this.geom;
/*      */       }
/*  361 */       int i = this.geom.getVertexFormat();
/*  362 */       this.hasColors = (0 != (i & 0xC));
/*      */       
/*  364 */       this.hasNormals = (0 != (i & 0x2));
/*  365 */       this.hasTexCoords = (0 != (i & 0x60));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  370 */     this.geomIndex = paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPointCoordinatesVW(Point3d paramPoint3d) {
/*  378 */     if (this.pointCoordinatesVW == null) {
/*  379 */       this.pointCoordinatesVW = new Point3d();
/*      */     }
/*  381 */     this.pointCoordinatesVW.x = paramPoint3d.x;
/*  382 */     this.pointCoordinatesVW.y = paramPoint3d.y;
/*  383 */     this.pointCoordinatesVW.z = paramPoint3d.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   public Point3d getPointCoordinatesVW() { return this.pointCoordinatesVW; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  401 */   public double getDistance() { return this.distance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  409 */   void setDistance(double paramDouble) { this.distance = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPrimitiveCoordinatesVW(Point3d[] paramArrayOfPoint3d) {
/*  417 */     this.primitiveCoordinatesVW = new Point3d[paramArrayOfPoint3d.length];
/*  418 */     System.arraycopy(paramArrayOfPoint3d, 0, this.primitiveCoordinatesVW, 0, paramArrayOfPoint3d.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  426 */   public Point3d[] getPrimitiveCoordinatesVW() { return this.primitiveCoordinatesVW; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setVertexIndices(int[] paramArrayOfInt) {
/*  434 */     this.primitiveVertexIndices = new int[paramArrayOfInt.length];
/*  435 */     System.arraycopy(paramArrayOfInt, 0, this.primitiveVertexIndices, 0, paramArrayOfInt.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  443 */   public int[] getPrimitiveVertexIndices() { return this.primitiveVertexIndices; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  451 */   public int getGeometryArrayIndex() { return this.geomIndex; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GeometryArray getGeometryArray() {
/*  460 */     if (this.geom == null) {
/*  461 */       GeometryArray[] arrayOfGeometryArray = this.pickResult.getGeometryArrays();
/*  462 */       this.geom = arrayOfGeometryArray[this.geomIndex];
/*  463 */       if (this.geom instanceof IndexedGeometryArray) {
/*  464 */         this.iGeom = (IndexedGeometryArray)this.geom;
/*      */       }
/*  466 */       int i = this.geom.getVertexFormat();
/*  467 */       this.hasColors = (0 != (i & 0xC));
/*      */       
/*  469 */       this.hasNormals = (0 != (i & 0x2));
/*  470 */       this.hasTexCoords = (0 != (i & 0x60));
/*      */     } 
/*      */ 
/*      */     
/*  474 */     return this.geom;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean geometryIsIndexed() {
/*  479 */     GeometryArray geometryArray = getGeometryArray();
/*  480 */     if (this.iGeom != null) {
/*  481 */       return true;
/*      */     }
/*  483 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d getClosestVertexCoordinates() {
/*  498 */     if (this.closestVertexCoordinates == null) {
/*  499 */       int i = getClosestVertexIndex();
/*  500 */       int j = this.geom.getVertexFormat();
/*      */ 
/*      */       
/*  503 */       int[] arrayOfInt = getPrimitiveCoordinateIndices();
/*  504 */       if ((j & 0x80) == 0) {
/*  505 */         this.closestVertexCoordinates = new Point3d();
/*  506 */         this.geom.getCoordinate(arrayOfInt[i], this.closestVertexCoordinates);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  513 */       else if ((j & 0x100) == 0) {
/*  514 */         double[] arrayOfDouble = this.geom.getCoordRefDouble();
/*      */         
/*  516 */         if (arrayOfDouble == null) {
/*  517 */           float[] arrayOfFloat = this.geom.getCoordRefFloat();
/*  518 */           if (arrayOfFloat == null) {
/*  519 */             Point3f[] arrayOfPoint3f = this.geom.getCoordRef3f();
/*  520 */             if (arrayOfPoint3f == null) {
/*  521 */               Point3d[] arrayOfPoint3d = this.geom.getCoordRef3d();
/*  522 */               this.closestVertexCoordinates = new Point3d((arrayOfPoint3d[arrayOfInt[i]]).x, (arrayOfPoint3d[arrayOfInt[i]]).y, (arrayOfPoint3d[arrayOfInt[i]]).z);
/*      */             } else {
/*      */               
/*  525 */               this.closestVertexCoordinates = new Point3d((arrayOfPoint3f[arrayOfInt[i]]).x, (arrayOfPoint3f[arrayOfInt[i]]).y, (arrayOfPoint3f[arrayOfInt[i]]).z);
/*      */             } 
/*      */           } else {
/*      */             
/*  529 */             int k = arrayOfInt[i] * 3;
/*  530 */             this.closestVertexCoordinates = new Point3d(arrayOfFloat[k], arrayOfFloat[k + 1], arrayOfFloat[k + 2]);
/*      */           } 
/*      */         } else {
/*      */           
/*  534 */           int k = arrayOfInt[i] * 3;
/*  535 */           this.closestVertexCoordinates = new Point3d(arrayOfDouble[k], arrayOfDouble[k + 1], arrayOfDouble[k + 2]);
/*      */         } 
/*      */       } else {
/*      */         
/*  539 */         float[] arrayOfFloat = this.geom.getInterleavedVertices();
/*  540 */         int m = getInterleavedVertexOffset(this.geom);
/*  541 */         int n = m + 3;
/*  542 */         int k = n * arrayOfInt[i] + m;
/*  543 */         this.closestVertexCoordinates = new Point3d(arrayOfFloat[k], arrayOfFloat[k + 1], arrayOfFloat[k + 2]);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  548 */     return this.closestVertexCoordinates;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d getClosestVertexCoordinatesVW() {
/*  556 */     if (this.closestVertexCoordinatesVW == null) {
/*  557 */       int i = getClosestVertexIndex();
/*  558 */       Point3d[] arrayOfPoint3d = getPrimitiveCoordinatesVW();
/*  559 */       this.closestVertexCoordinatesVW = arrayOfPoint3d[i];
/*      */     } 
/*  561 */     return this.closestVertexCoordinatesVW;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClosestVertexIndex() {
/*  568 */     if (this.closestVertexIndex == -1) {
/*  569 */       storeClosestVertex();
/*      */     }
/*  571 */     return this.closestVertexIndex;
/*      */   }
/*      */ 
/*      */   
/*      */   void storeClosestVertex() {
/*  576 */     if (this.closestVertexIndex == -1) {
/*  577 */       double d1 = Double.MAX_VALUE;
/*  578 */       double d2 = Double.MAX_VALUE;
/*  579 */       byte b1 = -1;
/*      */ 
/*      */ 
/*      */       
/*  583 */       for (byte b2 = 0; b2 < this.primitiveCoordinatesVW.length; b2++) {
/*  584 */         d2 = this.pointCoordinatesVW.distance(this.primitiveCoordinatesVW[b2]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  590 */         if (d2 < d1) {
/*  591 */           b1 = b2;
/*  592 */           d1 = d2;
/*      */         } 
/*      */       } 
/*  595 */       this.closestVertexIndex = b1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPrimitiveCoordinateIndices() {
/*  610 */     if (this.primitiveCoordinateIndices == null) {
/*  611 */       if (geometryIsIndexed()) {
/*  612 */         this.primitiveCoordinateIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/*  614 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/*  615 */           this.primitiveCoordinateIndices[b] = this.iGeom.getCoordinateIndex(this.primitiveVertexIndices[b]);
/*      */         }
/*      */       } else {
/*      */         
/*  619 */         this.primitiveCoordinateIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/*  622 */     return this.primitiveCoordinateIndices;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d[] getPrimitiveCoordinates() {
/*  630 */     if (this.primitiveCoordinates == null) {
/*  631 */       this.primitiveCoordinates = new Point3d[this.primitiveVertexIndices.length];
/*  632 */       int[] arrayOfInt = getPrimitiveCoordinateIndices();
/*  633 */       int i = this.geom.getVertexFormat();
/*      */ 
/*      */ 
/*      */       
/*  637 */       if ((i & 0x80) == 0) {
/*  638 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  639 */           this.primitiveCoordinates[b] = new Point3d();
/*      */           
/*  641 */           this.geom.getCoordinate(arrayOfInt[b], this.primitiveCoordinates[b]);
/*      */         }
/*      */       
/*      */       }
/*  645 */       else if ((i & 0x100) == 0) {
/*  646 */         double[] arrayOfDouble = this.geom.getCoordRefDouble();
/*      */         
/*  648 */         if (arrayOfDouble == null) {
/*  649 */           float[] arrayOfFloat = this.geom.getCoordRefFloat();
/*  650 */           if (arrayOfFloat == null) {
/*  651 */             Point3f[] arrayOfPoint3f = this.geom.getCoordRef3f();
/*  652 */             if (arrayOfPoint3f == null) {
/*  653 */               Point3d[] arrayOfPoint3d = this.geom.getCoordRef3d();
/*  654 */               for (byte b = 0; b < arrayOfInt.length; b++) {
/*  655 */                 this.primitiveCoordinates[b] = new Point3d((arrayOfPoint3d[arrayOfInt[b]]).x, (arrayOfPoint3d[arrayOfInt[b]]).y, (arrayOfPoint3d[arrayOfInt[b]]).z);
/*      */               }
/*      */             } else {
/*      */               
/*  659 */               for (byte b = 0; b < arrayOfInt.length; b++) {
/*  660 */                 this.primitiveCoordinates[b] = new Point3d((arrayOfPoint3f[arrayOfInt[b]]).x, (arrayOfPoint3f[arrayOfInt[b]]).y, (arrayOfPoint3f[arrayOfInt[b]]).z);
/*      */               }
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  666 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/*  667 */               int j = arrayOfInt[b] * 3;
/*  668 */               this.primitiveCoordinates[b] = new Point3d(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2]);
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/*  673 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  674 */             int j = arrayOfInt[b] * 3;
/*  675 */             this.primitiveCoordinates[b] = new Point3d(arrayOfDouble[j], arrayOfDouble[j + 1], arrayOfDouble[j + 2]);
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/*  680 */         float[] arrayOfFloat = this.geom.getInterleavedVertices();
/*  681 */         int j = getInterleavedVertexOffset(this.geom);
/*  682 */         int k = j + 3;
/*  683 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  684 */           int m = k * arrayOfInt[b] + j;
/*  685 */           this.primitiveCoordinates[b] = new Point3d(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  691 */     return this.primitiveCoordinates;
/*      */   }
/*      */   
/*      */   int getInterleavedVertexOffset(GeometryArray paramGeometryArray) {
/*  695 */     int i = 0;
/*  696 */     int j = paramGeometryArray.getVertexFormat();
/*  697 */     if ((j & 0x4) == 4) {
/*  698 */       i += true;
/*  699 */     } else if ((j & 0xC) == 12) {
/*  700 */       i += true;
/*      */     } 
/*  702 */     if ((j & 0x2) != 0)
/*  703 */       i += true; 
/*  704 */     if ((j & 0x20) == 32) {
/*  705 */       i += 2 * paramGeometryArray.getTexCoordSetCount();
/*      */     }
/*  707 */     else if ((j & 0x40) == 64) {
/*  708 */       i += 3 * paramGeometryArray.getTexCoordSetCount();
/*      */     } 
/*      */     
/*  711 */     return i;
/*      */   }
/*      */   
/*      */   int getInterleavedStride(GeometryArray paramGeometryArray) {
/*  715 */     int i = 3;
/*  716 */     int j = paramGeometryArray.getVertexFormat();
/*  717 */     if ((j & 0x4) == 4) {
/*  718 */       i += 3;
/*  719 */     } else if ((j & 0xC) == 12) {
/*  720 */       i += 4;
/*      */     } 
/*  722 */     if ((j & 0x2) != 0)
/*  723 */       i += 3; 
/*  724 */     if ((j & 0x20) == 32) {
/*  725 */       i += 2 * paramGeometryArray.getTexCoordSetCount();
/*      */     }
/*  727 */     else if ((j & 0x40) == 64) {
/*  728 */       i += 3 * paramGeometryArray.getTexCoordSetCount();
/*      */     } 
/*      */     
/*  731 */     return i;
/*      */   }
/*      */ 
/*      */   
/*      */   int getInterleavedColorOffset(GeometryArray paramGeometryArray) {
/*  736 */     int i = 0;
/*  737 */     int j = paramGeometryArray.getVertexFormat();
/*  738 */     if ((j & 0x20) == 32) {
/*  739 */       i += 2 * paramGeometryArray.getTexCoordSetCount();
/*      */     }
/*  741 */     else if ((j & 0x40) == 64) {
/*  742 */       i += 3 * paramGeometryArray.getTexCoordSetCount();
/*      */     } 
/*      */     
/*  745 */     return i;
/*      */   }
/*      */ 
/*      */   
/*      */   int getInterleavedNormalOffset(GeometryArray paramGeometryArray) {
/*  750 */     int i = 0;
/*  751 */     int j = paramGeometryArray.getVertexFormat();
/*  752 */     if ((j & 0x20) == 32) {
/*  753 */       i += 2 * paramGeometryArray.getTexCoordSetCount();
/*      */     }
/*  755 */     else if ((j & 0x40) == 64) {
/*  756 */       i += 3 * paramGeometryArray.getTexCoordSetCount();
/*      */     } 
/*  758 */     if ((j & 0x4) == 4) {
/*  759 */       i += 3;
/*  760 */     } else if ((j & 0xC) == 12) {
/*  761 */       i += 4;
/*      */     } 
/*  763 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPrimitiveNormalIndices() {
/*  776 */     if (this.hasNormals && this.primitiveNormalIndices == null) {
/*  777 */       if (geometryIsIndexed()) {
/*  778 */         this.primitiveNormalIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/*  780 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/*  781 */           this.primitiveNormalIndices[b] = this.iGeom.getNormalIndex(this.primitiveVertexIndices[b]);
/*      */         }
/*      */       } else {
/*      */         
/*  785 */         this.primitiveNormalIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/*  788 */     return this.primitiveNormalIndices;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector3f[] getPrimitiveNormals() {
/*  797 */     if (this.hasNormals && this.primitiveNormals == null) {
/*  798 */       this.primitiveNormals = new Vector3f[this.primitiveVertexIndices.length];
/*  799 */       int[] arrayOfInt = getPrimitiveNormalIndices();
/*  800 */       int i = this.geom.getVertexFormat();
/*      */ 
/*      */       
/*  803 */       if ((i & 0x80) == 0) {
/*  804 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  805 */           this.primitiveNormals[b] = new Vector3f();
/*  806 */           this.geom.getNormal(arrayOfInt[b], this.primitiveNormals[b]);
/*      */         }
/*      */       
/*      */       }
/*  810 */       else if ((i & 0x100) == 0) {
/*  811 */         float[] arrayOfFloat = this.geom.getNormalRefFloat();
/*  812 */         if (arrayOfFloat != null) {
/*  813 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  814 */             int j = arrayOfInt[b] * 3;
/*  815 */             this.primitiveNormals[b] = new Vector3f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2]);
/*      */           } 
/*      */         } else {
/*      */           
/*  819 */           Vector3f[] arrayOfVector3f = this.geom.getNormalRef3f();
/*  820 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  821 */             this.primitiveNormals[b] = new Vector3f((arrayOfVector3f[arrayOfInt[b]]).x, (arrayOfVector3f[arrayOfInt[b]]).y, (arrayOfVector3f[arrayOfInt[b]]).z);
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*  826 */         float[] arrayOfFloat = this.geom.getInterleavedVertices();
/*  827 */         int j = getInterleavedColorOffset(this.geom);
/*  828 */         int k = getInterleavedStride(this.geom);
/*  829 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  830 */           int m = k * arrayOfInt[b] + j;
/*  831 */           this.primitiveNormals[b] = new Vector3f(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  837 */     return this.primitiveNormals;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPrimitiveColorIndices() {
/*  847 */     if (this.hasColors && this.primitiveColorIndices == null) {
/*  848 */       if (geometryIsIndexed()) {
/*  849 */         this.primitiveColorIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/*  851 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/*  852 */           this.primitiveColorIndices[b] = this.iGeom.getColorIndex(this.primitiveVertexIndices[b]);
/*      */         }
/*      */       } else {
/*      */         
/*  856 */         this.primitiveColorIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/*  859 */     return this.primitiveColorIndices;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color4f[] getPrimitiveColors() {
/*  869 */     if (this.hasColors && this.primitiveColors == null) {
/*  870 */       this.primitiveColors = new Color4f[this.primitiveVertexIndices.length];
/*  871 */       int[] arrayOfInt = getPrimitiveColorIndices();
/*  872 */       int i = this.geom.getVertexFormat();
/*  873 */       if ((i & 0x80) == 0) {
/*  874 */         if ((i & 0xC) == 12) {
/*      */           
/*  876 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  877 */             this.primitiveColors[b] = new Color4f();
/*  878 */             this.geom.getColor(arrayOfInt[b], this.primitiveColors[b]);
/*      */           } 
/*      */         } else {
/*  881 */           Color3f color3f = new Color3f();
/*  882 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  883 */             this.primitiveColors[b] = new Color4f();
/*  884 */             this.geom.getColor(arrayOfInt[b], color3f);
/*  885 */             (this.primitiveColors[b]).x = color3f.x;
/*  886 */             (this.primitiveColors[b]).y = color3f.y;
/*  887 */             (this.primitiveColors[b]).z = color3f.z;
/*  888 */             (this.primitiveColors[b]).w = 1.0F;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  893 */       } else if ((i & 0x100) == 0) {
/*  894 */         float[] arrayOfFloat = this.geom.getColorRefFloat();
/*      */         
/*  896 */         if (arrayOfFloat == null) {
/*  897 */           byte[] arrayOfByte = this.geom.getColorRefByte();
/*  898 */           if (arrayOfByte == null) {
/*  899 */             Color3f[] arrayOfColor3f = this.geom.getColorRef3f();
/*  900 */             if (arrayOfColor3f == null) {
/*  901 */               Color4f[] arrayOfColor4f = this.geom.getColorRef4f();
/*  902 */               if (arrayOfColor4f == null) {
/*  903 */                 Color3b[] arrayOfColor3b = this.geom.getColorRef3b();
/*  904 */                 if (arrayOfColor3b == null) {
/*  905 */                   Color4b[] arrayOfColor4b = this.geom.getColorRef4b();
/*  906 */                   for (byte b = 0; b < arrayOfInt.length; b++) {
/*  907 */                     this.primitiveColors[b] = new Color4f((arrayOfColor4b[arrayOfInt[b]]).x, (arrayOfColor4b[arrayOfInt[b]]).y, (arrayOfColor4b[arrayOfInt[b]]).z, (arrayOfColor4b[arrayOfInt[b]]).w);
/*      */                   }
/*      */                 }
/*      */                 else {
/*      */                   
/*  912 */                   for (byte b = 0; b < arrayOfInt.length; b++) {
/*  913 */                     this.primitiveColors[b] = new Color4f((arrayOfColor3b[arrayOfInt[b]]).x, (arrayOfColor3b[arrayOfInt[b]]).y, (arrayOfColor3b[arrayOfInt[b]]).z, 1.0F);
/*      */                   }
/*      */                 }
/*      */               
/*      */               } else {
/*      */                 
/*  919 */                 for (byte b = 0; b < arrayOfInt.length; b++) {
/*  920 */                   this.primitiveColors[b] = new Color4f((arrayOfColor4f[arrayOfInt[b]]).x, (arrayOfColor4f[arrayOfInt[b]]).y, (arrayOfColor4f[arrayOfInt[b]]).z, (arrayOfColor4f[arrayOfInt[b]]).w);
/*      */                 }
/*      */               } 
/*      */             } else {
/*      */               
/*  925 */               for (byte b = 0; b < arrayOfInt.length; b++) {
/*  926 */                 this.primitiveColors[b] = new Color4f((arrayOfColor3f[arrayOfInt[b]]).x, (arrayOfColor3f[arrayOfInt[b]]).y, (arrayOfColor3f[arrayOfInt[b]]).z, 1.0F);
/*      */               
/*      */               }
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*  934 */           else if ((i & 0xC) == 12) {
/*      */             
/*  936 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/*  937 */               int j = arrayOfInt[b] << 2;
/*  938 */               this.primitiveColors[b] = new Color4f(arrayOfByte[j], arrayOfByte[j + 1], arrayOfByte[j + 2], arrayOfByte[j + 3]);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  943 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/*  944 */               int j = arrayOfInt[b] * 3;
/*  945 */               this.primitiveColors[b] = new Color4f(arrayOfByte[j], arrayOfByte[j + 1], arrayOfByte[j + 2], 1.0F);
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*  954 */         else if ((i & 0xC) == 12) {
/*      */           
/*  956 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  957 */             int j = arrayOfInt[b] << 2;
/*  958 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2], arrayOfFloat[j + 3]);
/*      */           } 
/*      */         } else {
/*      */           
/*  962 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  963 */             int j = arrayOfInt[b] * 3;
/*  964 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2], 1.0F);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  972 */         float[] arrayOfFloat = this.geom.getInterleavedVertices();
/*  973 */         int j = getInterleavedColorOffset(this.geom);
/*  974 */         int k = getInterleavedStride(this.geom);
/*  975 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  976 */           int m = k * arrayOfInt[b] + j;
/*  977 */           if ((i & 0xC) == 12) {
/*      */             
/*  979 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2], arrayOfFloat[m + 3]);
/*      */           } else {
/*      */             
/*  982 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2], 1.0F);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  988 */     return this.primitiveColors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPrimitiveTexCoordIndices(int paramInt) {
/* 1000 */     if (this.hasTexCoords && this.primitiveTexCoordIndices == null) {
/* 1001 */       if (geometryIsIndexed()) {
/* 1002 */         this.primitiveTexCoordIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/* 1004 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/* 1005 */           this.primitiveTexCoordIndices[b] = this.iGeom.getTextureCoordinateIndex(paramInt, this.primitiveVertexIndices[b]);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1010 */         this.primitiveTexCoordIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/* 1013 */     return this.primitiveTexCoordIndices;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TexCoord3f[] getPrimitiveTexCoords(int paramInt) {
/* 1026 */     if (this.primitiveTexCoords == null) {
/* 1027 */       this.primitiveTexCoords = new TexCoord3f[this.primitiveVertexIndices.length];
/* 1028 */       TexCoord2f texCoord2f = new TexCoord2f();
/*      */       
/* 1030 */       int[] arrayOfInt = getPrimitiveTexCoordIndices(paramInt);
/* 1031 */       int i = this.geom.getVertexFormat();
/* 1032 */       if ((i & 0x80) == 0) {
/* 1033 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/* 1034 */           this.primitiveTexCoords[b] = new TexCoord3f();
/* 1035 */           this.geom.getTextureCoordinate(paramInt, arrayOfInt[b], texCoord2f);
/* 1036 */           this.primitiveTexCoords[b].set(texCoord2f.x, texCoord2f.y, 0.0F);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 1043 */       else if ((i & 0x100) == 0) {
/*      */         
/* 1045 */         float[] arrayOfFloat = this.geom.getTexCoordRefFloat(paramInt);
/* 1046 */         if (arrayOfFloat != null) {
/* 1047 */           if ((i & 0x20) == 32) {
/* 1048 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/* 1049 */               int j = arrayOfInt[b] << 1;
/* 1050 */               this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[j], arrayOfFloat[j + 1], 0.0F);
/*      */             }
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 1056 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/* 1057 */               int j = arrayOfInt[b] * 3;
/* 1058 */               this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2]);
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1065 */           TexCoord2f[] arrayOfTexCoord2f = this.geom.getTexCoordRef2f(paramInt);
/* 1066 */           if (arrayOfTexCoord2f != null) {
/* 1067 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/* 1068 */               this.primitiveTexCoords[b] = new TexCoord3f((arrayOfTexCoord2f[arrayOfInt[b]]).x, (arrayOfTexCoord2f[arrayOfInt[b]]).y, 0.0F);
/*      */             
/*      */             }
/*      */           }
/*      */           else {
/*      */             
/* 1074 */             TexCoord3f[] arrayOfTexCoord3f = this.geom.getTexCoordRef3f(paramInt);
/* 1075 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/* 1076 */               this.primitiveTexCoords[b] = new TexCoord3f((arrayOfTexCoord3f[arrayOfInt[b]]).x, (arrayOfTexCoord3f[arrayOfInt[b]]).y, (arrayOfTexCoord3f[arrayOfInt[b]]).z);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         int k;
/*      */ 
/*      */ 
/*      */         
/* 1085 */         float[] arrayOfFloat = this.geom.getInterleavedVertices();
/* 1086 */         int j = getInterleavedStride(this.geom);
/*      */ 
/*      */         
/* 1089 */         if ((i & 0x20) == 32) {
/*      */           
/* 1091 */           k = paramInt << 1;
/*      */         } else {
/*      */           
/* 1094 */           k = paramInt * 3;
/*      */         } 
/* 1096 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/* 1097 */           int m = j * arrayOfInt[b];
/* 1098 */           if ((i & 0x20) == 32) {
/*      */             
/* 1100 */             this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[m + k], arrayOfFloat[m + 1 + k], 0.0F);
/*      */           } else {
/*      */             
/* 1103 */             this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[m + k], arrayOfFloat[m + 1 + k], arrayOfFloat[m + 2 + k]);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1109 */     return this.primitiveTexCoords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d getPointCoordinates() {
/* 1125 */     if (this.pointCoordinates == null) {
/* 1126 */       double[] arrayOfDouble = getInterpWeights();
/* 1127 */       Point3d[] arrayOfPoint3d = getPrimitiveCoordinates();
/* 1128 */       this.pointCoordinates = new Point3d();
/* 1129 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/* 1130 */         this.pointCoordinates.x += arrayOfDouble[b] * (arrayOfPoint3d[b]).x;
/* 1131 */         this.pointCoordinates.y += arrayOfDouble[b] * (arrayOfPoint3d[b]).y;
/* 1132 */         this.pointCoordinates.z += arrayOfDouble[b] * (arrayOfPoint3d[b]).z;
/*      */       } 
/*      */     } 
/* 1135 */     return this.pointCoordinates;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector3f getPointNormal() {
/* 1144 */     if (this.hasNormals && this.pointNormal == null) {
/* 1145 */       double[] arrayOfDouble = getInterpWeights();
/* 1146 */       Vector3f[] arrayOfVector3f = getPrimitiveNormals();
/* 1147 */       this.pointNormal = new Vector3f();
/* 1148 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/* 1149 */         this.pointNormal.x += (float)arrayOfDouble[b] * (arrayOfVector3f[b]).x;
/* 1150 */         this.pointNormal.y += (float)arrayOfDouble[b] * (arrayOfVector3f[b]).y;
/* 1151 */         this.pointNormal.z += (float)arrayOfDouble[b] * (arrayOfVector3f[b]).z;
/*      */       } 
/*      */     } 
/* 1154 */     return this.pointNormal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color4f getPointColor() {
/* 1165 */     if (this.hasColors && this.pointColor == null) {
/* 1166 */       double[] arrayOfDouble = getInterpWeights();
/* 1167 */       Color4f[] arrayOfColor4f = getPrimitiveColors();
/* 1168 */       this.pointColor = new Color4f();
/* 1169 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/* 1170 */         this.pointColor.x += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).x;
/* 1171 */         this.pointColor.y += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).y;
/* 1172 */         this.pointColor.z += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).z;
/* 1173 */         this.pointColor.w += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).w;
/*      */       } 
/*      */     } 
/* 1176 */     return this.pointColor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TexCoord3f getPointTextureCoordinate(int paramInt) {
/* 1189 */     if (this.hasTexCoords && this.pointTexCoord == null) {
/* 1190 */       double[] arrayOfDouble = getInterpWeights();
/* 1191 */       TexCoord3f[] arrayOfTexCoord3f = getPrimitiveTexCoords(paramInt);
/* 1192 */       this.pointTexCoord = new TexCoord3f();
/* 1193 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/* 1194 */         this.pointTexCoord.x += (float)arrayOfDouble[b] * (arrayOfTexCoord3f[b]).x;
/* 1195 */         this.pointTexCoord.y += (float)arrayOfDouble[b] * (arrayOfTexCoord3f[b]).y;
/* 1196 */         this.pointTexCoord.z += (float)arrayOfDouble[b] * (arrayOfTexCoord3f[b]).z;
/*      */       } 
/*      */     } 
/* 1199 */     return this.pointTexCoord;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double abs(double paramDouble) {
/* 1209 */     if (paramDouble < 0.0D) {
/* 1210 */       return -paramDouble;
/*      */     }
/* 1212 */     return paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int maxAxis(Vector3d paramVector3d) {
/* 1220 */     byte b = 1;
/* 1221 */     double d = abs(paramVector3d.x);
/* 1222 */     if (abs(paramVector3d.y) > d) {
/* 1223 */       b = 2;
/* 1224 */       d = abs(paramVector3d.y);
/*      */     } 
/* 1226 */     if (abs(paramVector3d.z) > d) {
/* 1227 */       b = 3;
/*      */     }
/* 1229 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean interpTriangle(int paramInt1, int paramInt2, int paramInt3, Point3d[] paramArrayOfPoint3d, Point3d paramPoint3d) {
/*      */     double d6, d5;
/*      */     int m, k, j;
/* 1271 */     Vector3d vector3d1 = new Vector3d();
/* 1272 */     Vector3d vector3d2 = new Vector3d();
/* 1273 */     Vector3d vector3d3 = new Vector3d();
/* 1274 */     vector3d1.sub(paramArrayOfPoint3d[paramInt2], paramArrayOfPoint3d[paramInt1]);
/* 1275 */     vector3d2.sub(paramArrayOfPoint3d[paramInt3], paramArrayOfPoint3d[paramInt1]);
/* 1276 */     vector3d3.sub(paramArrayOfPoint3d[paramInt3], paramArrayOfPoint3d[paramInt2]);
/* 1277 */     double d1 = vector3d1.lengthSquared();
/* 1278 */     double d2 = vector3d2.lengthSquared();
/* 1279 */     double d3 = vector3d3.lengthSquared();
/* 1280 */     Vector3d vector3d4 = vector3d1;
/* 1281 */     double d4 = d1;
/* 1282 */     if (d2 > d4) {
/* 1283 */       vector3d4 = vector3d2;
/* 1284 */       d4 = d2;
/*      */     } 
/* 1286 */     if (d3 > d4) {
/* 1287 */       vector3d4 = vector3d3;
/*      */     }
/* 1289 */     int i = maxAxis(vector3d4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1306 */     double[] arrayOfDouble = new double[3];
/*      */     
/* 1308 */     arrayOfDouble[0] = getInterpFactorForBase(paramPoint3d, paramArrayOfPoint3d[paramInt2], paramArrayOfPoint3d[paramInt3], i);
/*      */     
/* 1310 */     arrayOfDouble[1] = getInterpFactorForBase(paramPoint3d, paramArrayOfPoint3d[paramInt3], paramArrayOfPoint3d[paramInt1], i);
/*      */     
/* 1312 */     arrayOfDouble[2] = getInterpFactorForBase(paramPoint3d, paramArrayOfPoint3d[paramInt1], paramArrayOfPoint3d[paramInt2], i);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1338 */     if (arrayOfDouble[0] < 0.0D || arrayOfDouble[0] > 1.0D) {
/* 1339 */       j = paramInt1;
/* 1340 */       m = paramInt2;
/* 1341 */       k = paramInt3;
/* 1342 */       d6 = arrayOfDouble[2];
/* 1343 */       d5 = 1.0D - arrayOfDouble[1];
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1348 */     else if (arrayOfDouble[1] < 0.0D || arrayOfDouble[1] > 1.0D) {
/* 1349 */       j = paramInt2;
/* 1350 */       m = paramInt3;
/* 1351 */       k = paramInt1;
/* 1352 */       d6 = arrayOfDouble[0];
/* 1353 */       d5 = 1.0D - arrayOfDouble[2];
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1359 */       j = paramInt3;
/* 1360 */       m = paramInt1;
/* 1361 */       k = paramInt2;
/* 1362 */       d6 = arrayOfDouble[1];
/* 1363 */       d5 = 1.0D - arrayOfDouble[0];
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1375 */     Point3d point3d1 = new Point3d(d5 * (paramArrayOfPoint3d[k]).x + (1.0D - d5) * (paramArrayOfPoint3d[j]).x, d5 * (paramArrayOfPoint3d[k]).y + (1.0D - d5) * (paramArrayOfPoint3d[j]).y, d5 * (paramArrayOfPoint3d[k]).z + (1.0D - d5) * (paramArrayOfPoint3d[j]).z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1382 */     Point3d point3d2 = new Point3d(d6 * (paramArrayOfPoint3d[m]).x + (1.0D - d6) * (paramArrayOfPoint3d[j]).x, d6 * (paramArrayOfPoint3d[m]).y + (1.0D - d6) * (paramArrayOfPoint3d[j]).y, d6 * (paramArrayOfPoint3d[m]).z + (1.0D - d6) * (paramArrayOfPoint3d[j]).z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1395 */     vector3d1.sub(point3d1, point3d2);
/* 1396 */     int n = maxAxis(vector3d1);
/* 1397 */     double d7 = getInterpFactor(paramPoint3d, point3d2, point3d1, n);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1414 */     if (d7 < 0.0D) {
/*      */       
/* 1416 */       if (d7 + 1.0E-5D >= 0.0D)
/*      */       {
/* 1418 */         d7 = 0.0D;
/*      */       }
/*      */       else
/*      */       {
/* 1422 */         return false;
/*      */       }
/*      */     
/* 1425 */     } else if (d7 > 1.0D) {
/*      */       
/* 1427 */       if (d7 - 1.0E-5D <= 1.0D) {
/*      */         
/* 1429 */         d7 = 1.0D;
/*      */       }
/*      */       else {
/*      */         
/* 1433 */         return false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1438 */     this.interpWeights[j] = 1.0D - d7 * d5 - d6 + d7 * d6;
/*      */     
/* 1440 */     this.interpWeights[k] = d7 * d5;
/* 1441 */     this.interpWeights[m] = d6 - d7 * d6;
/* 1442 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   double[] getInterpWeights() {
/*      */     Vector3d vector3d;
/*      */     int i;
/*      */     double d;
/* 1451 */     Point3d point3d = getPointCoordinatesVW();
/* 1452 */     Point3d[] arrayOfPoint3d = getPrimitiveCoordinatesVW();
/*      */ 
/*      */ 
/*      */     
/* 1456 */     if (this.interpWeights != null) {
/* 1457 */       return this.interpWeights;
/*      */     }
/*      */     
/* 1460 */     this.interpWeights = new double[arrayOfPoint3d.length];
/*      */ 
/*      */     
/* 1463 */     switch (arrayOfPoint3d.length) {
/*      */       
/*      */       case 1:
/* 1466 */         this.interpWeights[0] = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1491 */         return this.interpWeights;case 2: vector3d = new Vector3d(); vector3d.sub(arrayOfPoint3d[1], arrayOfPoint3d[0]); i = maxAxis(vector3d); d = getInterpFactor(point3d, arrayOfPoint3d[1], arrayOfPoint3d[0], i); this.interpWeights[0] = d; this.interpWeights[1] = 1.0D - d; return this.interpWeights;case 3: if (!interpTriangle(0, 1, 2, arrayOfPoint3d, point3d)) throw new RuntimeException("Interp point outside triangle");  return this.interpWeights;case 4: if (!interpTriangle(0, 1, 2, arrayOfPoint3d, point3d) && !interpTriangle(0, 2, 3, arrayOfPoint3d, point3d)) throw new RuntimeException("Interp point outside quad");  return this.interpWeights;
/*      */     } 
/*      */     throw new RuntimeException("Unexpected number of points.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static float getInterpFactor(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, int paramInt) {
/*      */     float f;
/* 1503 */     switch (paramInt) {
/*      */       case 1:
/* 1505 */         if (paramPoint3d2.x == paramPoint3d3.x) {
/*      */           
/* 1507 */           f = 0.0F;
/*      */         } else {
/* 1509 */           f = (float)((paramPoint3d2.x - paramPoint3d1.x) / (paramPoint3d2.x - paramPoint3d3.x));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1528 */         return f;case 2: if (paramPoint3d2.y == paramPoint3d3.y) { f = 0.0F; } else { f = (float)((paramPoint3d2.y - paramPoint3d1.y) / (paramPoint3d2.y - paramPoint3d3.y)); }  return f;case 3: if (paramPoint3d2.z == paramPoint3d3.z) { f = 0.0F; } else { f = (float)((paramPoint3d2.z - paramPoint3d1.z) / (paramPoint3d2.z - paramPoint3d3.z)); }  return f;
/*      */     } 
/*      */     throw new RuntimeException("invalid axis parameter " + paramInt + " (must be 0-2)");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static float getInterpFactorForBase(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, int paramInt) {
/*      */     float f;
/* 1541 */     switch (paramInt) {
/*      */       case 1:
/* 1543 */         if (paramPoint3d2.x == paramPoint3d3.x) {
/* 1544 */           f = Float.MAX_VALUE;
/*      */         } else {
/* 1546 */           f = (float)((paramPoint3d2.x - paramPoint3d1.x) / (paramPoint3d2.x - paramPoint3d3.x));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1563 */         return f;case 2: if (paramPoint3d2.y == paramPoint3d3.y) { f = Float.MAX_VALUE; } else { f = (float)((paramPoint3d2.y - paramPoint3d1.y) / (paramPoint3d2.y - paramPoint3d3.y)); }  return f;case 3: if (paramPoint3d2.z == paramPoint3d3.z) { f = Float.MAX_VALUE; } else { f = (float)((paramPoint3d2.z - paramPoint3d1.z) / (paramPoint3d2.z - paramPoint3d3.z)); }  return f;
/*      */     } 
/*      */     throw new RuntimeException("invalid axis parameter " + paramInt + " (must be 0-2)");
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\picking\PickIntersection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */