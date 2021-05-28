/*      */ package com.sun.j3d.utils.geometry.compression;
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
/*  228 */     this.mcBounds = new Point3d[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  233 */     this.ncBounds = new Point3d[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  238 */     this.qcBounds = new Point3i[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  243 */     this.center = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  306 */     this.lastPosition = new int[3];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  311 */     this.lastColor = new int[4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  375 */     this.meshBuffer = new MeshBuffer();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  384 */     this.lastElementColor = false;
/*  385 */     this.lastLastElementColor = false;
/*  386 */     this.lastElementNormal = false;
/*  387 */     this.lastLastElementNormal = false;
/*      */ 
/*      */     
/*  390 */     this.p3f = new Point3f();
/*  391 */     this.c3f = new Color3f();
/*  392 */     this.c4f = new Color4f();
/*  393 */     this.n3f = new Vector3f();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  398 */     this.stream = new LinkedList();
/*      */     
/*  400 */     this.byteCount = 0;
/*  401 */     this.vertexCount = 0;
/*  402 */     this.meshReferenceCount = 0;
/*      */     
/*  404 */     this.mcBounds[0] = new Point3d(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*      */ 
/*      */     
/*  407 */     this.mcBounds[1] = new Point3d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
/*      */ 
/*      */ 
/*      */     
/*  411 */     this.qcBounds[0] = new Point3i(2147483647, 2147483647, 2147483647);
/*      */ 
/*      */     
/*  414 */     this.qcBounds[1] = new Point3i(-2147483648, -2147483648, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  419 */     this.ncBounds[0] = new Point3d();
/*  420 */     this.ncBounds[1] = new Point3d();
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
/*      */   CompressionStream(int paramInt1, int paramInt2) {
/*  438 */     this();
/*  439 */     this.streamType = paramInt1;
/*  440 */     this.vertexComponents = getVertexComponents(paramInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getVertexComponents(int paramInt) {
/*  447 */     char c = Character.MIN_VALUE;
/*      */     
/*  449 */     this.vertexColors = this.vertexColor3 = this.vertexColor4 = this.vertexNormals = this.vertexTextures = this.vertexTexture2 = this.vertexTexture3 = this.vertexTexture4 = false;
/*      */ 
/*      */ 
/*      */     
/*  453 */     if ((paramInt & 0x2) != 0) {
/*  454 */       this.vertexNormals = true;
/*  455 */       c &= 0x2;
/*      */     } 
/*      */ 
/*      */     
/*  459 */     if ((paramInt & 0x4) != 0) {
/*  460 */       this.vertexColors = true;
/*      */       
/*  462 */       if ((paramInt & 0xC) != 0) {
/*  463 */         this.vertexColor4 = true;
/*  464 */         c &= 0xC;
/*      */       }
/*      */       else {
/*      */         
/*  468 */         this.vertexColor3 = true;
/*  469 */         c &= 0x4;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  474 */     if ((paramInt & 0x20) != 0) {
/*  475 */       this.vertexTextures = true;
/*  476 */       this.vertexTexture2 = true;
/*  477 */       c &= 0x20;
/*      */     
/*      */     }
/*  480 */     else if ((paramInt & 0x40) != 0) {
/*  481 */       this.vertexTextures = true;
/*  482 */       this.vertexTexture3 = true;
/*  483 */       c &= 0x40;
/*      */     
/*      */     }
/*  486 */     else if ((paramInt & 0x400) != 0) {
/*  487 */       this.vertexTextures = true;
/*  488 */       this.vertexTexture4 = true;
/*  489 */       c &= 0x400;
/*      */     } 
/*      */ 
/*      */     
/*  493 */     if (this.vertexTextures)
/*      */     {
/*  495 */       throw new UnsupportedOperationException("\ncompression of texture coordinates is not supported");
/*      */     }
/*      */     
/*  498 */     return c;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getStreamType(GeometryArray paramGeometryArray) {
/*  503 */     if (paramGeometryArray instanceof javax.media.j3d.TriangleStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleStripArray || paramGeometryArray instanceof javax.media.j3d.TriangleFanArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleFanArray || paramGeometryArray instanceof javax.media.j3d.TriangleArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleArray || paramGeometryArray instanceof javax.media.j3d.QuadArray || paramGeometryArray instanceof javax.media.j3d.IndexedQuadArray)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  512 */       return 2;
/*      */     }
/*  514 */     if (paramGeometryArray instanceof javax.media.j3d.LineArray || paramGeometryArray instanceof javax.media.j3d.IndexedLineArray || paramGeometryArray instanceof javax.media.j3d.LineStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedLineStripArray)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  519 */       return 1;
/*      */     }
/*      */     
/*  522 */     return 0;
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
/*  556 */     this.positionQuant = 16;
/*  557 */     this.colorQuant = 9;
/*  558 */     this.normalQuant = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  563 */     this.center[0] = ((this.mcBounds[1]).x + (this.mcBounds[0]).x) / 2.0D;
/*  564 */     this.center[1] = ((this.mcBounds[1]).y + (this.mcBounds[0]).y) / 2.0D;
/*  565 */     this.center[2] = ((this.mcBounds[1]).z + (this.mcBounds[0]).z) / 2.0D;
/*      */     
/*  567 */     double d1 = (this.mcBounds[1]).x - (this.mcBounds[0]).x;
/*  568 */     double d2 = (this.mcBounds[1]).y - (this.mcBounds[0]).y;
/*  569 */     double d3 = (this.mcBounds[1]).z - (this.mcBounds[0]).z;
/*      */     
/*  571 */     if (d1 > d2) {
/*  572 */       this.positionRangeMaximum = d1;
/*      */     } else {
/*  574 */       this.positionRangeMaximum = d2;
/*      */     } 
/*  576 */     if (d3 > this.positionRangeMaximum) {
/*  577 */       this.positionRangeMaximum = d3;
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
/*  593 */     this.scale = 2.0D / this.positionRangeMaximum * 0.999969482421875D;
/*      */ 
/*      */     
/*  596 */     this.positionQuantChanged = this.colorQuantChanged = this.normalQuantChanged = true;
/*      */ 
/*      */     
/*  599 */     this.firstPosition = this.firstColor = this.firstNormal = true;
/*      */ 
/*      */     
/*  602 */     Iterator iterator = this.stream.iterator();
/*  603 */     while (iterator.hasNext()) {
/*  604 */       Object object = iterator.next();
/*      */       
/*  606 */       if (object instanceof CompressionStreamElement) {
/*  607 */         ((CompressionStreamElement)object).quantize(this, paramHuffmanTable);
/*      */ 
/*      */ 
/*      */         
/*  611 */         this.lastLastElementColor = this.lastElementColor;
/*  612 */         this.lastLastElementNormal = this.lastElementNormal;
/*  613 */         this.lastElementColor = this.lastElementNormal = false;
/*      */         
/*  615 */         if (object instanceof CompressionStreamColor) {
/*  616 */           this.lastElementColor = true; continue;
/*  617 */         }  if (object instanceof CompressionStreamNormal) {
/*  618 */           this.lastElementNormal = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  623 */     (this.ncBounds[0]).x = (this.qcBounds[0]).x / 32768.0D;
/*  624 */     (this.ncBounds[0]).y = (this.qcBounds[0]).y / 32768.0D;
/*  625 */     (this.ncBounds[0]).z = (this.qcBounds[0]).z / 32768.0D;
/*      */     
/*  627 */     (this.ncBounds[1]).x = (this.qcBounds[1]).x / 32768.0D;
/*  628 */     (this.ncBounds[1]).y = (this.qcBounds[1]).y / 32768.0D;
/*  629 */     (this.ncBounds[1]).z = (this.qcBounds[1]).z / 32768.0D;
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
/*  656 */     boolean bool1 = this.vertexNormals ? 1 : 0;
/*  657 */     boolean bool2 = (this.vertexColor3 || this.vertexColor4) ? 1 : 0;
/*  658 */     boolean bool3 = this.vertexColor4 ? 1 : 0;
/*      */     
/*  660 */     byte b = 0x18 | bool1;
/*  661 */     long l = (bool2 << 2 | bool3 << true);
/*      */ 
/*      */     
/*  664 */     paramCommandStream.addCommand(b, 8, l, 3);
/*      */ 
/*      */     
/*  667 */     paramHuffmanTable.outputCommands(paramCommandStream);
/*      */ 
/*      */     
/*  670 */     Iterator iterator = this.stream.iterator();
/*  671 */     while (iterator.hasNext()) {
/*  672 */       Object object = iterator.next();
/*  673 */       if (object instanceof CompressionStreamElement) {
/*  674 */         ((CompressionStreamElement)object).outputCommand(paramHuffmanTable, paramCommandStream);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  679 */     paramCommandStream.end();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  688 */   int getByteCount() { return this.byteCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  697 */   int getVertexCount() { return this.vertexCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  705 */   int getMeshReferenceCount() { return this.meshReferenceCount; }
/*      */ 
/*      */ 
/*      */   
/*      */   private class PositionQuant
/*      */     extends CompressionStreamElement
/*      */   {
/*      */     int value;
/*      */ 
/*      */     
/*  715 */     PositionQuant(int param1Int) { this.value = param1Int; }
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  719 */       CompressionStream.this.positionQuant = this.value;
/*  720 */       CompressionStream.this.positionQuantChanged = true;
/*      */ 
/*      */       
/*  723 */       CompressionStream.this.scale = 2.0D / CompressionStream.this.positionRangeMaximum * ((1 << this.value - 1) - 1) / (1 << this.value - 1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  728 */     public String toString() { return "positionQuant: " + this.value; }
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
/*  739 */     NormalQuant(int param1Int) { this.value = param1Int; }
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  743 */       CompressionStream.this.normalQuant = this.value;
/*  744 */       CompressionStream.this.normalQuantChanged = true;
/*      */     }
/*      */ 
/*      */     
/*  748 */     public String toString() { return "normalQuant: " + this.value; }
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
/*  759 */     ColorQuant(int param1Int) { this.value = param1Int; }
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  763 */       CompressionStream.this.colorQuant = this.value;
/*  764 */       CompressionStream.this.colorQuantChanged = true;
/*      */     }
/*      */ 
/*      */     
/*  768 */     public String toString() { return "colorQuant: " + this.value; }
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
/*  779 */       this.stripFlag = param1Int1;
/*  780 */       this.meshIndex = param1Int2;
/*  781 */       CompressionStream.this.meshReferenceCount++;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void quantize(CompressionStream param1CompressionStream, HuffmanTable param1HuffmanTable) {
/*  787 */       CompressionStreamVertex compressionStreamVertex = CompressionStream.this.meshBuffer.getVertex(this.meshIndex);
/*  788 */       CompressionStream.this.lastPosition[0] = compressionStreamVertex.xAbsolute;
/*  789 */       CompressionStream.this.lastPosition[1] = compressionStreamVertex.yAbsolute;
/*  790 */       CompressionStream.this.lastPosition[2] = compressionStreamVertex.zAbsolute;
/*      */ 
/*      */ 
/*      */       
/*  794 */       if (compressionStreamVertex.color != null && !CompressionStream.this.lastElementColor && (!CompressionStream.this.lastElementNormal || !CompressionStream.this.lastLastElementColor)) {
/*      */         
/*  796 */         CompressionStream.this.lastColor[0] = compressionStreamVertex.color.rAbsolute;
/*  797 */         CompressionStream.this.lastColor[1] = compressionStreamVertex.color.gAbsolute;
/*  798 */         CompressionStream.this.lastColor[2] = compressionStreamVertex.color.bAbsolute;
/*  799 */         CompressionStream.this.lastColor[3] = compressionStreamVertex.color.aAbsolute;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  804 */       if (compressionStreamVertex.normal != null && !CompressionStream.this.lastElementNormal && (!CompressionStream.this.lastElementColor || !CompressionStream.this.lastLastElementNormal)) {
/*      */         
/*  806 */         CompressionStream.this.lastSextant = compressionStreamVertex.normal.sextant;
/*  807 */         CompressionStream.this.lastOctant = compressionStreamVertex.normal.octant;
/*  808 */         CompressionStream.this.lastU = compressionStreamVertex.normal.uAbsolute;
/*  809 */         CompressionStream.this.lastV = compressionStreamVertex.normal.vAbsolute;
/*  810 */         CompressionStream.this.lastSpecialNormal = compressionStreamVertex.normal.specialNormal;
/*      */       } 
/*      */     }
/*      */     
/*      */     void outputCommand(HuffmanTable param1HuffmanTable, CommandStream param1CommandStream) {
/*  815 */       int i = 32;
/*  816 */       long l = (this.stripFlag & true);
/*      */       
/*  818 */       i |= (this.meshIndex & 0xF) << 1 | this.stripFlag >> 1;
/*  819 */       param1CommandStream.addCommand(i, 8, l, 1);
/*      */     }
/*      */ 
/*      */     
/*  823 */     public String toString() { return "meshReference: stripFlag " + this.stripFlag + " meshIndex " + this.meshIndex; }
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
/*  837 */   void addVertex(Point3f paramPoint3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, (Color3f)null, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  850 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color3f)null, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  862 */   void addVertex(Point3f paramPoint3f, Color3f paramColor3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor3f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  874 */   void addVertex(Point3f paramPoint3f, Color4f paramColor4f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor4f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  888 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color3f paramColor3f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor3f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  902 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor4f, paramInt, 0)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  914 */   void addVertex(Point3f paramPoint3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, (Color3f)null, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  928 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color3f)null, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  942 */   void addVertex(Point3f paramPoint3f, Color3f paramColor3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor3f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  956 */   void addVertex(Point3f paramPoint3f, Color4f paramColor4f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, (Vector3f)null, paramColor4f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  971 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color3f paramColor3f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor3f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  986 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt1, int paramInt2) { this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, paramColor4f, paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1003 */     if (this.vertexColor3) {
/* 1004 */       this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color3f)paramObject, paramInt1, paramInt2));
/*      */     } else {
/*      */       
/* 1007 */       this.stream.add(new CompressionStreamVertex(this, paramPoint3f, paramVector3f, (Color4f)paramObject, paramInt1, paramInt2));
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
/* 1018 */   void addMeshReference(int paramInt1, int paramInt2) { this.stream.add(new MeshReference(paramInt1, paramInt2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1026 */   void addColor(Color3f paramColor3f) { this.stream.add(new CompressionStreamColor(this, paramColor3f)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1034 */   void addColor(Color4f paramColor4f) { this.stream.add(new CompressionStreamColor(this, paramColor4f)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1042 */   void addNormal(Vector3f paramVector3f) { this.stream.add(new CompressionStreamNormal(this, paramVector3f)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1053 */   void addPositionQuantization(int paramInt) { this.stream.add(new PositionQuant(paramInt)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1064 */   void addColorQuantization(int paramInt) { this.stream.add(new ColorQuant(paramInt)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1076 */   void addNormalQuantization(int paramInt) { this.stream.add(new NormalQuant(paramInt)); }
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
/* 1114 */     ByCopyGeometry(CompressionStream this$0, GeometryArray param1GeometryArray) { this(param1GeometryArray, param1GeometryArray.getInitialVertexIndex(), param1GeometryArray.getValidVertexCount()); }
/*      */     ByCopyGeometry(GeometryArray param1GeometryArray, int param1Int1, int param1Int2) {
/*      */       this.positions = null;
/*      */       this.normals = null;
/*      */       this.colors3 = null;
/*      */       this.colors4 = null;
/* 1120 */       this.positions = new Point3f[param1Int2]; byte b;
/* 1121 */       for (b = 0; b < param1Int2; b++) {
/* 1122 */         this.positions[b] = new Point3f();
/*      */       }
/* 1124 */       param1GeometryArray.getCoordinates(param1Int1, this.positions);
/*      */       
/* 1126 */       if (CompressionStream.this.vertexNormals) {
/* 1127 */         this.normals = new Vector3f[param1Int2];
/* 1128 */         for (b = 0; b < param1Int2; b++) {
/* 1129 */           this.normals[b] = new Vector3f();
/*      */         }
/* 1131 */         param1GeometryArray.getNormals(param1Int1, this.normals);
/*      */       } 
/*      */       
/* 1134 */       if (CompressionStream.this.vertexColor3) {
/* 1135 */         this.colors3 = new Color3f[param1Int2];
/* 1136 */         for (b = 0; b < param1Int2; b++) {
/* 1137 */           this.colors3[b] = new Color3f();
/*      */         }
/* 1139 */         param1GeometryArray.getColors(param1Int1, this.colors3);
/*      */       }
/* 1141 */       else if (CompressionStream.this.vertexColor4) {
/* 1142 */         this.colors4 = new Color4f[param1Int2];
/* 1143 */         for (b = 0; b < param1Int2; b++) {
/* 1144 */           this.colors4[b] = new Color4f();
/*      */         }
/* 1146 */         param1GeometryArray.getColors(param1Int1, this.colors4);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1151 */       Point3f point3f = this.positions[param1Int1];
/* 1152 */       int i = CompressionStream.this.meshBuffer.getMeshReference(point3f);
/*      */       
/* 1154 */       CompressionStream.this.meshBuffer; if (i == -1 || (CompressionStream.this.vertexNormals && !this.normals[param1Int1].equals(CompressionStream.this.meshBuffer.getNormal(i)))) {
/*      */ 
/*      */ 
/*      */         
/* 1158 */         Vector3f vector3f = CompressionStream.this.vertexNormals ? this.normals[param1Int1] : null;
/* 1159 */         Color3f color3f = CompressionStream.this.vertexColor3 ? this.colors3[param1Int1] : (CompressionStream.this.vertexColor4 ? this.colors4[param1Int1] : null);
/*      */ 
/*      */         
/* 1162 */         CompressionStream.this.addVertex(point3f, vector3f, color3f, param1Int2, 1);
/* 1163 */         CompressionStream.this.meshBuffer.push(point3f, color3f, vector3f);
/*      */       } else {
/*      */         
/* 1166 */         if (CompressionStream.this.vertexNormals);
/*      */ 
/*      */ 
/*      */         
/* 1170 */         if (CompressionStream.this.vertexColor3 && !this.colors3[param1Int1].equals(CompressionStream.this.meshBuffer.getColor3(i))) {
/*      */           
/* 1172 */           CompressionStream.this.addColor(this.colors3[param1Int1]);
/*      */         }
/* 1174 */         else if (CompressionStream.this.vertexColor4 && !this.colors4[param1Int1].equals(CompressionStream.this.meshBuffer.getColor4(i))) {
/*      */           
/* 1176 */           CompressionStream.this.addColor(this.colors4[param1Int1]);
/*      */         } 
/* 1178 */         CompressionStream.this.addMeshReference(param1Int2, i);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class IndexArrays
/*      */   {
/*      */     private IndexArrays() {}
/*      */     
/* 1187 */     int[] colorIndices = null;
/* 1188 */     int[] normalIndices = null;
/* 1189 */     int[] positionIndices = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getIndexArrays(GeometryArray paramGeometryArray, IndexArrays paramIndexArrays) {
/* 1197 */     IndexedGeometryArray indexedGeometryArray = (IndexedGeometryArray)paramGeometryArray;
/*      */     
/* 1199 */     int i = indexedGeometryArray.getInitialIndexIndex();
/* 1200 */     int j = indexedGeometryArray.getValidIndexCount();
/* 1201 */     int k = indexedGeometryArray.getVertexFormat();
/*      */     
/* 1203 */     boolean bool = false;
/* 1204 */     if ((k & 0x200) != 0)
/*      */     {
/* 1206 */       bool = true;
/*      */     }
/*      */     
/* 1209 */     paramIndexArrays.positionIndices = new int[j];
/* 1210 */     indexedGeometryArray.getCoordinateIndices(i, paramIndexArrays.positionIndices);
/*      */     
/* 1212 */     if (this.vertexNormals) {
/* 1213 */       if (bool) {
/* 1214 */         paramIndexArrays.normalIndices = paramIndexArrays.positionIndices;
/*      */       } else {
/*      */         
/* 1217 */         paramIndexArrays.normalIndices = new int[j];
/* 1218 */         indexedGeometryArray.getNormalIndices(i, paramIndexArrays.normalIndices);
/*      */       } 
/*      */     }
/* 1221 */     if (this.vertexColor3 || this.vertexColor4) {
/* 1222 */       if (bool) {
/* 1223 */         paramIndexArrays.colorIndices = paramIndexArrays.positionIndices;
/*      */       } else {
/*      */         
/* 1226 */         paramIndexArrays.colorIndices = new int[j];
/* 1227 */         indexedGeometryArray.getColorIndices(i, paramIndexArrays.colorIndices);
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
/* 1245 */     paramVertexIndices.pi = paramIndexArrays.positionIndices[paramInt];
/* 1246 */     if (this.vertexNormals)
/* 1247 */       paramVertexIndices.ni = paramIndexArrays.normalIndices[paramInt]; 
/* 1248 */     if (this.vertexColors) {
/* 1249 */       paramVertexIndices.ci = paramIndexArrays.colorIndices[paramInt];
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class IndexedByCopyGeometry
/*      */     extends ByCopyGeometry
/*      */   {
/* 1257 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1258 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedByCopyGeometry(GeometryArray param1GeometryArray) {
/* 1261 */       super(CompressionStream.this, param1GeometryArray, 0, param1GeometryArray.getVertexCount());
/* 1262 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1266 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1267 */       int i = CompressionStream.this.meshBuffer.getMeshReference(this.vi.pi);
/*      */       
/* 1269 */       CompressionStream.this.meshBuffer; if (i == -1 || (CompressionStream.this.vertexNormals && this.vi.ni != CompressionStream.this.meshBuffer.getNormalIndex(i))) {
/*      */ 
/*      */ 
/*      */         
/* 1273 */         Point3f point3f = this.positions[this.vi.pi];
/* 1274 */         Vector3f vector3f = CompressionStream.this.vertexNormals ? this.normals[this.vi.ni] : null;
/* 1275 */         Color3f color3f = CompressionStream.this.vertexColor3 ? this.colors3[this.vi.ci] : (CompressionStream.this.vertexColor4 ? this.colors4[this.vi.ci] : null);
/*      */ 
/*      */         
/* 1278 */         CompressionStream.this.addVertex(point3f, vector3f, color3f, param1Int2, 1);
/* 1279 */         CompressionStream.this.meshBuffer.push(this.vi.pi, this.vi.ci, this.vi.ni);
/*      */       } else {
/*      */         
/* 1282 */         if (CompressionStream.this.vertexNormals);
/*      */ 
/*      */ 
/*      */         
/* 1286 */         if (CompressionStream.this.vertexColor3 && this.vi.ci != CompressionStream.this.meshBuffer.getColorIndex(i)) {
/* 1287 */           CompressionStream.this.addColor(this.colors3[this.vi.ci]);
/*      */         }
/* 1289 */         else if (CompressionStream.this.vertexColor4 && this.vi.ci != CompressionStream.this.meshBuffer.getColorIndex(i)) {
/* 1290 */           CompressionStream.this.addColor(this.colors4[this.vi.ci]);
/*      */         } 
/* 1292 */         CompressionStream.this.addMeshReference(param1Int2, i);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class VertexCopy
/*      */   {
/*      */     private VertexCopy() {}
/*      */     
/* 1302 */     Object c = null;
/* 1303 */     Point3f p = null;
/* 1304 */     Vector3f n = null;
/* 1305 */     Color3f c3 = null;
/* 1306 */     Color4f c4 = null;
/*      */   }
/*      */   
/*      */   private void processVertexCopy(VertexCopy paramVertexCopy, int paramInt) {
/* 1310 */     int i = this.meshBuffer.getMeshReference(paramVertexCopy.p);
/*      */     
/* 1312 */     this.meshBuffer; if (i == -1 || (this.vertexNormals && !paramVertexCopy.n.equals(this.meshBuffer.getNormal(i)))) {
/*      */ 
/*      */ 
/*      */       
/* 1316 */       addVertex(paramVertexCopy.p, paramVertexCopy.n, paramVertexCopy.c, paramInt, 1);
/* 1317 */       this.meshBuffer.push(paramVertexCopy.p, paramVertexCopy.c, paramVertexCopy.n);
/*      */     } else {
/*      */       
/* 1320 */       if (this.vertexNormals);
/*      */ 
/*      */ 
/*      */       
/* 1324 */       if (this.vertexColor3 && !paramVertexCopy.c3.equals(this.meshBuffer.getColor3(i))) {
/* 1325 */         addColor(paramVertexCopy.c3);
/*      */       }
/* 1327 */       else if (this.vertexColor4 && !paramVertexCopy.c4.equals(this.meshBuffer.getColor4(i))) {
/* 1328 */         addColor(paramVertexCopy.c4);
/*      */       } 
/* 1330 */       addMeshReference(paramInt, i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processIndexedVertexCopy(VertexCopy paramVertexCopy, VertexIndices paramVertexIndices, int paramInt) {
/* 1338 */     int i = this.meshBuffer.getMeshReference(paramVertexIndices.pi);
/*      */     
/* 1340 */     this.meshBuffer; if (i == -1 || (this.vertexNormals && paramVertexIndices.ni != this.meshBuffer.getNormalIndex(i))) {
/*      */ 
/*      */ 
/*      */       
/* 1344 */       addVertex(paramVertexCopy.p, paramVertexCopy.n, paramVertexCopy.c, paramInt, 1);
/* 1345 */       this.meshBuffer.push(paramVertexIndices.pi, paramVertexIndices.ci, paramVertexIndices.ni);
/*      */     } else {
/*      */       
/* 1348 */       if (this.vertexNormals);
/*      */ 
/*      */ 
/*      */       
/* 1352 */       if (this.vertexColor3 && paramVertexIndices.ci != this.meshBuffer.getColorIndex(i)) {
/* 1353 */         addColor(paramVertexCopy.c3);
/*      */       }
/* 1355 */       else if (this.vertexColor4 && paramVertexIndices.ci != this.meshBuffer.getColorIndex(i)) {
/* 1356 */         addColor(paramVertexCopy.c4);
/*      */       } 
/* 1358 */       addMeshReference(paramInt, i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private abstract class InterleavedGeometry
/*      */     implements GeometryAccessor
/*      */   {
/* 1368 */     CompressionStream.VertexCopy vc = new CompressionStream.VertexCopy(null);
/*      */     
/* 1370 */     int vstride = 0;
/* 1371 */     int coffset = 0;
/* 1372 */     int noffset = 0;
/* 1373 */     int poffset = 0;
/* 1374 */     int tstride = 0;
/* 1375 */     int tcount = 0;
/*      */     
/*      */     InterleavedGeometry(GeometryArray param1GeometryArray) {
/* 1378 */       if (CompressionStream.this.vertexTextures) {
/* 1379 */         if (CompressionStream.this.vertexTexture2) { this.tstride = 2; }
/* 1380 */         else if (CompressionStream.this.vertexTexture3) { this.tstride = 3; }
/* 1381 */         else if (CompressionStream.this.vertexTexture4) { this.tstride = 4; }
/*      */         
/* 1383 */         this.tcount = param1GeometryArray.getTexCoordSetCount();
/* 1384 */         this.vstride += this.tcount * this.tstride;
/*      */       } 
/*      */       
/* 1387 */       if (CompressionStream.this.vertexColors) {
/* 1388 */         this.coffset = this.vstride;
/* 1389 */         if (CompressionStream.this.vertexColor3) { this.vstride += 3; }
/* 1390 */         else { this.vstride += 4; }
/*      */       
/*      */       } 
/* 1393 */       if (CompressionStream.this.vertexNormals) {
/* 1394 */         this.noffset = this.vstride;
/* 1395 */         this.vstride += 3;
/*      */       } 
/*      */       
/* 1398 */       this.poffset = this.vstride;
/* 1399 */       this.vstride += 3;
/*      */     }
/*      */     
/*      */     abstract void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy);
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1405 */       copyVertex(param1Int1, param1Int1, param1Int1, this.vc);
/* 1406 */       CompressionStream.this.processVertexCopy(this.vc, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class InterleavedGeometryFloat
/*      */     extends InterleavedGeometry
/*      */   {
/* 1415 */     float[] vdata = null;
/*      */     
/*      */     InterleavedGeometryFloat(GeometryArray param1GeometryArray) {
/* 1418 */       super(CompressionStream.this, param1GeometryArray);
/* 1419 */       this.vdata = param1GeometryArray.getInterleavedVertices();
/*      */     }
/*      */ 
/*      */     
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1424 */       int i = param1Int1 * this.vstride;
/* 1425 */       param1VertexCopy.p = new Point3f(this.vdata[i + this.poffset + 0], this.vdata[i + this.poffset + 1], this.vdata[i + this.poffset + 2]);
/*      */ 
/*      */ 
/*      */       
/* 1429 */       if (CompressionStream.this.vertexNormals) {
/* 1430 */         i = param1Int2 * this.vstride;
/* 1431 */         param1VertexCopy.n = new Vector3f(this.vdata[i + this.noffset + 0], this.vdata[i + this.noffset + 1], this.vdata[i + this.noffset + 2]);
/*      */       } 
/*      */ 
/*      */       
/* 1435 */       if (CompressionStream.this.vertexColor3) {
/* 1436 */         i = param1Int3 * this.vstride;
/* 1437 */         param1VertexCopy.c3 = new Color3f(this.vdata[i + this.coffset + 0], this.vdata[i + this.coffset + 1], this.vdata[i + this.coffset + 2]);
/*      */ 
/*      */         
/* 1440 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1442 */       else if (CompressionStream.this.vertexColor4) {
/* 1443 */         i = param1Int3 * this.vstride;
/* 1444 */         param1VertexCopy.c4 = new Color4f(this.vdata[i + this.coffset + 0], this.vdata[i + this.coffset + 1], this.vdata[i + this.coffset + 2], this.vdata[i + this.coffset + 3]);
/*      */ 
/*      */ 
/*      */         
/* 1448 */         param1VertexCopy.c = param1VertexCopy.c4;
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
/* 1460 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1461 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedInterleavedGeometryFloat(GeometryArray param1GeometryArray) {
/* 1464 */       super(CompressionStream.this, param1GeometryArray);
/* 1465 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1469 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1470 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1471 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class InterleavedGeometryNIO
/*      */     extends InterleavedGeometry
/*      */   {
/* 1480 */     FloatBufferWrapper fbw = null;
/*      */     
/*      */     InterleavedGeometryNIO(GeometryArray param1GeometryArray) {
/* 1483 */       super(CompressionStream.this, param1GeometryArray);
/* 1484 */       J3DBuffer j3DBuffer = param1GeometryArray.getInterleavedVertexBuffer();
/* 1485 */       if (BufferWrapper.getBufferType(j3DBuffer) == 3) {
/*      */         
/* 1487 */         this.fbw = new FloatBufferWrapper(j3DBuffer);
/*      */       } else {
/*      */         
/* 1490 */         throw new IllegalArgumentException("\ninterleaved vertex buffer must be FloatBuffer");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1497 */       int i = param1Int1 * this.vstride;
/* 1498 */       param1VertexCopy.p = new Point3f(this.fbw.get(i + this.poffset + 0), this.fbw.get(i + this.poffset + 1), this.fbw.get(i + this.poffset + 2));
/*      */ 
/*      */ 
/*      */       
/* 1502 */       if (CompressionStream.this.vertexNormals) {
/* 1503 */         i = param1Int2 * this.vstride;
/* 1504 */         param1VertexCopy.n = new Vector3f(this.fbw.get(i + this.noffset + 0), this.fbw.get(i + this.noffset + 1), this.fbw.get(i + this.noffset + 2));
/*      */       } 
/*      */ 
/*      */       
/* 1508 */       if (CompressionStream.this.vertexColor3) {
/* 1509 */         i = param1Int3 * this.vstride;
/* 1510 */         param1VertexCopy.c3 = new Color3f(this.fbw.get(i + this.coffset + 0), this.fbw.get(i + this.coffset + 1), this.fbw.get(i + this.coffset + 2));
/*      */ 
/*      */         
/* 1513 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1515 */       else if (CompressionStream.this.vertexColor4) {
/* 1516 */         i = param1Int3 * this.vstride;
/* 1517 */         param1VertexCopy.c4 = new Color4f(this.fbw.get(i + this.coffset + 0), this.fbw.get(i + this.coffset + 1), this.fbw.get(i + this.coffset + 2), this.fbw.get(i + this.coffset + 3));
/*      */ 
/*      */ 
/*      */         
/* 1521 */         param1VertexCopy.c = param1VertexCopy.c4;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class IndexedInterleavedGeometryNIO
/*      */     extends InterleavedGeometryNIO
/*      */   {
/* 1531 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1532 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedInterleavedGeometryNIO(GeometryArray param1GeometryArray) {
/* 1535 */       super(CompressionStream.this, param1GeometryArray);
/* 1536 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1540 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1541 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1542 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
/*      */     } }
/*      */   
/*      */   private class ByRefGeometry implements GeometryAccessor { CompressionStream.VertexCopy vc;
/*      */     byte[] colorsB;
/*      */     float[] colorsF;
/*      */     float[] normals;
/*      */     
/*      */     ByRefGeometry(GeometryArray param1GeometryArray) {
/* 1551 */       this.vc = new CompressionStream.VertexCopy(null);
/*      */       
/* 1553 */       this.colorsB = null;
/* 1554 */       this.colorsF = null;
/* 1555 */       this.normals = null;
/* 1556 */       this.positionsF = null;
/* 1557 */       this.positionsD = null;
/*      */       
/* 1559 */       this.initialPositionIndex = 0;
/* 1560 */       this.initialNormalIndex = 0;
/* 1561 */       this.initialColorIndex = 0;
/*      */ 
/*      */       
/* 1564 */       this.positionsF = param1GeometryArray.getCoordRefFloat();
/*      */ 
/*      */ 
/*      */       
/* 1568 */       this.positionsD = param1GeometryArray.getCoordRefDouble();
/*      */ 
/*      */ 
/*      */       
/* 1572 */       if (this.positionsF == null && this.positionsD == null) {
/* 1573 */         throw new UnsupportedOperationException("\nby-reference access to Point3{d,f} arrays");
/*      */       }
/*      */       
/* 1576 */       this.initialPositionIndex = param1GeometryArray.getInitialCoordIndex();
/*      */       
/* 1578 */       if (CompressionStream.this.vertexColors) {
/* 1579 */         this.colorsB = param1GeometryArray.getColorRefByte();
/*      */ 
/*      */ 
/*      */         
/* 1583 */         this.colorsF = param1GeometryArray.getColorRefFloat();
/*      */ 
/*      */ 
/*      */         
/* 1587 */         if (this.colorsB == null && this.colorsF == null) {
/* 1588 */           throw new UnsupportedOperationException("\nby-reference access to Color{3b,3f,4b,4f} arrays");
/*      */         }
/*      */         
/* 1591 */         this.initialColorIndex = param1GeometryArray.getInitialColorIndex();
/*      */       } 
/*      */       
/* 1594 */       if (CompressionStream.this.vertexNormals) {
/* 1595 */         this.normals = param1GeometryArray.getNormalRefFloat();
/*      */ 
/*      */ 
/*      */         
/* 1599 */         if (this.normals == null) {
/* 1600 */           throw new UnsupportedOperationException("\nby-reference access to Normal3f array");
/*      */         }
/*      */         
/* 1603 */         this.initialNormalIndex = param1GeometryArray.getInitialNormalIndex();
/*      */       } 
/*      */     }
/*      */     float[] positionsF; double[] positionsD; int initialPositionIndex; int initialNormalIndex; int initialColorIndex;
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1608 */       param1Int1 *= 3;
/* 1609 */       if (this.positionsF != null) {
/* 1610 */         param1VertexCopy.p = new Point3f(this.positionsF[param1Int1 + 0], this.positionsF[param1Int1 + 1], this.positionsF[param1Int1 + 2]);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1615 */         param1VertexCopy.p = new Point3f((float)this.positionsD[param1Int1 + 0], (float)this.positionsD[param1Int1 + 1], (float)this.positionsD[param1Int1 + 2]);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1620 */       param1Int2 *= 3;
/* 1621 */       if (CompressionStream.this.vertexNormals) {
/* 1622 */         param1VertexCopy.n = new Vector3f(this.normals[param1Int2 + 0], this.normals[param1Int2 + 1], this.normals[param1Int2 + 2]);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1627 */       if (CompressionStream.this.vertexColor3) {
/* 1628 */         param1Int3 *= 3;
/* 1629 */         if (this.colorsB != null) {
/* 1630 */           param1VertexCopy.c3 = new Color3f((this.colorsB[param1Int3 + 0] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 1] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 2] & 0xFF) * 0.003921569F);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1636 */           param1VertexCopy.c3 = new Color3f(this.colorsF[param1Int3 + 0], this.colorsF[param1Int3 + 1], this.colorsF[param1Int3 + 2]);
/*      */         } 
/*      */ 
/*      */         
/* 1640 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1642 */       else if (CompressionStream.this.vertexColor4) {
/* 1643 */         param1Int3 *= 4;
/* 1644 */         if (this.colorsB != null) {
/* 1645 */           param1VertexCopy.c4 = new Color4f((this.colorsB[param1Int3 + 0] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 1] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 2] & 0xFF) * 0.003921569F, (this.colorsB[param1Int3 + 3] & 0xFF) * 0.003921569F);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1652 */           param1VertexCopy.c4 = new Color4f(this.colorsF[param1Int3 + 0], this.colorsF[param1Int3 + 1], this.colorsF[param1Int3 + 2], this.colorsF[param1Int3 + 3]);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1657 */         param1VertexCopy.c = param1VertexCopy.c4;
/*      */       } 
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1662 */       copyVertex(param1Int1 + this.initialPositionIndex, param1Int1 + this.initialNormalIndex, param1Int1 + this.initialColorIndex, this.vc);
/*      */ 
/*      */ 
/*      */       
/* 1666 */       CompressionStream.this.processVertexCopy(this.vc, param1Int2);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class IndexedByRefGeometry
/*      */     extends ByRefGeometry
/*      */   {
/* 1675 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1676 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedByRefGeometry(GeometryArray param1GeometryArray) {
/* 1679 */       super(CompressionStream.this, param1GeometryArray);
/* 1680 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1684 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1685 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1686 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class ByRefGeometryNIO
/*      */     implements GeometryAccessor
/*      */   {
/* 1695 */     CompressionStream.VertexCopy vc = new CompressionStream.VertexCopy(null);
/*      */     
/* 1697 */     ByteBufferWrapper colorsB = null;
/* 1698 */     FloatBufferWrapper colorsF = null;
/* 1699 */     FloatBufferWrapper normals = null;
/* 1700 */     FloatBufferWrapper positionsF = null;
/* 1701 */     DoubleBufferWrapper positionsD = null;
/*      */     
/* 1703 */     int initialPositionIndex = 0;
/* 1704 */     int initialNormalIndex = 0;
/* 1705 */     int initialColorIndex = 0;
/*      */ 
/*      */     
/*      */     ByRefGeometryNIO(GeometryArray param1GeometryArray) {
/* 1709 */       J3DBuffer j3DBuffer = param1GeometryArray.getCoordRefBuffer();
/* 1710 */       this.initialPositionIndex = param1GeometryArray.getInitialCoordIndex();
/*      */       
/* 1712 */       switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*      */         case 3:
/* 1714 */           this.positionsF = new FloatBufferWrapper(j3DBuffer);
/*      */           break;
/*      */         
/*      */         case 4:
/* 1718 */           this.positionsD = new DoubleBufferWrapper(j3DBuffer);
/*      */           break;
/*      */         
/*      */         default:
/* 1722 */           throw new IllegalArgumentException("\nposition buffer must be FloatBuffer or DoubleBuffer");
/*      */       } 
/*      */ 
/*      */       
/* 1726 */       if (CompressionStream.this.vertexColors) {
/* 1727 */         j3DBuffer = param1GeometryArray.getColorRefBuffer();
/* 1728 */         this.initialColorIndex = param1GeometryArray.getInitialColorIndex();
/*      */         
/* 1730 */         switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*      */           case 2:
/* 1732 */             this.colorsB = new ByteBufferWrapper(j3DBuffer);
/*      */             break;
/*      */           
/*      */           case 3:
/* 1736 */             this.colorsF = new FloatBufferWrapper(j3DBuffer);
/*      */             break;
/*      */           
/*      */           default:
/* 1740 */             throw new IllegalArgumentException("\ncolor buffer must be ByteBuffer or FloatBuffer");
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 1745 */       if (CompressionStream.this.vertexNormals) {
/* 1746 */         j3DBuffer = param1GeometryArray.getNormalRefBuffer();
/* 1747 */         this.initialNormalIndex = param1GeometryArray.getInitialNormalIndex();
/*      */         
/* 1749 */         switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*      */           case 3:
/* 1751 */             this.normals = new FloatBufferWrapper(j3DBuffer);
/*      */             return;
/*      */         } 
/*      */         
/* 1755 */         throw new IllegalArgumentException("\nnormal buffer must be FloatBuffer");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void copyVertex(int param1Int1, int param1Int2, int param1Int3, CompressionStream.VertexCopy param1VertexCopy) {
/* 1762 */       param1Int1 *= 3;
/* 1763 */       if (this.positionsF != null) {
/* 1764 */         param1VertexCopy.p = new Point3f(this.positionsF.get(param1Int1 + 0), this.positionsF.get(param1Int1 + 1), this.positionsF.get(param1Int1 + 2));
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1769 */         param1VertexCopy.p = new Point3f((float)this.positionsD.get(param1Int1 + 0), (float)this.positionsD.get(param1Int1 + 1), (float)this.positionsD.get(param1Int1 + 2));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1774 */       param1Int2 *= 3;
/* 1775 */       if (CompressionStream.this.vertexNormals) {
/* 1776 */         param1VertexCopy.n = new Vector3f(this.normals.get(param1Int2 + 0), this.normals.get(param1Int2 + 1), this.normals.get(param1Int2 + 2));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1781 */       if (CompressionStream.this.vertexColor3) {
/* 1782 */         param1Int3 *= 3;
/* 1783 */         if (this.colorsB != null) {
/* 1784 */           param1VertexCopy.c3 = new Color3f((this.colorsB.get(param1Int3 + 0) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 1) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 2) & 0xFF) * 0.003921569F);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1790 */           param1VertexCopy.c3 = new Color3f(this.colorsF.get(param1Int3 + 0), this.colorsF.get(param1Int3 + 1), this.colorsF.get(param1Int3 + 2));
/*      */         } 
/*      */ 
/*      */         
/* 1794 */         param1VertexCopy.c = param1VertexCopy.c3;
/*      */       }
/* 1796 */       else if (CompressionStream.this.vertexColor4) {
/* 1797 */         param1Int3 *= 4;
/* 1798 */         if (this.colorsB != null) {
/* 1799 */           param1VertexCopy.c4 = new Color4f((this.colorsB.get(param1Int3 + 0) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 1) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 2) & 0xFF) * 0.003921569F, (this.colorsB.get(param1Int3 + 3) & 0xFF) * 0.003921569F);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1806 */           param1VertexCopy.c4 = new Color4f(this.colorsF.get(param1Int3 + 0), this.colorsF.get(param1Int3 + 1), this.colorsF.get(param1Int3 + 2), this.colorsF.get(param1Int3 + 3));
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1811 */         param1VertexCopy.c = param1VertexCopy.c4;
/*      */       } 
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1816 */       copyVertex(param1Int1 + this.initialPositionIndex, param1Int1 + this.initialNormalIndex, param1Int1 + this.initialColorIndex, this.vc);
/*      */ 
/*      */ 
/*      */       
/* 1820 */       CompressionStream.this.processVertexCopy(this.vc, param1Int2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class IndexedByRefGeometryNIO
/*      */     extends ByRefGeometryNIO
/*      */   {
/* 1829 */     CompressionStream.IndexArrays ia = new CompressionStream.IndexArrays(null);
/* 1830 */     CompressionStream.VertexIndices vi = new CompressionStream.VertexIndices(null);
/*      */     
/*      */     IndexedByRefGeometryNIO(GeometryArray param1GeometryArray) {
/* 1833 */       super(CompressionStream.this, param1GeometryArray);
/* 1834 */       this$0.getIndexArrays(param1GeometryArray, this.ia);
/*      */     }
/*      */     
/*      */     public void processVertex(int param1Int1, int param1Int2) {
/* 1838 */       CompressionStream.this.getVertexIndices(param1Int1, this.ia, this.vi);
/* 1839 */       copyVertex(this.vi.pi, this.vi.ni, this.vi.ci, this.vc);
/* 1840 */       CompressionStream.this.processIndexedVertexCopy(this.vc, this.vi, param1Int2);
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
/* 1853 */     int i = 0;
/* 1854 */     int j = 0;
/* 1855 */     int k = paramGeometryArray.getVertexFormat();
/* 1856 */     ByRefGeometry byRefGeometry = null;
/*      */     
/* 1858 */     if (this.streamType != getStreamType(paramGeometryArray)) {
/* 1859 */       throw new IllegalArgumentException("GeometryArray has inconsistent dimensionality");
/*      */     }
/*      */     
/* 1862 */     if (this.vertexComponents != getVertexComponents(k)) {
/* 1863 */       throw new IllegalArgumentException("GeometryArray has inconsistent vertex components");
/*      */     }
/*      */ 
/*      */     
/* 1867 */     boolean bool1 = ((k & 0x800) != 0) ? 1 : 0;
/* 1868 */     boolean bool2 = ((k & 0x80) != 0) ? 1 : 0;
/* 1869 */     boolean bool3 = ((k & 0x100) != 0) ? 1 : 0;
/* 1870 */     boolean bool = paramGeometryArray instanceof IndexedGeometryArray;
/*      */     
/* 1872 */     if (bool) {
/*      */ 
/*      */ 
/*      */       
/* 1876 */       i = 0;
/* 1877 */       j = ((IndexedGeometryArray)paramGeometryArray).getValidIndexCount();
/*      */     } 
/*      */     
/* 1880 */     if (!bool2) {
/*      */       
/* 1882 */       if (bool) {
/* 1883 */         byRefGeometry = new IndexedByCopyGeometry(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1886 */         i = 0;
/* 1887 */         j = paramGeometryArray.getValidVertexCount();
/* 1888 */         ByCopyGeometry byCopyGeometry = new ByCopyGeometry(paramGeometryArray);
/*      */       }
/*      */     
/* 1891 */     } else if (bool3 && bool1) {
/*      */       
/* 1893 */       if (bool) {
/* 1894 */         IndexedInterleavedGeometryNIO indexedInterleavedGeometryNIO = new IndexedInterleavedGeometryNIO(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1897 */         i = paramGeometryArray.getInitialVertexIndex();
/* 1898 */         j = paramGeometryArray.getValidVertexCount();
/* 1899 */         InterleavedGeometryNIO interleavedGeometryNIO = new InterleavedGeometryNIO(paramGeometryArray);
/*      */       }
/*      */     
/* 1902 */     } else if (bool3 && !bool1) {
/*      */       
/* 1904 */       if (bool) {
/* 1905 */         IndexedInterleavedGeometryFloat indexedInterleavedGeometryFloat = new IndexedInterleavedGeometryFloat(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1908 */         i = paramGeometryArray.getInitialVertexIndex();
/* 1909 */         j = paramGeometryArray.getValidVertexCount();
/* 1910 */         InterleavedGeometryFloat interleavedGeometryFloat = new InterleavedGeometryFloat(paramGeometryArray);
/*      */       }
/*      */     
/* 1913 */     } else if (!bool3 && bool1) {
/*      */       
/* 1915 */       if (bool) {
/* 1916 */         IndexedByRefGeometryNIO indexedByRefGeometryNIO = new IndexedByRefGeometryNIO(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1919 */         i = 0;
/* 1920 */         j = paramGeometryArray.getValidVertexCount();
/* 1921 */         ByRefGeometryNIO byRefGeometryNIO = new ByRefGeometryNIO(paramGeometryArray);
/*      */       }
/*      */     
/* 1924 */     } else if (!bool3 && !bool1) {
/*      */       
/* 1926 */       if (bool) {
/* 1927 */         IndexedByRefGeometry indexedByRefGeometry = new IndexedByRefGeometry(paramGeometryArray);
/*      */       } else {
/*      */         
/* 1930 */         i = 0;
/* 1931 */         j = paramGeometryArray.getValidVertexCount();
/* 1932 */         byRefGeometry = new ByRefGeometry(paramGeometryArray);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1937 */     int m = 0;
/* 1938 */     int[] arrayOfInt = null;
/* 1939 */     byte b1 = 0;
/* 1940 */     byte b2 = 1;
/* 1941 */     boolean bool4 = false;
/* 1942 */     boolean bool5 = false;
/*      */     
/* 1944 */     if (paramGeometryArray instanceof javax.media.j3d.TriangleStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleStripArray || paramGeometryArray instanceof javax.media.j3d.LineStripArray || paramGeometryArray instanceof javax.media.j3d.IndexedLineStripArray) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1949 */       bool4 = true;
/* 1950 */       b2 = 3;
/*      */     
/*      */     }
/* 1953 */     else if (paramGeometryArray instanceof javax.media.j3d.TriangleFanArray || paramGeometryArray instanceof javax.media.j3d.IndexedTriangleFanArray) {
/*      */ 
/*      */       
/* 1956 */       bool4 = true;
/* 1957 */       b2 = 2;
/*      */     
/*      */     }
/* 1960 */     else if (paramGeometryArray instanceof javax.media.j3d.QuadArray || paramGeometryArray instanceof javax.media.j3d.IndexedQuadArray) {
/*      */ 
/*      */ 
/*      */       
/* 1964 */       bool5 = true;
/* 1965 */       b1 = 4;
/* 1966 */       b2 = 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1971 */     if (bool4) {
/* 1972 */       if (bool) {
/*      */         
/* 1974 */         IndexedGeometryStripArray indexedGeometryStripArray = (IndexedGeometryStripArray)paramGeometryArray;
/*      */         
/* 1976 */         m = indexedGeometryStripArray.getNumStrips();
/* 1977 */         arrayOfInt = new int[m];
/* 1978 */         indexedGeometryStripArray.getStripIndexCounts(arrayOfInt);
/*      */       }
/*      */       else {
/*      */         
/* 1982 */         GeometryStripArray geometryStripArray = (GeometryStripArray)paramGeometryArray;
/*      */         
/* 1984 */         m = geometryStripArray.getNumStrips();
/* 1985 */         arrayOfInt = new int[m];
/* 1986 */         geometryStripArray.getStripVertexCounts(arrayOfInt);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1991 */     int n = i;
/* 1992 */     if (bool4) {
/* 1993 */       for (byte b = 0; b < m; b++) {
/* 1994 */         byRefGeometry.processVertex(n++, 1);
/* 1995 */         for (byte b3 = 1; b3 < arrayOfInt[b]; b3++) {
/* 1996 */           byRefGeometry.processVertex(n++, b2);
/*      */         }
/*      */       }
/*      */     
/* 2000 */     } else if (bool5) {
/* 2001 */       while (n < i + j) {
/* 2002 */         byRefGeometry.processVertex(n++, 1);
/* 2003 */         for (byte b = 1; b < b1; b++) {
/* 2004 */           byRefGeometry.processVertex(n++, b2);
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 2009 */       while (n < i + j) {
/* 2010 */         byRefGeometry.processVertex(n++, 1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void print() {
/* 2019 */     System.out.println("\nstream has " + this.stream.size() + " entries");
/* 2020 */     System.out.println("uncompressed size " + this.byteCount + " bytes");
/* 2021 */     System.out.println("upper position bound: " + this.mcBounds[1].toString());
/* 2022 */     System.out.println("lower position bound: " + this.mcBounds[0].toString());
/* 2023 */     System.out.println("X, Y, Z centers (" + (float)this.center[0] + " " + (float)this.center[1] + " " + (float)this.center[2] + ")\n" + "scale " + (float)this.scale + "\n");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2029 */     Iterator iterator = this.stream.iterator();
/* 2030 */     while (iterator.hasNext()) {
/* 2031 */       System.out.println(iterator.next().toString() + "\n");
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
/* 2086 */     this();
/*      */ 
/*      */     
/* 2089 */     if (paramArrayOfShape3D == null) {
/* 2090 */       throw new IllegalArgumentException("null Shape3D array");
/*      */     }
/* 2092 */     if (paramArrayOfShape3D.length == 0) {
/* 2093 */       throw new IllegalArgumentException("zero-length Shape3D array");
/*      */     }
/* 2095 */     if (paramArrayOfShape3D[false] == null) {
/* 2096 */       throw new IllegalArgumentException("Shape3D at index 0 is null");
/*      */     }
/* 2098 */     long l = 0L;
/*      */ 
/*      */     
/* 2101 */     Geometry geometry = paramArrayOfShape3D[0].getGeometry();
/* 2102 */     if (!(geometry instanceof GeometryArray)) {
/* 2103 */       throw new IllegalArgumentException("Shape3D at index 0 is not a GeometryArray");
/*      */     }
/*      */     
/* 2106 */     GeometryArray geometryArray = (GeometryArray)geometry;
/* 2107 */     this.streamType = getStreamType(geometryArray);
/* 2108 */     this.vertexComponents = getVertexComponents(geometryArray.getVertexFormat());
/*      */ 
/*      */     
/* 2111 */     addPositionQuantization(paramInt1);
/* 2112 */     addColorQuantization(paramInt2);
/* 2113 */     addNormalQuantization(paramInt3);
/*      */ 
/*      */     
/* 2116 */     for (byte b = 0; b < paramArrayOfShape3D.length; b++) {
/*      */ 
/*      */       
/* 2119 */       geometry = paramArrayOfShape3D[b].getGeometry();
/* 2120 */       if (!(geometry instanceof GeometryArray)) {
/* 2121 */         throw new IllegalArgumentException("Shape3D at index " + b + " is not a GeometryArray");
/*      */       }
/*      */ 
/*      */       
/* 2125 */       Appearance appearance = paramArrayOfShape3D[b].getAppearance();
/* 2126 */       if (appearance != null) {
/* 2127 */         Material material = appearance.getMaterial();
/* 2128 */         if (material != null) {
/* 2129 */           material.getDiffuseColor(this.c3f);
/* 2130 */           if (this.vertexColor4) {
/* 2131 */             this.c4f.set(this.c3f.x, this.c3f.y, this.c3f.z, 1.0F);
/* 2132 */             addColor(this.c4f);
/*      */           } else {
/* 2134 */             addColor(this.c3f);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2139 */       addGeometryArray((GeometryArray)geometry);
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
/* 2186 */   public CompressionStream(Shape3D[] paramArrayOfShape3D) { this(16, 9, 6, paramArrayOfShape3D); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2222 */     this();
/*      */ 
/*      */     
/* 2225 */     if (paramArrayOfGeometryInfo == null) {
/* 2226 */       throw new IllegalArgumentException("null GeometryInfo array");
/*      */     }
/* 2228 */     if (paramArrayOfGeometryInfo.length == 0) {
/* 2229 */       throw new IllegalArgumentException("zero-length GeometryInfo array");
/*      */     }
/*      */     
/* 2232 */     if (paramArrayOfGeometryInfo[false] == null) {
/* 2233 */       throw new IllegalArgumentException("GeometryInfo at index 0 is null");
/*      */     }
/*      */     
/* 2236 */     long l = 0L;
/*      */ 
/*      */     
/* 2239 */     GeometryArray geometryArray = paramArrayOfGeometryInfo[0].getGeometryArray();
/* 2240 */     this.streamType = getStreamType(geometryArray);
/* 2241 */     this.vertexComponents = getVertexComponents(geometryArray.getVertexFormat());
/*      */ 
/*      */     
/* 2244 */     addPositionQuantization(paramInt1);
/* 2245 */     addColorQuantization(paramInt2);
/* 2246 */     addNormalQuantization(paramInt3);
/*      */ 
/*      */     
/* 2249 */     for (byte b = 0; b < paramArrayOfGeometryInfo.length; b++)
/*      */     {
/* 2251 */       addGeometryArray(paramArrayOfGeometryInfo[b].getGeometryArray());
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
/* 2288 */   public CompressionStream(GeometryInfo[] paramArrayOfGeometryInfo) { this(16, 9, 6, paramArrayOfGeometryInfo); }
/*      */ 
/*      */ 
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
/* 2301 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 2302 */     arrayOfPoint3d[0] = new Point3d(this.mcBounds[0]);
/* 2303 */     arrayOfPoint3d[1] = new Point3d(this.mcBounds[1]);
/* 2304 */     return arrayOfPoint3d;
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
/* 2316 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/* 2317 */     arrayOfPoint3d[0] = new Point3d(this.ncBounds[0]);
/* 2318 */     arrayOfPoint3d[1] = new Point3d(this.ncBounds[1]);
/* 2319 */     return arrayOfPoint3d;
/*      */   }
/*      */   
/*      */   private static interface GeometryAccessor {
/*      */     void processVertex(int param1Int1, int param1Int2);
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\compression\CompressionStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */