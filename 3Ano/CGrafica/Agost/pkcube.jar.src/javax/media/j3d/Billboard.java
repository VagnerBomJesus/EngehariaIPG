/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import javax.vecmath.AxisAngle4d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3d;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Billboard
/*     */   extends Behavior
/*     */ {
/*     */   public static final int ROTATE_ABOUT_AXIS = 0;
/*     */   public static final int ROTATE_ABOUT_POINT = 1;
/*     */   WakeupOnElapsedFrames wakeupFrame;
/*     */   int mode;
/*     */   Vector3f axis;
/*     */   Point3f rotationPoint;
/*     */   private Vector3d nAxis;
/*     */   TransformGroup tg;
/*     */   private Point3d viewPosition;
/*     */   private Point3d yUpPoint;
/*     */   private Vector3d eyeVec;
/*     */   private Vector3d yUp;
/*     */   private Vector3d zAxis;
/*     */   private Vector3d yAxis;
/*     */   private Vector3d vector;
/*     */   private AxisAngle4d aa;
/*     */   static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   public Billboard() {
/*  59 */     this.wakeupFrame = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.mode = 0;
/*     */ 
/*     */     
/*  67 */     this.axis = new Vector3f(0.0F, 1.0F, 0.0F);
/*  68 */     this.rotationPoint = new Point3f(0.0F, 0.0F, 1.0F);
/*  69 */     this.nAxis = new Vector3d(0.0D, 1.0D, 0.0D);
/*     */ 
/*     */     
/*  72 */     this.tg = null;
/*     */ 
/*     */ 
/*     */     
/*  76 */     this.viewPosition = new Point3d();
/*  77 */     this.yUpPoint = new Point3d();
/*     */     
/*  79 */     this.eyeVec = new Vector3d();
/*  80 */     this.yUp = new Vector3d();
/*  81 */     this.zAxis = new Vector3d();
/*  82 */     this.yAxis = new Vector3d();
/*  83 */     this.vector = new Vector3d();
/*     */     
/*  85 */     this.aa = new AxisAngle4d();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.nAxis.x = 0.0D;
/* 101 */     this.nAxis.y = 1.0D;
/* 102 */     this.nAxis.z = 0.0D; } public Billboard(TransformGroup paramTransformGroup) { this.wakeupFrame = new WakeupOnElapsedFrames(0, true); this.mode = 0;
/*     */     this.axis = new Vector3f(0.0F, 1.0F, 0.0F);
/*     */     this.rotationPoint = new Point3f(0.0F, 0.0F, 1.0F);
/*     */     this.nAxis = new Vector3d(0.0D, 1.0D, 0.0D);
/*     */     this.tg = null;
/*     */     this.viewPosition = new Point3d();
/*     */     this.yUpPoint = new Point3d();
/*     */     this.eyeVec = new Vector3d();
/*     */     this.yUp = new Vector3d();
/*     */     this.zAxis = new Vector3d();
/*     */     this.yAxis = new Vector3d();
/*     */     this.vector = new Vector3d();
/*     */     this.aa = new AxisAngle4d();
/* 115 */     this.tg = paramTransformGroup;
/* 116 */     this.nAxis.x = 0.0D;
/* 117 */     this.nAxis.y = 1.0D;
/* 118 */     this.nAxis.z = 0.0D; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Billboard(TransformGroup paramTransformGroup, int paramInt, Vector3f paramVector3f) {
/*     */     this.wakeupFrame = new WakeupOnElapsedFrames(0, true);
/*     */     this.mode = 0;
/*     */     this.axis = new Vector3f(0.0F, 1.0F, 0.0F);
/*     */     this.rotationPoint = new Point3f(0.0F, 0.0F, 1.0F);
/*     */     this.nAxis = new Vector3d(0.0D, 1.0D, 0.0D);
/*     */     this.tg = null;
/*     */     this.viewPosition = new Point3d();
/*     */     this.yUpPoint = new Point3d();
/*     */     this.eyeVec = new Vector3d();
/*     */     this.yUp = new Vector3d();
/*     */     this.zAxis = new Vector3d();
/*     */     this.yAxis = new Vector3d();
/*     */     this.vector = new Vector3d();
/*     */     this.aa = new AxisAngle4d();
/* 139 */     this.tg = paramTransformGroup;
/* 140 */     this.mode = paramInt;
/* 141 */     this.axis.set(paramVector3f);
/*     */     
/* 143 */     double d = 1.0D / Math.sqrt((paramVector3f.x * paramVector3f.x + paramVector3f.y * paramVector3f.y + paramVector3f.z * paramVector3f.z));
/* 144 */     this.nAxis.x = paramVector3f.x * d;
/* 145 */     this.nAxis.y = paramVector3f.y * d;
/* 146 */     this.nAxis.z = paramVector3f.z * d; } public Billboard(TransformGroup paramTransformGroup, int paramInt, Point3f paramPoint3f) { this.wakeupFrame = new WakeupOnElapsedFrames(0, true);
/*     */     this.mode = 0;
/*     */     this.axis = new Vector3f(0.0F, 1.0F, 0.0F);
/*     */     this.rotationPoint = new Point3f(0.0F, 0.0F, 1.0F);
/*     */     this.nAxis = new Vector3d(0.0D, 1.0D, 0.0D);
/*     */     this.tg = null;
/*     */     this.viewPosition = new Point3d();
/*     */     this.yUpPoint = new Point3d();
/*     */     this.eyeVec = new Vector3d();
/*     */     this.yUp = new Vector3d();
/*     */     this.zAxis = new Vector3d();
/*     */     this.yAxis = new Vector3d();
/*     */     this.vector = new Vector3d();
/*     */     this.aa = new AxisAngle4d();
/* 160 */     this.tg = paramTransformGroup;
/* 161 */     this.mode = paramInt;
/* 162 */     this.rotationPoint.set(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setAlignmentMode(int paramInt) { this.mode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public int getAlignmentMode() { return this.mode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlignmentAxis(Vector3f paramVector3f) {
/* 194 */     this.axis.set(paramVector3f);
/*     */     
/* 196 */     double d = 1.0D / Math.sqrt((paramVector3f.x * paramVector3f.x + paramVector3f.y * paramVector3f.y + paramVector3f.z * paramVector3f.z));
/* 197 */     this.nAxis.x = paramVector3f.x * d;
/* 198 */     this.nAxis.y = paramVector3f.y * d;
/* 199 */     this.nAxis.z = paramVector3f.z * d;
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
/*     */   public void setAlignmentAxis(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 217 */     this.axis.set(paramFloat1, paramFloat2, paramFloat3);
/* 218 */     this.axis.set(this.axis);
/*     */     
/* 220 */     double d = 1.0D / Math.sqrt((this.axis.x * this.axis.x + this.axis.y * this.axis.y + this.axis.z * this.axis.z));
/* 221 */     this.nAxis.x = this.axis.x * d;
/* 222 */     this.nAxis.y = this.axis.y * d;
/* 223 */     this.nAxis.z = this.axis.z * d;
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
/* 234 */   public void getAlignmentAxis(Vector3f paramVector3f) { paramVector3f.set(this.axis); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public void setRotationPoint(Point3f paramPoint3f) { this.rotationPoint.set(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public void setRotationPoint(float paramFloat1, float paramFloat2, float paramFloat3) { this.rotationPoint.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public void getRotationPoint(Point3f paramPoint3f) { paramPoint3f.set(this.rotationPoint); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   public void setTarget(TransformGroup paramTransformGroup) { this.tg = paramTransformGroup; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public TransformGroup getTarget() { return this.tg; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void initialize() { wakeupOn(this.wakeupFrame); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 297 */     double d = 0.0D;
/*     */ 
/*     */ 
/*     */     
/* 301 */     if (this.tg == null) {
/* 302 */       wakeupOn(this.wakeupFrame);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 307 */     View view = getView();
/* 308 */     if (view == null) {
/* 309 */       wakeupOn(this.wakeupFrame);
/*     */       return;
/*     */     } 
/* 312 */     Canvas3D canvas3D = view.getCanvas3D(0);
/*     */ 
/*     */     
/* 315 */     Transform3D transform3D1 = new Transform3D();
/* 316 */     Transform3D transform3D2 = new Transform3D();
/* 317 */     Transform3D transform3D3 = new Transform3D();
/*     */     
/* 319 */     ((TransformGroupRetained)this.tg.retained).getTransform(transform3D3);
/*     */     
/* 321 */     if (this.mode == 0) {
/* 322 */       canvas3D.getCenterEyeInImagePlate(this.viewPosition);
/* 323 */       canvas3D.getImagePlateToVworld(transform3D1);
/* 324 */       transform3D1.transform(this.viewPosition);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 332 */       ((NodeRetained)this.tg.retained).getLocalToVworld(transform3D1);
/*     */       
/* 334 */       transform3D1.invert();
/*     */ 
/*     */       
/* 337 */       transform3D1.transform(this.viewPosition);
/*     */ 
/*     */       
/* 340 */       this.eyeVec.set(this.viewPosition);
/* 341 */       this.eyeVec.normalize();
/*     */ 
/*     */       
/* 344 */       boolean bool = projectToPlane(this.eyeVec, this.nAxis);
/*     */ 
/*     */       
/* 347 */       if (bool) {
/*     */         
/* 349 */         this.zAxis.x = 0.0D;
/* 350 */         this.zAxis.y = 0.0D;
/* 351 */         this.zAxis.z = 1.0D;
/* 352 */         bool = projectToPlane(this.zAxis, this.nAxis);
/*     */       } 
/*     */ 
/*     */       
/* 356 */       ((TransformGroupRetained)this.tg.retained).getTransform(transform3D1);
/* 357 */       if (bool) {
/*     */         double d1;
/*     */         
/* 360 */         this.vector.cross(this.eyeVec, this.zAxis);
/* 361 */         if (this.vector.dot(this.nAxis) > 0.0D) {
/* 362 */           d1 = 1.0D;
/*     */         } else {
/* 364 */           d1 = -1.0D;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 369 */         double d2 = this.eyeVec.dot(this.zAxis);
/*     */         
/* 371 */         if (d2 > 1.0D) {
/* 372 */           d2 = 1.0D;
/* 373 */         } else if (d2 < -1.0D) {
/* 374 */           d2 = -1.0D;
/*     */         } 
/*     */         
/* 377 */         d = d1 * Math.acos(d2);
/*     */ 
/*     */         
/* 380 */         this.aa.x = this.nAxis.x;
/* 381 */         this.aa.y = this.nAxis.y;
/* 382 */         this.aa.z = this.nAxis.z;
/* 383 */         this.aa.angle = -d;
/* 384 */         transform3D2.set(this.aa);
/* 385 */         if (!transform3D3.epsilonEquals(transform3D2, 1.0E-6D))
/*     */         {
/*     */ 
/*     */           
/* 389 */           this.tg.setTransform(transform3D2);
/*     */         }
/*     */       } else {
/*     */         
/* 393 */         transform3D2.setIdentity();
/* 394 */         if (!transform3D3.epsilonEquals(transform3D2, 1.0E-6D)) {
/* 395 */           this.tg.setTransform(transform3D2);
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 403 */       Transform3D transform3D = new Transform3D();
/*     */ 
/*     */       
/* 406 */       canvas3D.getCenterEyeInImagePlate(this.viewPosition);
/*     */ 
/*     */       
/* 409 */       this.yUpPoint.set(this.viewPosition);
/* 410 */       this.yUpPoint.y += 0.01D;
/*     */ 
/*     */       
/* 413 */       canvas3D.getImagePlateToVworld(transform3D1);
/*     */       
/* 415 */       transform3D1.transform(this.viewPosition);
/* 416 */       transform3D1.transform(this.yUpPoint);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 424 */       ((NodeRetained)this.tg.retained).getLocalToVworld(transform3D1);
/*     */       
/* 426 */       transform3D1.invert();
/*     */ 
/*     */       
/* 429 */       transform3D1.transform(this.viewPosition);
/* 430 */       transform3D1.transform(this.yUpPoint);
/*     */ 
/*     */       
/* 433 */       this.eyeVec.set(this.viewPosition);
/* 434 */       this.eyeVec.normalize();
/*     */ 
/*     */       
/* 437 */       this.yUp.set(this.yUpPoint);
/* 438 */       this.yUp.sub(this.viewPosition);
/* 439 */       this.yUp.normalize();
/*     */ 
/*     */ 
/*     */       
/* 443 */       this.zAxis.x = 0.0D;
/* 444 */       this.zAxis.y = 0.0D;
/* 445 */       this.zAxis.z = 1.0D;
/*     */ 
/*     */       
/* 448 */       this.vector.cross(this.eyeVec, this.zAxis);
/*     */ 
/*     */ 
/*     */       
/* 452 */       double d1 = this.vector.length();
/*     */       
/* 454 */       if (d1 > 1.0E-4D) {
/* 455 */         double d2 = this.eyeVec.dot(this.zAxis);
/*     */         
/* 457 */         if (d2 > 1.0D) {
/* 458 */           d2 = 1.0D;
/* 459 */         } else if (d2 < -1.0D) {
/* 460 */           d2 = -1.0D;
/*     */         } 
/*     */         
/* 463 */         d = Math.acos(d2);
/* 464 */         this.aa.x = this.vector.x;
/* 465 */         this.aa.y = this.vector.y;
/* 466 */         this.aa.z = this.vector.z;
/* 467 */         this.aa.angle = -d;
/* 468 */         transform3D.set(this.aa);
/*     */       } else {
/*     */         
/* 471 */         transform3D.set(1.0D);
/*     */       } 
/*     */ 
/*     */       
/* 475 */       this.yAxis.x = 0.0D;
/* 476 */       this.yAxis.y = 1.0D;
/* 477 */       this.yAxis.z = 0.0D;
/* 478 */       transform3D.transform(this.yAxis);
/*     */ 
/*     */       
/* 481 */       boolean bool = projectToPlane(this.yAxis, this.eyeVec);
/*     */       
/* 483 */       if (bool)
/*     */       {
/* 485 */         bool = projectToPlane(this.yUp, this.eyeVec);
/*     */       }
/*     */       
/* 488 */       ((TransformGroupRetained)this.tg.retained).getTransform(transform3D1);
/* 489 */       if (bool) {
/*     */         
/* 491 */         double d2 = this.yUp.dot(this.yAxis);
/*     */ 
/*     */         
/* 494 */         if (d2 > 1.0D) {
/* 495 */           d2 = 1.0D;
/* 496 */         } else if (d2 < -1.0D) {
/* 497 */           d2 = -1.0D;
/*     */         } 
/*     */         
/* 500 */         d = Math.acos(d2);
/*     */ 
/*     */         
/* 503 */         this.vector.cross(this.yUp, this.yAxis);
/* 504 */         if (this.eyeVec.dot(this.vector) < 0.0D) {
/* 505 */           d *= -1.0D;
/*     */         }
/* 507 */         this.aa.x = this.eyeVec.x;
/* 508 */         this.aa.y = this.eyeVec.y;
/* 509 */         this.aa.z = this.eyeVec.z;
/* 510 */         this.aa.angle = -d;
/*     */         
/* 512 */         transform3D1.set(this.aa);
/*     */ 
/*     */         
/* 515 */         this.vector.x = this.rotationPoint.x;
/* 516 */         this.vector.y = this.rotationPoint.y;
/* 517 */         this.vector.z = this.rotationPoint.z;
/* 518 */         transform3D2.set(this.vector);
/* 519 */         transform3D2.mul(transform3D1);
/* 520 */         transform3D2.mul(transform3D);
/* 521 */         this.vector.scale(-1.0D);
/* 522 */         transform3D1.set(this.vector);
/* 523 */         transform3D2.mul(transform3D1);
/*     */ 
/*     */         
/* 526 */         if (!transform3D3.epsilonEquals(transform3D2, 1.0E-6D))
/*     */         {
/* 528 */           this.tg.setTransform(transform3D2);
/*     */         }
/*     */       } else {
/*     */         
/* 532 */         transform3D2.setIdentity();
/* 533 */         if (!transform3D3.epsilonEquals(transform3D2, 1.0E-6D)) {
/* 534 */           this.tg.setTransform(transform3D2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 540 */     wakeupOn(this.wakeupFrame);
/*     */   }
/*     */   
/*     */   private boolean projectToPlane(Vector3d paramVector3d1, Vector3d paramVector3d2) {
/* 544 */     double d1 = paramVector3d2.dot(paramVector3d1);
/* 545 */     paramVector3d1.x -= paramVector3d2.x * d1;
/* 546 */     paramVector3d1.y -= paramVector3d2.y * d1;
/* 547 */     paramVector3d1.z -= paramVector3d2.z * d1;
/*     */     
/* 549 */     double d2 = paramVector3d1.length();
/*     */     
/* 551 */     if (d2 < 1.0E-6D) {
/* 552 */       return false;
/*     */     }
/* 554 */     paramVector3d1.scale(1.0D / d2);
/* 555 */     return true;
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 573 */     Billboard billboard = new Billboard();
/* 574 */     billboard.duplicateNode(this, paramBoolean);
/* 575 */     return billboard;
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 601 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 603 */     Billboard billboard = (Billboard)paramNode;
/*     */     
/* 605 */     setAlignmentMode(billboard.getAlignmentMode());
/*     */     
/* 607 */     Vector3f vector3f = new Vector3f();
/* 608 */     billboard.getAlignmentAxis(vector3f);
/* 609 */     setAlignmentAxis(vector3f);
/*     */     
/* 611 */     Point3f point3f = new Point3f();
/* 612 */     billboard.getRotationPoint(point3f);
/* 613 */     setRotationPoint(point3f);
/*     */ 
/*     */     
/* 616 */     setTarget(billboard.getTarget());
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
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 647 */     super.updateNodeReferences(paramNodeReferenceTable);
/*     */ 
/*     */     
/* 650 */     TransformGroup transformGroup = getTarget();
/* 651 */     if (transformGroup != null)
/* 652 */       setTarget((TransformGroup)paramNodeReferenceTable.getNewObjectReference(transformGroup)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Billboard.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */