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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformGroup
/*     */   extends Group
/*     */ {
/*     */   public static final int ALLOW_TRANSFORM_READ = 17;
/*     */   public static final int ALLOW_TRANSFORM_WRITE = 18;
/*  65 */   private static final int[] readCapabilities = { 17 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public TransformGroup() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformGroup(Transform3D paramTransform3D) {
/*  85 */     if (!paramTransform3D.isAffine()) {
/*  86 */       throw new BadTransformException(J3dI18N.getString("TransformGroup0"));
/*     */     }
/*     */ 
/*     */     
/*  90 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  92 */     ((TransformGroupRetained)this.retained).setTransform(paramTransform3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 100 */     this.retained = new TransformGroupRetained();
/* 101 */     this.retained.setSource(this);
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
/*     */   public void setTransform(Transform3D paramTransform3D) {
/* 113 */     if (isLiveOrCompiled() && 
/* 114 */       !getCapability(18)) {
/* 115 */       throw new CapabilityNotSetException(J3dI18N.getString("TransformGroup1"));
/*     */     }
/* 117 */     if (!paramTransform3D.isAffine()) {
/* 118 */       throw new BadTransformException(J3dI18N.getString("TransformGroup0"));
/*     */     }
/*     */     
/* 121 */     ((TransformGroupRetained)this.retained).setTransform(paramTransform3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getTransform(Transform3D paramTransform3D) {
/* 132 */     if (isLiveOrCompiled() && 
/* 133 */       !getCapability(17)) {
/* 134 */       throw new CapabilityNotSetException(J3dI18N.getString("TransformGroup2"));
/*     */     }
/* 136 */     ((TransformGroupRetained)this.retained).getTransform(paramTransform3D);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 155 */     TransformGroup transformGroup = new TransformGroup();
/* 156 */     transformGroup.duplicateNode(this, paramBoolean);
/* 157 */     return transformGroup;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 183 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 185 */     Transform3D transform3D = new Transform3D();
/* 186 */     ((TransformGroupRetained)paramNode.retained).getTransform(transform3D);
/* 187 */     ((TransformGroupRetained)this.retained).setTransform(transform3D);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\TransformGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */