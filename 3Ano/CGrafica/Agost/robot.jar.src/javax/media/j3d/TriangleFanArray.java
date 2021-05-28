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
/*     */ public class TriangleFanArray
/*     */   extends GeometryStripArray
/*     */ {
/*     */   TriangleFanArray() {}
/*     */   
/*     */   public TriangleFanArray(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  58 */     super(paramInt1, paramInt2, paramArrayOfInt);
/*     */     
/*  60 */     if (paramInt1 < 3) {
/*  61 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleFanArray0"));
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
/*     */   public TriangleFanArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 102 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (paramInt1 < 3) {
/* 107 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleFanArray0"));
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
/*     */   public TriangleFanArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 154 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramArrayOfInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     if (paramInt1 < 3) {
/* 160 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleFanArray0"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 168 */     this.retained = new TriangleFanArrayRetained();
/* 169 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 177 */     TriangleFanArrayRetained triangleFanArrayRetained = (TriangleFanArrayRetained)this.retained;
/* 178 */     int[] arrayOfInt1 = new int[triangleFanArrayRetained.getNumStrips()];
/* 179 */     triangleFanArrayRetained.getStripVertexCounts(arrayOfInt1);
/* 180 */     int i = triangleFanArrayRetained.getTexCoordSetCount();
/* 181 */     int[] arrayOfInt2 = null;
/* 182 */     int j = triangleFanArrayRetained.getVertexAttrCount();
/* 183 */     int[] arrayOfInt3 = null;
/* 184 */     if (i > 0) {
/* 185 */       arrayOfInt2 = new int[triangleFanArrayRetained.getTexCoordSetMapLength()];
/* 186 */       triangleFanArrayRetained.getTexCoordSetMap(arrayOfInt2);
/*     */     } 
/* 188 */     if (j > 0) {
/* 189 */       arrayOfInt3 = new int[j];
/* 190 */       triangleFanArrayRetained.getVertexAttrSizes(arrayOfInt3);
/*     */     } 
/* 192 */     TriangleFanArray triangleFanArray = new TriangleFanArray(triangleFanArrayRetained.getVertexCount(), triangleFanArrayRetained.getVertexFormat(), i, arrayOfInt2, j, arrayOfInt3, arrayOfInt1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     triangleFanArray.duplicateNodeComponent(this);
/* 200 */     return triangleFanArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TriangleFanArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */