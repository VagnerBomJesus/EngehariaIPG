/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Group
/*     */   extends Node
/*     */ {
/*     */   public static final int ALLOW_CHILDREN_READ = 12;
/*     */   public static final int ALLOW_CHILDREN_WRITE = 13;
/*     */   public static final int ALLOW_CHILDREN_EXTEND = 14;
/*     */   public static final int ALLOW_COLLISION_BOUNDS_READ = 15;
/*     */   public static final int ALLOW_COLLISION_BOUNDS_WRITE = 16;
/*  64 */   private static final int[] readCapabilities = { 12, 15 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/*  75 */     this.retained = new GroupRetained();
/*  76 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCollisionBounds(Bounds paramBounds) {
/*  87 */     if (isLiveOrCompiled() && 
/*  88 */       !getCapability(16)) {
/*  89 */       throw new CapabilityNotSetException(J3dI18N.getString("Group0"));
/*     */     }
/*  91 */     ((GroupRetained)this.retained).setCollisionBounds(paramBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getCollisionBounds() {
/* 101 */     if (isLiveOrCompiled() && 
/* 102 */       !getCapability(15)) {
/* 103 */       throw new CapabilityNotSetException(J3dI18N.getString("Group1"));
/*     */     }
/* 105 */     return ((GroupRetained)this.retained).getCollisionBounds();
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
/*     */   public void setChild(Node paramNode, int paramInt) {
/* 125 */     if (paramNode instanceof SharedGroup) {
/* 126 */       throw new IllegalArgumentException(J3dI18N.getString("Group2"));
/*     */     }
/*     */     
/* 129 */     if (isLiveOrCompiled()) {
/* 130 */       Node node = ((GroupRetained)this.retained).getChild(paramInt);
/*     */       
/* 132 */       if (!(paramNode instanceof BranchGroup)) {
/* 133 */         throw new RestrictedAccessException(J3dI18N.getString("Group3"));
/*     */       }
/* 135 */       if (!getCapability(13)) {
/* 136 */         throw new CapabilityNotSetException(J3dI18N.getString("Group13"));
/*     */       }
/* 138 */       if (node != null && !((BranchGroup)node).getCapability(17))
/*     */       {
/* 140 */         throw new CapabilityNotSetException(J3dI18N.getString("Group4"));
/*     */       }
/*     */     } 
/*     */     
/* 144 */     ((GroupRetained)this.retained).setChild(paramNode, paramInt);
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
/*     */   public void insertChild(Node paramNode, int paramInt) {
/* 166 */     if (paramNode instanceof SharedGroup) {
/* 167 */       throw new IllegalArgumentException(J3dI18N.getString("Group2"));
/*     */     }
/*     */     
/* 170 */     if (isLiveOrCompiled()) {
/* 171 */       if (!(paramNode instanceof BranchGroup)) {
/* 172 */         throw new RestrictedAccessException(J3dI18N.getString("Group6"));
/*     */       }
/* 174 */       if (!getCapability(13)) {
/* 175 */         throw new CapabilityNotSetException(J3dI18N.getString("Group14"));
/*     */       }
/*     */     } 
/* 178 */     ((GroupRetained)this.retained).insertChild(paramNode, paramInt);
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
/*     */   public void removeChild(int paramInt) {
/* 195 */     if (isLiveOrCompiled()) {
/* 196 */       Node node = ((GroupRetained)this.retained).getChild(paramInt);
/* 197 */       if (!(node instanceof BranchGroup)) {
/* 198 */         throw new RestrictedAccessException(J3dI18N.getString("Group7"));
/*     */       }
/*     */       
/* 201 */       if (!getCapability(13)) {
/* 202 */         throw new CapabilityNotSetException(J3dI18N.getString("Group15"));
/*     */       }
/*     */       
/* 205 */       if (!((BranchGroup)node).getCapability(17)) {
/* 206 */         throw new CapabilityNotSetException(J3dI18N.getString("Group4"));
/*     */       }
/*     */     } 
/*     */     
/* 210 */     ((GroupRetained)this.retained).removeChild(paramInt);
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
/*     */   public Node getChild(int paramInt) {
/* 225 */     if (isLiveOrCompiled() && 
/* 226 */       !getCapability(12)) {
/* 227 */       throw new CapabilityNotSetException(J3dI18N.getString("Group9"));
/*     */     }
/* 229 */     return ((GroupRetained)this.retained).getChild(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration getAllChildren() {
/* 239 */     if (isLiveOrCompiled() && 
/* 240 */       !getCapability(12)) {
/* 241 */       throw new CapabilityNotSetException(J3dI18N.getString("Group9"));
/*     */     }
/* 243 */     return ((GroupRetained)this.retained).getAllChildren();
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
/*     */   public void addChild(Node paramNode) {
/* 259 */     if (paramNode instanceof SharedGroup) {
/* 260 */       throw new IllegalArgumentException(J3dI18N.getString("Group2"));
/*     */     }
/*     */     
/* 263 */     if (isLiveOrCompiled()) {
/* 264 */       if (!(paramNode instanceof BranchGroup)) {
/* 265 */         throw new RestrictedAccessException(J3dI18N.getString("Group12"));
/*     */       }
/* 267 */       if (!getCapability(14)) {
/* 268 */         throw new CapabilityNotSetException(J3dI18N.getString("Group16"));
/*     */       }
/*     */     } 
/* 271 */     ((GroupRetained)this.retained).addChild(paramNode);
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
/*     */   public void moveTo(BranchGroup paramBranchGroup) {
/* 283 */     if (isLiveOrCompiled()) {
/* 284 */       if (!getCapability(14)) {
/* 285 */         throw new CapabilityNotSetException(J3dI18N.getString("Group16"));
/*     */       }
/* 287 */       if (!paramBranchGroup.getCapability(17)) {
/* 288 */         throw new CapabilityNotSetException(J3dI18N.getString("Group4"));
/*     */       }
/*     */     } 
/*     */     
/* 292 */     ((GroupRetained)this.retained).moveTo(paramBranchGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int numChildren() {
/* 302 */     if (isLiveOrCompiled() && 
/* 303 */       !getCapability(12)) {
/* 304 */       throw new CapabilityNotSetException(J3dI18N.getString("Group9"));
/*     */     }
/* 306 */     return ((GroupRetained)this.retained).numChildren();
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
/*     */   public int indexOfChild(Node paramNode) {
/* 324 */     if (isLiveOrCompiled() && 
/* 325 */       !getCapability(12)) {
/* 326 */       throw new CapabilityNotSetException(J3dI18N.getString("Group9"));
/*     */     }
/* 328 */     return ((GroupRetained)this.retained).indexOfChild(paramNode);
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
/*     */   public void removeChild(Node paramNode) {
/* 348 */     if (isLiveOrCompiled()) {
/* 349 */       if (!(paramNode instanceof BranchGroup)) {
/* 350 */         throw new RestrictedAccessException(J3dI18N.getString("Group7"));
/*     */       }
/*     */       
/* 353 */       if (!getCapability(13)) {
/* 354 */         throw new CapabilityNotSetException(J3dI18N.getString("Group15"));
/*     */       }
/*     */       
/* 357 */       if (!((BranchGroup)paramNode).getCapability(17)) {
/* 358 */         throw new CapabilityNotSetException(J3dI18N.getString("Group4"));
/*     */       }
/*     */     } 
/*     */     
/* 362 */     ((GroupRetained)this.retained).removeChild(paramNode);
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
/*     */   public void removeAllChildren() {
/* 379 */     if (isLiveOrCompiled()) {
/* 380 */       GroupRetained groupRetained = (GroupRetained)this.retained;
/* 381 */       for (int i = groupRetained.numChildren() - 1; i >= 0; i--) {
/* 382 */         Node node = groupRetained.getChild(i);
/* 383 */         if (!(node instanceof BranchGroup)) {
/* 384 */           throw new RestrictedAccessException(J3dI18N.getString("Group7"));
/*     */         }
/* 386 */         if (!getCapability(13)) {
/* 387 */           throw new CapabilityNotSetException(J3dI18N.getString("Group15"));
/*     */         }
/* 389 */         if (!((BranchGroup)node).getCapability(17)) {
/* 390 */           throw new CapabilityNotSetException(J3dI18N.getString("Group4"));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 395 */     ((GroupRetained)this.retained).removeAllChildren();
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
/* 419 */   public void setAlternateCollisionTarget(boolean paramBoolean) { ((GroupRetained)this.retained).setAlternateCollisionTarget(paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public boolean getAlternateCollisionTarget() { return ((GroupRetained)this.retained).getAlternateCollisionTarget(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Node cloneTree(boolean paramBoolean, Hashtable paramHashtable) {
/* 454 */     Group group = (Group)super.cloneTree(paramBoolean, paramHashtable);
/* 455 */     GroupRetained groupRetained = (GroupRetained)this.retained;
/*     */     
/* 457 */     int i = groupRetained.numChildren();
/*     */     
/* 459 */     for (byte b = 0; b < i; b++) {
/* 460 */       Node node1 = groupRetained.getChild(b);
/* 461 */       Node node2 = node1.cloneTree(paramBoolean, paramHashtable);
/*     */       
/* 463 */       ((GroupRetained)group.retained).addChild(node2);
/*     */     } 
/*     */     
/* 466 */     return group;
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
/* 493 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 495 */     GroupRetained groupRetained1 = (GroupRetained)paramNode.retained;
/* 496 */     GroupRetained groupRetained2 = (GroupRetained)this.retained;
/*     */     
/* 498 */     groupRetained2.setCollisionBounds(groupRetained1.getCollisionBounds());
/* 499 */     groupRetained2.setAlternateCollisionTarget(groupRetained1.getAlternateCollisionTarget());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 504 */     groupRetained2.children.clear();
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
/* 523 */     Group group = new Group();
/* 524 */     group.duplicateNode(this, paramBoolean);
/* 525 */     return group;
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
/* 539 */   public Group() { setDefaultReadCapabilities(readCapabilities); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Group.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */