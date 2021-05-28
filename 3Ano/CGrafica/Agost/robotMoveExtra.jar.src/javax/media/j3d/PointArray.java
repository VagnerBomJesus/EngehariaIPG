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
/*     */ public class PointArray
/*     */   extends GeometryArray
/*     */ {
/*     */   PointArray() {}
/*     */   
/*     */   public PointArray(int paramInt1, int paramInt2) {
/*  43 */     super(paramInt1, paramInt2);
/*     */     
/*  45 */     if (paramInt1 < 1) {
/*  46 */       throw new IllegalArgumentException(J3dI18N.getString("PointArray0"));
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
/*     */   public PointArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  81 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt);
/*     */ 
/*     */     
/*  84 */     if (paramInt1 < 1) {
/*  85 */       throw new IllegalArgumentException(J3dI18N.getString("PointArray0"));
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
/*     */   public PointArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 126 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (paramInt1 < 1) {
/* 131 */       throw new IllegalArgumentException(J3dI18N.getString("PointArray0"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 139 */     this.retained = new PointArrayRetained();
/* 140 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 148 */     PointArrayRetained pointArrayRetained = (PointArrayRetained)this.retained;
/* 149 */     int i = pointArrayRetained.getTexCoordSetCount();
/* 150 */     int[] arrayOfInt1 = null;
/* 151 */     int j = pointArrayRetained.getVertexAttrCount();
/* 152 */     int[] arrayOfInt2 = null;
/* 153 */     if (i > 0) {
/* 154 */       arrayOfInt1 = new int[pointArrayRetained.getTexCoordSetMapLength()];
/* 155 */       pointArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 157 */     if (j > 0) {
/* 158 */       arrayOfInt2 = new int[j];
/* 159 */       pointArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 161 */     PointArray pointArray = new PointArray(pointArrayRetained.getVertexCount(), pointArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     pointArray.duplicateNodeComponent(this);
/* 168 */     return pointArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PointArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */