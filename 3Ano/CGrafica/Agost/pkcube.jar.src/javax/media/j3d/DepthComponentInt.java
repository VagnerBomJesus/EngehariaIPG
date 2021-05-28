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
/*     */ public class DepthComponentInt
/*     */   extends DepthComponent
/*     */ {
/*     */   DepthComponentInt() {}
/*     */   
/*  35 */   public DepthComponentInt(int paramInt1, int paramInt2) { ((DepthComponentIntRetained)this.retained).initialize(paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepthData(int[] paramArrayOfInt) {
/*  45 */     checkForLiveOrCompiled();
/*  46 */     ((DepthComponentIntRetained)this.retained).setDepthData(paramArrayOfInt);
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
/*     */   public void getDepthData(int[] paramArrayOfInt) {
/*  58 */     if (isLiveOrCompiled() && 
/*  59 */       !getCapability(1))
/*  60 */       throw new CapabilityNotSetException(J3dI18N.getString("DepthComponentInt0")); 
/*  61 */     ((DepthComponentIntRetained)this.retained).getDepthData(paramArrayOfInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  69 */     this.retained = new DepthComponentIntRetained();
/*  70 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/*  78 */     DepthComponentIntRetained depthComponentIntRetained = (DepthComponentIntRetained)this.retained;
/*  79 */     DepthComponentInt depthComponentInt = new DepthComponentInt(depthComponentIntRetained.width, depthComponentIntRetained.height);
/*     */     
/*  81 */     depthComponentInt.duplicateNodeComponent(this);
/*  82 */     return depthComponentInt;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 105 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */ 
/*     */     
/* 108 */     int i = getWidth() * getHeight();
/* 109 */     int[] arrayOfInt = new int[i];
/* 110 */     ((DepthComponentIntRetained)paramNodeComponent.retained).getDepthData(arrayOfInt);
/* 111 */     ((DepthComponentIntRetained)this.retained).setDepthData(arrayOfInt);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\DepthComponentInt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */