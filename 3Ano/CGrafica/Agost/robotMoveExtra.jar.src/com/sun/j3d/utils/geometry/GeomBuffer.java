/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.media.j3d.GeometryArray;
/*     */ import javax.media.j3d.QuadArray;
/*     */ import javax.media.j3d.TriangleArray;
/*     */ import javax.media.j3d.TriangleFanArray;
/*     */ import javax.media.j3d.TriangleStripArray;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.TexCoord2f;
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
/*     */ class GeomBuffer
/*     */ {
/*     */   static final int QUAD_STRIP = 1;
/*     */   static final int TRIANGLES = 2;
/*     */   static final int QUADS = 4;
/*     */   static final int TRIANGLE_FAN = 16;
/*     */   static final int TRIANGLE_STRIP = 32;
/*     */   private int flags;
/*     */   Point3f[] pts;
/*     */   Vector3f[] normals;
/*     */   TexCoord2f[] tcoords;
/*     */   int currVertCnt;
/*     */   int currPrimCnt;
/*     */   int[] currPrimType;
/*     */   int[] currPrimStartVertex;
/*     */   int[] currPrimEndVertex;
/*     */   GeometryArray geometry;
/*     */   int numVerts;
/*     */   int numTris;
/*     */   int numTexUnit;
/*     */   int[] texCoordSetMap;
/*     */   static final int debug = 0;
/*     */   
/*     */   GeomBuffer(int paramInt1, int paramInt2) {
/* 100 */     this.pts = null;
/* 101 */     this.normals = null;
/* 102 */     this.tcoords = null;
/*     */ 
/*     */     
/* 105 */     this.currPrimType = null;
/* 106 */     this.currPrimStartVertex = null;
/* 107 */     this.currPrimEndVertex = null;
/*     */     
/* 109 */     this.numVerts = 0;
/* 110 */     this.numTris = 0;
/* 111 */     this.numTexUnit = 1;
/* 112 */     this.texCoordSetMap = null;
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
/* 123 */     this.numTexUnit = paramInt2;
/* 124 */     this.pts = new Point3f[paramInt1];
/* 125 */     this.normals = new Vector3f[paramInt1];
/* 126 */     this.tcoords = new TexCoord2f[paramInt1];
/*     */     
/* 128 */     this.currPrimType = new int[paramInt1 / 3];
/* 129 */     this.currPrimStartVertex = new int[paramInt1 / 3];
/* 130 */     this.currPrimEndVertex = new int[paramInt1 / 3];
/* 131 */     this.currVertCnt = 0;
/* 132 */     this.currPrimCnt = 0;
/*     */     
/* 134 */     this.texCoordSetMap = new int[paramInt2];
/* 135 */     for (byte b = 0; b < paramInt2; b++) {
/* 136 */       this.texCoordSetMap[b] = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 142 */   GeomBuffer(int paramInt) { this(paramInt, 1); }
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
/*     */   GeometryArray getGeom(int paramInt) {
/* 154 */     GeometryArray geometryArray = null;
/* 155 */     this.flags = paramInt;
/*     */     
/* 157 */     this.numTris = 0;
/*     */ 
/*     */     
/* 160 */     switch (this.currPrimType[0]) {
/*     */       case 2:
/* 162 */         geometryArray = processTriangles();
/*     */         break;
/*     */       case 4:
/* 165 */         geometryArray = processQuads();
/*     */         break;
/*     */       case 1:
/*     */       case 32:
/* 169 */         geometryArray = processQuadStrips();
/*     */         break;
/*     */       case 16:
/* 172 */         geometryArray = processTriangleFan();
/*     */         break;
/*     */     } 
/* 175 */     if (geometryArray != null && (this.flags & 0x20) != 0) {
/* 176 */       geometryArray.setCapability(18);
/* 177 */       geometryArray.setCapability(17);
/* 178 */       geometryArray.setCapability(8);
/* 179 */       geometryArray.setCapability(0);
/*     */     } 
/* 181 */     return geometryArray;
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
/*     */   
/*     */   void begin(int paramInt) {
/* 195 */     this.currPrimType[this.currPrimCnt] = paramInt;
/* 196 */     this.currPrimStartVertex[this.currPrimCnt] = this.currVertCnt;
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
/*     */   void end() {
/* 208 */     this.currPrimEndVertex[this.currPrimCnt] = this.currVertCnt;
/* 209 */     this.currPrimCnt++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void vertex3d(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 218 */     this.pts[this.currVertCnt] = new Point3f((float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
/* 219 */     this.currVertCnt++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void normal3d(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 226 */     double d = paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2 + paramDouble3 * paramDouble3;
/* 227 */     if (Math.abs(d - 1.0D) > 0.001D) {
/*     */       
/* 229 */       double d1 = Math.sqrt(d);
/* 230 */       if (d1 > 1.0E-6D) {
/* 231 */         paramDouble1 /= d1;
/* 232 */         paramDouble2 /= d1;
/* 233 */         paramDouble3 /= d1;
/*     */       } else {
/* 235 */         paramDouble2 = paramDouble3 = 0.0D; paramDouble1 = 1.0D;
/*     */       } 
/*     */     } 
/* 238 */     this.normals[this.currVertCnt] = new Vector3f((float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 246 */   void texCoord2d(double paramDouble1, double paramDouble2) { this.tcoords[this.currVertCnt] = new TexCoord2f((float)paramDouble1, (float)paramDouble2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   TexCoord2f[] getTexCoords() { return this.tcoords; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   GeometryArray getComputedGeometry() { return this.geometry; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   int getNumTris() { return this.numTris; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   int getNumVerts() { return this.numVerts; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GeometryArray processQuadStrips() {
/* 277 */     TriangleStripArray triangleStripArray = null;
/*     */     
/* 279 */     int i = 0;
/*     */ 
/*     */     
/* 282 */     int[] arrayOfInt = new int[this.currPrimCnt]; byte b1;
/* 283 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/* 284 */       arrayOfInt[b1] = this.currPrimEndVertex[b1] - this.currPrimStartVertex[b1];
/* 285 */       i += arrayOfInt[b1];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 290 */     byte b2 = 1;
/* 291 */     if ((this.flags & true) != 0)
/* 292 */       b2 |= 0x2; 
/* 293 */     if ((this.flags & 0x2) != 0) {
/* 294 */       b2 |= 0x20;
/*     */     }
/*     */     
/* 297 */     triangleStripArray = new TriangleStripArray(i, b2, 1, this.texCoordSetMap, arrayOfInt);
/*     */ 
/*     */ 
/*     */     
/* 301 */     Point3f[] arrayOfPoint3f = new Point3f[i];
/* 302 */     Vector3f[] arrayOfVector3f = new Vector3f[i];
/* 303 */     TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[i];
/* 304 */     byte b3 = 0;
/*     */ 
/*     */     
/* 307 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/*     */       
/* 309 */       for (int j = this.currPrimStartVertex[b1]; j < this.currPrimEndVertex[b1]; j++) {
/* 310 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b3++, this.pts, this.normals, this.tcoords, j);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 316 */     this.numVerts = b3;
/* 317 */     this.numTris += i - this.currPrimCnt * 2;
/*     */     
/* 319 */     triangleStripArray.setCoordinates(0, arrayOfPoint3f);
/* 320 */     if ((this.flags & true) != 0)
/* 321 */       triangleStripArray.setNormals(0, arrayOfVector3f); 
/* 322 */     if ((this.flags & 0x2) != 0) {
/* 323 */       triangleStripArray.setTextureCoordinates(0, 0, arrayOfTexCoord2f);
/*     */     }
/* 325 */     this.geometry = triangleStripArray;
/* 326 */     return triangleStripArray;
/*     */   }
/*     */ 
/*     */   
/*     */   private GeometryArray processQuads() {
/* 331 */     QuadArray quadArray = null;
/*     */     
/* 333 */     int i = 0;
/*     */     byte b1;
/* 335 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/* 336 */       i += this.currPrimEndVertex[b1] - this.currPrimStartVertex[b1];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 341 */     if ((this.flags & true) != 0 && (this.flags & 0x2) != 0) {
/*     */       
/* 343 */       quadArray = new QuadArray(i, 35, 1, this.texCoordSetMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 350 */     else if ((this.flags & true) == 0 && (this.flags & 0x2) != 0) {
/*     */       
/* 352 */       quadArray = new QuadArray(i, 33, 1, this.texCoordSetMap);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 358 */     else if ((this.flags & true) != 0 && (this.flags & 0x2) == 0) {
/*     */       
/* 360 */       quadArray = new QuadArray(i, 3);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 365 */       quadArray = new QuadArray(i, 1);
/*     */     } 
/*     */ 
/*     */     
/* 369 */     Point3f[] arrayOfPoint3f = new Point3f[i];
/* 370 */     Vector3f[] arrayOfVector3f = new Vector3f[i];
/* 371 */     TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[i];
/* 372 */     byte b2 = 0;
/*     */ 
/*     */ 
/*     */     
/* 376 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/*     */ 
/*     */       
/* 379 */       for (int j = this.currPrimStartVertex[b1]; j < this.currPrimEndVertex[b1] - 3; j += 4) {
/* 380 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b2++, this.pts, this.normals, this.tcoords, j);
/*     */         
/* 382 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b2++, this.pts, this.normals, this.tcoords, j + 1);
/*     */         
/* 384 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b2++, this.pts, this.normals, this.tcoords, j + 2);
/*     */         
/* 386 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b2++, this.pts, this.normals, this.tcoords, j + 3);
/*     */         
/* 388 */         this.numTris += 2;
/*     */       } 
/*     */     } 
/* 391 */     this.numVerts = b2;
/*     */     
/* 393 */     quadArray.setCoordinates(0, arrayOfPoint3f);
/* 394 */     if ((this.flags & true) != 0)
/* 395 */       quadArray.setNormals(0, arrayOfVector3f); 
/* 396 */     if ((this.flags & 0x2) != 0) {
/* 397 */       quadArray.setTextureCoordinates(0, 0, arrayOfTexCoord2f);
/*     */     }
/* 399 */     this.geometry = quadArray;
/* 400 */     return quadArray;
/*     */   }
/*     */ 
/*     */   
/*     */   private GeometryArray processTriangles() {
/* 405 */     TriangleArray triangleArray = null;
/*     */     
/* 407 */     int i = 0;
/*     */     byte b1;
/* 409 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/* 410 */       i += this.currPrimEndVertex[b1] - this.currPrimStartVertex[b1];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 415 */     if ((this.flags & true) != 0 && (this.flags & 0x2) != 0) {
/*     */       
/* 417 */       triangleArray = new TriangleArray(i, 35, 1, this.texCoordSetMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 424 */     else if ((this.flags & true) == 0 && (this.flags & 0x2) != 0) {
/*     */       
/* 426 */       triangleArray = new TriangleArray(i, 33, 1, this.texCoordSetMap);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 432 */     else if ((this.flags & true) != 0 && (this.flags & 0x2) == 0) {
/*     */       
/* 434 */       triangleArray = new TriangleArray(i, 3);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 439 */       triangleArray = new TriangleArray(i, 1);
/*     */     } 
/*     */ 
/*     */     
/* 443 */     Point3f[] arrayOfPoint3f = new Point3f[i];
/* 444 */     Vector3f[] arrayOfVector3f = new Vector3f[i];
/* 445 */     TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[i];
/* 446 */     byte b2 = 0;
/*     */     
/* 448 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/* 449 */       for (int j = this.currPrimStartVertex[b1]; j < this.currPrimEndVertex[b1] - 2; j += 3) {
/* 450 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b2++, this.pts, this.normals, this.tcoords, j);
/*     */         
/* 452 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b2++, this.pts, this.normals, this.tcoords, j + 1);
/*     */         
/* 454 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b2++, this.pts, this.normals, this.tcoords, j + 2);
/*     */         
/* 456 */         this.numTris++;
/*     */       } 
/*     */     } 
/* 459 */     this.numVerts = b2;
/*     */     
/* 461 */     triangleArray.setCoordinates(0, arrayOfPoint3f);
/* 462 */     if ((this.flags & true) != 0)
/* 463 */       triangleArray.setNormals(0, arrayOfVector3f); 
/* 464 */     if ((this.flags & 0x2) != 0) {
/* 465 */       triangleArray.setTextureCoordinates(0, 0, arrayOfTexCoord2f);
/*     */     }
/* 467 */     this.geometry = triangleArray;
/* 468 */     return triangleArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GeometryArray processTriangleFan() {
/* 475 */     TriangleFanArray triangleFanArray = null;
/*     */     
/* 477 */     int i = 0;
/*     */     
/* 479 */     int[] arrayOfInt = new int[this.currPrimCnt];
/*     */     
/*     */     byte b1;
/* 482 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/* 483 */       arrayOfInt[b1] = this.currPrimEndVertex[b1] - this.currPrimStartVertex[b1];
/* 484 */       i += arrayOfInt[b1];
/*     */     } 
/*     */ 
/*     */     
/* 488 */     byte b2 = 1;
/* 489 */     if ((this.flags & true) != 0) {
/* 490 */       b2 |= 0x2;
/*     */     }
/* 492 */     if ((this.flags & 0x2) != 0) {
/* 493 */       b2 |= 0x20;
/*     */     }
/*     */ 
/*     */     
/* 497 */     triangleFanArray = new TriangleFanArray(i, b2, 1, this.texCoordSetMap, arrayOfInt);
/*     */ 
/*     */ 
/*     */     
/* 501 */     Point3f[] arrayOfPoint3f = new Point3f[i];
/* 502 */     Vector3f[] arrayOfVector3f = new Vector3f[i];
/* 503 */     TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[i];
/*     */     
/* 505 */     byte b3 = 0;
/*     */ 
/*     */     
/* 508 */     for (b1 = 0; b1 < this.currPrimCnt; b1++) {
/* 509 */       for (int j = this.currPrimStartVertex[b1]; j < this.currPrimEndVertex[b1]; j++) {
/* 510 */         outVertex(arrayOfPoint3f, arrayOfVector3f, arrayOfTexCoord2f, b3++, this.pts, this.normals, this.tcoords, j);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 515 */     for (b1 = 0; b1 < arrayOfPoint3f.length; b1++);
/*     */ 
/*     */ 
/*     */     
/* 519 */     this.numVerts = b3;
/* 520 */     this.numTris = i - this.currPrimCnt * 2;
/*     */ 
/*     */     
/* 523 */     triangleFanArray.setCoordinates(0, arrayOfPoint3f);
/*     */ 
/*     */     
/* 526 */     if ((this.flags & true) != 0) {
/* 527 */       triangleFanArray.setNormals(0, arrayOfVector3f);
/*     */     }
/* 529 */     if ((this.flags & 0x2) != 0) {
/* 530 */       triangleFanArray.setTextureCoordinates(0, 0, arrayOfTexCoord2f);
/*     */     }
/* 532 */     this.geometry = triangleFanArray;
/* 533 */     return triangleFanArray;
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
/*     */ 
/*     */ 
/*     */   
/*     */   void outVertex(Point3f[] paramArrayOfPoint3f1, Vector3f[] paramArrayOfVector3f1, TexCoord2f[] paramArrayOfTexCoord2f1, int paramInt1, Point3f[] paramArrayOfPoint3f2, Vector3f[] paramArrayOfVector3f2, TexCoord2f[] paramArrayOfTexCoord2f2, int paramInt2) {
/* 549 */     paramArrayOfPoint3f1[paramInt1] = new Point3f(paramArrayOfPoint3f2[paramInt2]);
/*     */     
/* 551 */     if ((this.flags & true) != 0) {
/* 552 */       paramArrayOfVector3f1[paramInt1] = new Vector3f(paramArrayOfVector3f2[paramInt2]);
/*     */     }
/* 554 */     if ((this.flags & 0x2) != 0)
/*     */     {
/* 556 */       paramArrayOfTexCoord2f1[paramInt1] = new TexCoord2f(paramArrayOfTexCoord2f2[paramInt2]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\GeomBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */