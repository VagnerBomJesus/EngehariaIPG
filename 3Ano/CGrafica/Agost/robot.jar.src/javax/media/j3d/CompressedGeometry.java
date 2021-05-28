/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompressedGeometry
/*     */   extends Geometry
/*     */ {
/*     */   CompressedGeometryHeader cgHeader;
/*     */   public static final int ALLOW_COUNT_READ = 0;
/*     */   public static final int ALLOW_HEADER_READ = 1;
/*     */   public static final int ALLOW_GEOMETRY_READ = 2;
/*     */   public static final int ALLOW_REF_DATA_READ = 3;
/*  93 */   private static final int[] readCapabilities = { 0, 1, 2, 3 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   CompressedGeometry() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public CompressedGeometry(CompressedGeometryHeader paramCompressedGeometryHeader, byte[] paramArrayOfByte) { this(paramCompressedGeometryHeader, paramArrayOfByte, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompressedGeometry(CompressedGeometryHeader paramCompressedGeometryHeader, byte[] paramArrayOfByte, boolean paramBoolean) {
/* 167 */     if (paramCompressedGeometryHeader.size + paramCompressedGeometryHeader.start > paramArrayOfByte.length) {
/* 168 */       throw new IllegalArgumentException(J3dI18N.getString("CompressedGeometry0"));
/*     */     }
/*     */ 
/*     */     
/* 172 */     setDefaultReadCapabilities(readCapabilities);
/*     */ 
/*     */     
/* 175 */     this.cgHeader = new CompressedGeometryHeader();
/* 176 */     paramCompressedGeometryHeader.copy(this.cgHeader);
/*     */ 
/*     */     
/* 179 */     ((CompressedGeometryRetained)this.retained).createCompressedGeometry(this.cgHeader, paramArrayOfByte, paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     if (!paramBoolean) {
/* 188 */       this.cgHeader.start = 0;
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
/* 201 */   public CompressedGeometry(CompressedGeometryHeader paramCompressedGeometryHeader, J3DBuffer paramJ3DBuffer) { throw new UnsupportedOperationException(J3dI18N.getString("CompressedGeometry9")); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getByteCount() {
/* 215 */     if (isLiveOrCompiled() && 
/* 216 */       !getCapability(0)) {
/* 217 */       throw new CapabilityNotSetException(J3dI18N.getString("CompressedGeometry1"));
/*     */     }
/*     */     
/* 220 */     return this.cgHeader.size;
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
/*     */   public void getCompressedGeometryHeader(CompressedGeometryHeader paramCompressedGeometryHeader) {
/* 238 */     if (isLiveOrCompiled() && 
/* 239 */       !getCapability(1)) {
/* 240 */       throw new CapabilityNotSetException(J3dI18N.getString("CompressedGeometry2"));
/*     */     }
/*     */     
/* 243 */     this.cgHeader.copy(paramCompressedGeometryHeader);
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
/*     */   public void getCompressedGeometry(byte[] paramArrayOfByte) {
/* 266 */     if (isLiveOrCompiled() && 
/* 267 */       !getCapability(2)) {
/* 268 */       throw new CapabilityNotSetException(J3dI18N.getString("CompressedGeometry3"));
/*     */     }
/*     */     
/* 271 */     if (isByReference()) {
/* 272 */       throw new IllegalStateException(J3dI18N.getString("CompressedGeometry7"));
/*     */     }
/*     */     
/* 275 */     if (this.cgHeader.size > paramArrayOfByte.length) {
/* 276 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("CompressedGeometry4"));
/*     */     }
/*     */     
/* 279 */     ((CompressedGeometryRetained)this.retained).copy(paramArrayOfByte);
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
/*     */   public Shape3D[] decompress() {
/* 296 */     if (isLiveOrCompiled() && 
/* 297 */       !getCapability(2)) {
/* 298 */       throw new CapabilityNotSetException(J3dI18N.getString("CompressedGeometry5"));
/*     */     }
/*     */     
/* 301 */     CompressedGeometryRetained compressedGeometryRetained = (CompressedGeometryRetained)this.retained;
/*     */ 
/*     */     
/* 304 */     GeometryDecompressorShape3D geometryDecompressorShape3D = new GeometryDecompressorShape3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     return geometryDecompressorShape3D.toTriangleStripArrays(compressedGeometryRetained);
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
/* 332 */   public boolean isByReference() { return ((CompressedGeometryRetained)this.retained).isByReference(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getCompressedGeometryRef() {
/* 350 */     if (isLiveOrCompiled() && 
/* 351 */       !getCapability(3)) {
/* 352 */       throw new CapabilityNotSetException(J3dI18N.getString("CompressedGeometry6"));
/*     */     }
/*     */     
/* 355 */     if (!isByReference()) {
/* 356 */       throw new IllegalStateException(J3dI18N.getString("CompressedGeometry8"));
/*     */     }
/*     */     
/* 359 */     return ((CompressedGeometryRetained)this.retained).getReference();
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
/*     */   public J3DBuffer getCompressedGeometryBuffer() {
/* 376 */     if (isLiveOrCompiled() && 
/* 377 */       !getCapability(3)) {
/* 378 */       throw new CapabilityNotSetException(J3dI18N.getString("CompressedGeometry6"));
/*     */     }
/*     */     
/* 381 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 390 */     this.retained = new CompressedGeometryRetained();
/* 391 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 398 */     CompressedGeometry compressedGeometry = new CompressedGeometry();
/*     */ 
/*     */     
/* 401 */     compressedGeometry.cgHeader = new CompressedGeometryHeader();
/* 402 */     this.cgHeader.copy(compressedGeometry.cgHeader);
/*     */ 
/*     */     
/* 405 */     CompressedGeometryRetained compressedGeometryRetained = (CompressedGeometryRetained)this.retained;
/* 406 */     compressedGeometryRetained.duplicate((CompressedGeometryRetained)compressedGeometry.retained);
/*     */ 
/*     */     
/* 409 */     compressedGeometry.duplicateNodeComponent(this);
/* 410 */     return compressedGeometry;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\CompressedGeometry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */