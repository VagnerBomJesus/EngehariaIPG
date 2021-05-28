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
/*      */ public class Matrix4f
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   static final long serialVersionUID = -8405036035410109353L;
/*      */   public float m00;
/*      */   public float m01;
/*      */   public float m02;
/*      */   public float m03;
/*      */   public float m10;
/*      */   public float m11;
/*      */   public float m12;
/*      */   public float m13;
/*      */   public float m20;
/*      */   public float m21;
/*      */   public float m22;
/*      */   public float m23;
/*      */   public float m30;
/*      */   public float m31;
/*      */   public float m32;
/*      */   public float m33;
/*      */   private static final double EPS = 1.0E-8D;
/*      */   
/*      */   public Matrix4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16) {
/*  137 */     this.m00 = paramFloat1;
/*  138 */     this.m01 = paramFloat2;
/*  139 */     this.m02 = paramFloat3;
/*  140 */     this.m03 = paramFloat4;
/*      */     
/*  142 */     this.m10 = paramFloat5;
/*  143 */     this.m11 = paramFloat6;
/*  144 */     this.m12 = paramFloat7;
/*  145 */     this.m13 = paramFloat8;
/*      */     
/*  147 */     this.m20 = paramFloat9;
/*  148 */     this.m21 = paramFloat10;
/*  149 */     this.m22 = paramFloat11;
/*  150 */     this.m23 = paramFloat12;
/*      */     
/*  152 */     this.m30 = paramFloat13;
/*  153 */     this.m31 = paramFloat14;
/*  154 */     this.m32 = paramFloat15;
/*  155 */     this.m33 = paramFloat16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix4f(float[] paramArrayOfFloat) {
/*  166 */     this.m00 = paramArrayOfFloat[0];
/*  167 */     this.m01 = paramArrayOfFloat[1];
/*  168 */     this.m02 = paramArrayOfFloat[2];
/*  169 */     this.m03 = paramArrayOfFloat[3];
/*      */     
/*  171 */     this.m10 = paramArrayOfFloat[4];
/*  172 */     this.m11 = paramArrayOfFloat[5];
/*  173 */     this.m12 = paramArrayOfFloat[6];
/*  174 */     this.m13 = paramArrayOfFloat[7];
/*      */     
/*  176 */     this.m20 = paramArrayOfFloat[8];
/*  177 */     this.m21 = paramArrayOfFloat[9];
/*  178 */     this.m22 = paramArrayOfFloat[10];
/*  179 */     this.m23 = paramArrayOfFloat[11];
/*      */     
/*  181 */     this.m30 = paramArrayOfFloat[12];
/*  182 */     this.m31 = paramArrayOfFloat[13];
/*  183 */     this.m32 = paramArrayOfFloat[14];
/*  184 */     this.m33 = paramArrayOfFloat[15];
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
/*      */   public Matrix4f(Quat4f paramQuat4f, Vector3f paramVector3f, float paramFloat) {
/*  199 */     this.m00 = (float)(paramFloat * (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z));
/*  200 */     this.m10 = (float)(paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z));
/*  201 */     this.m20 = (float)(paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y));
/*      */     
/*  203 */     this.m01 = (float)(paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z));
/*  204 */     this.m11 = (float)(paramFloat * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z));
/*  205 */     this.m21 = (float)(paramFloat * 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x));
/*      */     
/*  207 */     this.m02 = (float)(paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y));
/*  208 */     this.m12 = (float)(paramFloat * 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x));
/*  209 */     this.m22 = (float)(paramFloat * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y));
/*      */     
/*  211 */     this.m03 = paramVector3f.x;
/*  212 */     this.m13 = paramVector3f.y;
/*  213 */     this.m23 = paramVector3f.z;
/*      */     
/*  215 */     this.m30 = 0.0F;
/*  216 */     this.m31 = 0.0F;
/*  217 */     this.m32 = 0.0F;
/*  218 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix4f(Matrix4d paramMatrix4d) {
/*  229 */     this.m00 = (float)paramMatrix4d.m00;
/*  230 */     this.m01 = (float)paramMatrix4d.m01;
/*  231 */     this.m02 = (float)paramMatrix4d.m02;
/*  232 */     this.m03 = (float)paramMatrix4d.m03;
/*      */     
/*  234 */     this.m10 = (float)paramMatrix4d.m10;
/*  235 */     this.m11 = (float)paramMatrix4d.m11;
/*  236 */     this.m12 = (float)paramMatrix4d.m12;
/*  237 */     this.m13 = (float)paramMatrix4d.m13;
/*      */     
/*  239 */     this.m20 = (float)paramMatrix4d.m20;
/*  240 */     this.m21 = (float)paramMatrix4d.m21;
/*  241 */     this.m22 = (float)paramMatrix4d.m22;
/*  242 */     this.m23 = (float)paramMatrix4d.m23;
/*      */     
/*  244 */     this.m30 = (float)paramMatrix4d.m30;
/*  245 */     this.m31 = (float)paramMatrix4d.m31;
/*  246 */     this.m32 = (float)paramMatrix4d.m32;
/*  247 */     this.m33 = (float)paramMatrix4d.m33;
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
/*      */   public Matrix4f(Matrix4f paramMatrix4f) {
/*  259 */     this.m00 = paramMatrix4f.m00;
/*  260 */     this.m01 = paramMatrix4f.m01;
/*  261 */     this.m02 = paramMatrix4f.m02;
/*  262 */     this.m03 = paramMatrix4f.m03;
/*      */     
/*  264 */     this.m10 = paramMatrix4f.m10;
/*  265 */     this.m11 = paramMatrix4f.m11;
/*  266 */     this.m12 = paramMatrix4f.m12;
/*  267 */     this.m13 = paramMatrix4f.m13;
/*      */     
/*  269 */     this.m20 = paramMatrix4f.m20;
/*  270 */     this.m21 = paramMatrix4f.m21;
/*  271 */     this.m22 = paramMatrix4f.m22;
/*  272 */     this.m23 = paramMatrix4f.m23;
/*      */     
/*  274 */     this.m30 = paramMatrix4f.m30;
/*  275 */     this.m31 = paramMatrix4f.m31;
/*  276 */     this.m32 = paramMatrix4f.m32;
/*  277 */     this.m33 = paramMatrix4f.m33;
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
/*      */   public Matrix4f(Matrix3f paramMatrix3f, Vector3f paramVector3f, float paramFloat) {
/*  293 */     this.m00 = paramMatrix3f.m00 * paramFloat;
/*  294 */     this.m01 = paramMatrix3f.m01 * paramFloat;
/*  295 */     this.m02 = paramMatrix3f.m02 * paramFloat;
/*  296 */     this.m03 = paramVector3f.x;
/*      */     
/*  298 */     this.m10 = paramMatrix3f.m10 * paramFloat;
/*  299 */     this.m11 = paramMatrix3f.m11 * paramFloat;
/*  300 */     this.m12 = paramMatrix3f.m12 * paramFloat;
/*  301 */     this.m13 = paramVector3f.y;
/*      */     
/*  303 */     this.m20 = paramMatrix3f.m20 * paramFloat;
/*  304 */     this.m21 = paramMatrix3f.m21 * paramFloat;
/*  305 */     this.m22 = paramMatrix3f.m22 * paramFloat;
/*  306 */     this.m23 = paramVector3f.z;
/*      */     
/*  308 */     this.m30 = 0.0F;
/*  309 */     this.m31 = 0.0F;
/*  310 */     this.m32 = 0.0F;
/*  311 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix4f() {
/*  321 */     this.m00 = 0.0F;
/*  322 */     this.m01 = 0.0F;
/*  323 */     this.m02 = 0.0F;
/*  324 */     this.m03 = 0.0F;
/*      */     
/*  326 */     this.m10 = 0.0F;
/*  327 */     this.m11 = 0.0F;
/*  328 */     this.m12 = 0.0F;
/*  329 */     this.m13 = 0.0F;
/*      */     
/*  331 */     this.m20 = 0.0F;
/*  332 */     this.m21 = 0.0F;
/*  333 */     this.m22 = 0.0F;
/*  334 */     this.m23 = 0.0F;
/*      */     
/*  336 */     this.m30 = 0.0F;
/*  337 */     this.m31 = 0.0F;
/*  338 */     this.m32 = 0.0F;
/*  339 */     this.m33 = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  348 */   public String toString() { return this.m00 + ", " + this.m01 + ", " + this.m02 + ", " + this.m03 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + ", " + this.m13 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + ", " + this.m23 + "\n" + this.m30 + ", " + this.m31 + ", " + this.m32 + ", " + this.m33 + "\n"; }
/*      */ 
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
/*  360 */     this.m00 = 1.0F;
/*  361 */     this.m01 = 0.0F;
/*  362 */     this.m02 = 0.0F;
/*  363 */     this.m03 = 0.0F;
/*      */     
/*  365 */     this.m10 = 0.0F;
/*  366 */     this.m11 = 1.0F;
/*  367 */     this.m12 = 0.0F;
/*  368 */     this.m13 = 0.0F;
/*      */     
/*  370 */     this.m20 = 0.0F;
/*  371 */     this.m21 = 0.0F;
/*  372 */     this.m22 = 1.0F;
/*  373 */     this.m23 = 0.0F;
/*      */     
/*  375 */     this.m30 = 0.0F;
/*  376 */     this.m31 = 0.0F;
/*  377 */     this.m32 = 0.0F;
/*  378 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setElement(int paramInt1, int paramInt2, float paramFloat) {
/*  389 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  392 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  395 */             this.m00 = paramFloat;
/*      */             return;
/*      */           case 1:
/*  398 */             this.m01 = paramFloat;
/*      */             return;
/*      */           case 2:
/*  401 */             this.m02 = paramFloat;
/*      */             return;
/*      */           case 3:
/*  404 */             this.m03 = paramFloat;
/*      */             return;
/*      */         } 
/*  407 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  412 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  415 */             this.m10 = paramFloat;
/*      */             return;
/*      */           case 1:
/*  418 */             this.m11 = paramFloat;
/*      */             return;
/*      */           case 2:
/*  421 */             this.m12 = paramFloat;
/*      */             return;
/*      */           case 3:
/*  424 */             this.m13 = paramFloat;
/*      */             return;
/*      */         } 
/*  427 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  432 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  435 */             this.m20 = paramFloat;
/*      */             return;
/*      */           case 1:
/*  438 */             this.m21 = paramFloat;
/*      */             return;
/*      */           case 2:
/*  441 */             this.m22 = paramFloat;
/*      */             return;
/*      */           case 3:
/*  444 */             this.m23 = paramFloat;
/*      */             return;
/*      */         } 
/*  447 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*  452 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  455 */             this.m30 = paramFloat;
/*      */             return;
/*      */           case 1:
/*  458 */             this.m31 = paramFloat;
/*      */             return;
/*      */           case 2:
/*  461 */             this.m32 = paramFloat;
/*      */             return;
/*      */           case 3:
/*  464 */             this.m33 = paramFloat;
/*      */             return;
/*      */         } 
/*  467 */         throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  472 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
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
/*  484 */     switch (paramInt1) {
/*      */       
/*      */       case 0:
/*  487 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  490 */             return this.m00;
/*      */           case 1:
/*  492 */             return this.m01;
/*      */           case 2:
/*  494 */             return this.m02;
/*      */           case 3:
/*  496 */             return this.m03;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case 1:
/*  502 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  505 */             return this.m10;
/*      */           case 1:
/*  507 */             return this.m11;
/*      */           case 2:
/*  509 */             return this.m12;
/*      */           case 3:
/*  511 */             return this.m13;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 2:
/*  518 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  521 */             return this.m20;
/*      */           case 1:
/*  523 */             return this.m21;
/*      */           case 2:
/*  525 */             return this.m22;
/*      */           case 3:
/*  527 */             return this.m23;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 3:
/*  534 */         switch (paramInt2) {
/*      */           
/*      */           case 0:
/*  537 */             return this.m30;
/*      */           case 1:
/*  539 */             return this.m31;
/*      */           case 2:
/*  541 */             return this.m32;
/*      */           case 3:
/*  543 */             return this.m33;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  552 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f1"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, Vector4f paramVector4f) {
/*  561 */     if (paramInt == 0) {
/*  562 */       paramVector4f.x = this.m00;
/*  563 */       paramVector4f.y = this.m01;
/*  564 */       paramVector4f.z = this.m02;
/*  565 */       paramVector4f.w = this.m03;
/*  566 */     } else if (paramInt == 1) {
/*  567 */       paramVector4f.x = this.m10;
/*  568 */       paramVector4f.y = this.m11;
/*  569 */       paramVector4f.z = this.m12;
/*  570 */       paramVector4f.w = this.m13;
/*  571 */     } else if (paramInt == 2) {
/*  572 */       paramVector4f.x = this.m20;
/*  573 */       paramVector4f.y = this.m21;
/*  574 */       paramVector4f.z = this.m22;
/*  575 */       paramVector4f.w = this.m23;
/*  576 */     } else if (paramInt == 3) {
/*  577 */       paramVector4f.x = this.m30;
/*  578 */       paramVector4f.y = this.m31;
/*  579 */       paramVector4f.z = this.m32;
/*  580 */       paramVector4f.w = this.m33;
/*      */     } else {
/*  582 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f2"));
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
/*  593 */     if (paramInt == 0) {
/*  594 */       paramArrayOfFloat[0] = this.m00;
/*  595 */       paramArrayOfFloat[1] = this.m01;
/*  596 */       paramArrayOfFloat[2] = this.m02;
/*  597 */       paramArrayOfFloat[3] = this.m03;
/*  598 */     } else if (paramInt == 1) {
/*  599 */       paramArrayOfFloat[0] = this.m10;
/*  600 */       paramArrayOfFloat[1] = this.m11;
/*  601 */       paramArrayOfFloat[2] = this.m12;
/*  602 */       paramArrayOfFloat[3] = this.m13;
/*  603 */     } else if (paramInt == 2) {
/*  604 */       paramArrayOfFloat[0] = this.m20;
/*  605 */       paramArrayOfFloat[1] = this.m21;
/*  606 */       paramArrayOfFloat[2] = this.m22;
/*  607 */       paramArrayOfFloat[3] = this.m23;
/*  608 */     } else if (paramInt == 3) {
/*  609 */       paramArrayOfFloat[0] = this.m30;
/*  610 */       paramArrayOfFloat[1] = this.m31;
/*  611 */       paramArrayOfFloat[2] = this.m32;
/*  612 */       paramArrayOfFloat[3] = this.m33;
/*      */     } else {
/*  614 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f2"));
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
/*      */   public final void getColumn(int paramInt, Vector4f paramVector4f) {
/*  626 */     if (paramInt == 0) {
/*  627 */       paramVector4f.x = this.m00;
/*  628 */       paramVector4f.y = this.m10;
/*  629 */       paramVector4f.z = this.m20;
/*  630 */       paramVector4f.w = this.m30;
/*  631 */     } else if (paramInt == 1) {
/*  632 */       paramVector4f.x = this.m01;
/*  633 */       paramVector4f.y = this.m11;
/*  634 */       paramVector4f.z = this.m21;
/*  635 */       paramVector4f.w = this.m31;
/*  636 */     } else if (paramInt == 2) {
/*  637 */       paramVector4f.x = this.m02;
/*  638 */       paramVector4f.y = this.m12;
/*  639 */       paramVector4f.z = this.m22;
/*  640 */       paramVector4f.w = this.m32;
/*  641 */     } else if (paramInt == 3) {
/*  642 */       paramVector4f.x = this.m03;
/*  643 */       paramVector4f.y = this.m13;
/*  644 */       paramVector4f.z = this.m23;
/*  645 */       paramVector4f.w = this.m33;
/*      */     } else {
/*  647 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f4"));
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
/*  659 */     if (paramInt == 0) {
/*  660 */       paramArrayOfFloat[0] = this.m00;
/*  661 */       paramArrayOfFloat[1] = this.m10;
/*  662 */       paramArrayOfFloat[2] = this.m20;
/*  663 */       paramArrayOfFloat[3] = this.m30;
/*  664 */     } else if (paramInt == 1) {
/*  665 */       paramArrayOfFloat[0] = this.m01;
/*  666 */       paramArrayOfFloat[1] = this.m11;
/*  667 */       paramArrayOfFloat[2] = this.m21;
/*  668 */       paramArrayOfFloat[3] = this.m31;
/*  669 */     } else if (paramInt == 2) {
/*  670 */       paramArrayOfFloat[0] = this.m02;
/*  671 */       paramArrayOfFloat[1] = this.m12;
/*  672 */       paramArrayOfFloat[2] = this.m22;
/*  673 */       paramArrayOfFloat[3] = this.m32;
/*  674 */     } else if (paramInt == 3) {
/*  675 */       paramArrayOfFloat[0] = this.m03;
/*  676 */       paramArrayOfFloat[1] = this.m13;
/*  677 */       paramArrayOfFloat[2] = this.m23;
/*  678 */       paramArrayOfFloat[3] = this.m33;
/*      */     } else {
/*  680 */       throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f4"));
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
/*      */   public final void setScale(float paramFloat) {
/*  694 */     double[] arrayOfDouble1 = new double[9];
/*  695 */     double[] arrayOfDouble2 = new double[3];
/*  696 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  698 */     this.m00 = (float)(arrayOfDouble1[0] * paramFloat);
/*  699 */     this.m01 = (float)(arrayOfDouble1[1] * paramFloat);
/*  700 */     this.m02 = (float)(arrayOfDouble1[2] * paramFloat);
/*      */     
/*  702 */     this.m10 = (float)(arrayOfDouble1[3] * paramFloat);
/*  703 */     this.m11 = (float)(arrayOfDouble1[4] * paramFloat);
/*  704 */     this.m12 = (float)(arrayOfDouble1[5] * paramFloat);
/*      */     
/*  706 */     this.m20 = (float)(arrayOfDouble1[6] * paramFloat);
/*  707 */     this.m21 = (float)(arrayOfDouble1[7] * paramFloat);
/*  708 */     this.m22 = (float)(arrayOfDouble1[8] * paramFloat);
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
/*      */   public final void get(Matrix3d paramMatrix3d) {
/*  720 */     double[] arrayOfDouble1 = new double[9];
/*  721 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  723 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  725 */     paramMatrix3d.m00 = arrayOfDouble1[0];
/*  726 */     paramMatrix3d.m01 = arrayOfDouble1[1];
/*  727 */     paramMatrix3d.m02 = arrayOfDouble1[2];
/*      */     
/*  729 */     paramMatrix3d.m10 = arrayOfDouble1[3];
/*  730 */     paramMatrix3d.m11 = arrayOfDouble1[4];
/*  731 */     paramMatrix3d.m12 = arrayOfDouble1[5];
/*      */     
/*  733 */     paramMatrix3d.m20 = arrayOfDouble1[6];
/*  734 */     paramMatrix3d.m21 = arrayOfDouble1[7];
/*  735 */     paramMatrix3d.m22 = arrayOfDouble1[8];
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
/*      */   public final void get(Matrix3f paramMatrix3f) {
/*  747 */     double[] arrayOfDouble1 = new double[9];
/*  748 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  750 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  752 */     paramMatrix3f.m00 = (float)arrayOfDouble1[0];
/*  753 */     paramMatrix3f.m01 = (float)arrayOfDouble1[1];
/*  754 */     paramMatrix3f.m02 = (float)arrayOfDouble1[2];
/*      */     
/*  756 */     paramMatrix3f.m10 = (float)arrayOfDouble1[3];
/*  757 */     paramMatrix3f.m11 = (float)arrayOfDouble1[4];
/*  758 */     paramMatrix3f.m12 = (float)arrayOfDouble1[5];
/*      */     
/*  760 */     paramMatrix3f.m20 = (float)arrayOfDouble1[6];
/*  761 */     paramMatrix3f.m21 = (float)arrayOfDouble1[7];
/*  762 */     paramMatrix3f.m22 = (float)arrayOfDouble1[8];
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
/*      */   public final float get(Matrix3f paramMatrix3f, Vector3f paramVector3f) {
/*  777 */     double[] arrayOfDouble1 = new double[9];
/*  778 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  780 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  782 */     paramMatrix3f.m00 = (float)arrayOfDouble1[0];
/*  783 */     paramMatrix3f.m01 = (float)arrayOfDouble1[1];
/*  784 */     paramMatrix3f.m02 = (float)arrayOfDouble1[2];
/*      */     
/*  786 */     paramMatrix3f.m10 = (float)arrayOfDouble1[3];
/*  787 */     paramMatrix3f.m11 = (float)arrayOfDouble1[4];
/*  788 */     paramMatrix3f.m12 = (float)arrayOfDouble1[5];
/*      */     
/*  790 */     paramMatrix3f.m20 = (float)arrayOfDouble1[6];
/*  791 */     paramMatrix3f.m21 = (float)arrayOfDouble1[7];
/*  792 */     paramMatrix3f.m22 = (float)arrayOfDouble1[8];
/*      */     
/*  794 */     paramVector3f.x = this.m03;
/*  795 */     paramVector3f.y = this.m13;
/*  796 */     paramVector3f.z = this.m23;
/*      */     
/*  798 */     return (float)Matrix3d.max3(arrayOfDouble2);
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
/*      */   public final void get(Quat4f paramQuat4f) {
/*  810 */     double[] arrayOfDouble1 = new double[9];
/*  811 */     double[] arrayOfDouble2 = new double[3];
/*  812 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */ 
/*      */ 
/*      */     
/*  816 */     double d = 0.25D * (1.0D + arrayOfDouble1[0] + arrayOfDouble1[4] + arrayOfDouble1[8]);
/*  817 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  818 */       paramQuat4f.w = (float)Math.sqrt(d);
/*  819 */       d = 0.25D / paramQuat4f.w;
/*  820 */       paramQuat4f.x = (float)((arrayOfDouble1[7] - arrayOfDouble1[5]) * d);
/*  821 */       paramQuat4f.y = (float)((arrayOfDouble1[2] - arrayOfDouble1[6]) * d);
/*  822 */       paramQuat4f.z = (float)((arrayOfDouble1[3] - arrayOfDouble1[1]) * d);
/*      */       
/*      */       return;
/*      */     } 
/*  826 */     paramQuat4f.w = 0.0F;
/*  827 */     d = -0.5D * (arrayOfDouble1[4] + arrayOfDouble1[8]);
/*  828 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  829 */       paramQuat4f.x = (float)Math.sqrt(d);
/*  830 */       d = 0.5D / paramQuat4f.x;
/*  831 */       paramQuat4f.y = (float)(arrayOfDouble1[3] * d);
/*  832 */       paramQuat4f.z = (float)(arrayOfDouble1[6] * d);
/*      */       
/*      */       return;
/*      */     } 
/*  836 */     paramQuat4f.x = 0.0F;
/*  837 */     d = 0.5D * (1.0D - arrayOfDouble1[8]);
/*  838 */     if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
/*  839 */       paramQuat4f.y = (float)Math.sqrt(d);
/*  840 */       paramQuat4f.z = (float)(arrayOfDouble1[7] / 2.0D * paramQuat4f.y);
/*      */       
/*      */       return;
/*      */     } 
/*  844 */     paramQuat4f.y = 0.0F;
/*  845 */     paramQuat4f.z = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Vector3f paramVector3f) {
/*  856 */     paramVector3f.x = this.m03;
/*  857 */     paramVector3f.y = this.m13;
/*  858 */     paramVector3f.z = this.m23;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRotationScale(Matrix3f paramMatrix3f) {
/*  868 */     paramMatrix3f.m00 = this.m00; paramMatrix3f.m01 = this.m01; paramMatrix3f.m02 = this.m02;
/*  869 */     paramMatrix3f.m10 = this.m10; paramMatrix3f.m11 = this.m11; paramMatrix3f.m12 = this.m12;
/*  870 */     paramMatrix3f.m20 = this.m20; paramMatrix3f.m21 = this.m21; paramMatrix3f.m22 = this.m22;
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
/*      */   public final float getScale() {
/*  882 */     double[] arrayOfDouble1 = new double[9];
/*  883 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/*  885 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/*  887 */     return (float)Matrix3d.max3(arrayOfDouble2);
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
/*      */   public final void setRotationScale(Matrix3f paramMatrix3f) {
/*  899 */     this.m00 = paramMatrix3f.m00; this.m01 = paramMatrix3f.m01; this.m02 = paramMatrix3f.m02;
/*  900 */     this.m10 = paramMatrix3f.m10; this.m11 = paramMatrix3f.m11; this.m12 = paramMatrix3f.m12;
/*  901 */     this.m20 = paramMatrix3f.m20; this.m21 = paramMatrix3f.m21; this.m22 = paramMatrix3f.m22;
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
/*      */   public final void setRow(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  915 */     switch (paramInt) {
/*      */       case 0:
/*  917 */         this.m00 = paramFloat1;
/*  918 */         this.m01 = paramFloat2;
/*  919 */         this.m02 = paramFloat3;
/*  920 */         this.m03 = paramFloat4;
/*      */         return;
/*      */       
/*      */       case 1:
/*  924 */         this.m10 = paramFloat1;
/*  925 */         this.m11 = paramFloat2;
/*  926 */         this.m12 = paramFloat3;
/*  927 */         this.m13 = paramFloat4;
/*      */         return;
/*      */       
/*      */       case 2:
/*  931 */         this.m20 = paramFloat1;
/*  932 */         this.m21 = paramFloat2;
/*  933 */         this.m22 = paramFloat3;
/*  934 */         this.m23 = paramFloat4;
/*      */         return;
/*      */       
/*      */       case 3:
/*  938 */         this.m30 = paramFloat1;
/*  939 */         this.m31 = paramFloat2;
/*  940 */         this.m32 = paramFloat3;
/*  941 */         this.m33 = paramFloat4;
/*      */         return;
/*      */     } 
/*      */     
/*  945 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f6"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRow(int paramInt, Vector4f paramVector4f) {
/*  956 */     switch (paramInt) {
/*      */       case 0:
/*  958 */         this.m00 = paramVector4f.x;
/*  959 */         this.m01 = paramVector4f.y;
/*  960 */         this.m02 = paramVector4f.z;
/*  961 */         this.m03 = paramVector4f.w;
/*      */         return;
/*      */       
/*      */       case 1:
/*  965 */         this.m10 = paramVector4f.x;
/*  966 */         this.m11 = paramVector4f.y;
/*  967 */         this.m12 = paramVector4f.z;
/*  968 */         this.m13 = paramVector4f.w;
/*      */         return;
/*      */       
/*      */       case 2:
/*  972 */         this.m20 = paramVector4f.x;
/*  973 */         this.m21 = paramVector4f.y;
/*  974 */         this.m22 = paramVector4f.z;
/*  975 */         this.m23 = paramVector4f.w;
/*      */         return;
/*      */       
/*      */       case 3:
/*  979 */         this.m30 = paramVector4f.x;
/*  980 */         this.m31 = paramVector4f.y;
/*  981 */         this.m32 = paramVector4f.z;
/*  982 */         this.m33 = paramVector4f.w;
/*      */         return;
/*      */     } 
/*      */     
/*  986 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f6"));
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
/*      */   public final void setRow(int paramInt, float[] paramArrayOfFloat) {
/*  998 */     switch (paramInt) {
/*      */       case 0:
/* 1000 */         this.m00 = paramArrayOfFloat[0];
/* 1001 */         this.m01 = paramArrayOfFloat[1];
/* 1002 */         this.m02 = paramArrayOfFloat[2];
/* 1003 */         this.m03 = paramArrayOfFloat[3];
/*      */         return;
/*      */       
/*      */       case 1:
/* 1007 */         this.m10 = paramArrayOfFloat[0];
/* 1008 */         this.m11 = paramArrayOfFloat[1];
/* 1009 */         this.m12 = paramArrayOfFloat[2];
/* 1010 */         this.m13 = paramArrayOfFloat[3];
/*      */         return;
/*      */       
/*      */       case 2:
/* 1014 */         this.m20 = paramArrayOfFloat[0];
/* 1015 */         this.m21 = paramArrayOfFloat[1];
/* 1016 */         this.m22 = paramArrayOfFloat[2];
/* 1017 */         this.m23 = paramArrayOfFloat[3];
/*      */         return;
/*      */       
/*      */       case 3:
/* 1021 */         this.m30 = paramArrayOfFloat[0];
/* 1022 */         this.m31 = paramArrayOfFloat[1];
/* 1023 */         this.m32 = paramArrayOfFloat[2];
/* 1024 */         this.m33 = paramArrayOfFloat[3];
/*      */         return;
/*      */     } 
/*      */     
/* 1028 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f6"));
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
/*      */   public final void setColumn(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 1042 */     switch (paramInt) {
/*      */       case 0:
/* 1044 */         this.m00 = paramFloat1;
/* 1045 */         this.m10 = paramFloat2;
/* 1046 */         this.m20 = paramFloat3;
/* 1047 */         this.m30 = paramFloat4;
/*      */         return;
/*      */       
/*      */       case 1:
/* 1051 */         this.m01 = paramFloat1;
/* 1052 */         this.m11 = paramFloat2;
/* 1053 */         this.m21 = paramFloat3;
/* 1054 */         this.m31 = paramFloat4;
/*      */         return;
/*      */       
/*      */       case 2:
/* 1058 */         this.m02 = paramFloat1;
/* 1059 */         this.m12 = paramFloat2;
/* 1060 */         this.m22 = paramFloat3;
/* 1061 */         this.m32 = paramFloat4;
/*      */         return;
/*      */       
/*      */       case 3:
/* 1065 */         this.m03 = paramFloat1;
/* 1066 */         this.m13 = paramFloat2;
/* 1067 */         this.m23 = paramFloat3;
/* 1068 */         this.m33 = paramFloat4;
/*      */         return;
/*      */     } 
/*      */     
/* 1072 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f9"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setColumn(int paramInt, Vector4f paramVector4f) {
/* 1083 */     switch (paramInt) {
/*      */       case 0:
/* 1085 */         this.m00 = paramVector4f.x;
/* 1086 */         this.m10 = paramVector4f.y;
/* 1087 */         this.m20 = paramVector4f.z;
/* 1088 */         this.m30 = paramVector4f.w;
/*      */         return;
/*      */       
/*      */       case 1:
/* 1092 */         this.m01 = paramVector4f.x;
/* 1093 */         this.m11 = paramVector4f.y;
/* 1094 */         this.m21 = paramVector4f.z;
/* 1095 */         this.m31 = paramVector4f.w;
/*      */         return;
/*      */       
/*      */       case 2:
/* 1099 */         this.m02 = paramVector4f.x;
/* 1100 */         this.m12 = paramVector4f.y;
/* 1101 */         this.m22 = paramVector4f.z;
/* 1102 */         this.m32 = paramVector4f.w;
/*      */         return;
/*      */       
/*      */       case 3:
/* 1106 */         this.m03 = paramVector4f.x;
/* 1107 */         this.m13 = paramVector4f.y;
/* 1108 */         this.m23 = paramVector4f.z;
/* 1109 */         this.m33 = paramVector4f.w;
/*      */         return;
/*      */     } 
/*      */     
/* 1113 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f9"));
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
/* 1124 */     switch (paramInt) {
/*      */       case 0:
/* 1126 */         this.m00 = paramArrayOfFloat[0];
/* 1127 */         this.m10 = paramArrayOfFloat[1];
/* 1128 */         this.m20 = paramArrayOfFloat[2];
/* 1129 */         this.m30 = paramArrayOfFloat[3];
/*      */         return;
/*      */       
/*      */       case 1:
/* 1133 */         this.m01 = paramArrayOfFloat[0];
/* 1134 */         this.m11 = paramArrayOfFloat[1];
/* 1135 */         this.m21 = paramArrayOfFloat[2];
/* 1136 */         this.m31 = paramArrayOfFloat[3];
/*      */         return;
/*      */       
/*      */       case 2:
/* 1140 */         this.m02 = paramArrayOfFloat[0];
/* 1141 */         this.m12 = paramArrayOfFloat[1];
/* 1142 */         this.m22 = paramArrayOfFloat[2];
/* 1143 */         this.m32 = paramArrayOfFloat[3];
/*      */         return;
/*      */       
/*      */       case 3:
/* 1147 */         this.m03 = paramArrayOfFloat[0];
/* 1148 */         this.m13 = paramArrayOfFloat[1];
/* 1149 */         this.m23 = paramArrayOfFloat[2];
/* 1150 */         this.m33 = paramArrayOfFloat[3];
/*      */         return;
/*      */     } 
/*      */     
/* 1154 */     throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f9"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(float paramFloat) {
/* 1164 */     this.m00 += paramFloat;
/* 1165 */     this.m01 += paramFloat;
/* 1166 */     this.m02 += paramFloat;
/* 1167 */     this.m03 += paramFloat;
/* 1168 */     this.m10 += paramFloat;
/* 1169 */     this.m11 += paramFloat;
/* 1170 */     this.m12 += paramFloat;
/* 1171 */     this.m13 += paramFloat;
/* 1172 */     this.m20 += paramFloat;
/* 1173 */     this.m21 += paramFloat;
/* 1174 */     this.m22 += paramFloat;
/* 1175 */     this.m23 += paramFloat;
/* 1176 */     this.m30 += paramFloat;
/* 1177 */     this.m31 += paramFloat;
/* 1178 */     this.m32 += paramFloat;
/* 1179 */     this.m33 += paramFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(float paramFloat, Matrix4f paramMatrix4f) {
/* 1190 */     paramMatrix4f.m00 += paramFloat;
/* 1191 */     paramMatrix4f.m01 += paramFloat;
/* 1192 */     paramMatrix4f.m02 += paramFloat;
/* 1193 */     paramMatrix4f.m03 += paramFloat;
/* 1194 */     paramMatrix4f.m10 += paramFloat;
/* 1195 */     paramMatrix4f.m11 += paramFloat;
/* 1196 */     paramMatrix4f.m12 += paramFloat;
/* 1197 */     paramMatrix4f.m13 += paramFloat;
/* 1198 */     paramMatrix4f.m20 += paramFloat;
/* 1199 */     paramMatrix4f.m21 += paramFloat;
/* 1200 */     paramMatrix4f.m22 += paramFloat;
/* 1201 */     paramMatrix4f.m23 += paramFloat;
/* 1202 */     paramMatrix4f.m30 += paramFloat;
/* 1203 */     paramMatrix4f.m31 += paramFloat;
/* 1204 */     paramMatrix4f.m32 += paramFloat;
/* 1205 */     paramMatrix4f.m33 += paramFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix4f paramMatrix4f1, Matrix4f paramMatrix4f2) {
/* 1215 */     paramMatrix4f1.m00 += paramMatrix4f2.m00;
/* 1216 */     paramMatrix4f1.m01 += paramMatrix4f2.m01;
/* 1217 */     paramMatrix4f1.m02 += paramMatrix4f2.m02;
/* 1218 */     paramMatrix4f1.m03 += paramMatrix4f2.m03;
/*      */     
/* 1220 */     paramMatrix4f1.m10 += paramMatrix4f2.m10;
/* 1221 */     paramMatrix4f1.m11 += paramMatrix4f2.m11;
/* 1222 */     paramMatrix4f1.m12 += paramMatrix4f2.m12;
/* 1223 */     paramMatrix4f1.m13 += paramMatrix4f2.m13;
/*      */     
/* 1225 */     paramMatrix4f1.m20 += paramMatrix4f2.m20;
/* 1226 */     paramMatrix4f1.m21 += paramMatrix4f2.m21;
/* 1227 */     paramMatrix4f1.m22 += paramMatrix4f2.m22;
/* 1228 */     paramMatrix4f1.m23 += paramMatrix4f2.m23;
/*      */     
/* 1230 */     paramMatrix4f1.m30 += paramMatrix4f2.m30;
/* 1231 */     paramMatrix4f1.m31 += paramMatrix4f2.m31;
/* 1232 */     paramMatrix4f1.m32 += paramMatrix4f2.m32;
/* 1233 */     paramMatrix4f1.m33 += paramMatrix4f2.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Matrix4f paramMatrix4f) {
/* 1243 */     this.m00 += paramMatrix4f.m00;
/* 1244 */     this.m01 += paramMatrix4f.m01;
/* 1245 */     this.m02 += paramMatrix4f.m02;
/* 1246 */     this.m03 += paramMatrix4f.m03;
/*      */     
/* 1248 */     this.m10 += paramMatrix4f.m10;
/* 1249 */     this.m11 += paramMatrix4f.m11;
/* 1250 */     this.m12 += paramMatrix4f.m12;
/* 1251 */     this.m13 += paramMatrix4f.m13;
/*      */     
/* 1253 */     this.m20 += paramMatrix4f.m20;
/* 1254 */     this.m21 += paramMatrix4f.m21;
/* 1255 */     this.m22 += paramMatrix4f.m22;
/* 1256 */     this.m23 += paramMatrix4f.m23;
/*      */     
/* 1258 */     this.m30 += paramMatrix4f.m30;
/* 1259 */     this.m31 += paramMatrix4f.m31;
/* 1260 */     this.m32 += paramMatrix4f.m32;
/* 1261 */     this.m33 += paramMatrix4f.m33;
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
/*      */   public final void sub(Matrix4f paramMatrix4f1, Matrix4f paramMatrix4f2) {
/* 1273 */     paramMatrix4f1.m00 -= paramMatrix4f2.m00;
/* 1274 */     paramMatrix4f1.m01 -= paramMatrix4f2.m01;
/* 1275 */     paramMatrix4f1.m02 -= paramMatrix4f2.m02;
/* 1276 */     paramMatrix4f1.m03 -= paramMatrix4f2.m03;
/*      */     
/* 1278 */     paramMatrix4f1.m10 -= paramMatrix4f2.m10;
/* 1279 */     paramMatrix4f1.m11 -= paramMatrix4f2.m11;
/* 1280 */     paramMatrix4f1.m12 -= paramMatrix4f2.m12;
/* 1281 */     paramMatrix4f1.m13 -= paramMatrix4f2.m13;
/*      */     
/* 1283 */     paramMatrix4f1.m20 -= paramMatrix4f2.m20;
/* 1284 */     paramMatrix4f1.m21 -= paramMatrix4f2.m21;
/* 1285 */     paramMatrix4f1.m22 -= paramMatrix4f2.m22;
/* 1286 */     paramMatrix4f1.m23 -= paramMatrix4f2.m23;
/*      */     
/* 1288 */     paramMatrix4f1.m30 -= paramMatrix4f2.m30;
/* 1289 */     paramMatrix4f1.m31 -= paramMatrix4f2.m31;
/* 1290 */     paramMatrix4f1.m32 -= paramMatrix4f2.m32;
/* 1291 */     paramMatrix4f1.m33 -= paramMatrix4f2.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Matrix4f paramMatrix4f) {
/* 1301 */     this.m00 -= paramMatrix4f.m00;
/* 1302 */     this.m01 -= paramMatrix4f.m01;
/* 1303 */     this.m02 -= paramMatrix4f.m02;
/* 1304 */     this.m03 -= paramMatrix4f.m03;
/*      */     
/* 1306 */     this.m10 -= paramMatrix4f.m10;
/* 1307 */     this.m11 -= paramMatrix4f.m11;
/* 1308 */     this.m12 -= paramMatrix4f.m12;
/* 1309 */     this.m13 -= paramMatrix4f.m13;
/*      */     
/* 1311 */     this.m20 -= paramMatrix4f.m20;
/* 1312 */     this.m21 -= paramMatrix4f.m21;
/* 1313 */     this.m22 -= paramMatrix4f.m22;
/* 1314 */     this.m23 -= paramMatrix4f.m23;
/*      */     
/* 1316 */     this.m30 -= paramMatrix4f.m30;
/* 1317 */     this.m31 -= paramMatrix4f.m31;
/* 1318 */     this.m32 -= paramMatrix4f.m32;
/* 1319 */     this.m33 -= paramMatrix4f.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose() {
/* 1329 */     float f = this.m10;
/* 1330 */     this.m10 = this.m01;
/* 1331 */     this.m01 = f;
/*      */     
/* 1333 */     f = this.m20;
/* 1334 */     this.m20 = this.m02;
/* 1335 */     this.m02 = f;
/*      */     
/* 1337 */     f = this.m30;
/* 1338 */     this.m30 = this.m03;
/* 1339 */     this.m03 = f;
/*      */     
/* 1341 */     f = this.m21;
/* 1342 */     this.m21 = this.m12;
/* 1343 */     this.m12 = f;
/*      */     
/* 1345 */     f = this.m31;
/* 1346 */     this.m31 = this.m13;
/* 1347 */     this.m13 = f;
/*      */     
/* 1349 */     f = this.m32;
/* 1350 */     this.m32 = this.m23;
/* 1351 */     this.m23 = f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose(Matrix4f paramMatrix4f) {
/* 1360 */     if (this != paramMatrix4f) {
/* 1361 */       this.m00 = paramMatrix4f.m00;
/* 1362 */       this.m01 = paramMatrix4f.m10;
/* 1363 */       this.m02 = paramMatrix4f.m20;
/* 1364 */       this.m03 = paramMatrix4f.m30;
/*      */       
/* 1366 */       this.m10 = paramMatrix4f.m01;
/* 1367 */       this.m11 = paramMatrix4f.m11;
/* 1368 */       this.m12 = paramMatrix4f.m21;
/* 1369 */       this.m13 = paramMatrix4f.m31;
/*      */       
/* 1371 */       this.m20 = paramMatrix4f.m02;
/* 1372 */       this.m21 = paramMatrix4f.m12;
/* 1373 */       this.m22 = paramMatrix4f.m22;
/* 1374 */       this.m23 = paramMatrix4f.m32;
/*      */       
/* 1376 */       this.m30 = paramMatrix4f.m03;
/* 1377 */       this.m31 = paramMatrix4f.m13;
/* 1378 */       this.m32 = paramMatrix4f.m23;
/* 1379 */       this.m33 = paramMatrix4f.m33;
/*      */     } else {
/* 1381 */       transpose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4f paramQuat4f) {
/* 1391 */     this.m00 = 1.0F - 2.0F * paramQuat4f.y * paramQuat4f.y - 2.0F * paramQuat4f.z * paramQuat4f.z;
/* 1392 */     this.m10 = 2.0F * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/* 1393 */     this.m20 = 2.0F * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/* 1395 */     this.m01 = 2.0F * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/* 1396 */     this.m11 = 1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.z * paramQuat4f.z;
/* 1397 */     this.m21 = 2.0F * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/* 1399 */     this.m02 = 2.0F * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/* 1400 */     this.m12 = 2.0F * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/* 1401 */     this.m22 = 1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.y * paramQuat4f.y;
/*      */     
/* 1403 */     this.m03 = 0.0F;
/* 1404 */     this.m13 = 0.0F;
/* 1405 */     this.m23 = 0.0F;
/*      */     
/* 1407 */     this.m30 = 0.0F;
/* 1408 */     this.m31 = 0.0F;
/* 1409 */     this.m32 = 0.0F;
/* 1410 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(AxisAngle4f paramAxisAngle4f) {
/* 1420 */     float f = (float)Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/* 1421 */     if (f < 1.0E-8D) {
/* 1422 */       this.m00 = 1.0F;
/* 1423 */       this.m01 = 0.0F;
/* 1424 */       this.m02 = 0.0F;
/*      */       
/* 1426 */       this.m10 = 0.0F;
/* 1427 */       this.m11 = 1.0F;
/* 1428 */       this.m12 = 0.0F;
/*      */       
/* 1430 */       this.m20 = 0.0F;
/* 1431 */       this.m21 = 0.0F;
/* 1432 */       this.m22 = 1.0F;
/*      */     } else {
/* 1434 */       f = 1.0F / f;
/* 1435 */       float f1 = paramAxisAngle4f.x * f;
/* 1436 */       float f2 = paramAxisAngle4f.y * f;
/* 1437 */       float f3 = paramAxisAngle4f.z * f;
/*      */       
/* 1439 */       float f4 = (float)Math.sin(paramAxisAngle4f.angle);
/* 1440 */       float f5 = (float)Math.cos(paramAxisAngle4f.angle);
/* 1441 */       float f6 = 1.0F - f5;
/*      */       
/* 1443 */       float f7 = f1 * f3;
/* 1444 */       float f8 = f1 * f2;
/* 1445 */       float f9 = f2 * f3;
/*      */       
/* 1447 */       this.m00 = f6 * f1 * f1 + f5;
/* 1448 */       this.m01 = f6 * f8 - f4 * f3;
/* 1449 */       this.m02 = f6 * f7 + f4 * f2;
/*      */       
/* 1451 */       this.m10 = f6 * f8 + f4 * f3;
/* 1452 */       this.m11 = f6 * f2 * f2 + f5;
/* 1453 */       this.m12 = f6 * f9 - f4 * f1;
/*      */       
/* 1455 */       this.m20 = f6 * f7 - f4 * f2;
/* 1456 */       this.m21 = f6 * f9 + f4 * f1;
/* 1457 */       this.m22 = f6 * f3 * f3 + f5;
/*      */     } 
/* 1459 */     this.m03 = 0.0F;
/* 1460 */     this.m13 = 0.0F;
/* 1461 */     this.m23 = 0.0F;
/*      */     
/* 1463 */     this.m30 = 0.0F;
/* 1464 */     this.m31 = 0.0F;
/* 1465 */     this.m32 = 0.0F;
/* 1466 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4d paramQuat4d) {
/* 1476 */     this.m00 = (float)(1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z);
/* 1477 */     this.m10 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z));
/* 1478 */     this.m20 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y));
/*      */     
/* 1480 */     this.m01 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z));
/* 1481 */     this.m11 = (float)(1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z);
/* 1482 */     this.m21 = (float)(2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x));
/*      */     
/* 1484 */     this.m02 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y));
/* 1485 */     this.m12 = (float)(2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x));
/* 1486 */     this.m22 = (float)(1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y);
/*      */     
/* 1488 */     this.m03 = 0.0F;
/* 1489 */     this.m13 = 0.0F;
/* 1490 */     this.m23 = 0.0F;
/*      */     
/* 1492 */     this.m30 = 0.0F;
/* 1493 */     this.m31 = 0.0F;
/* 1494 */     this.m32 = 0.0F;
/* 1495 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(AxisAngle4d paramAxisAngle4d) {
/* 1505 */     double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/*      */     
/* 1507 */     if (d < 1.0E-8D) {
/* 1508 */       this.m00 = 1.0F;
/* 1509 */       this.m01 = 0.0F;
/* 1510 */       this.m02 = 0.0F;
/*      */       
/* 1512 */       this.m10 = 0.0F;
/* 1513 */       this.m11 = 1.0F;
/* 1514 */       this.m12 = 0.0F;
/*      */       
/* 1516 */       this.m20 = 0.0F;
/* 1517 */       this.m21 = 0.0F;
/* 1518 */       this.m22 = 1.0F;
/*      */     } else {
/* 1520 */       d = 1.0D / d;
/* 1521 */       double d1 = paramAxisAngle4d.x * d;
/* 1522 */       double d2 = paramAxisAngle4d.y * d;
/* 1523 */       double d3 = paramAxisAngle4d.z * d;
/*      */       
/* 1525 */       float f1 = (float)Math.sin(paramAxisAngle4d.angle);
/* 1526 */       float f2 = (float)Math.cos(paramAxisAngle4d.angle);
/* 1527 */       float f3 = 1.0F - f2;
/*      */       
/* 1529 */       float f4 = (float)(d1 * d3);
/* 1530 */       float f5 = (float)(d1 * d2);
/* 1531 */       float f6 = (float)(d2 * d3);
/*      */       
/* 1533 */       this.m00 = f3 * (float)(d1 * d1) + f2;
/* 1534 */       this.m01 = f3 * f5 - f1 * (float)d3;
/* 1535 */       this.m02 = f3 * f4 + f1 * (float)d2;
/*      */       
/* 1537 */       this.m10 = f3 * f5 + f1 * (float)d3;
/* 1538 */       this.m11 = f3 * (float)(d2 * d2) + f2;
/* 1539 */       this.m12 = f3 * f6 - f1 * (float)d1;
/*      */       
/* 1541 */       this.m20 = f3 * f4 - f1 * (float)d2;
/* 1542 */       this.m21 = f3 * f6 + f1 * (float)d1;
/* 1543 */       this.m22 = f3 * (float)(d3 * d3) + f2;
/*      */     } 
/* 1545 */     this.m03 = 0.0F;
/* 1546 */     this.m13 = 0.0F;
/* 1547 */     this.m23 = 0.0F;
/*      */     
/* 1549 */     this.m30 = 0.0F;
/* 1550 */     this.m31 = 0.0F;
/* 1551 */     this.m32 = 0.0F;
/* 1552 */     this.m33 = 1.0F;
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
/* 1564 */     this.m00 = (float)(paramDouble * (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z));
/* 1565 */     this.m10 = (float)(paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z));
/* 1566 */     this.m20 = (float)(paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y));
/*      */     
/* 1568 */     this.m01 = (float)(paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z));
/* 1569 */     this.m11 = (float)(paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z));
/* 1570 */     this.m21 = (float)(paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x));
/*      */     
/* 1572 */     this.m02 = (float)(paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y));
/* 1573 */     this.m12 = (float)(paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x));
/* 1574 */     this.m22 = (float)(paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y));
/*      */     
/* 1576 */     this.m03 = (float)paramVector3d.x;
/* 1577 */     this.m13 = (float)paramVector3d.y;
/* 1578 */     this.m23 = (float)paramVector3d.z;
/*      */     
/* 1580 */     this.m30 = 0.0F;
/* 1581 */     this.m31 = 0.0F;
/* 1582 */     this.m32 = 0.0F;
/* 1583 */     this.m33 = 1.0F;
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
/* 1595 */     this.m00 = paramFloat * (1.0F - 2.0F * paramQuat4f.y * paramQuat4f.y - 2.0F * paramQuat4f.z * paramQuat4f.z);
/* 1596 */     this.m10 = paramFloat * 2.0F * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
/* 1597 */     this.m20 = paramFloat * 2.0F * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
/*      */     
/* 1599 */     this.m01 = paramFloat * 2.0F * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
/* 1600 */     this.m11 = paramFloat * (1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.z * paramQuat4f.z);
/* 1601 */     this.m21 = paramFloat * 2.0F * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
/*      */     
/* 1603 */     this.m02 = paramFloat * 2.0F * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
/* 1604 */     this.m12 = paramFloat * 2.0F * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
/* 1605 */     this.m22 = paramFloat * (1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.y * paramQuat4f.y);
/*      */     
/* 1607 */     this.m03 = paramVector3f.x;
/* 1608 */     this.m13 = paramVector3f.y;
/* 1609 */     this.m23 = paramVector3f.z;
/*      */     
/* 1611 */     this.m30 = 0.0F;
/* 1612 */     this.m31 = 0.0F;
/* 1613 */     this.m32 = 0.0F;
/* 1614 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix4d paramMatrix4d) {
/* 1624 */     this.m00 = (float)paramMatrix4d.m00;
/* 1625 */     this.m01 = (float)paramMatrix4d.m01;
/* 1626 */     this.m02 = (float)paramMatrix4d.m02;
/* 1627 */     this.m03 = (float)paramMatrix4d.m03;
/*      */     
/* 1629 */     this.m10 = (float)paramMatrix4d.m10;
/* 1630 */     this.m11 = (float)paramMatrix4d.m11;
/* 1631 */     this.m12 = (float)paramMatrix4d.m12;
/* 1632 */     this.m13 = (float)paramMatrix4d.m13;
/*      */     
/* 1634 */     this.m20 = (float)paramMatrix4d.m20;
/* 1635 */     this.m21 = (float)paramMatrix4d.m21;
/* 1636 */     this.m22 = (float)paramMatrix4d.m22;
/* 1637 */     this.m23 = (float)paramMatrix4d.m23;
/*      */     
/* 1639 */     this.m30 = (float)paramMatrix4d.m30;
/* 1640 */     this.m31 = (float)paramMatrix4d.m31;
/* 1641 */     this.m32 = (float)paramMatrix4d.m32;
/* 1642 */     this.m33 = (float)paramMatrix4d.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix4f paramMatrix4f) {
/* 1652 */     this.m00 = paramMatrix4f.m00;
/* 1653 */     this.m01 = paramMatrix4f.m01;
/* 1654 */     this.m02 = paramMatrix4f.m02;
/* 1655 */     this.m03 = paramMatrix4f.m03;
/*      */     
/* 1657 */     this.m10 = paramMatrix4f.m10;
/* 1658 */     this.m11 = paramMatrix4f.m11;
/* 1659 */     this.m12 = paramMatrix4f.m12;
/* 1660 */     this.m13 = paramMatrix4f.m13;
/*      */     
/* 1662 */     this.m20 = paramMatrix4f.m20;
/* 1663 */     this.m21 = paramMatrix4f.m21;
/* 1664 */     this.m22 = paramMatrix4f.m22;
/* 1665 */     this.m23 = paramMatrix4f.m23;
/*      */     
/* 1667 */     this.m30 = paramMatrix4f.m30;
/* 1668 */     this.m31 = paramMatrix4f.m31;
/* 1669 */     this.m32 = paramMatrix4f.m32;
/* 1670 */     this.m33 = paramMatrix4f.m33;
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
/* 1681 */   public final void invert(Matrix4f paramMatrix4f) { invertGeneral(paramMatrix4f); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1689 */   public final void invert() { invertGeneral(this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void invertGeneral(Matrix4f paramMatrix4f) {
/* 1701 */     double[] arrayOfDouble1 = new double[16];
/* 1702 */     double[] arrayOfDouble2 = new double[16];
/* 1703 */     int[] arrayOfInt = new int[4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1710 */     arrayOfDouble1[0] = paramMatrix4f.m00;
/* 1711 */     arrayOfDouble1[1] = paramMatrix4f.m01;
/* 1712 */     arrayOfDouble1[2] = paramMatrix4f.m02;
/* 1713 */     arrayOfDouble1[3] = paramMatrix4f.m03;
/*      */     
/* 1715 */     arrayOfDouble1[4] = paramMatrix4f.m10;
/* 1716 */     arrayOfDouble1[5] = paramMatrix4f.m11;
/* 1717 */     arrayOfDouble1[6] = paramMatrix4f.m12;
/* 1718 */     arrayOfDouble1[7] = paramMatrix4f.m13;
/*      */     
/* 1720 */     arrayOfDouble1[8] = paramMatrix4f.m20;
/* 1721 */     arrayOfDouble1[9] = paramMatrix4f.m21;
/* 1722 */     arrayOfDouble1[10] = paramMatrix4f.m22;
/* 1723 */     arrayOfDouble1[11] = paramMatrix4f.m23;
/*      */     
/* 1725 */     arrayOfDouble1[12] = paramMatrix4f.m30;
/* 1726 */     arrayOfDouble1[13] = paramMatrix4f.m31;
/* 1727 */     arrayOfDouble1[14] = paramMatrix4f.m32;
/* 1728 */     arrayOfDouble1[15] = paramMatrix4f.m33;
/*      */ 
/*      */     
/* 1731 */     if (!luDecomposition(arrayOfDouble1, arrayOfInt))
/*      */     {
/* 1733 */       throw new SingularMatrixException(VecMathI18N.getString("Matrix4f12"));
/*      */     }
/*      */ 
/*      */     
/* 1737 */     for (byte b = 0; b < 16; ) { arrayOfDouble2[b] = 0.0D; b++; }
/* 1738 */      arrayOfDouble2[0] = 1.0D; arrayOfDouble2[5] = 1.0D; arrayOfDouble2[10] = 1.0D; arrayOfDouble2[15] = 1.0D;
/* 1739 */     luBacksubstitution(arrayOfDouble1, arrayOfInt, arrayOfDouble2);
/*      */     
/* 1741 */     this.m00 = (float)arrayOfDouble2[0];
/* 1742 */     this.m01 = (float)arrayOfDouble2[1];
/* 1743 */     this.m02 = (float)arrayOfDouble2[2];
/* 1744 */     this.m03 = (float)arrayOfDouble2[3];
/*      */     
/* 1746 */     this.m10 = (float)arrayOfDouble2[4];
/* 1747 */     this.m11 = (float)arrayOfDouble2[5];
/* 1748 */     this.m12 = (float)arrayOfDouble2[6];
/* 1749 */     this.m13 = (float)arrayOfDouble2[7];
/*      */     
/* 1751 */     this.m20 = (float)arrayOfDouble2[8];
/* 1752 */     this.m21 = (float)arrayOfDouble2[9];
/* 1753 */     this.m22 = (float)arrayOfDouble2[10];
/* 1754 */     this.m23 = (float)arrayOfDouble2[11];
/*      */     
/* 1756 */     this.m30 = (float)arrayOfDouble2[12];
/* 1757 */     this.m31 = (float)arrayOfDouble2[13];
/* 1758 */     this.m32 = (float)arrayOfDouble2[14];
/* 1759 */     this.m33 = (float)arrayOfDouble2[15];
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
/* 1786 */     double[] arrayOfDouble = new double[4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1794 */     byte b3 = 0;
/* 1795 */     byte b4 = 0;
/*      */ 
/*      */     
/* 1798 */     byte b1 = 4;
/* 1799 */     while (b1-- != 0) {
/* 1800 */       double d = 0.0D;
/*      */ 
/*      */       
/* 1803 */       byte b = 4;
/* 1804 */       while (b-- != 0) {
/* 1805 */         double d1 = paramArrayOfDouble[b3++];
/* 1806 */         d1 = Math.abs(d1);
/* 1807 */         if (d1 > d) {
/* 1808 */           d = d1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1813 */       if (d == 0.0D) {
/* 1814 */         return false;
/*      */       }
/* 1816 */       arrayOfDouble[b4++] = 1.0D / d;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1824 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1827 */     for (b1 = 0; b1 < 4; b1++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1833 */       for (b3 = 0; b3 < b1; b3++) {
/* 1834 */         byte b6 = b2 + 4 * b3 + b1;
/* 1835 */         double d1 = paramArrayOfDouble[b6];
/* 1836 */         byte b5 = b3;
/* 1837 */         byte b7 = b2 + 4 * b3;
/* 1838 */         byte b8 = b2 + b1;
/* 1839 */         while (b5-- != 0) {
/* 1840 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 1841 */           b7++;
/* 1842 */           b8 += 4;
/*      */         } 
/* 1844 */         paramArrayOfDouble[b6] = d1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1849 */       double d = 0.0D;
/* 1850 */       b4 = -1;
/* 1851 */       for (b3 = b1; b3 < 4; b3++) {
/* 1852 */         byte b6 = b2 + 4 * b3 + b1;
/* 1853 */         double d1 = paramArrayOfDouble[b6];
/* 1854 */         byte b5 = b1;
/* 1855 */         byte b7 = b2 + 4 * b3;
/* 1856 */         byte b8 = b2 + b1;
/* 1857 */         while (b5-- != 0) {
/* 1858 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 1859 */           b7++;
/* 1860 */           b8 += 4;
/*      */         } 
/* 1862 */         paramArrayOfDouble[b6] = d1;
/*      */         
/*      */         double d2;
/* 1865 */         if ((d2 = arrayOfDouble[b3] * Math.abs(d1)) >= d) {
/* 1866 */           d = d2;
/* 1867 */           b4 = b3;
/*      */         } 
/*      */       } 
/*      */       
/* 1871 */       if (b4 < 0) {
/* 1872 */         throw new RuntimeException(VecMathI18N.getString("Matrix4f13"));
/*      */       }
/*      */ 
/*      */       
/* 1876 */       if (b1 != b4) {
/*      */         
/* 1878 */         byte b = 4;
/* 1879 */         byte b5 = b2 + 4 * b4;
/* 1880 */         byte b6 = b2 + 4 * b1;
/* 1881 */         while (b-- != 0) {
/* 1882 */           double d1 = paramArrayOfDouble[b5];
/* 1883 */           paramArrayOfDouble[b5++] = paramArrayOfDouble[b6];
/* 1884 */           paramArrayOfDouble[b6++] = d1;
/*      */         } 
/*      */ 
/*      */         
/* 1888 */         arrayOfDouble[b4] = arrayOfDouble[b1];
/*      */       } 
/*      */ 
/*      */       
/* 1892 */       paramArrayOfInt[b1] = b4;
/*      */ 
/*      */       
/* 1895 */       if (paramArrayOfDouble[b2 + 4 * b1 + b1] == 0.0D) {
/* 1896 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1900 */       if (b1 != 3) {
/* 1901 */         double d1 = 1.0D / paramArrayOfDouble[b2 + 4 * b1 + b1];
/* 1902 */         byte b = b2 + 4 * (b1 + 1) + b1;
/* 1903 */         b3 = 3 - b1;
/* 1904 */         while (b3-- != 0) {
/* 1905 */           paramArrayOfDouble[b] = paramArrayOfDouble[b] * d1;
/* 1906 */           b += 4;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1912 */     return true;
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
/* 1942 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1945 */     for (byte b1 = 0; b1 < 4; b1++) {
/*      */       
/* 1947 */       int i = b1;
/* 1948 */       byte b4 = -1;
/*      */ 
/*      */       
/* 1951 */       for (byte b3 = 0; b3 < 4; b3++) {
/*      */ 
/*      */         
/* 1954 */         int j = paramArrayOfInt[b2 + b3];
/* 1955 */         double d = paramArrayOfDouble2[i + 4 * j];
/* 1956 */         paramArrayOfDouble2[i + 4 * j] = paramArrayOfDouble2[i + 4 * b3];
/* 1957 */         if (b4 >= 0) {
/*      */           
/* 1959 */           byte b6 = b3 * 4;
/* 1960 */           for (byte b5 = b4; b5 <= b3 - 1; b5++) {
/* 1961 */             d -= paramArrayOfDouble1[b6 + b5] * paramArrayOfDouble2[i + 4 * b5];
/*      */           }
/*      */         }
/* 1964 */         else if (d != 0.0D) {
/* 1965 */           b4 = b3;
/*      */         } 
/* 1967 */         paramArrayOfDouble2[i + 4 * b3] = d;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1972 */       byte b = 12;
/* 1973 */       paramArrayOfDouble2[i + 12] = paramArrayOfDouble2[i + 12] / paramArrayOfDouble1[b + 3];
/*      */       
/* 1975 */       b -= 4;
/* 1976 */       paramArrayOfDouble2[i + 8] = (paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 2];
/*      */ 
/*      */       
/* 1979 */       b -= 4;
/* 1980 */       paramArrayOfDouble2[i + 4] = (paramArrayOfDouble2[i + 4] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 1];
/*      */ 
/*      */ 
/*      */       
/* 1984 */       b -= 4;
/* 1985 */       paramArrayOfDouble2[i + 0] = (paramArrayOfDouble2[i + 0] - paramArrayOfDouble1[b + 1] * paramArrayOfDouble2[i + 4] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 0];
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
/*      */   public final float determinant() {
/* 2002 */     null = this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
/*      */     
/* 2004 */     null -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
/*      */     
/* 2006 */     null += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
/*      */     
/* 2008 */     return this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
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
/*      */   public final void set(Matrix3f paramMatrix3f) {
/* 2023 */     this.m00 = paramMatrix3f.m00; this.m01 = paramMatrix3f.m01; this.m02 = paramMatrix3f.m02; this.m03 = 0.0F;
/* 2024 */     this.m10 = paramMatrix3f.m10; this.m11 = paramMatrix3f.m11; this.m12 = paramMatrix3f.m12; this.m13 = 0.0F;
/* 2025 */     this.m20 = paramMatrix3f.m20; this.m21 = paramMatrix3f.m21; this.m22 = paramMatrix3f.m22; this.m23 = 0.0F;
/* 2026 */     this.m30 = 0.0F; this.m31 = 0.0F; this.m32 = 0.0F; this.m33 = 1.0F;
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
/* 2038 */     this.m00 = (float)paramMatrix3d.m00; this.m01 = (float)paramMatrix3d.m01; this.m02 = (float)paramMatrix3d.m02; this.m03 = 0.0F;
/* 2039 */     this.m10 = (float)paramMatrix3d.m10; this.m11 = (float)paramMatrix3d.m11; this.m12 = (float)paramMatrix3d.m12; this.m13 = 0.0F;
/* 2040 */     this.m20 = (float)paramMatrix3d.m20; this.m21 = (float)paramMatrix3d.m21; this.m22 = (float)paramMatrix3d.m22; this.m23 = 0.0F;
/* 2041 */     this.m30 = 0.0F; this.m31 = 0.0F; this.m32 = 0.0F; this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(float paramFloat) {
/* 2051 */     this.m00 = paramFloat;
/* 2052 */     this.m01 = 0.0F;
/* 2053 */     this.m02 = 0.0F;
/* 2054 */     this.m03 = 0.0F;
/*      */     
/* 2056 */     this.m10 = 0.0F;
/* 2057 */     this.m11 = paramFloat;
/* 2058 */     this.m12 = 0.0F;
/* 2059 */     this.m13 = 0.0F;
/*      */     
/* 2061 */     this.m20 = 0.0F;
/* 2062 */     this.m21 = 0.0F;
/* 2063 */     this.m22 = paramFloat;
/* 2064 */     this.m23 = 0.0F;
/*      */     
/* 2066 */     this.m30 = 0.0F;
/* 2067 */     this.m31 = 0.0F;
/* 2068 */     this.m32 = 0.0F;
/* 2069 */     this.m33 = 1.0F;
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
/* 2080 */     this.m00 = paramArrayOfFloat[0];
/* 2081 */     this.m01 = paramArrayOfFloat[1];
/* 2082 */     this.m02 = paramArrayOfFloat[2];
/* 2083 */     this.m03 = paramArrayOfFloat[3];
/* 2084 */     this.m10 = paramArrayOfFloat[4];
/* 2085 */     this.m11 = paramArrayOfFloat[5];
/* 2086 */     this.m12 = paramArrayOfFloat[6];
/* 2087 */     this.m13 = paramArrayOfFloat[7];
/* 2088 */     this.m20 = paramArrayOfFloat[8];
/* 2089 */     this.m21 = paramArrayOfFloat[9];
/* 2090 */     this.m22 = paramArrayOfFloat[10];
/* 2091 */     this.m23 = paramArrayOfFloat[11];
/* 2092 */     this.m30 = paramArrayOfFloat[12];
/* 2093 */     this.m31 = paramArrayOfFloat[13];
/* 2094 */     this.m32 = paramArrayOfFloat[14];
/* 2095 */     this.m33 = paramArrayOfFloat[15];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Vector3f paramVector3f) {
/* 2105 */     this.m00 = 1.0F;
/* 2106 */     this.m01 = 0.0F;
/* 2107 */     this.m02 = 0.0F;
/* 2108 */     this.m03 = paramVector3f.x;
/*      */     
/* 2110 */     this.m10 = 0.0F;
/* 2111 */     this.m11 = 1.0F;
/* 2112 */     this.m12 = 0.0F;
/* 2113 */     this.m13 = paramVector3f.y;
/*      */     
/* 2115 */     this.m20 = 0.0F;
/* 2116 */     this.m21 = 0.0F;
/* 2117 */     this.m22 = 1.0F;
/* 2118 */     this.m23 = paramVector3f.z;
/*      */     
/* 2120 */     this.m30 = 0.0F;
/* 2121 */     this.m31 = 0.0F;
/* 2122 */     this.m32 = 0.0F;
/* 2123 */     this.m33 = 1.0F;
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
/*      */   public final void set(float paramFloat, Vector3f paramVector3f) {
/* 2135 */     this.m00 = paramFloat;
/* 2136 */     this.m01 = 0.0F;
/* 2137 */     this.m02 = 0.0F;
/* 2138 */     this.m03 = paramVector3f.x;
/*      */     
/* 2140 */     this.m10 = 0.0F;
/* 2141 */     this.m11 = paramFloat;
/* 2142 */     this.m12 = 0.0F;
/* 2143 */     this.m13 = paramVector3f.y;
/*      */     
/* 2145 */     this.m20 = 0.0F;
/* 2146 */     this.m21 = 0.0F;
/* 2147 */     this.m22 = paramFloat;
/* 2148 */     this.m23 = paramVector3f.z;
/*      */     
/* 2150 */     this.m30 = 0.0F;
/* 2151 */     this.m31 = 0.0F;
/* 2152 */     this.m32 = 0.0F;
/* 2153 */     this.m33 = 1.0F;
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
/*      */   public final void set(Vector3f paramVector3f, float paramFloat) {
/* 2165 */     this.m00 = paramFloat;
/* 2166 */     this.m01 = 0.0F;
/* 2167 */     this.m02 = 0.0F;
/* 2168 */     this.m03 = paramFloat * paramVector3f.x;
/*      */     
/* 2170 */     this.m10 = 0.0F;
/* 2171 */     this.m11 = paramFloat;
/* 2172 */     this.m12 = 0.0F;
/* 2173 */     this.m13 = paramFloat * paramVector3f.y;
/*      */     
/* 2175 */     this.m20 = 0.0F;
/* 2176 */     this.m21 = 0.0F;
/* 2177 */     this.m22 = paramFloat;
/* 2178 */     this.m23 = paramFloat * paramVector3f.z;
/*      */     
/* 2180 */     this.m30 = 0.0F;
/* 2181 */     this.m31 = 0.0F;
/* 2182 */     this.m32 = 0.0F;
/* 2183 */     this.m33 = 1.0F;
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
/* 2196 */     this.m00 = paramMatrix3f.m00 * paramFloat;
/* 2197 */     this.m01 = paramMatrix3f.m01 * paramFloat;
/* 2198 */     this.m02 = paramMatrix3f.m02 * paramFloat;
/* 2199 */     this.m03 = paramVector3f.x;
/*      */     
/* 2201 */     this.m10 = paramMatrix3f.m10 * paramFloat;
/* 2202 */     this.m11 = paramMatrix3f.m11 * paramFloat;
/* 2203 */     this.m12 = paramMatrix3f.m12 * paramFloat;
/* 2204 */     this.m13 = paramVector3f.y;
/*      */     
/* 2206 */     this.m20 = paramMatrix3f.m20 * paramFloat;
/* 2207 */     this.m21 = paramMatrix3f.m21 * paramFloat;
/* 2208 */     this.m22 = paramMatrix3f.m22 * paramFloat;
/* 2209 */     this.m23 = paramVector3f.z;
/*      */     
/* 2211 */     this.m30 = 0.0F;
/* 2212 */     this.m31 = 0.0F;
/* 2213 */     this.m32 = 0.0F;
/* 2214 */     this.m33 = 1.0F;
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
/*      */   public final void set(Matrix3d paramMatrix3d, Vector3d paramVector3d, double paramDouble) {
/* 2227 */     this.m00 = (float)(paramMatrix3d.m00 * paramDouble);
/* 2228 */     this.m01 = (float)(paramMatrix3d.m01 * paramDouble);
/* 2229 */     this.m02 = (float)(paramMatrix3d.m02 * paramDouble);
/* 2230 */     this.m03 = (float)paramVector3d.x;
/*      */     
/* 2232 */     this.m10 = (float)(paramMatrix3d.m10 * paramDouble);
/* 2233 */     this.m11 = (float)(paramMatrix3d.m11 * paramDouble);
/* 2234 */     this.m12 = (float)(paramMatrix3d.m12 * paramDouble);
/* 2235 */     this.m13 = (float)paramVector3d.y;
/*      */     
/* 2237 */     this.m20 = (float)(paramMatrix3d.m20 * paramDouble);
/* 2238 */     this.m21 = (float)(paramMatrix3d.m21 * paramDouble);
/* 2239 */     this.m22 = (float)(paramMatrix3d.m22 * paramDouble);
/* 2240 */     this.m23 = (float)paramVector3d.z;
/*      */     
/* 2242 */     this.m30 = 0.0F;
/* 2243 */     this.m31 = 0.0F;
/* 2244 */     this.m32 = 0.0F;
/* 2245 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setTranslation(Vector3f paramVector3f) {
/* 2256 */     this.m03 = paramVector3f.x;
/* 2257 */     this.m13 = paramVector3f.y;
/* 2258 */     this.m23 = paramVector3f.z;
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
/*      */   public final void rotX(float paramFloat) {
/* 2271 */     float f1 = (float)Math.sin(paramFloat);
/* 2272 */     float f2 = (float)Math.cos(paramFloat);
/*      */     
/* 2274 */     this.m00 = 1.0F;
/* 2275 */     this.m01 = 0.0F;
/* 2276 */     this.m02 = 0.0F;
/* 2277 */     this.m03 = 0.0F;
/*      */     
/* 2279 */     this.m10 = 0.0F;
/* 2280 */     this.m11 = f2;
/* 2281 */     this.m12 = -f1;
/* 2282 */     this.m13 = 0.0F;
/*      */     
/* 2284 */     this.m20 = 0.0F;
/* 2285 */     this.m21 = f1;
/* 2286 */     this.m22 = f2;
/* 2287 */     this.m23 = 0.0F;
/*      */     
/* 2289 */     this.m30 = 0.0F;
/* 2290 */     this.m31 = 0.0F;
/* 2291 */     this.m32 = 0.0F;
/* 2292 */     this.m33 = 1.0F;
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
/* 2304 */     float f1 = (float)Math.sin(paramFloat);
/* 2305 */     float f2 = (float)Math.cos(paramFloat);
/*      */     
/* 2307 */     this.m00 = f2;
/* 2308 */     this.m01 = 0.0F;
/* 2309 */     this.m02 = f1;
/* 2310 */     this.m03 = 0.0F;
/*      */     
/* 2312 */     this.m10 = 0.0F;
/* 2313 */     this.m11 = 1.0F;
/* 2314 */     this.m12 = 0.0F;
/* 2315 */     this.m13 = 0.0F;
/*      */     
/* 2317 */     this.m20 = -f1;
/* 2318 */     this.m21 = 0.0F;
/* 2319 */     this.m22 = f2;
/* 2320 */     this.m23 = 0.0F;
/*      */     
/* 2322 */     this.m30 = 0.0F;
/* 2323 */     this.m31 = 0.0F;
/* 2324 */     this.m32 = 0.0F;
/* 2325 */     this.m33 = 1.0F;
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
/* 2337 */     float f1 = (float)Math.sin(paramFloat);
/* 2338 */     float f2 = (float)Math.cos(paramFloat);
/*      */     
/* 2340 */     this.m00 = f2;
/* 2341 */     this.m01 = -f1;
/* 2342 */     this.m02 = 0.0F;
/* 2343 */     this.m03 = 0.0F;
/*      */     
/* 2345 */     this.m10 = f1;
/* 2346 */     this.m11 = f2;
/* 2347 */     this.m12 = 0.0F;
/* 2348 */     this.m13 = 0.0F;
/*      */     
/* 2350 */     this.m20 = 0.0F;
/* 2351 */     this.m21 = 0.0F;
/* 2352 */     this.m22 = 1.0F;
/* 2353 */     this.m23 = 0.0F;
/*      */     
/* 2355 */     this.m30 = 0.0F;
/* 2356 */     this.m31 = 0.0F;
/* 2357 */     this.m32 = 0.0F;
/* 2358 */     this.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(float paramFloat) {
/* 2367 */     this.m00 *= paramFloat;
/* 2368 */     this.m01 *= paramFloat;
/* 2369 */     this.m02 *= paramFloat;
/* 2370 */     this.m03 *= paramFloat;
/* 2371 */     this.m10 *= paramFloat;
/* 2372 */     this.m11 *= paramFloat;
/* 2373 */     this.m12 *= paramFloat;
/* 2374 */     this.m13 *= paramFloat;
/* 2375 */     this.m20 *= paramFloat;
/* 2376 */     this.m21 *= paramFloat;
/* 2377 */     this.m22 *= paramFloat;
/* 2378 */     this.m23 *= paramFloat;
/* 2379 */     this.m30 *= paramFloat;
/* 2380 */     this.m31 *= paramFloat;
/* 2381 */     this.m32 *= paramFloat;
/* 2382 */     this.m33 *= paramFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(float paramFloat, Matrix4f paramMatrix4f) {
/* 2393 */     paramMatrix4f.m00 *= paramFloat;
/* 2394 */     paramMatrix4f.m01 *= paramFloat;
/* 2395 */     paramMatrix4f.m02 *= paramFloat;
/* 2396 */     paramMatrix4f.m03 *= paramFloat;
/* 2397 */     paramMatrix4f.m10 *= paramFloat;
/* 2398 */     paramMatrix4f.m11 *= paramFloat;
/* 2399 */     paramMatrix4f.m12 *= paramFloat;
/* 2400 */     paramMatrix4f.m13 *= paramFloat;
/* 2401 */     paramMatrix4f.m20 *= paramFloat;
/* 2402 */     paramMatrix4f.m21 *= paramFloat;
/* 2403 */     paramMatrix4f.m22 *= paramFloat;
/* 2404 */     paramMatrix4f.m23 *= paramFloat;
/* 2405 */     paramMatrix4f.m30 *= paramFloat;
/* 2406 */     paramMatrix4f.m31 *= paramFloat;
/* 2407 */     paramMatrix4f.m32 *= paramFloat;
/* 2408 */     paramMatrix4f.m33 *= paramFloat;
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
/*      */   public final void mul(Matrix4f paramMatrix4f) {
/* 2423 */     float f1 = this.m00 * paramMatrix4f.m00 + this.m01 * paramMatrix4f.m10 + this.m02 * paramMatrix4f.m20 + this.m03 * paramMatrix4f.m30;
/*      */     
/* 2425 */     float f2 = this.m00 * paramMatrix4f.m01 + this.m01 * paramMatrix4f.m11 + this.m02 * paramMatrix4f.m21 + this.m03 * paramMatrix4f.m31;
/*      */     
/* 2427 */     float f3 = this.m00 * paramMatrix4f.m02 + this.m01 * paramMatrix4f.m12 + this.m02 * paramMatrix4f.m22 + this.m03 * paramMatrix4f.m32;
/*      */     
/* 2429 */     float f4 = this.m00 * paramMatrix4f.m03 + this.m01 * paramMatrix4f.m13 + this.m02 * paramMatrix4f.m23 + this.m03 * paramMatrix4f.m33;
/*      */ 
/*      */     
/* 2432 */     float f5 = this.m10 * paramMatrix4f.m00 + this.m11 * paramMatrix4f.m10 + this.m12 * paramMatrix4f.m20 + this.m13 * paramMatrix4f.m30;
/*      */     
/* 2434 */     float f6 = this.m10 * paramMatrix4f.m01 + this.m11 * paramMatrix4f.m11 + this.m12 * paramMatrix4f.m21 + this.m13 * paramMatrix4f.m31;
/*      */     
/* 2436 */     float f7 = this.m10 * paramMatrix4f.m02 + this.m11 * paramMatrix4f.m12 + this.m12 * paramMatrix4f.m22 + this.m13 * paramMatrix4f.m32;
/*      */     
/* 2438 */     float f8 = this.m10 * paramMatrix4f.m03 + this.m11 * paramMatrix4f.m13 + this.m12 * paramMatrix4f.m23 + this.m13 * paramMatrix4f.m33;
/*      */ 
/*      */     
/* 2441 */     float f9 = this.m20 * paramMatrix4f.m00 + this.m21 * paramMatrix4f.m10 + this.m22 * paramMatrix4f.m20 + this.m23 * paramMatrix4f.m30;
/*      */     
/* 2443 */     float f10 = this.m20 * paramMatrix4f.m01 + this.m21 * paramMatrix4f.m11 + this.m22 * paramMatrix4f.m21 + this.m23 * paramMatrix4f.m31;
/*      */     
/* 2445 */     float f11 = this.m20 * paramMatrix4f.m02 + this.m21 * paramMatrix4f.m12 + this.m22 * paramMatrix4f.m22 + this.m23 * paramMatrix4f.m32;
/*      */     
/* 2447 */     float f12 = this.m20 * paramMatrix4f.m03 + this.m21 * paramMatrix4f.m13 + this.m22 * paramMatrix4f.m23 + this.m23 * paramMatrix4f.m33;
/*      */ 
/*      */     
/* 2450 */     float f13 = this.m30 * paramMatrix4f.m00 + this.m31 * paramMatrix4f.m10 + this.m32 * paramMatrix4f.m20 + this.m33 * paramMatrix4f.m30;
/*      */     
/* 2452 */     float f14 = this.m30 * paramMatrix4f.m01 + this.m31 * paramMatrix4f.m11 + this.m32 * paramMatrix4f.m21 + this.m33 * paramMatrix4f.m31;
/*      */     
/* 2454 */     float f15 = this.m30 * paramMatrix4f.m02 + this.m31 * paramMatrix4f.m12 + this.m32 * paramMatrix4f.m22 + this.m33 * paramMatrix4f.m32;
/*      */     
/* 2456 */     float f16 = this.m30 * paramMatrix4f.m03 + this.m31 * paramMatrix4f.m13 + this.m32 * paramMatrix4f.m23 + this.m33 * paramMatrix4f.m33;
/*      */ 
/*      */     
/* 2459 */     this.m00 = f1; this.m01 = f2; this.m02 = f3; this.m03 = f4;
/* 2460 */     this.m10 = f5; this.m11 = f6; this.m12 = f7; this.m13 = f8;
/* 2461 */     this.m20 = f9; this.m21 = f10; this.m22 = f11; this.m23 = f12;
/* 2462 */     this.m30 = f13; this.m31 = f14; this.m32 = f15; this.m33 = f16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(Matrix4f paramMatrix4f1, Matrix4f paramMatrix4f2) {
/* 2473 */     if (this != paramMatrix4f1 && this != paramMatrix4f2) {
/*      */       
/* 2475 */       this.m00 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m01 * paramMatrix4f2.m10 + paramMatrix4f1.m02 * paramMatrix4f2.m20 + paramMatrix4f1.m03 * paramMatrix4f2.m30;
/*      */       
/* 2477 */       this.m01 = paramMatrix4f1.m00 * paramMatrix4f2.m01 + paramMatrix4f1.m01 * paramMatrix4f2.m11 + paramMatrix4f1.m02 * paramMatrix4f2.m21 + paramMatrix4f1.m03 * paramMatrix4f2.m31;
/*      */       
/* 2479 */       this.m02 = paramMatrix4f1.m00 * paramMatrix4f2.m02 + paramMatrix4f1.m01 * paramMatrix4f2.m12 + paramMatrix4f1.m02 * paramMatrix4f2.m22 + paramMatrix4f1.m03 * paramMatrix4f2.m32;
/*      */       
/* 2481 */       this.m03 = paramMatrix4f1.m00 * paramMatrix4f2.m03 + paramMatrix4f1.m01 * paramMatrix4f2.m13 + paramMatrix4f1.m02 * paramMatrix4f2.m23 + paramMatrix4f1.m03 * paramMatrix4f2.m33;
/*      */ 
/*      */       
/* 2484 */       this.m10 = paramMatrix4f1.m10 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m10 + paramMatrix4f1.m12 * paramMatrix4f2.m20 + paramMatrix4f1.m13 * paramMatrix4f2.m30;
/*      */       
/* 2486 */       this.m11 = paramMatrix4f1.m10 * paramMatrix4f2.m01 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m12 * paramMatrix4f2.m21 + paramMatrix4f1.m13 * paramMatrix4f2.m31;
/*      */       
/* 2488 */       this.m12 = paramMatrix4f1.m10 * paramMatrix4f2.m02 + paramMatrix4f1.m11 * paramMatrix4f2.m12 + paramMatrix4f1.m12 * paramMatrix4f2.m22 + paramMatrix4f1.m13 * paramMatrix4f2.m32;
/*      */       
/* 2490 */       this.m13 = paramMatrix4f1.m10 * paramMatrix4f2.m03 + paramMatrix4f1.m11 * paramMatrix4f2.m13 + paramMatrix4f1.m12 * paramMatrix4f2.m23 + paramMatrix4f1.m13 * paramMatrix4f2.m33;
/*      */ 
/*      */       
/* 2493 */       this.m20 = paramMatrix4f1.m20 * paramMatrix4f2.m00 + paramMatrix4f1.m21 * paramMatrix4f2.m10 + paramMatrix4f1.m22 * paramMatrix4f2.m20 + paramMatrix4f1.m23 * paramMatrix4f2.m30;
/*      */       
/* 2495 */       this.m21 = paramMatrix4f1.m20 * paramMatrix4f2.m01 + paramMatrix4f1.m21 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m21 + paramMatrix4f1.m23 * paramMatrix4f2.m31;
/*      */       
/* 2497 */       this.m22 = paramMatrix4f1.m20 * paramMatrix4f2.m02 + paramMatrix4f1.m21 * paramMatrix4f2.m12 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m23 * paramMatrix4f2.m32;
/*      */       
/* 2499 */       this.m23 = paramMatrix4f1.m20 * paramMatrix4f2.m03 + paramMatrix4f1.m21 * paramMatrix4f2.m13 + paramMatrix4f1.m22 * paramMatrix4f2.m23 + paramMatrix4f1.m23 * paramMatrix4f2.m33;
/*      */ 
/*      */       
/* 2502 */       this.m30 = paramMatrix4f1.m30 * paramMatrix4f2.m00 + paramMatrix4f1.m31 * paramMatrix4f2.m10 + paramMatrix4f1.m32 * paramMatrix4f2.m20 + paramMatrix4f1.m33 * paramMatrix4f2.m30;
/*      */       
/* 2504 */       this.m31 = paramMatrix4f1.m30 * paramMatrix4f2.m01 + paramMatrix4f1.m31 * paramMatrix4f2.m11 + paramMatrix4f1.m32 * paramMatrix4f2.m21 + paramMatrix4f1.m33 * paramMatrix4f2.m31;
/*      */       
/* 2506 */       this.m32 = paramMatrix4f1.m30 * paramMatrix4f2.m02 + paramMatrix4f1.m31 * paramMatrix4f2.m12 + paramMatrix4f1.m32 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m32;
/*      */       
/* 2508 */       this.m33 = paramMatrix4f1.m30 * paramMatrix4f2.m03 + paramMatrix4f1.m31 * paramMatrix4f2.m13 + paramMatrix4f1.m32 * paramMatrix4f2.m23 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2515 */       float f1 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m01 * paramMatrix4f2.m10 + paramMatrix4f1.m02 * paramMatrix4f2.m20 + paramMatrix4f1.m03 * paramMatrix4f2.m30;
/* 2516 */       float f2 = paramMatrix4f1.m00 * paramMatrix4f2.m01 + paramMatrix4f1.m01 * paramMatrix4f2.m11 + paramMatrix4f1.m02 * paramMatrix4f2.m21 + paramMatrix4f1.m03 * paramMatrix4f2.m31;
/* 2517 */       float f3 = paramMatrix4f1.m00 * paramMatrix4f2.m02 + paramMatrix4f1.m01 * paramMatrix4f2.m12 + paramMatrix4f1.m02 * paramMatrix4f2.m22 + paramMatrix4f1.m03 * paramMatrix4f2.m32;
/* 2518 */       float f4 = paramMatrix4f1.m00 * paramMatrix4f2.m03 + paramMatrix4f1.m01 * paramMatrix4f2.m13 + paramMatrix4f1.m02 * paramMatrix4f2.m23 + paramMatrix4f1.m03 * paramMatrix4f2.m33;
/*      */       
/* 2520 */       float f5 = paramMatrix4f1.m10 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m10 + paramMatrix4f1.m12 * paramMatrix4f2.m20 + paramMatrix4f1.m13 * paramMatrix4f2.m30;
/* 2521 */       float f6 = paramMatrix4f1.m10 * paramMatrix4f2.m01 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m12 * paramMatrix4f2.m21 + paramMatrix4f1.m13 * paramMatrix4f2.m31;
/* 2522 */       float f7 = paramMatrix4f1.m10 * paramMatrix4f2.m02 + paramMatrix4f1.m11 * paramMatrix4f2.m12 + paramMatrix4f1.m12 * paramMatrix4f2.m22 + paramMatrix4f1.m13 * paramMatrix4f2.m32;
/* 2523 */       float f8 = paramMatrix4f1.m10 * paramMatrix4f2.m03 + paramMatrix4f1.m11 * paramMatrix4f2.m13 + paramMatrix4f1.m12 * paramMatrix4f2.m23 + paramMatrix4f1.m13 * paramMatrix4f2.m33;
/*      */       
/* 2525 */       float f9 = paramMatrix4f1.m20 * paramMatrix4f2.m00 + paramMatrix4f1.m21 * paramMatrix4f2.m10 + paramMatrix4f1.m22 * paramMatrix4f2.m20 + paramMatrix4f1.m23 * paramMatrix4f2.m30;
/* 2526 */       float f10 = paramMatrix4f1.m20 * paramMatrix4f2.m01 + paramMatrix4f1.m21 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m21 + paramMatrix4f1.m23 * paramMatrix4f2.m31;
/* 2527 */       float f11 = paramMatrix4f1.m20 * paramMatrix4f2.m02 + paramMatrix4f1.m21 * paramMatrix4f2.m12 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m23 * paramMatrix4f2.m32;
/* 2528 */       float f12 = paramMatrix4f1.m20 * paramMatrix4f2.m03 + paramMatrix4f1.m21 * paramMatrix4f2.m13 + paramMatrix4f1.m22 * paramMatrix4f2.m23 + paramMatrix4f1.m23 * paramMatrix4f2.m33;
/*      */       
/* 2530 */       float f13 = paramMatrix4f1.m30 * paramMatrix4f2.m00 + paramMatrix4f1.m31 * paramMatrix4f2.m10 + paramMatrix4f1.m32 * paramMatrix4f2.m20 + paramMatrix4f1.m33 * paramMatrix4f2.m30;
/* 2531 */       float f14 = paramMatrix4f1.m30 * paramMatrix4f2.m01 + paramMatrix4f1.m31 * paramMatrix4f2.m11 + paramMatrix4f1.m32 * paramMatrix4f2.m21 + paramMatrix4f1.m33 * paramMatrix4f2.m31;
/* 2532 */       float f15 = paramMatrix4f1.m30 * paramMatrix4f2.m02 + paramMatrix4f1.m31 * paramMatrix4f2.m12 + paramMatrix4f1.m32 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m32;
/* 2533 */       float f16 = paramMatrix4f1.m30 * paramMatrix4f2.m03 + paramMatrix4f1.m31 * paramMatrix4f2.m13 + paramMatrix4f1.m32 * paramMatrix4f2.m23 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */       
/* 2535 */       this.m00 = f1; this.m01 = f2; this.m02 = f3; this.m03 = f4;
/* 2536 */       this.m10 = f5; this.m11 = f6; this.m12 = f7; this.m13 = f8;
/* 2537 */       this.m20 = f9; this.m21 = f10; this.m22 = f11; this.m23 = f12;
/* 2538 */       this.m30 = f13; this.m31 = f14; this.m32 = f15; this.m33 = f16;
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
/*      */   public final void mulTransposeBoth(Matrix4f paramMatrix4f1, Matrix4f paramMatrix4f2) {
/* 2550 */     if (this != paramMatrix4f1 && this != paramMatrix4f2) {
/* 2551 */       this.m00 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m10 * paramMatrix4f2.m01 + paramMatrix4f1.m20 * paramMatrix4f2.m02 + paramMatrix4f1.m30 * paramMatrix4f2.m03;
/* 2552 */       this.m01 = paramMatrix4f1.m00 * paramMatrix4f2.m10 + paramMatrix4f1.m10 * paramMatrix4f2.m11 + paramMatrix4f1.m20 * paramMatrix4f2.m12 + paramMatrix4f1.m30 * paramMatrix4f2.m13;
/* 2553 */       this.m02 = paramMatrix4f1.m00 * paramMatrix4f2.m20 + paramMatrix4f1.m10 * paramMatrix4f2.m21 + paramMatrix4f1.m20 * paramMatrix4f2.m22 + paramMatrix4f1.m30 * paramMatrix4f2.m23;
/* 2554 */       this.m03 = paramMatrix4f1.m00 * paramMatrix4f2.m30 + paramMatrix4f1.m10 * paramMatrix4f2.m31 + paramMatrix4f1.m20 * paramMatrix4f2.m32 + paramMatrix4f1.m30 * paramMatrix4f2.m33;
/*      */       
/* 2556 */       this.m10 = paramMatrix4f1.m01 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m01 + paramMatrix4f1.m21 * paramMatrix4f2.m02 + paramMatrix4f1.m31 * paramMatrix4f2.m03;
/* 2557 */       this.m11 = paramMatrix4f1.m01 * paramMatrix4f2.m10 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m21 * paramMatrix4f2.m12 + paramMatrix4f1.m31 * paramMatrix4f2.m13;
/* 2558 */       this.m12 = paramMatrix4f1.m01 * paramMatrix4f2.m20 + paramMatrix4f1.m11 * paramMatrix4f2.m21 + paramMatrix4f1.m21 * paramMatrix4f2.m22 + paramMatrix4f1.m31 * paramMatrix4f2.m23;
/* 2559 */       this.m13 = paramMatrix4f1.m01 * paramMatrix4f2.m30 + paramMatrix4f1.m11 * paramMatrix4f2.m31 + paramMatrix4f1.m21 * paramMatrix4f2.m32 + paramMatrix4f1.m31 * paramMatrix4f2.m33;
/*      */       
/* 2561 */       this.m20 = paramMatrix4f1.m02 * paramMatrix4f2.m00 + paramMatrix4f1.m12 * paramMatrix4f2.m01 + paramMatrix4f1.m22 * paramMatrix4f2.m02 + paramMatrix4f1.m32 * paramMatrix4f2.m03;
/* 2562 */       this.m21 = paramMatrix4f1.m02 * paramMatrix4f2.m10 + paramMatrix4f1.m12 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m12 + paramMatrix4f1.m32 * paramMatrix4f2.m13;
/* 2563 */       this.m22 = paramMatrix4f1.m02 * paramMatrix4f2.m20 + paramMatrix4f1.m12 * paramMatrix4f2.m21 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m32 * paramMatrix4f2.m23;
/* 2564 */       this.m23 = paramMatrix4f1.m02 * paramMatrix4f2.m30 + paramMatrix4f1.m12 * paramMatrix4f2.m31 + paramMatrix4f1.m22 * paramMatrix4f2.m32 + paramMatrix4f1.m32 * paramMatrix4f2.m33;
/*      */       
/* 2566 */       this.m30 = paramMatrix4f1.m03 * paramMatrix4f2.m00 + paramMatrix4f1.m13 * paramMatrix4f2.m01 + paramMatrix4f1.m23 * paramMatrix4f2.m02 + paramMatrix4f1.m33 * paramMatrix4f2.m03;
/* 2567 */       this.m31 = paramMatrix4f1.m03 * paramMatrix4f2.m10 + paramMatrix4f1.m13 * paramMatrix4f2.m11 + paramMatrix4f1.m23 * paramMatrix4f2.m12 + paramMatrix4f1.m33 * paramMatrix4f2.m13;
/* 2568 */       this.m32 = paramMatrix4f1.m03 * paramMatrix4f2.m20 + paramMatrix4f1.m13 * paramMatrix4f2.m21 + paramMatrix4f1.m23 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m23;
/* 2569 */       this.m33 = paramMatrix4f1.m03 * paramMatrix4f2.m30 + paramMatrix4f1.m13 * paramMatrix4f2.m31 + paramMatrix4f1.m23 * paramMatrix4f2.m32 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2576 */       float f1 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m10 * paramMatrix4f2.m01 + paramMatrix4f1.m20 * paramMatrix4f2.m02 + paramMatrix4f1.m30 * paramMatrix4f2.m03;
/* 2577 */       float f2 = paramMatrix4f1.m00 * paramMatrix4f2.m10 + paramMatrix4f1.m10 * paramMatrix4f2.m11 + paramMatrix4f1.m20 * paramMatrix4f2.m12 + paramMatrix4f1.m30 * paramMatrix4f2.m13;
/* 2578 */       float f3 = paramMatrix4f1.m00 * paramMatrix4f2.m20 + paramMatrix4f1.m10 * paramMatrix4f2.m21 + paramMatrix4f1.m20 * paramMatrix4f2.m22 + paramMatrix4f1.m30 * paramMatrix4f2.m23;
/* 2579 */       float f4 = paramMatrix4f1.m00 * paramMatrix4f2.m30 + paramMatrix4f1.m10 * paramMatrix4f2.m31 + paramMatrix4f1.m20 * paramMatrix4f2.m32 + paramMatrix4f1.m30 * paramMatrix4f2.m33;
/*      */       
/* 2581 */       float f5 = paramMatrix4f1.m01 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m01 + paramMatrix4f1.m21 * paramMatrix4f2.m02 + paramMatrix4f1.m31 * paramMatrix4f2.m03;
/* 2582 */       float f6 = paramMatrix4f1.m01 * paramMatrix4f2.m10 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m21 * paramMatrix4f2.m12 + paramMatrix4f1.m31 * paramMatrix4f2.m13;
/* 2583 */       float f7 = paramMatrix4f1.m01 * paramMatrix4f2.m20 + paramMatrix4f1.m11 * paramMatrix4f2.m21 + paramMatrix4f1.m21 * paramMatrix4f2.m22 + paramMatrix4f1.m31 * paramMatrix4f2.m23;
/* 2584 */       float f8 = paramMatrix4f1.m01 * paramMatrix4f2.m30 + paramMatrix4f1.m11 * paramMatrix4f2.m31 + paramMatrix4f1.m21 * paramMatrix4f2.m32 + paramMatrix4f1.m31 * paramMatrix4f2.m33;
/*      */       
/* 2586 */       float f9 = paramMatrix4f1.m02 * paramMatrix4f2.m00 + paramMatrix4f1.m12 * paramMatrix4f2.m01 + paramMatrix4f1.m22 * paramMatrix4f2.m02 + paramMatrix4f1.m32 * paramMatrix4f2.m03;
/* 2587 */       float f10 = paramMatrix4f1.m02 * paramMatrix4f2.m10 + paramMatrix4f1.m12 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m12 + paramMatrix4f1.m32 * paramMatrix4f2.m13;
/* 2588 */       float f11 = paramMatrix4f1.m02 * paramMatrix4f2.m20 + paramMatrix4f1.m12 * paramMatrix4f2.m21 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m32 * paramMatrix4f2.m23;
/* 2589 */       float f12 = paramMatrix4f1.m02 * paramMatrix4f2.m30 + paramMatrix4f1.m12 * paramMatrix4f2.m31 + paramMatrix4f1.m22 * paramMatrix4f2.m32 + paramMatrix4f1.m32 * paramMatrix4f2.m33;
/*      */       
/* 2591 */       float f13 = paramMatrix4f1.m03 * paramMatrix4f2.m00 + paramMatrix4f1.m13 * paramMatrix4f2.m01 + paramMatrix4f1.m23 * paramMatrix4f2.m02 + paramMatrix4f1.m33 * paramMatrix4f2.m03;
/* 2592 */       float f14 = paramMatrix4f1.m03 * paramMatrix4f2.m10 + paramMatrix4f1.m13 * paramMatrix4f2.m11 + paramMatrix4f1.m23 * paramMatrix4f2.m12 + paramMatrix4f1.m33 * paramMatrix4f2.m13;
/* 2593 */       float f15 = paramMatrix4f1.m03 * paramMatrix4f2.m20 + paramMatrix4f1.m13 * paramMatrix4f2.m21 + paramMatrix4f1.m23 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m23;
/* 2594 */       float f16 = paramMatrix4f1.m03 * paramMatrix4f2.m30 + paramMatrix4f1.m13 * paramMatrix4f2.m31 + paramMatrix4f1.m23 * paramMatrix4f2.m32 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */       
/* 2596 */       this.m00 = f1; this.m01 = f2; this.m02 = f3; this.m03 = f4;
/* 2597 */       this.m10 = f5; this.m11 = f6; this.m12 = f7; this.m13 = f8;
/* 2598 */       this.m20 = f9; this.m21 = f10; this.m22 = f11; this.m23 = f12;
/* 2599 */       this.m30 = f13; this.m31 = f14; this.m32 = f15; this.m33 = f16;
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
/*      */   public final void mulTransposeRight(Matrix4f paramMatrix4f1, Matrix4f paramMatrix4f2) {
/* 2612 */     if (this != paramMatrix4f1 && this != paramMatrix4f2) {
/* 2613 */       this.m00 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m01 * paramMatrix4f2.m01 + paramMatrix4f1.m02 * paramMatrix4f2.m02 + paramMatrix4f1.m03 * paramMatrix4f2.m03;
/* 2614 */       this.m01 = paramMatrix4f1.m00 * paramMatrix4f2.m10 + paramMatrix4f1.m01 * paramMatrix4f2.m11 + paramMatrix4f1.m02 * paramMatrix4f2.m12 + paramMatrix4f1.m03 * paramMatrix4f2.m13;
/* 2615 */       this.m02 = paramMatrix4f1.m00 * paramMatrix4f2.m20 + paramMatrix4f1.m01 * paramMatrix4f2.m21 + paramMatrix4f1.m02 * paramMatrix4f2.m22 + paramMatrix4f1.m03 * paramMatrix4f2.m23;
/* 2616 */       this.m03 = paramMatrix4f1.m00 * paramMatrix4f2.m30 + paramMatrix4f1.m01 * paramMatrix4f2.m31 + paramMatrix4f1.m02 * paramMatrix4f2.m32 + paramMatrix4f1.m03 * paramMatrix4f2.m33;
/*      */       
/* 2618 */       this.m10 = paramMatrix4f1.m10 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m01 + paramMatrix4f1.m12 * paramMatrix4f2.m02 + paramMatrix4f1.m13 * paramMatrix4f2.m03;
/* 2619 */       this.m11 = paramMatrix4f1.m10 * paramMatrix4f2.m10 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m12 * paramMatrix4f2.m12 + paramMatrix4f1.m13 * paramMatrix4f2.m13;
/* 2620 */       this.m12 = paramMatrix4f1.m10 * paramMatrix4f2.m20 + paramMatrix4f1.m11 * paramMatrix4f2.m21 + paramMatrix4f1.m12 * paramMatrix4f2.m22 + paramMatrix4f1.m13 * paramMatrix4f2.m23;
/* 2621 */       this.m13 = paramMatrix4f1.m10 * paramMatrix4f2.m30 + paramMatrix4f1.m11 * paramMatrix4f2.m31 + paramMatrix4f1.m12 * paramMatrix4f2.m32 + paramMatrix4f1.m13 * paramMatrix4f2.m33;
/*      */       
/* 2623 */       this.m20 = paramMatrix4f1.m20 * paramMatrix4f2.m00 + paramMatrix4f1.m21 * paramMatrix4f2.m01 + paramMatrix4f1.m22 * paramMatrix4f2.m02 + paramMatrix4f1.m23 * paramMatrix4f2.m03;
/* 2624 */       this.m21 = paramMatrix4f1.m20 * paramMatrix4f2.m10 + paramMatrix4f1.m21 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m12 + paramMatrix4f1.m23 * paramMatrix4f2.m13;
/* 2625 */       this.m22 = paramMatrix4f1.m20 * paramMatrix4f2.m20 + paramMatrix4f1.m21 * paramMatrix4f2.m21 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m23 * paramMatrix4f2.m23;
/* 2626 */       this.m23 = paramMatrix4f1.m20 * paramMatrix4f2.m30 + paramMatrix4f1.m21 * paramMatrix4f2.m31 + paramMatrix4f1.m22 * paramMatrix4f2.m32 + paramMatrix4f1.m23 * paramMatrix4f2.m33;
/*      */       
/* 2628 */       this.m30 = paramMatrix4f1.m30 * paramMatrix4f2.m00 + paramMatrix4f1.m31 * paramMatrix4f2.m01 + paramMatrix4f1.m32 * paramMatrix4f2.m02 + paramMatrix4f1.m33 * paramMatrix4f2.m03;
/* 2629 */       this.m31 = paramMatrix4f1.m30 * paramMatrix4f2.m10 + paramMatrix4f1.m31 * paramMatrix4f2.m11 + paramMatrix4f1.m32 * paramMatrix4f2.m12 + paramMatrix4f1.m33 * paramMatrix4f2.m13;
/* 2630 */       this.m32 = paramMatrix4f1.m30 * paramMatrix4f2.m20 + paramMatrix4f1.m31 * paramMatrix4f2.m21 + paramMatrix4f1.m32 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m23;
/* 2631 */       this.m33 = paramMatrix4f1.m30 * paramMatrix4f2.m30 + paramMatrix4f1.m31 * paramMatrix4f2.m31 + paramMatrix4f1.m32 * paramMatrix4f2.m32 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2638 */       float f1 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m01 * paramMatrix4f2.m01 + paramMatrix4f1.m02 * paramMatrix4f2.m02 + paramMatrix4f1.m03 * paramMatrix4f2.m03;
/* 2639 */       float f2 = paramMatrix4f1.m00 * paramMatrix4f2.m10 + paramMatrix4f1.m01 * paramMatrix4f2.m11 + paramMatrix4f1.m02 * paramMatrix4f2.m12 + paramMatrix4f1.m03 * paramMatrix4f2.m13;
/* 2640 */       float f3 = paramMatrix4f1.m00 * paramMatrix4f2.m20 + paramMatrix4f1.m01 * paramMatrix4f2.m21 + paramMatrix4f1.m02 * paramMatrix4f2.m22 + paramMatrix4f1.m03 * paramMatrix4f2.m23;
/* 2641 */       float f4 = paramMatrix4f1.m00 * paramMatrix4f2.m30 + paramMatrix4f1.m01 * paramMatrix4f2.m31 + paramMatrix4f1.m02 * paramMatrix4f2.m32 + paramMatrix4f1.m03 * paramMatrix4f2.m33;
/*      */       
/* 2643 */       float f5 = paramMatrix4f1.m10 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m01 + paramMatrix4f1.m12 * paramMatrix4f2.m02 + paramMatrix4f1.m13 * paramMatrix4f2.m03;
/* 2644 */       float f6 = paramMatrix4f1.m10 * paramMatrix4f2.m10 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m12 * paramMatrix4f2.m12 + paramMatrix4f1.m13 * paramMatrix4f2.m13;
/* 2645 */       float f7 = paramMatrix4f1.m10 * paramMatrix4f2.m20 + paramMatrix4f1.m11 * paramMatrix4f2.m21 + paramMatrix4f1.m12 * paramMatrix4f2.m22 + paramMatrix4f1.m13 * paramMatrix4f2.m23;
/* 2646 */       float f8 = paramMatrix4f1.m10 * paramMatrix4f2.m30 + paramMatrix4f1.m11 * paramMatrix4f2.m31 + paramMatrix4f1.m12 * paramMatrix4f2.m32 + paramMatrix4f1.m13 * paramMatrix4f2.m33;
/*      */       
/* 2648 */       float f9 = paramMatrix4f1.m20 * paramMatrix4f2.m00 + paramMatrix4f1.m21 * paramMatrix4f2.m01 + paramMatrix4f1.m22 * paramMatrix4f2.m02 + paramMatrix4f1.m23 * paramMatrix4f2.m03;
/* 2649 */       float f10 = paramMatrix4f1.m20 * paramMatrix4f2.m10 + paramMatrix4f1.m21 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m12 + paramMatrix4f1.m23 * paramMatrix4f2.m13;
/* 2650 */       float f11 = paramMatrix4f1.m20 * paramMatrix4f2.m20 + paramMatrix4f1.m21 * paramMatrix4f2.m21 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m23 * paramMatrix4f2.m23;
/* 2651 */       float f12 = paramMatrix4f1.m20 * paramMatrix4f2.m30 + paramMatrix4f1.m21 * paramMatrix4f2.m31 + paramMatrix4f1.m22 * paramMatrix4f2.m32 + paramMatrix4f1.m23 * paramMatrix4f2.m33;
/*      */       
/* 2653 */       float f13 = paramMatrix4f1.m30 * paramMatrix4f2.m00 + paramMatrix4f1.m31 * paramMatrix4f2.m01 + paramMatrix4f1.m32 * paramMatrix4f2.m02 + paramMatrix4f1.m33 * paramMatrix4f2.m03;
/* 2654 */       float f14 = paramMatrix4f1.m30 * paramMatrix4f2.m10 + paramMatrix4f1.m31 * paramMatrix4f2.m11 + paramMatrix4f1.m32 * paramMatrix4f2.m12 + paramMatrix4f1.m33 * paramMatrix4f2.m13;
/* 2655 */       float f15 = paramMatrix4f1.m30 * paramMatrix4f2.m20 + paramMatrix4f1.m31 * paramMatrix4f2.m21 + paramMatrix4f1.m32 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m23;
/* 2656 */       float f16 = paramMatrix4f1.m30 * paramMatrix4f2.m30 + paramMatrix4f1.m31 * paramMatrix4f2.m31 + paramMatrix4f1.m32 * paramMatrix4f2.m32 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */       
/* 2658 */       this.m00 = f1; this.m01 = f2; this.m02 = f3; this.m03 = f4;
/* 2659 */       this.m10 = f5; this.m11 = f6; this.m12 = f7; this.m13 = f8;
/* 2660 */       this.m20 = f9; this.m21 = f10; this.m22 = f11; this.m23 = f12;
/* 2661 */       this.m30 = f13; this.m31 = f14; this.m32 = f15; this.m33 = f16;
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
/*      */   public final void mulTransposeLeft(Matrix4f paramMatrix4f1, Matrix4f paramMatrix4f2) {
/* 2675 */     if (this != paramMatrix4f1 && this != paramMatrix4f2) {
/* 2676 */       this.m00 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m10 * paramMatrix4f2.m10 + paramMatrix4f1.m20 * paramMatrix4f2.m20 + paramMatrix4f1.m30 * paramMatrix4f2.m30;
/* 2677 */       this.m01 = paramMatrix4f1.m00 * paramMatrix4f2.m01 + paramMatrix4f1.m10 * paramMatrix4f2.m11 + paramMatrix4f1.m20 * paramMatrix4f2.m21 + paramMatrix4f1.m30 * paramMatrix4f2.m31;
/* 2678 */       this.m02 = paramMatrix4f1.m00 * paramMatrix4f2.m02 + paramMatrix4f1.m10 * paramMatrix4f2.m12 + paramMatrix4f1.m20 * paramMatrix4f2.m22 + paramMatrix4f1.m30 * paramMatrix4f2.m32;
/* 2679 */       this.m03 = paramMatrix4f1.m00 * paramMatrix4f2.m03 + paramMatrix4f1.m10 * paramMatrix4f2.m13 + paramMatrix4f1.m20 * paramMatrix4f2.m23 + paramMatrix4f1.m30 * paramMatrix4f2.m33;
/*      */       
/* 2681 */       this.m10 = paramMatrix4f1.m01 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m10 + paramMatrix4f1.m21 * paramMatrix4f2.m20 + paramMatrix4f1.m31 * paramMatrix4f2.m30;
/* 2682 */       this.m11 = paramMatrix4f1.m01 * paramMatrix4f2.m01 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m21 * paramMatrix4f2.m21 + paramMatrix4f1.m31 * paramMatrix4f2.m31;
/* 2683 */       this.m12 = paramMatrix4f1.m01 * paramMatrix4f2.m02 + paramMatrix4f1.m11 * paramMatrix4f2.m12 + paramMatrix4f1.m21 * paramMatrix4f2.m22 + paramMatrix4f1.m31 * paramMatrix4f2.m32;
/* 2684 */       this.m13 = paramMatrix4f1.m01 * paramMatrix4f2.m03 + paramMatrix4f1.m11 * paramMatrix4f2.m13 + paramMatrix4f1.m21 * paramMatrix4f2.m23 + paramMatrix4f1.m31 * paramMatrix4f2.m33;
/*      */       
/* 2686 */       this.m20 = paramMatrix4f1.m02 * paramMatrix4f2.m00 + paramMatrix4f1.m12 * paramMatrix4f2.m10 + paramMatrix4f1.m22 * paramMatrix4f2.m20 + paramMatrix4f1.m32 * paramMatrix4f2.m30;
/* 2687 */       this.m21 = paramMatrix4f1.m02 * paramMatrix4f2.m01 + paramMatrix4f1.m12 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m21 + paramMatrix4f1.m32 * paramMatrix4f2.m31;
/* 2688 */       this.m22 = paramMatrix4f1.m02 * paramMatrix4f2.m02 + paramMatrix4f1.m12 * paramMatrix4f2.m12 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m32 * paramMatrix4f2.m32;
/* 2689 */       this.m23 = paramMatrix4f1.m02 * paramMatrix4f2.m03 + paramMatrix4f1.m12 * paramMatrix4f2.m13 + paramMatrix4f1.m22 * paramMatrix4f2.m23 + paramMatrix4f1.m32 * paramMatrix4f2.m33;
/*      */       
/* 2691 */       this.m30 = paramMatrix4f1.m03 * paramMatrix4f2.m00 + paramMatrix4f1.m13 * paramMatrix4f2.m10 + paramMatrix4f1.m23 * paramMatrix4f2.m20 + paramMatrix4f1.m33 * paramMatrix4f2.m30;
/* 2692 */       this.m31 = paramMatrix4f1.m03 * paramMatrix4f2.m01 + paramMatrix4f1.m13 * paramMatrix4f2.m11 + paramMatrix4f1.m23 * paramMatrix4f2.m21 + paramMatrix4f1.m33 * paramMatrix4f2.m31;
/* 2693 */       this.m32 = paramMatrix4f1.m03 * paramMatrix4f2.m02 + paramMatrix4f1.m13 * paramMatrix4f2.m12 + paramMatrix4f1.m23 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m32;
/* 2694 */       this.m33 = paramMatrix4f1.m03 * paramMatrix4f2.m03 + paramMatrix4f1.m13 * paramMatrix4f2.m13 + paramMatrix4f1.m23 * paramMatrix4f2.m23 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 2703 */       float f1 = paramMatrix4f1.m00 * paramMatrix4f2.m00 + paramMatrix4f1.m10 * paramMatrix4f2.m10 + paramMatrix4f1.m20 * paramMatrix4f2.m20 + paramMatrix4f1.m30 * paramMatrix4f2.m30;
/* 2704 */       float f2 = paramMatrix4f1.m00 * paramMatrix4f2.m01 + paramMatrix4f1.m10 * paramMatrix4f2.m11 + paramMatrix4f1.m20 * paramMatrix4f2.m21 + paramMatrix4f1.m30 * paramMatrix4f2.m31;
/* 2705 */       float f3 = paramMatrix4f1.m00 * paramMatrix4f2.m02 + paramMatrix4f1.m10 * paramMatrix4f2.m12 + paramMatrix4f1.m20 * paramMatrix4f2.m22 + paramMatrix4f1.m30 * paramMatrix4f2.m32;
/* 2706 */       float f4 = paramMatrix4f1.m00 * paramMatrix4f2.m03 + paramMatrix4f1.m10 * paramMatrix4f2.m13 + paramMatrix4f1.m20 * paramMatrix4f2.m23 + paramMatrix4f1.m30 * paramMatrix4f2.m33;
/*      */       
/* 2708 */       float f5 = paramMatrix4f1.m01 * paramMatrix4f2.m00 + paramMatrix4f1.m11 * paramMatrix4f2.m10 + paramMatrix4f1.m21 * paramMatrix4f2.m20 + paramMatrix4f1.m31 * paramMatrix4f2.m30;
/* 2709 */       float f6 = paramMatrix4f1.m01 * paramMatrix4f2.m01 + paramMatrix4f1.m11 * paramMatrix4f2.m11 + paramMatrix4f1.m21 * paramMatrix4f2.m21 + paramMatrix4f1.m31 * paramMatrix4f2.m31;
/* 2710 */       float f7 = paramMatrix4f1.m01 * paramMatrix4f2.m02 + paramMatrix4f1.m11 * paramMatrix4f2.m12 + paramMatrix4f1.m21 * paramMatrix4f2.m22 + paramMatrix4f1.m31 * paramMatrix4f2.m32;
/* 2711 */       float f8 = paramMatrix4f1.m01 * paramMatrix4f2.m03 + paramMatrix4f1.m11 * paramMatrix4f2.m13 + paramMatrix4f1.m21 * paramMatrix4f2.m23 + paramMatrix4f1.m31 * paramMatrix4f2.m33;
/*      */       
/* 2713 */       float f9 = paramMatrix4f1.m02 * paramMatrix4f2.m00 + paramMatrix4f1.m12 * paramMatrix4f2.m10 + paramMatrix4f1.m22 * paramMatrix4f2.m20 + paramMatrix4f1.m32 * paramMatrix4f2.m30;
/* 2714 */       float f10 = paramMatrix4f1.m02 * paramMatrix4f2.m01 + paramMatrix4f1.m12 * paramMatrix4f2.m11 + paramMatrix4f1.m22 * paramMatrix4f2.m21 + paramMatrix4f1.m32 * paramMatrix4f2.m31;
/* 2715 */       float f11 = paramMatrix4f1.m02 * paramMatrix4f2.m02 + paramMatrix4f1.m12 * paramMatrix4f2.m12 + paramMatrix4f1.m22 * paramMatrix4f2.m22 + paramMatrix4f1.m32 * paramMatrix4f2.m32;
/* 2716 */       float f12 = paramMatrix4f1.m02 * paramMatrix4f2.m03 + paramMatrix4f1.m12 * paramMatrix4f2.m13 + paramMatrix4f1.m22 * paramMatrix4f2.m23 + paramMatrix4f1.m32 * paramMatrix4f2.m33;
/*      */       
/* 2718 */       float f13 = paramMatrix4f1.m03 * paramMatrix4f2.m00 + paramMatrix4f1.m13 * paramMatrix4f2.m10 + paramMatrix4f1.m23 * paramMatrix4f2.m20 + paramMatrix4f1.m33 * paramMatrix4f2.m30;
/* 2719 */       float f14 = paramMatrix4f1.m03 * paramMatrix4f2.m01 + paramMatrix4f1.m13 * paramMatrix4f2.m11 + paramMatrix4f1.m23 * paramMatrix4f2.m21 + paramMatrix4f1.m33 * paramMatrix4f2.m31;
/* 2720 */       float f15 = paramMatrix4f1.m03 * paramMatrix4f2.m02 + paramMatrix4f1.m13 * paramMatrix4f2.m12 + paramMatrix4f1.m23 * paramMatrix4f2.m22 + paramMatrix4f1.m33 * paramMatrix4f2.m32;
/* 2721 */       float f16 = paramMatrix4f1.m03 * paramMatrix4f2.m03 + paramMatrix4f1.m13 * paramMatrix4f2.m13 + paramMatrix4f1.m23 * paramMatrix4f2.m23 + paramMatrix4f1.m33 * paramMatrix4f2.m33;
/*      */       
/* 2723 */       this.m00 = f1; this.m01 = f2; this.m02 = f3; this.m03 = f4;
/* 2724 */       this.m10 = f5; this.m11 = f6; this.m12 = f7; this.m13 = f8;
/* 2725 */       this.m20 = f9; this.m21 = f10; this.m22 = f11; this.m23 = f12;
/* 2726 */       this.m30 = f13; this.m31 = f14; this.m32 = f15; this.m33 = f16;
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
/*      */   public boolean equals(Matrix4f paramMatrix4f) {
/*      */     try {
/* 2741 */       return (this.m00 == paramMatrix4f.m00 && this.m01 == paramMatrix4f.m01 && this.m02 == paramMatrix4f.m02 && this.m03 == paramMatrix4f.m03 && this.m10 == paramMatrix4f.m10 && this.m11 == paramMatrix4f.m11 && this.m12 == paramMatrix4f.m12 && this.m13 == paramMatrix4f.m13 && this.m20 == paramMatrix4f.m20 && this.m21 == paramMatrix4f.m21 && this.m22 == paramMatrix4f.m22 && this.m23 == paramMatrix4f.m23 && this.m30 == paramMatrix4f.m30 && this.m31 == paramMatrix4f.m31 && this.m32 == paramMatrix4f.m32 && this.m33 == paramMatrix4f.m33);
/*      */ 
/*      */     
/*      */     }
/*      */     catch (NullPointerException nullPointerException) {
/*      */ 
/*      */       
/* 2748 */       return false;
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
/* 2762 */     try { Matrix4f matrix4f = (Matrix4f)paramObject;
/* 2763 */       return (this.m00 == matrix4f.m00 && this.m01 == matrix4f.m01 && this.m02 == matrix4f.m02 && this.m03 == matrix4f.m03 && this.m10 == matrix4f.m10 && this.m11 == matrix4f.m11 && this.m12 == matrix4f.m12 && this.m13 == matrix4f.m13 && this.m20 == matrix4f.m20 && this.m21 == matrix4f.m21 && this.m22 == matrix4f.m22 && this.m23 == matrix4f.m23 && this.m30 == matrix4f.m30 && this.m31 == matrix4f.m31 && this.m32 == matrix4f.m32 && this.m33 == matrix4f.m33);
/*      */       
/*      */        }
/*      */     
/*      */     catch (ClassCastException classCastException)
/*      */     
/*      */     { 
/* 2770 */       return false; }
/* 2771 */     catch (NullPointerException nullPointerException) { return false; }
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
/*      */   public boolean epsilonEquals(Matrix4f paramMatrix4f, float paramFloat) {
/* 2786 */     boolean bool = true;
/*      */     
/* 2788 */     if (Math.abs(this.m00 - paramMatrix4f.m00) > paramFloat) bool = false; 
/* 2789 */     if (Math.abs(this.m01 - paramMatrix4f.m01) > paramFloat) bool = false; 
/* 2790 */     if (Math.abs(this.m02 - paramMatrix4f.m02) > paramFloat) bool = false; 
/* 2791 */     if (Math.abs(this.m03 - paramMatrix4f.m03) > paramFloat) bool = false;
/*      */     
/* 2793 */     if (Math.abs(this.m10 - paramMatrix4f.m10) > paramFloat) bool = false; 
/* 2794 */     if (Math.abs(this.m11 - paramMatrix4f.m11) > paramFloat) bool = false; 
/* 2795 */     if (Math.abs(this.m12 - paramMatrix4f.m12) > paramFloat) bool = false; 
/* 2796 */     if (Math.abs(this.m13 - paramMatrix4f.m13) > paramFloat) bool = false;
/*      */     
/* 2798 */     if (Math.abs(this.m20 - paramMatrix4f.m20) > paramFloat) bool = false; 
/* 2799 */     if (Math.abs(this.m21 - paramMatrix4f.m21) > paramFloat) bool = false; 
/* 2800 */     if (Math.abs(this.m22 - paramMatrix4f.m22) > paramFloat) bool = false; 
/* 2801 */     if (Math.abs(this.m23 - paramMatrix4f.m23) > paramFloat) bool = false;
/*      */     
/* 2803 */     if (Math.abs(this.m30 - paramMatrix4f.m30) > paramFloat) bool = false; 
/* 2804 */     if (Math.abs(this.m31 - paramMatrix4f.m31) > paramFloat) bool = false; 
/* 2805 */     if (Math.abs(this.m32 - paramMatrix4f.m32) > paramFloat) bool = false; 
/* 2806 */     if (Math.abs(this.m33 - paramMatrix4f.m33) > paramFloat) bool = false;
/*      */     
/* 2808 */     return bool;
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
/* 2822 */     long l = 1L;
/* 2823 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m00);
/* 2824 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m01);
/* 2825 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m02);
/* 2826 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m03);
/* 2827 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m10);
/* 2828 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m11);
/* 2829 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m12);
/* 2830 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m13);
/* 2831 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m20);
/* 2832 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m21);
/* 2833 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m22);
/* 2834 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m23);
/* 2835 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m30);
/* 2836 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m31);
/* 2837 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m32);
/* 2838 */     l = 31L * l + VecMathUtil.floatToIntBits(this.m33);
/* 2839 */     return (int)(l ^ l >> 32);
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
/*      */   public final void transform(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
/* 2852 */     float f1 = this.m00 * paramTuple4f1.x + this.m01 * paramTuple4f1.y + this.m02 * paramTuple4f1.z + this.m03 * paramTuple4f1.w;
/*      */     
/* 2854 */     float f2 = this.m10 * paramTuple4f1.x + this.m11 * paramTuple4f1.y + this.m12 * paramTuple4f1.z + this.m13 * paramTuple4f1.w;
/*      */     
/* 2856 */     float f3 = this.m20 * paramTuple4f1.x + this.m21 * paramTuple4f1.y + this.m22 * paramTuple4f1.z + this.m23 * paramTuple4f1.w;
/*      */     
/* 2858 */     paramTuple4f2.w = this.m30 * paramTuple4f1.x + this.m31 * paramTuple4f1.y + this.m32 * paramTuple4f1.z + this.m33 * paramTuple4f1.w;
/*      */     
/* 2860 */     paramTuple4f2.x = f1;
/* 2861 */     paramTuple4f2.y = f2;
/* 2862 */     paramTuple4f2.z = f3;
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
/*      */   public final void transform(Tuple4f paramTuple4f) {
/* 2875 */     float f1 = this.m00 * paramTuple4f.x + this.m01 * paramTuple4f.y + this.m02 * paramTuple4f.z + this.m03 * paramTuple4f.w;
/*      */     
/* 2877 */     float f2 = this.m10 * paramTuple4f.x + this.m11 * paramTuple4f.y + this.m12 * paramTuple4f.z + this.m13 * paramTuple4f.w;
/*      */     
/* 2879 */     float f3 = this.m20 * paramTuple4f.x + this.m21 * paramTuple4f.y + this.m22 * paramTuple4f.z + this.m23 * paramTuple4f.w;
/*      */     
/* 2881 */     paramTuple4f.w = this.m30 * paramTuple4f.x + this.m31 * paramTuple4f.y + this.m32 * paramTuple4f.z + this.m33 * paramTuple4f.w;
/*      */     
/* 2883 */     paramTuple4f.x = f1;
/* 2884 */     paramTuple4f.y = f2;
/* 2885 */     paramTuple4f.z = f3;
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
/*      */   public final void transform(Point3f paramPoint3f1, Point3f paramPoint3f2) {
/* 2898 */     float f1 = this.m00 * paramPoint3f1.x + this.m01 * paramPoint3f1.y + this.m02 * paramPoint3f1.z + this.m03;
/* 2899 */     float f2 = this.m10 * paramPoint3f1.x + this.m11 * paramPoint3f1.y + this.m12 * paramPoint3f1.z + this.m13;
/* 2900 */     paramPoint3f2.z = this.m20 * paramPoint3f1.x + this.m21 * paramPoint3f1.y + this.m22 * paramPoint3f1.z + this.m23;
/* 2901 */     paramPoint3f2.x = f1;
/* 2902 */     paramPoint3f2.y = f2;
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
/* 2915 */     float f1 = this.m00 * paramPoint3f.x + this.m01 * paramPoint3f.y + this.m02 * paramPoint3f.z + this.m03;
/* 2916 */     float f2 = this.m10 * paramPoint3f.x + this.m11 * paramPoint3f.y + this.m12 * paramPoint3f.z + this.m13;
/* 2917 */     paramPoint3f.z = this.m20 * paramPoint3f.x + this.m21 * paramPoint3f.y + this.m22 * paramPoint3f.z + this.m23;
/* 2918 */     paramPoint3f.x = f1;
/* 2919 */     paramPoint3f.y = f2;
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
/* 2932 */     float f1 = this.m00 * paramVector3f1.x + this.m01 * paramVector3f1.y + this.m02 * paramVector3f1.z;
/* 2933 */     float f2 = this.m10 * paramVector3f1.x + this.m11 * paramVector3f1.y + this.m12 * paramVector3f1.z;
/* 2934 */     paramVector3f2.z = this.m20 * paramVector3f1.x + this.m21 * paramVector3f1.y + this.m22 * paramVector3f1.z;
/* 2935 */     paramVector3f2.x = f1;
/* 2936 */     paramVector3f2.y = f2;
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
/* 2949 */     float f1 = this.m00 * paramVector3f.x + this.m01 * paramVector3f.y + this.m02 * paramVector3f.z;
/* 2950 */     float f2 = this.m10 * paramVector3f.x + this.m11 * paramVector3f.y + this.m12 * paramVector3f.z;
/* 2951 */     paramVector3f.z = this.m20 * paramVector3f.x + this.m21 * paramVector3f.y + this.m22 * paramVector3f.z;
/* 2952 */     paramVector3f.x = f1;
/* 2953 */     paramVector3f.y = f2;
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
/*      */   public final void setRotation(Matrix3d paramMatrix3d) {
/* 2969 */     double[] arrayOfDouble1 = new double[9];
/* 2970 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 2972 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 2974 */     this.m00 = (float)(paramMatrix3d.m00 * arrayOfDouble2[0]);
/* 2975 */     this.m01 = (float)(paramMatrix3d.m01 * arrayOfDouble2[1]);
/* 2976 */     this.m02 = (float)(paramMatrix3d.m02 * arrayOfDouble2[2]);
/*      */     
/* 2978 */     this.m10 = (float)(paramMatrix3d.m10 * arrayOfDouble2[0]);
/* 2979 */     this.m11 = (float)(paramMatrix3d.m11 * arrayOfDouble2[1]);
/* 2980 */     this.m12 = (float)(paramMatrix3d.m12 * arrayOfDouble2[2]);
/*      */     
/* 2982 */     this.m20 = (float)(paramMatrix3d.m20 * arrayOfDouble2[0]);
/* 2983 */     this.m21 = (float)(paramMatrix3d.m21 * arrayOfDouble2[1]);
/* 2984 */     this.m22 = (float)(paramMatrix3d.m22 * arrayOfDouble2[2]);
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
/*      */   public final void setRotation(Matrix3f paramMatrix3f) {
/* 2999 */     double[] arrayOfDouble1 = new double[9];
/* 3000 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 3002 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3004 */     this.m00 = (float)(paramMatrix3f.m00 * arrayOfDouble2[0]);
/* 3005 */     this.m01 = (float)(paramMatrix3f.m01 * arrayOfDouble2[1]);
/* 3006 */     this.m02 = (float)(paramMatrix3f.m02 * arrayOfDouble2[2]);
/*      */     
/* 3008 */     this.m10 = (float)(paramMatrix3f.m10 * arrayOfDouble2[0]);
/* 3009 */     this.m11 = (float)(paramMatrix3f.m11 * arrayOfDouble2[1]);
/* 3010 */     this.m12 = (float)(paramMatrix3f.m12 * arrayOfDouble2[2]);
/*      */     
/* 3012 */     this.m20 = (float)(paramMatrix3f.m20 * arrayOfDouble2[0]);
/* 3013 */     this.m21 = (float)(paramMatrix3f.m21 * arrayOfDouble2[1]);
/* 3014 */     this.m22 = (float)(paramMatrix3f.m22 * arrayOfDouble2[2]);
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
/* 3028 */     double[] arrayOfDouble1 = new double[9];
/* 3029 */     double[] arrayOfDouble2 = new double[3];
/* 3030 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3032 */     this.m00 = (float)((1.0F - 2.0F * paramQuat4f.y * paramQuat4f.y - 2.0F * paramQuat4f.z * paramQuat4f.z) * arrayOfDouble2[0]);
/* 3033 */     this.m10 = (float)((2.0F * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z)) * arrayOfDouble2[0]);
/* 3034 */     this.m20 = (float)((2.0F * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y)) * arrayOfDouble2[0]);
/*      */     
/* 3036 */     this.m01 = (float)((2.0F * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z)) * arrayOfDouble2[1]);
/* 3037 */     this.m11 = (float)((1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.z * paramQuat4f.z) * arrayOfDouble2[1]);
/* 3038 */     this.m21 = (float)((2.0F * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x)) * arrayOfDouble2[1]);
/*      */     
/* 3040 */     this.m02 = (float)((2.0F * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y)) * arrayOfDouble2[2]);
/* 3041 */     this.m12 = (float)((2.0F * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x)) * arrayOfDouble2[2]);
/* 3042 */     this.m22 = (float)((1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.y * paramQuat4f.y) * arrayOfDouble2[2]);
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
/*      */   public final void setRotation(Quat4d paramQuat4d) {
/* 3058 */     double[] arrayOfDouble1 = new double[9];
/* 3059 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 3061 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3063 */     this.m00 = (float)((1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z) * arrayOfDouble2[0]);
/* 3064 */     this.m10 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z) * arrayOfDouble2[0]);
/* 3065 */     this.m20 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y) * arrayOfDouble2[0]);
/*      */     
/* 3067 */     this.m01 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z) * arrayOfDouble2[1]);
/* 3068 */     this.m11 = (float)((1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z) * arrayOfDouble2[1]);
/* 3069 */     this.m21 = (float)(2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x) * arrayOfDouble2[1]);
/*      */     
/* 3071 */     this.m02 = (float)(2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y) * arrayOfDouble2[2]);
/* 3072 */     this.m12 = (float)(2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x) * arrayOfDouble2[2]);
/* 3073 */     this.m22 = (float)((1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y) * arrayOfDouble2[2]);
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
/*      */   public final void setRotation(AxisAngle4f paramAxisAngle4f) {
/* 3087 */     double[] arrayOfDouble1 = new double[9];
/* 3088 */     double[] arrayOfDouble2 = new double[3];
/*      */     
/* 3090 */     getScaleRotate(arrayOfDouble2, arrayOfDouble1);
/*      */     
/* 3092 */     double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/* 3093 */     if (d < 1.0E-8D) {
/* 3094 */       this.m00 = 1.0F;
/* 3095 */       this.m01 = 0.0F;
/* 3096 */       this.m02 = 0.0F;
/*      */       
/* 3098 */       this.m10 = 0.0F;
/* 3099 */       this.m11 = 1.0F;
/* 3100 */       this.m12 = 0.0F;
/*      */       
/* 3102 */       this.m20 = 0.0F;
/* 3103 */       this.m21 = 0.0F;
/* 3104 */       this.m22 = 1.0F;
/*      */     } else {
/* 3106 */       d = 1.0D / d;
/* 3107 */       double d1 = paramAxisAngle4f.x * d;
/* 3108 */       double d2 = paramAxisAngle4f.y * d;
/* 3109 */       double d3 = paramAxisAngle4f.z * d;
/*      */       
/* 3111 */       double d4 = Math.sin(paramAxisAngle4f.angle);
/* 3112 */       double d5 = Math.cos(paramAxisAngle4f.angle);
/* 3113 */       double d6 = 1.0D - d5;
/*      */       
/* 3115 */       double d7 = (paramAxisAngle4f.x * paramAxisAngle4f.z);
/* 3116 */       double d8 = (paramAxisAngle4f.x * paramAxisAngle4f.y);
/* 3117 */       double d9 = (paramAxisAngle4f.y * paramAxisAngle4f.z);
/*      */       
/* 3119 */       this.m00 = (float)((d6 * d1 * d1 + d5) * arrayOfDouble2[0]);
/* 3120 */       this.m01 = (float)((d6 * d8 - d4 * d3) * arrayOfDouble2[1]);
/* 3121 */       this.m02 = (float)((d6 * d7 + d4 * d2) * arrayOfDouble2[2]);
/*      */       
/* 3123 */       this.m10 = (float)((d6 * d8 + d4 * d3) * arrayOfDouble2[0]);
/* 3124 */       this.m11 = (float)((d6 * d2 * d2 + d5) * arrayOfDouble2[1]);
/* 3125 */       this.m12 = (float)((d6 * d9 - d4 * d1) * arrayOfDouble2[2]);
/*      */       
/* 3127 */       this.m20 = (float)((d6 * d7 - d4 * d2) * arrayOfDouble2[0]);
/* 3128 */       this.m21 = (float)((d6 * d9 + d4 * d1) * arrayOfDouble2[1]);
/* 3129 */       this.m22 = (float)((d6 * d3 * d3 + d5) * arrayOfDouble2[2]);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setZero() {
/* 3140 */     this.m00 = 0.0F;
/* 3141 */     this.m01 = 0.0F;
/* 3142 */     this.m02 = 0.0F;
/* 3143 */     this.m03 = 0.0F;
/* 3144 */     this.m10 = 0.0F;
/* 3145 */     this.m11 = 0.0F;
/* 3146 */     this.m12 = 0.0F;
/* 3147 */     this.m13 = 0.0F;
/* 3148 */     this.m20 = 0.0F;
/* 3149 */     this.m21 = 0.0F;
/* 3150 */     this.m22 = 0.0F;
/* 3151 */     this.m23 = 0.0F;
/* 3152 */     this.m30 = 0.0F;
/* 3153 */     this.m31 = 0.0F;
/* 3154 */     this.m32 = 0.0F;
/* 3155 */     this.m33 = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate() {
/* 3163 */     this.m00 = -this.m00;
/* 3164 */     this.m01 = -this.m01;
/* 3165 */     this.m02 = -this.m02;
/* 3166 */     this.m03 = -this.m03;
/* 3167 */     this.m10 = -this.m10;
/* 3168 */     this.m11 = -this.m11;
/* 3169 */     this.m12 = -this.m12;
/* 3170 */     this.m13 = -this.m13;
/* 3171 */     this.m20 = -this.m20;
/* 3172 */     this.m21 = -this.m21;
/* 3173 */     this.m22 = -this.m22;
/* 3174 */     this.m23 = -this.m23;
/* 3175 */     this.m30 = -this.m30;
/* 3176 */     this.m31 = -this.m31;
/* 3177 */     this.m32 = -this.m32;
/* 3178 */     this.m33 = -this.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate(Matrix4f paramMatrix4f) {
/* 3188 */     this.m00 = -paramMatrix4f.m00;
/* 3189 */     this.m01 = -paramMatrix4f.m01;
/* 3190 */     this.m02 = -paramMatrix4f.m02;
/* 3191 */     this.m03 = -paramMatrix4f.m03;
/* 3192 */     this.m10 = -paramMatrix4f.m10;
/* 3193 */     this.m11 = -paramMatrix4f.m11;
/* 3194 */     this.m12 = -paramMatrix4f.m12;
/* 3195 */     this.m13 = -paramMatrix4f.m13;
/* 3196 */     this.m20 = -paramMatrix4f.m20;
/* 3197 */     this.m21 = -paramMatrix4f.m21;
/* 3198 */     this.m22 = -paramMatrix4f.m22;
/* 3199 */     this.m23 = -paramMatrix4f.m23;
/* 3200 */     this.m30 = -paramMatrix4f.m30;
/* 3201 */     this.m31 = -paramMatrix4f.m31;
/* 3202 */     this.m32 = -paramMatrix4f.m32;
/* 3203 */     this.m33 = -paramMatrix4f.m33;
/*      */   }
/*      */   
/*      */   private final void getScaleRotate(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 3207 */     double[] arrayOfDouble = new double[9];
/* 3208 */     arrayOfDouble[0] = this.m00;
/* 3209 */     arrayOfDouble[1] = this.m01;
/* 3210 */     arrayOfDouble[2] = this.m02;
/*      */     
/* 3212 */     arrayOfDouble[3] = this.m10;
/* 3213 */     arrayOfDouble[4] = this.m11;
/* 3214 */     arrayOfDouble[5] = this.m12;
/*      */     
/* 3216 */     arrayOfDouble[6] = this.m20;
/* 3217 */     arrayOfDouble[7] = this.m21;
/* 3218 */     arrayOfDouble[8] = this.m22;
/*      */     
/* 3220 */     Matrix3d.compute_svd(arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2);
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
/* 3234 */     Matrix4f matrix4f = null;
/*      */     try {
/* 3236 */       matrix4f = (Matrix4f)super.clone();
/* 3237 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*      */       
/* 3239 */       throw new InternalError();
/*      */     } 
/*      */     
/* 3242 */     return matrix4f;
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
/* 3253 */   public final float getM00() { return this.m00; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3264 */   public final void setM00(float paramFloat) { this.m00 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3275 */   public final float getM01() { return this.m01; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3286 */   public final void setM01(float paramFloat) { this.m01 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3297 */   public final float getM02() { return this.m02; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3308 */   public final void setM02(float paramFloat) { this.m02 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3319 */   public final float getM10() { return this.m10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3330 */   public final void setM10(float paramFloat) { this.m10 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3341 */   public final float getM11() { return this.m11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3352 */   public final void setM11(float paramFloat) { this.m11 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3363 */   public final float getM12() { return this.m12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3374 */   public final void setM12(float paramFloat) { this.m12 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3385 */   public final float getM20() { return this.m20; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3396 */   public final void setM20(float paramFloat) { this.m20 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3407 */   public final float getM21() { return this.m21; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3418 */   public final void setM21(float paramFloat) { this.m21 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3429 */   public final float getM22() { return this.m22; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3440 */   public final void setM22(float paramFloat) { this.m22 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3451 */   public final float getM03() { return this.m03; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3462 */   public final void setM03(float paramFloat) { this.m03 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3473 */   public final float getM13() { return this.m13; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3484 */   public final void setM13(float paramFloat) { this.m13 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3495 */   public final float getM23() { return this.m23; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3506 */   public final void setM23(float paramFloat) { this.m23 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3517 */   public final float getM30() { return this.m30; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3529 */   public final void setM30(float paramFloat) { this.m30 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3540 */   public final float getM31() { return this.m31; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3551 */   public final void setM31(float paramFloat) { this.m31 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3562 */   public final float getM32() { return this.m32; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3574 */   public final void setM32(float paramFloat) { this.m32 = paramFloat; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3585 */   public final float getM33() { return this.m33; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3596 */   public final void setM33(float paramFloat) { this.m33 = paramFloat; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\Matrix4f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */