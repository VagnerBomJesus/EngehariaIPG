/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class IndexedGeometryStripArrayRetained
/*     */   extends IndexedGeometryArrayRetained
/*     */ {
/*     */   int[] stripIndexCounts;
/*     */   int[] compileStripICOffset;
/*     */   int[] compileIndexLength;
/*     */   
/*     */   void setStripIndexCounts(int[] paramArrayOfInt) {
/*  36 */     int i = paramArrayOfInt.length, j = 0;
/*     */     byte b;
/*  38 */     for (b = 0; b < i; b++) {
/*  39 */       j += paramArrayOfInt[b];
/*  40 */       if (this instanceof IndexedLineStripArrayRetained) {
/*  41 */         if (paramArrayOfInt[b] < 2) {
/*  42 */           throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArrayRetained1"));
/*     */         }
/*     */       }
/*  45 */       else if (this instanceof IndexedTriangleStripArrayRetained) {
/*  46 */         if (paramArrayOfInt[b] < 3) {
/*  47 */           throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArrayRetained1"));
/*     */         }
/*     */       }
/*  50 */       else if (this instanceof IndexedTriangleFanArrayRetained && 
/*  51 */         paramArrayOfInt[b] < 3) {
/*  52 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArrayRetained1"));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  58 */     if (this.initialIndexIndex + j > this.indexCount)
/*  59 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryStripArrayRetained0")); 
/*  60 */     int k = 0;
/*  61 */     int m = 0;
/*  62 */     int n = 0;
/*  63 */     int[] arrayOfInt1 = null;
/*  64 */     int[] arrayOfInt2 = null;
/*     */     
/*  66 */     k = computeMaxIndex(this.initialIndexIndex, j, this.indexCoord);
/*  67 */     doErrorCheck(k);
/*  68 */     if ((this.vertexFormat & 0x200) == 0) {
/*  69 */       if ((this.vertexFormat & 0x4) != 0) {
/*  70 */         m = computeMaxIndex(this.initialIndexIndex, j, this.indexColor);
/*  71 */         doColorCheck(m);
/*     */       } 
/*  73 */       if ((this.vertexFormat & 0x460) != 0) {
/*  74 */         arrayOfInt1 = new int[this.texCoordSetCount];
/*  75 */         for (b = 0; b < this.texCoordSetCount; b++) {
/*  76 */           arrayOfInt1[b] = computeMaxIndex(this.initialIndexIndex, j, this.indexTexCoord[b]);
/*     */           
/*  78 */           doTexCoordCheck(arrayOfInt1[b], b);
/*     */         } 
/*     */       } 
/*  81 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  82 */         arrayOfInt2 = new int[this.vertexAttrCount];
/*  83 */         for (b = 0; b < this.vertexAttrCount; b++) {
/*  84 */           arrayOfInt2[b] = computeMaxIndex(this.initialIndexIndex, j, this.indexVertexAttr[b]);
/*     */ 
/*     */           
/*  87 */           doTexCoordCheck(arrayOfInt2[b], b);
/*     */         } 
/*     */       } 
/*  90 */       if ((this.vertexFormat & 0x2) != 0) {
/*  91 */         n = computeMaxIndex(this.initialIndexIndex, j, this.indexNormal);
/*  92 */         doNormalCheck(n);
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     this.geomLock.getLock();
/*  97 */     this.validIndexCount = j;
/*  98 */     this.stripIndexCounts = new int[i];
/*  99 */     for (b = 0; b < i; b++)
/*     */     {
/* 101 */       this.stripIndexCounts[b] = paramArrayOfInt[b];
/*     */     }
/* 103 */     this.maxCoordIndex = k;
/* 104 */     if ((this.vertexFormat & 0x200) == 0) {
/* 105 */       this.maxColorIndex = m;
/* 106 */       if ((this.vertexFormat & 0x460) != 0) {
/* 107 */         for (b = 0; b < this.texCoordSetCount; b++) {
/* 108 */           this.maxTexCoordIndices[b] = arrayOfInt1[b];
/*     */         }
/*     */       }
/* 111 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 112 */         for (b = 0; b < this.vertexAttrCount; b++) {
/* 113 */           this.maxVertexAttrIndices[b] = arrayOfInt2[b];
/*     */         }
/*     */       }
/* 116 */       this.maxNormalIndex = n;
/*     */     } else {
/*     */       
/* 119 */       this.maxColorIndex = this.maxCoordIndex;
/* 120 */       this.maxNormalIndex = this.maxCoordIndex;
/* 121 */       if ((this.vertexFormat & 0x460) != 0) {
/* 122 */         for (b = 0; b < this.texCoordSetCount; b++) {
/* 123 */           this.maxTexCoordIndices[b] = this.maxCoordIndex;
/*     */         }
/*     */       }
/* 126 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 127 */         for (b = 0; b < this.vertexAttrCount; b++) {
/* 128 */           this.maxVertexAttrIndices[b] = this.maxCoordIndex;
/*     */         }
/*     */       }
/*     */     } 
/* 132 */     this.geomLock.unLock();
/*     */ 
/*     */     
/* 135 */     if (!this.inUpdater && this.source != null && this.source.isLive())
/* 136 */       sendDataChangedMessage(true); 
/*     */   }
/*     */   
/*     */   GeometryArrayRetained cloneNonIndexedGeometry() {
/*     */     TriangleStripArrayRetained triangleStripArrayRetained;
/*     */     TriangleFanArrayRetained triangleFanArrayRetained;
/* 142 */     LineStripArrayRetained lineStripArrayRetained = null;
/*     */     
/* 144 */     switch (this.geoType) {
/*     */       case 14:
/* 146 */         lineStripArrayRetained = new LineStripArrayRetained();
/*     */         break;
/*     */       case 13:
/* 149 */         triangleFanArrayRetained = new TriangleFanArrayRetained();
/*     */         break;
/*     */       case 12:
/* 152 */         triangleStripArrayRetained = new TriangleStripArrayRetained();
/*     */         break;
/*     */     } 
/* 155 */     triangleStripArrayRetained.createGeometryArrayData(this.validIndexCount, this.vertexFormat & 0xFFFFF67F, this.texCoordSetCount, this.texCoordSetMap, this.vertexAttrCount, this.vertexAttrSizes);
/*     */ 
/*     */ 
/*     */     
/* 159 */     triangleStripArrayRetained.unIndexify(this);
/* 160 */     triangleStripArrayRetained.setStripVertexCounts(this.stripIndexCounts);
/*     */     
/* 162 */     return triangleStripArrayRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   int getNumStrips() { return this.stripIndexCounts.length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getStripIndexCounts(int[] paramArrayOfInt) {
/* 179 */     for (int i = paramArrayOfInt.length - 1; i >= 0; i--) {
/* 180 */       paramArrayOfInt[i] = this.stripIndexCounts[i];
/*     */     }
/*     */   }
/*     */   
/*     */   void mergeGeometryArrays(ArrayList paramArrayList) {
/* 185 */     int i = paramArrayList.size();
/* 186 */     int j = 0;
/*     */     
/*     */     byte b;
/* 189 */     for (b = 0; b < i; b++) {
/* 190 */       IndexedGeometryStripArrayRetained indexedGeometryStripArrayRetained = (IndexedGeometryStripArrayRetained)paramArrayList.get(b);
/* 191 */       j += indexedGeometryStripArrayRetained.stripIndexCounts.length;
/*     */     } 
/*     */     
/* 194 */     this.stripIndexCounts = new int[j];
/* 195 */     this.compileIndexLength = new int[j];
/* 196 */     this.compileStripICOffset = new int[i];
/* 197 */     int k = 0;
/* 198 */     for (b = 0; b < i; b++) {
/* 199 */       IndexedGeometryStripArrayRetained indexedGeometryStripArrayRetained = (IndexedGeometryStripArrayRetained)paramArrayList.get(b);
/* 200 */       this.compileStripICOffset[b] = k;
/* 201 */       this.compileIndexLength[b] = indexedGeometryStripArrayRetained.stripIndexCounts.length;
/* 202 */       System.arraycopy(indexedGeometryStripArrayRetained.stripIndexCounts, 0, this.stripIndexCounts, k, indexedGeometryStripArrayRetained.stripIndexCounts.length);
/*     */       
/* 204 */       k += indexedGeometryStripArrayRetained.stripIndexCounts.length;
/*     */     } 
/* 206 */     super.mergeGeometryArrays(paramArrayList);
/*     */   }
/*     */ 
/*     */   
/* 210 */   int getNumStrips(int paramInt) { return this.compileIndexLength[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getStripIndexCounts(int paramInt, int[] paramArrayOfInt) {
/* 218 */     int i = this.compileIndexLength[paramInt];
/* 219 */     int j = this.compileStripICOffset[paramInt];
/* 220 */     for (byte b = 0; b < i; b++)
/* 221 */       paramArrayOfInt[b] = this.stripIndexCounts[j + 1]; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\IndexedGeometryStripArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */