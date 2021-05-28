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
/*     */ public class IndexedTriangleStripArray
/*     */   extends IndexedGeometryStripArray
/*     */ {
/*     */   IndexedTriangleStripArray() {}
/*     */   
/*     */   public IndexedTriangleStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  65 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt);
/*     */     
/*  67 */     if (paramInt1 < 1) {
/*  68 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArray0"));
/*     */     }
/*  70 */     if (paramInt3 < 3) {
/*  71 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArray1"));
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
/*     */   public IndexedTriangleStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 118 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (paramInt1 < 1) {
/* 123 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArray0"));
/*     */     }
/* 125 */     if (paramInt4 < 3) {
/* 126 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArray1"));
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
/*     */   public IndexedTriangleStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5, int[] paramArrayOfInt3) {
/* 179 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5, paramArrayOfInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     if (paramInt1 < 1) {
/* 185 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArray0"));
/*     */     }
/* 187 */     if (paramInt5 < 3) {
/* 188 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleStripArray1"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 196 */     this.retained = new IndexedTriangleStripArrayRetained();
/* 197 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 205 */     IndexedTriangleStripArrayRetained indexedTriangleStripArrayRetained = (IndexedTriangleStripArrayRetained)this.retained;
/*     */     
/* 207 */     int[] arrayOfInt1 = new int[indexedTriangleStripArrayRetained.getNumStrips()];
/* 208 */     indexedTriangleStripArrayRetained.getStripIndexCounts(arrayOfInt1);
/* 209 */     int i = indexedTriangleStripArrayRetained.getTexCoordSetCount();
/* 210 */     int[] arrayOfInt2 = null;
/* 211 */     int j = indexedTriangleStripArrayRetained.getVertexAttrCount();
/* 212 */     int[] arrayOfInt3 = null;
/* 213 */     if (i > 0) {
/* 214 */       arrayOfInt2 = new int[indexedTriangleStripArrayRetained.getTexCoordSetMapLength()];
/* 215 */       indexedTriangleStripArrayRetained.getTexCoordSetMap(arrayOfInt2);
/*     */     } 
/* 217 */     if (j > 0) {
/* 218 */       arrayOfInt3 = new int[j];
/* 219 */       indexedTriangleStripArrayRetained.getVertexAttrSizes(arrayOfInt3);
/*     */     } 
/* 221 */     IndexedTriangleStripArray indexedTriangleStripArray = new IndexedTriangleStripArray(indexedTriangleStripArrayRetained.getVertexCount(), indexedTriangleStripArrayRetained.getVertexFormat(), i, arrayOfInt2, j, arrayOfInt3, indexedTriangleStripArrayRetained.getIndexCount(), arrayOfInt1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     indexedTriangleStripArray.duplicateNodeComponent(this);
/* 230 */     return indexedTriangleStripArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedTriangleStripArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */