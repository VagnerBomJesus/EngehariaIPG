/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import com.sun.j3d.utils.behaviors.interpolators.KBKeyFrame;
/*     */ import com.sun.j3d.utils.behaviors.interpolators.KBRotPosScaleSplinePathInterpolator;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsMotion
/*     */   extends TextfileParser
/*     */ {
/*     */   String motionName;
/*     */   LwsFrame[] frames;
/*     */   int numFrames;
/*     */   int numChannels;
/*     */   boolean loop;
/*     */   float totalTime;
/*     */   int firstFrame;
/*     */   int totalFrames;
/*     */   Behavior behaviors;
/*     */   
/*  89 */   LwsMotion(StreamTokenizer paramStreamTokenizer, int paramInt, float paramFloat) { this(paramStreamTokenizer, 0, paramInt, paramFloat, 16); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LwsMotion(StreamTokenizer paramStreamTokenizer, int paramInt1, int paramInt2, float paramFloat, int paramInt3) throws ParsingErrorException, IncorrectFormatException {
/* 101 */     this.debugPrinter.setValidOutput(paramInt3);
/* 102 */     this.numFrames = 0;
/* 103 */     this.totalTime = paramFloat;
/* 104 */     this.firstFrame = paramInt1;
/* 105 */     this.totalFrames = paramInt2;
/* 106 */     debugOutputLn(8, "about to get motion name");
/* 107 */     this.motionName = getName(paramStreamTokenizer);
/* 108 */     debugOutputLn(8, "about to get motion");
/* 109 */     getMotion(paramStreamTokenizer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getMotion(StreamTokenizer paramStreamTokenizer) throws ParsingErrorException, IncorrectFormatException {
/* 120 */     debugOutputLn(1, "getMotion()");
/* 121 */     this.numChannels = (int)getNumber(paramStreamTokenizer);
/* 122 */     if (this.numChannels != 9) {
/* 123 */       throw new IncorrectFormatException(J3dUtilsI18N.getString("LwsMotion0"));
/*     */     }
/*     */     
/* 126 */     debugOutputLn(8, "got channels");
/*     */     
/* 128 */     this.numFrames = (int)getNumber(paramStreamTokenizer);
/* 129 */     this.frames = new LwsFrame[this.numFrames];
/* 130 */     debugOutputLn(2, "got frames" + this.numFrames);
/*     */     int i;
/* 132 */     for (i = 0; i < this.numFrames; i++) {
/* 133 */       this.frames[i] = new LwsFrame(paramStreamTokenizer);
/*     */     }
/*     */     
/* 136 */     debugOutput(8, "got all frames");
/*     */     
/* 138 */     getAndCheckString(paramStreamTokenizer, "EndBehavior");
/* 139 */     i = (int)getNumber(paramStreamTokenizer);
/* 140 */     if (i == 1) {
/* 141 */       this.loop = false;
/*     */     } else {
/* 143 */       this.loop = true;
/*     */     } 
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
/*     */   void fixFrames() {
/* 170 */     boolean bool = false;
/* 171 */     Vector vector = new Vector();
/* 172 */     double d = 1.5707963705062866D;
/* 173 */     LwsFrame lwsFrame = null;
/*     */     
/* 175 */     for (byte b = 1; b < this.numFrames; b++) {
/*     */       
/* 177 */       LwsFrame lwsFrame1 = this.frames[b - true];
/* 178 */       LwsFrame lwsFrame2 = this.frames[b];
/*     */ 
/*     */       
/* 181 */       lwsFrame = lwsFrame2;
/* 182 */       vector.add(lwsFrame1);
/*     */       
/* 184 */       double d1 = 0.0D;
/* 185 */       double d2 = lwsFrame2.getHeading();
/* 186 */       double d3 = lwsFrame1.getHeading();
/* 187 */       double d4 = Math.abs(d2 - d3);
/* 188 */       if (d4 > d1) {
/* 189 */         d1 = d4;
/*     */       }
/* 191 */       d2 = lwsFrame2.getPitch();
/* 192 */       d3 = lwsFrame1.getPitch();
/* 193 */       d4 = Math.abs(d2 - d3);
/* 194 */       if (d4 > d1) {
/* 195 */         d1 = d4;
/*     */       }
/* 197 */       d2 = lwsFrame2.getBank();
/* 198 */       d3 = lwsFrame1.getBank();
/* 199 */       d4 = Math.abs(d2 - d3);
/* 200 */       if (d4 > d1) {
/* 201 */         d1 = d4;
/*     */       }
/* 203 */       if (d1 > d) {
/*     */         double d10, d9; LwsFrame lwsFrame4, lwsFrame3;
/* 205 */         bool = true;
/* 206 */         int i = (int)(d1 / d);
/* 207 */         double d5 = 1.0D / (i + 1);
/* 208 */         double d6 = d5;
/*     */         
/* 210 */         double d7 = this.frames[this.numFrames - 1].getFrameNum();
/* 211 */         double d8 = (lwsFrame2.getFrameNum() - lwsFrame1.getFrameNum()) / d7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 217 */         if (b - true < true) {
/* 218 */           lwsFrame3 = this.frames[b - true];
/* 219 */           d9 = 0.0D;
/*     */         } else {
/* 221 */           lwsFrame3 = this.frames[b - 2];
/* 222 */           d9 = d8 / (lwsFrame2.getFrameNum() - lwsFrame3.getFrameNum()) / d7;
/*     */         } 
/*     */ 
/*     */         
/* 226 */         if (b + true < this.numFrames) {
/* 227 */           lwsFrame4 = this.frames[b + true];
/* 228 */           d10 = d8 / (lwsFrame4.getFrameNum() - lwsFrame1.getFrameNum()) / d7;
/*     */         } else {
/*     */           
/* 231 */           lwsFrame4 = this.frames[b];
/* 232 */           d10 = 1.0D;
/*     */         } 
/*     */         
/* 235 */         for (byte b1 = 0; b1 < i; b1++) {
/*     */           LwsFrame lwsFrame5;
/*     */ 
/*     */ 
/*     */           
/* 240 */           if (lwsFrame2.linearValue == 1) {
/* 241 */             lwsFrame5 = new LwsFrame(lwsFrame1, lwsFrame2, d6);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 246 */             lwsFrame5 = new LwsFrame(lwsFrame3, lwsFrame1, lwsFrame2, lwsFrame4, d6, d9, d10);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 251 */           d6 += d5;
/* 252 */           vector.add(lwsFrame5);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 258 */     if (lwsFrame != null)
/* 259 */       vector.add(lwsFrame); 
/* 260 */     if (bool) {
/*     */ 
/*     */       
/* 263 */       LwsFrame[] arrayOfLwsFrame = new LwsFrame[vector.size()];
/* 264 */       Enumeration enumeration = vector.elements();
/* 265 */       byte b1 = 0;
/* 266 */       while (enumeration.hasMoreElements()) {
/* 267 */         arrayOfLwsFrame[b1++] = (LwsFrame)enumeration.nextElement();
/*     */       }
/* 269 */       this.frames = arrayOfLwsFrame;
/* 270 */       this.numFrames = this.frames.length;
/* 271 */       for (byte b2 = 0; b2 < this.numFrames; b2++) {
/* 272 */         debugOutputLn(2, "frame " + b2 + " = " + this.frames[b2]);
/* 273 */         this.frames[b2].printVals();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int intMod(int paramInt1, int paramInt2) {
/* 282 */     int i = paramInt1;
/* 283 */     int j = paramInt2;
/* 284 */     if (i < 0)
/* 285 */       i = -i; 
/* 286 */     if (j < 0)
/* 287 */       j = -j; 
/* 288 */     while (i > j) {
/* 289 */       i -= j;
/*     */     }
/* 291 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   class FrameHolder
/*     */   {
/*     */     double frameNumber;
/*     */     
/*     */     LwsFrame frame;
/*     */ 
/*     */     
/*     */     FrameHolder(LwsFrame param1LwsFrame, double param1Double) {
/* 303 */       this.frame = param1LwsFrame;
/* 304 */       this.frameNumber = param1Double;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void playWithFrameTimes(Vector paramVector) {
/* 315 */     debugOutputLn(1, "playWithFrameTimes: firstFrame = " + this.firstFrame);
/*     */     
/* 317 */     if (this.firstFrame == 1) {
/*     */       return;
/*     */     }
/* 320 */     if (this.frames[this.numFrames - 1].getFrameNum() < this.totalFrames) {
/*     */ 
/*     */ 
/*     */       
/* 324 */       int i = (int)(this.frames[this.numFrames - 1].getFrameNum() + 0.4999999D);
/*     */       
/* 326 */       int j = intMod(this.firstFrame, i);
/* 327 */       int k = intMod(this.totalFrames, i);
/* 328 */       byte b1 = 0;
/* 329 */       while (b1 < this.numFrames && 
/* 330 */         this.frames[b1].getFrameNum() < j)
/*     */       {
/* 332 */         b1++;
/*     */       }
/* 334 */       byte b2 = b1;
/* 335 */       if (this.frames[b2].getFrameNum() > this.firstFrame && b2 > 0)
/*     */       {
/* 337 */         b2--; } 
/* 338 */       b1 = b2;
/* 339 */       if (j < k) {
/*     */         
/* 341 */         while (b1 < this.numFrames && this.frames[b1].getFrameNum() <= k) {
/* 342 */           FrameHolder frameHolder = new FrameHolder(this.frames[b1], this.frames[b1].getFrameNum() - j);
/*     */ 
/*     */ 
/*     */           
/* 346 */           paramVector.addElement(frameHolder);
/* 347 */           b1++;
/*     */         } 
/*     */       } else {
/*     */         
/* 351 */         double d = -1.0D;
/* 352 */         while (b1 < this.numFrames) {
/* 353 */           d = this.frames[b1].getFrameNum() - j;
/*     */           
/* 355 */           FrameHolder frameHolder = new FrameHolder(this.frames[b1], d);
/*     */ 
/*     */           
/* 358 */           paramVector.addElement(frameHolder);
/* 359 */           b1++;
/*     */         } 
/* 361 */         b1 = 0;
/*     */         
/* 363 */         while (b1 <= b2 && this.frames[b1].getFrameNum() <= k) {
/* 364 */           if (b1 == 0) {
/* 365 */             LwsFrame lwsFrame = new LwsFrame(this.frames[b1], this.frames[b1 + 1], 1.0D / (this.frames[b1 + 1].getFrameNum() - this.frames[b1].getFrameNum()));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 370 */             FrameHolder frameHolder = new FrameHolder(lwsFrame, lwsFrame.getFrameNum() + d);
/*     */ 
/*     */ 
/*     */             
/* 374 */             paramVector.addElement(frameHolder);
/*     */           } else {
/*     */             
/* 377 */             FrameHolder frameHolder = new FrameHolder(this.frames[b1], this.frames[b1].getFrameNum() + d);
/*     */ 
/*     */ 
/*     */             
/* 381 */             paramVector.addElement(frameHolder);
/*     */           } 
/* 383 */           b1++;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 388 */       byte b1 = 0;
/* 389 */       while (b1 < this.numFrames && 
/* 390 */         this.frames[b1].getFrameNum() < this.firstFrame)
/*     */       {
/* 392 */         b1++;
/*     */       }
/* 394 */       byte b2 = b1;
/* 395 */       if (this.frames[b2].getFrameNum() > this.firstFrame && b2 > 0) {
/*     */ 
/*     */         
/* 398 */         double d = this.firstFrame / (this.frames[b2].getFrameNum() - this.frames[b2 - 1].getFrameNum());
/*     */ 
/*     */         
/* 401 */         LwsFrame lwsFrame = new LwsFrame(this.frames[b2 - 1], this.frames[b2], d);
/*     */ 
/*     */         
/* 404 */         FrameHolder frameHolder = new FrameHolder(lwsFrame, lwsFrame.getFrameNum() - this.firstFrame);
/*     */ 
/*     */         
/* 407 */         paramVector.addElement(frameHolder);
/*     */       } 
/* 409 */       b1 = b2;
/*     */       
/* 411 */       while (b1 < this.numFrames && this.frames[b1].getFrameNum() <= this.totalFrames) {
/* 412 */         FrameHolder frameHolder = new FrameHolder(this.frames[b1], this.frames[b1].getFrameNum() - this.firstFrame);
/*     */ 
/*     */ 
/*     */         
/* 416 */         paramVector.addElement(frameHolder);
/* 417 */         b1++;
/*     */       } 
/* 419 */       if (this.frames[b1 - 1].getFrameNum() < this.totalFrames) {
/*     */         
/* 421 */         double d = (this.totalFrames - this.frames[b1 - 1].getFrameNum()) / (this.frames[b1].getFrameNum() - this.frames[b1 - 1].getFrameNum());
/*     */ 
/*     */ 
/*     */         
/* 425 */         LwsFrame lwsFrame = new LwsFrame(this.frames[b1 - 1], this.frames[b1], d);
/*     */ 
/*     */         
/* 428 */         FrameHolder frameHolder = new FrameHolder(lwsFrame, (this.totalFrames - this.firstFrame));
/*     */         
/* 430 */         paramVector.addElement(frameHolder);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createJava3dBehaviorsForFramesSubset(TransformGroup paramTransformGroup) {
/* 443 */     debugOutputLn(1, "createJava3dBehaviorsForFramesSubset");
/* 444 */     Vector vector = new Vector();
/* 445 */     playWithFrameTimes(vector);
/* 446 */     long l = 0L;
/*     */ 
/*     */ 
/*     */     
/* 450 */     if (this.loop) {
/* 451 */       byte b2 = -1;
/*     */     } else {
/* 453 */       boolean bool = true;
/* 454 */     }  byte b = -1;
/*     */     
/* 456 */     int i = vector.size();
/*     */     
/* 458 */     debugOutputLn(2, "totalTime = " + this.totalTime);
/* 459 */     debugOutputLn(2, "loopCount = " + b);
/*     */     
/* 461 */     FrameHolder frameHolder = (FrameHolder)vector.elementAt(vector.size() - 1);
/*     */     
/* 463 */     LwsFrame lwsFrame = frameHolder.frame;
/* 464 */     float f = 1000.0F * this.totalTime * (float)(frameHolder.frameNumber / (this.totalFrames - this.firstFrame));
/*     */ 
/*     */     
/* 467 */     debugOutputLn(2, " anim time: " + f);
/* 468 */     debugOutputLn(2, " totalFrames = " + this.totalFrames);
/*     */     
/* 470 */     if (!this.loop)
/* 471 */       l = (long)(1000.0D * this.totalTime - f); 
/* 472 */     Alpha alpha = new Alpha(b, 1, 0L, 0L, (long)f, 0L, l, 0L, 0L, 0L);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 477 */     float[] arrayOfFloat = new float[i];
/* 478 */     Point3f[] arrayOfPoint3f1 = new Point3f[i];
/* 479 */     Quat4f[] arrayOfQuat4f = new Quat4f[i];
/* 480 */     Point3f[] arrayOfPoint3f2 = new Point3f[i];
/* 481 */     Transform3D transform3D = new Transform3D();
/* 482 */     Matrix4d matrix4d = new Matrix4d();
/* 483 */     KBKeyFrame[] arrayOfKBKeyFrame = new KBKeyFrame[i];
/*     */     
/* 485 */     for (byte b1 = 0; b1 < i; b1++) {
/*     */       
/* 487 */       FrameHolder frameHolder1 = (FrameHolder)vector.elementAt(b1);
/* 488 */       LwsFrame lwsFrame1 = frameHolder1.frame;
/*     */ 
/*     */       
/* 491 */       arrayOfPoint3f1[b1] = lwsFrame1.getPosition();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 498 */       arrayOfPoint3f2[b1] = lwsFrame1.getScale();
/*     */ 
/*     */       
/* 501 */       lwsFrame1.setRotationMatrix(matrix4d);
/* 502 */       debugOutputLn(2, "LwsMotion::createj3dbeh, mat = " + matrix4d);
/* 503 */       arrayOfQuat4f[b1] = new Quat4f();
/* 504 */       arrayOfQuat4f[b1].set(matrix4d);
/* 505 */       debugOutputLn(2, " and quat = " + arrayOfQuat4f[b1]);
/*     */ 
/*     */       
/* 508 */       if (b1 == 0) {
/* 509 */         arrayOfFloat[b1] = 0.0F;
/*     */       } else {
/* 511 */         arrayOfFloat[b1] = (float)frameHolder1.frameNumber / (float)frameHolder.frameNumber;
/*     */       } 
/*     */ 
/*     */       
/* 515 */       arrayOfKBKeyFrame[b1] = new KBKeyFrame(arrayOfFloat[b1], lwsFrame1.linearValue, arrayOfPoint3f1[b1], (float)lwsFrame1.heading, (float)lwsFrame1.pitch, (float)lwsFrame1.bank, arrayOfPoint3f2[b1], (float)lwsFrame1.tension, (float)lwsFrame1.continuity, (float)lwsFrame1.bias);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 525 */       debugOutputLn(2, "pos, knots, quat = " + arrayOfPoint3f1[b1] + arrayOfFloat[b1] + arrayOfQuat4f[b1]);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 530 */     KBRotPosScaleSplinePathInterpolator kBRotPosScaleSplinePathInterpolator = new KBRotPosScaleSplinePathInterpolator(alpha, paramTransformGroup, transform3D, arrayOfKBKeyFrame);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 535 */     if (kBRotPosScaleSplinePathInterpolator != null) {
/* 536 */       this.behaviors = kBRotPosScaleSplinePathInterpolator;
/* 537 */       BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 1000000.0D);
/*     */       
/* 539 */       kBRotPosScaleSplinePathInterpolator.setSchedulingBounds(boundingSphere);
/* 540 */       paramTransformGroup.setCapability(18);
/* 541 */       paramTransformGroup.addChild(this.behaviors);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createJava3dBehaviors(TransformGroup paramTransformGroup) {
/* 552 */     if (this.numFrames <= 1) {
/* 553 */       this.behaviors = null;
/*     */     } else {
/* 555 */       if (this.firstFrame > 1) {
/* 556 */         createJava3dBehaviorsForFramesSubset(paramTransformGroup);
/*     */         
/*     */         return;
/*     */       } 
/* 560 */       long l = 0L;
/*     */ 
/*     */ 
/*     */       
/* 564 */       if (this.loop) {
/* 565 */         byte b2 = -1;
/*     */       } else {
/* 567 */         boolean bool = true;
/* 568 */       }  byte b = -1;
/*     */       
/* 570 */       debugOutputLn(2, "totalTime = " + this.totalTime);
/* 571 */       debugOutputLn(2, "loopCount = " + b);
/*     */       
/* 573 */       float f = 1000.0F * this.totalTime * (float)(this.frames[this.numFrames - 1].getFrameNum() / this.totalFrames);
/*     */ 
/*     */       
/* 576 */       debugOutputLn(2, " anim time: " + f);
/* 577 */       debugOutputLn(2, " totalFrames = " + this.totalFrames);
/* 578 */       debugOutputLn(2, " lastFrame = " + this.frames[this.numFrames - 1].getFrameNum());
/*     */ 
/*     */       
/* 581 */       if (!this.loop)
/* 582 */         l = (long)(1000.0D * this.totalTime - f); 
/* 583 */       Alpha alpha = new Alpha(b, 1, 0L, 0L, (long)f, 0L, l, 0L, 0L, 0L);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 588 */       float[] arrayOfFloat = new float[this.numFrames];
/* 589 */       Point3f[] arrayOfPoint3f1 = new Point3f[this.numFrames];
/* 590 */       Quat4f[] arrayOfQuat4f = new Quat4f[this.numFrames];
/* 591 */       Point3f[] arrayOfPoint3f2 = new Point3f[this.numFrames];
/* 592 */       Transform3D transform3D = new Transform3D();
/* 593 */       Matrix4d matrix4d = new Matrix4d();
/* 594 */       KBKeyFrame[] arrayOfKBKeyFrame = new KBKeyFrame[this.numFrames];
/*     */       
/* 596 */       for (byte b1 = 0; b1 < this.numFrames; b1++) {
/*     */ 
/*     */         
/* 599 */         arrayOfPoint3f1[b1] = this.frames[b1].getPosition();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 606 */         arrayOfPoint3f2[b1] = this.frames[b1].getScale();
/*     */ 
/*     */         
/* 609 */         this.frames[b1].setRotationMatrix(matrix4d);
/* 610 */         debugOutputLn(2, "LwsMotion::createj3dbeh, mat = " + matrix4d);
/* 611 */         arrayOfQuat4f[b1] = new Quat4f();
/* 612 */         arrayOfQuat4f[b1].set(matrix4d);
/* 613 */         debugOutputLn(2, " and quat = " + arrayOfQuat4f[b1]);
/*     */ 
/*     */         
/* 616 */         if (!b1) {
/* 617 */           arrayOfFloat[b1] = 0.0F;
/*     */         } else {
/* 619 */           arrayOfFloat[b1] = (float)this.frames[b1].getFrameNum() / (float)this.frames[this.numFrames - 1].getFrameNum();
/*     */         } 
/*     */ 
/*     */         
/* 623 */         arrayOfKBKeyFrame[b1] = new KBKeyFrame(arrayOfFloat[b1], (this.frames[b1]).linearValue, arrayOfPoint3f1[b1], (float)(this.frames[b1]).heading, (float)(this.frames[b1]).pitch, (float)(this.frames[b1]).bank, arrayOfPoint3f2[b1], (float)(this.frames[b1]).tension, (float)(this.frames[b1]).continuity, (float)(this.frames[b1]).bias);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 634 */         debugOutputLn(2, "pos, knots, quat = " + arrayOfPoint3f1[b1] + arrayOfFloat[b1] + arrayOfQuat4f[b1]);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 639 */       KBRotPosScaleSplinePathInterpolator kBRotPosScaleSplinePathInterpolator = new KBRotPosScaleSplinePathInterpolator(alpha, paramTransformGroup, transform3D, arrayOfKBKeyFrame);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 644 */       if (kBRotPosScaleSplinePathInterpolator != null) {
/* 645 */         this.behaviors = kBRotPosScaleSplinePathInterpolator;
/* 646 */         BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 1000000.0D);
/*     */         
/* 648 */         kBRotPosScaleSplinePathInterpolator.setSchedulingBounds(boundingSphere);
/* 649 */         paramTransformGroup.setCapability(18);
/* 650 */         paramTransformGroup.addChild(this.behaviors);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 660 */   Behavior getBehaviors() { return this.behaviors; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LwsFrame getFirstFrame() {
/* 668 */     if (this.numFrames > 0) {
/* 669 */       return this.frames[0];
/*     */     }
/* 671 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void printVals() {
/* 678 */     debugOutputLn(2, "   motionName = " + this.motionName);
/* 679 */     debugOutputLn(2, "   numChannels = " + this.numChannels);
/* 680 */     debugOutputLn(2, "   numFrames = " + this.numFrames);
/* 681 */     debugOutputLn(2, "   loop = " + this.loop);
/* 682 */     for (byte b = 0; b < this.numFrames; b++) {
/* 683 */       debugOutputLn(2, "       FRAME " + b);
/* 684 */       this.frames[b].printVals();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\lw3d\LwsMotion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */