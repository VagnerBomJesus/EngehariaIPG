/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*  83 */   Shape3D[] toTriangleArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 0); }
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
/*  97 */   Shape3D[] toTriangleStripArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 1); }
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
/* 112 */   Shape3D[] toStripAndFanArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 2); }
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
/* 127 */   Shape3D[] toStripAndTriangleArrays(CompressedGeometryRetained paramCompressedGeometryRetained) { return decompress(paramCompressedGeometryRetained, 3); }
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
/* 139 */     if (!checkVersion(paramCompressedGeometryRetained.majorVersionNumber, paramCompressedGeometryRetained.minorVersionNumber)) {
/* 140 */       return null;
/*     */     }
/*     */     
/* 143 */     this.vlist = null;
/* 144 */     this.curColor = null;
/* 145 */     this.curNormal = null;
/*     */ 
/*     */     
/* 148 */     this.bufferDataType = paramCompressedGeometryRetained.bufferType;
/* 149 */     this.dataPresent = paramCompressedGeometryRetained.bufferContents;
/*     */ 
/*     */ 
/*     */     
/* 153 */     this.triOutputType = paramInt;
/* 154 */     this.shapes = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/* 158 */     decompress(paramCompressedGeometryRetained.offset, paramCompressedGeometryRetained.size, paramCompressedGeometryRetained.compressedGeometry);
/*     */ 
/*     */     
/* 161 */     addShape3D();
/*     */ 
/*     */ 
/*     */     
/* 165 */     Shape3D[] arrayOfShape3D = new Shape3D[this.shapes.size()];
/* 166 */     return (Shape3D[])this.shapes.toArray(arrayOfShape3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputVertexFormat(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/* 176 */     if (this.vlist != null)
/*     */     {
/* 178 */       addShape3D();
/*     */     }
/* 180 */     byte b = 1;
/*     */     
/* 182 */     if (paramBoolean1) b |= 0x2; 
/* 183 */     if (paramBoolean2) b |= 0x4; 
/* 184 */     if (paramBoolean3) b |= 0x8;
/*     */     
/* 186 */     this.vlist = new GeneralizedVertexList(b, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt) {
/* 195 */     if (this.curNormal != null) paramVector3f = this.curNormal; 
/* 196 */     this.vlist.addVertex(paramPoint3f, paramVector3f, paramColor4f, paramInt);
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
/* 217 */     if (this.vlist.size() > 0) {
/*     */       
/* 219 */       addShape3D();
/*     */ 
/*     */       
/* 222 */       this.vlist = new GeneralizedVertexList(this.vlist.vertexFormat, 1);
/*     */     } 
/*     */     
/* 225 */     if (this.curColor == null) this.curColor = new Color4f(); 
/* 226 */     this.curColor.set(paramColor4f);
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
/* 239 */     if ((this.vlist.vertexFormat & 0x2) == 0) {
/* 240 */       if (this.vlist.size() > 0)
/*     */       {
/* 242 */         addShape3D();
/*     */       }
/*     */       
/* 245 */       this.vlist = new GeneralizedVertexList(this.vlist.vertexFormat | 0x2, 1);
/*     */     } 
/*     */     
/* 248 */     if (this.curNormal == null) this.curNormal = new Vector3f(); 
/* 249 */     this.curNormal.set(paramVector3f);
/*     */   } private void addShape3D() {
/*     */     GeometryArray[] arrayOfGeometryArray;
/*     */     GeometryStripArray[] arrayOfGeometryStripArray;
/*     */     TriangleStripArray triangleStripArray;
/*     */     PointArray pointArray;
/*     */     TriangleArray triangleArray;
/*     */     LineStripArray lineStripArray;
/* 257 */     Material material = new Material();
/*     */     
/* 259 */     if (this.curColor != null) {
/* 260 */       if ((this.vlist.vertexFormat & 0x8) == 0) {
/* 261 */         material.setAmbientColor(this.curColor.x, this.curColor.y, this.curColor.z);
/* 262 */         material.setDiffuseColor(this.curColor.x, this.curColor.y, this.curColor.z);
/*     */       } else {
/*     */         
/* 265 */         material.setAmbientColor(this.curColor.x, this.curColor.y, this.curColor.z);
/* 266 */         material.setDiffuseColor(this.curColor.x, this.curColor.y, this.curColor.z, this.curColor.w);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 271 */     if ((this.vlist.vertexFormat & 0x2) == 0) {
/* 272 */       material.setLightingEnable(false);
/*     */     } else {
/* 274 */       material.setLightingEnable(true);
/*     */     } 
/* 276 */     Appearance appearance = new Appearance();
/* 277 */     appearance.setMaterial(material);
/*     */     
/* 279 */     switch (this.bufferDataType) {
/*     */       case 4:
/* 281 */         switch (this.triOutputType) {
/*     */           case 0:
/* 283 */             triangleArray = this.vlist.toTriangleArray();
/* 284 */             if (triangleArray != null)
/* 285 */               this.shapes.add(new Shape3D(triangleArray, appearance)); 
/*     */             return;
/*     */           case 1:
/* 288 */             triangleStripArray = this.vlist.toTriangleStripArray();
/* 289 */             if (triangleStripArray != null)
/* 290 */               this.shapes.add(new Shape3D(triangleStripArray, appearance)); 
/*     */             return;
/*     */           case 2:
/* 293 */             arrayOfGeometryStripArray = this.vlist.toStripAndFanArrays();
/* 294 */             if (arrayOfGeometryStripArray[false] != null)
/* 295 */               this.shapes.add(new Shape3D(arrayOfGeometryStripArray[0], appearance)); 
/* 296 */             if (arrayOfGeometryStripArray[true] != null)
/* 297 */               this.shapes.add(new Shape3D(arrayOfGeometryStripArray[1], appearance)); 
/*     */             return;
/*     */           case 3:
/* 300 */             arrayOfGeometryArray = this.vlist.toStripAndTriangleArrays();
/* 301 */             if (arrayOfGeometryArray[false] != null)
/* 302 */               this.shapes.add(new Shape3D(arrayOfGeometryArray[0], appearance)); 
/* 303 */             if (arrayOfGeometryArray[true] != null)
/* 304 */               this.shapes.add(new Shape3D(arrayOfGeometryArray[1], appearance)); 
/*     */             return;
/*     */         } 
/* 307 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryDecompressorShape3D0"));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 313 */         lineStripArray = this.vlist.toLineStripArray();
/* 314 */         if (lineStripArray != null) {
/* 315 */           this.shapes.add(new Shape3D(lineStripArray, appearance));
/*     */         }
/*     */         return;
/*     */       case 1:
/* 319 */         pointArray = this.vlist.toPointArray();
/* 320 */         if (pointArray != null) {
/* 321 */           this.shapes.add(new Shape3D(pointArray, appearance));
/*     */         }
/*     */         return;
/*     */     } 
/* 325 */     throw new IllegalArgumentException(J3dI18N.getString("GeometryDecompressorShape3D1"));
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
/* 338 */     System.err.println("\nGeometryDecompressorShape3D");
/*     */     
/* 340 */     switch (this.bufferDataType) {
/*     */       case 4:
/* 342 */         System.err.println(" buffer TYPE_TRIANGLE");
/*     */         break;
/*     */       case 2:
/* 345 */         System.err.println(" buffer TYPE_LINE");
/*     */         break;
/*     */       case 1:
/* 348 */         System.err.println(" buffer TYPE_POINT");
/*     */         break;
/*     */       default:
/* 351 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryDecompressorShape3D1"));
/*     */     } 
/*     */ 
/*     */     
/* 355 */     System.err.print(" buffer data present: coords");
/*     */     
/* 357 */     if ((this.dataPresent & true) != 0)
/* 358 */       System.err.print(" normals"); 
/* 359 */     if ((this.dataPresent & 0x2) != 0)
/* 360 */       System.err.print(" colors"); 
/* 361 */     if ((this.dataPresent & 0x4) != 0) {
/* 362 */       System.err.print(" alpha");
/*     */     }
/* 364 */     System.err.println();
/*     */     
/* 366 */     this.stripCount = 0;
/* 367 */     this.vertexCount = 0;
/* 368 */     this.triangleCount = 0;
/* 369 */     this.origVertexCount = 0;
/*     */     
/* 371 */     this.startTime = J3dClock.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   
/* 375 */   private void endPrint() { this.endTime = J3dClock.currentTimeMillis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void printBench() {
/* 385 */     float f = (float)(this.endTime - this.startTime) / 1000.0F;
/* 386 */     System.err.println(" decompression + strip conversion took " + f + " sec.");
/*     */ 
/*     */     
/* 389 */     switch (this.bufferDataType) {
/*     */       case 1:
/* 391 */         System.err.println(" points decompressed: " + this.vertexCount + "\n" + " net decompression rate: " + (this.vertexCount / f) + " points/sec.\n");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 397 */         System.err.println(" lines decompressed: " + (this.vertexCount - this.stripCount) + "\n" + " net decompression rate: " + ((this.vertexCount - this.stripCount) / f) + " lines/sec.\n");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 403 */         System.err.println(" triangles decompressed: " + (this.vertexCount - 2 * this.stripCount) + "\n" + " net decompression rate: " + ((this.vertexCount - 2 * this.stripCount) / f) + " triangles/sec.\n");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void printStats() {
/* 413 */     switch (this.triOutputType) {
/*     */       case 0:
/* 415 */         System.err.println(" using individual triangle output");
/*     */         break;
/*     */       case 1:
/* 418 */         System.err.println(" using strip output");
/*     */         break;
/*     */       case 2:
/* 421 */         System.err.println(" using strips and fans for output");
/*     */         break;
/*     */       case 3:
/* 424 */         System.err.println(" using strips and triangles for output");
/*     */         break;
/*     */     } 
/*     */     
/* 428 */     System.err.print(" number of Shape3D objects: " + this.shapes.size() + "\n number of Shape3D decompressed vertices: ");
/*     */ 
/*     */ 
/*     */     
/* 432 */     if (this.triOutputType == 0 || this.bufferDataType == 1) {
/* 433 */       System.err.println(this.vertexCount);
/*     */     }
/* 435 */     else if (this.triOutputType == 3) {
/* 436 */       System.err.println((this.vertexCount + this.triangleCount * 3) + "\n number of strips: " + this.stripCount + "\n number of individual triangles: " + this.triangleCount);
/*     */ 
/*     */ 
/*     */       
/* 440 */       if (this.stripCount > 0) {
/* 441 */         System.err.println(" vertices/strip: " + (this.vertexCount / this.stripCount) + "\n triangles represented in strips: " + (this.vertexCount - 2 * this.stripCount));
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 447 */       System.err.println(this.vertexCount + "\n number of strips: " + this.stripCount);
/*     */       
/* 449 */       if (this.stripCount > 0) {
/* 450 */         System.err.println(" vertices/strip: " + (this.vertexCount / this.stripCount));
/*     */       }
/*     */     } 
/*     */     
/* 454 */     System.err.print(" vertex data present in last Shape3D: coords");
/* 455 */     if ((this.vlist.vertexFormat & 0x2) != 0) {
/* 456 */       System.err.print(" normals");
/*     */     }
/* 458 */     if ((this.vlist.vertexFormat & 0x4) != 0) {
/* 459 */       System.err.print(" colors");
/* 460 */       if ((this.vlist.vertexFormat & 0x8) != 0)
/* 461 */         System.err.print(" alpha"); 
/*     */     } 
/* 463 */     System.err.println();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\GeometryDecompressorShape3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */