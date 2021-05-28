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
/*     */ public class LineArray
/*     */   extends GeometryArray
/*     */ {
/*     */   LineArray() {}
/*     */   
/*     */   public LineArray(int paramInt1, int paramInt2) {
/*  48 */     super(paramInt1, paramInt2);
/*     */     
/*  50 */     if (paramInt1 < 2 || paramInt1 % 2 != 0) {
/*  51 */       throw new IllegalArgumentException(J3dI18N.getString("LineArray0"));
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
/*     */   public LineArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  87 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt);
/*     */ 
/*     */     
/*  90 */     if (paramInt1 < 2 || paramInt1 % 2 != 0) {
/*  91 */       throw new IllegalArgumentException(J3dI18N.getString("LineArray0"));
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
/*     */   public LineArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 133 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 137 */     if (paramInt1 < 2 || paramInt1 % 2 != 0) {
/* 138 */       throw new IllegalArgumentException(J3dI18N.getString("LineArray0"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 146 */     this.retained = new LineArrayRetained();
/* 147 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 155 */     LineArrayRetained lineArrayRetained = (LineArrayRetained)this.retained;
/* 156 */     int i = lineArrayRetained.getTexCoordSetCount();
/* 157 */     int[] arrayOfInt1 = null;
/* 158 */     int j = lineArrayRetained.getVertexAttrCount();
/* 159 */     int[] arrayOfInt2 = null;
/* 160 */     if (i > 0) {
/* 161 */       arrayOfInt1 = new int[lineArrayRetained.getTexCoordSetMapLength()];
/* 162 */       lineArrayRetained.getTexCoordSetMap(arrayOfInt1);
/*     */     } 
/* 164 */     if (j > 0) {
/* 165 */       arrayOfInt2 = new int[j];
/* 166 */       lineArrayRetained.getVertexAttrSizes(arrayOfInt2);
/*     */     } 
/* 168 */     LineArray lineArray = new LineArray(lineArrayRetained.getVertexCount(), lineArrayRetained.getVertexFormat(), i, arrayOfInt1, j, arrayOfInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     lineArray.duplicateNodeComponent(this);
/* 175 */     return lineArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\LineArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */