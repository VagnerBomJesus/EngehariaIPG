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
/*     */ 
/*     */ 
/*     */ public class PointAttributes
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_SIZE_READ = 0;
/*     */   public static final int ALLOW_SIZE_WRITE = 1;
/*     */   public static final int ALLOW_ANTIALIASING_READ = 2;
/*     */   public static final int ALLOW_ANTIALIASING_WRITE = 3;
/*  69 */   private static final int[] readCapabilities = { 0, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public PointAttributes() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointAttributes(float paramFloat, boolean paramBoolean) {
/*  94 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  96 */     ((PointAttributesRetained)this.retained).initPointSize(paramFloat);
/*  97 */     ((PointAttributesRetained)this.retained).initPointAntialiasingEnable(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPointSize(float paramFloat) {
/* 107 */     if (isLiveOrCompiled() && 
/* 108 */       !getCapability(1)) {
/* 109 */       throw new CapabilityNotSetException(J3dI18N.getString("PointAttributes0"));
/*     */     }
/* 111 */     if (isLive()) {
/* 112 */       ((PointAttributesRetained)this.retained).setPointSize(paramFloat);
/*     */     } else {
/* 114 */       ((PointAttributesRetained)this.retained).initPointSize(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPointSize() {
/* 125 */     if (isLiveOrCompiled() && 
/* 126 */       !getCapability(0))
/* 127 */       throw new CapabilityNotSetException(J3dI18N.getString("PointAttributes1")); 
/* 128 */     return ((PointAttributesRetained)this.retained).getPointSize();
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
/*     */   public void setPointAntialiasingEnable(boolean paramBoolean) {
/* 147 */     if (isLiveOrCompiled() && 
/* 148 */       !getCapability(3))
/* 149 */       throw new CapabilityNotSetException(J3dI18N.getString("PointAttributes2")); 
/* 150 */     if (isLive()) {
/* 151 */       ((PointAttributesRetained)this.retained).setPointAntialiasingEnable(paramBoolean);
/*     */     } else {
/* 153 */       ((PointAttributesRetained)this.retained).initPointAntialiasingEnable(paramBoolean);
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
/*     */   public boolean getPointAntialiasingEnable() {
/* 165 */     if (isLiveOrCompiled() && 
/* 166 */       !getCapability(2))
/* 167 */       throw new CapabilityNotSetException(J3dI18N.getString("PointAttributes3")); 
/* 168 */     return ((PointAttributesRetained)this.retained).getPointAntialiasingEnable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 176 */     this.retained = new PointAttributesRetained();
/* 177 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 184 */     PointAttributes pointAttributes = new PointAttributes();
/* 185 */     pointAttributes.duplicateNodeComponent(this);
/* 186 */     return pointAttributes;
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
/* 209 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 211 */     PointAttributesRetained pointAttributesRetained1 = (PointAttributesRetained)paramNodeComponent.retained;
/*     */     
/* 213 */     PointAttributesRetained pointAttributesRetained2 = (PointAttributesRetained)this.retained;
/*     */     
/* 215 */     pointAttributesRetained2.initPointSize(pointAttributesRetained1.getPointSize());
/* 216 */     pointAttributesRetained2.initPointAntialiasingEnable(pointAttributesRetained1.getPointAntialiasingEnable());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PointAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */