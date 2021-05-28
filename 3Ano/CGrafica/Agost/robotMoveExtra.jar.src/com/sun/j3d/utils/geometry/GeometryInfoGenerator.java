/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import com.sun.j3d.internal.BufferWrapper;
/*     */ import com.sun.j3d.internal.ByteBufferWrapper;
/*     */ import com.sun.j3d.internal.DoubleBufferWrapper;
/*     */ import com.sun.j3d.internal.FloatBufferWrapper;
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import javax.media.j3d.GeometryArray;
/*     */ import javax.media.j3d.GeometryStripArray;
/*     */ import javax.media.j3d.IndexedGeometryArray;
/*     */ import javax.media.j3d.IndexedGeometryStripArray;
/*     */ import javax.media.j3d.J3DBuffer;
/*     */ import javax.vecmath.Color3b;
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Color4b;
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.TexCoord2f;
/*     */ import javax.vecmath.TexCoord3f;
/*     */ import javax.vecmath.TexCoord4f;
/*     */ import javax.vecmath.Vector3f;
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
/*     */ class GeometryInfoGenerator
/*     */ {
/*     */   public static void create(GeometryInfo paramGeometryInfo, GeometryArray paramGeometryArray) {
/*  78 */     if (paramGeometryArray instanceof GeometryStripArray)
/*  79 */     { create(paramGeometryInfo, (GeometryStripArray)paramGeometryArray); }
/*  80 */     else if (paramGeometryArray instanceof javax.media.j3d.TriangleArray)
/*  81 */     { paramGeometryInfo.reset(1);
/*  82 */       processGeometryArray(paramGeometryInfo, paramGeometryArray); }
/*  83 */     else if (paramGeometryArray instanceof javax.media.j3d.QuadArray)
/*  84 */     { paramGeometryInfo.reset(2);
/*  85 */       processGeometryArray(paramGeometryInfo, paramGeometryArray); }
/*  86 */     else if (paramGeometryArray instanceof IndexedGeometryArray)
/*  87 */     { create(paramGeometryInfo, (IndexedGeometryArray)paramGeometryArray); }
/*  88 */     else { throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfoGenerator0")); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void create(GeometryInfo paramGeometryInfo, GeometryStripArray paramGeometryStripArray) {
/*  97 */     if (paramGeometryStripArray instanceof javax.media.j3d.TriangleFanArray)
/*  98 */     { paramGeometryInfo.reset(3); }
/*  99 */     else if (paramGeometryStripArray instanceof javax.media.j3d.TriangleStripArray)
/* 100 */     { paramGeometryInfo.reset(4); }
/* 101 */     else { throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryInfoGenerator0")); }
/*     */ 
/*     */     
/* 104 */     processGeometryArray(paramGeometryInfo, paramGeometryStripArray);
/* 105 */     processStripArray(paramGeometryInfo, paramGeometryStripArray);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void create(GeometryInfo paramGeometryInfo, IndexedGeometryArray paramIndexedGeometryArray) {
/* 113 */     if (paramIndexedGeometryArray instanceof javax.media.j3d.IndexedQuadArray) {
/* 114 */       paramGeometryInfo.reset(2);
/* 115 */     } else if (paramIndexedGeometryArray instanceof javax.media.j3d.IndexedTriangleArray) {
/* 116 */       paramGeometryInfo.reset(1);
/* 117 */     } else if (paramIndexedGeometryArray instanceof javax.media.j3d.IndexedTriangleFanArray) {
/* 118 */       paramGeometryInfo.reset(3);
/* 119 */       processIndexStripArray(paramGeometryInfo, (IndexedGeometryStripArray)paramIndexedGeometryArray);
/* 120 */     } else if (paramIndexedGeometryArray instanceof javax.media.j3d.IndexedTriangleStripArray) {
/* 121 */       paramGeometryInfo.reset(4);
/* 122 */       processIndexStripArray(paramGeometryInfo, (IndexedGeometryStripArray)paramIndexedGeometryArray);
/*     */     } 
/*     */     
/* 125 */     processGeometryArray(paramGeometryInfo, paramIndexedGeometryArray);
/* 126 */     processIndexedArray(paramGeometryInfo, paramIndexedGeometryArray);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void processGeometryArray(GeometryInfo paramGeometryInfo, GeometryArray paramGeometryArray) {
/* 135 */     int k, i = paramGeometryArray.getVertexFormat();
/* 136 */     int j = paramGeometryArray.getTexCoordSetCount();
/*     */ 
/*     */ 
/*     */     
/* 140 */     if (paramGeometryArray instanceof GeometryStripArray)
/*     */     
/* 142 */     { GeometryStripArray geometryStripArray = (GeometryStripArray)paramGeometryArray;
/* 143 */       int[] arrayOfInt = new int[geometryStripArray.getNumStrips()];
/* 144 */       geometryStripArray.getStripVertexCounts(arrayOfInt);
/* 145 */       k = 0;
/* 146 */       for (byte b = 0; b < arrayOfInt.length; b++) {
/* 147 */         k += arrayOfInt[b];
/*     */       } }
/* 149 */     else if (paramGeometryArray instanceof IndexedGeometryArray)
/* 150 */     { k = paramGeometryArray.getVertexCount(); }
/* 151 */     else { k = paramGeometryArray.getValidVertexCount(); }
/*     */     
/* 153 */     if ((i & 0x100) != 0) {
/*     */       float[] arrayOfFloat;
/*     */       byte b2;
/* 156 */       int m = 3;
/* 157 */       if ((i & 0x2) != 0) m += 3; 
/* 158 */       if ((i & 0xC) == 12)
/* 159 */       { m += 4; }
/* 160 */       else if ((i & 0x4) != 0) { m += 3; }
/* 161 */        if ((i & 0x20) != 0) {
/* 162 */         m += 2 * j;
/* 163 */       } else if ((i & 0x40) != 0) {
/* 164 */         m += 3 * j;
/* 165 */       } else if ((i & 0x400) != 0) {
/* 166 */         m += 4 * j;
/*     */       } 
/*     */       
/* 169 */       if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 170 */       { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 171 */       else { b2 = 0; }
/*     */ 
/*     */       
/* 174 */       if ((i & 0x800) != 0)
/* 175 */       { J3DBuffer j3DBuffer = paramGeometryArray.getInterleavedVertexBuffer();
/* 176 */         FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 177 */         arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 178 */         floatBufferWrapper.position(0);
/* 179 */         floatBufferWrapper.get(arrayOfFloat); }
/* 180 */       else { arrayOfFloat = paramGeometryArray.getInterleavedVertices(); }
/*     */       
/* 182 */       int n = 0;
/* 183 */       if ((i & 0x20) != 0) {
/* 184 */         paramGeometryInfo.setTextureCoordinateParams(j, 2);
/* 185 */         int[] arrayOfInt = new int[paramGeometryArray.getTexCoordSetMapLength()];
/* 186 */         paramGeometryArray.getTexCoordSetMap(arrayOfInt);
/* 187 */         paramGeometryInfo.setTexCoordSetMap(arrayOfInt);
/* 188 */         for (byte b = 0; b < j; b++) {
/* 189 */           TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[k];
/* 190 */           for (byte b3 = 0; b3 < k; b3++) {
/* 191 */             arrayOfTexCoord2f[b3] = new TexCoord2f(arrayOfFloat[m * (b3 + b2) + n], arrayOfFloat[m * (b3 + b2) + n + 1]);
/*     */           }
/*     */           
/* 194 */           paramGeometryInfo.setTextureCoordinates(b, arrayOfTexCoord2f);
/* 195 */           n += 2;
/*     */         } 
/* 197 */       } else if ((i & 0x40) != 0) {
/* 198 */         paramGeometryInfo.setTextureCoordinateParams(j, 3);
/* 199 */         int[] arrayOfInt = new int[paramGeometryArray.getTexCoordSetMapLength()];
/* 200 */         paramGeometryArray.getTexCoordSetMap(arrayOfInt);
/* 201 */         paramGeometryInfo.setTexCoordSetMap(arrayOfInt);
/* 202 */         for (byte b = 0; b < j; b++) {
/* 203 */           TexCoord3f[] arrayOfTexCoord3f = new TexCoord3f[k];
/* 204 */           for (byte b3 = 0; b3 < k; b3++) {
/* 205 */             arrayOfTexCoord3f[b3] = new TexCoord3f(arrayOfFloat[m * (b3 + b2) + n], arrayOfFloat[m * (b3 + b2) + n + 1], arrayOfFloat[m * (b3 + b2) + n + 2]);
/*     */           }
/*     */ 
/*     */           
/* 209 */           paramGeometryInfo.setTextureCoordinates(b, arrayOfTexCoord3f);
/* 210 */           n += 3;
/*     */         } 
/* 212 */       } else if ((i & 0x400) != 0) {
/* 213 */         paramGeometryInfo.setTextureCoordinateParams(j, 4);
/* 214 */         int[] arrayOfInt = new int[paramGeometryArray.getTexCoordSetMapLength()];
/* 215 */         paramGeometryArray.getTexCoordSetMap(arrayOfInt);
/* 216 */         paramGeometryInfo.setTexCoordSetMap(arrayOfInt);
/* 217 */         for (byte b = 0; b < j; b++) {
/* 218 */           TexCoord4f[] arrayOfTexCoord4f = new TexCoord4f[k];
/* 219 */           for (byte b3 = 0; b3 < k; b3++) {
/* 220 */             arrayOfTexCoord4f[b3] = new TexCoord4f(arrayOfFloat[m * (b3 + b2) + n], arrayOfFloat[m * (b3 + b2) + n + 1], arrayOfFloat[m * (b3 + b2) + n + 2], arrayOfFloat[m * (b3 + b2) + n + 3]);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 225 */           paramGeometryInfo.setTextureCoordinates(b, arrayOfTexCoord4f);
/* 226 */           n += 4;
/*     */         } 
/*     */       } 
/*     */       
/* 230 */       if ((i & 0xC) == 12) {
/* 231 */         Color4f[] arrayOfColor4f = new Color4f[k];
/* 232 */         for (byte b = 0; b < k; b++) {
/* 233 */           arrayOfColor4f[b] = new Color4f(arrayOfFloat[m * (b + b2) + n], arrayOfFloat[m * (b + b2) + n + 1], arrayOfFloat[m * (b + b2) + n + 2], arrayOfFloat[m * (b + b2) + n + 3]);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 238 */         paramGeometryInfo.setColors(arrayOfColor4f);
/* 239 */         n += 4;
/* 240 */       } else if ((i & 0x4) != 0) {
/* 241 */         Color3f[] arrayOfColor3f = new Color3f[k];
/* 242 */         for (byte b = 0; b < k; b++) {
/* 243 */           arrayOfColor3f[b] = new Color3f(arrayOfFloat[m * (b + b2) + n], arrayOfFloat[m * (b + b2) + n + 1], arrayOfFloat[m * (b + b2) + n + 2]);
/*     */         }
/*     */ 
/*     */         
/* 247 */         paramGeometryInfo.setColors(arrayOfColor3f);
/* 248 */         n += 3;
/*     */       } 
/*     */       
/* 251 */       if ((i & 0x2) != 0) {
/* 252 */         Vector3f[] arrayOfVector3f = new Vector3f[k];
/* 253 */         for (byte b = 0; b < k; b++) {
/* 254 */           arrayOfVector3f[b] = new Vector3f(arrayOfFloat[m * (b + b2) + n], arrayOfFloat[m * (b + b2) + n + 1], arrayOfFloat[m * (b + b2) + n + 2]);
/*     */         }
/*     */ 
/*     */         
/* 258 */         paramGeometryInfo.setNormals(arrayOfVector3f);
/* 259 */         n += 3;
/*     */       } 
/*     */       
/* 262 */       Point3f[] arrayOfPoint3f = new Point3f[k];
/* 263 */       for (byte b1 = 0; b1 < k; b1++) {
/* 264 */         arrayOfPoint3f[b1] = new Point3f(arrayOfFloat[m * (b1 + b2) + n], arrayOfFloat[m * (b1 + b2) + n + 1], arrayOfFloat[m * (b1 + b2) + n + 2]);
/*     */       }
/*     */ 
/*     */       
/* 268 */       paramGeometryInfo.setCoordinates(arrayOfPoint3f);
/*     */     } else {
/*     */       
/* 271 */       boolean bool1 = ((i & 0x80) != 0) ? 1 : 0;
/* 272 */       boolean bool2 = ((i & 0x800) != 0) ? 1 : 0;
/*     */       
/* 274 */       Point3f[] arrayOfPoint3f = null;
/* 275 */       if (bool1) {
/*     */         byte b;
/*     */         
/* 278 */         if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 279 */         { b = paramGeometryArray.getInitialCoordIndex(); }
/* 280 */         else { b = 0; }
/*     */         
/* 282 */         if (bool2) {
/* 283 */           double[] arrayOfDouble; float[] arrayOfFloat; DoubleBufferWrapper doubleBufferWrapper; FloatBufferWrapper floatBufferWrapper; byte b1; J3DBuffer j3DBuffer = paramGeometryArray.getCoordRefBuffer();
/*     */           
/* 285 */           switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*     */             
/*     */             case 3:
/* 288 */               floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 289 */               arrayOfFloat = new float[k * 3];
/* 290 */               floatBufferWrapper.position(b * 3);
/* 291 */               floatBufferWrapper.get(arrayOfFloat, 0, k * 3);
/* 292 */               arrayOfPoint3f = new Point3f[k];
/* 293 */               for (b1 = 0; b1 < k; b1++) {
/* 294 */                 arrayOfPoint3f[b1] = new Point3f(arrayOfFloat[b1 * 3 + 0], arrayOfFloat[b1 * 3 + 1], arrayOfFloat[b1 * 3 + 2]);
/*     */               }
/*     */               break;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             case 4:
/* 302 */               doubleBufferWrapper = new DoubleBufferWrapper(j3DBuffer);
/* 303 */               arrayOfDouble = new double[k * 3];
/* 304 */               doubleBufferWrapper.position(b * 3);
/* 305 */               doubleBufferWrapper.get(arrayOfDouble, 0, k * 3);
/* 306 */               arrayOfPoint3f = new Point3f[k];
/* 307 */               for (b1 = 0; b1 < k; b1++) {
/* 308 */                 arrayOfPoint3f[b1] = new Point3f((float)arrayOfDouble[b1 * 3 + 0], (float)arrayOfDouble[b1 * 3 + 1], (float)arrayOfDouble[b1 * 3 + 2]);
/*     */               }
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/* 315 */         } else if (paramGeometryArray.getCoordRef3f() != null) {
/* 316 */           if (b)
/* 317 */           { Point3f[] arrayOfPoint3f1 = paramGeometryArray.getCoordRef3f();
/* 318 */             arrayOfPoint3f = new Point3f[k];
/* 319 */             for (byte b1 = 0; b1 < k; b1++)
/* 320 */               arrayOfPoint3f[b1] = new Point3f(arrayOfPoint3f1[b1 + b]);  }
/*     */           else
/* 322 */           { arrayOfPoint3f = paramGeometryArray.getCoordRef3f(); } 
/* 323 */         } else if (paramGeometryArray.getCoordRef3d() != null) {
/* 324 */           Point3d[] arrayOfPoint3d = paramGeometryArray.getCoordRef3d();
/* 325 */           arrayOfPoint3f = new Point3f[k];
/* 326 */           for (byte b1 = 0; b1 < k; b1++) {
/* 327 */             arrayOfPoint3f[b1] = new Point3f(arrayOfPoint3d[b1 + b]);
/*     */           }
/* 329 */         } else if (paramGeometryArray.getCoordRefFloat() != null) {
/* 330 */           float[] arrayOfFloat = paramGeometryArray.getCoordRefFloat();
/* 331 */           arrayOfPoint3f = new Point3f[k];
/* 332 */           for (byte b1 = 0; b1 < k; b1++) {
/* 333 */             arrayOfPoint3f[b1] = new Point3f(arrayOfFloat[(b1 + b) * 3], arrayOfFloat[(b1 + b) * 3 + 1], arrayOfFloat[(b1 + b) * 3 + 2]);
/*     */           
/*     */           }
/*     */         }
/* 337 */         else if (paramGeometryArray.getCoordRefDouble() != null) {
/* 338 */           double[] arrayOfDouble = paramGeometryArray.getCoordRefDouble();
/* 339 */           arrayOfPoint3f = new Point3f[k];
/* 340 */           for (byte b1 = 0; b1 < k; b1++) {
/* 341 */             arrayOfPoint3f[b1] = new Point3f((float)arrayOfDouble[(b1 + b) * 3], (float)arrayOfDouble[(b1 + b) * 3 + 1], (float)arrayOfDouble[(b1 + b) * 3 + 2]);
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         byte b2;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 350 */         if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 351 */         { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 352 */         else { b2 = 0; }
/* 353 */          arrayOfPoint3f = new Point3f[k];
/* 354 */         for (byte b1 = 0; b1 < k; ) { arrayOfPoint3f[b1] = new Point3f(); b1++; }
/* 355 */          paramGeometryArray.getCoordinates(b2, arrayOfPoint3f);
/*     */       } 
/* 357 */       paramGeometryInfo.setCoordinates(arrayOfPoint3f);
/*     */       
/* 359 */       if ((i & 0x2) != 0) {
/* 360 */         Vector3f[] arrayOfVector3f = null;
/* 361 */         if (bool1) {
/*     */           byte b;
/*     */           
/* 364 */           if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 365 */           { b = paramGeometryArray.getInitialNormalIndex(); }
/* 366 */           else { b = 0; }
/*     */           
/* 368 */           if (bool2) {
/* 369 */             J3DBuffer j3DBuffer = paramGeometryArray.getNormalRefBuffer();
/*     */             
/* 371 */             if (BufferWrapper.getBufferType(j3DBuffer) == 3) {
/* 372 */               FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 373 */               float[] arrayOfFloat = new float[k * 3];
/* 374 */               floatBufferWrapper.position(b * 3);
/* 375 */               floatBufferWrapper.get(arrayOfFloat, 0, k * 3);
/* 376 */               arrayOfVector3f = new Vector3f[k];
/* 377 */               for (byte b1 = 0; b1 < k; b1++) {
/* 378 */                 arrayOfVector3f[b1] = new Vector3f(arrayOfFloat[b1 * 3 + 0], arrayOfFloat[b1 * 3 + 1], arrayOfFloat[b1 * 3 + 2]);
/*     */               
/*     */               }
/*     */             }
/*     */           
/*     */           }
/* 384 */           else if (paramGeometryArray.getNormalRef3f() != null) {
/* 385 */             if (b)
/* 386 */             { Vector3f[] arrayOfVector3f1 = paramGeometryArray.getNormalRef3f();
/* 387 */               arrayOfVector3f = new Vector3f[k];
/* 388 */               for (byte b1 = 0; b1 < k; b1++)
/* 389 */                 arrayOfVector3f[b1] = new Vector3f(arrayOfVector3f1[b1 + b]);  }
/*     */             else
/* 391 */             { arrayOfVector3f = paramGeometryArray.getNormalRef3f(); } 
/* 392 */           } else if (paramGeometryArray.getNormalRefFloat() != null) {
/* 393 */             float[] arrayOfFloat = paramGeometryArray.getNormalRefFloat();
/* 394 */             arrayOfVector3f = new Vector3f[k];
/* 395 */             for (byte b1 = 0; b1 < k; b1++) {
/* 396 */               arrayOfVector3f[b1] = new Vector3f(arrayOfFloat[(b1 + b) * 3], arrayOfFloat[(b1 + b) * 3 + 1], arrayOfFloat[(b1 + b) * 3 + 2]);
/*     */             }
/*     */           } 
/*     */         } else {
/*     */           byte b2;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 405 */           if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 406 */           { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 407 */           else { b2 = 0; }
/* 408 */            arrayOfVector3f = new Vector3f[k];
/* 409 */           for (byte b1 = 0; b1 < k; ) { arrayOfVector3f[b1] = new Vector3f(); b1++; }
/* 410 */            paramGeometryArray.getNormals(b2, arrayOfVector3f);
/*     */         } 
/* 412 */         paramGeometryInfo.setNormals(arrayOfVector3f);
/*     */       } 
/*     */       
/* 415 */       if ((i & 0xC) == 12) {
/* 416 */         Color4f[] arrayOfColor4f = null;
/* 417 */         if (bool1) {
/*     */           byte b;
/*     */           
/* 420 */           if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 421 */           { b = paramGeometryArray.getInitialColorIndex(); }
/* 422 */           else { b = 0; }
/*     */           
/* 424 */           if (bool2) {
/* 425 */             byte[] arrayOfByte; float[] arrayOfFloat; ByteBufferWrapper byteBufferWrapper; FloatBufferWrapper floatBufferWrapper; byte b1; J3DBuffer j3DBuffer = paramGeometryArray.getColorRefBuffer();
/*     */             
/* 427 */             switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*     */               
/*     */               case 3:
/* 430 */                 floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 431 */                 arrayOfFloat = new float[k * 4];
/* 432 */                 floatBufferWrapper.position(b * 4);
/* 433 */                 floatBufferWrapper.get(arrayOfFloat, 0, k * 4);
/* 434 */                 arrayOfColor4f = new Color4f[k];
/* 435 */                 for (b1 = 0; b1 < k; b1++) {
/* 436 */                   arrayOfColor4f[b1] = new Color4f(arrayOfFloat[b1 * 4 + 0], arrayOfFloat[b1 * 4 + 1], arrayOfFloat[b1 * 4 + 2], arrayOfFloat[b1 * 4 + 3]);
/*     */                 }
/*     */                 break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case 2:
/* 445 */                 byteBufferWrapper = new ByteBufferWrapper(j3DBuffer);
/* 446 */                 arrayOfByte = new byte[k * 4];
/* 447 */                 byteBufferWrapper.position(b * 4);
/* 448 */                 byteBufferWrapper.get(arrayOfByte, 0, k * 4);
/* 449 */                 arrayOfColor4f = new Color4f[k];
/* 450 */                 for (b1 = 0; b1 < k; b1++) {
/* 451 */                   arrayOfColor4f[b1] = new Color4f((arrayOfByte[b1 * 4 + 0] & 0xFF) / 255.0F, (arrayOfByte[b1 * 4 + 1] & 0xFF) / 255.0F, (arrayOfByte[b1 * 4 + 2] & 0xFF) / 255.0F, (arrayOfByte[b1 * 4 + 3] & 0xFF) / 255.0F);
/*     */                 }
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 459 */           } else if (paramGeometryArray.getColorRef4f() != null) {
/* 460 */             if (b)
/* 461 */             { Color4f[] arrayOfColor4f1 = paramGeometryArray.getColorRef4f();
/* 462 */               arrayOfColor4f = new Color4f[k];
/* 463 */               for (byte b1 = 0; b1 < k; b1++)
/* 464 */                 arrayOfColor4f[b1] = new Color4f(arrayOfColor4f1[b1 + b]);  }
/*     */             else
/* 466 */             { arrayOfColor4f = paramGeometryArray.getColorRef4f(); } 
/* 467 */           } else if (paramGeometryArray.getColorRefFloat() != null) {
/* 468 */             float[] arrayOfFloat = paramGeometryArray.getColorRefFloat();
/* 469 */             arrayOfColor4f = new Color4f[k];
/* 470 */             for (byte b1 = 0; b1 < k; b1++) {
/* 471 */               arrayOfColor4f[b1] = new Color4f(arrayOfFloat[(b1 + b) * 4 + 0], arrayOfFloat[(b1 + b) * 4 + 1], arrayOfFloat[(b1 + b) * 4 + 2], arrayOfFloat[(b1 + b) * 4 + 3]);
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 476 */           else if (paramGeometryArray.getColorRefByte() != null) {
/* 477 */             byte[] arrayOfByte = paramGeometryArray.getColorRefByte();
/* 478 */             arrayOfColor4f = new Color4f[k];
/* 479 */             for (byte b1 = 0; b1 < k; b1++) {
/* 480 */               arrayOfColor4f[b1] = new Color4f((arrayOfByte[(b1 + b) * 4 + 0] & 0xFF) / 255.0F, (arrayOfByte[(b1 + b) * 4 + 1] & 0xFF) / 255.0F, (arrayOfByte[(b1 + b) * 4 + 2] & 0xFF) / 255.0F, (arrayOfByte[(b1 + b) * 4 + 3] & 0xFF) / 255.0F);
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 485 */           else if (paramGeometryArray.getColorRef4b() != null) {
/* 486 */             Color4b[] arrayOfColor4b = paramGeometryArray.getColorRef4b();
/* 487 */             arrayOfColor4f = new Color4f[k];
/* 488 */             for (byte b1 = 0; b1 < k; b1++) {
/* 489 */               arrayOfColor4f[b1] = new Color4f(((arrayOfColor4b[b1 + b]).x & 0xFF) / 255.0F, ((arrayOfColor4b[b1 + b]).y & 0xFF) / 255.0F, ((arrayOfColor4b[b1 + b]).z & 0xFF) / 255.0F, ((arrayOfColor4b[b1 + b]).w & 0xFF) / 255.0F);
/*     */             }
/*     */           } 
/*     */         } else {
/*     */           byte b2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 499 */           if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 500 */           { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 501 */           else { b2 = 0; }
/* 502 */            arrayOfColor4f = new Color4f[k];
/* 503 */           for (byte b1 = 0; b1 < k; ) { arrayOfColor4f[b1] = new Color4f(); b1++; }
/* 504 */            paramGeometryArray.getColors(b2, arrayOfColor4f);
/*     */         } 
/* 506 */         paramGeometryInfo.setColors(arrayOfColor4f);
/* 507 */       } else if ((i & 0x4) != 0) {
/* 508 */         Color3f[] arrayOfColor3f = null;
/* 509 */         if (bool1) {
/*     */           byte b;
/*     */           
/* 512 */           if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 513 */           { b = paramGeometryArray.getInitialColorIndex(); }
/* 514 */           else { b = 0; }
/*     */           
/* 516 */           if (bool2) {
/* 517 */             float[] arrayOfFloat; byte[] arrayOfByte; FloatBufferWrapper floatBufferWrapper; ByteBufferWrapper byteBufferWrapper; byte b1; J3DBuffer j3DBuffer = paramGeometryArray.getColorRefBuffer();
/*     */             
/* 519 */             switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*     */               
/*     */               case 3:
/* 522 */                 floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 523 */                 arrayOfFloat = new float[k * 3];
/* 524 */                 floatBufferWrapper.position(b * 3);
/* 525 */                 floatBufferWrapper.get(arrayOfFloat, 0, k * 3);
/* 526 */                 arrayOfColor3f = new Color3f[k];
/* 527 */                 for (b1 = 0; b1 < k; b1++) {
/* 528 */                   arrayOfColor3f[b1] = new Color3f(arrayOfFloat[b1 * 3 + 0], arrayOfFloat[b1 * 3 + 1], arrayOfFloat[b1 * 3 + 2]);
/*     */                 }
/*     */                 break;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case 2:
/* 536 */                 byteBufferWrapper = new ByteBufferWrapper(j3DBuffer);
/* 537 */                 arrayOfByte = new byte[k * 3];
/* 538 */                 byteBufferWrapper.position(b * 3);
/* 539 */                 byteBufferWrapper.get(arrayOfByte, 0, k * 3);
/* 540 */                 arrayOfColor3f = new Color3f[k];
/* 541 */                 for (b1 = 0; b1 < k; b1++) {
/* 542 */                   arrayOfColor3f[b1] = new Color3f((arrayOfByte[b1 * 3 + 0] & 0xFF) / 255.0F, (arrayOfByte[b1 * 3 + 1] & 0xFF) / 255.0F, (arrayOfByte[b1 * 3 + 2] & 0xFF) / 255.0F);
/*     */                 }
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */           
/* 549 */           } else if (paramGeometryArray.getColorRef3f() != null) {
/* 550 */             if (b)
/* 551 */             { Color3f[] arrayOfColor3f1 = paramGeometryArray.getColorRef3f();
/* 552 */               arrayOfColor3f = new Color3f[k];
/* 553 */               for (byte b1 = 0; b1 < k; b1++)
/* 554 */                 arrayOfColor3f[b1] = new Color3f(arrayOfColor3f1[b1 + b]);  }
/*     */             else
/* 556 */             { arrayOfColor3f = paramGeometryArray.getColorRef3f(); } 
/* 557 */           } else if (paramGeometryArray.getColorRefFloat() != null) {
/* 558 */             float[] arrayOfFloat = paramGeometryArray.getColorRefFloat();
/* 559 */             arrayOfColor3f = new Color3f[k];
/* 560 */             for (byte b1 = 0; b1 < k; b1++) {
/* 561 */               arrayOfColor3f[b1] = new Color3f(arrayOfFloat[(b1 + b) * 3 + 0], arrayOfFloat[(b1 + b) * 3 + 1], arrayOfFloat[(b1 + b) * 3 + 2]);
/*     */             
/*     */             }
/*     */           }
/* 565 */           else if (paramGeometryArray.getColorRefByte() != null) {
/* 566 */             byte[] arrayOfByte = paramGeometryArray.getColorRefByte();
/* 567 */             arrayOfColor3f = new Color3f[k];
/* 568 */             for (byte b1 = 0; b1 < k; b1++) {
/* 569 */               arrayOfColor3f[b1] = new Color3f((arrayOfByte[(b1 + b) * 3 + 0] & 0xFF) / 255.0F, (arrayOfByte[(b1 + b) * 3 + 1] & 0xFF) / 255.0F, (arrayOfByte[(b1 + b) * 3 + 2] & 0xFF) / 255.0F);
/*     */             
/*     */             }
/*     */           }
/* 573 */           else if (paramGeometryArray.getColorRef3b() != null) {
/* 574 */             Color3b[] arrayOfColor3b = paramGeometryArray.getColorRef3b();
/* 575 */             arrayOfColor3f = new Color3f[k];
/* 576 */             for (byte b1 = 0; b1 < k; b1++) {
/* 577 */               arrayOfColor3f[b1] = new Color3f(((arrayOfColor3b[b1 + b]).x & 0xFF) / 255.0F, ((arrayOfColor3b[b1 + b]).y & 0xFF) / 255.0F, ((arrayOfColor3b[b1 + b]).z & 0xFF) / 255.0F);
/*     */             }
/*     */           } 
/*     */         } else {
/*     */           byte b2;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 586 */           if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 587 */           { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 588 */           else { b2 = 0; }
/* 589 */            arrayOfColor3f = new Color3f[k];
/* 590 */           for (byte b1 = 0; b1 < k; ) { arrayOfColor3f[b1] = new Color3f(); b1++; }
/* 591 */            paramGeometryArray.getColors(b2, arrayOfColor3f);
/*     */         } 
/* 593 */         paramGeometryInfo.setColors(arrayOfColor3f);
/*     */       } 
/*     */       
/* 596 */       if ((i & 0x400) != 0) {
/* 597 */         paramGeometryInfo.setTextureCoordinateParams(j, 4);
/* 598 */         for (byte b = 0; b < j; b++) {
/* 599 */           TexCoord4f[] arrayOfTexCoord4f = null;
/* 600 */           if (bool1) {
/*     */             byte b1;
/*     */             
/* 603 */             if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 604 */             { b1 = paramGeometryArray.getInitialTexCoordIndex(b); }
/* 605 */             else { b1 = 0; }
/*     */             
/* 607 */             if (bool2) {
/* 608 */               J3DBuffer j3DBuffer = paramGeometryArray.getTexCoordRefBuffer(b);
/*     */               
/* 610 */               if (BufferWrapper.getBufferType(j3DBuffer) == 3) {
/* 611 */                 FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 612 */                 float[] arrayOfFloat = new float[k * 4];
/* 613 */                 floatBufferWrapper.position(b1 * 4);
/* 614 */                 floatBufferWrapper.get(arrayOfFloat, 0, k * 4);
/* 615 */                 arrayOfTexCoord4f = new TexCoord4f[k];
/* 616 */                 for (byte b2 = 0; b2 < k; b2++) {
/* 617 */                   arrayOfTexCoord4f[b2] = new TexCoord4f(arrayOfFloat[b2 * 4 + 0], arrayOfFloat[b2 * 4 + 1], arrayOfFloat[b2 * 4 + 2], arrayOfFloat[b2 * 4 + 3]);
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 626 */               float[] arrayOfFloat = paramGeometryArray.getTexCoordRefFloat(b);
/* 627 */               arrayOfTexCoord4f = new TexCoord4f[k];
/* 628 */               for (byte b2 = 0; b2 < k; b2++) {
/* 629 */                 arrayOfTexCoord4f[b2] = new TexCoord4f(arrayOfFloat[(b2 + b1) * 4], arrayOfFloat[(b2 + b1) * 4 + 1], arrayOfFloat[(b2 + b1) * 4 + 2], arrayOfFloat[(b2 + b1) * 4 + 3]);
/*     */               }
/*     */             } 
/*     */           } else {
/*     */             byte b2;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 638 */             if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 639 */             { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 640 */             else { b2 = 0; }
/* 641 */              arrayOfTexCoord4f = new TexCoord4f[k];
/* 642 */             for (byte b1 = 0; b1 < k; ) { arrayOfTexCoord4f[b1] = new TexCoord4f(); b1++; }
/* 643 */              paramGeometryArray.getTextureCoordinates(b, b2, arrayOfTexCoord4f);
/*     */           } 
/* 645 */           paramGeometryInfo.setTextureCoordinates(b, arrayOfTexCoord4f);
/*     */         } 
/* 647 */         int[] arrayOfInt = new int[paramGeometryArray.getTexCoordSetMapLength()];
/* 648 */         paramGeometryArray.getTexCoordSetMap(arrayOfInt);
/* 649 */         paramGeometryInfo.setTexCoordSetMap(arrayOfInt);
/* 650 */       } else if ((i & 0x40) != 0) {
/* 651 */         paramGeometryInfo.setTextureCoordinateParams(j, 3);
/* 652 */         for (byte b = 0; b < j; b++) {
/* 653 */           TexCoord3f[] arrayOfTexCoord3f = null;
/* 654 */           if (bool1) {
/*     */             byte b1;
/*     */             
/* 657 */             if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 658 */             { b1 = paramGeometryArray.getInitialTexCoordIndex(b); }
/* 659 */             else { b1 = 0; }
/*     */             
/* 661 */             if (bool2) {
/* 662 */               J3DBuffer j3DBuffer = paramGeometryArray.getTexCoordRefBuffer(b);
/*     */               
/* 664 */               if (BufferWrapper.getBufferType(j3DBuffer) == 3) {
/* 665 */                 FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 666 */                 float[] arrayOfFloat = new float[k * 3];
/* 667 */                 floatBufferWrapper.position(b1 * 3);
/* 668 */                 floatBufferWrapper.get(arrayOfFloat, 0, k * 3);
/* 669 */                 arrayOfTexCoord3f = new TexCoord3f[k];
/* 670 */                 for (byte b2 = 0; b2 < k; b2++) {
/* 671 */                   arrayOfTexCoord3f[b2] = new TexCoord3f(arrayOfFloat[b2 * 3 + 0], arrayOfFloat[b2 * 3 + 1], arrayOfFloat[b2 * 3 + 2]);
/*     */                 
/*     */                 }
/*     */               }
/*     */             
/*     */             }
/* 677 */             else if (paramGeometryArray.getTexCoordRef3f(b) != null) {
/* 678 */               if (b1)
/* 679 */               { TexCoord3f[] arrayOfTexCoord3f1 = paramGeometryArray.getTexCoordRef3f(b);
/* 680 */                 arrayOfTexCoord3f = new TexCoord3f[k];
/* 681 */                 for (byte b2 = 0; b2 < k; b2++)
/* 682 */                   arrayOfTexCoord3f[b2] = new TexCoord3f(arrayOfTexCoord3f1[b2 + b1]);  }
/*     */               else
/* 684 */               { arrayOfTexCoord3f = paramGeometryArray.getTexCoordRef3f(b); } 
/* 685 */             } else if (paramGeometryArray.getTexCoordRefFloat(b) != null) {
/* 686 */               float[] arrayOfFloat = paramGeometryArray.getTexCoordRefFloat(b);
/* 687 */               arrayOfTexCoord3f = new TexCoord3f[k];
/* 688 */               for (byte b2 = 0; b2 < k; b2++) {
/* 689 */                 arrayOfTexCoord3f[b2] = new TexCoord3f(arrayOfFloat[(b2 + b1) * 3], arrayOfFloat[(b2 + b1) * 3 + 1], arrayOfFloat[(b2 + b1) * 3 + 2]);
/*     */               }
/*     */             } 
/*     */           } else {
/*     */             byte b2;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 698 */             if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 699 */             { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 700 */             else { b2 = 0; }
/* 701 */              arrayOfTexCoord3f = new TexCoord3f[k];
/* 702 */             for (byte b1 = 0; b1 < k; ) { arrayOfTexCoord3f[b1] = new TexCoord3f(); b1++; }
/* 703 */              paramGeometryArray.getTextureCoordinates(b, b2, arrayOfTexCoord3f);
/*     */           } 
/* 705 */           paramGeometryInfo.setTextureCoordinates(b, arrayOfTexCoord3f);
/*     */         } 
/* 707 */         int[] arrayOfInt = new int[paramGeometryArray.getTexCoordSetMapLength()];
/* 708 */         paramGeometryArray.getTexCoordSetMap(arrayOfInt);
/* 709 */         paramGeometryInfo.setTexCoordSetMap(arrayOfInt);
/* 710 */       } else if ((i & 0x20) != 0) {
/* 711 */         paramGeometryInfo.setTextureCoordinateParams(j, 2);
/* 712 */         for (byte b = 0; b < j; b++) {
/* 713 */           TexCoord2f[] arrayOfTexCoord2f = null;
/* 714 */           if (bool1) {
/*     */             byte b1;
/*     */             
/* 717 */             if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 718 */             { b1 = paramGeometryArray.getInitialTexCoordIndex(b); }
/* 719 */             else { b1 = 0; }
/*     */             
/* 721 */             if (bool2) {
/* 722 */               J3DBuffer j3DBuffer = paramGeometryArray.getTexCoordRefBuffer(b);
/*     */               
/* 724 */               if (BufferWrapper.getBufferType(j3DBuffer) == 3) {
/* 725 */                 FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 726 */                 float[] arrayOfFloat = new float[k * 2];
/* 727 */                 floatBufferWrapper.position(b1 * 2);
/* 728 */                 floatBufferWrapper.get(arrayOfFloat, 0, k * 2);
/* 729 */                 arrayOfTexCoord2f = new TexCoord2f[k];
/* 730 */                 for (byte b2 = 0; b2 < k; b2++) {
/* 731 */                   arrayOfTexCoord2f[b2] = new TexCoord2f(arrayOfFloat[b2 * 2 + 0], arrayOfFloat[b2 * 2 + 1]);
/*     */                 }
/*     */               }
/*     */             
/*     */             }
/* 736 */             else if (paramGeometryArray.getTexCoordRefFloat(b) != null) {
/* 737 */               float[] arrayOfFloat = paramGeometryArray.getTexCoordRefFloat(b);
/* 738 */               arrayOfTexCoord2f = new TexCoord2f[k];
/* 739 */               for (byte b2 = 0; b2 < k; b2++) {
/* 740 */                 arrayOfTexCoord2f[b2] = new TexCoord2f(arrayOfFloat[(b2 + b1) * 2 + 0], arrayOfFloat[(b2 + b1) * 2 + 1]);
/*     */               }
/*     */             }
/* 743 */             else if (paramGeometryArray.getTexCoordRef2f(b) != null) {
/* 744 */               if (b1 != 0) {
/* 745 */                 TexCoord2f[] arrayOfTexCoord2f1 = paramGeometryArray.getTexCoordRef2f(b);
/* 746 */                 arrayOfTexCoord2f = new TexCoord2f[k];
/* 747 */                 for (byte b2 = 0; b2 < k; b2++)
/* 748 */                   arrayOfTexCoord2f[b2] = new TexCoord2f(arrayOfTexCoord2f1[b2 + b1]); 
/*     */               } else {
/* 750 */                 arrayOfTexCoord2f = paramGeometryArray.getTexCoordRef2f(b);
/*     */               } 
/*     */             } 
/*     */           } else {
/*     */             byte b2;
/*     */             
/* 756 */             if (!(paramGeometryArray instanceof IndexedGeometryArray))
/* 757 */             { b2 = paramGeometryArray.getInitialVertexIndex(); }
/* 758 */             else { b2 = 0; }
/* 759 */              arrayOfTexCoord2f = new TexCoord2f[k];
/* 760 */             for (byte b1 = 0; b1 < k; ) { arrayOfTexCoord2f[b1] = new TexCoord2f(); b1++; }
/* 761 */              paramGeometryArray.getTextureCoordinates(b, b2, arrayOfTexCoord2f);
/*     */           } 
/* 763 */           paramGeometryInfo.setTextureCoordinates(b, arrayOfTexCoord2f);
/*     */         } 
/* 765 */         int[] arrayOfInt = new int[paramGeometryArray.getTexCoordSetMapLength()];
/* 766 */         paramGeometryArray.getTexCoordSetMap(arrayOfInt);
/* 767 */         paramGeometryInfo.setTexCoordSetMap(arrayOfInt);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void processIndexedArray(GeometryInfo paramGeometryInfo, IndexedGeometryArray paramIndexedGeometryArray) {
/* 777 */     int m, i = paramIndexedGeometryArray.getInitialIndexIndex();
/* 778 */     int j = paramIndexedGeometryArray.getVertexFormat();
/* 779 */     int k = paramIndexedGeometryArray.getTexCoordSetCount();
/*     */ 
/*     */     
/* 782 */     if (paramIndexedGeometryArray instanceof IndexedGeometryStripArray) {
/* 783 */       IndexedGeometryStripArray indexedGeometryStripArray = (IndexedGeometryStripArray)paramIndexedGeometryArray;
/* 784 */       int[] arrayOfInt1 = new int[indexedGeometryStripArray.getNumStrips()];
/* 785 */       indexedGeometryStripArray.getStripIndexCounts(arrayOfInt1);
/* 786 */       m = 0;
/* 787 */       for (byte b = 0; b < arrayOfInt1.length; b++) {
/* 788 */         m += arrayOfInt1[b];
/*     */       }
/*     */     } else {
/* 791 */       m = paramIndexedGeometryArray.getValidIndexCount();
/*     */     } 
/*     */     
/* 794 */     int[] arrayOfInt = new int[m];
/* 795 */     paramIndexedGeometryArray.getCoordinateIndices(i, arrayOfInt);
/* 796 */     paramGeometryInfo.setCoordinateIndices(arrayOfInt);
/*     */     
/* 798 */     if ((j & 0x200) != 0) {
/* 799 */       if ((j & 0x2) != 0)
/* 800 */         paramGeometryInfo.setNormalIndices(arrayOfInt); 
/* 801 */       if ((j & 0x4) != 0 || (j & 0xC) != 0)
/*     */       {
/* 803 */         paramGeometryInfo.setColorIndices(arrayOfInt); } 
/* 804 */       if ((j & 0x20) != 0 || (j & 0x40) != 0 || (j & 0x400) != 0)
/*     */       {
/*     */         
/* 807 */         for (byte b = 0; b < k; b++) {
/* 808 */           paramGeometryInfo.setTextureCoordinateIndices(b, arrayOfInt);
/*     */         }
/*     */       }
/*     */     } else {
/* 812 */       if ((j & 0x2) != 0) {
/* 813 */         int[] arrayOfInt1 = new int[m];
/* 814 */         paramIndexedGeometryArray.getNormalIndices(i, arrayOfInt1);
/* 815 */         paramGeometryInfo.setNormalIndices(arrayOfInt1);
/*     */       } 
/*     */       
/* 818 */       if ((j & 0x4) != 0 || (j & 0xC) != 0) {
/*     */         
/* 820 */         int[] arrayOfInt1 = new int[m];
/* 821 */         paramIndexedGeometryArray.getColorIndices(i, arrayOfInt1);
/* 822 */         paramGeometryInfo.setColorIndices(arrayOfInt1);
/*     */       } 
/*     */       
/* 825 */       if ((j & 0x20) != 0 || (j & 0x40) != 0 || (j & 0x400) != 0)
/*     */       {
/*     */         
/* 828 */         for (byte b = 0; b < k; b++) {
/* 829 */           int[] arrayOfInt1 = new int[m];
/* 830 */           paramIndexedGeometryArray.getTextureCoordinateIndices(b, i, arrayOfInt1);
/* 831 */           paramGeometryInfo.setTextureCoordinateIndices(b, arrayOfInt1);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void processStripArray(GeometryInfo paramGeometryInfo, GeometryStripArray paramGeometryStripArray) {
/* 842 */     int[] arrayOfInt = new int[paramGeometryStripArray.getNumStrips()];
/* 843 */     paramGeometryStripArray.getStripVertexCounts(arrayOfInt);
/* 844 */     paramGeometryInfo.setStripCounts(arrayOfInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void processIndexStripArray(GeometryInfo paramGeometryInfo, IndexedGeometryStripArray paramIndexedGeometryStripArray) {
/* 852 */     int[] arrayOfInt = new int[paramIndexedGeometryStripArray.getNumStrips()];
/* 853 */     paramIndexedGeometryStripArray.getStripIndexCounts(arrayOfInt);
/* 854 */     paramGeometryInfo.setStripCounts(arrayOfInt);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\GeometryInfoGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */