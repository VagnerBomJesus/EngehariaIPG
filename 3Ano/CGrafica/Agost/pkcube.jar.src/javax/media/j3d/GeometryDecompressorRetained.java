/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Point3d;
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
/*     */ class GeometryDecompressorRetained
/*     */   extends GeometryDecompressor
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   private static final boolean benchmark = false;
/*     */   private static final boolean statistics = false;
/*     */   private static final boolean printInfo = false;
/*     */   private int bufferDataType;
/*     */   private int dataPresent;
/*     */   private int size;
/*     */   private Color4f curColor;
/*     */   private Vector3f curNormal;
/*     */   private GeneralizedVertexList vlist;
/*  47 */   private Point3d lbounds = new Point3d();
/*  48 */   private Point3d ubounds = new Point3d();
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean boundsOnly = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean positionsOnly = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final float bytesPerVertexFudge = 5.3F;
/*     */ 
/*     */ 
/*     */   
/*     */   private long startTime;
/*     */ 
/*     */   
/*     */   private long endTime;
/*     */ 
/*     */   
/*     */   private static final int TYPE_POINT = 1;
/*     */ 
/*     */   
/*     */   private static final int TYPE_LINE = 2;
/*     */ 
/*     */   
/*     */   private static final int TYPE_TRIANGLE = 4;
/*     */ 
/*     */   
/*     */   private static final int FRONTFACE_CCW = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   void setDecompressBoundsOnly(boolean paramBoolean) {
/*  84 */     this.boundsOnly = paramBoolean;
/*  85 */     if (paramBoolean) this.positionsOnly = false;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDecompressPositionsOnly(boolean paramBoolean) {
/*  95 */     this.positionsOnly = paramBoolean;
/*  96 */     if (paramBoolean) this.boundsOnly = false;
/*     */   
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
/*     */   GeometryRetained decompress(CompressedGeometryRetained paramCompressedGeometryRetained) {
/*     */     LineStripArray lineStripArray;
/*     */     PointArray pointArray;
/*     */     TriangleStripArray triangleStripArray;
/* 117 */     if (!checkVersion(paramCompressedGeometryRetained.majorVersionNumber, paramCompressedGeometryRetained.minorVersionNumber)) {
/* 118 */       return null;
/*     */     }
/*     */     
/* 121 */     this.vlist = null;
/* 122 */     this.curColor = null;
/* 123 */     this.curNormal = null;
/* 124 */     this.lbounds.set(1.0D, 1.0D, 1.0D);
/* 125 */     this.ubounds.set(-1.0D, -1.0D, -1.0D);
/*     */ 
/*     */     
/* 128 */     this.bufferDataType = paramCompressedGeometryRetained.bufferType;
/* 129 */     this.dataPresent = paramCompressedGeometryRetained.bufferContents;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     this.size = paramCompressedGeometryRetained.size;
/* 135 */     decompress(paramCompressedGeometryRetained.offset, this.size, paramCompressedGeometryRetained.compressedGeometry);
/*     */     
/* 137 */     if (this.boundsOnly)
/*     */     {
/* 139 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 144 */     switch (this.bufferDataType) {
/*     */       case 4:
/* 146 */         triangleStripArray = this.vlist.toTriangleStripArray();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 160 */         triangleStripArray.retained.setSource(null);
/*     */ 
/*     */         
/* 163 */         return (GeometryRetained)triangleStripArray.retained;case 2: lineStripArray = this.vlist.toLineStripArray(); lineStripArray.retained.setSource(null); return (GeometryRetained)lineStripArray.retained;case 1: pointArray = this.vlist.toPointArray(); pointArray.retained.setSource(null); return (GeometryRetained)pointArray.retained;
/*     */     } 
/*     */     throw new IllegalArgumentException(J3dI18N.getString("GeometryDecompressorRetained0"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void getBoundingBox(BoundingBox paramBoundingBox) {
/* 171 */     paramBoundingBox.setLower(this.lbounds);
/* 172 */     paramBoundingBox.setUpper(this.ubounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputVertexFormat(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/* 182 */     if (this.boundsOnly)
/*     */       return; 
/* 184 */     if (this.vlist != null) {
/* 185 */       throw new IllegalStateException(J3dI18N.getString("GeometryDecompressorRetained1"));
/*     */     }
/*     */     
/* 188 */     byte b = 1;
/*     */     
/* 190 */     if (!this.positionsOnly) {
/* 191 */       if (paramBoolean1) b |= 0x2; 
/* 192 */       if (paramBoolean2) b |= 0x4; 
/* 193 */       if (paramBoolean3) b |= 0x8;
/*     */     
/*     */     } 
/* 196 */     this.vlist = new GeneralizedVertexList(b, 1, (int)(this.size / 5.3F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt) {
/* 206 */     if (paramPoint3f.x < this.lbounds.x) this.lbounds.x = paramPoint3f.x; 
/* 207 */     if (paramPoint3f.y < this.lbounds.y) this.lbounds.y = paramPoint3f.y; 
/* 208 */     if (paramPoint3f.z < this.lbounds.z) this.lbounds.z = paramPoint3f.z;
/*     */     
/* 210 */     if (paramPoint3f.x > this.ubounds.x) this.ubounds.x = paramPoint3f.x; 
/* 211 */     if (paramPoint3f.y > this.ubounds.y) this.ubounds.y = paramPoint3f.y; 
/* 212 */     if (paramPoint3f.z > this.ubounds.z) this.ubounds.z = paramPoint3f.z;
/*     */     
/* 214 */     if (this.boundsOnly)
/* 215 */       return;  if (this.curColor != null) paramColor4f = this.curColor; 
/* 216 */     if (this.curNormal != null) paramVector3f = this.curNormal;
/*     */     
/* 218 */     this.vlist.addVertex(paramPoint3f, paramVector3f, paramColor4f, paramInt);
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
/*     */ 
/*     */   
/*     */   void outputColor(Color4f paramColor4f) {
/* 241 */     if (this.boundsOnly || this.positionsOnly) {
/*     */       return;
/*     */     }
/* 244 */     if ((this.vlist.vertexFormat & 0x4) == 0) {
/* 245 */       if (this.vlist.size() > 0) {
/* 246 */         throw new IllegalStateException(J3dI18N.getString("GeometryDecompressorRetained2"));
/*     */       }
/*     */       
/* 249 */       this.vlist.setVertexFormat(this.vlist.vertexFormat | 0x4);
/*     */     } 
/*     */     
/* 252 */     if (this.curColor == null) this.curColor = new Color4f(); 
/* 253 */     this.curColor.set(paramColor4f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void outputNormal(Vector3f paramVector3f) {
/* 264 */     if (this.boundsOnly || this.positionsOnly) {
/*     */       return;
/*     */     }
/* 267 */     if ((this.vlist.vertexFormat & 0x2) == 0) {
/* 268 */       if (this.vlist.size() > 0) {
/* 269 */         throw new IllegalStateException(J3dI18N.getString("GeometryDecompressorRetained3"));
/*     */       }
/*     */       
/* 272 */       this.vlist.setVertexFormat(this.vlist.vertexFormat | 0x2);
/*     */     } 
/*     */     
/* 275 */     if (this.curNormal == null) this.curNormal = new Vector3f(); 
/* 276 */     this.curNormal.set(paramVector3f);
/*     */   }
/*     */   
/*     */   private void beginPrint() {
/* 280 */     System.err.println("\nGeometryDecompressorRetained");
/*     */     
/* 282 */     switch (this.bufferDataType) {
/*     */       case 4:
/* 284 */         System.err.println(" buffer TYPE_TRIANGLE");
/*     */         break;
/*     */       case 2:
/* 287 */         System.err.println(" buffer TYPE_LINE");
/*     */         break;
/*     */       case 1:
/* 290 */         System.err.println(" buffer TYPE_POINT");
/*     */         break;
/*     */       default:
/* 293 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryDecompressorRetained4"));
/*     */     } 
/*     */ 
/*     */     
/* 297 */     System.err.print(" buffer data present: coords");
/*     */     
/* 299 */     if ((this.dataPresent & true) != 0)
/* 300 */       System.err.print(" normals"); 
/* 301 */     if ((this.dataPresent & 0x2) != 0)
/* 302 */       System.err.print(" colors"); 
/* 303 */     if ((this.dataPresent & 0x4) != 0) {
/* 304 */       System.err.print(" alpha");
/*     */     }
/* 306 */     System.err.println();
/* 307 */     if (this.boundsOnly) System.err.println(" computing bounds only"); 
/* 308 */     if (this.positionsOnly) System.err.println(" computing positions only");
/*     */     
/* 310 */     this.startTime = J3dClock.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   
/* 314 */   private void endPrint() { this.endTime = J3dClock.currentTimeMillis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void printBench() {
/* 324 */     float f = (float)(this.endTime - this.startTime) / 1000.0F;
/*     */     
/* 326 */     if (this.boundsOnly) {
/* 327 */       System.err.println(" decompression took " + f + " sec.\n");
/*     */       
/*     */       return;
/*     */     } 
/* 331 */     System.err.println(" decompression + strip conversion took " + f + " sec.");
/*     */ 
/*     */     
/* 334 */     switch (this.bufferDataType) {
/*     */       case 1:
/* 336 */         System.err.println(" decompressed " + this.vlist.size() + " points at " + (this.vlist.size() / f) + " points/sec.\n");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 342 */         System.err.println(" decompressed " + (this.vlist.vertexCount - this.vlist.stripCount) + " lines at " + ((this.vlist.vertexCount - this.vlist.stripCount) / f) + " lines/sec.\n");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 348 */         System.err.println(" decompressed " + (this.vlist.vertexCount - 2 * this.vlist.stripCount) + " triangles at " + ((this.vlist.vertexCount - 2 * this.vlist.stripCount) / f) + " triangles/sec.\n");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void printStats() {
/* 359 */     System.err.println(" bounding box:\n  lower " + this.lbounds.toString() + "\n  upper " + this.ubounds.toString());
/*     */ 
/*     */     
/* 362 */     if (this.boundsOnly)
/*     */       return; 
/* 364 */     System.err.print(" number of vertices in GeometryArray output: " + this.vlist.vertexCount + "\n" + " GeometryArray vertex data present: coords");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 369 */     if ((this.vlist.vertexFormat & 0x2) != 0) {
/* 370 */       System.err.print(" normals");
/*     */     }
/* 372 */     if ((this.vlist.vertexFormat & 0x4) != 0) {
/* 373 */       System.err.print(" colors");
/*     */     }
/* 375 */     if ((this.vlist.vertexFormat & 0x8) != 0) {
/* 376 */       System.err.print(" alpha");
/*     */     }
/* 378 */     System.err.println("\n number of strips: " + this.vlist.stripCount);
/* 379 */     if (this.vlist.stripCount > 0)
/* 380 */       System.err.println(" vertices/strip: " + (this.vlist.vertexCount / this.vlist.stripCount)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\GeometryDecompressorRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */