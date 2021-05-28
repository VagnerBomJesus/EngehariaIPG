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
/*     */ 
/*     */ public abstract class IndexedGeometryStripArray
/*     */   extends IndexedGeometryArray
/*     */ {
/*     */   IndexedGeometryStripArray() {}
/*     */   
/*     */   public IndexedGeometryStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt) {
/*  60 */     super(paramInt1, paramInt2, paramInt3);
/*  61 */     ((IndexedGeometryStripArrayRetained)this.retained).setStripIndexCounts(paramArrayOfInt);
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
/*     */   public IndexedGeometryStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2) {
/* 112 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4);
/*     */ 
/*     */     
/* 115 */     ((IndexedGeometryStripArrayRetained)this.retained).setStripIndexCounts(paramArrayOfInt2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndexedGeometryStripArray(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt1, int paramInt4, int[] paramArrayOfInt2, int paramInt5, int[] paramArrayOfInt3) {
/* 173 */     super(paramInt1, paramInt2, paramInt3, paramArrayOfInt1, paramInt4, paramArrayOfInt2, paramInt5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     ((IndexedGeometryStripArrayRetained)this.retained).setStripIndexCounts(paramArrayOfInt3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumStrips() {
/* 187 */     if (isLiveOrCompiled() && 
/* 188 */       !getCapability(8)) {
/* 189 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryStripArray0"));
/*     */     }
/* 191 */     return ((IndexedGeometryStripArrayRetained)this.retained).getNumStrips();
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
/*     */   public void setStripIndexCounts(int[] paramArrayOfInt) {
/* 210 */     if (isLiveOrCompiled() && 
/* 211 */       !getCapability(20)) {
/* 212 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryStripArray2"));
/*     */     }
/* 214 */     ((IndexedGeometryStripArrayRetained)this.retained).setStripIndexCounts(paramArrayOfInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getStripIndexCounts(int[] paramArrayOfInt) {
/* 225 */     if (isLiveOrCompiled() && 
/* 226 */       !getCapability(8)) {
/* 227 */       throw new CapabilityNotSetException(J3dI18N.getString("IndexedGeometryStripArray1"));
/*     */     }
/* 229 */     ((IndexedGeometryStripArrayRetained)this.retained).getStripIndexCounts(paramArrayOfInt);
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
/* 243 */   public void setValidIndexCount(int paramInt) { throw new UnsupportedOperationException(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\IndexedGeometryStripArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */