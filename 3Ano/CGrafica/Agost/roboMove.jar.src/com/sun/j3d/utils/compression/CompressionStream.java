/*      */ package com.sun.j3d.utils.compression;
/*      */ 
/*      */ import com.sun.j3d.internal.BufferWrapper;
/*      */ import com.sun.j3d.internal.ByteBufferWrapper;
/*      */ import com.sun.j3d.internal.DoubleBufferWrapper;
/*      */ import com.sun.j3d.internal.FloatBufferWrapper;
/*      */ import com.sun.j3d.utils.geometry.GeometryInfo;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import javax.media.j3d.Appearance;
/*      */ import javax.media.j3d.Geometry;
/*      */ import javax.media.j3d.GeometryArray;
/*      */ import javax.media.j3d.GeometryStripArray;
/*      */ import javax.media.j3d.IndexedGeometryArray;
/*      */ import javax.media.j3d.IndexedGeometryStripArray;
/*      */ import javax.media.j3d.J3DBuffer;
/*      */ import javax.media.j3d.Material;
/*      */ import javax.media.j3d.Shape3D;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Point3i;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CompressionStream
/*      */ {
/*      */   private static final boolean debug = false;
/*      */   private static final boolean benchmark = false;
/*      */   private static final boolean noMeshNormalSubstitution = true;
/*      */   static final int RESTART = 1;
/*      */   static final int REPLACE_MIDDLE = 2;
/*      */   static final int REPLACE_OLDEST = 3;
/*      */   static final int MESH_PUSH = 1;
/*      */   static final int NO_MESH_PUSH = 0;
/*      */   static final float ByteToFloatScale = 0.003921569F;
/*      */   int streamType;
/*      */   int vertexComponents;
/*      */   boolean vertexColors;
/*      */   boolean vertexColor3;
/*      */   boolean vertexColor4;
/*      */   boolean vertexNormals;
/*      */   boolean vertexTextures;
/*      */   boolean vertexTexture2;
/*      */   boolean vertexTexture3;
/*      */   boolean vertexTexture4;
/*      */   Point3d[] mcBounds;
/*      */   Point3d[] ncBounds;
/*      */   Point3i[] qcBounds;
/*      */   double[] center;
/*      */   double positionRangeMaximum;
/*      */   double scale;
/*      */   int positionQuant;
/*      */   int colorQuant;
/*      */   int normalQuant;
/*      */   boolean positionQuantChanged;
/*      */   boolean colorQuantChanged;
/*      */   boolean normalQuantChanged;
/*      */   int[] lastPosition;
/*      */   int[] lastColor;
/*      */   int lastSextant;
/*      */   int lastOctant;
/*      */   int lastU;
/*      */   int lastV;
/*      */   boolean lastSpecialNormal;
/*      */   boolean firstPosition;
/*      */   boolean firstColor;
/*      */   boolean firstNormal;
/*      */   int byteCount;
/*      */   int vertexCount;
/*      */   int meshReferenceCount;
/*      */   MeshBuffer meshBuffer;
/*      */   private Collection stream;
/*      */   private boolean lastElementColor;
/*      */   private boolean lastLastElementColor;
/*      */   private boolean lastElementNormal;
/*      */   private boolean lastLastElementNormal;
/*      */   private Point3f p3f;
/*      */   private Color3f c3f;
/*      */   private Color4f c4f;
/*      */   private Vector3f n3f;
/*      */   
/*      */   private CompressionStream() {
/*  230 */     this.mcBounds = new Point3d[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  235 */     this.ncBounds = new Point3d[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  240 */     this.qcBounds = new Point3i[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  245 */     this.center = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  308 */     this.lastPosition = new int[3];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  313 */     this.lastColor = new int[4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  377 */     this.meshBuffer = new MeshBuffer();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  386 */     this.lastElementColor = false;
/*  387 */     this.lastLastElementColor = false;
/*  388 */     this.lastElementNormal = false;
/*  389 */     this.lastLastElementNormal = false;
/*      */ 
/*      */     
/*  392 */     this.p3f = new Point3f();
/*  393 */     this.c3f = new Color3f();
/*  394 */     this.c4f = new Color4f();
/*  395 */     this.n3f = new Vector3f();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  400 */     this.stream = new LinkedList();
/*      */     
/*  402 */     this.byteCount = 0;
/*  403 */     this.vertexCount = 0;
/*  404 */     this.meshReferenceCount = 0;
/*      */     
/*  406 */     this.mcBounds[0] = new Point3d(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*      */ 
/*      */     
/*  409 */     this.mcBounds[1] = new Point3d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
/*      */ 
/*      */ 
/*      */     
/*  413 */     this.qcBounds[0] = new Point3i(2147483647, 2147483647, 2147483647);
/*      */ 
/*      */     
/*  416 */     this.qcBounds[1] = new Point3i(-2147483648, -2147483648, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  421 */     this.ncBounds[0] = new Point3d();
/*  422 */     this.ncBounds[1] = new Point3d();
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
/*      */   CompressionStream(int paramInt1, int paramInt2) {
/*  442 */     this();
/*  443 */     this.streamType = paramInt1;
/*  444 */     this.vertexComponents = getVertexComponents(paramInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getVertexComponents(int paramInt) {
/*  451 */     char c = Character.MIN_VALUE;
/*      */     
/*  453 */     this.vertexColors = this.vertexColor3 = this.vertexColor4 = this.vertexNormals = this.vertexTextures = this.vertexTexture2 = this.vertexTexture3 = this.vertexTexture4 = false;
/*      */ 
/*      */ 
/*      */     
/*  457 */     if ((paramInt & 0x2) != 0) {
/*  458 */       this.vertexNormals = true;
/*  459 */       c &= 0x2;
/*      */     } 
/*      */ 
/*      */     
/*  463 */     if ((paramInt & 0x4) != 0) {
/*  464 */       this.vertexColors = true;
/*      */       
/*  466 */       if ((paramInt & 0xC) != 0) {
/*  467 */         this.vertexColor4 = true;
/*  468 */         c &= 0xC;
/*      */       }
/*      */       else {
/*      */         
/*  472 */         this.vertexColor3 = true;
/*  473 */         c &= 0x4;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  478 */     if ((paramInt & 0x20) != 0) {
/*  479 */       this.vertexTextures = true;
/*  480 */       this.vertexTexture2 = true;
/*  481 */       c &= 0x20;
/*      */     
/*      */     }
/*  484 */     else if ((paramInt & 0x40) != 0) {
/*  485 */       this.vertexTextures = true;
/*  486 */       this.vertexTexture3 = true;
/*  487 */       c &= 0x40;
/*      */     
/*      */     }
/*  490 */     else if ((paramInt & 0x400) != 0) {
/*  491 */       this.vertexTextures = true;
/*  492 */       this.vertexTexture4 = true;
/*  493 */       c &= 0x400;
/*      */     } 
/*      */ 
/*      */     
/*  497 */     if (this.vertexTextures)
/*      */     {
/*  499 */       throw new UnsupportedOperationException("\ncompression of texture coordinates is not supported");
/*      */     }
/*      */     
/*  502 */     return c;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getStreamType(GeometryArray paramGeometryArray) {
/*  507 */     if (paramGeometryArray instanceof javax.media.j3d.TriangleStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleStripArray || paramGeometryArray instanceof javax.media.j3d.TriangleFanArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleFanArray || paramGeometryArray instanceof javax.media.j3d.TriangleArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleArray || paramGeometryArray instanceof javax.media.j3d.QuadArray || paramGeometryArray instanceof javax.media.j3d.IndexedQuadArray)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  516 */       return 2;
/*      */     }
/*  518 */     if (paramGeometryArray instanceof javax.media.j3d.LineArray || paramGeometryArray instanceof javax.media.j3d.IndexedLineArray || paramGeometryArray instanceof javax.media.j3d.LineStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedLineStripArray)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  523 */       return 1;
/*      */     }
/*      */     
/*  526 */     return 0;
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
/*      */   void quantize(HuffmanTable paramHuffmanTable) {
/*  560 */     this.positionQuant = 16;
/*  561 */     this.colorQuant = 9;
/*  562 */     this.normalQuant = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  567 */     this.center[0] = ((this.mcBounds[1]).x + (this.mcBounds[0]).x) / 2.0D;
/*  568 */     this.center[1] = ((this.mcBounds[1]).y + (this.mcBounds[0]).y) / 2.0D;
/*  569 */     this.center[2] = ((this.mcBounds[1]).z + (this.mcBounds[0]).z) / 2.0D;
/*      */     
/*  571 */     double d1 = (this.mcBounds[1]).x - (this.mcBounds[0]).x;
/*  572 */     double d2 = (this.mcBounds[1]).y - (this.mcBounds[0]).y;
/*  573 */     double d3 = (this.mcBounds[1]).z - (this.mcBounds[0]).z;
/*      */     
/*  575 */     if (d1 > d2) {
/*  576 */       this.positionRangeMaximum = d1;
/*      */     } else {
/*  578 */       this.positionRangeMaximum = d2;
/*      */     } 
/*  580 */     if (d3 > this.positionRangeMaximum) {
/*  581 */       this.positionRangeMaximum = d3;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  597 */     this.scale = 2.0D / this.positionRangeMaximum * 0.999969482421875D;
/*      */ 
/*      */     
/*  600 */     this.positionQuantChanged = this.colorQuantChanged = this.normalQuantChanged = true;
/*      */ 
/*      */     
/*  603 */     this.firstPosition = this.firstColor = this.firstNormal = true;
/*      */ 
/*      */     
/*  606 */     Iterator iterator = this.stream.iterator();
/*  607 */     while (iterator.hasNext()) {
/*  608 */       Object object = iterator.next();
/*      */       
/*  610 */       if (object instanceof CompressionStreamElement) {
/*  611 */         ((CompressionStreamElement)object).quantize(this, paramHuffmanTable);
/*      */ 
/*      */ 
/*      */         
/*  615 */         this.lastLastElementColor = this.lastElementColor;
/*  616 */         this.lastLastElementNormal = this.lastElementNormal;
/*  617 */         this.lastElementColor = this.lastElementNormal = false;
/*      */         
/*  619 */         if (object instanceof CompressionStreamColor) {
/*  620 */           this.lastElementColor = true; continue;
/*  621 */         }  if (object instanceof CompressionStreamNormal) {
/*  622 */           this.lastElementNormal = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  627 */     (this.ncBounds[0]).x = (this.qcBounds[0]).x / 32768.0D;
/*  628 */     (this.ncBounds[0]).y = (this.qcBounds[0]).y / 32768.0D;
/*  629 */     (this.ncBounds[0]).z = (this.qcBounds[0]).z / 32768.0D;
/*      */     
/*  631 */     (this.ncBounds[1]).x = (this.qcBounds[1]).x / 32768.0D;
/*  632 */     (this.ncBounds[1]).y = (this.qcBounds[1]).y / 32768.0D;
/*  633 */     (this.ncBounds[1]).z = (this.qcBounds[1]).z / 32768.0D;
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
/*      */   void outputCommands(HuffmanTable paramHuffmanTable, CommandStream paramCommandStream) {
/*  660 */     boolean bool1 = this.vertexNormals ? 1 : 0;
/*  661 */     boolean bool2 = (this.vertexColor3 || this.vertexColor4) ? 1 : 0;
/*  662 */     boolean bool3 = this.vertexColor4 ? 1 : 0;
/*      */     
/*  664 */     byte b = 0x18 | bool1;
/*  665 */     long l = (bool2 << 2 | bool3 << true);
/*      */ 
/*      */     
/*  668 */     paramCommandStream.addCommand(b, 8, l, 3);
/*      */ 
/*      */     
/*  671 */     paramHuffmanTable.outputCommands(paramCommandStream);
/*      */ 
/*      */     
/*  674 */     Iterator iterator = this.stream.iterator();
/*  675 */     while (iterator.hasNext()) {
/*  676 */       Object object = iterator.next();
/*  677 */       if (object instanceof CompressionStreamElement) {
/*  678 */         ((CompressionStreamElement)object).outputCommand(paramHuffmanTable, paramCommandStream);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  683 */     paramCommandStream.end();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  692 */   int getByteCount() { return this.byteCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  701 */   int getVertexCount() { return this.vertexCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  709 */   int getMeshReferenceCount() { return this.meshReferenceCount; }
/*      */ 
/*      */ 
/*      */   
/*      */   private class PositionQuant
/*      */     extends CompressionStreamElement
/*      */   {
/*      */     int value;
/*      */ 
/*      */     
/*  719 */     PositionQuant(int param1Int) { this.value = param1Int; }
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  723 */       CompressionStream.this.positionQuant = this.value;
/*  724 */       CompressionStream.this.positionQuantChanged = true;
/*      */ 
/*      */       
/*  727 */       CompressionStream.this.scale = 2.0D / CompressionStream.this.positionRangeMaximum * ((1 << this.value - 1) - 1) / (1 << this.value - 1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  732 */     public String toString() { return "positionQuant: " + this.value; }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class NormalQuant
/*      */     extends CompressionStreamElement
/*      */   {
/*      */     int value;
/*      */ 
/*      */     
/*  743 */     NormalQuant(int param1Int) { this.value = param1Int; }
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  747 */       CompressionStream.this.normalQuant = this.value;
/*  748 */       CompressionStream.this.normalQuantChanged = true;
/*      */     }
/*      */ 
/*      */     
/*  752 */     public String toString() { return "normalQuant: " + this.value; }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class ColorQuant
/*      */     extends CompressionStreamElement
/*      */   {
/*      */     int value;
/*      */ 
/*      */     
/*  763 */     ColorQuant(int param1Int) { this.value = param1Int; }
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  767 */       CompressionStream.this.colorQuant = this.value;
/*  768 */       CompressionStream.this.colorQuantChanged = true;
/*      */     }
/*      */ 
/*      */     
/*  772 */     public String toString() { return "colorQuant: " + this.value; }
/*      */   }
/*      */ 
/*      */   
/*      */   private class MeshReference
/*      */     extends CompressionStreamElement
/*      */   {
/*      */     int stripFlag;
/*      */     int meshIndex;
/*      */     
/*      */     MeshReference(int param1Int1, int param1Int2) {
/*  783 */       this.stripFlag = param1Int1;
/*  784 */       this.meshIndex = param1Int2;
/*  785 */       CompressionStream.this.meshReferenceCount++;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  791 */       CompressionStreamVertex compressionStreamVertex = CompressionStream.this.meshBuffer.getVertex(this.meshIndex);
/*  792 */       CompressionStream.this.lastPosition[0] = compressionStreamVertex.xAbsolute;
/*  793 */       CompressionStream.this.lastPosition[1] = compressionStreamVertex.yAbsolute;
/*  794 */       CompressionStream.this.lastPosition[2] = compressionStreamVertex.zAbsolute;
/*      */ 
/*      */ 
/*      */       
/*  798 */       if (compressionStreamVertex.color != null && !CompressionStream.this.lastElementColor && (!CompressionStream.this.lastElementNormal || !CompressionStream.this.lastLastElementColor)) {
/*      */         
/*  800 */         CompressionStream.this.lastColor[0] = compressionStreamVertex.color.rAbsolute;
/*  801 */         CompressionStream.this.lastColor[1] = compressionStreamVertex.color.gAbsolute;
/*  802 */         CompressionStream.this.lastColor[2] = compressionStreamVertex.color.bAbsolute;
/*  803 */         CompressionStream.this.lastColor[3] = compressionStreamVertex.color.aAbsolute;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  808 */       if (compressionStreamVertex.normal != null && !CompressionStream.this.lastElementNormal && (!CompressionStream.this.lastElementColor || !CompressionStream.this.lastLastElementNormal)) {
/*      */         
/*  810 */         CompressionStream.this.lastSextant = compressionStreamVertex.normal.sextant;
/*  811 */         CompressionStream.this.lastOctant = compressionStreamVertex.normal.octant;
/*  812 */         CompressionStream.this.lastU = compressionStreamVertex.normal.uAbsolute;
/*  813 */         CompressionStream.this.lastV = compressionStreamVertex.normal.vAbsolute;
/*  814 */         CompressionStream.this.lastSpecialNormal = compressionStreamVertex.normal.specialNormal;
/*      */       } 
/*      */     }
/*      */     
/*      */     void outputCommand(HuffmanTable param1HuffmanTable, CommandStream param1CommandStream) {
/*  819 */       int i = 32;
/*  820 */       long l = (this.stripFlag & true);
/*      */       
/*  822 */       i |= (this.meshIndex & 0xF) << 1 | this.stripFlag >> 1;
/*  823 */       param1CommandStream.addCommand(i, 8, l, 1);
/*      */     }
/*      */ 
/*      */     
/*  827 */     public String toString() { return "meshReference: stripFlag " + this.stripFlag + " meshIndex " + this.meshIndex; }
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
/*  841 */   void addVertex(Point3f paramPoint3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, (Color3f)null, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  854 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color3f)null, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  866 */   void addVertex(Point3f paramPoint3f, Color3f paramColor3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor3f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  878 */   void addVertex(Point3f paramPoint3f, Color4f paramColor4f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor4f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  892 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color3f paramColor3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor3f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  906 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor4f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  918 */   void addVertex(Point3f paramPoint3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, (Color3f)null, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  932 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color3f)null, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  946 */   void addVertex(Point3f paramPoint3f, Color3f paramColor3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor3f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  960 */   void addVertex(Point3f paramPoint3f, Color4f paramColor4f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor4f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  975 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color3f paramColor3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor3f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  990 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor4f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Object paramObject, int paramInt1, int paramInt2) {
/* 1007 */     if (this.vertexColor3) {
/* 1008 */       this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color3f)paramObject, paramInt1, paramInt2));
/*      */     } else {
/*      */       
/* 1011 */       this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color4f)paramObject, paramInt1, paramInt2));
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
/* 1022 */   void addMeshReference(int paramInt1, int paramInt2) { this.stream.add(new MeshReference(paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1030 */   void addColor(Color3f paramColor3f) { this.stream.add(new CompressionStreamColor(this, paramColor3f)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1038 */   void addColor(Color4f paramColor4f) { this.stream.add(new CompressionStreamColor(this, paramColor4f)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1046 */   void addNormal(Vector3f paramVector3f) { this.stream.add(new CompressionStreamNormal(this, paramVector3f)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1057 */   void addPositionQuantization(int paramInt) { this.stream.add(new PositionQuant(paramInt)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1068 */   void addColorQuantization(int paramInt) { this.stream.add(new ColorQuant(paramInt)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1080 */   void addNormalQuantization(int paramInt) { this.stream.add(new NormalQuant(paramInt)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class ByCopyGeometry
/*      */     implements GeometryAccessor
/*      */   {
/*      */     Point3f[] positions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Vector3f[] normals;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Color3f[] colors3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Color4f[] colors4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1118 */     ByCopyGeometry(CompressionStream this$0, GeometryArray param1GeometryArray) { this(param1GeometryArray, param1GeometryArray.getInitialVertexIndex(), param1GeometryArray.getValidVertexCount()); }
/*      */     ByCopyGeometry(GeometryArray param1GeometryArray, int param1Int1, int param1Int2) {
/*      */       this.positions = null;
/*      */       this.normals = null;
/*      */       this.colors3 = null;
/*      */       this.colors4 = null;
/* 1124 */       this.positions = new Point3f[param1Int2]; byte b;
/* 1125 */       for (b = 0; b < param1Int2; b++) {
/* 1126 */         this.positions[b] = new Point3f();
/*      */       }
/* 1128 */       param1GeometryArray.getCoordinates(param1Int1, this.positions);
/*      */       
/* 1130 */       if (CompressionStream.this.vertexNormals) {
/* 1131 */         this.normals = new Vector3f[param1Int2];
/* 1132 */         for (b = 0; b < param1Int2; b++) {
/* 1133 */           this.normals[b] = new Vector3f();
/*      */         }
/* 1135 */         param1GeometryArray.getNormals(param1Int1, this.normals);
/*      */       } 
/*      */       
/* 1138 */       if (CompressionStream.this.vertexColor3) {
/* 1139 */         this.colors3 = new Color3f[param1Int2];
/* 1140 */         for (b = 0; b < param1Int2; b++) {
/* 1141 */           this.colors3[b] = new Color3f();
/*      */         }
/* 1143 */         param1GeometryArray.getColors(param1Int1, this.colors3);
/*      */       }
/* 1145 */       else if (CompressionStream.this.vertexColor4) {
/* 1146 */         this.colors4 = new Color4f[param1Int2];
/* 1147 */         for (b = 0; b < param1Int2; b++) {
/* 1148 */           this.colors4[b] = new Color4f();
/*      */         }
/* 1150 */         param1GeometryArray.getColors(param1Int1, this.colors4);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1155 */       Point3f point3f = this.positions[param1Int1];
/* 1156 */       int i = CompressionStream.this.meshBuffer.getMeshReference(point3f);
/*      */       
/* 1158 */       CompressionStream.this.meshBuffer; if (i == -1 || (CompressionStream.this.vertexNormals && !this.normals[param1Int1].equals(CompressionStream.this.meshBuffer.getNormal(i)))) {
/*      */ 
/*      */ 
/*      */         
/* 1162 */         Vector3f vector3f = CompressionStream.this.vertexNormals ? this.normals[param1Int1] : null;
/* 1163 */         Color3f color3f = CompressionStream.this.vertexColor3 ? this.colors3[param1Int1] : (CompressionStream.this.vertexColor4 ? this.colors4[param1Int1] : null);
/*      */ 
/*      */         
/* 1166 */         CompressionStream.this.addVertex(point3f, vector3f, color3f, param1Int2, 1);
/* 1167 */         CompressionStream.this.meshBuffer.push(point3f, color3f, vector3f);
/*      */       } else {
/*      */         
/* 1170 */         if (CompressionStream.this.vertexNormals);
/*      */ 
/*      */ 
/*      */         
/* 1174 */         if (CompressionStream.this.vertexColor3 && !this.colors3[param1Int1].equals(CompressionStream.this.meshBuffer.getColor3(i))) {
/*      */           
/* 1176 */           CompressionStream.this.addColor(this.colors3[param1Int1]);
/*      */         }
/* 1178 */         else if (CompressionStream.this.vertexColor4 && !this.colors4[param1Int1].equals(CompressionStream.this.meshBuffer.getColor4(i))) {
/*      */           
/* 1180 */           CompressionStream.this.addColor(this.colors4[param1Int1]);
/*      */         } 
/* 1182 */         CompressionStream.this.addMeshReference(param1Int2, i);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class IndexArrays
/*      */   {
/*      */     private IndexArrays() {}
/*      */     
/* 1191 */     int[] colorIndices = null;
/* 1192 */     int[] normalIndices = null;
/* 1193 */     int[] positionIndices = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getIndexArrays(GeometryArray paramGeometryArray, IndexArrays paramIndexArrays) {
/* 1201 */     IndexedGeometryArray indexedGeometryArray = (IndexedGeometryArray)paramGeometryArray;
/*      */     
/* 1203 */     int i = indexedGeometryArray.getInitialIndexIndex();
/* 1204 */     int j = indexedGeometryArray.getValidIndexCount();
/* 1205 */     int k = indexedGeometryArray.getVertexFormat();
/*      */     
/* 1207 */     boolean bool = false;
/* 1208 */     if ((k & 0x200) != 0)
/*      */     {
/* 1210 */       bool = true;
/*      */     }
/*      */     
/* 1213 */     paramIndexArrays.positionIndices = new int[j];
/* 1214 */     indexedGeometryArray.getCoordinateIndices(i, paramIndexArrays.positionIndices);
/*      */     
/* 1216 */     if (this.vertexNormals) {
/* 1217 */       if (bool) {
/* 1218 */         paramIndexArrays.normalIndices = paramIndexArrays.positionIndices;
/*      */       } else {
/*      */         
/* 1221 */         paramIndexArrays.normalIndices = new int[j];
/* 1222 */         indexedGeometryArray.getNormalIndices(i, paramIndexArrays.normalIndices);
/*      */       } 
/*      */     }
/* 1225 */     if (this.vertexColor3 || this.vertexColor4) {
/* 1226 */       if (bool) {
/* 1227 */         paramIndexArrays.colorIndices = paramIndexArrays.positionIndices;
/*      */       } else {
/*      */         
/* 1230 */         paramIndexArrays.colorIndices = new int[j];
/* 1231 */         indexedGeometryArray.getColorIndices(i, paramIndexArrays.colorIndices);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class VertexIndices
/*      */   {
/*      */     int pi;
/*      */     
/*      */     int ni;
/*      */     
/*      */     int ci;
/*      */     
/*      */     private VertexIndices() {}
/*      */   }
/*      */   
/*      */   private void getVertexIndices(int paramInt, IndexArrays paramIndexArrays, VertexIndices paramVertexIndices) {
/* 1249 */     paramVertexIndices.pi = paramIndexArrays.positionIndices[paramInt];
/* 1250 */     if (this.vertexNormals)
/* 1251 */       paramVertexIndices.ni = paramIndexArrays.normalIndices[paramInt]; 
/* 1252 */     if (this.vertexColors) {
/* 1253 */       paramVertexIndices.ci = paramIndexArrays.colorIndices[paramInt];
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class IndexedByCopyGeometry
/*      */     extends ByCopyGeometry
/*      */   {
/* 1261 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1262 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedByCopyGeometry(GeometryArray param1GeometryArray) {
/* 1265 */       super(CompressionStream.this, param1GeometryArray, 0, param1GeometryArray.getVertexCount());
/* 1266 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1270 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1271 */       int i = CompressionStream.this.meshBuffer.getMeshReference(this.vi.pi);
/*      */       
/* 1273 */       CompressionStream.this.meshBuffer; if (i == -1 || (CompressionStream.this.vertexNormals && this.vi.ni != CompressionStream.this.meshBuffer.getNormalIndex(i))) {
/*      */ 
/*      */ 
/*      */         
/* 1277 */         Point3f point3f = this.positions[this.vi.pi];
/* 1278 */         Vector3f vector3f = CompressionStream.this.vertexNormals ? this.normals[this.vi.ni] : null;
/* 1279 */         Color3f color3f = CompressionStream.this.vertexColor3 ? this.colors3[this.vi.ci] : (CompressionStream.this.vertexColor4 ? this.colors4[this.vi.ci] : null);
/*      */ 
/*      */         
/* 1282 */         CompressionStream.this.addVertex(point3f, vector3f, color3f, param1Int2, 1);
/* 1283 */         CompressionStream.this.meshBuffer.push(this.vi.pi, this.vi.ci, this.vi.ni);
/*      */       } else {
/*      */         
/* 1286 */         if (CompressionStream.this.vertexNormals);
/*      */ 
/*      */ 
/*      */         
/* 1290 */         if (CompressionStream.this.vertexColor3 && this.vi.ci != CompressionStream.this.meshBuffer.getColorIndex(i)) {
/* 1291 */           CompressionStream.this.addColor(this.colors3[this.vi.ci]);
/*      */         }
/* 1293 */         else if (CompressionStream.this.vertexColor4 && this.vi.ci != CompressionStream.this.meshBuffer.getColorIndex(i)) {
/* 1294 */           CompressionStream.this.addColor(this.colors4[this.vi.ci]);
/*      */         } 
/* 1296 */         CompressionStream.this.addMeshReference(param1Int2, i);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class VertexCopy
/*      */   {
/*      */     private VertexCopy() {}
/*      */     
/* 1306 */     Object c = null;
/* 1307 */     Point3f p = null;
/* 1308 */     Vector3f n = null;
/* 1309 */     Color3f c3 = null;
/* 1310 */     Color4f c4 = null;
/*      */   }
/*      */   
/*      */   private void processVertexCopy(VertexCopy paramVertexCopy, int paramInt) {
/* 1314 */     int i = this.meshBuffer.getMeshReference(paramVertexCopy.p);
/*      */     
/* 1316 */     this.meshBuffer; if (i == -1 || (this.vertexNormals && !paramVertexCopy.n.equals(this.meshBuffer.getNormal(i)))) {
/*      */ 
/*      */ 
/*      */       
/* 1320 */       addVertex(paramVertexCopy.p, paramVertexCopy.n, paramVertexCopy.c, paramInt, 1);
/* 1321 */       this.meshBuffer.push(paramVertexCopy.p, paramVertexCopy.c, paramVertexCopy.n);
/*      */     } else {
/*      */       
/* 1324 */       if (this.vertexNormals);
/*      */ 
/*      */ 
/*      */       
/* 1328 */       if (this.vertexColor3 && !paramVertexCopy.c3.equals(this.meshBuffer.getColor3(i))) {
/* 1329 */         addColor(paramVertexCopy.c3);
/*      */       }
/* 1331 */       else if (this.vertexColor4 && !paramVertexCopy.c4.equals(this.meshBuffer.getColor4(i))) {
/* 1332 */         addColor(paramVertexCopy.c4);
/*      */       } 
/* 1334 */       addMeshReference(paramInt, i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processIndexedVertexCopy(VertexCopy paramVertexCopy, VertexIndices paramVertexIndices, int paramInt) {
/* 1342 */     int i = this.meshBuffer.getMeshReference(paramVertexIndices.pi);
/*      */     
/* 1344 */     this.meshBuffer; if (i == -1 || (this.vertexNormals && paramVertexIndices.ni != this.meshBuffer.getNormalIndex(i))) {
/*      */ 
/*      */ 
/*      */       
/* 1348 */       addVertex(paramVertexCopy.p, paramVertexCopy.n, paramVertexCopy.c, paramInt, 1);
/* 1349 */       this.meshBuffer.push(paramVertexIndices.pi, paramVertexIndices.ci, paramVertexIndices.ni);
/*      */     } else {
/*      */       
/* 1352 */       if (this.vertexNormals);
/*      */ 
/*      */ 
/*      */       
/* 1356 */       if (this.vertexColor3 && paramVertexIndices.ci != this.meshBuffer.getColorIndex(i)) {
/* 1357 */         addColor(paramVertexCopy.c3);
/*      */       }
/* 1359 */       else if (this.vertexColor4 && paramVertexIndices.ci != this.meshBuffer.getColorIndex(i)) {
/* 1360 */         addColor(paramVertexCopy.c4);
/*      */       } 
/* 1362 */       addMeshReference(paramInt, i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private abstract class InterleavedGeometry
/*      */     implements GeometryAccessor
/*      */   {
/* 1372 */     CompressionStream.VertexCopy vc = new CompressionStream.VertexCopy(null);
/*      */     
/* 1374 */     int vstride = 0;
/* 1375 */     int coffset = 0;
/* 1376 */     int noffset = 0;
/* 1377 */     int poffset = 0;
/* 1378 */     int tstride = 0;
/* 1379 */     int tcount = 0;
/*      */     
/*      */     InterleavedGeometry(GeometryArray param1GeometryArray) {
/* 1382 */       if (CompressionStream.this.vertexTextures) {
/* 1383 */         if (CompressionStream.this.vertexTexture2) { this.tstride = 2; }
/* 1384 */         else if (CompressionStream.this.vertexTexture3) { this.tstride = 3; }
/* 1385 */         else if (CompressionStream.this.vertexTexture4) { this.tstride = 4; }
/*      */         
/* 1387 */         this.tcount = param1GeometryArray.getTexCoordSetCount();
/* 1388 */         this.vstride += this.tcount * this.tstride;
/*      */       } 
/*      */       
/* 1391 */       if (CompressionStream.this.vertexColors) {
/* 1392 */         this.coffset = this.vstride;
/* 1393 */         if (CompressionStream.this.vertexColor3) { this.vstride += 3; }
/* 1394 */         else { this.vstride += 4; }
/*      */       
/*      */       } 
/* 1397 */       if (CompressionStream.this.vertexNormals) {
/* 1398 */         this.noffset = this.vstride;
/* 1399 */         this.vstride += 3;
/*      */       } 
/*      */       
/* 1402 */       this.poffset = this.vstride;
/* 1403 */       this.vstride += 3;
/*      */     }
/*      */     
/*      */     abstract void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy);
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1409 */       copyVertex(param1Int1, param1Int1, param1Int1, this.vc);
/* 1410 */       CompressionStream.this.processVertexCopy(this.vc, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class InterleavedGeometryFloat
/*      */     extends InterleavedGeometry
/*      */   {
/* 1419 */     float[] vdata = null;
/*      */     
/*      */     InterleavedGeometryFloat(GeometryArray param1GeometryArray) {
/* 1422 */       super(CompressionStream.this, param1GeometryArray);
/* 1423 */       this.vdata = param1GeometryArray.getInterleavedVertices();
/*      */     }
/*      */ 
/*      */     
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1428 */       int i = param1Int1 * this.vstride;
/* 1429 */       param1VertexCopy.p = new Point3f(this.vdata[i + this.poffset + 0], this.vdata[i + this.poffset + 1], this.vdata[i + this.poffset + 2]);
/*      */ 
/*      */ 
/*      */       
/* 1433 */       if (CompressionStream.this.vertexNormals) {
/* 1434 */         i = param1Int2 * this.vstride;
/* 1435 */         param1VertexCopy.n = new Vector3f(this.vdata[i + this.noffset + 0], this.vdata[i + this.noffset + 1], this.vdata[i + this.noffset + 2]);
/*      */       } 
/*      */ 
/*      */       
/* 1439 */       if (CompressionStream.this.vertexColor3) {
/* 1440 */         i = param1Int3 * this.vstride;
/* 1441 */         param1VertexCopy.c3 = new Color3f(this.vdata[i + this.coffset + 0], this.vdata[i + this.coffset + 1], this.vdata[i + this.coffset + 2]);
/*      */ 
/*      */         
/* 1444 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1446 */       else if (CompressionStream.this.vertexColor4) {
/* 1447 */         i = param1Int3 * this.vstride;
/* 1448 */         param1VertexCopy.c4 = new Color4f(this.vdata[i + this.coffset + 0], this.vdata[i + this.coffset + 1], this.vdata[i + this.coffset + 2], this.vdata[i + this.coffset + 3]);
/*      */ 
/*      */ 
/*      */         
/* 1452 */         param1VertexCopy.c = param1VertexCopy.c4;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class IndexedInterleavedGeometryFloat
/*      */     extends InterleavedGeometryFloat
/*      */   {
/* 1464 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1465 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedInterleavedGeometryFloat(GeometryArray param1GeometryArray) {
/* 1468 */       super(CompressionStream.this, param1GeometryArray);
/* 1469 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1473 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1474 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1475 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class InterleavedGeometryNIO
/*      */     extends InterleavedGeometry
/*      */   {
/* 1484 */     FloatBufferWrapper fbw = null;
/*      */     
/*      */     InterleavedGeometryNIO(GeometryArray param1GeometryArray) {
/* 1487 */       super(CompressionStream.this, param1GeometryArray);
/* 1488 */       J3DBuffer j3DBuffer = param1GeometryArray.getInterleavedVertexBuffer();
/* 1489 */       if (BufferWrapper.getBufferType(j3DBuffer) == 3) {
/*      */         
/* 1491 */         this.fbw = new FloatBufferWrapper(j3DBuffer);
/*      */       } else {
/*      */         
/* 1494 */         throw new IllegalArgumentException("\ninterleaved vertex buffer must be FloatBuffer");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1501 */       int i = param1Int1 * this.vstride;
/* 1502 */       param1VertexCopy.p = new Point3f(this.fbw.get(i + this.poffset + 0), this.fbw.get(i + this.poffset + 1), this.fbw.get(i + this.poffset + 2));
/*      */ 
/*      */ 
/*      */       
/* 1506 */       if (CompressionStream.this.vertexNormals) {
/* 1507 */         i = param1Int2 * this.vstride;
/* 1508 */         param1VertexCopy.n = new Vector3f(this.fbw.get(i + this.noffset + 0), this.fbw.get(i + this.noffset + 1), this.fbw.get(i + this.noffset + 2));
/*      */       } 
/*      */ 
/*      */       
/* 1512 */       if (CompressionStream.this.vertexColor3) {
/* 1513 */         i = param1Int3 * this.vstride;
/* 1514 */         param1VertexCopy.c3 = new Color3f(this.fbw.get(i + this.coffset + 0), this.fbw.get(i + this.coffset + 1), this.fbw.get(i + this.coffset + 2));
/*      */ 
/*      */         
/* 1517 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1519 */       else if (CompressionStream.this.vertexColor4) {
/* 1520 */         i = param1Int3 * this.vstride;
/* 1521 */         param1VertexCopy.c4 = new Color4f(this.fbw.get(i + this.coffset + 0), this.fbw.get(i + this.coffset + 1), this.fbw.get(i + this.coffset + 2), this.fbw.get(i + this.coffset + 3));
/*      */ 
/*      */ 
/*      */         
/* 1525 */         param1VertexCopy.c = param1VertexCopy.c4;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class IndexedInterleavedGeometryNIO
/*      */     extends InterleavedGeometryNIO
/*      */   {
/* 1535 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1536 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedInterleavedGeometryNIO(GeometryArray param1GeometryArray) {
/* 1539 */       super(CompressionStream.this, param1GeometryArray);
/* 1540 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1544 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1545 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1546 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
/*      */     } }
/*      */   
/*      */   private class ByRefGeometry implements GeometryAccessor { CompressionStream.VertexCopy vc;
/*      */     byte[] colorsB;
/*      */     float[] colorsF;
/*      */     float[] normals;
/*      */     
/*      */     ByRefGeometry(GeometryArray param1GeometryArray) {
/* 1555 */       this.vc = new CompressionStream.VertexCopy(null);
/*      */       
/* 1557 */       this.colorsB = null;
/* 1558 */       this.colorsF = null;
/* 1559 */       this.normals = null;
/* 1560 */       this.positionsF = null;
/* 1561 */       this.positionsD = null;
/*      */       
/* 1563 */       this.initialPositionIndex = 0;
/* 1564 */       this.initialNormalIndex = 0;
/* 1565 */       this.initialColorIndex = 0;
/*      */ 
/*      */       
/* 1568 */       this.positionsF = param1GeometryArray.getCoordRefFloat();
/*      */ 
/*      */ 
/*      */       
/* 1572 */       this.positionsD = param1GeometryArray.getCoordRefDouble();
/*      */ 
/*      */ 
/*      */       
/* 1576 */       if (this.positionsF == null && this.positionsD == null) {
/* 1577 */         throw new UnsupportedOperationException("\nby-reference access to Point3{d,f} arrays");
/*      */       }
/*      */       
/* 1580 */       this.initialPositionIndex = param1GeometryArray.getInitialCoordIndex();
/*      */       
/* 1582 */       if (CompressionStream.this.vertexColors) {
/* 1583 */         this.colorsB = param1GeometryArray.getColorRefByte();
/*      */ 
/*      */ 
/*      */         
/* 1587 */         this.colorsF = param1GeometryArray.getColorRefFloat();
/*      */ 
/*      */ 
/*      */         
/* 1591 */         if (this.colorsB == null && this.colorsF == null) {
/* 1592 */           throw new UnsupportedOperationException("\nby-reference access to Color{3b,3f,4b,4f} arrays");
/*      */         }
/*      */         
/* 1595 */         this.initialColorIndex = param1GeometryArray.getInitialColorIndex();
/*      */       } 
/*      */       
/* 1598 */       if (CompressionStream.this.vertexNormals) {
/* 1599 */         this.normals = param1GeometryArray.getNormalRefFloat();
/*      */ 
/*      */ 
/*      */         
/* 1603 */         if (this.normals == null) {
/* 1604 */           throw new UnsupportedOperationException("\nby-reference access to Normal3f array");
/*      */         }
/*      */         
/* 1607 */         this.initialNormalIndex = param1GeometryArray.getInitialNormalIndex();
/*      */       } 
/*      */     }
/*      */     float[] positionsF; double[] positionsD; int initialPositionIndex; int initialNormalIndex; int initialColorIndex;
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1612 */       param1Int1 *= 3;
/* 1613 */       if (this.positionsF != null) {
/* 1614 */         param1VertexCopy.p = new Point3f(this.positionsF[param1Int1 + 0], this.positionsF[param1Int1 + 1], this.positionsF[param1Int1 + 2]);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1619 */         param1VertexCopy.p = new Point3f((float)this.positionsD[param1Int1 + 0], (float)this.positionsD[param1Int1 + 1], (float)this.positionsD[param1Int1 + 2]);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1624 */       param1Int2 *= 3;
/* 1625 */       if (CompressionStream.this.vertexNormals) {
/* 1626 */         param1VertexCopy.n = new Vector3f(this.normals[param1Int2 + 0], this.normals[param1Int2 + 1], this.normals[param1Int2 + 2]);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1631 */       if (CompressionStream.this.vertexColor3) {
/* 1632 */         param1Int3 *= 3;
/* 1633 */         if (this.colorsB != null) {
/* 1634 */           param1VertexCopy.c3 = new Color3f((this.colorsB[param1Int3 + 0] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 1] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 2] & 0xFF) * 0.003921569F);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1640 */           param1VertexCopy.c3 = new Color3f(this.colorsF[param1Int3 + 0], this.colorsF[param1Int3 + 1], this.colorsF[param1Int3 + 2]);
/*      */         } 
/*      */ 
/*      */         
/* 1644 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1646 */       else if (CompressionStream.this.vertexColor4) {
/* 1647 */         param1Int3 *= 4;
/* 1648 */         if (this.colorsB != null) {
/* 1649 */           param1VertexCopy.c4 = new Color4f((this.colorsB[param1Int3 + 0] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 1] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 2] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 3] & 0xFF) * 0.003921569F);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1656 */           param1VertexCopy.c4 = new Color4f(this.colorsF[param1Int3 + 0], this.colorsF[param1Int3 + 1], this.colorsF[param1Int3 + 2], this.colorsF[param1Int3 + 3]);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1661 */         param1VertexCopy.c = param1VertexCopy.c4;
/*      */       } 
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1666 */       copyVertex(param1Int1 + this.initialPositionIndex, param1Int1 + this.initialNormalIndex, param1Int1 + this.initialColorIndex, this.vc);
/*      */ 
/*      */ 
/*      */       
/* 1670 */       CompressionStream.this.processVertexCopy(this.vc, param1Int2);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class IndexedByRefGeometry
/*      */     extends ByRefGeometry
/*      */   {
/* 1679 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1680 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedByRefGeometry(GeometryArray param1GeometryArray) {
/* 1683 */       super(CompressionStream.this, param1GeometryArray);
/* 1684 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1688 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1689 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1690 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class ByRefGeometryNIO
/*      */     implements GeometryAccessor
/*      */   {
/* 1699 */     CompressionStream.VertexCopy vc = new CompressionStream.VertexCopy(null);
/*      */     
/* 1701 */     ByteBufferWrapper colorsB = null;
/* 1702 */     FloatBufferWrapper colorsF = null;
/* 1703 */     FloatBufferWrapper normals = null;
/* 1704 */     FloatBufferWrapper positionsF = null;
/* 1705 */     DoubleBufferWrapper positionsD = null;
/*      */     
/* 1707 */     int initialPositionIndex = 0;
/* 1708 */     int initialNormalIndex = 0;
/* 1709 */     int initialColorIndex = 0;
/*      */ 
/*      */     
/*      */     ByRefGeometryNIO(GeometryArray param1GeometryArray) {
/* 1713 */       J3DBuffer j3DBuffer = param1GeometryArray.getCoordRefBuffer();
/* 1714 */       this.initialPositionIndex = param1GeometryArray.getInitialCoordIndex();
/*      */       
/* 1716 */       switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*      */         case 3:
/* 1718 */           this.positionsF = new FloatBufferWrapper(j3DBuffer);
/*      */           break;
/*      */         
/*      */         case 4:
/* 1722 */           this.positionsD = new DoubleBufferWrapper(j3DBuffer);
/*      */           break;
/*      */         
/*      */         default:
/* 1726 */           throw new IllegalArgumentException("\nposition buffer must be FloatBuffer or DoubleBuffer");
/*      */       } 
/*      */ 
/*      */       
/* 1730 */       if (CompressionStream.this.vertexColors) {
/* 1731 */         j3DBuffer = param1GeometryArray.getColorRefBuffer();
/* 1732 */         this.initialColorIndex = param1GeometryArray.getInitialColorIndex();
/*      */         
/* 1734 */         switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*      */           case 2:
/* 1736 */             this.colorsB = new ByteBufferWrapper(j3DBuffer);
/*      */             break;
/*      */           
/*      */           case 3:
/* 1740 */             this.colorsF = new FloatBufferWrapper(j3DBuffer);
/*      */             break;
/*      */           
/*      */           default:
/* 1744 */             throw new IllegalArgumentException("\ncolor buffer must be ByteBuffer or FloatBuffer");
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 1749 */       if (CompressionStream.this.vertexNormals) {
/* 1750 */         j3DBuffer = param1GeometryArray.getNormalRefBuffer();
/* 1751 */         this.initialNormalIndex = param1GeometryArray.getInitialNormalIndex();
/*      */         
/* 1753 */         switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*      */           case 3:
/* 1755 */             this.normals = new FloatBufferWrapper(j3DBuffer);
/*      */             return;
/*      */         } 
/*      */         
/* 1759 */         throw new IllegalArgumentException("\nnormal buffer must be FloatBuffer");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1766 */       param1Int1 *= 3;
/* 1767 */       if (this.positionsF != null) {
/* 1768 */         param1VertexCopy.p = new Point3f(this.positionsF.get(param1Int1 + 0), this.positionsF.get(param1Int1 + 1), this.positionsF.get(param1Int1 + 2));
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1773 */         param1VertexCopy.p = new Point3f((float)this.positionsD.get(param1Int1 + 0), (float)this.positionsD.get(param1Int1 + 1), (float)this.positionsD.get(param1Int1 + 2));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1778 */       param1Int2 *= 3;
/* 1779 */       if (CompressionStream.this.vertexNormals) {
/* 1780 */         param1VertexCopy.n = new Vector3f(this.normals.get(param1Int2 + 0), this.normals.get(param1Int2 + 1), this.normals.get(param1Int2 + 2));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1785 */       if (CompressionStream.this.vertexColor3) {
/* 1786 */         param1Int3 *= 3;
/* 1787 */         if (this.colorsB != null) {
/* 1788 */           param1VertexCopy.c3 = new Color3f((this.colorsB.get(param1Int3 + 0) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 1) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 2) & 0xFF) * 0.003921569F);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1794 */           param1VertexCopy.c3 = new Color3f(this.colorsF.get(param1Int3 + 0), this.colorsF.get(param1Int3 + 1), this.colorsF.get(param1Int3 + 2));
/*      */         } 
/*      */ 
/*      */         
/* 1798 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1800 */       else if (CompressionStream.this.vertexColor4) {
/* 1801 */         param1Int3 *= 4;
/* 1802 */         if (this.colorsB != null) {
/* 1803 */           param1VertexCopy.c4 = new Color4f((this.colorsB.get(param1Int3 + 0) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 1) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 2) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 3) & 0xFF) * 0.003921569F);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1810 */           param1VertexCopy.c4 = new Color4f(this.colorsF.get(param1Int3 + 0), this.colorsF.get(param1Int3 + 1), this.colorsF.get(param1Int3 + 2), this.colorsF.get(param1Int3 + 3));
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1815 */         param1VertexCopy.c = param1VertexCopy.c4;
/*      */       } 
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1820 */       copyVertex(param1Int1 + this.initialPositionIndex, param1Int1 + this.initialNormalIndex, param1Int1 + this.initialColorIndex, this.vc);
/*      */ 
/*      */ 
/*      */       
/* 1824 */       CompressionStream.this.processVertexCopy(this.vc, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class IndexedByRefGeometryNIO
/*      */     extends ByRefGeometryNIO
/*      */   {
/* 1833 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1834 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedByRefGeometryNIO(GeometryArray param1GeometryArray) {
/* 1837 */       super(CompressionStream.this, param1GeometryArray);
/* 1838 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1842 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1843 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1844 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
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
/*      */   void addGeometryArray(GeometryArray paramGeometryArray) {
/* 1857 */     int i = 0;
/* 1858 */     int j = 0;
/* 1859 */     int k = paramGeometryArray.getVertexFormat();
/* 1860 */     ByRefGeometry byRefGeometry = null;
/*      */     
/* 1862 */     if (this.streamType != getStreamType(paramGeometryArray)) {
/* 1863 */       throw new IllegalArgumentException("GeometryArray has inconsistent dimensionality");
/*      */     }
/*      */     
/* 1866 */     if (this.vertexComponents != getVertexComponents(k)) {
/* 1867 */       throw new IllegalArgumentException("GeometryArray has inconsistent vertex components");
/*      */     }
/*      */ 
/*      */     
/* 1871 */     boolean bool1 = ((k & 0x800) != 0) ? 1 : 0;
/* 1872 */     boolean bool2 = ((k & 0x80) != 0) ? 1 : 0;
/* 1873 */     boolean bool3 = ((k & 0x100) != 0) ? 1 : 0;
/* 1874 */     boolean bool = paramGeometryArray instanceof IndexedGeometryArray;
/*      */     
/* 1876 */     if (bool) {
/*      */ 
/*      */ 
/*      */       
/* 1880 */       i = 0;
/* 1881 */       j = ((IndexedGeometryArray)paramGeometryArray).getValidIndexCount();
/*      */     } 
/*      */     
/* 1884 */     if (!bool2) {
/*      */       
/* 1886 */       if (bool) {
/* 1887 */         byRefGeometry = new IndexedByCopyGeometry(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1890 */         i = 0;
/* 1891 */         j = paramGeometryArray.getValidVertexCount();
/* 1892 */         ByCopyGeometry byCopyGeometry = new ByCopyGeometry(paramGeometryArray);
/*      */       }
/*      */     
/* 1895 */     } else if (bool3 && bool1) {
/*      */       
/* 1897 */       if (bool) {
/* 1898 */         IndexedInterleavedGeometryNIO indexedInterleavedGeometryNIO = new IndexedInterleavedGeometryNIO(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1901 */         i = paramGeometryArray.getInitialVertexIndex();
/* 1902 */         j = paramGeometryArray.getValidVertexCount();
/* 1903 */         InterleavedGeometryNIO interleavedGeometryNIO = new InterleavedGeometryNIO(paramGeometryArray);
/*      */       }
/*      */     
/* 1906 */     } else if (bool3 && !bool1) {
/*      */       
/* 1908 */       if (bool) {
/* 1909 */         IndexedInterleavedGeometryFloat indexedInterleavedGeometryFloat = new IndexedInterleavedGeometryFloat(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1912 */         i = paramGeometryArray.getInitialVertexIndex();
/* 1913 */         j = paramGeometryArray.getValidVertexCount();
/* 1914 */         InterleavedGeometryFloat interleavedGeometryFloat = new InterleavedGeometryFloat(paramGeometryArray);
/*      */       }
/*      */     
/* 1917 */     } else if (!bool3 && bool1) {
/*      */       
/* 1919 */       if (bool) {
/* 1920 */         IndexedByRefGeometryNIO indexedByRefGeometryNIO = new IndexedByRefGeometryNIO(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1923 */         i = 0;
/* 1924 */         j = paramGeometryArray.getValidVertexCount();
/* 1925 */         ByRefGeometryNIO byRefGeometryNIO = new ByRefGeometryNIO(paramGeometryArray);
/*      */       }
/*      */     
/* 1928 */     } else if (!bool3 && !bool1) {
/*      */       
/* 1930 */       if (bool) {
/* 1931 */         IndexedByRefGeometry indexedByRefGeometry = new IndexedByRefGeometry(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1934 */         i = 0;
/* 1935 */         j = paramGeometryArray.getValidVertexCount();
/* 1936 */         byRefGeometry = new ByRefGeometry(paramGeometryArray);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1941 */     int m = 0;
/* 1942 */     int[] arrayOfInt = null;
/* 1943 */     byte b1 = 0;
/* 1944 */     byte b2 = 1;
/* 1945 */     boolean bool4 = false;
/* 1946 */     boolean bool5 = false;
/*      */     
/* 1948 */     if (paramGeometryArray instanceof javax.media.j3d.TriangleStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleStripArray || paramGeometryArray instanceof javax.media.j3d.LineStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedLineStripArray) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1953 */       bool4 = true;
/* 1954 */       b2 = 3;
/*      */     
/*      */     }
/* 1957 */     else if (paramGeometryArray instanceof javax.media.j3d.TriangleFanArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleFanArray) {
/*      */ 
/*      */       
/* 1960 */       bool4 = true;
/* 1961 */       b2 = 2;
/*      */     
/*      */     }
/* 1964 */     else if (paramGeometryArray instanceof javax.media.j3d.QuadArray || paramGeometryArray instanceof javax.media.j3d.IndexedQuadArray) {
/*      */ 
/*      */ 
/*      */       
/* 1968 */       bool5 = true;
/* 1969 */       b1 = 4;
/* 1970 */       b2 = 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1975 */     if (bool4) {
/* 1976 */       if (bool) {
/*      */         
/* 1978 */         IndexedGeometryStripArray indexedGeometryStripArray = (IndexedGeometryStripArray)paramGeometryArray;
/*      */         
/* 1980 */         m = indexedGeometryStripArray.getNumStrips();
/* 1981 */         arrayOfInt = new int[m];
/* 1982 */         indexedGeometryStripArray.getStripIndexCounts(arrayOfInt);
/*      */       }
/*      */       else {
/*      */         
/* 1986 */         GeometryStripArray geometryStripArray = (GeometryStripArray)paramGeometryArray;
/*      */         
/* 1988 */         m = geometryStripArray.getNumStrips();
/* 1989 */         arrayOfInt = new int[m];
/* 1990 */         geometryStripArray.getStripVertexCounts(arrayOfInt);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1995 */     int n = i;
/* 1996 */     if (bool4) {
/* 1997 */       for (byte b = 0; b < m; b++) {
/* 1998 */         byRefGeometry.processVertex(n++, 1);
/* 1999 */         for (byte b3 = 1; b3 < arrayOfInt[b]; b3++) {
/* 2000 */           byRefGeometry.processVertex(n++, b2);
/*      */         }
/*      */       }
/*      */     
/* 2004 */     } else if (bool5) {
/* 2005 */       while (n < i + j) {
/* 2006 */         byRefGeometry.processVertex(n++, 1);
/* 2007 */         for (byte b = 1; b < b1; b++) {
/* 2008 */           byRefGeometry.processVertex(n++, b2);
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 2013 */       while (n < i + j) {
/* 2014 */         byRefGeometry.processVertex(n++, 1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void print() {
/* 2023 */     System.out.println("\nstream has " + this.stream.size() + " entries");
/* 2024 */     System.out.println("uncompressed size " + this.byteCount + " bytes");
/* 2025 */     System.out.println("upper position bound: " + this.mcBounds[1].toString());
/* 2026 */     System.out.println("lower position bound: " + this.mcBounds[0].toString());
/* 2027 */     System.out.println("X, Y, Z centers (" + (float)this.center[0] + " " + (float)this.center[1] + " " + (float)this.center[2] + ")\n" + "scale " + (float)this.scale + "\n");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2033 */     Iterator iterator = this.stream.iterator();
/* 2034 */     while (iterator.hasNext()) {
/* 2035 */       System.out.println(iterator.next().toString() + "\n");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompressionStream(int paramInt1, int paramInt2, int paramInt3, Shape3D[] paramArrayOfShape3D) {
/* 2090 */     this();
/*      */ 
/*      */     
/* 2093 */     if (paramArrayOfShape3D == null) {
/* 2094 */       throw new IllegalArgumentException("null Shape3D array");
/*      */     }
/* 2096 */     if (paramArrayOfShape3D.length == 0) {
/* 2097 */       throw new IllegalArgumentException("zero-length Shape3D array");
/*      */     }
/* 2099 */     if (paramArrayOfShape3D[false] == null) {
/* 2100 */       throw new IllegalArgumentException("Shape3D at index 0 is null");
/*      */     }
/* 2102 */     long l = 0L;
/*      */ 
/*      */     
/* 2105 */     Geometry geometry = paramArrayOfShape3D[0].getGeometry();
/* 2106 */     if (!(geometry instanceof GeometryArray)) {
/* 2107 */       throw new IllegalArgumentException("Shape3D at index 0 is not a GeometryArray");
/*      */     }
/*      */     
/* 2110 */     GeometryArray geometryArray = (GeometryArray)geometry;
/* 2111 */     this.streamType = getStreamType(geometryArray);
/* 2112 */     this.vertexComponents = getVertexComponents(geometryArray.getVertexFormat());
/*      */ 
/*      */     
/* 2115 */     addPositionQuantization(paramInt1);
/* 2116 */     addColorQuantization(paramInt2);
/* 2117 */     addNormalQuantization(paramInt3);
/*      */ 
/*      */     
/* 2120 */     for (byte b = 0; b < paramArrayOfShape3D.length; b++) {
/*      */ 
/*      */       
/* 2123 */       geometry = paramArrayOfShape3D[b].getGeometry();
/* 2124 */       if (!(geometry instanceof GeometryArray)) {
/* 2125 */         throw new IllegalArgumentException("Shape3D at index " + b + " is not a GeometryArray");
/*      */       }
/*      */ 
/*      */       
/* 2129 */       Appearance appearance = paramArrayOfShape3D[b].getAppearance();
/* 2130 */       if (appearance != null) {
/* 2131 */         Material material = appearance.getMaterial();
/* 2132 */         if (material != null) {
/* 2133 */           material.getDiffuseColor(this.c3f);
/* 2134 */           if (this.vertexColor4) {
/* 2135 */             this.c4f.set(this.c3f.x, this.c3f.y, this.c3f.z, 1.0F);
/* 2136 */             addColor(this.c4f);
/*      */           } else {
/* 2138 */             addColor(this.c3f);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2143 */       addGeometryArray((GeometryArray)geometry);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2190 */   public CompressionStream(Shape3D[] paramArrayOfShape3D) { this(16, 9, 6, paramArrayOfShape3D); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompressionStream(int paramInt1, int paramInt2, int paramInt3, GeometryInfo[] paramArrayOfGeometryInfo) {
/* 2226 */     this();
/*      */ 
/*      */     
/* 2229 */     if (paramArrayOfGeometryInfo == null) {
/* 2230 */       throw new IllegalArgumentException("null GeometryInfo array");
/*      */     }
/* 2232 */     if (paramArrayOfGeometryInfo.length == 0) {
/* 2233 */       throw new IllegalArgumentException("zero-length GeometryInfo array");
/*      */     }
/*      */     
/* 2236 */     if (paramArrayOfGeometryInfo[false] == null) {
/* 2237 */       throw new IllegalArgumentException("GeometryInfo at index 0 is null");
/*      */     }
/*      */     
/* 2240 */     long l = 0L;
/*      */ 
/*      */     
/* 2243 */     GeometryArray geometryArray = paramArrayOfGeometryInfo[0].getGeometryArray();
/* 2244 */     this.streamType = getStreamType(geometryArray);
/* 2245 */     this.vertexComponents = getVertexComponents(geometryArray.getVertexFormat());
/*      */ 
/*      */     
/* 2248 */     addPositionQuantization(paramInt1);
/* 2249 */     addColorQuantization(paramInt2);
/* 2250 */     addNormalQuantization(paramInt3);
/*      */ 
/*      */     
/* 2253 */     for (byte b = 0; b < paramArrayOfGeometryInfo.length; b++)
/*      */     {
/* 2255 */       addGeometryArray(paramArrayOfGeometryInfo[b].getGeometryArray());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2292 */   public CompressionStream(GeometryInfo[] paramArrayOfGeometryInfo) { this(16, 9, 6, paramArrayOfGeometryInfo); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point3d[] getModelBounds() {
/* 2305 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 2306 */     arrayOfPoint3d[0] = new Point3d(this.mcBounds[0]);
/* 2307 */     arrayOfPoint3d[1] = new Point3d(this.mcBounds[1]);
/* 2308 */     return arrayOfPoint3d;
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
/*      */   public Point3d[] getNormalizedBounds() {
/* 2320 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 2321 */     arrayOfPoint3d[0] = new Point3d(this.ncBounds[0]);
/* 2322 */     arrayOfPoint3d[1] = new Point3d(this.ncBounds[1]);
/* 2323 */     return arrayOfPoint3d;
/*      */   }
/*      */   
/*      */   private static interface GeometryAccessor {
/*      */     void processVertex(int param1Int1, int param1Int2);
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\compression\CompressionStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */