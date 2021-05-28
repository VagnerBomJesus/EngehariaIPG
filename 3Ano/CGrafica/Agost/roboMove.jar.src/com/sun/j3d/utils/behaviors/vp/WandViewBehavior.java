/*      */ package com.sun.j3d.utils.behaviors.vp;
/*      */ 
/*      */ import com.sun.j3d.utils.behaviors.sensor.SensorBeamEcho;
/*      */ import com.sun.j3d.utils.behaviors.sensor.SensorButtonListener;
/*      */ import com.sun.j3d.utils.behaviors.sensor.SensorEvent;
/*      */ import com.sun.j3d.utils.behaviors.sensor.SensorEventAgent;
/*      */ import com.sun.j3d.utils.behaviors.sensor.SensorGnomonEcho;
/*      */ import com.sun.j3d.utils.behaviors.sensor.SensorInputAdaptor;
/*      */ import com.sun.j3d.utils.behaviors.sensor.SensorReadListener;
/*      */ import com.sun.j3d.utils.universe.ConfiguredUniverse;
/*      */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*      */ import com.sun.j3d.utils.universe.Viewer;
/*      */ import com.sun.j3d.utils.universe.ViewingPlatform;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Map;
/*      */ import javax.media.j3d.Appearance;
/*      */ import javax.media.j3d.BadTransformException;
/*      */ import javax.media.j3d.BoundingSphere;
/*      */ import javax.media.j3d.BranchGroup;
/*      */ import javax.media.j3d.Material;
/*      */ import javax.media.j3d.Sensor;
/*      */ import javax.media.j3d.Shape3D;
/*      */ import javax.media.j3d.Transform3D;
/*      */ import javax.media.j3d.TransformGroup;
/*      */ import javax.media.j3d.TransparencyAttributes;
/*      */ import javax.media.j3d.View;
/*      */ import javax.media.j3d.WakeupCondition;
/*      */ import javax.media.j3d.WakeupOnElapsedFrames;
/*      */ import javax.vecmath.AxisAngle4d;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Matrix3d;
/*      */ import javax.vecmath.Matrix4d;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WandViewBehavior
/*      */   extends ViewPlatformBehavior
/*      */ {
/*      */   public static final int NONE = 0;
/*      */   public static final int GRAB_VIEW = 1;
/*      */   public static final int TRANSLATE_FORWARD = 2;
/*      */   public static final int TRANSLATE_BACKWARD = 3;
/*      */   public static final int ROTATE_CCW = 4;
/*      */   public static final int ROTATE_CW = 5;
/*      */   public static final int SCALE_UP = 6;
/*      */   public static final int SCALE_DOWN = 7;
/*      */   public static final int TRANSLATION = 8;
/*      */   public static final int SCALE = 9;
/*      */   public static final int ROTATION = 10;
/*      */   public static final int PER_FRAME = 11;
/*      */   public static final int PER_SECOND = 12;
/*      */   public static final int VIRTUAL_UNITS = 13;
/*      */   public static final int PHYSICAL_METERS = 14;
/*      */   public static final int RADIANS = 15;
/*      */   public static final int DEGREES = 16;
/*      */   public static final int VIEW_PLATFORM = 17;
/*      */   public static final int HEAD = 18;
/*      */   public static final int SENSOR = 19;
/*      */   public static final int VWORLD_FIXED = 20;
/*      */   public static final int HOTSPOT = 21;
/*      */   public static final int ECHO = 22;
/*      */   public static final int GNOMON = 23;
/*      */   public static final int BEAM = 24;
/*      */   private static final int UNSET = -1;
/*      */   private View view;
/*      */   private SensorEventAgent eventAgent;
/*      */   private String sensor6DName;
/*      */   private String sensor2DName;
/*      */   private Shape3D echoGeometry;
/*      */   private BranchGroup echoBranchGroup;
/*      */   private TransformGroup echoTransformGroup;
/*      */   private SensorReadListener echoReadListener6D;
/*      */   private boolean echoBranchGroupAttached;
/*      */   private WakeupCondition wakeupConditions;
/*      */   private boolean configured;
/*      */   private Sensor sensor6D;
/*      */   private Sensor sensor2D;
/*      */   private int x2D;
/*      */   private int y2D;
/*      */   private double threshold2D;
/*      */   private int readAction6D;
/*      */   private int readAction2D;
/*      */   private ArrayList buttonActions6D;
/*      */   private ArrayList buttonActions2D;
/*      */   private double translationSpeed;
/*      */   private int translationUnits;
/*      */   private int translationTimeBase;
/*      */   private double accelerationTime;
/*      */   private double constantSpeedTime;
/*      */   private double fastSpeedFactor;
/*      */   private double rotationSpeed;
/*      */   private int rotationUnits;
/*      */   private int rotationTimeBase;
/*      */   private int rotationCoords;
/*      */   private double scaleSpeed;
/*      */   private int scaleTimeBase;
/*      */   private int transformCenterSource;
/*      */   private Point3d transformCenter;
/*      */   private int resetViewButtonCount6D;
/*      */   private int resetViewButtonCount2D;
/*      */   private int echoType;
/*      */   private double echoSize;
/*      */   private Color3f echoColor;
/*      */   private float echoTransparency;
/*      */   private Transform3D nominalSensorRotation;
/*      */   
/*      */   public WandViewBehavior() {
/*  354 */     this.view = null;
/*  355 */     this.eventAgent = null;
/*  356 */     this.sensor6DName = null;
/*  357 */     this.sensor2DName = null;
/*  358 */     this.echoGeometry = null;
/*  359 */     this.echoBranchGroup = null;
/*  360 */     this.echoTransformGroup = null;
/*  361 */     this.echoReadListener6D = null;
/*  362 */     this.echoBranchGroupAttached = false;
/*  363 */     this.wakeupConditions = new WakeupOnElapsedFrames(0);
/*  364 */     this.configured = false;
/*      */ 
/*      */ 
/*      */     
/*  368 */     this.sensor6D = null;
/*  369 */     this.sensor2D = null;
/*  370 */     this.x2D = 3;
/*  371 */     this.y2D = 7;
/*  372 */     this.threshold2D = 0.0D;
/*      */     
/*  374 */     this.readAction6D = -1;
/*  375 */     this.readAction2D = -1;
/*      */     
/*  377 */     this.buttonActions6D = new ArrayList();
/*  378 */     this.buttonActions2D = new ArrayList();
/*      */     
/*  380 */     this.translationSpeed = 0.1D;
/*  381 */     this.translationUnits = 14;
/*  382 */     this.translationTimeBase = 12;
/*  383 */     this.accelerationTime = 1.0D;
/*  384 */     this.constantSpeedTime = 8.0D;
/*  385 */     this.fastSpeedFactor = 10.0D;
/*      */     
/*  387 */     this.rotationSpeed = 180.0D;
/*  388 */     this.rotationUnits = 16;
/*  389 */     this.rotationTimeBase = 12;
/*  390 */     this.rotationCoords = 19;
/*      */     
/*  392 */     this.scaleSpeed = 2.0D;
/*  393 */     this.scaleTimeBase = 12;
/*      */     
/*  395 */     this.transformCenterSource = 21;
/*  396 */     this.transformCenter = new Point3d(0.0D, 0.0D, 0.0D);
/*      */     
/*  398 */     this.resetViewButtonCount6D = 3;
/*  399 */     this.resetViewButtonCount2D = 0;
/*      */     
/*  401 */     this.echoType = 23;
/*  402 */     this.echoSize = 0.01D;
/*  403 */     this.echoColor = null;
/*  404 */     this.echoTransparency = 0.0F;
/*  405 */     this.nominalSensorRotation = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  416 */     this.eventAgent = new SensorEventAgent(this);
/*      */ 
/*      */     
/*  419 */     setSchedulingBounds(new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), Double.POSITIVE_INFINITY));
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
/*      */   public WandViewBehavior(Sensor paramSensor1, Sensor paramSensor2, int paramInt, double paramDouble) {
/*  447 */     this();
/*  448 */     this.sensor6D = paramSensor1;
/*  449 */     this.sensor2D = paramSensor2;
/*  450 */     this.echoType = paramInt;
/*  451 */     this.echoSize = paramDouble;
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
/*      */   public WandViewBehavior(Sensor paramSensor1, Sensor paramSensor2, TransformGroup paramTransformGroup) {
/*  486 */     this();
/*  487 */     this.sensor6D = paramSensor1;
/*  488 */     this.sensor2D = paramSensor2;
/*  489 */     if (paramTransformGroup != null) {
/*  490 */       paramTransformGroup.setCapability(18);
/*  491 */       paramTransformGroup.setCapability(12);
/*  492 */       paramTransformGroup.setCapability(13);
/*  493 */       paramTransformGroup.setCapability(14);
/*      */     } 
/*  495 */     this.echoTransformGroup = paramTransformGroup;
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
/*      */   public WandViewBehavior(Sensor paramSensor1, Sensor paramSensor2, View paramView, TransformGroup paramTransformGroup1, Transform3D paramTransform3D, TransformGroup paramTransformGroup2) {
/*  540 */     this();
/*  541 */     this.sensor6D = paramSensor1;
/*  542 */     this.sensor2D = paramSensor2;
/*  543 */     this.view = paramView;
/*  544 */     this.targetTG = paramTransformGroup1;
/*  545 */     this.echoTransformGroup = paramTransformGroup2;
/*      */     
/*  547 */     if (paramTransform3D == null) {
/*  548 */       setHomeTransform(new Transform3D());
/*      */     } else {
/*  550 */       setHomeTransform(paramTransform3D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initialize() {
/*  560 */     if (!this.configured) {
/*  561 */       configureSensorActions();
/*      */ 
/*      */       
/*  564 */       if (this.vp != null) {
/*  565 */         if (this.echoTransformGroup == null && this.sensor6D != null && this.readAction6D == 22)
/*      */         {
/*  567 */           configureEcho();
/*      */         }
/*  569 */         if (this.echoTransformGroup != null) {
/*  570 */           this.echoBranchGroup = new BranchGroup();
/*  571 */           this.echoBranchGroup.setCapability(17);
/*      */           
/*  573 */           this.echoBranchGroup.setCapability(12);
/*      */           
/*  575 */           this.echoBranchGroup.setCapability(13);
/*      */ 
/*      */           
/*  578 */           this.echoBranchGroup.addChild(this.echoTransformGroup);
/*  579 */           this.echoBranchGroup.compile();
/*      */         } 
/*  581 */         attachEcho();
/*      */       } 
/*  583 */       this.configured = true;
/*      */     } 
/*  585 */     wakeupOn(this.wakeupConditions);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processStimulus(Enumeration paramEnumeration) {
/*  595 */     this.eventAgent.dispatchEvents();
/*      */ 
/*      */     
/*  598 */     wakeupOn(this.wakeupConditions);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEnable(boolean paramBoolean) {
/*  606 */     if (paramBoolean == getEnable()) {
/*      */       return;
/*      */     }
/*  609 */     if (paramBoolean) {
/*  610 */       attachEcho();
/*      */     } else {
/*      */       
/*  613 */       detachEcho();
/*      */     } 
/*  615 */     super.setEnable(paramBoolean);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setViewingPlatform(ViewingPlatform paramViewingPlatform) {
/*  626 */     super.setViewingPlatform(paramViewingPlatform);
/*  627 */     if (paramViewingPlatform == null) {
/*  628 */       detachEcho();
/*      */       
/*      */       return;
/*      */     } 
/*  632 */     Viewer[] arrayOfViewer = paramViewingPlatform.getViewers();
/*  633 */     if (arrayOfViewer != null) {
/*      */ 
/*      */       
/*  636 */       if (arrayOfViewer.length != 0 && arrayOfViewer[false] != null) {
/*  637 */         this.view = arrayOfViewer[0].getView();
/*      */       }
/*  639 */       if (arrayOfViewer.length > 1)
/*  640 */         throw new RuntimeException("multiple Viewers not supported"); 
/*      */     } 
/*  642 */     if (this.view == null)
/*      */     {
/*  644 */       this.view = getView();
/*      */     }
/*  646 */     if (this.view == null)
/*      */     {
/*  648 */       throw new RuntimeException("a view is not available");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  654 */     this.targetTG = paramViewingPlatform.getMultiTransformGroup().getTransformGroup(0);
/*      */ 
/*      */     
/*  657 */     if (this.homeTransform == null) {
/*  658 */       setHomeTransform(new Transform3D());
/*      */     }
/*  660 */     attachEcho();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void attachEcho() {
/*  667 */     if (this.vp != null && this.echoBranchGroup != null && !this.echoBranchGroupAttached) {
/*      */       
/*  669 */       this.vp.addChild(this.echoBranchGroup);
/*  670 */       this.echoBranchGroupAttached = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void detachEcho() {
/*  678 */     if (this.echoBranchGroup != null && this.echoBranchGroupAttached) {
/*  679 */       this.echoBranchGroup.detach();
/*  680 */       this.echoBranchGroupAttached = false;
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
/*      */   protected void configureSensorActions() {
/*  697 */     SimpleUniverse simpleUniverse = null;
/*  698 */     if (this.vp != null) simpleUniverse = this.vp.getUniverse(); 
/*  699 */     if (simpleUniverse != null && simpleUniverse instanceof ConfiguredUniverse) {
/*      */       
/*  701 */       Map map = ((ConfiguredUniverse)simpleUniverse).getNamedSensors();
/*      */       
/*  703 */       if (this.sensor2D == null && this.sensor2DName != null) {
/*  704 */         this.sensor2D = (Sensor)map.get(this.sensor2DName);
/*  705 */         if (this.sensor2D == null) {
/*  706 */           throw new IllegalArgumentException("\nsensor " + this.sensor2DName + " not found");
/*      */         }
/*      */       } 
/*      */       
/*  710 */       if (this.sensor6D == null && this.sensor6DName != null) {
/*  711 */         this.sensor6D = (Sensor)map.get(this.sensor6DName);
/*  712 */         if (this.sensor6D == null) {
/*  713 */           throw new IllegalArgumentException("\nsensor " + this.sensor6DName + " not found");
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  718 */     if (this.sensor6D != null) {
/*      */       
/*  720 */       if (this.readAction6D == -1) {
/*  721 */         this.readAction6D = 22;
/*      */       }
/*      */       
/*  724 */       if (this.readAction6D == 22) {
/*  725 */         this.echoReadListener6D = new EchoReadListener6D();
/*  726 */         this.eventAgent.addSensorReadListener(this.sensor6D, this.echoReadListener6D);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  731 */       int i = this.sensor6D.getSensorButtonCount();
/*  732 */       int j = this.buttonActions6D.size();
/*  733 */       if (j > i) {
/*  734 */         throw new IllegalArgumentException("\nbutton index " + (j - 1) + " >= number of buttons (" + i + ")");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  739 */       if (i > 2 && (j < 3 || this.buttonActions6D.get(2) == null))
/*      */       {
/*  741 */         setButtonAction6D(2, 3); } 
/*  742 */       if (i > 1 && (j < 2 || this.buttonActions6D.get(true) == null))
/*      */       {
/*  744 */         setButtonAction6D(1, 2); } 
/*  745 */       if (i > 0 && (j < 1 || this.buttonActions6D.get(false) == null))
/*      */       {
/*  747 */         setButtonAction6D(0, 1);
/*      */       }
/*  749 */       j = this.buttonActions6D.size();
/*  750 */       if (j > 0) {
/*      */         
/*  752 */         SensorButtonListener[] arrayOfSensorButtonListener = new SensorButtonListener[i];
/*  753 */         for (byte b = 0; b < j; b++) {
/*  754 */           Integer integer = (Integer)this.buttonActions6D.get(b);
/*  755 */           if (integer != null) {
/*  756 */             int k = integer.intValue();
/*  757 */             if (k == 0) {
/*  758 */               arrayOfSensorButtonListener[b] = null;
/*  759 */             } else if (k == 1) {
/*  760 */               arrayOfSensorButtonListener[b] = new GrabViewListener6D();
/*  761 */             } else if (k == 2) {
/*  762 */               arrayOfSensorButtonListener[b] = new TranslationListener6D(false);
/*  763 */             } else if (k == 3) {
/*  764 */               arrayOfSensorButtonListener[b] = new TranslationListener6D(true);
/*  765 */             } else if (k == 4) {
/*  766 */               arrayOfSensorButtonListener[b] = new RotationListener6D(false);
/*  767 */             } else if (k == 5) {
/*  768 */               arrayOfSensorButtonListener[b] = new RotationListener6D(true);
/*  769 */             } else if (k == 6) {
/*  770 */               arrayOfSensorButtonListener[b] = new ScaleListener6D(false);
/*  771 */             } else if (k == 7) {
/*  772 */               arrayOfSensorButtonListener[b] = new ScaleListener6D(true);
/*      */             } 
/*      */           } 
/*      */         } 
/*  776 */         this.eventAgent.addSensorButtonListeners(this.sensor6D, arrayOfSensorButtonListener);
/*      */       } 
/*      */ 
/*      */       
/*  780 */       if (this.resetViewButtonCount6D != 0) {
/*  781 */         ResetViewListener resetViewListener = new ResetViewListener(this.sensor6D, this.resetViewButtonCount6D);
/*      */         
/*  783 */         this.eventAgent.addSensorButtonListener(this.sensor6D, resetViewListener);
/*  784 */         this.eventAgent.addSensorReadListener(this.sensor6D, resetViewListener);
/*      */       } 
/*      */     } 
/*      */     
/*  788 */     if (this.sensor2D != null) {
/*      */       
/*  790 */       if (this.readAction2D == -1) {
/*  791 */         this.readAction2D = 10;
/*      */       }
/*      */       
/*  794 */       if (this.readAction2D == 10) {
/*  795 */         RotationListener2D rotationListener2D = new RotationListener2D(this.sensor2D, this.sensor6D);
/*      */         
/*  797 */         this.eventAgent.addSensorReadListener(this.sensor2D, rotationListener2D);
/*      */       }
/*  799 */       else if (this.readAction2D == 8) {
/*  800 */         TranslationListener2D translationListener2D = new TranslationListener2D(this.sensor2D, this.sensor6D);
/*      */         
/*  802 */         this.eventAgent.addSensorReadListener(this.sensor2D, translationListener2D);
/*      */       }
/*  804 */       else if (this.readAction2D == 9) {
/*  805 */         ScaleListener2D scaleListener2D = new ScaleListener2D(this.sensor2D, this.sensor6D);
/*      */         
/*  807 */         this.eventAgent.addSensorReadListener(this.sensor2D, scaleListener2D);
/*      */       } 
/*      */ 
/*      */       
/*  811 */       int i = this.sensor2D.getSensorButtonCount();
/*  812 */       int j = this.buttonActions2D.size();
/*  813 */       if (j > i) {
/*  814 */         throw new IllegalArgumentException("\nbutton index " + (j - 1) + " >= number of buttons (" + i + ")");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  819 */       if (j > 0) {
/*      */         
/*  821 */         SensorButtonListener[] arrayOfSensorButtonListener = new SensorButtonListener[i];
/*  822 */         for (byte b = 0; b < j; b++) {
/*  823 */           Integer integer = (Integer)this.buttonActions2D.get(b);
/*  824 */           if (integer != null) {
/*  825 */             int k = integer.intValue();
/*  826 */             if (k == 0) {
/*  827 */               arrayOfSensorButtonListener[b] = null;
/*  828 */             } else if (k == 10) {
/*  829 */               arrayOfSensorButtonListener[b] = new RotationListener2D(this.sensor2D, this.sensor6D);
/*      */             }
/*  831 */             else if (k == 8) {
/*  832 */               arrayOfSensorButtonListener[b] = new TranslationListener2D(this.sensor2D, this.sensor6D);
/*      */             }
/*  834 */             else if (k == 9) {
/*  835 */               arrayOfSensorButtonListener[b] = new ScaleListener2D(this.sensor2D, this.sensor6D);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  840 */         this.eventAgent.addSensorButtonListeners(this.sensor2D, arrayOfSensorButtonListener);
/*      */       } 
/*      */ 
/*      */       
/*  844 */       if (this.resetViewButtonCount2D != 0) {
/*  845 */         ResetViewListener resetViewListener = new ResetViewListener(this.sensor2D, this.resetViewButtonCount2D);
/*      */         
/*  847 */         this.eventAgent.addSensorButtonListener(this.sensor2D, resetViewListener);
/*  848 */         this.eventAgent.addSensorReadListener(this.sensor2D, resetViewListener);
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
/*      */   protected void configureEcho() {
/*  864 */     Point3d point3d = new Point3d();
/*  865 */     this.sensor6D.getHotspot(point3d);
/*      */     
/*  867 */     if (this.echoType == 23) {
/*  868 */       Transform3D transform3D = new Transform3D();
/*  869 */       if (this.nominalSensorRotation != null) {
/*  870 */         transform3D.set(this.nominalSensorRotation);
/*  871 */         transform3D.invert();
/*      */       } 
/*  873 */       transform3D.setTranslation(new Vector3d(point3d));
/*  874 */       this.echoGeometry = new SensorGnomonEcho(transform3D, 0.1D * this.echoSize, 0.5D * this.echoSize, true);
/*      */     
/*      */     }
/*  877 */     else if (this.echoType == 24) {
/*  878 */       this.echoGeometry = new SensorBeamEcho(point3d, this.echoSize, true);
/*      */     } 
/*      */     
/*  881 */     if (this.echoGeometry != null) {
/*  882 */       Appearance appearance = this.echoGeometry.getAppearance();
/*  883 */       if (this.echoColor != null) {
/*  884 */         Material material = appearance.getMaterial();
/*  885 */         material.setDiffuseColor(this.echoColor);
/*      */       } 
/*  887 */       if (this.echoTransparency != 0.0F) {
/*  888 */         TransparencyAttributes transparencyAttributes = appearance.getTransparencyAttributes();
/*  889 */         transparencyAttributes.setTransparencyMode(2);
/*  890 */         transparencyAttributes.setTransparency(this.echoTransparency);
/*      */         
/*  892 */         if (this.echoGeometry instanceof SensorGnomonEcho)
/*  893 */           transparencyAttributes.setDstBlendFunction(1); 
/*      */       } 
/*  895 */       this.echoTransformGroup = new TransformGroup();
/*  896 */       this.echoTransformGroup.setCapability(18);
/*      */       
/*  898 */       this.echoTransformGroup.setCapability(12);
/*  899 */       this.echoTransformGroup.setCapability(13);
/*  900 */       this.echoTransformGroup.setCapability(14);
/*  901 */       this.echoTransformGroup.addChild(this.echoGeometry);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class ListenerBase
/*      */     extends SensorInputAdaptor
/*      */   {
/*  913 */     protected Transform3D viewPlatformToVworld = new Transform3D();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  919 */     protected Transform3D trackerToVworld = new Transform3D();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  925 */     protected Transform3D sensorToVworld = new Transform3D();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  931 */     protected Transform3D sensorToTracker = new Transform3D();
/*      */ 
/*      */     
/*  934 */     private Transform3D trackerToSensor = new Transform3D();
/*      */     
/*      */     private boolean active = false;
/*      */     
/*  938 */     private double[] s3Tmp = new double[3];
/*  939 */     private double[] m16Tmp = new double[16];
/*  940 */     private Vector3d v3dTmp = new Vector3d();
/*  941 */     private Transform3D t3dTmp = new Transform3D();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void initAction(Sensor param1Sensor) {
/*  952 */       WandViewBehavior.this.targetTG.getTransform(this.viewPlatformToVworld);
/*  953 */       this.active = true;
/*  954 */       if (param1Sensor == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  961 */       param1Sensor.getRead(this.sensorToTracker);
/*  962 */       WandViewBehavior.this.view.getSensorToVworld(param1Sensor, this.sensorToVworld);
/*      */       
/*  964 */       this.trackerToSensor.invert(this.sensorToTracker);
/*  965 */       this.trackerToVworld.mul(this.sensorToVworld, this.trackerToSensor);
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
/*  977 */     protected void endAction(Sensor param1Sensor) { this.active = false; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  988 */     protected boolean isActive() { return this.active; }
/*      */ 
/*      */ 
/*      */     
/*  992 */     public void pressed(SensorEvent param1SensorEvent) { initAction(param1SensorEvent.getSensor()); }
/*      */ 
/*      */ 
/*      */     
/*  996 */     public void released(SensorEvent param1SensorEvent) { endAction(param1SensorEvent.getSensor()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected double getPhysicalToVirtualScale() {
/* 1003 */       WandViewBehavior.this.view.getCanvas3D(0).getImagePlateToVworld(this.t3dTmp);
/* 1004 */       this.t3dTmp.get(this.m16Tmp);
/* 1005 */       return Math.sqrt(this.m16Tmp[0] * this.m16Tmp[0] + this.m16Tmp[1] * this.m16Tmp[1] + this.m16Tmp[2] * this.m16Tmp[2]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected double getPhysicalToViewPlatformScale() {
/* 1016 */       WandViewBehavior.this.targetTG.getTransform(this.t3dTmp);
/* 1017 */       this.t3dTmp.get(this.m16Tmp);
/* 1018 */       double d = Math.sqrt(this.m16Tmp[0] * this.m16Tmp[0] + this.m16Tmp[1] * this.m16Tmp[1] + this.m16Tmp[2] * this.m16Tmp[2]);
/*      */ 
/*      */ 
/*      */       
/* 1022 */       return getPhysicalToVirtualScale() / d;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void translateTransform(Transform3D param1Transform3D, Vector3d param1Vector3d) {
/* 1033 */       param1Transform3D.get(this.v3dTmp);
/* 1034 */       this.v3dTmp.add(param1Vector3d);
/* 1035 */       param1Transform3D.setTranslation(this.v3dTmp);
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
/*      */     protected void transformAboutCenter(Transform3D param1Transform3D1, Point3d param1Point3d, Transform3D param1Transform3D2) {
/* 1050 */       param1Transform3D1.get(this.v3dTmp);
/* 1051 */       this.v3dTmp.sub(param1Point3d);
/* 1052 */       param1Transform3D1.setTranslation(this.v3dTmp);
/*      */ 
/*      */       
/* 1055 */       param1Transform3D1.mul(param1Transform3D2, param1Transform3D1);
/*      */ 
/*      */       
/* 1058 */       param1Transform3D1.get(this.v3dTmp);
/* 1059 */       this.v3dTmp.add(param1Point3d);
/* 1060 */       param1Transform3D1.setTranslation(this.v3dTmp);
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
/*      */     protected void conditionViewScale(Transform3D param1Transform3D) {
/* 1073 */       param1Transform3D.normalize();
/* 1074 */       param1Transform3D.get(this.m16Tmp);
/*      */       
/* 1076 */       this.s3Tmp[0] = this.m16Tmp[0] * this.m16Tmp[0] + this.m16Tmp[4] * this.m16Tmp[4] + this.m16Tmp[8] * this.m16Tmp[8];
/*      */       
/* 1078 */       this.s3Tmp[1] = this.m16Tmp[1] * this.m16Tmp[1] + this.m16Tmp[5] * this.m16Tmp[5] + this.m16Tmp[9] * this.m16Tmp[9];
/*      */       
/* 1080 */       this.s3Tmp[2] = this.m16Tmp[2] * this.m16Tmp[2] + this.m16Tmp[6] * this.m16Tmp[6] + this.m16Tmp[10] * this.m16Tmp[10];
/*      */ 
/*      */       
/* 1083 */       if (this.s3Tmp[0] == this.s3Tmp[1] && this.s3Tmp[0] == this.s3Tmp[2]) {
/*      */         return;
/*      */       }
/* 1086 */       this.s3Tmp[0] = Math.sqrt(this.s3Tmp[0]);
/* 1087 */       this.s3Tmp[1] = Math.sqrt(this.s3Tmp[1]);
/* 1088 */       this.s3Tmp[2] = Math.sqrt(this.s3Tmp[2]);
/*      */       
/* 1090 */       byte b1 = 0;
/* 1091 */       if (Math.abs(this.s3Tmp[1] - 1.0D) < Math.abs(this.s3Tmp[0] - 1.0D))
/* 1092 */         b1 = 1; 
/* 1093 */       if (Math.abs(this.s3Tmp[2] - 1.0D) < Math.abs(this.s3Tmp[b1] - 1.0D)) {
/* 1094 */         b1 = 2;
/*      */       }
/*      */       
/* 1097 */       for (byte b2 = 0; b2 < 3; b2++) {
/* 1098 */         if (b2 != b1) {
/* 1099 */           double d = this.s3Tmp[b1] / this.s3Tmp[b2];
/* 1100 */           this.m16Tmp[b2 + false] = this.m16Tmp[b2 + false] * d;
/* 1101 */           this.m16Tmp[b2 + 4] = this.m16Tmp[b2 + 4] * d;
/* 1102 */           this.m16Tmp[b2 + 8] = this.m16Tmp[b2 + 8] * d;
/*      */         } 
/*      */       } 
/*      */       
/* 1106 */       param1Transform3D.set(this.m16Tmp);
/* 1107 */       if ((param1Transform3D.getType() & 0x40) == 0) {
/* 1108 */         WandViewBehavior.this.goHome();
/*      */       } else {
/* 1110 */         WandViewBehavior.this.targetTG.setTransform(param1Transform3D);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public class GrabViewListener6D
/*      */     extends ListenerBase {
/*      */     private Transform3D t3d;
/*      */     private Transform3D initialVworldToSensor;
/*      */     
/*      */     public GrabViewListener6D() {
/* 1121 */       super(WandViewBehavior.this);
/* 1122 */       this.t3d = new Transform3D();
/* 1123 */       this.initialVworldToSensor = new Transform3D();
/*      */     }
/*      */     public void pressed(SensorEvent param1SensorEvent) {
/* 1126 */       initAction(param1SensorEvent.getSensor());
/*      */ 
/*      */       
/* 1129 */       this.initialVworldToSensor.invert(this.sensorToVworld);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void dragged(SensorEvent param1SensorEvent) {
/* 1135 */       Sensor sensor = param1SensorEvent.getSensor();
/* 1136 */       sensor.getRead(this.sensorToTracker);
/* 1137 */       this.sensorToVworld.mul(this.trackerToVworld, this.sensorToTracker);
/*      */ 
/*      */       
/* 1140 */       this.t3d.mul(this.sensorToVworld, this.initialVworldToSensor);
/*      */ 
/*      */ 
/*      */       
/* 1144 */       this.t3d.invert();
/* 1145 */       this.t3d.mul(this.viewPlatformToVworld);
/* 1146 */       WandViewBehavior.this.targetTG.setTransform(this.t3d);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class TranslationListener6D
/*      */     extends ListenerBase
/*      */   {
/*      */     private long buttonDownTime;
/*      */     
/*      */     private double speedScaled;
/*      */     
/*      */     private double interval0;
/*      */     
/*      */     private double interval1;
/*      */     
/*      */     private double interval2;
/*      */     
/* 1165 */     private Vector3d v3d = new Vector3d();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TranslationListener6D(boolean param1Boolean) {
/* 1173 */       super(WandViewBehavior.this);
/*      */       
/* 1175 */       this.interval0 = this$0.accelerationTime;
/* 1176 */       this.interval1 = this.interval0 + this$0.constantSpeedTime;
/* 1177 */       this.interval2 = this.interval1 + this$0.accelerationTime;
/*      */ 
/*      */       
/* 1180 */       if (this$0.translationUnits == 13) {
/* 1181 */         this.speedScaled = this$0.translationSpeed / getPhysicalToVirtualScale();
/*      */       } else {
/* 1183 */         this.speedScaled = this$0.translationSpeed;
/*      */       } 
/* 1185 */       if (param1Boolean) {
/* 1186 */         this.speedScaled = -this.speedScaled;
/*      */       }
/*      */     }
/*      */     
/*      */     public void pressed(SensorEvent param1SensorEvent) {
/* 1191 */       initAction(param1SensorEvent.getSensor());
/* 1192 */       this.buttonDownTime = param1SensorEvent.getTime();
/*      */     }
/*      */     public void dragged(SensorEvent param1SensorEvent) {
/*      */       double d1;
/* 1196 */       long l1 = param1SensorEvent.getTime();
/* 1197 */       long l2 = param1SensorEvent.getLastTime();
/*      */       
/* 1199 */       double d3 = 1.0D;
/* 1200 */       if (WandViewBehavior.this.translationTimeBase == 12) {
/* 1201 */         d3 = (l1 - l2) / 1.0E9D;
/*      */       }
/*      */       
/* 1204 */       double d2 = (l1 - this.buttonDownTime) / 1.0E9D;
/* 1205 */       if (d2 <= this.interval0) {
/* 1206 */         d1 = d2 / WandViewBehavior.this.accelerationTime * this.speedScaled;
/*      */       }
/* 1208 */       else if (d2 > this.interval1 && d2 < this.interval2) {
/* 1209 */         d1 = ((d2 - this.interval1) / WandViewBehavior.this.accelerationTime * (WandViewBehavior.this.fastSpeedFactor - 1.0D) + 1.0D) * this.speedScaled;
/*      */       
/*      */       }
/* 1212 */       else if (d2 >= this.interval2) {
/* 1213 */         d1 = WandViewBehavior.this.fastSpeedFactor * this.speedScaled;
/*      */       } else {
/*      */         
/* 1216 */         d1 = this.speedScaled;
/*      */       } 
/*      */ 
/*      */       
/* 1220 */       this.v3d.set(0.0D, 0.0D, -1.0D);
/* 1221 */       if (WandViewBehavior.this.nominalSensorRotation != null) {
/* 1222 */         WandViewBehavior.this.nominalSensorRotation.transform(this.v3d);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1227 */       Sensor sensor = param1SensorEvent.getSensor();
/* 1228 */       sensor.getRead(this.sensorToTracker);
/* 1229 */       this.sensorToVworld.mul(this.trackerToVworld, this.sensorToTracker);
/* 1230 */       this.sensorToVworld.transform(this.v3d);
/*      */ 
/*      */       
/* 1233 */       this.v3d.scale(d3 * d1);
/* 1234 */       translateTransform(this.viewPlatformToVworld, this.v3d);
/* 1235 */       WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/*      */ 
/*      */       
/* 1238 */       translateTransform(this.trackerToVworld, this.v3d);
/*      */       
/* 1240 */       if (WandViewBehavior.this.readAction6D == 22) {
/*      */ 
/*      */         
/* 1243 */         translateTransform(this.sensorToVworld, this.v3d);
/* 1244 */         WandViewBehavior.this.updateEcho(sensor, this.sensorToVworld);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class RotationListener6D
/*      */     extends ListenerBase
/*      */   {
/*      */     private boolean reverse;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private long buttonDownTime;
/*      */ 
/*      */ 
/*      */     
/* 1265 */     private Vector3d axis = new Vector3d();
/* 1266 */     private Point3d center = new Point3d();
/* 1267 */     private Transform3D t3d = new Transform3D();
/* 1268 */     private AxisAngle4d aa4d = new AxisAngle4d();
/* 1269 */     private Transform3D headToVworld = new Transform3D();
/*      */     private double speedScaled;
/*      */     
/*      */     protected void initAction(Sensor param1Sensor) {
/* 1273 */       super.initAction(param1Sensor);
/* 1274 */       if (WandViewBehavior.this.rotationCoords == 18) {
/* 1275 */         WandViewBehavior.this.view.setUserHeadToVworldEnable(true);
/*      */       }
/*      */     }
/*      */     
/*      */     protected void endAction(Sensor param1Sensor) {
/* 1280 */       super.endAction(param1Sensor);
/* 1281 */       this.viewPlatformToVworld.normalize();
/* 1282 */       WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/* 1283 */       if (WandViewBehavior.this.rotationCoords == 18) {
/* 1284 */         WandViewBehavior.this.view.setUserHeadToVworldEnable(false);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public RotationListener6D(boolean param1Boolean) {
/* 1294 */       super(WandViewBehavior.this);
/* 1295 */       this.reverse = param1Boolean;
/* 1296 */       if (this$0.rotationUnits == 16) {
/* 1297 */         this.speedScaled = this$0.rotationSpeed * Math.PI / 180.0D;
/*      */       } else {
/* 1299 */         this.speedScaled = this$0.rotationSpeed;
/*      */       } 
/*      */     }
/*      */     public void pressed(SensorEvent param1SensorEvent) {
/* 1303 */       initAction(param1SensorEvent.getSensor());
/* 1304 */       this.buttonDownTime = param1SensorEvent.getTime();
/*      */     }
/*      */     public void dragged(SensorEvent param1SensorEvent) {
/*      */       double d1;
/* 1308 */       long l1 = param1SensorEvent.getTime();
/* 1309 */       long l2 = param1SensorEvent.getLastTime();
/*      */       
/* 1311 */       double d3 = 1.0D;
/* 1312 */       if (WandViewBehavior.this.rotationTimeBase == 12) {
/* 1313 */         d3 = (l1 - l2) / 1.0E9D;
/*      */       }
/*      */       
/* 1316 */       double d2 = (l1 - this.buttonDownTime) / 1.0E9D;
/* 1317 */       if (d2 <= WandViewBehavior.this.accelerationTime) {
/* 1318 */         d1 = d2 / WandViewBehavior.this.accelerationTime * this.speedScaled;
/*      */       } else {
/*      */         
/* 1321 */         d1 = this.speedScaled;
/*      */       } 
/*      */ 
/*      */       
/* 1325 */       if (this.reverse) {
/* 1326 */         this.axis.set(0.0D, -1.0D, 0.0D);
/*      */       } else {
/* 1328 */         this.axis.set(0.0D, 1.0D, 0.0D);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1333 */       Sensor sensor = param1SensorEvent.getSensor();
/* 1334 */       sensor.getRead(this.sensorToTracker);
/* 1335 */       this.sensorToVworld.mul(this.trackerToVworld, this.sensorToTracker);
/*      */ 
/*      */       
/* 1338 */       if (WandViewBehavior.this.rotationCoords == 19) {
/* 1339 */         if (WandViewBehavior.this.nominalSensorRotation != null) {
/* 1340 */           WandViewBehavior.this.nominalSensorRotation.transform(this.axis);
/*      */         }
/* 1342 */         this.sensorToVworld.transform(this.axis);
/*      */       }
/* 1344 */       else if (WandViewBehavior.this.rotationCoords == 18) {
/* 1345 */         WandViewBehavior.this.view.getUserHeadToVworld(this.headToVworld);
/* 1346 */         this.headToVworld.transform(this.axis);
/*      */       } else {
/*      */         
/* 1349 */         this.viewPlatformToVworld.transform(this.axis);
/*      */       } 
/*      */ 
/*      */       
/* 1353 */       if (WandViewBehavior.this.transformCenterSource == 21) {
/* 1354 */         sensor.getHotspot(this.center);
/* 1355 */         this.sensorToVworld.transform(this.center);
/*      */       } else {
/*      */         
/* 1358 */         this.center.set(WandViewBehavior.this.transformCenter);
/*      */       } 
/*      */ 
/*      */       
/* 1362 */       this.aa4d.set(this.axis, d1 * d3);
/* 1363 */       this.t3d.set(this.aa4d);
/*      */ 
/*      */       
/* 1366 */       transformAboutCenter(this.viewPlatformToVworld, this.center, this.t3d);
/* 1367 */       WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/*      */ 
/*      */       
/* 1370 */       transformAboutCenter(this.trackerToVworld, this.center, this.t3d);
/*      */       
/* 1372 */       if (WandViewBehavior.this.readAction6D == 22) {
/*      */ 
/*      */         
/* 1375 */         transformAboutCenter(this.sensorToVworld, this.center, this.t3d);
/* 1376 */         WandViewBehavior.this.updateEcho(sensor, this.sensorToVworld);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class ScaleListener6D
/*      */     extends ListenerBase
/*      */   {
/*      */     private double direction;
/*      */ 
/*      */ 
/*      */     
/*      */     private long buttonDownTime;
/*      */ 
/*      */ 
/*      */     
/* 1395 */     private Point3d center = new Point3d();
/* 1396 */     private Transform3D t3d = new Transform3D();
/*      */     
/*      */     protected void endAction(Sensor param1Sensor) {
/* 1399 */       super.endAction(param1Sensor);
/* 1400 */       conditionViewScale(this.viewPlatformToVworld);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScaleListener6D(boolean param1Boolean) {
/* 1409 */       super(WandViewBehavior.this);
/* 1410 */       if (param1Boolean) {
/* 1411 */         this.direction = -1.0D;
/*      */       } else {
/* 1413 */         this.direction = 1.0D;
/*      */       } 
/*      */     }
/*      */     public void pressed(SensorEvent param1SensorEvent) {
/* 1417 */       initAction(param1SensorEvent.getSensor());
/* 1418 */       this.buttonDownTime = param1SensorEvent.getTime();
/*      */     }
/*      */     public void dragged(SensorEvent param1SensorEvent) {
/*      */       double d2;
/* 1422 */       long l1 = param1SensorEvent.getTime();
/* 1423 */       long l2 = param1SensorEvent.getLastTime();
/*      */       
/* 1425 */       double d4 = 1.0D;
/* 1426 */       if (WandViewBehavior.this.scaleTimeBase == 12) {
/* 1427 */         d4 = (l1 - l2) / 1.0E9D;
/*      */       }
/*      */       
/* 1430 */       double d3 = (l1 - this.buttonDownTime) / 1.0E9D;
/* 1431 */       if (d3 <= WandViewBehavior.this.accelerationTime) {
/* 1432 */         d2 = d3 / WandViewBehavior.this.accelerationTime * d4 * this.direction;
/*      */       } else {
/*      */         
/* 1435 */         d2 = d4 * this.direction;
/*      */       } 
/* 1437 */       double d1 = Math.pow(WandViewBehavior.this.scaleSpeed, d2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1442 */       Sensor sensor = param1SensorEvent.getSensor();
/* 1443 */       sensor.getRead(this.sensorToTracker);
/* 1444 */       this.sensorToVworld.mul(this.trackerToVworld, this.sensorToTracker);
/*      */ 
/*      */       
/* 1447 */       if (WandViewBehavior.this.transformCenterSource == 21) {
/* 1448 */         sensor.getHotspot(this.center);
/* 1449 */         this.sensorToVworld.transform(this.center);
/*      */       } else {
/*      */         
/* 1452 */         this.center.set(WandViewBehavior.this.transformCenter);
/*      */       } 
/*      */ 
/*      */       
/* 1456 */       this.t3d.set(d1);
/* 1457 */       transformAboutCenter(this.viewPlatformToVworld, this.center, this.t3d);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1465 */         WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/*      */       }
/* 1467 */       catch (BadTransformException badTransformException) {
/* 1468 */         conditionViewScale(this.viewPlatformToVworld);
/*      */       } 
/*      */ 
/*      */       
/* 1472 */       transformAboutCenter(this.trackerToVworld, this.center, this.t3d);
/*      */       
/* 1474 */       if (WandViewBehavior.this.readAction6D == 22) {
/*      */ 
/*      */         
/* 1477 */         transformAboutCenter(this.sensorToVworld, this.center, this.t3d);
/* 1478 */         WandViewBehavior.this.updateEcho(sensor, this.sensorToVworld);
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
/*      */   public class EchoReadListener6D
/*      */     implements SensorReadListener
/*      */   {
/* 1494 */     private Transform3D sensorToVworld = new Transform3D();
/*      */     
/*      */     public void read(SensorEvent param1SensorEvent) {
/* 1497 */       Sensor sensor = param1SensorEvent.getSensor();
/* 1498 */       WandViewBehavior.this.view.getSensorToVworld(sensor, this.sensorToVworld);
/* 1499 */       WandViewBehavior.this.updateEcho(sensor, this.sensorToVworld);
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
/*      */   public class RotationListener2D
/*      */     extends ListenerBase
/*      */   {
/*      */     private Sensor sensor2D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Sensor sensor6D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1537 */     private double[] m = new double[16];
/* 1538 */     private Vector3d axis = new Vector3d();
/* 1539 */     private Point3d center = new Point3d();
/* 1540 */     private Transform3D t3d = new Transform3D();
/* 1541 */     private AxisAngle4d aa4d = new AxisAngle4d();
/* 1542 */     private Transform3D sensor2DRead = new Transform3D();
/* 1543 */     private Transform3D headToVworld = new Transform3D();
/*      */     private double speedScaled;
/*      */     
/*      */     protected void initAction(Sensor param1Sensor) {
/* 1547 */       super.initAction(param1Sensor);
/* 1548 */       if (WandViewBehavior.this.rotationCoords == 18) {
/* 1549 */         WandViewBehavior.this.view.setUserHeadToVworldEnable(true);
/*      */       }
/* 1551 */       if (param1Sensor != null && WandViewBehavior.this.readAction6D == 22)
/*      */       {
/* 1553 */         WandViewBehavior.this.eventAgent.removeSensorReadListener(param1Sensor, WandViewBehavior.this.echoReadListener6D);
/*      */       }
/*      */     }
/*      */     
/*      */     protected void endAction(Sensor param1Sensor) {
/* 1558 */       super.endAction(param1Sensor);
/* 1559 */       this.viewPlatformToVworld.normalize();
/* 1560 */       WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/* 1561 */       if (WandViewBehavior.this.rotationCoords == 18) {
/* 1562 */         WandViewBehavior.this.view.setUserHeadToVworldEnable(false);
/*      */       }
/* 1564 */       if (param1Sensor != null && WandViewBehavior.this.readAction6D == 22) {
/* 1565 */         WandViewBehavior.this.eventAgent.addSensorReadListener(param1Sensor, WandViewBehavior.this.echoReadListener6D);
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
/*      */     public RotationListener2D(Sensor param1Sensor1, Sensor param1Sensor2) {
/* 1578 */       super(WandViewBehavior.this);
/* 1579 */       this.sensor2D = param1Sensor1;
/* 1580 */       this.sensor6D = param1Sensor2;
/*      */       
/* 1582 */       if (this$0.rotationUnits == 16) {
/* 1583 */         this.speedScaled = this$0.rotationSpeed * Math.PI / 180.0D;
/*      */       } else {
/* 1585 */         this.speedScaled = this$0.rotationSpeed;
/*      */       } 
/*      */     }
/*      */     public void read(SensorEvent param1SensorEvent) {
/* 1589 */       this.sensor2D.getRead(this.sensor2DRead);
/* 1590 */       this.sensor2DRead.get(this.m);
/*      */       
/* 1592 */       if (this.m[WandViewBehavior.this.x2D] > WandViewBehavior.this.threshold2D || this.m[WandViewBehavior.this.x2D] < -WandViewBehavior.this.threshold2D || this.m[WandViewBehavior.this.y2D] > WandViewBehavior.this.threshold2D || this.m[WandViewBehavior.this.y2D] < -WandViewBehavior.this.threshold2D)
/*      */       
/*      */       { 
/* 1595 */         if (!isActive()) initAction(this.sensor6D);
/*      */ 
/*      */ 
/*      */         
/* 1599 */         double d1 = Math.sqrt(this.m[WandViewBehavior.this.x2D] * this.m[WandViewBehavior.this.x2D] + this.m[WandViewBehavior.this.y2D] * this.m[WandViewBehavior.this.y2D]);
/* 1600 */         double d2 = 1.0D / d1;
/* 1601 */         this.axis.set(this.m[WandViewBehavior.this.y2D] * d2, -this.m[WandViewBehavior.this.x2D] * d2, 0.0D);
/*      */         
/* 1603 */         if (this.sensor6D != null) {
/*      */ 
/*      */ 
/*      */           
/* 1607 */           this.sensor6D.getRead(this.sensorToTracker);
/* 1608 */           this.sensorToVworld.mul(this.trackerToVworld, this.sensorToTracker);
/*      */         } 
/*      */ 
/*      */         
/* 1612 */         if (this.sensor6D != null && WandViewBehavior.this.rotationCoords == 19) {
/* 1613 */           if (WandViewBehavior.this.nominalSensorRotation != null) {
/* 1614 */             WandViewBehavior.this.nominalSensorRotation.transform(this.axis);
/*      */           }
/* 1616 */           this.sensorToVworld.transform(this.axis);
/*      */         }
/* 1618 */         else if (WandViewBehavior.this.rotationCoords == 18) {
/* 1619 */           WandViewBehavior.this.view.getUserHeadToVworld(this.headToVworld);
/* 1620 */           this.headToVworld.transform(this.axis);
/*      */         } else {
/*      */           
/* 1623 */           this.viewPlatformToVworld.transform(this.axis);
/*      */         } 
/*      */ 
/*      */         
/* 1627 */         if (WandViewBehavior.this.transformCenterSource == 21 && this.sensor6D != null) {
/* 1628 */           this.sensor6D.getHotspot(this.center);
/* 1629 */           this.sensorToVworld.transform(this.center);
/*      */         } else {
/*      */           
/* 1632 */           this.center.set(WandViewBehavior.this.transformCenter);
/*      */         } 
/*      */         
/* 1635 */         double d3 = 1.0D;
/* 1636 */         if (WandViewBehavior.this.rotationTimeBase == 12) {
/* 1637 */           d3 = (param1SensorEvent.getTime() - param1SensorEvent.getLastTime()) / 1.0E9D;
/*      */         }
/*      */         
/* 1640 */         this.aa4d.set(this.axis, this.speedScaled * d3 * d1);
/* 1641 */         this.t3d.set(this.aa4d);
/*      */ 
/*      */         
/* 1644 */         transformAboutCenter(this.viewPlatformToVworld, this.center, this.t3d);
/* 1645 */         WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/*      */         
/* 1647 */         if (this.sensor6D != null)
/*      */         {
/* 1649 */           transformAboutCenter(this.trackerToVworld, this.center, this.t3d);
/*      */         }
/*      */         
/* 1652 */         if (this.sensor6D != null && WandViewBehavior.this.readAction6D == 22)
/*      */         {
/*      */           
/* 1655 */           transformAboutCenter(this.sensorToVworld, this.center, this.t3d);
/* 1656 */           WandViewBehavior.this.updateEcho(this.sensor6D, this.sensorToVworld);
/*      */         
/*      */         }
/*      */          }
/*      */       
/* 1661 */       else if (isActive()) { endAction(this.sensor6D); }
/*      */     
/*      */     }
/*      */ 
/*      */     
/* 1666 */     public void pressed(SensorEvent param1SensorEvent) { initAction(this.sensor6D); }
/*      */ 
/*      */ 
/*      */     
/* 1670 */     public void released(SensorEvent param1SensorEvent) { if (isActive()) endAction(this.sensor6D);
/*      */        }
/*      */ 
/*      */     
/* 1674 */     public void dragged(SensorEvent param1SensorEvent) { read(param1SensorEvent); }
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
/*      */   public class TranslationListener2D
/*      */     extends ListenerBase
/*      */   {
/*      */     private Sensor sensor2D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Sensor sensor6D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1705 */     private double[] m = new double[16];
/* 1706 */     private Vector3d v3d = new Vector3d();
/* 1707 */     private Transform3D sensor2DRead = new Transform3D();
/*      */     private double speedScaled;
/*      */     
/*      */     protected void initAction(Sensor param1Sensor) {
/* 1711 */       super.initAction(param1Sensor);
/* 1712 */       if (param1Sensor != null && WandViewBehavior.this.readAction6D == 22)
/*      */       {
/* 1714 */         WandViewBehavior.this.eventAgent.removeSensorReadListener(param1Sensor, WandViewBehavior.this.echoReadListener6D);
/*      */       }
/*      */     }
/*      */     
/*      */     protected void endAction(Sensor param1Sensor) {
/* 1719 */       super.endAction(param1Sensor);
/* 1720 */       if (param1Sensor != null && WandViewBehavior.this.readAction6D == 22)
/*      */       {
/* 1722 */         WandViewBehavior.this.eventAgent.addSensorReadListener(param1Sensor, WandViewBehavior.this.echoReadListener6D);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TranslationListener2D(Sensor param1Sensor1, Sensor param1Sensor2) {
/* 1733 */       super(WandViewBehavior.this);
/* 1734 */       this.sensor2D = param1Sensor1;
/* 1735 */       this.sensor6D = param1Sensor2;
/*      */ 
/*      */       
/* 1738 */       if (this$0.translationUnits == 13) {
/* 1739 */         this.speedScaled = this$0.translationSpeed * this$0.fastSpeedFactor / getPhysicalToVirtualScale();
/*      */       } else {
/*      */         
/* 1742 */         this.speedScaled = this$0.translationSpeed * this$0.fastSpeedFactor;
/*      */       } 
/*      */       
/* 1745 */       if (param1Sensor2 == null)
/* 1746 */         this.speedScaled *= getPhysicalToViewPlatformScale(); 
/*      */     }
/*      */     
/*      */     public void read(SensorEvent param1SensorEvent) {
/* 1750 */       this.sensor2D.getRead(this.sensor2DRead);
/* 1751 */       this.sensor2DRead.get(this.m);
/*      */       
/* 1753 */       if (this.m[WandViewBehavior.this.x2D] > WandViewBehavior.this.threshold2D || this.m[WandViewBehavior.this.x2D] < -WandViewBehavior.this.threshold2D || this.m[WandViewBehavior.this.y2D] > WandViewBehavior.this.threshold2D || this.m[WandViewBehavior.this.y2D] < -WandViewBehavior.this.threshold2D)
/*      */       
/*      */       { 
/* 1756 */         if (!isActive()) initAction(this.sensor6D);
/*      */ 
/*      */ 
/*      */         
/* 1760 */         double d1 = Math.sqrt(this.m[WandViewBehavior.this.x2D] * this.m[WandViewBehavior.this.x2D] + this.m[WandViewBehavior.this.y2D] * this.m[WandViewBehavior.this.y2D]);
/* 1761 */         double d2 = 1.0D / d1;
/* 1762 */         this.v3d.set(this.m[WandViewBehavior.this.x2D] * d2, 0.0D, -this.m[WandViewBehavior.this.y2D] * d2);
/*      */ 
/*      */         
/* 1765 */         if (this.sensor6D != null) {
/* 1766 */           if (WandViewBehavior.this.nominalSensorRotation != null) {
/* 1767 */             WandViewBehavior.this.nominalSensorRotation.transform(this.v3d);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1772 */           this.sensor6D.getRead(this.sensorToTracker);
/* 1773 */           this.sensorToVworld.mul(this.trackerToVworld, this.sensorToTracker);
/* 1774 */           this.sensorToVworld.transform(this.v3d);
/*      */         } else {
/*      */           
/* 1777 */           this.viewPlatformToVworld.transform(this.v3d);
/*      */         } 
/*      */         
/* 1780 */         double d3 = 1.0D;
/* 1781 */         if (WandViewBehavior.this.translationTimeBase == 12) {
/* 1782 */           d3 = (param1SensorEvent.getTime() - param1SensorEvent.getLastTime()) / 1.0E9D;
/*      */         }
/* 1784 */         this.v3d.scale(d3 * this.speedScaled * d1);
/*      */ 
/*      */         
/* 1787 */         translateTransform(this.viewPlatformToVworld, this.v3d);
/* 1788 */         WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/*      */         
/* 1790 */         if (this.sensor6D != null)
/*      */         {
/* 1792 */           translateTransform(this.trackerToVworld, this.v3d);
/*      */         }
/*      */         
/* 1795 */         if (this.sensor6D != null && WandViewBehavior.this.readAction6D == 22)
/*      */         {
/*      */           
/* 1798 */           translateTransform(this.sensorToVworld, this.v3d);
/* 1799 */           WandViewBehavior.this.updateEcho(this.sensor6D, this.sensorToVworld);
/*      */         
/*      */         }
/*      */          }
/*      */       
/* 1804 */       else if (isActive()) { endAction(this.sensor6D); }
/*      */     
/*      */     }
/*      */ 
/*      */     
/* 1809 */     public void pressed(SensorEvent param1SensorEvent) { initAction(this.sensor6D); }
/*      */ 
/*      */ 
/*      */     
/* 1813 */     public void released(SensorEvent param1SensorEvent) { if (isActive()) endAction(this.sensor6D);
/*      */        }
/*      */ 
/*      */     
/* 1817 */     public void dragged(SensorEvent param1SensorEvent) { read(param1SensorEvent); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class ScaleListener2D
/*      */     extends ListenerBase
/*      */   {
/*      */     private Sensor sensor2D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Sensor sensor6D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1844 */     private double[] m = new double[16];
/* 1845 */     private Point3d center = new Point3d();
/* 1846 */     private Transform3D t3d = new Transform3D();
/* 1847 */     private Transform3D sensor2DRead = new Transform3D();
/*      */     
/*      */     protected void initAction(Sensor param1Sensor) {
/* 1850 */       super.initAction(param1Sensor);
/* 1851 */       if (param1Sensor != null && WandViewBehavior.this.readAction6D == 22)
/*      */       {
/* 1853 */         WandViewBehavior.this.eventAgent.removeSensorReadListener(param1Sensor, WandViewBehavior.this.echoReadListener6D);
/*      */       }
/*      */     }
/*      */     
/*      */     protected void endAction(Sensor param1Sensor) {
/* 1858 */       super.endAction(param1Sensor);
/* 1859 */       conditionViewScale(this.viewPlatformToVworld);
/* 1860 */       if (param1Sensor != null && WandViewBehavior.this.readAction6D == 22)
/*      */       {
/* 1862 */         WandViewBehavior.this.eventAgent.addSensorReadListener(param1Sensor, WandViewBehavior.this.echoReadListener6D);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScaleListener2D(Sensor param1Sensor1, Sensor param1Sensor2) {
/* 1873 */       super(WandViewBehavior.this);
/* 1874 */       this.sensor2D = param1Sensor1;
/* 1875 */       this.sensor6D = param1Sensor2;
/*      */     }
/*      */     
/*      */     public void read(SensorEvent param1SensorEvent) {
/* 1879 */       this.sensor2D.getRead(this.sensor2DRead);
/* 1880 */       this.sensor2DRead.get(this.m);
/*      */       
/* 1882 */       if (this.m[WandViewBehavior.this.y2D] > WandViewBehavior.this.threshold2D || this.m[WandViewBehavior.this.y2D] < -WandViewBehavior.this.threshold2D)
/*      */       
/* 1884 */       { if (!isActive()) initAction(this.sensor6D);
/*      */         
/* 1886 */         if (this.sensor6D != null) {
/*      */ 
/*      */ 
/*      */           
/* 1890 */           this.sensor6D.getRead(this.sensorToTracker);
/* 1891 */           this.sensorToVworld.mul(this.trackerToVworld, this.sensorToTracker);
/*      */         } 
/*      */ 
/*      */         
/* 1895 */         if (this.sensor6D != null && WandViewBehavior.this.transformCenterSource == 21) {
/* 1896 */           this.sensor6D.getHotspot(this.center);
/* 1897 */           this.sensorToVworld.transform(this.center);
/*      */         } else {
/*      */           
/* 1900 */           this.center.set(WandViewBehavior.this.transformCenter);
/*      */         } 
/*      */ 
/*      */         
/* 1904 */         double d1 = 1.0D;
/* 1905 */         if (WandViewBehavior.this.scaleTimeBase == 12) {
/* 1906 */           d1 = (param1SensorEvent.getTime() - param1SensorEvent.getLastTime()) / 1.0E9D;
/*      */         }
/*      */ 
/*      */         
/* 1910 */         double d2 = Math.pow(WandViewBehavior.this.scaleSpeed, -this.m[WandViewBehavior.this.y2D] * d1);
/*      */ 
/*      */         
/* 1913 */         this.t3d.set(d2);
/* 1914 */         transformAboutCenter(this.viewPlatformToVworld, this.center, this.t3d);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1923 */           WandViewBehavior.this.targetTG.setTransform(this.viewPlatformToVworld);
/*      */         }
/* 1925 */         catch (BadTransformException badTransformException) {
/* 1926 */           conditionViewScale(this.viewPlatformToVworld);
/*      */         } 
/*      */         
/* 1929 */         if (this.sensor6D != null)
/*      */         {
/* 1931 */           transformAboutCenter(this.trackerToVworld, this.center, this.t3d);
/*      */         }
/*      */         
/* 1934 */         if (this.sensor6D != null && WandViewBehavior.this.readAction6D == 22)
/*      */         {
/*      */           
/* 1937 */           transformAboutCenter(this.sensorToVworld, this.center, this.t3d);
/* 1938 */           WandViewBehavior.this.updateEcho(this.sensor6D, this.sensorToVworld);
/*      */         
/*      */         }
/*      */          }
/*      */       
/* 1943 */       else if (isActive()) { endAction(this.sensor6D); }
/*      */     
/*      */     }
/*      */ 
/*      */     
/* 1948 */     public void pressed(SensorEvent param1SensorEvent) { initAction(this.sensor6D); }
/*      */ 
/*      */ 
/*      */     
/* 1952 */     public void released(SensorEvent param1SensorEvent) { if (isActive()) endAction(this.sensor6D);
/*      */        }
/*      */ 
/*      */     
/* 1956 */     public void dragged(SensorEvent param1SensorEvent) { read(param1SensorEvent); }
/*      */   }
/*      */ 
/*      */   
/*      */   public class ResetViewListener
/*      */     extends SensorInputAdaptor
/*      */   {
/*      */     private int resetCount;
/*      */     
/*      */     private int[] buttonState;
/*      */     
/*      */     private boolean goHomeNextRead;
/*      */     
/*      */     public ResetViewListener(Sensor param1Sensor, int param1Int) {
/* 1970 */       this.buttonState = null;
/* 1971 */       this.goHomeNextRead = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1981 */       this.resetCount = param1Int;
/* 1982 */       this.buttonState = new int[param1Sensor.getSensorButtonCount()];
/*      */     }
/*      */     
/*      */     public void pressed(SensorEvent param1SensorEvent) {
/* 1986 */       byte b1 = 0;
/* 1987 */       param1SensorEvent.getButtonState(this.buttonState);
/* 1988 */       for (byte b2 = 0; b2 < this.buttonState.length; b2++) {
/* 1989 */         if (this.buttonState[b2] == 1) b1++; 
/*      */       } 
/* 1991 */       if (b1 >= this.resetCount)
/*      */       {
/*      */         
/* 1994 */         this.goHomeNextRead = true; } 
/*      */     }
/*      */     
/*      */     public void read(SensorEvent param1SensorEvent) {
/* 1998 */       if (this.goHomeNextRead) {
/* 1999 */         WandViewBehavior.this.goHome();
/* 2000 */         this.goHomeNextRead = false;
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
/* 2021 */   protected void updateEcho(Sensor paramSensor, Transform3D paramTransform3D) { this.echoTransformGroup.setTransform(paramTransform3D); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void Sensor6D(Object[] paramArrayOfObject) {
/* 2045 */     if (paramArrayOfObject.length != 1) {
/* 2046 */       throw new IllegalArgumentException("Sensor6D requires a single name or Sensor instance");
/*      */     }
/*      */     
/* 2049 */     if (paramArrayOfObject[0] instanceof String) {
/* 2050 */       this.sensor6DName = (String)paramArrayOfObject[0];
/* 2051 */     } else if (paramArrayOfObject[0] instanceof Sensor) {
/* 2052 */       this.sensor6D = (Sensor)paramArrayOfObject[0];
/*      */     } else {
/* 2054 */       throw new IllegalArgumentException("Sensor6D must be a name or a Sensor instance");
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
/* 2065 */   public Sensor getSensor6D() { return this.sensor6D; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void Sensor2D(Object[] paramArrayOfObject) {
/* 2095 */     if (paramArrayOfObject.length != 1) {
/* 2096 */       throw new IllegalArgumentException("Sensor2D requires a single name or Sensor instance");
/*      */     }
/*      */     
/* 2099 */     if (paramArrayOfObject[0] instanceof String) {
/* 2100 */       this.sensor2DName = (String)paramArrayOfObject[0];
/* 2101 */     } else if (paramArrayOfObject[0] instanceof Sensor) {
/* 2102 */       this.sensor2D = (Sensor)paramArrayOfObject[0];
/*      */     } else {
/* 2104 */       throw new IllegalArgumentException("Sensor2D must be a name or a Sensor instance");
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
/* 2115 */   public Sensor getSensor2D() { return this.sensor2D; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ButtonAction6D(Object[] paramArrayOfObject) {
/* 2170 */     if (paramArrayOfObject.length != 2 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof String))
/*      */     {
/* 2172 */       throw new IllegalArgumentException("\nButtonAction6D must be a number and a string");
/*      */     }
/*      */     
/* 2175 */     int i = ((Double)paramArrayOfObject[0]).intValue();
/* 2176 */     String str = (String)paramArrayOfObject[1];
/*      */     
/* 2178 */     if (str.equals("GrabView")) {
/* 2179 */       setButtonAction6D(i, 1);
/* 2180 */     } else if (str.equals("TranslateForward")) {
/* 2181 */       setButtonAction6D(i, 2);
/* 2182 */     } else if (str.equals("TranslateBackward")) {
/* 2183 */       setButtonAction6D(i, 3);
/* 2184 */     } else if (str.equals("RotateCCW")) {
/* 2185 */       setButtonAction6D(i, 4);
/* 2186 */     } else if (str.equals("RotateCW")) {
/* 2187 */       setButtonAction6D(i, 5);
/* 2188 */     } else if (str.equals("ScaleUp")) {
/* 2189 */       setButtonAction6D(i, 6);
/* 2190 */     } else if (str.equals("ScaleDown")) {
/* 2191 */       setButtonAction6D(i, 7);
/* 2192 */     } else if (str.equals("None")) {
/* 2193 */       setButtonAction6D(i, 0);
/*      */     } else {
/* 2195 */       throw new IllegalArgumentException("\nButtonAction6D must be GrabView, TranslateForward, TranslateBackward, RotateCCW, RotateCW, ScaleUp, ScaleDown, or None");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setButtonAction6D(int paramInt1, int paramInt2) {
/* 2253 */     if (paramInt2 != 2 && paramInt2 != 3 && paramInt2 != 1 && paramInt2 != 4 && paramInt2 != 5 && paramInt2 != 6 && paramInt2 != 7 && paramInt2 != 0)
/*      */     {
/*      */ 
/*      */       
/* 2257 */       throw new IllegalArgumentException("\naction must be TRANSLATE_FORWARD, TRANSLATE_BACKWARD, GRAB_VIEW, ROTATE_CCW, ROTATE_CW, SCALE_UP, SCALE_DOWN, or NONE");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2262 */     while (paramInt1 >= this.buttonActions6D.size()) {
/* 2263 */       this.buttonActions6D.add(null);
/*      */     }
/* 2265 */     this.buttonActions6D.set(paramInt1, new Integer(paramInt2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getButtonAction6D(int paramInt) {
/* 2275 */     if (paramInt >= this.buttonActions6D.size()) {
/* 2276 */       return 0;
/*      */     }
/* 2278 */     Integer integer = (Integer)this.buttonActions6D.get(paramInt);
/* 2279 */     if (integer == null) {
/* 2280 */       return 0;
/*      */     }
/* 2282 */     return integer.intValue();
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
/*      */   public void ReadAction2D(Object[] paramArrayOfObject) {
/* 2350 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof String)) {
/* 2351 */       throw new IllegalArgumentException("\nReadAction2D must be a String");
/*      */     }
/*      */     
/* 2354 */     String str = (String)paramArrayOfObject[0];
/*      */     
/* 2356 */     if (str.equals("Rotation")) {
/* 2357 */       setReadAction2D(10);
/* 2358 */     } else if (str.equals("Translation")) {
/* 2359 */       setReadAction2D(8);
/* 2360 */     } else if (str.equals("Scale")) {
/* 2361 */       setReadAction2D(9);
/* 2362 */     } else if (str.equals("None")) {
/* 2363 */       setReadAction2D(0);
/*      */     } else {
/* 2365 */       throw new IllegalArgumentException("\nReadAction2D must be Rotation, Translation, Scale, or None");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReadAction2D(int paramInt) {
/* 2433 */     if (paramInt != 10 && paramInt != 8 && paramInt != 9 && paramInt != 0)
/*      */     {
/* 2435 */       throw new IllegalArgumentException("\nReadAction2D must be ROTATION, TRANSLATION, SCALE, or NONE");
/*      */     }
/*      */ 
/*      */     
/* 2439 */     this.readAction2D = paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReadAction2D() {
/* 2448 */     if (this.readAction2D == -1) {
/* 2449 */       return 0;
/*      */     }
/* 2451 */     return this.readAction2D;
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
/*      */   public void ButtonAction2D(Object[] paramArrayOfObject) {
/* 2490 */     if (paramArrayOfObject.length != 2 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof String))
/*      */     {
/* 2492 */       throw new IllegalArgumentException("\nButtonAction2D must be a number and a string");
/*      */     }
/*      */     
/* 2495 */     int i = ((Double)paramArrayOfObject[0]).intValue();
/* 2496 */     String str = (String)paramArrayOfObject[1];
/*      */     
/* 2498 */     if (str.equals("Rotation")) {
/* 2499 */       setButtonAction2D(i, 10);
/* 2500 */     } else if (str.equals("Translation")) {
/* 2501 */       setButtonAction2D(i, 8);
/* 2502 */     } else if (str.equals("Scale")) {
/* 2503 */       setButtonAction2D(i, 9);
/* 2504 */     } else if (str.equals("None")) {
/* 2505 */       setButtonAction2D(i, 0);
/*      */     } else {
/* 2507 */       throw new IllegalArgumentException("\nButtonAction2D must be Rotation, Translation, Scale or None");
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
/*      */   public void setButtonAction2D(int paramInt1, int paramInt2) {
/* 2545 */     if (paramInt2 != 10 && paramInt2 != 8 && paramInt2 != 9 && paramInt2 != 0)
/*      */     {
/* 2547 */       throw new IllegalArgumentException("\naction must be ROTATION, TRANSLATION, SCALE, or NONE");
/*      */     }
/*      */     
/* 2550 */     while (paramInt1 >= this.buttonActions2D.size()) {
/* 2551 */       this.buttonActions2D.add(null);
/*      */     }
/* 2553 */     this.buttonActions2D.set(paramInt1, new Integer(paramInt2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getButtonAction2D(int paramInt) {
/* 2563 */     if (paramInt >= this.buttonActions2D.size()) {
/* 2564 */       return 0;
/*      */     }
/* 2566 */     Integer integer = (Integer)this.buttonActions2D.get(paramInt);
/* 2567 */     if (integer == null) {
/* 2568 */       return 0;
/*      */     }
/* 2570 */     return integer.intValue();
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
/*      */   public void ReadAction6D(Object[] paramArrayOfObject) {
/* 2594 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof String)) {
/* 2595 */       throw new IllegalArgumentException("\nReadAction6D must be a String");
/*      */     }
/*      */     
/* 2598 */     String str = (String)paramArrayOfObject[0];
/*      */     
/* 2600 */     if (str.equals("Echo")) {
/* 2601 */       setReadAction6D(22);
/* 2602 */     } else if (str.equals("None")) {
/* 2603 */       setReadAction6D(0);
/*      */     } else {
/* 2605 */       throw new IllegalArgumentException("\nReadAction6D must be Echo or None");
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
/*      */   public void setReadAction6D(int paramInt) {
/* 2627 */     if (paramInt != 22 && paramInt != 0) {
/* 2628 */       throw new IllegalArgumentException("\naction must be ECHO or NONE");
/*      */     }
/*      */     
/* 2631 */     this.readAction6D = paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReadAction6D() {
/* 2640 */     if (this.readAction6D == -1) {
/* 2641 */       return 0;
/*      */     }
/* 2643 */     return this.readAction6D;
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
/*      */   public void TranslationSpeed(Object[] paramArrayOfObject) {
/*      */     byte b2, b1;
/* 2661 */     if (paramArrayOfObject.length != 3 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof String) || !(paramArrayOfObject[2] instanceof String))
/*      */     {
/* 2663 */       throw new IllegalArgumentException("\nTranslationSpeed must be number, units, and time base");
/*      */     }
/*      */     
/* 2666 */     double d = ((Double)paramArrayOfObject[0]).doubleValue();
/* 2667 */     String str1 = (String)paramArrayOfObject[1];
/* 2668 */     String str2 = (String)paramArrayOfObject[2];
/*      */ 
/*      */     
/* 2671 */     if (str1.equals("PhysicalMeters")) {
/* 2672 */       b1 = 14;
/* 2673 */     } else if (str1.equals("VirtualUnits")) {
/* 2674 */       b1 = 13;
/*      */     } else {
/* 2676 */       throw new IllegalArgumentException("\nTranslationSpeed units must be PhysicalMeters or VirtualUnits");
/*      */     } 
/*      */ 
/*      */     
/* 2680 */     if (str2.equals("PerFrame")) {
/* 2681 */       b2 = 11;
/* 2682 */     } else if (str2.equals("PerSecond")) {
/* 2683 */       b2 = 12;
/*      */     } else {
/* 2685 */       throw new IllegalArgumentException("\ntime base must be PerFrame or PerSecond");
/*      */     } 
/*      */     
/* 2688 */     setTranslationSpeed(d, b1, b2);
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
/*      */   public void setTranslationSpeed(double paramDouble, int paramInt1, int paramInt2) {
/* 2702 */     this.translationSpeed = paramDouble;
/*      */     
/* 2704 */     if (paramInt1 == 14 || paramInt1 == 13) {
/* 2705 */       this.translationUnits = paramInt1;
/*      */     } else {
/* 2707 */       throw new IllegalArgumentException("\ntranslation speed units must be PHYSICAL_METERS or VIRTUAL_UNITS");
/*      */     } 
/*      */ 
/*      */     
/* 2711 */     if (paramInt2 == 11 || paramInt2 == 12) {
/* 2712 */       this.translationTimeBase = paramInt2;
/*      */     } else {
/* 2714 */       throw new IllegalArgumentException("\ntranslation time base must be PER_FRAME or PER_SECOND");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2724 */   public double getTranslationSpeed() { return this.translationSpeed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2733 */   public int getTranslationUnits() { return this.translationUnits; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2742 */   public int getTranslationTimeBase() { return this.translationTimeBase; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void AccelerationTime(Object[] paramArrayOfObject) {
/* 2759 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/* 2760 */       throw new IllegalArgumentException("\nAccelerationTime must be a number");
/*      */     }
/*      */     
/* 2763 */     setAccelerationTime(((Double)paramArrayOfObject[0]).doubleValue());
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
/* 2774 */   public void setAccelerationTime(double paramDouble) { this.accelerationTime = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2784 */   public double getAccelerationTime() { return this.accelerationTime; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ConstantSpeedTime(Object[] paramArrayOfObject) {
/* 2799 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/* 2800 */       throw new IllegalArgumentException("\nConstantSpeedTime must be a number");
/*      */     }
/*      */     
/* 2803 */     setConstantSpeedTime(((Double)paramArrayOfObject[0]).doubleValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2813 */   public void setConstantSpeedTime(double paramDouble) { this.constantSpeedTime = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2823 */   public double getConstantSpeedTime() { return this.constantSpeedTime; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void FastSpeedFactor(Object[] paramArrayOfObject) {
/* 2838 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/* 2839 */       throw new IllegalArgumentException("\nFastSpeedFactor must be a number");
/*      */     }
/*      */     
/* 2842 */     setFastSpeedFactor(((Double)paramArrayOfObject[0]).doubleValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2852 */   public void setFastSpeedFactor(double paramDouble) { this.fastSpeedFactor = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2862 */   public double getFastSpeedFactor() { return this.fastSpeedFactor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void Threshold2D(Object[] paramArrayOfObject) {
/* 2878 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/* 2879 */       throw new IllegalArgumentException("\nThreshold2D must be a number");
/*      */     }
/*      */     
/* 2882 */     setThreshold2D(((Double)paramArrayOfObject[0]).doubleValue());
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
/* 2893 */   public void setThreshold2D(double paramDouble) { this.threshold2D = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2902 */   public double getThreshold2D() { return this.threshold2D; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void MatrixIndices2D(Object[] paramArrayOfObject) {
/* 2919 */     if (paramArrayOfObject.length != 2 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof Double))
/*      */     {
/* 2921 */       throw new IllegalArgumentException("\nMatrixIndices2D must be a numbers");
/*      */     }
/*      */     
/* 2924 */     setMatrixIndices2D(((Double)paramArrayOfObject[0]).intValue(), ((Double)paramArrayOfObject[1]).intValue());
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
/*      */   public void setMatrixIndices2D(int paramInt1, int paramInt2) {
/* 2937 */     this.x2D = paramInt1;
/* 2938 */     this.y2D = paramInt2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2948 */   public int getMatrixXIndex2D() { return this.x2D; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2958 */   public int getMatrixYIndex2D() { return this.y2D; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void RotationSpeed(Object[] paramArrayOfObject) {
/*      */     byte b2, b1;
/* 2976 */     if (paramArrayOfObject.length != 3 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof String) || !(paramArrayOfObject[2] instanceof String))
/*      */     {
/* 2978 */       throw new IllegalArgumentException("\nRotationSpeed must be number, units, and time base");
/*      */     }
/*      */     
/* 2981 */     double d = ((Double)paramArrayOfObject[0]).doubleValue();
/* 2982 */     String str1 = (String)paramArrayOfObject[1];
/* 2983 */     String str2 = (String)paramArrayOfObject[2];
/*      */ 
/*      */     
/* 2986 */     if (str1.equals("Degrees")) {
/* 2987 */       b1 = 16;
/* 2988 */     } else if (str1.equals("Radians")) {
/* 2989 */       b1 = 15;
/*      */     } else {
/* 2991 */       throw new IllegalArgumentException("\nRotationSpeed units must be Degrees or Radians");
/*      */     } 
/*      */     
/* 2994 */     if (str2.equals("PerFrame")) {
/* 2995 */       b2 = 11;
/* 2996 */     } else if (str2.equals("PerSecond")) {
/* 2997 */       b2 = 12;
/*      */     } else {
/* 2999 */       throw new IllegalArgumentException("\nRotationSpeed time base must be PerFrame or PerSecond");
/*      */     } 
/*      */     
/* 3002 */     setRotationSpeed(d, b1, b2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationSpeed(double paramDouble, int paramInt1, int paramInt2) {
/* 3013 */     this.rotationSpeed = paramDouble;
/*      */     
/* 3015 */     if (paramInt1 == 16 || paramInt1 == 15) {
/* 3016 */       this.rotationUnits = paramInt1;
/*      */     } else {
/* 3018 */       throw new IllegalArgumentException("\nrotation speed units must be DEGREES or RADIANS");
/*      */     } 
/*      */     
/* 3021 */     if (paramInt2 == 11 || paramInt2 == 12) {
/* 3022 */       this.rotationTimeBase = paramInt2;
/*      */     } else {
/* 3024 */       throw new IllegalArgumentException("\nrotation time base must be PER_FRAME or PER_SECOND");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3034 */   public double getRotationSpeed() { return this.rotationSpeed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3043 */   public int getRotationUnits() { return this.rotationUnits; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3052 */   public int getRotationTimeBase() { return this.rotationTimeBase; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void RotationCoords(Object[] paramArrayOfObject) {
/* 3074 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof String)) {
/* 3075 */       throw new IllegalArgumentException("\nRotationCoords must be a String");
/*      */     }
/*      */     
/* 3078 */     String str = (String)paramArrayOfObject[0];
/*      */     
/* 3080 */     if (str.equals("Sensor")) {
/* 3081 */       setRotationCoords(19);
/* 3082 */     } else if (str.equals("ViewPlatform")) {
/* 3083 */       setRotationCoords(17);
/* 3084 */     } else if (str.equals("Head")) {
/* 3085 */       setRotationCoords(18);
/*      */     } else {
/* 3087 */       throw new IllegalArgumentException("\nRotationCoords must be Sensor, ViewPlatform, or Head");
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
/*      */   public void setRotationCoords(int paramInt) {
/* 3104 */     if (paramInt != 19 && paramInt != 17 && paramInt != 18) {
/* 3105 */       throw new IllegalArgumentException("\nrotation coordinates be SENSOR, VIEW_PLATFORM, or HEAD");
/*      */     }
/*      */     
/* 3108 */     this.rotationCoords = paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3117 */   public int getRotationCoords() { return this.rotationCoords; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ScaleSpeed(Object[] paramArrayOfObject) {
/*      */     byte b;
/* 3149 */     if (paramArrayOfObject.length != 2 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof String))
/*      */     {
/* 3151 */       throw new IllegalArgumentException("\nScalingSpeed must be a number and a string");
/*      */     }
/*      */     
/* 3154 */     double d = ((Double)paramArrayOfObject[0]).doubleValue();
/* 3155 */     String str = (String)paramArrayOfObject[2];
/*      */ 
/*      */     
/* 3158 */     if (str.equals("PerFrame")) {
/* 3159 */       b = 11;
/* 3160 */     } else if (str.equals("PerSecond")) {
/* 3161 */       b = 12;
/*      */     } else {
/* 3163 */       throw new IllegalArgumentException("\nScalingSpeed time base must be PerFrame or PerSecond");
/*      */     } 
/*      */     
/* 3166 */     setScaleSpeed(d, b);
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
/*      */   public void setScaleSpeed(double paramDouble, int paramInt) {
/* 3190 */     this.scaleSpeed = paramDouble;
/*      */     
/* 3192 */     if (paramInt == 11 || paramInt == 12) {
/* 3193 */       this.scaleTimeBase = paramInt;
/*      */     } else {
/* 3195 */       throw new IllegalArgumentException("\nscaling time base must be PER_FRAME or PER_SECOND");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3205 */   public double getScaleSpeed() { return this.scaleSpeed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3214 */   public int getScaleTimeBase() { return this.scaleTimeBase; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void TransformCenterSource(Object[] paramArrayOfObject) {
/* 3234 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof String)) {
/* 3235 */       throw new IllegalArgumentException("\nTransformCenterSource must be a String");
/*      */     }
/*      */     
/* 3238 */     String str = (String)paramArrayOfObject[0];
/*      */     
/* 3240 */     if (str.equals("Hotspot")) {
/* 3241 */       setTransformCenterSource(21);
/* 3242 */     } else if (str.equals("VworldFixed")) {
/* 3243 */       setTransformCenterSource(20);
/*      */     } else {
/* 3245 */       throw new IllegalArgumentException("\nTransformCenterSource must be Hotspot or VworldFixed");
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
/*      */   public void setTransformCenterSource(int paramInt) {
/* 3264 */     if (paramInt != 21 && paramInt != 20) {
/* 3265 */       throw new IllegalArgumentException("\nrotation/scale center source must be HOTSPOT or VWORLD_FIXED");
/*      */     }
/*      */ 
/*      */     
/* 3269 */     this.transformCenterSource = paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3278 */   public int getTransformCenterSource() { return this.transformCenterSource; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void TransformCenter(Object[] paramArrayOfObject) {
/* 3295 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Point3d)) {
/* 3296 */       throw new IllegalArgumentException("\nTransformCenter must be a Point3d");
/*      */     }
/*      */     
/* 3299 */     setTransformCenter((Point3d)paramArrayOfObject[0]);
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
/* 3315 */   public void setTransformCenter(Point3d paramPoint3d) { this.transformCenter.set(paramPoint3d); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3324 */   public void getTransformCenter(Point3d paramPoint3d) { paramPoint3d.set(this.transformCenter); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void NominalSensorRotation(Object[] paramArrayOfObject) {
/* 3368 */     if (paramArrayOfObject.length != 1 || (!(paramArrayOfObject[0] instanceof Matrix3d) && !(paramArrayOfObject[0] instanceof Matrix4d)))
/*      */     {
/* 3370 */       throw new IllegalArgumentException("\nNominalSensorRotation must be a Matrix3d or Matrix4d");
/*      */     }
/*      */     
/* 3373 */     Transform3D transform3D = new Transform3D();
/*      */     
/* 3375 */     if (paramArrayOfObject[0] instanceof Matrix3d) {
/* 3376 */       transform3D.set((Matrix3d)paramArrayOfObject[0]);
/*      */     } else {
/* 3378 */       transform3D.set((Matrix4d)paramArrayOfObject[0]);
/*      */     } 
/* 3380 */     setNominalSensorRotation(transform3D);
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
/*      */   public void setNominalSensorRotation(Transform3D paramTransform3D) {
/* 3418 */     if (paramTransform3D == null) {
/* 3419 */       this.nominalSensorRotation = null;
/*      */       
/*      */       return;
/*      */     } 
/* 3423 */     if (this.nominalSensorRotation == null) {
/* 3424 */       this.nominalSensorRotation = new Transform3D();
/*      */     }
/*      */     
/* 3427 */     this.nominalSensorRotation.set(paramTransform3D);
/* 3428 */     this.nominalSensorRotation.setTranslation(new Vector3d());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3438 */   public void getNominalSensorRotation(Transform3D paramTransform3D) { paramTransform3D.set(this.nominalSensorRotation); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ResetViewButtonCount6D(Object[] paramArrayOfObject) {
/* 3458 */     if (paramArrayOfObject.length != 1 || (!(paramArrayOfObject[0] instanceof Double) && !(paramArrayOfObject[0] instanceof String)))
/*      */     {
/* 3460 */       throw new IllegalArgumentException("\nResetViewButtonCount6D must be a number or None");
/*      */     }
/*      */     
/* 3463 */     if (paramArrayOfObject[0] instanceof String) {
/* 3464 */       String str = (String)paramArrayOfObject[0];
/* 3465 */       if (str.equals("None")) {
/* 3466 */         setResetViewButtonCount6D(0);
/*      */       } else {
/* 3468 */         throw new IllegalArgumentException("\nResetViewButtonCount6D string value must be None");
/*      */       } 
/*      */     } else {
/*      */       
/* 3472 */       setResetViewButtonCount6D(((Double)paramArrayOfObject[0]).intValue());
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
/*      */   public void setResetViewButtonCount6D(int paramInt) {
/* 3487 */     if (paramInt == 0 || paramInt > 1) {
/* 3488 */       this.resetViewButtonCount6D = paramInt;
/*      */     } else {
/*      */       
/* 3491 */       throw new IllegalArgumentException("reset view button count must be > 1");
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
/* 3504 */   public int getResetViewButtonCount6D() { return this.resetViewButtonCount6D; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ResetViewButtonCount2D(Object[] paramArrayOfObject) {
/* 3525 */     if (paramArrayOfObject.length != 1 || (!(paramArrayOfObject[0] instanceof Double) && !(paramArrayOfObject[0] instanceof String)))
/*      */     {
/* 3527 */       throw new IllegalArgumentException("\nResetViewButtonCount2D must be a number or None");
/*      */     }
/*      */     
/* 3530 */     if (paramArrayOfObject[0] instanceof String) {
/* 3531 */       String str = (String)paramArrayOfObject[0];
/* 3532 */       if (str.equals("None")) {
/* 3533 */         setResetViewButtonCount2D(0);
/*      */       } else {
/* 3535 */         throw new IllegalArgumentException("\nResetViewButtonCount2D string value must be None");
/*      */       } 
/*      */     } else {
/*      */       
/* 3539 */       setResetViewButtonCount2D(((Double)paramArrayOfObject[0]).intValue());
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
/*      */   public void setResetViewButtonCount2D(int paramInt) {
/* 3554 */     if (paramInt == 0 || paramInt > 1) {
/* 3555 */       this.resetViewButtonCount2D = paramInt;
/*      */     } else {
/*      */       
/* 3558 */       throw new IllegalArgumentException("reset view button count must be > 1");
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
/* 3571 */   public int getResetViewButtonCount2D() { return this.resetViewButtonCount2D; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void EchoType(Object[] paramArrayOfObject) {
/* 3593 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof String)) {
/* 3594 */       throw new IllegalArgumentException("\nEchoType must be a String");
/*      */     }
/*      */     
/* 3597 */     String str = (String)paramArrayOfObject[0];
/*      */     
/* 3599 */     if (str.equals("Gnomon")) {
/* 3600 */       setEchoType(23);
/* 3601 */     } else if (str.equals("Beam")) {
/* 3602 */       setEchoType(24);
/* 3603 */     } else if (str.equals("None")) {
/* 3604 */       setEchoType(0);
/*      */     } else {
/* 3606 */       throw new IllegalArgumentException("\nEchoType must be Gnomon, Beam, or None");
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
/* 3624 */   public void setEchoType(int paramInt) { this.echoType = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3633 */   public int getEchoType() { return this.echoType; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void EchoSize(Object[] paramArrayOfObject) {
/* 3649 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/* 3650 */       throw new IllegalArgumentException("\nEchoSize must be a Double");
/*      */     }
/*      */     
/* 3653 */     setEchoSize(((Double)paramArrayOfObject[0]).doubleValue());
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
/* 3664 */   public void setEchoSize(double paramDouble) { this.echoSize = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3673 */   public double getEchoSize() { return this.echoSize; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void EchoColor(Object[] paramArrayOfObject) {
/* 3688 */     if (paramArrayOfObject.length != 3 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof Double) || !(paramArrayOfObject[2] instanceof Double))
/*      */     {
/* 3690 */       throw new IllegalArgumentException("\nEchoColor must be 3 numbers for red, green, and blue");
/*      */     }
/*      */     
/* 3693 */     setEchoColor(new Color3f(((Double)paramArrayOfObject[0]).floatValue(), ((Double)paramArrayOfObject[1]).floatValue(), ((Double)paramArrayOfObject[2]).floatValue()));
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
/*      */   public void setEchoColor(Color3f paramColor3f) {
/* 3706 */     if (this.echoColor == null) {
/* 3707 */       this.echoColor = new Color3f(paramColor3f);
/*      */     } else {
/* 3709 */       this.echoColor.set(paramColor3f);
/*      */     } 
/* 3711 */     if (this.echoGeometry != null) {
/* 3712 */       Appearance appearance = this.echoGeometry.getAppearance();
/* 3713 */       Material material = appearance.getMaterial();
/* 3714 */       material.setDiffuseColor(this.echoColor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getEchoColor(Color3f paramColor3f) {
/* 3724 */     if (this.echoColor == null) {
/* 3725 */       paramColor3f.set(1.0F, 1.0F, 1.0F);
/*      */     } else {
/* 3727 */       paramColor3f.set(this.echoColor);
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
/*      */   public void EchoTransparency(Object[] paramArrayOfObject) {
/* 3743 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/* 3744 */       throw new IllegalArgumentException("\nEchoTransparency must be a number");
/*      */     }
/*      */     
/* 3747 */     setEchoTransparency(((Double)paramArrayOfObject[0]).floatValue());
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
/*      */   public void setEchoTransparency(float paramFloat) {
/* 3759 */     this.echoTransparency = paramFloat;
/*      */     
/* 3761 */     if (this.echoGeometry != null) {
/* 3762 */       Appearance appearance = this.echoGeometry.getAppearance();
/* 3763 */       TransparencyAttributes transparencyAttributes = appearance.getTransparencyAttributes();
/* 3764 */       if (this.echoTransparency == 0.0F) {
/* 3765 */         transparencyAttributes.setTransparencyMode(4);
/* 3766 */         transparencyAttributes.setTransparency(0.0F);
/*      */       } else {
/*      */         
/* 3769 */         transparencyAttributes.setTransparencyMode(2);
/* 3770 */         transparencyAttributes.setTransparency(this.echoTransparency);
/*      */         
/* 3772 */         if (this.echoGeometry instanceof SensorGnomonEcho) {
/* 3773 */           transparencyAttributes.setDstBlendFunction(1);
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
/* 3784 */   public float getEchoTransparency() { return this.echoTransparency; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEchoTransformGroup(TransformGroup paramTransformGroup) {
/* 3801 */     paramTransformGroup.setCapability(18);
/* 3802 */     paramTransformGroup.setCapability(12);
/* 3803 */     paramTransformGroup.setCapability(13);
/* 3804 */     paramTransformGroup.setCapability(14);
/* 3805 */     this.echoTransformGroup = paramTransformGroup;
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
/* 3816 */   public TransformGroup getEchoTransformGroup() { return this.echoTransformGroup; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3830 */   public Shape3D getEchoGeometry() { return this.echoGeometry; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3841 */   public SensorEventAgent getSensorEventAgent() { return this.eventAgent; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\behaviors\vp\WandViewBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */