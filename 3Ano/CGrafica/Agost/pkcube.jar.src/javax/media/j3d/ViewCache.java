/*     */ package javax.media.j3d;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ViewCache
/*     */ {
/*     */   View view;
/*     */   int viewAttachPolicy;
/*     */   Point3d leftEyePosInHead;
/*     */   Point3d rightEyePosInHead;
/*     */   Point3d leftEarPosInHead;
/*     */   Point3d rightEarPosInHead;
/*     */   double nominalEyeHeightFromGround;
/*     */   double nominalEyeOffsetFromNominalScreen;
/*     */   Transform3D headToHeadTracker;
/*     */   Transform3D coexistenceToTrackerBase;
/*     */   Transform3D headTrackerToTrackerBase;
/*     */   Transform3D trackerBaseToHeadTracker;
/*     */   boolean trackingAvailable;
/*     */   int headIndex;
/*     */   int coexistenceCenterInPworldPolicy;
/*     */   boolean compatibilityModeEnable;
/*     */   boolean coexistenceCenteringEnable;
/*     */   Point3d leftManualEyeInCoexistence;
/*     */   Point3d rightManualEyeInCoexistence;
/*     */   int viewPolicy;
/*     */   int projectionPolicy;
/*     */   int screenScalePolicy;
/*     */   double screenScale;
/*     */   int windowResizePolicy;
/*     */   int windowMovementPolicy;
/*     */   int windowEyepointPolicy;
/*     */   int monoscopicViewPolicy;
/*     */   double fieldOfView;
/*     */   double frontClipDistance;
/*     */   double backClipDistance;
/*     */   int frontClipPolicy;
/*     */   int backClipPolicy;
/*     */   ViewPlatformRetained vpRetained;
/*     */   int visibilityPolicy;
/*     */   boolean trackingEnable;
/*     */   boolean userHeadToVworldEnable;
/*     */   Transform3D compatVpcToEc;
/*     */   Transform3D compatLeftProjection;
/*     */   Transform3D compatRightProjection;
/*     */   int vcDirtyMask;
/*     */   private boolean doHeadTracking;
/*     */   Transform3D userHeadToVworld;
/*     */   
/*     */   void snapshot() {
/* 205 */     this.vcDirtyMask = this.view.vDirtyMask;
/* 206 */     this.view.vDirtyMask = 0;
/* 207 */     this.compatibilityModeEnable = this.view.compatibilityModeEnable;
/* 208 */     this.coexistenceCenteringEnable = this.view.coexistenceCenteringEnable;
/* 209 */     this.leftManualEyeInCoexistence.set(this.view.leftManualEyeInCoexistence);
/* 210 */     this.rightManualEyeInCoexistence.set(this.view.rightManualEyeInCoexistence);
/* 211 */     this.viewPolicy = this.view.viewPolicy;
/* 212 */     this.projectionPolicy = this.view.projectionPolicy;
/* 213 */     this.screenScalePolicy = this.view.screenScalePolicy;
/* 214 */     this.windowResizePolicy = this.view.windowResizePolicy;
/* 215 */     this.windowMovementPolicy = this.view.windowMovementPolicy;
/* 216 */     this.windowEyepointPolicy = this.view.windowEyepointPolicy;
/* 217 */     this.monoscopicViewPolicy = this.view.monoscopicViewPolicy;
/*     */     
/* 219 */     this.fieldOfView = this.view.fieldOfView;
/* 220 */     this.screenScale = this.view.screenScale;
/*     */     
/* 222 */     this.frontClipDistance = this.view.frontClipDistance;
/* 223 */     this.backClipDistance = this.view.backClipDistance;
/* 224 */     this.frontClipPolicy = this.view.frontClipPolicy;
/* 225 */     this.backClipPolicy = this.view.backClipPolicy;
/*     */     
/* 227 */     this.visibilityPolicy = this.view.visibilityPolicy;
/*     */     
/* 229 */     this.trackingEnable = this.view.trackingEnable;
/* 230 */     this.userHeadToVworldEnable = this.view.userHeadToVworldEnable;
/*     */     
/* 232 */     this.view.compatVpcToEc.getWithLock(this.compatVpcToEc);
/* 233 */     this.view.compatLeftProjection.getWithLock(this.compatLeftProjection);
/* 234 */     this.view.compatRightProjection.getWithLock(this.compatRightProjection);
/*     */ 
/*     */     
/* 237 */     ViewPlatform viewPlatform = this.view.getViewPlatform();
/*     */     
/* 239 */     if (viewPlatform == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     this.vpRetained = (ViewPlatformRetained)viewPlatform.retained;
/*     */     
/* 248 */     synchronized (this.vpRetained) {
/* 249 */       this.vcDirtyMask |= this.vpRetained.vprDirtyMask;
/* 250 */       this.vpRetained.vprDirtyMask = 0;
/* 251 */       this.viewAttachPolicy = this.vpRetained.viewAttachPolicy;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 256 */     PhysicalEnvironment physicalEnvironment = this.view.getPhysicalEnvironment();
/*     */     
/* 258 */     synchronized (physicalEnvironment) {
/* 259 */       this.vcDirtyMask |= physicalEnvironment.peDirtyMask;
/* 260 */       physicalEnvironment.peDirtyMask = 0;
/*     */       
/* 262 */       physicalEnvironment.coexistenceToTrackerBase.getWithLock(this.coexistenceToTrackerBase);
/* 263 */       this.trackingAvailable = physicalEnvironment.trackingAvailable;
/* 264 */       this.coexistenceCenterInPworldPolicy = physicalEnvironment.coexistenceCenterInPworldPolicy;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       this.doHeadTracking = (this.trackingEnable && this.trackingAvailable);
/*     */       
/* 271 */       if (this.doHeadTracking) {
/* 272 */         this.headIndex = physicalEnvironment.getHeadIndex();
/* 273 */         physicalEnvironment.getSensor(this.headIndex).getRead(this.headTrackerToTrackerBase);
/* 274 */         this.vcDirtyMask |= 0x800;
/*     */       } else {
/*     */         
/* 277 */         this.headTrackerToTrackerBase.setIdentity();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 282 */     PhysicalBody physicalBody = this.view.getPhysicalBody();
/*     */     
/* 284 */     synchronized (physicalBody) {
/* 285 */       this.vcDirtyMask |= physicalBody.pbDirtyMask;
/* 286 */       physicalBody.pbDirtyMask = 0;
/*     */       
/* 288 */       this.leftEyePosInHead.set(physicalBody.leftEyePosition);
/* 289 */       this.rightEyePosInHead.set(physicalBody.rightEyePosition);
/* 290 */       this.leftEarPosInHead.set(physicalBody.leftEarPosition);
/* 291 */       this.rightEarPosInHead.set(physicalBody.rightEarPosition);
/*     */       
/* 293 */       this.nominalEyeHeightFromGround = physicalBody.nominalEyeHeightFromGround;
/* 294 */       this.nominalEyeOffsetFromNominalScreen = physicalBody.nominalEyeOffsetFromNominalScreen;
/*     */     } 
/*     */ 
/*     */     
/* 298 */     physicalBody.headToHeadTracker.getWithLock(this.headToHeadTracker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void computeDerivedData() {
/* 306 */     if (this.doHeadTracking) {
/* 307 */       this.trackerBaseToHeadTracker.invert(this.headTrackerToTrackerBase);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 312 */       this.trackerBaseToHeadTracker.setIdentity();
/*     */     } 
/*     */ 
/*     */     
/* 316 */     this.userHeadToVworld.setIdentity();
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
/* 329 */   boolean getDoHeadTracking() { return this.doHeadTracking; } ViewCache(View paramView) { this.leftEyePosInHead = new Point3d(); this.rightEyePosInHead = new Point3d(); this.leftEarPosInHead = new Point3d(); this.rightEarPosInHead = new Point3d(); this.headToHeadTracker = new Transform3D(); this.coexistenceToTrackerBase = new Transform3D(); this.headTrackerToTrackerBase = new Transform3D(); this.trackerBaseToHeadTracker = new Transform3D(); this.leftManualEyeInCoexistence = new Point3d();
/*     */     this.rightManualEyeInCoexistence = new Point3d();
/*     */     this.compatVpcToEc = new Transform3D();
/*     */     this.compatLeftProjection = new Transform3D();
/*     */     this.compatRightProjection = new Transform3D();
/*     */     this.vcDirtyMask = 0;
/*     */     this.userHeadToVworld = new Transform3D();
/* 336 */     this.view = paramView; }
/*     */ 
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\ViewCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */