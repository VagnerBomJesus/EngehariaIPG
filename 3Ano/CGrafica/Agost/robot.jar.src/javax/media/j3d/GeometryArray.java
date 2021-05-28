/*      */ package javax.media.j3d;
/*      */ 
/*      */ import javax.vecmath.Color3b;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Color4b;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Point2f;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Point4f;
/*      */ import javax.vecmath.TexCoord2f;
/*      */ import javax.vecmath.TexCoord3f;
/*      */ import javax.vecmath.TexCoord4f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class GeometryArray
/*      */   extends Geometry
/*      */ {
/*      */   public static final int ALLOW_COORDINATE_READ = 0;
/*      */   public static final int ALLOW_COORDINATE_WRITE = 1;
/*      */   public static final int ALLOW_COLOR_READ = 2;
/*      */   public static final int ALLOW_COLOR_WRITE = 3;
/*      */   public static final int ALLOW_NORMAL_READ = 4;
/*      */   public static final int ALLOW_NORMAL_WRITE = 5;
/*      */   public static final int ALLOW_TEXCOORD_READ = 6;
/*      */   public static final int ALLOW_TEXCOORD_WRITE = 7;
/*      */   public static final int ALLOW_VERTEX_ATTR_READ = 22;
/*      */   public static final int ALLOW_VERTEX_ATTR_WRITE = 23;
/*      */   public static final int ALLOW_COUNT_READ = 8;
/*      */   public static final int ALLOW_COUNT_WRITE = 20;
/*      */   public static final int ALLOW_FORMAT_READ = 17;
/*      */   public static final int ALLOW_REF_DATA_READ = 21;
/*      */   private static final int J3D_1_2_ALLOW_REF_DATA_READ = 18;
/*      */   public static final int ALLOW_REF_DATA_WRITE = 19;
/*      */   public static final int COORDINATES = 1;
/*      */   public static final int NORMALS = 2;
/*      */   static final int COLOR = 4;
/*      */   static final int WITH_ALPHA = 8;
/*      */   public static final int COLOR_3 = 4;
/*      */   public static final int COLOR_4 = 12;
/*      */   public static final int TEXTURE_COORDINATE_2 = 32;
/*      */   public static final int TEXTURE_COORDINATE_3 = 64;
/*      */   public static final int TEXTURE_COORDINATE_4 = 1024;
/*      */   static final int TEXTURE_COORDINATE = 1120;
/*      */   public static final int BY_REFERENCE = 128;
/*      */   public static final int INTERLEAVED = 256;
/*      */   public static final int USE_NIO_BUFFER = 2048;
/*      */   public static final int USE_COORD_INDEX_ONLY = 512;
/*      */   public static final int VERTEX_ATTRIBUTES = 4096;
/*      */   public static final int BY_REFERENCE_INDICES = 8192;
/*      */   private static final int LAST_FORMAT_BIT = 8192;
/*      */   private TexCoord2f[] texCoord2fArray;
/*      */   private TexCoord3f[] texCoord3fArray;
/*      */   private TexCoord4f[] texCoord4fArray;
/*      */   private TexCoord2f texCoord2fScratch;
/*      */   private TexCoord3f texCoord3fScratch;
/*  352 */   private static final int[] defTexCoordMap = { 0 };
/*      */ 
/*      */   
/*  355 */   private static final int[] readCapabilities = { 2, 0, 8, 17, 4, 21, 6, 22 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   GeometryArray() {
/*      */     this.texCoord2fArray = null;
/*      */     this.texCoord3fArray = null;
/*      */     this.texCoord4fArray = null;
/*      */     this.texCoord2fScratch = null;
/*      */     this.texCoord3fScratch = null;
/*  370 */     setDefaultReadCapabilities(readCapabilities);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  447 */   public GeometryArray(int paramInt1, int paramInt2) { this(paramInt1, paramInt2, ((paramInt2 & 0x460) != 0) ? 1 : 0, ((paramInt2 & 0x460) != 0) ? defTexCoordMap : null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  592 */   public GeometryArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) { this(paramInt1, paramInt2, paramInt3, paramArrayOfInt, 0, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GeometryArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/*      */     this.texCoord2fArray = null;
/*      */     this.texCoord3fArray = null;
/*      */     this.texCoord4fArray = null;
/*      */     this.texCoord2fScratch = null;
/*      */     this.texCoord3fScratch = null;
/*  764 */     if (paramInt1 < 0) {
/*  765 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray96"));
/*      */     }
/*  767 */     if (paramInt3 < 0) {
/*  768 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray124"));
/*      */     }
/*  770 */     if (paramInt4 < 0) {
/*  771 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray125"));
/*      */     }
/*  773 */     if ((paramInt2 & true) == 0) {
/*  774 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray0"));
/*      */     }
/*  776 */     if ((paramInt2 & 0x100) != 0 && (paramInt2 & 0x80) == 0)
/*      */     {
/*  778 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray80"));
/*      */     }
/*  780 */     if ((paramInt2 & 0x100) != 0 && (paramInt2 & 0x1000) != 0)
/*      */     {
/*  782 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray128"));
/*      */     }
/*      */     
/*  785 */     if ((paramInt2 & 0x200) != 0 && !(this instanceof IndexedGeometryArray))
/*      */     {
/*  787 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray135"));
/*      */     }
/*      */ 
/*      */     
/*  791 */     if ((paramInt2 & 0x2000) != 0) {
/*  792 */       if (!(this instanceof IndexedGeometryArray))
/*  793 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray136")); 
/*  794 */       if ((paramInt2 & 0x80) == 0)
/*  795 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray137")); 
/*  796 */       if ((paramInt2 & 0x200) == 0) {
/*  797 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray138"));
/*      */       }
/*      */     } 
/*  800 */     if ((paramInt2 & 0x800) != 0 && (paramInt2 & 0x80) == 0)
/*      */     {
/*  802 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray117"));
/*      */     }
/*  804 */     if ((paramInt2 & 0x460) != 0) {
/*  805 */       if (paramArrayOfInt1 == null) {
/*  806 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray106"));
/*      */       }
/*  808 */       if (paramInt3 == 0) {
/*  809 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray107"));
/*      */       }
/*  811 */       for (byte b = 0; b < paramArrayOfInt1.length; b++) {
/*  812 */         if (paramArrayOfInt1[b] >= paramInt3) {
/*  813 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryArray108"));
/*      */         }
/*      */       } 
/*  816 */       if ((paramInt2 & 0x20) != 0) {
/*  817 */         this.texCoord2fArray = new TexCoord2f[1];
/*  818 */         this.texCoord2fScratch = new TexCoord2f();
/*      */       }
/*  820 */       else if ((paramInt2 & 0x40) != 0) {
/*  821 */         this.texCoord3fArray = new TexCoord3f[1];
/*  822 */         this.texCoord3fScratch = new TexCoord3f();
/*      */       }
/*  824 */       else if ((paramInt2 & 0x400) != 0) {
/*  825 */         this.texCoord4fArray = new TexCoord4f[1];
/*      */       } 
/*      */     } 
/*      */     
/*  829 */     if ((paramInt2 & 0x1000) != 0) {
/*  830 */       if (paramInt4 > 0) {
/*  831 */         if (paramInt4 != paramArrayOfInt2.length) {
/*  832 */           throw new IllegalArgumentException(J3dI18N.getString("GeometryArray132"));
/*      */         }
/*      */         
/*  835 */         for (byte b = 0; b < paramArrayOfInt2.length; b++) {
/*  836 */           if (paramArrayOfInt2[b] < 1 || paramArrayOfInt2[b] > 4) {
/*  837 */             throw new IllegalArgumentException(J3dI18N.getString("GeometryArray133"));
/*      */           }
/*      */         }
/*      */       
/*  841 */       } else if (paramArrayOfInt2 != null && paramArrayOfInt2.length != 0) {
/*  842 */         throw new IllegalArgumentException(J3dI18N.getString("GeometryArray132"));
/*      */       }
/*      */     
/*      */     }
/*  846 */     else if (paramInt4 > 0) {
/*  847 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray131"));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  852 */     setDefaultReadCapabilities(readCapabilities);
/*      */     
/*  854 */     ((GeometryArrayRetained)this.retained).createGeometryArrayData(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
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
/*      */   public int getVertexCount() {
/*  873 */     if (isLiveOrCompiled() && 
/*  874 */       !getCapability(8)) {
/*  875 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray1"));
/*      */     }
/*  877 */     return ((GeometryArrayRetained)this.retained).getVertexCount();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVertexFormat() {
/*  887 */     if (isLiveOrCompiled() && 
/*  888 */       !getCapability(17)) {
/*  889 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray2"));
/*      */     }
/*  891 */     return ((GeometryArrayRetained)this.retained).getVertexFormat();
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
/*  905 */   public int getTexCoordSetCount() { return ((GeometryArrayRetained)this.retained).getTexCoordSetCount(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  919 */   public int getTexCoordSetMapLength() { return ((GeometryArrayRetained)this.retained).getTexCoordSetMapLength(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  935 */   public void getTexCoordSetMap(int[] paramArrayOfInt) { ((GeometryArrayRetained)this.retained).getTexCoordSetMap(paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  949 */   public int getVertexAttrCount() { return ((GeometryArrayRetained)this.retained).getVertexAttrCount(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  964 */   public void getVertexAttrSizes(int[] paramArrayOfInt) { ((GeometryArrayRetained)this.retained).getVertexAttrSizes(paramArrayOfInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateData(GeometryUpdater paramGeometryUpdater) {
/*  991 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*  992 */     if ((i & 0x80) != 0 && isLiveOrCompiled() && !getCapability(19))
/*      */     {
/*      */ 
/*      */       
/*  996 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray81"));
/*      */     }
/*      */     
/*  999 */     ((GeometryArrayRetained)this.retained).updateData(paramGeometryUpdater);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValidVertexCount(int paramInt) {
/* 1050 */     if (isLiveOrCompiled() && 
/* 1051 */       !getCapability(20)) {
/* 1052 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray88"));
/*      */     }
/* 1054 */     if (paramInt < 0) {
/* 1055 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray96"));
/*      */     }
/* 1057 */     ((GeometryArrayRetained)this.retained).setValidVertexCount(paramInt);
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
/*      */   public int getValidVertexCount() {
/* 1075 */     if (isLiveOrCompiled() && 
/* 1076 */       !getCapability(8)) {
/* 1077 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray89"));
/*      */     }
/* 1079 */     return ((GeometryArrayRetained)this.retained).getValidVertexCount();
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
/*      */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 1103 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*      */ 
/*      */ 
/*      */     
/* 1107 */     GeometryArrayRetained geometryArrayRetained1 = (GeometryArrayRetained)paramNodeComponent.retained;
/* 1108 */     GeometryArrayRetained geometryArrayRetained2 = (GeometryArrayRetained)this.retained;
/* 1109 */     int i = geometryArrayRetained1.getVertexFormat();
/* 1110 */     if ((i & 0x80) == 0) {
/* 1111 */       System.arraycopy(geometryArrayRetained1.vertexData, 0, geometryArrayRetained2.vertexData, 0, geometryArrayRetained1.vertexData.length);
/*      */       
/* 1113 */       geometryArrayRetained2.setInitialVertexIndex(geometryArrayRetained1.getInitialVertexIndex());
/*      */     } else {
/*      */       
/* 1116 */       geometryArrayRetained2.setInitialCoordIndex(geometryArrayRetained1.getInitialCoordIndex());
/* 1117 */       geometryArrayRetained2.setInitialColorIndex(geometryArrayRetained1.getInitialColorIndex());
/* 1118 */       geometryArrayRetained2.setInitialNormalIndex(geometryArrayRetained1.getInitialNormalIndex());
/* 1119 */       int j = geometryArrayRetained1.getTexCoordSetCount();
/* 1120 */       int k = geometryArrayRetained1.getVertexAttrCount(); byte b;
/* 1121 */       for (b = 0; b < j; b++) {
/* 1122 */         geometryArrayRetained2.setInitialTexCoordIndex(b, geometryArrayRetained1.getInitialTexCoordIndex(b));
/*      */       }
/* 1124 */       if ((i & 0x100) == 0) {
/* 1125 */         if ((i & 0x800) == 0) {
/*      */           
/* 1127 */           geometryArrayRetained2.setCoordRefFloat(geometryArrayRetained1.getCoordRefFloat());
/* 1128 */           geometryArrayRetained2.setCoordRefDouble(geometryArrayRetained1.getCoordRefDouble());
/* 1129 */           geometryArrayRetained2.setCoordRef3f(geometryArrayRetained1.getCoordRef3f());
/* 1130 */           geometryArrayRetained2.setCoordRef3d(geometryArrayRetained1.getCoordRef3d());
/* 1131 */           geometryArrayRetained2.setColorRefFloat(geometryArrayRetained1.getColorRefFloat());
/* 1132 */           geometryArrayRetained2.setColorRefByte(geometryArrayRetained1.getColorRefByte());
/* 1133 */           if ((i & 0x8) == 0) {
/* 1134 */             geometryArrayRetained2.setColorRef3f(geometryArrayRetained1.getColorRef3f());
/* 1135 */             geometryArrayRetained2.setColorRef3b(geometryArrayRetained1.getColorRef3b());
/*      */           } else {
/* 1137 */             geometryArrayRetained2.setColorRef4f(geometryArrayRetained1.getColorRef4f());
/* 1138 */             geometryArrayRetained2.setColorRef4b(geometryArrayRetained1.getColorRef4b());
/*      */           } 
/* 1140 */           geometryArrayRetained2.setNormalRefFloat(geometryArrayRetained1.getNormalRefFloat());
/* 1141 */           geometryArrayRetained2.setNormalRef3f(geometryArrayRetained1.getNormalRef3f());
/*      */           
/* 1143 */           switch (geometryArrayRetained1.getVertexAttrType()) {
/*      */             case 32768:
/* 1145 */               for (b = 0; b < k; b++) {
/* 1146 */                 geometryArrayRetained2.setVertexAttrRefFloat(b, geometryArrayRetained1.getVertexAttrRefFloat(b));
/*      */               }
/*      */               break;
/*      */           } 
/*      */           
/* 1151 */           switch (geometryArrayRetained1.getTexCoordType()) {
/*      */             case 4096:
/* 1153 */               for (b = 0; b < j; b++) {
/* 1154 */                 geometryArrayRetained2.setTexCoordRefFloat(b, geometryArrayRetained1.getTexCoordRefFloat(b));
/*      */               }
/*      */               break;
/*      */             case 8192:
/* 1158 */               for (b = 0; b < j; b++) {
/* 1159 */                 geometryArrayRetained2.setTexCoordRef2f(b, geometryArrayRetained1.getTexCoordRef2f(b));
/*      */               }
/*      */               break;
/*      */             case 16384:
/* 1163 */               for (b = 0; b < j; b++) {
/* 1164 */                 geometryArrayRetained2.setTexCoordRef3f(b, geometryArrayRetained1.getTexCoordRef3f(b));
/*      */               }
/*      */               break;
/*      */           } 
/*      */         
/*      */         } else {
/* 1170 */           geometryArrayRetained2.setCoordRefBuffer(geometryArrayRetained1.getCoordRefBuffer());
/* 1171 */           geometryArrayRetained2.setColorRefBuffer(geometryArrayRetained1.getColorRefBuffer());
/* 1172 */           geometryArrayRetained2.setNormalRefBuffer(geometryArrayRetained1.getNormalRefBuffer());
/*      */           
/* 1174 */           switch (geometryArrayRetained1.getVertexAttrType()) {
/*      */             case 32768:
/* 1176 */               for (b = 0; b < k; b++) {
/* 1177 */                 geometryArrayRetained2.setVertexAttrRefBuffer(b, geometryArrayRetained1.getVertexAttrRefBuffer(b));
/*      */               }
/*      */               break;
/*      */           } 
/*      */           
/* 1182 */           switch (geometryArrayRetained1.getTexCoordType()) {
/*      */             case 4096:
/* 1184 */               for (b = 0; b < j; b++) {
/* 1185 */                 geometryArrayRetained2.setTexCoordRefBuffer(b, geometryArrayRetained1.getTexCoordRefBuffer(b));
/*      */               }
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1191 */         geometryArrayRetained2.setInterleavedVertices(geometryArrayRetained1.getInterleavedVertices());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialVertexIndex(int paramInt) {
/* 1237 */     if (isLiveOrCompiled() && 
/* 1238 */       !getCapability(20)) {
/* 1239 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray90"));
/*      */     }
/* 1241 */     if (paramInt < 0)
/* 1242 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray97")); 
/* 1243 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1244 */     if ((i & 0x80) != 0 && (i & 0x100) == 0) {
/* 1245 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray105"));
/*      */     }
/* 1247 */     ((GeometryArrayRetained)this.retained).setInitialVertexIndex(paramInt);
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
/*      */   public int getInitialVertexIndex() {
/* 1265 */     if (isLiveOrCompiled() && 
/* 1266 */       !getCapability(8)) {
/* 1267 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray91"));
/*      */     }
/* 1269 */     return ((GeometryArrayRetained)this.retained).getInitialVertexIndex();
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
/*      */   public void setCoordinate(int paramInt, float[] paramArrayOfFloat) {
/* 1284 */     if (isLiveOrCompiled() && 
/* 1285 */       !getCapability(1)) {
/* 1286 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray3"));
/*      */     }
/* 1288 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1289 */     if ((i & 0x80) != 0) {
/* 1290 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1292 */     ((GeometryArrayRetained)this.retained).setCoordinate(paramInt, paramArrayOfFloat);
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
/*      */   public void setCoordinate(int paramInt, double[] paramArrayOfDouble) {
/* 1306 */     if (isLiveOrCompiled() && 
/* 1307 */       !getCapability(1)) {
/* 1308 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray3"));
/*      */     }
/* 1310 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1311 */     if ((i & 0x80) != 0) {
/* 1312 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1314 */     ((GeometryArrayRetained)this.retained).setCoordinate(paramInt, paramArrayOfDouble);
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
/*      */   public void setCoordinate(int paramInt, Point3f paramPoint3f) {
/* 1328 */     if (isLiveOrCompiled() && 
/* 1329 */       !getCapability(1)) {
/* 1330 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray3"));
/*      */     }
/* 1332 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1333 */     if ((i & 0x80) != 0) {
/* 1334 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1336 */     ((GeometryArrayRetained)this.retained).setCoordinate(paramInt, paramPoint3f);
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
/*      */   public void setCoordinate(int paramInt, Point3d paramPoint3d) {
/* 1350 */     if (isLiveOrCompiled() && 
/* 1351 */       !getCapability(1)) {
/* 1352 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray3"));
/*      */     }
/* 1354 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1355 */     if ((i & 0x80) != 0) {
/* 1356 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1358 */     ((GeometryArrayRetained)this.retained).setCoordinate(paramInt, paramPoint3d);
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
/*      */   public void setCoordinates(int paramInt, float[] paramArrayOfFloat) {
/* 1373 */     if (isLiveOrCompiled() && 
/* 1374 */       !getCapability(1)) {
/* 1375 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1377 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1378 */     if ((i & 0x80) != 0) {
/* 1379 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1381 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt, paramArrayOfFloat);
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
/*      */   public void setCoordinates(int paramInt, double[] paramArrayOfDouble) {
/* 1396 */     if (isLiveOrCompiled() && 
/* 1397 */       !getCapability(1)) {
/* 1398 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1400 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1401 */     if ((i & 0x80) != 0) {
/* 1402 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1404 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt, paramArrayOfDouble);
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
/*      */   public void setCoordinates(int paramInt, Point3f[] paramArrayOfPoint3f) {
/* 1419 */     if (isLiveOrCompiled() && 
/* 1420 */       !getCapability(1)) {
/* 1421 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1423 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1424 */     if ((i & 0x80) != 0) {
/* 1425 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1427 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt, paramArrayOfPoint3f);
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
/*      */   public void setCoordinates(int paramInt, Point3d[] paramArrayOfPoint3d) {
/* 1442 */     if (isLiveOrCompiled() && 
/* 1443 */       !getCapability(1)) {
/* 1444 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1446 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1447 */     if ((i & 0x80) != 0) {
/* 1448 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1450 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt, paramArrayOfPoint3d);
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
/*      */   public void setCoordinates(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) {
/* 1468 */     if (isLiveOrCompiled() && 
/* 1469 */       !getCapability(1)) {
/* 1470 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1472 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1473 */     if ((i & 0x80) != 0) {
/* 1474 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1476 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt1, paramArrayOfFloat, paramInt2, paramInt3);
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
/*      */   public void setCoordinates(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3) {
/* 1495 */     if (isLiveOrCompiled() && 
/* 1496 */       !getCapability(1)) {
/* 1497 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1499 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1500 */     if ((i & 0x80) != 0) {
/* 1501 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1503 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt1, paramArrayOfDouble, paramInt2, paramInt3);
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
/*      */   public void setCoordinates(int paramInt1, Point3f[] paramArrayOfPoint3f, int paramInt2, int paramInt3) {
/* 1522 */     if (isLiveOrCompiled() && 
/* 1523 */       !getCapability(1)) {
/* 1524 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1526 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1527 */     if ((i & 0x80) != 0) {
/* 1528 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1530 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt1, paramArrayOfPoint3f, paramInt2, paramInt3);
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
/*      */   public void setCoordinates(int paramInt1, Point3d[] paramArrayOfPoint3d, int paramInt2, int paramInt3) {
/* 1549 */     if (isLiveOrCompiled() && 
/* 1550 */       !getCapability(1)) {
/* 1551 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray7"));
/*      */     }
/* 1553 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1554 */     if ((i & 0x80) != 0) {
/* 1555 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1557 */     ((GeometryArrayRetained)this.retained).setCoordinates(paramInt1, paramArrayOfPoint3d, paramInt2, paramInt3);
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
/*      */   public void setColor(int paramInt, float[] paramArrayOfFloat) {
/* 1574 */     if (isLiveOrCompiled() && 
/* 1575 */       !getCapability(3)) {
/* 1576 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray15"));
/*      */     }
/* 1578 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1579 */     if ((i & 0x80) != 0) {
/* 1580 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1582 */     if ((i & 0x4) == 0) {
/* 1583 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1585 */     ((GeometryArrayRetained)this.retained).setColor(paramInt, paramArrayOfFloat);
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
/*      */   public void setColor(int paramInt, byte[] paramArrayOfByte) {
/* 1601 */     if (isLiveOrCompiled() && 
/* 1602 */       !getCapability(3)) {
/* 1603 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray15"));
/*      */     }
/* 1605 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1606 */     if ((i & 0x80) != 0) {
/* 1607 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1609 */     if ((i & 0x4) == 0) {
/* 1610 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1612 */     ((GeometryArrayRetained)this.retained).setColor(paramInt, paramArrayOfByte);
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
/*      */   public void setColor(int paramInt, Color3f paramColor3f) {
/* 1630 */     if (isLiveOrCompiled() && 
/* 1631 */       !getCapability(3)) {
/* 1632 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray15"));
/*      */     }
/* 1634 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1635 */     if ((i & 0x80) != 0) {
/* 1636 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1638 */     if ((i & 0x4) == 0) {
/* 1639 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1641 */     if ((i & 0x8) != 0) {
/* 1642 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 1644 */     ((GeometryArrayRetained)this.retained).setColor(paramInt, paramColor3f);
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
/*      */   public void setColor(int paramInt, Color4f paramColor4f) {
/* 1662 */     if (isLiveOrCompiled() && 
/* 1663 */       !getCapability(3)) {
/* 1664 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray15"));
/*      */     }
/* 1666 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1667 */     if ((i & 0x80) != 0) {
/* 1668 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1670 */     if ((i & 0x4) == 0) {
/* 1671 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1673 */     if ((i & 0x8) == 0) {
/* 1674 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 1676 */     ((GeometryArrayRetained)this.retained).setColor(paramInt, paramColor4f);
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
/*      */   public void setColor(int paramInt, Color3b paramColor3b) {
/* 1694 */     if (isLiveOrCompiled() && 
/* 1695 */       !getCapability(3)) {
/* 1696 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray15"));
/*      */     }
/* 1698 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1699 */     if ((i & 0x80) != 0) {
/* 1700 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1702 */     if ((i & 0x4) == 0) {
/* 1703 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1705 */     if ((i & 0x8) != 0) {
/* 1706 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 1708 */     ((GeometryArrayRetained)this.retained).setColor(paramInt, paramColor3b);
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
/*      */   public void setColor(int paramInt, Color4b paramColor4b) {
/* 1726 */     if (isLiveOrCompiled() && 
/* 1727 */       !getCapability(3)) {
/* 1728 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray15"));
/*      */     }
/* 1730 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1731 */     if ((i & 0x80) != 0) {
/* 1732 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1734 */     if ((i & 0x4) == 0) {
/* 1735 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1737 */     if ((i & 0x8) == 0) {
/* 1738 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 1740 */     ((GeometryArrayRetained)this.retained).setColor(paramInt, paramColor4b);
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
/*      */   public void setColors(int paramInt, float[] paramArrayOfFloat) {
/* 1757 */     if (isLiveOrCompiled() && 
/* 1758 */       !getCapability(3)) {
/* 1759 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1761 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1762 */     if ((i & 0x80) != 0) {
/* 1763 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1765 */     if ((i & 0x4) == 0) {
/* 1766 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1768 */     ((GeometryArrayRetained)this.retained).setColors(paramInt, paramArrayOfFloat);
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
/*      */   public void setColors(int paramInt, byte[] paramArrayOfByte) {
/* 1785 */     if (isLiveOrCompiled() && 
/* 1786 */       !getCapability(3)) {
/* 1787 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1789 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1790 */     if ((i & 0x80) != 0) {
/* 1791 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1793 */     if ((i & 0x4) == 0) {
/* 1794 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1796 */     ((GeometryArrayRetained)this.retained).setColors(paramInt, paramArrayOfByte);
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
/*      */   public void setColors(int paramInt, Color3f[] paramArrayOfColor3f) {
/* 1814 */     if (isLiveOrCompiled() && 
/* 1815 */       !getCapability(3)) {
/* 1816 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1818 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1819 */     if ((i & 0x80) != 0) {
/* 1820 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1822 */     if ((i & 0x4) == 0) {
/* 1823 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1825 */     if ((i & 0x8) != 0) {
/* 1826 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 1828 */     ((GeometryArrayRetained)this.retained).setColors(paramInt, paramArrayOfColor3f);
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
/*      */   public void setColors(int paramInt, Color4f[] paramArrayOfColor4f) {
/* 1846 */     if (isLiveOrCompiled() && 
/* 1847 */       !getCapability(3)) {
/* 1848 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1850 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1851 */     if ((i & 0x80) != 0) {
/* 1852 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1854 */     if ((i & 0x4) == 0) {
/* 1855 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1857 */     if ((i & 0x8) == 0) {
/* 1858 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 1860 */     ((GeometryArrayRetained)this.retained).setColors(paramInt, paramArrayOfColor4f);
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
/*      */   public void setColors(int paramInt, Color3b[] paramArrayOfColor3b) {
/* 1878 */     if (isLiveOrCompiled() && 
/* 1879 */       !getCapability(3)) {
/* 1880 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1882 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1883 */     if ((i & 0x80) != 0) {
/* 1884 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1886 */     if ((i & 0x4) == 0) {
/* 1887 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1889 */     if ((i & 0x8) != 0) {
/* 1890 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 1892 */     ((GeometryArrayRetained)this.retained).setColors(paramInt, paramArrayOfColor3b);
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
/*      */   public void setColors(int paramInt, Color4b[] paramArrayOfColor4b) {
/* 1910 */     if (isLiveOrCompiled() && 
/* 1911 */       !getCapability(3)) {
/* 1912 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1914 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1915 */     if ((i & 0x80) != 0) {
/* 1916 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1918 */     if ((i & 0x4) == 0) {
/* 1919 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1921 */     if ((i & 0x8) == 0) {
/* 1922 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 1924 */     ((GeometryArrayRetained)this.retained).setColors(paramInt, paramArrayOfColor4b);
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
/*      */   public void setColors(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) {
/* 1944 */     if (isLiveOrCompiled() && 
/* 1945 */       !getCapability(3)) {
/* 1946 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1948 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1949 */     if ((i & 0x80) != 0) {
/* 1950 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1952 */     if ((i & 0x4) == 0) {
/* 1953 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1955 */     ((GeometryArrayRetained)this.retained).setColors(paramInt1, paramArrayOfFloat, paramInt2, paramInt3);
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
/*      */   public void setColors(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3) {
/* 1976 */     if (isLiveOrCompiled() && 
/* 1977 */       !getCapability(3)) {
/* 1978 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 1980 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 1981 */     if ((i & 0x80) != 0) {
/* 1982 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 1984 */     if ((i & 0x4) == 0) {
/* 1985 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 1987 */     ((GeometryArrayRetained)this.retained).setColors(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
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
/*      */   public void setColors(int paramInt1, Color3f[] paramArrayOfColor3f, int paramInt2, int paramInt3) {
/* 2009 */     if (isLiveOrCompiled() && 
/* 2010 */       !getCapability(3)) {
/* 2011 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 2013 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2014 */     if ((i & 0x80) != 0) {
/* 2015 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2017 */     if ((i & 0x4) == 0) {
/* 2018 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 2020 */     if ((i & 0x8) != 0) {
/* 2021 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 2023 */     ((GeometryArrayRetained)this.retained).setColors(paramInt1, paramArrayOfColor3f, paramInt2, paramInt3);
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
/*      */   public void setColors(int paramInt1, Color4f[] paramArrayOfColor4f, int paramInt2, int paramInt3) {
/* 2045 */     if (isLiveOrCompiled() && 
/* 2046 */       !getCapability(3)) {
/* 2047 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 2049 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2050 */     if ((i & 0x80) != 0) {
/* 2051 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2053 */     if ((i & 0x4) == 0) {
/* 2054 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 2056 */     if ((i & 0x8) == 0) {
/* 2057 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 2059 */     ((GeometryArrayRetained)this.retained).setColors(paramInt1, paramArrayOfColor4f, paramInt2, paramInt3);
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
/*      */   public void setColors(int paramInt1, Color3b[] paramArrayOfColor3b, int paramInt2, int paramInt3) {
/* 2081 */     if (isLiveOrCompiled() && 
/* 2082 */       !getCapability(3)) {
/* 2083 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 2085 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2086 */     if ((i & 0x80) != 0) {
/* 2087 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2089 */     if ((i & 0x4) == 0) {
/* 2090 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 2092 */     if ((i & 0x8) != 0) {
/* 2093 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 2095 */     ((GeometryArrayRetained)this.retained).setColors(paramInt1, paramArrayOfColor3b, paramInt2, paramInt3);
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
/*      */   public void setColors(int paramInt1, Color4b[] paramArrayOfColor4b, int paramInt2, int paramInt3) {
/* 2117 */     if (isLiveOrCompiled() && 
/* 2118 */       !getCapability(3)) {
/* 2119 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray21"));
/*      */     }
/* 2121 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2122 */     if ((i & 0x80) != 0) {
/* 2123 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2125 */     if ((i & 0x4) == 0) {
/* 2126 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 2128 */     if ((i & 0x8) == 0) {
/* 2129 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 2131 */     ((GeometryArrayRetained)this.retained).setColors(paramInt1, paramArrayOfColor4b, paramInt2, paramInt3);
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
/*      */   public void setNormal(int paramInt, float[] paramArrayOfFloat) {
/* 2148 */     if (isLiveOrCompiled() && 
/* 2149 */       !getCapability(5)) {
/* 2150 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray33"));
/*      */     }
/* 2152 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2153 */     if ((i & 0x80) != 0) {
/* 2154 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2156 */     if ((i & 0x2) == 0) {
/* 2157 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 2159 */     ((GeometryArrayRetained)this.retained).setNormal(paramInt, paramArrayOfFloat);
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
/*      */   public void setNormal(int paramInt, Vector3f paramVector3f) {
/* 2175 */     if (isLiveOrCompiled() && 
/* 2176 */       !getCapability(5)) {
/* 2177 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray33"));
/*      */     }
/* 2179 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2180 */     if ((i & 0x80) != 0) {
/* 2181 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2183 */     if ((i & 0x2) == 0) {
/* 2184 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 2186 */     ((GeometryArrayRetained)this.retained).setNormal(paramInt, paramVector3f);
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
/*      */   public void setNormals(int paramInt, float[] paramArrayOfFloat) {
/* 2203 */     if (isLiveOrCompiled() && 
/* 2204 */       !getCapability(5)) {
/* 2205 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray35"));
/*      */     }
/* 2207 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2208 */     if ((i & 0x80) != 0) {
/* 2209 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2211 */     if ((i & 0x2) == 0) {
/* 2212 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 2214 */     ((GeometryArrayRetained)this.retained).setNormals(paramInt, paramArrayOfFloat);
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
/*      */   public void setNormals(int paramInt, Vector3f[] paramArrayOfVector3f) {
/* 2231 */     if (isLiveOrCompiled() && 
/* 2232 */       !getCapability(5)) {
/* 2233 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray35"));
/*      */     }
/* 2235 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2236 */     if ((i & 0x80) != 0) {
/* 2237 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2239 */     if ((i & 0x2) == 0) {
/* 2240 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 2242 */     ((GeometryArrayRetained)this.retained).setNormals(paramInt, paramArrayOfVector3f);
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
/*      */   public void setNormals(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) {
/* 2262 */     if (isLiveOrCompiled() && 
/* 2263 */       !getCapability(5)) {
/* 2264 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray35"));
/*      */     }
/* 2266 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2267 */     if ((i & 0x80) != 0) {
/* 2268 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2270 */     if ((i & 0x2) == 0) {
/* 2271 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 2273 */     ((GeometryArrayRetained)this.retained).setNormals(paramInt1, paramArrayOfFloat, paramInt2, paramInt3);
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
/*      */   public void setNormals(int paramInt1, Vector3f[] paramArrayOfVector3f, int paramInt2, int paramInt3) {
/* 2293 */     if (isLiveOrCompiled() && 
/* 2294 */       !getCapability(5)) {
/* 2295 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray35"));
/*      */     }
/* 2297 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2298 */     if ((i & 0x80) != 0) {
/* 2299 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 2301 */     if ((i & 0x2) == 0) {
/* 2302 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 2304 */     ((GeometryArrayRetained)this.retained).setNormals(paramInt1, paramArrayOfVector3f, paramInt2, paramInt3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2313 */   public void setTextureCoordinate(int paramInt, float[] paramArrayOfFloat) { setTextureCoordinate(0, paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinate(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 2341 */     if (isLiveOrCompiled() && 
/* 2342 */       !getCapability(7)) {
/* 2343 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray39"));
/*      */     }
/* 2345 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfFloat, 0, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinate(int paramInt, Point2f paramPoint2f) {
/* 2354 */     this.texCoord2fScratch.set(paramPoint2f);
/* 2355 */     setTextureCoordinate(0, paramInt, this.texCoord2fScratch);
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
/*      */   public void setTextureCoordinate(int paramInt1, int paramInt2, TexCoord2f paramTexCoord2f) {
/* 2385 */     if (isLiveOrCompiled() && 
/* 2386 */       !getCapability(7)) {
/* 2387 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray39"));
/*      */     }
/* 2389 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x440) != 0)
/*      */     {
/* 2391 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray94"));
/*      */     }
/* 2393 */     this.texCoord2fArray[0] = paramTexCoord2f;
/* 2394 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, this.texCoord2fArray, 0, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinate(int paramInt, Point3f paramPoint3f) {
/* 2403 */     this.texCoord3fScratch.set(paramPoint3f);
/* 2404 */     setTextureCoordinate(0, paramInt, this.texCoord3fScratch);
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
/*      */   public void setTextureCoordinate(int paramInt1, int paramInt2, TexCoord3f paramTexCoord3f) {
/* 2434 */     if (isLiveOrCompiled() && 
/* 2435 */       !getCapability(7)) {
/* 2436 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray39"));
/*      */     }
/* 2438 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x420) != 0)
/*      */     {
/* 2440 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray95"));
/*      */     }
/* 2442 */     this.texCoord3fArray[0] = paramTexCoord3f;
/* 2443 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, this.texCoord3fArray, 0, 1);
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
/*      */   public void setTextureCoordinate(int paramInt1, int paramInt2, TexCoord4f paramTexCoord4f) {
/* 2474 */     if (isLiveOrCompiled() && 
/* 2475 */       !getCapability(7)) {
/* 2476 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray39"));
/*      */     }
/* 2478 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x60) != 0)
/*      */     {
/* 2480 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray109"));
/*      */     }
/* 2482 */     this.texCoord4fArray[0] = paramTexCoord4f;
/* 2483 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, this.texCoord4fArray, 0, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2492 */   public void setTextureCoordinates(int paramInt, float[] paramArrayOfFloat) { setTextureCoordinates(0, paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 2521 */     if (isLiveOrCompiled() && 
/* 2522 */       !getCapability(7)) {
/* 2523 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2525 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2526 */     if ((i & 0x20) != 0) {
/* 2527 */       ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfFloat, 0, paramArrayOfFloat.length / 2);
/*      */     }
/* 2529 */     else if ((i & 0x40) != 0) {
/* 2530 */       ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfFloat, 0, paramArrayOfFloat.length / 3);
/*      */     } else {
/*      */       
/* 2533 */       ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfFloat, 0, paramArrayOfFloat.length / 4);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt, Point2f[] paramArrayOfPoint2f) {
/* 2543 */     if (isLiveOrCompiled() && 
/* 2544 */       !getCapability(7)) {
/* 2545 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2547 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(0, paramInt, paramArrayOfPoint2f, 0, paramArrayOfPoint2f.length);
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
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord2f[] paramArrayOfTexCoord2f) {
/* 2580 */     if (isLiveOrCompiled() && 
/* 2581 */       !getCapability(7)) {
/* 2582 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2584 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x440) != 0)
/*      */     {
/* 2586 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray94"));
/*      */     }
/* 2588 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord2f, 0, paramArrayOfTexCoord2f.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt, Point3f[] paramArrayOfPoint3f) {
/* 2597 */     if (isLiveOrCompiled() && 
/* 2598 */       !getCapability(7)) {
/* 2599 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2601 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(0, paramInt, paramArrayOfPoint3f, 0, paramArrayOfPoint3f.length);
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
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord3f[] paramArrayOfTexCoord3f) {
/* 2634 */     if (isLiveOrCompiled() && 
/* 2635 */       !getCapability(7)) {
/* 2636 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/*      */     
/* 2639 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x420) != 0)
/*      */     {
/* 2641 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray95"));
/*      */     }
/* 2643 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord3f, 0, paramArrayOfTexCoord3f.length);
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
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord4f[] paramArrayOfTexCoord4f) {
/* 2676 */     if (isLiveOrCompiled() && 
/* 2677 */       !getCapability(7)) {
/* 2678 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/*      */     
/* 2681 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x60) != 0)
/*      */     {
/* 2683 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray109"));
/*      */     }
/* 2685 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord4f, 0, paramArrayOfTexCoord4f.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2695 */   public void setTextureCoordinates(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) { setTextureCoordinates(0, paramInt1, paramArrayOfFloat, paramInt2, paramInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, float[] paramArrayOfFloat, int paramInt3, int paramInt4) {
/* 2728 */     if (isLiveOrCompiled() && 
/* 2729 */       !getCapability(7)) {
/* 2730 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2732 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfFloat, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt1, Point2f[] paramArrayOfPoint2f, int paramInt2, int paramInt3) {
/* 2742 */     if (isLiveOrCompiled() && 
/* 2743 */       !getCapability(7)) {
/* 2744 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2746 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(0, paramInt1, paramArrayOfPoint2f, paramInt2, paramInt3);
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
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord2f[] paramArrayOfTexCoord2f, int paramInt3, int paramInt4) {
/* 2784 */     if (isLiveOrCompiled() && 
/* 2785 */       !getCapability(7)) {
/* 2786 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2788 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x440) != 0)
/*      */     {
/* 2790 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray94"));
/*      */     }
/* 2792 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord2f, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureCoordinates(int paramInt1, Point3f[] paramArrayOfPoint3f, int paramInt2, int paramInt3) {
/* 2802 */     if (isLiveOrCompiled() && 
/* 2803 */       !getCapability(7)) {
/* 2804 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2806 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(0, paramInt1, paramArrayOfPoint3f, paramInt2, paramInt3);
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
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord3f[] paramArrayOfTexCoord3f, int paramInt3, int paramInt4) {
/* 2843 */     if (isLiveOrCompiled() && 
/* 2844 */       !getCapability(7)) {
/* 2845 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2847 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x420) != 0)
/*      */     {
/* 2849 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray95"));
/*      */     }
/* 2851 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord3f, paramInt3, paramInt4);
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
/*      */   public void setTextureCoordinates(int paramInt1, int paramInt2, TexCoord4f[] paramArrayOfTexCoord4f, int paramInt3, int paramInt4) {
/* 2888 */     if (isLiveOrCompiled() && 
/* 2889 */       !getCapability(7)) {
/* 2890 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray42"));
/*      */     }
/* 2892 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x60) != 0)
/*      */     {
/* 2894 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray109"));
/*      */     }
/* 2896 */     ((GeometryArrayRetained)this.retained).setTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord4f, paramInt3, paramInt4);
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
/*      */   public void setVertexAttr(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 2926 */     if (isLiveOrCompiled() && 
/* 2927 */       !getCapability(23)) {
/* 2928 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 2932 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2933 */     if ((i & 0x80) != 0) {
/* 2934 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 2937 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfFloat, 0, 1);
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
/*      */   public void setVertexAttr(int paramInt1, int paramInt2, Point2f paramPoint2f) {
/* 2967 */     if (isLiveOrCompiled() && 
/* 2968 */       !getCapability(23)) {
/* 2969 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 2973 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 2974 */     if ((i & 0x80) != 0) {
/* 2975 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 2978 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 2979 */     if (j != 2) {
/* 2980 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 2983 */     ((GeometryArrayRetained)this.retained).setVertexAttr(paramInt1, paramInt2, paramPoint2f);
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
/*      */   public void setVertexAttr(int paramInt1, int paramInt2, Point3f paramPoint3f) {
/* 3013 */     if (isLiveOrCompiled() && 
/* 3014 */       !getCapability(23)) {
/* 3015 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3019 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3020 */     if ((i & 0x80) != 0) {
/* 3021 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3024 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3025 */     if (j != 3) {
/* 3026 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3029 */     ((GeometryArrayRetained)this.retained).setVertexAttr(paramInt1, paramInt2, paramPoint3f);
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
/*      */   public void setVertexAttr(int paramInt1, int paramInt2, Point4f paramPoint4f) {
/* 3059 */     if (isLiveOrCompiled() && 
/* 3060 */       !getCapability(23)) {
/* 3061 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3065 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3066 */     if ((i & 0x80) != 0) {
/* 3067 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3070 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3071 */     if (j != 4) {
/* 3072 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3075 */     ((GeometryArrayRetained)this.retained).setVertexAttr(paramInt1, paramInt2, paramPoint4f);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 3104 */     if (isLiveOrCompiled() && 
/* 3105 */       !getCapability(23)) {
/* 3106 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3110 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3111 */     if ((i & 0x80) != 0) {
/* 3112 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3115 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3116 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfFloat, 0, paramArrayOfFloat.length / j);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, Point2f[] paramArrayOfPoint2f) {
/* 3149 */     if (isLiveOrCompiled() && 
/* 3150 */       !getCapability(23)) {
/* 3151 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3155 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3156 */     if ((i & 0x80) != 0) {
/* 3157 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3160 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3161 */     if (j != 2) {
/* 3162 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3165 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint2f, 0, paramArrayOfPoint2f.length);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f) {
/* 3198 */     if (isLiveOrCompiled() && 
/* 3199 */       !getCapability(23)) {
/* 3200 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3204 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3205 */     if ((i & 0x80) != 0) {
/* 3206 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3209 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3210 */     if (j != 3) {
/* 3211 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3214 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint3f, 0, paramArrayOfPoint3f.length);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, Point4f[] paramArrayOfPoint4f) {
/* 3247 */     if (isLiveOrCompiled() && 
/* 3248 */       !getCapability(23)) {
/* 3249 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3253 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3254 */     if ((i & 0x80) != 0) {
/* 3255 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3258 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3259 */     if (j != 4) {
/* 3260 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3263 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint4f, 0, paramArrayOfPoint4f.length);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, float[] paramArrayOfFloat, int paramInt3, int paramInt4) {
/* 3297 */     if (isLiveOrCompiled() && 
/* 3298 */       !getCapability(23)) {
/* 3299 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3303 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3304 */     if ((i & 0x80) != 0) {
/* 3305 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3308 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfFloat, paramInt3, paramInt4);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, Point2f[] paramArrayOfPoint2f, int paramInt3, int paramInt4) {
/* 3346 */     if (isLiveOrCompiled() && 
/* 3347 */       !getCapability(23)) {
/* 3348 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3352 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3353 */     if ((i & 0x80) != 0) {
/* 3354 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3357 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3358 */     if (j != 2) {
/* 3359 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3362 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint2f, paramInt3, paramInt4);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f, int paramInt3, int paramInt4) {
/* 3400 */     if (isLiveOrCompiled() && 
/* 3401 */       !getCapability(23)) {
/* 3402 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3406 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3407 */     if ((i & 0x80) != 0) {
/* 3408 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3411 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3412 */     if (j != 3) {
/* 3413 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3416 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint3f, paramInt3, paramInt4);
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
/*      */   public void setVertexAttrs(int paramInt1, int paramInt2, Point4f[] paramArrayOfPoint4f, int paramInt3, int paramInt4) {
/* 3454 */     if (isLiveOrCompiled() && 
/* 3455 */       !getCapability(23)) {
/* 3456 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray126"));
/*      */     }
/*      */ 
/*      */     
/* 3460 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3461 */     if ((i & 0x80) != 0) {
/* 3462 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 3465 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 3466 */     if (j != 4) {
/* 3467 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 3470 */     ((GeometryArrayRetained)this.retained).setVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint4f, paramInt3, paramInt4);
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
/*      */   public void getCoordinate(int paramInt, float[] paramArrayOfFloat) {
/* 3486 */     if (isLiveOrCompiled() && 
/* 3487 */       !getCapability(0)) {
/* 3488 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray48"));
/*      */     }
/* 3490 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3491 */     if ((i & 0x80) != 0) {
/* 3492 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3494 */     ((GeometryArrayRetained)this.retained).getCoordinate(paramInt, paramArrayOfFloat);
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
/*      */   public void getCoordinate(int paramInt, double[] paramArrayOfDouble) {
/* 3508 */     if (isLiveOrCompiled() && 
/* 3509 */       !getCapability(0)) {
/* 3510 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray48"));
/*      */     }
/* 3512 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3513 */     if ((i & 0x80) != 0) {
/* 3514 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3516 */     ((GeometryArrayRetained)this.retained).getCoordinate(paramInt, paramArrayOfDouble);
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
/*      */   public void getCoordinate(int paramInt, Point3f paramPoint3f) {
/* 3530 */     if (isLiveOrCompiled() && 
/* 3531 */       !getCapability(0)) {
/* 3532 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray48"));
/*      */     }
/* 3534 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3535 */     if ((i & 0x80) != 0) {
/* 3536 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3538 */     ((GeometryArrayRetained)this.retained).getCoordinate(paramInt, paramPoint3f);
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
/*      */   public void getCoordinate(int paramInt, Point3d paramPoint3d) {
/* 3552 */     if (isLiveOrCompiled() && 
/* 3553 */       !getCapability(0)) {
/* 3554 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray48"));
/*      */     }
/* 3556 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3557 */     if ((i & 0x80) != 0) {
/* 3558 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3560 */     ((GeometryArrayRetained)this.retained).getCoordinate(paramInt, paramPoint3d);
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
/*      */   public void getCoordinates(int paramInt, float[] paramArrayOfFloat) {
/* 3582 */     if (isLiveOrCompiled() && 
/* 3583 */       !getCapability(0)) {
/* 3584 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray52"));
/*      */     }
/* 3586 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3587 */     if ((i & 0x80) != 0) {
/* 3588 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3590 */     ((GeometryArrayRetained)this.retained).getCoordinates(paramInt, paramArrayOfFloat);
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
/*      */   public void getCoordinates(int paramInt, double[] paramArrayOfDouble) {
/* 3612 */     if (isLiveOrCompiled() && 
/* 3613 */       !getCapability(0)) {
/* 3614 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray52"));
/*      */     }
/* 3616 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3617 */     if ((i & 0x80) != 0) {
/* 3618 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3620 */     ((GeometryArrayRetained)this.retained).getCoordinates(paramInt, paramArrayOfDouble);
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
/*      */   public void getCoordinates(int paramInt, Point3f[] paramArrayOfPoint3f) {
/* 3642 */     if (isLiveOrCompiled() && 
/* 3643 */       !getCapability(0)) {
/* 3644 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray52"));
/*      */     }
/* 3646 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3647 */     if ((i & 0x80) != 0) {
/* 3648 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3650 */     ((GeometryArrayRetained)this.retained).getCoordinates(paramInt, paramArrayOfPoint3f);
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
/*      */   public void getCoordinates(int paramInt, Point3d[] paramArrayOfPoint3d) {
/* 3672 */     if (isLiveOrCompiled() && 
/* 3673 */       !getCapability(0)) {
/* 3674 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray52"));
/*      */     }
/* 3676 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3677 */     if ((i & 0x80) != 0) {
/* 3678 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3680 */     ((GeometryArrayRetained)this.retained).getCoordinates(paramInt, paramArrayOfPoint3d);
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
/*      */   public void getColor(int paramInt, float[] paramArrayOfFloat) {
/* 3696 */     if (isLiveOrCompiled() && 
/* 3697 */       !getCapability(2)) {
/* 3698 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray56"));
/*      */     }
/* 3700 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3701 */     if ((i & 0x80) != 0) {
/* 3702 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3704 */     if ((i & 0x4) == 0) {
/* 3705 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3707 */     ((GeometryArrayRetained)this.retained).getColor(paramInt, paramArrayOfFloat);
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
/*      */   public void getColor(int paramInt, byte[] paramArrayOfByte) {
/* 3723 */     if (isLiveOrCompiled() && 
/* 3724 */       !getCapability(2)) {
/* 3725 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray56"));
/*      */     }
/* 3727 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3728 */     if ((i & 0x80) != 0) {
/* 3729 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3731 */     if ((i & 0x4) == 0) {
/* 3732 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3734 */     ((GeometryArrayRetained)this.retained).getColor(paramInt, paramArrayOfByte);
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
/*      */   public void getColor(int paramInt, Color3f paramColor3f) {
/* 3750 */     if (isLiveOrCompiled() && 
/* 3751 */       !getCapability(2)) {
/* 3752 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray56"));
/*      */     }
/* 3754 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3755 */     if ((i & 0x80) != 0) {
/* 3756 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3758 */     if ((i & 0x4) == 0) {
/* 3759 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3761 */     if ((i & 0x8) != 0) {
/* 3762 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 3764 */     ((GeometryArrayRetained)this.retained).getColor(paramInt, paramColor3f);
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
/*      */   public void getColor(int paramInt, Color4f paramColor4f) {
/* 3780 */     if (isLiveOrCompiled() && 
/* 3781 */       !getCapability(2)) {
/* 3782 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray56"));
/*      */     }
/* 3784 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3785 */     if ((i & 0x80) != 0) {
/* 3786 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3788 */     if ((i & 0x4) == 0) {
/* 3789 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3791 */     if ((i & 0x8) == 0) {
/* 3792 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 3794 */     ((GeometryArrayRetained)this.retained).getColor(paramInt, paramColor4f);
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
/*      */   public void getColor(int paramInt, Color3b paramColor3b) {
/* 3810 */     if (isLiveOrCompiled() && 
/* 3811 */       !getCapability(2)) {
/* 3812 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray56"));
/*      */     }
/* 3814 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3815 */     if ((i & 0x80) != 0) {
/* 3816 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3818 */     if ((i & 0x4) == 0) {
/* 3819 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3821 */     if ((i & 0x8) != 0) {
/* 3822 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 3824 */     ((GeometryArrayRetained)this.retained).getColor(paramInt, paramColor3b);
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
/*      */   public void getColor(int paramInt, Color4b paramColor4b) {
/* 3840 */     if (isLiveOrCompiled() && 
/* 3841 */       !getCapability(2)) {
/* 3842 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray56"));
/*      */     }
/* 3844 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3845 */     if ((i & 0x80) != 0) {
/* 3846 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3848 */     if ((i & 0x4) == 0) {
/* 3849 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3851 */     if ((i & 0x8) == 0) {
/* 3852 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 3854 */     ((GeometryArrayRetained)this.retained).getColor(paramInt, paramColor4b);
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
/*      */   public void getColors(int paramInt, float[] paramArrayOfFloat) {
/* 3878 */     if (isLiveOrCompiled() && 
/* 3879 */       !getCapability(2)) {
/* 3880 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray62"));
/*      */     }
/* 3882 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3883 */     if ((i & 0x80) != 0) {
/* 3884 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3886 */     if ((i & 0x4) == 0) {
/* 3887 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3889 */     ((GeometryArrayRetained)this.retained).getColors(paramInt, paramArrayOfFloat);
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
/*      */   public void getColors(int paramInt, byte[] paramArrayOfByte) {
/* 3913 */     if (isLiveOrCompiled() && 
/* 3914 */       !getCapability(2)) {
/* 3915 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray62"));
/*      */     }
/* 3917 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3918 */     if ((i & 0x80) != 0) {
/* 3919 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3921 */     if ((i & 0x4) == 0) {
/* 3922 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3924 */     ((GeometryArrayRetained)this.retained).getColors(paramInt, paramArrayOfByte);
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
/*      */   public void getColors(int paramInt, Color3f[] paramArrayOfColor3f) {
/* 3949 */     if (isLiveOrCompiled() && 
/* 3950 */       !getCapability(2)) {
/* 3951 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray62"));
/*      */     }
/* 3953 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3954 */     if ((i & 0x80) != 0) {
/* 3955 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3957 */     if ((i & 0x4) == 0) {
/* 3958 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3960 */     if ((i & 0x8) != 0) {
/* 3961 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 3963 */     ((GeometryArrayRetained)this.retained).getColors(paramInt, paramArrayOfColor3f);
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
/*      */   public void getColors(int paramInt, Color4f[] paramArrayOfColor4f) {
/* 3988 */     if (isLiveOrCompiled() && 
/* 3989 */       !getCapability(2)) {
/* 3990 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray62"));
/*      */     }
/* 3992 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 3993 */     if ((i & 0x80) != 0) {
/* 3994 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 3996 */     if ((i & 0x4) == 0) {
/* 3997 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 3999 */     if ((i & 0x8) == 0) {
/* 4000 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 4002 */     ((GeometryArrayRetained)this.retained).getColors(paramInt, paramArrayOfColor4f);
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
/*      */   public void getColors(int paramInt, Color3b[] paramArrayOfColor3b) {
/* 4027 */     if (isLiveOrCompiled() && 
/* 4028 */       !getCapability(2)) {
/* 4029 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray62"));
/*      */     }
/* 4031 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4032 */     if ((i & 0x80) != 0) {
/* 4033 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4035 */     if ((i & 0x4) == 0) {
/* 4036 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 4038 */     if ((i & 0x8) != 0) {
/* 4039 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 4041 */     ((GeometryArrayRetained)this.retained).getColors(paramInt, paramArrayOfColor3b);
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
/*      */   public void getColors(int paramInt, Color4b[] paramArrayOfColor4b) {
/* 4066 */     if (isLiveOrCompiled() && 
/* 4067 */       !getCapability(2)) {
/* 4068 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray62"));
/*      */     }
/* 4070 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4071 */     if ((i & 0x80) != 0) {
/* 4072 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4074 */     if ((i & 0x4) == 0) {
/* 4075 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray76"));
/*      */     }
/* 4077 */     if ((i & 0x8) == 0) {
/* 4078 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 4080 */     ((GeometryArrayRetained)this.retained).getColors(paramInt, paramArrayOfColor4b);
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
/*      */   public void getNormal(int paramInt, float[] paramArrayOfFloat) {
/* 4095 */     if (isLiveOrCompiled() && 
/* 4096 */       !getCapability(4)) {
/* 4097 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray68"));
/*      */     }
/* 4099 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4100 */     if ((i & 0x80) != 0) {
/* 4101 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4103 */     if ((i & 0x2) == 0) {
/* 4104 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 4106 */     ((GeometryArrayRetained)this.retained).getNormal(paramInt, paramArrayOfFloat);
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
/*      */   public void getNormal(int paramInt, Vector3f paramVector3f) {
/* 4120 */     if (isLiveOrCompiled() && 
/* 4121 */       !getCapability(4)) {
/* 4122 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray68"));
/*      */     }
/* 4124 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4125 */     if ((i & 0x80) != 0) {
/* 4126 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4128 */     if ((i & 0x2) == 0) {
/* 4129 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 4131 */     ((GeometryArrayRetained)this.retained).getNormal(paramInt, paramVector3f);
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
/*      */   public void getNormals(int paramInt, float[] paramArrayOfFloat) {
/* 4153 */     if (isLiveOrCompiled() && 
/* 4154 */       !getCapability(4)) {
/* 4155 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray70"));
/*      */     }
/* 4157 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4158 */     if ((i & 0x80) != 0) {
/* 4159 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4161 */     if ((i & 0x2) == 0) {
/* 4162 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 4164 */     ((GeometryArrayRetained)this.retained).getNormals(paramInt, paramArrayOfFloat);
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
/*      */   public void getNormals(int paramInt, Vector3f[] paramArrayOfVector3f) {
/* 4186 */     if (isLiveOrCompiled() && 
/* 4187 */       !getCapability(4)) {
/* 4188 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray70"));
/*      */     }
/* 4190 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4191 */     if ((i & 0x80) != 0) {
/* 4192 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4194 */     if ((i & 0x2) == 0) {
/* 4195 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray77"));
/*      */     }
/* 4197 */     ((GeometryArrayRetained)this.retained).getNormals(paramInt, paramArrayOfVector3f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4205 */   public void getTextureCoordinate(int paramInt, float[] paramArrayOfFloat) { getTextureCoordinate(0, paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureCoordinate(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 4232 */     if (isLiveOrCompiled() && 
/* 4233 */       !getCapability(6)) {
/* 4234 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray72"));
/*      */     }
/* 4236 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4237 */     if ((i & 0x80) != 0) {
/* 4238 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4240 */     if ((i & 0x460) == 0) {
/* 4241 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4243 */     ((GeometryArrayRetained)this.retained).getTextureCoordinate(paramInt1, paramInt2, paramArrayOfFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureCoordinate(int paramInt, Point2f paramPoint2f) {
/* 4252 */     getTextureCoordinate(0, paramInt, this.texCoord2fArray[0]);
/* 4253 */     paramPoint2f.set(this.texCoord2fArray[0]);
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
/*      */   public void getTextureCoordinate(int paramInt1, int paramInt2, TexCoord2f paramTexCoord2f) {
/* 4283 */     if (isLiveOrCompiled() && 
/* 4284 */       !getCapability(6)) {
/* 4285 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray72"));
/*      */     }
/* 4287 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4288 */     if ((i & 0x80) != 0) {
/* 4289 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4291 */     if ((i & 0x460) == 0) {
/* 4292 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4294 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x440) != 0)
/*      */     {
/* 4296 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray94"));
/*      */     }
/* 4298 */     ((GeometryArrayRetained)this.retained).getTextureCoordinate(paramInt1, paramInt2, paramTexCoord2f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureCoordinate(int paramInt, Point3f paramPoint3f) {
/* 4307 */     getTextureCoordinate(0, paramInt, this.texCoord3fArray[0]);
/* 4308 */     paramPoint3f.set(this.texCoord3fArray[0]);
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
/*      */   public void getTextureCoordinate(int paramInt1, int paramInt2, TexCoord3f paramTexCoord3f) {
/* 4338 */     if (isLiveOrCompiled() && 
/* 4339 */       !getCapability(6)) {
/* 4340 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray72"));
/*      */     }
/* 4342 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4343 */     if ((i & 0x80) != 0) {
/* 4344 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4346 */     if ((i & 0x460) == 0) {
/* 4347 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4349 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x420) != 0)
/*      */     {
/* 4351 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray95")); } 
/* 4352 */     ((GeometryArrayRetained)this.retained).getTextureCoordinate(paramInt1, paramInt2, paramTexCoord3f);
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
/*      */   public void getTextureCoordinate(int paramInt1, int paramInt2, TexCoord4f paramTexCoord4f) {
/* 4383 */     if (isLiveOrCompiled() && 
/* 4384 */       !getCapability(6)) {
/* 4385 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray72"));
/*      */     }
/* 4387 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4388 */     if ((i & 0x80) != 0) {
/* 4389 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4391 */     if ((i & 0x460) == 0) {
/* 4392 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4394 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x60) != 0)
/*      */     {
/* 4396 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray109")); } 
/* 4397 */     ((GeometryArrayRetained)this.retained).getTextureCoordinate(paramInt1, paramInt2, paramTexCoord4f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4407 */   public void getTextureCoordinates(int paramInt, float[] paramArrayOfFloat) { getTextureCoordinates(0, paramInt, paramArrayOfFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureCoordinates(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 4442 */     if (isLiveOrCompiled() && 
/* 4443 */       !getCapability(6)) {
/* 4444 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray75"));
/*      */     }
/* 4446 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4447 */     if ((i & 0x80) != 0) {
/* 4448 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4450 */     if ((i & 0x460) == 0) {
/* 4451 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4453 */     ((GeometryArrayRetained)this.retained).getTextureCoordinates(paramInt1, paramInt2, paramArrayOfFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureCoordinates(int paramInt, Point2f[] paramArrayOfPoint2f) {
/* 4462 */     if (isLiveOrCompiled() && 
/* 4463 */       !getCapability(6)) {
/* 4464 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray75"));
/*      */     }
/* 4466 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4467 */     if ((i & 0x80) != 0) {
/* 4468 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4470 */     if ((i & 0x460) == 0) {
/* 4471 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4473 */     ((GeometryArrayRetained)this.retained).getTextureCoordinates(0, paramInt, paramArrayOfPoint2f);
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
/*      */   public void getTextureCoordinates(int paramInt1, int paramInt2, TexCoord2f[] paramArrayOfTexCoord2f) {
/* 4512 */     if (isLiveOrCompiled() && 
/* 4513 */       !getCapability(6)) {
/* 4514 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray75"));
/*      */     }
/* 4516 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4517 */     if ((i & 0x80) != 0) {
/* 4518 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4520 */     if ((i & 0x460) == 0) {
/* 4521 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4523 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x440) != 0)
/*      */     {
/* 4525 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray94")); } 
/* 4526 */     ((GeometryArrayRetained)this.retained).getTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord2f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getTextureCoordinates(int paramInt, Point3f[] paramArrayOfPoint3f) {
/* 4535 */     if (isLiveOrCompiled() && 
/* 4536 */       !getCapability(6)) {
/* 4537 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray75"));
/*      */     }
/* 4539 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4540 */     if ((i & 0x80) != 0) {
/* 4541 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4543 */     if ((i & 0x460) == 0) {
/* 4544 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4546 */     ((GeometryArrayRetained)this.retained).getTextureCoordinates(0, paramInt, paramArrayOfPoint3f);
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
/*      */   public void getTextureCoordinates(int paramInt1, int paramInt2, TexCoord3f[] paramArrayOfTexCoord3f) {
/* 4585 */     if (isLiveOrCompiled() && 
/* 4586 */       !getCapability(6)) {
/* 4587 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray75"));
/*      */     }
/* 4589 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4590 */     if ((i & 0x80) != 0) {
/* 4591 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4593 */     if ((i & 0x460) == 0) {
/* 4594 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4596 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x420) != 0)
/*      */     {
/* 4598 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray95")); } 
/* 4599 */     ((GeometryArrayRetained)this.retained).getTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord3f);
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
/*      */   public void getTextureCoordinates(int paramInt1, int paramInt2, TexCoord4f[] paramArrayOfTexCoord4f) {
/* 4639 */     if (isLiveOrCompiled() && 
/* 4640 */       !getCapability(6)) {
/* 4641 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray75"));
/*      */     }
/* 4643 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4644 */     if ((i & 0x80) != 0) {
/* 4645 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/* 4647 */     if ((i & 0x460) == 0) {
/* 4648 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("GeometryArray79"));
/*      */     }
/* 4650 */     if ((((GeometryArrayRetained)this.retained).vertexFormat & 0x60) != 0)
/*      */     {
/* 4652 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray109"));
/*      */     }
/* 4654 */     ((GeometryArrayRetained)this.retained).getTextureCoordinates(paramInt1, paramInt2, paramArrayOfTexCoord4f);
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
/*      */   public void getVertexAttr(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 4681 */     if (isLiveOrCompiled() && 
/* 4682 */       !getCapability(22)) {
/* 4683 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 4687 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4688 */     if ((i & 0x80) != 0) {
/* 4689 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 4692 */     ((GeometryArrayRetained)this.retained).getVertexAttr(paramInt1, paramInt2, paramArrayOfFloat);
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
/*      */   public void getVertexAttr(int paramInt1, int paramInt2, Point2f paramPoint2f) {
/* 4721 */     if (isLiveOrCompiled() && 
/* 4722 */       !getCapability(22)) {
/* 4723 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 4727 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4728 */     if ((i & 0x80) != 0) {
/* 4729 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 4732 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 4733 */     if (j != 2) {
/* 4734 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 4737 */     ((GeometryArrayRetained)this.retained).getVertexAttr(paramInt1, paramInt2, paramPoint2f);
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
/*      */   public void getVertexAttr(int paramInt1, int paramInt2, Point3f paramPoint3f) {
/* 4766 */     if (isLiveOrCompiled() && 
/* 4767 */       !getCapability(22)) {
/* 4768 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 4772 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4773 */     if ((i & 0x80) != 0) {
/* 4774 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 4777 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 4778 */     if (j != 3) {
/* 4779 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 4782 */     ((GeometryArrayRetained)this.retained).getVertexAttr(paramInt1, paramInt2, paramPoint3f);
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
/*      */   public void getVertexAttr(int paramInt1, int paramInt2, Point4f paramPoint4f) {
/* 4811 */     if (isLiveOrCompiled() && 
/* 4812 */       !getCapability(22)) {
/* 4813 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 4817 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4818 */     if ((i & 0x80) != 0) {
/* 4819 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 4822 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 4823 */     if (j != 4) {
/* 4824 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 4827 */     ((GeometryArrayRetained)this.retained).getVertexAttr(paramInt1, paramInt2, paramPoint4f);
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
/*      */   public void getVertexAttrs(int paramInt1, int paramInt2, float[] paramArrayOfFloat) {
/* 4862 */     if (isLiveOrCompiled() && 
/* 4863 */       !getCapability(22)) {
/* 4864 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 4868 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4869 */     if ((i & 0x80) != 0) {
/* 4870 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 4873 */     ((GeometryArrayRetained)this.retained).getVertexAttrs(paramInt1, paramInt2, paramArrayOfFloat);
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
/*      */   public void getVertexAttrs(int paramInt1, int paramInt2, Point2f[] paramArrayOfPoint2f) {
/* 4910 */     if (isLiveOrCompiled() && 
/* 4911 */       !getCapability(22)) {
/* 4912 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 4916 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4917 */     if ((i & 0x80) != 0) {
/* 4918 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 4921 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 4922 */     if (j != 2) {
/* 4923 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 4926 */     ((GeometryArrayRetained)this.retained).getVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint2f);
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
/*      */   public void getVertexAttrs(int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f) {
/* 4963 */     if (isLiveOrCompiled() && 
/* 4964 */       !getCapability(22)) {
/* 4965 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 4969 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 4970 */     if ((i & 0x80) != 0) {
/* 4971 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 4974 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 4975 */     if (j != 3) {
/* 4976 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 4979 */     ((GeometryArrayRetained)this.retained).getVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint3f);
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
/*      */   public void getVertexAttrs(int paramInt1, int paramInt2, Point4f[] paramArrayOfPoint4f) {
/* 5016 */     if (isLiveOrCompiled() && 
/* 5017 */       !getCapability(22)) {
/* 5018 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray127"));
/*      */     }
/*      */ 
/*      */     
/* 5022 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5023 */     if ((i & 0x80) != 0) {
/* 5024 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray82"));
/*      */     }
/*      */     
/* 5027 */     int j = ((GeometryArrayRetained)this.retained).vertexAttrSizes[paramInt1];
/* 5028 */     if (j != 4) {
/* 5029 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray134"));
/*      */     }
/*      */     
/* 5032 */     ((GeometryArrayRetained)this.retained).getVertexAttrs(paramInt1, paramInt2, paramArrayOfPoint4f);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialCoordIndex(int paramInt) {
/* 5080 */     if (isLiveOrCompiled() && 
/* 5081 */       !getCapability(20)) {
/* 5082 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray90"));
/*      */     }
/* 5084 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5085 */     if (paramInt < 0)
/* 5086 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray97")); 
/* 5087 */     if ((i & 0x80) == 0) {
/* 5088 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5090 */     if ((i & 0x100) != 0) {
/* 5091 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5093 */     ((GeometryArrayRetained)this.retained).setInitialCoordIndex(paramInt);
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
/*      */   public int getInitialCoordIndex() {
/* 5112 */     if (isLiveOrCompiled() && 
/* 5113 */       !getCapability(8)) {
/* 5114 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray91"));
/*      */     }
/* 5116 */     return ((GeometryArrayRetained)this.retained).getInitialCoordIndex();
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
/*      */   
/*      */   public void setInitialColorIndex(int paramInt) {
/* 5158 */     if (isLiveOrCompiled() && 
/* 5159 */       !getCapability(20)) {
/* 5160 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray90"));
/*      */     }
/* 5162 */     if (paramInt < 0)
/* 5163 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray97")); 
/* 5164 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5165 */     if ((i & 0x80) == 0) {
/* 5166 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5168 */     if ((i & 0x100) != 0) {
/* 5169 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5171 */     ((GeometryArrayRetained)this.retained).setInitialColorIndex(paramInt);
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
/*      */   public int getInitialColorIndex() {
/* 5190 */     if (isLiveOrCompiled() && 
/* 5191 */       !getCapability(8)) {
/* 5192 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray91"));
/*      */     }
/* 5194 */     return ((GeometryArrayRetained)this.retained).getInitialColorIndex();
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
/*      */   
/*      */   public void setInitialNormalIndex(int paramInt) {
/* 5236 */     if (isLiveOrCompiled() && 
/* 5237 */       !getCapability(20)) {
/* 5238 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray90"));
/*      */     }
/* 5240 */     if (paramInt < 0) {
/* 5241 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray97"));
/*      */     }
/* 5243 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5244 */     if ((i & 0x80) == 0) {
/* 5245 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5247 */     if ((i & 0x100) != 0) {
/* 5248 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5250 */     ((GeometryArrayRetained)this.retained).setInitialNormalIndex(paramInt);
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
/*      */   public int getInitialNormalIndex() {
/* 5269 */     if (isLiveOrCompiled() && 
/* 5270 */       !getCapability(8)) {
/* 5271 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray91"));
/*      */     }
/* 5273 */     return ((GeometryArrayRetained)this.retained).getInitialNormalIndex();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialTexCoordIndex(int paramInt1, int paramInt2) {
/* 5322 */     if (isLiveOrCompiled() && 
/* 5323 */       !getCapability(20)) {
/* 5324 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray90"));
/*      */     }
/* 5326 */     if (paramInt2 < 0) {
/* 5327 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray97"));
/*      */     }
/* 5329 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5330 */     if ((i & 0x80) == 0) {
/* 5331 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5333 */     if ((i & 0x100) != 0) {
/* 5334 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5336 */     ((GeometryArrayRetained)this.retained).setInitialTexCoordIndex(paramInt1, paramInt2);
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
/*      */   public int getInitialTexCoordIndex(int paramInt) {
/* 5366 */     if (isLiveOrCompiled() && 
/* 5367 */       !getCapability(8)) {
/* 5368 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray91"));
/*      */     }
/* 5370 */     return ((GeometryArrayRetained)this.retained).getInitialTexCoordIndex(paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialVertexAttrIndex(int paramInt1, int paramInt2) {
/* 5419 */     if (isLiveOrCompiled() && 
/* 5420 */       !getCapability(20)) {
/* 5421 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray90"));
/*      */     }
/*      */ 
/*      */     
/* 5425 */     if (paramInt2 < 0) {
/* 5426 */       throw new IllegalArgumentException(J3dI18N.getString("GeometryArray97"));
/*      */     }
/*      */     
/* 5429 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5430 */     if ((i & 0x80) == 0) {
/* 5431 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/*      */     
/* 5434 */     if ((i & 0x100) != 0) {
/* 5435 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/*      */     
/* 5438 */     ((GeometryArrayRetained)this.retained).setInitialVertexAttrIndex(paramInt1, paramInt2);
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
/*      */   public int getInitialVertexAttrIndex(int paramInt) {
/* 5467 */     if (isLiveOrCompiled() && 
/* 5468 */       !getCapability(8)) {
/* 5469 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray91"));
/*      */     }
/*      */ 
/*      */     
/* 5473 */     return ((GeometryArrayRetained)this.retained).getInitialVertexAttrIndex(paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCoordRefBuffer(J3DBuffer paramJ3DBuffer) {
/* 5520 */     if (isLiveOrCompiled() && 
/* 5521 */       !getCapability(19)) {
/* 5522 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 5524 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 5526 */     if ((i & 0x800) == 0) {
/* 5527 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 5529 */     if ((i & 0x100) != 0) {
/* 5530 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5532 */     ((GeometryArrayRetained)this.retained).setCoordRefBuffer(paramJ3DBuffer);
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
/*      */   public J3DBuffer getCoordRefBuffer() {
/* 5548 */     if (isLiveOrCompiled() && 
/* 5549 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 5551 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 5554 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5555 */     if ((i & 0x800) == 0) {
/* 5556 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 5558 */     if ((i & 0x100) != 0) {
/* 5559 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5561 */     return ((GeometryArrayRetained)this.retained).getCoordRefBuffer();
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
/*      */   public void setCoordRefFloat(float[] paramArrayOfFloat) {
/* 5602 */     if (isLiveOrCompiled() && 
/* 5603 */       !getCapability(19)) {
/* 5604 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 5606 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5607 */     if ((i & 0x80) == 0) {
/* 5608 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5610 */     if ((i & 0x800) != 0) {
/* 5611 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5613 */     if ((i & 0x100) != 0) {
/* 5614 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5616 */     ((GeometryArrayRetained)this.retained).setCoordRefFloat(paramArrayOfFloat);
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
/*      */   public float[] getCoordRefFloat() {
/* 5633 */     if (isLiveOrCompiled() && 
/* 5634 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 5636 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 5639 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5640 */     if ((i & 0x80) == 0) {
/* 5641 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5643 */     if ((i & 0x800) != 0) {
/* 5644 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5646 */     if ((i & 0x100) != 0) {
/* 5647 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5649 */     return ((GeometryArrayRetained)this.retained).getCoordRefFloat();
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
/*      */   
/*      */   public void setCoordRefDouble(double[] paramArrayOfDouble) {
/* 5691 */     if (isLiveOrCompiled() && 
/* 5692 */       !getCapability(19)) {
/* 5693 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 5695 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5696 */     if ((i & 0x80) == 0) {
/* 5697 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5699 */     if ((i & 0x800) != 0) {
/* 5700 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5702 */     if ((i & 0x100) != 0) {
/* 5703 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5705 */     ((GeometryArrayRetained)this.retained).setCoordRefDouble(paramArrayOfDouble);
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
/*      */   public double[] getCoordRefDouble() {
/* 5722 */     if (isLiveOrCompiled() && 
/* 5723 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 5725 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 5728 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5729 */     if ((i & 0x80) == 0) {
/* 5730 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5732 */     if ((i & 0x800) != 0) {
/* 5733 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5735 */     if ((i & 0x100) != 0) {
/* 5736 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5738 */     return ((GeometryArrayRetained)this.retained).getCoordRefDouble();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCoordRef3f(Point3f[] paramArrayOfPoint3f) {
/* 5749 */     if (isLiveOrCompiled() && 
/* 5750 */       !getCapability(19)) {
/* 5751 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 5753 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5754 */     if ((i & 0x80) == 0) {
/* 5755 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5757 */     if ((i & 0x800) != 0) {
/* 5758 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5760 */     if ((i & 0x100) != 0) {
/* 5761 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5763 */     ((GeometryArrayRetained)this.retained).setCoordRef3f(paramArrayOfPoint3f);
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
/*      */   public Point3f[] getCoordRef3f() {
/* 5776 */     if (isLiveOrCompiled() && 
/* 5777 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 5779 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 5782 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5783 */     if ((i & 0x80) == 0) {
/* 5784 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5786 */     if ((i & 0x800) != 0) {
/* 5787 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5789 */     if ((i & 0x100) != 0) {
/* 5790 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5792 */     return ((GeometryArrayRetained)this.retained).getCoordRef3f();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCoordRef3d(Point3d[] paramArrayOfPoint3d) {
/* 5803 */     if (isLiveOrCompiled() && 
/* 5804 */       !getCapability(19)) {
/* 5805 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 5807 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5808 */     if ((i & 0x80) == 0) {
/* 5809 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5811 */     if ((i & 0x800) != 0) {
/* 5812 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5814 */     if ((i & 0x100) != 0) {
/* 5815 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5817 */     ((GeometryArrayRetained)this.retained).setCoordRef3d(paramArrayOfPoint3d);
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
/*      */   public Point3d[] getCoordRef3d() {
/* 5829 */     if (isLiveOrCompiled() && 
/* 5830 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 5832 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 5835 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5836 */     if ((i & 0x80) == 0) {
/* 5837 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5839 */     if ((i & 0x800) != 0) {
/* 5840 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 5842 */     if ((i & 0x100) != 0) {
/* 5843 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5845 */     return ((GeometryArrayRetained)this.retained).getCoordRef3d();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorRefBuffer(J3DBuffer paramJ3DBuffer) {
/* 5896 */     if (isLiveOrCompiled() && 
/* 5897 */       !getCapability(19)) {
/* 5898 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 5900 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 5902 */     if ((i & 0x800) == 0) {
/* 5903 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 5905 */     if ((i & 0x100) != 0) {
/* 5906 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5908 */     ((GeometryArrayRetained)this.retained).setColorRefBuffer(paramJ3DBuffer);
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
/*      */   public J3DBuffer getColorRefBuffer() {
/* 5925 */     if (isLiveOrCompiled() && 
/* 5926 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 5928 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 5931 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 5933 */     if ((i & 0x800) == 0) {
/* 5934 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 5936 */     if ((i & 0x100) != 0) {
/* 5937 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 5939 */     return ((GeometryArrayRetained)this.retained).getColorRefBuffer();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorRefFloat(float[] paramArrayOfFloat) {
/* 5989 */     if (isLiveOrCompiled() && 
/* 5990 */       !getCapability(19)) {
/* 5991 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 5993 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 5994 */     if ((i & 0x80) == 0) {
/* 5995 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 5997 */     if ((i & 0x800) != 0) {
/* 5998 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6000 */     if ((i & 0x100) != 0) {
/* 6001 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6003 */     ((GeometryArrayRetained)this.retained).setColorRefFloat(paramArrayOfFloat);
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
/*      */   public float[] getColorRefFloat() {
/* 6020 */     if (isLiveOrCompiled() && 
/* 6021 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6023 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6026 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6027 */     if ((i & 0x80) == 0) {
/* 6028 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6030 */     if ((i & 0x800) != 0) {
/* 6031 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6033 */     if ((i & 0x100) != 0) {
/* 6034 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6036 */     return ((GeometryArrayRetained)this.retained).getColorRefFloat();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorRefByte(byte[] paramArrayOfByte) {
/* 6086 */     if (isLiveOrCompiled() && 
/* 6087 */       !getCapability(19)) {
/* 6088 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6090 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6091 */     if ((i & 0x80) == 0) {
/* 6092 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6094 */     if ((i & 0x800) != 0) {
/* 6095 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6097 */     if ((i & 0x100) != 0) {
/* 6098 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6100 */     ((GeometryArrayRetained)this.retained).setColorRefByte(paramArrayOfByte);
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
/*      */   public byte[] getColorRefByte() {
/* 6119 */     if (isLiveOrCompiled() && 
/* 6120 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6122 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6125 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6126 */     if ((i & 0x80) == 0) {
/* 6127 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6129 */     if ((i & 0x800) != 0) {
/* 6130 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6132 */     if ((i & 0x100) != 0) {
/* 6133 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6135 */     return ((GeometryArrayRetained)this.retained).getColorRefByte();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorRef3f(Color3f[] paramArrayOfColor3f) {
/* 6146 */     if (isLiveOrCompiled() && 
/* 6147 */       !getCapability(19)) {
/* 6148 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6150 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6151 */     if ((i & 0x80) == 0) {
/* 6152 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6154 */     if ((i & 0x800) != 0) {
/* 6155 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6157 */     if ((i & 0x100) != 0) {
/* 6158 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6160 */     if ((i & 0x8) != 0) {
/* 6161 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 6163 */     ((GeometryArrayRetained)this.retained).setColorRef3f(paramArrayOfColor3f);
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
/*      */   public Color3f[] getColorRef3f() {
/* 6177 */     if (isLiveOrCompiled() && 
/* 6178 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6180 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6183 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6184 */     if ((i & 0x80) == 0) {
/* 6185 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6187 */     if ((i & 0x800) != 0) {
/* 6188 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6190 */     if ((i & 0x100) != 0) {
/* 6191 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6193 */     return ((GeometryArrayRetained)this.retained).getColorRef3f();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorRef4f(Color4f[] paramArrayOfColor4f) {
/* 6204 */     if (isLiveOrCompiled() && 
/* 6205 */       !getCapability(19)) {
/* 6206 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6208 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6209 */     if ((i & 0x80) == 0) {
/* 6210 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6212 */     if ((i & 0x800) != 0) {
/* 6213 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6215 */     if ((i & 0x100) != 0) {
/* 6216 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6218 */     if ((i & 0x8) == 0) {
/* 6219 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 6221 */     ((GeometryArrayRetained)this.retained).setColorRef4f(paramArrayOfColor4f);
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
/*      */   public Color4f[] getColorRef4f() {
/* 6235 */     if (isLiveOrCompiled() && 
/* 6236 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6238 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6241 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6242 */     if ((i & 0x80) == 0) {
/* 6243 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6245 */     if ((i & 0x800) != 0) {
/* 6246 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6248 */     if ((i & 0x100) != 0) {
/* 6249 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6251 */     return ((GeometryArrayRetained)this.retained).getColorRef4f();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorRef3b(Color3b[] paramArrayOfColor3b) {
/* 6262 */     if (isLiveOrCompiled() && 
/* 6263 */       !getCapability(19)) {
/* 6264 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6266 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6267 */     if ((i & 0x80) == 0) {
/* 6268 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6270 */     if ((i & 0x800) != 0) {
/* 6271 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6273 */     if ((i & 0x100) != 0) {
/* 6274 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6276 */     if ((i & 0x8) != 0) {
/* 6277 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray92"));
/*      */     }
/* 6279 */     ((GeometryArrayRetained)this.retained).setColorRef3b(paramArrayOfColor3b);
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
/*      */   public Color3b[] getColorRef3b() {
/* 6293 */     if (isLiveOrCompiled() && 
/* 6294 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6296 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6299 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6300 */     if ((i & 0x80) == 0) {
/* 6301 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6303 */     if ((i & 0x800) != 0) {
/* 6304 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6306 */     if ((i & 0x800) != 0) {
/* 6307 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6309 */     if ((i & 0x100) != 0) {
/* 6310 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6312 */     return ((GeometryArrayRetained)this.retained).getColorRef3b();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorRef4b(Color4b[] paramArrayOfColor4b) {
/* 6323 */     if (isLiveOrCompiled() && 
/* 6324 */       !getCapability(19)) {
/* 6325 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6327 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6328 */     if ((i & 0x80) == 0) {
/* 6329 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6331 */     if ((i & 0x800) != 0) {
/* 6332 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6334 */     if ((i & 0x100) != 0) {
/* 6335 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6337 */     if ((i & 0x8) == 0) {
/* 6338 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray93"));
/*      */     }
/* 6340 */     ((GeometryArrayRetained)this.retained).setColorRef4b(paramArrayOfColor4b);
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
/*      */   public Color4b[] getColorRef4b() {
/* 6354 */     if (isLiveOrCompiled() && 
/* 6355 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6357 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6360 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6361 */     if ((i & 0x80) == 0) {
/* 6362 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6364 */     if ((i & 0x800) != 0) {
/* 6365 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6367 */     if ((i & 0x100) != 0) {
/* 6368 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6370 */     return ((GeometryArrayRetained)this.retained).getColorRef4b();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNormalRefBuffer(J3DBuffer paramJ3DBuffer) {
/* 6417 */     if (isLiveOrCompiled() && 
/* 6418 */       !getCapability(19)) {
/* 6419 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6421 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6422 */     if ((i & 0x800) == 0) {
/* 6423 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 6425 */     if ((i & 0x100) != 0) {
/* 6426 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6428 */     ((GeometryArrayRetained)this.retained).setNormalRefBuffer(paramJ3DBuffer);
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
/*      */   public J3DBuffer getNormalRefBuffer() {
/* 6444 */     if (isLiveOrCompiled() && 
/* 6445 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6447 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6450 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 6452 */     if ((i & 0x800) == 0) {
/* 6453 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 6455 */     if ((i & 0x100) != 0) {
/* 6456 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6458 */     return ((GeometryArrayRetained)this.retained).getNormalRefBuffer();
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNormalRefFloat(float[] paramArrayOfFloat) {
/* 6502 */     if (isLiveOrCompiled() && 
/* 6503 */       !getCapability(19)) {
/* 6504 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6506 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6507 */     if ((i & 0x80) == 0) {
/* 6508 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6510 */     if ((i & 0x800) != 0) {
/* 6511 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6513 */     if ((i & 0x100) != 0) {
/* 6514 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6516 */     ((GeometryArrayRetained)this.retained).setNormalRefFloat(paramArrayOfFloat);
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
/*      */   public float[] getNormalRefFloat() {
/* 6535 */     if (isLiveOrCompiled() && 
/* 6536 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6538 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6541 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6542 */     if ((i & 0x80) == 0) {
/* 6543 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6545 */     if ((i & 0x800) != 0) {
/* 6546 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6548 */     if ((i & 0x100) != 0) {
/* 6549 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6551 */     return ((GeometryArrayRetained)this.retained).getNormalRefFloat();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNormalRef3f(Vector3f[] paramArrayOfVector3f) {
/* 6562 */     if (isLiveOrCompiled() && 
/* 6563 */       !getCapability(19)) {
/* 6564 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6566 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6567 */     if ((i & 0x80) == 0) {
/* 6568 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6570 */     if ((i & 0x800) != 0) {
/* 6571 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6573 */     if ((i & 0x100) != 0) {
/* 6574 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6576 */     ((GeometryArrayRetained)this.retained).setNormalRef3f(paramArrayOfVector3f);
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
/*      */   public Vector3f[] getNormalRef3f() {
/* 6590 */     if (isLiveOrCompiled() && 
/* 6591 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6593 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6596 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6597 */     if ((i & 0x80) == 0) {
/* 6598 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6600 */     if ((i & 0x800) != 0) {
/* 6601 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6603 */     if ((i & 0x100) != 0) {
/* 6604 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6606 */     return ((GeometryArrayRetained)this.retained).getNormalRef3f();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTexCoordRefBuffer(int paramInt, J3DBuffer paramJ3DBuffer) {
/* 6664 */     if (isLiveOrCompiled() && 
/* 6665 */       !getCapability(19)) {
/* 6666 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6668 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 6670 */     if ((i & 0x800) == 0) {
/* 6671 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 6673 */     if ((i & 0x100) != 0) {
/* 6674 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6676 */     ((GeometryArrayRetained)this.retained).setTexCoordRefBuffer(paramInt, paramJ3DBuffer);
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
/*      */   public J3DBuffer getTexCoordRefBuffer(int paramInt) {
/* 6705 */     if (isLiveOrCompiled() && 
/* 6706 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6708 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6711 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 6713 */     if ((i & 0x800) == 0) {
/* 6714 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 6716 */     if ((i & 0x100) != 0) {
/* 6717 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6719 */     return ((GeometryArrayRetained)this.retained).getTexCoordRefBuffer(paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTexCoordRefFloat(int paramInt, float[] paramArrayOfFloat) {
/* 6778 */     if (isLiveOrCompiled() && 
/* 6779 */       !getCapability(19)) {
/* 6780 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6782 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6783 */     if ((i & 0x80) == 0) {
/* 6784 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6786 */     if ((i & 0x800) != 0) {
/* 6787 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6789 */     if ((i & 0x100) != 0) {
/* 6790 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6792 */     ((GeometryArrayRetained)this.retained).setTexCoordRefFloat(paramInt, paramArrayOfFloat);
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
/*      */   public float[] getTexCoordRefFloat(int paramInt) {
/* 6824 */     if (isLiveOrCompiled() && 
/* 6825 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6827 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6830 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6831 */     if ((i & 0x80) == 0) {
/* 6832 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6834 */     if ((i & 0x800) != 0) {
/* 6835 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6837 */     if ((i & 0x100) != 0) {
/* 6838 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6840 */     return ((GeometryArrayRetained)this.retained).getTexCoordRefFloat(paramInt);
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
/*      */   public void setTexCoordRef2f(int paramInt, TexCoord2f[] paramArrayOfTexCoord2f) {
/* 6853 */     if (isLiveOrCompiled() && 
/* 6854 */       !getCapability(19)) {
/* 6855 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6857 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6858 */     if ((i & 0x80) == 0) {
/* 6859 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6861 */     if ((i & 0x800) != 0) {
/* 6862 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6864 */     if ((i & 0x100) != 0) {
/* 6865 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6867 */     if ((i & 0x440) != 0) {
/* 6868 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray94"));
/*      */     }
/* 6870 */     ((GeometryArrayRetained)this.retained).setTexCoordRef2f(paramInt, paramArrayOfTexCoord2f);
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
/*      */   public TexCoord2f[] getTexCoordRef2f(int paramInt) {
/* 6886 */     if (isLiveOrCompiled() && 
/* 6887 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6889 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6892 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6893 */     if ((i & 0x80) == 0) {
/* 6894 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6896 */     if ((i & 0x800) != 0) {
/* 6897 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6899 */     if ((i & 0x100) != 0) {
/* 6900 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6902 */     return ((GeometryArrayRetained)this.retained).getTexCoordRef2f(paramInt);
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
/*      */   public void setTexCoordRef3f(int paramInt, TexCoord3f[] paramArrayOfTexCoord3f) {
/* 6915 */     if (isLiveOrCompiled() && 
/* 6916 */       !getCapability(19)) {
/* 6917 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 6919 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6920 */     if ((i & 0x80) == 0) {
/* 6921 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6923 */     if ((i & 0x800) != 0) {
/* 6924 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6926 */     if ((i & 0x100) != 0) {
/* 6927 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6929 */     if ((i & 0x420) != 0) {
/* 6930 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray95"));
/*      */     }
/* 6932 */     ((GeometryArrayRetained)this.retained).setTexCoordRef3f(paramInt, paramArrayOfTexCoord3f);
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
/*      */   public TexCoord3f[] getTexCoordRef3f(int paramInt) {
/* 6948 */     if (isLiveOrCompiled() && 
/* 6949 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 6951 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 6954 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 6955 */     if ((i & 0x80) == 0) {
/* 6956 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/* 6958 */     if ((i & 0x800) != 0) {
/* 6959 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 6961 */     if ((i & 0x100) != 0) {
/* 6962 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/* 6964 */     return ((GeometryArrayRetained)this.retained).getTexCoordRef3f(paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVertexAttrRefBuffer(int paramInt, J3DBuffer paramJ3DBuffer) {
/* 7017 */     if (isLiveOrCompiled() && 
/* 7018 */       !getCapability(19)) {
/* 7019 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/*      */ 
/*      */     
/* 7023 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 7025 */     if ((i & 0x800) == 0) {
/* 7026 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/*      */     
/* 7029 */     if ((i & 0x100) != 0) {
/* 7030 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/*      */     
/* 7033 */     ((GeometryArrayRetained)this.retained).setVertexAttrRefBuffer(paramInt, paramJ3DBuffer);
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
/*      */   public J3DBuffer getVertexAttrRefBuffer(int paramInt) {
/* 7060 */     if (isLiveOrCompiled() && 
/* 7061 */       !getCapability(21) && !getCapability(18))
/*      */     {
/*      */       
/* 7064 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */ 
/*      */     
/* 7068 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/*      */     
/* 7070 */     if ((i & 0x800) == 0) {
/* 7071 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/*      */     
/* 7074 */     if ((i & 0x100) != 0) {
/* 7075 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/*      */     
/* 7078 */     return ((GeometryArrayRetained)this.retained).getVertexAttrRefBuffer(paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVertexAttrRefFloat(int paramInt, float[] paramArrayOfFloat) {
/* 7145 */     if (isLiveOrCompiled() && 
/* 7146 */       !getCapability(19)) {
/* 7147 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/*      */ 
/*      */     
/* 7151 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 7152 */     if ((i & 0x80) == 0) {
/* 7153 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/*      */     
/* 7156 */     if ((i & 0x800) != 0) {
/* 7157 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/*      */     
/* 7160 */     if ((i & 0x100) != 0) {
/* 7161 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/*      */     
/* 7164 */     ((GeometryArrayRetained)this.retained).setVertexAttrRefFloat(paramInt, paramArrayOfFloat);
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
/*      */   public float[] getVertexAttrRefFloat(int paramInt) {
/* 7195 */     if (isLiveOrCompiled() && 
/* 7196 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 7198 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */ 
/*      */     
/* 7202 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 7203 */     if ((i & 0x80) == 0) {
/* 7204 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray83"));
/*      */     }
/*      */     
/* 7207 */     if ((i & 0x800) != 0) {
/* 7208 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/*      */     
/* 7211 */     if ((i & 0x100) != 0) {
/* 7212 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray84"));
/*      */     }
/*      */     
/* 7215 */     return ((GeometryArrayRetained)this.retained).getVertexAttrRefFloat(paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInterleavedVertices(float[] paramArrayOfFloat) {
/* 7280 */     if (isLiveOrCompiled() && 
/* 7281 */       !getCapability(19)) {
/* 7282 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 7284 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 7285 */     if ((i & 0x100) == 0) {
/* 7286 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray85"));
/*      */     }
/* 7288 */     if ((i & 0x800) != 0) {
/* 7289 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 7291 */     ((GeometryArrayRetained)this.retained).setInterleavedVertices(paramArrayOfFloat);
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
/*      */   public float[] getInterleavedVertices() {
/* 7309 */     if (isLiveOrCompiled() && 
/* 7310 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 7312 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 7315 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 7316 */     if ((i & 0x100) == 0) {
/* 7317 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray85"));
/*      */     }
/*      */     
/* 7320 */     if ((i & 0x800) != 0) {
/* 7321 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray119"));
/*      */     }
/* 7323 */     return ((GeometryArrayRetained)this.retained).getInterleavedVertices();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInterleavedVertexBuffer(J3DBuffer paramJ3DBuffer) {
/* 7394 */     if (isLiveOrCompiled() && 
/* 7395 */       !getCapability(19)) {
/* 7396 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray86"));
/*      */     }
/* 7398 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 7399 */     if ((i & 0x100) == 0) {
/* 7400 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray85"));
/*      */     }
/*      */     
/* 7403 */     if ((i & 0x800) == 0) {
/* 7404 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 7406 */     ((GeometryArrayRetained)this.retained).setInterleavedVertexBuffer(paramJ3DBuffer);
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
/*      */   public J3DBuffer getInterleavedVertexBuffer() {
/* 7425 */     if (isLiveOrCompiled() && 
/* 7426 */       !getCapability(21) && !getCapability(18))
/*      */     {
/* 7428 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryArray87"));
/*      */     }
/*      */     
/* 7431 */     int i = ((GeometryArrayRetained)this.retained).vertexFormat;
/* 7432 */     if ((i & 0x100) == 0) {
/* 7433 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray85"));
/*      */     }
/* 7435 */     if ((i & 0x800) == 0) {
/* 7436 */       throw new IllegalStateException(J3dI18N.getString("GeometryArray118"));
/*      */     }
/* 7438 */     return ((GeometryArrayRetained)this.retained).getInterleavedVertexBuffer();
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\GeometryArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */