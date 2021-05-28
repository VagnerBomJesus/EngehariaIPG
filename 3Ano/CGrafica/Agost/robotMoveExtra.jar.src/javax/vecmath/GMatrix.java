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
/*      */ public class GMatrix
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   static final long serialVersionUID = 2777097312029690941L;
/*      */   private static final boolean debug = false;
/*      */   int nRow;
/*      */   int nCol;
/*      */   double[][] values;
/*      */   private static final double EPS = 1.0E-10D;
/*      */   
/*      */   public GMatrix(int paramInt1, int paramInt2) {
/*   47 */     this.values = new double[paramInt1][paramInt2];
/*   48 */     this.nRow = paramInt1;
/*   49 */     this.nCol = paramInt2;
/*      */     
/*      */     byte b;
/*   52 */     for (b = 0; b < paramInt1; b++) {
/*   53 */       for (byte b1 = 0; b1 < paramInt2; b1++) {
/*   54 */         this.values[b][b1] = 0.0D;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*   59 */     if (paramInt1 < paramInt2) {
/*   60 */       i = paramInt1;
/*      */     } else {
/*   62 */       i = paramInt2;
/*      */     } 
/*   64 */     for (b = 0; b < i; b++) {
/*   65 */       this.values[b][b] = 1.0D;
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
/*      */   public GMatrix(int paramInt1, int paramInt2, double[] paramArrayOfDouble) {
/*   83 */     this.values = new double[paramInt1][paramInt2];
/*   84 */     this.nRow = paramInt1;
/*   85 */     this.nCol = paramInt2;
/*      */ 
/*      */     
/*   88 */     for (int i = 0; i < paramInt1; i++) {
/*   89 */       for (int j = 0; j < paramInt2; j++) {
/*   90 */         this.values[i][j] = paramArrayOfDouble[i * paramInt2 + j];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GMatrix(GMatrix paramGMatrix) {
/*  102 */     this.nRow = paramGMatrix.nRow;
/*  103 */     this.nCol = paramGMatrix.nCol;
/*  104 */     this.values = new double[this.nRow][this.nCol];
/*      */ 
/*      */     
/*  107 */     for (byte b = 0; b < this.nRow; b++) {
/*  108 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  109 */         this.values[b][b1] = paramGMatrix.values[b][b1];
/*      */       }
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
/*      */   public final void mul(GMatrix paramGMatrix) {
/*  123 */     if (this.nCol != paramGMatrix.nRow || this.nCol != paramGMatrix.nCol) {
/*  124 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix0"));
/*      */     }
/*      */     
/*  127 */     double[][] arrayOfDouble = new double[this.nRow][this.nCol];
/*      */     
/*  129 */     for (byte b = 0; b < this.nRow; b++) {
/*  130 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  131 */         arrayOfDouble[b][b1] = 0.0D;
/*  132 */         for (byte b2 = 0; b2 < this.nCol; b2++) {
/*  133 */           arrayOfDouble[b][b1] = arrayOfDouble[b][b1] + this.values[b][b2] * paramGMatrix.values[b2][b1];
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  138 */     this.values = arrayOfDouble;
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
/*      */   public final void mul(GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/*  151 */     if (paramGMatrix1.nCol != paramGMatrix2.nRow || this.nRow != paramGMatrix1.nRow || this.nCol != paramGMatrix2.nCol) {
/*  152 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix1"));
/*      */     }
/*      */     
/*  155 */     double[][] arrayOfDouble = new double[this.nRow][this.nCol];
/*      */     
/*  157 */     for (byte b = 0; b < paramGMatrix1.nRow; b++) {
/*  158 */       for (byte b1 = 0; b1 < paramGMatrix2.nCol; b1++) {
/*  159 */         arrayOfDouble[b][b1] = 0.0D;
/*  160 */         for (byte b2 = 0; b2 < paramGMatrix1.nCol; b2++) {
/*  161 */           arrayOfDouble[b][b1] = arrayOfDouble[b][b1] + paramGMatrix1.values[b][b2] * paramGMatrix2.values[b2][b1];
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  166 */     this.values = arrayOfDouble;
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
/*      */   public final void mul(GVector paramGVector1, GVector paramGVector2) {
/*  181 */     if (this.nRow < paramGVector1.getSize()) {
/*  182 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix2"));
/*      */     }
/*      */     
/*  185 */     if (this.nCol < paramGVector2.getSize()) {
/*  186 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix3"));
/*      */     }
/*      */     
/*  189 */     for (byte b = 0; b < paramGVector1.getSize(); b++) {
/*  190 */       for (byte b1 = 0; b1 < paramGVector2.getSize(); b1++) {
/*  191 */         this.values[b][b1] = paramGVector1.values[b] * paramGVector2.values[b1];
/*      */       }
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
/*      */   public final void add(GMatrix paramGMatrix) {
/*  204 */     if (this.nRow != paramGMatrix.nRow) {
/*  205 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix4"));
/*      */     }
/*      */     
/*  208 */     if (this.nCol != paramGMatrix.nCol) {
/*  209 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix5"));
/*      */     }
/*      */     
/*  212 */     for (byte b = 0; b < this.nRow; b++) {
/*  213 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  214 */         this.values[b][b1] = this.values[b][b1] + paramGMatrix.values[b][b1];
/*      */       }
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
/*      */   public final void add(GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/*  228 */     if (paramGMatrix2.nRow != paramGMatrix1.nRow) {
/*  229 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix6"));
/*      */     }
/*      */     
/*  232 */     if (paramGMatrix2.nCol != paramGMatrix1.nCol) {
/*  233 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix7"));
/*      */     }
/*      */     
/*  236 */     if (this.nCol != paramGMatrix1.nCol || this.nRow != paramGMatrix1.nRow) {
/*  237 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix8"));
/*      */     }
/*      */     
/*  240 */     for (byte b = 0; b < this.nRow; b++) {
/*  241 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  242 */         this.values[b][b1] = paramGMatrix1.values[b][b1] + paramGMatrix2.values[b][b1];
/*      */       }
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
/*      */   public final void sub(GMatrix paramGMatrix) {
/*  255 */     if (this.nRow != paramGMatrix.nRow) {
/*  256 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix9"));
/*      */     }
/*      */     
/*  259 */     if (this.nCol != paramGMatrix.nCol) {
/*  260 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix28"));
/*      */     }
/*      */     
/*  263 */     for (byte b = 0; b < this.nRow; b++) {
/*  264 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  265 */         this.values[b][b1] = this.values[b][b1] - paramGMatrix.values[b][b1];
/*      */       }
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
/*      */   public final void sub(GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/*  279 */     if (paramGMatrix2.nRow != paramGMatrix1.nRow) {
/*  280 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix10"));
/*      */     }
/*      */     
/*  283 */     if (paramGMatrix2.nCol != paramGMatrix1.nCol) {
/*  284 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix11"));
/*      */     }
/*      */     
/*  287 */     if (this.nRow != paramGMatrix1.nRow || this.nCol != paramGMatrix1.nCol) {
/*  288 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix12"));
/*      */     }
/*      */     
/*  291 */     for (byte b = 0; b < this.nRow; b++) {
/*  292 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  293 */         this.values[b][b1] = paramGMatrix1.values[b][b1] - paramGMatrix2.values[b][b1];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void negate() {
/*  304 */     for (byte b = 0; b < this.nRow; b++) {
/*  305 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  306 */         this.values[b][b1] = -this.values[b][b1];
/*      */       }
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
/*      */   public final void negate(GMatrix paramGMatrix) {
/*  319 */     if (this.nRow != paramGMatrix.nRow || this.nCol != paramGMatrix.nCol) {
/*  320 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix13"));
/*      */     }
/*      */     
/*  323 */     for (byte b = 0; b < this.nRow; b++) {
/*  324 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  325 */         this.values[b][b1] = -paramGMatrix.values[b][b1];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setIdentity() {
/*      */     int i;
/*      */     byte b;
/*  336 */     for (b = 0; b < this.nRow; b++) {
/*  337 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  338 */         this.values[b][b1] = 0.0D;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  343 */     if (this.nRow < this.nCol) {
/*  344 */       i = this.nRow;
/*      */     } else {
/*  346 */       i = this.nCol;
/*      */     } 
/*  348 */     for (b = 0; b < i; b++) {
/*  349 */       this.values[b][b] = 1.0D;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setZero() {
/*  359 */     for (byte b = 0; b < this.nRow; b++) {
/*  360 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  361 */         this.values[b][b1] = 0.0D;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void identityMinus() {
/*      */     int i;
/*      */     byte b;
/*  374 */     for (b = 0; b < this.nRow; b++) {
/*  375 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/*  376 */         this.values[b][b1] = -this.values[b][b1];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  381 */     if (this.nRow < this.nCol) {
/*  382 */       i = this.nRow;
/*      */     } else {
/*  384 */       i = this.nCol;
/*      */     } 
/*  386 */     for (b = 0; b < i; b++) {
/*  387 */       this.values[b][b] = this.values[b][b] + 1.0D;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  397 */   public final void invert() { invertGeneral(this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  407 */   public final void invert(GMatrix paramGMatrix) { invertGeneral(paramGMatrix); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void copySubMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, GMatrix paramGMatrix) {
/*  432 */     if (this != paramGMatrix) {
/*  433 */       for (int i = 0; i < paramInt3; i++) {
/*  434 */         for (int j = 0; j < paramInt4; j++) {
/*  435 */           paramGMatrix.values[paramInt5 + i][paramInt6 + j] = this.values[paramInt1 + i][paramInt2 + j];
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/*  440 */       double[][] arrayOfDouble = new double[paramInt3][paramInt4]; int i;
/*  441 */       for (i = 0; i < paramInt3; i++) {
/*  442 */         for (int j = 0; j < paramInt4; j++) {
/*  443 */           arrayOfDouble[i][j] = this.values[paramInt1 + i][paramInt2 + j];
/*      */         }
/*      */       } 
/*  446 */       for (i = 0; i < paramInt3; i++) {
/*  447 */         for (int j = 0; j < paramInt4; j++) {
/*  448 */           paramGMatrix.values[paramInt5 + i][paramInt6 + j] = arrayOfDouble[i][j];
/*      */         }
/*      */       } 
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
/*      */   public final void setSize(int paramInt1, int paramInt2) {
/*      */     int j, i;
/*  463 */     double[][] arrayOfDouble = new double[paramInt1][paramInt2];
/*      */ 
/*      */     
/*  466 */     if (this.nRow < paramInt1) {
/*  467 */       i = this.nRow;
/*      */     } else {
/*  469 */       i = paramInt1;
/*      */     } 
/*  471 */     if (this.nCol < paramInt2) {
/*  472 */       j = this.nCol;
/*      */     } else {
/*  474 */       j = paramInt2;
/*      */     } 
/*  476 */     for (byte b = 0; b < i; b++) {
/*  477 */       for (byte b1 = 0; b1 < j; b1++) {
/*  478 */         arrayOfDouble[b][b1] = this.values[b][b1];
/*      */       }
/*      */     } 
/*      */     
/*  482 */     this.nRow = paramInt1;
/*  483 */     this.nCol = paramInt2;
/*      */     
/*  485 */     this.values = arrayOfDouble;
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
/*      */   public final void set(double[] paramArrayOfDouble) {
/*  500 */     for (int i = 0; i < this.nRow; i++) {
/*  501 */       for (int j = 0; j < this.nCol; j++) {
/*  502 */         this.values[i][j] = paramArrayOfDouble[this.nCol * i + j];
/*      */       }
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
/*      */   public final void set(Matrix3f paramMatrix3f) {
/*  515 */     if (this.nCol < 3 || this.nRow < 3) {
/*  516 */       this.nCol = 3;
/*  517 */       this.nRow = 3;
/*  518 */       this.values = new double[this.nRow][this.nCol];
/*      */     } 
/*      */     
/*  521 */     this.values[0][0] = paramMatrix3f.m00;
/*  522 */     this.values[0][1] = paramMatrix3f.m01;
/*  523 */     this.values[0][2] = paramMatrix3f.m02;
/*      */     
/*  525 */     this.values[1][0] = paramMatrix3f.m10;
/*  526 */     this.values[1][1] = paramMatrix3f.m11;
/*  527 */     this.values[1][2] = paramMatrix3f.m12;
/*      */     
/*  529 */     this.values[2][0] = paramMatrix3f.m20;
/*  530 */     this.values[2][1] = paramMatrix3f.m21;
/*  531 */     this.values[2][2] = paramMatrix3f.m22;
/*      */     
/*  533 */     for (byte b = 3; b < this.nRow; b++) {
/*  534 */       for (byte b1 = 3; b1 < this.nCol; b1++) {
/*  535 */         this.values[b][b1] = 0.0D;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix3d paramMatrix3d) {
/*  546 */     if (this.nRow < 3 || this.nCol < 3) {
/*  547 */       this.values = new double[3][3];
/*  548 */       this.nRow = 3;
/*  549 */       this.nCol = 3;
/*      */     } 
/*      */     
/*  552 */     this.values[0][0] = paramMatrix3d.m00;
/*  553 */     this.values[0][1] = paramMatrix3d.m01;
/*  554 */     this.values[0][2] = paramMatrix3d.m02;
/*      */     
/*  556 */     this.values[1][0] = paramMatrix3d.m10;
/*  557 */     this.values[1][1] = paramMatrix3d.m11;
/*  558 */     this.values[1][2] = paramMatrix3d.m12;
/*      */     
/*  560 */     this.values[2][0] = paramMatrix3d.m20;
/*  561 */     this.values[2][1] = paramMatrix3d.m21;
/*  562 */     this.values[2][2] = paramMatrix3d.m22;
/*      */     
/*  564 */     for (byte b = 3; b < this.nRow; b++) {
/*  565 */       for (byte b1 = 3; b1 < this.nCol; b1++) {
/*  566 */         this.values[b][b1] = 0.0D;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix4f paramMatrix4f) {
/*  578 */     if (this.nRow < 4 || this.nCol < 4) {
/*  579 */       this.values = new double[4][4];
/*  580 */       this.nRow = 4;
/*  581 */       this.nCol = 4;
/*      */     } 
/*      */     
/*  584 */     this.values[0][0] = paramMatrix4f.m00;
/*  585 */     this.values[0][1] = paramMatrix4f.m01;
/*  586 */     this.values[0][2] = paramMatrix4f.m02;
/*  587 */     this.values[0][3] = paramMatrix4f.m03;
/*      */     
/*  589 */     this.values[1][0] = paramMatrix4f.m10;
/*  590 */     this.values[1][1] = paramMatrix4f.m11;
/*  591 */     this.values[1][2] = paramMatrix4f.m12;
/*  592 */     this.values[1][3] = paramMatrix4f.m13;
/*      */     
/*  594 */     this.values[2][0] = paramMatrix4f.m20;
/*  595 */     this.values[2][1] = paramMatrix4f.m21;
/*  596 */     this.values[2][2] = paramMatrix4f.m22;
/*  597 */     this.values[2][3] = paramMatrix4f.m23;
/*      */     
/*  599 */     this.values[3][0] = paramMatrix4f.m30;
/*  600 */     this.values[3][1] = paramMatrix4f.m31;
/*  601 */     this.values[3][2] = paramMatrix4f.m32;
/*  602 */     this.values[3][3] = paramMatrix4f.m33;
/*      */     
/*  604 */     for (byte b = 4; b < this.nRow; b++) {
/*  605 */       for (byte b1 = 4; b1 < this.nCol; b1++) {
/*  606 */         this.values[b][b1] = 0.0D;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Matrix4d paramMatrix4d) {
/*  617 */     if (this.nRow < 4 || this.nCol < 4) {
/*  618 */       this.values = new double[4][4];
/*  619 */       this.nRow = 4;
/*  620 */       this.nCol = 4;
/*      */     } 
/*      */     
/*  623 */     this.values[0][0] = paramMatrix4d.m00;
/*  624 */     this.values[0][1] = paramMatrix4d.m01;
/*  625 */     this.values[0][2] = paramMatrix4d.m02;
/*  626 */     this.values[0][3] = paramMatrix4d.m03;
/*      */     
/*  628 */     this.values[1][0] = paramMatrix4d.m10;
/*  629 */     this.values[1][1] = paramMatrix4d.m11;
/*  630 */     this.values[1][2] = paramMatrix4d.m12;
/*  631 */     this.values[1][3] = paramMatrix4d.m13;
/*      */     
/*  633 */     this.values[2][0] = paramMatrix4d.m20;
/*  634 */     this.values[2][1] = paramMatrix4d.m21;
/*  635 */     this.values[2][2] = paramMatrix4d.m22;
/*  636 */     this.values[2][3] = paramMatrix4d.m23;
/*      */     
/*  638 */     this.values[3][0] = paramMatrix4d.m30;
/*  639 */     this.values[3][1] = paramMatrix4d.m31;
/*  640 */     this.values[3][2] = paramMatrix4d.m32;
/*  641 */     this.values[3][3] = paramMatrix4d.m33;
/*      */     
/*  643 */     for (byte b = 4; b < this.nRow; b++) {
/*  644 */       for (byte b1 = 4; b1 < this.nCol; b1++) {
/*  645 */         this.values[b][b1] = 0.0D;
/*      */       }
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
/*      */   public final void set(GMatrix paramGMatrix) {
/*  658 */     if (this.nRow < paramGMatrix.nRow || this.nCol < paramGMatrix.nCol) {
/*  659 */       this.nRow = paramGMatrix.nRow;
/*  660 */       this.nCol = paramGMatrix.nCol;
/*  661 */       this.values = new double[this.nRow][this.nCol];
/*      */     } 
/*      */     int i;
/*  664 */     for (i = 0; i < Math.min(this.nRow, paramGMatrix.nRow); i++) {
/*  665 */       for (byte b = 0; b < Math.min(this.nCol, paramGMatrix.nCol); b++) {
/*  666 */         this.values[i][b] = paramGMatrix.values[i][b];
/*      */       }
/*      */     } 
/*      */     
/*  670 */     for (i = paramGMatrix.nRow; i < this.nRow; i++) {
/*  671 */       for (int j = paramGMatrix.nCol; j < this.nCol; j++) {
/*  672 */         this.values[i][j] = 0.0D;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  683 */   public final int getNumRow() { return this.nRow; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  692 */   public final int getNumCol() { return this.nCol; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  703 */   public final double getElement(int paramInt1, int paramInt2) { return this.values[paramInt1][paramInt2]; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  715 */   public final void setElement(int paramInt1, int paramInt2, double paramDouble) { this.values[paramInt1][paramInt2] = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, double[] paramArrayOfDouble) {
/*  725 */     for (byte b = 0; b < this.nCol; b++) {
/*  726 */       paramArrayOfDouble[b] = this.values[paramInt][b];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRow(int paramInt, GVector paramGVector) {
/*  737 */     if (paramGVector.getSize() < this.nCol) {
/*  738 */       paramGVector.setSize(this.nCol);
/*      */     }
/*  740 */     for (byte b = 0; b < this.nCol; b++) {
/*  741 */       paramGVector.values[b] = this.values[paramInt][b];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getColumn(int paramInt, double[] paramArrayOfDouble) {
/*  752 */     for (byte b = 0; b < this.nRow; b++) {
/*  753 */       paramArrayOfDouble[b] = this.values[b][paramInt];
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
/*      */   public final void getColumn(int paramInt, GVector paramGVector) {
/*  765 */     if (paramGVector.getSize() < this.nRow) {
/*  766 */       paramGVector.setSize(this.nRow);
/*      */     }
/*  768 */     for (byte b = 0; b < this.nRow; b++) {
/*  769 */       paramGVector.values[b] = this.values[b][paramInt];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Matrix3d paramMatrix3d) {
/*  780 */     if (this.nRow < 3 || this.nCol < 3) {
/*  781 */       paramMatrix3d.setZero();
/*  782 */       if (this.nCol > 0) {
/*  783 */         if (this.nRow > 0) {
/*  784 */           paramMatrix3d.m00 = this.values[0][0];
/*  785 */           if (this.nRow > 1) {
/*  786 */             paramMatrix3d.m10 = this.values[1][0];
/*  787 */             if (this.nRow > 2) {
/*  788 */               paramMatrix3d.m20 = this.values[2][0];
/*      */             }
/*      */           } 
/*      */         } 
/*  792 */         if (this.nCol > 1) {
/*  793 */           if (this.nRow > 0) {
/*  794 */             paramMatrix3d.m01 = this.values[0][1];
/*  795 */             if (this.nRow > 1) {
/*  796 */               paramMatrix3d.m11 = this.values[1][1];
/*  797 */               if (this.nRow > 2) {
/*  798 */                 paramMatrix3d.m21 = this.values[2][1];
/*      */               }
/*      */             } 
/*      */           } 
/*  802 */           if (this.nCol > 2 && 
/*  803 */             this.nRow > 0) {
/*  804 */             paramMatrix3d.m02 = this.values[0][2];
/*  805 */             if (this.nRow > 1) {
/*  806 */               paramMatrix3d.m12 = this.values[1][2];
/*  807 */               if (this.nRow > 2) {
/*  808 */                 paramMatrix3d.m22 = this.values[2][2];
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  816 */       paramMatrix3d.m00 = this.values[0][0];
/*  817 */       paramMatrix3d.m01 = this.values[0][1];
/*  818 */       paramMatrix3d.m02 = this.values[0][2];
/*      */       
/*  820 */       paramMatrix3d.m10 = this.values[1][0];
/*  821 */       paramMatrix3d.m11 = this.values[1][1];
/*  822 */       paramMatrix3d.m12 = this.values[1][2];
/*      */       
/*  824 */       paramMatrix3d.m20 = this.values[2][0];
/*  825 */       paramMatrix3d.m21 = this.values[2][1];
/*  826 */       paramMatrix3d.m22 = this.values[2][2];
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
/*      */   public final void get(Matrix3f paramMatrix3f) {
/*  838 */     if (this.nRow < 3 || this.nCol < 3) {
/*  839 */       paramMatrix3f.setZero();
/*  840 */       if (this.nCol > 0) {
/*  841 */         if (this.nRow > 0) {
/*  842 */           paramMatrix3f.m00 = (float)this.values[0][0];
/*  843 */           if (this.nRow > 1) {
/*  844 */             paramMatrix3f.m10 = (float)this.values[1][0];
/*  845 */             if (this.nRow > 2) {
/*  846 */               paramMatrix3f.m20 = (float)this.values[2][0];
/*      */             }
/*      */           } 
/*      */         } 
/*  850 */         if (this.nCol > 1) {
/*  851 */           if (this.nRow > 0) {
/*  852 */             paramMatrix3f.m01 = (float)this.values[0][1];
/*  853 */             if (this.nRow > 1) {
/*  854 */               paramMatrix3f.m11 = (float)this.values[1][1];
/*  855 */               if (this.nRow > 2) {
/*  856 */                 paramMatrix3f.m21 = (float)this.values[2][1];
/*      */               }
/*      */             } 
/*      */           } 
/*  860 */           if (this.nCol > 2 && 
/*  861 */             this.nRow > 0) {
/*  862 */             paramMatrix3f.m02 = (float)this.values[0][2];
/*  863 */             if (this.nRow > 1) {
/*  864 */               paramMatrix3f.m12 = (float)this.values[1][2];
/*  865 */               if (this.nRow > 2) {
/*  866 */                 paramMatrix3f.m22 = (float)this.values[2][2];
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  874 */       paramMatrix3f.m00 = (float)this.values[0][0];
/*  875 */       paramMatrix3f.m01 = (float)this.values[0][1];
/*  876 */       paramMatrix3f.m02 = (float)this.values[0][2];
/*      */       
/*  878 */       paramMatrix3f.m10 = (float)this.values[1][0];
/*  879 */       paramMatrix3f.m11 = (float)this.values[1][1];
/*  880 */       paramMatrix3f.m12 = (float)this.values[1][2];
/*      */       
/*  882 */       paramMatrix3f.m20 = (float)this.values[2][0];
/*  883 */       paramMatrix3f.m21 = (float)this.values[2][1];
/*  884 */       paramMatrix3f.m22 = (float)this.values[2][2];
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Matrix4d paramMatrix4d) {
/*  895 */     if (this.nRow < 4 || this.nCol < 4) {
/*  896 */       paramMatrix4d.setZero();
/*  897 */       if (this.nCol > 0) {
/*  898 */         if (this.nRow > 0) {
/*  899 */           paramMatrix4d.m00 = this.values[0][0];
/*  900 */           if (this.nRow > 1) {
/*  901 */             paramMatrix4d.m10 = this.values[1][0];
/*  902 */             if (this.nRow > 2) {
/*  903 */               paramMatrix4d.m20 = this.values[2][0];
/*  904 */               if (this.nRow > 3) {
/*  905 */                 paramMatrix4d.m30 = this.values[3][0];
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*  910 */         if (this.nCol > 1) {
/*  911 */           if (this.nRow > 0) {
/*  912 */             paramMatrix4d.m01 = this.values[0][1];
/*  913 */             if (this.nRow > 1) {
/*  914 */               paramMatrix4d.m11 = this.values[1][1];
/*  915 */               if (this.nRow > 2) {
/*  916 */                 paramMatrix4d.m21 = this.values[2][1];
/*  917 */                 if (this.nRow > 3) {
/*  918 */                   paramMatrix4d.m31 = this.values[3][1];
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*  923 */           if (this.nCol > 2) {
/*  924 */             if (this.nRow > 0) {
/*  925 */               paramMatrix4d.m02 = this.values[0][2];
/*  926 */               if (this.nRow > 1) {
/*  927 */                 paramMatrix4d.m12 = this.values[1][2];
/*  928 */                 if (this.nRow > 2) {
/*  929 */                   paramMatrix4d.m22 = this.values[2][2];
/*  930 */                   if (this.nRow > 3) {
/*  931 */                     paramMatrix4d.m32 = this.values[3][2];
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*  936 */             if (this.nCol > 3 && 
/*  937 */               this.nRow > 0) {
/*  938 */               paramMatrix4d.m03 = this.values[0][3];
/*  939 */               if (this.nRow > 1) {
/*  940 */                 paramMatrix4d.m13 = this.values[1][3];
/*  941 */                 if (this.nRow > 2) {
/*  942 */                   paramMatrix4d.m23 = this.values[2][3];
/*  943 */                   if (this.nRow > 3) {
/*  944 */                     paramMatrix4d.m33 = this.values[3][3];
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  954 */       paramMatrix4d.m00 = this.values[0][0];
/*  955 */       paramMatrix4d.m01 = this.values[0][1];
/*  956 */       paramMatrix4d.m02 = this.values[0][2];
/*  957 */       paramMatrix4d.m03 = this.values[0][3];
/*      */       
/*  959 */       paramMatrix4d.m10 = this.values[1][0];
/*  960 */       paramMatrix4d.m11 = this.values[1][1];
/*  961 */       paramMatrix4d.m12 = this.values[1][2];
/*  962 */       paramMatrix4d.m13 = this.values[1][3];
/*      */       
/*  964 */       paramMatrix4d.m20 = this.values[2][0];
/*  965 */       paramMatrix4d.m21 = this.values[2][1];
/*  966 */       paramMatrix4d.m22 = this.values[2][2];
/*  967 */       paramMatrix4d.m23 = this.values[2][3];
/*      */       
/*  969 */       paramMatrix4d.m30 = this.values[3][0];
/*  970 */       paramMatrix4d.m31 = this.values[3][1];
/*  971 */       paramMatrix4d.m32 = this.values[3][2];
/*  972 */       paramMatrix4d.m33 = this.values[3][3];
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
/*      */   public final void get(Matrix4f paramMatrix4f) {
/*  985 */     if (this.nRow < 4 || this.nCol < 4) {
/*  986 */       paramMatrix4f.setZero();
/*  987 */       if (this.nCol > 0) {
/*  988 */         if (this.nRow > 0) {
/*  989 */           paramMatrix4f.m00 = (float)this.values[0][0];
/*  990 */           if (this.nRow > 1) {
/*  991 */             paramMatrix4f.m10 = (float)this.values[1][0];
/*  992 */             if (this.nRow > 2) {
/*  993 */               paramMatrix4f.m20 = (float)this.values[2][0];
/*  994 */               if (this.nRow > 3) {
/*  995 */                 paramMatrix4f.m30 = (float)this.values[3][0];
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/* 1000 */         if (this.nCol > 1) {
/* 1001 */           if (this.nRow > 0) {
/* 1002 */             paramMatrix4f.m01 = (float)this.values[0][1];
/* 1003 */             if (this.nRow > 1) {
/* 1004 */               paramMatrix4f.m11 = (float)this.values[1][1];
/* 1005 */               if (this.nRow > 2) {
/* 1006 */                 paramMatrix4f.m21 = (float)this.values[2][1];
/* 1007 */                 if (this.nRow > 3) {
/* 1008 */                   paramMatrix4f.m31 = (float)this.values[3][1];
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/* 1013 */           if (this.nCol > 2) {
/* 1014 */             if (this.nRow > 0) {
/* 1015 */               paramMatrix4f.m02 = (float)this.values[0][2];
/* 1016 */               if (this.nRow > 1) {
/* 1017 */                 paramMatrix4f.m12 = (float)this.values[1][2];
/* 1018 */                 if (this.nRow > 2) {
/* 1019 */                   paramMatrix4f.m22 = (float)this.values[2][2];
/* 1020 */                   if (this.nRow > 3) {
/* 1021 */                     paramMatrix4f.m32 = (float)this.values[3][2];
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1026 */             if (this.nCol > 3 && 
/* 1027 */               this.nRow > 0) {
/* 1028 */               paramMatrix4f.m03 = (float)this.values[0][3];
/* 1029 */               if (this.nRow > 1) {
/* 1030 */                 paramMatrix4f.m13 = (float)this.values[1][3];
/* 1031 */                 if (this.nRow > 2) {
/* 1032 */                   paramMatrix4f.m23 = (float)this.values[2][3];
/* 1033 */                   if (this.nRow > 3) {
/* 1034 */                     paramMatrix4f.m33 = (float)this.values[3][3];
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 1044 */       paramMatrix4f.m00 = (float)this.values[0][0];
/* 1045 */       paramMatrix4f.m01 = (float)this.values[0][1];
/* 1046 */       paramMatrix4f.m02 = (float)this.values[0][2];
/* 1047 */       paramMatrix4f.m03 = (float)this.values[0][3];
/*      */       
/* 1049 */       paramMatrix4f.m10 = (float)this.values[1][0];
/* 1050 */       paramMatrix4f.m11 = (float)this.values[1][1];
/* 1051 */       paramMatrix4f.m12 = (float)this.values[1][2];
/* 1052 */       paramMatrix4f.m13 = (float)this.values[1][3];
/*      */       
/* 1054 */       paramMatrix4f.m20 = (float)this.values[2][0];
/* 1055 */       paramMatrix4f.m21 = (float)this.values[2][1];
/* 1056 */       paramMatrix4f.m22 = (float)this.values[2][2];
/* 1057 */       paramMatrix4f.m23 = (float)this.values[2][3];
/*      */       
/* 1059 */       paramMatrix4f.m30 = (float)this.values[3][0];
/* 1060 */       paramMatrix4f.m31 = (float)this.values[3][1];
/* 1061 */       paramMatrix4f.m32 = (float)this.values[3][2];
/* 1062 */       paramMatrix4f.m33 = (float)this.values[3][3];
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(GMatrix paramGMatrix) {
/*      */     int m;
/*      */     int k;
/* 1075 */     if (this.nCol < paramGMatrix.nCol) {
/* 1076 */       k = this.nCol;
/*      */     } else {
/* 1078 */       k = paramGMatrix.nCol;
/*      */     } 
/* 1080 */     if (this.nRow < paramGMatrix.nRow) {
/* 1081 */       m = this.nRow;
/*      */     } else {
/* 1083 */       m = paramGMatrix.nRow;
/*      */     }  int i;
/* 1085 */     for (i = 0; i < m; i++) {
/* 1086 */       for (byte b = 0; b < k; b++) {
/* 1087 */         paramGMatrix.values[i][b] = this.values[i][b];
/*      */       }
/*      */     } 
/* 1090 */     for (i = m; i < paramGMatrix.nRow; i++) {
/* 1091 */       for (byte b = 0; b < paramGMatrix.nCol; b++) {
/* 1092 */         paramGMatrix.values[i][b] = 0.0D;
/*      */       }
/*      */     } 
/* 1095 */     for (int j = k; j < paramGMatrix.nCol; j++) {
/* 1096 */       for (i = 0; i < m; i++) {
/* 1097 */         paramGMatrix.values[i][j] = 0.0D;
/*      */       }
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
/*      */   public final void setRow(int paramInt, double[] paramArrayOfDouble) {
/* 1111 */     for (byte b = 0; b < this.nCol; b++) {
/* 1112 */       this.values[paramInt][b] = paramArrayOfDouble[b];
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
/*      */   public final void setRow(int paramInt, GVector paramGVector) {
/* 1125 */     for (byte b = 0; b < this.nCol; b++) {
/* 1126 */       this.values[paramInt][b] = paramGVector.values[b];
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
/*      */   public final void setColumn(int paramInt, double[] paramArrayOfDouble) {
/* 1139 */     for (byte b = 0; b < this.nRow; b++) {
/* 1140 */       this.values[b][paramInt] = paramArrayOfDouble[b];
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
/*      */   public final void setColumn(int paramInt, GVector paramGVector) {
/* 1153 */     for (byte b = 0; b < this.nRow; b++) {
/* 1154 */       this.values[b][paramInt] = paramGVector.values[b];
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
/*      */   public final void mulTransposeBoth(GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/* 1169 */     if (paramGMatrix1.nRow != paramGMatrix2.nCol || this.nRow != paramGMatrix1.nCol || this.nCol != paramGMatrix2.nRow) {
/* 1170 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix14"));
/*      */     }
/*      */     
/* 1173 */     if (paramGMatrix1 == this || paramGMatrix2 == this) {
/* 1174 */       double[][] arrayOfDouble = new double[this.nRow][this.nCol];
/* 1175 */       for (byte b = 0; b < this.nRow; b++) {
/* 1176 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1177 */           arrayOfDouble[b][b1] = 0.0D;
/* 1178 */           for (byte b2 = 0; b2 < paramGMatrix1.nRow; b2++) {
/* 1179 */             arrayOfDouble[b][b1] = arrayOfDouble[b][b1] + paramGMatrix1.values[b2][b] * paramGMatrix2.values[b1][b2];
/*      */           }
/*      */         } 
/*      */       } 
/* 1183 */       this.values = arrayOfDouble;
/*      */     } else {
/* 1185 */       for (byte b = 0; b < this.nRow; b++) {
/* 1186 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1187 */           this.values[b][b1] = 0.0D;
/* 1188 */           for (byte b2 = 0; b2 < paramGMatrix1.nRow; b2++) {
/* 1189 */             this.values[b][b1] = this.values[b][b1] + paramGMatrix1.values[b2][b] * paramGMatrix2.values[b1][b2];
/*      */           }
/*      */         } 
/*      */       } 
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
/*      */   public final void mulTransposeRight(GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/* 1206 */     if (paramGMatrix1.nCol != paramGMatrix2.nCol || this.nCol != paramGMatrix2.nRow || this.nRow != paramGMatrix1.nRow) {
/* 1207 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix15"));
/*      */     }
/*      */     
/* 1210 */     if (paramGMatrix1 == this || paramGMatrix2 == this) {
/* 1211 */       double[][] arrayOfDouble = new double[this.nRow][this.nCol];
/* 1212 */       for (byte b = 0; b < this.nRow; b++) {
/* 1213 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1214 */           arrayOfDouble[b][b1] = 0.0D;
/* 1215 */           for (byte b2 = 0; b2 < paramGMatrix1.nCol; b2++) {
/* 1216 */             arrayOfDouble[b][b1] = arrayOfDouble[b][b1] + paramGMatrix1.values[b][b2] * paramGMatrix2.values[b1][b2];
/*      */           }
/*      */         } 
/*      */       } 
/* 1220 */       this.values = arrayOfDouble;
/*      */     } else {
/* 1222 */       for (byte b = 0; b < this.nRow; b++) {
/* 1223 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1224 */           this.values[b][b1] = 0.0D;
/* 1225 */           for (byte b2 = 0; b2 < paramGMatrix1.nCol; b2++) {
/* 1226 */             this.values[b][b1] = this.values[b][b1] + paramGMatrix1.values[b][b2] * paramGMatrix2.values[b1][b2];
/*      */           }
/*      */         } 
/*      */       } 
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
/*      */   public final void mulTransposeLeft(GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/* 1245 */     if (paramGMatrix1.nRow != paramGMatrix2.nRow || this.nCol != paramGMatrix2.nCol || this.nRow != paramGMatrix1.nCol) {
/* 1246 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix16"));
/*      */     }
/*      */     
/* 1249 */     if (paramGMatrix1 == this || paramGMatrix2 == this) {
/* 1250 */       double[][] arrayOfDouble = new double[this.nRow][this.nCol];
/* 1251 */       for (byte b = 0; b < this.nRow; b++) {
/* 1252 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1253 */           arrayOfDouble[b][b1] = 0.0D;
/* 1254 */           for (byte b2 = 0; b2 < paramGMatrix1.nRow; b2++) {
/* 1255 */             arrayOfDouble[b][b1] = arrayOfDouble[b][b1] + paramGMatrix1.values[b2][b] * paramGMatrix2.values[b2][b1];
/*      */           }
/*      */         } 
/*      */       } 
/* 1259 */       this.values = arrayOfDouble;
/*      */     } else {
/* 1261 */       for (byte b = 0; b < this.nRow; b++) {
/* 1262 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1263 */           this.values[b][b1] = 0.0D;
/* 1264 */           for (byte b2 = 0; b2 < paramGMatrix1.nRow; b2++) {
/* 1265 */             this.values[b][b1] = this.values[b][b1] + paramGMatrix1.values[b2][b] * paramGMatrix2.values[b2][b1];
/*      */           }
/*      */         } 
/*      */       } 
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
/*      */   public final void transpose() {
/* 1280 */     if (this.nRow != this.nCol) {
/*      */       
/* 1282 */       int i = this.nRow;
/* 1283 */       this.nRow = this.nCol;
/* 1284 */       this.nCol = i;
/* 1285 */       double[][] arrayOfDouble = new double[this.nRow][this.nCol];
/* 1286 */       for (i = 0; i < this.nRow; i++) {
/* 1287 */         for (byte b = 0; b < this.nCol; b++) {
/* 1288 */           arrayOfDouble[i][b] = this.values[b][i];
/*      */         }
/*      */       } 
/* 1291 */       this.values = arrayOfDouble;
/*      */     } else {
/*      */       
/* 1294 */       for (byte b = 0; b < this.nRow; b++) {
/* 1295 */         for (byte b1 = 0; b1 < b; b1++) {
/* 1296 */           double d = this.values[b][b1];
/* 1297 */           this.values[b][b1] = this.values[b1][b];
/* 1298 */           this.values[b1][b] = d;
/*      */         } 
/*      */       } 
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
/*      */   public final void transpose(GMatrix paramGMatrix) {
/* 1312 */     if (this.nRow != paramGMatrix.nCol || this.nCol != paramGMatrix.nRow) {
/* 1313 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix17"));
/*      */     }
/*      */     
/* 1316 */     if (paramGMatrix != this) {
/* 1317 */       for (byte b = 0; b < this.nRow; b++) {
/* 1318 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1319 */           this.values[b][b1] = paramGMatrix.values[b1][b];
/*      */         }
/*      */       } 
/*      */     } else {
/* 1323 */       transpose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1333 */     StringBuffer stringBuffer = new StringBuffer(this.nRow * this.nCol * 8);
/*      */ 
/*      */ 
/*      */     
/* 1337 */     for (byte b = 0; b < this.nRow; b++) {
/* 1338 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1339 */         stringBuffer.append(this.values[b][b1]).append(" ");
/*      */       }
/* 1341 */       stringBuffer.append("\n");
/*      */     } 
/*      */     
/* 1344 */     return stringBuffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkMatrix(GMatrix paramGMatrix) {
/* 1351 */     for (byte b = 0; b < paramGMatrix.nRow; b++) {
/* 1352 */       for (byte b1 = 0; b1 < paramGMatrix.nCol; b1++) {
/* 1353 */         if (Math.abs(paramGMatrix.values[b][b1]) < 1.0E-10D) {
/* 1354 */           System.out.print(" 0.0     ");
/*      */         } else {
/* 1356 */           System.out.print(" " + paramGMatrix.values[b][b1]);
/*      */         } 
/*      */       } 
/* 1359 */       System.out.print("\n");
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
/*      */   public int hashCode() {
/* 1374 */     long l = 1L;
/*      */     
/* 1376 */     l = 31L * l + this.nRow;
/* 1377 */     l = 31L * l + this.nCol;
/*      */     
/* 1379 */     for (byte b = 0; b < this.nRow; b++) {
/* 1380 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1381 */         l = 31L * l + VecMathUtil.doubleToLongBits(this.values[b][b1]);
/*      */       }
/*      */     } 
/*      */     
/* 1385 */     return (int)(l ^ l >> 32);
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
/*      */   public boolean equals(GMatrix paramGMatrix) {
/*      */     try {
/* 1400 */       if (this.nRow != paramGMatrix.nRow || this.nCol != paramGMatrix.nCol) {
/* 1401 */         return false;
/*      */       }
/* 1403 */       for (byte b = 0; b < this.nRow; b++) {
/* 1404 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1405 */           if (this.values[b][b1] != paramGMatrix.values[b][b1])
/* 1406 */             return false; 
/*      */         } 
/*      */       } 
/* 1409 */       return true;
/*      */     }
/* 1411 */     catch (NullPointerException nullPointerException) {
/* 1412 */       return false;
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
/*      */     try {
/* 1426 */       GMatrix gMatrix = (GMatrix)paramObject;
/*      */       
/* 1428 */       if (this.nRow != gMatrix.nRow || this.nCol != gMatrix.nCol) {
/* 1429 */         return false;
/*      */       }
/* 1431 */       for (byte b = 0; b < this.nRow; b++) {
/* 1432 */         for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1433 */           if (this.values[b][b1] != gMatrix.values[b][b1])
/* 1434 */             return false; 
/*      */         } 
/*      */       } 
/* 1437 */       return true;
/*      */     }
/* 1439 */     catch (ClassCastException classCastException) {
/* 1440 */       return false;
/*      */     }
/* 1442 */     catch (NullPointerException nullPointerException) {
/* 1443 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1451 */   public boolean epsilonEquals(GMatrix paramGMatrix, float paramFloat) { return epsilonEquals(paramGMatrix, paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean epsilonEquals(GMatrix paramGMatrix, double paramDouble) {
/* 1467 */     if (this.nRow != paramGMatrix.nRow || this.nCol != paramGMatrix.nCol) {
/* 1468 */       return false;
/*      */     }
/* 1470 */     for (byte b = 0; b < this.nRow; b++) {
/* 1471 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1472 */         double d = this.values[b][b1] - paramGMatrix.values[b][b1];
/* 1473 */         if (((d < 0.0D) ? -d : d) > paramDouble)
/* 1474 */           return false; 
/*      */       } 
/*      */     } 
/* 1477 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double trace() {
/*      */     int i;
/* 1489 */     if (this.nRow < this.nCol) {
/* 1490 */       i = this.nRow;
/*      */     } else {
/* 1492 */       i = this.nCol;
/*      */     } 
/* 1494 */     double d = 0.0D;
/* 1495 */     for (byte b = 0; b < i; b++) {
/* 1496 */       d += this.values[b][b];
/*      */     }
/* 1498 */     return d;
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
/*      */   public final int SVD(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3) {
/* 1520 */     if (this.nCol != paramGMatrix3.nCol || this.nCol != paramGMatrix3.nRow) {
/* 1521 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix18"));
/*      */     }
/*      */ 
/*      */     
/* 1525 */     if (this.nRow != paramGMatrix1.nRow || this.nRow != paramGMatrix1.nCol) {
/* 1526 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix25"));
/*      */     }
/*      */ 
/*      */     
/* 1530 */     if (this.nRow != paramGMatrix2.nRow || this.nCol != paramGMatrix2.nCol) {
/* 1531 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix26"));
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
/* 1545 */     if (this.nRow == 2 && this.nCol == 2 && 
/* 1546 */       this.values[1][0] == 0.0D) {
/* 1547 */       paramGMatrix1.setIdentity();
/* 1548 */       paramGMatrix3.setIdentity();
/*      */       
/* 1550 */       if (this.values[0][1] == 0.0D) {
/* 1551 */         return 2;
/*      */       }
/*      */       
/* 1554 */       double[] arrayOfDouble1 = new double[1];
/* 1555 */       double[] arrayOfDouble2 = new double[1];
/* 1556 */       double[] arrayOfDouble3 = new double[1];
/* 1557 */       double[] arrayOfDouble4 = new double[1];
/* 1558 */       double[] arrayOfDouble5 = new double[2];
/*      */       
/* 1560 */       arrayOfDouble5[0] = this.values[0][0];
/* 1561 */       arrayOfDouble5[1] = this.values[1][1];
/*      */       
/* 1563 */       compute_2X2(this.values[0][0], this.values[0][1], this.values[1][1], arrayOfDouble5, arrayOfDouble1, arrayOfDouble3, arrayOfDouble2, arrayOfDouble4, 0);
/*      */ 
/*      */       
/* 1566 */       update_u(0, paramGMatrix1, arrayOfDouble3, arrayOfDouble1);
/* 1567 */       update_v(0, paramGMatrix3, arrayOfDouble4, arrayOfDouble2);
/*      */       
/* 1569 */       return 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1574 */     return computeSVD(this, paramGMatrix1, paramGMatrix2, paramGMatrix3);
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
/*      */   public final int LUD(GMatrix paramGMatrix, GVector paramGVector) {
/* 1598 */     int i = paramGMatrix.nRow * paramGMatrix.nCol;
/* 1599 */     double[] arrayOfDouble = new double[i];
/* 1600 */     int[] arrayOfInt1 = new int[1];
/* 1601 */     int[] arrayOfInt2 = new int[paramGMatrix.nRow];
/*      */ 
/*      */     
/* 1604 */     if (this.nRow != this.nCol) {
/* 1605 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix19"));
/*      */     }
/*      */ 
/*      */     
/* 1609 */     if (this.nRow != paramGMatrix.nRow) {
/* 1610 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
/*      */     }
/*      */ 
/*      */     
/* 1614 */     if (this.nCol != paramGMatrix.nCol) {
/* 1615 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
/*      */     }
/*      */ 
/*      */     
/* 1619 */     if (paramGMatrix.nRow != paramGVector.getSize()) {
/* 1620 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix20"));
/*      */     }
/*      */     
/*      */     int j;
/* 1624 */     for (j = 0; j < this.nRow; j++) {
/* 1625 */       for (int k = 0; k < this.nCol; k++) {
/* 1626 */         arrayOfDouble[j * this.nCol + k] = this.values[j][k];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1631 */     if (!luDecomposition(paramGMatrix.nRow, arrayOfDouble, arrayOfInt2, arrayOfInt1))
/*      */     {
/* 1633 */       throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
/*      */     }
/*      */ 
/*      */     
/* 1637 */     for (j = 0; j < this.nRow; j++) {
/* 1638 */       for (int k = 0; k < this.nCol; k++) {
/* 1639 */         paramGMatrix.values[j][k] = arrayOfDouble[j * this.nCol + k];
/*      */       }
/*      */     } 
/*      */     
/* 1643 */     for (j = 0; j < paramGMatrix.nRow; j++) {
/* 1644 */       paramGVector.values[j] = arrayOfInt2[j];
/*      */     }
/*      */     
/* 1647 */     return arrayOfInt1[0];
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
/*      */     int i;
/* 1659 */     if (this.nRow < this.nCol) {
/* 1660 */       i = this.nRow;
/*      */     } else {
/* 1662 */       i = this.nCol;
/*      */     }  byte b;
/* 1664 */     for (b = 0; b < this.nRow; b++) {
/* 1665 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 1666 */         this.values[b][b1] = 0.0D;
/*      */       }
/*      */     } 
/*      */     
/* 1670 */     for (b = 0; b < i; b++) {
/* 1671 */       this.values[b][b] = paramDouble;
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
/*      */   final void invertGeneral(GMatrix paramGMatrix) {
/* 1684 */     int i = paramGMatrix.nRow * paramGMatrix.nCol;
/* 1685 */     double[] arrayOfDouble1 = new double[i];
/* 1686 */     double[] arrayOfDouble2 = new double[i];
/* 1687 */     int[] arrayOfInt1 = new int[paramGMatrix.nRow];
/* 1688 */     int[] arrayOfInt2 = new int[1];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1693 */     if (paramGMatrix.nRow != paramGMatrix.nCol)
/*      */     {
/* 1695 */       throw new MismatchedSizeException(VecMathI18N.getString("GMatrix22"));
/*      */     }
/*      */     
/*      */     int j;
/*      */     
/* 1700 */     for (j = 0; j < this.nRow; j++) {
/* 1701 */       for (int k = 0; k < this.nCol; k++) {
/* 1702 */         arrayOfDouble1[j * this.nCol + k] = paramGMatrix.values[j][k];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1707 */     if (!luDecomposition(paramGMatrix.nRow, arrayOfDouble1, arrayOfInt1, arrayOfInt2))
/*      */     {
/* 1709 */       throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1714 */     for (j = 0; j < i; j++) {
/* 1715 */       arrayOfDouble2[j] = 0.0D;
/*      */     }
/* 1717 */     for (j = 0; j < this.nCol; j++) {
/* 1718 */       arrayOfDouble2[j + j * this.nCol] = 1.0D;
/*      */     }
/* 1720 */     luBacksubstitution(paramGMatrix.nRow, arrayOfDouble1, arrayOfInt1, arrayOfDouble2);
/*      */     
/* 1722 */     for (j = 0; j < this.nRow; j++) {
/* 1723 */       for (int k = 0; k < this.nCol; k++) {
/* 1724 */         this.values[j][k] = arrayOfDouble2[j * this.nCol + k];
/*      */       }
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
/*      */   static boolean luDecomposition(int paramInt, double[] paramArrayOfDouble, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 1749 */     double[] arrayOfDouble = new double[paramInt];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1756 */     byte b1 = 0;
/* 1757 */     byte b2 = 0;
/* 1758 */     paramArrayOfInt2[0] = 1;
/*      */ 
/*      */     
/* 1761 */     int i = paramInt;
/* 1762 */     while (i-- != 0) {
/* 1763 */       double d = 0.0D;
/*      */ 
/*      */       
/* 1766 */       int m = paramInt;
/* 1767 */       while (m-- != 0) {
/* 1768 */         double d1 = paramArrayOfDouble[b1++];
/* 1769 */         d1 = Math.abs(d1);
/* 1770 */         if (d1 > d) {
/* 1771 */           d = d1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1776 */       if (d == 0.0D) {
/* 1777 */         return false;
/*      */       }
/* 1779 */       arrayOfDouble[b2++] = 1.0D / d;
/*      */     } 
/*      */ 
/*      */     
/* 1783 */     int k = 0;
/* 1784 */     for (int j = 0; j < paramInt; j++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1790 */       for (i = 0; i < j; i++) {
/* 1791 */         int i1 = k + paramInt * i + j;
/* 1792 */         double d1 = paramArrayOfDouble[i1];
/* 1793 */         int n = i;
/* 1794 */         int i2 = k + paramInt * i;
/* 1795 */         int i3 = k + j;
/* 1796 */         while (n-- != 0) {
/* 1797 */           d1 -= paramArrayOfDouble[i2] * paramArrayOfDouble[i3];
/* 1798 */           i2++;
/* 1799 */           i3 += paramInt;
/*      */         } 
/* 1801 */         paramArrayOfDouble[i1] = d1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1806 */       double d = 0.0D;
/* 1807 */       int m = -1;
/* 1808 */       for (i = j; i < paramInt; i++) {
/* 1809 */         int i1 = k + paramInt * i + j;
/* 1810 */         double d2 = paramArrayOfDouble[i1];
/* 1811 */         int n = j;
/* 1812 */         int i2 = k + paramInt * i;
/* 1813 */         int i3 = k + j;
/* 1814 */         while (n-- != 0) {
/* 1815 */           d2 -= paramArrayOfDouble[i2] * paramArrayOfDouble[i3];
/* 1816 */           i2++;
/* 1817 */           i3 += paramInt;
/*      */         } 
/* 1819 */         paramArrayOfDouble[i1] = d2;
/*      */         
/*      */         double d1;
/* 1822 */         if ((d1 = arrayOfDouble[i] * Math.abs(d2)) >= d) {
/* 1823 */           d = d1;
/* 1824 */           m = i;
/*      */         } 
/*      */       } 
/*      */       
/* 1828 */       if (m < 0) {
/* 1829 */         throw new RuntimeException(VecMathI18N.getString("GMatrix24"));
/*      */       }
/*      */ 
/*      */       
/* 1833 */       if (j != m) {
/*      */         
/* 1835 */         int n = paramInt;
/* 1836 */         int i1 = k + paramInt * m;
/* 1837 */         int i2 = k + paramInt * j;
/* 1838 */         while (n-- != 0) {
/* 1839 */           double d1 = paramArrayOfDouble[i1];
/* 1840 */           paramArrayOfDouble[i1++] = paramArrayOfDouble[i2];
/* 1841 */           paramArrayOfDouble[i2++] = d1;
/*      */         } 
/*      */ 
/*      */         
/* 1845 */         arrayOfDouble[m] = arrayOfDouble[j];
/* 1846 */         paramArrayOfInt2[0] = -paramArrayOfInt2[0];
/*      */       } 
/*      */ 
/*      */       
/* 1850 */       paramArrayOfInt1[j] = m;
/*      */ 
/*      */       
/* 1853 */       if (paramArrayOfDouble[k + paramInt * j + j] == 0.0D) {
/* 1854 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1858 */       if (j != paramInt - 1) {
/* 1859 */         double d1 = 1.0D / paramArrayOfDouble[k + paramInt * j + j];
/* 1860 */         int n = k + paramInt * (j + 1) + j;
/* 1861 */         i = paramInt - 1 - j;
/* 1862 */         while (i-- != 0) {
/* 1863 */           paramArrayOfDouble[n] = paramArrayOfDouble[n] * d1;
/* 1864 */           n += paramInt;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1870 */     return true;
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
/*      */   static void luBacksubstitution(int paramInt, double[] paramArrayOfDouble1, int[] paramArrayOfInt, double[] paramArrayOfDouble2) {
/* 1901 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1904 */     for (byte b1 = 0; b1 < paramInt; b1++) {
/*      */       
/* 1906 */       int k = b1;
/* 1907 */       int j = -1;
/*      */       
/*      */       int i;
/* 1910 */       for (i = 0; i < paramInt; i++) {
/*      */ 
/*      */         
/* 1913 */         int m = paramArrayOfInt[b2 + i];
/* 1914 */         double d = paramArrayOfDouble2[k + paramInt * m];
/* 1915 */         paramArrayOfDouble2[k + paramInt * m] = paramArrayOfDouble2[k + paramInt * i];
/* 1916 */         if (j >= 0) {
/*      */           
/* 1918 */           int i1 = i * paramInt;
/* 1919 */           for (int n = j; n <= i - 1; n++) {
/* 1920 */             d -= paramArrayOfDouble1[i1 + n] * paramArrayOfDouble2[k + paramInt * n];
/*      */           }
/*      */         }
/* 1923 */         else if (d != 0.0D) {
/* 1924 */           j = i;
/*      */         } 
/* 1926 */         paramArrayOfDouble2[k + paramInt * i] = d;
/*      */       } 
/*      */ 
/*      */       
/* 1930 */       for (i = 0; i < paramInt; i++) {
/* 1931 */         int i1 = paramInt - 1 - i;
/* 1932 */         int n = paramInt * i1;
/* 1933 */         double d = 0.0D;
/* 1934 */         for (int m = 1; m <= i; m++) {
/* 1935 */           d += paramArrayOfDouble1[n + paramInt - m] * paramArrayOfDouble2[k + paramInt * (paramInt - m)];
/*      */         }
/* 1937 */         paramArrayOfDouble2[k + paramInt * i1] = (paramArrayOfDouble2[k + paramInt * i1] - d) / paramArrayOfDouble1[n + i1];
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int computeSVD(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3, GMatrix paramGMatrix4) {
/*      */     int n, m, k;
/* 1950 */     GMatrix gMatrix1 = new GMatrix(paramGMatrix1.nRow, paramGMatrix1.nCol);
/* 1951 */     GMatrix gMatrix2 = new GMatrix(paramGMatrix1.nRow, paramGMatrix1.nCol);
/* 1952 */     GMatrix gMatrix3 = new GMatrix(paramGMatrix1.nRow, paramGMatrix1.nCol);
/* 1953 */     GMatrix gMatrix4 = new GMatrix(paramGMatrix1);
/*      */ 
/*      */     
/* 1956 */     if (gMatrix4.nRow >= gMatrix4.nCol) {
/* 1957 */       m = gMatrix4.nCol;
/* 1958 */       k = gMatrix4.nCol - 1;
/*      */     } else {
/* 1960 */       m = gMatrix4.nRow;
/* 1961 */       k = gMatrix4.nRow;
/*      */     } 
/*      */     
/* 1964 */     if (gMatrix4.nRow > gMatrix4.nCol) {
/* 1965 */       n = gMatrix4.nRow;
/*      */     } else {
/* 1967 */       n = gMatrix4.nCol;
/*      */     } 
/* 1969 */     double[] arrayOfDouble1 = new double[n];
/* 1970 */     double[] arrayOfDouble2 = new double[m];
/* 1971 */     double[] arrayOfDouble3 = new double[k];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1977 */     null = 0;
/*      */     
/* 1979 */     paramGMatrix2.setIdentity();
/* 1980 */     paramGMatrix4.setIdentity();
/*      */     
/* 1982 */     int i = gMatrix4.nRow;
/* 1983 */     int j = gMatrix4.nCol;
/*      */ 
/*      */     
/* 1986 */     for (byte b2 = 0; b2 < m; b2++) {
/*      */ 
/*      */       
/* 1989 */       if (i > 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1996 */         double d1 = 0.0D; byte b3;
/* 1997 */         for (b3 = 0; b3 < i; b3++) {
/* 1998 */           d1 += gMatrix4.values[b3 + b2][b2] * gMatrix4.values[b3 + b2][b2];
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2005 */         d1 = Math.sqrt(d1);
/* 2006 */         if (gMatrix4.values[b2][b2] == 0.0D) {
/* 2007 */           arrayOfDouble1[0] = d1;
/*      */         } else {
/* 2009 */           arrayOfDouble1[0] = gMatrix4.values[b2][b2] + d_sign(d1, gMatrix4.values[b2][b2]);
/*      */         } 
/*      */         
/* 2012 */         for (b3 = 1; b3 < i; b3++) {
/* 2013 */           arrayOfDouble1[b3] = gMatrix4.values[b2 + b3][b2];
/*      */         }
/*      */         
/* 2016 */         double d2 = 0.0D;
/* 2017 */         for (b3 = 0; b3 < i; b3++)
/*      */         {
/*      */ 
/*      */           
/* 2021 */           d2 += arrayOfDouble1[b3] * arrayOfDouble1[b3];
/*      */         }
/*      */         
/* 2024 */         d2 = 2.0D / d2;
/*      */         
/*      */         byte b4;
/*      */         
/* 2028 */         for (b4 = b2; b4 < gMatrix4.nRow; b4++) {
/* 2029 */           for (byte b = b2; b < gMatrix4.nRow; b++) {
/* 2030 */             gMatrix2.values[b4][b] = -d2 * arrayOfDouble1[b4 - b2] * arrayOfDouble1[b - b2];
/*      */           }
/*      */         } 
/*      */         
/* 2034 */         for (b3 = b2; b3 < gMatrix4.nRow; b3++) {
/* 2035 */           gMatrix2.values[b3][b3] = gMatrix2.values[b3][b3] + 1.0D;
/*      */         }
/*      */ 
/*      */         
/* 2039 */         double d3 = 0.0D;
/* 2040 */         for (b3 = b2; b3 < gMatrix4.nRow; b3++) {
/* 2041 */           d3 += gMatrix2.values[b2][b3] * gMatrix4.values[b3][b2];
/*      */         }
/* 2043 */         gMatrix4.values[b2][b2] = d3;
/*      */ 
/*      */         
/* 2046 */         for (b4 = b2; b4 < gMatrix4.nRow; b4++) {
/* 2047 */           for (byte b = b2 + 1; b < gMatrix4.nCol; b++) {
/* 2048 */             gMatrix1.values[b4][b] = 0.0D;
/* 2049 */             for (b3 = b2; b3 < gMatrix4.nCol; b3++) {
/* 2050 */               gMatrix1.values[b4][b] = gMatrix1.values[b4][b] + gMatrix2.values[b4][b3] * gMatrix4.values[b3][b];
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 2055 */         for (b4 = b2; b4 < gMatrix4.nRow; b4++) {
/* 2056 */           for (byte b = b2 + 1; b < gMatrix4.nCol; b++) {
/* 2057 */             gMatrix4.values[b4][b] = gMatrix1.values[b4][b];
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2067 */         for (b4 = b2; b4 < gMatrix4.nRow; b4++) {
/* 2068 */           for (byte b = 0; b < gMatrix4.nCol; b++) {
/* 2069 */             gMatrix1.values[b4][b] = 0.0D;
/* 2070 */             for (b3 = b2; b3 < gMatrix4.nCol; b3++) {
/* 2071 */               gMatrix1.values[b4][b] = gMatrix1.values[b4][b] + gMatrix2.values[b4][b3] * paramGMatrix2.values[b3][b];
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 2076 */         for (b4 = b2; b4 < gMatrix4.nRow; b4++) {
/* 2077 */           for (byte b = 0; b < gMatrix4.nCol; b++) {
/* 2078 */             paramGMatrix2.values[b4][b] = gMatrix1.values[b4][b];
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2089 */         i--;
/*      */       } 
/*      */       
/* 2092 */       if (j > 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2098 */         double d1 = 0.0D; byte b3;
/* 2099 */         for (b3 = 1; b3 < j; b3++) {
/* 2100 */           d1 += gMatrix4.values[b2][b2 + b3] * gMatrix4.values[b2][b2 + b3];
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2108 */         d1 = Math.sqrt(d1);
/* 2109 */         if (gMatrix4.values[b2][b2 + 1] == 0.0D) {
/* 2110 */           arrayOfDouble1[0] = d1;
/*      */         } else {
/* 2112 */           arrayOfDouble1[0] = gMatrix4.values[b2][b2 + 1] + d_sign(d1, gMatrix4.values[b2][b2 + 1]);
/*      */         } 
/*      */ 
/*      */         
/* 2116 */         for (b3 = 1; b3 < j - 1; b3++) {
/* 2117 */           arrayOfDouble1[b3] = gMatrix4.values[b2][b2 + b3 + 1];
/*      */         }
/*      */ 
/*      */         
/* 2121 */         double d2 = 0.0D;
/* 2122 */         for (b3 = 0; b3 < j - 1; b3++)
/*      */         {
/* 2124 */           d2 += arrayOfDouble1[b3] * arrayOfDouble1[b3];
/*      */         }
/*      */         
/* 2127 */         d2 = 2.0D / d2;
/*      */         
/*      */         byte b4;
/*      */         
/* 2131 */         for (b4 = b2 + 1; b4 < j; b4++) {
/* 2132 */           for (byte b = b2 + 1; b < gMatrix4.nCol; b++) {
/* 2133 */             gMatrix3.values[b4][b] = -d2 * arrayOfDouble1[b4 - b2 - 1] * arrayOfDouble1[b - b2 - 1];
/*      */           }
/*      */         } 
/*      */         
/* 2137 */         for (b3 = b2 + 1; b3 < gMatrix4.nCol; b3++) {
/* 2138 */           gMatrix3.values[b3][b3] = gMatrix3.values[b3][b3] + 1.0D;
/*      */         }
/*      */         
/* 2141 */         double d3 = 0.0D;
/* 2142 */         for (b3 = b2; b3 < gMatrix4.nCol; b3++) {
/* 2143 */           d3 += gMatrix3.values[b3][b2 + 1] * gMatrix4.values[b2][b3];
/*      */         }
/* 2145 */         gMatrix4.values[b2][b2 + 1] = d3;
/*      */ 
/*      */         
/* 2148 */         for (b4 = b2 + 1; b4 < gMatrix4.nRow; b4++) {
/* 2149 */           for (byte b = b2 + 1; b < gMatrix4.nCol; b++) {
/* 2150 */             gMatrix1.values[b4][b] = 0.0D;
/* 2151 */             for (b3 = b2 + 1; b3 < gMatrix4.nCol; b3++) {
/* 2152 */               gMatrix1.values[b4][b] = gMatrix1.values[b4][b] + gMatrix3.values[b3][b] * gMatrix4.values[b4][b3];
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 2157 */         for (b4 = b2 + 1; b4 < gMatrix4.nRow; b4++) {
/* 2158 */           for (byte b = b2 + 1; b < gMatrix4.nCol; b++) {
/* 2159 */             gMatrix4.values[b4][b] = gMatrix1.values[b4][b];
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2170 */         for (b4 = 0; b4 < gMatrix4.nRow; b4++) {
/* 2171 */           for (byte b = b2 + 1; b < gMatrix4.nCol; b++) {
/* 2172 */             gMatrix1.values[b4][b] = 0.0D;
/* 2173 */             for (b3 = b2 + 1; b3 < gMatrix4.nCol; b3++) {
/* 2174 */               gMatrix1.values[b4][b] = gMatrix1.values[b4][b] + gMatrix3.values[b3][b] * paramGMatrix4.values[b4][b3];
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2182 */         for (b4 = 0; b4 < gMatrix4.nRow; b4++) {
/* 2183 */           for (byte b = b2 + 1; b < gMatrix4.nCol; b++) {
/* 2184 */             paramGMatrix4.values[b4][b] = gMatrix1.values[b4][b];
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2193 */         j--;
/*      */       } 
/*      */     } 
/*      */     byte b1;
/* 2197 */     for (b1 = 0; b1 < m; b1++) {
/* 2198 */       arrayOfDouble2[b1] = gMatrix4.values[b1][b1];
/*      */     }
/*      */     
/* 2201 */     for (b1 = 0; b1 < k; b1++) {
/* 2202 */       arrayOfDouble3[b1] = gMatrix4.values[b1][b1 + 1];
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
/* 2215 */     if (gMatrix4.nRow == 2 && gMatrix4.nCol == 2) {
/* 2216 */       double[] arrayOfDouble4 = new double[1];
/* 2217 */       double[] arrayOfDouble5 = new double[1];
/* 2218 */       double[] arrayOfDouble6 = new double[1];
/* 2219 */       double[] arrayOfDouble7 = new double[1];
/*      */       
/* 2221 */       compute_2X2(arrayOfDouble2[0], arrayOfDouble3[0], arrayOfDouble2[1], arrayOfDouble2, arrayOfDouble6, arrayOfDouble4, arrayOfDouble7, arrayOfDouble5, 0);
/*      */ 
/*      */       
/* 2224 */       update_u(0, paramGMatrix2, arrayOfDouble4, arrayOfDouble6);
/* 2225 */       update_v(0, paramGMatrix4, arrayOfDouble5, arrayOfDouble7);
/*      */       
/* 2227 */       return 2;
/*      */     } 
/*      */ 
/*      */     
/* 2231 */     compute_qr(0, arrayOfDouble3.length - 1, arrayOfDouble2, arrayOfDouble3, paramGMatrix2, paramGMatrix4);
/*      */ 
/*      */     
/* 2234 */     return arrayOfDouble2.length;
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
/*      */   static void compute_qr(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/* 2247 */     double[] arrayOfDouble1 = new double[1];
/* 2248 */     double[] arrayOfDouble2 = new double[1];
/* 2249 */     double[] arrayOfDouble3 = new double[1];
/* 2250 */     double[] arrayOfDouble4 = new double[1];
/* 2251 */     GMatrix gMatrix = new GMatrix(paramGMatrix1.nCol, paramGMatrix2.nRow);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2278 */     double d3 = 1.0D;
/* 2279 */     double d4 = -1.0D;
/* 2280 */     boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2285 */     double d1 = 0.0D;
/* 2286 */     double d2 = 0.0D;
/*      */     
/* 2288 */     for (byte b = 0; b < 2 && !bool; b++) {
/* 2289 */       int j; for (j = paramInt1; j <= paramInt2; j++) {
/*      */ 
/*      */         
/* 2292 */         if (j == paramInt1) {
/* 2293 */           int m; if (paramArrayOfDouble2.length == paramArrayOfDouble1.length) {
/* 2294 */             m = paramInt2;
/*      */           } else {
/* 2296 */             m = paramInt2 + 1;
/*      */           } 
/* 2298 */           double d5 = compute_shift(paramArrayOfDouble1[m - 1], paramArrayOfDouble2[paramInt2], paramArrayOfDouble1[m]);
/*      */           
/* 2300 */           d1 = (Math.abs(paramArrayOfDouble1[j]) - d5) * (d_sign(d3, paramArrayOfDouble1[j]) + d5 / paramArrayOfDouble1[j]);
/*      */           
/* 2302 */           d2 = paramArrayOfDouble2[j];
/*      */         } 
/*      */         
/* 2305 */         double d = compute_rot(d1, d2, arrayOfDouble4, arrayOfDouble2);
/* 2306 */         if (j != paramInt1) {
/* 2307 */           paramArrayOfDouble2[j - 1] = d;
/*      */         }
/* 2309 */         d1 = arrayOfDouble2[0] * paramArrayOfDouble1[j] + arrayOfDouble4[0] * paramArrayOfDouble2[j];
/* 2310 */         paramArrayOfDouble2[j] = arrayOfDouble2[0] * paramArrayOfDouble2[j] - arrayOfDouble4[0] * paramArrayOfDouble1[j];
/* 2311 */         d2 = arrayOfDouble4[0] * paramArrayOfDouble1[j + 1];
/* 2312 */         paramArrayOfDouble1[j + 1] = arrayOfDouble2[0] * paramArrayOfDouble1[j + 1];
/*      */ 
/*      */         
/* 2315 */         update_v(j, paramGMatrix2, arrayOfDouble2, arrayOfDouble4);
/*      */ 
/*      */ 
/*      */         
/* 2319 */         d = compute_rot(d1, d2, arrayOfDouble3, arrayOfDouble1);
/* 2320 */         paramArrayOfDouble1[j] = d;
/* 2321 */         d1 = arrayOfDouble1[0] * paramArrayOfDouble2[j] + arrayOfDouble3[0] * paramArrayOfDouble1[j + 1];
/* 2322 */         paramArrayOfDouble1[j + 1] = arrayOfDouble1[0] * paramArrayOfDouble1[j + 1] - arrayOfDouble3[0] * paramArrayOfDouble2[j];
/*      */         
/* 2324 */         if (j < paramInt2) {
/*      */           
/* 2326 */           d2 = arrayOfDouble3[0] * paramArrayOfDouble2[j + 1];
/* 2327 */           paramArrayOfDouble2[j + 1] = arrayOfDouble1[0] * paramArrayOfDouble2[j + 1];
/*      */         } 
/*      */ 
/*      */         
/* 2331 */         update_u(j, paramGMatrix1, arrayOfDouble1, arrayOfDouble3);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2337 */       if (paramArrayOfDouble1.length == paramArrayOfDouble2.length) {
/* 2338 */         double d = compute_rot(d1, d2, arrayOfDouble4, arrayOfDouble2);
/* 2339 */         d1 = arrayOfDouble2[0] * paramArrayOfDouble1[j] + arrayOfDouble4[0] * paramArrayOfDouble2[j];
/* 2340 */         paramArrayOfDouble2[j] = arrayOfDouble2[0] * paramArrayOfDouble2[j] - arrayOfDouble4[0] * paramArrayOfDouble1[j];
/* 2341 */         paramArrayOfDouble1[j + 1] = arrayOfDouble2[0] * paramArrayOfDouble1[j + 1];
/*      */         
/* 2343 */         update_v(j, paramGMatrix2, arrayOfDouble2, arrayOfDouble4);
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
/*      */       
/* 2356 */       while (paramInt2 - paramInt1 > 1 && Math.abs(paramArrayOfDouble2[paramInt2]) < 4.89E-15D) {
/* 2357 */         paramInt2--;
/*      */       }
/*      */ 
/*      */       
/* 2361 */       for (int k = paramInt2 - 2; k > paramInt1; k--) {
/* 2362 */         if (Math.abs(paramArrayOfDouble2[k]) < 4.89E-15D) {
/* 2363 */           compute_qr(k + 1, paramInt2, paramArrayOfDouble1, paramArrayOfDouble2, paramGMatrix1, paramGMatrix2);
/* 2364 */           paramInt2 = k - 1;
/*      */ 
/*      */           
/* 2367 */           while (paramInt2 - paramInt1 > 1 && Math.abs(paramArrayOfDouble2[paramInt2]) < 4.89E-15D)
/*      */           {
/* 2369 */             paramInt2--;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2377 */       if (paramInt2 - paramInt1 <= 1 && Math.abs(paramArrayOfDouble2[paramInt1 + 1]) < 4.89E-15D) {
/* 2378 */         bool = true;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2388 */     if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-15D) {
/* 2389 */       compute_2X2(paramArrayOfDouble1[paramInt1], paramArrayOfDouble2[paramInt1], paramArrayOfDouble1[paramInt1 + 1], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 0);
/*      */       
/* 2391 */       paramArrayOfDouble2[paramInt1] = 0.0D;
/* 2392 */       paramArrayOfDouble2[paramInt1 + 1] = 0.0D;
/*      */     } 
/*      */ 
/*      */     
/* 2396 */     int i = paramInt1;
/* 2397 */     update_u(i, paramGMatrix1, arrayOfDouble1, arrayOfDouble3);
/* 2398 */     update_v(i, paramGMatrix2, arrayOfDouble2, arrayOfDouble4);
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
/*      */   private static void print_se(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 2410 */     System.out.println("\ns =" + paramArrayOfDouble1[0] + " " + paramArrayOfDouble1[1] + " " + paramArrayOfDouble1[2]);
/* 2411 */     System.out.println("e =" + paramArrayOfDouble2[0] + " " + paramArrayOfDouble2[1]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void update_v(int paramInt, GMatrix paramGMatrix, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 2419 */     for (byte b = 0; b < paramGMatrix.nRow; b++) {
/* 2420 */       double d = paramGMatrix.values[b][paramInt];
/* 2421 */       paramGMatrix.values[b][paramInt] = paramArrayOfDouble1[0] * d + paramArrayOfDouble2[0] * paramGMatrix.values[b][paramInt + 1];
/*      */       
/* 2423 */       paramGMatrix.values[b][paramInt + 1] = -paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix.values[b][paramInt + 1];
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void chase_up(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt, GMatrix paramGMatrix) {
/* 2430 */     double[] arrayOfDouble1 = new double[1];
/* 2431 */     double[] arrayOfDouble2 = new double[1];
/*      */     
/* 2433 */     GMatrix gMatrix1 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
/* 2434 */     GMatrix gMatrix2 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2446 */     double d1 = paramArrayOfDouble2[paramInt];
/* 2447 */     double d2 = paramArrayOfDouble1[paramInt];
/*      */     int i;
/* 2449 */     for (i = paramInt; i > 0; i--) {
/* 2450 */       double d = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
/* 2451 */       d1 = -paramArrayOfDouble2[i - 1] * arrayOfDouble2[0];
/* 2452 */       d2 = paramArrayOfDouble1[i - 1];
/* 2453 */       paramArrayOfDouble1[i] = d;
/* 2454 */       paramArrayOfDouble2[i - 1] = paramArrayOfDouble2[i - 1] * arrayOfDouble1[0];
/* 2455 */       update_v_split(i, paramInt + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, gMatrix1, gMatrix2);
/*      */     } 
/*      */     
/* 2458 */     paramArrayOfDouble1[i + 1] = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
/* 2459 */     update_v_split(i, paramInt + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, gMatrix1, gMatrix2);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void chase_across(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt, GMatrix paramGMatrix) {
/* 2464 */     double[] arrayOfDouble1 = new double[1];
/* 2465 */     double[] arrayOfDouble2 = new double[1];
/*      */     
/* 2467 */     GMatrix gMatrix1 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
/* 2468 */     GMatrix gMatrix2 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2480 */     double d2 = paramArrayOfDouble2[paramInt];
/* 2481 */     double d1 = paramArrayOfDouble1[paramInt + 1];
/*      */     int i;
/* 2483 */     for (i = paramInt; i < paramGMatrix.nCol - 2; i++) {
/* 2484 */       double d = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
/* 2485 */       d2 = -paramArrayOfDouble2[i + 1] * arrayOfDouble2[0];
/* 2486 */       d1 = paramArrayOfDouble1[i + 2];
/* 2487 */       paramArrayOfDouble1[i + 1] = d;
/* 2488 */       paramArrayOfDouble2[i + 1] = paramArrayOfDouble2[i + 1] * arrayOfDouble1[0];
/* 2489 */       update_u_split(paramInt, i + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, gMatrix1, gMatrix2);
/*      */     } 
/*      */     
/* 2492 */     paramArrayOfDouble1[i + 1] = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
/* 2493 */     update_u_split(paramInt, i + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, gMatrix1, gMatrix2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void update_v_split(int paramInt1, int paramInt2, GMatrix paramGMatrix1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix2, GMatrix paramGMatrix3) {
/* 2502 */     for (byte b = 0; b < paramGMatrix1.nRow; b++) {
/* 2503 */       double d = paramGMatrix1.values[b][paramInt1];
/* 2504 */       paramGMatrix1.values[b][paramInt1] = paramArrayOfDouble1[0] * d - paramArrayOfDouble2[0] * paramGMatrix1.values[b][paramInt2];
/* 2505 */       paramGMatrix1.values[b][paramInt2] = paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix1.values[b][paramInt2];
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
/* 2519 */     System.out.println("topr    =" + paramInt1);
/* 2520 */     System.out.println("bottomr =" + paramInt2);
/* 2521 */     System.out.println("cosr =" + paramArrayOfDouble1[0]);
/* 2522 */     System.out.println("sinr =" + paramArrayOfDouble2[0]);
/* 2523 */     System.out.println("\nm =");
/* 2524 */     checkMatrix(paramGMatrix3);
/* 2525 */     System.out.println("\nv =");
/* 2526 */     checkMatrix(paramGMatrix2);
/* 2527 */     paramGMatrix3.mul(paramGMatrix3, paramGMatrix2);
/* 2528 */     System.out.println("\nt*m =");
/* 2529 */     checkMatrix(paramGMatrix3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void update_u_split(int paramInt1, int paramInt2, GMatrix paramGMatrix1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix2, GMatrix paramGMatrix3) {
/* 2538 */     for (byte b = 0; b < paramGMatrix1.nCol; b++) {
/* 2539 */       double d = paramGMatrix1.values[paramInt1][b];
/* 2540 */       paramGMatrix1.values[paramInt1][b] = paramArrayOfDouble1[0] * d - paramArrayOfDouble2[0] * paramGMatrix1.values[paramInt2][b];
/* 2541 */       paramGMatrix1.values[paramInt2][b] = paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix1.values[paramInt2][b];
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
/* 2554 */     System.out.println("\nm=");
/* 2555 */     checkMatrix(paramGMatrix3);
/* 2556 */     System.out.println("\nu=");
/* 2557 */     checkMatrix(paramGMatrix2);
/* 2558 */     paramGMatrix3.mul(paramGMatrix2, paramGMatrix3);
/* 2559 */     System.out.println("\nt*m=");
/* 2560 */     checkMatrix(paramGMatrix3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void update_u(int paramInt, GMatrix paramGMatrix, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 2568 */     for (byte b = 0; b < paramGMatrix.nCol; b++) {
/* 2569 */       double d = paramGMatrix.values[paramInt][b];
/* 2570 */       paramGMatrix.values[paramInt][b] = paramArrayOfDouble1[0] * d + paramArrayOfDouble2[0] * paramGMatrix.values[paramInt + 1][b];
/*      */       
/* 2572 */       paramGMatrix.values[paramInt + 1][b] = -paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix.values[paramInt + 1][b];
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void print_m(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3) {
/* 2578 */     GMatrix gMatrix = new GMatrix(paramGMatrix1.nCol, paramGMatrix1.nRow);
/*      */     
/* 2580 */     gMatrix.mul(paramGMatrix2, gMatrix);
/* 2581 */     gMatrix.mul(gMatrix, paramGMatrix3);
/* 2582 */     System.out.println("\n m = \n" + gMatrix.toString(gMatrix));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String toString(GMatrix paramGMatrix) {
/* 2588 */     StringBuffer stringBuffer = new StringBuffer(paramGMatrix.nRow * paramGMatrix.nCol * 8);
/*      */ 
/*      */     
/* 2591 */     for (byte b = 0; b < paramGMatrix.nRow; b++) {
/* 2592 */       for (byte b1 = 0; b1 < paramGMatrix.nCol; b1++) {
/* 2593 */         if (Math.abs(paramGMatrix.values[b][b1]) < 1.0E-9D) {
/* 2594 */           stringBuffer.append("0.0000 ");
/*      */         } else {
/* 2596 */           stringBuffer.append(paramGMatrix.values[b][b1]).append(" ");
/*      */         } 
/*      */       } 
/* 2599 */       stringBuffer.append("\n");
/*      */     } 
/* 2601 */     return stringBuffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void print_svd(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix1, GMatrix paramGMatrix2) {
/* 2607 */     GMatrix gMatrix = new GMatrix(paramGMatrix1.nCol, paramGMatrix2.nRow);
/*      */     
/* 2609 */     System.out.println(" \ns = "); byte b;
/* 2610 */     for (b = 0; b < paramArrayOfDouble1.length; b++) {
/* 2611 */       System.out.println(" " + paramArrayOfDouble1[b]);
/*      */     }
/*      */     
/* 2614 */     System.out.println(" \ne = ");
/* 2615 */     for (b = 0; b < paramArrayOfDouble2.length; b++) {
/* 2616 */       System.out.println(" " + paramArrayOfDouble2[b]);
/*      */     }
/*      */     
/* 2619 */     System.out.println(" \nu  = \n" + paramGMatrix1.toString());
/* 2620 */     System.out.println(" \nv  = \n" + paramGMatrix2.toString());
/*      */     
/* 2622 */     gMatrix.setIdentity();
/* 2623 */     for (b = 0; b < paramArrayOfDouble1.length; b++) {
/* 2624 */       gMatrix.values[b][b] = paramArrayOfDouble1[b];
/*      */     }
/* 2626 */     for (b = 0; b < paramArrayOfDouble2.length; b++) {
/* 2627 */       gMatrix.values[b][b + 1] = paramArrayOfDouble2[b];
/*      */     }
/* 2629 */     System.out.println(" \nm  = \n" + gMatrix.toString());
/*      */     
/* 2631 */     gMatrix.mulTransposeLeft(paramGMatrix1, gMatrix);
/* 2632 */     gMatrix.mulTransposeRight(gMatrix, paramGMatrix2);
/*      */     
/* 2634 */     System.out.println(" \n u.transpose*m*v.transpose  = \n" + gMatrix.toString());
/*      */   }
/*      */ 
/*      */   
/*      */   static double max(double paramDouble1, double paramDouble2) {
/* 2639 */     if (paramDouble1 > paramDouble2) {
/* 2640 */       return paramDouble1;
/*      */     }
/* 2642 */     return paramDouble2;
/*      */   }
/*      */   
/*      */   static double min(double paramDouble1, double paramDouble2) {
/* 2646 */     if (paramDouble1 < paramDouble2) {
/* 2647 */       return paramDouble1;
/*      */     }
/* 2649 */     return paramDouble2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static double compute_shift(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 2657 */     double d6, d3 = Math.abs(paramDouble1);
/* 2658 */     double d4 = Math.abs(paramDouble2);
/* 2659 */     double d5 = Math.abs(paramDouble3);
/* 2660 */     double d1 = min(d3, d5);
/* 2661 */     double d2 = max(d3, d5);
/*      */     
/* 2663 */     if (d1 == 0.0D) {
/* 2664 */       d6 = 0.0D;
/* 2665 */       if (d2 != 0.0D)
/*      */       {
/* 2667 */         double d = min(d2, d4) / max(d2, d4);
/*      */       }
/*      */     }
/* 2670 */     else if (d4 < d2) {
/* 2671 */       double d9 = d1 / d2 + 1.0D;
/* 2672 */       double d10 = (d2 - d1) / d2;
/* 2673 */       double d7 = d4 / d2;
/* 2674 */       double d11 = d7 * d7;
/* 2675 */       double d8 = 2.0D / (Math.sqrt(d9 * d9 + d11) + Math.sqrt(d10 * d10 + d11));
/* 2676 */       d6 = d1 * d8;
/*      */     } else {
/* 2678 */       double d = d2 / d4;
/* 2679 */       if (d == 0.0D) {
/* 2680 */         d6 = d1 * d2 / d4;
/*      */       } else {
/* 2682 */         double d10 = d1 / d2 + 1.0D;
/* 2683 */         double d11 = (d2 - d1) / d2;
/* 2684 */         double d7 = d10 * d;
/* 2685 */         double d8 = d11 * d;
/* 2686 */         double d9 = 1.0D / (Math.sqrt(d7 * d7 + 1.0D) + Math.sqrt(d8 * d8 + 1.0D));
/*      */         
/* 2688 */         d6 = d1 * d9 * d;
/* 2689 */         d6 += d6;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2694 */     return d6;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static int compute_2X2(double paramDouble1, double paramDouble2, double paramDouble3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, int paramInt) {
/*      */     boolean bool;
/* 2701 */     double d1 = 2.0D;
/* 2702 */     double d2 = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2714 */     double d15 = paramArrayOfDouble1[0];
/* 2715 */     double d14 = paramArrayOfDouble1[1];
/* 2716 */     double d10 = 0.0D;
/* 2717 */     double d11 = 0.0D;
/* 2718 */     double d12 = 0.0D;
/* 2719 */     double d13 = 0.0D;
/* 2720 */     double d3 = 0.0D;
/*      */     
/* 2722 */     double d7 = paramDouble1;
/* 2723 */     double d4 = Math.abs(d7);
/* 2724 */     double d9 = paramDouble3;
/* 2725 */     double d6 = Math.abs(paramDouble3);
/*      */     
/* 2727 */     byte b = 1;
/* 2728 */     if (d6 > d4) {
/* 2729 */       bool = true;
/*      */     } else {
/* 2731 */       bool = false;
/*      */     } 
/* 2733 */     if (bool) {
/* 2734 */       b = 3;
/* 2735 */       double d = d7;
/* 2736 */       d7 = d9;
/* 2737 */       d9 = d;
/* 2738 */       d = d4;
/* 2739 */       d4 = d6;
/* 2740 */       d6 = d;
/*      */     } 
/*      */ 
/*      */     
/* 2744 */     double d8 = paramDouble2;
/* 2745 */     double d5 = Math.abs(d8);
/* 2746 */     if (d5 == 0.0D) {
/* 2747 */       paramArrayOfDouble1[1] = d6;
/* 2748 */       paramArrayOfDouble1[0] = d4;
/* 2749 */       d10 = 1.0D;
/* 2750 */       d11 = 1.0D;
/* 2751 */       d12 = 0.0D;
/* 2752 */       d13 = 0.0D;
/*      */     } else {
/* 2754 */       boolean bool1 = true;
/* 2755 */       if (d5 > d4) {
/* 2756 */         b = 2;
/* 2757 */         if (d4 / d5 < 1.0E-10D) {
/* 2758 */           bool1 = false;
/* 2759 */           d15 = d5;
/*      */           
/* 2761 */           if (d6 > 1.0D) {
/* 2762 */             d14 = d4 / d5 / d6;
/*      */           } else {
/* 2764 */             d14 = d4 / d5 * d6;
/*      */           } 
/* 2766 */           d10 = 1.0D;
/* 2767 */           d12 = d9 / d8;
/* 2768 */           d13 = 1.0D;
/* 2769 */           d11 = d7 / d8;
/*      */         } 
/*      */       } 
/* 2772 */       if (bool1) {
/* 2773 */         double d20, d18, d17 = d4 - d6;
/* 2774 */         if (d17 == d4) {
/*      */           
/* 2776 */           d18 = 1.0D;
/*      */         } else {
/* 2778 */           d18 = d17 / d4;
/*      */         } 
/*      */         
/* 2781 */         double d19 = d8 / d7;
/* 2782 */         double d22 = 2.0D - d18;
/* 2783 */         double d23 = d19 * d19;
/* 2784 */         double d24 = d22 * d22;
/* 2785 */         double d21 = Math.sqrt(d24 + d23);
/*      */         
/* 2787 */         if (d18 == 0.0D) {
/* 2788 */           d20 = Math.abs(d19);
/*      */         } else {
/* 2790 */           d20 = Math.sqrt(d18 * d18 + d23);
/*      */         } 
/*      */         
/* 2793 */         double d16 = (d21 + d20) * 0.5D;
/* 2794 */         if (d5 > d4) {
/* 2795 */           b = 2;
/* 2796 */           if (d4 / d5 < 1.0E-10D) {
/* 2797 */             bool1 = false;
/* 2798 */             d15 = d5;
/* 2799 */             if (d6 > 1.0D) {
/* 2800 */               d14 = d4 / d5 / d6;
/*      */             } else {
/* 2802 */               d14 = d4 / d5 * d6;
/*      */             } 
/* 2804 */             d10 = 1.0D;
/* 2805 */             d12 = d9 / d8;
/* 2806 */             d13 = 1.0D;
/* 2807 */             d11 = d7 / d8;
/*      */           } 
/*      */         } 
/* 2810 */         if (bool1) {
/* 2811 */           d17 = d4 - d6;
/* 2812 */           if (d17 == d4) {
/* 2813 */             d18 = 1.0D;
/*      */           } else {
/* 2815 */             d18 = d17 / d4;
/*      */           } 
/*      */           
/* 2818 */           d19 = d8 / d7;
/* 2819 */           d22 = 2.0D - d18;
/*      */           
/* 2821 */           d23 = d19 * d19;
/* 2822 */           d24 = d22 * d22;
/* 2823 */           d21 = Math.sqrt(d24 + d23);
/*      */           
/* 2825 */           if (d18 == 0.0D) {
/* 2826 */             d20 = Math.abs(d19);
/*      */           } else {
/* 2828 */             d20 = Math.sqrt(d18 * d18 + d23);
/*      */           } 
/*      */           
/* 2831 */           d16 = (d21 + d20) * 0.5D;
/* 2832 */           d14 = d6 / d16;
/* 2833 */           d15 = d4 * d16;
/*      */           
/* 2835 */           if (d23 == 0.0D) {
/* 2836 */             if (d18 == 0.0D) {
/* 2837 */               d22 = d_sign(d1, d7) * d_sign(d2, d8);
/*      */             } else {
/* 2839 */               d22 = d8 / d_sign(d17, d7) + d19 / d22;
/*      */             } 
/*      */           } else {
/* 2842 */             d22 = (d19 / (d21 + d22) + d19 / (d20 + d18)) * (d16 + 1.0D);
/*      */           } 
/*      */           
/* 2845 */           d18 = Math.sqrt(d22 * d22 + 4.0D);
/* 2846 */           d11 = 2.0D / d18;
/* 2847 */           d13 = d22 / d18;
/* 2848 */           d10 = (d11 + d13 * d19) / d16;
/* 2849 */           d12 = d9 / d7 * d13 / d16;
/*      */         } 
/*      */       } 
/* 2852 */       if (bool) {
/* 2853 */         paramArrayOfDouble3[0] = d13;
/* 2854 */         paramArrayOfDouble2[0] = d11;
/* 2855 */         paramArrayOfDouble5[0] = d12;
/* 2856 */         paramArrayOfDouble4[0] = d10;
/*      */       } else {
/* 2858 */         paramArrayOfDouble3[0] = d10;
/* 2859 */         paramArrayOfDouble2[0] = d12;
/* 2860 */         paramArrayOfDouble5[0] = d11;
/* 2861 */         paramArrayOfDouble4[0] = d13;
/*      */       } 
/*      */       
/* 2864 */       if (b == 1) {
/* 2865 */         d3 = d_sign(d2, paramArrayOfDouble5[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble1);
/*      */       }
/*      */       
/* 2868 */       if (b == 2) {
/* 2869 */         d3 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble2);
/*      */       }
/*      */       
/* 2872 */       if (b == 3) {
/* 2873 */         d3 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble2[0]) * d_sign(d2, paramDouble3);
/*      */       }
/*      */ 
/*      */       
/* 2877 */       paramArrayOfDouble1[paramInt] = d_sign(d15, d3);
/* 2878 */       double d = d3 * d_sign(d2, paramDouble1) * d_sign(d2, paramDouble3);
/* 2879 */       paramArrayOfDouble1[paramInt + 1] = d_sign(d14, d);
/*      */     } 
/*      */     
/* 2882 */     return 0;
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
/*      */   static double compute_rot(double paramDouble1, double paramDouble2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/*      */     double d3, d2, d1;
/* 2897 */     if (paramDouble2 == 0.0D) {
/* 2898 */       d1 = 1.0D;
/* 2899 */       d2 = 0.0D;
/* 2900 */       d3 = paramDouble1;
/* 2901 */     } else if (paramDouble1 == 0.0D) {
/* 2902 */       d1 = 0.0D;
/* 2903 */       d2 = 1.0D;
/* 2904 */       d3 = paramDouble2;
/*      */     } else {
/* 2906 */       double d5 = paramDouble1;
/* 2907 */       double d6 = paramDouble2;
/* 2908 */       double d4 = max(Math.abs(d5), Math.abs(d6));
/* 2909 */       if (d4 >= 4.9947976805055876E145D) {
/* 2910 */         byte b3 = 0;
/* 2911 */         while (d4 >= 4.9947976805055876E145D) {
/* 2912 */           b3++;
/* 2913 */           d5 *= 2.002083095183101E-146D;
/* 2914 */           d6 *= 2.002083095183101E-146D;
/* 2915 */           d4 = max(Math.abs(d5), Math.abs(d6));
/*      */         } 
/* 2917 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 2918 */         d1 = d5 / d3;
/* 2919 */         d2 = d6 / d3;
/* 2920 */         byte b1 = b3;
/* 2921 */         for (byte b2 = 1; b2 <= b3; b2++) {
/* 2922 */           d3 *= 4.9947976805055876E145D;
/*      */         }
/* 2924 */       } else if (d4 <= 2.002083095183101E-146D) {
/* 2925 */         byte b3 = 0;
/* 2926 */         while (d4 <= 2.002083095183101E-146D) {
/* 2927 */           b3++;
/* 2928 */           d5 *= 4.9947976805055876E145D;
/* 2929 */           d6 *= 4.9947976805055876E145D;
/* 2930 */           d4 = max(Math.abs(d5), Math.abs(d6));
/*      */         } 
/* 2932 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 2933 */         d1 = d5 / d3;
/* 2934 */         d2 = d6 / d3;
/* 2935 */         byte b1 = b3;
/* 2936 */         for (byte b2 = 1; b2 <= b3; b2++) {
/* 2937 */           d3 *= 2.002083095183101E-146D;
/*      */         }
/*      */       } else {
/* 2940 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 2941 */         d1 = d5 / d3;
/* 2942 */         d2 = d6 / d3;
/*      */       } 
/* 2944 */       if (Math.abs(paramDouble1) > Math.abs(paramDouble2) && d1 < 0.0D) {
/* 2945 */         d1 = -d1;
/* 2946 */         d2 = -d2;
/* 2947 */         d3 = -d3;
/*      */       } 
/*      */     } 
/* 2950 */     paramArrayOfDouble1[0] = d2;
/* 2951 */     paramArrayOfDouble2[0] = d1;
/* 2952 */     return d3;
/*      */   }
/*      */ 
/*      */   
/*      */   static double d_sign(double paramDouble1, double paramDouble2) {
/* 2957 */     double d = (paramDouble1 >= 0.0D) ? paramDouble1 : -paramDouble1;
/* 2958 */     return (paramDouble2 >= 0.0D) ? d : -d;
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
/* 2970 */     GMatrix gMatrix = null;
/*      */     try {
/* 2972 */       gMatrix = (GMatrix)super.clone();
/* 2973 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*      */       
/* 2975 */       throw new InternalError();
/*      */     } 
/*      */ 
/*      */     
/* 2979 */     gMatrix.values = new double[this.nRow][this.nCol];
/* 2980 */     for (byte b = 0; b < this.nRow; b++) {
/* 2981 */       for (byte b1 = 0; b1 < this.nCol; b1++) {
/* 2982 */         gMatrix.values[b][b1] = this.values[b][b1];
/*      */       }
/*      */     } 
/*      */     
/* 2986 */     return gMatrix;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\GMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */