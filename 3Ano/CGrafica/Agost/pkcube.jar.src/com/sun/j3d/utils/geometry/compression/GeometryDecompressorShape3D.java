/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import java.util.ArrayList;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.GeometryArray;
/*     */ import javax.media.j3d.GeometryStripArray;
/*     */ import javax.media.j3d.LineStripArray;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.PointArray;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.TriangleArray;
/*     */ import javax.media.j3d.TriangleStripArray;
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Point3f;
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
/*     */ class GeometryDecompressorShape3D
/*     */   extends GeometryDecompressor
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   private static final boolean benchmark = false;
/*     */   private static final boolean statistics = false;
/*     */   private static final boolean printInfo = false;
/*     */   private int bufferDataType;
/*     */   private int dataPresent;
/*     */   private GeneralizedVertexList vlist;
/*     */   private ArrayList shapes;
/*     */   private Color4f curColor;
/*     */   private Vector3f curNormal;
/*     */   private int origVertexCount;
/*     */   private int stripCount;
/*     */   private int vertexCount;
/*     */   private int triangleCount;
/*     */   private long startTime;
/*     */   private long endTime;
/*     */   private int triOutputType;
/*     */   private static final int TRI_SET = 0;
/*     */   private static final int TRI_STRIP_SET = 1;
/*     */   private static final int TRI_STRIP_AND_FAN_SET = 2;
/*     */   private static final int TRI_STRIP_AND_TRI_SET = 3;
/*     */   private static final int TYPE_POINT = 1;
/*     */   private static final int TYPE_LINE = 2;
/*     */   private static final int TYPE_TRIANGLE = 4;
/*     */   private static final int FRONTFACE_CCW = 1;
/*     */   
/* 128 */   Shape3D[] toTriangleArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 0); }
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
/* 142 */   Shape3D[] toTriangleStripArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 1); }
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
/* 157 */   Shape3D[] toStripAndFanArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 2); }
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
/* 172 */   Shape3D[] toStripAndTriangleArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 3); }
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
/*     */   private Shape3D[] decompress(CompressedGeometryRetained paramCompressedGeometryRetained, int paramInt) {
/* 184 */     if (!checkVersion(paramCompressedGeometryRetained.majorVersionNumber, paramCompressedGeometryRetained.minorVersionNumber)) {
/* 185 */       return null;
/*     */     }
/*     */     
/* 188 */     this.vlist = null;
/* 189 */     this.curColor = null;
/* 190 */     this.curNormal = null;
/*     */ 
/*     */     
/* 193 */     this.bufferDataType = paramCompressedGeometryRetained.bufferType;
/* 194 */     this.dataPresent = paramCompressedGeometryRetained.bufferContents;
/*     */ 
/*     */ 
/*     */     
/* 198 */     this.triOutputType = paramInt;
/* 199 */     this.shapes = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/* 203 */     decompress(paramCompressedGeometryRetained.offset, paramCompressedGeometryRetained.size, paramCompressedGeometryRetained.compressedGeometry);
/*     */ 
/*     */     
/* 206 */     addShape3D();
/*     */ 
/*     */ 
/*     */     
/* 210 */     Shape3D[] arrayOfShape3D = new Shape3D[this.shapes.size()];
/* 211 */     return (Shape3D[])this.shapes.toArray(arrayOfShape3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputVertexFormat(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/* 221 */     if (this.vlist != null)
/*     */     {
/* 223 */       addShape3D();
/*     */     }
/* 225 */     byte b = 1;
/*     */     
/* 227 */     if (paramBoolean1) {
/* 228 */       b |= 0x2;
/*     */     }
/*     */     
/* 231 */     if (paramBoolean2) {
/* 232 */       if (paramBoolean3) {
/* 233 */         b |= 0xC;
/*     */       } else {
/* 235 */         b |= 0x4;
/*     */       } 
/*     */     }
/*     */     
/* 239 */     this.vlist = new GeneralizedVertexList(b, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt) {
/* 248 */     if (this.curNormal != null) paramVector3f = this.curNormal; 
/* 249 */     this.vlist.addVertex(paramPoint3f, paramVector3f, paramColor4f, paramInt);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputColor(Color4f paramColor4f) {
/* 270 */     if (this.vlist.size() > 0) {
/*     */       
/* 272 */       addShape3D();
/*     */ 
/*     */       
/* 275 */       this.vlist = new GeneralizedVertexList(this.vlist.vertexFormat, 1);
/*     */     } 
/*     */     
/* 278 */     if (this.curColor == null) this.curColor = new Color4f(); 
/* 279 */     this.curColor.set(paramColor4f);
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
/*     */   void outputNormal(Vector3f paramVector3f) {
/* 292 */     if ((this.vlist.vertexFormat & 0x2) == 0) {
/* 293 */       if (this.vlist.size() > 0)
/*     */       {
/* 295 */         addShape3D();
/*     */       }
/*     */       
/* 298 */       this.vlist = new GeneralizedVertexList(this.vlist.vertexFormat | 0x2, 1);
/*     */     } 
/*     */     
/* 301 */     if (this.curNormal == null) this.curNormal = new Vector3f(); 
/* 302 */     this.curNormal.set(paramVector3f);
/*     */   } private void addShape3D() {
/*     */     GeometryArray[] arrayOfGeometryArray;
/*     */     GeometryStripArray[] arrayOfGeometryStripArray;
/*     */     TriangleStripArray triangleStripArray;
/*     */     PointArray pointArray;
/*     */     LineStripArray lineStripArray;
/*     */     TriangleArray triangleArray;
/* 310 */     Material material = new Material();
/*     */     
/* 312 */     if (this.curColor != null) {
/* 313 */       if ((this.vlist.vertexFormat & 0xC) != 12) {
/* 314 */         material.setAmbientColor(this.curColor.x, this.curColor.y, this.curColor.z);
/* 315 */         material.setDiffuseColor(this.curColor.x, this.curColor.y, this.curColor.z);
/*     */       } else {
/*     */         
/* 318 */         material.setAmbientColor(this.curColor.x, this.curColor.y, this.curColor.z);
/* 319 */         material.setDiffuseColor(this.curColor.x, this.curColor.y, this.curColor.z, this.curColor.w);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 324 */     if ((this.vlist.vertexFormat & 0x2) == 0) {
/* 325 */       material.setLightingEnable(false);
/*     */     } else {
/* 327 */       material.setLightingEnable(true);
/*     */     } 
/* 329 */     Appearance appearance = new Appearance();
/* 330 */     appearance.setMaterial(material);
/*     */     
/* 332 */     switch (this.bufferDataType) {
/*     */       case 4:
/* 334 */         switch (this.triOutputType) {
/*     */           case 0:
/* 336 */             triangleArray = this.vlist.toTriangleArray();
/* 337 */             if (triangleArray != null)
/* 338 */               this.shapes.add(new Shape3D(triangleArray, appearance)); 
/*     */             return;
/*     */           case 1:
/* 341 */             triangleStripArray = this.vlist.toTriangleStripArray();
/* 342 */             if (triangleStripArray != null)
/* 343 */               this.shapes.add(new Shape3D(triangleStripArray, appearance)); 
/*     */             return;
/*     */           case 2:
/* 346 */             arrayOfGeometryStripArray = this.vlist.toStripAndFanArrays();
/* 347 */             if (arrayOfGeometryStripArray[false] != null)
/* 348 */               this.shapes.add(new Shape3D(arrayOfGeometryStripArray[0], appearance)); 
/* 349 */             if (arrayOfGeometryStripArray[true] != null)
/* 350 */               this.shapes.add(new Shape3D(arrayOfGeometryStripArray[1], appearance)); 
/*     */             return;
/*     */           case 3:
/* 353 */             arrayOfGeometryArray = this.vlist.toStripAndTriangleArrays();
/* 354 */             if (arrayOfGeometryArray[false] != null)
/* 355 */               this.shapes.add(new Shape3D(arrayOfGeometryArray[0], appearance)); 
/* 356 */             if (arrayOfGeometryArray[true] != null)
/* 357 */               this.shapes.add(new Shape3D(arrayOfGeometryArray[1], appearance)); 
/*     */             return;
/*     */         } 
/* 360 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryDecompressorShape3D0"));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 366 */         lineStripArray = this.vlist.toLineStripArray();
/* 367 */         if (lineStripArray != null) {
/* 368 */           this.shapes.add(new Shape3D(lineStripArray, appearance));
/*     */         }
/*     */         return;
/*     */       case 1:
/* 372 */         pointArray = this.vlist.toPointArray();
/* 373 */         if (pointArray != null) {
/* 374 */           this.shapes.add(new Shape3D(pointArray, appearance));
/*     */         }
/*     */         return;
/*     */     } 
/* 378 */     throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryDecompressorShape3D1"));
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
/*     */   private void beginPrint() {
/* 391 */     System.out.println("\nGeometryDecompressorShape3D");
/*     */     
/* 393 */     switch (this.bufferDataType) {
/*     */       case 4:
/* 395 */         System.out.println(" buffer TYPE_TRIANGLE");
/*     */         break;
/*     */       case 2:
/* 398 */         System.out.println(" buffer TYPE_LINE");
/*     */         break;
/*     */       case 1:
/* 401 */         System.out.println(" buffer TYPE_POINT");
/*     */         break;
/*     */       default:
/* 404 */         throw new IllegalArgumentException(J3dUtilsI18N.getString("GeometryDecompressorShape3D1"));
/*     */     } 
/*     */ 
/*     */     
/* 408 */     System.out.print(" buffer data present: coords");
/*     */     
/* 410 */     if ((this.dataPresent & true) != 0)
/* 411 */       System.out.print(" normals"); 
/* 412 */     if ((this.dataPresent & 0x2) != 0)
/* 413 */       System.out.print(" colors"); 
/* 414 */     if ((this.dataPresent & 0x4) != 0) {
/* 415 */       System.out.print(" alpha");
/*     */     }
/* 417 */     System.out.println();
/*     */     
/* 419 */     this.stripCount = 0;
/* 420 */     this.vertexCount = 0;
/* 421 */     this.triangleCount = 0;
/* 422 */     this.origVertexCount = 0;
/*     */     
/* 424 */     this.startTime = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   
/* 428 */   private void endPrint() { this.endTime = System.currentTimeMillis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void printBench() {
/* 438 */     float f = (float)(this.endTime - this.startTime) / 1000.0F;
/* 439 */     System.out.println(" decompression + strip conversion took " + f + " sec.");
/*     */ 
/*     */     
/* 442 */     switch (this.bufferDataType) {
/*     */       case 1:
/* 444 */         System.out.println(" points decompressed: " + this.vertexCount + "\n" + " net decompression rate: " + (this.vertexCount / f) + " points/sec.\n");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 450 */         System.out.println(" lines decompressed: " + (this.vertexCount - this.stripCount) + "\n" + " net decompression rate: " + ((this.vertexCount - this.stripCount) / f) + " lines/sec.\n");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 456 */         System.out.println(" triangles decompressed: " + (this.vertexCount - 2 * this.stripCount) + "\n" + " net decompression rate: " + ((this.vertexCount - 2 * this.stripCount) / f) + " triangles/sec.\n");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void printStats() {
/* 466 */     switch (this.triOutputType) {
/*     */       case 0:
/* 468 */         System.out.println(" using individual triangle output");
/*     */         break;
/*     */       case 1:
/* 471 */         System.out.println(" using strip output");
/*     */         break;
/*     */       case 2:
/* 474 */         System.out.println(" using strips and fans for output");
/*     */         break;
/*     */       case 3:
/* 477 */         System.out.println(" using strips and triangles for output");
/*     */         break;
/*     */     } 
/*     */     
/* 481 */     System.out.print(" number of Shape3D objects: " + this.shapes.size() + "\n number of Shape3D decompressed vertices: ");
/*     */ 
/*     */ 
/*     */     
/* 485 */     if (this.triOutputType == 0 || this.bufferDataType == 1) {
/* 486 */       System.out.println(this.vertexCount);
/*     */     }
/* 488 */     else if (this.triOutputType == 3) {
/* 489 */       System.out.println((this.vertexCount + this.triangleCount * 3) + "\n number of strips: " + this.stripCount + "\n number of individual triangles: " + this.triangleCount);
/*     */ 
/*     */ 
/*     */       
/* 493 */       if (this.stripCount > 0) {
/* 494 */         System.out.println(" vertices/strip: " + (this.vertexCount / this.stripCount) + "\n triangles represented in strips: " + (this.vertexCount - 2 * this.stripCount));
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 500 */       System.out.println(this.vertexCount + "\n number of strips: " + this.stripCount);
/*     */       
/* 502 */       if (this.stripCount > 0) {
/* 503 */         System.out.println(" vertices/strip: " + (this.vertexCount / this.stripCount));
/*     */       }
/*     */     } 
/*     */     
/* 507 */     System.out.print(" vertex data present in last Shape3D: coords");
/* 508 */     if ((this.vlist.vertexFormat & 0x2) != 0) {
/* 509 */       System.out.print(" normals");
/*     */     }
/* 511 */     boolean bool1 = ((this.vlist.vertexFormat & 0xC) == 12) ? 1 : 0;
/*     */     
/* 513 */     boolean bool2 = (!bool1 && (this.vlist.vertexFormat & 0x4) == 4) ? 1 : 0;
/*     */     
/* 515 */     if (bool2 || bool1) {
/* 516 */       System.out.print(" colors");
/* 517 */       if (bool1)
/* 518 */         System.out.print(" alpha"); 
/*     */     } 
/* 520 */     System.out.println();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\compression\GeometryDecompressorShape3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */