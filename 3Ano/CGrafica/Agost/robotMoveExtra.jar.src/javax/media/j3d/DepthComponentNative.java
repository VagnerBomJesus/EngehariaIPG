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
/*     */ public class DepthComponentNative
/*     */   extends DepthComponent
/*     */ {
/*     */   DepthComponentNative() {}
/*     */   
/*  35 */   public DepthComponentNative(int paramInt1, int paramInt2) { ((DepthComponentNativeRetained)this.retained).initialize(paramInt1, paramInt2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   void getDepthData(int[] paramArrayOfInt) { ((DepthComponentNativeRetained)this.retained).getDepthData(paramArrayOfInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  52 */     this.retained = new DepthComponentNativeRetained();
/*  53 */     this.retained.setSource(this);
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
/*     */   public NodeComponent cloneNodeComponent() {
/*  67 */     DepthComponentNativeRetained depthComponentNativeRetained = (DepthComponentNativeRetained)this.retained;
/*  68 */     DepthComponentNative depthComponentNative = new DepthComponentNative(depthComponentNativeRetained.width, depthComponentNativeRetained.height);
/*     */     
/*  70 */     depthComponentNative.duplicateNodeComponent(this);
/*  71 */     return depthComponentNative;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/*  95 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/*  97 */     int[] arrayOfInt1 = ((DepthComponentNativeRetained)paramNodeComponent.retained).depthData;
/*     */ 
/*     */     
/* 100 */     int[] arrayOfInt2 = ((DepthComponentNativeRetained)this.retained).depthData;
/*     */     
/* 102 */     if (arrayOfInt1 != null)
/* 103 */       for (byte b = 0; b < arrayOfInt1.length; b++)
/* 104 */         arrayOfInt2[b] = arrayOfInt1[b];  
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\DepthComponentNative.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */