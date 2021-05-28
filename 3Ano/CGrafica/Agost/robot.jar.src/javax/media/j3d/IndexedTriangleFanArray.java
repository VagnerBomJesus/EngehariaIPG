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
/*     */ public class IndexedTriangleFanArray
/*     */   extends IndexedGeometryStripArray
/*     */ {
/*     */   IndexedTriangleFanArray() {}
/*     */   
/*     */   public IndexedTriangleFanArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  63 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt);
/*     */     
/*  65 */     if (paramInt1 < 1) {
/*  66 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArray0"));
/*     */     }
/*  68 */     if (paramInt3 < 3) {
/*  69 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArray1"));
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
/*     */   public IndexedTriangleFanArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 116 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (paramInt1 < 1) {
/* 121 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArray0"));
/*     */     }
/* 123 */     if (paramInt4 < 3) {
/* 124 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArray1"));
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
/*     */   public IndexedTriangleFanArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5, int[] paramArrayOfInt3) {
/* 177 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5, paramArrayOfInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     if (paramInt1 < 1) {
/* 183 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArray0"));
/*     */     }
/* 185 */     if (paramInt5 < 3) {
/* 186 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedTriangleFanArray1"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 194 */     this.retained = new IndexedTriangleFanArrayRetained();
/* 195 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 203 */     IndexedTriangleFanArrayRetained indexedTriangleFanArrayRetained = (IndexedTriangleFanArrayRetained)this.retained;
/*     */     
/* 205 */     int[] arrayOfInt1 = new int[indexedTriangleFanArrayRetained.getNumStrips()];
/* 206 */     indexedTriangleFanArrayRetained.getStripIndexCounts(arrayOfInt1);
/* 207 */     int i = indexedTriangleFanArrayRetained.getTexCoordSetCount();
/* 208 */     int[] arrayOfInt2 = null;
/* 209 */     int j = indexedTriangleFanArrayRetained.getVertexAttrCount();
/* 210 */     int[] arrayOfInt3 = null;
/* 211 */     if (i > 0) {
/* 212 */       arrayOfInt2 = new int[indexedTriangleFanArrayRetained.getTexCoordSetMapLength()];
/* 213 */       indexedTriangleFanArrayRetained.getTexCoordSetMap(arrayOfInt2);
/*     */     } 
/* 215 */     if (j > 0) {
/* 216 */       arrayOfInt3 = new int[j];
/* 217 */       indexedTriangleFanArrayRetained.getVertexAttrSizes(arrayOfInt3);
/*     */     } 
/* 219 */     IndexedTriangleFanArray indexedTriangleFanArray = new IndexedTriangleFanArray(indexedTriangleFanArrayRetained.getVertexCount(), indexedTriangleFanArrayRetained.getVertexFormat(), i, arrayOfInt2, j, arrayOfInt3, indexedTriangleFanArrayRetained.getIndexCount(), arrayOfInt1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     indexedTriangleFanArray.duplicateNodeComponent(this);
/* 228 */     return indexedTriangleFanArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedTriangleFanArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */