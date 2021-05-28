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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewPlatform
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_POLICY_READ = 12;
/*     */   public static final int ALLOW_POLICY_WRITE = 13;
/* 112 */   private static final int[] readCapabilities = { 12 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public ViewPlatform() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 134 */     this.retained = new ViewPlatformRetained();
/* 135 */     this.retained.setSource(this);
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
/*     */   public void setViewAttachPolicy(int paramInt) {
/* 153 */     if (isLiveOrCompiled() && 
/* 154 */       !getCapability(13)) {
/* 155 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewPlatform0"));
/*     */     }
/* 157 */     switch (paramInt) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */         break;
/*     */       
/*     */       default:
/* 164 */         throw new IllegalArgumentException(J3dI18N.getString("ViewPlatform1"));
/*     */     } 
/*     */     
/* 167 */     ((ViewPlatformRetained)this.retained).setViewAttachPolicy(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getViewAttachPolicy() {
/* 178 */     if (isLiveOrCompiled() && 
/* 179 */       !getCapability(12)) {
/* 180 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewPlatform2"));
/*     */     }
/* 182 */     return ((ViewPlatformRetained)this.retained).getViewAttachPolicy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public void setActivationRadius(float paramFloat) { ((ViewPlatformRetained)this.retained).setActivationRadius(paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public float getActivationRadius() { return ((ViewPlatformRetained)this.retained).getActivationRadius(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 227 */     ViewPlatform viewPlatform = new ViewPlatform();
/* 228 */     viewPlatform.duplicateNode(this, paramBoolean);
/* 229 */     return viewPlatform;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 262 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 264 */     ViewPlatformRetained viewPlatformRetained1 = (ViewPlatformRetained)paramNode.retained;
/*     */     
/* 266 */     ViewPlatformRetained viewPlatformRetained2 = (ViewPlatformRetained)this.retained;
/*     */     
/* 268 */     viewPlatformRetained2.setActivationRadius(viewPlatformRetained1.getActivationRadius());
/* 269 */     viewPlatformRetained2.setViewAttachPolicy(viewPlatformRetained1.getViewAttachPolicy());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ViewPlatform.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */