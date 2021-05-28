/*      */ package com.sun.j3d.internal;
/*      */ 
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
/*      */ public class Distance
/*      */ {
/*      */   static final double FUZZ = 1.0E-5D;
/*      */   
/*   77 */   private static final double DIST(double paramDouble) { return Math.abs(paramDouble); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   93 */   public static double rayToSegment(Point3d paramPoint3d1, Vector3d paramVector3d, Point3d paramPoint3d2, Point3d paramPoint3d3) { return rayToSegment(paramPoint3d1, paramVector3d, paramPoint3d2, paramPoint3d3, null, null, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double rayToSegment(Point3d paramPoint3d1, Vector3d paramVector3d, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, Point3d paramPoint3d5, double[] paramArrayOfDouble) {
/*  131 */     Vector3d vector3d1 = new Vector3d();
/*  132 */     vector3d1.sub(paramPoint3d1, paramPoint3d2);
/*  133 */     Vector3d vector3d2 = new Vector3d();
/*  134 */     vector3d2.sub(paramPoint3d3, paramPoint3d2);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  139 */     double d3 = paramVector3d.dot(paramVector3d);
/*  140 */     double d4 = -paramVector3d.dot(vector3d2);
/*  141 */     double d5 = vector3d2.dot(vector3d2);
/*  142 */     double d6 = paramVector3d.dot(vector3d1);
/*      */     
/*  144 */     double d8 = vector3d1.dot(vector3d1);
/*  145 */     double d9 = Math.abs(d3 * d5 - d4 * d4);
/*      */ 
/*      */ 
/*      */     
/*  149 */     if (d9 >= 1.0E-5D) {
/*      */       
/*  151 */       double d13 = -vector3d2.dot(vector3d1);
/*  152 */       double d11 = d4 * d13 - d5 * d6;
/*  153 */       double d12 = d4 * d6 - d3 * d13;
/*      */       
/*  155 */       if (d11 >= 0.0D) {
/*  156 */         if (d12 >= 0.0D) {
/*  157 */           if (d12 <= d9) {
/*      */             
/*  159 */             double d = 1.0D / d9;
/*  160 */             d11 *= d;
/*  161 */             d12 *= d;
/*  162 */             if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d11, paramVector3d, paramPoint3d1); 
/*  163 */             if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d12, vector3d2, paramPoint3d2); 
/*  164 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  165 */              return DIST(d11 * (d3 * d11 + d4 * d12 + 2.0D * d6) + d12 * (d4 * d11 + d5 * d12 + 2.0D * d13) + d8);
/*      */           } 
/*      */ 
/*      */           
/*  169 */           d12 = 1.0D;
/*  170 */           if (d6 >= 0.0D) {
/*  171 */             d11 = 0.0D;
/*  172 */             if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  173 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  174 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  175 */              return DIST(d5 + 2.0D * d13 + d8);
/*      */           } 
/*      */           
/*  178 */           d11 = -d6 / d3;
/*  179 */           if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d11, paramVector3d, paramPoint3d1); 
/*  180 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  181 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  182 */            return DIST((d6 + 2.0D * d4) * d11 + d5 + 2.0D * d13 + d8);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  187 */         d12 = 0.0D;
/*  188 */         if (d6 >= 0.0D) {
/*  189 */           d11 = 0.0D;
/*  190 */           if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  191 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  192 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  193 */            return DIST(d8);
/*      */         } 
/*      */         
/*  196 */         d11 = -d6 / d3;
/*  197 */         if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d11, paramVector3d, paramPoint3d1); 
/*  198 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  199 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  200 */          return DIST(d6 * d11 + d8);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  205 */       if (d12 <= 0.0D) {
/*  206 */         if (d6 < 0.0D) {
/*  207 */           d11 = -d6 / d3;
/*  208 */           d12 = 0.0D;
/*  209 */           if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d11, paramVector3d, paramPoint3d1); 
/*  210 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  211 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  212 */            return DIST(d6 * d11 + d8);
/*      */         } 
/*      */         
/*  215 */         d11 = 0.0D;
/*  216 */         if (d13 >= 0.0D) {
/*  217 */           d12 = 0.0D;
/*  218 */           if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  219 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  220 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  221 */            return DIST(d8);
/*      */         } 
/*  223 */         if (-d13 >= d5) {
/*  224 */           d12 = 1.0D;
/*  225 */           if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  226 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  227 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  228 */            return DIST(d5 + 2.0D * d13 + d8);
/*      */         } 
/*      */         
/*  231 */         d12 = -d13 / d5;
/*  232 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  233 */         if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d12, vector3d2, paramPoint3d2); 
/*  234 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  235 */          return DIST(d13 * d12 + d8);
/*      */       } 
/*      */ 
/*      */       
/*  239 */       if (d12 <= d9) {
/*  240 */         d11 = 0.0D;
/*  241 */         if (d13 >= 0.0D) {
/*  242 */           d12 = 0.0D;
/*  243 */           if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  244 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  245 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  246 */            return DIST(d8);
/*      */         } 
/*  248 */         if (-d13 >= d5) {
/*  249 */           d12 = 1.0D;
/*  250 */           if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  251 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  252 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  253 */            return DIST(d5 + 2.0D * d13 + d8);
/*      */         } 
/*      */         
/*  256 */         d12 = -d13 / d5;
/*  257 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  258 */         if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d12, vector3d2, paramPoint3d2); 
/*  259 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  260 */          return DIST(d13 * d12 + d8);
/*      */       } 
/*      */ 
/*      */       
/*  264 */       double d14 = d4 + d6;
/*  265 */       if (d14 < 0.0D) {
/*  266 */         d11 = -d14 / d3;
/*  267 */         d12 = 1.0D;
/*  268 */         if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d11, paramVector3d, paramPoint3d1); 
/*  269 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  270 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  271 */          return DIST(d14 * d11 + d5 + 2.0D * d13 + d8);
/*      */       } 
/*      */       
/*  274 */       d11 = 0.0D;
/*  275 */       if (d13 >= 0.0D) {
/*  276 */         d12 = 0.0D;
/*  277 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  278 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  279 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  280 */          return DIST(d8);
/*      */       } 
/*  282 */       if (-d13 >= d5) {
/*  283 */         d12 = 1.0D;
/*  284 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  285 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  286 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  287 */          return DIST(d5 + 2.0D * d13 + d8);
/*      */       } 
/*      */       
/*  290 */       d12 = -d13 / d5;
/*  291 */       if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  292 */       if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d12, vector3d2, paramPoint3d2); 
/*  293 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  294 */        return DIST(d13 * d12 + d8);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  302 */     if (d4 > 0.0D) {
/*      */       
/*  304 */       double d12 = 0.0D;
/*  305 */       if (d6 >= 0.0D) {
/*  306 */         double d = 0.0D;
/*  307 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  308 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  309 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d; paramArrayOfDouble[1] = d12; }
/*  310 */          return DIST(d8);
/*      */       } 
/*      */       
/*  313 */       double d11 = -d6 / d3;
/*  314 */       if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d11, paramVector3d, paramPoint3d1); 
/*  315 */       if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  316 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d11; paramArrayOfDouble[1] = d12; }
/*  317 */        return DIST(d6 * d11 + d8);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  322 */     double d7 = vector3d2.dot(vector3d1);
/*  323 */     double d2 = 1.0D;
/*  324 */     double d10 = d4 + d6;
/*  325 */     if (d10 >= 0.0D) {
/*  326 */       double d = 0.0D;
/*  327 */       if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d1); 
/*  328 */       if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  329 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d; paramArrayOfDouble[1] = d2; }
/*  330 */        return DIST(d5 + 2.0D * d7 + d8);
/*      */     } 
/*      */     
/*  333 */     double d1 = -d10 / d3;
/*  334 */     if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d1, paramVector3d, paramPoint3d1); 
/*  335 */     if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d3); 
/*  336 */     if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d1; paramArrayOfDouble[1] = d2; }
/*  337 */      return DIST(d10 * d1 + d5 + 2.0D * d7 + d8);
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
/*  356 */   public static double rayToRay(Point3d paramPoint3d1, Vector3d paramVector3d1, Point3d paramPoint3d2, Vector3d paramVector3d2) { return rayToRay(paramPoint3d1, paramVector3d1, paramPoint3d2, paramVector3d2, null, null, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double rayToRay(Point3d paramPoint3d1, Vector3d paramVector3d1, Point3d paramPoint3d2, Vector3d paramVector3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, double[] paramArrayOfDouble) {
/*  394 */     Vector3d vector3d = new Vector3d();
/*  395 */     vector3d.sub(paramPoint3d1, paramPoint3d2);
/*      */     
/*  397 */     double d3 = paramVector3d1.dot(paramVector3d1);
/*  398 */     double d4 = -paramVector3d1.dot(paramVector3d2);
/*  399 */     double d5 = paramVector3d2.dot(paramVector3d2);
/*  400 */     double d6 = paramVector3d1.dot(vector3d);
/*      */     
/*  402 */     double d7 = vector3d.dot(vector3d);
/*  403 */     double d8 = Math.abs(d3 * d5 - d4 * d4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  409 */     if (d8 >= 1.0E-5D) {
/*      */       
/*  411 */       double d11 = -paramVector3d2.dot(vector3d);
/*  412 */       double d9 = d4 * d11 - d5 * d6;
/*  413 */       double d10 = d4 * d6 - d3 * d11;
/*      */       
/*  415 */       if (d9 >= 0.0D) {
/*  416 */         if (d10 >= 0.0D) {
/*      */           
/*  418 */           double d = 1.0D / d8;
/*  419 */           d9 *= d;
/*  420 */           d10 *= d;
/*  421 */           if (paramPoint3d3 != null) paramPoint3d3.scaleAdd(d9, paramVector3d1, paramPoint3d1); 
/*  422 */           if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d10, paramVector3d2, paramPoint3d2); 
/*  423 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  424 */            return DIST(d9 * (d3 * d9 + d4 * d10 + 2.0D * d6) + d10 * (d4 * d9 + d5 * d10 + 2.0D * d11) + d7);
/*      */         } 
/*      */         
/*  427 */         d10 = 0.0D;
/*  428 */         if (d6 >= 0.0D) {
/*  429 */           d9 = 0.0D;
/*  430 */           if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d1); 
/*  431 */           if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  432 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  433 */            return DIST(d7);
/*      */         } 
/*      */         
/*  436 */         d9 = -d6 / d3;
/*  437 */         if (paramPoint3d3 != null) paramPoint3d3.scaleAdd(d9, paramVector3d1, paramPoint3d1); 
/*  438 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  439 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  440 */          return DIST(d6 * d9 + d7);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  445 */       if (d10 >= 0.0D) {
/*  446 */         d9 = 0.0D;
/*  447 */         if (d11 >= 0.0D) {
/*  448 */           d10 = 0.0D;
/*  449 */           if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d1); 
/*  450 */           if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  451 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  452 */            return DIST(d7);
/*      */         } 
/*      */         
/*  455 */         d10 = -d11 / d5;
/*  456 */         if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d1); 
/*  457 */         if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d10, paramVector3d2, paramPoint3d2); 
/*  458 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  459 */          return DIST(d11 * d10 + d7);
/*      */       } 
/*      */ 
/*      */       
/*  463 */       if (d6 < 0.0D) {
/*  464 */         d9 = -d6 / d3;
/*  465 */         d10 = 0.0D;
/*  466 */         if (paramPoint3d3 != null) paramPoint3d3.scaleAdd(d9, paramVector3d1, paramPoint3d1); 
/*  467 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  468 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  469 */          return DIST(d6 * d9 + d7);
/*      */       } 
/*      */       
/*  472 */       d9 = 0.0D;
/*  473 */       if (d11 >= 0.0D) {
/*  474 */         d10 = 0.0D;
/*  475 */         if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d1); 
/*  476 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  477 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  478 */          return DIST(d7);
/*      */       } 
/*      */       
/*  481 */       d10 = -d11 / d5;
/*  482 */       if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d1); 
/*  483 */       if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d10, paramVector3d2, paramPoint3d2); 
/*  484 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  485 */        return DIST(d11 * d10 + d7);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  493 */     if (d4 > 0.0D) {
/*      */       
/*  495 */       double d10 = 0.0D;
/*  496 */       if (d6 >= 0.0D) {
/*  497 */         double d = 0.0D;
/*  498 */         if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d1); 
/*  499 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  500 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d; paramArrayOfDouble[1] = d10; }
/*  501 */          return DIST(d7);
/*      */       } 
/*      */       
/*  504 */       double d9 = -d6 / d3;
/*  505 */       if (paramPoint3d3 != null) paramPoint3d3.scaleAdd(d9, paramVector3d1, paramPoint3d1); 
/*  506 */       if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  507 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  508 */        return DIST(d6 * d9 + d7);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  513 */     if (d6 >= 0.0D) {
/*  514 */       double d11 = paramVector3d2.dot(vector3d);
/*  515 */       double d9 = 0.0D;
/*  516 */       double d10 = -d11 / d5;
/*  517 */       if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d1); 
/*  518 */       if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d10, paramVector3d2, paramPoint3d2); 
/*  519 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d9; paramArrayOfDouble[1] = d10; }
/*  520 */        return DIST(d11 * d10 + d7);
/*      */     } 
/*      */     
/*  523 */     double d1 = -d6 / d3;
/*  524 */     double d2 = 0.0D;
/*  525 */     if (paramPoint3d3 != null) paramPoint3d3.scaleAdd(d1, paramVector3d1, paramPoint3d1); 
/*  526 */     if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  527 */     if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d1; paramArrayOfDouble[1] = d2; }
/*  528 */      return DIST(d6 * d1 + d7);
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
/*  544 */   public static double pointToRay(Point3d paramPoint3d1, Point3d paramPoint3d2, Vector3d paramVector3d) { return pointToRay(paramPoint3d1, paramPoint3d2, paramVector3d, null, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double pointToRay(Point3d paramPoint3d1, Point3d paramPoint3d2, Vector3d paramVector3d, Point3d paramPoint3d3, double[] paramArrayOfDouble) {
/*  574 */     Vector3d vector3d = new Vector3d();
/*  575 */     vector3d.sub(paramPoint3d1, paramPoint3d2);
/*  576 */     double d = paramVector3d.dot(vector3d);
/*      */     
/*  578 */     if (d <= 0.0D) {
/*  579 */       d = 0.0D;
/*  580 */       if (paramPoint3d3 != null) paramPoint3d3.set(paramPoint3d2); 
/*  581 */       if (paramArrayOfDouble != null) paramArrayOfDouble[0] = d; 
/*      */     } else {
/*  583 */       d /= paramVector3d.dot(paramVector3d);
/*  584 */       vector3d.scaleAdd(-d, paramVector3d, vector3d);
/*  585 */       if (paramPoint3d3 != null) paramPoint3d3.scaleAdd(d, paramVector3d, paramPoint3d2); 
/*  586 */       if (paramArrayOfDouble != null) paramArrayOfDouble[0] = d; 
/*      */     } 
/*  588 */     return vector3d.dot(vector3d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  597 */   public static double pointToSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3) { return pointToSegment(paramPoint3d1, paramPoint3d2, paramPoint3d3, null, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double pointToSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, double[] paramArrayOfDouble) {
/*  610 */     Vector3d vector3d1 = new Vector3d();
/*  611 */     vector3d1.sub(paramPoint3d3, paramPoint3d2);
/*  612 */     Vector3d vector3d2 = new Vector3d();
/*  613 */     vector3d2.sub(paramPoint3d1, paramPoint3d2);
/*  614 */     double d = vector3d1.dot(vector3d2);
/*      */     
/*  616 */     if (d <= 0.0D) {
/*  617 */       d = 0.0D;
/*  618 */       if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d2); 
/*  619 */       if (paramArrayOfDouble != null) paramArrayOfDouble[0] = d;
/*      */     
/*      */     } else {
/*  622 */       double d1 = vector3d1.dot(vector3d1);
/*  623 */       if (d >= d1) {
/*  624 */         d = 1.0D;
/*  625 */         vector3d2.sub(vector3d1);
/*  626 */         if (paramPoint3d4 != null) paramPoint3d4.set(paramPoint3d3); 
/*  627 */         if (paramArrayOfDouble != null) paramArrayOfDouble[0] = d;
/*      */       
/*      */       } else {
/*  630 */         d /= d1;
/*  631 */         vector3d2.scaleAdd(-d, vector3d1, vector3d2);
/*  632 */         if (paramPoint3d4 != null) paramPoint3d4.scaleAdd(d, vector3d1, paramPoint3d2); 
/*  633 */         if (paramArrayOfDouble != null) paramArrayOfDouble[0] = d; 
/*      */       } 
/*      */     } 
/*  636 */     return vector3d2.dot(vector3d2);
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
/*  652 */   public static double segmentToSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4) { return segmentToSegment(paramPoint3d1, paramPoint3d2, paramPoint3d3, paramPoint3d4, null, null, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double segmentToSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4, Point3d paramPoint3d5, Point3d paramPoint3d6, double[] paramArrayOfDouble) {
/*  690 */     Vector3d vector3d1 = new Vector3d();
/*  691 */     vector3d1.sub(paramPoint3d1, paramPoint3d3);
/*      */     
/*  693 */     Vector3d vector3d2 = new Vector3d();
/*  694 */     vector3d2.sub(paramPoint3d2, paramPoint3d1);
/*  695 */     Vector3d vector3d3 = new Vector3d();
/*  696 */     vector3d3.sub(paramPoint3d4, paramPoint3d3);
/*      */     
/*  698 */     double d3 = vector3d2.dot(vector3d2);
/*  699 */     double d4 = -vector3d2.dot(vector3d3);
/*  700 */     double d5 = vector3d3.dot(vector3d3);
/*  701 */     double d6 = vector3d2.dot(vector3d1);
/*      */     
/*  703 */     double d8 = vector3d1.dot(vector3d1);
/*  704 */     double d9 = Math.abs(d3 * d5 - d4 * d4);
/*      */ 
/*      */ 
/*      */     
/*  708 */     if (d9 >= 1.0E-5D) {
/*      */       
/*  710 */       double d12 = -vector3d3.dot(vector3d1);
/*  711 */       double d10 = d4 * d12 - d5 * d6;
/*  712 */       double d11 = d4 * d6 - d3 * d12;
/*      */       
/*  714 */       if (d10 >= 0.0D) {
/*  715 */         if (d10 <= d9) {
/*  716 */           if (d11 >= 0.0D) {
/*  717 */             if (d11 <= d9) {
/*      */               
/*  719 */               double d14 = 1.0D / d9;
/*  720 */               d10 *= d14;
/*  721 */               d11 *= d14;
/*  722 */               if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/*  723 */               if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/*  724 */               if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  725 */                return DIST(d10 * (d3 * d10 + d4 * d11 + 2.0D * d6) + d11 * (d4 * d10 + d5 * d11 + 2.0D * d12) + d8);
/*      */             } 
/*      */             
/*  728 */             d11 = 1.0D;
/*  729 */             double d13 = d4 + d6;
/*  730 */             if (d13 >= 0.0D) {
/*  731 */               d10 = 0.0D;
/*  732 */               if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  733 */               if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  734 */               if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  735 */                return DIST(d5 + 2.0D * d12 + d8);
/*      */             } 
/*  737 */             if (-d13 >= d3) {
/*  738 */               d10 = 1.0D;
/*  739 */               if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  740 */               if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  741 */               if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  742 */                return DIST(d3 + d5 + d8 + 2.0D * (d12 + d13));
/*      */             } 
/*      */             
/*  745 */             d10 = -d13 / d3;
/*  746 */             if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/*  747 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  748 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  749 */              return DIST(d13 * d10 + d5 + 2.0D * d12 + d8);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  754 */           d11 = 0.0D;
/*  755 */           if (d6 >= 0.0D) {
/*  756 */             d10 = 0.0D;
/*  757 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  758 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  759 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  760 */              return DIST(d8);
/*      */           } 
/*  762 */           if (-d6 >= d3) {
/*  763 */             d10 = 1.0D;
/*  764 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  765 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  766 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  767 */              return DIST(d3 + 2.0D * d6 + d8);
/*      */           } 
/*      */           
/*  770 */           d10 = -d6 / d3;
/*  771 */           if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/*  772 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  773 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  774 */            return DIST(d6 * d10 + d8);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  779 */         if (d11 >= 0.0D) {
/*  780 */           if (d11 <= d9) {
/*  781 */             d10 = 1.0D;
/*  782 */             double d14 = d4 + d12;
/*  783 */             if (d14 >= 0.0D) {
/*  784 */               d11 = 0.0D;
/*  785 */               if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  786 */               if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  787 */               if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  788 */                return DIST(d3 + 2.0D * d6 + d8);
/*      */             } 
/*  790 */             if (-d14 >= d5) {
/*  791 */               d11 = 1.0D;
/*  792 */               if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  793 */               if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  794 */               if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  795 */                return DIST(d3 + d5 + d8 + 2.0D * (d6 + d14));
/*      */             } 
/*      */             
/*  798 */             d11 = -d14 / d5;
/*  799 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  800 */             if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/*  801 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  802 */              return DIST(d14 * d11 + d3 + 2.0D * d6 + d8);
/*      */           } 
/*      */ 
/*      */           
/*  806 */           double d13 = d4 + d6;
/*  807 */           if (-d13 <= d3) {
/*  808 */             d11 = 1.0D;
/*  809 */             if (d13 >= 0.0D) {
/*  810 */               d10 = 0.0D;
/*  811 */               if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  812 */               if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  813 */               if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  814 */                return DIST(d5 + 2.0D * d12 + d8);
/*      */             } 
/*      */             
/*  817 */             d10 = -d13 / d3;
/*  818 */             if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/*  819 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  820 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  821 */              return DIST(d13 * d10 + d5 + 2.0D * d12 + d8);
/*      */           } 
/*      */ 
/*      */           
/*  825 */           d10 = 1.0D;
/*  826 */           d13 = d4 + d12;
/*  827 */           if (d13 >= 0.0D) {
/*  828 */             d11 = 0.0D;
/*  829 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  830 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  831 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  832 */              return DIST(d3 + 2.0D * d6 + d8);
/*      */           } 
/*  834 */           if (-d13 >= d5) {
/*  835 */             d11 = 1.0D;
/*  836 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  837 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  838 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  839 */              return DIST(d3 + d5 + d8 + 2.0D * (d6 + d13));
/*      */           } 
/*      */           
/*  842 */           d11 = -d13 / d5;
/*  843 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  844 */           if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/*  845 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  846 */            return DIST(d13 * d11 + d3 + 2.0D * d6 + d8);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  852 */         if (-d6 < d3) {
/*  853 */           d11 = 0.0D;
/*  854 */           if (d6 >= 0.0D) {
/*  855 */             d10 = 0.0D;
/*  856 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  857 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  858 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  859 */              return DIST(d8);
/*      */           } 
/*      */           
/*  862 */           d10 = -d6 / d3;
/*  863 */           if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/*  864 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  865 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  866 */            return DIST(d6 * d10 + d8);
/*      */         } 
/*      */ 
/*      */         
/*  870 */         d10 = 1.0D;
/*  871 */         double d = d4 + d12;
/*  872 */         if (d >= 0.0D) {
/*  873 */           d11 = 0.0D;
/*  874 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  875 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  876 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  877 */            return DIST(d3 + 2.0D * d6 + d8);
/*      */         } 
/*  879 */         if (-d >= d5) {
/*  880 */           d11 = 1.0D;
/*  881 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  882 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  883 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  884 */            return DIST(d3 + d5 + d8 + 2.0D * (d6 + d));
/*      */         } 
/*      */         
/*  887 */         d11 = -d / d5;
/*  888 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  889 */         if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/*  890 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  891 */          return DIST(d * d11 + d3 + 2.0D * d6 + d8);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  898 */       if (d11 >= 0.0D) {
/*  899 */         if (d11 <= d9) {
/*  900 */           d10 = 0.0D;
/*  901 */           if (d12 >= 0.0D) {
/*  902 */             d11 = 0.0D;
/*  903 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  904 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  905 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  906 */              return DIST(d8);
/*      */           } 
/*  908 */           if (-d12 >= d5) {
/*  909 */             d11 = 1.0D;
/*  910 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  911 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  912 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  913 */              return DIST(d5 + 2.0D * d12 + d8);
/*      */           } 
/*      */           
/*  916 */           d11 = -d12 / d5;
/*  917 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  918 */           if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/*  919 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  920 */            return DIST(d12 * d11 + d8);
/*      */         } 
/*      */ 
/*      */         
/*  924 */         double d = d4 + d6;
/*  925 */         if (d < 0.0D) {
/*  926 */           d11 = 1.0D;
/*  927 */           if (-d >= d3) {
/*  928 */             d10 = 1.0D;
/*  929 */             if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  930 */             if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  931 */             if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  932 */              return DIST(d3 + d5 + d8 + 2.0D * (d12 + d));
/*      */           } 
/*      */           
/*  935 */           d10 = -d / d3;
/*  936 */           if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/*  937 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  938 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  939 */            return DIST(d * d10 + d5 + 2.0D * d12 + d8);
/*      */         } 
/*      */ 
/*      */         
/*  943 */         d10 = 0.0D;
/*  944 */         if (d12 >= 0.0D) {
/*  945 */           d11 = 0.0D;
/*  946 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  947 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  948 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  949 */            return DIST(d8);
/*      */         } 
/*  951 */         if (-d12 >= d5) {
/*  952 */           d11 = 1.0D;
/*  953 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  954 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  955 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  956 */            return DIST(d5 + 2.0D * d12 + d8);
/*      */         } 
/*      */         
/*  959 */         d11 = -d12 / d5;
/*  960 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  961 */         if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/*  962 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  963 */          return DIST(d12 * d11 + d8);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  969 */       if (d6 < 0.0D) {
/*  970 */         d11 = 0.0D;
/*  971 */         if (-d6 >= d3) {
/*  972 */           d10 = 1.0D;
/*  973 */           if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/*  974 */           if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  975 */           if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  976 */            return DIST(d3 + 2.0D * d6 + d8);
/*      */         } 
/*      */         
/*  979 */         d10 = -d6 / d3;
/*  980 */         if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/*  981 */         if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  982 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  983 */          return DIST(d6 * d10 + d8);
/*      */       } 
/*      */ 
/*      */       
/*  987 */       d10 = 0.0D;
/*  988 */       if (d12 >= 0.0D) {
/*  989 */         d11 = 0.0D;
/*  990 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  991 */         if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/*  992 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/*  993 */          return DIST(d8);
/*      */       } 
/*  995 */       if (-d12 >= d5) {
/*  996 */         d11 = 1.0D;
/*  997 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/*  998 */         if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/*  999 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/* 1000 */          return DIST(d5 + 2.0D * d12 + d8);
/*      */       } 
/*      */       
/* 1003 */       d11 = -d12 / d5;
/* 1004 */       if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/* 1005 */       if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/* 1006 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/* 1007 */        return DIST(d12 * d11 + d8);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1015 */     if (d4 > 0.0D) {
/*      */       
/* 1017 */       if (d6 >= 0.0D) {
/* 1018 */         double d14 = 0.0D;
/* 1019 */         double d15 = 0.0D;
/* 1020 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/* 1021 */         if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/* 1022 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d14; paramArrayOfDouble[1] = d15; }
/* 1023 */          return DIST(d8);
/*      */       } 
/* 1025 */       if (-d6 <= d3) {
/* 1026 */         double d14 = -d6 / d3;
/* 1027 */         double d15 = 0.0D;
/* 1028 */         if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d14, vector3d2, paramPoint3d1); 
/* 1029 */         if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/* 1030 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d14; paramArrayOfDouble[1] = d15; }
/* 1031 */          return DIST(d6 * d14 + d8);
/*      */       } 
/*      */       
/* 1034 */       double d12 = -vector3d3.dot(vector3d1);
/* 1035 */       double d10 = 1.0D;
/* 1036 */       double d13 = d3 + d6;
/* 1037 */       if (-d13 >= d4) {
/* 1038 */         double d = 1.0D;
/* 1039 */         if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/* 1040 */         if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/* 1041 */         if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d; }
/* 1042 */          return DIST(d3 + d5 + d8 + 2.0D * (d4 + d6 + d12));
/*      */       } 
/*      */       
/* 1045 */       double d11 = -d13 / d4;
/* 1046 */       if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/* 1047 */       if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d11, vector3d3, paramPoint3d3); 
/* 1048 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/* 1049 */        return DIST(d3 + 2.0D * d6 + d8 + d11 * (d5 * d11 + 2.0D * (d4 + d12)));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1055 */     if (-d6 >= d3) {
/* 1056 */       double d10 = 1.0D;
/* 1057 */       double d11 = 0.0D;
/* 1058 */       if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d2); 
/* 1059 */       if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/* 1060 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/* 1061 */        return DIST(d3 + 2.0D * d6 + d8);
/*      */     } 
/* 1063 */     if (d6 <= 0.0D) {
/* 1064 */       double d10 = -d6 / d3;
/* 1065 */       double d11 = 0.0D;
/* 1066 */       if (paramPoint3d5 != null) paramPoint3d5.scaleAdd(d10, vector3d2, paramPoint3d1); 
/* 1067 */       if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d3); 
/* 1068 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d10; paramArrayOfDouble[1] = d11; }
/* 1069 */        return DIST(d6 * d10 + d8);
/*      */     } 
/*      */     
/* 1072 */     double d7 = -vector3d3.dot(vector3d1);
/* 1073 */     double d1 = 0.0D;
/* 1074 */     if (d6 >= -d4) {
/* 1075 */       double d = 1.0D;
/* 1076 */       if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/* 1077 */       if (paramPoint3d6 != null) paramPoint3d6.set(paramPoint3d4); 
/* 1078 */       if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d1; paramArrayOfDouble[1] = d; }
/* 1079 */        return DIST(d5 + 2.0D * d7 + d8);
/*      */     } 
/*      */     
/* 1082 */     double d2 = -d6 / d4;
/* 1083 */     if (paramPoint3d5 != null) paramPoint3d5.set(paramPoint3d1); 
/* 1084 */     if (paramPoint3d6 != null) paramPoint3d6.scaleAdd(d2, vector3d3, paramPoint3d3); 
/* 1085 */     if (paramArrayOfDouble != null) { paramArrayOfDouble[0] = d1; paramArrayOfDouble[1] = d2; }
/* 1086 */      return DIST(d8 + d2 * (2.0D * d7 + d5 * d2));
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\internal\Distance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */