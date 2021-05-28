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
/*     */ public class IndexedTriangleArray
/*     */   extends IndexedGeometryArray
/*     */ {
/*     */   IndexedTriangleArray() {}
/*     */   
/*     */   public IndexedTriangleArray(int paramInt1, int paramInt2, int paramInt3) {
/*  53 */     super(paramInt1, paramInt2, paramInt3);
/*     */     
/*  55 */     if (paramInt1 < 1) {
/*  56 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleArray0"));
/*     */     }
/*  58 */     if (paramInt3 < 3 || paramInt3 % 3 != 0) {
/*  59 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleArray1"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndexedTriangleArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4) {
/* 101 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt, paramInt4);
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (paramInt1 < 1) {
/* 106 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleArray0"));
/*     */     }
/* 108 */     if (paramInt4 < 3 || paramInt4 % 3 != 0) {
/* 109 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleArray1"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndexedTriangleArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5) {
/* 157 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     if (paramInt1 < 1) {
/* 163 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleArray0"));
/*     */     }
/* 165 */     if (paramInt5 < 3 || paramInt5 % 3 != 0) {
/* 166 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleArray1"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 174 */     this.retained = new IndexedTriangleArrayRetained();
/* 175 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 183 */     IndexedTriangleArrayRetained indexedTriangleArrayRetained = (IndexedTriangleArrayRetained)this.retained;
/* 184 */     int i = indexedTriangleArrayRetained.getTexCoordSetCount();
/* 185 */     int[] arrayOfInt1 = null;
/* 186 */     int j = indexedTriangleArrayRetained.getVertexAttrCount();
/* 187 */     int[] arrayOfInt2 = null;
/* 188 */     if (i > 0) {
/* 189 */       arrayOfInt1 = new int[indexedTriangleArrayRetained.getTexCoordSetMapLength()];
/* 190 */       indexedTriangleArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 192 */     if (j > 0) {
/* 193 */       arrayOfInt2 = new int[j];
/* 194 */       indexedTriangleArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 196 */     IndexedTriangleArray indexedTriangleArray = new IndexedTriangleArray(indexedTriangleArrayRetained.getVertexCount(), indexedTriangleArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2, indexedTriangleArrayRetained.getIndexCount());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     indexedTriangleArray.duplicateNodeComponent(this);
/* 204 */     return indexedTriangleArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\IndexedTriangleArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */