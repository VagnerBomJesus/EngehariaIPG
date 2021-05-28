/*     */ package com.sun.j3d.audioengines.javasound;
/*     */ 
/*     */ import com.sun.j3d.audioengines.AuralParameters;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JSDirectionalSample
/*     */   extends JSPositionalSample
/*     */ {
/*  66 */   Vector3f xformDirection = new Vector3f(0.0F, 0.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setXformedDirection() {
/*  77 */     if (!getVWrldXfrmFlag()) {
/*     */ 
/*     */       
/*  80 */       this.xformDirection.set(this.direction);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  85 */       this.vworldXfrm.transform(this.direction, this.xformDirection);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double intersectEllipse(double paramDouble1, double paramDouble2) {
/* 196 */     Vector3f vector3f1 = this.direction;
/*     */     
/* 198 */     Vector3f vector3f2 = this.sourceToCenterEar;
/*     */     
/* 200 */     if (vector3f1 == null || vector3f2 == null)
/*     */     {
/*     */       
/* 203 */       return -1.0D;
/*     */     }
/*     */ 
/*     */     
/* 207 */     double d1 = (vector3f2.dot(vector3f1) / vector3f2.length() * vector3f1.length());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     double d2 = (float)Math.acos(d1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     double d3 = paramDouble2 + paramDouble1;
/* 220 */     double d4 = Math.tan(d2);
/* 221 */     double d5 = 1.0D / (4.0D / d3 * d3 + d4 * d4 / paramDouble2 * paramDouble1);
/*     */ 
/*     */     
/* 224 */     double d6 = Math.sqrt(d5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     double d7 = d4 * d6;
/*     */ 
/*     */     
/* 236 */     double d8 = d7 * d7;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 241 */     float f = (float)Math.sqrt(d5 + d8);
/*     */ 
/*     */     
/* 244 */     return f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float findFactor(double paramDouble, double[] paramArrayOfDouble1, float[] paramArrayOfFloat1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat2) {
/* 280 */     if (paramArrayOfDouble2 == null || paramArrayOfFloat2 == null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 285 */       return findFactor(paramDouble, paramArrayOfDouble1, paramArrayOfFloat1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 292 */     if (paramArrayOfDouble1 == null || paramArrayOfFloat1 == null)
/*     */     {
/*     */       
/* 295 */       return -1.0F;
/*     */     }
/*     */     
/* 298 */     int k = paramArrayOfDouble1.length;
/* 299 */     if (k < 2)
/*     */     {
/*     */       
/* 302 */       return -1.0F;
/*     */     }
/* 304 */     int m = k - 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 312 */     if (paramDouble >= paramArrayOfDouble1[m])
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       return paramArrayOfFloat1[m];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 329 */     if (paramDouble <= paramArrayOfDouble2[0])
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 336 */       return paramArrayOfFloat2[0];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 344 */     double[] arrayOfDouble = new double[k];
/* 345 */     float[] arrayOfFloat = new float[k];
/* 346 */     boolean[] arrayOfBoolean = new boolean[k];
/*     */     byte b;
/* 348 */     for (b = 0; b < k; b++)
/* 349 */       arrayOfBoolean[b] = false; 
/* 350 */     b = 0;
/* 351 */     int n = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 366 */     int i = 0;
/* 367 */     int j = m;
/*     */ 
/*     */ 
/*     */     
/* 371 */     while (i < j - 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 380 */       if (!arrayOfBoolean[i]) {
/* 381 */         arrayOfDouble[i] = intersectEllipse(paramArrayOfDouble1[i], paramArrayOfDouble2[i]);
/*     */ 
/*     */         
/* 384 */         if (arrayOfDouble[i] >= 0.0D) {
/* 385 */           arrayOfBoolean[i] = true;
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 391 */           arrayOfDouble[i] = (paramArrayOfDouble2[i] + paramArrayOfDouble1[i]) * 0.5D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 399 */           arrayOfBoolean[i] = true;
/*     */         } 
/*     */       } 
/*     */       
/* 403 */       if (!arrayOfBoolean[j]) {
/* 404 */         arrayOfDouble[j] = intersectEllipse(paramArrayOfDouble1[j], paramArrayOfDouble2[j]);
/*     */ 
/*     */         
/* 407 */         if (arrayOfDouble[j] >= 0.0D) {
/* 408 */           arrayOfBoolean[j] = true;
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 414 */           arrayOfDouble[j] = (paramArrayOfDouble2[j] + paramArrayOfDouble1[j]) * 0.5D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 422 */           arrayOfBoolean[j] = true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 431 */       if (arrayOfDouble[i] >= paramDouble) {
/* 432 */         if (!i || paramDouble < arrayOfDouble[i]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 445 */         b = 1;
/* 446 */         n = i;
/*     */         break;
/*     */       } 
/* 449 */       if (arrayOfDouble[j] <= paramDouble) {
/* 450 */         if (j == m || paramDouble > arrayOfDouble[j]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 463 */         b = 1;
/* 464 */         n = j;
/*     */         
/*     */         break;
/*     */       } 
/* 468 */       if (paramDouble > arrayOfDouble[i] && paramDouble < arrayOfDouble[j]) {
/*     */         
/* 470 */         int i1 = i + (j - i) / 2;
/* 471 */         if (paramDouble <= arrayOfDouble[i1]) {
/*     */           
/* 473 */           j = i1; continue;
/*     */         } 
/* 475 */         i = i1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 487 */     if (b != 0 && n >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 498 */       double d = (arrayOfDouble[n] - paramArrayOfDouble2[n]) / (paramArrayOfDouble1[n] - paramArrayOfDouble2[n]) * (paramArrayOfFloat1[n] - paramArrayOfFloat2[n]) + paramArrayOfFloat2[n];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 509 */       return (float)d;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 519 */     double d2 = 1.0D;
/* 520 */     double d3 = 0.0D;
/* 521 */     d2 = (arrayOfDouble[j] - paramArrayOfDouble2[j]) / (paramArrayOfDouble1[j] - paramArrayOfDouble2[j]) * (paramArrayOfFloat1[j] - paramArrayOfFloat2[j]) + paramArrayOfFloat2[j];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 537 */     d3 = (arrayOfDouble[i] - paramArrayOfDouble2[i]) / (paramArrayOfDouble1[i] - paramArrayOfDouble2[i]) * (paramArrayOfFloat1[i] - paramArrayOfFloat2[i]) + paramArrayOfFloat2[i];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 572 */     double d1 = (paramDouble - arrayOfDouble[i]) / (arrayOfDouble[j] - arrayOfDouble[i]) * (d2 - d3) + arrayOfFloat[i];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 580 */     return (float)d1;
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
/*     */   float calculateDistanceAttenuation(float paramFloat) {
/* 593 */     float f = findFactor(paramFloat, this.attenuationDistance, this.attenuationGain, this.backAttenuationDistance, this.backAttenuationGain);
/*     */ 
/*     */     
/* 596 */     if (f < 0.0F) {
/* 597 */       return 1.0F;
/*     */     }
/* 599 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float calculateAngularGain() {
/* 608 */     float f1 = findAngularOffset();
/* 609 */     float f2 = findFactor(f1, this.angularDistance, this.angularGain);
/* 610 */     if (f2 < 0.0F) {
/* 611 */       return 1.0F;
/*     */     }
/* 613 */     return f2;
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
/*     */   float findAngularOffset() {
/* 632 */     Vector3f vector3f1 = new Vector3f();
/* 633 */     Vector3f vector3f2 = new Vector3f();
/* 634 */     Point3f point3f1 = this.positions[this.currentIndex];
/* 635 */     Point3f point3f2 = this.centerEars[this.currentIndex];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 643 */     vector3f1.x = point3f2.x - point3f1.x;
/* 644 */     vector3f1.y = point3f2.y - point3f1.y;
/* 645 */     vector3f1.z = point3f2.z - point3f1.z;
/* 646 */     vector3f1.normalize();
/* 647 */     vector3f2.normalize(this.direction);
/* 648 */     float f = vector3f1.dot(vector3f2);
/* 649 */     return (float)Math.acos(f);
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
/*     */   void calculateFilter(float paramFloat, AuralParameters paramAuralParameters) {
/* 674 */     float f1 = 44100.0F;
/* 675 */     float f2 = 44100.0F;
/* 676 */     int i = paramAuralParameters.getDistanceFilterLength();
/* 677 */     int j = paramAuralParameters.getDistanceFilterType();
/*     */     
/* 679 */     boolean bool1 = false;
/* 680 */     boolean bool2 = false;
/* 681 */     if (j == -1 && i > 0) {
/* 682 */       double[] arrayOfDouble = new double[i];
/* 683 */       float[] arrayOfFloat = new float[i];
/* 684 */       paramAuralParameters.getDistanceFilter(arrayOfDouble, arrayOfFloat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 693 */       float f = findAngularOffset();
/* 694 */       f1 = findFactor(f, this.angularDistance, this.angularFilterCutoff);
/*     */       
/* 696 */       if (f1 < 0.0F) {
/* 697 */         bool1 = false;
/*     */       } else {
/* 699 */         bool1 = true;
/*     */       } 
/*     */     } else {
/* 702 */       bool1 = false;
/* 703 */       f1 = -1.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 710 */     i = this.angularDistance.length;
/* 711 */     j = this.angularFilterType;
/* 712 */     if (j != -1 && i > 0) {
/* 713 */       f2 = findFactor(paramFloat, this.angularDistance, this.angularFilterCutoff);
/*     */       
/* 715 */       if (f2 < 0.0F) {
/* 716 */         bool2 = false;
/*     */       } else {
/* 718 */         bool2 = true;
/*     */       } 
/*     */     } else {
/* 721 */       bool2 = false;
/* 722 */       f2 = -1.0F;
/*     */     } 
/*     */     
/* 725 */     this.filterFlag = (bool1 || bool2);
/* 726 */     if (f1 < 0.0F) {
/* 727 */       this.filterFreq = f2;
/* 728 */     } else if (f2 < 0.0F) {
/* 729 */       this.filterFreq = f1;
/*     */     } else {
/* 731 */       this.filterFreq = Math.min(f1, f2);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\audioengines\javasound\JSDirectionalSample.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */