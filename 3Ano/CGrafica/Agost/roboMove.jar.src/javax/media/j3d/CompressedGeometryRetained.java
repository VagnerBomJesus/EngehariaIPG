/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CompressedGeometryRetained
/*     */   extends GeometryRetained
/*     */ {
/*     */   private static final int HEADER_LENGTH = 48;
/*     */   private static final int HEADER_MAJOR_VERSION_OFFSET = 0;
/*     */   private static final int HEADER_MINOR_VERSION_OFFSET = 1;
/*     */   private static final int HEADER_MINOR_MINOR_VERSION_OFFSET = 2;
/*     */   private static final int HEADER_BUFFER_TYPE_OFFSET = 3;
/*     */   private static final int HEADER_BUFFER_DATA_OFFSET = 4;
/*     */   static final byte TYPE_POINT = 1;
/*     */   static final byte TYPE_LINE = 2;
/*     */   static final byte TYPE_TRIANGLE = 4;
/*     */   int majorVersionNumber;
/*     */   int minorVersionNumber;
/*     */   int minorMinorVersionNumber;
/*     */   int packedVersion;
/*     */   int bufferType;
/*     */   int bufferContents;
/*     */   int renderFlags;
/*     */   int offset;
/*     */   int size;
/*     */   byte[] compressedGeometry;
/*     */   private boolean byReference;
/*     */   private byte[] originalCompressedGeometry;
/*     */   private static boolean hardwareDecompression = false;
/*     */   private GeometryRetained pickGeometry;
/*     */   
/*  88 */   private boolean decompressByRef(Context paramContext) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   private boolean decompressHW(Context paramContext, int paramInt1, int paramInt2) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   private void execute(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, byte[] paramArrayOfByte) { assert false : "This method should never be called!"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   void execute(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, int paramInt, boolean paramBoolean3) { execute(paramCanvas3D.ctx, this.packedVersion, this.bufferType, this.bufferContents, this.renderFlags, this.offset, this.size, this.compressedGeometry); }
/*     */ 
/*     */ 
/*     */   
/*     */   CompressedGeometryRetained() {
/*     */     this.byReference = false;
/*     */     this.originalCompressedGeometry = null;
/*     */     this.pickGeometry = null;
/* 126 */     this.geoType = 17;
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.geoBounds.setUpper(1.0D, 1.0D, 1.0D);
/* 131 */     this.geoBounds.setLower(-1.0D, -1.0D, -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void computeBoundingBox() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   void update() { this.isDirty = 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   boolean isByReference() { return this.byReference; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createByCopy(byte[] paramArrayOfByte) {
/* 160 */     this.compressedGeometry = new byte[48 + this.size];
/*     */     
/* 162 */     this.compressedGeometry[0] = (byte)this.majorVersionNumber;
/*     */ 
/*     */     
/* 165 */     this.compressedGeometry[1] = (byte)this.minorVersionNumber;
/*     */ 
/*     */     
/* 168 */     this.compressedGeometry[2] = (byte)this.minorMinorVersionNumber;
/*     */ 
/*     */     
/* 171 */     this.compressedGeometry[3] = (byte)this.bufferType;
/*     */ 
/*     */     
/* 174 */     this.compressedGeometry[4] = (byte)this.bufferContents;
/*     */ 
/*     */     
/* 177 */     System.arraycopy(paramArrayOfByte, this.offset, this.compressedGeometry, 48, this.size);
/*     */ 
/*     */     
/* 180 */     this.offset = 48;
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
/*     */   void createCompressedGeometry(CompressedGeometryHeader paramCompressedGeometryHeader, byte[] paramArrayOfByte, boolean paramBoolean) {
/* 195 */     this.byReference = paramBoolean;
/*     */     
/* 197 */     if (paramCompressedGeometryHeader.lowerBound != null) {
/* 198 */       this.geoBounds.setLower(paramCompressedGeometryHeader.lowerBound);
/*     */     }
/* 200 */     if (paramCompressedGeometryHeader.upperBound != null) {
/* 201 */       this.geoBounds.setUpper(paramCompressedGeometryHeader.upperBound);
/*     */     }
/* 203 */     this.centroid.set(this.geoBounds.getCenter());
/* 204 */     this.recompCentroid = false;
/* 205 */     this.majorVersionNumber = paramCompressedGeometryHeader.majorVersionNumber;
/* 206 */     this.minorVersionNumber = paramCompressedGeometryHeader.minorVersionNumber;
/* 207 */     this.minorMinorVersionNumber = paramCompressedGeometryHeader.minorMinorVersionNumber;
/*     */     
/* 209 */     this.packedVersion = paramCompressedGeometryHeader.majorVersionNumber << 24 | paramCompressedGeometryHeader.minorVersionNumber << 16 | paramCompressedGeometryHeader.minorMinorVersionNumber << 8;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     switch (paramCompressedGeometryHeader.bufferType) {
/*     */       case 0:
/* 216 */         this.bufferType = 1;
/*     */         break;
/*     */       case 1:
/* 219 */         this.bufferType = 2;
/*     */         break;
/*     */       case 2:
/* 222 */         this.bufferType = 4;
/*     */         break;
/*     */     } 
/*     */     
/* 226 */     this.bufferContents = paramCompressedGeometryHeader.bufferDataPresent;
/* 227 */     this.renderFlags = 0;
/*     */     
/* 229 */     this.size = paramCompressedGeometryHeader.size;
/* 230 */     this.offset = paramCompressedGeometryHeader.start;
/*     */     
/* 232 */     if (paramBoolean) {
/*     */ 
/*     */       
/* 235 */       this.compressedGeometry = paramArrayOfByte;
/* 236 */       this.originalCompressedGeometry = paramArrayOfByte;
/*     */     }
/*     */     else {
/*     */       
/* 240 */       createByCopy(paramArrayOfByte);
/* 241 */       this.originalCompressedGeometry = null;
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
/*     */   GeometryRetained getGeometry(boolean paramBoolean, Canvas3D paramCanvas3D) {
/* 253 */     if (paramBoolean) {
/*     */ 
/*     */ 
/*     */       
/* 257 */       GeometryDecompressorRetained geometryDecompressorRetained = new GeometryDecompressorRetained();
/*     */ 
/*     */       
/* 260 */       this.mirrorGeometry = geometryDecompressorRetained.decompress(this);
/* 261 */       geometryDecompressorRetained.getBoundingBox(this.geoBounds);
/* 262 */       this.pickGeometry = this.mirrorGeometry;
/*     */     }
/*     */     else {
/*     */       
/* 266 */       if (hardwareDecompression) {
/* 267 */         return this;
/*     */       }
/*     */       
/* 270 */       if (decompressHW(paramCanvas3D.ctx, this.majorVersionNumber, this.minorVersionNumber)) {
/* 271 */         hardwareDecompression = true;
/*     */ 
/*     */         
/* 274 */         if (isByReference() && !decompressByRef(paramCanvas3D.ctx)) {
/* 275 */           createByCopy(this.compressedGeometry);
/*     */         }
/*     */         
/* 278 */         return this;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 283 */       GeometryDecompressorRetained geometryDecompressorRetained = new GeometryDecompressorRetained();
/*     */ 
/*     */       
/* 286 */       this.mirrorGeometry = geometryDecompressorRetained.decompress(this);
/* 287 */       geometryDecompressorRetained.getBoundingBox(this.geoBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 292 */       this.pickGeometry = this.mirrorGeometry;
/*     */     } 
/*     */     
/* 295 */     return this.mirrorGeometry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeometryRetained getPickGeometry() {
/* 306 */     if (this.pickGeometry != null) {
/* 307 */       return this.pickGeometry;
/*     */     }
/*     */ 
/*     */     
/* 311 */     GeometryDecompressorRetained geometryDecompressorRetained = new GeometryDecompressorRetained();
/* 312 */     geometryDecompressorRetained.setDecompressPositionsOnly(true);
/*     */     
/* 314 */     this.pickGeometry = geometryDecompressorRetained.decompress(this);
/* 315 */     geometryDecompressorRetained.getBoundingBox(this.geoBounds);
/* 316 */     return this.pickGeometry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) {
/* 325 */     GeometryRetained geometryRetained = getPickGeometry();
/* 326 */     return (geometryRetained != null) ? geometryRetained.intersect(paramPickShape, paramPickInfo, paramInt1, paramPoint3d, paramGeometryRetained, paramInt2) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Bounds paramBounds) {
/* 331 */     GeometryRetained geometryRetained = getPickGeometry();
/* 332 */     return (geometryRetained != null) ? geometryRetained.intersect(paramBounds) : 0;
/*     */   }
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 336 */     GeometryRetained geometryRetained = getPickGeometry();
/* 337 */     return (geometryRetained != null) ? geometryRetained.intersect(paramTransform3D, paramGeometryRetained) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 342 */     GeometryRetained geometryRetained = getPickGeometry();
/* 343 */     return (geometryRetained != null) ? geometryRetained.intersect(paramArrayOfPoint3d) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getVertexFormat() {
/* 351 */     byte b = 1;
/*     */     
/* 353 */     if ((this.bufferContents & true) != 0)
/*     */     {
/* 355 */       b |= 0x2;
/*     */     }
/* 357 */     if ((this.bufferContents & 0x2) != 0)
/*     */     {
/* 359 */       b |= 0x4;
/*     */     }
/* 361 */     if ((this.bufferContents & 0x4) != 0)
/*     */     {
/* 363 */       b |= 0x8;
/*     */     }
/* 365 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getBufferType() {
/* 372 */     switch (this.bufferType) {
/*     */       case 1:
/* 374 */         return 0;
/*     */       case 2:
/* 376 */         return 1;
/*     */     } 
/*     */     
/* 379 */     return 2;
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
/* 390 */   void copy(byte[] paramArrayOfByte) { System.arraycopy(this.compressedGeometry, this.offset, paramArrayOfByte, 0, this.size); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   byte[] getReference() { return this.originalCompressedGeometry; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicate(CompressedGeometryRetained paramCompressedGeometryRetained) {
/* 413 */     paramCompressedGeometryRetained.majorVersionNumber = this.majorVersionNumber;
/* 414 */     paramCompressedGeometryRetained.minorVersionNumber = this.minorVersionNumber;
/* 415 */     paramCompressedGeometryRetained.minorMinorVersionNumber = this.minorMinorVersionNumber;
/*     */     
/* 417 */     paramCompressedGeometryRetained.packedVersion = this.packedVersion;
/* 418 */     paramCompressedGeometryRetained.bufferType = this.bufferType;
/* 419 */     paramCompressedGeometryRetained.bufferContents = this.bufferContents;
/* 420 */     paramCompressedGeometryRetained.renderFlags = this.renderFlags;
/*     */     
/* 422 */     paramCompressedGeometryRetained.offset = this.offset;
/* 423 */     paramCompressedGeometryRetained.size = this.size;
/*     */     
/* 425 */     paramCompressedGeometryRetained.geoBounds.setLower(this.geoBounds.lower);
/* 426 */     paramCompressedGeometryRetained.geoBounds.setUpper(this.geoBounds.upper);
/* 427 */     paramCompressedGeometryRetained.pickGeometry = this.pickGeometry;
/* 428 */     paramCompressedGeometryRetained.byReference = this.byReference;
/*     */     
/* 430 */     if (this.byReference) {
/*     */       
/* 432 */       paramCompressedGeometryRetained.compressedGeometry = this.compressedGeometry;
/* 433 */       paramCompressedGeometryRetained.originalCompressedGeometry = this.originalCompressedGeometry;
/*     */     } else {
/*     */       
/* 436 */       paramCompressedGeometryRetained.compressedGeometry = new byte[this.compressedGeometry.length];
/* 437 */       System.arraycopy(this.compressedGeometry, 0, paramCompressedGeometryRetained.compressedGeometry, 0, this.compressedGeometry.length);
/*     */ 
/*     */       
/* 440 */       paramCompressedGeometryRetained.originalCompressedGeometry = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 445 */   int getClassType() { return 7; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\CompressedGeometryRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */