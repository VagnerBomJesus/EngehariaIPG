/*      */ package javax.media.j3d;
/*      */ 
/*      */ import com.sun.j3d.internal.FloatBufferWrapper;
/*      */ import java.util.ArrayList;
/*      */ import javax.vecmath.TexCoord2f;
/*      */ import javax.vecmath.TexCoord3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class IndexedGeometryArrayRetained
/*      */   extends GeometryArrayRetained
/*      */ {
/*      */   int[] indexCoord;
/*      */   int[] indexColor;
/*      */   int[] indexNormal;
/*      */   int[][] indexTexCoord;
/*      */   int[][] indexVertexAttr;
/*   35 */   int indexCount = 0;
/*      */   
/*   37 */   int initialIndexIndex = 0;
/*   38 */   int validIndexCount = 0;
/*      */   
/*      */   int[] compileIndexCount;
/*      */   
/*      */   int[] compileIndexOffset;
/*      */   
/*   44 */   int maxCoordIndex = 0;
/*   45 */   int maxColorIndex = 0;
/*   46 */   int maxNormalIndex = 0;
/*   47 */   int[] maxTexCoordIndices = null;
/*   48 */   int[] maxVertexAttrIndices = null;
/*      */   
/*      */   void createIndexedGeometryArrayData(int paramInt) {
/*   51 */     this.indexCount = paramInt;
/*   52 */     this.validIndexCount = paramInt;
/*      */ 
/*      */ 
/*      */     
/*   56 */     boolean bool = ((this.vertexFormat & 0x200) == 0) ? 1 : 0;
/*      */ 
/*      */ 
/*      */     
/*   60 */     if ((this.vertexFormat & true) != 0 && (this.vertexFormat & 0x2000) == 0)
/*      */     {
/*   62 */       this.indexCoord = new int[paramInt];
/*      */     }
/*   64 */     if ((this.vertexFormat & 0x2) != 0 && bool) {
/*   65 */       this.indexNormal = new int[paramInt];
/*      */     }
/*   67 */     if ((this.vertexFormat & 0x4) != 0 && bool) {
/*   68 */       this.indexColor = new int[paramInt];
/*      */     }
/*   70 */     if ((this.vertexFormat & 0x460) != 0) {
/*   71 */       this.indexTexCoord = new int[this.texCoordSetCount][];
/*   72 */       if (bool) {
/*   73 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/*   74 */           this.indexTexCoord[b] = new int[paramInt];
/*      */         }
/*      */       }
/*   77 */       this.maxTexCoordIndices = new int[this.texCoordSetCount];
/*      */     } 
/*      */     
/*   80 */     if ((this.vertexFormat & 0x1000) != 0) {
/*   81 */       this.indexVertexAttr = new int[this.vertexAttrCount][];
/*   82 */       if (bool) {
/*   83 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/*   84 */           this.indexVertexAttr[b] = new int[paramInt];
/*      */         }
/*      */       }
/*   87 */       this.maxVertexAttrIndices = new int[this.vertexAttrCount];
/*      */     } 
/*      */   } GeometryArrayRetained cloneNonIndexedGeometry() {
/*      */     PointArrayRetained pointArrayRetained;
/*      */     TriangleArrayRetained triangleArrayRetained;
/*      */     QuadArrayRetained quadArrayRetained;
/*   93 */     LineArrayRetained lineArrayRetained = null;
/*      */ 
/*      */     
/*   96 */     switch (this.geoType)
/*      */     { case 11:
/*   98 */         lineArrayRetained = new LineArrayRetained();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  113 */         lineArrayRetained.createGeometryArrayData(this.validIndexCount, this.vertexFormat & 0xFFFFF67F, this.texCoordSetCount, this.texCoordSetMap, this.vertexAttrCount, this.vertexAttrSizes);
/*      */ 
/*      */ 
/*      */         
/*  117 */         lineArrayRetained.cloneSourceArray = this;
/*  118 */         lineArrayRetained.unIndexify(this);
/*      */         
/*  120 */         return lineArrayRetained;case 10: pointArrayRetained = new PointArrayRetained(); pointArrayRetained.createGeometryArrayData(this.validIndexCount, this.vertexFormat & 0xFFFFF67F, this.texCoordSetCount, this.texCoordSetMap, this.vertexAttrCount, this.vertexAttrSizes); pointArrayRetained.cloneSourceArray = this; pointArrayRetained.unIndexify(this); return pointArrayRetained;case 8: quadArrayRetained = new QuadArrayRetained(); quadArrayRetained.createGeometryArrayData(this.validIndexCount, this.vertexFormat & 0xFFFFF67F, this.texCoordSetCount, this.texCoordSetMap, this.vertexAttrCount, this.vertexAttrSizes); quadArrayRetained.cloneSourceArray = this; quadArrayRetained.unIndexify(this); return quadArrayRetained;case 9: triangleArrayRetained = new TriangleArrayRetained(); triangleArrayRetained.createGeometryArrayData(this.validIndexCount, this.vertexFormat & 0xFFFFF67F, this.texCoordSetCount, this.texCoordSetMap, this.vertexAttrCount, this.vertexAttrSizes); triangleArrayRetained.cloneSourceArray = this; triangleArrayRetained.unIndexify(this); return triangleArrayRetained; }  assert false; triangleArrayRetained.createGeometryArrayData(this.validIndexCount, this.vertexFormat & 0xFFFFF67F, this.texCoordSetCount, this.texCoordSetMap, this.vertexAttrCount, this.vertexAttrSizes); triangleArrayRetained.cloneSourceArray = this; triangleArrayRetained.unIndexify(this); return triangleArrayRetained;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   int getIndexCount() { return this.indexCount; }
/*      */ 
/*      */   
/*      */   void doErrorCheck(int paramInt) {
/*  133 */     doCoordCheck(paramInt);
/*  134 */     if ((this.vertexFormat & 0x200) != 0) {
/*  135 */       if ((this.vertexFormat & 0x4) != 0) {
/*  136 */         doColorCheck(paramInt);
/*      */       }
/*  138 */       if ((this.vertexFormat & 0x460) != 0) {
/*  139 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  140 */           doTexCoordCheck(paramInt, b);
/*      */         }
/*      */       }
/*  143 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  144 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  145 */           doVertexAttrCheck(paramInt, b);
/*      */         }
/*      */       }
/*  148 */       if ((this.vertexFormat & 0x2) != 0) {
/*  149 */         doNormalCheck(paramInt);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doCoordCheck(int paramInt) {
/*  156 */     if ((this.vertexFormat & 0x80) == 0) {
/*  157 */       if (paramInt >= this.vertexCount) {
/*  158 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */       
/*      */       }
/*      */     }
/*  162 */     else if ((this.vertexFormat & 0x800) != 0) {
/*  163 */       if ((this.vertexFormat & 0x100) == 0) {
/*  164 */         switch (this.vertexType & 0xF) {
/*      */           case 1:
/*  166 */             if (this.floatBufferRefCoords != null && 3 * paramInt >= this.floatBufferRefCoords.limit()) {
/*  167 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */             }
/*      */             break;
/*      */           case 2:
/*  171 */             if (this.doubleBufferRefCoords != null && 3 * paramInt >= this.doubleBufferRefCoords.limit()) {
/*  172 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*  178 */       } else if (this.interleavedFloatBufferImpl != null && this.stride * paramInt >= this.interleavedFloatBufferImpl.limit()) {
/*  179 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */       }
/*      */     
/*      */     }
/*  183 */     else if ((this.vertexFormat & 0x100) == 0) {
/*  184 */       switch (this.vertexType & 0xF) {
/*      */         case 1:
/*  186 */           if (this.floatRefCoords != null && 3 * paramInt >= this.floatRefCoords.length) {
/*  187 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */           }
/*      */           break;
/*      */         case 2:
/*  191 */           if (this.doubleRefCoords != null && 3 * paramInt >= this.doubleRefCoords.length) {
/*  192 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */           }
/*      */           break;
/*      */         
/*      */         case 4:
/*  197 */           if (this.p3fRefCoords != null && paramInt >= this.p3fRefCoords.length) {
/*  198 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */           }
/*      */           break;
/*      */         case 8:
/*  202 */           if (this.p3dRefCoords != null && paramInt >= this.p3dRefCoords.length) {
/*  203 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  211 */     } else if (this.interLeavedVertexData != null && this.stride * paramInt >= this.interLeavedVertexData.length) {
/*  212 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray23"));
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
/*      */   void doColorCheck(int paramInt) {
/*  224 */     if ((this.vertexFormat & 0x4) == 0) {
/*      */       return;
/*      */     }
/*  227 */     if ((this.vertexFormat & 0x80) == 0) {
/*  228 */       if (paramInt >= this.vertexCount) {
/*  229 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */       }
/*      */     } else {
/*      */       
/*  233 */       int i = getColorStride();
/*      */       
/*  235 */       if ((this.vertexFormat & 0x800) != 0) {
/*  236 */         if ((this.vertexFormat & 0x100) == 0) {
/*  237 */           switch (this.vertexType & 0x3F0) {
/*      */             case 16:
/*  239 */               if (this.floatBufferRefColors != null && i * paramInt >= this.floatBufferRefColors.limit()) {
/*  240 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */               }
/*      */               break;
/*      */             case 32:
/*  244 */               if (this.byteBufferRefColors != null && i * paramInt >= this.byteBufferRefColors.limit()) {
/*  245 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */               }
/*      */               break;
/*      */           } 
/*      */ 
/*      */         
/*  251 */         } else if (this.interleavedFloatBufferImpl != null && this.stride * paramInt >= this.interleavedFloatBufferImpl.limit()) {
/*      */           
/*  253 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */         }
/*      */       
/*      */       }
/*  257 */       else if ((this.vertexFormat & 0x100) == 0) {
/*  258 */         switch (this.vertexType & 0x3F0) {
/*      */           case 16:
/*  260 */             if (this.floatRefColors != null && i * paramInt >= this.floatRefColors.length) {
/*  261 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */             }
/*      */             break;
/*      */           case 32:
/*  265 */             if (this.byteRefColors != null && i * paramInt >= this.byteRefColors.length) {
/*  266 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */             }
/*      */             break;
/*      */           
/*      */           case 64:
/*  271 */             if (this.c3fRefColors != null && paramInt >= this.c3fRefColors.length) {
/*  272 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */             }
/*      */             break;
/*      */           case 128:
/*  276 */             if (this.c4fRefColors != null && paramInt >= this.c4fRefColors.length) {
/*  277 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */             }
/*      */             break;
/*      */           case 256:
/*  281 */             if (this.c3bRefColors != null && paramInt >= this.c3bRefColors.length) {
/*  282 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */             }
/*      */             break;
/*      */           case 512:
/*  286 */             if (this.c4bRefColors != null && paramInt >= this.c4bRefColors.length) {
/*  287 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*  294 */       } else if (this.interLeavedVertexData != null && this.stride * paramInt >= this.interLeavedVertexData.length) {
/*  295 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray24"));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doNormalCheck(int paramInt) {
/*  305 */     if ((this.vertexFormat & 0x2) == 0) {
/*      */       return;
/*      */     }
/*      */     
/*  309 */     if ((this.vertexFormat & 0x80) == 0) {
/*  310 */       if (paramInt >= this.vertexCount) {
/*  311 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*      */       
/*      */       }
/*      */     }
/*  315 */     else if ((this.vertexFormat & 0x800) != 0) {
/*  316 */       if ((this.vertexFormat & 0x100) == 0) {
/*  317 */         switch (this.vertexType & 0xC00) {
/*      */           case 1024:
/*  319 */             if (this.floatBufferRefNormals != null && 3 * paramInt >= this.floatBufferRefNormals.limit()) {
/*  320 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*  326 */       } else if (this.interleavedFloatBufferImpl != null && this.stride * paramInt >= this.interleavedFloatBufferImpl.limit()) {
/*  327 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*      */       }
/*      */     
/*      */     }
/*  331 */     else if ((this.vertexFormat & 0x100) == 0) {
/*  332 */       switch (this.vertexType & 0xC00) {
/*      */         case 1024:
/*  334 */           if (this.floatRefNormals != null && 3 * paramInt >= this.floatRefNormals.length) {
/*  335 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*      */           }
/*      */           break;
/*      */         case 2048:
/*  339 */           if (this.v3fRefNormals != null && paramInt >= this.v3fRefNormals.length) {
/*  340 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  349 */     } else if (this.interLeavedVertexData != null && this.stride * paramInt >= this.interLeavedVertexData.length) {
/*  350 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray26"));
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
/*      */   void doTexCoordCheck(int paramInt1, int paramInt2) {
/*  363 */     if ((this.vertexFormat & 0x460) == 0) {
/*      */       return;
/*      */     }
/*  366 */     if ((this.vertexFormat & 0x80) == 0) {
/*  367 */       if (paramInt1 >= this.vertexCount) {
/*  368 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*      */       }
/*      */     } else {
/*      */       
/*  372 */       int i = getTexStride();
/*      */       
/*  374 */       if ((this.vertexFormat & 0x800) != 0) {
/*  375 */         if ((this.vertexFormat & 0x100) == 0) {
/*  376 */           FloatBufferWrapper floatBufferWrapper; switch (this.vertexType & 0x7000) {
/*      */             
/*      */             case 4096:
/*  379 */               floatBufferWrapper = (FloatBufferWrapper)((J3DBuffer)this.refTexCoordsBuffer[paramInt2]).getBufferImpl();
/*  380 */               if (this.refTexCoords[paramInt2] != null && i * paramInt1 >= floatBufferWrapper.limit()) {
/*  381 */                 throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*      */               }
/*      */               break;
/*      */           } 
/*      */ 
/*      */         
/*  387 */         } else if (this.interleavedFloatBufferImpl != null && this.stride * paramInt1 >= this.interleavedFloatBufferImpl.limit()) {
/*  388 */           throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  393 */       else if ((this.vertexFormat & 0x100) == 0) {
/*  394 */         switch (this.vertexType & 0x7000) {
/*      */           case 4096:
/*  396 */             if (this.refTexCoords[paramInt2] != null && i * paramInt1 >= (float[])this.refTexCoords[paramInt2].length) {
/*  397 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*      */             }
/*      */             break;
/*      */           case 8192:
/*  401 */             if (this.refTexCoords[paramInt2] != null && paramInt1 >= (TexCoord2f[])this.refTexCoords[paramInt2].length) {
/*  402 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*      */             }
/*      */             break;
/*      */           
/*      */           case 16384:
/*  407 */             if (this.refTexCoords[paramInt2] != null && paramInt1 >= (TexCoord3f[])this.refTexCoords[paramInt2].length) {
/*  408 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  416 */       } else if (this.interLeavedVertexData != null && this.stride * paramInt1 >= this.interLeavedVertexData.length) {
/*  417 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray25"));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doVertexAttrCheck(int paramInt1, int paramInt2) {
/*  428 */     if ((this.vertexFormat & 0x1000) == 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  433 */     assert (this.vertexFormat & 0x100) == 0;
/*      */     
/*  435 */     if ((this.vertexFormat & 0x80) == 0) {
/*  436 */       if (paramInt1 >= this.vertexCount) {
/*  437 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray30"));
/*      */       }
/*      */     } else {
/*  440 */       int i = this.vertexAttrSizes[paramInt2];
/*      */       
/*  442 */       if ((this.vertexFormat & 0x800) != 0) {
/*  443 */         switch (this.vertexType & 0x8000) {
/*      */           case 32768:
/*  445 */             if (i * paramInt1 >= this.floatBufferRefVertexAttrs[paramInt2].limit()) {
/*  446 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray30"));
/*      */             }
/*      */             break;
/*      */         } 
/*      */       } else {
/*  451 */         switch (this.vertexType & 0x8000) {
/*      */           case 32768:
/*  453 */             if (i * paramInt1 >= this.floatRefVertexAttrs[paramInt2].length) {
/*  454 */               throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray30"));
/*      */             }
/*      */             break;
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
/*      */   final void setCoordinateIndex(int paramInt1, int paramInt2) {
/*  471 */     int i = doIndexCheck(paramInt1, this.maxCoordIndex, this.indexCoord, paramInt2);
/*  472 */     if (i > this.maxCoordIndex) {
/*  473 */       doErrorCheck(i);
/*      */     }
/*  475 */     if ((this.vertexFormat & 0x200) != 0) {
/*  476 */       if ((this.vertexFormat & 0x4) != 0) {
/*  477 */         this.maxColorIndex = i;
/*      */       }
/*  479 */       if ((this.vertexFormat & 0x460) != 0) {
/*  480 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  481 */           this.maxTexCoordIndices[b] = i;
/*      */         }
/*      */       }
/*  484 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  485 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  486 */           this.maxVertexAttrIndices[b] = i;
/*      */         }
/*      */       }
/*  489 */       if ((this.vertexFormat & 0x2) != 0) {
/*  490 */         this.maxNormalIndex = i;
/*      */       }
/*      */     } 
/*      */     
/*  494 */     this.geomLock.getLock();
/*  495 */     this.dirtyFlag |= 0x20;
/*  496 */     this.indexCoord[paramInt1] = paramInt2;
/*  497 */     this.maxCoordIndex = i;
/*  498 */     this.geomLock.unLock();
/*  499 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  500 */       sendDataChangedMessage(true);
/*      */     }
/*      */   }
/*      */   
/*      */   int doIndexCheck(int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3) {
/*  505 */     int i = paramInt2;
/*  506 */     if (paramInt1 < this.initialIndexIndex) {
/*  507 */       return i;
/*      */     }
/*  509 */     if (paramInt1 >= this.initialIndexIndex + this.validIndexCount) {
/*  510 */       return i;
/*      */     }
/*  512 */     if (paramInt3 < 0)
/*      */     {
/*  514 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray27"));
/*      */     }
/*      */ 
/*      */     
/*  518 */     if (i == paramArrayOfInt[paramInt1]) {
/*  519 */       if (paramInt3 >= i) {
/*  520 */         i = paramInt3;
/*      */       }
/*      */       else {
/*      */         
/*  524 */         for (byte b = 0; b < paramArrayOfInt.length; b++) {
/*  525 */           if (paramArrayOfInt[b] > i) {
/*  526 */             i = paramArrayOfInt[b];
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*  531 */     } else if (paramInt3 > i) {
/*  532 */       i = paramInt3;
/*      */     } 
/*  534 */     return i;
/*      */   }
/*      */   
/*      */   int doIndicesCheck(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/*  538 */     int i = paramInt2;
/*  539 */     boolean bool1 = false;
/*  540 */     int k = paramArrayOfInt2.length;
/*  541 */     boolean bool2 = false;
/*  542 */     for (int j = 0; j < k; j++) {
/*  543 */       if (paramInt1 + j >= this.initialIndexIndex)
/*      */       {
/*      */         
/*  546 */         if (paramInt1 + j < this.initialIndexIndex + this.validIndexCount) {
/*      */           
/*  548 */           if (paramArrayOfInt2[j] < 0)
/*      */           {
/*  550 */             throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray27"));
/*      */           }
/*      */           
/*  553 */           if (paramArrayOfInt1[paramInt1 + j] == paramInt2) {
/*  554 */             if (paramArrayOfInt2[j] >= i) {
/*  555 */               i = paramArrayOfInt2[j];
/*  556 */               bool1 = false;
/*  557 */               bool2 = true;
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*  562 */             else if (!bool2) {
/*  563 */               bool1 = true;
/*      */             }
/*      */           
/*  566 */           } else if (paramArrayOfInt2[j] >= i) {
/*  567 */             i = paramArrayOfInt2[j];
/*  568 */             bool1 = false;
/*  569 */             bool2 = true;
/*      */           } 
/*      */         }  } 
/*  572 */     }  if (bool1) {
/*  573 */       for (byte b = 0; b < paramArrayOfInt1.length; b++) {
/*  574 */         if (paramArrayOfInt1[b] > i) {
/*  575 */           i = paramArrayOfInt1[b];
/*      */         }
/*      */       } 
/*      */     }
/*  579 */     return i;
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
/*      */   final void setCoordinateIndices(int paramInt, int[] paramArrayOfInt) {
/*  591 */     int k = paramArrayOfInt.length;
/*  592 */     int i = doIndicesCheck(paramInt, this.maxCoordIndex, this.indexCoord, paramArrayOfInt);
/*  593 */     if (i > this.maxCoordIndex) {
/*  594 */       doErrorCheck(i);
/*      */     }
/*  596 */     if ((this.vertexFormat & 0x200) != 0) {
/*  597 */       if ((this.vertexFormat & 0x4) != 0) {
/*  598 */         this.maxColorIndex = i;
/*      */       }
/*  600 */       if ((this.vertexFormat & 0x460) != 0) {
/*  601 */         for (byte b1 = 0; b1 < this.texCoordSetCount; b1++) {
/*  602 */           this.maxTexCoordIndices[b1] = i;
/*      */         }
/*      */       }
/*  605 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  606 */         for (byte b1 = 0; b1 < this.vertexAttrCount; b1++) {
/*  607 */           this.maxVertexAttrIndices[b1] = i;
/*      */         }
/*      */       }
/*  610 */       if ((this.vertexFormat & 0x2) != 0) {
/*  611 */         this.maxNormalIndex = i;
/*      */       }
/*      */     } 
/*      */     
/*  615 */     this.geomLock.getLock();
/*  616 */     this.dirtyFlag |= 0x20;
/*  617 */     this.maxCoordIndex = i; byte b; int j;
/*  618 */     for (b = 0, j = paramInt; b < k; b++, j++) {
/*  619 */       this.indexCoord[j] = paramArrayOfInt[b];
/*      */     }
/*  621 */     this.geomLock.unLock();
/*  622 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  623 */       sendDataChangedMessage(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void setCoordIndicesRef(int[] paramArrayOfInt) {
/*  633 */     int i = 0;
/*      */     
/*  635 */     if (paramArrayOfInt != null) {
/*  636 */       if (paramArrayOfInt.length < this.initialIndexIndex + this.validIndexCount) {
/*  637 */         throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryArray33"));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  652 */       i = computeMaxIndexWithCheck(this.initialIndexIndex, this.validIndexCount, paramArrayOfInt);
/*  653 */       if (i > this.maxCoordIndex) {
/*  654 */         doErrorCheck(i);
/*      */       }
/*      */     } 
/*      */     
/*  658 */     if ((this.vertexFormat & 0x200) != 0) {
/*  659 */       if ((this.vertexFormat & 0x4) != 0) {
/*  660 */         this.maxColorIndex = i;
/*      */       }
/*  662 */       if ((this.vertexFormat & 0x460) != 0) {
/*  663 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  664 */           this.maxTexCoordIndices[b] = i;
/*      */         }
/*      */       }
/*  667 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  668 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  669 */           this.maxVertexAttrIndices[b] = i;
/*      */         }
/*      */       }
/*  672 */       if ((this.vertexFormat & 0x2) != 0) {
/*  673 */         this.maxNormalIndex = i;
/*      */       }
/*      */     } 
/*      */     
/*  677 */     this.geomLock.getLock();
/*  678 */     this.dirtyFlag |= 0x20;
/*  679 */     this.maxCoordIndex = i;
/*  680 */     this.indexCoord = paramArrayOfInt;
/*  681 */     this.geomLock.unLock();
/*  682 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  683 */       sendDataChangedMessage(true);
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
/*      */   final void doPostUpdaterUpdate() {
/*  709 */     int i = 0;
/*      */     
/*  711 */     if (this.indexCoord != null) {
/*  712 */       i = computeMaxIndexWithCheck(this.initialIndexIndex, this.validIndexCount, this.indexCoord);
/*  713 */       if (i > this.maxCoordIndex) {
/*  714 */         doErrorCheck(i);
/*      */       }
/*      */     } 
/*      */     
/*  718 */     if ((this.vertexFormat & 0x200) != 0) {
/*  719 */       if ((this.vertexFormat & 0x4) != 0) {
/*  720 */         this.maxColorIndex = i;
/*      */       }
/*  722 */       if ((this.vertexFormat & 0x460) != 0) {
/*  723 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/*  724 */           this.maxTexCoordIndices[b] = i;
/*      */         }
/*      */       }
/*  727 */       if ((this.vertexFormat & 0x1000) != 0) {
/*  728 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/*  729 */           this.maxVertexAttrIndices[b] = i;
/*      */         }
/*      */       }
/*  732 */       if ((this.vertexFormat & 0x2) != 0) {
/*  733 */         this.maxNormalIndex = i;
/*      */       }
/*      */     } 
/*      */     
/*  737 */     this.dirtyFlag |= 0x20;
/*  738 */     this.maxCoordIndex = i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void setColorIndex(int paramInt1, int paramInt2) {
/*  748 */     int i = this.maxColorIndex;
/*      */     
/*  750 */     i = doIndexCheck(paramInt1, this.maxColorIndex, this.indexColor, paramInt2);
/*  751 */     if (i > this.maxColorIndex) {
/*  752 */       doColorCheck(i);
/*      */     }
/*  754 */     this.geomLock.getLock();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  759 */     this.indexColor[paramInt1] = paramInt2;
/*  760 */     this.maxColorIndex = i;
/*  761 */     this.geomLock.unLock();
/*  762 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  763 */       sendDataChangedMessage(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void setColorIndices(int paramInt, int[] paramArrayOfInt) {
/*  774 */     int j = paramArrayOfInt.length;
/*      */ 
/*      */     
/*  777 */     int k = doIndicesCheck(paramInt, this.maxColorIndex, this.indexColor, paramArrayOfInt);
/*  778 */     if (k > this.maxColorIndex) {
/*  779 */       doColorCheck(k);
/*      */     }
/*  781 */     this.geomLock.getLock();
/*  782 */     this.maxColorIndex = k; byte b; int i;
/*  783 */     for (b = 0, i = paramInt; b < j; b++, i++) {
/*  784 */       this.indexColor[i] = paramArrayOfInt[b];
/*      */     }
/*  786 */     this.geomLock.unLock();
/*  787 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  788 */       sendDataChangedMessage(false);
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
/*      */   final void setNormalIndex(int paramInt1, int paramInt2) {
/*  801 */     int i = doIndexCheck(paramInt1, this.maxNormalIndex, this.indexNormal, paramInt2);
/*  802 */     if (i > this.maxNormalIndex) {
/*  803 */       doNormalCheck(i);
/*      */     }
/*  805 */     this.geomLock.getLock();
/*  806 */     this.maxNormalIndex = i;
/*  807 */     this.indexNormal[paramInt1] = paramInt2;
/*  808 */     this.geomLock.unLock();
/*  809 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  810 */       sendDataChangedMessage(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void setNormalIndices(int paramInt, int[] paramArrayOfInt) {
/*  821 */     int j = paramArrayOfInt.length;
/*      */ 
/*      */     
/*  824 */     int k = doIndicesCheck(paramInt, this.maxNormalIndex, this.indexNormal, paramArrayOfInt);
/*  825 */     if (k > this.maxNormalIndex) {
/*  826 */       doNormalCheck(k);
/*      */     }
/*  828 */     this.geomLock.getLock(); byte b; int i;
/*  829 */     for (b = 0, i = paramInt; b < j; b++, i++) {
/*  830 */       this.indexNormal[i] = paramArrayOfInt[b];
/*      */     }
/*  832 */     this.maxNormalIndex = k;
/*  833 */     this.geomLock.unLock();
/*  834 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  835 */       sendDataChangedMessage(false);
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
/*      */   final void setTextureCoordinateIndex(int paramInt1, int paramInt2, int paramInt3) {
/*  848 */     int[] arrayOfInt = this.indexTexCoord[paramInt1];
/*      */     
/*  850 */     int i = doIndexCheck(paramInt2, this.maxTexCoordIndices[paramInt1], arrayOfInt, paramInt3);
/*  851 */     if (i > this.maxTexCoordIndices[paramInt1]) {
/*  852 */       doTexCoordCheck(i, paramInt1);
/*      */     }
/*  854 */     this.geomLock.getLock();
/*  855 */     this.maxTexCoordIndices[paramInt1] = i;
/*  856 */     arrayOfInt[paramInt2] = paramInt3;
/*  857 */     this.geomLock.unLock();
/*  858 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  859 */       sendDataChangedMessage(false);
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
/*      */   final void setTextureCoordinateIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  871 */     int j = paramArrayOfInt.length;
/*  872 */     int[] arrayOfInt = this.indexTexCoord[paramInt1];
/*      */ 
/*      */ 
/*      */     
/*  876 */     int k = doIndicesCheck(paramInt2, this.maxTexCoordIndices[paramInt1], arrayOfInt, paramArrayOfInt);
/*  877 */     if (k > this.maxTexCoordIndices[paramInt1]) {
/*  878 */       doTexCoordCheck(k, paramInt1);
/*      */     }
/*  880 */     this.geomLock.getLock();
/*  881 */     this.maxTexCoordIndices[paramInt1] = k; byte b; int i;
/*  882 */     for (b = 0, i = paramInt2; b < j; b++, i++) {
/*  883 */       arrayOfInt[i] = paramArrayOfInt[b];
/*      */     }
/*  885 */     this.geomLock.unLock();
/*  886 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  887 */       sendDataChangedMessage(false);
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
/*      */   void setVertexAttrIndex(int paramInt1, int paramInt2, int paramInt3) {
/*  901 */     int[] arrayOfInt = this.indexVertexAttr[paramInt1];
/*      */     
/*  903 */     int i = doIndexCheck(paramInt2, this.maxVertexAttrIndices[paramInt1], arrayOfInt, paramInt3);
/*  904 */     if (i > this.maxVertexAttrIndices[paramInt1]) {
/*  905 */       doVertexAttrCheck(i, paramInt1);
/*      */     }
/*  907 */     this.geomLock.getLock();
/*  908 */     this.maxVertexAttrIndices[paramInt1] = i;
/*  909 */     arrayOfInt[paramInt2] = paramInt3;
/*  910 */     this.geomLock.unLock();
/*  911 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  912 */       sendDataChangedMessage(false);
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
/*      */   void setVertexAttrIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  925 */     int j = paramArrayOfInt.length;
/*  926 */     int[] arrayOfInt = this.indexVertexAttr[paramInt1];
/*      */ 
/*      */ 
/*      */     
/*  930 */     int k = doIndicesCheck(paramInt2, this.maxVertexAttrIndices[paramInt1], arrayOfInt, paramArrayOfInt);
/*  931 */     if (k > this.maxVertexAttrIndices[paramInt1]) {
/*  932 */       doVertexAttrCheck(k, paramInt1);
/*      */     }
/*  934 */     this.geomLock.getLock();
/*  935 */     this.maxVertexAttrIndices[paramInt1] = k; byte b; int i;
/*  936 */     for (b = 0, i = paramInt2; b < j; b++, i++) {
/*  937 */       arrayOfInt[i] = paramArrayOfInt[b];
/*      */     }
/*  939 */     this.geomLock.unLock();
/*  940 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/*  941 */       sendDataChangedMessage(false);
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
/*  952 */   final int getCoordinateIndex(int paramInt) { return this.indexCoord[paramInt]; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void getCoordinateIndices(int paramInt, int[] paramArrayOfInt) {
/*  962 */     int j = paramArrayOfInt.length; byte b;
/*      */     int i;
/*  964 */     for (b = 0, i = paramInt; b < j; b++, i++) {
/*  965 */       paramArrayOfInt[b] = this.indexCoord[i];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  975 */   final int[] getCoordIndicesRef() { return this.indexCoord; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  985 */   final int getColorIndex(int paramInt) { return this.indexColor[paramInt]; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void getColorIndices(int paramInt, int[] paramArrayOfInt) {
/*  995 */     int j = paramArrayOfInt.length; byte b;
/*      */     int i;
/*  997 */     for (b = 0, i = paramInt; b < j; b++, i++) {
/*  998 */       paramArrayOfInt[b] = this.indexColor[i];
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
/* 1009 */   final int getNormalIndex(int paramInt) { return this.indexNormal[paramInt]; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void getNormalIndices(int paramInt, int[] paramArrayOfInt) {
/* 1019 */     int j = paramArrayOfInt.length; byte b;
/*      */     int i;
/* 1021 */     for (b = 0, i = paramInt; b < j; b++, i++) {
/* 1022 */       paramArrayOfInt[b] = this.indexNormal[i];
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
/*      */   final int getTextureCoordinateIndex(int paramInt1, int paramInt2) {
/* 1034 */     int[] arrayOfInt = this.indexTexCoord[paramInt1];
/*      */     
/* 1036 */     return arrayOfInt[paramInt2];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void getTextureCoordinateIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/* 1047 */     int j = paramArrayOfInt.length;
/* 1048 */     int[] arrayOfInt = this.indexTexCoord[paramInt1]; byte b;
/*      */     int i;
/* 1050 */     for (b = 0, i = paramInt2; b < j; b++, i++) {
/* 1051 */       paramArrayOfInt[b] = arrayOfInt[i];
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
/*      */   int getVertexAttrIndex(int paramInt1, int paramInt2) {
/* 1063 */     int[] arrayOfInt = this.indexVertexAttr[paramInt1];
/*      */     
/* 1065 */     return arrayOfInt[paramInt2];
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
/*      */   void getVertexAttrIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/* 1077 */     int j = paramArrayOfInt.length;
/* 1078 */     int[] arrayOfInt = this.indexVertexAttr[paramInt1]; byte b;
/*      */     int i;
/* 1080 */     for (b = 0, i = paramInt2; b < j; b++, i++) {
/* 1081 */       paramArrayOfInt[b] = arrayOfInt[i];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void execute(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, int paramInt, boolean paramBoolean3) {
/* 1091 */     boolean bool = false;
/*      */     
/* 1093 */     if (this.mirrorGeometry != null) {
/* 1094 */       this.mirrorGeometry.execute(paramCanvas3D, paramRenderAtom, paramBoolean1, paramBoolean2, paramFloat, paramInt, paramBoolean3);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1100 */     if (this.indexCoord == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1105 */     if ((this.vertexFormat & 0x800) == 0) {
/* 1106 */       if ((this.vertexFormat & 0x80) == 0) {
/*      */         float[] arrayOfFloat;
/*      */         int i;
/* 1109 */         synchronized (this) {
/* 1110 */           i = this.dirtyFlag;
/* 1111 */           if (paramBoolean2 && !paramBoolean3) {
/*      */             
/* 1113 */             Object[] arrayOfObject = updateAlphaInVertexData(paramCanvas3D, paramInt, paramFloat);
/* 1114 */             bool = (arrayOfObject[false] == Boolean.TRUE);
/* 1115 */             arrayOfFloat = (float[])arrayOfObject[1];
/*      */ 
/*      */             
/* 1118 */             if (paramFloat != this.lastScreenAlpha) {
/*      */               
/* 1120 */               this.lastScreenAlpha = paramFloat;
/* 1121 */               i |= 0x4;
/*      */             } 
/*      */           } else {
/* 1124 */             arrayOfFloat = this.vertexData;
/*      */             
/* 1126 */             if (this.lastScreenAlpha != -1.0F) {
/* 1127 */               this.lastScreenAlpha = -1.0F;
/* 1128 */               i |= 0x4;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1134 */           this.dirtyFlag = 0;
/*      */         } 
/*      */         
/* 1137 */         Pipeline.getPipeline().executeIndexedGeometry(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, bool, paramBoolean3, this.initialIndexIndex, this.validIndexCount, this.maxCoordIndex + 1, ((this.vertexFormat & 0x4) != 0) ? (this.vertexFormat | 0xC) : this.vertexFormat, this.vertexAttrCount, this.vertexAttrSizes, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, paramCanvas3D.numActiveTexUnit, arrayOfFloat, null, i, this.indexCoord);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1156 */       else if ((this.vertexFormat & 0x100) != 0) {
/* 1157 */         int i; if (this.interLeavedVertexData == null) {
/*      */           return;
/*      */         }
/* 1160 */         float[] arrayOfFloat = null;
/*      */         
/* 1162 */         synchronized (this) {
/* 1163 */           i = this.dirtyFlag;
/* 1164 */           if (paramBoolean2 && !paramBoolean3) {
/*      */             
/* 1166 */             Object[] arrayOfObject = updateAlphaInInterLeavedData(paramCanvas3D, paramInt, paramFloat);
/* 1167 */             bool = (arrayOfObject[false] == Boolean.TRUE);
/* 1168 */             arrayOfFloat = (float[])arrayOfObject[1];
/* 1169 */             if (paramFloat != this.lastScreenAlpha) {
/* 1170 */               this.lastScreenAlpha = paramFloat;
/* 1171 */               i |= 0x4;
/*      */             }
/*      */           
/*      */           }
/* 1175 */           else if (this.lastScreenAlpha != -1.0F) {
/* 1176 */             this.lastScreenAlpha = -1.0F;
/* 1177 */             i |= 0x4;
/*      */           } 
/*      */           
/* 1180 */           this.dirtyFlag = 0;
/*      */         } 
/*      */         
/* 1183 */         Pipeline.getPipeline().executeIndexedGeometry(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, bool, paramBoolean3, this.initialIndexIndex, this.validIndexCount, this.maxCoordIndex + 1, this.vertexFormat, this.vertexAttrCount, this.vertexAttrSizes, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, paramCanvas3D.numActiveTexUnit, this.interLeavedVertexData, arrayOfFloat, i, this.indexCoord);
/*      */       } else {
/*      */         int i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1202 */         if (this.vertexType == 0 || (this.vertexType & 0xF) == 0 || ((this.vertexFormat & 0x4) != 0 && (this.vertexType & 0x3F0) == 0) || ((this.vertexFormat & 0x2) != 0 && (this.vertexType & 0xC00) == 0) || ((this.vertexFormat & 0x1000) != 0 && (this.vertexType & 0x8000) == 0) || ((this.vertexFormat & 0x460) != 0 && (this.vertexType & 0x7000) == 0)) {
/*      */           return;
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
/* 1214 */         byte[] arrayOfByte = null;
/* 1215 */         float[] arrayOfFloat = null;
/*      */         
/* 1217 */         if ((this.vertexType & 0xD0) != 0) {
/* 1218 */           synchronized (this) {
/* 1219 */             i = this.dirtyFlag;
/* 1220 */             if (paramBoolean2 && !paramBoolean3) {
/* 1221 */               arrayOfFloat = updateAlphaInFloatRefColors(paramCanvas3D, paramInt, paramFloat);
/*      */               
/* 1223 */               if (paramFloat != this.lastScreenAlpha) {
/* 1224 */                 this.lastScreenAlpha = paramFloat;
/* 1225 */                 i |= 0x4;
/*      */               } 
/*      */             } else {
/* 1228 */               arrayOfFloat = this.mirrorFloatRefColors[0];
/*      */               
/* 1230 */               if (this.lastScreenAlpha != -1.0F) {
/* 1231 */                 this.lastScreenAlpha = -1.0F;
/* 1232 */                 i |= 0x4;
/*      */               } 
/*      */             } 
/*      */             
/* 1236 */             this.dirtyFlag = 0;
/*      */           } 
/* 1238 */         } else if ((this.vertexType & 0x320) != 0) {
/* 1239 */           synchronized (this) {
/* 1240 */             i = this.dirtyFlag;
/* 1241 */             if (paramBoolean2 && !paramBoolean3) {
/* 1242 */               arrayOfByte = updateAlphaInByteRefColors(paramCanvas3D, paramInt, paramFloat);
/*      */               
/* 1244 */               if (paramFloat != this.lastScreenAlpha) {
/* 1245 */                 this.lastScreenAlpha = paramFloat;
/* 1246 */                 i |= 0x4;
/*      */               } 
/*      */             } else {
/* 1249 */               arrayOfByte = this.mirrorUnsignedByteRefColors[0];
/*      */               
/* 1251 */               if (this.lastScreenAlpha != -1.0F) {
/* 1252 */                 this.lastScreenAlpha = -1.0F;
/* 1253 */                 i |= 0x4;
/*      */               } 
/*      */             } 
/* 1256 */             this.dirtyFlag = 0;
/*      */           } 
/*      */         } else {
/* 1259 */           i = this.dirtyFlag;
/*      */         } 
/*      */         
/* 1262 */         byte b = 0;
/* 1263 */         if ((this.vertexType & 0x5) != 0)
/* 1264 */           b |= true; 
/* 1265 */         if ((this.vertexType & 0xA) != 0)
/* 1266 */           b |= 0x2; 
/* 1267 */         if ((this.vertexType & 0xD0) != 0)
/* 1268 */           b |= 0x4; 
/* 1269 */         if ((this.vertexType & 0x320) != 0)
/* 1270 */           b |= 0x8; 
/* 1271 */         if ((this.vertexType & 0xC00) != 0)
/* 1272 */           b |= 0x10; 
/* 1273 */         if ((this.vertexType & 0x8000) != 0)
/* 1274 */           b |= 0x40; 
/* 1275 */         if ((this.vertexType & 0x7000) != 0) {
/* 1276 */           b |= 0x20;
/*      */         }
/* 1278 */         Pipeline.getPipeline().executeIndexedGeometryVA(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, paramBoolean3, this.initialIndexIndex, this.validIndexCount, this.maxCoordIndex + 1, this.vertexFormat | this.c4fAllocated, b, this.mirrorFloatRefCoords, this.mirrorDoubleRefCoords, arrayOfFloat, arrayOfByte, this.mirrorFloatRefNormals, this.vertexAttrCount, this.vertexAttrSizes, this.mirrorFloatRefVertexAttrs, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMap, paramCanvas3D.numActiveTexUnit, this.texCoordStride, this.mirrorRefTexCoords, i, this.indexCoord);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1301 */     else if ((this.vertexFormat & 0x100) != 0) {
/* 1302 */       int i; if (this.interleavedFloatBufferImpl == null) {
/*      */         return;
/*      */       }
/* 1305 */       float[] arrayOfFloat = null;
/*      */       
/* 1307 */       synchronized (this) {
/* 1308 */         i = this.dirtyFlag;
/* 1309 */         if (paramBoolean2 && !paramBoolean3) {
/*      */           
/* 1311 */           Object[] arrayOfObject = updateAlphaInInterLeavedData(paramCanvas3D, paramInt, paramFloat);
/* 1312 */           bool = (arrayOfObject[false] == Boolean.TRUE);
/* 1313 */           arrayOfFloat = (float[])arrayOfObject[1];
/* 1314 */           if (paramFloat != this.lastScreenAlpha) {
/* 1315 */             this.lastScreenAlpha = paramFloat;
/* 1316 */             i |= 0x4;
/*      */           }
/*      */         
/*      */         }
/* 1320 */         else if (this.lastScreenAlpha != -1.0F) {
/* 1321 */           this.lastScreenAlpha = -1.0F;
/* 1322 */           i |= 0x4;
/*      */         } 
/*      */         
/* 1325 */         this.dirtyFlag = 0;
/*      */       } 
/*      */       
/* 1328 */       Pipeline.getPipeline().executeIndexedGeometryBuffer(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, bool, paramBoolean3, this.initialIndexIndex, this.validIndexCount, this.maxCoordIndex + 1, this.vertexFormat, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, paramCanvas3D.numActiveTexUnit, this.interleavedFloatBufferImpl.getBufferAsObject(), arrayOfFloat, i, this.indexCoord);
/*      */     } else {
/*      */       int i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1346 */       if (this.vertexType == 0 || (this.vertexType & 0xF) == 0 || ((this.vertexFormat & 0x4) != 0 && (this.vertexType & 0x3F0) == 0) || ((this.vertexFormat & 0x2) != 0 && (this.vertexType & 0xC00) == 0) || ((this.vertexFormat & 0x1000) != 0 && (this.vertexType & 0x8000) == 0) || ((this.vertexFormat & 0x460) != 0 && (this.vertexType & 0x7000) == 0)) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1358 */       byte[] arrayOfByte = null;
/* 1359 */       float[] arrayOfFloat = null;
/*      */       
/* 1361 */       if ((this.vertexType & 0x10) != 0) {
/* 1362 */         synchronized (this) {
/* 1363 */           i = this.dirtyFlag;
/* 1364 */           if (paramBoolean2 && !paramBoolean3) {
/* 1365 */             arrayOfFloat = updateAlphaInFloatRefColors(paramCanvas3D, paramInt, paramFloat);
/*      */             
/* 1367 */             if (paramFloat != this.lastScreenAlpha) {
/* 1368 */               this.lastScreenAlpha = paramFloat;
/* 1369 */               i |= 0x4;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1374 */             arrayOfFloat = this.mirrorFloatRefColors[0];
/*      */             
/* 1376 */             if (this.lastScreenAlpha != -1.0F) {
/* 1377 */               this.lastScreenAlpha = -1.0F;
/* 1378 */               i |= 0x4;
/*      */             } 
/*      */           } 
/*      */           
/* 1382 */           this.dirtyFlag = 0;
/*      */         } 
/* 1384 */       } else if ((this.vertexType & 0x20) != 0) {
/* 1385 */         synchronized (this) {
/* 1386 */           i = this.dirtyFlag;
/* 1387 */           if (paramBoolean2 && !paramBoolean3) {
/* 1388 */             arrayOfByte = updateAlphaInByteRefColors(paramCanvas3D, paramInt, paramFloat);
/*      */             
/* 1390 */             if (paramFloat != this.lastScreenAlpha) {
/* 1391 */               this.lastScreenAlpha = paramFloat;
/* 1392 */               i |= 0x4;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1397 */             arrayOfByte = this.mirrorUnsignedByteRefColors[0];
/*      */             
/* 1399 */             if (this.lastScreenAlpha != -1.0F) {
/* 1400 */               this.lastScreenAlpha = -1.0F;
/* 1401 */               i |= 0x4;
/*      */             } 
/*      */           } 
/* 1404 */           this.dirtyFlag = 0;
/*      */         } 
/*      */       } else {
/* 1407 */         i = this.dirtyFlag;
/*      */       } 
/*      */       
/* 1410 */       Object object1 = null, object2 = null, object3 = null;
/*      */       
/* 1412 */       byte b = 0;
/* 1413 */       if ((this.vertexType & true) != 0) {
/* 1414 */         b |= true;
/* 1415 */         object1 = this.floatBufferRefCoords.getBufferAsObject();
/* 1416 */       } else if ((this.vertexType & 0x2) != 0) {
/* 1417 */         b |= 0x2;
/* 1418 */         object1 = this.doubleBufferRefCoords.getBufferAsObject();
/*      */       } 
/* 1420 */       if ((this.vertexType & 0x10) != 0) {
/* 1421 */         b |= 0x4;
/* 1422 */         object2 = this.floatBufferRefColors.getBufferAsObject();
/* 1423 */       } else if ((this.vertexType & 0x20) != 0) {
/* 1424 */         b |= 0x8;
/* 1425 */         object2 = this.byteBufferRefColors.getBufferAsObject();
/*      */       } 
/*      */       
/* 1428 */       if ((this.vertexType & 0xC00) != 0) {
/* 1429 */         b |= 0x10;
/* 1430 */         object3 = this.floatBufferRefNormals.getBufferAsObject();
/*      */       } 
/*      */       
/* 1433 */       if ((this.vertexType & 0x8000) != 0) {
/* 1434 */         b |= 0x40;
/*      */       }
/*      */       
/* 1437 */       if ((this.vertexType & 0x7000) != 0) {
/* 1438 */         b |= 0x20;
/*      */       }
/*      */       
/* 1441 */       Pipeline.getPipeline().executeIndexedGeometryVABuffer(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, paramBoolean3, this.initialIndexIndex, this.validIndexCount, this.maxCoordIndex + 1, this.vertexFormat | this.c4fAllocated, b, object1, object2, arrayOfFloat, arrayOfByte, object3, this.vertexAttrCount, this.vertexAttrSizes, this.nioFloatBufferRefVertexAttrs, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMap, paramCanvas3D.numActiveTexUnit, this.texCoordStride, this.refTexCoords, i, this.indexCoord);
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
/*      */   void buildGA(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1470 */     boolean bool = false;
/*      */     
/* 1472 */     if (this.mirrorGeometry != null) {
/* 1473 */       ((GeometryArrayRetained)this.mirrorGeometry).buildGA(paramCanvas3D, paramRenderAtom, paramBoolean1, paramBoolean2, paramFloat, paramBoolean3, paramTransform3D1, paramTransform3D2);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1478 */     else if ((this.vertexFormat & 0x80) == 0) {
/*      */       float[] arrayOfFloat;
/*      */       
/* 1481 */       synchronized (this) {
/* 1482 */         int i = this.dirtyFlag;
/* 1483 */         if (paramBoolean2 && !paramBoolean3) {
/*      */           
/* 1485 */           Object[] arrayOfObject = updateAlphaInVertexData(paramCanvas3D, paramCanvas3D.screen.screen, paramFloat);
/* 1486 */           bool = (arrayOfObject[false] == Boolean.TRUE) ? 1 : 0;
/* 1487 */           arrayOfFloat = (float[])arrayOfObject[1];
/*      */ 
/*      */           
/* 1490 */           if (paramFloat != this.lastScreenAlpha) {
/*      */             
/* 1492 */             this.lastScreenAlpha = paramFloat;
/* 1493 */             i |= 0x4;
/*      */           } 
/*      */         } else {
/* 1496 */           arrayOfFloat = this.vertexData;
/*      */           
/* 1498 */           if (this.lastScreenAlpha != -1.0F) {
/* 1499 */             this.lastScreenAlpha = -1.0F;
/* 1500 */             i |= 0x4;
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1506 */         this.dirtyFlag = 0;
/*      */       } 
/*      */       
/* 1509 */       Pipeline.getPipeline().buildIndexedGeometry(paramCanvas3D.ctx, this, this.geoType, paramBoolean1, paramBoolean2, paramFloat, paramBoolean3, this.initialIndexIndex, this.validIndexCount, this.maxCoordIndex + 1, this.vertexFormat, this.vertexAttrCount, this.vertexAttrSizes, this.texCoordSetCount, this.texCoordSetMap, (this.texCoordSetMap == null) ? 0 : this.texCoordSetMap.length, this.texCoordSetMapOffset, (paramTransform3D1 == null) ? null : paramTransform3D1.mat, (paramTransform3D2 == null) ? null : paramTransform3D2.mat, arrayOfFloat, this.indexCoord);
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
/*      */   void mergeGeometryArrays(ArrayList paramArrayList) {
/* 1534 */     int i = paramArrayList.size();
/* 1535 */     int[] arrayOfInt = null;
/* 1536 */     this.indexCount = 0; byte b1;
/* 1537 */     for (b1 = 0; b1 < i; b1++) {
/* 1538 */       IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)paramArrayList.get(b1);
/* 1539 */       this.indexCount += indexedGeometryArrayRetained.validIndexCount;
/*      */     } 
/* 1541 */     this.validIndexCount = this.indexCount;
/* 1542 */     this.initialIndexIndex = 0;
/* 1543 */     this.compileIndexCount = new int[i];
/* 1544 */     this.compileIndexOffset = new int[i];
/* 1545 */     this.indexCoord = new int[this.indexCount];
/* 1546 */     b1 = ((this.vertexFormat & 0x200) == 0) ? 1 : 0;
/* 1547 */     if (b1 != 0) {
/* 1548 */       if ((this.vertexFormat & 0x4) != 0)
/* 1549 */         this.indexColor = new int[this.indexCount]; 
/* 1550 */       if ((this.vertexFormat & 0x2) != 0) {
/* 1551 */         this.indexNormal = new int[this.indexCount];
/*      */       }
/*      */       
/* 1554 */       if ((this.vertexFormat & 0x460) != 0) {
/* 1555 */         this.indexTexCoord = new int[1][];
/* 1556 */         this.indexTexCoord[0] = new int[this.indexCount];
/* 1557 */         arrayOfInt = this.indexTexCoord[0];
/*      */       } 
/*      */     } 
/* 1560 */     int j = 0;
/* 1561 */     int k = 0;
/* 1562 */     for (byte b2 = 0; b2 < i; b2++) {
/* 1563 */       IndexedGeometryArrayRetained indexedGeometryArrayRetained = (IndexedGeometryArrayRetained)paramArrayList.get(b2);
/* 1564 */       int m = indexedGeometryArrayRetained.validIndexCount;
/* 1565 */       this.compileIndexCount[b2] = m;
/*      */       
/* 1567 */       for (int n = 0; n < m; n++) {
/* 1568 */         this.indexCoord[n + k] = indexedGeometryArrayRetained.indexCoord[n + indexedGeometryArrayRetained.initialIndexIndex] + j;
/* 1569 */         if (b1 != 0) {
/* 1570 */           if ((this.vertexFormat & 0x4) != 0)
/* 1571 */             this.indexColor[n + k] = indexedGeometryArrayRetained.indexColor[n + indexedGeometryArrayRetained.initialIndexIndex] + j; 
/* 1572 */           if ((this.vertexFormat & 0x2) != 0)
/* 1573 */             this.indexNormal[n + k] = indexedGeometryArrayRetained.indexNormal[n + indexedGeometryArrayRetained.initialIndexIndex] + j; 
/* 1574 */           if ((this.vertexFormat & 0x460) != 0)
/* 1575 */             arrayOfInt[n + k] = indexedGeometryArrayRetained.indexTexCoord[0][n + indexedGeometryArrayRetained.initialIndexIndex] + j; 
/*      */         } 
/*      */       } 
/* 1578 */       indexedGeometryArrayRetained.maxCoordIndex += j;
/* 1579 */       this.compileIndexOffset[b2] = k;
/* 1580 */       j += indexedGeometryArrayRetained.vertexCount;
/* 1581 */       k += m;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1586 */     super.mergeGeometryArrays(paramArrayList);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isWriteStatic() {
/* 1592 */     if (!super.isWriteStatic() || this.source.getCapability(10) || this.source.getCapability(12) || this.source.getCapability(14) || this.source.getCapability(25) || this.source.getCapability(16))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1598 */       return false;
/*      */     }
/*      */     
/* 1601 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1609 */   int getIndexCount(int paramInt) { return this.compileIndexCount[paramInt]; }
/*      */ 
/*      */   
/*      */   int computeMaxIndex(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/* 1613 */     int i = 0;
/* 1614 */     if (paramArrayOfInt != null) {
/* 1615 */       for (int j = paramInt1; j < paramInt1 + paramInt2; j++) {
/* 1616 */         if (paramArrayOfInt[j] > i) {
/* 1617 */           i = paramArrayOfInt[j];
/*      */         }
/*      */       } 
/*      */     }
/* 1621 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int computeMaxIndexWithCheck(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/* 1628 */     int i = 0;
/* 1629 */     for (int j = paramInt1; j < paramInt1 + paramInt2; j++) {
/*      */       
/* 1631 */       if (paramArrayOfInt[j] < 0)
/* 1632 */         throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("IndexedGeometryArray27")); 
/* 1633 */       if (paramArrayOfInt[j] > i) {
/* 1634 */         i = paramArrayOfInt[j];
/*      */       }
/*      */     } 
/* 1637 */     return i;
/*      */   }
/*      */ 
/*      */   
/*      */   void setValidIndexCount(int paramInt) {
/* 1642 */     if (paramInt < 0) {
/* 1643 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryArray21"));
/*      */     }
/* 1645 */     if (this.initialIndexIndex + paramInt > this.indexCount) {
/* 1646 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryArray22"));
/*      */     }
/* 1648 */     if ((this.vertexFormat & 0x2000) != 0 && 
/* 1649 */       this.indexCoord != null && this.indexCoord.length < this.initialIndexIndex + paramInt) {
/* 1650 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryArray33"));
/*      */     }
/*      */     
/* 1653 */     int i = 0;
/* 1654 */     int j = 0;
/* 1655 */     int k = 0;
/* 1656 */     int[] arrayOfInt1 = null;
/* 1657 */     int[] arrayOfInt2 = null;
/*      */     
/* 1659 */     i = computeMaxIndex(this.initialIndexIndex, paramInt, this.indexCoord);
/* 1660 */     doErrorCheck(i);
/* 1661 */     if ((this.vertexFormat & 0x200) == 0) {
/* 1662 */       if ((this.vertexFormat & 0x4) != 0) {
/* 1663 */         j = computeMaxIndex(this.initialIndexIndex, paramInt, this.indexColor);
/* 1664 */         doColorCheck(j);
/*      */       } 
/* 1666 */       if ((this.vertexFormat & 0x460) != 0) {
/* 1667 */         arrayOfInt1 = new int[this.texCoordSetCount];
/* 1668 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1669 */           arrayOfInt1[b] = computeMaxIndex(this.initialIndexIndex, paramInt, this.indexTexCoord[b]);
/*      */           
/* 1671 */           doTexCoordCheck(arrayOfInt1[b], b);
/*      */         } 
/*      */       } 
/* 1674 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 1675 */         arrayOfInt2 = new int[this.vertexAttrCount];
/* 1676 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/* 1677 */           arrayOfInt2[b] = computeMaxIndex(this.initialIndexIndex, paramInt, this.indexVertexAttr[b]);
/*      */ 
/*      */           
/* 1680 */           doVertexAttrCheck(arrayOfInt2[b], b);
/*      */         } 
/*      */       } 
/* 1683 */       if ((this.vertexFormat & 0x2) != 0) {
/* 1684 */         k = computeMaxIndex(this.initialIndexIndex, paramInt, this.indexNormal);
/* 1685 */         doNormalCheck(k);
/*      */       } 
/*      */     } 
/*      */     
/* 1689 */     this.geomLock.getLock();
/* 1690 */     this.validIndexCount = paramInt;
/* 1691 */     this.maxCoordIndex = i;
/* 1692 */     if ((this.vertexFormat & 0x200) == 0) {
/* 1693 */       this.maxColorIndex = j;
/* 1694 */       if ((this.vertexFormat & 0x460) != 0) {
/* 1695 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1696 */           this.maxTexCoordIndices[b] = arrayOfInt1[b];
/*      */         }
/*      */       }
/* 1699 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 1700 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/* 1701 */           this.maxVertexAttrIndices[b] = arrayOfInt2[b];
/*      */         }
/*      */       }
/* 1704 */       this.maxNormalIndex = k;
/*      */     } else {
/*      */       
/* 1707 */       this.maxColorIndex = this.maxCoordIndex;
/* 1708 */       this.maxNormalIndex = this.maxCoordIndex;
/* 1709 */       if ((this.vertexFormat & 0x460) != 0) {
/* 1710 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1711 */           this.maxTexCoordIndices[b] = this.maxCoordIndex;
/*      */         }
/*      */       }
/* 1714 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 1715 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/* 1716 */           this.maxVertexAttrIndices[b] = this.maxCoordIndex;
/*      */         }
/*      */       }
/*      */     } 
/* 1720 */     this.geomLock.unLock();
/*      */ 
/*      */     
/* 1723 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/* 1724 */       sendDataChangedMessage(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void setInitialIndexIndex(int paramInt) {
/* 1730 */     if (paramInt + this.validIndexCount > this.indexCount) {
/* 1731 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryArray22"));
/*      */     }
/* 1733 */     if ((this.vertexFormat & 0x2000) != 0 && 
/* 1734 */       this.indexCoord != null && this.indexCoord.length < paramInt + this.validIndexCount) {
/* 1735 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryArray33"));
/*      */     }
/*      */ 
/*      */     
/* 1739 */     int i = 0;
/* 1740 */     int j = 0;
/* 1741 */     int k = 0;
/* 1742 */     int[] arrayOfInt1 = null;
/* 1743 */     int[] arrayOfInt2 = null;
/*      */     
/* 1745 */     i = computeMaxIndex(paramInt, this.validIndexCount, this.indexCoord);
/* 1746 */     doErrorCheck(i);
/* 1747 */     if ((this.vertexFormat & 0x200) == 0) {
/* 1748 */       if ((this.vertexFormat & 0x4) != 0) {
/* 1749 */         j = computeMaxIndex(paramInt, this.validIndexCount, this.indexColor);
/* 1750 */         doColorCheck(j);
/*      */       } 
/* 1752 */       if ((this.vertexFormat & 0x460) != 0) {
/* 1753 */         arrayOfInt1 = new int[this.texCoordSetCount];
/* 1754 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1755 */           arrayOfInt1[b] = computeMaxIndex(paramInt, this.validIndexCount, this.indexTexCoord[b]);
/*      */           
/* 1757 */           doTexCoordCheck(arrayOfInt1[b], b);
/*      */         } 
/*      */       } 
/* 1760 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 1761 */         arrayOfInt2 = new int[this.vertexAttrCount];
/* 1762 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/* 1763 */           arrayOfInt2[b] = computeMaxIndex(paramInt, this.validIndexCount, this.indexVertexAttr[b]);
/*      */ 
/*      */           
/* 1766 */           doVertexAttrCheck(arrayOfInt2[b], b);
/*      */         } 
/*      */       } 
/* 1769 */       if ((this.vertexFormat & 0x2) != 0) {
/* 1770 */         k = computeMaxIndex(paramInt, this.validIndexCount, this.indexNormal);
/* 1771 */         doNormalCheck(k);
/*      */       } 
/*      */     } 
/*      */     
/* 1775 */     this.geomLock.getLock();
/* 1776 */     this.dirtyFlag |= 0x20;
/* 1777 */     this.initialIndexIndex = paramInt;
/* 1778 */     this.maxCoordIndex = i;
/* 1779 */     if ((this.vertexFormat & 0x200) == 0) {
/* 1780 */       this.maxColorIndex = j;
/* 1781 */       if ((this.vertexFormat & 0x460) != 0) {
/* 1782 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1783 */           this.maxTexCoordIndices[b] = arrayOfInt1[b];
/*      */         }
/*      */       }
/* 1786 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 1787 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/* 1788 */           this.maxVertexAttrIndices[b] = arrayOfInt2[b];
/*      */         }
/*      */       }
/* 1791 */       this.maxNormalIndex = k;
/*      */     } else {
/*      */       
/* 1794 */       this.maxColorIndex = this.maxCoordIndex;
/* 1795 */       this.maxNormalIndex = this.maxCoordIndex;
/* 1796 */       if ((this.vertexFormat & 0x460) != 0) {
/* 1797 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 1798 */           this.maxTexCoordIndices[b] = this.maxCoordIndex;
/*      */         }
/*      */       }
/* 1801 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 1802 */         for (byte b = 0; b < this.vertexAttrCount; b++) {
/* 1803 */           this.maxVertexAttrIndices[b] = this.maxCoordIndex;
/*      */         }
/*      */       }
/*      */     } 
/* 1807 */     this.geomLock.unLock();
/*      */ 
/*      */     
/* 1810 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/* 1811 */       sendDataChangedMessage(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/* 1816 */   int getInitialIndexIndex() { return this.initialIndexIndex; }
/*      */ 
/*      */ 
/*      */   
/* 1820 */   int getValidIndexCount() { return this.validIndexCount; }
/*      */ 
/*      */   
/*      */   void handleFrequencyChange(int paramInt) {
/* 1824 */     if (paramInt == 10 || ((this.vertexFormat & 0x200) == 0 && (this.vertexFormat & 0x4) != 0 && paramInt == 12) || ((this.vertexFormat & 0x200) == 0 && (this.vertexFormat & 0x2) != 0 && paramInt == 14) || ((this.vertexFormat & 0x200) == 0 && (this.vertexFormat & 0x1000) != 0 && paramInt == 25) || ((this.vertexFormat & 0x200) == 0 && (this.vertexFormat & 0x460) != 0 && paramInt == 16)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1838 */       setFrequencyChangeMask(paramInt, 1);
/*      */     } else {
/*      */       
/* 1841 */       super.handleFrequencyChange(paramInt);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedGeometryArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */