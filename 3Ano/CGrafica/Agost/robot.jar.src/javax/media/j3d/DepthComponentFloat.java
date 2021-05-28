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
/*     */ public class DepthComponentFloat
/*     */   extends DepthComponent
/*     */ {
/*     */   DepthComponentFloat() {}
/*     */   
/*  36 */   public DepthComponentFloat(int paramInt1, int paramInt2) { ((DepthComponentFloatRetained)this.retained).initialize(paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepthData(float[] paramArrayOfFloat) {
/*  46 */     checkForLiveOrCompiled();
/*  47 */     ((DepthComponentFloatRetained)this.retained).setDepthData(paramArrayOfFloat);
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
/*     */   public void getDepthData(float[] paramArrayOfFloat) {
/*  59 */     if (isLiveOrCompiled() && 
/*  60 */       !getCapability(1))
/*  61 */       throw new CapabilityNotSetException(J3dI18N.getString("DepthComponentFloat0")); 
/*  62 */     ((DepthComponentFloatRetained)this.retained).getDepthData(paramArrayOfFloat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  70 */     this.retained = new DepthComponentFloatRetained();
/*  71 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/*  79 */     DepthComponentFloatRetained depthComponentFloatRetained = (DepthComponentFloatRetained)this.retained;
/*  80 */     DepthComponentFloat depthComponentFloat = new DepthComponentFloat(depthComponentFloatRetained.width, depthComponentFloatRetained.height);
/*     */     
/*  82 */     depthComponentFloat.duplicateNodeComponent(this);
/*  83 */     return depthComponentFloat;
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
/* 106 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */ 
/*     */     
/* 109 */     int i = getWidth() * getHeight();
/* 110 */     float[] arrayOfFloat = new float[i];
/*     */     
/* 112 */     ((DepthComponentFloatRetained)paramNodeComponent.retained).getDepthData(arrayOfFloat);
/* 113 */     ((DepthComponentFloatRetained)this.retained).setDepthData(arrayOfFloat);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\DepthComponentFloat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */