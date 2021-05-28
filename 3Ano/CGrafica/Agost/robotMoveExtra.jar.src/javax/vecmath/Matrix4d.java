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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Matrix4d
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   static final long serialVersionUID = 8223903484171633710L;
/*      */   public double m00;
/*      */   public double m01;
/*      */   public double m02;
/*      */   public double m03;
/*      */   public double m10;
/*      */   public double m11;
/*      */   public double m12;
/*      */   public double m13;
/*      */   public double m20;
/*      */   public double m21;
/*      */   public double m22;
/*      */   public double m23;
/*      */   public double m30;
/*      */   public double m31;
/*      */   public double m32;
/*      */   public double m33;
/*      */   private static final double EPS = 1.0E-10D;
/*      */   
/*      */   public Matrix4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9, double paramDouble10, double paramDouble11, double paramDouble12, double paramDouble13, double paramDouble14, double paramDouble15, double paramDouble16) {
/*  138 */     this.m00 = paramDouble1;
/*  139 */     this.m01 = paramDouble2;
/*  140 */     this.m02 = paramDouble3;
/*  141 */     this.m03 = paramDouble4;
/*      */     
/*  143 */     this.m10 = paramDouble5;
/*  144 */     this.m11 = paramDouble6;
/*  145 */     this.m12 = paramDouble7;
/*  146 */     this.m13 = paramDouble8;
/*      */     
/*  148 */     this.m20 = paramDouble9;
/*  149 */     this.m21 = paramDouble10;
/*  150 */     this.m22 = paramDouble11;
/*  151 */     this.m23 = paramDouble12;
/*      */     
/*  153 */     this.m30 = paramDouble13;
/*  154 */     this.m31 = paramDouble14;
/*  155 */     this.m32 = paramDouble15;
/*  156 */     this.m33 = paramDouble16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix4d(double[] paramArrayOfDouble) {
/*  167 */     this.m00 = paramArrayOfDouble[0];
/*  168 */     this.m01 = paramArrayOfDouble[1];
/*  169 */     this.m02 = paramArrayOfDouble[2];
/*  170 */     this.m03 = paramArrayOfDouble[3];
/*      */     
/*  172 */     this.m10 = paramArrayOfDouble[4];
/*  173 */     this.m11 = paramArrayOfDouble[5];
/*  174 */     this.m12 = paramArrayOfDouble[6];
/*  175 */     this.m13 = paramArrayOfDouble[7];
/*      */     
/*  177 */     this.m20 = paramArrayOfDouble[8];
/*  178 */     this.m21 = paramArrayOfDouble[9];
/*  179 */     this.m22 = paramArrayOfDouble[10];
/*  180 */     this.m23 = paramArrayOfDouble[11];
/*      */     
/*  182 */     this.m30 = paramArrayOfDouble[12];
/*  183 */     this.m31 = paramArrayOfDouble[13];
/*  184 */     this.m32 = paramArrayOfDouble[14];
/*  185 */     this.m33 = paramArrayOfDouble[15];
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
/*      */   public Matrix4d(Quat4d paramQuat4d, Vector3d paramVector3d, double paramDouble) {
/*  200 */     this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z);
/*  201 */     this.m10 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
/*  202 */     this.m20 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
/*      */     
/*  204 */     this.m01 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
/*  205 */     this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z);
/*  206 */     this.m21 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
/*      */     
/*  208 */     this.m02 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
/*  209 */     this.m12 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
/*  210 */     this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y);
/*      */     
/*  212 */     this.m03 = paramVector3d.x;
/*  213 */     this.m13 = paramVector3d.y;
/*  214 */     this.m23 = paramVector3d.z;
/*      */     
/*  216 */     this.m30 = 0.0D;
/*  217 */     this.m31 = 0.0D;
/*  218 */     this.m32 = 0.0D;
/*  219 */     this.m33 = 1.0D;
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
/*      */   public Matrix4d(Quat4f paramQuat4f, Vector3d paramVector3d, double paramDouble) {
/*  234 */     this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z);
/*  235 */     this.m10 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/*  236 */     this.m20 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/*  238 */     this.m01 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/*  239 */     this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z);
/*  240 */     this.m21 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/*  242 */     this.m02 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/*  243 */     this.m12 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/*  244 */     this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y);
/*      */     
/*  246 */     this.m03 = paramVector3d.x;
/*  247 */     this.m13 = paramVector3d.y;
/*  248 */     this.m23 = paramVector3d.z;
/*      */     
/*  250 */     this.m30 = 0.0D;
/*  251 */     this.m31 = 0.0D;
/*  252 */     this.m32 = 0.0D;
/*  253 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix4d(Matrix4d paramMatrix4d) {
/*  264 */     this.m00 = paramMatrix4d.m00;
/*  265 */     this.m01 = paramMatrix4d.m01;
/*  266 */     this.m02 = paramMatrix4d.m02;
/*  267 */     this.m03 = paramMatrix4d.m03;
/*      */     
/*  269 */     this.m10 = paramMatrix4d.m10;
/*  270 */     this.m11 = paramMatrix4d.m11;
/*  271 */     this.m12 = paramMatrix4d.m12;
/*  272 */     this.m13 = paramMatrix4d.m13;
/*      */     
/*  274 */     this.m20 = paramMatrix4d.m20;
/*  275 */     this.m21 = paramMatrix4d.m21;
/*  276 */     this.m22 = paramMatrix4d.m22;
/*  277 */     this.m23 = paramMatrix4d.m23;
/*      */     
/*  279 */     this.m30 = paramMatrix4d.m30;
/*  280 */     this.m31 = paramMatrix4d.m31;
/*  281 */     this.m32 = paramMatrix4d.m32;
/*  282 */     this.m33 = paramMatrix4d.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix4d(Matrix4f paramMatrix4f) {
/*  293 */     this.m00 = paramMatrix4f.m00;
/*  294 */     this.m01 = paramMatrix4f.m01;
/*  295 */     this.m02 = paramMatrix4f.m02;
/*  296 */     this.m03 = paramMatrix4f.m03;
/*      */     
/*  298 */     this.m10 = paramMatrix4f.m10;
/*  299 */     this.m11 = paramMatrix4f.m11;
/*  300 */     this.m12 = paramMatrix4f.m12;
/*  301 */     this.m13 = paramMatrix4f.m13;
/*      */     
/*  303 */     this.m20 = paramMatrix4f.m20;
/*  304 */     this.m21 = paramMatrix4f.m21;
/*  305 */     this.m22 = paramMatrix4f.m22;
/*  306 */     this.m23 = paramMatrix4f.m23;
/*      */     
/*  308 */     this.m30 = paramMatrix4f.m30;
/*  309 */     this.m31 = paramMatrix4f.m31;
/*  310 */     this.m32 = paramMatrix4f.m32;
/*  311 */     this.m33 = paramMatrix4f.m33;
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
/*      */   public Matrix4d(Matrix3f paramMatrix3f, Vector3d paramVector3d, double paramDouble) {
/*  326 */     this.m00 = paramMatrix3f.m00 * paramDouble;
/*  327 */     this.m01 = paramMatrix3f.m01 * paramDouble;
/*  328 */     this.m02 = paramMatrix3f.m02 * paramDouble;
/*  329 */     this.m03 = paramVector3d.x;
/*      */     
/*  331 */     this.m10 = paramMatrix3f.m10 * paramDouble;
/*  332 */     this.m11 = paramMatrix3f.m11 * paramDouble;
/*  333 */     this.m12 = paramMatrix3f.m12 * paramDouble;
/*  334 */     this.m13 = paramVector3d.y;
/*      */     
/*  336 */     this.m20 = paramMatrix3f.m20 * paramDouble;
/*  337 */     this.m21 = paramMatrix3f.m21 * paramDouble;
/*  338 */     this.m22 = paramMatrix3f.m22 * paramDouble;
/*  339 */     this.m23 = paramVector3d.z;
/*      */     
/*  341 */     this.m30 = 0.0D;
/*  342 */     this.m31 = 0.0D;
/*  343 */     this.m32 = 0.0D;
/*  344 */     this.m33 = 1.0D;
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
/*      */   public Matrix4d(Matrix3d paramMatrix3d, Vector3d paramVector3d, double paramDouble) {
/*  359 */     this.m00 = paramMatrix3d.m00 * paramDouble;
/*  360 */     this.m01 = paramMatrix3d.m01 * paramDouble;
/*  361 */     this.m02 = paramMatrix3d.m02 * paramDouble;
/*  362 */     this.m03 = paramVector3d.x;
/*      */     
/*  364 */     this.m10 = paramMatrix3d.m10 * paramDouble;
/*  365 */     this.m11 = paramMatrix3d.m11 * paramDouble;
/*  366 */     this.m12 = paramMatrix3d.m12 * paramDouble;
/*  367 */     this.m13 = paramVector3d.y;
/*      */     
/*  369 */     this.m20 = paramMatrix3d.m20 * paramDouble;
/*  370 */     this.m21 = paramMatrix3d.m21 * paramDouble;
/*  371 */     this.m22 = paramMatrix3d.m22 * paramDouble;
/*  372 */     this.m23 = paramVector3d.z;
/*      */     
/*  374 */     this.m30 = 0.0D;
/*  375 */     this.m31 = 0.0D;
/*  376 */     this.m32 = 0.0D;
/*  377 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix4d() {
/*  386 */     this.m00 = 0.0D;
/*  387 */     this.m01 = 0.0D;
/*  388 */     this.m02 = 0.0D;
/*  389 */     this.m03 = 0.0D;
/*      */     
/*  391 */     this.m10 = 0.0D;
/*  392 */     this.m11 = 0.0D;
/*  393 */     this.m12 = 0.0D;
/*  394 */     this.m13 = 0.0D;
/*      */     
/*  396 */     this.m20 = 0.0D;
/*  397 */     this.m21 = 0.0D;
/*  398 */     this.m22 = 0.0D;
/*  399 */     this.m23 = 0.0D;
/*      */     
/*  401 */     this.m30 = 0.0D;
/*  402 */     this.m31 = 0.0D;
/*  403 */     this.m32 = 0.0D;
/*  404 */     this.m33 = 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  413 */   public String toString() { return this.m00 + ", " + this.m01 + ", " + this.m02 + ", " + this.m03 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + ", " + this.m13 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + ", " + this.m23 + "\n" + this.m30 + ", " + this.m31 + ", " + this.m32 + ", " + this.m33 + "\n"; }
/*      */ 
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
/*  425 */     this.m00 = 1.0D;
/*  426 */     this.m01 = 0.0D;
/*  427 */     this.m02 = 0.0D;
/*  428 */     this.m03 = 0.0D;
/*      */     
/*  430 */     this.m10 = 0.0D;
/*  431 */     this.m11 = 1.0D;
/*  432 */     this.m12 = 0.0D;
/*  433 */     this.m13 = 0.0D;
/*      */     
/*  435 */     this.m20 = 0.0D;
/*  436 */     this.m21 = 0.0D;
/*  437 */     this.m22 = 1.0D;
/*  438 */     this.m23 = 0.0D;
/*      */     
/*  440 */     this.m30 = 0.0D;
/*  441 */     this.m31 = 0.0D;
/*  442 */     this.m32 = 0.0D;
/*  443 */     this.m33 = 1.0D;
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
/*  454 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  457 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  460 */             this.m00 = paramDouble;
/*      */             return;
/*      */           case 1:
/*  463 */             this.m01 = paramDouble;
/*      */             return;
/*      */           case 2:
/*  466 */             this.m02 = paramDouble;
/*      */             return;
/*      */           case 3:
/*  469 */             this.m03 = paramDouble;
/*      */             return;
/*      */         } 
/*  472 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  477 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  480 */             this.m10 = paramDouble;
/*      */             return;
/*      */           case 1:
/*  483 */             this.m11 = paramDouble;
/*      */             return;
/*      */           case 2:
/*  486 */             this.m12 = paramDouble;
/*      */             return;
/*      */           case 3:
/*  489 */             this.m13 = paramDouble;
/*      */             return;
/*      */         } 
/*  492 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  497 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  500 */             this.m20 = paramDouble;
/*      */             return;
/*      */           case 1:
/*  503 */             this.m21 = paramDouble;
/*      */             return;
/*      */           case 2:
/*  506 */             this.m22 = paramDouble;
/*      */             return;
/*      */           case 3:
/*  509 */             this.m23 = paramDouble;
/*      */             return;
/*      */         } 
/*  512 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*  517 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  520 */             this.m30 = paramDouble;
/*      */             return;
/*      */           case 1:
/*  523 */             this.m31 = paramDouble;
/*      */             return;
/*      */           case 2:
/*  526 */             this.m32 = paramDouble;
/*      */             return;
/*      */           case 3:
/*  529 */             this.m33 = paramDouble;
/*      */             return;
/*      */         } 
/*  532 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  537 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
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
/*      */   public final double getElement(int paramInt1, int paramInt2) {
/*  549 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  552 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  555 */             return this.m00;
/*      */           case 1:
/*  557 */             return this.m01;
/*      */           case 2:
/*  559 */             return this.m02;
/*      */           case 3:
/*  561 */             return this.m03;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case 1:
/*  567 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  570 */             return this.m10;
/*      */           case 1:
/*  572 */             return this.m11;
/*      */           case 2:
/*  574 */             return this.m12;
/*      */           case 3:
/*  576 */             return this.m13;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 2:
/*  583 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  586 */             return this.m20;
/*      */           case 1:
/*  588 */             return this.m21;
/*      */           case 2:
/*  590 */             return this.m22;
/*      */           case 3:
/*  592 */             return this.m23;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 3:
/*  599 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  602 */             return this.m30;
/*      */           case 1:
/*  604 */             return this.m31;
/*      */           case 2:
/*  606 */             return this.m32;
/*      */           case 3:
/*  608 */             return this.m33;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  617 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d1"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, Vector4d paramVector4d) {
/*  626 */     if (paramInt == 0) {
/*  627 */       paramVector4d.x = this.m00;
/*  628 */       paramVector4d.y = this.m01;
/*  629 */       paramVector4d.z = this.m02;
/*  630 */       paramVector4d.w = this.m03;
/*  631 */     } else if (paramInt == 1) {
/*  632 */       paramVector4d.x = this.m10;
/*  633 */       paramVector4d.y = this.m11;
/*  634 */       paramVector4d.z = this.m12;
/*  635 */       paramVector4d.w = this.m13;
/*  636 */     } else if (paramInt == 2) {
/*  637 */       paramVector4d.x = this.m20;
/*  638 */       paramVector4d.y = this.m21;
/*  639 */       paramVector4d.z = this.m22;
/*  640 */       paramVector4d.w = this.m23;
/*  641 */     } else if (paramInt == 3) {
/*  642 */       paramVector4d.x = this.m30;
/*  643 */       paramVector4d.y = this.m31;
/*  644 */       paramVector4d.z = this.m32;
/*  645 */       paramVector4d.w = this.m33;
/*      */     } else {
/*  647 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d2"));
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
/*  658 */     if (paramInt == 0) {
/*  659 */       paramArrayOfDouble[0] = this.m00;
/*  660 */       paramArrayOfDouble[1] = this.m01;
/*  661 */       paramArrayOfDouble[2] = this.m02;
/*  662 */       paramArrayOfDouble[3] = this.m03;
/*  663 */     } else if (paramInt == 1) {
/*  664 */       paramArrayOfDouble[0] = this.m10;
/*  665 */       paramArrayOfDouble[1] = this.m11;
/*  666 */       paramArrayOfDouble[2] = this.m12;
/*  667 */       paramArrayOfDouble[3] = this.m13;
/*  668 */     } else if (paramInt == 2) {
/*  669 */       paramArrayOfDouble[0] = this.m20;
/*  670 */       paramArrayOfDouble[1] = this.m21;
/*  671 */       paramArrayOfDouble[2] = this.m22;
/*  672 */       paramArrayOfDouble[3] = this.m23;
/*  673 */     } else if (paramInt == 3) {
/*  674 */       paramArrayOfDouble[0] = this.m30;
/*  675 */       paramArrayOfDouble[1] = this.m31;
/*  676 */       paramArrayOfDouble[2] = this.m32;
/*  677 */       paramArrayOfDouble[3] = this.m33;
/*      */     } else {
/*      */       
/*  680 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d2"));
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
/*      */   public final void getColumn(int paramInt, Vector4d paramVector4d) {
/*  693 */     if (paramInt == 0) {
/*  694 */       paramVector4d.x = this.m00;
/*  695 */       paramVector4d.y = this.m10;
/*  696 */       paramVector4d.z = this.m20;
/*  697 */       paramVector4d.w = this.m30;
/*  698 */     } else if (paramInt == 1) {
/*  699 */       paramVector4d.x = this.m01;
/*  700 */       paramVector4d.y = this.m11;
/*  701 */       paramVector4d.z = this.m21;
/*  702 */       paramVector4d.w = this.m31;
/*  703 */     } else if (paramInt == 2) {
/*  704 */       paramVector4d.x = this.m02;
/*  705 */       paramVector4d.y = this.m12;
/*  706 */       paramVector4d.z = this.m22;
/*  707 */       paramVector4d.w = this.m32;
/*  708 */     } else if (paramInt == 3) {
/*  709 */       paramVector4d.x = this.m03;
/*  710 */       paramVector4d.y = this.m13;
/*  711 */       paramVector4d.z = this.m23;
/*  712 */       paramVector4d.w = this.m33;
/*      */     } else {
/*  714 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d3"));
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
/*      */   public final void getColumn(int paramInt, double[] paramArrayOfDouble) {
/*  729 */     if (paramInt == 0) {
/*  730 */       paramArrayOfDouble[0] = this.m00;
/*  731 */       paramArrayOfDouble[1] = this.m10;
/*  732 */       paramArrayOfDouble[2] = this.m20;
/*  733 */       paramArrayOfDouble[3] = this.m30;
/*  734 */     } else if (paramInt == 1) {
/*  735 */       paramArrayOfDouble[0] = this.m01;
/*  736 */       paramArrayOfDouble[1] = this.m11;
/*  737 */       paramArrayOfDouble[2] = this.m21;
/*  738 */       paramArrayOfDouble[3] = this.m31;
/*  739 */     } else if (paramInt == 2) {
/*  740 */       paramArrayOfDouble[0] = this.m02;
/*  741 */       paramArrayOfDouble[1] = this.m12;
/*  742 */       paramArrayOfDouble[2] = this.m22;
/*  743 */       paramArrayOfDouble[3] = this.m32;
/*  744 */     } else if (paramInt == 3) {
/*  745 */       paramArrayOfDouble[0] = this.m03;
/*  746 */       paramArrayOfDouble[1] = this.m13;
/*  747 */       paramArrayOfDouble[2] = this.m23;
/*  748 */       paramArrayOfDouble[3] = this.m33;
/*      */     } else {
/*  750 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d3"));
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
/*      */   public final void get(Matrix3d paramMatrix3d) {
/*  766 */     double[] arrayOfDouble1 = new double[9];
/*  767 */     double[] arrayOfDouble2 = new double[3];
/*  768 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  770 */     paramMatrix3d.m00 = arrayOfDouble1[0];
/*  771 */     paramMatrix3d.m01 = arrayOfDouble1[1];
/*  772 */     paramMatrix3d.m02 = arrayOfDouble1[2];
/*      */     
/*  774 */     paramMatrix3d.m10 = arrayOfDouble1[3];
/*  775 */     paramMatrix3d.m11 = arrayOfDouble1[4];
/*  776 */     paramMatrix3d.m12 = arrayOfDouble1[5];
/*      */     
/*  778 */     paramMatrix3d.m20 = arrayOfDouble1[6];
/*  779 */     paramMatrix3d.m21 = arrayOfDouble1[7];
/*  780 */     paramMatrix3d.m22 = arrayOfDouble1[8];
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
/*      */   public final void get(Matrix3f paramMatrix3f) {
/*  793 */     double[] arrayOfDouble1 = new double[9];
/*  794 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  796 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  798 */     paramMatrix3f.m00 = (float)arrayOfDouble1[0];
/*  799 */     paramMatrix3f.m01 = (float)arrayOfDouble1[1];
/*  800 */     paramMatrix3f.m02 = (float)arrayOfDouble1[2];
/*      */     
/*  802 */     paramMatrix3f.m10 = (float)arrayOfDouble1[3];
/*  803 */     paramMatrix3f.m11 = (float)arrayOfDouble1[4];
/*  804 */     paramMatrix3f.m12 = (float)arrayOfDouble1[5];
/*      */     
/*  806 */     paramMatrix3f.m20 = (float)arrayOfDouble1[6];
/*  807 */     paramMatrix3f.m21 = (float)arrayOfDouble1[7];
/*  808 */     paramMatrix3f.m22 = (float)arrayOfDouble1[8];
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
/*      */   public final double get(Matrix3d paramMatrix3d, Vector3d paramVector3d) {
/*  822 */     double[] arrayOfDouble1 = new double[9];
/*  823 */     double[] arrayOfDouble2 = new double[3];
/*  824 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  826 */     paramMatrix3d.m00 = arrayOfDouble1[0];
/*  827 */     paramMatrix3d.m01 = arrayOfDouble1[1];
/*  828 */     paramMatrix3d.m02 = arrayOfDouble1[2];
/*      */     
/*  830 */     paramMatrix3d.m10 = arrayOfDouble1[3];
/*  831 */     paramMatrix3d.m11 = arrayOfDouble1[4];
/*  832 */     paramMatrix3d.m12 = arrayOfDouble1[5];
/*      */     
/*  834 */     paramMatrix3d.m20 = arrayOfDouble1[6];
/*  835 */     paramMatrix3d.m21 = arrayOfDouble1[7];
/*  836 */     paramMatrix3d.m22 = arrayOfDouble1[8];
/*      */     
/*  838 */     paramVector3d.x = this.m03;
/*  839 */     paramVector3d.y = this.m13;
/*  840 */     paramVector3d.z = this.m23;
/*      */     
/*  842 */     return Matrix3d.max3(arrayOfDouble2);
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
/*      */   public final double get(Matrix3f paramMatrix3f, Vector3d paramVector3d) {
/*  856 */     double[] arrayOfDouble1 = new double[9];
/*  857 */     double[] arrayOfDouble2 = new double[3];
/*  858 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  860 */     paramMatrix3f.m00 = (float)arrayOfDouble1[0];
/*  861 */     paramMatrix3f.m01 = (float)arrayOfDouble1[1];
/*  862 */     paramMatrix3f.m02 = (float)arrayOfDouble1[2];
/*      */     
/*  864 */     paramMatrix3f.m10 = (float)arrayOfDouble1[3];
/*  865 */     paramMatrix3f.m11 = (float)arrayOfDouble1[4];
/*  866 */     paramMatrix3f.m12 = (float)arrayOfDouble1[5];
/*      */     
/*  868 */     paramMatrix3f.m20 = (float)arrayOfDouble1[6];
/*  869 */     paramMatrix3f.m21 = (float)arrayOfDouble1[7];
/*  870 */     paramMatrix3f.m22 = (float)arrayOfDouble1[8];
/*      */     
/*  872 */     paramVector3d.x = this.m03;
/*  873 */     paramVector3d.y = this.m13;
/*  874 */     paramVector3d.z = this.m23;
/*      */     
/*  876 */     return Matrix3d.max3(arrayOfDouble2);
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
/*      */   public final void get(Quat4f paramQuat4f) {
/*  889 */     double[] arrayOfDouble1 = new double[9];
/*  890 */     double[] arrayOfDouble2 = new double[3];
/*  891 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */ 
/*      */ 
/*      */     
/*  895 */     double d = 0.25D * (1.0D + arrayOfDouble1[0] + arrayOfDouble1[4] + arrayOfDouble1[8]);
/*  896 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  897 */       paramQuat4f.w = (float)Math.sqrt(d);
/*  898 */       d = 0.25D / paramQuat4f.w;
/*  899 */       paramQuat4f.x = (float)((arrayOfDouble1[7] - arrayOfDouble1[5]) * d);
/*  900 */       paramQuat4f.y = (float)((arrayOfDouble1[2] - arrayOfDouble1[6]) * d);
/*  901 */       paramQuat4f.z = (float)((arrayOfDouble1[3] - arrayOfDouble1[1]) * d);
/*      */       
/*      */       return;
/*      */     } 
/*  905 */     paramQuat4f.w = 0.0F;
/*  906 */     d = -0.5D * (arrayOfDouble1[4] + arrayOfDouble1[8]);
/*  907 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  908 */       paramQuat4f.x = (float)Math.sqrt(d);
/*  909 */       d = 0.5D / paramQuat4f.x;
/*  910 */       paramQuat4f.y = (float)(arrayOfDouble1[3] * d);
/*  911 */       paramQuat4f.z = (float)(arrayOfDouble1[6] * d);
/*      */       
/*      */       return;
/*      */     } 
/*  915 */     paramQuat4f.x = 0.0F;
/*  916 */     d = 0.5D * (1.0D - arrayOfDouble1[8]);
/*  917 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  918 */       paramQuat4f.y = (float)Math.sqrt(d);
/*  919 */       paramQuat4f.z = (float)(arrayOfDouble1[7] / 2.0D * paramQuat4f.y);
/*      */       
/*      */       return;
/*      */     } 
/*  923 */     paramQuat4f.y = 0.0F;
/*  924 */     paramQuat4f.z = 1.0F;
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
/*      */   public final void get(Quat4d paramQuat4d) {
/*  936 */     double[] arrayOfDouble1 = new double[9];
/*  937 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  939 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */ 
/*      */ 
/*      */     
/*  943 */     double d = 0.25D * (1.0D + arrayOfDouble1[0] + arrayOfDouble1[4] + arrayOfDouble1[8]);
/*  944 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  945 */       paramQuat4d.w = Math.sqrt(d);
/*  946 */       d = 0.25D / paramQuat4d.w;
/*  947 */       paramQuat4d.x = (arrayOfDouble1[7] - arrayOfDouble1[5]) * d;
/*  948 */       paramQuat4d.y = (arrayOfDouble1[2] - arrayOfDouble1[6]) * d;
/*  949 */       paramQuat4d.z = (arrayOfDouble1[3] - arrayOfDouble1[1]) * d;
/*      */       
/*      */       return;
/*      */     } 
/*  953 */     paramQuat4d.w = 0.0D;
/*  954 */     d = -0.5D * (arrayOfDouble1[4] + arrayOfDouble1[8]);
/*  955 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  956 */       paramQuat4d.x = Math.sqrt(d);
/*  957 */       d = 0.5D / paramQuat4d.x;
/*  958 */       paramQuat4d.y = arrayOfDouble1[3] * d;
/*  959 */       paramQuat4d.z = arrayOfDouble1[6] * d;
/*      */       
/*      */       return;
/*      */     } 
/*  963 */     paramQuat4d.x = 0.0D;
/*  964 */     d = 0.5D * (1.0D - arrayOfDouble1[8]);
/*  965 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  966 */       paramQuat4d.y = Math.sqrt(d);
/*  967 */       paramQuat4d.z = arrayOfDouble1[7] / 2.0D * paramQuat4d.y;
/*      */       
/*      */       return;
/*      */     } 
/*  971 */     paramQuat4d.y = 0.0D;
/*  972 */     paramQuat4d.z = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Vector3d paramVector3d) {
/*  981 */     paramVector3d.x = this.m03;
/*  982 */     paramVector3d.y = this.m13;
/*  983 */     paramVector3d.z = this.m23;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRotationScale(Matrix3f paramMatrix3f) {
/*  993 */     paramMatrix3f.m00 = (float)this.m00; paramMatrix3f.m01 = (float)this.m01; paramMatrix3f.m02 = (float)this.m02;
/*  994 */     paramMatrix3f.m10 = (float)this.m10; paramMatrix3f.m11 = (float)this.m11; paramMatrix3f.m12 = (float)this.m12;
/*  995 */     paramMatrix3f.m20 = (float)this.m20; paramMatrix3f.m21 = (float)this.m21; paramMatrix3f.m22 = (float)this.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRotationScale(Matrix3d paramMatrix3d) {
/* 1005 */     paramMatrix3d.m00 = this.m00; paramMatrix3d.m01 = this.m01; paramMatrix3d.m02 = this.m02;
/* 1006 */     paramMatrix3d.m10 = this.m10; paramMatrix3d.m11 = this.m11; paramMatrix3d.m12 = this.m12;
/* 1007 */     paramMatrix3d.m20 = this.m20; paramMatrix3d.m21 = this.m21; paramMatrix3d.m22 = this.m22;
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
/*      */   public final double getScale() {
/* 1020 */     double[] arrayOfDouble1 = new double[9];
/* 1021 */     double[] arrayOfDouble2 = new double[3];
/* 1022 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 1024 */     return Matrix3d.max3(arrayOfDouble2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRotationScale(Matrix3d paramMatrix3d) {
/* 1035 */     this.m00 = paramMatrix3d.m00; this.m01 = paramMatrix3d.m01; this.m02 = paramMatrix3d.m02;
/* 1036 */     this.m10 = paramMatrix3d.m10; this.m11 = paramMatrix3d.m11; this.m12 = paramMatrix3d.m12;
/* 1037 */     this.m20 = paramMatrix3d.m20; this.m21 = paramMatrix3d.m21; this.m22 = paramMatrix3d.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRotationScale(Matrix3f paramMatrix3f) {
/* 1047 */     this.m00 = paramMatrix3f.m00; this.m01 = paramMatrix3f.m01; this.m02 = paramMatrix3f.m02;
/* 1048 */     this.m10 = paramMatrix3f.m10; this.m11 = paramMatrix3f.m11; this.m12 = paramMatrix3f.m12;
/* 1049 */     this.m20 = paramMatrix3f.m20; this.m21 = paramMatrix3f.m21; this.m22 = paramMatrix3f.m22;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setScale(double paramDouble) {
/* 1060 */     double[] arrayOfDouble1 = new double[9];
/* 1061 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 1063 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 1065 */     this.m00 = arrayOfDouble1[0] * paramDouble;
/* 1066 */     this.m01 = arrayOfDouble1[1] * paramDouble;
/* 1067 */     this.m02 = arrayOfDouble1[2] * paramDouble;
/*      */     
/* 1069 */     this.m10 = arrayOfDouble1[3] * paramDouble;
/* 1070 */     this.m11 = arrayOfDouble1[4] * paramDouble;
/* 1071 */     this.m12 = arrayOfDouble1[5] * paramDouble;
/*      */     
/* 1073 */     this.m20 = arrayOfDouble1[6] * paramDouble;
/* 1074 */     this.m21 = arrayOfDouble1[7] * paramDouble;
/* 1075 */     this.m22 = arrayOfDouble1[8] * paramDouble;
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
/*      */   public final void setRow(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 1089 */     switch (paramInt) {
/*      */       case 0:
/* 1091 */         this.m00 = paramDouble1;
/* 1092 */         this.m01 = paramDouble2;
/* 1093 */         this.m02 = paramDouble3;
/* 1094 */         this.m03 = paramDouble4;
/*      */         return;
/*      */       
/*      */       case 1:
/* 1098 */         this.m10 = paramDouble1;
/* 1099 */         this.m11 = paramDouble2;
/* 1100 */         this.m12 = paramDouble3;
/* 1101 */         this.m13 = paramDouble4;
/*      */         return;
/*      */       
/*      */       case 2:
/* 1105 */         this.m20 = paramDouble1;
/* 1106 */         this.m21 = paramDouble2;
/* 1107 */         this.m22 = paramDouble3;
/* 1108 */         this.m23 = paramDouble4;
/*      */         return;
/*      */       
/*      */       case 3:
/* 1112 */         this.m30 = paramDouble1;
/* 1113 */         this.m31 = paramDouble2;
/* 1114 */         this.m32 = paramDouble3;
/* 1115 */         this.m33 = paramDouble4;
/*      */         return;
/*      */     } 
/*      */     
/* 1119 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d4"));
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
/*      */   public final void setRow(int paramInt, Vector4d paramVector4d) {
/* 1131 */     switch (paramInt) {
/*      */       case 0:
/* 1133 */         this.m00 = paramVector4d.x;
/* 1134 */         this.m01 = paramVector4d.y;
/* 1135 */         this.m02 = paramVector4d.z;
/* 1136 */         this.m03 = paramVector4d.w;
/*      */         return;
/*      */       
/*      */       case 1:
/* 1140 */         this.m10 = paramVector4d.x;
/* 1141 */         this.m11 = paramVector4d.y;
/* 1142 */         this.m12 = paramVector4d.z;
/* 1143 */         this.m13 = paramVector4d.w;
/*      */         return;
/*      */       
/*      */       case 2:
/* 1147 */         this.m20 = paramVector4d.x;
/* 1148 */         this.m21 = paramVector4d.y;
/* 1149 */         this.m22 = paramVector4d.z;
/* 1150 */         this.m23 = paramVector4d.w;
/*      */         return;
/*      */       
/*      */       case 3:
/* 1154 */         this.m30 = paramVector4d.x;
/* 1155 */         this.m31 = paramVector4d.y;
/* 1156 */         this.m32 = paramVector4d.z;
/* 1157 */         this.m33 = paramVector4d.w;
/*      */         return;
/*      */     } 
/*      */     
/* 1161 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d4"));
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
/* 1172 */     switch (paramInt) {
/*      */       case 0:
/* 1174 */         this.m00 = paramArrayOfDouble[0];
/* 1175 */         this.m01 = paramArrayOfDouble[1];
/* 1176 */         this.m02 = paramArrayOfDouble[2];
/* 1177 */         this.m03 = paramArrayOfDouble[3];
/*      */         return;
/*      */       
/*      */       case 1:
/* 1181 */         this.m10 = paramArrayOfDouble[0];
/* 1182 */         this.m11 = paramArrayOfDouble[1];
/* 1183 */         this.m12 = paramArrayOfDouble[2];
/* 1184 */         this.m13 = paramArrayOfDouble[3];
/*      */         return;
/*      */       
/*      */       case 2:
/* 1188 */         this.m20 = paramArrayOfDouble[0];
/* 1189 */         this.m21 = paramArrayOfDouble[1];
/* 1190 */         this.m22 = paramArrayOfDouble[2];
/* 1191 */         this.m23 = paramArrayOfDouble[3];
/*      */         return;
/*      */       
/*      */       case 3:
/* 1195 */         this.m30 = paramArrayOfDouble[0];
/* 1196 */         this.m31 = paramArrayOfDouble[1];
/* 1197 */         this.m32 = paramArrayOfDouble[2];
/* 1198 */         this.m33 = paramArrayOfDouble[3];
/*      */         return;
/*      */     } 
/*      */     
/* 1202 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d4"));
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
/*      */   public final void setColumn(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 1216 */     switch (paramInt) {
/*      */       case 0:
/* 1218 */         this.m00 = paramDouble1;
/* 1219 */         this.m10 = paramDouble2;
/* 1220 */         this.m20 = paramDouble3;
/* 1221 */         this.m30 = paramDouble4;
/*      */         return;
/*      */       
/*      */       case 1:
/* 1225 */         this.m01 = paramDouble1;
/* 1226 */         this.m11 = paramDouble2;
/* 1227 */         this.m21 = paramDouble3;
/* 1228 */         this.m31 = paramDouble4;
/*      */         return;
/*      */       
/*      */       case 2:
/* 1232 */         this.m02 = paramDouble1;
/* 1233 */         this.m12 = paramDouble2;
/* 1234 */         this.m22 = paramDouble3;
/* 1235 */         this.m32 = paramDouble4;
/*      */         return;
/*      */       
/*      */       case 3:
/* 1239 */         this.m03 = paramDouble1;
/* 1240 */         this.m13 = paramDouble2;
/* 1241 */         this.m23 = paramDouble3;
/* 1242 */         this.m33 = paramDouble4;
/*      */         return;
/*      */     } 
/*      */     
/* 1246 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d7"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setColumn(int paramInt, Vector4d paramVector4d) {
/* 1257 */     switch (paramInt) {
/*      */       case 0:
/* 1259 */         this.m00 = paramVector4d.x;
/* 1260 */         this.m10 = paramVector4d.y;
/* 1261 */         this.m20 = paramVector4d.z;
/* 1262 */         this.m30 = paramVector4d.w;
/*      */         return;
/*      */       
/*      */       case 1:
/* 1266 */         this.m01 = paramVector4d.x;
/* 1267 */         this.m11 = paramVector4d.y;
/* 1268 */         this.m21 = paramVector4d.z;
/* 1269 */         this.m31 = paramVector4d.w;
/*      */         return;
/*      */       
/*      */       case 2:
/* 1273 */         this.m02 = paramVector4d.x;
/* 1274 */         this.m12 = paramVector4d.y;
/* 1275 */         this.m22 = paramVector4d.z;
/* 1276 */         this.m32 = paramVector4d.w;
/*      */         return;
/*      */       
/*      */       case 3:
/* 1280 */         this.m03 = paramVector4d.x;
/* 1281 */         this.m13 = paramVector4d.y;
/* 1282 */         this.m23 = paramVector4d.z;
/* 1283 */         this.m33 = paramVector4d.w;
/*      */         return;
/*      */     } 
/*      */     
/* 1287 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d7"));
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
/* 1298 */     switch (paramInt) {
/*      */       case 0:
/* 1300 */         this.m00 = paramArrayOfDouble[0];
/* 1301 */         this.m10 = paramArrayOfDouble[1];
/* 1302 */         this.m20 = paramArrayOfDouble[2];
/* 1303 */         this.m30 = paramArrayOfDouble[3];
/*      */         return;
/*      */       
/*      */       case 1:
/* 1307 */         this.m01 = paramArrayOfDouble[0];
/* 1308 */         this.m11 = paramArrayOfDouble[1];
/* 1309 */         this.m21 = paramArrayOfDouble[2];
/* 1310 */         this.m31 = paramArrayOfDouble[3];
/*      */         return;
/*      */       
/*      */       case 2:
/* 1314 */         this.m02 = paramArrayOfDouble[0];
/* 1315 */         this.m12 = paramArrayOfDouble[1];
/* 1316 */         this.m22 = paramArrayOfDouble[2];
/* 1317 */         this.m32 = paramArrayOfDouble[3];
/*      */         return;
/*      */       
/*      */       case 3:
/* 1321 */         this.m03 = paramArrayOfDouble[0];
/* 1322 */         this.m13 = paramArrayOfDouble[1];
/* 1323 */         this.m23 = paramArrayOfDouble[2];
/* 1324 */         this.m33 = paramArrayOfDouble[3];
/*      */         return;
/*      */     } 
/*      */     
/* 1328 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d7"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(double paramDouble) {
/* 1338 */     this.m00 += paramDouble;
/* 1339 */     this.m01 += paramDouble;
/* 1340 */     this.m02 += paramDouble;
/* 1341 */     this.m03 += paramDouble;
/* 1342 */     this.m10 += paramDouble;
/* 1343 */     this.m11 += paramDouble;
/* 1344 */     this.m12 += paramDouble;
/* 1345 */     this.m13 += paramDouble;
/* 1346 */     this.m20 += paramDouble;
/* 1347 */     this.m21 += paramDouble;
/* 1348 */     this.m22 += paramDouble;
/* 1349 */     this.m23 += paramDouble;
/* 1350 */     this.m30 += paramDouble;
/* 1351 */     this.m31 += paramDouble;
/* 1352 */     this.m32 += paramDouble;
/* 1353 */     this.m33 += paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(double paramDouble, Matrix4d paramMatrix4d) {
/* 1364 */     paramMatrix4d.m00 += paramDouble;
/* 1365 */     paramMatrix4d.m01 += paramDouble;
/* 1366 */     paramMatrix4d.m02 += paramDouble;
/* 1367 */     paramMatrix4d.m03 += paramDouble;
/* 1368 */     paramMatrix4d.m10 += paramDouble;
/* 1369 */     paramMatrix4d.m11 += paramDouble;
/* 1370 */     paramMatrix4d.m12 += paramDouble;
/* 1371 */     paramMatrix4d.m13 += paramDouble;
/* 1372 */     paramMatrix4d.m20 += paramDouble;
/* 1373 */     paramMatrix4d.m21 += paramDouble;
/* 1374 */     paramMatrix4d.m22 += paramDouble;
/* 1375 */     paramMatrix4d.m23 += paramDouble;
/* 1376 */     paramMatrix4d.m30 += paramDouble;
/* 1377 */     paramMatrix4d.m31 += paramDouble;
/* 1378 */     paramMatrix4d.m32 += paramDouble;
/* 1379 */     paramMatrix4d.m33 += paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
/* 1389 */     paramMatrix4d1.m00 += paramMatrix4d2.m00;
/* 1390 */     paramMatrix4d1.m01 += paramMatrix4d2.m01;
/* 1391 */     paramMatrix4d1.m02 += paramMatrix4d2.m02;
/* 1392 */     paramMatrix4d1.m03 += paramMatrix4d2.m03;
/*      */     
/* 1394 */     paramMatrix4d1.m10 += paramMatrix4d2.m10;
/* 1395 */     paramMatrix4d1.m11 += paramMatrix4d2.m11;
/* 1396 */     paramMatrix4d1.m12 += paramMatrix4d2.m12;
/* 1397 */     paramMatrix4d1.m13 += paramMatrix4d2.m13;
/*      */     
/* 1399 */     paramMatrix4d1.m20 += paramMatrix4d2.m20;
/* 1400 */     paramMatrix4d1.m21 += paramMatrix4d2.m21;
/* 1401 */     paramMatrix4d1.m22 += paramMatrix4d2.m22;
/* 1402 */     paramMatrix4d1.m23 += paramMatrix4d2.m23;
/*      */     
/* 1404 */     paramMatrix4d1.m30 += paramMatrix4d2.m30;
/* 1405 */     paramMatrix4d1.m31 += paramMatrix4d2.m31;
/* 1406 */     paramMatrix4d1.m32 += paramMatrix4d2.m32;
/* 1407 */     paramMatrix4d1.m33 += paramMatrix4d2.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix4d paramMatrix4d) {
/* 1416 */     this.m00 += paramMatrix4d.m00;
/* 1417 */     this.m01 += paramMatrix4d.m01;
/* 1418 */     this.m02 += paramMatrix4d.m02;
/* 1419 */     this.m03 += paramMatrix4d.m03;
/*      */     
/* 1421 */     this.m10 += paramMatrix4d.m10;
/* 1422 */     this.m11 += paramMatrix4d.m11;
/* 1423 */     this.m12 += paramMatrix4d.m12;
/* 1424 */     this.m13 += paramMatrix4d.m13;
/*      */     
/* 1426 */     this.m20 += paramMatrix4d.m20;
/* 1427 */     this.m21 += paramMatrix4d.m21;
/* 1428 */     this.m22 += paramMatrix4d.m22;
/* 1429 */     this.m23 += paramMatrix4d.m23;
/*      */     
/* 1431 */     this.m30 += paramMatrix4d.m30;
/* 1432 */     this.m31 += paramMatrix4d.m31;
/* 1433 */     this.m32 += paramMatrix4d.m32;
/* 1434 */     this.m33 += paramMatrix4d.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
/* 1445 */     paramMatrix4d1.m00 -= paramMatrix4d2.m00;
/* 1446 */     paramMatrix4d1.m01 -= paramMatrix4d2.m01;
/* 1447 */     paramMatrix4d1.m02 -= paramMatrix4d2.m02;
/* 1448 */     paramMatrix4d1.m03 -= paramMatrix4d2.m03;
/*      */     
/* 1450 */     paramMatrix4d1.m10 -= paramMatrix4d2.m10;
/* 1451 */     paramMatrix4d1.m11 -= paramMatrix4d2.m11;
/* 1452 */     paramMatrix4d1.m12 -= paramMatrix4d2.m12;
/* 1453 */     paramMatrix4d1.m13 -= paramMatrix4d2.m13;
/*      */     
/* 1455 */     paramMatrix4d1.m20 -= paramMatrix4d2.m20;
/* 1456 */     paramMatrix4d1.m21 -= paramMatrix4d2.m21;
/* 1457 */     paramMatrix4d1.m22 -= paramMatrix4d2.m22;
/* 1458 */     paramMatrix4d1.m23 -= paramMatrix4d2.m23;
/*      */     
/* 1460 */     paramMatrix4d1.m30 -= paramMatrix4d2.m30;
/* 1461 */     paramMatrix4d1.m31 -= paramMatrix4d2.m31;
/* 1462 */     paramMatrix4d1.m32 -= paramMatrix4d2.m32;
/* 1463 */     paramMatrix4d1.m33 -= paramMatrix4d2.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Matrix4d paramMatrix4d) {
/* 1474 */     this.m00 -= paramMatrix4d.m00;
/* 1475 */     this.m01 -= paramMatrix4d.m01;
/* 1476 */     this.m02 -= paramMatrix4d.m02;
/* 1477 */     this.m03 -= paramMatrix4d.m03;
/*      */     
/* 1479 */     this.m10 -= paramMatrix4d.m10;
/* 1480 */     this.m11 -= paramMatrix4d.m11;
/* 1481 */     this.m12 -= paramMatrix4d.m12;
/* 1482 */     this.m13 -= paramMatrix4d.m13;
/*      */     
/* 1484 */     this.m20 -= paramMatrix4d.m20;
/* 1485 */     this.m21 -= paramMatrix4d.m21;
/* 1486 */     this.m22 -= paramMatrix4d.m22;
/* 1487 */     this.m23 -= paramMatrix4d.m23;
/*      */     
/* 1489 */     this.m30 -= paramMatrix4d.m30;
/* 1490 */     this.m31 -= paramMatrix4d.m31;
/* 1491 */     this.m32 -= paramMatrix4d.m32;
/* 1492 */     this.m33 -= paramMatrix4d.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose() {
/* 1502 */     double d = this.m10;
/* 1503 */     this.m10 = this.m01;
/* 1504 */     this.m01 = d;
/*      */     
/* 1506 */     d = this.m20;
/* 1507 */     this.m20 = this.m02;
/* 1508 */     this.m02 = d;
/*      */     
/* 1510 */     d = this.m30;
/* 1511 */     this.m30 = this.m03;
/* 1512 */     this.m03 = d;
/*      */     
/* 1514 */     d = this.m21;
/* 1515 */     this.m21 = this.m12;
/* 1516 */     this.m12 = d;
/*      */     
/* 1518 */     d = this.m31;
/* 1519 */     this.m31 = this.m13;
/* 1520 */     this.m13 = d;
/*      */     
/* 1522 */     d = this.m32;
/* 1523 */     this.m32 = this.m23;
/* 1524 */     this.m23 = d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose(Matrix4d paramMatrix4d) {
/* 1533 */     if (this != paramMatrix4d) {
/* 1534 */       this.m00 = paramMatrix4d.m00;
/* 1535 */       this.m01 = paramMatrix4d.m10;
/* 1536 */       this.m02 = paramMatrix4d.m20;
/* 1537 */       this.m03 = paramMatrix4d.m30;
/*      */       
/* 1539 */       this.m10 = paramMatrix4d.m01;
/* 1540 */       this.m11 = paramMatrix4d.m11;
/* 1541 */       this.m12 = paramMatrix4d.m21;
/* 1542 */       this.m13 = paramMatrix4d.m31;
/*      */       
/* 1544 */       this.m20 = paramMatrix4d.m02;
/* 1545 */       this.m21 = paramMatrix4d.m12;
/* 1546 */       this.m22 = paramMatrix4d.m22;
/* 1547 */       this.m23 = paramMatrix4d.m32;
/*      */       
/* 1549 */       this.m30 = paramMatrix4d.m03;
/* 1550 */       this.m31 = paramMatrix4d.m13;
/* 1551 */       this.m32 = paramMatrix4d.m23;
/* 1552 */       this.m33 = paramMatrix4d.m33;
/*      */     } else {
/* 1554 */       transpose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(double[] paramArrayOfDouble) {
/* 1565 */     this.m00 = paramArrayOfDouble[0];
/* 1566 */     this.m01 = paramArrayOfDouble[1];
/* 1567 */     this.m02 = paramArrayOfDouble[2];
/* 1568 */     this.m03 = paramArrayOfDouble[3];
/* 1569 */     this.m10 = paramArrayOfDouble[4];
/* 1570 */     this.m11 = paramArrayOfDouble[5];
/* 1571 */     this.m12 = paramArrayOfDouble[6];
/* 1572 */     this.m13 = paramArrayOfDouble[7];
/* 1573 */     this.m20 = paramArrayOfDouble[8];
/* 1574 */     this.m21 = paramArrayOfDouble[9];
/* 1575 */     this.m22 = paramArrayOfDouble[10];
/* 1576 */     this.m23 = paramArrayOfDouble[11];
/* 1577 */     this.m30 = paramArrayOfDouble[12];
/* 1578 */     this.m31 = paramArrayOfDouble[13];
/* 1579 */     this.m32 = paramArrayOfDouble[14];
/* 1580 */     this.m33 = paramArrayOfDouble[15];
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
/* 1592 */     this.m00 = paramMatrix3f.m00; this.m01 = paramMatrix3f.m01; this.m02 = paramMatrix3f.m02; this.m03 = 0.0D;
/* 1593 */     this.m10 = paramMatrix3f.m10; this.m11 = paramMatrix3f.m11; this.m12 = paramMatrix3f.m12; this.m13 = 0.0D;
/* 1594 */     this.m20 = paramMatrix3f.m20; this.m21 = paramMatrix3f.m21; this.m22 = paramMatrix3f.m22; this.m23 = 0.0D;
/* 1595 */     this.m30 = 0.0D; this.m31 = 0.0D; this.m32 = 0.0D; this.m33 = 1.0D;
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
/* 1607 */     this.m00 = paramMatrix3d.m00; this.m01 = paramMatrix3d.m01; this.m02 = paramMatrix3d.m02; this.m03 = 0.0D;
/* 1608 */     this.m10 = paramMatrix3d.m10; this.m11 = paramMatrix3d.m11; this.m12 = paramMatrix3d.m12; this.m13 = 0.0D;
/* 1609 */     this.m20 = paramMatrix3d.m20; this.m21 = paramMatrix3d.m21; this.m22 = paramMatrix3d.m22; this.m23 = 0.0D;
/* 1610 */     this.m30 = 0.0D; this.m31 = 0.0D; this.m32 = 0.0D; this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4d paramQuat4d) {
/* 1620 */     this.m00 = 1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z;
/* 1621 */     this.m10 = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
/* 1622 */     this.m20 = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
/*      */     
/* 1624 */     this.m01 = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
/* 1625 */     this.m11 = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z;
/* 1626 */     this.m21 = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
/*      */     
/* 1628 */     this.m02 = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
/* 1629 */     this.m12 = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
/* 1630 */     this.m22 = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y;
/*      */     
/* 1632 */     this.m03 = 0.0D;
/* 1633 */     this.m13 = 0.0D;
/* 1634 */     this.m23 = 0.0D;
/*      */     
/* 1636 */     this.m30 = 0.0D;
/* 1637 */     this.m31 = 0.0D;
/* 1638 */     this.m32 = 0.0D;
/* 1639 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(AxisAngle4d paramAxisAngle4d) {
/* 1649 */     double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/*      */     
/* 1651 */     if (d < 1.0E-10D) {
/* 1652 */       this.m00 = 1.0D;
/* 1653 */       this.m01 = 0.0D;
/* 1654 */       this.m02 = 0.0D;
/*      */       
/* 1656 */       this.m10 = 0.0D;
/* 1657 */       this.m11 = 1.0D;
/* 1658 */       this.m12 = 0.0D;
/*      */       
/* 1660 */       this.m20 = 0.0D;
/* 1661 */       this.m21 = 0.0D;
/* 1662 */       this.m22 = 1.0D;
/*      */     } else {
/* 1664 */       d = 1.0D / d;
/* 1665 */       double d1 = paramAxisAngle4d.x * d;
/* 1666 */       double d2 = paramAxisAngle4d.y * d;
/* 1667 */       double d3 = paramAxisAngle4d.z * d;
/*      */       
/* 1669 */       double d4 = Math.sin(paramAxisAngle4d.angle);
/* 1670 */       double d5 = Math.cos(paramAxisAngle4d.angle);
/* 1671 */       double d6 = 1.0D - d5;
/*      */       
/* 1673 */       double d7 = d1 * d3;
/* 1674 */       double d8 = d1 * d2;
/* 1675 */       double d9 = d2 * d3;
/*      */       
/* 1677 */       this.m00 = d6 * d1 * d1 + d5;
/* 1678 */       this.m01 = d6 * d8 - d4 * d3;
/* 1679 */       this.m02 = d6 * d7 + d4 * d2;
/*      */       
/* 1681 */       this.m10 = d6 * d8 + d4 * d3;
/* 1682 */       this.m11 = d6 * d2 * d2 + d5;
/* 1683 */       this.m12 = d6 * d9 - d4 * d1;
/*      */       
/* 1685 */       this.m20 = d6 * d7 - d4 * d2;
/* 1686 */       this.m21 = d6 * d9 + d4 * d1;
/* 1687 */       this.m22 = d6 * d3 * d3 + d5;
/*      */     } 
/*      */     
/* 1690 */     this.m03 = 0.0D;
/* 1691 */     this.m13 = 0.0D;
/* 1692 */     this.m23 = 0.0D;
/*      */     
/* 1694 */     this.m30 = 0.0D;
/* 1695 */     this.m31 = 0.0D;
/* 1696 */     this.m32 = 0.0D;
/* 1697 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4f paramQuat4f) {
/* 1707 */     this.m00 = 1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z;
/* 1708 */     this.m10 = 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/* 1709 */     this.m20 = 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/* 1711 */     this.m01 = 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/* 1712 */     this.m11 = 1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z;
/* 1713 */     this.m21 = 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/* 1715 */     this.m02 = 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/* 1716 */     this.m12 = 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/* 1717 */     this.m22 = 1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y;
/*      */     
/* 1719 */     this.m03 = 0.0D;
/* 1720 */     this.m13 = 0.0D;
/* 1721 */     this.m23 = 0.0D;
/*      */     
/* 1723 */     this.m30 = 0.0D;
/* 1724 */     this.m31 = 0.0D;
/* 1725 */     this.m32 = 0.0D;
/* 1726 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(AxisAngle4f paramAxisAngle4f) {
/* 1736 */     double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/*      */     
/* 1738 */     if (d < 1.0E-10D) {
/* 1739 */       this.m00 = 1.0D;
/* 1740 */       this.m01 = 0.0D;
/* 1741 */       this.m02 = 0.0D;
/*      */       
/* 1743 */       this.m10 = 0.0D;
/* 1744 */       this.m11 = 1.0D;
/* 1745 */       this.m12 = 0.0D;
/*      */       
/* 1747 */       this.m20 = 0.0D;
/* 1748 */       this.m21 = 0.0D;
/* 1749 */       this.m22 = 1.0D;
/*      */     } else {
/* 1751 */       d = 1.0D / d;
/* 1752 */       double d1 = paramAxisAngle4f.x * d;
/* 1753 */       double d2 = paramAxisAngle4f.y * d;
/* 1754 */       double d3 = paramAxisAngle4f.z * d;
/*      */       
/* 1756 */       double d4 = Math.sin(paramAxisAngle4f.angle);
/* 1757 */       double d5 = Math.cos(paramAxisAngle4f.angle);
/* 1758 */       double d6 = 1.0D - d5;
/*      */       
/* 1760 */       double d7 = d1 * d3;
/* 1761 */       double d8 = d1 * d2;
/* 1762 */       double d9 = d2 * d3;
/*      */       
/* 1764 */       this.m00 = d6 * d1 * d1 + d5;
/* 1765 */       this.m01 = d6 * d8 - d4 * d3;
/* 1766 */       this.m02 = d6 * d7 + d4 * d2;
/*      */       
/* 1768 */       this.m10 = d6 * d8 + d4 * d3;
/* 1769 */       this.m11 = d6 * d2 * d2 + d5;
/* 1770 */       this.m12 = d6 * d9 - d4 * d1;
/*      */       
/* 1772 */       this.m20 = d6 * d7 - d4 * d2;
/* 1773 */       this.m21 = d6 * d9 + d4 * d1;
/* 1774 */       this.m22 = d6 * d3 * d3 + d5;
/*      */     } 
/* 1776 */     this.m03 = 0.0D;
/* 1777 */     this.m13 = 0.0D;
/* 1778 */     this.m23 = 0.0D;
/*      */     
/* 1780 */     this.m30 = 0.0D;
/* 1781 */     this.m31 = 0.0D;
/* 1782 */     this.m32 = 0.0D;
/* 1783 */     this.m33 = 1.0D;
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
/*      */   public final void set(Quat4d paramQuat4d, Vector3d paramVector3d, double paramDouble) {
/* 1795 */     this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z);
/* 1796 */     this.m10 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
/* 1797 */     this.m20 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
/*      */     
/* 1799 */     this.m01 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
/* 1800 */     this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z);
/* 1801 */     this.m21 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
/*      */     
/* 1803 */     this.m02 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
/* 1804 */     this.m12 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
/* 1805 */     this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y);
/*      */     
/* 1807 */     this.m03 = paramVector3d.x;
/* 1808 */     this.m13 = paramVector3d.y;
/* 1809 */     this.m23 = paramVector3d.z;
/*      */     
/* 1811 */     this.m30 = 0.0D;
/* 1812 */     this.m31 = 0.0D;
/* 1813 */     this.m32 = 0.0D;
/* 1814 */     this.m33 = 1.0D;
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
/*      */   public final void set(Quat4f paramQuat4f, Vector3d paramVector3d, double paramDouble) {
/* 1826 */     this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z);
/* 1827 */     this.m10 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/* 1828 */     this.m20 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/* 1830 */     this.m01 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/* 1831 */     this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z);
/* 1832 */     this.m21 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/* 1834 */     this.m02 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/* 1835 */     this.m12 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/* 1836 */     this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y);
/*      */     
/* 1838 */     this.m03 = paramVector3d.x;
/* 1839 */     this.m13 = paramVector3d.y;
/* 1840 */     this.m23 = paramVector3d.z;
/*      */     
/* 1842 */     this.m30 = 0.0D;
/* 1843 */     this.m31 = 0.0D;
/* 1844 */     this.m32 = 0.0D;
/* 1845 */     this.m33 = 1.0D;
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
/*      */   public final void set(Quat4f paramQuat4f, Vector3f paramVector3f, float paramFloat) {
/* 1857 */     this.m00 = paramFloat * (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z);
/* 1858 */     this.m10 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/* 1859 */     this.m20 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/* 1861 */     this.m01 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/* 1862 */     this.m11 = paramFloat * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z);
/* 1863 */     this.m21 = paramFloat * 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/* 1865 */     this.m02 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/* 1866 */     this.m12 = paramFloat * 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/* 1867 */     this.m22 = paramFloat * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y);
/*      */     
/* 1869 */     this.m03 = paramVector3f.x;
/* 1870 */     this.m13 = paramVector3f.y;
/* 1871 */     this.m23 = paramVector3f.z;
/*      */     
/* 1873 */     this.m30 = 0.0D;
/* 1874 */     this.m31 = 0.0D;
/* 1875 */     this.m32 = 0.0D;
/* 1876 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix4f paramMatrix4f) {
/* 1886 */     this.m00 = paramMatrix4f.m00;
/* 1887 */     this.m01 = paramMatrix4f.m01;
/* 1888 */     this.m02 = paramMatrix4f.m02;
/* 1889 */     this.m03 = paramMatrix4f.m03;
/*      */     
/* 1891 */     this.m10 = paramMatrix4f.m10;
/* 1892 */     this.m11 = paramMatrix4f.m11;
/* 1893 */     this.m12 = paramMatrix4f.m12;
/* 1894 */     this.m13 = paramMatrix4f.m13;
/*      */     
/* 1896 */     this.m20 = paramMatrix4f.m20;
/* 1897 */     this.m21 = paramMatrix4f.m21;
/* 1898 */     this.m22 = paramMatrix4f.m22;
/* 1899 */     this.m23 = paramMatrix4f.m23;
/*      */     
/* 1901 */     this.m30 = paramMatrix4f.m30;
/* 1902 */     this.m31 = paramMatrix4f.m31;
/* 1903 */     this.m32 = paramMatrix4f.m32;
/* 1904 */     this.m33 = paramMatrix4f.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix4d paramMatrix4d) {
/* 1914 */     this.m00 = paramMatrix4d.m00;
/* 1915 */     this.m01 = paramMatrix4d.m01;
/* 1916 */     this.m02 = paramMatrix4d.m02;
/* 1917 */     this.m03 = paramMatrix4d.m03;
/*      */     
/* 1919 */     this.m10 = paramMatrix4d.m10;
/* 1920 */     this.m11 = paramMatrix4d.m11;
/* 1921 */     this.m12 = paramMatrix4d.m12;
/* 1922 */     this.m13 = paramMatrix4d.m13;
/*      */     
/* 1924 */     this.m20 = paramMatrix4d.m20;
/* 1925 */     this.m21 = paramMatrix4d.m21;
/* 1926 */     this.m22 = paramMatrix4d.m22;
/* 1927 */     this.m23 = paramMatrix4d.m23;
/*      */     
/* 1929 */     this.m30 = paramMatrix4d.m30;
/* 1930 */     this.m31 = paramMatrix4d.m31;
/* 1931 */     this.m32 = paramMatrix4d.m32;
/* 1932 */     this.m33 = paramMatrix4d.m33;
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
/* 1943 */   public final void invert(Matrix4d paramMatrix4d) { invertGeneral(paramMatrix4d); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1951 */   public final void invert() { invertGeneral(this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void invertGeneral(Matrix4d paramMatrix4d) {
/* 1963 */     double[] arrayOfDouble1 = new double[16];
/* 1964 */     int[] arrayOfInt = new int[4];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1969 */     double[] arrayOfDouble2 = new double[16];
/*      */     
/* 1971 */     arrayOfDouble2[0] = paramMatrix4d.m00;
/* 1972 */     arrayOfDouble2[1] = paramMatrix4d.m01;
/* 1973 */     arrayOfDouble2[2] = paramMatrix4d.m02;
/* 1974 */     arrayOfDouble2[3] = paramMatrix4d.m03;
/*      */     
/* 1976 */     arrayOfDouble2[4] = paramMatrix4d.m10;
/* 1977 */     arrayOfDouble2[5] = paramMatrix4d.m11;
/* 1978 */     arrayOfDouble2[6] = paramMatrix4d.m12;
/* 1979 */     arrayOfDouble2[7] = paramMatrix4d.m13;
/*      */     
/* 1981 */     arrayOfDouble2[8] = paramMatrix4d.m20;
/* 1982 */     arrayOfDouble2[9] = paramMatrix4d.m21;
/* 1983 */     arrayOfDouble2[10] = paramMatrix4d.m22;
/* 1984 */     arrayOfDouble2[11] = paramMatrix4d.m23;
/*      */     
/* 1986 */     arrayOfDouble2[12] = paramMatrix4d.m30;
/* 1987 */     arrayOfDouble2[13] = paramMatrix4d.m31;
/* 1988 */     arrayOfDouble2[14] = paramMatrix4d.m32;
/* 1989 */     arrayOfDouble2[15] = paramMatrix4d.m33;
/*      */ 
/*      */     
/* 1992 */     if (!luDecomposition(arrayOfDouble2, arrayOfInt))
/*      */     {
/* 1994 */       throw new SingularMatrixException(VecMathI18N.getString("Matrix4d10"));
/*      */     }
/*      */ 
/*      */     
/* 1998 */     for (byte b = 0; b < 16; ) { arrayOfDouble1[b] = 0.0D; b++; }
/* 1999 */      arrayOfDouble1[0] = 1.0D; arrayOfDouble1[5] = 1.0D; arrayOfDouble1[10] = 1.0D; arrayOfDouble1[15] = 1.0D;
/* 2000 */     luBacksubstitution(arrayOfDouble2, arrayOfInt, arrayOfDouble1);
/*      */     
/* 2002 */     this.m00 = arrayOfDouble1[0];
/* 2003 */     this.m01 = arrayOfDouble1[1];
/* 2004 */     this.m02 = arrayOfDouble1[2];
/* 2005 */     this.m03 = arrayOfDouble1[3];
/*      */     
/* 2007 */     this.m10 = arrayOfDouble1[4];
/* 2008 */     this.m11 = arrayOfDouble1[5];
/* 2009 */     this.m12 = arrayOfDouble1[6];
/* 2010 */     this.m13 = arrayOfDouble1[7];
/*      */     
/* 2012 */     this.m20 = arrayOfDouble1[8];
/* 2013 */     this.m21 = arrayOfDouble1[9];
/* 2014 */     this.m22 = arrayOfDouble1[10];
/* 2015 */     this.m23 = arrayOfDouble1[11];
/*      */     
/* 2017 */     this.m30 = arrayOfDouble1[12];
/* 2018 */     this.m31 = arrayOfDouble1[13];
/* 2019 */     this.m32 = arrayOfDouble1[14];
/* 2020 */     this.m33 = arrayOfDouble1[15];
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
/* 2047 */     double[] arrayOfDouble = new double[4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2055 */     byte b3 = 0;
/* 2056 */     byte b4 = 0;
/*      */ 
/*      */     
/* 2059 */     byte b1 = 4;
/* 2060 */     while (b1-- != 0) {
/* 2061 */       double d = 0.0D;
/*      */ 
/*      */       
/* 2064 */       byte b = 4;
/* 2065 */       while (b-- != 0) {
/* 2066 */         double d1 = paramArrayOfDouble[b3++];
/* 2067 */         d1 = Math.abs(d1);
/* 2068 */         if (d1 > d) {
/* 2069 */           d = d1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2074 */       if (d == 0.0D) {
/* 2075 */         return false;
/*      */       }
/* 2077 */       arrayOfDouble[b4++] = 1.0D / d;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2085 */     byte b2 = 0;
/*      */ 
/*      */     
/* 2088 */     for (b1 = 0; b1 < 4; b1++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2094 */       for (b3 = 0; b3 < b1; b3++) {
/* 2095 */         byte b6 = b2 + 4 * b3 + b1;
/* 2096 */         double d1 = paramArrayOfDouble[b6];
/* 2097 */         byte b5 = b3;
/* 2098 */         byte b7 = b2 + 4 * b3;
/* 2099 */         byte b8 = b2 + b1;
/* 2100 */         while (b5-- != 0) {
/* 2101 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 2102 */           b7++;
/* 2103 */           b8 += 4;
/*      */         } 
/* 2105 */         paramArrayOfDouble[b6] = d1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2110 */       double d = 0.0D;
/* 2111 */       b4 = -1;
/* 2112 */       for (b3 = b1; b3 < 4; b3++) {
/* 2113 */         byte b6 = b2 + 4 * b3 + b1;
/* 2114 */         double d1 = paramArrayOfDouble[b6];
/* 2115 */         byte b5 = b1;
/* 2116 */         byte b7 = b2 + 4 * b3;
/* 2117 */         byte b8 = b2 + b1;
/* 2118 */         while (b5-- != 0) {
/* 2119 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 2120 */           b7++;
/* 2121 */           b8 += 4;
/*      */         } 
/* 2123 */         paramArrayOfDouble[b6] = d1;
/*      */         
/*      */         double d2;
/* 2126 */         if ((d2 = arrayOfDouble[b3] * Math.abs(d1)) >= d) {
/* 2127 */           d = d2;
/* 2128 */           b4 = b3;
/*      */         } 
/*      */       } 
/*      */       
/* 2132 */       if (b4 < 0) {
/* 2133 */         throw new RuntimeException(VecMathI18N.getString("Matrix4d11"));
/*      */       }
/*      */ 
/*      */       
/* 2137 */       if (b1 != b4) {
/*      */         
/* 2139 */         byte b = 4;
/* 2140 */         byte b5 = b2 + 4 * b4;
/* 2141 */         byte b6 = b2 + 4 * b1;
/* 2142 */         while (b-- != 0) {
/* 2143 */           double d1 = paramArrayOfDouble[b5];
/* 2144 */           paramArrayOfDouble[b5++] = paramArrayOfDouble[b6];
/* 2145 */           paramArrayOfDouble[b6++] = d1;
/*      */         } 
/*      */ 
/*      */         
/* 2149 */         arrayOfDouble[b4] = arrayOfDouble[b1];
/*      */       } 
/*      */ 
/*      */       
/* 2153 */       paramArrayOfInt[b1] = b4;
/*      */ 
/*      */       
/* 2156 */       if (paramArrayOfDouble[b2 + 4 * b1 + b1] == 0.0D) {
/* 2157 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2161 */       if (b1 != 3) {
/* 2162 */         double d1 = 1.0D / paramArrayOfDouble[b2 + 4 * b1 + b1];
/* 2163 */         byte b = b2 + 4 * (b1 + 1) + b1;
/* 2164 */         b3 = 3 - b1;
/* 2165 */         while (b3-- != 0) {
/* 2166 */           paramArrayOfDouble[b] = paramArrayOfDouble[b] * d1;
/* 2167 */           b += 4;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2173 */     return true;
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
/* 2203 */     byte b2 = 0;
/*      */ 
/*      */     
/* 2206 */     for (byte b1 = 0; b1 < 4; b1++) {
/*      */       
/* 2208 */       int i = b1;
/* 2209 */       byte b4 = -1;
/*      */ 
/*      */       
/* 2212 */       for (byte b3 = 0; b3 < 4; b3++) {
/*      */ 
/*      */         
/* 2215 */         int j = paramArrayOfInt[b2 + b3];
/* 2216 */         double d = paramArrayOfDouble2[i + 4 * j];
/* 2217 */         paramArrayOfDouble2[i + 4 * j] = paramArrayOfDouble2[i + 4 * b3];
/* 2218 */         if (b4 >= 0) {
/*      */           
/* 2220 */           byte b6 = b3 * 4;
/* 2221 */           for (byte b5 = b4; b5 <= b3 - 1; b5++) {
/* 2222 */             d -= paramArrayOfDouble1[b6 + b5] * paramArrayOfDouble2[i + 4 * b5];
/*      */           }
/*      */         }
/* 2225 */         else if (d != 0.0D) {
/* 2226 */           b4 = b3;
/*      */         } 
/* 2228 */         paramArrayOfDouble2[i + 4 * b3] = d;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2233 */       byte b = 12;
/* 2234 */       paramArrayOfDouble2[i + 12] = paramArrayOfDouble2[i + 12] / paramArrayOfDouble1[b + 3];
/*      */       
/* 2236 */       b -= 4;
/* 2237 */       paramArrayOfDouble2[i + 8] = (paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 2];
/*      */ 
/*      */       
/* 2240 */       b -= 4;
/* 2241 */       paramArrayOfDouble2[i + 4] = (paramArrayOfDouble2[i + 4] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 1];
/*      */ 
/*      */ 
/*      */       
/* 2245 */       b -= 4;
/* 2246 */       paramArrayOfDouble2[i + 0] = (paramArrayOfDouble2[i + 0] - paramArrayOfDouble1[b + 1] * paramArrayOfDouble2[i + 4] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 0];
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
/*      */   public final double determinant() {
/* 2263 */     null = this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
/*      */     
/* 2265 */     null -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
/*      */     
/* 2267 */     null += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
/*      */     
/* 2269 */     return this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
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
/*      */   public final void set(double paramDouble) {
/* 2282 */     this.m00 = paramDouble;
/* 2283 */     this.m01 = 0.0D;
/* 2284 */     this.m02 = 0.0D;
/* 2285 */     this.m03 = 0.0D;
/*      */     
/* 2287 */     this.m10 = 0.0D;
/* 2288 */     this.m11 = paramDouble;
/* 2289 */     this.m12 = 0.0D;
/* 2290 */     this.m13 = 0.0D;
/*      */     
/* 2292 */     this.m20 = 0.0D;
/* 2293 */     this.m21 = 0.0D;
/* 2294 */     this.m22 = paramDouble;
/* 2295 */     this.m23 = 0.0D;
/*      */     
/* 2297 */     this.m30 = 0.0D;
/* 2298 */     this.m31 = 0.0D;
/* 2299 */     this.m32 = 0.0D;
/* 2300 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Vector3d paramVector3d) {
/* 2310 */     this.m00 = 1.0D;
/* 2311 */     this.m01 = 0.0D;
/* 2312 */     this.m02 = 0.0D;
/* 2313 */     this.m03 = paramVector3d.x;
/*      */     
/* 2315 */     this.m10 = 0.0D;
/* 2316 */     this.m11 = 1.0D;
/* 2317 */     this.m12 = 0.0D;
/* 2318 */     this.m13 = paramVector3d.y;
/*      */     
/* 2320 */     this.m20 = 0.0D;
/* 2321 */     this.m21 = 0.0D;
/* 2322 */     this.m22 = 1.0D;
/* 2323 */     this.m23 = paramVector3d.z;
/*      */     
/* 2325 */     this.m30 = 0.0D;
/* 2326 */     this.m31 = 0.0D;
/* 2327 */     this.m32 = 0.0D;
/* 2328 */     this.m33 = 1.0D;
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
/*      */   public final void set(double paramDouble, Vector3d paramVector3d) {
/* 2340 */     this.m00 = paramDouble;
/* 2341 */     this.m01 = 0.0D;
/* 2342 */     this.m02 = 0.0D;
/* 2343 */     this.m03 = paramVector3d.x;
/*      */     
/* 2345 */     this.m10 = 0.0D;
/* 2346 */     this.m11 = paramDouble;
/* 2347 */     this.m12 = 0.0D;
/* 2348 */     this.m13 = paramVector3d.y;
/*      */     
/* 2350 */     this.m20 = 0.0D;
/* 2351 */     this.m21 = 0.0D;
/* 2352 */     this.m22 = paramDouble;
/* 2353 */     this.m23 = paramVector3d.z;
/*      */     
/* 2355 */     this.m30 = 0.0D;
/* 2356 */     this.m31 = 0.0D;
/* 2357 */     this.m32 = 0.0D;
/* 2358 */     this.m33 = 1.0D;
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
/*      */   public final void set(Vector3d paramVector3d, double paramDouble) {
/* 2370 */     this.m00 = paramDouble;
/* 2371 */     this.m01 = 0.0D;
/* 2372 */     this.m02 = 0.0D;
/* 2373 */     this.m03 = paramDouble * paramVector3d.x;
/*      */     
/* 2375 */     this.m10 = 0.0D;
/* 2376 */     this.m11 = paramDouble;
/* 2377 */     this.m12 = 0.0D;
/* 2378 */     this.m13 = paramDouble * paramVector3d.y;
/*      */     
/* 2380 */     this.m20 = 0.0D;
/* 2381 */     this.m21 = 0.0D;
/* 2382 */     this.m22 = paramDouble;
/* 2383 */     this.m23 = paramDouble * paramVector3d.z;
/*      */     
/* 2385 */     this.m30 = 0.0D;
/* 2386 */     this.m31 = 0.0D;
/* 2387 */     this.m32 = 0.0D;
/* 2388 */     this.m33 = 1.0D;
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
/*      */   public final void set(Matrix3f paramMatrix3f, Vector3f paramVector3f, float paramFloat) {
/* 2401 */     this.m00 = (paramMatrix3f.m00 * paramFloat);
/* 2402 */     this.m01 = (paramMatrix3f.m01 * paramFloat);
/* 2403 */     this.m02 = (paramMatrix3f.m02 * paramFloat);
/* 2404 */     this.m03 = paramVector3f.x;
/*      */     
/* 2406 */     this.m10 = (paramMatrix3f.m10 * paramFloat);
/* 2407 */     this.m11 = (paramMatrix3f.m11 * paramFloat);
/* 2408 */     this.m12 = (paramMatrix3f.m12 * paramFloat);
/* 2409 */     this.m13 = paramVector3f.y;
/*      */     
/* 2411 */     this.m20 = (paramMatrix3f.m20 * paramFloat);
/* 2412 */     this.m21 = (paramMatrix3f.m21 * paramFloat);
/* 2413 */     this.m22 = (paramMatrix3f.m22 * paramFloat);
/* 2414 */     this.m23 = paramVector3f.z;
/*      */     
/* 2416 */     this.m30 = 0.0D;
/* 2417 */     this.m31 = 0.0D;
/* 2418 */     this.m32 = 0.0D;
/* 2419 */     this.m33 = 1.0D;
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
/*      */   public final void set(Matrix3d paramMatrix3d, Vector3d paramVector3d, double paramDouble) {
/* 2433 */     this.m00 = paramMatrix3d.m00 * paramDouble;
/* 2434 */     this.m01 = paramMatrix3d.m01 * paramDouble;
/* 2435 */     this.m02 = paramMatrix3d.m02 * paramDouble;
/* 2436 */     this.m03 = paramVector3d.x;
/*      */     
/* 2438 */     this.m10 = paramMatrix3d.m10 * paramDouble;
/* 2439 */     this.m11 = paramMatrix3d.m11 * paramDouble;
/* 2440 */     this.m12 = paramMatrix3d.m12 * paramDouble;
/* 2441 */     this.m13 = paramVector3d.y;
/*      */     
/* 2443 */     this.m20 = paramMatrix3d.m20 * paramDouble;
/* 2444 */     this.m21 = paramMatrix3d.m21 * paramDouble;
/* 2445 */     this.m22 = paramMatrix3d.m22 * paramDouble;
/* 2446 */     this.m23 = paramVector3d.z;
/*      */     
/* 2448 */     this.m30 = 0.0D;
/* 2449 */     this.m31 = 0.0D;
/* 2450 */     this.m32 = 0.0D;
/* 2451 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setTranslation(Vector3d paramVector3d) {
/* 2462 */     this.m03 = paramVector3d.x;
/* 2463 */     this.m13 = paramVector3d.y;
/* 2464 */     this.m23 = paramVector3d.z;
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
/* 2476 */     double d1 = Math.sin(paramDouble);
/* 2477 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 2479 */     this.m00 = 1.0D;
/* 2480 */     this.m01 = 0.0D;
/* 2481 */     this.m02 = 0.0D;
/* 2482 */     this.m03 = 0.0D;
/*      */     
/* 2484 */     this.m10 = 0.0D;
/* 2485 */     this.m11 = d2;
/* 2486 */     this.m12 = -d1;
/* 2487 */     this.m13 = 0.0D;
/*      */     
/* 2489 */     this.m20 = 0.0D;
/* 2490 */     this.m21 = d1;
/* 2491 */     this.m22 = d2;
/* 2492 */     this.m23 = 0.0D;
/*      */     
/* 2494 */     this.m30 = 0.0D;
/* 2495 */     this.m31 = 0.0D;
/* 2496 */     this.m32 = 0.0D;
/* 2497 */     this.m33 = 1.0D;
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
/* 2509 */     double d1 = Math.sin(paramDouble);
/* 2510 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 2512 */     this.m00 = d2;
/* 2513 */     this.m01 = 0.0D;
/* 2514 */     this.m02 = d1;
/* 2515 */     this.m03 = 0.0D;
/*      */     
/* 2517 */     this.m10 = 0.0D;
/* 2518 */     this.m11 = 1.0D;
/* 2519 */     this.m12 = 0.0D;
/* 2520 */     this.m13 = 0.0D;
/*      */     
/* 2522 */     this.m20 = -d1;
/* 2523 */     this.m21 = 0.0D;
/* 2524 */     this.m22 = d2;
/* 2525 */     this.m23 = 0.0D;
/*      */     
/* 2527 */     this.m30 = 0.0D;
/* 2528 */     this.m31 = 0.0D;
/* 2529 */     this.m32 = 0.0D;
/* 2530 */     this.m33 = 1.0D;
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
/* 2542 */     double d1 = Math.sin(paramDouble);
/* 2543 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 2545 */     this.m00 = d2;
/* 2546 */     this.m01 = -d1;
/* 2547 */     this.m02 = 0.0D;
/* 2548 */     this.m03 = 0.0D;
/*      */     
/* 2550 */     this.m10 = d1;
/* 2551 */     this.m11 = d2;
/* 2552 */     this.m12 = 0.0D;
/* 2553 */     this.m13 = 0.0D;
/*      */     
/* 2555 */     this.m20 = 0.0D;
/* 2556 */     this.m21 = 0.0D;
/* 2557 */     this.m22 = 1.0D;
/* 2558 */     this.m23 = 0.0D;
/*      */     
/* 2560 */     this.m30 = 0.0D;
/* 2561 */     this.m31 = 0.0D;
/* 2562 */     this.m32 = 0.0D;
/* 2563 */     this.m33 = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(double paramDouble) {
/* 2572 */     this.m00 *= paramDouble;
/* 2573 */     this.m01 *= paramDouble;
/* 2574 */     this.m02 *= paramDouble;
/* 2575 */     this.m03 *= paramDouble;
/* 2576 */     this.m10 *= paramDouble;
/* 2577 */     this.m11 *= paramDouble;
/* 2578 */     this.m12 *= paramDouble;
/* 2579 */     this.m13 *= paramDouble;
/* 2580 */     this.m20 *= paramDouble;
/* 2581 */     this.m21 *= paramDouble;
/* 2582 */     this.m22 *= paramDouble;
/* 2583 */     this.m23 *= paramDouble;
/* 2584 */     this.m30 *= paramDouble;
/* 2585 */     this.m31 *= paramDouble;
/* 2586 */     this.m32 *= paramDouble;
/* 2587 */     this.m33 *= paramDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(double paramDouble, Matrix4d paramMatrix4d) {
/* 2598 */     paramMatrix4d.m00 *= paramDouble;
/* 2599 */     paramMatrix4d.m01 *= paramDouble;
/* 2600 */     paramMatrix4d.m02 *= paramDouble;
/* 2601 */     paramMatrix4d.m03 *= paramDouble;
/* 2602 */     paramMatrix4d.m10 *= paramDouble;
/* 2603 */     paramMatrix4d.m11 *= paramDouble;
/* 2604 */     paramMatrix4d.m12 *= paramDouble;
/* 2605 */     paramMatrix4d.m13 *= paramDouble;
/* 2606 */     paramMatrix4d.m20 *= paramDouble;
/* 2607 */     paramMatrix4d.m21 *= paramDouble;
/* 2608 */     paramMatrix4d.m22 *= paramDouble;
/* 2609 */     paramMatrix4d.m23 *= paramDouble;
/* 2610 */     paramMatrix4d.m30 *= paramDouble;
/* 2611 */     paramMatrix4d.m31 *= paramDouble;
/* 2612 */     paramMatrix4d.m32 *= paramDouble;
/* 2613 */     paramMatrix4d.m33 *= paramDouble;
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
/*      */   public final void mul(Matrix4d paramMatrix4d) {
/* 2628 */     double d1 = this.m00 * paramMatrix4d.m00 + this.m01 * paramMatrix4d.m10 + this.m02 * paramMatrix4d.m20 + this.m03 * paramMatrix4d.m30;
/*      */     
/* 2630 */     double d2 = this.m00 * paramMatrix4d.m01 + this.m01 * paramMatrix4d.m11 + this.m02 * paramMatrix4d.m21 + this.m03 * paramMatrix4d.m31;
/*      */     
/* 2632 */     double d3 = this.m00 * paramMatrix4d.m02 + this.m01 * paramMatrix4d.m12 + this.m02 * paramMatrix4d.m22 + this.m03 * paramMatrix4d.m32;
/*      */     
/* 2634 */     double d4 = this.m00 * paramMatrix4d.m03 + this.m01 * paramMatrix4d.m13 + this.m02 * paramMatrix4d.m23 + this.m03 * paramMatrix4d.m33;
/*      */ 
/*      */     
/* 2637 */     double d5 = this.m10 * paramMatrix4d.m00 + this.m11 * paramMatrix4d.m10 + this.m12 * paramMatrix4d.m20 + this.m13 * paramMatrix4d.m30;
/*      */     
/* 2639 */     double d6 = this.m10 * paramMatrix4d.m01 + this.m11 * paramMatrix4d.m11 + this.m12 * paramMatrix4d.m21 + this.m13 * paramMatrix4d.m31;
/*      */     
/* 2641 */     double d7 = this.m10 * paramMatrix4d.m02 + this.m11 * paramMatrix4d.m12 + this.m12 * paramMatrix4d.m22 + this.m13 * paramMatrix4d.m32;
/*      */     
/* 2643 */     double d8 = this.m10 * paramMatrix4d.m03 + this.m11 * paramMatrix4d.m13 + this.m12 * paramMatrix4d.m23 + this.m13 * paramMatrix4d.m33;
/*      */ 
/*      */     
/* 2646 */     double d9 = this.m20 * paramMatrix4d.m00 + this.m21 * paramMatrix4d.m10 + this.m22 * paramMatrix4d.m20 + this.m23 * paramMatrix4d.m30;
/*      */     
/* 2648 */     double d10 = this.m20 * paramMatrix4d.m01 + this.m21 * paramMatrix4d.m11 + this.m22 * paramMatrix4d.m21 + this.m23 * paramMatrix4d.m31;
/*      */     
/* 2650 */     double d11 = this.m20 * paramMatrix4d.m02 + this.m21 * paramMatrix4d.m12 + this.m22 * paramMatrix4d.m22 + this.m23 * paramMatrix4d.m32;
/*      */     
/* 2652 */     double d12 = this.m20 * paramMatrix4d.m03 + this.m21 * paramMatrix4d.m13 + this.m22 * paramMatrix4d.m23 + this.m23 * paramMatrix4d.m33;
/*      */ 
/*      */     
/* 2655 */     double d13 = this.m30 * paramMatrix4d.m00 + this.m31 * paramMatrix4d.m10 + this.m32 * paramMatrix4d.m20 + this.m33 * paramMatrix4d.m30;
/*      */     
/* 2657 */     double d14 = this.m30 * paramMatrix4d.m01 + this.m31 * paramMatrix4d.m11 + this.m32 * paramMatrix4d.m21 + this.m33 * paramMatrix4d.m31;
/*      */     
/* 2659 */     double d15 = this.m30 * paramMatrix4d.m02 + this.m31 * paramMatrix4d.m12 + this.m32 * paramMatrix4d.m22 + this.m33 * paramMatrix4d.m32;
/*      */     
/* 2661 */     double d16 = this.m30 * paramMatrix4d.m03 + this.m31 * paramMatrix4d.m13 + this.m32 * paramMatrix4d.m23 + this.m33 * paramMatrix4d.m33;
/*      */ 
/*      */     
/* 2664 */     this.m00 = d1; this.m01 = d2; this.m02 = d3; this.m03 = d4;
/* 2665 */     this.m10 = d5; this.m11 = d6; this.m12 = d7; this.m13 = d8;
/* 2666 */     this.m20 = d9; this.m21 = d10; this.m22 = d11; this.m23 = d12;
/* 2667 */     this.m30 = d13; this.m31 = d14; this.m32 = d15; this.m33 = d16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
/* 2678 */     if (this != paramMatrix4d1 && this != paramMatrix4d2) {
/*      */       
/* 2680 */       this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m03 * paramMatrix4d2.m30;
/*      */       
/* 2682 */       this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m21 + paramMatrix4d1.m03 * paramMatrix4d2.m31;
/*      */       
/* 2684 */       this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m01 * paramMatrix4d2.m12 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m32;
/*      */       
/* 2686 */       this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m01 * paramMatrix4d2.m13 + paramMatrix4d1.m02 * paramMatrix4d2.m23 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
/*      */ 
/*      */       
/* 2689 */       this.m10 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m30;
/*      */       
/* 2691 */       this.m11 = paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m13 * paramMatrix4d2.m31;
/*      */       
/* 2693 */       this.m12 = paramMatrix4d1.m10 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m32;
/*      */       
/* 2695 */       this.m13 = paramMatrix4d1.m10 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m12 * paramMatrix4d2.m23 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
/*      */ 
/*      */       
/* 2698 */       this.m20 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m23 * paramMatrix4d2.m30;
/*      */       
/* 2700 */       this.m21 = paramMatrix4d1.m20 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m31;
/*      */       
/* 2702 */       this.m22 = paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m32;
/*      */       
/* 2704 */       this.m23 = paramMatrix4d1.m20 * paramMatrix4d2.m03 + paramMatrix4d1.m21 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
/*      */ 
/*      */       
/* 2707 */       this.m30 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m10 + paramMatrix4d1.m32 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
/*      */       
/* 2709 */       this.m31 = paramMatrix4d1.m30 * paramMatrix4d2.m01 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
/*      */       
/* 2711 */       this.m32 = paramMatrix4d1.m30 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
/*      */       
/* 2713 */       this.m33 = paramMatrix4d1.m30 * paramMatrix4d2.m03 + paramMatrix4d1.m31 * paramMatrix4d2.m13 + paramMatrix4d1.m32 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 2722 */       double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m03 * paramMatrix4d2.m30;
/* 2723 */       double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m21 + paramMatrix4d1.m03 * paramMatrix4d2.m31;
/* 2724 */       double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m01 * paramMatrix4d2.m12 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m32;
/* 2725 */       double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m01 * paramMatrix4d2.m13 + paramMatrix4d1.m02 * paramMatrix4d2.m23 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
/*      */       
/* 2727 */       double d5 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m30;
/* 2728 */       double d6 = paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m13 * paramMatrix4d2.m31;
/* 2729 */       double d7 = paramMatrix4d1.m10 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m32;
/* 2730 */       double d8 = paramMatrix4d1.m10 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m12 * paramMatrix4d2.m23 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
/*      */       
/* 2732 */       double d9 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m23 * paramMatrix4d2.m30;
/* 2733 */       double d10 = paramMatrix4d1.m20 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m31;
/* 2734 */       double d11 = paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m32;
/* 2735 */       double d12 = paramMatrix4d1.m20 * paramMatrix4d2.m03 + paramMatrix4d1.m21 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
/*      */       
/* 2737 */       double d13 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m10 + paramMatrix4d1.m32 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
/* 2738 */       double d14 = paramMatrix4d1.m30 * paramMatrix4d2.m01 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
/* 2739 */       double d15 = paramMatrix4d1.m30 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
/* 2740 */       double d16 = paramMatrix4d1.m30 * paramMatrix4d2.m03 + paramMatrix4d1.m31 * paramMatrix4d2.m13 + paramMatrix4d1.m32 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */       
/* 2742 */       this.m00 = d1; this.m01 = d2; this.m02 = d3; this.m03 = d4;
/* 2743 */       this.m10 = d5; this.m11 = d6; this.m12 = d7; this.m13 = d8;
/* 2744 */       this.m20 = d9; this.m21 = d10; this.m22 = d11; this.m23 = d12;
/* 2745 */       this.m30 = d13; this.m31 = d14; this.m32 = d15; this.m33 = d16;
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
/*      */   public final void mulTransposeBoth(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
/* 2758 */     if (this != paramMatrix4d1 && this != paramMatrix4d2) {
/* 2759 */       this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m30 * paramMatrix4d2.m03;
/* 2760 */       this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m12 + paramMatrix4d1.m30 * paramMatrix4d2.m13;
/* 2761 */       this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m10 * paramMatrix4d2.m21 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m23;
/* 2762 */       this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m10 * paramMatrix4d2.m31 + paramMatrix4d1.m20 * paramMatrix4d2.m32 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
/*      */       
/* 2764 */       this.m10 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m03;
/* 2765 */       this.m11 = paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m31 * paramMatrix4d2.m13;
/* 2766 */       this.m12 = paramMatrix4d1.m01 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m23;
/* 2767 */       this.m13 = paramMatrix4d1.m01 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m21 * paramMatrix4d2.m32 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
/*      */       
/* 2769 */       this.m20 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m32 * paramMatrix4d2.m03;
/* 2770 */       this.m21 = paramMatrix4d1.m02 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m13;
/* 2771 */       this.m22 = paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m23;
/* 2772 */       this.m23 = paramMatrix4d1.m02 * paramMatrix4d2.m30 + paramMatrix4d1.m12 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
/*      */       
/* 2774 */       this.m30 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m01 + paramMatrix4d1.m23 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
/* 2775 */       this.m31 = paramMatrix4d1.m03 * paramMatrix4d2.m10 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
/* 2776 */       this.m32 = paramMatrix4d1.m03 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
/* 2777 */       this.m33 = paramMatrix4d1.m03 * paramMatrix4d2.m30 + paramMatrix4d1.m13 * paramMatrix4d2.m31 + paramMatrix4d1.m23 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2784 */       double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m30 * paramMatrix4d2.m03;
/* 2785 */       double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m12 + paramMatrix4d1.m30 * paramMatrix4d2.m13;
/* 2786 */       double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m10 * paramMatrix4d2.m21 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m23;
/* 2787 */       double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m10 * paramMatrix4d2.m31 + paramMatrix4d1.m20 * paramMatrix4d2.m32 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
/*      */       
/* 2789 */       double d5 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m03;
/* 2790 */       double d6 = paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m31 * paramMatrix4d2.m13;
/* 2791 */       double d7 = paramMatrix4d1.m01 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m23;
/* 2792 */       double d8 = paramMatrix4d1.m01 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m21 * paramMatrix4d2.m32 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
/*      */       
/* 2794 */       double d9 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m32 * paramMatrix4d2.m03;
/* 2795 */       double d10 = paramMatrix4d1.m02 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m13;
/* 2796 */       double d11 = paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m23;
/* 2797 */       double d12 = paramMatrix4d1.m02 * paramMatrix4d2.m30 + paramMatrix4d1.m12 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
/*      */       
/* 2799 */       double d13 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m01 + paramMatrix4d1.m23 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
/* 2800 */       double d14 = paramMatrix4d1.m03 * paramMatrix4d2.m10 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
/* 2801 */       double d15 = paramMatrix4d1.m03 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
/* 2802 */       double d16 = paramMatrix4d1.m03 * paramMatrix4d2.m30 + paramMatrix4d1.m13 * paramMatrix4d2.m31 + paramMatrix4d1.m23 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */       
/* 2804 */       this.m00 = d1; this.m01 = d2; this.m02 = d3; this.m03 = d4;
/* 2805 */       this.m10 = d5; this.m11 = d6; this.m12 = d7; this.m13 = d8;
/* 2806 */       this.m20 = d9; this.m21 = d10; this.m22 = d11; this.m23 = d12;
/* 2807 */       this.m30 = d13; this.m31 = d14; this.m32 = d15; this.m33 = d16;
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
/*      */   public final void mulTransposeRight(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
/* 2822 */     if (this != paramMatrix4d1 && this != paramMatrix4d2) {
/* 2823 */       this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m03 * paramMatrix4d2.m03;
/* 2824 */       this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m12 + paramMatrix4d1.m03 * paramMatrix4d2.m13;
/* 2825 */       this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m01 * paramMatrix4d2.m21 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m23;
/* 2826 */       this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m01 * paramMatrix4d2.m31 + paramMatrix4d1.m02 * paramMatrix4d2.m32 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
/*      */       
/* 2828 */       this.m10 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m03;
/* 2829 */       this.m11 = paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m13 * paramMatrix4d2.m13;
/* 2830 */       this.m12 = paramMatrix4d1.m10 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m23;
/* 2831 */       this.m13 = paramMatrix4d1.m10 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m12 * paramMatrix4d2.m32 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
/*      */       
/* 2833 */       this.m20 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m23 * paramMatrix4d2.m03;
/* 2834 */       this.m21 = paramMatrix4d1.m20 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m13;
/* 2835 */       this.m22 = paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m23;
/* 2836 */       this.m23 = paramMatrix4d1.m20 * paramMatrix4d2.m30 + paramMatrix4d1.m21 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
/*      */       
/* 2838 */       this.m30 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m01 + paramMatrix4d1.m32 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
/* 2839 */       this.m31 = paramMatrix4d1.m30 * paramMatrix4d2.m10 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
/* 2840 */       this.m32 = paramMatrix4d1.m30 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
/* 2841 */       this.m33 = paramMatrix4d1.m30 * paramMatrix4d2.m30 + paramMatrix4d1.m31 * paramMatrix4d2.m31 + paramMatrix4d1.m32 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2848 */       double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m03 * paramMatrix4d2.m03;
/* 2849 */       double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m12 + paramMatrix4d1.m03 * paramMatrix4d2.m13;
/* 2850 */       double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m01 * paramMatrix4d2.m21 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m23;
/* 2851 */       double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m01 * paramMatrix4d2.m31 + paramMatrix4d1.m02 * paramMatrix4d2.m32 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
/*      */       
/* 2853 */       double d5 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m03;
/* 2854 */       double d6 = paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m13 * paramMatrix4d2.m13;
/* 2855 */       double d7 = paramMatrix4d1.m10 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m23;
/* 2856 */       double d8 = paramMatrix4d1.m10 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m12 * paramMatrix4d2.m32 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
/*      */       
/* 2858 */       double d9 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m23 * paramMatrix4d2.m03;
/* 2859 */       double d10 = paramMatrix4d1.m20 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m13;
/* 2860 */       double d11 = paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m23;
/* 2861 */       double d12 = paramMatrix4d1.m20 * paramMatrix4d2.m30 + paramMatrix4d1.m21 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
/*      */       
/* 2863 */       double d13 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m01 + paramMatrix4d1.m32 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
/* 2864 */       double d14 = paramMatrix4d1.m30 * paramMatrix4d2.m10 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
/* 2865 */       double d15 = paramMatrix4d1.m30 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
/* 2866 */       double d16 = paramMatrix4d1.m30 * paramMatrix4d2.m30 + paramMatrix4d1.m31 * paramMatrix4d2.m31 + paramMatrix4d1.m32 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */       
/* 2868 */       this.m00 = d1; this.m01 = d2; this.m02 = d3; this.m03 = d4;
/* 2869 */       this.m10 = d5; this.m11 = d6; this.m12 = d7; this.m13 = d8;
/* 2870 */       this.m20 = d9; this.m21 = d10; this.m22 = d11; this.m23 = d12;
/* 2871 */       this.m30 = d13; this.m31 = d14; this.m32 = d15; this.m33 = d16;
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
/*      */   public final void mulTransposeLeft(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
/* 2884 */     if (this != paramMatrix4d1 && this != paramMatrix4d2) {
/* 2885 */       this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m30 * paramMatrix4d2.m30;
/* 2886 */       this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m21 + paramMatrix4d1.m30 * paramMatrix4d2.m31;
/* 2887 */       this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m10 * paramMatrix4d2.m12 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m32;
/* 2888 */       this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m10 * paramMatrix4d2.m13 + paramMatrix4d1.m20 * paramMatrix4d2.m23 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
/*      */       
/* 2890 */       this.m10 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m30;
/* 2891 */       this.m11 = paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m31 * paramMatrix4d2.m31;
/* 2892 */       this.m12 = paramMatrix4d1.m01 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m32;
/* 2893 */       this.m13 = paramMatrix4d1.m01 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m21 * paramMatrix4d2.m23 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
/*      */       
/* 2895 */       this.m20 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m32 * paramMatrix4d2.m30;
/* 2896 */       this.m21 = paramMatrix4d1.m02 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m31;
/* 2897 */       this.m22 = paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m32;
/* 2898 */       this.m23 = paramMatrix4d1.m02 * paramMatrix4d2.m03 + paramMatrix4d1.m12 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
/*      */       
/* 2900 */       this.m30 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m10 + paramMatrix4d1.m23 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
/* 2901 */       this.m31 = paramMatrix4d1.m03 * paramMatrix4d2.m01 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
/* 2902 */       this.m32 = paramMatrix4d1.m03 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
/* 2903 */       this.m33 = paramMatrix4d1.m03 * paramMatrix4d2.m03 + paramMatrix4d1.m13 * paramMatrix4d2.m13 + paramMatrix4d1.m23 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 2912 */       double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m30 * paramMatrix4d2.m30;
/* 2913 */       double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m21 + paramMatrix4d1.m30 * paramMatrix4d2.m31;
/* 2914 */       double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m10 * paramMatrix4d2.m12 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m32;
/* 2915 */       double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m10 * paramMatrix4d2.m13 + paramMatrix4d1.m20 * paramMatrix4d2.m23 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
/*      */       
/* 2917 */       double d5 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m30;
/* 2918 */       double d6 = paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m31 * paramMatrix4d2.m31;
/* 2919 */       double d7 = paramMatrix4d1.m01 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m32;
/* 2920 */       double d8 = paramMatrix4d1.m01 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m21 * paramMatrix4d2.m23 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
/*      */       
/* 2922 */       double d9 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m32 * paramMatrix4d2.m30;
/* 2923 */       double d10 = paramMatrix4d1.m02 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m31;
/* 2924 */       double d11 = paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m32;
/* 2925 */       double d12 = paramMatrix4d1.m02 * paramMatrix4d2.m03 + paramMatrix4d1.m12 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
/*      */       
/* 2927 */       double d13 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m10 + paramMatrix4d1.m23 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
/* 2928 */       double d14 = paramMatrix4d1.m03 * paramMatrix4d2.m01 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
/* 2929 */       double d15 = paramMatrix4d1.m03 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
/* 2930 */       double d16 = paramMatrix4d1.m03 * paramMatrix4d2.m03 + paramMatrix4d1.m13 * paramMatrix4d2.m13 + paramMatrix4d1.m23 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
/*      */       
/* 2932 */       this.m00 = d1; this.m01 = d2; this.m02 = d3; this.m03 = d4;
/* 2933 */       this.m10 = d5; this.m11 = d6; this.m12 = d7; this.m13 = d8;
/* 2934 */       this.m20 = d9; this.m21 = d10; this.m22 = d11; this.m23 = d12;
/* 2935 */       this.m30 = d13; this.m31 = d14; this.m32 = d15; this.m33 = d16;
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
/*      */   public boolean equals(Matrix4d paramMatrix4d) {
/*      */     try {
/* 2950 */       return (this.m00 == paramMatrix4d.m00 && this.m01 == paramMatrix4d.m01 && this.m02 == paramMatrix4d.m02 && this.m03 == paramMatrix4d.m03 && this.m10 == paramMatrix4d.m10 && this.m11 == paramMatrix4d.m11 && this.m12 == paramMatrix4d.m12 && this.m13 == paramMatrix4d.m13 && this.m20 == paramMatrix4d.m20 && this.m21 == paramMatrix4d.m21 && this.m22 == paramMatrix4d.m22 && this.m23 == paramMatrix4d.m23 && this.m30 == paramMatrix4d.m30 && this.m31 == paramMatrix4d.m31 && this.m32 == paramMatrix4d.m32 && this.m33 == paramMatrix4d.m33);
/*      */ 
/*      */     
/*      */     }
/*      */     catch (NullPointerException nullPointerException) {
/*      */ 
/*      */       
/* 2957 */       return false;
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
/* 2971 */     try { Matrix4d matrix4d = (Matrix4d)paramObject;
/* 2972 */       return (this.m00 == matrix4d.m00 && this.m01 == matrix4d.m01 && this.m02 == matrix4d.m02 && this.m03 == matrix4d.m03 && this.m10 == matrix4d.m10 && this.m11 == matrix4d.m11 && this.m12 == matrix4d.m12 && this.m13 == matrix4d.m13 && this.m20 == matrix4d.m20 && this.m21 == matrix4d.m21 && this.m22 == matrix4d.m22 && this.m23 == matrix4d.m23 && this.m30 == matrix4d.m30 && this.m31 == matrix4d.m31 && this.m32 == matrix4d.m32 && this.m33 == matrix4d.m33);
/*      */       
/*      */        }
/*      */     
/*      */     catch (ClassCastException classCastException)
/*      */     
/*      */     { 
/* 2979 */       return false; }
/* 2980 */     catch (NullPointerException nullPointerException) { return false; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2987 */   public boolean epsilonEquals(Matrix4d paramMatrix4d, float paramFloat) { return epsilonEquals(paramMatrix4d, paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean epsilonEquals(Matrix4d paramMatrix4d, double paramDouble) {
/* 3002 */     double d = this.m00 - paramMatrix4d.m00;
/* 3003 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3005 */     d = this.m01 - paramMatrix4d.m01;
/* 3006 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3008 */     d = this.m02 - paramMatrix4d.m02;
/* 3009 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3011 */     d = this.m03 - paramMatrix4d.m03;
/* 3012 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3014 */     d = this.m10 - paramMatrix4d.m10;
/* 3015 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3017 */     d = this.m11 - paramMatrix4d.m11;
/* 3018 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3020 */     d = this.m12 - paramMatrix4d.m12;
/* 3021 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3023 */     d = this.m13 - paramMatrix4d.m13;
/* 3024 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3026 */     d = this.m20 - paramMatrix4d.m20;
/* 3027 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3029 */     d = this.m21 - paramMatrix4d.m21;
/* 3030 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3032 */     d = this.m22 - paramMatrix4d.m22;
/* 3033 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3035 */     d = this.m23 - paramMatrix4d.m23;
/* 3036 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3038 */     d = this.m30 - paramMatrix4d.m30;
/* 3039 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3041 */     d = this.m31 - paramMatrix4d.m31;
/* 3042 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3044 */     d = this.m32 - paramMatrix4d.m32;
/* 3045 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3047 */     d = this.m33 - paramMatrix4d.m33;
/* 3048 */     if (((d < 0.0D) ? -d : d) > paramDouble) return false;
/*      */     
/* 3050 */     return true;
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
/*      */   public int hashCode() {
/* 3062 */     long l = 1L;
/* 3063 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m00);
/* 3064 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m01);
/* 3065 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m02);
/* 3066 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m03);
/* 3067 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m10);
/* 3068 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m11);
/* 3069 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m12);
/* 3070 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m13);
/* 3071 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m20);
/* 3072 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m21);
/* 3073 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m22);
/* 3074 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m23);
/* 3075 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m30);
/* 3076 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m31);
/* 3077 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m32);
/* 3078 */     l = 31L * l + VecMathUtil.doubleToLongBits(this.m33);
/* 3079 */     return (int)(l ^ l >> 32);
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
/*      */   public final void transform(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2) {
/* 3092 */     double d1 = this.m00 * paramTuple4d1.x + this.m01 * paramTuple4d1.y + this.m02 * paramTuple4d1.z + this.m03 * paramTuple4d1.w;
/*      */     
/* 3094 */     double d2 = this.m10 * paramTuple4d1.x + this.m11 * paramTuple4d1.y + this.m12 * paramTuple4d1.z + this.m13 * paramTuple4d1.w;
/*      */     
/* 3096 */     double d3 = this.m20 * paramTuple4d1.x + this.m21 * paramTuple4d1.y + this.m22 * paramTuple4d1.z + this.m23 * paramTuple4d1.w;
/*      */     
/* 3098 */     paramTuple4d2.w = this.m30 * paramTuple4d1.x + this.m31 * paramTuple4d1.y + this.m32 * paramTuple4d1.z + this.m33 * paramTuple4d1.w;
/*      */     
/* 3100 */     paramTuple4d2.x = d1;
/* 3101 */     paramTuple4d2.y = d2;
/* 3102 */     paramTuple4d2.z = d3;
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
/*      */   public final void transform(Tuple4d paramTuple4d) {
/* 3114 */     double d1 = this.m00 * paramTuple4d.x + this.m01 * paramTuple4d.y + this.m02 * paramTuple4d.z + this.m03 * paramTuple4d.w;
/*      */     
/* 3116 */     double d2 = this.m10 * paramTuple4d.x + this.m11 * paramTuple4d.y + this.m12 * paramTuple4d.z + this.m13 * paramTuple4d.w;
/*      */     
/* 3118 */     double d3 = this.m20 * paramTuple4d.x + this.m21 * paramTuple4d.y + this.m22 * paramTuple4d.z + this.m23 * paramTuple4d.w;
/*      */     
/* 3120 */     paramTuple4d.w = this.m30 * paramTuple4d.x + this.m31 * paramTuple4d.y + this.m32 * paramTuple4d.z + this.m33 * paramTuple4d.w;
/*      */     
/* 3122 */     paramTuple4d.x = d1;
/* 3123 */     paramTuple4d.y = d2;
/* 3124 */     paramTuple4d.z = d3;
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
/*      */   public final void transform(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
/* 3136 */     float f1 = (float)(this.m00 * paramTuple4f1.x + this.m01 * paramTuple4f1.y + this.m02 * paramTuple4f1.z + this.m03 * paramTuple4f1.w);
/*      */     
/* 3138 */     float f2 = (float)(this.m10 * paramTuple4f1.x + this.m11 * paramTuple4f1.y + this.m12 * paramTuple4f1.z + this.m13 * paramTuple4f1.w);
/*      */     
/* 3140 */     float f3 = (float)(this.m20 * paramTuple4f1.x + this.m21 * paramTuple4f1.y + this.m22 * paramTuple4f1.z + this.m23 * paramTuple4f1.w);
/*      */     
/* 3142 */     paramTuple4f2.w = (float)(this.m30 * paramTuple4f1.x + this.m31 * paramTuple4f1.y + this.m32 * paramTuple4f1.z + this.m33 * paramTuple4f1.w);
/*      */     
/* 3144 */     paramTuple4f2.x = f1;
/* 3145 */     paramTuple4f2.y = f2;
/* 3146 */     paramTuple4f2.z = f3;
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
/*      */   public final void transform(Tuple4f paramTuple4f) {
/* 3158 */     float f1 = (float)(this.m00 * paramTuple4f.x + this.m01 * paramTuple4f.y + this.m02 * paramTuple4f.z + this.m03 * paramTuple4f.w);
/*      */     
/* 3160 */     float f2 = (float)(this.m10 * paramTuple4f.x + this.m11 * paramTuple4f.y + this.m12 * paramTuple4f.z + this.m13 * paramTuple4f.w);
/*      */     
/* 3162 */     float f3 = (float)(this.m20 * paramTuple4f.x + this.m21 * paramTuple4f.y + this.m22 * paramTuple4f.z + this.m23 * paramTuple4f.w);
/*      */     
/* 3164 */     paramTuple4f.w = (float)(this.m30 * paramTuple4f.x + this.m31 * paramTuple4f.y + this.m32 * paramTuple4f.z + this.m33 * paramTuple4f.w);
/*      */     
/* 3166 */     paramTuple4f.x = f1;
/* 3167 */     paramTuple4f.y = f2;
/* 3168 */     paramTuple4f.z = f3;
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
/*      */   public final void transform(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/* 3182 */     double d1 = this.m00 * paramPoint3d1.x + this.m01 * paramPoint3d1.y + this.m02 * paramPoint3d1.z + this.m03;
/* 3183 */     double d2 = this.m10 * paramPoint3d1.x + this.m11 * paramPoint3d1.y + this.m12 * paramPoint3d1.z + this.m13;
/* 3184 */     paramPoint3d2.z = this.m20 * paramPoint3d1.x + this.m21 * paramPoint3d1.y + this.m22 * paramPoint3d1.z + this.m23;
/* 3185 */     paramPoint3d2.x = d1;
/* 3186 */     paramPoint3d2.y = d2;
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
/*      */   public final void transform(Point3d paramPoint3d) {
/* 3200 */     double d1 = this.m00 * paramPoint3d.x + this.m01 * paramPoint3d.y + this.m02 * paramPoint3d.z + this.m03;
/* 3201 */     double d2 = this.m10 * paramPoint3d.x + this.m11 * paramPoint3d.y + this.m12 * paramPoint3d.z + this.m13;
/* 3202 */     paramPoint3d.z = this.m20 * paramPoint3d.x + this.m21 * paramPoint3d.y + this.m22 * paramPoint3d.z + this.m23;
/* 3203 */     paramPoint3d.x = d1;
/* 3204 */     paramPoint3d.y = d2;
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
/*      */   public final void transform(Point3f paramPoint3f1, Point3f paramPoint3f2) {
/* 3219 */     float f1 = (float)(this.m00 * paramPoint3f1.x + this.m01 * paramPoint3f1.y + this.m02 * paramPoint3f1.z + this.m03);
/* 3220 */     float f2 = (float)(this.m10 * paramPoint3f1.x + this.m11 * paramPoint3f1.y + this.m12 * paramPoint3f1.z + this.m13);
/* 3221 */     paramPoint3f2.z = (float)(this.m20 * paramPoint3f1.x + this.m21 * paramPoint3f1.y + this.m22 * paramPoint3f1.z + this.m23);
/* 3222 */     paramPoint3f2.x = f1;
/* 3223 */     paramPoint3f2.y = f2;
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
/*      */   public final void transform(Point3f paramPoint3f) {
/* 3236 */     float f1 = (float)(this.m00 * paramPoint3f.x + this.m01 * paramPoint3f.y + this.m02 * paramPoint3f.z + this.m03);
/* 3237 */     float f2 = (float)(this.m10 * paramPoint3f.x + this.m11 * paramPoint3f.y + this.m12 * paramPoint3f.z + this.m13);
/* 3238 */     paramPoint3f.z = (float)(this.m20 * paramPoint3f.x + this.m21 * paramPoint3f.y + this.m22 * paramPoint3f.z + this.m23);
/* 3239 */     paramPoint3f.x = f1;
/* 3240 */     paramPoint3f.y = f2;
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
/*      */   public final void transform(Vector3d paramVector3d1, Vector3d paramVector3d2) {
/* 3253 */     double d1 = this.m00 * paramVector3d1.x + this.m01 * paramVector3d1.y + this.m02 * paramVector3d1.z;
/* 3254 */     double d2 = this.m10 * paramVector3d1.x + this.m11 * paramVector3d1.y + this.m12 * paramVector3d1.z;
/* 3255 */     paramVector3d2.z = this.m20 * paramVector3d1.x + this.m21 * paramVector3d1.y + this.m22 * paramVector3d1.z;
/* 3256 */     paramVector3d2.x = d1;
/* 3257 */     paramVector3d2.y = d2;
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
/*      */   public final void transform(Vector3d paramVector3d) {
/* 3270 */     double d1 = this.m00 * paramVector3d.x + this.m01 * paramVector3d.y + this.m02 * paramVector3d.z;
/* 3271 */     double d2 = this.m10 * paramVector3d.x + this.m11 * paramVector3d.y + this.m12 * paramVector3d.z;
/* 3272 */     paramVector3d.z = this.m20 * paramVector3d.x + this.m21 * paramVector3d.y + this.m22 * paramVector3d.z;
/* 3273 */     paramVector3d.x = d1;
/* 3274 */     paramVector3d.y = d2;
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
/*      */   public final void transform(Vector3f paramVector3f1, Vector3f paramVector3f2) {
/* 3287 */     float f1 = (float)(this.m00 * paramVector3f1.x + this.m01 * paramVector3f1.y + this.m02 * paramVector3f1.z);
/* 3288 */     float f2 = (float)(this.m10 * paramVector3f1.x + this.m11 * paramVector3f1.y + this.m12 * paramVector3f1.z);
/* 3289 */     paramVector3f2.z = (float)(this.m20 * paramVector3f1.x + this.m21 * paramVector3f1.y + this.m22 * paramVector3f1.z);
/* 3290 */     paramVector3f2.x = f1;
/* 3291 */     paramVector3f2.y = f2;
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
/*      */   public final void transform(Vector3f paramVector3f) {
/* 3304 */     float f1 = (float)(this.m00 * paramVector3f.x + this.m01 * paramVector3f.y + this.m02 * paramVector3f.z);
/* 3305 */     float f2 = (float)(this.m10 * paramVector3f.x + this.m11 * paramVector3f.y + this.m12 * paramVector3f.z);
/* 3306 */     paramVector3f.z = (float)(this.m20 * paramVector3f.x + this.m21 * paramVector3f.y + this.m22 * paramVector3f.z);
/* 3307 */     paramVector3f.x = f1;
/* 3308 */     paramVector3f.y = f2;
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
/*      */   public final void setRotation(Matrix3d paramMatrix3d) {
/* 3322 */     double[] arrayOfDouble1 = new double[9];
/* 3323 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 3325 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3327 */     this.m00 = paramMatrix3d.m00 * arrayOfDouble2[0];
/* 3328 */     this.m01 = paramMatrix3d.m01 * arrayOfDouble2[1];
/* 3329 */     this.m02 = paramMatrix3d.m02 * arrayOfDouble2[2];
/*      */     
/* 3331 */     this.m10 = paramMatrix3d.m10 * arrayOfDouble2[0];
/* 3332 */     this.m11 = paramMatrix3d.m11 * arrayOfDouble2[1];
/* 3333 */     this.m12 = paramMatrix3d.m12 * arrayOfDouble2[2];
/*      */     
/* 3335 */     this.m20 = paramMatrix3d.m20 * arrayOfDouble2[0];
/* 3336 */     this.m21 = paramMatrix3d.m21 * arrayOfDouble2[1];
/* 3337 */     this.m22 = paramMatrix3d.m22 * arrayOfDouble2[2];
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
/*      */   public final void setRotation(Matrix3f paramMatrix3f) {
/* 3355 */     double[] arrayOfDouble1 = new double[9];
/* 3356 */     double[] arrayOfDouble2 = new double[3];
/* 3357 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3359 */     this.m00 = paramMatrix3f.m00 * arrayOfDouble2[0];
/* 3360 */     this.m01 = paramMatrix3f.m01 * arrayOfDouble2[1];
/* 3361 */     this.m02 = paramMatrix3f.m02 * arrayOfDouble2[2];
/*      */     
/* 3363 */     this.m10 = paramMatrix3f.m10 * arrayOfDouble2[0];
/* 3364 */     this.m11 = paramMatrix3f.m11 * arrayOfDouble2[1];
/* 3365 */     this.m12 = paramMatrix3f.m12 * arrayOfDouble2[2];
/*      */     
/* 3367 */     this.m20 = paramMatrix3f.m20 * arrayOfDouble2[0];
/* 3368 */     this.m21 = paramMatrix3f.m21 * arrayOfDouble2[1];
/* 3369 */     this.m22 = paramMatrix3f.m22 * arrayOfDouble2[2];
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
/*      */   public final void setRotation(Quat4f paramQuat4f) {
/* 3383 */     double[] arrayOfDouble1 = new double[9];
/* 3384 */     double[] arrayOfDouble2 = new double[3];
/* 3385 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3387 */     this.m00 = (1.0D - (2.0F * paramQuat4f.y * paramQuat4f.y) - (2.0F * paramQuat4f.z * paramQuat4f.z)) * arrayOfDouble2[0];
/* 3388 */     this.m10 = 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z) * arrayOfDouble2[0];
/* 3389 */     this.m20 = 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y) * arrayOfDouble2[0];
/*      */     
/* 3391 */     this.m01 = 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z) * arrayOfDouble2[1];
/* 3392 */     this.m11 = (1.0D - (2.0F * paramQuat4f.x * paramQuat4f.x) - (2.0F * paramQuat4f.z * paramQuat4f.z)) * arrayOfDouble2[1];
/* 3393 */     this.m21 = 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x) * arrayOfDouble2[1];
/*      */     
/* 3395 */     this.m02 = 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y) * arrayOfDouble2[2];
/* 3396 */     this.m12 = 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x) * arrayOfDouble2[2];
/* 3397 */     this.m22 = (1.0D - (2.0F * paramQuat4f.x * paramQuat4f.x) - (2.0F * paramQuat4f.y * paramQuat4f.y)) * arrayOfDouble2[2];
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
/*      */   public final void setRotation(Quat4d paramQuat4d) {
/* 3414 */     double[] arrayOfDouble1 = new double[9];
/* 3415 */     double[] arrayOfDouble2 = new double[3];
/* 3416 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3418 */     this.m00 = (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z) * arrayOfDouble2[0];
/* 3419 */     this.m10 = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z) * arrayOfDouble2[0];
/* 3420 */     this.m20 = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y) * arrayOfDouble2[0];
/*      */     
/* 3422 */     this.m01 = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z) * arrayOfDouble2[1];
/* 3423 */     this.m11 = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z) * arrayOfDouble2[1];
/* 3424 */     this.m21 = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x) * arrayOfDouble2[1];
/*      */     
/* 3426 */     this.m02 = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y) * arrayOfDouble2[2];
/* 3427 */     this.m12 = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x) * arrayOfDouble2[2];
/* 3428 */     this.m22 = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y) * arrayOfDouble2[2];
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
/*      */   public final void setRotation(AxisAngle4d paramAxisAngle4d) {
/* 3444 */     double[] arrayOfDouble1 = new double[9];
/* 3445 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 3447 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3449 */     double d1 = 1.0D / Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/* 3450 */     double d2 = paramAxisAngle4d.x * d1;
/* 3451 */     double d3 = paramAxisAngle4d.y * d1;
/* 3452 */     double d4 = paramAxisAngle4d.z * d1;
/*      */     
/* 3454 */     double d5 = Math.sin(paramAxisAngle4d.angle);
/* 3455 */     double d6 = Math.cos(paramAxisAngle4d.angle);
/* 3456 */     double d7 = 1.0D - d6;
/*      */     
/* 3458 */     double d8 = paramAxisAngle4d.x * paramAxisAngle4d.z;
/* 3459 */     double d9 = paramAxisAngle4d.x * paramAxisAngle4d.y;
/* 3460 */     double d10 = paramAxisAngle4d.y * paramAxisAngle4d.z;
/*      */     
/* 3462 */     this.m00 = (d7 * d2 * d2 + d6) * arrayOfDouble2[0];
/* 3463 */     this.m01 = (d7 * d9 - d5 * d4) * arrayOfDouble2[1];
/* 3464 */     this.m02 = (d7 * d8 + d5 * d3) * arrayOfDouble2[2];
/*      */     
/* 3466 */     this.m10 = (d7 * d9 + d5 * d4) * arrayOfDouble2[0];
/* 3467 */     this.m11 = (d7 * d3 * d3 + d6) * arrayOfDouble2[1];
/* 3468 */     this.m12 = (d7 * d10 - d5 * d2) * arrayOfDouble2[2];
/*      */     
/* 3470 */     this.m20 = (d7 * d8 - d5 * d3) * arrayOfDouble2[0];
/* 3471 */     this.m21 = (d7 * d10 + d5 * d2) * arrayOfDouble2[1];
/* 3472 */     this.m22 = (d7 * d4 * d4 + d6) * arrayOfDouble2[2];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setZero() {
/* 3481 */     this.m00 = 0.0D;
/* 3482 */     this.m01 = 0.0D;
/* 3483 */     this.m02 = 0.0D;
/* 3484 */     this.m03 = 0.0D;
/* 3485 */     this.m10 = 0.0D;
/* 3486 */     this.m11 = 0.0D;
/* 3487 */     this.m12 = 0.0D;
/* 3488 */     this.m13 = 0.0D;
/* 3489 */     this.m20 = 0.0D;
/* 3490 */     this.m21 = 0.0D;
/* 3491 */     this.m22 = 0.0D;
/* 3492 */     this.m23 = 0.0D;
/* 3493 */     this.m30 = 0.0D;
/* 3494 */     this.m31 = 0.0D;
/* 3495 */     this.m32 = 0.0D;
/* 3496 */     this.m33 = 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate() {
/* 3504 */     this.m00 = -this.m00;
/* 3505 */     this.m01 = -this.m01;
/* 3506 */     this.m02 = -this.m02;
/* 3507 */     this.m03 = -this.m03;
/* 3508 */     this.m10 = -this.m10;
/* 3509 */     this.m11 = -this.m11;
/* 3510 */     this.m12 = -this.m12;
/* 3511 */     this.m13 = -this.m13;
/* 3512 */     this.m20 = -this.m20;
/* 3513 */     this.m21 = -this.m21;
/* 3514 */     this.m22 = -this.m22;
/* 3515 */     this.m23 = -this.m23;
/* 3516 */     this.m30 = -this.m30;
/* 3517 */     this.m31 = -this.m31;
/* 3518 */     this.m32 = -this.m32;
/* 3519 */     this.m33 = -this.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate(Matrix4d paramMatrix4d) {
/* 3529 */     this.m00 = -paramMatrix4d.m00;
/* 3530 */     this.m01 = -paramMatrix4d.m01;
/* 3531 */     this.m02 = -paramMatrix4d.m02;
/* 3532 */     this.m03 = -paramMatrix4d.m03;
/* 3533 */     this.m10 = -paramMatrix4d.m10;
/* 3534 */     this.m11 = -paramMatrix4d.m11;
/* 3535 */     this.m12 = -paramMatrix4d.m12;
/* 3536 */     this.m13 = -paramMatrix4d.m13;
/* 3537 */     this.m20 = -paramMatrix4d.m20;
/* 3538 */     this.m21 = -paramMatrix4d.m21;
/* 3539 */     this.m22 = -paramMatrix4d.m22;
/* 3540 */     this.m23 = -paramMatrix4d.m23;
/* 3541 */     this.m30 = -paramMatrix4d.m30;
/* 3542 */     this.m31 = -paramMatrix4d.m31;
/* 3543 */     this.m32 = -paramMatrix4d.m32;
/* 3544 */     this.m33 = -paramMatrix4d.m33;
/*      */   }
/*      */   private final void getScaleRotate(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 3547 */     double[] arrayOfDouble = new double[9];
/* 3548 */     arrayOfDouble[0] = this.m00;
/* 3549 */     arrayOfDouble[1] = this.m01;
/* 3550 */     arrayOfDouble[2] = this.m02;
/*      */     
/* 3552 */     arrayOfDouble[3] = this.m10;
/* 3553 */     arrayOfDouble[4] = this.m11;
/* 3554 */     arrayOfDouble[5] = this.m12;
/*      */     
/* 3556 */     arrayOfDouble[6] = this.m20;
/* 3557 */     arrayOfDouble[7] = this.m21;
/* 3558 */     arrayOfDouble[8] = this.m22;
/*      */     
/* 3560 */     Matrix3d.compute_svd(arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2);
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
/*      */   public Object clone() {
/* 3574 */     Matrix4d matrix4d = null;
/*      */     try {
/* 3576 */       matrix4d = (Matrix4d)super.clone();
/* 3577 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*      */       
/* 3579 */       throw new InternalError();
/*      */     } 
/*      */     
/* 3582 */     return matrix4d;
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
/* 3593 */   public final double getM00() { return this.m00; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3605 */   public final void setM00(double paramDouble) { this.m00 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3616 */   public final double getM01() { return this.m01; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3627 */   public final void setM01(double paramDouble) { this.m01 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3638 */   public final double getM02() { return this.m02; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3649 */   public final void setM02(double paramDouble) { this.m02 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3660 */   public final double getM10() { return this.m10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3671 */   public final void setM10(double paramDouble) { this.m10 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3682 */   public final double getM11() { return this.m11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3693 */   public final void setM11(double paramDouble) { this.m11 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3704 */   public final double getM12() { return this.m12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3716 */   public final void setM12(double paramDouble) { this.m12 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3727 */   public final double getM20() { return this.m20; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3738 */   public final void setM20(double paramDouble) { this.m20 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3749 */   public final double getM21() { return this.m21; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3760 */   public final void setM21(double paramDouble) { this.m21 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3771 */   public final double getM22() { return this.m22; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3782 */   public final void setM22(double paramDouble) { this.m22 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3793 */   public final double getM03() { return this.m03; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3804 */   public final void setM03(double paramDouble) { this.m03 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3815 */   public final double getM13() { return this.m13; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3826 */   public final void setM13(double paramDouble) { this.m13 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3837 */   public final double getM23() { return this.m23; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3848 */   public final void setM23(double paramDouble) { this.m23 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3859 */   public final double getM30() { return this.m30; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3870 */   public final void setM30(double paramDouble) { this.m30 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3881 */   public final double getM31() { return this.m31; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3892 */   public final void setM31(double paramDouble) { this.m31 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3904 */   public final double getM32() { return this.m32; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3915 */   public final void setM32(double paramDouble) { this.m32 = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3926 */   public final double getM33() { return this.m33; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3937 */   public final void setM33(double paramDouble) { this.m33 = paramDouble; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Matrix4d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */