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
/*     */ public class TriangleStripArray
/*     */   extends GeometryStripArray
/*     */ {
/*     */   TriangleStripArray() {}
/*     */   
/*     */   public TriangleStripArray(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  57 */     super(paramInt1, paramInt2, paramArrayOfInt);
/*     */     
/*  59 */     if (paramInt1 < 3) {
/*  60 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArray0"));
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
/*     */   public TriangleStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 101 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (paramInt1 < 3) {
/* 106 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArray0"));
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
/*     */   public TriangleStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 153 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramArrayOfInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     if (paramInt1 < 3) {
/* 159 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArray0"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 167 */     this.retained = new TriangleStripArrayRetained();
/* 168 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 176 */     TriangleStripArrayRetained triangleStripArrayRetained = (TriangleStripArrayRetained)this.retained;
/* 177 */     int[] arrayOfInt1 = new int[triangleStripArrayRetained.getNumStrips()];
/* 178 */     triangleStripArrayRetained.getStripVertexCounts(arrayOfInt1);
/* 179 */     int i = triangleStripArrayRetained.getTexCoordSetCount();
/* 180 */     int[] arrayOfInt2 = null;
/* 181 */     int j = triangleStripArrayRetained.getVertexAttrCount();
/* 182 */     int[] arrayOfInt3 = null;
/* 183 */     if (i > 0) {
/* 184 */       arrayOfInt2 = new int[triangleStripArrayRetained.getTexCoordSetMapLength()];
/* 185 */       triangleStripArrayRetained.getTexCoordSetMap(arrayOfInt2);
/*     */     } 
/* 187 */     if (j > 0) {
/* 188 */       arrayOfInt3 = new int[j];
/* 189 */       triangleStripArrayRetained.getVertexAttrSizes(arrayOfInt3);
/*     */     } 
/* 191 */     TriangleStripArray triangleStripArray = new TriangleStripArray(triangleStripArrayRetained.getVertexCount(), triangleStripArrayRetained.getVertexFormat(), i, arrayOfInt2, j, arrayOfInt3, arrayOfInt1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     triangleStripArray.duplicateNodeComponent(this);
/* 199 */     return triangleStripArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TriangleStripArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */