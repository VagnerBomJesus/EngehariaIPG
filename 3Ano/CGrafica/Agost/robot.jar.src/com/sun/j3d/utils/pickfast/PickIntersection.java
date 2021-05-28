/*      */ package com.sun.j3d.utils.pickfast;
/*      */ 
/*      */ import javax.media.j3d.Geometry;
/*      */ import javax.media.j3d.GeometryArray;
/*      */ import javax.media.j3d.IndexedGeometryArray;
/*      */ import javax.media.j3d.PickInfo;
/*      */ import javax.media.j3d.Transform3D;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point3d;
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
/*      */   private double[] interpWeights;
/*      */   private static final boolean debug = false;
/*      */   private static final int X_AXIS = 1;
/*      */   private static final int Y_AXIS = 2;
/*      */   private static final int Z_AXIS = 3;
/*      */   static final double TOL = 1.0E-5D;
/*      */   private PickInfo.IntersectionInfo iInfo;
/*      */   private Transform3D l2vw;
/*      */   private Geometry geometry;
/*      */   private boolean geometryIsIndexed;
/*      */   private double distance;
/*      */   private boolean hasColors;
/*      */   private boolean hasNormals;
/*      */   private boolean hasTexCoords;
/*      */   private int[] primitiveCoordinateIndices;
/*      */   private int[] primitiveNormalIndices;
/*      */   private int[] primitiveColorIndices;
/*      */   private int[] primitiveTexCoordIndices;
/*      */   private int[] primitiveVertexIndices;
/*      */   private Point3d[] primitiveCoordinates;
/*      */   private Point3d[] primitiveCoordinatesVW;
/*      */   private Vector3f[] primitiveNormals;
/*      */   private Color4f[] primitiveColors;
/*      */   private TexCoord3f[] primitiveTexCoords;
/*      */   private Point3d pointCoordinatesVW;
/*      */   private Point3d pointCoordinates;
/*      */   private Vector3f pointNormal;
/*      */   private Color4f pointColor;
/*      */   private TexCoord3f pointTexCoord;
/*      */   private int closestVertexIndex;
/*      */   private Point3d closestVertexCoordinates;
/*      */   private Point3d closestVertexCoordinatesVW;
/*      */   
/*      */   public PickIntersection(Transform3D paramTransform3D, PickInfo.IntersectionInfo paramIntersectionInfo) {
/*  135 */     this.iInfo = null;
/*  136 */     this.l2vw = null;
/*  137 */     this.geometry = null;
/*  138 */     this.geometryIsIndexed = false;
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
/*  153 */     this.primitiveVertexIndices = null;
/*      */ 
/*      */     
/*  156 */     this.primitiveCoordinates = null;
/*      */ 
/*      */     
/*  159 */     this.primitiveCoordinatesVW = null;
/*      */ 
/*      */     
/*  162 */     this.primitiveNormals = null;
/*      */ 
/*      */     
/*  165 */     this.primitiveColors = null;
/*      */ 
/*      */     
/*  168 */     this.primitiveTexCoords = null;
/*      */ 
/*      */ 
/*      */     
/*  172 */     this.pointCoordinatesVW = null;
/*      */ 
/*      */     
/*  175 */     this.pointCoordinates = null;
/*      */ 
/*      */     
/*  178 */     this.pointNormal = null;
/*      */ 
/*      */     
/*  181 */     this.pointColor = null;
/*      */ 
/*      */     
/*  184 */     this.pointTexCoord = null;
/*      */ 
/*      */ 
/*      */     
/*  188 */     this.closestVertexIndex = -1;
/*      */ 
/*      */     
/*  191 */     this.closestVertexCoordinates = null;
/*      */ 
/*      */     
/*  194 */     this.closestVertexCoordinatesVW = null;
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
/*  207 */     this.l2vw = paramTransform3D;
/*      */     
/*  209 */     this.iInfo = paramIntersectionInfo;
/*      */     
/*  211 */     this.geometry = this.iInfo.getGeometry();
/*      */     
/*  213 */     this.pointCoordinates = this.iInfo.getIntersectionPoint();
/*  214 */     this.distance = this.iInfo.getDistance();
/*  215 */     this.primitiveVertexIndices = this.iInfo.getVertexIndices();
/*      */     
/*  217 */     if (this.geometry instanceof GeometryArray) {
/*      */       
/*  219 */       int i = ((GeometryArray)this.geometry).getVertexFormat();
/*  220 */       this.hasColors = (0 != (i & 0xC));
/*      */       
/*  222 */       this.hasNormals = (0 != (i & 0x2));
/*  223 */       this.hasTexCoords = (0 != (i & 0x60));
/*      */ 
/*      */ 
/*      */       
/*  227 */       if (this.geometry instanceof IndexedGeometryArray) {
/*  228 */         this.geometryIsIndexed = true;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  238 */   public boolean geometryIsIndexed() { return this.geometryIsIndexed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d getClosestVertexCoordinates() {
/*  248 */     GeometryArray geometryArray = (GeometryArray)this.geometry;
/*      */     
/*  250 */     if (this.closestVertexCoordinates == null) {
/*  251 */       int i = getClosestVertexIndex();
/*  252 */       int j = geometryArray.getVertexFormat();
/*      */ 
/*      */       
/*  255 */       int[] arrayOfInt = getPrimitiveCoordinateIndices();
/*  256 */       if ((j & 0x80) == 0) {
/*  257 */         this.closestVertexCoordinates = new Point3d();
/*  258 */         geometryArray.getCoordinate(arrayOfInt[i], this.closestVertexCoordinates);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  264 */       else if ((j & 0x100) == 0) {
/*  265 */         double[] arrayOfDouble = geometryArray.getCoordRefDouble();
/*      */         
/*  267 */         if (arrayOfDouble == null) {
/*  268 */           float[] arrayOfFloat = geometryArray.getCoordRefFloat();
/*  269 */           if (arrayOfFloat == null) {
/*  270 */             throw new UnsupportedOperationException("Deprecated : BY_REF - p3f and p3d");
/*      */           }
/*      */           
/*  273 */           int k = arrayOfInt[i] * 3;
/*  274 */           this.closestVertexCoordinates = new Point3d(arrayOfFloat[k], arrayOfFloat[k + 1], arrayOfFloat[k + 2]);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  280 */           int k = arrayOfInt[i] * 3;
/*  281 */           this.closestVertexCoordinates = new Point3d(arrayOfDouble[k], arrayOfDouble[k + 1], arrayOfDouble[k + 2]);
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  287 */         float[] arrayOfFloat = geometryArray.getInterleavedVertices();
/*  288 */         int m = getInterleavedVertexOffset(geometryArray);
/*  289 */         int n = m + 3;
/*  290 */         int k = n * arrayOfInt[i] + m;
/*  291 */         this.closestVertexCoordinates = new Point3d(arrayOfFloat[k], arrayOfFloat[k + 1], arrayOfFloat[k + 2]);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  298 */     return this.closestVertexCoordinates;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d getClosestVertexCoordinatesVW() {
/*  307 */     if (this.closestVertexCoordinatesVW == null) {
/*  308 */       int i = getClosestVertexIndex();
/*  309 */       Point3d[] arrayOfPoint3d = getPrimitiveCoordinatesVW();
/*  310 */       this.closestVertexCoordinatesVW = arrayOfPoint3d[i];
/*      */     } 
/*  312 */     return this.closestVertexCoordinatesVW;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClosestVertexIndex() {
/*  320 */     if (this.closestVertexIndex == -1) {
/*  321 */       double d1 = Double.MAX_VALUE;
/*  322 */       double d2 = Double.MAX_VALUE;
/*  323 */       byte b1 = -1;
/*  324 */       this.primitiveCoordinates = getPrimitiveCoordinates();
/*      */       
/*  326 */       assert this.primitiveCoordinates != null;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  331 */       for (byte b2 = 0; b2 < this.primitiveCoordinates.length; b2++) {
/*  332 */         d2 = this.pointCoordinates.distance(this.primitiveCoordinates[b2]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  339 */         if (d2 < d1) {
/*  340 */           b1 = b2;
/*  341 */           d1 = d2;
/*      */         } 
/*      */       } 
/*  344 */       this.closestVertexIndex = b1;
/*      */     } 
/*  346 */     return this.closestVertexIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  354 */   public double getDistance() { return this.distance; }
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
/*      */   public Color4f getPointColor() {
/*  366 */     if (this.hasColors && this.pointColor == null) {
/*  367 */       double[] arrayOfDouble = getInterpWeights();
/*  368 */       Color4f[] arrayOfColor4f = getPrimitiveColors();
/*  369 */       this.pointColor = new Color4f();
/*  370 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/*  371 */         this.pointColor.x += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).x;
/*  372 */         this.pointColor.y += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).y;
/*  373 */         this.pointColor.z += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).z;
/*  374 */         this.pointColor.w += (float)arrayOfDouble[b] * (arrayOfColor4f[b]).w;
/*      */       } 
/*      */     } 
/*  377 */     return this.pointColor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  387 */   public Point3d getPointCoordinates() { return this.pointCoordinates; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d getPointCoordinatesVW() {
/*  397 */     if (this.pointCoordinatesVW != null) {
/*  398 */       return this.pointCoordinatesVW;
/*      */     }
/*      */     
/*  401 */     this.pointCoordinatesVW = new Point3d();
/*      */     
/*  403 */     this.pointCoordinatesVW.x = this.pointCoordinates.x;
/*  404 */     this.pointCoordinatesVW.y = this.pointCoordinates.y;
/*  405 */     this.pointCoordinatesVW.z = this.pointCoordinates.z;
/*      */     
/*  407 */     this.l2vw.transform(this.pointCoordinatesVW);
/*  408 */     return this.pointCoordinatesVW;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector3f getPointNormal() {
/*  417 */     if (this.hasNormals && this.pointNormal == null) {
/*  418 */       double[] arrayOfDouble = getInterpWeights();
/*  419 */       Vector3f[] arrayOfVector3f = getPrimitiveNormals();
/*  420 */       this.pointNormal = new Vector3f();
/*  421 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/*  422 */         this.pointNormal.x += (float)arrayOfDouble[b] * (arrayOfVector3f[b]).x;
/*  423 */         this.pointNormal.y += (float)arrayOfDouble[b] * (arrayOfVector3f[b]).y;
/*  424 */         this.pointNormal.z += (float)arrayOfDouble[b] * (arrayOfVector3f[b]).z;
/*      */       } 
/*      */     } 
/*  427 */     return this.pointNormal;
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
/*  440 */     if (this.hasTexCoords && this.pointTexCoord == null) {
/*  441 */       double[] arrayOfDouble = getInterpWeights();
/*  442 */       TexCoord3f[] arrayOfTexCoord3f = getPrimitiveTexCoords(paramInt);
/*  443 */       this.pointTexCoord = new TexCoord3f();
/*  444 */       for (byte b = 0; b < arrayOfDouble.length; b++) {
/*  445 */         this.pointTexCoord.x += (float)arrayOfDouble[b] * (arrayOfTexCoord3f[b]).x;
/*  446 */         this.pointTexCoord.y += (float)arrayOfDouble[b] * (arrayOfTexCoord3f[b]).y;
/*  447 */         this.pointTexCoord.z += (float)arrayOfDouble[b] * (arrayOfTexCoord3f[b]).z;
/*      */       } 
/*      */     } 
/*  450 */     return this.pointTexCoord;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPrimitiveColorIndices() {
/*  460 */     if (this.hasColors && this.primitiveColorIndices == null) {
/*  461 */       if (geometryIsIndexed()) {
/*  462 */         this.primitiveColorIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/*  464 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/*  465 */           this.primitiveColorIndices[b] = ((IndexedGeometryArray)this.geometry).getColorIndex(this.primitiveVertexIndices[b]);
/*      */         }
/*      */       } else {
/*      */         
/*  469 */         this.primitiveColorIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/*  472 */     return this.primitiveColorIndices;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color4f[] getPrimitiveColors() {
/*  483 */     GeometryArray geometryArray = (GeometryArray)this.geometry;
/*      */     
/*  485 */     if (this.hasColors && this.primitiveColors == null) {
/*  486 */       this.primitiveColors = new Color4f[this.primitiveVertexIndices.length];
/*  487 */       int[] arrayOfInt = getPrimitiveColorIndices();
/*  488 */       int i = geometryArray.getVertexFormat();
/*  489 */       if ((i & 0x80) == 0) {
/*  490 */         if ((i & 0xC) == 12) {
/*      */           
/*  492 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  493 */             this.primitiveColors[b] = new Color4f();
/*  494 */             geometryArray.getColor(arrayOfInt[b], this.primitiveColors[b]);
/*      */           } 
/*      */         } else {
/*  497 */           Color3f color3f = new Color3f();
/*  498 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  499 */             this.primitiveColors[b] = new Color4f();
/*  500 */             geometryArray.getColor(arrayOfInt[b], color3f);
/*  501 */             (this.primitiveColors[b]).x = color3f.x;
/*  502 */             (this.primitiveColors[b]).y = color3f.y;
/*  503 */             (this.primitiveColors[b]).z = color3f.z;
/*  504 */             (this.primitiveColors[b]).w = 1.0F;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  509 */       } else if ((i & 0x100) == 0) {
/*  510 */         float[] arrayOfFloat = geometryArray.getColorRefFloat();
/*      */         
/*  512 */         if (arrayOfFloat == null) {
/*  513 */           byte[] arrayOfByte = geometryArray.getColorRefByte();
/*  514 */           if (arrayOfByte == null) {
/*  515 */             throw new UnsupportedOperationException("Deprecated : BY_REF - c3b and c3f");
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  520 */           if ((i & 0xC) == 12)
/*      */           {
/*  522 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/*  523 */               int j = arrayOfInt[b] << 2;
/*  524 */               this.primitiveColors[b] = new Color4f(arrayOfByte[j], arrayOfByte[j + 1], arrayOfByte[j + 2], arrayOfByte[j + 3]);
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*      */           else
/*      */           {
/*  532 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/*  533 */               int j = arrayOfInt[b] * 3;
/*  534 */               this.primitiveColors[b] = new Color4f(arrayOfByte[j], arrayOfByte[j + 1], arrayOfByte[j + 2], 1.0F);
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  546 */         else if ((i & 0xC) == 12) {
/*      */           
/*  548 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  549 */             int j = arrayOfInt[b] << 2;
/*  550 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2], arrayOfFloat[j + 3]);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  557 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  558 */             int j = arrayOfInt[b] * 3;
/*  559 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2], 1.0F);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  570 */         float[] arrayOfFloat = geometryArray.getInterleavedVertices();
/*  571 */         int j = getInterleavedColorOffset(geometryArray);
/*  572 */         int k = getInterleavedStride(geometryArray);
/*  573 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  574 */           int m = k * arrayOfInt[b] + j;
/*  575 */           if ((i & 0xC) == 12) {
/*      */             
/*  577 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2], arrayOfFloat[m + 3]);
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/*  583 */             this.primitiveColors[b] = new Color4f(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2], 1.0F);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  592 */     return this.primitiveColors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPrimitiveCoordinateIndices() {
/*  601 */     if (this.primitiveCoordinateIndices == null) {
/*  602 */       if (geometryIsIndexed()) {
/*  603 */         this.primitiveCoordinateIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/*  605 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/*  606 */           this.primitiveCoordinateIndices[b] = ((IndexedGeometryArray)this.geometry).getCoordinateIndex(this.primitiveVertexIndices[b]);
/*      */         }
/*      */       } else {
/*      */         
/*  610 */         this.primitiveCoordinateIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/*  613 */     return this.primitiveCoordinateIndices;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d[] getPrimitiveCoordinates() {
/*  622 */     GeometryArray geometryArray = (GeometryArray)this.geometry;
/*      */     
/*  624 */     if (this.primitiveCoordinates == null) {
/*  625 */       this.primitiveCoordinates = new Point3d[this.primitiveVertexIndices.length];
/*  626 */       int[] arrayOfInt = getPrimitiveCoordinateIndices();
/*  627 */       int i = geometryArray.getVertexFormat();
/*      */ 
/*      */ 
/*      */       
/*  631 */       if ((i & 0x80) == 0) {
/*  632 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  633 */           this.primitiveCoordinates[b] = new Point3d();
/*      */           
/*  635 */           geometryArray.getCoordinate(arrayOfInt[b], this.primitiveCoordinates[b]);
/*      */         }
/*      */       
/*      */       }
/*  639 */       else if ((i & 0x100) == 0) {
/*  640 */         double[] arrayOfDouble = geometryArray.getCoordRefDouble();
/*      */         
/*  642 */         if (arrayOfDouble == null) {
/*  643 */           float[] arrayOfFloat = geometryArray.getCoordRefFloat();
/*  644 */           if (arrayOfFloat == null) {
/*  645 */             throw new UnsupportedOperationException("Deprecated : BY_REF - c3f and c3d");
/*      */           }
/*      */           
/*  648 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  649 */             int j = arrayOfInt[b] * 3;
/*  650 */             this.primitiveCoordinates[b] = new Point3d(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2]);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  657 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  658 */             int j = arrayOfInt[b] * 3;
/*  659 */             this.primitiveCoordinates[b] = new Point3d(arrayOfDouble[j], arrayOfDouble[j + 1], arrayOfDouble[j + 2]);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  666 */         float[] arrayOfFloat = geometryArray.getInterleavedVertices();
/*  667 */         int j = getInterleavedVertexOffset(geometryArray);
/*  668 */         int k = j + 3;
/*  669 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  670 */           int m = k * arrayOfInt[b] + j;
/*  671 */           this.primitiveCoordinates[b] = new Point3d(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  679 */     return this.primitiveCoordinates;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d[] getPrimitiveCoordinatesVW() {
/*  690 */     if (this.primitiveCoordinatesVW == null) {
/*      */       
/*  692 */       Point3d[] arrayOfPoint3d = getPrimitiveCoordinates();
/*      */       
/*  694 */       this.primitiveCoordinatesVW = new Point3d[arrayOfPoint3d.length];
/*  695 */       for (byte b = 0; b < arrayOfPoint3d.length; b++) {
/*  696 */         this.primitiveCoordinatesVW[b] = new Point3d();
/*      */         
/*  698 */         (this.primitiveCoordinatesVW[b]).x = (arrayOfPoint3d[b]).x;
/*  699 */         (this.primitiveCoordinatesVW[b]).y = (arrayOfPoint3d[b]).y;
/*  700 */         (this.primitiveCoordinatesVW[b]).z = (arrayOfPoint3d[b]).z;
/*      */         
/*  702 */         this.l2vw.transform(this.primitiveCoordinatesVW[b]);
/*      */       } 
/*      */     } 
/*  705 */     return this.primitiveCoordinatesVW;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPrimitiveNormalIndices() {
/*  715 */     if (this.hasNormals && this.primitiveNormalIndices == null) {
/*  716 */       if (geometryIsIndexed()) {
/*  717 */         this.primitiveNormalIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/*  719 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/*  720 */           this.primitiveNormalIndices[b] = ((IndexedGeometryArray)this.geometry).getNormalIndex(this.primitiveVertexIndices[b]);
/*      */         }
/*      */       } else {
/*      */         
/*  724 */         this.primitiveNormalIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/*  727 */     return this.primitiveNormalIndices;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector3f[] getPrimitiveNormals() {
/*  737 */     GeometryArray geometryArray = (GeometryArray)this.geometry;
/*      */     
/*  739 */     if (this.hasNormals && this.primitiveNormals == null) {
/*  740 */       this.primitiveNormals = new Vector3f[this.primitiveVertexIndices.length];
/*  741 */       int[] arrayOfInt = getPrimitiveNormalIndices();
/*  742 */       int i = geometryArray.getVertexFormat();
/*      */ 
/*      */       
/*  745 */       if ((i & 0x80) == 0) {
/*  746 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  747 */           this.primitiveNormals[b] = new Vector3f();
/*  748 */           geometryArray.getNormal(arrayOfInt[b], this.primitiveNormals[b]);
/*      */         }
/*      */       
/*      */       }
/*  752 */       else if ((i & 0x100) == 0) {
/*  753 */         float[] arrayOfFloat = geometryArray.getNormalRefFloat();
/*  754 */         if (arrayOfFloat != null) {
/*  755 */           for (byte b = 0; b < arrayOfInt.length; b++) {
/*  756 */             int j = arrayOfInt[b] * 3;
/*  757 */             this.primitiveNormals[b] = new Vector3f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2]);
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  763 */           throw new UnsupportedOperationException("Deprecated : BY_REF - n3f");
/*      */         } 
/*      */       } else {
/*      */         
/*  767 */         float[] arrayOfFloat = geometryArray.getInterleavedVertices();
/*  768 */         int j = getInterleavedColorOffset(geometryArray);
/*  769 */         int k = getInterleavedStride(geometryArray);
/*  770 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  771 */           int m = k * arrayOfInt[b] + j;
/*  772 */           this.primitiveNormals[b] = new Vector3f(arrayOfFloat[m], arrayOfFloat[m + 1], arrayOfFloat[m + 2]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  778 */     return this.primitiveNormals;
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
/*      */   public int[] getPrimitiveTexCoordIndices(int paramInt) {
/*  791 */     if (this.hasTexCoords && this.primitiveTexCoordIndices == null) {
/*  792 */       if (geometryIsIndexed()) {
/*  793 */         this.primitiveTexCoordIndices = new int[this.primitiveVertexIndices.length];
/*      */         
/*  795 */         for (byte b = 0; b < this.primitiveVertexIndices.length; b++) {
/*  796 */           this.primitiveTexCoordIndices[b] = ((IndexedGeometryArray)this.geometry).getTextureCoordinateIndex(paramInt, this.primitiveVertexIndices[b]);
/*      */         }
/*      */       } else {
/*      */         
/*  800 */         this.primitiveTexCoordIndices = this.primitiveVertexIndices;
/*      */       } 
/*      */     }
/*  803 */     return this.primitiveTexCoordIndices;
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
/*      */   public TexCoord3f[] getPrimitiveTexCoords(int paramInt) {
/*  817 */     GeometryArray geometryArray = (GeometryArray)this.geometry;
/*      */     
/*  819 */     if (this.primitiveTexCoords == null) {
/*  820 */       this.primitiveTexCoords = new TexCoord3f[this.primitiveVertexIndices.length];
/*  821 */       int[] arrayOfInt = getPrimitiveTexCoordIndices(paramInt);
/*  822 */       int i = geometryArray.getVertexFormat();
/*  823 */       if ((i & 0x80) == 0) {
/*  824 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  825 */           this.primitiveTexCoords[b] = new TexCoord3f();
/*  826 */           geometryArray.getTextureCoordinate(paramInt, arrayOfInt[b], this.primitiveTexCoords[b]);
/*      */         }
/*      */       
/*      */       }
/*  830 */       else if ((i & 0x100) == 0) {
/*      */         
/*  832 */         float[] arrayOfFloat = geometryArray.getTexCoordRefFloat(paramInt);
/*  833 */         if (arrayOfFloat != null) {
/*  834 */           if ((i & 0x20) == 32) {
/*  835 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/*  836 */               int j = arrayOfInt[b] << 1;
/*  837 */               this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[j], arrayOfFloat[j + 1], 0.0F);
/*      */             }
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  843 */             for (byte b = 0; b < arrayOfInt.length; b++) {
/*  844 */               int j = arrayOfInt[b] * 3;
/*  845 */               this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[j], arrayOfFloat[j + 1], arrayOfFloat[j + 2]);
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  852 */           throw new UnsupportedOperationException("Deprecated : BY_REF - t2f and t3f");
/*      */         } 
/*      */       } else {
/*      */         int k;
/*  856 */         float[] arrayOfFloat = geometryArray.getInterleavedVertices();
/*  857 */         int j = getInterleavedStride(geometryArray);
/*      */ 
/*      */         
/*  860 */         if ((i & 0x20) == 32) {
/*      */           
/*  862 */           k = paramInt << 1;
/*      */         } else {
/*      */           
/*  865 */           k = paramInt * 3;
/*      */         } 
/*  867 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  868 */           int m = j * arrayOfInt[b];
/*  869 */           if ((i & 0x20) == 32) {
/*      */             
/*  871 */             this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[m + k], arrayOfFloat[m + 1 + k], 0.0F);
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/*  877 */             this.primitiveTexCoords[b] = new TexCoord3f(arrayOfFloat[m + k], arrayOfFloat[m + 1 + k], arrayOfFloat[m + 2 + k]);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  886 */     return this.primitiveTexCoords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  894 */   public int[] getPrimitiveVertexIndices() { return this.primitiveVertexIndices; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  901 */   public PickInfo.IntersectionInfo getIntersectionInfo() { return this.iInfo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  908 */     String str = new String("PickIntersection: ");
/*  909 */     str = str + " IntersectionInfo = " + this.iInfo + "\n";
/*  910 */     str = str + " geometry = " + this.geometry + "\n";
/*  911 */     if (this.distance != -1.0D) str = str + " dist:" + this.distance + "\n"; 
/*  912 */     if (this.pointCoordinates != null) str = str + " pt:" + this.pointCoordinates + "\n"; 
/*  913 */     if (this.pointCoordinatesVW != null) str = str + " ptVW:" + this.pointCoordinatesVW + "\n";
/*      */     
/*  915 */     if (this.primitiveCoordinateIndices != null) {
/*  916 */       str = str + " prim coordinate ind:\n";
/*  917 */       for (byte b = 0; b < this.primitiveCoordinateIndices.length; b++) {
/*  918 */         str = str + " " + this.primitiveCoordinateIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  922 */     if (this.primitiveColorIndices != null) {
/*  923 */       str = str + " prim color ind:\n";
/*  924 */       for (byte b = 0; b < this.primitiveColorIndices.length; b++) {
/*  925 */         str = str + " " + this.primitiveColorIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  929 */     if (this.primitiveNormalIndices != null) {
/*  930 */       str = str + " prim normal ind:\n";
/*  931 */       for (byte b = 0; b < this.primitiveNormalIndices.length; b++) {
/*  932 */         str = str + " " + this.primitiveNormalIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  936 */     if (this.primitiveTexCoordIndices != null) {
/*  937 */       str = str + " prim texture ind:\n";
/*  938 */       for (byte b = 0; b < this.primitiveTexCoordIndices.length; b++) {
/*  939 */         str = str + " " + this.primitiveTexCoordIndices[b] + "\n";
/*      */       }
/*      */     } 
/*      */     
/*  943 */     if (this.closestVertexCoordinates != null) {
/*  944 */       str = str + " clos. vert:" + this.closestVertexCoordinates + "\n";
/*      */     }
/*      */     
/*  947 */     if (this.closestVertexCoordinatesVW != null) {
/*  948 */       str = str + " clos. vert:" + this.closestVertexCoordinatesVW + "\n";
/*      */     }
/*      */     
/*  951 */     if (this.closestVertexIndex != -1) {
/*  952 */       str = str + " clos. vert. ind.:" + this.closestVertexIndex + "\n";
/*      */     }
/*  954 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getInterleavedVertexOffset(GeometryArray paramGeometryArray) {
/*  962 */     int i = 0;
/*  963 */     int j = paramGeometryArray.getVertexFormat();
/*  964 */     if ((j & 0x4) == 4) {
/*  965 */       i += true;
/*  966 */     } else if ((j & 0xC) == 12) {
/*  967 */       i += true;
/*      */     } 
/*  969 */     if ((j & 0x2) != 0)
/*  970 */       i += true; 
/*  971 */     if ((j & 0x20) == 32) {
/*  972 */       i += 2 * paramGeometryArray.getTexCoordSetCount();
/*      */     }
/*  974 */     else if ((j & 0x40) == 64) {
/*  975 */       i += 3 * paramGeometryArray.getTexCoordSetCount();
/*      */     } 
/*      */     
/*  978 */     return i;
/*      */   }
/*      */   
/*      */   int getInterleavedStride(GeometryArray paramGeometryArray) {
/*  982 */     int i = 3;
/*  983 */     int j = paramGeometryArray.getVertexFormat();
/*  984 */     if ((j & 0x4) == 4) {
/*  985 */       i += 3;
/*  986 */     } else if ((j & 0xC) == 12) {
/*  987 */       i += 4;
/*      */     } 
/*  989 */     if ((j & 0x2) != 0)
/*  990 */       i += 3; 
/*  991 */     if ((j & 0x20) == 32) {
/*  992 */       i += 2 * paramGeometryArray.getTexCoordSetCount();
/*      */     }
/*  994 */     else if ((j & 0x40) == 64) {
/*  995 */       i += 3 * paramGeometryArray.getTexCoordSetCount();
/*      */     } 
/*      */     
/*  998 */     return i;
/*      */   }
/*      */   
/*      */   int getInterleavedColorOffset(GeometryArray paramGeometryArray) {
/* 1002 */     int i = 0;
/* 1003 */     int j = paramGeometryArray.getVertexFormat();
/* 1004 */     if ((j & 0x20) == 32) {
/* 1005 */       i += 2 * paramGeometryArray.getTexCoordSetCount();
/*      */     }
/* 1007 */     else if ((j & 0x40) == 64) {
/* 1008 */       i += 3 * paramGeometryArray.getTexCoordSetCount();
/*      */     } 
/*      */     
/* 1011 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double abs(double paramDouble) {
/* 1022 */     if (paramDouble < 0.0D) {
/* 1023 */       return -paramDouble;
/*      */     }
/* 1025 */     return paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int maxAxis(Vector3d paramVector3d) {
/* 1032 */     byte b = 1;
/* 1033 */     double d = abs(paramVector3d.x);
/* 1034 */     if (abs(paramVector3d.y) > d) {
/* 1035 */       b = 2;
/* 1036 */       d = abs(paramVector3d.y);
/*      */     } 
/* 1038 */     if (abs(paramVector3d.z) > d) {
/* 1039 */       b = 3;
/*      */     }
/* 1041 */     return b;
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
/* 1083 */     Vector3d vector3d1 = new Vector3d();
/* 1084 */     Vector3d vector3d2 = new Vector3d();
/* 1085 */     Vector3d vector3d3 = new Vector3d();
/* 1086 */     vector3d1.sub(paramArrayOfPoint3d[paramInt2], paramArrayOfPoint3d[paramInt1]);
/* 1087 */     vector3d2.sub(paramArrayOfPoint3d[paramInt3], paramArrayOfPoint3d[paramInt1]);
/* 1088 */     vector3d3.sub(paramArrayOfPoint3d[paramInt3], paramArrayOfPoint3d[paramInt2]);
/* 1089 */     double d1 = vector3d1.lengthSquared();
/* 1090 */     double d2 = vector3d2.lengthSquared();
/* 1091 */     double d3 = vector3d3.lengthSquared();
/* 1092 */     Vector3d vector3d4 = vector3d1;
/* 1093 */     double d4 = d1;
/* 1094 */     if (d2 > d4) {
/* 1095 */       vector3d4 = vector3d2;
/* 1096 */       d4 = d2;
/*      */     } 
/* 1098 */     if (d3 > d4) {
/* 1099 */       vector3d4 = vector3d3;
/*      */     }
/* 1101 */     int i = maxAxis(vector3d4);
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
/* 1118 */     double[] arrayOfDouble = new double[3];
/*      */     
/* 1120 */     arrayOfDouble[0] = getInterpFactorForBase(paramPoint3d, paramArrayOfPoint3d[paramInt2], paramArrayOfPoint3d[paramInt3], i);
/*      */     
/* 1122 */     arrayOfDouble[1] = getInterpFactorForBase(paramPoint3d, paramArrayOfPoint3d[paramInt3], paramArrayOfPoint3d[paramInt1], i);
/*      */     
/* 1124 */     arrayOfDouble[2] = getInterpFactorForBase(paramPoint3d, paramArrayOfPoint3d[paramInt1], paramArrayOfPoint3d[paramInt2], i);
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
/* 1150 */     if (arrayOfDouble[0] < 0.0D || arrayOfDouble[0] > 1.0D) {
/* 1151 */       j = paramInt1;
/* 1152 */       m = paramInt2;
/* 1153 */       k = paramInt3;
/* 1154 */       d6 = arrayOfDouble[2];
/* 1155 */       d5 = 1.0D - arrayOfDouble[1];
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1160 */     else if (arrayOfDouble[1] < 0.0D || arrayOfDouble[1] > 1.0D) {
/* 1161 */       j = paramInt2;
/* 1162 */       m = paramInt3;
/* 1163 */       k = paramInt1;
/* 1164 */       d6 = arrayOfDouble[0];
/* 1165 */       d5 = 1.0D - arrayOfDouble[2];
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1171 */       j = paramInt3;
/* 1172 */       m = paramInt1;
/* 1173 */       k = paramInt2;
/* 1174 */       d6 = arrayOfDouble[1];
/* 1175 */       d5 = 1.0D - arrayOfDouble[0];
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
/* 1187 */     Point3d point3d1 = new Point3d(d5 * (paramArrayOfPoint3d[k]).x + (1.0D - d5) * (paramArrayOfPoint3d[j]).x, d5 * (paramArrayOfPoint3d[k]).y + (1.0D - d5) * (paramArrayOfPoint3d[j]).y, d5 * (paramArrayOfPoint3d[k]).z + (1.0D - d5) * (paramArrayOfPoint3d[j]).z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1194 */     Point3d point3d2 = new Point3d(d6 * (paramArrayOfPoint3d[m]).x + (1.0D - d6) * (paramArrayOfPoint3d[j]).x, d6 * (paramArrayOfPoint3d[m]).y + (1.0D - d6) * (paramArrayOfPoint3d[j]).y, d6 * (paramArrayOfPoint3d[m]).z + (1.0D - d6) * (paramArrayOfPoint3d[j]).z);
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
/* 1207 */     vector3d1.sub(point3d1, point3d2);
/* 1208 */     int n = maxAxis(vector3d1);
/* 1209 */     double d7 = getInterpFactor(paramPoint3d, point3d2, point3d1, n);
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
/* 1226 */     if (d7 < 0.0D) {
/*      */       
/* 1228 */       if (d7 + 1.0E-5D >= 0.0D)
/*      */       {
/* 1230 */         d7 = 0.0D;
/*      */       }
/*      */       else
/*      */       {
/* 1234 */         return false;
/*      */       }
/*      */     
/* 1237 */     } else if (d7 > 1.0D) {
/*      */       
/* 1239 */       if (d7 - 1.0E-5D <= 1.0D) {
/*      */         
/* 1241 */         d7 = 1.0D;
/*      */       }
/*      */       else {
/*      */         
/* 1245 */         return false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1250 */     this.interpWeights[j] = 1.0D - d7 * d5 - d6 + d7 * d6;
/*      */     
/* 1252 */     this.interpWeights[k] = d7 * d5;
/* 1253 */     this.interpWeights[m] = d6 - d7 * d6;
/* 1254 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   double[] getInterpWeights() {
/*      */     Vector3d vector3d;
/*      */     int i;
/*      */     double d;
/* 1263 */     Point3d point3d = getPointCoordinates();
/* 1264 */     Point3d[] arrayOfPoint3d = getPrimitiveCoordinates();
/*      */ 
/*      */ 
/*      */     
/* 1268 */     if (this.interpWeights != null) {
/* 1269 */       return this.interpWeights;
/*      */     }
/*      */     
/* 1272 */     this.interpWeights = new double[arrayOfPoint3d.length];
/*      */ 
/*      */     
/* 1275 */     switch (arrayOfPoint3d.length) {
/*      */       
/*      */       case 1:
/* 1278 */         this.interpWeights[0] = 1.0D;
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
/* 1303 */         return this.interpWeights;case 2: vector3d = new Vector3d(); vector3d.sub(arrayOfPoint3d[1], arrayOfPoint3d[0]); i = maxAxis(vector3d); d = getInterpFactor(point3d, arrayOfPoint3d[1], arrayOfPoint3d[0], i); this.interpWeights[0] = d; this.interpWeights[1] = 1.0D - d; return this.interpWeights;case 3: if (!interpTriangle(0, 1, 2, arrayOfPoint3d, point3d)) throw new RuntimeException("Interp point outside triangle");  return this.interpWeights;case 4: if (!interpTriangle(0, 1, 2, arrayOfPoint3d, point3d) && !interpTriangle(0, 2, 3, arrayOfPoint3d, point3d)) throw new RuntimeException("Interp point outside quad");  return this.interpWeights;
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
/* 1315 */     switch (paramInt) {
/*      */       case 1:
/* 1317 */         if (paramPoint3d2.x == paramPoint3d3.x) {
/*      */           
/* 1319 */           f = 0.0F;
/*      */         } else {
/* 1321 */           f = (float)((paramPoint3d2.x - paramPoint3d1.x) / (paramPoint3d2.x - paramPoint3d3.x));
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
/* 1340 */         return f;case 2: if (paramPoint3d2.y == paramPoint3d3.y) { f = 0.0F; } else { f = (float)((paramPoint3d2.y - paramPoint3d1.y) / (paramPoint3d2.y - paramPoint3d3.y)); }  return f;case 3: if (paramPoint3d2.z == paramPoint3d3.z) { f = 0.0F; } else { f = (float)((paramPoint3d2.z - paramPoint3d1.z) / (paramPoint3d2.z - paramPoint3d3.z)); }  return f;
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
/* 1353 */     switch (paramInt) {
/*      */       case 1:
/* 1355 */         if (paramPoint3d2.x == paramPoint3d3.x) {
/* 1356 */           f = Float.MAX_VALUE;
/*      */         } else {
/* 1358 */           f = (float)((paramPoint3d2.x - paramPoint3d1.x) / (paramPoint3d2.x - paramPoint3d3.x));
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
/* 1375 */         return f;case 2: if (paramPoint3d2.y == paramPoint3d3.y) { f = Float.MAX_VALUE; } else { f = (float)((paramPoint3d2.y - paramPoint3d1.y) / (paramPoint3d2.y - paramPoint3d3.y)); }  return f;case 3: if (paramPoint3d2.z == paramPoint3d3.z) { f = Float.MAX_VALUE; } else { f = (float)((paramPoint3d2.z - paramPoint3d1.z) / (paramPoint3d2.z - paramPoint3d3.z)); }  return f;
/*      */     } 
/*      */     throw new RuntimeException("invalid axis parameter " + paramInt + " (must be 0-2)");
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\pickfast\PickIntersection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */