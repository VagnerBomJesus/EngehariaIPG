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
/*     */ public class IndexedLineStripArray
/*     */   extends IndexedGeometryStripArray
/*     */ {
/*     */   IndexedLineStripArray() {}
/*     */   
/*     */   public IndexedLineStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  64 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt);
/*     */     
/*  66 */     if (paramInt1 < 1) {
/*  67 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArray0"));
/*     */     }
/*  69 */     if (paramInt3 < 2) {
/*  70 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArray1"));
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
/*     */   public IndexedLineStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 117 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 121 */     if (paramInt1 < 1) {
/* 122 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArray0"));
/*     */     }
/* 124 */     if (paramInt4 < 2) {
/* 125 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArray1"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndexedLineStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5, int[] paramArrayOfInt3) {
/* 178 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5, paramArrayOfInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (paramInt1 < 1) {
/* 184 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArray0"));
/*     */     }
/* 186 */     if (paramInt5 < 2) {
/* 187 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineStripArray1"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 195 */     this.retained = new IndexedLineStripArrayRetained();
/* 196 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 204 */     IndexedLineStripArrayRetained indexedLineStripArrayRetained = (IndexedLineStripArrayRetained)this.retained;
/*     */     
/* 206 */     int[] arrayOfInt1 = new int[indexedLineStripArrayRetained.getNumStrips()];
/* 207 */     indexedLineStripArrayRetained.getStripIndexCounts(arrayOfInt1);
/* 208 */     int i = indexedLineStripArrayRetained.getTexCoordSetCount();
/* 209 */     int[] arrayOfInt2 = null;
/* 210 */     int j = indexedLineStripArrayRetained.getVertexAttrCount();
/* 211 */     int[] arrayOfInt3 = null;
/* 212 */     if (i > 0) {
/* 213 */       arrayOfInt2 = new int[indexedLineStripArrayRetained.getTexCoordSetMapLength()];
/* 214 */       indexedLineStripArrayRetained.getTexCoordSetMap(arrayOfInt2);
/*     */     } 
/* 216 */     if (j > 0) {
/* 217 */       arrayOfInt3 = new int[j];
/* 218 */       indexedLineStripArrayRetained.getVertexAttrSizes(arrayOfInt3);
/*     */     } 
/* 220 */     IndexedLineStripArray indexedLineStripArray = new IndexedLineStripArray(indexedLineStripArrayRetained.getVertexCount(), indexedLineStripArrayRetained.getVertexFormat(), i, arrayOfInt2, j, arrayOfInt3, indexedLineStripArrayRetained.getIndexCount(), arrayOfInt1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     indexedLineStripArray.duplicateNodeComponent(this);
/* 229 */     return indexedLineStripArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\IndexedLineStripArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */