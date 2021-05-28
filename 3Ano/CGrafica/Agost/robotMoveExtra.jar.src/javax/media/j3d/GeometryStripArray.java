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
/*     */ 
/*     */ public abstract class GeometryStripArray
/*     */   extends GeometryArray
/*     */ {
/*     */   GeometryStripArray() {}
/*     */   
/*     */   public GeometryStripArray(int paramInt1, int paramInt2, int[] paramArrayOfInt) {
/*  59 */     super(paramInt1, paramInt2);
/*  60 */     ((GeometryStripArrayRetained)this.retained).setStripVertexCounts(paramArrayOfInt);
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
/*     */   public GeometryStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 105 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1);
/*     */     
/* 107 */     ((GeometryStripArrayRetained)this.retained).setStripVertexCounts(paramArrayOfInt2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeometryStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 159 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */     
/* 163 */     ((GeometryStripArrayRetained)this.retained).setStripVertexCounts(paramArrayOfInt3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumStrips() {
/* 171 */     if (isLiveOrCompiled() && 
/* 172 */       !getCapability(8)) {
/* 173 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryStripArray0"));
/*     */     }
/* 175 */     return ((GeometryStripArrayRetained)this.retained).getNumStrips();
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
/*     */   public void setStripVertexCounts(int[] paramArrayOfInt) {
/* 218 */     if (isLiveOrCompiled() && 
/* 219 */       !getCapability(20)) {
/* 220 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryStripArray2"));
/*     */     }
/* 222 */     ((GeometryStripArrayRetained)this.retained).setStripVertexCounts(paramArrayOfInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getStripVertexCounts(int[] paramArrayOfInt) {
/* 233 */     if (isLiveOrCompiled() && 
/* 234 */       !getCapability(8)) {
/* 235 */       throw new CapabilityNotSetException(J3dI18N.getString("GeometryStripArray1"));
/*     */     }
/* 237 */     ((GeometryStripArrayRetained)this.retained).getStripVertexCounts(paramArrayOfInt);
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
/* 250 */   public void setValidVertexCount(int paramInt) { throw new UnsupportedOperationException(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\GeometryStripArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */