/*       */ package javax.media.j3d;
/*       */ 
/*       */ import com.sun.j3d.internal.ByteBufferWrapper;
/*       */ import com.sun.j3d.internal.Distance;
/*       */ import com.sun.j3d.internal.DoubleBufferWrapper;
/*       */ import com.sun.j3d.internal.FloatBufferWrapper;
/*       */ import java.util.ArrayList;
/*       */ import java.util.Enumeration;
/*       */ import java.util.HashMap;
/*       */ import java.util.HashSet;
/*       */ import java.util.Set;
/*       */ import javax.vecmath.Color3b;
/*       */ import javax.vecmath.Color3f;
/*       */ import javax.vecmath.Color4b;
/*       */ import javax.vecmath.Color4f;
/*       */ import javax.vecmath.Point2d;
/*       */ import javax.vecmath.Point2f;
/*       */ import javax.vecmath.Point3d;
/*       */ import javax.vecmath.Point3f;
/*       */ import javax.vecmath.Point4d;
/*       */ import javax.vecmath.Point4f;
/*       */ import javax.vecmath.TexCoord2f;
/*       */ import javax.vecmath.TexCoord3f;
/*       */ import javax.vecmath.TexCoord4f;
/*       */ import javax.vecmath.Vector3d;
/*       */ import javax.vecmath.Vector3f;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ abstract class GeometryArrayRetained
/*       */   extends GeometryRetained
/*       */ {
/*       */   int vertexFormat;
/*       */   int c4fAllocated;
/*       */   int vertexCount;
/*       */   int validVertexCount;
/*       */   float[] vertexData;
/*       */   private float[][] mvertexData;
/*       */   int stride;
/*       */   int texCoordStride;
/*       */   int coordinateOffset;
/*       */   int normalOffset;
/*       */   int colorOffset;
/*       */   int textureOffset;
/*       */   int[] vertexAttrOffsets;
/*       */   int vertexAttrStride;
/*       */   private float[] lastAlpha;
/*       */   float lastScreenAlpha;
/*       */   int colorChanged;
/*       */   static final float ByteToFloatScale = 0.003921569F;
/*       */   static final float FloatToByteScale = 255.0F;
/*       */   boolean inUpdater;
/*       */   ArrayList gaList;
/*       */   static final int targetThreads = 192;
/*       */   float[] floatRefCoords;
/*       */   double[] doubleRefCoords;
/*       */   Point3d[] p3dRefCoords;
/*       */   Point3f[] p3fRefCoords;
/*       */   J3DBuffer coordRefBuffer;
/*       */   FloatBufferWrapper floatBufferRefCoords;
/*       */   DoubleBufferWrapper doubleBufferRefCoords;
/*       */   int initialCoordIndex;
/*       */   int initialColorIndex;
/*       */   int initialNormalIndex;
/*       */   int[] initialTexCoordIndex;
/*       */   int[] initialVertexAttrIndex;
/*       */   int initialVertexIndex;
/*       */   float[] floatRefColors;
/*       */   byte[] byteRefColors;
/*       */   Color3f[] c3fRefColors;
/*       */   Color4f[] c4fRefColors;
/*       */   Color3b[] c3bRefColors;
/*       */   Color4b[] c4bRefColors;
/*       */   J3DBuffer colorRefBuffer;
/*       */   FloatBufferWrapper floatBufferRefColors;
/*       */   ByteBufferWrapper byteBufferRefColors;
/*       */   int vertexType;
/*       */   static final int PF = 1;
/*       */   static final int PD = 2;
/*       */   static final int P3F = 4;
/*       */   static final int P3D = 8;
/*       */   static final int VERTEX_DEFINED = 15;
/*       */   static final int CF = 16;
/*       */   static final int CUB = 32;
/*       */   static final int C3F = 64;
/*       */   static final int C4F = 128;
/*       */   static final int C3UB = 256;
/*       */   static final int C4UB = 512;
/*       */   static final int COLOR_DEFINED = 1008;
/*       */   static final int NF = 1024;
/*       */   static final int N3F = 2048;
/*       */   static final int NORMAL_DEFINED = 3072;
/*       */   static final int TF = 4096;
/*       */   static final int T2F = 8192;
/*       */   static final int T3F = 16384;
/*       */   static final int TEXCOORD_DEFINED = 28672;
/*       */   static final int AF = 32768;
/*       */   static final int VATTR_DEFINED = 32768;
/*       */   private int texCoordType;
/*       */   private int vertexAttrType;
/*       */   static final int COORD_FLOAT = 1;
/*       */   static final int COORD_DOUBLE = 2;
/*       */   static final int COLOR_FLOAT = 4;
/*       */   static final int COLOR_BYTE = 8;
/*       */   static final int NORMAL_FLOAT = 16;
/*       */   static final int TEXCOORD_FLOAT = 32;
/*       */   static final int VATTR_FLOAT = 64;
/*       */   float[] floatRefNormals;
/*       */   Vector3f[] v3fRefNormals;
/*       */   J3DBuffer normalRefBuffer;
/*       */   FloatBufferWrapper floatBufferRefNormals;
/*       */   float[][] floatRefVertexAttrs;
/*       */   J3DBuffer[] vertexAttrsRefBuffer;
/*       */   FloatBufferWrapper[] floatBufferRefVertexAttrs;
/*       */   Object[] nioFloatBufferRefVertexAttrs;
/*       */   Object[] refTexCoords;
/*       */   TexCoord2f[] t2fRefTexCoords;
/*       */   TexCoord3f[] t3fRefTexCoords;
/*       */   Object[] refTexCoordsBuffer;
/*       */   float[] interLeavedVertexData;
/*       */   J3DBuffer interleavedVertexBuffer;
/*       */   FloatBufferWrapper interleavedFloatBufferImpl;
/*       */   float[] mirrorFloatRefCoords;
/*       */   double[] mirrorDoubleRefCoords;
/*       */   float[] mirrorFloatRefNormals;
/*       */   float[][] mirrorFloatRefVertexAttrs;
/*       */   float[] mirrorFloatRefTexCoords;
/*       */   Object[] mirrorRefTexCoords;
/*       */   float[][] mirrorFloatRefColors;
/*       */   byte[][] mirrorUnsignedByteRefColors;
/*       */   float[][] mirrorInterleavedColorPointer;
/*       */   int mirrorVertexAllocated;
/*       */   int mirrorColorAllocated;
/*       */   boolean mirrorNormalAllocated;
/*       */   static final int COORDINATE_CHANGED = 1;
/*       */   static final int NORMAL_CHANGED = 2;
/*       */   static final int COLOR_CHANGED = 4;
/*       */   static final int TEXTURE_CHANGED = 8;
/*       */   static final int BOUNDS_CHANGED = 16;
/*       */   static final int INDEX_CHANGED = 32;
/*       */   static final int STRIPCOUNT_CHANGED = 64;
/*       */   static final int VATTR_CHANGED = 128;
/*       */   static final int VERTEX_CHANGED = 143;
/*   268 */   static final int[] defaultTexCoordSetMap = { 0 };
/*       */ 
/*       */ 
/*       */   
/*       */   int texCoordSetCount;
/*       */ 
/*       */ 
/*       */   
/*       */   int[] texCoordSetMap;
/*       */ 
/*       */ 
/*       */   
/*       */   int[] texCoordSetMapOffset;
/*       */ 
/*       */ 
/*       */   
/*       */   int vertexAttrCount;
/*       */ 
/*       */ 
/*       */   
/*       */   int[] vertexAttrSizes;
/*       */ 
/*       */ 
/*       */   
/*       */   long pVertexBuffers;
/*       */ 
/*       */   
/*       */   int dirtyFlag;
/*       */ 
/*       */   
/*       */   int resourceCreationMask;
/*       */ 
/*       */   
/*       */   private HashMap dlistUsers;
/*       */ 
/*       */   
/*       */   private long[] timeStampPerDlist;
/*       */ 
/*       */   
/*       */   int dlistId;
/*       */ 
/*       */   
/*       */   Integer dlistObj;
/*       */ 
/*       */   
/*       */   static final int INIT_MIRROR_GEOMETRY = 2;
/*       */ 
/*       */   
/*       */   ArrayList morphUniverseList;
/*       */ 
/*       */   
/*       */   ArrayList morphUserLists;
/*       */ 
/*       */   
/*       */   int[] geoOffset;
/*       */ 
/*       */   
/*       */   int[] compileVcount;
/*       */ 
/*       */   
/*       */   boolean isCompiled;
/*       */ 
/*       */   
/*       */   boolean isShared;
/*       */ 
/*       */   
/*       */   IndexedGeometryArrayRetained cloneSourceArray;
/*       */ 
/*       */   
/*       */   static final double EPS = 1.0E-13D;
/*       */ 
/*       */ 
/*       */   
/*       */   void freeD3DArray(boolean paramBoolean) {
/*   342 */     assert VirtualUniverse.mc.isD3D();
/*   343 */     Pipeline.getPipeline().freeD3DArray(this, paramBoolean); } GeometryArrayRetained() { this.c4fAllocated = 0; this.lastAlpha = new float[1]; this.lastScreenAlpha = -1.0F; this.colorChanged = 0; this.inUpdater = false; this.gaList = new ArrayList(1); this.floatRefCoords = null; this.doubleRefCoords = null; this.p3dRefCoords = null; this.p3fRefCoords = null; this.coordRefBuffer = null; this.floatBufferRefCoords = null; this.doubleBufferRefCoords = null; this.initialCoordIndex = 0; this.initialColorIndex = 0; this.initialNormalIndex = 0; this.initialTexCoordIndex = null; this.initialVertexAttrIndex = null; this.initialVertexIndex = 0; this.floatRefColors = null; this.byteRefColors = null; this.c3fRefColors = null; this.c4fRefColors = null; this.c3bRefColors = null; this.c4bRefColors = null; this.colorRefBuffer = null; this.floatBufferRefColors = null; this.byteBufferRefColors = null; this.vertexType = 0; this.texCoordType = 0; this.vertexAttrType = 0; this.floatRefNormals = null; this.v3fRefNormals = null; this.normalRefBuffer = null; this.floatBufferRefNormals = null; this.floatRefVertexAttrs = (float[][])null; this.vertexAttrsRefBuffer = null; this.floatBufferRefVertexAttrs = null; this.nioFloatBufferRefVertexAttrs = null; this.refTexCoords = null; this.t2fRefTexCoords = null; this.t3fRefTexCoords = null; this.refTexCoordsBuffer = null; this.interLeavedVertexData = null; this.interleavedVertexBuffer = null; this.interleavedFloatBufferImpl = null; this.mirrorFloatRefCoords = null; this.mirrorDoubleRefCoords = null; this.mirrorFloatRefNormals = null; this.mirrorFloatRefVertexAttrs = (float[][])null; this.mirrorFloatRefTexCoords = null; this.mirrorRefTexCoords = null; this.mirrorFloatRefColors = new float[1][]; this.mirrorUnsignedByteRefColors = new byte[1][]; this.mirrorInterleavedColorPointer = (float[][])null; this.mirrorVertexAllocated = 0; this.mirrorColorAllocated = 0; this.mirrorNormalAllocated = false; this.texCoordSetCount = 0; this.texCoordSetMap = null; this.texCoordSetMapOffset = null; this.vertexAttrCount = 0; this.vertexAttrSizes = null; this.pVertexBuffers = 0L; this.resourceCreationMask = 0; this.dlistUsers = null; this.timeStampPerDlist = new long[2]; this.dlistId = -1; this.dlistObj = null; this.morphUniverseList = null; this.morphUserLists = null;
/*       */     this.isCompiled = false;
/*       */     this.isShared = false;
/*       */     this.cloneSourceArray = null;
/*   347 */     this.dirtyFlag = 175;
/*   348 */     this.lastAlpha[0] = 1.0F; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setLive(boolean paramBoolean, int paramInt) {
/*   353 */     this.dirtyFlag = 175;
/*   354 */     this.isEditable = !isWriteStatic();
/*   355 */     doSetLive(paramBoolean, paramInt);
/*   356 */     markAsLive();
/*       */ 
/*       */     
/*   359 */     if (this.refCount > 1) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   370 */       this.isShared = true;
/*       */ 
/*       */ 
/*       */     
/*       */     }
/*   375 */     else if (this instanceof IndexedGeometryArrayRetained) {
/*       */       
/*   377 */       J3dMessage j3dMessage = new J3dMessage();
/*   378 */       j3dMessage.threads = 1024;
/*   379 */       j3dMessage.type = 17;
/*   380 */       j3dMessage.universe = null;
/*   381 */       j3dMessage.args[0] = null;
/*   382 */       j3dMessage.args[1] = this;
/*   383 */       j3dMessage.args[2] = new Integer(2);
/*   384 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   void clearLive(int paramInt) {
/*   391 */     super.clearLive(paramInt);
/*       */     
/*   393 */     if (this.refCount <= 0) {
/*   394 */       if (this.pVertexBuffers != 0L) {
/*   395 */         J3dMessage j3dMessage = new J3dMessage();
/*   396 */         j3dMessage.threads = 16;
/*   397 */         j3dMessage.type = 44;
/*   398 */         j3dMessage.universe = null;
/*   399 */         j3dMessage.view = null;
/*   400 */         j3dMessage.args[0] = null;
/*   401 */         j3dMessage.args[1] = this;
/*       */ 
/*       */         
/*   404 */         Enumeration enumeration = Screen3D.deviceRendererMap.elements();
/*   405 */         Renderer renderer = (Renderer)enumeration.nextElement();
/*   406 */         renderer.rendererStructure.addMessage(j3dMessage);
/*   407 */         VirtualUniverse.mc.setWorkForRequestRenderer();
/*       */       } 
/*   409 */       this.isShared = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox() {
/*   417 */     if (this.boundsDirty && VirtualUniverse.mc.cacheAutoComputedBounds) {
/*   418 */       for (ArrayList arrayList : this.userLists) {
/*   419 */         for (Shape3DRetained shape3DRetained : arrayList) {
/*   420 */           shape3DRetained.dirtyBoundsCache();
/*       */         }
/*       */       } 
/*       */     }
/*   424 */     if ((this.vertexFormat & 0x80) == 0) {
/*       */       
/*   426 */       computeBoundingBox(this.initialVertexIndex, this.vertexData);
/*       */     }
/*   428 */     else if ((this.vertexFormat & 0x800) != 0) {
/*       */       
/*   430 */       if ((this.vertexFormat & 0x100) != 0) {
/*   431 */         computeBoundingBox(this.initialCoordIndex, this.interleavedFloatBufferImpl);
/*   432 */       } else if ((this.vertexType & true) != 0) {
/*   433 */         computeBoundingBox(this.floatBufferRefCoords);
/*   434 */       } else if ((this.vertexType & 0x2) != 0) {
/*   435 */         computeBoundingBox(this.doubleBufferRefCoords);
/*       */       }
/*       */     
/*   438 */     } else if ((this.vertexFormat & 0x100) != 0) {
/*       */       
/*   440 */       computeBoundingBox(this.initialCoordIndex, this.interLeavedVertexData);
/*   441 */     } else if ((this.vertexType & true) != 0) {
/*       */       
/*   443 */       computeBoundingBox(this.floatRefCoords);
/*   444 */     } else if ((this.vertexType & 0x4) != 0) {
/*       */       
/*   446 */       computeBoundingBox(this.p3fRefCoords);
/*   447 */     } else if ((this.vertexType & 0x8) != 0) {
/*       */       
/*   449 */       computeBoundingBox(this.p3dRefCoords);
/*   450 */     } else if ((this.vertexType & 0x2) != 0) {
/*       */       
/*   452 */       computeBoundingBox(this.doubleRefCoords);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void processCoordsChanged(boolean paramBoolean) {
/*   466 */     if (paramBoolean) {
/*   467 */       synchronized (this.geoBounds) {
/*   468 */         this.geoBounds.setLower(-1.0D, -1.0D, -1.0D);
/*   469 */         this.geoBounds.setUpper(1.0D, 1.0D, 1.0D);
/*   470 */         this.boundsDirty = false;
/*       */       } 
/*   472 */       synchronized (this.centroid) {
/*   473 */         this.recompCentroid = false;
/*   474 */         this.centroid.set(this.geoBounds.getCenter());
/*       */       }
/*       */     
/*       */     }
/*       */     else {
/*       */       
/*   480 */       synchronized (this.centroid) {
/*   481 */         this.recompCentroid = true;
/*       */       } 
/*       */       
/*   484 */       synchronized (this.geoBounds) {
/*   485 */         this.boundsDirty = true;
/*   486 */         computeBoundingBox();
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(int paramInt, float[] paramArrayOfFloat) {
/*   505 */     synchronized (this.geoBounds) {
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   510 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*   513 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*       */       
/*   517 */       int i = paramInt * this.stride + this.coordinateOffset;
/*       */       
/*   519 */       double d2 = paramArrayOfFloat[i], d1 = d2;
/*   520 */       double d4 = paramArrayOfFloat[i + 1], d3 = d4;
/*   521 */       double d6 = paramArrayOfFloat[i + 2], d5 = d6;
/*   522 */       i += this.stride;
/*   523 */       for (byte b = 1; b < this.validVertexCount; b++) {
/*   524 */         if (paramArrayOfFloat[i] > d2)
/*   525 */           d2 = paramArrayOfFloat[i]; 
/*   526 */         if (paramArrayOfFloat[i] < d1) {
/*   527 */           d1 = paramArrayOfFloat[i];
/*       */         }
/*   529 */         if (paramArrayOfFloat[i + 1] > d4)
/*   530 */           d4 = paramArrayOfFloat[i + 1]; 
/*   531 */         if (paramArrayOfFloat[i + 1] < d3) {
/*   532 */           d3 = paramArrayOfFloat[i + 1];
/*       */         }
/*   534 */         if (paramArrayOfFloat[i + 2] > d6)
/*   535 */           d6 = paramArrayOfFloat[i + 2]; 
/*   536 */         if (paramArrayOfFloat[i + 2] < d5) {
/*   537 */           d5 = paramArrayOfFloat[i + 2];
/*       */         }
/*   539 */         i += this.stride;
/*       */       } 
/*       */       
/*   542 */       this.geoBounds.setUpper(d2, d4, d6);
/*   543 */       this.geoBounds.setLower(d1, d3, d5);
/*   544 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(int paramInt, FloatBufferWrapper paramFloatBufferWrapper) {
/*   558 */     synchronized (this.geoBounds) {
/*       */       
/*   560 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*       */       
/*   564 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*       */       
/*   568 */       int i = paramInt * this.stride + this.coordinateOffset;
/*       */       
/*   570 */       double d2 = paramFloatBufferWrapper.get(i), d1 = d2;
/*   571 */       double d4 = paramFloatBufferWrapper.get(i + 1), d3 = d4;
/*   572 */       double d6 = paramFloatBufferWrapper.get(i + 2), d5 = d6;
/*   573 */       i += this.stride;
/*   574 */       for (byte b = 1; b < this.validVertexCount; b++) {
/*   575 */         if (paramFloatBufferWrapper.get(i) > d2)
/*   576 */           d2 = paramFloatBufferWrapper.get(i); 
/*   577 */         if (paramFloatBufferWrapper.get(i) < d1) {
/*   578 */           d1 = paramFloatBufferWrapper.get(i);
/*       */         }
/*   580 */         if (paramFloatBufferWrapper.get(i + 1) > d4)
/*   581 */           d4 = paramFloatBufferWrapper.get(i + 1); 
/*   582 */         if (paramFloatBufferWrapper.get(i + 1) < d3) {
/*   583 */           d3 = paramFloatBufferWrapper.get(i + 1);
/*       */         }
/*   585 */         if (paramFloatBufferWrapper.get(i + 2) > d6)
/*   586 */           d6 = paramFloatBufferWrapper.get(i + 2); 
/*   587 */         if (paramFloatBufferWrapper.get(i + 2) < d5) {
/*   588 */           d5 = paramFloatBufferWrapper.get(i + 2);
/*       */         }
/*   590 */         i += this.stride;
/*       */       } 
/*       */       
/*   593 */       this.geoBounds.setUpper(d2, d4, d6);
/*   594 */       this.geoBounds.setLower(d1, d3, d5);
/*   595 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(DoubleBufferWrapper paramDoubleBufferWrapper) {
/*   605 */     synchronized (this.geoBounds) {
/*       */       
/*   607 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*       */       
/*   611 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*   614 */       int j = this.initialCoordIndex;
/*   615 */       int k = 3 * this.validVertexCount;
/*       */ 
/*       */       
/*   618 */       double d2 = paramDoubleBufferWrapper.get(j++), d1 = d2;
/*   619 */       double d4 = paramDoubleBufferWrapper.get(j++), d3 = d4;
/*   620 */       double d6 = paramDoubleBufferWrapper.get(j++), d5 = d6;
/*       */       
/*   622 */       for (int i = j; i < k; i += 3) {
/*   623 */         int m = i + 1;
/*   624 */         int n = i + 2;
/*       */         
/*   626 */         if (paramDoubleBufferWrapper.get(i) > d2)
/*   627 */           d2 = paramDoubleBufferWrapper.get(i); 
/*   628 */         if (paramDoubleBufferWrapper.get(i) < d1) {
/*   629 */           d1 = paramDoubleBufferWrapper.get(i);
/*       */         }
/*   631 */         if (paramDoubleBufferWrapper.get(m) > d4)
/*   632 */           d4 = paramDoubleBufferWrapper.get(m); 
/*   633 */         if (paramDoubleBufferWrapper.get(m) < d3) {
/*   634 */           d3 = paramDoubleBufferWrapper.get(m);
/*       */         }
/*   636 */         if (paramDoubleBufferWrapper.get(n) > d6)
/*   637 */           d6 = paramDoubleBufferWrapper.get(n); 
/*   638 */         if (paramDoubleBufferWrapper.get(n) < d5) {
/*   639 */           d5 = paramDoubleBufferWrapper.get(n);
/*       */         }
/*       */       } 
/*   642 */       this.geoBounds.setUpper(d2, d4, d6);
/*   643 */       this.geoBounds.setLower(d1, d3, d5);
/*   644 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(FloatBufferWrapper paramFloatBufferWrapper) {
/*   654 */     synchronized (this.geoBounds) {
/*       */       
/*   656 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*       */       
/*   660 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*       */       
/*   664 */       int j = this.initialCoordIndex;
/*   665 */       int k = 3 * this.validVertexCount;
/*       */ 
/*       */       
/*   668 */       double d2 = paramFloatBufferWrapper.get(j++), d1 = d2;
/*   669 */       double d4 = paramFloatBufferWrapper.get(j++), d3 = d4;
/*   670 */       double d6 = paramFloatBufferWrapper.get(j++), d5 = d6;
/*       */       
/*   672 */       for (int i = j; i < k; i += 3) {
/*   673 */         int m = i + 1;
/*   674 */         int n = i + 2;
/*       */         
/*   676 */         if (paramFloatBufferWrapper.get(i) > d2)
/*   677 */           d2 = paramFloatBufferWrapper.get(i); 
/*   678 */         if (paramFloatBufferWrapper.get(i) < d1) {
/*   679 */           d1 = paramFloatBufferWrapper.get(i);
/*       */         }
/*   681 */         if (paramFloatBufferWrapper.get(m) > d4)
/*   682 */           d4 = paramFloatBufferWrapper.get(m); 
/*   683 */         if (paramFloatBufferWrapper.get(m) < d3) {
/*   684 */           d3 = paramFloatBufferWrapper.get(m);
/*       */         }
/*   686 */         if (paramFloatBufferWrapper.get(n) > d6)
/*   687 */           d6 = paramFloatBufferWrapper.get(n); 
/*   688 */         if (paramFloatBufferWrapper.get(n) < d5) {
/*   689 */           d5 = paramFloatBufferWrapper.get(n);
/*       */         }
/*       */       } 
/*   692 */       this.geoBounds.setUpper(d2, d4, d6);
/*   693 */       this.geoBounds.setLower(d1, d3, d5);
/*   694 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(float[] paramArrayOfFloat) {
/*   703 */     synchronized (this.geoBounds) {
/*       */       
/*   705 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*       */       
/*   709 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*   712 */       int j = this.initialCoordIndex;
/*   713 */       int k = 3 * this.validVertexCount;
/*       */ 
/*       */       
/*   716 */       double d2 = paramArrayOfFloat[j++], d1 = d2;
/*   717 */       double d4 = paramArrayOfFloat[j++], d3 = d4;
/*   718 */       double d6 = paramArrayOfFloat[j++], d5 = d6;
/*       */       
/*   720 */       for (int i = j; i < k; i += 3) {
/*   721 */         int m = i + 1;
/*   722 */         int n = i + 2;
/*       */         
/*   724 */         if (paramArrayOfFloat[i] > d2)
/*   725 */           d2 = paramArrayOfFloat[i]; 
/*   726 */         if (paramArrayOfFloat[i] < d1) {
/*   727 */           d1 = paramArrayOfFloat[i];
/*       */         }
/*   729 */         if (paramArrayOfFloat[m] > d4)
/*   730 */           d4 = paramArrayOfFloat[m]; 
/*   731 */         if (paramArrayOfFloat[m] < d3) {
/*   732 */           d3 = paramArrayOfFloat[m];
/*       */         }
/*   734 */         if (paramArrayOfFloat[n] > d6)
/*   735 */           d6 = paramArrayOfFloat[n]; 
/*   736 */         if (paramArrayOfFloat[n] < d5) {
/*   737 */           d5 = paramArrayOfFloat[n];
/*       */         }
/*       */       } 
/*   740 */       this.geoBounds.setUpper(d2, d4, d6);
/*       */       
/*   742 */       this.geoBounds.setLower(d1, d3, d5);
/*       */ 
/*       */       
/*   745 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(double[] paramArrayOfDouble) {
/*   754 */     synchronized (this.geoBounds) {
/*       */       
/*   756 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*       */       
/*   760 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*       */       
/*   764 */       int j = this.initialCoordIndex;
/*   765 */       int k = 3 * this.validVertexCount;
/*       */ 
/*       */       
/*   768 */       double d2 = paramArrayOfDouble[j++], d1 = d2;
/*   769 */       double d4 = paramArrayOfDouble[j++], d3 = d4;
/*   770 */       double d6 = paramArrayOfDouble[j++], d5 = d6;
/*       */       
/*   772 */       for (int i = j; i < k; i += 3) {
/*   773 */         int m = i + 1;
/*   774 */         int n = i + 2;
/*       */         
/*   776 */         if (paramArrayOfDouble[i] > d2)
/*   777 */           d2 = paramArrayOfDouble[i]; 
/*   778 */         if (paramArrayOfDouble[i] < d1) {
/*   779 */           d1 = paramArrayOfDouble[i];
/*       */         }
/*   781 */         if (paramArrayOfDouble[m] > d4)
/*   782 */           d4 = paramArrayOfDouble[m]; 
/*   783 */         if (paramArrayOfDouble[m] < d3) {
/*   784 */           d3 = paramArrayOfDouble[m];
/*       */         }
/*   786 */         if (paramArrayOfDouble[n] > d6)
/*   787 */           d6 = paramArrayOfDouble[n]; 
/*   788 */         if (paramArrayOfDouble[n] < d5) {
/*   789 */           d5 = paramArrayOfDouble[n];
/*       */         }
/*       */       } 
/*   792 */       this.geoBounds.setUpper(d2, d4, d6);
/*   793 */       this.geoBounds.setLower(d1, d3, d5);
/*   794 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(Point3f[] paramArrayOfPoint3f) {
/*   804 */     synchronized (this.geoBounds) {
/*       */       
/*   806 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*       */       
/*   810 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*   816 */       double d2 = (paramArrayOfPoint3f[this.initialCoordIndex]).x, d1 = d2;
/*   817 */       double d4 = (paramArrayOfPoint3f[this.initialCoordIndex]).y, d3 = d4;
/*   818 */       double d6 = (paramArrayOfPoint3f[this.initialCoordIndex]).z, d5 = d6;
/*       */       
/*   820 */       for (int i = this.initialCoordIndex + 1; i < this.validVertexCount; i++) {
/*   821 */         Point3f point3f = paramArrayOfPoint3f[i];
/*   822 */         if (point3f.x > d2) d2 = point3f.x; 
/*   823 */         if (point3f.x < d1) d1 = point3f.x;
/*       */         
/*   825 */         if (point3f.y > d4) d4 = point3f.y; 
/*   826 */         if (point3f.y < d3) d3 = point3f.y;
/*       */         
/*   828 */         if (point3f.z > d6) d6 = point3f.z; 
/*   829 */         if (point3f.z < d5) d5 = point3f.z;
/*       */       
/*       */       } 
/*   832 */       this.geoBounds.setUpper(d2, d4, d6);
/*   833 */       this.geoBounds.setLower(d1, d3, d5);
/*   834 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeBoundingBox(Point3d[] paramArrayOfPoint3d) {
/*   844 */     synchronized (this.geoBounds) {
/*       */       
/*   846 */       if (this.computeGeoBounds == 0 && this.refCount > 0) {
/*       */         return;
/*       */       }
/*       */       
/*   850 */       if (!this.boundsDirty) {
/*       */         return;
/*       */       }
/*       */ 
/*       */       
/*   855 */       double d2 = (paramArrayOfPoint3d[this.initialCoordIndex]).x, d1 = d2;
/*   856 */       double d4 = (paramArrayOfPoint3d[this.initialCoordIndex]).y, d3 = d4;
/*   857 */       double d6 = (paramArrayOfPoint3d[this.initialCoordIndex]).z, d5 = d6;
/*       */       
/*   859 */       for (int i = this.initialCoordIndex + 1; i < this.validVertexCount; i++) {
/*   860 */         Point3d point3d = paramArrayOfPoint3d[i];
/*   861 */         if (point3d.x > d2) d2 = point3d.x; 
/*   862 */         if (point3d.x < d1) d1 = point3d.x;
/*       */         
/*   864 */         if (point3d.y > d4) d4 = point3d.y; 
/*   865 */         if (point3d.y < d3) d3 = point3d.y;
/*       */         
/*   867 */         if (point3d.z > d6) d6 = point3d.z; 
/*   868 */         if (point3d.z < d5) d5 = point3d.z;
/*       */       
/*       */       } 
/*   871 */       this.geoBounds.setUpper(d2, d4, d6);
/*   872 */       this.geoBounds.setLower(d1, d3, d5);
/*   873 */       this.boundsDirty = false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   void update() {}
/*       */ 
/*       */   
/*       */   void setupMirrorVertexPointer(int paramInt) {
/*       */     int j;
/*       */     int i;
/*   885 */     switch (paramInt) {
/*       */       case 1:
/*   887 */         if (this.floatRefCoords == null) {
/*   888 */           if ((this.vertexType & 0xF) == 1) {
/*   889 */             this.vertexType &= 0xFFFFFFFE;
/*   890 */             this.mirrorFloatRefCoords = null;
/*   891 */             this.mirrorVertexAllocated &= 0xFFFFFFFE;
/*       */           } 
/*       */           break;
/*       */         } 
/*   895 */         this.vertexType |= 0x1;
/*   896 */         this.mirrorFloatRefCoords = this.floatRefCoords;
/*   897 */         this.mirrorVertexAllocated &= 0xFFFFFFFE;
/*       */         break;
/*       */ 
/*       */       
/*       */       case 2:
/*   902 */         if (this.doubleRefCoords == null) {
/*   903 */           if ((this.vertexType & 0xF) == 2) {
/*   904 */             this.mirrorDoubleRefCoords = null;
/*   905 */             this.mirrorVertexAllocated &= 0xFFFFFFFD;
/*   906 */             this.vertexType &= 0xFFFFFFFD;
/*       */           } 
/*   908 */           this.vertexType &= 0xFFFFFFFD;
/*       */           break;
/*       */         } 
/*   911 */         this.vertexType |= 0x2;
/*   912 */         this.mirrorDoubleRefCoords = this.doubleRefCoords;
/*   913 */         this.mirrorVertexAllocated &= 0xFFFFFFFD;
/*       */         break;
/*       */ 
/*       */       
/*       */       case 4:
/*   918 */         if (this.p3fRefCoords == null) {
/*   919 */           this.vertexType &= 0xFFFFFFFB;
/*       */ 
/*       */           
/*       */           break;
/*       */         } 
/*       */         
/*   925 */         this.vertexType |= 0x4;
/*       */         
/*   927 */         if ((this.mirrorVertexAllocated & true) == 0) {
/*   928 */           this.mirrorFloatRefCoords = new float[this.vertexCount * 3];
/*   929 */           this.mirrorVertexAllocated |= 0x1;
/*       */         } 
/*       */         
/*   932 */         j = this.initialCoordIndex * 3;
/*   933 */         for (i = this.initialCoordIndex; i < this.validVertexCount; i++) {
/*   934 */           this.mirrorFloatRefCoords[j++] = (this.p3fRefCoords[i]).x;
/*   935 */           this.mirrorFloatRefCoords[j++] = (this.p3fRefCoords[i]).y;
/*   936 */           this.mirrorFloatRefCoords[j++] = (this.p3fRefCoords[i]).z;
/*       */         } 
/*       */         break;
/*       */       
/*       */       case 8:
/*   941 */         if (this.p3dRefCoords == null) {
/*   942 */           this.vertexType &= 0xFFFFFFF7;
/*       */ 
/*       */           
/*       */           break;
/*       */         } 
/*       */         
/*   948 */         this.vertexType |= 0x8;
/*       */         
/*   950 */         if ((this.mirrorVertexAllocated & 0x2) == 0) {
/*   951 */           this.mirrorDoubleRefCoords = new double[this.vertexCount * 3];
/*   952 */           this.mirrorVertexAllocated |= 0x2;
/*       */         } 
/*       */         
/*   955 */         j = this.initialCoordIndex * 3;
/*   956 */         for (i = this.initialCoordIndex; i < this.validVertexCount; i++) {
/*   957 */           this.mirrorDoubleRefCoords[j++] = (this.p3dRefCoords[i]).x;
/*   958 */           this.mirrorDoubleRefCoords[j++] = (this.p3dRefCoords[i]).y;
/*   959 */           this.mirrorDoubleRefCoords[j++] = (this.p3dRefCoords[i]).z;
/*       */         } 
/*       */         break;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setupMirrorInterleavedColorPointer(boolean paramBoolean) {
/*   975 */     if (paramBoolean || this.c4fAllocated != 0) {
/*       */       
/*   977 */       int j = 4 * this.vertexCount;
/*       */       
/*   979 */       if (this.mirrorInterleavedColorPointer == null) {
/*   980 */         this.mirrorInterleavedColorPointer = new float[1][j];
/*       */       }
/*       */       
/*   983 */       int i = 4 * this.initialVertexIndex;
/*   984 */       int k = this.stride * this.initialVertexIndex + this.colorOffset;
/*       */       
/*   986 */       if ((this.vertexFormat & 0x800) == 0 && this.interLeavedVertexData != null) {
/*       */         
/*   988 */         if ((this.vertexFormat & 0x8) != 0) {
/*       */           
/*   990 */           for (int m = this.initialVertexIndex; m < this.validVertexCount; m++) {
/*   991 */             this.mirrorInterleavedColorPointer[0][i++] = this.interLeavedVertexData[k];
/*       */             
/*   993 */             this.mirrorInterleavedColorPointer[0][i++] = this.interLeavedVertexData[k + 1];
/*       */             
/*   995 */             this.mirrorInterleavedColorPointer[0][i++] = this.interLeavedVertexData[k + 2];
/*       */             
/*   997 */             this.mirrorInterleavedColorPointer[0][i++] = this.interLeavedVertexData[k + 3];
/*       */             
/*   999 */             k += this.stride;
/*       */           } 
/*       */         } else {
/*       */           
/*  1003 */           for (int m = this.initialVertexIndex; m < this.validVertexCount; m++) {
/*  1004 */             this.mirrorInterleavedColorPointer[0][i++] = this.interLeavedVertexData[k];
/*       */             
/*  1006 */             this.mirrorInterleavedColorPointer[0][i++] = this.interLeavedVertexData[k + 1];
/*       */             
/*  1008 */             this.mirrorInterleavedColorPointer[0][i++] = this.interLeavedVertexData[k + 2];
/*       */             
/*  1010 */             this.mirrorInterleavedColorPointer[0][i++] = 1.0F;
/*  1011 */             k += this.stride;
/*       */           }
/*       */         
/*       */         }
/*       */       
/*  1016 */       } else if ((this.vertexFormat & 0x8) != 0 && this.interleavedFloatBufferImpl != null) {
/*       */         
/*  1018 */         for (int m = this.initialVertexIndex; m < this.validVertexCount; m++) {
/*  1019 */           this.interleavedFloatBufferImpl.position(k);
/*  1020 */           this.interleavedFloatBufferImpl.get(this.mirrorInterleavedColorPointer[0], i, 4);
/*       */           
/*  1022 */           i += 4;
/*  1023 */           k += this.stride;
/*       */         } 
/*       */       } else {
/*       */         
/*  1027 */         for (int m = this.initialVertexIndex; m < this.validVertexCount; m++) {
/*  1028 */           this.interleavedFloatBufferImpl.position(k);
/*  1029 */           this.interleavedFloatBufferImpl.get(this.mirrorInterleavedColorPointer[0], i, 3);
/*       */           
/*  1031 */           this.mirrorInterleavedColorPointer[0][i + 3] = 1.0F;
/*  1032 */           i += 4;
/*  1033 */           k += this.stride;
/*       */         } 
/*       */       } 
/*       */ 
/*       */       
/*  1038 */       this.c4fAllocated = 8;
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   void setupMirrorColorPointer(int paramInt, boolean paramBoolean) {
/*  1044 */     int k, i = 0, j = 0;
/*       */ 
/*       */     
/*  1047 */     if (this.c4fAllocated == 0 && !paramBoolean) {
/*  1048 */       k = 3;
/*       */ 
/*       */     
/*       */     }
/*       */     else {
/*       */ 
/*       */       
/*  1055 */       if (paramBoolean && this.c4fAllocated == 0 && (this.vertexFormat & 0x8) == 0)
/*       */       {
/*  1057 */         this.mirrorColorAllocated = 0;
/*       */       }
/*  1059 */       this.c4fAllocated = 8;
/*  1060 */       k = 4;
/*       */     } 
/*       */     
/*  1063 */     if ((this.vertexFormat & 0x800) == 0) {
/*  1064 */       int m; switch (paramInt) {
/*       */         case 16:
/*  1066 */           if (this.floatRefColors == null) {
/*  1067 */             if (this.c4fAllocated == 0 && !paramBoolean && (this.vertexType & 0x3F0) == 16) {
/*       */               
/*  1069 */               this.mirrorFloatRefColors[0] = null;
/*  1070 */               this.mirrorColorAllocated &= 0xFFFFFFEF;
/*       */             } 
/*  1072 */             this.vertexType &= 0xFFFFFFEF;
/*       */             
/*       */             return;
/*       */           } 
/*  1076 */           this.vertexType |= 0x10;
/*  1077 */           if (this.c4fAllocated == 0 && !paramBoolean) {
/*  1078 */             this.mirrorFloatRefColors[0] = this.floatRefColors;
/*  1079 */             this.mirrorColorAllocated &= 0xFFFFFFEF;
/*       */             break;
/*       */           } 
/*  1082 */           if ((this.mirrorColorAllocated & 0x10) == 0) {
/*  1083 */             this.mirrorFloatRefColors[0] = new float[4 * this.vertexCount];
/*  1084 */             this.mirrorColorAllocated |= 0x10;
/*       */           } 
/*       */           
/*  1087 */           if ((this.vertexFormat & 0x8) == 0) {
/*       */             
/*  1089 */             i = this.initialColorIndex * 3;
/*  1090 */             j = this.initialColorIndex * 4;
/*       */             
/*  1092 */             for (int n = this.initialColorIndex; n < this.validVertexCount; n++) {
/*  1093 */               this.mirrorFloatRefColors[0][j++] = this.floatRefColors[i++];
/*       */               
/*  1095 */               this.mirrorFloatRefColors[0][j++] = this.floatRefColors[i++];
/*       */               
/*  1097 */               this.mirrorFloatRefColors[0][j++] = this.floatRefColors[i++];
/*       */               
/*  1099 */               this.mirrorFloatRefColors[0][j++] = 1.0F;
/*       */             } 
/*       */             
/*       */             break;
/*       */           } 
/*  1104 */           i = this.initialColorIndex * 4;
/*  1105 */           System.arraycopy(this.floatRefColors, i, this.mirrorFloatRefColors[0], i, 4 * this.validVertexCount);
/*       */           break;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         case 32:
/*  1112 */           if (this.byteRefColors == null) {
/*  1113 */             if (this.c4fAllocated == 0 && !paramBoolean && (this.vertexType & 0x3F0) == 32) {
/*       */               
/*  1115 */               this.mirrorUnsignedByteRefColors[0] = null;
/*  1116 */               this.mirrorColorAllocated &= 0xFFFFFFDF;
/*       */             } 
/*  1118 */             this.vertexType &= 0xFFFFFFDF;
/*       */             return;
/*       */           } 
/*  1121 */           this.vertexType |= 0x20;
/*  1122 */           if (this.c4fAllocated == 0 && !paramBoolean) {
/*  1123 */             this.mirrorUnsignedByteRefColors[0] = this.byteRefColors;
/*  1124 */             this.mirrorColorAllocated &= 0xFFFFFFDF;
/*       */             break;
/*       */           } 
/*  1127 */           if ((this.mirrorColorAllocated & 0x20) == 0) {
/*  1128 */             this.mirrorUnsignedByteRefColors[0] = new byte[4 * this.vertexCount];
/*  1129 */             this.mirrorColorAllocated |= 0x20;
/*       */           } 
/*  1131 */           if ((this.vertexFormat & 0x8) == 0) {
/*       */             
/*  1133 */             i = this.initialColorIndex * 3;
/*  1134 */             j = this.initialColorIndex * 4;
/*       */             
/*  1136 */             for (int n = this.initialColorIndex; n < this.validVertexCount; n++) {
/*  1137 */               this.mirrorUnsignedByteRefColors[0][j++] = this.byteRefColors[i++];
/*       */               
/*  1139 */               this.mirrorUnsignedByteRefColors[0][j++] = this.byteRefColors[i++];
/*       */               
/*  1141 */               this.mirrorUnsignedByteRefColors[0][j++] = this.byteRefColors[i++];
/*       */               
/*  1143 */               this.mirrorUnsignedByteRefColors[0][j++] = -1;
/*       */             } 
/*       */             
/*       */             break;
/*       */           } 
/*  1148 */           i = this.initialColorIndex * 4;
/*  1149 */           System.arraycopy(this.byteRefColors, i, this.mirrorUnsignedByteRefColors[0], i, 4 * this.validVertexCount);
/*       */           break;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         case 64:
/*  1157 */           if (this.c3fRefColors == null) {
/*  1158 */             this.vertexType &= 0xFFFFFFBF;
/*       */             return;
/*       */           } 
/*  1161 */           this.vertexType |= 0x40;
/*       */           
/*  1163 */           if ((this.mirrorColorAllocated & 0x10) == 0) {
/*  1164 */             this.mirrorFloatRefColors[0] = new float[this.vertexCount * k];
/*  1165 */             this.mirrorColorAllocated |= 0x10;
/*       */           } 
/*  1167 */           if ((this.c4fAllocated & 0x8) == 0) {
/*       */             
/*  1169 */             j = this.initialColorIndex * 3;
/*  1170 */             for (int n = this.initialColorIndex; n < this.validVertexCount; n++) {
/*  1171 */               this.mirrorFloatRefColors[0][j++] = (this.c3fRefColors[n]).x;
/*  1172 */               this.mirrorFloatRefColors[0][j++] = (this.c3fRefColors[n]).y;
/*  1173 */               this.mirrorFloatRefColors[0][j++] = (this.c3fRefColors[n]).z;
/*       */             } 
/*       */             break;
/*       */           } 
/*  1177 */           j = this.initialColorIndex * 4;
/*  1178 */           for (m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1179 */             this.mirrorFloatRefColors[0][j++] = (this.c3fRefColors[m]).x;
/*  1180 */             this.mirrorFloatRefColors[0][j++] = (this.c3fRefColors[m]).y;
/*  1181 */             this.mirrorFloatRefColors[0][j++] = (this.c3fRefColors[m]).z;
/*  1182 */             this.mirrorFloatRefColors[0][j++] = 1.0F;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 128:
/*  1188 */           if (this.c4fRefColors == null) {
/*  1189 */             this.vertexType &= 0xFFFFFF7F;
/*       */             return;
/*       */           } 
/*  1192 */           this.vertexType |= 0x80;
/*       */           
/*  1194 */           if ((this.mirrorColorAllocated & 0x10) == 0) {
/*  1195 */             this.mirrorFloatRefColors[0] = new float[this.vertexCount << 2];
/*  1196 */             this.mirrorColorAllocated |= 0x10;
/*       */           } 
/*       */           
/*  1199 */           j = this.initialColorIndex * 4;
/*  1200 */           for (m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1201 */             this.mirrorFloatRefColors[0][j++] = (this.c4fRefColors[m]).x;
/*  1202 */             this.mirrorFloatRefColors[0][j++] = (this.c4fRefColors[m]).y;
/*  1203 */             this.mirrorFloatRefColors[0][j++] = (this.c4fRefColors[m]).z;
/*  1204 */             this.mirrorFloatRefColors[0][j++] = (this.c4fRefColors[m]).w;
/*       */           } 
/*       */           break;
/*       */         case 256:
/*  1208 */           if (this.c3bRefColors == null) {
/*  1209 */             this.vertexType &= 0xFFFFFEFF;
/*       */             return;
/*       */           } 
/*  1212 */           this.vertexType |= 0x100;
/*       */           
/*  1214 */           if ((this.mirrorColorAllocated & 0x20) == 0) {
/*  1215 */             this.mirrorUnsignedByteRefColors[0] = new byte[this.vertexCount * k];
/*       */             
/*  1217 */             this.mirrorColorAllocated |= 0x20;
/*       */           } 
/*  1219 */           if ((this.c4fAllocated & 0x8) == 0) {
/*  1220 */             j = this.initialColorIndex * 3;
/*  1221 */             for (m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1222 */               this.mirrorUnsignedByteRefColors[0][j++] = (this.c3bRefColors[m]).x;
/*  1223 */               this.mirrorUnsignedByteRefColors[0][j++] = (this.c3bRefColors[m]).y;
/*  1224 */               this.mirrorUnsignedByteRefColors[0][j++] = (this.c3bRefColors[m]).z;
/*       */             }  break;
/*       */           } 
/*  1227 */           j = this.initialColorIndex * 4;
/*  1228 */           for (m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1229 */             this.mirrorUnsignedByteRefColors[0][j++] = (this.c3bRefColors[m]).x;
/*  1230 */             this.mirrorUnsignedByteRefColors[0][j++] = (this.c3bRefColors[m]).y;
/*  1231 */             this.mirrorUnsignedByteRefColors[0][j++] = (this.c3bRefColors[m]).z;
/*  1232 */             this.mirrorUnsignedByteRefColors[0][j++] = -1;
/*       */           } 
/*       */           break;
/*       */         
/*       */         case 512:
/*  1237 */           if (this.c4bRefColors == null) {
/*  1238 */             this.vertexType &= 0xFFFFFDFF;
/*       */             return;
/*       */           } 
/*  1241 */           this.vertexType |= 0x200;
/*  1242 */           if ((this.mirrorColorAllocated & 0x20) == 0) {
/*  1243 */             this.mirrorUnsignedByteRefColors[0] = new byte[this.vertexCount << 2];
/*  1244 */             this.mirrorColorAllocated |= 0x20;
/*       */           } 
/*       */           
/*  1247 */           j = this.initialColorIndex * 4;
/*  1248 */           for (m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1249 */             this.mirrorUnsignedByteRefColors[0][j++] = (this.c4bRefColors[m]).x;
/*  1250 */             this.mirrorUnsignedByteRefColors[0][j++] = (this.c4bRefColors[m]).y;
/*  1251 */             this.mirrorUnsignedByteRefColors[0][j++] = (this.c4bRefColors[m]).z;
/*  1252 */             this.mirrorUnsignedByteRefColors[0][j++] = (this.c4bRefColors[m]).w;
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */     
/*       */     } else {
/*  1260 */       if (this.colorRefBuffer == null) {
/*  1261 */         if (this.c4fAllocated == 0 && !paramBoolean && (this.vertexType & 0x3F0) == 16) {
/*       */           
/*  1263 */           this.mirrorFloatRefColors[0] = null;
/*  1264 */           this.mirrorColorAllocated &= 0xFFFFFFEF;
/*       */         } 
/*  1266 */         this.vertexType &= 0xFFFFFFEF;
/*       */         
/*  1268 */         if (this.c4fAllocated == 0 && !paramBoolean && (this.vertexType & 0x3F0) == 32) {
/*       */           
/*  1270 */           this.mirrorUnsignedByteRefColors[0] = null;
/*  1271 */           this.mirrorColorAllocated &= 0xFFFFFFDF;
/*       */         } 
/*  1273 */         this.vertexType &= 0xFFFFFFDF;
/*       */         return;
/*       */       } 
/*  1276 */       if (this.floatBufferRefColors != null) {
/*  1277 */         this.vertexType |= 0x10;
/*  1278 */         this.vertexType &= 0xFFFFFFDF;
/*  1279 */         if (this.c4fAllocated == 0 && !paramBoolean) {
/*       */           
/*  1281 */           this.mirrorFloatRefColors[0] = null;
/*  1282 */           this.mirrorColorAllocated &= 0xFFFFFFEF;
/*       */         } else {
/*       */           
/*  1285 */           if ((this.mirrorColorAllocated & 0x10) == 0) {
/*  1286 */             this.mirrorFloatRefColors[0] = new float[4 * this.vertexCount];
/*  1287 */             this.mirrorColorAllocated |= 0x10;
/*       */           } 
/*  1289 */           this.floatBufferRefColors.rewind();
/*  1290 */           if ((this.vertexFormat & 0x8) == 0) {
/*  1291 */             i = this.initialColorIndex * 3;
/*  1292 */             j = this.initialColorIndex * 4;
/*  1293 */             this.floatBufferRefColors.position(i);
/*       */             
/*  1295 */             for (int m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1296 */               this.floatBufferRefColors.get(this.mirrorFloatRefColors[0], j, 3);
/*  1297 */               this.mirrorFloatRefColors[0][j + 3] = 1.0F;
/*  1298 */               j += 4;
/*       */             }
/*       */           
/*       */           } else {
/*       */             
/*  1303 */             i = this.initialColorIndex * 4;
/*  1304 */             j = this.initialColorIndex * 4;
/*  1305 */             this.floatBufferRefColors.position(i);
/*  1306 */             for (int m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1307 */               this.floatBufferRefColors.get(this.mirrorFloatRefColors[0], j, 4);
/*  1308 */               j += 4;
/*       */             } 
/*       */           } 
/*       */         } 
/*  1312 */       } else if (this.byteBufferRefColors != null) {
/*  1313 */         this.vertexType |= 0x20;
/*  1314 */         this.vertexType &= 0xFFFFFFEF;
/*  1315 */         if (this.c4fAllocated == 0 && !paramBoolean) {
/*       */           
/*  1317 */           this.mirrorUnsignedByteRefColors[0] = null;
/*  1318 */           this.mirrorColorAllocated &= 0xFFFFFFDF;
/*       */         } else {
/*       */           
/*  1321 */           if ((this.mirrorColorAllocated & 0x20) == 0) {
/*  1322 */             this.mirrorUnsignedByteRefColors[0] = new byte[4 * this.vertexCount];
/*  1323 */             this.mirrorColorAllocated |= 0x20;
/*       */           } 
/*       */           
/*  1326 */           this.byteBufferRefColors.rewind();
/*  1327 */           if ((this.vertexFormat & 0x8) == 0) {
/*  1328 */             i = this.initialColorIndex * 3;
/*  1329 */             j = this.initialColorIndex * 4;
/*  1330 */             this.byteBufferRefColors.position(i);
/*  1331 */             for (int m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1332 */               this.byteBufferRefColors.get(this.mirrorUnsignedByteRefColors[0], j, 3);
/*       */               
/*  1334 */               this.mirrorUnsignedByteRefColors[0][j + 3] = -1;
/*  1335 */               j += 4;
/*       */             } 
/*       */           } else {
/*       */             
/*  1339 */             i = this.initialColorIndex * 4;
/*  1340 */             j = this.initialColorIndex * 4;
/*  1341 */             this.byteBufferRefColors.position(i);
/*  1342 */             for (int m = this.initialColorIndex; m < this.validVertexCount; m++) {
/*  1343 */               this.byteBufferRefColors.get(this.mirrorUnsignedByteRefColors[0], j, 4);
/*  1344 */               j += 4;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/*  1351 */     this.colorChanged = 65535;
/*       */   }
/*       */ 
/*       */   
/*       */   void setupMirrorNormalPointer(int paramInt) {
/*       */     int j;
/*       */     int i;
/*  1358 */     switch (paramInt) {
/*       */       case 1024:
/*  1360 */         if (this.floatRefNormals == null) {
/*  1361 */           if ((this.vertexType & 0xC00) == 1024) {
/*  1362 */             this.vertexType &= 0xFFFFFBFF;
/*  1363 */             this.mirrorFloatRefNormals = null;
/*  1364 */             this.mirrorNormalAllocated = false;
/*       */           } 
/*       */           break;
/*       */         } 
/*  1368 */         this.vertexType |= 0x400;
/*  1369 */         this.mirrorFloatRefNormals = this.floatRefNormals;
/*  1370 */         this.mirrorNormalAllocated = false;
/*       */         break;
/*       */       
/*       */       case 2048:
/*  1374 */         if (this.v3fRefNormals == null) {
/*  1375 */           if ((this.vertexType & 0xC00) == 2048) {
/*  1376 */             this.vertexType &= 0xFFFFF7FF;
/*       */           }
/*       */           
/*       */           return;
/*       */         } 
/*  1381 */         this.vertexType |= 0x800;
/*       */         
/*  1383 */         if (!this.mirrorNormalAllocated) {
/*  1384 */           this.mirrorFloatRefNormals = new float[this.vertexCount * 3];
/*  1385 */           this.mirrorNormalAllocated = true;
/*       */         } 
/*       */         
/*  1388 */         j = this.initialNormalIndex * 3;
/*  1389 */         for (i = this.initialNormalIndex; i < this.validVertexCount; i++) {
/*  1390 */           this.mirrorFloatRefNormals[j++] = (this.v3fRefNormals[i]).x;
/*  1391 */           this.mirrorFloatRefNormals[j++] = (this.v3fRefNormals[i]).y;
/*  1392 */           this.mirrorFloatRefNormals[j++] = (this.v3fRefNormals[i]).z;
/*       */         } 
/*       */         break;
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   void setupMirrorTexCoordPointer(int paramInt) {
/*  1400 */     for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  1401 */       doSetupMirrorTexCoordPointer(b, paramInt);
/*       */     }
/*       */     
/*  1404 */     validateTexCoordPointerType();
/*       */   }
/*       */   
/*       */   void setupMirrorTexCoordPointer(int paramInt1, int paramInt2) {
/*  1408 */     doSetupMirrorTexCoordPointer(paramInt1, paramInt2);
/*  1409 */     validateTexCoordPointerType();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   private void validateTexCoordPointerType() {
/*  1415 */     boolean bool1 = true;
/*  1416 */     boolean bool2 = true;
/*  1417 */     for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  1418 */       if (this.refTexCoords[b] == null) {
/*  1419 */         bool1 = false;
/*       */       } else {
/*  1421 */         bool2 = false;
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  1426 */     if (bool2) {
/*  1427 */       this.texCoordType = 0;
/*       */     }
/*       */ 
/*       */     
/*  1431 */     this.vertexType &= 0xFFFF8FFF;
/*  1432 */     if (bool1) {
/*  1433 */       this.vertexType |= this.texCoordType;
/*       */     }
/*       */   }
/*       */   
/*       */   private void doSetupMirrorTexCoordPointer(int paramInt1, int paramInt2) {
/*       */     int j;
/*       */     int i;
/*  1440 */     switch (paramInt2) {
/*       */       case 4096:
/*  1442 */         this.texCoordType = 4096;
/*  1443 */         this.mirrorRefTexCoords[paramInt1] = this.refTexCoords[paramInt1];
/*       */         break;
/*       */       
/*       */       case 8192:
/*  1447 */         this.texCoordType = 8192;
/*  1448 */         this.t2fRefTexCoords = (TexCoord2f[])this.refTexCoords[paramInt1];
/*       */         
/*  1450 */         if (this.t2fRefTexCoords == null) {
/*  1451 */           this.mirrorRefTexCoords[paramInt1] = null;
/*       */           
/*       */           break;
/*       */         } 
/*  1455 */         this.mirrorFloatRefTexCoords = (float[])this.mirrorRefTexCoords[paramInt1];
/*  1456 */         if (this.mirrorFloatRefTexCoords != null) {
/*  1457 */           if (this.mirrorFloatRefTexCoords.length < this.vertexCount * 2) {
/*  1458 */             this.mirrorRefTexCoords[paramInt1] = this.mirrorFloatRefTexCoords = new float[this.vertexCount * 2];
/*       */           }
/*       */         } else {
/*       */           
/*  1462 */           this.mirrorRefTexCoords[paramInt1] = this.mirrorFloatRefTexCoords = new float[this.vertexCount * 2];
/*       */         } 
/*       */ 
/*       */         
/*  1466 */         j = this.initialTexCoordIndex[paramInt1] * 2;
/*  1467 */         for (i = this.initialTexCoordIndex[paramInt1]; i < this.validVertexCount; i++) {
/*  1468 */           this.mirrorFloatRefTexCoords[j++] = (this.t2fRefTexCoords[i]).x;
/*  1469 */           this.mirrorFloatRefTexCoords[j++] = (this.t2fRefTexCoords[i]).y;
/*       */         } 
/*       */         break;
/*       */       
/*       */       case 16384:
/*  1474 */         this.texCoordType = 16384;
/*  1475 */         this.t3fRefTexCoords = (TexCoord3f[])this.refTexCoords[paramInt1];
/*       */         
/*  1477 */         if (this.t3fRefTexCoords == null) {
/*  1478 */           this.mirrorRefTexCoords[paramInt1] = null;
/*       */           
/*       */           break;
/*       */         } 
/*  1482 */         this.mirrorFloatRefTexCoords = (float[])this.mirrorRefTexCoords[paramInt1];
/*  1483 */         if (this.mirrorFloatRefTexCoords != null) {
/*  1484 */           if (this.mirrorFloatRefTexCoords.length < this.vertexCount * 3) {
/*  1485 */             this.mirrorRefTexCoords[paramInt1] = this.mirrorFloatRefTexCoords = new float[this.vertexCount * 3];
/*       */           }
/*       */         } else {
/*       */           
/*  1489 */           this.mirrorRefTexCoords[paramInt1] = this.mirrorFloatRefTexCoords = new float[this.vertexCount * 3];
/*       */         } 
/*       */ 
/*       */         
/*  1493 */         j = this.initialTexCoordIndex[paramInt1] * 3;
/*  1494 */         for (i = this.initialTexCoordIndex[paramInt1]; i < this.validVertexCount; i++) {
/*  1495 */           this.mirrorFloatRefTexCoords[j++] = (this.t3fRefTexCoords[i]).x;
/*  1496 */           this.mirrorFloatRefTexCoords[j++] = (this.t3fRefTexCoords[i]).y;
/*  1497 */           this.mirrorFloatRefTexCoords[j++] = (this.t3fRefTexCoords[i]).z;
/*       */         } 
/*       */         break;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setupMirrorVertexAttrPointer(int paramInt) {
/*  1507 */     for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  1508 */       doSetupMirrorVertexAttrPointer(b, paramInt);
/*       */     }
/*       */     
/*  1511 */     validateVertexAttrPointerType();
/*       */   }
/*       */   
/*       */   void setupMirrorVertexAttrPointer(int paramInt1, int paramInt2) {
/*  1515 */     doSetupMirrorVertexAttrPointer(paramInt1, paramInt2);
/*  1516 */     validateVertexAttrPointerType();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void validateVertexAttrPointerType() {
/*  1523 */     boolean bool1 = true;
/*  1524 */     boolean bool2 = true;
/*       */     
/*  1526 */     if ((this.vertexFormat & 0x800) == 0) {
/*  1527 */       for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  1528 */         if (this.floatRefVertexAttrs[b] == null) {
/*  1529 */           bool1 = false;
/*       */         } else {
/*  1531 */           bool2 = false;
/*       */         } 
/*       */       } 
/*       */     } else {
/*  1535 */       for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  1536 */         if (this.nioFloatBufferRefVertexAttrs[b] == null) {
/*  1537 */           bool1 = false;
/*       */         } else {
/*  1539 */           bool2 = false;
/*       */         } 
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  1545 */     if (bool2) {
/*  1546 */       this.vertexAttrType = 0;
/*       */     }
/*       */ 
/*       */     
/*  1550 */     this.vertexType &= 0xFFFF7FFF;
/*  1551 */     if (bool1) {
/*  1552 */       this.vertexType |= this.vertexAttrType;
/*       */     }
/*       */   }
/*       */   
/*       */   private void doSetupMirrorVertexAttrPointer(int paramInt1, int paramInt2) {
/*  1557 */     switch (paramInt2) {
/*       */       case 32768:
/*  1559 */         this.vertexAttrType = 32768;
/*  1560 */         this.mirrorFloatRefVertexAttrs[paramInt1] = this.floatRefVertexAttrs[paramInt1];
/*       */         break;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void createGeometryArrayData(int paramInt1, int paramInt2) {
/*  1570 */     if ((paramInt2 & 0x460) != 0) {
/*  1571 */       createGeometryArrayData(paramInt1, paramInt2, 1, defaultTexCoordSetMap);
/*       */     } else {
/*       */       
/*  1574 */       createGeometryArrayData(paramInt1, paramInt2, 0, null);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  1581 */   void createGeometryArrayData(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) { createGeometryArrayData(paramInt1, paramInt2, paramInt3, paramArrayOfInt, 0, null); }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void createGeometryArrayData(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/*  1589 */     this.vertexFormat = paramInt2;
/*  1590 */     this.vertexCount = paramInt1;
/*  1591 */     this.validVertexCount = paramInt1;
/*       */     
/*  1593 */     this.texCoordSetCount = paramInt3;
/*  1594 */     if (paramArrayOfInt1 == null) {
/*  1595 */       this.texCoordSetMap = null;
/*       */     } else {
/*       */       
/*  1598 */       this.texCoordSetMap = (int[])paramArrayOfInt1.clone();
/*       */     } 
/*       */     
/*  1601 */     this.vertexAttrCount = paramInt4;
/*  1602 */     if (paramArrayOfInt2 == null) {
/*  1603 */       this.vertexAttrSizes = null;
/*       */     } else {
/*       */       
/*  1606 */       this.vertexAttrSizes = (int[])paramArrayOfInt2.clone();
/*       */     } 
/*       */     
/*  1609 */     this.vertexAttrStride = vertexAttrStride();
/*  1610 */     this.stride = stride();
/*       */     
/*  1612 */     this.vertexAttrOffsets = vertexAttrOffsets();
/*  1613 */     this.texCoordSetMapOffset = texCoordSetMapOffset();
/*  1614 */     this.textureOffset = textureOffset();
/*  1615 */     this.colorOffset = colorOffset();
/*  1616 */     this.normalOffset = normalOffset();
/*  1617 */     this.coordinateOffset = coordinateOffset();
/*       */     
/*  1619 */     if ((paramInt2 & 0x80) == 0) {
/*  1620 */       this.vertexData = new float[this.vertexCount * this.stride];
/*       */     } else {
/*       */       
/*  1623 */       this.vertexData = null;
/*  1624 */       if ((paramInt2 & 0x460) != 0) {
/*  1625 */         this.mirrorRefTexCoords = new Object[paramInt3];
/*  1626 */         this.refTexCoords = new Object[paramInt3];
/*  1627 */         if ((paramInt2 & 0x800) != 0)
/*  1628 */           this.refTexCoordsBuffer = new Object[paramInt3]; 
/*       */       } 
/*  1630 */       if ((paramInt2 & 0x1000) != 0) {
/*  1631 */         this.floatRefVertexAttrs = new float[paramInt4][];
/*  1632 */         this.mirrorFloatRefVertexAttrs = new float[paramInt4][];
/*  1633 */         if ((paramInt2 & 0x800) != 0) {
/*  1634 */           this.vertexAttrsRefBuffer = new J3DBuffer[paramInt4];
/*  1635 */           this.floatBufferRefVertexAttrs = new FloatBufferWrapper[paramInt4];
/*  1636 */           this.nioFloatBufferRefVertexAttrs = new Object[paramInt4];
/*       */         } 
/*       */       } 
/*       */     } 
/*  1640 */     if ((paramInt2 & 0x460) != 0) {
/*  1641 */       this.initialTexCoordIndex = new int[paramInt3];
/*       */     }
/*  1643 */     if ((paramInt2 & 0x1000) != 0) {
/*  1644 */       this.initialVertexAttrIndex = new int[paramInt4];
/*       */     }
/*  1646 */     this.noAlpha = ((paramInt2 & 0x8) == 0);
/*  1647 */     this.lastAlpha[0] = 1.0F;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  1652 */   void setVertexFormat(boolean paramBoolean1, boolean paramBoolean2, Context paramContext) { Pipeline.getPipeline().setVertexFormat(paramContext, this, this.vertexFormat, paramBoolean1, paramBoolean2); }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  1658 */   void disableGlobalAlpha(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) { Pipeline.getPipeline().disableGlobalAlpha(paramContext, this, this.vertexFormat, paramBoolean1, paramBoolean2); }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   float[] updateAlphaInFloatRefColors(Canvas3D paramCanvas3D, int paramInt, float paramFloat) {
/*  1669 */     if (paramCanvas3D.supportGlobalAlpha()) {
/*  1670 */       paramCanvas3D.setGlobalAlpha(paramCanvas3D.ctx, paramFloat);
/*  1671 */       return this.mirrorFloatRefColors[0];
/*       */     } 
/*       */ 
/*       */     
/*  1675 */     if (((this.vertexFormat | this.c4fAllocated) & 0x8) == 0) {
/*  1676 */       return this.mirrorFloatRefColors[0];
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  1681 */     if (paramFloat <= 1.0E-6D) {
/*  1682 */       paramFloat = 1.0E-6F;
/*       */     }
/*       */     
/*  1685 */     assert this.lastAlpha != null;
/*  1686 */     assert this.mirrorFloatRefColors != null;
/*  1687 */     assert this.mirrorFloatRefColors.length == this.lastAlpha.length;
/*       */ 
/*       */ 
/*       */     
/*  1691 */     if (this.lastAlpha.length <= paramInt) {
/*  1692 */       float[] arrayOfFloat = new float[paramInt + 1];
/*  1693 */       for (byte b = 0; b < this.lastAlpha.length; b++) {
/*  1694 */         arrayOfFloat[b] = this.lastAlpha[b];
/*       */       }
/*  1696 */       this.lastAlpha = arrayOfFloat;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1705 */     if (this.mirrorFloatRefColors.length <= paramInt) {
/*  1706 */       float[][] arrayOfFloat = new float[paramInt + 1][];
/*       */       int i;
/*  1708 */       for (i = 0; i < this.mirrorFloatRefColors.length; i++) {
/*  1709 */         arrayOfFloat[i] = this.mirrorFloatRefColors[i];
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1715 */       for (i = this.mirrorFloatRefColors.length; i < paramInt + 1; i++) {
/*  1716 */         arrayOfFloat[i] = new float[4 * this.vertexCount];
/*  1717 */         System.arraycopy(arrayOfFloat[0], 0, arrayOfFloat[i], 0, 4 * this.vertexCount);
/*  1718 */         this.lastAlpha[i] = this.lastAlpha[0];
/*       */       } 
/*       */       
/*  1721 */       this.mirrorFloatRefColors = arrayOfFloat;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1727 */     assert this.lastAlpha[paramInt] >= 0.0D;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1736 */     if ((this.colorChanged & 1 << paramInt) == 0) {
/*       */       
/*  1738 */       if (Math.abs(this.lastAlpha[paramInt] - paramFloat) <= 1.0E-6D)
/*       */       {
/*       */ 
/*       */ 
/*       */         
/*  1743 */         return this.mirrorFloatRefColors[paramInt];
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1749 */       float f = paramFloat / this.lastAlpha[paramInt];
/*       */       
/*  1751 */       float[] arrayOfFloat = this.mirrorFloatRefColors[paramInt];
/*       */       byte b;
/*       */       boolean bool;
/*  1754 */       for (b = 0, bool = false; b < this.vertexCount; b++, bool += true) {
/*  1755 */         arrayOfFloat[bool + 3] = arrayOfFloat[bool + 3] * f;
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  1760 */     else if (paramInt == 0) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1767 */       float[] arrayOfFloat = this.mirrorFloatRefColors[paramInt];
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1774 */       int i = this.initialColorIndex * 4;
/*  1775 */       for (int j = this.initialColorIndex; j < this.validVertexCount; j++, i += 4) {
/*  1776 */         arrayOfFloat[i + 3] = arrayOfFloat[i + 3] * paramFloat;
/*       */       }
/*       */     } else {
/*       */       float f;
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1784 */       if ((this.colorChanged & true) == 0) {
/*       */         
/*  1786 */         f = paramFloat / this.lastAlpha[0];
/*       */       } else {
/*  1788 */         f = paramFloat;
/*       */       } 
/*       */       
/*  1791 */       float[] arrayOfFloat1 = this.mirrorFloatRefColors[0];
/*  1792 */       float[] arrayOfFloat2 = this.mirrorFloatRefColors[paramInt];
/*       */       
/*  1794 */       int i = this.initialColorIndex * 4;
/*  1795 */       for (int j = this.initialColorIndex; j < this.validVertexCount; j++) {
/*  1796 */         arrayOfFloat2[i] = arrayOfFloat1[i++];
/*  1797 */         arrayOfFloat2[i] = arrayOfFloat1[i++];
/*  1798 */         arrayOfFloat2[i] = arrayOfFloat1[i++];
/*  1799 */         arrayOfFloat2[i] = arrayOfFloat1[i++] * f;
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  1804 */     this.lastAlpha[paramInt] = paramFloat;
/*  1805 */     this.colorChanged &= (1 << paramInt ^ 0xFFFFFFFF);
/*  1806 */     this.dirtyFlag |= 0x4;
/*  1807 */     return this.mirrorFloatRefColors[paramInt];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   byte[] updateAlphaInByteRefColors(Canvas3D paramCanvas3D, int paramInt, float paramFloat) {
/*  1820 */     if (paramCanvas3D.supportGlobalAlpha()) {
/*  1821 */       paramCanvas3D.setGlobalAlpha(paramCanvas3D.ctx, paramFloat);
/*  1822 */       return this.mirrorUnsignedByteRefColors[0];
/*       */     } 
/*       */ 
/*       */     
/*  1826 */     if (((this.vertexFormat | this.c4fAllocated) & 0x8) == 0) {
/*  1827 */       return this.mirrorUnsignedByteRefColors[0];
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  1832 */     if (paramFloat <= 1.0E-6D) {
/*  1833 */       paramFloat = 1.0E-6F;
/*       */     }
/*       */     
/*  1836 */     assert this.lastAlpha != null;
/*  1837 */     assert this.mirrorUnsignedByteRefColors != null;
/*  1838 */     assert this.mirrorUnsignedByteRefColors.length == this.lastAlpha.length;
/*       */ 
/*       */ 
/*       */     
/*  1842 */     if (this.lastAlpha.length <= paramInt) {
/*  1843 */       float[] arrayOfFloat = new float[paramInt + 1];
/*  1844 */       for (byte b = 0; b < this.lastAlpha.length; b++) {
/*  1845 */         arrayOfFloat[b] = this.lastAlpha[b];
/*       */       }
/*  1847 */       this.lastAlpha = arrayOfFloat;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  1852 */     if (this.mirrorUnsignedByteRefColors.length <= paramInt) {
/*  1853 */       byte[][] arrayOfByte = new byte[paramInt + 1][]; int i;
/*  1854 */       for (i = 0; i < this.mirrorUnsignedByteRefColors.length; i++) {
/*  1855 */         arrayOfByte[i] = this.mirrorUnsignedByteRefColors[i];
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1861 */       for (i = this.mirrorUnsignedByteRefColors.length; i < paramInt + 1; i++) {
/*  1862 */         arrayOfByte[i] = new byte[4 * this.vertexCount];
/*  1863 */         System.arraycopy(arrayOfByte[0], 0, arrayOfByte[i], 0, 4 * this.vertexCount);
/*  1864 */         this.lastAlpha[i] = this.lastAlpha[0];
/*       */       } 
/*       */       
/*  1867 */       this.mirrorUnsignedByteRefColors = arrayOfByte;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1873 */     assert this.lastAlpha[paramInt] >= 0.0D;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1882 */     if ((this.colorChanged & 1 << paramInt) == 0) {
/*       */       
/*  1884 */       if (Math.abs(this.lastAlpha[paramInt] - paramFloat) <= 1.0E-6D)
/*       */       {
/*       */ 
/*       */ 
/*       */         
/*  1889 */         return this.mirrorUnsignedByteRefColors[paramInt];
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1895 */       float f = paramFloat / this.lastAlpha[paramInt];
/*       */       
/*  1897 */       byte[] arrayOfByte = this.mirrorUnsignedByteRefColors[paramInt];
/*       */       byte b;
/*       */       boolean bool;
/*  1900 */       for (b = 0, bool = false; b < this.vertexCount; b++, bool += true) {
/*  1901 */         arrayOfByte[bool + 3] = (byte)(int)((arrayOfByte[bool + 3] & 0xFF) * f);
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  1906 */     else if (paramInt == 0) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1912 */       byte[] arrayOfByte = this.mirrorUnsignedByteRefColors[paramInt];
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1918 */       int i = this.initialColorIndex * 4;
/*  1919 */       for (int j = this.initialColorIndex; j < this.validVertexCount; j++, i += 4) {
/*  1920 */         arrayOfByte[i + 3] = (byte)(int)((arrayOfByte[i + 3] & 0xFF) * paramFloat);
/*       */       }
/*       */     } else {
/*       */       float f;
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1928 */       if ((this.colorChanged & true) == 0) {
/*       */         
/*  1930 */         f = paramFloat / this.lastAlpha[0];
/*       */       } else {
/*  1932 */         f = paramFloat;
/*       */       } 
/*  1934 */       byte[] arrayOfByte1 = this.mirrorUnsignedByteRefColors[0];
/*  1935 */       byte[] arrayOfByte2 = this.mirrorUnsignedByteRefColors[paramInt];
/*       */       
/*  1937 */       int i = this.initialColorIndex * 4;
/*  1938 */       for (int j = this.initialColorIndex; j < this.validVertexCount; j++) {
/*  1939 */         arrayOfByte2[i] = arrayOfByte1[i++];
/*  1940 */         arrayOfByte2[i] = arrayOfByte1[i++];
/*  1941 */         arrayOfByte2[i] = arrayOfByte1[i++];
/*  1942 */         arrayOfByte2[i] = (byte)(int)((arrayOfByte1[i++] & 0xFF) * f);
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  1947 */     this.lastAlpha[paramInt] = paramFloat;
/*  1948 */     this.colorChanged &= (1 << paramInt ^ 0xFFFFFFFF);
/*  1949 */     this.dirtyFlag |= 0x4;
/*  1950 */     return this.mirrorUnsignedByteRefColors[paramInt];
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   Object[] updateAlphaInVertexData(Canvas3D paramCanvas3D, int paramInt, float paramFloat) {
/*  1956 */     Object[] arrayOfObject = new Object[2];
/*  1957 */     arrayOfObject[0] = Boolean.FALSE;
/*       */ 
/*       */     
/*  1960 */     if (paramCanvas3D.supportGlobalAlpha()) {
/*  1961 */       paramCanvas3D.setGlobalAlpha(paramCanvas3D.ctx, paramFloat);
/*  1962 */       arrayOfObject[1] = this.vertexData;
/*  1963 */       return arrayOfObject;
/*       */     } 
/*       */ 
/*       */     
/*  1967 */     if ((this.vertexFormat & 0x4) == 0) {
/*  1968 */       arrayOfObject[1] = this.vertexData;
/*  1969 */       return arrayOfObject;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1975 */     if (paramFloat <= 1.0E-6D) {
/*  1976 */       paramFloat = 1.0E-6F;
/*       */     }
/*  1978 */     arrayOfObject[0] = Boolean.TRUE;
/*       */     
/*  1980 */     assert this.lastAlpha != null;
/*  1981 */     assert this.mvertexData == null || this.mvertexData.length == this.lastAlpha.length;
/*       */ 
/*       */ 
/*       */     
/*  1985 */     if (this.lastAlpha.length <= paramInt) {
/*  1986 */       float[] arrayOfFloat = new float[paramInt + 1];
/*  1987 */       for (byte b = 0; b < this.lastAlpha.length; b++) {
/*  1988 */         arrayOfFloat[b] = this.lastAlpha[b];
/*       */       }
/*  1990 */       this.lastAlpha = arrayOfFloat;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1996 */     if (this.mvertexData == null || this.mvertexData.length <= paramInt) {
/*       */       
/*  1998 */       float[][] arrayOfFloat = new float[paramInt + 1][];
/*  1999 */       int i = 1;
/*       */       
/*  2001 */       if (this.mvertexData != null) {
/*  2002 */         i = this.mvertexData.length;
/*  2003 */         for (byte b = 0; b < this.mvertexData.length; b++) {
/*  2004 */           arrayOfFloat[b] = this.mvertexData[b];
/*       */         }
/*       */       } 
/*       */       
/*  2008 */       if (arrayOfFloat[0] == null) {
/*  2009 */         arrayOfFloat[0] = this.vertexData;
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2015 */       if (paramInt > 0) {
/*  2016 */         for (int j = i; j < paramInt + 1; j++) {
/*  2017 */           arrayOfFloat[j] = new float[this.stride * this.vertexCount];
/*  2018 */           System.arraycopy(arrayOfFloat[0], 0, arrayOfFloat[j], 0, this.stride * this.vertexCount);
/*       */           
/*  2020 */           this.lastAlpha[j] = this.lastAlpha[0];
/*       */         } 
/*       */       }
/*       */       
/*  2024 */       this.mvertexData = arrayOfFloat;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2030 */     assert this.lastAlpha[paramInt] >= 0.0D;
/*       */     
/*  2032 */     if ((this.colorChanged & 1 << paramInt) == 0) {
/*       */       
/*  2034 */       if (Math.abs(this.lastAlpha[paramInt] - paramFloat) <= 1.0E-6D) {
/*       */ 
/*       */         
/*  2037 */         arrayOfObject[1] = this.mvertexData[paramInt];
/*  2038 */         return arrayOfObject;
/*       */       } 
/*       */       
/*  2041 */       float f = paramFloat / this.lastAlpha[paramInt];
/*       */       
/*  2043 */       float[] arrayOfFloat = this.mvertexData[paramInt]; byte b; int i;
/*  2044 */       for (b = 0, i = this.colorOffset; b < this.vertexCount; 
/*  2045 */         b++, i += this.stride) {
/*  2046 */         arrayOfFloat[i + 3] = arrayOfFloat[i + 3] * f;
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  2051 */     else if (paramInt == 0) {
/*       */ 
/*       */ 
/*       */       
/*  2055 */       float[] arrayOfFloat = this.mvertexData[paramInt];
/*  2056 */       double d = (paramFloat / this.lastAlpha[0]); byte b;
/*       */       int i;
/*  2058 */       for (b = 0, i = this.colorOffset; b < this.vertexCount; 
/*  2059 */         b++, i += this.stride) {
/*  2060 */         arrayOfFloat[i + 3] = (float)(arrayOfFloat[i + 3] * d);
/*       */       }
/*       */     }
/*       */     else {
/*       */       
/*  2065 */       float f = paramFloat / this.lastAlpha[0];
/*  2066 */       float[] arrayOfFloat1 = this.mvertexData[0];
/*  2067 */       float[] arrayOfFloat2 = this.mvertexData[paramInt]; byte b;
/*       */       int i;
/*  2069 */       for (b = 0, i = this.colorOffset; b < this.vertexCount; 
/*  2070 */         b++, i += this.stride) {
/*  2071 */         System.arraycopy(arrayOfFloat1, i, arrayOfFloat2, i, 3);
/*  2072 */         arrayOfFloat2[i + 3] = arrayOfFloat1[i + 3] * f;
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  2077 */     this.lastAlpha[paramInt] = paramFloat;
/*  2078 */     this.colorChanged &= (1 << paramInt ^ 0xFFFFFFFF);
/*  2079 */     this.dirtyFlag |= 0x4;
/*  2080 */     arrayOfObject[1] = this.mvertexData[paramInt];
/*  2081 */     return arrayOfObject;
/*       */   }
/*       */ 
/*       */   
/*       */   Object[] updateAlphaInInterLeavedData(Canvas3D paramCanvas3D, int paramInt, float paramFloat) {
/*  2086 */     Object[] arrayOfObject = new Object[2];
/*  2087 */     arrayOfObject[0] = Boolean.FALSE;
/*       */ 
/*       */     
/*  2090 */     if (paramCanvas3D.supportGlobalAlpha()) {
/*  2091 */       paramCanvas3D.setGlobalAlpha(paramCanvas3D.ctx, paramFloat);
/*  2092 */       arrayOfObject[1] = null;
/*  2093 */       return arrayOfObject;
/*       */     } 
/*       */ 
/*       */     
/*  2097 */     if (((this.vertexFormat | this.c4fAllocated) & 0x8) == 0) {
/*  2098 */       arrayOfObject[1] = this.mirrorInterleavedColorPointer[0];
/*  2099 */       return arrayOfObject;
/*       */     } 
/*  2101 */     int i = this.initialColorIndex << 2;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2106 */     if (paramFloat <= 1.0E-6D) {
/*  2107 */       paramFloat = 1.0E-6F;
/*       */     }
/*  2109 */     arrayOfObject[0] = Boolean.TRUE;
/*       */     
/*  2111 */     assert this.lastAlpha != null;
/*  2112 */     assert this.mirrorInterleavedColorPointer != null;
/*  2113 */     assert this.mirrorInterleavedColorPointer.length == this.lastAlpha.length;
/*       */ 
/*       */ 
/*       */     
/*  2117 */     if (this.lastAlpha.length <= paramInt) {
/*  2118 */       float[] arrayOfFloat = new float[paramInt + 1];
/*  2119 */       for (byte b = 0; b < this.lastAlpha.length; b++) {
/*  2120 */         arrayOfFloat[b] = this.lastAlpha[b];
/*       */       }
/*  2122 */       this.lastAlpha = arrayOfFloat;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  2127 */     if (this.mirrorInterleavedColorPointer.length <= paramInt) {
/*       */       
/*  2129 */       float[][] arrayOfFloat = new float[paramInt + 1][];
/*       */       int j;
/*  2131 */       for (j = 0; j < this.mirrorInterleavedColorPointer.length; j++) {
/*  2132 */         arrayOfFloat[j] = this.mirrorInterleavedColorPointer[j];
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2138 */       for (j = this.mirrorInterleavedColorPointer.length; j < paramInt + 1; j++) {
/*  2139 */         arrayOfFloat[j] = new float[4 * this.vertexCount];
/*  2140 */         System.arraycopy(arrayOfFloat[0], 0, arrayOfFloat[j], 0, 4 * this.vertexCount);
/*  2141 */         this.lastAlpha[j] = this.lastAlpha[0];
/*       */       } 
/*       */       
/*  2144 */       this.mirrorInterleavedColorPointer = arrayOfFloat;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2150 */     assert this.lastAlpha[paramInt] >= 0.0D;
/*       */     
/*  2152 */     if ((this.colorChanged & 1 << paramInt) == 0) {
/*       */       
/*  2154 */       if (Math.abs(this.lastAlpha[paramInt] - paramFloat) <= 1.0E-6D) {
/*       */ 
/*       */         
/*  2157 */         arrayOfObject[1] = this.mirrorInterleavedColorPointer[paramInt];
/*  2158 */         return arrayOfObject;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  2163 */       float f = paramFloat / this.lastAlpha[paramInt];
/*       */       
/*  2165 */       float[] arrayOfFloat = this.mirrorInterleavedColorPointer[paramInt];
/*       */       
/*  2167 */       i = this.initialColorIndex << 2;
/*  2168 */       for (int j = i; j < i + (this.vertexCount << 2); j += 4) {
/*  2169 */         arrayOfFloat[j + 3] = arrayOfFloat[j + 3] * f;
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  2174 */     else if (paramInt == 0) {
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2179 */       float[] arrayOfFloat = this.mirrorInterleavedColorPointer[paramInt];
/*       */       
/*  2181 */       for (int j = i; j < i + (this.vertexCount << 2); j += 4) {
/*  2182 */         arrayOfFloat[j + 3] = arrayOfFloat[j + 3] * paramFloat;
/*       */       }
/*       */     } else {
/*       */       float f;
/*       */ 
/*       */ 
/*       */       
/*  2189 */       if ((this.colorChanged & true) == 0) {
/*       */         
/*  2191 */         f = paramFloat / this.lastAlpha[0];
/*       */       } else {
/*  2193 */         f = paramFloat;
/*       */       } 
/*       */       
/*  2196 */       float[] arrayOfFloat1 = this.mirrorInterleavedColorPointer[0];
/*  2197 */       float[] arrayOfFloat2 = this.mirrorInterleavedColorPointer[paramInt];
/*       */       
/*  2199 */       for (int j = i; j < i + (this.vertexCount << 2); ) {
/*       */         
/*  2201 */         arrayOfFloat2[j] = arrayOfFloat1[j++];
/*  2202 */         arrayOfFloat2[j] = arrayOfFloat1[j++];
/*  2203 */         arrayOfFloat2[j] = arrayOfFloat1[j++];
/*  2204 */         arrayOfFloat2[j] = arrayOfFloat1[j++] * f;
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  2209 */     this.lastAlpha[paramInt] = paramFloat;
/*  2210 */     this.colorChanged &= (1 << paramInt ^ 0xFFFFFFFF);
/*  2211 */     this.dirtyFlag |= 0x4;
/*  2212 */     arrayOfObject[1] = this.mirrorInterleavedColorPointer[paramInt];
/*  2213 */     return arrayOfObject;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void execute(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, int paramInt, boolean paramBoolean3) {
/*  2228 */     boolean bool = false;
/*       */ 
/*       */ 
/*       */     
/*  2232 */     if ((this.vertexFormat & 0x80) == 0) {
/*       */       float[] arrayOfFloat;
/*       */       int i;
/*  2235 */       synchronized (this) {
/*  2236 */         i = this.dirtyFlag;
/*  2237 */         if (paramBoolean2 && !paramBoolean3) {
/*       */           
/*  2239 */           Object[] arrayOfObject = updateAlphaInVertexData(paramCanvas3D, paramInt, paramFloat);
/*  2240 */           bool = (arrayOfObject[false] == Boolean.TRUE);
/*  2241 */           arrayOfFloat = (float[])arrayOfObject[1];
/*       */ 
/*       */           
/*  2244 */           if (paramFloat != this.lastScreenAlpha) {
/*       */             
/*  2246 */             this.lastScreenAlpha = paramFloat;
/*  2247 */             i |= 0x4;
/*       */           } 
/*       */         } else {
/*  2250 */           arrayOfFloat = this.vertexData;
/*       */           
/*  2252 */           if (this.lastScreenAlpha != -1.0F) {
/*  2253 */             this.lastScreenAlpha = -1.0F;
/*  2254 */             i |= 0x4;
/*       */           } 
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  2260 */         this.dirtyFlag = 0;
/*       */       } 
/*       */       
/*  2263 */       Pipeline.getPipeline().execute(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, bool, paramBoolean3, this.initialVertexIndex, this.validVertexCount, ((this.vertexFormat & 0x4) != 0) ? (this.vertexFormat | 0xC) : this.vertexFormat, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, paramCanvas3D.numActiveTexUnit, this.vertexAttrCount, this.vertexAttrSizes, arrayOfFloat, null, i);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     }
/*  2280 */     else if ((this.vertexFormat & 0x800) == 0) {
/*       */       
/*  2282 */       if ((this.vertexFormat & 0x100) != 0) {
/*  2283 */         int i; if (this.interLeavedVertexData == null) {
/*       */           return;
/*       */         }
/*  2286 */         float[] arrayOfFloat = null;
/*       */         
/*  2288 */         synchronized (this) {
/*  2289 */           i = this.dirtyFlag;
/*  2290 */           if (paramBoolean2 && !paramBoolean3) {
/*       */             
/*  2292 */             Object[] arrayOfObject = updateAlphaInInterLeavedData(paramCanvas3D, paramInt, paramFloat);
/*  2293 */             bool = (arrayOfObject[false] == Boolean.TRUE);
/*  2294 */             arrayOfFloat = (float[])arrayOfObject[1];
/*  2295 */             if (paramFloat != this.lastScreenAlpha) {
/*  2296 */               this.lastScreenAlpha = paramFloat;
/*  2297 */               i |= 0x4;
/*       */             }
/*       */           
/*       */           }
/*  2301 */           else if (this.lastScreenAlpha != -1.0F) {
/*  2302 */             this.lastScreenAlpha = -1.0F;
/*  2303 */             i |= 0x4;
/*       */           } 
/*       */           
/*  2306 */           this.dirtyFlag = 0;
/*       */         } 
/*       */         
/*  2309 */         Pipeline.getPipeline().execute(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, bool, paramBoolean3, this.initialVertexIndex, this.validVertexCount, this.vertexFormat, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, paramCanvas3D.numActiveTexUnit, this.vertexAttrCount, this.vertexAttrSizes, this.interLeavedVertexData, arrayOfFloat, i);
/*       */       } else {
/*       */         int i;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2331 */         if (this.vertexType == 0 || (this.vertexType & 0xF) == 0 || ((this.vertexFormat & 0x4) != 0 && (this.vertexType & 0x3F0) == 0) || ((this.vertexFormat & 0x2) != 0 && (this.vertexType & 0xC00) == 0) || ((this.vertexFormat & 0x1000) != 0 && (this.vertexType & 0x8000) == 0) || ((this.vertexFormat & 0x460) != 0 && (this.vertexType & 0x7000) == 0)) {
/*       */           return;
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2343 */         byte[] arrayOfByte = null;
/*  2344 */         float[] arrayOfFloat = null;
/*       */         
/*  2346 */         if ((this.vertexType & 0xD0) != 0) {
/*       */           
/*  2348 */           synchronized (this) {
/*  2349 */             i = this.dirtyFlag;
/*  2350 */             if (paramBoolean2 && !paramBoolean3) {
/*  2351 */               arrayOfFloat = updateAlphaInFloatRefColors(paramCanvas3D, paramInt, paramFloat);
/*  2352 */               if (paramFloat != this.lastScreenAlpha) {
/*  2353 */                 this.lastScreenAlpha = paramFloat;
/*  2354 */                 i |= 0x4;
/*       */               } 
/*       */             } else {
/*  2357 */               arrayOfFloat = this.mirrorFloatRefColors[0];
/*       */               
/*  2359 */               if (this.lastScreenAlpha != -1.0F) {
/*  2360 */                 this.lastScreenAlpha = -1.0F;
/*  2361 */                 i |= 0x4;
/*       */               } 
/*       */             } 
/*       */             
/*  2365 */             this.dirtyFlag = 0;
/*       */           }
/*       */         
/*  2368 */         } else if ((this.vertexType & 0x320) != 0) {
/*  2369 */           synchronized (this) {
/*  2370 */             i = this.dirtyFlag;
/*  2371 */             if (paramBoolean2 && !paramBoolean3) {
/*  2372 */               arrayOfByte = updateAlphaInByteRefColors(paramCanvas3D, paramInt, paramFloat);
/*  2373 */               if (paramFloat != this.lastScreenAlpha) {
/*  2374 */                 this.lastScreenAlpha = paramFloat;
/*  2375 */                 i |= 0x4;
/*       */               } 
/*       */             } else {
/*  2378 */               arrayOfByte = this.mirrorUnsignedByteRefColors[0];
/*       */               
/*  2380 */               if (this.lastScreenAlpha != -1.0F) {
/*  2381 */                 this.lastScreenAlpha = -1.0F;
/*  2382 */                 i |= 0x4;
/*       */               } 
/*       */             } 
/*  2385 */             this.dirtyFlag = 0;
/*       */           } 
/*       */         } else {
/*       */           
/*  2389 */           i = this.dirtyFlag;
/*       */         } 
/*       */         
/*  2392 */         byte b = 0;
/*  2393 */         if ((this.vertexType & 0x5) != 0)
/*  2394 */           b |= true; 
/*  2395 */         if ((this.vertexType & 0xA) != 0)
/*  2396 */           b |= 0x2; 
/*  2397 */         if ((this.vertexType & 0xD0) != 0)
/*  2398 */           b |= 0x4; 
/*  2399 */         if ((this.vertexType & 0x320) != 0)
/*  2400 */           b |= 0x8; 
/*  2401 */         if ((this.vertexType & 0xC00) != 0)
/*  2402 */           b |= 0x10; 
/*  2403 */         if ((this.vertexType & 0x8000) != 0)
/*  2404 */           b |= 0x40; 
/*  2405 */         if ((this.vertexType & 0x7000) != 0) {
/*  2406 */           b |= 0x20;
/*       */         }
/*  2408 */         Pipeline.getPipeline().executeVA(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, paramBoolean3, this.validVertexCount, this.vertexFormat | this.c4fAllocated, b, this.initialCoordIndex, this.mirrorFloatRefCoords, this.mirrorDoubleRefCoords, this.initialColorIndex, arrayOfFloat, arrayOfByte, this.initialNormalIndex, this.mirrorFloatRefNormals, this.vertexAttrCount, this.vertexAttrSizes, this.initialVertexAttrIndex, this.mirrorFloatRefVertexAttrs, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMap, paramCanvas3D.numActiveTexUnit, this.initialTexCoordIndex, this.texCoordStride, this.mirrorRefTexCoords, i);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     }
/*  2432 */     else if ((this.vertexFormat & 0x100) != 0) {
/*       */       int i;
/*  2434 */       if (this.interleavedFloatBufferImpl == null) {
/*       */         return;
/*       */       }
/*  2437 */       float[] arrayOfFloat = null;
/*  2438 */       synchronized (this) {
/*  2439 */         i = this.dirtyFlag;
/*  2440 */         if (paramBoolean2 && !paramBoolean3) {
/*       */ 
/*       */           
/*  2443 */           Object[] arrayOfObject = updateAlphaInInterLeavedData(paramCanvas3D, paramInt, paramFloat);
/*  2444 */           bool = (arrayOfObject[false] == Boolean.TRUE);
/*  2445 */           arrayOfFloat = (float[])arrayOfObject[1];
/*       */           
/*  2447 */           if (paramFloat != this.lastScreenAlpha) {
/*  2448 */             this.lastScreenAlpha = paramFloat;
/*  2449 */             i |= 0x4;
/*       */           } 
/*       */         } else {
/*       */           
/*  2453 */           arrayOfFloat = null;
/*       */           
/*  2455 */           if (this.lastScreenAlpha != -1.0F) {
/*  2456 */             this.lastScreenAlpha = -1.0F;
/*  2457 */             i |= 0x4;
/*       */           } 
/*       */         } 
/*  2460 */         this.dirtyFlag = 0;
/*       */       } 
/*       */       
/*  2463 */       Pipeline.getPipeline().executeInterleavedBuffer(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, bool, paramBoolean3, this.initialVertexIndex, this.validVertexCount, this.vertexFormat, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, paramCanvas3D.numActiveTexUnit, this.interleavedFloatBufferImpl.getBufferAsObject(), arrayOfFloat, i);
/*       */     } else {
/*       */       int i;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2484 */       if (this.vertexType == 0 || (this.vertexType & 0xF) == 0 || ((this.vertexFormat & 0x4) != 0 && (this.vertexType & 0x3F0) == 0) || ((this.vertexFormat & 0x2) != 0 && (this.vertexType & 0xC00) == 0) || ((this.vertexFormat & 0x1000) != 0 && (this.vertexType & 0x8000) == 0) || ((this.vertexFormat & 0x460) != 0 && (this.vertexType & 0x7000) == 0)) {
/*       */         return;
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2496 */       byte[] arrayOfByte = null;
/*  2497 */       float[] arrayOfFloat = null;
/*       */       
/*  2499 */       if ((this.vertexType & 0x10) != 0) {
/*  2500 */         synchronized (this) {
/*  2501 */           i = this.dirtyFlag;
/*  2502 */           if (paramBoolean2 && !paramBoolean3) {
/*  2503 */             arrayOfFloat = updateAlphaInFloatRefColors(paramCanvas3D, paramInt, paramFloat);
/*       */             
/*  2505 */             if (paramFloat != this.lastScreenAlpha) {
/*  2506 */               this.lastScreenAlpha = paramFloat;
/*  2507 */               i |= 0x4;
/*       */             }
/*       */           
/*       */           } else {
/*       */             
/*  2512 */             arrayOfFloat = this.mirrorFloatRefColors[0];
/*       */             
/*  2514 */             if (this.lastScreenAlpha != -1.0F) {
/*  2515 */               this.lastScreenAlpha = -1.0F;
/*  2516 */               i |= 0x4;
/*       */             } 
/*       */           } 
/*       */           
/*  2520 */           this.dirtyFlag = 0;
/*       */         }
/*       */       
/*  2523 */       } else if ((this.vertexType & 0x20) != 0) {
/*  2524 */         synchronized (this) {
/*  2525 */           i = this.dirtyFlag;
/*  2526 */           if (paramBoolean2 && !paramBoolean3) {
/*  2527 */             arrayOfByte = updateAlphaInByteRefColors(paramCanvas3D, paramInt, paramFloat);
/*       */             
/*  2529 */             if (paramFloat != this.lastScreenAlpha) {
/*  2530 */               this.lastScreenAlpha = paramFloat;
/*  2531 */               i |= 0x4;
/*       */             }
/*       */           
/*       */           } else {
/*       */             
/*  2536 */             arrayOfByte = this.mirrorUnsignedByteRefColors[0];
/*       */             
/*  2538 */             if (this.lastScreenAlpha != -1.0F) {
/*  2539 */               this.lastScreenAlpha = -1.0F;
/*  2540 */               i |= 0x4;
/*       */             } 
/*       */           } 
/*  2543 */           this.dirtyFlag = 0;
/*       */         } 
/*       */       } else {
/*       */         
/*  2547 */         i = this.dirtyFlag;
/*       */       } 
/*       */       
/*  2550 */       Object object1 = null, object2 = null, object3 = null;
/*       */       
/*  2552 */       byte b = 0;
/*  2553 */       if ((this.vertexType & true) != 0) {
/*  2554 */         b |= true;
/*  2555 */         object1 = this.floatBufferRefCoords.getBufferAsObject();
/*  2556 */       } else if ((this.vertexType & 0x2) != 0) {
/*  2557 */         b |= 0x2;
/*  2558 */         object1 = this.doubleBufferRefCoords.getBufferAsObject();
/*       */       } 
/*       */       
/*  2561 */       if ((this.vertexType & 0x10) != 0) {
/*  2562 */         b |= 0x4;
/*  2563 */         object2 = this.floatBufferRefColors.getBufferAsObject();
/*  2564 */       } else if ((this.vertexType & 0x20) != 0) {
/*  2565 */         b |= 0x8;
/*  2566 */         object2 = this.byteBufferRefColors.getBufferAsObject();
/*       */       } 
/*       */       
/*  2569 */       if ((this.vertexType & 0xC00) != 0) {
/*  2570 */         b |= 0x10;
/*  2571 */         object3 = this.floatBufferRefNormals.getBufferAsObject();
/*       */       } 
/*       */       
/*  2574 */       if ((this.vertexType & 0x8000) != 0) {
/*  2575 */         b |= 0x40;
/*       */       }
/*       */       
/*  2578 */       if ((this.vertexType & 0x7000) != 0) {
/*  2579 */         b |= 0x20;
/*       */       }
/*  2581 */       Pipeline.getPipeline().executeVABuffer(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, paramBoolean3, this.validVertexCount, this.vertexFormat | this.c4fAllocated, b, this.initialCoordIndex, object1, this.initialColorIndex, object2, arrayOfFloat, arrayOfByte, this.initialNormalIndex, object3, this.vertexAttrCount, this.vertexAttrSizes, this.initialVertexAttrIndex, this.nioFloatBufferRefVertexAttrs, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMap, paramCanvas3D.numActiveTexUnit, this.initialTexCoordIndex, this.texCoordStride, this.refTexCoords, i);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void buildGA(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/*  2611 */     float[] arrayOfFloat = null;
/*       */ 
/*       */     
/*  2614 */     assert (this.vertexFormat & 0x800) == 0;
/*       */     
/*  2616 */     if ((this.vertexFormat & 0x80) == 0) {
/*  2617 */       arrayOfFloat = this.vertexData;
/*       */     }
/*  2619 */     else if ((this.vertexFormat & 0x100) != 0 && (this.vertexFormat & 0x800) == 0) {
/*       */       
/*  2621 */       arrayOfFloat = this.interLeavedVertexData;
/*       */     } 
/*  2623 */     if (arrayOfFloat != null) {
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2628 */       Pipeline.getPipeline().buildGA(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, paramBoolean2, paramFloat, paramBoolean3, this.initialVertexIndex, this.validVertexCount, this.vertexFormat, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, this.vertexAttrCount, this.vertexAttrSizes, (paramTransform3D1 == null) ? null : paramTransform3D1.mat, (paramTransform3D2 == null) ? null : paramTransform3D2.mat, arrayOfFloat);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     }
/*       */     else {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2644 */       if (this.vertexType == 0 || (this.vertexType & 0xF) == 0 || ((this.vertexFormat & 0x4) != 0 && (this.vertexType & 0x3F0) == 0) || ((this.vertexFormat & 0x2) != 0 && (this.vertexType & 0xC00) == 0) || ((this.vertexFormat & 0x1000) != 0 && (this.vertexType & 0x8000) == 0) || ((this.vertexFormat & 0x460) != 0 && (this.vertexType & 0x7000) == 0)) {
/*       */         return;
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2659 */       if ((this.vertexFormat & 0x800) == 0) {
/*       */ 
/*       */         
/*  2662 */         byte b = 0;
/*  2663 */         if ((this.vertexType & 0x5) != 0)
/*  2664 */           b |= true; 
/*  2665 */         if ((this.vertexType & 0xA) != 0)
/*  2666 */           b |= 0x2; 
/*  2667 */         if ((this.vertexType & 0xD0) != 0)
/*  2668 */           b |= 0x4; 
/*  2669 */         if ((this.vertexType & 0x320) != 0)
/*  2670 */           b |= 0x8; 
/*  2671 */         if ((this.vertexType & 0xC00) != 0)
/*  2672 */           b |= 0x10; 
/*  2673 */         if ((this.vertexType & 0x8000) != 0)
/*  2674 */           b |= 0x40; 
/*  2675 */         if ((this.vertexType & 0x7000) != 0) {
/*  2676 */           b |= 0x20;
/*       */         }
/*  2678 */         Pipeline.getPipeline().buildGAForByRef(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, paramBoolean2, paramFloat, paramBoolean3, this.validVertexCount, this.vertexFormat, b, this.initialCoordIndex, this.mirrorFloatRefCoords, this.mirrorDoubleRefCoords, this.initialColorIndex, this.mirrorFloatRefColors[0], this.mirrorUnsignedByteRefColors[0], this.initialNormalIndex, this.mirrorFloatRefNormals, this.vertexAttrCount, this.vertexAttrSizes, this.initialVertexAttrIndex, this.mirrorFloatRefVertexAttrs, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMap, this.initialTexCoordIndex, this.texCoordStride, this.mirrorRefTexCoords, (paramTransform3D1 == null) ? null : paramTransform3D1.mat, (paramTransform3D2 == null) ? null : paramTransform3D2.mat);
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void unIndexify(IndexedGeometryArrayRetained paramIndexedGeometryArrayRetained) {
/*  2759 */     if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x800) == 0) {
/*  2760 */       unIndexifyJavaArray(paramIndexedGeometryArrayRetained);
/*       */     } else {
/*       */       
/*  2763 */       unIndexifyNIOBuffer(paramIndexedGeometryArrayRetained);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   private void unIndexifyJavaArray(IndexedGeometryArrayRetained paramIndexedGeometryArrayRetained) {
/*  2770 */     int i = 0, j = 0;
/*  2771 */     byte b = 0;
/*  2772 */     float[] arrayOfFloat = null;
/*       */ 
/*       */     
/*  2775 */     int k = paramIndexedGeometryArrayRetained.initialIndexIndex;
/*  2776 */     int m = paramIndexedGeometryArrayRetained.initialIndexIndex + paramIndexedGeometryArrayRetained.validIndexCount;
/*       */     
/*  2778 */     if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x80) == 0 || (paramIndexedGeometryArrayRetained.vertexFormat & 0x100) != 0) {
/*       */ 
/*       */       
/*  2781 */       if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x80) == 0) {
/*  2782 */         arrayOfFloat = paramIndexedGeometryArrayRetained.vertexData;
/*  2783 */         if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x4) != 0) {
/*  2784 */           b = 4;
/*       */         }
/*  2786 */       } else if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x100) != 0) {
/*  2787 */         arrayOfFloat = paramIndexedGeometryArrayRetained.interLeavedVertexData;
/*  2788 */         if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  2789 */           b = 4;
/*  2790 */         } else if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x4) != 0) {
/*  2791 */           b = 3;
/*       */         } 
/*       */       } 
/*       */       
/*  2795 */       for (int n = k; n < m; n++) {
/*  2796 */         if ((this.vertexFormat & 0x2) != 0) {
/*  2797 */           System.arraycopy(arrayOfFloat, paramIndexedGeometryArrayRetained.indexNormal[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.normalOffset, this.vertexData, i + this.normalOffset, 3);
/*       */         }
/*       */ 
/*       */         
/*  2801 */         if (b == 4) {
/*       */           
/*  2803 */           System.arraycopy(arrayOfFloat, paramIndexedGeometryArrayRetained.indexColor[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.colorOffset, this.vertexData, i + this.colorOffset, b);
/*       */         
/*       */         }
/*  2806 */         else if (b == 3) {
/*       */           
/*  2808 */           System.arraycopy(arrayOfFloat, paramIndexedGeometryArrayRetained.indexColor[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.colorOffset, this.vertexData, i + this.colorOffset, b);
/*       */ 
/*       */           
/*  2811 */           this.vertexData[i + this.colorOffset + 3] = 1.0F;
/*       */         } 
/*       */         
/*  2814 */         if ((this.vertexFormat & 0x460) != 0) {
/*  2815 */           int i2 = i + this.textureOffset;
/*  2816 */           int i3 = 0;
/*       */           
/*  2818 */           for (int i1 = 0; i1 < this.texCoordSetCount; 
/*  2819 */             i1++, i2 += this.texCoordStride) {
/*       */             
/*  2821 */             if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x100) != 0) {
/*  2822 */               i3 = i1 * this.texCoordStride;
/*       */             }
/*       */             
/*  2825 */             System.arraycopy(arrayOfFloat, paramIndexedGeometryArrayRetained.indexTexCoord[i1][n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.textureOffset + i3, this.vertexData, i2, this.texCoordStride);
/*       */           } 
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  2831 */         if ((this.vertexFormat & 0x1000) != 0) {
/*       */           
/*  2833 */           assert (paramIndexedGeometryArrayRetained.vertexFormat & 0x100) == 0;
/*       */           
/*  2835 */           for (byte b1 = 0; b1 < this.vertexAttrCount; b1++) {
/*  2836 */             int i1 = i + this.vertexAttrOffsets[b1];
/*       */             
/*  2838 */             System.arraycopy(arrayOfFloat, paramIndexedGeometryArrayRetained.indexVertexAttr[b1][n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.vertexAttrOffsets[b1], this.vertexData, i1, this.vertexAttrSizes[b1]);
/*       */           } 
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  2844 */         if ((this.vertexFormat & true) != 0)
/*       */         {
/*  2846 */           System.arraycopy(arrayOfFloat, paramIndexedGeometryArrayRetained.indexCoord[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.coordinateOffset, this.vertexData, i + this.coordinateOffset, 3);
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2852 */         i += this.stride;
/*       */       } 
/*       */     } else {
/*       */       
/*  2856 */       if ((this.vertexFormat & 0x2) != 0) {
/*  2857 */         int n; i = this.normalOffset;
/*  2858 */         switch (paramIndexedGeometryArrayRetained.vertexType & 0xC00) {
/*       */           case 1024:
/*  2860 */             for (n = k; n < m; n++) {
/*  2861 */               System.arraycopy(paramIndexedGeometryArrayRetained.floatRefNormals, paramIndexedGeometryArrayRetained.indexNormal[n] * 3, this.vertexData, i, 3);
/*       */ 
/*       */ 
/*       */               
/*  2865 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 2048:
/*  2869 */             for (n = k; n < m; n++) {
/*  2870 */               int i1 = paramIndexedGeometryArrayRetained.indexNormal[n];
/*  2871 */               this.vertexData[i] = (paramIndexedGeometryArrayRetained.v3fRefNormals[i1]).x;
/*  2872 */               this.vertexData[i + 1] = (paramIndexedGeometryArrayRetained.v3fRefNormals[i1]).y;
/*  2873 */               this.vertexData[i + 2] = (paramIndexedGeometryArrayRetained.v3fRefNormals[i1]).z;
/*  2874 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */         } 
/*       */ 
/*       */ 
/*       */       
/*       */       } 
/*  2882 */       if ((this.vertexFormat & 0x4) != 0) {
/*  2883 */         int n; i = this.colorOffset;
/*  2884 */         int i1 = 3;
/*  2885 */         if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  2886 */           i1 = 4;
/*       */         }
/*  2888 */         switch (paramIndexedGeometryArrayRetained.vertexType & 0x3F0) {
/*       */           case 16:
/*  2890 */             for (n = k; n < m; n++) {
/*  2891 */               if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  2892 */                 System.arraycopy(paramIndexedGeometryArrayRetained.floatRefColors, paramIndexedGeometryArrayRetained.indexColor[n] * i1, this.vertexData, i, 4);
/*       */               
/*       */               }
/*       */               else {
/*       */ 
/*       */                 
/*  2898 */                 System.arraycopy(paramIndexedGeometryArrayRetained.floatRefColors, paramIndexedGeometryArrayRetained.indexColor[n] * i1, this.vertexData, i, 3);
/*       */ 
/*       */ 
/*       */                 
/*  2902 */                 this.vertexData[i + 3] = 1.0F;
/*       */               } 
/*  2904 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 32:
/*  2908 */             for (n = k; n < m; n++) {
/*  2909 */               int i2 = paramIndexedGeometryArrayRetained.indexColor[n] * i1;
/*  2910 */               this.vertexData[i] = (paramIndexedGeometryArrayRetained.byteRefColors[i2] & 0xFF) * 0.003921569F;
/*  2911 */               this.vertexData[i + 1] = (paramIndexedGeometryArrayRetained.byteRefColors[i2 + 1] & 0xFF) * 0.003921569F;
/*  2912 */               this.vertexData[i + 2] = (paramIndexedGeometryArrayRetained.byteRefColors[i2 + 2] & 0xFF) * 0.003921569F;
/*  2913 */               if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  2914 */                 this.vertexData[i + 3] = (paramIndexedGeometryArrayRetained.byteRefColors[i2 + 3] & 0xFF) * 0.003921569F;
/*       */               } else {
/*       */                 
/*  2917 */                 this.vertexData[i + 3] = 1.0F;
/*       */               } 
/*  2919 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 64:
/*  2923 */             for (n = k; n < m; n++) {
/*  2924 */               int i2 = paramIndexedGeometryArrayRetained.indexColor[n];
/*  2925 */               this.vertexData[i] = (paramIndexedGeometryArrayRetained.c3fRefColors[i2]).x;
/*  2926 */               this.vertexData[i + 1] = (paramIndexedGeometryArrayRetained.c3fRefColors[i2]).y;
/*  2927 */               this.vertexData[i + 2] = (paramIndexedGeometryArrayRetained.c3fRefColors[i2]).z;
/*  2928 */               this.vertexData[i + 3] = 1.0F;
/*  2929 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 128:
/*  2933 */             for (n = k; n < m; n++) {
/*  2934 */               int i2 = paramIndexedGeometryArrayRetained.indexColor[n];
/*  2935 */               this.vertexData[i] = (paramIndexedGeometryArrayRetained.c4fRefColors[i2]).x;
/*  2936 */               this.vertexData[i + 1] = (paramIndexedGeometryArrayRetained.c4fRefColors[i2]).y;
/*  2937 */               this.vertexData[i + 2] = (paramIndexedGeometryArrayRetained.c4fRefColors[i2]).z;
/*  2938 */               this.vertexData[i + 3] = (paramIndexedGeometryArrayRetained.c4fRefColors[i2]).w;
/*  2939 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 256:
/*  2943 */             for (n = k; n < m; n++) {
/*  2944 */               int i2 = paramIndexedGeometryArrayRetained.indexColor[n];
/*  2945 */               this.vertexData[i] = ((paramIndexedGeometryArrayRetained.c3bRefColors[i2]).x & 0xFF) * 0.003921569F;
/*  2946 */               this.vertexData[i + 1] = ((paramIndexedGeometryArrayRetained.c3bRefColors[i2]).y & 0xFF) * 0.003921569F;
/*  2947 */               this.vertexData[i + 2] = ((paramIndexedGeometryArrayRetained.c3bRefColors[i2]).z & 0xFF) * 0.003921569F;
/*  2948 */               this.vertexData[i + 3] = 1.0F;
/*  2949 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 512:
/*  2953 */             for (n = k; n < m; n++) {
/*  2954 */               int i2 = paramIndexedGeometryArrayRetained.indexColor[n];
/*  2955 */               this.vertexData[i] = ((paramIndexedGeometryArrayRetained.c4bRefColors[i2]).x & 0xFF) * 0.003921569F;
/*  2956 */               this.vertexData[i + 1] = ((paramIndexedGeometryArrayRetained.c4bRefColors[i2]).y & 0xFF) * 0.003921569F;
/*  2957 */               this.vertexData[i + 2] = ((paramIndexedGeometryArrayRetained.c4bRefColors[i2]).z & 0xFF) * 0.003921569F;
/*  2958 */               this.vertexData[i + 3] = ((paramIndexedGeometryArrayRetained.c4bRefColors[i2]).w & 0xFF) * 0.003921569F;
/*  2959 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */         } 
/*       */ 
/*       */ 
/*       */       
/*       */       } 
/*  2967 */       if ((this.vertexFormat & 0x460) != 0) {
/*  2968 */         int n; i = this.textureOffset;
/*  2969 */         switch (paramIndexedGeometryArrayRetained.vertexType & 0x7000) {
/*       */           case 4096:
/*  2971 */             for (n = k; n < m; n++) {
/*  2972 */               byte b1 = 0; j = i;
/*  2973 */               for (; b1 < this.texCoordSetCount; b1++) {
/*  2974 */                 System.arraycopy(paramIndexedGeometryArrayRetained.refTexCoords[b1], paramIndexedGeometryArrayRetained.indexTexCoord[b1][n] * this.texCoordStride, this.vertexData, j, this.texCoordStride);
/*       */ 
/*       */                 
/*  2977 */                 j += this.texCoordStride;
/*       */               } 
/*  2979 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 8192:
/*  2983 */             for (n = k; n < m; n++) {
/*  2984 */               byte b1 = 0; j = i;
/*  2985 */               for (; b1 < this.texCoordSetCount; b1++) {
/*  2986 */                 int i1 = paramIndexedGeometryArrayRetained.indexTexCoord[b1][n];
/*  2987 */                 this.vertexData[j] = ((TexCoord2f[])paramIndexedGeometryArrayRetained.refTexCoords[b1][i1]).x;
/*       */                 
/*  2989 */                 this.vertexData[j + 1] = ((TexCoord2f[])paramIndexedGeometryArrayRetained.refTexCoords[b1][i1]).y;
/*       */                 
/*  2991 */                 j += this.texCoordStride;
/*       */               } 
/*  2993 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 16384:
/*  2997 */             for (n = k; n < m; n++) {
/*  2998 */               byte b1 = 0; j = i;
/*  2999 */               for (; b1 < this.texCoordSetCount; b1++) {
/*  3000 */                 int i1 = paramIndexedGeometryArrayRetained.indexTexCoord[b1][n];
/*  3001 */                 this.vertexData[j] = ((TexCoord3f[])paramIndexedGeometryArrayRetained.refTexCoords[b1][i1]).x;
/*       */                 
/*  3003 */                 this.vertexData[j + 1] = ((TexCoord3f[])paramIndexedGeometryArrayRetained.refTexCoords[b1][i1]).y;
/*       */                 
/*  3005 */                 this.vertexData[j + 2] = ((TexCoord3f[])paramIndexedGeometryArrayRetained.refTexCoords[b1][i1]).z;
/*       */                 
/*  3007 */                 j += this.texCoordStride;
/*       */               } 
/*  3009 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */         } 
/*       */ 
/*       */ 
/*       */       
/*       */       } 
/*  3017 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  3018 */         int n; i = 0;
/*  3019 */         switch (paramIndexedGeometryArrayRetained.vertexType & 0x8000) {
/*       */           case 32768:
/*  3021 */             for (n = k; n < m; n++) {
/*  3022 */               for (byte b1 = 0; b1 < this.vertexAttrCount; b1++) {
/*  3023 */                 int i1 = i + this.vertexAttrOffsets[b1];
/*  3024 */                 System.arraycopy(paramIndexedGeometryArrayRetained.floatRefVertexAttrs[b1], paramIndexedGeometryArrayRetained.indexVertexAttr[b1][n] * this.vertexAttrSizes[b1], this.vertexData, i1, this.vertexAttrSizes[b1]);
/*       */               } 
/*       */ 
/*       */               
/*  3028 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */         } 
/*       */       
/*       */       } 
/*  3034 */       if ((this.vertexFormat & true) != 0) {
/*  3035 */         int n; i = this.coordinateOffset;
/*  3036 */         switch (paramIndexedGeometryArrayRetained.vertexType & 0xF) {
/*       */           case 1:
/*  3038 */             for (n = k; n < m; n++) {
/*  3039 */               System.arraycopy(paramIndexedGeometryArrayRetained.floatRefCoords, paramIndexedGeometryArrayRetained.indexCoord[n] * 3, this.vertexData, i, 3);
/*       */ 
/*       */ 
/*       */               
/*  3043 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 2:
/*  3047 */             for (n = k; n < m; n++) {
/*  3048 */               int i1 = paramIndexedGeometryArrayRetained.indexCoord[n] * 3;
/*  3049 */               this.vertexData[i] = (float)paramIndexedGeometryArrayRetained.doubleRefCoords[i1];
/*  3050 */               this.vertexData[i + 1] = (float)paramIndexedGeometryArrayRetained.doubleRefCoords[i1 + 1];
/*  3051 */               this.vertexData[i + 2] = (float)paramIndexedGeometryArrayRetained.doubleRefCoords[i1 + 2];
/*  3052 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 4:
/*  3056 */             for (n = k; n < m; n++) {
/*  3057 */               int i1 = paramIndexedGeometryArrayRetained.indexCoord[n];
/*  3058 */               this.vertexData[i] = (paramIndexedGeometryArrayRetained.p3fRefCoords[i1]).x;
/*  3059 */               this.vertexData[i + 1] = (paramIndexedGeometryArrayRetained.p3fRefCoords[i1]).y;
/*  3060 */               this.vertexData[i + 2] = (paramIndexedGeometryArrayRetained.p3fRefCoords[i1]).z;
/*  3061 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 8:
/*  3065 */             for (n = k; n < m; n++) {
/*  3066 */               int i1 = paramIndexedGeometryArrayRetained.indexCoord[n];
/*  3067 */               this.vertexData[i] = (float)(paramIndexedGeometryArrayRetained.p3dRefCoords[i1]).x;
/*  3068 */               this.vertexData[i + 1] = (float)(paramIndexedGeometryArrayRetained.p3dRefCoords[i1]).y;
/*  3069 */               this.vertexData[i + 2] = (float)(paramIndexedGeometryArrayRetained.p3dRefCoords[i1]).z;
/*  3070 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */         } 
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void unIndexifyNIOBuffer(IndexedGeometryArrayRetained paramIndexedGeometryArrayRetained) {
/*  3085 */     int i = 0, j = 0;
/*  3086 */     byte b = 0;
/*  3087 */     Object object = null;
/*       */ 
/*       */     
/*  3090 */     int k = paramIndexedGeometryArrayRetained.initialIndexIndex;
/*  3091 */     int m = paramIndexedGeometryArrayRetained.initialIndexIndex + paramIndexedGeometryArrayRetained.validIndexCount;
/*       */     
/*  3093 */     if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x100) != 0) {
/*  3094 */       if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  3095 */         b = 4;
/*  3096 */       } else if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x4) != 0) {
/*  3097 */         b = 3;
/*       */       } 
/*       */       
/*  3100 */       for (int n = k; n < m; n++) {
/*  3101 */         if ((this.vertexFormat & 0x2) != 0) {
/*  3102 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryArrayRetained.indexNormal[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.normalOffset);
/*  3103 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.normalOffset, 3);
/*       */         } 
/*       */         
/*  3106 */         if (b == 4) {
/*  3107 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryArrayRetained.indexColor[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.colorOffset);
/*  3108 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.colorOffset, b);
/*  3109 */         } else if (b == 3) {
/*  3110 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryArrayRetained.indexColor[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.colorOffset);
/*  3111 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.colorOffset, b);
/*  3112 */           this.vertexData[i + this.colorOffset + 3] = 1.0F;
/*       */         } 
/*       */         
/*  3115 */         if ((this.vertexFormat & 0x460) != 0) {
/*  3116 */           int i1 = i + this.textureOffset;
/*  3117 */           for (byte b1 = 0; b1 < this.texCoordSetCount; 
/*  3118 */             b1++, i1 += this.texCoordStride) {
/*       */             
/*  3120 */             paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryArrayRetained.indexTexCoord[b1][n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.textureOffset);
/*       */             
/*  3122 */             paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i1, this.texCoordStride);
/*       */           } 
/*       */         } 
/*  3125 */         if ((this.vertexFormat & true) != 0) {
/*  3126 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryArrayRetained.indexCoord[n] * paramIndexedGeometryArrayRetained.stride + paramIndexedGeometryArrayRetained.coordinateOffset);
/*  3127 */           paramIndexedGeometryArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.coordinateOffset, 3);
/*       */         } 
/*  3129 */         i += this.stride;
/*       */       } 
/*       */     } else {
/*       */       
/*  3133 */       if ((this.vertexFormat & 0x2) != 0) {
/*  3134 */         i = this.normalOffset;
/*  3135 */         if ((paramIndexedGeometryArrayRetained.vertexType & 0xC00) != 0) {
/*  3136 */           for (int n = k; n < m; n++) {
/*  3137 */             paramIndexedGeometryArrayRetained.floatBufferRefNormals.position(paramIndexedGeometryArrayRetained.indexNormal[n] * 3);
/*  3138 */             paramIndexedGeometryArrayRetained.floatBufferRefNormals.get(this.vertexData, i, 3);
/*  3139 */             i += this.stride;
/*       */           } 
/*       */         }
/*       */       } 
/*       */       
/*  3144 */       if ((this.vertexFormat & 0x4) != 0) {
/*  3145 */         int n; i = this.colorOffset;
/*  3146 */         int i1 = 3;
/*  3147 */         if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  3148 */           i1 = 4;
/*       */         }
/*  3150 */         switch (paramIndexedGeometryArrayRetained.vertexType & 0x3F0) {
/*       */           case 16:
/*  3152 */             for (n = k; n < m; n++) {
/*  3153 */               if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  3154 */                 paramIndexedGeometryArrayRetained.floatBufferRefColors.position(paramIndexedGeometryArrayRetained.indexColor[n] * i1);
/*  3155 */                 paramIndexedGeometryArrayRetained.floatBufferRefColors.get(this.vertexData, i, 4);
/*       */               } else {
/*       */                 
/*  3158 */                 paramIndexedGeometryArrayRetained.floatBufferRefColors.position(paramIndexedGeometryArrayRetained.indexColor[n] * i1);
/*  3159 */                 paramIndexedGeometryArrayRetained.floatBufferRefColors.get(this.vertexData, i, 3);
/*  3160 */                 this.vertexData[i + 3] = 1.0F;
/*       */               } 
/*  3162 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 32:
/*  3166 */             for (n = k; n < m; n++) {
/*  3167 */               int i2 = paramIndexedGeometryArrayRetained.indexColor[n] * i1;
/*  3168 */               this.vertexData[i] = (paramIndexedGeometryArrayRetained.byteBufferRefColors.get(i2) & 0xFF) * 0.003921569F;
/*  3169 */               this.vertexData[i + 1] = (paramIndexedGeometryArrayRetained.byteBufferRefColors.get(i2 + 1) & 0xFF) * 0.003921569F;
/*  3170 */               this.vertexData[i + 2] = (paramIndexedGeometryArrayRetained.byteBufferRefColors.get(i2 + 2) & 0xFF) * 0.003921569F;
/*       */               
/*  3172 */               if ((paramIndexedGeometryArrayRetained.vertexFormat & 0x8) != 0) {
/*  3173 */                 this.vertexData[i + 3] = (paramIndexedGeometryArrayRetained.byteBufferRefColors.get(i2 + 3) & 0xFF) * 0.003921569F;
/*       */               } else {
/*       */                 
/*  3176 */                 this.vertexData[i + 3] = 1.0F;
/*       */               } 
/*  3178 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */         } 
/*       */ 
/*       */ 
/*       */       
/*       */       } 
/*  3186 */       if ((this.vertexFormat & 0x460) != 0) {
/*  3187 */         i = this.textureOffset;
/*       */         
/*  3189 */         if ((paramIndexedGeometryArrayRetained.vertexType & 0x7000) != 0) {
/*  3190 */           for (int n = k; n < m; n++) {
/*  3191 */             byte b1 = 0; j = i;
/*  3192 */             for (; b1 < this.texCoordSetCount; b1++) {
/*  3193 */               FloatBufferWrapper floatBufferWrapper = (FloatBufferWrapper)((J3DBuffer)paramIndexedGeometryArrayRetained.refTexCoordsBuffer[b1]).getBufferImpl();
/*  3194 */               floatBufferWrapper.position(paramIndexedGeometryArrayRetained.indexTexCoord[b1][n] * this.texCoordStride);
/*  3195 */               floatBufferWrapper.get(this.vertexData, j, this.texCoordStride);
/*  3196 */               j += this.texCoordStride;
/*       */             } 
/*  3198 */             i += this.stride;
/*       */           } 
/*       */         }
/*       */       } 
/*       */       
/*  3203 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  3204 */         i = 0;
/*  3205 */         if ((paramIndexedGeometryArrayRetained.vertexType & 0x8000) == 32768) {
/*  3206 */           for (int n = k; n < m; n++) {
/*  3207 */             for (byte b1 = 0; b1 < this.vertexAttrCount; b1++) {
/*  3208 */               int i1 = i + this.vertexAttrOffsets[b1];
/*  3209 */               FloatBufferWrapper floatBufferWrapper = paramIndexedGeometryArrayRetained.floatBufferRefVertexAttrs[b1];
/*  3210 */               floatBufferWrapper.position(paramIndexedGeometryArrayRetained.indexVertexAttr[b1][n] * this.vertexAttrSizes[b1]);
/*  3211 */               floatBufferWrapper.get(this.vertexData, i1, this.vertexAttrSizes[b1]);
/*       */             } 
/*  3213 */             i += this.stride;
/*       */           } 
/*       */         }
/*       */       } 
/*       */       
/*  3218 */       if ((this.vertexFormat & true) != 0) {
/*  3219 */         int n; i = this.coordinateOffset;
/*  3220 */         switch (paramIndexedGeometryArrayRetained.vertexType & 0xF) {
/*       */           case 1:
/*  3222 */             for (n = k; n < m; n++) {
/*  3223 */               paramIndexedGeometryArrayRetained.floatBufferRefCoords.position(paramIndexedGeometryArrayRetained.indexCoord[n] * 3);
/*  3224 */               paramIndexedGeometryArrayRetained.floatBufferRefCoords.get(this.vertexData, i, 3);
/*  3225 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */           case 2:
/*  3229 */             for (n = k; n < m; n++) {
/*  3230 */               int i1 = paramIndexedGeometryArrayRetained.indexCoord[n] * 3;
/*  3231 */               this.vertexData[i] = (float)paramIndexedGeometryArrayRetained.doubleBufferRefCoords.get(i1);
/*  3232 */               this.vertexData[i + 1] = (float)paramIndexedGeometryArrayRetained.doubleBufferRefCoords.get(i1 + 1);
/*  3233 */               this.vertexData[i + 2] = (float)paramIndexedGeometryArrayRetained.doubleBufferRefCoords.get(i1 + 2);
/*  3234 */               i += this.stride;
/*       */             } 
/*       */             break;
/*       */         } 
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int stride() {
/*  3253 */     int i = 0;
/*       */     
/*  3255 */     if ((this.vertexFormat & true) != 0) i += true; 
/*  3256 */     if ((this.vertexFormat & 0x2) != 0) i += true;
/*       */     
/*  3258 */     if ((this.vertexFormat & 0x4) != 0) {
/*  3259 */       if ((this.vertexFormat & 0x80) == 0) {
/*       */         
/*  3261 */         i += true;
/*       */       }
/*  3263 */       else if ((this.vertexFormat & 0x8) == 0) {
/*  3264 */         i += true;
/*       */       } else {
/*       */         
/*  3267 */         i += true;
/*       */       } 
/*       */     }
/*       */ 
/*       */     
/*  3272 */     if ((this.vertexFormat & 0x460) != 0) {
/*       */       
/*  3274 */       if ((this.vertexFormat & 0x20) != 0) {
/*       */         
/*  3276 */         this.texCoordStride = 2;
/*  3277 */       } else if ((this.vertexFormat & 0x40) != 0) {
/*       */         
/*  3279 */         this.texCoordStride = 3;
/*  3280 */       } else if ((this.vertexFormat & 0x400) != 0) {
/*       */         
/*  3282 */         this.texCoordStride = 4;
/*       */       } 
/*       */       
/*  3285 */       i += this.texCoordStride * this.texCoordSetCount;
/*       */     } 
/*       */     
/*  3288 */     if ((this.vertexFormat & 0x1000) != 0) {
/*  3289 */       i += this.vertexAttrStride;
/*       */     }
/*       */ 
/*       */     
/*  3293 */     return i;
/*       */   }
/*       */ 
/*       */   
/*       */   int[] texCoordSetMapOffset() {
/*  3298 */     if (this.texCoordSetMap == null) {
/*  3299 */       return null;
/*       */     }
/*  3301 */     this.texCoordSetMapOffset = new int[this.texCoordSetMap.length];
/*  3302 */     for (byte b = 0; b < this.texCoordSetMap.length; b++) {
/*  3303 */       if (this.texCoordSetMap[b] == -1) {
/*  3304 */         this.texCoordSetMapOffset[b] = -1;
/*       */       } else {
/*  3306 */         this.texCoordSetMapOffset[b] = this.texCoordSetMap[b] * this.texCoordStride;
/*       */       } 
/*       */     } 
/*  3309 */     return this.texCoordSetMapOffset;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int vertexAttrStride() {
/*  3318 */     int i = 0;
/*  3319 */     for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  3320 */       i += this.vertexAttrSizes[b];
/*       */     }
/*  3322 */     return i;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int[] vertexAttrOffsets() {
/*       */     int[] arrayOfInt;
/*  3336 */     if (this.vertexAttrCount > 0) {
/*  3337 */       arrayOfInt = new int[this.vertexAttrCount];
/*       */     } else {
/*       */       
/*  3340 */       arrayOfInt = new int[1];
/*       */     } 
/*  3342 */     arrayOfInt[0] = 0;
/*  3343 */     for (byte b = 1; b < this.vertexAttrCount; b++) {
/*  3344 */       arrayOfInt[b] = arrayOfInt[b - true] + this.vertexAttrSizes[b - true];
/*       */     }
/*       */     
/*  3347 */     return arrayOfInt;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int textureOffset() {
/*  3358 */     int i = this.vertexAttrOffsets[0];
/*       */     
/*  3360 */     if ((this.vertexFormat & 0x1000) != 0) {
/*  3361 */       i += this.vertexAttrStride;
/*       */     }
/*       */     
/*  3364 */     return i;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int colorOffset() {
/*  3376 */     int i = this.textureOffset;
/*       */     
/*  3378 */     if ((this.vertexFormat & 0x20) != 0) {
/*  3379 */       i += 2 * this.texCoordSetCount;
/*  3380 */     } else if ((this.vertexFormat & 0x40) != 0) {
/*  3381 */       i += 3 * this.texCoordSetCount;
/*  3382 */     } else if ((this.vertexFormat & 0x400) != 0) {
/*  3383 */       i += 4 * this.texCoordSetCount;
/*       */     } 
/*  3385 */     return i;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int normalOffset() {
/*  3396 */     int i = this.colorOffset;
/*       */     
/*  3398 */     if ((this.vertexFormat & 0x4) != 0) {
/*  3399 */       if ((this.vertexFormat & 0x80) == 0) {
/*  3400 */         i += 4;
/*       */       }
/*  3402 */       else if ((this.vertexFormat & 0x8) == 0) {
/*  3403 */         i += 3;
/*       */       } else {
/*       */         
/*  3406 */         i += 4;
/*       */       } 
/*       */     }
/*       */     
/*  3410 */     return i;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   int coordinateOffset() {
/*  3420 */     int i = this.normalOffset;
/*       */     
/*  3422 */     if ((this.vertexFormat & 0x2) != 0) i += 3; 
/*  3423 */     return i;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  3431 */   int getVertexCount() { return this.vertexCount; }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  3439 */   int getVertexFormat() { return this.vertexFormat; }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  3450 */   int getVertexAttrCount() { return this.vertexAttrCount; }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getVertexAttrSizes(int[] paramArrayOfInt) {
/*  3463 */     for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  3464 */       paramArrayOfInt[b] = this.vertexAttrSizes[b];
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void sendDataChangedMessage(boolean paramBoolean) {
/*  3478 */     synchronized (this.liveStateLock) {
/*  3479 */       if (this.source != null && this.source.isLive()) {
/*       */         byte b;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3486 */         char c = '';
/*       */ 
/*       */         
/*  3489 */         c |= 0x400;
/*       */         
/*  3491 */         synchronized (this.universeList) {
/*  3492 */           int i = this.universeList.size();
/*  3493 */           J3dMessage[] arrayOfJ3dMessage = new J3dMessage[i];
/*       */           
/*  3495 */           b = 0;
/*       */           
/*  3497 */           for (byte b1 = 0; b1 < i; b1++, b++) {
/*  3498 */             this.gaList.clear();
/*       */             
/*  3500 */             ArrayList arrayList = (ArrayList)this.userLists.get(b1); byte b2;
/*  3501 */             for (b2 = 0; b2 < arrayList.size(); b2++) {
/*  3502 */               Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList.get(b2);
/*  3503 */               LeafRetained leafRetained = (LeafRetained)shape3DRetained.sourceNode;
/*       */               
/*  3505 */               if (paramBoolean && leafRetained.boundsAutoCompute) {
/*  3506 */                 leafRetained.boundsDirty = true;
/*       */               }
/*       */             } 
/*       */             
/*  3510 */             for (b2 = 0; b2 < arrayList.size(); b2++) {
/*  3511 */               Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList.get(b2);
/*  3512 */               LeafRetained leafRetained = (LeafRetained)shape3DRetained.sourceNode;
/*  3513 */               if (leafRetained.boundsDirty) {
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*  3518 */                 leafRetained.updateBounds();
/*  3519 */                 leafRetained.boundsDirty = false;
/*       */               } 
/*  3521 */               this.gaList.add(Shape3DRetained.getGeomAtom(shape3DRetained));
/*       */             } 
/*       */             
/*  3524 */             arrayOfJ3dMessage[b] = new J3dMessage();
/*       */             
/*  3526 */             (arrayOfJ3dMessage[b]).type = 17;
/*       */             
/*  3528 */             (arrayOfJ3dMessage[b]).threads = c;
/*  3529 */             (arrayOfJ3dMessage[b]).args[0] = this.gaList.toArray();
/*  3530 */             (arrayOfJ3dMessage[b]).args[1] = this;
/*  3531 */             (arrayOfJ3dMessage[b]).args[2] = null;
/*  3532 */             (arrayOfJ3dMessage[b]).args[3] = new Integer(this.changedFrequent);
/*  3533 */             (arrayOfJ3dMessage[b]).universe = (VirtualUniverse)this.universeList.get(b1);
/*       */           } 
/*  3535 */           VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*       */         } 
/*       */         
/*  3538 */         if (this.morphUniverseList != null) {
/*  3539 */           synchronized (this.morphUniverseList) {
/*  3540 */             int i = this.morphUniverseList.size();
/*       */ 
/*       */             
/*  3543 */             if (i > 0) {
/*  3544 */               synchronized (this.morphUniverseList) {
/*  3545 */                 for (byte b1 = 0; b1 < i; b1++, b++) {
/*  3546 */                   ArrayList arrayList = (ArrayList)this.morphUserLists.get(b1);
/*  3547 */                   for (byte b2 = 0; b2 < arrayList.size(); b2++) {
/*  3548 */                     MorphRetained morphRetained = (MorphRetained)arrayList.get(b2);
/*  3549 */                     morphRetained.updateMorphedGeometryArray(this, paramBoolean);
/*       */                   } 
/*       */                 } 
/*       */               } 
/*       */             }
/*       */           } 
/*       */         }
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinate(int paramInt, float[] paramArrayOfFloat) {
/*  3567 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */     
/*  3569 */     this.geomLock.getLock();
/*  3570 */     this.dirtyFlag |= 0x1;
/*       */     
/*  3572 */     this.vertexData[i] = paramArrayOfFloat[0];
/*  3573 */     this.vertexData[i + 1] = paramArrayOfFloat[1];
/*  3574 */     this.vertexData[i + 2] = paramArrayOfFloat[2];
/*       */     
/*  3576 */     this.geomLock.unLock();
/*       */     
/*  3578 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3581 */     if (!this.source.isLive()) {
/*  3582 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3587 */     processCoordsChanged(false);
/*  3588 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinate(int paramInt, double[] paramArrayOfDouble) {
/*  3599 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */ 
/*       */ 
/*       */     
/*  3603 */     this.geomLock.getLock();
/*  3604 */     this.dirtyFlag |= 0x1;
/*  3605 */     this.vertexData[i] = (float)paramArrayOfDouble[0];
/*  3606 */     this.vertexData[i + 1] = (float)paramArrayOfDouble[1];
/*  3607 */     this.vertexData[i + 2] = (float)paramArrayOfDouble[2];
/*  3608 */     this.geomLock.unLock();
/*       */     
/*  3610 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3613 */     if (!this.source.isLive()) {
/*  3614 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3619 */     processCoordsChanged(false);
/*  3620 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinate(int paramInt, Point3f paramPoint3f) {
/*  3630 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */     
/*  3632 */     this.geomLock.getLock();
/*  3633 */     this.dirtyFlag |= 0x1;
/*  3634 */     this.vertexData[i] = paramPoint3f.x;
/*  3635 */     this.vertexData[i + 1] = paramPoint3f.y;
/*  3636 */     this.vertexData[i + 2] = paramPoint3f.z;
/*       */     
/*  3638 */     this.geomLock.unLock();
/*       */     
/*  3640 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3643 */     if (!this.source.isLive()) {
/*  3644 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3649 */     processCoordsChanged(false);
/*  3650 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinate(int paramInt, Point3d paramPoint3d) {
/*  3660 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */     
/*  3662 */     this.geomLock.getLock();
/*       */     
/*  3664 */     this.dirtyFlag |= 0x1;
/*  3665 */     this.vertexData[i] = (float)paramPoint3d.x;
/*  3666 */     this.vertexData[i + 1] = (float)paramPoint3d.y;
/*  3667 */     this.vertexData[i + 2] = (float)paramPoint3d.z;
/*       */     
/*  3669 */     this.geomLock.unLock();
/*       */     
/*  3671 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3674 */     if (!this.source.isLive()) {
/*  3675 */       this.boundsDirty = true;
/*       */ 
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3681 */     processCoordsChanged(false);
/*  3682 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt, float[] paramArrayOfFloat) {
/*  3692 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  3693 */     int k = paramArrayOfFloat.length;
/*       */     
/*  3695 */     this.geomLock.getLock();
/*  3696 */     this.dirtyFlag |= 0x1; boolean bool;
/*       */     int j;
/*  3698 */     for (bool = false, j = i; bool < k; bool += true, j += this.stride) {
/*       */       
/*  3700 */       this.vertexData[j] = paramArrayOfFloat[bool];
/*  3701 */       this.vertexData[j + 1] = paramArrayOfFloat[bool + true];
/*  3702 */       this.vertexData[j + 2] = paramArrayOfFloat[bool + 2];
/*       */     } 
/*       */     
/*  3705 */     this.geomLock.unLock();
/*  3706 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3709 */     if (!this.source.isLive()) {
/*  3710 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3715 */     processCoordsChanged(false);
/*       */     
/*  3717 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt, double[] paramArrayOfDouble) {
/*  3728 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  3729 */     int k = paramArrayOfDouble.length;
/*       */     
/*  3731 */     this.geomLock.getLock();
/*  3732 */     this.dirtyFlag |= 0x1; boolean bool;
/*       */     int j;
/*  3734 */     for (bool = false, j = i; bool < k; bool += true, j += this.stride) {
/*       */       
/*  3736 */       this.vertexData[j] = (float)paramArrayOfDouble[bool];
/*  3737 */       this.vertexData[j + 1] = (float)paramArrayOfDouble[bool + true];
/*  3738 */       this.vertexData[j + 2] = (float)paramArrayOfDouble[bool + 2];
/*       */     } 
/*       */     
/*  3741 */     this.geomLock.unLock();
/*       */     
/*  3743 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3746 */     if (!this.source.isLive()) {
/*  3747 */       this.boundsDirty = true;
/*       */ 
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3753 */     processCoordsChanged(false);
/*       */     
/*  3755 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt, Point3f[] paramArrayOfPoint3f) {
/*  3765 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  3766 */     int k = paramArrayOfPoint3f.length;
/*       */     
/*  3768 */     this.geomLock.getLock();
/*  3769 */     this.dirtyFlag |= 0x1; byte b;
/*       */     int j;
/*  3771 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  3773 */       this.vertexData[j] = (paramArrayOfPoint3f[b]).x;
/*  3774 */       this.vertexData[j + 1] = (paramArrayOfPoint3f[b]).y;
/*  3775 */       this.vertexData[j + 2] = (paramArrayOfPoint3f[b]).z;
/*       */     } 
/*       */     
/*  3778 */     this.geomLock.unLock();
/*       */     
/*  3780 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3783 */     if (!this.source.isLive()) {
/*  3784 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3789 */     processCoordsChanged(false);
/*       */     
/*  3791 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt, Point3d[] paramArrayOfPoint3d) {
/*  3802 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  3803 */     int k = paramArrayOfPoint3d.length;
/*       */     
/*  3805 */     this.geomLock.getLock();
/*  3806 */     this.dirtyFlag |= 0x1; byte b;
/*       */     int j;
/*  3808 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  3810 */       this.vertexData[j] = (float)(paramArrayOfPoint3d[b]).x;
/*  3811 */       this.vertexData[j + 1] = (float)(paramArrayOfPoint3d[b]).y;
/*  3812 */       this.vertexData[j + 2] = (float)(paramArrayOfPoint3d[b]).z;
/*       */     } 
/*       */     
/*  3815 */     this.geomLock.unLock();
/*       */     
/*  3817 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3820 */     if (!this.source.isLive()) {
/*  3821 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3826 */     processCoordsChanged(false);
/*       */     
/*  3828 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) {
/*  3842 */     int i = this.stride * paramInt1 + this.coordinateOffset;
/*       */ 
/*       */     
/*  3845 */     this.geomLock.getLock();
/*  3846 */     this.dirtyFlag |= 0x1; int j, k;
/*  3847 */     for (j = paramInt2 * 3, k = i; j < (paramInt2 + paramInt3) * 3; 
/*  3848 */       j += 3, k += this.stride) {
/*  3849 */       this.vertexData[k] = paramArrayOfFloat[j];
/*  3850 */       this.vertexData[k + 1] = paramArrayOfFloat[j + 1];
/*  3851 */       this.vertexData[k + 2] = paramArrayOfFloat[j + 2];
/*       */     } 
/*       */     
/*  3854 */     this.geomLock.unLock();
/*  3855 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3858 */     if (!this.source.isLive()) {
/*  3859 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3864 */     processCoordsChanged(false);
/*       */     
/*  3866 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3) {
/*  3879 */     int i = this.stride * paramInt1 + this.coordinateOffset;
/*       */ 
/*       */     
/*  3882 */     this.geomLock.getLock();
/*  3883 */     this.dirtyFlag |= 0x1;
/*       */     int j, k;
/*  3885 */     for (j = paramInt2 * 3, k = i; j < (paramInt2 + paramInt3) * 3; 
/*  3886 */       j += 3, k += this.stride) {
/*  3887 */       this.vertexData[k] = (float)paramArrayOfDouble[j];
/*  3888 */       this.vertexData[k + 1] = (float)paramArrayOfDouble[j + 1];
/*  3889 */       this.vertexData[k + 2] = (float)paramArrayOfDouble[j + 2];
/*       */     } 
/*       */     
/*  3892 */     this.geomLock.unLock();
/*       */     
/*  3894 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3897 */     if (!this.source.isLive()) {
/*  3898 */       this.boundsDirty = true;
/*       */ 
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3904 */     processCoordsChanged(false);
/*       */     
/*  3906 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt1, Point3f[] paramArrayOfPoint3f, int paramInt2, int paramInt3) {
/*  3920 */     int i = this.stride * paramInt1 + this.coordinateOffset;
/*       */ 
/*       */     
/*  3923 */     this.geomLock.getLock();
/*  3924 */     this.dirtyFlag |= 0x1;
/*       */     int j, k;
/*  3926 */     for (j = paramInt2, k = i; j < paramInt2 + paramInt3; j++, k += this.stride) {
/*  3927 */       this.vertexData[k] = (paramArrayOfPoint3f[j]).x;
/*  3928 */       this.vertexData[k + 1] = (paramArrayOfPoint3f[j]).y;
/*  3929 */       this.vertexData[k + 2] = (paramArrayOfPoint3f[j]).z;
/*       */     } 
/*       */     
/*  3932 */     this.geomLock.unLock();
/*       */ 
/*       */     
/*  3935 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3938 */     if (!this.source.isLive()) {
/*  3939 */       this.boundsDirty = true;
/*       */ 
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3945 */     processCoordsChanged(false);
/*       */     
/*  3947 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordinates(int paramInt1, Point3d[] paramArrayOfPoint3d, int paramInt2, int paramInt3) {
/*  3961 */     int i = this.stride * paramInt1 + this.coordinateOffset;
/*       */ 
/*       */     
/*  3964 */     this.geomLock.getLock();
/*  3965 */     this.dirtyFlag |= 0x1;
/*       */     int j, k;
/*  3967 */     for (j = paramInt2, k = i; j < paramInt2 + paramInt3; j++, k += this.stride) {
/*  3968 */       this.vertexData[k] = (float)(paramArrayOfPoint3d[j]).x;
/*  3969 */       this.vertexData[k + 1] = (float)(paramArrayOfPoint3d[j]).y;
/*  3970 */       this.vertexData[k + 2] = (float)(paramArrayOfPoint3d[j]).z;
/*       */     } 
/*       */     
/*  3973 */     this.geomLock.unLock();
/*       */ 
/*       */     
/*  3976 */     if (this.inUpdater || this.source == null) {
/*       */       return;
/*       */     }
/*  3979 */     if (!this.source.isLive()) {
/*  3980 */       this.boundsDirty = true;
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  3985 */     processCoordsChanged(false);
/*       */     
/*  3987 */     sendDataChangedMessage(true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColor(int paramInt, float[] paramArrayOfFloat) {
/*  3997 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  3999 */     this.geomLock.getLock();
/*  4000 */     this.dirtyFlag |= 0x4;
/*  4001 */     this.colorChanged = 65535;
/*  4002 */     this.vertexData[i] = paramArrayOfFloat[0];
/*  4003 */     this.vertexData[i + 1] = paramArrayOfFloat[1];
/*  4004 */     this.vertexData[i + 2] = paramArrayOfFloat[2];
/*  4005 */     if ((this.vertexFormat & 0x8) != 0) {
/*  4006 */       this.vertexData[i + 3] = paramArrayOfFloat[3] * this.lastAlpha[0];
/*       */     } else {
/*  4008 */       this.vertexData[i + 3] = this.lastAlpha[0];
/*       */     } 
/*  4010 */     if (this.source == null || !this.source.isLive()) {
/*  4011 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4015 */     this.geomLock.unLock();
/*  4016 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColor(int paramInt, byte[] paramArrayOfByte) {
/*  4027 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  4029 */     this.geomLock.getLock();
/*  4030 */     this.dirtyFlag |= 0x4;
/*  4031 */     this.colorChanged = 65535;
/*  4032 */     this.vertexData[i] = (paramArrayOfByte[0] & 0xFF) * 0.003921569F;
/*  4033 */     this.vertexData[i + 1] = (paramArrayOfByte[1] & 0xFF) * 0.003921569F;
/*  4034 */     this.vertexData[i + 2] = (paramArrayOfByte[2] & 0xFF) * 0.003921569F;
/*  4035 */     if ((this.vertexFormat & 0x8) != 0) {
/*  4036 */       this.vertexData[i + 3] = (paramArrayOfByte[3] & 0xFF) * 0.003921569F * this.lastAlpha[0];
/*       */     } else {
/*  4038 */       this.vertexData[i + 3] = this.lastAlpha[0];
/*       */     } 
/*  4040 */     if (this.source == null || !this.source.isLive()) {
/*  4041 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4045 */     this.geomLock.unLock();
/*  4046 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColor(int paramInt, Color3f paramColor3f) {
/*  4057 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  4059 */     this.geomLock.getLock();
/*  4060 */     this.dirtyFlag |= 0x4;
/*  4061 */     this.colorChanged = 65535;
/*  4062 */     this.vertexData[i] = paramColor3f.x;
/*  4063 */     this.vertexData[i + 1] = paramColor3f.y;
/*  4064 */     this.vertexData[i + 2] = paramColor3f.z;
/*  4065 */     this.vertexData[i + 3] = this.lastAlpha[0];
/*       */     
/*  4067 */     if (this.source == null || !this.source.isLive()) {
/*  4068 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4072 */     this.geomLock.unLock();
/*  4073 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColor(int paramInt, Color4f paramColor4f) {
/*  4084 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  4086 */     this.geomLock.getLock();
/*  4087 */     this.dirtyFlag |= 0x4;
/*  4088 */     this.colorChanged = 65535;
/*  4089 */     this.vertexData[i] = paramColor4f.x;
/*  4090 */     this.vertexData[i + 1] = paramColor4f.y;
/*  4091 */     this.vertexData[i + 2] = paramColor4f.z;
/*  4092 */     this.vertexData[i + 3] = paramColor4f.w * this.lastAlpha[0];
/*       */     
/*  4094 */     if (this.source == null || !this.source.isLive()) {
/*  4095 */       this.geomLock.unLock();
/*       */       return;
/*       */     } 
/*  4098 */     this.geomLock.unLock();
/*  4099 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColor(int paramInt, Color3b paramColor3b) {
/*  4110 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  4112 */     this.geomLock.getLock();
/*  4113 */     this.dirtyFlag |= 0x4;
/*  4114 */     this.colorChanged = 65535;
/*  4115 */     this.vertexData[i] = (paramColor3b.x & 0xFF) * 0.003921569F;
/*  4116 */     this.vertexData[i + 1] = (paramColor3b.y & 0xFF) * 0.003921569F;
/*  4117 */     this.vertexData[i + 2] = (paramColor3b.z & 0xFF) * 0.003921569F;
/*  4118 */     this.vertexData[i + 3] = this.lastAlpha[0];
/*       */     
/*  4120 */     if (this.source == null || !this.source.isLive()) {
/*  4121 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4125 */     this.geomLock.unLock();
/*  4126 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColor(int paramInt, Color4b paramColor4b) {
/*  4137 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  4139 */     this.geomLock.getLock();
/*  4140 */     this.dirtyFlag |= 0x4;
/*  4141 */     this.colorChanged = 65535;
/*  4142 */     this.vertexData[i] = (paramColor4b.x * 255) * 0.003921569F;
/*  4143 */     this.vertexData[i + 1] = (paramColor4b.y * 255) * 0.003921569F;
/*  4144 */     this.vertexData[i + 2] = (paramColor4b.z * 255) * 0.003921569F;
/*  4145 */     this.vertexData[i + 3] = (paramColor4b.w & 0xFF) * 0.003921569F * this.lastAlpha[0];
/*  4146 */     if (this.source == null || !this.source.isLive()) {
/*  4147 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4151 */     this.geomLock.unLock();
/*  4152 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt, float[] paramArrayOfFloat) {
/*  4163 */     int i = this.stride * paramInt + this.colorOffset;
/*  4164 */     int j = paramArrayOfFloat.length;
/*       */     
/*  4166 */     this.geomLock.getLock();
/*  4167 */     this.dirtyFlag |= 0x4;
/*  4168 */     this.colorChanged = 65535;
/*       */     
/*  4170 */     if ((this.vertexFormat & 0x8) != 0) {
/*       */       boolean bool; int k;
/*  4172 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  4174 */         this.vertexData[k] = paramArrayOfFloat[bool];
/*  4175 */         this.vertexData[k + 1] = paramArrayOfFloat[bool + true];
/*  4176 */         this.vertexData[k + 2] = paramArrayOfFloat[bool + 2];
/*  4177 */         this.vertexData[k + 3] = paramArrayOfFloat[bool + 3] * this.lastAlpha[0];
/*       */       } 
/*       */     } else {
/*       */       boolean bool;
/*       */       int k;
/*  4182 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  4184 */         this.vertexData[k] = paramArrayOfFloat[bool];
/*  4185 */         this.vertexData[k + 1] = paramArrayOfFloat[bool + true];
/*  4186 */         this.vertexData[k + 2] = paramArrayOfFloat[bool + 2];
/*  4187 */         this.vertexData[k + 3] = this.lastAlpha[0];
/*       */       } 
/*       */     } 
/*  4190 */     if (this.source == null || !this.source.isLive()) {
/*  4191 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4195 */     this.geomLock.unLock();
/*  4196 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt, byte[] paramArrayOfByte) {
/*  4207 */     int i = this.stride * paramInt + this.colorOffset;
/*  4208 */     int j = paramArrayOfByte.length;
/*       */     
/*  4210 */     this.geomLock.getLock();
/*  4211 */     this.dirtyFlag |= 0x4;
/*  4212 */     this.colorChanged = 65535;
/*       */     
/*  4214 */     if ((this.vertexFormat & 0x8) != 0) {
/*       */       boolean bool; int k;
/*  4216 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  4218 */         this.vertexData[k] = (paramArrayOfByte[bool] & 0xFF) * 0.003921569F;
/*  4219 */         this.vertexData[k + 1] = (paramArrayOfByte[bool + true] & 0xFF) * 0.003921569F;
/*  4220 */         this.vertexData[k + 2] = (paramArrayOfByte[bool + 2] & 0xFF) * 0.003921569F;
/*  4221 */         this.vertexData[k + 3] = (paramArrayOfByte[bool + 3] & 0xFF) * 0.003921569F * this.lastAlpha[0];
/*       */       } 
/*       */     } else {
/*       */       boolean bool;
/*       */       int k;
/*  4226 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  4228 */         this.vertexData[k] = (paramArrayOfByte[bool] & 0xFF) * 0.003921569F;
/*  4229 */         this.vertexData[k + 1] = (paramArrayOfByte[bool + true] & 0xFF) * 0.003921569F;
/*  4230 */         this.vertexData[k + 2] = (paramArrayOfByte[bool + 2] & 0xFF) * 0.003921569F;
/*  4231 */         this.vertexData[k + 3] = this.lastAlpha[0];
/*       */       } 
/*       */     } 
/*  4234 */     if (this.source == null || !this.source.isLive()) {
/*  4235 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4239 */     this.geomLock.unLock();
/*  4240 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt, Color3f[] paramArrayOfColor3f) {
/*  4251 */     int i = this.stride * paramInt + this.colorOffset;
/*  4252 */     int k = paramArrayOfColor3f.length;
/*       */     
/*  4254 */     this.geomLock.getLock();
/*  4255 */     this.dirtyFlag |= 0x4;
/*  4256 */     this.colorChanged = 65535; byte b;
/*       */     int j;
/*  4258 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  4260 */       this.vertexData[j] = (paramArrayOfColor3f[b]).x;
/*  4261 */       this.vertexData[j + 1] = (paramArrayOfColor3f[b]).y;
/*  4262 */       this.vertexData[j + 2] = (paramArrayOfColor3f[b]).z;
/*  4263 */       this.vertexData[j + 3] = this.lastAlpha[0];
/*       */     } 
/*  4265 */     if (this.source == null || !this.source.isLive()) {
/*  4266 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4270 */     this.geomLock.unLock();
/*  4271 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt, Color4f[] paramArrayOfColor4f) {
/*  4282 */     int i = this.stride * paramInt + this.colorOffset;
/*  4283 */     int k = paramArrayOfColor4f.length;
/*       */     
/*  4285 */     this.geomLock.getLock();
/*  4286 */     this.dirtyFlag |= 0x4;
/*  4287 */     this.colorChanged = 65535; byte b;
/*       */     int j;
/*  4289 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  4291 */       this.vertexData[j] = (paramArrayOfColor4f[b]).x;
/*  4292 */       this.vertexData[j + 1] = (paramArrayOfColor4f[b]).y;
/*  4293 */       this.vertexData[j + 2] = (paramArrayOfColor4f[b]).z;
/*  4294 */       this.vertexData[j + 3] = (paramArrayOfColor4f[b]).w * this.lastAlpha[0];
/*       */     } 
/*  4296 */     if (this.source == null || !this.source.isLive()) {
/*  4297 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4301 */     this.geomLock.unLock();
/*  4302 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt, Color3b[] paramArrayOfColor3b) {
/*  4313 */     int i = this.stride * paramInt + this.colorOffset;
/*  4314 */     int k = paramArrayOfColor3b.length;
/*       */     
/*  4316 */     this.geomLock.getLock();
/*  4317 */     this.dirtyFlag |= 0x4;
/*  4318 */     this.colorChanged = 65535; byte b; int j;
/*  4319 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  4321 */       this.vertexData[j] = ((paramArrayOfColor3b[b]).x & 0xFF) * 0.003921569F;
/*  4322 */       this.vertexData[j + 1] = ((paramArrayOfColor3b[b]).y & 0xFF) * 0.003921569F;
/*  4323 */       this.vertexData[j + 2] = ((paramArrayOfColor3b[b]).z & 0xFF) * 0.003921569F;
/*  4324 */       this.vertexData[j + 3] = this.lastAlpha[0];
/*       */     } 
/*  4326 */     if (this.source == null || !this.source.isLive()) {
/*  4327 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4331 */     this.geomLock.unLock();
/*  4332 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt, Color4b[] paramArrayOfColor4b) {
/*  4343 */     int i = this.stride * paramInt + this.colorOffset;
/*  4344 */     int k = paramArrayOfColor4b.length;
/*       */     
/*  4346 */     this.geomLock.getLock();
/*  4347 */     this.dirtyFlag |= 0x4;
/*  4348 */     this.colorChanged = 65535; byte b;
/*       */     int j;
/*  4350 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  4352 */       this.vertexData[j] = ((paramArrayOfColor4b[b]).x & 0xFF) * 0.003921569F;
/*  4353 */       this.vertexData[j + 1] = ((paramArrayOfColor4b[b]).y & 0xFF) * 0.003921569F;
/*  4354 */       this.vertexData[j + 2] = ((paramArrayOfColor4b[b]).z & 0xFF) * 0.003921569F;
/*  4355 */       this.vertexData[j + 3] = ((paramArrayOfColor4b[b]).w & 0xFF) * 0.003921569F * this.lastAlpha[0];
/*       */     } 
/*  4357 */     if (this.source == null || !this.source.isLive()) {
/*  4358 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4362 */     this.geomLock.unLock();
/*  4363 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) {
/*  4377 */     int i = this.stride * paramInt1 + this.colorOffset;
/*       */ 
/*       */     
/*  4380 */     this.geomLock.getLock();
/*  4381 */     this.dirtyFlag |= 0x4;
/*  4382 */     this.colorChanged = 65535;
/*       */     
/*  4384 */     if ((this.vertexFormat & 0x8) != 0) {
/*  4385 */       int j; int k; for (j = paramInt2 * 4, k = i; j < (paramInt2 + paramInt3) * 4; 
/*  4386 */         j += 4, k += this.stride) {
/*  4387 */         this.vertexData[k] = paramArrayOfFloat[j];
/*  4388 */         this.vertexData[k + 1] = paramArrayOfFloat[j + 1];
/*  4389 */         this.vertexData[k + 2] = paramArrayOfFloat[j + 2];
/*  4390 */         this.vertexData[k + 3] = paramArrayOfFloat[j + 3] * this.lastAlpha[0];
/*       */       } 
/*       */     } else {
/*  4393 */       int j; int k; for (j = paramInt2 * 3, k = i; j < (paramInt2 + paramInt3) * 3; 
/*  4394 */         j += 3, k += this.stride) {
/*  4395 */         this.vertexData[k] = paramArrayOfFloat[j];
/*  4396 */         this.vertexData[k + 1] = paramArrayOfFloat[j + 1];
/*  4397 */         this.vertexData[k + 2] = paramArrayOfFloat[j + 2];
/*  4398 */         this.vertexData[k + 3] = this.lastAlpha[0];
/*       */       } 
/*       */     } 
/*  4401 */     if (this.source == null || !this.source.isLive()) {
/*  4402 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4406 */     this.geomLock.unLock();
/*  4407 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3) {
/*  4421 */     int i = this.stride * paramInt1 + this.colorOffset;
/*       */ 
/*       */     
/*  4424 */     this.geomLock.getLock();
/*  4425 */     this.dirtyFlag |= 0x4;
/*  4426 */     this.colorChanged = 65535;
/*       */     
/*  4428 */     if ((this.vertexFormat & 0x8) != 0) {
/*  4429 */       int j; int k; for (j = paramInt2 * 4, k = i; j < (paramInt2 + paramInt3) * 4; 
/*  4430 */         j += 4, k += this.stride) {
/*  4431 */         this.vertexData[k] = (paramArrayOfByte[j] & 0xFF) * 0.003921569F;
/*  4432 */         this.vertexData[k + 1] = (paramArrayOfByte[j + 1] & 0xFF) * 0.003921569F;
/*  4433 */         this.vertexData[k + 2] = (paramArrayOfByte[j + 2] & 0xFF) * 0.003921569F;
/*  4434 */         this.vertexData[k + 3] = (paramArrayOfByte[j + 3] & 0xFF) * 0.003921569F * this.lastAlpha[0];
/*       */       } 
/*       */     } else {
/*  4437 */       int j; int k; for (j = paramInt2 * 3, k = i; j < (paramInt2 + paramInt3) * 3; 
/*  4438 */         j += 3, k += this.stride) {
/*  4439 */         this.vertexData[k] = (paramArrayOfByte[j] & 0xFF) * 0.003921569F;
/*  4440 */         this.vertexData[k + 1] = (paramArrayOfByte[j + 1] & 0xFF) * 0.003921569F;
/*  4441 */         this.vertexData[k + 2] = (paramArrayOfByte[j + 2] & 0xFF) * 0.003921569F;
/*  4442 */         this.vertexData[k + 3] = this.lastAlpha[0];
/*       */       } 
/*       */     } 
/*  4445 */     if (this.source == null || !this.source.isLive()) {
/*  4446 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4450 */     this.geomLock.unLock();
/*  4451 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt1, Color3f[] paramArrayOfColor3f, int paramInt2, int paramInt3) {
/*  4465 */     int i = this.stride * paramInt1 + this.colorOffset;
/*       */ 
/*       */     
/*  4468 */     this.geomLock.getLock();
/*  4469 */     this.dirtyFlag |= 0x4;
/*  4470 */     this.colorChanged = 65535;
/*       */     int j, k;
/*  4472 */     for (j = paramInt2, k = i; j < paramInt2 + paramInt3; j++, k += this.stride) {
/*  4473 */       this.vertexData[k] = (paramArrayOfColor3f[j]).x;
/*  4474 */       this.vertexData[k + 1] = (paramArrayOfColor3f[j]).y;
/*  4475 */       this.vertexData[k + 2] = (paramArrayOfColor3f[j]).z;
/*  4476 */       this.vertexData[k + 3] = this.lastAlpha[0];
/*       */     } 
/*  4478 */     if (this.source == null || !this.source.isLive()) {
/*  4479 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4483 */     this.geomLock.unLock();
/*  4484 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt1, Color4f[] paramArrayOfColor4f, int paramInt2, int paramInt3) {
/*  4498 */     int i = this.stride * paramInt1 + this.colorOffset;
/*       */ 
/*       */     
/*  4501 */     this.geomLock.getLock();
/*  4502 */     this.dirtyFlag |= 0x4;
/*  4503 */     this.colorChanged = 65535;
/*       */     int j, k;
/*  4505 */     for (j = paramInt2, k = i; j < paramInt2 + paramInt3; j++, k += this.stride) {
/*  4506 */       this.vertexData[k] = (paramArrayOfColor4f[j]).x;
/*  4507 */       this.vertexData[k + 1] = (paramArrayOfColor4f[j]).y;
/*  4508 */       this.vertexData[k + 2] = (paramArrayOfColor4f[j]).z;
/*  4509 */       this.vertexData[k + 3] = (paramArrayOfColor4f[j]).w * this.lastAlpha[0];
/*       */     } 
/*  4511 */     if (this.source == null || !this.source.isLive()) {
/*  4512 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4516 */     this.geomLock.unLock();
/*  4517 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt1, Color3b[] paramArrayOfColor3b, int paramInt2, int paramInt3) {
/*  4531 */     int i = this.stride * paramInt1 + this.colorOffset;
/*       */ 
/*       */     
/*  4534 */     this.geomLock.getLock();
/*  4535 */     this.dirtyFlag |= 0x4;
/*  4536 */     this.colorChanged = 65535;
/*       */     int j, k;
/*  4538 */     for (j = paramInt2, k = i; j < paramInt2 + paramInt3; j++, k += this.stride) {
/*  4539 */       this.vertexData[k] = ((paramArrayOfColor3b[j]).x & 0xFF) * 0.003921569F;
/*  4540 */       this.vertexData[k + 1] = ((paramArrayOfColor3b[j]).y & 0xFF) * 0.003921569F;
/*  4541 */       this.vertexData[k + 2] = ((paramArrayOfColor3b[j]).z & 0xFF) * 0.003921569F;
/*  4542 */       this.vertexData[k + 3] = this.lastAlpha[0];
/*       */     } 
/*  4544 */     if (this.source == null || !this.source.isLive()) {
/*  4545 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4549 */     this.geomLock.unLock();
/*  4550 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColors(int paramInt1, Color4b[] paramArrayOfColor4b, int paramInt2, int paramInt3) {
/*  4564 */     int i = this.stride * paramInt1 + this.colorOffset;
/*       */ 
/*       */     
/*  4567 */     this.geomLock.getLock();
/*  4568 */     this.dirtyFlag |= 0x4;
/*  4569 */     this.colorChanged = 65535;
/*       */     int j, k;
/*  4571 */     for (j = paramInt2, k = i; j < paramInt2 + paramInt3; j++, k += this.stride) {
/*  4572 */       this.vertexData[k] = ((paramArrayOfColor4b[j]).x & 0xFF) * 0.003921569F;
/*  4573 */       this.vertexData[k + 1] = ((paramArrayOfColor4b[j]).y & 0xFF) * 0.003921569F;
/*  4574 */       this.vertexData[k + 2] = ((paramArrayOfColor4b[j]).z & 0xFF) * 0.003921569F;
/*  4575 */       this.vertexData[k + 3] = ((paramArrayOfColor4b[j]).w & 0xFF) * 0.003921569F * this.lastAlpha[0];
/*       */     } 
/*  4577 */     if (this.source == null || !this.source.isLive()) {
/*  4578 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4582 */     this.geomLock.unLock();
/*  4583 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormal(int paramInt, float[] paramArrayOfFloat) {
/*  4594 */     int i = this.stride * paramInt + this.normalOffset;
/*       */     
/*  4596 */     this.geomLock.getLock();
/*       */ 
/*       */     
/*  4599 */     this.vertexData[i] = paramArrayOfFloat[0];
/*  4600 */     this.vertexData[i + 1] = paramArrayOfFloat[1];
/*  4601 */     this.vertexData[i + 2] = paramArrayOfFloat[2];
/*  4602 */     if (this.source == null || !this.source.isLive()) {
/*  4603 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4607 */     this.geomLock.unLock();
/*  4608 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormal(int paramInt, Vector3f paramVector3f) {
/*  4619 */     int i = this.stride * paramInt + this.normalOffset;
/*       */     
/*  4621 */     this.geomLock.getLock();
/*       */     
/*  4623 */     this.dirtyFlag |= 0x2;
/*  4624 */     this.vertexData[i] = paramVector3f.x;
/*  4625 */     this.vertexData[i + 1] = paramVector3f.y;
/*  4626 */     this.vertexData[i + 2] = paramVector3f.z;
/*  4627 */     if (this.source == null || !this.source.isLive()) {
/*  4628 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4632 */     this.geomLock.unLock();
/*  4633 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormals(int paramInt, float[] paramArrayOfFloat) {
/*  4644 */     int i = this.stride * paramInt + this.normalOffset;
/*  4645 */     int k = paramArrayOfFloat.length;
/*       */     
/*  4647 */     this.geomLock.getLock();
/*       */     
/*  4649 */     this.dirtyFlag |= 0x2; boolean bool; int j;
/*  4650 */     for (bool = false, j = i; bool < k; bool += true, j += this.stride) {
/*       */       
/*  4652 */       this.vertexData[j] = paramArrayOfFloat[bool];
/*  4653 */       this.vertexData[j + 1] = paramArrayOfFloat[bool + true];
/*  4654 */       this.vertexData[j + 2] = paramArrayOfFloat[bool + 2];
/*       */     } 
/*  4656 */     if (this.source == null || !this.source.isLive()) {
/*  4657 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4661 */     this.geomLock.unLock();
/*  4662 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormals(int paramInt, Vector3f[] paramArrayOfVector3f) {
/*  4673 */     int i = this.stride * paramInt + this.normalOffset;
/*  4674 */     int k = paramArrayOfVector3f.length;
/*       */     
/*  4676 */     this.geomLock.getLock();
/*       */     
/*  4678 */     this.dirtyFlag |= 0x2; byte b; int j;
/*  4679 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  4681 */       this.vertexData[j] = (paramArrayOfVector3f[b]).x;
/*  4682 */       this.vertexData[j + 1] = (paramArrayOfVector3f[b]).y;
/*  4683 */       this.vertexData[j + 2] = (paramArrayOfVector3f[b]).z;
/*       */     } 
/*  4685 */     if (this.source == null || !this.source.isLive()) {
/*  4686 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4690 */     this.geomLock.unLock();
/*  4691 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormals(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) {
/*  4705 */     int i = this.stride * paramInt1 + this.normalOffset;
/*       */ 
/*       */     
/*  4708 */     this.geomLock.getLock();
/*       */     
/*  4710 */     this.dirtyFlag |= 0x2; int j, k;
/*  4711 */     for (j = paramInt2 * 3, k = i; j < (paramInt2 + paramInt3) * 3; 
/*  4712 */       j += 3, k += this.stride) {
/*  4713 */       this.vertexData[k] = paramArrayOfFloat[j];
/*  4714 */       this.vertexData[k + 1] = paramArrayOfFloat[j + 1];
/*  4715 */       this.vertexData[k + 2] = paramArrayOfFloat[j + 2];
/*       */     } 
/*  4717 */     if (this.source == null || !this.source.isLive()) {
/*  4718 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4722 */     this.geomLock.unLock();
/*  4723 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormals(int paramInt1, Vector3f[] paramArrayOfVector3f, int paramInt2, int paramInt3) {
/*  4737 */     int i = this.stride * paramInt1 + this.normalOffset;
/*       */ 
/*       */     
/*  4740 */     this.geomLock.getLock();
/*       */     
/*  4742 */     this.dirtyFlag |= 0x2; int j, k;
/*  4743 */     for (j = paramInt2, k = i; j < paramInt2 + paramInt3; j++, k += this.stride) {
/*  4744 */       this.vertexData[k] = (paramArrayOfVector3f[j]).x;
/*  4745 */       this.vertexData[k + 1] = (paramArrayOfVector3f[j]).y;
/*  4746 */       this.vertexData[k + 2] = (paramArrayOfVector3f[j]).z;
/*       */     } 
/*  4748 */     if (this.source == null || !this.source.isLive()) {
/*  4749 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4753 */     this.geomLock.unLock();
/*  4754 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTextureCoordinates(int paramInt1, int paramInt2, float[] paramArrayOfFloat, int paramInt3, int paramInt4) {
/*  4771 */     if ((this.vertexFormat & 0x80) != 0) {
/*  4772 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*       */     }
/*  4774 */     if ((this.vertexFormat & 0x460) == 0) {
/*  4775 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*       */     }
/*  4777 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */ 
/*       */ 
/*       */     
/*  4781 */     this.geomLock.getLock();
/*  4782 */     this.dirtyFlag |= 0x8;
/*       */     
/*  4784 */     if ((this.vertexFormat & 0x400) != 0) {
/*  4785 */       int j; int k; byte b; for (j = paramInt3 * 4, k = i, b = 0; b < paramInt4; 
/*  4786 */         k += this.stride, b++) {
/*  4787 */         this.vertexData[k] = paramArrayOfFloat[j++];
/*  4788 */         this.vertexData[k + 1] = paramArrayOfFloat[j++];
/*  4789 */         this.vertexData[k + 2] = paramArrayOfFloat[j++];
/*  4790 */         this.vertexData[k + 3] = paramArrayOfFloat[j++];
/*       */       } 
/*  4792 */     } else if ((this.vertexFormat & 0x40) != 0) {
/*       */       int j; int k; byte b;
/*  4794 */       for (j = paramInt3 * 3, k = i, b = 0; b < paramInt4; 
/*  4795 */         k += this.stride, b++) {
/*  4796 */         this.vertexData[k] = paramArrayOfFloat[j++];
/*  4797 */         this.vertexData[k + 1] = paramArrayOfFloat[j++];
/*  4798 */         this.vertexData[k + 2] = paramArrayOfFloat[j++];
/*       */       } 
/*       */     } else {
/*  4801 */       int j; int k; byte b; for (j = paramInt3 * 2, k = i, b = 0; b < paramInt4; 
/*  4802 */         k += this.stride, b++) {
/*  4803 */         this.vertexData[k] = paramArrayOfFloat[j++];
/*  4804 */         this.vertexData[k + 1] = paramArrayOfFloat[j++];
/*       */       } 
/*       */     } 
/*  4807 */     if (this.source == null || !this.source.isLive()) {
/*  4808 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4812 */     this.geomLock.unLock();
/*  4813 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTextureCoordinates(int paramInt1, int paramInt2, Point2f[] paramArrayOfPoint2f, int paramInt3, int paramInt4) {
/*  4829 */     if ((this.vertexFormat & 0x80) != 0) {
/*  4830 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*       */     }
/*  4832 */     if ((this.vertexFormat & 0x460) == 0) {
/*  4833 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*       */     }
/*  4835 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */ 
/*       */ 
/*       */     
/*  4839 */     this.geomLock.getLock();
/*  4840 */     this.dirtyFlag |= 0x8;
/*       */     int j, k;
/*  4842 */     for (j = paramInt3, k = i; j < paramInt3 + paramInt4; j++, k += this.stride) {
/*  4843 */       this.vertexData[k] = (paramArrayOfPoint2f[j]).x;
/*  4844 */       this.vertexData[k + 1] = (paramArrayOfPoint2f[j]).y;
/*       */     } 
/*  4846 */     if (this.source == null || !this.source.isLive()) {
/*  4847 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4851 */     this.geomLock.unLock();
/*  4852 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTextureCoordinates(int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f, int paramInt3, int paramInt4) {
/*  4868 */     if ((this.vertexFormat & 0x80) != 0) {
/*  4869 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*       */     }
/*  4871 */     if ((this.vertexFormat & 0x460) == 0) {
/*  4872 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*       */     }
/*  4874 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */ 
/*       */ 
/*       */     
/*  4878 */     this.geomLock.getLock();
/*  4879 */     this.dirtyFlag |= 0x8;
/*       */     int j, k;
/*  4881 */     for (j = paramInt3, k = i; j < paramInt3 + paramInt4; j++, k += this.stride) {
/*  4882 */       this.vertexData[k] = (paramArrayOfPoint3f[j]).x;
/*  4883 */       this.vertexData[k + 1] = (paramArrayOfPoint3f[j]).y;
/*  4884 */       this.vertexData[k + 2] = (paramArrayOfPoint3f[j]).z;
/*       */     } 
/*  4886 */     if (this.source == null || !this.source.isLive()) {
/*  4887 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4891 */     this.geomLock.unLock();
/*  4892 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord2f[] paramArrayOfTexCoord2f, int paramInt3, int paramInt4) {
/*  4908 */     this.geomLock.getLock();
/*  4909 */     this.dirtyFlag |= 0x8;
/*       */     
/*  4911 */     if ((this.vertexFormat & 0x80) != 0) {
/*  4912 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*       */     }
/*  4914 */     if ((this.vertexFormat & 0x460) == 0) {
/*  4915 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*       */     }
/*  4917 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*       */     int j, k;
/*       */     
/*  4921 */     for (j = paramInt3, k = i; j < paramInt3 + paramInt4; j++, k += this.stride) {
/*  4922 */       this.vertexData[k] = (paramArrayOfTexCoord2f[j]).x;
/*  4923 */       this.vertexData[k + 1] = (paramArrayOfTexCoord2f[j]).y;
/*       */     } 
/*  4925 */     if (this.source == null || !this.source.isLive()) {
/*  4926 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4930 */     this.geomLock.unLock();
/*  4931 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord3f[] paramArrayOfTexCoord3f, int paramInt3, int paramInt4) {
/*  4948 */     this.geomLock.getLock();
/*  4949 */     this.dirtyFlag |= 0x8;
/*       */     
/*  4951 */     if ((this.vertexFormat & 0x80) != 0) {
/*  4952 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*       */     }
/*  4954 */     if ((this.vertexFormat & 0x460) == 0) {
/*  4955 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*       */     }
/*  4957 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*       */     int j, k;
/*       */     
/*  4961 */     for (j = paramInt3, k = i; j < paramInt3 + paramInt4; j++, k += this.stride) {
/*  4962 */       this.vertexData[k] = (paramArrayOfTexCoord3f[j]).x;
/*  4963 */       this.vertexData[k + 1] = (paramArrayOfTexCoord3f[j]).y;
/*  4964 */       this.vertexData[k + 2] = (paramArrayOfTexCoord3f[j]).z;
/*       */     } 
/*  4966 */     if (this.source == null || !this.source.isLive()) {
/*  4967 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  4971 */     this.geomLock.unLock();
/*  4972 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord4f[] paramArrayOfTexCoord4f, int paramInt3, int paramInt4) {
/*  4988 */     this.geomLock.getLock();
/*  4989 */     this.dirtyFlag |= 0x8;
/*       */     
/*  4991 */     if ((this.vertexFormat & 0x80) != 0) {
/*  4992 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*       */     }
/*  4994 */     if ((this.vertexFormat & 0x460) == 0) {
/*  4995 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*       */     }
/*  4997 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*       */     int j, k;
/*       */     
/*  5001 */     for (j = paramInt3, k = i; j < paramInt3 + paramInt4; j++, k += this.stride) {
/*  5002 */       this.vertexData[k] = (paramArrayOfTexCoord4f[j]).x;
/*  5003 */       this.vertexData[k + 1] = (paramArrayOfTexCoord4f[j]).y;
/*  5004 */       this.vertexData[k + 2] = (paramArrayOfTexCoord4f[j]).z;
/*  5005 */       this.vertexData[k + 3] = (paramArrayOfTexCoord4f[j]).w;
/*       */     } 
/*  5007 */     if (this.source == null || !this.source.isLive()) {
/*  5008 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5012 */     this.geomLock.unLock();
/*  5013 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttr(int paramInt1, int paramInt2, Point2f paramPoint2f) {
/*  5029 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */     
/*  5031 */     this.geomLock.getLock();
/*  5032 */     this.dirtyFlag |= 0x80;
/*       */     
/*  5034 */     this.vertexData[i] = paramPoint2f.x;
/*  5035 */     this.vertexData[i + 1] = paramPoint2f.y;
/*       */     
/*  5037 */     if (this.source == null || !this.source.isLive()) {
/*  5038 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5042 */     this.geomLock.unLock();
/*  5043 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttr(int paramInt1, int paramInt2, Point3f paramPoint3f) {
/*  5058 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */     
/*  5060 */     this.geomLock.getLock();
/*  5061 */     this.dirtyFlag |= 0x80;
/*       */     
/*  5063 */     this.vertexData[i] = paramPoint3f.x;
/*  5064 */     this.vertexData[i + 1] = paramPoint3f.y;
/*  5065 */     this.vertexData[i + 2] = paramPoint3f.z;
/*       */     
/*  5067 */     if (this.source == null || !this.source.isLive()) {
/*  5068 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5072 */     this.geomLock.unLock();
/*  5073 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttr(int paramInt1, int paramInt2, Point4f paramPoint4f) {
/*  5088 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */     
/*  5090 */     this.geomLock.getLock();
/*  5091 */     this.dirtyFlag |= 0x80;
/*       */     
/*  5093 */     this.vertexData[i] = paramPoint4f.x;
/*  5094 */     this.vertexData[i + 1] = paramPoint4f.y;
/*  5095 */     this.vertexData[i + 2] = paramPoint4f.z;
/*  5096 */     this.vertexData[i + 3] = paramPoint4f.w;
/*       */     
/*  5098 */     if (this.source == null || !this.source.isLive()) {
/*  5099 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5103 */     this.geomLock.unLock();
/*  5104 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttrs(int paramInt1, int paramInt2, float[] paramArrayOfFloat, int paramInt3, int paramInt4) {
/*  5125 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*  5126 */     int j = this.vertexAttrSizes[paramInt1];
/*       */ 
/*       */     
/*  5129 */     this.geomLock.getLock();
/*  5130 */     this.dirtyFlag |= 0x80; int k, m;
/*       */     byte b;
/*  5132 */     for (k = paramInt3 * j, m = i, b = 0; b < paramInt4; k += j, m += this.stride, b++) {
/*  5133 */       for (int n = 0; n < j; n++) {
/*  5134 */         this.vertexData[m + n] = paramArrayOfFloat[k + n];
/*       */       }
/*       */     } 
/*       */     
/*  5138 */     if (this.source == null || !this.source.isLive()) {
/*  5139 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5143 */     this.geomLock.unLock();
/*  5144 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttrs(int paramInt1, int paramInt2, Point2f[] paramArrayOfPoint2f, int paramInt3, int paramInt4) {
/*  5166 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */ 
/*       */     
/*  5169 */     this.geomLock.getLock();
/*  5170 */     this.dirtyFlag |= 0x80; int j, k;
/*       */     byte b;
/*  5172 */     for (j = paramInt3, k = i, b = 0; b < paramInt4; j++, k += this.stride, b++) {
/*  5173 */       this.vertexData[k] = (paramArrayOfPoint2f[j]).x;
/*  5174 */       this.vertexData[k + 1] = (paramArrayOfPoint2f[j]).y;
/*       */     } 
/*       */     
/*  5177 */     if (this.source == null || !this.source.isLive()) {
/*  5178 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5182 */     this.geomLock.unLock();
/*  5183 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttrs(int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f, int paramInt3, int paramInt4) {
/*  5205 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */ 
/*       */     
/*  5208 */     this.geomLock.getLock();
/*  5209 */     this.dirtyFlag |= 0x80; int j, k;
/*       */     byte b;
/*  5211 */     for (j = paramInt3, k = i, b = 0; b < paramInt4; j++, k += this.stride, b++) {
/*  5212 */       this.vertexData[k] = (paramArrayOfPoint3f[j]).x;
/*  5213 */       this.vertexData[k + 1] = (paramArrayOfPoint3f[j]).y;
/*  5214 */       this.vertexData[k + 2] = (paramArrayOfPoint3f[j]).z;
/*       */     } 
/*       */     
/*  5217 */     if (this.source == null || !this.source.isLive()) {
/*  5218 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5222 */     this.geomLock.unLock();
/*  5223 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttrs(int paramInt1, int paramInt2, Point4f[] paramArrayOfPoint4f, int paramInt3, int paramInt4) {
/*  5245 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */ 
/*       */     
/*  5248 */     this.geomLock.getLock();
/*  5249 */     this.dirtyFlag |= 0x80; int j, k;
/*       */     byte b;
/*  5251 */     for (j = paramInt3, k = i, b = 0; b < paramInt4; j++, k += this.stride, b++) {
/*  5252 */       this.vertexData[k] = (paramArrayOfPoint4f[j]).x;
/*  5253 */       this.vertexData[k + 1] = (paramArrayOfPoint4f[j]).y;
/*  5254 */       this.vertexData[k + 2] = (paramArrayOfPoint4f[j]).z;
/*  5255 */       this.vertexData[k + 3] = (paramArrayOfPoint4f[j]).w;
/*       */     } 
/*       */     
/*  5258 */     if (this.source == null || !this.source.isLive()) {
/*  5259 */       this.geomLock.unLock();
/*       */       
/*       */       return;
/*       */     } 
/*  5263 */     this.geomLock.unLock();
/*  5264 */     sendDataChangedMessage(false);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinate(int paramInt, float[] paramArrayOfFloat) {
/*  5275 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */     
/*  5277 */     paramArrayOfFloat[0] = this.vertexData[i];
/*  5278 */     paramArrayOfFloat[1] = this.vertexData[i + 1];
/*  5279 */     paramArrayOfFloat[2] = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinate(int paramInt, double[] paramArrayOfDouble) {
/*  5289 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */     
/*  5291 */     paramArrayOfDouble[0] = this.vertexData[i];
/*  5292 */     paramArrayOfDouble[1] = this.vertexData[i + 1];
/*  5293 */     paramArrayOfDouble[2] = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinate(int paramInt, Point3f paramPoint3f) {
/*  5303 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */     
/*  5305 */     paramPoint3f.x = this.vertexData[i];
/*  5306 */     paramPoint3f.y = this.vertexData[i + 1];
/*  5307 */     paramPoint3f.z = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinate(int paramInt, Point3d paramPoint3d) {
/*  5317 */     int i = this.stride * paramInt + this.coordinateOffset;
/*       */     
/*  5319 */     paramPoint3d.x = this.vertexData[i];
/*  5320 */     paramPoint3d.y = this.vertexData[i + 1];
/*  5321 */     paramPoint3d.z = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinates(int paramInt, float[] paramArrayOfFloat) {
/*  5331 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  5332 */     int k = paramArrayOfFloat.length; boolean bool;
/*       */     int j;
/*  5334 */     for (bool = false, j = i; bool < k; bool += true, j += this.stride) {
/*       */       
/*  5336 */       paramArrayOfFloat[bool] = this.vertexData[j];
/*  5337 */       paramArrayOfFloat[bool + true] = this.vertexData[j + 1];
/*  5338 */       paramArrayOfFloat[bool + 2] = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinates(int paramInt, double[] paramArrayOfDouble) {
/*  5350 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  5351 */     int k = paramArrayOfDouble.length; boolean bool;
/*       */     int j;
/*  5353 */     for (bool = false, j = i; bool < k; bool += true, j += this.stride) {
/*       */       
/*  5355 */       paramArrayOfDouble[bool] = this.vertexData[j];
/*  5356 */       paramArrayOfDouble[bool + true] = this.vertexData[j + 1];
/*  5357 */       paramArrayOfDouble[bool + 2] = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinates(int paramInt, Point3f[] paramArrayOfPoint3f) {
/*  5368 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  5369 */     int k = paramArrayOfPoint3f.length; byte b;
/*       */     int j;
/*  5371 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5373 */       (paramArrayOfPoint3f[b]).x = this.vertexData[j];
/*  5374 */       (paramArrayOfPoint3f[b]).y = this.vertexData[j + 1];
/*  5375 */       (paramArrayOfPoint3f[b]).z = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getCoordinates(int paramInt, Point3d[] paramArrayOfPoint3d) {
/*  5386 */     int i = this.stride * paramInt + this.coordinateOffset;
/*  5387 */     int k = paramArrayOfPoint3d.length; byte b;
/*       */     int j;
/*  5389 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5391 */       (paramArrayOfPoint3d[b]).x = this.vertexData[j];
/*  5392 */       (paramArrayOfPoint3d[b]).y = this.vertexData[j + 1];
/*  5393 */       (paramArrayOfPoint3d[b]).z = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColor(int paramInt, float[] paramArrayOfFloat) {
/*  5404 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  5406 */     paramArrayOfFloat[0] = this.vertexData[i];
/*  5407 */     paramArrayOfFloat[1] = this.vertexData[i + 1];
/*  5408 */     paramArrayOfFloat[2] = this.vertexData[i + 2];
/*  5409 */     if ((this.vertexFormat & 0x8) != 0) {
/*  5410 */       paramArrayOfFloat[3] = this.vertexData[i + 3] / this.lastAlpha[0];
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColor(int paramInt, byte[] paramArrayOfByte) {
/*  5420 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  5422 */     paramArrayOfByte[0] = (byte)(int)(this.vertexData[i] * 255.0F);
/*  5423 */     paramArrayOfByte[1] = (byte)(int)(this.vertexData[i + 1] * 255.0F);
/*  5424 */     paramArrayOfByte[2] = (byte)(int)(this.vertexData[i + 2] * 255.0F);
/*  5425 */     if ((this.vertexFormat & 0x8) != 0) {
/*  5426 */       paramArrayOfByte[3] = (byte)(int)(this.vertexData[i + 3] / this.lastAlpha[0] * 255.0F);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColor(int paramInt, Color3f paramColor3f) {
/*  5436 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  5438 */     paramColor3f.x = this.vertexData[i];
/*  5439 */     paramColor3f.y = this.vertexData[i + 1];
/*  5440 */     paramColor3f.z = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColor(int paramInt, Color4f paramColor4f) {
/*  5450 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  5452 */     paramColor4f.x = this.vertexData[i];
/*  5453 */     paramColor4f.y = this.vertexData[i + 1];
/*  5454 */     paramColor4f.z = this.vertexData[i + 2];
/*  5455 */     paramColor4f.w = this.vertexData[i + 3] / this.lastAlpha[0];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColor(int paramInt, Color3b paramColor3b) {
/*  5465 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  5467 */     paramColor3b.x = (byte)(int)(this.vertexData[i] * 255.0F);
/*  5468 */     paramColor3b.y = (byte)(int)(this.vertexData[i + 1] * 255.0F);
/*  5469 */     paramColor3b.z = (byte)(int)(this.vertexData[i + 2] * 255.0F);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColor(int paramInt, Color4b paramColor4b) {
/*  5479 */     int i = this.stride * paramInt + this.colorOffset;
/*       */     
/*  5481 */     paramColor4b.x = (byte)(int)(this.vertexData[i] * 255.0F);
/*  5482 */     paramColor4b.y = (byte)(int)(this.vertexData[i + 1] * 255.0F);
/*  5483 */     paramColor4b.z = (byte)(int)(this.vertexData[i + 2] * 255.0F);
/*  5484 */     paramColor4b.w = (byte)(int)(this.vertexData[i + 3] / this.lastAlpha[0] * 255.0F);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColors(int paramInt, float[] paramArrayOfFloat) {
/*  5494 */     int i = this.stride * paramInt + this.colorOffset;
/*  5495 */     int j = paramArrayOfFloat.length;
/*  5496 */     float f = 1.0F / this.lastAlpha[0];
/*       */     
/*  5498 */     if ((this.vertexFormat & 0x8) != 0) {
/*       */       boolean bool; int k;
/*  5500 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  5502 */         paramArrayOfFloat[bool] = this.vertexData[k];
/*  5503 */         paramArrayOfFloat[bool + true] = this.vertexData[k + 1];
/*  5504 */         paramArrayOfFloat[bool + 2] = this.vertexData[k + 2];
/*  5505 */         paramArrayOfFloat[bool + 3] = this.vertexData[k + 3] * f;
/*       */       } 
/*       */     } else {
/*       */       boolean bool;
/*       */       int k;
/*  5510 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  5512 */         paramArrayOfFloat[bool] = this.vertexData[k];
/*  5513 */         paramArrayOfFloat[bool + true] = this.vertexData[k + 1];
/*  5514 */         paramArrayOfFloat[bool + 2] = this.vertexData[k + 2];
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColors(int paramInt, byte[] paramArrayOfByte) {
/*  5526 */     int i = this.stride * paramInt + this.colorOffset;
/*  5527 */     int j = paramArrayOfByte.length;
/*  5528 */     float f = 1.0F / this.lastAlpha[0];
/*       */ 
/*       */     
/*  5531 */     if ((this.vertexFormat & 0x8) != 0) {
/*       */       boolean bool; int k;
/*  5533 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  5535 */         paramArrayOfByte[bool] = (byte)(int)(this.vertexData[k] * 255.0F);
/*  5536 */         paramArrayOfByte[bool + true] = (byte)(int)(this.vertexData[k + 1] * 255.0F);
/*  5537 */         paramArrayOfByte[bool + 2] = (byte)(int)(this.vertexData[k + 2] * 255.0F);
/*  5538 */         paramArrayOfByte[bool + 3] = (byte)(int)(this.vertexData[k + 3] * f * 255.0F);
/*       */       } 
/*       */     } else {
/*       */       boolean bool;
/*       */       int k;
/*  5543 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  5545 */         paramArrayOfByte[bool] = (byte)(int)(this.vertexData[k] * 255.0F);
/*  5546 */         paramArrayOfByte[bool + true] = (byte)(int)(this.vertexData[k + 1] * 255.0F);
/*  5547 */         paramArrayOfByte[bool + 2] = (byte)(int)(this.vertexData[k + 2] * 255.0F);
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColors(int paramInt, Color3f[] paramArrayOfColor3f) {
/*  5559 */     int i = this.stride * paramInt + this.colorOffset;
/*  5560 */     int k = paramArrayOfColor3f.length; byte b;
/*       */     int j;
/*  5562 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5564 */       (paramArrayOfColor3f[b]).x = this.vertexData[j];
/*  5565 */       (paramArrayOfColor3f[b]).y = this.vertexData[j + 1];
/*  5566 */       (paramArrayOfColor3f[b]).z = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColors(int paramInt, Color4f[] paramArrayOfColor4f) {
/*  5577 */     int i = this.stride * paramInt + this.colorOffset;
/*  5578 */     int k = paramArrayOfColor4f.length;
/*  5579 */     float f = 1.0F / this.lastAlpha[0]; byte b;
/*       */     int j;
/*  5581 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5583 */       (paramArrayOfColor4f[b]).x = this.vertexData[j];
/*  5584 */       (paramArrayOfColor4f[b]).y = this.vertexData[j + 1];
/*  5585 */       (paramArrayOfColor4f[b]).z = this.vertexData[j + 2];
/*  5586 */       (paramArrayOfColor4f[b]).w = this.vertexData[j + 3] * f;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColors(int paramInt, Color3b[] paramArrayOfColor3b) {
/*  5597 */     int i = this.stride * paramInt + this.colorOffset;
/*  5598 */     int k = paramArrayOfColor3b.length; byte b;
/*       */     int j;
/*  5600 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5602 */       (paramArrayOfColor3b[b]).x = (byte)(int)(this.vertexData[j] * 255.0F);
/*  5603 */       (paramArrayOfColor3b[b]).y = (byte)(int)(this.vertexData[j + 1] * 255.0F);
/*  5604 */       (paramArrayOfColor3b[b]).z = (byte)(int)(this.vertexData[j + 2] * 255.0F);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getColors(int paramInt, Color4b[] paramArrayOfColor4b) {
/*  5615 */     int i = this.stride * paramInt + this.colorOffset;
/*  5616 */     int k = paramArrayOfColor4b.length;
/*  5617 */     float f = 1.0F / this.lastAlpha[0]; byte b;
/*       */     int j;
/*  5619 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5621 */       (paramArrayOfColor4b[b]).x = (byte)(int)(this.vertexData[j] * 255.0F);
/*  5622 */       (paramArrayOfColor4b[b]).y = (byte)(int)(this.vertexData[j + 1] * 255.0F);
/*  5623 */       (paramArrayOfColor4b[b]).z = (byte)(int)(this.vertexData[j + 2] * 255.0F);
/*  5624 */       (paramArrayOfColor4b[b]).w = (byte)(int)(this.vertexData[j + 3] * f * 255.0F);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getNormal(int paramInt, float[] paramArrayOfFloat) {
/*  5635 */     int i = this.stride * paramInt + this.normalOffset;
/*       */     
/*  5637 */     paramArrayOfFloat[0] = this.vertexData[i];
/*  5638 */     paramArrayOfFloat[1] = this.vertexData[i + 1];
/*  5639 */     paramArrayOfFloat[2] = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getNormal(int paramInt, Vector3f paramVector3f) {
/*  5649 */     int i = this.stride * paramInt + this.normalOffset;
/*       */     
/*  5651 */     paramVector3f.x = this.vertexData[i];
/*  5652 */     paramVector3f.y = this.vertexData[i + 1];
/*  5653 */     paramVector3f.z = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getNormals(int paramInt, float[] paramArrayOfFloat) {
/*  5663 */     int i = this.stride * paramInt + this.normalOffset;
/*  5664 */     int k = paramArrayOfFloat.length; boolean bool;
/*       */     int j;
/*  5666 */     for (bool = false, j = i; bool < k; bool += true, j += this.stride) {
/*       */       
/*  5668 */       paramArrayOfFloat[bool] = this.vertexData[j];
/*  5669 */       paramArrayOfFloat[bool + true] = this.vertexData[j + 1];
/*  5670 */       paramArrayOfFloat[bool + 2] = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getNormals(int paramInt, Vector3f[] paramArrayOfVector3f) {
/*  5681 */     int i = this.stride * paramInt + this.normalOffset;
/*  5682 */     int k = paramArrayOfVector3f.length; byte b;
/*       */     int j;
/*  5684 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5686 */       (paramArrayOfVector3f[b]).x = this.vertexData[j];
/*  5687 */       (paramArrayOfVector3f[b]).y = this.vertexData[j + 1];
/*  5688 */       (paramArrayOfVector3f[b]).z = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinate(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/*  5699 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */ 
/*       */     
/*  5702 */     paramArrayOfFloat[0] = this.vertexData[i];
/*  5703 */     paramArrayOfFloat[1] = this.vertexData[i + 1];
/*  5704 */     if ((this.vertexFormat & 0x40) != 0) {
/*  5705 */       paramArrayOfFloat[2] = this.vertexData[i + 2];
/*       */     }
/*  5707 */     else if ((this.vertexFormat & 0x400) != 0) {
/*       */       
/*  5709 */       paramArrayOfFloat[2] = this.vertexData[i + 2];
/*  5710 */       paramArrayOfFloat[3] = this.vertexData[i + 3];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinate(int paramInt1, int paramInt2, TexCoord2f paramTexCoord2f) {
/*  5721 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */ 
/*       */     
/*  5724 */     paramTexCoord2f.x = this.vertexData[i];
/*  5725 */     paramTexCoord2f.y = this.vertexData[i + 1];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinate(int paramInt1, int paramInt2, TexCoord3f paramTexCoord3f) {
/*  5735 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */ 
/*       */     
/*  5738 */     paramTexCoord3f.x = this.vertexData[i];
/*  5739 */     paramTexCoord3f.y = this.vertexData[i + 1];
/*  5740 */     paramTexCoord3f.z = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinate(int paramInt1, int paramInt2, TexCoord4f paramTexCoord4f) {
/*  5750 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */ 
/*       */     
/*  5753 */     paramTexCoord4f.x = this.vertexData[i];
/*  5754 */     paramTexCoord4f.y = this.vertexData[i + 1];
/*  5755 */     paramTexCoord4f.z = this.vertexData[i + 2];
/*  5756 */     paramTexCoord4f.w = this.vertexData[i + 3];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinates(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/*  5766 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*  5768 */     int j = paramArrayOfFloat.length;
/*       */     
/*  5770 */     if ((this.vertexFormat & 0x400) != 0) {
/*  5771 */       boolean bool; int k; for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  5773 */         paramArrayOfFloat[bool] = this.vertexData[k];
/*  5774 */         paramArrayOfFloat[bool + true] = this.vertexData[k + 1];
/*  5775 */         paramArrayOfFloat[bool + 2] = this.vertexData[k + 2];
/*  5776 */         paramArrayOfFloat[bool + 3] = this.vertexData[k + 3];
/*       */       } 
/*  5778 */     } else if ((this.vertexFormat & 0x40) != 0) {
/*       */       boolean bool; int k;
/*  5780 */       for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  5782 */         paramArrayOfFloat[bool] = this.vertexData[k];
/*  5783 */         paramArrayOfFloat[bool + true] = this.vertexData[k + 1];
/*  5784 */         paramArrayOfFloat[bool + 2] = this.vertexData[k + 2];
/*       */       } 
/*       */     } else {
/*  5787 */       boolean bool; int k; for (bool = false, k = i; bool < j; bool += true, k += this.stride) {
/*       */         
/*  5789 */         paramArrayOfFloat[bool] = this.vertexData[k];
/*  5790 */         paramArrayOfFloat[bool + true] = this.vertexData[k + 1];
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinates(int paramInt1, int paramInt2, TexCoord2f[] paramArrayOfTexCoord2f) {
/*  5803 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*  5805 */     int k = paramArrayOfTexCoord2f.length; byte b;
/*       */     int j;
/*  5807 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5809 */       (paramArrayOfTexCoord2f[b]).x = this.vertexData[j];
/*  5810 */       (paramArrayOfTexCoord2f[b]).y = this.vertexData[j + 1];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinates(int paramInt1, int paramInt2, TexCoord3f[] paramArrayOfTexCoord3f) {
/*  5821 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*  5823 */     int k = paramArrayOfTexCoord3f.length; byte b;
/*       */     int j;
/*  5825 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5827 */       (paramArrayOfTexCoord3f[b]).x = this.vertexData[j];
/*  5828 */       (paramArrayOfTexCoord3f[b]).y = this.vertexData[j + 1];
/*  5829 */       (paramArrayOfTexCoord3f[b]).z = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getTextureCoordinates(int paramInt1, int paramInt2, TexCoord4f[] paramArrayOfTexCoord4f) {
/*  5840 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*  5842 */     int k = paramArrayOfTexCoord4f.length; byte b;
/*       */     int j;
/*  5844 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5846 */       (paramArrayOfTexCoord4f[b]).x = this.vertexData[j];
/*  5847 */       (paramArrayOfTexCoord4f[b]).y = this.vertexData[j + 1];
/*  5848 */       (paramArrayOfTexCoord4f[b]).z = this.vertexData[j + 2];
/*  5849 */       (paramArrayOfTexCoord4f[b]).w = this.vertexData[j + 3];
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   void getTextureCoordinates(int paramInt1, int paramInt2, Point2f[] paramArrayOfPoint2f) {
/*  5855 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*  5857 */     int k = paramArrayOfPoint2f.length; byte b;
/*       */     int j;
/*  5859 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5861 */       (paramArrayOfPoint2f[b]).x = this.vertexData[j];
/*  5862 */       (paramArrayOfPoint2f[b]).y = this.vertexData[j + 1];
/*       */     } 
/*       */   }
/*       */   
/*       */   void getTextureCoordinates(int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f) {
/*  5867 */     int i = this.stride * paramInt2 + this.textureOffset + paramInt1 * this.texCoordStride;
/*       */     
/*  5869 */     int k = paramArrayOfPoint3f.length; byte b;
/*       */     int j;
/*  5871 */     for (b = 0, j = i; b < k; b++, j += this.stride) {
/*       */       
/*  5873 */       (paramArrayOfPoint3f[b]).x = this.vertexData[j];
/*  5874 */       (paramArrayOfPoint3f[b]).y = this.vertexData[j + 1];
/*  5875 */       (paramArrayOfPoint3f[b]).z = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttr(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/*  5888 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*  5889 */     int j = this.vertexAttrSizes[paramInt1];
/*       */     
/*  5891 */     for (int k = 0; k < j; k++) {
/*  5892 */       paramArrayOfFloat[k] = this.vertexData[i + k];
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttr(int paramInt1, int paramInt2, Point2f paramPoint2f) {
/*  5906 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */     
/*  5908 */     paramPoint2f.x = this.vertexData[i];
/*  5909 */     paramPoint2f.y = this.vertexData[i + 1];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttr(int paramInt1, int paramInt2, Point3f paramPoint3f) {
/*  5921 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */     
/*  5923 */     paramPoint3f.x = this.vertexData[i];
/*  5924 */     paramPoint3f.y = this.vertexData[i + 1];
/*  5925 */     paramPoint3f.z = this.vertexData[i + 2];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttr(int paramInt1, int paramInt2, Point4f paramPoint4f) {
/*  5937 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */     
/*  5939 */     paramPoint4f.x = this.vertexData[i];
/*  5940 */     paramPoint4f.y = this.vertexData[i + 1];
/*  5941 */     paramPoint4f.z = this.vertexData[i + 2];
/*  5942 */     paramPoint4f.w = this.vertexData[i + 3];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttrs(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/*  5954 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*  5955 */     int j = this.vertexAttrSizes[paramInt1];
/*       */ 
/*       */     
/*  5958 */     int k = 0, m = i;
/*  5959 */     for (; k < paramArrayOfFloat.length && m < this.vertexData.length; 
/*  5960 */       k += j, m += this.stride) {
/*  5961 */       for (int n = 0; n < j; n++) {
/*  5962 */         paramArrayOfFloat[k + n] = this.vertexData[m + n];
/*       */       }
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttrs(int paramInt1, int paramInt2, Point2f[] paramArrayOfPoint2f) {
/*  5976 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */ 
/*       */     
/*  5979 */     byte b = 0; int j = i;
/*  5980 */     for (; b < paramArrayOfPoint2f.length && j < this.vertexData.length; 
/*  5981 */       b++, j += this.stride) {
/*  5982 */       (paramArrayOfPoint2f[b]).x = this.vertexData[j];
/*  5983 */       (paramArrayOfPoint2f[b]).y = this.vertexData[j + 1];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttrs(int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f) {
/*  5996 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */ 
/*       */     
/*  5999 */     byte b = 0; int j = i;
/*  6000 */     for (; b < paramArrayOfPoint3f.length && j < this.vertexData.length; 
/*  6001 */       b++, j += this.stride) {
/*  6002 */       (paramArrayOfPoint3f[b]).x = this.vertexData[j];
/*  6003 */       (paramArrayOfPoint3f[b]).y = this.vertexData[j + 1];
/*  6004 */       (paramArrayOfPoint3f[b]).z = this.vertexData[j + 2];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getVertexAttrs(int paramInt1, int paramInt2, Point4f[] paramArrayOfPoint4f) {
/*  6017 */     int i = this.stride * paramInt2 + this.vertexAttrOffsets[paramInt1];
/*       */ 
/*       */     
/*  6020 */     byte b = 0; int j = i;
/*  6021 */     for (; b < paramArrayOfPoint4f.length && j < this.vertexData.length; 
/*  6022 */       b++, j += this.stride) {
/*  6023 */       (paramArrayOfPoint4f[b]).x = this.vertexData[j];
/*  6024 */       (paramArrayOfPoint4f[b]).y = this.vertexData[j + 1];
/*  6025 */       (paramArrayOfPoint4f[b]).z = this.vertexData[j + 2];
/*  6026 */       (paramArrayOfPoint4f[b]).w = this.vertexData[j + 3];
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void updateData(GeometryUpdater paramGeometryUpdater) {
/*  6036 */     boolean bool = false;
/*       */ 
/*       */ 
/*       */     
/*  6040 */     this.geomLock.getLock();
/*       */     
/*  6042 */     this.inUpdater = true;
/*  6043 */     paramGeometryUpdater.updateData((Geometry)this.source);
/*  6044 */     this.inUpdater = false;
/*  6045 */     if ((this.vertexFormat & 0x80) != 0) {
/*  6046 */       if ((this.vertexFormat & 0x800) != 0) {
/*       */         
/*  6048 */         if (!(this instanceof IndexedGeometryArrayRetained) || (this.vertexFormat & 0x200) != 0)
/*       */         {
/*  6050 */           if ((this.vertexFormat & 0x100) != 0) {
/*  6051 */             setupMirrorInterleavedColorPointer(false);
/*  6052 */             bool = (this.interleavedFloatBufferImpl == null);
/*       */           } else {
/*       */             
/*  6055 */             setupMirrorColorPointer(this.vertexType & 0x3F0, false);
/*  6056 */             bool = ((this.vertexType & 0xF) == 0);
/*       */           }
/*       */         
/*       */         }
/*       */       }
/*  6061 */       else if (!(this instanceof IndexedGeometryArrayRetained) || (this.vertexFormat & 0x200) != 0) {
/*       */         
/*  6063 */         if ((this.vertexFormat & 0x100) != 0) {
/*  6064 */           setupMirrorInterleavedColorPointer(false);
/*  6065 */           bool = (this.interLeavedVertexData == null);
/*       */         } else {
/*       */           
/*  6068 */           setupMirrorVertexPointer(this.vertexType & 0xF);
/*  6069 */           setupMirrorColorPointer(this.vertexType & 0x3F0, false);
/*  6070 */           setupMirrorNormalPointer(this.vertexType & 0xC00);
/*  6071 */           setupMirrorTexCoordPointer(this.texCoordType);
/*  6072 */           setupMirrorVertexAttrPointer(this.vertexAttrType);
/*  6073 */           bool = ((this.vertexType & 0xF) == 0);
/*       */         } 
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6083 */       if ((this.vertexFormat & 0x2000) != 0) {
/*  6084 */         assert this instanceof IndexedGeometryArrayRetained;
/*       */         
/*  6086 */         if (((IndexedGeometryArrayRetained)this).getCoordIndicesRef() == null) {
/*  6087 */           bool = true;
/*       */         }
/*  6089 */         ((IndexedGeometryArrayRetained)this).doPostUpdaterUpdate();
/*       */       } 
/*       */     } 
/*       */     
/*  6093 */     this.dirtyFlag |= 0x8F;
/*  6094 */     this.colorChanged = 65535;
/*  6095 */     this.geomLock.unLock();
/*       */     
/*  6097 */     if (this.source != null && this.source.isLive()) {
/*  6098 */       processCoordsChanged(bool);
/*  6099 */       sendDataChangedMessage(true);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectBoundingBox(Point3d[] paramArrayOfPoint3d, BoundingBox paramBoundingBox, double[] paramArrayOfDouble, Point3d paramPoint3d) {
/*  6108 */     int[] arrayOfInt = new int[6];
/*       */     
/*       */     byte b;
/*  6111 */     for (b = 0; b < 6; b++)
/*  6112 */       arrayOfInt[b] = 0; 
/*  6113 */     for (b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  6114 */       if ((paramArrayOfPoint3d[b]).x >= paramBoundingBox.lower.x && (paramArrayOfPoint3d[b]).x <= paramBoundingBox.upper.x && (paramArrayOfPoint3d[b]).y >= paramBoundingBox.lower.y && (paramArrayOfPoint3d[b]).y <= paramBoundingBox.upper.y && (paramArrayOfPoint3d[b]).z >= paramBoundingBox.lower.z && (paramArrayOfPoint3d[b]).z <= paramBoundingBox.upper.z)
/*       */       {
/*       */ 
/*       */         
/*  6118 */         return true;
/*       */       }
/*  6120 */       if ((paramArrayOfPoint3d[b]).x < paramBoundingBox.lower.x)
/*  6121 */         arrayOfInt[0] = arrayOfInt[0] + 1; 
/*  6122 */       if ((paramArrayOfPoint3d[b]).y < paramBoundingBox.lower.y)
/*  6123 */         arrayOfInt[1] = arrayOfInt[1] + 1; 
/*  6124 */       if ((paramArrayOfPoint3d[b]).z < paramBoundingBox.lower.z)
/*  6125 */         arrayOfInt[2] = arrayOfInt[2] + 1; 
/*  6126 */       if ((paramArrayOfPoint3d[b]).x > paramBoundingBox.upper.x)
/*  6127 */         arrayOfInt[3] = arrayOfInt[3] + 1; 
/*  6128 */       if ((paramArrayOfPoint3d[b]).y > paramBoundingBox.upper.y)
/*  6129 */         arrayOfInt[4] = arrayOfInt[4] + 1; 
/*  6130 */       if ((paramArrayOfPoint3d[b]).z > paramBoundingBox.upper.z) {
/*  6131 */         arrayOfInt[5] = arrayOfInt[5] + 1;
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  6136 */     if (arrayOfInt[0] == paramArrayOfPoint3d.length || arrayOfInt[1] == paramArrayOfPoint3d.length || arrayOfInt[2] == paramArrayOfPoint3d.length || arrayOfInt[3] == paramArrayOfPoint3d.length || arrayOfInt[4] == paramArrayOfPoint3d.length || arrayOfInt[5] == paramArrayOfPoint3d.length)
/*       */     {
/*       */ 
/*       */       
/*  6140 */       return false;
/*       */     }
/*       */     
/*  6143 */     Point3d[] arrayOfPoint3d = new Point3d[4];
/*  6144 */     for (b = 0; b < 4; b++) {
/*  6145 */       arrayOfPoint3d[b] = new Point3d();
/*       */     }
/*       */     
/*  6148 */     arrayOfPoint3d[0].set(paramBoundingBox.lower.x, paramBoundingBox.lower.y, paramBoundingBox.lower.z);
/*  6149 */     arrayOfPoint3d[1].set(paramBoundingBox.lower.x, paramBoundingBox.lower.y, paramBoundingBox.upper.z);
/*  6150 */     arrayOfPoint3d[2].set(paramBoundingBox.lower.x, paramBoundingBox.upper.y, paramBoundingBox.upper.z);
/*  6151 */     arrayOfPoint3d[3].set(paramBoundingBox.lower.x, paramBoundingBox.upper.y, paramBoundingBox.lower.z);
/*       */ 
/*       */     
/*  6154 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d)) {
/*  6155 */       if (paramArrayOfDouble != null) {
/*  6156 */         computeMinDistance(arrayOfPoint3d, paramBoundingBox.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */       }
/*       */ 
/*       */       
/*  6160 */       return true;
/*       */     } 
/*       */ 
/*       */     
/*  6164 */     arrayOfPoint3d[0].set(paramBoundingBox.upper.x, paramBoundingBox.lower.y, paramBoundingBox.lower.z);
/*  6165 */     arrayOfPoint3d[1].set(paramBoundingBox.upper.x, paramBoundingBox.upper.y, paramBoundingBox.lower.z);
/*  6166 */     arrayOfPoint3d[2].set(paramBoundingBox.upper.x, paramBoundingBox.upper.y, paramBoundingBox.upper.z);
/*  6167 */     arrayOfPoint3d[3].set(paramBoundingBox.upper.x, paramBoundingBox.lower.y, paramBoundingBox.upper.z);
/*  6168 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d)) {
/*  6169 */       if (paramArrayOfDouble != null) {
/*  6170 */         computeMinDistance(arrayOfPoint3d, paramBoundingBox.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */       }
/*       */ 
/*       */       
/*  6174 */       return true;
/*       */     } 
/*       */ 
/*       */     
/*  6178 */     arrayOfPoint3d[0].set(paramBoundingBox.upper.x, paramBoundingBox.lower.y, paramBoundingBox.upper.z);
/*  6179 */     arrayOfPoint3d[1].set(paramBoundingBox.lower.x, paramBoundingBox.lower.y, paramBoundingBox.upper.z);
/*  6180 */     arrayOfPoint3d[2].set(paramBoundingBox.lower.x, paramBoundingBox.lower.y, paramBoundingBox.lower.z);
/*  6181 */     arrayOfPoint3d[3].set(paramBoundingBox.upper.x, paramBoundingBox.lower.y, paramBoundingBox.lower.z);
/*  6182 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d)) {
/*  6183 */       if (paramArrayOfDouble != null) {
/*  6184 */         computeMinDistance(arrayOfPoint3d, paramBoundingBox.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */       }
/*       */ 
/*       */       
/*  6188 */       return true;
/*       */     } 
/*       */     
/*  6191 */     arrayOfPoint3d[0].set(paramBoundingBox.upper.x, paramBoundingBox.upper.y, paramBoundingBox.upper.z);
/*  6192 */     arrayOfPoint3d[1].set(paramBoundingBox.upper.x, paramBoundingBox.upper.y, paramBoundingBox.lower.z);
/*  6193 */     arrayOfPoint3d[2].set(paramBoundingBox.lower.x, paramBoundingBox.upper.y, paramBoundingBox.lower.z);
/*  6194 */     arrayOfPoint3d[3].set(paramBoundingBox.lower.x, paramBoundingBox.upper.y, paramBoundingBox.upper.z);
/*  6195 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d)) {
/*  6196 */       if (paramArrayOfDouble != null) {
/*  6197 */         computeMinDistance(arrayOfPoint3d, paramBoundingBox.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */       }
/*       */ 
/*       */       
/*  6201 */       return true;
/*       */     } 
/*       */ 
/*       */     
/*  6205 */     arrayOfPoint3d[0].set(paramBoundingBox.upper.x, paramBoundingBox.upper.y, paramBoundingBox.upper.z);
/*  6206 */     arrayOfPoint3d[1].set(paramBoundingBox.lower.x, paramBoundingBox.upper.y, paramBoundingBox.upper.z);
/*  6207 */     arrayOfPoint3d[2].set(paramBoundingBox.lower.x, paramBoundingBox.lower.y, paramBoundingBox.upper.z);
/*  6208 */     arrayOfPoint3d[3].set(paramBoundingBox.upper.x, paramBoundingBox.lower.y, paramBoundingBox.upper.z);
/*  6209 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d)) {
/*  6210 */       if (paramArrayOfDouble != null) {
/*  6211 */         computeMinDistance(arrayOfPoint3d, paramBoundingBox.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */       }
/*       */ 
/*       */       
/*  6215 */       return true;
/*       */     } 
/*       */ 
/*       */     
/*  6219 */     arrayOfPoint3d[0].set(paramBoundingBox.upper.x, paramBoundingBox.upper.y, paramBoundingBox.lower.z);
/*  6220 */     arrayOfPoint3d[1].set(paramBoundingBox.upper.x, paramBoundingBox.lower.y, paramBoundingBox.lower.z);
/*  6221 */     arrayOfPoint3d[2].set(paramBoundingBox.lower.x, paramBoundingBox.lower.y, paramBoundingBox.lower.z);
/*  6222 */     arrayOfPoint3d[3].set(paramBoundingBox.lower.x, paramBoundingBox.upper.y, paramBoundingBox.lower.z);
/*  6223 */     if (intersectPolygon(arrayOfPoint3d, paramArrayOfPoint3d)) {
/*  6224 */       if (paramArrayOfDouble != null) {
/*  6225 */         computeMinDistance(arrayOfPoint3d, paramBoundingBox.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */       }
/*       */ 
/*       */       
/*  6229 */       return true;
/*       */     } 
/*  6231 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectBoundingSphere(Point3d[] paramArrayOfPoint3d, BoundingSphere paramBoundingSphere, double[] paramArrayOfDouble, Point3d paramPoint3d) {
/*  6241 */     Vector3d vector3d1 = new Vector3d();
/*       */ 
/*       */     
/*       */     byte b1;
/*       */     
/*  6246 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  6247 */       vector3d1.x = (paramArrayOfPoint3d[b1]).x - paramBoundingSphere.center.x;
/*  6248 */       vector3d1.y = (paramArrayOfPoint3d[b1]).y - paramBoundingSphere.center.y;
/*  6249 */       vector3d1.z = (paramArrayOfPoint3d[b1]).z - paramBoundingSphere.center.z;
/*       */       
/*  6251 */       if (vector3d1.length() <= paramBoundingSphere.radius) {
/*       */         
/*  6253 */         if (paramArrayOfDouble != null) {
/*  6254 */           computeMinDistance(paramArrayOfPoint3d, paramBoundingSphere.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*  6259 */         return true;
/*       */       } 
/*       */     } 
/*       */     
/*  6263 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  6264 */       boolean bool; if (b1 < paramArrayOfPoint3d.length - 1) {
/*  6265 */         bool = edgeIntersectSphere(paramBoundingSphere, paramArrayOfPoint3d[b1], paramArrayOfPoint3d[b1 + 1]);
/*       */       } else {
/*       */         
/*  6268 */         bool = edgeIntersectSphere(paramBoundingSphere, paramArrayOfPoint3d[b1], paramArrayOfPoint3d[0]);
/*       */       } 
/*  6270 */       if (bool == true) {
/*  6271 */         if (paramArrayOfDouble != null) {
/*  6272 */           computeMinDistance(paramArrayOfPoint3d, paramBoundingSphere.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6278 */         return true;
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  6283 */     if (paramArrayOfPoint3d.length < 3) {
/*  6284 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  6289 */     Vector3d vector3d2 = new Vector3d();
/*  6290 */     Vector3d vector3d3 = new Vector3d();
/*  6291 */     Vector3d vector3d4 = new Vector3d();
/*  6292 */     Vector3d vector3d5 = new Vector3d();
/*  6293 */     Point3d point3d = new Point3d();
/*       */ 
/*       */ 
/*       */     
/*  6297 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length - 1; ) {
/*  6298 */       vector3d2.x = (paramArrayOfPoint3d[b1 + 1]).x - (paramArrayOfPoint3d[b1]).x;
/*  6299 */       vector3d2.y = (paramArrayOfPoint3d[b1 + 1]).y - (paramArrayOfPoint3d[b1]).y;
/*  6300 */       vector3d2.z = (paramArrayOfPoint3d[b1 + 1]).z - (paramArrayOfPoint3d[b1++]).z;
/*  6301 */       if (vector3d2.length() > 0.0D)
/*       */         break; 
/*       */     } 
/*       */     byte b2;
/*  6305 */     for (b2 = b1; b2 < paramArrayOfPoint3d.length - 1; b2++) {
/*  6306 */       vector3d3.x = (paramArrayOfPoint3d[b2 + 1]).x - (paramArrayOfPoint3d[b2]).x;
/*  6307 */       vector3d3.y = (paramArrayOfPoint3d[b2 + 1]).y - (paramArrayOfPoint3d[b2]).y;
/*  6308 */       vector3d3.z = (paramArrayOfPoint3d[b2 + 1]).z - (paramArrayOfPoint3d[b2]).z;
/*  6309 */       if (vector3d3.length() > 0.0D) {
/*       */         break;
/*       */       }
/*       */     } 
/*  6313 */     if (b2 == paramArrayOfPoint3d.length - 1)
/*       */     {
/*  6315 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6325 */     vector3d4.cross(vector3d2, vector3d3);
/*       */     
/*  6327 */     double d1 = vector3d4.lengthSquared();
/*  6328 */     if (d1 == 0.0D)
/*       */     {
/*  6330 */       return false;
/*       */     }
/*       */     
/*  6333 */     vector3d5.x = (paramArrayOfPoint3d[0]).x - paramBoundingSphere.center.x;
/*  6334 */     vector3d5.y = (paramArrayOfPoint3d[0]).y - paramBoundingSphere.center.y;
/*  6335 */     vector3d5.z = (paramArrayOfPoint3d[0]).z - paramBoundingSphere.center.z;
/*       */     
/*  6337 */     double d3 = vector3d4.dot(vector3d5);
/*       */     
/*  6339 */     double d2 = Math.sqrt(d3 * d3 / d1);
/*       */     
/*  6341 */     if (d2 > paramBoundingSphere.radius) {
/*  6342 */       return false;
/*       */     }
/*       */     
/*  6345 */     double d4 = d3 / d1;
/*       */     
/*  6347 */     paramBoundingSphere.center.x += d4 * vector3d4.x;
/*  6348 */     paramBoundingSphere.center.y += d4 * vector3d4.y;
/*  6349 */     paramBoundingSphere.center.z += d4 * vector3d4.z;
/*       */ 
/*       */     
/*  6352 */     if (pointIntersectPolygon2D(vector3d4, paramArrayOfPoint3d, point3d)) {
/*  6353 */       if (paramArrayOfDouble != null) {
/*  6354 */         computeMinDistance(paramArrayOfPoint3d, paramBoundingSphere.getCenter(), vector3d4, paramArrayOfDouble, paramPoint3d);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  6359 */       return true;
/*       */     } 
/*  6361 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectBoundingPolytope(Point3d[] paramArrayOfPoint3d, BoundingPolytope paramBoundingPolytope, double[] paramArrayOfDouble, Point3d paramPoint3d) {
/*  6371 */     boolean bool1 = false;
/*       */     
/*  6373 */     Point4d point4d = new Point4d();
/*       */ 
/*       */     
/*  6376 */     double d1 = -1.0D;
/*       */     
/*  6378 */     if (paramArrayOfPoint3d.length == 2) {
/*       */       
/*  6380 */       if (paramBoundingPolytope.intersect(paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], point4d)) {
/*       */         
/*  6382 */         if (paramArrayOfDouble != null) {
/*  6383 */           paramPoint3d.x = point4d.x;
/*  6384 */           paramPoint3d.y = point4d.y;
/*  6385 */           paramPoint3d.z = point4d.z;
/*  6386 */           Point3d point3d = paramBoundingPolytope.getCenter();
/*  6387 */           double d3 = paramPoint3d.x - point3d.x;
/*  6388 */           double d4 = paramPoint3d.y - point3d.y;
/*  6389 */           double d5 = paramPoint3d.z - point3d.z;
/*  6390 */           paramArrayOfDouble[0] = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*       */         } 
/*  6392 */         return true;
/*       */       } 
/*  6394 */       return false;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6404 */     if (bool1) {
/*  6405 */       System.err.println("The value of the input vertices are: "); byte b;
/*  6406 */       for (b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  6407 */         System.err.println("The " + b + " th vertex is: " + paramArrayOfPoint3d[b]);
/*       */       }
/*       */       
/*  6410 */       System.err.println("The value of the input bounding Polytope's planes =");
/*  6411 */       for (b = 0; b < paramBoundingPolytope.planes.length; b++) {
/*  6412 */         System.err.println("The " + b + " th plane is: " + paramBoundingPolytope.planes[b]);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  6418 */     double[] arrayOfDouble = new double[4];
/*  6419 */     arrayOfDouble[0] = 0.8D; arrayOfDouble[1] = 0.9D; arrayOfDouble[2] = 1.1D; arrayOfDouble[3] = 1.2D;
/*       */     
/*  6421 */     boolean bool2 = true;
/*  6422 */     boolean bool3 = false;
/*       */     
/*  6424 */     if (bool3)
/*       */     {
/*  6426 */       for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  6427 */         for (byte b1 = 0; b1 < paramBoundingPolytope.planes.length; b1++) {
/*  6428 */           if ((paramBoundingPolytope.planes[b1]).x * (paramArrayOfPoint3d[b]).x + (paramBoundingPolytope.planes[b1]).y * (paramArrayOfPoint3d[b]).y + (paramBoundingPolytope.planes[b1]).z * (paramArrayOfPoint3d[b]).z <= d1 * (paramBoundingPolytope.planes[b1]).w) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  6433 */             bool2 = true;
/*       */           } else {
/*       */             
/*  6436 */             bool2 = false;
/*       */             break;
/*       */           } 
/*       */         } 
/*  6440 */         if (bool2) {
/*       */           
/*  6442 */           if (paramArrayOfDouble != null) {
/*  6443 */             computeMinDistance(paramArrayOfPoint3d, paramBoundingPolytope.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*  6448 */           return true;
/*       */         } 
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6456 */     int i = paramBoundingPolytope.planes.length + 2 + paramArrayOfPoint3d.length + 1;
/*  6457 */     int j = 1 + paramArrayOfPoint3d.length;
/*       */     
/*  6459 */     double[][] arrayOfDouble1 = new double[j][i];
/*       */     
/*       */     int k;
/*       */     
/*  6463 */     for (k = 0; k < paramBoundingPolytope.planes.length; k++) {
/*  6464 */       for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  6465 */         arrayOfDouble1[b][k] = -1.0D * ((paramBoundingPolytope.planes[k]).x * (paramArrayOfPoint3d[b]).x + (paramBoundingPolytope.planes[k]).y * (paramArrayOfPoint3d[b]).y + (paramBoundingPolytope.planes[k]).z * (paramArrayOfPoint3d[b]).z);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6472 */     for (k = 0; k < paramArrayOfPoint3d.length; k++) {
/*  6473 */       arrayOfDouble1[k][paramBoundingPolytope.planes.length] = -1.0D;
/*  6474 */       arrayOfDouble1[k][paramBoundingPolytope.planes.length + 1] = 1.0D;
/*       */       
/*  6476 */       for (int m = 0; m < paramArrayOfPoint3d.length; m++) {
/*  6477 */         if (k == m) {
/*  6478 */           arrayOfDouble1[k][m + paramBoundingPolytope.planes.length + 2] = 1.0D;
/*       */         } else {
/*  6480 */           arrayOfDouble1[k][m + paramBoundingPolytope.planes.length + 2] = 0.0D;
/*       */         } 
/*       */ 
/*       */         
/*  6484 */         arrayOfDouble1[k][i - 1] = arrayOfDouble[k];
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  6489 */     for (k = 0; k < paramBoundingPolytope.planes.length; k++) {
/*  6490 */       arrayOfDouble1[j - 1][k] = d1 * (paramBoundingPolytope.planes[k]).w;
/*       */     }
/*       */     
/*  6493 */     arrayOfDouble1[j - 1][paramBoundingPolytope.planes.length] = 1.0D;
/*  6494 */     arrayOfDouble1[j - 1][paramBoundingPolytope.planes.length + 1] = -1.0D;
/*  6495 */     for (k = 0; k < paramArrayOfPoint3d.length; k++) {
/*  6496 */       arrayOfDouble1[j - 1][paramBoundingPolytope.planes.length + 2 + k] = 0.0D;
/*       */     }
/*       */     
/*  6499 */     if (bool1) {
/*  6500 */       System.err.println("The value of the problem tableau is: ");
/*  6501 */       for (k = 0; k < arrayOfDouble1.length; k++) {
/*  6502 */         for (byte b = 0; b < arrayOfDouble1[0].length; b++) {
/*  6503 */           System.err.print(arrayOfDouble1[k][b] + "  ");
/*       */         }
/*  6505 */         System.err.println();
/*       */       } 
/*       */     } 
/*       */     
/*  6509 */     double d2 = generalStandardSimplexSolver(arrayOfDouble1, Double.NEGATIVE_INFINITY);
/*       */     
/*  6511 */     if (bool1) {
/*  6512 */       System.err.println("The value returned by the general standard simplex = " + d2);
/*       */     }
/*       */     
/*  6515 */     if (d2 == Double.POSITIVE_INFINITY) {
/*  6516 */       return false;
/*       */     }
/*  6518 */     if (paramArrayOfDouble != null) {
/*  6519 */       computeMinDistance(paramArrayOfPoint3d, paramBoundingPolytope.getCenter(), null, paramArrayOfDouble, paramPoint3d);
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  6524 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   double generalStandardSimplexSolver(double[][] paramArrayOfDouble, double paramDouble) {
/*  6535 */     boolean bool1 = false;
/*  6536 */     int i = paramArrayOfDouble.length;
/*  6537 */     int j = paramArrayOfDouble[0].length;
/*  6538 */     boolean bool2 = false;
/*       */ 
/*       */     
/*  6541 */     boolean bool3 = false;
/*       */ 
/*       */     
/*  6544 */     if (bool1) {
/*  6545 */       System.err.println("The number of rows is : " + i);
/*  6546 */       System.err.println("The number of columns is : " + j);
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  6551 */     while (!bool2) {
/*       */       
/*  6553 */       if (bool1) {
/*  6554 */         System.err.println("input problem tableau is:");
/*  6555 */         for (byte b = 0; b < i; b++) {
/*  6556 */           for (byte b3 = 0; b3 < j; b3++) {
/*  6557 */             System.err.println("kth, jth value is:" + b + " " + b3 + " : " + paramArrayOfDouble[b][b3]);
/*       */           }
/*       */         } 
/*       */       } 
/*       */       
/*       */       byte b1;
/*       */       
/*       */       byte b2;
/*       */       double d;
/*  6566 */       for (b1 = 0, d = 0.0D, b2 = -1; b1 < j - 1; b1++) {
/*       */         
/*  6568 */         double d1 = paramArrayOfDouble[i - 1][b1];
/*  6569 */         if (d1 < d) {
/*  6570 */           d = d1;
/*  6571 */           b2 = b1;
/*       */         } 
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  6577 */       if (b2 == -1)
/*       */       {
/*       */         
/*  6580 */         bool2 = true;
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  6585 */       if (!bool2) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6596 */         double d2 = Double.POSITIVE_INFINITY;
/*  6597 */         double d1 = 0.0D;
/*  6598 */         byte b = -1;
/*       */ 
/*       */ 
/*       */         
/*  6602 */         for (b1 = 0; b1 < i - 1; b1++) {
/*  6603 */           double d4 = paramArrayOfDouble[b1][b2];
/*  6604 */           double d5 = paramArrayOfDouble[b1][j - 1];
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  6610 */           if (d4 == 0.0D) {
/*  6611 */             if (bool1) {
/*  6612 */               System.err.println("Division by zero has occurred");
/*  6613 */               System.err.println("Within the linear program solver");
/*  6614 */               System.err.println("Ignoring the zero as a potential pivot");
/*       */             } 
/*  6616 */           } else if (d4 < 0.0D || d5 < 0.0D) {
/*  6617 */             if (bool1) {
/*  6618 */               System.err.println("Ignoring cases where element is negative");
/*  6619 */               System.err.println("The value of element is: " + d4);
/*  6620 */               System.err.println("The value of end Element is: " + d5);
/*       */             } 
/*       */           } else {
/*  6623 */             d1 = d5 / d4;
/*  6624 */             if (bool1) {
/*  6625 */               System.err.println("The value of element is: " + d4);
/*  6626 */               System.err.println("The value of endElement is: " + d5);
/*  6627 */               System.err.println("The value of ratio is: " + d1);
/*  6628 */               System.err.println("The value of prevRatio is: " + d2);
/*  6629 */               System.err.println("Value of ratio <= prevRatio is :" + ((d1 <= d2) ? 1 : 0));
/*       */             } 
/*       */             
/*  6632 */             if (d1 <= d2) {
/*  6633 */               if (bool1) {
/*  6634 */                 System.err.println("updating prevRatio with ratio");
/*       */               }
/*  6636 */               d2 = d1;
/*  6637 */               b = b1;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6646 */         if (b == -1) {
/*  6647 */           if (bool1) {
/*  6648 */             System.err.println("UNABLE TO FIND SOLUTION");
/*  6649 */             System.err.println("The system is infeasable or unbounded");
/*       */           } 
/*  6651 */           return Double.POSITIVE_INFINITY;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6661 */         double d3 = paramArrayOfDouble[b][b2];
/*       */         
/*  6663 */         if (bool1) {
/*  6664 */           System.err.println("The value of row index is: " + b);
/*  6665 */           System.err.println("The value of col index is: " + b2);
/*  6666 */           System.err.println("The value of pivotValue is: " + d3);
/*       */         } 
/*       */         
/*  6669 */         for (b1 = 0; b1 < j; b1++) {
/*  6670 */           paramArrayOfDouble[b][b1] = paramArrayOfDouble[b][b1] / d3;
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6676 */         for (b1 = 0; b1 < i; b1++) {
/*  6677 */           if (b1 != b) {
/*  6678 */             double d4 = paramArrayOfDouble[b1][b2];
/*  6679 */             for (byte b3 = 0; b3 < j; b3++) {
/*  6680 */               paramArrayOfDouble[b1][b3] = paramArrayOfDouble[b1][b3] - d4 * paramArrayOfDouble[b][b3];
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  6688 */     return paramArrayOfDouble[i - 1][j - 1];
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean edgeIntersectSphere(BoundingSphere paramBoundingSphere, Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*  6697 */     Vector3d vector3d1 = new Vector3d();
/*  6698 */     Vector3d vector3d2 = new Vector3d();
/*       */     
/*  6700 */     vector3d1.x = paramPoint3d2.x - paramPoint3d1.x;
/*  6701 */     vector3d1.y = paramPoint3d2.y - paramPoint3d1.y;
/*  6702 */     vector3d1.z = paramPoint3d2.z - paramPoint3d1.z;
/*       */     
/*  6704 */     vector3d2.x = paramBoundingSphere.center.x - paramPoint3d1.x;
/*  6705 */     vector3d2.y = paramBoundingSphere.center.y - paramPoint3d1.y;
/*  6706 */     vector3d2.z = paramBoundingSphere.center.z - paramPoint3d1.z;
/*       */     
/*  6708 */     double d4 = vector3d1.dot(vector3d2);
/*       */     
/*  6710 */     if (d4 < 0.0D) {
/*  6711 */       return false;
/*       */     }
/*       */     
/*  6714 */     double d1 = vector3d1.lengthSquared();
/*  6715 */     double d2 = d4 * d4 / d1;
/*       */     
/*  6717 */     if (d2 < d1) {
/*  6718 */       return false;
/*       */     }
/*       */     
/*  6721 */     double d5 = paramBoundingSphere.radius * paramBoundingSphere.radius;
/*  6722 */     double d3 = vector3d2.lengthSquared();
/*       */     
/*  6724 */     if (d3 - d2 <= d5) {
/*  6725 */       return true;
/*       */     }
/*       */     
/*  6728 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  6735 */   double det2D(Point2d paramPoint2d1, Point2d paramPoint2d2, Point2d paramPoint2d3) { return (paramPoint2d3.x - paramPoint2d1.x) * (paramPoint2d1.y - paramPoint2d2.y) + (paramPoint2d1.y - paramPoint2d3.y) * (paramPoint2d1.x - paramPoint2d2.x); }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean pointIntersectPolygon2D(Vector3d paramVector3d, Point3d[] paramArrayOfPoint3d, Point3d paramPoint3d) {
/*       */     byte b3;
/*  6745 */     Point2d[] arrayOfPoint2d = new Point2d[paramArrayOfPoint3d.length];
/*  6746 */     Point2d point2d = new Point2d();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6755 */     double d1 = Math.abs(paramVector3d.x);
/*  6756 */     double d2 = Math.abs(paramVector3d.y);
/*  6757 */     double d3 = Math.abs(paramVector3d.z);
/*       */     
/*  6759 */     if (d1 > d2) {
/*  6760 */       b3 = 0;
/*       */     } else {
/*  6762 */       b3 = 1;
/*       */     } 
/*  6764 */     if (!b3) {
/*  6765 */       if (d1 < d3) {
/*  6766 */         b3 = 2;
/*       */       }
/*  6768 */     } else if (b3 == 1 && 
/*  6769 */       d2 < d3) {
/*  6770 */       b3 = 2;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  6775 */     for (byte b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  6776 */       arrayOfPoint2d[b1] = new Point2d();
/*       */       
/*  6778 */       switch (b3) {
/*       */         case 0:
/*  6780 */           (arrayOfPoint2d[b1]).x = (paramArrayOfPoint3d[b1]).y;
/*  6781 */           (arrayOfPoint2d[b1]).y = (paramArrayOfPoint3d[b1]).z;
/*       */           break;
/*       */         
/*       */         case 1:
/*  6785 */           (arrayOfPoint2d[b1]).x = (paramArrayOfPoint3d[b1]).x;
/*  6786 */           (arrayOfPoint2d[b1]).y = (paramArrayOfPoint3d[b1]).z;
/*       */           break;
/*       */         
/*       */         case 2:
/*  6790 */           (arrayOfPoint2d[b1]).x = (paramArrayOfPoint3d[b1]).x;
/*  6791 */           (arrayOfPoint2d[b1]).y = (paramArrayOfPoint3d[b1]).y;
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     } 
/*  6799 */     switch (b3) {
/*       */       case 0:
/*  6801 */         point2d.x = paramPoint3d.y;
/*  6802 */         point2d.y = paramPoint3d.z;
/*       */         break;
/*       */       
/*       */       case 1:
/*  6806 */         point2d.x = paramPoint3d.x;
/*  6807 */         point2d.y = paramPoint3d.z;
/*       */         break;
/*       */       
/*       */       case 2:
/*  6811 */         point2d.x = paramPoint3d.x;
/*  6812 */         point2d.y = paramPoint3d.y;
/*       */         break;
/*       */     } 
/*       */ 
/*       */     
/*  6817 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d.length; b2++) {
/*  6818 */       if (b2 < paramArrayOfPoint3d.length - 1) {
/*  6819 */         if (det2D(arrayOfPoint2d[b2], arrayOfPoint2d[b2 + true], point2d) <= 0.0D)
/*       */         {
/*       */           
/*  6822 */           return false;
/*       */         }
/*  6824 */       } else if (det2D(arrayOfPoint2d[b2], arrayOfPoint2d[0], point2d) <= 0.0D) {
/*       */ 
/*       */         
/*  6827 */         return false;
/*       */       } 
/*       */     } 
/*  6830 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean edgeIntersectPlane(Vector3d paramVector3d, Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4) {
/*  6839 */     Vector3d vector3d1 = new Vector3d();
/*  6840 */     Vector3d vector3d2 = new Vector3d();
/*       */ 
/*       */ 
/*       */     
/*  6844 */     vector3d1.set(paramPoint3d1);
/*  6845 */     double d1 = paramVector3d.dot(vector3d1);
/*       */     
/*  6847 */     vector3d2.x = paramPoint3d3.x - paramPoint3d2.x;
/*  6848 */     vector3d2.y = paramPoint3d3.y - paramPoint3d2.y;
/*  6849 */     vector3d2.z = paramPoint3d3.z - paramPoint3d2.z;
/*       */     
/*  6851 */     double d2 = paramVector3d.dot(vector3d2);
/*       */ 
/*       */     
/*  6854 */     if (d2 == 0.0D)
/*       */     {
/*  6856 */       return false;
/*       */     }
/*       */     
/*  6859 */     vector3d1.set(paramPoint3d2);
/*       */     
/*  6861 */     double d3 = (d1 - paramVector3d.dot(vector3d1)) / d2;
/*       */ 
/*       */ 
/*       */     
/*  6865 */     if (d3 < 0.0D || d3 > 1.0D)
/*       */     {
/*  6867 */       return false;
/*       */     }
/*       */     
/*  6870 */     paramPoint3d2.x += d3 * vector3d2.x;
/*  6871 */     paramPoint3d2.y += d3 * vector3d2.y;
/*  6872 */     paramPoint3d2.z += d3 * vector3d2.z;
/*       */     
/*  6874 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean edgeIntersectPolygon2D(Vector3d paramVector3d, Point3d[] paramArrayOfPoint3d1, Point3d[] paramArrayOfPoint3d2) {
/*       */     byte b3;
/*  6884 */     Point2d[] arrayOfPoint2d1 = new Point2d[paramArrayOfPoint3d1.length];
/*  6885 */     Point2d[] arrayOfPoint2d2 = new Point2d[2];
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6894 */     double d1 = Math.abs(paramVector3d.x);
/*  6895 */     double d2 = Math.abs(paramVector3d.y);
/*  6896 */     double d3 = Math.abs(paramVector3d.z);
/*       */     
/*  6898 */     if (d1 > d2) {
/*  6899 */       b3 = 0;
/*       */     } else {
/*  6901 */       b3 = 1;
/*       */     } 
/*  6903 */     if (!b3) {
/*  6904 */       if (d1 < d3) {
/*  6905 */         b3 = 2;
/*       */       }
/*  6907 */     } else if (b3 == 1 && 
/*  6908 */       d2 < d3) {
/*  6909 */       b3 = 2;
/*       */     } 
/*       */     
/*       */     byte b1;
/*       */     
/*  6914 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/*  6915 */       arrayOfPoint2d1[b1] = new Point2d();
/*       */       
/*  6917 */       switch (b3) {
/*       */         case 0:
/*  6919 */           (arrayOfPoint2d1[b1]).x = (paramArrayOfPoint3d1[b1]).y;
/*  6920 */           (arrayOfPoint2d1[b1]).y = (paramArrayOfPoint3d1[b1]).z;
/*       */           break;
/*       */         
/*       */         case 1:
/*  6924 */           (arrayOfPoint2d1[b1]).x = (paramArrayOfPoint3d1[b1]).x;
/*  6925 */           (arrayOfPoint2d1[b1]).y = (paramArrayOfPoint3d1[b1]).z;
/*       */           break;
/*       */         
/*       */         case 2:
/*  6929 */           (arrayOfPoint2d1[b1]).x = (paramArrayOfPoint3d1[b1]).x;
/*  6930 */           (arrayOfPoint2d1[b1]).y = (paramArrayOfPoint3d1[b1]).y;
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */     
/*       */     } 
/*  6937 */     for (b1 = 0; b1 < 2; b1++) {
/*  6938 */       arrayOfPoint2d2[b1] = new Point2d();
/*  6939 */       switch (b3) {
/*       */         case 0:
/*  6941 */           (arrayOfPoint2d2[b1]).x = (paramArrayOfPoint3d2[b1]).y;
/*  6942 */           (arrayOfPoint2d2[b1]).y = (paramArrayOfPoint3d2[b1]).z;
/*       */           break;
/*       */         
/*       */         case 1:
/*  6946 */           (arrayOfPoint2d2[b1]).x = (paramArrayOfPoint3d2[b1]).x;
/*  6947 */           (arrayOfPoint2d2[b1]).y = (paramArrayOfPoint3d2[b1]).z;
/*       */           break;
/*       */         
/*       */         case 2:
/*  6951 */           (arrayOfPoint2d2[b1]).x = (paramArrayOfPoint3d2[b1]).x;
/*  6952 */           (arrayOfPoint2d2[b1]).y = (paramArrayOfPoint3d2[b1]).y;
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     } 
/*  6960 */     boolean[][] arrayOfBoolean = new boolean[2][paramArrayOfPoint3d1.length];
/*       */ 
/*       */     
/*  6963 */     for (byte b2 = 0; b2 < paramArrayOfPoint3d1.length; b2++) {
/*  6964 */       for (b1 = 0; b1 < 2; b1++) {
/*  6965 */         if (b2 < paramArrayOfPoint3d1.length - 1) {
/*  6966 */           arrayOfBoolean[b1][b2] = (det2D(arrayOfPoint2d1[b2], arrayOfPoint2d1[b2 + true], arrayOfPoint2d2[b1]) < 0.0D);
/*       */         } else {
/*  6968 */           arrayOfBoolean[b1][b2] = (det2D(arrayOfPoint2d1[b2], arrayOfPoint2d1[0], arrayOfPoint2d2[b1]) < 0.0D);
/*       */         } 
/*       */       } 
/*  6971 */       if (!arrayOfBoolean[0][b2] && !arrayOfBoolean[1][b2]) {
/*  6972 */         return false;
/*       */       }
/*       */     } 
/*  6975 */     boolean bool = true;
/*  6976 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/*  6977 */       if (!arrayOfBoolean[0][b1]) {
/*  6978 */         bool = false;
/*       */         
/*       */         break;
/*       */       } 
/*       */     } 
/*  6983 */     if (bool == true) {
/*  6984 */       return true;
/*       */     }
/*  6986 */     bool = true;
/*  6987 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/*  6988 */       if (!arrayOfBoolean[1][b1]) {
/*  6989 */         bool = false;
/*       */         
/*       */         break;
/*       */       } 
/*       */     } 
/*  6994 */     if (bool == true) {
/*  6995 */       return true;
/*       */     }
/*       */     
/*  6998 */     byte b4 = 0;
/*  6999 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length; b1++) {
/*  7000 */       if (det2D(arrayOfPoint2d2[0], arrayOfPoint2d2[1], arrayOfPoint2d1[b1]) < 0.0D) {
/*  7001 */         b4++;
/*       */       }
/*       */     } 
/*  7004 */     if (b4 == 0 || b4 == paramArrayOfPoint3d1.length) {
/*  7005 */       return false;
/*       */     }
/*  7007 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   double getCompValue(Point3d paramPoint3d, int paramInt) {
/*  7014 */     switch (paramInt) { case 0:
/*  7015 */         return paramPoint3d.x;
/*  7016 */       case 1: return paramPoint3d.y; }
/*       */ 
/*       */     
/*  7019 */     return paramPoint3d.z;
/*       */   }
/*       */   
/*       */   double getCompValue(Point3d paramPoint3d1, Point3d paramPoint3d2, int paramInt) {
/*  7023 */     switch (paramInt) { case 0:
/*  7024 */         return paramPoint3d1.x - paramPoint3d2.x;
/*  7025 */       case 1: return paramPoint3d1.y - paramPoint3d2.y; }
/*       */ 
/*       */     
/*  7028 */     return paramPoint3d1.z - paramPoint3d2.z;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean pointInTri(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, Vector3d paramVector3d) {
/*       */     byte b2, b1;
/*  7040 */     double d1 = Math.abs(paramVector3d.x);
/*  7041 */     double d2 = Math.abs(paramVector3d.y);
/*  7042 */     double d3 = Math.abs(paramVector3d.z);
/*       */     
/*  7044 */     if (d1 > d2) {
/*  7045 */       if (d1 > d3) {
/*  7046 */         b1 = 1;
/*  7047 */         b2 = 2;
/*       */       } else {
/*       */         
/*  7050 */         b1 = 0;
/*  7051 */         b2 = 1;
/*       */       }
/*       */     
/*  7054 */     } else if (d3 > d2) {
/*  7055 */       b1 = 0;
/*  7056 */       b2 = 1;
/*       */     } else {
/*       */       
/*  7059 */       b1 = 0;
/*  7060 */       b2 = 2;
/*       */     } 
/*       */     
/*  7063 */     return pointInTri(paramPoint3d1, paramPoint3d2, paramPoint3d3, paramPoint3d4, b1, b2);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean pointInTri(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, int paramInt1, int paramInt2) {
/*  7073 */     double d1 = getCompValue(paramPoint3d3, paramPoint3d2, paramInt2);
/*  7074 */     double d2 = -getCompValue(paramPoint3d3, paramPoint3d2, paramInt1);
/*  7075 */     double d3 = -d1 * getCompValue(paramPoint3d2, paramInt1) - d2 * getCompValue(paramPoint3d2, paramInt2);
/*  7076 */     double d4 = d1 * getCompValue(paramPoint3d1, paramInt1) + d2 * getCompValue(paramPoint3d1, paramInt2) + d3;
/*       */     
/*  7078 */     d1 = getCompValue(paramPoint3d4, paramPoint3d3, paramInt2);
/*  7079 */     d2 = -getCompValue(paramPoint3d4, paramPoint3d3, paramInt1);
/*  7080 */     d3 = -d1 * getCompValue(paramPoint3d3, paramInt1) - d2 * getCompValue(paramPoint3d3, paramInt2);
/*  7081 */     double d5 = d1 * getCompValue(paramPoint3d1, paramInt1) + d2 * getCompValue(paramPoint3d1, paramInt2) + d3;
/*       */     
/*  7083 */     d1 = getCompValue(paramPoint3d2, paramPoint3d4, paramInt2);
/*  7084 */     d2 = -getCompValue(paramPoint3d2, paramPoint3d4, paramInt1);
/*  7085 */     d3 = -d1 * getCompValue(paramPoint3d4, paramInt1) - d2 * getCompValue(paramPoint3d4, paramInt2);
/*  7086 */     double d6 = d1 * getCompValue(paramPoint3d1, paramInt1) + d2 * getCompValue(paramPoint3d1, paramInt2) + d3;
/*       */     
/*  7088 */     if (d4 * d5 > 0.0D && 
/*  7089 */       d4 * d6 > 0.0D) {
/*  7090 */       return true;
/*       */     }
/*       */     
/*  7093 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean edgeAgainstEdge(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2) {
/*  7103 */     double d1 = getCompValue(paramPoint3d2, paramPoint3d3, paramInt1);
/*  7104 */     double d2 = getCompValue(paramPoint3d2, paramPoint3d3, paramInt2);
/*  7105 */     double d3 = getCompValue(paramPoint3d1, paramPoint3d2, paramInt1);
/*  7106 */     double d4 = getCompValue(paramPoint3d1, paramPoint3d2, paramInt2);
/*       */     
/*  7108 */     double d6 = paramDouble2 * d1 - paramDouble1 * d2;
/*  7109 */     double d5 = d2 * d3 - d1 * d4;
/*  7110 */     if ((d6 > 0.0D && d5 >= 0.0D && d5 <= d6) || (d6 < 0.0D && d5 <= 0.0D && d5 >= d6)) {
/*  7111 */       double d = paramDouble1 * d4 - paramDouble2 * d3;
/*  7112 */       if (d6 > 0.0D) {
/*  7113 */         if (d >= 0.0D && d <= d6) {
/*  7114 */           return true;
/*       */         }
/*       */       }
/*  7117 */       else if (d <= 0.0D && d >= d6) {
/*  7118 */         return true;
/*       */       } 
/*       */     } 
/*       */     
/*  7122 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean edgeAgainstTriEdges(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, Point3d paramPoint3d5, int paramInt1, int paramInt2) {
/*  7132 */     double d1 = getCompValue(paramPoint3d2, paramPoint3d1, paramInt1);
/*  7133 */     double d2 = getCompValue(paramPoint3d2, paramPoint3d1, paramInt2);
/*       */ 
/*       */     
/*  7136 */     if (edgeAgainstEdge(paramPoint3d1, paramPoint3d3, paramPoint3d4, d1, d2, paramInt1, paramInt2)) {
/*  7137 */       return true;
/*       */     }
/*  7139 */     if (edgeAgainstEdge(paramPoint3d1, paramPoint3d4, paramPoint3d5, d1, d2, paramInt1, paramInt2)) {
/*  7140 */       return true;
/*       */     }
/*  7142 */     if (edgeAgainstEdge(paramPoint3d1, paramPoint3d5, paramPoint3d3, d1, d2, paramInt1, paramInt2)) {
/*  7143 */       return true;
/*       */     }
/*  7145 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean coplanarTriTri(Vector3d paramVector3d, Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, Point3d paramPoint3d5, Point3d paramPoint3d6) {
/*       */     byte b2, b1;
/*  7157 */     double d1 = Math.abs(paramVector3d.x);
/*  7158 */     double d2 = Math.abs(paramVector3d.y);
/*  7159 */     double d3 = Math.abs(paramVector3d.z);
/*       */     
/*  7161 */     if (d1 > d2) {
/*  7162 */       if (d1 > d3) {
/*  7163 */         b1 = 1;
/*  7164 */         b2 = 2;
/*       */       } else {
/*       */         
/*  7167 */         b1 = 0;
/*  7168 */         b2 = 1;
/*       */       }
/*       */     
/*       */     }
/*  7172 */     else if (d3 > d2) {
/*  7173 */       b1 = 0;
/*  7174 */       b2 = 1;
/*       */     } else {
/*       */       
/*  7177 */       b1 = 0;
/*  7178 */       b2 = 2;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  7183 */     if (edgeAgainstTriEdges(paramPoint3d1, paramPoint3d2, paramPoint3d4, paramPoint3d5, paramPoint3d6, b1, b2)) {
/*  7184 */       return true;
/*       */     }
/*  7186 */     if (edgeAgainstTriEdges(paramPoint3d2, paramPoint3d3, paramPoint3d4, paramPoint3d5, paramPoint3d6, b1, b2)) {
/*  7187 */       return true;
/*       */     }
/*  7189 */     if (edgeAgainstTriEdges(paramPoint3d3, paramPoint3d1, paramPoint3d4, paramPoint3d5, paramPoint3d6, b1, b2)) {
/*  7190 */       return true;
/*       */     }
/*       */     
/*  7193 */     if (pointInTri(paramPoint3d1, paramPoint3d4, paramPoint3d5, paramPoint3d6, b1, b2)) {
/*  7194 */       return true;
/*       */     }
/*  7196 */     if (pointInTri(paramPoint3d4, paramPoint3d1, paramPoint3d2, paramPoint3d3, b1, b2)) {
/*  7197 */       return true;
/*       */     }
/*  7199 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectTriPnt(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4) {
/*       */     byte b2, b1;
/*  7208 */     Vector3d vector3d1 = new Vector3d();
/*  7209 */     Vector3d vector3d2 = new Vector3d();
/*  7210 */     Vector3d vector3d3 = new Vector3d();
/*  7211 */     Vector3d vector3d4 = new Vector3d();
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7216 */     vector3d1.x = paramPoint3d2.x - paramPoint3d1.x;
/*  7217 */     vector3d1.y = paramPoint3d2.y - paramPoint3d1.y;
/*  7218 */     vector3d1.z = paramPoint3d2.z - paramPoint3d1.z;
/*       */     
/*  7220 */     vector3d2.x = paramPoint3d3.x - paramPoint3d1.x;
/*  7221 */     vector3d2.y = paramPoint3d3.y - paramPoint3d1.y;
/*  7222 */     vector3d2.z = paramPoint3d3.z - paramPoint3d1.z;
/*       */     
/*  7224 */     vector3d3.cross(vector3d1, vector3d2);
/*       */     
/*  7226 */     if (vector3d3.length() == 0.0D)
/*       */     {
/*  7228 */       return false;
/*       */     }
/*       */     
/*  7231 */     vector3d4.set(paramPoint3d1);
/*  7232 */     double d1 = -vector3d3.dot(vector3d4);
/*       */ 
/*       */     
/*  7235 */     vector3d4.set(paramPoint3d4);
/*  7236 */     double d2 = vector3d3.dot(vector3d4) + d1;
/*       */ 
/*       */     
/*  7239 */     if (Math.abs(d2) < 1.0E-13D) d2 = 0.0D;
/*       */ 
/*       */     
/*  7242 */     if (d2 != 0.0D) {
/*  7243 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7251 */     double d3 = Math.abs(vector3d3.x);
/*  7252 */     double d4 = Math.abs(vector3d3.y);
/*  7253 */     double d5 = Math.abs(vector3d3.z);
/*       */     
/*  7255 */     if (d3 > d4) {
/*  7256 */       if (d3 > d5) {
/*  7257 */         b1 = 1;
/*  7258 */         b2 = 2;
/*       */       } else {
/*       */         
/*  7261 */         b1 = 0;
/*  7262 */         b2 = 1;
/*       */       }
/*       */     
/*       */     }
/*  7266 */     else if (d5 > d4) {
/*  7267 */       b1 = 0;
/*  7268 */       b2 = 1;
/*       */     } else {
/*       */       
/*  7271 */       b1 = 0;
/*  7272 */       b2 = 2;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7278 */     if (pointInTri(paramPoint3d4, paramPoint3d1, paramPoint3d2, paramPoint3d3, b1, b2)) {
/*  7279 */       return true;
/*       */     }
/*       */     
/*  7282 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectTriTri(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, Point3d paramPoint3d5, Point3d paramPoint3d6) {
/*  7307 */     Vector3d vector3d1 = new Vector3d();
/*  7308 */     Vector3d vector3d2 = new Vector3d();
/*  7309 */     Vector3d vector3d3 = new Vector3d();
/*  7310 */     Vector3d vector3d4 = new Vector3d();
/*  7311 */     Vector3d vector3d5 = new Vector3d();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7317 */     double d13 = 0.0D, d14 = 0.0D, d15 = 0.0D;
/*  7318 */     double d16 = 0.0D, d17 = 0.0D, d18 = 0.0D;
/*       */ 
/*       */ 
/*       */     
/*  7322 */     vector3d1.x = paramPoint3d2.x - paramPoint3d1.x;
/*  7323 */     vector3d1.y = paramPoint3d2.y - paramPoint3d1.y;
/*  7324 */     vector3d1.z = paramPoint3d2.z - paramPoint3d1.z;
/*       */     
/*  7326 */     vector3d2.x = paramPoint3d3.x - paramPoint3d1.x;
/*  7327 */     vector3d2.y = paramPoint3d3.y - paramPoint3d1.y;
/*  7328 */     vector3d2.z = paramPoint3d3.z - paramPoint3d1.z;
/*       */     
/*  7330 */     vector3d3.cross(vector3d1, vector3d2);
/*       */     
/*  7332 */     if (vector3d3.length() == 0.0D)
/*       */     {
/*  7334 */       return false;
/*       */     }
/*       */     
/*  7337 */     vector3d5.set(paramPoint3d1);
/*  7338 */     double d1 = -vector3d3.dot(vector3d5);
/*       */ 
/*       */ 
/*       */     
/*  7342 */     vector3d5.set(paramPoint3d4);
/*  7343 */     double d3 = vector3d3.dot(vector3d5) + d1;
/*  7344 */     vector3d5.set(paramPoint3d5);
/*  7345 */     double d4 = vector3d3.dot(vector3d5) + d1;
/*  7346 */     vector3d5.set(paramPoint3d6);
/*  7347 */     double d5 = vector3d3.dot(vector3d5) + d1;
/*       */ 
/*       */     
/*  7350 */     if (Math.abs(d3) < 1.0E-13D) d3 = 0.0D; 
/*  7351 */     if (Math.abs(d4) < 1.0E-13D) d4 = 0.0D; 
/*  7352 */     if (Math.abs(d5) < 1.0E-13D) d5 = 0.0D;
/*       */     
/*  7354 */     double d9 = d3 * d4;
/*  7355 */     double d10 = d3 * d5;
/*       */ 
/*       */ 
/*       */     
/*  7359 */     if (d9 > 0.0D && d10 > 0.0D)
/*       */     {
/*  7361 */       return false;
/*       */     }
/*       */ 
/*       */     
/*  7365 */     vector3d1.x = paramPoint3d5.x - paramPoint3d4.x;
/*  7366 */     vector3d1.y = paramPoint3d5.y - paramPoint3d4.y;
/*  7367 */     vector3d1.z = paramPoint3d5.z - paramPoint3d4.z;
/*       */     
/*  7369 */     vector3d2.x = paramPoint3d6.x - paramPoint3d4.x;
/*  7370 */     vector3d2.y = paramPoint3d6.y - paramPoint3d4.y;
/*  7371 */     vector3d2.z = paramPoint3d6.z - paramPoint3d4.z;
/*       */     
/*  7373 */     vector3d4.cross(vector3d1, vector3d2);
/*       */     
/*  7375 */     if (vector3d4.length() == 0.0D)
/*       */     {
/*  7377 */       return false;
/*       */     }
/*       */     
/*  7380 */     vector3d5.set(paramPoint3d4);
/*  7381 */     double d2 = -vector3d4.dot(vector3d5);
/*       */ 
/*       */ 
/*       */     
/*  7385 */     vector3d5.set(paramPoint3d1);
/*  7386 */     double d6 = vector3d4.dot(vector3d5) + d2;
/*  7387 */     vector3d5.set(paramPoint3d2);
/*  7388 */     double d7 = vector3d4.dot(vector3d5) + d2;
/*  7389 */     vector3d5.set(paramPoint3d3);
/*  7390 */     double d8 = vector3d4.dot(vector3d5) + d2;
/*       */ 
/*       */     
/*  7393 */     if (Math.abs(d6) < 1.0E-13D) d6 = 0.0D; 
/*  7394 */     if (Math.abs(d7) < 1.0E-13D) d7 = 0.0D; 
/*  7395 */     if (Math.abs(d8) < 1.0E-13D) d8 = 0.0D;
/*       */     
/*  7397 */     double d11 = d6 * d7;
/*  7398 */     double d12 = d6 * d8;
/*       */ 
/*       */ 
/*       */     
/*  7402 */     if (d11 > 0.0D && d12 > 0.0D)
/*       */     {
/*  7404 */       return false;
/*       */     }
/*       */     
/*  7407 */     vector3d5.cross(vector3d3, vector3d4);
/*       */ 
/*       */     
/*  7410 */     double d21 = Math.abs(vector3d5.x);
/*  7411 */     byte b = 0;
/*  7412 */     double d19 = Math.abs(vector3d5.y);
/*  7413 */     double d20 = Math.abs(vector3d5.z);
/*  7414 */     if (d19 > d21) {
/*  7415 */       d21 = d19;
/*  7416 */       b = 1;
/*       */     } 
/*  7418 */     if (d20 > d21) {
/*  7419 */       d21 = d20;
/*  7420 */       b = 2;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  7425 */     switch (b) {
/*       */       case 0:
/*  7427 */         d13 = paramPoint3d1.x;
/*  7428 */         d14 = paramPoint3d2.x;
/*  7429 */         d15 = paramPoint3d3.x;
/*       */         
/*  7431 */         d16 = paramPoint3d4.x;
/*  7432 */         d17 = paramPoint3d5.x;
/*  7433 */         d18 = paramPoint3d6.x;
/*       */         break;
/*       */       case 1:
/*  7436 */         d13 = paramPoint3d1.y;
/*  7437 */         d14 = paramPoint3d2.y;
/*  7438 */         d15 = paramPoint3d3.y;
/*       */         
/*  7440 */         d16 = paramPoint3d4.y;
/*  7441 */         d17 = paramPoint3d5.y;
/*  7442 */         d18 = paramPoint3d6.y;
/*       */         break;
/*       */       case 2:
/*  7445 */         d13 = paramPoint3d1.z;
/*  7446 */         d14 = paramPoint3d2.z;
/*  7447 */         d15 = paramPoint3d3.z;
/*       */         
/*  7449 */         d16 = paramPoint3d4.z;
/*  7450 */         d17 = paramPoint3d5.z;
/*  7451 */         d18 = paramPoint3d6.z;
/*       */         break;
/*       */     } 
/*       */ 
/*       */     
/*  7456 */     double d22 = 0.0D, d23 = 0.0D, d24 = 0.0D, d25 = 0.0D, d26 = 0.0D;
/*  7457 */     if (d11 > 0.0D) {
/*       */ 
/*       */       
/*  7460 */       d22 = d15; d23 = (d13 - d15) * d8; d24 = (d14 - d15) * d8;
/*  7461 */       d25 = d8 - d6; d26 = d8 - d7;
/*       */     }
/*  7463 */     else if (d12 > 0.0D) {
/*       */       
/*  7465 */       d22 = d14; d23 = (d13 - d14) * d7; d24 = (d15 - d14) * d7;
/*  7466 */       d25 = d7 - d6; d26 = d7 - d8;
/*       */     }
/*  7468 */     else if (d7 * d8 > 0.0D || d6 != 0.0D) {
/*       */       
/*  7470 */       d22 = d13; d23 = (d14 - d13) * d6; d24 = (d15 - d13) * d6;
/*  7471 */       d25 = d6 - d7; d26 = d6 - d8;
/*       */     }
/*  7473 */     else if (d7 != 0.0D) {
/*  7474 */       d22 = d14; d23 = (d13 - d14) * d7; d24 = (d15 - d14) * d7;
/*  7475 */       d25 = d7 - d6; d26 = d7 - d8;
/*       */     }
/*  7477 */     else if (d8 != 0.0D) {
/*  7478 */       d22 = d15; d23 = (d13 - d15) * d8; d24 = (d14 - d15) * d8;
/*  7479 */       d25 = d8 - d6; d26 = d8 - d7;
/*       */     }
/*       */     else {
/*       */       
/*  7483 */       return coplanarTriTri(vector3d3, paramPoint3d1, paramPoint3d2, paramPoint3d3, paramPoint3d4, paramPoint3d5, paramPoint3d6);
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7489 */     double d27 = 0.0D, d28 = 0.0D, d29 = 0.0D, d30 = 0.0D, d31 = 0.0D;
/*  7490 */     if (d9 > 0.0D) {
/*       */ 
/*       */       
/*  7493 */       d27 = d18; d28 = (d16 - d18) * d5; d29 = (d17 - d18) * d5;
/*  7494 */       d30 = d5 - d3; d31 = d5 - d4;
/*       */     }
/*  7496 */     else if (d10 > 0.0D) {
/*       */       
/*  7498 */       d27 = d17; d28 = (d16 - d17) * d4; d29 = (d18 - d17) * d4;
/*  7499 */       d30 = d4 - d3; d31 = d4 - d5;
/*       */     }
/*  7501 */     else if (d4 * d5 > 0.0D || d3 != 0.0D) {
/*       */       
/*  7503 */       d27 = d16; d28 = (d17 - d16) * d3; d29 = (d18 - d16) * d3;
/*  7504 */       d30 = d3 - d4; d31 = d3 - d5;
/*       */     }
/*  7506 */     else if (d4 != 0.0D) {
/*  7507 */       d27 = d17; d28 = (d16 - d17) * d4; d29 = (d18 - d17) * d4;
/*  7508 */       d30 = d4 - d3; d31 = d4 - d5;
/*       */     }
/*  7510 */     else if (d5 != 0.0D) {
/*  7511 */       d27 = d18; d28 = (d16 - d18) * d5; d29 = (d17 - d18) * d5;
/*  7512 */       d30 = d5 - d3; d31 = d5 - d4;
/*       */     
/*       */     }
/*       */     else {
/*       */       
/*  7517 */       return coplanarTriTri(vector3d4, paramPoint3d1, paramPoint3d2, paramPoint3d3, paramPoint3d4, paramPoint3d5, paramPoint3d6);
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  7522 */     double d32 = d25 * d26;
/*  7523 */     double d33 = d30 * d31;
/*  7524 */     double d34 = d32 * d33;
/*       */     
/*  7526 */     double d35 = d22 * d34;
/*  7527 */     double d36 = d35 + d23 * d26 * d33;
/*  7528 */     double d37 = d35 + d24 * d25 * d33;
/*       */     
/*  7530 */     d35 = d27 * d34;
/*  7531 */     double d38 = d35 + d28 * d31 * d32;
/*  7532 */     double d39 = d35 + d29 * d30 * d32;
/*       */ 
/*       */     
/*  7535 */     if (d36 > d37) {
/*  7536 */       d35 = d36;
/*  7537 */       d36 = d37;
/*  7538 */       d37 = d35;
/*       */     } 
/*       */ 
/*       */     
/*  7542 */     if (d38 > d39) {
/*  7543 */       d35 = d38;
/*  7544 */       d38 = d39;
/*  7545 */       d39 = d35;
/*       */     } 
/*       */     
/*  7548 */     if (d37 < d38 || d39 < d36)
/*       */     {
/*       */       
/*  7551 */       return false;
/*       */     }
/*       */ 
/*       */     
/*  7555 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectPolygon(Point3d[] paramArrayOfPoint3d1, Point3d[] paramArrayOfPoint3d2) {
/*  7563 */     Vector3d vector3d1 = new Vector3d();
/*  7564 */     Vector3d vector3d2 = new Vector3d();
/*  7565 */     Vector3d vector3d3 = new Vector3d();
/*       */ 
/*       */     
/*       */     byte b1;
/*       */     
/*  7570 */     for (b1 = 0; b1 < paramArrayOfPoint3d1.length - 1; ) {
/*  7571 */       vector3d1.x = (paramArrayOfPoint3d1[b1 + true]).x - (paramArrayOfPoint3d1[b1]).x;
/*  7572 */       vector3d1.y = (paramArrayOfPoint3d1[b1 + true]).y - (paramArrayOfPoint3d1[b1]).y;
/*  7573 */       vector3d1.z = (paramArrayOfPoint3d1[b1 + true]).z - (paramArrayOfPoint3d1[b1++]).z;
/*  7574 */       if (vector3d1.length() > 0.0D)
/*       */         break; 
/*       */     } 
/*       */     byte b2;
/*  7578 */     for (b2 = b1; b2 < paramArrayOfPoint3d1.length - 1; b2++) {
/*  7579 */       vector3d2.x = (paramArrayOfPoint3d1[b2 + 1]).x - (paramArrayOfPoint3d1[b2]).x;
/*  7580 */       vector3d2.y = (paramArrayOfPoint3d1[b2 + 1]).y - (paramArrayOfPoint3d1[b2]).y;
/*  7581 */       vector3d2.z = (paramArrayOfPoint3d1[b2 + 1]).z - (paramArrayOfPoint3d1[b2]).z;
/*  7582 */       if (vector3d2.length() > 0.0D) {
/*       */         break;
/*       */       }
/*       */     } 
/*  7586 */     if (b2 == paramArrayOfPoint3d1.length - 1)
/*       */     {
/*  7588 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7598 */     vector3d3.cross(vector3d1, vector3d2);
/*       */     
/*  7600 */     if (vector3d3.length() == 0.0D)
/*       */     {
/*  7602 */       return false;
/*       */     }
/*       */     
/*  7605 */     b2 = 0;
/*  7606 */     Point3d[] arrayOfPoint3d = new Point3d[2];
/*  7607 */     arrayOfPoint3d[0] = new Point3d();
/*  7608 */     arrayOfPoint3d[1] = new Point3d();
/*       */     
/*  7610 */     for (b1 = 0; b1 < paramArrayOfPoint3d2.length; b1++) {
/*  7611 */       boolean bool; if (b1 < paramArrayOfPoint3d2.length - 1) {
/*  7612 */         bool = edgeIntersectPlane(vector3d3, paramArrayOfPoint3d1[0], paramArrayOfPoint3d2[b1], paramArrayOfPoint3d2[b1 + 1], arrayOfPoint3d[b2]);
/*       */       } else {
/*       */         
/*  7615 */         bool = edgeIntersectPlane(vector3d3, paramArrayOfPoint3d1[0], paramArrayOfPoint3d2[b1], paramArrayOfPoint3d2[0], arrayOfPoint3d[b2]);
/*       */       } 
/*  7617 */       if (bool && 
/*  7618 */         ++b2 > 1) {
/*       */         break;
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  7624 */     if (b2 == 0) {
/*  7625 */       return false;
/*       */     }
/*       */     
/*  7628 */     if (paramArrayOfPoint3d2.length < 3) {
/*  7629 */       return pointIntersectPolygon2D(vector3d3, paramArrayOfPoint3d1, arrayOfPoint3d[0]);
/*       */     }
/*       */     
/*  7632 */     return edgeIntersectPolygon2D(vector3d3, paramArrayOfPoint3d1, arrayOfPoint3d);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  7646 */   boolean intersectRay(Point3d[] paramArrayOfPoint3d, PickRay paramPickRay, double[] paramArrayOfDouble, Point3d paramPoint3d) { return intersectRayOrSegment(paramArrayOfPoint3d, paramPickRay.direction, paramPickRay.origin, paramArrayOfDouble, paramPoint3d, false); }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectSegment(Point3d[] paramArrayOfPoint3d, Point3d paramPoint3d1, Point3d paramPoint3d2, double[] paramArrayOfDouble, Point3d paramPoint3d3) {
/*  7658 */     Vector3d vector3d = new Vector3d();
/*  7659 */     vector3d.x = paramPoint3d2.x - paramPoint3d1.x;
/*  7660 */     vector3d.y = paramPoint3d2.y - paramPoint3d1.y;
/*  7661 */     vector3d.z = paramPoint3d2.z - paramPoint3d1.z;
/*  7662 */     return intersectRayOrSegment(paramArrayOfPoint3d, vector3d, paramPoint3d1, paramArrayOfDouble, paramPoint3d3, true);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectRayOrSegment(Point3d[] paramArrayOfPoint3d, Vector3d paramVector3d, Point3d paramPoint3d1, double[] paramArrayOfDouble, Point3d paramPoint3d2, boolean paramBoolean) {
/*  7676 */     Vector3d vector3d1 = new Vector3d();
/*  7677 */     Vector3d vector3d2 = new Vector3d();
/*  7678 */     Vector3d vector3d3 = new Vector3d();
/*       */     
/*  7680 */     double d4 = 0.0D;
/*  7681 */     double d5 = 0.0D;
/*       */     
/*  7683 */     null = false;
/*  7684 */     int i = 0; boolean bool = false;
/*       */     
/*       */     byte b1;
/*  7687 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  7688 */       if (b1 != paramArrayOfPoint3d.length - 1) {
/*  7689 */         bool = b1 + true;
/*       */       } else {
/*  7691 */         bool = false;
/*       */       } 
/*  7693 */       vector3d1.x = (paramArrayOfPoint3d[bool]).x - (paramArrayOfPoint3d[b1]).x;
/*  7694 */       vector3d1.y = (paramArrayOfPoint3d[bool]).y - (paramArrayOfPoint3d[b1]).y;
/*  7695 */       vector3d1.z = (paramArrayOfPoint3d[bool]).z - (paramArrayOfPoint3d[b1]).z;
/*  7696 */       if (vector3d1.length() > 0.0D) {
/*       */         break;
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  7702 */     for (byte b2 = bool; b2 < paramArrayOfPoint3d.length; b2++) {
/*  7703 */       if (b2 != paramArrayOfPoint3d.length - 1) {
/*  7704 */         i = b2 + true;
/*       */       } else {
/*  7706 */         i = 0;
/*       */       } 
/*  7708 */       vector3d2.x = (paramArrayOfPoint3d[i]).x - (paramArrayOfPoint3d[b2]).x;
/*  7709 */       vector3d2.y = (paramArrayOfPoint3d[i]).y - (paramArrayOfPoint3d[b2]).y;
/*  7710 */       vector3d2.z = (paramArrayOfPoint3d[i]).z - (paramArrayOfPoint3d[b2]).z;
/*  7711 */       if (vector3d2.length() > 0.0D) {
/*       */         break;
/*       */       }
/*       */     } 
/*       */     
/*  7716 */     vector3d3.cross(vector3d1, vector3d2);
/*       */     
/*  7718 */     if (vector3d2.length() == 0.0D || vector3d3.length() == 0.0D) {
/*       */ 
/*       */       
/*  7721 */       i = !bool ? (paramArrayOfPoint3d.length - 1) : (bool - true);
/*  7722 */       return intersectLineAndRay(paramArrayOfPoint3d[bool], paramArrayOfPoint3d[i], paramPoint3d1, paramVector3d, paramArrayOfDouble, paramPoint3d2);
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7736 */     d5 = vector3d3.dot(paramVector3d);
/*       */ 
/*       */     
/*  7739 */     if (d5 == 0.0D) {
/*       */ 
/*       */       
/*  7742 */       for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  7743 */         if (b1 != paramArrayOfPoint3d.length - 1) {
/*  7744 */           i = b1 + 1;
/*       */         } else {
/*  7746 */           i = 0;
/*       */         } 
/*  7748 */         if (intersectLineAndRay(paramArrayOfPoint3d[b1], paramArrayOfPoint3d[i], paramPoint3d1, paramVector3d, paramArrayOfDouble, paramPoint3d2)) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  7754 */           null = true;
/*       */           break;
/*       */         } 
/*       */       } 
/*  7758 */       return null;
/*       */     } 
/*       */ 
/*       */     
/*  7762 */     Vector3d vector3d4 = new Vector3d();
/*  7763 */     vector3d4.set(paramArrayOfPoint3d[0]);
/*  7764 */     d4 = vector3d3.dot(vector3d4);
/*  7765 */     vector3d4.set(paramPoint3d1);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7771 */     paramArrayOfDouble[0] = (d4 - vector3d3.dot(vector3d4)) / d5;
/*       */ 
/*       */     
/*  7774 */     if (paramArrayOfDouble[0] < -1.0E-13D || (paramBoolean && paramArrayOfDouble[0] > 1.0000000000001D))
/*       */     {
/*       */ 
/*       */       
/*  7778 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  7783 */     if (paramPoint3d2 == null) {
/*  7784 */       paramPoint3d2 = new Point3d();
/*       */     }
/*  7786 */     paramPoint3d1.x += paramVector3d.x * paramArrayOfDouble[0];
/*  7787 */     paramPoint3d1.y += paramVector3d.y * paramArrayOfDouble[0];
/*  7788 */     paramPoint3d1.z += paramVector3d.z * paramArrayOfDouble[0];
/*       */ 
/*       */ 
/*       */     
/*  7792 */     double d1 = Math.abs(vector3d3.x);
/*  7793 */     double d2 = Math.abs(vector3d3.y);
/*  7794 */     double d3 = Math.abs(vector3d3.z);
/*       */ 
/*       */ 
/*       */     
/*  7798 */     double d6 = 0.0D;
/*  7799 */     Point3d point3d1 = paramArrayOfPoint3d[paramArrayOfPoint3d.length - 1];
/*  7800 */     Point3d point3d2 = paramArrayOfPoint3d[0];
/*       */     
/*  7802 */     null = true;
/*       */     
/*  7804 */     if (d1 > d2) {
/*  7805 */       if (d1 < d3) {
/*  7806 */         for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  7807 */           point3d1 = paramArrayOfPoint3d[b1];
/*  7808 */           point3d2 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/*  7809 */           double d = (paramPoint3d2.y - point3d1.y) * (point3d2.x - point3d1.x) - (paramPoint3d2.x - point3d1.x) * (point3d2.y - point3d1.y);
/*       */           
/*  7811 */           if (isNonZero(d)) {
/*  7812 */             if (d * d6 < 0.0D) {
/*  7813 */               null = false;
/*       */               break;
/*       */             } 
/*  7816 */             d6 = d;
/*       */           } else {
/*  7818 */             double d7 = point3d2.y - point3d1.y;
/*  7819 */             if (isNonZero(d7)) {
/*  7820 */               d7 = (paramPoint3d2.y - point3d1.y) / d7;
/*  7821 */               null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */               break;
/*       */             } 
/*  7824 */             d7 = point3d2.x - point3d1.x;
/*  7825 */             if (isNonZero(d7)) {
/*  7826 */               d7 = (paramPoint3d2.x - point3d1.x) / d7;
/*  7827 */               null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*       */               break;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } else {
/*  7839 */         for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  7840 */           point3d1 = paramArrayOfPoint3d[b1];
/*  7841 */           point3d2 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/*  7842 */           double d = (paramPoint3d2.y - point3d1.y) * (point3d2.z - point3d1.z) - (paramPoint3d2.z - point3d1.z) * (point3d2.y - point3d1.y);
/*       */           
/*  7844 */           if (isNonZero(d)) {
/*  7845 */             if (d * d6 < 0.0D) {
/*  7846 */               null = false;
/*       */               break;
/*       */             } 
/*  7849 */             d6 = d;
/*       */           } else {
/*  7851 */             double d7 = point3d2.y - point3d1.y;
/*       */             
/*  7853 */             if (isNonZero(d7)) {
/*  7854 */               d7 = (paramPoint3d2.y - point3d1.y) / d7;
/*  7855 */               null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */               
/*       */               break;
/*       */             } 
/*  7859 */             d7 = point3d2.z - point3d1.z;
/*  7860 */             if (isNonZero(d7)) {
/*  7861 */               d7 = (paramPoint3d2.z - point3d1.z) / d7;
/*  7862 */               null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*       */               break;
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } 
/*  7872 */     } else if (d2 < d3) {
/*  7873 */       for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  7874 */         point3d1 = paramArrayOfPoint3d[b1];
/*  7875 */         point3d2 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/*  7876 */         double d = (paramPoint3d2.y - point3d1.y) * (point3d2.x - point3d1.x) - (paramPoint3d2.x - point3d1.x) * (point3d2.y - point3d1.y);
/*       */         
/*  7878 */         if (isNonZero(d)) {
/*  7879 */           if (d * d6 < 0.0D) {
/*  7880 */             null = false;
/*       */             break;
/*       */           } 
/*  7883 */           d6 = d;
/*       */         } else {
/*  7885 */           double d7 = point3d2.y - point3d1.y;
/*  7886 */           if (isNonZero(d7)) {
/*  7887 */             d7 = (paramPoint3d2.y - point3d1.y) / d7;
/*  7888 */             null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */             break;
/*       */           } 
/*  7891 */           d7 = point3d2.x - point3d1.x;
/*  7892 */           if (isNonZero(d7)) {
/*  7893 */             d7 = (paramPoint3d2.x - point3d1.x) / d7;
/*  7894 */             null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } else {
/*  7903 */       for (b1 = 0; b1 < paramArrayOfPoint3d.length; b1++) {
/*  7904 */         point3d1 = paramArrayOfPoint3d[b1];
/*  7905 */         point3d2 = (b1 != paramArrayOfPoint3d.length - 1) ? paramArrayOfPoint3d[b1 + 1] : paramArrayOfPoint3d[0];
/*  7906 */         double d = (paramPoint3d2.x - point3d1.x) * (point3d2.z - point3d1.z) - (paramPoint3d2.z - point3d1.z) * (point3d2.x - point3d1.x);
/*       */         
/*  7908 */         if (isNonZero(d)) {
/*  7909 */           if (d * d6 < 0.0D) {
/*  7910 */             null = false;
/*       */             break;
/*       */           } 
/*  7913 */           d6 = d;
/*       */         } else {
/*  7915 */           double d7 = point3d2.x - point3d1.x;
/*  7916 */           if (isNonZero(d7)) {
/*  7917 */             d7 = (paramPoint3d2.x - point3d1.x) / d7;
/*  7918 */             null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */             break;
/*       */           } 
/*  7921 */           d7 = point3d2.z - point3d1.z;
/*  7922 */           if (isNonZero(d7)) {
/*  7923 */             d7 = (paramPoint3d2.z - point3d1.z) / d7;
/*  7924 */             null = (d7 > -1.0E-13D && d7 < 1.0000000000001D);
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  7935 */     if (null) {
/*  7936 */       paramArrayOfDouble[0] = paramArrayOfDouble[0] * paramVector3d.length();
/*       */     }
/*  7938 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  7944 */   static final boolean isNonZero(double paramDouble) { return (paramDouble > 1.0E-13D || paramDouble < -1.0E-13D); }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean inside(Point3d[] paramArrayOfPoint3d, PickPoint paramPickPoint, int paramInt) {
/*  7954 */     Vector3d vector3d1 = new Vector3d();
/*  7955 */     Vector3d vector3d2 = new Vector3d();
/*  7956 */     Vector3d vector3d3 = new Vector3d();
/*  7957 */     double d1 = 0.0D;
/*  7958 */     Vector3d vector3d4 = new Vector3d();
/*  7959 */     double d2 = 0.0D;
/*       */ 
/*       */ 
/*       */     
/*       */     byte b1;
/*       */ 
/*       */     
/*  7966 */     for (b1 = 0; b1 < paramArrayOfPoint3d.length - 1; ) {
/*  7967 */       vector3d1.x = (paramArrayOfPoint3d[b1 + true]).x - (paramArrayOfPoint3d[b1]).x;
/*  7968 */       vector3d1.y = (paramArrayOfPoint3d[b1 + true]).y - (paramArrayOfPoint3d[b1]).y;
/*  7969 */       vector3d1.z = (paramArrayOfPoint3d[b1 + true]).z - (paramArrayOfPoint3d[b1++]).z;
/*  7970 */       if (vector3d1.length() > 0.0D)
/*       */         break; 
/*       */     } 
/*       */     byte b2;
/*  7974 */     for (b2 = b1; b2 < paramArrayOfPoint3d.length - 1; b2++) {
/*  7975 */       vector3d2.x = (paramArrayOfPoint3d[b2 + 1]).x - (paramArrayOfPoint3d[b2]).x;
/*  7976 */       vector3d2.y = (paramArrayOfPoint3d[b2 + 1]).y - (paramArrayOfPoint3d[b2]).y;
/*  7977 */       vector3d2.z = (paramArrayOfPoint3d[b2 + 1]).z - (paramArrayOfPoint3d[b2]).z;
/*  7978 */       if (vector3d2.length() > 0.0D) {
/*       */         break;
/*       */       }
/*       */     } 
/*  7982 */     if (b2 == paramArrayOfPoint3d.length - 1)
/*       */     {
/*  7984 */       return false;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7994 */     if (paramInt == 1) {
/*  7995 */       vector3d3.cross(vector3d1, vector3d2);
/*       */     } else {
/*  7997 */       vector3d3.cross(vector3d2, vector3d1);
/*       */     } 
/*  7999 */     if (vector3d3.length() == 0.0D)
/*       */     {
/*  8001 */       return false;
/*       */     }
/*       */     
/*  8004 */     vector3d4.set(paramArrayOfPoint3d[0]);
/*  8005 */     d1 = vector3d3.dot(vector3d4);
/*  8006 */     vector3d4.set(paramPickPoint.location);
/*       */     
/*  8008 */     return (d1 - vector3d3.dot(vector3d4) <= 0.0D);
/*       */   }
/*       */ 
/*       */   
/*  8012 */   boolean intersectPntAndPnt(Point3d paramPoint3d1, Point3d paramPoint3d2) { return (paramPoint3d1.x == paramPoint3d2.x && paramPoint3d1.y == paramPoint3d2.y && paramPoint3d1.z == paramPoint3d2.z); }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectPntAndRay(Point3d paramPoint3d1, Point3d paramPoint3d2, Vector3d paramVector3d, double[] paramArrayOfDouble) {
/*  8019 */     byte b = 0;
/*       */ 
/*       */     
/*  8022 */     if (paramVector3d.x != 0.0D) {
/*  8023 */       b = 0;
/*  8024 */       paramArrayOfDouble[0] = (paramPoint3d1.x - paramPoint3d2.x) / paramVector3d.x;
/*       */     }
/*  8026 */     else if (paramVector3d.y != 0.0D) {
/*  8027 */       if (paramPoint3d1.x != paramPoint3d2.x)
/*  8028 */         return false; 
/*  8029 */       b = 1;
/*  8030 */       paramArrayOfDouble[0] = (paramPoint3d1.y - paramPoint3d2.y) / paramVector3d.y;
/*       */     }
/*  8032 */     else if (paramVector3d.z != 0.0D) {
/*  8033 */       if (paramPoint3d1.x != paramPoint3d2.x || paramPoint3d1.y != paramPoint3d2.y)
/*  8034 */         return false; 
/*  8035 */       b = 2;
/*  8036 */       paramArrayOfDouble[0] = (paramPoint3d1.z - paramPoint3d2.z) / paramVector3d.z;
/*       */     }
/*       */     else {
/*       */       
/*  8040 */       return false;
/*       */     } 
/*  8042 */     if (paramArrayOfDouble[0] < 0.0D) {
/*  8043 */       return false;
/*       */     }
/*  8045 */     if (b == 0) {
/*  8046 */       double d = paramPoint3d2.y + paramArrayOfDouble[0] * paramVector3d.y;
/*  8047 */       if (paramPoint3d1.y < d - 1.0E-13D || paramPoint3d1.y > d + 1.0E-13D) {
/*  8048 */         return false;
/*       */       }
/*       */     } 
/*  8051 */     if (b < 2) {
/*  8052 */       double d = paramPoint3d2.z + paramArrayOfDouble[0] * paramVector3d.z;
/*  8053 */       if (paramPoint3d1.z < d - 1.0E-13D || paramPoint3d1.z > d + 1.0E-13D) {
/*  8054 */         return false;
/*       */       }
/*       */     } 
/*  8057 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectLineAndRay(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Vector3d paramVector3d, double[] paramArrayOfDouble, Point3d paramPoint3d4) {
/*  8074 */     Vector3d vector3d = new Vector3d();
/*  8075 */     vector3d.x = paramPoint3d2.x - paramPoint3d1.x;
/*  8076 */     vector3d.y = paramPoint3d2.y - paramPoint3d1.y;
/*  8077 */     vector3d.z = paramPoint3d2.z - paramPoint3d1.z;
/*       */     
/*  8079 */     double d1 = vector3d.x;
/*  8080 */     double d2 = -paramVector3d.x;
/*  8081 */     double d3 = vector3d.y;
/*  8082 */     double d4 = -paramVector3d.y;
/*       */ 
/*       */     
/*  8085 */     double d9 = d1 * d4 - d3 * d2;
/*       */     
/*  8087 */     if (d9 == 0.0D) {
/*  8088 */       boolean bool = false;
/*  8089 */       if (vector3d.x == 0.0D && vector3d.y == 0.0D && vector3d.z == 0.0D) {
/*  8090 */         bool = intersectPntAndRay(paramPoint3d1, paramPoint3d3, paramVector3d, paramArrayOfDouble);
/*  8091 */         if (bool && paramPoint3d4 != null) {
/*  8092 */           paramPoint3d4.set(paramPoint3d1);
/*       */         }
/*       */       } 
/*  8095 */       return bool;
/*       */     } 
/*       */     
/*  8098 */     double d12 = 1.0D / d9;
/*       */     
/*  8100 */     double d5 = d12 * d4;
/*  8101 */     double d6 = d12 * -d2;
/*  8102 */     double d7 = d12 * -d3;
/*  8103 */     double d8 = d12 * d1;
/*       */     
/*  8105 */     d12 = paramPoint3d3.x - paramPoint3d1.x;
/*  8106 */     double d13 = paramPoint3d3.y - paramPoint3d1.y;
/*       */     
/*  8108 */     double d10 = d5 * d12 + d6 * d13;
/*  8109 */     double d11 = d7 * d12 + d8 * d13;
/*       */     
/*  8111 */     if (d11 < 0.0D)
/*       */     {
/*  8113 */       return false;
/*       */     }
/*  8115 */     if (d10 < 0.0D || d10 > 1.0D)
/*       */     {
/*  8117 */       return false;
/*       */     }
/*       */     
/*  8120 */     d12 = paramPoint3d3.z + d11 * paramVector3d.z;
/*  8121 */     d13 = paramPoint3d1.z + d10 * vector3d.z;
/*       */     
/*  8123 */     if (d12 < d13 - 1.0E-13D || d12 > d13 + 1.0E-13D)
/*       */     {
/*  8125 */       return false;
/*       */     }
/*       */     
/*  8128 */     paramArrayOfDouble[0] = d11;
/*       */     
/*  8130 */     if (paramPoint3d4 != null) {
/*       */       
/*  8132 */       paramPoint3d3.x += paramVector3d.x * paramArrayOfDouble[0];
/*  8133 */       paramPoint3d3.y += paramVector3d.y * paramArrayOfDouble[0];
/*  8134 */       paramPoint3d3.z += paramVector3d.z * paramArrayOfDouble[0];
/*       */     } 
/*       */ 
/*       */     
/*  8138 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectCylinder(Point3d[] paramArrayOfPoint3d, PickCylinder paramPickCylinder, double[] paramArrayOfDouble, Point3d paramPoint3d) {
/*  8148 */     Point3d point3d1 = new Point3d();
/*  8149 */     Point3d point3d2 = new Point3d();
/*  8150 */     Vector3d vector3d1 = new Vector3d();
/*  8151 */     Point3d point3d3 = new Point3d();
/*  8152 */     Vector3d vector3d2 = new Vector3d();
/*       */     
/*  8154 */     if (paramPoint3d == null) {
/*  8155 */       paramPoint3d = new Point3d();
/*       */     }
/*       */ 
/*       */     
/*  8159 */     paramPickCylinder.getOrigin(point3d1);
/*  8160 */     paramPickCylinder.getDirection(vector3d1);
/*  8161 */     double d = paramPickCylinder.getRadius();
/*       */     
/*  8163 */     if (paramPickCylinder instanceof PickCylinderSegment) {
/*  8164 */       ((PickCylinderSegment)paramPickCylinder).getEnd(point3d2);
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  8169 */     if (paramArrayOfPoint3d.length > 2) {
/*  8170 */       if (paramPickCylinder instanceof PickCylinderRay) {
/*  8171 */         if (intersectRay(paramArrayOfPoint3d, new PickRay(point3d1, vector3d1), paramArrayOfDouble, paramPoint3d))
/*       */         {
/*       */           
/*  8174 */           return true;
/*       */         
/*       */         }
/*       */       }
/*  8178 */       else if (intersectSegment(paramArrayOfPoint3d, point3d1, point3d2, paramArrayOfDouble, paramPoint3d)) {
/*  8179 */         return true;
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8187 */     for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  8188 */       double d1; boolean bool = (b < paramArrayOfPoint3d.length - 1) ? (b + true) : 0;
/*  8189 */       if (paramPickCylinder instanceof PickCylinderSegment) {
/*  8190 */         d1 = Distance.segmentToSegment(point3d1, point3d2, paramArrayOfPoint3d[b], paramArrayOfPoint3d[bool], point3d3, paramPoint3d, null);
/*       */       
/*       */       }
/*       */       else {
/*       */ 
/*       */         
/*  8196 */         d1 = Distance.rayToSegment(point3d1, vector3d1, paramArrayOfPoint3d[b], paramArrayOfPoint3d[bool], point3d3, paramPoint3d, null);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  8201 */       if (d1 <= d * d) {
/*  8202 */         vector3d2.sub(point3d3, point3d1);
/*  8203 */         paramArrayOfDouble[0] = vector3d2.length();
/*  8204 */         return true;
/*       */       } 
/*       */     } 
/*  8207 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectCone(Point3d[] paramArrayOfPoint3d, PickCone paramPickCone, double[] paramArrayOfDouble, Point3d paramPoint3d) {
/*  8217 */     Point3d point3d1 = new Point3d();
/*  8218 */     Point3d point3d2 = new Point3d();
/*  8219 */     Vector3d vector3d1 = new Vector3d();
/*  8220 */     Vector3d vector3d2 = new Vector3d();
/*       */ 
/*       */     
/*  8223 */     Point3d point3d3 = new Point3d();
/*  8224 */     Vector3d vector3d3 = new Vector3d();
/*       */     
/*  8226 */     if (paramPoint3d == null) {
/*  8227 */       paramPoint3d = new Point3d();
/*       */     }
/*       */     
/*  8230 */     paramPickCone.getOrigin(point3d1);
/*  8231 */     paramPickCone.getDirection(vector3d1);
/*       */ 
/*       */     
/*  8234 */     if (paramPickCone instanceof PickConeSegment) {
/*  8235 */       ((PickConeSegment)paramPickCone).getEnd(point3d2);
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  8240 */     if (paramArrayOfPoint3d.length > 2) {
/*  8241 */       if (paramPickCone instanceof PickConeRay) {
/*  8242 */         if (intersectRay(paramArrayOfPoint3d, new PickRay(point3d1, vector3d1), paramArrayOfDouble, paramPoint3d))
/*       */         {
/*       */           
/*  8245 */           return true;
/*       */         
/*       */         }
/*       */       }
/*  8249 */       else if (intersectSegment(paramArrayOfPoint3d, point3d1, point3d2, paramArrayOfDouble, paramPoint3d)) {
/*  8250 */         return true;
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8257 */     boolean bool = false;
/*  8258 */     for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  8259 */       double d3; bool = (b < paramArrayOfPoint3d.length - 1) ? (b + true) : 0;
/*  8260 */       if (paramPickCone instanceof PickConeSegment) {
/*  8261 */         d3 = Distance.segmentToSegment(point3d1, point3d2, paramArrayOfPoint3d[b], paramArrayOfPoint3d[bool], point3d3, paramPoint3d, null);
/*       */       
/*       */       }
/*       */       else {
/*       */ 
/*       */         
/*  8267 */         d3 = Distance.rayToSegment(point3d1, vector3d1, paramArrayOfPoint3d[b], paramArrayOfPoint3d[bool], point3d3, paramPoint3d, null);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  8272 */       vector3d2.sub(point3d3, point3d1);
/*  8273 */       double d1 = vector3d2.length();
/*  8274 */       double d2 = Math.tan(paramPickCone.getSpreadAngle()) * d1;
/*  8275 */       if (d3 <= d2 * d2) {
/*       */         
/*  8277 */         paramArrayOfDouble[0] = d1;
/*  8278 */         return true;
/*       */       } 
/*       */     } 
/*  8281 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectCylinder(Point3d paramPoint3d, PickCylinder paramPickCylinder, double[] paramArrayOfDouble) {
/*       */     double d2;
/*  8292 */     Point3d point3d1 = new Point3d();
/*  8293 */     Point3d point3d2 = new Point3d();
/*  8294 */     Vector3d vector3d1 = new Vector3d();
/*  8295 */     Point3d point3d3 = new Point3d();
/*  8296 */     Vector3d vector3d2 = new Vector3d();
/*       */ 
/*       */     
/*  8299 */     paramPickCylinder.getOrigin(point3d1);
/*  8300 */     paramPickCylinder.getDirection(vector3d1);
/*  8301 */     double d1 = paramPickCylinder.getRadius();
/*       */ 
/*       */     
/*  8304 */     if (paramPickCylinder instanceof PickCylinderSegment) {
/*  8305 */       ((PickCylinderSegment)paramPickCylinder).getEnd(point3d2);
/*  8306 */       d2 = Distance.pointToSegment(paramPoint3d, point3d1, point3d2, point3d3, null);
/*       */     } else {
/*       */       
/*  8309 */       d2 = Distance.pointToRay(paramPoint3d, point3d1, vector3d1, point3d3, null);
/*       */     } 
/*  8311 */     if (d2 <= d1 * d1) {
/*  8312 */       vector3d2.sub(point3d3, point3d1);
/*  8313 */       paramArrayOfDouble[0] = vector3d2.length();
/*  8314 */       return true;
/*       */     } 
/*  8316 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersectCone(Point3d paramPoint3d, PickCone paramPickCone, double[] paramArrayOfDouble) {
/*       */     double d3;
/*  8325 */     Point3d point3d1 = new Point3d();
/*  8326 */     Point3d point3d2 = new Point3d();
/*  8327 */     Vector3d vector3d1 = new Vector3d();
/*  8328 */     Point3d point3d3 = new Point3d();
/*  8329 */     Vector3d vector3d2 = new Vector3d();
/*       */ 
/*       */     
/*  8332 */     paramPickCone.getOrigin(point3d1);
/*  8333 */     paramPickCone.getDirection(vector3d1);
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8338 */     if (point3d3 == null) {
/*  8339 */       point3d3 = new Point3d();
/*       */     }
/*       */     
/*  8342 */     if (paramPickCone instanceof PickConeSegment) {
/*  8343 */       ((PickConeSegment)paramPickCone).getEnd(point3d2);
/*  8344 */       d3 = Distance.pointToSegment(paramPoint3d, point3d1, point3d2, point3d3, null);
/*       */     } else {
/*       */       
/*  8347 */       d3 = Distance.pointToRay(paramPoint3d, point3d1, vector3d1, point3d3, null);
/*       */     } 
/*  8349 */     vector3d2.sub(point3d3, point3d1);
/*  8350 */     double d2 = vector3d2.length();
/*  8351 */     double d1 = Math.tan(paramPickCone.getSpreadAngle()) * d2;
/*  8352 */     if (d3 <= d1 * d1) {
/*  8353 */       paramArrayOfDouble[0] = d2;
/*  8354 */       return true;
/*       */     } 
/*  8356 */     return false;
/*       */   }
/*       */ 
/*       */   
/*       */   void setCoordRefBuffer(J3DBuffer paramJ3DBuffer) {
/*  8361 */     if (paramJ3DBuffer != null) {
/*  8362 */       switch (paramJ3DBuffer.getBufferType()) {
/*       */         case 7:
/*  8364 */           assert ((FloatBufferWrapper)paramJ3DBuffer.getBufferImpl()).isDirect();
/*       */           break;
/*       */         case 8:
/*  8367 */           assert ((DoubleBufferWrapper)paramJ3DBuffer.getBufferImpl()).isDirect();
/*       */           break;
/*       */         case 0:
/*  8370 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryArray115"));
/*       */         
/*       */         default:
/*  8373 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryArray116"));
/*       */       } 
/*       */       
/*  8376 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8377 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*  8378 */         if (3 * indexedGeometryArrayRetained.maxCoordIndex >= paramJ3DBuffer.getBufferImpl().limit()) {
/*  8379 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*  8381 */       } else if (paramJ3DBuffer.getBufferImpl().limit() < 3 * (this.initialCoordIndex + this.validVertexCount)) {
/*  8382 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  8387 */     this.geomLock.getLock();
/*  8388 */     this.dirtyFlag |= 0x1;
/*  8389 */     this.coordRefBuffer = paramJ3DBuffer;
/*  8390 */     if (paramJ3DBuffer == null) {
/*  8391 */       this.floatBufferRefCoords = null;
/*  8392 */       this.doubleBufferRefCoords = null;
/*       */ 
/*       */       
/*  8395 */       this.vertexType &= 0xFFFFFFFD;
/*  8396 */       this.vertexType &= 0xFFFFFFFE;
/*       */     } else {
/*  8398 */       switch (paramJ3DBuffer.getBufferType()) {
/*       */         case 7:
/*  8400 */           this.floatBufferRefCoords = (FloatBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*       */           
/*  8402 */           this.doubleBufferRefCoords = null;
/*  8403 */           this.vertexType |= 0x1;
/*  8404 */           this.vertexType &= 0xFFFFFFFD;
/*       */           break;
/*       */         case 8:
/*  8407 */           this.floatBufferRefCoords = null;
/*  8408 */           this.doubleBufferRefCoords = (DoubleBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*       */           
/*  8410 */           this.vertexType |= 0x2;
/*  8411 */           this.vertexType &= 0xFFFFFFFE;
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     } 
/*  8423 */     this.geomLock.unLock();
/*       */     
/*  8425 */     if (!this.inUpdater && this.source != null) {
/*  8426 */       if (this.source.isLive()) {
/*  8427 */         processCoordsChanged((paramJ3DBuffer == null));
/*  8428 */         sendDataChangedMessage(true);
/*       */       } else {
/*  8430 */         this.boundsDirty = true;
/*       */       } 
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  8438 */   J3DBuffer getCoordRefBuffer() { return this.coordRefBuffer; }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordRefFloat(float[] paramArrayOfFloat) {
/*  8446 */     if (paramArrayOfFloat != null) {
/*  8447 */       if ((this.vertexType & 0xF) != 0 && (this.vertexType & 0xF) != 1)
/*       */       {
/*  8449 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */ 
/*       */       
/*  8453 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8454 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8456 */         if (3 * indexedGeometryArrayRetained.maxCoordIndex >= paramArrayOfFloat.length) {
/*  8457 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*  8459 */       } else if (paramArrayOfFloat.length < 3 * (this.initialCoordIndex + this.validVertexCount)) {
/*  8460 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */       } 
/*       */     } 
/*       */     
/*  8464 */     this.geomLock.getLock();
/*  8465 */     this.dirtyFlag |= 0x1;
/*       */     
/*  8467 */     this.floatRefCoords = paramArrayOfFloat;
/*  8468 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8470 */       if (paramArrayOfFloat == null) {
/*  8471 */         this.vertexType &= 0xFFFFFFFE;
/*       */       } else {
/*  8473 */         this.vertexType |= 0x1;
/*       */       } 
/*       */     } else {
/*  8476 */       setupMirrorVertexPointer(1);
/*       */     } 
/*       */     
/*  8479 */     this.geomLock.unLock();
/*       */     
/*  8481 */     if (!this.inUpdater && this.source != null) {
/*  8482 */       if (this.source.isLive()) {
/*  8483 */         processCoordsChanged((paramArrayOfFloat == null));
/*  8484 */         sendDataChangedMessage(true);
/*       */       } else {
/*  8486 */         this.boundsDirty = true;
/*       */       } 
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  8493 */   float[] getCoordRefFloat() { return this.floatRefCoords; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordRefDouble(double[] paramArrayOfDouble) {
/*  8499 */     if (paramArrayOfDouble != null) {
/*  8500 */       if ((this.vertexType & 0xF) != 0 && (this.vertexType & 0xF) != 2)
/*       */       {
/*  8502 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8505 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8506 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*  8507 */         if (3 * indexedGeometryArrayRetained.maxCoordIndex >= paramArrayOfDouble.length) {
/*  8508 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*  8510 */       } else if (paramArrayOfDouble.length < 3 * (this.initialCoordIndex + this.validVertexCount)) {
/*  8511 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  8516 */     this.geomLock.getLock();
/*  8517 */     this.dirtyFlag |= 0x1;
/*  8518 */     this.doubleRefCoords = paramArrayOfDouble;
/*  8519 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8521 */       if (paramArrayOfDouble == null) {
/*  8522 */         this.vertexType &= 0xFFFFFFFD;
/*       */       } else {
/*  8524 */         this.vertexType |= 0x2;
/*       */       } 
/*       */     } else {
/*  8527 */       setupMirrorVertexPointer(2);
/*       */     } 
/*  8529 */     this.geomLock.unLock();
/*       */     
/*  8531 */     if (!this.inUpdater && this.source != null) {
/*  8532 */       if (this.source.isLive()) {
/*  8533 */         processCoordsChanged((paramArrayOfDouble == null));
/*  8534 */         sendDataChangedMessage(true);
/*       */       } else {
/*  8536 */         this.boundsDirty = true;
/*       */       } 
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*  8542 */   double[] getCoordRefDouble() { return this.doubleRefCoords; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordRef3f(Point3f[] paramArrayOfPoint3f) {
/*  8547 */     if (paramArrayOfPoint3f != null) {
/*  8548 */       if ((this.vertexType & 0xF) != 0 && (this.vertexType & 0xF) != 4)
/*       */       {
/*  8550 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8553 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8554 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8556 */         if (indexedGeometryArrayRetained.maxCoordIndex >= paramArrayOfPoint3f.length) {
/*  8557 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*  8559 */       } else if (paramArrayOfPoint3f.length < this.initialCoordIndex + this.validVertexCount) {
/*  8560 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */       } 
/*       */     } 
/*       */     
/*  8564 */     this.geomLock.getLock();
/*  8565 */     this.dirtyFlag |= 0x1;
/*  8566 */     this.p3fRefCoords = paramArrayOfPoint3f;
/*  8567 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8569 */       if (paramArrayOfPoint3f == null) {
/*  8570 */         this.vertexType &= 0xFFFFFFFB;
/*       */       } else {
/*  8572 */         this.vertexType |= 0x4;
/*       */       } 
/*       */     } else {
/*  8575 */       setupMirrorVertexPointer(4);
/*       */     } 
/*  8577 */     this.geomLock.unLock();
/*       */     
/*  8579 */     if (!this.inUpdater && this.source != null) {
/*  8580 */       if (this.source.isLive()) {
/*  8581 */         processCoordsChanged((paramArrayOfPoint3f == null));
/*  8582 */         sendDataChangedMessage(true);
/*       */       } else {
/*  8584 */         this.boundsDirty = true;
/*       */       } 
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*  8590 */   Point3f[] getCoordRef3f() { return this.p3fRefCoords; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCoordRef3d(Point3d[] paramArrayOfPoint3d) {
/*  8596 */     if (paramArrayOfPoint3d != null) {
/*  8597 */       if ((this.vertexType & 0xF) != 0 && (this.vertexType & 0xF) != 8)
/*       */       {
/*  8599 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8602 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8603 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8605 */         if (indexedGeometryArrayRetained.maxCoordIndex >= paramArrayOfPoint3d.length) {
/*  8606 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*  8608 */       } else if (paramArrayOfPoint3d.length < this.initialCoordIndex + this.validVertexCount) {
/*  8609 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */       } 
/*       */     } 
/*  8612 */     this.geomLock.getLock();
/*  8613 */     this.dirtyFlag |= 0x1;
/*  8614 */     this.p3dRefCoords = paramArrayOfPoint3d;
/*  8615 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0))
/*       */     
/*  8617 */     { if (paramArrayOfPoint3d == null) {
/*  8618 */         this.vertexType &= 0xFFFFFFF7;
/*       */       } else {
/*  8620 */         this.vertexType |= 0x8;
/*       */       }  }
/*  8622 */     else { setupMirrorVertexPointer(8); }
/*       */     
/*  8624 */     this.geomLock.unLock();
/*       */     
/*  8626 */     if (!this.inUpdater && this.source != null) {
/*  8627 */       if (this.source.isLive()) {
/*  8628 */         processCoordsChanged((paramArrayOfPoint3d == null));
/*  8629 */         sendDataChangedMessage(true);
/*       */       } else {
/*  8631 */         this.boundsDirty = true;
/*       */       } 
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*  8637 */   Point3d[] getCoordRef3d() { return this.p3dRefCoords; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setColorRefFloat(float[] paramArrayOfFloat) {
/*  8642 */     if (paramArrayOfFloat != null) {
/*  8643 */       if ((this.vertexType & 0x3F0) != 0 && (this.vertexType & 0x3F0) != 16)
/*       */       {
/*  8645 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8648 */       if ((this.vertexFormat & 0x4) == 0) {
/*  8649 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray123"));
/*       */       }
/*       */       
/*  8652 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8653 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8655 */         if (getColorStride() * indexedGeometryArrayRetained.maxColorIndex >= paramArrayOfFloat.length) {
/*  8656 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*  8658 */       } else if (paramArrayOfFloat.length < getColorStride() * (this.initialColorIndex + this.validVertexCount)) {
/*  8659 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */       } 
/*       */     } 
/*       */     
/*  8663 */     this.geomLock.getLock();
/*  8664 */     this.dirtyFlag |= 0x4;
/*  8665 */     this.colorChanged = 65535;
/*  8666 */     this.floatRefColors = paramArrayOfFloat;
/*  8667 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8669 */       if (paramArrayOfFloat == null) {
/*  8670 */         this.vertexType &= 0xFFFFFFEF;
/*       */       } else {
/*  8672 */         this.vertexType |= 0x10;
/*       */       } 
/*       */     } else {
/*  8675 */       setupMirrorColorPointer(16, false);
/*       */     } 
/*  8677 */     this.geomLock.unLock();
/*  8678 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  8679 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  8685 */   float[] getColorRefFloat() { return this.floatRefColors; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColorRefBuffer(J3DBuffer paramJ3DBuffer) {
/*  8691 */     if (paramJ3DBuffer != null) {
/*  8692 */       switch (paramJ3DBuffer.getBufferType()) {
/*       */         case 7:
/*  8694 */           assert ((FloatBufferWrapper)paramJ3DBuffer.getBufferImpl()).isDirect();
/*       */           break;
/*       */         case 2:
/*  8697 */           assert ((ByteBufferWrapper)paramJ3DBuffer.getBufferImpl()).isDirect();
/*       */           break;
/*       */         case 0:
/*  8700 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryArray115"));
/*       */         
/*       */         default:
/*  8703 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryArray116"));
/*       */       } 
/*       */       
/*  8706 */       if ((this.vertexFormat & 0x4) == 0) {
/*  8707 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray123"));
/*       */       }
/*       */       
/*  8710 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8711 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8713 */         if (getColorStride() * indexedGeometryArrayRetained.maxColorIndex >= paramJ3DBuffer.getBufferImpl().limit()) {
/*  8714 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*  8716 */       } else if (paramJ3DBuffer.getBufferImpl().limit() < getColorStride() * (this.initialColorIndex + this.validVertexCount)) {
/*       */         
/*  8718 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */       } 
/*       */     } 
/*       */     
/*  8722 */     this.geomLock.getLock();
/*  8723 */     this.dirtyFlag |= 0x4;
/*  8724 */     this.colorChanged = 65535;
/*  8725 */     this.colorRefBuffer = paramJ3DBuffer;
/*  8726 */     if (paramJ3DBuffer == null) {
/*  8727 */       this.floatBufferRefColors = null;
/*  8728 */       this.byteBufferRefColors = null;
/*       */     } else {
/*  8730 */       switch (paramJ3DBuffer.getBufferType()) {
/*       */         case 7:
/*  8732 */           this.floatBufferRefColors = (FloatBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*  8733 */           this.byteBufferRefColors = null;
/*       */           break;
/*       */         
/*       */         case 2:
/*  8737 */           this.byteBufferRefColors = (ByteBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*  8738 */           this.floatBufferRefColors = null;
/*       */           break;
/*       */       } 
/*       */ 
/*       */     
/*       */     } 
/*  8744 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8746 */       if (paramJ3DBuffer == null) {
/*  8747 */         this.vertexType &= 0xFFFFFFEF;
/*  8748 */         this.vertexType &= 0xFFFFFFDF;
/*       */       } else {
/*  8750 */         switch (paramJ3DBuffer.getBufferType()) {
/*       */           case 7:
/*  8752 */             this.vertexType |= 0x10;
/*  8753 */             this.vertexType &= 0xFFFFFFDF;
/*       */             break;
/*       */           
/*       */           case 2:
/*  8757 */             this.vertexType |= 0x20;
/*  8758 */             this.vertexType &= 0xFFFFFFEF;
/*       */             break;
/*       */         } 
/*       */ 
/*       */ 
/*       */       
/*       */       } 
/*       */     } else {
/*  8766 */       setupMirrorColorPointer(48, false);
/*       */     } 
/*  8768 */     this.geomLock.unLock();
/*  8769 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  8770 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  8776 */   J3DBuffer getColorRefBuffer() { return this.colorRefBuffer; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setColorRefByte(byte[] paramArrayOfByte) {
/*  8781 */     if (paramArrayOfByte != null) {
/*  8782 */       if ((this.vertexType & 0x3F0) != 0 && (this.vertexType & 0x3F0) != 32)
/*       */       {
/*  8784 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8787 */       if ((this.vertexFormat & 0x4) == 0) {
/*  8788 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray123"));
/*       */       }
/*       */       
/*  8791 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8792 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8794 */         if (getColorStride() * indexedGeometryArrayRetained.maxColorIndex >= paramArrayOfByte.length) {
/*  8795 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*  8797 */       } else if (paramArrayOfByte.length < getColorStride() * (this.initialColorIndex + this.validVertexCount)) {
/*  8798 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */       } 
/*       */     } 
/*       */     
/*  8802 */     this.geomLock.getLock();
/*  8803 */     this.dirtyFlag |= 0x4;
/*  8804 */     this.colorChanged = 65535;
/*  8805 */     this.byteRefColors = paramArrayOfByte;
/*  8806 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8808 */       if (paramArrayOfByte == null) {
/*  8809 */         this.vertexType &= 0xFFFFFFDF;
/*       */       } else {
/*  8811 */         this.vertexType |= 0x20;
/*       */       } 
/*       */     } else {
/*  8814 */       setupMirrorColorPointer(32, false);
/*       */     } 
/*  8816 */     this.geomLock.unLock();
/*  8817 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  8818 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  8824 */   byte[] getColorRefByte() { return this.byteRefColors; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setColorRef3f(Color3f[] paramArrayOfColor3f) {
/*  8829 */     if (paramArrayOfColor3f != null) {
/*  8830 */       if ((this.vertexType & 0x3F0) != 0 && (this.vertexType & 0x3F0) != 64)
/*       */       {
/*  8832 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8835 */       if ((this.vertexFormat & 0x4) == 0) {
/*  8836 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*       */       }
/*       */       
/*  8839 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8840 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*  8841 */         if (indexedGeometryArrayRetained.maxColorIndex >= paramArrayOfColor3f.length) {
/*  8842 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*  8844 */       } else if (paramArrayOfColor3f.length < this.initialColorIndex + this.validVertexCount) {
/*  8845 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  8850 */     this.geomLock.getLock();
/*  8851 */     this.dirtyFlag |= 0x4;
/*  8852 */     this.colorChanged = 65535;
/*  8853 */     this.c3fRefColors = paramArrayOfColor3f;
/*  8854 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8856 */       if (paramArrayOfColor3f == null) {
/*  8857 */         this.vertexType &= 0xFFFFFFBF;
/*       */       } else {
/*  8859 */         this.vertexType |= 0x40;
/*       */       } 
/*       */     } else {
/*  8862 */       setupMirrorColorPointer(64, false);
/*       */     } 
/*  8864 */     this.geomLock.unLock();
/*  8865 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  8866 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  8872 */   Color3f[] getColorRef3f() { return this.c3fRefColors; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColorRef4f(Color4f[] paramArrayOfColor4f) {
/*  8878 */     if (paramArrayOfColor4f != null) {
/*  8879 */       if ((this.vertexType & 0x3F0) != 0 && (this.vertexType & 0x3F0) != 128)
/*       */       {
/*  8881 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*  8883 */       if ((this.vertexFormat & 0xC) == 0) {
/*  8884 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*       */       }
/*       */       
/*  8887 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8888 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*  8889 */         if (indexedGeometryArrayRetained.maxColorIndex >= paramArrayOfColor4f.length) {
/*  8890 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*  8892 */       } else if (paramArrayOfColor4f.length < this.initialColorIndex + this.validVertexCount) {
/*  8893 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */       } 
/*       */     } 
/*       */     
/*  8897 */     this.geomLock.getLock();
/*  8898 */     this.dirtyFlag |= 0x4;
/*  8899 */     this.colorChanged = 65535;
/*  8900 */     this.c4fRefColors = paramArrayOfColor4f;
/*  8901 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8903 */       if (paramArrayOfColor4f == null) {
/*  8904 */         this.vertexType &= 0xFFFFFF7F;
/*       */       } else {
/*  8906 */         this.vertexType |= 0x80;
/*       */       } 
/*       */     } else {
/*  8909 */       setupMirrorColorPointer(128, false);
/*       */     } 
/*  8911 */     this.geomLock.unLock();
/*  8912 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  8913 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*  8918 */   Color4f[] getColorRef4f() { return this.c4fRefColors; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setColorRef3b(Color3b[] paramArrayOfColor3b) {
/*  8924 */     if (paramArrayOfColor3b != null) {
/*       */       
/*  8926 */       if ((this.vertexType & 0x3F0) != 0 && (this.vertexType & 0x3F0) != 256)
/*       */       {
/*  8928 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8931 */       if ((this.vertexFormat & 0x4) == 0) {
/*  8932 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*       */       }
/*       */       
/*  8935 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8936 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8938 */         if (indexedGeometryArrayRetained.maxColorIndex >= paramArrayOfColor3b.length) {
/*  8939 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*  8941 */       } else if (paramArrayOfColor3b.length < this.initialColorIndex + this.validVertexCount) {
/*  8942 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */       } 
/*       */     } 
/*  8945 */     this.geomLock.getLock();
/*  8946 */     this.dirtyFlag |= 0x4;
/*  8947 */     this.colorChanged = 65535;
/*  8948 */     this.c3bRefColors = paramArrayOfColor3b;
/*  8949 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8951 */       if (paramArrayOfColor3b == null) {
/*  8952 */         this.vertexType &= 0xFFFFFEFF;
/*       */       } else {
/*  8954 */         this.vertexType |= 0x100;
/*       */       } 
/*       */     } else {
/*  8957 */       setupMirrorColorPointer(256, false);
/*       */     } 
/*       */     
/*  8960 */     this.geomLock.unLock();
/*  8961 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  8962 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  8968 */   Color3b[] getColorRef3b() { return this.c3bRefColors; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setColorRef4b(Color4b[] paramArrayOfColor4b) {
/*  8973 */     if (paramArrayOfColor4b != null) {
/*  8974 */       if ((this.vertexType & 0x3F0) != 0 && (this.vertexType & 0x3F0) != 512)
/*       */       {
/*  8976 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  8979 */       if ((this.vertexFormat & 0xC) == 0) {
/*  8980 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*       */       }
/*       */       
/*  8983 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  8984 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  8986 */         if (indexedGeometryArrayRetained.maxColorIndex >= paramArrayOfColor4b.length) {
/*  8987 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*  8989 */       } else if (paramArrayOfColor4b.length < this.initialColorIndex + this.validVertexCount) {
/*  8990 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */       } 
/*       */     } 
/*  8993 */     this.geomLock.getLock();
/*  8994 */     this.dirtyFlag |= 0x4;
/*  8995 */     this.colorChanged = 65535;
/*  8996 */     this.c4bRefColors = paramArrayOfColor4b;
/*  8997 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  8999 */       if (paramArrayOfColor4b == null) {
/*  9000 */         this.vertexType &= 0xFFFFFDFF;
/*       */       } else {
/*  9002 */         this.vertexType |= 0x200;
/*       */       } 
/*       */     } else {
/*  9005 */       setupMirrorColorPointer(512, false);
/*       */     } 
/*  9007 */     this.geomLock.unLock();
/*  9008 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9009 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  9015 */   Color4b[] getColorRef4b() { return this.c4bRefColors; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormalRefFloat(float[] paramArrayOfFloat) {
/*  9020 */     if (paramArrayOfFloat != null) {
/*  9021 */       if ((this.vertexType & 0xC00) != 0 && (this.vertexType & 0xC00) != 1024)
/*       */       {
/*  9023 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  9026 */       if ((this.vertexFormat & 0x2) == 0) {
/*  9027 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray122"));
/*       */       }
/*       */       
/*  9030 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9031 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9033 */         if (indexedGeometryArrayRetained.maxNormalIndex * 3 >= paramArrayOfFloat.length) {
/*  9034 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*       */         }
/*  9036 */       } else if (paramArrayOfFloat.length < 3 * (this.initialNormalIndex + this.validVertexCount)) {
/*  9037 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111"));
/*       */       } 
/*       */     } 
/*  9040 */     this.geomLock.getLock();
/*  9041 */     this.dirtyFlag |= 0x2;
/*  9042 */     this.floatRefNormals = paramArrayOfFloat;
/*  9043 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  9045 */       if (paramArrayOfFloat == null) {
/*  9046 */         this.vertexType &= 0xFFFFFBFF;
/*       */       } else {
/*  9048 */         this.vertexType |= 0x400;
/*       */       } 
/*       */     } else {
/*  9051 */       setupMirrorNormalPointer(1024);
/*       */     } 
/*  9053 */     this.geomLock.unLock();
/*       */     
/*  9055 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9056 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  9062 */   float[] getNormalRefFloat() { return this.floatRefNormals; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormalRefBuffer(J3DBuffer paramJ3DBuffer) {
/*  9068 */     FloatBufferWrapper floatBufferWrapper = null;
/*       */     
/*  9070 */     if (paramJ3DBuffer != null) {
/*  9071 */       if (paramJ3DBuffer.getBufferType() != 7) {
/*  9072 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray116"));
/*       */       }
/*  9074 */       floatBufferWrapper = (FloatBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*       */       
/*  9076 */       assert floatBufferWrapper.isDirect();
/*       */       
/*  9078 */       if ((this.vertexFormat & 0x2) == 0) {
/*  9079 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray122"));
/*       */       }
/*       */       
/*  9082 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9083 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*  9084 */         if (indexedGeometryArrayRetained.maxNormalIndex * 3 >= ((FloatBufferWrapper)paramJ3DBuffer.getBufferImpl()).limit())
/*       */         {
/*  9086 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*       */         }
/*  9088 */       } else if (floatBufferWrapper.limit() < 3 * (this.initialNormalIndex + this.validVertexCount)) {
/*  9089 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111"));
/*       */       } 
/*       */     } 
/*  9092 */     this.geomLock.getLock();
/*  9093 */     this.dirtyFlag |= 0x2;
/*  9094 */     this.normalRefBuffer = paramJ3DBuffer;
/*       */     
/*  9096 */     if (paramJ3DBuffer == null) {
/*  9097 */       this.vertexType &= 0xFFFFFBFF;
/*  9098 */       this.floatBufferRefNormals = null;
/*       */     } else {
/*       */       
/*  9101 */       this.vertexType |= 0x400;
/*  9102 */       this.floatBufferRefNormals = floatBufferWrapper;
/*       */     } 
/*  9104 */     this.geomLock.unLock();
/*       */     
/*  9106 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9107 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*  9112 */   J3DBuffer getNormalRefBuffer() { return this.normalRefBuffer; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setNormalRef3f(Vector3f[] paramArrayOfVector3f) {
/*  9117 */     if (paramArrayOfVector3f != null) {
/*  9118 */       if ((this.vertexType & 0xC00) != 0 && (this.vertexType & 0xC00) != 2048)
/*       */       {
/*  9120 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*  9123 */       if ((this.vertexFormat & 0x2) == 0) {
/*  9124 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray122"));
/*       */       }
/*       */       
/*  9127 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9128 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*  9129 */         if (indexedGeometryArrayRetained.maxNormalIndex >= paramArrayOfVector3f.length) {
/*  9130 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*       */         }
/*  9132 */       } else if (paramArrayOfVector3f.length < this.initialNormalIndex + this.validVertexCount) {
/*  9133 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111"));
/*       */       } 
/*       */     } 
/*  9136 */     this.geomLock.getLock();
/*  9137 */     this.dirtyFlag |= 0x2;
/*  9138 */     this.v3fRefNormals = paramArrayOfVector3f;
/*  9139 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  9141 */       if (paramArrayOfVector3f == null) {
/*  9142 */         this.vertexType &= 0xFFFFF7FF;
/*       */       } else {
/*  9144 */         this.vertexType |= 0x800;
/*       */       } 
/*       */     } else {
/*  9147 */       setupMirrorNormalPointer(2048);
/*       */     } 
/*  9149 */     this.geomLock.unLock();
/*  9150 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9151 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*  9156 */   Vector3f[] getNormalRef3f() { return this.v3fRefNormals; }
/*       */ 
/*       */ 
/*       */   
/*  9160 */   final int getColorStride() { return ((this.vertexFormat & 0x8) != 0) ? 4 : 3; }
/*       */ 
/*       */   
/*       */   final int getTexStride() {
/*  9164 */     if ((this.vertexFormat & 0x20) != 0) {
/*  9165 */       return 2;
/*       */     }
/*  9167 */     if ((this.vertexFormat & 0x40) != 0) {
/*  9168 */       return 3;
/*       */     }
/*  9170 */     if ((this.vertexFormat & 0x400) != 0) {
/*  9171 */       return 4;
/*       */     }
/*       */     
/*  9174 */     throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray121"));
/*       */   }
/*       */ 
/*       */   
/*       */   void setTexCoordRefFloat(int paramInt, float[] paramArrayOfFloat) {
/*  9179 */     if (this.texCoordType != 0 && this.texCoordType != 4096) {
/*  9180 */       if (paramArrayOfFloat != null) {
/*  9181 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  9187 */     if (paramArrayOfFloat != null) {
/*       */       
/*  9189 */       int i = getTexStride();
/*       */       
/*  9191 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9192 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9194 */         if (indexedGeometryArrayRetained.maxTexCoordIndices[paramInt] * i >= paramArrayOfFloat.length) {
/*  9195 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*       */         }
/*  9197 */       } else if (paramArrayOfFloat.length < i * (this.initialTexCoordIndex[paramInt] + this.validVertexCount)) {
/*  9198 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */       } 
/*       */     } 
/*       */     
/*  9202 */     this.geomLock.getLock();
/*  9203 */     this.dirtyFlag |= 0x8;
/*  9204 */     this.refTexCoords[paramInt] = paramArrayOfFloat;
/*  9205 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  9207 */       this.texCoordType = 4096;
/*  9208 */       validateTexCoordPointerType();
/*       */     } else {
/*       */       
/*  9211 */       setupMirrorTexCoordPointer(paramInt, 4096);
/*       */     } 
/*  9213 */     this.geomLock.unLock();
/*  9214 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9215 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  9221 */   float[] getTexCoordRefFloat(int paramInt) { return (float[])this.refTexCoords[paramInt]; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTexCoordRefBuffer(int paramInt, J3DBuffer paramJ3DBuffer) {
/*  9227 */     FloatBufferWrapper floatBufferWrapper = null;
/*       */     
/*  9229 */     if (paramJ3DBuffer != null) {
/*  9230 */       if (paramJ3DBuffer.getBufferType() != 7) {
/*  9231 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray116"));
/*       */       }
/*  9233 */       floatBufferWrapper = (FloatBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*  9234 */       int i = floatBufferWrapper.limit();
/*       */       
/*  9236 */       assert floatBufferWrapper.isDirect();
/*       */       
/*  9238 */       int j = getTexStride();
/*       */       
/*  9240 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9241 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*  9242 */         if (indexedGeometryArrayRetained.maxTexCoordIndices[paramInt] * j >= i) {
/*  9243 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*       */         }
/*  9245 */       } else if (i < j * (this.initialTexCoordIndex[paramInt] + this.validVertexCount)) {
/*  9246 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */       } 
/*       */     } 
/*       */     
/*  9250 */     this.geomLock.getLock();
/*  9251 */     this.dirtyFlag |= 0x8;
/*       */     
/*  9253 */     this.refTexCoordsBuffer[paramInt] = paramJ3DBuffer;
/*  9254 */     if (paramJ3DBuffer == null) {
/*  9255 */       this.refTexCoords[paramInt] = null;
/*       */     }
/*       */     else {
/*       */       
/*  9259 */       this.refTexCoords[paramInt] = floatBufferWrapper.getBufferAsObject();
/*       */     } 
/*  9261 */     this.texCoordType = 4096;
/*  9262 */     validateTexCoordPointerType();
/*  9263 */     this.geomLock.unLock();
/*  9264 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9265 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*  9270 */   J3DBuffer getTexCoordRefBuffer(int paramInt) { return (J3DBuffer)this.refTexCoordsBuffer[paramInt]; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setTexCoordRef2f(int paramInt, TexCoord2f[] paramArrayOfTexCoord2f) {
/*  9275 */     if (this.texCoordType != 0 && this.texCoordType != 8192) {
/*  9276 */       if (paramArrayOfTexCoord2f != null) {
/*  9277 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  9283 */     if (paramArrayOfTexCoord2f != null) {
/*  9284 */       if ((this.vertexFormat & 0x20) == 0) {
/*  9285 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray94"));
/*       */       }
/*       */ 
/*       */       
/*  9289 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9290 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9292 */         if (indexedGeometryArrayRetained.maxTexCoordIndices[paramInt] >= paramArrayOfTexCoord2f.length) {
/*  9293 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*       */         }
/*  9295 */       } else if (paramArrayOfTexCoord2f.length < this.initialTexCoordIndex[paramInt] + this.validVertexCount) {
/*  9296 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  9301 */     this.geomLock.getLock();
/*  9302 */     this.dirtyFlag |= 0x8;
/*  9303 */     this.refTexCoords[paramInt] = paramArrayOfTexCoord2f;
/*  9304 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  9306 */       this.texCoordType = 8192;
/*  9307 */       validateTexCoordPointerType();
/*       */     } else {
/*       */       
/*  9310 */       setupMirrorTexCoordPointer(paramInt, 8192);
/*       */     } 
/*  9312 */     this.geomLock.unLock();
/*  9313 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9314 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   TexCoord2f[] getTexCoordRef2f(int paramInt) {
/*  9320 */     if (this.refTexCoords != null && this.refTexCoords[paramInt] != null && this.refTexCoords[paramInt] instanceof TexCoord2f[])
/*       */     {
/*  9322 */       return (TexCoord2f[])this.refTexCoords[paramInt];
/*       */     }
/*  9324 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setTexCoordRef3f(int paramInt, TexCoord3f[] paramArrayOfTexCoord3f) {
/*  9331 */     if (this.texCoordType != 0 && this.texCoordType != 16384) {
/*  9332 */       if (paramArrayOfTexCoord3f != null) {
/*  9333 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray98"));
/*       */       }
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/*  9339 */     if (paramArrayOfTexCoord3f != null) {
/*       */       
/*  9341 */       if ((this.vertexFormat & 0x40) == 0) {
/*  9342 */         throw new IllegalStateException(J3dI18N.getString("GeometryArray95"));
/*       */       }
/*       */ 
/*       */       
/*  9346 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9347 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9349 */         if (indexedGeometryArrayRetained.maxTexCoordIndices[paramInt] >= paramArrayOfTexCoord3f.length) {
/*  9350 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*       */         }
/*       */       }
/*  9353 */       else if (paramArrayOfTexCoord3f.length < this.initialTexCoordIndex[paramInt] + this.validVertexCount) {
/*  9354 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  9359 */     this.geomLock.getLock();
/*  9360 */     this.dirtyFlag |= 0x8;
/*  9361 */     this.refTexCoords[paramInt] = paramArrayOfTexCoord3f;
/*  9362 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  9364 */       this.texCoordType = 16384;
/*  9365 */       validateTexCoordPointerType();
/*       */     } else {
/*       */       
/*  9368 */       setupMirrorTexCoordPointer(paramInt, 16384);
/*       */     } 
/*  9370 */     this.geomLock.unLock();
/*  9371 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9372 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   TexCoord3f[] getTexCoordRef3f(int paramInt) {
/*  9378 */     if (this.refTexCoords != null && this.refTexCoords[paramInt] != null && this.refTexCoords[paramInt] instanceof TexCoord3f[])
/*       */     {
/*  9380 */       return (TexCoord3f[])this.refTexCoords[paramInt];
/*       */     }
/*  9382 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttrRefFloat(int paramInt, float[] paramArrayOfFloat) {
/*  9405 */     if (paramArrayOfFloat != null) {
/*  9406 */       int i = this.vertexAttrSizes[paramInt];
/*       */       
/*  9408 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9409 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9411 */         if (i * indexedGeometryArrayRetained.maxVertexAttrIndices[paramInt] >= paramArrayOfFloat.length) {
/*  9412 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray30"));
/*       */         }
/*       */       }
/*  9415 */       else if (paramArrayOfFloat.length < i * (this.initialVertexAttrIndex[paramInt] + this.validVertexCount)) {
/*  9416 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray129"));
/*       */       } 
/*       */     } 
/*       */     
/*  9420 */     this.geomLock.getLock();
/*  9421 */     this.dirtyFlag |= 0x80;
/*  9422 */     this.floatRefVertexAttrs[paramInt] = paramArrayOfFloat;
/*  9423 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0)) {
/*       */       
/*  9425 */       this.vertexAttrType = 32768;
/*  9426 */       validateVertexAttrPointerType();
/*       */     } else {
/*       */       
/*  9429 */       setupMirrorVertexAttrPointer(paramInt, 32768);
/*       */     } 
/*  9431 */     this.geomLock.unLock();
/*  9432 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9433 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  9442 */   float[] getVertexAttrRefFloat(int paramInt) { return this.floatRefVertexAttrs[paramInt]; }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setVertexAttrRefBuffer(int paramInt, J3DBuffer paramJ3DBuffer) {
/*  9452 */     FloatBufferWrapper floatBufferWrapper = null;
/*       */     
/*  9454 */     if (paramJ3DBuffer != null) {
/*  9455 */       if (paramJ3DBuffer.getBufferType() != 7) {
/*  9456 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray116"));
/*       */       }
/*  9458 */       floatBufferWrapper = (FloatBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*  9459 */       int i = floatBufferWrapper.limit();
/*       */       
/*  9461 */       assert floatBufferWrapper.isDirect();
/*       */       
/*  9463 */       int j = this.vertexAttrSizes[paramInt];
/*       */       
/*  9465 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9466 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9468 */         if (indexedGeometryArrayRetained.maxVertexAttrIndices[paramInt] * j >= i) {
/*  9469 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray30"));
/*       */         }
/*  9471 */       } else if (i < j * (this.initialVertexAttrIndex[paramInt] + this.validVertexCount)) {
/*  9472 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray129"));
/*       */       } 
/*       */     } 
/*       */     
/*  9476 */     this.geomLock.getLock();
/*  9477 */     this.dirtyFlag |= 0x80;
/*  9478 */     this.vertexAttrsRefBuffer[paramInt] = paramJ3DBuffer;
/*  9479 */     if (paramJ3DBuffer == null) {
/*  9480 */       this.floatBufferRefVertexAttrs[paramInt] = null;
/*  9481 */       this.nioFloatBufferRefVertexAttrs[paramInt] = null;
/*       */     } else {
/*       */       
/*  9484 */       this.floatBufferRefVertexAttrs[paramInt] = floatBufferWrapper;
/*  9485 */       this.nioFloatBufferRefVertexAttrs[paramInt] = floatBufferWrapper.getBufferAsObject();
/*       */     } 
/*       */     
/*  9488 */     this.vertexAttrType = 32768;
/*  9489 */     validateVertexAttrPointerType();
/*  9490 */     this.geomLock.unLock();
/*  9491 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9492 */       sendDataChangedMessage(false);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  9502 */   J3DBuffer getVertexAttrRefBuffer(int paramInt) { return this.vertexAttrsRefBuffer[paramInt]; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setInterleavedVertices(float[] paramArrayOfFloat) {
/*  9507 */     if (paramArrayOfFloat != null)
/*       */     {
/*  9509 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9510 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9512 */         if (this.stride * indexedGeometryArrayRetained.maxCoordIndex >= paramArrayOfFloat.length) {
/*  9513 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*       */         
/*  9516 */         if ((this.vertexFormat & 0x460) != 0) {
/*  9517 */           for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  9518 */             if (this.stride * indexedGeometryArrayRetained.maxTexCoordIndices[b] >= paramArrayOfFloat.length) {
/*  9519 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*       */             }
/*       */           } 
/*       */         }
/*       */ 
/*       */         
/*  9525 */         if ((this.vertexFormat & 0x4) != 0 && this.stride * indexedGeometryArrayRetained.maxColorIndex >= paramArrayOfFloat.length)
/*       */         {
/*  9527 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*       */         
/*  9530 */         if ((this.vertexFormat & 0x2) != 0 && this.stride * indexedGeometryArrayRetained.maxNormalIndex >= paramArrayOfFloat.length)
/*       */         {
/*  9532 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*       */         }
/*       */       }
/*  9535 */       else if (paramArrayOfFloat.length < this.stride * (this.initialVertexIndex + this.validVertexCount)) {
/*  9536 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray114"));
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  9542 */     this.geomLock.getLock();
/*  9543 */     this.dirtyFlag |= 0x8F;
/*  9544 */     this.colorChanged = 65535;
/*  9545 */     this.interLeavedVertexData = paramArrayOfFloat;
/*  9546 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0))
/*       */     {
/*  9548 */       setupMirrorInterleavedColorPointer(false);
/*       */     }
/*  9550 */     this.geomLock.unLock();
/*  9551 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9552 */       processCoordsChanged((paramArrayOfFloat == null));
/*  9553 */       sendDataChangedMessage(true);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   void setInterleavedVertexBuffer(J3DBuffer paramJ3DBuffer) {
/*  9560 */     FloatBufferWrapper floatBufferWrapper = null;
/*       */     
/*  9562 */     if (paramJ3DBuffer != null) {
/*       */       
/*  9564 */       if (paramJ3DBuffer.getBufferType() != 7) {
/*  9565 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray116"));
/*       */       }
/*  9567 */       floatBufferWrapper = (FloatBufferWrapper)paramJ3DBuffer.getBufferImpl();
/*       */       
/*  9569 */       assert floatBufferWrapper.isDirect();
/*       */       
/*  9571 */       int i = floatBufferWrapper.limit();
/*       */       
/*  9573 */       if (this instanceof IndexedGeometryArrayRetained) {
/*  9574 */         IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)this;
/*       */         
/*  9576 */         if (this.stride * indexedGeometryArrayRetained.maxCoordIndex >= i) {
/*  9577 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*       */         
/*  9580 */         if ((this.vertexFormat & 0x460) != 0) {
/*  9581 */           for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  9582 */             if (this.stride * indexedGeometryArrayRetained.maxTexCoordIndices[b] >= i) {
/*  9583 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*       */             }
/*       */           } 
/*       */         }
/*       */ 
/*       */         
/*  9589 */         if ((this.vertexFormat & 0x4) != 0 && this.stride * indexedGeometryArrayRetained.maxColorIndex >= i)
/*       */         {
/*  9591 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*       */         }
/*       */         
/*  9594 */         if ((this.vertexFormat & 0x2) != 0 && this.stride * indexedGeometryArrayRetained.maxNormalIndex >= i)
/*       */         {
/*  9596 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*       */         }
/*       */       }
/*  9599 */       else if (i < this.stride * (this.initialVertexIndex + this.validVertexCount)) {
/*  9600 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray114"));
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  9605 */     this.geomLock.getLock();
/*  9606 */     this.dirtyFlag |= 0x8F;
/*  9607 */     this.colorChanged = 65535;
/*  9608 */     this.interleavedVertexBuffer = paramJ3DBuffer;
/*       */     
/*  9610 */     if (paramJ3DBuffer == null) {
/*  9611 */       this.interleavedFloatBufferImpl = null;
/*       */     } else {
/*  9613 */       this.interleavedFloatBufferImpl = floatBufferWrapper;
/*       */     } 
/*  9615 */     if (this.inUpdater || (this instanceof IndexedGeometryArrayRetained && (this.vertexFormat & 0x200) == 0))
/*       */     {
/*  9617 */       setupMirrorInterleavedColorPointer(false);
/*       */     }
/*  9619 */     this.geomLock.unLock();
/*  9620 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9621 */       processCoordsChanged((paramJ3DBuffer == null));
/*  9622 */       sendDataChangedMessage(true);
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*  9627 */   float[] getInterleavedVertices() { return this.interLeavedVertexData; }
/*       */ 
/*       */ 
/*       */   
/*  9631 */   J3DBuffer getInterleavedVertexBuffer() { return this.interleavedVertexBuffer; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setValidVertexCount(int paramInt) {
/*  9636 */     boolean bool = false;
/*  9637 */     if (paramInt < 0) {
/*  9638 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray110"));
/*       */     }
/*       */     
/*  9641 */     if (this.initialVertexIndex + paramInt > this.vertexCount) {
/*  9642 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray100"));
/*       */     }
/*       */     
/*  9645 */     if ((this.vertexFormat & 0x100) != 0) {
/*       */ 
/*       */ 
/*       */       
/*  9649 */       if ((this.vertexFormat & 0x800) != 0 && this.interleavedFloatBufferImpl != null) {
/*  9650 */         if (this.interleavedFloatBufferImpl.limit() < this.stride * (this.initialVertexIndex + paramInt)) {
/*  9651 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray114"));
/*       */         
/*       */         }
/*       */       }
/*  9655 */       else if (this.interLeavedVertexData != null) {
/*  9656 */         if (this.interLeavedVertexData.length < this.stride * (this.initialVertexIndex + paramInt)) {
/*  9657 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray114"));
/*       */         }
/*       */       } else {
/*       */         
/*  9661 */         bool = true;
/*       */       } 
/*  9663 */     } else if ((this.vertexFormat & 0x80) != 0) {
/*       */ 
/*       */       
/*  9666 */       if (this.initialCoordIndex + paramInt > this.vertexCount) {
/*  9667 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray104"));
/*       */       }
/*  9669 */       if (this.initialColorIndex + paramInt > this.vertexCount) {
/*  9670 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray101"));
/*       */       }
/*  9672 */       if (this.initialNormalIndex + paramInt > this.vertexCount) {
/*  9673 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray102"));
/*       */       }
/*       */       
/*  9676 */       if ((this.vertexFormat & 0x460) != 0) {
/*  9677 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  9678 */           if (this.initialTexCoordIndex[b] + paramInt > this.vertexCount) {
/*  9679 */             throw new IllegalArgumentException(J3dI18N.getString("GeometryArray103"));
/*       */           }
/*       */         } 
/*       */       }
/*       */ 
/*       */       
/*  9685 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  9686 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  9687 */           if (this.initialVertexAttrIndex[b] + paramInt > this.vertexCount) {
/*  9688 */             throw new IllegalArgumentException(J3dI18N.getString("GeometryArray130"));
/*       */           }
/*       */         } 
/*       */       }
/*       */ 
/*       */       
/*  9694 */       if ((this.vertexType & 0xF) == 0) {
/*  9695 */         bool = true;
/*       */       }
/*       */       
/*  9698 */       if ((this.vertexFormat & 0x800) != 0) {
/*       */         int i; byte b;
/*  9700 */         switch (this.vertexType & 0xF) {
/*       */           case 1:
/*  9702 */             if (this.floatBufferRefCoords.limit() < 3 * (this.initialCoordIndex + paramInt)) {
/*  9703 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */             }
/*       */             break;
/*       */           case 2:
/*  9707 */             if (this.doubleBufferRefCoords.limit() < 3 * (this.initialCoordIndex + paramInt)) {
/*  9708 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */             }
/*       */             break;
/*       */         } 
/*       */         
/*  9713 */         switch (this.vertexType & 0x3F0) {
/*       */           case 16:
/*  9715 */             if ((this.vertexFormat & 0xC) == 4) {
/*  9716 */               if (this.floatBufferRefColors.limit() < 3 * (this.initialColorIndex + paramInt))
/*  9717 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */               break;
/*       */             } 
/*  9720 */             if ((this.vertexFormat & 0xC) == 12 && 
/*  9721 */               this.floatBufferRefColors.limit() < 4 * (this.initialColorIndex + paramInt)) {
/*  9722 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */           
/*       */           case 32:
/*  9727 */             if ((this.vertexFormat & 0xC) == 4) {
/*  9728 */               if (this.byteBufferRefColors.limit() < 3 * (this.initialColorIndex + paramInt))
/*  9729 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */               break;
/*       */             } 
/*  9732 */             if ((this.vertexFormat & 0xC) == 12 && 
/*  9733 */               this.byteBufferRefColors.limit() < 4 * (this.initialColorIndex + paramInt)) {
/*  9734 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */         } 
/*       */         
/*  9739 */         switch (this.vertexType & 0x7000) {
/*       */           
/*       */           case 4096:
/*  9742 */             for (i = 0; i < this.texCoordSetCount; i++) {
/*  9743 */               FloatBufferWrapper floatBufferWrapper = (FloatBufferWrapper)((J3DBuffer)this.refTexCoordsBuffer[i]).getBufferImpl();
/*  9744 */               if ((this.vertexFormat & 0x20) != 0) {
/*  9745 */                 if (floatBufferWrapper.limit() < 2 * (this.initialTexCoordIndex[i] + paramInt)) {
/*  9746 */                   throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */                 }
/*       */               }
/*  9749 */               else if ((this.vertexFormat & 0x40) != 0) {
/*  9750 */                 if (floatBufferWrapper.limit() < 3 * (this.initialTexCoordIndex[i] + paramInt)) {
/*  9751 */                   throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */                 }
/*       */               }
/*  9754 */               else if ((this.vertexFormat & 0x400) != 0 && 
/*  9755 */                 floatBufferWrapper.limit() < 4 * (this.initialTexCoordIndex[i] + paramInt)) {
/*  9756 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */               } 
/*       */             } 
/*       */             break;
/*       */         } 
/*       */ 
/*       */         
/*  9763 */         switch (this.vertexType & 0xC00) {
/*       */           case 1024:
/*  9765 */             if (this.floatBufferRefNormals.limit() < 3 * (this.initialNormalIndex + paramInt)) {
/*  9766 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111"));
/*       */             }
/*       */             break;
/*       */         } 
/*  9770 */         switch (this.vertexType & 0x8000) {
/*       */           case 32768:
/*  9772 */             for (b = 0; b < this.vertexAttrCount; b++) {
/*  9773 */               i = this.vertexAttrSizes[b];
/*  9774 */               if (this.floatBufferRefVertexAttrs[b].limit() < i * (this.initialVertexAttrIndex[b] + paramInt))
/*       */               {
/*  9776 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray129"));
/*       */               }
/*       */             } 
/*       */             break;
/*       */         } 
/*       */ 
/*       */       
/*       */       } else {
/*       */         byte b;
/*  9785 */         switch (this.vertexType & 0xF) {
/*       */           case 1:
/*  9787 */             if (this.floatRefCoords.length < 3 * (this.initialCoordIndex + paramInt)) {
/*  9788 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */             }
/*       */             break;
/*       */           case 2:
/*  9792 */             if (this.doubleRefCoords.length < 3 * (this.initialCoordIndex + paramInt)) {
/*  9793 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */             }
/*       */             break;
/*       */           case 4:
/*  9797 */             if (this.p3fRefCoords.length < this.initialCoordIndex + paramInt) {
/*  9798 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */             }
/*       */             break;
/*       */           case 8:
/*  9802 */             if (this.p3dRefCoords.length < this.initialCoordIndex + paramInt) {
/*  9803 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */             }
/*       */             break;
/*       */         } 
/*  9807 */         switch (this.vertexType & 0x3F0) {
/*       */           case 16:
/*  9809 */             if ((this.vertexFormat & 0xC) == 4) {
/*  9810 */               if (this.floatRefColors.length < 3 * (this.initialColorIndex + paramInt))
/*  9811 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */               break;
/*       */             } 
/*  9814 */             if ((this.vertexFormat & 0xC) == 12 && 
/*  9815 */               this.floatRefColors.length < 4 * (this.initialColorIndex + paramInt)) {
/*  9816 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */           
/*       */           case 32:
/*  9821 */             if ((this.vertexFormat & 0xC) == 4) {
/*  9822 */               if (this.byteRefColors.length < 3 * (this.initialColorIndex + paramInt))
/*  9823 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */               break;
/*       */             } 
/*  9826 */             if ((this.vertexFormat & 0xC) == 12 && 
/*  9827 */               this.byteRefColors.length < 4 * (this.initialColorIndex + paramInt)) {
/*  9828 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */           
/*       */           case 64:
/*  9833 */             if (this.c3fRefColors.length < this.initialColorIndex + paramInt) {
/*  9834 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */           case 128:
/*  9838 */             if (this.c4fRefColors.length < this.initialColorIndex + paramInt) {
/*  9839 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */           case 256:
/*  9843 */             if (this.c3bRefColors.length < this.initialColorIndex + paramInt) {
/*  9844 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */           case 512:
/*  9848 */             if (this.c4bRefColors.length < this.initialColorIndex + paramInt) {
/*  9849 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */             }
/*       */             break;
/*       */         } 
/*  9853 */         switch (this.vertexType & 0x7000) {
/*       */           case 4096:
/*  9855 */             for (b = 0; b < this.texCoordSetCount; b++) {
/*  9856 */               if ((this.vertexFormat & 0x20) != 0) {
/*  9857 */                 if ((float[])this.refTexCoords[b].length < 2 * (this.initialTexCoordIndex[b] + paramInt)) {
/*  9858 */                   throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */                 }
/*       */               }
/*  9861 */               else if ((this.vertexFormat & 0x40) != 0) {
/*  9862 */                 if ((float[])this.refTexCoords[b].length < 3 * (this.initialTexCoordIndex[b] + paramInt)) {
/*  9863 */                   throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */                 }
/*  9865 */                 if ((this.vertexFormat & 0x400) != 0 && 
/*  9866 */                   (float[])this.refTexCoords[b].length < 4 * (this.initialTexCoordIndex[b] + paramInt)) {
/*  9867 */                   throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */                 }
/*       */               } 
/*       */             } 
/*       */             break;
/*       */ 
/*       */           
/*       */           case 8192:
/*  9875 */             for (b = 0; b < this.texCoordSetCount; b++) {
/*  9876 */               if ((TexCoord2f[])this.refTexCoords[b].length < this.initialTexCoordIndex[b] + paramInt) {
/*  9877 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */               }
/*       */             } 
/*       */             break;
/*       */           
/*       */           case 16384:
/*  9883 */             for (b = 0; b < this.texCoordSetCount; b++) {
/*  9884 */               if ((TexCoord3f[])this.refTexCoords[b].length < this.initialTexCoordIndex[b] + paramInt) {
/*  9885 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */               }
/*       */             } 
/*       */             break;
/*       */         } 
/*       */         
/*  9891 */         switch (this.vertexType & 0xC00) {
/*       */           case 1024:
/*  9893 */             if (this.floatRefNormals.length < 3 * (this.initialNormalIndex + paramInt)) {
/*  9894 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111"));
/*       */             }
/*       */             break;
/*       */           case 2048:
/*  9898 */             if (this.v3fRefNormals.length < this.initialNormalIndex + paramInt)
/*  9899 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111")); 
/*       */             break;
/*       */         } 
/*  9902 */         switch (this.vertexType & 0x8000) {
/*       */           case 32768:
/*  9904 */             for (b = 0; b < this.vertexAttrCount; b++) {
/*  9905 */               int i = this.vertexAttrSizes[b];
/*  9906 */               if (this.floatRefVertexAttrs[b].length < i * (this.initialVertexAttrIndex[b] + paramInt))
/*       */               {
/*  9908 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray129"));
/*       */               }
/*       */             } 
/*       */             break;
/*       */         } 
/*       */ 
/*       */       
/*       */       } 
/*       */     } 
/*  9917 */     this.geomLock.getLock();
/*  9918 */     this.dirtyFlag |= 0x8F;
/*  9919 */     this.validVertexCount = paramInt;
/*  9920 */     this.geomLock.unLock();
/*  9921 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9922 */       processCoordsChanged(bool);
/*  9923 */       sendDataChangedMessage(true);
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*  9929 */   int getValidVertexCount() { return this.validVertexCount; }
/*       */ 
/*       */ 
/*       */   
/*       */   void setInitialVertexIndex(int paramInt) {
/*  9934 */     boolean bool = false;
/*       */     
/*  9936 */     if (paramInt + this.validVertexCount > this.vertexCount) {
/*  9937 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray100"));
/*       */     }
/*       */     
/*  9940 */     if ((this.vertexFormat & 0x800) != 0 && this.interleavedFloatBufferImpl != null) {
/*  9941 */       if (this.interleavedFloatBufferImpl.limit() < this.stride * (paramInt + this.validVertexCount)) {
/*  9942 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray114"));
/*       */       
/*       */       }
/*       */     }
/*  9946 */     else if (this.interLeavedVertexData != null) {
/*  9947 */       if (this.interLeavedVertexData.length < this.stride * (paramInt + this.validVertexCount)) {
/*  9948 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray114"));
/*       */       }
/*       */     } else {
/*       */       
/*  9952 */       bool = ((this.vertexFormat & 0x100) != 0);
/*       */     } 
/*  9954 */     this.geomLock.getLock();
/*  9955 */     this.dirtyFlag |= 0x8F;
/*  9956 */     this.initialVertexIndex = paramInt;
/*  9957 */     this.geomLock.unLock();
/*  9958 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  9959 */       processCoordsChanged(bool);
/*  9960 */       sendDataChangedMessage(true);
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*  9965 */   int getInitialVertexIndex() { return this.initialVertexIndex; }
/*       */ 
/*       */   
/*       */   void setInitialCoordIndex(int paramInt) {
/*  9969 */     if (paramInt + this.validVertexCount > this.vertexCount) {
/*  9970 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray104"));
/*       */     }
/*       */     
/*  9973 */     if ((this.vertexFormat & 0x800) != 0) {
/*  9974 */       switch (this.vertexType & 0xF) {
/*       */         case 1:
/*  9976 */           if (this.floatBufferRefCoords.limit() < paramInt + this.validVertexCount) {
/*  9977 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */           }
/*       */           break;
/*       */         case 2:
/*  9981 */           if (this.doubleBufferRefCoords.limit() < paramInt + this.validVertexCount) {
/*  9982 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */           }
/*       */           break;
/*       */       } 
/*       */     } else {
/*  9987 */       switch (this.vertexType & 0xF) {
/*       */         case 1:
/*  9989 */           if (this.floatRefCoords.length < 3 * (paramInt + this.validVertexCount)) {
/*  9990 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */           }
/*       */           break;
/*       */         case 2:
/*  9994 */           if (this.doubleRefCoords.length < 3 * (paramInt + this.validVertexCount)) {
/*  9995 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */           }
/*       */           break;
/*       */         case 4:
/*  9999 */           if (this.p3fRefCoords.length < paramInt + this.validVertexCount) {
/* 10000 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */           }
/*       */           break;
/*       */         case 8:
/* 10004 */           if (this.p3dRefCoords.length < paramInt + this.validVertexCount) {
/* 10005 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray99"));
/*       */           }
/*       */           break;
/*       */       } 
/*       */     } 
/* 10010 */     this.geomLock.getLock();
/* 10011 */     this.dirtyFlag |= 0x1;
/* 10012 */     this.initialCoordIndex = paramInt;
/* 10013 */     this.dirtyFlag |= 0x1;
/* 10014 */     this.geomLock.unLock();
/*       */     
/* 10016 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/* 10017 */       processCoordsChanged(((this.vertexType & 0xF) == 0));
/* 10018 */       sendDataChangedMessage(true);
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/* 10023 */   int getInitialCoordIndex() { return this.initialCoordIndex; }
/*       */ 
/*       */   
/*       */   void setInitialColorIndex(int paramInt) {
/* 10027 */     if (paramInt + this.validVertexCount > this.vertexCount) {
/* 10028 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray101"));
/*       */     }
/*       */     
/* 10031 */     if ((this.vertexFormat & 0x800) != 0) {
/* 10032 */       switch (this.vertexType & 0x3F0) {
/*       */         case 16:
/* 10034 */           if ((this.vertexFormat & 0xC) == 4) {
/* 10035 */             if (this.floatBufferRefColors.limit() < 3 * (paramInt + this.validVertexCount))
/* 10036 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */             break;
/*       */           } 
/* 10039 */           if ((this.vertexFormat & 0xC) == 12 && 
/* 10040 */             this.floatBufferRefColors.limit() < 4 * (paramInt + this.validVertexCount)) {
/* 10041 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */ 
/*       */         
/*       */         case 32:
/* 10047 */           if ((this.vertexFormat & 0xC) == 4) {
/* 10048 */             if (this.byteBufferRefColors.limit() < 3 * (paramInt + this.validVertexCount))
/* 10049 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */             break;
/*       */           } 
/* 10052 */           if ((this.vertexFormat & 0xC) == 12 && 
/* 10053 */             this.byteBufferRefColors.limit() < 4 * (paramInt + this.validVertexCount)) {
/* 10054 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */     
/*       */     } else {
/* 10062 */       switch (this.vertexType & 0x3F0) {
/*       */         case 16:
/* 10064 */           if ((this.vertexFormat & 0xC) == 4) {
/* 10065 */             if (this.floatRefColors.length < 3 * (paramInt + this.validVertexCount))
/* 10066 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */             break;
/*       */           } 
/* 10069 */           if ((this.vertexFormat & 0xC) == 12 && 
/* 10070 */             this.floatRefColors.length < 4 * (paramInt + this.validVertexCount)) {
/* 10071 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */         
/*       */         case 32:
/* 10076 */           if ((this.vertexFormat & 0xC) == 4) {
/* 10077 */             if (this.byteRefColors.length < 3 * (paramInt + this.validVertexCount))
/* 10078 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112")); 
/*       */             break;
/*       */           } 
/* 10081 */           if ((this.vertexFormat & 0xC) == 12 && 
/* 10082 */             this.byteRefColors.length < 4 * (paramInt + this.validVertexCount)) {
/* 10083 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */         
/*       */         case 64:
/* 10088 */           if (this.c3fRefColors.length < paramInt + this.validVertexCount) {
/* 10089 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */         case 128:
/* 10093 */           if (this.c4fRefColors.length < paramInt + this.validVertexCount) {
/* 10094 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */         case 256:
/* 10098 */           if (this.c3bRefColors.length < paramInt + this.validVertexCount) {
/* 10099 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */         case 512:
/* 10103 */           if (this.c4bRefColors.length < paramInt + this.validVertexCount) {
/* 10104 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray112"));
/*       */           }
/*       */           break;
/*       */       } 
/*       */     } 
/* 10109 */     this.geomLock.getLock();
/* 10110 */     this.dirtyFlag |= 0x4;
/* 10111 */     this.colorChanged = 65535;
/* 10112 */     this.initialColorIndex = paramInt;
/* 10113 */     this.geomLock.unLock();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 10120 */   int getInitialColorIndex() { return this.initialColorIndex; }
/*       */ 
/*       */   
/*       */   void setInitialNormalIndex(int paramInt) {
/* 10124 */     if (paramInt + this.validVertexCount > this.vertexCount) {
/* 10125 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray102"));
/*       */     }
/* 10127 */     if ((this.vertexFormat & 0x800) != 0) {
/* 10128 */       if ((this.vertexType & 0xC00) == 1024 && 
/* 10129 */         this.floatBufferRefNormals.limit() < 3 * (paramInt + this.validVertexCount)) {
/* 10130 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111"));
/*       */       }
/*       */     } else {
/*       */       
/* 10134 */       switch (this.vertexType & 0xC00) {
/*       */         case 1024:
/* 10136 */           if (this.floatRefNormals.length < 3 * (paramInt + this.validVertexCount)) {
/* 10137 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111"));
/*       */           }
/*       */           break;
/*       */         case 2048:
/* 10141 */           if (this.v3fRefNormals.length < paramInt + this.validVertexCount)
/* 10142 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray111")); 
/*       */           break;
/*       */       } 
/*       */     } 
/* 10146 */     this.geomLock.getLock();
/* 10147 */     this.dirtyFlag |= 0x2;
/* 10148 */     this.initialNormalIndex = paramInt;
/* 10149 */     this.geomLock.unLock();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 10155 */   int getInitialNormalIndex() { return this.initialNormalIndex; }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setInitialVertexAttrIndex(int paramInt1, int paramInt2) {
/* 10165 */     if (paramInt2 + this.validVertexCount > this.vertexCount) {
/* 10166 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray130"));
/*       */     }
/*       */     
/* 10169 */     int i = this.vertexAttrSizes[paramInt1];
/* 10170 */     int j = i * (paramInt2 + this.validVertexCount);
/* 10171 */     if ((this.vertexType & 0x8000) == 32768) {
/* 10172 */       if ((this.vertexFormat & 0x800) != 0) {
/* 10173 */         if (this.floatBufferRefVertexAttrs[paramInt1].limit() < j) {
/* 10174 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray129"));
/*       */         
/*       */         }
/*       */       }
/* 10178 */       else if (this.floatRefVertexAttrs[paramInt1].length < j) {
/* 10179 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray129"));
/*       */       } 
/*       */     }
/*       */ 
/*       */     
/* 10184 */     this.geomLock.getLock();
/* 10185 */     this.dirtyFlag |= 0x80;
/* 10186 */     this.initialVertexAttrIndex[paramInt1] = paramInt2;
/* 10187 */     this.geomLock.unLock();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 10198 */   int getInitialVertexAttrIndex(int paramInt) { return this.initialVertexAttrIndex[paramInt]; }
/*       */ 
/*       */   
/*       */   void setInitialTexCoordIndex(int paramInt1, int paramInt2) {
/* 10202 */     if (paramInt2 + this.validVertexCount > this.vertexCount) {
/* 10203 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray103"));
/*       */     }
/*       */     
/* 10206 */     if ((this.vertexFormat & 0x800) != 0) {
/* 10207 */       if ((this.vertexType & 0x7000) == 4096) {
/* 10208 */         FloatBufferWrapper floatBufferWrapper = (FloatBufferWrapper)((J3DBuffer)this.refTexCoordsBuffer[paramInt1]).getBufferImpl();
/* 10209 */         if ((this.vertexFormat & 0x20) != 0) {
/* 10210 */           if (floatBufferWrapper.limit() < 2 * (paramInt2 + this.validVertexCount)) {
/* 10211 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */           }
/*       */         }
/* 10214 */         else if ((this.vertexFormat & 0x40) != 0) {
/* 10215 */           if (floatBufferWrapper.limit() < 3 * (paramInt2 + this.validVertexCount)) {
/* 10216 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */           }
/*       */         }
/* 10219 */         else if ((this.vertexFormat & 0x400) != 0 && 
/* 10220 */           floatBufferWrapper.limit() < 4 * (paramInt2 + this.validVertexCount)) {
/* 10221 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */         }
/*       */       
/*       */       } 
/*       */     } else {
/*       */       
/* 10227 */       switch (this.vertexType & 0x7000) {
/*       */         case 4096:
/* 10229 */           if ((this.vertexFormat & 0x20) != 0) {
/* 10230 */             if ((float[])this.refTexCoords[paramInt1].length < 2 * (paramInt2 + this.validVertexCount))
/* 10231 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113")); 
/*       */             break;
/*       */           } 
/* 10234 */           if ((this.vertexFormat & 0x40) != 0) {
/* 10235 */             if ((float[])this.refTexCoords[paramInt1].length < 3 * (paramInt2 + this.validVertexCount))
/* 10236 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113")); 
/*       */             break;
/*       */           } 
/* 10239 */           if ((this.vertexFormat & 0x400) != 0 && 
/* 10240 */             (float[])this.refTexCoords[paramInt1].length < 4 * (paramInt2 + this.validVertexCount)) {
/* 10241 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */           }
/*       */           break;
/*       */ 
/*       */         
/*       */         case 8192:
/* 10247 */           if ((TexCoord2f[])this.refTexCoords[paramInt1].length < paramInt2 + this.validVertexCount) {
/* 10248 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */           }
/*       */           break;
/*       */         
/*       */         case 16384:
/* 10253 */           if ((TexCoord3f[])this.refTexCoords[paramInt1].length < paramInt2 + this.validVertexCount) {
/* 10254 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray113"));
/*       */           }
/*       */           break;
/*       */       } 
/*       */     
/*       */     } 
/* 10260 */     this.geomLock.getLock();
/* 10261 */     this.dirtyFlag |= 0x8;
/* 10262 */     this.initialTexCoordIndex[paramInt1] = paramInt2;
/* 10263 */     this.geomLock.unLock();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 10269 */   int getInitialTexCoordIndex(int paramInt) { return this.initialTexCoordIndex[paramInt]; }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 10274 */   int getTexCoordSetCount() { return this.texCoordSetCount; }
/*       */ 
/*       */   
/*       */   int getTexCoordSetMapLength() {
/* 10278 */     if (this.texCoordSetMap != null) {
/* 10279 */       return this.texCoordSetMap.length;
/*       */     }
/* 10281 */     return 0;
/*       */   }
/*       */ 
/*       */   
/*       */   void getTexCoordSetMap(int[] paramArrayOfInt) {
/* 10286 */     if (this.texCoordSetMap != null) {
/* 10287 */       for (byte b = 0; b < this.texCoordSetMap.length; b++) {
/* 10288 */         paramArrayOfInt[b] = this.texCoordSetMap[b];
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */   void freeDlistId() {
/* 10294 */     if (this.dlistId != -1) {
/* 10295 */       VirtualUniverse.mc.freeDisplayListId(this.dlistObj);
/* 10296 */       this.dlistId = -1;
/*       */     } 
/*       */   }
/*       */   
/*       */   void assignDlistId() {
/* 10301 */     if (this.dlistId == -1) {
/* 10302 */       this.dlistObj = VirtualUniverse.mc.getDisplayListId();
/* 10303 */       this.dlistId = this.dlistObj.intValue();
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   void addDlistUser(RenderBin paramRenderBin, RenderAtomListInfo paramRenderAtomListInfo) {
/* 10310 */     if (this.dlistUsers == null) {
/* 10311 */       this.dlistUsers = new HashMap(2, 1.0F);
/*       */     }
/*       */     
/* 10314 */     Set set = (Set)this.dlistUsers.get(paramRenderBin);
/* 10315 */     if (set == null) {
/* 10316 */       set = new HashSet();
/* 10317 */       this.dlistUsers.put(paramRenderBin, set);
/*       */     } 
/* 10319 */     set.add(paramRenderAtomListInfo);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   void removeDlistUser(RenderBin paramRenderBin, RenderAtomListInfo paramRenderAtomListInfo) {
/* 10325 */     if (this.dlistUsers == null) {
/*       */       return;
/*       */     }
/*       */ 
/*       */     
/* 10330 */     Set set = (Set)this.dlistUsers.get(paramRenderBin);
/* 10331 */     if (set == null) {
/*       */       return;
/*       */     }
/*       */     
/* 10335 */     set.remove(paramRenderAtomListInfo);
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   boolean isDlistUserSetEmpty(RenderBin paramRenderBin) {
/* 10341 */     if (this.dlistUsers == null) {
/* 10342 */       return true;
/*       */     }
/*       */     
/* 10345 */     Set set = (Set)this.dlistUsers.get(paramRenderBin);
/* 10346 */     if (set == null) {
/* 10347 */       return true;
/*       */     }
/* 10349 */     return set.isEmpty();
/*       */   }
/*       */ 
/*       */   
/*       */   int numDlistUsers(RenderBin paramRenderBin) {
/* 10354 */     if (isDlistUserSetEmpty(paramRenderBin)) {
/* 10355 */       return 0;
/*       */     }
/* 10357 */     Set set = (Set)this.dlistUsers.get(paramRenderBin);
/* 10358 */     return set.size();
/*       */   }
/*       */   
/*       */   void setDlistTimeStamp(int paramInt, long paramLong) {
/* 10362 */     int i = getIndex(paramInt);
/* 10363 */     if (i >= this.timeStampPerDlist.length) {
/* 10364 */       long[] arrayOfLong = new long[i * 2];
/* 10365 */       for (byte b = 0; b < this.timeStampPerDlist.length; b++) {
/* 10366 */         arrayOfLong[b] = this.timeStampPerDlist[b];
/*       */       }
/* 10368 */       this.timeStampPerDlist = arrayOfLong;
/*       */     } 
/* 10370 */     this.timeStampPerDlist[i] = paramLong;
/*       */   }
/*       */   
/*       */   long getDlistTimeStamp(int paramInt) {
/* 10374 */     int i = getIndex(paramInt);
/*       */ 
/*       */     
/* 10377 */     if (i >= this.timeStampPerDlist.length) {
/* 10378 */       setDlistTimeStamp(paramInt, 0L);
/*       */     }
/* 10380 */     return this.timeStampPerDlist[i];
/*       */   }
/*       */   
/*       */   int getIndex(int paramInt) {
/* 10384 */     byte b = 0;
/*       */     
/* 10386 */     while (paramInt > 0) {
/* 10387 */       b++;
/* 10388 */       paramInt >>= 1;
/*       */     } 
/* 10390 */     return b;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   boolean isWriteStatic() {
/* 10396 */     if (this.source.getCapability(1) || this.source.getCapability(3) || this.source.getCapability(5) || this.source.getCapability(7) || this.source.getCapability(23) || this.source.getCapability(20) || this.source.getCapability(19))
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 10403 */       return false;
/*       */     }
/* 10405 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void setCompiled(ArrayList paramArrayList) {
/* 10413 */     int i = paramArrayList.size();
/* 10414 */     int j = 0;
/* 10415 */     this.geoOffset = new int[i];
/* 10416 */     this.compileVcount = new int[i];
/* 10417 */     int k = 0, m = 0;
/* 10418 */     k = 0;
/* 10419 */     this.isCompiled = true;
/*       */     
/* 10421 */     if (i > 0)
/* 10422 */       this.source = ((SceneGraphObjectRetained)paramArrayList.get(0)).source; 
/* 10423 */     for (byte b = 0; b < i; b++) {
/*       */       
/* 10425 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramArrayList.get(b);
/* 10426 */       ((GeometryArray)geometryArrayRetained.source).retained = this;
/* 10427 */       this.compileVcount[b] = geometryArrayRetained.getValidVertexCount();
/* 10428 */       k += geometryArrayRetained.getValidVertexCount();
/* 10429 */       this.geoOffset[b] = j;
/* 10430 */       j += geometryArrayRetained.stride() * this.compileVcount[b];
/* 10431 */       m = geometryArrayRetained.getVertexFormat();
/*       */     } 
/* 10433 */     createGeometryArrayData(k, m);
/*       */ 
/*       */     
/* 10436 */     this.validVertexCount = k;
/* 10437 */     this.initialVertexIndex = 0;
/*       */     
/* 10439 */     mergeGeometryArrays(paramArrayList);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void mergeGeometryArrays(ArrayList paramArrayList) {
/* 10463 */     int i = 0;
/*       */ 
/*       */     
/* 10466 */     if ((this.vertexFormat & 0x460) != 0) {
/* 10467 */       this.texCoordSetCount = 1;
/* 10468 */       this.texCoordSetMap = new int[1];
/* 10469 */       this.texCoordSetMap[0] = 1;
/*       */     } 
/* 10471 */     for (byte b = 0; b < paramArrayList.size(); b++) {
/* 10472 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramArrayList.get(b);
/*       */       
/* 10474 */       float[] arrayOfFloat = geometryArrayRetained.vertexData;
/* 10475 */       int j = geometryArrayRetained.validVertexCount * this.stride;
/* 10476 */       int k = geometryArrayRetained.initialVertexIndex * this.stride;
/* 10477 */       System.arraycopy(arrayOfFloat, k, this.vertexData, i, j);
/*       */       
/* 10479 */       i += j;
/*       */ 
/*       */       
/* 10482 */       this.geoBounds.combine(geometryArrayRetained.geoBounds);
/*       */     } 
/*       */     
/* 10485 */     this.centroid.set(this.geoBounds.getCenter());
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   boolean isMergeable() {
/* 10491 */     if ((this.vertexFormat & 0x80) != 0) {
/* 10492 */       return false;
/*       */     }
/* 10494 */     if (!isStatic()) {
/* 10495 */       return false;
/*       */     }
/*       */ 
/*       */     
/* 10499 */     if ((this.vertexFormat & 0x460) != 0 && (this.texCoordSetCount > 1 || (this.texCoordSetMap != null && this.texCoordSetMap.length > 1)))
/*       */     {
/*       */       
/* 10502 */       return false;
/*       */     }
/*       */ 
/*       */     
/* 10506 */     if ((this.vertexFormat & 0x1000) != 0) {
/* 10507 */       return false;
/*       */     }
/*       */ 
/*       */     
/* 10511 */     if (this.source.getCapability(18)) {
/* 10512 */       return false;
/*       */     }
/* 10514 */     return true;
/*       */   }
/*       */   
/*       */   void compile(CompileState paramCompileState) {
/* 10518 */     super.compile(paramCompileState);
/*       */     
/* 10520 */     if ((this.vertexFormat & 0x2) != 0) {
/* 10521 */       paramCompileState.needNormalsTransform = true;
/*       */     }
/*       */   }
/*       */   
/*       */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 10526 */     if (this.geoBounds != null) {
/* 10527 */       this.geoBounds.transform(paramTransformGroupRetained.transform);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void addMorphUser(MorphRetained paramMorphRetained) {
/* 10536 */     if (this.morphUniverseList == null) {
/* 10537 */       this.morphUniverseList = new ArrayList(1);
/* 10538 */       this.morphUserLists = new ArrayList(1);
/*       */     } 
/* 10540 */     synchronized (this.morphUniverseList) {
/* 10541 */       if (this.morphUniverseList.contains(paramMorphRetained.universe)) {
/* 10542 */         int i = this.morphUniverseList.indexOf(paramMorphRetained.universe);
/* 10543 */         ArrayList arrayList = (ArrayList)this.morphUserLists.get(i);
/* 10544 */         arrayList.add(paramMorphRetained);
/*       */       } else {
/* 10546 */         this.morphUniverseList.add(paramMorphRetained.universe);
/* 10547 */         ArrayList arrayList = new ArrayList(5);
/* 10548 */         arrayList.add(paramMorphRetained);
/* 10549 */         this.morphUserLists.add(arrayList);
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void removeMorphUser(MorphRetained paramMorphRetained) {
/* 10559 */     if (this.morphUniverseList == null) {
/*       */       return;
/*       */     }
/* 10562 */     synchronized (this.morphUniverseList) {
/* 10563 */       int i = this.morphUniverseList.indexOf(paramMorphRetained.universe);
/* 10564 */       ArrayList arrayList = (ArrayList)this.morphUserLists.get(i);
/* 10565 */       arrayList.remove(arrayList.indexOf(paramMorphRetained));
/* 10566 */       if (arrayList.size() == 0) {
/* 10567 */         this.morphUserLists.remove(i);
/* 10568 */         this.morphUniverseList.remove(i);
/*       */       } 
/*       */     } 
/*       */   }
/*       */   
/*       */   void initMirrorGeometry() {
/* 10574 */     this.geomLock.getLock();
/* 10575 */     if (this instanceof IndexedGeometryArrayRetained) {
/* 10576 */       if ((this.vertexFormat & 0x200) == 0) {
/* 10577 */         this.mirrorGeometry = ((IndexedGeometryArrayRetained)this).cloneNonIndexedGeometry();
/*       */       }
/*       */       else {
/*       */         
/* 10581 */         this.mirrorGeometry = null;
/*       */       } 
/*       */     }
/* 10584 */     this.geomLock.unLock();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   void updateMirrorGeometry() {
/* 10590 */     this.geomLock.getLock();
/* 10591 */     if (this instanceof IndexedGeometryArrayRetained && 
/* 10592 */       this.mirrorGeometry != null) {
/* 10593 */       this.mirrorGeometry = ((IndexedGeometryArrayRetained)this).cloneNonIndexedGeometry();
/*       */     }
/*       */ 
/*       */     
/* 10597 */     this.geomLock.unLock();
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void getVertexData(int paramInt, Point3d paramPoint3d) {
/* 10605 */     if ((this.vertexFormat & 0x80) == 0) {
/* 10606 */       int i = this.stride * paramInt + this.coordinateOffset;
/* 10607 */       paramPoint3d.x = this.vertexData[i];
/* 10608 */       paramPoint3d.y = this.vertexData[i + 1];
/* 10609 */       paramPoint3d.z = this.vertexData[i + 2];
/*       */       
/*       */       return;
/*       */     } 
/* 10613 */     if ((this.vertexFormat & 0x800) == 0) {
/* 10614 */       if ((this.vertexFormat & 0x100) != 0) {
/* 10615 */         int i = this.stride * paramInt + this.coordinateOffset;
/* 10616 */         paramPoint3d.x = this.interLeavedVertexData[i];
/* 10617 */         paramPoint3d.y = this.interLeavedVertexData[i + 1];
/* 10618 */         paramPoint3d.z = this.interLeavedVertexData[i + 2];
/*       */       } else {
/*       */         int i;
/* 10621 */         switch (this.vertexType & 0xF) {
/*       */           case 1:
/* 10623 */             i = paramInt * 3;
/* 10624 */             paramPoint3d.x = this.floatRefCoords[i];
/* 10625 */             paramPoint3d.y = this.floatRefCoords[i + 1];
/* 10626 */             paramPoint3d.z = this.floatRefCoords[i + 2];
/*       */             break;
/*       */           case 2:
/* 10629 */             i = paramInt * 3;
/* 10630 */             paramPoint3d.x = this.doubleRefCoords[i];
/* 10631 */             paramPoint3d.y = this.doubleRefCoords[i + 1];
/* 10632 */             paramPoint3d.z = this.doubleRefCoords[i + 2];
/*       */             break;
/*       */           case 4:
/* 10635 */             paramPoint3d.x = (this.p3fRefCoords[paramInt]).x;
/* 10636 */             paramPoint3d.y = (this.p3fRefCoords[paramInt]).y;
/* 10637 */             paramPoint3d.z = (this.p3fRefCoords[paramInt]).z;
/*       */             break;
/*       */           case 8:
/* 10640 */             paramPoint3d.x = (this.p3dRefCoords[paramInt]).x;
/* 10641 */             paramPoint3d.y = (this.p3dRefCoords[paramInt]).y;
/* 10642 */             paramPoint3d.z = (this.p3dRefCoords[paramInt]).z;
/*       */             break;
/*       */         } 
/*       */ 
/*       */       
/*       */       } 
/* 10648 */     } else if ((this.vertexFormat & 0x100) != 0) {
/* 10649 */       int i = this.stride * paramInt + this.coordinateOffset;
/* 10650 */       paramPoint3d.x = this.interleavedFloatBufferImpl.get(i);
/* 10651 */       paramPoint3d.y = this.interleavedFloatBufferImpl.get(i + 1);
/* 10652 */       paramPoint3d.z = this.interleavedFloatBufferImpl.get(i + 2);
/*       */     } else {
/*       */       int i;
/* 10655 */       switch (this.vertexType & 0xF) {
/*       */         case 1:
/* 10657 */           i = paramInt * 3;
/* 10658 */           paramPoint3d.x = this.floatBufferRefCoords.get(i);
/* 10659 */           paramPoint3d.y = this.floatBufferRefCoords.get(i + 1);
/* 10660 */           paramPoint3d.z = this.floatBufferRefCoords.get(i + 2);
/*       */           break;
/*       */         case 2:
/* 10663 */           i = paramInt * 3;
/* 10664 */           paramPoint3d.x = this.doubleBufferRefCoords.get(i);
/* 10665 */           paramPoint3d.y = this.doubleBufferRefCoords.get(i + 1);
/* 10666 */           paramPoint3d.z = this.doubleBufferRefCoords.get(i + 2);
/*       */           break;
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */   
/*       */   void getCrossValue(Point3d paramPoint3d1, Point3d paramPoint3d2, Vector3d paramVector3d) {
/* 10674 */     paramVector3d.x += paramPoint3d1.y * paramPoint3d2.z - paramPoint3d1.z * paramPoint3d2.y;
/* 10675 */     paramVector3d.y += paramPoint3d2.x * paramPoint3d1.z - paramPoint3d2.z * paramPoint3d1.x;
/* 10676 */     paramVector3d.z += paramPoint3d1.x * paramPoint3d2.y - paramPoint3d1.y * paramPoint3d2.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   boolean intersect(Transform3D paramTransform3D1, Transform3D paramTransform3D2, GeometryRetained paramGeometryRetained) {
/* 10683 */     Transform3D transform3D = new Transform3D();
/* 10684 */     boolean bool = false;
/*       */     
/* 10686 */     if (paramGeometryRetained instanceof GeometryArrayRetained) {
/* 10687 */       GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramGeometryRetained;
/*       */       
/* 10689 */       if (geometryArrayRetained.validVertexCount >= this.validVertexCount) {
/* 10690 */         transform3D.invert(paramTransform3D2);
/* 10691 */         transform3D.mul(paramTransform3D1);
/* 10692 */         bool = intersect(transform3D, paramGeometryRetained);
/*       */       } else {
/* 10694 */         transform3D.invert(paramTransform3D1);
/* 10695 */         transform3D.mul(paramTransform3D2);
/* 10696 */         bool = geometryArrayRetained.intersect(transform3D, this);
/*       */       } 
/*       */     } else {
/* 10699 */       transform3D.invert(paramTransform3D1);
/* 10700 */       transform3D.mul(paramTransform3D2);
/* 10701 */       bool = paramGeometryRetained.intersect(transform3D, this);
/*       */     } 
/* 10703 */     return bool;
/*       */   }
/*       */   
/*       */   int getNumCoordCount() {
/* 10707 */     null = 0;
/* 10708 */     if ((this.vertexFormat & true) != 0) {
/* 10709 */       if ((this.vertexFormat & 0x80) == 0) {
/* 10710 */         return this.vertexCount;
/*       */       }
/*       */ 
/*       */       
/* 10714 */       if ((this.vertexFormat & 0x800) == 0) {
/* 10715 */         if ((this.vertexFormat & 0x100) == 0) {
/* 10716 */           switch (this.vertexType & 0xF) {
/*       */             case 1:
/* 10718 */               null = this.floatRefCoords.length / 3;
/*       */               break;
/*       */             case 2:
/* 10721 */               null = this.doubleRefCoords.length / 3;
/*       */               break;
/*       */             case 4:
/* 10724 */               null = this.p3fRefCoords.length;
/*       */               break;
/*       */             case 8:
/* 10727 */               null = this.p3dRefCoords.length;
/*       */               break;
/*       */           } 
/*       */         
/*       */         } else {
/* 10732 */           null = this.interLeavedVertexData.length / this.stride;
/*       */         }
/*       */       
/*       */       }
/* 10736 */       else if ((this.vertexFormat & 0x100) == 0) {
/* 10737 */         switch (this.vertexType & 0xF) {
/*       */           case 1:
/* 10739 */             null = this.floatBufferRefCoords.limit() / 3;
/*       */             break;
/*       */           case 2:
/* 10742 */             null = this.doubleBufferRefCoords.limit() / 3;
/*       */             break;
/*       */         } 
/*       */       
/*       */       } else {
/* 10747 */         null = this.interleavedFloatBufferImpl.limit() / this.stride;
/*       */       } 
/*       */     } 
/*       */     
/* 10751 */     return null;
/*       */   }
/*       */   
/*       */   int getNumColorCount() {
/* 10755 */     null = 0;
/* 10756 */     if ((this.vertexFormat & 0x4) != 0) {
/* 10757 */       if ((this.vertexFormat & 0x80) == 0) {
/* 10758 */         return this.vertexCount;
/*       */       }
/*       */       
/* 10761 */       if ((this.vertexFormat & 0x800) == 0) {
/* 10762 */         if ((this.vertexFormat & 0x100) == 0) {
/* 10763 */           switch (this.vertexType & 0x3F0) {
/*       */             case 16:
/* 10765 */               if ((this.vertexFormat & 0xC) == 4) {
/* 10766 */                 null = this.floatRefColors.length / 3; break;
/*       */               } 
/* 10768 */               if ((this.vertexFormat & 0xC) == 12) {
/* 10769 */                 null = this.floatRefColors.length / 4;
/*       */               }
/*       */               break;
/*       */             case 32:
/* 10773 */               if ((this.vertexFormat & 0xC) == 4) {
/* 10774 */                 null = this.byteRefColors.length / 3; break;
/*       */               } 
/* 10776 */               if ((this.vertexFormat & 0xC) == 12) {
/* 10777 */                 null = this.byteRefColors.length / 4;
/*       */               }
/*       */               break;
/*       */             case 64:
/* 10781 */               null = this.c3fRefColors.length;
/*       */               break;
/*       */             case 128:
/* 10784 */               null = this.c4fRefColors.length;
/*       */               break;
/*       */             case 256:
/* 10787 */               null = this.c3bRefColors.length;
/*       */               break;
/*       */             case 512:
/* 10790 */               null = this.c4bRefColors.length;
/*       */               break;
/*       */           } 
/*       */         
/*       */         } else {
/* 10795 */           null = this.interLeavedVertexData.length / this.stride;
/*       */         }
/*       */       
/*       */       }
/* 10799 */       else if ((this.vertexFormat & 0x100) == 0) {
/* 10800 */         switch (this.vertexType & 0x3F0) {
/*       */           case 16:
/* 10802 */             if ((this.vertexFormat & 0xC) == 4) {
/* 10803 */               null = this.floatBufferRefColors.limit() / 3; break;
/*       */             } 
/* 10805 */             if ((this.vertexFormat & 0xC) == 12) {
/* 10806 */               null = this.floatBufferRefColors.limit() / 4;
/*       */             }
/*       */             break;
/*       */           case 32:
/* 10810 */             if ((this.vertexFormat & 0xC) == 4) {
/* 10811 */               null = this.byteBufferRefColors.limit() / 3; break;
/*       */             } 
/* 10813 */             if ((this.vertexFormat & 0xC) == 12) {
/* 10814 */               null = this.byteBufferRefColors.limit() / 4;
/*       */             }
/*       */             break;
/*       */         } 
/*       */       
/*       */       } else {
/* 10820 */         null = this.interleavedFloatBufferImpl.limit() / this.stride;
/*       */       } 
/*       */     } 
/*       */     
/* 10824 */     return null;
/*       */   }
/*       */   
/*       */   int getNumNormalCount() {
/* 10828 */     null = 0;
/* 10829 */     if ((this.vertexFormat & 0x2) != 0) {
/* 10830 */       if ((this.vertexFormat & 0x80) == 0) {
/* 10831 */         return this.vertexCount;
/*       */       }
/*       */ 
/*       */       
/* 10835 */       if ((this.vertexFormat & 0x800) == 0) {
/* 10836 */         if ((this.vertexFormat & 0x100) == 0) {
/* 10837 */           switch (this.vertexType & 0xC00) {
/*       */             case 1024:
/* 10839 */               null = this.floatRefNormals.length / 3;
/*       */               break;
/*       */             case 2048:
/* 10842 */               null = this.v3fRefNormals.length;
/*       */               break;
/*       */           } 
/*       */         
/*       */         } else {
/* 10847 */           null = this.interLeavedVertexData.length / this.stride;
/*       */         }
/*       */       
/*       */       }
/* 10851 */       else if ((this.vertexFormat & 0x100) == 0) {
/* 10852 */         if ((this.vertexType & 0xC00) == 1024) {
/* 10853 */           null = this.floatBufferRefNormals.limit() / 3;
/*       */         }
/*       */       } else {
/*       */         
/* 10857 */         null = this.interleavedFloatBufferImpl.limit() / this.stride;
/*       */       } 
/*       */     } 
/*       */     
/* 10861 */     return null;
/*       */   }
/*       */   
/*       */   int getNumTexCoordCount(int paramInt) {
/* 10865 */     null = 0;
/* 10866 */     if ((this.vertexFormat & 0x460) != 0) {
/* 10867 */       if ((this.vertexFormat & 0x80) == 0) {
/* 10868 */         return this.vertexCount;
/*       */       }
/*       */ 
/*       */       
/* 10872 */       if ((this.vertexFormat & 0x800) == 0) {
/* 10873 */         if ((this.vertexFormat & 0x100) == 0) {
/* 10874 */           switch (this.vertexType & 0x7000) {
/*       */             case 4096:
/* 10876 */               if ((this.vertexFormat & 0x20) != 0) {
/* 10877 */                 null = (float[])this.refTexCoords[paramInt].length / 2; break;
/* 10878 */               }  if ((this.vertexFormat & 0x40) != 0) {
/* 10879 */                 null = (float[])this.refTexCoords[paramInt].length / 3; break;
/* 10880 */               }  if ((this.vertexFormat & 0x400) != 0) {
/* 10881 */                 null = (float[])this.refTexCoords[paramInt].length / 4;
/*       */               }
/*       */               break;
/*       */             
/*       */             case 8192:
/* 10886 */               null = (TexCoord2f[])this.refTexCoords[paramInt].length;
/*       */               break;
/*       */             case 16384:
/* 10889 */               null = (TexCoord3f[])this.refTexCoords[paramInt].length;
/*       */               break;
/*       */           } 
/*       */         } else {
/* 10893 */           null = this.interLeavedVertexData.length / this.stride;
/*       */         }
/*       */       
/*       */       }
/* 10897 */       else if ((this.vertexFormat & 0x100) == 0) {
/* 10898 */         if ((this.vertexType & 0x7000) == 4096) {
/* 10899 */           FloatBufferWrapper floatBufferWrapper = (FloatBufferWrapper)((J3DBuffer)this.refTexCoordsBuffer[paramInt]).getBufferImpl();
/* 10900 */           if ((this.vertexFormat & 0x20) != 0) {
/* 10901 */             null = floatBufferWrapper.limit() / 2;
/* 10902 */           } else if ((this.vertexFormat & 0x40) != 0) {
/* 10903 */             null = floatBufferWrapper.limit() / 3;
/* 10904 */           } else if ((this.vertexFormat & 0x400) != 0) {
/* 10905 */             null = floatBufferWrapper.limit() / 4;
/*       */           } 
/*       */         } 
/*       */       } else {
/*       */         
/* 10910 */         null = this.interleavedFloatBufferImpl.limit() / this.stride;
/*       */       } 
/*       */     } 
/*       */     
/* 10914 */     return null;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   void computeMinDistance(Point3d[] paramArrayOfPoint3d, Point3d paramPoint3d1, Vector3d paramVector3d, double[] paramArrayOfDouble, Point3d paramPoint3d2) {
/* 10929 */     if (paramArrayOfPoint3d.length == 1) {
/*       */       
/* 10931 */       paramPoint3d2.x = (paramArrayOfPoint3d[0]).x;
/* 10932 */       paramPoint3d2.y = (paramArrayOfPoint3d[0]).y;
/* 10933 */       paramPoint3d2.z = (paramArrayOfPoint3d[0]).z;
/* 10934 */       double d3 = paramPoint3d2.x - paramPoint3d1.x;
/* 10935 */       double d4 = paramPoint3d2.y - paramPoint3d1.y;
/* 10936 */       double d5 = paramPoint3d2.z - paramPoint3d1.z;
/* 10937 */       paramArrayOfDouble[0] = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/* 10942 */     if (paramArrayOfPoint3d.length == 2) {
/*       */       
/* 10944 */       paramArrayOfDouble[0] = Math.sqrt(Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramPoint3d2, null));
/*       */ 
/*       */       
/*       */       return;
/*       */     } 
/*       */ 
/*       */     
/* 10951 */     double d1 = 0.0D;
/*       */     
/* 10953 */     if (paramVector3d == null) {
/* 10954 */       Vector3d vector3d1 = new Vector3d();
/* 10955 */       Vector3d vector3d2 = new Vector3d();
/* 10956 */       paramVector3d = new Vector3d();
/*       */       byte b1;
/* 10958 */       for (b1 = 0; b1 < paramArrayOfPoint3d.length - 1; ) {
/* 10959 */         vector3d1.x = (paramArrayOfPoint3d[b1 + true]).x - (paramArrayOfPoint3d[b1]).x;
/* 10960 */         vector3d1.y = (paramArrayOfPoint3d[b1 + true]).y - (paramArrayOfPoint3d[b1]).y;
/* 10961 */         vector3d1.z = (paramArrayOfPoint3d[b1 + true]).z - (paramArrayOfPoint3d[b1++]).z;
/* 10962 */         if (vector3d1.length() > 0.0D)
/*       */           break; 
/*       */       } 
/*       */       byte b2;
/* 10966 */       for (b2 = b1; b2 < paramArrayOfPoint3d.length - 1; b2++) {
/* 10967 */         vector3d2.x = (paramArrayOfPoint3d[b2 + 1]).x - (paramArrayOfPoint3d[b2]).x;
/* 10968 */         vector3d2.y = (paramArrayOfPoint3d[b2 + 1]).y - (paramArrayOfPoint3d[b2]).y;
/* 10969 */         vector3d2.z = (paramArrayOfPoint3d[b2 + 1]).z - (paramArrayOfPoint3d[b2]).z;
/* 10970 */         if (vector3d2.length() > 0.0D) {
/*       */           break;
/*       */         }
/*       */       } 
/* 10974 */       if (b2 == paramArrayOfPoint3d.length - 1) {
/*       */         
/* 10976 */         paramVector3d = null;
/*       */       } else {
/* 10978 */         paramVector3d.cross(vector3d1, vector3d2);
/*       */       } 
/*       */     } 
/*       */     
/* 10982 */     if (paramVector3d != null) {
/* 10983 */       d1 = paramVector3d.length();
/* 10984 */       if (d1 == 0.0D)
/*       */       {
/* 10986 */         paramVector3d = null;
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/* 10991 */     if (paramArrayOfPoint3d.length == 3) {
/*       */       
/* 10993 */       if (paramVector3d != null) {
/* 10994 */         double d3 = -(paramVector3d.x * (paramArrayOfPoint3d[0]).x + paramVector3d.y * (paramArrayOfPoint3d[0]).y + paramVector3d.z * (paramArrayOfPoint3d[0]).z);
/*       */ 
/*       */         
/* 10997 */         paramArrayOfDouble[0] = (paramVector3d.x * paramPoint3d1.x + paramVector3d.y * paramPoint3d1.y + paramVector3d.z * paramPoint3d1.z + d3) / d1;
/*       */ 
/*       */         
/* 11000 */         paramPoint3d1.x -= paramArrayOfDouble[0] * paramVector3d.x / d1;
/* 11001 */         paramPoint3d1.y -= paramArrayOfDouble[0] * paramVector3d.y / d1;
/* 11002 */         paramPoint3d1.z -= paramArrayOfDouble[0] * paramVector3d.z / d1;
/*       */         
/* 11004 */         if (pointInTri(paramPoint3d2, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], paramVector3d)) {
/*       */           return;
/*       */         }
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 11012 */       Point3d point3d1 = new Point3d();
/*       */       
/* 11014 */       paramArrayOfDouble[0] = Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramPoint3d2, null);
/*       */       
/* 11016 */       double d = Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], point3d1, null);
/*       */       
/* 11018 */       if (d < paramArrayOfDouble[0]) {
/* 11019 */         paramArrayOfDouble[0] = d;
/* 11020 */         paramPoint3d2.x = point3d1.x;
/* 11021 */         paramPoint3d2.y = point3d1.y;
/* 11022 */         paramPoint3d2.z = point3d1.z;
/*       */       } 
/* 11024 */       d = Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[2], paramArrayOfPoint3d[0], point3d1, null);
/*       */       
/* 11026 */       if (d < paramArrayOfDouble[0]) {
/* 11027 */         paramArrayOfDouble[0] = d;
/* 11028 */         paramPoint3d2.x = point3d1.x;
/* 11029 */         paramPoint3d2.y = point3d1.y;
/* 11030 */         paramPoint3d2.z = point3d1.z;
/*       */       } 
/* 11032 */       paramArrayOfDouble[0] = Math.sqrt(paramArrayOfDouble[0]);
/*       */       
/*       */       return;
/*       */     } 
/*       */     
/* 11037 */     if (paramVector3d != null) {
/* 11038 */       double d = -(paramVector3d.x * (paramArrayOfPoint3d[0]).x + paramVector3d.y * (paramArrayOfPoint3d[0]).y + paramVector3d.z * (paramArrayOfPoint3d[0]).z);
/*       */ 
/*       */       
/* 11041 */       paramArrayOfDouble[0] = (paramVector3d.x * paramPoint3d1.x + paramVector3d.y * paramPoint3d1.y + paramVector3d.z * paramPoint3d1.z + d) / d1;
/*       */ 
/*       */       
/* 11044 */       paramPoint3d1.x -= paramArrayOfDouble[0] * paramVector3d.x / d1;
/* 11045 */       paramPoint3d1.y -= paramArrayOfDouble[0] * paramVector3d.y / d1;
/* 11046 */       paramPoint3d1.z -= paramArrayOfDouble[0] * paramVector3d.z / d1;
/*       */       
/* 11048 */       if (pointInTri(paramPoint3d2, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], paramVector3d) || pointInTri(paramPoint3d2, paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], paramArrayOfPoint3d[3], paramVector3d)) {
/*       */         return;
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11058 */     Point3d point3d = new Point3d();
/*       */     
/* 11060 */     paramArrayOfDouble[0] = Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[0], paramArrayOfPoint3d[1], paramPoint3d2, null);
/*       */     
/* 11062 */     double d2 = Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[1], paramArrayOfPoint3d[2], point3d, null);
/*       */     
/* 11064 */     if (d2 < paramArrayOfDouble[0]) {
/* 11065 */       paramArrayOfDouble[0] = d2;
/* 11066 */       paramPoint3d2.x = point3d.x;
/* 11067 */       paramPoint3d2.y = point3d.y;
/* 11068 */       paramPoint3d2.z = point3d.z;
/*       */     } 
/* 11070 */     d2 = Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[2], paramArrayOfPoint3d[3], point3d, null);
/*       */     
/* 11072 */     if (d2 < paramArrayOfDouble[0]) {
/* 11073 */       paramArrayOfDouble[0] = d2;
/* 11074 */       paramPoint3d2.x = point3d.x;
/* 11075 */       paramPoint3d2.y = point3d.y;
/* 11076 */       paramPoint3d2.z = point3d.z;
/*       */     } 
/*       */     
/* 11079 */     d2 = Distance.pointToSegment(paramPoint3d1, paramArrayOfPoint3d[3], paramArrayOfPoint3d[0], point3d, null);
/*       */     
/* 11081 */     if (d2 < paramArrayOfDouble[0]) {
/* 11082 */       paramArrayOfDouble[0] = d2;
/* 11083 */       paramPoint3d2.x = point3d.x;
/* 11084 */       paramPoint3d2.y = point3d.y;
/* 11085 */       paramPoint3d2.z = point3d.z;
/*       */     } 
/*       */     
/* 11088 */     paramArrayOfDouble[0] = Math.sqrt(paramArrayOfDouble[0]);
/*       */   }
/*       */   
/*       */   void handleFrequencyChange(int paramInt) {
/* 11092 */     byte b = 0;
/* 11093 */     if ((this.vertexFormat & 0x80) == 0) {
/* 11094 */       if (paramInt == 1 || ((this.vertexFormat & 0x4) != 0 && paramInt == 3) || ((this.vertexFormat & 0x2) != 0 && paramInt == 5) || ((this.vertexFormat & 0x460) != 0 && paramInt == 7) || ((this.vertexFormat & 0x1000) != 0 && paramInt == 23) || paramInt == 20)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11104 */         b = 1;
/*       */       
/*       */       }
/*       */     }
/* 11108 */     else if (paramInt == 19) {
/* 11109 */       b = 1;
/*       */     } 
/* 11111 */     if (b) {
/* 11112 */       setFrequencyChangeMask(paramInt, b);
/*       */     }
/*       */   }
/*       */ 
/*       */   
/* 11117 */   int getTexCoordType() { return this.texCoordType; }
/*       */ 
/*       */ 
/*       */   
/* 11121 */   int getVertexAttrType() { return this.vertexAttrType; }
/*       */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\GeometryArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */