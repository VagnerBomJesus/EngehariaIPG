/*      */ package com.sun.j3d.utils.geometry;
/*      */ 
/*      */ import com.sun.j3d.internal.ByteBufferWrapper;
/*      */ import com.sun.j3d.internal.ByteOrderWrapper;
/*      */ import com.sun.j3d.internal.FloatBufferWrapper;
/*      */ import com.sun.j3d.internal.J3dUtilsI18N;
/*      */ import java.util.HashMap;
/*      */ import javax.media.j3d.GeometryArray;
/*      */ import javax.media.j3d.IndexedGeometryArray;
/*      */ import javax.media.j3d.IndexedQuadArray;
/*      */ import javax.media.j3d.IndexedTriangleArray;
/*      */ import javax.media.j3d.IndexedTriangleFanArray;
/*      */ import javax.media.j3d.IndexedTriangleStripArray;
/*      */ import javax.media.j3d.QuadArray;
/*      */ import javax.media.j3d.TriangleArray;
/*      */ import javax.media.j3d.TriangleFanArray;
/*      */ import javax.media.j3d.TriangleStripArray;
/*      */ import javax.vecmath.Color3b;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Color4b;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point2f;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.TexCoord2f;
/*      */ import javax.vecmath.TexCoord3f;
/*      */ import javax.vecmath.TexCoord4f;
/*      */ import javax.vecmath.Tuple2f;
/*      */ import javax.vecmath.Tuple3f;
/*      */ import javax.vecmath.Tuple4f;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GeometryInfo
/*      */ {
/*      */   public static final int TRIANGLE_ARRAY = 1;
/*      */   public static final int QUAD_ARRAY = 2;
/*      */   public static final int TRIANGLE_FAN_ARRAY = 3;
/*      */   public static final int TRIANGLE_STRIP_ARRAY = 4;
/*      */   public static final int POLYGON_ARRAY = 5;
/*      */   private int prim;
/*      */   private static final int DEBUG = 0;
/*  173 */   private Point3f[] coordinates = null;
/*  174 */   private Color3f[] colors3 = null;
/*  175 */   private Color4f[] colors4 = null;
/*  176 */   private Vector3f[] normals = null;
/*  177 */   private Object[][] texCoordSets = (Object[][])null;
/*      */   
/*  179 */   private int[] coordinateIndices = null;
/*  180 */   private int[] colorIndices = null;
/*  181 */   private int[] normalIndices = null;
/*  182 */   private int[][] texCoordIndexSets = (int[][])null;
/*      */   
/*  184 */   private int[] texCoordSetMap = null;
/*  185 */   private int texCoordSetCount = 0;
/*  186 */   private int texCoordDim = 0;
/*      */   
/*  188 */   private int[] stripCounts = null;
/*  189 */   private int[] contourCounts = null;
/*      */   
/*  191 */   private Triangulator tr = null;
/*  192 */   private NormalGenerator ng = null;
/*      */   
/*  194 */   private int oldPrim = 0;
/*  195 */   private int[] oldStripCounts = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean coordOnly = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GeometryInfo(int paramInt) {
/*  212 */     if (paramInt >= 1 && paramInt <= 5) {
/*  213 */       this.prim = paramInt;
/*      */     } else {
/*  215 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo0"));
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  233 */   public GeometryInfo(GeometryArray paramGeometryArray) { GeometryInfoGenerator.create(this, paramGeometryArray); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset(int paramInt) {
/*  249 */     if (paramInt >= 1 && paramInt <= 5) {
/*  250 */       this.prim = paramInt;
/*      */     } else {
/*  252 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo0"));
/*      */     } 
/*      */ 
/*      */     
/*  256 */     this.coordinates = null;
/*  257 */     this.colors3 = null;
/*  258 */     this.colors4 = null;
/*  259 */     this.normals = null;
/*      */     
/*  261 */     this.coordinateIndices = null;
/*  262 */     this.colorIndices = null;
/*  263 */     this.normalIndices = null;
/*      */     
/*  265 */     this.stripCounts = null;
/*  266 */     this.contourCounts = null;
/*      */     
/*  268 */     this.oldPrim = 0;
/*  269 */     this.oldStripCounts = null;
/*      */     
/*  271 */     this.texCoordDim = 0;
/*  272 */     this.texCoordSetCount = 0;
/*  273 */     this.texCoordSets = (Object[][])null;
/*  274 */     this.texCoordIndexSets = (int[][])null;
/*  275 */     this.texCoordSetMap = null;
/*      */     
/*  277 */     this.coordOnly = false;
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
/*  289 */   public void reset(GeometryArray paramGeometryArray) { GeometryInfoGenerator.create(this, paramGeometryArray); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] expandQuad(int[] paramArrayOfInt) {
/*  299 */     int[] arrayOfInt = new int[paramArrayOfInt.length / 4 * 6];
/*      */     
/*  301 */     for (byte b = 0; b < paramArrayOfInt.length / 4; b++) {
/*  302 */       arrayOfInt[b * 6 + 0] = paramArrayOfInt[b * 4];
/*  303 */       arrayOfInt[b * 6 + 1] = paramArrayOfInt[b * 4 + 1];
/*  304 */       arrayOfInt[b * 6 + 2] = paramArrayOfInt[b * 4 + 2];
/*  305 */       arrayOfInt[b * 6 + 3] = paramArrayOfInt[b * 4];
/*  306 */       arrayOfInt[b * 6 + 4] = paramArrayOfInt[b * 4 + 2];
/*  307 */       arrayOfInt[b * 6 + 5] = paramArrayOfInt[b * 4 + 3];
/*      */     } 
/*      */     
/*  310 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] expandTriFan(int paramInt, int[] paramArrayOfInt) {
/*  320 */     int[] arrayOfInt = new int[paramInt * 3];
/*  321 */     byte b1 = 0;
/*  322 */     int i = 0;
/*  323 */     for (byte b2 = 0; b2 < this.stripCounts.length; b2++) {
/*  324 */       for (byte b = 0; b < this.stripCounts[b2] - 2; b++) {
/*  325 */         arrayOfInt[b1++] = paramArrayOfInt[i];
/*  326 */         arrayOfInt[b1++] = paramArrayOfInt[i + b + true];
/*  327 */         arrayOfInt[b1++] = paramArrayOfInt[i + b + 2];
/*      */       } 
/*  329 */       i += this.stripCounts[b2];
/*      */     } 
/*  331 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] expandTriStrip(int paramInt, int[] paramArrayOfInt) {
/*  341 */     int[] arrayOfInt = new int[paramInt * 3];
/*      */     
/*  343 */     byte b1 = 0;
/*  344 */     int i = 0;
/*  345 */     for (byte b2 = 0; b2 < this.stripCounts.length; b2++) {
/*  346 */       for (byte b = 0; b < this.stripCounts[b2] - 2; b++) {
/*      */ 
/*      */ 
/*      */         
/*  350 */         if (b % 2 == 0) {
/*  351 */           arrayOfInt[b1++] = paramArrayOfInt[i + b + false];
/*  352 */           arrayOfInt[b1++] = paramArrayOfInt[i + b + 1];
/*  353 */           arrayOfInt[b1++] = paramArrayOfInt[i + b + 2];
/*      */         } else {
/*  355 */           arrayOfInt[b1++] = paramArrayOfInt[i + b + 0];
/*  356 */           arrayOfInt[b1++] = paramArrayOfInt[i + b + 2];
/*  357 */           arrayOfInt[b1++] = paramArrayOfInt[i + b + 1];
/*      */         } 
/*      */       } 
/*  360 */       i += this.stripCounts[b2];
/*      */     } 
/*      */     
/*  363 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void rememberOldPrim() {
/*  374 */     this.oldPrim = this.prim;
/*  375 */     this.oldStripCounts = this.stripCounts;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  384 */   int getOldPrim() { return this.oldPrim; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void forgetOldPrim() {
/*  398 */     this.oldPrim = 0;
/*  399 */     this.oldStripCounts = null;
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
/*      */   private void changeBackToOldPrim() {
/*  413 */     if (this.oldPrim != 0) {
/*  414 */       convertToIndexedTriangles();
/*  415 */       if (this.ng == null) this.ng = new NormalGenerator(); 
/*  416 */       this.ng.convertBackToOldPrim(this, this.oldPrim, this.oldStripCounts);
/*  417 */       this.oldPrim = 0;
/*  418 */       this.oldStripCounts = null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void convertToIndexedTriangles() {
/*      */     byte b;
/*  440 */     int i = 0;
/*      */ 
/*      */     
/*  443 */     indexify();
/*      */     
/*  445 */     if (this.prim == 1)
/*      */       return; 
/*  447 */     switch (this.prim) {
/*      */ 
/*      */       
/*      */       case 2:
/*  451 */         this.coordinateIndices = expandQuad(this.coordinateIndices);
/*  452 */         if (this.colorIndices != null) this.colorIndices = expandQuad(this.colorIndices); 
/*  453 */         if (this.normalIndices != null)
/*  454 */           this.normalIndices = expandQuad(this.normalIndices); 
/*  455 */         for (b = 0; b < this.texCoordSetCount; b++) {
/*  456 */           this.texCoordIndexSets[b] = expandQuad(this.texCoordIndexSets[b]);
/*      */         }
/*      */         break;
/*      */       
/*      */       case 3:
/*  461 */         for (b = 0; b < this.stripCounts.length; b++) {
/*  462 */           i += this.stripCounts[b] - 2;
/*      */         }
/*      */         
/*  465 */         this.coordinateIndices = expandTriFan(i, this.coordinateIndices);
/*  466 */         if (this.colorIndices != null)
/*  467 */           this.colorIndices = expandTriFan(i, this.colorIndices); 
/*  468 */         if (this.normalIndices != null)
/*  469 */           this.normalIndices = expandTriFan(i, this.normalIndices); 
/*  470 */         for (b = 0; b < this.texCoordSetCount; b++) {
/*  471 */           this.texCoordIndexSets[b] = expandTriFan(i, this.texCoordIndexSets[b]);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  477 */         for (b = 0; b < this.stripCounts.length; b++) {
/*  478 */           i += this.stripCounts[b] - 2;
/*      */         }
/*      */         
/*  481 */         this.coordinateIndices = expandTriStrip(i, this.coordinateIndices);
/*  482 */         if (this.colorIndices != null)
/*  483 */           this.colorIndices = expandTriStrip(i, this.colorIndices); 
/*  484 */         if (this.normalIndices != null)
/*  485 */           this.normalIndices = expandTriStrip(i, this.normalIndices); 
/*  486 */         for (b = 0; b < this.texCoordSetCount; b++) {
/*  487 */           this.texCoordIndexSets[b] = expandTriStrip(i, this.texCoordIndexSets[b]);
/*      */         }
/*      */         break;
/*      */       
/*      */       case 5:
/*  492 */         if (this.tr == null) this.tr = new Triangulator(); 
/*  493 */         this.tr.triangulate(this);
/*      */         break;
/*      */     } 
/*      */     
/*  497 */     this.prim = 1;
/*  498 */     this.stripCounts = null;
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
/*  510 */   public int getPrimitive() { return this.prim; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPrimitive(int paramInt) {
/*  524 */     if (this.prim >= 1 && this.prim <= 5) {
/*  525 */       this.prim = paramInt;
/*      */     } else {
/*  527 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo0"));
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
/*  540 */   public void setCoordinates(Point3f[] paramArrayOfPoint3f) { this.coordinates = paramArrayOfPoint3f; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCoordinates(Point3d[] paramArrayOfPoint3d) {
/*  551 */     if (paramArrayOfPoint3d == null) { this.coordinates = null; }
/*      */     else
/*  553 */     { this.coordinates = new Point3f[paramArrayOfPoint3d.length];
/*  554 */       for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  555 */         this.coordinates[b] = new Point3f((float)(paramArrayOfPoint3d[b]).x, (float)(paramArrayOfPoint3d[b]).y, (float)(paramArrayOfPoint3d[b]).z);
/*      */       } }
/*      */   
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
/*      */   public void setCoordinates(float[] paramArrayOfFloat) {
/*  571 */     if (paramArrayOfFloat == null) { this.coordinates = null; }
/*      */     else
/*  573 */     { this.coordinates = new Point3f[paramArrayOfFloat.length / 3];
/*  574 */       for (byte b = 0; b < this.coordinates.length; b++) {
/*  575 */         this.coordinates[b] = new Point3f(paramArrayOfFloat[b * 3], paramArrayOfFloat[b * 3 + 1], paramArrayOfFloat[b * 3 + 2]);
/*      */       } }
/*      */   
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
/*      */   public void setCoordinates(double[] paramArrayOfDouble) {
/*  590 */     if (paramArrayOfDouble == null) { this.coordinates = null; }
/*      */     else
/*  592 */     { this.coordinates = new Point3f[paramArrayOfDouble.length / 3];
/*  593 */       for (byte b = 0; b < paramArrayOfDouble.length / 3; b++) {
/*  594 */         this.coordinates[b] = new Point3f((float)paramArrayOfDouble[b * 3], (float)paramArrayOfDouble[b * 3 + 1], (float)paramArrayOfDouble[b * 3 + 2]);
/*      */       } }
/*      */   
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
/*  608 */   public Point3f[] getCoordinates() { return this.coordinates; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColors(Color3f[] paramArrayOfColor3f) {
/*  620 */     this.colors3 = paramArrayOfColor3f;
/*  621 */     this.colors4 = null;
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
/*      */   public void setColors(Color4f[] paramArrayOfColor4f) {
/*  633 */     this.colors3 = null;
/*  634 */     this.colors4 = paramArrayOfColor4f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColors(Color3b[] paramArrayOfColor3b) {
/*  645 */     if (paramArrayOfColor3b == null) {
/*  646 */       this.colors3 = null;
/*  647 */       this.colors4 = null;
/*      */     } else {
/*  649 */       this.colors3 = new Color3f[paramArrayOfColor3b.length];
/*  650 */       this.colors4 = null;
/*  651 */       for (byte b = 0; b < paramArrayOfColor3b.length; b++) {
/*  652 */         this.colors3[b] = new Color3f(((paramArrayOfColor3b[b]).x & 0xFF) / 255.0F, ((paramArrayOfColor3b[b]).y & 0xFF) / 255.0F, ((paramArrayOfColor3b[b]).z & 0xFF) / 255.0F);
/*      */       }
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
/*      */   public void setColors(Color4b[] paramArrayOfColor4b) {
/*  667 */     if (paramArrayOfColor4b == null) {
/*  668 */       this.colors3 = null;
/*  669 */       this.colors4 = null;
/*      */     } else {
/*  671 */       this.colors3 = null;
/*  672 */       this.colors4 = new Color4f[paramArrayOfColor4b.length];
/*  673 */       for (byte b = 0; b < paramArrayOfColor4b.length; b++) {
/*  674 */         this.colors4[b] = new Color4f(((paramArrayOfColor4b[b]).x & 0xFF) / 255.0F, ((paramArrayOfColor4b[b]).y & 0xFF) / 255.0F, ((paramArrayOfColor4b[b]).z & 0xFF) / 255.0F, ((paramArrayOfColor4b[b]).w & 0xFF) / 255.0F);
/*      */       }
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
/*      */   
/*      */   public void setColors3(float[] paramArrayOfFloat) {
/*  691 */     if (paramArrayOfFloat == null) {
/*  692 */       this.colors3 = null;
/*  693 */       this.colors4 = null;
/*      */     } else {
/*  695 */       this.colors3 = new Color3f[paramArrayOfFloat.length / 3];
/*  696 */       this.colors4 = null;
/*  697 */       for (byte b = 0; b < paramArrayOfFloat.length / 3; b++) {
/*  698 */         this.colors3[b] = new Color3f(paramArrayOfFloat[b * 3], paramArrayOfFloat[b * 3 + 1], paramArrayOfFloat[b * 3 + 2]);
/*      */       }
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
/*      */   public void setColors4(float[] paramArrayOfFloat) {
/*  714 */     if (paramArrayOfFloat == null) {
/*  715 */       this.colors3 = null;
/*  716 */       this.colors4 = null;
/*      */     } else {
/*  718 */       this.colors3 = null;
/*  719 */       this.colors4 = new Color4f[paramArrayOfFloat.length / 4];
/*  720 */       for (byte b = 0; b < paramArrayOfFloat.length / 4; b++) {
/*  721 */         this.colors4[b] = new Color4f(paramArrayOfFloat[b * 4], paramArrayOfFloat[b * 4 + 1], paramArrayOfFloat[b * 4 + 2], paramArrayOfFloat[b * 4 + 3]);
/*      */       }
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
/*      */   
/*      */   public void setColors3(byte[] paramArrayOfByte) {
/*  738 */     if (paramArrayOfByte == null) {
/*  739 */       this.colors3 = null;
/*  740 */       this.colors4 = null;
/*      */     } else {
/*  742 */       this.colors3 = new Color3f[paramArrayOfByte.length / 3];
/*  743 */       this.colors4 = null;
/*  744 */       for (byte b = 0; b < paramArrayOfByte.length / 3; b++) {
/*  745 */         this.colors3[b] = new Color3f((paramArrayOfByte[b * 3] & 0xFF) / 255.0F, (paramArrayOfByte[b * 3 + 1] & 0xFF) / 255.0F, (paramArrayOfByte[b * 3 + 2] & 0xFF) / 255.0F);
/*      */       }
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
/*      */   
/*      */   public void setColors4(byte[] paramArrayOfByte) {
/*  762 */     if (paramArrayOfByte == null) {
/*  763 */       this.colors3 = null;
/*  764 */       this.colors4 = null;
/*      */     } else {
/*  766 */       this.colors3 = null;
/*  767 */       this.colors4 = new Color4f[paramArrayOfByte.length / 4];
/*  768 */       for (byte b = 0; b < paramArrayOfByte.length / 4; b++) {
/*  769 */         this.colors4[b] = new Color4f((paramArrayOfByte[b * 4] & 0xFF) / 255.0F, (paramArrayOfByte[b * 4 + 1] & 0xFF) / 255.0F, (paramArrayOfByte[b * 4 + 2] & 0xFF) / 255.0F, (paramArrayOfByte[b * 4 + 3] & 0xFF) / 255.0F);
/*      */       }
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
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] getColors() {
/*  788 */     if (this.colors3 != null) return this.colors3; 
/*  789 */     return this.colors4;
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
/*      */   public int getNumColorComponents() {
/*  801 */     if (this.colors3 != null) return 3; 
/*  802 */     if (this.colors4 != null) return 4; 
/*  803 */     return 0;
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
/*  815 */   public void setNormals(Vector3f[] paramArrayOfVector3f) { this.normals = paramArrayOfVector3f; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNormals(float[] paramArrayOfFloat) {
/*  826 */     if (paramArrayOfFloat == null) { this.normals = null; }
/*      */     else
/*  828 */     { this.normals = new Vector3f[paramArrayOfFloat.length / 3];
/*  829 */       for (byte b = 0; b < this.normals.length; b++) {
/*  830 */         this.normals[b] = new Vector3f(paramArrayOfFloat[b * 3], paramArrayOfFloat[b * 3 + 1], paramArrayOfFloat[b * 3 + 2]);
/*      */       } }
/*      */   
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
/*  844 */   public Vector3f[] getNormals() { return this.normals; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinateParams(int paramInt1, int paramInt2) {
/*  880 */     if (paramInt2 == 2) {
/*  881 */       this.texCoordSets = new TexCoord2f[paramInt1][];
/*  882 */     } else if (paramInt2 == 3) {
/*  883 */       this.texCoordSets = new TexCoord3f[paramInt1][];
/*  884 */     } else if (paramInt2 == 4) {
/*  885 */       this.texCoordSets = new TexCoord4f[paramInt1][];
/*      */     } else {
/*  887 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo9"));
/*      */     } 
/*      */     
/*  890 */     this.texCoordIndexSets = new int[paramInt1][];
/*  891 */     this.texCoordDim = paramInt2;
/*  892 */     this.texCoordSetCount = paramInt1;
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
/*  910 */   public int getTexCoordSetCount() { return this.texCoordSetCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  931 */   public int getNumTexCoordComponents() { return this.texCoordDim; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  947 */   public void setTexCoordSetMap(int[] paramArrayOfInt) { this.texCoordSetMap = paramArrayOfInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  959 */   public int[] getTexCoordSetMap() { return this.texCoordSetMap; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt, TexCoord2f[] paramArrayOfTexCoord2f) {
/*  977 */     if (this.texCoordDim != 2) {
/*  978 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo15"));
/*      */     }
/*  980 */     if (paramInt >= this.texCoordSetCount || paramInt < 0) {
/*  981 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo18"));
/*      */     }
/*      */     
/*  984 */     this.texCoordSets[paramInt] = paramArrayOfTexCoord2f;
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
/*      */   public void setTextureCoordinates(Point2f[] paramArrayOfPoint2f) {
/* 1000 */     this.texCoordSetCount = 1;
/* 1001 */     this.texCoordDim = 2;
/* 1002 */     this.texCoordSets = new TexCoord2f[1][];
/* 1003 */     if (paramArrayOfPoint2f != null) {
/* 1004 */       TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[paramArrayOfPoint2f.length];
/* 1005 */       for (byte b = 0; b < paramArrayOfPoint2f.length; b++)
/* 1006 */         arrayOfTexCoord2f[b] = new TexCoord2f(paramArrayOfPoint2f[b]); 
/* 1007 */       this.texCoordSets[0] = arrayOfTexCoord2f;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt, TexCoord3f[] paramArrayOfTexCoord3f) {
/* 1026 */     if (this.texCoordDim != 3) {
/* 1027 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo16"));
/*      */     }
/* 1029 */     if (paramInt >= this.texCoordSetCount || paramInt < 0) {
/* 1030 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo18"));
/*      */     }
/*      */     
/* 1033 */     this.texCoordSets[paramInt] = paramArrayOfTexCoord3f;
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
/*      */   public void setTextureCoordinates(Point3f[] paramArrayOfPoint3f) {
/* 1049 */     this.texCoordSetCount = 1;
/* 1050 */     this.texCoordDim = 3;
/* 1051 */     this.texCoordSets = new TexCoord3f[1][];
/* 1052 */     if (paramArrayOfPoint3f != null) {
/* 1053 */       TexCoord3f[] arrayOfTexCoord3f = new TexCoord3f[paramArrayOfPoint3f.length];
/* 1054 */       for (byte b = 0; b < paramArrayOfPoint3f.length; b++)
/* 1055 */         arrayOfTexCoord3f[b] = new TexCoord3f(paramArrayOfPoint3f[b]); 
/* 1056 */       this.texCoordSets[0] = arrayOfTexCoord3f;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt, TexCoord4f[] paramArrayOfTexCoord4f) {
/* 1074 */     if (this.texCoordDim != 4) {
/* 1075 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo17"));
/*      */     }
/* 1077 */     if (paramInt >= this.texCoordSetCount || paramInt < 0) {
/* 1078 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo18"));
/*      */     }
/*      */     
/* 1081 */     this.texCoordSets[paramInt] = paramArrayOfTexCoord4f;
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
/*      */   public void setTextureCoordinates(int paramInt, float[] paramArrayOfFloat) {
/* 1102 */     if (paramArrayOfFloat.length % this.texCoordDim != 0) {
/* 1103 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo2"));
/*      */     }
/*      */ 
/*      */     
/* 1107 */     if (this.texCoordDim == 2) {
/* 1108 */       TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[paramArrayOfFloat.length / 2];
/* 1109 */       for (byte b = 0; b < arrayOfTexCoord2f.length; b++) {
/* 1110 */         arrayOfTexCoord2f[b] = new TexCoord2f(paramArrayOfFloat[b * 2], paramArrayOfFloat[b * 2 + 1]);
/*      */       }
/* 1112 */       setTextureCoordinates(paramInt, arrayOfTexCoord2f);
/* 1113 */     } else if (this.texCoordDim == 3) {
/* 1114 */       TexCoord3f[] arrayOfTexCoord3f = new TexCoord3f[paramArrayOfFloat.length / 3];
/* 1115 */       for (byte b = 0; b < arrayOfTexCoord3f.length; b++) {
/* 1116 */         arrayOfTexCoord3f[b] = new TexCoord3f(paramArrayOfFloat[b * 3], paramArrayOfFloat[b * 3 + 1], paramArrayOfFloat[b * 3 + 2]);
/*      */       }
/*      */       
/* 1119 */       setTextureCoordinates(paramInt, arrayOfTexCoord3f);
/* 1120 */     } else if (this.texCoordDim == 4) {
/* 1121 */       TexCoord4f[] arrayOfTexCoord4f = new TexCoord4f[paramArrayOfFloat.length / 4];
/* 1122 */       for (byte b = 0; b < arrayOfTexCoord4f.length; b++) {
/* 1123 */         arrayOfTexCoord4f[b] = new TexCoord4f(paramArrayOfFloat[b * 4], paramArrayOfFloat[b * 4 + 1], paramArrayOfFloat[b * 4 + 2], paramArrayOfFloat[b * 4 + 3]);
/*      */       }
/*      */ 
/*      */       
/* 1127 */       setTextureCoordinates(paramInt, arrayOfTexCoord4f);
/*      */     } else {
/* 1129 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo21"));
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates2(float[] paramArrayOfFloat) {
/* 1148 */     this.texCoordSetCount = 1;
/* 1149 */     this.texCoordDim = 2;
/* 1150 */     this.texCoordSets = new TexCoord2f[1][];
/* 1151 */     setTextureCoordinates(0, paramArrayOfFloat);
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
/*      */   public void setTextureCoordinates3(float[] paramArrayOfFloat) {
/* 1168 */     this.texCoordSetCount = 1;
/* 1169 */     this.texCoordDim = 3;
/* 1170 */     this.texCoordSets = new TexCoord3f[1][];
/* 1171 */     setTextureCoordinates(0, paramArrayOfFloat);
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
/*      */   public Object[] getTextureCoordinates(int paramInt) {
/* 1191 */     if (paramInt >= this.texCoordSetCount || paramInt < 0) {
/* 1192 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo18"));
/*      */     }
/* 1194 */     return this.texCoordSets[paramInt];
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
/* 1212 */   public Object[] getTextureCoordinates() { return this.texCoordSets[0]; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1223 */   public void setCoordinateIndices(int[] paramArrayOfInt) { this.coordinateIndices = paramArrayOfInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1255 */   public int[] getCoordinateIndices() { return this.coordinateIndices; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1266 */   public void setColorIndices(int[] paramArrayOfInt) { this.colorIndices = paramArrayOfInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1298 */   public int[] getColorIndices() { return this.colorIndices; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1309 */   public void setNormalIndices(int[] paramArrayOfInt) { this.normalIndices = paramArrayOfInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1342 */   public int[] getNormalIndices() { return this.normalIndices; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinateIndices(int paramInt, int[] paramArrayOfInt) {
/* 1359 */     if (paramInt >= this.texCoordSetCount || paramInt < 0) {
/* 1360 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo18"));
/*      */     }
/*      */ 
/*      */     
/* 1364 */     this.texCoordIndexSets[paramInt] = paramArrayOfInt;
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
/*      */   public void setTextureCoordinateIndices(int[] paramArrayOfInt) {
/* 1380 */     if (this.texCoordSetCount > 1) {
/* 1381 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo1"));
/*      */     }
/* 1383 */     this.texCoordIndexSets = new int[1][];
/* 1384 */     this.texCoordIndexSets[0] = paramArrayOfInt;
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
/* 1419 */   public int[] getTextureCoordinateIndices(int paramInt) { return this.texCoordIndexSets[paramInt]; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getTextureCoordinateIndices() {
/* 1433 */     if (this.texCoordIndexSets == null) return null; 
/* 1434 */     return this.texCoordIndexSets[0];
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
/* 1452 */   public void setStripCounts(int[] paramArrayOfInt) { this.stripCounts = paramArrayOfInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1483 */   public int[] getStripCounts() { return this.stripCounts; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1498 */   public void setContourCounts(int[] paramArrayOfInt) { this.contourCounts = paramArrayOfInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1508 */   public int[] getContourCounts() { return this.contourCounts; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int[] getListIndices(Object[] paramArrayOfObject) {
/* 1519 */     int[] arrayOfInt = new int[paramArrayOfObject.length];
/*      */ 
/*      */ 
/*      */     
/* 1523 */     HashMap hashMap = new HashMap(paramArrayOfObject.length);
/*      */ 
/*      */     
/* 1526 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/*      */ 
/*      */       
/* 1529 */       Integer integer = (Integer)hashMap.get(paramArrayOfObject[b]);
/*      */       
/* 1531 */       if (integer == null) {
/*      */         
/* 1533 */         arrayOfInt[b] = b;
/*      */ 
/*      */         
/* 1536 */         hashMap.put(paramArrayOfObject[b], new Integer(b));
/*      */       }
/*      */       else {
/*      */         
/* 1540 */         arrayOfInt[b] = integer.intValue();
/*      */       } 
/*      */     } 
/*      */     
/* 1544 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */   
/*      */   private class IndexRow
/*      */   {
/*      */     int[] val;
/*      */     
/*      */     int size;
/*      */     
/*      */     private static final int HASHCONST = -1161889074;
/*      */     
/*      */     public int hashCode() {
/* 1557 */       int i = 0;
/* 1558 */       for (byte b = 0; b < this.size; b++) {
/* 1559 */         i ^= i * -1161889074 << 2;
/*      */       }
/* 1561 */       return i;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object param1Object) {
/* 1566 */       for (byte b = 0; b < this.size; b++) {
/* 1567 */         if (((IndexRow)param1Object).get(b) != this.val[b]) return false; 
/*      */       } 
/* 1569 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1574 */     public int get(int param1Int) { return this.val[param1Int]; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1579 */     public void set(int param1Int1, int param1Int2) { this.val[param1Int1] = param1Int2; }
/*      */ 
/*      */ 
/*      */     
/*      */     IndexRow(int param1Int) {
/* 1584 */       this.size = param1Int;
/* 1585 */       this.val = new int[this.size];
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void indexify(boolean paramBoolean) {
/* 1614 */     checkForBadData();
/*      */     
/* 1616 */     if (paramBoolean) {
/*      */       
/* 1618 */       if (this.coordOnly) {
/*      */         return;
/*      */       }
/* 1621 */       indexify(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1628 */       int i = 1;
/* 1629 */       if (this.colorIndices != null) i++; 
/* 1630 */       if (this.normalIndices != null) i++; 
/* 1631 */       i += this.texCoordSetCount;
/*      */ 
/*      */       
/* 1634 */       int j = this.coordinateIndices.length;
/* 1635 */       IndexRow[] arrayOfIndexRow1 = new IndexRow[j];
/*      */       
/* 1637 */       for (byte b1 = 0; b1 < j; b1++) {
/* 1638 */         arrayOfIndexRow1[b1] = new IndexRow(i);
/* 1639 */         byte b3 = 0;
/* 1640 */         arrayOfIndexRow1[b1].set(b3++, this.coordinateIndices[b1]);
/* 1641 */         if (this.colorIndices != null) arrayOfIndexRow1[b1].set(b3++, this.colorIndices[b1]); 
/* 1642 */         if (this.normalIndices != null) arrayOfIndexRow1[b1].set(b3++, this.normalIndices[b1]); 
/* 1643 */         for (byte b4 = 0; b4 < this.texCoordSetCount; b4++) {
/* 1644 */           arrayOfIndexRow1[b1].set(b3++, this.texCoordIndexSets[b4][b1]);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1649 */       int[] arrayOfInt1 = getListIndices(arrayOfIndexRow1);
/*      */ 
/*      */       
/* 1652 */       int[] arrayOfInt2 = new int[arrayOfInt1.length];
/* 1653 */       IndexRow[] arrayOfIndexRow = (IndexRow[])compactData(arrayOfInt1, arrayOfIndexRow1, arrayOfInt2);
/* 1654 */       arrayOfInt1 = arrayOfInt2;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1659 */       Point3f[] arrayOfPoint3f = new Point3f[arrayOfIndexRow.length];
/* 1660 */       Color3f[] arrayOfColor3f = null;
/* 1661 */       Color4f[] arrayOfColor4f = null;
/* 1662 */       Vector3f[] arrayOfVector3f = null;
/* 1663 */       TexCoord4f[][] arrayOfTexCoord4f = (Object[][])null;
/* 1664 */       if (this.colors3 != null) { arrayOfColor3f = new Color3f[arrayOfIndexRow.length]; }
/* 1665 */       else if (this.colors4 != null) { arrayOfColor4f = new Color4f[arrayOfIndexRow.length]; }
/* 1666 */        if (this.normals != null) arrayOfVector3f = new Vector3f[arrayOfIndexRow.length];  byte b2;
/* 1667 */       for (b2 = 0; b2 < this.texCoordSetCount; b2++) {
/* 1668 */         if (this.texCoordDim == 2) {
/* 1669 */           TexCoord2f[][] arrayOfTexCoord2f; if (!b2) arrayOfTexCoord2f = new TexCoord2f[this.texCoordSetCount][]; 
/* 1670 */           arrayOfTexCoord2f[b2] = new TexCoord2f[arrayOfIndexRow.length];
/* 1671 */         } else if (this.texCoordDim == 3) {
/* 1672 */           TexCoord3f[][] arrayOfTexCoord3f; if (!b2) arrayOfTexCoord3f = new TexCoord3f[this.texCoordSetCount][]; 
/* 1673 */           arrayOfTexCoord3f[b2] = new TexCoord3f[arrayOfIndexRow.length];
/* 1674 */         } else if (this.texCoordDim == 4) {
/* 1675 */           if (!b2) arrayOfTexCoord4f = new TexCoord4f[this.texCoordSetCount][]; 
/* 1676 */           arrayOfTexCoord4f[b2] = new TexCoord4f[arrayOfIndexRow.length];
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1681 */       j = arrayOfIndexRow.length;
/* 1682 */       for (b2 = 0; b2 < j; b2++) {
/* 1683 */         byte b3 = 0;
/* 1684 */         arrayOfPoint3f[b2] = this.coordinates[arrayOfIndexRow[b2].get(b3++)];
/* 1685 */         if (this.colors3 != null) {
/* 1686 */           arrayOfColor3f[b2] = this.colors3[arrayOfIndexRow[b2].get(b3++)];
/* 1687 */         } else if (this.colors4 != null) {
/* 1688 */           arrayOfColor4f[b2] = this.colors4[arrayOfIndexRow[b2].get(b3++)];
/*      */         } 
/* 1690 */         if (this.normals != null) arrayOfVector3f[b2] = this.normals[arrayOfIndexRow[b2].get(b3++)]; 
/* 1691 */         for (byte b4 = 0; b4 < this.texCoordSetCount; b4++) {
/* 1692 */           arrayOfTexCoord4f[b4][b2] = this.texCoordSets[b4][arrayOfIndexRow[b2].get(b3++)];
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1697 */       this.coordinates = arrayOfPoint3f;
/* 1698 */       this.colors3 = arrayOfColor3f;
/* 1699 */       this.colors4 = arrayOfColor4f;
/* 1700 */       this.normals = arrayOfVector3f;
/* 1701 */       this.texCoordSets = arrayOfTexCoord4f;
/* 1702 */       this.coordinateIndices = arrayOfInt1;
/* 1703 */       this.colorIndices = null;
/* 1704 */       this.normalIndices = null;
/* 1705 */       this.texCoordIndexSets = new int[this.texCoordSetCount][];
/*      */       
/* 1707 */       this.coordOnly = true;
/* 1708 */     } else if (this.coordOnly) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1713 */       int i = this.coordinateIndices.length;
/* 1714 */       if (this.colors3 != null || this.colors4 != null) {
/* 1715 */         this.colorIndices = new int[i];
/* 1716 */         for (byte b1 = 0; b1 < i; ) { this.colorIndices[b1] = this.coordinateIndices[b1]; b1++; }
/*      */       
/* 1718 */       }  if (this.normals != null) {
/* 1719 */         this.normalIndices = new int[i];
/* 1720 */         for (byte b1 = 0; b1 < i; ) { this.normalIndices[b1] = this.coordinateIndices[b1]; b1++; }
/*      */       
/* 1722 */       }  this.texCoordIndexSets = new int[this.texCoordSetCount][];
/* 1723 */       for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1724 */         this.texCoordIndexSets[b] = new int[i];
/* 1725 */         for (byte b1 = 0; b1 < i; b1++) {
/* 1726 */           this.texCoordIndexSets[b][b1] = this.coordinateIndices[b1];
/*      */         }
/*      */       } 
/* 1729 */       this.coordOnly = false;
/*      */     }
/*      */     else {
/*      */       
/* 1733 */       if (this.coordinateIndices != null)
/*      */         return; 
/* 1735 */       this.coordinateIndices = getListIndices(this.coordinates);
/*      */       
/* 1737 */       if (this.colors3 != null) { this.colorIndices = getListIndices(this.colors3); }
/* 1738 */       else if (this.colors4 != null) { this.colorIndices = getListIndices(this.colors4); }
/*      */       
/* 1740 */       if (this.normals != null) this.normalIndices = getListIndices(this.normals);
/*      */       
/* 1742 */       this.texCoordIndexSets = new int[this.texCoordSetCount][];
/* 1743 */       for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1744 */         this.texCoordIndexSets[b] = getListIndices(this.texCoordSets[b]);
/*      */       }
/*      */       
/* 1747 */       this.coordOnly = false;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1768 */   public void indexify() { indexify(false); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Object[] allocateArray(Object[] paramArrayOfObject, int paramInt) {
/* 1789 */     IndexRow[] arrayOfIndexRow = null;
/* 1790 */     if (paramArrayOfObject instanceof Point3f[])
/* 1791 */     { arrayOfIndexRow = new Point3f[paramInt]; }
/* 1792 */     else if (paramArrayOfObject instanceof Vector3f[])
/* 1793 */     { arrayOfIndexRow = new Vector3f[paramInt]; }
/* 1794 */     else if (paramArrayOfObject instanceof Color3f[])
/* 1795 */     { arrayOfIndexRow = new Color3f[paramInt]; }
/* 1796 */     else if (paramArrayOfObject instanceof Color4f[])
/* 1797 */     { arrayOfIndexRow = new Color4f[paramInt]; }
/* 1798 */     else if (paramArrayOfObject instanceof TexCoord2f[])
/* 1799 */     { arrayOfIndexRow = new TexCoord2f[paramInt]; }
/* 1800 */     else if (paramArrayOfObject instanceof TexCoord3f[])
/* 1801 */     { arrayOfIndexRow = new TexCoord3f[paramInt]; }
/* 1802 */     else if (paramArrayOfObject instanceof TexCoord4f[])
/* 1803 */     { arrayOfIndexRow = new TexCoord4f[paramInt]; }
/* 1804 */     else if (paramArrayOfObject instanceof IndexRow[])
/*      */     
/* 1806 */     { arrayOfIndexRow = new IndexRow[paramInt]; }
/* 1807 */     else { throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo9")); }
/*      */     
/* 1809 */     return arrayOfIndexRow;
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
/*      */   private Object[] compactData(int[] paramArrayOfInt1, Object[] paramArrayOfObject, int[] paramArrayOfInt2) {
/* 1831 */     Object[] arrayOfObject = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1837 */     byte b1 = 0;
/* 1838 */     int[] arrayOfInt = new int[paramArrayOfObject.length]; byte b2;
/* 1839 */     for (b2 = 0; b2 < paramArrayOfInt1.length; b2++) {
/* 1840 */       if (arrayOfInt[paramArrayOfInt1[b2]] == 0) {
/*      */         
/* 1842 */         b1++;
/* 1843 */         arrayOfInt[paramArrayOfInt1[b2]] = 1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1850 */     arrayOfObject = allocateArray(paramArrayOfObject, b1);
/* 1851 */     b2 = 0; byte b3;
/* 1852 */     for (b3 = 0; b3 < arrayOfInt.length; b3++) {
/* 1853 */       if (arrayOfInt[b3] != 0) {
/* 1854 */         arrayOfObject[b2] = paramArrayOfObject[b3];
/* 1855 */         arrayOfInt[b3] = b2++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1861 */     for (b3 = 0; b3 < paramArrayOfInt1.length; b3++) {
/* 1862 */       paramArrayOfInt2[b3] = arrayOfInt[paramArrayOfInt1[b3]];
/*      */     }
/* 1864 */     return arrayOfObject;
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
/*      */   public void compact() {
/* 1887 */     checkForBadData();
/*      */ 
/*      */     
/* 1890 */     if (this.coordinateIndices == null) {
/*      */       return;
/*      */     }
/* 1893 */     if (this.coordOnly)
/*      */       return; 
/* 1895 */     int[] arrayOfInt = new int[this.coordinateIndices.length];
/* 1896 */     this.coordinates = (Point3f[])compactData(this.coordinateIndices, this.coordinates, arrayOfInt);
/*      */     
/* 1898 */     this.coordinateIndices = arrayOfInt;
/*      */     
/* 1900 */     if (this.colorIndices != null) {
/* 1901 */       arrayOfInt = new int[this.colorIndices.length];
/* 1902 */       if (this.colors3 != null) {
/* 1903 */         this.colors3 = (Color3f[])compactData(this.colorIndices, this.colors3, arrayOfInt);
/* 1904 */       } else if (this.colors4 != null) {
/* 1905 */         this.colors4 = (Color4f[])compactData(this.colorIndices, this.colors4, arrayOfInt);
/* 1906 */       }  this.colorIndices = arrayOfInt;
/*      */     } 
/*      */     
/* 1909 */     if (this.normalIndices != null) {
/* 1910 */       arrayOfInt = new int[this.normalIndices.length];
/* 1911 */       this.normals = (Vector3f[])compactData(this.normalIndices, this.normals, arrayOfInt);
/* 1912 */       this.normalIndices = arrayOfInt;
/*      */     } 
/*      */     
/* 1915 */     for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1916 */       arrayOfInt = new int[this.texCoordIndexSets[b].length];
/* 1917 */       this.texCoordSets[b] = compactData(this.texCoordIndexSets[b], this.texCoordSets[b], arrayOfInt);
/*      */       
/* 1919 */       this.texCoordIndexSets[b] = arrayOfInt;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkForBadData() {
/*      */     int i;
/* 1929 */     boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1934 */     if (this.coordinates == null) {
/* 1935 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo3"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1942 */     if (this.colors3 == null && this.colors4 == null && this.colorIndices != null) {
/* 1943 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo4"));
/*      */     }
/* 1945 */     if (this.normals == null && this.normalIndices != null) {
/* 1946 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo11"));
/*      */     }
/*      */ 
/*      */     
/*      */     byte b;
/*      */     
/* 1952 */     for (b = 0; b < this.texCoordSetCount; b++) {
/* 1953 */       if (this.texCoordSets[b] == null) {
/* 1954 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo10"));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1961 */     b = 0;
/* 1962 */     if (this.texCoordIndexSets != null)
/* 1963 */       for (i = 0; i < this.texCoordSetCount; i++) {
/* 1964 */         if (this.texCoordIndexSets[i] != null) b = 1;
/*      */       
/*      */       }  
/* 1967 */     if (this.coordinateIndices != null || this.colorIndices != null || this.normalIndices != null || b != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1973 */       if (this.coordinateIndices == null) { bool = true; }
/* 1974 */       else if (this.coordOnly)
/* 1975 */       { if (this.colorIndices != null || this.normalIndices != null || b == 1)
/*      */         {
/*      */           
/* 1978 */           throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo20"));
/*      */         } }
/*      */       
/* 1981 */       else if ((this.colors3 != null || this.colors4 != null) && this.colorIndices == null)
/* 1982 */       { bool = true; }
/* 1983 */       else if (this.normals != null && this.normalIndices == null) { bool = true; }
/* 1984 */       else if (this.texCoordSetCount > 0 && b == 0) { bool = true; }
/* 1985 */        if (bool) throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo19"));
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1992 */     if (this.coordinateIndices != null && !this.coordOnly) {
/* 1993 */       if ((this.colors3 != null || this.colors4 != null) && this.colorIndices.length != this.coordinateIndices.length) {
/*      */         
/* 1995 */         bool = true;
/* 1996 */       } else if (this.normals != null && this.normalIndices.length != this.coordinateIndices.length) {
/*      */         
/* 1998 */         bool = true;
/*      */       } else {
/*      */         
/* 2001 */         for (i = 0; i < this.texCoordSetCount; i++) {
/* 2002 */           if (this.texCoordIndexSets[i].length != this.coordinateIndices.length) {
/* 2003 */             bool = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 2008 */       if (bool) {
/* 2009 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo5"));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2017 */     if (this.prim == 4 || this.prim == 3 || this.prim == 5)
/*      */     
/*      */     { 
/* 2020 */       if (this.stripCounts == null) bool = true;  }
/* 2021 */     else if (this.stripCounts != null) { bool = true; }
/* 2022 */      if (bool) {
/* 2023 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo6"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2029 */     if (this.coordinateIndices == null) { i = this.coordinates.length; }
/* 2030 */     else { i = this.coordinateIndices.length; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2037 */     if (this.prim == 4 || this.prim == 3 || this.prim == 5) {
/*      */ 
/*      */       
/* 2040 */       int j = 0;
/* 2041 */       for (byte b1 = 0; b1 < this.stripCounts.length; b1++) {
/* 2042 */         j += this.stripCounts[b1];
/*      */       }
/* 2044 */       if (j != i) {
/* 2045 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo7"));
/*      */       }
/*      */     }
/* 2048 */     else if (this.prim == 1) {
/* 2049 */       if (i % 3 != 0) {
/* 2050 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo12"));
/*      */       }
/*      */     }
/* 2053 */     else if (this.prim == 2 && 
/* 2054 */       i % 4 != 0) {
/* 2055 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo13"));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2063 */     if (this.prim == 5) {
/* 2064 */       if (this.contourCounts != null) {
/* 2065 */         int j = 0;
/* 2066 */         for (byte b1 = 0; b1 < this.contourCounts.length; b1++)
/* 2067 */           j += this.contourCounts[b1]; 
/* 2068 */         if (j != this.stripCounts.length) {
/* 2069 */           throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo8"));
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 2074 */     else if (this.contourCounts != null) {
/* 2075 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfo14"));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unindexify() {
/* 2098 */     checkForBadData();
/* 2099 */     if (this.coordinateIndices != null) {
/*      */       
/* 2101 */       if (this.coordOnly) indexify(false);
/*      */       
/* 2103 */       this.coordinates = (Point3f[])unindexifyData(this.coordinates, this.coordinateIndices);
/*      */       
/* 2105 */       this.coordinateIndices = null;
/*      */       
/* 2107 */       if (this.colors3 != null) {
/* 2108 */         this.colors3 = (Color3f[])unindexifyData(this.colors3, this.colorIndices);
/* 2109 */       } else if (this.colors4 != null) {
/* 2110 */         this.colors4 = (Color4f[])unindexifyData(this.colors4, this.colorIndices);
/*      */       } 
/* 2112 */       this.colorIndices = null;
/*      */       
/* 2114 */       if (this.normals != null) {
/* 2115 */         this.normals = (Vector3f[])unindexifyData(this.normals, this.normalIndices);
/* 2116 */         this.normalIndices = null;
/*      */       } 
/*      */       
/* 2119 */       for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2120 */         this.texCoordSets[b] = unindexifyData(this.texCoordSets[b], this.texCoordIndexSets[b]);
/*      */       }
/* 2122 */       this.texCoordIndexSets = new int[this.texCoordSetCount][];
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
/*      */   private Object[] unindexifyData(Object[] paramArrayOfObject, int[] paramArrayOfInt) {
/* 2135 */     Object[] arrayOfObject = allocateArray(paramArrayOfObject, paramArrayOfInt.length);
/* 2136 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 2137 */       arrayOfObject[b] = paramArrayOfObject[paramArrayOfInt[b]];
/*      */     }
/* 2139 */     return arrayOfObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getVertexFormat() {
/* 2149 */     char c = '\001';
/*      */     
/* 2151 */     if (this.colors3 != null) { c |= 0x4; }
/* 2152 */     else if (this.colors4 != null) { c |= 0xC; }
/*      */     
/* 2154 */     if (this.normals != null) c |= 0x2;
/*      */     
/* 2156 */     if (this.texCoordDim == 2) {
/* 2157 */       c |= 0x20;
/* 2158 */     } else if (this.texCoordDim == 3) {
/* 2159 */       c |= 0x40;
/* 2160 */     } else if (this.texCoordDim == 4) {
/* 2161 */       c |= 0x400;
/*      */     } 
/* 2163 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getVertexCount() {
/* 2173 */     int i = this.coordinates.length;
/*      */     
/* 2175 */     if (this.colors3 != null)
/* 2176 */     { if (this.colors3.length > i) i = this.colors3.length;  }
/* 2177 */     else if (this.colors4 != null && 
/* 2178 */       this.colors4.length > i) { i = this.colors4.length; }
/*      */ 
/*      */     
/* 2181 */     if (this.normals != null && 
/* 2182 */       this.normals.length > i) i = this.normals.length;
/*      */ 
/*      */ 
/*      */     
/* 2186 */     for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2187 */       if (this.texCoordSets[b].length > i) {
/* 2188 */         i = this.texCoordSets[b].length;
/*      */       }
/*      */     } 
/* 2191 */     return i;
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
/*      */   private float[] vecmathToFloat(Object[] paramArrayOfObject) {
/* 2204 */     if (paramArrayOfObject[0] instanceof Tuple2f) {
/* 2205 */       float[] arrayOfFloat = new float[paramArrayOfObject.length * 2];
/* 2206 */       Tuple2f[] arrayOfTuple2f = (Tuple2f[])paramArrayOfObject;
/* 2207 */       for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 2208 */         arrayOfFloat[b * 2] = (arrayOfTuple2f[b]).x;
/* 2209 */         arrayOfFloat[b * 2 + 1] = (arrayOfTuple2f[b]).y;
/*      */       } 
/* 2211 */       return arrayOfFloat;
/* 2212 */     }  if (paramArrayOfObject[0] instanceof Tuple3f) {
/* 2213 */       float[] arrayOfFloat = new float[paramArrayOfObject.length * 3];
/* 2214 */       Tuple3f[] arrayOfTuple3f = (Tuple3f[])paramArrayOfObject;
/* 2215 */       for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 2216 */         arrayOfFloat[b * 3] = (arrayOfTuple3f[b]).x;
/* 2217 */         arrayOfFloat[b * 3 + 1] = (arrayOfTuple3f[b]).y;
/* 2218 */         arrayOfFloat[b * 3 + 2] = (arrayOfTuple3f[b]).z;
/*      */       } 
/* 2220 */       return arrayOfFloat;
/* 2221 */     }  if (paramArrayOfObject[0] instanceof Tuple4f) {
/* 2222 */       float[] arrayOfFloat = new float[paramArrayOfObject.length * 4];
/* 2223 */       Tuple4f[] arrayOfTuple4f = (Tuple4f[])paramArrayOfObject;
/* 2224 */       for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 2225 */         arrayOfFloat[b * 4] = (arrayOfTuple4f[b]).x;
/* 2226 */         arrayOfFloat[b * 4 + 1] = (arrayOfTuple4f[b]).y;
/* 2227 */         arrayOfFloat[b * 4 + 2] = (arrayOfTuple4f[b]).z;
/* 2228 */         arrayOfFloat[b * 4 + 3] = (arrayOfTuple4f[b]).w;
/*      */       } 
/* 2230 */       return arrayOfFloat;
/*      */     } 
/* 2232 */     return null;
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
/*      */   private void fillIn(GeometryArray paramGeometryArray, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/* 2244 */     if (paramBoolean2) {
/*      */       
/* 2246 */       int i = 3;
/* 2247 */       if (this.normals != null) i += 3; 
/* 2248 */       if (this.colors3 != null) { i += 3; }
/* 2249 */       else if (this.colors4 != null) { i += 4; }
/* 2250 */        i += this.texCoordSetCount * this.texCoordDim;
/*      */ 
/*      */       
/* 2253 */       float[] arrayOfFloat = new float[i * this.coordinates.length];
/*      */ 
/*      */       
/* 2256 */       byte b1 = 0;
/* 2257 */       for (byte b2 = 0; b2 < this.coordinates.length; b2++) {
/* 2258 */         if (this.texCoordDim == 2) {
/* 2259 */           for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2260 */             arrayOfFloat[b1++] = ((TexCoord2f)this.texCoordSets[b][b2]).x;
/* 2261 */             arrayOfFloat[b1++] = ((TexCoord2f)this.texCoordSets[b][b2]).y;
/*      */           } 
/* 2263 */         } else if (this.texCoordDim == 3) {
/* 2264 */           for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2265 */             arrayOfFloat[b1++] = ((TexCoord3f)this.texCoordSets[b][b2]).x;
/* 2266 */             arrayOfFloat[b1++] = ((TexCoord3f)this.texCoordSets[b][b2]).y;
/* 2267 */             arrayOfFloat[b1++] = ((TexCoord3f)this.texCoordSets[b][b2]).z;
/*      */           } 
/* 2269 */         } else if (this.texCoordDim == 4) {
/* 2270 */           for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2271 */             arrayOfFloat[b1++] = ((TexCoord4f)this.texCoordSets[b][b2]).x;
/* 2272 */             arrayOfFloat[b1++] = ((TexCoord4f)this.texCoordSets[b][b2]).y;
/* 2273 */             arrayOfFloat[b1++] = ((TexCoord4f)this.texCoordSets[b][b2]).z;
/* 2274 */             arrayOfFloat[b1++] = ((TexCoord4f)this.texCoordSets[b][b2]).w;
/*      */           } 
/*      */         } 
/*      */         
/* 2278 */         if (this.colors3 != null) {
/* 2279 */           arrayOfFloat[b1++] = (this.colors3[b2]).x;
/* 2280 */           arrayOfFloat[b1++] = (this.colors3[b2]).y;
/* 2281 */           arrayOfFloat[b1++] = (this.colors3[b2]).z;
/* 2282 */         } else if (this.colors4 != null) {
/* 2283 */           arrayOfFloat[b1++] = (this.colors4[b2]).x;
/* 2284 */           arrayOfFloat[b1++] = (this.colors4[b2]).y;
/* 2285 */           arrayOfFloat[b1++] = (this.colors4[b2]).z;
/* 2286 */           arrayOfFloat[b1++] = (this.colors4[b2]).w;
/*      */         } 
/*      */         
/* 2289 */         if (this.normals != null) {
/* 2290 */           arrayOfFloat[b1++] = (this.normals[b2]).x;
/* 2291 */           arrayOfFloat[b1++] = (this.normals[b2]).y;
/* 2292 */           arrayOfFloat[b1++] = (this.normals[b2]).z;
/*      */         } 
/*      */         
/* 2295 */         arrayOfFloat[b1++] = (this.coordinates[b2]).x;
/* 2296 */         arrayOfFloat[b1++] = (this.coordinates[b2]).y;
/* 2297 */         arrayOfFloat[b1++] = (this.coordinates[b2]).z;
/*      */       } 
/*      */       
/* 2300 */       if (paramBoolean3)
/* 2301 */       { ByteBufferWrapper byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfFloat.length * 4);
/* 2302 */         FloatBufferWrapper floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*      */         
/* 2304 */         floatBufferWrapper.put(arrayOfFloat);
/* 2305 */         paramGeometryArray.setInterleavedVertexBuffer(floatBufferWrapper.getJ3DBuffer()); }
/* 2306 */       else { paramGeometryArray.setInterleavedVertices(arrayOfFloat); } 
/* 2307 */     } else if (paramBoolean3) {
/*      */       
/* 2309 */       ByteBufferWrapper byteBufferWrapper = ByteBufferWrapper.allocateDirect(this.coordinates.length * 4 * 3);
/*      */       
/* 2311 */       FloatBufferWrapper floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*      */       
/* 2313 */       floatBufferWrapper.put(vecmathToFloat(this.coordinates));
/* 2314 */       paramGeometryArray.setCoordRefBuffer(floatBufferWrapper.getJ3DBuffer());
/*      */       
/* 2316 */       if (this.colors3 != null) {
/* 2317 */         byteBufferWrapper = ByteBufferWrapper.allocateDirect(this.colors3.length * 4 * 3);
/* 2318 */         floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/* 2319 */         floatBufferWrapper.put(vecmathToFloat(this.colors3));
/* 2320 */         paramGeometryArray.setColorRefBuffer(floatBufferWrapper.getJ3DBuffer());
/* 2321 */       } else if (this.colors4 != null) {
/* 2322 */         byteBufferWrapper = ByteBufferWrapper.allocateDirect(this.colors4.length * 4 * 4);
/* 2323 */         floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/* 2324 */         floatBufferWrapper.put(vecmathToFloat(this.colors4));
/* 2325 */         paramGeometryArray.setColorRefBuffer(floatBufferWrapper.getJ3DBuffer());
/*      */       } 
/*      */       
/* 2328 */       if (this.normals != null) {
/* 2329 */         byteBufferWrapper = ByteBufferWrapper.allocateDirect(this.normals.length * 4 * 3);
/* 2330 */         floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/* 2331 */         floatBufferWrapper.put(vecmathToFloat(this.normals));
/* 2332 */         paramGeometryArray.setNormalRefBuffer(floatBufferWrapper.getJ3DBuffer());
/*      */       } 
/*      */       
/* 2335 */       for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2336 */         byteBufferWrapper = ByteBufferWrapper.allocateDirect(this.texCoordSets[b].length * 4 * this.texCoordDim);
/*      */         
/* 2338 */         floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/* 2339 */         floatBufferWrapper.put(vecmathToFloat(this.texCoordSets[b]));
/* 2340 */         paramGeometryArray.setTexCoordRefBuffer(b, floatBufferWrapper.getJ3DBuffer());
/*      */       } 
/* 2342 */     } else if (paramBoolean1) {
/*      */ 
/*      */       
/* 2345 */       paramGeometryArray.setCoordRefFloat(vecmathToFloat(this.coordinates));
/* 2346 */       if (this.colors3 != null) { paramGeometryArray.setColorRefFloat(vecmathToFloat(this.colors3)); }
/* 2347 */       else if (this.colors4 != null) { paramGeometryArray.setColorRefFloat(vecmathToFloat(this.colors4)); }
/* 2348 */        if (this.normals != null) paramGeometryArray.setNormalRefFloat(vecmathToFloat(this.normals)); 
/* 2349 */       for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2350 */         paramGeometryArray.setTexCoordRefFloat(b, vecmathToFloat(this.texCoordSets[b]));
/*      */       }
/*      */     } else {
/* 2353 */       paramGeometryArray.setCoordinates(0, this.coordinates);
/* 2354 */       if (this.colors3 != null) { paramGeometryArray.setColors(0, this.colors3); }
/* 2355 */       else if (this.colors4 != null) { paramGeometryArray.setColors(0, this.colors4); }
/* 2356 */        if (this.normals != null) paramGeometryArray.setNormals(0, this.normals); 
/* 2357 */       for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2358 */         if (this.texCoordDim == 2) {
/* 2359 */           paramGeometryArray.setTextureCoordinates(b, 0, (TexCoord2f[])this.texCoordSets[b]);
/* 2360 */         } else if (this.texCoordDim == 3) {
/* 2361 */           paramGeometryArray.setTextureCoordinates(b, 0, (TexCoord3f[])this.texCoordSets[b]);
/* 2362 */         } else if (this.texCoordDim == 4) {
/* 2363 */           paramGeometryArray.setTextureCoordinates(b, 0, (TexCoord4f[])this.texCoordSets[b]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2368 */     if (this.coordinateIndices != null) {
/* 2369 */       IndexedGeometryArray indexedGeometryArray = null;
/* 2370 */       indexedGeometryArray = (IndexedGeometryArray)paramGeometryArray;
/* 2371 */       indexedGeometryArray.setCoordinateIndices(0, this.coordinateIndices);
/* 2372 */       if (!this.coordOnly) {
/* 2373 */         if (this.colorIndices != null) indexedGeometryArray.setColorIndices(0, this.colorIndices); 
/* 2374 */         if (this.normalIndices != null) indexedGeometryArray.setNormalIndices(0, this.normalIndices); 
/* 2375 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2376 */           indexedGeometryArray.setTextureCoordinateIndices(b, 0, this.texCoordIndexSets[b]);
/*      */         }
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void recomputeIndices() {
/* 2406 */     boolean bool = this.coordOnly;
/*      */ 
/*      */     
/* 2409 */     unindexify();
/* 2410 */     indexify(bool);
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
/*      */   private void reverseList(int[] paramArrayOfInt) {
/* 2423 */     if (paramArrayOfInt == null)
/*      */       return; 
/* 2425 */     for (int i = 0; i < paramArrayOfInt.length / 2; i++) {
/* 2426 */       int j = paramArrayOfInt[i];
/* 2427 */       paramArrayOfInt[i] = paramArrayOfInt[paramArrayOfInt.length - i - 1];
/* 2428 */       paramArrayOfInt[paramArrayOfInt.length - i - 1] = j;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reverse() {
/* 2455 */     indexify();
/* 2456 */     reverseList(this.stripCounts);
/* 2457 */     reverseList(this.oldStripCounts);
/* 2458 */     reverseList(this.contourCounts);
/* 2459 */     reverseList(this.coordinateIndices);
/* 2460 */     reverseList(this.colorIndices);
/* 2461 */     reverseList(this.normalIndices);
/* 2462 */     for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 2463 */       reverseList(this.texCoordIndexSets[b]);
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
/*      */   
/* 2478 */   public boolean getUseCoordIndexOnly() { return this.coordOnly; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2503 */   public void setUseCoordIndexOnly(boolean paramBoolean) { this.coordOnly = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GeometryArray getGeometryArray(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/*      */     TriangleFanArray triangleFanArray2;
/*      */     TriangleStripArray triangleStripArray2;
/*      */     QuadArray quadArray2;
/*      */     TriangleArray triangleArray2;
/*      */     QuadArray quadArray1;
/*      */     TriangleStripArray triangleStripArray1;
/*      */     TriangleFanArray triangleFanArray1;
/* 2532 */     checkForBadData();
/*      */     
/* 2534 */     if (this.prim == 5)
/* 2535 */     { if (this.tr == null) this.tr = new Triangulator(); 
/* 2536 */       this.tr.triangulate(this); }
/* 2537 */     else { changeBackToOldPrim(); }
/*      */     
/* 2539 */     unindexify();
/*      */     
/* 2541 */     int i = getVertexFormat();
/* 2542 */     if (paramBoolean3) i |= 0x880;
/*      */     
/* 2544 */     if (paramBoolean2) i |= 0x180;
/*      */     
/* 2546 */     if (paramBoolean1) i |= 0x80;
/*      */     
/* 2548 */     int j = this.coordinates.length;
/*      */ 
/*      */ 
/*      */     
/* 2552 */     if (this.texCoordSetCount > 0 && this.texCoordSetMap == null) {
/* 2553 */       this.texCoordSetCount = 1;
/* 2554 */       this.texCoordSetMap = new int[1];
/* 2555 */       this.texCoordSetMap[0] = 0;
/*      */     } 
/*      */ 
/*      */     
/* 2559 */     TriangleArray triangleArray1 = null;
/* 2560 */     switch (this.prim) {
/*      */       case 1:
/* 2562 */         triangleArray2 = new TriangleArray(j, i, this.texCoordSetCount, this.texCoordSetMap);
/*      */         
/* 2564 */         triangleArray1 = triangleArray2;
/*      */         break;
/*      */       
/*      */       case 2:
/* 2568 */         quadArray2 = new QuadArray(j, i, this.texCoordSetCount, this.texCoordSetMap);
/*      */         
/* 2570 */         quadArray1 = quadArray2;
/*      */         break;
/*      */       
/*      */       case 4:
/* 2574 */         triangleStripArray2 = new TriangleStripArray(j, i, this.texCoordSetCount, this.texCoordSetMap, this.stripCounts);
/*      */ 
/*      */         
/* 2577 */         triangleStripArray1 = triangleStripArray2;
/*      */         break;
/*      */       
/*      */       case 3:
/* 2581 */         triangleFanArray2 = new TriangleFanArray(j, i, this.texCoordSetCount, this.texCoordSetMap, this.stripCounts);
/*      */ 
/*      */         
/* 2584 */         triangleFanArray1 = triangleFanArray2;
/*      */         break;
/*      */     } 
/*      */     
/* 2588 */     fillIn(triangleFanArray1, paramBoolean1, paramBoolean2, paramBoolean3);
/*      */     
/* 2590 */     return triangleFanArray1;
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
/* 2614 */   public GeometryArray getGeometryArray() { return getGeometryArray(false, false, false); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IndexedGeometryArray getIndexedGeometryArray(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5) {
/*      */     IndexedTriangleFanArray indexedTriangleFanArray2;
/*      */     IndexedTriangleStripArray indexedTriangleStripArray2;
/*      */     IndexedQuadArray indexedQuadArray2;
/*      */     IndexedTriangleArray indexedTriangleArray2;
/*      */     IndexedTriangleFanArray indexedTriangleFanArray1;
/*      */     IndexedTriangleStripArray indexedTriangleStripArray1;
/*      */     IndexedQuadArray indexedQuadArray1;
/* 2653 */     indexify(paramBoolean4);
/*      */     
/* 2655 */     if (paramBoolean1) compact();
/*      */     
/* 2657 */     if (this.prim == 5)
/* 2658 */     { if (this.tr == null) this.tr = new Triangulator(); 
/* 2659 */       this.tr.triangulate(this); }
/* 2660 */     else { changeBackToOldPrim(); }
/*      */     
/* 2662 */     if (paramBoolean4 && !this.coordOnly) {
/*      */ 
/*      */       
/* 2665 */       boolean bool = true;
/*      */       
/* 2667 */       if (this.coordinateIndices != null) {
/*      */         
/* 2669 */         if (this.colorIndices != null && this.colorIndices.length != this.coordinateIndices.length)
/*      */         {
/* 2671 */           bool = false;
/*      */         }
/* 2673 */         if (this.normalIndices != null && this.normalIndices.length != this.coordinateIndices.length)
/*      */         {
/* 2675 */           bool = false; } 
/*      */         byte b;
/* 2677 */         for (b = 0; b < this.texCoordSetCount; b++) {
/* 2678 */           if (this.texCoordIndexSets[b] != null && this.texCoordIndexSets[b].length != this.coordinateIndices.length) {
/*      */             
/* 2680 */             bool = false;
/*      */             break;
/*      */           } 
/*      */         } 
/* 2684 */         if (bool && (this.colorIndices != null || this.normalIndices != null || this.texCoordSetCount > 0))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2690 */           for (b = 0; b < this.coordinateIndices.length; b++) {
/* 2691 */             int k = this.coordinateIndices[b];
/*      */ 
/*      */             
/* 2694 */             if (this.colorIndices != null && this.colorIndices[b] != k) {
/*      */               
/* 2696 */               bool = false;
/*      */               break;
/*      */             } 
/* 2699 */             if (this.normalIndices != null && this.normalIndices[b] != k) {
/*      */               
/* 2701 */               bool = false;
/*      */               break;
/*      */             } 
/* 2704 */             for (byte b1 = 0; b1 < this.texCoordSetCount; b1++) {
/* 2705 */               if (this.texCoordIndexSets[b1] != null && this.texCoordIndexSets[b1][b] != k) {
/*      */                 
/* 2707 */                 bool = false;
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/* 2714 */       this.coordOnly = bool;
/*      */     } 
/*      */     
/* 2717 */     int i = getVertexFormat();
/* 2718 */     if (paramBoolean5) i |= 0x880;
/*      */     
/* 2720 */     if (paramBoolean3) i |= 0x180;
/*      */     
/* 2722 */     if (paramBoolean2) i |= 0x80; 
/* 2723 */     if (this.coordOnly) i |= 0x200;
/*      */     
/* 2725 */     int j = getVertexCount();
/*      */     
/* 2727 */     if (this.texCoordSetCount > 0 && this.texCoordSetMap == null) {
/* 2728 */       this.texCoordSetCount = 1;
/* 2729 */       this.texCoordSetMap = new int[1];
/* 2730 */       this.texCoordSetMap[0] = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2737 */     IndexedTriangleArray indexedTriangleArray1 = null;
/*      */     
/* 2739 */     switch (this.prim) {
/*      */       case 1:
/* 2741 */         indexedTriangleArray2 = new IndexedTriangleArray(j, i, this.texCoordSetCount, this.texCoordSetMap, this.coordinateIndices.length);
/*      */ 
/*      */         
/* 2744 */         indexedTriangleArray1 = indexedTriangleArray2;
/*      */         break;
/*      */       
/*      */       case 2:
/* 2748 */         indexedQuadArray2 = new IndexedQuadArray(j, i, this.texCoordSetCount, this.texCoordSetMap, this.coordinateIndices.length);
/*      */ 
/*      */         
/* 2751 */         indexedQuadArray1 = indexedQuadArray2;
/*      */         break;
/*      */       case 4:
/* 2754 */         indexedTriangleStripArray2 = new IndexedTriangleStripArray(j, i, this.texCoordSetCount, this.texCoordSetMap, this.coordinateIndices.length, this.stripCounts);
/*      */ 
/*      */         
/* 2757 */         indexedTriangleStripArray1 = indexedTriangleStripArray2;
/*      */         break;
/*      */       
/*      */       case 3:
/* 2761 */         indexedTriangleFanArray2 = new IndexedTriangleFanArray(j, i, this.texCoordSetCount, this.texCoordSetMap, this.coordinateIndices.length, this.stripCounts);
/*      */ 
/*      */         
/* 2764 */         indexedTriangleFanArray1 = indexedTriangleFanArray2;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 2769 */     fillIn(indexedTriangleFanArray1, paramBoolean2, paramBoolean3, paramBoolean5);
/*      */     
/* 2771 */     return indexedTriangleFanArray1;
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
/* 2797 */   public IndexedGeometryArray getIndexedGeometryArray(boolean paramBoolean) { return getIndexedGeometryArray(paramBoolean, false, false, false, false); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2821 */   public IndexedGeometryArray getIndexedGeometryArray() { return getIndexedGeometryArray(false, false, false, false, false); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\GeometryInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */