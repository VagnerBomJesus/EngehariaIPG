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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Texture3D
/*     */   extends Texture
/*     */ {
/*     */   public Texture3D() {}
/*     */   
/*     */   public Texture3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/*  78 */     super(paramInt1, paramInt2, paramInt3, paramInt4);
/*     */     
/*  80 */     int i = -1;
/*     */     
/*  82 */     i = getLevelsNPOT(paramInt5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     ((Texture3DRetained)this.retained).setDepth(paramInt5);
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
/*     */   public Texture3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 125 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt6);
/* 126 */     int i = -1;
/*     */     
/* 128 */     i = getLevelsNPOT(paramInt5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     ((Texture3DRetained)this.retained).setDepth(paramInt5);
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
/*     */   public void setBoundaryModeR(int paramInt) {
/* 147 */     checkForLiveOrCompiled();
/* 148 */     switch (paramInt) {
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */         break;
/*     */       default:
/* 155 */         throw new IllegalArgumentException(J3dI18N.getString("Texture31"));
/*     */     } 
/* 157 */     ((Texture3DRetained)this.retained).initBoundaryModeR(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBoundaryModeR() {
/* 167 */     if (isLiveOrCompiled() && 
/* 168 */       !getCapability(2))
/* 169 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture3D0")); 
/* 170 */     return ((Texture3DRetained)this.retained).getBoundaryModeR();
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
/*     */   public int getDepth() {
/* 182 */     if (isLiveOrCompiled() && 
/* 183 */       !getCapability(8)) {
/* 184 */       throw new CapabilityNotSetException(J3dI18N.getString("Texture3D2"));
/*     */     }
/* 186 */     return ((Texture3DRetained)this.retained).getDepth();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 194 */     this.retained = new Texture3DRetained();
/* 195 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 202 */     Texture3DRetained texture3DRetained = (Texture3DRetained)this.retained;
/* 203 */     Texture3D texture3D = new Texture3D(texture3DRetained.getMipMapMode(), texture3DRetained.format, texture3DRetained.width, texture3DRetained.height, texture3DRetained.depth);
/*     */     
/* 205 */     texture3D.duplicateNodeComponent(this);
/* 206 */     return texture3D;
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
/* 218 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { checkDuplicateNodeComponent(paramNodeComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 241 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 243 */     ((Texture3DRetained)this.retained).initBoundaryModeR(((Texture3DRetained)paramNodeComponent.retained).getBoundaryModeR());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Texture3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */