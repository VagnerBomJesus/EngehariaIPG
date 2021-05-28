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
/*      */ public class Matrix3d
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   static final long serialVersionUID = 6837536777072402710L;
/*      */   public double m00;
/*      */   public double m01;
/*      */   public double m02;
/*      */   public double m10;
/*      */   public double m11;
/*      */   public double m12;
/*      */   public double m20;
/*      */   public double m21;
/*      */   public double m22;
/*      */   private static final double EPS = 1.110223024E-16D;
/*      */   private static final double ERR_EPS = 1.0E-8D;
/*      */   private static double xin;
/*      */   private static double yin;
/*      */   private static double zin;
/*      */   private static double xout;
/*      */   private static double yout;
/*      */   private static double zout;
/*      */   
/*      */   public Matrix3d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9) {
/*   95 */     this.m00 = paramDouble1;
/*   96 */     this.m01 = paramDouble2;
/*   97 */     this.m02 = paramDouble3;
/*      */     
/*   99 */     this.m10 = paramDouble4;
/*  100 */     this.m11 = paramDouble5;
/*  101 */     this.m12 = paramDouble6;
/*      */     
/*  103 */     this.m20 = paramDouble7;
/*  104 */     this.m21 = paramDouble8;
/*  105 */     this.m22 = paramDouble9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix3d(double[] paramArrayOfDouble) {
/*  116 */     this.m00 = paramArrayOfDouble[0];
/*  117 */     this.m01 = paramArrayOfDouble[1];
/*  118 */     this.m02 = paramArrayOfDouble[2];
/*      */     
/*  120 */     this.m10 = paramArrayOfDouble[3];
/*  121 */     this.m11 = paramArrayOfDouble[4];
/*  122 */     this.m12 = paramArrayOfDouble[5];
/*      */     
/*  124 */     this.m20 = paramArrayOfDouble[6];
/*  125 */     this.m21 = paramArrayOfDouble[7];
/*  126 */     this.m22 = paramArrayOfDouble[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix3d(Matrix3d paramMatrix3d) {
/*  137 */     this.m00 = paramMatrix3d.m00;
/*  138 */     this.m01 = paramMatrix3d.m01;
/*  139 */     this.m02 = paramMatrix3d.m02;
/*      */     
/*  141 */     this.m10 = paramMatrix3d.m10;
/*  142 */     this.m11 = paramMatrix3d.m11;
/*  143 */     this.m12 = paramMatrix3d.m12;
/*      */     
/*  145 */     this.m20 = paramMatrix3d.m20;
/*  146 */     this.m21 = paramMatrix3d.m21;
/*  147 */     this.m22 = paramMatrix3d.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix3d(Matrix3f paramMatrix3f) {
/*  158 */     this.m00 = paramMatrix3f.m00;
/*  159 */     this.m01 = paramMatrix3f.m01;
/*  160 */     this.m02 = paramMatrix3f.m02;
/*      */     
/*  162 */     this.m10 = paramMatrix3f.m10;
/*  163 */     this.m11 = paramMatrix3f.m11;
/*  164 */     this.m12 = paramMatrix3f.m12;
/*      */     
/*  166 */     this.m20 = paramMatrix3f.m20;
/*  167 */     this.m21 = paramMatrix3f.m21;
/*  168 */     this.m22 = paramMatrix3f.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix3d() {
/*  177 */     this.m00 = 0.0D;
/*  178 */     this.m01 = 0.0D;
/*  179 */     this.m02 = 0.0D;
/*      */     
/*  181 */     this.m10 = 0.0D;
/*  182 */     this.m11 = 0.0D;
/*  183 */     this.m12 = 0.0D;
/*      */     
/*  185 */     this.m20 = 0.0D;
/*  186 */     this.m21 = 0.0D;
/*  187 */     this.m22 = 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   public String toString() { return this.m00 + ", " + this.m01 + ", " + this.m02 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + "\n"; }
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
/*  207 */     this.m00 = 1.0D;
/*  208 */     this.m01 = 0.0D;
/*  209 */     this.m02 = 0.0D;
/*      */     
/*  211 */     this.m10 = 0.0D;
/*  212 */     this.m11 = 1.0D;
/*  213 */     this.m12 = 0.0D;
/*      */     
/*  215 */     this.m20 = 0.0D;
/*  216 */     this.m21 = 0.0D;
/*  217 */     this.m22 = 1.0D;
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
/*      */   public final void setScale(double paramDouble) {
/*  229 */     double[] arrayOfDouble1 = new double[9];
/*  230 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  232 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  234 */     this.m00 = arrayOfDouble1[0] * paramDouble;
/*  235 */     this.m01 = arrayOfDouble1[1] * paramDouble;
/*  236 */     this.m02 = arrayOfDouble1[2] * paramDouble;
/*      */     
/*  238 */     this.m10 = arrayOfDouble1[3] * paramDouble;
/*  239 */     this.m11 = arrayOfDouble1[4] * paramDouble;
/*  240 */     this.m12 = arrayOfDouble1[5] * paramDouble;
/*      */     
/*  242 */     this.m20 = arrayOfDouble1[6] * paramDouble;
/*  243 */     this.m21 = arrayOfDouble1[7] * paramDouble;
/*  244 */     this.m22 = arrayOfDouble1[8] * paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setElement(int paramInt1, int paramInt2, double paramDouble) {
/*  255 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  258 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  261 */             this.m00 = paramDouble;
/*      */             return;
/*      */           case 1:
/*  264 */             this.m01 = paramDouble;
/*      */             return;
/*      */           case 2:
/*  267 */             this.m02 = paramDouble;
/*      */             return;
/*      */         } 
/*  270 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  275 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  278 */             this.m10 = paramDouble;
/*      */             return;
/*      */           case 1:
/*  281 */             this.m11 = paramDouble;
/*      */             return;
/*      */           case 2:
/*  284 */             this.m12 = paramDouble;
/*      */             return;
/*      */         } 
/*  287 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  293 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  296 */             this.m20 = paramDouble;
/*      */             return;
/*      */           case 1:
/*  299 */             this.m21 = paramDouble;
/*      */             return;
/*      */           case 2:
/*  302 */             this.m22 = paramDouble;
/*      */             return;
/*      */         } 
/*  305 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  310 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
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
/*      */   public final double getElement(int paramInt1, int paramInt2) {
/*  323 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  326 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  329 */             return this.m00;
/*      */           case 1:
/*  331 */             return this.m01;
/*      */           case 2:
/*  333 */             return this.m02;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case 1:
/*  339 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  342 */             return this.m10;
/*      */           case 1:
/*  344 */             return this.m11;
/*      */           case 2:
/*  346 */             return this.m12;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 2:
/*  353 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  356 */             return this.m20;
/*      */           case 1:
/*  358 */             return this.m21;
/*      */           case 2:
/*  360 */             return this.m22;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  370 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d1"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, Vector3d paramVector3d) {
/*  379 */     if (paramInt == 0) {
/*  380 */       paramVector3d.x = this.m00;
/*  381 */       paramVector3d.y = this.m01;
/*  382 */       paramVector3d.z = this.m02;
/*  383 */     } else if (paramInt == 1) {
/*  384 */       paramVector3d.x = this.m10;
/*  385 */       paramVector3d.y = this.m11;
/*  386 */       paramVector3d.z = this.m12;
/*  387 */     } else if (paramInt == 2) {
/*  388 */       paramVector3d.x = this.m20;
/*  389 */       paramVector3d.y = this.m21;
/*  390 */       paramVector3d.z = this.m22;
/*      */     } else {
/*  392 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, double[] paramArrayOfDouble) {
/*  403 */     if (paramInt == 0) {
/*  404 */       paramArrayOfDouble[0] = this.m00;
/*  405 */       paramArrayOfDouble[1] = this.m01;
/*  406 */       paramArrayOfDouble[2] = this.m02;
/*  407 */     } else if (paramInt == 1) {
/*  408 */       paramArrayOfDouble[0] = this.m10;
/*  409 */       paramArrayOfDouble[1] = this.m11;
/*  410 */       paramArrayOfDouble[2] = this.m12;
/*  411 */     } else if (paramInt == 2) {
/*  412 */       paramArrayOfDouble[0] = this.m20;
/*  413 */       paramArrayOfDouble[1] = this.m21;
/*  414 */       paramArrayOfDouble[2] = this.m22;
/*      */     } else {
/*  416 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
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
/*      */   public final void getColumn(int paramInt, Vector3d paramVector3d) {
/*  428 */     if (paramInt == 0) {
/*  429 */       paramVector3d.x = this.m00;
/*  430 */       paramVector3d.y = this.m10;
/*  431 */       paramVector3d.z = this.m20;
/*  432 */     } else if (paramInt == 1) {
/*  433 */       paramVector3d.x = this.m01;
/*  434 */       paramVector3d.y = this.m11;
/*  435 */       paramVector3d.z = this.m21;
/*  436 */     } else if (paramInt == 2) {
/*  437 */       paramVector3d.x = this.m02;
/*  438 */       paramVector3d.y = this.m12;
/*  439 */       paramVector3d.z = this.m22;
/*      */     } else {
/*  441 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
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
/*      */   public final void getColumn(int paramInt, double[] paramArrayOfDouble) {
/*  453 */     if (paramInt == 0) {
/*  454 */       paramArrayOfDouble[0] = this.m00;
/*  455 */       paramArrayOfDouble[1] = this.m10;
/*  456 */       paramArrayOfDouble[2] = this.m20;
/*  457 */     } else if (paramInt == 1) {
/*  458 */       paramArrayOfDouble[0] = this.m01;
/*  459 */       paramArrayOfDouble[1] = this.m11;
/*  460 */       paramArrayOfDouble[2] = this.m21;
/*  461 */     } else if (paramInt == 2) {
/*  462 */       paramArrayOfDouble[0] = this.m02;
/*  463 */       paramArrayOfDouble[1] = this.m12;
/*  464 */       paramArrayOfDouble[2] = this.m22;
/*      */     } else {
/*  466 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
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
/*      */   public final void setRow(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
/*  481 */     switch (paramInt) {
/*      */       case 0:
/*  483 */         this.m00 = paramDouble1;
/*  484 */         this.m01 = paramDouble2;
/*  485 */         this.m02 = paramDouble3;
/*      */         return;
/*      */       
/*      */       case 1:
/*  489 */         this.m10 = paramDouble1;
/*  490 */         this.m11 = paramDouble2;
/*  491 */         this.m12 = paramDouble3;
/*      */         return;
/*      */       
/*      */       case 2:
/*  495 */         this.m20 = paramDouble1;
/*  496 */         this.m21 = paramDouble2;
/*  497 */         this.m22 = paramDouble3;
/*      */         return;
/*      */     } 
/*      */     
/*  501 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRow(int paramInt, Vector3d paramVector3d) {
/*  512 */     switch (paramInt) {
/*      */       case 0:
/*  514 */         this.m00 = paramVector3d.x;
/*  515 */         this.m01 = paramVector3d.y;
/*  516 */         this.m02 = paramVector3d.z;
/*      */         return;
/*      */       
/*      */       case 1:
/*  520 */         this.m10 = paramVector3d.x;
/*  521 */         this.m11 = paramVector3d.y;
/*  522 */         this.m12 = paramVector3d.z;
/*      */         return;
/*      */       
/*      */       case 2:
/*  526 */         this.m20 = paramVector3d.x;
/*  527 */         this.m21 = paramVector3d.y;
/*  528 */         this.m22 = paramVector3d.z;
/*      */         return;
/*      */     } 
/*      */     
/*  532 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRow(int paramInt, double[] paramArrayOfDouble) {
/*  543 */     switch (paramInt) {
/*      */       case 0:
/*  545 */         this.m00 = paramArrayOfDouble[0];
/*  546 */         this.m01 = paramArrayOfDouble[1];
/*  547 */         this.m02 = paramArrayOfDouble[2];
/*      */         return;
/*      */       
/*      */       case 1:
/*  551 */         this.m10 = paramArrayOfDouble[0];
/*  552 */         this.m11 = paramArrayOfDouble[1];
/*  553 */         this.m12 = paramArrayOfDouble[2];
/*      */         return;
/*      */       
/*      */       case 2:
/*  557 */         this.m20 = paramArrayOfDouble[0];
/*  558 */         this.m21 = paramArrayOfDouble[1];
/*  559 */         this.m22 = paramArrayOfDouble[2];
/*      */         return;
/*      */     } 
/*      */     
/*  563 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
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
/*      */   public final void setColumn(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
/*  576 */     switch (paramInt) {
/*      */       case 0:
/*  578 */         this.m00 = paramDouble1;
/*  579 */         this.m10 = paramDouble2;
/*  580 */         this.m20 = paramDouble3;
/*      */         return;
/*      */       
/*      */       case 1:
/*  584 */         this.m01 = paramDouble1;
/*  585 */         this.m11 = paramDouble2;
/*  586 */         this.m21 = paramDouble3;
/*      */         return;
/*      */       
/*      */       case 2:
/*  590 */         this.m02 = paramDouble1;
/*  591 */         this.m12 = paramDouble2;
/*  592 */         this.m22 = paramDouble3;
/*      */         return;
/*      */     } 
/*      */     
/*  596 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setColumn(int paramInt, Vector3d paramVector3d) {
/*  607 */     switch (paramInt) {
/*      */       case 0:
/*  609 */         this.m00 = paramVector3d.x;
/*  610 */         this.m10 = paramVector3d.y;
/*  611 */         this.m20 = paramVector3d.z;
/*      */         return;
/*      */       
/*      */       case 1:
/*  615 */         this.m01 = paramVector3d.x;
/*  616 */         this.m11 = paramVector3d.y;
/*  617 */         this.m21 = paramVector3d.z;
/*      */         return;
/*      */       
/*      */       case 2:
/*  621 */         this.m02 = paramVector3d.x;
/*  622 */         this.m12 = paramVector3d.y;
/*  623 */         this.m22 = paramVector3d.z;
/*      */         return;
/*      */     } 
/*      */     
/*  627 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setColumn(int paramInt, double[] paramArrayOfDouble) {
/*  638 */     switch (paramInt) {
/*      */       case 0:
/*  640 */         this.m00 = paramArrayOfDouble[0];
/*  641 */         this.m10 = paramArrayOfDouble[1];
/*  642 */         this.m20 = paramArrayOfDouble[2];
/*      */         return;
/*      */       
/*      */       case 1:
/*  646 */         this.m01 = paramArrayOfDouble[0];
/*  647 */         this.m11 = paramArrayOfDouble[1];
/*  648 */         this.m21 = paramArrayOfDouble[2];
/*      */         return;
/*      */       
/*      */       case 2:
/*  652 */         this.m02 = paramArrayOfDouble[0];
/*  653 */         this.m12 = paramArrayOfDouble[1];
/*  654 */         this.m22 = paramArrayOfDouble[2];
/*      */         return;
/*      */     } 
/*      */     
/*  658 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
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
/*      */   public final double getScale() {
/*  672 */     double[] arrayOfDouble1 = new double[3];
/*  673 */     double[] arrayOfDouble2 = new double[9];
/*  674 */     getScaleRotate(arrayOfDouble1, arrayOfDouble2);
/*      */     
/*  676 */     return max3(arrayOfDouble1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(double paramDouble) {
/*  686 */     this.m00 += paramDouble;
/*  687 */     this.m01 += paramDouble;
/*  688 */     this.m02 += paramDouble;
/*      */     
/*  690 */     this.m10 += paramDouble;
/*  691 */     this.m11 += paramDouble;
/*  692 */     this.m12 += paramDouble;
/*      */     
/*  694 */     this.m20 += paramDouble;
/*  695 */     this.m21 += paramDouble;
/*  696 */     this.m22 += paramDouble;
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
/*      */   public final void add(double paramDouble, Matrix3d paramMatrix3d) {
/*  708 */     paramMatrix3d.m00 += paramDouble;
/*  709 */     paramMatrix3d.m01 += paramDouble;
/*  710 */     paramMatrix3d.m02 += paramDouble;
/*      */     
/*  712 */     paramMatrix3d.m10 += paramDouble;
/*  713 */     paramMatrix3d.m11 += paramDouble;
/*  714 */     paramMatrix3d.m12 += paramDouble;
/*      */     
/*  716 */     paramMatrix3d.m20 += paramDouble;
/*  717 */     paramMatrix3d.m21 += paramDouble;
/*  718 */     paramMatrix3d.m22 += paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
/*  728 */     paramMatrix3d1.m00 += paramMatrix3d2.m00;
/*  729 */     paramMatrix3d1.m01 += paramMatrix3d2.m01;
/*  730 */     paramMatrix3d1.m02 += paramMatrix3d2.m02;
/*      */     
/*  732 */     paramMatrix3d1.m10 += paramMatrix3d2.m10;
/*  733 */     paramMatrix3d1.m11 += paramMatrix3d2.m11;
/*  734 */     paramMatrix3d1.m12 += paramMatrix3d2.m12;
/*      */     
/*  736 */     paramMatrix3d1.m20 += paramMatrix3d2.m20;
/*  737 */     paramMatrix3d1.m21 += paramMatrix3d2.m21;
/*  738 */     paramMatrix3d1.m22 += paramMatrix3d2.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix3d paramMatrix3d) {
/*  747 */     this.m00 += paramMatrix3d.m00;
/*  748 */     this.m01 += paramMatrix3d.m01;
/*  749 */     this.m02 += paramMatrix3d.m02;
/*      */     
/*  751 */     this.m10 += paramMatrix3d.m10;
/*  752 */     this.m11 += paramMatrix3d.m11;
/*  753 */     this.m12 += paramMatrix3d.m12;
/*      */     
/*  755 */     this.m20 += paramMatrix3d.m20;
/*  756 */     this.m21 += paramMatrix3d.m21;
/*  757 */     this.m22 += paramMatrix3d.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
/*  768 */     paramMatrix3d1.m00 -= paramMatrix3d2.m00;
/*  769 */     paramMatrix3d1.m01 -= paramMatrix3d2.m01;
/*  770 */     paramMatrix3d1.m02 -= paramMatrix3d2.m02;
/*      */     
/*  772 */     paramMatrix3d1.m10 -= paramMatrix3d2.m10;
/*  773 */     paramMatrix3d1.m11 -= paramMatrix3d2.m11;
/*  774 */     paramMatrix3d1.m12 -= paramMatrix3d2.m12;
/*      */     
/*  776 */     paramMatrix3d1.m20 -= paramMatrix3d2.m20;
/*  777 */     paramMatrix3d1.m21 -= paramMatrix3d2.m21;
/*  778 */     paramMatrix3d1.m22 -= paramMatrix3d2.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Matrix3d paramMatrix3d) {
/*  788 */     this.m00 -= paramMatrix3d.m00;
/*  789 */     this.m01 -= paramMatrix3d.m01;
/*  790 */     this.m02 -= paramMatrix3d.m02;
/*      */     
/*  792 */     this.m10 -= paramMatrix3d.m10;
/*  793 */     this.m11 -= paramMatrix3d.m11;
/*  794 */     this.m12 -= paramMatrix3d.m12;
/*      */     
/*  796 */     this.m20 -= paramMatrix3d.m20;
/*  797 */     this.m21 -= paramMatrix3d.m21;
/*  798 */     this.m22 -= paramMatrix3d.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose() {
/*  808 */     double d = this.m10;
/*  809 */     this.m10 = this.m01;
/*  810 */     this.m01 = d;
/*      */     
/*  812 */     d = this.m20;
/*  813 */     this.m20 = this.m02;
/*  814 */     this.m02 = d;
/*      */     
/*  816 */     d = this.m21;
/*  817 */     this.m21 = this.m12;
/*  818 */     this.m12 = d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose(Matrix3d paramMatrix3d) {
/*  827 */     if (this != paramMatrix3d) {
/*  828 */       this.m00 = paramMatrix3d.m00;
/*  829 */       this.m01 = paramMatrix3d.m10;
/*  830 */       this.m02 = paramMatrix3d.m20;
/*      */       
/*  832 */       this.m10 = paramMatrix3d.m01;
/*  833 */       this.m11 = paramMatrix3d.m11;
/*  834 */       this.m12 = paramMatrix3d.m21;
/*      */       
/*  836 */       this.m20 = paramMatrix3d.m02;
/*  837 */       this.m21 = paramMatrix3d.m12;
/*  838 */       this.m22 = paramMatrix3d.m22;
/*      */     } else {
/*  840 */       transpose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4d paramQuat4d) {
/*  850 */     this.m00 = 1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z;
/*  851 */     this.m10 = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
/*  852 */     this.m20 = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
/*      */     
/*  854 */     this.m01 = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
/*  855 */     this.m11 = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z;
/*  856 */     this.m21 = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
/*      */     
/*  858 */     this.m02 = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
/*  859 */     this.m12 = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
/*  860 */     this.m22 = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(AxisAngle4d paramAxisAngle4d) {
/*  870 */     double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/*      */     
/*  872 */     if (d < 1.110223024E-16D) {
/*  873 */       this.m00 = 1.0D;
/*  874 */       this.m01 = 0.0D;
/*  875 */       this.m02 = 0.0D;
/*      */       
/*  877 */       this.m10 = 0.0D;
/*  878 */       this.m11 = 1.0D;
/*  879 */       this.m12 = 0.0D;
/*      */       
/*  881 */       this.m20 = 0.0D;
/*  882 */       this.m21 = 0.0D;
/*  883 */       this.m22 = 1.0D;
/*      */     } else {
/*  885 */       d = 1.0D / d;
/*  886 */       double d1 = paramAxisAngle4d.x * d;
/*  887 */       double d2 = paramAxisAngle4d.y * d;
/*  888 */       double d3 = paramAxisAngle4d.z * d;
/*      */       
/*  890 */       double d4 = Math.sin(paramAxisAngle4d.angle);
/*  891 */       double d5 = Math.cos(paramAxisAngle4d.angle);
/*  892 */       double d6 = 1.0D - d5;
/*      */       
/*  894 */       double d7 = d1 * d3;
/*  895 */       double d8 = d1 * d2;
/*  896 */       double d9 = d2 * d3;
/*      */       
/*  898 */       this.m00 = d6 * d1 * d1 + d5;
/*  899 */       this.m01 = d6 * d8 - d4 * d3;
/*  900 */       this.m02 = d6 * d7 + d4 * d2;
/*      */       
/*  902 */       this.m10 = d6 * d8 + d4 * d3;
/*  903 */       this.m11 = d6 * d2 * d2 + d5;
/*  904 */       this.m12 = d6 * d9 - d4 * d1;
/*      */       
/*  906 */       this.m20 = d6 * d7 - d4 * d2;
/*  907 */       this.m21 = d6 * d9 + d4 * d1;
/*  908 */       this.m22 = d6 * d3 * d3 + d5;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4f paramQuat4f) {
/*  919 */     this.m00 = 1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z;
/*  920 */     this.m10 = 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/*  921 */     this.m20 = 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/*  923 */     this.m01 = 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/*  924 */     this.m11 = 1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z;
/*  925 */     this.m21 = 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/*  927 */     this.m02 = 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/*  928 */     this.m12 = 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/*  929 */     this.m22 = 1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(AxisAngle4f paramAxisAngle4f) {
/*  939 */     double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/*  940 */     if (d < 1.110223024E-16D) {
/*  941 */       this.m00 = 1.0D;
/*  942 */       this.m01 = 0.0D;
/*  943 */       this.m02 = 0.0D;
/*      */       
/*  945 */       this.m10 = 0.0D;
/*  946 */       this.m11 = 1.0D;
/*  947 */       this.m12 = 0.0D;
/*      */       
/*  949 */       this.m20 = 0.0D;
/*  950 */       this.m21 = 0.0D;
/*  951 */       this.m22 = 1.0D;
/*      */     } else {
/*  953 */       d = 1.0D / d;
/*  954 */       double d1 = paramAxisAngle4f.x * d;
/*  955 */       double d2 = paramAxisAngle4f.y * d;
/*  956 */       double d3 = paramAxisAngle4f.z * d;
/*  957 */       double d4 = Math.sin(paramAxisAngle4f.angle);
/*  958 */       double d5 = Math.cos(paramAxisAngle4f.angle);
/*  959 */       double d6 = 1.0D - d5;
/*      */       
/*  961 */       double d7 = d1 * d3;
/*  962 */       double d8 = d1 * d2;
/*  963 */       double d9 = d2 * d3;
/*      */       
/*  965 */       this.m00 = d6 * d1 * d1 + d5;
/*  966 */       this.m01 = d6 * d8 - d4 * d3;
/*  967 */       this.m02 = d6 * d7 + d4 * d2;
/*      */       
/*  969 */       this.m10 = d6 * d8 + d4 * d3;
/*  970 */       this.m11 = d6 * d2 * d2 + d5;
/*  971 */       this.m12 = d6 * d9 - d4 * d1;
/*      */       
/*  973 */       this.m20 = d6 * d7 - d4 * d2;
/*  974 */       this.m21 = d6 * d9 + d4 * d1;
/*  975 */       this.m22 = d6 * d3 * d3 + d5;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix3f paramMatrix3f) {
/*  986 */     this.m00 = paramMatrix3f.m00;
/*  987 */     this.m01 = paramMatrix3f.m01;
/*  988 */     this.m02 = paramMatrix3f.m02;
/*      */     
/*  990 */     this.m10 = paramMatrix3f.m10;
/*  991 */     this.m11 = paramMatrix3f.m11;
/*  992 */     this.m12 = paramMatrix3f.m12;
/*      */     
/*  994 */     this.m20 = paramMatrix3f.m20;
/*  995 */     this.m21 = paramMatrix3f.m21;
/*  996 */     this.m22 = paramMatrix3f.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix3d paramMatrix3d) {
/* 1006 */     this.m00 = paramMatrix3d.m00;
/* 1007 */     this.m01 = paramMatrix3d.m01;
/* 1008 */     this.m02 = paramMatrix3d.m02;
/*      */     
/* 1010 */     this.m10 = paramMatrix3d.m10;
/* 1011 */     this.m11 = paramMatrix3d.m11;
/* 1012 */     this.m12 = paramMatrix3d.m12;
/*      */     
/* 1014 */     this.m20 = paramMatrix3d.m20;
/* 1015 */     this.m21 = paramMatrix3d.m21;
/* 1016 */     this.m22 = paramMatrix3d.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(double[] paramArrayOfDouble) {
/* 1027 */     this.m00 = paramArrayOfDouble[0];
/* 1028 */     this.m01 = paramArrayOfDouble[1];
/* 1029 */     this.m02 = paramArrayOfDouble[2];
/*      */     
/* 1031 */     this.m10 = paramArrayOfDouble[3];
/* 1032 */     this.m11 = paramArrayOfDouble[4];
/* 1033 */     this.m12 = paramArrayOfDouble[5];
/*      */     
/* 1035 */     this.m20 = paramArrayOfDouble[6];
/* 1036 */     this.m21 = paramArrayOfDouble[7];
/* 1037 */     this.m22 = paramArrayOfDouble[8];
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
/* 1048 */   public final void invert(Matrix3d paramMatrix3d) { invertGeneral(paramMatrix3d); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1056 */   public final void invert() { invertGeneral(this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void invertGeneral(Matrix3d paramMatrix3d) {
/* 1068 */     double[] arrayOfDouble1 = new double[9];
/* 1069 */     int[] arrayOfInt = new int[3];
/*      */     
/* 1071 */     double[] arrayOfDouble2 = new double[9];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1077 */     arrayOfDouble2[0] = paramMatrix3d.m00;
/* 1078 */     arrayOfDouble2[1] = paramMatrix3d.m01;
/* 1079 */     arrayOfDouble2[2] = paramMatrix3d.m02;
/*      */     
/* 1081 */     arrayOfDouble2[3] = paramMatrix3d.m10;
/* 1082 */     arrayOfDouble2[4] = paramMatrix3d.m11;
/* 1083 */     arrayOfDouble2[5] = paramMatrix3d.m12;
/*      */     
/* 1085 */     arrayOfDouble2[6] = paramMatrix3d.m20;
/* 1086 */     arrayOfDouble2[7] = paramMatrix3d.m21;
/* 1087 */     arrayOfDouble2[8] = paramMatrix3d.m22;
/*      */ 
/*      */ 
/*      */     
/* 1091 */     if (!luDecomposition(arrayOfDouble2, arrayOfInt))
/*      */     {
/* 1093 */       throw new SingularMatrixException(VecMathI18N.getString("Matrix3d12"));
/*      */     }
/*      */ 
/*      */     
/* 1097 */     for (byte b = 0; b < 9; ) { arrayOfDouble1[b] = 0.0D; b++; }
/* 1098 */      arrayOfDouble1[0] = 1.0D; arrayOfDouble1[4] = 1.0D; arrayOfDouble1[8] = 1.0D;
/* 1099 */     luBacksubstitution(arrayOfDouble2, arrayOfInt, arrayOfDouble1);
/*      */     
/* 1101 */     this.m00 = arrayOfDouble1[0];
/* 1102 */     this.m01 = arrayOfDouble1[1];
/* 1103 */     this.m02 = arrayOfDouble1[2];
/*      */     
/* 1105 */     this.m10 = arrayOfDouble1[3];
/* 1106 */     this.m11 = arrayOfDouble1[4];
/* 1107 */     this.m12 = arrayOfDouble1[5];
/*      */     
/* 1109 */     this.m20 = arrayOfDouble1[6];
/* 1110 */     this.m21 = arrayOfDouble1[7];
/* 1111 */     this.m22 = arrayOfDouble1[8];
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
/* 1138 */     double[] arrayOfDouble = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1146 */     byte b3 = 0;
/* 1147 */     byte b4 = 0;
/*      */ 
/*      */     
/* 1150 */     byte b1 = 3;
/* 1151 */     while (b1-- != 0) {
/* 1152 */       double d = 0.0D;
/*      */ 
/*      */       
/* 1155 */       byte b = 3;
/* 1156 */       while (b-- != 0) {
/* 1157 */         double d1 = paramArrayOfDouble[b3++];
/* 1158 */         d1 = Math.abs(d1);
/* 1159 */         if (d1 > d) {
/* 1160 */           d = d1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1165 */       if (d == 0.0D) {
/* 1166 */         return false;
/*      */       }
/* 1168 */       arrayOfDouble[b4++] = 1.0D / d;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1176 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1179 */     for (b1 = 0; b1 < 3; b1++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1185 */       for (b3 = 0; b3 < b1; b3++) {
/* 1186 */         byte b6 = b2 + 3 * b3 + b1;
/* 1187 */         double d1 = paramArrayOfDouble[b6];
/* 1188 */         byte b5 = b3;
/* 1189 */         byte b7 = b2 + 3 * b3;
/* 1190 */         byte b8 = b2 + b1;
/* 1191 */         while (b5-- != 0) {
/* 1192 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 1193 */           b7++;
/* 1194 */           b8 += 3;
/*      */         } 
/* 1196 */         paramArrayOfDouble[b6] = d1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1201 */       double d = 0.0D;
/* 1202 */       b4 = -1;
/* 1203 */       for (b3 = b1; b3 < 3; b3++) {
/* 1204 */         byte b6 = b2 + 3 * b3 + b1;
/* 1205 */         double d1 = paramArrayOfDouble[b6];
/* 1206 */         byte b5 = b1;
/* 1207 */         byte b7 = b2 + 3 * b3;
/* 1208 */         byte b8 = b2 + b1;
/* 1209 */         while (b5-- != 0) {
/* 1210 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 1211 */           b7++;
/* 1212 */           b8 += 3;
/*      */         } 
/* 1214 */         paramArrayOfDouble[b6] = d1;
/*      */         
/*      */         double d2;
/* 1217 */         if ((d2 = arrayOfDouble[b3] * Math.abs(d1)) >= d) {
/* 1218 */           d = d2;
/* 1219 */           b4 = b3;
/*      */         } 
/*      */       } 
/*      */       
/* 1223 */       if (b4 < 0) {
/* 1224 */         throw new RuntimeException(VecMathI18N.getString("Matrix3d13"));
/*      */       }
/*      */ 
/*      */       
/* 1228 */       if (b1 != b4) {
/*      */         
/* 1230 */         byte b = 3;
/* 1231 */         byte b5 = b2 + 3 * b4;
/* 1232 */         byte b6 = b2 + 3 * b1;
/* 1233 */         while (b-- != 0) {
/* 1234 */           double d1 = paramArrayOfDouble[b5];
/* 1235 */           paramArrayOfDouble[b5++] = paramArrayOfDouble[b6];
/* 1236 */           paramArrayOfDouble[b6++] = d1;
/*      */         } 
/*      */ 
/*      */         
/* 1240 */         arrayOfDouble[b4] = arrayOfDouble[b1];
/*      */       } 
/*      */ 
/*      */       
/* 1244 */       paramArrayOfInt[b1] = b4;
/*      */ 
/*      */       
/* 1247 */       if (paramArrayOfDouble[b2 + 3 * b1 + b1] == 0.0D) {
/* 1248 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1252 */       if (b1 != 2) {
/* 1253 */         double d1 = 1.0D / paramArrayOfDouble[b2 + 3 * b1 + b1];
/* 1254 */         byte b = b2 + 3 * (b1 + 1) + b1;
/* 1255 */         b3 = 2 - b1;
/* 1256 */         while (b3-- != 0) {
/* 1257 */           paramArrayOfDouble[b] = paramArrayOfDouble[b] * d1;
/* 1258 */           b += 3;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1264 */     return true;
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
/* 1294 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1297 */     for (byte b1 = 0; b1 < 3; b1++) {
/*      */       
/* 1299 */       int i = b1;
/* 1300 */       byte b4 = -1;
/*      */ 
/*      */       
/* 1303 */       for (byte b3 = 0; b3 < 3; b3++) {
/*      */ 
/*      */         
/* 1306 */         int j = paramArrayOfInt[b2 + b3];
/* 1307 */         double d = paramArrayOfDouble2[i + 3 * j];
/* 1308 */         paramArrayOfDouble2[i + 3 * j] = paramArrayOfDouble2[i + 3 * b3];
/* 1309 */         if (b4 >= 0) {
/*      */           
/* 1311 */           byte b6 = b3 * 3;
/* 1312 */           for (byte b5 = b4; b5 <= b3 - 1; b5++) {
/* 1313 */             d -= paramArrayOfDouble1[b6 + b5] * paramArrayOfDouble2[i + 3 * b5];
/*      */           }
/*      */         }
/* 1316 */         else if (d != 0.0D) {
/* 1317 */           b4 = b3;
/*      */         } 
/* 1319 */         paramArrayOfDouble2[i + 3 * b3] = d;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1324 */       byte b = 6;
/* 1325 */       paramArrayOfDouble2[i + 6] = paramArrayOfDouble2[i + 6] / paramArrayOfDouble1[b + 2];
/*      */       
/* 1327 */       b -= 3;
/* 1328 */       paramArrayOfDouble2[i + 3] = (paramArrayOfDouble2[i + 3] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 6]) / paramArrayOfDouble1[b + 1];
/*      */ 
/*      */       
/* 1331 */       b -= 3;
/* 1332 */       paramArrayOfDouble2[i + 0] = (paramArrayOfDouble2[i + 0] - paramArrayOfDouble1[b + 1] * paramArrayOfDouble2[i + 3] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 6]) / paramArrayOfDouble1[b + 0];
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
/* 1347 */   public final double determinant() { return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(double paramDouble) {
/* 1360 */     this.m00 = paramDouble;
/* 1361 */     this.m01 = 0.0D;
/* 1362 */     this.m02 = 0.0D;
/*      */     
/* 1364 */     this.m10 = 0.0D;
/* 1365 */     this.m11 = paramDouble;
/* 1366 */     this.m12 = 0.0D;
/*      */     
/* 1368 */     this.m20 = 0.0D;
/* 1369 */     this.m21 = 0.0D;
/* 1370 */     this.m22 = paramDouble;
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
/*      */   public final void rotX(double paramDouble) {
/* 1382 */     double d1 = Math.sin(paramDouble);
/* 1383 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 1385 */     this.m00 = 1.0D;
/* 1386 */     this.m01 = 0.0D;
/* 1387 */     this.m02 = 0.0D;
/*      */     
/* 1389 */     this.m10 = 0.0D;
/* 1390 */     this.m11 = d2;
/* 1391 */     this.m12 = -d1;
/*      */     
/* 1393 */     this.m20 = 0.0D;
/* 1394 */     this.m21 = d1;
/* 1395 */     this.m22 = d2;
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
/*      */   public final void rotY(double paramDouble) {
/* 1407 */     double d1 = Math.sin(paramDouble);
/* 1408 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 1410 */     this.m00 = d2;
/* 1411 */     this.m01 = 0.0D;
/* 1412 */     this.m02 = d1;
/*      */     
/* 1414 */     this.m10 = 0.0D;
/* 1415 */     this.m11 = 1.0D;
/* 1416 */     this.m12 = 0.0D;
/*      */     
/* 1418 */     this.m20 = -d1;
/* 1419 */     this.m21 = 0.0D;
/* 1420 */     this.m22 = d2;
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
/*      */   public final void rotZ(double paramDouble) {
/* 1432 */     double d1 = Math.sin(paramDouble);
/* 1433 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 1435 */     this.m00 = d2;
/* 1436 */     this.m01 = -d1;
/* 1437 */     this.m02 = 0.0D;
/*      */     
/* 1439 */     this.m10 = d1;
/* 1440 */     this.m11 = d2;
/* 1441 */     this.m12 = 0.0D;
/*      */     
/* 1443 */     this.m20 = 0.0D;
/* 1444 */     this.m21 = 0.0D;
/* 1445 */     this.m22 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(double paramDouble) {
/* 1454 */     this.m00 *= paramDouble;
/* 1455 */     this.m01 *= paramDouble;
/* 1456 */     this.m02 *= paramDouble;
/*      */     
/* 1458 */     this.m10 *= paramDouble;
/* 1459 */     this.m11 *= paramDouble;
/* 1460 */     this.m12 *= paramDouble;
/*      */     
/* 1462 */     this.m20 *= paramDouble;
/* 1463 */     this.m21 *= paramDouble;
/* 1464 */     this.m22 *= paramDouble;
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
/*      */   public final void mul(double paramDouble, Matrix3d paramMatrix3d) {
/* 1476 */     this.m00 = paramDouble * paramMatrix3d.m00;
/* 1477 */     this.m01 = paramDouble * paramMatrix3d.m01;
/* 1478 */     this.m02 = paramDouble * paramMatrix3d.m02;
/*      */     
/* 1480 */     this.m10 = paramDouble * paramMatrix3d.m10;
/* 1481 */     this.m11 = paramDouble * paramMatrix3d.m11;
/* 1482 */     this.m12 = paramDouble * paramMatrix3d.m12;
/*      */     
/* 1484 */     this.m20 = paramDouble * paramMatrix3d.m20;
/* 1485 */     this.m21 = paramDouble * paramMatrix3d.m21;
/* 1486 */     this.m22 = paramDouble * paramMatrix3d.m22;
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
/*      */   public final void mul(Matrix3d paramMatrix3d) {
/* 1501 */     double d1 = this.m00 * paramMatrix3d.m00 + this.m01 * paramMatrix3d.m10 + this.m02 * paramMatrix3d.m20;
/* 1502 */     double d2 = this.m00 * paramMatrix3d.m01 + this.m01 * paramMatrix3d.m11 + this.m02 * paramMatrix3d.m21;
/* 1503 */     double d3 = this.m00 * paramMatrix3d.m02 + this.m01 * paramMatrix3d.m12 + this.m02 * paramMatrix3d.m22;
/*      */     
/* 1505 */     double d4 = this.m10 * paramMatrix3d.m00 + this.m11 * paramMatrix3d.m10 + this.m12 * paramMatrix3d.m20;
/* 1506 */     double d5 = this.m10 * paramMatrix3d.m01 + this.m11 * paramMatrix3d.m11 + this.m12 * paramMatrix3d.m21;
/* 1507 */     double d6 = this.m10 * paramMatrix3d.m02 + this.m11 * paramMatrix3d.m12 + this.m12 * paramMatrix3d.m22;
/*      */     
/* 1509 */     double d7 = this.m20 * paramMatrix3d.m00 + this.m21 * paramMatrix3d.m10 + this.m22 * paramMatrix3d.m20;
/* 1510 */     double d8 = this.m20 * paramMatrix3d.m01 + this.m21 * paramMatrix3d.m11 + this.m22 * paramMatrix3d.m21;
/* 1511 */     double d9 = this.m20 * paramMatrix3d.m02 + this.m21 * paramMatrix3d.m12 + this.m22 * paramMatrix3d.m22;
/*      */     
/* 1513 */     this.m00 = d1; this.m01 = d2; this.m02 = d3;
/* 1514 */     this.m10 = d4; this.m11 = d5; this.m12 = d6;
/* 1515 */     this.m20 = d7; this.m21 = d8; this.m22 = d9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
/* 1526 */     if (this != paramMatrix3d1 && this != paramMatrix3d2) {
/* 1527 */       this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20;
/* 1528 */       this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21;
/* 1529 */       this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
/*      */       
/* 1531 */       this.m10 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20;
/* 1532 */       this.m11 = paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21;
/* 1533 */       this.m12 = paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
/*      */       
/* 1535 */       this.m20 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
/* 1536 */       this.m21 = paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
/* 1537 */       this.m22 = paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1543 */       double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20;
/* 1544 */       double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21;
/* 1545 */       double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
/*      */       
/* 1547 */       double d4 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20;
/* 1548 */       double d5 = paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21;
/* 1549 */       double d6 = paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
/*      */       
/* 1551 */       double d7 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
/* 1552 */       double d8 = paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
/* 1553 */       double d9 = paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */       
/* 1555 */       this.m00 = d1; this.m01 = d2; this.m02 = d3;
/* 1556 */       this.m10 = d4; this.m11 = d5; this.m12 = d6;
/* 1557 */       this.m20 = d7; this.m21 = d8; this.m22 = d9;
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
/*      */   public final void mulNormalize(Matrix3d paramMatrix3d) {
/* 1569 */     double[] arrayOfDouble1 = new double[9];
/* 1570 */     double[] arrayOfDouble2 = new double[9];
/* 1571 */     double[] arrayOfDouble3 = new double[3];
/*      */     
/* 1573 */     arrayOfDouble1[0] = this.m00 * paramMatrix3d.m00 + this.m01 * paramMatrix3d.m10 + this.m02 * paramMatrix3d.m20;
/* 1574 */     arrayOfDouble1[1] = this.m00 * paramMatrix3d.m01 + this.m01 * paramMatrix3d.m11 + this.m02 * paramMatrix3d.m21;
/* 1575 */     arrayOfDouble1[2] = this.m00 * paramMatrix3d.m02 + this.m01 * paramMatrix3d.m12 + this.m02 * paramMatrix3d.m22;
/*      */     
/* 1577 */     arrayOfDouble1[3] = this.m10 * paramMatrix3d.m00 + this.m11 * paramMatrix3d.m10 + this.m12 * paramMatrix3d.m20;
/* 1578 */     arrayOfDouble1[4] = this.m10 * paramMatrix3d.m01 + this.m11 * paramMatrix3d.m11 + this.m12 * paramMatrix3d.m21;
/* 1579 */     arrayOfDouble1[5] = this.m10 * paramMatrix3d.m02 + this.m11 * paramMatrix3d.m12 + this.m12 * paramMatrix3d.m22;
/*      */     
/* 1581 */     arrayOfDouble1[6] = this.m20 * paramMatrix3d.m00 + this.m21 * paramMatrix3d.m10 + this.m22 * paramMatrix3d.m20;
/* 1582 */     arrayOfDouble1[7] = this.m20 * paramMatrix3d.m01 + this.m21 * paramMatrix3d.m11 + this.m22 * paramMatrix3d.m21;
/* 1583 */     arrayOfDouble1[8] = this.m20 * paramMatrix3d.m02 + this.m21 * paramMatrix3d.m12 + this.m22 * paramMatrix3d.m22;
/*      */     
/* 1585 */     compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
/*      */     
/* 1587 */     this.m00 = arrayOfDouble2[0];
/* 1588 */     this.m01 = arrayOfDouble2[1];
/* 1589 */     this.m02 = arrayOfDouble2[2];
/*      */     
/* 1591 */     this.m10 = arrayOfDouble2[3];
/* 1592 */     this.m11 = arrayOfDouble2[4];
/* 1593 */     this.m12 = arrayOfDouble2[5];
/*      */     
/* 1595 */     this.m20 = arrayOfDouble2[6];
/* 1596 */     this.m21 = arrayOfDouble2[7];
/* 1597 */     this.m22 = arrayOfDouble2[8];
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
/*      */   public final void mulNormalize(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
/* 1611 */     double[] arrayOfDouble1 = new double[9];
/* 1612 */     double[] arrayOfDouble2 = new double[9];
/* 1613 */     double[] arrayOfDouble3 = new double[3];
/*      */     
/* 1615 */     arrayOfDouble1[0] = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20;
/* 1616 */     arrayOfDouble1[1] = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21;
/* 1617 */     arrayOfDouble1[2] = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
/*      */     
/* 1619 */     arrayOfDouble1[3] = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20;
/* 1620 */     arrayOfDouble1[4] = paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21;
/* 1621 */     arrayOfDouble1[5] = paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
/*      */     
/* 1623 */     arrayOfDouble1[6] = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
/* 1624 */     arrayOfDouble1[7] = paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
/* 1625 */     arrayOfDouble1[8] = paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */     
/* 1627 */     compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
/*      */     
/* 1629 */     this.m00 = arrayOfDouble2[0];
/* 1630 */     this.m01 = arrayOfDouble2[1];
/* 1631 */     this.m02 = arrayOfDouble2[2];
/*      */     
/* 1633 */     this.m10 = arrayOfDouble2[3];
/* 1634 */     this.m11 = arrayOfDouble2[4];
/* 1635 */     this.m12 = arrayOfDouble2[5];
/*      */     
/* 1637 */     this.m20 = arrayOfDouble2[6];
/* 1638 */     this.m21 = arrayOfDouble2[7];
/* 1639 */     this.m22 = arrayOfDouble2[8];
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
/*      */   public final void mulTransposeBoth(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
/* 1651 */     if (this != paramMatrix3d1 && this != paramMatrix3d2) {
/* 1652 */       this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m20 * paramMatrix3d2.m02;
/* 1653 */       this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m12;
/* 1654 */       this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m10 * paramMatrix3d2.m21 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
/*      */       
/* 1656 */       this.m10 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m02;
/* 1657 */       this.m11 = paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m12;
/* 1658 */       this.m12 = paramMatrix3d1.m01 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
/*      */       
/* 1660 */       this.m20 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
/* 1661 */       this.m21 = paramMatrix3d1.m02 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
/* 1662 */       this.m22 = paramMatrix3d1.m02 * paramMatrix3d2.m20 + paramMatrix3d1.m12 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1668 */       double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m20 * paramMatrix3d2.m02;
/* 1669 */       double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m12;
/* 1670 */       double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m10 * paramMatrix3d2.m21 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
/*      */       
/* 1672 */       double d4 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m02;
/* 1673 */       double d5 = paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m12;
/* 1674 */       double d6 = paramMatrix3d1.m01 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
/*      */       
/* 1676 */       double d7 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
/* 1677 */       double d8 = paramMatrix3d1.m02 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
/* 1678 */       double d9 = paramMatrix3d1.m02 * paramMatrix3d2.m20 + paramMatrix3d1.m12 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */       
/* 1680 */       this.m00 = d1; this.m01 = d2; this.m02 = d3;
/* 1681 */       this.m10 = d4; this.m11 = d5; this.m12 = d6;
/* 1682 */       this.m20 = d7; this.m21 = d8; this.m22 = d9;
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
/*      */   public final void mulTransposeRight(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
/* 1695 */     if (this != paramMatrix3d1 && this != paramMatrix3d2) {
/* 1696 */       this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m02 * paramMatrix3d2.m02;
/* 1697 */       this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m12;
/* 1698 */       this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m01 * paramMatrix3d2.m21 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
/*      */       
/* 1700 */       this.m10 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m02;
/* 1701 */       this.m11 = paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m12;
/* 1702 */       this.m12 = paramMatrix3d1.m10 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
/*      */       
/* 1704 */       this.m20 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
/* 1705 */       this.m21 = paramMatrix3d1.m20 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
/* 1706 */       this.m22 = paramMatrix3d1.m20 * paramMatrix3d2.m20 + paramMatrix3d1.m21 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1712 */       double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m02 * paramMatrix3d2.m02;
/* 1713 */       double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m12;
/* 1714 */       double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m01 * paramMatrix3d2.m21 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
/*      */       
/* 1716 */       double d4 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m02;
/* 1717 */       double d5 = paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m12;
/* 1718 */       double d6 = paramMatrix3d1.m10 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
/*      */       
/* 1720 */       double d7 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
/* 1721 */       double d8 = paramMatrix3d1.m20 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
/* 1722 */       double d9 = paramMatrix3d1.m20 * paramMatrix3d2.m20 + paramMatrix3d1.m21 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */       
/* 1724 */       this.m00 = d1; this.m01 = d2; this.m02 = d3;
/* 1725 */       this.m10 = d4; this.m11 = d5; this.m12 = d6;
/* 1726 */       this.m20 = d7; this.m21 = d8; this.m22 = d9;
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
/*      */   public final void mulTransposeLeft(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
/* 1738 */     if (this != paramMatrix3d1 && this != paramMatrix3d2) {
/* 1739 */       this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m20 * paramMatrix3d2.m20;
/* 1740 */       this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m21;
/* 1741 */       this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m10 * paramMatrix3d2.m12 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
/*      */       
/* 1743 */       this.m10 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m20;
/* 1744 */       this.m11 = paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m21;
/* 1745 */       this.m12 = paramMatrix3d1.m01 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
/*      */       
/* 1747 */       this.m20 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
/* 1748 */       this.m21 = paramMatrix3d1.m02 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
/* 1749 */       this.m22 = paramMatrix3d1.m02 * paramMatrix3d2.m02 + paramMatrix3d1.m12 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1755 */       double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m20 * paramMatrix3d2.m20;
/* 1756 */       double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m21;
/* 1757 */       double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m10 * paramMatrix3d2.m12 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
/*      */       
/* 1759 */       double d4 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m20;
/* 1760 */       double d5 = paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m21;
/* 1761 */       double d6 = paramMatrix3d1.m01 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
/*      */       
/* 1763 */       double d7 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
/* 1764 */       double d8 = paramMatrix3d1.m02 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
/* 1765 */       double d9 = paramMatrix3d1.m02 * paramMatrix3d2.m02 + paramMatrix3d1.m12 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
/*      */       
/* 1767 */       this.m00 = d1; this.m01 = d2; this.m02 = d3;
/* 1768 */       this.m10 = d4; this.m11 = d5; this.m12 = d6;
/* 1769 */       this.m20 = d7; this.m21 = d8; this.m22 = d9;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalize() {
/* 1779 */     double[] arrayOfDouble1 = new double[9];
/* 1780 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 1782 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 1784 */     this.m00 = arrayOfDouble1[0];
/* 1785 */     this.m01 = arrayOfDouble1[1];
/* 1786 */     this.m02 = arrayOfDouble1[2];
/*      */     
/* 1788 */     this.m10 = arrayOfDouble1[3];
/* 1789 */     this.m11 = arrayOfDouble1[4];
/* 1790 */     this.m12 = arrayOfDouble1[5];
/*      */     
/* 1792 */     this.m20 = arrayOfDouble1[6];
/* 1793 */     this.m21 = arrayOfDouble1[7];
/* 1794 */     this.m22 = arrayOfDouble1[8];
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
/*      */   public final void normalize(Matrix3d paramMatrix3d) {
/* 1806 */     double[] arrayOfDouble1 = new double[9];
/* 1807 */     double[] arrayOfDouble2 = new double[9];
/* 1808 */     double[] arrayOfDouble3 = new double[3];
/*      */     
/* 1810 */     arrayOfDouble1[0] = paramMatrix3d.m00;
/* 1811 */     arrayOfDouble1[1] = paramMatrix3d.m01;
/* 1812 */     arrayOfDouble1[2] = paramMatrix3d.m02;
/*      */     
/* 1814 */     arrayOfDouble1[3] = paramMatrix3d.m10;
/* 1815 */     arrayOfDouble1[4] = paramMatrix3d.m11;
/* 1816 */     arrayOfDouble1[5] = paramMatrix3d.m12;
/*      */     
/* 1818 */     arrayOfDouble1[6] = paramMatrix3d.m20;
/* 1819 */     arrayOfDouble1[7] = paramMatrix3d.m21;
/* 1820 */     arrayOfDouble1[8] = paramMatrix3d.m22;
/*      */     
/* 1822 */     compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
/*      */     
/* 1824 */     this.m00 = arrayOfDouble2[0];
/* 1825 */     this.m01 = arrayOfDouble2[1];
/* 1826 */     this.m02 = arrayOfDouble2[2];
/*      */     
/* 1828 */     this.m10 = arrayOfDouble2[3];
/* 1829 */     this.m11 = arrayOfDouble2[4];
/* 1830 */     this.m12 = arrayOfDouble2[5];
/*      */     
/* 1832 */     this.m20 = arrayOfDouble2[6];
/* 1833 */     this.m21 = arrayOfDouble2[7];
/* 1834 */     this.m22 = arrayOfDouble2[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalizeCP() {
/* 1844 */     double d = 1.0D / Math.sqrt(this.m00 * this.m00 + this.m10 * this.m10 + this.m20 * this.m20);
/* 1845 */     this.m00 *= d;
/* 1846 */     this.m10 *= d;
/* 1847 */     this.m20 *= d;
/*      */     
/* 1849 */     d = 1.0D / Math.sqrt(this.m01 * this.m01 + this.m11 * this.m11 + this.m21 * this.m21);
/* 1850 */     this.m01 *= d;
/* 1851 */     this.m11 *= d;
/* 1852 */     this.m21 *= d;
/*      */     
/* 1854 */     this.m02 = this.m10 * this.m21 - this.m11 * this.m20;
/* 1855 */     this.m12 = this.m01 * this.m20 - this.m00 * this.m21;
/* 1856 */     this.m22 = this.m00 * this.m11 - this.m01 * this.m10;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalizeCP(Matrix3d paramMatrix3d) {
/* 1867 */     double d = 1.0D / Math.sqrt(paramMatrix3d.m00 * paramMatrix3d.m00 + paramMatrix3d.m10 * paramMatrix3d.m10 + paramMatrix3d.m20 * paramMatrix3d.m20);
/* 1868 */     paramMatrix3d.m00 *= d;
/* 1869 */     paramMatrix3d.m10 *= d;
/* 1870 */     paramMatrix3d.m20 *= d;
/*      */     
/* 1872 */     d = 1.0D / Math.sqrt(paramMatrix3d.m01 * paramMatrix3d.m01 + paramMatrix3d.m11 * paramMatrix3d.m11 + paramMatrix3d.m21 * paramMatrix3d.m21);
/* 1873 */     paramMatrix3d.m01 *= d;
/* 1874 */     paramMatrix3d.m11 *= d;
/* 1875 */     paramMatrix3d.m21 *= d;
/*      */     
/* 1877 */     this.m02 = this.m10 * this.m21 - this.m11 * this.m20;
/* 1878 */     this.m12 = this.m01 * this.m20 - this.m00 * this.m21;
/* 1879 */     this.m22 = this.m00 * this.m11 - this.m01 * this.m10;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Matrix3d paramMatrix3d) {
/*      */     try {
/* 1891 */       return (this.m00 == paramMatrix3d.m00 && this.m01 == paramMatrix3d.m01 && this.m02 == paramMatrix3d.m02 && this.m10 == paramMatrix3d.m10 && this.m11 == paramMatrix3d.m11 && this.m12 == paramMatrix3d.m12 && this.m20 == paramMatrix3d.m20 && this.m21 == paramMatrix3d.m21 && this.m22 == paramMatrix3d.m22);
/*      */     }
/*      */     catch (NullPointerException nullPointerException) {
/*      */       
/* 1895 */       return false;
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
/*      */   public boolean equals(Object paramObject) {
/*      */     
/* 1909 */     try { Matrix3d matrix3d = (Matrix3d)paramObject;
/* 1910 */       return (this.m00 == matrix3d.m00 && this.m01 == matrix3d.m01 && this.m02 == matrix3d.m02 && this.m10 == matrix3d.m10 && this.m11 == matrix3d.m11 && this.m12 == matrix3d.m12 && this.m20 == matrix3d.m20 && this.m21 == matrix3d.m21 && this.m22 == matrix3d.m22); }
/*      */     
/*      */     catch (ClassCastException classCastException)
/*      */     
/* 1914 */     { return false; }
/* 1915 */     catch (NullPointerException nullPointerException) { return false; }
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
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean epsilonEquals(Matrix3d paramMatrix3d, double paramDouble) {
/* 1932 */     double d = this.m00 - paramMatrix3d.m00;
/* 1933 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1935 */     d = this.m01 - paramMatrix3d.m01;
/* 1936 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1938 */     d = this.m02 - paramMatrix3d.m02;
/* 1939 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1941 */     d = this.m10 - paramMatrix3d.m10;
/* 1942 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1944 */     d = this.m11 - paramMatrix3d.m11;
/* 1945 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1947 */     d = this.m12 - paramMatrix3d.m12;
/* 1948 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1950 */     d = this.m20 - paramMatrix3d.m20;
/* 1951 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1953 */     d = this.m21 - paramMatrix3d.m21;
/* 1954 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1956 */     d = this.m22 - paramMatrix3d.m22;
/* 1957 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 1959 */     return true;
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
/*      */   public int hashCode() {
/* 1972 */     long l = 1L;
/* 1973 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m00);
/* 1974 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m01);
/* 1975 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m02);
/* 1976 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m10);
/* 1977 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m11);
/* 1978 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m12);
/* 1979 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m20);
/* 1980 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m21);
/* 1981 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m22);
/* 1982 */     return (int)(l ^ l >> 32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setZero() {
/* 1991 */     this.m00 = 0.0D;
/* 1992 */     this.m01 = 0.0D;
/* 1993 */     this.m02 = 0.0D;
/*      */     
/* 1995 */     this.m10 = 0.0D;
/* 1996 */     this.m11 = 0.0D;
/* 1997 */     this.m12 = 0.0D;
/*      */     
/* 1999 */     this.m20 = 0.0D;
/* 2000 */     this.m21 = 0.0D;
/* 2001 */     this.m22 = 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate() {
/* 2010 */     this.m00 = -this.m00;
/* 2011 */     this.m01 = -this.m01;
/* 2012 */     this.m02 = -this.m02;
/*      */     
/* 2014 */     this.m10 = -this.m10;
/* 2015 */     this.m11 = -this.m11;
/* 2016 */     this.m12 = -this.m12;
/*      */     
/* 2018 */     this.m20 = -this.m20;
/* 2019 */     this.m21 = -this.m21;
/* 2020 */     this.m22 = -this.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate(Matrix3d paramMatrix3d) {
/* 2031 */     this.m00 = -paramMatrix3d.m00;
/* 2032 */     this.m01 = -paramMatrix3d.m01;
/* 2033 */     this.m02 = -paramMatrix3d.m02;
/*      */     
/* 2035 */     this.m10 = -paramMatrix3d.m10;
/* 2036 */     this.m11 = -paramMatrix3d.m11;
/* 2037 */     this.m12 = -paramMatrix3d.m12;
/*      */     
/* 2039 */     this.m20 = -paramMatrix3d.m20;
/* 2040 */     this.m21 = -paramMatrix3d.m21;
/* 2041 */     this.m22 = -paramMatrix3d.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Tuple3d paramTuple3d) {
/* 2052 */     double d1 = this.m00 * paramTuple3d.x + this.m01 * paramTuple3d.y + this.m02 * paramTuple3d.z;
/* 2053 */     double d2 = this.m10 * paramTuple3d.x + this.m11 * paramTuple3d.y + this.m12 * paramTuple3d.z;
/* 2054 */     double d3 = this.m20 * paramTuple3d.x + this.m21 * paramTuple3d.y + this.m22 * paramTuple3d.z;
/* 2055 */     paramTuple3d.set(d1, d2, d3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2) {
/* 2066 */     double d1 = this.m00 * paramTuple3d1.x + this.m01 * paramTuple3d1.y + this.m02 * paramTuple3d1.z;
/* 2067 */     double d2 = this.m10 * paramTuple3d1.x + this.m11 * paramTuple3d1.y + this.m12 * paramTuple3d1.z;
/* 2068 */     paramTuple3d2.z = this.m20 * paramTuple3d1.x + this.m21 * paramTuple3d1.y + this.m22 * paramTuple3d1.z;
/* 2069 */     paramTuple3d2.x = d1;
/* 2070 */     paramTuple3d2.y = d2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void getScaleRotate(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 2078 */     double[] arrayOfDouble = new double[9];
/*      */     
/* 2080 */     arrayOfDouble[0] = this.m00;
/* 2081 */     arrayOfDouble[1] = this.m01;
/* 2082 */     arrayOfDouble[2] = this.m02;
/*      */     
/* 2084 */     arrayOfDouble[3] = this.m10;
/* 2085 */     arrayOfDouble[4] = this.m11;
/* 2086 */     arrayOfDouble[5] = this.m12;
/*      */     
/* 2088 */     arrayOfDouble[6] = this.m20;
/* 2089 */     arrayOfDouble[7] = this.m21;
/* 2090 */     arrayOfDouble[8] = this.m22;
/* 2091 */     compute_svd(arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void compute_svd(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3) {
/* 2099 */     double[] arrayOfDouble1 = new double[9];
/* 2100 */     double[] arrayOfDouble2 = new double[9];
/* 2101 */     double[] arrayOfDouble3 = new double[9];
/* 2102 */     double[] arrayOfDouble4 = new double[9];
/*      */     
/* 2104 */     double[] arrayOfDouble5 = arrayOfDouble3;
/* 2105 */     double[] arrayOfDouble6 = arrayOfDouble4;
/*      */     
/* 2107 */     double[] arrayOfDouble7 = new double[9];
/* 2108 */     double[] arrayOfDouble8 = new double[3];
/* 2109 */     double[] arrayOfDouble9 = new double[3];
/*      */     
/* 2111 */     byte b2 = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     byte b1;
/*      */ 
/*      */     
/* 2118 */     for (b1 = 0; b1 < 9; b1++) {
/* 2119 */       arrayOfDouble7[b1] = paramArrayOfDouble1[b1];
/*      */     }
/*      */ 
/*      */     
/* 2123 */     if (paramArrayOfDouble1[3] * paramArrayOfDouble1[3] < 1.110223024E-16D) {
/* 2124 */       arrayOfDouble1[0] = 1.0D; arrayOfDouble1[1] = 0.0D; arrayOfDouble1[2] = 0.0D;
/* 2125 */       arrayOfDouble1[3] = 0.0D; arrayOfDouble1[4] = 1.0D; arrayOfDouble1[5] = 0.0D;
/* 2126 */       arrayOfDouble1[6] = 0.0D; arrayOfDouble1[7] = 0.0D; arrayOfDouble1[8] = 1.0D;
/* 2127 */     } else if (paramArrayOfDouble1[0] * paramArrayOfDouble1[0] < 1.110223024E-16D) {
/* 2128 */       arrayOfDouble5[0] = paramArrayOfDouble1[0];
/* 2129 */       arrayOfDouble5[1] = paramArrayOfDouble1[1];
/* 2130 */       arrayOfDouble5[2] = paramArrayOfDouble1[2];
/* 2131 */       paramArrayOfDouble1[0] = paramArrayOfDouble1[3];
/* 2132 */       paramArrayOfDouble1[1] = paramArrayOfDouble1[4];
/* 2133 */       paramArrayOfDouble1[2] = paramArrayOfDouble1[5];
/*      */       
/* 2135 */       paramArrayOfDouble1[3] = -arrayOfDouble5[0];
/* 2136 */       paramArrayOfDouble1[4] = -arrayOfDouble5[1];
/* 2137 */       paramArrayOfDouble1[5] = -arrayOfDouble5[2];
/*      */       
/* 2139 */       arrayOfDouble1[0] = 0.0D; arrayOfDouble1[1] = 1.0D; arrayOfDouble1[2] = 0.0D;
/* 2140 */       arrayOfDouble1[3] = -1.0D; arrayOfDouble1[4] = 0.0D; arrayOfDouble1[5] = 0.0D;
/* 2141 */       arrayOfDouble1[6] = 0.0D; arrayOfDouble1[7] = 0.0D; arrayOfDouble1[8] = 1.0D;
/*      */     } else {
/* 2143 */       double d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[0] * paramArrayOfDouble1[0] + paramArrayOfDouble1[3] * paramArrayOfDouble1[3]);
/* 2144 */       double d2 = paramArrayOfDouble1[0] * d1;
/* 2145 */       double d3 = paramArrayOfDouble1[3] * d1;
/* 2146 */       arrayOfDouble5[0] = d2 * paramArrayOfDouble1[0] + d3 * paramArrayOfDouble1[3];
/* 2147 */       arrayOfDouble5[1] = d2 * paramArrayOfDouble1[1] + d3 * paramArrayOfDouble1[4];
/* 2148 */       arrayOfDouble5[2] = d2 * paramArrayOfDouble1[2] + d3 * paramArrayOfDouble1[5];
/*      */       
/* 2150 */       paramArrayOfDouble1[3] = -d3 * paramArrayOfDouble1[0] + d2 * paramArrayOfDouble1[3];
/* 2151 */       paramArrayOfDouble1[4] = -d3 * paramArrayOfDouble1[1] + d2 * paramArrayOfDouble1[4];
/* 2152 */       paramArrayOfDouble1[5] = -d3 * paramArrayOfDouble1[2] + d2 * paramArrayOfDouble1[5];
/*      */       
/* 2154 */       paramArrayOfDouble1[0] = arrayOfDouble5[0];
/* 2155 */       paramArrayOfDouble1[1] = arrayOfDouble5[1];
/* 2156 */       paramArrayOfDouble1[2] = arrayOfDouble5[2];
/* 2157 */       arrayOfDouble1[0] = d2; arrayOfDouble1[1] = d3; arrayOfDouble1[2] = 0.0D;
/* 2158 */       arrayOfDouble1[3] = -d3; arrayOfDouble1[4] = d2; arrayOfDouble1[5] = 0.0D;
/* 2159 */       arrayOfDouble1[6] = 0.0D; arrayOfDouble1[7] = 0.0D; arrayOfDouble1[8] = 1.0D;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2164 */     if (paramArrayOfDouble1[6] * paramArrayOfDouble1[6] >= 1.110223024E-16D) {
/* 2165 */       if (paramArrayOfDouble1[0] * paramArrayOfDouble1[0] < 1.110223024E-16D) {
/* 2166 */         arrayOfDouble5[0] = paramArrayOfDouble1[0];
/* 2167 */         arrayOfDouble5[1] = paramArrayOfDouble1[1];
/* 2168 */         arrayOfDouble5[2] = paramArrayOfDouble1[2];
/* 2169 */         paramArrayOfDouble1[0] = paramArrayOfDouble1[6];
/* 2170 */         paramArrayOfDouble1[1] = paramArrayOfDouble1[7];
/* 2171 */         paramArrayOfDouble1[2] = paramArrayOfDouble1[8];
/*      */         
/* 2173 */         paramArrayOfDouble1[6] = -arrayOfDouble5[0];
/* 2174 */         paramArrayOfDouble1[7] = -arrayOfDouble5[1];
/* 2175 */         paramArrayOfDouble1[8] = -arrayOfDouble5[2];
/*      */         
/* 2177 */         arrayOfDouble5[0] = arrayOfDouble1[0];
/* 2178 */         arrayOfDouble5[1] = arrayOfDouble1[1];
/* 2179 */         arrayOfDouble5[2] = arrayOfDouble1[2];
/* 2180 */         arrayOfDouble1[0] = arrayOfDouble1[6];
/* 2181 */         arrayOfDouble1[1] = arrayOfDouble1[7];
/* 2182 */         arrayOfDouble1[2] = arrayOfDouble1[8];
/*      */         
/* 2184 */         arrayOfDouble1[6] = -arrayOfDouble5[0];
/* 2185 */         arrayOfDouble1[7] = -arrayOfDouble5[1];
/* 2186 */         arrayOfDouble1[8] = -arrayOfDouble5[2];
/*      */       } else {
/* 2188 */         double d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[0] * paramArrayOfDouble1[0] + paramArrayOfDouble1[6] * paramArrayOfDouble1[6]);
/* 2189 */         double d2 = paramArrayOfDouble1[0] * d1;
/* 2190 */         double d3 = paramArrayOfDouble1[6] * d1;
/* 2191 */         arrayOfDouble5[0] = d2 * paramArrayOfDouble1[0] + d3 * paramArrayOfDouble1[6];
/* 2192 */         arrayOfDouble5[1] = d2 * paramArrayOfDouble1[1] + d3 * paramArrayOfDouble1[7];
/* 2193 */         arrayOfDouble5[2] = d2 * paramArrayOfDouble1[2] + d3 * paramArrayOfDouble1[8];
/*      */         
/* 2195 */         paramArrayOfDouble1[6] = -d3 * paramArrayOfDouble1[0] + d2 * paramArrayOfDouble1[6];
/* 2196 */         paramArrayOfDouble1[7] = -d3 * paramArrayOfDouble1[1] + d2 * paramArrayOfDouble1[7];
/* 2197 */         paramArrayOfDouble1[8] = -d3 * paramArrayOfDouble1[2] + d2 * paramArrayOfDouble1[8];
/* 2198 */         paramArrayOfDouble1[0] = arrayOfDouble5[0];
/* 2199 */         paramArrayOfDouble1[1] = arrayOfDouble5[1];
/* 2200 */         paramArrayOfDouble1[2] = arrayOfDouble5[2];
/*      */         
/* 2202 */         arrayOfDouble5[0] = d2 * arrayOfDouble1[0];
/* 2203 */         arrayOfDouble5[1] = d2 * arrayOfDouble1[1];
/* 2204 */         arrayOfDouble1[2] = d3;
/*      */         
/* 2206 */         arrayOfDouble5[6] = -arrayOfDouble1[0] * d3;
/* 2207 */         arrayOfDouble5[7] = -arrayOfDouble1[1] * d3;
/* 2208 */         arrayOfDouble1[8] = d2;
/* 2209 */         arrayOfDouble1[0] = arrayOfDouble5[0];
/* 2210 */         arrayOfDouble1[1] = arrayOfDouble5[1];
/* 2211 */         arrayOfDouble1[6] = arrayOfDouble5[6];
/* 2212 */         arrayOfDouble1[7] = arrayOfDouble5[7];
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2217 */     if (paramArrayOfDouble1[2] * paramArrayOfDouble1[2] < 1.110223024E-16D) {
/* 2218 */       arrayOfDouble2[0] = 1.0D; arrayOfDouble2[1] = 0.0D; arrayOfDouble2[2] = 0.0D;
/* 2219 */       arrayOfDouble2[3] = 0.0D; arrayOfDouble2[4] = 1.0D; arrayOfDouble2[5] = 0.0D;
/* 2220 */       arrayOfDouble2[6] = 0.0D; arrayOfDouble2[7] = 0.0D; arrayOfDouble2[8] = 1.0D;
/* 2221 */     } else if (paramArrayOfDouble1[1] * paramArrayOfDouble1[1] < 1.110223024E-16D) {
/* 2222 */       arrayOfDouble5[2] = paramArrayOfDouble1[2];
/* 2223 */       arrayOfDouble5[5] = paramArrayOfDouble1[5];
/* 2224 */       arrayOfDouble5[8] = paramArrayOfDouble1[8];
/* 2225 */       paramArrayOfDouble1[2] = -paramArrayOfDouble1[1];
/* 2226 */       paramArrayOfDouble1[5] = -paramArrayOfDouble1[4];
/* 2227 */       paramArrayOfDouble1[8] = -paramArrayOfDouble1[7];
/*      */       
/* 2229 */       paramArrayOfDouble1[1] = arrayOfDouble5[2];
/* 2230 */       paramArrayOfDouble1[4] = arrayOfDouble5[5];
/* 2231 */       paramArrayOfDouble1[7] = arrayOfDouble5[8];
/*      */       
/* 2233 */       arrayOfDouble2[0] = 1.0D; arrayOfDouble2[1] = 0.0D; arrayOfDouble2[2] = 0.0D;
/* 2234 */       arrayOfDouble2[3] = 0.0D; arrayOfDouble2[4] = 0.0D; arrayOfDouble2[5] = -1.0D;
/* 2235 */       arrayOfDouble2[6] = 0.0D; arrayOfDouble2[7] = 1.0D; arrayOfDouble2[8] = 0.0D;
/*      */     } else {
/* 2237 */       double d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[1] * paramArrayOfDouble1[1] + paramArrayOfDouble1[2] * paramArrayOfDouble1[2]);
/* 2238 */       double d2 = paramArrayOfDouble1[1] * d1;
/* 2239 */       double d3 = paramArrayOfDouble1[2] * d1;
/* 2240 */       arrayOfDouble5[1] = d2 * paramArrayOfDouble1[1] + d3 * paramArrayOfDouble1[2];
/* 2241 */       paramArrayOfDouble1[2] = -d3 * paramArrayOfDouble1[1] + d2 * paramArrayOfDouble1[2];
/* 2242 */       paramArrayOfDouble1[1] = arrayOfDouble5[1];
/*      */       
/* 2244 */       arrayOfDouble5[4] = d2 * paramArrayOfDouble1[4] + d3 * paramArrayOfDouble1[5];
/* 2245 */       paramArrayOfDouble1[5] = -d3 * paramArrayOfDouble1[4] + d2 * paramArrayOfDouble1[5];
/* 2246 */       paramArrayOfDouble1[4] = arrayOfDouble5[4];
/*      */       
/* 2248 */       arrayOfDouble5[7] = d2 * paramArrayOfDouble1[7] + d3 * paramArrayOfDouble1[8];
/* 2249 */       paramArrayOfDouble1[8] = -d3 * paramArrayOfDouble1[7] + d2 * paramArrayOfDouble1[8];
/* 2250 */       paramArrayOfDouble1[7] = arrayOfDouble5[7];
/*      */       
/* 2252 */       arrayOfDouble2[0] = 1.0D; arrayOfDouble2[1] = 0.0D; arrayOfDouble2[2] = 0.0D;
/* 2253 */       arrayOfDouble2[3] = 0.0D; arrayOfDouble2[4] = d2; arrayOfDouble2[5] = -d3;
/* 2254 */       arrayOfDouble2[6] = 0.0D; arrayOfDouble2[7] = d3; arrayOfDouble2[8] = d2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2259 */     if (paramArrayOfDouble1[7] * paramArrayOfDouble1[7] >= 1.110223024E-16D) {
/* 2260 */       if (paramArrayOfDouble1[4] * paramArrayOfDouble1[4] < 1.110223024E-16D) {
/* 2261 */         arrayOfDouble5[3] = paramArrayOfDouble1[3];
/* 2262 */         arrayOfDouble5[4] = paramArrayOfDouble1[4];
/* 2263 */         arrayOfDouble5[5] = paramArrayOfDouble1[5];
/* 2264 */         paramArrayOfDouble1[3] = paramArrayOfDouble1[6];
/* 2265 */         paramArrayOfDouble1[4] = paramArrayOfDouble1[7];
/* 2266 */         paramArrayOfDouble1[5] = paramArrayOfDouble1[8];
/*      */         
/* 2268 */         paramArrayOfDouble1[6] = -arrayOfDouble5[3];
/* 2269 */         paramArrayOfDouble1[7] = -arrayOfDouble5[4];
/* 2270 */         paramArrayOfDouble1[8] = -arrayOfDouble5[5];
/*      */         
/* 2272 */         arrayOfDouble5[3] = arrayOfDouble1[3];
/* 2273 */         arrayOfDouble5[4] = arrayOfDouble1[4];
/* 2274 */         arrayOfDouble5[5] = arrayOfDouble1[5];
/* 2275 */         arrayOfDouble1[3] = arrayOfDouble1[6];
/* 2276 */         arrayOfDouble1[4] = arrayOfDouble1[7];
/* 2277 */         arrayOfDouble1[5] = arrayOfDouble1[8];
/*      */         
/* 2279 */         arrayOfDouble1[6] = -arrayOfDouble5[3];
/* 2280 */         arrayOfDouble1[7] = -arrayOfDouble5[4];
/* 2281 */         arrayOfDouble1[8] = -arrayOfDouble5[5];
/*      */       } else {
/*      */         
/* 2284 */         double d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[4] * paramArrayOfDouble1[4] + paramArrayOfDouble1[7] * paramArrayOfDouble1[7]);
/* 2285 */         double d2 = paramArrayOfDouble1[4] * d1;
/* 2286 */         double d3 = paramArrayOfDouble1[7] * d1;
/* 2287 */         arrayOfDouble5[3] = d2 * paramArrayOfDouble1[3] + d3 * paramArrayOfDouble1[6];
/* 2288 */         paramArrayOfDouble1[6] = -d3 * paramArrayOfDouble1[3] + d2 * paramArrayOfDouble1[6];
/* 2289 */         paramArrayOfDouble1[3] = arrayOfDouble5[3];
/*      */         
/* 2291 */         arrayOfDouble5[4] = d2 * paramArrayOfDouble1[4] + d3 * paramArrayOfDouble1[7];
/* 2292 */         paramArrayOfDouble1[7] = -d3 * paramArrayOfDouble1[4] + d2 * paramArrayOfDouble1[7];
/* 2293 */         paramArrayOfDouble1[4] = arrayOfDouble5[4];
/*      */         
/* 2295 */         arrayOfDouble5[5] = d2 * paramArrayOfDouble1[5] + d3 * paramArrayOfDouble1[8];
/* 2296 */         paramArrayOfDouble1[8] = -d3 * paramArrayOfDouble1[5] + d2 * paramArrayOfDouble1[8];
/* 2297 */         paramArrayOfDouble1[5] = arrayOfDouble5[5];
/*      */         
/* 2299 */         arrayOfDouble5[3] = d2 * arrayOfDouble1[3] + d3 * arrayOfDouble1[6];
/* 2300 */         arrayOfDouble1[6] = -d3 * arrayOfDouble1[3] + d2 * arrayOfDouble1[6];
/* 2301 */         arrayOfDouble1[3] = arrayOfDouble5[3];
/*      */         
/* 2303 */         arrayOfDouble5[4] = d2 * arrayOfDouble1[4] + d3 * arrayOfDouble1[7];
/* 2304 */         arrayOfDouble1[7] = -d3 * arrayOfDouble1[4] + d2 * arrayOfDouble1[7];
/* 2305 */         arrayOfDouble1[4] = arrayOfDouble5[4];
/*      */         
/* 2307 */         arrayOfDouble5[5] = d2 * arrayOfDouble1[5] + d3 * arrayOfDouble1[8];
/* 2308 */         arrayOfDouble1[8] = -d3 * arrayOfDouble1[5] + d2 * arrayOfDouble1[8];
/* 2309 */         arrayOfDouble1[5] = arrayOfDouble5[5];
/*      */       } 
/*      */     }
/* 2312 */     arrayOfDouble6[0] = paramArrayOfDouble1[0];
/* 2313 */     arrayOfDouble6[1] = paramArrayOfDouble1[4];
/* 2314 */     arrayOfDouble6[2] = paramArrayOfDouble1[8];
/* 2315 */     arrayOfDouble8[0] = paramArrayOfDouble1[1];
/* 2316 */     arrayOfDouble8[1] = paramArrayOfDouble1[5];
/*      */     
/* 2318 */     if (arrayOfDouble8[0] * arrayOfDouble8[0] >= 1.110223024E-16D || arrayOfDouble8[1] * arrayOfDouble8[1] >= 1.110223024E-16D)
/*      */     {
/*      */       
/* 2321 */       compute_qr(arrayOfDouble6, arrayOfDouble8, arrayOfDouble1, arrayOfDouble2);
/*      */     }
/*      */     
/* 2324 */     arrayOfDouble9[0] = arrayOfDouble6[0];
/* 2325 */     arrayOfDouble9[1] = arrayOfDouble6[1];
/* 2326 */     arrayOfDouble9[2] = arrayOfDouble6[2];
/*      */ 
/*      */ 
/*      */     
/* 2330 */     if (almostEqual(Math.abs(arrayOfDouble9[0]), 1.0D) && almostEqual(Math.abs(arrayOfDouble9[1]), 1.0D) && almostEqual(Math.abs(arrayOfDouble9[2]), 1.0D)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2335 */       for (b1 = 0; b1 < 3; b1++) {
/* 2336 */         if (arrayOfDouble9[b1] < 0.0D)
/* 2337 */           b2++; 
/*      */       } 
/* 2339 */       if (b2 == 0 || b2 == 2) {
/*      */         
/* 2341 */         paramArrayOfDouble2[2] = 1.0D; paramArrayOfDouble2[1] = 1.0D; paramArrayOfDouble2[0] = 1.0D;
/* 2342 */         for (b1 = 0; b1 < 9; b1++) {
/* 2343 */           paramArrayOfDouble3[b1] = arrayOfDouble7[b1];
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/* 2350 */     transpose_mat(arrayOfDouble1, arrayOfDouble3);
/* 2351 */     transpose_mat(arrayOfDouble2, arrayOfDouble4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2365 */     svdReorder(paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble4, arrayOfDouble9, paramArrayOfDouble3, paramArrayOfDouble2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void svdReorder(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, double[] paramArrayOfDouble6) {
/* 2372 */     int[] arrayOfInt1 = new int[3];
/* 2373 */     int[] arrayOfInt2 = new int[3];
/*      */     
/* 2375 */     double[] arrayOfDouble1 = new double[3];
/* 2376 */     double[] arrayOfDouble2 = new double[9];
/*      */ 
/*      */ 
/*      */     
/* 2380 */     if (paramArrayOfDouble4[0] < 0.0D) {
/* 2381 */       paramArrayOfDouble4[0] = -paramArrayOfDouble4[0];
/* 2382 */       paramArrayOfDouble3[0] = -paramArrayOfDouble3[0];
/* 2383 */       paramArrayOfDouble3[1] = -paramArrayOfDouble3[1];
/* 2384 */       paramArrayOfDouble3[2] = -paramArrayOfDouble3[2];
/*      */     } 
/* 2386 */     if (paramArrayOfDouble4[1] < 0.0D) {
/* 2387 */       paramArrayOfDouble4[1] = -paramArrayOfDouble4[1];
/* 2388 */       paramArrayOfDouble3[3] = -paramArrayOfDouble3[3];
/* 2389 */       paramArrayOfDouble3[4] = -paramArrayOfDouble3[4];
/* 2390 */       paramArrayOfDouble3[5] = -paramArrayOfDouble3[5];
/*      */     } 
/* 2392 */     if (paramArrayOfDouble4[2] < 0.0D) {
/* 2393 */       paramArrayOfDouble4[2] = -paramArrayOfDouble4[2];
/* 2394 */       paramArrayOfDouble3[6] = -paramArrayOfDouble3[6];
/* 2395 */       paramArrayOfDouble3[7] = -paramArrayOfDouble3[7];
/* 2396 */       paramArrayOfDouble3[8] = -paramArrayOfDouble3[8];
/*      */     } 
/*      */     
/* 2399 */     mat_mul(paramArrayOfDouble2, paramArrayOfDouble3, arrayOfDouble2);
/*      */ 
/*      */     
/* 2402 */     if (almostEqual(Math.abs(paramArrayOfDouble4[0]), Math.abs(paramArrayOfDouble4[1])) && almostEqual(Math.abs(paramArrayOfDouble4[1]), Math.abs(paramArrayOfDouble4[2]))) {
/*      */       byte b;
/* 2404 */       for (b = 0; b < 9; b++) {
/* 2405 */         paramArrayOfDouble5[b] = arrayOfDouble2[b];
/*      */       }
/* 2407 */       for (b = 0; b < 3; b++) {
/* 2408 */         paramArrayOfDouble6[b] = paramArrayOfDouble4[b];
/*      */       }
/*      */     } else {
/*      */       boolean bool2, bool1;
/*      */       
/*      */       byte b;
/* 2414 */       if (paramArrayOfDouble4[0] > paramArrayOfDouble4[1]) {
/* 2415 */         if (paramArrayOfDouble4[0] > paramArrayOfDouble4[2]) {
/* 2416 */           if (paramArrayOfDouble4[2] > paramArrayOfDouble4[1]) {
/* 2417 */             arrayOfInt1[0] = 0; arrayOfInt1[1] = 2; arrayOfInt1[2] = 1;
/*      */           } else {
/* 2419 */             arrayOfInt1[0] = 0; arrayOfInt1[1] = 1; arrayOfInt1[2] = 2;
/*      */           } 
/*      */         } else {
/* 2422 */           arrayOfInt1[0] = 2; arrayOfInt1[1] = 0; arrayOfInt1[2] = 1;
/*      */         }
/*      */       
/* 2425 */       } else if (paramArrayOfDouble4[1] > paramArrayOfDouble4[2]) {
/* 2426 */         if (paramArrayOfDouble4[2] > paramArrayOfDouble4[0]) {
/* 2427 */           arrayOfInt1[0] = 1; arrayOfInt1[1] = 2; arrayOfInt1[2] = 0;
/*      */         } else {
/* 2429 */           arrayOfInt1[0] = 1; arrayOfInt1[1] = 0; arrayOfInt1[2] = 2;
/*      */         } 
/*      */       } else {
/* 2432 */         arrayOfInt1[0] = 2; arrayOfInt1[1] = 1; arrayOfInt1[2] = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2444 */       arrayOfDouble1[0] = paramArrayOfDouble1[0] * paramArrayOfDouble1[0] + paramArrayOfDouble1[1] * paramArrayOfDouble1[1] + paramArrayOfDouble1[2] * paramArrayOfDouble1[2];
/* 2445 */       arrayOfDouble1[1] = paramArrayOfDouble1[3] * paramArrayOfDouble1[3] + paramArrayOfDouble1[4] * paramArrayOfDouble1[4] + paramArrayOfDouble1[5] * paramArrayOfDouble1[5];
/* 2446 */       arrayOfDouble1[2] = paramArrayOfDouble1[6] * paramArrayOfDouble1[6] + paramArrayOfDouble1[7] * paramArrayOfDouble1[7] + paramArrayOfDouble1[8] * paramArrayOfDouble1[8];
/*      */       
/* 2448 */       if (arrayOfDouble1[0] > arrayOfDouble1[1]) {
/* 2449 */         if (arrayOfDouble1[0] > arrayOfDouble1[2]) {
/* 2450 */           if (arrayOfDouble1[2] > arrayOfDouble1[1]) {
/*      */             
/* 2452 */             b = 0; bool2 = true; bool1 = true;
/*      */           } else {
/*      */             
/* 2455 */             b = 0; bool1 = true; bool2 = true;
/*      */           } 
/*      */         } else {
/*      */           
/* 2459 */           bool2 = false; b = 1; bool1 = true;
/*      */         }
/*      */       
/* 2462 */       } else if (arrayOfDouble1[1] > arrayOfDouble1[2]) {
/* 2463 */         if (arrayOfDouble1[2] > arrayOfDouble1[0]) {
/*      */           
/* 2465 */           bool1 = false; bool2 = true; b = 2;
/*      */         } else {
/*      */           
/* 2468 */           bool1 = false; b = 1; bool2 = true;
/*      */         } 
/*      */       } else {
/*      */         
/* 2472 */         bool2 = false; bool1 = true; b = 2;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2477 */       int i = arrayOfInt1[b];
/* 2478 */       paramArrayOfDouble6[0] = paramArrayOfDouble4[i];
/*      */       
/* 2480 */       i = arrayOfInt1[bool1];
/* 2481 */       paramArrayOfDouble6[1] = paramArrayOfDouble4[i];
/*      */       
/* 2483 */       i = arrayOfInt1[bool2];
/* 2484 */       paramArrayOfDouble6[2] = paramArrayOfDouble4[i];
/*      */ 
/*      */       
/* 2487 */       i = arrayOfInt1[b];
/* 2488 */       paramArrayOfDouble5[0] = arrayOfDouble2[i];
/*      */       
/* 2490 */       i = arrayOfInt1[b] + 3;
/* 2491 */       paramArrayOfDouble5[3] = arrayOfDouble2[i];
/*      */       
/* 2493 */       i = arrayOfInt1[b] + 6;
/* 2494 */       paramArrayOfDouble5[6] = arrayOfDouble2[i];
/*      */       
/* 2496 */       i = arrayOfInt1[bool1];
/* 2497 */       paramArrayOfDouble5[1] = arrayOfDouble2[i];
/*      */       
/* 2499 */       i = arrayOfInt1[bool1] + 3;
/* 2500 */       paramArrayOfDouble5[4] = arrayOfDouble2[i];
/*      */       
/* 2502 */       i = arrayOfInt1[bool1] + 6;
/* 2503 */       paramArrayOfDouble5[7] = arrayOfDouble2[i];
/*      */       
/* 2505 */       i = arrayOfInt1[bool2];
/* 2506 */       paramArrayOfDouble5[2] = arrayOfDouble2[i];
/*      */       
/* 2508 */       i = arrayOfInt1[bool2] + 3;
/* 2509 */       paramArrayOfDouble5[5] = arrayOfDouble2[i];
/*      */       
/* 2511 */       i = arrayOfInt1[bool2] + 6;
/* 2512 */       paramArrayOfDouble5[8] = arrayOfDouble2[i];
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int compute_qr(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4) {
/* 2521 */     double[] arrayOfDouble1 = new double[2];
/* 2522 */     double[] arrayOfDouble2 = new double[2];
/* 2523 */     double[] arrayOfDouble3 = new double[2];
/* 2524 */     double[] arrayOfDouble4 = new double[2];
/* 2525 */     double[] arrayOfDouble5 = new double[9];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2533 */     double d1 = 1.0D;
/* 2534 */     double d2 = -1.0D;
/*      */     
/* 2536 */     boolean bool = false;
/*      */ 
/*      */     
/* 2539 */     byte b2 = 1;
/*      */     
/* 2541 */     if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-15D || Math.abs(paramArrayOfDouble2[0]) < 4.89E-15D) bool = true;
/*      */     
/* 2543 */     for (byte b1 = 0; b1 < 10 && !bool; b1++) {
/* 2544 */       double d3 = compute_shift(paramArrayOfDouble1[1], paramArrayOfDouble2[1], paramArrayOfDouble1[2]);
/* 2545 */       double d7 = (Math.abs(paramArrayOfDouble1[0]) - d3) * (d_sign(d1, paramArrayOfDouble1[0]) + d3 / paramArrayOfDouble1[0]);
/* 2546 */       double d8 = paramArrayOfDouble2[0];
/* 2547 */       double d4 = compute_rot(d7, d8, arrayOfDouble4, arrayOfDouble2, 0, b2);
/* 2548 */       d7 = arrayOfDouble2[0] * paramArrayOfDouble1[0] + arrayOfDouble4[0] * paramArrayOfDouble2[0];
/* 2549 */       paramArrayOfDouble2[0] = arrayOfDouble2[0] * paramArrayOfDouble2[0] - arrayOfDouble4[0] * paramArrayOfDouble1[0];
/* 2550 */       d8 = arrayOfDouble4[0] * paramArrayOfDouble1[1];
/* 2551 */       paramArrayOfDouble1[1] = arrayOfDouble2[0] * paramArrayOfDouble1[1];
/*      */       
/* 2553 */       d4 = compute_rot(d7, d8, arrayOfDouble3, arrayOfDouble1, 0, b2);
/* 2554 */       b2 = 0;
/* 2555 */       paramArrayOfDouble1[0] = d4;
/* 2556 */       d7 = arrayOfDouble1[0] * paramArrayOfDouble2[0] + arrayOfDouble3[0] * paramArrayOfDouble1[1];
/* 2557 */       paramArrayOfDouble1[1] = arrayOfDouble1[0] * paramArrayOfDouble1[1] - arrayOfDouble3[0] * paramArrayOfDouble2[0];
/* 2558 */       d8 = arrayOfDouble3[0] * paramArrayOfDouble2[1];
/* 2559 */       paramArrayOfDouble2[1] = arrayOfDouble1[0] * paramArrayOfDouble2[1];
/*      */       
/* 2561 */       d4 = compute_rot(d7, d8, arrayOfDouble4, arrayOfDouble2, 1, b2);
/* 2562 */       paramArrayOfDouble2[0] = d4;
/* 2563 */       d7 = arrayOfDouble2[1] * paramArrayOfDouble1[1] + arrayOfDouble4[1] * paramArrayOfDouble2[1];
/* 2564 */       paramArrayOfDouble2[1] = arrayOfDouble2[1] * paramArrayOfDouble2[1] - arrayOfDouble4[1] * paramArrayOfDouble1[1];
/* 2565 */       d8 = arrayOfDouble4[1] * paramArrayOfDouble1[2];
/* 2566 */       paramArrayOfDouble1[2] = arrayOfDouble2[1] * paramArrayOfDouble1[2];
/*      */       
/* 2568 */       d4 = compute_rot(d7, d8, arrayOfDouble3, arrayOfDouble1, 1, b2);
/* 2569 */       paramArrayOfDouble1[1] = d4;
/* 2570 */       d7 = arrayOfDouble1[1] * paramArrayOfDouble2[1] + arrayOfDouble3[1] * paramArrayOfDouble1[2];
/* 2571 */       paramArrayOfDouble1[2] = arrayOfDouble1[1] * paramArrayOfDouble1[2] - arrayOfDouble3[1] * paramArrayOfDouble2[1];
/* 2572 */       paramArrayOfDouble2[1] = d7;
/*      */ 
/*      */       
/* 2575 */       double d5 = paramArrayOfDouble3[0];
/* 2576 */       paramArrayOfDouble3[0] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfDouble3[3];
/* 2577 */       paramArrayOfDouble3[3] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfDouble3[3];
/* 2578 */       d5 = paramArrayOfDouble3[1];
/* 2579 */       paramArrayOfDouble3[1] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfDouble3[4];
/* 2580 */       paramArrayOfDouble3[4] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfDouble3[4];
/* 2581 */       d5 = paramArrayOfDouble3[2];
/* 2582 */       paramArrayOfDouble3[2] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfDouble3[5];
/* 2583 */       paramArrayOfDouble3[5] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfDouble3[5];
/*      */       
/* 2585 */       d5 = paramArrayOfDouble3[3];
/* 2586 */       paramArrayOfDouble3[3] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfDouble3[6];
/* 2587 */       paramArrayOfDouble3[6] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfDouble3[6];
/* 2588 */       d5 = paramArrayOfDouble3[4];
/* 2589 */       paramArrayOfDouble3[4] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfDouble3[7];
/* 2590 */       paramArrayOfDouble3[7] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfDouble3[7];
/* 2591 */       d5 = paramArrayOfDouble3[5];
/* 2592 */       paramArrayOfDouble3[5] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfDouble3[8];
/* 2593 */       paramArrayOfDouble3[8] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfDouble3[8];
/*      */ 
/*      */ 
/*      */       
/* 2597 */       double d6 = paramArrayOfDouble4[0];
/* 2598 */       paramArrayOfDouble4[0] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfDouble4[1];
/* 2599 */       paramArrayOfDouble4[1] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfDouble4[1];
/* 2600 */       d6 = paramArrayOfDouble4[3];
/* 2601 */       paramArrayOfDouble4[3] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfDouble4[4];
/* 2602 */       paramArrayOfDouble4[4] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfDouble4[4];
/* 2603 */       d6 = paramArrayOfDouble4[6];
/* 2604 */       paramArrayOfDouble4[6] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfDouble4[7];
/* 2605 */       paramArrayOfDouble4[7] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfDouble4[7];
/*      */       
/* 2607 */       d6 = paramArrayOfDouble4[1];
/* 2608 */       paramArrayOfDouble4[1] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfDouble4[2];
/* 2609 */       paramArrayOfDouble4[2] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfDouble4[2];
/* 2610 */       d6 = paramArrayOfDouble4[4];
/* 2611 */       paramArrayOfDouble4[4] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfDouble4[5];
/* 2612 */       paramArrayOfDouble4[5] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfDouble4[5];
/* 2613 */       d6 = paramArrayOfDouble4[7];
/* 2614 */       paramArrayOfDouble4[7] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfDouble4[8];
/* 2615 */       paramArrayOfDouble4[8] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfDouble4[8];
/*      */ 
/*      */       
/* 2618 */       arrayOfDouble5[0] = paramArrayOfDouble1[0]; arrayOfDouble5[1] = paramArrayOfDouble2[0]; arrayOfDouble5[2] = 0.0D;
/* 2619 */       arrayOfDouble5[3] = 0.0D; arrayOfDouble5[4] = paramArrayOfDouble1[1]; arrayOfDouble5[5] = paramArrayOfDouble2[1];
/* 2620 */       arrayOfDouble5[6] = 0.0D; arrayOfDouble5[7] = 0.0D; arrayOfDouble5[8] = paramArrayOfDouble1[2];
/*      */       
/* 2622 */       if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-15D || Math.abs(paramArrayOfDouble2[0]) < 4.89E-15D) bool = true;
/*      */     
/*      */     } 
/* 2625 */     if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-15D) {
/* 2626 */       compute_2X2(paramArrayOfDouble1[0], paramArrayOfDouble2[0], paramArrayOfDouble1[1], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 0);
/*      */       
/* 2628 */       double d3 = paramArrayOfDouble3[0];
/* 2629 */       paramArrayOfDouble3[0] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[3];
/* 2630 */       paramArrayOfDouble3[3] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[3];
/* 2631 */       d3 = paramArrayOfDouble3[1];
/* 2632 */       paramArrayOfDouble3[1] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[4];
/* 2633 */       paramArrayOfDouble3[4] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[4];
/* 2634 */       d3 = paramArrayOfDouble3[2];
/* 2635 */       paramArrayOfDouble3[2] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[5];
/* 2636 */       paramArrayOfDouble3[5] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[5];
/*      */ 
/*      */ 
/*      */       
/* 2640 */       double d4 = paramArrayOfDouble4[0];
/* 2641 */       paramArrayOfDouble4[0] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[1];
/* 2642 */       paramArrayOfDouble4[1] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[1];
/* 2643 */       d4 = paramArrayOfDouble4[3];
/* 2644 */       paramArrayOfDouble4[3] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[4];
/* 2645 */       paramArrayOfDouble4[4] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[4];
/* 2646 */       d4 = paramArrayOfDouble4[6];
/* 2647 */       paramArrayOfDouble4[6] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[7];
/* 2648 */       paramArrayOfDouble4[7] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[7];
/*      */     } else {
/* 2650 */       compute_2X2(paramArrayOfDouble1[1], paramArrayOfDouble2[1], paramArrayOfDouble1[2], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 1);
/*      */       
/* 2652 */       double d3 = paramArrayOfDouble3[3];
/* 2653 */       paramArrayOfDouble3[3] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[6];
/* 2654 */       paramArrayOfDouble3[6] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[6];
/* 2655 */       d3 = paramArrayOfDouble3[4];
/* 2656 */       paramArrayOfDouble3[4] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[7];
/* 2657 */       paramArrayOfDouble3[7] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[7];
/* 2658 */       d3 = paramArrayOfDouble3[5];
/* 2659 */       paramArrayOfDouble3[5] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[8];
/* 2660 */       paramArrayOfDouble3[8] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[8];
/*      */ 
/*      */ 
/*      */       
/* 2664 */       double d4 = paramArrayOfDouble4[1];
/* 2665 */       paramArrayOfDouble4[1] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[2];
/* 2666 */       paramArrayOfDouble4[2] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[2];
/* 2667 */       d4 = paramArrayOfDouble4[4];
/* 2668 */       paramArrayOfDouble4[4] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[5];
/* 2669 */       paramArrayOfDouble4[5] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[5];
/* 2670 */       d4 = paramArrayOfDouble4[7];
/* 2671 */       paramArrayOfDouble4[7] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[8];
/* 2672 */       paramArrayOfDouble4[8] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[8];
/*      */     } 
/*      */     
/* 2675 */     return 0;
/*      */   }
/*      */   static double max(double paramDouble1, double paramDouble2) {
/* 2678 */     if (paramDouble1 > paramDouble2) {
/* 2679 */       return paramDouble1;
/*      */     }
/* 2681 */     return paramDouble2;
/*      */   }
/*      */   static double min(double paramDouble1, double paramDouble2) {
/* 2684 */     if (paramDouble1 < paramDouble2) {
/* 2685 */       return paramDouble1;
/*      */     }
/* 2687 */     return paramDouble2;
/*      */   }
/*      */   
/*      */   static double d_sign(double paramDouble1, double paramDouble2) {
/* 2691 */     double d = (paramDouble1 >= 0.0D) ? paramDouble1 : -paramDouble1;
/* 2692 */     return (paramDouble2 >= 0.0D) ? d : -d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static double compute_shift(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 2700 */     double d6, d3 = Math.abs(paramDouble1);
/* 2701 */     double d4 = Math.abs(paramDouble2);
/* 2702 */     double d5 = Math.abs(paramDouble3);
/* 2703 */     double d1 = min(d3, d5);
/* 2704 */     double d2 = max(d3, d5);
/* 2705 */     if (d1 == 0.0D) {
/* 2706 */       d6 = 0.0D;
/* 2707 */       if (d2 != 0.0D)
/*      */       {
/* 2709 */         double d = min(d2, d4) / max(d2, d4);
/*      */       }
/*      */     }
/* 2712 */     else if (d4 < d2) {
/* 2713 */       double d9 = d1 / d2 + 1.0D;
/* 2714 */       double d10 = (d2 - d1) / d2;
/* 2715 */       double d7 = d4 / d2;
/* 2716 */       double d11 = d7 * d7;
/* 2717 */       double d8 = 2.0D / (Math.sqrt(d9 * d9 + d11) + Math.sqrt(d10 * d10 + d11));
/* 2718 */       d6 = d1 * d8;
/*      */     } else {
/* 2720 */       double d = d2 / d4;
/* 2721 */       if (d == 0.0D) {
/* 2722 */         d6 = d1 * d2 / d4;
/*      */       } else {
/* 2724 */         double d10 = d1 / d2 + 1.0D;
/* 2725 */         double d11 = (d2 - d1) / d2;
/* 2726 */         double d7 = d10 * d;
/* 2727 */         double d8 = d11 * d;
/* 2728 */         double d9 = 1.0D / (Math.sqrt(d7 * d7 + 1.0D) + Math.sqrt(d8 * d8 + 1.0D));
/* 2729 */         d6 = d1 * d9 * d;
/* 2730 */         d6 += d6;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2735 */     return d6;
/*      */   }
/*      */   
/*      */   static int compute_2X2(double paramDouble1, double paramDouble2, double paramDouble3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, int paramInt) {
/*      */     boolean bool;
/* 2740 */     double d1 = 2.0D;
/* 2741 */     double d2 = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2753 */     double d15 = paramArrayOfDouble1[0];
/* 2754 */     double d14 = paramArrayOfDouble1[1];
/* 2755 */     double d10 = 0.0D;
/* 2756 */     double d11 = 0.0D;
/* 2757 */     double d12 = 0.0D;
/* 2758 */     double d13 = 0.0D;
/* 2759 */     double d3 = 0.0D;
/*      */     
/* 2761 */     double d7 = paramDouble1;
/* 2762 */     double d4 = Math.abs(d7);
/* 2763 */     double d9 = paramDouble3;
/* 2764 */     double d6 = Math.abs(paramDouble3);
/*      */     
/* 2766 */     byte b = 1;
/* 2767 */     if (d6 > d4) {
/* 2768 */       bool = true;
/*      */     } else {
/* 2770 */       bool = false;
/*      */     } 
/* 2772 */     if (bool) {
/* 2773 */       b = 3;
/* 2774 */       double d = d7;
/* 2775 */       d7 = d9;
/* 2776 */       d9 = d;
/* 2777 */       d = d4;
/* 2778 */       d4 = d6;
/* 2779 */       d6 = d;
/*      */     } 
/*      */     
/* 2782 */     double d8 = paramDouble2;
/* 2783 */     double d5 = Math.abs(d8);
/* 2784 */     if (d5 == 0.0D) {
/*      */       
/* 2786 */       paramArrayOfDouble1[1] = d6;
/* 2787 */       paramArrayOfDouble1[0] = d4;
/* 2788 */       d10 = 1.0D;
/* 2789 */       d11 = 1.0D;
/* 2790 */       d12 = 0.0D;
/* 2791 */       d13 = 0.0D;
/*      */     } else {
/* 2793 */       boolean bool1 = true;
/*      */       
/* 2795 */       if (d5 > d4) {
/* 2796 */         b = 2;
/* 2797 */         if (d4 / d5 < 1.110223024E-16D) {
/*      */           
/* 2799 */           bool1 = false;
/* 2800 */           d15 = d5;
/* 2801 */           if (d6 > 1.0D) {
/* 2802 */             d14 = d4 / d5 / d6;
/*      */           } else {
/* 2804 */             d14 = d4 / d5 * d6;
/*      */           } 
/* 2806 */           d10 = 1.0D;
/* 2807 */           d12 = d9 / d8;
/* 2808 */           d13 = 1.0D;
/* 2809 */           d11 = d7 / d8;
/*      */         } 
/*      */       } 
/* 2812 */       if (bool1) {
/*      */         
/* 2814 */         double d20, d18, d17 = d4 - d6;
/* 2815 */         if (d17 == d4) {
/*      */           
/* 2817 */           d18 = 1.0D;
/*      */         } else {
/* 2819 */           d18 = d17 / d4;
/*      */         } 
/*      */         
/* 2822 */         double d19 = d8 / d7;
/*      */         
/* 2824 */         double d22 = 2.0D - d18;
/*      */         
/* 2826 */         double d23 = d19 * d19;
/* 2827 */         double d24 = d22 * d22;
/* 2828 */         double d21 = Math.sqrt(d24 + d23);
/*      */         
/* 2830 */         if (d18 == 0.0D) {
/* 2831 */           d20 = Math.abs(d19);
/*      */         } else {
/* 2833 */           d20 = Math.sqrt(d18 * d18 + d23);
/*      */         } 
/*      */         
/* 2836 */         double d16 = (d21 + d20) * 0.5D;
/*      */         
/* 2838 */         if (d5 > d4) {
/* 2839 */           b = 2;
/* 2840 */           if (d4 / d5 < 1.110223024E-16D) {
/*      */             
/* 2842 */             bool1 = false;
/* 2843 */             d15 = d5;
/* 2844 */             if (d6 > 1.0D) {
/* 2845 */               d14 = d4 / d5 / d6;
/*      */             } else {
/* 2847 */               d14 = d4 / d5 * d6;
/*      */             } 
/* 2849 */             d10 = 1.0D;
/* 2850 */             d12 = d9 / d8;
/* 2851 */             d13 = 1.0D;
/* 2852 */             d11 = d7 / d8;
/*      */           } 
/*      */         } 
/* 2855 */         if (bool1) {
/*      */           
/* 2857 */           d17 = d4 - d6;
/* 2858 */           if (d17 == d4) {
/*      */             
/* 2860 */             d18 = 1.0D;
/*      */           } else {
/* 2862 */             d18 = d17 / d4;
/*      */           } 
/*      */           
/* 2865 */           d19 = d8 / d7;
/*      */           
/* 2867 */           d22 = 2.0D - d18;
/*      */           
/* 2869 */           d23 = d19 * d19;
/* 2870 */           d24 = d22 * d22;
/* 2871 */           d21 = Math.sqrt(d24 + d23);
/*      */           
/* 2873 */           if (d18 == 0.0D) {
/* 2874 */             d20 = Math.abs(d19);
/*      */           } else {
/* 2876 */             d20 = Math.sqrt(d18 * d18 + d23);
/*      */           } 
/*      */           
/* 2879 */           d16 = (d21 + d20) * 0.5D;
/*      */ 
/*      */           
/* 2882 */           d14 = d6 / d16;
/* 2883 */           d15 = d4 * d16;
/* 2884 */           if (d23 == 0.0D) {
/*      */             
/* 2886 */             if (d18 == 0.0D) {
/* 2887 */               d22 = d_sign(d1, d7) * d_sign(d2, d8);
/*      */             } else {
/* 2889 */               d22 = d8 / d_sign(d17, d7) + d19 / d22;
/*      */             } 
/*      */           } else {
/* 2892 */             d22 = (d19 / (d21 + d22) + d19 / (d20 + d18)) * (d16 + 1.0D);
/*      */           } 
/* 2894 */           d18 = Math.sqrt(d22 * d22 + 4.0D);
/* 2895 */           d11 = 2.0D / d18;
/* 2896 */           d13 = d22 / d18;
/* 2897 */           d10 = (d11 + d13 * d19) / d16;
/* 2898 */           d12 = d9 / d7 * d13 / d16;
/*      */         } 
/*      */       } 
/* 2901 */       if (bool) {
/* 2902 */         paramArrayOfDouble3[0] = d13;
/* 2903 */         paramArrayOfDouble2[0] = d11;
/* 2904 */         paramArrayOfDouble5[0] = d12;
/* 2905 */         paramArrayOfDouble4[0] = d10;
/*      */       } else {
/* 2907 */         paramArrayOfDouble3[0] = d10;
/* 2908 */         paramArrayOfDouble2[0] = d12;
/* 2909 */         paramArrayOfDouble5[0] = d11;
/* 2910 */         paramArrayOfDouble4[0] = d13;
/*      */       } 
/*      */       
/* 2913 */       if (b == 1) {
/* 2914 */         d3 = d_sign(d2, paramArrayOfDouble5[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble1);
/*      */       }
/* 2916 */       if (b == 2) {
/* 2917 */         d3 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble2);
/*      */       }
/* 2919 */       if (b == 3) {
/* 2920 */         d3 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble2[0]) * d_sign(d2, paramDouble3);
/*      */       }
/* 2922 */       paramArrayOfDouble1[paramInt] = d_sign(d15, d3);
/* 2923 */       double d = d3 * d_sign(d2, paramDouble1) * d_sign(d2, paramDouble3);
/* 2924 */       paramArrayOfDouble1[paramInt + 1] = d_sign(d14, d);
/*      */     } 
/*      */ 
/*      */     
/* 2928 */     return 0;
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
/*      */   static double compute_rot(double paramDouble1, double paramDouble2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2) {
/*      */     double d3, d2, d1;
/* 2942 */     if (paramDouble2 == 0.0D) {
/* 2943 */       d1 = 1.0D;
/* 2944 */       d2 = 0.0D;
/* 2945 */       d3 = paramDouble1;
/* 2946 */     } else if (paramDouble1 == 0.0D) {
/* 2947 */       d1 = 0.0D;
/* 2948 */       d2 = 1.0D;
/* 2949 */       d3 = paramDouble2;
/*      */     } else {
/* 2951 */       double d5 = paramDouble1;
/* 2952 */       double d6 = paramDouble2;
/* 2953 */       double d4 = max(Math.abs(d5), Math.abs(d6));
/* 2954 */       if (d4 >= 4.9947976805055876E145D) {
/* 2955 */         byte b3 = 0;
/* 2956 */         while (d4 >= 4.9947976805055876E145D) {
/* 2957 */           b3++;
/* 2958 */           d5 *= 2.002083095183101E-146D;
/* 2959 */           d6 *= 2.002083095183101E-146D;
/* 2960 */           d4 = max(Math.abs(d5), Math.abs(d6));
/*      */         } 
/* 2962 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 2963 */         d1 = d5 / d3;
/* 2964 */         d2 = d6 / d3;
/* 2965 */         byte b1 = b3;
/* 2966 */         for (byte b2 = 1; b2 <= b3; b2++) {
/* 2967 */           d3 *= 4.9947976805055876E145D;
/*      */         }
/* 2969 */       } else if (d4 <= 2.002083095183101E-146D) {
/* 2970 */         byte b3 = 0;
/* 2971 */         while (d4 <= 2.002083095183101E-146D) {
/* 2972 */           b3++;
/* 2973 */           d5 *= 4.9947976805055876E145D;
/* 2974 */           d6 *= 4.9947976805055876E145D;
/* 2975 */           d4 = max(Math.abs(d5), Math.abs(d6));
/*      */         } 
/* 2977 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 2978 */         d1 = d5 / d3;
/* 2979 */         d2 = d6 / d3;
/* 2980 */         byte b1 = b3;
/* 2981 */         for (byte b2 = 1; b2 <= b3; b2++) {
/* 2982 */           d3 *= 2.002083095183101E-146D;
/*      */         }
/*      */       } else {
/* 2985 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 2986 */         d1 = d5 / d3;
/* 2987 */         d2 = d6 / d3;
/*      */       } 
/* 2989 */       if (Math.abs(paramDouble1) > Math.abs(paramDouble2) && d1 < 0.0D) {
/* 2990 */         d1 = -d1;
/* 2991 */         d2 = -d2;
/* 2992 */         d3 = -d3;
/*      */       } 
/*      */     } 
/* 2995 */     paramArrayOfDouble1[paramInt1] = d2;
/* 2996 */     paramArrayOfDouble2[paramInt1] = d1;
/* 2997 */     return d3;
/*      */   }
/*      */ 
/*      */   
/*      */   static void print_mat(double[] paramArrayOfDouble) {
/* 3002 */     for (byte b = 0; b < 3; b++) {
/* 3003 */       System.out.println(paramArrayOfDouble[b * 3 + 0] + " " + paramArrayOfDouble[b * 3 + 1] + " " + paramArrayOfDouble[b * 3 + 2] + "\n");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void print_det(double[] paramArrayOfDouble) {
/* 3010 */     double d = paramArrayOfDouble[0] * paramArrayOfDouble[4] * paramArrayOfDouble[8] + paramArrayOfDouble[1] * paramArrayOfDouble[5] * paramArrayOfDouble[6] + paramArrayOfDouble[2] * paramArrayOfDouble[3] * paramArrayOfDouble[7] - paramArrayOfDouble[2] * paramArrayOfDouble[4] * paramArrayOfDouble[6] - paramArrayOfDouble[0] * paramArrayOfDouble[5] * paramArrayOfDouble[7] - paramArrayOfDouble[1] * paramArrayOfDouble[3] * paramArrayOfDouble[8];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3016 */     System.out.println("det= " + d);
/*      */   }
/*      */   
/*      */   static void mat_mul(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3) {
/* 3020 */     double[] arrayOfDouble = new double[9];
/*      */     
/* 3022 */     arrayOfDouble[0] = paramArrayOfDouble1[0] * paramArrayOfDouble2[0] + paramArrayOfDouble1[1] * paramArrayOfDouble2[3] + paramArrayOfDouble1[2] * paramArrayOfDouble2[6];
/* 3023 */     arrayOfDouble[1] = paramArrayOfDouble1[0] * paramArrayOfDouble2[1] + paramArrayOfDouble1[1] * paramArrayOfDouble2[4] + paramArrayOfDouble1[2] * paramArrayOfDouble2[7];
/* 3024 */     arrayOfDouble[2] = paramArrayOfDouble1[0] * paramArrayOfDouble2[2] + paramArrayOfDouble1[1] * paramArrayOfDouble2[5] + paramArrayOfDouble1[2] * paramArrayOfDouble2[8];
/*      */     
/* 3026 */     arrayOfDouble[3] = paramArrayOfDouble1[3] * paramArrayOfDouble2[0] + paramArrayOfDouble1[4] * paramArrayOfDouble2[3] + paramArrayOfDouble1[5] * paramArrayOfDouble2[6];
/* 3027 */     arrayOfDouble[4] = paramArrayOfDouble1[3] * paramArrayOfDouble2[1] + paramArrayOfDouble1[4] * paramArrayOfDouble2[4] + paramArrayOfDouble1[5] * paramArrayOfDouble2[7];
/* 3028 */     arrayOfDouble[5] = paramArrayOfDouble1[3] * paramArrayOfDouble2[2] + paramArrayOfDouble1[4] * paramArrayOfDouble2[5] + paramArrayOfDouble1[5] * paramArrayOfDouble2[8];
/*      */     
/* 3030 */     arrayOfDouble[6] = paramArrayOfDouble1[6] * paramArrayOfDouble2[0] + paramArrayOfDouble1[7] * paramArrayOfDouble2[3] + paramArrayOfDouble1[8] * paramArrayOfDouble2[6];
/* 3031 */     arrayOfDouble[7] = paramArrayOfDouble1[6] * paramArrayOfDouble2[1] + paramArrayOfDouble1[7] * paramArrayOfDouble2[4] + paramArrayOfDouble1[8] * paramArrayOfDouble2[7];
/* 3032 */     arrayOfDouble[8] = paramArrayOfDouble1[6] * paramArrayOfDouble2[2] + paramArrayOfDouble1[7] * paramArrayOfDouble2[5] + paramArrayOfDouble1[8] * paramArrayOfDouble2[8];
/*      */     
/* 3034 */     for (byte b = 0; b < 9; b++)
/* 3035 */       paramArrayOfDouble3[b] = arrayOfDouble[b]; 
/*      */   }
/*      */   
/*      */   static void transpose_mat(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 3039 */     paramArrayOfDouble2[0] = paramArrayOfDouble1[0];
/* 3040 */     paramArrayOfDouble2[1] = paramArrayOfDouble1[3];
/* 3041 */     paramArrayOfDouble2[2] = paramArrayOfDouble1[6];
/*      */     
/* 3043 */     paramArrayOfDouble2[3] = paramArrayOfDouble1[1];
/* 3044 */     paramArrayOfDouble2[4] = paramArrayOfDouble1[4];
/* 3045 */     paramArrayOfDouble2[5] = paramArrayOfDouble1[7];
/*      */     
/* 3047 */     paramArrayOfDouble2[6] = paramArrayOfDouble1[2];
/* 3048 */     paramArrayOfDouble2[7] = paramArrayOfDouble1[5];
/* 3049 */     paramArrayOfDouble2[8] = paramArrayOfDouble1[8];
/*      */   }
/*      */   static double max3(double[] paramArrayOfDouble) {
/* 3052 */     if (paramArrayOfDouble[0] > paramArrayOfDouble[1]) {
/* 3053 */       if (paramArrayOfDouble[0] > paramArrayOfDouble[2]) {
/* 3054 */         return paramArrayOfDouble[0];
/*      */       }
/* 3056 */       return paramArrayOfDouble[2];
/*      */     } 
/* 3058 */     if (paramArrayOfDouble[1] > paramArrayOfDouble[2]) {
/* 3059 */       return paramArrayOfDouble[1];
/*      */     }
/* 3061 */     return paramArrayOfDouble[2];
/*      */   }
/*      */ 
/*      */   
/*      */   private static final boolean almostEqual(double paramDouble1, double paramDouble2) {
/* 3066 */     if (paramDouble1 == paramDouble2) {
/* 3067 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 3071 */     double d1 = Math.abs(paramDouble1 - paramDouble2);
/* 3072 */     double d2 = Math.abs(paramDouble1);
/* 3073 */     double d3 = Math.abs(paramDouble2);
/* 3074 */     double d4 = (d2 >= d3) ? d2 : d3;
/*      */     
/* 3076 */     if (d1 < 1.0E-6D) {
/* 3077 */       return true;
/*      */     }
/* 3079 */     if (d1 / d4 < 1.0E-4D) {
/* 3080 */       return true;
/*      */     }
/* 3082 */     return false;
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
/*      */   public Object clone() {
/* 3094 */     Matrix3d matrix3d = null;
/*      */     try {
/* 3096 */       matrix3d = (Matrix3d)super.clone();
/* 3097 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*      */       
/* 3099 */       throw new InternalError();
/*      */     } 
/*      */ 
/*      */     
/* 3103 */     return matrix3d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3112 */   public final double getM00() { return this.m00; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3123 */   public final void setM00(double paramDouble) { this.m00 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3134 */   public final double getM01() { return this.m01; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3145 */   public final void setM01(double paramDouble) { this.m01 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3156 */   public final double getM02() { return this.m02; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3167 */   public final void setM02(double paramDouble) { this.m02 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3178 */   public final double getM10() { return this.m10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3189 */   public final void setM10(double paramDouble) { this.m10 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3200 */   public final double getM11() { return this.m11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3211 */   public final void setM11(double paramDouble) { this.m11 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3222 */   public final double getM12() { return this.m12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3233 */   public final void setM12(double paramDouble) { this.m12 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3244 */   public final double getM20() { return this.m20; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3255 */   public final void setM20(double paramDouble) { this.m20 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3266 */   public final double getM21() { return this.m21; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3277 */   public final void setM21(double paramDouble) { this.m21 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3288 */   public final double getM22() { return this.m22; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3299 */   public final void setM22(double paramDouble) { this.m22 = paramDouble; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Matrix3d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */