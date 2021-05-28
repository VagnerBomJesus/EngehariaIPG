/*      */ package com.sun.j3d.utils.behaviors.vp;
/*      */ 
/*      */ import com.sun.j3d.internal.J3dUtilsI18N;
/*      */ import com.sun.j3d.utils.universe.ViewingPlatform;
/*      */ import java.awt.AWTEvent;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseWheelEvent;
/*      */ import javax.media.j3d.Canvas3D;
/*      */ import javax.media.j3d.Transform3D;
/*      */ import javax.vecmath.Matrix3d;
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
/*      */ public class OrbitBehavior
/*      */   extends ViewPlatformAWTBehavior
/*      */ {
/*  130 */   private Transform3D velocityTransform = new Transform3D();
/*  131 */   private Transform3D longditudeTransform = new Transform3D();
/*  132 */   private Transform3D rollTransform = new Transform3D();
/*  133 */   private Transform3D latitudeTransform = new Transform3D();
/*  134 */   private Transform3D rotateTransform = new Transform3D();
/*      */ 
/*      */   
/*  137 */   private Transform3D temp1 = new Transform3D();
/*  138 */   private Transform3D temp2 = new Transform3D();
/*  139 */   private Transform3D translation = new Transform3D();
/*  140 */   private Vector3d transVector = new Vector3d();
/*  141 */   private Vector3d distanceVector = new Vector3d();
/*  142 */   private Vector3d centerVector = new Vector3d();
/*  143 */   private Vector3d invertCenterVector = new Vector3d();
/*      */   
/*  145 */   private double longditude = 0.0D;
/*  146 */   private double latitude = 0.0D;
/*  147 */   private double rollAngle = 0.0D;
/*  148 */   private double startDistanceFromCenter = 20.0D;
/*  149 */   private double distanceFromCenter = 20.0D;
/*  150 */   private final double MAX_MOUSE_ANGLE = Math.toRadians(3.0D);
/*  151 */   private final double ZOOM_FACTOR = 1.0D;
/*  152 */   private Point3d rotationCenter = new Point3d();
/*  153 */   private Matrix3d rotMatrix = new Matrix3d();
/*  154 */   private Transform3D currentXfm = new Transform3D();
/*      */   
/*  156 */   private int mouseX = 0;
/*  157 */   private int mouseY = 0;
/*      */   
/*  159 */   private double rotXFactor = 1.0D;
/*  160 */   private double rotYFactor = 1.0D;
/*  161 */   private double transXFactor = 1.0D;
/*  162 */   private double transYFactor = 1.0D;
/*  163 */   private double zoomFactor = 1.0D;
/*      */   
/*  165 */   private double xtrans = 0.0D;
/*  166 */   private double ytrans = 0.0D;
/*  167 */   private double ztrans = 0.0D;
/*      */   
/*      */   private boolean zoomEnabled = true;
/*      */   private boolean rotateEnabled = true;
/*      */   private boolean translateEnabled = true;
/*      */   private boolean reverseRotate = false;
/*      */   private boolean reverseTrans = false;
/*      */   private boolean reverseZoom = false;
/*      */   private boolean stopZoom = false;
/*      */   private boolean proportionalZoom = false;
/*  177 */   private double minRadius = 0.0D;
/*  178 */   private int leftButton = 0;
/*  179 */   private int rightButton = 1;
/*  180 */   private int middleButton = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  185 */   private float wheelZoomFactor = 50.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REVERSE_ROTATE = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REVERSE_TRANSLATE = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REVERSE_ZOOM = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REVERSE_ALL = 112;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STOP_ZOOM = 256;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DISABLE_ROTATE = 512;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DISABLE_TRANSLATE = 1024;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DISABLE_ZOOM = 2048;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int PROPORTIONAL_ZOOM = 4096;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ROTATE = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int TRANSLATE = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ZOOM = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final double NOMINAL_ZOOM_FACTOR = 0.01D;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final double NOMINAL_PZOOM_FACTOR = 1.0D;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final double NOMINAL_ROT_FACTOR = 0.01D;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final double NOMINAL_TRANS_FACTOR = 0.01D;
/*      */ 
/*      */ 
/*      */   
/*  258 */   private double rotXMul = 0.01D * this.rotXFactor;
/*  259 */   private double rotYMul = 0.01D * this.rotYFactor;
/*  260 */   private double transXMul = 0.01D * this.transXFactor;
/*  261 */   private double transYMul = 0.01D * this.transYFactor;
/*  262 */   private double zoomMul = 0.01D * this.zoomFactor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  273 */   public OrbitBehavior() { super(11); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  282 */   public OrbitBehavior(Canvas3D paramCanvas3D) { this(paramCanvas3D, 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public OrbitBehavior(Canvas3D paramCanvas3D, int paramInt) {
/*  292 */     super(paramCanvas3D, 0xB | paramInt);
/*      */     
/*  294 */     if ((paramInt & 0x200) != 0) this.rotateEnabled = false; 
/*  295 */     if ((paramInt & 0x800) != 0) this.zoomEnabled = false; 
/*  296 */     if ((paramInt & 0x400) != 0) this.translateEnabled = false; 
/*  297 */     if ((paramInt & 0x20) != 0) this.reverseTrans = true; 
/*  298 */     if ((paramInt & 0x10) != 0) this.reverseRotate = true; 
/*  299 */     if ((paramInt & 0x40) != 0) this.reverseZoom = true; 
/*  300 */     if ((paramInt & 0x100) != 0) this.stopZoom = true; 
/*  301 */     if ((paramInt & 0x1000) != 0) {
/*  302 */       this.proportionalZoom = true;
/*  303 */       this.zoomMul = 1.0D * this.zoomFactor;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void processAWTEvents(AWTEvent[] paramArrayOfAWTEvent) {
/*  308 */     this.motion = false;
/*  309 */     for (byte b = 0; b < paramArrayOfAWTEvent.length; b++) {
/*  310 */       if (paramArrayOfAWTEvent[b] instanceof MouseEvent)
/*  311 */         processMouseEvent((MouseEvent)paramArrayOfAWTEvent[b]); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void processMouseEvent(MouseEvent paramMouseEvent) {
/*  316 */     if (paramMouseEvent.getID() == 501) {
/*  317 */       this.mouseX = paramMouseEvent.getX();
/*  318 */       this.mouseY = paramMouseEvent.getY();
/*  319 */       this.motion = true;
/*  320 */     } else if (paramMouseEvent.getID() == 506) {
/*  321 */       int i = paramMouseEvent.getX() - this.mouseX;
/*  322 */       int j = paramMouseEvent.getY() - this.mouseY;
/*      */       
/*  324 */       if (rotate(paramMouseEvent)) {
/*  325 */         if (this.reverseRotate) {
/*  326 */           this.longditude -= i * this.rotXMul;
/*  327 */           this.latitude -= j * this.rotYMul;
/*      */         } else {
/*      */           
/*  330 */           this.longditude += i * this.rotXMul;
/*  331 */           this.latitude += j * this.rotYMul;
/*      */         }
/*      */       
/*      */       }
/*  335 */       else if (translate(paramMouseEvent)) {
/*  336 */         if (this.reverseTrans) {
/*  337 */           this.xtrans -= i * this.transXMul;
/*  338 */           this.ytrans += j * this.transYMul;
/*      */         } else {
/*      */           
/*  341 */           this.xtrans += i * this.transXMul;
/*  342 */           this.ytrans -= j * this.transYMul;
/*      */         }
/*      */       
/*      */       }
/*  346 */       else if (zoom(paramMouseEvent)) {
/*  347 */         doZoomOperations(j);
/*      */       } 
/*  349 */       this.mouseX = paramMouseEvent.getX();
/*  350 */       this.mouseY = paramMouseEvent.getY();
/*  351 */       this.motion = true;
/*  352 */     } else if (paramMouseEvent.getID() != 502 && 
/*  353 */       paramMouseEvent.getID() == 507 && 
/*  354 */       zoom(paramMouseEvent)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  359 */       if (paramMouseEvent instanceof MouseWheelEvent) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  368 */         int i = (int)(((MouseWheelEvent)paramMouseEvent).getWheelRotation() * this.wheelZoomFactor);
/*      */ 
/*      */         
/*  371 */         doZoomOperations(i);
/*  372 */         this.motion = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doZoomOperations(int paramInt) {
/*  381 */     if (this.proportionalZoom) {
/*  382 */       if (this.reverseZoom) {
/*  383 */         if (this.distanceFromCenter - this.zoomMul * paramInt * this.distanceFromCenter / 100.0D > this.minRadius)
/*      */         {
/*      */           
/*  386 */           this.distanceFromCenter -= this.zoomMul * paramInt * this.distanceFromCenter / 100.0D;
/*      */         }
/*      */         else
/*      */         {
/*  390 */           this.distanceFromCenter = this.minRadius;
/*      */         }
/*      */       
/*      */       }
/*  394 */       else if (this.distanceFromCenter + this.zoomMul * paramInt * this.distanceFromCenter / 100.0D > this.minRadius) {
/*      */ 
/*      */         
/*  397 */         this.distanceFromCenter += this.zoomMul * paramInt * this.distanceFromCenter / 100.0D;
/*      */       }
/*      */       else {
/*      */         
/*  401 */         this.distanceFromCenter = this.minRadius;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  406 */     else if (this.stopZoom) {
/*  407 */       if (this.reverseZoom) {
/*  408 */         if (this.distanceFromCenter - paramInt * this.zoomMul > this.minRadius) {
/*  409 */           this.distanceFromCenter -= paramInt * this.zoomMul;
/*      */         } else {
/*      */           
/*  412 */           this.distanceFromCenter = this.minRadius;
/*      */         }
/*      */       
/*      */       }
/*  416 */       else if (this.distanceFromCenter + paramInt * this.zoomMul > this.minRadius) {
/*  417 */         this.distanceFromCenter += paramInt * this.zoomMul;
/*      */       } else {
/*      */         
/*  420 */         this.distanceFromCenter = this.minRadius;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  425 */     else if (this.reverseZoom) {
/*  426 */       this.distanceFromCenter -= paramInt * this.zoomMul;
/*      */     } else {
/*      */       
/*  429 */       this.distanceFromCenter += paramInt * this.zoomMul;
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
/*      */   public void setViewingPlatform(ViewingPlatform paramViewingPlatform) {
/*  445 */     super.setViewingPlatform(paramViewingPlatform);
/*      */     
/*  447 */     if (paramViewingPlatform != null) {
/*  448 */       resetView();
/*  449 */       integrateTransforms();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetView() {
/*  458 */     Vector3d vector3d = new Vector3d();
/*      */     
/*  460 */     this.targetTG.getTransform(this.targetTransform);
/*      */     
/*  462 */     this.targetTransform.get(this.rotMatrix, this.transVector);
/*  463 */     vector3d.sub(this.transVector, this.rotationCenter);
/*  464 */     this.distanceFromCenter = vector3d.length();
/*  465 */     this.startDistanceFromCenter = this.distanceFromCenter;
/*      */     
/*  467 */     this.targetTransform.get(this.rotMatrix);
/*  468 */     this.rotateTransform.set(this.rotMatrix);
/*      */ 
/*      */     
/*  471 */     this.temp1.set(vector3d);
/*  472 */     this.rotateTransform.invert();
/*  473 */     this.rotateTransform.mul(this.temp1);
/*  474 */     this.rotateTransform.get(vector3d);
/*  475 */     this.xtrans = vector3d.x;
/*  476 */     this.ytrans = vector3d.y;
/*  477 */     this.ztrans = vector3d.z;
/*      */ 
/*      */     
/*  480 */     this.rotateTransform.set(this.rotMatrix);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void integrateTransforms() {
/*  486 */     this.targetTG.getTransform(this.currentXfm);
/*  487 */     if (!this.targetTransform.equals(this.currentXfm)) {
/*  488 */       resetView();
/*      */     }
/*  490 */     this.longditudeTransform.rotY(this.longditude);
/*  491 */     this.latitudeTransform.rotX(this.latitude);
/*  492 */     this.rotateTransform.mul(this.rotateTransform, this.latitudeTransform);
/*  493 */     this.rotateTransform.mul(this.rotateTransform, this.longditudeTransform);
/*      */     
/*  495 */     this.distanceVector.z = this.distanceFromCenter - this.startDistanceFromCenter;
/*      */     
/*  497 */     this.temp1.set(this.distanceVector);
/*  498 */     this.temp1.mul(this.rotateTransform, this.temp1);
/*      */ 
/*      */     
/*  501 */     this.transVector.x = this.rotationCenter.x + this.xtrans;
/*  502 */     this.transVector.y = this.rotationCenter.y + this.ytrans;
/*  503 */     this.transVector.z = this.rotationCenter.z + this.ztrans;
/*      */     
/*  505 */     this.translation.set(this.transVector);
/*  506 */     this.targetTransform.mul(this.temp1, this.translation);
/*      */ 
/*      */     
/*  509 */     this.temp1.set(this.centerVector);
/*  510 */     this.temp1.mul(this.targetTransform);
/*      */     
/*  512 */     this.invertCenterVector.x = -this.centerVector.x;
/*  513 */     this.invertCenterVector.y = -this.centerVector.y;
/*  514 */     this.invertCenterVector.z = -this.centerVector.z;
/*      */     
/*  516 */     this.temp2.set(this.invertCenterVector);
/*  517 */     this.targetTransform.mul(this.temp1, this.temp2);
/*      */     
/*  519 */     this.targetTG.setTransform(this.targetTransform);
/*      */ 
/*      */     
/*  522 */     this.longditude = 0.0D;
/*  523 */     this.latitude = 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationCenter(Point3d paramPoint3d) {
/*  532 */     this.rotationCenter.x = paramPoint3d.x;
/*  533 */     this.rotationCenter.y = paramPoint3d.y;
/*  534 */     this.rotationCenter.z = paramPoint3d.z;
/*  535 */     this.centerVector.set(this.rotationCenter);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void RotationCenter(Object[] paramArrayOfObject) {
/*  545 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Point3d)) {
/*  546 */       throw new IllegalArgumentException("RotationCenter must be a single Point3d");
/*      */     }
/*      */     
/*  549 */     setRotationCenter((Point3d)paramArrayOfObject[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getRotationCenter(Point3d paramPoint3d) {
/*  558 */     paramPoint3d.x = this.rotationCenter.x;
/*  559 */     paramPoint3d.y = this.rotationCenter.y;
/*  560 */     paramPoint3d.z = this.rotationCenter.z;
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
/*      */   public void setRotFactors(double paramDouble1, double paramDouble2) {
/*  580 */     this.rotXFactor = paramDouble1;
/*  581 */     this.rotYFactor = paramDouble2;
/*  582 */     this.rotXMul = 0.01D * paramDouble1;
/*  583 */     this.rotYMul = 0.01D * paramDouble2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void RotFactors(Object[] paramArrayOfObject) {
/*  593 */     if (paramArrayOfObject.length != 2 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof Double))
/*      */     {
/*  595 */       throw new IllegalArgumentException("RotFactors must be two Doubles");
/*      */     }
/*      */     
/*  598 */     setRotFactors(((Double)paramArrayOfObject[0]).doubleValue(), ((Double)paramArrayOfObject[1]).doubleValue());
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
/*      */   public void setRotXFactor(double paramDouble) {
/*  610 */     this.rotXFactor = paramDouble;
/*  611 */     this.rotXMul = 0.01D * paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void RotXFactor(Object[] paramArrayOfObject) {
/*  621 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/*  622 */       throw new IllegalArgumentException("RotXFactor must be a Double");
/*      */     }
/*  624 */     setRotXFactor(((Double)paramArrayOfObject[0]).doubleValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotYFactor(double paramDouble) {
/*  635 */     this.rotYFactor = paramDouble;
/*  636 */     this.rotYMul = 0.01D * paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void RotYFactor(Object[] paramArrayOfObject) {
/*  646 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/*  647 */       throw new IllegalArgumentException("RotYFactor must be a Double");
/*      */     }
/*  649 */     setRotYFactor(((Double)paramArrayOfObject[0]).doubleValue());
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
/*      */   public void setTransFactors(double paramDouble1, double paramDouble2) {
/*  662 */     this.transXFactor = paramDouble1;
/*  663 */     this.transYFactor = paramDouble2;
/*  664 */     this.transXMul = 0.01D * paramDouble1;
/*  665 */     this.transYMul = 0.01D * paramDouble2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void TransFactors(Object[] paramArrayOfObject) {
/*  675 */     if (paramArrayOfObject.length != 2 || !(paramArrayOfObject[0] instanceof Double) || !(paramArrayOfObject[1] instanceof Double))
/*      */     {
/*  677 */       throw new IllegalArgumentException("TransFactors must be two Doubles");
/*      */     }
/*      */     
/*  680 */     setTransFactors(((Double)paramArrayOfObject[0]).doubleValue(), ((Double)paramArrayOfObject[1]).doubleValue());
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
/*      */   public void setTransXFactor(double paramDouble) {
/*  692 */     this.transXFactor = paramDouble;
/*  693 */     this.transXMul = 0.01D * paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void TransXFactor(Object[] paramArrayOfObject) {
/*  703 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/*  704 */       throw new IllegalArgumentException("TransXFactor must be a Double");
/*      */     }
/*  706 */     setTransXFactor(((Double)paramArrayOfObject[0]).doubleValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransYFactor(double paramDouble) {
/*  717 */     this.transYFactor = paramDouble;
/*  718 */     this.transYMul = 0.01D * paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void TransYFactor(Object[] paramArrayOfObject) {
/*  728 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/*  729 */       throw new IllegalArgumentException("TransYFactor must be a Double");
/*      */     }
/*  731 */     setTransYFactor(((Double)paramArrayOfObject[0]).doubleValue());
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
/*      */   public void setZoomFactor(double paramDouble) {
/*  744 */     this.zoomFactor = paramDouble;
/*  745 */     if (this.proportionalZoom) {
/*  746 */       this.zoomMul = 1.0D * paramDouble;
/*      */     } else {
/*      */       
/*  749 */       this.zoomMul = 0.01D * paramDouble;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ZoomFactor(Object[] paramArrayOfObject) {
/*  760 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/*  761 */       throw new IllegalArgumentException("ZoomFactor must be a Double");
/*      */     }
/*  763 */     setZoomFactor(((Double)paramArrayOfObject[0]).doubleValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  771 */   public double getRotXFactor() { return this.rotXFactor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  779 */   public double getRotYFactor() { return this.rotYFactor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  787 */   public double getTransXFactor() { return this.transXFactor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  795 */   public double getTransYFactor() { return this.transYFactor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  803 */   public double getZoomFactor() { return this.zoomFactor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  811 */   public void setRotateEnable(boolean paramBoolean) { this.rotateEnabled = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void RotateEnable(Object[] paramArrayOfObject) {
/*  821 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Boolean)) {
/*  822 */       throw new IllegalArgumentException("RotateEnable must be Boolean");
/*      */     }
/*  824 */     setRotateEnable(((Boolean)paramArrayOfObject[0]).booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  832 */   public void setZoomEnable(boolean paramBoolean) { this.zoomEnabled = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ZoomEnable(Object[] paramArrayOfObject) {
/*  842 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Boolean)) {
/*  843 */       throw new IllegalArgumentException("ZoomEnable must be Boolean");
/*      */     }
/*  845 */     setZoomEnable(((Boolean)paramArrayOfObject[0]).booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  853 */   public void setTranslateEnable(boolean paramBoolean) { this.translateEnabled = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void TranslateEnable(Object[] paramArrayOfObject) {
/*  863 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Boolean)) {
/*  864 */       throw new IllegalArgumentException("TranslateEnable must be Boolean");
/*      */     }
/*      */     
/*  867 */     setTranslateEnable(((Boolean)paramArrayOfObject[0]).booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  875 */   public boolean getRotateEnable() { return this.rotateEnabled; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  883 */   public boolean getZoomEnable() { return this.zoomEnabled; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  891 */   public boolean getTranslateEnable() { return this.translateEnabled; }
/*      */ 
/*      */   
/*      */   boolean rotate(MouseEvent paramMouseEvent) {
/*  895 */     if (this.rotateEnabled) {
/*  896 */       if (this.leftButton == 0 && !paramMouseEvent.isAltDown() && !paramMouseEvent.isMetaDown())
/*      */       {
/*  898 */         return true;
/*      */       }
/*  900 */       if (this.middleButton == 0 && paramMouseEvent.isAltDown() && !paramMouseEvent.isMetaDown())
/*      */       {
/*  902 */         return true;
/*      */       }
/*  904 */       if (this.rightButton == 0 && !paramMouseEvent.isAltDown() && paramMouseEvent.isMetaDown())
/*      */       {
/*  906 */         return true;
/*      */       }
/*      */     } 
/*  909 */     return false;
/*      */   }
/*      */   
/*      */   boolean zoom(MouseEvent paramMouseEvent) {
/*  913 */     if (this.zoomEnabled) {
/*  914 */       if (paramMouseEvent instanceof MouseWheelEvent) {
/*  915 */         return true;
/*      */       }
/*  917 */       if (this.leftButton == 2 && !paramMouseEvent.isAltDown() && !paramMouseEvent.isMetaDown())
/*      */       {
/*  919 */         return true;
/*      */       }
/*  921 */       if (this.middleButton == 2 && paramMouseEvent.isAltDown() && !paramMouseEvent.isMetaDown())
/*      */       {
/*  923 */         return true;
/*      */       }
/*  925 */       if (this.rightButton == 2 && !paramMouseEvent.isAltDown() && paramMouseEvent.isMetaDown())
/*      */       {
/*  927 */         return true;
/*      */       }
/*      */     } 
/*  930 */     return false;
/*      */   }
/*      */   
/*      */   boolean translate(MouseEvent paramMouseEvent) {
/*  934 */     if (this.translateEnabled) {
/*  935 */       if (this.leftButton == 1 && !paramMouseEvent.isAltDown() && !paramMouseEvent.isMetaDown())
/*      */       {
/*  937 */         return true;
/*      */       }
/*  939 */       if (this.middleButton == 1 && paramMouseEvent.isAltDown() && !paramMouseEvent.isMetaDown())
/*      */       {
/*  941 */         return true;
/*      */       }
/*  943 */       if (this.rightButton == 1 && !paramMouseEvent.isAltDown() && paramMouseEvent.isMetaDown())
/*      */       {
/*  945 */         return true;
/*      */       }
/*      */     } 
/*  948 */     return false;
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
/*      */   public void setMinRadius(double paramDouble) {
/*  960 */     if (paramDouble < 0.0D) {
/*  961 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("OrbitBehavior1"));
/*      */     }
/*  963 */     this.minRadius = paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void MinRadius(Object[] paramArrayOfObject) {
/*  973 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Double)) {
/*  974 */       throw new IllegalArgumentException("MinRadius must be a Double");
/*      */     }
/*  976 */     setMinRadius(((Double)paramArrayOfObject[0]).doubleValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  985 */   public double getMinRadius() { return this.minRadius; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  994 */   public void setReverseTranslate(boolean paramBoolean) { this.reverseTrans = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ReverseTranslate(Object[] paramArrayOfObject) {
/* 1004 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Boolean)) {
/* 1005 */       throw new IllegalArgumentException("ReverseTranslate must be Boolean");
/*      */     }
/*      */     
/* 1008 */     setReverseTranslate(((Boolean)paramArrayOfObject[0]).booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1017 */   public void setReverseRotate(boolean paramBoolean) { this.reverseRotate = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ReverseRotate(Object[] paramArrayOfObject) {
/* 1027 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Boolean)) {
/* 1028 */       throw new IllegalArgumentException("ReverseRotate must be Boolean");
/*      */     }
/* 1030 */     setReverseRotate(((Boolean)paramArrayOfObject[0]).booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1039 */   public void setReverseZoom(boolean paramBoolean) { this.reverseZoom = paramBoolean; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ReverseZoom(Object[] paramArrayOfObject) {
/* 1049 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Boolean)) {
/* 1050 */       throw new IllegalArgumentException("ReverseZoom must be Boolean");
/*      */     }
/* 1052 */     setReverseZoom(((Boolean)paramArrayOfObject[0]).booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProportionalZoom(boolean paramBoolean) {
/* 1061 */     this.proportionalZoom = paramBoolean;
/*      */     
/* 1063 */     if (paramBoolean) {
/* 1064 */       this.zoomMul = 1.0D * this.zoomFactor;
/*      */     } else {
/*      */       
/* 1067 */       this.zoomMul = 0.01D * this.zoomFactor;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ProportionalZoom(Object[] paramArrayOfObject) {
/* 1078 */     if (paramArrayOfObject.length != 1 || !(paramArrayOfObject[0] instanceof Boolean)) {
/* 1079 */       throw new IllegalArgumentException("ProportionalZoom must be Boolean");
/*      */     }
/*      */     
/* 1082 */     setProportionalZoom(((Boolean)paramArrayOfObject[0]).booleanValue());
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\vp\OrbitBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */