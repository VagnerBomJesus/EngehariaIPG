/*      */ package com.sun.j3d.utils.universe;
/*      */ 
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.FieldPosition;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.media.j3d.Canvas3D;
/*      */ import javax.media.j3d.PhysicalBody;
/*      */ import javax.media.j3d.PhysicalEnvironment;
/*      */ import javax.media.j3d.Screen3D;
/*      */ import javax.media.j3d.Sensor;
/*      */ import javax.media.j3d.Transform3D;
/*      */ import javax.media.j3d.View;
/*      */ import javax.media.j3d.ViewPlatform;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Vector3d;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ViewInfo
/*      */ {
/*      */   private static final boolean verbose = false;
/*      */   public static final int SCREEN_AUTO_UPDATE = 1;
/*      */   public static final int CANVAS_AUTO_UPDATE = 2;
/*      */   public static final int VIEW_AUTO_UPDATE = 4;
/*      */   public static final int HEAD_AUTO_UPDATE = 8;
/*      */   public static final int PLATFORM_AUTO_UPDATE = 16;
/*  235 */   private static Map staticVpMap = new HashMap();
/*  236 */   private static Map staticSiMap = new HashMap();
/*      */   
/*  238 */   private Map screenMap = null;
/*  239 */   private Map viewPlatformMap = null;
/*      */ 
/*      */   
/*  242 */   private View view = null;
/*  243 */   private Sensor headTracker = null;
/*      */   
/*      */   private boolean useTracking = false;
/*      */   
/*      */   private boolean clipVirtual = false;
/*  248 */   private ViewPlatformInfo vpi = null;
/*  249 */   private int canvasCount = 0;
/*  250 */   private Map canvasMap = new HashMap();
/*  251 */   private CanvasInfo[] canvasInfo = new CanvasInfo[1];
/*      */   
/*      */   private boolean updateView = true;
/*      */   
/*      */   private boolean updateHead = true;
/*      */   
/*      */   private boolean autoUpdate = false;
/*  258 */   private int autoUpdateFlags = 0;
/*      */ 
/*      */   
/*  261 */   private int viewPolicy = 0;
/*  262 */   private int resizePolicy = 1;
/*  263 */   private int movementPolicy = 1;
/*  264 */   private int eyePolicy = 2;
/*  265 */   private int projectionPolicy = 1;
/*  266 */   private int frontClipPolicy = 3;
/*  267 */   private int backClipPolicy = 3;
/*  268 */   private int scalePolicy = 0;
/*      */ 
/*      */   
/*      */   private boolean coeCentering = true;
/*      */   
/*  273 */   private Transform3D coeToTrackerBase = null;
/*  274 */   private Transform3D headToHeadTracker = null;
/*      */ 
/*      */   
/*  277 */   private Transform3D headTrackerToTrackerBase = null;
/*  278 */   private Transform3D trackerBaseToHeadTracker = null;
/*      */ 
/*      */   
/*  281 */   private Transform3D headToTrackerBase = null;
/*  282 */   private Transform3D coeToHeadTracker = null;
/*      */ 
/*      */   
/*  285 */   private PhysicalEnvironment env = null;
/*  286 */   private PhysicalBody body = null;
/*  287 */   private Point3d leftEyeInHead = new Point3d();
/*  288 */   private Point3d rightEyeInHead = new Point3d();
/*      */ 
/*      */ 
/*      */   
/*  292 */   private Vector3d v3d = new Vector3d();
/*  293 */   private double[] m16d = new double[16];
/*  294 */   private Point3d leftEye = new Point3d();
/*  295 */   private Point3d rightEye = new Point3d();
/*  296 */   private Map newMap = new HashMap();
/*  297 */   private Set newSet = new HashSet();
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
/*  322 */   public ViewInfo(View paramView) { this(paramView, 0); }
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
/*  343 */   public ViewInfo(View paramView, int paramInt) { this(paramView, paramInt, staticSiMap, staticVpMap); }
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
/*      */   public ViewInfo(View paramView, int paramInt, Map paramMap1, Map paramMap2) {
/*  384 */     if (paramView == null)
/*  385 */       throw new IllegalArgumentException("View is null"); 
/*  386 */     if (paramMap1 == null)
/*  387 */       throw new IllegalArgumentException("screenMap is null"); 
/*  388 */     if (paramMap2 == null) {
/*  389 */       throw new IllegalArgumentException("viewPlatformMap is null");
/*      */     }
/*  391 */     this.view = paramView;
/*  392 */     this.screenMap = paramMap1;
/*  393 */     this.viewPlatformMap = paramMap2;
/*      */     
/*  395 */     if (paramInt == 0) {
/*  396 */       this.autoUpdate = false;
/*      */     } else {
/*      */       
/*  399 */       this.autoUpdate = true;
/*  400 */       this.autoUpdateFlags = paramInt;
/*      */     } 
/*      */     
/*  403 */     getViewInfo();
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
/*      */   public void getImagePlateToViewPlatform(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/*  425 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getImagePlateToViewPlatform", false);
/*      */ 
/*      */     
/*  428 */     getImagePlateToViewPlatform(canvasInfo1);
/*  429 */     paramTransform3D1.set(canvasInfo1.plateToViewPlatform);
/*  430 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/*  431 */       paramTransform3D2.set(canvasInfo1.rightPlateToViewPlatform); 
/*      */   }
/*      */   
/*      */   private void getImagePlateToViewPlatform(CanvasInfo paramCanvasInfo) {
/*  435 */     if (paramCanvasInfo.updatePlateToViewPlatform) {
/*      */       
/*  437 */       if (paramCanvasInfo.plateToViewPlatform == null) {
/*  438 */         paramCanvasInfo.plateToViewPlatform = new Transform3D();
/*      */       }
/*  440 */       getCoexistenceToImagePlate(paramCanvasInfo);
/*  441 */       getViewPlatformToCoexistence(paramCanvasInfo);
/*      */       
/*  443 */       paramCanvasInfo.plateToViewPlatform.mul(paramCanvasInfo.coeToPlate, paramCanvasInfo.viewPlatformToCoe);
/*  444 */       paramCanvasInfo.plateToViewPlatform.invert();
/*      */       
/*  446 */       if (paramCanvasInfo.useStereo) {
/*  447 */         if (paramCanvasInfo.rightPlateToViewPlatform == null) {
/*  448 */           paramCanvasInfo.rightPlateToViewPlatform = new Transform3D();
/*      */         }
/*  450 */         paramCanvasInfo.rightPlateToViewPlatform.mul(paramCanvasInfo.coeToRightPlate, paramCanvasInfo.viewPlatformToCoe);
/*      */         
/*  452 */         paramCanvasInfo.rightPlateToViewPlatform.invert();
/*      */       } 
/*  454 */       paramCanvasInfo.updatePlateToViewPlatform = false;
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
/*      */   public void getImagePlateToVworld(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/*  481 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getImagePlateToVworld", true);
/*  482 */     getImagePlateToVworld(canvasInfo1);
/*  483 */     paramTransform3D1.set(canvasInfo1.plateToVworld);
/*  484 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/*  485 */       paramTransform3D2.set(canvasInfo1.rightPlateToVworld); 
/*      */   }
/*      */   
/*      */   private void getImagePlateToVworld(CanvasInfo paramCanvasInfo) {
/*  489 */     if (paramCanvasInfo.updatePlateToVworld) {
/*      */       
/*  491 */       if (paramCanvasInfo.plateToVworld == null) {
/*  492 */         paramCanvasInfo.plateToVworld = new Transform3D();
/*      */       }
/*  494 */       getImagePlateToViewPlatform(paramCanvasInfo);
/*  495 */       paramCanvasInfo.plateToVworld.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.plateToViewPlatform);
/*      */ 
/*      */       
/*  498 */       if (paramCanvasInfo.useStereo) {
/*  499 */         if (paramCanvasInfo.rightPlateToVworld == null) {
/*  500 */           paramCanvasInfo.rightPlateToVworld = new Transform3D();
/*      */         }
/*  502 */         paramCanvasInfo.rightPlateToVworld.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.rightPlateToViewPlatform);
/*      */       } 
/*      */       
/*  505 */       paramCanvasInfo.updatePlateToVworld = false;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void getCoexistenceToImagePlate(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/*  544 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getCoexistenceToImagePlate", false);
/*  545 */     getCoexistenceToImagePlate(canvasInfo1);
/*  546 */     paramTransform3D1.set(canvasInfo1.coeToPlate);
/*  547 */     if (canvasInfo1.useStereo && paramTransform3D2 != null) {
/*  548 */       paramTransform3D2.set(canvasInfo1.coeToRightPlate);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getCoexistenceToImagePlate(CanvasInfo paramCanvasInfo) {
/*  559 */     if (paramCanvasInfo.updateCoeToPlate) {
/*      */       
/*  561 */       if (paramCanvasInfo.coeToPlate == null) {
/*  562 */         paramCanvasInfo.coeToPlate = new Transform3D();
/*  563 */         paramCanvasInfo.coeToRightPlate = new Transform3D();
/*      */       } 
/*  565 */       if (this.viewPolicy == 1) {
/*      */ 
/*      */ 
/*      */         
/*  569 */         paramCanvasInfo.coeToPlate.mul(paramCanvasInfo.si.headTrackerToLeftPlate, this.coeToHeadTracker);
/*      */         
/*  571 */         if (paramCanvasInfo.useStereo) {
/*      */ 
/*      */           
/*  574 */           paramCanvasInfo.coeToRightPlate.mul(paramCanvasInfo.si.headTrackerToRightPlate, this.coeToHeadTracker);
/*      */         } else {
/*      */           
/*  577 */           paramCanvasInfo.coeToRightPlate.set(paramCanvasInfo.coeToPlate);
/*      */         } 
/*  579 */       } else if (this.coeCentering) {
/*      */ 
/*      */         
/*  582 */         if (this.movementPolicy == 1) {
/*      */           
/*  584 */           this.v3d.set(paramCanvasInfo.canvasX + paramCanvasInfo.canvasWidth / 2.0D, paramCanvasInfo.canvasY + paramCanvasInfo.canvasHeight / 2.0D, 0.0D);
/*      */         }
/*      */         else {
/*      */           
/*  588 */           this.v3d.set(paramCanvasInfo.si.screenWidth / 2.0D, paramCanvasInfo.si.screenHeight / 2.0D, 0.0D);
/*      */         } 
/*      */         
/*  591 */         paramCanvasInfo.coeToPlate.set(this.v3d);
/*  592 */         paramCanvasInfo.coeToRightPlate.set(this.v3d);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  598 */         paramCanvasInfo.coeToPlate.mul(paramCanvasInfo.si.trackerBaseToPlate, this.coeToTrackerBase);
/*  599 */         paramCanvasInfo.coeToRightPlate.set(paramCanvasInfo.coeToPlate);
/*      */       } 
/*  601 */       paramCanvasInfo.updateCoeToPlate = false;
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
/*      */   public void getViewPlatformToCoexistence(Canvas3D paramCanvas3D, Transform3D paramTransform3D) {
/*  648 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getViewPlatformToCoexistence", false);
/*      */ 
/*      */     
/*  651 */     getViewPlatformToCoexistence(canvasInfo1);
/*  652 */     paramTransform3D.set(canvasInfo1.viewPlatformToCoe);
/*      */   }
/*      */   private void getViewPlatformToCoexistence(CanvasInfo paramCanvasInfo) {
/*      */     double d1;
/*  656 */     if (!paramCanvasInfo.updateViewPlatformToCoe)
/*      */       return; 
/*  658 */     if (paramCanvasInfo.viewPlatformToCoe == null) {
/*  659 */       paramCanvasInfo.viewPlatformToCoe = new Transform3D();
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
/*  671 */     getScreenScale(paramCanvasInfo);
/*  672 */     if (this.resizePolicy == 1) {
/*  673 */       paramCanvasInfo.viewPlatformToCoe.setScale(paramCanvasInfo.screenScale * paramCanvasInfo.windowScale);
/*      */     } else {
/*  675 */       paramCanvasInfo.viewPlatformToCoe.setScale(paramCanvasInfo.screenScale);
/*      */     } 
/*  677 */     if (this.viewPolicy == 1) {
/*      */ 
/*      */       
/*  680 */       paramCanvasInfo.updateViewPlatformToCoe = false;
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
/*  691 */     double d2 = this.body.getNominalEyeHeightFromGround();
/*  692 */     int i = this.view.getViewPlatform().getViewAttachPolicy();
/*  693 */     int j = this.env.getCoexistenceCenterInPworldPolicy();
/*      */     
/*  695 */     if (this.eyePolicy == 2) {
/*      */       
/*  697 */       d1 = paramCanvasInfo.getFieldOfViewOffset();
/*      */     } else {
/*      */       
/*  700 */       d1 = this.body.getNominalEyeOffsetFromNominalScreen();
/*      */     } 
/*  702 */     if (j == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  707 */       if (i == 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  713 */         this.v3d.set(0.0D, 0.0D, d1);
/*      */       }
/*  715 */       else if (i == 2)
/*      */       {
/*      */         
/*  718 */         this.v3d.set(0.0D, 0.0D, 0.0D);
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  723 */         this.v3d.set(0.0D, -d2, d1);
/*      */       }
/*      */     
/*  726 */     } else if (j == 0) {
/*      */       
/*  728 */       if (i == 0)
/*      */       {
/*      */ 
/*      */         
/*  732 */         this.v3d.set(0.0D, 0.0D, 0.0D);
/*      */       }
/*  734 */       else if (i == 2)
/*      */       {
/*      */         
/*  737 */         this.v3d.set(0.0D, 0.0D, -d1);
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  742 */         this.v3d.set(0.0D, -d2, 0.0D);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  747 */     else if (i == 0) {
/*  748 */       this.v3d.set(0.0D, d2, 0.0D);
/*      */     }
/*  750 */     else if (i == 2) {
/*  751 */       this.v3d.set(0.0D, d2, -d1);
/*      */     } else {
/*      */       
/*  754 */       this.v3d.set(0.0D, 0.0D, 0.0D);
/*      */     } 
/*      */ 
/*      */     
/*  758 */     paramCanvasInfo.viewPlatformToCoe.setTranslation(this.v3d);
/*  759 */     paramCanvasInfo.updateViewPlatformToCoe = false;
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
/*      */   public void getCoexistenceToViewPlatform(Canvas3D paramCanvas3D, Transform3D paramTransform3D) {
/*  779 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getCoexistenceToViewPlatform", false);
/*      */ 
/*      */     
/*  782 */     getCoexistenceToViewPlatform(canvasInfo1);
/*  783 */     paramTransform3D.set(canvasInfo1.coeToViewPlatform);
/*      */   }
/*      */   
/*      */   private void getCoexistenceToViewPlatform(CanvasInfo paramCanvasInfo) {
/*  787 */     if (paramCanvasInfo.updateCoeToViewPlatform) {
/*      */       
/*  789 */       if (paramCanvasInfo.coeToViewPlatform == null) {
/*  790 */         paramCanvasInfo.coeToViewPlatform = new Transform3D();
/*      */       }
/*  792 */       getViewPlatformToCoexistence(paramCanvasInfo);
/*  793 */       paramCanvasInfo.coeToViewPlatform.invert(paramCanvasInfo.viewPlatformToCoe);
/*      */       
/*  795 */       paramCanvasInfo.updateCoeToViewPlatform = false;
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
/*      */   public void getCoexistenceToVworld(Canvas3D paramCanvas3D, Transform3D paramTransform3D) {
/*  820 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getCoexistenceToVworld", true);
/*  821 */     getCoexistenceToVworld(canvasInfo1);
/*  822 */     paramTransform3D.set(canvasInfo1.coeToVworld);
/*      */   }
/*      */   
/*      */   private void getCoexistenceToVworld(CanvasInfo paramCanvasInfo) {
/*  826 */     if (paramCanvasInfo.updateCoeToVworld) {
/*      */       
/*  828 */       if (paramCanvasInfo.coeToVworld == null) paramCanvasInfo.coeToVworld = new Transform3D();
/*      */       
/*  830 */       getCoexistenceToViewPlatform(paramCanvasInfo);
/*  831 */       paramCanvasInfo.coeToVworld.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.coeToViewPlatform);
/*      */ 
/*      */       
/*  834 */       paramCanvasInfo.updateCoeToVworld = false;
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
/*      */   public void getEyeToImagePlate(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/*  865 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getEyeToImagePlate", false);
/*  866 */     getEyeToImagePlate(canvasInfo1);
/*  867 */     paramTransform3D1.set(canvasInfo1.eyeToPlate);
/*  868 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/*  869 */       paramTransform3D2.set(canvasInfo1.rightEyeToPlate); 
/*      */   }
/*      */   
/*      */   private void getEyeToImagePlate(CanvasInfo paramCanvasInfo) {
/*  873 */     if (paramCanvasInfo.updateEyeInPlate) {
/*      */       
/*  875 */       if (paramCanvasInfo.eyeToPlate == null) {
/*  876 */         paramCanvasInfo.eyeToPlate = new Transform3D();
/*      */       }
/*  878 */       if (this.viewPolicy == 1) {
/*  879 */         getEyesHMD(paramCanvasInfo);
/*      */       }
/*  881 */       else if (this.useTracking) {
/*  882 */         getEyesTracked(paramCanvasInfo);
/*      */       } else {
/*      */         
/*  885 */         getEyesFixedScreen(paramCanvasInfo);
/*      */       } 
/*  887 */       paramCanvasInfo.updateEyeInPlate = false;
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
/*      */   private void getEyesHMD(CanvasInfo paramCanvasInfo) {
/*  914 */     if (paramCanvasInfo.useStereo) {
/*      */ 
/*      */ 
/*      */       
/*  918 */       this.leftEye.set(this.leftEyeInHead);
/*  919 */       this.headToHeadTracker.transform(this.leftEye);
/*  920 */       paramCanvasInfo.si.headTrackerToLeftPlate.transform(this.leftEye, paramCanvasInfo.eyeInPlate);
/*      */       
/*  922 */       this.rightEye.set(this.rightEyeInHead);
/*  923 */       this.headToHeadTracker.transform(this.rightEye);
/*  924 */       paramCanvasInfo.si.headTrackerToRightPlate.transform(this.rightEye, paramCanvasInfo.rightEyeInPlate);
/*      */       
/*  926 */       if (paramCanvasInfo.rightEyeToPlate == null) {
/*  927 */         paramCanvasInfo.rightEyeToPlate = new Transform3D();
/*      */       }
/*  929 */       this.v3d.set(paramCanvasInfo.rightEyeInPlate);
/*  930 */       paramCanvasInfo.rightEyeToPlate.set(this.v3d);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  935 */       switch (paramCanvasInfo.monoscopicPolicy) {
/*      */         case 0:
/*  937 */           this.leftEye.set(this.leftEyeInHead);
/*  938 */           this.headToHeadTracker.transform(this.leftEye);
/*  939 */           paramCanvasInfo.si.headTrackerToLeftPlate.transform(this.leftEye, paramCanvasInfo.eyeInPlate);
/*      */           break;
/*      */         
/*      */         case 1:
/*  943 */           this.rightEye.set(this.rightEyeInHead);
/*  944 */           this.headToHeadTracker.transform(this.rightEye);
/*  945 */           paramCanvasInfo.si.headTrackerToRightPlate.transform(this.rightEye, paramCanvasInfo.eyeInPlate);
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  950 */           throw new IllegalStateException("Illegal monoscopic view policy for 2-channel HMD");
/*      */       } 
/*      */     
/*      */     } 
/*  954 */     this.v3d.set(paramCanvasInfo.eyeInPlate);
/*  955 */     paramCanvasInfo.eyeToPlate.set(this.v3d);
/*      */   }
/*      */   
/*      */   private void getEyesTracked(CanvasInfo paramCanvasInfo) {
/*  959 */     this.leftEye.set(this.leftEyeInHead);
/*  960 */     this.rightEye.set(this.rightEyeInHead);
/*  961 */     this.headToTrackerBase.transform(this.leftEye);
/*  962 */     this.headToTrackerBase.transform(this.rightEye);
/*  963 */     if (this.coeCentering) {
/*      */ 
/*      */       
/*  966 */       getCoexistenceToImagePlate(paramCanvasInfo);
/*  967 */       paramCanvasInfo.coeToPlate.transform(this.leftEye);
/*  968 */       paramCanvasInfo.coeToRightPlate.transform(this.rightEye);
/*      */     }
/*      */     else {
/*      */       
/*  972 */       paramCanvasInfo.si.trackerBaseToPlate.transform(this.leftEye);
/*  973 */       paramCanvasInfo.si.trackerBaseToPlate.transform(this.rightEye);
/*      */     } 
/*  975 */     setEyeScreenRelative(paramCanvasInfo, this.leftEye, this.rightEye);
/*      */   }
/*      */   private void getEyesFixedScreen(CanvasInfo paramCanvasInfo) {
/*      */     double d;
/*  979 */     switch (this.eyePolicy) {
/*      */       case 2:
/*  981 */         d = paramCanvasInfo.getFieldOfViewOffset();
/*  982 */         setEyeWindowRelative(paramCanvasInfo, d, d);
/*      */         break;
/*      */       case 1:
/*  985 */         setEyeWindowRelative(paramCanvasInfo, paramCanvasInfo.leftManualEyeInPlate.z, paramCanvasInfo.rightManualEyeInPlate.z);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 0:
/*  990 */         setEyeScreenRelative(paramCanvasInfo, paramCanvasInfo.leftManualEyeInPlate, paramCanvasInfo.rightManualEyeInPlate);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  995 */         this.view.getLeftManualEyeInCoexistence(this.leftEye);
/*  996 */         this.view.getRightManualEyeInCoexistence(this.rightEye);
/*      */         
/*  998 */         getCoexistenceToImagePlate(paramCanvasInfo);
/*  999 */         paramCanvasInfo.coeToPlate.transform(this.leftEye);
/* 1000 */         paramCanvasInfo.coeToRightPlate.transform(this.rightEye);
/* 1001 */         setEyeScreenRelative(paramCanvasInfo, this.leftEye, this.rightEye);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setEyeWindowRelative(CanvasInfo paramCanvasInfo, double paramDouble1, double paramDouble2) {
/* 1010 */     double d = paramCanvasInfo.canvasX + paramCanvasInfo.canvasWidth / 2.0D;
/* 1011 */     this.leftEye.x = d + this.leftEyeInHead.x;
/* 1012 */     this.rightEye.x = d + this.rightEyeInHead.x;
/*      */ 
/*      */     
/* 1015 */     this.rightEye.y = paramCanvasInfo.canvasY + paramCanvasInfo.canvasHeight / 2.0D;
/*      */ 
/*      */     
/* 1018 */     this.leftEye.z = paramDouble1;
/* 1019 */     this.rightEye.z = paramDouble2;
/*      */     
/* 1021 */     setEyeScreenRelative(paramCanvasInfo, this.leftEye, this.rightEye);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setEyeScreenRelative(CanvasInfo paramCanvasInfo, Point3d paramPoint3d1, Point3d paramPoint3d2) {
/* 1026 */     if (paramCanvasInfo.useStereo) {
/* 1027 */       paramCanvasInfo.eyeInPlate.set(paramPoint3d1);
/* 1028 */       paramCanvasInfo.rightEyeInPlate.set(paramPoint3d2);
/*      */       
/* 1030 */       if (paramCanvasInfo.rightEyeToPlate == null) {
/* 1031 */         paramCanvasInfo.rightEyeToPlate = new Transform3D();
/*      */       }
/* 1033 */       this.v3d.set(paramCanvasInfo.rightEyeInPlate);
/* 1034 */       paramCanvasInfo.rightEyeToPlate.set(this.v3d);
/*      */     } else {
/*      */       
/* 1037 */       switch (paramCanvasInfo.monoscopicPolicy) {
/*      */         case 2:
/* 1039 */           paramCanvasInfo.eyeInPlate.set((paramPoint3d1.x + paramPoint3d2.x) / 2.0D, (paramPoint3d1.y + paramPoint3d2.y) / 2.0D, (paramPoint3d1.z + paramPoint3d2.z) / 2.0D);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 0:
/* 1044 */           paramCanvasInfo.eyeInPlate.set(paramPoint3d1);
/*      */           break;
/*      */         case 1:
/* 1047 */           paramCanvasInfo.eyeInPlate.set(paramPoint3d2);
/*      */           break;
/*      */       } 
/*      */     } 
/* 1051 */     this.v3d.set(paramCanvasInfo.eyeInPlate);
/* 1052 */     paramCanvasInfo.eyeToPlate.set(this.v3d);
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
/*      */   public void getEyeToViewPlatform(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1106 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getEyeToViewPlatform", false);
/* 1107 */     getEyeToViewPlatform(canvasInfo1);
/* 1108 */     paramTransform3D1.set(canvasInfo1.eyeToViewPlatform);
/* 1109 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/* 1110 */       paramTransform3D2.set(canvasInfo1.rightEyeToViewPlatform); 
/*      */   }
/*      */   
/*      */   private void getEyeToViewPlatform(CanvasInfo paramCanvasInfo) {
/* 1114 */     if (paramCanvasInfo.updateEyeToViewPlatform) {
/*      */       
/* 1116 */       if (paramCanvasInfo.eyeToViewPlatform == null) {
/* 1117 */         paramCanvasInfo.eyeToViewPlatform = new Transform3D();
/*      */       }
/* 1119 */       getEyeToImagePlate(paramCanvasInfo);
/* 1120 */       getImagePlateToViewPlatform(paramCanvasInfo);
/* 1121 */       paramCanvasInfo.eyeToViewPlatform.mul(paramCanvasInfo.plateToViewPlatform, paramCanvasInfo.eyeToPlate);
/*      */       
/* 1123 */       if (paramCanvasInfo.useStereo) {
/* 1124 */         if (paramCanvasInfo.rightEyeToViewPlatform == null) {
/* 1125 */           paramCanvasInfo.rightEyeToViewPlatform = new Transform3D();
/*      */         }
/* 1127 */         paramCanvasInfo.rightEyeToViewPlatform.mul(paramCanvasInfo.rightPlateToViewPlatform, paramCanvasInfo.rightEyeToPlate);
/*      */       } 
/*      */       
/* 1130 */       paramCanvasInfo.updateEyeToViewPlatform = false;
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
/*      */   public void getViewPlatformToEye(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1157 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getViewPlatformToEye", false);
/* 1158 */     getViewPlatformToEye(canvasInfo1);
/* 1159 */     paramTransform3D1.set(canvasInfo1.viewPlatformToEye);
/* 1160 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/* 1161 */       paramTransform3D2.set(canvasInfo1.viewPlatformToRightEye); 
/*      */   }
/*      */   
/*      */   private void getViewPlatformToEye(CanvasInfo paramCanvasInfo) {
/* 1165 */     if (paramCanvasInfo.updateViewPlatformToEye) {
/*      */       
/* 1167 */       if (paramCanvasInfo.viewPlatformToEye == null) {
/* 1168 */         paramCanvasInfo.viewPlatformToEye = new Transform3D();
/*      */       }
/* 1170 */       getEyeToViewPlatform(paramCanvasInfo);
/* 1171 */       paramCanvasInfo.viewPlatformToEye.invert(paramCanvasInfo.eyeToViewPlatform);
/*      */       
/* 1173 */       if (paramCanvasInfo.useStereo) {
/* 1174 */         if (paramCanvasInfo.viewPlatformToRightEye == null) {
/* 1175 */           paramCanvasInfo.viewPlatformToRightEye = new Transform3D();
/*      */         }
/* 1177 */         paramCanvasInfo.viewPlatformToRightEye.invert(paramCanvasInfo.rightEyeToViewPlatform);
/*      */       } 
/* 1179 */       paramCanvasInfo.updateViewPlatformToEye = false;
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
/*      */   public void getEyeToVworld(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1209 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getEyeToVworld", true);
/* 1210 */     getEyeToVworld(canvasInfo1);
/* 1211 */     paramTransform3D1.set(canvasInfo1.eyeToVworld);
/* 1212 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/* 1213 */       paramTransform3D2.set(canvasInfo1.rightEyeToVworld); 
/*      */   }
/*      */   
/*      */   private void getEyeToVworld(CanvasInfo paramCanvasInfo) {
/* 1217 */     if (paramCanvasInfo.updateEyeToVworld) {
/*      */       
/* 1219 */       if (paramCanvasInfo.eyeToVworld == null) {
/* 1220 */         paramCanvasInfo.eyeToVworld = new Transform3D();
/*      */       }
/* 1222 */       getEyeToViewPlatform(paramCanvasInfo);
/* 1223 */       paramCanvasInfo.eyeToVworld.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.eyeToViewPlatform);
/*      */ 
/*      */       
/* 1226 */       if (paramCanvasInfo.useStereo) {
/* 1227 */         if (paramCanvasInfo.rightEyeToVworld == null) {
/* 1228 */           paramCanvasInfo.rightEyeToVworld = new Transform3D();
/*      */         }
/* 1230 */         paramCanvasInfo.rightEyeToVworld.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.rightEyeToViewPlatform);
/*      */       } 
/*      */       
/* 1233 */       paramCanvasInfo.updateEyeToVworld = false;
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
/*      */   public void getProjection(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1268 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getProjection", true);
/* 1269 */     getProjection(canvasInfo1);
/* 1270 */     paramTransform3D1.set(canvasInfo1.projection);
/* 1271 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/* 1272 */       paramTransform3D2.set(canvasInfo1.rightProjection); 
/*      */   }
/*      */   
/*      */   private void getProjection(CanvasInfo paramCanvasInfo) {
/* 1276 */     if (paramCanvasInfo.updateProjection) {
/*      */       
/* 1278 */       if (paramCanvasInfo.projection == null) {
/* 1279 */         paramCanvasInfo.projection = new Transform3D();
/*      */       }
/* 1281 */       getEyeToImagePlate(paramCanvasInfo);
/* 1282 */       getClipDistances(paramCanvasInfo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1288 */       double d = getBackClip(paramCanvasInfo, paramCanvasInfo.eyeInPlate);
/* 1289 */       computeProjection(paramCanvasInfo, paramCanvasInfo.eyeInPlate, getFrontClip(paramCanvasInfo, paramCanvasInfo.eyeInPlate), d, paramCanvasInfo.projection);
/*      */ 
/*      */ 
/*      */       
/* 1293 */       if (paramCanvasInfo.useStereo) {
/* 1294 */         if (paramCanvasInfo.rightProjection == null) {
/* 1295 */           paramCanvasInfo.rightProjection = new Transform3D();
/*      */         }
/* 1297 */         computeProjection(paramCanvasInfo, paramCanvasInfo.rightEyeInPlate, getFrontClip(paramCanvasInfo, paramCanvasInfo.rightEyeInPlate), d, paramCanvasInfo.rightProjection);
/*      */       } 
/*      */ 
/*      */       
/* 1301 */       paramCanvasInfo.updateProjection = false;
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
/*      */   public void getInverseProjection(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1332 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getInverseProjection", true);
/* 1333 */     getInverseProjection(canvasInfo1);
/* 1334 */     paramTransform3D1.set(canvasInfo1.inverseProjection);
/* 1335 */     if (canvasInfo1.useStereo && paramTransform3D2 != null)
/* 1336 */       paramTransform3D2.set(canvasInfo1.inverseRightProjection); 
/*      */   }
/*      */   
/*      */   private void getInverseProjection(CanvasInfo paramCanvasInfo) {
/* 1340 */     if (paramCanvasInfo.updateInverseProjection) {
/*      */       
/* 1342 */       if (paramCanvasInfo.inverseProjection == null) {
/* 1343 */         paramCanvasInfo.inverseProjection = new Transform3D();
/*      */       }
/* 1345 */       getProjection(paramCanvasInfo);
/* 1346 */       paramCanvasInfo.inverseProjection.invert(paramCanvasInfo.projection);
/*      */       
/* 1348 */       if (paramCanvasInfo.useStereo) {
/* 1349 */         if (paramCanvasInfo.inverseRightProjection == null) {
/* 1350 */           paramCanvasInfo.inverseRightProjection = new Transform3D();
/*      */         }
/* 1352 */         paramCanvasInfo.inverseRightProjection.invert(paramCanvasInfo.rightProjection);
/*      */       } 
/* 1354 */       paramCanvasInfo.updateInverseProjection = false;
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
/*      */   public void getInverseViewPlatformProjection(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1386 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getInverseViewPlatformProjection", true);
/*      */ 
/*      */     
/* 1389 */     getInverseViewPlatformProjection(canvasInfo1);
/* 1390 */     paramTransform3D1.set(canvasInfo1.inverseViewPlatformProjection);
/* 1391 */     if (canvasInfo1.useStereo & ((paramTransform3D2 != null) ? 1 : 0))
/* 1392 */       paramTransform3D2.set(canvasInfo1.inverseViewPlatformRightProjection); 
/*      */   }
/*      */   
/*      */   private void getInverseViewPlatformProjection(CanvasInfo paramCanvasInfo) {
/* 1396 */     if (paramCanvasInfo.updateInverseViewPlatformProjection) {
/*      */       
/* 1398 */       if (paramCanvasInfo.inverseViewPlatformProjection == null) {
/* 1399 */         paramCanvasInfo.inverseViewPlatformProjection = new Transform3D();
/*      */       }
/* 1401 */       getInverseProjection(paramCanvasInfo);
/* 1402 */       getEyeToViewPlatform(paramCanvasInfo);
/* 1403 */       paramCanvasInfo.inverseViewPlatformProjection.mul(paramCanvasInfo.eyeToViewPlatform, paramCanvasInfo.inverseProjection);
/*      */ 
/*      */       
/* 1406 */       if (paramCanvasInfo.useStereo) {
/* 1407 */         if (paramCanvasInfo.inverseViewPlatformRightProjection == null) {
/* 1408 */           paramCanvasInfo.inverseViewPlatformRightProjection = new Transform3D();
/*      */         }
/* 1410 */         paramCanvasInfo.inverseViewPlatformRightProjection.mul(paramCanvasInfo.rightEyeToViewPlatform, paramCanvasInfo.inverseRightProjection);
/*      */       } 
/*      */       
/* 1413 */       paramCanvasInfo.updateInverseVworldProjection = false;
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
/*      */   public void getInverseVworldProjection(Canvas3D paramCanvas3D, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 1442 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getInverseVworldProjection", true);
/* 1443 */     getInverseVworldProjection(canvasInfo1);
/* 1444 */     paramTransform3D1.set(canvasInfo1.inverseVworldProjection);
/* 1445 */     if (canvasInfo1.useStereo & ((paramTransform3D2 != null) ? 1 : 0))
/* 1446 */       paramTransform3D2.set(canvasInfo1.inverseVworldRightProjection); 
/*      */   }
/*      */   
/*      */   private void getInverseVworldProjection(CanvasInfo paramCanvasInfo) {
/* 1450 */     if (paramCanvasInfo.updateInverseVworldProjection) {
/*      */       
/* 1452 */       if (paramCanvasInfo.inverseVworldProjection == null) {
/* 1453 */         paramCanvasInfo.inverseVworldProjection = new Transform3D();
/*      */       }
/* 1455 */       getInverseViewPlatformProjection(paramCanvasInfo);
/* 1456 */       paramCanvasInfo.inverseVworldProjection.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.inverseViewPlatformProjection);
/*      */ 
/*      */       
/* 1459 */       if (paramCanvasInfo.useStereo) {
/* 1460 */         if (paramCanvasInfo.inverseVworldRightProjection == null) {
/* 1461 */           paramCanvasInfo.inverseVworldRightProjection = new Transform3D();
/*      */         }
/* 1463 */         paramCanvasInfo.inverseVworldRightProjection.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.inverseViewPlatformRightProjection);
/*      */       } 
/*      */ 
/*      */       
/* 1467 */       paramCanvasInfo.updateInverseVworldProjection = false;
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
/*      */   private void computeProjection(CanvasInfo paramCanvasInfo, Point3d paramPoint3d, double paramDouble1, double paramDouble2, Transform3D paramTransform3D) {
/* 1480 */     double d1 = paramCanvasInfo.canvasX - paramPoint3d.x;
/* 1481 */     double d2 = paramCanvasInfo.canvasY - paramPoint3d.y;
/* 1482 */     double d3 = paramCanvasInfo.canvasX + paramCanvasInfo.canvasWidth - paramPoint3d.x;
/* 1483 */     double d4 = paramCanvasInfo.canvasY + paramCanvasInfo.canvasHeight - paramPoint3d.y;
/* 1484 */     double d5 = paramDouble1 - paramPoint3d.z;
/* 1485 */     double d6 = paramDouble2 - paramPoint3d.z;
/* 1486 */     double d7 = -paramPoint3d.z;
/*      */     
/* 1488 */     if (this.projectionPolicy == 1) {
/* 1489 */       computePerspectiveProjection(d1, d2, d3, d4, d7, d5, d6, this.m16d);
/*      */     } else {
/* 1491 */       computeParallelProjection(d1, d2, d3, d4, d5, d6, this.m16d);
/*      */     } 
/* 1493 */     paramTransform3D.set(this.m16d);
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
/*      */   private void computePerspectiveProjection(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double[] paramArrayOfDouble) {
/* 1585 */     double d1 = 1.0D / (paramDouble3 - paramDouble1);
/* 1586 */     double d2 = 1.0D / (paramDouble4 - paramDouble2);
/* 1587 */     double d3 = 1.0D / (paramDouble6 - paramDouble7);
/*      */     
/* 1589 */     paramArrayOfDouble[0] = -2.0D * paramDouble5 * d1;
/* 1590 */     paramArrayOfDouble[5] = -2.0D * paramDouble5 * d2;
/* 1591 */     paramArrayOfDouble[2] = (paramDouble3 + paramDouble1) * d1;
/* 1592 */     paramArrayOfDouble[6] = (paramDouble4 + paramDouble2) * d2;
/* 1593 */     paramArrayOfDouble[10] = -(paramDouble6 + paramDouble7) * d3;
/* 1594 */     paramArrayOfDouble[11] = 2.0D * paramDouble7 * paramDouble6 * d3;
/* 1595 */     paramArrayOfDouble[14] = -1.0D;
/* 1596 */     paramArrayOfDouble[15] = 0.0D; paramArrayOfDouble[13] = 0.0D; paramArrayOfDouble[12] = 0.0D; paramArrayOfDouble[9] = 0.0D; paramArrayOfDouble[8] = 0.0D; paramArrayOfDouble[7] = 0.0D; paramArrayOfDouble[4] = 0.0D; paramArrayOfDouble[3] = 0.0D; paramArrayOfDouble[1] = 0.0D;
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
/*      */   private void computeParallelProjection(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double[] paramArrayOfDouble) {
/* 1611 */     double d1 = 1.0D / (paramDouble3 - paramDouble1);
/* 1612 */     double d2 = 1.0D / (paramDouble4 - paramDouble2);
/* 1613 */     double d3 = 1.0D / (paramDouble5 - paramDouble6);
/*      */     
/* 1615 */     paramArrayOfDouble[0] = 2.0D * d1;
/* 1616 */     paramArrayOfDouble[5] = 2.0D * d2;
/* 1617 */     paramArrayOfDouble[10] = 2.0D * d3;
/* 1618 */     paramArrayOfDouble[3] = -(paramDouble3 + paramDouble1) * d1;
/* 1619 */     paramArrayOfDouble[7] = -(paramDouble4 + paramDouble2) * d2;
/* 1620 */     paramArrayOfDouble[11] = -(paramDouble5 + paramDouble6) * d3;
/* 1621 */     paramArrayOfDouble[15] = 1.0D;
/* 1622 */     paramArrayOfDouble[14] = 0.0D; paramArrayOfDouble[13] = 0.0D; paramArrayOfDouble[12] = 0.0D; paramArrayOfDouble[9] = 0.0D; paramArrayOfDouble[8] = 0.0D; paramArrayOfDouble[6] = 0.0D; paramArrayOfDouble[4] = 0.0D; paramArrayOfDouble[2] = 0.0D; paramArrayOfDouble[1] = 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double getFrontClip(CanvasInfo paramCanvasInfo, Point3d paramPoint3d) {
/* 1629 */     if (this.frontClipPolicy == 3 || this.frontClipPolicy == 2)
/*      */     {
/* 1631 */       return paramPoint3d.z - paramCanvasInfo.frontClipDistance;
/*      */     }
/*      */     
/* 1634 */     return -paramCanvasInfo.frontClipDistance;
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
/*      */   private double getBackClip(CanvasInfo paramCanvasInfo, Point3d paramPoint3d) {
/* 1647 */     if (this.backClipPolicy == 3 || this.backClipPolicy == 2)
/*      */     {
/* 1649 */       return paramPoint3d.z - paramCanvasInfo.backClipDistance;
/*      */     }
/*      */     
/* 1652 */     return -paramCanvasInfo.backClipDistance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double getClipScale(CanvasInfo paramCanvasInfo, int paramInt) {
/* 1660 */     if (paramInt == 2 || paramInt == 0) {
/*      */       
/* 1662 */       getScreenScale(paramCanvasInfo);
/* 1663 */       if (this.resizePolicy == 1) {
/* 1664 */         return this.vpi.vworldToViewPlatformScale * paramCanvasInfo.screenScale * paramCanvasInfo.windowScale;
/*      */       }
/*      */       
/* 1667 */       return this.vpi.vworldToViewPlatformScale * paramCanvasInfo.screenScale;
/*      */     } 
/*      */     
/* 1670 */     if (this.resizePolicy == 1) {
/* 1671 */       return paramCanvasInfo.windowScale;
/*      */     }
/* 1673 */     return 1.0D;
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
/*      */   public double getPhysicalFrontClipDistance(Canvas3D paramCanvas3D) {
/* 1704 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPhysicalFrontClipDistance", true);
/*      */ 
/*      */     
/* 1707 */     getClipDistances(canvasInfo1);
/* 1708 */     return canvasInfo1.frontClipDistance;
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
/*      */   public double getPhysicalBackClipDistance(Canvas3D paramCanvas3D) {
/* 1738 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPhysicalBackClipDistance", true);
/* 1739 */     getClipDistances(canvasInfo1);
/* 1740 */     return canvasInfo1.backClipDistance;
/*      */   }
/*      */   
/*      */   private void getClipDistances(CanvasInfo paramCanvasInfo) {
/* 1744 */     if (paramCanvasInfo.updateClipDistances) {
/*      */ 
/*      */       
/* 1747 */       paramCanvasInfo.frontClipDistance = this.view.getFrontClipDistance() * getClipScale(paramCanvasInfo, this.frontClipPolicy);
/*      */ 
/*      */       
/* 1750 */       paramCanvasInfo.backClipDistance = this.view.getBackClipDistance() * getClipScale(paramCanvasInfo, this.backClipPolicy);
/*      */ 
/*      */       
/* 1753 */       paramCanvasInfo.updateClipDistances = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getScreenScale(CanvasInfo paramCanvasInfo) {
/* 1764 */     if (paramCanvasInfo.updateScreenScale) {
/*      */ 
/*      */       
/* 1767 */       if (this.scalePolicy == 0) {
/* 1768 */         paramCanvasInfo.screenScale = paramCanvasInfo.si.screenWidth / 2.0D;
/*      */       } else {
/* 1770 */         paramCanvasInfo.screenScale = this.view.getScreenScale();
/*      */       } 
/* 1772 */       paramCanvasInfo.updateScreenScale = false;
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
/*      */   public double getPhysicalToViewPlatformScale(Canvas3D paramCanvas3D) {
/* 1794 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPhysicalToViewPlatformScale", false);
/*      */ 
/*      */     
/* 1797 */     getPhysicalToViewPlatformScale(canvasInfo1);
/* 1798 */     return canvasInfo1.physicalToVpScale;
/*      */   }
/*      */   
/*      */   private void getPhysicalToViewPlatformScale(CanvasInfo paramCanvasInfo) {
/* 1802 */     if (paramCanvasInfo.updatePhysicalToVpScale) {
/*      */ 
/*      */       
/* 1805 */       getScreenScale(paramCanvasInfo);
/* 1806 */       if (this.resizePolicy == 1) {
/* 1807 */         paramCanvasInfo.physicalToVpScale = 1.0D / paramCanvasInfo.screenScale * paramCanvasInfo.windowScale;
/*      */       } else {
/* 1809 */         paramCanvasInfo.physicalToVpScale = 1.0D / paramCanvasInfo.screenScale;
/*      */       } 
/* 1811 */       paramCanvasInfo.updatePhysicalToVpScale = false;
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
/*      */   public double getPhysicalToVirtualScale(Canvas3D paramCanvas3D) {
/* 1834 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPhysicalToVirtualScale", true);
/* 1835 */     getPhysicalToVirtualScale(canvasInfo1);
/* 1836 */     return canvasInfo1.physicalToVirtualScale;
/*      */   }
/*      */   
/*      */   private void getPhysicalToVirtualScale(CanvasInfo paramCanvasInfo) {
/* 1840 */     if (paramCanvasInfo.updatePhysicalToVirtualScale) {
/*      */ 
/*      */ 
/*      */       
/* 1844 */       getPhysicalToViewPlatformScale(paramCanvasInfo);
/* 1845 */       paramCanvasInfo.physicalToVirtualScale = paramCanvasInfo.physicalToVpScale / this.vpi.vworldToViewPlatformScale;
/*      */ 
/*      */       
/* 1848 */       paramCanvasInfo.updatePhysicalToVirtualScale = false;
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
/*      */   public double getPhysicalWidth(Canvas3D paramCanvas3D) {
/* 1866 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPhysicalWidth", false);
/* 1867 */     return canvasInfo1.canvasWidth;
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
/*      */   public double getPhysicalHeight(Canvas3D paramCanvas3D) {
/* 1882 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPhysicalHeight", false);
/* 1883 */     return canvasInfo1.canvasHeight;
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
/*      */   public void getPhysicalLocation(Canvas3D paramCanvas3D, Point3d paramPoint3d) {
/* 1900 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPhysicalLocation", false);
/* 1901 */     paramPoint3d.set(canvasInfo1.canvasX, canvasInfo1.canvasY, 0.0D);
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
/*      */   public void getPixelLocationInImagePlate(Canvas3D paramCanvas3D, int paramInt1, int paramInt2, Point3d paramPoint3d) {
/* 1919 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getPixelLocationInImagePlate", false);
/*      */ 
/*      */     
/* 1922 */     paramPoint3d.set(canvasInfo1.canvasX + paramInt1 * canvasInfo1.si.metersPerPixelX, canvasInfo1.canvasY - paramInt2 * canvasInfo1.si.metersPerPixelY + canvasInfo1.canvasHeight, 0.0D);
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
/*      */   public void getSensorToVworld(Canvas3D paramCanvas3D, Sensor paramSensor, Transform3D paramTransform3D) {
/* 1945 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getSensorToVworld", true);
/* 1946 */     getTrackerBaseToVworld(canvasInfo1);
/* 1947 */     paramSensor.getRead(paramTransform3D);
/* 1948 */     paramTransform3D.mul(canvasInfo1.trackerBaseToVworld, paramTransform3D);
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
/*      */   public void getTrackerBaseToViewPlatform(Canvas3D paramCanvas3D, Transform3D paramTransform3D) {
/* 1965 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getTrackerBaseToViewPlatform", false);
/*      */ 
/*      */     
/* 1968 */     getTrackerBaseToViewPlatform(canvasInfo1);
/* 1969 */     paramTransform3D.set(canvasInfo1.trackerBaseToViewPlatform);
/*      */   }
/*      */   
/*      */   private void getTrackerBaseToViewPlatform(CanvasInfo paramCanvasInfo) {
/* 1973 */     if (paramCanvasInfo.updateTrackerBaseToViewPlatform) {
/*      */       
/* 1975 */       if (paramCanvasInfo.trackerBaseToViewPlatform == null) {
/* 1976 */         paramCanvasInfo.trackerBaseToViewPlatform = new Transform3D();
/*      */       }
/* 1978 */       getViewPlatformToCoexistence(paramCanvasInfo);
/* 1979 */       paramCanvasInfo.trackerBaseToViewPlatform.mul(this.coeToTrackerBase, paramCanvasInfo.viewPlatformToCoe);
/*      */ 
/*      */       
/* 1982 */       paramCanvasInfo.trackerBaseToViewPlatform.invert();
/* 1983 */       paramCanvasInfo.updateTrackerBaseToViewPlatform = false;
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
/*      */   public void getTrackerBaseToVworld(Canvas3D paramCanvas3D, Transform3D paramTransform3D) {
/* 2006 */     CanvasInfo canvasInfo1 = updateCache(paramCanvas3D, "getTrackerBaseToVworld", true);
/* 2007 */     getTrackerBaseToVworld(canvasInfo1);
/* 2008 */     paramTransform3D.set(canvasInfo1.trackerBaseToVworld);
/*      */   }
/*      */   
/*      */   private void getTrackerBaseToVworld(CanvasInfo paramCanvasInfo) {
/* 2012 */     if (paramCanvasInfo.updateTrackerBaseToVworld) {
/*      */       
/* 2014 */       if (paramCanvasInfo.trackerBaseToVworld == null) {
/* 2015 */         paramCanvasInfo.trackerBaseToVworld = new Transform3D();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2023 */       getTrackerBaseToViewPlatform(paramCanvasInfo);
/* 2024 */       paramCanvasInfo.trackerBaseToVworld.mul(this.vpi.viewPlatformToVworld, paramCanvasInfo.trackerBaseToViewPlatform);
/*      */ 
/*      */       
/* 2027 */       paramCanvasInfo.updateTrackerBaseToVworld = false;
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
/*      */   public static void clear() {
/* 2043 */     Iterator iterator = staticVpMap.values().iterator();
/* 2044 */     for (; iterator.hasNext(); ((ViewPlatformInfo)iterator.next()).clear());
/* 2045 */     staticVpMap.clear();
/*      */     
/* 2047 */     iterator = staticSiMap.values().iterator();
/* 2048 */     for (; iterator.hasNext(); ((ScreenInfo)iterator.next()).clear());
/* 2049 */     staticSiMap.clear();
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
/*      */   public void updateScreen(Screen3D paramScreen3D) {
/* 2062 */     ScreenInfo screenInfo = (ScreenInfo)this.screenMap.get(paramScreen3D);
/* 2063 */     if (screenInfo != null) screenInfo.updateScreen = true;
/*      */   
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
/*      */   public void updateCanvas(Canvas3D paramCanvas3D) {
/* 2077 */     CanvasInfo canvasInfo1 = (CanvasInfo)this.canvasMap.get(paramCanvas3D);
/* 2078 */     if (canvasInfo1 != null) canvasInfo1.updateCanvas = true;
/*      */   
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
/* 2107 */   public void updateView() { this.updateView = true; }
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
/* 2118 */   public void updateHead() { this.updateHead = true; }
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
/* 2134 */   public void updateViewPlatform() { this.vpi.updateViewPlatformToVworld = true; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getAutoUpdate(CanvasInfo paramCanvasInfo) {
/* 2142 */     if ((this.autoUpdateFlags & true) != 0) {
/* 2143 */       paramCanvasInfo.si.updateScreen = true;
/*      */     }
/* 2145 */     if ((this.autoUpdateFlags & 0x2) != 0) {
/* 2146 */       paramCanvasInfo.updateCanvas = true;
/*      */     }
/* 2148 */     if ((this.autoUpdateFlags & 0x10) != 0) {
/* 2149 */       this.vpi.updateViewPlatformToVworld = true;
/*      */     }
/* 2151 */     if ((this.autoUpdateFlags & 0x8) != 0) {
/* 2152 */       this.updateHead = true;
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
/*      */   private CanvasInfo updateCache(Canvas3D paramCanvas3D, String paramString, boolean paramBoolean) {
/* 2169 */     if (this.updateView || (this.autoUpdateFlags & 0x4) != 0) {
/* 2170 */       getViewInfo();
/*      */     }
/*      */     
/* 2173 */     CanvasInfo canvasInfo1 = (CanvasInfo)this.canvasMap.get(paramCanvas3D);
/* 2174 */     if (canvasInfo1 == null) {
/* 2175 */       throw new IllegalArgumentException("Specified Canvas3D is not a component of the View");
/*      */     }
/*      */ 
/*      */     
/* 2179 */     if (this.autoUpdate) getAutoUpdate(canvasInfo1);
/*      */ 
/*      */     
/* 2182 */     if (canvasInfo1.si.updateScreen) {
/* 2183 */       canvasInfo1.si.getScreenInfo();
/*      */     }
/* 2185 */     if (canvasInfo1.updateCanvas) {
/* 2186 */       canvasInfo1.getCanvasInfo();
/*      */     }
/* 2188 */     if (paramBoolean && this.vpi.updateViewPlatformToVworld) {
/* 2189 */       this.vpi.getViewPlatformToVworld();
/*      */     }
/* 2191 */     if (this.useTracking && this.updateHead) {
/* 2192 */       getHeadInfo();
/*      */     }
/*      */     
/* 2195 */     return canvasInfo1;
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
/*      */   private void getViewInfo() {
/* 2208 */     if (this.canvasCount != this.view.numCanvas3Ds()) {
/* 2209 */       this.canvasCount = this.view.numCanvas3Ds();
/* 2210 */       getCanvases();
/*      */     } else {
/*      */       
/* 2213 */       for (byte b1 = 0; b1 < this.canvasCount; b1++) {
/* 2214 */         if (this.canvasMap.get(this.view.getCanvas3D(b1)) != this.canvasInfo[b1]) {
/* 2215 */           getCanvases();
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2222 */     getViewPlatform();
/*      */ 
/*      */     
/* 2225 */     this.body = this.view.getPhysicalBody();
/* 2226 */     this.env = this.view.getPhysicalEnvironment();
/*      */ 
/*      */ 
/*      */     
/* 2230 */     this.useTracking = useHeadTracking();
/*      */ 
/*      */     
/* 2233 */     if (this.view.getTrackingEnable() && this.env.getTrackingAvailable()) {
/* 2234 */       int i = this.env.getHeadIndex();
/* 2235 */       this.headTracker = this.env.getSensor(i);
/*      */     } 
/*      */ 
/*      */     
/* 2239 */     this.viewPolicy = this.view.getViewPolicy();
/* 2240 */     this.projectionPolicy = this.view.getProjectionPolicy();
/* 2241 */     this.resizePolicy = this.view.getWindowResizePolicy();
/* 2242 */     this.movementPolicy = this.view.getWindowMovementPolicy();
/* 2243 */     this.eyePolicy = this.view.getWindowEyepointPolicy();
/* 2244 */     this.scalePolicy = this.view.getScreenScalePolicy();
/* 2245 */     this.backClipPolicy = this.view.getBackClipPolicy();
/* 2246 */     this.frontClipPolicy = this.view.getFrontClipPolicy();
/*      */     
/* 2248 */     if (this.useTracking || this.viewPolicy == 1) {
/* 2249 */       if (this.headToHeadTracker == null)
/* 2250 */         this.headToHeadTracker = new Transform3D(); 
/* 2251 */       if (this.headTrackerToTrackerBase == null) {
/* 2252 */         this.headTrackerToTrackerBase = new Transform3D();
/*      */       }
/* 2254 */       if (this.viewPolicy == 1) {
/* 2255 */         if (this.trackerBaseToHeadTracker == null)
/* 2256 */           this.trackerBaseToHeadTracker = new Transform3D(); 
/* 2257 */         if (this.coeToHeadTracker == null) {
/* 2258 */           this.coeToHeadTracker = new Transform3D();
/*      */         }
/*      */       }
/* 2261 */       else if (this.headToTrackerBase == null) {
/* 2262 */         this.headToTrackerBase = new Transform3D();
/*      */       } 
/*      */       
/* 2265 */       this.body.getLeftEyePosition(this.leftEyeInHead);
/* 2266 */       this.body.getRightEyePosition(this.rightEyeInHead);
/* 2267 */       this.body.getHeadToHeadTracker(this.headToHeadTracker);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2276 */     if (this.eyePolicy == 1 || this.eyePolicy == 2) {
/*      */       
/* 2278 */       this.body.getLeftEyePosition(this.leftEyeInHead);
/* 2279 */       this.body.getRightEyePosition(this.rightEyeInHead);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2286 */     if (this.env.getCoexistenceCenterInPworldPolicy() != 2 || this.viewPolicy == 1) {
/*      */       
/* 2288 */       this.coeCentering = false;
/*      */     } else {
/* 2290 */       this.coeCentering = this.view.getCoexistenceCenteringEnable();
/*      */     } 
/* 2292 */     if (!this.coeCentering || this.useTracking) {
/* 2293 */       if (this.coeToTrackerBase == null) {
/* 2294 */         this.coeToTrackerBase = new Transform3D();
/*      */       }
/* 2296 */       this.env.getCoexistenceToTrackerBase(this.coeToTrackerBase);
/*      */     } 
/*      */ 
/*      */     
/* 2300 */     if (this.backClipPolicy == 2 || this.backClipPolicy == 0 || this.frontClipPolicy == 2 || this.frontClipPolicy == 0) {
/*      */ 
/*      */ 
/*      */       
/* 2304 */       this.clipVirtual = true;
/*      */     } else {
/*      */       
/* 2307 */       this.clipVirtual = false;
/*      */     } 
/*      */ 
/*      */     
/* 2311 */     for (byte b = 0; b < this.canvasCount; b++) {
/* 2312 */       this.canvasInfo[b].updateViewDependencies();
/*      */     }
/* 2314 */     this.updateView = false;
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
/*      */   private void getCanvases() {
/* 2330 */     if (this.canvasInfo.length < this.canvasCount) {
/* 2331 */       this.canvasInfo = new CanvasInfo[this.canvasCount];
/*      */     }
/*      */     int i;
/* 2334 */     for (i = 0; i < this.canvasCount; i++) {
/* 2335 */       Canvas3D canvas3D = this.view.getCanvas3D(i);
/* 2336 */       Screen3D screen3D = canvas3D.getScreen3D();
/*      */ 
/*      */       
/* 2339 */       ScreenInfo screenInfo = (ScreenInfo)this.screenMap.get(screen3D);
/* 2340 */       if (screenInfo == null) {
/* 2341 */         screenInfo = new ScreenInfo(screen3D, canvas3D.getGraphicsConfiguration(), null);
/* 2342 */         this.screenMap.put(screen3D, screenInfo);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2348 */       if (this.newSet.add(screenInfo)) screenInfo.clear(this);
/*      */ 
/*      */       
/* 2351 */       CanvasInfo canvasInfo1 = (CanvasInfo)this.canvasMap.get(canvas3D);
/* 2352 */       if (canvasInfo1 == null) canvasInfo1 = new CanvasInfo(canvas3D, screenInfo, null);
/*      */ 
/*      */       
/* 2355 */       screenInfo.addCanvasInfo(this, canvasInfo1);
/*      */ 
/*      */       
/* 2358 */       this.newMap.put(canvas3D, canvasInfo1);
/* 2359 */       this.canvasInfo[i] = canvasInfo1;
/*      */     } 
/*      */ 
/*      */     
/* 2363 */     for (i = this.canvasCount; i < this.canvasInfo.length; i++) {
/* 2364 */       this.canvasInfo[i] = null;
/*      */     }
/*      */     
/* 2367 */     Map map = this.canvasMap;
/* 2368 */     this.canvasMap = this.newMap;
/* 2369 */     this.newMap = map;
/*      */ 
/*      */     
/* 2372 */     this.newMap.clear();
/* 2373 */     this.newSet.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void clearCanvases() {
/* 2381 */     this.canvasCount = 0;
/* 2382 */     this.canvasMap.clear();
/* 2383 */     this.updateView = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getViewPlatform() {
/* 2391 */     ViewPlatform viewPlatform = this.view.getViewPlatform();
/* 2392 */     if (viewPlatform == null) {
/* 2393 */       throw new IllegalStateException("The View must be attached to a ViewPlatform");
/*      */     }
/*      */     
/* 2396 */     ViewPlatformInfo viewPlatformInfo = (ViewPlatformInfo)this.viewPlatformMap.get(viewPlatform);
/*      */ 
/*      */     
/* 2399 */     if (viewPlatformInfo == null) {
/*      */       
/* 2401 */       viewPlatformInfo = new ViewPlatformInfo(viewPlatform, null);
/* 2402 */       this.viewPlatformMap.put(viewPlatform, viewPlatformInfo);
/*      */     } 
/*      */     
/* 2405 */     if (this.vpi != viewPlatformInfo) {
/*      */ 
/*      */       
/* 2408 */       if (this.vpi != null)
/*      */       {
/*      */         
/* 2411 */         this.vpi.removeViewInfo(this);
/*      */       }
/* 2413 */       this.vpi = viewPlatformInfo;
/* 2414 */       this.vpi.addViewInfo(this);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2419 */       if (viewPlatform.getCapability(11)) {
/* 2420 */         this.vpi.updateViewPlatformToVworld = true;
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
/* 2432 */   private void clearViewPlatform() { this.updateView = true; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateVworldDependencies() {
/* 2440 */     for (byte b = 0; b < this.canvasCount; b++) {
/* 2441 */       this.canvasInfo[b].updateVworldDependencies();
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
/*      */   protected Transform3D getHeadTrackerToTrackerBase() {
/* 2464 */     this.headTracker.getRead(this.headTrackerToTrackerBase);
/* 2465 */     return this.headTrackerToTrackerBase;
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
/* 2481 */   protected boolean useHeadTracking() { return (this.view.getTrackingEnable() && this.env.getTrackingAvailable()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getHeadInfo() {
/* 2490 */     this.headTrackerToTrackerBase = getHeadTrackerToTrackerBase();
/* 2491 */     if (this.viewPolicy == 1) {
/* 2492 */       this.trackerBaseToHeadTracker.invert(this.headTrackerToTrackerBase);
/* 2493 */       this.coeToHeadTracker.mul(this.trackerBaseToHeadTracker, this.coeToTrackerBase);
/*      */     }
/*      */     else {
/*      */       
/* 2497 */       this.headToTrackerBase.mul(this.headTrackerToTrackerBase, this.headToHeadTracker);
/*      */     } 
/*      */     
/* 2500 */     for (byte b = 0; b < this.canvasCount; b++) {
/* 2501 */       this.canvasInfo[b].updateHeadDependencies();
/*      */     }
/* 2503 */     this.updateHead = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class ScreenInfo
/*      */   {
/*      */     private Screen3D s3d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private GraphicsConfiguration graphicsConfiguration;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean updateScreen;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Map viewInfoMap;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private List viewInfoList;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Transform3D t3d;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private double screenWidth;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private double screenHeight;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean updateScreenSize;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Rectangle screenBounds;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private double metersPerPixelX;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private double metersPerPixelY;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean updatePixelSize;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Transform3D trackerBaseToPlate;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Transform3D headTrackerToLeftPlate;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Transform3D headTrackerToRightPlate;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean updateTrackerBaseToPlate;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean updateHeadTrackerToPlate;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ScreenInfo(Screen3D param1Screen3D, GraphicsConfiguration param1GraphicsConfiguration) {
/* 2607 */       this.s3d = null;
/* 2608 */       this.graphicsConfiguration = null;
/* 2609 */       this.updateScreen = true;
/*      */       
/* 2611 */       this.viewInfoMap = new HashMap();
/* 2612 */       this.viewInfoList = new LinkedList();
/* 2613 */       this.t3d = new Transform3D();
/*      */       
/* 2615 */       this.screenWidth = 0.0D;
/* 2616 */       this.screenHeight = 0.0D;
/* 2617 */       this.updateScreenSize = true;
/*      */       
/* 2619 */       this.screenBounds = null;
/* 2620 */       this.metersPerPixelX = 0.0D;
/* 2621 */       this.metersPerPixelY = 0.0D;
/* 2622 */       this.updatePixelSize = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2629 */       this.trackerBaseToPlate = new Transform3D();
/* 2630 */       this.headTrackerToLeftPlate = new Transform3D();
/* 2631 */       this.headTrackerToRightPlate = new Transform3D();
/* 2632 */       this.updateTrackerBaseToPlate = false;
/* 2633 */       this.updateHeadTrackerToPlate = false;
/*      */ 
/*      */       
/* 2636 */       this.s3d = param1Screen3D;
/* 2637 */       this.graphicsConfiguration = param1GraphicsConfiguration;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private List getCanvasList(ViewInfo param1ViewInfo) {
/* 2643 */       List list = (List)this.viewInfoMap.get(param1ViewInfo);
/* 2644 */       if (list == null) {
/* 2645 */         list = new LinkedList();
/* 2646 */         this.viewInfoMap.put(param1ViewInfo, list);
/* 2647 */         this.viewInfoList.add(list);
/*      */       } 
/* 2649 */       return list;
/*      */     }
/*      */ 
/*      */     
/* 2653 */     private void clear(ViewInfo param1ViewInfo) { getCanvasList(param1ViewInfo).clear(); }
/*      */ 
/*      */     
/*      */     private void clear() {
/* 2657 */       Iterator iterator = this.viewInfoMap.keySet().iterator();
/* 2658 */       for (; iterator.hasNext(); ((ViewInfo)iterator.next()).clearCanvases());
/* 2659 */       this.viewInfoMap.clear();
/*      */       
/* 2661 */       iterator = this.viewInfoList.iterator();
/* 2662 */       for (; iterator.hasNext(); ((List)iterator.next()).clear());
/* 2663 */       this.viewInfoList.clear();
/*      */     }
/*      */ 
/*      */     
/* 2667 */     private void addCanvasInfo(ViewInfo param1ViewInfo, ViewInfo.CanvasInfo param1CanvasInfo) { getCanvasList(param1ViewInfo).add(param1CanvasInfo); }
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
/*      */     private void getScreenInfo() {
/* 2686 */       this.s3d.getTrackerBaseToImagePlate(this.t3d);
/* 2687 */       if (!this.t3d.equals(this.trackerBaseToPlate)) {
/* 2688 */         this.trackerBaseToPlate.set(this.t3d);
/* 2689 */         this.updateTrackerBaseToPlate = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2697 */       this.s3d.getHeadTrackerToLeftImagePlate(this.t3d);
/* 2698 */       if (!this.t3d.equals(this.headTrackerToLeftPlate)) {
/* 2699 */         this.headTrackerToLeftPlate.set(this.t3d);
/* 2700 */         this.updateHeadTrackerToPlate = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2705 */       this.s3d.getHeadTrackerToRightImagePlate(this.t3d);
/* 2706 */       if (!this.t3d.equals(this.headTrackerToRightPlate)) {
/* 2707 */         this.headTrackerToRightPlate.set(this.t3d);
/* 2708 */         this.updateHeadTrackerToPlate = true;
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
/* 2720 */       double d1 = this.s3d.getPhysicalScreenWidth();
/* 2721 */       double d2 = this.s3d.getPhysicalScreenHeight();
/* 2722 */       if (d1 != this.screenWidth || d2 != this.screenHeight) {
/* 2723 */         this.screenWidth = d1;
/* 2724 */         this.screenHeight = d2;
/* 2725 */         this.updateScreenSize = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2732 */       GraphicsConfiguration graphicsConfiguration1 = this.graphicsConfiguration;
/*      */ 
/*      */       
/* 2735 */       if (graphicsConfiguration1 == null) {
/* 2736 */         graphicsConfiguration1 = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
/*      */       }
/*      */       
/* 2739 */       this.screenBounds = graphicsConfiguration1.getBounds();
/* 2740 */       double d3 = this.screenWidth / this.screenBounds.width;
/* 2741 */       double d4 = this.screenHeight / this.screenBounds.height;
/* 2742 */       if (d3 != this.metersPerPixelX || d4 != this.metersPerPixelY) {
/* 2743 */         this.metersPerPixelX = d3;
/* 2744 */         this.metersPerPixelY = d4;
/* 2745 */         this.updatePixelSize = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2754 */       Iterator iterator = this.viewInfoList.iterator();
/* 2755 */       while (iterator.hasNext()) {
/* 2756 */         Iterator iterator1 = ((List)iterator.next()).iterator();
/* 2757 */         while (iterator1.hasNext()) {
/* 2758 */           ((ViewInfo.CanvasInfo)iterator1.next()).updateScreenDependencies();
/*      */         }
/*      */       } 
/* 2761 */       this.updateTrackerBaseToPlate = false;
/* 2762 */       this.updateHeadTrackerToPlate = false;
/* 2763 */       this.updateScreenSize = false;
/* 2764 */       this.updatePixelSize = false;
/* 2765 */       this.updateScreen = false;
/*      */     } }
/*      */   
/*      */   private static class ViewPlatformInfo { private ViewPlatform vp;
/*      */     private List viewInfo;
/*      */     private double[] m;
/*      */     private Transform3D viewPlatformToVworld;
/*      */     private Transform3D vworldToViewPlatform;
/*      */     private double vworldToViewPlatformScale;
/*      */     private boolean updateViewPlatformToVworld;
/*      */     private boolean updateVworldScale;
/*      */     
/*      */     private ViewPlatformInfo(ViewPlatform param1ViewPlatform) {
/* 2778 */       this.vp = null;
/* 2779 */       this.viewInfo = new LinkedList();
/* 2780 */       this.m = new double[16];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2788 */       this.viewPlatformToVworld = new Transform3D();
/* 2789 */       this.vworldToViewPlatform = new Transform3D();
/* 2790 */       this.vworldToViewPlatformScale = 1.0D;
/*      */ 
/*      */ 
/*      */       
/* 2794 */       this.updateViewPlatformToVworld = false;
/* 2795 */       this.updateVworldScale = false;
/*      */ 
/*      */       
/* 2798 */       this.vp = param1ViewPlatform;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2804 */     private void addViewInfo(ViewInfo param1ViewInfo) { this.viewInfo.add(param1ViewInfo); }
/*      */ 
/*      */ 
/*      */     
/* 2808 */     private void removeViewInfo(ViewInfo param1ViewInfo) { this.viewInfo.remove(param1ViewInfo); }
/*      */ 
/*      */     
/*      */     private void clear() {
/* 2812 */       Iterator iterator = this.viewInfo.iterator();
/* 2813 */       for (; iterator.hasNext(); ((ViewInfo)iterator.next()).clearViewPlatform());
/* 2814 */       this.viewInfo.clear();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void getViewPlatformToVworld() {
/* 2825 */       this.vp.getLocalToVworld(this.viewPlatformToVworld);
/* 2826 */       this.vworldToViewPlatform.invert(this.viewPlatformToVworld);
/*      */ 
/*      */ 
/*      */       
/* 2830 */       this.vworldToViewPlatform.get(this.m);
/* 2831 */       double d = Math.sqrt(this.m[0] * this.m[0] + this.m[1] * this.m[1] + this.m[2] * this.m[2]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2838 */       if (d > this.vworldToViewPlatformScale + 1.0E-7D || d < this.vworldToViewPlatformScale - 1.0E-7D) {
/*      */         
/* 2840 */         this.vworldToViewPlatformScale = d;
/* 2841 */         this.updateVworldScale = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2847 */       Iterator iterator = this.viewInfo.iterator();
/* 2848 */       while (iterator.hasNext()) {
/* 2849 */         ((ViewInfo)iterator.next()).updateVworldDependencies();
/*      */       }
/* 2851 */       this.updateVworldScale = false;
/* 2852 */       this.updateViewPlatformToVworld = false;
/*      */     } }
/*      */   private class CanvasInfo { private Canvas3D c3d; private ViewInfo.ScreenInfo si; private boolean updateCanvas; private double canvasX; private double canvasY; private boolean updatePosition; private double canvasWidth; private double canvasHeight; private double windowScale; private boolean updateWindowScale; private double screenScale; private boolean updateScreenScale; private boolean useStereo; private boolean updateStereo;
/*      */     private Transform3D coeToPlate;
/*      */     private Transform3D coeToRightPlate;
/*      */     private boolean updateCoeToPlate;
/*      */     
/*      */     private CanvasInfo(Canvas3D param1Canvas3D, ViewInfo.ScreenInfo param1ScreenInfo) {
/* 2860 */       this.c3d = null;
/* 2861 */       this.si = null;
/* 2862 */       this.updateCanvas = true;
/*      */       
/* 2864 */       this.canvasX = 0.0D;
/* 2865 */       this.canvasY = 0.0D;
/* 2866 */       this.updatePosition = true;
/*      */       
/* 2868 */       this.canvasWidth = 0.0D;
/* 2869 */       this.canvasHeight = 0.0D;
/* 2870 */       this.windowScale = 0.0D;
/* 2871 */       this.updateWindowScale = true;
/*      */       
/* 2873 */       this.screenScale = 0.0D;
/* 2874 */       this.updateScreenScale = true;
/*      */       
/* 2876 */       this.useStereo = false;
/* 2877 */       this.updateStereo = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2884 */       this.coeToPlate = null;
/* 2885 */       this.coeToRightPlate = null;
/* 2886 */       this.updateCoeToPlate = true;
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
/* 2898 */       this.viewPlatformToCoe = null;
/* 2899 */       this.coeToViewPlatform = null;
/* 2900 */       this.updateViewPlatformToCoe = true;
/* 2901 */       this.updateCoeToViewPlatform = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2907 */       this.plateToViewPlatform = null;
/* 2908 */       this.rightPlateToViewPlatform = null;
/* 2909 */       this.updatePlateToViewPlatform = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2915 */       this.trackerBaseToViewPlatform = null;
/* 2916 */       this.updateTrackerBaseToViewPlatform = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2925 */       this.eyeInPlate = new Point3d();
/* 2926 */       this.rightEyeInPlate = new Point3d();
/* 2927 */       this.eyeToPlate = null;
/* 2928 */       this.rightEyeToPlate = null;
/* 2929 */       this.updateEyeInPlate = true;
/*      */       
/* 2931 */       this.leftManualEyeInPlate = new Point3d();
/* 2932 */       this.rightManualEyeInPlate = new Point3d();
/* 2933 */       this.updateManualEye = true;
/*      */       
/* 2935 */       this.monoscopicPolicy = -1;
/* 2936 */       this.updateMonoPolicy = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2942 */       this.eyeToViewPlatform = null;
/* 2943 */       this.rightEyeToViewPlatform = null;
/* 2944 */       this.updateEyeToViewPlatform = true;
/*      */       
/* 2946 */       this.viewPlatformToEye = null;
/* 2947 */       this.viewPlatformToRightEye = null;
/* 2948 */       this.updateViewPlatformToEye = true;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2953 */       this.projection = null;
/* 2954 */       this.rightProjection = null;
/* 2955 */       this.updateProjection = true;
/*      */       
/* 2957 */       this.inverseProjection = null;
/* 2958 */       this.inverseRightProjection = null;
/* 2959 */       this.updateInverseProjection = true;
/*      */       
/* 2961 */       this.inverseViewPlatformProjection = null;
/* 2962 */       this.inverseViewPlatformRightProjection = null;
/* 2963 */       this.updateInverseViewPlatformProjection = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2969 */       this.frontClipDistance = 0.0D;
/* 2970 */       this.backClipDistance = 0.0D;
/* 2971 */       this.updateClipDistances = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2977 */       this.physicalToVpScale = 0.0D;
/* 2978 */       this.physicalToVirtualScale = 0.0D;
/* 2979 */       this.updatePhysicalToVpScale = true;
/* 2980 */       this.updatePhysicalToVirtualScale = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2986 */       this.plateToVworld = null;
/* 2987 */       this.rightPlateToVworld = null;
/* 2988 */       this.updatePlateToVworld = true;
/*      */       
/* 2990 */       this.coeToVworld = null;
/* 2991 */       this.updateCoeToVworld = true;
/*      */       
/* 2993 */       this.eyeToVworld = null;
/* 2994 */       this.rightEyeToVworld = null;
/* 2995 */       this.updateEyeToVworld = true;
/*      */       
/* 2997 */       this.trackerBaseToVworld = null;
/* 2998 */       this.updateTrackerBaseToVworld = true;
/*      */       
/* 3000 */       this.inverseVworldProjection = null;
/* 3001 */       this.inverseVworldRightProjection = null;
/* 3002 */       this.updateInverseVworldProjection = true;
/*      */ 
/*      */       
/* 3005 */       this.si = param1ScreenInfo;
/* 3006 */       this.c3d = param1Canvas3D;
/*      */     }
/*      */     private Transform3D viewPlatformToCoe; private Transform3D coeToViewPlatform; private boolean updateViewPlatformToCoe; private boolean updateCoeToViewPlatform; private Transform3D plateToViewPlatform; private Transform3D rightPlateToViewPlatform; private boolean updatePlateToViewPlatform; private Transform3D trackerBaseToViewPlatform; private boolean updateTrackerBaseToViewPlatform; private Point3d eyeInPlate; private Point3d rightEyeInPlate; private Transform3D eyeToPlate; private Transform3D rightEyeToPlate; private boolean updateEyeInPlate; private Point3d leftManualEyeInPlate; private Point3d rightManualEyeInPlate; private boolean updateManualEye; private int monoscopicPolicy; private boolean updateMonoPolicy; private Transform3D eyeToViewPlatform; private Transform3D rightEyeToViewPlatform; private boolean updateEyeToViewPlatform; private Transform3D viewPlatformToEye; private Transform3D viewPlatformToRightEye; private boolean updateViewPlatformToEye; private Transform3D projection; private Transform3D rightProjection; private boolean updateProjection; private Transform3D inverseProjection; private Transform3D inverseRightProjection; private boolean updateInverseProjection; private Transform3D inverseViewPlatformProjection; private Transform3D inverseViewPlatformRightProjection; private boolean updateInverseViewPlatformProjection; private double frontClipDistance; private double backClipDistance; private boolean updateClipDistances; private double physicalToVpScale; private double physicalToVirtualScale; private boolean updatePhysicalToVpScale; private boolean updatePhysicalToVirtualScale; private Transform3D plateToVworld; private Transform3D rightPlateToVworld; private boolean updatePlateToVworld; private Transform3D coeToVworld; private boolean updateCoeToVworld; private Transform3D eyeToVworld; private Transform3D rightEyeToVworld; private boolean updateEyeToVworld; private Transform3D trackerBaseToVworld; private boolean updateTrackerBaseToVworld;
/*      */     private Transform3D inverseVworldProjection;
/*      */     private Transform3D inverseVworldRightProjection;
/*      */     private boolean updateInverseVworldProjection;
/*      */     
/*      */     private void getCanvasInfo() {
/* 3014 */       boolean bool = (this.c3d.getStereoEnable() && this.c3d.getStereoAvailable()) ? 1 : 0;
/*      */ 
/*      */       
/* 3017 */       if (this.useStereo != bool) {
/* 3018 */         this.useStereo = bool;
/* 3019 */         this.updateStereo = true;
/*      */       } 
/*      */ 
/*      */       
/* 3023 */       this.canvasWidth = this.c3d.getWidth() * this.si.metersPerPixelX;
/* 3024 */       this.canvasHeight = this.c3d.getHeight() * this.si.metersPerPixelY;
/* 3025 */       double d1 = this.canvasWidth / this.si.screenWidth;
/*      */       
/* 3027 */       if (this.windowScale != d1) {
/* 3028 */         this.windowScale = d1;
/* 3029 */         this.updateWindowScale = true;
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
/* 3040 */       Point point = this.c3d.getLocationOnScreen();
/* 3041 */       int i = point.x - this.si.screenBounds.x;
/* 3042 */       int j = point.y - this.si.screenBounds.y;
/*      */       
/* 3044 */       double d2 = this.si.metersPerPixelX * i;
/* 3045 */       double d3 = this.si.metersPerPixelY * (this.si.screenBounds.height - j + this.c3d.getHeight());
/*      */ 
/*      */       
/* 3048 */       if (this.canvasX != d2 || this.canvasY != d3) {
/* 3049 */         this.canvasX = d2;
/* 3050 */         this.canvasY = d3;
/* 3051 */         this.updatePosition = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3058 */       int k = this.c3d.getMonoscopicViewPolicy();
/* 3059 */       if (this.monoscopicPolicy != k) {
/* 3060 */         this.monoscopicPolicy = k;
/* 3061 */         this.updateMonoPolicy = true;
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
/* 3073 */       this.c3d.getLeftManualEyeInImagePlate(ViewInfo.this.leftEye);
/* 3074 */       this.c3d.getRightManualEyeInImagePlate(ViewInfo.this.rightEye);
/*      */       
/* 3076 */       if (!ViewInfo.this.leftEye.equals(this.leftManualEyeInPlate) || !ViewInfo.this.rightEye.equals(this.rightManualEyeInPlate)) {
/*      */ 
/*      */         
/* 3079 */         this.leftManualEyeInPlate.set(ViewInfo.this.leftEye);
/* 3080 */         this.rightManualEyeInPlate.set(ViewInfo.this.rightEye);
/* 3081 */         this.updateManualEye = true;
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
/* 3092 */       updateCanvasDependencies();
/* 3093 */       this.updateStereo = false;
/* 3094 */       this.updateWindowScale = false;
/* 3095 */       this.updatePosition = false;
/* 3096 */       this.updateMonoPolicy = false;
/* 3097 */       this.updateManualEye = false;
/* 3098 */       this.updateCanvas = false;
/*      */     }
/*      */ 
/*      */     
/* 3102 */     private double getFieldOfViewOffset() { return 0.5D * this.canvasWidth / Math.tan(0.5D * ViewInfo.this.view.getFieldOfView()); }
/*      */ 
/*      */     
/*      */     private void updateScreenDependencies() {
/* 3106 */       if (this.si.updatePixelSize || this.si.updateScreenSize) {
/* 3107 */         if (ViewInfo.this.eyePolicy == 1 || ViewInfo.this.eyePolicy == 2)
/*      */         {
/*      */ 
/*      */           
/* 3111 */           this.updateEyeInPlate = true;
/*      */         }
/* 3113 */         if (ViewInfo.this.resizePolicy == 1 || ViewInfo.this.eyePolicy == 2)
/*      */         {
/*      */           
/* 3116 */           this.updateViewPlatformToCoe = true;
/*      */         }
/* 3118 */         if (ViewInfo.this.resizePolicy == 1) {
/*      */ 
/*      */           
/* 3121 */           this.updateClipDistances = true;
/* 3122 */           this.updatePhysicalToVpScale = true;
/* 3123 */           this.updatePhysicalToVirtualScale = true;
/*      */         } 
/*      */         
/* 3126 */         this.updateProjection = true;
/*      */       } 
/* 3128 */       if (this.si.updateScreenSize && ViewInfo.this.scalePolicy == 0) {
/*      */ 
/*      */ 
/*      */         
/* 3132 */         this.updateScreenScale = true;
/* 3133 */         this.updateClipDistances = true;
/* 3134 */         this.updatePhysicalToVpScale = true;
/* 3135 */         this.updatePhysicalToVirtualScale = true;
/* 3136 */         this.updateViewPlatformToCoe = true;
/*      */       } 
/*      */       
/* 3139 */       if (ViewInfo.this.viewPolicy == 1) {
/* 3140 */         if (this.si.updateHeadTrackerToPlate)
/*      */         {
/* 3142 */           this.updateEyeInPlate = true;
/* 3143 */           this.updateCoeToPlate = true;
/*      */         }
/*      */       
/* 3146 */       } else if (ViewInfo.this.coeCentering) {
/* 3147 */         if (ViewInfo.this.movementPolicy == 1) {
/*      */           
/* 3149 */           if (this.si.updatePixelSize || this.si.updateScreenSize)
/*      */           {
/*      */             
/* 3152 */             this.updateCoeToPlate = true;
/*      */           }
/* 3154 */         } else if (this.si.updateScreenSize) {
/*      */           
/* 3156 */           this.updateCoeToPlate = true;
/*      */         } 
/* 3158 */       } else if (this.si.updateTrackerBaseToPlate) {
/*      */ 
/*      */ 
/*      */         
/* 3162 */         this.updateCoeToPlate = true;
/*      */       } 
/*      */       
/* 3165 */       if (this.updateCoeToPlate && ViewInfo.this.eyePolicy == 3)
/*      */       {
/*      */         
/* 3168 */         this.updateEyeInPlate = true;
/*      */       }
/* 3170 */       if (this.updateViewPlatformToCoe) {
/*      */ 
/*      */         
/* 3173 */         this.updateCoeToViewPlatform = true;
/* 3174 */         this.updateCoeToVworld = true;
/* 3175 */         this.updateTrackerBaseToViewPlatform = true;
/* 3176 */         this.updateTrackerBaseToVworld = true;
/*      */       } 
/* 3178 */       if (this.updateCoeToPlate || this.updateViewPlatformToCoe) {
/*      */ 
/*      */ 
/*      */         
/* 3182 */         this.updatePlateToViewPlatform = true;
/* 3183 */         this.updatePlateToVworld = true;
/*      */       } 
/* 3185 */       updateEyeDependencies();
/*      */     }
/*      */     
/*      */     private void updateEyeDependencies() {
/* 3189 */       if (this.updateEyeInPlate) {
/* 3190 */         this.updateEyeToVworld = true;
/* 3191 */         this.updateProjection = true;
/*      */       } 
/* 3193 */       if (this.updateProjection) {
/* 3194 */         this.updateInverseProjection = true;
/* 3195 */         this.updateInverseViewPlatformProjection = true;
/* 3196 */         this.updateInverseVworldProjection = true;
/*      */       } 
/* 3198 */       if (this.updateEyeInPlate || this.updatePlateToViewPlatform) {
/* 3199 */         this.updateViewPlatformToEye = true;
/* 3200 */         this.updateEyeToViewPlatform = true;
/*      */       } 
/*      */     }
/*      */     
/*      */     private void updateCanvasDependencies() {
/* 3205 */       if (this.updateStereo || this.updateMonoPolicy || (this.updateManualEye && (ViewInfo.this.eyePolicy == 1 || ViewInfo.this.eyePolicy == 0)))
/*      */       {
/*      */         
/* 3208 */         this.updateEyeInPlate = true;
/*      */       }
/* 3210 */       if (this.updateWindowScale || this.updatePosition) {
/* 3211 */         if (ViewInfo.this.coeCentering && ViewInfo.this.movementPolicy == 1) {
/*      */           
/* 3213 */           this.updateCoeToPlate = true;
/* 3214 */           if (ViewInfo.this.eyePolicy == 3)
/* 3215 */             this.updateEyeInPlate = true; 
/*      */         } 
/* 3217 */         if (ViewInfo.this.eyePolicy == 2 || ViewInfo.this.eyePolicy == 1)
/*      */         {
/*      */           
/* 3220 */           this.updateEyeInPlate = true; } 
/*      */       } 
/* 3222 */       if (this.updateWindowScale) {
/* 3223 */         if (ViewInfo.this.resizePolicy == 1 || ViewInfo.this.eyePolicy == 2) {
/*      */ 
/*      */ 
/*      */           
/* 3227 */           this.updateViewPlatformToCoe = true;
/* 3228 */           this.updateCoeToViewPlatform = true;
/* 3229 */           this.updateCoeToVworld = true;
/* 3230 */           this.updateTrackerBaseToViewPlatform = true;
/* 3231 */           this.updateTrackerBaseToVworld = true;
/*      */         } 
/* 3233 */         if (ViewInfo.this.resizePolicy == 1) {
/*      */ 
/*      */           
/* 3236 */           this.updateClipDistances = true;
/* 3237 */           this.updateProjection = true;
/* 3238 */           this.updatePhysicalToVpScale = true;
/* 3239 */           this.updatePhysicalToVirtualScale = true;
/*      */         } 
/*      */       } 
/* 3242 */       if (this.updateViewPlatformToCoe || this.updateCoeToPlate) {
/*      */ 
/*      */ 
/*      */         
/* 3246 */         this.updatePlateToViewPlatform = true;
/* 3247 */         this.updatePlateToVworld = true;
/*      */       } 
/* 3249 */       if (ViewInfo.this.coeCentering && !this.updateManualEye && !this.updateWindowScale && ViewInfo.this.movementPolicy == 1 && ViewInfo.this.eyePolicy != 0) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3259 */       updateEyeDependencies();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void updateViewDependencies() {
/* 3270 */       this.updateEyeInPlate = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3277 */       this.updateProjection = true;
/* 3278 */       this.updateClipDistances = true;
/* 3279 */       this.updatePhysicalToVpScale = true;
/* 3280 */       this.updatePhysicalToVirtualScale = true;
/*      */ 
/*      */ 
/*      */       
/* 3284 */       this.updateCoeToPlate = true;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3289 */       this.updateViewPlatformToCoe = true;
/* 3290 */       this.updateCoeToViewPlatform = true;
/* 3291 */       this.updateCoeToVworld = true;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3296 */       this.updatePlateToViewPlatform = true;
/* 3297 */       this.updatePlateToVworld = true;
/*      */ 
/*      */ 
/*      */       
/* 3301 */       this.updateTrackerBaseToViewPlatform = true;
/* 3302 */       this.updateTrackerBaseToVworld = true;
/*      */ 
/*      */       
/* 3305 */       this.updateScreenScale = true;
/*      */ 
/*      */       
/* 3308 */       updateEyeDependencies();
/*      */     }
/*      */     
/*      */     private void updateHeadDependencies() {
/* 3312 */       if (ViewInfo.this.viewPolicy == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3318 */         this.updateCoeToPlate = true;
/* 3319 */         this.updatePlateToViewPlatform = true;
/* 3320 */         this.updatePlateToVworld = true;
/* 3321 */         this.updateViewPlatformToEye = true;
/* 3322 */         this.updateEyeToViewPlatform = true;
/* 3323 */         this.updateEyeToVworld = true;
/* 3324 */         this.updateInverseViewPlatformProjection = true;
/* 3325 */         this.updateInverseVworldProjection = true;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 3331 */         this.updateEyeInPlate = true;
/* 3332 */         updateEyeDependencies();
/*      */       } 
/*      */     }
/*      */     
/*      */     private void updateVworldDependencies() {
/* 3337 */       this.updatePlateToVworld = true;
/* 3338 */       this.updateCoeToVworld = true;
/* 3339 */       this.updateEyeToVworld = true;
/* 3340 */       this.updateTrackerBaseToVworld = true;
/* 3341 */       this.updateInverseVworldProjection = true;
/*      */       
/* 3343 */       if (ViewInfo.this.vpi.updateVworldScale) {
/* 3344 */         this.updatePhysicalToVirtualScale = true;
/*      */       }
/* 3346 */       if (ViewInfo.this.vpi.updateVworldScale && ViewInfo.this.clipVirtual) {
/*      */ 
/*      */         
/* 3349 */         this.updateProjection = true;
/* 3350 */         this.updateClipDistances = true;
/* 3351 */         this.updateInverseProjection = true;
/* 3352 */         this.updateInverseViewPlatformProjection = true;
/*      */       } 
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void t3dPrint(Transform3D paramTransform3D, String paramString) {
/* 3364 */     double[] arrayOfDouble = new double[16];
/* 3365 */     paramTransform3D.get(arrayOfDouble);
/* 3366 */     String[] arrayOfString = formatMatrixRows(4, 4, arrayOfDouble);
/* 3367 */     System.err.println(paramString);
/* 3368 */     for (byte b = 0; b < 4; ) { System.err.println(arrayOfString[b]); b++; }
/*      */   
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
/*      */   private static String[] formatMatrixRows(int paramInt1, int paramInt2, double[] paramArrayOfDouble) {
/* 3385 */     DecimalFormat decimalFormat = new DecimalFormat("0.000000");
/* 3386 */     FieldPosition fieldPosition = new FieldPosition(0);
/* 3387 */     StringBuffer stringBuffer1 = new StringBuffer();
/* 3388 */     StringBuffer stringBuffer2 = new StringBuffer();
/* 3389 */     String[] arrayOfString = new String[paramInt1];
/*      */     
/* 3391 */     for (int i = 0; i < paramInt1; i++) {
/* 3392 */       stringBuffer1.setLength(0);
/* 3393 */       for (int j = 0; j < paramInt2; j++) {
/* 3394 */         stringBuffer2.setLength(0);
/* 3395 */         decimalFormat.format(paramArrayOfDouble[i * paramInt2 + j], stringBuffer2, fieldPosition);
/* 3396 */         int k = 8 - fieldPosition.getEndIndex();
/* 3397 */         for (byte b = 0; b < k; b++) {
/* 3398 */           stringBuffer2.insert(0, " ");
/*      */         }
/* 3400 */         stringBuffer1.append(stringBuffer2);
/*      */       } 
/* 3402 */       arrayOfString[i] = stringBuffer1.toString();
/*      */     } 
/* 3404 */     return arrayOfString;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\util\\universe\ViewInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */