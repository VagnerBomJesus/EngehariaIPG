/*     */ package javax.vecmath;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GVector
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private int length;
/*     */   double[] values;
/*     */   static final long serialVersionUID = 1398850036893875112L;
/*     */   
/*     */   public GVector(int paramInt) {
/*  39 */     this.length = paramInt;
/*  40 */     this.values = new double[paramInt];
/*  41 */     for (byte b = 0; b < paramInt; ) { this.values[b] = 0.0D; b++; }
/*     */   
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
/*     */   public GVector(double[] paramArrayOfDouble) {
/*  55 */     this.length = paramArrayOfDouble.length;
/*  56 */     this.values = new double[paramArrayOfDouble.length];
/*  57 */     for (byte b = 0; b < this.length; ) { this.values[b] = paramArrayOfDouble[b]; b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVector(GVector paramGVector) {
/*  69 */     this.values = new double[paramGVector.length];
/*  70 */     this.length = paramGVector.length;
/*  71 */     for (byte b = 0; b < this.length; ) { this.values[b] = paramGVector.values[b]; b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVector(Tuple2f paramTuple2f) {
/*  81 */     this.values = new double[2];
/*  82 */     this.values[0] = paramTuple2f.x;
/*  83 */     this.values[1] = paramTuple2f.y;
/*  84 */     this.length = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVector(Tuple3f paramTuple3f) {
/*  94 */     this.values = new double[3];
/*  95 */     this.values[0] = paramTuple3f.x;
/*  96 */     this.values[1] = paramTuple3f.y;
/*  97 */     this.values[2] = paramTuple3f.z;
/*  98 */     this.length = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVector(Tuple3d paramTuple3d) {
/* 108 */     this.values = new double[3];
/* 109 */     this.values[0] = paramTuple3d.x;
/* 110 */     this.values[1] = paramTuple3d.y;
/* 111 */     this.values[2] = paramTuple3d.z;
/* 112 */     this.length = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVector(Tuple4f paramTuple4f) {
/* 122 */     this.values = new double[4];
/* 123 */     this.values[0] = paramTuple4f.x;
/* 124 */     this.values[1] = paramTuple4f.y;
/* 125 */     this.values[2] = paramTuple4f.z;
/* 126 */     this.values[3] = paramTuple4f.w;
/* 127 */     this.length = 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVector(Tuple4d paramTuple4d) {
/* 137 */     this.values = new double[4];
/* 138 */     this.values[0] = paramTuple4d.x;
/* 139 */     this.values[1] = paramTuple4d.y;
/* 140 */     this.values[2] = paramTuple4d.z;
/* 141 */     this.values[3] = paramTuple4d.w;
/* 142 */     this.length = 4;
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
/*     */   public GVector(double[] paramArrayOfDouble, int paramInt) {
/* 158 */     this.length = paramInt;
/* 159 */     this.values = new double[paramInt];
/* 160 */     for (byte b = 0; b < paramInt; b++) {
/* 161 */       this.values[b] = paramArrayOfDouble[b];
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
/*     */   public final double norm() {
/* 173 */     double d = 0.0D;
/*     */ 
/*     */     
/* 176 */     for (byte b = 0; b < this.length; b++) {
/* 177 */       d += this.values[b] * this.values[b];
/*     */     }
/*     */     
/* 180 */     return Math.sqrt(d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double normSquared() {
/* 191 */     double d = 0.0D;
/*     */ 
/*     */     
/* 194 */     for (byte b = 0; b < this.length; b++) {
/* 195 */       d += this.values[b] * this.values[b];
/*     */     }
/*     */     
/* 198 */     return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize(GVector paramGVector) {
/* 207 */     double d1 = 0.0D;
/*     */ 
/*     */     
/* 210 */     if (this.length != paramGVector.length)
/* 211 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector0")); 
/*     */     byte b;
/* 213 */     for (b = 0; b < this.length; b++) {
/* 214 */       d1 += paramGVector.values[b] * paramGVector.values[b];
/*     */     }
/*     */ 
/*     */     
/* 218 */     double d2 = 1.0D / Math.sqrt(d1);
/*     */     
/* 220 */     for (b = 0; b < this.length; b++) {
/* 221 */       this.values[b] = paramGVector.values[b] * d2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize() {
/* 231 */     double d1 = 0.0D;
/*     */     
/*     */     byte b;
/* 234 */     for (b = 0; b < this.length; b++) {
/* 235 */       d1 += this.values[b] * this.values[b];
/*     */     }
/*     */ 
/*     */     
/* 239 */     double d2 = 1.0D / Math.sqrt(d1);
/*     */     
/* 241 */     for (b = 0; b < this.length; b++) {
/* 242 */       this.values[b] = this.values[b] * d2;
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
/*     */   public final void scale(double paramDouble, GVector paramGVector) {
/* 256 */     if (this.length != paramGVector.length) {
/* 257 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector1"));
/*     */     }
/* 259 */     for (byte b = 0; b < this.length; b++) {
/* 260 */       this.values[b] = paramGVector.values[b] * paramDouble;
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
/*     */   public final void scale(double paramDouble) {
/* 272 */     for (byte b = 0; b < this.length; b++) {
/* 273 */       this.values[b] = this.values[b] * paramDouble;
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
/*     */   public final void scaleAdd(double paramDouble, GVector paramGVector1, GVector paramGVector2) {
/* 289 */     if (paramGVector2.length != paramGVector1.length) {
/* 290 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector2"));
/*     */     }
/* 292 */     if (this.length != paramGVector1.length) {
/* 293 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector3"));
/*     */     }
/* 295 */     for (byte b = 0; b < this.length; b++) {
/* 296 */       this.values[b] = paramGVector1.values[b] * paramDouble + paramGVector2.values[b];
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
/*     */   public final void add(GVector paramGVector) {
/* 309 */     if (this.length != paramGVector.length) {
/* 310 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector4"));
/*     */     }
/* 312 */     for (byte b = 0; b < this.length; b++) {
/* 313 */       this.values[b] = this.values[b] + paramGVector.values[b];
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
/*     */   public final void add(GVector paramGVector1, GVector paramGVector2) {
/* 327 */     if (paramGVector1.length != paramGVector2.length) {
/* 328 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector5"));
/*     */     }
/* 330 */     if (this.length != paramGVector1.length) {
/* 331 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector6"));
/*     */     }
/* 333 */     for (byte b = 0; b < this.length; b++) {
/* 334 */       this.values[b] = paramGVector1.values[b] + paramGVector2.values[b];
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
/*     */   public final void sub(GVector paramGVector) {
/* 346 */     if (this.length != paramGVector.length) {
/* 347 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector7"));
/*     */     }
/* 349 */     for (byte b = 0; b < this.length; b++) {
/* 350 */       this.values[b] = this.values[b] - paramGVector.values[b];
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
/*     */   public final void sub(GVector paramGVector1, GVector paramGVector2) {
/* 365 */     if (paramGVector1.length != paramGVector2.length) {
/* 366 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector8"));
/*     */     }
/* 368 */     if (this.length != paramGVector1.length) {
/* 369 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector9"));
/*     */     }
/* 371 */     for (byte b = 0; b < this.length; b++) {
/* 372 */       this.values[b] = paramGVector1.values[b] - paramGVector2.values[b];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void mul(GMatrix paramGMatrix, GVector paramGVector) {
/*     */     double[] arrayOfDouble;
/* 382 */     if (paramGMatrix.getNumCol() != paramGVector.length) {
/* 383 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector10"));
/*     */     }
/* 385 */     if (this.length != paramGMatrix.getNumRow()) {
/* 386 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector11"));
/*     */     }
/*     */     
/* 389 */     if (paramGVector != this) {
/* 390 */       arrayOfDouble = paramGVector.values;
/*     */     } else {
/* 392 */       arrayOfDouble = (double[])this.values.clone();
/*     */     } 
/*     */     
/* 395 */     for (int i = this.length - 1; i >= 0; i--) {
/* 396 */       this.values[i] = 0.0D;
/* 397 */       for (int j = paramGVector.length - 1; j >= 0; j--) {
/* 398 */         this.values[i] = this.values[i] + paramGMatrix.values[i][j] * arrayOfDouble[j];
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
/*     */ 
/*     */   
/*     */   public final void mul(GVector paramGVector, GMatrix paramGMatrix) {
/*     */     double[] arrayOfDouble;
/* 414 */     if (paramGMatrix.getNumRow() != paramGVector.length) {
/* 415 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector12"));
/*     */     }
/* 417 */     if (this.length != paramGMatrix.getNumCol()) {
/* 418 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector13"));
/*     */     }
/*     */     
/* 421 */     if (paramGVector != this) {
/* 422 */       arrayOfDouble = paramGVector.values;
/*     */     } else {
/* 424 */       arrayOfDouble = (double[])this.values.clone();
/*     */     } 
/*     */     
/* 427 */     for (int i = this.length - 1; i >= 0; i--) {
/* 428 */       this.values[i] = 0.0D;
/* 429 */       for (int j = paramGVector.length - 1; j >= 0; j--) {
/* 430 */         this.values[i] = this.values[i] + paramGMatrix.values[j][i] * arrayOfDouble[j];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void negate() {
/* 439 */     for (int i = this.length - 1; i >= 0; i--) {
/* 440 */       this.values[i] = this.values[i] * -1.0D;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void zero() {
/* 448 */     for (byte b = 0; b < this.length; b++) {
/* 449 */       this.values[b] = 0.0D;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSize(int paramInt) {
/*     */     int i;
/* 460 */     double[] arrayOfDouble = new double[paramInt];
/*     */ 
/*     */     
/* 463 */     if (this.length < paramInt) {
/* 464 */       i = this.length;
/*     */     } else {
/* 466 */       i = paramInt;
/*     */     } 
/* 468 */     for (byte b = 0; b < i; b++) {
/* 469 */       arrayOfDouble[b] = this.values[b];
/*     */     }
/* 471 */     this.length = paramInt;
/*     */     
/* 473 */     this.values = arrayOfDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(double[] paramArrayOfDouble) {
/* 484 */     for (int i = this.length - 1; i >= 0; i--) {
/* 485 */       this.values[i] = paramArrayOfDouble[i];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(GVector paramGVector) {
/* 495 */     if (this.length < paramGVector.length) {
/* 496 */       this.length = paramGVector.length;
/* 497 */       this.values = new double[this.length];
/* 498 */       for (byte b = 0; b < this.length; b++)
/* 499 */         this.values[b] = paramGVector.values[b]; 
/*     */     } else {
/* 501 */       int i; for (i = 0; i < paramGVector.length; i++)
/* 502 */         this.values[i] = paramGVector.values[i]; 
/* 503 */       for (i = paramGVector.length; i < this.length; i++) {
/* 504 */         this.values[i] = 0.0D;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple2f paramTuple2f) {
/* 514 */     if (this.length < 2) {
/* 515 */       this.length = 2;
/* 516 */       this.values = new double[2];
/*     */     } 
/* 518 */     this.values[0] = paramTuple2f.x;
/* 519 */     this.values[1] = paramTuple2f.y;
/* 520 */     for (byte b = 2; b < this.length; ) { this.values[b] = 0.0D; b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3f paramTuple3f) {
/* 530 */     if (this.length < 3) {
/* 531 */       this.length = 3;
/* 532 */       this.values = new double[3];
/*     */     } 
/* 534 */     this.values[0] = paramTuple3f.x;
/* 535 */     this.values[1] = paramTuple3f.y;
/* 536 */     this.values[2] = paramTuple3f.z;
/* 537 */     for (byte b = 3; b < this.length; ) { this.values[b] = 0.0D; b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3d paramTuple3d) {
/* 546 */     if (this.length < 3) {
/* 547 */       this.length = 3;
/* 548 */       this.values = new double[3];
/*     */     } 
/* 550 */     this.values[0] = paramTuple3d.x;
/* 551 */     this.values[1] = paramTuple3d.y;
/* 552 */     this.values[2] = paramTuple3d.z;
/* 553 */     for (byte b = 3; b < this.length; ) { this.values[b] = 0.0D; b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4f paramTuple4f) {
/* 562 */     if (this.length < 4) {
/* 563 */       this.length = 4;
/* 564 */       this.values = new double[4];
/*     */     } 
/* 566 */     this.values[0] = paramTuple4f.x;
/* 567 */     this.values[1] = paramTuple4f.y;
/* 568 */     this.values[2] = paramTuple4f.z;
/* 569 */     this.values[3] = paramTuple4f.w;
/* 570 */     for (byte b = 4; b < this.length; ) { this.values[b] = 0.0D; b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4d paramTuple4d) {
/* 579 */     if (this.length < 4) {
/* 580 */       this.length = 4;
/* 581 */       this.values = new double[4];
/*     */     } 
/* 583 */     this.values[0] = paramTuple4d.x;
/* 584 */     this.values[1] = paramTuple4d.y;
/* 585 */     this.values[2] = paramTuple4d.z;
/* 586 */     this.values[3] = paramTuple4d.w;
/* 587 */     for (byte b = 4; b < this.length; ) { this.values[b] = 0.0D; b++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 596 */   public final int getSize() { return this.values.length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 606 */   public final double getElement(int paramInt) { return this.values[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 617 */   public final void setElement(int paramInt, double paramDouble) { this.values[paramInt] = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 625 */     StringBuffer stringBuffer = new StringBuffer(this.length * 8);
/*     */ 
/*     */ 
/*     */     
/* 629 */     for (byte b = 0; b < this.length; b++) {
/* 630 */       stringBuffer.append(this.values[b]).append(" ");
/*     */     }
/*     */     
/* 633 */     return stringBuffer.toString();
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
/*     */   public int hashCode() {
/* 648 */     long l = 1L;
/*     */     
/* 650 */     for (byte b = 0; b < this.length; b++) {
/* 651 */       l = 31L * l + VecMathUtil.doubleToLongBits(this.values[b]);
/*     */     }
/*     */     
/* 654 */     return (int)(l ^ l >> 32);
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
/*     */   public boolean equals(GVector paramGVector) {
/*     */     try {
/* 667 */       if (this.length != paramGVector.length) return false;
/*     */       
/* 669 */       for (byte b = 0; b < this.length; b++) {
/* 670 */         if (this.values[b] != paramGVector.values[b]) return false;
/*     */       
/*     */       } 
/* 673 */       return true;
/*     */     } catch (NullPointerException nullPointerException) {
/* 675 */       return false;
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
/*     */   public boolean equals(Object paramObject) {
/*     */     
/* 688 */     try { GVector gVector = (GVector)paramObject;
/*     */       
/* 690 */       if (this.length != gVector.length) return false;
/*     */       
/* 692 */       for (byte b = 0; b < this.length; b++) {
/* 693 */         if (this.values[b] != gVector.values[b]) return false; 
/*     */       } 
/* 695 */       return true; }
/*     */     catch (ClassCastException classCastException)
/* 697 */     { return false; }
/* 698 */     catch (NullPointerException nullPointerException) { return false; }
/*     */   
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
/*     */   public boolean epsilonEquals(GVector paramGVector, double paramDouble) {
/* 715 */     if (this.length != paramGVector.length) return false;
/*     */     
/* 717 */     for (byte b = 0; b < this.length; b++) {
/* 718 */       double d = this.values[b] - paramGVector.values[b];
/* 719 */       if (((d < 0.0D) ? -d : d) > paramDouble) return false; 
/*     */     } 
/* 721 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double dot(GVector paramGVector) {
/* 731 */     if (this.length != paramGVector.length) {
/* 732 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector14"));
/*     */     }
/* 734 */     double d = 0.0D;
/* 735 */     for (byte b = 0; b < this.length; b++) {
/* 736 */       d += this.values[b] * paramGVector.values[b];
/*     */     }
/* 738 */     return d;
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
/*     */   public final void SVDBackSolve(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3, GVector paramGVector) {
/* 755 */     if (paramGMatrix1.nRow != paramGVector.getSize() || paramGMatrix1.nRow != paramGMatrix1.nCol || paramGMatrix1.nRow != paramGMatrix2.nRow)
/*     */     {
/*     */       
/* 758 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector15"));
/*     */     }
/*     */     
/* 761 */     if (paramGMatrix2.nCol != this.values.length || paramGMatrix2.nCol != paramGMatrix3.nCol || paramGMatrix2.nCol != paramGMatrix3.nRow)
/*     */     {
/*     */       
/* 764 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector23"));
/*     */     }
/*     */     
/* 767 */     GMatrix gMatrix = new GMatrix(paramGMatrix1.nRow, paramGMatrix2.nCol);
/* 768 */     gMatrix.mul(paramGMatrix1, paramGMatrix3);
/* 769 */     gMatrix.mulTransposeRight(paramGMatrix1, paramGMatrix2);
/* 770 */     gMatrix.invert();
/* 771 */     mul(gMatrix, paramGVector);
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
/*     */   public final void LUDBackSolve(GMatrix paramGMatrix, GVector paramGVector1, GVector paramGVector2) {
/* 789 */     int i = paramGMatrix.nRow * paramGMatrix.nCol;
/*     */     
/* 791 */     double[] arrayOfDouble1 = new double[i];
/* 792 */     double[] arrayOfDouble2 = new double[i];
/* 793 */     int[] arrayOfInt = new int[paramGVector1.getSize()];
/*     */ 
/*     */     
/* 796 */     if (paramGMatrix.nRow != paramGVector1.getSize()) {
/* 797 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector16"));
/*     */     }
/*     */     
/* 800 */     if (paramGMatrix.nRow != paramGVector2.getSize()) {
/* 801 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector24"));
/*     */     }
/*     */     
/* 804 */     if (paramGMatrix.nRow != paramGMatrix.nCol) {
/* 805 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector25"));
/*     */     }
/*     */     int j;
/* 808 */     for (j = 0; j < paramGMatrix.nRow; j++) {
/* 809 */       for (int k = 0; k < paramGMatrix.nCol; k++) {
/* 810 */         arrayOfDouble1[j * paramGMatrix.nCol + k] = paramGMatrix.values[j][k];
/*     */       }
/*     */     } 
/*     */     
/* 814 */     for (j = 0; j < i; ) { arrayOfDouble2[j] = 0.0D; j++; }
/* 815 */      for (j = 0; j < paramGMatrix.nRow; ) { arrayOfDouble2[j * paramGMatrix.nCol] = paramGVector1.values[j]; j++; }
/* 816 */      for (j = 0; j < paramGMatrix.nCol; ) { arrayOfInt[j] = (int)paramGVector2.values[j]; j++; }
/*     */     
/* 818 */     GMatrix.luBacksubstitution(paramGMatrix.nRow, arrayOfDouble1, arrayOfInt, arrayOfDouble2);
/*     */     
/* 820 */     for (j = 0; j < paramGMatrix.nRow; ) { this.values[j] = arrayOfDouble2[j * paramGMatrix.nCol]; j++; }
/*     */   
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
/* 832 */   public final double angle(GVector paramGVector) { return Math.acos(dot(paramGVector) / norm() * paramGVector.norm()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 840 */   public final void interpolate(GVector paramGVector1, GVector paramGVector2, float paramFloat) { interpolate(paramGVector1, paramGVector2, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 848 */   public final void interpolate(GVector paramGVector, float paramFloat) { interpolate(paramGVector, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void interpolate(GVector paramGVector1, GVector paramGVector2, double paramDouble) {
/* 861 */     if (paramGVector2.length != paramGVector1.length) {
/* 862 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector20"));
/*     */     }
/* 864 */     if (this.length != paramGVector1.length) {
/* 865 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector21"));
/*     */     }
/* 867 */     for (byte b = 0; b < this.length; b++) {
/* 868 */       this.values[b] = (1.0D - paramDouble) * paramGVector1.values[b] + paramDouble * paramGVector2.values[b];
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
/*     */   public final void interpolate(GVector paramGVector, double paramDouble) {
/* 880 */     if (paramGVector.length != this.length) {
/* 881 */       throw new MismatchedSizeException(VecMathI18N.getString("GVector22"));
/*     */     }
/* 883 */     for (byte b = 0; b < this.length; b++) {
/* 884 */       this.values[b] = (1.0D - paramDouble) * this.values[b] + paramDouble * paramGVector.values[b];
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
/*     */   public Object clone() {
/* 897 */     GVector gVector = null;
/*     */     try {
/* 899 */       gVector = (GVector)super.clone();
/* 900 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 902 */       throw new InternalError();
/*     */     } 
/*     */ 
/*     */     
/* 906 */     gVector.values = new double[this.length];
/* 907 */     for (byte b = 0; b < this.length; b++) {
/* 908 */       gVector.values[b] = this.values[b];
/*     */     }
/*     */     
/* 911 */     return gVector;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\GVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */