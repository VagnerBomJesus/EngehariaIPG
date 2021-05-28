/*     */ package javax.media.j3d;
/*     */ 
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
/*     */ public abstract class NodeComponent
/*     */   extends SceneGraphObject
/*     */ {
/*     */   boolean forceDuplicate = false;
/*     */   
/*  52 */   public void setDuplicateOnCloneTree(boolean paramBoolean) { ((NodeComponentRetained)this.retained).setDuplicateOnCloneTree(paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public boolean getDuplicateOnCloneTree() { return ((NodeComponentRetained)this.retained).getDuplicateOnCloneTree(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public NodeComponent cloneNodeComponent() { throw new RuntimeException(J3dI18N.getString("NodeComponent0")); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { duplicateAttributes(paramNodeComponent, paramNodeComponent.forceDuplicate); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void checkDuplicateNodeComponent(NodeComponent paramNodeComponent) {
/* 114 */     if (paramNodeComponent.nodeHashtable != null) {
/* 115 */       duplicateAttributes(paramNodeComponent, paramNodeComponent.forceDuplicate);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 120 */       paramNodeComponent.nodeHashtable = new Hashtable();
/* 121 */       duplicateAttributes(paramNodeComponent, paramNodeComponent.forceDuplicate);
/*     */       
/* 123 */       paramNodeComponent.nodeHashtable = null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void duplicateNodeComponent(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 154 */     paramNodeComponent.forceDuplicate = paramBoolean;
/*     */     try {
/* 156 */       duplicateNodeComponent(paramNodeComponent);
/* 157 */     } catch (RuntimeException runtimeException) {
/* 158 */       paramNodeComponent.forceDuplicate = false;
/* 159 */       throw runtimeException;
/*     */     } 
/* 161 */     paramNodeComponent.forceDuplicate = false;
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
/*     */   public NodeComponent cloneNodeComponent(boolean paramBoolean) {
/*     */     NodeComponent nodeComponent;
/* 206 */     this.forceDuplicate = paramBoolean;
/*     */     try {
/* 208 */       nodeComponent = cloneNodeComponent();
/* 209 */     } catch (RuntimeException runtimeException) {
/* 210 */       this.forceDuplicate = false;
/* 211 */       throw runtimeException;
/*     */     } 
/* 213 */     this.forceDuplicate = false;
/* 214 */     return nodeComponent;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 240 */     if (paramBoolean && paramNodeComponent.isCompiled()) {
/* 241 */       throw new RestrictedAccessException(J3dI18N.getString("NodeComponent1"));
/*     */     }
/*     */ 
/*     */     
/* 245 */     duplicateSceneGraphObject(paramNodeComponent);
/* 246 */     setDuplicateOnCloneTree(paramNodeComponent.getDuplicateOnCloneTree());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 254 */     this.retained = new NodeComponentRetained();
/* 255 */     this.retained.setSource(this);
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
/* 266 */   boolean duplicateChild() { return getDuplicateOnCloneTree(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void validateImageIllegalSharing(ImageComponent paramImageComponent) {
/* 279 */     if (paramImageComponent != null) {
/* 280 */       ImageComponentRetained imageComponentRetained = (ImageComponentRetained)paramImageComponent.retained;
/* 281 */       NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)this.retained;
/* 282 */       if (imageComponentRetained.getUsedByOffScreen()) {
/* 283 */         if (isLive()) {
/* 284 */           throw new IllegalSharingException(J3dI18N.getString("NodeComponent2"));
/*     */         }
/* 286 */         if (nodeComponentRetained.getInImmCtx())
/* 287 */           throw new IllegalSharingException(J3dI18N.getString("NodeComponent3")); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\NodeComponent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */