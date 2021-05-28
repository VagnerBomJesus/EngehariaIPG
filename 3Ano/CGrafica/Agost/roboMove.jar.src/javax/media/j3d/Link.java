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
/*     */ public class Link
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_SHARED_GROUP_READ = 12;
/*     */   public static final int ALLOW_SHARED_GROUP_WRITE = 13;
/*  38 */   private static final int[] readCapabilities = { 12 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public Link() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Link(SharedGroup paramSharedGroup) {
/*  58 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  60 */     ((LinkRetained)this.retained).setSharedGroup(paramSharedGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  68 */     this.retained = new LinkRetained();
/*  69 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSharedGroup(SharedGroup paramSharedGroup) {
/*  80 */     if (isLiveOrCompiled() && 
/*  81 */       !getCapability(13))
/*  82 */       throw new CapabilityNotSetException(J3dI18N.getString("Link0")); 
/*  83 */     ((LinkRetained)this.retained).setSharedGroup(paramSharedGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SharedGroup getSharedGroup() {
/*  94 */     if (isLiveOrCompiled() && 
/*  95 */       !getCapability(12))
/*  96 */       throw new CapabilityNotSetException(J3dI18N.getString("Link1")); 
/*  97 */     return ((LinkRetained)this.retained).getSharedGroup();
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 119 */     Link link = new Link();
/* 120 */     link.duplicateNode(this, paramBoolean);
/* 121 */     return link;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 146 */     super.duplicateAttributes(paramNode, paramBoolean);
/* 147 */     ((LinkRetained)this.retained).setSharedGroup(((LinkRetained)paramNode.retained).getSharedGroup());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Link.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */