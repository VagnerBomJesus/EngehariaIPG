/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NodeComponentRetained
/*     */   extends SceneGraphObjectRetained
/*     */ {
/*     */   boolean duplicateOnCloneTree = false;
/*  29 */   int refCount = 0;
/*  30 */   int refCnt = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inImmCtx = false;
/*     */ 
/*     */   
/*  37 */   ArrayList users = new ArrayList(1);
/*     */ 
/*     */   
/*  40 */   NodeComponentRetained mirror = null;
/*     */ 
/*     */ 
/*     */   
/*  44 */   int changedFrequent = 0;
/*  45 */   int compChanged = 0;
/*     */ 
/*     */   
/*     */   void doSetLive(boolean paramBoolean, int paramInt) {
/*  49 */     int i = this.refCount;
/*  50 */     this.refCount += paramInt;
/*  51 */     if (i <= 0) {
/*  52 */       doSetLive(paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  57 */       createMirrorObject();
/*     */     } 
/*     */   }
/*     */   
/*     */   void setLive(boolean paramBoolean, int paramInt) {
/*  62 */     int i = this.refCount;
/*  63 */     doSetLive(paramBoolean, paramInt);
/*  64 */     if (i <= 0) {
/*  65 */       markAsLive();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(int paramInt) {
/*  73 */     this.refCount -= paramInt;
/*     */     
/*  75 */     if (this.refCount <= 0) {
/*  76 */       clearLive();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  82 */   void incRefCnt() { this.refCnt++; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   void decRefCnt() { this.refCnt--; }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeAMirrorUser(Shape3DRetained paramShape3DRetained) {
/*  92 */     synchronized (this.mirror.users) {
/*  93 */       this.mirror.users.remove(paramShape3DRetained);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void addAMirrorUser(Shape3DRetained paramShape3DRetained) {
/*  99 */     synchronized (this.mirror.users) {
/* 100 */       this.mirror.users.add(paramShape3DRetained);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void copyMirrorUsers(NodeComponentRetained paramNodeComponentRetained) {
/* 106 */     synchronized (this.mirror.users) {
/* 107 */       synchronized (paramNodeComponentRetained.mirror.users) {
/* 108 */         int i = paramNodeComponentRetained.mirror.users.size();
/* 109 */         for (byte b = 0; b < i; b++) {
/* 110 */           this.mirror.users.add(paramNodeComponentRetained.mirror.users.get(b));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeMirrorUsers(NodeComponentRetained paramNodeComponentRetained) {
/* 120 */     synchronized (this.mirror.users) {
/* 121 */       synchronized (paramNodeComponentRetained.mirror.users) {
/* 122 */         for (int i = paramNodeComponentRetained.mirror.users.size() - 1; i >= 0; i--) {
/* 123 */           this.mirror.users.remove(this.mirror.users.indexOf(paramNodeComponentRetained.mirror.users.get(i)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void removeUser(NodeRetained paramNodeRetained) {
/* 131 */     if (paramNodeRetained.source.isLive()) {
/* 132 */       this.users.remove(this.users.indexOf(paramNodeRetained));
/*     */     }
/*     */   }
/*     */   
/*     */   void addUser(NodeRetained paramNodeRetained) {
/* 137 */     if (paramNodeRetained.source.isLive()) {
/* 138 */       this.users.add(paramNodeRetained);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void notifyUsers() {
/* 145 */     if (this.source == null || !this.source.isLive()) {
/*     */       return;
/*     */     }
/*     */     
/* 149 */     for (int i = this.users.size() - 1; i >= 0; i--) {
/* 150 */       ((NodeRetained)this.users.get(i)).notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   void setInImmCtx(boolean paramBoolean) { this.inImmCtx = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   boolean getInImmCtx() { return this.inImmCtx; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   void setDuplicateOnCloneTree(boolean paramBoolean) { this.duplicateOnCloneTree = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   boolean getDuplicateOnCloneTree() { return this.duplicateOnCloneTree; }
/*     */ 
/*     */ 
/*     */   
/*     */   void initMirrorObject() {}
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {}
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 211 */     initMirrorObject();
/* 212 */     this.mirror = null;
/*     */   }
/*     */   
/*     */   void setFrequencyChangeMask(int paramInt1, int paramInt2) {
/* 216 */     if (this.source.getCapabilityIsFrequent(paramInt1)) {
/* 217 */       this.changedFrequent |= paramInt2;
/* 218 */     } else if (!this.source.isLive()) {
/*     */       
/* 220 */       this.changedFrequent &= (paramInt2 ^ 0xFFFFFFFF);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object clone() {
/* 225 */     NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)super.clone();
/* 226 */     nodeComponentRetained.changedFrequent = this.changedFrequent;
/* 227 */     return nodeComponentRetained;
/*     */   }
/*     */ 
/*     */   
/* 231 */   protected void set(NodeComponentRetained paramNodeComponentRetained) { this.changedFrequent = paramNodeComponentRetained.changedFrequent; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\NodeComponentRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */