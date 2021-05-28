/*      */ package javax.vecmath;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Matrix3f
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   static final long serialVersionUID = 329697160112089834L;
/*      */   public float m00;
/*      */   public float m01;
/*      */   public float m02;
/*      */   public float m10;
/*      */   public float m11;
/*      */   public float m12;
/*      */   public float m20;
/*      */   public float m21;
/*      */   public float m22;
/*      */   private static final double EPS = 1.0E-8D;
/*      */   
/*      */   public Matrix3f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9) {
/*   96 */     this.m00 = paramFloat1;
/*   97 */     this.m01 = paramFloat2;
/*   98 */     this.m02 = paramFloat3;
/*      */     
/*  100 */     this.m10 = paramFloat4;
/*  101 */     this.m11 = paramFloat5;
/*  102 */     this.m12 = paramFloat6;
/*      */     
/*  104 */     this.m20 = paramFloat7;
/*  105 */     this.m21 = paramFloat8;
/*  106 */     this.m22 = paramFloat9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix3f(float[] paramArrayOfFloat) {
/*  117 */     this.m00 = paramArrayOfFloat[0];
/*  118 */     this.m01 = paramArrayOfFloat[1];
/*  119 */     this.m02 = paramArrayOfFloat[2];
/*      */     
/*  121 */     this.m10 = paramArrayOfFloat[3];
/*  122 */     this.m11 = paramArrayOfFloat[4];
/*  123 */     this.m12 = paramArrayOfFloat[5];
/*      */     
/*  125 */     this.m20 = paramArrayOfFloat[6];
/*  126 */     this.m21 = paramArrayOfFloat[7];
/*  127 */     this.m22 = paramArrayOfFloat[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix3f(Matrix3d paramMatrix3d) {
/*  138 */     this.m00 = (float)paramMatrix3d.m00;
/*  139 */     this.m01 = (float)paramMatrix3d.m01;
/*  140 */     this.m02 = (float)paramMatrix3d.m02;
/*      */     
/*  142 */     this.m10 = (float)paramMatrix3d.m10;
/*  143 */     this.m11 = (float)paramMatrix3d.m11;
/*  144 */     this.m12 = (float)paramMatrix3d.m12;
/*      */     
/*  146 */     this.m20 = (float)paramMatrix3d.m20;
/*  147 */     this.m21 = (float)paramMatrix3d.m21;
/*  148 */     this.m22 = (float)paramMatrix3d.m22;
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
/*      */   public Matrix3f(Matrix3f paramMatrix3f) {
/*  160 */     this.m00 = paramMatrix3f.m00;
/*  161 */     this.m01 = paramMatrix3f.m01;
/*  162 */     this.m02 = paramMatrix3f.m02;
/*      */     
/*  164 */     this.m10 = paramMatrix3f.m10;
/*  165 */     this.m11 = paramMatrix3f.m11;
/*  166 */     this.m12 = paramMatrix3f.m12;
/*      */     
/*  168 */     this.m20 = paramMatrix3f.m20;
/*  169 */     this.m21 = paramMatrix3f.m21;
/*  170 */     this.m22 = paramMatrix3f.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix3f() {
/*  180 */     this.m00 = 0.0F;
/*  181 */     this.m01 = 0.0F;
/*  182 */     this.m02 = 0.0F;
/*      */     
/*  184 */     this.m10 = 0.0F;
/*  185 */     this.m11 = 0.0F;
/*  186 */     this.m12 = 0.0F;
/*      */     
/*  188 */     this.m20 = 0.0F;
/*  189 */     this.m21 = 0.0F;
/*  190 */     this.m22 = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  199 */   public String toString() { return this.m00 + ", " + this.m01 + ", " + this.m02 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + "\n"; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setIdentity() {
/*  210 */     this.m00 = 1.0F;
/*  211 */     this.m01 = 0.0F;
/*  212 */     this.m02 = 0.0F;
/*      */     
/*  214 */     this.m10 = 0.0F;
/*  215 */     this.m11 = 1.0F;
/*  216 */     this.m12 = 0.0F;
/*      */     
/*  218 */     this.m20 = 0.0F;
/*  219 */     this.m21 = 0.0F;
/*  220 */     this.m22 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setScale(float paramFloat) {
/*  231 */     double[] arrayOfDouble1 = new double[9];
/*  232 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  234 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  236 */     this.m00 = (float)(arrayOfDouble1[0] * paramFloat);
/*  237 */     this.m01 = (float)(arrayOfDouble1[1] * paramFloat);
/*  238 */     this.m02 = (float)(arrayOfDouble1[2] * paramFloat);
/*      */     
/*  240 */     this.m10 = (float)(arrayOfDouble1[3] * paramFloat);
/*  241 */     this.m11 = (float)(arrayOfDouble1[4] * paramFloat);
/*  242 */     this.m12 = (float)(arrayOfDouble1[5] * paramFloat);
/*      */     
/*  244 */     this.m20 = (float)(arrayOfDouble1[6] * paramFloat);
/*  245 */     this.m21 = (float)(arrayOfDouble1[7] * paramFloat);
/*  246 */     this.m22 = (float)(arrayOfDouble1[8] * paramFloat);
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
/*      */   public final void setElement(int paramInt1, int paramInt2, float paramFloat) {
/*  258 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  261 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  264 */             this.m00 = paramFloat;
/*      */             return;
/*      */           case 1:
/*  267 */             this.m01 = paramFloat;
/*      */             return;
/*      */           case 2:
/*  270 */             this.m02 = paramFloat;
/*      */             return;
/*      */         } 
/*  273 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  278 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  281 */             this.m10 = paramFloat;
/*      */             return;
/*      */           case 1:
/*  284 */             this.m11 = paramFloat;
/*      */             return;
/*      */           case 2:
/*  287 */             this.m12 = paramFloat;
/*      */             return;
/*      */         } 
/*  290 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  295 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  298 */             this.m20 = paramFloat;
/*      */             return;
/*      */           case 1:
/*  301 */             this.m21 = paramFloat;
/*      */             return;
/*      */           case 2:
/*  304 */             this.m22 = paramFloat;
/*      */             return;
/*      */         } 
/*      */         
/*  308 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  313 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, Vector3f paramVector3f) {
/*  323 */     if (paramInt == 0) {
/*  324 */       paramVector3f.x = this.m00;
/*  325 */       paramVector3f.y = this.m01;
/*  326 */       paramVector3f.z = this.m02;
/*  327 */     } else if (paramInt == 1) {
/*  328 */       paramVector3f.x = this.m10;
/*  329 */       paramVector3f.y = this.m11;
/*  330 */       paramVector3f.z = this.m12;
/*  331 */     } else if (paramInt == 2) {
/*  332 */       paramVector3f.x = this.m20;
/*  333 */       paramVector3f.y = this.m21;
/*  334 */       paramVector3f.z = this.m22;
/*      */     } else {
/*  336 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f1"));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, float[] paramArrayOfFloat) {
/*  347 */     if (paramInt == 0) {
/*  348 */       paramArrayOfFloat[0] = this.m00;
/*  349 */       paramArrayOfFloat[1] = this.m01;
/*  350 */       paramArrayOfFloat[2] = this.m02;
/*  351 */     } else if (paramInt == 1) {
/*  352 */       paramArrayOfFloat[0] = this.m10;
/*  353 */       paramArrayOfFloat[1] = this.m11;
/*  354 */       paramArrayOfFloat[2] = this.m12;
/*  355 */     } else if (paramInt == 2) {
/*  356 */       paramArrayOfFloat[0] = this.m20;
/*  357 */       paramArrayOfFloat[1] = this.m21;
/*  358 */       paramArrayOfFloat[2] = this.m22;
/*      */     } else {
/*  360 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f1"));
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
/*      */   public final void getColumn(int paramInt, Vector3f paramVector3f) {
/*  372 */     if (paramInt == 0) {
/*  373 */       paramVector3f.x = this.m00;
/*  374 */       paramVector3f.y = this.m10;
/*  375 */       paramVector3f.z = this.m20;
/*  376 */     } else if (paramInt == 1) {
/*  377 */       paramVector3f.x = this.m01;
/*  378 */       paramVector3f.y = this.m11;
/*  379 */       paramVector3f.z = this.m21;
/*  380 */     } else if (paramInt == 2) {
/*  381 */       paramVector3f.x = this.m02;
/*  382 */       paramVector3f.y = this.m12;
/*  383 */       paramVector3f.z = this.m22;
/*      */     } else {
/*  385 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f3"));
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
/*      */   public final void getColumn(int paramInt, float[] paramArrayOfFloat) {
/*  397 */     if (paramInt == 0) {
/*  398 */       paramArrayOfFloat[0] = this.m00;
/*  399 */       paramArrayOfFloat[1] = this.m10;
/*  400 */       paramArrayOfFloat[2] = this.m20;
/*  401 */     } else if (paramInt == 1) {
/*  402 */       paramArrayOfFloat[0] = this.m01;
/*  403 */       paramArrayOfFloat[1] = this.m11;
/*  404 */       paramArrayOfFloat[2] = this.m21;
/*  405 */     } else if (paramInt == 2) {
/*  406 */       paramArrayOfFloat[0] = this.m02;
/*  407 */       paramArrayOfFloat[1] = this.m12;
/*  408 */       paramArrayOfFloat[2] = this.m22;
/*      */     } else {
/*  410 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f3"));
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
/*      */   public final float getElement(int paramInt1, int paramInt2) {
/*  423 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  426 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  429 */             return this.m00;
/*      */           case 1:
/*  431 */             return this.m01;
/*      */           case 2:
/*  433 */             return this.m02;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case 1:
/*  439 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  442 */             return this.m10;
/*      */           case 1:
/*  444 */             return this.m11;
/*      */           case 2:
/*  446 */             return this.m12;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 2:
/*  453 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  456 */             return this.m20;
/*      */           case 1:
/*  458 */             return this.m21;
/*      */           case 2:
/*  460 */             return this.m22;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  469 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f5"));
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
/*      */   public final void setRow(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  481 */     switch (paramInt) {
/*      */       case 0:
/*  483 */         this.m00 = paramFloat1;
/*  484 */         this.m01 = paramFloat2;
/*  485 */         this.m02 = paramFloat3;
/*      */         return;
/*      */       
/*      */       case 1:
/*  489 */         this.m10 = paramFloat1;
/*  490 */         this.m11 = paramFloat2;
/*  491 */         this.m12 = paramFloat3;
/*      */         return;
/*      */       
/*      */       case 2:
/*  495 */         this.m20 = paramFloat1;
/*  496 */         this.m21 = paramFloat2;
/*  497 */         this.m22 = paramFloat3;
/*      */         return;
/*      */     } 
/*      */     
/*  501 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRow(int paramInt, Vector3f paramVector3f) {
/*  512 */     switch (paramInt) {
/*      */       case 0:
/*  514 */         this.m00 = paramVector3f.x;
/*  515 */         this.m01 = paramVector3f.y;
/*  516 */         this.m02 = paramVector3f.z;
/*      */         return;
/*      */       
/*      */       case 1:
/*  520 */         this.m10 = paramVector3f.x;
/*  521 */         this.m11 = paramVector3f.y;
/*  522 */         this.m12 = paramVector3f.z;
/*      */         return;
/*      */       
/*      */       case 2:
/*  526 */         this.m20 = paramVector3f.x;
/*  527 */         this.m21 = paramVector3f.y;
/*  528 */         this.m22 = paramVector3f.z;
/*      */         return;
/*      */     } 
/*      */     
/*  532 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRow(int paramInt, float[] paramArrayOfFloat) {
/*  543 */     switch (paramInt) {
/*      */       case 0:
/*  545 */         this.m00 = paramArrayOfFloat[0];
/*  546 */         this.m01 = paramArrayOfFloat[1];
/*  547 */         this.m02 = paramArrayOfFloat[2];
/*      */         return;
/*      */       
/*      */       case 1:
/*  551 */         this.m10 = paramArrayOfFloat[0];
/*  552 */         this.m11 = paramArrayOfFloat[1];
/*  553 */         this.m12 = paramArrayOfFloat[2];
/*      */         return;
/*      */       
/*      */       case 2:
/*  557 */         this.m20 = paramArrayOfFloat[0];
/*  558 */         this.m21 = paramArrayOfFloat[1];
/*  559 */         this.m22 = paramArrayOfFloat[2];
/*      */         return;
/*      */     } 
/*      */     
/*  563 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
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
/*      */   public final void setColumn(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  576 */     switch (paramInt) {
/*      */       case 0:
/*  578 */         this.m00 = paramFloat1;
/*  579 */         this.m10 = paramFloat2;
/*  580 */         this.m20 = paramFloat3;
/*      */         return;
/*      */       
/*      */       case 1:
/*  584 */         this.m01 = paramFloat1;
/*  585 */         this.m11 = paramFloat2;
/*  586 */         this.m21 = paramFloat3;
/*      */         return;
/*      */       
/*      */       case 2:
/*  590 */         this.m02 = paramFloat1;
/*  591 */         this.m12 = paramFloat2;
/*  592 */         this.m22 = paramFloat3;
/*      */         return;
/*      */     } 
/*      */     
/*  596 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setColumn(int paramInt, Vector3f paramVector3f) {
/*  607 */     switch (paramInt) {
/*      */       case 0:
/*  609 */         this.m00 = paramVector3f.x;
/*  610 */         this.m10 = paramVector3f.y;
/*  611 */         this.m20 = paramVector3f.z;
/*      */         return;
/*      */       
/*      */       case 1:
/*  615 */         this.m01 = paramVector3f.x;
/*  616 */         this.m11 = paramVector3f.y;
/*  617 */         this.m21 = paramVector3f.z;
/*      */         return;
/*      */       
/*      */       case 2:
/*  621 */         this.m02 = paramVector3f.x;
/*  622 */         this.m12 = paramVector3f.y;
/*  623 */         this.m22 = paramVector3f.z;
/*      */         return;
/*      */     } 
/*      */     
/*  627 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setColumn(int paramInt, float[] paramArrayOfFloat) {
/*  638 */     switch (paramInt) {
/*      */       case 0:
/*  640 */         this.m00 = paramArrayOfFloat[0];
/*  641 */         this.m10 = paramArrayOfFloat[1];
/*  642 */         this.m20 = paramArrayOfFloat[2];
/*      */         return;
/*      */       
/*      */       case 1:
/*  646 */         this.m01 = paramArrayOfFloat[0];
/*  647 */         this.m11 = paramArrayOfFloat[1];
/*  648 */         this.m21 = paramArrayOfFloat[2];
/*      */         return;
/*      */       
/*      */       case 2:
/*  652 */         this.m02 = paramArrayOfFloat[0];
/*  653 */         this.m12 = paramArrayOfFloat[1];
/*  654 */         this.m22 = paramArrayOfFloat[2];
/*      */         return;
/*      */     } 
/*      */     
/*  658 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
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
/*      */   public final float getScale() {
/*  672 */     double[] arrayOfDouble1 = new double[9];
/*  673 */     double[] arrayOfDouble2 = new double[3];
/*  674 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  676 */     return (float)Matrix3d.max3(arrayOfDouble2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(float paramFloat) {
/*  686 */     this.m00 += paramFloat;
/*  687 */     this.m01 += paramFloat;
/*  688 */     this.m02 += paramFloat;
/*  689 */     this.m10 += paramFloat;
/*  690 */     this.m11 += paramFloat;
/*  691 */     this.m12 += paramFloat;
/*  692 */     this.m20 += paramFloat;
/*  693 */     this.m21 += paramFloat;
/*  694 */     this.m22 += paramFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(float paramFloat, Matrix3f paramMatrix3f) {
/*  705 */     paramMatrix3f.m00 += paramFloat;
/*  706 */     paramMatrix3f.m01 += paramFloat;
/*  707 */     paramMatrix3f.m02 += paramFloat;
/*  708 */     paramMatrix3f.m10 += paramFloat;
/*  709 */     paramMatrix3f.m11 += paramFloat;
/*  710 */     paramMatrix3f.m12 += paramFloat;
/*  711 */     paramMatrix3f.m20 += paramFloat;
/*  712 */     paramMatrix3f.m21 += paramFloat;
/*  713 */     paramMatrix3f.m22 += paramFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2) {
/*  723 */     paramMatrix3f1.m00 += paramMatrix3f2.m00;
/*  724 */     paramMatrix3f1.m01 += paramMatrix3f2.m01;
/*  725 */     paramMatrix3f1.m02 += paramMatrix3f2.m02;
/*      */     
/*  727 */     paramMatrix3f1.m10 += paramMatrix3f2.m10;
/*  728 */     paramMatrix3f1.m11 += paramMatrix3f2.m11;
/*  729 */     paramMatrix3f1.m12 += paramMatrix3f2.m12;
/*      */     
/*  731 */     paramMatrix3f1.m20 += paramMatrix3f2.m20;
/*  732 */     paramMatrix3f1.m21 += paramMatrix3f2.m21;
/*  733 */     paramMatrix3f1.m22 += paramMatrix3f2.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix3f paramMatrix3f) {
/*  743 */     this.m00 += paramMatrix3f.m00;
/*  744 */     this.m01 += paramMatrix3f.m01;
/*  745 */     this.m02 += paramMatrix3f.m02;
/*      */     
/*  747 */     this.m10 += paramMatrix3f.m10;
/*  748 */     this.m11 += paramMatrix3f.m11;
/*  749 */     this.m12 += paramMatrix3f.m12;
/*      */     
/*  751 */     this.m20 += paramMatrix3f.m20;
/*  752 */     this.m21 += paramMatrix3f.m21;
/*  753 */     this.m22 += paramMatrix3f.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2) {
/*  764 */     paramMatrix3f1.m00 -= paramMatrix3f2.m00;
/*  765 */     paramMatrix3f1.m01 -= paramMatrix3f2.m01;
/*  766 */     paramMatrix3f1.m02 -= paramMatrix3f2.m02;
/*      */     
/*  768 */     paramMatrix3f1.m10 -= paramMatrix3f2.m10;
/*  769 */     paramMatrix3f1.m11 -= paramMatrix3f2.m11;
/*  770 */     paramMatrix3f1.m12 -= paramMatrix3f2.m12;
/*      */     
/*  772 */     paramMatrix3f1.m20 -= paramMatrix3f2.m20;
/*  773 */     paramMatrix3f1.m21 -= paramMatrix3f2.m21;
/*  774 */     paramMatrix3f1.m22 -= paramMatrix3f2.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Matrix3f paramMatrix3f) {
/*  784 */     this.m00 -= paramMatrix3f.m00;
/*  785 */     this.m01 -= paramMatrix3f.m01;
/*  786 */     this.m02 -= paramMatrix3f.m02;
/*      */     
/*  788 */     this.m10 -= paramMatrix3f.m10;
/*  789 */     this.m11 -= paramMatrix3f.m11;
/*  790 */     this.m12 -= paramMatrix3f.m12;
/*      */     
/*  792 */     this.m20 -= paramMatrix3f.m20;
/*  793 */     this.m21 -= paramMatrix3f.m21;
/*  794 */     this.m22 -= paramMatrix3f.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose() {
/*  804 */     float f = this.m10;
/*  805 */     this.m10 = this.m01;
/*  806 */     this.m01 = f;
/*      */     
/*  808 */     f = this.m20;
/*  809 */     this.m20 = this.m02;
/*  810 */     this.m02 = f;
/*      */     
/*  812 */     f = this.m21;
/*  813 */     this.m21 = this.m12;
/*  814 */     this.m12 = f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose(Matrix3f paramMatrix3f) {
/*  823 */     if (this != paramMatrix3f) {
/*  824 */       this.m00 = paramMatrix3f.m00;
/*  825 */       this.m01 = paramMatrix3f.m10;
/*  826 */       this.m02 = paramMatrix3f.m20;
/*      */       
/*  828 */       this.m10 = paramMatrix3f.m01;
/*  829 */       this.m11 = paramMatrix3f.m11;
/*  830 */       this.m12 = paramMatrix3f.m21;
/*      */       
/*  832 */       this.m20 = paramMatrix3f.m02;
/*  833 */       this.m21 = paramMatrix3f.m12;
/*  834 */       this.m22 = paramMatrix3f.m22;
/*      */     } else {
/*  836 */       transpose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4f paramQuat4f) {
/*  846 */     this.m00 = 1.0F - 2.0F * paramQuat4f.y * paramQuat4f.y - 2.0F * paramQuat4f.z * paramQuat4f.z;
/*  847 */     this.m10 = 2.0F * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/*  848 */     this.m20 = 2.0F * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/*  850 */     this.m01 = 2.0F * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/*  851 */     this.m11 = 1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.z * paramQuat4f.z;
/*  852 */     this.m21 = 2.0F * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/*  854 */     this.m02 = 2.0F * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/*  855 */     this.m12 = 2.0F * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/*  856 */     this.m22 = 1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.y * paramQuat4f.y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(AxisAngle4f paramAxisAngle4f) {
/*  866 */     float f = (float)Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/*  867 */     if (f < 1.0E-8D) {
/*  868 */       this.m00 = 1.0F;
/*  869 */       this.m01 = 0.0F;
/*  870 */       this.m02 = 0.0F;
/*      */       
/*  872 */       this.m10 = 0.0F;
/*  873 */       this.m11 = 1.0F;
/*  874 */       this.m12 = 0.0F;
/*      */       
/*  876 */       this.m20 = 0.0F;
/*  877 */       this.m21 = 0.0F;
/*  878 */       this.m22 = 1.0F;
/*      */     } else {
/*  880 */       f = 1.0F / f;
/*  881 */       float f1 = paramAxisAngle4f.x * f;
/*  882 */       float f2 = paramAxisAngle4f.y * f;
/*  883 */       float f3 = paramAxisAngle4f.z * f;
/*      */       
/*  885 */       float f4 = (float)Math.sin(paramAxisAngle4f.angle);
/*  886 */       float f5 = (float)Math.cos(paramAxisAngle4f.angle);
/*  887 */       float f6 = 1.0F - f5;
/*      */       
/*  889 */       float f7 = f1 * f3;
/*  890 */       float f8 = f1 * f2;
/*  891 */       float f9 = f2 * f3;
/*      */       
/*  893 */       this.m00 = f6 * f1 * f1 + f5;
/*  894 */       this.m01 = f6 * f8 - f4 * f3;
/*  895 */       this.m02 = f6 * f7 + f4 * f2;
/*      */       
/*  897 */       this.m10 = f6 * f8 + f4 * f3;
/*  898 */       this.m11 = f6 * f2 * f2 + f5;
/*  899 */       this.m12 = f6 * f9 - f4 * f1;
/*      */       
/*  901 */       this.m20 = f6 * f7 - f4 * f2;
/*  902 */       this.m21 = f6 * f9 + f4 * f1;
/*  903 */       this.m22 = f6 * f3 * f3 + f5;
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
/*      */   public final void set(AxisAngle4d paramAxisAngle4d) {
/*  915 */     double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/*  916 */     if (d < 1.0E-8D) {
/*  917 */       this.m00 = 1.0F;
/*  918 */       this.m01 = 0.0F;
/*  919 */       this.m02 = 0.0F;
/*      */       
/*  921 */       this.m10 = 0.0F;
/*  922 */       this.m11 = 1.0F;
/*  923 */       this.m12 = 0.0F;
/*      */       
/*  925 */       this.m20 = 0.0F;
/*  926 */       this.m21 = 0.0F;
/*  927 */       this.m22 = 1.0F;
/*      */     } else {
/*  929 */       d = 1.0D / d;
/*  930 */       double d1 = paramAxisAngle4d.x * d;
/*  931 */       double d2 = paramAxisAngle4d.y * d;
/*  932 */       double d3 = paramAxisAngle4d.z * d;
/*      */       
/*  934 */       double d4 = Math.sin(paramAxisAngle4d.angle);
/*  935 */       double d5 = Math.cos(paramAxisAngle4d.angle);
/*  936 */       double d6 = 1.0D - d5;
/*      */       
/*  938 */       double d7 = d1 * d3;
/*  939 */       double d8 = d1 * d2;
/*  940 */       double d9 = d2 * d3;
/*      */       
/*  942 */       this.m00 = (float)(d6 * d1 * d1 + d5);
/*  943 */       this.m01 = (float)(d6 * d8 - d4 * d3);
/*  944 */       this.m02 = (float)(d6 * d7 + d4 * d2);
/*      */       
/*  946 */       this.m10 = (float)(d6 * d8 + d4 * d3);
/*  947 */       this.m11 = (float)(d6 * d2 * d2 + d5);
/*  948 */       this.m12 = (float)(d6 * d9 - d4 * d1);
/*      */       
/*  950 */       this.m20 = (float)(d6 * d7 - d4 * d2);
/*  951 */       this.m21 = (float)(d6 * d9 + d4 * d1);
/*  952 */       this.m22 = (float)(d6 * d3 * d3 + d5);
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
/*      */   public final void set(Quat4d paramQuat4d) {
/*  964 */     this.m00 = (float)(1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z);
/*  965 */     this.m10 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z));
/*  966 */     this.m20 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y));
/*      */     
/*  968 */     this.m01 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z));
/*  969 */     this.m11 = (float)(1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z);
/*  970 */     this.m21 = (float)(2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x));
/*      */     
/*  972 */     this.m02 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y));
/*  973 */     this.m12 = (float)(2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x));
/*  974 */     this.m22 = (float)(1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(float[] paramArrayOfFloat) {
/*  985 */     this.m00 = paramArrayOfFloat[0];
/*  986 */     this.m01 = paramArrayOfFloat[1];
/*  987 */     this.m02 = paramArrayOfFloat[2];
/*      */     
/*  989 */     this.m10 = paramArrayOfFloat[3];
/*  990 */     this.m11 = paramArrayOfFloat[4];
/*  991 */     this.m12 = paramArrayOfFloat[5];
/*      */     
/*  993 */     this.m20 = paramArrayOfFloat[6];
/*  994 */     this.m21 = paramArrayOfFloat[7];
/*  995 */     this.m22 = paramArrayOfFloat[8];
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
/*      */   public final void set(Matrix3f paramMatrix3f) {
/* 1007 */     this.m00 = paramMatrix3f.m00;
/* 1008 */     this.m01 = paramMatrix3f.m01;
/* 1009 */     this.m02 = paramMatrix3f.m02;
/*      */     
/* 1011 */     this.m10 = paramMatrix3f.m10;
/* 1012 */     this.m11 = paramMatrix3f.m11;
/* 1013 */     this.m12 = paramMatrix3f.m12;
/*      */     
/* 1015 */     this.m20 = paramMatrix3f.m20;
/* 1016 */     this.m21 = paramMatrix3f.m21;
/* 1017 */     this.m22 = paramMatrix3f.m22;
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
/*      */   public final void set(Matrix3d paramMatrix3d) {
/* 1029 */     this.m00 = (float)paramMatrix3d.m00;
/* 1030 */     this.m01 = (float)paramMatrix3d.m01;
/* 1031 */     this.m02 = (float)paramMatrix3d.m02;
/*      */     
/* 1033 */     this.m10 = (float)paramMatrix3d.m10;
/* 1034 */     this.m11 = (float)paramMatrix3d.m11;
/* 1035 */     this.m12 = (float)paramMatrix3d.m12;
/*      */     
/* 1037 */     this.m20 = (float)paramMatrix3d.m20;
/* 1038 */     this.m21 = (float)paramMatrix3d.m21;
/* 1039 */     this.m22 = (float)paramMatrix3d.m22;
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
/* 1051 */   public final void invert(Matrix3f paramMatrix3f) { invertGeneral(paramMatrix3f); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1059 */   public final void invert() { invertGeneral(this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void invertGeneral(Matrix3f paramMatrix3f) {
/* 1071 */     double[] arrayOfDouble1 = new double[9];
/* 1072 */     double[] arrayOfDouble2 = new double[9];
/* 1073 */     int[] arrayOfInt = new int[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1080 */     arrayOfDouble1[0] = paramMatrix3f.m00;
/* 1081 */     arrayOfDouble1[1] = paramMatrix3f.m01;
/* 1082 */     arrayOfDouble1[2] = paramMatrix3f.m02;
/*      */     
/* 1084 */     arrayOfDouble1[3] = paramMatrix3f.m10;
/* 1085 */     arrayOfDouble1[4] = paramMatrix3f.m11;
/* 1086 */     arrayOfDouble1[5] = paramMatrix3f.m12;
/*      */     
/* 1088 */     arrayOfDouble1[6] = paramMatrix3f.m20;
/* 1089 */     arrayOfDouble1[7] = paramMatrix3f.m21;
/* 1090 */     arrayOfDouble1[8] = paramMatrix3f.m22;
/*      */ 
/*      */ 
/*      */     
/* 1094 */     if (!luDecomposition(arrayOfDouble1, arrayOfInt))
/*      */     {
/* 1096 */       throw new SingularMatrixException(VecMathI18N.getString("Matrix3f12"));
/*      */     }
/*      */ 
/*      */     
/* 1100 */     for (byte b = 0; b < 9; ) { arrayOfDouble2[b] = 0.0D; b++; }
/* 1101 */      arrayOfDouble2[0] = 1.0D; arrayOfDouble2[4] = 1.0D; arrayOfDouble2[8] = 1.0D;
/* 1102 */     luBacksubstitution(arrayOfDouble1, arrayOfInt, arrayOfDouble2);
/*      */     
/* 1104 */     this.m00 = (float)arrayOfDouble2[0];
/* 1105 */     this.m01 = (float)arrayOfDouble2[1];
/* 1106 */     this.m02 = (float)arrayOfDouble2[2];
/*      */     
/* 1108 */     this.m10 = (float)arrayOfDouble2[3];
/* 1109 */     this.m11 = (float)arrayOfDouble2[4];
/* 1110 */     this.m12 = (float)arrayOfDouble2[5];
/*      */     
/* 1112 */     this.m20 = (float)arrayOfDouble2[6];
/* 1113 */     this.m21 = (float)arrayOfDouble2[7];
/* 1114 */     this.m22 = (float)arrayOfDouble2[8];
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
/*      */   static boolean luDecomposition(double[] paramArrayOfDouble, int[] paramArrayOfInt) {
/* 1141 */     double[] arrayOfDouble = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1149 */     byte b3 = 0;
/* 1150 */     byte b4 = 0;
/*      */ 
/*      */     
/* 1153 */     byte b1 = 3;
/* 1154 */     while (b1-- != 0) {
/* 1155 */       double d = 0.0D;
/*      */ 
/*      */       
/* 1158 */       byte b = 3;
/* 1159 */       while (b-- != 0) {
/* 1160 */         double d1 = paramArrayOfDouble[b3++];
/* 1161 */         d1 = Math.abs(d1);
/* 1162 */         if (d1 > d) {
/* 1163 */           d = d1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1168 */       if (d == 0.0D) {
/* 1169 */         return false;
/*      */       }
/* 1171 */       arrayOfDouble[b4++] = 1.0D / d;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1179 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1182 */     for (b1 = 0; b1 < 3; b1++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1188 */       for (b3 = 0; b3 < b1; b3++) {
/* 1189 */         byte b6 = b2 + 3 * b3 + b1;
/* 1190 */         double d1 = paramArrayOfDouble[b6];
/* 1191 */         byte b5 = b3;
/* 1192 */         byte b7 = b2 + 3 * b3;
/* 1193 */         byte b8 = b2 + b1;
/* 1194 */         while (b5-- != 0) {
/* 1195 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 1196 */           b7++;
/* 1197 */           b8 += 3;
/*      */         } 
/* 1199 */         paramArrayOfDouble[b6] = d1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1204 */       double d = 0.0D;
/* 1205 */       b4 = -1;
/* 1206 */       for (b3 = b1; b3 < 3; b3++) {
/* 1207 */         byte b6 = b2 + 3 * b3 + b1;
/* 1208 */         double d1 = paramArrayOfDouble[b6];
/* 1209 */         byte b5 = b1;
/* 1210 */         byte b7 = b2 + 3 * b3;
/* 1211 */         byte b8 = b2 + b1;
/* 1212 */         while (b5-- != 0) {
/* 1213 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 1214 */           b7++;
/* 1215 */           b8 += 3;
/*      */         } 
/* 1217 */         paramArrayOfDouble[b6] = d1;
/*      */         
/*      */         double d2;
/* 1220 */         if ((d2 = arrayOfDouble[b3] * Math.abs(d1)) >= d) {
/* 1221 */           d = d2;
/* 1222 */           b4 = b3;
/*      */         } 
/*      */       } 
/*      */       
/* 1226 */       if (b4 < 0) {
/* 1227 */         throw new RuntimeException(VecMathI18N.getString("Matrix3f13"));
/*      */       }
/*      */ 
/*      */       
/* 1231 */       if (b1 != b4) {
/*      */         
/* 1233 */         byte b = 3;
/* 1234 */         byte b5 = b2 + 3 * b4;
/* 1235 */         byte b6 = b2 + 3 * b1;
/* 1236 */         while (b-- != 0) {
/* 1237 */           double d1 = paramArrayOfDouble[b5];
/* 1238 */           paramArrayOfDouble[b5++] = paramArrayOfDouble[b6];
/* 1239 */           paramArrayOfDouble[b6++] = d1;
/*      */         } 
/*      */ 
/*      */         
/* 1243 */         arrayOfDouble[b4] = arrayOfDouble[b1];
/*      */       } 
/*      */ 
/*      */       
/* 1247 */       paramArrayOfInt[b1] = b4;
/*      */ 
/*      */       
/* 1250 */       if (paramArrayOfDouble[b2 + 3 * b1 + b1] == 0.0D) {
/* 1251 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1255 */       if (b1 != 2) {
/* 1256 */         double d1 = 1.0D / paramArrayOfDouble[b2 + 3 * b1 + b1];
/* 1257 */         byte b = b2 + 3 * (b1 + 1) + b1;
/* 1258 */         b3 = 2 - b1;
/* 1259 */         while (b3-- != 0) {
/* 1260 */           paramArrayOfDouble[b] = paramArrayOfDouble[b] * d1;
/* 1261 */           b += 3;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1267 */     return true;
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
/*      */   static void luBacksubstitution(double[] paramArrayOfDouble1, int[] paramArrayOfInt, double[] paramArrayOfDouble2) {
/* 1297 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1300 */     for (byte b1 = 0; b1 < 3; b1++) {
/*      */       
/* 1302 */       int i = b1;
/* 1303 */       byte b4 = -1;
/*      */ 
/*      */       
/* 1306 */       for (byte b3 = 0; b3 < 3; b3++) {
/*      */ 
/*      */         
/* 1309 */         int j = paramArrayOfInt[b2 + b3];
/* 1310 */         double d = paramArrayOfDouble2[i + 3 * j];
/* 1311 */         paramArrayOfDouble2[i + 3 * j] = paramArrayOfDouble2[i + 3 * b3];
/* 1312 */         if (b4 >= 0) {
/*      */           
/* 1314 */           byte b6 = b3 * 3;
/* 1315 */           for (byte b5 = b4; b5 <= b3 - 1; b5++) {
/* 1316 */             d -= paramArrayOfDouble1[b6 + b5] * paramArrayOfDouble2[i + 3 * b5];
/*      */           }
/*      */         }
/* 1319 */         else if (d != 0.0D) {
/* 1320 */           b4 = b3;
/*      */         } 
/* 1322 */         paramArrayOfDouble2[i + 3 * b3] = d;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1327 */       byte b = 6;
/* 1328 */       paramArrayOfDouble2[i + 6] = paramArrayOfDouble2[i + 6] / paramArrayOfDouble1[b + 2];
/*      */       
/* 1330 */       b -= 3;
/* 1331 */       paramArrayOfDouble2[i + 3] = (paramArrayOfDouble2[i + 3] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 6]) / paramArrayOfDouble1[b + 1];
/*      */ 
/*      */       
/* 1334 */       b -= 3;
/* 1335 */       paramArrayOfDouble2[i + 0] = (paramArrayOfDouble2[i + 0] - paramArrayOfDouble1[b + 1] * paramArrayOfDouble2[i + 3] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 6]) / paramArrayOfDouble1[b + 0];
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
/* 1348 */   public final float determinant() { return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(float paramFloat) {
/* 1361 */     this.m00 = paramFloat;
/* 1362 */     this.m01 = 0.0F;
/* 1363 */     this.m02 = 0.0F;
/*      */     
/* 1365 */     this.m10 = 0.0F;
/* 1366 */     this.m11 = paramFloat;
/* 1367 */     this.m12 = 0.0F;
/*      */     
/* 1369 */     this.m20 = 0.0F;
/* 1370 */     this.m21 = 0.0F;
/* 1371 */     this.m22 = paramFloat;
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
/*      */   public final void rotX(float paramFloat) {
/* 1383 */     float f1 = (float)Math.sin(paramFloat);
/* 1384 */     float f2 = (float)Math.cos(paramFloat);
/*      */     
/* 1386 */     this.m00 = 1.0F;
/* 1387 */     this.m01 = 0.0F;
/* 1388 */     this.m02 = 0.0F;
/*      */     
/* 1390 */     this.m10 = 0.0F;
/* 1391 */     this.m11 = f2;
/* 1392 */     this.m12 = -f1;
/*      */     
/* 1394 */     this.m20 = 0.0F;
/* 1395 */     this.m21 = f1;
/* 1396 */     this.m22 = f2;
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
/*      */   public final void rotY(float paramFloat) {
/* 1408 */     float f1 = (float)Math.sin(paramFloat);
/* 1409 */     float f2 = (float)Math.cos(paramFloat);
/*      */     
/* 1411 */     this.m00 = f2;
/* 1412 */     this.m01 = 0.0F;
/* 1413 */     this.m02 = f1;
/*      */     
/* 1415 */     this.m10 = 0.0F;
/* 1416 */     this.m11 = 1.0F;
/* 1417 */     this.m12 = 0.0F;
/*      */     
/* 1419 */     this.m20 = -f1;
/* 1420 */     this.m21 = 0.0F;
/* 1421 */     this.m22 = f2;
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
/*      */   public final void rotZ(float paramFloat) {
/* 1433 */     float f1 = (float)Math.sin(paramFloat);
/* 1434 */     float f2 = (float)Math.cos(paramFloat);
/*      */     
/* 1436 */     this.m00 = f2;
/* 1437 */     this.m01 = -f1;
/* 1438 */     this.m02 = 0.0F;
/*      */     
/* 1440 */     this.m10 = f1;
/* 1441 */     this.m11 = f2;
/* 1442 */     this.m12 = 0.0F;
/*      */     
/* 1444 */     this.m20 = 0.0F;
/* 1445 */     this.m21 = 0.0F;
/* 1446 */     this.m22 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(float paramFloat) {
/* 1455 */     this.m00 *= paramFloat;
/* 1456 */     this.m01 *= paramFloat;
/* 1457 */     this.m02 *= paramFloat;
/*      */     
/* 1459 */     this.m10 *= paramFloat;
/* 1460 */     this.m11 *= paramFloat;
/* 1461 */     this.m12 *= paramFloat;
/*      */     
/* 1463 */     this.m20 *= paramFloat;
/* 1464 */     this.m21 *= paramFloat;
/* 1465 */     this.m22 *= paramFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(float paramFloat, Matrix3f paramMatrix3f) {
/* 1476 */     this.m00 = paramFloat * paramMatrix3f.m00;
/* 1477 */     this.m01 = paramFloat * paramMatrix3f.m01;
/* 1478 */     this.m02 = paramFloat * paramMatrix3f.m02;
/*      */     
/* 1480 */     this.m10 = paramFloat * paramMatrix3f.m10;
/* 1481 */     this.m11 = paramFloat * paramMatrix3f.m11;
/* 1482 */     this.m12 = paramFloat * paramMatrix3f.m12;
/*      */     
/* 1484 */     this.m20 = paramFloat * paramMatrix3f.m20;
/* 1485 */     this.m21 = paramFloat * paramMatrix3f.m21;
/* 1486 */     this.m22 = paramFloat * paramMatrix3f.m22;
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
/*      */   public final void mul(Matrix3f paramMatrix3f) {
/* 1501 */     float f1 = this.m00 * paramMatrix3f.m00 + this.m01 * paramMatrix3f.m10 + this.m02 * paramMatrix3f.m20;
/* 1502 */     float f2 = this.m00 * paramMatrix3f.m01 + this.m01 * paramMatrix3f.m11 + this.m02 * paramMatrix3f.m21;
/* 1503 */     float f3 = this.m00 * paramMatrix3f.m02 + this.m01 * paramMatrix3f.m12 + this.m02 * paramMatrix3f.m22;
/*      */     
/* 1505 */     float f4 = this.m10 * paramMatrix3f.m00 + this.m11 * paramMatrix3f.m10 + this.m12 * paramMatrix3f.m20;
/* 1506 */     float f5 = this.m10 * paramMatrix3f.m01 + this.m11 * paramMatrix3f.m11 + this.m12 * paramMatrix3f.m21;
/* 1507 */     float f6 = this.m10 * paramMatrix3f.m02 + this.m11 * paramMatrix3f.m12 + this.m12 * paramMatrix3f.m22;
/*      */     
/* 1509 */     float f7 = this.m20 * paramMatrix3f.m00 + this.m21 * paramMatrix3f.m10 + this.m22 * paramMatrix3f.m20;
/* 1510 */     float f8 = this.m20 * paramMatrix3f.m01 + this.m21 * paramMatrix3f.m11 + this.m22 * paramMatrix3f.m21;
/* 1511 */     float f9 = this.m20 * paramMatrix3f.m02 + this.m21 * paramMatrix3f.m12 + this.m22 * paramMatrix3f.m22;
/*      */     
/* 1513 */     this.m00 = f1; this.m01 = f2; this.m02 = f3;
/* 1514 */     this.m10 = f4; this.m11 = f5; this.m12 = f6;
/* 1515 */     this.m20 = f7; this.m21 = f8; this.m22 = f9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2) {
/* 1526 */     if (this != paramMatrix3f1 && this != paramMatrix3f2) {
/* 1527 */       this.m00 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m02 * paramMatrix3f2.m20;
/* 1528 */       this.m01 = paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m21;
/* 1529 */       this.m02 = paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m01 * paramMatrix3f2.m12 + paramMatrix3f1.m02 * paramMatrix3f2.m22;
/*      */       
/* 1531 */       this.m10 = paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m20;
/* 1532 */       this.m11 = paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m21;
/* 1533 */       this.m12 = paramMatrix3f1.m10 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m12 * paramMatrix3f2.m22;
/*      */       
/* 1535 */       this.m20 = paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20;
/* 1536 */       this.m21 = paramMatrix3f1.m20 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21;
/* 1537 */       this.m22 = paramMatrix3f1.m20 * paramMatrix3f2.m02 + paramMatrix3f1.m21 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1543 */       float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m02 * paramMatrix3f2.m20;
/* 1544 */       float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m21;
/* 1545 */       float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m01 * paramMatrix3f2.m12 + paramMatrix3f1.m02 * paramMatrix3f2.m22;
/*      */       
/* 1547 */       float f4 = paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m20;
/* 1548 */       float f5 = paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m21;
/* 1549 */       float f6 = paramMatrix3f1.m10 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m12 * paramMatrix3f2.m22;
/*      */       
/* 1551 */       float f7 = paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20;
/* 1552 */       float f8 = paramMatrix3f1.m20 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21;
/* 1553 */       float f9 = paramMatrix3f1.m20 * paramMatrix3f2.m02 + paramMatrix3f1.m21 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */       
/* 1555 */       this.m00 = f1; this.m01 = f2; this.m02 = f3;
/* 1556 */       this.m10 = f4; this.m11 = f5; this.m12 = f6;
/* 1557 */       this.m20 = f7; this.m21 = f8; this.m22 = f9;
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
/*      */   public final void mulNormalize(Matrix3f paramMatrix3f) {
/* 1569 */     double[] arrayOfDouble1 = new double[9];
/* 1570 */     double[] arrayOfDouble2 = new double[9];
/* 1571 */     double[] arrayOfDouble3 = new double[3];
/*      */     
/* 1573 */     arrayOfDouble1[0] = (this.m00 * paramMatrix3f.m00 + this.m01 * paramMatrix3f.m10 + this.m02 * paramMatrix3f.m20);
/* 1574 */     arrayOfDouble1[1] = (this.m00 * paramMatrix3f.m01 + this.m01 * paramMatrix3f.m11 + this.m02 * paramMatrix3f.m21);
/* 1575 */     arrayOfDouble1[2] = (this.m00 * paramMatrix3f.m02 + this.m01 * paramMatrix3f.m12 + this.m02 * paramMatrix3f.m22);
/*      */     
/* 1577 */     arrayOfDouble1[3] = (this.m10 * paramMatrix3f.m00 + this.m11 * paramMatrix3f.m10 + this.m12 * paramMatrix3f.m20);
/* 1578 */     arrayOfDouble1[4] = (this.m10 * paramMatrix3f.m01 + this.m11 * paramMatrix3f.m11 + this.m12 * paramMatrix3f.m21);
/* 1579 */     arrayOfDouble1[5] = (this.m10 * paramMatrix3f.m02 + this.m11 * paramMatrix3f.m12 + this.m12 * paramMatrix3f.m22);
/*      */     
/* 1581 */     arrayOfDouble1[6] = (this.m20 * paramMatrix3f.m00 + this.m21 * paramMatrix3f.m10 + this.m22 * paramMatrix3f.m20);
/* 1582 */     arrayOfDouble1[7] = (this.m20 * paramMatrix3f.m01 + this.m21 * paramMatrix3f.m11 + this.m22 * paramMatrix3f.m21);
/* 1583 */     arrayOfDouble1[8] = (this.m20 * paramMatrix3f.m02 + this.m21 * paramMatrix3f.m12 + this.m22 * paramMatrix3f.m22);
/*      */     
/* 1585 */     Matrix3d.compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
/*      */     
/* 1587 */     this.m00 = (float)arrayOfDouble2[0];
/* 1588 */     this.m01 = (float)arrayOfDouble2[1];
/* 1589 */     this.m02 = (float)arrayOfDouble2[2];
/*      */     
/* 1591 */     this.m10 = (float)arrayOfDouble2[3];
/* 1592 */     this.m11 = (float)arrayOfDouble2[4];
/* 1593 */     this.m12 = (float)arrayOfDouble2[5];
/*      */     
/* 1595 */     this.m20 = (float)arrayOfDouble2[6];
/* 1596 */     this.m21 = (float)arrayOfDouble2[7];
/* 1597 */     this.m22 = (float)arrayOfDouble2[8];
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
/*      */   public final void mulNormalize(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2) {
/* 1610 */     double[] arrayOfDouble1 = new double[9];
/* 1611 */     double[] arrayOfDouble2 = new double[9];
/* 1612 */     double[] arrayOfDouble3 = new double[3];
/*      */ 
/*      */     
/* 1615 */     arrayOfDouble1[0] = (paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m02 * paramMatrix3f2.m20);
/* 1616 */     arrayOfDouble1[1] = (paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m21);
/* 1617 */     arrayOfDouble1[2] = (paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m01 * paramMatrix3f2.m12 + paramMatrix3f1.m02 * paramMatrix3f2.m22);
/*      */     
/* 1619 */     arrayOfDouble1[3] = (paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m20);
/* 1620 */     arrayOfDouble1[4] = (paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m21);
/* 1621 */     arrayOfDouble1[5] = (paramMatrix3f1.m10 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m12 * paramMatrix3f2.m22);
/*      */     
/* 1623 */     arrayOfDouble1[6] = (paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20);
/* 1624 */     arrayOfDouble1[7] = (paramMatrix3f1.m20 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21);
/* 1625 */     arrayOfDouble1[8] = (paramMatrix3f1.m20 * paramMatrix3f2.m02 + paramMatrix3f1.m21 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22);
/*      */     
/* 1627 */     Matrix3d.compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
/*      */     
/* 1629 */     this.m00 = (float)arrayOfDouble2[0];
/* 1630 */     this.m01 = (float)arrayOfDouble2[1];
/* 1631 */     this.m02 = (float)arrayOfDouble2[2];
/*      */     
/* 1633 */     this.m10 = (float)arrayOfDouble2[3];
/* 1634 */     this.m11 = (float)arrayOfDouble2[4];
/* 1635 */     this.m12 = (float)arrayOfDouble2[5];
/*      */     
/* 1637 */     this.m20 = (float)arrayOfDouble2[6];
/* 1638 */     this.m21 = (float)arrayOfDouble2[7];
/* 1639 */     this.m22 = (float)arrayOfDouble2[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mulTransposeBoth(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2) {
/* 1650 */     if (this != paramMatrix3f1 && this != paramMatrix3f2) {
/* 1651 */       this.m00 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m20 * paramMatrix3f2.m02;
/* 1652 */       this.m01 = paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m12;
/* 1653 */       this.m02 = paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m10 * paramMatrix3f2.m21 + paramMatrix3f1.m20 * paramMatrix3f2.m22;
/*      */       
/* 1655 */       this.m10 = paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m02;
/* 1656 */       this.m11 = paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m12;
/* 1657 */       this.m12 = paramMatrix3f1.m01 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m21 * paramMatrix3f2.m22;
/*      */       
/* 1659 */       this.m20 = paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02;
/* 1660 */       this.m21 = paramMatrix3f1.m02 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12;
/* 1661 */       this.m22 = paramMatrix3f1.m02 * paramMatrix3f2.m20 + paramMatrix3f1.m12 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1667 */       float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m20 * paramMatrix3f2.m02;
/* 1668 */       float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m12;
/* 1669 */       float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m10 * paramMatrix3f2.m21 + paramMatrix3f1.m20 * paramMatrix3f2.m22;
/*      */       
/* 1671 */       float f4 = paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m02;
/* 1672 */       float f5 = paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m12;
/* 1673 */       float f6 = paramMatrix3f1.m01 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m21 * paramMatrix3f2.m22;
/*      */       
/* 1675 */       float f7 = paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02;
/* 1676 */       float f8 = paramMatrix3f1.m02 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12;
/* 1677 */       float f9 = paramMatrix3f1.m02 * paramMatrix3f2.m20 + paramMatrix3f1.m12 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */       
/* 1679 */       this.m00 = f1; this.m01 = f2; this.m02 = f3;
/* 1680 */       this.m10 = f4; this.m11 = f5; this.m12 = f6;
/* 1681 */       this.m20 = f7; this.m21 = f8; this.m22 = f9;
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
/*      */   public final void mulTransposeRight(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2) {
/* 1695 */     if (this != paramMatrix3f1 && this != paramMatrix3f2) {
/* 1696 */       this.m00 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m02 * paramMatrix3f2.m02;
/* 1697 */       this.m01 = paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m12;
/* 1698 */       this.m02 = paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m01 * paramMatrix3f2.m21 + paramMatrix3f1.m02 * paramMatrix3f2.m22;
/*      */       
/* 1700 */       this.m10 = paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m02;
/* 1701 */       this.m11 = paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m12;
/* 1702 */       this.m12 = paramMatrix3f1.m10 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m12 * paramMatrix3f2.m22;
/*      */       
/* 1704 */       this.m20 = paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02;
/* 1705 */       this.m21 = paramMatrix3f1.m20 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12;
/* 1706 */       this.m22 = paramMatrix3f1.m20 * paramMatrix3f2.m20 + paramMatrix3f1.m21 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1712 */       float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m02 * paramMatrix3f2.m02;
/* 1713 */       float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m12;
/* 1714 */       float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m01 * paramMatrix3f2.m21 + paramMatrix3f1.m02 * paramMatrix3f2.m22;
/*      */       
/* 1716 */       float f4 = paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m02;
/* 1717 */       float f5 = paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m12;
/* 1718 */       float f6 = paramMatrix3f1.m10 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m12 * paramMatrix3f2.m22;
/*      */       
/* 1720 */       float f7 = paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02;
/* 1721 */       float f8 = paramMatrix3f1.m20 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12;
/* 1722 */       float f9 = paramMatrix3f1.m20 * paramMatrix3f2.m20 + paramMatrix3f1.m21 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */       
/* 1724 */       this.m00 = f1; this.m01 = f2; this.m02 = f3;
/* 1725 */       this.m10 = f4; this.m11 = f5; this.m12 = f6;
/* 1726 */       this.m20 = f7; this.m21 = f8; this.m22 = f9;
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
/*      */   public final void mulTransposeLeft(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2) {
/* 1738 */     if (this != paramMatrix3f1 && this != paramMatrix3f2) {
/* 1739 */       this.m00 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m20 * paramMatrix3f2.m20;
/* 1740 */       this.m01 = paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m21;
/* 1741 */       this.m02 = paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m10 * paramMatrix3f2.m12 + paramMatrix3f1.m20 * paramMatrix3f2.m22;
/*      */       
/* 1743 */       this.m10 = paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m20;
/* 1744 */       this.m11 = paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m21;
/* 1745 */       this.m12 = paramMatrix3f1.m01 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m21 * paramMatrix3f2.m22;
/*      */       
/* 1747 */       this.m20 = paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20;
/* 1748 */       this.m21 = paramMatrix3f1.m02 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21;
/* 1749 */       this.m22 = paramMatrix3f1.m02 * paramMatrix3f2.m02 + paramMatrix3f1.m12 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1755 */       float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m20 * paramMatrix3f2.m20;
/* 1756 */       float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m21;
/* 1757 */       float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m10 * paramMatrix3f2.m12 + paramMatrix3f1.m20 * paramMatrix3f2.m22;
/*      */       
/* 1759 */       float f4 = paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m20;
/* 1760 */       float f5 = paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m21;
/* 1761 */       float f6 = paramMatrix3f1.m01 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m21 * paramMatrix3f2.m22;
/*      */       
/* 1763 */       float f7 = paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20;
/* 1764 */       float f8 = paramMatrix3f1.m02 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21;
/* 1765 */       float f9 = paramMatrix3f1.m02 * paramMatrix3f2.m02 + paramMatrix3f1.m12 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
/*      */       
/* 1767 */       this.m00 = f1; this.m01 = f2; this.m02 = f3;
/* 1768 */       this.m10 = f4; this.m11 = f5; this.m12 = f6;
/* 1769 */       this.m20 = f7; this.m21 = f8; this.m22 = f9;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalize() {
/* 1778 */     double[] arrayOfDouble1 = new double[9];
/* 1779 */     double[] arrayOfDouble2 = new double[3];
/* 1780 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 1782 */     this.m00 = (float)arrayOfDouble1[0];
/* 1783 */     this.m01 = (float)arrayOfDouble1[1];
/* 1784 */     this.m02 = (float)arrayOfDouble1[2];
/*      */     
/* 1786 */     this.m10 = (float)arrayOfDouble1[3];
/* 1787 */     this.m11 = (float)arrayOfDouble1[4];
/* 1788 */     this.m12 = (float)arrayOfDouble1[5];
/*      */     
/* 1790 */     this.m20 = (float)arrayOfDouble1[6];
/* 1791 */     this.m21 = (float)arrayOfDouble1[7];
/* 1792 */     this.m22 = (float)arrayOfDouble1[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalize(Matrix3f paramMatrix3f) {
/* 1802 */     double[] arrayOfDouble1 = new double[9];
/* 1803 */     double[] arrayOfDouble2 = new double[9];
/* 1804 */     double[] arrayOfDouble3 = new double[3];
/*      */     
/* 1806 */     arrayOfDouble1[0] = paramMatrix3f.m00;
/* 1807 */     arrayOfDouble1[1] = paramMatrix3f.m01;
/* 1808 */     arrayOfDouble1[2] = paramMatrix3f.m02;
/*      */     
/* 1810 */     arrayOfDouble1[3] = paramMatrix3f.m10;
/* 1811 */     arrayOfDouble1[4] = paramMatrix3f.m11;
/* 1812 */     arrayOfDouble1[5] = paramMatrix3f.m12;
/*      */     
/* 1814 */     arrayOfDouble1[6] = paramMatrix3f.m20;
/* 1815 */     arrayOfDouble1[7] = paramMatrix3f.m21;
/* 1816 */     arrayOfDouble1[8] = paramMatrix3f.m22;
/*      */     
/* 1818 */     Matrix3d.compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
/*      */     
/* 1820 */     this.m00 = (float)arrayOfDouble2[0];
/* 1821 */     this.m01 = (float)arrayOfDouble2[1];
/* 1822 */     this.m02 = (float)arrayOfDouble2[2];
/*      */     
/* 1824 */     this.m10 = (float)arrayOfDouble2[3];
/* 1825 */     this.m11 = (float)arrayOfDouble2[4];
/* 1826 */     this.m12 = (float)arrayOfDouble2[5];
/*      */     
/* 1828 */     this.m20 = (float)arrayOfDouble2[6];
/* 1829 */     this.m21 = (float)arrayOfDouble2[7];
/* 1830 */     this.m22 = (float)arrayOfDouble2[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalizeCP() {
/* 1839 */     float f = 1.0F / (float)Math.sqrt((this.m00 * this.m00 + this.m10 * this.m10 + this.m20 * this.m20));
/* 1840 */     this.m00 *= f;
/* 1841 */     this.m10 *= f;
/* 1842 */     this.m20 *= f;
/*      */     
/* 1844 */     f = 1.0F / (float)Math.sqrt((this.m01 * this.m01 + this.m11 * this.m11 + this.m21 * this.m21));
/* 1845 */     this.m01 *= f;
/* 1846 */     this.m11 *= f;
/* 1847 */     this.m21 *= f;
/*      */     
/* 1849 */     this.m02 = this.m10 * this.m21 - this.m11 * this.m20;
/* 1850 */     this.m12 = this.m01 * this.m20 - this.m00 * this.m21;
/* 1851 */     this.m22 = this.m00 * this.m11 - this.m01 * this.m10;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalizeCP(Matrix3f paramMatrix3f) {
/* 1862 */     float f = 1.0F / (float)Math.sqrt((paramMatrix3f.m00 * paramMatrix3f.m00 + paramMatrix3f.m10 * paramMatrix3f.m10 + paramMatrix3f.m20 * paramMatrix3f.m20));
/* 1863 */     paramMatrix3f.m00 *= f;
/* 1864 */     paramMatrix3f.m10 *= f;
/* 1865 */     paramMatrix3f.m20 *= f;
/*      */     
/* 1867 */     f = 1.0F / (float)Math.sqrt((paramMatrix3f.m01 * paramMatrix3f.m01 + paramMatrix3f.m11 * paramMatrix3f.m11 + paramMatrix3f.m21 * paramMatrix3f.m21));
/* 1868 */     paramMatrix3f.m01 *= f;
/* 1869 */     paramMatrix3f.m11 *= f;
/* 1870 */     paramMatrix3f.m21 *= f;
/*      */     
/* 1872 */     this.m02 = this.m10 * this.m21 - this.m11 * this.m20;
/* 1873 */     this.m12 = this.m01 * this.m20 - this.m00 * this.m21;
/* 1874 */     this.m22 = this.m00 * this.m11 - this.m01 * this.m10;
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
/*      */   public boolean equals(Matrix3f paramMatrix3f) {
/*      */     try {
/* 1888 */       return (this.m00 == paramMatrix3f.m00 && this.m01 == paramMatrix3f.m01 && this.m02 == paramMatrix3f.m02 && this.m10 == paramMatrix3f.m10 && this.m11 == paramMatrix3f.m11 && this.m12 == paramMatrix3f.m12 && this.m20 == paramMatrix3f.m20 && this.m21 == paramMatrix3f.m21 && this.m22 == paramMatrix3f.m22);
/*      */     }
/*      */     catch (NullPointerException nullPointerException) {
/*      */       
/* 1892 */       return false;
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
/*      */   public boolean equals(Object paramObject) {
/*      */     
/* 1907 */     try { Matrix3f matrix3f = (Matrix3f)paramObject;
/* 1908 */       return (this.m00 == matrix3f.m00 && this.m01 == matrix3f.m01 && this.m02 == matrix3f.m02 && this.m10 == matrix3f.m10 && this.m11 == matrix3f.m11 && this.m12 == matrix3f.m12 && this.m20 == matrix3f.m20 && this.m21 == matrix3f.m21 && this.m22 == matrix3f.m22); }
/*      */     
/*      */     catch (ClassCastException classCastException)
/*      */     
/* 1912 */     { return false; }
/* 1913 */     catch (NullPointerException nullPointerException) { return false; }
/*      */   
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
/*      */   public boolean epsilonEquals(Matrix3f paramMatrix3f, float paramFloat) {
/* 1927 */     boolean bool = true;
/*      */     
/* 1929 */     if (Math.abs(this.m00 - paramMatrix3f.m00) > paramFloat) bool = false; 
/* 1930 */     if (Math.abs(this.m01 - paramMatrix3f.m01) > paramFloat) bool = false; 
/* 1931 */     if (Math.abs(this.m02 - paramMatrix3f.m02) > paramFloat) bool = false;
/*      */     
/* 1933 */     if (Math.abs(this.m10 - paramMatrix3f.m10) > paramFloat) bool = false; 
/* 1934 */     if (Math.abs(this.m11 - paramMatrix3f.m11) > paramFloat) bool = false; 
/* 1935 */     if (Math.abs(this.m12 - paramMatrix3f.m12) > paramFloat) bool = false;
/*      */     
/* 1937 */     if (Math.abs(this.m20 - paramMatrix3f.m20) > paramFloat) bool = false; 
/* 1938 */     if (Math.abs(this.m21 - paramMatrix3f.m21) > paramFloat) bool = false; 
/* 1939 */     if (Math.abs(this.m22 - paramMatrix3f.m22) > paramFloat) bool = false;
/*      */     
/* 1941 */     return bool;
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
/*      */   public int hashCode() {
/* 1955 */     long l = 1L;
/* 1956 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m00);
/* 1957 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m01);
/* 1958 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m02);
/* 1959 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m10);
/* 1960 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m11);
/* 1961 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m12);
/* 1962 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m20);
/* 1963 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m21);
/* 1964 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m22);
/* 1965 */     return (int)(l ^ l >> 32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setZero() {
/* 1974 */     this.m00 = 0.0F;
/* 1975 */     this.m01 = 0.0F;
/* 1976 */     this.m02 = 0.0F;
/*      */     
/* 1978 */     this.m10 = 0.0F;
/* 1979 */     this.m11 = 0.0F;
/* 1980 */     this.m12 = 0.0F;
/*      */     
/* 1982 */     this.m20 = 0.0F;
/* 1983 */     this.m21 = 0.0F;
/* 1984 */     this.m22 = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate() {
/* 1993 */     this.m00 = -this.m00;
/* 1994 */     this.m01 = -this.m01;
/* 1995 */     this.m02 = -this.m02;
/*      */     
/* 1997 */     this.m10 = -this.m10;
/* 1998 */     this.m11 = -this.m11;
/* 1999 */     this.m12 = -this.m12;
/*      */     
/* 2001 */     this.m20 = -this.m20;
/* 2002 */     this.m21 = -this.m21;
/* 2003 */     this.m22 = -this.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate(Matrix3f paramMatrix3f) {
/* 2014 */     this.m00 = -paramMatrix3f.m00;
/* 2015 */     this.m01 = -paramMatrix3f.m01;
/* 2016 */     this.m02 = -paramMatrix3f.m02;
/*      */     
/* 2018 */     this.m10 = -paramMatrix3f.m10;
/* 2019 */     this.m11 = -paramMatrix3f.m11;
/* 2020 */     this.m12 = -paramMatrix3f.m12;
/*      */     
/* 2022 */     this.m20 = -paramMatrix3f.m20;
/* 2023 */     this.m21 = -paramMatrix3f.m21;
/* 2024 */     this.m22 = -paramMatrix3f.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Tuple3f paramTuple3f) {
/* 2035 */     float f1 = this.m00 * paramTuple3f.x + this.m01 * paramTuple3f.y + this.m02 * paramTuple3f.z;
/* 2036 */     float f2 = this.m10 * paramTuple3f.x + this.m11 * paramTuple3f.y + this.m12 * paramTuple3f.z;
/* 2037 */     float f3 = this.m20 * paramTuple3f.x + this.m21 * paramTuple3f.y + this.m22 * paramTuple3f.z;
/* 2038 */     paramTuple3f.set(f1, f2, f3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2) {
/* 2049 */     float f1 = this.m00 * paramTuple3f1.x + this.m01 * paramTuple3f1.y + this.m02 * paramTuple3f1.z;
/* 2050 */     float f2 = this.m10 * paramTuple3f1.x + this.m11 * paramTuple3f1.y + this.m12 * paramTuple3f1.z;
/* 2051 */     paramTuple3f2.z = this.m20 * paramTuple3f1.x + this.m21 * paramTuple3f1.y + this.m22 * paramTuple3f1.z;
/* 2052 */     paramTuple3f2.x = f1;
/* 2053 */     paramTuple3f2.y = f2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getScaleRotate(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 2061 */     double[] arrayOfDouble = new double[9];
/* 2062 */     arrayOfDouble[0] = this.m00;
/* 2063 */     arrayOfDouble[1] = this.m01;
/* 2064 */     arrayOfDouble[2] = this.m02;
/* 2065 */     arrayOfDouble[3] = this.m10;
/* 2066 */     arrayOfDouble[4] = this.m11;
/* 2067 */     arrayOfDouble[5] = this.m12;
/* 2068 */     arrayOfDouble[6] = this.m20;
/* 2069 */     arrayOfDouble[7] = this.m21;
/* 2070 */     arrayOfDouble[8] = this.m22;
/* 2071 */     Matrix3d.compute_svd(arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2);
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
/*      */   public Object clone() {
/* 2086 */     Matrix3f matrix3f = null;
/*      */     try {
/* 2088 */       matrix3f = (Matrix3f)super.clone();
/* 2089 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*      */       
/* 2091 */       throw new InternalError();
/*      */     } 
/* 2093 */     return matrix3f;
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
/* 2105 */   public final float getM00() { return this.m00; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2116 */   public final void setM00(float paramFloat) { this.m00 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2128 */   public final float getM01() { return this.m01; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2139 */   public final void setM01(float paramFloat) { this.m01 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2150 */   public final float getM02() { return this.m02; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2161 */   public final void setM02(float paramFloat) { this.m02 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2172 */   public final float getM10() { return this.m10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2183 */   public final void setM10(float paramFloat) { this.m10 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2194 */   public final float getM11() { return this.m11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2205 */   public final void setM11(float paramFloat) { this.m11 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2216 */   public final float getM12() { return this.m12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2225 */   public final void setM12(float paramFloat) { this.m12 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2236 */   public final float getM20() { return this.m20; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2247 */   public final void setM20(float paramFloat) { this.m20 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2258 */   public final float getM21() { return this.m21; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2269 */   public final void setM21(float paramFloat) { this.m21 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2280 */   public final float getM22() { return this.m22; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2291 */   public final void setM22(float paramFloat) { this.m22 = paramFloat; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Matrix3f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */