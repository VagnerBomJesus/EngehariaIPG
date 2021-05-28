/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OrderedGroup
/*     */   extends Group
/*     */ {
/*     */   private boolean[] checkArr;
/*     */   public static final int ALLOW_CHILD_INDEX_ORDER_READ = 17;
/*     */   public static final int ALLOW_CHILD_INDEX_ORDER_WRITE = 18;
/*  76 */   private static final int[] readCapabilities = { 17 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OrderedGroup() {
/*     */     this.checkArr = null;
/*  87 */     setDefaultReadCapabilities(readCapabilities);
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
/*     */   public void setChildIndexOrder(int[] paramArrayOfInt) {
/* 125 */     verifyChildIndexOrderArray(paramArrayOfInt, 0);
/*     */     
/* 127 */     ((OrderedGroupRetained)this.retained).setChildIndexOrder(paramArrayOfInt);
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
/*     */   public int[] getChildIndexOrder() {
/* 144 */     if (isLiveOrCompiled() && 
/* 145 */       !getCapability(17)) {
/* 146 */       throw new CapabilityNotSetException(J3dI18N.getString("OrderedGroup5"));
/*     */     }
/*     */     
/* 149 */     return ((OrderedGroupRetained)this.retained).getChildIndexOrder();
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
/*     */   public void addChild(Node paramNode, int[] paramArrayOfInt) {
/* 200 */     verifyAddStates(paramNode);
/* 201 */     verifyChildIndexOrderArray(paramArrayOfInt, 1);
/*     */     
/* 203 */     ((OrderedGroupRetained)this.retained).addChild(paramNode, paramArrayOfInt);
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
/* 233 */   public void addChild(Node paramNode) { super.addChild(paramNode); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertChild(Node paramNode, int paramInt) {
/* 262 */     if (((OrderedGroupRetained)this.retained).userChildIndexOrder != null) {
/* 263 */       throw new IllegalStateException(J3dI18N.getString("OrderedGroup6"));
/*     */     }
/*     */ 
/*     */     
/* 267 */     super.insertChild(paramNode, paramInt);
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
/* 299 */   public void removeChild(int paramInt) { super.removeChild(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public void moveTo(BranchGroup paramBranchGroup) { super.moveTo(paramBranchGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   public void removeChild(Node paramNode) { super.removeChild(paramNode); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   public void removeAllChildren() { super.removeAllChildren(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 384 */     this.retained = new OrderedGroupRetained();
/* 385 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */   
/*     */   void verifyAddStates(Node paramNode) {
/* 390 */     if (paramNode instanceof SharedGroup) {
/* 391 */       throw new IllegalArgumentException(J3dI18N.getString("Group2"));
/*     */     }
/*     */     
/* 394 */     if (isLiveOrCompiled()) {
/* 395 */       if (!(paramNode instanceof BranchGroup)) {
/* 396 */         throw new RestrictedAccessException(J3dI18N.getString("Group12"));
/*     */       }
/* 398 */       if (!getCapability(14)) {
/* 399 */         throw new CapabilityNotSetException(J3dI18N.getString("Group16"));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   void verifyChildIndexOrderArray(int[] paramArrayOfInt, int paramInt) {
/* 405 */     if (isLiveOrCompiled())
/*     */     {
/* 407 */       if (!getCapability(18)) {
/* 408 */         throw new CapabilityNotSetException(J3dI18N.getString("OrderedGroup4"));
/*     */       }
/*     */     }
/*     */     
/* 412 */     if (paramArrayOfInt != null) {
/*     */       
/* 414 */       if (paramArrayOfInt.length != ((GroupRetained)this.retained).children.size() + paramInt) {
/* 415 */         throw new IllegalArgumentException(J3dI18N.getString("OrderedGroup0"));
/*     */       }
/*     */ 
/*     */       
/* 419 */       if (this.checkArr == null || this.checkArr.length != paramArrayOfInt.length) {
/* 420 */         this.checkArr = new boolean[paramArrayOfInt.length];
/*     */       }
/*     */       
/* 423 */       Arrays.fill(this.checkArr, false);
/*     */       
/* 425 */       for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 426 */         if (paramArrayOfInt[b] < 0) {
/* 427 */           throw new IllegalArgumentException(J3dI18N.getString("OrderedGroup1"));
/*     */         }
/*     */         
/* 430 */         if (paramArrayOfInt[b] >= paramArrayOfInt.length) {
/* 431 */           throw new IllegalArgumentException(J3dI18N.getString("OrderedGroup2"));
/*     */         }
/*     */         
/* 434 */         if (this.checkArr[paramArrayOfInt[b]]) {
/* 435 */           throw new IllegalArgumentException(J3dI18N.getString("OrderedGroup3"));
/*     */         }
/*     */ 
/*     */         
/* 439 */         this.checkArr[paramArrayOfInt[b]] = true;
/*     */       } 
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
/* 460 */     OrderedGroup orderedGroup = new OrderedGroup();
/* 461 */     orderedGroup.duplicateNode(this, paramBoolean);
/* 462 */     return orderedGroup;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\OrderedGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */