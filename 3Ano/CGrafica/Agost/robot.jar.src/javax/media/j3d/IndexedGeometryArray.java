/*      */ package javax.media.j3d;
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
/*      */ public abstract class IndexedGeometryArray
/*      */   extends GeometryArray
/*      */ {
/*      */   public static final int ALLOW_COORDINATE_INDEX_READ = 9;
/*      */   public static final int ALLOW_COORDINATE_INDEX_WRITE = 10;
/*      */   public static final int ALLOW_COLOR_INDEX_READ = 11;
/*      */   public static final int ALLOW_COLOR_INDEX_WRITE = 12;
/*      */   public static final int ALLOW_NORMAL_INDEX_READ = 13;
/*      */   public static final int ALLOW_NORMAL_INDEX_WRITE = 14;
/*      */   public static final int ALLOW_TEXCOORD_INDEX_READ = 15;
/*      */   public static final int ALLOW_TEXCOORD_INDEX_WRITE = 16;
/*      */   public static final int ALLOW_VERTEX_ATTR_INDEX_READ = 24;
/*      */   public static final int ALLOW_VERTEX_ATTR_INDEX_WRITE = 25;
/*      */   
/*   32 */   IndexedGeometryArray() { setDefaultReadCapabilities(readCapabilities); }
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
/*      */ 
/*      */   
/*  110 */   private static final int[] readCapabilities = { 11, 9, 13, 15, 24 };
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
/*      */   public IndexedGeometryArray(int paramInt1, int paramInt2, int paramInt3) {
/*  149 */     super(paramInt1, paramInt2);
/*      */ 
/*      */     
/*  152 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  154 */     ((IndexedGeometryArrayRetained)this.retained).createIndexedGeometryArrayData(paramInt3);
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
/*  194 */   public IndexedGeometryArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4) { this(paramInt1, paramInt2, paramInt3, paramArrayOfInt, 0, null, paramInt4); }
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
/*      */   public IndexedGeometryArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5) {
/*  242 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  247 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  249 */     ((IndexedGeometryArrayRetained)this.retained).createIndexedGeometryArrayData(paramInt5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIndexCount() {
/*  257 */     if (isLiveOrCompiled() && 
/*  258 */       !getCapability(8)) {
/*  259 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray0"));
/*      */     }
/*  261 */     return ((IndexedGeometryArrayRetained)this.retained).getIndexCount();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValidIndexCount(int paramInt) {
/*  296 */     if (isLiveOrCompiled() && 
/*  297 */       !getCapability(20)) {
/*  298 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray16"));
/*      */     }
/*  300 */     ((IndexedGeometryArrayRetained)this.retained).setValidIndexCount(paramInt);
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
/*      */   public int getValidIndexCount() {
/*  317 */     if (isLiveOrCompiled() && 
/*  318 */       !getCapability(8)) {
/*  319 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray17"));
/*      */     }
/*  321 */     return ((IndexedGeometryArrayRetained)this.retained).getValidIndexCount();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialIndexIndex(int paramInt) {
/*  355 */     if (isLiveOrCompiled() && 
/*  356 */       !getCapability(20)) {
/*  357 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray18"));
/*      */     }
/*  359 */     if (paramInt < 0) {
/*  360 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedGeometryArray20"));
/*      */     }
/*      */     
/*  363 */     ((IndexedGeometryArrayRetained)this.retained).setInitialIndexIndex(paramInt);
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
/*      */   public int getInitialIndexIndex() {
/*  375 */     if (isLiveOrCompiled() && 
/*  376 */       !getCapability(8)) {
/*  377 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray19"));
/*      */     }
/*  379 */     return ((IndexedGeometryArrayRetained)this.retained).getInitialIndexIndex();
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
/*  395 */   public void setInitialVertexIndex(int paramInt) { throw new UnsupportedOperationException(); }
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
/*  408 */   public void setInitialCoordIndex(int paramInt) { throw new UnsupportedOperationException(); }
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
/*  421 */   public void setInitialColorIndex(int paramInt) { throw new UnsupportedOperationException(); }
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
/*  434 */   public void setInitialNormalIndex(int paramInt) { throw new UnsupportedOperationException(); }
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
/*  448 */   public void setInitialTexCoordIndex(int paramInt1, int paramInt2) { throw new UnsupportedOperationException(); }
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
/*  462 */   public void setInitialVertexAttrIndex(int paramInt1, int paramInt2) { throw new UnsupportedOperationException(); }
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
/*  477 */   public void setValidVertexCount(int paramInt) { throw new UnsupportedOperationException(); }
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
/*      */   
/*      */   public void setCoordinateIndex(int paramInt1, int paramInt2) {
/*  505 */     if (isLiveOrCompiled() && 
/*  506 */       !getCapability(10)) {
/*  507 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray1"));
/*      */     }
/*      */     
/*  510 */     int i = ((IndexedGeometryArrayRetained)this.retained).vertexFormat;
/*  511 */     if ((i & 0x2000) != 0) {
/*  512 */       throw new IllegalStateException(J3dI18N.getString("IndexedGeometryArray31"));
/*      */     }
/*  514 */     ((IndexedGeometryArrayRetained)this.retained).setCoordinateIndex(paramInt1, paramInt2);
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
/*      */   public void setCoordinateIndices(int paramInt, int[] paramArrayOfInt) {
/*  542 */     if (isLiveOrCompiled() && 
/*  543 */       !getCapability(10)) {
/*  544 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray1"));
/*      */     }
/*      */     
/*  547 */     int i = ((IndexedGeometryArrayRetained)this.retained).vertexFormat;
/*  548 */     if ((i & 0x2000) != 0) {
/*  549 */       throw new IllegalStateException(J3dI18N.getString("IndexedGeometryArray31"));
/*      */     }
/*  551 */     ((IndexedGeometryArrayRetained)this.retained).setCoordinateIndices(paramInt, paramArrayOfInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCoordIndicesRef(int[] paramArrayOfInt) {
/*  583 */     if (isLiveOrCompiled() && 
/*  584 */       !getCapability(19)) {
/*  585 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/*      */     
/*  588 */     int i = ((IndexedGeometryArrayRetained)this.retained).vertexFormat;
/*  589 */     if ((i & 0x2000) == 0) {
/*  590 */       throw new IllegalStateException(J3dI18N.getString("IndexedGeometryArray32"));
/*      */     }
/*  592 */     ((IndexedGeometryArrayRetained)this.retained).setCoordIndicesRef(paramArrayOfInt);
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
/*      */   public void setColorIndex(int paramInt1, int paramInt2) {
/*  618 */     if (isLiveOrCompiled() && 
/*  619 */       !getCapability(12)) {
/*  620 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray3"));
/*      */     }
/*  622 */     ((IndexedGeometryArrayRetained)this.retained).setColorIndex(paramInt1, paramInt2);
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
/*      */   public void setColorIndices(int paramInt, int[] paramArrayOfInt) {
/*  648 */     if (isLiveOrCompiled() && 
/*  649 */       !getCapability(12)) {
/*  650 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray3"));
/*      */     }
/*  652 */     ((IndexedGeometryArrayRetained)this.retained).setColorIndices(paramInt, paramArrayOfInt);
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
/*      */   public void setNormalIndex(int paramInt1, int paramInt2) {
/*  678 */     if (isLiveOrCompiled() && 
/*  679 */       !getCapability(14)) {
/*  680 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray5"));
/*      */     }
/*  682 */     ((IndexedGeometryArrayRetained)this.retained).setNormalIndex(paramInt1, paramInt2);
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
/*      */   public void setNormalIndices(int paramInt, int[] paramArrayOfInt) {
/*  708 */     if (isLiveOrCompiled() && 
/*  709 */       !getCapability(14)) {
/*  710 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray5"));
/*      */     }
/*  712 */     ((IndexedGeometryArrayRetained)this.retained).setNormalIndices(paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  720 */   public void setTextureCoordinateIndex(int paramInt1, int paramInt2) { setTextureCoordinateIndex(0, paramInt1, paramInt2); }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinateIndex(int paramInt1, int paramInt2, int paramInt3) {
/*  755 */     if (isLiveOrCompiled() && 
/*  756 */       !getCapability(16)) {
/*  757 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray7"));
/*      */     }
/*  759 */     ((IndexedGeometryArrayRetained)this.retained).setTextureCoordinateIndex(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  767 */   public void setTextureCoordinateIndices(int paramInt, int[] paramArrayOfInt) { setTextureCoordinateIndices(0, paramInt, paramArrayOfInt); }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinateIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  802 */     if (isLiveOrCompiled() && 
/*  803 */       !getCapability(16)) {
/*  804 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray7"));
/*      */     }
/*  806 */     ((IndexedGeometryArrayRetained)this.retained).setTextureCoordinateIndices(paramInt1, paramInt2, paramArrayOfInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVertexAttrIndex(int paramInt1, int paramInt2, int paramInt3) {
/*  839 */     if (isLiveOrCompiled() && 
/*  840 */       !getCapability(25)) {
/*  841 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray28"));
/*      */     }
/*      */ 
/*      */     
/*  845 */     ((IndexedGeometryArrayRetained)this.retained).setVertexAttrIndex(paramInt1, paramInt2, paramInt3);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVertexAttrIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  878 */     if (isLiveOrCompiled() && 
/*  879 */       !getCapability(25)) {
/*  880 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray28"));
/*      */     }
/*      */ 
/*      */     
/*  884 */     ((IndexedGeometryArrayRetained)this.retained).setVertexAttrIndices(paramInt1, paramInt2, paramArrayOfInt);
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
/*      */   public int getCoordinateIndex(int paramInt) {
/*  900 */     if (isLiveOrCompiled() && 
/*  901 */       !getCapability(9)) {
/*  902 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray9"));
/*      */     }
/*      */     
/*  905 */     int i = ((IndexedGeometryArrayRetained)this.retained).vertexFormat;
/*  906 */     if ((i & 0x2000) != 0) {
/*  907 */       throw new IllegalStateException(J3dI18N.getString("IndexedGeometryArray31"));
/*      */     }
/*  909 */     return ((IndexedGeometryArrayRetained)this.retained).getCoordinateIndex(paramInt);
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
/*      */   public void getCoordinateIndices(int paramInt, int[] paramArrayOfInt) {
/*  925 */     if (isLiveOrCompiled() && 
/*  926 */       !getCapability(9)) {
/*  927 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray9"));
/*      */     }
/*      */     
/*  930 */     int i = ((IndexedGeometryArrayRetained)this.retained).vertexFormat;
/*  931 */     if ((i & 0x2000) != 0) {
/*  932 */       throw new IllegalStateException(J3dI18N.getString("IndexedGeometryArray31"));
/*      */     }
/*  934 */     ((IndexedGeometryArrayRetained)this.retained).getCoordinateIndices(paramInt, paramArrayOfInt);
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
/*      */   public int[] getCoordIndicesRef() {
/*  951 */     if (isLiveOrCompiled() && 
/*  952 */       !getCapability(21)) {
/*  953 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*  955 */     int i = ((IndexedGeometryArrayRetained)this.retained).vertexFormat;
/*  956 */     if ((i & 0x2000) == 0) {
/*  957 */       throw new IllegalStateException(J3dI18N.getString("IndexedGeometryArray32"));
/*      */     }
/*  959 */     return ((IndexedGeometryArrayRetained)this.retained).getCoordIndicesRef();
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
/*      */   public int getColorIndex(int paramInt) {
/*  974 */     if (isLiveOrCompiled() && 
/*  975 */       !getCapability(11)) {
/*  976 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray11"));
/*      */     }
/*  978 */     return ((IndexedGeometryArrayRetained)this.retained).getColorIndex(paramInt);
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
/*      */   public void getColorIndices(int paramInt, int[] paramArrayOfInt) {
/*  995 */     if (isLiveOrCompiled() && 
/*  996 */       !getCapability(11)) {
/*  997 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray11"));
/*      */     }
/*  999 */     ((IndexedGeometryArrayRetained)this.retained).getColorIndices(paramInt, paramArrayOfInt);
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
/*      */   public int getNormalIndex(int paramInt) {
/* 1014 */     if (isLiveOrCompiled() && 
/* 1015 */       !getCapability(13)) {
/* 1016 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray13"));
/*      */     }
/* 1018 */     return ((IndexedGeometryArrayRetained)this.retained).getNormalIndex(paramInt);
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
/*      */   public void getNormalIndices(int paramInt, int[] paramArrayOfInt) {
/* 1036 */     if (isLiveOrCompiled() && 
/* 1037 */       !getCapability(13)) {
/* 1038 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray13"));
/*      */     }
/* 1040 */     ((IndexedGeometryArrayRetained)this.retained).getNormalIndices(paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1048 */   public int getTextureCoordinateIndex(int paramInt) { return getTextureCoordinateIndex(0, paramInt); }
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
/*      */   public int getTextureCoordinateIndex(int paramInt1, int paramInt2) {
/* 1075 */     if (isLiveOrCompiled() && 
/* 1076 */       !getCapability(9)) {
/* 1077 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray15"));
/*      */     }
/* 1079 */     return ((IndexedGeometryArrayRetained)this.retained).getTextureCoordinateIndex(paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1087 */   public void getTextureCoordinateIndices(int paramInt, int[] paramArrayOfInt) { getTextureCoordinateIndices(0, paramInt, paramArrayOfInt); }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureCoordinateIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/* 1119 */     if (isLiveOrCompiled() && 
/* 1120 */       !getCapability(9)) {
/* 1121 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray15"));
/*      */     }
/* 1123 */     ((IndexedGeometryArrayRetained)this.retained).getTextureCoordinateIndices(paramInt1, paramInt2, paramArrayOfInt);
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
/*      */   public int getVertexAttrIndex(int paramInt1, int paramInt2) {
/* 1149 */     if (isLiveOrCompiled() && 
/* 1150 */       !getCapability(24)) {
/* 1151 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray29"));
/*      */     }
/*      */ 
/*      */     
/* 1155 */     return ((IndexedGeometryArrayRetained)this.retained).getVertexAttrIndex(paramInt1, paramInt2);
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
/*      */   public void getVertexAttrIndices(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/* 1183 */     if (isLiveOrCompiled() && 
/* 1184 */       !getCapability(24)) {
/* 1185 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryArray29"));
/*      */     }
/*      */ 
/*      */     
/* 1189 */     ((IndexedGeometryArrayRetained)this.retained).getVertexAttrIndices(paramInt1, paramInt2, paramArrayOfInt);
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
/*      */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 1211 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1217 */     IndexedGeometryArrayRetained indexedGeometryArrayRetained1 = (IndexedGeometryArrayRetained)paramNodeComponent.retained;
/*      */     
/* 1219 */     IndexedGeometryArrayRetained indexedGeometryArrayRetained2 = (IndexedGeometryArrayRetained)this.retained;
/*      */ 
/*      */     
/* 1222 */     int i = indexedGeometryArrayRetained1.getVertexFormat();
/* 1223 */     int[] arrayOfInt = new int[indexedGeometryArrayRetained1.getIndexCount()];
/*      */     
/* 1225 */     if ((i & true) != 0) {
/* 1226 */       indexedGeometryArrayRetained1.getCoordinateIndices(0, arrayOfInt);
/* 1227 */       indexedGeometryArrayRetained2.setCoordinateIndices(0, arrayOfInt);
/*      */     } 
/*      */     
/* 1230 */     if ((i & 0x200) == 0) {
/* 1231 */       if ((i & 0x2) != 0) {
/* 1232 */         indexedGeometryArrayRetained1.getNormalIndices(0, arrayOfInt);
/* 1233 */         indexedGeometryArrayRetained2.setNormalIndices(0, arrayOfInt);
/*      */       } 
/*      */       
/* 1236 */       if ((i & 0x4) != 0) {
/* 1237 */         indexedGeometryArrayRetained1.getColorIndices(0, arrayOfInt);
/* 1238 */         indexedGeometryArrayRetained2.setColorIndices(0, arrayOfInt);
/*      */       } 
/*      */       
/* 1241 */       if ((i & 0x1000) != 0) {
/* 1242 */         for (byte b = 0; b < indexedGeometryArrayRetained1.vertexAttrCount; b++) {
/* 1243 */           indexedGeometryArrayRetained1.getVertexAttrIndices(b, 0, arrayOfInt);
/* 1244 */           indexedGeometryArrayRetained2.setVertexAttrIndices(b, 0, arrayOfInt);
/*      */         } 
/*      */       }
/*      */       
/* 1248 */       if ((i & 0x460) != 0)
/* 1249 */         for (byte b = 0; b < indexedGeometryArrayRetained1.texCoordSetCount; b++) {
/* 1250 */           indexedGeometryArrayRetained1.getTextureCoordinateIndices(b, 0, arrayOfInt);
/* 1251 */           indexedGeometryArrayRetained2.setTextureCoordinateIndices(b, 0, arrayOfInt);
/*      */         }  
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedGeometryArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */