/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.AxisAngle4d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3d;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class OrientedShape3DRetained
/*     */   extends Shape3DRetained
/*     */ {
/*     */   static final int ALIGNMENT_CHANGED = 32;
/*     */   static final int AXIS_CHANGED = 64;
/*     */   static final int ROTATION_CHANGED = 128;
/*     */   static final int CONSTANT_SCALE_CHANGED = 256;
/*     */   static final int SCALE_FACTOR_CHANGED = 512;
/*  27 */   int mode = 0;
/*     */ 
/*     */   
/*  30 */   Vector3f axis = new Vector3f(0.0F, 1.0F, 0.0F);
/*  31 */   Point3f rotationPoint = new Point3f(0.0F, 0.0F, 1.0F);
/*  32 */   private Vector3d nAxis = new Vector3d(0.0D, 1.0D, 0.0D);
/*     */ 
/*     */   
/*  35 */   private Point3d viewPosition = new Point3d();
/*  36 */   private Point3d yUpPoint = new Point3d();
/*     */   
/*  38 */   private Vector3d eyeVec = new Vector3d();
/*  39 */   private Vector3d yUp = new Vector3d();
/*  40 */   private Vector3d zAxis = new Vector3d();
/*  41 */   private Vector3d yAxis = new Vector3d();
/*  42 */   private Vector3d vector = new Vector3d();
/*     */   
/*  44 */   private AxisAngle4d aa = new AxisAngle4d();
/*     */   
/*  46 */   private Transform3D xform = new Transform3D();
/*  47 */   private Transform3D zRotate = new Transform3D();
/*     */   
/*     */   boolean constantScale = false;
/*     */   
/*  51 */   double scaleFactor = 1.0D;
/*     */ 
/*     */ 
/*     */   
/*  55 */   private Transform3D left_xform = new Transform3D();
/*  56 */   private Transform3D right_xform = new Transform3D();
/*     */ 
/*     */ 
/*     */   
/*  60 */   Transform3D scaleXform = new Transform3D();
/*     */ 
/*     */   
/*  63 */   private Vector4d[] im_vec = { new Vector4d(), new Vector4d() };
/*  64 */   private Vector4d lvec = new Vector4d();
/*     */   
/*     */   boolean orientedTransformDirty = true;
/*     */   
/*  68 */   Transform3D[] orientedTransforms = new Transform3D[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final double EPSILON = 1.0E-6D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   void initAlignmentMode(int paramInt) { this.mode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAlignmentMode(int paramInt) {
/*  96 */     if (this.mode != paramInt) {
/*  97 */       initAlignmentMode(paramInt);
/*  98 */       sendChangedMessage(32, new Integer(paramInt));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   int getAlignmentMode() { return this.mode; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   void initAlignmentAxis(Vector3f paramVector3f) { initAlignmentAxis(paramVector3f.x, paramVector3f.y, paramVector3f.z); }
/*     */ 
/*     */ 
/*     */   
/*     */   void initAlignmentAxis(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 117 */     this.axis.set(paramFloat1, paramFloat2, paramFloat3);
/*     */     
/* 119 */     double d = 1.0D / Math.sqrt((this.axis.x * this.axis.x + this.axis.y * this.axis.y + this.axis.z * this.axis.z));
/* 120 */     this.nAxis.x = this.axis.x * d;
/* 121 */     this.nAxis.y = this.axis.y * d;
/* 122 */     this.nAxis.z = this.axis.z * d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   void setAlignmentAxis(Vector3f paramVector3f) { setAlignmentAxis(paramVector3f.x, paramVector3f.y, paramVector3f.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAlignmentAxis(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 142 */     initAlignmentAxis(paramFloat1, paramFloat2, paramFloat3);
/*     */     
/* 144 */     if (this.mode == 0) {
/* 145 */       sendChangedMessage(64, new Vector3f(paramFloat1, paramFloat2, paramFloat3));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   void getAlignmentAxis(Vector3f paramVector3f) { paramVector3f.set(this.axis); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   void initRotationPoint(Point3f paramPoint3f) { this.rotationPoint.set(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   void initRotationPoint(float paramFloat1, float paramFloat2, float paramFloat3) { this.rotationPoint.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   void setRotationPoint(Point3f paramPoint3f) { setRotationPoint(paramPoint3f.x, paramPoint3f.y, paramPoint3f.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setRotationPoint(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 185 */     initRotationPoint(paramFloat1, paramFloat2, paramFloat3);
/*     */     
/* 187 */     if (this.mode == 1) {
/* 188 */       sendChangedMessage(128, new Point3f(paramFloat1, paramFloat2, paramFloat3));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   void getRotationPoint(Point3f paramPoint3f) { paramPoint3f.set(this.rotationPoint); }
/*     */ 
/*     */   
/*     */   void setConstantScaleEnable(boolean paramBoolean) {
/* 202 */     if (this.constantScale != paramBoolean) {
/* 203 */       initConstantScaleEnable(paramBoolean);
/* 204 */       sendChangedMessage(256, new Boolean(paramBoolean));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 209 */   boolean getConstantScaleEnable() { return this.constantScale; }
/*     */ 
/*     */ 
/*     */   
/* 213 */   void initConstantScaleEnable(boolean paramBoolean) { this.constantScale = paramBoolean; }
/*     */ 
/*     */   
/*     */   void setScale(double paramDouble) {
/* 217 */     initScale(paramDouble);
/* 218 */     if (this.constantScale) {
/* 219 */       sendChangedMessage(512, new Double(paramDouble));
/*     */     }
/*     */   }
/*     */   
/* 223 */   void initScale(double paramDouble) { this.scaleFactor = paramDouble; }
/*     */ 
/*     */ 
/*     */   
/* 227 */   double getScale() { return this.scaleFactor; }
/*     */ 
/*     */   
/*     */   void sendChangedMessage(int paramInt, Object paramObject) {
/* 231 */     J3dMessage j3dMessage = new J3dMessage();
/* 232 */     j3dMessage.type = 46;
/* 233 */     j3dMessage.threads = 4224;
/* 234 */     j3dMessage.universe = this.universe;
/* 235 */     j3dMessage.args[0] = getGeomAtomsArray(this.mirrorShape3D);
/* 236 */     j3dMessage.args[1] = new Integer(paramInt);
/* 237 */     j3dMessage.args[2] = paramObject;
/* 238 */     OrientedShape3DRetained[] arrayOfOrientedShape3DRetained = new OrientedShape3DRetained[this.mirrorShape3D.size()];
/*     */     
/* 240 */     this.mirrorShape3D.toArray(arrayOfOrientedShape3DRetained);
/* 241 */     j3dMessage.args[3] = arrayOfOrientedShape3DRetained;
/* 242 */     j3dMessage.args[4] = this;
/* 243 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */   
/*     */   void updateImmediateMirrorObject(Object[] paramArrayOfObject) {
/* 247 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 248 */     if ((i & 0x3E0) != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 253 */       OrientedShape3DRetained[] arrayOfOrientedShape3DRetained = (OrientedShape3DRetained[])paramArrayOfObject[3];
/* 254 */       Object object = paramArrayOfObject[2];
/* 255 */       if ((i & 0x20) != 0) {
/* 256 */         int j = ((Integer)object).intValue();
/* 257 */         for (byte b = 0; b < arrayOfOrientedShape3DRetained.length; b++) {
/* 258 */           arrayOfOrientedShape3DRetained[b].initAlignmentMode(j);
/*     */         }
/*     */       }
/* 261 */       else if ((i & 0x40) != 0) {
/* 262 */         Vector3f vector3f = (Vector3f)object;
/* 263 */         for (byte b = 0; b < arrayOfOrientedShape3DRetained.length; b++) {
/* 264 */           arrayOfOrientedShape3DRetained[b].initAlignmentAxis(vector3f);
/*     */         }
/*     */       }
/* 267 */       else if ((i & 0x80) != 0) {
/* 268 */         Point3f point3f = (Point3f)object;
/* 269 */         for (byte b = 0; b < arrayOfOrientedShape3DRetained.length; b++) {
/* 270 */           arrayOfOrientedShape3DRetained[b].initRotationPoint(point3f);
/*     */         }
/*     */       }
/* 273 */       else if ((i & 0x100) != 0) {
/* 274 */         boolean bool = ((Boolean)object).booleanValue();
/* 275 */         for (byte b = 0; b < arrayOfOrientedShape3DRetained.length; b++) {
/* 276 */           arrayOfOrientedShape3DRetained[b].initConstantScaleEnable(bool);
/*     */         }
/*     */       }
/* 279 */       else if ((i & 0x200) != 0) {
/* 280 */         double d = ((Double)object).doubleValue();
/* 281 */         for (byte b = 0; b < arrayOfOrientedShape3DRetained.length; b++) {
/* 282 */           arrayOfOrientedShape3DRetained[b].initScale(d);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 287 */       super.updateImmediateMirrorObject(paramArrayOfObject);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   Transform3D getOrientedTransform(int paramInt) {
/* 293 */     synchronized (this.orientedTransforms) {
/* 294 */       if (paramInt >= this.orientedTransforms.length) {
/* 295 */         Transform3D transform3D = new Transform3D();
/* 296 */         Transform3D[] arrayOfTransform3D = new Transform3D[paramInt + 1];
/* 297 */         for (byte b = 0; b < this.orientedTransforms.length; b++) {
/* 298 */           arrayOfTransform3D[b] = this.orientedTransforms[b];
/*     */         }
/* 300 */         arrayOfTransform3D[paramInt] = transform3D;
/* 301 */         this.orientedTransforms = arrayOfTransform3D;
/*     */       
/*     */       }
/* 304 */       else if (this.orientedTransforms[paramInt] == null) {
/* 305 */         this.orientedTransforms[paramInt] = new Transform3D();
/*     */       } 
/*     */     } 
/*     */     
/* 309 */     return this.orientedTransforms[paramInt];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateOrientedTransform(Canvas3D paramCanvas3D, int paramInt) {
/* 316 */     double d = 0.0D;
/*     */ 
/*     */ 
/*     */     
/* 320 */     Transform3D transform3D = getOrientedTransform(paramInt);
/*     */     
/* 322 */     if (this.mode == 0) {
/* 323 */       paramCanvas3D.getCenterEyeInImagePlate(this.viewPosition);
/* 324 */       paramCanvas3D.getImagePlateToVworld(this.xform);
/* 325 */       this.xform.transform(this.viewPosition);
/*     */ 
/*     */       
/* 328 */       this.xform.set(getCurrentLocalToVworld());
/* 329 */       this.xform.invert();
/*     */ 
/*     */       
/* 332 */       this.xform.transform(this.viewPosition);
/*     */ 
/*     */ 
/*     */       
/* 336 */       this.eyeVec.set(this.viewPosition);
/* 337 */       this.eyeVec.normalize();
/*     */ 
/*     */       
/* 340 */       boolean bool = projectToPlane(this.eyeVec, this.nAxis);
/*     */       
/* 342 */       if (bool) {
/*     */         
/* 344 */         this.zAxis.x = 0.0D;
/* 345 */         this.zAxis.y = 0.0D;
/* 346 */         this.zAxis.z = 1.0D;
/* 347 */         bool = projectToPlane(this.zAxis, this.nAxis);
/*     */       } 
/* 349 */       if (bool) {
/*     */         double d1;
/*     */ 
/*     */         
/* 353 */         this.vector.cross(this.eyeVec, this.zAxis);
/* 354 */         if (this.vector.dot(this.nAxis) > 0.0D) {
/* 355 */           d1 = 1.0D;
/*     */         } else {
/* 357 */           d1 = -1.0D;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 363 */         double d2 = this.eyeVec.dot(this.zAxis);
/* 364 */         if (d2 > 1.0D) {
/* 365 */           d2 = 1.0D;
/* 366 */         } else if (d2 < -1.0D) {
/* 367 */           d2 = -1.0D;
/*     */         } 
/*     */         
/* 370 */         d = d1 * Math.acos(d2);
/*     */ 
/*     */         
/* 373 */         this.aa.x = this.nAxis.x;
/* 374 */         this.aa.y = this.nAxis.y;
/* 375 */         this.aa.z = this.nAxis.z;
/* 376 */         this.aa.angle = -d;
/* 377 */         transform3D.set(this.aa);
/*     */       } else {
/*     */         
/* 380 */         transform3D.setIdentity();
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 388 */       paramCanvas3D.getCenterEyeInImagePlate(this.viewPosition);
/*     */ 
/*     */       
/* 391 */       this.yUpPoint.set(this.viewPosition);
/* 392 */       this.yUpPoint.y += 0.01D;
/*     */ 
/*     */       
/* 395 */       paramCanvas3D.getImagePlateToVworld(this.xform);
/* 396 */       this.xform.transform(this.viewPosition);
/* 397 */       this.xform.transform(this.yUpPoint);
/*     */ 
/*     */       
/* 400 */       this.xform.set(getCurrentLocalToVworld());
/* 401 */       this.xform.invert();
/*     */ 
/*     */       
/* 404 */       this.xform.transform(this.viewPosition);
/* 405 */       this.xform.transform(this.yUpPoint);
/*     */ 
/*     */       
/* 408 */       this.eyeVec.set(this.viewPosition);
/* 409 */       this.eyeVec.normalize();
/*     */ 
/*     */       
/* 412 */       this.yUp.set(this.yUpPoint);
/* 413 */       this.yUp.sub(this.viewPosition);
/* 414 */       this.yUp.normalize();
/*     */ 
/*     */ 
/*     */       
/* 418 */       this.zAxis.x = 0.0D;
/* 419 */       this.zAxis.y = 0.0D;
/* 420 */       this.zAxis.z = 1.0D;
/*     */ 
/*     */       
/* 423 */       this.vector.cross(this.eyeVec, this.zAxis);
/*     */ 
/*     */ 
/*     */       
/* 427 */       double d1 = this.vector.length();
/* 428 */       if (d1 > 1.0E-4D) {
/* 429 */         double d2 = this.eyeVec.dot(this.zAxis);
/* 430 */         if (d2 > 1.0D) {
/* 431 */           d2 = 1.0D;
/* 432 */         } else if (d2 < -1.0D) {
/* 433 */           d2 = -1.0D;
/*     */         } 
/* 435 */         d = Math.acos(d2);
/* 436 */         this.aa.x = this.vector.x;
/* 437 */         this.aa.y = this.vector.y;
/* 438 */         this.aa.z = this.vector.z;
/* 439 */         this.aa.angle = -d;
/* 440 */         this.zRotate.set(this.aa);
/*     */       } else {
/*     */         
/* 443 */         this.zRotate.set(1.0D);
/*     */       } 
/*     */ 
/*     */       
/* 447 */       this.yAxis.x = 0.0D;
/* 448 */       this.yAxis.y = 1.0D;
/* 449 */       this.yAxis.z = 0.0D;
/* 450 */       this.zRotate.transform(this.yAxis);
/*     */ 
/*     */       
/* 453 */       boolean bool = projectToPlane(this.yAxis, this.eyeVec);
/*     */ 
/*     */       
/* 456 */       if (bool)
/*     */       {
/* 458 */         bool = projectToPlane(this.yUp, this.eyeVec);
/*     */       }
/*     */       
/* 461 */       if (bool) {
/*     */         
/* 463 */         double d2 = this.yUp.dot(this.yAxis);
/*     */ 
/*     */         
/* 466 */         if (d2 > 1.0D) {
/* 467 */           d2 = 1.0D;
/* 468 */         } else if (d2 < -1.0D) {
/* 469 */           d2 = -1.0D;
/*     */         } 
/*     */         
/* 472 */         d = Math.acos(d2);
/*     */ 
/*     */         
/* 475 */         this.vector.cross(this.yUp, this.yAxis);
/* 476 */         if (this.eyeVec.dot(this.vector) < 0.0D) {
/* 477 */           d *= -1.0D;
/*     */         }
/* 479 */         this.aa.x = this.eyeVec.x;
/* 480 */         this.aa.y = this.eyeVec.y;
/* 481 */         this.aa.z = this.eyeVec.z;
/* 482 */         this.aa.angle = -d;
/* 483 */         this.xform.set(this.aa);
/*     */ 
/*     */         
/* 486 */         this.vector.x = this.rotationPoint.x;
/* 487 */         this.vector.y = this.rotationPoint.y;
/* 488 */         this.vector.z = this.rotationPoint.z;
/* 489 */         transform3D.set(this.vector);
/* 490 */         transform3D.mul(this.xform);
/* 491 */         transform3D.mul(this.zRotate);
/* 492 */         this.vector.scale(-1.0D);
/* 493 */         this.xform.set(this.vector);
/* 494 */         transform3D.mul(this.xform);
/*     */       } else {
/*     */         
/* 497 */         transform3D.setIdentity();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 502 */     if (this.constantScale) {
/*     */       
/* 504 */       paramCanvas3D.getInverseVworldProjection(this.left_xform, this.right_xform);
/*     */ 
/*     */ 
/*     */       
/* 508 */       this.im_vec[0].set(0.0D, 0.0D, 0.0D, 1.0D);
/* 509 */       this.im_vec[1].set(1.0D, 0.0D, 0.0D, 1.0D);
/* 510 */       this.left_xform.transform(this.im_vec[0]);
/* 511 */       this.left_xform.transform(this.im_vec[1]);
/*     */       
/* 513 */       this.left_xform.set(getCurrentLocalToVworld());
/* 514 */       this.left_xform.invert();
/* 515 */       this.left_xform.transform(this.im_vec[0]);
/* 516 */       this.left_xform.transform(this.im_vec[1]);
/* 517 */       this.lvec.set(this.im_vec[1]);
/* 518 */       this.lvec.sub(this.im_vec[0]);
/*     */ 
/*     */       
/* 521 */       this.lvec.normalize();
/* 522 */       this.im_vec[0].set(0.0D, 0.0D, 0.0D, 1.0D);
/* 523 */       this.im_vec[1].set(this.lvec);
/* 524 */       (this.im_vec[1]).w = 1.0D;
/*     */ 
/*     */       
/* 527 */       this.left_xform.set(getCurrentLocalToVworld());
/* 528 */       this.left_xform.transform(this.im_vec[0]);
/* 529 */       this.left_xform.transform(this.im_vec[1]);
/*     */       
/* 531 */       paramCanvas3D.getVworldProjection(this.left_xform, this.right_xform);
/* 532 */       this.left_xform.transform(this.im_vec[0]);
/* 533 */       this.left_xform.transform(this.im_vec[1]);
/*     */ 
/*     */       
/* 536 */       (this.im_vec[0]).x /= (this.im_vec[0]).w;
/* 537 */       (this.im_vec[0]).y /= (this.im_vec[0]).w;
/* 538 */       (this.im_vec[0]).z /= (this.im_vec[0]).w;
/*     */       
/* 540 */       (this.im_vec[1]).x /= (this.im_vec[1]).w;
/* 541 */       (this.im_vec[1]).y /= (this.im_vec[1]).w;
/* 542 */       (this.im_vec[1]).z /= (this.im_vec[1]).w;
/*     */       
/* 544 */       this.lvec.set(this.im_vec[1]);
/* 545 */       this.lvec.sub(this.im_vec[0]);
/*     */ 
/*     */ 
/*     */       
/* 549 */       double d1 = 1.0D / this.lvec.length();
/*     */ 
/*     */       
/* 552 */       d1 *= this.scaleFactor * paramCanvas3D.getPhysicalWidth() / 2.0D;
/*     */ 
/*     */       
/* 555 */       this.scaleXform.setScale(d1);
/* 556 */       transform3D.mul(this.scaleXform);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean projectToPlane(Vector3d paramVector3d1, Vector3d paramVector3d2) {
/* 563 */     double d1 = paramVector3d2.dot(paramVector3d1);
/* 564 */     paramVector3d1.x -= paramVector3d2.x * d1;
/* 565 */     paramVector3d1.y -= paramVector3d2.y * d1;
/* 566 */     paramVector3d1.z -= paramVector3d2.z * d1;
/*     */     
/* 568 */     double d2 = paramVector3d1.length();
/* 569 */     if (d2 < 1.0E-6D) {
/* 570 */       return false;
/*     */     }
/* 572 */     paramVector3d1.scale(1.0D / d2);
/* 573 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   void compile(CompileState paramCompileState) {
/* 578 */     super.compile(paramCompileState);
/*     */     
/* 580 */     this.mergeFlag = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 586 */     paramCompileState.keepTG = true;
/*     */   }
/*     */ 
/*     */   
/* 590 */   void searchGeometryAtoms(UnorderList paramUnorderList) { paramUnorderList.add(getGeomAtom(getMirrorShape(this.key))); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\OrientedShape3DRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */