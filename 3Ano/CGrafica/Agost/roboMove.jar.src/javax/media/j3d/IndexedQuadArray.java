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
/*     */ public class IndexedQuadArray
/*     */   extends IndexedGeometryArray
/*     */ {
/*     */   IndexedQuadArray() {}
/*     */   
/*     */   public IndexedQuadArray(int paramInt1, int paramInt2, int paramInt3) {
/*  53 */     super(paramInt1, paramInt2, paramInt3);
/*     */     
/*  55 */     if (paramInt1 < 1) {
/*  56 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedQuadArray0"));
/*     */     }
/*  58 */     if (paramInt3 < 4 || paramInt3 % 4 != 0) {
/*  59 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedQuadArray1"));
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
/*     */   public IndexedQuadArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4) {
/* 101 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt, paramInt4);
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (paramInt1 < 1) {
/* 106 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedQuadArray0"));
/*     */     }
/* 108 */     if (paramInt4 < 4 || paramInt4 % 4 != 0) {
/* 109 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedQuadArray1"));
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
/*     */   public IndexedQuadArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5) {
/* 157 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     if (paramInt1 < 1) {
/* 163 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedQuadArray0"));
/*     */     }
/* 165 */     if (paramInt5 < 4 || paramInt5 % 4 != 0) {
/* 166 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedQuadArray1"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 174 */     this.retained = new IndexedQuadArrayRetained();
/* 175 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 183 */     IndexedQuadArrayRetained indexedQuadArrayRetained = (IndexedQuadArrayRetained)this.retained;
/* 184 */     int i = indexedQuadArrayRetained.getTexCoordSetCount();
/* 185 */     int[] arrayOfInt1 = null;
/* 186 */     int j = indexedQuadArrayRetained.getVertexAttrCount();
/* 187 */     int[] arrayOfInt2 = null;
/* 188 */     if (i > 0) {
/* 189 */       arrayOfInt1 = new int[indexedQuadArrayRetained.getTexCoordSetMapLength()];
/* 190 */       indexedQuadArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 192 */     if (j > 0) {
/* 193 */       arrayOfInt2 = new int[j];
/* 194 */       indexedQuadArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 196 */     IndexedQuadArray indexedQuadArray = new IndexedQuadArray(indexedQuadArrayRetained.getVertexCount(), indexedQuadArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2, indexedQuadArrayRetained.getIndexCount());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     indexedQuadArray.duplicateNodeComponent(this);
/* 204 */     return indexedQuadArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\IndexedQuadArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */