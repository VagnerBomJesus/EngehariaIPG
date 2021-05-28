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
/*     */ public class QuadArray
/*     */   extends GeometryArray
/*     */ {
/*     */   QuadArray() {}
/*     */   
/*     */   public QuadArray(int paramInt1, int paramInt2) {
/*  46 */     super(paramInt1, paramInt2);
/*     */     
/*  48 */     if (paramInt1 < 4 || paramInt1 % 4 != 0) {
/*  49 */       throw new IllegalArgumentException(J3dI18N.getString("QuadArray0"));
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
/*     */   public QuadArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  85 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt);
/*     */ 
/*     */     
/*  88 */     if (paramInt1 < 4 || paramInt1 % 4 != 0) {
/*  89 */       throw new IllegalArgumentException(J3dI18N.getString("QuadArray0"));
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
/*     */   public QuadArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 131 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (paramInt1 < 4 || paramInt1 % 4 != 0) {
/* 136 */       throw new IllegalArgumentException(J3dI18N.getString("QuadArray0"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 144 */     this.retained = new QuadArrayRetained();
/* 145 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 153 */     QuadArrayRetained quadArrayRetained = (QuadArrayRetained)this.retained;
/* 154 */     int i = quadArrayRetained.getTexCoordSetCount();
/* 155 */     int[] arrayOfInt1 = null;
/* 156 */     int j = quadArrayRetained.getVertexAttrCount();
/* 157 */     int[] arrayOfInt2 = null;
/* 158 */     if (i > 0) {
/* 159 */       arrayOfInt1 = new int[quadArrayRetained.getTexCoordSetMapLength()];
/* 160 */       quadArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 162 */     if (j > 0) {
/* 163 */       arrayOfInt2 = new int[j];
/* 164 */       quadArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 166 */     QuadArray quadArray = new QuadArray(quadArrayRetained.getVertexCount(), quadArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     quadArray.duplicateNodeComponent(this);
/* 173 */     return quadArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\QuadArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */