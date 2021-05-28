/*      */ package com.sun.j3d.audioengines.javasound;
/*      */ 
/*      */ import com.sun.j3d.audioengines.AuralParameters;
/*      */ import javax.media.j3d.PhysicalBody;
/*      */ import javax.media.j3d.Transform3D;
/*      */ import javax.media.j3d.View;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class JSPositionalSample
/*      */   extends JSSample
/*      */ {
/*   67 */   float leftGain = 1.0F;
/*   68 */   float rightGain = 1.0F;
/*   69 */   int leftDelay = 0;
/*   70 */   int rightDelay = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final boolean dopplerFlag = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   82 */   int secondIndex = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   93 */   int reverbIndex = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   99 */   Point3f xformLeftEar = new Point3f(-0.09F, -0.03F, 0.095F);
/*  100 */   Point3f xformRightEar = new Point3f(0.09F, -0.03F, 0.095F);
/*      */   
/*  102 */   Vector3f xformHeadZAxis = new Vector3f(0.0F, 0.0F, -1.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   Vector3f sourceToCenterEar = new Vector3f();
/*  108 */   Vector3f sourceToRightEar = new Vector3f();
/*  109 */   Vector3f sourceToLeftEar = new Vector3f();
/*      */   
/*      */   boolean averageDistances = false;
/*  112 */   long deltaTime = 0L;
/*  113 */   double sourcePositionChange = -1.0D;
/*  114 */   double headPositionChange = -1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  121 */   static int MAX_DISTANCES = 4;
/*  122 */   int numDistances = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   long[] times = new long[MAX_DISTANCES];
/*  128 */   Point3f[] positions = new Point3f[MAX_DISTANCES];
/*  129 */   Point3f[] centerEars = new Point3f[MAX_DISTANCES];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   int firstIndex = 0;
/*  135 */   int lastIndex = 0;
/*  136 */   int currentIndex = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   double lastRequestedDopplerRateRatio = -1.0D;
/*  147 */   double lastActualDopplerRateRatio = -1.0D;
/*  148 */   static double maxRatio = 256.0D;
/*      */ 
/*      */ 
/*      */   
/*  152 */   static int TOWARDS = 1;
/*  153 */   static int NO_CHANGE = 0;
/*  154 */   static int AWAY = -1;
/*      */ 
/*      */   
/*      */   boolean filterFlag = false;
/*      */ 
/*      */   
/*  160 */   float filterFreq = -1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSPositionalSample() {
/*  170 */     for (byte b = 0; b < MAX_DISTANCES; b++) {
/*  171 */       this.positions[b] = new Point3f();
/*  172 */       this.centerEars[b] = new Point3f(0.09F, -0.03F, 0.095F);
/*      */     } 
/*  174 */     clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  183 */   boolean getFilterFlag() { return this.filterFlag; }
/*      */ 
/*      */   
/*  186 */   float getFilterFreq() { return this.filterFreq; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  197 */     super.clear();
/*  198 */     this.leftGain = 1.0F;
/*  199 */     this.rightGain = 1.0F;
/*  200 */     this.leftDelay = 0;
/*  201 */     this.rightDelay = 0;
/*  202 */     this.xformLeftEar.set(-0.09F, -0.03F, 0.095F);
/*  203 */     this.xformRightEar.set(0.09F, -0.03F, 0.095F);
/*      */     
/*  205 */     this.xformHeadZAxis.set(0.0F, 0.0F, -1.0F);
/*  206 */     this.sourceToCenterEar.set(0.0F, 0.0F, 0.0F);
/*  207 */     this.sourceToRightEar.set(0.0F, 0.0F, 0.0F);
/*  208 */     this.sourceToLeftEar.set(0.0F, 0.0F, 0.0F);
/*  209 */     reset();
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
/*      */   void reset() {
/*  221 */     super.reset();
/*  222 */     this.averageDistances = false;
/*  223 */     this.deltaTime = 0L;
/*  224 */     this.sourcePositionChange = -1.0D;
/*  225 */     this.headPositionChange = -1.0D;
/*  226 */     this.rateRatio = 1.0F;
/*  227 */     this.numDistances = 0;
/*  228 */     this.averageDistances = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void incrementIndices() {
/*  235 */     int i = MAX_DISTANCES - 1;
/*  236 */     if (this.numDistances < i) {
/*  237 */       this.averageDistances = false;
/*  238 */       this.currentIndex = this.numDistances;
/*  239 */       this.lastIndex = this.currentIndex - 1;
/*  240 */       this.firstIndex = 0;
/*  241 */       this.numDistances++;
/*      */     }
/*  243 */     else if (this.numDistances == i) {
/*      */ 
/*      */       
/*  246 */       this.averageDistances = true;
/*  247 */       this.currentIndex = i;
/*  248 */       this.lastIndex = this.currentIndex - 1;
/*  249 */       this.firstIndex = 0;
/*  250 */       this.numDistances++;
/*      */     }
/*  252 */     else if (this.numDistances > i) {
/*      */       
/*  254 */       this.averageDistances = true;
/*  255 */       this.currentIndex++;
/*  256 */       this.lastIndex++;
/*  257 */       this.firstIndex++;
/*  258 */       this.currentIndex %= MAX_DISTANCES;
/*  259 */       this.lastIndex %= MAX_DISTANCES;
/*  260 */       this.firstIndex %= MAX_DISTANCES;
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
/*      */   void setXformedPosition() {
/*  272 */     Point3f point3f = new Point3f();
/*      */ 
/*      */ 
/*      */     
/*  276 */     if (getVWrldXfrmFlag()) {
/*      */ 
/*      */       
/*  279 */       this.vworldXfrm.transform(this.position, point3f);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  284 */       point3f.set(this.position);
/*      */     } 
/*      */     
/*  287 */     if (point3f.x == (this.positions[this.currentIndex]).x && point3f.y == (this.positions[this.currentIndex]).y && point3f.z == (this.positions[this.currentIndex]).z) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  295 */     incrementIndices();
/*      */     
/*  297 */     this.times[this.currentIndex] = System.currentTimeMillis();
/*  298 */     this.positions[this.currentIndex].set(point3f);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  308 */     if (this.numDistances > 1) {
/*  309 */       this.centerEars[this.currentIndex].set(this.centerEars[this.lastIndex]);
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
/*      */   float calculateDoppler(AuralParameters paramAuralParameters) {
/*  365 */     double d1 = 1.0D;
/*  366 */     double d2 = 0.0D;
/*  367 */     double d3 = 0.0D;
/*  368 */     double d4 = 0.0D;
/*  369 */     double d5 = 0.0D;
/*  370 */     paramAuralParameters; float f1 = 0.344F;
/*  371 */     double d6 = 1.0D;
/*  372 */     double d7 = 1.0D;
/*  373 */     int i = NO_CHANGE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  379 */     float f2 = 0.0F;
/*  380 */     float f3 = 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  386 */     if (!this.averageDistances) {
/*      */ 
/*      */       
/*  389 */       debugPrint("JSPositionalSample.calculateDoppler - not enough distance data collected, dopplerRatio set to zero");
/*      */ 
/*      */ 
/*      */       
/*  393 */       return 0.0F;
/*      */     } 
/*      */     
/*  396 */     Point3f point3f1 = this.positions[this.lastIndex];
/*  397 */     Point3f point3f2 = this.centerEars[this.lastIndex];
/*  398 */     Point3f point3f3 = this.positions[this.currentIndex];
/*  399 */     Point3f point3f4 = this.centerEars[this.currentIndex];
/*  400 */     d4 = point3f3.distance(point3f4);
/*  401 */     d5 = point3f1.distance(point3f2);
/*      */     
/*  403 */     debugPrint("JSPositionalSample.calculateDoppler - distances: current,last = " + d4 + ", " + d5);
/*      */ 
/*      */     
/*  406 */     debugPrint("                                      current position = " + point3f3.x + ", " + point3f3.y + ", " + point3f3.z);
/*      */ 
/*      */ 
/*      */     
/*  410 */     debugPrint("                                      current ear = " + point3f4.x + ", " + point3f4.y + ", " + point3f4.z);
/*      */ 
/*      */ 
/*      */     
/*  414 */     debugPrint("                                      last position = " + point3f1.x + ", " + point3f1.y + ", " + point3f1.z);
/*      */ 
/*      */ 
/*      */     
/*  418 */     debugPrint("                                      last ear = " + point3f2.x + ", " + point3f2.y + ", " + point3f2.z);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  423 */     if (d4 == d5) {
/*      */ 
/*      */       
/*  426 */       debugPrint("JSPositionalSample.calculateDoppler - distance diff = 0, dopplerRatio set to zero");
/*      */ 
/*      */       
/*  429 */       return 0.0F;
/*      */     } 
/*      */     
/*  432 */     this.deltaTime = this.times[this.currentIndex] - this.times[this.firstIndex];
/*  433 */     for (byte b = 0; b < MAX_DISTANCES - 1; b++) {
/*  434 */       f2 += this.positions[b + true].distance(this.positions[b]);
/*  435 */       f3 += this.centerEars[b + true].distance(this.centerEars[b]);
/*      */     } 
/*  437 */     f2 /= (MAX_DISTANCES - 1);
/*  438 */     f3 /= (MAX_DISTANCES - 1);
/*  439 */     d3 = (f2 / (float)this.deltaTime);
/*  440 */     d2 = (f3 / (float)this.deltaTime);
/*      */     
/*  442 */     debugPrint("                                      delta time = " + this.deltaTime);
/*      */     
/*  444 */     debugPrint("                                      soundPosition delta = " + point3f3.distance(point3f1));
/*      */ 
/*      */     
/*  447 */     debugPrint("                                      soundVelocity = " + d3);
/*      */     
/*  449 */     debugPrint("                                      headPosition delta = " + point3f4.distance(point3f2));
/*      */ 
/*      */     
/*  452 */     debugPrint("                                      headVelocity = " + d2);
/*      */ 
/*      */     
/*  455 */     if (paramAuralParameters != null) {
/*      */       
/*  457 */       float f4 = paramAuralParameters.rolloff;
/*  458 */       float f5 = paramAuralParameters.velocityScaleFactor;
/*  459 */       if (f4 != 1.0F) {
/*  460 */         f1 *= f4;
/*      */         
/*  462 */         debugPrint("                                      attrib rollof = " + f4);
/*      */       } 
/*      */       
/*  465 */       if (f5 != 1.0F) {
/*  466 */         d3 *= f5;
/*  467 */         d2 *= f5;
/*      */         
/*  469 */         debugPrint("                                      attrib velocity scale factor = " + f5);
/*      */ 
/*      */         
/*  472 */         debugPrint("                                      new soundVelocity = " + d3);
/*      */         
/*  474 */         debugPrint("                                      new headVelocity = " + d2);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  479 */     if (d4 < d5) {
/*      */ 
/*      */       
/*  482 */       debugPrint("                                      moving towards...");
/*      */       
/*  484 */       i = TOWARDS;
/*  485 */       d6 = f1 + d2;
/*  486 */       d7 = f1 - d3;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  492 */       debugPrint("                                      moving away...");
/*      */       
/*  494 */       i = AWAY;
/*  495 */       d6 = f1 - d2;
/*  496 */       d7 = f1 + d3;
/*      */     } 
/*  498 */     if (d6 <= 0.0D) {
/*      */       
/*  500 */       debugPrint("JSPositionalSample.calculateDoppler: BOOM!! - velocity of head > speed of sound");
/*      */       
/*  502 */       return -1.0F;
/*      */     } 
/*  504 */     if (d7 <= 0.0D) {
/*      */       
/*  506 */       debugPrint("JSPositionalSample.calculateDoppler: BOOM!! - velocity of sound source negative");
/*      */       
/*  508 */       return -1.0F;
/*      */     } 
/*      */ 
/*      */     
/*  512 */     debugPrint("JSPositionalSample.calculateDoppler: numerator = " + d6 + ", denominator = " + d7);
/*      */ 
/*      */     
/*  515 */     d1 = d6 / d7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  529 */     return (float)d1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateEar(int paramInt, View paramView) {
/*  536 */     Point3f point3f = new Point3f();
/*  537 */     if (!calculateNewEar(paramInt, paramView, point3f)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  543 */     if (point3f.x == (this.centerEars[this.currentIndex]).x && point3f.y == (this.centerEars[this.currentIndex]).y && point3f.z == (this.centerEars[this.currentIndex]).z) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  551 */     incrementIndices();
/*  552 */     this.times[this.currentIndex] = System.currentTimeMillis();
/*  553 */     this.centerEars[this.currentIndex].set(point3f);
/*      */ 
/*      */     
/*  556 */     if (this.numDistances > 1) {
/*  557 */       this.positions[this.currentIndex].set(this.positions[this.lastIndex]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean calculateNewEar(int paramInt, View paramView, Point3f paramPoint3f) {
/*  564 */     Point3d point3d = new Point3d();
/*      */ 
/*      */ 
/*      */     
/*  568 */     boolean bool = false;
/*  569 */     if (!bool && 
/*  570 */       paramView != null) {
/*  571 */       PhysicalBody physicalBody = paramView.getPhysicalBody();
/*  572 */       if (physicalBody != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  577 */         Transform3D transform3D = new Transform3D();
/*  578 */         paramView.getUserHeadToVworld(transform3D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  595 */         physicalBody.getLeftEarPosition(point3d);
/*  596 */         this.xformLeftEar.x = (float)point3d.x;
/*  597 */         this.xformLeftEar.y = (float)point3d.y;
/*  598 */         this.xformLeftEar.z = (float)point3d.z;
/*  599 */         physicalBody.getRightEarPosition(point3d);
/*  600 */         this.xformRightEar.x = (float)point3d.x;
/*  601 */         this.xformRightEar.y = (float)point3d.y;
/*  602 */         this.xformRightEar.z = (float)point3d.z;
/*  603 */         transform3D.transform(this.xformRightEar);
/*  604 */         transform3D.transform(this.xformLeftEar);
/*      */         
/*  606 */         this.xformHeadZAxis.set(0.0F, 0.0F, -1.0F);
/*  607 */         transform3D.transform(this.xformHeadZAxis);
/*      */ 
/*      */ 
/*      */         
/*  611 */         this.xformLeftEar.x += (this.xformRightEar.x - this.xformLeftEar.x) * 0.5F;
/*      */         
/*  613 */         this.xformLeftEar.y += (this.xformRightEar.y - this.xformLeftEar.y) * 0.5F;
/*      */         
/*  615 */         this.xformLeftEar.z += (this.xformRightEar.z - this.xformLeftEar.z) * 0.5F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  626 */         bool = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  634 */     if (!bool);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  639 */     return bool;
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
/*      */   public void render(int paramInt, View paramView, AuralParameters paramAuralParameters) {
/*  651 */     updateEar(paramInt, paramView);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  667 */     float f = 1.0F;
/*  668 */     if (paramAuralParameters != null) {
/*  669 */       float f1 = paramAuralParameters.rolloff;
/*  670 */       float f2 = paramAuralParameters.frequencyScaleFactor;
/*  671 */       float f3 = paramAuralParameters.velocityScaleFactor;
/*      */       
/*  673 */       debugPrint("JSPositionalSample: attribs NOT null");
/*  674 */       if (f1 > 0.0F)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  680 */         if (f2 > 0.0F)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  687 */           if (f3 > 0.0F)
/*      */           {
/*  689 */             debugPrint("    velocityScaleFactor = " + f3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  696 */             f = calculateDoppler(paramAuralParameters);
/*      */             
/*  698 */             if (f != 0.0F)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  706 */               if (f != -1.0F)
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  716 */                 if (f > 0.0F)
/*      */                 {
/*  718 */                   this.rateRatio = f * f2 * getRateScaleFactor();
/*      */ 
/*      */                 
/*      */                 }
/*      */ 
/*      */               
/*      */               }
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*      */             
/*  735 */             this.rateRatio = f2 * getRateScaleFactor();
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  744 */       debugPrint("JSPositionalSample: attribs null");
/*  745 */       this.rateRatio = 1.0F;
/*      */     } 
/*      */     
/*  748 */     panSample(paramAuralParameters);
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
/*  764 */   float calculateAngularGain() { return 1.0F; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void calculateFilter(float paramFloat, AuralParameters paramAuralParameters) {
/*  786 */     float f1 = 44100.0F;
/*  787 */     float f2 = 44100.0F;
/*  788 */     int i = paramAuralParameters.getDistanceFilterLength();
/*  789 */     int j = paramAuralParameters.getDistanceFilterType();
/*  790 */     boolean bool1 = false;
/*  791 */     boolean bool2 = false;
/*  792 */     if (j != -1 && i > 0) {
/*  793 */       double[] arrayOfDouble = new double[i];
/*  794 */       float[] arrayOfFloat = new float[i];
/*  795 */       paramAuralParameters.getDistanceFilter(arrayOfDouble, arrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  802 */       f1 = findFactor(paramFloat, arrayOfDouble, arrayOfFloat);
/*      */       
/*  804 */       if (f1 < 0.0F) {
/*  805 */         bool1 = false;
/*      */       } else {
/*  807 */         bool1 = true;
/*      */       } 
/*      */     } else {
/*  810 */       bool1 = false;
/*  811 */       f1 = -1.0F;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  818 */     bool2 = false;
/*  819 */     f2 = -1.0F;
/*      */     
/*  821 */     this.filterFlag = (bool1 || bool2);
/*  822 */     this.filterFreq = f1;
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
/*      */   float findFactor(double paramDouble, double[] paramArrayOfDouble, float[] paramArrayOfFloat) {
/*  858 */     if (paramArrayOfDouble == null || paramArrayOfFloat == null)
/*      */     {
/*      */       
/*  861 */       return -1.0F;
/*      */     }
/*  863 */     int k = paramArrayOfDouble.length;
/*  864 */     if (k < 2)
/*      */     {
/*      */       
/*  867 */       return -1.0F;
/*      */     }
/*  869 */     int m = k - 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  874 */     if (paramDouble >= paramArrayOfDouble[m])
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  880 */       return paramArrayOfFloat[m];
/*      */     }
/*  882 */     if (paramDouble <= paramArrayOfDouble[0])
/*      */     {
/*      */ 
/*      */       
/*  886 */       return paramArrayOfFloat[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  893 */     int i = 0;
/*  894 */     int j = m;
/*      */ 
/*      */     
/*  897 */     while (i < j - 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  911 */       if (paramArrayOfDouble[i] >= paramDouble) {
/*  912 */         if (paramDouble < paramArrayOfDouble[i]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  924 */         return paramArrayOfFloat[i];
/*      */       } 
/*  926 */       if (paramArrayOfDouble[j] <= paramDouble) {
/*  927 */         if (paramDouble > paramArrayOfDouble[j]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  939 */         return paramArrayOfFloat[j];
/*      */       } 
/*  941 */       if (paramDouble > paramArrayOfDouble[i] && paramDouble < paramArrayOfDouble[j]) {
/*      */         
/*  943 */         int n = i + (j - i) / 2;
/*  944 */         if (paramDouble <= paramArrayOfDouble[n]) {
/*      */           
/*  946 */           j = n; continue;
/*      */         } 
/*  948 */         i = n;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  967 */     return (float)((paramDouble - paramArrayOfDouble[i]) / (paramArrayOfDouble[j] - paramArrayOfDouble[i])) * (paramArrayOfFloat[j] - paramArrayOfFloat[i]) + paramArrayOfFloat[i];
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
/*      */   float calculateDistanceAttenuation(float paramFloat) {
/*  985 */     float f = 1.0F;
/*  986 */     f = findFactor(paramFloat, this.attenuationDistance, this.attenuationGain);
/*      */     
/*  988 */     if (f >= 0.0D) {
/*  989 */       return f;
/*      */     }
/*  991 */     return 1.0F;
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
/*      */   void panSample(AuralParameters paramAuralParameters) {
/*      */     float f4;
/* 1004 */     byte b = 1;
/* 1005 */     float f1 = 1.0F;
/* 1006 */     float f2 = 0.125F;
/* 1007 */     float f3 = f1 - f2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1018 */     float f6 = 1.0E-6F;
/* 1019 */     float f7 = 0.999999F;
/* 1020 */     float f8 = -f7;
/* 1021 */     float f9 = 1.5707964F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1026 */     float f10 = 0.0F;
/* 1027 */     float f11 = 0.0F;
/* 1028 */     float f12 = 0.0F;
/* 1029 */     float f13 = 0.0F;
/* 1030 */     float f14 = 0.18F;
/* 1031 */     float f15 = 0.0F;
/* 1032 */     float f16 = 0.0F;
/*      */     
/* 1034 */     float f17 = 0.0F;
/* 1035 */     float f18 = 0.0F;
/* 1036 */     float f19 = 0.0F;
/*      */ 
/*      */     
/* 1039 */     paramAuralParameters; float f20 = 0.344F;
/* 1040 */     paramAuralParameters; float f21 = 1.0F / 0.344F;
/*      */     
/* 1042 */     float f22 = 44.1F;
/*      */     
/* 1044 */     boolean bool1 = false;
/* 1045 */     boolean bool2 = false;
/*      */     
/* 1047 */     float f23 = 1.0F;
/* 1048 */     float f24 = this.gain;
/*      */     
/* 1050 */     Point3f point3f1 = new Point3f();
/* 1051 */     Point3f point3f2 = new Point3f();
/*      */ 
/*      */ 
/*      */     
/* 1055 */     Vector3f vector3f = new Vector3f();
/*      */ 
/*      */     
/* 1058 */     point3f1.set(this.positions[this.currentIndex]);
/* 1059 */     point3f2.set(this.centerEars[this.currentIndex]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1077 */     this.sourceToCenterEar.x = point3f2.x - point3f1.x;
/* 1078 */     this.sourceToCenterEar.y = point3f2.y - point3f1.y;
/* 1079 */     this.sourceToCenterEar.z = point3f2.z - point3f1.z;
/* 1080 */     this.sourceToRightEar.x = this.xformRightEar.x - point3f1.x;
/* 1081 */     this.sourceToRightEar.y = this.xformRightEar.y - point3f1.y;
/* 1082 */     this.sourceToRightEar.z = this.xformRightEar.z - point3f1.z;
/* 1083 */     this.sourceToLeftEar.x = this.xformLeftEar.x - point3f1.x;
/* 1084 */     this.sourceToLeftEar.y = this.xformLeftEar.y - point3f1.y;
/* 1085 */     this.sourceToLeftEar.z = this.xformLeftEar.z - point3f1.z;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1093 */     f10 = point3f1.distance(point3f2);
/* 1094 */     f12 = point3f1.distance(this.xformRightEar);
/* 1095 */     f13 = point3f1.distance(this.xformLeftEar);
/* 1096 */     f14 = this.xformRightEar.distance(this.xformLeftEar);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1101 */     f15 = f14 * 0.5F;
/*      */ 
/*      */     
/* 1104 */     f16 = f15 / f10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1122 */     double d = (this.sourceToCenterEar.dot(this.xformHeadZAxis) / this.sourceToCenterEar.length() * this.xformHeadZAxis.length());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1127 */     f17 = (float)Math.acos(d);
/*      */ 
/*      */ 
/*      */     
/* 1131 */     if (f17 > f9) {
/*      */ 
/*      */       
/* 1134 */       bool2 = true;
/* 1135 */       f17 = 3.1415927F - f17;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1140 */       bool2 = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1145 */     f19 = (float)Math.acos(f16);
/*      */ 
/*      */ 
/*      */     
/* 1149 */     bool1 = (f12 > f13) ? 0 : 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1154 */     if (bool1) {
/*      */ 
/*      */       
/* 1157 */       if (bool2) {
/* 1158 */         b = 4;
/*      */       } else {
/* 1160 */         b = 1;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1165 */     else if (bool2) {
/* 1166 */       b = 3;
/*      */     } else {
/* 1168 */       b = 2;
/*      */     } 
/* 1170 */     f18 = (float)Math.sin(f17);
/* 1171 */     if (f18 < 0.0D) f18 = -f18;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1180 */     float f25 = (float)Math.sqrt(f10 * f10 + (f15 * f15));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1186 */     if (bool1) {
/* 1187 */       f13 = f25 + f15 * (f9 + f17 - f19);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1194 */       f12 = f25 + f15 * (f9 + f17 - f19);
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
/* 1206 */     if (f18 < f16) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1211 */       if (bool1) {
/* 1212 */         f12 = f25 + f15 * (f9 - f17 - f19);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1219 */         f13 = f25 + f15 * (f9 - f17 - f19);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1229 */     else if (bool1) {
/*      */     
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1250 */     f22 = this.channel.rateInHz * 0.001F;
/* 1251 */     if (bool1) {
/* 1252 */       this.rightDelay = 0;
/* 1253 */       this.leftDelay = (int)((f13 - f12) * f21 * f22);
/*      */     }
/*      */     else {
/*      */       
/* 1257 */       this.leftDelay = 0;
/* 1258 */       this.rightDelay = (int)((f12 - f13) * f21 * f22);
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
/* 1271 */     point3f1.sub(point3f2);
/*      */     
/* 1273 */     point3f1.scale(1.0F / f10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1284 */     f23 = calculateDistanceAttenuation(f10);
/*      */     
/* 1286 */     f24 *= f23;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1294 */     f24 *= calculateAngularGain();
/*      */ 
/*      */ 
/*      */     
/* 1298 */     float f5 = point3f1.x / 2.0F;
/* 1299 */     if (f5 >= 0.0F) {
/* 1300 */       f4 = f3 * (0.5F - f5);
/*      */     } else {
/* 1302 */       f4 = f3 * (0.5F + f5);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1309 */     switch (b) {
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 4:
/* 1314 */         this.rightGain = f24 * (f1 - f4);
/* 1315 */         this.leftGain = f24 * (f2 + f4);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/* 1322 */         this.leftGain = f24 * (f1 - f4);
/* 1323 */         this.rightGain = f24 * (f2 + f4);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1332 */     calculateFilter(f10, paramAuralParameters);
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\audioengines\javasound\JSPositionalSample.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */