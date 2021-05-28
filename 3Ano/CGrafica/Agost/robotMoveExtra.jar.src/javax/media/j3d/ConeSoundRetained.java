/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point2f;
/*     */ import javax.vecmath.Point3f;
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
/*     */ class ConeSoundRetained
/*     */   extends PointSoundRetained
/*     */ {
/*  35 */   Vector3f direction = new Vector3f(0.0F, 0.0F, 1.0F);
/*     */ 
/*     */   
/*  38 */   Vector3f xformDirection = new Vector3f(0.0F, 0.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final int NO_FILTERING = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final int LOW_PASS = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   float[] backAttenuationDistance = null;
/*  56 */   float[] backAttenuationGain = null;
/*     */   
/*  58 */   float[] angularDistance = { 0.0F, 1.5707964F };
/*  59 */   float[] angularGain = { 1.0F, 0.0F };
/*  60 */   int filterType = -1;
/*  61 */   float[] frequencyCutoff = { -1.0F, -1.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDistanceGain(Point2f[] paramArrayOfPoint2f1, Point2f[] paramArrayOfPoint2f2) {
/*  85 */     setDistanceGain(paramArrayOfPoint2f1);
/*  86 */     setBackDistanceGain(paramArrayOfPoint2f2);
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
/*     */   void setDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4) {
/* 100 */     setDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2);
/* 101 */     setBackDistanceGain(paramArrayOfFloat3, paramArrayOfFloat4);
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
/*     */   void setBackDistanceGain(Point2f[] paramArrayOfPoint2f) {
/* 115 */     if (paramArrayOfPoint2f == null) {
/* 116 */       this.backAttenuationDistance = null;
/* 117 */       this.backAttenuationGain = null;
/*     */     } else {
/*     */       
/* 120 */       int i = paramArrayOfPoint2f.length;
/* 121 */       if (i == 0) {
/* 122 */         this.backAttenuationDistance = null;
/* 123 */         this.backAttenuationGain = null;
/*     */       } else {
/*     */         
/* 126 */         this.backAttenuationDistance = new float[i];
/* 127 */         this.backAttenuationGain = new float[i];
/* 128 */         for (byte b = 0; b < i; b++) {
/* 129 */           this.backAttenuationDistance[b] = (paramArrayOfPoint2f[b]).x;
/* 130 */           this.backAttenuationGain[b] = (paramArrayOfPoint2f[b]).y;
/*     */         } 
/*     */       } 
/*     */     } 
/* 134 */     dispatchAttribChange(256, paramArrayOfPoint2f);
/* 135 */     if (this.source != null && this.source.isLive()) {
/* 136 */       notifySceneGraphChanged(false);
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
/*     */   void setBackDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 149 */     int i = 0;
/*     */     
/* 151 */     if (paramArrayOfFloat1 == null || paramArrayOfFloat2 == null) {
/* 152 */       this.backAttenuationDistance = null;
/* 153 */       this.backAttenuationGain = null;
/*     */     }
/*     */     else {
/*     */       
/* 157 */       int j = paramArrayOfFloat2.length;
/* 158 */       i = paramArrayOfFloat1.length;
/* 159 */       if (i == 0 || j == 0) {
/* 160 */         this.backAttenuationDistance = null;
/* 161 */         this.backAttenuationGain = null;
/*     */       } else {
/*     */         
/* 164 */         this.backAttenuationDistance = new float[i];
/* 165 */         this.backAttenuationGain = new float[i];
/*     */         
/* 167 */         System.arraycopy(paramArrayOfFloat1, 0, this.backAttenuationDistance, 0, i);
/*     */ 
/*     */         
/* 170 */         if (i <= j) {
/* 171 */           System.arraycopy(paramArrayOfFloat2, 0, this.backAttenuationGain, 0, i);
/*     */         }
/*     */         else {
/*     */           
/* 175 */           System.arraycopy(paramArrayOfFloat2, 0, this.backAttenuationGain, 0, j);
/*     */ 
/*     */           
/* 178 */           for (int k = j; k < i; k++) {
/* 179 */             this.backAttenuationGain[k] = paramArrayOfFloat2[j - 1];
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     Point2f[] arrayOfPoint2f = new Point2f[i];
/* 186 */     for (byte b = 0; b < i; b++) {
/* 187 */       arrayOfPoint2f[b] = new Point2f(this.backAttenuationDistance[b], this.backAttenuationGain[b]);
/*     */     }
/*     */     
/* 190 */     dispatchAttribChange(256, arrayOfPoint2f);
/* 191 */     if (this.source != null && this.source.isLive()) {
/* 192 */       notifySceneGraphChanged(false);
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
/*     */   void getDistanceGain(Point2f[] paramArrayOfPoint2f1, Point2f[] paramArrayOfPoint2f2) {
/* 205 */     getDistanceGain(paramArrayOfPoint2f1);
/* 206 */     getBackDistanceGain(paramArrayOfPoint2f2);
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
/*     */   void getDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4) {
/* 220 */     getDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2);
/* 221 */     getBackDistanceGain(paramArrayOfFloat3, paramArrayOfFloat4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getBackDistanceGain(Point2f[] paramArrayOfPoint2f) {
/* 232 */     if (paramArrayOfPoint2f == null)
/*     */       return; 
/* 234 */     if (this.backAttenuationDistance == null || this.backAttenuationGain == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 239 */     int i = this.backAttenuationDistance.length;
/* 240 */     int j = paramArrayOfPoint2f.length;
/* 241 */     if (i < j)
/* 242 */       i = j; 
/* 243 */     for (byte b = 0; b < i; b++) {
/* 244 */       (paramArrayOfPoint2f[b]).x = this.backAttenuationDistance[b];
/* 245 */       (paramArrayOfPoint2f[b]).y = this.backAttenuationGain[b];
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
/*     */   void getBackDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 257 */     if (paramArrayOfFloat1 == null || paramArrayOfFloat2 == null)
/*     */       return; 
/* 259 */     if (this.backAttenuationDistance == null || this.backAttenuationGain == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 265 */     int i = this.backAttenuationDistance.length;
/* 266 */     int j = paramArrayOfFloat1.length;
/* 267 */     if (i > j)
/* 268 */       i = j; 
/* 269 */     System.arraycopy(this.backAttenuationDistance, 0, paramArrayOfFloat1, 0, i);
/* 270 */     i = this.backAttenuationGain.length;
/* 271 */     int k = paramArrayOfFloat2.length;
/* 272 */     if (i > k)
/* 273 */       i = k; 
/* 274 */     System.arraycopy(this.backAttenuationGain, 0, paramArrayOfFloat2, 0, i);
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
/*     */   void setDirection(Vector3f paramVector3f) {
/* 289 */     if (this.staticTransform != null) {
/* 290 */       this.staticTransform.transform.transform(paramVector3f, this.direction);
/*     */     } else {
/* 292 */       this.direction.set(paramVector3f);
/*     */     } 
/* 294 */     dispatchAttribChange(512, new Vector3f(this.direction));
/*     */ 
/*     */     
/* 297 */     if (this.source != null && this.source.isLive()) {
/* 298 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDirection(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 309 */     this.direction.x = paramFloat1;
/* 310 */     this.direction.y = paramFloat2;
/* 311 */     this.direction.z = paramFloat3;
/* 312 */     if (this.staticTransform != null) {
/* 313 */       this.staticTransform.transform.transform(this.direction);
/*     */     }
/* 315 */     dispatchAttribChange(512, new Vector3f(this.direction));
/*     */     
/* 317 */     if (this.source != null && this.source.isLive()) {
/* 318 */       notifySceneGraphChanged(false);
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
/*     */   void getDirection(Vector3f paramVector3f) {
/* 330 */     if (this.staticTransform != null) {
/* 331 */       Transform3D transform3D = this.staticTransform.getInvTransform();
/* 332 */       transform3D.transform(this.direction, paramVector3f);
/*     */     } else {
/* 334 */       paramVector3f.set(this.direction);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 340 */   void getXformDirection(Vector3f paramVector3f) { paramVector3f.set(this.xformDirection); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAngularAttenuation(Point2f[] paramArrayOfPoint2f) {
/* 355 */     int i = 0;
/* 356 */     this.filterType = -1;
/* 357 */     if (paramArrayOfPoint2f == null) {
/* 358 */       this.angularDistance = null;
/* 359 */       this.angularGain = null;
/*     */     } else {
/*     */       
/* 362 */       i = paramArrayOfPoint2f.length;
/* 363 */       if (i == 0) {
/* 364 */         this.angularDistance = null;
/* 365 */         this.angularGain = null;
/*     */       } else {
/*     */         
/* 368 */         this.angularDistance = new float[i];
/* 369 */         this.angularGain = new float[i];
/* 370 */         for (byte b1 = 0; b1 < i; b1++) {
/* 371 */           this.angularDistance[b1] = (paramArrayOfPoint2f[b1]).x;
/* 372 */           this.angularGain[b1] = (paramArrayOfPoint2f[b1]).y;
/*     */         } 
/*     */       } 
/*     */     } 
/* 376 */     Point3f[] arrayOfPoint3f = new Point3f[i];
/* 377 */     for (byte b = 0; b < i; b++) {
/* 378 */       arrayOfPoint3f[b] = new Point3f(this.angularDistance[b], this.angularGain[b], -1.0F);
/*     */     }
/*     */ 
/*     */     
/* 382 */     dispatchAttribChange(1024, arrayOfPoint3f);
/*     */     
/* 384 */     if (this.source != null && this.source.isLive()) {
/* 385 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAngularAttenuation(Point3f[] paramArrayOfPoint3f) {
/* 394 */     if (paramArrayOfPoint3f == null) {
/* 395 */       this.angularDistance = null;
/* 396 */       this.angularGain = null;
/* 397 */       this.frequencyCutoff = null;
/* 398 */       this.filterType = -1;
/*     */     } else {
/*     */       
/* 401 */       int i = paramArrayOfPoint3f.length;
/* 402 */       if (i == 0) {
/* 403 */         this.angularDistance = null;
/* 404 */         this.angularGain = null;
/* 405 */         this.frequencyCutoff = null;
/* 406 */         this.filterType = -1;
/*     */       } else {
/*     */         
/* 409 */         this.angularDistance = new float[i];
/* 410 */         this.angularGain = new float[i];
/* 411 */         this.frequencyCutoff = new float[i];
/* 412 */         this.filterType = 1;
/* 413 */         for (byte b = 0; b < i; b++) {
/* 414 */           this.angularDistance[b] = (paramArrayOfPoint3f[b]).x;
/* 415 */           this.angularGain[b] = (paramArrayOfPoint3f[b]).y;
/* 416 */           this.frequencyCutoff[b] = (paramArrayOfPoint3f[b]).z;
/*     */         } 
/*     */       } 
/*     */     } 
/* 420 */     dispatchAttribChange(1024, paramArrayOfPoint3f);
/* 421 */     if (this.source != null && this.source.isLive()) {
/* 422 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAngularAttenuation(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3) {
/* 432 */     int i = 0;
/* 433 */     if (paramArrayOfFloat1 == null || paramArrayOfFloat2 == null || paramArrayOfFloat3 == null) {
/* 434 */       this.angularDistance = null;
/* 435 */       this.angularGain = null;
/* 436 */       this.frequencyCutoff = null;
/* 437 */       this.filterType = -1;
/*     */     } else {
/*     */       
/* 440 */       i = paramArrayOfFloat1.length;
/* 441 */       int j = paramArrayOfFloat2.length;
/* 442 */       if (i == 0 || j == 0) {
/* 443 */         this.angularDistance = null;
/* 444 */         this.angularGain = null;
/* 445 */         this.frequencyCutoff = null;
/* 446 */         this.filterType = -1;
/*     */       } else {
/*     */         
/* 449 */         int k = paramArrayOfFloat3.length;
/* 450 */         this.angularDistance = new float[i];
/* 451 */         this.angularGain = new float[i];
/* 452 */         this.frequencyCutoff = new float[i];
/*     */         
/* 454 */         System.arraycopy(paramArrayOfFloat1, 0, this.angularDistance, 0, i);
/*     */         
/* 456 */         if (i <= j) {
/* 457 */           System.arraycopy(paramArrayOfFloat2, 0, this.angularGain, 0, i);
/*     */         } else {
/*     */           
/* 460 */           System.arraycopy(paramArrayOfFloat2, 0, this.angularGain, 0, j);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 465 */           for (int m = j; m < i; m++) {
/* 466 */             this.angularGain[m] = paramArrayOfFloat2[j - 1];
/*     */           }
/*     */         } 
/*     */         
/* 470 */         if (k == 0) {
/* 471 */           this.filterType = -1;
/*     */         } else {
/* 473 */           this.filterType = 1;
/* 474 */           if (i <= k) {
/* 475 */             System.arraycopy(paramArrayOfFloat3, 0, this.frequencyCutoff, 0, i);
/*     */           } else {
/*     */             
/* 478 */             System.arraycopy(paramArrayOfFloat3, 0, this.frequencyCutoff, 0, k);
/*     */ 
/*     */             
/* 481 */             for (int m = k; m < i; m++) {
/* 482 */               this.frequencyCutoff[m] = paramArrayOfFloat3[k - 1];
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 488 */     Point3f[] arrayOfPoint3f = new Point3f[i];
/* 489 */     for (byte b = 0; b < i; b++) {
/* 490 */       if (this.filterType != -1) {
/* 491 */         arrayOfPoint3f[b] = new Point3f(this.angularDistance[b], this.angularGain[b], this.frequencyCutoff[b]);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 496 */         arrayOfPoint3f[b] = new Point3f(this.angularDistance[b], this.angularGain[b], -1.0F);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 501 */     dispatchAttribChange(1024, arrayOfPoint3f);
/* 502 */     if (this.source != null && this.source.isLive()) {
/* 503 */       notifySceneGraphChanged(false);
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
/*     */   int getAngularAttenuationLength() {
/* 515 */     if (this.angularDistance == null) {
/* 516 */       return 0;
/*     */     }
/* 518 */     return this.angularDistance.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getAngularAttenuation(Point3f[] paramArrayOfPoint3f) {
/* 528 */     if (this.angularDistance == null || this.angularGain == null)
/*     */       return; 
/* 530 */     if (paramArrayOfPoint3f == null)
/*     */       return; 
/* 532 */     int i = this.angularDistance.length;
/* 533 */     if (paramArrayOfPoint3f.length < i)
/* 534 */       i = paramArrayOfPoint3f.length; 
/* 535 */     for (byte b = 0; b < i; b++) {
/* 536 */       (paramArrayOfPoint3f[b]).x = this.angularDistance[b];
/* 537 */       (paramArrayOfPoint3f[b]).y = this.angularGain[b];
/* 538 */       if (this.filterType == -1 || this.frequencyCutoff == null) {
/* 539 */         (paramArrayOfPoint3f[b]).z = -1.0F;
/* 540 */       } else if (this.filterType == 1) {
/* 541 */         (paramArrayOfPoint3f[b]).z = this.frequencyCutoff[b];
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
/*     */   void getAngularAttenuation(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3) {
/* 554 */     if (paramArrayOfFloat1 == null || paramArrayOfFloat2 == null || paramArrayOfFloat3 == null)
/*     */       return; 
/* 556 */     if (this.angularDistance == null || this.angularGain == null)
/*     */       return; 
/* 558 */     int i = this.angularDistance.length;
/* 559 */     if (paramArrayOfFloat1.length < i)
/* 560 */       i = paramArrayOfFloat1.length; 
/* 561 */     System.arraycopy(this.angularDistance, 0, paramArrayOfFloat1, 0, i);
/*     */     
/* 563 */     int j = this.angularGain.length;
/* 564 */     if (paramArrayOfFloat2.length < j)
/* 565 */       j = paramArrayOfFloat2.length; 
/* 566 */     System.arraycopy(this.angularGain, 0, paramArrayOfFloat2, 0, j);
/*     */     
/* 568 */     int k = 0;
/* 569 */     if (this.frequencyCutoff == null || this.filterType == -1) {
/* 570 */       k = paramArrayOfFloat3.length;
/*     */     } else {
/* 572 */       k = this.frequencyCutoff.length;
/* 573 */       if (paramArrayOfFloat3.length < k)
/* 574 */         k = paramArrayOfFloat3.length; 
/*     */     } 
/* 576 */     if (this.filterType == -1 || this.frequencyCutoff == null)
/* 577 */       for (byte b = 0; b < k; b++) {
/* 578 */         paramArrayOfFloat3[b] = -1.0F;
/*     */       } 
/* 580 */     if (this.filterType == 1) {
/* 581 */       System.arraycopy(this.frequencyCutoff, 0, paramArrayOfFloat3, 0, k);
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
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 595 */     Object object = null;
/* 596 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 597 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/* 598 */     SoundRetained[] arrayOfSoundRetained = (SoundRetained[])paramArrayOfObject[3];
/* 599 */     if (i == -1) {
/*     */       
/* 601 */       initMirrorObject((ConeSoundRetained)paramArrayOfObject[2]);
/*     */       return;
/*     */     } 
/* 604 */     if ((i & 0x200) != 0) {
/* 605 */       for (byte b = 0; b < j; b++) {
/* 606 */         ConeSoundRetained coneSoundRetained = (ConeSoundRetained)arrayOfSoundRetained[b];
/* 607 */         coneSoundRetained.direction = (Vector3f)paramArrayOfObject[4];
/* 608 */         coneSoundRetained.getLastLocalToVworld().transform(coneSoundRetained.direction, coneSoundRetained.xformDirection);
/*     */         
/* 610 */         coneSoundRetained.xformDirection.normalize();
/*     */       } 
/*     */     }
/*     */     
/* 614 */     super.updateMirrorObject(paramArrayOfObject);
/*     */   }
/*     */   
/*     */   void initMirrorObject(ConeSoundRetained paramConeSoundRetained) {
/* 618 */     initMirrorObject(paramConeSoundRetained);
/* 619 */     paramConeSoundRetained.direction.set(this.direction);
/* 620 */     paramConeSoundRetained.xformDirection.set(this.xformDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   void updateTransformChange() {
/* 625 */     Transform3D transform3D = getLastLocalToVworld();
/*     */     
/* 627 */     super.updateTransformChange();
/* 628 */     transform3D.transform(this.direction, this.xformDirection);
/* 629 */     this.xformDirection.normalize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 638 */     super.mergeTransform(paramTransformGroupRetained);
/* 639 */     paramTransformGroupRetained.transform.transform(this.direction);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ConeSoundRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */