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
/*     */ public class TriangleArray
/*     */   extends GeometryArray
/*     */ {
/*     */   TriangleArray() {}
/*     */   
/*     */   public TriangleArray(int paramInt1, int paramInt2) {
/*  45 */     super(paramInt1, paramInt2);
/*     */     
/*  47 */     if (paramInt1 < 3 || paramInt1 % 3 != 0) {
/*  48 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleArray0"));
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
/*     */   public TriangleArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  84 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt);
/*     */ 
/*     */     
/*  87 */     if (paramInt1 < 3 || paramInt1 % 3 != 0) {
/*  88 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleArray0"));
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
/*     */   public TriangleArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 130 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (paramInt1 < 3 || paramInt1 % 3 != 0) {
/* 135 */       throw new IllegalArgumentException(J3dI18N.getString("TriangleArray0"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 144 */     this.retained = new TriangleArrayRetained();
/* 145 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 153 */     TriangleArrayRetained triangleArrayRetained = (TriangleArrayRetained)this.retained;
/* 154 */     int i = triangleArrayRetained.getTexCoordSetCount();
/* 155 */     int[] arrayOfInt1 = null;
/* 156 */     int j = triangleArrayRetained.getVertexAttrCount();
/* 157 */     int[] arrayOfInt2 = null;
/* 158 */     if (i > 0) {
/* 159 */       arrayOfInt1 = new int[triangleArrayRetained.getTexCoordSetMapLength()];
/* 160 */       triangleArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 162 */     if (j > 0) {
/* 163 */       arrayOfInt2 = new int[j];
/* 164 */       triangleArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 166 */     TriangleArray triangleArray = new TriangleArray(triangleArrayRetained.getVertexCount(), triangleArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     triangleArray.duplicateNodeComponent(this);
/* 173 */     return triangleArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\TriangleArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */