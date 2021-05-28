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
/*     */ abstract class SceneGraphObjectRetained
/*     */   extends IndexedObject
/*     */   implements Cloneable
/*     */ {
/*     */   SceneGraphObject source;
/*     */   boolean inBackgroundGroup = false;
/*     */   boolean onUpdateList = false;
/*     */   boolean inSetLive = false;
/*     */   static final int DONT_MERGE = 0;
/*     */   static final int MERGE = 1;
/*     */   static final int MERGE_DONE = 2;
/*  47 */   int mergeFlag = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   void setSource(SceneGraphObject paramSceneGraphObject) { this.source = paramSceneGraphObject; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   SceneGraphObject getSource() { return this.source; }
/*     */ 
/*     */   
/*     */   void markAsLive() {
/*  67 */     this.source.setLive();
/*  68 */     this.inSetLive = false;
/*     */   }
/*     */   
/*     */   void setLive(boolean paramBoolean) {
/*  72 */     doSetLive(paramBoolean);
/*  73 */     markAsLive();
/*     */   }
/*     */   
/*  76 */   boolean isInSetLive() { return this.inSetLive; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void doSetLive(boolean paramBoolean) {
/*  83 */     this.inSetLive = true;
/*  84 */     this.inBackgroundGroup = paramBoolean;
/*     */   }
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/*  88 */     doSetLive(paramSetLiveState);
/*  89 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void doSetLive(SetLiveState paramSetLiveState) {
/*  96 */     this.inSetLive = true;
/*  97 */     this.inBackgroundGroup = paramSetLiveState.inBackgroundGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(VirtualUniverse paramVirtualUniverse, int paramInt, boolean paramBoolean, HashKey[] paramArrayOfHashKey) {
/* 105 */     this.inBackgroundGroup = false;
/* 106 */     this.source.clearLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive() {
/* 113 */     this.inBackgroundGroup = false;
/* 114 */     this.source.clearLive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   void setCompiled() { this.source.setCompiled(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   void compile(CompileState paramCompileState) { setCompiled(); }
/*     */ 
/*     */ 
/*     */   
/*     */   void merge(CompileState paramCompileState) {}
/*     */ 
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {}
/*     */ 
/*     */   
/*     */   void traverse(boolean paramBoolean, int paramInt) {
/* 141 */     System.err.println();
/* 142 */     for (byte b = 0; b < paramInt; b++) {
/* 143 */       System.err.print(".");
/*     */     }
/* 145 */     System.err.print(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   boolean isStatic() { return this.source.capabilityBitsEmpty(); }
/*     */ 
/*     */   
/*     */   protected Object clone() {
/*     */     try {
/* 157 */       return super.clone();
/* 158 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/* 159 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {}
/*     */ 
/*     */   
/* 167 */   VirtualUniverse getVirtualUniverse() { return null; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\SceneGraphObjectRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */