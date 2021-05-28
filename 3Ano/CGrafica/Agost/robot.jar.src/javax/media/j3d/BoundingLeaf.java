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
/*     */ public class BoundingLeaf
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_REGION_READ = 12;
/*     */   public static final int ALLOW_REGION_WRITE = 13;
/*  54 */   private static final int[] readCapabilities = { 12 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf() {
/*  63 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  65 */     ((BoundingLeafRetained)this.retained).createBoundingLeaf();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf(Bounds paramBounds) {
/*  74 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  76 */     ((BoundingLeafRetained)this.retained).createBoundingLeaf();
/*  77 */     ((BoundingLeafRetained)this.retained).initRegion(paramBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRegion(Bounds paramBounds) {
/*  87 */     if (isLiveOrCompiled() && 
/*  88 */       !getCapability(13)) {
/*  89 */       throw new CapabilityNotSetException(J3dI18N.getString("BoundingLeaf0"));
/*     */     }
/*  91 */     if (isLive()) {
/*  92 */       ((BoundingLeafRetained)this.retained).setRegion(paramBounds);
/*     */     } else {
/*  94 */       ((BoundingLeafRetained)this.retained).initRegion(paramBounds);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getRegion() {
/* 104 */     if (isLiveOrCompiled() && 
/* 105 */       !getCapability(12)) {
/* 106 */       throw new CapabilityNotSetException(J3dI18N.getString("BoundingLeaf1"));
/*     */     }
/* 108 */     return ((BoundingLeafRetained)this.retained).getRegion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 116 */     this.retained = new BoundingLeafRetained();
/* 117 */     this.retained.setSource(this);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 135 */     BoundingLeaf boundingLeaf = new BoundingLeaf();
/* 136 */     boundingLeaf.duplicateNode(this, paramBoolean);
/* 137 */     return boundingLeaf;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 164 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 166 */     ((BoundingLeafRetained)this.retained).initRegion(((BoundingLeafRetained)paramNode.retained).getRegion());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\BoundingLeaf.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */