/*     */ package javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.internal.FloatBufferWrapper;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.TexCoord2f;
/*     */ import javax.vecmath.TexCoord3f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class GeometryStripArrayRetained
/*     */   extends GeometryArrayRetained
/*     */ {
/*     */   int[] stripVertexCounts;
/*     */   int[] stripStartVertexIndices;
/*     */   int[] stripStartOffsetIndices;
/*     */   int[] compileNumStrips;
/*     */   int[] compileStripCountOffset;
/*     */   
/*     */   void setStripVertexCounts(int[] paramArrayOfInt) {
/*  50 */     boolean bool = false;
/*     */     
/*  52 */     int i = paramArrayOfInt.length, j = 0; byte b;
/*  53 */     for (b = 0; b < i; b++) {
/*  54 */       j += paramArrayOfInt[b];
/*  55 */       if (this instanceof LineStripArrayRetained) {
/*  56 */         if (paramArrayOfInt[b] < 2) {
/*  57 */           throw new IllegalArgumentException(J3dI18N.getString("LineStripArrayRetained1"));
/*     */         }
/*     */       }
/*  60 */       else if (this instanceof TriangleStripArrayRetained) {
/*  61 */         if (paramArrayOfInt[b] < 3) {
/*  62 */           throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArrayRetained1"));
/*     */         }
/*     */       }
/*  65 */       else if (this instanceof TriangleFanArrayRetained && 
/*  66 */         paramArrayOfInt[b] < 3) {
/*  67 */         throw new IllegalArgumentException(J3dI18N.getString("TriangleFanArrayRetained1"));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  72 */     if (this.initialVertexIndex + j > this.vertexCount) {
/*  73 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryStripArray3"));
/*     */     }
/*  75 */     if (this.initialCoordIndex + j > this.vertexCount) {
/*  76 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryStripArray7"));
/*     */     }
/*  78 */     if (this.initialColorIndex + j > this.vertexCount) {
/*  79 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryStripArray4"));
/*     */     }
/*  81 */     if (this.initialNormalIndex + j > this.vertexCount) {
/*  82 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryStripArray5"));
/*     */     }
/*  84 */     if ((this.vertexFormat & 0x460) != 0 && (
/*  85 */       this.vertexFormat & (0x80 | this.vertexFormat & 0x100)) == 128) {
/*  86 */       for (b = 0; b < this.texCoordSetCount; b++) {
/*  87 */         if (this.initialTexCoordIndex[b] + j > this.vertexCount) {
/*  88 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryStripArray6"));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  94 */     if ((this.vertexFormat & 0x1000) != 0 && (
/*  95 */       this.vertexFormat & (0x80 | this.vertexFormat & 0x100)) == 128) {
/*  96 */       for (b = 0; b < this.vertexAttrCount; b++) {
/*  97 */         if (this.initialVertexAttrIndex[b] + j > this.vertexCount) {
/*  98 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryStripArray8"));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.geomLock.getLock();
/* 106 */     this.dirtyFlag |= 0x40;
/* 107 */     this.validVertexCount = j;
/* 108 */     this.stripVertexCounts = new int[i];
/* 109 */     this.stripStartVertexIndices = new int[i];
/* 110 */     this.stripStartOffsetIndices = new int[i];
/* 111 */     this.stripStartOffsetIndices[0] = 0;
/* 112 */     if ((this.vertexFormat & (0x80 | this.vertexFormat & 0x100)) == 128) {
/* 113 */       this.stripStartVertexIndices[0] = this.initialCoordIndex;
/* 114 */       bool = ((this.vertexType & 0xF) == 0);
/*     */     } else {
/*     */       
/* 117 */       this.stripStartVertexIndices[0] = this.initialVertexIndex;
/* 118 */       if ((this.vertexFormat & 0x100) != 0) {
/* 119 */         if ((this.vertexFormat & 0x800) != 0) {
/* 120 */           bool = (this.interLeavedVertexData == null);
/*     */         } else {
/*     */           
/* 123 */           bool = (this.interleavedFloatBufferImpl == null);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 128 */     for (b = 0; b < i - 1; b++) {
/* 129 */       this.stripVertexCounts[b] = paramArrayOfInt[b];
/* 130 */       this.stripStartVertexIndices[b + 1] = this.stripStartVertexIndices[b] + paramArrayOfInt[b];
/*     */       
/* 132 */       this.stripStartOffsetIndices[b + 1] = this.stripStartOffsetIndices[b] + paramArrayOfInt[b];
/*     */     } 
/* 134 */     this.stripVertexCounts[i - 1] = paramArrayOfInt[i - 1];
/*     */     
/* 136 */     this.geomLock.unLock();
/* 137 */     if (!this.inUpdater && this.source != null && this.source.isLive()) {
/* 138 */       processCoordsChanged(bool);
/* 139 */       sendDataChangedMessage(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   void unIndexify(IndexedGeometryStripArrayRetained paramIndexedGeometryStripArrayRetained) {
/* 144 */     if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x800) == 0) {
/* 145 */       unIndexifyJavaArray(paramIndexedGeometryStripArrayRetained);
/*     */     } else {
/*     */       
/* 148 */       unIndexifyNIOBuffer(paramIndexedGeometryStripArrayRetained);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void unIndexifyJavaArray(IndexedGeometryStripArrayRetained paramIndexedGeometryStripArrayRetained) {
/* 153 */     int i = 0, j = 0;
/* 154 */     int k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 155 */     byte b = 0;
/* 156 */     float[] arrayOfFloat = null;
/*     */     
/* 158 */     if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x80) == 0 || (paramIndexedGeometryStripArrayRetained.vertexFormat & 0x100) != 0) {
/*     */ 
/*     */       
/* 161 */       if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x80) == 0) {
/* 162 */         arrayOfFloat = paramIndexedGeometryStripArrayRetained.vertexData;
/* 163 */         if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x4) != 0) {
/* 164 */           b = 4;
/*     */         }
/* 166 */       } else if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x100) != 0) {
/* 167 */         arrayOfFloat = paramIndexedGeometryStripArrayRetained.interLeavedVertexData;
/* 168 */         if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 169 */           b = 4;
/* 170 */         } else if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x4) != 0) {
/* 171 */           b = 3;
/*     */         } 
/*     */       } 
/* 174 */       for (byte b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 175 */         for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 176 */           int n = m + k;
/* 177 */           if ((this.vertexFormat & 0x2) != 0) {
/* 178 */             System.arraycopy(arrayOfFloat, paramIndexedGeometryStripArrayRetained.indexNormal[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.normalOffset, this.vertexData, i + this.normalOffset, 3);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 183 */           if (b == 4) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 193 */             System.arraycopy(arrayOfFloat, paramIndexedGeometryStripArrayRetained.indexColor[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.colorOffset, this.vertexData, i + this.colorOffset, b);
/*     */           
/*     */           }
/* 196 */           else if (b == 3) {
/* 197 */             System.arraycopy(arrayOfFloat, paramIndexedGeometryStripArrayRetained.indexColor[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.colorOffset, this.vertexData, i + this.colorOffset, b);
/*     */ 
/*     */             
/* 200 */             this.vertexData[i + this.colorOffset + 3] = 1.0F;
/*     */           } 
/*     */           
/* 203 */           if ((this.vertexFormat & 0x460) != 0) {
/* 204 */             for (byte b2 = 0; b2 < this.texCoordSetCount; b2++) {
/* 205 */               System.arraycopy(arrayOfFloat, paramIndexedGeometryStripArrayRetained.indexTexCoord[b2][n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.textureOffset + paramIndexedGeometryStripArrayRetained.texCoordSetMapOffset[b2], this.vertexData, i + this.textureOffset + this.texCoordSetMapOffset[b2], this.texCoordStride);
/*     */             }
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 216 */           if ((this.vertexFormat & 0x1000) != 0) {
/* 217 */             for (byte b2 = 0; b2 < this.vertexAttrCount; b2++) {
/* 218 */               System.arraycopy(arrayOfFloat, paramIndexedGeometryStripArrayRetained.indexVertexAttr[b2][n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.vertexAttrOffsets[b2], this.vertexData, i + this.vertexAttrOffsets[b2], this.vertexAttrSizes[b2]);
/*     */             }
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 226 */           if ((this.vertexFormat & true) != 0) {
/* 227 */             System.arraycopy(arrayOfFloat, paramIndexedGeometryStripArrayRetained.indexCoord[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.coordinateOffset, this.vertexData, i + this.coordinateOffset, 3);
/*     */           }
/*     */ 
/*     */           
/* 231 */           i += this.stride;
/*     */         } 
/* 233 */         k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */       } 
/*     */     } else {
/*     */       
/* 237 */       if ((this.vertexFormat & 0x2) != 0) {
/* 238 */         byte b1; k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 239 */         i = this.normalOffset;
/* 240 */         switch (paramIndexedGeometryStripArrayRetained.vertexType & 0xC00) {
/*     */           case 1024:
/* 242 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 243 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 244 */                 int n = m + k;
/* 245 */                 System.arraycopy(paramIndexedGeometryStripArrayRetained.floatRefNormals, paramIndexedGeometryStripArrayRetained.indexNormal[n] * 3, this.vertexData, i, 3);
/*     */ 
/*     */ 
/*     */                 
/* 249 */                 i += this.stride;
/*     */               } 
/* 251 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 2048:
/* 255 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 256 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 257 */                 int n = paramIndexedGeometryStripArrayRetained.indexNormal[m + k];
/* 258 */                 this.vertexData[i] = (paramIndexedGeometryStripArrayRetained.v3fRefNormals[n]).x;
/* 259 */                 this.vertexData[i + 1] = (paramIndexedGeometryStripArrayRetained.v3fRefNormals[n]).y;
/* 260 */                 this.vertexData[i + 2] = (paramIndexedGeometryStripArrayRetained.v3fRefNormals[n]).z;
/* 261 */                 i += this.stride;
/*     */               } 
/* 263 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 271 */       if ((this.vertexFormat & 0x4) != 0) {
/* 272 */         byte b1; k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 273 */         i = this.colorOffset;
/* 274 */         int m = 3;
/* 275 */         if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 276 */           m = 4;
/*     */         }
/* 278 */         switch (paramIndexedGeometryStripArrayRetained.vertexType & 0x3F0) {
/*     */           case 16:
/* 280 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 281 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 282 */                 int i1 = n + k;
/*     */                 
/* 284 */                 if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 285 */                   System.arraycopy(paramIndexedGeometryStripArrayRetained.floatRefColors, paramIndexedGeometryStripArrayRetained.indexColor[i1] * m, this.vertexData, i, 4);
/*     */                 
/*     */                 }
/*     */                 else {
/*     */ 
/*     */                   
/* 291 */                   System.arraycopy(paramIndexedGeometryStripArrayRetained.floatRefColors, paramIndexedGeometryStripArrayRetained.indexColor[i1] * m, this.vertexData, i, 3);
/*     */ 
/*     */ 
/*     */                   
/* 295 */                   this.vertexData[i + 3] = 1.0F;
/*     */                 } 
/* 297 */                 i += this.stride;
/*     */               } 
/* 299 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 32:
/* 303 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 304 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 305 */                 int i1 = paramIndexedGeometryStripArrayRetained.indexColor[n + k] * m;
/* 306 */                 this.vertexData[i] = (paramIndexedGeometryStripArrayRetained.byteRefColors[i1] & 0xFF) * 0.003921569F;
/* 307 */                 this.vertexData[i + 1] = (paramIndexedGeometryStripArrayRetained.byteRefColors[i1 + 1] & 0xFF) * 0.003921569F;
/* 308 */                 this.vertexData[i + 2] = (paramIndexedGeometryStripArrayRetained.byteRefColors[i1 + 2] & 0xFF) * 0.003921569F;
/* 309 */                 if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 310 */                   this.vertexData[i + 3] = (paramIndexedGeometryStripArrayRetained.byteRefColors[i1 + 3] & 0xFF) * 0.003921569F;
/*     */                 } else {
/*     */                   
/* 313 */                   this.vertexData[i + 3] = 1.0F;
/*     */                 } 
/* 315 */                 i += this.stride;
/*     */               } 
/* 317 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 64:
/* 321 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 322 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 323 */                 int i1 = paramIndexedGeometryStripArrayRetained.indexColor[n + k];
/* 324 */                 this.vertexData[i] = (paramIndexedGeometryStripArrayRetained.c3fRefColors[i1]).x;
/* 325 */                 this.vertexData[i + 1] = (paramIndexedGeometryStripArrayRetained.c3fRefColors[i1]).y;
/* 326 */                 this.vertexData[i + 2] = (paramIndexedGeometryStripArrayRetained.c3fRefColors[i1]).z;
/* 327 */                 this.vertexData[i + 3] = 1.0F;
/* 328 */                 i += this.stride;
/*     */               } 
/* 330 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 128:
/* 334 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 335 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 336 */                 int i1 = paramIndexedGeometryStripArrayRetained.indexColor[n + k];
/* 337 */                 this.vertexData[i] = (paramIndexedGeometryStripArrayRetained.c4fRefColors[i1]).x;
/* 338 */                 this.vertexData[i + 1] = (paramIndexedGeometryStripArrayRetained.c4fRefColors[i1]).y;
/* 339 */                 this.vertexData[i + 2] = (paramIndexedGeometryStripArrayRetained.c4fRefColors[i1]).z;
/* 340 */                 this.vertexData[i + 3] = (paramIndexedGeometryStripArrayRetained.c4fRefColors[i1]).w;
/* 341 */                 i += this.stride;
/*     */               } 
/* 343 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 256:
/* 347 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 348 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 349 */                 int i1 = paramIndexedGeometryStripArrayRetained.indexColor[n + k];
/* 350 */                 this.vertexData[i] = ((paramIndexedGeometryStripArrayRetained.c3bRefColors[i1]).x & 0xFF) * 0.003921569F;
/* 351 */                 this.vertexData[i + 1] = ((paramIndexedGeometryStripArrayRetained.c3bRefColors[i1]).y & 0xFF) * 0.003921569F;
/* 352 */                 this.vertexData[i + 2] = ((paramIndexedGeometryStripArrayRetained.c3bRefColors[i1]).z & 0xFF) * 0.003921569F;
/* 353 */                 this.vertexData[i + 3] = 1.0F;
/*     */                 
/* 355 */                 i += this.stride;
/*     */               } 
/* 357 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 512:
/* 361 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 362 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 363 */                 int i1 = paramIndexedGeometryStripArrayRetained.indexColor[n + k];
/* 364 */                 this.vertexData[i] = ((paramIndexedGeometryStripArrayRetained.c4bRefColors[i1]).x & 0xFF) * 0.003921569F;
/* 365 */                 this.vertexData[i + 1] = ((paramIndexedGeometryStripArrayRetained.c4bRefColors[i1]).y & 0xFF) * 0.003921569F;
/* 366 */                 this.vertexData[i + 2] = ((paramIndexedGeometryStripArrayRetained.c4bRefColors[i1]).z & 0xFF) * 0.003921569F;
/* 367 */                 this.vertexData[i + 3] = ((paramIndexedGeometryStripArrayRetained.c4bRefColors[i1]).w & 0xFF) * 0.003921569F;
/* 368 */                 i += this.stride;
/*     */               } 
/* 370 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 378 */       if ((this.vertexFormat & 0x460) != 0) {
/* 379 */         byte b1; k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 380 */         i = this.textureOffset;
/* 381 */         switch (paramIndexedGeometryStripArrayRetained.vertexType & 0x7000) {
/*     */           case 4096:
/* 383 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 384 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 385 */                 int n = m + k;
/*     */                 
/* 387 */                 byte b2 = 0; j = i;
/* 388 */                 for (; b2 < this.texCoordSetCount; b2++) {
/* 389 */                   System.arraycopy(paramIndexedGeometryStripArrayRetained.refTexCoords[b2], paramIndexedGeometryStripArrayRetained.indexTexCoord[b2][n] * this.texCoordStride, this.vertexData, j, this.texCoordStride);
/*     */ 
/*     */ 
/*     */                   
/* 393 */                   j += this.texCoordStride;
/*     */                 } 
/* 395 */                 i += this.stride;
/*     */               } 
/* 397 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 8192:
/* 401 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 402 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 403 */                 int n = m + k;
/* 404 */                 byte b2 = 0; j = i;
/* 405 */                 for (; b2 < this.texCoordSetCount; b2++) {
/* 406 */                   int i1 = paramIndexedGeometryStripArrayRetained.indexTexCoord[b2][n];
/*     */                   
/* 408 */                   this.vertexData[j] = ((TexCoord2f[])paramIndexedGeometryStripArrayRetained.refTexCoords[b2][i1]).x;
/*     */                   
/* 410 */                   this.vertexData[j + 1] = ((TexCoord2f[])paramIndexedGeometryStripArrayRetained.refTexCoords[b2][i1]).y;
/*     */                   
/* 412 */                   j += this.texCoordStride;
/*     */                 } 
/* 414 */                 i += this.stride;
/*     */               } 
/* 416 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 16384:
/* 420 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 421 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 422 */                 int n = m + k;
/* 423 */                 byte b2 = 0; j = i;
/* 424 */                 for (; b2 < this.texCoordSetCount; b2++) {
/* 425 */                   int i1 = paramIndexedGeometryStripArrayRetained.indexTexCoord[b2][n];
/*     */                   
/* 427 */                   this.vertexData[j] = ((TexCoord3f[])paramIndexedGeometryStripArrayRetained.refTexCoords[b2][i1]).x;
/*     */                   
/* 429 */                   this.vertexData[j + 1] = ((TexCoord3f[])paramIndexedGeometryStripArrayRetained.refTexCoords[b2][i1]).y;
/*     */                   
/* 431 */                   this.vertexData[j + 2] = ((TexCoord3f[])paramIndexedGeometryStripArrayRetained.refTexCoords[b2][i1]).z;
/*     */                   
/* 433 */                   j += this.texCoordStride;
/*     */                 } 
/* 435 */                 i += this.stride;
/*     */               } 
/* 437 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 446 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 447 */         byte b1; k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 448 */         i = 0;
/* 449 */         switch (paramIndexedGeometryStripArrayRetained.vertexType & 0x8000) {
/*     */           case 32768:
/* 451 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 452 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 453 */                 int n = m + k;
/*     */                 
/* 455 */                 for (byte b2 = 0; b2 < this.vertexAttrCount; b2++) {
/* 456 */                   System.arraycopy(paramIndexedGeometryStripArrayRetained.floatRefVertexAttrs[b2], paramIndexedGeometryStripArrayRetained.indexVertexAttr[b2][n] * this.vertexAttrSizes[b2], this.vertexData, i + this.vertexAttrOffsets[b2], this.vertexAttrSizes[b2]);
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 462 */                 i += this.stride;
/*     */               } 
/* 464 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */         } 
/*     */       
/*     */       } 
/* 470 */       if ((this.vertexFormat & true) != 0) {
/* 471 */         byte b1; i = this.coordinateOffset;
/* 472 */         k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 473 */         switch (paramIndexedGeometryStripArrayRetained.vertexType & 0xF) {
/*     */           case 1:
/* 475 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 476 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 477 */                 int n = m + k;
/* 478 */                 System.arraycopy(paramIndexedGeometryStripArrayRetained.floatRefCoords, paramIndexedGeometryStripArrayRetained.indexCoord[n] * 3, this.vertexData, i, 3);
/*     */ 
/*     */ 
/*     */                 
/* 482 */                 i += this.stride;
/*     */               } 
/* 484 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 488 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 489 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 490 */                 int n = paramIndexedGeometryStripArrayRetained.indexCoord[m + k] * 3;
/* 491 */                 this.vertexData[i] = (float)paramIndexedGeometryStripArrayRetained.doubleRefCoords[n];
/* 492 */                 this.vertexData[i + 1] = (float)paramIndexedGeometryStripArrayRetained.doubleRefCoords[n + 1];
/* 493 */                 this.vertexData[i + 2] = (float)paramIndexedGeometryStripArrayRetained.doubleRefCoords[n + 2];
/* 494 */                 i += this.stride;
/*     */               } 
/* 496 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 4:
/* 500 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 501 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 502 */                 int n = paramIndexedGeometryStripArrayRetained.indexCoord[m + k];
/* 503 */                 this.vertexData[i] = (paramIndexedGeometryStripArrayRetained.p3fRefCoords[n]).x;
/* 504 */                 this.vertexData[i + 1] = (paramIndexedGeometryStripArrayRetained.p3fRefCoords[n]).y;
/* 505 */                 this.vertexData[i + 2] = (paramIndexedGeometryStripArrayRetained.p3fRefCoords[n]).z;
/* 506 */                 i += this.stride;
/*     */               } 
/* 508 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 8:
/* 512 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 513 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 514 */                 int n = paramIndexedGeometryStripArrayRetained.indexCoord[m + k];
/* 515 */                 this.vertexData[i] = (float)(paramIndexedGeometryStripArrayRetained.p3dRefCoords[n]).x;
/* 516 */                 this.vertexData[i + 1] = (float)(paramIndexedGeometryStripArrayRetained.p3dRefCoords[n]).y;
/* 517 */                 this.vertexData[i + 2] = (float)(paramIndexedGeometryStripArrayRetained.p3dRefCoords[n]).z;
/* 518 */                 i += this.stride;
/*     */               } 
/* 520 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void unIndexifyNIOBuffer(IndexedGeometryStripArrayRetained paramIndexedGeometryStripArrayRetained) {
/* 531 */     int i = 0, j = 0;
/* 532 */     int k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 533 */     byte b = 0;
/*     */ 
/*     */ 
/*     */     
/* 537 */     if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x100) != 0) {
/* 538 */       if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 539 */         b = 4;
/* 540 */       } else if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x4) != 0) {
/* 541 */         b = 3;
/*     */       } 
/* 543 */       for (byte b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 544 */         for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 545 */           int n = m + k;
/* 546 */           if ((this.vertexFormat & 0x2) != 0) {
/* 547 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryStripArrayRetained.indexNormal[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.normalOffset);
/* 548 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.normalOffset, 3);
/*     */           } 
/* 550 */           if (b == 4) {
/* 551 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryStripArrayRetained.indexColor[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.colorOffset);
/* 552 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.colorOffset, b);
/* 553 */           } else if (b == 3) {
/* 554 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryStripArrayRetained.indexColor[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.colorOffset);
/* 555 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.colorOffset, b);
/* 556 */             this.vertexData[i + this.colorOffset + 3] = 1.0F;
/*     */           } 
/*     */           
/* 559 */           if ((this.vertexFormat & 0x460) != 0) {
/* 560 */             for (byte b2 = 0; b2 < this.texCoordSetCount; b2++) {
/* 561 */               paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryStripArrayRetained.indexTexCoord[b2][n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.textureOffset + paramIndexedGeometryStripArrayRetained.texCoordSetMapOffset[b2]);
/*     */ 
/*     */ 
/*     */               
/* 565 */               paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.textureOffset + this.texCoordSetMapOffset[b2], this.texCoordStride);
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 570 */           if ((this.vertexFormat & true) != 0) {
/* 571 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.position(paramIndexedGeometryStripArrayRetained.indexCoord[n] * paramIndexedGeometryStripArrayRetained.stride + paramIndexedGeometryStripArrayRetained.coordinateOffset);
/* 572 */             paramIndexedGeometryStripArrayRetained.interleavedFloatBufferImpl.get(this.vertexData, i + this.coordinateOffset, 3);
/*     */           } 
/* 574 */           i += this.stride;
/*     */         } 
/* 576 */         k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */       } 
/*     */     } else {
/*     */       
/* 580 */       if ((this.vertexFormat & 0x2) != 0) {
/* 581 */         k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 582 */         i = this.normalOffset;
/* 583 */         if ((paramIndexedGeometryStripArrayRetained.vertexType & 0xC00) != 0) {
/* 584 */           for (byte b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 585 */             for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 586 */               int n = m + k;
/* 587 */               paramIndexedGeometryStripArrayRetained.floatBufferRefNormals.position(paramIndexedGeometryStripArrayRetained.indexNormal[n] * 3);
/* 588 */               paramIndexedGeometryStripArrayRetained.floatBufferRefNormals.get(this.vertexData, i, 3);
/* 589 */               i += this.stride;
/*     */             } 
/* 591 */             k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 596 */       if ((this.vertexFormat & 0x4) != 0) {
/* 597 */         byte b1; k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 598 */         i = this.colorOffset;
/* 599 */         int m = 3;
/* 600 */         if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 601 */           m = 4;
/*     */         }
/* 603 */         switch (paramIndexedGeometryStripArrayRetained.vertexType & 0x3F0) {
/*     */           case 16:
/* 605 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 606 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 607 */                 int i1 = n + k;
/*     */                 
/* 609 */                 if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 610 */                   paramIndexedGeometryStripArrayRetained.floatBufferRefColors.position(paramIndexedGeometryStripArrayRetained.indexColor[i1] * m);
/* 611 */                   paramIndexedGeometryStripArrayRetained.floatBufferRefColors.get(this.vertexData, i, 4);
/*     */                 }
/*     */                 else {
/*     */                   
/* 615 */                   paramIndexedGeometryStripArrayRetained.floatBufferRefColors.position(paramIndexedGeometryStripArrayRetained.indexColor[i1] * m);
/* 616 */                   paramIndexedGeometryStripArrayRetained.floatBufferRefColors.get(this.vertexData, i, 3);
/*     */                   
/* 618 */                   this.vertexData[i + 3] = 1.0F;
/*     */                 } 
/* 620 */                 i += this.stride;
/*     */               } 
/* 622 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 32:
/* 626 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 627 */               for (int n = 0; n < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; n++) {
/* 628 */                 int i1 = paramIndexedGeometryStripArrayRetained.indexColor[n + k] * m;
/* 629 */                 this.vertexData[i] = (paramIndexedGeometryStripArrayRetained.byteBufferRefColors.get(i1) & 0xFF) * 0.003921569F;
/* 630 */                 this.vertexData[i + 1] = (paramIndexedGeometryStripArrayRetained.byteBufferRefColors.get(i1 + 1) & 0xFF) * 0.003921569F;
/* 631 */                 this.vertexData[i + 2] = (paramIndexedGeometryStripArrayRetained.byteBufferRefColors.get(i1 + 2) & 0xFF) * 0.003921569F;
/* 632 */                 if ((paramIndexedGeometryStripArrayRetained.vertexFormat & 0x8) != 0) {
/* 633 */                   this.vertexData[i + 3] = (paramIndexedGeometryStripArrayRetained.byteBufferRefColors.get(i1 + 3) & 0xFF) * 0.003921569F;
/*     */                 } else {
/*     */                   
/* 636 */                   this.vertexData[i + 3] = 1.0F;
/*     */                 } 
/* 638 */                 i += this.stride;
/*     */               } 
/* 640 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 648 */       if ((this.vertexFormat & 0x460) != 0) {
/* 649 */         k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 650 */         i = this.textureOffset;
/*     */         
/* 652 */         if ((paramIndexedGeometryStripArrayRetained.vertexType & 0x7000) != 0) {
/* 653 */           for (byte b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 654 */             for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 655 */               int n = m + k;
/*     */               
/* 657 */               byte b2 = 0; j = i;
/* 658 */               for (; b2 < this.texCoordSetCount; b2++) {
/* 659 */                 FloatBufferWrapper floatBufferWrapper = (FloatBufferWrapper)((J3DBuffer)paramIndexedGeometryStripArrayRetained.refTexCoordsBuffer[b2]).getBufferImpl();
/* 660 */                 floatBufferWrapper.position(paramIndexedGeometryStripArrayRetained.indexTexCoord[b2][n] * this.texCoordStride);
/* 661 */                 floatBufferWrapper.get(this.vertexData, j, this.texCoordStride);
/* 662 */                 j += this.texCoordStride;
/*     */               } 
/* 664 */               i += this.stride;
/*     */             } 
/* 666 */             k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 671 */       if ((this.vertexFormat & 0x1000) != 0) {
/* 672 */         k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 673 */         i = 0;
/* 674 */         if ((paramIndexedGeometryStripArrayRetained.vertexType & 0x8000) == 32768) {
/* 675 */           for (byte b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 676 */             for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 677 */               int n = m + k;
/*     */               
/* 679 */               for (byte b2 = 0; b2 < this.vertexAttrCount; b2++) {
/* 680 */                 int i1 = i + this.vertexAttrOffsets[b2];
/* 681 */                 FloatBufferWrapper floatBufferWrapper = paramIndexedGeometryStripArrayRetained.floatBufferRefVertexAttrs[b2];
/* 682 */                 floatBufferWrapper.position(paramIndexedGeometryStripArrayRetained.indexVertexAttr[b2][n] * this.vertexAttrSizes[b2]);
/* 683 */                 floatBufferWrapper.get(this.vertexData, i1, this.vertexAttrSizes[b2]);
/*     */               } 
/* 685 */               i += this.stride;
/*     */             } 
/* 687 */             k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 692 */       if ((this.vertexFormat & true) != 0) {
/* 693 */         byte b1; i = this.coordinateOffset;
/* 694 */         k = paramIndexedGeometryStripArrayRetained.initialIndexIndex;
/* 695 */         switch (paramIndexedGeometryStripArrayRetained.vertexType & 0xF) {
/*     */           case 1:
/* 697 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 698 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 699 */                 int n = m + k;
/* 700 */                 paramIndexedGeometryStripArrayRetained.floatBufferRefCoords.position(paramIndexedGeometryStripArrayRetained.indexCoord[n] * 3);
/* 701 */                 paramIndexedGeometryStripArrayRetained.floatBufferRefCoords.get(this.vertexData, i, 3);
/*     */                 
/* 703 */                 i += this.stride;
/*     */               } 
/* 705 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 709 */             for (b1 = 0; b1 < paramIndexedGeometryStripArrayRetained.stripIndexCounts.length; b1++) {
/* 710 */               for (int m = 0; m < paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1]; m++) {
/* 711 */                 int n = paramIndexedGeometryStripArrayRetained.indexCoord[m + k] * 3;
/* 712 */                 this.vertexData[i] = (float)paramIndexedGeometryStripArrayRetained.doubleBufferRefCoords.get(n);
/* 713 */                 this.vertexData[i + 1] = (float)paramIndexedGeometryStripArrayRetained.doubleBufferRefCoords.get(n + 1);
/* 714 */                 this.vertexData[i + 2] = (float)paramIndexedGeometryStripArrayRetained.doubleBufferRefCoords.get(n + 2);
/* 715 */                 i += this.stride;
/*     */               } 
/* 717 */               k += paramIndexedGeometryStripArrayRetained.stripIndexCounts[b1];
/*     */             } 
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
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
/* 734 */   int getNumStrips() { return this.stripVertexCounts.length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getStripVertexCounts(int[] paramArrayOfInt) {
/* 743 */     int i = this.stripVertexCounts.length;
/*     */     
/* 745 */     for (byte b = 0; b < i; b++)
/*     */     {
/* 747 */       paramArrayOfInt[b] = this.stripVertexCounts[b];
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void getStripVertexCounts(int paramInt, int[] paramArrayOfInt) {
/* 753 */     int i = this.compileStripCountOffset[paramInt];
/* 754 */     int j = this.compileNumStrips[paramInt];
/* 755 */     System.arraycopy(this.stripVertexCounts, i, paramArrayOfInt, 0, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 760 */   int getNumStrips(int paramInt) { return this.compileNumStrips[paramInt]; }
/*     */ 
/*     */ 
/*     */   
/*     */   void mergeGeometryArrays(ArrayList paramArrayList) {
/* 765 */     int i = paramArrayList.size();
/* 766 */     int j = 0;
/*     */     
/*     */     int k;
/* 769 */     for (k = 0; k < i; k++) {
/* 770 */       j += ((GeometryStripArrayRetained)paramArrayList.get(k)).stripVertexCounts.length;
/*     */     }
/*     */     
/* 773 */     this.stripVertexCounts = new int[j];
/* 774 */     this.stripStartVertexIndices = new int[j];
/* 775 */     this.stripStartOffsetIndices = new int[j];
/* 776 */     k = 0;
/* 777 */     int m = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 783 */     this.compileNumStrips = new int[i];
/* 784 */     this.compileStripCountOffset = new int[i];
/* 785 */     for (byte b = 0; b < i; b++) {
/* 786 */       GeometryStripArrayRetained geometryStripArrayRetained = (GeometryStripArrayRetained)paramArrayList.get(b);
/*     */       
/* 788 */       int[] arrayOfInt1 = geometryStripArrayRetained.stripVertexCounts;
/* 789 */       int[] arrayOfInt2 = geometryStripArrayRetained.stripStartVertexIndices;
/* 790 */       int[] arrayOfInt3 = geometryStripArrayRetained.stripStartOffsetIndices;
/* 791 */       int n = arrayOfInt1.length;
/* 792 */       this.compileNumStrips[b] = n;
/* 793 */       this.compileStripCountOffset[b] = k;
/* 794 */       System.arraycopy(arrayOfInt1, 0, this.stripVertexCounts, k, n);
/*     */ 
/*     */ 
/*     */       
/* 798 */       for (byte b1 = 0; b1 < n; b1++) {
/* 799 */         this.stripStartVertexIndices[b1 + k] = arrayOfInt2[b1] + m;
/*     */         
/* 801 */         this.stripStartOffsetIndices[b1 + k] = arrayOfInt3[b1] + m;
/*     */       } 
/*     */       
/* 804 */       k += n;
/* 805 */       m += geometryStripArrayRetained.validVertexCount;
/*     */     } 
/*     */     
/* 808 */     this.validVertexCount = m;
/*     */ 
/*     */     
/* 811 */     super.mergeGeometryArrays(paramArrayList);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\GeometryStripArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */