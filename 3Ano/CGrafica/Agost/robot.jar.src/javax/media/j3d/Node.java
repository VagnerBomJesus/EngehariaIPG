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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Node
/*     */   extends SceneGraphObject
/*     */ {
/*     */   public static final int ENABLE_PICK_REPORTING = 1;
/*     */   public static final int ENABLE_COLLISION_REPORTING = 0;
/*     */   public static final int ALLOW_BOUNDS_READ = 3;
/*     */   public static final int ALLOW_BOUNDS_WRITE = 4;
/*     */   public static final int ALLOW_PICKABLE_READ = 5;
/*     */   public static final int ALLOW_PICKABLE_WRITE = 6;
/*     */   public static final int ALLOW_COLLIDABLE_READ = 7;
/*     */   public static final int ALLOW_COLLIDABLE_WRITE = 8;
/*     */   public static final int ALLOW_AUTO_COMPUTE_BOUNDS_READ = 9;
/*     */   public static final int ALLOW_AUTO_COMPUTE_BOUNDS_WRITE = 10;
/*     */   public static final int ALLOW_LOCAL_TO_VWORLD_READ = 11;
/*     */   public static final int ALLOW_PARENT_READ = 46;
/*     */   public static final int ALLOW_LOCALE_READ = 47;
/* 135 */   private static final int[] readCapabilities = { 3, 5, 7, 9, 11, 46, 47 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean visited;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node() {
/* 146 */     this.visited = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     setDefaultReadCapabilities(readCapabilities);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getParent() {
/* 171 */     if (isLiveOrCompiled() && 
/* 172 */       !getCapability(46)) {
/* 173 */       throw new CapabilityNotSetException(J3dI18N.getString("Node0"));
/*     */     }
/*     */ 
/*     */     
/* 177 */     NodeRetained nodeRetained = ((NodeRetained)this.retained).getParent();
/* 178 */     return (nodeRetained == null) ? null : (Node)nodeRetained.getSource();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBounds(Bounds paramBounds) {
/* 188 */     if (isLiveOrCompiled() && 
/* 189 */       !getCapability(4)) {
/* 190 */       throw new CapabilityNotSetException(J3dI18N.getString("Node1"));
/*     */     }
/* 192 */     ((NodeRetained)this.retained).setBounds(paramBounds);
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
/*     */   public Bounds getBounds() {
/* 205 */     if (isLiveOrCompiled()) {
/* 206 */       if (!getCapability(3)) {
/* 207 */         throw new CapabilityNotSetException(J3dI18N.getString("Node2"));
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 213 */       checkForCycle();
/*     */     } 
/*     */     
/* 216 */     return ((NodeRetained)this.retained).getBounds();
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
/*     */   public boolean getCollidable() {
/* 229 */     if (isLiveOrCompiled() && 
/* 230 */       !getCapability(7)) {
/* 231 */       throw new CapabilityNotSetException(J3dI18N.getString("Node16"));
/*     */     }
/* 233 */     return ((NodeRetained)this.retained).getCollidable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCollidable(boolean paramBoolean) {
/* 242 */     if (isLiveOrCompiled() && 
/* 243 */       !getCapability(8)) {
/* 244 */       throw new CapabilityNotSetException(J3dI18N.getString("Node4"));
/*     */     }
/* 246 */     ((NodeRetained)this.retained).setCollidable(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoundsAutoCompute(boolean paramBoolean) {
/* 257 */     if (isLiveOrCompiled() && 
/* 258 */       !getCapability(10)) {
/* 259 */       throw new CapabilityNotSetException(J3dI18N.getString("Node5"));
/*     */     }
/* 261 */     ((NodeRetained)this.retained).setBoundsAutoCompute(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoundsAutoCompute() {
/* 271 */     if (isLiveOrCompiled() && 
/* 272 */       !getCapability(9)) {
/* 273 */       throw new CapabilityNotSetException(J3dI18N.getString("Node6"));
/*     */     }
/* 275 */     return ((NodeRetained)this.retained).getBoundsAutoCompute();
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
/*     */   public void getLocalToVworld(Transform3D paramTransform3D) {
/* 296 */     if (isLiveOrCompiled() && 
/* 297 */       !getCapability(11)) {
/* 298 */       throw new CapabilityNotSetException(J3dI18N.getString("Node8"));
/*     */     }
/*     */     
/* 301 */     if (!isLive()) {
/*     */       
/* 303 */       if (isCompiled()) {
/* 304 */         throw new RestrictedAccessException(J3dI18N.getString("Node7"));
/*     */       }
/*     */       
/* 307 */       ((NodeRetained)this.retained).computeNonLiveLocalToVworld(paramTransform3D, this);
/*     */     } else {
/*     */       
/* 310 */       ((NodeRetained)this.retained).getLocalToVworld(paramTransform3D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getLocalToVworld(SceneGraphPath paramSceneGraphPath, Transform3D paramTransform3D) {
/* 336 */     if (!isLive()) {
/* 337 */       throw new RestrictedAccessException(J3dI18N.getString("Node7"));
/*     */     }
/*     */     
/* 340 */     if (!getCapability(11)) {
/* 341 */       throw new CapabilityNotSetException(J3dI18N.getString("Node8"));
/*     */     }
/* 343 */     ((NodeRetained)this.retained).getLocalToVworld(paramSceneGraphPath, paramTransform3D);
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
/*     */   public Locale getLocale() {
/* 361 */     if (!isLive()) {
/* 362 */       return null;
/*     */     }
/*     */     
/* 365 */     if (!getCapability(47)) {
/* 366 */       throw new CapabilityNotSetException(J3dI18N.getString("Node17"));
/*     */     }
/*     */     
/* 369 */     return ((NodeRetained)this.retained).getLocale();
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
/* 391 */   public Node cloneTree() { return cloneTree(new NodeReferenceTable(), false, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 418 */   public Node cloneTree(boolean paramBoolean) { return cloneTree(new NodeReferenceTable(), paramBoolean, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public Node cloneTree(boolean paramBoolean1, boolean paramBoolean2) { return cloneTree(new NodeReferenceTable(), paramBoolean1, paramBoolean2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   public Node cloneTree(NodeReferenceTable paramNodeReferenceTable) { return cloneTree(paramNodeReferenceTable, false, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 519 */   public Node cloneTree(NodeReferenceTable paramNodeReferenceTable, boolean paramBoolean) { return cloneTree(paramNodeReferenceTable, paramBoolean, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneTree(NodeReferenceTable paramNodeReferenceTable, boolean paramBoolean1, boolean paramBoolean2) {
/* 561 */     if (!isLiveOrCompiled())
/*     */     {
/*     */       
/* 564 */       checkForCycle();
/*     */     }
/*     */     
/* 567 */     paramNodeReferenceTable.set(paramBoolean2, new Hashtable());
/* 568 */     Node node = cloneTree(paramBoolean1, paramNodeReferenceTable.objectHashtable);
/*     */ 
/*     */ 
/*     */     
/* 572 */     Enumeration enumeration = paramNodeReferenceTable.objectHashtable.elements();
/*     */     
/* 574 */     while (enumeration.hasMoreElements()) {
/* 575 */       SceneGraphObject sceneGraphObject = (SceneGraphObject)enumeration.nextElement();
/* 576 */       sceneGraphObject.updateNodeReferences(paramNodeReferenceTable);
/*     */     } 
/* 578 */     return node;
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
/*     */   Node cloneTree(boolean paramBoolean, Hashtable paramHashtable) {
/*     */     Node node;
/* 606 */     this.nodeHashtable = paramHashtable;
/*     */     try {
/* 608 */       node = cloneNode(paramBoolean);
/* 609 */     } catch (RuntimeException runtimeException) {
/* 610 */       this.nodeHashtable = null;
/* 611 */       throw runtimeException;
/*     */     } 
/*     */ 
/*     */     
/* 615 */     this.nodeHashtable = null;
/* 616 */     paramHashtable.put(this, node);
/* 617 */     return node;
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
/* 651 */   public Node cloneNode(boolean paramBoolean) { throw new RuntimeException(J3dI18N.getString("Node12")); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 688 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { duplicateAttributes(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void checkDuplicateNode(Node paramNode, boolean paramBoolean) {
/* 721 */     if (paramNode.nodeHashtable != null) {
/* 722 */       duplicateAttributes(paramNode, paramBoolean);
/*     */     }
/*     */     else {
/*     */       
/* 726 */       paramNode.nodeHashtable = new Hashtable();
/* 727 */       duplicateAttributes(paramNode, paramBoolean);
/* 728 */       paramNode.nodeHashtable = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 756 */     if (paramNode.isLiveOrCompiled()) {
/* 757 */       throw new RestrictedAccessException(J3dI18N.getString("Node13"));
/*     */     }
/* 759 */     duplicateSceneGraphObject(paramNode);
/* 760 */     NodeRetained nodeRetained1 = (NodeRetained)paramNode.retained;
/* 761 */     NodeRetained nodeRetained2 = (NodeRetained)this.retained;
/*     */     
/* 763 */     nodeRetained2.setPickable(nodeRetained1.getPickable());
/* 764 */     nodeRetained2.setCollidable(nodeRetained1.getCollidable());
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
/*     */   public void setPickable(boolean paramBoolean) {
/* 776 */     if (isLiveOrCompiled() && 
/* 777 */       !getCapability(6)) {
/* 778 */       throw new CapabilityNotSetException(J3dI18N.getString("Node14"));
/*     */     }
/* 780 */     ((NodeRetained)this.retained).setPickable(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPickable() {
/* 788 */     if (isLiveOrCompiled() && 
/* 789 */       !getCapability(5)) {
/* 790 */       throw new CapabilityNotSetException(J3dI18N.getString("Node3"));
/*     */     }
/* 792 */     return ((NodeRetained)this.retained).getPickable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void checkForCycle() {
/* 799 */     if (this.visited) {
/* 800 */       throw new SceneGraphCycleException(J3dI18N.getString("Node15"));
/*     */     }
/* 802 */     this.visited = true;
/* 803 */     Node node = getParent();
/* 804 */     if (node != null) {
/* 805 */       node.checkForCycle();
/*     */     }
/* 807 */     this.visited = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Node.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */