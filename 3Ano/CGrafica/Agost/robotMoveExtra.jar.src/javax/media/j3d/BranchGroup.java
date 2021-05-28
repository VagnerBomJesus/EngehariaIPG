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
/*     */ public class BranchGroup
/*     */   extends Group
/*     */ {
/*     */   public static final int ALLOW_DETACH = 17;
/*     */   
/*     */   void createRetained() {
/*  57 */     this.retained = new BranchGroupRetained();
/*  58 */     this.retained.setSource(this);
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
/*     */   public void compile() {
/*  71 */     if (isLive()) {
/*  72 */       throw new RestrictedAccessException(J3dI18N.getString("BranchGroup0"));
/*     */     }
/*     */ 
/*     */     
/*  76 */     if (!isCompiled()) {
/*     */ 
/*     */       
/*  79 */       checkForCycle();
/*     */       
/*  81 */       ((BranchGroupRetained)this.retained).compile();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {
/*  91 */     if (isLiveOrCompiled()) {
/*  92 */       if (!getCapability(17)) {
/*  93 */         throw new CapabilityNotSetException(J3dI18N.getString("BranchGroup1"));
/*     */       }
/*  95 */       if (((BranchGroupRetained)this.retained).parent != null) {
/*  96 */         Group group = (Group)((BranchGroupRetained)this.retained).parent.source;
/*  97 */         if (!group.getCapability(13)) {
/*  98 */           throw new CapabilityNotSetException(J3dI18N.getString("BranchGroup2"));
/*     */         }
/*     */       } 
/*     */     } 
/* 102 */     ((BranchGroupRetained)this.retained).detach();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void validateModeFlagAndPickShape(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 108 */     if (!isLive()) {
/* 109 */       throw new IllegalStateException(J3dI18N.getString("BranchGroup3"));
/*     */     }
/*     */     
/* 112 */     if (paramInt1 != 1 && paramInt1 != 2)
/*     */     {
/* 114 */       throw new IllegalArgumentException(J3dI18N.getString("BranchGroup4"));
/*     */     }
/*     */     
/* 117 */     if (paramPickShape instanceof PickPoint && paramInt1 == 2) {
/* 118 */       throw new IllegalArgumentException(J3dI18N.getString("BranchGroup5"));
/*     */     }
/*     */     
/* 121 */     if ((paramInt2 & 0x20) != 0 && (paramInt2 & 0x40) != 0)
/*     */     {
/* 123 */       throw new IllegalArgumentException(J3dI18N.getString("BranchGroup6"));
/*     */     }
/*     */     
/* 126 */     if (paramInt1 == 1 && (paramInt2 & 0x78) != 0)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       throw new IllegalArgumentException(J3dI18N.getString("BranchGroup7"));
/*     */     }
/*     */     
/* 135 */     if (paramPickShape instanceof PickBounds && (paramInt2 & 0x78) != 0)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 141 */       throw new IllegalArgumentException(J3dI18N.getString("BranchGroup8"));
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
/*     */   public SceneGraphPath[] pickAll(PickShape paramPickShape) {
/* 161 */     if (!isLive()) {
/* 162 */       throw new IllegalStateException(J3dI18N.getString("BranchGroup3"));
/*     */     }
/* 164 */     return ((BranchGroupRetained)this.retained).pickAll(paramPickShape);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickInfo[] pickAll(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 239 */     validateModeFlagAndPickShape(paramInt1, paramInt2, paramPickShape);
/* 240 */     return ((BranchGroupRetained)this.retained).pickAll(paramInt1, paramInt2, paramPickShape);
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
/*     */   public SceneGraphPath[] pickAllSorted(PickShape paramPickShape) {
/* 263 */     if (!isLive()) {
/* 264 */       throw new IllegalStateException(J3dI18N.getString("BranchGroup3"));
/*     */     }
/* 266 */     return ((BranchGroupRetained)this.retained).pickAllSorted(paramPickShape);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickInfo[] pickAllSorted(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 343 */     validateModeFlagAndPickShape(paramInt1, paramInt2, paramPickShape);
/* 344 */     return ((BranchGroupRetained)this.retained).pickAllSorted(paramInt1, paramInt2, paramPickShape);
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
/*     */   public SceneGraphPath pickClosest(PickShape paramPickShape) {
/* 364 */     if (!isLive()) {
/* 365 */       throw new IllegalStateException(J3dI18N.getString("BranchGroup3"));
/*     */     }
/* 367 */     return ((BranchGroupRetained)this.retained).pickClosest(paramPickShape);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickInfo pickClosest(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 441 */     validateModeFlagAndPickShape(paramInt1, paramInt2, paramPickShape);
/* 442 */     return ((BranchGroupRetained)this.retained).pickClosest(paramInt1, paramInt2, paramPickShape);
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
/*     */   public SceneGraphPath pickAny(PickShape paramPickShape) {
/* 460 */     if (!isLive()) {
/* 461 */       throw new IllegalStateException(J3dI18N.getString("BranchGroup3"));
/*     */     }
/* 463 */     return ((BranchGroupRetained)this.retained).pickAny(paramPickShape);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickInfo pickAny(int paramInt1, int paramInt2, PickShape paramPickShape) {
/* 537 */     validateModeFlagAndPickShape(paramInt1, paramInt2, paramPickShape);
/* 538 */     return ((BranchGroupRetained)this.retained).pickAny(paramInt1, paramInt2, paramPickShape);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 558 */     BranchGroup branchGroup = new BranchGroup();
/* 559 */     branchGroup.duplicateNode(this, paramBoolean);
/* 560 */     return branchGroup;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\BranchGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */