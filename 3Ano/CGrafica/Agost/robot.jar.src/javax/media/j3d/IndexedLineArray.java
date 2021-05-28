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
/*     */ public class IndexedLineArray
/*     */   extends IndexedGeometryArray
/*     */ {
/*     */   IndexedLineArray() {}
/*     */   
/*     */   public IndexedLineArray(int paramInt1, int paramInt2, int paramInt3) {
/*  51 */     super(paramInt1, paramInt2, paramInt3);
/*     */     
/*  53 */     if (paramInt1 < 1) {
/*  54 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineArray0"));
/*     */     }
/*  56 */     if (paramInt3 < 2 || paramInt3 % 2 != 0) {
/*  57 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineArray1"));
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
/*     */   public IndexedLineArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4) {
/*  99 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt, paramInt4);
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (paramInt1 < 1) {
/* 104 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineArray0"));
/*     */     }
/* 106 */     if (paramInt4 < 2 || paramInt4 % 2 != 0) {
/* 107 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineArray1"));
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
/*     */   public IndexedLineArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5) {
/* 155 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (paramInt1 < 1) {
/* 161 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineArray0"));
/*     */     }
/* 163 */     if (paramInt5 < 2 || paramInt5 % 2 != 0) {
/* 164 */       throw new IllegalArgumentException(J3dI18N.getString("IndexedLineArray1"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 172 */     this.retained = new IndexedLineArrayRetained();
/* 173 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 180 */     IndexedLineArrayRetained indexedLineArrayRetained = (IndexedLineArrayRetained)this.retained;
/* 181 */     int i = indexedLineArrayRetained.getTexCoordSetCount();
/* 182 */     int[] arrayOfInt1 = null;
/* 183 */     int j = indexedLineArrayRetained.getVertexAttrCount();
/* 184 */     int[] arrayOfInt2 = null;
/* 185 */     if (i > 0) {
/* 186 */       arrayOfInt1 = new int[indexedLineArrayRetained.getTexCoordSetMapLength()];
/* 187 */       indexedLineArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 189 */     if (j > 0) {
/* 190 */       arrayOfInt2 = new int[j];
/* 191 */       indexedLineArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 193 */     IndexedLineArray indexedLineArray = new IndexedLineArray(indexedLineArrayRetained.getVertexCount(), indexedLineArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2, indexedLineArrayRetained.getIndexCount());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     indexedLineArray.duplicateNodeComponent(this);
/* 201 */     return indexedLineArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\IndexedLineArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */