/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ViewPlatformRetained
/*     */   extends LeafRetained
/*     */ {
/*     */   static final int VP_IN_BS_LIST = 0;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 1;
/*     */   int viewAttachPolicy;
/*     */   private ArrayList viewList;
/*     */   private View[] views;
/*     */   Locale locale;
/*     */   boolean viewListDirty;
/*     */   Transform3D vworldToVpc;
/*     */   Transform3D vpcToVworld;
/*     */   BoundingSphere sphere;
/*     */   BoundingSphere schedSphere;
/*     */   Point3d center;
/*  79 */   static final Point3d zeroPoint = new Point3d();
/*     */ 
/*     */ 
/*     */   
/*     */   int vprDirtyMask;
/*     */ 
/*     */   
/*  86 */   static final Object[] emptyObj = new Object[0];
/*  87 */   static final Transform3D identity = new Transform3D(); ViewPlatformRetained() { this.viewAttachPolicy = 0; this.viewList = new ArrayList(); this.views = null; this.locale = null; this.viewListDirty = true; this.vworldToVpc = null; this.vpcToVworld = new Transform3D(); this.sphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 62.0D);
/*     */     this.center = new Point3d();
/*     */     this.vprDirtyMask = 196608;
/*  90 */     this.nodeType = 16;
/*  91 */     this.localBounds = new BoundingBox();
/*  92 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/*  93 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*  94 */     IndexedUnorderSet.init(this, 1);
/*  95 */     this.schedSphere = (BoundingSphere)this.sphere.clone(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setViewAttachPolicy(int paramInt) {
/* 107 */     synchronized (this) {
/* 108 */       this.viewAttachPolicy = paramInt;
/* 109 */       this.vprDirtyMask |= 0x10000;
/*     */     } 
/*     */     
/* 112 */     if (this.source != null && this.source.isLive()) {
/* 113 */       repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void repaint() {
/* 119 */     View[] arrayOfView = getViewList();
/* 120 */     for (int i = arrayOfView.length - 1; i >= 0; i--) {
/* 121 */       arrayOfView[i].repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   int getViewAttachPolicy() { return this.viewAttachPolicy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setActivationRadius(float paramFloat) {
/* 137 */     this.sphere.setRadius(paramFloat);
/*     */     
/* 139 */     if (this.source != null && this.source.isLive()) {
/* 140 */       repaint();
/*     */     }
/*     */     
/* 143 */     if (this.source.isLive()) {
/* 144 */       J3dMessage j3dMessage = new J3dMessage();
/* 145 */       j3dMessage.type = 48;
/* 146 */       j3dMessage.threads = 384;
/* 147 */       j3dMessage.universe = this.universe;
/* 148 */       j3dMessage.args[0] = this;
/* 149 */       j3dMessage.args[1] = new Float(paramFloat);
/* 150 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } else {
/* 152 */       this.schedSphere.setRadius(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   float getActivationRadius() { return (float)this.sphere.getRadius(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setView(View paramView) {
/* 168 */     synchronized (this.viewList) {
/* 169 */       if (!this.viewList.contains(paramView)) {
/* 170 */         this.viewList.add(paramView);
/*     */       }
/* 172 */       this.viewListDirty = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   void removeView(View paramView) {
/* 177 */     synchronized (this.viewList) {
/* 178 */       if (this.viewList.contains(paramView)) {
/* 179 */         this.viewList.remove(this.viewList.indexOf(paramView));
/*     */       }
/* 181 */       this.viewListDirty = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   Transform3D getVworldToVpc() {
/* 186 */     if (this.vworldToVpc == null)
/* 187 */       this.vworldToVpc = new Transform3D(); 
/* 188 */     this.vworldToVpc.set(getCurrentLocalToVworld(null));
/* 189 */     this.vworldToVpc.invert();
/* 190 */     return this.vworldToVpc;
/*     */   }
/*     */   
/*     */   Transform3D getVpcToVworld() {
/* 194 */     this.vpcToVworld.set(getCurrentLocalToVworld(null));
/* 195 */     return this.vpcToVworld;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   void evaluateViewPlatformTransform() { this.vworldToVpc = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void evaluateInitViewPlatformTransform(NodeRetained paramNodeRetained, Transform3D paramTransform3D) {
/* 215 */     if (paramNodeRetained instanceof TransformGroupRetained) {
/* 216 */       Transform3D transform3D = new Transform3D();
/* 217 */       TransformGroupRetained transformGroupRetained = (TransformGroupRetained)paramNodeRetained;
/* 218 */       transformGroupRetained.transform.getWithLock(transform3D);
/* 219 */       paramTransform3D.mul(transform3D, paramTransform3D);
/*     */     } 
/*     */     
/* 222 */     NodeRetained nodeRetained = paramNodeRetained.getParent();
/* 223 */     if (nodeRetained != null)
/*     */     {
/* 225 */       evaluateInitViewPlatformTransform(nodeRetained, paramTransform3D);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void evaluateInitViewPlatformTransform() {
/* 233 */     synchronized (this) {
/* 234 */       Transform3D transform3D = getLastLocalToVworld();
/*     */       
/* 236 */       if (transform3D.equals(identity))
/*     */       {
/*     */         
/* 239 */         evaluateInitViewPlatformTransform(this, transform3D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateActivationRadius(float paramFloat) {
/* 248 */     this.schedSphere.setCenter(zeroPoint);
/* 249 */     this.schedSphere.setRadius(paramFloat);
/* 250 */     this.schedSphere.transform(getCurrentLocalToVworld(null));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateTransformRegion() {
/* 256 */     Transform3D transform3D = getCurrentLocalToVworld(null);
/* 257 */     this.schedSphere.setCenter(zeroPoint);
/* 258 */     this.schedSphere.transform(transform3D);
/* 259 */     transform3D.transform(zeroPoint, this.center);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 268 */     View[] arrayOfView = getViewList();
/*     */     int i;
/* 270 */     for (i = arrayOfView.length - 1; i >= 0; i--) {
/* 271 */       arrayOfView[i].checkView();
/*     */     }
/*     */     
/* 274 */     doSetLive(paramSetLiveState);
/*     */     
/* 276 */     if (this.inBackgroundGroup) {
/* 277 */       throw new IllegalSceneGraphException(J3dI18N.getString("ViewPlatformRetained1"));
/*     */     }
/*     */ 
/*     */     
/* 281 */     if (this.inSharedGroup) {
/* 282 */       throw new IllegalSharingException(J3dI18N.getString("ViewPlatformRetained2"));
/*     */     }
/*     */ 
/*     */     
/* 286 */     if (paramSetLiveState.viewLists != null) {
/* 287 */       throw new IllegalSceneGraphException(J3dI18N.getString("ViewPlatformRetained3"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 298 */     this.locale = paramSetLiveState.locale;
/*     */ 
/*     */     
/* 301 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 302 */       paramSetLiveState.transformTargets[0].addNode(this, 4);
/* 303 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */     
/* 306 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 308 */       paramSetLiveState.switchTargets[0].addNode(this, 4);
/*     */     }
/* 310 */     this.switchState = (SwitchState)paramSetLiveState.switchStates.get(0);
/* 311 */     paramSetLiveState.nodeList.add(this);
/* 312 */     paramSetLiveState.notifyThreads |= 0x100;
/* 313 */     markAsLive();
/* 314 */     for (i = arrayOfView.length - 1; i >= 0; i--) {
/* 315 */       arrayOfView[i].setUniverse(paramSetLiveState.universe);
/* 316 */       arrayOfView[i].evaluateActive();
/*     */     } 
/*     */     
/* 319 */     this.universe.addViewPlatform(this);
/* 320 */     paramSetLiveState.traverseFlags |= 0x1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(SetLiveState paramSetLiveState) {
/* 329 */     super.clearLive(paramSetLiveState);
/* 330 */     if (paramSetLiveState.switchTargets != null && paramSetLiveState.switchTargets[false] != null)
/*     */     {
/* 332 */       paramSetLiveState.switchTargets[0].addNode(this, 4);
/*     */     }
/*     */     
/* 335 */     View[] arrayOfView = getViewList();
/* 336 */     for (int i = arrayOfView.length - 1; i >= 0; i--) {
/* 337 */       arrayOfView[i].evaluateActive();
/*     */     }
/* 339 */     paramSetLiveState.nodeList.add(this);
/* 340 */     if (paramSetLiveState.transformTargets != null && paramSetLiveState.transformTargets[false] != null) {
/* 341 */       paramSetLiveState.transformTargets[0].addNode(this, 4);
/* 342 */       paramSetLiveState.notifyThreads |= 0x2000;
/*     */     } 
/*     */     
/* 345 */     paramSetLiveState.notifyThreads |= 0x102;
/*     */     
/* 347 */     this.universe.removeViewPlatform(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void reEvaluateView() {
/* 356 */     View[] arrayOfView = getViewList();
/*     */     
/* 358 */     for (int i = arrayOfView.length - 1; i >= 0; i--) {
/* 359 */       arrayOfView[i].evaluateActive();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   View[] getViewList() {
/* 367 */     synchronized (this.viewList) {
/* 368 */       if (this.viewListDirty) {
/* 369 */         this.views = (View[])this.viewList.toArray(new View[this.viewList.size()]);
/* 370 */         this.viewListDirty = false;
/*     */       } 
/* 372 */       return this.views;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isActiveViewPlatform() {
/* 381 */     View[] arrayOfView = getViewList();
/* 382 */     if (arrayOfView != null) {
/* 383 */       for (byte b = 0; b < arrayOfView.length; b++) {
/* 384 */         if ((arrayOfView[b]).active) {
/* 385 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 389 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 393 */   void processSwitchChanged() { reEvaluateView(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void compile(CompileState paramCompileState) {
/* 399 */     super.compile(paramCompileState);
/*     */ 
/*     */ 
/*     */     
/* 403 */     paramCompileState.keepTG = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ViewPlatformRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */