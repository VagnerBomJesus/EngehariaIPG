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
/*     */ public class LineStripArray
/*     */   extends GeometryStripArray
/*     */ {
/*     */   LineStripArray() {}
/*     */   
/*     */   public LineStripArray(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  58 */     super(paramInt1, paramInt2, paramArrayOfInt);
/*     */     
/*  60 */     if (paramInt1 < 2) {
/*  61 */       throw new IllegalArgumentException(J3dI18N.getString("LineStripArray0"));
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
/*     */   public LineStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 102 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (paramInt1 < 2) {
/* 107 */       throw new IllegalArgumentException(J3dI18N.getString("LineStripArray0"));
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
/*     */   public LineStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 154 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramArrayOfInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     if (paramInt1 < 2) {
/* 160 */       throw new IllegalArgumentException(J3dI18N.getString("LineStripArray0"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 168 */     this.retained = new LineStripArrayRetained();
/* 169 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 176 */     LineStripArrayRetained lineStripArrayRetained = (LineStripArrayRetained)this.retained;
/* 177 */     int[] arrayOfInt1 = new int[lineStripArrayRetained.getNumStrips()];
/* 178 */     lineStripArrayRetained.getStripVertexCounts(arrayOfInt1);
/* 179 */     int i = lineStripArrayRetained.getTexCoordSetCount();
/* 180 */     int[] arrayOfInt2 = null;
/* 181 */     int j = lineStripArrayRetained.getVertexAttrCount();
/* 182 */     int[] arrayOfInt3 = null;
/* 183 */     if (i > 0) {
/* 184 */       arrayOfInt2 = new int[lineStripArrayRetained.getTexCoordSetMapLength()];
/* 185 */       lineStripArrayRetained.getTexCoordSetMap(arrayOfInt2);
/*     */     } 
/* 187 */     if (j > 0) {
/* 188 */       arrayOfInt3 = new int[j];
/* 189 */       lineStripArrayRetained.getVertexAttrSizes(arrayOfInt3);
/*     */     } 
/* 191 */     LineStripArray lineStripArray = new LineStripArray(lineStripArrayRetained.getVertexCount(), lineStripArrayRetained.getVertexFormat(), i, arrayOfInt2, j, arrayOfInt3, arrayOfInt1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     lineStripArray.duplicateNodeComponent(this);
/* 199 */     return lineStripArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\LineStripArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */