/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point2f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class AuralAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*  27 */   float attributeGain = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   float rolloff = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final float SPEED_OF_SOUND = 0.344F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   float reflectionCoefficient = 0.0F;
/*  66 */   float reverbCoefficient = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   float reflectionDelay = 20.0F;
/*  76 */   float reverbDelay = 40.0F;
/*  77 */   Bounds reverbBounds = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   float decayTime = 1000.0F;
/*  84 */   float decayFilter = 5000.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   float diffusion = 1.0F;
/*  90 */   float density = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   int reverbOrder = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int NO_FILTERING = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int LOW_PASS = 1;
/*     */ 
/*     */ 
/*     */   
/* 116 */   int filterType = -1;
/* 117 */   float[] distance = null;
/* 118 */   float[] frequencyCutoff = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   float frequencyScaleFactor = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   float velocityScaleFactor = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean aaDirty = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   AuralAttributesRetained mirrorAa = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean debugFlag = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean internalErrors = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void debugPrint(String paramString) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAttributeGain(float paramFloat) {
/* 208 */     this.attributeGain = paramFloat;
/* 209 */     this.aaDirty = true;
/* 210 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   float getAttributeGain() { return this.attributeGain; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setRolloff(float paramFloat) {
/* 225 */     this.rolloff = paramFloat;
/* 226 */     this.aaDirty = true;
/* 227 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   float getRolloff() { return this.rolloff; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setReflectionCoefficient(float paramFloat) {
/* 243 */     this.reflectionCoefficient = paramFloat;
/* 244 */     this.aaDirty = true;
/* 245 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   float getReflectionCoefficient() { return this.reflectionCoefficient; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setReflectionDelay(float paramFloat) {
/* 262 */     this.reflectionDelay = paramFloat;
/* 263 */     this.aaDirty = true;
/* 264 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   float getReflectionDelay() { return this.reflectionDelay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setReverbCoefficient(float paramFloat) {
/* 280 */     this.reverbCoefficient = paramFloat;
/* 281 */     this.aaDirty = true;
/* 282 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   float getReverbCoefficient() { return this.reverbCoefficient; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setReverbDelay(float paramFloat) {
/* 298 */     this.reverbDelay = paramFloat;
/* 299 */     this.aaDirty = true;
/* 300 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   float getReverbDelay() { return this.reverbDelay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDecayTime(float paramFloat) {
/* 314 */     this.decayTime = paramFloat;
/* 315 */     this.aaDirty = true;
/* 316 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   float getDecayTime() { return this.decayTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDecayFilter(float paramFloat) {
/* 331 */     this.decayFilter = paramFloat;
/* 332 */     this.aaDirty = true;
/* 333 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   float getDecayFilter() { return this.decayFilter; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDiffusion(float paramFloat) {
/* 349 */     this.diffusion = paramFloat;
/* 350 */     this.aaDirty = true;
/* 351 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   float getDiffusion() { return this.diffusion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDensity(float paramFloat) {
/* 367 */     this.density = paramFloat;
/* 368 */     this.aaDirty = true;
/* 369 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   float getDensity() { return this.density; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setReverbBounds(Bounds paramBounds) {
/* 386 */     this.reverbBounds = paramBounds;
/* 387 */     this.aaDirty = true;
/* 388 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   Bounds getReverbBounds() { return this.reverbBounds; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setReverbOrder(int paramInt) {
/* 404 */     this.reverbOrder = paramInt;
/* 405 */     this.aaDirty = true;
/* 406 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 413 */   int getReverbOrder() { return this.reverbOrder; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDistanceFilter(Point2f[] paramArrayOfPoint2f) {
/* 421 */     if (paramArrayOfPoint2f == null) {
/* 422 */       this.filterType = -1;
/*     */       return;
/*     */     } 
/* 425 */     int i = paramArrayOfPoint2f.length;
/* 426 */     if (i == 0) {
/* 427 */       this.filterType = -1;
/*     */       return;
/*     */     } 
/* 430 */     this.filterType = 1;
/*     */     
/* 432 */     if (this.distance == null || (this.distance != null && this.distance.length != i)) {
/*     */       
/* 434 */       this.distance = new float[i];
/* 435 */       this.frequencyCutoff = new float[i];
/*     */     } 
/* 437 */     for (byte b = 0; b < i; b++) {
/* 438 */       this.distance[b] = (paramArrayOfPoint2f[b]).x;
/* 439 */       this.frequencyCutoff[b] = (paramArrayOfPoint2f[b]).y;
/*     */     } 
/* 441 */     this.aaDirty = true;
/* 442 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDistanceFilter(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 451 */     if (paramArrayOfFloat1 == null || paramArrayOfFloat2 == null) {
/* 452 */       this.filterType = -1;
/*     */       return;
/*     */     } 
/* 455 */     int i = paramArrayOfFloat1.length;
/* 456 */     int j = paramArrayOfFloat2.length;
/* 457 */     if (i == 0 || j == 0) {
/* 458 */       this.filterType = -1;
/*     */       
/*     */       return;
/*     */     } 
/* 462 */     if (this.distance == null || (this.distance != null && this.distance.length != j)) {
/*     */ 
/*     */       
/* 465 */       this.distance = new float[i];
/* 466 */       this.frequencyCutoff = new float[i];
/*     */     } 
/* 468 */     this.filterType = 1;
/*     */     
/* 470 */     System.arraycopy(paramArrayOfFloat1, 0, this.distance, 0, i);
/*     */     
/* 472 */     if (i <= j) {
/* 473 */       System.arraycopy(paramArrayOfFloat2, 0, this.frequencyCutoff, 0, i);
/*     */     } else {
/*     */       
/* 476 */       System.arraycopy(paramArrayOfFloat2, 0, this.frequencyCutoff, 0, j);
/*     */ 
/*     */       
/* 479 */       for (int k = j; k < i; k++) {
/* 480 */         this.frequencyCutoff[k] = paramArrayOfFloat2[j - 1];
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
/* 492 */     this.aaDirty = true;
/* 493 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getDistanceFilterLength() {
/* 501 */     if (this.distance == null) {
/* 502 */       return 0;
/*     */     }
/* 504 */     return this.distance.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getDistanceFilter(Point2f[] paramArrayOfPoint2f) {
/* 514 */     if (paramArrayOfPoint2f == null)
/*     */       return; 
/* 516 */     if (this.distance == null || this.frequencyCutoff == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 521 */     int i = this.distance.length;
/*     */ 
/*     */     
/* 524 */     if (i > paramArrayOfPoint2f.length)
/* 525 */       i = paramArrayOfPoint2f.length; 
/* 526 */     for (byte b = 0; b < i; b++) {
/* 527 */       (paramArrayOfPoint2f[b]).x = this.distance[b];
/* 528 */       if (this.filterType == -1) {
/* 529 */         (paramArrayOfPoint2f[b]).y = -1.0F;
/* 530 */       } else if (this.filterType == 1) {
/* 531 */         (paramArrayOfPoint2f[b]).y = this.frequencyCutoff[b];
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
/*     */   void getDistanceFilter(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 544 */     if (paramArrayOfFloat1 == null || paramArrayOfFloat2 == null)
/*     */       return; 
/* 546 */     if (this.distance == null || this.frequencyCutoff == null)
/*     */       return; 
/* 548 */     int i = this.distance.length;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 553 */     if (paramArrayOfFloat1.length < i)
/*     */     {
/* 555 */       i = paramArrayOfFloat1.length; } 
/* 556 */     System.arraycopy(this.distance, 0, paramArrayOfFloat1, 0, i);
/*     */ 
/*     */     
/* 559 */     int j = this.frequencyCutoff.length;
/* 560 */     if (paramArrayOfFloat2.length < j)
/*     */     {
/* 562 */       j = paramArrayOfFloat2.length; } 
/* 563 */     if (this.filterType == -1)
/* 564 */       for (byte b = 0; b < j; b++) {
/* 565 */         paramArrayOfFloat2[b] = -1.0F;
/*     */       } 
/* 567 */     if (this.filterType == 1) {
/* 568 */       System.arraycopy(this.frequencyCutoff, 0, paramArrayOfFloat2, 0, j);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFrequencyScaleFactor(float paramFloat) {
/* 579 */     this.frequencyScaleFactor = paramFloat;
/* 580 */     this.aaDirty = true;
/* 581 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 588 */   float getFrequencyScaleFactor() { return this.frequencyScaleFactor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setVelocityScaleFactor(float paramFloat) {
/* 596 */     this.velocityScaleFactor = paramFloat;
/* 597 */     this.aaDirty = true;
/* 598 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 605 */   float getVelocityScaleFactor() { return this.velocityScaleFactor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void reset(AuralAttributesRetained paramAuralAttributesRetained) {
/* 611 */     this.attributeGain = paramAuralAttributesRetained.attributeGain;
/* 612 */     this.rolloff = paramAuralAttributesRetained.rolloff;
/* 613 */     this.reflectionCoefficient = paramAuralAttributesRetained.reflectionCoefficient;
/* 614 */     this.reverbCoefficient = paramAuralAttributesRetained.reverbCoefficient;
/* 615 */     this.reflectionDelay = paramAuralAttributesRetained.reflectionDelay;
/* 616 */     this.reverbDelay = paramAuralAttributesRetained.reverbDelay;
/* 617 */     this.reverbBounds = paramAuralAttributesRetained.reverbBounds;
/* 618 */     this.reverbOrder = paramAuralAttributesRetained.reverbOrder;
/* 619 */     this.decayTime = paramAuralAttributesRetained.decayTime;
/* 620 */     this.decayFilter = paramAuralAttributesRetained.decayFilter;
/* 621 */     this.diffusion = paramAuralAttributesRetained.diffusion;
/* 622 */     this.density = paramAuralAttributesRetained.density;
/* 623 */     this.frequencyScaleFactor = paramAuralAttributesRetained.frequencyScaleFactor;
/* 624 */     this.velocityScaleFactor = paramAuralAttributesRetained.velocityScaleFactor;
/*     */     
/* 626 */     if (paramAuralAttributesRetained.distance != null) {
/* 627 */       this.distance = new float[paramAuralAttributesRetained.distance.length];
/*     */ 
/*     */       
/* 630 */       System.arraycopy(paramAuralAttributesRetained.distance, 0, this.distance, 0, this.distance.length);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 635 */     if (paramAuralAttributesRetained.frequencyCutoff != null) {
/* 636 */       this.frequencyCutoff = new float[paramAuralAttributesRetained.frequencyCutoff.length];
/*     */ 
/*     */       
/* 639 */       System.arraycopy(paramAuralAttributesRetained.frequencyCutoff, 0, this.frequencyCutoff, 0, this.frequencyCutoff.length);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 646 */     this.aaDirty = false;
/* 647 */     paramAuralAttributesRetained.aaDirty = false;
/*     */   }
/*     */ 
/*     */   
/* 651 */   void update(AuralAttributesRetained paramAuralAttributesRetained) { reset(paramAuralAttributesRetained); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\AuralAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */