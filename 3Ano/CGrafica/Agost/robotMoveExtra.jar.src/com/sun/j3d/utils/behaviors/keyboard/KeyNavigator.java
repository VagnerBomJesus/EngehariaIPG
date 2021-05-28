/*     */ package com.sun.j3d.utils.behaviors.keyboard;
/*     */ 
/*     */ import java.awt.event.KeyEvent;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Quat4d;
/*     */ import javax.vecmath.Vector3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyNavigator
/*     */ {
/*     */   private Vector3d navVec;
/*     */   private long time;
/*     */   private Vector3d fwdAcc;
/*     */   private Vector3d bwdAcc;
/*     */   private Vector3d leftAcc;
/*     */   private Vector3d rightAcc;
/*     */   private Vector3d upAcc;
/*     */   private Vector3d downAcc;
/*     */   private Vector3d fwdDrag;
/*     */   private Vector3d bwdDrag;
/*     */   private Vector3d leftDrag;
/*     */   private Vector3d rightDrag;
/*     */   private Vector3d upDrag;
/*     */   private Vector3d downDrag;
/*     */   private double fwdVMax;
/*     */   private double bwdVMax;
/*     */   private double leftVMax;
/*     */   private double rightVMax;
/*     */   private double upVMax;
/*     */   private double downVMax;
/*     */   private float leftRotAngle;
/*     */   private float rightRotAngle;
/*     */   private float upRotAngle;
/*     */   private float downRotAngle;
/*     */   private double mmx;
/*     */   private Vector3d a;
/*     */   private Vector3d dv;
/*     */   private Point3d dp;
/*     */   private Quat4d udQuat;
/*     */   private Quat4d lrQuat;
/*     */   private Vector3d vpPos;
/*     */   private double vpScale;
/*     */   private Quat4d vpQuat;
/*     */   private Matrix4d vpMatrix;
/*     */   private Transform3D vpTrans;
/*     */   private Matrix4d mat;
/*     */   private Vector3d nda;
/*     */   private Vector3d temp;
/*     */   private Transform3D nominal;
/*     */   private TransformGroup targetTG;
/*     */   private static final int UP_ARROW = 1;
/*     */   private static final int DOWN_ARROW = 2;
/*     */   private static final int LEFT_ARROW = 4;
/*     */   private static final int RIGHT_ARROW = 8;
/*     */   private static final int PLUS_SIGN = 16;
/*     */   private static final int MINUS_SIGN = 32;
/*     */   private static final int PAGE_UP = 64;
/*     */   private static final int PAGE_DOWN = 128;
/*     */   private static final int HOME_DIR = 256;
/*     */   private static final int HOME_NOMINAL = 512;
/*     */   private static final int SHIFT = 1024;
/*     */   private static final int ALT = 2048;
/*     */   private static final int META = 4096;
/*     */   private static final int KEY_UP = 8192;
/*     */   private static final int KEY_DOWN = 16384;
/*     */   private int key_state;
/*     */   private int modifier_key_state;
/*     */   
/*     */   public KeyNavigator(TransformGroup paramTransformGroup) {
/*  91 */     this.a = new Vector3d();
/*  92 */     this.dv = new Vector3d();
/*  93 */     this.dp = new Point3d();
/*  94 */     this.udQuat = new Quat4d();
/*  95 */     this.lrQuat = new Quat4d();
/*  96 */     this.vpPos = new Vector3d();
/*     */     
/*  98 */     this.vpQuat = new Quat4d();
/*  99 */     this.vpMatrix = new Matrix4d();
/* 100 */     this.vpTrans = new Transform3D();
/* 101 */     this.mat = new Matrix4d();
/* 102 */     this.nda = new Vector3d();
/* 103 */     this.temp = new Vector3d();
/* 104 */     this.nominal = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.key_state = 0;
/* 126 */     this.modifier_key_state = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.targetTG = paramTransformGroup;
/* 136 */     paramTransformGroup.getTransform(this.nominal);
/*     */     
/* 138 */     this.mmx = 128.0D;
/* 139 */     this.navVec = new Vector3d(0.0D, 0.0D, 0.0D);
/*     */     
/* 141 */     this.fwdAcc = new Vector3d(0.0D, 0.0D, -this.mmx);
/* 142 */     this.bwdAcc = new Vector3d(0.0D, 0.0D, this.mmx);
/* 143 */     this.leftAcc = new Vector3d(-this.mmx, 0.0D, 0.0D);
/* 144 */     this.rightAcc = new Vector3d(this.mmx, 0.0D, 0.0D);
/* 145 */     this.upAcc = new Vector3d(0.0D, this.mmx, 0.0D);
/* 146 */     this.downAcc = new Vector3d(0.0D, -this.mmx, 0.0D);
/*     */     
/* 148 */     this.fwdDrag = new Vector3d(0.0D, 0.0D, this.mmx);
/* 149 */     this.bwdDrag = new Vector3d(0.0D, 0.0D, -this.mmx);
/* 150 */     this.leftDrag = new Vector3d(this.mmx, 0.0D, 0.0D);
/* 151 */     this.rightDrag = new Vector3d(-this.mmx, 0.0D, 0.0D);
/* 152 */     this.upDrag = new Vector3d(0.0D, -this.mmx, 0.0D);
/* 153 */     this.downDrag = new Vector3d(0.0D, this.mmx, 0.0D);
/*     */     
/* 155 */     this.fwdVMax = -this.mmx;
/* 156 */     this.bwdVMax = this.mmx;
/* 157 */     this.leftVMax = -this.mmx;
/* 158 */     this.rightVMax = this.mmx;
/* 159 */     this.upVMax = this.mmx;
/* 160 */     this.downVMax = -this.mmx;
/*     */     
/* 162 */     this.leftRotAngle = -2.0943952F;
/* 163 */     this.rightRotAngle = 2.0943952F;
/* 164 */     this.upRotAngle = 2.0943952F;
/* 165 */     this.downRotAngle = -2.0943952F;
/*     */ 
/*     */     
/* 168 */     this.time = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private long getDeltaTime() {
/* 174 */     long l1 = System.currentTimeMillis();
/* 175 */     long l2 = l1 - this.time;
/* 176 */     this.time = l1;
/* 177 */     if (l2 > 2000L) return 0L; 
/* 178 */     return l2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void genRotQuat(double paramDouble, int paramInt, Quat4d paramQuat4d) {
/* 185 */     paramQuat4d.x = paramQuat4d.y = paramQuat4d.z = 0.0D;
/* 186 */     paramQuat4d.w = Math.cos(paramDouble / 2.0D);
/*     */     
/* 188 */     double d = 1.0D - paramQuat4d.w * paramQuat4d.w;
/*     */     
/* 190 */     if (d > 0.0D) {
/* 191 */       d = Math.sqrt(d);
/*     */     } else {
/*     */       return;
/*     */     } 
/* 195 */     if (paramDouble < 0.0D)
/* 196 */       d = -d; 
/* 197 */     if (paramInt == 0) {
/* 198 */       paramQuat4d.x = d;
/* 199 */     } else if (paramInt == 1) {
/* 200 */       paramQuat4d.y = d;
/*     */     } else {
/* 202 */       paramQuat4d.z = d;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void accKeyAdd(Vector3d paramVector3d1, Vector3d paramVector3d2, Vector3d paramVector3d3, double paramDouble) {
/* 209 */     this.nda.scale(paramDouble, paramVector3d2);
/*     */     
/* 211 */     this.nda.sub(paramVector3d3);
/*     */ 
/*     */     
/* 214 */     paramVector3d1.add(this.nda);
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
/*     */   public void integrateTransformChanges() {
/*     */     double d3, d2, d1;
/* 231 */     this.targetTG.getTransform(this.vpTrans);
/*     */     
/* 233 */     this.vpScale = this.vpTrans.get(this.vpQuat, this.vpPos);
/*     */ 
/*     */     
/* 236 */     double d8 = getDeltaTime();
/* 237 */     d8 *= 0.001D;
/*     */ 
/*     */     
/* 240 */     if ((this.modifier_key_state & 0x400) != 0 && (this.modifier_key_state & 0x1000) == 0) {
/*     */       
/* 242 */       d1 = 3.0D; d2 = 2.0D; d3 = 4.0D;
/*     */     }
/* 244 */     else if ((this.modifier_key_state & 0x400) == 0 && (this.modifier_key_state & 0x1000) != 0) {
/*     */       
/* 246 */       d1 = 0.1D; d2 = 0.1D; d3 = 0.1D;
/*     */     }
/* 248 */     else if ((this.modifier_key_state & 0x400) != 0 && (this.modifier_key_state & 0x1000) != 0) {
/*     */       
/* 250 */       d1 = 0.3D; d2 = 0.5D; d3 = 0.1D;
/*     */     } else {
/*     */       
/* 253 */       d2 = d1 = 1.0D; d3 = 4.0D;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     this.a.x = this.a.y = this.a.z = 0.0D;
/*     */ 
/*     */     
/* 263 */     if ((this.key_state & true) != 0 && (this.key_state & 0x2) == 0) {
/* 264 */       accKeyAdd(this.a, this.fwdAcc, this.fwdDrag, d1);
/*     */     }
/* 266 */     else if ((this.key_state & true) == 0 && (this.key_state & 0x2) != 0) {
/* 267 */       accKeyAdd(this.a, this.bwdAcc, this.bwdDrag, d1);
/*     */     } 
/* 269 */     if ((this.modifier_key_state & 0x800) != 0 && (this.key_state & 0x4) != 0 && (this.key_state & 0x8) == 0) {
/*     */       
/* 271 */       accKeyAdd(this.a, this.leftAcc, this.leftDrag, d1);
/*     */     }
/* 273 */     else if ((this.modifier_key_state & 0x800) != 0 && (this.key_state & 0x4) == 0 && (this.key_state & 0x8) != 0) {
/*     */       
/* 275 */       accKeyAdd(this.a, this.rightAcc, this.rightDrag, d1);
/*     */     } 
/* 277 */     if ((this.modifier_key_state & 0x800) != 0 && (this.key_state & 0x40) != 0 && (this.key_state & 0x80) == 0) {
/*     */       
/* 279 */       accKeyAdd(this.a, this.upAcc, this.upDrag, d1);
/*     */     }
/* 281 */     else if ((this.modifier_key_state & 0x800) != 0 && (this.key_state & 0x40) == 0 && (this.key_state & 0x80) != 0) {
/*     */       
/* 283 */       accKeyAdd(this.a, this.downAcc, this.downDrag, d1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 289 */     double d4 = this.navVec.z + this.a.z * d8;
/* 290 */     if (d4 < 0.0D) {
/* 291 */       if (d4 + this.fwdDrag.z * d8 < 0.0D)
/* 292 */       { this.a.add(this.fwdDrag); }
/*     */       else
/* 294 */       { this.a.z -= d4 / d8; } 
/* 295 */     } else if (d4 > 0.0D) {
/* 296 */       if (d4 + this.bwdDrag.z * d8 > 0.0D) {
/* 297 */         this.a.add(this.bwdDrag);
/*     */       } else {
/* 299 */         this.a.z -= d4 / d8;
/*     */       } 
/*     */     } 
/* 302 */     d4 = this.navVec.x + this.a.x * d8;
/* 303 */     if (d4 < 0.0D) {
/* 304 */       if (d4 + this.leftDrag.x * d8 < 0.0D)
/* 305 */       { this.a.add(this.leftDrag); }
/*     */       else
/* 307 */       { this.a.x -= d4 / d8; } 
/* 308 */     } else if (d4 > 0.0D) {
/* 309 */       if (d4 + this.rightDrag.x * d8 > 0.0D) {
/* 310 */         this.a.add(this.rightDrag);
/*     */       } else {
/* 312 */         this.a.x -= d4 / d8;
/*     */       } 
/*     */     } 
/* 315 */     d4 = this.navVec.y + this.a.y * d8;
/* 316 */     if (d4 < 0.0D) {
/* 317 */       if (d4 + this.downDrag.y * d8 < 0.0D)
/* 318 */       { this.a.add(this.downDrag); }
/*     */       else
/* 320 */       { this.a.y -= d4 / d8; } 
/* 321 */     } else if (d4 > 0.0D) {
/* 322 */       if (d4 + this.upDrag.y * d8 > 0.0D) {
/* 323 */         this.a.add(this.upDrag);
/*     */       } else {
/* 325 */         this.a.y -= d4 / d8;
/*     */       } 
/*     */     } 
/*     */     
/* 329 */     this.dv.scale(d8, this.a);
/* 330 */     this.navVec.add(this.dv);
/*     */ 
/*     */     
/* 333 */     if (this.navVec.z < d1 * this.fwdVMax) this.navVec.z = d1 * this.fwdVMax; 
/* 334 */     if (this.navVec.z > d1 * this.bwdVMax) this.navVec.z = d1 * this.bwdVMax; 
/* 335 */     if (this.navVec.x < d1 * this.leftVMax) this.navVec.x = d1 * this.leftVMax; 
/* 336 */     if (this.navVec.x > d1 * this.rightVMax) this.navVec.x = d1 * this.rightVMax; 
/* 337 */     if (this.navVec.y > d1 * this.upVMax) this.navVec.y = d1 * this.upVMax; 
/* 338 */     if (this.navVec.y < d1 * this.downVMax) this.navVec.y = d1 * this.downVMax;
/*     */ 
/*     */     
/* 341 */     this.dp.scale(d8, this.navVec);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 347 */     double d7 = this.vpScale / 1.0D;
/* 348 */     this.dp.scale(d7, this.dp);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 353 */     double d6 = 0.0D, d5 = d6;
/*     */ 
/*     */     
/* 356 */     if ((this.modifier_key_state & 0x800) == 0 && (this.key_state & 0x4) != 0 && (this.key_state & 0x8) == 0) {
/*     */       
/* 358 */       d6 = this.leftRotAngle;
/* 359 */     } else if ((this.modifier_key_state & 0x800) == 0 && (this.key_state & 0x4) == 0 && (this.key_state & 0x8) != 0) {
/*     */       
/* 361 */       d6 = this.rightRotAngle;
/*     */     } 
/* 363 */     if ((this.modifier_key_state & 0x800) == 0 && (this.key_state & 0x40) != 0 && (this.key_state & 0x80) == 0) {
/*     */       
/* 365 */       d5 = this.upRotAngle;
/* 366 */     } else if ((this.modifier_key_state & 0x800) == 0 && (this.key_state & 0x40) == 0 && (this.key_state & 0x80) != 0) {
/*     */       
/* 368 */       d5 = this.downRotAngle;
/*     */     } 
/* 370 */     d6 *= d2;
/* 371 */     d5 *= d2;
/*     */ 
/*     */     
/* 374 */     d6 *= d8;
/* 375 */     d5 *= d8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 381 */     this.vpQuat.inverse();
/*     */     
/* 383 */     if (d6 != 0.0D) {
/* 384 */       genRotQuat(d6, 1, this.lrQuat);
/* 385 */       this.vpQuat.mul(this.lrQuat, this.vpQuat);
/*     */     } 
/*     */     
/* 388 */     if (d5 != 0.0D) {
/* 389 */       genRotQuat(d5, 0, this.udQuat);
/* 390 */       this.vpQuat.mul(this.udQuat, this.vpQuat);
/*     */     } 
/*     */ 
/*     */     
/* 394 */     this.vpQuat.inverse();
/* 395 */     this.vpQuat.normalize();
/* 396 */     this.mat.set(this.vpQuat);
/* 397 */     this.mat.transform(this.dp);
/*     */ 
/*     */     
/* 400 */     if ((this.key_state & 0x10) != 0) {
/* 401 */       this.vpScale *= (1.0D + d3 * d8);
/* 402 */       if (this.vpScale > 1.0E15D) this.vpScale = 1.0D; 
/* 403 */     } else if ((this.key_state & 0x20) != 0) {
/* 404 */       this.vpScale /= (1.0D + d3 * d8);
/* 405 */       if (this.vpScale < 1.0E-13D) this.vpScale = 1.0D;
/*     */     
/*     */     } 
/*     */     
/* 409 */     this.vpPos.add(this.dp);
/*     */     
/* 411 */     if ((this.key_state & 0x200) != 0) {
/* 412 */       resetVelocity();
/*     */ 
/*     */       
/* 415 */       this.vpScale = this.nominal.get(this.vpQuat, this.vpPos);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 421 */     this.vpTrans.set(this.vpQuat, this.vpPos, this.vpScale);
/* 422 */     this.targetTG.setTransform(this.vpTrans);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   private void resetVelocity() { this.navVec.x = this.navVec.y = this.navVec.z = 0.0D; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processKeyEvent(KeyEvent paramKeyEvent) {
/* 440 */     int i = paramKeyEvent.getKeyCode();
/* 441 */     char c = paramKeyEvent.getKeyChar();
/*     */ 
/*     */ 
/*     */     
/* 445 */     if (paramKeyEvent.getID() == 402) {
/* 446 */       if (c == '+') { this.key_state &= 0xFFFFFFEF; }
/*     */       else
/* 448 */       { switch (i) { case 38:
/* 449 */             this.key_state &= 0xFFFFFFFE; break;
/* 450 */           case 40: this.key_state &= 0xFFFFFFFD; break;
/* 451 */           case 37: this.key_state &= 0xFFFFFFFB; break;
/* 452 */           case 39: this.key_state &= 0xFFFFFFF7; break;
/* 453 */           case 33: this.key_state &= 0xFFFFFFBF; break;
/* 454 */           case 34: this.key_state &= 0xFFFFFF7F; break;
/* 455 */           case 61: this.key_state &= 0xFFFFFDFF; break;
/* 456 */           default: switch (c) { case '-':
/* 457 */                 this.key_state &= 0xFFFFFFDF; break; }  break; }
/*     */          }
/*     */     
/* 460 */     } else if (paramKeyEvent.getID() == 401) {
/* 461 */       if (c == '+') this.key_state |= 0x10; 
/* 462 */       switch (i) { case 38:
/* 463 */           this.key_state |= 0x1; break;
/* 464 */         case 40: this.key_state |= 0x2; break;
/* 465 */         case 37: this.key_state |= 0x4; break;
/* 466 */         case 39: this.key_state |= 0x8; break;
/* 467 */         case 33: this.key_state |= 0x40; break;
/* 468 */         case 34: this.key_state |= 0x80; break;
/* 469 */         case 61: this.key_state |= 0x200; break;
/* 470 */         default: switch (c) { case '-':
/* 471 */               this.key_state |= 0x20;
/*     */               break; }
/*     */           
/*     */           break; }
/*     */     
/*     */     } 
/* 477 */     if (paramKeyEvent.isShiftDown()) {
/* 478 */       this.modifier_key_state |= 0x400;
/*     */     } else {
/* 480 */       this.modifier_key_state &= 0xFFFFFBFF;
/*     */     } 
/* 482 */     if (paramKeyEvent.isMetaDown()) {
/* 483 */       this.modifier_key_state |= 0x1000;
/*     */     } else {
/* 485 */       this.modifier_key_state &= 0xFFFFEFFF;
/*     */     } 
/* 487 */     if (paramKeyEvent.isAltDown()) {
/* 488 */       this.modifier_key_state |= 0x800;
/*     */     } else {
/* 490 */       this.modifier_key_state &= 0xFFFFF7FF;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\behaviors\keyboard\KeyNavigator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */