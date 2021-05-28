/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import javax.vecmath.Matrix4d;
/*      */ import javax.vecmath.Point2d;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point4d;
/*      */ import javax.vecmath.SingularMatrixException;
/*      */ import javax.vecmath.Vector3d;
/*      */ import javax.vecmath.Vector4d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class CanvasViewCache
/*      */ {
/*   29 */   private static Object debugLock = new Object();
/*      */ 
/*      */   
/*      */   private Canvas3D canvas;
/*      */ 
/*      */   
/*      */   int cvcDirtyMask;
/*      */ 
/*      */   
/*      */   private ScreenViewCache screenViewCache;
/*      */ 
/*      */   
/*      */   private ViewCache viewCache;
/*      */ 
/*      */   
/*      */   private int awtCanvasX;
/*      */ 
/*      */   
/*      */   private int awtCanvasY;
/*      */ 
/*      */   
/*      */   private int awtCanvasWidth;
/*      */ 
/*      */   
/*      */   private int awtCanvasHeight;
/*      */ 
/*      */   
/*      */   private RenderBin renderBin;
/*      */ 
/*      */   
/*      */   private boolean useStereo;
/*      */ 
/*      */   
/*      */   private int monoscopicViewPolicy;
/*      */ 
/*      */   
/*      */   private Point3d leftManualEyeInImagePlate;
/*      */ 
/*      */   
/*      */   private Point3d rightManualEyeInImagePlate;
/*      */ 
/*      */   
/*      */   double physicalScreenWidth;
/*      */   
/*      */   double physicalScreenHeight;
/*      */   
/*      */   int screenWidth;
/*      */   
/*      */   int screenHeight;
/*      */   
/*      */   double metersPerPixelX;
/*      */   
/*      */   double metersPerPixelY;
/*      */   
/*      */   private int canvasX;
/*      */   
/*      */   private int canvasY;
/*      */   
/*      */   private int canvasWidth;
/*      */   
/*      */   private int canvasHeight;
/*      */   
/*      */   private int effectiveMonoscopicViewPolicy;
/*      */   
/*      */   private Transform3D leftProjection;
/*      */   
/*      */   private Transform3D rightProjection;
/*      */   
/*      */   private Transform3D infLeftProjection;
/*      */   
/*      */   private Transform3D infRightProjection;
/*      */   
/*      */   private Transform3D leftVpcToEc;
/*      */   
/*      */   private Transform3D rightVpcToEc;
/*      */   
/*      */   private Transform3D infLeftVpcToEc;
/*      */   
/*      */   private Transform3D infRightVpcToEc;
/*      */   
/*      */   private Transform3D leftEcToVpc;
/*      */   
/*      */   private Transform3D rightEcToVpc;
/*      */   
/*      */   private Transform3D infLeftEcToVpc;
/*      */   
/*      */   private Transform3D infRightEcToVpc;
/*      */   
/*      */   private Vector4d[] leftFrustumPlanes;
/*      */   
/*      */   private Vector4d[] rightFrustumPlanes;
/*      */   
/*      */   private Point4d[] leftFrustumPoints;
/*      */   
/*      */   private Point4d[] rightFrustumPoints;
/*      */   
/*      */   private Transform3D headTrackerToLeftImagePlate;
/*      */   
/*      */   private Transform3D headTrackerToRightImagePlate;
/*      */   
/*      */   private Point3d leftTrackedEyeInImagePlate;
/*      */   
/*      */   private Point3d rightTrackedEyeInImagePlate;
/*      */   
/*      */   private Point3d leftEyeInImagePlate;
/*      */   
/*      */   private Point3d rightEyeInImagePlate;
/*      */   
/*      */   private Point3d centerEyeInImagePlate;
/*      */   
/*      */   private double nominalEyeOffset;
/*      */   
/*      */   private double physicalWindowXLeft;
/*      */   
/*      */   private double physicalWindowYBottom;
/*      */   
/*      */   private double physicalWindowXRight;
/*      */   
/*      */   private double physicalWindowYTop;
/*      */   
/*      */   private double physicalWindowWidth;
/*      */   
/*      */   private double physicalWindowHeight;
/*      */   
/*      */   private Point3d physicalWindowCenter;
/*      */   
/*      */   private double screenScale;
/*      */   
/*      */   private double windowScale;
/*      */   
/*      */   private double viewPlatformScale;
/*      */   
/*      */   private Transform3D leftCcToVworld;
/*      */   
/*      */   private Transform3D rightCcToVworld;
/*      */   
/*      */   private Transform3D coexistenceToLeftPlate;
/*      */   
/*      */   private Transform3D coexistenceToRightPlate;
/*      */   
/*      */   private Transform3D vpcToCoexistence;
/*      */   
/*      */   private Transform3D vpcToLeftPlate;
/*      */   
/*      */   private Transform3D vpcToRightPlate;
/*      */   
/*      */   private Transform3D leftPlateToVpc;
/*      */   
/*      */   private Transform3D rightPlateToVpc;
/*      */   
/*      */   private Transform3D vworldToLeftPlate;
/*      */   
/*      */   private Transform3D lastVworldToLeftPlate;
/*      */   
/*      */   private Transform3D vworldToRightPlate;
/*      */   
/*      */   private Transform3D leftPlateToVworld;
/*      */   
/*      */   private Transform3D rightPlateToVworld;
/*      */   
/*      */   private Transform3D headToLeftImagePlate;
/*      */   
/*      */   private Transform3D headToRightImagePlate;
/*      */   
/*      */   private Transform3D vworldToTrackerBase;
/*      */   
/*      */   private Transform3D tempTrans;
/*      */   
/*      */   private Transform3D headToVworld;
/*      */   
/*      */   private Vector3d coexistenceCenter;
/*      */   
/*      */   private double vworldToCoexistenceScale;
/*      */   
/*      */   private double infVworldToCoexistenceScale;
/*      */   
/*      */   private Transform3D tMat1;
/*      */   
/*      */   private Transform3D tMat2;
/*      */   
/*      */   private Vector3d tVec1;
/*      */   
/*      */   private Vector3d tVec2;
/*      */   
/*      */   private Vector3d tVec3;
/*      */   
/*      */   private Point3d tPnt1;
/*      */   
/*      */   private Point3d tPnt2;
/*      */   
/*      */   private Matrix4d tMatrix;
/*      */   
/*      */   private Transform3D vworldToVpc;
/*      */   
/*      */   private Transform3D vpcToVworld;
/*      */   
/*      */   private Transform3D infVworldToVpc;
/*      */   
/*      */   private boolean lastDoInfinite;
/*      */   
/*      */   private boolean updateLastTime;
/*      */ 
/*      */   
/*      */   void getCanvasPositionAndSize() {
/*  233 */     this.awtCanvasX = this.canvas.newPosition.x;
/*  234 */     this.awtCanvasY = this.canvas.newPosition.y;
/*  235 */     this.awtCanvasWidth = this.canvas.newSize.width;
/*  236 */     this.awtCanvasHeight = this.canvas.newSize.height;
/*      */ 
/*      */ 
/*      */     
/*  240 */     if (this.awtCanvasWidth <= 0 || this.awtCanvasHeight <= 0) {
/*  241 */       this.awtCanvasWidth = 1;
/*  242 */       this.awtCanvasHeight = 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void computefrustumBBox(BoundingBox paramBoundingBox) {
/*      */     byte b;
/*  255 */     for (b = 0; b < this.leftFrustumPoints.length; b++) {
/*  256 */       if (paramBoundingBox.lower.x > (this.leftFrustumPoints[b]).x)
/*  257 */         paramBoundingBox.lower.x = (this.leftFrustumPoints[b]).x; 
/*  258 */       if (paramBoundingBox.lower.y > (this.leftFrustumPoints[b]).y)
/*  259 */         paramBoundingBox.lower.y = (this.leftFrustumPoints[b]).y; 
/*  260 */       if (paramBoundingBox.lower.z > (this.leftFrustumPoints[b]).z) {
/*  261 */         paramBoundingBox.lower.z = (this.leftFrustumPoints[b]).z;
/*      */       }
/*  263 */       if (paramBoundingBox.upper.x < (this.leftFrustumPoints[b]).x)
/*  264 */         paramBoundingBox.upper.x = (this.leftFrustumPoints[b]).x; 
/*  265 */       if (paramBoundingBox.upper.y < (this.leftFrustumPoints[b]).y)
/*  266 */         paramBoundingBox.upper.y = (this.leftFrustumPoints[b]).y; 
/*  267 */       if (paramBoundingBox.upper.z < (this.leftFrustumPoints[b]).z) {
/*  268 */         paramBoundingBox.upper.z = (this.leftFrustumPoints[b]).z;
/*      */       }
/*      */     } 
/*  271 */     if (this.useStereo)
/*      */     {
/*  273 */       for (b = 0; b < this.rightFrustumPoints.length; b++) {
/*  274 */         if (paramBoundingBox.lower.x > (this.rightFrustumPoints[b]).x)
/*  275 */           paramBoundingBox.lower.x = (this.rightFrustumPoints[b]).x; 
/*  276 */         if (paramBoundingBox.lower.y > (this.rightFrustumPoints[b]).y)
/*  277 */           paramBoundingBox.lower.y = (this.rightFrustumPoints[b]).y; 
/*  278 */         if (paramBoundingBox.lower.z > (this.rightFrustumPoints[b]).z) {
/*  279 */           paramBoundingBox.lower.z = (this.rightFrustumPoints[b]).z;
/*      */         }
/*  281 */         if (paramBoundingBox.upper.x < (this.rightFrustumPoints[b]).x)
/*  282 */           paramBoundingBox.upper.x = (this.rightFrustumPoints[b]).x; 
/*  283 */         if (paramBoundingBox.upper.y < (this.rightFrustumPoints[b]).y)
/*  284 */           paramBoundingBox.upper.y = (this.rightFrustumPoints[b]).y; 
/*  285 */         if (paramBoundingBox.upper.z < (this.rightFrustumPoints[b]).z) {
/*  286 */           paramBoundingBox.upper.z = (this.rightFrustumPoints[b]).z;
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copyComputedCanvasViewCache(CanvasViewCache paramCanvasViewCache, boolean paramBoolean) {
/*  307 */     paramCanvasViewCache.useStereo = this.useStereo;
/*  308 */     paramCanvasViewCache.canvasWidth = this.canvasWidth;
/*  309 */     paramCanvasViewCache.canvasHeight = this.canvasHeight;
/*  310 */     paramCanvasViewCache.leftProjection.set(this.leftProjection);
/*  311 */     paramCanvasViewCache.rightProjection.set(this.rightProjection);
/*  312 */     paramCanvasViewCache.leftVpcToEc.set(this.leftVpcToEc);
/*  313 */     paramCanvasViewCache.rightVpcToEc.set(this.rightVpcToEc);
/*      */     
/*  315 */     paramCanvasViewCache.vpcToVworld = this.vpcToVworld;
/*  316 */     paramCanvasViewCache.vworldToVpc.set(this.vworldToVpc);
/*      */     
/*  318 */     if (paramBoolean) {
/*  319 */       paramCanvasViewCache.infLeftProjection.set(this.infLeftProjection);
/*  320 */       paramCanvasViewCache.infRightProjection.set(this.infRightProjection);
/*  321 */       paramCanvasViewCache.infLeftVpcToEc.set(this.infLeftVpcToEc);
/*  322 */       paramCanvasViewCache.infRightVpcToEc.set(this.infRightVpcToEc);
/*  323 */       paramCanvasViewCache.infVworldToVpc.set(this.infVworldToVpc);
/*      */     } 
/*      */     
/*  326 */     for (byte b = 0; b < this.leftFrustumPlanes.length; b++) {
/*  327 */       (paramCanvasViewCache.leftFrustumPlanes[b]).x = (this.leftFrustumPlanes[b]).x;
/*  328 */       (paramCanvasViewCache.leftFrustumPlanes[b]).y = (this.leftFrustumPlanes[b]).y;
/*  329 */       (paramCanvasViewCache.leftFrustumPlanes[b]).z = (this.leftFrustumPlanes[b]).z;
/*  330 */       (paramCanvasViewCache.leftFrustumPlanes[b]).w = (this.leftFrustumPlanes[b]).w;
/*      */       
/*  332 */       (paramCanvasViewCache.rightFrustumPlanes[b]).x = (this.rightFrustumPlanes[b]).x;
/*  333 */       (paramCanvasViewCache.rightFrustumPlanes[b]).y = (this.rightFrustumPlanes[b]).y;
/*  334 */       (paramCanvasViewCache.rightFrustumPlanes[b]).z = (this.rightFrustumPlanes[b]).z;
/*  335 */       (paramCanvasViewCache.rightFrustumPlanes[b]).w = (this.rightFrustumPlanes[b]).w;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void snapshot(boolean paramBoolean) {
/*  348 */     boolean bool = paramBoolean ? 1 : 0;
/*      */ 
/*      */     
/*  351 */     synchronized (this.canvas.dirtyMaskLock) {
/*      */       
/*  353 */       this.cvcDirtyMask = this.canvas.cvDirtyMask[bool];
/*  354 */       this.canvas.cvDirtyMask[bool] = 0;
/*      */     } 
/*      */     
/*  357 */     this.useStereo = this.canvas.useStereo;
/*  358 */     this.monoscopicViewPolicy = this.canvas.monoscopicViewPolicy;
/*  359 */     this.leftManualEyeInImagePlate.set(this.canvas.leftManualEyeInImagePlate);
/*  360 */     this.rightManualEyeInImagePlate.set(this.canvas.rightManualEyeInImagePlate);
/*      */     
/*  362 */     if ((this.cvcDirtyMask & 0x8) != 0) {
/*  363 */       getCanvasPositionAndSize();
/*      */     }
/*      */     
/*  366 */     this.renderBin = this.canvas.view.renderBin;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  384 */   void computeDerivedData(boolean paramBoolean1, CanvasViewCache paramCanvasViewCache, BoundingBox paramBoundingBox, boolean paramBoolean2) { doComputeDerivedData(paramBoolean1, paramCanvasViewCache, paramBoundingBox, paramBoolean2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doComputeDerivedData(boolean paramBoolean1, CanvasViewCache paramCanvasViewCache, BoundingBox paramBoundingBox, boolean paramBoolean2) {
/*      */     int i;
/*  398 */     boolean bool1 = (paramBoundingBox != null) ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  403 */     synchronized (this.screenViewCache) {
/*  404 */       i = this.screenViewCache.scrvcDirtyMask[bool1];
/*      */ 
/*      */ 
/*      */       
/*  408 */       if (this.canvas.offScreen) {
/*  409 */         this.screenViewCache.scrvcDirtyMask[bool1] = 0;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  427 */     if (this.viewCache.vpRetained == null) {
/*  428 */       System.err.println("CanvasViewCache : Error! viewCache.vpRetained is null");
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  435 */     boolean bool2 = (this.viewCache.vpRetained.vprDirtyMask == 0) ? 1 : 0;
/*      */ 
/*      */     
/*  438 */     if (!this.canvas.manualRendering && bool2 && this.cvcDirtyMask == 0 && i == 0 && this.viewCache.vcDirtyMask == 0 && (!this.updateLastTime || paramBoolean2 == this.lastDoInfinite)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  444 */       if (paramBoundingBox != null) {
/*  445 */         computefrustumBBox(paramBoundingBox);
/*      */       }
/*      */       
/*  448 */       if (paramCanvasViewCache != null) {
/*  449 */         copyComputedCanvasViewCache(paramCanvasViewCache, paramBoolean2);
/*      */       }
/*  451 */       this.lastDoInfinite = paramBoolean2;
/*  452 */       this.updateLastTime = false;
/*      */       
/*      */       return;
/*      */     } 
/*  456 */     this.lastDoInfinite = paramBoolean2;
/*  457 */     this.updateLastTime = true;
/*      */     
/*  459 */     if (paramBoolean1) {
/*  460 */       this.vpcToVworld.set(this.viewCache.vpRetained.getCurrentLocalToVworld(null));
/*      */     } else {
/*      */       
/*  463 */       this.vpcToVworld.set(this.viewCache.vpRetained.getLastLocalToVworld(null));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  469 */       this.vworldToVpc.invert(this.vpcToVworld);
/*      */     }
/*  471 */     catch (SingularMatrixException singularMatrixException) {
/*  472 */       this.vworldToVpc.setIdentity();
/*      */     } 
/*      */     
/*  475 */     if (paramBoolean2) {
/*  476 */       this.vworldToVpc.getRotation(this.infVworldToVpc);
/*      */     }
/*      */ 
/*      */     
/*  480 */     if (this.monoscopicViewPolicy == 2) {
/*  481 */       this.effectiveMonoscopicViewPolicy = this.viewCache.monoscopicViewPolicy;
/*      */     } else {
/*  483 */       this.effectiveMonoscopicViewPolicy = this.monoscopicViewPolicy;
/*      */     } 
/*      */     
/*  486 */     computeCanvasInfo();
/*      */ 
/*      */     
/*  489 */     computeCoexistenceCenter();
/*      */ 
/*      */     
/*  492 */     cacheEyePosition();
/*      */ 
/*      */     
/*  495 */     computeVpcToCoexistence();
/*  496 */     computeCoexistenceToPlate();
/*      */ 
/*      */     
/*  499 */     computeView(paramBoolean2);
/*      */ 
/*      */     
/*  502 */     computePlateToVworld();
/*      */     
/*  504 */     if (!paramBoolean1)
/*      */     {
/*  506 */       this.lastVworldToLeftPlate.set(this.vworldToLeftPlate);
/*      */     }
/*  508 */     computeHeadToVworld();
/*      */     
/*  510 */     if (paramBoundingBox != null) {
/*  511 */       computefrustumBBox(paramBoundingBox);
/*      */     }
/*      */     
/*  514 */     assert paramCanvasViewCache == null;
/*  515 */     if (paramCanvasViewCache != null) {
/*  516 */       copyComputedCanvasViewCache(paramCanvasViewCache, paramBoolean2);
/*      */     }
/*  518 */     this.canvas.canvasDirty |= 0x8000;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeCanvasInfo() {
/*  548 */     this.physicalScreenWidth = this.screenViewCache.physicalScreenWidth;
/*  549 */     this.physicalScreenHeight = this.screenViewCache.physicalScreenHeight;
/*      */     
/*  551 */     this.screenWidth = this.screenViewCache.screenWidth;
/*  552 */     this.screenHeight = this.screenViewCache.screenHeight;
/*      */     
/*  554 */     this.metersPerPixelX = this.screenViewCache.metersPerPixelX;
/*  555 */     this.metersPerPixelY = this.screenViewCache.metersPerPixelY;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  561 */     Rectangle rectangle = this.canvas.graphicsConfiguration.getBounds();
/*  562 */     this.canvasX = this.awtCanvasX - rectangle.x;
/*  563 */     this.canvasY = this.awtCanvasY - rectangle.y;
/*      */ 
/*      */     
/*  566 */     this.canvasWidth = this.awtCanvasWidth;
/*  567 */     this.canvasHeight = this.awtCanvasHeight;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  573 */     this.physicalWindowWidth = this.canvasWidth * this.metersPerPixelX;
/*  574 */     this.physicalWindowHeight = this.canvasHeight * this.metersPerPixelY;
/*      */ 
/*      */     
/*  577 */     this.physicalWindowXLeft = this.metersPerPixelX * this.canvasX;
/*      */     
/*  579 */     this.physicalWindowYBottom = this.metersPerPixelY * (this.screenHeight - this.canvasHeight - this.canvasY);
/*      */ 
/*      */     
/*  582 */     this.physicalWindowXRight = this.physicalWindowXLeft + this.physicalWindowWidth;
/*  583 */     this.physicalWindowYTop = this.physicalWindowYBottom + this.physicalWindowHeight;
/*      */ 
/*      */     
/*  586 */     this.physicalWindowCenter.x = this.physicalWindowXLeft + this.physicalWindowWidth / 2.0D;
/*      */     
/*  588 */     this.physicalWindowCenter.y = this.physicalWindowYBottom + this.physicalWindowHeight / 2.0D;
/*      */     
/*  590 */     this.physicalWindowCenter.z = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  611 */     computeViewPlatformScale();
/*      */     
/*  613 */     if (!this.viewCache.compatibilityModeEnable && this.viewCache.viewPolicy == 1)
/*      */     {
/*  615 */       if (!this.useStereo) {
/*  616 */         switch (this.effectiveMonoscopicViewPolicy) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*  625 */             this.headTrackerToLeftImagePlate.set(this.screenViewCache.headTrackerToLeftImagePlate);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 1:
/*  630 */             this.headTrackerToLeftImagePlate.set(this.screenViewCache.headTrackerToRightImagePlate);
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } else {
/*  636 */         this.headTrackerToLeftImagePlate.set(this.screenViewCache.headTrackerToLeftImagePlate);
/*      */ 
/*      */         
/*  639 */         this.headTrackerToRightImagePlate.set(this.screenViewCache.headTrackerToRightImagePlate);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeViewPlatformScale() {
/*  649 */     this.windowScale = this.screenScale = 1.0D;
/*      */     
/*  651 */     if (!this.viewCache.compatibilityModeEnable) {
/*  652 */       switch (this.viewCache.screenScalePolicy) {
/*      */         case 0:
/*  654 */           this.screenScale = this.physicalScreenWidth / 2.0D;
/*      */           break;
/*      */         case 1:
/*  657 */           this.screenScale = this.viewCache.screenScale;
/*      */           break;
/*      */       } 
/*      */       
/*  661 */       if (this.viewCache.windowResizePolicy == 1) {
/*  662 */         this.windowScale = this.physicalWindowWidth / this.physicalScreenWidth;
/*      */       }
/*      */     } 
/*      */     
/*  666 */     this.viewPlatformScale = this.windowScale * this.screenScale;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cacheEyePosFixedField() {
/*  683 */     this.leftEyeInImagePlate.y = this.physicalWindowCenter.y;
/*      */ 
/*      */ 
/*      */     
/*  687 */     if (!this.useStereo) {
/*  688 */       switch (this.effectiveMonoscopicViewPolicy) {
/*      */         case 2:
/*  690 */           this.leftEyeInImagePlate.x = this.physicalWindowCenter.x;
/*      */           break;
/*      */         
/*      */         case 0:
/*  694 */           this.physicalWindowCenter.x += this.viewCache.leftEyePosInHead.x;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 1:
/*  699 */           this.physicalWindowCenter.x += this.viewCache.rightEyePosInHead.x;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  705 */       this.rightEyeInImagePlate.x = this.leftEyeInImagePlate.x;
/*      */     } else {
/*      */       
/*  708 */       this.physicalWindowCenter.x += this.viewCache.leftEyePosInHead.x;
/*      */ 
/*      */       
/*  711 */       this.physicalWindowCenter.x += this.viewCache.rightEyePosInHead.x;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  719 */     this.leftEyeInImagePlate.z = this.physicalWindowWidth / 2.0D * Math.tan(this.viewCache.fieldOfView / 2.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  726 */     if (this.viewCache.view.soundScheduler != null) {
/*  727 */       this.viewCache.view.soundScheduler.setListenerFlag(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cacheEyePosWindowRelative() {
/*  741 */     this.leftEyeInImagePlate.y = this.physicalWindowCenter.y;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  746 */     this.leftEyeInImagePlate.z = this.leftManualEyeInImagePlate.z;
/*      */ 
/*      */ 
/*      */     
/*  750 */     if (!this.useStereo) {
/*      */       
/*  752 */       switch (this.effectiveMonoscopicViewPolicy) {
/*      */         
/*      */         case 2:
/*  755 */           this.leftEyeInImagePlate.x = this.physicalWindowCenter.x;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 0:
/*  760 */           this.physicalWindowCenter.x += this.viewCache.leftEyePosInHead.x;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 1:
/*  766 */           this.physicalWindowCenter.x += this.viewCache.rightEyePosInHead.x;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  774 */       this.rightEyeInImagePlate.x = this.leftEyeInImagePlate.x;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  780 */       this.physicalWindowCenter.x += this.viewCache.leftEyePosInHead.x;
/*      */ 
/*      */ 
/*      */       
/*  784 */       this.physicalWindowCenter.x += this.viewCache.rightEyePosInHead.x;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  789 */       this.rightEyeInImagePlate.z = this.rightManualEyeInImagePlate.z;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  795 */     if (this.viewCache.view.soundScheduler != null) {
/*  796 */       this.viewCache.view.soundScheduler.setListenerFlag(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cacheEyePosScreenRelative(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*  808 */     if (!this.useStereo) {
/*  809 */       switch (this.effectiveMonoscopicViewPolicy) {
/*      */         
/*      */         case 2:
/*  812 */           this.leftEyeInImagePlate.x = (paramPoint3d1.x + paramPoint3d2.x) / 2.0D;
/*  813 */           this.leftEyeInImagePlate.y = (paramPoint3d1.y + paramPoint3d2.y) / 2.0D;
/*  814 */           this.leftEyeInImagePlate.z = (paramPoint3d1.z + paramPoint3d2.z) / 2.0D;
/*      */           break;
/*      */         
/*      */         case 0:
/*  818 */           this.leftEyeInImagePlate.set(paramPoint3d1);
/*      */           break;
/*      */         
/*      */         case 1:
/*  822 */           this.leftEyeInImagePlate.set(paramPoint3d2);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  828 */       this.rightEyeInImagePlate.set(this.leftEyeInImagePlate);
/*      */     } else {
/*      */       
/*  831 */       this.leftEyeInImagePlate.set(paramPoint3d1);
/*  832 */       this.rightEyeInImagePlate.set(paramPoint3d2);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  837 */     if (this.viewCache.view.soundScheduler != null) {
/*  838 */       this.viewCache.view.soundScheduler.setListenerFlag(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void cacheEyePosCoexistenceRelative(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*  845 */     this.tPnt1.set(paramPoint3d1);
/*  846 */     this.viewCache.coexistenceToTrackerBase.transform(this.tPnt1);
/*  847 */     this.screenViewCache.trackerBaseToImagePlate.transform(this.tPnt1);
/*  848 */     this.tPnt1.add(this.coexistenceCenter);
/*      */     
/*  850 */     this.tPnt2.set(paramPoint3d2);
/*  851 */     this.viewCache.coexistenceToTrackerBase.transform(this.tPnt2);
/*  852 */     this.screenViewCache.trackerBaseToImagePlate.transform(this.tPnt2);
/*  853 */     this.tPnt2.add(this.coexistenceCenter);
/*      */     
/*  855 */     cacheEyePosScreenRelative(this.tPnt1, this.tPnt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeTrackedEyePosition() {
/*  873 */     if (this.viewCache.viewPolicy != 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  879 */       this.headToLeftImagePlate.set(this.coexistenceCenter);
/*  880 */       this.headToLeftImagePlate.mul(this.screenViewCache.trackerBaseToImagePlate);
/*  881 */       this.headToLeftImagePlate.mul(this.viewCache.headTrackerToTrackerBase);
/*  882 */       this.headToLeftImagePlate.mul(this.viewCache.headToHeadTracker);
/*      */       
/*  884 */       this.headToLeftImagePlate.transform(this.viewCache.leftEyePosInHead, this.leftTrackedEyeInImagePlate);
/*      */ 
/*      */       
/*  887 */       this.headToLeftImagePlate.transform(this.viewCache.rightEyePosInHead, this.rightTrackedEyeInImagePlate);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  896 */       this.headToLeftImagePlate.mul(this.headTrackerToLeftImagePlate, this.viewCache.headToHeadTracker);
/*      */ 
/*      */       
/*  899 */       this.headToLeftImagePlate.transform(this.viewCache.leftEyePosInHead, this.leftTrackedEyeInImagePlate);
/*      */ 
/*      */       
/*  902 */       if (this.useStereo) {
/*  903 */         this.headToRightImagePlate.mul(this.headTrackerToRightImagePlate, this.viewCache.headToHeadTracker);
/*      */ 
/*      */         
/*  906 */         this.headToRightImagePlate.transform(this.viewCache.rightEyePosInHead, this.rightTrackedEyeInImagePlate);
/*      */       }
/*      */       else {
/*      */         
/*  910 */         this.headToLeftImagePlate.transform(this.viewCache.rightEyePosInHead, this.rightTrackedEyeInImagePlate);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cacheEyePosition() {
/*  930 */     if (this.viewCache.compatibilityModeEnable) {
/*      */       
/*  932 */       cacheEyePosScreenRelative(this.leftManualEyeInImagePlate, this.rightManualEyeInImagePlate);
/*      */     
/*      */     }
/*  935 */     else if (this.viewCache.getDoHeadTracking()) {
/*  936 */       computeTrackedEyePosition();
/*  937 */       cacheEyePosScreenRelative(this.leftTrackedEyeInImagePlate, this.rightTrackedEyeInImagePlate);
/*      */     }
/*      */     else {
/*      */       
/*  941 */       switch (this.viewCache.windowEyepointPolicy) {
/*      */         
/*      */         case 2:
/*  944 */           cacheEyePosFixedField();
/*      */           break;
/*      */         
/*      */         case 1:
/*  948 */           cacheEyePosWindowRelative();
/*      */           break;
/*      */         
/*      */         case 0:
/*  952 */           cacheEyePosScreenRelative(this.leftManualEyeInImagePlate, this.rightManualEyeInImagePlate);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  957 */           cacheEyePosCoexistenceRelative(this.viewCache.leftManualEyeInCoexistence, this.viewCache.rightManualEyeInCoexistence);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  964 */     this.centerEyeInImagePlate.add(this.leftEyeInImagePlate, this.rightEyeInImagePlate);
/*  965 */     this.centerEyeInImagePlate.scale(0.5D);
/*      */ 
/*      */     
/*  968 */     if (this.viewCache.windowEyepointPolicy == 2) {
/*  969 */       this.nominalEyeOffset = this.centerEyeInImagePlate.z;
/*      */     } else {
/*  971 */       this.nominalEyeOffset = this.viewCache.nominalEyeOffsetFromNominalScreen;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computePlateToVworld() {
/*  987 */     if (this.viewCache.compatibilityModeEnable) {
/*      */       
/*  989 */       this.leftPlateToVworld.setIdentity();
/*  990 */       this.vworldToLeftPlate.setIdentity();
/*      */     } else {
/*      */       
/*      */       try {
/*  994 */         this.leftPlateToVpc.invert(this.vpcToLeftPlate);
/*      */       }
/*  996 */       catch (SingularMatrixException singularMatrixException) {
/*  997 */         this.leftPlateToVpc.setIdentity();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1004 */       this.leftPlateToVworld.mul(this.vpcToVworld, this.leftPlateToVpc);
/* 1005 */       this.vworldToLeftPlate.mul(this.vpcToLeftPlate, this.vworldToVpc);
/*      */       
/* 1007 */       if (this.useStereo) {
/*      */         try {
/* 1009 */           this.rightPlateToVpc.invert(this.vpcToRightPlate);
/*      */         }
/* 1011 */         catch (SingularMatrixException singularMatrixException) {
/* 1012 */           this.rightPlateToVpc.setIdentity();
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1019 */         this.rightPlateToVworld.mul(this.vpcToVworld, this.rightPlateToVpc);
/* 1020 */         this.vworldToRightPlate.mul(this.vpcToRightPlate, this.vworldToVpc);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1040 */     if (this.viewCache.view.soundScheduler != null) {
/* 1041 */       this.viewCache.view.soundScheduler.setListenerFlag(4);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeHeadToVworld() {
/* 1049 */     if (this.viewCache.compatibilityModeEnable) {
/*      */       
/* 1051 */       this.headToVworld.setIdentity();
/*      */     } else {
/*      */       
/* 1054 */       this.headToVworld.mul(this.leftPlateToVworld, this.headToLeftImagePlate);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1068 */     if (this.viewCache.view.soundScheduler != null) {
/* 1069 */       this.viewCache.view.soundScheduler.setListenerFlag(8);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void computeVpcToCoexistence() {
/* 1075 */     this.tMat1.set(this.viewPlatformScale);
/*      */ 
/*      */ 
/*      */     
/* 1079 */     if (this.viewCache.viewPolicy != 1) {
/* 1080 */       switch (this.viewCache.coexistenceCenterInPworldPolicy) {
/*      */         case 2:
/* 1082 */           switch (this.viewCache.viewAttachPolicy) {
/*      */             case 2:
/* 1084 */               this.tMat2.setIdentity();
/*      */               break;
/*      */             case 0:
/* 1087 */               this.tVec1.set(0.0D, 0.0D, this.nominalEyeOffset);
/* 1088 */               this.tMat2.set(this.tVec1);
/*      */               break;
/*      */             case 1:
/* 1091 */               this.tVec1.set(0.0D, -this.viewCache.nominalEyeHeightFromGround, this.nominalEyeOffset);
/*      */               
/* 1093 */               this.tMat2.set(this.tVec1);
/*      */               break;
/*      */           } 
/*      */           
/*      */           break;
/*      */         case 0:
/* 1099 */           switch (this.viewCache.viewAttachPolicy) {
/*      */             case 2:
/* 1101 */               this.tVec1.set(0.0D, 0.0D, -this.nominalEyeOffset);
/* 1102 */               this.tMat2.set(this.tVec1);
/*      */               break;
/*      */             case 0:
/* 1105 */               this.tMat2.setIdentity();
/*      */               break;
/*      */             case 1:
/* 1108 */               this.tVec1.set(0.0D, -this.viewCache.nominalEyeHeightFromGround, 0.0D);
/*      */               
/* 1110 */               this.tMat2.set(this.tVec1);
/*      */               break;
/*      */           } 
/*      */           break;
/*      */         case 1:
/* 1115 */           switch (this.viewCache.viewAttachPolicy) {
/*      */             case 2:
/* 1117 */               this.tVec1.set(0.0D, this.viewCache.nominalEyeHeightFromGround, -this.nominalEyeOffset);
/*      */               
/* 1119 */               this.tMat2.set(this.tVec1);
/*      */               break;
/*      */             case 0:
/* 1122 */               this.tVec1.set(0.0D, this.viewCache.nominalEyeHeightFromGround, 0.0D);
/*      */               
/* 1124 */               this.tMat2.set(this.tVec1);
/*      */               break;
/*      */             
/*      */             case 1:
/* 1128 */               this.tMat2.setIdentity();
/*      */               break;
/*      */           } 
/*      */           
/*      */           break;
/*      */       } 
/* 1134 */       this.vpcToCoexistence.mul(this.tMat2, this.tMat1);
/*      */     } else {
/*      */       
/* 1137 */       this.vpcToCoexistence.set(this.tMat1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeCoexistenceCenter() {
/* 1148 */     if (!this.viewCache.compatibilityModeEnable && this.viewCache.viewPolicy != 1 && this.viewCache.coexistenceCenteringEnable && this.viewCache.coexistenceCenterInPworldPolicy == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1160 */       if (this.viewCache.windowMovementPolicy == 0) {
/* 1161 */         this.coexistenceCenter.x = this.physicalScreenWidth / 2.0D;
/* 1162 */         this.coexistenceCenter.y = this.physicalScreenHeight / 2.0D;
/* 1163 */         this.coexistenceCenter.z = 0.0D;
/*      */       } else {
/*      */         
/* 1166 */         this.coexistenceCenter.x = this.physicalWindowCenter.x;
/* 1167 */         this.coexistenceCenter.y = this.physicalWindowCenter.y;
/* 1168 */         this.coexistenceCenter.z = 0.0D;
/*      */       } 
/*      */     } else {
/*      */       
/* 1172 */       this.coexistenceCenter.set(0.0D, 0.0D, 0.0D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeCoexistenceToPlate() {
/* 1183 */     if (this.viewCache.compatibilityModeEnable) {
/*      */       
/* 1185 */       this.coexistenceToLeftPlate.setIdentity();
/*      */       
/*      */       return;
/*      */     } 
/* 1189 */     if (this.viewCache.viewPolicy != 1) {
/* 1190 */       this.coexistenceToLeftPlate.set(this.coexistenceCenter);
/* 1191 */       this.coexistenceToLeftPlate.mul(this.screenViewCache.trackerBaseToImagePlate);
/* 1192 */       this.coexistenceToLeftPlate.mul(this.viewCache.coexistenceToTrackerBase);
/*      */       
/* 1194 */       if (this.useStereo) {
/* 1195 */         this.coexistenceToRightPlate.set(this.coexistenceToLeftPlate);
/*      */       }
/*      */     } else {
/*      */       
/* 1199 */       this.coexistenceToLeftPlate.mul(this.headTrackerToLeftImagePlate, this.viewCache.trackerBaseToHeadTracker);
/*      */       
/* 1201 */       this.coexistenceToLeftPlate.mul(this.viewCache.coexistenceToTrackerBase);
/*      */       
/* 1203 */       if (this.useStereo) {
/* 1204 */         this.coexistenceToRightPlate.mul(this.headTrackerToRightImagePlate, this.viewCache.trackerBaseToHeadTracker);
/*      */         
/* 1206 */         this.coexistenceToRightPlate.mul(this.viewCache.coexistenceToTrackerBase);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeView(boolean paramBoolean) {
/*      */     double d5, d4, d3, d2, d1;
/*      */     int i;
/* 1238 */     this.vworldToCoexistenceScale = this.vworldToVpc.getDistanceScale() * this.vpcToCoexistence.getDistanceScale();
/*      */     
/* 1240 */     if (paramBoolean) {
/* 1241 */       this.infVworldToCoexistenceScale = this.infVworldToVpc.getDistanceScale() * this.vpcToCoexistence.getDistanceScale();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1251 */     this.tempTrans.mul(this.viewCache.coexistenceToTrackerBase, this.vpcToCoexistence);
/* 1252 */     this.vworldToTrackerBase.mul(this.tempTrans, this.vworldToVpc);
/*      */ 
/*      */ 
/*      */     
/* 1256 */     if (this.viewCache.compatibilityModeEnable) {
/* 1257 */       this.leftProjection.set(this.viewCache.compatLeftProjection);
/* 1258 */       this.leftVpcToEc.set(this.viewCache.compatVpcToEc);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1268 */       computeFrustumPlanes(this.leftProjection, this.leftVpcToEc, this.leftFrustumPlanes, this.leftFrustumPoints, this.leftCcToVworld);
/*      */ 
/*      */ 
/*      */       
/* 1272 */       if (this.useStereo) {
/* 1273 */         this.rightProjection.set(this.viewCache.compatRightProjection);
/* 1274 */         this.rightVpcToEc.set(this.viewCache.compatVpcToEc);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1283 */         computeFrustumPlanes(this.rightProjection, this.rightVpcToEc, this.rightFrustumPlanes, this.rightFrustumPoints, this.rightCcToVworld);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1306 */     if (this.viewCache.frontClipPolicy == 2 || this.viewCache.frontClipPolicy == 0) {
/*      */       
/* 1308 */       d4 = this.vworldToCoexistenceScale;
/*      */     } else {
/*      */       
/* 1311 */       d4 = this.windowScale;
/*      */     } 
/*      */ 
/*      */     
/* 1315 */     if (this.viewCache.frontClipPolicy == 3 || this.viewCache.frontClipPolicy == 2) {
/*      */       
/* 1317 */       d1 = this.leftEyeInImagePlate.z + d4 * -this.viewCache.frontClipDistance;
/*      */       
/* 1319 */       d2 = this.rightEyeInImagePlate.z + d4 * -this.viewCache.frontClipDistance;
/*      */     }
/*      */     else {
/*      */       
/* 1323 */       d1 = d4 * -this.viewCache.frontClipDistance;
/* 1324 */       d2 = d4 * -this.viewCache.frontClipDistance;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1329 */     if (this.renderBin != null && this.renderBin.backClipActive) {
/* 1330 */       i = 2;
/* 1331 */       d5 = this.renderBin.backClipDistanceInVworld;
/*      */     } else {
/* 1333 */       i = this.viewCache.backClipPolicy;
/* 1334 */       d5 = this.viewCache.backClipDistance;
/*      */     } 
/*      */ 
/*      */     
/* 1338 */     if (i == 2 || i == 0) {
/*      */       
/* 1340 */       d4 = this.vworldToCoexistenceScale;
/*      */     } else {
/*      */       
/* 1343 */       d4 = this.windowScale;
/*      */     } 
/*      */ 
/*      */     
/* 1347 */     if (i == 3 || i == 2) {
/*      */ 
/*      */       
/* 1350 */       d3 = this.leftEyeInImagePlate.z + d4 * -d5;
/*      */     }
/*      */     else {
/*      */       
/* 1354 */       d3 = d4 * -d5;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1370 */     buildProjView(this.leftEyeInImagePlate, this.coexistenceToLeftPlate, this.vpcToLeftPlate, d1, d3, this.leftProjection, this.leftVpcToEc, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1379 */     computeFrustumPlanes(this.leftProjection, this.leftVpcToEc, this.leftFrustumPlanes, this.leftFrustumPoints, this.leftCcToVworld);
/*      */ 
/*      */ 
/*      */     
/* 1383 */     if (this.useStereo) {
/*      */ 
/*      */ 
/*      */       
/* 1387 */       buildProjView(this.rightEyeInImagePlate, this.coexistenceToRightPlate, this.vpcToRightPlate, d2, d3, this.rightProjection, this.rightVpcToEc, false);
/*      */ 
/*      */ 
/*      */       
/* 1391 */       computeFrustumPlanes(this.rightProjection, this.rightVpcToEc, this.rightFrustumPlanes, this.rightFrustumPoints, this.rightCcToVworld);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1399 */     if (paramBoolean) {
/*      */       
/* 1401 */       buildProjView(this.leftEyeInImagePlate, this.coexistenceToLeftPlate, this.vpcToLeftPlate, this.leftEyeInImagePlate.z - 0.05D, this.leftEyeInImagePlate.z - 1.5D, this.infLeftProjection, this.infLeftVpcToEc, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1406 */       if (this.useStereo) {
/* 1407 */         buildProjView(this.rightEyeInImagePlate, this.coexistenceToRightPlate, this.vpcToRightPlate, this.rightEyeInImagePlate.z - 0.05D, this.rightEyeInImagePlate.z - 1.5D, this.infRightProjection, this.infRightVpcToEc, true);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void analyzeProjection(Transform3D paramTransform3D, double paramDouble) {
/* 1444 */     if (this.viewCache.projectionPolicy == 0) {
/* 1445 */       System.err.println("PARALLEL_PROJECTION =");
/*      */     } else {
/* 1447 */       System.err.println("PERSPECTIVE_PROJECTION =");
/*      */     } 
/* 1449 */     System.err.println(paramTransform3D);
/*      */     
/* 1451 */     double d = (paramTransform3D.mat[0] * paramDouble + paramTransform3D.mat[3] - paramTransform3D.mat[15]) / (paramTransform3D.mat[14] - paramTransform3D.mat[2]);
/*      */ 
/*      */     
/* 1454 */     System.err.println("projection plane at z = " + d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void buildProjView(Point3d paramPoint3d, Transform3D paramTransform3D1, Transform3D paramTransform3D2, double paramDouble1, double paramDouble2, Transform3D paramTransform3D3, Transform3D paramTransform3D4, boolean paramBoolean) {
/* 1484 */     double d1 = this.physicalWindowXLeft, d2 = this.physicalWindowXRight;
/* 1485 */     double d3 = this.physicalWindowYBottom, d4 = this.physicalWindowYTop;
/*      */     
/* 1487 */     paramTransform3D3.setIdentity();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1527 */     if (this.viewCache.projectionPolicy == 0) {
/*      */       
/* 1529 */       double d5 = 1.0D / (d2 - d1);
/* 1530 */       double d6 = 1.0D / (d4 - d3);
/* 1531 */       double d7 = 1.0D / (paramDouble1 - paramDouble2);
/*      */       
/* 1533 */       paramTransform3D3.mat[0] = 2.0D * d5;
/* 1534 */       paramTransform3D3.mat[3] = -(d2 + d1) * d5;
/* 1535 */       paramTransform3D3.mat[5] = 2.0D * d6;
/* 1536 */       paramTransform3D3.mat[7] = -(d4 + d3) * d6;
/* 1537 */       paramTransform3D3.mat[10] = 2.0D * d7;
/* 1538 */       paramTransform3D3.mat[11] = -(paramDouble1 + paramDouble2) * d7;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1543 */       double d7 = 1.0D / (d2 - d1);
/* 1544 */       double d8 = 1.0D / (d4 - d3);
/* 1545 */       double d6 = 1.0D / (paramPoint3d.z - paramDouble2);
/* 1546 */       double d5 = paramPoint3d.z * d6;
/*      */       
/* 1548 */       paramTransform3D3.mat[0] = d5 * 2.0D * d7;
/* 1549 */       paramTransform3D3.mat[5] = d5 * 2.0D * d8;
/*      */       
/* 1551 */       paramTransform3D3.mat[2] = d6 * (d2 + d1 - 2.0D * paramPoint3d.x) * d7;
/* 1552 */       paramTransform3D3.mat[6] = d6 * (d4 + d3 - 2.0D * paramPoint3d.y) * d8;
/* 1553 */       paramTransform3D3.mat[10] = d6 * (paramDouble2 + paramDouble1 - 2.0D * paramPoint3d.z) / (paramDouble2 - paramDouble1);
/* 1554 */       paramTransform3D3.mat[14] = -d6;
/*      */       
/* 1556 */       paramTransform3D3.mat[3] = d5 * (-d2 - d1) * d7;
/* 1557 */       paramTransform3D3.mat[7] = d5 * (-d4 - d3) * d8;
/* 1558 */       paramTransform3D3.mat[11] = d6 * (paramDouble2 - paramPoint3d.z - paramDouble2 * (paramDouble2 + paramDouble1 - 2.0D * paramPoint3d.z) / (paramDouble2 - paramDouble1));
/* 1559 */       paramTransform3D3.mat[15] = d5;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1564 */     paramTransform3D3.setOrthoDirtyBit();
/*      */ 
/*      */     
/* 1567 */     this.tVec1.set(paramPoint3d.x, paramPoint3d.y, paramPoint3d.z);
/* 1568 */     this.tMat1.set(this.tVec1);
/* 1569 */     paramTransform3D3.mul(this.tMat1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1576 */     if (!paramBoolean) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1582 */       paramTransform3D2.mul(paramTransform3D1, this.vpcToCoexistence);
/*      */ 
/*      */       
/* 1585 */       this.tVec1.set(-paramPoint3d.x, -paramPoint3d.y, -paramPoint3d.z);
/* 1586 */       this.tMat1.set(this.tVec1);
/* 1587 */       paramTransform3D4.mul(this.tMat1, paramTransform3D2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1613 */       this.tVec1.set(-paramPoint3d.x, -paramPoint3d.y, -paramPoint3d.z);
/* 1614 */       this.tMat1.set(this.tVec1);
/* 1615 */       this.tMat1.mul(this.tMat1, paramTransform3D2);
/* 1616 */       this.tMat1.getRotation(paramTransform3D4);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeFrustumPlanes(Transform3D paramTransform3D1, Transform3D paramTransform3D2, Vector4d[] paramArrayOfVector4d, Point4d[] paramArrayOfPoint4d, Transform3D paramTransform3D3) {
/* 1635 */     this.tMat2.mul(paramTransform3D1, paramTransform3D2);
/* 1636 */     paramTransform3D3.mul(this.tMat2, this.vworldToVpc);
/*      */     
/*      */     try {
/* 1639 */       paramTransform3D3.invert();
/*      */     }
/* 1641 */     catch (SingularMatrixException singularMatrixException) {
/* 1642 */       paramTransform3D3.setIdentity();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1657 */     paramArrayOfPoint4d[0].set(-1.0D, -1.0D, 1.0D, 1.0D);
/* 1658 */     paramArrayOfPoint4d[1].set(-1.0D, 1.0D, 1.0D, 1.0D);
/* 1659 */     paramArrayOfPoint4d[2].set(1.0D, 1.0D, 1.0D, 1.0D);
/* 1660 */     paramArrayOfPoint4d[3].set(1.0D, -1.0D, 1.0D, 1.0D);
/* 1661 */     paramArrayOfPoint4d[4].set(-1.0D, -1.0D, -1.0D, 1.0D);
/* 1662 */     paramArrayOfPoint4d[5].set(-1.0D, 1.0D, -1.0D, 1.0D);
/* 1663 */     paramArrayOfPoint4d[6].set(1.0D, 1.0D, -1.0D, 1.0D);
/* 1664 */     paramArrayOfPoint4d[7].set(1.0D, -1.0D, -1.0D, 1.0D);
/*      */     
/* 1666 */     paramTransform3D3.get(this.tMatrix);
/*      */     
/* 1668 */     for (byte b = 0; b < paramArrayOfPoint4d.length; b++) {
/* 1669 */       this.tMatrix.transform(paramArrayOfPoint4d[b]);
/* 1670 */       double d = 1.0D / (paramArrayOfPoint4d[b]).w;
/* 1671 */       (paramArrayOfPoint4d[b]).x *= d;
/* 1672 */       (paramArrayOfPoint4d[b]).y *= d;
/* 1673 */       (paramArrayOfPoint4d[b]).z *= d;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1678 */     computePlaneEq(paramArrayOfPoint4d[0], paramArrayOfPoint4d[4], paramArrayOfPoint4d[5], paramArrayOfPoint4d[1], paramArrayOfVector4d[0]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1683 */     computePlaneEq(paramArrayOfPoint4d[3], paramArrayOfPoint4d[2], paramArrayOfPoint4d[6], paramArrayOfPoint4d[7], paramArrayOfVector4d[1]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1688 */     computePlaneEq(paramArrayOfPoint4d[1], paramArrayOfPoint4d[5], paramArrayOfPoint4d[6], paramArrayOfPoint4d[2], paramArrayOfVector4d[2]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1693 */     computePlaneEq(paramArrayOfPoint4d[0], paramArrayOfPoint4d[3], paramArrayOfPoint4d[7], paramArrayOfPoint4d[4], paramArrayOfVector4d[3]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1698 */     computePlaneEq(paramArrayOfPoint4d[0], paramArrayOfPoint4d[1], paramArrayOfPoint4d[2], paramArrayOfPoint4d[3], paramArrayOfVector4d[4]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1703 */     computePlaneEq(paramArrayOfPoint4d[4], paramArrayOfPoint4d[7], paramArrayOfPoint4d[6], paramArrayOfPoint4d[5], paramArrayOfVector4d[5]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computePlaneEq(Point4d paramPoint4d1, Point4d paramPoint4d2, Point4d paramPoint4d3, Point4d paramPoint4d4, Vector4d paramVector4d) {
/* 1717 */     this.tVec1.x = paramPoint4d3.x - paramPoint4d1.x;
/* 1718 */     this.tVec1.y = paramPoint4d3.y - paramPoint4d1.y;
/* 1719 */     this.tVec1.z = paramPoint4d3.z - paramPoint4d1.z;
/*      */     
/* 1721 */     this.tVec2.x = paramPoint4d2.x - paramPoint4d1.x;
/* 1722 */     this.tVec2.y = paramPoint4d2.y - paramPoint4d1.y;
/* 1723 */     this.tVec2.z = paramPoint4d2.z - paramPoint4d1.z;
/*      */     
/* 1725 */     this.tVec3.cross(this.tVec2, this.tVec1);
/* 1726 */     this.tVec3.normalize();
/* 1727 */     paramVector4d.x = this.tVec3.x;
/* 1728 */     paramVector4d.y = this.tVec3.y;
/* 1729 */     paramVector4d.z = this.tVec3.z;
/* 1730 */     paramVector4d.w = -(paramVector4d.x * paramPoint4d1.x + paramVector4d.y * paramPoint4d1.y + paramVector4d.z * paramPoint4d1.z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1747 */   int getCanvasX() { return this.canvasX; }
/*      */ 
/*      */ 
/*      */   
/* 1751 */   int getCanvasY() { return this.canvasY; }
/*      */ 
/*      */ 
/*      */   
/* 1755 */   int getCanvasWidth() { return this.canvasWidth; }
/*      */ 
/*      */ 
/*      */   
/* 1759 */   int getCanvasHeight() { return this.canvasHeight; }
/*      */ 
/*      */ 
/*      */   
/* 1763 */   double getPhysicalWindowWidth() { return this.physicalWindowWidth; }
/*      */ 
/*      */ 
/*      */   
/* 1767 */   double getPhysicalWindowHeight() { return this.physicalWindowHeight; }
/*      */ 
/*      */ 
/*      */   
/* 1771 */   boolean getUseStereo() { return this.useStereo; }
/*      */ 
/*      */ 
/*      */   
/* 1775 */   Transform3D getLeftProjection() { return this.leftProjection; }
/*      */ 
/*      */ 
/*      */   
/* 1779 */   Transform3D getRightProjection() { return this.rightProjection; }
/*      */ 
/*      */ 
/*      */   
/* 1783 */   Transform3D getLeftVpcToEc() { return this.leftVpcToEc; }
/*      */ 
/*      */ 
/*      */   
/* 1787 */   Transform3D getRightVpcToEc() { return this.rightVpcToEc; }
/*      */ 
/*      */ 
/*      */   
/* 1791 */   Transform3D getLeftEcToVpc() { return this.leftEcToVpc; }
/*      */ 
/*      */ 
/*      */   
/* 1795 */   Transform3D getRightEcToVpc() { return this.rightEcToVpc; }
/*      */ 
/*      */ 
/*      */   
/* 1799 */   Transform3D getInfLeftProjection() { return this.infLeftProjection; }
/*      */ 
/*      */ 
/*      */   
/* 1803 */   Transform3D getInfRightProjection() { return this.infLeftProjection; }
/*      */ 
/*      */ 
/*      */   
/* 1807 */   Transform3D getInfLeftVpcToEc() { return this.infLeftVpcToEc; }
/*      */ 
/*      */ 
/*      */   
/* 1811 */   Transform3D getInfRightVpcToEc() { return this.infRightVpcToEc; }
/*      */ 
/*      */ 
/*      */   
/* 1815 */   Transform3D getInfLeftEcToVpc() { return this.infLeftEcToVpc; }
/*      */ 
/*      */ 
/*      */   
/* 1819 */   Transform3D getInfgRightEcToVpc() { return this.infRightEcToVpc; }
/*      */ 
/*      */ 
/*      */   
/* 1823 */   Transform3D getInfVworldToVpc() { return this.infVworldToVpc; }
/*      */ 
/*      */ 
/*      */   
/* 1827 */   Transform3D getLeftCcToVworld() { return this.leftCcToVworld; }
/*      */ 
/*      */ 
/*      */   
/* 1831 */   Transform3D getRightCcToVworld() { return this.rightCcToVworld; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1836 */   Transform3D getImagePlateToVworld() { return this.leftPlateToVworld; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1843 */   Transform3D getLastVworldToImagePlate() { return this.lastVworldToLeftPlate; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1849 */   Transform3D getVworldToImagePlate() { return this.vworldToLeftPlate; }
/*      */ 
/*      */ 
/*      */   
/* 1853 */   Transform3D getVworldToTrackerBase() { return this.vworldToTrackerBase; }
/*      */ 
/*      */ 
/*      */   
/* 1857 */   double getVworldToCoexistenceScale() { return this.vworldToCoexistenceScale; }
/*      */ 
/*      */ 
/*      */   
/* 1861 */   double getInfVworldToCoexistenceScale() { return this.infVworldToCoexistenceScale; }
/*      */ 
/*      */ 
/*      */   
/* 1865 */   Point3d getLeftEyeInImagePlate() { return this.leftEyeInImagePlate; }
/*      */ 
/*      */ 
/*      */   
/* 1869 */   Point3d getRightEyeInImagePlate() { return this.rightEyeInImagePlate; }
/*      */ 
/*      */ 
/*      */   
/* 1873 */   Point3d getCenterEyeInImagePlate() { return this.centerEyeInImagePlate; }
/*      */ 
/*      */ 
/*      */   
/* 1877 */   Transform3D getHeadToVworld() { return this.headToVworld; }
/*      */ 
/*      */ 
/*      */   
/* 1881 */   Transform3D getVpcToVworld() { return this.vpcToVworld; }
/*      */ 
/*      */ 
/*      */   
/* 1885 */   Transform3D getVworldToVpc() { return this.vworldToVpc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double getWindowXInImagePlate(double paramDouble) {
/* 1892 */     double d = paramDouble + this.canvasX;
/* 1893 */     return this.metersPerPixelX * d;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   double getWindowYInImagePlate(double paramDouble) {
/* 1899 */     double d = paramDouble + this.canvasY;
/* 1900 */     return this.metersPerPixelY * ((this.screenHeight - 1) - d);
/*      */   }
/*      */ 
/*      */   
/* 1904 */   Vector4d[] getLeftFrustumPlanesInVworld() { return this.leftFrustumPlanes; }
/*      */ 
/*      */ 
/*      */   
/* 1908 */   Vector4d[] getRightFrustumPlanesInVworld() { return this.rightFrustumPlanes; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getPixelLocationInImagePlate(double paramDouble1, double paramDouble2, double paramDouble3, Point3d paramPoint3d) {
/* 1915 */     double d1 = (paramDouble1 + this.canvasX) * this.metersPerPixelX;
/* 1916 */     double d2 = ((this.screenHeight - 1 - this.canvasY) - paramDouble2) * this.metersPerPixelY;
/*      */     
/* 1918 */     if (this.viewCache.projectionPolicy == 1 && this.centerEyeInImagePlate.z != 0.0D) {
/*      */       
/* 1920 */       double d = 1.0D - paramDouble3 / this.centerEyeInImagePlate.z;
/* 1921 */       paramPoint3d.x = (d1 - this.centerEyeInImagePlate.x) * d + this.centerEyeInImagePlate.x;
/*      */       
/* 1923 */       paramPoint3d.y = (d2 - this.centerEyeInImagePlate.y) * d + this.centerEyeInImagePlate.y;
/*      */     } else {
/*      */       
/* 1926 */       paramPoint3d.x = d1;
/* 1927 */       paramPoint3d.y = d2;
/*      */     } 
/* 1929 */     paramPoint3d.z = paramDouble3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getPixelLocationFromImagePlate(Point3d paramPoint3d, Point2d paramPoint2d) {
/*      */     double d2, d1;
/* 1941 */     if (this.viewCache.projectionPolicy == 1) {
/*      */       
/* 1943 */       this.tVec1.sub(paramPoint3d, this.centerEyeInImagePlate);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1952 */       if (this.tVec1.z != 0.0D) {
/* 1953 */         double d = this.centerEyeInImagePlate.z / -this.tVec1.z;
/* 1954 */         d1 = this.centerEyeInImagePlate.x + this.tVec1.x * d;
/* 1955 */         d2 = this.centerEyeInImagePlate.y + this.tVec1.y * d;
/*      */       } else {
/*      */         
/* 1958 */         d1 = paramPoint3d.x;
/* 1959 */         d2 = paramPoint3d.y;
/*      */       } 
/*      */     } else {
/*      */       
/* 1963 */       d1 = paramPoint3d.x;
/* 1964 */       d2 = paramPoint3d.y;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1971 */     paramPoint2d.x = d1 / this.screenViewCache.metersPerPixelX - this.canvasX;
/* 1972 */     paramPoint2d.y = (this.screenViewCache.screenHeight - 1) - d2 / this.screenViewCache.metersPerPixelY - this.canvasY; } CanvasViewCache(Canvas3D paramCanvas3D, ScreenViewCache paramScreenViewCache, ViewCache paramViewCache) { this.cvcDirtyMask = 0; this.leftManualEyeInImagePlate = new Point3d(); this.rightManualEyeInImagePlate = new Point3d(); this.leftProjection = new Transform3D(); this.rightProjection = new Transform3D(); this.infLeftProjection = new Transform3D(); this.infRightProjection = new Transform3D(); this.leftVpcToEc = new Transform3D(); this.rightVpcToEc = new Transform3D(); this.infLeftVpcToEc = new Transform3D(); this.infRightVpcToEc = new Transform3D(); this.leftEcToVpc = new Transform3D(); this.rightEcToVpc = new Transform3D(); this.infLeftEcToVpc = new Transform3D(); this.infRightEcToVpc = new Transform3D(); this.leftFrustumPlanes = new Vector4d[6]; this.rightFrustumPlanes = new Vector4d[6]; this.leftFrustumPoints = new Point4d[8]; this.rightFrustumPoints = new Point4d[8]; this.headTrackerToLeftImagePlate = new Transform3D(); this.headTrackerToRightImagePlate = new Transform3D(); this.leftTrackedEyeInImagePlate = new Point3d(); this.rightTrackedEyeInImagePlate = new Point3d(); this.leftEyeInImagePlate = new Point3d(); this.rightEyeInImagePlate = new Point3d(); this.centerEyeInImagePlate = new Point3d(); this.physicalWindowCenter = new Point3d(); this.leftCcToVworld = new Transform3D(); this.rightCcToVworld = new Transform3D(); this.coexistenceToLeftPlate = new Transform3D(); this.coexistenceToRightPlate = new Transform3D(); this.vpcToCoexistence = new Transform3D(); this.vpcToLeftPlate = new Transform3D(); this.vpcToRightPlate = new Transform3D(); this.leftPlateToVpc = new Transform3D(); this.rightPlateToVpc = new Transform3D(); this.vworldToLeftPlate = new Transform3D(); this.lastVworldToLeftPlate = new Transform3D(); this.vworldToRightPlate = new Transform3D(); this.leftPlateToVworld = new Transform3D(); this.rightPlateToVworld = new Transform3D(); this.headToLeftImagePlate = new Transform3D(); this.headToRightImagePlate = new Transform3D(); this.vworldToTrackerBase = new Transform3D(); this.tempTrans = new Transform3D(); this.headToVworld = new Transform3D();
/*      */     this.coexistenceCenter = new Vector3d();
/*      */     this.tMat1 = new Transform3D();
/*      */     this.tMat2 = new Transform3D();
/*      */     this.tVec1 = new Vector3d();
/*      */     this.tVec2 = new Vector3d();
/*      */     this.tVec3 = new Vector3d();
/*      */     this.tPnt1 = new Point3d();
/*      */     this.tPnt2 = new Point3d();
/*      */     this.tMatrix = new Matrix4d();
/*      */     this.vworldToVpc = new Transform3D();
/*      */     this.vpcToVworld = new Transform3D();
/*      */     this.infVworldToVpc = new Transform3D();
/*      */     this.lastDoInfinite = false;
/*      */     this.updateLastTime = false;
/* 1987 */     this.canvas = paramCanvas3D;
/* 1988 */     this.screenViewCache = paramScreenViewCache;
/* 1989 */     this.viewCache = paramViewCache;
/*      */     
/*      */     byte b;
/*      */     
/* 1993 */     for (b = 0; b < this.leftFrustumPlanes.length; b++) {
/* 1994 */       this.leftFrustumPlanes[b] = new Vector4d();
/* 1995 */       this.rightFrustumPlanes[b] = new Vector4d();
/*      */     } 
/*      */     
/* 1998 */     for (b = 0; b < this.leftFrustumPoints.length; b++) {
/* 1999 */       this.leftFrustumPoints[b] = new Point4d();
/* 2000 */       this.rightFrustumPoints[b] = new Point4d();
/*      */     } 
/*      */ 
/*      */     
/* 2004 */     if (paramCanvas3D != null) {
/* 2005 */       this.leftEyeInImagePlate.set(paramCanvas3D.leftManualEyeInImagePlate);
/* 2006 */       this.rightEyeInImagePlate.set(paramCanvas3D.rightManualEyeInImagePlate);
/* 2007 */       this.centerEyeInImagePlate.add(this.leftEyeInImagePlate, this.rightEyeInImagePlate);
/*      */       
/* 2009 */       this.centerEyeInImagePlate.scale(0.5D);
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2017 */   void setCanvas(Canvas3D paramCanvas3D) { this.canvas = paramCanvas3D; }
/*      */ 
/*      */ 
/*      */   
/* 2021 */   void setScreenViewCache(ScreenViewCache paramScreenViewCache) { this.screenViewCache = paramScreenViewCache; }
/*      */ 
/*      */ 
/*      */   
/* 2025 */   void setViewCache(ViewCache paramViewCache) { this.viewCache = paramViewCache; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\CanvasViewCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */