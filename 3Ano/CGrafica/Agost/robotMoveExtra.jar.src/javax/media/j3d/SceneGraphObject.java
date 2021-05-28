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
/*     */ 
/*     */ public abstract class SceneGraphObject
/*     */ {
/*     */   SceneGraphObjectRetained retained;
/*     */   private long capabilityBits;
/*     */   private long capabilityIsFrequentBits;
/*     */   private boolean compiled;
/*     */   private boolean live;
/*     */   private boolean liveOrCompiled;
/*     */   private Object userData;
/*     */   private String objectName;
/*     */   Hashtable nodeHashtable;
/*     */   
/*     */   public SceneGraphObject() {
/*  61 */     this.capabilityBits = 0L;
/*     */ 
/*     */     
/*  64 */     this.capabilityIsFrequentBits = -1L;
/*     */ 
/*     */     
/*  67 */     this.compiled = false;
/*     */ 
/*     */     
/*  70 */     this.live = false;
/*     */ 
/*     */     
/*  73 */     this.liveOrCompiled = false;
/*     */ 
/*     */     
/*  76 */     this.userData = null;
/*     */ 
/*     */     
/*  79 */     this.objectName = null;
/*     */ 
/*     */     
/*  82 */     this.nodeHashtable = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     createRetained();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   void createRetained() { this.retained = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDefaultReadCapabilities(int[] paramArrayOfInt) {
/* 123 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 124 */       setCapability(paramArrayOfInt[b]);
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
/* 137 */   public final boolean getCapability(int paramInt) { return ((this.capabilityBits & 1L << paramInt) != 0L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setCapability(int paramInt) {
/* 149 */     if (isLiveOrCompiled()) {
/* 150 */       throw new RestrictedAccessException(J3dI18N.getString("SceneGraphObject0"));
/*     */     }
/*     */     
/* 153 */     this.capabilityBits |= 1L << paramInt;
/* 154 */     this.retained.handleFrequencyChange(paramInt);
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
/*     */   public final void clearCapability(int paramInt) {
/* 167 */     if (isLiveOrCompiled()) {
/* 168 */       throw new RestrictedAccessException(J3dI18N.getString("SceneGraphObject0"));
/*     */     }
/* 170 */     this.capabilityBits &= (1L << paramInt ^ 0xFFFFFFFFFFFFFFFFL);
/* 171 */     this.retained.handleFrequencyChange(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   final boolean capabilityBitsEmpty() { return (this.capabilityBits == 0L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public final boolean getCapabilityIsFrequent(int paramInt) { return ((this.capabilityIsFrequentBits & 1L << paramInt) != 0L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setCapabilityIsFrequent(int paramInt) {
/* 228 */     if (isCompiled()) {
/* 229 */       throw new RestrictedAccessException(J3dI18N.getString("SceneGraphObject1"));
/*     */     }
/* 231 */     this.capabilityIsFrequentBits |= 1L << paramInt;
/* 232 */     this.retained.handleFrequencyChange(paramInt);
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
/*     */   public final void clearCapabilityIsFrequent(int paramInt) {
/* 262 */     if (isCompiled()) {
/* 263 */       throw new RestrictedAccessException(J3dI18N.getString("SceneGraphObject1"));
/*     */     }
/* 265 */     this.capabilityIsFrequentBits &= (1L << paramInt ^ 0xFFFFFFFFFFFFFFFFL);
/* 266 */     this.retained.handleFrequencyChange(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setCompiled() {
/* 275 */     this.compiled = true;
/* 276 */     this.liveOrCompiled = (this.live || this.compiled);
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
/* 287 */   public final boolean isCompiled() { return this.compiled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setLive() {
/* 295 */     this.live = true;
/* 296 */     this.liveOrCompiled = (this.live || this.compiled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void clearLive() {
/* 304 */     this.live = false;
/* 305 */     this.liveOrCompiled = (this.live || this.compiled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public final boolean isLive() { return this.live; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   final boolean isLiveOrCompiled() { return this.liveOrCompiled; }
/*     */ 
/*     */   
/*     */   final void checkForLiveOrCompiled() {
/* 327 */     if (isLiveOrCompiled()) {
/* 328 */       throw new RestrictedAccessException(J3dI18N.getString("SceneGraphObject2"));
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
/* 341 */   public void setUserData(Object paramObject) { this.userData = paramObject; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public Object getUserData() { return this.userData; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 392 */   public void setName(String paramString) { this.objectName = paramString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public String getName() { return this.objectName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void duplicateSceneGraphObject(SceneGraphObject paramSceneGraphObject) {
/* 425 */     this.capabilityBits = paramSceneGraphObject.capabilityBits;
/* 426 */     this.userData = paramSceneGraphObject.userData;
/* 427 */     this.objectName = paramSceneGraphObject.objectName;
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
/*     */   NodeComponent getNodeComponent(NodeComponent paramNodeComponent, boolean paramBoolean, Hashtable paramHashtable) {
/* 453 */     if (paramNodeComponent != null && (paramBoolean || paramNodeComponent.duplicateChild())) {
/*     */ 
/*     */       
/* 456 */       NodeComponent nodeComponent = (NodeComponent)paramHashtable.get(paramNodeComponent);
/*     */       
/* 458 */       if (nodeComponent == null) {
/* 459 */         paramNodeComponent.nodeHashtable = paramHashtable;
/*     */         try {
/* 461 */           nodeComponent = paramNodeComponent.cloneNodeComponent(paramBoolean);
/*     */         }
/* 463 */         catch (RuntimeException runtimeException) {
/*     */           
/* 465 */           paramNodeComponent.nodeHashtable = null;
/* 466 */           throw runtimeException;
/*     */         } 
/* 468 */         paramNodeComponent.nodeHashtable = null;
/*     */         
/* 470 */         paramHashtable.put(paramNodeComponent, nodeComponent);
/*     */       } 
/* 472 */       return nodeComponent;
/*     */     } 
/* 474 */     return paramNodeComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   String getNamePrefix() {
/* 480 */     String str = getName();
/*     */     
/* 482 */     if (str != null) {
/* 483 */       return "[" + str + "] ";
/*     */     }
/*     */     
/* 486 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 495 */   public String toString() { return getNamePrefix() + super.toString(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SceneGraphObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */