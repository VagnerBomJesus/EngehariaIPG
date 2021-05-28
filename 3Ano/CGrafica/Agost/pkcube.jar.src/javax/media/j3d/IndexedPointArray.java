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
/*     */ public class IndexedPointArray
/*     */   extends IndexedGeometryArray
/*     */ {
/*     */   IndexedPointArray() {}
/*     */   
/*     */   public IndexedPointArray(int paramInt1, int paramInt2, int paramInt3) {
/*  51 */     super(paramInt1, paramInt2, paramInt3);
/*     */     
/*  53 */     if (paramInt1 < 1) {
/*  54 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedPointArray0"));
/*     */     }
/*  56 */     if (paramInt3 < 1) {
/*  57 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedPointArray1"));
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
/*     */   public IndexedPointArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4) {
/*  98 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt, paramInt4);
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (paramInt1 < 1) {
/* 103 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedPointArray0"));
/*     */     }
/* 105 */     if (paramInt4 < 1) {
/* 106 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedPointArray1"));
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
/*     */   public IndexedPointArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5) {
/* 153 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     if (paramInt1 < 1) {
/* 159 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedPointArray0"));
/*     */     }
/* 161 */     if (paramInt5 < 1) {
/* 162 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedPointArray1"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 170 */     this.retained = new IndexedPointArrayRetained();
/* 171 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 179 */     IndexedPointArrayRetained indexedPointArrayRetained = (IndexedPointArrayRetained)this.retained;
/* 180 */     int i = indexedPointArrayRetained.getTexCoordSetCount();
/* 181 */     int[] arrayOfInt1 = null;
/* 182 */     int j = indexedPointArrayRetained.getVertexAttrCount();
/* 183 */     int[] arrayOfInt2 = null;
/* 184 */     if (i > 0) {
/* 185 */       arrayOfInt1 = new int[indexedPointArrayRetained.getTexCoordSetMapLength()];
/* 186 */       indexedPointArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 188 */     if (j > 0) {
/* 189 */       arrayOfInt2 = new int[j];
/* 190 */       indexedPointArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 192 */     IndexedPointArray indexedPointArray = new IndexedPointArray(indexedPointArrayRetained.getVertexCount(), indexedPointArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2, indexedPointArrayRetained.getIndexCount());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     indexedPointArray.duplicateNodeComponent(this);
/* 200 */     return indexedPointArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\IndexedPointArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */