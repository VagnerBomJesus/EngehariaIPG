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
/*     */ public class SharedGroup
/*     */   extends Group
/*     */ {
/*     */   public static final int ALLOW_LINK_READ = 17;
/*  71 */   private static final int[] readCapabilities = { 17 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public SharedGroup() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Link[] getLinks() {
/*  91 */     if (isLiveOrCompiled() && 
/*  92 */       !getCapability(17)) {
/*  93 */       throw new CapabilityNotSetException(J3dI18N.getString("SharedGroup1"));
/*     */     }
/*     */     
/*  96 */     return ((SharedGroupRetained)this.retained).getLinks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 105 */     this.retained = new SharedGroupRetained();
/* 106 */     this.retained.setSource(this);
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
/* 119 */     if (isLive()) {
/* 120 */       throw new RestrictedAccessException(J3dI18N.getString("SharedGroup0"));
/*     */     }
/*     */     
/* 123 */     if (!isCompiled()) {
/*     */ 
/*     */       
/* 126 */       checkForCycle();
/*     */       
/* 128 */       ((SharedGroupRetained)this.retained).compile();
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 148 */     SharedGroup sharedGroup = new SharedGroup();
/* 149 */     sharedGroup.duplicateNode(this, paramBoolean);
/* 150 */     return sharedGroup;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\SharedGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */