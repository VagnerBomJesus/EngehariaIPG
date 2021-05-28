/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewSpecificGroup
/*     */   extends Group
/*     */ {
/*     */   public static final int ALLOW_VIEW_READ = 17;
/*     */   public static final int ALLOW_VIEW_WRITE = 18;
/*  75 */   private static final int[] readCapabilities = { 17 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public ViewSpecificGroup() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  93 */     this.retained = new ViewSpecificGroupRetained();
/*  94 */     this.retained.setSource(this);
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
/*     */   public void setView(View paramView, int paramInt) {
/* 108 */     if (isLiveOrCompiled() && 
/* 109 */       !getCapability(18)) {
/* 110 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup1"));
/*     */     }
/* 112 */     ((ViewSpecificGroupRetained)this.retained).setView(paramView, paramInt);
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
/*     */   public View getView(int paramInt) {
/* 126 */     if (isLiveOrCompiled() && 
/* 127 */       !getCapability(17)) {
/* 128 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup2"));
/*     */     }
/* 130 */     return ((ViewSpecificGroupRetained)this.retained).getView(paramInt);
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
/*     */   public void insertView(View paramView, int paramInt) {
/* 144 */     if (isLiveOrCompiled() && 
/* 145 */       !getCapability(18)) {
/* 146 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup1"));
/*     */     }
/* 148 */     ((ViewSpecificGroupRetained)this.retained).insertView(paramView, paramInt);
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
/*     */   public void removeView(int paramInt) {
/* 164 */     if (isLiveOrCompiled() && 
/* 165 */       !getCapability(18))
/* 166 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup1")); 
/* 167 */     ((ViewSpecificGroupRetained)this.retained).removeView(paramInt);
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
/*     */   public Enumeration getAllViews() {
/* 181 */     if (isLiveOrCompiled() && 
/* 182 */       !getCapability(17)) {
/* 183 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup2"));
/*     */     }
/* 185 */     return ((ViewSpecificGroupRetained)this.retained).getAllViews();
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
/*     */   public void addView(View paramView) {
/* 197 */     if (isLiveOrCompiled() && 
/* 198 */       !getCapability(18)) {
/* 199 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup1"));
/*     */     }
/* 201 */     ((ViewSpecificGroupRetained)this.retained).addView(paramView);
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
/*     */   public int numViews() {
/* 216 */     if (isLiveOrCompiled() && 
/* 217 */       !getCapability(17)) {
/* 218 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup2"));
/*     */     }
/* 220 */     return ((ViewSpecificGroupRetained)this.retained).numViews();
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
/*     */   public int indexOfView(View paramView) {
/* 237 */     if (isLiveOrCompiled() && 
/* 238 */       !getCapability(17)) {
/* 239 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup2"));
/*     */     }
/* 241 */     return ((ViewSpecificGroupRetained)this.retained).indexOfView(paramView);
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
/*     */   public void removeView(View paramView) {
/* 260 */     if (isLiveOrCompiled() && 
/* 261 */       !getCapability(18)) {
/* 262 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup1"));
/*     */     }
/* 264 */     ((ViewSpecificGroupRetained)this.retained).removeView(paramView);
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
/*     */   public void removeAllViews() {
/* 280 */     if (isLiveOrCompiled() && 
/* 281 */       !getCapability(18)) {
/* 282 */       throw new CapabilityNotSetException(J3dI18N.getString("ViewSpecificGroup1"));
/*     */     }
/* 284 */     ((ViewSpecificGroupRetained)this.retained).removeAllViews();
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
/* 303 */     ViewSpecificGroup viewSpecificGroup = new ViewSpecificGroup();
/* 304 */     viewSpecificGroup.duplicateNode(this, paramBoolean);
/* 305 */     return viewSpecificGroup;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 334 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 336 */     ViewSpecificGroupRetained viewSpecificGroupRetained1 = (ViewSpecificGroupRetained)paramNode.retained;
/* 337 */     ViewSpecificGroupRetained viewSpecificGroupRetained2 = (ViewSpecificGroupRetained)this.retained;
/*     */     
/* 339 */     for (Enumeration enumeration = viewSpecificGroupRetained1.getAllViews(); enumeration.hasMoreElements();)
/* 340 */       viewSpecificGroupRetained2.addView((View)enumeration.nextElement()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ViewSpecificGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */